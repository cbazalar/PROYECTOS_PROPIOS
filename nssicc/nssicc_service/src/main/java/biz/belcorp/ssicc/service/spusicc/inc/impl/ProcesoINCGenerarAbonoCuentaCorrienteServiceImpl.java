package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarAbonoCuentaCorrienteDAO;

/**
 * Service que va a generar Abono Cuenta Corriente
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCGenerarAbonoCuentaCorrienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCGenerarAbonoCuentaCorrienteServiceImpl extends
	BaseInterfazProcesoAbstractService {
	
	@Resource(name="spusicc.procesoINCGenerarAbonoCuentaCorrienteDAO")
	private ProcesoINCGenerarAbonoCuentaCorrienteDAO procesoINCGenerarAbonoCuentaCorrienteDAO;

	protected void executeStoreProcedure(Map params) {
		procesoINCGenerarAbonoCuentaCorrienteDAO.executeGenerarAbonoCuentaCorriente(params);
	}
	
	/**
	 * @param procesoINCGenerarAbonoCuentaCorrienteServiceDAO the procesoINCGenerarAbonoCuentaCorrienteServiceDAO to set
	 */
	public void setProcesoINCGenerarAbonoCuentaCorrienteDAO(
			ProcesoINCGenerarAbonoCuentaCorrienteDAO procesoINCGenerarAbonoCuentaCorrienteDAO) {
		this.procesoINCGenerarAbonoCuentaCorrienteDAO = procesoINCGenerarAbonoCuentaCorrienteDAO;
		
	}
	
}