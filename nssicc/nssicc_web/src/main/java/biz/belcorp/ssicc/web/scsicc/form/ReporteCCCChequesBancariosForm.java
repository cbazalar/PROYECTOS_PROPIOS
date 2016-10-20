package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 * @struts.form name = "reporteCCCChequesBancariosForm"
 */
public class ReporteCCCChequesBancariosForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
				
	private String codigoBanco;
	
	private String tipoReporte;
	
	private String tipoCheque;
	
	private String fechaCobroDesde;
	
	private Date fechaCobroDesdeD;

	private String fechaCobroHasta;
	
	private Date fechaCobroHastaD;
	
	private String fechaProcDesde;
	
	private Date fechaProcDesdeD;

	private String fechaProcHasta;
	
	private Date fechaProcHastaD;
	
	
	

	public Date getFechaCobroDesdeD() {
		return fechaCobroDesdeD;
	}

	public void setFechaCobroDesdeD(Date fechaCobroDesdeD) {
		this.fechaCobroDesdeD = fechaCobroDesdeD;
	}

	public Date getFechaCobroHastaD() {
		return fechaCobroHastaD;
	}

	public void setFechaCobroHastaD(Date fechaCobroHastaD) {
		this.fechaCobroHastaD = fechaCobroHastaD;
	}

	public Date getFechaProcDesdeD() {
		return fechaProcDesdeD;
	}

	public void setFechaProcDesdeD(Date fechaProcDesdeD) {
		this.fechaProcDesdeD = fechaProcDesdeD;
	}

	public Date getFechaProcHastaD() {
		return fechaProcHastaD;
	}

	public void setFechaProcHastaD(Date fechaProcHastaD) {
		this.fechaProcHastaD = fechaProcHastaD;
	}

	public void reset() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = new Date(System.currentTimeMillis());
		this.fechaCobroDesdeD = fecha;
		this.fechaCobroHastaD = fecha;
		this.fechaProcDesdeD = fecha;
		this.fechaProcHastaD = fecha;

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
	 * @struts.validator type = "required"
	 */
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


	/**
	 * @param tipoReporte the tipoReporte to set
	 * @struts.validator type = "required"
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}


	/**
	 * @return the tipoCheque
	 * 
	 */
	public String getTipoCheque() {
		return tipoCheque;
	}


	/**
	 * @param tipoCheque the tipoCheque to set
	 * @struts.validator type = "required"
	 */
	public void setTipoCheque(String tipoCheque) {
		this.tipoCheque = tipoCheque;
	}

	
	/**
	 * @return Returns the codigoBanco.
	 */
	public String getCodigoBanco() {
		return codigoBanco;
	}

	/**
	 * @param codigoBanco
	 *            The codigoBanco to set.
	 */
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	
	/**
	 * @return Returns the fechaCobroDesde.
	 */
	public String getFechaCobroDesde() {
		return fechaCobroDesde;
	}

	/**
	 * @param fechaCobroDesde
	 *            The fechaCobroDesde to set.
	 * 
	 */
	public void setFechaCobroDesde(String fechaCobroDesde) {
		this.fechaCobroDesde = fechaCobroDesde;
	}

	/**
	 * @return Returns the fechaCobroHasta.
	 */
	public String getFechaCobroHasta() {
		return fechaCobroHasta;
	}

	/**
	 * @param fechaCobroHasta
	 *            The fechaCobroHasta to set.
	 * 
	 */
	public void setFechaCobroHasta(String fechaCobroHasta) {
		this.fechaCobroHasta = fechaCobroHasta;
	}

	
	/**
	 * @return Returns the fechaProcDesde.
	 */
	public String getFechaProcDesde() {
		return fechaProcDesde;
	}

	/**
	 * @param fechaProcDesde
	 *            The fechaProcDesde to set.
	 * 
	 */
	public void setFechaProcDesde(String fechaProcDesde) {
		this.fechaProcDesde = fechaProcDesde;
	}
	
	/**
	 * @return Returns the fechaProcHasta.
	 */
	public String getFechaProcHasta() {
		return fechaProcHasta;
	}

	/**
	 * @param fechaProcHasta
	 *            The fechaProcHasta to set.
	 * 
	 */
	public void setFechaProcHasta(String fechaProcHasta) {
		this.fechaProcHasta = fechaProcHasta;
	}

}
