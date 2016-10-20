/*
 * Created on 02/03/2005 11:37:48 AM
 * biz.belcorp.ssicc.service.UsuarioService
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface UsuarioService extends Service {

    /**
     * Obtiene un listado de los bloqueos de un usuario
     * @param codigoUsuario
     * @return
     */
    public List getBloqueosByUsuario(final String codigoUsuario);

    /**
     * Obtiene la informacin del usuario en base al cdigo.
     * 
     * @param codigo
     *            el codigo del usuario
     * @return usuario objeto usuario poblado
     */
    public Usuario getUsuario(String codigo);

    /**
     * Obtiene la informacin del usuario en base al username.
     * 
     * @param username
     *            el username del usuario
     * @return usuario objeto usuario poblado
     */
    public Usuario getUsuarioByUsername(String username);

    /**
     * Obtiene todos los usuarios
     */
    public List getUsuarios(Usuario usuario);

    /**
     * Obtiene todos los usuarios tomando como criterios de busqueda los valores
     * enviados a traves de un Map
     */
    public List getUsuariosByCriteria(Map criteria);

    /**
     * Inserta los bloqueos de un usuario
     * @param bloqueos
     * 			Lista de bloqueos
     * @param usuario
     * 			El usuario que actualiza
     */
    public void insertBloqueosUsuario(List bloqueos, Usuario usuario);

    /**
     * Registra la informacin de un nuevo usuario
     * 
     * @param obj
     *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la informacin
     */
    public void insertUsuario(Usuario obj, Usuario usuario);
    
    /**
     * Actualiza los bloqueos de un usuario
     * @param bloqueos
     * 			Lista de bloqueos
     * @param usuario
     * 			El usuario que actualiza
     */
    public void removeBloqueosUsuario(List bloqueos, Usuario usuario);

    /**
     * Elimina un usuario de la base de datos en base a su codigo
     * 
     * @param codigo
     *            el codigo del usuario
     * @param usuario
     *            el usuario que elimina la informacin
     */
    public void removeUsuario(final String codigo, Usuario usuario);

        
    /**
     * Actualiza la informacion de un usuario
     * 
     * @param obj
     *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la informacin
     */
    public void updateUsuario(Usuario obj, Usuario usuario);
    
    
     /**
     * Actualiza los intentos fallidos para la contrasea de un usuario
     * 
     * @param obj
     *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza los intentos fallidos para la contrasea
     */
    public void updateIntentosFallidosClaveUsuario(Usuario obj, Usuario usuario);
    
    
    /**
     * Actualiza la fecha de modificacin de la contrasea de un usuario
     * 
     * @param obj
     *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la fecha de modificacin de la contrasea
     */
    public void updateFechaModificacionClaveUsuario(Usuario obj, Usuario usuario);

    
	
	/**
     * Obtiene la poltica de seguridad
	 * @param criteria
	 * @return
	 */
	public String getPoliticaByCodigo(Map criteria);
	
	/**
	 * Verifica si exsite el Login respectivo
	 * @param criteria
	 * @return
	 */
	public Integer verificarExisteLogin(Map criteria);
	
	/**
	 * Devuelve Lista de Opciones de Consulta de HiperConsulta
	 * @param criteria
	 * @return
	 */
	public List getOpcionesConsultaHiperConsulta(Map criteria);
	
	/**
	 * Devuelve Lista de Opciones de Consulta de HiperConsulta x Usuario
	 * @param codigoUsuario
	 * @return
	 */
	public List getOpcionesConsultaHiperConsultaByUsuario(final String codigoUsuario);
	
	/**
	 * Elimina Opciones Consulta del Usuario
	 * @param criteria
	 */
	public void removeOpcionesConsultaHiperConsultaUsuario(Map criteria);
	
	
	/**
	 * Inserta Opciones Consulta del Usuario
	 * @param bloqueos
	 */
	public void insertOpcionesConsultaHiperConsultaUsuario(List bloqueos);
	
	
	/**
	 * Bloqueo de Usuarios seleccionados
	 * @param criteria
	 */
	public void bloqueoManualUsuario(Map criteria);
	
	
	/**
	 * DesBloqueo de Usuarios seleccionados
	 * @param criteria
	 */
	public void desbloqueoManualUsuario(Map criteria);
	
	/**
	 * Eliminación fisica de Usuarios seleccionados
	 * @param criteria
	 */
	public void eliminarFisicoManualUsuario(Map criteria);
	
	
    
    /* INI NSSICC */
    /**
     * Ejemplo para invocar messages a la capa DAO
     * @param id
     * @return
     */
    public String getKeyMessageDAO(String id);
    
    /**
     * Inicializa la Ruta del Servidor
     * @param rutaPath
     */
    public void setRutaPath(String rutaPath);
    
    /**
     * Retorna la Ruta del Servidor
     * @return
     */
    public String getRutaPath();
    
    /* FIN NSSICC */
    
    /**
	 * Verifica si exsite el Login respectivo en la tabla SEG_USUAR_BORRA
	 * @param criteria
	 * @return
	 */
	public Integer getVerificarUsuarioEliminadoPoliticaSeguridad(Map criteria);
}
