package biz.belcorp.ssicc.service.edu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.MantenimientoEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDURegistroCalificacionEjecutivaDAO;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCapacitadasDetalle;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictado;
import biz.belcorp.ssicc.dao.edu.model.HistoricoCursoDictadoDetalle;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.edu.ProcesoEDURegistroCalificacionEjecutivaService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * @author peextrvela
 *
 */
@Service("edu.procesoEDURegistroCalificacionEjecutivaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoEDURegistroCalificacionEjecutivaServiceImpl extends 
	BaseService implements ProcesoEDURegistroCalificacionEjecutivaService{
	
	@Resource(name="edu.procesoEDURegistroCalificacionEjecutivaDAO")
	ProcesoEDURegistroCalificacionEjecutivaDAO procesoEDURegistroCalificacionEjecutivaDAO;
	
	@Resource(name="edu.mantenimientoEDUCursoCapacitacionDAO")
	MantenimientoEDUCursoCapacitacionDAO mantenimientoEDUCursoCapacitacionDAO;
										  
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoCapacitadasDetalle(biz.belcorp.ssicc.edu.dao.model.HistoricoCapacitadasDetalle)
	 */
	public List getHistoricoCapacitadasDetalle(HistoricoCapacitadasDetalle historicoCapacitadasDetalle) {
		// TODO Auto-generated method stub
		return procesoEDURegistroCalificacionEjecutivaDAO.getHistoricoCapacitadasDetalle(historicoCapacitadasDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.MantenimientoEDUCursoCapacitacionService#getHistoricoCursoDictado(biz.belcorp.ssicc.edu.dao.model.HistoricoCursoDictado)
	 */
	public List getHistoricoCursoDictado(HistoricoCursoDictado historicoCursoDictado) {
		// TODO Auto-generated method stub
		return procesoEDURegistroCalificacionEjecutivaDAO.getHistoricoCursoDictado(historicoCursoDictado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.ProcesoEDURegistroCalificacionEjecutivaService#inserDetalleTemporal(biz.belcorp.ssicc.edu.form.ProcesoEDURegistroCalificacionEjecutivaForm, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRegistroCalificacionEjecutiva(HistoricoCursoDictado cursoDictado, 
			List listaUsuarios,String[] listaPromedio, Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		Double totalCalificacion = new Double(0.00);
		int totalCantidad = 0;
		if ((listaUsuarios!=null) && (listaUsuarios.size()>0)){
			HistoricoCapacitadasDetalle historicoCapacitadasDetalle = new HistoricoCapacitadasDetalle();
			HistoricoCursoDictadoDetalle historicoCursoDictadoDetalle = new HistoricoCursoDictadoDetalle();
			
			for (int i=0;i<listaUsuarios.size();i++){
				historicoCapacitadasDetalle = new HistoricoCapacitadasDetalle();
				historicoCapacitadasDetalle = (HistoricoCapacitadasDetalle) listaUsuarios.get(i);
				if ((listaPromedio!=null) && (listaPromedio.length>0)){
					if ((listaPromedio[i]!=null) && (!listaPromedio[i].equals(""))){
						historicoCapacitadasDetalle.setCalificacionEvaluacionInstructora(
								Double.valueOf(listaPromedio[i]));
						totalCalificacion = new Double(totalCalificacion.doubleValue() + 
								historicoCapacitadasDetalle.getCalificacionEvaluacionInstructora().doubleValue());
						if (historicoCapacitadasDetalle.
								getCalificacionEvaluacionInstructora().doubleValue() > new Double("0.00").doubleValue())
								totalCantidad++;
					}else{
						historicoCapacitadasDetalle.setCalificacionEvaluacionInstructora(new Double(0.00));
					}
				}else{
					historicoCapacitadasDetalle.setCalificacionEvaluacionInstructora(new Double(0.00));
				}
				/*Actualizamos el detalle capacitado*/
				procesoEDURegistroCalificacionEjecutivaDAO.updateEvaluacionInstructoraCapacitadas(
						historicoCapacitadasDetalle,usuario);
				/*Actualizamos el detalle del dictado*/
				BeanUtils.copyProperties(historicoCursoDictadoDetalle,historicoCapacitadasDetalle);	
				historicoCursoDictadoDetalle.setCalificacionInstructora(
						historicoCapacitadasDetalle.getCalificacionEvaluacionInstructora());
				procesoEDURegistroCalificacionEjecutivaDAO.updateDetalleDictadoCalificacionEjecutiva(
						historicoCursoDictadoDetalle,usuario);
			}
			/*Actualizamos la Cabecera del Dictado*/
			HistoricoCursoDictado historicoCursoDictado = new HistoricoCursoDictado();
			BeanUtils.copyProperties(historicoCursoDictado,cursoDictado);
			if (totalCalificacion.doubleValue()> new Double("0.00").doubleValue())
				historicoCursoDictado.setCalificacionPromedio(
						new Double(totalCalificacion.doubleValue() / totalCantidad));
			else
				historicoCursoDictado.setCalificacionPromedio(new Double("0.00"));
			procesoEDURegistroCalificacionEjecutivaDAO.
				updateDictadoCalificacionEjecutiva(historicoCursoDictado,usuario);
			/*Devolvemos el valor obtenido*/
			cursoDictado.setCalificacionPromedio(historicoCursoDictado.getCalificacionPromedio());
		}
	}


}
