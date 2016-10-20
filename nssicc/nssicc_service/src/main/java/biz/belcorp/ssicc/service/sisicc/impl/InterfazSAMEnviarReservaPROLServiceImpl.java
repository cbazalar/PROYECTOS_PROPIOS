/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarInscritasServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;


/**
 * 
 * <p>
 * <a href="InterfazSAMEnviarReservaPROLServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazSAMEnviarReservaPROLService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMEnviarReservaPROLServiceImpl extends	BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
	
	@Resource(name="sisicc.sapConnectorService")
	private SapConnectorService sapConnectorService;
	
	@Resource(name="spusicc.procesoPEDService")
	private ProcesoPEDService servicePed;

	

	/**
	 * Devuelve el Map de parametros del query. Sobreescrito con la finalidad de
	 * obtener los 'Parametros de Interaz' configurados en el mantenimiento de
	 * la interfaz
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Map con parametros del query
	 */
	public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'prepareQueryParams'");
        }
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String numeroLote = (String)interfazParams.getQueryParams().get("numeroLote");

		String anho = numeroLote.substring(0, 4);
		String mes = numeroLote.substring(4, 6);
		int dia = timestamp.getDate();
		int hora = timestamp.getHours();
		int minuto = timestamp.getMinutes();
		String d = String.valueOf(dia);
		String hor = String.valueOf(hora);
		String min = String.valueOf(minuto);

		if ( d.length() == 1){
			d = "0" + d;
		}

		if ( hor.length() == 1){
			hor = "0" + hor;
		}

		if ( min.length() == 1){
			min = "0" + min;
		}

        String fecha = d + mes + anho + hor + min;
		
        String codigoPais = (String)interfazParams.getQueryParams().get("codigoPais");
		String nombreArchivo = codigoPais + "_" + "RES" + "_" + fecha;
		 
		queryParams.put("nombreArchivo", nombreArchivo);

		interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);

		return queryParams;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSAMDAO.executeInterfazSAMEnviarReservaPROL(params);
	}
	
	protected void afterExecuteInterfaz(Map params) throws Exception{
		super.afterExecuteInterfaz(params);
	
		Map criteria = new HashMap();

		String codigoPais = (String)params.get("codigoPais");
		String codigoPeriodo = (String)params.get("codigoPeriodo");

		criteria.put("codigoPais", codigoPais);
    	criteria.put("codigoPeriodo", codigoPeriodo);
    	String indicadorPROL = servicePed.getIndicadorActividadPROL(criteria);
    	if (indicadorPROL.equals(Constants.NUMERO_DOS))
		criteria.put("indicador", Constants.NUMERO_TRES);
    	if (indicadorPROL.equals(Constants.NUMERO_CINCO))
    		criteria.put("indicador", Constants.NUMERO_CERO);
		servicePed.executeActualizaIndicadorPROL(criteria);

	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
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
					result = sapConnectorService.execute(funcionSAP, inputParams, null);
				} catch (Exception e) {
					log.error("Error al invocar a SAP" + e);
					throw new InterfazException("Error al invocar a SAP"+e);
				}

				if (log.isDebugEnabled()) {
					log.debug("result SAP=" + result);
				}
			} else {
				log.info("La interfaz esta configurada para NO invocar a la funcion SAP");
			}
	    }
}