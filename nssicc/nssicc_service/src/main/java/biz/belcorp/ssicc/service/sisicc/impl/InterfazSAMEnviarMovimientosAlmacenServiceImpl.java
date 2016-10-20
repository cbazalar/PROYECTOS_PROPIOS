/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarInscritasServiceImpl
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
import biz.belcorp.ssicc.dao.model.RegistrosStock;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;

/**
 * 
 * <p>
 * <a href="InterfazSAMEnviarMovimientosAlmacenServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 */
@Service("sisicc.interfazSAMEnviarMovimientosAlmacenService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMEnviarMovimientosAlmacenServiceImpl extends	BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
	
	private int numeroRegistros;
	
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
		String indicadorMaterialPromocional=  (String) queryParams.get("indicadorMaterial");
		log.debug("indicadorMaterialPromocional " + indicadorMaterialPromocional);
		//String indicadorEnvioUltimoLote= MapUtils.getString(queryParams, "indicadorEnvioUltimoLote"); SERA USADO EN EL PROCEDIMIENTO
		
		if(indicadorMaterialPromocional.compareToIgnoreCase("1")==0){
			String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivoMaterial");
			log.debug("nombreArchivoMaterial " + nombreArchivo);
			log.debug("interfazParams.getTempName() before " + interfazParams.getTempName());
			//cambiando el valor si se tarta de fijo ya que tendra qser caso al nombre del indicador
			interfazParams.getInterfaz().setTipoNombreArchivo(Constants.ARCHIVO_VARIABLE);
			//interfaz.getTipoNombreArchivo()			
			// Actualizamos tambien el objeto interfaz
				interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
				log.debug("interfazParams.getTempName() after " + interfazParams.getTempName());
				queryParams.put("nombreArchivo", interfazParams.getTempName());
				log.debug("interfazParams.getTempZipFileName() " + interfazParams.getTempZipFileName());				
			}
	
				 
		return queryParams;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'beforeProcessInterfaz' method");
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		String indicadorValidacionStock=(String) queryParams.get("indicadorValidacionStock");
		numeroRegistros=0;
		if(indicadorValidacionStock.compareToIgnoreCase("1")==0){
			String numeroMovimiento= interfazSAMDAO.getNumeroMovimiento(queryParams);
			queryParams.put("numeroMovimiento", numeroMovimiento);
			
			List  registrosStock=interfazSAMDAO.getNumeroRegistrosInterfazStock(queryParams);
			if(registrosStock.size()>0){
				
				numeroRegistros=registrosStock.size();
				for(int i=0;i<registrosStock.size();i++){
					
					RegistrosStock registro= (RegistrosStock)registrosStock.get(i);
					interfazParams.appendLog(registro.getCodigoSap()+";"+registro.getUnidadesAtendidas()+";"+registro.getCandidadStock());
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
			
		if(numeroRegistros==0){
			interfazSAMDAO.executeInterfazSAMEnviarMovimientosAlmacen(params);
			params.put("resultadoInterfaz","0");
		}
		else
			params.put("resultadoInterfaz","1");
			
	}
	
	protected void afterExecuteInterfaz(Map params) throws Exception {
		 super.afterExecuteInterfaz(params);		
		 log.debug("Actualizando indicador PROL");
		 String resultadoInterfaz=params.get("resultadoInterfaz").toString();
		
		// ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
		 Map criteria = new HashMap();

  		 String indicadorPROL = servicePed.getIndicadorActividadPROL(criteria);
		 
	    	if (StringUtils.isNotEmpty(indicadorPROL)){
	    		criteria.put("indicador", Constants.NUMERO_CINCO);
	    		servicePed.executeActualizaIndicadorPROL(criteria);
	    	}			
		
	}	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		Map result = null;
        Map inputParams = new HashMap();
        Map queryParams = interfazParams.getQueryParams();
        String invocarFuncionSAP = MapUtils.getString(queryParams, "invocarFuncionSAP", Constants.SI);
        String funcionSAP = MapUtils.getString(queryParams, "funcionSAP");
        String nombreParametro = MapUtils.getString(queryParams, "nombreParametro");
        String valorParametro = MapUtils.getString(queryParams, "valorParametro");
        String nombreParametro2 = MapUtils.getString(queryParams, "nombreParametro2");
        String valorParametro2 = MapUtils.getString(queryParams, "valorParametro2");
        
        
        // Validamos si el parametro que determina si se invoca o no a SAP esta activo
		if (StringUtils.equals(invocarFuncionSAP, Constants.SI)) {
			if (log.isDebugEnabled()) {
				log.debug("Invocando a la funcion SAP: " + funcionSAP);
				log.debug(nombreParametro + "=" + valorParametro);
				log.debug(nombreParametro2 + "=" + valorParametro2);
			}

            if(StringUtils.isNotBlank(nombreParametro)) {
                inputParams.put(nombreParametro, valorParametro);
            }
                       
            if(StringUtils.isNotBlank(nombreParametro2)) {
                inputParams.put(nombreParametro2, valorParametro2);
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
	
}
