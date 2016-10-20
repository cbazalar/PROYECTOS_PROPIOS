package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazDIRDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service para la recepcion de Clientes.
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("sisicc.interfazDIRRecepcionarClientesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDIRRecepcionarClientesServiceImpl extends BaseInterfazEntradaAbstractService {
	
	@Resource(name="sisicc.interfazDIRDAO")
	InterfazDIRDAO interfazDIRDAO;
		
	/**
	 * @param interfazDIRDAO the interfazDIRDAO to set
	 */
	public void setInterfazDIRDAO(InterfazDIRDAO interfazDIRDAO) {
		this.interfazDIRDAO = interfazDIRDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) throws InterfazException {
		Map map = interfazParams.getQueryParams();
		try {
			interfazDIRDAO.deleteInterfazDIRRecepcionarClientesTemporal();
		} catch (Exception e) {
			throw new InterfazException("Error al borrar los registros de la tabla : "+ e.getMessage());
		}
	}
  
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#addLine(java.util.List, java.util.Map)
	 */
	protected void addLine(List data, Map row) {
		interfazDIRDAO.insertInterfazDIRRecepcionarClientesTemporal(row);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazEntradaAbstractService#processData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.util.List)
	 */
	@Override
	protected void processData(InterfazParams interfazParams, List data) throws InterfazException {
		Map params = interfazParams.getQueryParams();
		Usuario usuario = interfazParams.getUsuario();
		
		params.put("usuario", usuario.getLogin());
		interfazDIRDAO.executeInterfazDIRRecepcionarClientes(params);
	}
	
}