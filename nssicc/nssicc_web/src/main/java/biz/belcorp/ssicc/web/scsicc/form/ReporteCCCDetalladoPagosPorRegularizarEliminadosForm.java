package biz.belcorp.ssicc.web.scsicc.form;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 * @struts.form name = "reporteCCCDetalladoPagosPorRegularizarEliminadosForm"
 */
public class ReporteCCCDetalladoPagosPorRegularizarEliminadosForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;
			
	private String codigoBanco;
	
	private String fechaPagoDesde;

	private String fechaPagoHasta;
	
	private String fechaProcDesde;

	private String fechaProcHasta;
	
	private String fechaElimDesde;

	private String fechaElimHasta;
	
	//dates
	
	private Date fechaPagoDesdeDt;

	private Date fechaPagoHastaDt;
	
	private Date fechaProcDesdeDt;

	private Date fechaProcHastaDt;
	
	private Date fechaElimDesdeDt;

	private Date fechaElimHastaDt;
	
	

	public Date getFechaPagoDesdeDt() {
		return fechaPagoDesdeDt;
	}

	public void setFechaPagoDesdeDt(Date fechaPagoDesdeDt) {
		this.fechaPagoDesdeDt = fechaPagoDesdeDt;
	}

	public Date getFechaPagoHastaDt() {
		return fechaPagoHastaDt;
	}

	public void setFechaPagoHastaDt(Date fechaPagoHastaDt) {
		this.fechaPagoHastaDt = fechaPagoHastaDt;
	}

	public Date getFechaProcDesdeDt() {
		return fechaProcDesdeDt;
	}

	public void setFechaProcDesdeDt(Date fechaProcDesdeDt) {
		this.fechaProcDesdeDt = fechaProcDesdeDt;
	}

	public Date getFechaProcHastaDt() {
		return fechaProcHastaDt;
	}

	public void setFechaProcHastaDt(Date fechaProcHastaDt) {
		this.fechaProcHastaDt = fechaProcHastaDt;
	}

	public Date getFechaElimDesdeDt() {
		return fechaElimDesdeDt;
	}

	public void setFechaElimDesdeDt(Date fechaElimDesdeDt) {
		this.fechaElimDesdeDt = fechaElimDesdeDt;
	}

	public Date getFechaElimHastaDt() {
		return fechaElimHastaDt;
	}

	public void setFechaElimHastaDt(Date fechaElimHastaDt) {
		this.fechaElimHastaDt = fechaElimHastaDt;
	}

	public void reset() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));		
		this.fechaElimDesde = fecha;
		this.fechaElimHasta = fecha;

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
	 * @return Returns the fechaPagoDesde.
	 */
	public String getFechaPagoDesde() {
		return fechaPagoDesde;
	}

	/**
	 * @param fechaPagoDesde
	 *            The fechapagoDesde to set.
	 * 
	 */
	public void setFechaPagoDesde(String fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}

	/**
	 * @return Returns the fechaPagoHasta.
	 */
	public String getFechaPagoHasta() {
		return fechaPagoHasta;
	}

	/**
	 * @param fechaPagoHasta
	 *            The fechapagoHasta to set.
	 * 
	 */
	public void setFechaPagoHasta(String fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
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
	
	/**r
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
	
	/**
	 * @return Returns the fechaElimDesde.
	 */
	public String getFechaElimDesde() {
		return fechaElimDesde;
	}

	/**
	 * @param fechaElimDesde
	 *            The fechaElimDesde to set.
	 * 
	 */
	public void setFechaElimDesde(String fechaElimDesde) {
		this.fechaElimDesde = fechaElimDesde;
	}
	
	/**
	 * @return Returns the fechaElimHasta.
	 */
	public String getFechaElimHasta() {
		return fechaElimHasta;
	}

	/**
	 * @param fechaElimHasta
	 *            The fechaElimHasta to set.
	 * 
	 */
	public void setFechaElimHasta(String fechaElimHasta) {
		this.fechaElimHasta = fechaElimHasta;
	}
	
}
