/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CondicionOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetalleOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.GrupoOferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.Oferta;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.RangoPromocion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaService;

/**
 * @author Ivan Tocto
 *
 */
@Service("spusicc.mantenimientoPEDConfiguracionOfertaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDConfiguracionOfertaServiceImpl extends BaseService implements MantenimientoPEDConfiguracionOfertaService {

	@Resource(name="spusicc.mantenimientoPEDConfiguracionOfertaDAO")
	private MantenimientoPEDConfiguracionOfertaDAO mantenimientoPEDConfiguracionOfertaDAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getPeriodosMatrizFacturacion()
	 */
	public List getPeriodosMatrizFacturacion() {
		return mantenimientoPEDConfiguracionOfertaDAO.getPeriodosMatrizFacturacion();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getEstimadosMatrizFacturacion(java.util.Map)
	 */
	public List getEstimadosMatrizFacturacion(Map params) {
		return mantenimientoPEDConfiguracionOfertaDAO.getEstimadosMatrizFacturacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#insertMatrizFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.MatrizFacturacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMatrizFacturacion(MatrizFacturacion matriz, Usuario usuario) {
		mantenimientoPEDConfiguracionOfertaDAO.insertMatrizFacturacion(matriz, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#updateMatrizFacturacion(biz.belcorp.ssicc.spusicc.pedidos.model.MatrizFacturacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMatrizFacturacion(MatrizFacturacion matriz, Usuario usuario) {
		mantenimientoPEDConfiguracionOfertaDAO.updateMatrizFacturacion(matriz, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getMatrizFacturacion(java.lang.String)
	 */
	public MatrizFacturacion getMatrizFacturacion(String oidMatriz) {
		
		MatrizFacturacion matriz = null;
		
		Map params = new HashMap();
		params.put("oidMatriz", oidMatriz);
		
		List lista = mantenimientoPEDConfiguracionOfertaDAO.getEstimadosMatrizFacturacion(params);
		
		if(lista != null && lista.size() == 1)
			matriz = (MatrizFacturacion)lista.get(0);
			
		return matriz;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#executeAsignarCodigoVentaAction(java.util.Map)
	 */
	public void executeAsignarCodigoVentaAction(Map params) {		
		mantenimientoPEDConfiguracionOfertaDAO.executeAsignarCodigoVentaAction(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getEstrategias(java.util.Map)
	 */
	public List getEstrategias(Map params) {
		return mantenimientoPEDConfiguracionOfertaDAO.getEstrategias(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getOidAccesosByCodigoISO(java.lang.String)
	 */
	public List getOidAccesosByCodigoISO(String codigoIso) {
		return mantenimientoPEDConfiguracionOfertaDAO.getOidAccesosByCodigoISO(codigoIso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getOidSubaccesosByOidAcceso(java.lang.String)
	 */
	public List getOidSubaccesosByOidAcceso(String oidAcceso) {
		return mantenimientoPEDConfiguracionOfertaDAO.getOidSubaccesosByOidAcceso(oidAcceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getProductosAsociadosByCriteria(java.util.Map)
	 */
	public List getProductosAsociadosByCriteria(Map params) {
		boolean salirCambioCodigoVentaModificaCUV = MapUtils.getBoolean(params, "salirCambioCodigoVentaModificaCUV");
		boolean salirCambioCodigoVentaModificaCUVCompuestaFija = MapUtils.getBoolean(params, "salirCambioCodigoVentaModificaCUVCompuestaFija");
		List lista = new ArrayList();
		
		if(salirCambioCodigoVentaModificaCUV || salirCambioCodigoVentaModificaCUVCompuestaFija){
			lista = mantenimientoPEDConfiguracionOfertaDAO.getProductosAsociadosParaGrupo(params);
		}else{
			lista = mantenimientoPEDConfiguracionOfertaDAO.getProductosAsociadosByCriteria1(params);
			
			if(lista == null || lista.size() == 0)
				lista = mantenimientoPEDConfiguracionOfertaDAO.getProductosAsociadosByCriteria2(params);
		}
		
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getValidarTipoOferta(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String getValidarTipoOferta(String oidTipoOferta, String codigoProducto, String oidEstrategia, String precioCatalogo, String precioPosicionamiento) {
		return mantenimientoPEDConfiguracionOfertaDAO.getValidarTipoOferta(oidTipoOferta, codigoProducto, oidEstrategia, precioCatalogo, precioPosicionamiento);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getOidTipoEstrategia(java.lang.String)
	 */
	public String getOidTipoEstrategia(String oidEstrategia) {
		String oid = "";
		Map params = new HashMap();
		params.put("oidEstrategia", oidEstrategia);
		
		List estrategias = mantenimientoPEDConfiguracionOfertaDAO.getEstrategias(params);
		
		if(estrategias != null && estrategias.size() == 1)
			oid = MapUtils.getString((Map)estrategias.get(0), "oidTipo");
		
		return oid;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getTiposCuadre(java.lang.String)
	 */
	public List getTiposCuadre(String numeroGrupos) {
		return mantenimientoPEDConfiguracionOfertaDAO.getTiposCuadre(numeroGrupos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#getCondicionPromocionTiposCuadre()
	 */
	public List getCondicionPromocionTiposCuadre() {
		return mantenimientoPEDConfiguracionOfertaDAO.getCondicionPromocionTiposCuadre();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDConfiguracionOfertaService#insertOferta(biz.belcorp.ssicc.spusicc.pedidos.model.Oferta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertOferta(Oferta oferta, Usuario usuario) {
		
		int consecutivoDetalleOferta = 1;
		
		//Insertamos la oferta		
		mantenimientoPEDConfiguracionOfertaDAO.insertOferta(oferta, usuario);
		
		//Insertamos los grupos
		if(oferta.getGrupos() != null && oferta.getGrupos().size() > 0)
		{
			for(int i=0; i<oferta.getGrupos().size(); i++)
			{
				GrupoOferta grupoOferta = (GrupoOferta)oferta.getGrupos().get(i);
				grupoOferta.setOidOferta(oferta.getOid());
				
				mantenimientoPEDConfiguracionOfertaDAO.insertGrupoOferta(grupoOferta, usuario);
				
				//Insertamos los detalles
				if(grupoOferta.getDetalles() != null && grupoOferta.getDetalles().size() > 0)
				{
					for(int j=0; j<grupoOferta.getDetalles().size(); j++)
					{
						DetalleOferta detalleOferta = (DetalleOferta)grupoOferta.getDetalles().get(j);
						detalleOferta.setOidOferta(oferta.getOid());
						detalleOferta.setOidGrupo(grupoOferta.getOid());
						detalleOferta.setNumeroLinea(Integer.toString(consecutivoDetalleOferta));
						mantenimientoPEDConfiguracionOfertaDAO.insertDetalleOferta(detalleOferta, usuario);
						consecutivoDetalleOferta++;
					}
				}
				//
			}
		}
		//
		
		//Insertamos los detalles de la oferta, productos asociados
		if(oferta.getDetalles() != null && oferta.getDetalles().size() > 0)
		{
			for(int i=0; i<oferta.getDetalles().size(); i++)
			{
				DetalleOferta detalleOferta = (DetalleOferta)oferta.getDetalles().get(i);
				detalleOferta.setOidOferta(oferta.getOid());
				detalleOferta.setNumeroLinea(Integer.toString(consecutivoDetalleOferta));
				mantenimientoPEDConfiguracionOfertaDAO.insertDetalleOferta(detalleOferta, usuario);
				consecutivoDetalleOferta++;
			}
		}
		//
		
		//Condicion de oferta
		if(oferta.getCondicion() != null)
		{
			CondicionOferta condicion = oferta.getCondicion();
			condicion.setOidOferta(oferta.getOid());
			
			mantenimientoPEDConfiguracionOfertaDAO.insertCondicionOferta(condicion, usuario);
			
			//Rangos
			if(oferta.getRangosPromocion() != null && oferta.getRangosPromocion().size() > 0)
			{
				for(int i=0; i<oferta.getRangosPromocion().size(); i++)
				{
					RangoPromocion rangoPromocion = (RangoPromocion)oferta.getRangosPromocion().get(i);
					rangoPromocion.setOidPromocion(condicion.getOid());
					mantenimientoPEDConfiguracionOfertaDAO.insertRangoPromocion(rangoPromocion, usuario);
				}
			}
			//			
		}
		//
		
	}
	
}
