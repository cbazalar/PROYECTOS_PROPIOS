package biz.belcorp.ssicc.dao;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * Lookup Data Access Object (DAO) interface. This is used to lookup values in
 * the database (i.e. for drop-downs).
 * <p>
 * <a href="LookupDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramirez </a>
 */
public interface LookupDAO extends DAO {

    /**
	 * Obtiene una lista de todos los tipos de acciones de bloqueo de usuarios
	 * @return
	 */
	public List getAllTiposAcciones();

    /**
	 * Obtiene una lista de todos los tipos de bloqueo a los usuarios
	 * @return
	 */
	public List getAllTiposBloqueoUsuario();

    /**
     * Obtiene la relacion de todas las extensiones para los archivos de
     * entrada/salida
     * 
     * @return
     */
    public List getExtensionesArchivo();

    /**
     * Obtiene la relacion de todas las extensiones para los archivos de log.
     * 
     * @return
     */
    public List getExtensionesLog();

	/**
     * Obtiene la relacion de todos los idiomas registrados en el sistema
     * 
     * @return List con objetos Pais.
     */
    public List getIdiomas();
	
    /**
     * Obtiene la relacion de todos los paises registrados en el sistema
     * 
     * @return List con objetos Pais.
     */
    public List getPaises();

    /**
	 *Actualiza los Procesos Batch No terminados 
	 */
	public void updateProcesosNoTerminados();	
	

	/**
     * Obtiene una lista con las polticas de seguridad para las contraseas
     * 
	 * @return
	 */
	public List getAllPoliticasSeguridadContrasenia();
	
}