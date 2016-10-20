package biz.belcorp.ssicc.service.spusicc.zon;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.zon.model.HistoricoDirectorioVenta;
import biz.belcorp.ssicc.dao.spusicc.zon.model.PerfilDirectorio;
import biz.belcorp.ssicc.dao.spusicc.zon.model.RolDirectorio;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoZONDirectorioService"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */

public interface MantenimientoZONDirectorioService extends Service {

	/**
	 * Retorna los tipos de cargo
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
	void executeValidacionDirectorio(Map map) throws Exception;

	/**
	 * Retona la lista del detalle de directorios 
	 * @param mapCabecera
	 * @return
	 */
	List getDirectorioVentasDetalle(Map mapCabecera);

	/**
	 * Ejecuta validaciones de nombramiento
	 * @param map
	 */
	void executeValidacionDirectorioNombramiento(Map map)throws Exception;

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
	void executeRotacionDirectorio(Map map)throws Exception;

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
	void executeLicenciaPersona(Map map)throws Exception;

	/**Realiza la accion de reactivacion de licencia
	 * @param map
	 */
	void executeReactivacionLicenciaPersona(Map map)throws Exception;

	/**
	 * Realiza el retiro del directorio d euna consultora
	 * @param map
	 */
	void executeRetiroDirectorio(Map map)throws Exception;

	/**
	 * Retorn las operaciones del directorio
	 * @param map
	 * @return
	 */
	List getOperaciones(Map map);

	/**
	 * @param criteria2
	 */
	void updateParametroRutaEnvioCorreo(Map criteria);

	/**
	 * @param params
	 */
	void executeReactivacionLicencia(Map params);

	/**
	 * @param criteria2
	 * @return
	 */
	List getLicenciasActivas(Map criteria);

	/**
	 * @param criteria1
	 * @return
	 */
	List getDescripcionCargo(Map criteria);

	/**
	 * @param criteriaSearch
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
	String executeAprobarOperacion(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionIN(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionLI(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionRE(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionRO(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getAprobarOperacionNM(Map criteria);

	/**
	 * @param criteriaSearch
	 * @return
	 */
	List getClientesReemplazoByCriteria(Map criteriaSearch);

	/**
	 * @param items
	 * @param login
	 */
	void deleteLicencia(String[] items, String login);

	List getDirectorioVentasList(Map criteria);
	
	public String getObtenerGerentexTipo(Map criteria);
	
	public String getExisteUsuarioDirectorioVenta(Map criteria);
	
	public void insertDirectorioVentaCabecera(Map criteria);
	
	public void insertDirectorioVenta(Map cabecera, List detalle);
	
	public void insertDirectorioVentaRetiro(Map cabecera, List detalle);
	
	public void insertDirectorioVentaRotacion(Map criteria);

	public void updateDirectorioVentaCabecera(Map criteria);
	
	public Map getValidaEstadoCargo(Map criteria);
	
	public Map obtenerDirectorioVentaCabecera(Map criteria);
	
	public Map obtenerDirectorioVentaDetalle(Map criteria);
	
	public List getTipoLicenciaList();
	List getCargosList(Map criteria);

	List getZonDirecVentaCabecList(Map criteria);

	List getZonDirecVentaDetalList(Map map);

	List getRegionesDisp(Map map);

	List getHistoricoList(Map map);

	List getZonaDisp(Map map);

	void insertDirectorioVentaDetalleRot(Map map);

	void updateDirectorioVentaDetalleRot(Map cabec);

	List getZonDirecVentaDetalSoloList(Map map);

	List getMaeCliente(Map map);

	public void insertDirectorioVenta(Map criteria);
	
	public void insertDirectorioVentaLicencia(Map criteria);
	
	public void updateDirectorioVenta(Map criteria);
	
	public List getResponsableUA(Map criteria);

	public void enviarCorreo(Map criteria);
	
	public List getDirectorioHistorioNovedades(Map criteria);
	
	public List getConsultarDirectorioVentas(Map criteria);
	
	public List getTipoCargoList();
	
	/**Retorn numero de clientes del directorio asociados a un cargos activo
	 * @param criteria
	 * @return
	 */
	public int getValidarTipoCargoDirectorioVenta(Map criteria);
	
	/**
	 * Obtiene una lista de roles en base a los criterios de busqueda 
	 * @param params
	 * @return
	 */
	List getRolesByCriteria(Map params);

	/**
	 * Elimina en forma lgica un rol por su id
	 * @param id
	 * @param usuario
	 */
	void removeRol(String id, Usuario usuario);

	/**
	 * Obtiene una lista de perfiles en base a los criterios de busqueda
	 * @param params
	 * @return
	 */
	List getPerfilesByCriteria(Map params);

	/**
	 * Elimina en forma lgica un perfil por su id
	 * @param id
	 * @param usuario
	 */
	void removePerfil(String id, Usuario usuario);

	/**
	 * Obtiene un Rol por su id
	 * @param id
	 * @return
	 */
	RolDirectorio getRol(String id);

	/**
	 * Obtiene un perfil por su id
	 * @param id
	 * @return
	 */
	PerfilDirectorio getPerfil(String id);
	
	/**
	 * Actualiza un rol
	 * @param rol
	 * @param usuario
	 */
	void updateRol(RolDirectorio rol, Usuario usuario);

	/**
	 * Actualiza un perfil
	 * @param perfil
	 * @param usuario
	 */
	void updatePerfil(PerfilDirectorio perfil, Usuario usuario);

	/**
	 * Inserta un rol
	 * @param rol
	 * @param usuario
	 */
	void insertRol(RolDirectorio rol, Usuario usuario);

	/**
	 * Inserta un perfil
	 * @param rol
	 * @param usuario
	 */
	void insertPerfil(PerfilDirectorio perfil, Usuario usuario);

	/**
	 * Inserta un registro en el historico del directorio, si aun no existe
	 * @param historico
	 */
	void insertHistoricoDirectorioVenta(HistoricoDirectorioVenta historico, Usuario usuario);

	/**
	 * Obtiene los datos del cliente que son auditables
	 * @param params
	 * @return
	 */
	Map getDetalleDatosCliente(Map params);

	/**
	 * Inserta un registro en el historico del directorio, siemore y cuando ya exista un registro padre
	 * @param historico
	 * @param usuario
	 */
	void insertHistoricoDirectorioVentaDetalle(HistoricoDirectorioVenta historico, Usuario usuario);
	
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
  	 * Devuelve el control de facturacin de FOX.
  	 * @param criteria
  	 * @return
  	 */
     public Map getControlFacturacionByCriteriaFOX(Map criteria);
	
}
