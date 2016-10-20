/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECProductosFFNNEEDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.ProdutosFFNNEE;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECProductosFFNNEEService;

/**
 * @author peextcroman
 *
 */
@Service("spusicc.mantenimientoRECProductosFFNNEEService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECProductosFFNNEEServiceImpl extends BaseService implements MantenimientoRECProductosFFNNEEService {
	
	@Resource(name="spusicc.mantenimientoRECProductosFFNNEEDAO")
	MantenimientoRECProductosFFNNEEDAO mantenimientoRECProductosFFNNEEDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECProductosFFNNEEService#getProductosFFNNEEList(java.util.Map)
	 */
	public List getProductosFFNNEEList(Map map){
		return mantenimientoRECProductosFFNNEEDAO.getProductosFFNNEEList(map);
	}
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECProductosFFNNEEService#getDescripcionProducto(java.util.Map)
	 */
	public String getDescripcionProducto(Map map){
		return mantenimientoRECProductosFFNNEEDAO.getDescripcionProducto(map);
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECProductosFFNNEEService#getOidProducto(java.util.Map)
	 */
	public String getOidProducto(Map map){
		return mantenimientoRECProductosFFNNEEDAO.getOidProducto(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECProductosFFNNEEService#deleteProductosFFNNEE(java.util.Map)
	 */
	public void deleteProductosFFNNEE(Map map){
		mantenimientoRECProductosFFNNEEDAO.deleteProductosFFNNEE(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECProductosFFNNEEService#insertProductosFFNNEE(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List)
	 */
	public void insertProductosFFNNEE(String codigoPais,String codigoPeriodoInicio,String codigoPeriodoFin,String codigoRegion,List detalle,List regionList){
		
		
	Map criteriaInserta = new  HashMap();
	   
	if(codigoRegion.compareToIgnoreCase("")==0){
		for (Iterator iterator = regionList.iterator(); iterator.hasNext();) {
			
			Base baseRegion = (Base) iterator.next();
			log.debug("codigoRegion"+baseRegion.getCodigo());
			criteriaInserta.put("codigoPais",codigoPais);
			criteriaInserta.put("codigoPeriodoInicio",codigoPeriodoInicio);
			criteriaInserta.put("codigoPeriodoFin",codigoPeriodoFin);
			criteriaInserta.put("codigoRegion", baseRegion.getCodigo());
			
			

			for (Iterator iterator2 = detalle.iterator(); iterator2.hasNext();) {
				
				Base base = (Base) iterator2.next();
				criteriaInserta.put("codigoSAP", base.getCodigo());
				if(mantenimientoRECProductosFFNNEEDAO.validaProductoFFNNEE(criteriaInserta).compareToIgnoreCase("0")==0){
					mantenimientoRECProductosFFNNEEDAO.insertProductosFFNNEE(criteriaInserta);
				}
				
			}	
		}	
		
	}else
	{
		for (Iterator iterator = detalle.iterator(); iterator.hasNext();) {
		
		Base base = (Base) iterator.next();
		
		criteriaInserta.put("codigoPais",codigoPais);
		criteriaInserta.put("codigoPeriodoInicio",codigoPeriodoInicio);
		criteriaInserta.put("codigoPeriodoFin",codigoPeriodoFin);
		criteriaInserta.put("codigoRegion", codigoRegion);
		criteriaInserta.put("codigoSAP", base.getCodigo());
		
		if(mantenimientoRECProductosFFNNEEDAO.validaProductoFFNNEE(criteriaInserta).compareToIgnoreCase("0")==0){
			mantenimientoRECProductosFFNNEEDAO.insertProductosFFNNEE(criteriaInserta);
		}
	    }	
	}
	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECProductosFFNNEEService#insertProductosFFNNEEArchivo(java.lang.String, java.util.List)
	 */
	public void insertProductosFFNNEEArchivo(String codigoPais, List detalle){
		
		Map criteriaInserta = new  HashMap();
		criteriaInserta.put("codigoPais",codigoPais);
		if(detalle != null){
			for (Iterator iterator2 = detalle.iterator(); iterator2.hasNext();) {
				
				ProdutosFFNNEE producto = (ProdutosFFNNEE) iterator2.next();
				criteriaInserta.put("codigoSAP", producto.getCodigoSAP());
				criteriaInserta.put("codigoPeriodoInicio", producto.getCodigoPeriodoInicio());
				criteriaInserta.put("codigoPeriodoFin", producto.getCodigoPeriodoFin());
				criteriaInserta.put("codigoRegion", producto.getCodigoRegion());
				if(mantenimientoRECProductosFFNNEEDAO.validaProductoFFNNEE(criteriaInserta).compareToIgnoreCase("0")==0){
					mantenimientoRECProductosFFNNEEDAO.insertProductosFFNNEE(criteriaInserta);
				}
				
			}	
		}
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECProductosFFNNEEService#obtenerPathUpload(java.lang.String)
	 */
	public String obtenerPathUpload(String codigoPais){
		return mantenimientoRECProductosFFNNEEDAO.obtenerPathUpload(codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECProductosFFNNEEService#getCodigoCUVFicticioProducto(java.util.Map)
	 */
	public String getCodigoCUVFicticioProducto(Map map){
		return mantenimientoRECProductosFFNNEEDAO.getCodigoCUVFicticioProducto(map);
	}
	
	
	
}
