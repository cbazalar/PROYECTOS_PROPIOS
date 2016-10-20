/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ClienteDAO;
import biz.belcorp.ssicc.dao.scdf.model.Cliente;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPRIRecepcionarFichasInscripcionServiceImpl"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazPRIRecepcionarFichasInscripcionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIRecepcionarFichasInscripcionServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="scdf.clienteDAO")
    ClienteDAO clienteDAO;

   
    protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {
        row.put("codigoPais", interfazParams.getInterfaz().getCodigoPais());
        try {
            cargarFichasInscripcion(row, interfazParams.getUsuario());
        } catch (Exception e) {
            throw new InterfazException(e.getMessage());
        }
    }

    public synchronized void cargarFichasInscripcion(Map row, Usuario usuario)
            throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarFichaInscripcion'");
        }
        try {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(cliente, row);
            cliente.setStatus(Constants.STATUS_CLIENTE_FICHAS);
            clienteDAO.insertCliente(cliente, usuario);
            cliente = null;
        } catch (Exception e) {
            throw e;
        }
    }
}
