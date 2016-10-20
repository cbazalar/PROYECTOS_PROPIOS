package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.edu.ProcesoEDUCierreProcesosCampannaService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

@Service("spusicc.procesoGENCerrarProcesosCampaniaEDUService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENCerrarProcesosCampaniaEDUServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name = "edu.procesoEDUCierreProcesosCampannaService")
	private ProcesoEDUCierreProcesosCampannaService procesoEDUCierreProcesosCampannaService;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws Exception{
		
		String codigoPais = (String)params.get("codigoPais");
		
		procesoEDUCierreProcesosCampannaService.executeCierreProcesosCampanna(codigoPais, params);
		
	}
}