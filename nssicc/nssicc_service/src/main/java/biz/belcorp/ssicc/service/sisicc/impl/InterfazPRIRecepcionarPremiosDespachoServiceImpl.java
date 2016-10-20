/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ClienteDAO;
import biz.belcorp.ssicc.dao.scdf.PremioDAO;
import biz.belcorp.ssicc.dao.scdf.TarjetaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Cliente;
import biz.belcorp.ssicc.dao.scdf.model.ClientePK;
import biz.belcorp.ssicc.dao.scdf.model.Premio;
import biz.belcorp.ssicc.dao.scdf.model.Tarjeta;
import biz.belcorp.ssicc.dao.scdf.model.TarjetaPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPRIRecepcionarFactorConversionServiceImpl"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazPRIRecepcionarPremiosDespachoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIRecepcionarPremiosDespachoServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="scdf.tarjetaDAO")
    TarjetaDAO tarjetaDAO;

	@Resource(name="scdf.clienteDAO")
    ClienteDAO clienteDAO;

	@Resource(name="scdf.premioDAO")
    PremioDAO premioDAO;

   

    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {

        row.put("codigoPais", interfazParams.getInterfaz().getCodigoPais());
        try {
            cargarPremiosDespacho(row, interfazParams.getUsuario());
        } catch (Exception e) {
            throw new InterfazException(e.getMessage());
        }
    }

    public synchronized void cargarPremiosDespacho(Map row, Usuario usuario)
            throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarPremioDespacho'");
        }
        Premio premio = new Premio();
        try {
            BeanUtils.copyProperties(premio, row);
            log.debug("Premio " + premio);
            log.debug("Row " + row);
        } catch (IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e;
        }

        // Buscamos la tarjeta para determinar si es necesario insertar
        // una nueva y su correspondiente cliente
        TarjetaPK tarjetaPK = new TarjetaPK(premio.getCodigoPais(), premio
                .getNumeroTarjeta());
        Tarjeta tarjeta = new Tarjeta();

        try {
            tarjeta = tarjetaDAO.getTarjeta(tarjetaPK);
        } catch (ObjectRetrievalFailureException orfe) {
            // En caso ocurra la excepcion entonces la tarjeta no existe
            try {
                BeanUtils.copyProperties(tarjeta, row);
            } catch (IllegalAccessException e) {
                throw e;
            } catch (InvocationTargetException e) {
                throw e;
            }
            tarjeta.setCodigoPais(tarjeta.getCodigoPais());

            // Ahora buscamos al cliente asociado al premio/tarjeta
            ClientePK clientePK = new ClientePK(tarjeta.getCodigoPais(),
                    tarjeta.getCodigoCliente());
            Cliente cliente = new Cliente();

            try {
                cliente = clienteDAO.getCliente(clientePK);
            } catch (ObjectRetrievalFailureException orfe2) {
                // En caso ocurra la excepcion entonces el cliente no
                // existe
                try {
                    BeanUtils.copyProperties(cliente, row);
                } catch (IllegalAccessException e) {
                    throw e;
                } catch (InvocationTargetException e) {
                    throw e;
                }
                cliente.setCodigoPais(tarjeta.getCodigoPais());
                cliente.setCodigo(tarjeta.getCodigoCliente());
                cliente.setStatus(Constants.STATUS_CLIENTE_PREMIO);

                // Insertamos al cliente
                try {
                    clienteDAO.insertCliente(cliente, usuario);
                } catch (Exception e) {
                    throw e;
                }
            }

            // Insertamos la tarjeta
            try {
                tarjetaDAO.insertTarjeta(tarjeta, usuario);
            } catch (Exception e) {
                throw e;
            }

        }
        try {
            premioDAO.insertPremio(premio, usuario);
        } catch (Exception e) {
            throw e;
        }
        premio = null;
    }
}
