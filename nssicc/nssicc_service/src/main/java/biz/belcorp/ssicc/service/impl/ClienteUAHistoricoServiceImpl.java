/*
 * Created on 04-ene-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.ClienteUAHistoricoDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAHistorico;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAHistoricoPK;
import biz.belcorp.ssicc.service.ClienteUAHistoricoService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.util.exception.NoDataFoundException;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAHistoricoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.clienteUAHistoricoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ClienteUAHistoricoServiceImpl extends BaseService implements
        ClienteUAHistoricoService {

	@Resource(name="sisicc.clienteUAHistoricoDAO")
    private ClienteUAHistoricoDAO clienteUAHistoricoDAO;

 


    /*
     * @see biz.belcorp.ssicc.service.ClienteUAHistoricoService#insertClienteUAHistorico(biz.belcorp.ssicc.model.ClienteUAHistorico,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertClienteUAHistorico(ClienteUAHistorico cliente,
            Usuario usuario) {
        try {
            // Verificamos que no exista un cliente con el mismo identificador
            // En caso no exista el objeto se lanzar una excepcion
            // ObjectRetrievalFailureException
            this.clienteUAHistoricoDAO
                    .getClienteUAHistorico(new ClienteUAHistoricoPK(cliente
                            .getCodigoPais(), cliente.getCodigoCliente(), cliente.getNumeroLote()));
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
            throw new InvalidIdentifierException(ClienteUAHistorico.class,
                    cliente.getCodigoCliente());
        } catch (ObjectRetrievalFailureException orfe) {
            // Seteamos los valores por defecto
            cliente.setEstado(Constants.ESTADO_ACTIVO);

            // Insertamos el nuevo sistema
            this.clienteUAHistoricoDAO.insertClienteUAHistorico(cliente,
                    usuario);
        }
    }

    public int selectClienteUAHistorico(ClienteUAHistorico cliente) {
        int cantidad = 0;
        try {
            cantidad = this.clienteUAHistoricoDAO
                    .selectClienteUAHistorico(cliente);
        } catch (ObjectRetrievalFailureException orfe) {
        }
        return cantidad;
    }

    public void updateClienteUAHistorico(ClienteUAHistorico cliente,
            Usuario usuario) {
        try {
            cliente.setEstado(Constants.ESTADO_ACTIVO);
            this.clienteUAHistoricoDAO.updateClienteUAHistorico(cliente,
                    usuario);
        } catch (ObjectRetrievalFailureException orfe) {
        }
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAHistoricoService#getClienteUAHistorico(biz.belcorp.ssicc.model.ClienteUAHistoricoPK)
     */
    public ClienteUAHistorico getClienteUAHistorico(ClienteUAHistoricoPK pk) {
        return this.clienteUAHistoricoDAO.getClienteUAHistorico(pk);
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAHistoricoService#getClientesUAHistorico()
     */
    public List getClientesUAHistorico() {
        List clientes = this.clienteUAHistoricoDAO.getClientesUAHistorico();
        if (clientes.size() == 0 || clientes == null) {
            throw new NoDataFoundException();
        }
        return clientes;
    }
}
