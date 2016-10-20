package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;


/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class AccesoRolSTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
    private String codigoTipoDocumento;
    private String codigoAccion;
    private Integer nivelAccion; 
    private String desAccion;
    private String indicadorActivo;
    private String estadoAccion;
	/**
	 * @return Returns the codigoAccion.
	 */
	public String getCodigoAccion() {
		return codigoAccion;
	}
	/**
	 * @param codigoAccion The codigoAccion to set.
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoTipoDocumento.
	 */
	public String getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}
	/**
	 * @param codigoTipoDocumento The codigoTipoDocumento to set.
	 */
	public void setCodigoTipoDocumento(String codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}
	/**
	 * @return Returns the desAccion.
	 */
	public String getDesAccion() {
		return desAccion;
	}
	/**
	 * @param desAccion The desAccion to set.
	 */
	public void setDesAccion(String desAccion) {
		this.desAccion = desAccion;
	}
	/**
	 * @return Returns the estadoAccion.
	 */
	public String getEstadoAccion() {
		return estadoAccion;
	}
	/**
	 * @param estadoAccion The estadoAccion to set.
	 */
	public void setEstadoAccion(String estadoAccion) {
		this.estadoAccion = estadoAccion;
	}
	/**
	 * @return Returns the indicadorActivo.
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo The indicadorActivo to set.
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return Returns the nivelAccion.
	 */
	public Integer getNivelAccion() {
		return nivelAccion;
	}
	/**
	 * @param nivelAccion The nivelAccion to set.
	 */
	public void setNivelAccion(Integer nivelAccion) {
		this.nivelAccion = nivelAccion;
	}
    

}
