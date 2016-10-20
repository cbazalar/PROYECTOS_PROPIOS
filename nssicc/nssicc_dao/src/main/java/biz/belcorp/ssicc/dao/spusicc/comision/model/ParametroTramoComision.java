package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextcbazalar
 *
 */
public class ParametroTramoComision extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4465015849773978859L;

	private String numeroDiasComision;
	
	private String porcentajeRecuperacion;
	
	private String porcentajeComision;
	
	public ParametroTramoComision() {
		this.numeroDiasComision = " ";
		this.porcentajeRecuperacion = " ";
		this.porcentajeComision = " ";
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the numeroDiasComision.
	 */
	public String getNumeroDiasComision() {
		return numeroDiasComision;
	}

	/**
	 * @param numeroDiasComision The numeroDiasComision to set.
	 */
	public void setNumeroDiasComision(String numeroDiasComision) {
		this.numeroDiasComision = numeroDiasComision;
	}

	/**
	 * @return Returns the porcentajeComision.
	 */
	public String getPorcentajeComision() {
		return porcentajeComision;
	}

	/**
	 * @param porcentajeComision The porcentajeComision to set.
	 */
	public void setPorcentajeComision(String porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
	}

	/**
	 * @return Returns the porcentajeRecuperacion.
	 */
	public String getPorcentajeRecuperacion() {
		return porcentajeRecuperacion;
	}

	/**
	 * @param porcentajeRecuperacion The porcentajeRecuperacion to set.
	 */
	public void setPorcentajeRecuperacion(String porcentajeRecuperacion) {
		this.porcentajeRecuperacion = porcentajeRecuperacion;
	}
	
	


	
}
