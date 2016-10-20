package biz.belcorp.ssicc.service.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface ProcesoIMPGeneracionDocumentosLaserService extends Service {

	/**
	 * Genera los documentos laser
	 * @param params
	 */
	public void executeGeneracionDocumentosLaser(Map params);

	/**
	 * genera los documentos laser a color
	 * @param params
	 */
	public void executeGeneracionDocumentosLaserColor(Map params);
	
	/**
	 * Valida Formato del archivo Excel
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validarFormatoArchivoExcel(Map criteria) throws Exception;
	
	/**
	 * Validamos la informacion para la Carga de Seccion Ventas
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public boolean executeValidarSeccionVentas(Map criteria) throws Exception;
	
	/**
	 * Cargamos la informacion para la Carga de Seccion Ventas
	 * 
	 * @param criteria
	 * @throws Exception
	 */
	public void executeCargaSeccionVentas(Map criteria) throws Exception;

	/**
	 * Validamos la informacion para la Carga de Seccion Compartamos
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public boolean executeValidarSeccionCompartamos(Map criteria) throws Exception;
	
	/**
	 * Cargamos la informacion para la Carga de Seccion Ventas
	 * 
	 * @param criteria
	 * @throws Exception
	 */
	public void executeCargaSeccionCompartamos(Map criteria) throws Exception;

	/**
	 * Validamos la informacion para el registro de los datos de Plan Piloto
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean executeValidarPlanPiloto(Map criteria);
	
	/**
	 * Registramos la informacion en la entidad Plan Piloto
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void executeInsertPlanPiloto(List list) throws Exception;

	/**
	 * Validamos la informacion para la Carga de Seccion Focalizados
	 * 
	 * @param criteria
	 * @return
	 * @throws Exception
	 */
	public boolean executeValidarSeccionFocalizados(Map criteria) throws Exception;
	
	/**
	 * Cargamos la informacion para la Carga de Seccion Focalizados
	 * 
	 * @param criteria
	 * @throws Exception
	 */
	public void executeCargaSeccionFocalizados(Map criteria) throws Exception;

}
