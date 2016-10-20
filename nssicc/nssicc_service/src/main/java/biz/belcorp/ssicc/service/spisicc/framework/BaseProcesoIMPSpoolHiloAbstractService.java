/*
 * Created on 19/01/2007 04:35:40 PM
 * biz.belcorp.ssicc.spisicc.service.impl.ProcesoImpresionLaserServiceImpl
 */
package biz.belcorp.ssicc.service.spisicc.framework;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.spisicc.model.ProcesoSpool;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService;
import biz.belcorp.ssicc.service.spisicc.ProcesoHiloImpresionService;

/**
 * <p>
 * <a href="ProcesoImpresionLaserServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose A. Cairampoma Granados</a>
 */
@Service("spisicc.baseProcesoIMPSpoolHiloAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseProcesoIMPSpoolHiloAbstractService extends BaseInterfazAbstractService {
	
	@Resource(name="spisicc.ProcesoHiloImpresionService")
	protected ProcesoHiloImpresionService procesoHiloImpresionService;
	
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
		
		
		List listaZonas = procesoHiloImpresionService.getListaZonasActivasSpool();
		String codigoPais = (String)params.get("codigoPais");
        String codigoPeriodo = (String)params.get("codigoPeriodo");
        String fechaFacturacion = (String)params.get("fechaFacturacion");
		
        String parametro = genericoDAO.getParametroPais(codigoPais,Constants.SISTEMA_IMP,Constants.IMP_PARAM_NTHREADS);
        
        int nThreads=Constants.INT_UNO;
        
        if (parametro!=null) {
        	nThreads = Integer.parseInt(parametro);
        }
        
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		List<Future<ProcesoSpool>> list = new ArrayList<Future<ProcesoSpool>>();
		for (int i = 0; i < listaZonas.size(); i++) {
			ProcesoSpool proceso = new ProcesoSpool(codigoPais,codigoPeriodo,(String)listaZonas.get(i),fechaFacturacion);
			Callable<ProcesoSpool> worker = new ProcesoIMPSpoolHilo(this, proceso);
			Future<ProcesoSpool> submit = executor.submit(worker);
			list.add(submit);
		}
		
		String mensajeError="";
	    boolean isSucces = true;
	    
		for (Future<ProcesoSpool> future : list) {
			
			ProcesoSpool  proceso = (ProcesoSpool)future.get();
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
    public abstract void executeHilo(ProcesoSpool proceso);


}
