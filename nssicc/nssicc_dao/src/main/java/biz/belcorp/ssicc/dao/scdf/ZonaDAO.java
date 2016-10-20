/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.AccesoDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Zona;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ZonaDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ZonaDAO extends DAO {

    /**
     * Obtiene una Zona previamente seleccionada por tener a un Objeto Zona con
     * sus valores.
     * 
     * @param zona
     *            Objeto Zona que sirve como filtro
     * @return Objeto Zona debidamente poblado.
     */
    public Zona getZona(Zona zona);

    /**
     * Obtiene una Lista de Maps de Zonas identificados por Pais.
     * 
     * @param codigoPais
     *            codigo de Pais
     * @return Lista de Objetos Map poblados.
     */
    public List getZonaMapByPais(String codigoPais);

    /**
     * Registra la información de una nueva zona
     * 
     * @param zona
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertZona(Zona zona, Usuario usuario);

    /**
     * Actualiza la informacion de una zona
     * 
     * @param zona
     *            el objeto a ser acualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateZona(Zona zona, Usuario usuario);

    /**
     * Elimina un zona de la base de datos en base a su primaryKey
     * 
     * @param zona
     *            Objeto que posee parametros para filtrar una o mas Zonas.
     */
    public void removeZona(Zona zona);

}
