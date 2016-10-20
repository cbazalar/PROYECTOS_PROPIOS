package biz.belcorp.ssicc.web.scsicc.form;

import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCCCVentasPorDiaForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
		
	private String fechaDocumentoDesde;
	
	private Date fechaDocumentoDesdeD;

	private String fechaDocumentoHasta;	
	
	private Date fechaDocumentoHastaD;
						
	
	/**
	 * @return
	 */
	public Date getFechaDocumentoDesdeD() {
		return fechaDocumentoDesdeD;
	}
	/**
	 * @param fechaDocumentoDesdeD
	 */
	public void setFechaDocumentoDesdeD(Date fechaDocumentoDesdeD) {
		this.fechaDocumentoDesdeD = fechaDocumentoDesdeD;
	}
	/**
	 * @return
	 */
	public Date getFechaDocumentoHastaD() {
		return fechaDocumentoHastaD;
	}
	/**
	 * @param fechaDocumentoHastaD
	 */
	public void setFechaDocumentoHastaD(Date fechaDocumentoHastaD) {
		this.fechaDocumentoHastaD = fechaDocumentoHastaD;
	}
	
	/*
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDocumentoDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaDocumentoHasta = sdf.format(new Date(System.currentTimeMillis()));

	}
	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#setCodigoPais(java.lang.String)
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}	

	/**
	 * @return Returns the fechaDocumentoDesde.
	 */
	public String getFechaDocumentoDesde() {
		return fechaDocumentoDesde;
	}

	/**
	 * @param fechaDocumentoDesde
	 */
	public void setFechaDocumentoDesde(String fechaDocumentoDesde) {
		this.fechaDocumentoDesde = fechaDocumentoDesde;
	}

	/**
	 * @return Returns the fechaDocumentoHasta.
	 */
	public String getFechaDocumentoHasta() {
		return fechaDocumentoHasta;
	}

	/**
	 * @param fechaDocumentoHasta
	 */
	public void setFechaDocumentoHasta(String fechaDocumentoHasta) {
		this.fechaDocumentoHasta = fechaDocumentoHasta;
	}

		
	
}
