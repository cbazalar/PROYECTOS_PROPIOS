package biz.belcorp.ssicc.service;

import java.util.List;

/**
 * Business Service Interface to talk to persistence layer and retrieve values
 * for drop-down choice lists.
 * <p>
 * <a href="LookupService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramirez </a>
 */
public interface LookupService {

    /**
     * Obtiene todos los idiomas haciendo uso de la capa de persistencia
     * 
     * @return List
     */
    public List getAllIdiomas();

    /**
     * Obtiene todos los paises haciendo uso de la capa de persistencia
     * 
     * @return List
     */
    public List getAllPaises();

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
     * Obtiene una lista con las extensiones de los archivos de entrada salida
     * 
     * @return
     */
    public List getExtensionesArchivo();

	/**
     * Obtiene una lista con las extensiones de los archivos de log
     * 
     * @return
     */
    public List getExtensionesLog();

		
	/**
	 * Actualiza los procesos No terminados
	 */
	public void updateProcesosNoTerminados();
	

	/**
     * Obtiene una lista con las polticas de seguridad para las contraseas
     * 
	 * @return
	 */
	public List getAllPoliticasSeguridadContrasenia();
}
