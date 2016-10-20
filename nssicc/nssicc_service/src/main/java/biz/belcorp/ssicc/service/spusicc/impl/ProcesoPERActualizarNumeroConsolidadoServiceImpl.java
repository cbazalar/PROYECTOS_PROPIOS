/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPERActualizarNumeroConsolidadoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERActualizarNumeroConsolidadoService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoPERActualizarNumeroConsolidadoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */

@Service("spusicc.procesoPERActualizarNumeroConsolidadoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPERActualizarNumeroConsolidadoServiceImpl extends BaseService implements ProcesoPERActualizarNumeroConsolidadoService {

	@Resource(name="spusicc.procesoPERActualizarNumeroConsolidadoDAO")
	ProcesoPERActualizarNumeroConsolidadoDAO procesoPERActualizarNumeroConsolidadoDAO;

	/*
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPERActualizarNumeroConsolidadoService#executeActualizarNumeroConsolidado(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void executeActualizarNumeroConsolidado(Map criteria, Usuario usuario) {
		procesoPERActualizarNumeroConsolidadoDAO.updateNumeroConsolidadoPercepciones(criteria, usuario);
		
	}
}
