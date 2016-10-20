package biz.belcorp.ssicc.service.spusicc.pre;

import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
public interface ProcesoPRERenombrarMatrizFacturacionService extends Service {

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