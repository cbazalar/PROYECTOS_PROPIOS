package biz.belcorp.ssicc.service.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;


/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoAPEMantenerMapaCDService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 */
public interface MantenimientoAPEMantenerMapaCDService extends Service {

	/**
	 * @param criteria
	 * @return Lista de Anaqueles por Mapa CD
	 */
	public List getAnaquelDestinoListar(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Anaqueles Origen por Mapa CD 
	 */
	public List getAnaquelOrigenListar(Map criteria);
	
	/**
	 * @param criteria
	 * @return Obtenemos el Oid Sub Linea Armado por Oid Mapa CD Detalle
	 */
	public String getObtenerOidSubLineaxOidMapaCDDet(Map criteria);

	/**
	 * @param criteria
	 * @return Valida si no existe Asignacion de Producto a Anaquel Detalle
	 */
	public String getValidaNoExisteAsignaProductoAAnaquelDet(Map criteria);
	
	/**
	 * @param criteria
	 * @return el Oid Marca
	 */
	public String getOidMarcaxCod(Map criteria);
	
	/**
	 * @param criteria
	 * @return el Oid Canal
	 */
	public String getOidCanalxCod(Map criteria);
	
	/**
	 * @param criteria
	 * @return el Oid Periodo
	 */
	public String getOidPeriodo(Map criteria);
	
	/**
	 * Permite actualizar el Codigo Anaquel Destino con la capacidad
	 * @param criteria
	 */
	public void updateCapacidadMapCDDetalle(Map criteria);
	
	/**
	 * Permite actualizar los anaqueles origenes con la llave foranea del anaquel destino y con el indicador de expansin.
	 * @param criteria
	 */
	public void updateAnaquelOrigenMapaCDDetalle(Map criteria);	
	
	/**
	 * @param criteria
	 * @return La Lista de Codigos de Anaquel Origen 
	 */
	public List getCodAnaquelOrigenListar(Map criteria);
	
	/**
	 * @param criteria
	 * Permite actualizar el Cdigo de Anaquel Origen a expandido False 
	 */
	public void updateAnaquelOrigenOpcionDividir(Map criteria);
	
	/**
	 * @param criteria
	 * @return eL Numero de Anaquel
	 */
	public String getNumeroAnaquel(Map criteria);

	/**
	 * @param criteria
	 * @return La lista de Cdigos de Anaquel asociados con el codigo de Anaquel Destino 
	 */
	public List getCodigoAnaquelDividirList(Map criteria);

}
