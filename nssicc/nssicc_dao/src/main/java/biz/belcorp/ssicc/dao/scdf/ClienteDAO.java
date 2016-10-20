/*
 * Created on 10/11/2005 11:20:54 AM biz.belcorp.ssicc.dao.ClienteDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Cliente;
import biz.belcorp.ssicc.dao.scdf.model.ClientePK;
import biz.belcorp.ssicc.dao.scdf.model.EmailCliente;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ClienteDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ClienteDAO extends DAO {

    /**
     * Obtiene una Lista de Objetos Clientes
     * 
     * @param cliente
     *            Informacion de un Cliente segun parametros
     * @return Lista de objetos clientes poblados
     */
    public List getClientes(Cliente cliente);

    /**
     * Obtiene una Lista de Maps con campos del Cliente
     * 
     * @param cliente
     *            Informacion de un Cliente segun parametros
     * @return Lista de objetos Map poblados
     */
    public List getClientesMap(Cliente cliente);

    /**
     * Obtiene la relacion de clientes a ser enviados a Privilege a traves de la
     * interfaz de descarga.
     * 
     * @param codigoPais Codigo del pais
     * @return
     */
    public List getClientesMapByPais(String codigoPais);

    /**
     * Obtiene un listado de clientes en base a ciertos criterios los cuales son
     * enviados a través de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Cliente poblados
     */
    public List getClientesByCriteria(Map criteria);

    /**
     * Obtiene la informacion del cliente en base a su primaryKey. La
     * excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si
     * el cliente no es encontrado.
     * 
     * @param primaryKey
     *            la primaryKey del cliente
     * @return cliente objeto cliente poblado
     */
    public Cliente getCliente(ClientePK primaryKey);

    /**
     * Registra la información de un nuevo cliente
     * 
     * @param cliente
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertCliente(Cliente cliente, Usuario usuario);

    /**
     * Actualiza la informacion de un cliente
     * 
     * @param cliente
     *            el objeto a ser actualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateCliente(Cliente cliente, Usuario usuario);

    /**
     * Actualiza el las campañas de los clientes que no tienen campaña asignada.
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario
     */
    public void updatePeriodoClienteByPais(String codigoPais);

    /**
     * Actualiza el status de Impresion del Cliente segun el codigo del Pais
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario
     */

    public void updateClienteStatusByPais(String codigoPais);

    /**
     * Elimina un cliente de la base de datos en base a sus Datos de cliente
     * 
     * @param cliente
     *            el codigo del cliente
     */
    public void removeCliente(Cliente cliente);

    /**
     * Se elimina todos los Clientes segun el Pais del Usuario
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario
     */
    public void removeClienteByPais(String codigoPais);

    /**
     * @param codigoPais
     */
    public void executeEliminarBuzonMsg(String codigoPais);

	/**
	 * Elimina los registros de la tabla temporal de clientes
	 */
	public void deleteEmailClientes();

	/**
	 * Inserta los Email de los clientes en la tabla temporal de Emails de CLientes
	 * Privilege
	 * @param emailCliente
	 * @param usuario
	 */
	public void insertEmailCliente(EmailCliente emailCliente, Usuario usuario);

	/* Procesa la informacion recepcionada por la Interfaz de Recepcion de Emails de Clientes
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.DAO#executeInterfazPRIRecepcionarEmailClientes()
	 */
	public void executeInterfazPRIRecepcionarEmailClientes();

}