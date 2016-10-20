package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazAVIDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazEVIDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service para el envio de PreFacturacion Detalle de la Interfaz AVI.
 *   
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
@Service("sisicc.interfazAVIEnviarPrefacturacionDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAVIEnviarPrefacturacionDetalleServiceImpl extends
        BaseInterfazSalidaAbstractService {
	
	@Resource(name="sisicc.interfazEVIDAO")
	private InterfazEVIDAO interfazEVIDAO;
	
	@Resource(name="sisicc.interfazAVIDAO")
	private InterfazAVIDAO interfazAVIDAO;
		
    protected void beforeProcessInterfaz(InterfazParams interfazParams) {
        Map params = interfazParams.getQueryParams();
        
        // Usamos un parametro para determinar si ya se realizo la carga de
        //  resumenes y saldos
        String resumenesActualizados = MapUtils.getString(params, "resumenesActualizados", Constants.NO);

        if(StringUtils.equals(resumenesActualizados, Constants.SI)) {
            log.info("La carga de resumenes y saldos se realizo previamente.");
        }
        else {
            log.info("Ejecutando carga de resumenes y saldos");
            //this.interfazEVIDAO.cargarResumenesPrefacturacion();
            this.interfazEVIDAO.updateInterfazEVIRecepcionarOCSaldoDeudor(params);
            params.put("resumenesActualizados", Constants.SI);
        }
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
     */
    protected void executeStoreProcedure(Map params) {
		interfazAVIDAO.executeInterfazAVIPrefacturacionDetalle(params); //getInterfazAVIPrefacturacionDetalle
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

        // Obtenemos la informacin
        List listPrefacturacionDetalle = null;
        try {
        	listPrefacturacionDetalle = this.interfazAVIDAO
            		.getInterfazAVIPrefacturacionDetalle(queryParams);
        	        	
	        
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return listPrefacturacionDetalle;
    }

    
}
