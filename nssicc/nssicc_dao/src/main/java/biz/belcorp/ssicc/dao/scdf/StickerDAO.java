/*
 * Created on 02/08/2005 06:34:26 PM biz.belcorp.ssicc.dao.StickerDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Sticker;

/**
 * TODO Include class description here.
 * <p>
 * <a href="StickerDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface StickerDAO extends DAO {

    /**
     * Obtiene una Lista de Stickers los cuales estan filtrados por el Pais.
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario invocador
     * @return Lista de Objetos Stickers debidamente poblados
     */
    public List getStickersByPais(String codigoPais);

    /**
     * Obtiene una Lista de Maps conteniendo la informacion Stickers los cuales
     * estan filtrados por el Pais.
     * 
     * @param codigoPais
     * @return Lista de Objetos Map debidamente poblados
     */
    public List getStickersMapByPais(String codigoPais);

    /**
     * Obtiene una Lista de Stickers los cuales estan filtrados por un Mapa de
     * criterios.
     * 
     * @param criteria
     *            Map conteniendo parametros que se utilizaran como filtros
     * @return Lista de objetos stickers poblados.
     */
    public List getStickersByCriteria(Map criteria);

    /**
     * Obtiene la informacion de los stickers en base a la tabla de Historicos
     * filtrado por el codigo de Pais
     * 
     * @param codigoPais
     *            Codigo de Pais del usuario invocador.
     * @return Lista de Stickers de la Tabla de Historicos
     */
    public List getHistoricoStickersByPais(String codigoPais);

    /**
     * Obtiene la informacion de los stickers, almacenados en Maps, en base a la
     * tabla de Historicos filtrado por el codigo de Pais
     * 
     * @param codigoPais
     *            Codigo de Pais del usuario invocador.
     * @return Lista de Maps con Informacion de Stickers de la Tabla de
     *         Historicos
     */
    public List getHistoricoStickersMapByPais(String codigoPais);

    /**
     * Registra la información de un nuevo sticker
     * 
     * @param sticker
     *            El objeto a ser insertado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void insertSticker(Sticker sticker, Usuario usuario);

    /**
     * Actualiza la informacion de un sticker
     * 
     * @param sticker
     *            el objeto a ser actualizado
     * @param usuario
     *            objeto conteniendo información del usuario invocador
     */
    public void updateSticker(Sticker sticker, Usuario usuario);

    /**
     * Actualiza el Status del Historico de Stickers en base al Codigo del Pais
     * 
     * @param codigoPais
     *            Codigo del pais del usuario invocador
     */
    public void updateHistoricoStickerStatusByPais(String codigoPais);

    /**
     * Elimina sticker(s) de la base de datos en base al objeto sticker
     * 
     * @param sticker
     *            Sticker con algunos parametros poblados que serviran como
     *            filtro.
     */
    public void removeSticker(Sticker sticker, Usuario usuario);

    /**
     * Ejecuta el Stored Procedure PRI_PR_GENER_STICK, el cual realiza la
     * generacion de Stickers en base a los Productos y las Consultoras.
     * 
     * @param pais
     *            Codigo de pais del usuario invocador
     * @param usuario
     *            Usuario del Sistem
     * @return Numero de Stickers procesados.
     */
    public int executeGeneraStickers(Pais pais, Usuario usuario);

    /**
     * Obtiene informacion referente a los Estados de los Stickers por Pais de
     * procesamiento
     * 
     * @param codigoPais
     *            Codigo de pais del usuario invocador
     * @return Lista de objetos Stickers con ciertas estadisticas.
     */
    public List getEstadosStickersByPais(String codigoPais);

    /**
     * Actualiza el correlativo de stickers almacenado en la tabla de parametros
     * de control.
     * 
     * @param codigoPais Codigo del pais de los stickers
     */
    public void updateCorrelativoStickers(String codigoPais);

}
