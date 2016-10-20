/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionCalculo;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionClasificacion;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionEscalonada;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisiones;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosRegionesSubGerencia;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ZonaDemandaAnticipada;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;



/**
 * @author 
 *
 */
public interface MantenimientoCOMComisionDAO extends DAO {

	/**
	 * retorna la lista de comisiones
	 * @param calificacionComisionCabecera
	 * @return
	 */
	public List getComisionesList(CalificacionComisionCabecera calificacionComisionCabecera);

	/**
	 * retorna la lista de nivel
	 * @param codigo
	 * @return
	 */
	public List getNivelList(String codigo);
	
	/**
	 * Borra la cabecera de la comisin 
	 * @param calificacionComisionCabecera
	 */
	public void deleteComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera);

	/**
	 * Borra detalle de la comisin
	 * @param calificacionComisionCabecera
	 */
	public void deleteComisionDetalle(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 *  retorna la lista de detalle de comisin
	 * @param calificacionComisionCabecera
	 * @return
	 */
	public List getComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * Valido para el codigo de la comisin - se autogenera un secuencial
	 * @return
	 */
	public String getSecuenciaNextValue();
	
	/**
	 * actualiza cabecera y detalle de la comisin
	 * @param calificacionComisionCabecera
	 */
	public void updateComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * Agrega cabecera de la comisin
	 * @param calificacionComisionCabecera
	 */
	public void insertComisionCabecera(CalificacionComisionCabecera calificacionComisionCabecera);
	
	/**
	 * Agrega detalle de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void insertComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * Actualiza Detalle de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void updateComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 * Elimina detalle de la comisin
	 * @param calificacionComisionDetalle
	 */
	public void deleteComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle);
	
	/**
	 *  retorna la lista de detalle de comisin
	 * @param calificacionComisionDetalle
	 * @return
	 */
	public List getComisionDetalleList(CalificacionComisionDetalle calificacionComisionDetalle);
	
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
	public void insertComisiones(DatosComisiones datosComisiones);
	public void insertDetalleComisiones(DatosRegionesSubGerencia datosRegionesSubGerencia);
	public int getOidComisiones(Map criteria);
	public void insertComisionCalculada(DatosComisionCalculo datosComisionCalculo);
	public void insertComisionEscalonada(DatosComisionEscalonada datosComisionEscalonada);
	public void insertComisionCobranza(Map map);
	public int getOidCobranzaComisiones(Map criteria);
	
	public DatosComisiones getDatosComisionRecuperacion(Map criteria);
	
	public void updateComisiones(DatosComisiones datosComisiones);
	
	public List getDatosComisionesZonasSubGerencia(Map criteria);
	
	public List getDatosComisiones(Map criteria);
	
	public List getComisionEscalonada(Map criteria);
	public Integer getCantCobranza(Map criteria);
	
	public void deleteZonasSubGerencia(Map criteria);
	public void deleteDatosComisiones(Map criteria);
	public void deleteDatosEscalonada(Map criteria);
	public void deleteDatosCobranza(Map criteria);
	public void deleteComisionRecuperacion(Map criteria);
	
	public int getCantComisionesCalculadasXRegion(Map criteria);
	public int getCantComisionesCalculadasxZona (Map criteria);
	public int getCantComisionesCalculadas(Map criteria);
	
	public List getBasesComision(Map criteria);

	public List getComisionCalcuRegionList(Map map);
	
	public List getComisionVentaNetaEfectivaList(Map criteria);

	public List getTipoComiList(Map criteria);

	public List getComDatosAdam(Map criteria);

	public List getComArchivoNominaList(Map criteria);
	
	public List getTipoClasificacionList();
	
	public List getClasificacionComisionList(Map criteria);
	
	public List getClasificacionByCodigoComisionList(Map criteria);
	
	public void deleteClasificacion (Map criteria);
	
	public void insertClasificacion (DatosComisionClasificacion datosComisionClasificacion);
	
	public List getListaMostrarGerentesRetiradas(Map criteria);
	
	public String getCodigoBaseComision(Map criteria);
}
