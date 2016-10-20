package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCCondonacionDeudasCastigadasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCCondonacionDeudasCastigadasService;

/**
 * 
 * @author
 * 
 */
@Service("spusicc.mantenimientoCCCCondonacionDeudasCastigadasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCCondonacionDeudasCastigadasServiceImpl extends BaseService implements MantenimientoCCCCondonacionDeudasCastigadasService{
	
	@Resource(name="spusicc.mantenimientoCCCCondonacionDeudasCastigadasDAO")
	private MantenimientoCCCCondonacionDeudasCastigadasDAO mantenimientoCCCCondonacionDeudasCastigadasDAO;
	
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCLiquidacionLoteBancarioService#getCondonacionDeudasCastigadasList(java.util.Map)
	 */
	public List  getCondonacionDeudasCastigadasList(Map datos) {
		return mantenimientoCCCCondonacionDeudasCastigadasDAO.getCondonacionDeudasCastigadasList(datos);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCLiquidacionLoteBancarioService#executeCondonacionDeudaCastigo(java.util.Map)
	 */
	public void executeCondonacionDeudaCastigo(Map datos){
		this.mantenimientoCCCCondonacionDeudasCastigadasDAO.executeCondonacionDeudaCastigo(datos);
	}
	
}
