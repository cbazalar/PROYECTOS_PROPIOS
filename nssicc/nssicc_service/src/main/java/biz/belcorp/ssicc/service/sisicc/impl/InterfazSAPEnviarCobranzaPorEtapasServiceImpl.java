package biz.belcorp.ssicc.service.sisicc.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

@Service("sisicc.interfazSAPEnviarCobranzaPorEtapasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAPEnviarCobranzaPorEtapasServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazSAFDAO")
	protected InterfazSAFDAO interfazSAFDAO;
	
	@Resource(name="genericoService")
	protected GenericoService genericoService;
	
	@Resource(name="sisicc.sapSafFiConnectorService")
	private SapConnectorService sapConnectorService;

	@Override
	protected void executeStoreProcedure(Map params) {		
		interfazSAFDAO.executeInterfazSAPEnviarCobranzaxEtapas(params);		
		
	}
	
	public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'prepareQueryParams'");
        }
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String fecha=sdf.format(new Date(System.currentTimeMillis()));
	    String sociedad = MapUtils.getString(queryParams, "codigoSociedad");
	    String interfaz = MapUtils.getString(queryParams, "codigoInterfaz");
	    String nombreArchivo=interfaz+"_"+sociedad;
		queryParams.put("nombreArchivo", nombreArchivo);	
		interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
		return queryParams;
	}
	
	protected void beforeProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'beforeProcessInterfaz' method");
	
		Map queryParams = super.prepareQueryParams(interfazParams);	
		
	}
	
		
	@Override
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		Map result = null;
        Map inputParams = new HashMap();
        Map queryParams = interfazParams.getQueryParams();
        String invocarFuncionSAP = MapUtils.getString(queryParams, "invocarFuncionSAP", Constants.SI);
        String funcionSAP = MapUtils.getString(queryParams, "funcionSAP");
        String nombreParametro = MapUtils.getString(queryParams, "nombreParametro");
        String valorParametro = MapUtils.getString(queryParams, "valorParametro");       
        String ejecucionAsincrono = MapUtils.getString(queryParams, "ejecucionAsincronoSAP");
        String sociedad = MapUtils.getString(queryParams, "codigoSociedad");
      
        //Obtener Nombre
       String codPeriodo= interfazSAFDAO.getCampoPeriodoActualSAP();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
       String fecha=sdf.format(new Date(System.currentTimeMillis()));
       valorParametro=sociedad+"01"+codPeriodo+fecha;	
       
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
				result = sapConnectorService.execute(funcionSAP, inputParams,null);				
			} catch (Exception e) {
				log.warn(e.getMessage());
				throw new InterfazException(e.getMessage());
			}

			if (log.isDebugEnabled()) {
				log.debug("result SAP=" + result);
			}
           
		} else {
			log.info("La interfaz esta configurada para NO invocar a la funcion SAP");
		}
	}
	
	protected String adicionarKeyTituloCabecera() {
		return "interfazSAPEnviarCobranzaPorEtapasFormTituloCabecera";
}

	
	

}
