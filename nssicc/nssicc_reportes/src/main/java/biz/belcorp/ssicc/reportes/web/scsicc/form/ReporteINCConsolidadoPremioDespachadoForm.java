/**
 * 
 */
package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author peextrramirez - Rosalvina Ramirez Guardia
 * 
 */
public class ReporteINCConsolidadoPremioDespachadoForm extends BaseReporteForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8524738868717509500L;

	private String codigoPais;
	
	private String codigoMarca;
	
	private String codigoCanal;
	
	private String[] listConcursos;	
	
	private String concursos;

	private String soloActivos;
	
	private String oidPais;
	
	private String oidMarca;
	
	private String oidCanal;


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
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return the listConcursos
	 */
	public String[] getListConcursos() {
		return listConcursos;
	}

	/**
	 * @param listConcursos the listConcursos to set
	 */
	public void setListConcursos(String[] listConcursos) {
		this.listConcursos = listConcursos;
	}
	
	/**
	 * @return the concursos
	 */
	public String getConcursos() {
		return concursos;
	}

	/**
	 * @param concursos the concursos to set
	 */
	public void setConcursos(String concursos) {
		this.concursos = concursos;
	}

	/**
	 * @return the soloActivos
	 */
	public String getSoloActivos() {
		return soloActivos;
	}

	/**
	 * @param soloActivos the soloActivos to set
	 */
	public void setSoloActivos(String soloActivos) {
		this.soloActivos = soloActivos;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	
	/**
	 * @return the oidMarca
	 */
	public String getOidMarca() {
		return oidMarca;
	}

	/**
	 * @param oidMarca the oidMarca to set
	 */
	public void setOidMarca(String oidMarca) {
		this.oidMarca = oidMarca;
	}
	
	/**
	 * @return the oidCanal
	 */
	public String getOidCanal() {
		return oidCanal;
	}

	/**
	 * @param oidCanal the oidCanal to set
	 */
	public void setOidCanal(String oidCanal) {
		this.oidCanal = oidCanal;
	}
}
