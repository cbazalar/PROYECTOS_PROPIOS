/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;




/**
 * @author peextcroman
 *
 */
public interface ConsultaRECCDRsDigitadosDAO extends DAO {

	/**
	 * Metodo que devuelve la lista de cabeceras digitados
	 * @param params
	 * @return
	 */
	public List getListaCabeceras(Map params);

	/**
	 *Metodo que devuelve la lista de detalles digitados
	 * @param params
	 * @return
	 */
	public List getListaDetalles(Map params);
	
	/**
	 * Metodo que elimina detalles digitados
	 * @param params
	 */
	public void deleteDetallesDigitados(Map params);
	
	/**
	 * Metodo que elimina cabeceras digitados
	 * @param params
	 */
	public void deleteCabeceraDigitada(Map params);

	/**Metodo que trae las secuencias de Zona Diarias
	 * @return
	 */
	public List getSecuenciaZonaDiaria();
	
	/**
	 * Metodo que devuelve la lista de cabeceras digitados historicos
	 * @param params
	 * @return
	 */
	public List getListaCabecerasHistorico(Map params);
	
	/**
	 * Metodo que devuelve la lista de detalles digitados historicos
	 * @param params
	 * @return
	 */
	public List getListaDetallesHistoricos(Map params);
	
	/**
	 * Metodo que elimina detalles digitados historicos
	 * @param params
	 */
	public void deleteDetallesDigitadosHistoricos(Map params);
	
	/**
	 * Metodo que elimina cabeceras digitados historico
	 * @param params
	 */
	public void deleteCabeceraDigitadaHistoricos(Map params);
	
}
