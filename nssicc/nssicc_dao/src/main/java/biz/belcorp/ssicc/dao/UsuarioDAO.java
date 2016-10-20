/*
 * Created on 25/02/2005 11:20:54 AM biz.belcorp.ssicc.dao.UsuarioDAO
 */
package biz.belcorp.ssicc.dao;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.model.UsuarioBloqueo;

/**
 * TODO Include class description here.
 * <p>
 * <a href="UsuarioDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface UsuarioDAO extends DAO {

    /**
     * Obtiene un listado de los bloqueos de un usuario
     * @param codigoUsuario
     * @return
     */
    public List getBloqueosByUsuario(final String codigoUsuario);

    /**
     * Obtiene un bloqueo de usuario por criterios
     * @param criteria
     * @return
     */
    public UsuarioBloqueo getBloqueoUsuarioByCriteria(Map criteria);

    /**
     * Obtiene el correlativo de la tabla de bloqueos de usuario
     * @return
     */
    public long getCorrelativoUsuarioBloqueo();

    /**
     * Obtiene el valor del siguiente PK para el usuario que se va a insertar en la
     * base de datos, los parametros enviados son usados para calcular el valor
     * de la llave primaria.
     * 
     * @param params
     * @return El nuevo codigo a asignar
     */
    public String getNextPK(Map params);

    /**
     * Obtiene la informacion del usuario en base a su codigo. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param codigo
     *            el codigo del usuario
     * @return usuario objeto usuario poblado
     */
    public Usuario getUsuario(final String codigo); 

    /**
     * Obtiene la informacion del usuario en base a su username. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param username
     *            el username del usuario
     * @return usuario objeto usuario poblado
     */
    public Usuario getUsuarioByUsername(final String username);

    /**
     * Obtiene un listado de todos los usuarios
     */
    public List getUsuarios(Usuario usuario);

    /**
     * Obtiene un listado de todos los usuarios en base a ciertos criterios los
     * cuales son enviados a traves de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            bsqueda
     * @return Lista de objetos Usuario poblados
     */
    public List getUsuariosByCriteria(Map criteria);
    
    /**
     * Inserta un bloqueo de usuario
     * @param bloqueo
     * 		objeto a insertar
     * @param usuario
     * 		objeto conteniendo informacin del usuario invocador
     */
    public void insertBloqueoUsuario(UsuarioBloqueo bloqueo, Usuario usuario);
    
    /**
     * Registra la informacin de un nuevo usuario
     * 
     * @param obj
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo informacion del usuario invocador
     */
    public void insertUsuario(Usuario obj, Usuario usuario);
    
    /**
     * Elimina un usuario de la base de datos en base a su codigo
     * 
     * @param codigo
     *            el codigo del usuario
     */
    public void removeUsuario(final String codigo);
    
    /**
     * Actualiza un bloqueo de usuario
     * @param bloqueo
     * 		objeto a insertar
     * @param usuario
     * 		objeto conteniendo informacion del usuario invocador
     */
    public void updateBloqueoUsuario(UsuarioBloqueo bloqueo, Usuario usuario);
 
    /**
     * Actualiza la informacion de un usuario
     * 
     * @param obj
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo informacion del usuario invocador
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
	 * @param criteria
	 */
	public void insertOpcionesConsultaHiperConsultaUsuario(Map criteria);
	
	
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
	
	/**
	 * Verifica si exsite el Login respectivo en la tabla SEG_USUAR_BORRA
	 * @param criteria
	 * @return
	 */
	public Integer getVerificarUsuarioEliminadoPoliticaSeguridad(Map criteria);
	
}