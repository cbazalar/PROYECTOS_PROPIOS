package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConsultoraChequeoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConsultoraChequeoService;

/**
 * @author Jos Luis Rodrguez
 */
@Service("spusicc.pedidos.mantenimientoPEDConsultoraChequeoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDConsultoraChequeoServiceImpl extends BaseService implements MantenimientoPEDConsultoraChequeoService{
	
	@Resource(name="spusicc.mantenimientoPEDConsultoraChequeoDAO")
	private MantenimientoPEDConsultoraChequeoDAO mantenimientoPEDConsultoraChequeoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConsultoraChequeoService#getTipoChequeoPais(java.util.Map)
	 */
	public List getTipoChequeoPais(Map criteria) {
		return mantenimientoPEDConsultoraChequeoDAO.getTipoChequeoPais(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConsultoraChequeoService#insertConsultoraChequear(java.util.Map)
	 */
	public void insertConsultoraChequear(Map criteria){
		mantenimientoPEDConsultoraChequeoDAO.insertConsultoraChequear(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConsultoraChequeoService#deleteConsultoraChequear(java.util.Map)
	 */
	public void deleteConsultoraChequear(Map criteria){
		mantenimientoPEDConsultoraChequeoDAO.deleteConsultoraChequear(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConsultoraChequeoService#getConsultoraChequear(java.util.Map)
	 */
	public List getConsultoraChequear(Map criteria){
		return mantenimientoPEDConsultoraChequeoDAO.getConsultoraChequear(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConsultoraChequeoService#getExisteConsultoraChequear(java.util.Map)
	 */
	public int getExisteConsultoraChequear(Map criteria) {
		return mantenimientoPEDConsultoraChequeoDAO.getExisteConsultoraChequear(criteria);
	}
	
	
}