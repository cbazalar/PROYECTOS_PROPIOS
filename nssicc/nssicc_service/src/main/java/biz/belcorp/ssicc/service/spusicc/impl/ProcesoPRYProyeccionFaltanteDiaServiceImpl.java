/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ProcesoPRYProyeccionFaltanteDiaDAO;
import biz.belcorp.ssicc.dao.spusicc.proyeccion.model.PorcentajeFaltante;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPRYProyeccionFaltanteDiaService;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
@Service("spusicc.procesoPRYProyeccionFaltanteDiaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPRYProyeccionFaltanteDiaServiceImpl extends BaseService implements ProcesoPRYProyeccionFaltanteDiaService {

	@Resource(name="spusicc.procesoPRYProyeccionFaltanteDiaDAO")
	private ProcesoPRYProyeccionFaltanteDiaDAO procesoPRYProyeccionFaltanteDiaDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#executeCargoAbonoDirecto(java.util.Map)
	 */
	public void executeProyeccionFaltante(Map criteria) {
		procesoPRYProyeccionFaltanteDiaDAO.executeProyeccionFaltante(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#existeIndicadorValorCritico(java.util.Map)
	 */
	public Integer existeIndicadorValorCritico(Map criteria) {
		return procesoPRYProyeccionFaltanteDiaDAO.existeIndicadorValorCritico(criteria);
	}
	
	/**
	 * @return Returns the procesoPRYProyeccionFaltanteDiaDAO.
	 */
	public ProcesoPRYProyeccionFaltanteDiaDAO getProcesoPRYProyeccionFaltanteDiaDAO() {
		return procesoPRYProyeccionFaltanteDiaDAO;
	}

	/**
	 * @param procesoPRYProyeccionFaltanteDiaDAO The procesoPRYProyeccionFaltanteDiaDAO to set.
	 */
	public void setProcesoPRYProyeccionFaltanteDiaDAO(
			ProcesoPRYProyeccionFaltanteDiaDAO procesoPRYProyeccionFaltanteDiaDAO) {
		this.procesoPRYProyeccionFaltanteDiaDAO = procesoPRYProyeccionFaltanteDiaDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#getProyeccionFaltanteGrupo(java.util.Map)
	 */
	public List getProyeccionFaltanteGrupo(Map criteria) {
		return this.procesoPRYProyeccionFaltanteDiaDAO.getProyeccionFaltanteGrupo(criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#getProyeccionFaltanteProducto(java.util.Map)
	 */
	public List getProyeccionFaltanteProducto(Map criteria) {
		return this.procesoPRYProyeccionFaltanteDiaDAO.getProyeccionFaltanteProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#getProyeccionFaltanteProductoTodos(java.util.Map)
	 */
	public List getProyeccionFaltanteProductoTodos(Map criteria) {
		return this.procesoPRYProyeccionFaltanteDiaDAO.getProyeccionFaltanteProductoTodos(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#getPeriodoActual(java.util.Map)
	 */
	public String getPeriodoActual(Map criteria) {
		return this.procesoPRYProyeccionFaltanteDiaDAO.getPeriodoActual(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#getTotalOC(java.util.Map)
	 */
	public Integer getTotalOC(Map criteria) {
		return this.procesoPRYProyeccionFaltanteDiaDAO.getTotalOC(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#getMaximoVersionProyeccionFaltanteDia(java.util.Map)
	 */
	public String getMaximoVersionProyeccionFaltanteDia(Map criteria) {
		return this.procesoPRYProyeccionFaltanteDiaDAO.getMaximoVersionProyeccionFaltanteDia(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#getPorcentajeFaltante(biz.belcorp.ssicc.spusicc.ventas.model.PorcentajeFaltante)
	 */
	public List getPorcentajeFaltante(PorcentajeFaltante porcentaje) {
		return this.procesoPRYProyeccionFaltanteDiaDAO.getPorcentajeFaltante(porcentaje);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#updatePorcentajeFaltante(biz.belcorp.ssicc.spusicc.proyeccion.model.PorcentajeFaltante, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario){
		this.procesoPRYProyeccionFaltanteDiaDAO.updatePorcentajeFaltante(porcentaje,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#insertPorcentajeFaltante(biz.belcorp.ssicc.spusicc.proyeccion.model.PorcentajeFaltante, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario){
		this.procesoPRYProyeccionFaltanteDiaDAO.insertPorcentajeFaltante(porcentaje,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#deletePorcentajeFaltante(biz.belcorp.ssicc.spusicc.proyeccion.model.PorcentajeFaltante, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deletePorcentajeFaltante(PorcentajeFaltante porcentaje, Usuario usuario){
		this.procesoPRYProyeccionFaltanteDiaDAO.deletePorcentajeFaltante(porcentaje,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.service.ProcesoPRYProyeccionFaltanteDiaService#getDevuelveDescripcionPeriodo(java.util.Map)
	 */
	public String getDevuelveDescripcionPeriodo(Map criteria) {
		return this.procesoPRYProyeccionFaltanteDiaDAO.getDevuelveDescripcionPeriodo(criteria);
	}
}
