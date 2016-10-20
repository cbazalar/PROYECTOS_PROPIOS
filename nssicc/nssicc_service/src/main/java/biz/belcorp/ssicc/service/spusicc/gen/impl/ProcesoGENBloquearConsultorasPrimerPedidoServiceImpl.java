package biz.belcorp.ssicc.service.spusicc.gen.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ProcesoGENBloquearConsultorasPrimerPedidoDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECAtencionesMasivasDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * 
 * @author Gonzalo Javier Huertas Agurto
 *
 */
@Service("spusicc.procesoGENBloquearConsultorasPrimerPedidoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoGENBloquearConsultorasPrimerPedidoServiceImpl extends BaseInterfazProcesoAbstractService{
	
	@Resource(name = "spusicc.procesoGENBloquearConsultorasPrimerPedidoDAO")
	private ProcesoGENBloquearConsultorasPrimerPedidoDAO procesoGENBloquearConsultorasPrimerPedidoDAO;
	
	@Resource(name = "spusicc.mantenimientoRECAtencionesMasivasDAO")
	private MantenimientoRECAtencionesMasivasDAO mantenimientoRECAtencionesMasivasDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		procesoGENBloquearConsultorasPrimerPedidoDAO.executeProcesoGENBloquearConsultorasPrimerPedido(params);
		
		List codigos = mantenimientoRECAtencionesMasivasDAO.getGTTDetalleIngresoAtencionesMasivasList(params);
		
		if(codigos!=null && codigos.size()>0){
			for(int i=0;i<codigos.size(); i++){
				Map codigo = (Map)codigos.get(i);
				log.error("Error al registrar el bloqueo a la consultora c�digo: "+MapUtils.getString(codigo, "codigoConsultora"));
			}
		}
		
	}

}
