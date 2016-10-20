package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author <a href="kgomez@sigcomt.com">Karina Gomez</a>
 *
 */

public class TipoDocumento extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String codigoPais;
	private String nombreEntidad;
	private String oidTipoDoc;
	private String codigo;
	private String estado;
	private String descripcion;
	private String obligatorio;
	private String siglas;
	private String longitud;
	private String dni;
	private String fiscal;
	private String tipoDocu;
	
	
	
	

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
	 * @return the nombreEntidad
	 */
	public String getNombreEntidad() {
		return nombreEntidad;
	}

	/**
	 * @param nombreEntidad the nombreEntidad to set
	 */
	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	/**
	 * @return the oidTipoDoc
	 */
	public String getOidTipoDoc() {
		return oidTipoDoc;
	}

	/**
	 * @param oidTipoDoc the oidTipoDoc to set
	 */
	public void setOidTipoDoc(String oidTipoDoc) {
		this.oidTipoDoc = oidTipoDoc;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the obligatorio
	 */
	public String getObligatorio() {
		return obligatorio;
	}

	/**
	 * @param obligatorio the obligatorio to set
	 */
	public void setObligatorio(String obligatorio) {
		this.obligatorio = obligatorio;
	}

	/**
	 * @return the siglas
	 */
	public String getSiglas() {
		return siglas;
	}

	/**
	 * @param siglas the siglas to set
	 */
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	/**
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * @return the fiscal
	 */
	public String getFiscal() {
		return fiscal;
	}

	/**
	 * @param fiscal the fiscal to set
	 */
	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}

	/**
	 * @return the tipoDocu
	 */
	public String getTipoDocu() {
		return tipoDocu;
	}

	/**
	 * @param tipoDocu the tipoDocu to set
	 */
	public void setTipoDocu(String tipoDocu) {
		this.tipoDocu = tipoDocu;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TipoDocumento [codigoPais=" + codigoPais + ", codigo=" + codigo
				+ ", nombreEntidad=" + nombreEntidad
				+ ", oidTipoDoc=" + oidTipoDoc 
				+ ", estado=" + estado
				+ ", obligatorio=" + obligatorio
				+ ", siglas=" + siglas
				+ ", longitud=" + longitud
				+ ", dni=" + dni
				+ ", fiscal=" + fiscal
				+ ", tipoDocu=" + tipoDocu
				+ ", descripcion=" + descripcion  + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoDocumento other = (TipoDocumento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (oidTipoDoc == null) {
			if (other.oidTipoDoc != null)
				return false;
		} else if (!oidTipoDoc.equals(other.oidTipoDoc))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (obligatorio == null) {
			if (other.obligatorio != null)
				return false;
		} else if (!obligatorio.equals(other.obligatorio))
			return false;
		if (siglas == null) {
			if (other.siglas != null)
				return false;
		} else if (!siglas.equals(other.siglas))
			return false;
		if (longitud  == null) {
			if (other.longitud != null)
				return false;
		} else if (!longitud.equals(other.longitud))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (fiscal  == null) {
			if (other.fiscal != null)
				return false;
		} else if (!fiscal.equals(other.fiscal))
			return false;
		if (tipoDocu == null) {
			if (other.tipoDocu != null)
				return false;
		} else if (!tipoDocu.equals(other.tipoDocu))
			return false;
		return true;

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub

		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((nombreEntidad == null) ? 0
						: nombreEntidad.hashCode());
		result = prime * result
				+ ((oidTipoDoc == null) ? 0 : oidTipoDoc.hashCode());
		result = prime * result
				+ ((estado  == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((descripcion  == null) ? 0 : descripcion.hashCode());
		result = prime * result
				+ ((obligatorio  == null) ? 0 : obligatorio.hashCode());
		result = prime * result
				+ ((siglas  == null) ? 0 : siglas.hashCode());
		result = prime * result
				+ ((longitud  == null) ? 0 : longitud.hashCode());
		result = prime * result
				+ ((dni  == null) ? 0 : dni.hashCode());
		result = prime * result
				+ ((fiscal  == null) ? 0 : fiscal.hashCode());
		result = prime * result
				+ ((tipoDocu  == null) ? 0 : tipoDocu.hashCode());
		
		return result;

	}

}
