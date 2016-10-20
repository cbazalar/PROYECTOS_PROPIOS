package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPEPreasignacionPorVariacionEstimadosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.ProcesoAPEPreasignacionPorVariacionEstimadosService;

/**
 *  
 * <p>
 * <a href="ProcesoAPEPreasignacionPorVariacionEstimadosServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Christian Gonzales Komiya</a>
 * 
 */
@Service("spusicc.procesoAPEPreasignacionPorVariacionEstimadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 public class ProcesoAPEPreasignacionPorVariacionEstimadosServiceImpl extends BaseService implements ProcesoAPEPreasignacionPorVariacionEstimadosService{

	@Resource(name="spusicc.procesoAPEPreasignacionPorVariacionEstimadosDAO")
 	private ProcesoAPEPreasignacionPorVariacionEstimadosDAO procesoAPEPreasignacionPorVariacionEstimadosDAO;
	
 	 	

	public List getFuentePeriodoPreasignacionList() {
		
		return procesoAPEPreasignacionPorVariacionEstimadosDAO.getFuentePeriodoPreasignacionList();
	}

	public void executePreasignacionPorVariacionEstimados(Map criteria) {
		procesoAPEPreasignacionPorVariacionEstimadosDAO.executePreasignacionPorVariacionEstimados(criteria);
		
	}

	public String getCodigoFuncionDistribucionPorOidSublinea(String oidSublinea) {		
		return procesoAPEPreasignacionPorVariacionEstimadosDAO.getCodigoFuncionDistribucionPorOidSublinea(oidSublinea);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEPreasignacionPorVariacionEstimadosService#executePreasignacionAframe(java.util.Map)
	 */
	public void executePreasignacionAframe(Map criteria) {
		procesoAPEPreasignacionPorVariacionEstimadosDAO.executePreasignacionAframe(criteria);
		
	}

 }