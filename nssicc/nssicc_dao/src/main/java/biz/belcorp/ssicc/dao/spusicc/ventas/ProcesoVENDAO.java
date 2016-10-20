/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.ventas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Calendario;
import biz.belcorp.ssicc.dao.sisicc.model.FeriadoZona;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="procesoVENForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public interface ProcesoVENDAO extends DAO {

	/**
	 * ejecuta proceso generico
	 * @param nombreExecute
	 * @param criteria
	 */
	public void executeGenerico(String nombreExecute, Map criteria);
	
    /**
     * Actualiza calendario
     * @param calendario
     * @param usuario
     */
    public void updateCalendario(Calendario calendario, Usuario usuario);
	
	/**
	 * Retorna lista de calendarios
	 * @param criteria
	 * @return
	 */
	public List getCalendarios(Map criteria);
	
	/**
	 * Devuleve el calendario
	 * @param criteria
	 * @return
	 */
	public Calendario getCalendario(Map criteria);
	
	/**
	 * Devuelve los feriados d ela zona
	 * @param criteria
	 * @return
	 */
	public List getFeriadoZona(Map criteria);
	
	/**
	 * Actualiza los feriados d ela zona
	 * @param feriadoZona
	 * @param usuario
	 */
	public void updateFeriadoZona(FeriadoZona feriadoZona, Usuario usuario);
	
	/**
	 * Inserta los feriados d ela zona
	 * @param feriadoZona
	 * @param usuario
	 */
	public void insertFeriadoZona(FeriadoZona feriadoZona, Usuario usuario);
	
	/**
	 * Elimina los feriados d ela zona
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
	 * Retorna el indicador habilitacion RUV
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
	 * Genera la informacion para el reporte ZIP
	 * @param map
	 */
	public void executeGenerarArchivosLibroVentasDetalleSII(Map map);
	
}