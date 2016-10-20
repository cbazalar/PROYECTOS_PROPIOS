package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEAsignarCajaEmbalajeLineaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface MantenimientoAPEAsignarCajaEmbalajeLineaService extends Service{

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
	public void deleteTipoCajaLinea(Map criteria, String[] items);
	
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