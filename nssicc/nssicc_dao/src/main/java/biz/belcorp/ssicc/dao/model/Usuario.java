/*
 * Created on 08/11/2005 04:53:43 PM biz.belcorp.ssicc.model.Usuario
 */
package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;
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

    @NotNull
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    private String login;

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private String nombres;

    @NotNull
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    private String apellidos;

    @NotNull
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    private String clave;

    @NotNull
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    private String confirmacionClave;

    @NotNull
    public String getConfirmacionClave() {
        return confirmacionClave;
    }

    public void setConfirmacionClave(String confirmacionClave) {
        this.confirmacionClave = confirmacionClave;
    }

    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private Pais pais = new Pais();

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    private String codigoPais;

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    private String codigoIdioma;

    public String getCodigoIdioma() {
        return codigoIdioma;
    }

    public void setCodigoIdioma(String codigoLenguaje) {
        this.codigoIdioma = codigoLenguaje;
    }

    private String correoElectronico;

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @uml.property name="codigoUnicoBelcorp" multiplicity="(0 1)"
     */
    private String codigoUnicoBelcorp;
    
    /**
     * @uml.property name="codigoUnicoBelcorp"
     * @struts.form-field
     */
    public String getCodigoUnicoBelcorp() {
        return codigoUnicoBelcorp;
    }

    /**
     * @uml.property name="codigoUnicoBelcorp"
     */
    public void setCodigoUnicoBelcorp(String codigoUnicoBelcorp) {
        this.codigoUnicoBelcorp = codigoUnicoBelcorp;
    }
    
    /**
     * @uml.property name="codigoUnicoBelcorp" multiplicity="(0 1)"
     */
    private String centroCosto;
    
    /**
     * @uml.property name="centroCosto"
     * @struts.form-field
     */
    public String getCentroCosto() {
        return centroCosto;
    }

    /**
     * @uml.property name="centroCosto"
     */
    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    /**
     * @uml.property name="idioma"
     * @uml.associationEnd inverse="user:biz.belcorp.ssicc.model.Pais"
     *                     multiplicity= "(0 1)"
     */
    private Idioma idioma = new Idioma();

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    private List perfiles;

    public List getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List perfiles) {
        this.perfiles = perfiles;
    }

    private String ipMaquinaRemota;
    
	private String lastLogin;
	
	private String indicadorUsuarioSistema;
    private String nroRCR;
    private String nroRCRBloqueo;
    private String nroRCRDesbloqueo;
    private String nroRCREliminacion;
    
    /**
	 * @return the lastLogin
	 */
	public String getLastLogin() {
		return lastLogin;
	}
    
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
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
	
	private Date ultimaModifiacionClave;
	
	private String intentosFallidosClave;

	/**
	 * @return the ultimaModifiacionClave
	 */
	public Date getUltimaModifiacionClave() {
		return ultimaModifiacionClave;
	}

	/**
	 * @param ultimaModifiacionClave the ultimaModifiacionClave to set
	 */
	public void setUltimaModifiacionClave(Date ultimaModifiacionClave) {
		this.ultimaModifiacionClave = ultimaModifiacionClave;
	}

	/**
	 * @return the intentosFallidosClave
	 */
	public String getIntentosFallidosClave() {
		return intentosFallidosClave;
	}

	/**
	 * @param intentosFallidosClave the intentosFallidosClave to set
	 */
	public void setIntentosFallidosClave(String intentosFallidosClave) {
		this.intentosFallidosClave = intentosFallidosClave;
	}

	
	
	/**
	 * @return the indicadorUsuarioSistema
	 */
	public String getIndicadorUsuarioSistema() {
		return indicadorUsuarioSistema;
	}

	/**
	 * @param indicadorUsuarioSistema the indicadorUsuarioSistema to set
	 */
	public void setIndicadorUsuarioSistema(String indicadorUsuarioSistema) {
		this.indicadorUsuarioSistema = indicadorUsuarioSistema;
	}

	/**
	 * @return the nroRCR
	 */
	public String getNroRCR() {
		return nroRCR;
	}

	/**
	 * @param nroRCR the nroRCR to set
	 */
	public void setNroRCR(String nroRCR) {
		this.nroRCR = nroRCR;
	}

	/**
	 * @return the nroRCRBloqueo
	 */
	public String getNroRCRBloqueo() {
		return nroRCRBloqueo;
	}

	/**
	 * @param nroRCRBloqueo the nroRCRBloqueo to set
	 */
	public void setNroRCRBloqueo(String nroRCRBloqueo) {
		this.nroRCRBloqueo = nroRCRBloqueo;
	}

	/**
	 * @return the nroRCRDesbloqueo
	 */
	public String getNroRCRDesbloqueo() {
		return nroRCRDesbloqueo;
	}

	/**
	 * @param nroRCRDesbloqueo the nroRCRDesbloqueo to set
	 */
	public void setNroRCRDesbloqueo(String nroRCRDesbloqueo) {
		this.nroRCRDesbloqueo = nroRCRDesbloqueo;
	}

	/**
	 * @return the nroRCREliminacion
	 */
	public String getNroRCREliminacion() {
		return nroRCREliminacion;
	}

	/**
	 * @param nroRCREliminacion the nroRCREliminacion to set
	 */
	public void setNroRCREliminacion(String nroRCREliminacion) {
		this.nroRCREliminacion = nroRCREliminacion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", login=" + login + ", nombres="
				+ nombres + ", apellidos=" + apellidos + ", clave=" + clave
				+ ", confirmacionClave=" + confirmacionClave + ", estado="
				+ estado + ", pais=" + pais + ", codigoPais=" + codigoPais
				+ ", codigoIdioma=" + codigoIdioma + ", correoElectronico="
				+ correoElectronico + ", codigoUnicoBelcorp="
				+ codigoUnicoBelcorp + ", centroCosto=" + centroCosto
				+ ", idioma=" + idioma + ", perfiles=" + perfiles
				+ ", ipMaquinaRemota=" + ipMaquinaRemota + ", lastLogin="
				+ lastLogin + ", ultimaModifiacionClave="
				+ ultimaModifiacionClave + ", intentosFallidosClave="
				+ intentosFallidosClave + "]";
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
		result = prime * result
				+ ((centroCosto == null) ? 0 : centroCosto.hashCode());
		result = prime * result + ((clave == null) ? 0 : clave.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((codigoIdioma == null) ? 0 : codigoIdioma.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime
				* result
				+ ((codigoUnicoBelcorp == null) ? 0 : codigoUnicoBelcorp
						.hashCode());
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
		result = prime
				* result
				+ ((intentosFallidosClave == null) ? 0 : intentosFallidosClave
						.hashCode());
		result = prime * result
				+ ((ipMaquinaRemota == null) ? 0 : ipMaquinaRemota.hashCode());
		result = prime * result
				+ ((lastLogin == null) ? 0 : lastLogin.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nombres == null) ? 0 : nombres.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result
				+ ((perfiles == null) ? 0 : perfiles.hashCode());
		result = prime
				* result
				+ ((ultimaModifiacionClave == null) ? 0
						: ultimaModifiacionClave.hashCode());
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
		if (centroCosto == null) {
			if (other.centroCosto != null)
				return false;
		} else if (!centroCosto.equals(other.centroCosto))
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
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoUnicoBelcorp == null) {
			if (other.codigoUnicoBelcorp != null)
				return false;
		} else if (!codigoUnicoBelcorp.equals(other.codigoUnicoBelcorp))
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
		if (intentosFallidosClave == null) {
			if (other.intentosFallidosClave != null)
				return false;
		} else if (!intentosFallidosClave.equals(other.intentosFallidosClave))
			return false;
		if (ipMaquinaRemota == null) {
			if (other.ipMaquinaRemota != null)
				return false;
		} else if (!ipMaquinaRemota.equals(other.ipMaquinaRemota))
			return false;
		if (lastLogin == null) {
			if (other.lastLogin != null)
				return false;
		} else if (!lastLogin.equals(other.lastLogin))
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
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (perfiles == null) {
			if (other.perfiles != null)
				return false;
		} else if (!perfiles.equals(other.perfiles))
			return false;
		if (ultimaModifiacionClave == null) {
			if (other.ultimaModifiacionClave != null)
				return false;
		} else if (!ultimaModifiacionClave.equals(other.ultimaModifiacionClave))
			return false;
		return true;
	}
	
	
	
	
	
	
}