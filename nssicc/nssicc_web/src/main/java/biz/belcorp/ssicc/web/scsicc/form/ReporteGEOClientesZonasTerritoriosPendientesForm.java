package biz.belcorp.ssicc.web.scsicc.form;



import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * @author <a href="mailto:itocto@belcorp.biz">Marco Agurto</a>
 * 
 * @struts.form name = "reporteGEOClientesZonasTerritoriosPendientesForm"
 */
public class ReporteGEOClientesZonasTerritoriosPendientesForm extends BaseReporteForm {
	private String codigoPais;

	private String fechaDesde;
	
	private Date fechaDesdeDt;

	private String fechaHasta;
	
	private Date fechaHastaDt;
	
	private String tipoReporte;
	

	public Date getFechaDesdeDt() {
		return fechaDesdeDt;
	}

	public void setFechaDesdeDt(Date fechaDesdeDt) {
		this.fechaDesdeDt = fechaDesdeDt;
	}

	public Date getFechaHastaDt() {
		return fechaHastaDt;
	}

	public void setFechaHastaDt(Date fechaHastaDt) {
		this.fechaHastaDt = fechaHastaDt;
	}

	

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

	/**
	 * @param fechaDesde
	 *            The fechaDesde to set.
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * @return Returns the fechaHasta.
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * @param fechaHasta
	 *            The fechaHasta to set.
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/*
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaHasta = sdf.format(new Date(System.currentTimeMillis()));

	}
}
