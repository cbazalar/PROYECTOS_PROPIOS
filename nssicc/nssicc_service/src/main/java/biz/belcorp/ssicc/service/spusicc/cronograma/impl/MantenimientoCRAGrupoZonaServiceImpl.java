/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.cronograma.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRAGrupoZonaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAGrupoZonaService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCRAGrupoZonaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:rramirez@belcorp.biz">Rosalvina Ramirez Guardia</a>
 */
@Service("spusicc.mantenimientoCRAGrupoZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCRAGrupoZonaServiceImpl extends BaseService implements MantenimientoCRAGrupoZonaService{

	@Resource(name="spusicc.mantenimientoCRAGrupoZonaDAO")
	private MantenimientoCRAGrupoZonaDAO  mantenimientoCRAGrupoZonaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAGrupoZonaService#getGrupos(java.util.Map)
	 */
	public List getGrupos(Map criteria){
		return mantenimientoCRAGrupoZonaDAO.getGrupos(criteria);
    }
	
	/**
	 * Metodo que devuelve los grupos y zonas.
	 * @param criteria
	 * @return
	 */
	public List getGrupoZonas(Map criteria){
		return mantenimientoCRAGrupoZonaDAO.getGrupoZonas(criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAGrupoZonaService#deleteGrupoZona(java.util.Map)
	 */
	public void deleteGrupoZona(Map criteria){
		mantenimientoCRAGrupoZonaDAO.deleteGrupoZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAGrupoZonaService#getGrupoRegionNoAsignadas()
	 */
	public List getGrupoRegionNoAsignadas(){
		return mantenimientoCRAGrupoZonaDAO.getGrupoRegionNoAsignadas();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAGrupoZonaService#getZonaAsignadasGrupo(java.lang.String)
	 */
	public List getZonaAsignadasGrupo(Map criteria){
		return mantenimientoCRAGrupoZonaDAO.getZonaAsignadasGrupo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAGrupoZonaService#insertNombreGrupoZona(java.util.Map)
	 */
	public void insertNombreGrupoZona(Map map){
		mantenimientoCRAGrupoZonaDAO.insertNombreGrupoZona(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAGrupoZonaService#updateNombreGrupoZona(java.util.Map)
	 */
	public void updateNombreGrupoZona(Map map){
		mantenimientoCRAGrupoZonaDAO.updateNombreGrupoZona(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAGrupoZonaService#updateZonasAsignadas(java.util.Map)
	 */
	public void updateZonasAsignadas(Map map) {
		mantenimientoCRAGrupoZonaDAO.updateZonasAsignadas(map);		
	}
}
