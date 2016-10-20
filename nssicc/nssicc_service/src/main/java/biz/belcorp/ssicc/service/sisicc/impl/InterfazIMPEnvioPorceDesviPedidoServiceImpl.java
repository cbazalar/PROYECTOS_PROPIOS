package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.ReporteIMPPorceDesviPedidoService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;


/**
 * Service para el proceso que Genera Reporte de Porcentaje de Desviacin de Pedidos
 * 
 * @author <a href="mailto:yrivas@sigcomt.com">Carlos Bazalar</a>
 */
@Service("sisicc.interfazIMPEnvioPorceDesviPedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPEnvioPorceDesviPedidoServiceImpl extends
	BaseInterfazProcesoAbstractService {

	
	@Resource(name="sisicc.reporteIMPPorceDesviPedidoService")
	ReporteIMPPorceDesviPedidoService reporteIMPPorceDesviPedidoService;
	 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		 log.debug("inicio InterfazIMPEnvioPorceDesviPedidoServiceImpl " + params);
		  Usuario usuario = (Usuario)params.get("usuario");
		  params.put("usuarioTemp", usuario);		
		 try {
					reporteIMPPorceDesviPedidoService.grabarReporte(params);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 
	}


	
	
}