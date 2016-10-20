/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

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
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSABEnviarIncentivosConsultorasServiceImpl.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazSABEnviarIncentivosConsultorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSABEnviarIncentivosConsultorasServiceImpl extends
        BaseInterfazSalidaAbstractService {

	@Resource(name="sisicc.sapConnectorService")
    private SapConnectorService sapConnectorService;

   
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
     */
    protected List readData(Map queryParams) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'readData' method");
        List list = null;
        try {
            list = interfazSiCCDAO
                    .getInterfazSABEnviarIncentivosConsultoras(queryParams);
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return list;
    }

    protected void afterProcessInterfaz(InterfazParams interfazParams)
            throws InterfazException {
        Map result = null;
        Map inputParams = new HashMap();
        Map queryParams = interfazParams.getQueryParams();
        
        // Obtenemos el parametro que determina si se invoca a la funcion SAP
        String invocarFuncionSAP = MapUtils.getString(queryParams, "invocarFuncionSAP", Constants.NO);
        
        // Obtenemos los parametros a pasar a la funcion SAP
        String codigoSociedad = MapUtils.getString(queryParams, "codigoSociedad");
        String codigoInterfaz = MapUtils.getString(queryParams, "codigoInterfaz");
        String codigoCanal = MapUtils.getString(queryParams, "codigoCanal");
        String codigoVentaDirectaSiCC = MapUtils.getString(queryParams, "codigoVentaDirectaSiCC");
        String codigoVentaDirectaSAP = MapUtils.getString(queryParams, "codigoVentaDirectaSAP");
        String codigoPeriodo = MapUtils.getString(queryParams, "codigoPeriodo");
        String nombreParametro = MapUtils.getString(queryParams, "nombreParametro");
        
        // Hacemos la homologacion entre SiCC y SAP
        if(StringUtils.equals(codigoCanal, codigoVentaDirectaSiCC)) {
            codigoCanal = codigoVentaDirectaSAP;
        }
        
        // Creamos el valor del parametro
        String parametro = codigoSociedad + codigoCanal + codigoPeriodo + codigoInterfaz;
        
        // Si el parametro tiene valor invocamos a SAP
        String funcionSAP = MapUtils.getString(queryParams, "funcionSAP");

        if(StringUtils.equals(invocarFuncionSAP, Constants.SI)) {
            if (log.isDebugEnabled()) {
                log.debug("Invocando a la funcion SAP: " + funcionSAP);
            }

            try {
                inputParams.put(nombreParametro, parametro);
                result = sapConnectorService.execute(funcionSAP, inputParams, null);
            } catch (Exception e) {
                log.warn(e.getMessage());
                throw new InterfazException(e.getMessage());
            }

            if (log.isDebugEnabled()) {
                log.debug("result SAP=" + result);
            }
        }
        else {
            log.info("La interfaz esta configurada para NO invocar la funcion SAP.");
        }
    }

}
