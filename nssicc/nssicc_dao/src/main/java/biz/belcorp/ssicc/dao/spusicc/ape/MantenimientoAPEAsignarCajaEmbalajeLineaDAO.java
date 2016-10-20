package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEAsignarCajaEmbalajeLineaDAO extends DAO{
	
	/**
	 * Devuelve la lista de los tipo de Caja Asignados a la Linea
	 * @param criteria
	 * @return
	 */
	public List getTipoCajaLineaList(Map criteria);
	
	/**
	 * Elimina la Asignacion del Tipo de Caja Embalaje a la Linea Seleccionada
	 * @param criteria
	 * @param items
	 */
	public void deleteTipoCajaLinea(Map criteria);
	
	/**
	 * Inserta la Asignacion del Tipo de Caja Embalaje para la Linea Seleccionada
	 * @param criteria
	 */
	public void insertTipoCajaLinea(Map criteria);
	
	/**
	 * Valida si ya esta registrado la el Tipo de Caja Emabalaje para la Linea Seleccionada
	 * @param criteria
	 * @return
	 */
	public String validaExisteTipoCajaLinea(Map criteria);
	
	/**
	 * Valida que por lo menos exista una caja activa para la linea seleccionada
	 * @param criteria
	 * @return
	 */
	public int validaMinimoTipoCajaLinea(Map criteria);
	
	/**
	 * Devuelve la lista de Tipo de Caja Embalaje
	 * @param criteria
	 * @return
	 */
	public List getTipoCajaEmbalajeComboList(Map criteria);
	
	/**
	 * Obtien el Oid del Tipo de Caja Embalaje
	 * @param criteria
	 * @return
	 */
	public String getOidTipoCajaEmbalbyCodigo(Map criteria);
	
	/**
	 * Obtiene la descripcion de la linea de armado
	 * @param criteria
	 * @return
	 */
	public String getDescripcionLineabyOid(Map criteria);
	
	/**
	 * Obtiene la Descripcion del Tipo de Caja de Embalaje
	 * @param criteria
	 * @return
	 */
	public String getDescripcionTipoCajabyOid(Map criteria);
}