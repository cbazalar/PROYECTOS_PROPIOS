package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ConsultaLETResultadosConcursosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ConsultaLETResultadosConcursosService;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.consultaLETResultadosConcursosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaLETResultadosConcursosServiceImpl extends BaseService implements ConsultaLETResultadosConcursosService{
	
	@Resource(name="spusicc.consultaLETResultadosConcursosDAO")
	private ConsultaLETResultadosConcursosDAO consultaLETResultadosConcursosDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getParametroConcursoDescCompletaList(java.util.Map)
	 */
	public List getParametroConcursoDescCompletaList(Map criteria) {
		return consultaLETResultadosConcursosDAO.getParametroConcursoDescCompletaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getConsultaLETResultadosConcursos(java.util.Map)
	 */
	public List getConsultaLETResultadosConcursos(Map criterios) {
		return consultaLETResultadosConcursosDAO.getConsultaLETResultadosConcursos(criterios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getResultadoConcursoByCriteria(java.util.Map)
	 */
	public Map getResultadoConcursoByCriteria(Map criteria) {
		return consultaLETResultadosConcursosDAO.getResultadoConcursoByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getHistoricoClasificacionByCriteria(java.util.Map)
	 */
	public Map getHistoricoClasificacionByCriteria(Map criteria) {
		return consultaLETResultadosConcursosDAO.getHistoricoClasificacionByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getUltimaSeccionCargoConcursoByCriteria(java.util.Map)
	 */
	public Map getUltimaSeccionCargoConcursoByCriteria(Map criteria) {
		return consultaLETResultadosConcursosDAO.getUltimaSeccionCargoConcursoByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getGestionSeccionActualByCriteria(java.util.Map)
	 */
	public Map getGestionSeccionActualByCriteria(Map criteria) {
		return consultaLETResultadosConcursosDAO.getGestionSeccionActualByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getDetalleResultadosConcursosListByCriteria(java.util.Map)
	 */
	public List getDetalleResultadosConcursosListByCriteria(Map criteria) {
		return consultaLETResultadosConcursosDAO.getDetalleResultadosConcursosListByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getRecomendacionesListByCriteria(java.util.Map)
	 */
	public List getRecomendacionesListByCriteria(Map criteria) {
		return consultaLETResultadosConcursosDAO.getRecomendacionesListByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getTotalRecomendadasByCriteria(java.util.Map)
	 */
	public Integer getTotalRecomendadasByCriteria(Map criteria) {
		return consultaLETResultadosConcursosDAO.getTotalRecomendadasByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getTotalIngresosEfectivos(java.util.Map)
	 */
	public Integer getTotalIngresosEfectivos(Map criteria) {
		return consultaLETResultadosConcursosDAO.getTotalIngresosEfectivos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ConsultaLETResultadosConcursosService#getRotacionesListByCriteria(java.util.Map)
	 */
	public List getRotacionesListByCriteria(Map criteria) {
		return consultaLETResultadosConcursosDAO.getRotacionesListByCriteria(criteria);
	}	
}