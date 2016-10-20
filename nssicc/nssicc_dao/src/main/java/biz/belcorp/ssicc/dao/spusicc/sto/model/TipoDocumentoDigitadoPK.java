package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;
/**
 * Representa a la llave primaria de la clase Interfaz.
 * <p>
 * <a href="TipoDocumentoDigitadoPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
public class TipoDocumentoDigitadoPK extends BaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6367367154400817038L;

	private String codPais;

	private String codTipoDocu;

	public TipoDocumentoDigitadoPK(String codPais, String codTipoDocu) {

		this.codPais = codPais;
		this.codTipoDocu = codTipoDocu;
	}

	/**
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * @param codPais the codPais to set
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	/**
	 * @return the codTipoDocu
	 */
	public String getCodTipoDocu() {
		return codTipoDocu;
	}

	/**
	 * @param codTipoDocu the codTipoDocu to set
	 */
	public void setCodTipoDocu(String codTipoDocu) {
		this.codTipoDocu = codTipoDocu;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;		
		if (getClass() != obj.getClass())
			return false;
		TipoDocumentoDigitadoPK other = (TipoDocumentoDigitadoPK) obj;
		if (codPais == null) {
			if (other.codPais != null)
				return false;
		} else if (!codPais.equals(other.codPais))
			return false;
		if (codTipoDocu == null) {
			if (other.codTipoDocu != null)
				return false;
		} else if (!codTipoDocu.equals(other.codTipoDocu))
			return false;
		return true;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ((codPais == null) ? 0 : codPais.hashCode());
		result = prime * result
				+ ((codTipoDocu == null) ? 0 : codTipoDocu.hashCode());
		return result;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
		.append("codPais", this.codPais)
		.append("codTipoDocu", this.codTipoDocu).toString();
	}

	
	
}
