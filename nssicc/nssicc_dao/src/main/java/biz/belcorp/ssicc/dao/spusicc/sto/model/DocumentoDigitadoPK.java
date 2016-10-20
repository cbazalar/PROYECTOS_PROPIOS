package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

public class DocumentoDigitadoPK  implements Comparable,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8207590211632455676L;
	private String codPais;
	private String codTipoDocu;
	private String numLote;
	private String secNumeDocu;


	/**
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * @param codPais
	 *            the codPais to set
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

	/**
	 * @return the numLote
	 */
	public String getNumLote() {
		return numLote;
	}

	/**
	 * @param numLote
	 *            the numLote to set
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}

	/**
	 * @return the secNumeDocu
	 */
	public String getSecNumeDocu() {
		return secNumeDocu;
	}

	/**
	 * @param secNumeDocu
	 *            the secNumeDocu to set
	 */
	public void setSecNumeDocu(String secNumeDocu) {
		this.secNumeDocu = secNumeDocu;
	}
	
	
	
	/**
	 * @param o
	 * @return
	 */
	public int compareTo(Object o) {
		DocumentoDigitadoPK bean = (DocumentoDigitadoPK) o;
		
        String pk1 = "" + this.codPais+this.codTipoDocu+this.numLote+this.secNumeDocu;
        String pk2 = bean.getCodPais()+bean.getCodTipoDocu()+bean.getNumLote()+bean.getSecNumeDocu();
        return pk1.compareTo(pk2);
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPais == null) ? 0 : codPais.hashCode());
		result = prime * result
				+ ((codTipoDocu == null) ? 0 : codTipoDocu.hashCode());
		result = prime * result + ((numLote == null) ? 0 : numLote.hashCode());
		result = prime * result
				+ ((secNumeDocu == null) ? 0 : secNumeDocu.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoDigitadoPK other = (DocumentoDigitadoPK) obj;
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
		if (numLote == null) {
			if (other.numLote != null)
				return false;
		} else if (!numLote.equals(other.numLote))
			return false;
		if (secNumeDocu == null) {
			if (other.secNumeDocu != null)
				return false;
		} else if (!secNumeDocu.equals(other.secNumeDocu))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DocumentoDigitadoPK [codPais=" + codPais + ", codTipoDocu="
				+ codTipoDocu + ", numLote=" + numLote + ", secNumeDocu="
				+ secNumeDocu + "]";
	}
}
