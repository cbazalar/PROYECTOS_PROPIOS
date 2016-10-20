/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.AccesoDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Subgerencia;

/**
 * TODO Include class description here.
 * <p>
 * <a href="SubgerenciaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface SubgerenciaDAO extends DAO {

    /**
     * Obtenemos informacion referente a una Subgerencia, pasandole la
     * informacion referente a su clave primaria
     * 
     * @param subgerencia
     *            Subgerencia con los valores de su Primary Key.
     * @return Objeto Subgerencia debidamente poblado.
     */
    public Subgerencia getSubgerencia(Subgerencia subgerencia);

    /**
     * Obtenemos Lista de Objetos Map que sean del Pais seleccionado.
     * 
     * @param codigoPais
     *            codigo del Pais
     * @return Lista de objetos Map relacionados Subgerencia.
     */
    public List getSubgerenciaMapByPais(String codigoPais);

    /**
     * Registra la información de un nuevo subgerencia
     * 
     * @param subgerencia
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertSubgerencia(Subgerencia subgerencia, Usuario usuario);

    /**
     * Actualiza la informacion de un subgerencia
     * 
     * @param subgerencia
     *            el objeto a ser actualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateSubgerencia(Subgerencia subgerencia, Usuario usuario);

    /**
     * Elimina un subgerencia de la base de datos en base a su primaryKey
     * 
     * @param subgerencia
     *            Elimina aquellas Subgerencias que coincidan con los parametros
     *            del objeto Subgerencia.
     */
    public void removeSubgerencia(Subgerencia subgerencia);

}
