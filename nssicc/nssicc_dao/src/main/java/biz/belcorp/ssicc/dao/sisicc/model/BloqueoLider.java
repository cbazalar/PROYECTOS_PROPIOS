/*
 * Created on 17-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BloqueoLider.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class BloqueoLider extends AuditableBaseObject implements Comparable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2260178266003595869L;

	private String codigo;	
    
    private String nombreApellidos;  
    
    private String codigoPais;
	
    private String periodoInicial;
    
    private String periodoFinal;
    
    private String estado;
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigoPais() {
        return codigoPais;
    }
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getPeriodoFinal() {
        return periodoFinal;
    }
    public void setPeriodoFinal(String periodoFinal) {
        this.periodoFinal = periodoFinal;
    }
    public String getPeriodoInicial() {
        return periodoInicial;
    }
    public void setPeriodoInicial(String periodoInicial) {
        this.periodoInicial = periodoInicial;
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BloqueoLider myClass = (BloqueoLider) object;
        return new CompareToBuilder().append(this.periodoFinal,
                myClass.periodoFinal).append(this.codigoPais,
                myClass.codigoPais).append(this.periodoInicial,
                myClass.periodoInicial).append(this.auditInfo,
                myClass.auditInfo).append(this.estado, myClass.estado).append(
                this.codigo, myClass.codigo).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BloqueoLider)) {
            return false;
        }
        BloqueoLider rhs = (BloqueoLider) object;
        return new EqualsBuilder().append(this.periodoFinal, rhs.periodoFinal)
                .append(this.codigoPais, rhs.codigoPais).append(
                        this.periodoInicial, rhs.periodoInicial).append(
                        this.auditInfo, rhs.auditInfo).append(this.estado,
                        rhs.estado).append(this.codigo, rhs.codigo).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1548061109, -357206569).append(
                this.periodoFinal).append(this.codigoPais).append(
                this.periodoInicial).append(this.auditInfo).append(this.estado)
                .append(this.codigo).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoPais", this.codigoPais)
                .append("periodoFinal", this.periodoFinal).append("estado",
                        this.estado).append("auditInfo", this.auditInfo)
                .append("periodoInicial", this.periodoInicial).append("codigo",
                        this.codigo).toString();
    }
    public String getNombreApellidos() {
        return nombreApellidos;
    }
    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }	

    
    
}