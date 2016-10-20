package biz.belcorp.ssicc.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:mmacias@belcorp.biz">Carolina Macias </a>
 * 
 */
public class ReporteAPEMapaAnaquelForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoCentro;
	
	private String codigoLinea;
	
	private String mapa;
	
	private String version;
	
	private String codsublinea;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return this.codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoCentro
	 */
	public String getCodigoCentro() {
		return this.codigoCentro;
	}

	/**
	 * @param codigoCentro the codigoCentro to set
	 */
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
	}

	/**
	 * @return the codigoLinea
	 */
	public String getCodigoLinea() {
		return this.codigoLinea;
	}

	/**
	 * @param codigoLinea the codigoLinea to set
	 */
	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}

	/**
	 * @return the mapa
	 */
	public String getMapa() {
		return this.mapa;
	}

	/**
	 * @param mapa the mapa to set
	 */
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCodsublinea() {
		return codsublinea;
	}

	public void setCodsublinea(String codsublinea) {
		this.codsublinea = codsublinea;
	}


	
}
