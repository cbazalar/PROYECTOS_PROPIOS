package biz.belcorp.ssicc.service.edu.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDURegistroAsistenciaDAO;
import biz.belcorp.ssicc.dao.edu.model.CursoCapacitacion;
import biz.belcorp.ssicc.dao.edu.model.DetallePlanilla;
import biz.belcorp.ssicc.dao.edu.model.HistoricoAptas;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadas;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadasDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictado;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictadoDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPedido;
import biz.belcorp.ssicc.dao.edu.model.HistoricoPlanillaInstructora;
import biz.belcorp.ssicc.dao.edu.model.PlanillaProgramacionCurso;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.edu.ProcesoEDURegistroAsistenciaService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author peextrvela
 *
 */
@Service("edu.procesoEDURegistroAsistenciaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDURegistroAsistenciaServiceImpl extends 
	BaseService implements ProcesoEDURegistroAsistenciaService{
	
	@Resource(name="edu.procesoEDURegistroAsistenciaDAO")
	ProcesoEDURegistroAsistenciaDAO procesoEDURegistroAsistenciaDAO;
	
	@Resource(name="edu.mantenimientoEDUCursoCapacitacionDAO")
	MantenimientoEDUCursoCapacitacionDAO mantenimientoEDUCursoCapacitacionDAO;
										  
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas)
	 */
	public List getHistoricoAptas(HistoricoAptas historicoAptas) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoAptas(historicoAptas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas)
	 */
	public List getHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoCapacitadas(historicoCapacitadas);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle)
	 */
	public List getHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoCapacitadasDetalle(historicoCapacitadasDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado)
	 */
	public List getHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoCursoDictado(historicoCursoDictado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle)
	 */
	public List getHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoCursoDictadoDetalle(historicoCursoDictadoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getMaxHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado)
	 */
	public String getMaxHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getMaxHistoricoCursoDictado(historicoCursoDictado);
	}

	public String existClienteCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle) {
		return procesoEDURegistroAsistenciaDAO.existClienteCursoDictadoDetalle(historicoCursoDictadoDetalle);
	}
	public String existClienteCursoCapacitadaDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle) {
		return procesoEDURegistroAsistenciaDAO.existClienteCursoCapacitadaDetalle(historicoCapacitadasDetalle);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getPlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso)
	 */
	public List getPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getPlanillaProgramacionCurso(planillaProgramacionCurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.insertHistoricoAptas(historicoAptas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.insertHistoricoCapacitadas(historicoCapacitadas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.insertHistoricoCapacitadasDetalle(historicoCapacitadasDetalle,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.insertHistoricoCursoDictado(historicoCursoDictado,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.insertHistoricoCursoDictadoDetalle(historicoCursoDictadoDetalle,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#insertPlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.insertPlanillaProgramacionCurso(planillaProgramacionCurso,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeFisicoHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeFisicoHistoricoAptas(historicoAptas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeFisicoHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeFisicoHistoricoCapacitadas(historicoCapacitadas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeFisicoHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeFisicoHistoricoCapacitadasDetalle(historicoCapacitadasDetalle,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeFisicoHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeFisicoHistoricoCursoDictado(historicoCursoDictado,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeFisicoHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeFisicoHistoricoCursoDictadoDetalle(historicoCursoDictadoDetalle,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeFisicoPlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeFisicoPlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeFisicoPlanillaProgramacionCurso(planillaProgramacionCurso,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeHistoricoAptas(historicoAptas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeHistoricoCapacitadas(historicoCapacitadas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeHistoricoCapacitadasDetalle(historicoCapacitadasDetalle,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeHistoricoCursoDictado(historicoCursoDictado,usuario);		
	}	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removeHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeHistoricoCursoDictadoDetalle(historicoCursoDictadoDetalle,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#removePlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removePlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removePlanillaProgramacionCurso(planillaProgramacionCurso,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoAptas(HistoricoAptas historicoAptas, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.updateHistoricoAptas(historicoAptas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateHistoricoCapacitadas(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadas, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCapacitadas(HistoricoCapacitadas historicoCapacitadas, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.updateHistoricoCapacitadas(historicoCapacitadas,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.updateHistoricoCapacitadasDetalle(historicoCapacitadasDetalle,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.updateHistoricoCursoDictado(historicoCursoDictado,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCursoDictadoWs(HistoricoCursoDictado historicoCursoDictado, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.updateHistoricoCursoDictadoWs(historicoCursoDictado,usuario);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateHistoricoCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.updateHistoricoCursoDictadoDetalle(historicoCursoDictadoDetalle,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updatePlanillaProgramacionCurso(biz.belcorp.ssicc.edu.dao.model.PlanillaProgramacionCurso, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePlanillaProgramacionCurso(PlanillaProgramacionCurso planillaProgramacionCurso, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.updatePlanillaProgramacionCurso(planillaProgramacionCurso,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getCapacitadasTemporal(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla)
	 */
	public List getCapacitadasTemporal(DetallePlanilla detallePlanilla) {
		return procesoEDURegistroAsistenciaDAO.getCapacitadasTemporal(detallePlanilla);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#insertCapacitadasTemporal(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.insertCapacitadasTemporal(detallePlanilla,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#removeCapacitadasTemporal(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.removeCapacitadasTemporal(detallePlanilla,usuario);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#updateCapacitadasTemporal(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateCapacitadasTemporal(DetallePlanilla detallePlanilla, Usuario usuario) {
		procesoEDURegistroAsistenciaDAO.removeCapacitadasTemporal(detallePlanilla,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#insertRegistroAsistencia(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario, org.apache.struts.action.ActionMessages)
	 */
	public boolean insertRegistroAsistencia(HistoricoCursoDictado cursoDictado,List listaTemporal, Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		DetallePlanilla detalleTemp = new DetallePlanilla();
		HistoricoCursoDictadoDetalle detalleCurso = new HistoricoCursoDictadoDetalle();
		HistoricoCapacitadas historicoCapacitadas = new HistoricoCapacitadas();
		HistoricoCapacitadasDetalle historicoCapacitadasDetalle = new HistoricoCapacitadasDetalle();
		HistoricoPlanillaInstructora historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
		List listaPlanillasInstructora = new LinkedList();
		if (log.isDebugEnabled()) {                        
			log.debug("Entering 'insertRegistroAsistencia' method");
			log.debug(cursoDictado);   	
			log.debug(listaTemporal);
			log.debug(usuario);
		}   
		
		/* Obteniendo dato del Curso */
		CursoCapacitacion cursoCapacitacion = new CursoCapacitacion();
		Map criterios = new HashMap();				
		criterios.put("codigoPais", cursoDictado.getCodigoPais());
		criterios.put("codigoEmpresa", cursoDictado.getCodigoEmpresa());
		criterios.put("codigoCurso", cursoDictado.getCodigoCurso());
		List listaCurso = mantenimientoEDUCursoCapacitacionDAO.getCursosCapacitacionByCriteria(criterios);
		if ((listaCurso!=null) && (listaCurso.size()>0)) {
			cursoCapacitacion = (CursoCapacitacion) listaCurso.get(0);
		}
			/* Fin Obteniendo dato del Curso */
			/*Historico Curso Dictado Cabecera */
			String codigo = procesoEDURegistroAsistenciaDAO.
				getMaxHistoricoCursoDictado(cursoDictado);
			if (StringUtils.isBlank(codigo)) codigo="0";
			cursoDictado.setCodigoCursoDictado(String.valueOf(
					Integer.valueOf(codigo).intValue() + 1));
			cursoDictado.setEstadoRegistro(Constants.ESTADO_ACTIVO);
			cursoDictado.setEstadoCursoDictado(Constants.ESTADO_CURSO_VIGENTE);
			log.debug("cursoCapacitacion.getIndicadorEvaluacionCapacitadora() " +cursoCapacitacion.getIndicadorEvaluacionCapacitadora());
			cursoDictado.setIndicadorEvaluacionCurso(cursoCapacitacion.getIndicadorEvaluacionCapacitadora());
			cursoDictado.setIndicadorEvaluacionCapacitada(cursoCapacitacion.getIndicadorEvaluacionCapacitada());
			if (log.isDebugEnabled()) {                        
				log.debug("Entering 'insertHistoricoCursoDictado' method");
				log.debug(cursoDictado);   	
			}                                                  			
			procesoEDURegistroAsistenciaDAO.insertHistoricoCursoDictado(cursoDictado,usuario);
			/*Llenamos los parametros para obtener los registros de la tabla temporal de los detalles*/
			detalleTemp.setCodigoPais(cursoDictado.getCodigoPais());
			detalleTemp.setCodigoEmpresa(cursoDictado.getCodigoEmpresa());
			detalleTemp.setCodigoCurso(cursoDictado.getCodigoCurso());
			detalleTemp.setCodigoInstructora(cursoDictado.getCodigoInstructora());
			List detallesConsultoras = new LinkedList();
			if (listaTemporal!= null) {
				detallesConsultoras=listaTemporal;	
			}else{
				detallesConsultoras = procesoEDURegistroAsistenciaDAO.
						getCapacitadasTemporal(detalleTemp);
			}
			
			/* Verificando que la campaa de Inicio sea mayor a la Campaa de Programacion de cada consultora */
			boolean verifica = procesoEDURegistroAsistenciaDAO.
								verificarCampannaRegistroAsistenciaInsert(cursoDictado, detallesConsultoras);
			if (!verifica) {
				String mensajeError =  this.messageSource.getMessage("procesoEDURegistroAsistencia.error.verificarCampanna",
										null, getLocale(usuario));
				throw new Exception(mensajeError);
			}	
			
				
			for (int i =0;i<detallesConsultoras.size();i++){
				/*Historico Curso Dictado Detalle */
				detalleTemp = new DetallePlanilla();
				historicoCapacitadasDetalle = new HistoricoCapacitadasDetalle();
				detalleCurso = new HistoricoCursoDictadoDetalle();
				detalleTemp = (DetallePlanilla) detallesConsultoras.get(i);
				BeanUtils.copyProperties(detalleCurso,detalleTemp);
				/*Historico Planilla por Instructora*/
				historicoPlanillaInstructora = new HistoricoPlanillaInstructora();				
				BeanUtils.copyProperties(historicoPlanillaInstructora,detalleTemp);
				historicoPlanillaInstructora.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
				historicoPlanillaInstructora.setCodigoInstructora(cursoDictado.getCodigoInstructora());
				historicoPlanillaInstructora.setCodigoPlanilla(detalleTemp.getCodigoPlanilla());
				synPlanillaInstructora(listaPlanillasInstructora,historicoPlanillaInstructora);
				/*Fin Historico Planillas por Instructora*/
				detalleCurso.setCalificacionConsultora(new Double("0.00"));
				if (Constants.SI.equals(cursoDictado.getIndicadorEvaluacionCapacitada()) && 
					Constants.SI.equals(detalleCurso.getIndicadorAsistencia())) 
					detalleCurso.setCalificacionConsultora(detalleTemp.getCalificacionEvaluacionCurso());				
				detalleCurso.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
				detalleCurso.setCodigoPlanillaProgramacion(detalleTemp.getCodigoPlanilla());
				detalleCurso.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
				procesoEDURegistroAsistenciaDAO.insertHistoricoCursoDictadoDetalle(detalleCurso,usuario);
				if (Constants.SI.equals(detalleCurso.getIndicadorAsistencia())){			
					/* Historico de Capacitadas Cabecera (Cuidado es por Cliente)*/
					historicoCapacitadas = new HistoricoCapacitadas();
					BeanUtils.copyProperties(historicoCapacitadas,detalleTemp);
					List listaCapacitada = procesoEDURegistroAsistenciaDAO.
						getHistoricoCapacitadas(historicoCapacitadas);
					
					log.debug("listaCapacitada "+listaCapacitada.size());
			
					/*Si el registro existe solo se actualiza*/
					if ((listaCapacitada!=null) && (listaCapacitada.size() > 0 ))
					{
						historicoCapacitadas = (HistoricoCapacitadas) listaCapacitada.get(0);
						historicoCapacitadas.setUltimaCampanha(cursoDictado.getCampanhaInicio());
						historicoCapacitadas.setNivelCapacitacion(cursoCapacitacion.getCodigoCurso());
						/*Obtenemos ciertos datos del historico de pedidos*/
						HistoricoPedido historicoPedido = new HistoricoPedido();					
						BeanUtils.copyProperties(historicoPedido,detalleTemp);
						List listaHistoricoPedido = mantenimientoEDUCursoCapacitacionDAO.getHistoricoPedido(historicoPedido); 
						if ((listaHistoricoPedido!=null) && (listaHistoricoPedido.size()>0)){
							historicoPedido = (HistoricoPedido) listaHistoricoPedido.get(0);
							historicoCapacitadas.setCampanhaIngreso(historicoPedido.getCampanhaInicioPedido());
						}
						if (log.isDebugEnabled()) {                        
							log.debug("Entering 'updateHistoricoCapacitadas' method");
							log.debug(historicoCapacitadas);   	
						}                                                  			
						procesoEDURegistroAsistenciaDAO.updateHistoricoCapacitadas(historicoCapacitadas,usuario);
					}else
					{/*Si el registro no existe se crea*/
						HistoricoPedido historicoPedido = new HistoricoPedido();					
						BeanUtils.copyProperties(historicoPedido,detalleTemp);
						List listaHistoricoPedido = mantenimientoEDUCursoCapacitacionDAO.getHistoricoPedido(historicoPedido); 
						if ((listaHistoricoPedido!=null) && (listaHistoricoPedido.size()>0)){
							historicoPedido = (HistoricoPedido) listaHistoricoPedido.get(0);
							historicoCapacitadas.setCampanhaIngreso(historicoPedido.getCampanhaInicioPedido());
						}
						historicoCapacitadas.setUltimaCampanha(cursoDictado.getCampanhaInicio());
						historicoCapacitadas.setNivelCapacitacion(cursoCapacitacion.getCodigoCurso());
						if (log.isDebugEnabled()) {                        
							log.debug("Entering 'insertHistoricoCapacitadas' method");
							log.debug(historicoCapacitadas);   	
						}                                                  			
						procesoEDURegistroAsistenciaDAO.insertHistoricoCapacitadas(historicoCapacitadas,usuario);
					}
					/* Insertando Detalle Historico de Capacitadas */
					/* Beans Comunes */
					HistoricoAptas historicoAptas = new HistoricoAptas();
					historicoAptas.setCodigoPais(cursoDictado.getCodigoPais());
					historicoAptas.setCodigoEmpresa(cursoDictado.getCodigoEmpresa());
					historicoAptas.setCodigoCurso(cursoDictado.getCodigoCurso());
					historicoAptas.setCodigoCliente(detalleTemp.getCodigoCliente());
					List listaHistoricoApta = procesoEDURegistroAsistenciaDAO.getHistoricoAptas(historicoAptas);
					if ((listaHistoricoApta!=null) && (listaHistoricoApta.size() > 0 ))
					{
						historicoAptas = (HistoricoAptas) listaHistoricoApta.get(0);
						historicoAptas.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
						historicoAptas.setEstadoCapacitacion(Constants.ESTADO_CAPACITADA_CAPACITADA);
						historicoAptas.setCampanhaCapacitacion(cursoDictado.getCampanhaInicio());
						procesoEDURegistroAsistenciaDAO.updateHistoricoAptas(historicoAptas,usuario);
					}
					/* Fin Obteniendo dato del Historico de Aptas */
					BeanUtils.copyProperties(historicoCapacitadasDetalle,historicoCapacitadas);				
					historicoCapacitadasDetalle.setCodigoTipoAsistencia(Constants.EDU_PARAMETRO_TIPO_ASIST_CURSO_PRESENCIAL);
					historicoCapacitadasDetalle.setCodigoTipoAsistente(Constants.EDU_PARAMETRO_TIPO_ASITT_CURSO_REGULAR);
					historicoCapacitadasDetalle.setCodigoInstructora(cursoDictado.getCodigoInstructora());
					historicoCapacitadasDetalle.setCodigoCurso(cursoDictado.getCodigoCurso());
					historicoCapacitadasDetalle.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
					historicoCapacitadasDetalle.setIndicadorPagoCurso(cursoCapacitacion.getIndicadorCostoCurso());
					historicoCapacitadasDetalle.setCampanhaPrimeraCalificacionApta(historicoAptas.getCampanhaPrimeraCalificacionApta());
					historicoCapacitadasDetalle.setCampanhaUltimaCalificacionApta(historicoAptas.getCampanhaUltimaCalificacionApta());
					historicoCapacitadasDetalle.setCampanhaRegistroAsistencia(cursoDictado.getCampanhaInicio());
					historicoCapacitadasDetalle.setCampanhaCapacitacion(cursoDictado.getCampanhaInicio());
					historicoCapacitadasDetalle.setCodigoCliente(detalleCurso.getCodigoCliente());
					historicoCapacitadasDetalle.setIndicadorEvaluacionCurso(cursoDictado.getIndicadorEvaluacionCurso());
					historicoCapacitadasDetalle.setindicadorEvaluacionInstructora(cursoCapacitacion.getIndicadorEvaluacionCapacitadora());
					if (Constants.SI.equals(cursoDictado.getIndicadorEvaluacionCapacitada())) 
						historicoCapacitadasDetalle.setCalificacionEvaluacionCurso(detalleTemp.getCalificacionEvaluacionCurso());
					historicoCapacitadasDetalle.setNumeroInvitaciones(historicoAptas.getNumeroInvitacion());
					historicoCapacitadasDetalle.setCodigoPlanilla(detalleTemp.getCodigoPlanilla());
					if (log.isDebugEnabled()) {                        
						log.debug("Entering 'insertHistoricoCapacitadasDetalle' method");
						log.debug(historicoCapacitadas);   	
					}                                                  			
					procesoEDURegistroAsistenciaDAO.insertHistoricoCapacitadasDetalle(historicoCapacitadasDetalle,usuario);
				}/*Fin Indicador Asistencia*/
		   }/*Fin For*/
		/* Eliminamos el Registro del Temporal */
		detalleTemp.setCodigoPlanilla(null);
		procesoEDURegistroAsistenciaDAO.removeCapacitadasTemporal(detalleTemp,usuario);
		/*Actualizamos el Historico de Planilla */
		for (int i=0;i<listaPlanillasInstructora.size();i++){
			historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
			historicoPlanillaInstructora = (HistoricoPlanillaInstructora) listaPlanillasInstructora.get(i);
			procesoEDURegistroAsistenciaDAO.updateHistoricoPlanillaInstructora(historicoPlanillaInstructora,usuario);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#updateRegistroAsistencia(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario, org.apache.struts.action.ActionMessages)
	 */
	public boolean updateRegistroAsistencia(HistoricoCursoDictado cursoDictado, Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		boolean bGrabarCab = false;
			if (log.isDebugEnabled()) {                        
				log.debug("Entering 'updateRegistroAsistencia' method");
				log.debug(cursoDictado);   					
			}                                                  			
			/* Fin Obteniendo dato del Curso */
			
			/* Verificando lista de consultoras */
			HistoricoCursoDictadoDetalle detalleCurso = new HistoricoCursoDictadoDetalle();
			detalleCurso.setCodigoPais(cursoDictado.getCodigoPais());
			detalleCurso.setCodigoEmpresa(cursoDictado.getCodigoEmpresa());
			detalleCurso.setCodigoCurso(cursoDictado.getCodigoCurso());
			detalleCurso.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
			List listadetallesPlanilla = getHistoricoCursoDictadoDetalle(detalleCurso);
			boolean verifica = procesoEDURegistroAsistenciaDAO.verificarCampannaRegistroAsistencia(cursoDictado, listadetallesPlanilla);				
			if (!verifica) {
				String mensajeError =  this.messageSource.getMessage("procesoEDURegistroAsistencia.error.verificarCampanna",
										null, getLocale(usuario));
				throw new Exception(mensajeError);
			}	
			
			/* Actualizando cabecera */
			procesoEDURegistroAsistenciaDAO.updateHistoricoCursoDictado(cursoDictado,usuario);
			bGrabarCab = true;

	    return bGrabarCab;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#updateRegistroAsistencia(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado, biz.belcorp.ssicc.model.Usuario, org.apache.struts.action.ActionMessages)
	 */
	public boolean updateRegistroAsistenciaWs(HistoricoCursoDictado cursoDictado, Usuario usuario) {
		// TODO Auto-generated method stub
		boolean bGrabarCab = false;
		try {
			if (log.isDebugEnabled()) {                        
				log.debug("Entering 'updateRegistroAsistenciaWs' method");
				log.debug(cursoDictado);   					
			}                                                  			
			/* Fin Obteniendo dato del Curso */
			procesoEDURegistroAsistenciaDAO.updateHistoricoCursoDictadoWs(cursoDictado,usuario);
			bGrabarCab = true;
		} catch (Exception e) {
			if (log.isDebugEnabled()) {                        
				e.printStackTrace();
			}                                                  			
			bGrabarCab = false;
		}
	return bGrabarCab;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#inserDetalleTemporal(biz.belcorp.ssicc.edu.form.ProcesoEDURegistroAsistenciaForm, biz.belcorp.ssicc.model.Usuario)
	 */
	public boolean inserDetalleTemporal(HistoricoCursoDictado cursoDictado, List listaUsuarios,String[] listaCapacitados,String[] listaPromedio, Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		DetallePlanilla detalleTemp = new DetallePlanilla();
		
			/*Primero Limpiamos la Temporal*/			
			detalleTemp.setCodigoCurso(cursoDictado.getCodigoCurso());
			detalleTemp.setCodigoEmpresa(cursoDictado.getCodigoEmpresa());
			detalleTemp.setCodigoPais(cursoDictado.getCodigoPais());			
			detalleTemp.setCodigoInstructora(cursoDictado.getCodigoInstructora());
			detalleTemp.setCodigoPlanilla(cursoDictado.getCodigoPlanilla());
			
			/* Verificando que la campaa de Inicio sea mayor a la Campaa de Programacion de cada consultora */
			boolean verifica = procesoEDURegistroAsistenciaDAO.verificarCampannaRegistroAsistenciaInsert(cursoDictado, listaUsuarios);
			if (!verifica) {
				String mensajeError =  this.messageSource.getMessage("procesoEDURegistroAsistencia.error.verificarCampanna",
										null, getLocale(usuario));
				throw new Exception(mensajeError);
			}	
			
			procesoEDURegistroAsistenciaDAO.removeCapacitadasTemporal(detalleTemp,usuario);
			/*Recorremos las asistencias */
			if (Constants.NO.equals(cursoDictado.getSinClientes()) && (listaCapacitados!=null) && (listaCapacitados.length>0)){			
				for(int i=0;i<listaCapacitados.length;i++){
					int idIndex   = Integer.valueOf(listaCapacitados[i]).intValue() -1;
					detalleTemp = (DetallePlanilla)listaUsuarios.get(idIndex);
					detalleTemp.setIndicadorAsistencia(Constants.INDICADOR_ASISTENCIA_SI);
					if ((listaPromedio!=null) && (listaPromedio.length)>=(idIndex)){
						detalleTemp.setCalificacionEvaluacionCurso(Double.valueOf(listaPromedio[idIndex]));	
					}else{
						detalleTemp.setCalificacionEvaluacionCurso(new Double(0.00));
					}
					listaUsuarios.set(idIndex,detalleTemp);
				}
			}
			
			/*Luego Insertamos la Lista Actual*/
			for(int i=0;i<listaUsuarios.size();i++){
				detalleTemp = new DetallePlanilla();
				detalleTemp = (DetallePlanilla)listaUsuarios.get(i);
				/*Obtenemos la data seleccionada*/
				detalleTemp.setCodigoPais(cursoDictado.getCodigoPais());
				detalleTemp.setCodigoEmpresa(cursoDictado.getCodigoEmpresa());
				detalleTemp.setCodigoCurso(cursoDictado.getCodigoCurso());
				detalleTemp.setCodigoCursoDictado("1");
				detalleTemp.setCodigoInstructora(cursoDictado.getCodigoInstructora());
				detalleTemp.setCampanhaInicio(cursoDictado.getCampanhaInicio());
				detalleTemp.setFechaInicio(cursoDictado.getFechaInicio());
				detalleTemp.setNumeroSesiones(cursoDictado.getNumeroSesiones());
				detalleTemp.setCodigoRegion(cursoDictado.getCodigoRegion());
				detalleTemp.setCodigoZona(cursoDictado.getCodigoZona());
				detalleTemp.setLugarCapacitacion(cursoDictado.getLugarCapacitacion());
				detalleTemp.setCategoriaLugarCapacitacion(cursoDictado.getCategoriaLugar());
				detalleTemp.setEstadoRegistro(Constants.ESTADO_ACTIVO);
				detalleTemp.setEstadoCursoDictado(Constants.ESTADO_CURSO_VIGENTE);
				detalleTemp.setIndicadorEvaluacionCurso(cursoDictado.getIndicadorEvaluacionCapacitada());
				if (Constants.SI.equals(cursoDictado.getSinClientes())){
					detalleTemp.setIndicadorAsistencia(Constants.INDICADOR_ASISTENCIA_NO);
					detalleTemp.setCalificacionEvaluacionCurso(new Double(0.00));
				}
				procesoEDURegistroAsistenciaDAO.insertCapacitadasTemporal(detalleTemp,usuario);	
			}
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#inserDetalleTemporal(biz.belcorp.ssicc.edu.form.ProcesoEDURegistroAsistenciaForm, biz.belcorp.ssicc.model.Usuario)
	 */
	public boolean updatePlanillaRegistroAsistencia(HistoricoCursoDictado cursoDictado, List listaUsuarios,String[] listaCapacitados,String[] listaPromedio, Usuario usuario) 
					throws Exception {
		// TODO Auto-generated method stub
		/*Beans Comunes*/
		HistoricoPlanillaInstructora historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
		List listaPlanillasInstructora = new LinkedList();
		HistoricoCursoDictadoDetalle detalleCurso = new HistoricoCursoDictadoDetalle();
		//DetallePlanilla detalleTemp = new DetallePlanilla();
		HistoricoCapacitadas historicoCapacitadas = new HistoricoCapacitadas();
		HistoricoCapacitadasDetalle historicoCapacitadasDetalle = new HistoricoCapacitadasDetalle();		
		CursoCapacitacion cursoCapacitacion = new CursoCapacitacion();
		
		/* Verificando que la campaa de Inicio sea mayor a la Campaa de Programacion de cada consultora */
		boolean verifica = procesoEDURegistroAsistenciaDAO.verificarCampannaRegistroAsistencia(cursoDictado, listaUsuarios);
		if (!verifica) {
			String mensajeError =  this.messageSource.getMessage("procesoEDURegistroAsistencia.error.verificarCampanna",
									null, getLocale(usuario));
			throw new Exception(mensajeError);
		}	
		
		/*Beans Comunes*/
		Map criterios = new HashMap();				
		criterios.put("codigoPais", cursoDictado.getCodigoPais());
		criterios.put("codigoEmpresa", cursoDictado.getCodigoEmpresa());
		criterios.put("codigoCurso", cursoDictado.getCodigoCurso());
		List listaCurso = mantenimientoEDUCursoCapacitacionDAO.getCursosCapacitacionByCriteria(criterios);
		if ((listaCurso!=null) && (listaCurso.size()>0)) {
			cursoCapacitacion = (CursoCapacitacion) listaCurso.get(0);
			cursoDictado.setIndicadorEvaluacionCapacitada(cursoCapacitacion.getIndicadorEvaluacionCapacitada());
			cursoDictado.setIndicadorEvaluacionCurso(cursoCapacitacion.getIndicadorEvaluacionCapacitadora());
		}
		/* Luego Insertamos la Lista Actual */
		/*Recorremos las asistencias */
		if (Constants.NO.equals(cursoDictado.getSinClientes()) && (listaCapacitados!=null) && (listaCapacitados.length>0)){			
			for(int i=0;i<listaCapacitados.length;i++){
				detalleCurso = new HistoricoCursoDictadoDetalle();
				int idIndex   = Integer.valueOf(listaCapacitados[i]).intValue() -1;
				detalleCurso = (HistoricoCursoDictadoDetalle)listaUsuarios.get(idIndex);
				detalleCurso.setIndicadorAsistencia(Constants.INDICADOR_ASISTENCIA_SI);
				if ((listaPromedio!=null) && (listaPromedio.length)>=(idIndex)){
					detalleCurso.setCalificacionConsultora(Double.valueOf(listaPromedio[idIndex]));	
				}else{
					detalleCurso.setCalificacionConsultora(new Double(0.00));
				}
				listaUsuarios.set(idIndex,detalleCurso);
			}
		}
		/*Antes de Entrar a los Detalles del Curso Eliminamos todos los Detalles de Capacitados */
		historicoCapacitadasDetalle.setCodigoPais(cursoDictado.getCodigoPais());
		historicoCapacitadasDetalle.setCodigoEmpresa(cursoDictado.getCodigoEmpresa());
		historicoCapacitadasDetalle.setCodigoCurso(cursoDictado.getCodigoCurso());
		historicoCapacitadasDetalle.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
		historicoCapacitadasDetalle.setCodigoPlanilla(cursoDictado.getCodigoPlanilla());
		log.debug("elimnando del histroico decapacitadas ");
		procesoEDURegistroAsistenciaDAO.removeFisicoHistoricoCapacitadasDetalle(historicoCapacitadasDetalle,usuario);
		/*Fin de Eliminacion de los Detalles Capacitados */
		for(int i=0;i<listaUsuarios.size();i++){
			detalleCurso = new HistoricoCursoDictadoDetalle();
			detalleCurso = (HistoricoCursoDictadoDetalle)listaUsuarios.get(i);
			/*Obtenemos la data seleccionada para comparar*/
				
				if (Constants.NO.equals(detalleCurso.getIndicadorAsistencia())) 
					detalleCurso.setCalificacionConsultora(new Double("0.00"));
			
			/*Actualizamos el detalle del dictado, Insertamos si no Existe*/
			detalleCurso.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());			
			String clienteRegistrado = procesoEDURegistroAsistenciaDAO.
				existClienteCursoDictadoDetalle(detalleCurso);
			log.debug("clienteRegistrado "+clienteRegistrado);
			if (!StringUtils.isBlank(clienteRegistrado)){
				log.debug("actualizando el historico curso dicatdo");
				procesoEDURegistroAsistenciaDAO.updateHistoricoCursoDictadoDetalle(detalleCurso,usuario);
				}
			else{
				log.debug("si es uno nuevo q se asigna asistencia de una nueva palnilla");
				detalleCurso.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
				procesoEDURegistroAsistenciaDAO.insertHistoricoCursoDictadoDetalle(detalleCurso,usuario);
				/*Historico Planilla por Instructora*/
				historicoPlanillaInstructora = new HistoricoPlanillaInstructora();				
				BeanUtils.copyProperties(historicoPlanillaInstructora,detalleCurso);
				historicoPlanillaInstructora.setCodigoInstructora(cursoDictado.getCodigoInstructora());
				historicoPlanillaInstructora.setCodigoPlanilla(detalleCurso.getCodigoPlanillaProgramacion());
				log.debug("a esta nueva planilla se le pone en estado procesada");
				synPlanillaInstructora(listaPlanillasInstructora,historicoPlanillaInstructora);
				/*Fin Historico Planillas por Instructora*/
			}
			if (Constants.SI.equals(detalleCurso.getIndicadorAsistencia())){			
				/* Historico de Capacitadas Cabecera (Cuidado es por Cliente)*/
				historicoCapacitadas = new HistoricoCapacitadas();
				BeanUtils.copyProperties(historicoCapacitadas,detalleCurso);
				List listaCapacitada = procesoEDURegistroAsistenciaDAO.
					getHistoricoCapacitadas(historicoCapacitadas);
				log.debug("lista de asistentes actual q ta en base de datos" + listaCapacitada.size());
				/*Si el registro existe solo se actualiza*/
				if ((listaCapacitada!=null) && (listaCapacitada.size() > 0 )){
					historicoCapacitadas = (HistoricoCapacitadas) listaCapacitada.get(0);
					historicoCapacitadas.setUltimaCampanha(cursoDictado.getCampanhaInicio());
					historicoCapacitadas.setNivelCapacitacion(cursoDictado.getCodigoCurso());
					/*Obtenemos ciertos datos del historico de pedidos*/
					HistoricoPedido historicoPedido = new HistoricoPedido();					
					BeanUtils.copyProperties(historicoPedido,detalleCurso);
					List listaHistoricoPedido = mantenimientoEDUCursoCapacitacionDAO.
						getHistoricoPedido(historicoPedido); 
					if ((listaHistoricoPedido!=null) && (listaHistoricoPedido.size()>0)){
						historicoPedido = (HistoricoPedido) listaHistoricoPedido.get(0);
						historicoCapacitadas.setCampanhaIngreso(historicoPedido.getCampanhaInicioPedido());
					}
					log.debug("update Historico Cpacitadas cabecrea");
					procesoEDURegistroAsistenciaDAO.updateHistoricoCapacitadas(historicoCapacitadas,usuario);
				}else{
					log.debug("insert Historioc de capacita asi como el dettale");
					HistoricoPedido historicoPedido = new HistoricoPedido();					
					BeanUtils.copyProperties(historicoPedido,detalleCurso);
					List listaHistoricoPedido = mantenimientoEDUCursoCapacitacionDAO.
						getHistoricoPedido(historicoPedido); 
					if ((listaHistoricoPedido!=null) && (listaHistoricoPedido.size()>0)){
						historicoPedido = (HistoricoPedido) listaHistoricoPedido.get(0);
						historicoCapacitadas.setCampanhaIngreso(historicoPedido.getCampanhaInicioPedido());
					}
					historicoCapacitadas.setUltimaCampanha(cursoDictado.getCampanhaInicio());
					historicoCapacitadas.setNivelCapacitacion(cursoDictado.getCodigoCurso());
					procesoEDURegistroAsistenciaDAO.insertHistoricoCapacitadas(historicoCapacitadas,usuario);
				}
				/* Insertando Detalle Historico de Capacitadas - ELIMINADOS ANTERIORMENTE..*/
				/* Beans Comunes */
				HistoricoAptas historicoAptas = new HistoricoAptas();
				historicoAptas.setCodigoPais(cursoDictado.getCodigoPais());
				historicoAptas.setCodigoEmpresa(cursoDictado.getCodigoEmpresa());
				historicoAptas.setCodigoCurso(cursoDictado.getCodigoCurso());
				historicoAptas.setCodigoCliente(detalleCurso.getCodigoCliente());
				/*en el historico de aptas se debe actualizar el estado*/
				List listaHistoricoApta = procesoEDURegistroAsistenciaDAO.getHistoricoAptas(historicoAptas);
				if ((listaHistoricoApta!=null) && (listaHistoricoApta.size() > 0 ))
				{
					historicoAptas = (HistoricoAptas) listaHistoricoApta.get(0);
					historicoAptas.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
					historicoAptas.setEstadoCapacitacion(Constants.ESTADO_CAPACITADA_CAPACITADA);
					historicoAptas.setCampanhaCapacitacion(cursoDictado.getCampanhaInicio());
					procesoEDURegistroAsistenciaDAO.updateHistoricoAptas(historicoAptas,usuario);
				}
				/* Fin Obteniendo dato del Historico de Aptas */
				BeanUtils.copyProperties(historicoCapacitadasDetalle,historicoCapacitadas);				
				historicoCapacitadasDetalle.setCodigoTipoAsistencia(Constants.EDU_PARAMETRO_TIPO_ASIST_CURSO_PRESENCIAL);
				historicoCapacitadasDetalle.setCodigoTipoAsistente(Constants.EDU_PARAMETRO_TIPO_ASITT_CURSO_REGULAR);
				historicoCapacitadasDetalle.setCodigoInstructora(cursoDictado.getCodigoInstructora());
				historicoCapacitadasDetalle.setCodigoCurso(cursoDictado.getCodigoCurso());
				historicoCapacitadasDetalle.setCodigoPlanilla(detalleCurso.getCodigoPlanillaProgramacion());
				historicoCapacitadasDetalle.setCodigoCursoDictado(cursoDictado.getCodigoCursoDictado());
				historicoCapacitadasDetalle.setIndicadorPagoCurso(cursoCapacitacion.getIndicadorCostoCurso());
				historicoCapacitadasDetalle.setCampanhaPrimeraCalificacionApta(historicoAptas.
							getCampanhaPrimeraCalificacionApta());
				historicoCapacitadasDetalle.setCampanhaUltimaCalificacionApta(historicoAptas.
							getCampanhaUltimaCalificacionApta());
				historicoCapacitadasDetalle.setCampanhaRegistroAsistencia(cursoDictado.getCampanhaInicio());
				historicoCapacitadasDetalle.setCampanhaCapacitacion(cursoDictado.getCampanhaInicio());
				historicoCapacitadasDetalle.setCodigoCliente(detalleCurso.getCodigoCliente());
				historicoCapacitadasDetalle.setIndicadorEvaluacionCurso(cursoDictado.
							getIndicadorEvaluacionCapacitada());
				historicoCapacitadasDetalle.setindicadorEvaluacionInstructora(cursoCapacitacion.
							getIndicadorEvaluacionCapacitadora());
				if (Constants.SI.equals(cursoDictado.getIndicadorEvaluacionCapacitada())) 
						historicoCapacitadasDetalle.setCalificacionEvaluacionCurso(detalleCurso.
						getCalificacionConsultora());
				historicoCapacitadasDetalle.setNumeroInvitaciones(historicoAptas.getNumeroInvitacion());
				procesoEDURegistroAsistenciaDAO.insertHistoricoCapacitadasDetalle(historicoCapacitadasDetalle,usuario);

			}/*Indicador Asistencia*/
			else{
				/*Si No existe Detalles , se deber eliminar el detalle y la cabecera de  capacitacion-cabecera*/
				log.debug("ahora eres No asistente , pero antes PUDISTE SEr SI " + detalleCurso.getCodigoCliente());
				historicoCapacitadasDetalle.setCodigoCliente(detalleCurso.getCodigoCliente());	
				String lastCurso = procesoEDURegistroAsistenciaDAO.
					getMaxCursoCapacitacionDetalle(historicoCapacitadasDetalle);
				log.debug("lastCurso "+lastCurso);
				if (!StringUtils.isBlank(lastCurso)){
					historicoCapacitadas.setCodigoCliente(detalleCurso.getCodigoCliente());
					List listaCapacitada = procesoEDURegistroAsistenciaDAO.
							getHistoricoCapacitadas(historicoCapacitadas);
					/*Si el registro existe solo se actualiza*/
					if ((listaCapacitada!=null) && (listaCapacitada.size() > 0 )){
						historicoCapacitadas = (HistoricoCapacitadas) listaCapacitada.get(0);
						//historico aptas0
						HistoricoCapacitadasDetalle historicoCapacitadasDetalleAux = new HistoricoCapacitadasDetalle();
						historicoCapacitadasDetalleAux.setCodigoPais(detalleCurso.getCodigoPais());
						historicoCapacitadasDetalleAux.setCodigoEmpresa(detalleCurso.getCodigoEmpresa());
						historicoCapacitadasDetalleAux.setCodigoCliente(detalleCurso.getCodigoCliente());
						historicoCapacitadasDetalleAux.setCodigoCurso(lastCurso);
						String nivelCapacitacionCurso=getNivelCapacitacionCurso(historicoCapacitadasDetalleAux);
						historicoCapacitadas.setUltimaCampanha(nivelCapacitacionCurso);
						historicoCapacitadas.setNivelCapacitacion(cursoDictado.getCodigoCurso());
						/*Obtenemos ciertos datos del historico de pedidos*/
						HistoricoPedido historicoPedido = new HistoricoPedido();					
						BeanUtils.copyProperties(historicoPedido,detalleCurso);
						List listaHistoricoPedido = mantenimientoEDUCursoCapacitacionDAO.
							getHistoricoPedido(historicoPedido); 
						if ((listaHistoricoPedido!=null) && (listaHistoricoPedido.size()>0)){
							historicoPedido = (HistoricoPedido) listaHistoricoPedido.get(0);
							historicoCapacitadas.setCampanhaIngreso(historicoPedido.getCampanhaInicioPedido());
							historicoCapacitadas.setNivelCapacitacion(lastCurso);
						}
						procesoEDURegistroAsistenciaDAO.updateHistoricoCapacitadas(historicoCapacitadas,usuario);
					}
				}
				else
				{
					/*Eliminamos la Cabecera - No queda Huella */
					log.debug("eliminamos cabecera de historico caapcitadas cabecera");
					historicoCapacitadas.setCodigoCliente(historicoCapacitadasDetalle.getCodigoCliente());
					historicoCapacitadas.setCodigoEmpresa(historicoCapacitadasDetalle.getCodigoEmpresa());
					historicoCapacitadas.setCodigoPais(historicoCapacitadasDetalle.getCodigoPais());
					procesoEDURegistroAsistenciaDAO.removeFisicoHistoricoCapacitadas(historicoCapacitadas,usuario);
										
				}	
				/*Luego restauramos el valor Pendiente en Aptas */
				/* Beans Comunes */
				HistoricoAptas historicoAptas = new HistoricoAptas();
				historicoAptas.setCodigoPais(cursoDictado.getCodigoPais());
				historicoAptas.setCodigoEmpresa(cursoDictado.getCodigoEmpresa());
				historicoAptas.setCodigoCurso(cursoDictado.getCodigoCurso());
				historicoAptas.setCodigoCliente(detalleCurso.getCodigoCliente());
				/*en el historico de aptas se debe actualizar el estado*/
				List listaHistoricoApta = procesoEDURegistroAsistenciaDAO.getHistoricoAptas(historicoAptas);
				if ((listaHistoricoApta!=null) && (listaHistoricoApta.size() > 0 ))
				{
					historicoAptas = (HistoricoAptas) listaHistoricoApta.get(0);
					historicoAptas.setCodigoCursoDictado(null);
					historicoAptas.setEstadoCapacitacion(Constants.ESTADO_CAPACITADA_PROGRAMADA);
					historicoAptas.setCampanhaCapacitacion(null);
					procesoEDURegistroAsistenciaDAO.updateHistoricoAptas(historicoAptas,usuario);
				}
			}
			
		}/*Fin del For*/
		/*Actualizamos el Historico de Planilla */
		for (int i=0;i<listaPlanillasInstructora.size();i++){
			historicoPlanillaInstructora = new HistoricoPlanillaInstructora();
			historicoPlanillaInstructora = (HistoricoPlanillaInstructora) listaPlanillasInstructora.get(i);
			procesoEDURegistroAsistenciaDAO.updateHistoricoPlanillaInstructora(historicoPlanillaInstructora,usuario);
		}
		return true;
	}

	/**
	 * Retorna la capacitacion del curso que se esta modificando para ser
	 * actualizso en el historico cabecera
	 * @param histoAptas
	 * @return
	 */
	private String getNivelCapacitacionCurso(
			HistoricoCapacitadasDetalle historicoCapacitadasDetalle) {
		List list = procesoEDURegistroAsistenciaDAO.getHistoricoCapacitadasDetalle(
				historicoCapacitadasDetalle);
		if(list.size()>0){
			HistoricoCapacitadasDetalle ha =(HistoricoCapacitadasDetalle)list.get(0);
			log.debug("campanha capac "+ha.getCampanhaCapacitacion());
			return ha.getCampanhaCapacitacion();
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getHistoricoAptasPlanilla(biz.belcorp.ssicc.edu.dao.model.DetallePlanilla)
	 */
	public List getHistoricoAptasPlanilla(DetallePlanilla detallePlanilla) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoAptasPlanilla(detallePlanilla);
	}

	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getPlanillaCursoDictadoDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictadoDetalle)
	 */
	public List getPlanillaCursoDictadoDetalle(HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getPlanillaCursoDictadoDetalle(historicoCursoDictadoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getHistoricoCursoDictadoCapacitadas(java.util.Map)
	 */
	public List getHistoricoCursoDictadoCapacitadas(Map criteria) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoCursoDictadoCapacitadas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getHistoricoPlanillaCapacitadas(java.util.Map)
	 */
	public List getHistoricoPlanillaCapacitadas(Map criteria) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoPlanillaCapacitadas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getHistoricoPlanillaProgramadas(java.util.Map)
	 */
	public List getHistoricoPlanillaProgramadas(Map criteria) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoPlanillaProgramadas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoPlanillaInstructora(biz.belcorp.ssicc.edu.dao.model.HistoricoPlanillaInstructora)
	 */
	public List getHistoricoPlanillaInstructora(HistoricoPlanillaInstructora historicoPlanillaInstructora) {
		// TODO Auto-generated method stub
		return procesoEDURegistroAsistenciaDAO.getHistoricoPlanillaInstructora(historicoPlanillaInstructora);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#updateHistoricoPlanillaInstructora(biz.belcorp.ssicc.edu.dao.model.HistoricoPlanillaInstructora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateHistoricoPlanillaInstructora(HistoricoPlanillaInstructora historicoPlanillaInstructora, Usuario usuario) {
		// TODO Auto-generated method stub
		procesoEDURegistroAsistenciaDAO.updateHistoricoPlanillaInstructora(historicoPlanillaInstructora,usuario);		
	}
	
	/**
	 * @param lista
	 * @param planilla
	 */
	private void synPlanillaInstructora(List lista,HistoricoPlanillaInstructora planilla){
		boolean bfound=false;	
			if ((lista!=null) && (lista.size()>0)){
				HistoricoPlanillaInstructora detalle = new HistoricoPlanillaInstructora();
				for(int i=0;i<lista.size();i++){
					detalle = new HistoricoPlanillaInstructora();
					detalle = (HistoricoPlanillaInstructora)lista.get(i);
					if (detalle.getCodigoPais().equals(planilla.getCodigoPais()) &&
						detalle.getCodigoEmpresa().equals(planilla.getCodigoEmpresa()) && 
						detalle.getCodigoCurso().equals(planilla.getCodigoCurso()) &&
						detalle.getCodigoPlanilla().equals(planilla.getCodigoPlanilla()) &&
						detalle.getCodigoInstructora().equals(planilla.getCodigoInstructora()))	{
							bfound=true;
							break;
					}
				}
			}
			if (!bfound){
				planilla.setSituacionPlanilla(Constants.SITUACION_PLANILLA_PROCESADA);
				lista.add(planilla);
				}
			}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getSituacionActualHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas)
	 */
	public List getSituacionActualHistoricoAptas(HistoricoAptas historicoAptas) {
		//List resultado=new ArrayList();
		//SE MANDA TODAS LAS DISTINTAS DE CP , para Lbel , se enviara un curso , para Esika 
		// permite varos cursos en paralelo
		List list=procesoEDURegistroAsistenciaDAO.getSituacionActualHistoricoAptas(historicoAptas);
		//if(list.size()>0)//se obtiene el ultimo registro de aptas
		// resultado.add(list.get(0));		
		return list;
	}
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getSituacionActualExoneradoHistoricoAptas(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas)
	 */
	public List getSituacionActualExoneradoHistoricoAptas(HistoricoAptas historicoAptas) {
		List list=procesoEDURegistroAsistenciaDAO.getSituacionActualExoneradoHistoricoAptas(historicoAptas);
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroAsistenciaService#getCursosExoneracionMasiva(biz.belcorp.ssicc.edu.dao.model.HistoricoAptas)
	 */
	public List getCursosExoneracionMasiva(HistoricoAptas historicoAptas) {
		List list=procesoEDURegistroAsistenciaDAO.getCursosExoneracionMasiva(historicoAptas);
		return list;
	}

			
}