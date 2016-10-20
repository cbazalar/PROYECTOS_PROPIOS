package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionRecaudadorasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCGenerarInformacionRecaudadorasService;


/**
 * Service que controla la Generacion de la Informacion para Recaudadoras
 *  
 * <p>
 * <a href="ProcesoCCCGenerarInformacionRecaudadorasServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
public class ProcesoCCCGenerarInformacionRecaudadorasServiceImpl extends BaseService implements ProcesoCCCGenerarInformacionRecaudadorasService {


	private ProcesoCCCGenerarInformacionRecaudadorasDAO procesoCCCGenerarInformacionRecaudadorasDAO;
		
		   	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ProcesoCCCGenerarInformacionRecaudadorasService#executeGenerarInformacionRecaudadoras(java.util.Map)
	 */
	public void executeGenerarInformacionRecaudadoras (Map criteria) {
		this.procesoCCCGenerarInformacionRecaudadorasDAO.executeGenerarInformacionRecaudadoras(criteria);
	}


	/**
	 * @return the procesoCCCGenerarInformacionRecaudadorasDAO
	 */
	public ProcesoCCCGenerarInformacionRecaudadorasDAO getProcesoCCCGenerarInformacionRecaudadorasDAO() {
		return procesoCCCGenerarInformacionRecaudadorasDAO;
	}


	/**
	 * @param procesoCCCGenerarInformacionRecaudadorasDAO the procesoCCCGenerarInformacionRecaudadorasDAO to set
	 */
	public void setProcesoCCCGenerarInformacionRecaudadorasDAO(
			ProcesoCCCGenerarInformacionRecaudadorasDAO procesoCCCGenerarInformacionRecaudadorasDAO) {
		this.procesoCCCGenerarInformacionRecaudadorasDAO = procesoCCCGenerarInformacionRecaudadorasDAO;
	}
	
	
	
}
