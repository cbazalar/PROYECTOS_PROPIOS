/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoPERMovimientosBancariosDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
public interface MantenimientoPERMovimientosBancariosDAO extends DAO {

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
	 * @return el siguiente numero de Lote para insertar
	 */
	public String getNextNumeroLote(String codigoPais, String codigoTipoOrigenDatos);
	
}
