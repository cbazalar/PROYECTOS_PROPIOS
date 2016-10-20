package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class CarParamCarte extends AuditableBaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oidParaCart;
	private String paisOidPais;		
	private String niriOidNiveRies;
	private String desNivelRiesgo;
	private String valMontMaxiPerm;
	private String indMontMaxi;
	
	private boolean selected;

	public String getOidParaCart() {
		return oidParaCart;
	}

	public void setOidParaCart(String oidParaCart) {
		this.oidParaCart = oidParaCart;
	}

	public String getPaisOidPais() {
		return paisOidPais;
	}

	public void setPaisOidPais(String paisOidPais) {
		this.paisOidPais = paisOidPais;
	}

	public String getNiriOidNiveRies() {
		return niriOidNiveRies;
	}

	public void setNiriOidNiveRies(String niriOidNiveRies) {
		this.niriOidNiveRies = niriOidNiveRies;
	}

	public String getValMontMaxiPerm() {
		return valMontMaxiPerm;
	}

	public void setValMontMaxiPerm(String valMontMaxiPerm) {
		this.valMontMaxiPerm = valMontMaxiPerm;
	}

	public String getIndMontMaxi() {
		return indMontMaxi;
	}

	public void setIndMontMaxi(String indMontMaxi) {
		this.indMontMaxi = indMontMaxi;
	}

	public String getDesNivelRiesgo() {
		return desNivelRiesgo;
	}

	public void setDesNivelRiesgo(String desNivelRiesgo) {
		this.desNivelRiesgo = desNivelRiesgo;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
