package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPECopiarAsignacionProductosDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.CopiarAsignarProductos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPECopiarAsignacionProductosService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPECopiarAsignacionProductosServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPECopiarAsignacionProductosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPECopiarAsignacionProductosServiceImpl extends BaseService implements MantenimientoAPECopiarAsignacionProductosService{
	
	@Resource(name="spusicc.mantenimientoAPECopiarAsignacionProductosDAO")
	private MantenimientoAPECopiarAsignacionProductosDAO mantenimientoAPECopiarAsignacionProductosDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarAsignacionProductosService#getCopiarAsignacionProductosList(java.util.Map)
	 */
	public List getCopiarAsignacionProductosList(Map criteria) {
		return this.mantenimientoAPECopiarAsignacionProductosDAO.getCopiarAsignacionProductosList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarAsignacionProductosService#getValidaVersionByCDyPeriodo(java.util.Map)
	 */
	public String getValidaVersionByCDyPeriodo(Map criteria) {
		return this.mantenimientoAPECopiarAsignacionProductosDAO.getValidaVersionByCDyPeriodo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarAsignacionProductosService#generaCopiaAsignacionProductosAnaqueles(java.util.Map)
	 */
	public void generaCopiaAsignacionProductosAnaqueles(Map criteria) {
		this.mantenimientoAPECopiarAsignacionProductosDAO.generaCopiaAsignacionProductosAnaqueles(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarAsignacionProductosService#getCopiarAsigProdAnaquelObject(java.util.Map)
	 */
	public CopiarAsignarProductos getCopiarAsigProdAnaquelObject(Map criteria) {
		return this.mantenimientoAPECopiarAsignacionProductosDAO.getCopiarAsigProdAnaquelObject(criteria);
	}
	
}