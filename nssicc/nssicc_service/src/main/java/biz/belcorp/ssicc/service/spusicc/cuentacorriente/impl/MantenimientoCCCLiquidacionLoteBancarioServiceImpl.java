package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCLiquidacionLoteBancarioDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCLiquidacionLoteBancarioService;

/**
 * Service que controla el Mantenimiento de la Liquidacion de Lote Bancario
 *  
 * <p>
 * <a href="MantenimientoCCCLiquidacionLoteBancarioServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"></a>
 * 
 */
@Service("spusicc.mantenimientoCCCLiquidacionLoteBancarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCLiquidacionLoteBancarioServiceImpl extends BaseService implements MantenimientoCCCLiquidacionLoteBancarioService {

	@Resource(name = "spusicc.mantenimientoCCCLiquidacionLoteBancarioDAO")
	private MantenimientoCCCLiquidacionLoteBancarioDAO mantenimientoCCCLiquidacionLoteBancarioDAO;
		
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCLiquidacionLoteBancarioService#getLotesBancariosList(java.util.Map)
	 */
	public List  getLotesBancariosList(Map datos) {
		return mantenimientoCCCLiquidacionLoteBancarioDAO.getLotesBancariosList(datos);
	}
					
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCLiquidacionLoteBancarioService#executeLiquidarLoteBancario(java.util.Map)
	 */
	public void executeLiquidarLoteBancario(Map datos){
		this.mantenimientoCCCLiquidacionLoteBancarioDAO.executeLiquidarLoteBancario(datos);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCLiquidacionLoteBancarioService#executeEliminarLoteBancario(java.lang.String)
	 */
	public void executeEliminarLoteBancario(Map datos){
		this.mantenimientoCCCLiquidacionLoteBancarioDAO.executeEliminarLoteBancario(datos);
	}
}
