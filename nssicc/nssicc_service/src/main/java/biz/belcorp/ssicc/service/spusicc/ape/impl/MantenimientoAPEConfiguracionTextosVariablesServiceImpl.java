package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEConfiguracionTextosVariablesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEConfiguracionTextosVariablesService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEConfiguracionTextosVariablesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 * 
 */
@Service("spusicc.mantenimientoAPEConfiguracionTextosVariablesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEConfiguracionTextosVariablesServiceImpl extends BaseService implements MantenimientoAPEConfiguracionTextosVariablesService{
	
	@Resource(name="spusicc.mantenimientoAPEConfiguracionTextosVariablesDAO")
	private MantenimientoAPEConfiguracionTextosVariablesDAO mantenimientoAPEConfiguracionTextosVariablesDAO;


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#insertConfiguracionTextosVariables(java.util.Map)
	 */
	public void insertConfiguracionTextosVariables(Map criteria){
		mantenimientoAPEConfiguracionTextosVariablesDAO.insertConfiguracionTextosVariables(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#updateConfiguracionTextosVariables(java.util.Map)
	 */
	public void updateConfiguracionTextosVariables(Map criteria) {
		mantenimientoAPEConfiguracionTextosVariablesDAO.updateConfiguracionTextosVariables(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#deleteConfiguracionTextosVariables(java.util.Map, java.lang.String[])
	 */
	public void deleteConfiguracionTextosVariables(Map criteria,String[] items) {
		for(int i = 0; i < items.length; i++){
			String id = items[i];
			criteria.put("oidConfTextVari", StringUtils.split(id, "|")[0]);
			this.mantenimientoAPEConfiguracionTextosVariablesDAO.deleteConfiguracionTextosVariables(criteria);	
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#getNextOidConfiguracionTextosVariables()
	 */
	public int getNextOidConfiguracionTextosVariables() {
		return mantenimientoAPEConfiguracionTextosVariablesDAO.getNextOidConfiguracionTextosVariables();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#getTipoClienteList()
	 */
	public List getTipoClienteList() {
		return mantenimientoAPEConfiguracionTextosVariablesDAO.getTipoClienteList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#getSubTiClienteList(java.util.Map)
	 */
	public List getSubTiClienteList(Map criteria) {
		return mantenimientoAPEConfiguracionTextosVariablesDAO.getSubTiClienteList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#getTipoClasificacionByOidSubTipoClienteList(java.util.Map)
	 */
	public List getTipoClasificacionByOidSubTipoClienteList(Map criteria) {
		return mantenimientoAPEConfiguracionTextosVariablesDAO.getTipoClasificacionByOidSubTipoClienteList(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#getClasificacionByOidTipoClasificacionList(java.util.Map)
	 */
	public List getClasificacionByOidTipoClasificacionList(Map criteria) {
		return mantenimientoAPEConfiguracionTextosVariablesDAO.getClasificacionByOidTipoClasificacionList(criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#getConfiTextoVariaList(java.util.Map)
	 */
	public List getConfiTextoVariaList(Map criteria) {
		return mantenimientoAPEConfiguracionTextosVariablesDAO.getConfiTextoVariaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEConfiguracionTextosVariablesService#getExisteConfiguracionTextosVariablesCD(java.util.Map)
	 */
	public int getExisteConfiguracionTextosVariablesCD(Map criteria){
		return mantenimientoAPEConfiguracionTextosVariablesDAO.getExisteConfiguracionTextosVariablesCD(criteria);
	}
	
}