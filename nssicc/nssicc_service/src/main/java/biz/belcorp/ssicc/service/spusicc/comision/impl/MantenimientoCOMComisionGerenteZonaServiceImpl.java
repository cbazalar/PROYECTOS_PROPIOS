/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMComisionGerenteZonaDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ComisionPeriodoGerenteZona;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroTramoComision;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionGerenteZonaService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 *
 */
@Service("spusicc.mantenimientoCOMComisionGerenteZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMComisionGerenteZonaServiceImpl extends BaseService 
    implements MantenimientoCOMComisionGerenteZonaService {
	
	@Resource(name="spusicc.mantenimientoCOMComisionGerenteZonaDAO")
	MantenimientoCOMComisionGerenteZonaDAO mantenimientoCOMComisionGerenteZonaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionPeriodoGerenteZona(biz.belcorp.ssicc.spusicc.comision.dao.model.ComisionPeriodoGerenteZona)
	 */
	public ComisionPeriodoGerenteZona getComisionPeriodoGerenteZona(ComisionPeriodoGerenteZona bean) {
		return this.mantenimientoCOMComisionGerenteZonaDAO.getComisionPeriodoGerenteZona(bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionPeriodoGerenteZonaByCriteria(java.util.Map)
	 */
	public List getComisionPeriodoGerenteZonaByCriteria(Map criteria) {
		return this.mantenimientoCOMComisionGerenteZonaDAO.getComisionPeriodoGerenteZonaByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionPeriodoLideresByCriteria(java.util.Map)
	 */
	public List getComisionPeriodoLideresByCriteria(Map criteria) {
		return this.mantenimientoCOMComisionGerenteZonaDAO.getComisionPeriodoLideresByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getDevuelveIDComision(java.util.Map)
	 */
	public Integer getDevuelveIDComision(Map criteria) {
		return this.mantenimientoCOMComisionGerenteZonaDAO.getDevuelveIDComision(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getIndicadoresComision(java.util.Map)
	 */
	public Map getIndicadoresComision(Map criteria) {
		return this.mantenimientoCOMComisionGerenteZonaDAO.getIndicadoresComision(criteria);
	}
	
	/**
	 * @return Returns the mantenimientoCOMComisionGerenteZonaDAO.
	 */
	public MantenimientoCOMComisionGerenteZonaDAO getMantenimientoCOMComisionGerenteZonaDAO() {
		return this.mantenimientoCOMComisionGerenteZonaDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getParametrosTramoComision(java.util.Map)
	 */
	public ParametroTramoComision getParametrosTramoComision(Map criteria) {
		return this.mantenimientoCOMComisionGerenteZonaDAO.getParametrosTramoComision(criteria);
	}
	
	
	/**
	 * @param mantenimientoCOMComisionGerenteZonaDAO The mantenimientoCOMComisionGerenteZonaDAO to set.
	 */
	public void setMantenimientoCOMComisionGerenteZonaDAO(
			MantenimientoCOMComisionGerenteZonaDAO mantenimientoCOMComisionGerenteZonaDAO) {
		this.mantenimientoCOMComisionGerenteZonaDAO = mantenimientoCOMComisionGerenteZonaDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#updateComisionPeriodoGerenteZona(biz.belcorp.ssicc.spusicc.comision.dao.model.ComisionPeriodoGerenteZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateComisionPeriodoGerenteZona(ComisionPeriodoGerenteZona bean, Usuario usuario) {
		this.mantenimientoCOMComisionGerenteZonaDAO.updateComisionPeriodoGerenteZona(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionPeriodoGerenteZonaByCriteria(java.util.Map)
	 */
	public List getComisionPeriodoGerenteZonaEscalonadaByCriteria(Map criteria) {
		return this.mantenimientoCOMComisionGerenteZonaDAO.getComisionPeriodoGerenteZonaEscalonadaByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionPeriodoGerenteRegion(java.util.Map)
	 */
	public List getComisionPeriodoGerenteRegion(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoCOMComisionGerenteZonaDAO.getComisionPeriodoGerenteRegion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionVal()
	 */
	public List getComisionVal(Map criteria) {
		return mantenimientoCOMComisionGerenteZonaDAO.getComisionVal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionRegionList(java.util.Map)
	 */
	public List getComisionRegionList(Map criteria) {
		return mantenimientoCOMComisionGerenteZonaDAO.getComisionRegionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComGerenteZona(java.util.Map)
	 */
	public List getComGerenteZonaList(Map criteria) {
		return mantenimientoCOMComisionGerenteZonaDAO.getComGerenteZonaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComArchivoNominaList(java.util.Map)
	 */
	public List getComArchivoNominaList(Map criteria) {
		return mantenimientoCOMComisionGerenteZonaDAO.getComArchivoNominaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionGerenteRegionObjetivo(java.util.Map)
	 */	
	public List getComisionGerenteRegionObjetivo(Map criteria) {
		return mantenimientoCOMComisionGerenteZonaDAO.getComisionGerenteRegionObjetivo(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionRetail(java.util.Map)
	 */
	public List getComisionRetail(Map criteria) {
		return mantenimientoCOMComisionGerenteZonaDAO.getComisionRetail(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionBase(java.util.Map)
	 */
	public List getComisionBase(Map criteria) {
		return mantenimientoCOMComisionGerenteZonaDAO.getComisionBase(criteria);
	}
	
}
