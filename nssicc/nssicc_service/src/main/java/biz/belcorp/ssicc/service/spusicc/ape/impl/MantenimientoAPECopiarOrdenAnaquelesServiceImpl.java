package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPECopiarOrdenAnaquelesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPECopiarOrdenAnaquelesService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPECopiarOrdenAnaquelesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPECopiarOrdenAnaquelesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPECopiarOrdenAnaquelesServiceImpl extends BaseService implements MantenimientoAPECopiarOrdenAnaquelesService {

	@Resource(name="spusicc.mantenimientoAPECopiarOrdenAnaquelesDAO")
	private MantenimientoAPECopiarOrdenAnaquelesDAO mantenimientoAPECopiarOrdenAnaquelesDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarOrdenAnaquelesService#getMapaZonaDefault(java.util.Map)
	 */
	public String getMapaZonaDefault(Map criteria) {
		return this.mantenimientoAPECopiarOrdenAnaquelesDAO.getMapaZonaDefault(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarOrdenAnaquelesService#getOidOrdenAnaquel(java.util.Map)
	 */
	public String getOidOrdenAnaquel(Map criteria) {
		return this.mantenimientoAPECopiarOrdenAnaquelesDAO.getOidOrdenAnaquel(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarOrdenAnaquelesService#getOrdenAnaquelList(java.util.Map)
	 */
	public List getOrdenAnaquelList(Map criteria) {
		return this.mantenimientoAPECopiarOrdenAnaquelesDAO.getOrdenAnaquelList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPECopiarOrdenAnaquelesService#executeGenerarOrdenAnaquelDetalle(java.util.Map)
	 */
	public void executeGenerarOrdenAnaquelDetalle(Map criteria) {
		this.mantenimientoAPECopiarOrdenAnaquelesDAO.executeGenerarOrdenAnaquelDetalle(criteria);
	}

}
