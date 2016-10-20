/*
 * Created on 09/11/2005 06:04:32 PM
 *
 * biz.belcorp.ssicc.scdf.service.StickerService
 */
package biz.belcorp.ssicc.service.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="StickerService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface StickerService extends Service {

    /**
     * Genera los stickers para las ordenes de pedido que contengan productos de
     * tratamiento facial, haciendo el calculo de los puntos en base al factor y
     * al precio unitario del producto o tomando el valor del puntaje de la
     * matriz en caso sea un producto con puntaje fijo.
     * 
     * @param pais
     *            Informacion del Pais del Usuario.
     * @param usuario
     *            Usuario que ejecuta el proceso.
     */
    public int executeGeneracionStickers(Pais pais, Usuario usuario);

    /**
     * Realiza la Reimpresion de Stickers segun determinados criterios y rangos.
     * Entre estos criterios encontramos: Stickers, Campaas, Consultoras,
     * Fechas, Productos y Facturas
     * 
     * @param criteria
     *            Objeto Map que contiene los criterios de acuerdo a los checks
     *            seleccionados.
     * @return Lista de objetos Stickers debidamente poblados.
     */
    public List executeReimpresionStickers(Map criteria);

    /**
     * Obtiene una relacion de consultoras, mostrando el rango maximo y minimo
     * de Stickers generados.
     * 
     * @param pais
     *            Informacion del Pais del Usuario invocador.
     * @return Lista que contienen Informacion de Stickers agrupados por
     *         Consultora.
     */
    public List selectEstadosStickers(Pais pais);

    /**
     * Actualiza el status que determina si un sticker debe ser enviado o no a Privilege
     * @param codigoPais codigo del pais
     */
    public void updateHistoricoStickerStatusByPais(String codigoPais);

    /**
     * Actualiza el correlativo de stickers de la tabla de parametros de control
     * con el valor del ultimo sticker generado en la tabla temporal del
     * proceso.
     * 
     * @param codigoPais
     *            Codigo de pais
     */
    public void updateCorrelativoStickers(String codigoPais);

}
