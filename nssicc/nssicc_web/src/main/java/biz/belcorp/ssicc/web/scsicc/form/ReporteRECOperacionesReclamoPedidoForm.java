package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
public class ReporteRECOperacionesReclamoPedidoForm extends BaseReporteForm
		implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;

	private String tipoPeriodo;

	private String[] codigoOperacion;

	private String descripcionOperacion;

	private String tipoReporte;

	private String tipoMovimiento;

	private String[] tipoOperacionList;

	private String fechaFacDesde;

	private String fechaFacHasta;

	private Date fechaFacDesdeD;

	private Date fechaFacHastaD;

	/**
	 * @return Returns the codigoOperacion.
	 */
	public String[] getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion
	 *            The codigoOperacion to set.
	 */
	public void setCodigoOperacion(String[] codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * 
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
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * @param codigoPeriodoFinal
	 *            The codigoPeriodoFinal to set.
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return Returns the codigoPeriodoInicial.
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial
	 *            The codigoPeriodoInicial to set.
	 * 
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}

	/**
	 * @return Returns the tipoPeriodo.
	 */
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}

	/**
	 * @param tipoPeriodo
	 *            The tipoPeriodo to set.
	 */
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	/**
	 * @return Returns the descripcionOperacion.
	 */
	public String getDescripcionOperacion() {
		return descripcionOperacion;
	}

	/**
	 * @param descripcionOperacion
	 *            The descripcionOperacion to set.
	 */
	public void setDescripcionOperacion(String descripcionOperacion) {
		String temp = StringUtils.replace(descripcionOperacion, "&&", "\n");
		this.descripcionOperacion = temp;
	}

	/**
	 * @return Returns the tipoMovimiento.
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento
	 *            The tipoMovimiento to set.
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	/**
	 * @return the tipoOperacionList
	 */
	public String[] getTipoOperacionList() {
		return tipoOperacionList;
	}

	/**
	 * @param tipoOperacionList
	 *            the tipoOperacionList to set
	 */
	public void setTipoOperacionList(String[] tipoOperacionList) {
		this.tipoOperacionList = tipoOperacionList;
	}

	/**
	 * @return the fechaFacDesde
	 */
	public String getFechaFacDesde() {
		return fechaFacDesde;
	}

	/**
	 * @param fechaFacDesde
	 *            the fechaFacDesde to set
	 */
	public void setFechaFacDesde(String fechaFacDesde) {
		this.fechaFacDesde = fechaFacDesde;
	}

	/**
	 * @return the fechaFacHasta
	 */
	public String getFechaFacHasta() {
		return fechaFacHasta;
	}

	/**
	 * @param fechaFacHasta
	 *            the fechaFacHasta to set
	 */
	public void setFechaFacHasta(String fechaFacHasta) {
		this.fechaFacHasta = fechaFacHasta;
	}

	/**
	 * @return the fechaFacDesdeD
	 */
	public Date getFechaFacDesdeD() {
		return fechaFacDesdeD;
	}

	/**
	 * @param fechaFacDesdeD
	 *            the fechaFacDesdeD to set
	 */
	public void setFechaFacDesdeD(Date fechaFacDesdeD) {
		this.fechaFacDesdeD = fechaFacDesdeD;
	}

	/**
	 * @return the fechaFacHastaD
	 */
	public Date getFechaFacHastaD() {
		return fechaFacHastaD;
	}

	/**
	 * @param fechaFacHastaD
	 *            the fechaFacHastaD to set
	 */
	public void setFechaFacHastaD(Date fechaFacHastaD) {
		this.fechaFacHastaD = fechaFacHastaD;
	}
}