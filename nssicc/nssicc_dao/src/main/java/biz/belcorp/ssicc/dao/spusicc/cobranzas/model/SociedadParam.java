package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author croman
 *
 */
public class SociedadParam extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoSociedad;
	private Integer diasCronograma;
	private Integer diasCalendario;
	private Integer cronogramaEtapas;
	private String codigoEtapa;
	private String usuarioCreacion;
	private String fechaCreacion;
	private String usuarioModificacion;
	private String fechaModificacion;
	private String sociedad;
	
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the codigoEtapa.
	 */
	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	/**
	 * @param codigoEtapa The codigoEtapa to set.
	 */
	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
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
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad The codigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	
	/**
	 * @return Returns the fechaCreacion.
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion The fechaCreacion to set.
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return Returns the fechaModificacion.
	 */
	public String getFechaModificacion() {
		return fechaModificacion;
	}

	/**
	 * @param fechaModificacion The fechaModificacion to set.
	 */
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	/**
	 * @return Returns the usuarioCreacion.
	 */
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	/**
	 * @param usuarioCreacion The usuarioCreacion to set.
	 */
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	/**
	 * @return Returns the usuarioModificacion.
	 */
	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	/**
	 * @param usuarioModificacion The usuarioModificacion to set.
	 */
	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	/**
	 * @return Returns the sociedad.
	 */
	public String getSociedad() {
		return sociedad;
	}

	/**
	 * @param sociedad The sociedad to set.
	 */
	public void setSociedad(String sociedad) {
		this.sociedad = sociedad;
	}

	/**
	 * @return Returns the cronogramaEtapas.
	 */
	public Integer getCronogramaEtapas() {
		return cronogramaEtapas;
	}

	/**
	 * @param cronogramaEtapas The cronogramaEtapas to set.
	 */
	public void setCronogramaEtapas(Integer cronogramaEtapas) {
		this.cronogramaEtapas = cronogramaEtapas;
	}

	/**
	 * @return Returns the diasCalendario.
	 */
	public Integer getDiasCalendario() {
		return diasCalendario;
	}

	/**
	 * @param diasCalendario The diasCalendario to set.
	 */
	public void setDiasCalendario(Integer diasCalendario) {
		this.diasCalendario = diasCalendario;
	}

	/**
	 * @return Returns the diasCronograma.
	 */
	public Integer getDiasCronograma() {
		return diasCronograma;
	}

	/**
	 * @param diasCronograma The diasCronograma to set.
	 */
	public void setDiasCronograma(Integer diasCronograma) {
		this.diasCronograma = diasCronograma;
	}

			
}
