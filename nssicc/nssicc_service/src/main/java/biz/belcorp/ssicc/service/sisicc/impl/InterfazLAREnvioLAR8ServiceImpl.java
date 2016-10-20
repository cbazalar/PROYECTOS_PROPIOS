/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazLARDAO;
import biz.belcorp.ssicc.service.sisicc.InterfazLARService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * @author Danny Amaro
 *
 */
@Service("sisicc.interfazLAREnvioLAR8Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazLAREnvioLAR8ServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazLARDAO")
	protected InterfazLARDAO interfazLARDAO;
	
	@Resource(name="sisicc.interfazLARService")
	protected InterfazLARService interfazLARService;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	@Override
	protected void executeStoreProcedure(Map params) {
		interfazLARDAO.executeInterfazLAREnvioLAR8(params);		
	}
	
	protected void  beforeExecuteInterfaz(Map params) throws Exception{
		super.beforeExecuteInterfaz(params);    	
            	
    	List tipoDocList = (List)params.get("tipoDocumento");
    	List zonaList = (List)params.get("zonaList");
    	List regionList = (List)params.get("regionList");
    	 
    	interfazLARService.deleteInterfazLAR8Parametros();
    	
    	if(tipoDocList!=null && tipoDocList.size()>0){
    		for(int i=0; i<tipoDocList.size(); i++){
    			String tipoDocumento = (String)tipoDocList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "TIPODOC");
    			map.put("tipoDocumento", tipoDocumento);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}
    	
    	if(zonaList!=null && zonaList.size()>0){
    		for(int i=0; i<zonaList.size(); i++){
    			String zona = (String)zonaList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "ZONA");
    			map.put("codigoZona", zona);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}
    	
    	if(regionList!=null && regionList.size()>0){
    		for(int i=0; i<regionList.size(); i++){
    			String region = (String)regionList.get(i);
    			Map map = new HashMap();
    			map.put("tipoParam", "REGION");
    			map.put("codigoRegion", region);
    			interfazLARService.insertInterfazLAR8Parametros(map);
    		}    		
    	}

	}
	
	@Override
	protected void afterExecuteInterfaz(Map params) throws Exception {
		super.afterExecuteInterfaz(params);
		interfazLARService.deleteInterfazLAR8Parametros();
	}

}
