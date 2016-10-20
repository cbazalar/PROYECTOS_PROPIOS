package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoPERResumenDiarioPercepcionesSunatForm extends BaseProcesoForm implements Serializable {

	private static final long serialVersionUID = 8630759186633137654L;
	
	private String codigoPais;
	private String fechaGeneracion;
	private Date fechaGeneracionDate;
	private String tipoReporte;
	
	
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
	 * @return the fechaGeneracion
	 */
	public String getFechaGeneracion() {
		return fechaGeneracion;
	}
	/**
	 * @param fechaGeneracion the fechaGeneracion to set
	 */
	public void setFechaGeneracion(String fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
	/**
	 * @return the fechaGeneracionDate
	 */
	public Date getFechaGeneracionDate() {
		return fechaGeneracionDate;
	}
	/**
	 * @param fechaGeneracionDate the fechaGeneracionDate to set
	 */
	public void setFechaGeneracionDate(Date fechaGeneracionDate) {
		this.fechaGeneracionDate = fechaGeneracionDate;
	}
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}
	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	
	
	
}
