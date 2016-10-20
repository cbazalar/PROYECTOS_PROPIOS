package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCalculoCalificacionTramoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 *
 */
@Service("spusicc.procesoCOMCalculoCalificacionTramoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOMCalculoCalificacionTramoServiceImpl extends BaseService
	implements ProcesoCOMCalculoCalificacionTramoService {
	
	@Resource(name="spusicc.procesoCOMCalculoCalificacionTramoDAO")
	ProcesoCOMCalculoCalificacionTramoDAO procesoCOMCalculoCalificacionTramoDAO;
	
	public List getTiposComisionistas(String codigoPais) {
		return this.procesoCOMCalculoCalificacionTramoDAO.getTiposComisionistas(codigoPais);
	}
	
	public List getTramos(String codigoPais) {
		return this.procesoCOMCalculoCalificacionTramoDAO.getTramos(codigoPais);
	}
	
	public void executeCalculoCalificacionTramo(Map criteria) {
		this.procesoCOMCalculoCalificacionTramoDAO.executeCalculoCalificacionTramo(criteria);
	}
	
	/**
	 * @param procesoCOMCalculoCalificacionTramoDAO The procesoCOMCalculoCalificacionTramoDAO to set.
	 */
	public void setProcesoCOMCalculoCalificacionTramoDAO(
			ProcesoCOMCalculoCalificacionTramoDAO procesoCOMCalculoCalificacionTramoDAO) {
		this.procesoCOMCalculoCalificacionTramoDAO = procesoCOMCalculoCalificacionTramoDAO;
	}
	
	public List getTipoCalculoList(String codigoPais) {
		return this.procesoCOMCalculoCalificacionTramoDAO.getTipoCalculoList(codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCalculoCalificacionTramoService#getCampanasRango(java.util.Map)
	 */
	public List getCampanasRango(Map criteria){
		return this.procesoCOMCalculoCalificacionTramoDAO.getCampanasRango(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.ProcesoCOMCalculoCalificacionTramoService#getComisionByTipo(java.util.Map)
	 */
	public List getComisionByTipo(Map criteria) {
		return procesoCOMCalculoCalificacionTramoDAO.getComisionByTipo(criteria);
	}
	
}
