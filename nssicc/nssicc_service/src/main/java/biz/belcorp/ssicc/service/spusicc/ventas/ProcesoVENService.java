/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.ventas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Calendario;
import biz.belcorp.ssicc.dao.sisicc.model.FeriadoZona;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoVENService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public interface ProcesoVENService extends Service {

	/**
	 * Ejecuta procedimiento generico
	 * @param nombreExecute
	 * @param criteria
	 */
	public void executeGenerico(String nombreExecute, Map criteria)  ;
	
	/**Actualiza calensarios
	 * @param calendario
	 * @param usuario
	 */
	public void updateCalendario(Calendario calendario, Usuario usuario);
	
	/**devuelve lista de calendarios
	 * @param criteria
	 * @return
	 */
	public List getCalendarios(Map criteria);
	
	/**
	 * devuelve el calendario
	 * @param criteria
	 * @return
	 */
	public Calendario getCalendario(Map criteria);
	
    /**
     * devuelve los feriados d euna zona
     * @param criteria
     * @return
     */
    public List getFeriadoZona(Map criteria);
	
	/**
	 * Actualiza feriados de una zona
	 * @param feriadoZona
	 * @param usuario
	 */
	public void updateFeriadoZona(FeriadoZona feriadoZona, Usuario usuario);
	
	/**
	 * Inserta los feriados de una zona
	 * @param feriadoZona
	 * @param usuario
	 */
	public void insertFeriadoZona(FeriadoZona feriadoZona, Usuario usuario);
	
	/**
	 * elimina los feriados d euna zona
	 * @param feriadoZona
	 * @param usuario
	 */
	public void deleteFeriadoZona(FeriadoZona feriadoZona, Usuario usuario);

	/**
	 * Metodo que trae las zonas de una region
	 * @param feriadoRegion
	 * @return
	 */
	public List getZonasRegion(String feriadoRegion);

	/**
	 * Retorna el indicador de habilitacion RUV
	 * @param criteria
	 * @return
	 */
	public String getIndicadorHabilitacionRuv(Map criteria);

	/**
	 * Genera la informacion para el reporte RUV
	 * @param map
	 */
	public void executeGeneracionReporteRUV(Map map);
	
	/**
	 * Genera los archivos de libro de ventas - detalles SII
	 * @param map
	 */
	public void executeGenerarArchivosLibroVentasDetalleSII(Map map);
	
}