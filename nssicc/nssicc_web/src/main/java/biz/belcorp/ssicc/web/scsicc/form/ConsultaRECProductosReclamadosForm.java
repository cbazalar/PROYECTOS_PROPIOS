package biz.belcorp.ssicc.web.scsicc.form;


import java.io.Serializable;

import biz.belcorp.ssicc.web.sisicc.form.InterfazRECProductosReclamadosForm;


public class ConsultaRECProductosReclamadosForm extends InterfazRECProductosReclamadosForm
    implements Serializable {

	private String docRef;
    private String cliente;
    private String codSap;
    private String codVenta;
    private String campRef;
    private String descProd;
    private String unidades;
    private String indicadorSap;
    
	public String getCampRef() {
		return campRef;
		
	}
	
	/**
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	
	public void setCampRef(String campRef) {
		this.campRef = campRef;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCodSap() {
		return codSap;
	}
	public void setCodSap(String codSap) {
		this.codSap = codSap;
	}
	public String getCodVenta() {
		return codVenta;
	}
	public void setCodVenta(String codVenta) {
		this.codVenta = codVenta;
	}
	public String getDescProd() {
		return descProd;
	}
	public void setDescProd(String descProd) {
		this.descProd = descProd;
	}
	public String getDocRef() {
		return docRef;
	}
	public void setDocRef(String docRef) {
		this.docRef = docRef;
	}
	public String getUnidades() {
		return unidades;
	}
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}

	public String getIndicadorSap() {
		return indicadorSap;
	}

	public void setIndicadorSap(String indicadorSap) {
		this.indicadorSap = indicadorSap;
	}
    
}
