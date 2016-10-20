package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEEstimadoProductoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.EstimadoProducto;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEEstimadoProductoService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEEstimadoProductoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 * 
 */

@Service("spusicc.mantenimientoAPEEstimadoProductoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEEstimadoProductoServiceImpl extends BaseService implements MantenimientoAPEEstimadoProductoService{
	
	@Resource(name="spusicc.mantenimientoAPEEstimadoProductoDAO")
	private MantenimientoAPEEstimadoProductoDAO mantenimientoAPEEstimadoProductoDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getLineaArmadobyOidCentro(java.util.Map)
	 */
	public List getLineaArmadobyOidCentro(Map criteria) {
		return mantenimientoAPEEstimadoProductoDAO.getLineaArmadobyOidCentro(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getCodLineaArmadaDefecto(java.util.Map)
	 */
	public String getCodLineaArmadaDefecto(Map criteria){
		return mantenimientoAPEEstimadoProductoDAO.getCodLineaArmadaDefecto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getOidLineaArmadobyCodigo(java.util.Map)
	 */
	
	public String getOidLineaArmadobyCodigo(Map criteria) {
		return mantenimientoAPEEstimadoProductoDAO.getOidLineaArmadobyCodigo(criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getEstimadoProductoList(java.util.Map)
	 */
	public List getEstimadoProductoList(Map criteria){
		return mantenimientoAPEEstimadoProductoDAO.getEstimadoProductoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getSubLineaArmadoObject(java.util.Map)
	 */
	public EstimadoProducto getSubLineaArmadoObject(Map criteria) {
		return mantenimientoAPEEstimadoProductoDAO.getSubLineaArmadoObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getExisteLetraAnquelCD(java.util.Map)
	 */
	public int getExisteEstimadoProductoCD(Map criteria){
		return mantenimientoAPEEstimadoProductoDAO.getExisteEstimadoProductoCD(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#insertEstimadoProducto(java.util.Map)
	 */
	public void insertEstimadoProducto(Map criteria){
		mantenimientoAPEEstimadoProductoDAO.insertEstimadoProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#updateSubLineaArmado(java.util.Map)
	 */
	public void updateEstimadoProducto(Map criteria) {
		mantenimientoAPEEstimadoProductoDAO.updateEstimadoProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#deleteEstimadoProducto(java.util.Map, java.lang.String[])
	 */
	public void deleteEstimadoProducto(Map criteria,String[] items) {
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			criteria.put("oidPeriodo", StringUtils.split(id, "|")[4]);
			criteria.put("oidProd", StringUtils.split(id, "|")[6]);
			criteria.put("oidProc", StringUtils.split(id, "|")[12]);
			
			this.mantenimientoAPEEstimadoProductoDAO.deleteEstimadoProducto(criteria);	
			
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getCodLineaArmadaDefectoList(java.util.Map)
	 */
	public String getCodLineaArmadaDefectoList(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getCodigoLineaArmadobyOid(java.util.Map)
	 */
	public String getCodigoLineaArmadobyOid(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getLineaArmadoComboList(java.util.Map)
	 */
	public List getLineaArmadoComboList(Map criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getNextOidEstimadoProducto()
	 */
	public int getNextOidEstimadoProducto() {
		return mantenimientoAPEEstimadoProductoDAO.getNextOidEstimadoProducto();
	}
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getOidProductoByCodigoyPais(java.util.Map)
	 */
	public String getOidProductoByCodigoyPais(Map criteria) {
		return mantenimientoAPEEstimadoProductoDAO.getOidProductoByCodigoyPais(criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEEstimadoProductoService#getExisteProductoCD(java.util.Map)
	 */
	public int getExisteProductoCD(Map criteria){
		return mantenimientoAPEEstimadoProductoDAO.getExisteProductoCD(criteria);
	}	

}