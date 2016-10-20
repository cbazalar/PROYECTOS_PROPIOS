package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.LineaArmado;
/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPELineaArmadoDAO extends DAO{

	/**
	 * Retorna la lista de Centros de Distribucion por Pais
	 * @return
	 */
	public List getCentroDistList(Map criteria);

	/**
	 * Retorna las descripciones de las Lineas de Armado
	 * @return
	 */
	public List getLineaArmadobyOidCentro(Map criteria);
	
	/**
	 * Devuelve los datos de la cabecera de la Linea de Armado
	 * @param criteria
	 * @return
	 */
	public List getLineaArmadoCabec(Map criteria);
	
	/**
	 * Retorna el oid del centro de Distribucion
	 * @param criteria
	 * @return
	 */
	public String getOidCentroDistribucionPais( Map criteria);
	
	/**
	 * Elimina la Linea de Armado
	 * @param criteria
	 */
	public void deleteLineaArmado(Map criteria);
	
	/**
	 * Obtiene el centro por defecto del pais
	 * @param criteria
	 * @return
	 */
	public String getCentroDistribucionDefecto( Map criteria);
	
	/**
	 * Retorna la lista de Centro de Cubicaje
	 * @param criteria
	 * @return
	 */
	public List getProgramaCubicajeList(Map criteria);
	
	
	/**
	 * Retorna la lista de Plataforma
	 * @param criteria
	 * @return
	 */
	public List getPlataformaList(Map criteria);
	
	/**
	 * Retorna la lista de Tipo de Solicitud Consolidado
	 * @param criteria
	 * @return
	 */
	public List getTipoSolicitudConsoList(Map criteria);
	
	/**
	 * Retorna el objeto Linea Armado
	 * @param criteria
	 * @return
	 */
	public LineaArmado getLineaArmadoObject(Map criteria);
	
	/**
	 * Devuelve la lista de Usuarios Alarma para una Linea de Armado
	 * @param criteria
	 * @return
	 */
	public List getUsuarioAlarmaList(Map criteria);
	
	/**
	 * Devuelve el siguiente Oid de Linea de armado
	 * @param criteria
	 * @return
	 */
	public int getNextOidLinaeArmado(Map criteria);
	
	/**
	 * Devuelve el siguiente codigo de Linea de Armado
	 * @param criteria
	 * @return
	 */
	public int getMaxCodLinaeArmado(Map criteria);
	
	/**
	 * Devuelve el Oid del Programa de Cubicaje
	 * @param criteria
	 * @return
	 */
	public int getOidProgCubicaje(Map criteria);
	
	
	/**
	 * Devuelve el Oid de la Plataforma de Picado
	 * @param criteria
	 * @return
	 */
	public int getOidPlataforma(Map criteria);
	
	/**
	 * Verifica si existe una linea de armado por defecto para el CD
	 * @param criteria
	 * @return
	 */
	public int getExisteLineaDefault(Map criteria);
	
	/**
	 * Devuelve la descripcion de la linea de armado por defecto
	 * del centro de distribucion
	 * @param criteria
	 * @return
	 */
	public String getDescripcionLineaDefault( Map criteria);
	
	/**
	 * Registra una linea de armado
	 * @param criteria
	 */
	public void insertLineaArmado(Map criteria);
	
	/**
	 * Registra los mail de los Usurios de la Linea de Armado
	 * @param criteria
	 */
	public void insertUsuarioAlarma(Map criteria);
	
	/**
	 * Registra las Solicitudes de la Linea de Armado
	 * @param criteria
	 */
	public void insertTipoSolicitudLinea(Map criteria);

	/**
	 * Actuliza los datos de una linea de armado
	 * @param criteria
	 */
	public void updateLineaArmado(Map criteria);
	
	/**
	 * Elimina de lista de Usuario Alarma
	 * @param criteria
	 */
	public void deleteUsuarioAlarma(Map criteria);
	
	/**
	 * Obtiene el siguiente Oid de Usuario Alarma
	 * @return
	 */
	public int getNextOidUsuarioAlarma();
	
	/**
	 * Obtiene las solicitudes seleccionadas de una linea de armado
	 * @param criteria
	 * @return
	 */
	public List getTipoSolicitudConsoSelecList(Map criteria);
	
	/**
	 * Elimina una Solicitud de la Linea de Armado
	 * @param criteria
	 */
	public void deleteTipoSolicitudLinea(Map criteria);
	
	/**
	 * Obtiene el codigo de la linea por defecto del centro de distribucion
	 * @param criteria
	 * @return
	 */
	public String getCodigoLineaDefault( Map criteria);
	
	/**
	 * Obtiene el periodo actual del pais, marca y canal ingresado
	 * @param criteria
	 * @return
	 */
	public String getPeriodoActual( Map criteria);
	
	/**
	 * Devuelve la campana anterior
	 * @param criteria
	 * @return
	 */
	public String getPeriodoAnterior( Map criteria);
	
	/**
	 * Devuelve la descripcion del Centro de Distribucion
	 * @param criteria
	 * @return
	 */
	public String getDescCentroDistribucion( Map criteria);
	
	/**
	 * Devuelve la descripcion de la Linea de Armado
	 * @param criteria
	 * @return
	 */
	public String getDescLineaArmado( Map criteria);
	
	/**
	 * Devuelve la descripcion del Canal ingresado
	 * @param criteria
	 * @return
	 */
	public String getDescCanal( Map criteria);
	
	/**
	 * Devuelve la descripcion de la Marca ingresado
	 * @param criteria
	 * @return
	 */
	public String getDescMarca( Map criteria);
	
	/**
	 * Obtiene los totales por tipo de Movimiento
	 * @param criteria
	 * @return
	 */
	public List getTotalesMovimientoList(Map criteria);
	
	/**
	 * Valida sio existe periodo para el canal y marca ingresado
	 * @param criteria
	 * @return
	 */
	public int validaExistePeriodobyMarcaCanal(Map criteria);
	
	/**
	 * Obtiene la lista de Zonas
	 * @param criteria
	 * @return
	 */
	public List getZonasList(Map criteria);
	
	/**
	 * Obtiene la lista de Tipo de Solicitudes segun
	 * el pais
	 * @param criteria
	 * @return
	 */
	public List getTipoSolicitudList(Map criteria);
}
