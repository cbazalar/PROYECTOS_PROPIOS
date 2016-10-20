/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.MovimientoClienteDAO;
import biz.belcorp.ssicc.dao.scdf.model.MovimientoCliente;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPRIRecepcionarMovimientoClientesServiceImpl"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jrosas@belcorp.biz">Jhenifer Rosas</a>
 */
@Service("sisicc.InterfazPRIRecepcionarMovimientoClientesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIRecepcionarMovimientoClientesServiceImpl extends
        BaseInterfazEntradaAbstractService {

	@Resource(name="scdf.movimientoClienteDAO")
	MovimientoClienteDAO  movimientoClienteDAO;

   
	protected void beforeReadData(InterfazParams interfazParams)throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		try {
			interfazSiCCDAO.deleteInterfazMovimientoCliente();
			
		} catch (Exception e) {
			throw new InterfazException(
			"Error al borrar/cargar los registros de la tabla temporal: "
					+ e.getMessage());
		}
	}
	
	protected void processLine(InterfazParams interfazParams, int lineCount,
            Map row) throws InterfazException {

        row.put("codigoPais", interfazParams.getInterfaz().getCodigoPais());
        try {
            cargarMovimientoClientes(row, interfazParams.getUsuario());
        } catch (Exception e) {
            throw new InterfazException(e.getMessage());
        }
    }

    public synchronized void cargarMovimientoClientes(Map row, Usuario usuario)
            throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarMovimientoClientes'");
        }
        MovimientoCliente mov = new MovimientoCliente();
        try {
            BeanUtils.copyProperties(mov, row);
            log.debug("MovimientoCliente " + mov);
            log.debug("Row " + row);
            
            movimientoClienteDAO.insertMovimientoCliente(mov, usuario);
            
        } catch (IllegalAccessException e) {
            throw e;
        } catch (InvocationTargetException e) {
            throw e;
        }
    }

}
