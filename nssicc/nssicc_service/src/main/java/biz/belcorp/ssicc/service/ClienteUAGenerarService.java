/*
 * Created on 29-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerar;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerarPK;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAGenerarService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface ClienteUAGenerarService extends Service {

	/**
	 * Registra la informacion de un nuevo cliente.
	 * 
	 * @param cliente
	 * Datos del cliente a insertar.
	 * 
	 * @param usuario
	 * Usuario quien hace la invocacion.
	 */
	public void insertClienteUAGenerar(ClienteUAGenerar cliente, Usuario usuario);
	
    /**
     * Registra la informacion de un nuevo cliente.
     * 
     * @param cliente
     * Datos del cliente a insertar.
     * 
     * @param usuario
     * Usuario quien hace la invocacion.
     */
    public void updateClienteUAGenerar(ClienteUAGenerar cliente, Usuario usuario);
    
    
    /**
     * Registra la informacion de un nuevo cliente.
     * 
     * @param cliente
     * Datos del cliente a insertar.
     * 
     * @param usuario
     * Usuario quien hace la invocacion.
     */
    public int selectClienteUAGenerar(ClienteUAGenerar cliente);
    
    
	/**
	 * Obtiene los datos de un cliente en base a su llaer primaria.
	 * 
	 * @param pk
	 * Llave primaria del cliente.
	 * 
	 * @return
	 * Objeto de tipo ClienteUAGenerar, poblado.
	 */
	public ClienteUAGenerar getClienteUAGenerar(ClienteUAGenerarPK pk);

	/**
	 * Obtiene el numero total de registros que existe en la entidad MAE_CLIEN_UNAD
	 * 
	 * @return
	 * Cantidad total de registros
	 */
	public Long getNumeroClientesUAGenerar();
	
	/**
	 * Obtiene todos los clientes existentes.
	 * 
	 * @return
	 * Lista de objetos de tipo ClienteUAGenerar, poblados
	 */
	public List getClientesUAGenerar();

    /**
	 * Elimina todos los clientes existentes.
	 */
	public void removeClientesUAGenerar();
    /**
     * Obtiene todos los clientes existentes.
     * 
     * @return
     * Lista de objetos de tipo ClienteUAGenerar, poblados
     */
     public List getLibretaAhorroByCriteria(Map criteria);
   /**
     * Obtiene todos los clientes existentes.
     * 
     * @return
     * Lista de objetos de tipo ClienteUAGenerar, poblados
     */
    public LibretaAhorro getLibretaAhorro (Map criteria);
    
	/**
     * Actualiza la libreta de ahorros segun criterio
     * @param criteria: Map conteniendo informacion para filtrado
     * @param usuario: Objeto conteniendo informacion del Usuario en sesion.
     */
    public void updateLibretaAhorro (Map criteria, Usuario usuario);

	/**
     * Obtiene la longitud del codigo del Cliente, dado un codigo de Pais.
     * 
     * @return
     * Valor entero con el tamao del codigo del cliente.
     */
    public Integer getTamanhoNumeroCliente (String codigoPais);

    /**
     * Obtienen los tipos de regimen
     * 
     * @return
     */
	public List getTiposRegimen();
	
}