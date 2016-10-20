/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.SocketException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.PercepcionesVtaDirecta;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Delimitador;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.sisicc.model.Formato;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazBELDireccionClientes;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazBELFacturasCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazBELFacturasDetalle;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazBELPorcentajesReferencia;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazBELUbicacionesGeograficas;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazBELUnidadesAtendidas;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazCOMLibretaAhorros;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazCOMLideresNuevas;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazCOMPagoLider;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazGISEnviarDireccionConsultoras;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazGISReporteDireccionConsultoras;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazMyEbelCronogramaFacturacion;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazMyEbelMatrizCampanya;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazMyEbelMovimientosCuentaCorriente;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazREUDocumentosAnulados;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazREUMatrizCampanya;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazREUPeriodosFacturacion;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazRetailComplementoFacturasVentaDirecta;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazRetailFacturasVentaDirecta;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazRetailMatrizCampanya;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazSABFuenteVentasPrevista;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazSABIncentivosConsultoras;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazSABRentabilidadPorZona;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazSAMInicializacionStocks;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.dao.sisicc.util.InterfazNombreVariableFilter;
import biz.belcorp.ssicc.service.sisicc.util.exception.CompressingLocalFileException;
import biz.belcorp.ssicc.service.sisicc.util.exception.CopingFileOverLocalNetworkException;
import biz.belcorp.ssicc.service.sisicc.util.exception.DeletingTemporalFileException;
import biz.belcorp.ssicc.service.sisicc.util.exception.GeneratingLocalFileException;
import biz.belcorp.ssicc.service.sisicc.util.exception.StoringFileOverFtpException;
import biz.belcorp.ssicc.service.util.ExcepcionSistema;

/**
 * @author peextrdelosreyes
 * @deprecated
 */
public class Fichero {

	static final int BUFFER = 4096;

	private String nombreArchivo;

	private List registros;

	private Delimitador delimitador;

	private Formato formato;

	private Interfaz interfaz;

	private NormaInterfaz normaInterfaz;

	private List estructuraArchivoInterfaz;

	private String nombreArchivoTemporal;

	protected final Log log = LogFactory.getLog(getClass());

	private StringBuffer strlog = new StringBuffer();

	/**
	 * @return Returns the nombreArchivo.
	 */
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	/**
	 * @param nombreArchivo
	 *            The nombreArchivo to set.
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	/**
	 * @return Returns the estructuraArchivoInterfaz.
	 */
	public List getEstructuraArchivoInterfaz() {
		return estructuraArchivoInterfaz;
	}

	/**
	 * @return Returns the interfaz.
	 */
	public Interfaz getInterfaz() {
		return interfaz;
	}

	/**
	 * @return Returns the normaInterfaz.
	 */
	public NormaInterfaz getNormaInterfaz() {
		return normaInterfaz;
	}

