package biz.belcorp.ssicc.dao.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.zon.model.HistoricoDirectorioVenta;
import biz.belcorp.ssicc.dao.spusicc.zon.model.PerfilDirectorio;
import biz.belcorp.ssicc.dao.spusicc.zon.model.RolDirectorio;

/**
 * Implementacion del DAO que ejecutara los metodos de proceso de unidades administrativas
 * <p>
 * <a href="MantenimientoZONDirectorioDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
public interface MantenimientoZONDirectorioDAO extends DAO {

	/**Retorn la lista de cargo
	 * @param criteria
	 * @return
	 */
	List getTipoCargo(Map criteria);

	/**
	 * Elimina cargo
	 * @param map
	 */
	void deleteCargo(Map map);
	
	/**
	 * Devuelvela cabcera del directorio de ventas
	 * @param map
	 * @return
	 */
	List getDirectorioVentas(Map map);

	/**
	 * Actualiza el cargo
	 * @param map
	 */
	void updateCargo(Map map);

	/**
	 * Inserta el cargo
	 * @param map
	 */
	void insertCargo(Map map);
	
	/**
	 * Ejecuta las validaciones del directorio
	 * @param map
	 */
	void executeValidacionDirectorio(Map map);
	
	/**
	 * Retona la lista del detalle de directorios 
	 * @param mapCabecera
	 * @return
	 */
	List getDirectorioVentasDetalle(Map mapCabecera);

	/**
	 * Ejecuta las validaciones del directorio de nombramiento
	 * @param map
	 */
	void executeValidacionDirectorioNombramiento(Map map);

	/**
	 * Devuelve el nombre de la responsable de la region o zona segun sea caso
	 * @param map
	 * @return
	 */
	Map getResponsableRegionZona(Map map);

	/**
	 * Retorna la subgerencia desde la zona
	 * @param map
	 * @return
	 */
	String getSubgerenciaZona(Map map);

	/**
	 * Retorna la subgerencia desde la region
	 * @param map
	 * @return
	 */
	String getSubgerenciaRegion(Map map);
	
	/**
	 * Ejecuta la rotacion de personas
	 * @param map
	 */
	void executeRotacionDirectorio(Map map);
	
	/**
	 * Recupera todos los motivos de licencia 
	 * @param Map
	 * @return
	 */
	List getMotivoLicencia(Map map);

	/**
	 * Ejecita la accin de licencia
	 * @param map
	 */
	void executeLicenciaPersona(Map map);
	
	/**Realiza la accion de reactivacion de licencia
	 * @param map
	 */
	void executeReactivacionLicenciaPersona(Map map);
	
	/**
	 * Realiza el retiro del directorio d euna consultora
	 * @param map
	 */
	void executeRetiroDirectorio(Map map);

	/**
	 * Retorn las operaciones del directorio
	 * @param map
	 * @return
	 */
	List getOperaciones(Map map);
	
	/**
	 * @param criteria
	 */
	void updateParametroRutaEnvioCorreo(Map criteria);

	/**
	 * @param map
	 * @return
	 */
	List getLicenciasActivas(Map map);

	/**
	 * @param params
	 */
	void executeReactivacionLicencia(Map params);

	/**
	 * @param params
	 */
	void executeReactivacionGerentesFuturas(Map params);
	
	/**
	 * @param criteria
	 * @return
	 */
	List getDescripcionCargo(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getClientesByCriteria(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getLicenciaList(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	void executeAprobarOperacion(Map criteria);	
	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionIN(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionRE(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionLI(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionNM(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionRO(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getRegionesDirectorioNoTitular(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getRegionesDirectorioTitular(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	Cliente getExisteCodigoClienteDirectorioByCodPais(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	Cliente getExisteCodigoClienteDirectorioReemplazoByCodPais(Map criteria);


	/**
	 * @param criteria
	 * @return
	 */
	List getClientesReemplazoByCriteria(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	List getRegionesZonasDirectorioID(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	List getListaZonasDirectorioNoTitular(Map criteria);

	
	/**
	 * @param criteria
	 * @return
	 */
	List getListaZonasDirectorioTitular(Map criteria);

	/**
	 * @param codigoCliente
	 * @return
	 */
	String getDescripcionCargoZON(String oidCliente);

	/**
	 * @param oidCliente
	 * @return
	 */
	List getUnidadAdministrativaZON(Map criteria);

	/**
	 * @param criteria
	 */
	void deleteLicenciaCab(Map criteria);
	
	/**
	 * @param criteria
	 */
	void deleteLicenciaDet(Map criteria);

	List getDirectorioVentasList(Map criteria);

	List getCargosList(Map criteria);

	List getZonDirecVentaCabecList(Map criteria);

	List getZonDirecVentaDetalList(Map criteria);

	List getRegionesDisp(Map map);

	List getZonaDisp(Map map);
	
	List getHistoricoList(Map criteria);


	public String obtenerDatosConsultora(Map criteria);
	
	public String obtenerDatosConsultoraAsignarCargo(Map criteria);
	
	public String getVerificarCargoTitular(Map criteria);
	
	public String getObtenerGerentexTipo(Map criteria);
	
	public String getExisteUsuarioDirectorioVenta(Map criteria);
	
	public void insertDirectorioVentaCabecera(Map criteria);
	
	public void insertDirectorioVentaDetalle(Map criteria);
	
	public void updateDirectorioVentaCabecera(Map criteria);
	
	public List getDirectorioVentaCabeceraActivo(Map criteria);
	
	public void updateDirectorioVentaDetalle(Map criteria);
	
	public int getValidaRegionxZona(Map criteria);
	
	public boolean getValidarRegistroExiste(Map criteria);
	
	public Map getValidaEstadoCargo(Map criteria);
	
	public List getObtenerDatosCorreos(Map criteria);
	
	public void updateParametrosCorreo(Map criteroa);
	
	public Map obtenerDirectorioVentaCabecera(Map criteria);
	
	public Map obtenerDirectorioVentaDetalle(Map criteria);
	
	public List getTipoLicenciaList();
	
	public String getDatosConsultoraReemplazo(Map criteria);
	
	public List getDatosUnidaddesAdministrativasConsultora(Map criteria);
	
	public List getDatosCargoConsultoraReemplazo (Map criteria);
	
	public String getValidacionConsultoraAptoLicencia(Map criteria);

	List getZonDirecVentaDetalSoloList(Map map);

	List getMaeCliente(Map map);

	List getResponsableUA(Map criteria);
	
	public List getDirectorioHistorioNovedades(Map criteria);
	
	public List getConsultarDirectorioVentas(Map criteria);
	
	public List getConsultarDirectorioVentasFOX(Map criteria);
	
	public List getTipoCargoList();
	
	/**
	 * Retorna numero de clientes del directorio asociados a un cargos activo
	 * @param criteria
	 * @return
	 */
	public int getValidarTipoCargoDirectorioVenta(Map criteria);
	
	/**
	 * Actualiza la asignacion de la gerencia de Zona
	 * @param criteria
	 */
	public void updateGerenciaZona(Map criteria);
	
	/**
	 * Actualiza la asignacion de la gerencia de Region
	 * @param criteria
	 */
	public void updateGerenciaRegion(Map criteria);
	
	/**
	 * Inserta registro en el Historio de Gerentes de Zona y Region
	 * @param criteria
	 */
	public void insertHistoricoGerenteZonaRegion(Map criteria);
	
	/**
	 * Obtiene el registro historico de Gerentes
	 * @param criteria
	 * @return
	 */
	public List getLastHistoricoGerenteByCliente(Map criteria);
	
	/**
	 * Actualiza peridos de fin de historicos de Gerentes 
	 * @param criteria
	 */
	public void updateLastHistoricoGerenteByCliente(Map criteria);
	
	/**
	 * Elimina Historico de Gerencia de la misma campaa
	 * @param map
	 */
	void deleteHistoricoGerenteByCliente(Map criteria);
	
	/**
	 * Obtiene el correlativo de la cabecera del directorio
	 * @return
	 */
	public String getCorrelativoCabeceraDirectorio();
	
	/**
	 * Actualiza la tabla de zonas fox con los datos de la nueva gerente
	 * @param criteria
	 */
	void updateGerenciaZonaFOX(Map criteria);

	/**
	 * Registra en el historico los datos de la nueva gerente de zona
	 * @param criteria
	 */
	void insertHistoricoGerenteZonaRegionFOX(Map criteria);
	
	/**
	 * Elimina el historico de la zona
	 * @param criteria
	 */
	void deleteHistoricoGerenteByClienteFOX(Map criteria);

	/**
	 * Actualiza la tabla de regiones fox con los datos de la nueva gerente
	 * @param criteria
	 */
	void updateGerenciaRegionFOX(Map criteria);

	/**
	 * Obtiene el registro historico de Gerentes de paises FOX
	 * @param criteria
	 * @return
	 */
	List getLastHistoricoGerenteByClienteFOX(Map criteria);

	/**
	 * Actualiza peridos de fin de historicos de Gerentes de paises FOX
	 * @param criteria
	 */
	void updateLastHistoricoGerenteByClienteFOX(Map criteria);

	/**
	 * Valida si una zona pertenece a una region, paises FOX
	 * @param criteria
	 * @return
	 */
	int getValidaRegionxZonaFOX(Map criteria);

	/**
	 * Obtiene un listado de roles en base a los criterios de busqueda
	 * @param params
	 * @return
	 */
	List getRolesByCriteria(Map params);

	/**
	 * Obtiene un Rol por su id
	 * @param id
	 * @return
	 */
	RolDirectorio getRol(String id);

	/**
	 * Actualiza un rol
	 * @param rol
	 * @param usuario
	 */
	void updateRol(RolDirectorio rol, Usuario usuario);

	/**
	 * Obtienen una lista de perfiles en base a los criterios de busqueda
	 * @param params
	 * @return
	 */
	List getPerfilesByCriteria(Map params);

	/**
	 * Obtiene un perfil por su id
	 * @param id
	 * @return
	 */
	PerfilDirectorio getPerfil(String id);

	/**
	 * Actualiza un perfil
	 * @param perfil
	 * @param usuario
	 */
	void updatePerfil(PerfilDirectorio perfil, Usuario usuario);

	/**
	 * Inserta un Rol
	 * @param rol
	 * @param usuario
	 */
	void insertRol(RolDirectorio rol, Usuario usuario);

	/**
	 * Inserta un Perfil
	 * @param perfil
	 * @param usuario
	 */
	void insertPerfil(PerfilDirectorio perfil, Usuario usuario);

	/**
	 * Obtiene el historico de venta de un cliente
	 * @param params
	 * @return
	 */
	HistoricoDirectorioVenta getHistoricoDirectorioVenta(Map params);

	/**
	 * Inserta un historico en la tabla de historicos
	 * @param historico
	 * @param usuario
	 */
	void insertHistoricoDirectorioVenta(HistoricoDirectorioVenta historico, Usuario usuario);

	/**
	 * Obtiene los detalles de los datos de un cliente
	 * @param params
	 * @return
	 */
	Map getDetalleDatosCliente(Map params);
	
	/**
	 * Actualiza la tabla MAV_ENVIO_CONFI
	 * @param criteria
	 */
	public void actualizarEnviosMav(Map criteria);
	
	/**
	 * Obtiene una lista de codigo de Cargos Base
	 * @param criteria
	 * @return
	 */
	List getCodigoCargosList(Map criteria);
	
	/**
	 * Obtiene el codigo del rol con perfil titular, mediante el codigo de cargo dado. 
	 * @param criteria
	 * @return
	 */
	public Map getTipoCargoFuturas(Map criteria);
	
	 
	 /**
		 * Valida la fecha de ingreso por tipo de cargo (futuros).
		 * @param criteria
		 * @return
		 */
	 public String getValidarFechaIngreso(Map criteria);
	 
	 /**
		 * Valida el cruce de fecha Gerente.
		 * @param criteria
		 * @return
		 */
	 public String getValidarCruceFechaGeren(Map criteria);	 
	
	 /**
	 * Verifica si existe la consultora en el SSiCC
	 * @param criteria
	 * @return
	 */
	public String getVerificaClienteSSiCC(Map criteria);
	 
	 /**
	 * Verifica si existe la consultora en los Paises FOX
	 * @param criteria
	 * @return
	 */
	public String getVerificaClienteFOX(Map criteria);
	
	/**
	 * Devuelve Lista de Asignaciones para la Consultora SSiCC
	 * @param criteria
	 * @return
	 */
	public List getObtenerAsignacionesConsultoraSSiCC(Map criteria);
	
	/**
	 * Devuelve Lista de Asignaciones para la Consultora FOX
	 * @param criteria
	 * @return
	 */
	public List getObtenerAsignacionesConsultoraFOX(Map criteria);
	
	/**
	 * Valida la fecha de ingreso por tipo de cargo (futuros). FOX
	 * @param criteria
	 * @return
	 */
     public String getValidarFechaIngresoFOX(Map criteria);
     
     /**
 	 * Devuelve la campaa dada una fecha.
 	 * @param criteria
 	 * @return
 	 */
     public String getPeriodoByFecha(Map criteria);
     
     /**
  	 * Devuelve el control de facturacin de FOX.
  	 * @param criteria
  	 * @return
  	 */
     public Map getControlFacturacionByCriteriaFOX(Map criteria);
     
     /**
   	 * Devuelve el rol,perfil dado un cargo.
   	 * @param criteria
   	 * @return
   	 */
     public List mostrarRolPerfil(Map criteria);
     
     /**
 	 * Actualiza el estado del directorio de venta
 	 * @param map
 	 */
 	void updateEstadoDirectotioVenta(Map map);
 	
 	/**
 	 * Actualiza el estado del directorio de venta
 	 * @param map
 	 */
 	void updateEstadoCargoDirectotioVenta(Map map);
 	
}
