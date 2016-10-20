package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBGenerarCronogramaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBGenerarCronogramaService;


/**
 * Service que controla la Generacion del Cronograma
 *  
 * <p>
 * <a href="ProcesoCOBGenerarCronogramaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Service("spusicc.procesoCOBGenerarCronogramaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOBGenerarCronogramaServiceImpl extends BaseService implements ProcesoCOBGenerarCronogramaService {

	@Resource(name="spusicc.procesoCOBGenerarCronogramaDAO")
	private ProcesoCOBGenerarCronogramaDAO procesoCOBGenerarCronogramaDAO;

	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ReporteService#executeAsignacionCartera(java.util.Map)
	 */
	public void executeGenerarCronograma (Map criteria) {
		this.procesoCOBGenerarCronogramaDAO.executeGenerarCronograma(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionGerenteZonaService#getComisionPeriodoLideresByCriteria(java.util.Map)
	 */
	public List getCronogramaByPaisSociedadEtapaPeriodo(Map criteria) {
		return this.procesoCOBGenerarCronogramaDAO.getCronogramaByPaisSociedadEtapaPeriodo(criteria);
	}



}
