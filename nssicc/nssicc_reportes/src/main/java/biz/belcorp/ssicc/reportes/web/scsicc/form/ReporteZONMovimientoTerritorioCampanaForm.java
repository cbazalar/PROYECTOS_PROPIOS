package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */

public class ReporteZONMovimientoTerritorioCampanaForm extends BaseReporteForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2388851694217481072L;
	private String codigoPais;
	private String codigoMarca;
	private String codigoCanal;
	private String codigoCampania;
	private String consultora;
	private String fechaActua;
	private Date fechaActuaD;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the codigoCampania
	 */
	public String getCodigoCampania() {
		return codigoCampania;
	}

	/**
	 * @param codigoCampania
	 *            the codigoCampania to set
	 */
	public void setCodigoCampania(String codigoCampania) {
		this.codigoCampania = codigoCampania;
	}

	/**
	 * @return the consultora
	 */
	public String getConsultora() {
		return consultora;
	}

	/**
	 * @param consultora
	 *            the consultora to set
	 */
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}

	/**
	 * @return the fechaActua
	 */
	public String getFechaActua() {
		return fechaActua;
	}

	/**
	 * @param fechaActua
	 *            the fechaActua to set
	 */
	public void setFechaActua(String fechaActua) {
		this.fechaActua = fechaActua;
	}

	/**
	 * @return the fechaActuaD
	 */
	public Date getFechaActuaD() {
		return fechaActuaD;
	}

	/**
	 * @param fechaActuaD
	 *            the fechaActuaD to set
	 */
	public void setFechaActuaD(Date fechaActuaD) {
		this.fechaActuaD = fechaActuaD;
	}

}
