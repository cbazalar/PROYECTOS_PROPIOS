/*
 * Created on 06-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;
import biz.belcorp.ssicc.dao.model.Pais;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="NormaInterfaz.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class NormaInterfaz extends AuditableBaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4762798342251468879L;

	private String codigoPais;

	private String codigo;

	private String codigoTipoFormatoArchivo;

	private String flagRellenoNumerico;

	private String rellenoNumerico;

	private String flagRellenoAlfanumerico;

	private String rellenoAlfanumerico;

	private String flagAlineamientoNumerico;

	private String alineamientoNumerico;

	private String flagAlineamientoAlfanumerico;

	private String alineamientoAlfanumerico;

	private String flagFecha;

	private String formatoFecha;

	private String flagTruncamientoNumerico;

	private String truncamientoNumerico;

	private String flagTruncamientoAlfanumerico;

	private String truncamientoAlfanumerico;

	private String estado;

	private Pais pais;

	private TipoFormatoArchivo tipoFormatoArchivo;

	/**
	 * @return Returns the pais.
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * @param pais
	 *            The pais to set.
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	/**
	 * @return Returns the tipoFormatoArchivo.
	 */
	public TipoFormatoArchivo getTipoFormatoArchivo() {
		return tipoFormatoArchivo;
	}

	/**
	 * @param tipoFormatoArchivo
	 *            The tipoFormatoArchivo to set.
	 */
	public void setTipoFormatoArchivo(TipoFormatoArchivo tipoFormatoArchivo) {
		this.tipoFormatoArchivo = tipoFormatoArchivo;
	}

	/**
	 * @return Returns the alineamientoAlfanumerico.
	 */
	public String getAlineamientoAlfanumerico() {
		return alineamientoAlfanumerico;
	}

	/**
	 * @param alineamientoAlfanumerico
	 *            The alineamientoAlfanumerico to set.
	 */
	public void setAlineamientoAlfanumerico(String alineamientoAlfanumerico) {
		this.alineamientoAlfanumerico = alineamientoAlfanumerico;
	}

	/**
	 * @return Returns the alineamientoNumerico.
	 */
	public String getAlineamientoNumerico() {
		return alineamientoNumerico;
	}

	/**
	 * @param alineamientoNumerico
	 *            The alineamientoNumerico to set.
	 */
	public void setAlineamientoNumerico(String alineamientoNumerico) {
		this.alineamientoNumerico = alineamientoNumerico;
	}

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * @return Returns the codigoTipoFormatoArchivo.
	 */
	public String getCodigoTipoFormatoArchivo() {
		return codigoTipoFormatoArchivo;
	}

	/**
	 * @param codigoTipoFormatoArchivo
	 *            The codigoTipoFormatoArchivo to set.
	 */
	public void setCodigoTipoFormatoArchivo(String codigoTipoFormatoArchivo) {
		this.codigoTipoFormatoArchivo = codigoTipoFormatoArchivo;
	}

	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return Returns the flagAlineamientoAlfanumerico.
	 */
	public String getFlagAlineamientoAlfanumerico() {
		return flagAlineamientoAlfanumerico;
	}

	/**
	 * @param flagAlineamientoAlfanumerico
	 *            The flagAlineamientoAlfanumerico to set.
	 */
	public void setFlagAlineamientoAlfanumerico(
			String flagAlineamientoAlfanumerico) {
		this.flagAlineamientoAlfanumerico = flagAlineamientoAlfanumerico;
	}

	/**
	 * @return Returns the flagAlineamientoNumerico.
	 */
	public String getFlagAlineamientoNumerico() {
		return flagAlineamientoNumerico;
	}

	/**
	 * @param flagAlineamientoNumerico
	 *            The flagAlineamientoNumerico to set.
	 */
	public void setFlagAlineamientoNumerico(String flagAlineamientoNumerico) {
		this.flagAlineamientoNumerico = flagAlineamientoNumerico;
	}

	/**
	 * @return Returns the flagFecha.
	 */
	public String getFlagFecha() {
		return flagFecha;
	}

	/**
	 * @param flagFecha
	 *            The flagFecha to set.
	 */
	public void setFlagFecha(String flagFecha) {
		this.flagFecha = flagFecha;
	}

	/**
	 * @return Returns the flagRellenoAlfanumerico.
	 */
	public String getFlagRellenoAlfanumerico() {
		return flagRellenoAlfanumerico;
	}

	/**
	 * @param flagRellenoAlfanumerico
	 *            The flagRellenoAlfanumerico to set.
	 */
	public void setFlagRellenoAlfanumerico(String flagRellenoAlfanumerico) {
		this.flagRellenoAlfanumerico = flagRellenoAlfanumerico;
	}

	/**
	 * @return Returns the flagRellenoNumerico.
	 */
	public String getFlagRellenoNumerico() {
		return flagRellenoNumerico;
	}

	/**
	 * @param flagRellenoNumerico
	 *            The flagRellenoNumerico to set.
	 */
	public void setFlagRellenoNumerico(String flagRellenoNumerico) {
		this.flagRellenoNumerico = flagRellenoNumerico;
	}

	/**
	 * @return Returns the flagTruncamientoAlfanumerico.
	 */
	public String getFlagTruncamientoAlfanumerico() {
		return flagTruncamientoAlfanumerico;
	}

	/**
	 * @param flagTruncamientoAlfanumerico
	 *            The flagTruncamientoAlfanumerico to set.
	 */
	public void setFlagTruncamientoAlfanumerico(
			String flagTruncamientoAlfanumerico) {
		this.flagTruncamientoAlfanumerico = flagTruncamientoAlfanumerico;
	}

	/**
	 * @return Returns the flagTruncamientoNumerico.
	 */
	public String getFlagTruncamientoNumerico() {
		return flagTruncamientoNumerico;
	}

	/**
	 * @param flagTruncamientoNumerico
	 *            The flagTruncamientoNumerico to set.
	 */
	public void setFlagTruncamientoNumerico(String flagTruncamientoNumerico) {
		this.flagTruncamientoNumerico = flagTruncamientoNumerico;
	}

	/**
	 * @return Returns the formatoFecha.
	 */
	public String getFormatoFecha() {
		return formatoFecha;
	}

	/**
	 * @param formatoFecha
	 *            The formatoFecha to set.
	 */
	public void setFormatoFecha(String formatoFecha) {
		this.formatoFecha = formatoFecha;
	}

	/**
	 * @return Returns the rellenoAlfanumerico.
	 */
	public String getRellenoAlfanumerico() {
		return rellenoAlfanumerico;
	}

	/**
	 * @param rellenoAlfanumerico
	 *            The rellenoAlfanumerico to set.
	 */
	public void setRellenoAlfanumerico(String rellenoAlfanumerico) {
		this.rellenoAlfanumerico = rellenoAlfanumerico;
	}

	/**
	 * @return Returns the rellenoNumerico.
	 */
	public String getRellenoNumerico() {
		return rellenoNumerico;
	}

	/**
	 * @param rellenoNumerico
	 *            The rellenoNumerico to set.
	 */
	public void setRellenoNumerico(String rellenoNumerico) {
		this.rellenoNumerico = rellenoNumerico;
	}

	/**
	 * @return Returns the truncamientoAlfanumerico.
	 */
	public String getTruncamientoAlfanumerico() {
		return truncamientoAlfanumerico;
	}

	/**
	 * @param truncamientoAlfanumerico
	 *            The truncamientoAlfanumerico to set.
	 */
	public void setTruncamientoAlfanumerico(String truncamientoAlfanumerico) {
		this.truncamientoAlfanumerico = truncamientoAlfanumerico;
	}

	/**
	 * @return Returns the truncamientoNumerico.
	 */
	public String getTruncamientoNumerico() {
		return truncamientoNumerico;
	}

	/**
	 * @param truncamientoNumerico
	 *            The truncamientoNumerico to set.
	 */
	public void setTruncamientoNumerico(String truncamientoNumerico) {
		this.truncamientoNumerico = truncamientoNumerico;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("tipoFormatoArchivo", this.tipoFormatoArchivo)
				.append("codigoPais", this.codigoPais)
				.append("flagFecha", this.flagFecha)
				.append("flagAlineamientoAlfanumerico",
						this.flagAlineamientoAlfanumerico)
				.append("formatoFecha", this.formatoFecha)
				.append("flagTruncamientoNumerico",
						this.flagTruncamientoNumerico)
				.append("alineamientoAlfanumerico",
						this.alineamientoAlfanumerico)
				.append("flagRellenoAlfanumerico", this.flagRellenoAlfanumerico)
				.append("flagAlineamientoNumerico",
						this.flagAlineamientoNumerico).append(
						"truncamientoNumerico", this.truncamientoNumerico)
				.append("rellenoAlfanumerico", this.rellenoAlfanumerico)
				.append("flagTruncamientoAlfanumerico",
						this.flagTruncamientoAlfanumerico).append(
						"codigoTipoFormatoArchivo",
						this.codigoTipoFormatoArchivo).append(
						"flagRellenoNumerico", this.flagRellenoNumerico)
				.append("truncamientoAlfanumerico",
						this.truncamientoAlfanumerico).append("estado",
						this.estado).append("rellenoNumerico",
						this.rellenoNumerico).append("codigo", this.codigo)
				.append("alineamientoNumerico", this.alineamientoNumerico)
				.toString();
	}
}
