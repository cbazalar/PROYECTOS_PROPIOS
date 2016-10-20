package biz.belcorp.ssicc.dao.spusicc.ventas.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;
/**
 * Bean  que guarda  informacion de la entidad VEN_SUBPR_RECAU
 * @author peextjnunez
 *
 */
public class SubProcesoRecaudo extends AuditableBaseObject implements Serializable{
	
	private Proceso proceso;/** Proceso*/
	private SubProceso subProceso;/**Sub Proceso*/
	
	
	public boolean equals(Object object) {
        if (!(object instanceof SubProcesoRecaudo)) {
            return false;
        }
        SubProcesoRecaudo rhs = (SubProcesoRecaudo) object;
        return new EqualsBuilder().append(this.proceso, rhs.proceso)
                .append(this.subProceso, rhs.subProceso).isEquals();
  }
 
   public String toString() {
	        return new ToStringBuilder(this)
	                .append("proceso", this.proceso).append("subProceso",
	                        this.subProceso).toString();
   }
 
   public int hashCode() {
	        return new HashCodeBuilder(-504457923, 1884526667).append(
	                this.proceso).append(this.subProceso).toHashCode();
   }

/**
 * @return Returns the proceso.
 */
public Proceso getProceso() {
	return proceso;
}

/**
 * @param proceso The proceso to set.
 */
public void setProceso(Proceso proceso) {
	this.proceso = proceso;
}

/**
 * @return Returns the subProceso.
 */
public SubProceso getSubProceso() {
	return subProceso;
}

/**
 * @param subProceso The subProceso to set.
 */
public void setSubProceso(SubProceso subProceso) {
	this.subProceso = subProceso;
}

}
