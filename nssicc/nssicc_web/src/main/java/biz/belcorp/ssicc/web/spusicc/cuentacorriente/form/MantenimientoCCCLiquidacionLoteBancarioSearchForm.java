package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCLiquidacionLoteBancarioSearchForm extends BaseSearchForm  implements Serializable{
			 
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;		
	private String codigoCuentaCorriente;
	private String estadoLote;
	private String fechaProceso;
	private Date fechaProcesoD;
	private String numeroLote;
			
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
	 * @return Returns the codigoCuentaCorriente.
	 */
	public String getCodigoCuentaCorriente() {
		return codigoCuentaCorriente;
	}

	/**
	 * @param codigoCuentaCorriente
	 *            The codigoCuentaCorriente to set.
	 */
	public void setCodigoCuentaCorriente(String codigoCuentaCorriente) {
		this.codigoCuentaCorriente = codigoCuentaCorriente;
	}

	/**
	 * @return the estadoLote
	 */
	public String getEstadoLote() {
		return estadoLote;
	}

	/**
	 * @param estadoLote the estadoLote to set
	 */
	public void setEstadoLote(String estadoLote) {
		this.estadoLote = estadoLote;
	}

	/**
	 * @return the fechaProceso
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso the fechaProceso to set
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the fechaProcesoD
	 */
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}

	/**
	 * @param fechaProcesoD the fechaProcesoD to set
	 */
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}
}