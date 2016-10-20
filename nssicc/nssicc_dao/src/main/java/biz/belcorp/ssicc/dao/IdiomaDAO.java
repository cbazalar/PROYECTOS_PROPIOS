/*
 * Created on 10/11/2005 02:54:02 PM biz.belcorp.ssicc.dao.IdiomaDAO
 */
package biz.belcorp.ssicc.dao;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="IdiomaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface IdiomaDAO extends DAO {

    /**
     * Obtiene un listado de todos los idiomas
     */
    public List getIdiomas(Idioma idioma);

    /**
     * Obtiene un listado de todos los idiomas en base a ciertos criterios los
     * cuales son enviados a través de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Idioma poblados
     */
    public List getIdiomasByCriteria(Map criteria);

    /**
     * Obtiene la informacion del idioma en base a su codigo. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param codigo
     *            el codigo del idioma
     * @return idioma objeto idioma poblado
     */
    public Idioma getIdioma(final String codigo);

    /**
     * Obtiene la informacion del idioma en base a su codigo ISO3166. La
     * excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si
     * no es encontrada.
     * 
     * @param codigo
     *            el codigo ISO3166 del idioma
     * @return idioma objeto idioma poblado
     */
    public Idioma getIdiomaByCodigoISO(final String codigo);

    /**
     * Registra la informacin de un nuevo idioma
     * 
     * @param idioma
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo informacin del usuario invocador
     */
    public void insertIdioma(Idioma idioma, Usuario usuario);

    /**
     * Actualiza la informacion de un idioma
     * 
     * @param idioma
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo informacin del usuario invocador
     */
    public void updateIdioma(Idioma idioma, Usuario usuario);

    /**
     * Elimina un idioma de la base de datos en base a su codigo
     * 
     * @param codigo
     *            el codigo del idioma
     */
    public void removeIdioma(final String codigo);

    /**
     * Obtiene el valor del siguiente PK para el idioma que se va a insertar en la
     * base de datos, los parametros enviados son usados para calcular el valor
     * de la llave primaria.
     * 
     * @param params
     * @return El nuevo codigo a asignar
     */
    public String getNextPK(Map params);
}
