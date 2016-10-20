package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETipoCajaProductoDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoCajaProducto;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPETipoCajaProductoService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPETipoCajaProductoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPETipoCajaProductoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 public class MantenimientoAPETipoCajaProductoServiceImpl extends BaseService implements MantenimientoAPETipoCajaProductoService{

	@Resource(name="spusicc.mantenimientoAPETipoCajaProductoDAO")
 	private MantenimientoAPETipoCajaProductoDAO mantenimientoAPETipoCajaProductoDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoCajaProductoService#getTipoCajaProdList(java.util.Map)
	 */
	public List getTipoCajaProdList(Map criteria){
		return mantenimientoAPETipoCajaProductoDAO.getTipoCajaProdList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoCajaProductoService#getTipoCajaProductoObject(java.util.Map)
	 */
	public TipoCajaProducto getTipoCajaProductoObject(Map criteria){
		return mantenimientoAPETipoCajaProductoDAO.getTipoCajaProductoObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoCajaProductoService#insertTipoCajaProducto(java.util.Map)
	 */
	public void insertTipoCajaProducto(Map criteria){
		mantenimientoAPETipoCajaProductoDAO.insertTipoCajaProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoCajaProductoService#getExisteCodTipoCajaProducto(java.util.Map)
	 */
	public int getExisteCodTipoCajaProducto(Map criteria){
		return mantenimientoAPETipoCajaProductoDAO.getExisteCodTipoCajaProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoCajaProductoService#getNextOidTipoCajaProducto(java.util.Map)
	 */
	public int getNextOidTipoCajaProducto(Map criteria){
		return mantenimientoAPETipoCajaProductoDAO.getNextOidTipoCajaProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoCajaProductoService#deleteTipoCajaProducto(java.util.Map, java.lang.String[])
	 */
	public String deleteTipoCajaProducto(Map criteria, String[] items){
		String vNomTabla = new String();
		String error= new String();
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			criteria.put("oidTipoCajaProd", StringUtils.split(id, "|")[0]);
			criteria.put("codTipoCajaProd", StringUtils.split(id, "|")[1]);
			criteria.put("nombreTablaTipoCajaProducto", Constants.TABLA_TIPO_CAJA_PRODUCTO);
			
			//Antes de eliminar se valida que no existan registros en las tablas hijas
			mantenimientoAPETipoCajaProductoDAO.executeValidarRegistrosTipoCajaProducto(criteria);
			error = (String) criteria.get("valError");
			
			if (Constants.NUMERO_CERO.equals(error)){
				mantenimientoAPETipoCajaProductoDAO.deleteTipoCajaProducto(criteria);
				
				//Se elimina de la tabla de idoma
				mantenimientoAPETipoCajaProductoDAO.deleteIdiomaTipoCajaProducto(criteria);
				vNomTabla ="1";
			}else{
				vNomTabla = (String) criteria.get("nomTabla");
				break;
			}
		}
		return vNomTabla;
	}
 }