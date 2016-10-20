package biz.belcorp.ssicc.web.scsicc.form;
import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteAPEUnidadesPicadasDiaForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 2973750128011082657L;
	private String codigoPais;
	private String codigoCentro;
	private String codigoLinea;
	private String fechaFacturacion;
	private Date fechaFacturacionDt;
	
	public Date getFechaFacturacionDt() {
		return fechaFacturacionDt;
	}

	public void setFechaFacturacionDt(Date fechaFacturacionDt) {
		this.fechaFacturacionDt = fechaFacturacionDt;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return this.fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return
	 */
	public String getCodigoCentro() {
		return codigoCentro;
	}

	/**
	 * @param codigoCentro
	 */
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	/**
	 * @return
	 */
	public String getCodigoLinea() {
		return codigoLinea;
	}

	/**
	 * @param codigoLinea
	 */
	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}
}