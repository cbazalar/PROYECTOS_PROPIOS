package biz.belcorp.ssicc.service.spusicc.mic.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mic.MantenimientoMICSegurosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mic.MantenimientoMICSegurosService;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mic.mantenimientoMICSegurosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoMICSegurosServiceImpl extends BaseService implements
	MantenimientoMICSegurosService {

	@Resource(name="spusicc.mantenimientoMICSegurosDAO")
	MantenimientoMICSegurosDAO mantenimientoMICSegurosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getCronogramaMicroseguros(java.util.Map)
	 */
	public List getCronogramaMicroseguros(Map criteria) {
		return mantenimientoMICSegurosDAO.getCronogramaMicroseguros(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getTipoOperacion()
	 */
	public List getTipoOperacion() {
		return mantenimientoMICSegurosDAO.getTipoOperacion();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#insertCronograma(java.util.Map)
	 */
	public void insertCronograma(Map map) {
		mantenimientoMICSegurosDAO.insertCronograma(map);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#updateCronograma(java.util.Map)
	 */
	public void updateCronograma(Map map) {
		mantenimientoMICSegurosDAO.updateCronograma(map);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#insertMicroseguros(java.util.Map)
	 */
	public void insertMicroseguros(Map map) {
		mantenimientoMICSegurosDAO.insertMicroseguros(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#updateMicroseguros(java.util.Map)
	 */
	public void updateMicroseguros(Map map) {
		mantenimientoMICSegurosDAO.updateMicroseguros(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#insertCobertura(java.util.Map)
	 */
	public void insertCobertura(Map map) {
		mantenimientoMICSegurosDAO.insertCobertura(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#updateCobertura(java.util.Map)
	 */
	public void updateCobertura(Map map) {
		mantenimientoMICSegurosDAO.updateCobertura(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#insertBancos(java.util.Map)
	 */
	public void insertBancos(Map map) {
		mantenimientoMICSegurosDAO.insertBancos(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#updateBancos(java.util.Map)
	 */
	public void updateBancos(Map map) {
		mantenimientoMICSegurosDAO.updateBancos(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#insertGrupos(java.util.Map)
	 */
	public void insertGrupos(Map map) {
		mantenimientoMICSegurosDAO.insertGrupos(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#insertGruposDetalle(java.util.Map)
	 */
	public void insertGruposDetalle(Map map) {
		mantenimientoMICSegurosDAO.insertGruposDetalle(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#updateGrupos(java.util.Map)
	 */
	public void updateGrupos(Map map) {
		mantenimientoMICSegurosDAO.updateGrupos(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#updateGruposDetalle(java.util.Map)
	 */
	public void updateGruposDetalle(Map map) {
		mantenimientoMICSegurosDAO.updateGruposDetalle(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getBancos(java.util.Map)
	 */
	public List getBancos(Map criteria) {
		return mantenimientoMICSegurosDAO.getBancos(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getCobertura(java.util.Map)
	 */
	public List getCobertura(Map criteria) {
		return mantenimientoMICSegurosDAO.getCobertura(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getGrupos(java.util.Map)
	 */
	public List getGrupos(Map criteria) {
		return mantenimientoMICSegurosDAO.getGrupos(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getGruposDetalle(java.util.Map)
	 */
	public List getGruposDetalle(Map criteria) {
		return mantenimientoMICSegurosDAO.getGruposDetalle(criteria);
	}


	public List getMicroseguros(Map criteria) {
		return mantenimientoMICSegurosDAO.getMicroseguros(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#deleteGruposDetalle(java.util.Map)
	 */
	public void deleteGruposDetalle(Map criteria) {
		mantenimientoMICSegurosDAO.deleteGruposDetalle(criteria);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getParametrosMicroSeguro()
	 */
	public Map getParametrosMicroSeguro() {
		return mantenimientoMICSegurosDAO.getParametrosMicroSeguro();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getCoberturaGrupos(java.util.Map)
	 */
	public List getCoberturaGrupos(Map criteria) {
		return mantenimientoMICSegurosDAO.getCoberturaGrupos(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#insertCoberturaGrupos(java.util.Map)
	 */
	public void insertCoberturaGrupos(Map map) {
		mantenimientoMICSegurosDAO.insertCoberturaGrupos(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#updateCoberturaGrupos(java.util.Map)
	 */
	public void updateCoberturaGrupos(Map map) {
		mantenimientoMICSegurosDAO.updateCoberturaGrupos(map);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mic.service.MantenimientoMICSegurosService#getValidacionFechaInicioCronograma(java.util.Map)
	 */
	public Integer getValidacionFechaInicioCronograma(Map map) {		
		return mantenimientoMICSegurosDAO.getValidacionFechaInicioCronograma(map);
	}

}
