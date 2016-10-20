package biz.belcorp.ssicc.dao.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spisicc.model.ClasificacionVIP;
import biz.belcorp.ssicc.dao.spisicc.model.Etiqueta;
import biz.belcorp.ssicc.dao.spisicc.model.EtiquetaClasificacion;
import biz.belcorp.ssicc.dao.spisicc.model.EtiquetaEstatus;
import biz.belcorp.ssicc.dao.spisicc.model.ProceImpresion;


/**
 * Service con metodos para las consultas invocados por las pantallas de Mantenimiento Proceso Impresion
 * 
 * <p>
 * <a href="MantenimientoIMPProcesoImpresionDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Gonzalo Javier Huertas Agurto</a>
 * 
 */

public interface MantenimientoIMPProcesoImpresionDAO extends DAO {
	
	/**
	 * Retorna la lista de  proceso impresion
	 * @param params
	 * @return
	 */
	public List getProcesosImpresionByCriteria(Map params);
	
	/**
	 * Devuelve un proceso de impresion en funcion de su OID
	 * @param params
	 * @return
	 */
	public ProceImpresion getProcesosImpresion(Map params);
	
	/**
	 * Inserta un registro en la tabla PROCESOS DE IMPRESION
	 * @param proceImpresion
	 * @param usuario
	 */
	void insertProcesoImpresion(ProceImpresion proceImpresion, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla PROCESOS DE IMPRESION
	 * @param tipoBloqueo
	 * @param usuario
	 */
	void updateProcesoImpresion(ProceImpresion proceImpresion, Usuario usuario);
	
	/**
	 * Retorna la lista de  etiquetas
	 * @param params
	 * @return
	 */
	public List getEtiquetaByCriteria(Map params);
	
	/**
	 * Devuelve una etiqueta en funcion de su OID
	 * @param params
	 * @return
	 */
	public Etiqueta getEtiqueta(Map params);
	
	/**
	 * Inserta un registro en la tabla ETIQUETAS
	 * @param etiqueta
	 * @param usuario
	 */
	void insertEtiqueta(Etiqueta etiqueta, Usuario usuario);
	
	/**
	 * Actualiza un registro en la tabla ETIQUETAS
	 * @param etiqueta
	 * @param usuario
	 */
	void updateEtiqueta(Etiqueta etiqueta, Usuario usuario);
	
	/**
	 * elimina un registro en la tabla ETIQUETAS
	 * @param etiqueta
	 */
	void deleteEtiqueta(Etiqueta etiqueta);
	
	/**
	 * Retorna la lista de  etiquetas
	 * @param params
	 * @return
	 */
	public List getEtiquetasList();
	
	/**
	 * Retorna la lista de estatus
	 * @param params
	 * @return
	 */
	public List getEstatusList(Map params);
	
	/**
	 * Retorna la lista de  etiquetas estatus
	 * @param params
	 * @return
	 */
	public List getEtiquetaEstatusByCriteria(Map params);
	
	/**
	 * Inserta un registro en la tabla ETIQUETAS ESTATUS
	 * @param etiquetaEstatus
	 * @param usuario
	 */
	void insertEtiquetaEstatus(EtiquetaEstatus etiquetaEstatus, Usuario usuario);
	
	/**
	 * elimina un registro en la tabla ETIQUETAS ESTATUS
	 * @param etiquetaEstatus
	 */
	void deleteEtiquetaEstatus(EtiquetaEstatus etiquetaEstatus);
	
	/**
	 * Retorna un objeto del tipo EtiquetaEstatus
	 * @param params
	 * @return
	 */
	EtiquetaEstatus getEtiquetaEstatus(Map params);
	
	/**
	 * Retorna la lista de  tipos de clasificacion
	 * @param params
	 * @return
	 */
	public List getTipoClasificacionList(Map params);
	
	/**
	 * Retorna la lista de clasificacion
	 * @param params
	 * @return
	 */
	public List getClasificacionList(Map params);
	
	/**
	 * Retorna la lista de  etiquetas clasificacion
	 * @param params
	 * @return
	 */
	public List getEtiquetaClasificacionByCriteria(Map params);
	
	/**
	 * Retorna un objeto del tipo EtiquetaClasificacion
	 * @param params
	 * @return
	 */
	EtiquetaClasificacion getEtiquetaClasificacion(Map params);
	
	/**
	 * Inserta un registro en la tabla ETIQUETAS CLASIFICACION
	 * @param etiquetaClasificacion
	 * @param usuario
	 */
	void insertEtiquetaClasificacion(EtiquetaClasificacion etiquetaClasificacion, Usuario usuario);
	
	/**
	 * elimina un registro en la tabla ETIQUETAS CLASIFICACION
	 * @param etiquetaClasificacion
	 */
	void deleteEtiquetaClasificacion(EtiquetaClasificacion etiquetaClasificacion);
	
	/**
	 * Retorna la lista de clasificacion VIP
	 * @param params
	 * @return
	 */
	public List getClasificacionVIPByCriteria(Map params);
	
	/**
	 * Retorna un objeto del tipo ClasificacionVIP
	 * @param params
	 * @return
	 */
	ClasificacionVIP getClasificacionVIP(Map params);
	
	/**
	 * Inserta un registro en la tabla ClasificacionVIP
	 * @param clasificacionVIP
	 * @param usuario
	 */
	void insertClasificacionVIP(ClasificacionVIP clasificacionVIP, Usuario usuario);
	
	/**
	 * elimina un registro en la tabla ClasificacionVIP
	 * @param clasificacionVIP
	 */
	void deleteClasificacionVIP(ClasificacionVIP clasificacionVIP);
}
