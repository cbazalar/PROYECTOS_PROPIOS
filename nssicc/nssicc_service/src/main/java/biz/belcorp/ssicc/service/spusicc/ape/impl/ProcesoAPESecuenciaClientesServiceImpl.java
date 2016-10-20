package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPESecuenciaClientesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.ProcesoAPESecuenciaClientesService;

/**
 * Service que ejecutara los metodos de la secuencia de clientes
 *  
 * <p>
 * <a href="ProcesoAPESecuenciaClientesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@csigcomt.com">Dennys OLiva Iriarte</a>
 * 
 */
@Service("spusicc.procesoAPESecuenciaClientesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoAPESecuenciaClientesServiceImpl extends BaseService implements ProcesoAPESecuenciaClientesService {

	@Resource(name="spusicc.procesoAPESecuenciaClientesDAO")
	private ProcesoAPESecuenciaClientesDAO procesoAPESecuenciaClientesDAO;
		


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPESecuenciaClientesService#executeSecuenciaClientes(java.util.Map)
	 */
	public void executeSecuenciaClientes(Map criteria){
		procesoAPESecuenciaClientesDAO.deleteSecuenciaClientes(criteria);
		procesoAPESecuenciaClientesDAO.insertSecuenciaClientes(criteria);		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPESecuenciaClientesService#executeProcesoAPECargaRutasCliente(java.util.Map)
	 */
	public void executeProcesoAPECargaRutasCliente(Map queryParams) {
		procesoAPESecuenciaClientesDAO.executeProcesoAPECargaRutasCliente(queryParams);
	}
}

