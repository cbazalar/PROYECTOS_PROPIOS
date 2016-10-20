package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCargaDespachoConcursoRxPDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaDespachoConcursoRxPService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoINCCargaDespachoConcursoRxPService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCCargaDespachoConcursoRxPServiceImpl extends BaseService implements
	ProcesoINCCargaDespachoConcursoRxPService {
		
	@Resource(name="spusicc.procesoINCCargaDespachoConcursoRxPDAO")
	private ProcesoINCCargaDespachoConcursoRxPDAO procesoINCCargaDespachoConcursoRxPDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaDespachoConcursoRxPService#getListConcursoVigentesRxP()
	 */
	public List getListConcursoVigentesRxP() {
		return this.procesoINCCargaDespachoConcursoRxPDAO.getListConcursoVigentesRxP();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaDespachoConcursoRxPService#validarDespachoConcursoRxP(java.util.Map)
	 */
	public String validarDespachoConcursoRxP(Map criteria) {
		return this.procesoINCCargaDespachoConcursoRxPDAO.validarDespachoConcursoRxP(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaDespachoConcursoRxPService#executeCargaDespachoConcursoRxP(java.util.Map)
	 */
	public void executeCargaDespachoConcursoRxP(Map params) {
		String encontrado = (String)params.get("encontrado");
		List listaCodigosVenta = (List)params.get("listaCodigosVenta");
		
		if(encontrado.equals(Constants.NUMERO_UNO)) {
			//this.procesoINCCargaDespachoConcursoRxPDAO.updateDespachoConcursoRxP(params);
			this.procesoINCCargaDespachoConcursoRxPDAO.deleteDespachoConcursoRxPDetalle(params);
		} else {
			this.procesoINCCargaDespachoConcursoRxPDAO.insertDespachoConcursoRxP(params);
		}
		
		for(int i=0; i<listaCodigosVenta.size(); i++) {
			params.put("codigoVenta", (String)listaCodigosVenta.get(i));
			
			this.procesoINCCargaDespachoConcursoRxPDAO.insertDespachoConcursoRxPDetalle(params);
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaDespachoConcursoRxPService#getDespachoConcursoRxPDetalle(java.util.Map)
	 */
	public List getDespachoConcursoRxPDetalle(Map criteria) {
		return this.procesoINCCargaDespachoConcursoRxPDAO.getDespachoConcursoRxPDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.ProcesoINCCargaDespachoConcursoRxPService#getDespachoConcursoRxP(java.util.Map)
	 */
	public List getDespachoConcursoRxP(Map criteria) {
		return this.procesoINCCargaDespachoConcursoRxPDAO.getDespachoConcursoRxP(criteria);
	}
	
}
