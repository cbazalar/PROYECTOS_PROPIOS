package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoPEDMontoMaximoService extends Service {
	/**
	 * Obtiene los Niveles de Riesgo
	 * 
	 * @return
	 */
	public List getNivelRiesgo();

	/**
	 * obtiene CountExisteMontoMaximo;
	 * 
	 * @return
	 */
	public String getCountExisteMontoMaximo(Map criteria);

	/**
	 * @param criteria
	 */
	public Map insertCarParamCarte(Map criteria);

	/**
	 * @param criteria
	 */
	public void insertCarAsignCodigConfi(Map criteria);

	/**
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
	public List getCarParamCarte(Map criteria);
	
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
