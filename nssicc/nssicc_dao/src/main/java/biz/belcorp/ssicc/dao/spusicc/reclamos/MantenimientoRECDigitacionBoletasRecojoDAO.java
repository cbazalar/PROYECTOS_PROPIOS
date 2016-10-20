/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.DatosConsultora;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;

/**
 * @author 
 * Jose Antonio 
 * Cairampoma Granados
 *
 */
public interface MantenimientoRECDigitacionBoletasRecojoDAO extends DAO {

	/**
	 * Obtiene la lista de Cabeceras de Boleta de Recojo
	 * @param params
	 * @return
	 */
	public List getBoletasRecojoCabeceraList(Map params);

	/**
	 * Obtiene la lista de Detalles de Boleta de Recojo
	 * @param boletaRecojoCabecera
	 * @return
	 */
	public List getBoletaRecojoDetalleList(BoletaRecojoCabecera boletaRecojoCabecera);
	
	/**
	 * Obtiene un listado de Productos que van a ser ingresados como discrepantes
	 * @param params
	 * @return
	 */
	public List getProductosByCriteria(Map params);

	/**
	 * Obtiene el lsitado de discrepantes de una linea de boleta de Recojo
	 * @param boletaRecojoDetalle
	 * @return
	 */
	public List getBoletaRecojoDetalleDiscrepanteList(BoletaRecojoDetalle boletaRecojoDetalle);

	/**
	 * Actualiza la Cabecera, detalles y discrepantes de una Boleta de Recojo Digitada
	 * @param boletaRecojoCabecera
	 * @param detalList
	 * @param detalElimList
	 * @param detalDiscList
	 * @param usuario 
	 */
	public void updateBoletaRecojoCabeceraDetalle(BoletaRecojoCabecera boletaRecojoCabecera, List detalList,List detalElimList, Map detalDiscList, Usuario usuario);

	/**
	 * Obtiene una boleta de recojo
	 * @param criteria
	 * @return
	 */
	public List getCabeceraBoletaRecojo(Map criteria);	
	/**
	 * @param criteria
	 * @return
	 */
	public String getBloqueoConsultora(Map criteria);
	/**
	 * @param criteria
	 * @return
	 */
	public DatosConsultora getDatosConsultora(Map criteria);
	/**
	 * @param criteria
	 * @return
	 */
	public List getDetallesBoletaRecojo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve los datos de la boleta de recojo en base al numero de boleta
	 */
	public List getDatosBoletaRecojoSimplificada(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve la lista de Novedades de recojo 
	 */
	public List getNovedadesRecojo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve el codigo de la boleta de recojo
	 */
	public String getCodigoCabeceraBoletaRecojo(Map criteria);
		
	/**
	 * @param boletaRecojoCabecera
	 * @return
	 * Devuelve la cabecera de la boleta de recojo en base al id
	 */
	public BoletaRecojoCabecera getCabeceraBoletaRecojoById(BoletaRecojoCabecera boletaRecojoCabecera);
	
	/**
	 * @param params
	 * Ejecuta el procedimiento de Activacion de Boletas de Recojo(Reimprimir) 
	 */
	public void activarBoletaRecojoProcess(Map params);
	
	/**
	 * @param params
	 * Ejecuta el procedimiento de Eliminacion de Boletas de Recojo(Anular)
	 */
	public void eliminarBoletaRecojoProcess(Map params);
	
	/**
	 * @param numeroBoleta
	 * @return
	 * Retorna el detalle de las Boleta de Recojo
	 */
	public List getConsultaRECBoletaRecojoDetail(String numeroBoleta);
	
	/**
	 * 
	 * @param numeroBoleta
	 * @param usuario
	 * @return
	 */
	public void executeReclamoProcesoAbono(Map params);

}
