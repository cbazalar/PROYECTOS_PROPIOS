package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

/**
 *  @author <a href="mailto:">Efrain Fernandez</a>
 */

public class ClasificacionConsultora implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String desClas;
	private String desTipoClas;
	private String codTipoCliente;
	private String codSubTipoCliente;
	private String codTipoClas;
	private String codClas;
	private String indOrde;
	public String getDesClas() {
		return desClas;
	}
	/**
	 * @param desClas
	 */
	public void setDesClas(String desClas) {
		this.desClas = desClas;
	}
	/**
	 * @return
	 */
	public String getDesTipoClas() {
		return desTipoClas;
	}
	/**
	 * @param desTipoClas
	 */
	public void setDesTipoClas(String desTipoClas) {
		this.desTipoClas = desTipoClas;
	}

	public String getCodTipoCliente() {
		return codTipoCliente;
	}

	public void setCodTipoCliente(String codTipoCliente) {
		this.codTipoCliente = codTipoCliente;
	}
	/**
	 * @return
	 */
	public String getCodSubTipoCliente() {
		return codSubTipoCliente;
	}
	/**
	 * @param codSubTipoCliente
	 */
	public void setCodSubTipoCliente(String codSubTipoCliente) {
		this.codSubTipoCliente = codSubTipoCliente;
	}
	/**
	 * @return
	 */
	public String getCodTipoClas() {
		return codTipoClas;
	}
	/**
	 * @param codTipoClas
	 */
	public void setCodTipoClas(String codTipoClas) {
		this.codTipoClas = codTipoClas;
	}
	/**
	 * @return
	 */
	public String getCodClas() {
		return codClas;
	}
	/**
	 * @param codClas
	 */
	public void setCodClas(String codClas) {
		this.codClas = codClas;
	}
	/**
	 * @return
	 */
	public String getIndOrde() {
		return indOrde;
	}
	/**
	 * @param indOrde
	 */
	public void setIndOrde(String indOrde) {
		this.indOrde = indOrde;
	}


	
	
}
