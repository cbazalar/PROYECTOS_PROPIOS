package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface ProcesoSTOLevantamientoErroresValidacionDAO extends DAO {

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
	 * Metodo que devuelve los motivos de rechazo por documento de STO
	 * @param params
	 * @return
	 */
	public List getMotivosRechazo(Map params);
	
	/**
	 * Metodo que devuelve la lista de acciones
	 * @param params
	 * @return
	 */
	public List getAccionesByRol(Map params);
	
	
	/**
	 * Metodo para obtener el codigo del rol
	 * @param params
	 * @return
	 */
	public String getCodigoRolDocumento(Map params);
	
	/**
	 * Metodo para obtener el codigo del rol
	 * @param params
	 * @return
	 */
	public String getCodigoRolDocumentoAccion(Map params);
	
	/**
	 * Inserta en la  tabla STO_TIPO_DOCUM_ROL
	 * @param params
	 */
	public void insertRolDocumento(Map params);
	
	/**
	 * Inserta en la tabla STO_TIPO_DOCUM_ACCIO_ROL
	 * @param params
	 */
	public void insertRolDocumentoAccion(Map params);
	
	/**
	 * Modifica la tabla STO_TIPO_DOCUM_ROL
	 * @param params
	 */
	public void updateRolDocumento(Map params);
	
	/**
	 * Modifica la tabla STO_TIPO_DOCUM_ACCIO_ROL
	 * @param params
	 */
	public void updateRolDocumentoAccion(Map params);
	
	/**
	 * Metodo que inserta los rechazos manuales para el envio de mails
	 * @param criteria
	 */
	public void insertRechazoEnvioMailSTO(Map criteria);
	
	/**
	 * Metodo que devuelve la lista de estados en base al tipo de documento
	 * @param params
	 * @return
	 */
	public List getEstadosSTOByTipoDocumento(Map params);
	
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
	public void executeEliminarPedidosOrdenCompra(Map criteria);
	
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
	 * Metodo para consultar CDRs a eliminar
	 * @param params
	 * @return
	 */
	public List getConsultaCDRTemporalList(Map params);
	
	/**
	 * Metodo que retorna el indicador de mensaje de rechazo
	 * @param params
	 * @return
	 */
	public String getParametroRechazoByDocumento(Map params);
	
	/**
	 * Metodo que retorna la lineas minima y maxima a paginar en listados.  
	 * @param params
	 * @return
	 */
	public String getNumeroRegistroMinMaxSTO(Map params); 
	
	/**
	 * @param params
	 * @return
	 */
	public List getValidacionesExcepcionByDocumento(Map params);
}
