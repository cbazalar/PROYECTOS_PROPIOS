package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.SubLineaArmado;
/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPESubLineaArmadoDAO extends DAO{

	/**
	 * Retorna La Lista de Linea de Armado de Centro de Distribucion Seleccionado
	 * @param criteria
	 * @return
	 */
	public List getLineaArmadobyOidCentro(Map criteria);
	
	/**
	 * Retorna el Codigo de Linea de Armado por defecto del Centro de Distribucion
	 * Seleccionado
	 * @param criteria
	 * @return
	 */
	public String getCodLineaArmadaDefecto(Map criteria);
	
	/**
	 * Retorna la Lista de Sistema de Picado
	 * @param criteria
	 * @return
	 */
	public List getSistemaPicadoList(Map criteria);
	
	/**
	 * Retorna la Lista de Codigo de Impresora
	 * @return
	 */
	public List getCodigoImpresoraList();
	
	/**
	 * Retorna la Lista de Sublinea
	 * @param criteria
	 * @return
	 */
	public List getSubLineaArmadoCabec(Map criteria);
	
	/**
	 * Devuelve el Oid de la Linea de Armado
	 * @param criteria
	 * @return
	 */
	public String getOidLineaArmadobyCodigo(Map criteria);
	
	/**
	 * Elimina una Sub Linea de Armado
	 * @param criteria
	 * @return
	 */
	public void deleteSubLineaArmado(Map criteria);
	
	/**
	 * Obtiene la lista de Linea de Armado
	 * @param criteria
	 * @return
	 */
	public List getLineaArmadoComboList(Map criteria);
	
	/**
	 * Obtiene la linea de armado por defecto del centro de distribucin
	 * @param criteria
	 * @return
	 */
	public String getCodLineaArmadaDefectoList(Map criteria);
	
	/**
	 * Obtiene el objeto SubLinea de Armado
	 * @param criteria
	 * @return
	 */
	public SubLineaArmado getSubLineaArmadoObject(Map criteria);
	
	/**
	 * Devuelve los Tipos de Caja Producto por Pais
	 * @param criteria
	 * @return
	 */
	public List getTipoCajaProductoList(Map criteria);
	
	/**
	 * Devuelve los Tipos de Caja Producto por  Sub Linea de Armado
	 * @param criteria
	 * @return
	 */
	public List getTipoCajaSubLineaList(Map criteria);
	
	/**
	 * Devuelve el Oid de la SubLinea
	 * @param criteria
	 * @return
	 */
	public String getOidSubLineaArmadobyCodigo(Map criteria);
	
	/**
	 * Elimina de lista de Tipo de Caja
	 * @param criteria
	 */
	public void deleteTipoCaja(Map criteria);
	
	/**
	 * Obtiene el siguiente Oid de Tipo de Caja Producto
	 * @return
	 */
	public int getNextOidTipoCaja();
	
	/**
	 * Obtiene el Oid de Tipo de Caja Producto
	 * @param criteria
	 * @return
	 */
	public String getOidTipoCajaProducto(Map criteria);
	
	/**
	 * Inserta un tipo de caja producto
	 * @param criteria
	 */
	public void insertTipoCaja(Map criteria);
	
	/**
	 * Inserta una SubLinea de Armado
	 * @param criteria
	 */
	public void insertSubLineaArmado(Map criteria);
	
	/**
	 * Inserta una Impresora para la SubLinea de Armado
	 * @param criteria
	 */
	public void insertImpresoraSubLinea(Map criteria);
	
	/**
	 * Obtiene el Oid del Sistema de Picado
	 * @param criteria
	 * @return
	 */
	public String getOidSistemaPicado(Map criteria);
	
	/**
	 * Obtiene el siguiente nuero de oid de la sublinea
	 * @return
	 */
	public int getNextOidSubLinea();
	
	/**
	 * Obtiene el siguiente codigo de sublinea de armado
	 * @return
	 */
	public int getMaxCodSubLinesArmado();
	
	/**
	 * Actuliza los datos de una Sublinea de armado
	 * @param criteria
	 */
	public void updateSubLineaArmado(Map criteria);
	
	/**
	 * Valida si existe la letra del anaquel en el centro de
	 * distribucion 
	 * @param criteria
	 * @return
	 */
	public int getExisteLetraAnquelCD(Map criteria);
	
	/**
	 * Obtiene el Oid del Codigo de Impresora
	 * @param criteria
	 * @return
	 */
	public int getOidCodigoImpresora(Map criteria);
	
	/**
	 * Elimina un codigo de impresora de la Sub Linea de Armado
	 * @param criteria
	 */
	public void deleteCodigoImpresora(Map criteria);
	
	/**
	 * Devuelve el codigo de Linea de Armado por el Oid
	 * @param criteria
	 * @return
	 */
	public String getCodigoLineaArmadobyOid(Map criteria);
}
