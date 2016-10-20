package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 * @struts.form name = "reporteCCCCargosDirectosForm"
 */
public class ReporteCCCCargosDirectosForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
			
	private String tipoReporte;
	
	private String tipoCargo;
	
     private String fechaDesde;
	
     private Date fechaDesdeD;
     
	private String fechaHasta;
	
	private Date fechaHastaD;
	
	
	
	
	

	
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


	public void reset() {		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date(System.currentTimeMillis());		
		
		this.fechaDesdeD = fecha;
		this.fechaHastaD = fecha;
	}

	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

						
	
	/**
	 * @return the tipoReporte
	 * 
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}



	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}


	/**
	 * @return the tipoCargo
	 * 
	 */
	public String getTipoCargo() {
		return tipoCargo;
	}



	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}


	/**
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	/**
	 * @return the fechaHasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}


}
