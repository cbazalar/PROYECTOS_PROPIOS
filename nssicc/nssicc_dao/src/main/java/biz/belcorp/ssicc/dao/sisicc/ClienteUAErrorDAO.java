/*
 * Created on 03-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAError;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAErrorPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAErrorDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface ClienteUAErrorDAO extends DAO {
	/**
	 * Registra la informacion de un nuevo cliente.
	 * 
	 * @param cliente
	 * Datos del cliente a insertar.
	 * 
	 * @param usuario
	 * Usuario quien hace la invocacion.
	 */
	public void insertClienteUAError(ClienteUAError cliente, Usuario usuario);
    /**
     * Actualiza la informacion de un nuevo cliente.
     * 
     * @param cliente
     * Datos del cliente a actualizar.
     * 
     * @param usuario
     * Usuario quien hace la invocacion.
     */

    public void updateClienteUAError(ClienteUAError cliente, Usuario usuario);
    /**
     * Selecciona un cliente.
     * 
     * @param cliente
     * Datos del cliente a seleccionar.
     * 
     * @param usuario
     * Usuario quien hace la invocacion.
     */
    public int selectClienteUAError (ClienteUAError clienteOriginal);
	/**
	 * Obtiene los datos de un cliente en base a su llave primaria.
	 * 
	 * @param pk
	 * Llave primaria del cliente.
	 * 
	 * @return
	 * Objeto de tipo ClienteUAError, poblado.
	 */
	public ClienteUAError getClienteUAError(ClienteUAErrorPK pk);

	/**
	 * Obtiene una lista de clientes en base a un criterio de busqeda.
	 * 
	 * @param criteria
	 * Criterio de busqueda.
	 * 
	 * @return
	 * Lista de objetos de tipo ClienteUAError, poblados.
	 */
	public List getClientesUAErrorByCriteria(Map criteria);
    
    /**
     * Obtiene una lista de clientes en base a un criterio de busqeda.
     * 
     * @param criteria
     * Criterio de busqueda.
     * 
     * @return
     * Lista de objetos de tipo ClienteUAError, poblados.
     */
    public List getDireccionConsultorasByCriteria(Map criteria);
    
    /**
     * Obtiene una lista de clientes en base a un criterio de busqeda.
     * 
     * @param criteria
     * Criterio de busqueda.
     * 
     * @return
     * Lista de objetos de tipo ClienteUAError, poblados.
     */
    public List getReporteClientesErroneos(Map criteria);
    
    /**
     * Obtiene una lista de clientes en base a un criterio de busqeda.
     * 
     * @param criteria
     * Criterio de busqueda.
     * 
     * @return
     * Lista de objetos de tipo ClienteUAError, poblados.
     */
    public List getPagoLideresByCriteria(Map criteria);
    /**
     * Obtiene una lista de clientes en base a un criterio de busqeda.
     * 
     * @param criteria
     * Criterio de busqueda.
     * 
     * @return
     * Lista de objetos de tipo ClienteUAError, poblados.
     */
    
    public List getLideresNuevasByCriteria(Map criteria);
}
