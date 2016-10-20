package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.SFTPClientWrapper;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase service abstracta para las Intefaces Entrada SiCC. Implementa el
 * template method 'processInterfaz' para las Interfaces de Entrada.
 * NSSICC: 
 * Mejoras Realizadas NSSICC:
 *  - Colocacion del this
 *  - Colocacion del nombreArchivo en Historico
 *  - El archivo de Entrada ya no es borrado al inicio, sino al final siempre y cuando todo este OK, 
 *    caso contrario ocurra alguna excepcion el archivo sigue en el FTP
 *     
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */

@Service("sisicc.baseInterfazEntradaAbstractService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public abstract class BaseInterfazEntradaAbstractService extends
		BaseInterfazAbstractService {

	/**
	 * Template Method que define el algoritmo basico para las Interfaces de
	 * Entrada de SiCC: Lee los datos del archivo de entrada, los parsea y
	 * procesa.
	 * 
	 * @param interfazParams
	 *            parametros de la Interfaz
	 * @return numero de registros procesados
	 */
	protected int processInterfaz(InterfazParams interfazParams)
			throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'processInterfaz' method");
		File tempFile = null;
		int registrosProcesados = 0;
		Interfaz interfaz = interfazParams.getInterfaz();
		String flagMoverArchivoEntradaTemporal = interfaz.getFlagMoverArchivoEntradaTemporal();
		if (StringUtils.isBlank(flagMoverArchivoEntradaTemporal)) {
			log.info("Convirtiendo flagMoverArchivoEntradaTemporal a N");
			flagMoverArchivoEntradaTemporal = Constants.NO;
		}
		log.info("flagMoverArchivoEntradaTemporal: "+ flagMoverArchivoEntradaTemporal);

		/* Comportamiento Actual del SSiCC, el archivo de Entrada es movido a la Carpeta Temporal al inicio
		 * Comportamiento NSSiCC, el archivo de Entrada ya no es borrado al inicio, sino al final siempre y cuando todo este OK  */
		try {
			this.getNombreArchivo(interfazParams);			
			tempFile = this.receiveFile(interfazParams);
			tempFile = this.afterReceiveFile(tempFile);
			log.info("Se recibio el archivo de entrada");
			// En este List se almacenara la data si es que se requiere
			List data = new ArrayList();
			this.beforeReadData(interfazParams);
			
			registrosProcesados = this.readData(interfazParams, tempFile, data);
			log.info("Se procesaron los datos del archivo temporal");
			// Sobrescribir para un procesamiento de todas las lineas
			this.processData(interfazParams, data);
			interfazResult.setCompletadoInterfaz(true); // FRAMEWORK NSSICC
			// Para liberar las referencias y apresurar al GarbageCollector
			data = null;
			
			
		} catch (Exception e) {
			log.error("Error al procesar la interfaz: " + e.getMessage());
			Map params = interfazParams.getQueryParams();
			params.put("errorInterfaz", Constants.SI);	
			
			InterfazException interfazException = new InterfazException(e.getMessage());
			interfazException.setRegistrosProcesados(registrosProcesados);
			throw interfazException;
		} finally {
			FileUtil.deleteFile(tempFile);
		}
			
	
		log.info("Se termino de procesar la interfaz de entrada");
		return registrosProcesados;
	}

	/**
	 * @param interfazParams
	 * @throws InterfazException
	 */
	protected void getNombreArchivo(InterfazParams interfazParams)
	throws InterfazException {
	}
	
	/**
	 * @param interfazParams
	 * @throws InterfazException
	 */
	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
	}

	/**
	 * Lee el archivo temporal de entrada. Esta implementacion lee el archivo
	 * temporal linea por linea aplicando el parseo del
	 * InterfazFormatterService. Si se requiere de un procesamiento por linea se
	 * debe implementar el metodo 'writeLine'. Si se requiere de procesar todas
	 * las filas se debe implementar el metodo 'addLine' y agregando la linea al
	 * List, luego se debe implementar el metodo 'writeData' para procesar el
	 * List con todas las filas del archivo.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param tempFile
	 *            archivo temporal de entrada
	 * @param data
	 *            List de Maps con las filas del archivo de texto si se
	 *            implemento el metodo addLine
	 * @return numero de filas procesadas
	 * @throws InterfazException
	 */
	protected int readData(InterfazParams interfazParams, File tempFile,
			List data) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method");
		InterfazFormat interfazFormat = interfazFormatServiceFacade
				.getInterfazFormat(interfazParams.getInterfaz());
		int lineCount = 0;
		LineIterator lineIterator = null;
		String line = null;
		try {
			lineIterator = FileUtils.lineIterator(tempFile, null);
			while (lineIterator.hasNext()) {
				line = lineIterator.nextLine();
				lineCount++;
				Map row = interfazFormatterService.parseLine(line,
						interfazFormat);
				// Sobrescribir para un procesamiento linea por linea
				processLine(interfazParams, lineCount, row);
				// Sobrescribir para un procesamiento de todas las lineas
				addLine(data, row);
			}
		} catch (IOException e) {
			log.error("Error de IO al leer el archivo temporal: "+ e.getMessage());
			InterfazException ie = new InterfazException(e.getMessage());
			ie.setRegistrosProcesados(lineCount);
			LineIterator.closeQuietly(lineIterator);
			throw new InterfazException(e.getMessage());
		} finally {
			LineIterator.closeQuietly(lineIterator);
		}
		return lineCount;
	}

	/**
	 * Metodo invocado por el metodo 'readData' dentro de la iteracion de las
	 * lineas del archivo temporal. Este metodo no contiene implementacion para
	 * evitar el tener toda la lista en memoria. La intencion de este metodo es
	 * el de ser sobrescrito en caso se requiera de procesar todo el archivo, lo
	 * que debe realizar es agregar el Map de la fila al List: data.add(row);
	 * 
	 * @see writeData
	 * 
	 * @param data
	 *            List con las filas del archivo
	 * @param row
	 *            Map con la fila del archivo a agregar
	 */
	protected void addLine(List data, Map row) {
	}

	/**
	 * Metodo invocado por el metodo 'readData' dentro de la iteracion de las
	 * lineas del archivo temporal. La intencion de este metodo es el de ser
	 * sobrescrito en caso se requiera un procesamiento linea por linea.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param lineCount
	 *            numero de la linea leida
	 * @param row
	 *            Map con la fila leida
	 * @throws InterfazException
	 */
	protected void processLine(InterfazParams interfazParams, int lineCount,
			Map row) throws InterfazException {
	}

	/**
	 * Metodo invocado por el metodo 'processInterfaz' luego de 'readData'. La
	 * intencion de este metodo es el de ser sobrescrito en caso se requiera
	 * procesar todo el archivo. Debe ser sobrescrito juntamente con el metodo
	 * 'addLine' para almacenar las filas del archivo en el List.
	 * 
	 * @see addLine
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param data
	 *            List con las filas del archivo
	 * 
	 * @throws InterfazException
	 */
	protected void processData(InterfazParams interfazParams, List data)
			throws InterfazException {
	}

	/**
	 * Recibe el archivo de entrada por Red o por FTP y lo copia a la carpeta
	 * temporal y al historico.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param tempFileName
	 *            nombre del archivo temporal
	 * @param histoFileName
	 *            nombre del archivo historico
	 * @return referencia al archivo temporal
	 */
	private File receiveFile(InterfazParams interfazParams)	throws InterfazException {
		
		if (log.isDebugEnabled()) log.debug("Entering 'receiveFile' method");
		
		Interfaz interfaz = interfazParams.getInterfaz();
		String flagMoverArchivoEntradaTemporal = interfaz.getFlagMoverArchivoEntradaTemporal();
		File tempFile = new File(interfazParams.getTempPath());
		
		log.warn("Creando el archivo temporal: " + tempFile.getAbsolutePath());

        String flagEnvioArchivo = interfaz.getFlagEnvioArchivo().trim();
	
		
		try {
			// Recibo por FTP
			if (flagEnvioArchivo.equalsIgnoreCase(Constants.ENVIO_FTP)) {
				
				log.warn("Recibiendo archivo de entrada por FTP");
							
				FTPUtil ftpUtil = new FTPUtil();
				ftpUtil.loguearFTP(interfaz);
				
				moveEntradaToTempFTP(interfazParams, ftpUtil);
				
				String tipoNombreArchivo = interfazParams.getInterfaz().getTipoNombreArchivo();
				if  (tipoNombreArchivo.equals(Constants.ARCHIVO_FIJO)) {
					copyTempToHistoricoFTP(ftpUtil, interfazParams, tempFile,interfazParams.getArchivoSalidaFileName());
				}
				
				
				copyTempToHistoricoFTP(ftpUtil, interfazParams, tempFile,interfazParams.getHistoricoFileName());
				ftpUtil.cerrarFTP();
				
			}

			/* INI SA PER-SiCC-2012-0840 */
			// Recibo por SFTP
			else if (flagEnvioArchivo.equalsIgnoreCase(Constants.ENVIO_SFTP)) {
				log.warn("Recibiendo archivo de entrada por SFTP");
				
				SFTPClientWrapper sftpUtil = new SFTPClientWrapper();
				sftpUtil.loguearSFTP(interfaz);
				moveEntradaToTempSFTP(interfazParams, sftpUtil);
				sftpUtil.disconnect();
				
				copyTempToHistoricoRed(interfazParams, tempFile);
			}
			/* FIN SA PER-SiCC-2012-0840 */
			
			// Recibo por Red
			else if (flagEnvioArchivo.equalsIgnoreCase(Constants.ENVIO_RED)) {
				log.info("Recibiendo archivo de entrada por red");
				
				/* INI FRAMEWORK NSSICC	*/
				if (flagMoverArchivoEntradaTemporal.equals(Constants.NO)) {
					log.info("Copiando ARCHIVO A TEMPORAL");
					this.copiarEntradaToTempRed(interfazParams, tempFile); 
				}
				else {
					log.info("Moviendo ARCHIVO A TEMPORAL");
					this.moveEntradaToTempRed(interfazParams, tempFile);
				}
				/* FIN FRAMEWORK NSSICC	*/
				this.copyTempToHistoricoRed(interfazParams, tempFile);
			}
			// Recepcion Mixta
			else if(flagEnvioArchivo.equalsIgnoreCase(Constants.ENVIO_MIXTO)){
				
				if(interfaz.getFlagDirectorioEntradaSalida().trim().equalsIgnoreCase(Constants.ENVIO_FTP)){
					log.info("Recibiendo archivo de entrada por FTP");
					FTPUtil ftpUtil = new FTPUtil();
					ftpUtil.loguearFTP(interfaz);
					moveEntradaToTempFTP(interfazParams, ftpUtil);
					ftpUtil.cerrarFTP();
				}else if(interfaz.getFlagDirectorioEntradaSalida().trim().equalsIgnoreCase(Constants.ENVIO_RED)){
					log.info("Recibiendo archivo de entrada por red");
					
					/* INI FRAMEWORK NSSICC	*/
					if (flagMoverArchivoEntradaTemporal.equals(Constants.NO))
						this.copiarEntradaToTempRed(interfazParams, tempFile); 
					else
						this.moveEntradaToTempRed(interfazParams, tempFile);
					/* FIN FRAMEWORK NSSICC	*/
				}
				
				if(interfaz.getFlagDirectorioHistorico().trim().equalsIgnoreCase(Constants.ENVIO_FTP)){
					
					log.info("Recibiendo archivo de entrada por FTP");
					FTPUtil ftpUtil = new FTPUtil();
					ftpUtil.loguearFTP(interfaz);
					String tipoNombreArchivo = interfazParams.getInterfaz().getTipoNombreArchivo();
					
					if  (tipoNombreArchivo.equals(Constants.ARCHIVO_FIJO)) {
						this.copyTempToHistoricoFTP(ftpUtil, interfazParams, tempFile,interfazParams.getArchivoSalidaFileName());
					}
					
					this.copyTempToHistoricoFTP(ftpUtil, interfazParams, tempFile,	interfazParams.getHistoricoFileName());
					ftpUtil.cerrarFTP();
				}else if(interfaz.getFlagDirectorioHistorico().trim().equalsIgnoreCase(Constants.ENVIO_RED)){
					log.info("Recibiendo archivo de entrada por red");
					copyTempToHistoricoRed(interfazParams, tempFile);
				}
			}
		} catch (InterfazException e) {
			throw e;
		} catch (Exception e) {
			throw new InterfazException("Error al recibir el archivo de entrada: " + e.getMessage());
		}
		return tempFile;
	}
	
	/* INI FRAMEWORK NSSICC */
	/**
	 * Elimina archivo del directorio de Entrada
	 * @param interfazParams
	 * @throws InterfazException
	 */
	private void eliminarFileEntrada(InterfazParams interfazParams)
			throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'eliminarFile' method");
		Interfaz interfaz = interfazParams.getInterfaz();
		Map queryParams = interfazParams.getQueryParams();
		String archivoEntradaFileName = (String) queryParams.get("archivoEntradaFileName");
		log.info("archivoEntradaFileName: " + archivoEntradaFileName);
		if (StringUtils.isNotBlank(archivoEntradaFileName)) {
			try {
				// Recibo por FTP
				if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_FTP)) {
					log.info("Eliminando archivo de entrada por FTP");
					FTPUtil ftpUtil = new FTPUtil();
					ftpUtil.loguearFTP(interfaz);
					ftpUtil.eliminarArchivo(interfaz.getDirectorioEntradaSalida(),	archivoEntradaFileName);	
					ftpUtil.cerrarFTP();
				}
				
				// Recibo por SFTP
				else if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_SFTP)) {
					log.warn("Eliminando archivo de entrada por SFTP");
					
	                SFTPClientWrapper sftpUtil = new SFTPClientWrapper();
					sftpUtil.loguearSFTP(interfaz);
					sftpUtil.deleteFile(interfaz.getDirectorioEntradaSalida(), archivoEntradaFileName);
					sftpUtil.disconnect();
				}
	
				// Recibo por Red
				else if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_RED)) {
					log.info("Eliminando archivo de entrada por red");
					File inputFile = new File(archivoEntradaFileName);
					FileUtil.deleteFile(inputFile);
				}
				// Recepcion Mixta
				else if(interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(Constants.ENVIO_MIXTO)){
					
					if(interfaz.getFlagDirectorioEntradaSalida().trim().equalsIgnoreCase(Constants.ENVIO_FTP)){
						log.info("Eliminando archivo de entrada por FTP");
						FTPUtil ftpUtil = new FTPUtil();				
						ftpUtil.loguearFTP(interfaz);
						ftpUtil.eliminarArchivo(interfaz.getDirectorioEntradaSalida(),	archivoEntradaFileName);				
						ftpUtil.cerrarFTP();
					}
					else if(interfaz.getFlagDirectorioEntradaSalida().trim().equalsIgnoreCase(Constants.ENVIO_RED)){
						log.info("Eliminando archivo de entrada por red");
						File inputFile = new File(archivoEntradaFileName);
						FileUtil.deleteFile(inputFile);
					}
				}
			} catch (InterfazException e) {
				throw e;
			} catch (Exception e) {
				throw new InterfazException("Error al eliminar el archivo de entrada: " + e.getMessage());
			}
		}	
		if (log.isDebugEnabled())
			log.debug("Finish 'eliminarFile' method");
		return;
	}
	/*  FIN FRAMEWORK NSSICC */

	/**
	 * Este metodo puede ser sobrescrito en caso se requiera de realizar alguna
	 * accion sobre el archivo temporal antes de procesarlo. Por ejemplo en caso
	 * que el archivo de entrada este comprimido, la descompresion se implementa
	 * en este metodo. Esta implementacion solo devuelve el mismo archivo.
	 * 
	 * @param tempFile
	 *            archivo temporal
	 * @return archivo temporal
	 */
	protected File afterReceiveFile(File tempFile) {
		return tempFile;
	}

	/**
	 * Mueve el archivo de la carpeta de entrada al temporal por FTP
	 * 
	 * @param interfazParams
	 * @param ftpUtil
	 * @throws InterfazException
	 */
	// TODO Refactoring de los parametros
	private void moveEntradaToTempFTP(InterfazParams interfazParams, FTPUtil ftpUtil) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'moveEntradaToTempFTP' method");
		try {
			Interfaz interfaz = interfazParams.getInterfaz();
			String archivoEntradaFileName = ftpUtil.buscarArchivoEntrada(interfaz);
                        log.debug("archivoEntradaFileName : " + archivoEntradaFileName );

			this.validatePreviousLoad(interfaz,archivoEntradaFileName);
			
			/* INI FRAMEWORK NSSICC */
            Map queryParams = interfazParams.getQueryParams();
			queryParams.put("archivoEntradaFileName", archivoEntradaFileName);
			interfazParams.setQueryParams(queryParams);

			Historico historicoEjecucion = interfazParams.getHistorico();
			historicoEjecucion.setNombreArchivo(archivoEntradaFileName);
			interfazParams.setHistorico(historicoEjecucion);
			/* FIN FRAMEWORK NSSICC */
			
			ftpUtil.copiarArchivoFTPaRed(interfaz.getDirectorioEntradaSalida(), archivoEntradaFileName, interfaz.getDirectorioTemporal(), interfazParams.getTempFileName());
			ftpUtil.eliminarArchivo(interfaz.getDirectorioEntradaSalida(), archivoEntradaFileName);
			log.info("Se movio el archivo de la carpeta de entrada al temporal por FTP");
		} catch (InterfazException e) {
			throw e;
		} catch (Exception e) {
			InterfazException error = new InterfazException(Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_FTP + e.getMessage());
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}

        /**
	 * @param interfaz
	 * @param ftpUtil
	 * @throws InterfazException
	 */
	private void validatePreviousLoad(Interfaz interfaz,String archivoEntradaFileName) throws InterfazException {
		
		
		if (interfaz.getFlagValidarCargaPrevia().equals(Constants.SI)){
			
			Map criteria = new HashMap();
			
          	criteria.put("codigoPais",interfaz.getCodigoPais());  
          	criteria.put("codigoSistema",interfaz.getCodigoSistema());
			criteria.put("codigoInterfaz",interfaz.getCodigo());
			criteria.put("ejecucionSatisfactoria",Constants.SI);			
			
			criteria.put("nombreArchivoOriginal",archivoEntradaFileName);
			
			int size = historicoService.getHistoricosByCriteria(criteria).size();
			
			if (size>0)	{
				
				InterfazException error = new InterfazException("Archivo  " + archivoEntradaFileName + " ya fue cargado");
				error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}
			
		}
		
		
	
	}

	

	/**
	 * Mueve el archivo de la carpeta de entrada al temporal por Red.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param tempFile
	 *            archivo temporal
	 * @throws InterfazException
	 */
	private void moveEntradaToTempRed(InterfazParams interfazParams, File tempFile) throws InterfazException {
		
		if (log.isDebugEnabled()) log.debug("Entering 'moveEntradaToTempRed' method");
		
		try {
			File inputFile = new File(interfazParams.getArchivoEntradaPath());
 
			/* INI FRAMEWORK NSSICC */
            String archivoEntradaFileName = interfazParams.getArchivoEntradaPath();
			String nombreArchivo = interfazParams.getArchivoEntradaFileName();
			Map mapAdd= interfazParams.getQueryParams();

            Interfaz interfaz = interfazParams.getInterfaz();
			validatePreviousLoad(interfaz, nombreArchivo);

			Historico historicoEjecucion = interfazParams.getHistorico();
			
			mapAdd.put("nombreArchivo", nombreArchivo);
			mapAdd.put("archivoEntradaFileName", archivoEntradaFileName);
			historicoEjecucion.setNombreArchivo(nombreArchivo);
			interfazParams.setQueryParams(mapAdd);
			interfazParams.setHistorico(historicoEjecucion);
            /* FIN FRAMEWORK NSSICC */
			log.debug("INI Moviendo a carpeta TEMP");
			FileUtils.copyFile(inputFile, tempFile);			
			FileUtil.deleteFile(inputFile);
			log.debug("FIN Moviendo a carpeta TEMP");
			//FileUtils.moveFile(inputFile, tempFile);	

			log.warn("Se movio el archivo de la carpeta de entrada al temporal por Red");
		
         	
         } catch (Exception e) {
        	 log.warn("ERROR - No Se movio el archivo de la carpeta de entrada al temporal por Red");
			InterfazException error = new InterfazException(Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_RED + e.getMessage());
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}
	
     /* INI SA PER-SiCC-2012-0840 */
	/**
	 * Mueve el archivo de la carpeta de entrada al temporal por SFTP
	 * 
	 * @param interfazParams
	 * @param sftpUtil
	 * @throws InterfazException
	 */
	private void moveEntradaToTempSFTP(InterfazParams interfazParams, SFTPClientWrapper sftpUtil) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'moveEntradaToTempFTP' method");
		try {
			
			Interfaz interfaz = interfazParams.getInterfaz();
			String archivoEntradaFileName = sftpUtil.buscarArchivoEntrada(interfaz);
			validatePreviousLoad(interfaz,archivoEntradaFileName);
			sftpUtil.copiarArchivoSFTPaRed(interfaz.getDirectorioEntradaSalida(), archivoEntradaFileName, interfaz.getDirectorioTemporal(),	interfazParams.getTempFileName());
			sftpUtil.deleteFile(interfaz.getDirectorioEntradaSalida(), archivoEntradaFileName);
			
			log.warn("Se movio el archivo de la carpeta de entrada al temporal por SFTP");
		} catch (InterfazException e) {
			throw e;
        } catch (Exception e) {
			InterfazException error = new InterfazException(Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_SFTP + e.getMessage());
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}
	/* FIN SA PER-SiCC-2012-0840 */

	/* INI FRAMEWORK NSSICC */
	
	/**
	 * Copia el archivo de la carpeta de entrada al temporal por SFTP
	 * 
	 * @param interfazParams
	 * @param sftpUtil
	 * @throws InterfazException
	 */
	private void copyEntradaToTempSFTP(InterfazParams interfazParams, SFTPClientWrapper sftpUtil) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copyEntradaToTempSFTP' method");
		try {
			
			Interfaz interfaz = interfazParams.getInterfaz();
			String archivoEntradaFileName = sftpUtil.buscarArchivoEntrada(interfaz);
			validatePreviousLoad(interfaz,archivoEntradaFileName);
			sftpUtil.copiarArchivoSFTPaRed(interfaz.getDirectorioEntradaSalida(), archivoEntradaFileName, interfaz.getDirectorioTemporal(),	interfazParams.getTempFileName());
			
			log.warn("Se copio el archivo de la carpeta de entrada al temporal por SFTP");
		} catch (InterfazException e) {
			throw e;
        } catch (Exception e) {
			InterfazException error = new InterfazException(Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_SFTP + e.getMessage());
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}
	
	
	/**
	 * Copia el archivo de la carpeta de entrada al temporal por FTP
	 * 
	 * @param interfazParams
	 * @param ftpUtil
	 * @throws InterfazException
	 */
	// TODO Refactoring de los parametros
	private void copiarEntradaToTempFTP(InterfazParams interfazParams,
			FTPUtil ftpUtil) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copiarEntradaToTempFTP' method");
		try {
			Interfaz interfaz = interfazParams.getInterfaz();
			String archivoEntradaFileName = ftpUtil.buscarArchivoEntrada(interfaz);
			Map queryParams = interfazParams.getQueryParams();
			queryParams.put("archivoEntradaFileName", archivoEntradaFileName);
			interfazParams.setQueryParams(queryParams);
			
			/* INI FRAMEWORK NSSICC */
			Historico historicoEjecucion = interfazParams.getHistorico();
			historicoEjecucion.setNombreArchivo(archivoEntradaFileName);
			interfazParams.setHistorico(historicoEjecucion);
			/* FIN FRAMEWORK NSSICC */
			
			ftpUtil.copiarArchivoFTPaRed(interfaz
					.getDirectorioEntradaSalida(), archivoEntradaFileName, interfaz.getDirectorioTemporal(),
					interfazParams.getTempFileName());
			
			log.info("Se copio el archivo de la carpeta de entrada al temporal por FTP");
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_FTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}

	/**
	 * Copia el archivo de la carpeta de entrada al temporal por Red.
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @param tempFile
	 *            archivo temporal
	 * @throws InterfazException
	 */
	private void copiarEntradaToTempRed(InterfazParams interfazParams,
			File tempFile) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'copiarEntradaToTempRed' method");
		try {
			/* INI FRAMEWORK NSSICC */
			String archivoEntradaFileName = interfazParams.getArchivoEntradaPath();
			String nombreArchivo = interfazParams.getArchivoEntradaFileName();
			File inputFile = new File(archivoEntradaFileName);
			Map mapAdd= interfazParams.getQueryParams();
			Historico historicoEjecucion = interfazParams.getHistorico();
			
			mapAdd.put("nombreArchivo", nombreArchivo);
			mapAdd.put("archivoEntradaFileName", archivoEntradaFileName);
			historicoEjecucion.setNombreArchivo(nombreArchivo);
			interfazParams.setQueryParams(mapAdd);
			interfazParams.setHistorico(historicoEjecucion);
			/* FIN FRAMEWORK NSSICC */
			log.info("INI - Se copio el archivo de la carpeta de entrada al temporal por Red");
			FileUtils.copyFile(inputFile, tempFile);
			log.info("FIN - Se copio el archivo de la carpeta de entrada al temporal por Red");
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_RED);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}
	/* FIN FRAMEWORK NSSICC */
}