package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEMapaZonaLineaArmadoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEMapaZonaLineaArmadoService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEMapaZonaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEMapaZonaLineaArmadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEMapaZonaLineaArmadoServiceImpl extends BaseService implements MantenimientoAPEMapaZonaLineaArmadoService {

	@Resource(name="spusicc.mantenimientoAPEMapaZonaLineaArmadoDAO")
	private MantenimientoAPEMapaZonaLineaArmadoDAO mantenimientoAPEMapaZonaLineaArmadoDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getCodMapaZonaCabecera(java.util.Map)
	 */
	public String getCodMapaZonaCabecera(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getCodMapaZonaCabecera(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#insertarMapaZonaCabecera(java.util.Map)
	 */
	public void insertarMapaZonaCabecera(Map criteria) {
		this.mantenimientoAPEMapaZonaLineaArmadoDAO.insertarMapaZonaCabecera(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getOidMapaZona(java.util.Map)
	 */
	public String getOidMapaZona(Map criteria) {
		return (String)this.mantenimientoAPEMapaZonaLineaArmadoDAO.getOidMapaZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#insertarMapaZonaDetalle(java.util.Map)
	 */
	public void insertarMapaZonaDetalle(Map criteria) {
		this.mantenimientoAPEMapaZonaLineaArmadoDAO.insertarMapaZonaDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getMapaZonaxOidMapaCD(java.util.Map)
	 */
	public List getMapaZonaxOidMapaCD(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getMapaZonaxOidMapaCD(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getListaMapaZonaLineaArmado(java.util.Map)
	 */
	public List getListaMapaZonaLineaArmado(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getListaMapaZonaLineaArmado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getObtenerDetMapaZonaLineaArmado(java.util.Map)
	 */
	public List getObtenerDetMapaZonaLineaArmado(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getObtenerDetMapaZonaLineaArmado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getSubLineaComboList(java.util.Map)
	 */
	public List getSubLineaComboList(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getSubLineaComboList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getObtenerCodLineaArmadoxOidSubLinea(java.util.Map)
	 */
	public String getObtenerCodLineaArmadoxOidSubLinea(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getObtenerCodLineaArmadoxOidSubLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getObtenerCodigoCDxOidMapaCD(java.util.Map)
	 */
	public String getObtenerCodigoCDxOidMapaCD(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getObtenerCodigoCDxOidMapaCD(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getObtenerCodigoMapaCD(java.util.Map)
	 */
	public String getObtenerCodigoMapaCD(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getObtenerCodigoMapaCD(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getSubLineaporLineaList(java.util.Map)
	 */
	public List getSubLineaporLineaList(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getSubLineaporLineaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#updateMapaZonaCabecera(java.util.Map)
	 */
	public void updateMapaZonaCabecera(Map criteria) {
		this.mantenimientoAPEMapaZonaLineaArmadoDAO.updateMapaZonaCabecera(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#deleteLineaMapaZona(java.util.Map, java.lang.String[])
	 */
	public String deleteLineaMapaZona(Map criteria, String[] items) {
		String vNomTabla = new String();
		String error= new String();
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			criteria.put("oidMapaZona",id);
			
			this.mantenimientoAPEMapaZonaLineaArmadoDAO.deleteLineaMapaZona(criteria);
			
			error = (String) criteria.get("valError");
			
			if (Constants.NRO_CERO.equals(error)){
				vNomTabla ="1";
			}else{
				vNomTabla = (String) criteria.get("nomTabla");
				break;
			}
		}
		
		return vNomTabla;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#deleteMapaZonaxLineaArmado(java.util.Map)
	 */
	public void deleteMapaZonaxLineaArmado(Map criteria) {
		this.mantenimientoAPEMapaZonaLineaArmadoDAO.deleteMapaZonaxLineaArmado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getValidaExisteMapaZonaDefecto(java.util.Map)
	 */
	public String getValidaExisteMapaZonaDefecto(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getValidaExisteMapaZonaDefecto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getObtenerBahiaMayorxSubLinea(java.util.Map)
	 */
	public String getObtenerBahiaMayorxSubLinea(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getObtenerBahiaMayorxSubLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getObtenerDescripcionSubLinea(java.util.Map)
	 */
	public String getObtenerDescripcionSubLinea(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getObtenerDescripcionSubLinea(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getOidMapaCDxOidCentro(java.util.Map)
	 */
	public String getOidMapaCDxOidCentro(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getOidMapaCDxOidCentro(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getObtenerBahiasxSubLineaList(java.util.Map)
	 */
	public List getObtenerBahiasxSubLineaList(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getObtenerBahiasxSubLineaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEMapaZonaLineaArmadoService#getObtenerCantidadBahiasxSubLinea(java.util.Map)
	 */
	public String getObtenerCantidadBahiasxSubLinea(Map criteria) {
		return this.mantenimientoAPEMapaZonaLineaArmadoDAO.getObtenerCantidadBahiasxSubLinea(criteria);
	}

	
}
