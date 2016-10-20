package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm extends BaseSearchForm implements Serializable{
	
	
	private static final long serialVersionUID = -3898494875126274260L;
	
	private String codigoPais;
	private String [] codigoCanal;
	private String [] codigoAcceso;
	private String [] codigoSubAcceso;
	private String fechaDesde;
	private String fechaHasta;
	private Date fechaDesdeD;
	private Date fechaHastaD;

	
	public ConsultaPERConsolidadoPercepcionesAcumuladoDiaForm(){
		this.codigoCanal = null;		
		this.codigoAcceso = null;
		this.codigoSubAcceso = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaHasta = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaDesdeD = new Date(System.currentTimeMillis());
		this.fechaHastaD = new Date(System.currentTimeMillis());
	}
	
	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}	

	public String[] getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String[] codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	
	public String[] getCodigoAcceso() {
		return codigoAcceso;
	}
	
	public void setCodigoAcceso(String[] codigoAcceso) {
		this.codigoAcceso = codigoAcceso;
	}

	public String[] getCodigoSubAcceso() {
		return codigoSubAcceso;
	}

	public void setCodigoSubAcceso(String[] codigoSubAcceso) {
		this.codigoSubAcceso = codigoSubAcceso;
	}

	
	public String getFechaDesde() {
		return fechaDesde;
	}
	
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public Date getFechaDesdeD() {
		return fechaDesdeD;
	}

	public void setFechaDesdeD(Date fechaDesdeD) {
		this.fechaDesdeD = fechaDesdeD;
	}

	public Date getFechaHastaD() {
		return fechaHastaD;
	}

	public void setFechaHastaD(Date fechaHastaD) {
		this.fechaHastaD = fechaHastaD;
	}
	
	
}
