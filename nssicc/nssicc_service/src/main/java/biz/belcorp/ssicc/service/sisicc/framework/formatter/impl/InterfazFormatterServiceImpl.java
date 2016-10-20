package biz.belcorp.ssicc.service.sisicc.framework.formatter.impl;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.InterfazFormatterService;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.InterfazFormatterStrategy;

@Service("sisicc.interfazFormatterService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazFormatterServiceImpl implements InterfazFormatterService {
	protected final Log log = LogFactory.getLog(getClass());

	private Map formatterStrategies;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterService#formatTituloCabecera(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat)
	 */
	public String formatTituloCabecera(Map row, InterfazFormat interfazFormat) {
		StringBuffer result = new StringBuffer();

		InterfazFormatterStrategy formatterStrategy = getInterfazFormatterStrategy(interfazFormat);
		Iterator iterator = interfazFormat.getEstructurasArchivo().iterator();
		//if (log.isDebugEnabled()) log.debug("Ingresando metodo formatMap");

		// Itero sobre todos los campos de la fila definidos en la estructura de
		// archivo
		short columna = 0;
		boolean archivoBinario = interfazFormat.isArchivoBinario();
		boolean archivoXML = interfazFormat.esArchivoTipoXML();
		while (iterator.hasNext()) {
			try {
				EstructuraArchivo estructuraArchivo = (EstructuraArchivo) iterator
						.next();
				if (archivoBinario) {
					interfazFormat.getExcelUtil().setColumna(columna);
					//if (log.isDebugEnabled()) log.debug("Incrementando columna del Excel:" + interfazFormat.getExcelUtil().getColumna());
				}
				String formattedField = formatterStrategy.formatTituloCabecera(getField(
						row, estructuraArchivo), interfazFormat,
						estructuraArchivo);
				columna++;
				if (!archivoXML) 
					result.append(formattedField);
				
			} catch (Exception e) {
				log.error("Error al formatear el campo: " + e.getMessage());
			}
		}

		// Ya esta la fila formateada, agrego el cambio de linea
        result.append(interfazFormat.getInterfaz().getSeparadorLinea());
		return result.toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterService#formatMap(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat)
	 */
	public String formatMap(Map row, InterfazFormat interfazFormat) {
		StringBuffer result = new StringBuffer();

		InterfazFormatterStrategy formatterStrategy = getInterfazFormatterStrategy(interfazFormat);
		Iterator iterator = interfazFormat.getEstructurasArchivo().iterator();
		//if (log.isDebugEnabled()) log.debug("Ingresando metodo formatMap");

		// Itero sobre todos los campos de la fila definidos en la estructura de
		// archivo
		short columna = 0;
		boolean archivoBinario = interfazFormat.isArchivoBinario();
		boolean archivoXML = interfazFormat.esArchivoTipoXML();
		if (archivoXML) {
			result.append(Constants.STRING_TABULADOR_XML);
			result.append(Constants.INI_TAG_XML);
			result.append(interfazFormat.getInterfaz().getNombreEtiquetaRegistroXML());
			result.append(interfazFormat.getInterfaz().getSeparadorLinea());
		}
		while (iterator.hasNext()) {
			try {
				EstructuraArchivo estructuraArchivo = (EstructuraArchivo) iterator
						.next();
				if (archivoBinario) {
					interfazFormat.getExcelUtil().setColumna(columna);
					//if (log.isDebugEnabled()) log.debug("Incrementando columna del Excel:" + interfazFormat.getExcelUtil().getColumna());
				}
				String formattedField = formatterStrategy.formatField(getField(
						row, estructuraArchivo), interfazFormat,
						estructuraArchivo);
				columna++;
				if (archivoXML) {
					result.append(Constants.STRING_TABULADOR_XML);
					result.append(Constants.STRING_TABULADOR_XML);
					result.append(Constants.STRING_TABULADOR_XML);
					result.append(estructuraArchivo.getIdentificadorCampo());
					result.append(Constants.STRING_IGUAL);
					result.append(Constants.STRING_COMILLA);
					result.append(formattedField.trim());
					result.append(Constants.STRING_COMILLA);
					result.append(interfazFormat.getInterfaz().getSeparadorLinea());
				}
				else
					result.append(formattedField);
				
			} catch (Exception e) {
				log.error("Error al formatear el campo: " + e.getMessage());
			}
		}
		if (archivoXML) {
			result.append(Constants.STRING_TABULADOR_XML);
			result.append("/");
			result.append(Constants.FIN_TAG_XML);
		}
		
		// Ya esta la fila formateada, agrego el cambio de linea
        result.append(interfazFormat.getInterfaz().getSeparadorLinea());
		return result.toString();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.formatter.InterfazFormatterService#parseLine(java.lang.String, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazFormat)
	 */
	public Map parseLine(String line, InterfazFormat interfazFormat) {
		InterfazFormatterStrategy formatterStrategy = getInterfazFormatterStrategy(interfazFormat);
		Map row = formatterStrategy.parseLine(line, interfazFormat);
		return row;
	}

	/**
	 * Obtiene el valor del campo del Map para la estructura de archivo.
	 * 
	 * @param row
	 *            Map con los campos a formatear
	 * @param estructuraArchivo
	 *            estructura del campo a obtener
	 * @return el valor del campo si se encontro, cadena vacia en caso no se
	 *         haya encontrado o tenga el valor de null
	 */
	private Object getField(Map row, EstructuraArchivo estructuraArchivo) {
		return row.get(estructuraArchivo.getIdentificadorCampo());
	}

	/**
	 * Obtiene la implementacion del InterfazFormatterStrategy segun el tipo de
	 * formato del archivo. Estas implementacion son inyectadas mediantes Spring
	 * en la configuracion de este Service.
	 * 
	 * @param interfazFormat
	 *            parametros del formateo de la interfaz
	 * @return InterfazFormatterStrategy para la interfaz
	 */
	private InterfazFormatterStrategy getInterfazFormatterStrategy(
			InterfazFormat interfazFormat) {
		InterfazFormatterStrategy formatterStrategy = (InterfazFormatterStrategy) formatterStrategies
				.get(interfazFormat.getNormaInterfaz()
						.getCodigoTipoFormatoArchivo());
		return formatterStrategy;
	}

	/**
	 * Metodo para inyectar las InterfazFormatterStrategy
	 * 
	 * @param formatterStrategies
	 *            Map de InterfazFormatterStrategy
	 */
	public void setFormatterStrategies(Map formatterStrategies) {
		this.formatterStrategies = formatterStrategies;
	}
}