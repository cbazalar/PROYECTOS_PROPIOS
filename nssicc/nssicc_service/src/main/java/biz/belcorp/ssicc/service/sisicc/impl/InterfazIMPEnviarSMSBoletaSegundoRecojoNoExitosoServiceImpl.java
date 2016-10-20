package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.util.DateUtil;

/**
 * @author ghuertas
 * 
 */
@Service("sisicc.interfazIMPEnviarSMSBoletaSegundoRecojoNoExitosoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnviarSMSBoletaSegundoRecojoNoExitosoServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		log.debug("Entering 'executeStoreProcedure' method");
		interfazSiCCService.executeEnviarSMSBoletaSegundoRecojoNoExitoso(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService#prepareQueryParams(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams)
	 */
	@Override
	protected Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
		if (log.isDebugEnabled()) {
		    log.debug("Dentro del metodo 'prepareQueryParams'");
		}
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		Interfaz interfaz = interfazParams.getInterfaz();
		
		String nombreArchivo = interfaz.getNombreArchivoEntradaSalida();
		
		/*creamos el nombre solicitado*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		
		String fechaActual = sdf.format(new Date(System.currentTimeMillis()));
		nombreArchivo = String.format("%s%s_%s", nombreArchivo, fechaActual, "P"); 
		/**/
		
		queryParams.put("nombreArchivo", nombreArchivo);
		 // Actualizamos tambien el objeto interfaz
       interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
       
       // Si el archivo es variable, para evitar problemas en la lectura de archivos, forzamos a que sea de nombre de archivo fijo
       interfazParams.getInterfaz().setTipoNombreArchivo(Constants.ARCHIVO_FIJO);
		
		if (log.isDebugEnabled()) {
			log.debug(queryParams);
           log.debug(interfazParams.getInterfaz().getNombreArchivoEntradaSalida());
		}
		
		return queryParams;
	}
	
	protected String adicionarKeyTituloCabecera() {
		return "interfazIMPEnviarSMSBoletaSegundoRecojoNoExitoso";
	}
}
