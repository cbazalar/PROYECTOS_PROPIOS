/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.MinimoNuevas;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMMinimoNuevasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMMinimoNuevasService;

/**
 * @author jvelasquez
 *
 */
@Service("spusicc.mantenimientoCOMMinimoNuevasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMMinimoNuevasServiceImpl extends BaseService implements MantenimientoCOMMinimoNuevasService {
	
	@Resource(name="spusicc.mantenimientoCOMMinimoNuevasDAO")
	private MantenimientoCOMMinimoNuevasDAO mantenimientoCOMMinimoNuevasDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMMinimoNuevasService#getMinimiNuevasList(java.util.Map)
	 */
	public List getMinimoNuevasList(Map criteria) {
		return mantenimientoCOMMinimoNuevasDAO.getMinimoNuevasList(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMMinimoNuevasService#getMinimoNuevas(java.util.Map)
	 */
	public MinimoNuevas getMinimoNuevas(Map criteria) {
		return mantenimientoCOMMinimoNuevasDAO.getMinimoNuevas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMMinimoNuevasService#insertMinimoNuevas(java.util.Map)
	 */
	public String insertMinimoNuevas(Map criteria) {
		String resultado = "";
		String codigoZona = (String)criteria.get("codigoZona");
		List lista = new ArrayList();
		if (StringUtils.isNotEmpty(codigoZona)) {
			//Validar ZONAS
			log.debug("Validando Zonas");
			
			//Validar region duplicada
			lista = mantenimientoCOMMinimoNuevasDAO.getMinimoNuevasDuplicada(criteria);
			
			if (lista != null && lista.size() > 0) {
				return resultado = "Existe regin registrada con cantidad mnima";
			}
			
			lista = new ArrayList();
			lista = mantenimientoCOMMinimoNuevasDAO.getValidarMinimoNuevasZonas(criteria);
			if (lista != null && lista.size() > 0) {
				return resultado = "Existe zona registrada con cantidad mnima";
			}
			
			log.debug("Insertando Minimo Nuevas");
			mantenimientoCOMMinimoNuevasDAO.insertMinimoNuevas(criteria);
		}else {
			//Validar Regiones
			log.debug("Validando Regiones");
			
			lista = mantenimientoCOMMinimoNuevasDAO.getMinimoNuevasDuplicada(criteria);
			
			if (lista != null && lista.size() > 0) {
				return resultado = "Existe regin registrada con cantidad mnima";
			}
			
			lista = new ArrayList();
			lista = mantenimientoCOMMinimoNuevasDAO.getValidarMinimoNuevasRegion(criteria);
			if (lista != null && lista.size() > 0) {
				return resultado = "Existen zonas registradas para la regin";
			}
			
			log.debug("Insertando Minimo Nuevas");
			mantenimientoCOMMinimoNuevasDAO.insertMinimoNuevas(criteria);
		}
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMMinimoNuevasService#updateMinimoNuevas(java.util.Map)
	 */
	public void updateMinimoNuevas(Map criteria) {
		mantenimientoCOMMinimoNuevasDAO.updateMinimoNuevas(criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMMinimoNuevasService#deleteMinimoNuevas(java.util.Map)
	 */
	public void deleteMinimoNuevas(Map criteria) {
		mantenimientoCOMMinimoNuevasDAO.deleteMinimoNuevas(criteria);
	}


}
