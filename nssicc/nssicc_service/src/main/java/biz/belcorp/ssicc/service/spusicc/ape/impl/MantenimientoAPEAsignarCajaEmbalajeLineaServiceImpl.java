package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEAsignarCajaEmbalajeLineaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEAsignarCajaEmbalajeLineaService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEGenerarEstimadoProductoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEAsignarCajaEmbalajeLineaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 public class MantenimientoAPEAsignarCajaEmbalajeLineaServiceImpl extends BaseService implements MantenimientoAPEAsignarCajaEmbalajeLineaService{

	@Resource(name="spusicc.mantenimientoAPEAsignarCajaEmbalajeLineaDAO")
 	private MantenimientoAPEAsignarCajaEmbalajeLineaDAO mantenimientoAPEAsignarCajaEmbalajeLineaDAO;



	/* (non-Javadoc)
 	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#getTipoCajaLineaList(java.util.Map)
 	 */
 	public List getTipoCajaLineaList(Map criteria){
 		return mantenimientoAPEAsignarCajaEmbalajeLineaDAO.getTipoCajaLineaList(criteria);
 	}

 	/* (non-Javadoc)
 	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#deleteTipoCajaLinea(java.util.Map, java.lang.String[])
 	 */
 	public void deleteTipoCajaLinea(Map criteria, String[] items) {
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			criteria.put("oidTipoCajaLinea", StringUtils.split(id, "|")[0]);
			
			mantenimientoAPEAsignarCajaEmbalajeLineaDAO.deleteTipoCajaLinea(criteria);
		}
	}
 	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#insertTipoCajaLinea(java.util.Map)
	 */
	public void insertTipoCajaLinea(Map criteria){
		mantenimientoAPEAsignarCajaEmbalajeLineaDAO.insertTipoCajaLinea(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#validaExisteTipoCajaLinea(java.util.Map)
	 */
	public String validaExisteTipoCajaLinea(Map criteria){
		return mantenimientoAPEAsignarCajaEmbalajeLineaDAO.validaExisteTipoCajaLinea(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#validaMinimoTipoCajaLinea(java.util.Map)
	 */
	public int validaMinimoTipoCajaLinea(Map criteria){
		return mantenimientoAPEAsignarCajaEmbalajeLineaDAO.validaMinimoTipoCajaLinea(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#getTipoCajaEmbalajeComboList(java.util.Map)
	 */
	public List getTipoCajaEmbalajeComboList(Map criteria){
		return mantenimientoAPEAsignarCajaEmbalajeLineaDAO.getTipoCajaEmbalajeComboList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#getOidTipoCajaEmbalbyCodigo(java.util.Map)
	 */
	public String getOidTipoCajaEmbalbyCodigo(Map criteria){
		return mantenimientoAPEAsignarCajaEmbalajeLineaDAO.getOidTipoCajaEmbalbyCodigo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#getDescripcionLineabyOid(java.util.Map)
	 */
	public String getDescripcionLineabyOid(Map criteria){
		return mantenimientoAPEAsignarCajaEmbalajeLineaDAO.getDescripcionLineabyOid(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEAsignarCajaEmbalajeLineaService#getDescripcionTipoCajabyOid(java.util.Map)
	 */
	public String getDescripcionTipoCajabyOid(Map criteria){
		return mantenimientoAPEAsignarCajaEmbalajeLineaDAO.getDescripcionTipoCajabyOid(criteria);
	}
 }