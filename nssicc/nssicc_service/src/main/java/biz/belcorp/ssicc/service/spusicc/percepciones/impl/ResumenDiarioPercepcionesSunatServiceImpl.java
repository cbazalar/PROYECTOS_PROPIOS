package biz.belcorp.ssicc.service.spusicc.percepciones.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.percepciones.ResumenDiarioPercepcionesSunatDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.percepciones.ResumenDiarioPercepcionesSunatService;



@Service("spusicc.resumenDiarioPercepcionesSunatService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ResumenDiarioPercepcionesSunatServiceImpl extends BaseService implements ResumenDiarioPercepcionesSunatService{

	@Resource(name="spusicc.resumenDiarioPercepcionesSunatDAO")
	ResumenDiarioPercepcionesSunatDAO resumenDiarioPercepcionesSunatDAO;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.percepciones.ResumenDiarioPercepcionesSunatService#executeGenerarResumenDiarioPercepcionesSunat(java.util.Map)
	 */
	public void executeGenerarResumenDiarioPercepcionesSunat(Map parametros) {		
		resumenDiarioPercepcionesSunatDAO.executeGenerarResumenDiarioPercepcionesSunat(parametros);
	}
 
}
