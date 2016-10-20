package biz.belcorp.ssicc.service.sisicc.framework.impl;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.ZipUtil;

/**
 * Clase service que sirve para Ejecutar Interfaces de Paquete, para
 * el caso que desean enviar zipeado todas los archivos generados
 * por las interfaces unitarias. Cada interfaz unitaria guarda su archivo
 * generado en una carpeta temporal del paquete (carpeta temporal + codigo_paquete + numero lote)
 * para luego ser recupeados y zipeados en esta clase
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("sisicc.baseInterfazSalidaPaqueteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BaseInterfazSalidaPaqueteService extends BaseInterfazSalidaAbstractService {
	private static final char SEPARATOR = ';';

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
	protected final List readData(Map params) throws InterfazException {
		log.debug("Entering 'readData' method");
		log.info("No se realiza nada, porque cada interfaz unitaria esta realizando la generacion de su archivo");
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#writeData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File, java.util.List)
	 */
	protected final int writeData(InterfazParams interfazParams, File tempFile,
			List data) throws InterfazException {
		log.debug("Entering 'writeData' method");
		int registrosProcesados = 0;
		
		return registrosProcesados;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#prepareFileBeforeSend(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File)
	 */
	protected File prepareFileBeforeSend(InterfazParams interfazParams,
			File tempFile) {
		log.debug("Entering 'prepareFileBeforeSend' method");
		Interfaz interfaz = interfazParams.getInterfaz();
		if (interfaz.comprimir()) {
			File zipFile = null;
			
			try {
				// Comprimo el directorio temporal
				ZipUtil.zipDir(interfaz.getDirectorioTemporalPaquete(), interfazParams.getTempZipPath());

				zipFile = new File(interfazParams.getTempZipPath());
				
				// Elimino el directorio temporal del paquete
				FileUtils.deleteDirectory(new File(FileUtil.formatDirectory(interfaz.getDirectorioTemporalPaquete())));

			} catch(Exception ex) {
				log.error("NO SE PUDO ZIPEAR EL PAQUETE DE INTERFACES :" + interfaz.getCodigo());
			}

			return zipFile;
		} else {
			return tempFile;
		}

	}

}