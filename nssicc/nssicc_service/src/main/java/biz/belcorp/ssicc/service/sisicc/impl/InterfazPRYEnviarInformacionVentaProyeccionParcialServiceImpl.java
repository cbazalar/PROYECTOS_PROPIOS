package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazPRYDAO;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service del envio de datos para la Proyeccion Parcial de la Interfaz PRY.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
@Service("sisicc.interfazPRYEnviarInformacionVentaProyeccionParcialService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRYEnviarInformacionVentaProyeccionParcialServiceImpl extends
        BaseInterfazSalidaAbstractService {

	@Resource(name="sisicc.sapConnectorService")
	private SapConnectorService sapConnectorService;

	@Resource(name="sisicc.interfazPRYDAO")
    private InterfazPRYDAO interfazPRYDAO;
	
    protected List readData(Map queryParams) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'readData' method ");
        List result = null;
        try {
            String fechaFacturacion = (String) queryParams
                    .get("fechaFacturacion");
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
            queryParams
                    .put("fechaFacturacionDate", sdf.parse(fechaFacturacion));
            log.debug("queryParams=" + queryParams);
            result = this.interfazPRYDAO
                    .getInterfazPRYEnviarInformacionVentaProyeccionParcial(queryParams);
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return result;
    }

    protected void afterProcessInterfaz(InterfazParams interfazParams)
            throws InterfazException {
        Map result = null;
        Map inputParams = new HashMap();
        Map queryParams = interfazParams.getQueryParams();
        String invocarFuncionSAP = MapUtils.getString(queryParams, "invocarFuncionSAP", Constants.NO);
        String funcionSAP = MapUtils.getString(queryParams, "funcionSAP");
        String nombreParametro = MapUtils.getString(queryParams, "nombreParametro");
        String valorParametro = MapUtils.getString(queryParams, "valorParametro");
        
        // Validamos si el parametro que determina si se invoca o no a SAP esta activo
		if (StringUtils.equals(invocarFuncionSAP, Constants.SI)) {
			if (log.isDebugEnabled()) {
				log.debug("Invocando a la funcion SAP: " + funcionSAP);
				log.debug(nombreParametro + "=" + valorParametro);
			}

            if(StringUtils.isNotBlank(nombreParametro)) {
                inputParams.put(nombreParametro, valorParametro);
            }

			try {
				result = sapConnectorService.execute(funcionSAP, inputParams,
						null);
			} catch (Exception e) {
				log.warn(e.getMessage());
				throw new InterfazException(e.getMessage());
			}

			if (log.isDebugEnabled()) {
				log.debug("result SAP=" + result);
			}
		} else {
			log
					.info("La interfaz esta configurada para NO invocar a la funcion SAP");
		}
    }

}