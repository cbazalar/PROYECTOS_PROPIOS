package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class ParametroReporte extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4122821472787036315L;
	private String codigoPais;
	private String nombreReporte;
	private String rutaGrabar;
	private String prefijoArchivo;
	private String estadoRegistro;
	private String correoOrigen;
	private String correoDefaultDestino;
	private String correoDestino;
	private String tituloCorreo;
	private String indicadorEnvioCorreo;
	private String plantillaCorreoTxt;
	private String plantillaCorreoHtml;
	private String nivelEnvioCorreo;
	
	private String indicadorEmailRegional;
	
	/**
	 * @return Returns the indicadorEmailRegional.
	 */
	public String getIndicadorEmailRegional() {
		return indicadorEmailRegional;
	}
	/**
	 * @param indicadorEmailRegional The indicadorEmailRegional to set.
	 */
	public void setIndicadorEmailRegional(String indicadorEmailRegional) {
		this.indicadorEmailRegional = indicadorEmailRegional;
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
	 * @return Returns the correoDefaultDestino.
	 */
	public String getCorreoDefaultDestino() {
		return correoDefaultDestino;
	}
	/**
	 * @param correoDefaultDestino The correoDefaultDestino to set.
	 */
	public void setCorreoDefaultDestino(String correoDefaultDestino) {
		this.correoDefaultDestino = correoDefaultDestino;
	}
	/**
	 * @return Returns the correoDestino.
	 */
	public String getCorreoDestino() {
		return correoDestino;
	}
	/**
	 * @param correoDestino The correoDestino to set.
	 */
	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}
	/**
	 * @return Returns the correoOrigen.
	 */
	public String getCorreoOrigen() {
		return correoOrigen;
	}
	/**
	 * @param correoOrigen The correoOrigen to set.
	 */
	public void setCorreoOrigen(String correoOrigen) {
		this.correoOrigen = correoOrigen;
	}
	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return Returns the indicadorEnvioCorreo.
	 */
	public String getIndicadorEnvioCorreo() {
		return indicadorEnvioCorreo;
	}
	/**
	 * @param indicadorEnvioCorreo The indicadorEnvioCorreo to set.
	 */
	public void setIndicadorEnvioCorreo(String indicadorEnvioCorreo) {
		this.indicadorEnvioCorreo = indicadorEnvioCorreo;
	}
	/**
	 * @return Returns the nivelEnvioCorreo.
	 */
	public String getNivelEnvioCorreo() {
		return nivelEnvioCorreo;
	}
	/**
	 * @param nivelEnvioCorreo The nivelEnvioCorreo to set.
	 */
	public void setNivelEnvioCorreo(String nivelEnvioCorreo) {
		this.nivelEnvioCorreo = nivelEnvioCorreo;
	}
	/**
	 * @return Returns the nombreReporte.
	 */
	public String getNombreReporte() {
		return nombreReporte;
	}
	/**
	 * @param nombreReporte The nombreReporte to set.
	 */
	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}
	/**
	 * @return Returns the plantillaCorreoHtml.
	 */
	public String getPlantillaCorreoHtml() {
		return plantillaCorreoHtml;
	}
	/**
	 * @param plantillaCorreoHtml The plantillaCorreoHtml to set.
	 */
	public void setPlantillaCorreoHtml(String plantillaCorreoHtml) {
		this.plantillaCorreoHtml = plantillaCorreoHtml;
	}
	/**
	 * @return Returns the plantillaCorreoTxt.
	 */
	public String getPlantillaCorreoTxt() {
		return plantillaCorreoTxt;
	}
	/**
	 * @param plantillaCorreoTxt The plantillaCorreoTxt to set.
	 */
	public void setPlantillaCorreoTxt(String plantillaCorreoTxt) {
		this.plantillaCorreoTxt = plantillaCorreoTxt;
	}
	/**
	 * @return Returns the prefijoArchivo.
	 */
	public String getPrefijoArchivo() {
		return prefijoArchivo;
	}
	/**
	 * @param prefijoArchivo The prefijoArchivo to set.
	 */
	public void setPrefijoArchivo(String prefijoArchivo) {
		this.prefijoArchivo = prefijoArchivo;
	}
	/**
	 * @return Returns the rutaGrabar.
	 */
	public String getRutaGrabar() {
		return rutaGrabar;
	}
	/**
	 * @param rutaGrabar The rutaGrabar to set.
	 */
	public void setRutaGrabar(String rutaGrabar) {
		this.rutaGrabar = rutaGrabar;
	}
	/**
	 * @return Returns the tituloCorreo.
	 */
	public String getTituloCorreo() {
		return tituloCorreo;
	}
	/**
	 * @param tituloCorreo The tituloCorreo to set.
	 */
	public void setTituloCorreo(String tituloCorreo) {
		this.tituloCorreo = tituloCorreo;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
