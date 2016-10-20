/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMResponsablesUADAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMResponsablesUAService;


/**
 * @author <a href="">Jose Luis Rodriguez</a>
 *
 */
@Service("spusicc.mantenimientoCOMResponsablesUAService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMResponsablesUAServiceImpl extends BaseService implements MantenimientoCOMResponsablesUAService {
	
	@Resource(name="spusicc.mantenimientoCOMResponsablesUADAO")
	MantenimientoCOMResponsablesUADAO mantenimientoCOMResponsablesUADAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMResponsablesUAService#getListaResponsablesUA(java.util.Map)
	 */
	public List getListaResponsablesUA(Map criteria) {		
		return mantenimientoCOMResponsablesUADAO.getListaResponsablesUA(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMResponsablesUAService#updateResponsableUA(java.util.Map)
	 */
	public void updateResponsableUA(Map criteria){
		mantenimientoCOMResponsablesUADAO.updateResponsableUA(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMResponsablesUAService#getValidaResponsable(java.util.Map)
	 */
	public String getValidaResponsable(Map criteria) {
		return mantenimientoCOMResponsablesUADAO.getValidaResponsable(criteria);
			}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMResponsablesUAService#getCodigosSubGerenciaRegionByCodigoZona(java.lang.String)
	 */
	public Map getCodigosSubGerenciaRegionByCodigoZona(String codigoZona) {
		return mantenimientoCOMResponsablesUADAO.getCodigosSubGerenciaRegionByCodigoZona(codigoZona);
			}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMResponsablesUAService#insertResponsableUA(java.util.Map)
	 */
	public void insertResponsableUA(Map criteria) {
		mantenimientoCOMResponsablesUADAO.insertResponsableUA(criteria);
		}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMResponsablesUAService#deleteResponsableUA(java.lang.Integer)
	 */
	public void deleteResponsableUA(Integer oidHistoGere) {
		mantenimientoCOMResponsablesUADAO.deleteResponsableUA(oidHistoGere);
	}
}