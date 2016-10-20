package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.InterfazDATService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazPaqueteAbstractServiceImpl;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;

@Service("sisicc.paqueteProcesoGENProcesosCierreCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class PaqueteProcesoGENProcesosCierreCampaniaServiceImpl extends BaseInterfazPaqueteAbstractServiceImpl 
{
	
	@Resource(name="sisicc.interfazDATService")
	private InterfazDATService interfazDATService;
	
	@Resource(name="spusicc.procesoGENProcesarCierreService")
	private ProcesoGENProcesarCierreService service;
	
	
	@Override
	protected void beforeExecuteInterfaz(Map params) throws Exception 
	{		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProcessBeforeInterfaz' method");
		}
		
		String indicadorEjecucionSICC = (String)params.get("indicadorEjecucionSICC");
		if(Constants.SI.equals(indicadorEjecucionSICC)) 
		{			
			service.executeProcesarCierrePeriodo(params);
		}
	}
}
