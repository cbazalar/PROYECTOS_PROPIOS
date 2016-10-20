package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoSTOLevantamientoErroresValidacionService extends
		Service {

	public List getLevantamientoErroresValidacionList(Map params);

	public List getValidacionesByDocumento(Map params);
	
	public List getGestionDocumentoList(Map params);
	

	public List getAccionesGestionSTO(Map params);
	
	public void updateAprobarDocumentosSTO(Map params);
	
	public void updateDesaprobarDocumentosSTO(Map params);
	
	public void updateRechazarDocumentosSTO(Map params);
	
	public String getMensajesxAccionSTO(Map params);

	public List getConsultaValidacionesList(Map params);
	
	/**
	 * @param params
	 * @return
	 */
	public List getListaHoras (Map params);
	
	/**
	 * Metodo que devuelve la lista de cupones para bloquear o desbloquear
	 * @param params
	 * @return
	 */
	public List getCuponesRechazoSello(Map params);
	
	/**
	 * Metodo para bloquear los cupones
	 * @param params
	 */
	public void bloqueoCuponSTO(Map params);
	
	/**
	 * Metodo para desbloquear los cupones
	 * @param params
	 */
	public void desbloqueoCuponSTO(Map params);
	
	/**
	 * Metodo que devuelve la lista de acciones
	 * @param params
	 * @return
	 */
	public List getAccionesByRol(Map params);
	
	/**
	 * Metodo que inserta o modifica los roles de sto
	 * @param Acceso
	 * @param rol
	 */
	public void insertRol(List Acceso, String rol, String usuario);
	
	/**
	 * @param params
	 * @return
	 * Obtiene la lista para eliminar de la tabla temporal
	 */
	public List getConsultaValidacionesTemporalList(Map params);
		
	/**
	 * @param criteria
	 * Elimina los pedidos de Ordenes de compra
	 */
	public void executeEliminarPedidosOrdenCompra(String[] selectedItems,Map filter);
	
	/**
	 * Metodo que obtiene el numero de registros por pagina
	 * @param criteria
	 * @return
	 */
	public String getNumeroRegistrosSTO(Map criteria);
	
	/**
	 * Metodo que devuelve la fecha final del proceso
	 * @param criteria
	 * @return
	 */
	public String getFechaFinProceso(Map criteria);
	
	/**
	 * Metodo que obtiene los tipos de documento para ejecutar los reportes
	 * @param criteria
	 * @return
	 */
	public List getReporteTiposDocumentosSTO(Map criteria);
	
	/**
	 * Metodo que obtiene los repotes de la tabla STO_REPOR_TIPO_DOCUM
	 * @param criteria
	 * @return
	 */
	public List getReporteSTO(Map criteria);
	
	/**
	 * Metodo que devuelve la lista de CDRs a eliminar
	 * @param params
	 * @return
	 */
	public List getConsultaCDRTemporalList(Map params);

	/**
	 * Metodo que obtiene la lista de origenes
	 * @param params
	 * @return
	 */
	public List getOrigenSTOByTipoDocumento(Map params);
	
	/**
	 * Metodo que obtiene la lista de zonas de arribo
	 * @param params
	 * @return
	 */
	public List getZonaArriboSTOByTipoDocumento(Map params);

	/**
	 * @param params
	 * @return
	 */
	public List getReclamosEliminados(Map params);
}
