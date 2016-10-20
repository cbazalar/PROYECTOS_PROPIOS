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
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service para el envio de Facturacion Cabecera de la Interfaz AVI.
 *  
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
@Service("sisicc.interfazAVIEnviarFacturacionCabeceraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAVIEnviarFacturacionCabeceraServiceImpl extends
        BaseInterfazSalidaAbstractService {
	
	@Resource(name="sisicc.interfazAVIDAO")
	private InterfazAVIDAO interfazAVIDAO;
	 
	protected void beforeProcessInterfaz(InterfazParams interfazParams) {
        Map params = interfazParams.getQueryParams();

        // De la misma forma para determinar si se realiza la carga de detalle de facturacion
        String detalleCargado = MapUtils.getString(params, "detalleCargado", Constants.NO);
        if(StringUtils.equals(detalleCargado, Constants.SI)) {
            log.info("La carga de Facturacion Detalle se realizo prAVIamente.");
        }
        else {
            log.info("Ejecutando carga de tabla temporal de Facturacion Detalle");
            this.interfazAVIDAO.cargarFacturacionDetalle(params);
            params.put("detalleCargado", Constants.SI);
        }

        log.info("Ejecutando carga de tabla temporal de Facturacion Cabecera");
		this.interfazAVIDAO.cargarFacturacionCabecera(params);
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

        log.debug(queryParams);
        // Obtenemos la informacin
        List listFacturacionCabecera = null;
        try {
        	listFacturacionCabecera = this.interfazAVIDAO
            		.getInterfazAVIFacturacionCabecera(queryParams);
        	        	
	        
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return listFacturacionCabecera;
    }

   
}
