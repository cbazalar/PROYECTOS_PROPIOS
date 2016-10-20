package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * 
 * <p>
 * <a href="TipoOperacion.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 *                      
 */
public class TipoOperacion extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String oidTipoOperacion;
	private String codigoTipoOperacion;
	private String indCampReferenciaUnica;
	private String numDiasHaciaAtras;
	private String indInfoBelcorpNoticias;
	private String indDevuelveEstaFactura;
	private String indEnviaEstaFactura;
	private String oidOperacionReclamo;
	private String codigoMotivoRechazo;
	private String indicadorAccion;
	private String descMotivoRechazo;

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
	 * @return the oidTipoOperacion
	 */
	public String getOidTipoOperacion() {
		return oidTipoOperacion;
	}

	/**
	 * @param oidTipoOperacion the oidTipoOperacion to set
	 */
	public void setOidTipoOperacion(String oidTipoOperacion) {
		this.oidTipoOperacion = oidTipoOperacion;
	}

	/**
	 * @return the codigoTipoOperacion
	 */
	public String getCodigoTipoOperacion() {
		return codigoTipoOperacion;
	}

	/**
	 * @param codigoTipoOperacion the codigoTipoOperacion to set
	 */
	public void setCodigoTipoOperacion(String codigoTipoOperacion) {
		this.codigoTipoOperacion = codigoTipoOperacion;
	}

	/**
	 * @return the indCampReferenciaUnica
	 */
	public String getIndCampReferenciaUnica() {
		return indCampReferenciaUnica;
	}

	/**
	 * @param indCampReferenciaUnica the indCampReferenciaUnica to set
	 */
	public void setIndCampReferenciaUnica(String indCampReferenciaUnica) {
		this.indCampReferenciaUnica = indCampReferenciaUnica;
	}

	/**
	 * @return the numDiasHaciaAtras
	 */
	public String getNumDiasHaciaAtras() {
		return numDiasHaciaAtras;
	}

	/**
	 * @param numDiasHaciaAtras the numDiasHaciaAtras to set
	 */
	public void setNumDiasHaciaAtras(String numDiasHaciaAtras) {
		this.numDiasHaciaAtras = numDiasHaciaAtras;
	}

	/**
	 * @return the indInfoBelcorpNoticias
	 */
	public String getIndInfoBelcorpNoticias() {
		return indInfoBelcorpNoticias;
	}

	/**
	 * @param indInfoBelcorpNoticias the indInfoBelcorpNoticias to set
	 */
	public void setIndInfoBelcorpNoticias(String indInfoBelcorpNoticias) {
		this.indInfoBelcorpNoticias = indInfoBelcorpNoticias;
	}

	/**
	 * @return the indDevuelveEstaFactura
	 */
	public String getIndDevuelveEstaFactura() {
		return indDevuelveEstaFactura;
	}

	/**
	 * @param indDevuelveEstaFactura the indDevuelveEstaFactura to set
	 */
	public void setIndDevuelveEstaFactura(String indDevuelveEstaFactura) {
		this.indDevuelveEstaFactura = indDevuelveEstaFactura;
	}

	/**
	 * @return the indEnviaEstaFactura
	 */
	public String getIndEnviaEstaFactura() {
		return indEnviaEstaFactura;
	}

	/**
	 * @param indEnviaEstaFactura the indEnviaEstaFactura to set
	 */
	public void setIndEnviaEstaFactura(String indEnviaEstaFactura) {
		this.indEnviaEstaFactura = indEnviaEstaFactura;
	}

	/**
	 * @return the oidOperacionReclamo
	 */
	public String getOidOperacionReclamo() {
		return oidOperacionReclamo;
	}

	/**
	 * @param oidOperacionReclamo the oidOperacionReclamo to set
	 */
	public void setOidOperacionReclamo(String oidOperacionReclamo) {
		this.oidOperacionReclamo = oidOperacionReclamo;
	}

	/**
	 * @return the codigoMotivoRechazo
	 */
	public String getCodigoMotivoRechazo() {
		return codigoMotivoRechazo;
	}

	/**
	 * @param codigoMotivoRechazo the codigoMotivoRechazo to set
	 */
	public void setCodigoMotivoRechazo(String codigoMotivoRechazo) {
		this.codigoMotivoRechazo = codigoMotivoRechazo;
	}

	/**
	 * @return the indicadorAccion
	 */
	public String getIndicadorAccion() {
		return indicadorAccion;
	}

	/**
	 * @param indicadorAccion the indicadorAccion to set
	 */
	public void setIndicadorAccion(String indicadorAccion) {
		this.indicadorAccion = indicadorAccion;
	}

	/**
	 * @return the descMotivoRechazo
	 */
	public String getDescMotivoRechazo() {
		return descMotivoRechazo;
	}

	/**
	 * @param descMotivoRechazo the descMotivoRechazo to set
	 */
	public void setDescMotivoRechazo(String descMotivoRechazo) {
		this.descMotivoRechazo = descMotivoRechazo;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
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
		return ToStringBuilder.reflectionToString(this);
	}
}