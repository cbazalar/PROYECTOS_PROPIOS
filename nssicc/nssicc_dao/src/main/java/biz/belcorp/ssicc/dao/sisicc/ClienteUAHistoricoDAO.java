/*
 * Created on 04-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAHistorico;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAHistoricoPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAHistoricoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface ClienteUAHistoricoDAO extends DAO {
	/**
	 * Registra la informacion de un nuevo cliente.
	 * 
	 * @param cliente
	 * Datos del cliente a insertar.
	 * 
	 * @param usuario
	 * Usuario quien hace la invocacion.
	 */
	public void insertClienteUAHistorico(ClienteUAHistorico cliente, Usuario usuario);
    /**
     * Actualiza la informacion de un nuevo cliente.
     * 
     * @param cliente
     * Datos del cliente a actualizar.
     * 
     * @param usuario
     * Usuario quien hace la invocacion.
     */
    public void updateClienteUAHistorico(ClienteUAHistorico cliente, Usuario usuario);

    /**
     * verifica la informacion de un nuevo cliente.
     * 
     * @param cliente
     * Datos del cliente a verificar.
     * 
     * @param usuario
     * Usuario quien hace la invocacion.
     */
    public int selectClienteUAHistorico(ClienteUAHistorico cliente);

	/**
	 * Obtiene los datos de un cliente en base a su llaer primaria.
	 * 
	 * @param pk
	 * Llave primaria del cliente.
	 * 
	 * @return
	 * Objeto de tipo ClienteUAGenerar, poblado.
	 */
	public ClienteUAHistorico getClienteUAHistorico(ClienteUAHistoricoPK pk);
	
	/**
	 * Obtiene todos los clientes existentes.
	 * 
	 * @return
	 * Lista de objetos de tipo ClienteUAGenerar, poblados
	 */
	public List getClientesUAHistorico();
	
}
