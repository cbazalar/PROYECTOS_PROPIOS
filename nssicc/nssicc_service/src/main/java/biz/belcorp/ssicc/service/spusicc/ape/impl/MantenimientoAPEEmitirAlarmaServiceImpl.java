package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEEmitirAlarmaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEEmitirAlarmaService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEEmitirAlarmaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 * 
 */
@Service("spusicc.mantenimientoAPEEmitirAlarmaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEEmitirAlarmaServiceImpl extends BaseService implements MantenimientoAPEEmitirAlarmaService{
	
	@Resource(name="spusicc.mantenimientoAPEEmitirAlarmaDAO")
	private MantenimientoAPEEmitirAlarmaDAO mantenimientoAPEEmitirAlarmaDAO;


	/**
	 * @param mantenimientoAPEEmitirAlarmaDAO the mantenimientoAPEEmitirAlarmaDAO to set
	 */
	public void setMantenimientoAPEEmitirAlarmaDAO(
			MantenimientoAPEEmitirAlarmaDAO mantenimientoAPEEmitirAlarmaDAO) {
		this.mantenimientoAPEEmitirAlarmaDAO = mantenimientoAPEEmitirAlarmaDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaService#getEmitirAlarmaList(java.util.Map)
	 */
	public List getEmitirAlarmaList(Map criteria) {
		return mantenimientoAPEEmitirAlarmaDAO.getEmitirAlarmaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaService#getMailEmitirAlarmaList(java.util.Map)
	 */
	public List getMailEmitirAlarmaList(Map criteria) {
		return mantenimientoAPEEmitirAlarmaDAO.getMailEmitirAlarmaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaService#getOidPeriodobyMarcaCanal(java.util.Map)
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria){
		return mantenimientoAPEEmitirAlarmaDAO.getOidPeriodobyMarcaCanal(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaService#getMapaZonaComboList(java.util.Map)
	 */
	public List getMapaZonaComboList(Map criteria){
		return mantenimientoAPEEmitirAlarmaDAO.getMapaZonaComboList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaService#getCodigoMapaZona(java.util.Map)
	 */
	public String getCodigoMapaZona(Map criteria){
		return mantenimientoAPEEmitirAlarmaDAO.getCodigoMapaZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaService#getExistePeriodobyMarcaCanal(java.util.Map)
	 */
	public int getExistePeriodobyMarcaCanal(Map criteria){
		return mantenimientoAPEEmitirAlarmaDAO.getExistePeriodobyMarcaCanal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEmitirAlarmaService#getMapaZonaVersionList(java.util.Map)
	 */
	public List getMapaZonaVersionList(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEmitirAlarma(Map criteria) {
		// TODO Auto-generated method stub
		
	}

}