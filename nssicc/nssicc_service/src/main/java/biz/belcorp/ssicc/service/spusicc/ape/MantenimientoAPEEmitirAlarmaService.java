package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEEmitirAlarmaService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">David Ramos</a>
 */
public interface MantenimientoAPEEmitirAlarmaService extends Service{

	/**
	 * Actualiza el indicador de Activacion para la Facturacion
	 * @param criteria
	 */
	public void updateEmitirAlarma(Map criteria);
	
	/**
	 * Devuelve la lista de productos Emitir Alarma
	 * @param criteria
	 * @return
	 */
	public List getEmitirAlarmaList(Map criteria);
	
	/**
	 * Devuelve la lista de productos Emitir Alarma
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
	 * Obtiene la lista de Mapa Zonas en estado Activo
	 * @param criteria
	 * @return
	 */
	public List getMapaZonaVersionList(Map criteria);
	
	
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
	 * Devuelve el codigo de Linea de Armado por el Oid
	 * @param criteria
	 * @return
	 */
	public int getExistePeriodobyMarcaCanal(Map criteria);
}