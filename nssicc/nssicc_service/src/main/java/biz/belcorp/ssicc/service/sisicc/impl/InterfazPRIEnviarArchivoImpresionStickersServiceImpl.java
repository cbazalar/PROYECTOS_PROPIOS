/*
 * Created on 26/09/2006 05:37:46 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarDocumentosAnuladosServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scdf.ControlImpresionStickersDAO;
import biz.belcorp.ssicc.dao.scdf.StickerDAO;
import biz.belcorp.ssicc.dao.scdf.model.ControlImpresionStickers;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPRIEnviarArchivoImpresionStickersServiceImpl"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazPRIEnviarArchivoImpresionStickersService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRIEnviarArchivoImpresionStickersServiceImpl extends
		BaseInterfazSalidaAbstractService {

	protected final String SYSTEM_LINE_SEPARATOR = System
			.getProperty("line.separator");

	@Resource(name="scdf.controlImpresionStickersDAO")
	private ControlImpresionStickersDAO controlImpresionStickersDAO;

	@Resource(name="scdf.stickerDAO")
	private StickerDAO stickerDAO;
	


	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method");
		List list = null;
		try {
			String codigoPais = (String)queryParams.get("codigoPais");
			list = this.stickerDAO.getStickersMapByPais(codigoPais);
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return list;
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#writeData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File, java.util.List)
     */
    protected int writeData(InterfazParams interfazParams, File tempFile, List data) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'writeData' method");
        int registrosProcesados = 0;
        OutputStreamWriter outputStream = getTempFileOutputStream(tempFile);
        String codigoPais = (String)interfazParams.getQueryParams().get("codigoPais");
        //La relacin de stickers esta ordenada por consultora, por tanto
        // usamos dicho campo para adicionar un sticker en blanco entre grupo
        // de stickers por consultora
        String codigoAnterior = "";
        try {
             // Primero obtenemos los parametros de configuracion de impresion
            log.debug("codigoPais:"+codigoPais);
            ControlImpresionStickers control = controlImpresionStickersDAO
                    .getControlImpresionStickers(codigoPais);
            String plantillaImpresion = control.getPlantillaSticker();
            String plantillaSeparador = control.getPlantillaSeparador();
            
            //Iteramos por cada uno de los stickers
            for (int i = 0; i < data.size(); i++) {
                // Obtenemos la informacin del sticker
                Map sticker = (Map) data.get(i);
                
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
                    outputStream.write(plantillaSeparador + SYSTEM_LINE_SEPARATOR);
                    codigoAnterior = MapUtils.getString(sticker,
                            "codigoConsultora");
                }

                // Imprimimos el sticker la cantidad de veces indicada por el
                // atributo "numero de impresiones"
                int numeroImpresiones = MapUtils.getIntValue(sticker,
                        "numeroImpresiones", 0);
                for (int j = 0; j < numeroImpresiones; j++) {
                    // Escribimos el buffer modificado en el archivo
                    outputStream.write(resultadoPlantilla);
                }

            }
        } catch (Exception e) {
            log.error("Error al escribir el archivo: " + e.getMessage());
            InterfazException ie = new InterfazException(
                    Constants.INTERFAZSICC_ERROR_GENERAR_ARCHIVO);
            ie.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
            throw ie;
        } finally {
            try {
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                log.error("Error al cerrar el archivo: " + e.getMessage());
                InterfazException ie = new InterfazException(
                        Constants.INTERFAZSICC_ERROR_GENERAR_ARCHIVO);
                ie.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
                throw ie;
            }
        }
        log.info("Se termino de escribir sobre el archivo");
        return registrosProcesados;
    }

}
