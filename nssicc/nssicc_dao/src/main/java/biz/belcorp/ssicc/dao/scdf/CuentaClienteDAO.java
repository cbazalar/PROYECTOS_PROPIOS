/*
 * Created on 19/04/2005 04:43:53 PM biz.belcorp.ssicc.dao.MenuDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.CuentaCliente;

/**
 * TODO Include class description here.
 * <p>
 * <a href="CuentaClienteDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface CuentaClienteDAO extends DAO {

    /**
     * Obtiene un listado de todos las cuentas
     * 
     * @param cuenta
     *            objeto CuentaCliente cuyos atributos son usados como criterios
     *            de búsqueda
     * @return Lista de objetos CuentaCliente poblados
     */
    public List getCuentaClientes(CuentaCliente cuentaCliente);

    /**
     * Registra la información de una nueva cuenta
     * 
     * @param cuenta
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertCuentaCliente(CuentaCliente cuenta, Usuario usuario);

    /**
     * Actualiza la informacion de una cuenta
     * 
     * @param cuenta
     *            el objeto a ser actualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateCuentaCliente(CuentaCliente cuenta, Usuario usuario);

    /**
     * Elimina una cuenta de la base de datos en base a su codigo
     * 
     * @param cuentaCliente
     *            el codigo de la Cuenta del Cliente.
     */
    public void removeCuentaCliente(CuentaCliente cuentaCliente);

    /**
     * Elimina una cuenta de Cliente en base al Pais al que pertenece
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario invocador
     */
    public void removeCuentaClienteByPais(String codigoPais);
}