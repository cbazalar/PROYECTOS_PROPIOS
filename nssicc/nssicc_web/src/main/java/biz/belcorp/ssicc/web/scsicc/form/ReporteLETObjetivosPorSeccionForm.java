package biz.belcorp.ssicc.web.scsicc.form;
import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteLETObjetivosPorSeccionForm extends BaseReporteForm	implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3210805894925612503L;
	private String codigoPais;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String campanyaObjetivo;
	private String codigoPrograma;
	private String descripcionPrograma;
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
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @return the campanyaObjetivo
	 */
	public String getCampanyaObjetivo() {
		return campanyaObjetivo;
	}
	/**
	 * @param campanyaObjetivo the campanyaObjetivo to set
	 */
	public void setCampanyaObjetivo(String campanyaObjetivo) {
		this.campanyaObjetivo = campanyaObjetivo;
	}
	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	/**
	 * @return the descripcionPrograma
	 */
	public String getDescripcionPrograma() {
		return descripcionPrograma;
	}
	/**
	 * @param descripcionPrograma the descripcionPrograma to set
	 */
	public void setDescripcionPrograma(String descripcionPrograma) {
		this.descripcionPrograma = descripcionPrograma;
	}

	
	
	
}



