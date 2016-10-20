package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface MantenimientoPEDUnidadesAdministrativasChequeoDAO extends DAO{
	
	/**
	 * retorna las unidades administrativas a chequear de acuerdo al filtro de campaa,tipo de ingreso,region,zona,seccion,territorio
	 * @param map
	 * @return
	 */
	public List getUnidadesAdministrativasChequeoList(Map map);
	
	/**
	 * inserta una unidad admimistrativa a chequear de acuerdo a las opciones ingresadas en el filtro,
	 * que en este caso sera obligatorio seleccionar una opcion de cada una de los filtros
	 * @param map
	 */
	public void insertUnidadesAdministrativasChequeo(Map map);
	
	/**
	 * borra una unidad administrativa de chequeo por codigoPais,codigoTipoChequeo,codigoPeriodo,codigoRegion,codigoZona,codigoSeccion,codigoTerritorio
	 * @param map
	 */
	public void deleteUnidadesAdministrativasChequeo(Map map);

}