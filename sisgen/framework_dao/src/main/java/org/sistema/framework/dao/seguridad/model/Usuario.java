/*
 * Created on 08/11/2005 04:53:43 PM biz.belcorp.ssicc.model.Usuario
 */
package org.sistema.framework.dao.seguridad.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import org.sistema.framework.dao.model.AuditableBaseObject;
/**
 * TODO Include class description here.
 * <p>
 * <a href="Usuario.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class Usuario extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -797439287449959511L;
	private String codigo;
	private String login;
	private String nombres;
	private String apellidos;
	private String clave;
	private String confirmacionClave;
	private String estado;
	private String codigoIdioma;
	private String correoElectronico;
	private String ipMaquinaRemota;
	private Idioma idioma;

	
	
	

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", login=" + login + ", nombres="
				+ nombres + ", apellidos=" + apellidos + ", clave=" + clave
				+ ", confirmacionClave=" + confirmacionClave + ", estado="
				+ estado + ", codigoIdioma=" + codigoIdioma
				+ ", correoElectronico=" + correoElectronico
				+ ", ipMaquinaRemota=" + ipMaquinaRemota + ", idioma=" + idioma
				+ "]";
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoIdioma == null) ? 0 : codigoIdioma.hashCode());
		result = prime
				* result
				+ ((confirmacionClave == null) ? 0 : confirmacionClave
						.hashCode());
		result = prime
				* result
				+ ((correoElectronico == null) ? 0 : correoElectronico
						.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
		result = prime * result
				+ ((ipMaquinaRemota == null) ? 0 : ipMaquinaRemota.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		return result;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (clave == null) {
			if (other.clave != null)
				return false;
		} else if (!clave.equals(other.clave))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (codigoIdioma == null) {
			if (other.codigoIdioma != null)
				return false;
		} else if (!codigoIdioma.equals(other.codigoIdioma))
			return false;
		if (confirmacionClave == null) {
			if (other.confirmacionClave != null)
				return false;
		} else if (!confirmacionClave.equals(other.confirmacionClave))
			return false;
		if (correoElectronico == null) {
			if (other.correoElectronico != null)
				return false;
		} else if (!correoElectronico.equals(other.correoElectronico))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!idioma.equals(other.idioma))
			return false;
		if (ipMaquinaRemota == null) {
			if (other.ipMaquinaRemota != null)
				return false;
		} else if (!ipMaquinaRemota.equals(other.ipMaquinaRemota))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nombres == null) {
			if (other.nombres != null)
				return false;
		} else if (!nombres.equals(other.nombres))
			return false;
		return true;
	}




	@NotNull
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

   

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
    @NotNull
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    

    @NotNull
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    

    @NotNull
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    

    @NotNull
    public String getConfirmacionClave() {
        return confirmacionClave;
    }

    public void setConfirmacionClave(String confirmacionClave) {
        this.confirmacionClave = confirmacionClave;
    }

    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    

    

    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    public void setCodigoIdioma(String codigoLenguaje) {
        this.codigoIdioma = codigoLenguaje;
    }

    

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    

    
	/**
	 * @return the ipMaquinaRemota
	 */
	public String getIpMaquinaRemota() {
		return ipMaquinaRemota;
	}

	/**
	 * @param ipMaquinaRemota the ipMaquinaRemota to set
	 */
	public void setIpMaquinaRemota(String ipMaquinaRemota) {
		this.ipMaquinaRemota = ipMaquinaRemota;
	}
	
	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
	public Idioma getIdioma() {
		return idioma;
	}
	
}