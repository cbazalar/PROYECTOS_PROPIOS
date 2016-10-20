/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarDuplaCyzoneServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSICEnviarDuplaCyzoneServiceImpl.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius </a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSICEnviarDuplaCyzoneServiceImpl extends
        BaseInterfazSalidaAbstractService {

   
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
        // Obtenemos los parametros por defecto de la clase padre
        Map queryParams = super.prepareQueryParams(interfazParams);
        
        // Obtenemos el codigoISO del idioma del usuario
        queryParams.put("codigoISO", interfazParams.getUsuario().getIdioma().getCodigoISO());        
        if (log.isDebugEnabled()) {
            log.debug(queryParams);
        }

        return queryParams;
    }
	
		
	/**
	 * Obtiene la data para la interfaz
	 * 
	 * @param queryParams
	 *            parametros del query.
	 * @return List con Maps de las filas resultado.
	 * @throws InterfazException
	 */
    protected List readData(Map queryParams) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'readData' method ");

        // Obtenemos la fecha de ultimo proceso
        InterfazPK pk = new InterfazPK(MapUtils.getString(queryParams,
                "codigoPais"),
                MapUtils.getString(queryParams, "codigoSistema"), MapUtils
                        .getString(queryParams, "codigoInterfaz"));
        queryParams.put("fechaUltimoProceso", interfazService
                .getFechaUltimoProceso(pk));        
        
        // Obtenemos la informacin, depende del tipo de interfaz, se selecciona la informacion a enviar
        String interfazSeleccionada = queryParams.get("interfazSeleccionada").toString();
        List listVentaBaseConsultoras = null;
        try {
	        if (interfazSeleccionada.equals("1")){
	            listVentaBaseConsultoras = this.interfazSiCCDAO
	                    .getInterfazSICVentaBaseConsultorasList(queryParams);
	        }else if (interfazSeleccionada.equals("2")){
	        	listVentaBaseConsultoras = this.interfazSiCCDAO
                		.getInterfazSICVentasPuntajesXCampanyaList(queryParams);
	        }else if (interfazSeleccionada.equals("3")){
	        	listVentaBaseConsultoras = this.interfazSiCCDAO
                		.getInterfazSICDuplaCyZoneInscritasList(queryParams);
	        }
	        
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return listVentaBaseConsultoras;
    }

}
