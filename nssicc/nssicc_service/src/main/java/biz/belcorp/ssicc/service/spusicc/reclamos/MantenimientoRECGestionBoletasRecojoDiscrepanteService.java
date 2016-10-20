package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoRECGestionBoletasRecojoDiscrepanteService extends Service {

	/**
	 * @param params
	 * @return
	 */
	public List getBoletasRecojoDiscrepantesList(Map params);
	

	/**
	 * @param boletaRecojoCabecera
	 * @param detalList
	 * @param detalDiscList
	 */
	public void updateBoletaRecojoDiscrepanteCabeceraDetalle(BoletaRecojoCabecera boletaRecojoCabecera, List detalList,	Map detalDiscList);

	
	/**
	 * @param criteria
	 */
	public void executeProcesoBoletaRecojoDiscrepante(Map params);


	/**
	 * @param params
	 * @return
	 */
	public List getCodigoVentaListByCriteria(Map params);

}
