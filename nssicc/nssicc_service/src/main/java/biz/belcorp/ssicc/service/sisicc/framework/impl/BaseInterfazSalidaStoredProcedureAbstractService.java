package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.ZipUtil;

/* POR REESTRUCTURAR NSSICC */
/**
 * Clase service abstracta para las Interfaces Salida SiCC cuya logica se
 * ejecutara desde un Stored Procedure. Este tipo de interfaces generalmente
 * serán utilizadas cuando se generen gran cantidad de registros. Toda la logica
 * se ejecutara en el Stored Procedure, esta clase implementa la funcionalidad
 * de traer el archivo temporal desde el servidor, descomprimirlo, leerlo y
 * formatearlo. Las clases que heredan de esta deberan sobrescribir el metodo
 * 'executeStoreProcedure' para realizar la llamada al Stored Procedure.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */

@Service("sisicc.baseInterfazSalidaStoredProcedureAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseInterfazSalidaStoredProcedureAbstractService extends
		BaseInterfazSalidaAbstractService {
	private static final char SEPARATOR = ';';

	/**
	 * Sobrescribir este metodo y realizar la llamada al Stored Procedure
	 * correspondiente.
	 * 
	 * @param params
	 *            Map con parametros de la ejecucion del Stored Procedure
	 */
	protected abstract void executeStoreProcedure(Map params);

	/**
	 * Se agrega el nombreArchivo al Map (requerido para la generacion del
	 * archivo por el Stored Procedure)
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 */
	protected Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
			Map queryParams = super.prepareQueryParams(interfazParams);
		queryParams.put("nombreArchivo", interfazParams.getTempName());
		return queryParams;
	}

	/**
	 * Delega la lectura de la data a la ejecucion del Store Procedure.
	 * 
	 * @param params
	 *            Parametros de ejecucion del Store Procedure
	 * @return siempre devuelve null, ya no se genera la lista
	 */
	protected final List readData(Map params) throws InterfazException {
		log
				.info("No se realiza el query, la data se genera desde el Stored Procedure");
		log.info("Ejecutando Stored Procedure ... " + new Date());
		executeStoreProcedure(params);
		log.info("Ejecutando terminada ... " + new Date());
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#writeData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams, java.io.File, java.util.List)
	 */
	protected final int writeData(InterfazParams interfazParams, File tempFile,
			List data) throws InterfazException {
		log.debug("Entering 'writeData' method");
		int registrosProcesados = 0;
		
		InterfazFormat interfazFormat = interfazFormatServiceFacade.getInterfazFormat(interfazParams.getInterfaz());
		Map queryParams = interfazParams.getQueryParams();
		String tituloCabeceraInterfaz = (String) queryParams.get("tituloCabeceraInterfaz");
		interfazFormat.setTituloCabeceraInterfaz(tituloCabeceraInterfaz);
		
		boolean archivoBinario = interfazFormat.esArchivoTipoBinario();
		boolean archivoXML = interfazFormat.esArchivoTipoXML();
		
		log.debug("Se obtuvo valor ArchivoBinario: "+ archivoBinario);
		log.debug("Se obtuvo valor getExtensionArchivo: "+ interfazFormat.getInterfaz().getExtensionArchivoDescripcion());
		log.debug("Se obtuvo el InterfazFormat");
		
		/* INI CB ECU-SiCC-2012-0191 */
		Interfaz interfaz = interfazParams.getInterfaz();
		/* FIN CB ECU-SiCC-2012-0191 */
		
		OutputStreamWriter outputStream = null;
		FileOutputStream fileOutputStream = null;
		if (archivoBinario) {			
			//Creando archivo binario
			fileOutputStream = getTempFileOutputStreamBinario(tempFile);
			log.debug("Se creo archivo fileOutputStream");
			//Creando objeto binario
			try  {
				interfazFormat.setExcelUtil(new ExcelUtil(fileOutputStream));
				log.debug("Se creo object ExcelUtil");
			}	
			catch (Exception e) {
				InterfazException ie = new InterfazException(e.getMessage());
				throw ie;
			}
		}	
		else {	
			/* INI CB ECU-SiCC-2012-0191 */
			String valorEncoding = interfaz.getValorEncodingSalida();
			if (StringUtils.isBlank(valorEncoding))
			outputStream = getTempFileOutputStream(tempFile);
			else
				outputStream = getTempFileOutputStream(tempFile, valorEncoding);
			/* FIN CB ECU-SiCC-2012-0191 */
		}	
			
		File inputFile = getFile(interfazParams);
		LineIterator lineIterator = null;
		String inputLine = null;
		log.debug("Procesando el archivo de proceso temporal... ");
		
		short registrosExcel = 1;
		
		/* Verificando si tiene Titulo Cabecera */
		if (StringUtils.isNotBlank(interfazFormat.getTituloCabeceraInterfaz())) {
			String[] titulo = StringUtils.splitPreserveAllTokens(interfazFormat.getTituloCabeceraInterfaz(),	SEPARATOR);
			try {
				Iterator iterator = interfazFormat.getEstructurasArchivo().iterator();
				Map rowMapTitulo = new HashMap();
				for (int i = 0; i < titulo.length; i++) {
					EstructuraArchivo estructuraArchivo = (EstructuraArchivo) iterator.next();
					rowMapTitulo.put(estructuraArchivo.getIdentificadorCampo(), titulo[i]);
				}
				
				String line = interfazFormatterService.formatTituloCabecera(rowMapTitulo,interfazFormat);
				if (!archivoBinario) {
                    // Escribimos la linea en el archivo
	  			    outputStream.write(line);
				}
				else {
					interfazFormat.getExcelUtil().setRegistro(registrosExcel);					
					registrosExcel++;
				}
			} catch (Exception e) {
				log.error("Error al escribir el archivo temporal.");
	            log.error("Numero de linea: " + registrosProcesados);
	            log.error("Valor de la linea: " + inputLine);
	            log.error("Excepcion: " + e.getMessage(), e);
	            
	            String message = e.getMessage();
	            if(e instanceof ArrayIndexOutOfBoundsException)
	            	message = "Exception ArrayIndexOutOfBoundsException, Numero de linea : " + registrosProcesados +
	            				", Valor de la linea: " + inputLine;
	            
				LineIterator.closeQuietly(lineIterator);
				if (archivoBinario) 
					closeQuietly(interfazFormat);	
				else
				    closeQuietly(outputStream);
				InterfazException ie = new InterfazException(message);
				ie.setRegistrosProcesados(registrosProcesados);
				throw ie;	
			}	
				
		}
		
		/* Iterando el archivo */
		try {
			lineIterator = FileUtils.lineIterator(inputFile, null);

			// La primera fila del archivo tiene la informacion de la cabecera
			inputLine = lineIterator.nextLine();
			String[] header = StringUtils.splitPreserveAllTokens(inputLine,	SEPARATOR);
			log.info("Iniciando formateo ... " + new Date());
			if (archivoXML) {
				outputStream.write(Constants.STRING_ENCABEZADO_XML+interfazFormat.getInterfaz().getSeparadorLinea());
				String result = Constants.INI_TAG_XML + interfazFormat.getInterfaz().getNombreEtiquetaPrincipalXML() + ">" + interfazFormat.getInterfaz().getSeparadorLinea();
				outputStream.write(result);
			}
			
			while (lineIterator.hasNext()) {
				inputLine = lineIterator.nextLine();
                registrosProcesados++;
                // Validamos que la linea leida sea diferente de null
                if(inputLine == null) {
                    InterfazException ie = new InterfazException(
                            "Error al leer el archivo a formatear, la lInea a procesar es null.");
                    ie.setRegistrosProcesados(registrosProcesados);
                    throw ie;
                }
                // Formateamos la linea leída
                Map rowMap = new HashMap();
				String[] row = StringUtils.splitPreserveAllTokens(inputLine, SEPARATOR);
				for (int i = 0; i < row.length; i++) {
					rowMap.put(header[i], row[i]);
				}
				String line = interfazFormatterService.formatMap(rowMap,interfazFormat);
				if (!archivoBinario) {
                    // Escribimos la linea en el archivo
	  			    outputStream.write(line);
				}
				else {
					interfazFormat.getExcelUtil().setRegistro(registrosExcel);					
					registrosExcel++;
				}
			}
			/*SI EL ARCHIVO GENERADO ESTA FORMATO WINDOWS SE AGREGA FIN DE ARCHIVO*/
			/*if (!archivoBinario && registrosProcesados==0){
				String tipoSeparadorLinea = interfazParams.getInterfaz().getTipoSeparadorLinea();
				if (StringUtils.equals(tipoSeparadorLinea,
	                    Constants.TIPO_SEPARADOR_LINEA_WINDOWS)) {
	                String line = Constants.SEPARADOR_LINEA_WINDOWS;
	                outputStream.write(line);
	            } 
			}*/
			if (!archivoBinario && registrosProcesados==0){
				String tipoSeparadorLinea = interfazParams.getInterfaz().getTipoSeparadorLinea();
				if (StringUtils.equals(interfazParams.getInterfaz()
						.getAddSepLineaArchivoVacio(), Constants.SI)) {
	                String line = interfazParams.getInterfaz().getSeparadorLinea();
	                outputStream.write(line);
	            } 
			}
			// Agrego la validacion de archivo vacio
			if (StringUtils.equals(interfazParams.getInterfaz()
					.getFlagArchivoVacio(), Constants.NO)
					&& (registrosProcesados == 0))
				throw new InterfazException(
						"No se encontraron registros, no se genero el archivo.");

			//Se agrega en el atributo queryParams el campo registrosProcesados
			interfazParams.getQueryParams().put("QP_registrosProcesados", new Integer(registrosProcesados));
			
			log.info("Formateo terminado ... " + new Date());
		} catch (InterfazException e) {
            LineIterator.closeQuietly(lineIterator);
            closeQuietly(outputStream);
			throw e;
		} catch (Exception e) {
			log.error("Error al escribir el archivo temporal.");
            log.error("Numero de linea: " + registrosProcesados);
            log.error("Valor de la linea: " + inputLine);
            log.error("Excepcion: " + e.getMessage(), e);
            
            String message = e.getMessage();
            if(e instanceof ArrayIndexOutOfBoundsException)
            	message = "Exception ArrayIndexOutOfBoundsException: Numero de linea : " + registrosProcesados +
            				", Valor de la linea: " + inputLine;	
            
			LineIterator.closeQuietly(lineIterator);
			if (archivoBinario) 
				closeQuietly(interfazFormat);	
			else
			    closeQuietly(outputStream);
			InterfazException ie = new InterfazException(message);
			ie.setRegistrosProcesados(registrosProcesados);
			throw ie;
		} finally {
			// Cierro el iterador del archivo temporal
			LineIterator.closeQuietly(lineIterator);
			if (archivoBinario) 
				closeQuietly(interfazFormat);	
			else {
				if (archivoXML) {
					try {
						String result = Constants.INI_TAG_XML + "/" + interfazFormat.getInterfaz().getNombreEtiquetaPrincipalXML() + ">";
						outputStream.write(result);
					}
					catch (Exception e) {
						
					}
					
				}
			    closeQuietly(outputStream);
			}    
			FileUtil.deleteFile(inputFile);
		}
		return registrosProcesados;
	}

	/**
	 * Obtiene Archivo
	 * @param interfazParams
	 * @return
	 * @throws InterfazException
	 */
	private File getFile(InterfazParams interfazParams)
			throws InterfazException {
		File unzippedFile = null;
		String tempZipPath = interfazParams.getTempZipPath();
		try {
			// Obtengo el archivo temporal remoto generado por Oracle
            /*if (interfazParams.getInterfaz().getFlagEnvioArchivo().trim()
                    .equalsIgnoreCase(Constants.ENVIO_FTP)) {
                if(log.isDebugEnabled()) {
                    log.debug("Obteniendo el archivo generado por BD via FTP...");
                }
                FTPClientWrapper ftpWrapper = getFTPClientWrapper(interfazParams);
                ftpWrapper.retrieveFile(tempZipPath, interfazParams.getInterfaz()
                        .getDirectorioProc(), interfazParams.getTempZipFileName());
                // Elimino el archivo temporal remoto generado por Oracle
                ftpWrapper.deleteFile(interfazParams.getInterfaz()
                        .getDirectorioProc(), interfazParams.getTempZipFileName());
                ftpWrapper.disconnect();
            }
            else {*/
                if(log.isDebugEnabled()) {
                    log.debug("Copiando el archivo generado por BD al directorio de trabajo...");
                }
                File srcFile = new File(interfazParams.getInterfaz()
                        .getDirectorioProc(), interfazParams
                        .getTempZipFileName());
                File destFile = new File(interfazParams.getInterfaz()
                        .getDirectorioTemporal(), interfazParams
                        .getTempZipFileName());
                FileUtils.copyFile(srcFile, destFile);
                
                // Elimino el archivo temporal remoto generado por Oracle
                srcFile.delete();
            //}

			// Descomprimo el archivo
            //log.debug("Descomprimiendo el archivo en la carpeta temporal local...");
			File zippedFile = new File(tempZipPath);
			//log.debug("zippedFile=" + zippedFile.getAbsolutePath());

			unzippedFile = new File(ZipUtil.unzipFile(zippedFile
					.getAbsolutePath(), interfazParams.getTempDirectory()));
			log.debug("unzippedFile=" + unzippedFile.getAbsolutePath());

			// Elimino el archivo temporal local
			FileUtil.deleteFile(zippedFile);
		} catch (Exception e) {
            log.error(e.getMessage());
			throw new InterfazException("Error al recuperar el archivo ZIP: "
					+ interfazParams.getTempZipFileName());
		}
		return unzippedFile;
	}

	
}