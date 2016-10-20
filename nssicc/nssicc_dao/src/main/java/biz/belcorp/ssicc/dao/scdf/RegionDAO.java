/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.AccesoDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Region;

/**
 * TODO Include class description here.
 * <p>
 * <a href="RegionDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface RegionDAO extends DAO {

    /**
     * Obtiene la informacion de la region en base a su Primary Key. La
     * excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si
     * no es encontrada.
     * 
     * @param region
     *            Region conteniendo informacion de su Primary Key
     * @return objeto region poblado.
     */
    public Region getRegion(Region region);

    /**
     * Lista de Map, que contienen informacion de Region identificados por el
     * Codigo del Pais
     * 
     * @param codigoPais
     *            Codigo de Pais del Usuario invocador
     * @return lista de objetos Map debidamente poblados.
     */
    public List getRegionMapByPais(String codigoPais);

    /**
     * Registra la información de una nueva region
     * 
     * @param region
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertRegion(Region region, Usuario usuario);

    /**
     * Actualiza la informacion de un region
     * 
     * @param region
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateRegion(Region region, Usuario usuario);

    /**
     * Elimina regiones en base a los valores del parametro.
     * 
     * @param region
     *            Objeto Region cuyos parametros sirven como filtro
     */
    public void removeRegion(Region region);

}
