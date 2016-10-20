package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class BonoNivel extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPrograma;
	private String tipoBono;
	private String codTipoBono;
	private String bonoNivel;
	private String codBonoNivel;
	private String nivel;
	private String codNivel;
	private String secuenciaBonoNivel;
	private String campanaLanzamiento;
	private String nroLanzamiento;
	private String valorPorcObj;
	private String valorPorSobreCum;
	private String nroMinIngr;
	private String nroMaxIngr;
	private String porReten;
	private String codTipoPrem;
	private String tipoPrem;
	private String monPrem;
	private String exigencia;
    private String sobExigencia;
    private String nroMinIng22;
    private String porRetExig22;
    private String nroMinIng33;
    private String porRetExig33;
    private String nroMinIng44;
    private String porRetExig44;
    private String nroCampMax;
    private String nroMinIncPed;
    private String codTipoMedi;
    private String tipoMedi;
	private String indActi;
	private String correlativo;
	private String accion;
	
	private String nroMinIng;
	private String nroMaxIng;
    private String porRetExig;
    
    private String nroMinRete;
    private String nroMaxRete;
    
    private String codigoProducto;
    
    
	public String getBonoNivel() {
		return bonoNivel;
	}
	public String getCodBonoNivel() {
		return codBonoNivel;
	}
	public String getMonPrem() {
		return monPrem;
	}
	public void setBonoNivel(String bonoNivel) {
		this.bonoNivel = bonoNivel;
	}
	public void setCodBonoNivel(String codBonoNivel) {
		this.codBonoNivel = codBonoNivel;
	}
	public void setMonPrem(String monPrem) {
		this.monPrem = monPrem;
	}
	public String getExigencia() {
		return exigencia;
	}
	public String getSobExigencia() {
		return sobExigencia;
	}
	public String getNroMinIng22() {
		return nroMinIng22;
	}
	public String getPorRetExig22() {
		return porRetExig22;
	}
	public String getNroMinIng33() {
		return nroMinIng33;
	}
	public String getPorRetExig33() {
		return porRetExig33;
	}
	public String getNroMinIng44() {
		return nroMinIng44;
	}
	public String getPorRetExig44() {
		return porRetExig44;
	}
	public String getNroCampMax() {
		return nroCampMax;
	}
	public String getNroMinIncPed() {
		return nroMinIncPed;
	}
	public String getCodTipoMedi() {
		return codTipoMedi;
	}
	public String getTipoMedi() {
		return tipoMedi;
	}
	public void setExigencia(String exigencia) {
		this.exigencia = exigencia;
	}
	public void setSobExigencia(String sobExigencia) {
		this.sobExigencia = sobExigencia;
	}
	public void setNroMinIng22(String nroMinIng22) {
		this.nroMinIng22 = nroMinIng22;
	}
	public void setPorRetExig22(String porRetExig22) {
		this.porRetExig22 = porRetExig22;
	}
	public void setNroMinIng33(String nroMinIng33) {
		this.nroMinIng33 = nroMinIng33;
	}
	public void setPorRetExig33(String porRetExig33) {
		this.porRetExig33 = porRetExig33;
	}
	public void setNroMinIng44(String nroMinIng44) {
		this.nroMinIng44 = nroMinIng44;
	}
	public void setPorRetExig44(String porRetExig44) {
		this.porRetExig44 = porRetExig44;
	}
	public void setNroCampMax(String nroCampMax) {
		this.nroCampMax = nroCampMax;
	}
	public void setNroMinIncPed(String nroMinIncPed) {
		this.nroMinIncPed = nroMinIncPed;
	}
	public void setCodTipoMedi(String codTipoMedi) {
		this.codTipoMedi = codTipoMedi;
	}
	public void setTipoMedi(String tipoMedi) {
		this.tipoMedi = tipoMedi;
	}
	public String getCodigoPais() {
		return codigoPais;
	}
	public String getCodigoPrograma() {
		return codigoPrograma;
	}
	public String getTipoBono() {
		return tipoBono;
	}
	public String getCodTipoBono() {
		return codTipoBono;
	}
	public String getNivel() {
		return nivel;
	}
	public String getCodNivel() {
		return codNivel;
	}
	public String getSecuenciaBonoNivel() {
		return secuenciaBonoNivel;
	}
	public String getCampanaLanzamiento() {
		return campanaLanzamiento;
	}
	public String getNroLanzamiento() {
		return nroLanzamiento;
	}
	public String getValorPorcObj() {
		return valorPorcObj;
	}
	public String getValorPorSobreCum() {
		return valorPorSobreCum;
	}
	public String getNroMinIngr() {
		return nroMinIngr;
	}
	public String getPorReten() {
		return porReten;
	}
	public String getCodTipoPrem() {
		return codTipoPrem;
	}
	public String getTipoPrem() {
		return tipoPrem;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}
	public void setTipoBono(String tipoBono) {
		this.tipoBono = tipoBono;
	}
	public void setCodTipoBono(String codTipoBono) {
		this.codTipoBono = codTipoBono;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public void setCodNivel(String codNivel) {
		this.codNivel = codNivel;
	}
	public void setSecuenciaBonoNivel(String secuenciaBonoNivel) {
		this.secuenciaBonoNivel = secuenciaBonoNivel;
	}
	public void setCampanaLanzamiento(String campanaLanzamiento) {
		this.campanaLanzamiento = campanaLanzamiento;
	}
	public void setNroLanzamiento(String nroLanzamiento) {
		this.nroLanzamiento = nroLanzamiento;
	}
	public void setValorPorcObj(String valorPorcObj) {
		this.valorPorcObj = valorPorcObj;
	}
	public void setValorPorSobreCum(String valorPorSobreCum) {
		this.valorPorSobreCum = valorPorSobreCum;
	}
	public void setNroMinIngr(String nroMinIngr) {
		this.nroMinIngr = nroMinIngr;
	}
	public void setPorReten(String porReten) {
		this.porReten = porReten;
	}
	public void setCodTipoPrem(String codTipoPrem) {
		this.codTipoPrem = codTipoPrem;
	}
	public void setTipoPrem(String tipoPrem) {
		this.tipoPrem = tipoPrem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((campanaLanzamiento == null) ? 0 : campanaLanzamiento
						.hashCode());
		result = prime * result
				+ ((codNivel == null) ? 0 : codNivel.hashCode());
		result = prime * result
				+ ((codTipoBono == null) ? 0 : codTipoBono.hashCode());
		result = prime * result
				+ ((codTipoPrem == null) ? 0 : codTipoPrem.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result
				+ ((nroLanzamiento == null) ? 0 : nroLanzamiento.hashCode());
		result = prime * result
				+ ((nroMinIngr == null) ? 0 : nroMinIngr.hashCode());
		result = prime * result
				+ ((porReten == null) ? 0 : porReten.hashCode());
		result = prime
				* result
				+ ((secuenciaBonoNivel == null) ? 0 : secuenciaBonoNivel
						.hashCode());
		result = prime * result
				+ ((tipoBono == null) ? 0 : tipoBono.hashCode());
		result = prime * result
				+ ((tipoPrem == null) ? 0 : tipoPrem.hashCode());
		result = prime
				* result
				+ ((valorPorSobreCum == null) ? 0 : valorPorSobreCum.hashCode());
		result = prime * result
				+ ((valorPorcObj == null) ? 0 : valorPorcObj.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonoNivel other = (BonoNivel) obj;
		if (campanaLanzamiento == null) {
			if (other.campanaLanzamiento != null)
				return false;
		} else if (!campanaLanzamiento.equals(other.campanaLanzamiento))
			return false;
		if (codNivel == null) {
			if (other.codNivel != null)
				return false;
		} else if (!codNivel.equals(other.codNivel))
			return false;
		if (codTipoBono == null) {
			if (other.codTipoBono != null)
				return false;
		} else if (!codTipoBono.equals(other.codTipoBono))
			return false;
		if (codTipoPrem == null) {
			if (other.codTipoPrem != null)
				return false;
		} else if (!codTipoPrem.equals(other.codTipoPrem))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nroLanzamiento == null) {
			if (other.nroLanzamiento != null)
				return false;
		} else if (!nroLanzamiento.equals(other.nroLanzamiento))
			return false;
		if (nroMinIngr == null) {
			if (other.nroMinIngr != null)
				return false;
		} else if (!nroMinIngr.equals(other.nroMinIngr))
			return false;
		if (porReten == null) {
			if (other.porReten != null)
				return false;
		} else if (!porReten.equals(other.porReten))
			return false;
		if (secuenciaBonoNivel == null) {
			if (other.secuenciaBonoNivel != null)
				return false;
		} else if (!secuenciaBonoNivel.equals(other.secuenciaBonoNivel))
			return false;
		if (tipoBono == null) {
			if (other.tipoBono != null)
				return false;
		} else if (!tipoBono.equals(other.tipoBono))
			return false;
		if (tipoPrem == null) {
			if (other.tipoPrem != null)
				return false;
		} else if (!tipoPrem.equals(other.tipoPrem))
			return false;
		if (valorPorSobreCum == null) {
			if (other.valorPorSobreCum != null)
				return false;
		} else if (!valorPorSobreCum.equals(other.valorPorSobreCum))
			return false;
		if (valorPorcObj == null) {
			if (other.valorPorcObj != null)
				return false;
		} else if (!valorPorcObj.equals(other.valorPorcObj))
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "BonoNivel [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", tipoBono=" + tipoBono + ", codTipoBono="
				+ codTipoBono + ", bonoNivel=" + bonoNivel + ", codBonoNivel="
				+ codBonoNivel + ", nivel=" + nivel + ", codNivel=" + codNivel
				+ ", secuenciaBonoNivel=" + secuenciaBonoNivel
				+ ", campanaLanzamiento=" + campanaLanzamiento
				+ ", nroLanzamiento=" + nroLanzamiento + ", valorPorcObj="
				+ valorPorcObj + ", valorPorSobreCum=" + valorPorSobreCum
				+ ", nroMinIngr=" + nroMinIngr + ", nroMaxIngr=" + nroMaxIngr
				+ ", porReten=" + porReten + ", codTipoPrem=" + codTipoPrem
				+ ", tipoPrem=" + tipoPrem + ", monPrem=" + monPrem
				+ ", exigencia=" + exigencia + ", sobExigencia=" + sobExigencia
				+ ", nroMinIng22=" + nroMinIng22 + ", porRetExig22="
				+ porRetExig22 + ", nroMinIng33=" + nroMinIng33
				+ ", porRetExig33=" + porRetExig33 + ", nroMinIng44="
				+ nroMinIng44 + ", porRetExig44=" + porRetExig44
				+ ", nroCampMax=" + nroCampMax + ", nroMinIncPed="
				+ nroMinIncPed + ", codTipoMedi=" + codTipoMedi + ", tipoMedi="
				+ tipoMedi + ", indActi=" + indActi + ", correlativo="
				+ correlativo + ", accion=" + accion + ", nroMinIng="
				+ nroMinIng + ", nroMaxIng=" + nroMaxIng + ", porRetExig="
				+ porRetExig + ", nroMinRete=" + nroMinRete + ", nroMaxRete="
				+ nroMaxRete + ", codigoProducto=" + codigoProducto + "]";
	}
	public String getIndActi() {
		return indActi;
	}
	public void setIndActi(String indActi) {
		this.indActi = indActi;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the nroMinIng
	 */
	public String getNroMinIng() {
		return nroMinIng;
	}
	/**
	 * @param nroMinIng the nroMinIng to set
	 */
	public void setNroMinIng(String nroMinIng) {
		this.nroMinIng = nroMinIng;
	}
	/**
	 * @return the porRetExig
	 */
	public String getPorRetExig() {
		return porRetExig;
	}
	/**
	 * @param porRetExig the porRetExig to set
	 */
	public void setPorRetExig(String porRetExig) {
		this.porRetExig = porRetExig;
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the nroMaxIngr
	 */
	public String getNroMaxIngr() {
		return nroMaxIngr;
	}
	/**
	 * @param nroMaxIngr the nroMaxIngr to set
	 */
	public void setNroMaxIngr(String nroMaxIngr) {
		this.nroMaxIngr = nroMaxIngr;
	}
	/**
	 * @return the nroMaxIng
	 */
	public String getNroMaxIng() {
		return nroMaxIng;
	}
	/**
	 * @param nroMaxIng the nroMaxIng to set
	 */
	public void setNroMaxIng(String nroMaxIng) {
		this.nroMaxIng = nroMaxIng;
	}
	/**
	 * @return the nroMinRete
	 */
	public String getNroMinRete() {
		return nroMinRete;
	}
	/**
	 * @param nroMinRete the nroMinRete to set
	 */
	public void setNroMinRete(String nroMinRete) {
		this.nroMinRete = nroMinRete;
	}
	/**
	 * @return the nroMaxRete
	 */
	public String getNroMaxRete() {
		return nroMaxRete;
	}
	/**
	 * @param nroMaxRete the nroMaxRete to set
	 */
	public void setNroMaxRete(String nroMaxRete) {
		this.nroMaxRete = nroMaxRete;
	}
	
	
	
}
