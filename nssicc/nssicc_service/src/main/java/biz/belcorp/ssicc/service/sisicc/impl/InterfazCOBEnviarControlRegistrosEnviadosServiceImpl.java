package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.ZipUtil;

/**
 * <p>
 * <a href="InterfazCOBEnviarControlRegistrosEnviadosServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("sisicc.interfazCOBEnviarControlRegistrosEnviadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarControlRegistrosEnviadosServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazCOBDAO.executeInterfazCOBEnviarControlRegistrosEnviados(params);
	}
	
	protected void afterProcessInterfaz(InterfazParams interfazParams)
	throws InterfazException {

		//Sacar la lista de usuarios
		List proveedores = interfazCOBDAO.getDatosFTPProveedores(interfazParams.getInterfaz().getCodigoPais());
		
		if(proveedores != null && proveedores.size() > 0)
		{
			for(int i=0; i<proveedores.size(); i++)
			{
				Map proveedor = (Map)proveedores.get(i);
				
				Interfaz interfaz = interfazParams.getInterfaz();
				
				String usuarioCobra = MapUtils.getString(proveedor, "usuario", "");
				String servidor = MapUtils.getString(proveedor, "servidorFTP", "");
				String usuario = MapUtils.getString(proveedor, "usuarioFTP", "");
				String clave = MapUtils.getString(proveedor, "claveFTP", "");
				String direccion = MapUtils.getString(proveedor, "direccionFTP", "");
				String puerto = MapUtils.getString(proveedor, "puertoFTP", "");
				
				String prefijoArchivo =String.format("%s_", usuarioCobra);
					
				if(StringUtils.isNotBlank(servidor) && StringUtils.isNotBlank(usuario) && StringUtils.isNotBlank(clave) && StringUtils.isNotBlank(direccion))
				{
					interfaz.setServidorFtp(servidor);
					interfaz.setUsuarioFtp(usuario);
					interfaz.setPasswordFtp(clave);
					interfaz.setDirectorioEntradaSalida(direccion);
					
					if (StringUtils.isNotBlank(puerto))
						interfaz.setPuertoFtp(puerto);
					
					//Sacamos los archivos del usuaarios					
					List listNombreArchivosSalida = interfazParams.getListNombreArchivosGenerados(usuarioCobra, "ZIP");
					
					if (listNombreArchivosSalida.size() > 0) {
						
						for(int j=0; j<listNombreArchivosSalida.size(); j++)
						{
							if(log.isDebugEnabled())
								log.debug("Enviando archivo: " + listNombreArchivosSalida.get(j));
							
							//Descomprimimos el archivo y lo renombramos a TXT
							File zippedFile = new File((String)listNombreArchivosSalida.get(j)); 
							File unzippedFile = new File(ZipUtil.unzipFile(zippedFile.getAbsolutePath(), FileUtil.formatDirectory(interfazParams.getInterfaz().getDirectorioProc())));
							File tempFile = new File(StringUtils.remove(StringUtils.replace(unzippedFile.getAbsolutePath(), ".TMP", ".TXT"), prefijoArchivo));
							
							if(unzippedFile.renameTo(tempFile))
							{
								this.sendOtherFile(interfazParams, tempFile);
							}
							
							//Eliminamos el zipeado y el unzipeado
							zippedFile.delete();
							unzippedFile.delete();
						}
					}
					
				}
				else
				{
					log.warn("Datos de FTP incompletos");
				}
			}
		}
	}	
}