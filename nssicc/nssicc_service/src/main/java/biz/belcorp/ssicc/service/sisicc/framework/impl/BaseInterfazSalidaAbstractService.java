package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.SFTPClientWrapper;
import biz.belcorp.ssicc.service.util.ZipUtil;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase service abstracta para las Intefaces Salida SiCC. Implementa el
 * template method 'processInterfaz' para las Interfaces de Salida. Generalmente
 * las nuevas interfaces de salida requeriran solo de implementar el metodo
 * 'readData' que contiene la lógica particular de cada interfaz para obtener la
 * data. Contiene implementaciones por defecto para escribir y enviar el archivo
 * que produce la interfaz de salida.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * 
 */

@Service("sisicc.baseInterfazSalidaAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseInterfazSalidaAbstractService extends
		BaseInterfazAbstractService {

	
	/**
	 * Este metodo debe ser sobreescrito para las interfaces que contengan titulo
	 * de cabecera. Para ello debe colocarse un valor key que luego el framework
	 * buscará en el archivo properties para obtener el valor
	 * @return
	 * @throws InterfazException
	 */
	protected String adicionarKeyTituloCabecera() {
		return null;
	}
	
	
	/**
	 * Template Method que define el algoritmo basico para las Interfaces de
	 * Salida de SiCC: Lee la data dado los parametros del query, escribe la
	 * data formateandola en un archivo temporal, envia el archivo temporal.
	 * 
	 * @param parametros
	 *            de la Interfaz
	 * @return numero de registros procesados
	 */
	protected int processInterfaz(InterfazParams interfazParams)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'processInterfaz' method");
		int registrosProcesados = 0;
		File tempFile = null;
		try {
			log.info("Preparando los parametros del query...");
			log.info("Ejecutando el query... queryParams="
					+ interfazParams.getQueryParams());
			this.beforeReadData(interfazParams);	
			List data = readData(interfazParams.getQueryParams());
			if (data != null) {
				log.info("Query ejecutado, data.size=" + data.size());
				this.prepareDataBeforeWrite(interfazParams, data);
				this.validateNoData(interfazParams, data);
			}

			tempFile = this.createTempFile(interfazParams.getTempPath());
			log.info("Escribiendo la data en el archivo...");
			registrosProcesados = this.writeData(interfazParams, tempFile, data);
			log.info("Archivo creado, registrosProcesados="	+ registrosProcesados);
			interfazResult.setCompletadoInterfaz(true); // FRAMEWORK NSSICC
			
			File sendFile = this.prepareFileBeforeSend(interfazParams, tempFile);
			log.info("Enviando el archivo: " + sendFile.getAbsolutePath());
			this.sendFile(interfazParams, sendFile);
		} catch (InterfazException interfazException) {
			log.error("Error al procesar la interfaz: "
					+ interfazException.getMessage());
			Map params = interfazParams.getQueryParams();
			params.put("errorInterfaz", Constants.SI);			
			
            if(registrosProcesados != 0) {
                interfazException.setRegistrosProcesados(registrosProcesados);
            }
			throw interfazException;
		} catch (Exception e) {
			log.error("Error al procesar la interfaz: " + e.getMessage());
			Map params = interfazParams.getQueryParams();
			params.put("errorInterfaz", Constants.SI);			
			
			InterfazException interfazException = new InterfazException(e
					.getMessage());
			interfazException.setRegistrosProcesados(registrosProcesados);
			throw interfazException;
		}
		return registrosProcesados;
	}

	/**
	 * Advice antes de leer la data
	 * @param interfazParams
	 */
	protected void beforeReadData(InterfazParams interfazParams) {
		String keyTituloCabecera = this.adicionarKeyTituloCabecera();
		Usuario usuario = interfazParams.getUsuario();
		if (StringUtils.isNotBlank(keyTituloCabecera))	{
			String tituloCabecera = this.getKeyMessage(keyTituloCabecera, usuario);
			Map queryParams = interfazParams.getQueryParams();
			queryParams.put("tituloCabeceraInterfaz", tituloCabecera);
			interfazParams.setQueryParams(queryParams);
		}
	}

	/**
	 * Valida si es que no se devolvieron datos en el List.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param data
	 *            List con los datos de la interfaz
	 * @throws InterfazException
	 *             en caso de que no existan datos en el List y la interfaz no
	 *             genere archivos vacios.
	 */
	private void validateNoData(InterfazParams interfazParams, List data)
			throws InterfazException {
		if (StringUtils.equals(interfazParams.getInterfaz()
				.getFlagArchivoVacio(), Constants.NO)
				&& (data == null || data.size() == 0))
			throw new InterfazException(
					"No se encontraron registros, no se generó el archivo.");
	}

	/**
	 * Este metodo debe ser implementado por las clases Service concretas de las
	 * Interfaces de Salida utilizando el DAO correspondiente para recuperar la
	 * data.
	 * 
	 * @param params
	 *            Map con parametros del query.
	 * @return List con los resultados del query, se recomienda utilizar un Map
	 *         por element del List que representa una fila.
	 * @throws InterfazException
	 */
	protected abstract List readData(Map params) throws InterfazException;

	/**
	 * Crea el archivo temporal.
	 * 
	 * @param tempFileName
	 *            nombre del archivo temporal
	 * @return archivo temporal creado
	 */
	private File createTempFile(String tempFileName) {
		if (log.isDebugEnabled())
			log.debug("Entering 'createTempFile' method");
		return new File(tempFileName);
	}

	/**
	 * Crea el OutputStream a partir del archivo temporal. Este OutputStream es
	 * el utilizado en el metodo 'writeData'.
	 * 
	 * @param tempFile
	 *            archivo temporal
	 * @return OutputStream creado
	 */
	protected final OutputStreamWriter getTempFileOutputStream(File tempFile)  {
		if (log.isDebugEnabled())
			log.debug("Entering 'getTempFileOutputStream' method");
		OutputStreamWriter outputStreamWriter = null;
		try {
			outputStreamWriter = new OutputStreamWriter(
					new BufferedOutputStream(new FileOutputStream(tempFile)));
		} catch (IOException e) {
			log.error("Error al crear el OutputStream para el archivo temporal: " + e.getMessage());
			
		}
		log.info("Se creo el OutputStreamWriter para el archivo temporal '"
				+ tempFile.getAbsolutePath() + "'");
		return outputStreamWriter;
	}
	
	/* INI CB ECU-SiCC-2012-0191 */
	/**
	 * Crea el OutputStream a partir del archivo temporal. Este OutputStream es
	 * el utilizado en el metodo 'writeData'.
	 * @param tempFile
	 * @param encoding
	 * @return
	 */
	protected final OutputStreamWriter getTempFileOutputStream(File tempFile, String encoding)  {
		if (log.isDebugEnabled())
			log.debug("Entering 'getTempFileOutputStream' method");
		OutputStreamWriter outputStreamWriter = null;
		try {
			outputStreamWriter = new OutputStreamWriter(
					new BufferedOutputStream(new FileOutputStream(tempFile)), encoding);
		} catch (IOException e) {
			log.error("Error al crear el OutputStream para el archivo temporal: " + e.getMessage());
			
		}
		log.info("Se creo el OutputStreamWriter para el archivo temporal '"
				+ tempFile.getAbsolutePath() + "'");
		return outputStreamWriter;
	}
	/* FIN CB ECU-SiCC-2012-0191 */
	
	/**
	 * Crea el FileOutputStream  a partir del archivo temporal. Este FileOutputStream es
	 * el utilizado en el metodo 'writeData'.
	 * 
	 * @param tempFile
	 *            archivo temporal
	 * @return OutputStream creado
	 */
	protected final FileOutputStream getTempFileOutputStreamBinario(File tempFile)   {
		if (log.isDebugEnabled())
			log.debug("Entering 'getTempFileOutputStreamBinario' method");
		FileOutputStream  fileOutputStream  = null;
		try {
			fileOutputStream = new FileOutputStream(tempFile);
					
		} catch (IOException e) {
			log.error("Error al crear el FileOutputStream para el archivo temporal: "+ e.getMessage());
			
		}
		log.info("Se creo el FileOutputStream para el archivo temporal '" + tempFile.getAbsolutePath() + "'");
		return fileOutputStream;
	}
	
	
	/**
	 * Prepara el list antes de llamar al metodo 'writeData'. En caso se
	 * requiera alguna modificacion adicional al List se puede sobreescribir
	 * este metodo.
	 * 
	 * @param data
	 *            List con los datos de la interfaz
	 */
	protected void prepareDataBeforeWrite(InterfazParams interfazParams,
			List data) {
		if (log.isDebugEnabled())
			log.debug("Entering 'prepareDataBeforeWrite' method");
	}

	/**
	 * Crea el archivo temporal y escribe la data de la Interfaz de Salida. Esta
	 * implementacion por defecto asume un List que contiene Maps que
	 * representan cada fila. Se itera sobre cada fila, se formatea y se escribe
	 * en el OutputStream creado para el archivo temporal. Para el formateo se
	 * utiliza el InterfazFormatterService.
	 * 
	 * @param outputStream
	 *            Stream para escribir los datos en el archivo de salida
	 * @param data
	 *            List con la data de la interfaz de salida
	 * @return numero de registros procesados en el archivo
	 * @throws InterfazException
	 *             relanzada en caso ocurra un IOException al manipular el
	 *             OutputStream
	 */
	protected int writeData(InterfazParams interfazParams, File tempFile,
			List data) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'writeData' method");
		int registrosProcesados = 0;
		
		InterfazFormat interfazFormat = interfazFormatServiceFacade.getInterfazFormat(interfazParams.getInterfaz());
		Map queryParams = interfazParams.getQueryParams();
		
		/* INI CB ECU-SiCC-2012-0191 */
		Interfaz interfaz = interfazParams.getInterfaz();
		/* FIN CB ECU-SiCC-2012-0191 */
		
		String tituloCabeceraInterfaz = (String) queryParams.get("tituloCabeceraInterfaz");
		interfazFormat.setTituloCabeceraInterfaz(tituloCabeceraInterfaz);
		
		boolean archivoBinario = interfazFormat.esArchivoTipoBinario();
		log.info("Se obtuvo el InterfazFormat");
		
		OutputStreamWriter outputStream = null;
		FileOutputStream fileOutputStream = null;
		if (archivoBinario) {			
			//Creando archivo binario
			fileOutputStream = getTempFileOutputStreamBinario(tempFile);
			log.info("Se creo archivo fileOutputStream");
			//Creando objeto binario
			try  {
				interfazFormat.setExcelUtil(new ExcelUtil(fileOutputStream));
				log.info("Se creo object ExcelUtil");
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
		
		/* Iterando el archivo */
		short registrosExcel = 1;
		try {
			// Itero sobre las filas del List y formateo las lineas
			Iterator iter = data.iterator();
			while (iter.hasNext()) {
				Object element = iter.next();
				if (!(element instanceof Map))
					throw new InterfazException(
							"Error al procesar la fila, se esperaba un Map,");
				Map row = (Map) element;
				String line = interfazFormatterService.formatMap(row,
						interfazFormat);
				line=proccesLine(line,row);
				if (!archivoBinario) 
					outputStream.write(line);
				else {
					interfazFormat.getExcelUtil().setRegistro(registrosExcel);
					registrosExcel++;
				}
					
				registrosProcesados++;
			}
			/*SI EL ARCHIVO GENERADO ESTA FORMATO WINDOWS SE AGREGA FIN DE ARCHIVO*/
			if (!archivoBinario && registrosProcesados==0){
				String tipoSeparadorLinea = interfazParams.getInterfaz().getTipoSeparadorLinea();
				if (StringUtils.equals(tipoSeparadorLinea,
	                    Constants.TIPO_SEPARADOR_LINEA_WINDOWS)) {
	                String line = Constants.SEPARADOR_LINEA_WINDOWS;
	                outputStream.write(line);
	            } 
			}
		} catch (InterfazException ie) {
			log.error("Error al escribir el archivo: " + ie.getMessage());
			ie.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw ie;
		} catch (Exception e) {
			log.error("Error al escribir el archivo: " + e.getMessage());
			InterfazException ie = new InterfazException(
					Constants.INTERFAZSICC_ERROR_GENERAR_ARCHIVO);
			ie.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			if (archivoBinario) 
				closeQuietly(interfazFormat);	
			else
			    closeQuietly(outputStream);
			throw ie;
		} finally {
			if (archivoBinario) 
				closeQuietly(interfazFormat);	
			else
			    closeQuietly(outputStream);
		}
		log.info("Se termino de escribir sobre el archivo");
		return registrosProcesados;
	}

	/**
	 * Se encarga de procesar la linea antes de guardarlo en el archvo si es 
	 * sobrescrito el metodo, caso contrario se devuelve la misma linea  
	 * @param line
	 */
	protected String proccesLine(String line,Map row){
		return line;
	}

	/**
	 * Cierra flujo outputStream
	 * @param outputStream
	 */
	protected final void closeQuietly(OutputStreamWriter outputStream) {
        if(outputStream != null) { 
    		try {
    			outputStream.flush();
    			outputStream.close();
    		} catch (Exception ignore) {
                // Ignoramos la excepcion
    		}
        }
	}
	
	/**
	 * Cierra flujo FileOutputStream
	 * @param outputStream
	 */
	protected final void closeQuietly(InterfazFormat interfazFormat) {
        try {
    		interfazFormat.getExcelUtil().cerrarOutput();
    	} 
        catch (Exception ignore) {
            // Ignoramos la excepcion
        }
	}	
	
	/**
	 * Hook method antes del envio del archivo. Este metodo puede ser
	 * sobreescrito en caso se requiera convertir el archivo a enviar a un zip
	 * por ejemplo.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param tempFile
	 *            archivo temporal a enviar
	 * @return archivo a enviar convertido
	 */
	protected File prepareFileBeforeSend(InterfazParams interfazParams,
			File tempFile) {
		log.debug("Entering 'prepareFileBeforeSend' method");
		Interfaz interfaz = interfazParams.getInterfaz();
		if (interfaz.comprimir()) {
			// Comprimo el archivo temporal
			File zipFile = new File(ZipUtil.zipFile(tempFile.getAbsolutePath(),
					interfazParams.getTempZipPath()));
			// Elimino el archivo temporal
			tempFile.delete();
			return zipFile;
		} else {
			return tempFile;
		}

	}

	/**
	 * Envia el archivo temporal a la carpeta Historico y Salida.
	 * 
	 * @param interfaz
	 *            Interfaz
	 * 
	 * @param tempFile
	 *            archivo temporal
	 */
	private void sendFile(InterfazParams interfazParams, File tempFile)
			throws InterfazException {
		Interfaz interfaz = interfazParams.getInterfaz();

		try {
			// Envio por FTP
			if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_FTP)) {
				log.info("Enviando archivo de salida por FTP");
				FTPUtil ftpUtil = new FTPUtil();
				ftpUtil.loguearFTP(interfaz);
				copyTempToSalidaFTP(ftpUtil, interfaz, tempFile);
				copyTempToHistoricoFTP(ftpUtil, interfazParams, tempFile,
						interfazParams.getHistoricoFileName());
				ftpUtil.cerrarFTP();
			}
			/* INI SA PER-SiCC-2012-0840 */
			// Envio por SFTP
			else if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_SFTP)) {
				log.info("Enviando archivo de salida por SFTP");
				SFTPClientWrapper sftpUtil = new SFTPClientWrapper();
				sftpUtil.loguearSFTP(interfaz);
				copyTempToSalidaSFTP(sftpUtil, interfaz, tempFile);
				sftpUtil.disconnect();
				copyTempToHistoricoRed(interfazParams, tempFile);
			}
			/* FIN SA PER-SiCC-2012-0840 */
			// Envio por Red
			else if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_RED)) {
				log.info("Enviando archivo de salida por Red");
				copyTempToSalidaRed(interfazParams, tempFile);
				copyTempToHistoricoRed(interfazParams, tempFile);
			}
			
			//Envio Mixto(Red y FTP)
			else if(interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_MIXTO)){
				
				if(interfaz.getFlagDirectorioEntradaSalida().trim().equalsIgnoreCase(Constants.ENVIO_FTP)){
					log.info("Enviando archivo de salida por FTP");
					FTPUtil ftpUtil = new FTPUtil();
					ftpUtil.loguearFTP(interfaz);
					copyTempToSalidaFTP(ftpUtil, interfaz, tempFile);
					ftpUtil.cerrarFTP();
				}else if(interfaz.getFlagDirectorioEntradaSalida().trim().equalsIgnoreCase(Constants.ENVIO_RED)){
					log.info("Enviando archivo de salida por Red");
					copyTempToSalidaRed(interfazParams, tempFile);
				}
				
				if(interfaz.getFlagDirectorioHistorico().trim().equalsIgnoreCase(Constants.ENVIO_FTP)){
					log.info("Enviando archivo de salida por FTP");
					FTPUtil ftpUtil = new FTPUtil();
					ftpUtil.loguearFTP(interfaz);
					copyTempToHistoricoFTP(ftpUtil, interfazParams, tempFile,interfazParams.getHistoricoFileName());
					ftpUtil.cerrarFTP();
				}else if(interfaz.getFlagDirectorioHistorico().trim().equalsIgnoreCase(Constants.ENVIO_RED)){
					log.info("Enviando archivo de salida por Red");
					copyTempToHistoricoRed(interfazParams, tempFile);
				}
				
			}
				
			// Envio a carpeta Temporal del Paquete, para luego ser zipeado
			if(interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA) && 
					(!StringUtils.isEmpty(interfaz.getDirectorioTemporalPaquete())) ) {
				log.info("Enviando archivo de salida por Red a la carpeta tempora del paquete");
				copyTempToTemporalPaqueteRed(interfazParams, tempFile);
			}
			
			log.info("Se envio el archivo a la salida y al historico");
			FileUtil.deleteFile(tempFile);
		} catch (Exception e) {
			log.error("Ocurrio un error al enviar el archivo de temporal, tempFile=" + tempFile.getAbsolutePath());
			
			if(e.getMessage().equals(Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP)) {
				String message = "Exception FTPLogueoException, " + e.getMessage() + ": " + 
									"servidor: " + interfaz.getServidorFtp() + 
									", puerto: " + interfaz.getPuertoFtp() + 
									", usuario: " + interfaz.getUsuarioFtp() +
									", password: " + interfaz.getPasswordFtp();
				log.error("Exception FTPLogueoException, " + e.getMessage() + ": " + 
						"servidor: " + interfaz.getServidorFtp() + 
						", puerto: " + interfaz.getPuertoFtp() + 
						", usuario: " + interfaz.getUsuarioFtp() +
						", password: " + interfaz.getPasswordFtp());
						
				throw new InterfazException(message);
			}
				
			throw new InterfazException("Error al enviar el archivo de salida: " + e.getMessage());
		}
	}

	/**
	 * Copia el archivo temporal en la carpeta de salida por Red.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param tempFile
	 *            archivo temporal
	 */
	protected void copyTempToSalidaRed(InterfazParams interfazParams,
			File tempFile) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copyTempToSalidaRed' method");
		try {
			Interfaz interfaz = interfazParams.getInterfaz();
			String indicadorCopiado = interfaz.getIndicadorArchivoTemporalCopyDestino();
			log.debug("indicadorCopiado: "+ indicadorCopiado);

			if (Constants.SI.equals(interfaz.getIndicadorArchivoTemporalCopyDestino())) {
				String nombreDestinoFinal = interfazParams.getArchivoSalidaPath();
				String nombreDestinoIntermedio  = nombreDestinoFinal + Constants.DELIMITADOR_EXTENSION_ARCHIVO + Constants.EXTENSION_TMP;
				File outputFile = new File(nombreDestinoFinal);
				File outputFileIntermedio = new File(nombreDestinoIntermedio);
				FileUtils.copyFile(tempFile, outputFileIntermedio);
				
				FileUtils.moveFile(outputFileIntermedio, outputFile);
			}
			else {
			File outputFile = new File(interfazParams.getArchivoSalidaPath());
			FileUtils.copyFile(tempFile, outputFile);
			}	
			
			log.info("Se movio el archivo de la carpeta temporal a la salida por Red");
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_RED);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_REAL);
			throw error;
		}
	}

	/**
	 * Genera Archivo de Salida en el directorio de Salida del FTP
	 * 
	 * @param ftp
	 *            conexion al FTP
	 * @param interfaz
	 *            Interfaz con los datos a buscar
	 * @param tempFile
	 *            Nombre de Archivo a copiar
	 */
	protected void copyTempToSalidaFTP(FTPUtil ftp, Interfaz interfaz,
			File tempFile) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copyTempToSalidaFTP' method");
		try {
			String indicadorCopiado = interfaz.getIndicadorArchivoTemporalCopyDestino();
			
			Boolean flagCopiado = new Boolean(false);
			if (Constants.SI.equals(indicadorCopiado))
				flagCopiado = new Boolean(true);
			log.debug("indicadorCopiado: "+ indicadorCopiado);
			log.debug("flagCopiado: "+ flagCopiado);
			ftp.copiarArchivoRedaFTP(tempFile, interfaz.getDirectorioEntradaSalida(), flagCopiado);
			log
					.info("Se movio el archivo de la carpeta temporal a la salida por FTP");
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_REAL);
			throw error;
		}
	}
	
	/**
	 * Copia el archivo temporal en la carpeta de salida por Red.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param tempFile
	 *            archivo temporal
	 */
	protected void copyTempToTemporalPaqueteRed(InterfazParams interfazParams,
			File tempFile) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copyTempToTemporalPaqueteRed' method");
		try {
			File outputFile = new File(interfazParams.getArchivoTemporalPaquetePath());
			FileUtils.copyFile(tempFile, outputFile);
			log
					.info("Se movio el archivo de la carpeta temporal a la carpeta temporal del paquete");
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_RED);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_REAL);
			throw error;
		}
	}
	
	/**
	 * Metodo que es usado para enviar otros archivos
	 * 
	 * @param interfazParams
	 * @param tempFile
	 * @throws InterfazException
	 */
	public void sendOtherFile(InterfazParams interfazParams, File tempFile) throws InterfazException 
	{
		sendFile(interfazParams, tempFile);
	}
	
	/**
	 * Elimina un archivo enviado del directorio de entrada/salida. 
	 * Usado para eliminar archivos dummys que son enviados para que el proceso finalice correctamente
	 * 
	 * @param interfazParams
	 * @param nombreArchivo
	 * @throws InterfazException
	 */
	public void deleteSendedFile(InterfazParams interfazParams, String nombreArchivo)
			throws InterfazException {
		Interfaz interfaz = interfazParams.getInterfaz();

		try {
			// Si se envio por FTP
			if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_FTP)) {
				log.info("Eliminando archivo de salida por FTP");
				FTPUtil ftpUtil = new FTPUtil();
				ftpUtil.loguearFTP(interfaz);				
				ftpUtil.eliminarArchivoEntrada(interfaz, nombreArchivo);				
				ftpUtil.cerrarFTP();
			}
			/* INI SA PER-SiCC-2012-0840 */
			// Si se envio por SFTP
			else if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_SFTP)) {
				log.info("Eliminando archivo de salida por SFTP");
				SFTPClientWrapper sftpUtil = new SFTPClientWrapper();
				sftpUtil.loguearSFTP(interfaz);				
				sftpUtil.eliminarArchivoEntrada(interfaz, nombreArchivo);				
				sftpUtil.disconnect();
			}
			/* FIN SA PER-SiCC-2012-0840 */
			// Envio por Red
			else if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_RED)) {
				log.info("Eliminando archivo de salida por Red");
				
				File fileToDelete = new File(interfazParams.getArchivoSalidaPath());				
				fileToDelete.delete();				
			}

			// Envio Mixto(Red y FTP)
			else if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_MIXTO)) {

				if (interfaz.getFlagDirectorioEntradaSalida().trim()
						.equalsIgnoreCase(Constants.ENVIO_FTP)) {
					log.info("Eliminando archivo de salida por FTP");
					FTPUtil ftpUtil = new FTPUtil();
					ftpUtil.loguearFTP(interfaz);
					ftpUtil.eliminarArchivoEntrada(interfaz, nombreArchivo);
					ftpUtil.cerrarFTP();
				} else if (interfaz.getFlagDirectorioEntradaSalida().trim()
						.equalsIgnoreCase(Constants.ENVIO_RED)) {
					log.info("Eliminando archivo de salida por Red");
					
					File fileToDelete = new File(interfazParams.getArchivoSalidaPath());				
					fileToDelete.delete();				
				}
			}
		} catch (Exception e) {
			log.error("Ocurrio un error al eliminar el archivo, tempFile=" + nombreArchivo);
			throw new InterfazException(
					"Error al eliminar el archivo: " + e.getMessage());
		}
	}
	
	/* INI SA PER-SiCC-2012-0840 */
	
	/**
	 * Genera Archivo de Salida en el directorio de Salida del SFTP
	 * 
	 * @param sftp
	 *            conexion al SFTP
	 * @param interfaz
	 *            Interfaz con los datos a buscar
	 * @param tempFile
	 *            Nombre de Archivo a copiar
	 */
	protected void copyTempToSalidaSFTP(SFTPClientWrapper sftpUtil, Interfaz interfaz,
			File tempFile) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copyTempToSalidaSFTP' method");
		try { 
			String indicadorCopiado = interfaz.getIndicadorArchivoTemporalCopyDestino();
			Boolean flagCopiado = new Boolean(false);
			if (Constants.SI.equals(indicadorCopiado))
				flagCopiado = new Boolean(true);
			
			log.debug("indicadorCopiado: "+ indicadorCopiado);
			log.debug("flagCopiado: "+ flagCopiado);
			sftpUtil.copiarArchivoRedaSFTP(tempFile, interfaz.getDirectorioEntradaSalida(), flagCopiado);
			log.info("Se movio el archivo de la carpeta temporal a la salida por SFTP");
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_SFTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_REAL);
			throw error;
		}
	}
	/* FIN SA PER-SiCC-2012-0840 */
	
}