/*
 * Created on 23/05/2006 09:19:18 AM
 * biz.belcorp.ssicc.scdf.service.ControlImpresionStickers
 */
package biz.belcorp.ssicc.service.scdf;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.ControlImpresionStickers;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlImpresionStickers.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface ControlImpresionStickersService extends Service {

   
    /**
     * Obtiene la informacin del control de facturacin de un determinado pas.
     * 
     * @param codigoPais
     *            Valor del cdigo del pas.
     * @return el objeto ControlImpresionStickers del pais correspondiente.
     */
    public ControlImpresionStickers getControlImpresionStickers(
            String codigoPais);

    /**
     * Ingresa un nuevo registro de control de facturacin.
     * 
     * @param controlImpresionStickers
     *            el objeto a ser insertado
     * @param usuario
     *            el usuario que registra la informacin
     */
    public void insertControlImpresionStickers(
            ControlImpresionStickers controlImpresionStickers, Usuario usuario);

    /**
     * Actualiza la informacin del control de facturacin en el sistema.
     * 
     * @param controlImpresionStickers
     *            el objeto a ser actualizado
     * @param usuario
     *            el usuario que actualiza la informacin
     */
    public void updateControlImpresionStickers(
            ControlImpresionStickers controlImpresionStickers, Usuario usuario);

    /**
     * Genera el archivo de impresion de stickers de acuerdo a los parametros de
     * configuracin del pais.
     * 
     * @param codigoPais
     *            Valor del cdigo del pas.
     */
    public void executeImpresionStickers(String codigoPais);

}
