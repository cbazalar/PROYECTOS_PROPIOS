package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEErroresEnCubicajeDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEErroresEnCubicajeService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEErroresEnCubicajeServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEErroresEnCubicajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 public class MantenimientoAPEErroresEnCubicajeServiceImpl extends BaseService implements MantenimientoAPEErroresEnCubicajeService{

	@Resource(name="spusicc.mantenimientoAPEErroresEnCubicajeDAO")
	MantenimientoAPEErroresEnCubicajeDAO mantenimientoAPEErroresEnCubicajeDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEErroresEnCubicajeService#eliminarErrorCubicaje(java.lang.String[], java.util.Map)
	 */
	public void eliminarErrorCubicaje(String[] items, Map criteria) {
		
		for (int i=0; i<items.length;i++){
			String[] cad = StringUtils.split(items[i],"|");
			criteria.put("fechaFactura", cad[0]);
			criteria.put("oidProducto", cad[1]);
			this.mantenimientoAPEErroresEnCubicajeDAO.eliminarErrorCubicaje(criteria);
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEErroresEnCubicajeService#getConsultaErroresCubicajeList(java.util.Map)
	 */
	public List getConsultaErroresCubicajeList(Map criteria) {
		return this.mantenimientoAPEErroresEnCubicajeDAO.getConsultaErroresCubicajeList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEErroresEnCubicajeService#getValidaValoresProducto(java.util.Map)
	 */
	public String getValidaValoresProducto(Map criteria) {
		return this.mantenimientoAPEErroresEnCubicajeDAO.getValidaValoresProducto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEErroresEnCubicajeService#getValidaValorAltoProducto(java.util.Map)
	 */
	public String getValidaValorAltoProducto(Map criteria) {
		return this.mantenimientoAPEErroresEnCubicajeDAO.getValidaValorAltoProducto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEErroresEnCubicajeService#getValidaValorAnchoProducto(java.util.Map)
	 */
	public String getValidaValorAnchoProducto(Map criteria) {
		return this.mantenimientoAPEErroresEnCubicajeDAO.getValidaValorAnchoProducto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEErroresEnCubicajeService#getValidaValorLargoProducto(java.util.Map)
	 */
	public String getValidaValorLargoProducto(Map criteria) {
		return this.mantenimientoAPEErroresEnCubicajeDAO.getValidaValorLargoProducto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEErroresEnCubicajeService#getValidaValorUndMedVolProducto(java.util.Map)
	 */
	public String getValidaValorUndMedVolProducto(Map criteria) {
		return this.mantenimientoAPEErroresEnCubicajeDAO.getValidaValorUndMedVolProducto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEErroresEnCubicajeService#deleteErrorCubicaje(java.lang.String[], java.util.Map)
	 */
	public void deleteErrorCubicaje(String[] items, Map criteria) {
		
		for (int i=0; i<items.length;i++){
			String[] cad = StringUtils.split(items[i],"|");
			criteria.put("fechaFactura", cad[0]);
			criteria.put("oidProducto", cad[1]);
			this.mantenimientoAPEErroresEnCubicajeDAO.deleteErrorCubicaje(criteria);
		}
		
	}
	
 }