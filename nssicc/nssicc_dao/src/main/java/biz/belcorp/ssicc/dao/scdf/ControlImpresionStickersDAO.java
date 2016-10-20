/*
 * Created on 22/05/2006 06:46:40 PM
 * biz.belcorp.ssicc.scdf.dao.ControlImpresionStickersDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.ControlImpresionStickers;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlImpresionStickersDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ControlImpresionStickersDAO {

    /**
     * Obtiene la informacion del control de impresion en base a su Primary Key (Codigo 
     * del Pais). La excepcion ObjectRetrievalFailureException Runtime Exception es
     * lanzada si no es encontrada.
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario
     * @return Objeto ControlImpresionStickers poblado
     */
    public ControlImpresionStickers getControlImpresionStickers(String codigoPais);

    /**
     * Registra la información de un nuevo ControlImpresionStickers
     * 
     * @param control
     *            el objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertControlImpresionStickers(ControlImpresionStickers control,
            Usuario usuario);

    /**
     * Actualiza la informacion de un ControlImpresionStickers
     * 
     * @param control
     *            el objeto a ser actualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateControlImpresionStickers(ControlImpresionStickers control,
            Usuario usuario);

    /**
     * Elimina un ControlImpresionStickers.
     * 
     * @param control
     *            el objeto a ser borrado
     * 
     */
    public void removeControlImpresionStickers(ControlImpresionStickers control,
            Usuario usuario);

}
