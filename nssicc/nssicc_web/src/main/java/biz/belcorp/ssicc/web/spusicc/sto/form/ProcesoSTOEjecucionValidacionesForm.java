package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoSTOEjecucionValidacionesForm extends BaseProcesoForm
		implements Serializable {

	private static final long serialVersionUID = -3778525435623175641L;
	private String tipoDocumento;
	private String[] codigoDocumentos;
	private String codigoPeriodo;
	private String fechaProceso;
	private Date fechaProcesoD;

	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}

	/**
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return Returns the documentos.
	 */
	/**
	 * @return Returns the codigoDocumentos.
	 */
	public String[] getCodigoDocumentos() {
		return codigoDocumentos;
	}

	/**
	 * @param codigoDocumentos
	 *            The codigoDocumentos to set.
	 */
	public void setCodigoDocumentos(String[] codigoDocumentos) {

		this.codigoDocumentos = codigoDocumentos;
		System.out.println("----------leo -------- " + this.codigoDocumentos);
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
}