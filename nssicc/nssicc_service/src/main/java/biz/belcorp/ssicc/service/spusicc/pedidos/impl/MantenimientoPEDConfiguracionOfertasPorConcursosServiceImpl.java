/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosService;

/**
 * @author Sigcomt
 * 
 */
@Service("spusicc.mantenimientoPEDConfiguracionOfertasPorConcursosService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MantenimientoPEDConfiguracionOfertasPorConcursosServiceImpl extends
		BaseService implements
		MantenimientoPEDConfiguracionOfertasPorConcursosService {

	@Resource(name = "spusicc.mantenimientoPEDConfiguracionOfertasPorConcursosDAO")
	private MantenimientoPEDConfiguracionOfertasPorConcursosDAO mantenimientoPEDConfiguracionOfertasPorConcursosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#getOfertaConcursosList(java.util.Map)
	 */
	public List getOfertaConcursosList(Map params) {
		return this.mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getOfertaConcursosList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#insertOfertaConcursos(java.util.Map)
	 */
	public void insertOfertaConcursos(Map params) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.insertOfertaConcursos(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#insertRangoOfertaConcursos(java.util.Map)
	 */
	public void insertRangoOfertaConcursos(Map rango) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.insertRangoOfertaConcursos(rango);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#removeRangoOfertaConcursos(java.lang.String)
	 */
	public void removeRangoOfertaConcursos(String oidRango, String codigoUsuario) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.removeRangoOfertaConcursos(oidRango, codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#getRangoOfertaConcursosList(java.util.Map)
	 */
	public List getRangoOfertaConcursosList(Map params) {
		return this.mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getRangoOfertaConcursosList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#getRangoGratisOfertaConcursosList(java.util.Map)
	 */
	public List getRangoGratisOfertaConcursosList(Map params) {
		return this.mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getRangoGratisOfertaConcursosList(params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#insertRangoGratisOfertaConcursos(java.util.Map)
	 */
	public void insertRangoGratisOfertaConcursos(Map regalo) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.insertRangoGratisOfertaConcursos(regalo);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#getMoneda(java.util.Map)
	 */
	public Map getMoneda(Map params) {
		return mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getMoneda(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#getCuvs(java.util.Map)
	 */
	public List getCuvs(Map params) {
		return mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getCuvs(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#removeRangoGratisOfertaConcursos(java.lang.String)
	 */
	public void removeRangoGratisOfertaConcursos(String oidGratis, String codigoUsuario) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.removeRangoGratisOfertaConcursos(oidGratis, codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#insertCriterioOfertaConcursos(java.util.Map)
	 */
	public void insertCriterioOfertaConcursos(Map criterio) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.insertCriterioOfertaConcursos(criterio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#removeCriterioOfertaConcursos(java.lang.String)
	 */
	public void removeCriterioOfertaConcursos(String oidCriterio, String codigoUsuario) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.removeCriterioOfertaConcursos(oidCriterio, codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#executeCalcularProductos(java.util.Map)
	 */
	public List executeCalcularProductos(Map params) {
		
		String codigoUsuario = (String) params.get("codigoUsuario");
		String oidOferta = (String) params.get("oidOferta");
		
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.deleteProductosOfertaConcursos(oidOferta, codigoUsuario);
		
		List productos = this.mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getProductoOfertaConcursosList(params);
		 
		if(productos != null && productos.size() > 0)
		{
			for(int i=0; i<productos.size(); i++) {
				Map producto = (Map)productos.get(i);
				producto.put("oidOferta", MapUtils.getString(params, "oidOferta", ""));
				producto.put("codigoUsuario", MapUtils.getString(params, "codigoUsuario", ""));
				
				mantenimientoPEDConfiguracionOfertasPorConcursosDAO.insertProductoOfertaConcursos(producto);
			}
		}
		//INI PER-SiCC-2015-0206
		productos= this.mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getProductoOfertaConcursosList1(params);
		//FIN PER-SiCC-2015-0206
		
		return productos;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#getCriterioOfertaConcursosList(java.lang.String)
	 */
	public List getCriterioOfertaConcursosList(String oidOferta) {
		return this.mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getCriterioOfertaConcursosList(oidOferta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#removeOfertaConcursos(java.lang.String)
	 */
	public void removeOfertaConcursos(String oidOferta, String codigoUsuario) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.removeOfertaConcursos(oidOferta, codigoUsuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#getProductoOfertaConcursosList(java.util.Map)
	 */
	public List getProductoOfertaConcursosList(Map params) {
		return mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getProductoOfertaConcursosList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#getProductoOfertaConcursosList1(java.util.Map)
	 */
	public List getProductoOfertaConcursosList1(Map params) {
		return mantenimientoPEDConfiguracionOfertasPorConcursosDAO.getProductoOfertaConcursosList1(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertasPorConcursosService#updateOfertaConcursos(java.util.Map)
	 */
	public void updateOfertaConcursos(Map params) {
		mantenimientoPEDConfiguracionOfertasPorConcursosDAO.updateOfertaConcursos(params);
	}
	
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertasPorConcursosService#updateRangoOfertaConcursos(java.util.List, java.lang.String)
	 */
	public String updateRangoOfertaConcursos(List rangoList, String codigoUsuario) {
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
			rango.put("codigoUsuario", codigoUsuario);
			// Actualizamos en BD
			
			mantenimientoPEDConfiguracionOfertasPorConcursosDAO.updateRangoOfertaConcursos(rango);
		}
		return retorno;
		
	}
		
}
