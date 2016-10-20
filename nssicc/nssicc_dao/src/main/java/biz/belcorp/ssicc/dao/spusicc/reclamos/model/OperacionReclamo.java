package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * 
 * <p>
 * <a href="OperacionReclamo.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 * 
 *                      
 */
public class OperacionReclamo extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String oidOperacionReclamo;
	private String codigoOperacion;
	private String descripcion;
	private String indAnulacion;
	private String indDevFisicoFact;
	private String nroCampHistoria;
	private String tipoPrecio;
	private String tipoPrecioEnvia;
	private String codigoMotivoBloqueo;
	private String indFaltanteMercaderia;
	private String indEnvia;
	private String tipoSolicitud;
	private String indEnviaGeneraDev;
	private String indDevuelve;
	private String tipoSolicitudGenera;
	private String almacen;
	private String indDevuelveGeneraEnv;
	private String codigoMovimientoAlmacen;
	
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
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the indAnulacion
	 */
	public String getIndAnulacion() {
		return indAnulacion;
	}

	/**
	 * @param indAnulacion the indAnulacion to set
	 */
	public void setIndAnulacion(String indAnulacion) {
		this.indAnulacion = indAnulacion;
	}

	/**
	 * @return the indDevFisicoFact
	 */
	public String getIndDevFisicoFact() {
		return indDevFisicoFact;
	}

	/**
	 * @param indDevFisicoFact the indDevFisicoFact to set
	 */
	public void setIndDevFisicoFact(String indDevFisicoFact) {
		this.indDevFisicoFact = indDevFisicoFact;
	}

	/**
	 * @return the nroCampHistoria
	 */
	public String getNroCampHistoria() {
		return nroCampHistoria;
	}

	/**
	 * @param nroCampHistoria the nroCampHistoria to set
	 */
	public void setNroCampHistoria(String nroCampHistoria) {
		this.nroCampHistoria = nroCampHistoria;
	}

	/**
	 * @return the tipoPrecio
	 */
	public String getTipoPrecio() {
		return tipoPrecio;
	}

	/**
	 * @param tipoPrecio the tipoPrecio to set
	 */
	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	/**
	 * @return the tipoPrecioEnvia
	 */
	public String getTipoPrecioEnvia() {
		return tipoPrecioEnvia;
	}

	/**
	 * @param tipoPrecioEnvia the tipoPrecioEnvia to set
	 */
	public void setTipoPrecioEnvia(String tipoPrecioEnvia) {
		this.tipoPrecioEnvia = tipoPrecioEnvia;
	}

	/**
	 * @return the codigoMotivoBloqueo
	 */
	public String getCodigoMotivoBloqueo() {
		return codigoMotivoBloqueo;
	}

	/**
	 * @param codigoMotivoBloqueo the codigoMotivoBloqueo to set
	 */
	public void setCodigoMotivoBloqueo(String codigoMotivoBloqueo) {
		this.codigoMotivoBloqueo = codigoMotivoBloqueo;
	}

	/**
	 * @return the indFaltanteMercaderia
	 */
	public String getIndFaltanteMercaderia() {
		return indFaltanteMercaderia;
	}

	/**
	 * @param indFaltanteMercaderia the indFaltanteMercaderia to set
	 */
	public void setIndFaltanteMercaderia(String indFaltanteMercaderia) {
		this.indFaltanteMercaderia = indFaltanteMercaderia;
	}

	/**
	 * @return the indEnvia
	 */
	public String getIndEnvia() {
		return indEnvia;
	}

	/**
	 * @param indEnvia the indEnvia to set
	 */
	public void setIndEnvia(String indEnvia) {
		this.indEnvia = indEnvia;
	}

	/**
	 * @return the indEnviaGeneraDev
	 */
	public String getIndEnviaGeneraDev() {
		return indEnviaGeneraDev;
	}

	/**
	 * @param indEnviaGeneraDev the indEnviaGeneraDev to set
	 */
	public void setIndEnviaGeneraDev(String indEnviaGeneraDev) {
		this.indEnviaGeneraDev = indEnviaGeneraDev;
	}

	/**
	 * @return the indDevuelve
	 */
	public String getIndDevuelve() {
		return indDevuelve;
	}

	/**
	 * @param indDevuelve the indDevuelve to set
	 */
	public void setIndDevuelve(String indDevuelve) {
		this.indDevuelve = indDevuelve;
	}

	/**
	 * @return the indDevuelveGeneraEnv
	 */
	public String getIndDevuelveGeneraEnv() {
		return indDevuelveGeneraEnv;
	}

	/**
	 * @param indDevuelveGeneraEnv the indDevuelveGeneraEnv to set
	 */
	public void setIndDevuelveGeneraEnv(String indDevuelveGeneraEnv) {
		this.indDevuelveGeneraEnv = indDevuelveGeneraEnv;
	}

	/**
	 * @return the codigoMovimientoAlmacen
	 */
	public String getCodigoMovimientoAlmacen() {
		return codigoMovimientoAlmacen;
	}

	/**
	 * @param codigoMovimientoAlmacen the codigoMovimientoAlmacen to set
	 */
	public void setCodigoMovimientoAlmacen(String codigoMovimientoAlmacen) {
		this.codigoMovimientoAlmacen = codigoMovimientoAlmacen;
	}

	/**
	 * @return the tipoSolicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * @param tipoSolicitud the tipoSolicitud to set
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * @return the tipoSolicitudGenera
	 */
	public String getTipoSolicitudGenera() {
		return tipoSolicitudGenera;
	}

	/**
	 * @param tipoSolicitudGenera the tipoSolicitudGenera to set
	 */
	public void setTipoSolicitudGenera(String tipoSolicitudGenera) {
		this.tipoSolicitudGenera = tipoSolicitudGenera;
	}

	/**
	 * @return the almacen
	 */
	public String getAlmacen() {
		return almacen;
	}

	/**
	 * @param almacen the almacen to set
	 */
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
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