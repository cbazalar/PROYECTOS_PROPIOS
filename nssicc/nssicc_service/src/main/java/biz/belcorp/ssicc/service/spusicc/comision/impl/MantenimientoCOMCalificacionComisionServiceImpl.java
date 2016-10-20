/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMCalificacionComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DatosComision;
import biz.belcorp.ssicc.dao.spusicc.comision.model.OrdenEstadisticoPorZona;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMCalificacionComisionService;

/**
 * @author peextllizana
 * 
 */
@Service("spusicc.mantenimientoCOMCalificacionComisionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMCalificacionComisionServiceImpl extends
		BaseService implements MantenimientoCOMCalificacionComisionService {

	@Resource(name="spusicc.mantenimientoCOMCalificacionComisionDAO")
	MantenimientoCOMCalificacionComisionDAO mantenimientoCOMCalificacionComisionDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.
	 * MantenimientoCOMCalificacionComisionService
	 * #getCalificacionesComisionesList
	 * (biz.belcorp.ssicc.spusicc.comision.web.form
	 * .MantenimientoCOMCalificacionComisionSearchForm)
	 */
	public List getCalificacionesComisionesList(CalificacionComisionCabecera calificacionComisionCabecera){
		return mantenimientoCOMCalificacionComisionDAO.getCalificacionesComisionesList(calificacionComisionCabecera);
	}


	public void deleteCalificacionComisionCabeceraAndChild(
			CalificacionComisionCabecera calificacionComisionCabecera) {

			mantenimientoCOMCalificacionComisionDAO.deleteCalificacionComisionDetalle(calificacionComisionCabecera);
			mantenimientoCOMCalificacionComisionDAO.deleteCalificacionComisionCabecera(calificacionComisionCabecera);
		
	}


	public List getNivelList(String codigo) {
		return mantenimientoCOMCalificacionComisionDAO.getNivelList(codigo);

	}


	public void deleteCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMCalificacionComisionDAO.deleteCalificacionComisionDetalle(calificacionComisionDetalle);

	}


	public void updateCalificacionComisionCabeceraAndDetalle(CalificacionComisionCabecera updateCalificacionComisionCabecera,	List detalList) {
		mantenimientoCOMCalificacionComisionDAO.updateCalificacionComisionCabeceraAndDetalle(updateCalificacionComisionCabecera);

	}


	public void insertCalificacionComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,	List detalList) {
		
		String codigoCalificacion = mantenimientoCOMCalificacionComisionDAO.getSecuenciaNextValue();
		calificacionComisionCabecera.setCodigoCalificacion(codigoCalificacion);
		mantenimientoCOMCalificacionComisionDAO.insertCalificacionComisionCabecera(calificacionComisionCabecera);
		if (detalList != null)
			for (Iterator iterator = detalList.iterator(); iterator.hasNext();) {
				CalificacionComisionDetalle calificacionComisionDetalle = (CalificacionComisionDetalle) iterator.next();
				calificacionComisionDetalle.setCodigoCalificacion(codigoCalificacion);			
				mantenimientoCOMCalificacionComisionDAO.insertCalificacionComisionDetalle(calificacionComisionDetalle);

			}

	}


	public List getCalificacionComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera) {
		return mantenimientoCOMCalificacionComisionDAO.getCalificacionComisionDetalleList(calificacionComisionCabecera);
	}


	public void updateCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMCalificacionComisionDAO.updateCalificacionComisionDetalle(calificacionComisionDetalle);

	}

	public void insertCalificacionComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMCalificacionComisionDAO.insertCalificacionComisionDetalle(calificacionComisionDetalle);

	}


	public DatosComision getDatosComision(DatosComision datos) {
		return mantenimientoCOMCalificacionComisionDAO.getDatosComision(datos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMCalificacionComisionService#getEnviosSAP(java.util.Map)
	 */
	public List getEnviosSAP(Map criteria) {
		return mantenimientoCOMCalificacionComisionDAO.getEnviosSAP(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMCalificacionComisionService#desmarcarEnvio(java.util.Map)
	 */
	public void desmarcarEnvio(Map criteria) {
		mantenimientoCOMCalificacionComisionDAO.desmarcarEnvio(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMCalificacionComisionService#getOrdenEstadisticoPorZonaList(biz.belcorp.ssicc.spusicc.comision.dao.model.OrdenEstadisticoPorZona)
	 */
	public List getOrdenEstadisticoPorZonaList(OrdenEstadisticoPorZona ordenEstadisticoPorZona){
		return mantenimientoCOMCalificacionComisionDAO.getOrdenEstadisticoPorZonaList(ordenEstadisticoPorZona);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMCalificacionComisionService#updateOrdenEstadisticoPorZona(biz.belcorp.ssicc.spusicc.comision.dao.model.OrdenEstadisticoPorZona)
	 */
	public void updateOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona,Usuario usuario){
		mantenimientoCOMCalificacionComisionDAO.updateOrdenEstadisticoPorZona(ordenEstadisticoPorZona,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMCalificacionComisionService#insertOrdenEstadisticoPorZona(biz.belcorp.ssicc.spusicc.comision.dao.model.OrdenEstadisticoPorZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona,Usuario usuario){
		mantenimientoCOMCalificacionComisionDAO.insertOrdenEstadisticoPorZona(ordenEstadisticoPorZona,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMCalificacionComisionService#deleteOrdenEstadisticoPorZona(biz.belcorp.ssicc.spusicc.comision.dao.model.OrdenEstadisticoPorZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteOrdenEstadisticoPorZona(OrdenEstadisticoPorZona ordenEstadisticoPorZona, Usuario usuario) {
		mantenimientoCOMCalificacionComisionDAO.deleteOrdenEstadisticoPorZona(ordenEstadisticoPorZona,usuario);		
	}
}
