/*
 * Created on 07/11/2005 11:21:13 AM
 *
 * biz.belcorp.ssicc.dao.TarjetaDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Tarjeta;
import biz.belcorp.ssicc.dao.scdf.model.TarjetaPK;

/**
 * TODO Include class description here.
 * <p>
 * <a href="TarjetaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface TarjetaDAO extends DAO {

    /**
     * Obtiene un listado las tarjetas segun las condiciones que se encuentre en
     * el parametro Tarjeta.
     */
    public List getTarjetas(Tarjeta tarjeta);

    /**
     * Obtiene un listado de todos las tarjetas en base al Codigo del Pais
     * 
     * @param codigoPais
     *            Codigo del pais
     * @return Lista de objetos Tarjeta poblados
     */
    public List getTarjetasByPais(String codigoPais);

    /**
     * Obtiene un listado de todos las tarjetas en base al Codigo del Pais
     * 
     * @param codigoPais
     *            Codigo del pais
     * @return Lista de objetos Tarjeta poblados
     */
    public List getTarjetasMapByPais(String codigoPais);

    /**
     * Obtiene un listado de todos las tarjetas en base a ciertos criterios los
     * cuales son enviados a través de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Tarjeta poblados
     */
    public List getTarjetasByCriteria(Map criteria);

    /**
     * Obtiene la informacion de la tarjeta en base a su primaryKey. La
     * excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si
     * no es encontrada.
     * 
     * @param primaryKey
     *            la primaryKey de la tarjeta
     * @return tarjeta objeto tarjeta poblado
     */
    public Tarjeta getTarjeta(final TarjetaPK primaryKey);

    /**
     * Registra la información de un nuevo tarjeta
     * 
     * @param tarjeta
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertTarjeta(Tarjeta tarjeta, Usuario usuario);

    /**
     * Actualiza la informacion de una tarjeta
     * 
     * @param tarjeta
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateTarjeta(Tarjeta tarjeta, Usuario usuario);

    /**
     * Actualiza el Status de la Tarjeta en base al Codigo del Pais
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario Invocador
     */
    public void updatePeriodoTarjetaByPais(String codigoPais);

    /**
     * Actualiza el Status de la Tarjeta en base al Codigo del Pais
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario Invocador
     */
    public void updateTarjetaStatusByPais(String codigoPais);

    /**
     * Elimina una tarjeta de la base de datos en base a su codigo
     * 
     * @param codigo
     *            el codigo del tarjeta
     */
    public void removeTarjeta(Tarjeta tarjeta);

    /**
     * Elimina todas las tarjetas que pertenezcan a un determinado Pais
     * 
     * @param codigoPais
     *            Codigo del Pais del usuario invocador
     */
    public void removeTarjetaByPais(String codigoPais);

}