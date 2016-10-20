package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;

@Service("sisicc.paqueteInterfazOCRProcesarConsolidadoOCSService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteInterfazOCRProcesarConsolidadoOCSServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl{

	@Resource(name="spusicc.procesoSTOExecutionService")
	private ProcesoSTOExecutionService procesoSTOExecutionService;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazAbstractService#afterExecuteInterfaz(java.util.Map)
	 */
	@Override
	protected void afterExecuteInterfaz(Map params) throws Exception {
		super.afterExecuteInterfaz(params);
		
		//if (interfazExecutionResult.isEjecutarSTO()){
		if(interfazExecutionResult.ejecucionCompletada()){
	        String codigoPais = (String)params.get("codigoPais");
	        String	indValidacionSTO = (String)params.get("indValidacionSTO");
	         if (indValidacionSTO!=null && indValidacionSTO.equals(Constants.SI)){
		        AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_AUTO);    			
		        procesoSTOExecutionService.execute(accionTipoDocumento,params, null);
	         }
	   }
	}
    
    
    
}
