/*
 * Created on 23/05/2006 10:09:29 AM
 * biz.belcorp.ssicc.scdf.service.impl.ControlImpresionStickersServiceImpl
 */
package biz.belcorp.ssicc.service.scdf.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ControlImpresionStickersDAO;
import biz.belcorp.ssicc.dao.scdf.StickerDAO;
import biz.belcorp.ssicc.dao.scdf.model.ControlImpresionStickers;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.ControlImpresionStickersService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlImpresionStickersServiceImpl.java.html"> <i>View Source </i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 * @deprecated
 */
@Service("scdf.controlImpresionStickersService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ControlImpresionStickersServiceImpl extends BaseService implements
        ControlImpresionStickersService {

    protected final String SYSTEM_LINE_SEPARATOR = System
            .getProperty("line.separator");

    @Resource(name="scdf.controlImpresionStickersDAO")
    private ControlImpresionStickersDAO controlImpresionStickersDAO;

    @Resource(name="scdf.stickerDAO")
    private StickerDAO stickerDAO;

    
    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.ControlImpresionStickersService#getControlImpresionStickers(java.lang.String)
     */
    public ControlImpresionStickers getControlImpresionStickers(
            String codigoPais) {
        return controlImpresionStickersDAO
                .getControlImpresionStickers(codigoPais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.ControlImpresionStickersService#insertControlImpresionStickers(biz.belcorp.ssicc.scdf.model.ControlImpresionStickers,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertControlImpresionStickers(
            ControlImpresionStickers controlImpresionStickers, Usuario usuario) {
        controlImpresionStickersDAO.insertControlImpresionStickers(
                controlImpresionStickers, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.ControlImpresionStickersService#updateControlImpresionStickers(biz.belcorp.ssicc.scdf.model.ControlImpresionStickers,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updateControlImpresionStickers(
            ControlImpresionStickers controlImpresionStickers, Usuario usuario) {
        controlImpresionStickersDAO.updateControlImpresionStickers(
                controlImpresionStickers, usuario);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.ControlImpresionStickersService#executeImpresionStickers(java.lang.String)
     */
    public void executeImpresionStickers(String codigoPais) {
        // Primero obtenemos los parametros de configuracion de impresion
        ControlImpresionStickers control = controlImpresionStickersDAO
                .getControlImpresionStickers(codigoPais);

        // Al mismo tiempo obtenemos la relacion de stickers a imprimir
        List stickers = stickerDAO.getStickersMapByPais(codigoPais);

        // La relacin de stickers esta ordenada por consultora, por tanto
        // usamos dicho campo para adicionar un sticker en blanco entre grupo
        // de stickers por consultora
        String codigoAnterior = "";

        // Creamos el archivo
        File directorioImpresion = new File(control.getDirectorioArchivo());
        File archivoImpresion = new File(directorioImpresion, control
                .getNombreArchivo());
        FileWriter fw = null;
        String plantillaImpresion = control.getPlantillaSticker();
        String plantillaSeparador = control.getPlantillaSeparador();

        try {
            // Creamos el archivo fisico
            fw = new FileWriter(archivoImpresion);

            // Iteramos por cada uno de los stickers
            for (int i = 0; i < stickers.size(); i++) {
                // Obtenemos la informacin del sticker
                Map sticker = (Map) stickers.get(i);

                // Obtenemos los nombres con los cuales se han
                // almacenado los valores del sticker en el map
                Iterator keys = sticker.keySet().iterator();

                // Creamos el buffer que contendr la informacion
                StringBuffer buffer = new StringBuffer(plantillaImpresion)
                        .append(SYSTEM_LINE_SEPARATOR);
                String resultadoPlantilla = buffer.toString();
                while (keys.hasNext()) {
                    // Reemplazamos cada variable por su respectivo valor
                    // tomando como nombre de variable el formato del tipo
                    // $NOMBRE_VARIABLE$
                    String key = (String) keys.next();
                    resultadoPlantilla = StringUtils.replace(
                            resultadoPlantilla, "$" + key + "$", MapUtils
                                    .getString(sticker, key));
                }

                // Inicializamos el codigoAnterior con el codigo de consultora
                // del primer sticker
                if (i == 0) {
                    codigoAnterior = MapUtils.getString(sticker,
                            "codigoConsultora");
                }
                // Si el codigo de consultora del sticker actual no es igual
                // al codigo anterior
                if (!StringUtils.equals(MapUtils.getString(sticker,
                        "codigoConsultora"), codigoAnterior)
                        && control.getIndicadorSeparador().booleanValue()) {
                    fw.write(plantillaSeparador + SYSTEM_LINE_SEPARATOR);
                    codigoAnterior = MapUtils.getString(sticker,
                            "codigoConsultora");
                }

                // Escribimos el buffer modificado en el archivo
                fw.write(resultadoPlantilla);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ignore) {
                }
            }
        }
    }

}
