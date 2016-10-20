package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConsultarFacturasCajasDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.ConsultaFacturasCajas;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEConsultarFacturasCajasService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEConsultarFacturasCajasServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEConsultarFacturasCajasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 public class MantenimientoAPEConsultarFacturasCajasServiceImpl extends BaseService implements MantenimientoAPEConsultarFacturasCajasService{

	@Resource(name="spusicc.mantenimientoAPEConsultarFacturasCajasDAO")
 	private MantenimientoAPEConsultarFacturasCajasDAO mantenimientoAPEConsultarFacturasCajasDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConsultarFacturasCajasService#getConsultaFacturasCajasList(java.util.Map)
	 */
	public List getConsultaFacturasCajasList(Map criteria) {
		return this.mantenimientoAPEConsultarFacturasCajasDAO.getConsultaFacturasCajasList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConsultarFacturasCajasService#getProductosFacturasCajasObject(java.util.Map)
	 */
	public ConsultaFacturasCajas getProductosFacturasCajasObject(Map criteria) {
		return this.mantenimientoAPEConsultarFacturasCajasDAO.getProductosFacturasCajasObject(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConsultarFacturasCajasService#getProductosFacturasCajasList(java.util.Map)
	 */
	public List getProductosFacturasCajasList(Map criteria) {
		return this.mantenimientoAPEConsultarFacturasCajasDAO.getProductosFacturasCajasList(criteria);
	}

 }