	/**
	 * @return Returns the registros.
	 */
	public List getRegistros() {
		return registros;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombreArchivoTemporal() {
		return nombreArchivoTemporal;
	}

	/**
	 * 
	 * @param nombreArchivoTemporal
	 */
	public void setNombreArchivoTemporal(String nombreArchivoTemporal) {
		this.nombreArchivoTemporal = nombreArchivoTemporal;
	}

	/**
	 * @param delimitador
	 *            The delimitador to set.
	 */
	public void setDelimitador(Delimitador delimitador) {
		this.delimitador = delimitador;
	}

	/**
	 * @param estructuraArchivoInterfaz
	 *            The estructuraArchivoInterfaz to set.
	 */
	public void setEstructuraArchivoInterfaz(List estructuraArchivoInterfaz) {
		this.estructuraArchivoInterfaz = estructuraArchivoInterfaz;
	}

	/**
	 * @param formato
	 *            The formato to set.
	 */
	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	/**
	 * @param interfaz
	 *            The interfaz to set.
	 */
	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	/**
	 * @param normaInterfaz
	 *            The normaInterfaz to set.
	 */
	public void setNormaInterfaz(NormaInterfaz normaInterfaz) {
		this.normaInterfaz = normaInterfaz;
	}

	/**
	 * @param registros
	 *            The registros to set.
	 */
	public void setRegistros(List registros) {
		this.registros = registros;
	}

	/**
	 * Comprime el archivo especificado como parametro.
	 * 
	 * @param carpetaTrabajo
	 *            Carpeta donde comprimira el archivo.
	 * 
	 * @param nombreArchivoPlano
	 *            El nombre del archivo a comprimir.
	 * 
	 * @param nombreArchivoZip
	 *            Nombre del archivo comprimido.
	 */
	public void comprimirArchivo(String carpetaTrabajo,
			String nombreArchivoPlano, String nombreArchivoZip) {

		BufferedInputStream origin = null;
		ZipOutputStream out = null;

		try {
			carpetaTrabajo = formatearRuta(carpetaTrabajo);

			FileOutputStream dest = new FileOutputStream(carpetaTrabajo
					+ nombreArchivoZip);
			out = new ZipOutputStream(new BufferedOutputStream(dest));

			byte data[] = new byte[BUFFER];

			FileInputStream fi = new FileInputStream(carpetaTrabajo
					+ nombreArchivoPlano);
			origin = new BufferedInputStream(fi, BUFFER);
			ZipEntry entry = new ZipEntry(nombreArchivoPlano);
			out.putNextEntry(entry);
			int count;
			while ((count = origin.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			origin.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
			throw new CompressingLocalFileException(e.getMessage());
		} finally {
			if (origin != null)
				try {
					origin.close();
				} catch (IOException e) {
					;
				}
			if (out != null)
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					;
				}
		}
	}

	private String aplicarNormaFormato(Object campo, int posicion) {
		String tipoDatoSalida;
		String strCampo = "";
		String relleno;
		int longitudCampo = 0;
		int cantidadDecimales = 0;

		EstructuraArchivo itemEstructura = getItemEstructura(posicion);

		// Identificamos de que tipo de dato es el campo (data de entrada)
		// y convertimos a un tipo todos los
		if (campo instanceof String) {
			strCampo = (String) campo;
		} else if (campo instanceof Integer) {
			campo = (Integer) campo;
			strCampo = campo.toString();
		} else if (campo instanceof Long) {
			campo = (Long) campo;
			strCampo = campo.toString();
		} else if (campo instanceof Float) {
			campo = (Float) campo;
			strCampo = campo.toString();
		} else if (campo instanceof Double) {
			campo = (Double) campo;
			strCampo = campo.toString();
		}
		strCampo = strCampo.trim();

		// Identificamos que tipo de dato es el campo de salida (data de salida
		// - interfaz)
		tipoDatoSalida = itemEstructura.getTipoDato().getSigla();
		longitudCampo = itemEstructura.getLongitudCampo();
		cantidadDecimales = itemEstructura.getCantidadDecimales();

		if (tipoDatoSalida.equals(Constants.TIPO_DATO_NUMERICO)) {
			// formateamos el numero de acuerdo a la cantidad de decimales y se
			// quita el punto decimal
			strCampo = formatearNumeroInterfaz(strCampo, cantidadDecimales);
			if (normaInterfaz.getFlagRellenoNumerico().equals(Constants.SI)) {
				relleno = normaInterfaz.getRellenoNumerico();
				if (StringUtils.isBlank(relleno))
					relleno = " ";

				if (normaInterfaz.getFlagAlineamientoNumerico().equals(
						Constants.SI)) {
					if (normaInterfaz.getAlineamientoNumerico().equals(
							Constants.IZQUIERDA)) {
						// Alineamos el valor a la izquierda, y rellenamos el
						// resto de los campos
						// con el valor de relleno, hasta completar la longitud
						// del campo
						strCampo = StringUtils.rightPad(strCampo,
								longitudCampo, relleno);
					} else if (normaInterfaz.getAlineamientoNumerico().equals(
							Constants.DERECHA)) {
						// Alineamos el valor a la derecha, y rellenamos el
						// resto de los campos
						// con el valor de relleno, hasta completar la longitud
						// del campo
						strCampo = StringUtils.leftPad(strCampo, longitudCampo,
								relleno);
					}
				}
			}
			if (normaInterfaz.getFlagTruncamientoNumerico()
					.equals(Constants.SI)) {
				if (normaInterfaz.getTruncamientoNumerico().equals(
						Constants.IZQUIERDA)) {
					// Truncamos desde la izquierda
					strCampo = StringUtils.right(strCampo, longitudCampo);
				} else if (normaInterfaz.getTruncamientoNumerico().equals(
						Constants.DERECHA)) {
					// Truncamos desde la derecha
					strCampo = StringUtils.left(strCampo, longitudCampo);
				}
			}
			// Si el archivo es con separadores, quitamos los ceros de la
			// izquierda
			if (this.normaInterfaz.getCodigoTipoFormatoArchivo().equals(
					Constants.ARCHIVO_SEPARADOR)) {
				// aplicar formato
				if (this.interfaz.getFlagTipoFormato().equalsIgnoreCase(
						Constants.FORMATO_TOTAL)) {
					strCampo = this.getFormato() + strCampo.trim()
							+ this.getFormato();
				} else if (this.interfaz.getFlagTipoFormato().equalsIgnoreCase(
						Constants.FORMATO_ALFANUMERICO)) {
					strCampo = quitarCerosIzquierda(strCampo);
				}
			}
		} else if (tipoDatoSalida.equals(Constants.TIPO_DATO_CARACTER)) {
			if (normaInterfaz.getFlagFecha().equals(Constants.SI)) {
				if (campo instanceof Timestamp) {
					campo = (Timestamp) campo;
					String patron = normaInterfaz.getFormatoFecha().trim()
							.replace('A', 'y').replace('D', 'd');
					SimpleDateFormat sdf = new SimpleDateFormat(patron);
					strCampo = sdf.format(campo);
				}
			}

			if (NumberUtils.isNumber(strCampo)) {
				strCampo = formatearNumeroCaracterInterfaz(strCampo, 2);
			}

			if (normaInterfaz.getFlagRellenoAlfanumerico().equals(Constants.SI)) {
				relleno = normaInterfaz.getRellenoAlfanumerico();
				if (StringUtils.isBlank(relleno))
					relleno = " ";

				if (normaInterfaz.getFlagAlineamientoAlfanumerico().equals(
						Constants.SI)) {
					if (normaInterfaz.getAlineamientoAlfanumerico().equals(
							Constants.IZQUIERDA)) {
						// Alineamos el valor a la izquierda, y rellenamos el
						// resto de los campos
						// con el valor de relleno, hasta completar la longitud
						// del campo
						strCampo = StringUtils.rightPad(strCampo,
								longitudCampo, relleno);
					} else if (normaInterfaz.getAlineamientoAlfanumerico()
							.equals(Constants.DERECHA)) {
						// Alineamos el valor a la derecha, y rellenamos el
						// resto de los campos
						// con el valor de relleno, hasta completar la longitud
						// del campo
						strCampo = StringUtils.leftPad(strCampo, longitudCampo,
								relleno);
					}
				}
			}
			if (normaInterfaz.getFlagTruncamientoAlfanumerico().equals(
					Constants.SI)) {
				if (normaInterfaz.getTruncamientoAlfanumerico().equals(
						Constants.IZQUIERDA)) {
					// Truncamos desde la izquierda
					strCampo = StringUtils.right(strCampo, longitudCampo);
				} else if (normaInterfaz.getTruncamientoAlfanumerico().equals(
						Constants.DERECHA)) {
					// Truncamos desde la derecha
					strCampo = StringUtils.left(strCampo, longitudCampo);
				}
			}

			if (this.normaInterfaz.getCodigoTipoFormatoArchivo().equals(
					Constants.ARCHIVO_SEPARADOR)) {
				// aplicar formato
				strCampo = this.getFormato() + strCampo.trim()
						+ this.getFormato();
			}
		}

		if (this.normaInterfaz.getCodigoTipoFormatoArchivo().equals(
				Constants.ARCHIVO_SEPARADOR)) {
			if (estructuraArchivoInterfaz.size() != posicion) {
				// agregar Delimitador
				StringBuffer campoBuffer = new StringBuffer();
				campoBuffer.append(strCampo);
				campoBuffer = this.agregarDelimitador(this.getDelimitador(),
						campoBuffer);
				strCampo = campoBuffer.toString();
			}
		}

		return strCampo;
	}

	/**
	 * Formatea al formato de numero que se envia en las interfaces, sin el
	 * punto decimal para el caso de decimales.
	 * 
	 * @param numero
	 *            Cadena a formatear, si no es un valor numerico, se devuelve el
	 *            mismo valor
	 * 
	 * @param numeroDecimales
	 *            Numero de decimales
	 * 
	 * @return Cadena formateada o no, segun sea el caso.
	 */
	private String formatearNumeroInterfaz(String numero, int numeroDecimales) {
		if (NumberUtils.isNumber(numero)) {

			if (numero.indexOf(".") > 0) {
				BigDecimal b = new BigDecimal(numero);
				numero = b.toString();

				if (numeroDecimales > 0) {
					return StringUtils.substring(numero, 0, StringUtils
							.indexOf(numero, '.'))
							+ StringUtils.rightPad(StringUtils.substring(
									numero,
									StringUtils.indexOf(numero, '.') + 1),
									numeroDecimales, '0');
				} else {
					return StringUtils.substring(numero, 0, StringUtils
							.indexOf(numero, '.'));
				}
			} else {
				BigInteger bi = new BigInteger(numero);
				numero = bi.toString();

				if (numeroDecimales > 0) {
					return numero
							+ StringUtils.rightPad("0", numeroDecimales, '0');
				} else {
					return numero;
				}

			}
		} else {
			return numero;
		}
	}

	/**
	 * Formatea un valor numerico al formato caracter que se envia en las
	 * interfaces, con el punto decimal para el caso de decimales.
	 * 
	 * @param numero
	 *            Cadena a formatear, si no es un valor numerico, se devuelve el
	 *            mismo valor
	 * 
	 * @param numeroDecimales
	 *            Numero de decimales
	 * 
	 * @return Cadena formateada o no, segun sea el caso.
	 */
	private String formatearNumeroCaracterInterfaz(String numero,
			int numeroDecimales) {
		if (NumberUtils.isNumber(numero)) {
			if (numero.indexOf(".") > 0) {
				BigDecimal b = new BigDecimal(numero);
				numero = b.toString();

				if (numeroDecimales > 0) {
					numero = StringUtils.substring(numero, 0, StringUtils
							.indexOf(numero, '.'))
							+ "."
							+ StringUtils.rightPad(StringUtils.substring(
									numero,
									StringUtils.indexOf(numero, '.') + 1),
									numeroDecimales, '0');
				}
			}
		}
		return numero;
	}

	private EstructuraArchivo getItemEstructura(int posicion) {
		Iterator it = estructuraArchivoInterfaz.iterator();
		EstructuraArchivo ea = null;
		while (it.hasNext()) {
			ea = (EstructuraArchivo) it.next();
			if (ea.getPosicionCampo() == posicion)
				break;
		}
		return ea;
	}

	/**
	 * Elimina los ceros a la izquierda de la cadena que es pasada como
	 * parametro.
	 * 
	 * @param cadena
	 *            Cadena a trabajar.
	 * 
	 * @return Cadena sin ceros a la izquierda.
	 */
	private String quitarCerosIzquierda(String cadena) {
		if (cadena.indexOf(".") > 0) {
			return NumberUtils.createBigDecimal(cadena).toString();
		} else {
			return NumberUtils.createBigInteger(cadena).toString();
		}
	}

	/**
	 * Envia a al directorio Historico en la Red los archivos que se encuentran
	 * en el directorio ubicado en dirTmp.
	 * 
	 */
	public void grabarHistoricoRed() throws Exception {
		try {
			System.out.println("ENTRANDO METODO GRABAR HISTORICO RED");
			String archivoOrigen = this.formatearRuta(this.interfaz
					.getDirectorioTemporal())
					+ nombreArchivoTemporal;
			String archivoDestino = this.formatearRuta(this.interfaz
					.getDirectorioHistorico())
					+ nombreArchivoTemporal;

			/* Copiando Archivo al Directorio Historico */
			this.copiar(archivoOrigen, archivoDestino);

			/* Eliminando Archivo en el Directorio Temporal */
			try {
				this.eliminarArchivo(archivoOrigen);
			} catch (Exception e) {
				ExcepcionSistema errorSistema = new ExcepcionSistema(
						Constants.INTERFAZSICC_ERROR_BORRAR_ARCHIVO_FTP_TEMPORAL);
				errorSistema
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BORRAR_TEMP);
				throw errorSistema;
			}
		} catch (ExcepcionSistema e) {
			throw e;
		} catch (Exception e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_HISTO);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_HIST);
			throw errorSistema;
		}
	}

	/**
	 * Envia a al directorio Historico del FTP los archivos que se encuentran en
	 * el directorio ubicado en dirTmp.
	 * 
	 */
	public void grabarHistoricoFTP(FTPClient ftp) throws Exception {
		try {
			System.out.println("ENTRANDO METODO GRABAR HISTORICO FTP");
			String temporal = this.formatearRutaFTP(this.interfaz
					.getDirectorioTemporal())
					+ this.nombreArchivoTemporal;
			String histo = this.formatearRutaFTP(this.interfaz
					.getDirectorioHistorico())
					+ this.nombreArchivoTemporal;

			// Ir al Subdirectorio Temporal
			try {
				this.cambiarDirectorioFTP(ftp, this.interfaz
						.getDirectorioTemporal());
				// Grabando en el Directorio Historico
				if (!ftp.rename(temporal, histo)) {
					throw new Exception(); // En caso no pudo grabarse en el
											// Directorio Historico
				}
			} catch (Exception e) {
				this.enviarFTP(ftp, this.interfaz.getDirectorioTemporal(),
						nombreArchivoTemporal, this.interfaz
								.getDirectorioHistorico()); // En caso se pudo
															// grabar en el
															// Directorio
															// Historico
				temporal = this.formatearRuta(this.interfaz
						.getDirectorioTemporal())
						+ this.nombreArchivoTemporal;
				this.eliminarArchivo(temporal); // Eliminando archivo en la
												// carpeta temporal
			}

		} catch (Exception e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_HISTO);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_HIST);
			throw errorSistema;
		}
	}

	/**
	 * Envia a un FTP los archivos que se encuentran en el directorio ubicado en
	 * dirTmp.
	 * 
	 * @throws StoringFileOverFtpException
	 *             Excepcion lanzada cuando ocurre un error al subir los
	 *             archivos al ftp.
	 * 
	 * @throws DeletingTemporalFileException
	 *             Excepcion lanzada cuando ocurre un error al eliminar los
	 *             archivos de la carpeta temporal.
	 */
	public void enviarFTP(FTPClient ftp, String dirOrigenRed, String archivo,
			String dirDestinoFTP) throws Exception {
		String dirTmp = formatearRuta(dirOrigenRed);
		String rutaOrigen = dirTmp + archivo;
		try {
			// Sube los archivos de la carpeta local a la carpeta del FTP
			File file = new File(rutaOrigen);

			FileInputStream fis = new FileInputStream(file);
			ftp.storeFile(this.formatearRutaFTP(dirDestinoFTP) + archivo, fis);
			fis.close();

		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Envia a un FTP los archivos que se encuentran en el directorio ubicado en
	 * dirTmp.
	 * 
	 * @throws StoringFileOverFtpException
	 *             Excepcion lanzada cuando ocurre un error al subir los
	 *             archivos al ftp.
	 * 
	 * @throws DeletingTemporalFileException
	 *             Excepcion lanzada cuando ocurre un error al eliminar los
	 *             archivos de la carpeta temporal.
	 */
	public void enviarFTP() throws StoringFileOverFtpException,
			DeletingTemporalFileException {
		String dirTmp = formatearRuta(this.interfaz.getDirectorioTemporal());
		try {
			Integer iPort = new Integer(this.interfaz.getPuertoFtp());

			// Se conecta y loguea al ftp
			FTPClient ftp = new FTPClient();
			ftp.connect(this.interfaz.getServidorFtp(), iPort.intValue());
			ftp.login(this.interfaz.getUsuarioFtp(), this.interfaz
					.getPasswordFtp());
			copiarACarpetaFTP(dirTmp, this.interfaz.getDirectorioHistorico(),
					ftp, Constants.CARPETA_HISTORICO);

			// Cierra la session y desconecta del ftp
			ftp.logout();
			ftp.disconnect();
			eliminar(dirTmp);
		} catch (SocketException e) {
			try {
				eliminar(dirTmp);
			} catch (DeletingTemporalFileException ex) {
				throw new DeletingTemporalFileException(e.getMessage());
			} catch (Exception ex) {
			}
			e.printStackTrace();
		} catch (DeletingTemporalFileException ex) {
			throw new DeletingTemporalFileException(ex.getMessage());
		} catch (Exception ex) {
			throw new StoringFileOverFtpException(ex.getMessage());
		}
	}

	/**
	 * Copia todos los archivos existentes desde el directorio fuente local al
	 * directorio destino en un FTP.
	 * 
	 * @param dirFuente
	 *            Directorio fuente Local.
	 * 
	 * @param dirDestino
	 *            Directorio destino FTP.
	 * 
	 * @param ftp
	 *            Objeto de tipo FTPClient, con una conexion al ftp.
	 * 
	 * @param idCarpeta
	 *            Identificador de la carpeta donde se copia los archivos.
	 * 
	 * @throws IOException
	 * @throws StoringFileOverFtpException
	 */
	private void copiarACarpetaFTP(String dirFuente, String dirDestino,
			FTPClient ftp, String idCarpeta) throws IOException,
			StoringFileOverFtpException {
		// Obtiene la lista de archivos del directorio temporal
		File f = new File(dirFuente);
		File[] archivos = f.listFiles();
		// Sube los archivos de la carpeta local ala carpeta del FTP
		if (!ftp.changeWorkingDirectory(dirDestino)) {
			throw new StoringFileOverFtpException(idCarpeta);
		}
		for (int i = 0; i < archivos.length; i++) {
			File file = new File(dirFuente + archivos[i].getName());
			FileInputStream fis = new FileInputStream(file);
			ftp.storeFile(archivos[i].getName(), fis);
			fis.close();
		}
	}

	/**
	 * Copia todos los archivos existentes desde una carpeta fuente, hacia otra
	 * carpeta destino.
	 * 
	 * @param dirFuente
	 *            Directorio fuente.
	 * 
	 * @param dirDestino
	 *            Directorio destino.
	 * 
	 * @param idCarpeta
	 *            Identificador de la carpeta donde se estan copiando los
	 *            archivos.
	 * 
	 * @throws CopingFileOverLocalNetworkException
	 *             Lanzada si ocurre un error al momento de realizar la copia.
	 */
	private void copiarACarpetaRed(String dirFuente, String dirDestino,
			String idCarpeta) throws CopingFileOverLocalNetworkException {
		try {
			// Obtiene la lista de archivos del directorio temporal
			File f = new File(dirFuente);
			File[] archivos = f.listFiles();

			// Copia al directorio de salida
			for (int i = 0; i < archivos.length; i++) {
				copiar(dirFuente + archivos[i].getName(), dirDestino
						+ archivos[i].getName());
			}
		} catch (Exception e) {
			throw new CopingFileOverLocalNetworkException(idCarpeta);
		}
	}

	/**
	 * Copia un archivo.
	 * 
	 * @param rutaNombreArchivoFuente
	 *            Nombre de Archivo a copiar.
	 * 
	 * @param nombreArchivoDestino
	 *            Nombre de archivo destino.
	 * 
	 * @throws IOException
	 */
	private void copiar(String rutaNombreArchivoFuente,
			String nombreArchivoDestino) throws Exception {
		System.out.println("Entro METODO COPIAR");
		System.out.println("Ruta Origen " + rutaNombreArchivoFuente);
		System.out.println("Ruta Destino " + nombreArchivoDestino);
		File archivoFuente = new File(rutaNombreArchivoFuente);
		File archivoDestino = new File(nombreArchivoDestino);

		FileInputStream from = null;
		FileOutputStream to = null;

		try {
			from = new FileInputStream(archivoFuente);
			to = new FileOutputStream(archivoDestino);
			byte[] buffer = new byte[BUFFER];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1) {
				to.write(buffer, 0, bytesRead);
			}
			from.close();
			to.close();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Elimina todos los archivos de un directorio.
	 * 
	 * @param directorio
	 *            Directorio a eliminar.
	 * 
	 * @throws DeletingTemporalFileException
	 *             Lanzada si ocurre un error al momento de eliminar.
	 */
	private void eliminar(String directorio)
			throws DeletingTemporalFileException {
		// Obtiene la lista de archivos del directorio temporal
		File f = new File(directorio);
		System.out
				.println("Mostrando el nombre del archivo temporal (directorio) "
						+ directorio);
		boolean errorDelete = false;
		File[] archivos = f.listFiles();
		if (archivos == null) {
			log.debug("======== ELIMINANDO ARCHIVOS TEMPORALES ========");
			log.debug("Eliminando archivos temporales "
					+ System.getProperty("line.separator"));
			// Eliminar archivos
			for (int i = 0; i < archivos.length; i++) {
				if (!archivos[i].delete()) {
					errorDelete = true;
					log.debug("======== ERROR al eliminar archivo temporal: "
							+ archivos[i].getName() + " "
							+ System.getProperty("line.separator"));
					log.debug("ERROR al eliminar archivo temporal: "
							+ archivos[i].getName() + " "
							+ System.getProperty("line.separator"));
				} else {
					log.debug("Eliminando: " + archivos[i].getName() + " "
							+ System.getProperty("line.separator"));
					log.debug("=============== Eliminando: "
							+ archivos[i].getName() + " "
							+ System.getProperty("line.separator"));
				}

			}
			if (errorDelete) {
				throw new DeletingTemporalFileException();
			} else
				log
						.debug("Los archivos temporales fueron eliminados correctamente"
								+ System.getProperty("line.separator"));
			log
					.debug("Los archivos temporales fueron eliminados correctamente");
		}
	}

	private void eliminarArchivo(String archivo) throws Exception {
		try {
			// Obtiene la lista de archivos del directorio temporal
			File f = new File(archivo);
			System.out
					.println("ELIMINAR ARCHIVO Mostrando el nombre del archivo temporal (directorio) "
							+ archivo);
			boolean errorDelete = false;
			if (!f.delete()) {
				errorDelete = true;
			}
			if (errorDelete)
				throw new Exception();
		} catch (Exception e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_BORRAR_ARCHIVO_FTP_TEMPORAL);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_BORRAR_TEMP);
			throw errorSistema;
		}

	}

	/**
	 * Envia un archivo desde una carpeta de red, hacia una carpeta de red.
	 * 
	 * @throws CopingFileOverLocalNetworkException
	 *             Lanzado si ocurre un error al copiar los archivos.
	 * 
	 * @throws DeletingTemporalFileException
	 *             Lanzado si ocurre un error al eliminar los archivos del
	 *             directorio temporal.
	 */
	public void enviarRed() throws CopingFileOverLocalNetworkException,
			DeletingTemporalFileException {
		try {
			String dirES = formatearRuta(this.interfaz
					.getDirectorioEntradaSalida());
			String dirTmp = formatearRuta(this.interfaz.getDirectorioTemporal());
			// String dirLog = formatearRuta(this.interfaz.getDirectorioLog());
			String dirHist = formatearRuta(this.interfaz
					.getDirectorioHistorico());

			copiarACarpetaRed(dirTmp, dirES, Constants.CARPETA_SALIDA);
			copiarACarpetaRed(dirTmp, dirHist, Constants.CARPETA_HISTORICO);
			// Elimina los archivos de la carpeta local temporal
			eliminar(dirTmp);
		} catch (DeletingTemporalFileException e) {
			throw new DeletingTemporalFileException(e.getMessage());
		} catch (Exception e) {
			throw new CopingFileOverLocalNetworkException(e.getMessage());
		}
	}

	/**
	 * METODO A ELIMINAR Deja el log de errores, en un ftp o en una carpeta de
	 * la red.
	 * 
	 * @param params
	 * 
	 * @param errorTrace
	 */
	public void dejarLog(Interfaz params, StackTraceElement errorTrace[]) {
		String tipoEnvio = params.getFlagEnvioArchivo();
		String dirLog = params.getDirectorioLog();
		String server = params.getServidorFtp();
		String port = params.getPuertoFtp();
		String username = params.getUsuarioFtp();
		String password = params.getPasswordFtp();

		String archivo = "";
		File file = null;
		if (nombreArchivo != null && nombreArchivo.length() > 0) {
			if (params.getExtensionLogErroresDescripcion() != null) {
				archivo = nombreArchivoTemporal;
				int pos = nombreArchivoTemporal
						.indexOf(Constants.DELIMITADOR_EXTENSION_ARCHIVO);
				if (pos >= 0) {
					archivo = nombreArchivoTemporal.substring(0, pos + 1)
							+ params.getExtensionLogErroresDescripcion();
				}
				file = new File(archivo);
			}

			crearLog(file, errorTrace);
			try {
				if (tipoEnvio.equalsIgnoreCase(Constants.ENVIO_FTP)) {
					Integer iPort = new Integer(port);

					// Se conecta y loguea al ftp
					FTPClient ftp = new FTPClient();
					ftp.connect(server, iPort.intValue());
					ftp.login(username, password);
					// Sube los archivos a la carpeta de ES
					if (ftp.changeWorkingDirectory(dirLog)) {
						FileInputStream fis = new FileInputStream(file);
						ftp.storeFile(archivo, fis);
						fis.close();
						file.delete();
					}

					// Cierra la session y desconecta del ftp
					ftp.logout();
					ftp.disconnect();
				} else if (tipoEnvio.equalsIgnoreCase(Constants.ENVIO_RED)) {
					dirLog = formatearRuta(dirLog);
					this.copiar(archivo, dirLog);
				}
			} catch (Exception e) {
				file.delete();
			}
		}
	}

	/**
	 * METODO A ELIMINAR Crea el log de erores.
	 * 
	 * @param nombreArchivo
	 *            Nombre del archivo.
	 */
	private void crearLog(File file, StackTraceElement errorTrace[]) {
		OutputStream fout = null;
		OutputStream bout = null;
		OutputStreamWriter out = null;

		// convertimos la traza del error a string
		strlog
				.append("TRAZA DEL ERROR:"
						+ System.getProperty("line.separator"));
		for (int i = 0; i < errorTrace.length; i++) {
			strlog.append(errorTrace[i].toString()
					+ System.getProperty("line.separator"));
		}

		try {
			fout = new FileOutputStream(file);
			bout = new BufferedOutputStream(fout);
			out = new OutputStreamWriter(bout);

			out.write(strlog.toString());

			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					;
				}
		}
	}

	/**
	 * Deja el log de errores en una carpeta de la red.
	 * 
	 * @param params
	 *            Interfaz
	 * 
	 * @param numeroLote
	 *            Numero de lote con el cual se generara el log
	 * 
	 * @param error
	 *            Excepcion del cual va tener el contenido del log
	 */
	public void dejarLogRed(Interfaz params, String numeroLote, Exception error) {
		String archivo = "";
		String dirLog = formatearRuta(this.interfaz.getDirectorioLog());
		File file = null;
		try {
			if (nombreArchivo != null && nombreArchivo.length() > 0) {
				if (params.getExtensionLogErroresDescripcion() != null) {
					archivo = dirLog + nombreArchivo + numeroLote
							+ Constants.DELIMITADOR_EXTENSION_ARCHIVO
							+ params.getExtensionLogErroresDescripcion();
					file = new File(archivo);
				}

				/* Generando Mensaje a mostrar en el Log */
				this.crearLog(error);

				/* Generando Log en el directorio Log en la Red */
				OutputStream fout = null;
				OutputStream bout = null;
				OutputStreamWriter out = null;
				fout = new FileOutputStream(file);
				bout = new BufferedOutputStream(fout);
				out = new OutputStreamWriter(bout);
				out.write(strlog.toString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deja el log de errores en el FTP.
	 * 
	 * @param params
	 *            Interfaz
	 * 
	 * @param numeroLote
	 *            Numero de lote con el cual se generara el log
	 * 
	 * @param error
	 *            Excepcion del cual va tener el contenido del log
	 */
	public void dejarLogFTP(Interfaz params, String numeroLote, Exception error) {
		String archivo = "";
		FTPClient ftp = new FTPClient();
		try {
			if (nombreArchivo != null && nombreArchivo.length() > 0) {
				if (params.getExtensionLogErroresDescripcion() != null) {
					archivo = nombreArchivo + numeroLote
							+ Constants.DELIMITADOR_EXTENSION_ARCHIVO
							+ params.getExtensionLogErroresDescripcion();
				}

				/* Generando Mensaje a mostrar en el Log */
				this.crearLog(error);

				/* Grabando en el FTP */
				OutputStream fout = null;
				OutputStream bout = null;
				OutputStreamWriter out = null;
				ftp = this.loguearFTP();

				fout = ftp.storeFileStream(formatearRutaFTP(this.interfaz
						.getDirectorioLog())
						+ archivo);
				bout = new BufferedOutputStream(fout);
				out = new OutputStreamWriter(bout);
				out.write(strlog.toString());
				out.flush();
				out.close();
				this.cerrarFTP(ftp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea el log de erores.
	 * 
	 * @param Error
	 *            Excepcion a mostrar
	 */
	private void crearLog(Exception error) {
		/* Generando Mensaje a mostrar en el Log */
		strlog
				.append("TRAZA DEL ERROR:"
						+ System.getProperty("line.separator"));
		String mensaje = "";
		try {
			mensaje = error.getMessage();
		} catch (Exception e) {
			mensaje = "";
		}
		strlog.append(mensaje + System.getProperty("line.separator"));
		try {
			mensaje = error.getCause().toString();
			System.out.println("CAUSA LOG: " + mensaje);
		} catch (Exception e) {
			mensaje = "";
		}
		strlog.append(mensaje + System.getProperty("line.separator"));
	}

	/**
	 * formatea la ruta de las carpetas.
	 * 
	 * @param ruta
	 *            Ruta a formatear.
	 * 
	 * @return Ruta formateada.
	 */
	private String formatearRuta(String ruta) {
		if (!ruta.trim().endsWith(System.getProperty("file.separator")))
			ruta = ruta.trim() + System.getProperty("file.separator");

		return ruta.trim();
	}

	/**
	 * formatea la ruta de las carpetas FTP.
	 * 
	 * @param ruta
	 *            Ruta a formatear.
	 * 
	 * @return Ruta formateada.
	 */
	private String formatearRutaFTP(String ruta) {
		if (!ruta.trim().endsWith("/"))
			ruta = ruta.trim() + "/";

		return ruta.trim();
	}

	/**
	 * @return verdadero o falso, indicando si el SO es Windows o no.
	 */
	protected boolean isWindows() {
		if (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1)
			return true;
		else
			return false;
	}

	/**
	 * Genera un archivo de texto, formateado y en la carpeta de destino
	 * especificada.
	 * 
	 * @param numeroLote
	 *            Numero de lote generado
	 * 
	 * @param numeroInterfaz
	 *            Numero de la interfaz al que pertenece el archivo que se va
	 *            generar.
	 */
	public void generarArchivo(String numeroLote, int numeroInterfaz) {
		int numRegistros = 0;

		StringBuffer linea = null;
		OutputStream fout = null;
		OutputStream bout = null;
		OutputStreamWriter out = null;
		int columnIndex = 0;

		String strExt = "";

		// Construir nombre del archivo
		if (numeroInterfaz == Constants.INTERFAZ_GIS_REPORTE_DIRECCION_CONSULTORAS) {
			strExt = "xls";
			this.nombreArchivo = interfaz.getCodigo()
					+ Constants.DELIMITADOR_NOMBRE_ARCHIVO + "REP"
					+ StringUtils.left(numeroLote, 8);
			this.nombreArchivo = this.nombreArchivo
					+ Constants.DELIMITADOR_EXTENSION_ARCHIVO + strExt;
		} else {
			strExt = this.getExtension();
			this.nombreArchivo = interfaz.getCodigo()
					+ Constants.DELIMITADOR_NOMBRE_ARCHIVO + numeroLote;
			this.nombreArchivo = this.nombreArchivo
					+ Constants.DELIMITADOR_EXTENSION_ARCHIVO + strExt;
		}

		// Generar el archivo en el directorio temporal
		String carpetaDestino = interfaz.getDirectorioTemporal();

		try {
			carpetaDestino = formatearRuta(carpetaDestino);
			if (numeroInterfaz != Constants.INTERFAZ_GIS_REPORTE_DIRECCION_CONSULTORAS) {
				fout = new FileOutputStream(carpetaDestino + nombreArchivo);
				bout = new BufferedOutputStream(fout);
				out = new OutputStreamWriter(bout);
			}

			if (numeroInterfaz == Constants.INTERFAZ_COM_PAGO_LIDERES) {
				InterfazCOMPagoLider bean = new InterfazCOMPagoLider();
				for (int i = 0; i < this.registros.size(); i++) {
					bean = (InterfazCOMPagoLider) this.registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getFechaPago(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoPlanilla(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCentroCosto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getMontoPagar(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFlag(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoRegion(),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			if (numeroInterfaz == Constants.INTERFAZ_MYEBEL_CRONOGRAMA_FACTURACION) {
				InterfazMyEbelCronogramaFacturacion bean = new InterfazMyEbelCronogramaFacturacion();
				for (int i = 0; i < this.registros.size(); i++) {
					bean = (InterfazMyEbelCronogramaFacturacion) this.registros
							.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoZona(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoCampanya(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getFechaFacturacion(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getFechaRefacturacion(), columnIndex++));

					linea.append(System.getProperty("line.separator"));

					out.write(linea.toString());
					numRegistros++;
				}
			}

			if (numeroInterfaz == Constants.INTERFAZ_BEL_PORCENTAJES_REFERENCIA) {
				InterfazBELPorcentajesReferencia bean = new InterfazBELPorcentajesReferencia();
				for (int i = 0; i < this.registros.size(); i++) {
					bean = (InterfazBELPorcentajesReferencia) this.registros
							.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoCliente(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getEstadoFacturacion(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoGrupo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getPorcentajeComision(), columnIndex++));
					linea.append(System.getProperty("line.separator"));

					out.write(linea.toString());
					numRegistros++;
				}
			}
			if (numeroInterfaz == Constants.INTERFAZ_BEL_UBICACIONES_GEOGRAFICAS) {
				InterfazBELUbicacionesGeograficas bean = new InterfazBELUbicacionesGeograficas();
				for (int i = 0; i < this.registros.size(); i++) {
					bean = (InterfazBELUbicacionesGeograficas) this.registros
							.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean
							.getCodigoUbicacionGeografica(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getCodigoUbicacionGeograficaPostal(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescCompletaDepartamento(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescCompletaProvincia(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescCompletaDistrito(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescAbreviadaDepartamento(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescAbreviadaProvincia(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescAbreviadaDistrito(), columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			else if (numeroInterfaz == Constants.INTERFAZ_MYEBEL_MATRIZ_CAMPANYA) {
				InterfazMyEbelMatrizCampanya bean = new InterfazMyEbelMatrizCampanya();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazMyEbelMatrizCampanya) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoVenta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoGrupo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getCodigoTipoOferta(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPrecioProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getFactorRepeticion(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getUnidadesMaximas(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPaginaCatalogo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getProductoEstadisticable(), columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_RETAIL_MATRIZ_CAMPANYA) {
				InterfazRetailMatrizCampanya bean = new InterfazRetailMatrizCampanya();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazRetailMatrizCampanya) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoVenta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescripcionProducto(), columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getCodigoTipoOferta(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPrecioCatalogo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTasaImpuestos(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getValorDescuentoEspecifico(), columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_MYEBEL_MOVIMIENTOS_CUENTA_CORRIENTE) {
				InterfazMyEbelMovimientosCuentaCorriente bean = new InterfazMyEbelMovimientosCuentaCorriente();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazMyEbelMovimientosCuentaCorriente) registros
							.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getCodigoConsultora(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroLote(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTipoOperacion(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaOperacion(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getFechaVencimiento(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getAnyoCampanya(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescripcionTransaccion(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getMontoOperacion(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_RETAIL_FACTURAS_VENTA_DIRECTA) {
				InterfazRetailFacturasVentaDirecta bean = new InterfazRetailFacturasVentaDirecta();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazRetailFacturasVentaDirecta) registros
							.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoVenta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getCuentaConsultora(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoMarca(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getUnidadesAtendidas(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getValorTotal(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getOrigenFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getUbicacionGeografica(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCampanyaFactura(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_RETAIL_COMPLEMENTO_FACTURAS_VENTA_DIRECTA) {
				InterfazRetailComplementoFacturasVentaDirecta bean = new InterfazRetailComplementoFacturasVentaDirecta();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazRetailComplementoFacturasVentaDirecta) registros
							.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoVenta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getCuentaConsultora(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getValorVentaCatalogoUnitario(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getValorVentaCatalogoUnitarioDescuento(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getValorVentaCatalogoDescuento(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTotalImpuesto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFlete(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTipoDocumento(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_REUTILIZACION_DOCUMENTOS_ANULADOS) {
				InterfazREUDocumentosAnulados bean = new InterfazREUDocumentosAnulados();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazREUDocumentosAnulados) registros
							.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroBoleta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoCliente(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaAnulacion(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			else if (numeroInterfaz == Constants.INTERFAZ_REU_PERIODOS_FACTURACION) {
				InterfazREUPeriodosFacturacion bean = new InterfazREUPeriodosFacturacion();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazREUPeriodosFacturacion) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					// modificacion hecha para agregar el codigo de pais al
					// archivo.
					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					// --
					linea.append(aplicarNormaFormato(bean.getCodigoPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaInicio(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaFin(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_BEL_DIRECCION_CLIENTES) {
				InterfazBELDireccionClientes bean = new InterfazBELDireccionClientes();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazBELDireccionClientes) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoCliente(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTipoVia(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNombreVia(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getStatusIndicadorDescripcionAdicional(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroVia(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroBlock(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroManzana(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroLote(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroKilometro(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getNumeroInteriorDomicilio(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoPostal(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getCodigoUbigeoINEI(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getCodigoTipoCentroPoblado(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroEtapa(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroSector(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getStatusIndicadorDescripcionReferencia(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroTelefono1(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroTelefono2(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getStatusIndicadorDireccionTrabajo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroTelefono3(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroTelefono4(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getStatusIndicadorAdicional(), columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			else if (numeroInterfaz == Constants.INTERFAZ_OCR_REGIONES) {
				Base bean = new Base();
				for (int i = 0; i < registros.size(); i++) {
					bean = (Base) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getDescripcion(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_OCR_ZONAS) {
				Base bean = new Base();
				for (int i = 0; i < registros.size(); i++) {
					bean = (Base) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getDescripcion(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_SAM_INICIALIZACION_STOCKS) {
				InterfazSAMInicializacionStocks bean = new InterfazSAMInicializacionStocks();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazSAMInicializacionStocks) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoAlmacen(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getVariacionInventario(), columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_SAB_RENTABILIDAD_ZONA) {
				InterfazSABRentabilidadPorZona bean = new InterfazSABRentabilidadPorZona();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazSABRentabilidadPorZona) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoSociedad(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoEjercicio(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoZona(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getDescripcionZona(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTipoMoneda(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCentroCosto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCuentaContable(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoMoneda(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getValorIncobrabilidad(), columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_SAB_INCENTIVOS_CONSULTORAS) {
				InterfazSABIncentivosConsultoras bean = new InterfazSABIncentivosConsultoras();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazSABIncentivosConsultoras) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoSociedad(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoCanal(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getEjercicio(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroConcurso(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNivelPremio(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroPremio(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPosicionPlan(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getCantidadPremiosEntregados(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getUnidadMedida(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getPrecioPublicoPremio(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getCodigoMonedaLocal(), columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_SAB_FUENTE_VENTAS_PREVISTA) {
				InterfazSABFuenteVentasPrevista bean = new InterfazSABFuenteVentasPrevista();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazSABFuenteVentasPrevista) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoSociedad(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoAlmacen(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoCanal(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getAnio(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoMoneda(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoZona(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoRegion(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getActivasIniciales(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getActivasFinales(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getIngresos(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getReingresos(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getEgresos(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getRezonificadasRecibidas(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getRezonificadasEntregadas(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroOrdenes(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroPedidos(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getUnidadesVendidas(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroClientes(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getVentaNeta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaCierre(),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			else if (numeroInterfaz == Constants.INTERFAZ_BEL_FACTURAS_CABECERA) {
				InterfazBELFacturasCabecera bean = new InterfazBELFacturasCabecera();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazBELFacturasCabecera) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getAnyoCampanya(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoCliente(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTipoPedido(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTipoDocumento(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_BEL_FACTURAS_DETALLE) {
				InterfazBELFacturasDetalle bean = new InterfazBELFacturasDetalle();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazBELFacturasDetalle) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getNumeroFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoVenta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getUnidadesAtendidas(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getUnidadesFaltantes(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPrecioCatalogo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPrecioFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPrecioContable(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_BEL_UNIDADES_ATENDIDAS) {
				InterfazBELUnidadesAtendidas bean = new InterfazBELUnidadesAtendidas();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazBELUnidadesAtendidas) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.getPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroFactura(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoVenta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getUnidadesAtendidas(), columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			else if (numeroInterfaz == Constants.INTERFAZ_COM_LIBRETA_AHORROS) {
				InterfazCOMLibretaAhorros bean = new InterfazCOMLibretaAhorros();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazCOMLibretaAhorros) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.getCodigoPlanilla(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getDni(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getNumeroLibretaAhorro(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTipoCuenta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoBanco(),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_COM_LIDERES_NUEVAS) {
				InterfazCOMLideresNuevas bean = new InterfazCOMLideresNuevas();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazCOMLideresNuevas) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.getNombres(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getApellidoPaterno(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getApellidoMaterno(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaIngreso(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getDni(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getDireccion(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTelefono(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFechaNacimiento(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCiudad(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getEstadoCivil(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getLimaProvincia(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getFechaIngresoPlanilla(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCentroCosto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoZona(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescripcionDistrito(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoLider(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescripcionDepartamento(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getDescripcionProvincia(), columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_REUTILIZACION_MATRIZ_CAMPANYA) {
				InterfazREUMatrizCampanya bean = new InterfazREUMatrizCampanya();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazREUMatrizCampanya) registros
							.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoPeriodo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoVenta(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoGrupo(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getCodigoTipoOferta(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPrecioProducto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPrecioContable(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getNumeroPaginaCatalogo(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getFactorRepeticionProducto(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getNumeroUnidadesMaximas(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getIndicadorProductoEstadisticable(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getGrupoDescuento(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getIndicadorComisionable(), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.getIndicadorComisionAdicional(), columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_GIS_DIRECCION_CONSULTORAS) {
				InterfazGISEnviarDireccionConsultoras bean = new InterfazGISEnviarDireccionConsultoras();
				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazGISEnviarDireccionConsultoras) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getTipoRegistro(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoMarca(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoCanal(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodigoCliente(),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.getCodigoTerritorio(), columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNombre(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getApellidoUno(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getApellidoDos(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getEstatus(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getVia(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumeroPrincipal(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getInterior(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getManzana(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getObservaciones(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getErrorUbicacion(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getSecuencialVia(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getUbigeo(),
							columnIndex++));

					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_GIS_REPORTE_DIRECCION_CONSULTORAS) { // REPORTE
				// EN
				// EXCEL
				InterfazGISReporteDireccionConsultoras bean = new InterfazGISReporteDireccionConsultoras();

				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFSheet sheet = wb.createSheet("REP-DIR-CONSULTORAS");

				for (int i = 0; i < registros.size(); i++) {
					bean = (InterfazGISReporteDireccionConsultoras) registros
							.get(i);
					columnIndex = 0;
					HSSFRow row = sheet.createRow(i);

					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoPais());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoMarca());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoCanal());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoZona());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoSeccion());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoTerritorio());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getErrorUbicacion());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoCliente());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getApellidoPaterno());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getApellidoMaterno());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getNombre());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCampanyaPrimerPedido());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getEstatus());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoTipoVia());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getDescripcionTipoVia());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getNombreVia());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getNumeroPuerta());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getInterior());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getManzana());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getLote());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getKilometro());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getUbigeo());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getDepartamento());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getProvincia());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getDistrito());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCodigoTipoCentroPoblado());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getTipoCentroPoblado());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getCentroPoblado());
					row.createCell((short) columnIndex++).setCellValue(
							bean.getTelefono());

					numRegistros++;
				}
				FileOutputStream f_out = new FileOutputStream(carpetaDestino
						+ nombreArchivo);
				wb.write(f_out);
				f_out.close();
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_CONTROL_FACTURACION) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.get("campaa"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("fecha"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_SUBGERENCIAS) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.get("codigo"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("descripcion"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_REGIONES) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean
							.get("codigoSubgerencia"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigo"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("descripcion"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_ZONAS) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean
							.get("codigoSubgerencia"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigoRegion"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigo"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("descripcion"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_CONSULTORAS) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean
							.get("codigoPrivilege"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigoCanal"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigo"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("codigoSubgerencia"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigoRegion"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigoZona"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("nombre"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("telefono"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("documentoIdentidad"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("condicion"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_PRODUCTOS) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.get("codigo"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("descripcion"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_STICKERS) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean
							.get("codigoPrivilege"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigo"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigoCanal"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("campaa"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("codigoConsultora"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("fecha"),
							columnIndex++));
					linea.append(aplicarNormaFormato(
							bean.get("codigoProducto"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("valorUnitario"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("factor"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("puntaje"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("codigoSubgerencia"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigoRegion"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigoZona"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("codigoTerritorio"), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("numeroImpresiones"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("puntajeFijo"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("statusAnulado"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_FICHAS_INSCRIPCION) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean
							.get("numeroFichaInscripcion"), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("statusImpresion"), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("campaaRegistro"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigo"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_TARJETAS) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.get("numeroTarjeta"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("statusImpresion"), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("campaaRegistro"), columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("codigoCliente"),
							columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			} else if (numeroInterfaz == Constants.INTERFAZ_PRI_DESCARGA_PREMIOS) {
				Map bean = null;
				for (int i = 0; i < registros.size(); i++) {
					bean = (Map) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;
					linea.append(aplicarNormaFormato(bean.get("numeroTarjeta"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("correlativo"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.get("status"),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("campaaSolicitud"), columnIndex++));
					linea.append(aplicarNormaFormato(bean
							.get("campaaDespacho"), columnIndex++));
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			/*
			 * Interfaz de Percepciones Venta Directa Cabecera Author: David
			 * Toledo
			 */
			else if (numeroInterfaz == Constants.INTERFAZ_MYE_PERCEPCIONES_VENTA_DIRECTA_CABECERA) {
				if (log.isDebugEnabled())
					log
							.debug("Archivo.java - Entering if INTERFAZ_MYE_PERCEPCIONES_VENTA_DIRECTA_CABECERA");

				// Map bean = null;
				PercepcionesVtaDirecta bean = new PercepcionesVtaDirecta();
				for (int i = 0; i < registros.size(); i++) {
					// bean = (Map) registros.get(i);
					bean = (PercepcionesVtaDirecta) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getSerCope(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumCope(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getConCons(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFecDole(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getMonPerc(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getCodAusu(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getEstado(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumLote(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getAuxiliar(),
							columnIndex++));

					if (log.isDebugEnabled())
						log.debug("linea=" + linea.toString());

					log.error("linea=" + linea.toString());
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			else if (numeroInterfaz == Constants.INTERFAZ_MYE_PERCEPCIONES_VENTA_DIRECTA_DETALLE) {
				log
						.error("Archivo.java - Entering if INTERFAZ_MYE_PERCEPCIONES_VENTA_DIRECTA_DETALLE  ");

				if (log.isDebugEnabled())
					log
							.debug(" Entering if INTERFAZ_MYE_PERCEPCIONES_VENTA_DIRECTA_DETALLE");

				PercepcionesVtaDirecta bean = new PercepcionesVtaDirecta();
				for (int i = 0; i < registros.size(); i++) {
					bean = (PercepcionesVtaDirecta) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodigoPais(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getSerCope(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumCope(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getTipDole(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getSerDole(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumDole(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFecDole(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getMonTodl(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getPorPerc(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getMonPerc(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getMonPago(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getAuxiliar(),
							columnIndex++));

					if (log.isDebugEnabled())
						log.debug("linea=" + linea.toString());

					log.error("linea=" + linea.toString());
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}
			}

			else if (numeroInterfaz == Constants.INTERFAZ_MYE_CODIGO_AUTORIZACION_SUNAT) {
				log
						.error("Archivo.java - Entering if INTERFAZ_MYE_CODIGO_AUTORIZACION_SUNAT  ");
				if (log.isDebugEnabled())
					log
							.debug(" Entering if INTERFAZ_MYE_CODIGO_AUTORIZACION_SUNAT");

				PercepcionesVtaDirecta bean = new PercepcionesVtaDirecta();
				for (int i = 0; i < registros.size(); i++) {
					bean = (PercepcionesVtaDirecta) registros.get(i);
					linea = new StringBuffer();
					columnIndex = 1;

					linea.append(aplicarNormaFormato(bean.getCodAuto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumAusu(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getFecAuto(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getSerDocu(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumInau(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getNumFiau(),
							columnIndex++));
					linea.append(aplicarNormaFormato(bean.getAuxiliar(),
							columnIndex++));

					if (log.isDebugEnabled())
						log.debug("linea=" + linea.toString());

					log.error("linea=" + linea.toString());
					linea.append(System.getProperty("line.separator"));
					out.write(linea.toString());
					numRegistros++;
				}

			}

			if (numeroInterfaz != Constants.INTERFAZ_GIS_REPORTE_DIRECCION_CONSULTORAS) {
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (out != null)
				try {
					out.flush();
					out.close();
					this.eliminar(carpetaDestino);
				} catch (IOException ex) {
					;
				}

			throw new GeneratingLocalFileException(e.getMessage(), e);
		} finally {
			if (out != null)
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					;
				}
		}
		log.debug("El Archivo: " + nombreArchivo
				+ " fue generado correctamente"
				+ System.getProperty("line.separator"));
	}

	/**
	 * Agrega un delimitador al buffer de linea de archivo.
	 * 
	 * @param delimitador
	 *            delimitador a agregar.
	 * 
	 * @param campo
	 *            Campo donde agregar delimitador al final.
	 * 
	 * @return Campo con delimitador al final
	 */
	private StringBuffer agregarDelimitador(String delimitador,
			StringBuffer campo) {
		String strValue = delimitador;
		char chrValue = Constants.CHAR_NULO;

		if (strValue.equalsIgnoreCase(Constants.STRING_TABULADOR)) {
			chrValue = Constants.CHAR_TABULADOR;
		}
		if (chrValue == Constants.CHAR_NULO)
			campo.append(strValue);
		else
			campo.append(chrValue);

		return campo;
	}

	private String getDelimitador() {
		if (this.delimitador == null) {
			return "";
		} else {
			return this.delimitador.getValor().trim();
		}
	}

	private String getFormato() {
		if (this.formato == null) {
			return "";
		} else {
			return this.formato.getValor().trim();
		}
	}

	private String getExtension() {
		if (interfaz.getExtensionArchivoDescripcion() == null) {
			return "";
		} else {
			return interfaz.getExtensionArchivoDescripcion();
		}
	}

	/**
	 * Funcion que realiza el logueo al FTP
	 * 
	 * @return FTP logueado
	 */
	public FTPClient loguearFTP() throws ExcepcionSistema {
		try {
			Integer iPort = new Integer(this.interfaz.getPuertoFtp());
			FTPClient ftp = new FTPClient();
			// Conectamos al servidor FTP
			ftp.connect(this.interfaz.getServidorFtp(), iPort.intValue());
			// Autenticarse en el servidor
			ftp.login(this.interfaz.getUsuarioFtp(), this.interfaz
					.getPasswordFtp());
			return ftp;
		} catch (Exception e) {
			ExcepcionSistema error = new ExcepcionSistema(
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
	public void cambiarDirectorioFTP(FTPClient ftp, String directorio)
			throws ExcepcionSistema {
		try {
			ftp.changeWorkingDirectory(directorio);

			// Verificamos que se haya podido cambiar al directorio de trabajo
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new IOException();
			}
		} catch (Exception e) {
			ExcepcionSistema error = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_CAMBIAR_DIRECTORIO_FTP);
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}

	/**
	 * Funcion que desconecta FTP
	 * 
	 * @param ftp
	 *            FTP logueado
	 */
	public void cerrarFTP(FTPClient ftp) throws ExcepcionSistema {
		try {
			// Desloguearse
			ftp.logout();

			// Desconectarse
			ftp.disconnect();
		} catch (Exception e) {
			ExcepcionSistema error = new ExcepcionSistema(e.toString());
			error.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw error;
		}
	}

	/**
	 * Copia Archivo guardado en el FTP en el directorio Entrada al Directorio
	 * Temporal
	 * 
	 * @param ftp
	 *            ftp logueado
	 * 
	 * @param dirFtpEntrada
	 *            directorio FTP de Entrada
	 * 
	 * @param dirFtpDestino
	 *            directorio FTP Destino
	 */
	public void copiarArchivoTemporalFtp(FTPClient ftp, String dirFtpEntrada,
			String dirFtpDestino) throws Exception {
		String dirTmp = this.formatearRutaFTP(dirFtpDestino);
		log.info("ENTRANDO METODO COPIAR FTP A FTP");
		try {
			// Ir al Subdirectorio de Trabajo
			try {
				this.cambiarDirectorioFTP(ftp, dirFtpEntrada);
			} catch (Exception e) {
				ExcepcionSistema errorSistema = new ExcepcionSistema(
						Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA);
				errorSistema
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw errorSistema;
			}

			/* Obteniendo Archivo */

			if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(
					Constants.ARCHIVO_VARIABLE)) {
				ftp.setFileType(FTP.ASCII_FILE_TYPE);
				FTPFile[] archivos = ftp.listFiles();
				boolean flag = false;
				for (int i = 0; i < archivos.length; i++) {
					if (!flag) {
						flag = identificadorArchivo(archivos[i].getName(),
								interfaz.getNombreArchivoEntradaSalida(),
								interfaz.getExtensionArchivoDescripcion());
						nombreArchivoTemporal = archivos[i].getName();
					}
				}
			} else {
				nombreArchivoTemporal = interfaz.getNombreArchivo(interfaz.getNombreArchivoEntradaSalida());
					
			}
			if (this.nombreArchivoTemporal == null) {
				throw new IOException();
			}

			String ruta = dirTmp + this.nombreArchivoTemporal;
			String entrada = this.formatearRutaFTP(dirFtpEntrada)
					+ this.nombreArchivoTemporal;

			log.info("Nombre del Archivo Temporal " + nombreArchivoTemporal);
			log.info("Mostrando la ruta ENTRADA :" + dirFtpEntrada);
			log.info("Mostrando la ruta TEMPORAL:" + ruta);

			// En caso no haya generado archivo en el Directorio Temporal
			if (!ftp.rename(entrada, ruta)) {
				this.recibirFTP(ftp, dirFtpEntrada, nombreArchivoTemporal,
						dirFtpDestino);
				this.cambiarDirectorioFTP(ftp, dirFtpEntrada);
				ftp.deleteFile(nombreArchivoTemporal);
			} else {
				// Verificamos que se haya podido copiar el archivo en el
				// directorio Temporal
				int reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					throw new StoringFileOverFtpException();
				}
			}
		} catch (ExcepcionSistema e) {
			throw e;
		} catch (IOException e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw errorSistema;
		} catch (StoringFileOverFtpException e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_TEMPORAL);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw errorSistema;
		} catch (Exception e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_TEMPORAL);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw errorSistema;
		}
	}

	/**
	 * Copia Archivo guardado en el directorio RED Entrada al directorio
	 * Temporal de Red
	 * 
	 */
	public void copiarArchivoTemporalRed() throws Exception {
		try {
			System.out.println("Entro METODO copiarArchivoTemporalRed");
			String dirEntrada = this.formatearRuta(this.interfaz
					.getDirectorioEntradaSalida());

			/* Verificando si es archivo variable o fijo */
			if (interfaz.getTipoNombreArchivo().equalsIgnoreCase(
					Constants.ARCHIVO_VARIABLE)) {
				File temporal;
				temporal = new File(dirEntrada);

				FilenameFilter patron = new InterfazNombreVariableFilter(nombreArchivo,
						interfaz.getExtensionArchivoDescripcion());
				File[] matchs = temporal.listFiles(patron);
				if (matchs.length > 0)
					nombreArchivoTemporal = matchs[0].getName();
				else {
					ExcepcionSistema errorSistema = new ExcepcionSistema(
							Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA);
					errorSistema
							.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
					throw errorSistema;
				}
			} else {
				nombreArchivoTemporal = interfaz.getNombreArchivo(nombreArchivo);
						
			}
			if (this.nombreArchivoTemporal == null) {
				ExcepcionSistema errorSistema = new ExcepcionSistema(
						Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA);
				errorSistema
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw errorSistema;
			}

			/* Copiando Archivo al Directorio Temporal */
			String archivoOrigen = this.formatearRuta(this.interfaz
					.getDirectorioEntradaSalida())
					+ nombreArchivoTemporal;
			String archivoDestino = this.formatearRuta(this.interfaz
					.getDirectorioTemporal())
					+ nombreArchivoTemporal;
			try {
				this.copiar(archivoOrigen, archivoDestino);
			} catch (Exception e) {
				ExcepcionSistema errorSistema = new ExcepcionSistema(
						Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_TEMPORAL);
				errorSistema
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
				throw errorSistema;
			}

			/* Eliminando Archivo en el Directorio Entrada */
			try {
				this.eliminarArchivo(archivoOrigen);
			} catch (Exception e) {
				ExcepcionSistema errorSistema = new ExcepcionSistema(
						Constants.INTERFAZSICC_ERROR_BORRAR_ARCHIVO_FTP_REAL);
				errorSistema
						.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_REAL);
				throw errorSistema;
			}
		} catch (ExcepcionSistema e) {
			throw e;
		} catch (Exception e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw errorSistema;
		}
	}

	/**
	 * 
	 * Copia Archivo guardado en el FTP al directorio de Red
	 * 
	 * @param delimitador
	 *            delimitador a agregar.
	 * 
	 * @param campo
	 *            Campo donde agregar delimitador al final.
	 * 
	 * @return Campo con delimitador al final
	 */
	public void recibirFTP(FTPClient ftp, String dirEntradaFTP, String archivo,
			String dirSalidaRed) throws Exception {
		String dirTmp;
		try {
			dirTmp = formatearRuta(dirSalidaRed);
			// Ir al Subdirectorio de Trabajo
			this.cambiarDirectorioFTP(ftp, dirEntradaFTP);

			/* Grabando en el Directorio en la Red */
			FileOutputStream fos = null;

			String ruta = dirTmp + archivo;
			fos = new FileOutputStream(ruta);
			ftp.retrieveFile(archivo, fos);

			// Verificamos que se haya podido copiar el archivo en la Red
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new StoringFileOverFtpException();
			}

			// Cerrando flujo
			fos.flush();
			fos.close();

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * METODO A ELIMINAR Copia Archivo guardado en el Directorio Entrada/Salida
	 * al directorio Temporal
	 * 
	 * @param delimitador
	 *            delimitador a agregar.
	 * 
	 * @param campo
	 *            Campo donde agregar delimitador al final.
	 * 
	 * @return Campo con delimitador al final
	 */
	public void recibirFTP() throws ExcepcionSistema {
		String dirTmp;
		try {
			dirTmp = formatearRuta(this.interfaz.getDirectorioTemporal());

			Integer iPort = new Integer(this.interfaz.getPuertoFtp());
			FTPClient ftp = new FTPClient();
			// Conectamos al servidor FTP
			ftp.connect(this.interfaz.getServidorFtp(), iPort.intValue());
			// Autenticarse en el servidor
			ftp.login(this.interfaz.getUsuarioFtp(), this.interfaz
					.getPasswordFtp());
			// Ir al Subdirectorio de Trabajo
			ftp.changeWorkingDirectory(this.interfaz
					.getDirectorioEntradaSalida());

			// Verificamos que se haya podido cambiar al directorio de trabajo
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				try {
					ftp.disconnect();
				} catch (Exception ignore) {
				}
				throw new IOException();
			}

			FileOutputStream fos = null;
			ftp.setFileType(FTP.ASCII_FILE_TYPE);
			FTPFile[] archivos = ftp.listFiles();
			boolean flag = false;
			for (int i = 0; i < archivos.length; i++) {
				if (!flag) {
					flag = identificadorArchivo(archivos[i].getName(), interfaz
							.getNombreArchivoEntradaSalida(), interfaz
							.getExtensionArchivoDescripcion());
					nombreArchivoTemporal = archivos[i].getName();
				}
			}
			if (this.nombreArchivoTemporal == null) {
				throw new IOException();
			}

			String ruta = dirTmp + this.nombreArchivoTemporal;
			System.out.println("Mostrando la ruta TEMPORAL a grabar:" + ruta);
			System.out.println("Nombre del Archivo Temporal "
					+ nombreArchivoTemporal);
			fos = new FileOutputStream(ruta);
			ftp.retrieveFile(nombreArchivoTemporal, fos);

			// Verificamos que se haya podido copiar el archivo de entrada al
			// Temporal
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				try {
					ftp.disconnect();
				} catch (IOException ignore) {
				}
				throw new StoringFileOverFtpException();
			}

			// Desloguearse
			ftp.logout();
			fos.close();

			// Desconectarse
			ftp.disconnect();
		} catch (IOException e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_RECIBIR_ARCHIVO_ENTRADA);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw errorSistema;
		} catch (StoringFileOverFtpException e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_TEMPORAL);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw errorSistema;
		} catch (Exception e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(
					Constants.INTERFAZSICC_ERROR_ENVIAR_ARCHIVO_FTP_TEMPORAL);
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw errorSistema;
		}
	}

	private boolean identificadorArchivo(String nombre, String inicio,
			String fin) {
		boolean valor = nombre.endsWith(fin);
		valor = valor && nombre.startsWith(inicio);
		if (StringUtils.isBlank(fin))
			valor = nombre.startsWith(inicio);
		return valor;
	}

	public Object[] leerArchivo(Usuario usuario) throws Exception {
		int contRecorre = 0;
		FileReader fr = null;
		BufferedReader br = null;
		HashMap resultado = null;
		String mensajesError = "";
		Object[] devoluciones = new Object[2];
		ArrayList registros = new ArrayList();
		System.out.println("Entrando metodo LEER_ARCHIVO");

		FTPClient ftp = new FTPClient();
		/* Grabando el Archivo en el Directorio Temporal */
		try {
			if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_FTP)) {
				ftp = this.loguearFTP();
				this.copiarArchivoTemporalFtp(ftp, this.interfaz
						.getDirectorioEntradaSalida(), this.interfaz
						.getDirectorioTemporal());
			} else {
				this.copiarArchivoTemporalRed();
			}
		} catch (ExcepcionSistema e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		/* Adicionando informacion a la variable Registros */
		try {
			String linea = null;
			boolean encontroFTP = false;
			if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_FTP)) { // En caso sea FTP
				try {
					InputStream ff = ftp
							.retrieveFileStream(formatearRutaFTP(this.interfaz
									.getDirectorioTemporal())
									+ nombreArchivoTemporal);
					br = new BufferedReader(new InputStreamReader(ff));

					while ((linea = StringUtils.trimToNull(br.readLine())) != null) {
						resultado = new HashMap(descomponeLinea(linea));
						registros.add(resultado);
						contRecorre++;
					}
					ff.close();
					br.close();
					encontroFTP = true;
				} catch (Exception e) {
					encontroFTP = false;
				}
			}
			if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_RED)
					|| (!encontroFTP)) { // En caso sea RED
				fr = new FileReader(formatearRuta(this.interfaz
						.getDirectorioTemporal())
						+ nombreArchivoTemporal);
				br = new BufferedReader(fr);
				while ((linea = StringUtils.trimToNull(br.readLine())) != null) {
					resultado = new HashMap(descomponeLinea(linea));
					registros.add(resultado);
					contRecorre++;
				}
				fr.close();
				br.close();
			}
		} catch (Exception e) {
			ExcepcionSistema errorSistema = new ExcepcionSistema(e.toString());
			errorSistema
					.setCodigoError(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_TEMP);
			throw errorSistema;
		}

		/* Grabando Archivo en el Directorio Historico */
		try {
			if (interfaz.getFlagEnvioArchivo().trim().equalsIgnoreCase(
					Constants.ENVIO_FTP)) { // En caso sea FTP
				this.cerrarFTP(ftp);
				ftp = new FTPClient();
				ftp = this.loguearFTP();

				this.grabarHistoricoFTP(ftp); // Grabando en el Historico FTP
				this.cerrarFTP(ftp);
			} else { // En caso sea RED
				this.grabarHistoricoRed(); // Grabado en el Historico Red
			}
		} catch (ExcepcionSistema e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		devoluciones[0] = registros;
		devoluciones[1] = mensajesError;
		return devoluciones;
	}

	public Map descomponeLinea(String linea) throws IllegalAccessException,
			InvocationTargetException, Exception {
		Map resultado = new HashMap();
		List lcolumna = estructuraArchivoInterfaz;
		EstructuraArchivo columna = null;
		int tamc = lcolumna.size();
		int ic = 0;
		if (interfaz.getFlagDelimitadorCampos().equalsIgnoreCase(Constants.SI)) {
			log
					.debug("=================== PARA EL CASO QUE TENGA DELIMITADOR ===================");
			String separador = delimitador.getValor();
			log.debug("DELIMITADOR " + separador);
			String[] tokens = StringUtils.splitPreserveAllTokens(linea,
					separador);
			while (ic < tamc) {
				columna = getItemEstructura(ic + 1);
				String cadena = "";

				log.debug("Columna #: " + (ic + 1));
				log.debug("Columna - Nombre Columna: "
						+ columna.getIdentificadorCampo());
				cadena = tokens[ic];
				log.debug("CADENA: " + cadena);
				ic++;
				if (interfaz.getFlagFormatoArchivo().equalsIgnoreCase(
						Constants.SI)) {
					String extremo = StringUtils.stripToEmpty(formato
							.getValor());
					log.debug("EXTREMO: " + formato.getValor());
					cadena = StringUtils.strip(cadena, extremo);
					log.debug("CADENA: " + cadena);
				}
				cadena = StringUtils.trimToEmpty(cadena);
				log.debug("Valor: " + cadena + " --> Columna: "
						+ columna.getIdentificadorCampo());
				if (columna.getTipoDato().getSigla().compareToIgnoreCase(
						Constants.TIPO_DATO_NUMERICO) == 0) {
					if (normaInterfaz.getFlagRellenoAlfanumerico()
							.compareToIgnoreCase(Constants.SI) == 0) {
						String relleno = normaInterfaz.getRellenoAlfanumerico();

						int longitud = columna.getLongitudCampo()
								+ columna.getCantidadDecimales()
								+ ((columna.getCantidadDecimales() > 0) ? 1 : 0);
						cadena = StringUtils.leftPad(cadena, longitud, relleno);
					}
				} else {
					if (normaInterfaz.getFlagRellenoNumerico()
							.compareToIgnoreCase(Constants.SI) == 0) {
						String relleno = normaInterfaz.getRellenoNumerico();

						int longitud = columna.getLongitudCampo();
						cadena = StringUtils
								.rightPad(cadena, longitud, relleno);
					}
				}
				log.debug("KEY: " + columna.getIdentificadorCampo()
						+ "/ VALOR:" + cadena);
				if (Constants.INTERFAZ_TIPO_ENTRADA.equals(this.interfaz
						.getTipo()))
					cadena = cadena.trim(); // Eliminando espacios en blancos en
											// la cadena
				resultado.put(columna.getIdentificadorCampo(), cadena);
			}
		} else {
			log
					.debug("=================== PARA EL CASO QUE TENGA LONGITUD FIJA ===================");
			int ini = 0;
			int fin = 0;
			int var = 0;
			while (ic < tamc) {
				columna = getItemEstructura(ic + 1);
				log.debug("LONGITUD DEL CAMPO " + columna.getLongitudCampo()
						+ " " + columna.getCantidadDecimales());
				ic++;
				String cadena = "";
				log.debug("Columna N: " + (ic));
				log.debug("Columna - Nombre Columna: "
						+ columna.getIdentificadorCampo());
				if (interfaz.getFlagDelimitadorCampos().equalsIgnoreCase(
						Constants.SI)) {
					var = delimitador.getValor().length();
				} else {
					var = 0;
				}
				int longitud;
				longitud = columna.getLongitudCampo();
				log.debug("LONGITUD TOTAL DEL CAMPO " + longitud);
				if (columna.getTipoDato().getSigla().compareToIgnoreCase(
						Constants.TIPO_DATO_NUMERICO) == 0) {
					ini = fin + var;
					fin = ini + longitud;
					cadena = linea.substring(ini, fin);
					int decimales = columna.getCantidadDecimales();
					if (decimales > 0) {
						String tempo = StringUtils.trimToEmpty(cadena
								.substring(0, longitud - decimales))
								+ "."
								+ cadena.substring(longitud - decimales,
										longitud);
						cadena = tempo;
					}
					log.debug("CADENA NUMERICA: " + cadena);
				} else {
					ini = fin + var;
					fin = ini + longitud;
					try {
						cadena = linea.substring(ini, fin);
					} catch (StringIndexOutOfBoundsException e) {
						cadena = linea.substring(ini);
					}
					log.debug("CADENA ALFABETICA: " + cadena);
				}
				if (interfaz.getFlagFormatoArchivo().equalsIgnoreCase(
						Constants.SI)) {
					String extremo = StringUtils.stripToEmpty(formato
							.getValor());
					log.debug("EXTREMO: " + formato.getValor());
					cadena = StringUtils.strip(cadena, extremo);
					log.debug("CADENA: " + cadena);
				}
				cadena = StringUtils.trimToEmpty(cadena);
				log.debug("CADENA: " + cadena);
				/*
				 * if (log.isDebugEnabled()) log.debug("CADENA " + cadena + "
				 * PARA LA COLUMNA: " + columna.getNombreColumna());
				 */
				if (columna.getTipoDato().getSigla().compareToIgnoreCase(
						Constants.TIPO_DATO_NUMERICO) == 0) {
					if (normaInterfaz.getFlagRellenoNumerico()
							.compareToIgnoreCase(Constants.SI) == 0) {
						String relleno = normaInterfaz.getRellenoNumerico();
						cadena = StringUtils.leftPad(cadena, longitud, relleno);
						log.debug("CADENA: " + cadena);
					}
				} else {
					if (normaInterfaz.getFlagRellenoAlfanumerico()
							.compareToIgnoreCase(Constants.SI) == 0) {
						String relleno = normaInterfaz.getRellenoAlfanumerico();
						cadena = StringUtils
								.rightPad(cadena, longitud, relleno);
						log.debug("CADENA: " + cadena);
					}
				}
				log.debug("KEY: " + columna.getIdentificadorCampo()
						+ "/ VALOR:" + cadena);
				if (Constants.INTERFAZ_TIPO_ENTRADA.equals(this.interfaz
						.getTipo()))
					cadena = cadena.trim(); // Eliminando espacios en blancos en
											// la cadena
				resultado.put(columna.getIdentificadorCampo(), cadena);
				fin = fin + var;
			}
		}
		return resultado;
	}
}
