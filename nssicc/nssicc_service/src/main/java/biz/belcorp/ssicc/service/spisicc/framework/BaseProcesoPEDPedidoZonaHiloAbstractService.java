/*
 * Created on 19/01/2007 04:35:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionLaserServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.framework;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoPedidoZona;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService;
import biz.belcorp.ssicc.service.spisicc.ProcesoHiloPedidoZonaService;

/**
 * <p>
 * <a href="ProcesoImpresionLaserServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Gonzalo Javier Huertas Agurto</a>
 */
@Service("spisicc.baseProcesoPEDPedidoZonaHiloAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseProcesoPEDPedidoZonaHiloAbstractService extends BaseInterfazAbstractService {
	
	@Resource(name="spisicc.ProcesoHiloPedidoZonaService")
	protected ProcesoHiloPedidoZonaService procesoHiloPedidoZonaService;
	
	/**
	 * Template Method que define el algoritmo basico para los Procesos de
	 * SiCC: Ejecuta el stored del Proceso.
	 * 
	 * @param parametros
	 *            de la Interfaz
	 * @return numero de registros procesados, que sera 0 por ser un Proceso
	 */
	protected int processInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'processInterfaz' method");
		int registrosProcesados = 0;

		try {
			//actualizamos para que no genere LOG
			Interfaz interfaz = interfazParams.getInterfaz();
			interfaz.setFlagLogErrores(Constants.NO);
			executeStoreProcedure(interfazParams.getQueryParams());
		}   catch (Exception e) {
			log.error("Error al procesar la interfaz: " + e.getMessage());
			InterfazException interfazException = new InterfazException(e
					.getMessage());
			interfazException.setRegistrosProcesados(registrosProcesados);
			throw interfazException;
		}
		return registrosProcesados;
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params)  throws Exception {
		
		
		List listaZonas = (List)params.get("listaZonas");
		String codigoPais = (String)params.get("codigoPais");
        String codigoPeriodo = (String)params.get("codigoPeriodo");
        String fechaFacturacion = (String)params.get("fechaFacturacion");
        String codigoUsuario = (String)params.get("codigoUsuario");
		
        String parametro = genericoDAO.getParametroPais(codigoPais,Constants.SISTEMA_PED,Constants.PED_PARAM_NTHREADS);
        
        int nThreads=Constants.INT_UNO;
        
        if (parametro!=null) {
        	nThreads = Integer.parseInt(parametro);
        }
        
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		List<Future<ProcesoPedidoZona>> list = new ArrayList<Future<ProcesoPedidoZona>>();
		for (int i = 0; i < listaZonas.size(); i++) {
			ProcesoPedidoZona proceso = new ProcesoPedidoZona(codigoPais,codigoPeriodo, MapUtils.getString((HashMap)listaZonas.get(i), "oidZona"),fechaFacturacion, codigoUsuario);
			Callable<ProcesoPedidoZona> worker = new ProcesoPEDPedidoZonaHilo(this, proceso);
			Future<ProcesoPedidoZona> submit = executor.submit(worker);
			list.add(submit);
		}
		
		String mensajeError="";
	    boolean isSucces = true;
	    
		for (Future<ProcesoPedidoZona> future : list) {
			
			ProcesoPedidoZona  proceso = (ProcesoPedidoZona)future.get();
	        if (!proceso.isSuccess()){
	        	isSucces=false;
	        	mensajeError+="Error oidZona  " + proceso.getOidZona() + proceso.getMensajeError();
	        	log.error(mensajeError);
	        }
			
		}
		
		executor.shutdown();
		
		if (!isSucces) throw new Exception(mensajeError);
		
        
    }

    /**
     * @param params
     */
    public abstract void executeHilo(ProcesoPedidoZona proceso);


}
