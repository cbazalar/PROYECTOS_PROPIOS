/**
 *
 */
package biz.belcorp.ssicc.service.spncd;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoCUPLogrosService.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */
public interface MantenimientoCUPLogrosService extends Service {

	/**
	 * Devuelve la lista de logros segun criteria
	 * @param criteria
	 * @return
	 */
	public List getLogrosList(Map criteria);

	/**
	 * Elimina logicamente los logros de la entidad
	 * @param items
	 */
	public void deleteLogros(String[] items, String usuario);

	/**
	 * Carga el combo de tipos de logros
	 * @return
	 */
	public List getTiposLogro();
	
	/**
	 * Carga el combo de Medios de captura
	 * @return
	 */
	public List getMediosCaptura();
	
	/**
	 * Obtiene el logro en base a su primary key
	 * @param criteria
	 * @return
	 */
	public List getLogrosByIdList(Map criteria);
	
	/**
	 * Inserta el logro en la entidad correspondiente
	 * @param criteria
	 */
	public void insertLogros(Map criteria);
	
	/**
	 * Actualiza el logro en la entidad correspondiente
	 * @param criteria
	 */
	public void updateLogros(Map criteria);
	
	/**
	 * Obtiene el periodo de ingreso de la consultora
	 * @param codigoConsultora
	 * @return
	 */
	public String getPeriodoIngresoConsultora(String codigoConsultora);
	
	/**
	 * Valida el rango de montos
	 * @param criteria
	 * @return
	 */
	public LabelValue getValidaMontoLogro(Map criteria);
	
	/**
	 * Devuelve el parametro indicador de nuevas para logros
	 * @param criteria
	 * @return
	 */
	public String getParametroNuevasLogro(Map criteria);
	
	/**
	 * Devuelve el indicador de actividad de la consultora
	 * @param criteria
	 * @return
	 */
	public String getIndicadorActivoConsultora(Map criteria);
	
	/**
	 * Devuelve el periodo siguiente
	 * @param criteria
	 * @return
	 */
	public String getPeriodoSiguiente(Map criteria);
}
