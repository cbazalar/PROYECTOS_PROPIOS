package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author cdavila
 * 
 */
public interface MantenimientoPEDMontoMaximoDAO extends DAO {

	/**
	 * obtiene el OidRegionByCodZona
	 * 
	 * @param criteria
	 * @return
	 */
	public String getOidRegionByCodZona(Map criteria);

	/**
	 * obtiene el oidPais
	 * 
	 * @return
	 */
	public String getOidPais(Map criteria);

	/**
	 * obtiene oidZona
	 * 
	 * @param criteria
	 * @return
	 */
	public String getOidZonaByCodZona(Map criteria);

	/**
	 * obtiene oidRegion
	 * 
	 * @param criteria
	 * @return
	 */
	public String getOidRegionByCodRegion(Map criteria);

	/**
	 * Obtiene los Niveles de Riesgo
	 * 
	 * @return
	 */
	public List getNivelRiesgo();

	/**
	 * obtiene CountExisteMontoMaximo
	 * 
	 * @return
	 */
	public String getCountExisteMontoMaximo(Map criteria);

	/**
	 * insert insertCarParamCarte
	 * 
	 * @param criteria
	 */
	public void insertCarParamCarte(Map criteria);

	/**
	 * insert insertCarAsignCodigConfi
	 * 
	 * @param criteria
	 */
	public void insertCarAsignCodigConfi(Map criteria);

	/**
	 * insert insertCarParamCarteAudit
	 * 
	 * @param criteria
	 */
	public void insertCarParamCarteAudit(Map criteria);

	/**
	 * insert insertCarAsignCodigConfiAudit
	 * 
	 * @param criteria
	 */
	public void insertCarAsignCodigConfiAudit(Map criteria);

	/**
	 * @return getIdSgteCarParamCarte
	 */
	public Long getIdSgteCarParamCarte();

	/**
	 * @return getIdSgteCarAsignCodigConfi
	 */
	public Long getIdSgteCarAsignCodigConfi();

	/**
	 * @return getIdSgteCarParamCarteAudit
	 */
	public Long getIdSgteCarParamCarteAudit();

	/**
	 * @return getIdSgteCarAsignCodigConfiAudit
	 */
	public Long getIdSgteCarAsignCodigConfiAudit();

	/**
	 * retorna busqueda Monto Maximo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getMontoMaximoList(Map criteria);

	/**
	 * @param criteria
	 */
	public void deleteCarParamCarte(Map criteria);

	/**
	 * @param criteria
	 */
	public void deleteCarAsignCodigConfi(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCarParamCarte(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCarAsignCodigConfi(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateIndMontMaxi(Map criteria);
	
	/**
	 * @param criteria
	 */
	public void updateRegMontMaxi(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCodigoRegionUA(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getCodigoZonaUA(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getCodigoSeccionUA(Map criteria);

}
