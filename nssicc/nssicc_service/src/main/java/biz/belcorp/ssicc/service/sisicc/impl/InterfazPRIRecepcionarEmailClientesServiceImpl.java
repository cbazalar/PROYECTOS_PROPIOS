/*
 * Created on 12-ago-08 12:18:15
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazPRIRecepcionarClientesRechazadosServiceImpl
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
import biz.belcorp.ssicc.dao.scdf.ClienteDAO;
import biz.belcorp.ssicc.dao.scdf.model.EmailCliente;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPRIRecepcionarClientesRechazadosServiceImpl.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazPRIRecepcionarEmailClientesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIRecepcionarEmailClientesServiceImpl extends	BaseInterfazEntradaAbstractService {

	@Resource(name="scdf.clienteDAO")
	ClienteDAO clienteDAO;

	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		try {
			clienteDAO.deleteEmailClientes();
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
			cargarEmailCliente(row, interfazParams.getUsuario());
		} catch (Exception e) {
			throw new InterfazException(e.getMessage());
		}
	}

	public synchronized void cargarEmailCliente(Map row, Usuario usuario) throws Exception {
		if (log.isInfoEnabled()) {
			log.info("Dentro del metodo 'cargarMovimientoClientes'");
		}
		EmailCliente emailCliente = new EmailCliente();
		try {
			BeanUtils.copyProperties(emailCliente, row);
			clienteDAO.insertEmailCliente(emailCliente,
					usuario);
		} catch (IllegalAccessException e) {
			throw e;
		} catch (InvocationTargetException e) {
			throw e;
		}
	}
	
	
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		
		super.afterProcessInterfaz(interfazParams);
		
		
		try {
			clienteDAO.executeInterfazPRIRecepcionarEmailClientes();
		} catch (Exception e) {
			throw new InterfazException("Error al procesar procesaSolicitudConsolCabecera: "+ e.getMessage());
		}
	}

}
