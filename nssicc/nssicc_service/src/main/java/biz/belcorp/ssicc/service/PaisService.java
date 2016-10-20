/*
 * Created on 15/03/2005 11:08:56 AM
 *
 * biz.belcorp.ssicc.service.PaisService
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here. 
 * <p>
 * <a href="PaisService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface PaisService extends Service {

	

	/**
	 * Obtiene todos los paises
	 */
	public List getPaises(Pais pais);

    /**
     * Obtiene todos los paises tomando como criterios de busqueda los valores
     * enviados a traves de un Map
     */
    public List getPaisesByCriteria(Map criteria);

	/**
	 * Obtiene la información del pais en base al código.
	 * 
	 * @param codigo
	 *            el codigo del pais
	 * @return pais objeto pais poblado
	 */
	public Pais getPais(final String codigo);

	/**
	 * Obtiene la información del pais en base al código ISO3166.
	 * 
	 * @param codigo
	 *            el codigo ISO3166 del pais
	 * @throws IllegalArgumentException si el codigo es null o blanco.
	 * @return pais objeto pais poblado
	 */
	public Pais getPaisByCodigoISO(final String codigo);

	/**
	 * Registra la información de un nuevo pais
	 * 
	 * @param pais
	 *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la información
	 */
	public void insertPais(Pais pais, Usuario usuario);

	/**
	 * Actualiza la informacion de un pais
	 * 
	 * @param pais
	 *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la información
	 */
	public void updatePais(Pais pais, Usuario usuario);

	/**
	 * Elimina un pais de la base de datos en base a su codigo
	 * 
	 * @param codigo
	 *            el codigo del pais
     * @param usuario
     *            el usuario que elimina la información
	 */
	public void removePais(final String codigo, Usuario usuario);

	/**
	 * Devuelve Lista de Parametros de Segmentacion por Pais
	 * @param codigoPais
	 * @return
	 */
	public List getParametrosSegmentacionByPais(String codigoPais);
	
	/**
	 * Inserta registro en Parametros Segmentacion
	 * @param criteria
	 */
	public void insertParametrosSegmentacion(Map criteria);
	
	/**
	 * Actualiza registro en Parametros Segmentacion
	 * @param criteria
	 */
	public void updateParametrosSegmentacion(Map criteria);

	/**
	 * @param contextRoot
	 * @return
	 */
	public Pais getPaisByContextRoot(String contextRoot);
}
