/*
 * Created on 25/02/2005 11:21:13 AM biz.belcorp.ssicc.dao.PaisDAO
 */
package biz.belcorp.ssicc.dao;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PaisDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface PaisDAO extends DAO {

    /**
     * Obtiene un listado de todos los paises
     */
    public List getPaises(Pais pais);

    /**
     * Obtiene un listado de todos los paises en base a ciertos criterios los
     * cuales son enviados a través de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            bsqueda
     * @return Lista de objetos Pais poblados
     */
    public List getPaisesByCriteria(Map criteria);

    /**
     * Obtiene la informacion del pais en base a su codigo. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param codigo
     *            el codigo del pais
     * @return pais objeto pais poblado
     */
    public Pais getPais(final String codigo);

    /**
     * Obtiene la informacion del pais en base a su codigo ISO3166. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param codigo
     *            el codigo ISO3166 del pais
     * @return pais objeto pais poblado
     */
    public Pais getPaisByCodigoISO(final String codigo);

    /**
     * Registra la informacin de un nuevo pais
     * 
     * @param pais
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertPais(Pais pais, Usuario usuario);

    /**
     * Actualiza la informacion de un pais
     * 
     * @param pais
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updatePais(Pais pais, Usuario usuario);

    /**
     * Elimina un pais de la base de datos en base a su codigo
     * 
     * @param codigo
     *            el codigo del pais
     */
    public void removePais(final String codigo);
    
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