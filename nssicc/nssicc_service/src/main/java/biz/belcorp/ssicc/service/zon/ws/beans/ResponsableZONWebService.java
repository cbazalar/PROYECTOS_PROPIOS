package biz.belcorp.ssicc.service.zon.ws.beans;

import java.io.Serializable;

public class ResponsableZONWebService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoConsultora;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String indUA;
	private String IndOperacion;

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return
	 */
	public String getIndUA() {
		return indUA;
	}

	/**
	 * @param indUA
	 */
	public void setIndUA(String indUA) {
		this.indUA = indUA;
	}

	/**
	 * @return
	 */
	public String getIndOperacion() {
		return IndOperacion;
	}

	/**
	 * @param indOperacion
	 */
	public void setIndOperacion(String indOperacion) {
		IndOperacion = indOperacion;
	}

}
