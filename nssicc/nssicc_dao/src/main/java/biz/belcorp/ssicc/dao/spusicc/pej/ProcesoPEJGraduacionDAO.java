package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoPEJGraduacionDAO extends DAO{

	/**
	 * Retorna la lista de etapas de Ejecutivas
	 * @return
	 */
	public List getEtapas();

	/**
	 * Verifica si la etapa seleccionada y el ao de inicio ya fueron procesados
	 * @param codigoPais
	 * @param anioInicial
	 * @param nroEtapa
	 * @return
	 */
	public Integer validaEtapaProcesada(String codigoPais,String anioInicial,String nroEtapa);

	/**
	 * Ejecuta el proceso de Graduacion
	 * @param params
	 */
	public void executeProcesoPEJGraduacion(Map params);

	
	/**
	 * Ejecuta Proceso PEJ de Calcular Avance Gestion
	 * @param params
	 */
	public void executeProcesoPEJCalcularAvanceGestion(Map params);

}