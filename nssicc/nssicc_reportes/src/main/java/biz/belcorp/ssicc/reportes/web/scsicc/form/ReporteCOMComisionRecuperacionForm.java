package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOMComisionRecuperacionForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = -1874654542248811371L;

	private String codigoPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;
	
	private String codigoComision;
	
	private String codigoComisionIngreso;
	
	private String descripcionComision;

	private String codigoPeriodo;
	
	private String presentacion;
	
	private String tipoComision;
	
	private String tipoPresentacion;

	private String oidComision;
	
	private String indicadorFeriado;
	
	private String indicadorDsctoImpuesto;
	
	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * @return Returns the descripcionCanal.
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal
	 *            The descripcionCanal to set.
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return Returns the descripcionMarca.
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca
	 *            The descripcionMarca to set.
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/**
	 * @return Returns the codigoComision.
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision The codigoComision to set.
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}

	/**
	 * @return Returns the descripcionComision.
	 */
	public String getDescripcionComision() {
		return descripcionComision;
	}

	/**
	 * @param descripcionComision The descripcionComision to set.
	 */
	public void setDescripcionComision(String descripcionComision) {
		this.descripcionComision = descripcionComision;
	}

	/**
	 * @return Returns the presentacion.
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * @param presentacion The presentacion to set.
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * @return Returns the tipoComision.
	 */
	public String getTipoComision() {
		return tipoComision;
	}

	/**
	 * @param tipoComision The tipoComision to set.
	 */
	public void setTipoComision(String tipoComision) {
		this.tipoComision = tipoComision;
	}

	/**
	 * @return Returns the codigoComisionIngreso.
	 */
	public String getCodigoComisionIngreso() {
		return codigoComisionIngreso;
	}

	/**
	 * @param codigoComisionIngreso The codigoComisionIngreso to set.
	 */
	public void setCodigoComisionIngreso(String codigoComisionIngreso) {
		this.codigoComisionIngreso = codigoComisionIngreso;
	}
	
	/**
	 * @return the tipoPresentacion
	 */
	public String getTipoPresentacion() {
		return tipoPresentacion;
	}

	/**
	 * @param tipoPresentacion the tipoPresentacion to set
	 */
	public void setTipoPresentacion(String tipoPresentacion) {
		this.tipoPresentacion = tipoPresentacion;
	}
	
	/**
	 * @return the oidComision
	 */
	public String getOidComision() {
		return oidComision;
	}

	/**
	 * @param oidComision the oidComision to set
	 */
	public void setOidComision(String oidComision) {
		this.oidComision = oidComision;
	}

	/**
	 * @return the indicadorFeriado
	 */
	public String getIndicadorFeriado() {
		return indicadorFeriado;
	}

	/**
	 * @param indicadorFeriado the indicadorFeriado to set
	 */
	public void setIndicadorFeriado(String indicadorFeriado) {
		this.indicadorFeriado = indicadorFeriado;
	}

	/**
	 * @return the indicadorDsctoImpuesto
	 */
	public String getIndicadorDsctoImpuesto() {
		return indicadorDsctoImpuesto;
	}

	/**
	 * @param indicadorDsctoImpuesto the indicadorDsctoImpuesto to set
	 */
	public void setIndicadorDsctoImpuesto(String indicadorDsctoImpuesto) {
		this.indicadorDsctoImpuesto = indicadorDsctoImpuesto;
	}
	
}
