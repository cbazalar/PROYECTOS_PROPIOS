/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sto.model;

/**
 * @author Jose Cairampoma
 * 
 */
public class AccionTipoDocumento {

	private String codPais;
	private String codTipoDocu;
	private String codAcciTipoDocu;
	
	public AccionTipoDocumento(String codPais, String codTipoDocu,String codAcciTipoDocu) {
		super();
		this.codPais = codPais;
		this.codTipoDocu = codTipoDocu;
		this.codAcciTipoDocu = codAcciTipoDocu;
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



	/**
	 * @return the codAcciTipoDocu
	 */
	public String getCodAcciTipoDocu() {
		return codAcciTipoDocu;
	}



	/**
	 * @param codAcciTipoDocu the codAcciTipoDocu to set
	 */
	public void setCodAcciTipoDocu(String codAcciTipoDocu) {
		this.codAcciTipoDocu = codAcciTipoDocu;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "AccionTipoDocumento [codPais=" + codPais + ", codTipoDocu="
				+ codTipoDocu + ", codAcciTipoDocu=" + codAcciTipoDocu + "]";
	}
	
	

}
