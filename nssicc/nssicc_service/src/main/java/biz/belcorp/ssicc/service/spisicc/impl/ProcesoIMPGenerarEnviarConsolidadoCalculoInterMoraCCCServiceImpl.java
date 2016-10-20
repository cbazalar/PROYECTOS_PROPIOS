package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spisicc.ReporteIMPGenerarEnviarConsolidadoCalculoInterMoraCCCService;

/**
 * Service para el proceso enviar consolidado de calculo inter mora
 * 
 * @author <a href="mailto:dtorres@sigcomt.com">Diego Torres Loyola</a>
 */
@Service("spisicc.procesoIMPGenerarEnviarConsolidadoCalculoInterMoraCCCService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoIMPGenerarEnviarConsolidadoCalculoInterMoraCCCServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spisicc.procesoImpresionDAO")
	private ProcesoImpresionDAO procesoImpresionDAO;
	
	@Resource(name="spisicc.reporteIMPGenerarEnviarConsolidadoCalculoInterMoraCCCService")
	private ReporteIMPGenerarEnviarConsolidadoCalculoInterMoraCCCService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService
	 * #executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {

		log.debug("inicio ProcesoIMPGenerarEnviarConsolidadoCalculoInterMoraCCCServiceImpl"
				+ params);

		procesoImpresionDAO.executeCalculoInterMora();
		params.put("usuarioTemp", MapUtils.getObject(params, "usuario"));	
		
		try {
		service.grabarReporte(params);
		} catch (Exception e) {
			e.printStackTrace(); 
		}


	}
	
}
