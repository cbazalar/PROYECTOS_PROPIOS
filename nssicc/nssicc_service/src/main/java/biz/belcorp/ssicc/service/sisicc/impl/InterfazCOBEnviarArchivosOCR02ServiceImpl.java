package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
 * <a href="InterfazCOBEnviarArchivosOCR02ServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("sisicc.interfazCOBEnviarArchivosOCR02Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarArchivosOCR02ServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazCOBDAO.executeInterfazCOBEnviarArchivoOCR2(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		
		List datosFtp = interfazCOBDAO.getDatosFTP();
		
		if(datosFtp != null && datosFtp.size() > 0){
			String direccion = (String) datosFtp.get(0);
			String servidor = (String) datosFtp.get(1);
			String puerto = (String) datosFtp.get(2);
			String usuario = (String) datosFtp.get(3);
			String clave = (String) datosFtp.get(4);
			String archivo = "OCR_COB_02";
			
			Interfaz interfaz = interfazParams.getInterfaz();
			
			if(StringUtils.isNotBlank(servidor) && StringUtils.isNotBlank(usuario) && StringUtils.isNotBlank(clave) && StringUtils.isNotBlank(direccion)) {
				interfaz.setServidorFtp(servidor);
				interfaz.setUsuarioFtp(usuario);
				interfaz.setPasswordFtp(clave);
				interfaz.setDirectorioEntradaSalida(direccion);
				
				if (StringUtils.isNotBlank(puerto))
					interfaz.setPuertoFtp(puerto);
				
				//Sacamos los archivos del usuaarios					
				List listNombreArchivosSalida = interfazParams.getListNombreArchivosGenerados(archivo, "ZIP");
				
				if (listNombreArchivosSalida.size() > 0) {
					
					for(int j=0; j<listNombreArchivosSalida.size(); j++)
					{
						if(log.isDebugEnabled())
							log.debug("Enviando archivo: " + listNombreArchivosSalida.get(j));
						
						//Descomprimimos el archivo y lo renombramos a TXT
						File zippedFile = new File((String)listNombreArchivosSalida.get(j)); 
						File unzippedFile = new File(ZipUtil.unzipFile(zippedFile.getAbsolutePath(), FileUtil.formatDirectory(interfazParams.getInterfaz().getDirectorioProc())));
						File tempFile = new File(StringUtils.replace(unzippedFile.getAbsolutePath(), ".TMP", ".TXT"));
						
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
			else {
				log.warn("Datos de FTP incompletos");
			}
		}
	}
}