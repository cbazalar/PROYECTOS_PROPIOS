/*
 * Created on 16/11/2005 03:37:11 PM biz.belcorp.ssicc.dao.PerfilDAO
 */
package biz.belcorp.ssicc.dao;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Perfil;
import biz.belcorp.ssicc.dao.model.PerfilPK;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PerfilDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramirez </a>
 */
public interface PerfilDAO extends DAO {

    /**
     * Obtiene la informacion del perfil en base a su primaryKey. La excepcion
     * ObjectRetrievalFailureException Runtime Exception es lanzada si no es
     * encontrada.
     * 
     * @param primaryKey
     *            el primaryKey del perfil
     * @return perfil objeto perfil poblado
     */
    public Perfil getPerfil(final PerfilPK primaryKey);

    /**
     * Registra la información de un nuevo perfil
     * 
     * @param perfil
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo informacióndel usuario invocador
     */
    public void insertPerfil(Perfil perfil, Usuario usuario);

    /**
     * Actualiza la informacion de un perfil
     * 
     * @param perfil
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updatePerfil(Perfil perfil, Usuario usuario);

    /**
     * Elimina un perfil de la base de datos en base a su primaryKey
     * 
     * @param primaryKey
     *            el primaryKey del perfil
     */
    public void removePerfil(final PerfilPK primaryKey);

    /**
     * Obtiene una lista con los objetos Perfil con los cuales está relacionado
     * un rol determinado
     * 
     * @return
     */
    public List getPerfilesByUsuario(final String codigoUsuario, final String codigoIdioma);

}
