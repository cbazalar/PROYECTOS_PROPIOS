package biz.belcorp.ssicc.service.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.util.exception.StoringFileOverFtpException;

/**
 * Clase utilitaria para el manejo de archivos en el FTP
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
public class FTPUtil {
	protected final Log log = LogFactory.getLog(getClass());

	private FTPClient ftp;

	/**
	 * Formatea la ruta de las carpetas FTP.
	 * 
	 * @param ruta
	 *            Ruta a formatear.
	 * 
	 * @return Ruta formateada.
	 */
	public String formatearRuta(String ruta) {
		if (!ruta.trim().endsWith("/"))
			ruta = ruta.trim() + "/";
		return ruta.trim();
	}

	/**
	 * Funcion que realiza el logueo al FTP
	 * 
	 * @param interfaz
	 *            Clase que contiene los valores para loguearse al ftp
	 * 
	 */
	public boolean loguearFTP(Interfaz interfaz) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'loguearFTP' method");
		try {
			Integer iPort = new Integer(interfaz.getPuertoFtp());
			this.ftp = new FTPClient();

			// Conectamos al servidor FTP
			this.ftp.connect(interfaz.getServidorFtp(), iPort.intValue());

			// Autenticarse en el servidor
			this.ftp.login(interfaz.getUsuarioFtp(), interfaz.getPasswordFtp());

			// Verificando que este logueado
			boolean logged = this.ftp.isConnected();
			if (logged)
				log.info("Succesfully logged to " + interfaz.getServidorFtp());
			return logged;
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}

	/**
	 * Funcion que realiza el logueo al FTP en Modo Pasivo
	 * 
	 * @param interfaz
	 *            Clase que contiene los valores para loguearse al ftp
	 * 
	 */
	public boolean loguearFTPPasivo(Interfaz interfaz) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'loguearFTPPasivo' method");
		try {
			Integer iPort = new Integer(interfaz.getPuertoFtp());
			this.ftp = new FTPClient();

			// Conectamos al servidor FTP
			this.ftp.connect(interfaz.getServidorFtp(), iPort.intValue());

			// Autenticarse en el servidor
			this.ftp.login(interfaz.getUsuarioFtp(), interfaz.getPasswordFtp());
			this.ftp.enterLocalPassiveMode();
			
			// Verificando que este logueado
			boolean logged = this.ftp.isConnected();
			if (logged)
				log.info("Succesfully logged to " + interfaz.getServidorFtp());
			return logged;
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}
	
	/**
	 * Funcion que cambia directorio activo en el FTP
	 * 
	 * @param ftp
	 *            FTP logueado
	 * 
	 * @param directorio
	 *            Directorio del cual se pide sea el Activo
	 */
	public void cambiarDirectorioFTP(String directorio)
			throws InterfazException {
		try {
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Cambiando directorio
			ftp.changeWorkingDirectory(directorio);

			// Verificamos que se haya podido cambiar al directorio de trabajo
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new Exception();
			}
			log.info("Se cambio el directorio FTP a: " + directorio);
		} catch (InterfazException e) {
			throw e;
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_CAMBIAR_DIRECTORIO_FTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}

	/**
	 * 
	 * Copia Archivo guardado en el FTP al directorio de Red
	 * 
	 * @param dirOrigenFTP
	 *            Directorio Origen ubicado en el FTP
	 * @param archivo
	 *            Nombre de Archivo a copiar
	 * @param dirDestinoRed
	 *            Directorio Destino ubicando en Red
	 * @param ftp
	 *            Conexion ftp
	 * 
	 */
	public void copiarArchivoFTPaRed(String dirOrigenFTP, String archivo,
			String dirDestinoRed) throws Exception {
		this
				.copiarArchivoFTPaRed(dirOrigenFTP, archivo, dirDestinoRed,
						archivo);
	}

	/**
	 * 
	 * Copia Archivo guardado en el FTP al directorio de Red
	 * 
	 * @param dirOrigenFTP
	 *            Directorio Origen ubicado en el FTP
	 * @param archivo
	 *            Nombre de Archivo a copiar
	 * @param dirDestinoRed
	 *            Directorio Destino ubicando en Red
	 * @param archivoDestino
	 *            Nombre de Archivo destino
	 * @param ftp
	 *            Conexion ftp
	 */
	public void copiarArchivoFTPaRed(String dirOrigenFTP, String archivo,
			String dirDestinoRed, String archivoDestino) throws Exception {
		String dirTmp;
		FileOutputStream fos = null;

		try {
			dirTmp = FileUtil.formatDirectory(dirDestinoRed);

			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP(dirOrigenFTP);

			/* Grabando en el Directorio en la Red */
			String ruta = dirTmp + archivoDestino;
			fos = new FileOutputStream(ruta);
			log.info("Copying " + archivo + " to " + ruta);
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.retrieveFile(archivo, fos);

			// Verificamos que se haya podido copiar el archivo en la Red
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new StoringFileOverFtpException();
			}
		} catch (InterfazException e) {
			try {
				// Cerrando flujo
				fos.flush();
				fos.close();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		} catch (Exception e) {
			try {
				// Cerrando flujo
				fos.flush();
				fos.close();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		} finally {
			try {
				// Cerrando flujo
				fos.flush();
				fos.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	/**
	 * 
	 * Copia Archivo guardado en la Red al Servidor FTP
	 * 
	 * @param dirOrigenRed
	 *            Directorio Origen ubicado en Red
	 * @param archivo
	 *            Nombre de Archivo a copiar
	 * @param dirDestinoFTP
	 *            Directorio Destino ubicando en el FTP
	 * 
	 */
	public void copiarArchivoRedaFTP(String dirOrigenRed, String archivo,
			String dirDestinoFTP) throws Exception {
		try {
			String rutaOrigen = FileUtil.formatDirectory(dirOrigenRed)
					+ archivo;

			// Sube los archivos de la carpeta local a la carpeta del FTP
			File filaOrigen = new File(rutaOrigen);
			this.copiarArchivoRedaFTP(filaOrigen, dirDestinoFTP);
		} catch (InterfazException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * 
	 * Copia Archivo guardado en la Red al Servidor FTP
	 * 
	 * @param fileArchivo
	 *            Archivo a copiar
	 * @param dirDestinoFTP
	 *            Directorio Destino ubicando en el FTP
	 * 
	 */
	public void copiarArchivoRedaFTP(File fileArchivo, String dirDestinoFTP)
			throws Exception {
		try {
			// Obteniendo nombre del Archivo
			String archivo = fileArchivo.getName();

			// Sube los archivos de la carpeta local a la carpeta del FTP
			this.copiarArchivoRedaFTP(fileArchivo, dirDestinoFTP, archivo);
		} catch (Exception ex) {
			throw ex;
		}
	}

	
	/**
	 * Copia Archivo guardado en la Red al Servidor FTP
	 * @param fileArchivo
	 * @param dirDestinoFTP
	 * @param indicador
	 * @throws Exception
	 */
	public void copiarArchivoRedaFTP(File fileArchivo, String dirDestinoFTP, Boolean indicador)
			throws Exception {
		log.debug("Entering 'copiarArchivoRedaFTP' method con boolean: " + indicador);
		try {
			// Obteniendo nombre del Archivo
			String archivo = fileArchivo.getName();

			// Sube los archivos de la carpeta local a la carpeta del FTP
			this.copiarArchivoRedaFTP(fileArchivo, dirDestinoFTP, archivo, indicador);
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * 
	 * Copia Archivo guardado en la Red al Servidor FTP
	 * 
	 * @param filaArchivo
	 *            Archivo a copiar
	 * @param dirDestinoFTP
	 *            Directorio Destino ubicado en el FTP
	 * @param nombreDestinoFTP
	 *            Nombre Destino ubicado en el FTP
	 * 
	 * 
	 */
	public void copiarArchivoRedaFTP(File filaArchivo, String dirDestinoFTP,
			String nombreDestinoFTP) throws Exception {
		FileInputStream fis = null;
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Sube los archivos de la carpeta local a la carpeta del FTP
			fis = new FileInputStream(filaArchivo);

			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.storeFile(formatearRuta(dirDestinoFTP) + nombreDestinoFTP, fis);

			// Verificamos que se haya podido copiar el archivo en la Red
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new StoringFileOverFtpException();
			}
		} catch (InterfazException ex) {
			try {
				// Cerrando flujo
				fis.close();
			} catch (Exception e) {
				throw e;
			}
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				// Cerrando flujo
				fis.close();
			} catch (Exception e) {
				throw e;
			}
			throw ex;
		} finally {
			try {
				// Cerrando flujo
				fis.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	/**
	 * 
	 * Copia Archivo guardado en la Red al Servidor FTP
	 * 
	 * @param filaArchivo
	 *            Archivo a copiar
	 * @param dirDestinoFTP
	 *            Directorio Destino ubicado en el FTP
	 * @param nombreDestinoFTP
	 *            Nombre Destino ubicado en el FTP
	 * 
	 * 
	 */
	public void copiarArchivoRedaFTP(File filaArchivo, String dirDestinoFTP,
			String nombreDestinoFTP, Boolean indicadorTemporal) throws Exception {
		FileInputStream fis = null;
		String nombreDestinoFTPIntermedio = nombreDestinoFTP + Constants.DELIMITADOR_EXTENSION_ARCHIVO + Constants.EXTENSION_TMP;
		try {
			// Verificando si existe conexion al FTP
			
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Sube los archivos de la carpeta local a la carpeta del FTP
			fis = new FileInputStream(filaArchivo);
			
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			if (indicadorTemporal) {
				log.debug("Archivo TMP: " + formatearRuta(dirDestinoFTP) + nombreDestinoFTPIntermedio);
				ftp.storeFile(formatearRuta(dirDestinoFTP) + nombreDestinoFTPIntermedio, fis);
			}
			else {
				log.debug("Archivo Sin TMP: " + formatearRuta(dirDestinoFTP) + nombreDestinoFTP);
				ftp.storeFile(formatearRuta(dirDestinoFTP) + nombreDestinoFTP, fis);
			}	
			
			// Verificamos que se haya podido copiar el archivo en la Red
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new StoringFileOverFtpException();
			}
		} catch (InterfazException ex) {
			try {
				// Cerrando flujo
				fis.close();
			} catch (Exception e) {
				throw e;
			}
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				// Cerrando flujo
				fis.close();
			} catch (Exception e) {
				throw e;
			}
			throw ex;
		} finally {
			try {
				// Cerrando flujo
				fis.close();
				if (indicadorTemporal) {
					log.debug("Archivo TMP renombrado");
					ftp.rename(formatearRuta(dirDestinoFTP) + nombreDestinoFTPIntermedio, formatearRuta(dirDestinoFTP) + nombreDestinoFTP);
				}
				
			} catch (Exception e) {
				throw e;
			}
		}
	}

	/**
	 * 
	 * Mueve Archivo guardado en el FTP al directorio destino en el FTP
	 * 
	 * @param ftp
	 *            Conexion ftp
	 * 
	 * @param dirOrigen
	 *            Directorio Origen ubicado en el FTP
	 * 
	 * @param archivo
	 *            Nombre de Archivo a mover
	 * 
	 * @param dirDestino
	 *            Directorio Destino ubicando en el FTP
	 * 
	 */
	public void moverArchivo(String dirOrigen, String archivo, String dirDestino)
			throws Exception {
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP(dirOrigen);

			/* Grabando en el Directorio Destino */
			String origen = formatearRuta(dirOrigen) + archivo;
			String destino = formatearRuta(dirDestino) + archivo;
			if (!ftp.rename(origen, destino)) {
				throw new StoringFileOverFtpException();
			}

			// Verificamos que se haya podido copiar el archivo
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new StoringFileOverFtpException();
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * Elimina Archivo guardado en el FTP
	 * 
	 * @param ftp
	 *            Conexion ftp
	 * 
	 * @param directorio
	 *            Directorio en el FTP donde se encuentra unbicado el archivo
	 * 
	 * @param archivo
	 *            Nombre de Archivo a eliminar
	 * 
	 */
	public void eliminarArchivo(String directorio, String archivo)
			throws Exception {
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP(directorio);

			/* Eliminado Archivo */
			if (!ftp.deleteFile(archivo)) {
				throw new StoringFileOverFtpException();
			}

			// Verificamos que se haya podido eliminar el archivo
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new StoringFileOverFtpException();
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * Busca Archivo guardado en el FTP
	 * 
	 * @param ftp
	 *            Conexion ftp
	 * 
	 * @param directorio
	 *            Directorio en el FTP donde se encuentra unbicado el archivo
	 * 
	 * @param nombreArchivoBuscar
	 *            Nombre de Archivo a buscar
	 * 
	 * @param extension
	 *            extension del archivo
	 * 
	 * @param tipo
	 *            Tipo de Nombre de Archivo: Variable o Fijo
	 * 
	 * @return Nombre de Archivo encontrado
	 */
	public String buscarArchivo(String directorio, String nombreArchivoBuscar,
			String extension, String tipo) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'buscarArchivo' method");
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP(directorio);

			/* Obteniendo Archivo */
			ftp.setFileType(FTP.ASCII_FILE_TYPE);
			FTPFile[] archivos = ftp.listFiles();
			
			for (int i = 0; i < archivos.length; i++) {
				String nombreArchivo = archivos[i].getName();
				if (tipo.equalsIgnoreCase(Constants.ARCHIVO_VARIABLE)) {
					if (esNombreArchivoVariable(nombreArchivo,
							nombreArchivoBuscar, extension)) {
						log.debug("Se encontro el archivo de nombre variable: "
								+ nombreArchivo);
						return nombreArchivo;
					}	
				} else if (tipo.equalsIgnoreCase(Constants.ARCHIVO_FIJO)) {
					if (StringUtils.isBlank(extension)) {
						if (StringUtils.equalsIgnoreCase(nombreArchivo, nombreArchivoBuscar)) {
							log.debug("Se encontro el archivo de nombre fijo: "
									+ nombreArchivo);
							return nombreArchivo;
						}
					}
					
					if (StringUtils.equalsIgnoreCase(nombreArchivo,
							nombreArchivoBuscar
									+ Constants.DELIMITADOR_EXTENSION_ARCHIVO
									+ extension)) {
						log.debug("Se encontro el archivo de nombre fijo: "
								+ nombreArchivo);
						return nombreArchivo;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		log.error("No se encontro el archivo: " + nombreArchivoBuscar
				+ " en el directorio del FTP");
		return null;
	}
	
	
	
	/**
	 * Busca Archivo guardado en el FTP y de acuerdo al nro de ocurrencia
	 * @param directorio
	 * @param nombreArchivoBuscar
	 * @param extension
	 * @param tipo
	 * @param ocurrencia
	 * @return
	 * @throws Exception
	 */
	public String buscarArchivo(String directorio, 
			String nombreArchivoBuscar,
			String extension, 
			String tipo,
			int ocurrencia) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'buscarArchivo' method");
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP(directorio);

			/* Obteniendo Archivo */
			ftp.setFileType(FTP.ASCII_FILE_TYPE);
			FTPFile[] archivos = ftp.listFiles();
			int contador = 0;
			for (int i = 0; i < archivos.length; i++) {
				String nombreArchivo = archivos[i].getName();
				if (tipo.equalsIgnoreCase(Constants.ARCHIVO_VARIABLE)) {
					if (esNombreArchivoVariable(nombreArchivo, 	nombreArchivoBuscar, extension)) {
						log.debug("Se encontro el archivo de nombre variable: " + nombreArchivo);
						if (contador == ocurrencia)
							return nombreArchivo;
						contador++;
					}	
				} else if (tipo.equalsIgnoreCase(Constants.ARCHIVO_FIJO)) {
					if (StringUtils.isBlank(extension)) {
						if (StringUtils.equalsIgnoreCase(nombreArchivo, nombreArchivoBuscar)) {
							log.debug("Se encontro el archivo de nombre fijo: " + nombreArchivo);
							if (contador == ocurrencia)
								return nombreArchivo;
							contador++;
						}
					}
					
					if (StringUtils.equalsIgnoreCase(nombreArchivo, nombreArchivoBuscar + Constants.DELIMITADOR_EXTENSION_ARCHIVO + extension)) {
						log.debug("Se encontro el archivo de nombre fijo: " + nombreArchivo);
						if (contador == ocurrencia)
							return nombreArchivo;
						contador++;
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		log.error("No se encontro el archivo: " + nombreArchivoBuscar
				+ " en el directorio del FTP");
		return null;
	}
	
	
	/**
	 * Obtiene lista de Archivos del FTP en base al nombre del Archivo a buscar
	 * @param directorio
	 * @param nombreArchivoBuscar
	 * @param extension
	 * @param tipo
	 * @param ocurrencia
	 * @return
	 * @throws Exception
	 */
	public List<String> obtenerListaArchivo(String directorio, 
			String nombreArchivoBuscar,
			String extension, 
			String tipo) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'buscarArchivo' method");
		List<String> listaArchivos = new ArrayList<String>();
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP(directorio);

			/* Obteniendo Archivo */
			ftp.setFileType(FTP.ASCII_FILE_TYPE);
			FTPFile[] archivos = ftp.listFiles();
			int contador = 0;
			for (int i = 0; i < archivos.length; i++) {
				String nombreArchivo = archivos[i].getName();
				if (tipo.equalsIgnoreCase(Constants.ARCHIVO_VARIABLE)) {
					if (esNombreArchivoVariable(nombreArchivo, 	nombreArchivoBuscar, extension)) {
						listaArchivos.add(nombreArchivo);
						contador++;
					}	
				} else if (tipo.equalsIgnoreCase(Constants.ARCHIVO_FIJO)) {
					if (StringUtils.isBlank(extension)) {
						if (StringUtils.equalsIgnoreCase(nombreArchivo, nombreArchivoBuscar)) {
							listaArchivos.add(nombreArchivo);
							contador++;
						}
					}
					
					if (StringUtils.equalsIgnoreCase(nombreArchivo, nombreArchivoBuscar + Constants.DELIMITADOR_EXTENSION_ARCHIVO + extension)) {
						listaArchivos.add(nombreArchivo);
						contador++;
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		log.error("No se encontro el archivo: " + nombreArchivoBuscar
				+ " en el directorio del FTP");
		return listaArchivos;
	}
	

	/**
	 * 
	 * Genera Archivo en el FTP
	 * 
	 * @param directorio
	 *            Nombre del directorio a generar el Archivo
	 * 
	 * @param numeroLote
	 *            Numero del archivo a generarse
	 * 
	 * @param strlog
	 *            Contiene el contenido a ser escrito en el archivo log
	 * 
	 */
	public void generarArchivo(String directorio, String archivo,
			StringBuffer strlog) throws Exception {
		OutputStream fout = null;
		OutputStream bout = null;
		OutputStreamWriter out = null;

		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException e = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				e.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw e;
			}

			// Generando Archivo
			fout = ftp.storeFileStream(formatearRuta(directorio) + archivo);
			bout = new BufferedOutputStream(fout);
			out = new OutputStreamWriter(bout);
			out.write(strlog.toString());
		} catch (Exception e) {
			try {
				// Cerrando flujo
				out.flush();
				out.close();
			} catch (Exception e2) {
				throw e2;
			}
			throw e;
		} finally {
			try {
				// Cerrando flujo
				out.flush();
				out.close();
			} catch (Exception e) {
				throw e;
			}
		}
	}

	/**
	 * Funcion que desconecta FTP
	 * 
	 * @param ftp
	 *            FTP logueado
	 */
	public void cerrarFTP() throws InterfazException {
		try {
			// Desloguearse
			ftp.logout();

			// Desconectarse
			ftp.disconnect();
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}

	/**
	 * @param nombreArchivoVariable
	 * @param parteFija
	 * @param extension
	 * @return
	 */
	private boolean esNombreArchivoVariable(String nombreArchivoVariable,
			String parteFija, String extension) {
		boolean result = false;
		if (StringUtils.isBlank(extension)) {
			result = nombreArchivoVariable.toUpperCase().startsWith(
					parteFija.toUpperCase());
			return result;
		}
		result = nombreArchivoVariable.toUpperCase().startsWith(
				parteFija.toUpperCase())
				&& nombreArchivoVariable.toUpperCase().endsWith(
						Constants.DELIMITADOR_EXTENSION_ARCHIVO
								+ extension.toUpperCase());
		return result;
	}

	/**
	 * 
	 * Busca Archivo guardado en el FTP en el Directorio de Entrada
	 * 
	 * @param ftp
	 *            Conexion ftp
	 * 
	 * @param interfaz
	 *            Interfaz con los datos a buscar
	 * 
	 * @return Nombre de Archivo encontrado
	 */
	public String buscarArchivoEntrada(Interfaz interfaz) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'buscarArchivoEntrada' method");
		try {
			String nombreArchivoRetorno = buscarArchivo(interfaz
					.getDirectorioEntradaSalida(), interfaz
					.getNombreArchivoEntradaSalida(), interfaz
					.getExtensionArchivoDescripcion(), interfaz
					.getTipoNombreArchivo());
			if (StringUtils.isEmpty(nombreArchivoRetorno))
				throw new Exception();
			log.debug("Nombre archivo entrada FTP = " + nombreArchivoRetorno);
			return nombreArchivoRetorno;
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw e;
		}
	}
	
	/**
	 * Busca Archivo guardado en el FTP en el Directorio de Entrada y de acuerdo al nro de ocurrencia
	 * @param interfaz
	 * @param ocurrencia
	 * @return
	 * @throws Exception
	 */
	public String buscarArchivoEntrada(Interfaz interfaz, int ocurrencia) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'buscarArchivoEntrada' method");
		try {
			String nombreArchivoRetorno = buscarArchivo(
					interfaz.getDirectorioEntradaSalida(), 
					interfaz.getNombreArchivoEntradaSalida(), 
					interfaz.getExtensionArchivoDescripcion(), 
					interfaz.getTipoNombreArchivo(),
					ocurrencia);
			if (StringUtils.isEmpty(nombreArchivoRetorno))
				return "-1";
			log.debug("Nombre archivo entrada FTP = " + nombreArchivoRetorno);
			return nombreArchivoRetorno;
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw e;
		}
	}
	

	/**
	 * 
	 * Copia Archivo guardado en el FTP en el Directorio Entrada al directorio
	 * de Red
	 * 
	 * @param interfaz
	 *            Interfaz con los datos a buscar
	 * @param nombreArchivoEntrada
	 *            Nombre de Archivo de Entrada a copiar
	 * @param ftp
	 *            Conexion ftp
	 * 
	 */
	public void generarTemporal(Interfaz interfaz, String nombreArchivoEntrada)
			throws Exception {
		try {
			copiarArchivoFTPaRed(interfaz.getDirectorioEntradaSalida(),
					nombreArchivoEntrada, interfaz.getDirectorioTemporal());
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_TEMPORAL);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw e;
		}
	}

	/**
	 * 
	 * Elimina Archivo guardado en el FTP en el Directorio Entrada
	 * 
	 * @param ftp
	 *            Conexion ftp
	 * 
	 * @param interfaz
	 *            Interfaz con los datos a buscar
	 * 
	 * @param nombreArchivoEntrada
	 *            Nombre de Archivo de Entrada a eliminar
	 * 
	 */
	public void eliminarArchivoEntrada(Interfaz interfaz,
			String nombreArchivoEntrada) throws Exception {
		try {
			eliminarArchivo(interfaz.getDirectorioEntradaSalida(),
					nombreArchivoEntrada);
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_BORRAR_ARCHIVO_FTP_REAL);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_REAL);
			throw e;
		}
	}

	/**
	 * 
	 * Busca Archivo guardado en el FTP
	 * 
	 * @param ftp
	 *            Conexion ftp
	 * 
	 * @param directorio
	 *            Directorio en el FTP donde se encuentra unbicado el archivo
	 * 
	 * @param nombreArchivoBuscar
	 *            Nombre de Archivo a buscar
	 * 
	 * @param extension
	 *            extension del archivo
	 * 
	 * @param tipo
	 *            Tipo de Nombre de Archivo: Variable o Fijo
	 * 
	 * @return Nombre de Archivo encontrado
	 */
	public ArrayList buscarListaArchivo(String directorio, String nombreArchivoBuscar,
			String extension, String tipo) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'buscarListaArchivo' method");
		
		ArrayList listaArchivos = new ArrayList();
		
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP(directorio);

			/* Obteniendo Archivo */
			FTPFile[] archivos = ftp.listFiles();
			
			for (int i = 0; i < archivos.length; i++) {
				String nombreArchivo = archivos[i].getName();
				if (tipo.equalsIgnoreCase(Constants.ARCHIVO_VARIABLE)) {
					if (esNombreArchivoVariable(nombreArchivo,
							nombreArchivoBuscar, extension)) {
						log.debug("Se encontro el archivo de nombre variable: "
								+ nombreArchivo);
						listaArchivos.add(nombreArchivo);
					}	
				} else if (tipo.equalsIgnoreCase(Constants.ARCHIVO_FIJO)) {
					if (StringUtils.equalsIgnoreCase(nombreArchivo,
							nombreArchivoBuscar
									+ Constants.DELIMITADOR_EXTENSION_ARCHIVO
									+ extension)) {
						log.debug("Se encontro el archivo de nombre fijo: "
								+ nombreArchivo);
						listaArchivos.add(nombreArchivo);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return listaArchivos;
	}

	/**
	 * Obtenemos la lista de archivos de un directorio del servidor FTP 
	 * 
	 * @param directorio
	 * @return
	 * @throws Exception
	 */
	public ArrayList obtenerListaArchivo(String directorio) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'obtenerListaArchivo' method");
		
		ArrayList listaArchivos = new ArrayList();
		
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP(directorio);

			/* Obteniendo Archivo */
			FTPFile[] archivos = ftp.listFiles();
			
			for (int i = 0; i < archivos.length; i++) {
				String nombreArchivo = archivos[i].getName();
				listaArchivos.add(nombreArchivo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return listaArchivos;
	}

	/**
	 * 
	 * Elimina Directorio guardado en el FTP, siempre y cuando este vacio
	 * 
	 * @param ftp
	 *            Conexion ftp
	 * 
	 * @param directorio
	 *            Directorio en el FTP donde se encuentra unbicado el archivo
	 * 
	 */
	public void eliminarDirectorio(String directorio)
			throws Exception {
		try {
			// Verificando si existe conexion al FTP
			if (!ftp.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_FTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			//obtenemos todos los archivos del directorio
			ArrayList listaArchivos = obtenerListaArchivo(directorio);
			
			//vamos borrando todos los archivos encontrados
			for(int i=0; i < listaArchivos.size(); i++) {
				String archivo = (String)listaArchivos.get(i);
				eliminarArchivo(directorio, archivo);
			}

			// Ir al Subdirectorio de Trabajo
			cambiarDirectorioFTP("/");
			
			/* Eliminado Directorio */
			if (!ftp.removeDirectory(directorio)) {
				throw new StoringFileOverFtpException();
			}

			// Verificamos que se haya podido eliminar el archivo
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new StoringFileOverFtpException();
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Copia un directorio de un Servidor de Red a un Servidor FTP
	 * 
	 * @param dirOrigenRed
	 * 			Directorio padre Red donde esta ubicado el directorio a copiar
	 * 
	 * @param nombreDirectorio
	 * 			Nombre del directorio a copiar del servidor de Red al servidor FTP
	 * 
	 * @param dirDestinoFTP
	 * 			Directorio padre FTP donde sera copiado el nuevo directorio
	 * 
	 * @throws Exception
	 */
	public void copiarDirectorioRedaFTP(String dirOrigenRed, String nombreDirectorio,
			String dirDestinoFTP) throws Exception {
		try {
			cambiarDirectorioFTP(dirDestinoFTP);
			
			ftp.makeDirectory(nombreDirectorio);

			String dirRed = dirOrigenRed + nombreDirectorio;
			String directorioFTP = dirDestinoFTP + nombreDirectorio;
			
			File dir = new File(dirRed);
			FilenameFilter filenameFilter = new FilenameFilter() {
				public boolean accept(File dir, String name){
			        return true;
			    }
			};

			String[] fileNames = dir.list(filenameFilter);
			//lo vamos añadiendo al PDF
			for(int i=0; i < fileNames.length; i++) {	
				copiarArchivoRedaFTP(dirRed, fileNames[i], directorioFTP);
			}	
			
		} catch (InterfazException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
}
