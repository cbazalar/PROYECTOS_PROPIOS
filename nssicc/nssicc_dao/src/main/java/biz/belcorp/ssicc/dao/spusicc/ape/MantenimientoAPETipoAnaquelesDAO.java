package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoAnaquel;

/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPETipoAnaquelesDAO extends DAO{

  /**
	 * Retorna los Codigos de los Tipos de Anaquel
	 * @param criteria
	 * @return
	 */
	public List getCodigoTipoAnaquelesList(Map criteria);
	
	/**
	 * Retorna la lista de Tipo de Anaqules
	 * @param criteria
	 * @return
	 */
	public List getTipoAnaquelesList(Map criteria);
	
	/**
	 * Elimina un Tipo de Anaquel
	 * @param map
	 * @param items
	 */
	public void deleteTipoAnaquel(Map criteria);
	
	/**
	 * Retorna el objeto Tipo Anaquel
	 * @param criteria
	 * @return
	 */
	public TipoAnaquel getTipoAnaquelObject(Map criteria);
	
	/**
	 * Retorna la lista de Tipo Chanel
	 * @param criteria
	 * @return
	 */
	public List getTipoChanelList(Map criteria);
	
	/**
	 * Retorna el oid Tipo Chanel
	 * @param criteria
	 * @return
	 */
	public int getOidTipoChanel(Map criteria);
	
	/**
	 * Actuliza los datos de Tipo de Anaquel
	 * @param criteria
	 * @return
	 */
	public void updateTipoAnaquel(Map criteria);
	
	/**
	 * Retorna el siguiente oid de Tipo de Anaquel
	 * @param criteria
	 * @return
	 */
	public int getNextOidTipoAnaquel(Map criteria);
	
	/**
	 * Retorna el maximo codigo de Tipo de Anaquel
	 * @param criteria
	 * @return
	 */
	public int getExisteCodTipoAnaquel(Map criteria);
	
	/**
	 * Registra un Tipo de Anaquel
	 * @param criteria
	 * @return
	 */
	public void insertTipoAnaquel(Map criteria);
	
	/**
	 * Inserta en la tabla de idiomas
	 * @param criteria
	 */
	public void insertIdiomasComunAPE(Map criteria);
	
	/**
	 * Actualiza la tabla idiomas
	 * @param criteria
	 */
	public void updateIdiomasComunAPE(Map criteria);
	
	/**
	 * Valida si el Tipo de Anaquel esta marcado por Default
	 * @param criteria
	 * @return
	 */
	public int validaTipoAnaquelDefaultbyOid(Map criteria);
	
	/**
	 * Valida si existe un tipo de anaquel defaul por Tipo
	 * @param criteria
	 * @return
	 */
	public int validaExisteTipoAnaquelDefaultbyTipo(Map criteria);
	
	/**
	 * Actualiza el valor por defecto del tipo
	 * @param criteria
	 */
	public void updateValorTipoDefault(Map criteria);
	
	/**
	 * Obtiene el oid actual del tipo de anaquel por default
	 * @param criteria
	 * @return
	 */
	public String getOidTipoAnaquelbyTipoDefault(Map criteria);
}