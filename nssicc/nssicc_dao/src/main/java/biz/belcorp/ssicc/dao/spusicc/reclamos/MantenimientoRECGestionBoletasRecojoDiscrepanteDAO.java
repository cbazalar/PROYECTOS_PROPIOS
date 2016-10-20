/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;



/**
 * @author peextjcairampoma
 *
 */
public interface MantenimientoRECGestionBoletasRecojoDiscrepanteDAO extends DAO {

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
	 * @param params
	 */
	public void executeProcesoBoletaRecojoDiscrepante(Map params);



	/**
	 * @param params
	 * @return
	 */
	public List getCodigoVentaListByCriteria(Map params);



}
