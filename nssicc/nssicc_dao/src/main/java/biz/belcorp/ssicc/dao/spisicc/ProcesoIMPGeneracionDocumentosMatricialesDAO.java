/*
 * Created on 15/05/2009 11:25:04 AM
 * biz.belcorp.ssicc.dao.ProcesoIMPGeneracionDocumentosMatricialesDAO
 */
package biz.belcorp.ssicc.dao.spisicc;

import java.util.Map;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoIMPGeneracionDocumentosMatricialesDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface ProcesoIMPGeneracionDocumentosMatricialesDAO {

	/**
	 * Carga la informacion de los cupones para luego generar el archivo matricial.
	 * @param params
	 */
	public void executeProcesaCuponesMatriciales(Map params);
	
	/**
	 * Genera el archivo de cupones matriciales en base a la informacion cargada
	 * previamente.
	 * @param params
	 */
	public void executeGeneraCuponesMatriciales(Map params);
	
	/**
	 * Obtiene la cantidad de cupones generados para retornarla al framework
	 * de interfaces
	 * @param params
	 * @return Cantidad de cupones matriciales generados
	 */
	public int getCantidadCuponesMatriciales(Map params);
	
	/**
	 * Carga la informacion de las facturas para luego generar el archivo matricial.
	 * @param params
	 */
	public void executeProcesaFacturasMatriciales(Map params);
	
	/**
	 * Genera el archivo de facturas matriciales en base a la informacion cargada
	 * previamente.
	 * @param params
	 */
	public void executeGeneraFacturasMatriciales(Map params);
	
	/**
	 * Obtiene la cantidad de documentos generadss para retornarla al framework
	 * de interfaces
	 * @param params
	 * @return Cantidad de documentos matriciales generados
	 */
	public int getCantidadDocumentosMatriciales(Map params);

	/**
	 * Carga la informacion de las notas de credito para luego generar el archivo matricial.
	 * @param params
	 */
	public void executeProcesaNotasCreditoMatriciales(Map params);
	
	/**
	 * Genera el archivo de notas de credito matriciales en base a la informacion cargada
	 * previamente.
	 * @param params
	 */
	public void executeGeneraNotasCreditoMatriciales(Map params);
	
	/**
	 * Carga la informacion de las notas de debito para luego generar el archivo matricial.
	 * @param params
	 */
	public void executeProcesaNotasDebitoMatriciales(Map params);
	
	/**
	 * Genera el archivo de notas de debito matriciales en base a la informacion cargada
	 * previamente.
	 * @param params
	 */
	public void executeGeneraNotasDebitoMatriciales(Map params);
	
	/**
	 * Actualiza el correlativo usado para determinar el oid a partir del cual se va a 
	 * generar determinado tipo de documento matricial.
	 * previamente.
	 * @param params
	 */
	public void executeActualizaCorrelativoDocumentosMatriciales(Map params);

	/**
	 * Carga la informacion de las facturas de premios para luego generar el
	 * archivo matricial.
	 * 
	 * @param params
	 */
	public void executeProcesaFacturasPremioMatriciales(Map params);

	/**
	 * Genera el archivo de facturas de premios matriciales en base a la
	 * informacion cargada previamente.
	 * 
	 * @param params
	 */
	public void executeGeneraFacturasPremioMatriciales(Map params);
	
	/**
	 * Carga la informacion de las guias de remision para luego generar el
	 * archivo matricial.
	 * 
	 * @param params
	 */
	public void executeProcesaGuiasRemisionMatriciales(Map params);

	/**
	 * Genera el archivo de guias de remision matriciales en base a la
	 * informacion cargada previamente.
	 * 
	 * @param params
	 */
	public void executeGeneraGuiasRemisionMatriciales(Map params);
	
}
