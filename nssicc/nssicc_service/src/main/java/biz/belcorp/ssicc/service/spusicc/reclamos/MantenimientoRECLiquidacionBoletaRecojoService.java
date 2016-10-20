package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextdoliva
 *
 */
public interface MantenimientoRECLiquidacionBoletaRecojoService extends Service {
	public List getEstados();
	public List getCabecerasBoletasReclamo(Map criteria);
	public List getDetallesBoletasReclamo(Map criteria);
	public String getTotalBR(Map criteria);	
	public String getPendientesBR(Map criteria);
	public String getAprobadasBR(Map criteria);
	public String getRechazadasBR(Map criteria);
	public String getTotalCargosBR(Map criteria);
	public String getTotalAbonosBR(Map criteria);
	public void aprobarReclamoDigitados(Map params, String[] ids);
	public void updateRechazarReclamoDigitados(Map params);
	public String getNumLoteSTO(Map criteria);
	/**
	 * Realiza el recalculo de la Boleta Recojo seleccionadas
	 * @param criteria
	 */
	public void updateRecalcularBoletaRecojo(Map criteria, String[] ids);
}
