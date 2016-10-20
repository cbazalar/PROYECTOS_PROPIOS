package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIVREnviarTablaControlServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos A. Cairampoma</a>
 */
@Service("sisicc.interfazIVREnviarTablaControlService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIVREnviarTablaControlServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	/**
	 * Devuelve el Map de parametros del query. Sobreescrito con la finalidad de
	 * obtener los 'Parametros de Interaz' configurados en el mantenimiento de
	 * la interfaz
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Map con parametros del query
	 */
	public Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'prepareQueryParams'");
        }
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		// Obtenemos el codigoISO del idioma del usuario
		queryParams.put("codigoISO", interfazParams.getUsuario().getIdioma()
				.getCodigoISO());

		String codigoPais = (String) queryParams.get("codigoPais");
		String codigoSistema = (String) queryParams.get("codigoSistema");
		String codigoInterfaz = (String) queryParams.get("codigoInterfaz");
		
		
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		String numeroLote = (String) queryParams.get("numeroLote");
		if (StringUtils.isEmpty(numeroLote)) {
			numeroLote = interfazService.getNumeroLote(interfazEjecucionPK);
			queryParams.put("numeroLote", numeroLote);
		}
        
        // Actualizamos el nombre del archivo
        String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivo");

		Map paramsIVR = new HashMap();
		paramsIVR.put("nombreArchivo", nombreArchivo);
		paramsIVR.put("codigoPais", codigoPais);
		paramsIVR.put("infoAdic", Constants.IVR_SISTEMA_COMERCIAL);

		nombreArchivo = interfazSiCCDAO.getNombreArchivoIVR(paramsIVR);
		// Concatenamos el valor de la comision

		queryParams.put("nombreArchivo", nombreArchivo);
		// Actualizamos tambien el objeto interfaz
		interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
        

		if (log.isDebugEnabled()) {
			log.debug(queryParams);
            log.debug(interfazParams.getInterfaz().getNombreArchivoEntradaSalida());
		}
		return queryParams;
	}
	
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazIVREnviarTablaControl(params);
	}

}
