package biz.belcorp.ssicc.service.spusicc.pre.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPRETipoOfertaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPRETipoOfertaService;



@Service("spusicc.mantenimientoPRETipoOfertaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPRETipoOfertaServiceImpl extends BaseService implements MantenimientoPRETipoOfertaService {

	@Resource(name="spusicc.mantenimientoPRETipoOfertaDAO")
	MantenimientoPRETipoOfertaDAO mantenimientoPRETipoOfertaDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPRETipoOfertaService#getManPRETipoOfertaList(java.util.Map)
	 */
	public List getManPRETipoOfertaList(Map param) {
		return mantenimientoPRETipoOfertaDAO.getManPRETipoOfertaList(param);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPRETipoOfertaService#insertManPreTiPofertaTotal(java.util.Map)
	 */
	public void insertManPreTiPofertaTotal(Map param) {
		  mantenimientoPRETipoOfertaDAO.insertManPRETipoOferta(param);		  
		  mantenimientoPRETipoOfertaDAO.insertManPRETipoOfertaDetalle(param);		 
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPRETipoOfertaService#updateManPreTiPofertaTotal(java.util.Map)
	 */
	public void updateManPreTiPofertaTotal(Map param) {		
	        mantenimientoPRETipoOfertaDAO.updateManPRETipoOferta(param);
	        mantenimientoPRETipoOfertaDAO.updateManPRETipoDetalle(param);		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPRETipoOfertaService#getManPRETipoOferta(java.util.Map)
	 */
	public Map getManPRETipoOferta(Map param) {
		return mantenimientoPRETipoOfertaDAO.getManPRETipoOferta(param);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPRETipoOfertaService#deleteManPRETipoOferta(java.util.Map)
	 */
	public void deleteManPRETipoOferta(Map param) {		
		mantenimientoPRETipoOfertaDAO.deleteManPRETipoOferta(param);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPRETipoOfertaService#validaManPRETipoOferta(java.lang.String)
	 */
	public int validaManPRETipoOferta(String codigo) {		
		return mantenimientoPRETipoOfertaDAO.validaManPRETipoOferta(codigo);
	}
	
	
	
}
