package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ProcesoMAEClasificacionClientesDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBloqueoControlDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el proceso que valida la informacion de los clientes.
 * 
 * @author <a href="mailto:frrodriguez@belcorp.biz">Francesco Rodriguez</a>
 */
@Service("spusicc.procesoGENValidarInformacionClienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENValidarInformacionClienteServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name = "spusicc.procesoMAEClasificacionClientesDAO")
	private ProcesoMAEClasificacionClientesDAO procesoMAEClasificacionClientesDAO;
	
	@Resource(name = "spusicc.mantenimientoSTOBloqueoControlDAO")
	private MantenimientoSTOBloqueoControlDAO mantenimientoSTOBloqueoControlDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
				
		String indValidacionMAE = MapUtils.getString(params, "indicadorValMae", "");
		
		if(StringUtils.isBlank(indValidacionMAE))
		{
			Map criteriaParam = new HashMap();
			criteriaParam.put("codigoPais", MapUtils.getString(params, "codigoPais", ""));
			criteriaParam.put("codigoSistema", "MAE");
			criteriaParam.put("nombreParametro", "indValidacionMAE");
			
			indValidacionMAE = mantenimientoSTOBloqueoControlDAO.getParametroGenericoSistema(criteriaParam);
		}
		
		if(StringUtils.equals(indValidacionMAE, Constants.NRO_UNO))
		{			
			this.procesoMAEClasificacionClientesDAO.executeValidacionClientes(params);
		}
	}

	

}
