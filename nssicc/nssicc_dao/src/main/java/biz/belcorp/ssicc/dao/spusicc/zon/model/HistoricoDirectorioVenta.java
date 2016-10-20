/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.zon.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="itocto@sigcomt.com">Ivan Tocto</a>
 *
 */
public class HistoricoDirectorioVenta extends AuditableBaseObject implements Serializable {

	private String oid;
	private String codigoPais;
	private String codigoCliente;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String primerNombre;
	private String segundoNombre;
	private String codigoBelcorp;
	private String oidTipoDocumentoIdentidad;
	private String numeroDocumentoIdentidad;
	private String fechaNacimiento;
	private String codigoRegion;
	private String codigoZona;
	private String numeroCelularEmpresa;
	private String numeroCelularPersonal;
	private String direccionDomicilio;
	private String numeroTelefonoCasa;
	private String email;
	private String oidPadre;
	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
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
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return the apellidoMaterno
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno the apellidoMaterno to set
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return the primerNombre
	 */
	public String getPrimerNombre() {
		return primerNombre;
	}
	/**
	 * @param primerNombre the primerNombre to set
	 */
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	/**
	 * @return the segundoNombre
	 */
	public String getSegundoNombre() {
		return segundoNombre;
	}
	/**
	 * @param segundoNombre the segundoNombre to set
	 */
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	/**
	 * @return the codigoBelcorp
	 */
	public String getCodigoBelcorp() {
		return codigoBelcorp;
	}
	/**
	 * @param codigoBelcorp the codigoBelcorp to set
	 */
	public void setCodigoBelcorp(String codigoBelcorp) {
		this.codigoBelcorp = codigoBelcorp;
	}
	/**
	 * @return the oidTipoDocumentoIdentidad
	 */
	public String getOidTipoDocumentoIdentidad() {
		return oidTipoDocumentoIdentidad;
	}
	/**
	 * @param oidTipoDocumentoIdentidad the oidTipoDocumentoIdentidad to set
	 */
	public void setOidTipoDocumentoIdentidad(String oidTipoDocumentoIdentidad) {
		this.oidTipoDocumentoIdentidad = oidTipoDocumentoIdentidad;
	}
	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}
	/**
	 * @param numeroDocumentoIdentidad the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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
	 * @return the numeroCelularEmpresa
	 */
	public String getNumeroCelularEmpresa() {
		return numeroCelularEmpresa;
	}
	/**
	 * @param numeroCelularEmpresa the numeroCelularEmpresa to set
	 */
	public void setNumeroCelularEmpresa(String numeroCelularEmpresa) {
		this.numeroCelularEmpresa = numeroCelularEmpresa;
	}
	/**
	 * @return the numeroCelularPersonal
	 */
	public String getNumeroCelularPersonal() {
		return numeroCelularPersonal;
	}
	/**
	 * @param numeroCelularPersonal the numeroCelularPersonal to set
	 */
	public void setNumeroCelularPersonal(String numeroCelularPersonal) {
		this.numeroCelularPersonal = numeroCelularPersonal;
	}
	/**
	 * @return the direccionDomicilio
	 */
	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}
	/**
	 * @param direccionDomicilio the direccionDomicilio to set
	 */
	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}
	/**
	 * @return the numeroTelefonoCasa
	 */
	public String getNumeroTelefonoCasa() {
		return numeroTelefonoCasa;
	}
	/**
	 * @param numeroTelefonoCasa the numeroTelefonoCasa to set
	 */
	public void setNumeroTelefonoCasa(String numeroTelefonoCasa) {
		this.numeroTelefonoCasa = numeroTelefonoCasa;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the oidPadre
	 */
	public String getOidPadre() {
		return oidPadre;
	}
	/**
	 * @param oidPadre the oidPadre to set
	 */
	public void setOidPadre(String oidPadre) {
		this.oidPadre = oidPadre;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 13;
		result = prime * result
				+ ((apellidoMaterno == null) ? 0 : apellidoMaterno.hashCode());
		result = prime * result
				+ ((apellidoPaterno == null) ? 0 : apellidoPaterno.hashCode());
		result = prime * result
				+ ((codigoBelcorp == null) ? 0 : codigoBelcorp.hashCode());
		result = prime * result
				+ ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoRegion == null) ? 0 : codigoRegion.hashCode());
		result = prime * result
				+ ((codigoZona == null) ? 0 : codigoZona.hashCode());
		result = prime
				* result
				+ ((direccionDomicilio == null) ? 0 : direccionDomicilio
						.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
		result = prime
				* result
				+ ((numeroCelularEmpresa == null) ? 0 : numeroCelularEmpresa
						.hashCode());
		result = prime
				* result
				+ ((numeroCelularPersonal == null) ? 0 : numeroCelularPersonal
						.hashCode());
		result = prime
				* result
				+ ((numeroDocumentoIdentidad == null) ? 0
						: numeroDocumentoIdentidad.hashCode());
		result = prime
				* result
				+ ((numeroTelefonoCasa == null) ? 0 : numeroTelefonoCasa
						.hashCode());
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result
				+ ((oidPadre == null) ? 0 : oidPadre.hashCode());
		result = prime
				* result
				+ ((oidTipoDocumentoIdentidad == null) ? 0
						: oidTipoDocumentoIdentidad.hashCode());
		result = prime * result
				+ ((primerNombre == null) ? 0 : primerNombre.hashCode());
		result = prime * result
				+ ((segundoNombre == null) ? 0 : segundoNombre.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		HistoricoDirectorioVenta other = (HistoricoDirectorioVenta) obj;
		if (apellidoMaterno == null) {
			if (other.apellidoMaterno != null)
				return false;
		} else if (!apellidoMaterno.equals(other.apellidoMaterno))
			return false;
		if (apellidoPaterno == null) {
			if (other.apellidoPaterno != null)
				return false;
		} else if (!apellidoPaterno.equals(other.apellidoPaterno))
			return false;
		if (codigoBelcorp == null) {
			if (other.codigoBelcorp != null)
				return false;
		} else if (!codigoBelcorp.equals(other.codigoBelcorp))
			return false;
		if (codigoCliente == null) {
			if (other.codigoCliente != null)
				return false;
		} else if (!codigoCliente.equals(other.codigoCliente))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoRegion == null) {
			if (other.codigoRegion != null)
				return false;
		} else if (!codigoRegion.equals(other.codigoRegion))
			return false;
		if (codigoZona == null) {
			if (other.codigoZona != null)
				return false;
		} else if (!codigoZona.equals(other.codigoZona))
			return false;
		if (direccionDomicilio == null) {
			if (other.direccionDomicilio != null)
				return false;
		} else if (!direccionDomicilio.equals(other.direccionDomicilio))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fechaNacimiento == null) {
			if (other.fechaNacimiento != null)
				return false;
		} else if (!fechaNacimiento.equals(other.fechaNacimiento))
			return false;
		if (numeroCelularEmpresa == null) {
			if (other.numeroCelularEmpresa != null)
				return false;
		} else if (!numeroCelularEmpresa.equals(other.numeroCelularEmpresa))
			return false;
		if (numeroCelularPersonal == null) {
			if (other.numeroCelularPersonal != null)
				return false;
		} else if (!numeroCelularPersonal.equals(other.numeroCelularPersonal))
			return false;
		if (numeroDocumentoIdentidad == null) {
			if (other.numeroDocumentoIdentidad != null)
				return false;
		} else if (!numeroDocumentoIdentidad
				.equals(other.numeroDocumentoIdentidad))
			return false;
		if (numeroTelefonoCasa == null) {
			if (other.numeroTelefonoCasa != null)
				return false;
		} else if (!numeroTelefonoCasa.equals(other.numeroTelefonoCasa))
			return false;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (oidPadre == null) {
			if (other.oidPadre != null)
				return false;
		} else if (!oidPadre.equals(other.oidPadre))
			return false;
		if (oidTipoDocumentoIdentidad == null) {
			if (other.oidTipoDocumentoIdentidad != null)
				return false;
		} else if (!oidTipoDocumentoIdentidad
				.equals(other.oidTipoDocumentoIdentidad))
			return false;
		if (primerNombre == null) {
			if (other.primerNombre != null)
				return false;
		} else if (!primerNombre.equals(other.primerNombre))
			return false;
		if (segundoNombre == null) {
			if (other.segundoNombre != null)
				return false;
		} else if (!segundoNombre.equals(other.segundoNombre))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HistoricoDirectorioVenta [apellidoMaterno=" + apellidoMaterno
				+ ", apellidoPaterno=" + apellidoPaterno + ", codigoBelcorp="
				+ codigoBelcorp + ", codigoCliente=" + codigoCliente
				+ ", codigoPais=" + codigoPais + ", codigoRegion="
				+ codigoRegion + ", codigoZona=" + codigoZona
				+ ", direccionDomicilio=" + direccionDomicilio + ", email="
				+ email + ", fechaNacimiento=" + fechaNacimiento
				+ ", numeroCelularEmpresa=" + numeroCelularEmpresa
				+ ", numeroCelularPersonal=" + numeroCelularPersonal
				+ ", numeroDocumentoIdentidad=" + numeroDocumentoIdentidad
				+ ", numeroTelefonoCasa=" + numeroTelefonoCasa + ", oid=" + oid
				+ ", oidPadre=" + oidPadre + ", oidTipoDocumentoIdentidad="
				+ oidTipoDocumentoIdentidad + ", primerNombre=" + primerNombre
				+ ", segundoNombre=" + segundoNombre + "]";
	}

}
