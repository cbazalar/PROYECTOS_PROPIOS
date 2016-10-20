/*
 * Created on 29-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.ClienteUAGenerarDAO;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerar;
import biz.belcorp.ssicc.dao.sisicc.model.ClienteUAGenerarPK;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAGenerarServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@Service("sisicc.clienteUAGenerarService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ClienteUAGenerarServiceImpl extends BaseService implements
        ClienteUAGenerarService {

	@Resource(name="sisicc.clienteUAGenerarDAO")
    private ClienteUAGenerarDAO clienteUAGenerarDAO;
   
    /*
     * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#insertClienteUAGenerar(biz.belcorp.ssicc.model.ClienteUAGenerar,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertClienteUAGenerar(ClienteUAGenerar cliente, Usuario usuario) {
        try {
            // Verificamos que no exista un cliente con el mismo identificador
            // En caso no exista el objeto se lanzar una excepcion
            // ObjectRetrievalFailureException
            this.clienteUAGenerarDAO
                    .getClienteUAGenerar(new ClienteUAGenerarPK(cliente
                            .getCodigoPais(), cliente.getCodigoCliente()));
            // Si ninguna excepcion es lanzada entonces el objeto ya existe
            // Por consiguiente lanzamos la excepcion correspondiente
            throw new InvalidIdentifierException(ClienteUAGenerar.class,
                    cliente.getCodigoCliente());

        } catch (ObjectRetrievalFailureException orfe) {
            // Seteamos los valores por defecto
            cliente.setEstado(Constants.ESTADO_ACTIVO);
            cliente.setNivelSocioeconomico(clienteUAGenerarDAO
                    .getNivelSocioeconomico(cliente.getCodigoPais(), cliente
                            .getCodigoTerritorio()));

            // Insertamos el nuevo sistema
            this.clienteUAGenerarDAO.insertClienteUAGenerar(cliente, usuario);
        }
    }

    public int selectClienteUAGenerar(ClienteUAGenerar cliente) {
        int cantidad = 0;
        try {
            cantidad = this.clienteUAGenerarDAO.selectClienteUAGenerar(cliente);
        } catch (ObjectRetrievalFailureException orfe) {
        }
        return cantidad;
    }

    public void updateClienteUAGenerar(ClienteUAGenerar cliente, Usuario usuario) {
        try {
            cliente.setEstado(Constants.ESTADO_ACTIVO);
            cliente.setNivelSocioeconomico(clienteUAGenerarDAO
                    .getNivelSocioeconomico(cliente.getCodigoPais(), cliente
                            .getCodigoTerritorio()));
            this.clienteUAGenerarDAO.updateClienteUAGenerar(cliente, usuario);
        } catch (ObjectRetrievalFailureException orfe) {
        }
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#getClienteUAGenerar(biz.belcorp.ssicc.model.ClienteUAGenerarPK)
     */
    public ClienteUAGenerar getClienteUAGenerar(ClienteUAGenerarPK pk) {
        // TODO Auto-generated method stub
        return this.clienteUAGenerarDAO.getClienteUAGenerar(pk);
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#getNumeroClientesUAGenerar()
     */
    public Long getNumeroClientesUAGenerar() {
        // TODO Auto-generated method stub
        return this.clienteUAGenerarDAO.getNumeroClientesUAGenerar();
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#getClientesUAGenerar()
     */
    public List getClientesUAGenerar() {
        List clientes = this.clienteUAGenerarDAO.getClientesUAGenerar();
        return clientes;
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#removeClientesUAGenerar()
     */
    public void removeClientesUAGenerar() {
        // TODO Auto-generated method stub
        this.clienteUAGenerarDAO.removeClientesUAGenerar();
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#removeClientesUAGenerar()
     */
    public List getLibretaAhorroByCriteria(Map criteria) {
        return this.clienteUAGenerarDAO.getLibretaAhorroByCriteria(criteria);
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#removeClientesUAGenerar()
     */
    public LibretaAhorro getLibretaAhorro(Map criteria) {
        return this.clienteUAGenerarDAO.getLibretaAhorro(criteria);
    }

    /*
     * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#removeClientesUAGenerar()
     */
    public void updateLibretaAhorro(Map criteria, Usuario usuario) {
        try {
            this.clienteUAGenerarDAO.updateLibretaAhorro(criteria, usuario);
        } catch (ObjectRetrievalFailureException orfe) {
            log.debug("");
        }
    }

    public Integer getTamanhoNumeroCliente(String codigoPais) {
        Integer valor = null;
        try {
            valor = clienteUAGenerarDAO
                    .getTamanhoNumeroClientesXPais(codigoPais);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valor;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.ClienteUAGenerarService#getTiposRegimen()
	 */
	public List getTiposRegimen() {
		return  clienteUAGenerarDAO.getTiposRegimen();
	}

}