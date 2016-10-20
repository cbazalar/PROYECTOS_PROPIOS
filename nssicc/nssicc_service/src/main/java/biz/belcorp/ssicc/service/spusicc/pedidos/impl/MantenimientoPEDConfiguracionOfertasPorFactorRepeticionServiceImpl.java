/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService;

/**
 * @author Sigcomt
 *
 */
@Service("spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDConfiguracionOfertasPorFactorRepeticionServiceImpl extends BaseService implements MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService {

	@Resource(name="spusicc.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO")
	private MantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#getOfertaFactorRepeticionList(java.util.Map)
	 */
	public List getOfertaFactorRepeticionList(Map params) {
		return this.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getOfertaFactorRepeticionList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#insertOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertOfertaFactorRepeticion(Map params) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.insertOfertaFactorRepeticion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#insertRangoOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertRangoOfertaFactorRepeticion(Map rango) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.insertRangoOfertaFactorRepeticion(rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#removeRangoOfertaFactorRepeticion(java.lang.String)
	 */
	public void removeRangoOfertaFactorRepeticion(String oidRango, String codigoUsuario) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.removeRangoOfertaFactorRepeticion(oidRango, codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#getRangoOfertaFactorRepeticionList(java.util.Map)
	 */
	public List getRangoOfertaFactorRepeticionList(Map params) {
		return this.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getRangoOfertaFactorRepeticionList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#getRangoGratisOfertaFactorRepeticionList(java.util.Map)
	 */
	public List getRangoGratisOfertaFactorRepeticionList(Map params) {
		return this.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getRangoGratisOfertaFactorRepeticionList(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#insertRangoGratisOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertRangoGratisOfertaFactorRepeticion(Map regalo) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.insertRangoGratisOfertaFactorRepeticion(regalo);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#getMoneda(java.util.Map)
	 */
	public Map getMoneda(Map params) {
		return mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getMoneda(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#getCuvs(java.util.Map)
	 */
	public List getCuvs(Map params) {
		return mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getCuvs(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#removeRangoGratisOfertaFactorRepeticion(java.lang.String)
	 */
	public void removeRangoGratisOfertaFactorRepeticion(String oidGratis, String codigoUsuario) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.removeRangoGratisOfertaFactorRepeticion(oidGratis, codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#insertCriterioOfertaFactorRepeticion(java.util.Map)
	 */
	public void insertCriterioOfertaFactorRepeticion(Map criterio) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.insertCriterioOfertaFactorRepeticion(criterio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#removeCriterioOfertaFactorRepeticion(java.lang.String)
	 */
	public void removeCriterioOfertaFactorRepeticion(String oidCriterio, String codigoUsuario) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.removeCriterioOfertaFactorRepeticion(oidCriterio, codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#executeCalcularProductos(java.util.Map)
	 */
	public List executeCalcularProductos(Map params) {
		
		String codigoUsuario = (String) params.get("codigoUsuario");
		String oidOferta = (String) params.get("oidOferta");
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.deleteProductosOfertaFactorRepeticion(oidOferta, codigoUsuario);
		
		List productos = this.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getProductoOfertaFactorRepeticionList(params);
		
		if(productos != null && productos.size() > 0)
		{
			for(int i=0; i<productos.size(); i++)
			{
				Map producto = (Map)productos.get(i);
				producto.put("oidOferta", MapUtils.getString(params, "oidOferta", ""));
				producto.put("codigoUsuario", MapUtils.getString(params, "codigoUsuario", ""));
				mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.insertProductoOfertaFactorRepeticion(producto);
			}
		}
		
		productos=this.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getProductoOfertaFactorRepeticionList1(params);
		
		return productos;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#getCriterioOfertaFactorRepeticionList(java.lang.String)
	 */
	public List getCriterioOfertaFactorRepeticionList(String oidOferta) {
		return this.mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getCriterioOfertaFactorRepeticionList(oidOferta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#removeOfertaFactorRepeticion(java.lang.String)
	 */
	public void removeOfertaFactorRepeticion(String oidOferta, String codigoUsuario) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.removeOfertaFactorRepeticion(oidOferta, codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#getProductoOfertaFactorRepeticionList(java.util.Map)
	 */
	public List getProductoOfertaFactorRepeticionList(Map params) {
		return mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getProductoOfertaFactorRepeticionList(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#getProductoOfertaFactorRepeticionList1(java.util.Map)
	 */
	public List getProductoOfertaFactorRepeticionList1(Map params) {
		return mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.getProductoOfertaFactorRepeticionList1(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#updateOfertaFactorRepeticion(java.util.Map)
	 */
	public void updateOfertaFactorRepeticion(Map params) {
		mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.updateOfertaFactorRepeticion(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorFactorRepeticionService#updateRangoOfertaFactorRepeticion(java.util.List)
	 */
	public String updateRangoOfertaFactorRepeticion(List rangoList,  String oidOferta, String codigoUsuario) {
		String retorno = "";
		
		for(int i=0; i < rangoList.size(); i++ ) {
			Map rango = (Map) rangoList.get(i);
			String precioUnitario = (String) rango.get("precioUnitario");
			
			if(StringUtils.isBlank(precioUnitario)) 	{
				retorno = this.getKeyMessage("updateRangoOfertaFactorRepeticion.precioUnitario.requerido");
				return retorno;
			}
		}
		
		for(int i=0; i < rangoList.size(); i++ ) {
			Map rango = (Map) rangoList.get(i);
			rango.put("oidOferta", oidOferta);
			rango.put("codigoUsuario", codigoUsuario);
			// Actualizamos en BD
			mantenimientoPEDConfiguracionOfertasPorFactorRepeticionDAO.updateRangoOfertaFactorRepeticion(rango);
		}
		return retorno;
	}

	
}
