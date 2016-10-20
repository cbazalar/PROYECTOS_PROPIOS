/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.ProcesoCOMCalculoVariablesNivelSeccionCampaniaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoVariablesNivelSeccionCampaniaService;

/**
 * @author peextllizana
 *
 */
@Service("spusicc.ProcesoCOMCalculoVariablesNivelSeccionCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCOMCalculoVariablesNivelSeccionCampaniaServiceImpl extends
		BaseService implements
		ProcesoCOMCalculoVariablesNivelSeccionCampaniaService {
	
	@Resource(name="spusicc.procesoCOMCalculoVariablesNivelSeccionCampaniaDAO")
	ProcesoCOMCalculoVariablesNivelSeccionCampaniaDAO procesoCOMCalculoVariablesNivelSeccionCampaniaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.ProcesoCOMCalculoVariablesNivelSeccionCampaniaService#executeCalculoVariablesNivelSeccionCampania(java.util.Map)
	 */
	public void executeCalculoVariablesNivelSeccionCampania(Map params) {
		procesoCOMCalculoVariablesNivelSeccionCampaniaDAO.executeCalculoVariablesNivelSeccionCampania(params);

	}


}
