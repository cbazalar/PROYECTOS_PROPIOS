package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public class ReporteCOBReporteRecuperacion31DiasForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String codigoSociedad;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String codigoPeriodo;

	private String descripcionRegion;
	private String descripcionZona;
	
	private String idiomaReporte;
	private String paisReporte;

	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
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
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		String temp = StringUtils.replace(descripcionRegion, "&&","\n" );
		this.descripcionRegion = temp;
	}
	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		String temp = StringUtils.replace(descripcionZona, "&&","\n" );
		this.descripcionZona = temp;
	}

	/**
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the idiomaReporte
	 */
	public String getIdiomaReporte() {
		return idiomaReporte;
	}
	/**
	 * @param idiomaReporte the idiomaReporte to set
	 */
	public void setIdiomaReporte(String idiomaReporte) {
		this.idiomaReporte = idiomaReporte;
	}
	/**
	 * @return the paisReporte
	 */
	public String getPaisReporte() {
		return paisReporte;
	}
	/**
	 * @param paisReporte the paisReporte to set
	 */
	public void setPaisReporte(String paisReporte) {
		this.paisReporte = paisReporte;
	}
	
}
