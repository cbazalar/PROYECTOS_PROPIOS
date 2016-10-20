/*
 * Created on 07/11/2005 02:10:21 PM biz.belcorp.ssicc.model.PercepcionesVtaDirecta
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PercepcionesVtaDirecta  implements
        Serializable { 
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = -2676273591056372435L;

	private String codigoPais;

    private String serCope;

    private Timestamp fecDole;
    
    private String numCope;

    private String conCons;

    private Float monPerc;

    private String codAusu;

    private Float numLote;

    private Float auxiliar;
    
    private Float estado;

    private String numDole;
    
    private String tipDole;
    
    private String serDole;
    
    private Float monTodl;

    private Float porPerc;

    private Float monPago;
    
    private String codAuto;
    
    private String numAusu;
    
    private Timestamp fecAuto;
    
    private String serDocu;
    
    private Float numInau;
    
    private Float numFiau;
    
    

	public Float getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(Float auxiliar) {
		this.auxiliar = auxiliar;
	}

	public String getCodAusu() {
		return codAusu;
	}

	public void setCodAusu(String codAusu) {
		this.codAusu = codAusu;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getConCons() {
		return conCons;
	}

	public void setConCons(String conCons) {
		this.conCons = conCons;
	}

	public Float getEstado() {
		return estado;
	}

	public void setEstado(Float estado) {
		this.estado = estado;
	}



	public Timestamp getFecDole() {
		return fecDole;
	}

	public void setFecDole(Timestamp fecDole) {
		this.fecDole = fecDole;
	}

	public Float getMonPerc() {
		return monPerc;
	}

	public void setMonPerc(Float monPerc) {
		this.monPerc = monPerc;
	}

	public String getNumCope() {
		return numCope;
	}

	public void setNumCope(String numCope) {
		this.numCope = numCope;
	}

	public String getNumDole() {
		return numDole;
	}

	public void setNumDole(String numDole) {
		this.numDole = numDole;
	}

	public Float getNumLote() {
		return numLote;
	}

	public void setNumLote(Float numLote) {
		this.numLote = numLote;
	}

	public String getSerCope() {
		return serCope;
	}

	public void setSerCope(String serCope) {
		this.serCope = serCope;
	}

	public Float getMonPago() {
		return monPago;
	}

	public void setMonPago(Float monPago) {
		this.monPago = monPago;
	}

	public Float getMonTodl() {
		return monTodl;
	}

	public void setMonTodl(Float monTodl) {
		this.monTodl = monTodl;
	}

	public Float getPorPerc() {
		return porPerc;
	}

	public void setPorPerc(Float porPerc) {
		this.porPerc = porPerc;
	}

	public String getSerDole() {
		return serDole;
	}

	public void setSerDole(String serDole) {
		this.serDole = serDole;
	}

	public String getTipDole() {
		return tipDole;
	}

	public void setTipDole(String tipDole) {
		this.tipDole = tipDole;
	}

	public String getCodAuto() {
		return codAuto;
	}

	public void setCodAuto(String codAuto) {
		this.codAuto = codAuto;
	}

	public Timestamp getFecAuto() {
		return fecAuto;
	}

	public void setFecAuto(Timestamp fecAuto) {
		this.fecAuto = fecAuto;
	}

	public String getNumAusu() {
		return numAusu;
	}

	public void setNumAusu(String numAusu) {
		this.numAusu = numAusu;
	}

	public Float getNumFiau() {
		return numFiau;
	}

	public void setNumFiau(Float numFiau) {
		this.numFiau = numFiau;
	}

	public Float getNumInau() {
		return numInau;
	}

	public void setNumInau(Float numInau) {
		this.numInau = numInau;
	}

	public String getSerDocu() {
		return serDocu;
	}

	public void setSerDocu(String serDocu) {
		this.serDocu = serDocu;
	}

 

}