/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.AccesoDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlFacturacionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ControlFacturacionDAO extends DAO {

    /**
     * Obtiene una relacion de objetos ControlFacturacion en base a los
     * criterios que son pasados a través de un map.
     * 
     * @param criteria
     * @return
     */
    public List getControlesFacturacionByCriteria(Map criteria);

    /**
     * Obtiene la informacion del acceso en base a su Primary Key (Codigo del
     * Pais). La excepcion ObjectRetrievalFailureException Runtime Exception es
     * lanzada si no es encontrada.
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario
     * @return Objeto ControlFacturacion poblado
     */
    public ControlFacturacion getControlFacturacion(String codigoPais);

    /**
     * Obtiene un objeto ControlFacturacion el cual lo almacena en una lista a
     * efectos de usarla para los metodos de escritura de archivos.
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario
     * @return Lista con el unico objeto ControlFacturacion resultante del Pais
     *         filtro.
     */
    public List getControlFacturacionMap(String codigoPais);

    /**
     * Registra la información de un nuevo ControlFacturacion
     * 
     * @param control
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertControlFacturacion(ControlFacturacion control,
            Usuario usuario);

    /**
     * Actualiza la informacion de un ControlFacturacion
     * 
     * @param control
     *            el objeto a ser actualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateControlFacturacion(ControlFacturacion control,
            Usuario usuario);

    /**
     * Elimina un ControlFacturacion.
     * 
     * @param control
     *            el objeto a ser borrado
     * 
     */
    public void removeControlFacturacion(ControlFacturacion control,
            Usuario usuario);

}
