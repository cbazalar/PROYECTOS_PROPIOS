package biz.belcorp.ssicc.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * Clase Utilitaria para el envio de archivos via SFTP
 * @author Sergio Buchelli
 *
 */
public class SFTPClientWrapper {
	protected final Log log = LogFactory.getLog(getClass());
	private JSch sftpClient;
	private Session session;
	private ChannelSftp channel;
	
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(ChannelSftp channel) {
		this.channel = channel;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Inicializa el SFTP
	 * @param server
	 * @param port
	 * @param user
	 * @param password
	 * @throws Exception
	 */
	public SFTPClientWrapper(String server, int port, String user,
			String password) throws Exception {
		try {
			sftpClient = new JSch();
			session = sftpClient.getSession(user, server, port);
			session.setPassword(password);
			// El SFTP requiere un intercambio de claves
			// con esta propiedad le decimos que acepte la clave
			// sin pedir confirmacin
			Properties prop = new Properties();
			prop.put("StrictHostKeyChecking", "no");
			session.setConfig(prop);
			session.connect();
			if (session.isConnected()){
	    		// Abrimos el canal de sftp y conectamos
	    		channel = (ChannelSftp) session.openChannel("sftp");
	    		channel.connect();
				log.info("Succesfully logged to " + server);
			}	
		} catch (Exception e) {
			session=null;
			sftpClient=null;
			throw new Exception(e);
		}
	}

	/**
	 * Se recupera archivo de un servidor SFTP en la ruta local
	 * @param localPath
	 * @param remoteDirectory
	 * @param remoteFileName
	 */
	public void retrieveFile(String localPath, String remoteDirectory,
			String remoteFileName) {
		if (log.isDebugEnabled()) {
			log.debug("Retrieving file from SFTP");
			log.debug("remoteDirectory=" + remoteDirectory);
			log.debug("remoteFileName=" + remoteFileName);
		}
		FileOutputStream localStream = null;
		try {
			changeWorkingDirectory(remoteDirectory);
			localStream = new FileOutputStream(localPath);
			channel.get(remoteFileName,localStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQuietly(localStream);
			
		}
	}

    /**
     * Envia el archivo de red a servidor SFTP
     * @param localFile
     * @param remoteDirectory
     * @param remoteFileName
     */
    public void sendFile(File localFile, String remoteDirectory, String remoteFileName) {
        if (log.isDebugEnabled()) {
            log.debug("sendFile file from SFTP");
            log.debug("remoteDirectory=" + remoteDirectory);
            log.debug("remoteFileName=" + remoteFileName);
        }
        FileInputStream localStream = null;
        try {
        	
            changeWorkingDirectory(remoteDirectory);
            localStream = new FileInputStream(localFile);                         
            channel.put(localStream,remoteFileName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(localStream);
        }
    }

	/**
     * Envia el archivo de red a servidor SFTP
     * @param localFile
     * @param remoteDirectory
     * @param remoteFileName
     */
    public void sendFile(File localFile, String remoteDirectory, String remoteFileName, Boolean indicadorCopiadoTemporal) {
        if (log.isDebugEnabled()) {
            log.debug("sendFile file from SFTP");
            log.debug("remoteDirectory=" + remoteDirectory);
            log.debug("remoteFileName=" + remoteFileName);
        }
        FileInputStream localStream = null;
        String remoteFileNameIntermedio = remoteFileName + Constants.DELIMITADOR_EXTENSION_ARCHIVO + Constants.EXTENSION_TMP;
        try {
            changeWorkingDirectory(remoteDirectory);
            localStream = new FileInputStream(localFile);    
            
            if (indicadorCopiadoTemporal) {
	            channel.put(localStream, remoteFileNameIntermedio);
	            channel.rename(remoteFileNameIntermedio, remoteFileName);
            }
            else
            	channel.put(localStream,remoteFileName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(localStream);
        }
    }

	/**
	 * retorta true si cambia diretorio de trabajo, caso contrario false
	 * @param remoteDirectory
	 * @throws SftpException 
	 */
	public boolean changeWorkingDirectory(String remoteDirectory)  {		
		try {
			channel.cd(remoteDirectory);
		} catch (SftpException e) {
			return false;
		}
		return true;
	}

	/**
	 * Elimina archivo del servidro SFTP
	 * @param remoteDirectory
	 * @param remoteFileName
	 */
	public void deleteFile(String remoteDirectory, String remoteFileName) {
		if (log.isDebugEnabled()) {
			log.debug("Deleting file from FTP");
			log.debug("remoteDirectory=" + remoteDirectory);
			log.debug("remoteFileName=" + remoteFileName);
		}

		try {
			changeWorkingDirectory(remoteDirectory);
			channel.rm(remoteFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * se desconecta del servidor SFTP
	 */
	public void disconnect() {
		log.debug("disconnect");
		try {
			channel.disconnect();
			session.disconnect();
			sftpClient = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Cerrando los flujos 
	 * @param stream
	 */
	private void closeQuietly(OutputStream stream) {
		try {
			stream.flush();
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cerrando los flujos 
	 * @param stream
	 */
    private void closeQuietly(InputStream stream) {
        try {
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * @return the sftpClient
	 */
	public JSch getSftpClient() {
		return sftpClient;
	}

	/**
	 * Imprime directorio actual
	 * @return
	 */
	public String printWorkingDirectory() throws Exception {
		return channel.pwd();
	}

	/* INI SA PER-SiCC-2012-0840 */
	/**
	 * Inicializa el SFTP
	 * 
	 */
	public SFTPClientWrapper() {
	}

	/**
	 * Funcion que realiza el logueo al SFTP, sea por diferentes casos:
	 *  1. usuario y password
	 *  2. usuario, llave privada
	 *  3. usuario, llave privada y clave llave privada
	 *  4. usuario, password y llave privada
	 *  
	 * @param interfaz
	 *            Clase que contiene los valores para loguearse al sftp
	 * 
	 */
	public boolean loguearSFTP(Interfaz interfaz) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'loguearSFTP' method");
		try {
			boolean logged = false;
			Integer iPort = new Integer(interfaz.getPuertoFtp());
			
			sftpClient = new JSch();
			
			if(interfaz.getClavePrivadaSFTP() != null) {
				byte[] arregloClavePrivada = interfaz.getClavePrivadaSFTP().getBytes();
								
				if(interfaz.getPasswordClavePrivadaSFTP() != null)  
					sftpClient.addIdentity(null, arregloClavePrivada, null, interfaz.getPasswordClavePrivadaSFTP().getBytes());
				else	
					sftpClient.addIdentity(null, arregloClavePrivada, null, null);
			}
			
	        session = sftpClient.getSession(interfaz.getUsuarioFtp(), interfaz.getServidorFtp(), iPort);
	        if(interfaz.getPasswordFtp() != null)
	        	session.setPassword(interfaz.getPasswordFtp());
			
			// El SFTP requiere un intercambio de claves
			// con esta propiedad le decimos que acepte la clave
			// sin pedir confirmacin
			Properties prop = new Properties();
			prop.put("StrictHostKeyChecking", "no");
			session.setConfig(prop);
			session.connect();
			
			if (session.isConnected()){
	    		// Abrimos el canal de sftp y conectamos
	    		channel = (ChannelSftp) session.openChannel("sftp");
	    		channel.connect();
	    		logged = true;
				log.info("Succesfully logged to " + interfaz.getServidorFtp());
			}	
			
			return logged;
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_LOGUEAR_SFTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}
	
	/**
	 * Busca Archivo guardado en el SFTP en el Directorio de Entrada
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
	 * Busca Archivo guardado en el SFTP
	 * 
	 * @param directorio
	 *            Directorio en el SFTP donde se encuentra unbicado el archivo
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
			if (!channel.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_SFTP);
				error
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			changeWorkingDirectory(directorio);

			/* Obteniendo Archivo */
			Vector archivos = channel.ls("*.*");
			
			for (int i = 0; i < archivos.size(); i++) {
				ChannelSftp.LsEntry entrada= (ChannelSftp.LsEntry)archivos.get(i);
	            
				String nombreArchivo = entrada.getFilename();
				
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
				+ " en el directorio del SFTP");
		return null;
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
	 * Copia Archivo guardado en el SFTP al directorio de Red
	 * 
	 * @param dirOrigenSFTP
	 *            Directorio Origen ubicado en el SFTP
	 * @param archivo
	 *            Nombre de Archivo a copiar
	 * @param dirDestinoRed
	 *            Directorio Destino ubicando en Red
	 * @param ftp
	 *            Conexion sftp
	 * 
	 */
	public void copiarArchivoSFTPaRed(String dirOrigenSFTP, String archivo,
			String dirDestinoRed) throws Exception {
		this.copiarArchivoSFTPaRed(dirOrigenSFTP, archivo, dirDestinoRed,
						archivo);
	}
	
	/**
	 * 
	 * Copia Archivo guardado en el SFTP al directorio de Red
	 * 
	 * @param dirOrigenSFTP
	 *            Directorio Origen ubicado en el SFTP
	 * @param archivo
	 *            Nombre de Archivo a copiar
	 * @param dirDestinoRed
	 *            Directorio Destino ubicando en Red
	 * @param archivoDestino
	 *            Nombre de Archivo destino
	 * @param sftp
	 *            Conexion sftp
	 */
	public void copiarArchivoSFTPaRed(String dirOrigenSFTP, String archivo,
			String dirDestinoRed, String archivoDestino) throws Exception {
		String dirTmp;
		FileOutputStream fos = null;

		try {
			dirTmp = FileUtil.formatDirectory(dirDestinoRed);

			// Verificando si existe conexion al SFTP
			if (!channel.isConnected()) {
				InterfazException error = new InterfazException(
						Constants.INTERFAZSICC_ERROR_LOGUEAR_SFTP);
				error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw error;
			}

			// Ir al Subdirectorio de Trabajo
			changeWorkingDirectory(dirOrigenSFTP);

			/* Grabando en el Directorio en la Red */
			String ruta = dirTmp + archivoDestino;
			fos = new FileOutputStream(ruta);
			log.info("Copying " + archivo + " to " + ruta);
			
			channel.get(archivo, fos);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQuietly(fos);
			
		}
	}
	
	/**
	 * 
	 * Copia Archivo guardado en la Red al Servidor SFTP
	 * 
	 * @param dirOrigenRed
	 *            Directorio Origen ubicado en Red
	 * @param archivo
	 *            Nombre de Archivo a copiar
	 * @param dirDestinoSFTP
	 *            Directorio Destino ubicando en el SFTP
	 * 
	 */
	public void copiarArchivoRedaSFTP(String dirOrigenRed, String archivo,
			String dirDestinoSFTP) throws Exception {
		try {
			String rutaOrigen = FileUtil.formatDirectory(dirOrigenRed)
					+ archivo;

			// Sube los archivos de la carpeta local a la carpeta del SFTP
			File filaOrigen = new File(rutaOrigen);
			this.copiarArchivoRedaSFTP(filaOrigen, dirDestinoSFTP);
		} catch (InterfazException ex) {
			throw ex;
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * 
	 * Copia Archivo guardado en la Red al Servidor SFTP
	 * 
	 * @param fileArchivo
	 *            Archivo a copiar
	 * @param dirDestinoSFTP
	 *            Directorio Destino ubicando en el SFTP
	 * 
	 */
	public void copiarArchivoRedaSFTP(File fileArchivo, String dirDestinoSFTP)
			throws Exception {
		try {
			// Obteniendo nombre del Archivo
			String archivo = fileArchivo.getName();

			// Sube los archivos de la carpeta local a la carpeta del SFTP
			this.sendFile(fileArchivo, dirDestinoSFTP, archivo);
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * 
	 * Copia Archivo guardado en la Red al Servidor SFTP
	 * 
	 * @param fileArchivo
	 *            Archivo a copiar
	 * @param dirDestinoSFTP
	 *            Directorio Destino ubicando en el SFTP
	 * 
	 */
	public void copiarArchivoRedaSFTP(File fileArchivo, String dirDestinoSFTP, Boolean indicadorCopiadoTemporal)
			throws Exception {
		try {
			// Obteniendo nombre del Archivo
			String archivo = fileArchivo.getName();

			// Sube los archivos de la carpeta local a la carpeta del SFTP
			this.sendFile(fileArchivo, dirDestinoSFTP, archivo, indicadorCopiadoTemporal);
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * 
	 * Elimina Archivo guardado en el SFTP en el Directorio Entrada
	 * 
	 * @param sftp
	 *            Conexion sftp
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
			deleteFile(interfaz.getDirectorioEntradaSalida(),
					nombreArchivoEntrada);
		} catch (Exception e) {
			InterfazException error = new InterfazException(
					Constants.INTERFAZSICC_ERROR_BORRAR_ARCHIVO_FTP_REAL);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_REAL);
			throw e;
		}
	}
	/* FIN SA PER-SiCC-2012-0840 */
	
}
