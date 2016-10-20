/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionCalculo;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionClasificacion;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionEscalonada;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisiones;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosRegionesSubGerencia;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ZonaDemandaAnticipada;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;


/**
 * @author 
 *
 */
@Repository("spusicc.mantenimientoCOMComisionDAO")
public class MantenimientoCOMComisionDAOIbatis extends
		BaseDAOiBatis implements MantenimientoCOMComisionDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComisionesList(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * retorna la lista de comisiones
	 */
	public List getComisionesList(CalificacionComisionCabecera calificacionComisionCabecera){
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComisionesList",calificacionComisionCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getNivelList(java.lang.String)
	 * retorna la lista de niveles
	 */
	public List getNivelList(String codigo) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getNivelList",codigo);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteComisionCabecera(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * Borra la cabecera de la comisin 
	 */
	public void deleteComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteComisionCabecera", calificacionComisionCabecera);	
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * Borra el detalle de la comisin 
	 */
	public void deleteComisionDetalle(
			CalificacionComisionCabecera calificacionComisionCabecera) {
			getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteComisionDetalle", calificacionComisionCabecera);
		
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComisionDetalleList(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * retorna la lista de detalle de comisin
	 */
	public List getComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComisionDetalleList",calificacionComisionCabecera);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getSecuenciaNextValue()
	 * Valido para el codigo de la comisin - se autogenera un secuencial
	 */
	public String getSecuenciaNextValue() {
		 return (String) getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getSecuenciaNextValue", null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#updateComisionCabeceraAndDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * actualiza cabecera y detalle de la comisin
	 */
	public void updateComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateComisionCabeceraAndDetalle", calificacionComisionCabecera);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertComisionCabecera(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * Agrega cabecera de la comisin
	 */
	public void insertComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertComisionCabecera", calificacionComisionCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Agrega detalle de la comisin
	 */
	public void insertComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertComisionDetalle", calificacionComisionDetalle);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#updateComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Actualiza detalle de la comisin
	 */
	public void updateComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		 getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateComisionDetalle", calificacionComisionDetalle);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteComisionDetal(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Borra detalle de la comisin
	 */
	public void deleteComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteComisionDetal", calificacionComisionDetalle);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComisionDetalleList(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * retorna la lista de detalle de comisin
	 */
	public List getComisionDetalleList(CalificacionComisionDetalle calificacionComisionDetalle) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComisionDetalleLista",calificacionComisionDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getZonasDemandaAnticipadaDetalle(java.util.Map)
	 */
	public List getZonasDemandaAnticipadaDetalle(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getZonasDemandaAnticipadaDetalle",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertZonasDemandaAnticipada(java.util.Map)
	 */
	public void insertZonasDemandaAnticipada(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertZonasDemandaAnticipada",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#updateZonasDemandaAnticipada(java.util.Map)
	 */
	public void updateZonasDemandaAnticipada(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateZonasDemandaAnticipada",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteZonasDemandaAnticipada(java.util.Map)
	 */
	public void deleteZonasDemandaAnticipada(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.deleteZonasDemandaAnticipada",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getZonaDemandaAnticipada(java.util.Map)
	 */
	public ZonaDemandaAnticipada getZonaDemandaAnticipada(Map criteria) {
		return (ZonaDemandaAnticipada)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getZonaDemandaAnticipada",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getValidarZonasDemanta(java.util.Map)
	 */
	public int getValidarZonasDemanda(Map criteria) {
		Integer zona = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getValidarZonasDemanta",criteria); 
		return zona.intValue();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getValidaExisteZona(java.util.Map)
	 */
	public int getValidaExisteZona(Map criteria) {
		Integer zona = (Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getValidaExisteZona",criteria); 
		return zona.intValue();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComisionesRecuperacionDetalle(java.util.Map)
	 */
	public List getComisionesRecuperacionDetalle(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComisionesRecuperacionDetalle",criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getCodigoComisionSiguiente()
	 */
	public String getCodigoComisionSiguiente() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getCodigoComisionSiguiente");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertComisiones(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosComisiones)
	 */
	public void insertComisiones(DatosComisiones datosComisiones) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertComisiones",datosComisiones);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertDetalleComisiones(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosRegionesSubGerencia)
	 */
	public void insertDetalleComisiones(DatosRegionesSubGerencia datosRegionesSubGerencia) {
		String codigoZona = datosRegionesSubGerencia.getCodigoZona();
		if (StringUtils.isBlank(codigoZona))
			getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertDetalleComisiones",datosRegionesSubGerencia);
		else
			getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertDetalleComisionesZona",datosRegionesSubGerencia);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getOidComisiones(java.util.Map)
	 */
	public int getOidComisiones(Map criteria) {
		Integer oidComision =(Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getOidComisiones",criteria);
		return oidComision.intValue(); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertComisionCalculada(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosComisionCalculo)
	 */
	public void insertComisionCalculada(DatosComisionCalculo datosComisionCalculo) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertComisionCalculada",datosComisionCalculo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertComisionEscalonada(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosComisionEscalonada)
	 */
	public void insertComisionEscalonada(DatosComisionEscalonada datosComisionEscalonada) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertComisionEscalonada",datosComisionEscalonada);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertComisionCobranza(java.util.Map)
	 */
	public void insertComisionCobranza(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.insertComisionCobranza",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getOidCobranzaComisiones(java.util.Map)
	 */
	public int getOidCobranzaComisiones(Map criteria) {
		Integer oidCobranza =(Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getOidCobranzaComisiones",criteria);
		return oidCobranza.intValue(); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getDatosComisiones(java.util.Map)
	 */
	public DatosComisiones getDatosComisionRecuperacion(Map criteria) {
		return (DatosComisiones)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getDatosComisionRecuperacion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#updateComisiones(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosComisiones)
	 */
	public void updateComisiones(DatosComisiones datosComisiones) {
		getSqlMapClientTemplate().update("spusicc.comision.mantenimientoCOMSQL.updateComisiones",datosComisiones);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getDatosComisionesZonasSubGerencia(java.util.Map)
	 */
	public List getDatosComisionesZonasSubGerencia(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getDatosComisionesZonasSubGerencia",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getRegionesSubGerencia(java.util.Map)
	 */
	public List getDatosComisiones(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getDatosComisiones",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComisionEscalonada(java.util.Map)
	 */
	public List getComisionEscalonada(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComisionEscalonada",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComisionesCobranza(java.util.Map)
	 */
	public Integer getCantCobranza(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getCantCobranza",criteria);	} 
		 

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteZonasSubGerencia(java.util.Map)
	 */
	public void deleteZonasSubGerencia(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteZonasSubGerencia",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteDatosComisiones(java.util.Map)
	 */
	public void deleteDatosComisiones(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteDatosComisiones",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteDatosEscalonada(java.util.Map)
	 */
	public void deleteDatosEscalonada(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteDatosEscalonada",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteDatosCobranza(java.util.Map)
	 */
	public void deleteDatosCobranza(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteDatosCobranza",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteComisionRecuperacion(java.util.Map)
	 */
	public void deleteComisionRecuperacion(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteComisionRecuperacion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getCantComisionesCalculadasXRegion(java.util.Map)
	 */
	public int getCantComisionesCalculadasXRegion(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getCantComisionesCalculadasXRegion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getCantComisionesCalculadasxZona(java.util.Map)
	 */
	public int getCantComisionesCalculadasxZona(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getCantComisionesCalculadasxZona",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getCantComisionesCalculadas(java.util.Map)
	 */
	public int getCantComisionesCalculadas(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getCantComisionesCalculadas",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getBasesComision(java.lang.String)
	 */
	public List getBasesComision(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getBasesComision", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComisionCalcuRegionList(java.util.Map)
	 */
	public List getComisionCalcuRegionList(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComisionCalcuRegionList", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComisionVentaNetaEfectivaList(java.util.Map)
	 */
	public List getComisionVentaNetaEfectivaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComisionVentaNetaEfectivaList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getTipoComiList(java.util.Map)
	 */
	public List getTipoComiList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getTipoComiList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComDatosAdam()
	 */
	public List getComDatosAdam(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComDatosAdam",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getComArchivoNominaList(java.util.Map)
	 */
	public List getComArchivoNominaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getComArchivoNominaList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getTipoClasificacion()
	 */
	public List getTipoClasificacionList() {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getTipoClasificacionList",null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getClasificacionComisionList(java.util.Map)
	 */
	public List getClasificacionComisionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getClasificacionComisionList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getClasificacionByCodigoComisionList(java.util.Map)
	 */
	public List getClasificacionByCodigoComisionList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getClasificacionByCodigoComisionList",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#deleteClasificacion(java.util.Map)
	 */
	public void deleteClasificacion(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.comision.mantenimientoCOMSQL.deleteClasificacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#insertClasificacion(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosComisionClasificacion)
	 */
	public void insertClasificacion(
			DatosComisionClasificacion datosComisionClasificacion) {
		getSqlMapClientTemplate().insert("spusicc.comision.mantenimientoCOMSQL.insertClasificacion", datosComisionClasificacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getListaMostrarGerentesRetiradas(java.util.Map)
	 */
	public List getListaMostrarGerentesRetiradas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.comision.mantenimientoCOMSQL.getListaMostrarGerentesRetiradas",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.dao.MantenimientoCOMComisionDAO#getCodigoBaseComision(java.util.Map)
	 */
	public String getCodigoBaseComision(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.comision.mantenimientoCOMSQL.getCodigoBaseComision",criteria);
	}
}
