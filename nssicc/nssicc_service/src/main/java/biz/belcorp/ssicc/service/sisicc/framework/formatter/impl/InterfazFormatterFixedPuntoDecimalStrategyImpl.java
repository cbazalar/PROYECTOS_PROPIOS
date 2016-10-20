package biz.belcorp.ssicc.service.sisicc.framework.formatter.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.InterfazFormatterStrategy;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.util.InterfazFormatterUtil;

/**
 * Implementa el formateo y el parseo de archivos de longitud fija con punto Decimal.
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 */
@Service("sisicc.interfazFormatterFixedPuntoDecimalStrategyImpl")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazFormatterFixedPuntoDecimalStrategyImpl implements
		InterfazFormatterStrategy {
	protected final Log log = LogFactory.getLog(this.getClass());
	public static final String PUNTO_DECIMAL = ".";
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterStrategy#formatTituloCabecera(java.lang.Object, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat, biz.belcorp.ssicc.sisicc.model.EstructuraArchivo)
	 */
	public String formatTituloCabecera(Object field, InterfazFormat interfazFormat,
			EstructuraArchivo estructuraArchivo) {
		String result = "";
		boolean archivoBinario = interfazFormat.isArchivoBinario();
		String tipoDato = estructuraArchivo.getTipoDato().getSigla();
		
		try {
			// Convierte el campo a un String
			if (field != null) {
				result = field.toString().trim();
			}
			if (archivoBinario) {
				this.generarDatoArchivoBinario(interfazFormat, result);
			}
						
		} catch (Exception e) {
			log.error("Error al formatear el campo con separador: "
					+ e.getMessage());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterStrategy#formatField(java.lang.Object, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat, biz.belcorp.ssicc.sisicc.model.EstructuraArchivo)
	 */
	public String formatField(Object field, InterfazFormat interfazFormat,
			EstructuraArchivo estructuraArchivo) {
		String result = "";
		boolean archivoBinario = interfazFormat.isArchivoBinario();
		String tipoDato = estructuraArchivo.getTipoDato().getSigla();
		
		try {
			// Convierte el campo a un String
			String convertedToStringField = InterfazFormatterUtil
					.convertToString(field, interfazFormat, estructuraArchivo);

			// Realiza el formateo si el campo es numerico
			String numberFormattedField = this.formatNumber(
					convertedToStringField, interfazFormat, estructuraArchivo);

			// Alinea el campo y aplica el relleno
			String alignedField = InterfazFormatterUtil.alignField(
					numberFormattedField, interfazFormat, estructuraArchivo);

			// Trunca el campo a su longitud
			String truncatedField = InterfazFormatterUtil.truncateField(
					alignedField, interfazFormat, estructuraArchivo);

			result = truncatedField;
			if (archivoBinario) {
				this.generarDatoArchivoBinario(interfazFormat, tipoDato, result);
			}
			
		} catch (Exception e) {
			this.log.error("Error al formatear el campo de longitud fija: "
					+ e.getMessage());
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterStrategy#generarDatoArchivoBinario(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat, java.lang.String)
	 */
	public void generarDatoArchivoBinario(InterfazFormat interfazFormat, String result) throws Exception {
		interfazFormat.getExcelUtil().generarCelda(result);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterStrategy#generarDatoArchivoBinario(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat, java.lang.String, java.lang.String)
	 */
	public void generarDatoArchivoBinario(InterfazFormat interfazFormat, String tipoDato, String result) throws Exception {
		interfazFormat.getExcelUtil().generarCelda(tipoDato, result);
	}

	/**
	 * Formateador de numeros
	 * @param field
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return
	 */
	public String formatNumber(String field,
			InterfazFormat interfazFormat, EstructuraArchivo estructuraArchivo) {
		String result = field;
		String tipoDato = estructuraArchivo.getTipoDato().getSigla();

		if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)
				&& NumberUtils.isNumber(field)) {
			String number = field;
			if (estructuraArchivo.getCantidadDecimales() > 0) {
				BigDecimal b = new BigDecimal(number);
				number = b.toString();
	
				// Formato de decimales
				if (number.indexOf(".") > 0) {
					result = StringUtils.substring(number, 0, StringUtils
								.indexOf(number, '.'))	+ PUNTO_DECIMAL +
								StringUtils.rightPad(StringUtils.substring(
										number,
										StringUtils.indexOf(number, '.') + 1),
										estructuraArchivo.getCantidadDecimales(),
										'0');
				}
				else {
					result = number + PUNTO_DECIMAL +
					         StringUtils.rightPad("0", estructuraArchivo
							     .getCantidadDecimales(), '0');
				}
					
			}	
		}
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterStrategy#parseLine(java.lang.String, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat)
	 */
	public Map parseLine(String line, InterfazFormat interfazFormat) {
		Map result = new HashMap();
		NormaInterfaz normaInterfaz = interfazFormat.getNormaInterfaz();
		List estructuras = interfazFormat.getEstructurasArchivo();

		int start = 0;
		String field = null;
		for (Iterator iter = estructuras.iterator(); iter.hasNext();) {
			EstructuraArchivo estructura = (EstructuraArchivo) iter.next();
			String tipoDato = estructura.getTipoDato().getSigla();

			int length = estructura.getLongitudCampo();
			field = StringUtils.substring(line, start, start + length);
			start = start + length;

			if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)) {
				// Relleno y alineacion
				if (normaInterfaz.getFlagRellenoNumerico().equals(Constants.SI)) {
					if (normaInterfaz.getAlineamientoNumerico().equals(
							Constants.IZQUIERDA)) {
						field = StringUtils.stripEnd(field, normaInterfaz
								.getRellenoNumerico());
					} else if (normaInterfaz.getAlineamientoNumerico().equals(
							Constants.DERECHA)) {
						field = StringUtils.stripStart(field, normaInterfaz
								.getRellenoNumerico());
					}
				}

				// Para el caso de que todos caracteres sean de relleno, la
				// cadena puede quedar vacia luego de quitar los caracteres de
				// relleno y seria un 0
				if (StringUtils.isBlank(field)) {
					result.put(estructura.getIdentificadorCampo(),
							new BigDecimal(0));
					continue;
				}

				// Decimales
				int dec = estructura.getCantidadDecimales();
				int len = field.length();
				if (dec > 0) {
					field = StringUtils.substring(field, 0, len - dec) + "."
							+ StringUtils.substring(field, len - dec, len);
				}
				try {
					BigDecimal bigDecimal = new BigDecimal(field);
					result.put(estructura.getIdentificadorCampo(), bigDecimal);
					continue;
				} catch (Exception e) {
					this.log.error("Error al convertir el campo a numero, campo="
							+ estructura.getIdentificadorCampo() + ", valor="
							+ field);
				}
			}

			else if (tipoDato.equals(Constants.TIPO_DATO_CARACTER)) {
				// Relleno y alineacion
				if (normaInterfaz.getFlagRellenoAlfanumerico().equals(
						Constants.SI)) {
					if (normaInterfaz.getAlineamientoAlfanumerico().equals(
							Constants.IZQUIERDA)) {
						field = StringUtils.stripEnd(field, normaInterfaz
								.getRellenoAlfanumerico());
					} else if (normaInterfaz.getAlineamientoAlfanumerico()
							.equals(Constants.DERECHA)) {
						field = StringUtils.stripStart(field, normaInterfaz
								.getRellenoAlfanumerico());
					}
				}
				result.put(estructura.getIdentificadorCampo(), field);
				continue;
			}
		}
		return result;
	}
}
