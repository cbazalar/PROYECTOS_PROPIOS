/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

@Service("sisicc.interfazEnviarResumenDiarioPercepcionesSunatService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazEnviarResumenDiarioPercepcionesSunatServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;
		
	@Override
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazEnviarResumenDiarioPercepcionesSunat(params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService#prepareQueryParams(biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams)
	 */
	@Override
	protected Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled()) {
		    log.debug("Dentro del metodo 'prepareQueryParams'");
		}
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		Interfaz interfaz = interfazParams.getInterfaz();
		
		String nombreArchivo = interfaz.getNombreArchivoEntradaSalida();
		String numeroLote = MapUtils.getString(queryParams, "numeroLote");
		String fechaActual = MapUtils.getString(queryParams, "fechaGenera");		
		String nuevoNumeroLote = StringUtils.substring(numeroLote, 8);				
		nuevoNumeroLote = StringUtils.leftPad(nuevoNumeroLote, 5, '0');		
		nombreArchivo = String.format("%s%s-%s", nombreArchivo, fechaActual, nuevoNumeroLote);
		
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
}