/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarInscritasServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOMGenerarFicheroAdamServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 */
@Service("sisicc.interfazCOMEnviarArchivoAdamService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOMEnviarArchivoAdamServiceImpl extends
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
        
        // Actualizamos el nombre del archivo para incluir el codigo de comision
        String codigoComision = MapUtils.getString(queryParams, "codigoComision");
        if(StringUtils.isNotBlank(codigoComision)) {
            String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivo");
            // Quitamos el numero de lote
            nombreArchivo = StringUtils.substring(nombreArchivo, 0, StringUtils.indexOf(nombreArchivo, numeroLote));
            // Concatenamos el valor de la comision
            nombreArchivo = nombreArchivo + codigoComision + "_";
            // Asignamos el nuevo valor en el map
            queryParams.put("nombreArchivo", nombreArchivo + numeroLote);
            // Actualizamos tambien el objeto interfaz
            interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
        }

		if (log.isDebugEnabled()) {
			log.debug(queryParams);
            log.debug(interfazParams.getInterfaz().getNombreArchivoEntradaSalida());
		}
		return queryParams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazCOMEnviarArchivoAdam(params);
	}

}
