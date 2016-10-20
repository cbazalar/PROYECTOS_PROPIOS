/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoPERMovimientosBancariosService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
public interface MantenimientoPERMovimientosBancariosService extends Service {

	/**
	 * 
	 * @param cabecera
	 * @return
	 */
	public List getMovimientosBancarios(MovimientosBancariosCabecera cabecera);

	/**
	 * 
	 * @param cabecera
	 * @return
	 */
	public List getMovimientosBancariosCabecera(MovimientosBancariosCabecera cabecera);
	
	/**
	 * 
	 * @param cabecera
	 * @return
	 */
	public MovimientosBancariosCabecera getBeanMovimientosBancariosCabecera(MovimientosBancariosCabecera cabecera);
	
	/**
	 * 
	 * @param detalle
	 * @return
	 */
	public List getMovimientosBancariosDetalle(MovimientosBancariosDetalle detalle);
	
	/**
	 * 
	 * @param cabecera
	 * @param usuario
	 */
	public void insertMovimientosBancariosCabecera(MovimientosBancariosCabecera cabecera, Usuario usuario);
	
	/**
	 * 
	 * @param detalle
	 * @param usuario
	 */
	public void insertMovimientosBancariosDetalle(MovimientosBancariosDetalle detalle, Usuario usuario);
	
	/**
	 * 
	 * @param cabecera
	 * @param usuario
	 */
	public void updateMovimientosBancariosCabecera(MovimientosBancariosCabecera cabecera, Usuario usuario);
	
	/**
	 * 
	 * @param detalle
	 * @param usuario
	 */
	public void updateMovimientosBancariosDetalle(MovimientosBancariosDetalle detalle, Usuario usuario);
	
	/**
	 * 
	 * @param cabecera
	 * @param usuario
	 */
	public void removeMovimientosBancariosCabecera(MovimientosBancariosCabecera cabecera, Usuario usuario);
	
	/**
	 * 
	 * @param detalle
	 * @param usuario
	 */
	public void removeMovimientosBancariosDetalle(MovimientosBancariosDetalle detalle, Usuario usuario);
	
	/**
	 * 
	 * @return
	 */
	public String getNextNumeroLote(String codigoPais, String codigoTipoOrigenDatos);

	/**
	 * Convierte del formato <i>dd/MM/yyyy</i> al formato <i>yyyyMMdd</i>. <br /> Ej: 31/12/2006 a 20061231
	 * @param fechaProceso String con el formato <i>dd/MM/yyyy</i> 
	 * @return String con el formato <i>yyyyMMdd</i>
	 */
	public String convierteFormatoFecha(String fechaProceso);
	
}