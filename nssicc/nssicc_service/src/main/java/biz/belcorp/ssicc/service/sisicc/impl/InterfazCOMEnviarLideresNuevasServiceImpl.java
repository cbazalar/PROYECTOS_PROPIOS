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

import org.apache.commons.lang.CharUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOMEnviarLibretaAhorrosServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazCOMEnviarLideresNuevasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOMEnviarLideresNuevasServiceImpl extends
		BaseInterfazSalidaAbstractService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
	 */
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method");
		List list = null;
		try {
			list = this.interfazSiCCDAO.getInterfazCOMEnviarLideresNuevas(queryParams);
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return list;
	}

    /**
     * Se incluyo la implementacin exacta de la clase padre con la diferencia
     * que al final del archivo agrega el caracter de control requerido por el
     * periferico.
     */
    protected int writeData(InterfazParams interfazParams, File tempFile,
            List data) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'writeData' method");
        int registrosProcesados = 0;
        OutputStreamWriter outputStream = getTempFileOutputStream(tempFile);
        InterfazFormat interfazFormat = interfazFormatServiceFacade
                .getInterfazFormat(interfazParams.getInterfaz());
        log.info("Se obtuvo el InterfazFormat");

        try {
            // Intero sobre las filas del List y formateo las lineas
            Iterator iter = data.iterator();
            while (iter.hasNext()) {
                Object element = iter.next();
                if (!(element instanceof Map))
                    throw new InterfazException(
                            "Error al procesar la fila, se esperaba un Map,");
                Map row = (Map) element;
                String line = interfazFormatterService.formatMap(row,
                        interfazFormat);
                outputStream.write(line);
                registrosProcesados++;
            }
            
            // Escritura del caracter de control.
            outputStream.write(CharUtils.toString((char)26));
        } catch (InterfazException ie) {
            log.error("Error al escribir el archivo: " + ie.getMessage());
            ie.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
            throw ie;
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
