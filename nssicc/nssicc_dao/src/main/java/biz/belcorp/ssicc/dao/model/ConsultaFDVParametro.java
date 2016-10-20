package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * <p>
 * <a href="ConsultaFDVParametro.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:fayala@belcorp.biz">Frank Ayala</a>
 */

public class ConsultaFDVParametro extends AuditableBaseObject implements Serializable{

	private static final long serialVersionUID = -516324296224116527L;
	private String codPara;
	private String desPara;
	private String valPara;
	private String gruPara;
	
	public String getCodPara() {
		return codPara;
	}

	public void setCodPara(String codPara) {
		this.codPara = codPara;
	}

	public String getDesPara() {
		return desPara;
	}

	public void setDesPara(String desPara) {
		this.desPara = desPara;
	}

	public String getValPara() {
		return valPara;
	}

	public void setValPara(String valPara) {
		this.valPara = valPara;
	}
	
	public String getGruPara() {
		return gruPara;
	}

	public void setGruPara(String gruPara) {
		this.gruPara = gruPara;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ConsultaFDVParametro)) {
            return false;
        }
		ConsultaFDVParametro rhs = (ConsultaFDVParametro) object;
        return new EqualsBuilder().append(this.codPara, rhs.codPara).append(this.desPara, rhs.desPara)
        		.append(this.valPara, rhs.valPara).append(this.gruPara, rhs.gruPara)
        		.append(this.auditInfo, rhs.auditInfo).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(57526443, 1334634051).append(this.codPara).append(this.desPara)
		.append(this.valPara).append(this.gruPara).append(this.auditInfo).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this)
		.append("codigo del parametro", this.codPara).append("descripcion del parametro", this.desPara)
		.append("valor del parametro", this.valPara).append("grupo del parametro", this.gruPara)
		.append("auditInfo", this.auditInfo).toString();
	}
}
