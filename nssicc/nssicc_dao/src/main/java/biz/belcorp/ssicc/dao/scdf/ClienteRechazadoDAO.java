/*
 * Created on 12-ago-08 12:06:53
 * biz.belcorp.ssicc.scdf.dao.ClienteRechazadoDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.ClienteRechazado;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ClienteRechazadoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ClienteRechazadoDAO extends DAO {

    /**
     * Registra la información de un cliente rechazado
     * 
     * @param cliente
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertClienteRechazado(ClienteRechazado clienteRechazado, Usuario usuario);

    public void deleteClientesRechazados();
}
