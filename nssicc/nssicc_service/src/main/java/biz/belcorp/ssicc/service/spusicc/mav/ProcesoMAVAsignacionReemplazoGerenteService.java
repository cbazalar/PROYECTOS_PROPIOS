package biz.belcorp.ssicc.service.spusicc.mav;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoMAVAsignacionReemplazoGerenteService extends Service {
	
	/**
	 * Recupera la lista de gerente de reciones
	 * 
	 * @param params
	 * @return
	 */
	List getGerentesRegiones(Map params);

	/**
	 * Ejecura el proceso de registrar reemplazo de Gerentes a Regiones  activa
	 * 
	 * @param params
	 */
	void executeAsignacionGerenteRegion(Map params);

	/**
	 * Verifica si existe codigo de Cliente
	 * 
	 * @param params
	 */
	boolean existeCodigoCliente(Map params);
	
	/**
	 * Recupera la lista de gerente de zonas
	 * 
	 * @param params
	 * @return
	 */
	List getGerentesZonas(Map params);

	/**
	 * Ejecura el proceso de registrar reemplazo de Gerentes a Zonas  activa
	 * 
	 * @param params
	 */
	void executeAsignacionGerenteZona(Map params);	

	/**
	 * Recupera las remisiones de Material Promocional por fecha
	 * 
	 * @param params
	 * @return
	 */
	List getRemisionesMaterialPromocional(Map params);

	/**
	 * Recupera los abastecimientos de material por Actividad, fecha y producto
	 * 
	 * @param params
	 * @return
	 */
	List getAbastecimientoMaterial(Map params);
	
	/**
	 * Recupera las lista de actividades de MAV
	 * 
	 * @param params
	 * @return
	 */
	List getListaActividades(Map params);

	/**
	 * Recupera los despachos de Material Promocional por campaa, region/zona y/o Consultora
	 * 
	 * @param params
	 * @return
	 */
	List getHistoricoMaterialPromocional(Map params);

	/**
	 * Obtiene la lista de despachos de Armador General por producto
	 * 
	 * @param params
	 * @return
	 */
	List getCabeceraArmadoGeneral(Map params);

	/**
	 * Obtiene la lista de despachos de Armador General por producto, region y zona
	 * 
	 * @param params
	 * @return
	 */
	List getDetalleArmadoGeneral(Map params);

	/**
	 * Recupera la lista planilla de Entrega de Material
	 * 
	 * @param params
	 * @return
	 */
	List getPlanillaEntregaMaterial(Map params);

	/**
	 * Ejecura el proceso de registrar numero de cajas a una solicitud
	 * 
	 * @param params
	 */
	void executeAsignacionNumeroCajas(Map params);
	
}
