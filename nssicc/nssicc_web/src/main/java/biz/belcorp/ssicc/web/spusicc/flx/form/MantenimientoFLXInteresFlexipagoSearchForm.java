package biz.belcorp.ssicc.web.spusicc.flx.form;

import java.io.Serializable;
import java.math.BigDecimal;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFLXInteresFlexipagoSearchForm extends BaseSearchForm implements Serializable {

	private static final long serialVersionUID = -1756559891157625625L;
	
	private String codigoPais;
	private String periodoInicio;
	private String periodoFin;
	private BigDecimal rangoImpoDesde;
	private BigDecimal rangoImpoHasta;
	private BigDecimal valorCosto;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the periodoInicio
	 */
	public String getPeriodoInicio() {
		return periodoInicio;
	}

	/**
	 * @param periodoInicio the periodoInicio to set
	 */
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	/**
	 * @return the periodoFin
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}

	/**
	 * @param periodoFin the periodoFin to set
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}

	/**
	 * @return the rangoImpoDesde
	 */
	public BigDecimal getRangoImpoDesde() {
		return rangoImpoDesde;
	}

	/**
	 * @param rangoImpoDesde the rangoImpoDesde to set
	 */
	public void setRangoImpoDesde(BigDecimal rangoImpoDesde) {
		this.rangoImpoDesde = rangoImpoDesde;
	}

	/**
	 * @return the rangoImpoHasta
	 */
	public BigDecimal getRangoImpoHasta() {
		return rangoImpoHasta;
	}

	/**
	 * @param rangoImpoHasta the rangoImpoHasta to set
	 */
	public void setRangoImpoHasta(BigDecimal rangoImpoHasta) {
		this.rangoImpoHasta = rangoImpoHasta;
	}

	/**
	 * @return the valorCosto
	 */
	public BigDecimal getValorCosto() {
		return valorCosto;
	}

	/**
	 * @param valorCosto the valorCosto to set
	 */
	public void setValorCosto(BigDecimal valorCosto) {
		this.valorCosto = valorCosto;
	}
	
	

}
	