package biz.belcorp.ssicc.service.sisicc.framework.formatter.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.InterfazFormatterStrategy;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.util.InterfazFormatterUtil;

/**
 * Implementa el formateo y el parseo de archivos con separador.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazFormatterSeparatorStrategy")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazFormatterSeparatorStrategyImpl implements
		InterfazFormatterStrategy {
	
	protected final Log log = LogFactory.getLog(getClass());
	
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
			
			// Agrega los delimitadores
			result = InterfazFormatterUtil.delimitFieldCabecera(result, interfazFormat,
					estructuraArchivo);
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
			result = InterfazFormatterUtil.convertToString(field,
					interfazFormat, estructuraArchivo);
			if (StringUtils.isNotBlank(result)) {
				// Realiza el formateo si el campo es numerico
				result = InterfazFormatterUtil.formatNumber(result,
						interfazFormat, estructuraArchivo);
			}

			if (StringUtils.isNotBlank(result)) {
				// Trunca el campo a su longitud
				result = InterfazFormatterUtil.truncateField(result,
						interfazFormat, estructuraArchivo);
			}
			if (archivoBinario) {
				this.generarDatoArchivoBinario(interfazFormat, tipoDato, result);
			}
			
			// Agrega los delimitadores
			result = InterfazFormatterUtil.delimitField(result, interfazFormat,
					estructuraArchivo);
		} catch (Exception e) {
			log.error("Error al formatear el campo con separador: "
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

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterStrategy#parseLine(java.lang.String, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat)
	 */
	public Map parseLine(String line, InterfazFormat interfazFormat) {
		Map result = new HashMap();
		String separator = interfazFormat.getDelimitador().getValor();
		List estructuras = interfazFormat.getEstructurasArchivo();

		String[] tokens = null;
		if(StringUtils.equals(separator, "\\t"))
		{
			tokens = StringUtils.splitPreserveAllTokens(line, "\t");
		}
		else
		{
			tokens = StringUtils.splitPreserveAllTokens(line, separator);
		}
		
		for (int i = 0; i < estructuras.size(); i++) {
			EstructuraArchivo estructura = (EstructuraArchivo) estructuras
					.get(i);
			String tipoDato = estructura.getTipoDato().getSigla();
			String field = tokens[i];

			if (tipoDato.equals(Constants.TIPO_DATO_NUMERICO)) {
				// Decimales
				int dec = estructura.getCantidadDecimales();
				int len = field.length();
				if (dec > 0) {
					field = StringUtils.substring(field, 0, len - dec) + "."
							+ StringUtils.substring(field, len - dec, len);
				}
				//Se quita los espacios en blancos, para evitar que se caiga 
				BigDecimal bigDecimal = new BigDecimal(StringUtils.trim(field));
				result.put(estructura.getIdentificadorCampo(), bigDecimal);
			}

			else if (tipoDato.equals(Constants.TIPO_DATO_CARACTER)) {
				// Quitar el formato
				if (interfazFormat.getInterfaz().getFlagFormatoArchivo()
						.equalsIgnoreCase(Constants.SI)) {
					String formato = interfazFormat.getFormato().getValor();
					field = StringUtils.removeStart(field, formato);
					field = StringUtils.removeEnd(field, formato);
                    field = StringUtils.trimToEmpty(field);
				}
				result.put(estructura.getIdentificadorCampo(), field);
			}

		}
		return result;
	}
}
