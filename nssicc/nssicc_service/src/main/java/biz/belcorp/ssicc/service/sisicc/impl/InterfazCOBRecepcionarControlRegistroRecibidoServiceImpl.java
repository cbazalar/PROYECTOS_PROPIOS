/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.FileUtil;

/**
 * <p>
 * <a href="InterfazCOBRecepcionarControlRegistroRecibidoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jvelasquez@sigcomt.com">Jorge Velasquez</a>
 */
@Service("sisicc.interfazCOBRecepcionarControlRegistroRecibidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBRecepcionarControlRegistroRecibidoServiceImpl extends BaseInterfazEntradaAbstractService {

	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		
		Map params = interfazParams.getQueryParams();
		
		List datosFtp = this.interfazCOBDAO.getDatosFTPCOBRecepcion();
		
		Interfaz interfaz = interfazParams.getInterfaz();
		Interfaz inter = new Interfaz();
		List archivos = new ArrayList();
		StringBuffer listaArchivosParStor = new StringBuffer(); 
		
		if (datosFtp != null && datosFtp.size() > 0) {
			for (int i = 0; i < datosFtp.size(); i++) {
				Map map = (Map)datosFtp.get(i);
				String direccion = (String)map.get("direccionFtp");
				String servidor = (String)map.get("servidorFtp");
				BigDecimal puertobd = (BigDecimal)map.get("puertoFtp");
				String usuario = (String)map.get("usuarioFtp");
				String clave = (String)map.get("claveFtp");
				String codigoCobrador = (String)map.get("codigoUsuarioCobrador");
				
				String puerto = ""; 
				if(puertobd !=null){
					puerto= puertobd.toString();
				}
				
				if (StringUtils.isNotBlank(servidor)
						&& StringUtils.isNotBlank(usuario)
						&& StringUtils.isNotBlank(clave)
						&& StringUtils.isNotBlank(direccion)
						&& StringUtils.isNotBlank(codigoCobrador)) {
					
					inter = new Interfaz();
					inter.setServidorFtp(servidor);
					inter.setUsuarioFtp(usuario);
					inter.setPasswordFtp(clave);
					
					if (StringUtils.isNotBlank(puerto)) {
						inter.setPuertoFtp(puerto);
					}
					else
					{
						inter.setPuertoFtp(Constants.FTP_DEFAULT_PORT);
					}
					
					try {
						//nos conectamos al servidor FTP
						FTPUtil ftpUtil = new FTPUtil();
						ftpUtil.loguearFTPPasivo(inter);
						
						ArrayList listaArchivos = ftpUtil.buscarListaArchivo(direccion, interfaz.getNombreArchivoEntradaSalida(), interfaz.getExtensionArchivoDescripcion(), interfaz.getTipoNombreArchivo());
												
						String[] fileNames = (String[]) listaArchivos.toArray(new String[listaArchivos.size()]);
						
						if (fileNames != null && fileNames.length > 0) {
							//lo ordenamos por nombre para obtener solo el mas reciente
							Arrays.sort(fileNames);
							
							//Descargar el archivo mas reciente desde el FTP
							String nombreFinalArchivo = String.format("%s_%s", codigoCobrador, fileNames[fileNames.length-1]);
							
							listaArchivosParStor.append(StringUtils.remove(nombreFinalArchivo, "."+interfaz.getExtensionArchivoDescripcion())).append(",");
							
							ftpUtil.copiarArchivoFTPaRed(direccion, fileNames[fileNames.length-1], interfaz.getDirectorioTemporal(), nombreFinalArchivo);
							ftpUtil.eliminarArchivo(direccion, fileNames[fileNames.length-1]);
							//
							archivos.add(nombreFinalArchivo);
						}
						else
						{
							this.log.warn("NO SE UBICARON ARCHIVOS EN EL DIRECTORIO DEL PROVEEDOR: " + codigoCobrador);							
						}
						
						ftpUtil.cerrarFTP();
					} catch (Exception e){ 
						this.log.error("ERROR al intentar descargar archivos del directorio del proveedor: " + codigoCobrador);
						e.printStackTrace();
					}
					
				}else {
					this.log.warn("Datos de FTP incompletos");
				}
			}
			
			if(archivos.size() > 0)
			{
				try
				{
					//Juntar los archivos descargados de los proveedores
					ByteBuffer[] buffers = new ByteBuffer[archivos.size()];
					
					for (int i = 0; i < archivos.size(); i++) {
						if(!FileUtil.validarUltimaLineaArchivo(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) + archivos.get(i).toString()))
						{
							this.log.warn("El archivo no pudo ser preprocesado (Posible omision del primer registro del siguiente archivo.)");
						}
						RandomAccessFile raf = new RandomAccessFile(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) + archivos.get(i).toString(), "r");
						FileChannel channel = raf.getChannel();
						buffers[i] = channel.map(FileChannel.MapMode.READ_ONLY, 0, raf.length());
						
						channel.close();
						raf.close();
					}

					String nombreArchivoConsolidado = String.format("%s%s.%s", interfaz.getNombreArchivoEntradaSalida(), DateUtil.getDateTimeNow(Constants.PATRON_FECHA_AAAAMMDD, new Date()), interfaz.getExtensionArchivoDescripcion());
					FileOutputStream outFile = new FileOutputStream(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) + nombreArchivoConsolidado);
					FileChannel out = outFile.getChannel();
					out.write(buffers);
					out.close();
					//
					
					//Elimino los archivos concatenados
					//Se envia archivo al Historico y luego se elimina
					for (int i = 0; i < archivos.size(); i++) {
						String arrNombre[] = StringUtils.split(archivos.get(i).toString(), ".");
						String fechaHora = DateUtil.getDateTime(Constants.DEFAULT_PLAIN_DATE_TIME_FORMAT, new Date());
						
						String nombreArchivoHistorico = String.format("%s_%s.%s", arrNombre[0], fechaHora, arrNombre[1]);
						
						File fileSrc = new File(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) + archivos.get(i).toString());
						File fileDes = new File(FileUtil.formatDirectory(interfaz.getDirectorioHistorico()) + nombreArchivoHistorico);
						
						FileUtils.copyFile(fileSrc, fileDes);
						
						if(fileSrc.exists()) {
							fileSrc.delete();
						}						
					}
					//
					
					//Enviamos el consolidado al directorio de E/S
					File copiaFuente = new File(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()) + nombreArchivoConsolidado);
					File copiaDestino = new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()) + nombreArchivoConsolidado);
					
					FileUtils.copyFile(copiaFuente, copiaDestino);
					//
					
					//Eliminamos el consolidado del temporal
					copiaFuente.delete();
					//
				}
				catch(Exception ex)
				{
					this.log.error(ex.getMessage(), ex);
				}
			}
			else{
				this.log.warn("No se copiaron archivos a la Red");
			}
			//
			
		}
		
		params.put("listaArchivosParStor", listaArchivosParStor.toString());		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	@Override
	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		
		Map params = interfazParams.getQueryParams();
		Interfaz interfaz = interfazParams.getInterfaz();
		
		params.put("nombreArchivo", interfazParams.getTempName());
		
		this.interfazCOBDAO.executeInterfazCOBRecepcionarControlRegistroRecibido(params);

		//Eliminar Historico en RED
		File histoFile = new File(interfazParams.getHistoricoPath());
		
		if(histoFile.exists())
			histoFile.delete();
		
		String tipoNombreArchivo = interfazParams.getInterfaz().getTipoNombreArchivo();		
		if  (tipoNombreArchivo.equals(Constants.ARCHIVO_FIJO)) {
			File histoFijoFile = new File(interfazParams.getHistoricoPathNombreFijo());
			
			if(histoFijoFile.exists())
				histoFijoFile.delete();
		}		
		//
		
	}

	/**
	 * @param interfazCOBDAO the interfazCOBDAO to set
	 */
	public void setInterfazCOBDAO(InterfazCOBDAO interfazCOBDAO) {
		this.interfazCOBDAO = interfazCOBDAO;
	}
	
}
