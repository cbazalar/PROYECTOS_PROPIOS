package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
public class ReporteRECIndFactVentasProductoForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String regionList;
	private String zonaList;

	/**
	 * @return Returns the regionList.
	 */
	public String getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 */
	public void setZonaList(String zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return Returns the codigoPeriodoFin.
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin
	 *            The codigoPeriodoFin to set.
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return Returns the codigoPeriodoInicio.
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio
	 *            The codigoPeriodoInicio to set.
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
}