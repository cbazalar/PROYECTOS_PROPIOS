package biz.belcorp.ssicc.service.sisicc.framework.formatter.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;

/**
 * Metodos estticos para el formateo de los archivos en las Interfaces de
 * Salida.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
/**
 * @author pecbazalar
 *
 */
public class InterfazFormatterUtil {
	public static final String DEFAULT_RELLENO_NUMERICO = "0";

	public static final String DEFAULT_RELLENO_ALFANUMERICO = " ";

	/**
	 * Funcion para alineamiento de campos
	 * @param field
	 * @param interfazFormatter
	 * @param estructuraArchivo
	 * @return
	 */
	public static String alignField(String field,
			InterfazFormat interfazFormatter,
			EstructuraArchivo estructuraArchivo) {
		try {
			NormaInterfaz normaInterfaz = interfazFormatter.getNormaInterfaz();
			String tipoDato = estructuraArchivo.getTipoDato().getSigla();

			if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)) {
				/* INI CBAZALAR */
				if (normaInterfaz.getFlagRellenoNumerico().equals(Constants.NO)) {
					String relleno = DEFAULT_RELLENO_ALFANUMERICO;
					if (normaInterfaz.getFlagAlineamientoNumerico().equals(
							Constants.SI)) {
						if (normaInterfaz.getAlineamientoNumerico().equals(
								Constants.IZQUIERDA)) {
							// Alineamos el valor a la izquierda, y rellenamos
							// el resto de los campos con el valor de relleno,
							// hasta completar la longitud del campo
							return StringUtils.rightPad(field,
									estructuraArchivo.getLongitudCampo(),
									relleno);
						} else if (normaInterfaz.getAlineamientoNumerico()
								.equals(Constants.DERECHA)) {

							// Alineamos el valor a la derecha, y rellenamos el
							// resto de los campos con el valor de relleno,
							// hasta completar la longitud del campo
							String leftPadded = StringUtils.leftPad(field,
									estructuraArchivo.getLongitudCampo(),
									relleno);
							return leftPadded;
						}
					}
				}
				/* FIN CBAZALAR */
				
				if (normaInterfaz.getFlagRellenoNumerico().equals(Constants.SI)) {
					String relleno = normaInterfaz.getRellenoNumerico();
					if (StringUtils.isBlank(relleno)) {
						relleno = DEFAULT_RELLENO_NUMERICO;
					}

					if (normaInterfaz.getFlagAlineamientoNumerico().equals(
							Constants.SI)) {
						if (normaInterfaz.getAlineamientoNumerico().equals(
								Constants.IZQUIERDA)) {
							// Alineamos el valor a la izquierda, y rellenamos
							// el resto de los campos con el valor de relleno,
							// hasta completar la longitud del campo
							return StringUtils.rightPad(field,
									estructuraArchivo.getLongitudCampo(),
									relleno);
						} else if (normaInterfaz.getAlineamientoNumerico()
								.equals(Constants.DERECHA)) {
							// Cambio para los numeros negativos. El signo debe
							// ir al inicio por ejemplo para un N(12,2) seria:
							// -00000012310
							// Se esta agregando un flag de numeros negativos y
							// quitando el signo al numero para colocarlo al
							// final luego del relleno en caso sea numero
							// negativo
							boolean isNegative = false;
							if (field.startsWith("-")) {
								isNegative = true;
								field = field.substring(1);
							}

							// Alineamos el valor a la derecha, y rellenamos el
							// resto de los campos con el valor de relleno,
							// hasta completar la longitud del campo
							String leftPadded = StringUtils.leftPad(field,
									estructuraArchivo.getLongitudCampo(),
									relleno);

							// Cambio para los numeros negativos, pongo el signo
							// menos al inicio.
							if (isNegative) {
								return "-" + leftPadded.substring(1);
							}
							return leftPadded;
						}
					}
				}
			} else if (tipoDato.equals(Constants.TIPO_DATO_CARACTER)) {
				if (normaInterfaz.getFlagRellenoAlfanumerico().equals(
						Constants.SI)) {
					String relleno = normaInterfaz.getRellenoAlfanumerico();
					if (StringUtils.isBlank(relleno)) {
						relleno = DEFAULT_RELLENO_ALFANUMERICO;
					}

					if (normaInterfaz.getFlagAlineamientoAlfanumerico().equals(
							Constants.SI)) {
						if (normaInterfaz.getAlineamientoAlfanumerico().equals(
								Constants.IZQUIERDA)) {
							// Alineamos el valor a la izquierda, y rellenamos
							// el resto de los campos con el valor de relleno,
							// hasta completar la longitud del campo
							return StringUtils.rightPad(field,
									estructuraArchivo.getLongitudCampo(),
									relleno);
						} else if (normaInterfaz.getAlineamientoAlfanumerico()
								.equals(Constants.DERECHA)) {
							// Alineamos el valor a la derecha, y rellenamos el
							// resto de los campos con el valor de relleno,
							// hasta completar la longitud del campo
							return StringUtils.leftPad(field, estructuraArchivo
									.getLongitudCampo(), relleno);
						}
					}
				}
			}
		} catch (Exception e) {
			// No se imprime un log para no disminuir la performance
		}
		return "";
	}

	/**
	 * Aplica el delimitador al final del campo.
	 * 
	 * @param field
	 *            campo a formatear
	 * @param interfazFormat
	 *            parametros de formato
	 * @return campo formateado
	 */
	private static String aplicarDelimitador(String field,
			InterfazFormat interfazFormat) {
		String delimitador = getDelimitador(interfazFormat);
		if (StringUtils.isNotBlank(delimitador)) {
			return StringEscapeUtils.unescapeJava(field + delimitador);
		} else {
			return field;
		}
	}

	/**
	 * Aplica el formato al campo, encierra el campo entre comillas o
	 * apostrofes.
	 * 
	 * @param field
	 *            campo a formatear
	 * @param interfazFormat
	 *            parametros de formato
	 * @return campo formateado
	 */
	private static String aplicarFormato(String field,
			InterfazFormat interfazFormat) {
		String formato = getFormato(interfazFormat);
		if (StringUtils.isNotBlank(formato)) {
			return formato + field + formato;
		} else {
			return field;
		}
	}

	/**
	 * @param field
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return
	 */
	public static String convertToString(Object field,
			InterfazFormat interfazFormat, EstructuraArchivo estructuraArchivo) {
		// Para el caso que la BD devuelva un Date
		if (field instanceof Date) {
			Date date = (Date) field;
			String formattedDate = DateFormatUtils.format(date, interfazFormat
					.getNormaInterfaz().getFormatoFecha());
			return formattedDate;
		}
		// Los otros casos se toman com String
		else {
			if (field != null) {
				return field.toString().trim();
			}
		}
		return "";
	}

	/**
	 * @param field
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return
	 */
	public static String delimitFieldCabecera(String field,
			InterfazFormat interfazFormat, EstructuraArchivo estructuraArchivo) {
		String result = field;
		
		// Agrego el delimitador al final
		if (interfazFormat.getInterfaz().getFlagDelimitadorCampos()
				.equalsIgnoreCase(Constants.SI)) {
			// No se agrega el delimitador si es el ultimo campo
			if (estructuraArchivo.getPosicionCampo() < interfazFormat
					.getNumeroCampos()) {
				result = aplicarDelimitador(result, interfazFormat);
			}
		}
		return result;
	}

	
	/**
	 * @param field
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return
	 */
	public static String delimitField(String field,
			InterfazFormat interfazFormat, EstructuraArchivo estructuraArchivo) {
		String result = field;
		String tipoDato = estructuraArchivo.getTipoDato().getSigla();

		// Agrego los caracteres de formato (comilla o apostrofe)
		if (interfazFormat.getInterfaz().getFlagFormatoArchivo()
				.equalsIgnoreCase(Constants.SI)) {
			if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)
					&& interfazFormat.getInterfaz().getFlagTipoFormato()
							.equalsIgnoreCase(Constants.FORMATO_ALFANUMERICO)) {
				// En este caso no se aplica el formato ya que el tipo de dato
				// es numerico y solo se aplica el formato a los alfanumericos
			} else {
				result = aplicarFormato(result, interfazFormat);
			}
		}

		// Agrego el delimitador al final
		if (interfazFormat.getInterfaz().getFlagDelimitadorCampos()
				.equalsIgnoreCase(Constants.SI)) {
			// No se agrega el delimitador si es el ultimo campo
			if (estructuraArchivo.getPosicionCampo() < interfazFormat
					.getNumeroCampos()) {
				result = aplicarDelimitador(result, interfazFormat);
			}
		}
		return result;
	}

	/**
	 * @param field
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return
	 */
	public static String formatNumber(String field,
			InterfazFormat interfazFormat, EstructuraArchivo estructuraArchivo) {
		String result = field;
		String tipoDato = estructuraArchivo.getTipoDato().getSigla();

		if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)
				&& NumberUtils.isNumber(field)) {
			String number = field;
			BigDecimal b = new BigDecimal(number);
			number = b.toString();

			// Formato de decimales
			if (number.indexOf(".") > 0) {
				if (estructuraArchivo.getCantidadDecimales() > 0) {
					result = StringUtils.substring(number, 0, StringUtils
							.indexOf(number, '.'))
							+ StringUtils.rightPad(StringUtils.substring(
									number,
									StringUtils.indexOf(number, '.') + 1),
									estructuraArchivo.getCantidadDecimales(),
									'0');
				} else {
					result = StringUtils.substring(number, 0, StringUtils
							.indexOf(number, '.'));
				}
			} else {
				if (estructuraArchivo.getCantidadDecimales() > 0) {
					result = number
							+ StringUtils.rightPad("0", estructuraArchivo
									.getCantidadDecimales(), '0');
				} else {
					result = number;
				}
			}
		}
		return result;
	}

	/**
	 * @param interfazFormat
	 * @return
	 */
	private static String getDelimitador(InterfazFormat interfazFormat) {
		return interfazFormat.getDelimitador().getValor();
	}

	/**
	 * @param interfazFormat
	 * @return
	 */
	private static String getFormato(InterfazFormat interfazFormat) {
		return interfazFormat.getFormato().getValor();
	}

	/**
	 * @param field
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return
	 */
	public static String truncateField(String field,
			InterfazFormat interfazFormat, EstructuraArchivo estructuraArchivo) {
		String result = field;
		try {
			NormaInterfaz normaInterfaz = interfazFormat.getNormaInterfaz();
			String tipoDato = estructuraArchivo.getTipoDato().getSigla();

			// Truncamiento Numerico
			if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)
					&& normaInterfaz.getFlagTruncamientoNumerico().equals(
							Constants.SI)) {
				if (normaInterfaz.getTruncamientoNumerico().equals(
						Constants.IZQUIERDA)) {
					return StringUtils.right(field, estructuraArchivo
							.getLongitudCampo());
				} else if (normaInterfaz.getTruncamientoNumerico().equals(
						Constants.DERECHA)) {
					return StringUtils.left(field, estructuraArchivo
							.getLongitudCampo());
				}
			}
			// Truncamiento Alfanumerico
			else if (tipoDato.equals(Constants.TIPO_DATO_CARACTER)
					&& normaInterfaz.getFlagTruncamientoAlfanumerico().equals(
							Constants.SI)) {
				if (normaInterfaz.getTruncamientoAlfanumerico().equals(
						Constants.IZQUIERDA)) {
					return StringUtils.right(field, estructuraArchivo
							.getLongitudCampo());
				} else if (normaInterfaz.getTruncamientoAlfanumerico().equals(
						Constants.DERECHA)) {
					return StringUtils.left(field, estructuraArchivo
							.getLongitudCampo());
				}
			}
		} catch (Exception e) {
			// No se imprime un log para no disminuir la performance
		}
		return result;
	}
	
	/**
	 * @param field
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return
	 */
	public static String convertToStringCSV(String field, InterfazFormat interfazFormat, EstructuraArchivo estructuraArchivo) {
		String retorno = field;
		boolean archivoCSV = interfazFormat.esArchivoTipoCSV();
		String tipoDato = estructuraArchivo.getTipoDato().getSigla();

		if (!tipoDato.equals(Constants.TIPO_DATO_CARACTER)) return retorno;
		
		if (!archivoCSV) return retorno;
		if (StringUtils.isBlank(field)) return retorno;
		if (field.length() < 1) return retorno;
		
		retorno = "=T(\"" + retorno + "\")";
		return retorno;
	}
	
}
