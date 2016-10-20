package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPEEvaluarCargaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.ProcesoAPEEvaluarCargaService;

/**
 *  
 * <p>
 * <a href="ProcesoAPEEvaluarCargaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.procesoAPEEvaluarCargaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoAPEEvaluarCargaServiceImpl extends BaseService implements ProcesoAPEEvaluarCargaService{
	
	@Resource(name="spusicc.procesoAPEEvaluarCargaDAO")
	private ProcesoAPEEvaluarCargaDAO procesoAPEEvaluarCargaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEEvaluarCargaService#getNumeroZonaSubLinea(java.util.Map)
	 */
	public int getNumeroZonaSubLinea(Map criteria) {
		return procesoAPEEvaluarCargaDAO.getNumeroZonaSubLinea(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEEvaluarCargaService#getMapaZonaCabecera(java.util.Map)
	 */
	public String getMapaZonaCabecera(Map criteria) {
		return procesoAPEEvaluarCargaDAO.getMapaZonaCabecera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEEvaluarCargaService#getAsignacionProductoAnaquelCabecera(java.util.Map)
	 */
	public String getAsignacionProductoAnaquelCabecera(Map criteria) {
		return procesoAPEEvaluarCargaDAO.getAsignacionProductoAnaquelCabecera(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEEvaluarCargaService#getTotalEstimadoSubLinea(java.util.Map)
	 */
	public int getTotalEstimadoSubLinea(Map criteria){
		return procesoAPEEvaluarCargaDAO.getTotalEstimadoSubLinea(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEEvaluarCargaService#getEvaluarCargaList(java.util.Map)
	 */
	public List getEvaluarCargaList(Map criteria){
		return procesoAPEEvaluarCargaDAO.getEvaluarCargaList(criteria);
	}
}