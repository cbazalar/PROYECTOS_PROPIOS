/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ClienteDAO;
import biz.belcorp.ssicc.dao.scdf.CuentaClienteDAO;
import biz.belcorp.ssicc.dao.scdf.TarjetaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Cliente;
import biz.belcorp.ssicc.dao.scdf.model.CuentaCliente;
import biz.belcorp.ssicc.dao.scdf.model.Tarjeta;
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
@Service("sisicc.interfazPRIRecepcionarTarjetaPuntosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIRecepcionarTarjetaPuntosServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="scdf.clienteDAO")
	ClienteDAO clienteDAO;

    @Resource(name="scdf.cuentaClienteDAO")
    CuentaClienteDAO cuentaClienteDAO;

    @Resource(name="scdf.tarjetaDAO")
    TarjetaDAO tarjetaDAO;

    public TarjetaDAO getTarjetaDAO() {
        return tarjetaDAO;
    }

    public void setTarjetaDAO(TarjetaDAO tarjetaDAO) {
        this.tarjetaDAO = tarjetaDAO;
    }

    public CuentaClienteDAO getCuentaClienteDAO() {
        return cuentaClienteDAO;
    }

    public void setCuentaClienteDAO(CuentaClienteDAO cuentaClienteDAO) {
        this.cuentaClienteDAO = cuentaClienteDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
        row.put("codigoPais", interfazParams.getInterfaz().getCodigoPais());
        try {
            cargarTarjetasPuntos(row, interfazParams.getUsuario());
        } catch (Exception e) {
            throw new InterfazException(e.getMessage());
        }
    }

    public synchronized void cargarTarjetasPuntos(Map row, Usuario usuario)
            throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarTarjetaPuntos'");
        }
        try {
            CuentaCliente cuentaCliente = new CuentaCliente();
            try {
                BeanUtils.copyProperties(cuentaCliente, row);
            } catch (IllegalAccessException e) {
                throw new InterfazException(
                        "Error en el Copiado a CUENTA CLIENTE");
            } catch (InvocationTargetException e) {
                throw new InterfazException(
                        "Error en el Copiado a CUENTA CLIENTE");
            }
            Tarjeta tarjeta = new Tarjeta();
            try {
                BeanUtils.copyProperties(tarjeta, row);
            } catch (IllegalAccessException e) {
                throw new InterfazException("Error en el Copiado a TARJETA");
            } catch (InvocationTargetException e) {
                throw new InterfazException("Error en el Copiado a TARJETA");

            }
            /**
             * Si no existe el Cliente lo insertamos y establecemos el status a
             * T.
             */
            Cliente cliente = new Cliente();
            cliente.setCodigo(cuentaCliente.getCodigoCliente());
            List clientes = clienteDAO.getClientes(cliente);
            if (clientes.size() == 0) {
                Cliente mapCliente = new Cliente();
                try {
                    BeanUtils.copyProperties(mapCliente, row);
                } catch (IllegalAccessException e) {
                    throw new InterfazException("Error en el Copiado a CLIENTE");
                } catch (InvocationTargetException e) {
                    throw new InterfazException("Error en el Copiado a CLIENTE");
                }
                cliente.setCodigo(tarjeta.getCodigoCliente());
                cliente.setCodigoPais(tarjeta.getCodigoPais());
                cliente.setNombres(mapCliente.getNombres());
                cliente.setDocumentoIdentidad(mapCliente
                        .getDocumentoIdentidad());
                cliente.setCodigoConsultora(tarjeta.getCodigoConsultora());
                cliente.setStatus(Constants.STATUS_CLIENTE_TARJETA);
                clienteDAO.insertCliente(cliente, usuario);
            }
            CuentaCliente mapCuentaCliente = new CuentaCliente();
            mapCuentaCliente.setCodigoPais(cuentaCliente.getCodigoPais());
            mapCuentaCliente.setCodigoCliente(cuentaCliente.getCodigoCliente());
            List cuentaClientes = cuentaClienteDAO
                    .getCuentaClientes(mapCuentaCliente);
            if (cuentaClientes.size() == 0) {
                cuentaClienteDAO.insertCuentaCliente(cuentaCliente, usuario);
            }
            Tarjeta mapTarjeta = new Tarjeta();
            mapTarjeta.setCodigoPais(tarjeta.getCodigoPais());
            mapTarjeta.setNumeroTarjeta(tarjeta.getNumeroTarjeta());
            List tarjetas = tarjetaDAO.getTarjetas(mapTarjeta);
            if (tarjetas.size() == 0) {
                tarjetaDAO.insertTarjeta(tarjeta, usuario);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
