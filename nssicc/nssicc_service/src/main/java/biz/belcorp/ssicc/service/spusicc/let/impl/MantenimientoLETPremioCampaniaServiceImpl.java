package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETPremioCampaniaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETPremioCampaniaService;

@SuppressWarnings("rawtypes")
@Service("spusicc.mantenimientoLETPremioCampaniaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLETPremioCampaniaServiceImpl extends BaseService implements MantenimientoLETPremioCampaniaService {
	
	@Resource(name="spusicc.mantenimientoLETPremioCampaniaDAO")
	private MantenimientoLETPremioCampaniaDAO mantenimientoLETPremioCampaniaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#getNumeroConcursoList(java.util.Map)
	 */
	public List getNumeroConcursoList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaServiceImpl - getNumeroConcursoList(java.util.Map)");
		List lista = mantenimientoLETPremioCampaniaDAO.getNumeroConcursoList(criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaServiceImpl - getNumeroConcursoList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#getPremioCampaniaList(java.util.Map)
	 */
	public List getPremioCampaniaList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaServiceImpl - getPremioCampaniaList(java.util.Map)");
		List lista = mantenimientoLETPremioCampaniaDAO.getPremioCampaniaList(criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaServiceImpl - getPremioCampaniaList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#getNivelRangoList(java.util.Map)
	 */
	public List getNivelRangoList(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaServiceImpl - getNivelRangoList(java.util.Map)");
		List lista = mantenimientoLETPremioCampaniaDAO.getNivelRangoList(criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaServiceImpl - getNivelRangoList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#deletePremioCampania(java.util.Map)
	 */
	public void deletePremioCampania(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaServiceImpl - deletePremioCampania(java.util.Map)");
		mantenimientoLETPremioCampaniaDAO.deletePremioCampania(criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaServiceImpl - deletePremioCampania(java.util.Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#getValidaCodigoVenta(java.util.Map)
	 */
	public int getValidaCodigoVenta(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaServiceImpl - getValidaCodigoVenta(java.util.Map)");
		int lista = mantenimientoLETPremioCampaniaDAO.getValidaCodigoVenta(criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaServiceImpl - getValidaCodigoVenta(java.util.Map) - Resultado:"+lista);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDPremioCampaniaService#insertPremioCampania(java.util.Map)
	 */
	public void insertPremioCampania(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaServiceImpl - insertPremioCampania(java.util.Map)");
		mantenimientoLETPremioCampaniaDAO.insertPremioCampania(criteria);
		log.info("Salio a MantenimientoLETPremioCampaniaServiceImpl - insertPremioCampania(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETPremioCampaniaService#getValidaPremioCampaniaExiste(java.util.Map)
	 */
	public String getValidaPremioCampaniaExiste(Map criteria) {
		log.info("Entro a MantenimientoLETPremioCampaniaServiceImpl - getValidaPremioCampaniaExiste(java.util.Map)");
		String resultado = mantenimientoLETPremioCampaniaDAO.getValidaPremioCampaniaExiste(criteria);
		log.info("Entro a MantenimientoLETPremioCampaniaServiceImpl - getValidaPremioCampaniaExiste(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	/**
	 * @return the mantenimientoLETPremioCampaniaDAO
	 */
	public MantenimientoLETPremioCampaniaDAO getMantenimientoLETPremioCampaniaDAO() {
		return mantenimientoLETPremioCampaniaDAO;
	}

	/**
	 * @param mantenimientoLETPremioCampaniaDAO the mantenimientoLETPremioCampaniaDAO to set
	 */
	public void setMantenimientoLETPremioCampaniaDAO(
			MantenimientoLETPremioCampaniaDAO mantenimientoLETPremioCampaniaDAO) {
		this.mantenimientoLETPremioCampaniaDAO = mantenimientoLETPremioCampaniaDAO;
	}

}
