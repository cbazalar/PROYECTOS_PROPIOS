package biz.belcorp.ssicc.service.sisicc.framework.formatter;

import java.util.Map;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;

/**
 * Service para formatear y parsear los datos en las Interfaces de Entrada y
 * Salida SiCC.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 * 
 */
public interface InterfazFormatterService {

	/**
	 * Formatea un Map utilizando el InterfazFormatter a String para el tema de las 
	 * cabecera de las interfaces. 
	 * @param row
	 * @param interfazFormat
	 * @return
	 */
	public String formatTituloCabecera(Map row, InterfazFormat interfazFormat);
	
	/**
	 * Formatea un Map utilizando el InterfazFormatter a String. Este metodo es
	 * utilizado para procesar las filas de la data de la Interfaces de Salida y
	 * generar el archivo de texto con el formato de la Interfaz.
	 * 
	 * @param row
	 *            Map a formatear
	 * @param interfazFormat
	 *            wrapper bean con los datos para el formateo
	 * @return String formateado
	 */
	public String formatMap(Map row, InterfazFormat interfazFormat);

	/**
	 * Parsea un String utilizando el InterfazFormatter a un Map. Este metodo es
	 * utilizado para procesar las lineas del archivo de texto de las Interfaces
	 * de Entrada.
	 * 
	 * @param line
	 *            String a parsear
	 * @param interfazFormat
	 *            wrapper bean con los datos para el parseo
	 * @return Map parseado
	 */
	public Map parseLine(String line, InterfazFormat interfazFormat);
}