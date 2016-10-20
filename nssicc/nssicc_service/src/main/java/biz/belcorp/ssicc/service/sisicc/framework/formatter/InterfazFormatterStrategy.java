package biz.belcorp.ssicc.service.sisicc.framework.formatter;

import java.util.Map;

import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;

/**
 * Strategy para formatear un campo.
 * 
 * @author peextlshimokawa
 * 
 */
public interface InterfazFormatterStrategy {
	
	/**
	 * Formatea el campo titulo de cabecera usando InterfazFormatter y la EstructuraArchivo
	 * @param field
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return
	 */
	public String formatTituloCabecera(Object field, InterfazFormat interfazFormat,
			EstructuraArchivo estructuraArchivo);
	
	/**
	 * Formatea el campo dado el InterfazFormatter y la EstructuraArchivo
	 * 
	 * @param field
	 *            campo a formatear
	 * @param interfazFormat
	 * @param estructuraArchivo
	 * @return campo formateado
	 */
	public String formatField(Object field, InterfazFormat interfazFormat,
			EstructuraArchivo estructuraArchivo);

	/**
	 * @param line
	 * @param interfazFormat
	 * @return
	 */
	public Map parseLine(String line, InterfazFormat interfazFormat);
	
	
	/**
	 * Genera informacion para ser grabada en caso de Interfaces que generen 
	 * Archivos Binarios (Ej.Excel)
	 * @param interfazFormat
	 * @param tipoDato
	 * @param result
	 */
	public void generarDatoArchivoBinario(InterfazFormat interfazFormat, String tipoDato, String result) throws Exception;
	
	
	/**
	 * Genera informacion para ser grabada en caso de Interfaces que generen 
	 * Archivos Binarios (Ej.Excel)
	 * @param interfazFormat
	 * @param result
	 * @throws Exception
	 */
	public void generarDatoArchivoBinario(InterfazFormat interfazFormat, String result) throws Exception;
	
}
