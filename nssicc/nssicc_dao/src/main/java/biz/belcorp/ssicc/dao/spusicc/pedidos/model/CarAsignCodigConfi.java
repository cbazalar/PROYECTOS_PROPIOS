package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class CarAsignCodigConfi extends AuditableBaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oidAsigCodiConf;
	private String zorgOidRegi;
	private String zzonOidZona;
	private String niriOidNiveRies;
	private String pacaOidParaCart;

	public String getOidAsigCodiConf() {
		return oidAsigCodiConf;
	}

	public void setOidAsigCodiConf(String oidAsigCodiConf) {
		this.oidAsigCodiConf = oidAsigCodiConf;
	}

	public String getZorgOidRegi() {
		return zorgOidRegi;
	}

	public void setZorgOidRegi(String zorgOidRegi) {
		this.zorgOidRegi = zorgOidRegi;
	}

	public String getZzonOidZona() {
		return zzonOidZona;
	}

	public void setZzonOidZona(String zzonOidZona) {
		this.zzonOidZona = zzonOidZona;
	}

	public String getNiriOidNiveRies() {
		return niriOidNiveRies;
	}

	public void setNiriOidNiveRies(String niriOidNiveRies) {
		this.niriOidNiveRies = niriOidNiveRies;
	}

	public String getPacaOidParaCart() {
		return pacaOidParaCart;
	}

	public void setPacaOidParaCart(String pacaOidParaCart) {
		this.pacaOidParaCart = pacaOidParaCart;
	}

	public String getZsccOidSecc() {
		return zsccOidSecc;
	}

	public void setZsccOidSecc(String zsccOidSecc) {
		this.zsccOidSecc = zsccOidSecc;
	}

	private String zsccOidSecc;

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
