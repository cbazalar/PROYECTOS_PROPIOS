package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author David Ramos
 *
 */

public interface MantenimientoAPEEmitirAlarmaDAO extends DAO{
	/**
	 * Actuliza los datos de una linea de armado
	 * @param criteria
	 */
	public void updateEmitirAlarma(Map criteria);

	/**
	 * Devuelve la lista de Productos 
	 * @param criteria
	 * @return
	 */
	public List getEmitirAlarmaList(Map criteria);
	
	/**
	 * Devuelve la lista de Productos 
	 * @param criteria
	 * @return
	 */
	public List getMailEmitirAlarmaList(Map criteria);
	
	/**
	 * Devuelve el oid del periodo por Marca y Canal
	 * @param criteria
	 * @return
	 */
	public String getOidPeriodobyMarcaCanal(Map criteria);
	
	/**
	 * Obtiene la lista de Mapa Zonas del Mapa Centro seleccionado
	 * @param criteria
	 * @return
	 */
	public List getMapaZonaComboList(Map criteria);
	
	/**
	 * Obtiene el codigo del Mapa Zona
	 * @param criteria
	 * @return
	 */
	public String getCodigoMapaZona(Map criteria);
	
	/**
 	 * Obtiene el Flag que confirma si existe informacion en la tabla periodo con los filtros de marca y canal
	 * @param criteria
	 * @return
	 */
	public int getExistePeriodobyMarcaCanal(Map criteria);
	
	/**
	 * Valida si existe la lista que esta activa para la facturacion
	 * @param criteria
	 * @return
	 */
	public String validaExisteFacturadosAlarma(Map criteria);
	
	/**
	 * Devuelve la descripcion del Centro de Distribucion
	 * @param criteria
	 * @return
	 */
	public String getDescCentroDistribucion(Map criteria);
	
	/**
	 * Devuelve la descripcion de la Linea de Armado  
	 * @param criteria
	 * @return
	 */
	public String getDescLineaArmado(Map criteria);
	
	/**
	 * Devuelve la descripcion del Mapa Centro Cabecera
	 * @param criteria
	 * @return
	 */
	public String getDescMapaCentroCabec(Map criteria);
}
