package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Nicols Lpez
 *
 */
public interface MantenimientoAPEUnidadesAdministrativasDAO extends DAO {
	
	/**
	 * Recupera la lista de Centros de Distribucin
	 * 
	 * @param criteria
	 * @return
	 */
	
	public List getCodigoCentroDistribucionList(Map criteria);
	
	/**
	 * @param criteria
	 * @return OidCentroCDPais
	 */
	public String getOidCentroDistribucionPais(Map criteria);
	
	/**
	 * @param criteria
	 * @return El Codigo CD por Defecto
	 */
	public String getCodigoCDDefecto(Map criteria);
	
	
	/**
	 * @param criteria
	 * @return La lista de Armado
	 */
	public List getLineaArmadoListar(Map criteria);
	
	/**
	 * @param criteria
	 * @return Codigo de Linea Armado por defecto
	 */
	public String getCodLineaArmadaDefecto(Map criteria);
	
	/**
	 * @param criteria
	 * Realiza la insercion de la Unidad Administrativa por Lnea
	 */
	public void insertUnidadAdministrativaLinea(Map criteria);
	
	/**
	 * @param criteria
	 * @return el OidLineaArmado
	 */
	public String getOidLineaArmado(Map criteria);
	
	/**
	 * @param criteria
	 * @return Valida si Existe registro en Unidad Administrativa por Linea
	 */
	public String getValidaExisteUadmLineaAPE(Map criteria);
	
	/**
	 * @param criteria
	 * @return Listado de Unidades Administrativas por Linea
	 */
	public List getUnidadAdministrativaLineaList(Map criteria);
	
	/**
	 * @param criteria
	 * Elimina el registro en la tabla Unidad Administrativa por Linea
	 */
	public void deleteUnidadAdministrativa(Map criteria);
	
	/**
	 * @param criteria
	 * @return el oIdNivelAgrupacionOlas
	 */
	public String getOidNivelAgrupacionOlas(Map criteria);
	
	/**
	 * @param criteria
	 * @return Lista de Nivel Olas por OidCentroDistribucin
	 */
	public List getObtenerNivelOlas(Map criteria);
	
}
