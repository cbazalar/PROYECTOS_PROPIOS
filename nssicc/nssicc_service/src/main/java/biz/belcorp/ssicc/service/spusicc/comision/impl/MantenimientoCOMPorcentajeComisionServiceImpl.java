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

import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMPorcentajeComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMPorcentajeComisionService;

/**
 * @author <a href="mailto:avillavicencio@csigcomt.com">Alexander Villavicencio</a>
 *
 */
@Service("spusicc.mantenimientoCOMPorcentajeComisionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMPorcentajeComisionServiceImpl extends BaseService implements 
		MantenimientoCOMPorcentajeComisionService {
	
	@Resource(name="spusicc.mantenimientoCOMPorcentajeComisionDAO")
	MantenimientoCOMPorcentajeComisionDAO mantenimientoCOMPorcentajeComisionDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.MantenimientoCOMPorcentajeComisionService#getPorcentajesComisionesList(biz.belcorp.ssicc.spusicc.comision.web.form.MantenimientoCOMPorcentajeComisionSearchForm)
	 * retorna la lista de porcentaje de comisiones
	 */
	public List getPorcentajesComisionesList(CalificacionComisionCabecera calificacionComisionCabecera){
		return mantenimientoCOMPorcentajeComisionDAO.getPorcentajesComisionesList(calificacionComisionCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#getNivelList(java.lang.String)
	 * retorna la lista de niveles
	 */
	public List getNivelList(String codigo) {
		return mantenimientoCOMPorcentajeComisionDAO.getNivelList(codigo);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#deletePorcentajeComisionCabeceraAndChild(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * Borra la cabecera del porcentaje de la comisin y todo lo que pertenece a dicha comisin
	 */
	public void deletePorcentajeComisionCabeceraAndChild(
			CalificacionComisionCabecera calificacionComisionCabecera) {
		
			mantenimientoCOMPorcentajeComisionDAO.deletePorcentajeComisionDetalle(calificacionComisionCabecera);
			mantenimientoCOMPorcentajeComisionDAO.deletePorcentajeComisionCabecera(calificacionComisionCabecera);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#getPorcentajeComisionDetalleList(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * retorna la lista de detalle del porcentaje de comisin
	 */
	public List getPorcentajeComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera) {
		return mantenimientoCOMPorcentajeComisionDAO.getPorcentajeComisionDetalleList(calificacionComisionCabecera);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#updatePorcentajeComisionCabeceraAndDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera, java.util.List)
	 * actualiza cabecera y detalle del porcentaje de la comisin
	 */
	public void updatePorcentajeComisionCabeceraAndDetalle(CalificacionComisionCabecera updatePorcentajeComisionCabecera,	List detalList) {
		mantenimientoCOMPorcentajeComisionDAO.updatePorcentajeComisionCabeceraAndDetalle(updatePorcentajeComisionCabecera);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#insertPorcentajeComisionCabeceraAndDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera, java.util.List)
	 * Agrega cabecera y detalle del porcentaje de la comisin
	 */
	public void insertPorcentajeComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,	List detalList) {
		
		String codigoPorcentaje = mantenimientoCOMPorcentajeComisionDAO.getSecuenciaNextValue();
		Integer numeroItem = null;
		calificacionComisionCabecera.setCodigoPorcentaje(codigoPorcentaje);
		mantenimientoCOMPorcentajeComisionDAO.insertPorcentajeComisionCabecera(calificacionComisionCabecera);
		if(detalList !=null)
			for (Iterator iterator = detalList.iterator(); iterator.hasNext();) {
				CalificacionComisionDetalle calificacionComisionDetalle = (CalificacionComisionDetalle) iterator.next();
				calificacionComisionDetalle.setCodigoPorcentaje(codigoPorcentaje);			
				mantenimientoCOMPorcentajeComisionDAO.insertPorcentajeComisionDetalle(calificacionComisionDetalle);
				
			}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#insertPorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Agrega detalle del porcentaje de la comisin
	 */
	public void insertPorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle, Integer numeroItem) {
		calificacionComisionDetalle.setNumeroItem(numeroItem); 
		mantenimientoCOMPorcentajeComisionDAO.insertPorcentajeComisionDetalle(calificacionComisionDetalle);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#insertPorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Agrega detalle del porcentaje de la comisin
	 */
	public void insertPorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMPorcentajeComisionDAO.insertPorcentajeComisionDetalle(calificacionComisionDetalle);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#updatePorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Actualiza detalle del porcentaje de la comisin
	 */
	public void updatePorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMPorcentajeComisionDAO.updatePorcentajeComisionDetalle(calificacionComisionDetalle);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#deletePorcentajeComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Borra detalle del porcentaje de la comisin
	 */
	public void deletePorcentajeComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMPorcentajeComisionDAO.deletePorcentajeComisionDetalle(calificacionComisionDetalle);

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMPorcentajeComisionService#getNumeroItem(java.util.Map)
	 * Obtiene el mximo nmero de item
	 */
	public Integer getNumeroItem(Map criteria){
		return mantenimientoCOMPorcentajeComisionDAO.getMaximoNumeroItem(criteria);
				
	}
	
}

