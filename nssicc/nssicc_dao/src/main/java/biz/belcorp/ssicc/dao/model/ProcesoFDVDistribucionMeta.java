package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProcesoFDVDistribucionMeta implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codProc;
	private String codZona;
	private String codPara;
	private String gruPara;
	private String desPara;
	private String camCase;
	private BigDecimal valPara;
	private Long nroScco;
	private Long nroSeco;
	private String uniPara;
	
	public ProcesoFDVDistribucionMeta() {}

	public String getCodProc() {
		return codProc;
	}

	public void setCodProc(String codProc) {
		this.codProc = codProc;
	}

	public String getCodZona() {
		return codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public String getGruPara() {
		return gruPara;
	}

	public void setGruPara(String gruPara) {
		this.gruPara = gruPara;
	}

	public String getDesPara() {
		return desPara;
	}

	public void setDesPara(String desPara) {
		this.desPara = desPara;
	}

	public String getCamCase() {
		return camCase;
	}

	public void setCamCase(String camCase) {
		this.camCase = camCase;
	}

	public BigDecimal getValPara() {
		return valPara;
	}

	public void setValPara(BigDecimal valPara) {
		this.valPara = valPara;
	}

	public Long getNroScco() {
		return nroScco;
	}

	public void setNroScco(Long nroScco) {
		this.nroScco = nroScco;
	}

	public Long getNroSeco() {
		return nroSeco;
	}

	public void setNroSeco(Long nroSeco) {
		this.nroSeco = nroSeco;
	}

	public String getCodPara() {
		return codPara;
	}

	public void setCodPara(String codPara) {
		this.codPara = codPara;
	}

	/**
	 * @return the uniPara
	 */
	public String getUniPara() {
		return uniPara;
	}

	/**
	 * @param uniPara the uniPara to set
	 */
	public void setUniPara(String uniPara) {
		this.uniPara = uniPara;
	}
}
