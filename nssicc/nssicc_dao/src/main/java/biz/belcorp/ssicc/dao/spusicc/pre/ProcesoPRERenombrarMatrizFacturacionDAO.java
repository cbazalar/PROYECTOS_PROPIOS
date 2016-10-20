package biz.belcorp.ssicc.dao.spusicc.pre;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public interface ProcesoPRERenombrarMatrizFacturacionDAO extends DAO {

	/**
	 * Devuelve el Oid Cabecera de la Campana Destino de la Matriz de Facturación
	 * 
	 * @param codigoPeriodo
	 * @return
	 */
	String getOidCabeceraCampanaDestino(String codigoPeriodo);
	
	/**
	 * Verifica si existen oferta creadas para la Matriz de Facturación de Destino
	 * 
	 * @param oidCabecera
	 * @return
	 */
	int getExisteOfertaEnMatrizFacturacionDestino(String oidCabecera);
	
	/**
	 * Ejecura el proceso de Renombrar Matriz de Facturación
	 *  
	 * @param params
	 */
	void executeRenombrarMatrizFacturacion(Map params);

}
