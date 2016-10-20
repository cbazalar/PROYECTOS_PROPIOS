package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.scdf.model.Zona;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionClasificacion;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisiones;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ZonaDemandaAnticipada;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoCOMComisionService extends Service {

	/**
	 * retorna la lista de comisiones
	 * @param calificacionComisionCabecera
	 * @return
	 */
	public List getComisionesList(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * retorna la lista de niveles
	 * @param codigo
	 * @return
	 */
	public List getNivelList(String codigo);

	/**
	 * Borra la cabecera de la comisin y todo lo que pertenece a dicha comisin
	 * @param calificacionComisionCabecera
	 */
	public void deleteComisionCabeceraAndChild(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * retorna la lista de detalle de comisin
	 * @param calificacionComisionCabecera
	 * @return
	 */
	public List getComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * actualiza cabecera y detalle de la comisin
	 * @param calificacionComisionCabecera
	 * @param cabeceraList
	 */
	public void updateComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,List cabeceraList);

	/**
	 * Agrega cabecera y detalle de la comisin
	 * @param calificacionComisionCabecera
	 * @param detalList
	 */
	public void insertComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,List detalList);
	
	/**
	 * Agrega detalle de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void insertComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * Actualiza cabecera de la comisin
	 * @param calificacionComisionCabecera
	 */
	public void updateComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * Borra detalle de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void deleteComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * retorna la lista de detalle de comisin
	 * @param calificacionComisionDetalle
	 * @return
	 */
	public List getComisionDetalleList(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * Mantenimiento Zonas Demanda Anticipada
	 * */
	public List getZonasDemandaAnticipadaDetalle(Map criteria);
	
	public void insertZonasDemandaAnticipada(Map criteria);
	
	public void updateZonasDemandaAnticipada(Map criteria);
	
	public void deleteZonasDemandaAnticipada(Map criteria);
	
	public ZonaDemandaAnticipada getZonaDemandaAnticipada(Map criteria);
	
	public int getValidarZonasDemanda(Map criteria);
	
	public int getValidaExisteZona(Map criteria);
	
	/**
	 * Mantenimiento de Comisiones Recuperacin
	 * */
	public List getComisionesRecuperacionDetalle(Map criteria);
	
	public String getCodigoComisionSiguiente();
	
	public void insertComisiones(DatosComisiones datosComisiones,
			List comisionRegionesList, 
			List comisionDatosComisionList, 
			List comisionComisionEscalonadaList,
			List comisionClasificacion,
			Map criteria);
	
	public DatosComisiones getDatosComisionRecuperacion(Map criteria);
	
	public void updateComisiones(DatosComisiones datosComisiones,
			List comisionRegionesList, 
			List comisionDatosComisionList, 
			List comisionComisionEscalonadaList,
			List comisionClasificacion,
			Map criteria);
	
	public List getDatosComisionesZonasSubGerencia(Map criteria);
	
	public List getDatosComisiones(Map criteria);
	
	public List getComisionEscalonada(Map criteria);
	
	public int deleteComisionRecuperacion(Map criteria);
	
	public List getTipoComiList(Map criteria);

	public List getComDatosAdam(Map criteria);

	public List getComArchivoNominaList(Map criteria);
	
	public List getTipoClasificacionList();
	
	public List getClasificacionComisionList(Map criteria);
	
	public List getClasificacionByCodigoComisionList(Map criteria);
	
	public void deleteClasificacion (Map criteria);
	
	public void insertClasificacion (DatosComisionClasificacion datosComisionClasificacion);
	
	/**
	 * Devuelve datos de la Zona
	 * @param zona
	 * @return
	 */
	public Zona getZona(Zona zona);
	
	/**
	 * Devuelve Lista de Geerentes Retiradas
	 * @param criteria
	 * @return
	 */
	public List getListaMostrarGerentesRetiradas(Map criteria);
}
