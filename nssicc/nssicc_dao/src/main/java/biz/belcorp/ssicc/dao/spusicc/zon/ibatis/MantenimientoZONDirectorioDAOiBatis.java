package biz.belcorp.ssicc.dao.spusicc.zon.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONDirectorioDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.HistoricoDirectorioVenta;
import biz.belcorp.ssicc.dao.spusicc.zon.model.PerfilDirectorio;
import biz.belcorp.ssicc.dao.spusicc.zon.model.RolDirectorio;

/**
 * Implementacion del DAO que ejecutara los metodos de proceso de unidades administrativas
 * <p>
 * <a href="MantenimientoZONDirectorioDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Repository("spusicc.mantenimientoZONDirectorioDAO")
public class MantenimientoZONDirectorioDAOiBatis extends BaseDAOiBatis implements
			MantenimientoZONDirectorioDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getTipoCargo(java.util.Map)
	 */
	public List getTipoCargo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getTipoCargo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#deleteCargo(java.util.Map)
	 */
	public void deleteCargo(Map map) {
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteCargo", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDirectorioVentas(java.util.Map)
	 */
	public List getDirectorioVentas(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getDirectorioVentas", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#insertCargo(java.util.Map)
	 */
	public void insertCargo(Map map) {
		getSqlMapClientTemplate().insert("spusicc.zon.MantenimientoZONSQL.insertCargo", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateCargo(java.util.Map)
	 */
	public void updateCargo(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateCargo", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#executeValidacionDirectorio(java.util.Map)
	 */
	public void executeValidacionDirectorio(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeValidacionDirectorio", map);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDirectorioVentasDetalle(java.util.Map)
	 */
	public List getDirectorioVentasDetalle(Map mapCabecera) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getDirectorioVentasDetalle", mapCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#executeValidacionDirectorioNombramiento(java.util.Map)
	 */
	public void executeValidacionDirectorioNombramiento(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeValidacionDirectorioNombramiento", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getResponsableRegionZona(java.util.Map)
	 */
	public Map getResponsableRegionZona(Map map) {
		return (Map)getSqlMapClientTemplate().
						queryForObject("spusicc.zon.MantenimientoZONSQL." +
														"getResponsableRegionZona", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getSubgerenciaRegion(java.util.Map)
	 */
	public String getSubgerenciaRegion(Map map) {
		return (String)getSqlMapClientTemplate().
		queryForObject("spusicc.zon.MantenimientoZONSQL." +
										"getSubgerenciaRegion", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getSubgerenciaZona(java.util.Map)
	 */
	public String getSubgerenciaZona(Map map) {
		return (String)getSqlMapClientTemplate().
		queryForObject("spusicc.zon.MantenimientoZONSQL." +
										"getSubgerenciaZona", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#executeRotacionDirectorio(java.util.Map)
	 */
	public void executeRotacionDirectorio(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeRotacionDirectorio", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getMotivoLicencia(java.util.Map)
	 */
	public List getMotivoLicencia(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getMotivoLicencia",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#executeLicenciaPersona(java.util.Map)
	 */
	public void executeLicenciaPersona(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeLicenciaPersona", map);
		
	}

	public void executeReactivacionLicenciaPersona(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeReactivacionLicenciaPersona", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#executeRetiroDirectorio(java.util.Map)
	 */
	public void executeRetiroDirectorio(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeRetiroDirectorio", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getOperaciones(java.util.Map)
	 */
	public List getOperaciones(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getOperaciones",map);
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateParametroRutaEnvioCorreo(java.util.Map)
	 */
	public void updateParametroRutaEnvioCorreo(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateParametroRutaEnvioCorreo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getLicenciasActivas(java.util.Map)
	 */
	public List getLicenciasActivas(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getLicenciasActivas", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#executeReactivacionLicencia(java.util.Map)
	 */
	public void executeReactivacionLicencia(Map params) {
		String codigoConexionExterna = MapUtils.getString(params, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeReactivacionLicenciaFOX", params);
		else	
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeReactivacionLicencia", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#executeReactivacionLicencia(java.util.Map)
	 */
	public void executeReactivacionGerentesFuturas(Map params) {
		String codigoConexionExterna = MapUtils.getString(params, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeReactivacionGerentesFuturasFOX", params);
		else	
			getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeReactivacionGerentesFuturas", params);
	}

	public List getDescripcionCargo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getDescripcionCargo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getClientesByCriteria(java.util.Map)
	 */
	public List getClientesByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getClientesByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getLicenciaList(java.util.Map)
	 */
	public List getLicenciaList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getLicenciaList",criteria);
	}
	
	public void executeAprobarOperacion(Map params) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.executeAprobarOperacion", params);
		
	}

	public List getAprobarOperacionIN(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getAprobarOperacionIN", criteria);
	}

	public List getAprobarOperacionLI(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getAprobarOperacionLI", criteria);
	}

	public List getAprobarOperacionNM(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getAprobarOperacionNM", criteria);
	}

	public List getAprobarOperacionRE(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getAprobarOperacionRE", criteria);
	}

	public List getAprobarOperacionRO(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getAprobarOperacionRO", criteria);
	}

	public List getRegionesDirectorioNoTitular(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRegionesDirectorioNoTitular", criteria);
	}
	
	public List getRegionesDirectorioTitular(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRegionesDirectorioTitular", criteria);
	}

	public Cliente getExisteCodigoClienteDirectorioByCodPais(Map criteria) {
		return (Cliente) getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getExisteCodigoClienteDirectorioByCodPais", criteria);
	}
	
	public Cliente getExisteCodigoClienteDirectorioReemplazoByCodPais(Map criteria) {
		return (Cliente) getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getExisteCodigoClienteDirectorioReemplazoByCodPais", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getClientesReemplazoByCriteria(java.util.Map)
	 */
	public List getClientesReemplazoByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getClientesReemplazoByCriteria", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getRegionesZonasDirectorioID(java.util.Map)
	 */
	public List getRegionesZonasDirectorioID(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRegionesZonasDirectorioID", criteria);
	}

	public List getListaZonasDirectorioZON(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getListaZonasDirectorioZON", criteria);
	}

	public List getListaZonasDirectorioNoTitular(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getListaZonasDirectorioNoTitular", criteria);
	}

	public List getListaZonasDirectorioTitular(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getListaZonasDirectorioTitular", criteria);
	}

	public String getDescripcionCargoZON(String oidCliente) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getDescripcionCargoZON", oidCliente);
	}

	public List getUnidadAdministrativaZON(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getUnidadAdministrativaZON", criteria);
	}

	public void deleteLicenciaCab(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteLicenciaCab", criteria);
		
	}
	
	public void deleteLicenciaDet(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteLicenciaDet", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDirectorioVentasList(java.util.Map)
	 */
	public List getDirectorioVentasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getDirectorioVentasList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#obtenerDatosConsultora(java.util.Map)
	 */
	public String obtenerDatosConsultora(Map criteria) {

		String ret = "";
		
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			ret = (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.obtenerDatosConsultoraFOX", criteria);
		else	
			ret = (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.obtenerDatosConsultora", criteria);
		
		return ret;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#obtenerDatosConsultoraAsignarCargo(java.util.Map)
	 */
	public String obtenerDatosConsultoraAsignarCargo(Map criteria) {
		String ret = "";
		
		List lista = null;
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			lista = getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.obtenerDatosConsultoraAsignarCargoFOX", criteria);
		else	
			lista = getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.obtenerDatosConsultoraAsignarCargo", criteria);
		
		if(lista != null && lista.size() > 0)
		{
			Base obj = (Base)lista.get(0);
			
			ret = obj.getDescripcion();
		}
		
		return ret; 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getVerificarCargoTitular(java.util.Map)
	 */
	public String getVerificarCargoTitular(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getVerificarCargoTitular", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getObtenerGerentexTipo(java.util.Map)
	 */
	public String getObtenerGerentexTipo(Map criteria) {
		String ret = "";
		
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			ret = (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getObtenerGerentexTipoFOX", criteria);
		else	
			ret = (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getObtenerGerentexTipo", criteria);
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getExisteUsuarioDirectorioVenta(java.util.Map)
	 */
	public boolean getExisxxxxteUsuarioDirectorioVenta(Map criteria) {
		String resultado= (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getExisteUsuarioDirectorioVenta", criteria);
		if (resultado.equals("1")) {
			return true;
		}else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getExisteUsuarioDirectorioVenta(java.util.Map)
	 */
	public String getExisteUsuarioDirectorioVenta(Map criteria) {
		String resultado = (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getExisteUsuarioDirectorioVenta", criteria);
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#insertDirectorioVentaCabecera(java.util.Map)
	 */
	public void insertDirectorioVentaCabecera(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.zon.MantenimientoZONSQL.insertDirectorioVentaCabecera", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#insertDirectorioVentaDetalle(java.util.Map)
	 */
	public void insertDirectorioVentaDetalle(Map criteria) {
		getSqlMapClientTemplate().insert(
				"spusicc.zon.MantenimientoZONSQL.insertDirectorioVentaDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateDirectorioVentaCabecera(java.util.Map)
	 */
	public void updateDirectorioVentaCabecera(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.zon.MantenimientoZONSQL.updateDirectorioVentaCabecera", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDirectorioVentaCabeceraActivo(java.util.Map)
	 */
	public List getDirectorioVentaCabeceraActivo(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getDirectorioVentaCabeceraActivo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateDirectorioVentaDetalle(java.util.Map)
	 */
	public void updateDirectorioVentaDetalle(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.zon.MantenimientoZONSQL.updateDirectorioVentaDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getValidaRegionxZona(java.util.Map)
	 */
	public int getValidaRegionxZona(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getValidaRegionxZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getValidarRegistroExiste(java.util.Map)
	 */
	public boolean getValidarRegistroExiste(Map criteria) {
		String resultado = (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getValidarRegistroExiste", criteria);
		
		if (resultado.equals("1")) {
			return true;
		}else {
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getValidaEstadoCargo(java.util.Map)
	 */
	public Map getValidaEstadoCargo(Map criteria) {
		Map resultado = (Map)getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getValidaEstadoCargo", criteria);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getObtenerDatosCorreos(java.util.Map)
	 */
	public List getObtenerDatosCorreos(Map criteria) {
		return (List)getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getObtenerDatosCorreos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateParametrosCorreo(java.util.Map)
	 */
	public void updateParametrosCorreo(Map criteria) {
		getSqlMapClientTemplate().update(
				"spusicc.zon.MantenimientoZONSQL.updateParametrosCorreo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#obtenerDirectorioVentaCabecera(java.util.Map)
	 */
	public Map obtenerDirectorioVentaCabecera(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.obtenerDirectorioVentaCabecera", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#obtenerDirectorioVentaDetalle(java.util.Map)
	 */
	public Map obtenerDirectorioVentaDetalle(Map criteria) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.obtenerDirectorioVentaDetalle", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getTipoLicenciaList()
	 */
	public List getTipoLicenciaList() {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getTipoLicenciaList");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDatosConsultoraReemplazo(java.util.Map)
	 */
	public String getDatosConsultoraReemplazo(Map criteria) {
		
		String ret = "";
		
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			ret = (String)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getDatosConsultoraReemplazoFOX", criteria);
		else
			ret = (String)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getDatosConsultoraReemplazo", criteria);
		
		return ret;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDatosUnidaddesAdministrativasConsultora(java.util.Map)
	 */
	public List getDatosUnidaddesAdministrativasConsultora(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getDatosUnidaddesAdministrativasConsultora",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDatosCargoConsultoraReemplazo(java.util.Map)
	 */
	public List getDatosCargoConsultoraReemplazo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getDatosCargoConsultoraReemplazo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getValidacionConsultoraAptoLicencia(java.util.Map)
	 */
	public String getValidacionConsultoraAptoLicencia(Map criteria) {
		return (String)getSqlMapClientTemplate().
				queryForObject("spusicc.zon.MantenimientoZONSQL.getValidacionConsultoraAptoLicencia", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getCargosList()
	 */
	public List getCargosList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getCargosList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getZonDirecVentaCabecList(java.util.Map)
	 */
	public List getZonDirecVentaCabecList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getZonDirecVentaCabecList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getZonDirecVentaDetalList(java.util.Map)
	 */
	public List getZonDirecVentaDetalList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getZonDirecVentaDetalList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getRegionesDisp()
	 */
	public List getRegionesDisp(Map map) {
		
		List ret = null;
		String codigoConexionExterna = MapUtils.getString(map, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			ret = getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRegionesDispFOX", map);
		else	
			ret = getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRegionesDisp", map);
				
		return ret;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getZonaDisp(java.util.Map)
	 */
	public List getZonaDisp(Map criteria) {
		List ret = null;

		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			ret = getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getZonaDispFOX", criteria);
		else	
			ret = getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getZonaDisp", criteria);
				
		return ret;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getHistoricoList(java.util.Map)
	 */
	public List getHistoricoList(Map criteria) {
		List ret = null;
		
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			ret = getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getHistoricoListFOX", criteria);
		else	
			ret = getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getHistoricoList", criteria);
				
		return ret;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getZonDirecVentaDetalSoloList(java.util.Map)
	 */
	public List getZonDirecVentaDetalSoloList(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getZonDirecVentaDetalSoloList",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getMaeCliente(java.util.Map)
	 */
	public List getMaeCliente(Map map) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getMaeCliente",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getResponsableUA(java.util.Map)
	 */
	public List getResponsableUA(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getResponsableUA",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDirectorioHistorioNovedades(java.util.Map)
	 */
	public List getDirectorioHistorioNovedades(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getDirectorioHistorioNovedades",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getConsultarDirectorioVentas(java.util.Map)
	 */
	public List getConsultarDirectorioVentas(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getConsultarDirectorioVentas",criteria);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getConsultarDirectorioVentasFOX(java.util.Map)
	 */
	public List getConsultarDirectorioVentasFOX(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getConsultarDirectorioVentasFOX",criteria);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getTipoCargoList()
	 */
	public List getTipoCargoList() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.zon.MantenimientoZONSQL.getTipoCargoList",null);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getValidarTipoCargoDirectorioVenta(java.util.Map)
	 */
	public int getValidarTipoCargoDirectorioVenta(Map criteria) {			
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getValidarTipoCargoDirectorioVenta", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateGerenciaZona(java.util.Map)
	 */
	public void updateGerenciaZona(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateGerenciaZona", criteria);				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateGerenciaRegion(java.util.Map)
	 */
	public void updateGerenciaRegion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateGerenciaRegion", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#insertHistoricoGerenteZonaRegion(java.util.Map)
	 */
	public void insertHistoricoGerenteZonaRegion(Map criteria) {
		
		//El insert; debe de eliminar el que existe con los mismos valores para evitar el HGER_UK
		this.deleteHistoricoGerenteByCliente(criteria);
		//
		
		getSqlMapClientTemplate().insert("spusicc.zon.MantenimientoZONSQL.insertHistoricoGerenteZonaRegion", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getLastHistoricoGerenteByCliente(java.util.Map)
	 */
	public List getLastHistoricoGerenteByCliente(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getLastHistoricoGerenteByCliente",criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateLastHistoricoGerenteByCliente(java.util.Map)
	 */
	public void updateLastHistoricoGerenteByCliente(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateLastHistoricoGerenteByCliente", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#deleteHistoricoGerenteByCliente(java.util.Map)
	 */
	public void deleteHistoricoGerenteByCliente(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteHistoricoGerenteByCliente", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getCorrelativoCabeceraDirectorio()
	 */
	public String getCorrelativoCabeceraDirectorio() {
		String id = "";
		Long correlativo = (Long)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getCorrelativoCabeceraDirectorio");
		
		if(correlativo != null)
			id = correlativo.toString();
		
		return id;
			
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateGerenciaZonaFOX(java.util.Map)
	 */
	public void updateGerenciaZonaFOX(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateGerenciaZonaFOX", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#insertHistoricoGerenteZonaRegionFOX(java.util.Map)
	 */
	public void insertHistoricoGerenteZonaRegionFOX(Map criteria) {
		//El insert; debe de eliminar el que existe con los mismos valores para evitar el HGER_UK
		this.deleteHistoricoGerenteByClienteFOX(criteria);
		//
		
		getSqlMapClientTemplate().insert("spusicc.zon.MantenimientoZONSQL.insertHistoricoGerenteZonaRegionFOX", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#deleteHistoricoGerenteByClienteFOX(java.util.Map)
	 */
	public void deleteHistoricoGerenteByClienteFOX(Map criteria) {
		getSqlMapClientTemplate().delete("spusicc.zon.MantenimientoZONSQL.deleteHistoricoGerenteByClienteFOX", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateGerenciaRegionFOX(java.util.Map)
	 */
	public void updateGerenciaRegionFOX(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateGerenciaRegionFOX", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getLastHistoricoGerenteByClienteFOX(java.util.Map)
	 */
	public List getLastHistoricoGerenteByClienteFOX(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getLastHistoricoGerenteByClienteFOX",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateLastHistoricoGerenteByClienteFOX(java.util.Map)
	 */
	public void updateLastHistoricoGerenteByClienteFOX(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateLastHistoricoGerenteByClienteFOX", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getValidaRegionxZonaFOX(java.util.Map)
	 */
	public int getValidaRegionxZonaFOX(Map criteria) {
		return (Integer)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getValidaRegionxZonaFOX", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getPerfil(java.lang.String)
	 */
	public PerfilDirectorio getPerfil(String id) {
		PerfilDirectorio perfil = (PerfilDirectorio)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getPerfil", id);
		
		if(perfil == null)
			throw new ObjectRetrievalFailureException(PerfilDirectorio.class, id);
				
		return perfil;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getPerfilesByCriteria(java.util.Map)
	 */
	public List getPerfilesByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getPerfilesByCriteria",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getRol(java.lang.String)
	 */
	public RolDirectorio getRol(String id) {
		
		RolDirectorio rol = (RolDirectorio)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getRol", id);
		
		if(rol == null)
			throw new ObjectRetrievalFailureException(RolDirectorio.class, id);
				
		return rol;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getRolesByCriteria(java.util.Map)
	 */
	public List getRolesByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getRolesByCriteria",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updatePerfil(biz.belcorp.ssicc.spusicc.zon.model.PerfilDirectorio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePerfil(PerfilDirectorio perfil, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updatePerfil", perfil);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#updateRol(biz.belcorp.ssicc.spusicc.zon.model.RolDirectorio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRol(RolDirectorio rol, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateRol", rol);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#insertPerfil(biz.belcorp.ssicc.spusicc.zon.model.PerfilDirectorio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPerfil(PerfilDirectorio perfil, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.insertPerfil", perfil);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#insertRol(biz.belcorp.ssicc.spusicc.zon.model.RolDirectorio, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRol(RolDirectorio rol, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.insertRol", rol);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getDetalleDatosCliente(java.util.Map)
	 */
	public Map getDetalleDatosCliente(Map params) {
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getDetalleDatosCliente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getHistoricoDirectorioVenta(java.util.Map)
	 */
	public HistoricoDirectorioVenta getHistoricoDirectorioVenta(Map params) {
		return (HistoricoDirectorioVenta)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getHistoricoDirectorioVenta", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#insertHistoricoDirectorioVenta(biz.belcorp.ssicc.spusicc.zon.model.HistoricoDirectorioVenta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoDirectorioVenta(HistoricoDirectorioVenta historico, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.insertHistoricoDirectorioVenta", historico);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#actualizarEnviosMav(java.util.Map)
	 */
	public void actualizarEnviosMav(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.actualizarEnviosMav", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getCodigoCargosList(java.util.Map)
	 */
	public List getCodigoCargosList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getCodigoCargosList",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getTipoCargoFuturas(java.util.Map)
	 */
	public Map getTipoCargoFuturas(Map criteria){
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getTipoCargoFuturas",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getValidarFechaIngreso(java.util.Map)
	 */
	public String getValidarFechaIngreso(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getValidarFechaIngreso", criteria));
	}

	public String getValidarCruceFechaGeren(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getValidarCruceFechaGeren", criteria));
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getVerificaClienteSSiCC(java.util.Map)
	 */
	public String getVerificaClienteSSiCC(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getVerificaClienteSSiCC",criteria);
	}
	 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getVerificaClienteFOX(java.util.Map)
	 */
	public String getVerificaClienteFOX(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getVerificaClienteFOX",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getObtenerAsignacionesConsultoraSSiCC(java.util.Map)
	 */
	public List getObtenerAsignacionesConsultoraSSiCC(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getObtenerAsignacionesConsultoraSSiCC",criteria);
	}
	 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getObtenerAsignacionesConsultoraFOX(java.util.Map)
	 */
	public List getObtenerAsignacionesConsultoraFOX(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getObtenerAsignacionesConsultoraFOX",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getValidarFechaIngresoFOX(java.util.Map)
	 */
	public String getValidarFechaIngresoFOX(Map criteria) {
		return String.valueOf(getSqlMapClientTemplate().queryForObject(
				"spusicc.zon.MantenimientoZONSQL.getValidarFechaIngresoFOX", criteria));
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getPeriodoByFecha(java.util.Map)
	 */
	public String getPeriodoByFecha(Map criteria){
		String ret = "";
		
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			ret = (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getPeriodoByFechaFox",criteria);
		else	
			ret = (String) getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getPeriodoByFecha",criteria);
		
		return ret;
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#getControlFacturacionByCriteriaFOX(java.util.Map)
	 */
	public Map getControlFacturacionByCriteriaFOX(Map criteria){
		return (Map)getSqlMapClientTemplate().queryForObject("spusicc.zon.MantenimientoZONSQL.getControlFacturacionByCriteriaFOX",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.dao.MantenimientoZONDirectorioDAO#mostrarRolPerfil(java.util.Map)
	 */
	public List mostrarRolPerfil(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.zon.MantenimientoZONSQL.getTipoCargoRolPerfil",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONDirectorioDAO#updateEstadoDirectotioVenta(java.util.Map)
	 */
	public void updateEstadoDirectotioVenta(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateEstadoDirectotioVenta", map);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONDirectorioDAO#updateEstadoCargoDirectotioVenta(java.util.Map)
	 */
	public void updateEstadoCargoDirectotioVenta(Map map) {
		getSqlMapClientTemplate().update("spusicc.zon.MantenimientoZONSQL.updateEstadoCargoDirectotioVenta", map);
		
	}
}
