package biz.belcorp.ssicc.web.scsicc.form;



import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;




/**
 * @author <a href="mailto:jonunez@belcorp.biz">Jose Nunez</a>
 * 
 * @struts.form name = "reporteVENResumenVentasForm" extends="baseReporteForm"
 */

public class ReporteVENResumenVentasForm extends BaseReporteForm{
	
	private String codigoPais;

	private String fechaDesde;
	
	private Date fechaDesdeD;

	private String fechaHasta;
	
	private Date fechaHastaD;

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * 
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the fechaDesde.
	 */
	public String getFechaDesde() {
		return fechaDesde;
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

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	
	public void reset() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDesdeD =new Date(System.currentTimeMillis());
		this.fechaHastaD =new Date(System.currentTimeMillis());

	}

}
