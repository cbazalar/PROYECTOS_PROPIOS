/*
 * Created on 10/11/2005 11:20:54 AM biz.belcorp.ssicc.dao.ConsultoraDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Consultora;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ConsultoraDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ConsultoraDAO extends DAO {

    /**
     * Obtiene un listado de todos las consultoras en base a ciertos criterios.
     * 
     * @param criteria
     *            objeto Consultora cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Consultora poblados
     */
    public List getConsultoras(Consultora consultora);

    /**
     * Obtiene un listado de todos las consultoras en base a ciertos criterios
     * los cuales son enviados a través de un Map.
     * 
     * @param criteria
     *            objeto Map cuyos atributos son usados como criterios de
     *            búsqueda
     * @return Lista de objetos Consultora poblados
     */
    public List getConsultorasMapByPais(String codigoPais);

    /**
     * Registra la información de un nuevo cliente
     * 
     * @param consultora
     *            la consultora a ser insertada
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertConsultora(Consultora consultora, Usuario usuario);

    /**
     * Actualiza la informacion de una consultora
     * 
     * @param consultora
     *            la consultora a ser actualizada
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateConsultora(Consultora consultora, Usuario usuario);

    /**
     * Actualiza el status de una consultora en base al Pais de origen.
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario
     */
    public void updateConsultoraStatusByPais(String codigoPais);

    /**
     * Elimina las consultora que coincidan con el criterio de busqueda.
     * 
     * @param consultora
     *            Informacion de la consultora para eliminar.
     */
    public void removeConsultora(Consultora consultora);

}