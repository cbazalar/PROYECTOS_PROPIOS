/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 *
 */
public interface MantenimientoRECLiquidacionBoletaRecojoDAO extends DAO {
	public List getEstados();
	public List getCabecerasBoletasReclamo(Map criteria);
	public List getDetallesBoletasReclamo(Map criteria);
	public String getTotalBR(Map criteria);	
	public String getPendientesBR(Map criteria);
	public String getAprobadasBR(Map criteria);
	public String getRechazadasBR(Map criteria);
	public String getTotalCargosBR(Map criteria);
	public String getTotalAbonosBR(Map criteria);
	public void aprobarReclamoDigitados(Map params);
	public void updateRechazarReclamoDigitados(Map param);
	/**
	 * Realiza el recalculo de la Boleta Recojo seleccionada
	 * @param criteria
	 */
	public void updateRecalcularBoletaRecojo(Map criteria);
	
}
