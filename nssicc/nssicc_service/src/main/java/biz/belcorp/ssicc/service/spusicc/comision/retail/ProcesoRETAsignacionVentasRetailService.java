package biz.belcorp.ssicc.service.spusicc.comision.retail;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoRETAsignacionVentasRetailService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface ProcesoRETAsignacionVentasRetailService extends Service{

	/**
	 * Asignacion de las ventas retail a las Gerentes de Zona
	 * 
	 * @param criteria
	 */
	public void executeAsignacionVentasRetail(Map criteria);

	/**
	 * Inserta los registros de venta retail desde web service
	 * @param retailCabecera
	 * @param retailDetalle
	 */
	public void saveVentaRetail (List retailCabecera,List retailDetalle,String tipoProceso)throws Exception;

	/**
	 * Inserta los consolidados de venta retail desde web service
	 * @param retailConsolidado
	 */
	public void saveConsolidadoRetail(List retailConsolidado)throws Exception;
	
	/* INI JJ PER-SiCC-2012-0348 */
	/**
	 * elimina las ventas Retail cabecera
	 * @param map
	 */
	public void deleteVentaRetailCabecera(Map map);

	/**
	 * Elimina los detalles Retail
	 * @param map
	 */
	public void deleteVentaRetailDetalle(Map map);
	
	/**
	 * Lista los registros de la venta Retail cabecera 
	 * @param map
	 * @return 
	 */
	public List listaRetailCabecera(Map map);
	/**
	 * Lista los registros de la venta Retail detalle 
	 * @param map
	 * @return 
	 */
	public List listaRetailDetalle(Map map);
	/**
	 * Lista los  Campaa y Fecha de Proceso
	 * @param map
	 * @return 
	 */
	
	public List listaCampanaFechaProceso();
	
	public List listaPais(Map map);
	/* FIN JJ PER-SiCC-2012-0348 */
}