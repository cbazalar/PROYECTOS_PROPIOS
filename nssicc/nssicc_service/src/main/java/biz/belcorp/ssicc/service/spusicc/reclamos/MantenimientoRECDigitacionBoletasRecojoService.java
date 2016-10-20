package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.DatosConsultora;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;
import biz.belcorp.ssicc.service.framework.Service;



public interface MantenimientoRECDigitacionBoletasRecojoService extends Service {

	/**
	 * Obtiene la lista de Cabeceras de Boleta de Recojo en base a los filtros enviados en el map  
	 * @param params
	 * @return
	 */
	public List getBoletasRecojoCabeceraList(Map params);
	
	/**
	 * Obtiene la lista de Detalles de la Boleta de Recojo enviado como parametro
	 * @param boletaRecojoCabecera
	 * @return
	 */
	public List getBoletaRecojoDetalleList(	BoletaRecojoCabecera boletaRecojoCabecera);
	
	/**
	 * Actulaiza los valores de la Cabecera, detalle, discrepantes de la Boleta Recojo Digitada
	 * @param boletaRecojoCabecera
	 * @param detalList
	 * @param detalElimList
	 * @param detalDiscList
	 * @param usuario 
	 */
	public void updateBoletaRecojoCabeceraDetalle(BoletaRecojoCabecera boletaRecojoCabecera, List detalList,List detalElimList, Map detalDiscList, Usuario usuario);

	/**
	 * Obtiene la lista de Discrepantes del detalle de Boleta de Recojo enviado como parametro
	 * @param boletaRecojoDetalle
	 * @return
	 */
	public List getBoletaRecojoDetalleDiscrepanteList(BoletaRecojoDetalle boletaRecojoDetalle);
	
	/**
	 * @param params
	 * @return
	 */
	public List getProductosByCriteria(Map params);

	/**
	 * @param criteria
	 * @return
	 * Devuelve una lista de cabeceras de Boletas de recojo
	 */
	public List getCabeceraBoletaRecojo(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 * Devuelve si una consultora ta bloqueada o no
	 */
	public String getBloqueoConsultora(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve los datos de la consultora
	 */
	public DatosConsultora getDatosConsultora(Map criteria);
	/**
	 * @param criteria
	 * @return
	 * Devuelve una lista de detalles de Boletas de recojo
	 */
	public List getDetallesBoletaRecojo(Map criteria);

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
}
