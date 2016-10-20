package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.util.InterfazNombreVariableFilter;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.util.FTPUtil;
import biz.belcorp.ssicc.service.util.FileUtil;
import biz.belcorp.ssicc.service.util.SFTPClientWrapper;

/* NUEVA REESTRUCTURACION NSSICC */
/**
 * Clase que encapsula los parametros para la ejecucion de las Interfaces SiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * 
 */
public class InterfazParams implements Cloneable {
	private Interfaz interfaz;   

	private Historico historico;

	private Usuario usuario;

	private Map queryParams;

	private StringBuffer logBuffer;

	private InterfazException interfazException;
	
	private boolean ejecucionPaqueteInterfaz = false; //NSSICC
	
	public InterfazParams() {
		logBuffer = new StringBuffer();
	}

	String indicadorFTP = "0";
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException {
		InterfazParams obj = null;
	    obj = (InterfazParams)super.clone();
	    obj.queryParams = new HashMap();
	    //obj.interfaz = new Interfaz();
		return obj;
    }
	
	/**
	 * Agrega el mensaje al log de la interfaz con un cambio de linea.
	 * 
	 * @param message
	 *            mensaje al log de la interfaz
	 */
	public void appendLog(String message) {
		logBuffer.append(message).append(System.getProperty("line.separator"));
	}

	/**
	 * @return String con el log de la interfaz
	 */
	public String getLog() {
		return logBuffer.toString();
	}

	// Logica de rutas de archivos para las interfaces
	// TODO Refactoring: estos metodos deben pasar a un Strategy

	/**
	 * Devuelve el directorio temporal
	 * 
	 * @return String con el directorio temporal formateado
	 */
	public String getTempDirectory() {
		return FileUtil.formatDirectory(interfaz.getDirectorioTemporal());
	}

	/**
	 * Devuelve el nombre temporal de la interfaz de tipo fijo o variable, sin
	 * extension.
	 * 
	 * @return String con el nombre temporal sin extension
	 */
	public String getTempName() {
		// Tipo de nombre fijo
		if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(
				Constants.ARCHIVO_FIJO)) {
			return interfaz.getNombreArchivoEntradaSalida();
		}

