package biz.belcorp.ssicc.web.scsicc.form;



import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 * @struts.form name="reporteCCCAbonosPorDiaForm" extends="baseReporteForm"
 */
public class ReporteCCCAbonosPorDiaForm extends BaseReporteForm {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
		
	private String fechaDocumentoDesde;
	
	private Date fechaDocumentoDesdeD;

	private String fechaDocumentoHasta;
	
	private Date fechaDocumentoHastaD;
						

	public Date getFechaDocumentoDesdeD() {
		return fechaDocumentoDesdeD;
	}

	public void setFechaDocumentoDesdeD(Date fechaDocumentoDesdeD) {
		this.fechaDocumentoDesdeD = fechaDocumentoDesdeD;
	}

	public Date getFechaDocumentoHastaD() {
		return fechaDocumentoHastaD;
	}

	public void setFechaDocumentoHastaD(Date fechaDocumentoHastaD) {
		this.fechaDocumentoHastaD = fechaDocumentoHastaD;
	}

	public void reset() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaDocumentoDesde = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaDocumentoHasta = sdf.format(new Date(System.currentTimeMillis()));

	}

	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}	

	/**
	 * @return Returns the fechaDocumentoDesde.
	 */
	public String getFechaDocumentoDesde() {
		return fechaDocumentoDesde;
	}


	public void setFechaDocumentoDesde(String fechaDocumentoDesde) {
		this.fechaDocumentoDesde = fechaDocumentoDesde;
	}

	/**
	 * @return Returns the fechaDocumentoHasta.
	 */
	public String getFechaDocumentoHasta() {
		return fechaDocumentoHasta;
	}


	public void setFechaDocumentoHasta(String fechaDocumentoHasta) {
		this.fechaDocumentoHasta = fechaDocumentoHasta;
	}

		
	
}
