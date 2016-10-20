/*
 * Created on 10/11/2005 04:03:53 PM
 *
 * biz.belcorp.ssicc.service.IdiomaService
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="IdiomaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a> 
 */
public interface IdiomaService extends Service {


	/**
	 * Obtiene todos los idiomas
	 */
	public List getIdiomas(Idioma idioma);

    /**
     * Obtiene todos los idiomas tomando como criterios de busqueda los valores
     * enviados a traves de un Map
     */
    public List getIdiomasByCriteria(Map criteria);

	/**
	 * Obtiene la información del idioma en base al código.
	 * 
	 * @param codigo
	 *            el codigo del idioma
	 * @return idioma objeto idioma poblado
	 */
	public Idioma getIdioma(final String codigo);

	/**
	 * Obtiene la información del idioma en base al código ISO3166.
	 * 
	 * @param codigo
	 *            el codigo ISO3166 del idioma
	 * @throws IllegalArgumentException si el codigo es null o blanco.
	 * @return idioma objeto idioma poblado
	 */
	public Idioma getIdiomaByCodigoISO(final String codigo);

	/**
	 * Registra la información de un nuevo idioma
	 * 
	 * @param idioma
	 *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la información
	 */
	public void insertIdioma(Idioma idioma, Usuario usuario);

	/**
	 * Actualiza la informacion de un idioma
	 * 
	 * @param idioma
	 *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la información
	 */
	public void updateIdioma(Idioma idioma, Usuario usuario);

	/**
	 * Elimina un idioma de la base de datos en base a su codigo
	 * 
	 * @param codigo
	 *            el codigo del idioma
     * @param usuario
     *            el usuario que elimina la información
	 */
	public void removeIdioma(final String codigo, Usuario usuario);
}	
