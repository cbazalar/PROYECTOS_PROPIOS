package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEFactoresConversionDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.FactorConversion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEFactoresConversionService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEFactoresConversionServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicolas Lopez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEFactoresConversionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEFactoresConversionServiceImpl extends BaseService implements MantenimientoAPEFactoresConversionService {

	@Resource(name="spusicc.mantenimientoAPEFactoresConversionDAO")
	private MantenimientoAPEFactoresConversionDAO mantenimientoAPEFactoresConversionDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#getFactoresConversionList(java.util.Map)
	 */
	public List getFactoresConversionList(Map criteria) {
		return this.mantenimientoAPEFactoresConversionDAO.getFactoresConversionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#getUnidadMedidaList(java.util.Map)
	 */
	public List getUnidadMedidaList(Map criteria) {
		return this.mantenimientoAPEFactoresConversionDAO.getUnidadMedidaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#getMagnitudList(java.util.Map)
	 */
	public List getMagnitudList(Map criteria) {
		return this.mantenimientoAPEFactoresConversionDAO.getMagnitudList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#getObtenerOidMagnitud(java.util.Map)
	 */
	public String getObtenerOidMagnitud(Map criteria) {
		return this.mantenimientoAPEFactoresConversionDAO.getObtenerOidMagnitud(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#getObtenerOidUnidadMedida(java.util.Map)
	 */
	public String getObtenerOidUnidadMedida(Map criteria) {
		return this.mantenimientoAPEFactoresConversionDAO.getObtenerOidUnidadMedida(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#insertarFactoresConversion(java.util.Map)
	 */
	public void insertarFactoresConversion(Map criteria) {
		this.mantenimientoAPEFactoresConversionDAO.insertarFactoresConversion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#actualizarFactoresConversion(java.util.Map)
	 */
	public void actualizarFactoresConversion(Map criteria) {
		this.mantenimientoAPEFactoresConversionDAO.actualizarFactoresConversion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#getFactorConversionObject(java.util.Map)
	 */
	public FactorConversion getFactorConversionObject(Map criteria) {
		return this.mantenimientoAPEFactoresConversionDAO.getFactorConversionObject(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#eliminarFactorConversion(java.util.Map)
	 */
	public void eliminarFactorConversion(Map criteria, String[] items) {
		
		for (int i=0;i<items.length;i++){
			criteria.put("oidFactConv",items[i]);
			this.mantenimientoAPEFactoresConversionDAO.eliminarFactorConversion(criteria);	
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEFactoresConversionService#validaRepeticionUndOrigenUndDestino(java.util.Map)
	 */
	public String validaRepeticionUndOrigenUndDestino(Map criteria) {
		return this.mantenimientoAPEFactoresConversionDAO.validaRepeticionUndOrigenUndDestino(criteria);
	}

}
