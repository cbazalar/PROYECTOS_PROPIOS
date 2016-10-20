/*
 * Created on 12-ago-08 12:02:59
 * biz.belcorp.ssicc.scdf.model.ClienteRechazado
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ClienteRechazado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public class ClienteRechazado extends AuditableBaseObject implements
        Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8269887522420570736L;

	private String codigoPais;

    private String codigoConsultora;

    private String nombreCompleto;

    private String numeroDocumentoIdentidad;

    private String numeroTelefono;

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
     * @return the codigoConsultora
     */
    public String getCodigoConsultora() {
        return codigoConsultora;
    }

    /**
     * @param codigoConsultora the codigoConsultora to set
     */
    public void setCodigoConsultora(String codigoConsultora) {
        this.codigoConsultora = codigoConsultora;
    }

    /**
     * @return the nombreCompleto
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * @param nombreCompleto the nombreCompleto to set
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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
     * @return the numeroTelefono
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * @param numeroTelefono the numeroTelefono to set
     */
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
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
