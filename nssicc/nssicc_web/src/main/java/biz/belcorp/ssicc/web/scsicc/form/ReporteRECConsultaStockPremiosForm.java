package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * Form base para los Reportes REC de ScSiCC.
 * 
 * @author <a href="">Marco Agurto</a>
 * 
 */
public class ReporteRECConsultaStockPremiosForm extends BaseReporteForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String [] codigoConcurso;
	
	private String descripcionConcurso;

	private String codigoSap;

	private String descripcionCorta;
	
	private String codigoCanal;
	
	private String codigoMarca;

	private String codigoVenta;

	private String codigoPais;
	
	
	/**
	 * @return Returns the codigoSap.
	 */
	public String getCodigoSap() {
		return codigoSap;
	}

	/**
	 * @param codigoSap
	 *            The codigoSap to set.
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	/**
	 * @return Returns the descripcionCorta.
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * @param descripcionCorta
	 *            The descripcionCorta to set.
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	/**
	 * @return Returns the codigoVenta.
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta
	 *            The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
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
	 * @return Returns the codigoConcurso.
	 */
	public String []getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @param codigoConcurso
	 *            The codigoConcurso to set.
	 */
	public void setCodigoConcurso(String []codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	/**
	 * @return Returns the descripcionConcurso.
	 */
	public String getDescripcionConcurso() {
		return descripcionConcurso;
	}

	/**
	 * @param descripcionConcurso The descripcionConcurso to set.
	 */
	public void setDescripcionConcurso(String descripcionConcurso) {
		this.descripcionConcurso = descripcionConcurso;
	}

	/**
	 * @return Returns the codigoCanal.	 * 
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal The codigoCanal to set.
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
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

}