		// Tipo de nombre variable
		else {
			if (StringUtils
					.isNotBlank(interfaz.getNombreArchivoEntradaSalida())) {
				return interfaz.getNombreArchivoEntradaSalida()
						+ getNumeroLote();
			} else {
				return interfaz.getCodigo() + "_" + getNumeroLote();
			}
		}
	}

	/**
	 * Devuelve el nombre temporal de la interfaz con su extension configurada
	 * en la interfaz.
	 * 
	 * @return [tempName].[extension]
	 */
	public String getTempFileName() {
//		return getTempName() + Constants.DELIMITADOR_EXTENSION_ARCHIVO
//				+ interfaz.getExtensionArchivoDescripcion();
		
		return interfaz.getNombreArchivo(this.getTempName());
	}

	public String getTempPath() {
		return getTempDirectory() + getTempFileName();
	}

	public String getProcessDirectory() {
		return FileUtil.formatDirectory(interfaz.getDirectorioProc());
	}

	/**
	 * Devuelve el nombre temporal de la interfaz con extension ZIP.
	 * 
	 * @return [tempName].ZIP
	 */
	public String getTempZipFileName() {
		return getTempName() + Constants.DELIMITADOR_EXTENSION_ARCHIVO
				+ Constants.EXTENSION_ZIP;
	}

	public String getTempZipPath() {
		return FileUtil.formatDirectory(interfaz.getDirectorioTemporal())
				+ getTempZipFileName();
	}

	/**
	 * Devuelve el nombre temporal de la interfaz con extension TMP.
	 * 
	 * @return [tempName].TMP
	 */
	public String getTempProcessFileName() {
		return getTempName() + Constants.DELIMITADOR_EXTENSION_ARCHIVO
				+ Constants.EXTENSION_TMP;
	}

	public String getArchivoEntradaFileName() {
		String result = "";
		// Tipo de nombre fijo
		if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(
				Constants.ARCHIVO_FIJO)) {
			/*
			result = interfaz.getNombreArchivoEntradaSalida()
					+ Constants.DELIMITADOR_EXTENSION_ARCHIVO
					+ interfaz.getExtensionArchivoDescripcion();
			*/
			result = interfaz.getNombreArchivo(interfaz.getNombreArchivoEntradaSalida());
		}
		// Tipo de nombre variable, realizo la busqueda en el directorio de
		// entrada
		else {
			File dir = new File(FileUtil.formatDirectory(interfaz
					.getDirectorioEntradaSalida()));
			FilenameFilter filenameFilter = new InterfazNombreVariableFilter(
					interfaz.getNombreArchivoEntradaSalida(), interfaz
							.getExtensionArchivoDescripcion());

			String[] fileNames = dir.list(filenameFilter);
			if (fileNames != null && fileNames.length > 0) {
				Arrays.sort(fileNames);
				result = fileNames[0];
			}
		}
		return result;
	}

	public String getArchivoEntradaPath() {
		return FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida())
				+ getArchivoEntradaFileName();
	}

	public String getArchivoSalidaPath() {
		return FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida())
				+ getArchivoSalidaFileName();
	}

	public String getArchivoSalidaFileName() {
		String nombre;

		//Verificamos si el archivo tiene secuencia
		String secuencia = MapUtils.getString(queryParams, Constants.INTERFAZ_XRX_NUMERO_SECUENCIA_LOTE, "");
		String flagNOSeparador = MapUtils.getString(queryParams, Constants.INTERFAZ_XRX_NO_USAR_SEPARADOR_SECUENCIA, "");
		
		// Tipo de nombre fijo
		if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(
				Constants.ARCHIVO_FIJO)) {
			
			if(StringUtils.isBlank(secuencia))
			{
			nombre = interfaz.getNombreArchivoEntradaSalida();
		}
			else
			{
				if(StringUtils.equals(flagNOSeparador, Constants.ESTADO_ACTIVO))
					nombre = interfaz.getNombreArchivoEntradaSalida() + secuencia;
				else
				nombre = interfaz.getNombreArchivoEntradaSalida() + "_" + secuencia;				
			}			
		}

		// Tipo de nombre variable, concatena el numero de lote
		else {
			
			if(StringUtils.isBlank(secuencia))
			{
			if (StringUtils
					.isNotBlank(interfaz.getNombreArchivoEntradaSalida())) {
				nombre = interfaz.getNombreArchivoEntradaSalida()
						+ getNumeroLote();
			} else {
				nombre = interfaz.getCodigo() + "_" + getNumeroLote();

			}
		}
			else
			{
				if (StringUtils.isNotBlank(interfaz.getNombreArchivoEntradaSalida())) {
					
					if(StringUtils.equals(flagNOSeparador, Constants.ESTADO_ACTIVO))
						nombre = interfaz.getNombreArchivoEntradaSalida()+ getNumeroLote() + secuencia;
					else
						nombre = interfaz.getNombreArchivoEntradaSalida()+ getNumeroLote() + "_" + secuencia;
				} 
				else {
					
					if(StringUtils.equals(flagNOSeparador, Constants.ESTADO_ACTIVO))
						nombre = interfaz.getCodigo() + "_" + getNumeroLote() + secuencia;
					else
					nombre = interfaz.getCodigo() + "_" + getNumeroLote() + "_" + secuencia;
				}
			}
		}

		// Concateno la extension
		if (interfaz.comprimir()) {
			return nombre + Constants.DELIMITADOR_EXTENSION_ARCHIVO
					+ Constants.EXTENSION_ZIP;
		} else {
			return nombre + Constants.DELIMITADOR_EXTENSION_ARCHIVO
					+ interfaz.getExtensionArchivoDescripcion();
		}
	}

	/**
	 * Obtiene el Path con el nombre de archivo Fijo, tomando como 
	 * nombre del archivo el nombre del archivo de entrada/salida
	 * @return
	 */
	public String getHistoricoPathNombreFijo() {
		return FileUtil.formatDirectory(interfaz.getDirectorioHistorico())
				+ getArchivoSalidaFileName();
	}
	
	public String getHistoricoPath() {
		return FileUtil.formatDirectory(interfaz.getDirectorioHistorico())
				+ getHistoricoFileName();
	}

	public String getHistoricoFileName() {
		String nombre;

		if (StringUtils
				.isNotBlank(interfaz.getNombreArchivoEntradaSalida())) {
			nombre = interfaz.getNombreArchivoEntradaSalida()
					+ getNumeroLote();
		} else {
			nombre = interfaz.getCodigo() + "_" + getNumeroLote();
		}

		// Concateno la extension
		if (interfaz.comprimir()) {
			return nombre + Constants.DELIMITADOR_EXTENSION_ARCHIVO
					+ Constants.EXTENSION_ZIP;
		} else {
//			return nombre + Constants.DELIMITADOR_EXTENSION_ARCHIVO
//					+ interfaz.getExtensionArchivoDescripcion();
			return interfaz.getNombreArchivo(nombre);
		}
		
//		return getArchivoSalidaFileName();
	}

	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio de entrada de interfaces
	 * @return
	 */
	public List getListArchivosEntrada() throws Exception {
		List archivosList = new ArrayList();
		File direc = null;
		
		if (StringUtils.isBlank(interfaz.getDirectorioEntradaSalida())) {
			throw new Exception("Debe ingresar valor en el Campo de Directorio de Entrada / Salida de la Interfaz");
		}

		//Se verifica si el archivo se obtiene x FTP
		if (interfaz.getFlagEnvioArchivo().trim().equals(Constants.ENVIO_FTP) &&
			interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA) &&
		   !interfaz.getCodigoSistema().equals("PRI")){

			//Se obtiene el archivo del FTP y se guarda en el directorio Temporal
			try {
				FTPUtil ftpUtil = new FTPUtil();
				ftpUtil.loguearFTP(interfaz);

				String archivoEntradaFileName = ftpUtil.buscarArchivoEntrada(interfaz);

				ftpUtil.copiarArchivoFTPaRed(interfaz.getDirectorioEntradaSalida(),
											 archivoEntradaFileName,
											 interfaz.getDirectorioTemporal());

				direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()));

				ftpUtil.cerrarFTP();
				indicadorFTP = "1";
			}
			catch (Exception e) {
				direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()));
				indicadorFTP = "0";
			}
		}
		
		/* INI SA PER-SiCC-2012-0840 */
		//Se verifica si el archivo se obtiene x SFTP
		if (interfaz.getFlagEnvioArchivo().trim().equals(Constants.ENVIO_SFTP) &&
			interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA) &&
		   !interfaz.getCodigoSistema().equals("PRI")){

			//Se obtiene el archivo del SFTP y se guarda en el directorio Temporal
			try {
				SFTPClientWrapper sftpUtil = new SFTPClientWrapper();
				sftpUtil.loguearSFTP(interfaz);

				String archivoEntradaFileName = sftpUtil.buscarArchivoEntrada(interfaz);

				sftpUtil.copiarArchivoSFTPaRed(interfaz.getDirectorioEntradaSalida(),
											 archivoEntradaFileName,
											 interfaz.getDirectorioTemporal());

				direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()));

				sftpUtil.disconnect();
				indicadorFTP = "1";
			}
			catch (Exception e) {
				direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()));
				indicadorFTP = "0";
			}
		}
		/* FIN SA PER-SiCC-2012-0840 */
		
		// Este es el caso x RED
		else if(interfaz.getFlagEnvioArchivo().trim().equals(Constants.ENVIO_RED) &&
				interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA) &&
				!interfaz.getCodigoSistema().equals("PRI")){
			direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()));
			indicadorFTP = "0";
		}
		// Este es el caso Mixto
		else if(interfaz.getFlagEnvioArchivo().trim().equals(Constants.ENVIO_MIXTO) &&
				interfaz.getTipoGeneracion().equals(Constants.TIPO_GENERACION_UNITARIA) &&
				!interfaz.getCodigoSistema().equals("PRI")){
			
			if(interfaz.getFlagDirectorioEntradaSalida().trim().equalsIgnoreCase(Constants.ENVIO_FTP)){
				//Se obtiene el archivo del FTP y se guarda en el directorio Temporal
				try {
					FTPUtil ftpUtil = new FTPUtil();
					ftpUtil.loguearFTP(interfaz);

					String archivoEntradaFileName = ftpUtil.buscarArchivoEntrada(interfaz);

					ftpUtil.copiarArchivoFTPaRed(interfaz.getDirectorioEntradaSalida(),
												 archivoEntradaFileName,
												 interfaz.getDirectorioTemporal());

					direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioTemporal()));

					ftpUtil.cerrarFTP();
					indicadorFTP = "1";
				}
				catch (Exception e) {
					direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()));
					indicadorFTP = "0";
				}
			}else if(interfaz.getFlagDirectorioEntradaSalida().trim().equalsIgnoreCase(Constants.ENVIO_RED)){
				direc = new File(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida()));
				indicadorFTP = "0";
			}
		}

		//File direc = new File("D:/=Dennys=/prueba2");
		// Tipo de nombre fijo
		if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(Constants.ARCHIVO_FIJO)) {
			try {
				File[] files = direc.listFiles();
				Arrays.sort(files);
				for (int j = 0; j < files.length; j++) {
					File f = files[j];
					if(f.getName().equals(interfaz.getNombreArchivoEntradaSalida()
											+ Constants.DELIMITADOR_EXTENSION_ARCHIVO
											+ interfaz.getExtensionArchivoDescripcion())){
						LabelArchivos labelArchivos = new LabelArchivos();
						labelArchivos.setNombreArchivo(f.getName());
						labelArchivos.setPesoArchivo(f.length()+" bytes");
						if(archivosList.size()==0)
							labelArchivos.setIndicadorEjecucion(Constants.NUMERO_UNO);
						else 
							labelArchivos.setIndicadorEjecucion(Constants.NUMERO_CERO);
						archivosList.add(labelArchivos);
					}
					
				}
			} catch (Exception e) {
			}
		}
		// Tipo de nombre variable, realizo la busqueda en el directorio de entrada
		else {
			FilenameFilter filenameFilter = new InterfazNombreVariableFilter(interfaz.getNombreArchivoEntradaSalida(), 
					                                                         interfaz.getExtensionArchivoDescripcion());
			try {
				File[] files = direc.listFiles(filenameFilter);
				Arrays.sort(files);
				for (int j = 0; j < files.length; j++) {
					File f = files[j];
					LabelArchivos labelArchivos = new LabelArchivos();
					labelArchivos.setNombreArchivo(f.getName());
					//LineNumberReader lineCounter = new LineNumberReader(new InputStreamReader(new FileInputStream(f)));
					
					labelArchivos.setPesoArchivo(f.length() +" Bytes");
					if(j==0)
						labelArchivos.setIndicadorEjecucion(Constants.NUMERO_UNO);
					else 
						labelArchivos.setIndicadorEjecucion(Constants.NUMERO_CERO);
					archivosList.add(labelArchivos);
				}
			} catch (Exception e) {
			}
		}
		//cambio
		getListLineasArchivosEntrada(archivosList);
		
		//Se elimina el archivo temporal
		if(Constants.NUMERO_UNO.equals(this.indicadorFTP)){
			borrarArchivo(archivosList);
		}
		return archivosList;
	}
	
	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio de entrada de interfaces
	 * @param archivosList
	 * @return
	 */
	public List getListLineasArchivosEntrada(List archivosList) {
		long lNumeroLineas;
		String sCadena;
		FileReader fr;

		for (int i = 0; i < archivosList.size(); i++) {
			lNumeroLineas = 0;
			LabelArchivos labelArchivos = (LabelArchivos)archivosList.get(i);

			try {
				if(Constants.NUMERO_UNO.equals(this.indicadorFTP)){
					fr = new FileReader(FileUtil.formatDirectory(interfaz.getDirectorioTemporal())+System.getProperty("file.separator")+labelArchivos.getNombreArchivo());	
				}else{
					fr = new FileReader(FileUtil.formatDirectory(interfaz.getDirectorioEntradaSalida())+System.getProperty("file.separator")+labelArchivos.getNombreArchivo());
				}
				//FileReader fr = new FileReader("D:/=Dennys=/prueba2"+System.getProperty("file.separator")+labelArchivos.getNombreArchivo());

				BufferedReader bf = new BufferedReader(fr);
				while ((sCadena = bf.readLine())!=null) {
				  lNumeroLineas++;
				}
				bf.close();
				fr.close();
			} catch (Exception e) {
			}
			labelArchivos.setLineasArchivo(lNumeroLineas+" Lineas");
			labelArchivos.setNumerRegistro(lNumeroLineas);
		}

		return archivosList;
	}
	
	/**
	 * Elimina el archivo traido x FTP de la carpeta temporal
	 * @param archivosList
	 */
	private void borrarArchivo(List archivosList) {
		String path = interfaz.getDirectorioTemporal();
		
		for (int i = 0; i < archivosList.size(); i++) {

			LabelArchivos labelArchivos = (LabelArchivos)archivosList.get(i);
			String nombreArchivo = labelArchivos.getNombreArchivo(); 

			try {
				File file = new File(path, nombreArchivo);
				file.delete();
			}
			catch(Exception ex) {
			}

		}
	}
	
	public String getNumeroLote() {
		if (historico != null)
			return historico.getNumeroLote();
		else
			return null;
	}

	public Interfaz getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	public Map getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * Actualiza el codigo de interfaz en queryParams.
	 */
	public void updateInterfazQueryParams() {
		if (interfaz != null) {
			queryParams.put("codigoInterfaz", interfaz.getCodigo());
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public InterfazException getInterfazException() {
		return interfazException;
	}

	public void setInterfazException(InterfazException interfazException) {
		this.interfazException = interfazException;
	}
	
	public String getArchivoTemporalPaquetePath() {
		return FileUtil.formatDirectory(interfaz.getDirectorioTemporalPaquete())
				+ getArchivoSalidaFileName();
	}
	
	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio que ha generado el Oracle
	 * para el caso de nombres variables
	 * @return
	 */
	public List getListNombreArchivosGenerados(String nombreArchivo, String extension){

		List nombreArchivosList = new ArrayList();
		File direc = new File(FileUtil.formatDirectory(getInterfaz().getDirectorioProc()));
		
		FilenameFilter filenameFilter = new InterfazNombreVariableFilter(nombreArchivo, extension);		
		File [] files = direc.listFiles(filenameFilter);
		
		if(files != null && files.length > 0)
		{
			for(int i=0; i<files.length; i++)
				nombreArchivosList.add(files[i].getAbsolutePath());
		}
		
		return nombreArchivosList;
	}

	/**
	 * Metodo que devuelve el listado de archivos que estan en el directorio Entrada Salida
	 * para el caso de nombres variables
	 * @return
	 */
	public List getListNombreArchivosGeneradosEntradaSalida(String nombreArchivo, String extension){

		List nombreArchivosList = new ArrayList();
		File direc = new File(FileUtil.formatDirectory(getInterfaz().getDirectorioEntradaSalida()));
		
		FilenameFilter filenameFilter = new InterfazNombreVariableFilter(nombreArchivo, extension);		
		File [] files = direc.listFiles(filenameFilter);
		
		if(files != null && files.length > 0)
		{
			for(int i=0; i<files.length; i++)
				nombreArchivosList.add(files[i].getAbsolutePath());
		}
		
		return nombreArchivosList;
	}


	/**
	 * @return the indicadorFTP
	 */
	public String getIndicadorFTP() {
		return indicadorFTP;
	}

	/**
	 * @param indicadorFTP the indicadorFTP to set
	 */
	public void setIndicadorFTP(String indicadorFTP) {
		this.indicadorFTP = indicadorFTP;
	}

	/**
	 * @return the ejecucionPaqueteInterfaz
	 */
	public boolean isEjecucionPaqueteInterfaz() {
		return ejecucionPaqueteInterfaz;
	}

	/**
	 * @param ejecucionPaqueteInterfaz the ejecucionPaqueteInterfaz to set
	 */
	public void setEjecucionPaqueteInterfaz(boolean ejecucionPaqueteInterfaz) {
		this.ejecucionPaqueteInterfaz = ejecucionPaqueteInterfaz;
	}
	
	
	
	
	
}
