package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEMantenerMapaCDDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEMantenerMapaCDService;


/**
 *  
 * <p>
 * <a href="MantenimientoAPEMantenerMapaCDServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEMantenerMapaCDService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEMantenerMapaCDServiceImpl extends BaseService implements MantenimientoAPEMantenerMapaCDService {

	@Resource(name="spusicc.mantenimientoAPEMantenerMapaCDDAO")
	private MantenimientoAPEMantenerMapaCDDAO mantenimientoAPEMantenerMapaCDDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getAnaquelDestinoListar(java.util.Map)
	 */
	public List getAnaquelDestinoListar(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getAnaquelDestinoListar(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getAnaquelOrigenListar(java.util.Map)
	 */
	public List getAnaquelOrigenListar(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getAnaquelOrigenListar(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getObtenerOidSubLineaxOidMapaCDDet(java.util.Map)
	 */
	public String getObtenerOidSubLineaxOidMapaCDDet(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getObtenerOidSubLineaxOidMapaCDDet(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getValidaNoExisteAsignaProductoAAnaquelDet(java.util.Map)
	 */
	public String getValidaNoExisteAsignaProductoAAnaquelDet(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getValidaNoExisteAsignaProductoAAnaquelDet(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getOidCanalxCod(java.util.Map)
	 */
	public String getOidCanalxCod(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getOidCanalxCod(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getOidMarcaxCod(java.util.Map)
	 */
	public String getOidMarcaxCod(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getOidMarcaxCod(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getOidPeriodo(java.util.Map)
	 */
	public String getOidPeriodo(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getOidPeriodo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#updateAnaquelOrigenMapaCDDetalle(java.util.Map)
	 */
	public void updateAnaquelOrigenMapaCDDetalle(Map criteria) {
		this.mantenimientoAPEMantenerMapaCDDAO.updateAnaquelOrigenMapaCDDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#updateCapacidadMapCDDetalle(java.util.Map)
	 */
	public void updateCapacidadMapCDDetalle(Map criteria) {
		this.mantenimientoAPEMantenerMapaCDDAO.updateCapacidadMapCDDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getCodAnaquelOrigenListar(java.util.Map)
	 */
	public List getCodAnaquelOrigenListar(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getCodAnaquelOrigenListar(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#updateAnaquelOrigenOpcionDividir(java.util.Map)
	 */
	public void updateAnaquelOrigenOpcionDividir(Map criteria) {
		this.mantenimientoAPEMantenerMapaCDDAO.updateAnaquelOrigenOpcionDividir(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getNumeroAnaquel(java.util.Map)
	 */
	public String getNumeroAnaquel(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getNumeroAnaquel(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMantenerMapaCDService#getCodigoAnaquelDividirList(java.util.Map)
	 */
	public List getCodigoAnaquelDividirList(Map criteria) {
		return this.mantenimientoAPEMantenerMapaCDDAO.getCodigoAnaquelDividirList(criteria);
	}

}
