package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 * @struts.form name = "reporteCCCLiquidacionCobranzasAplicacionForm"
 */
public class ReporteCCCLiquidacionCobranzasAplicacionForm extends BaseReporteForm implements
		Serializable {

	private String codigoPais;
		
	private String tipoVista;
	
	private String tipoOrigen;

	private String codigoBanco;
	
	private String fechaPagoDesde;
	
	private Date fechaPagoDesdeD;

	private String fechaPagoHasta;
	
	private Date fechaPagoHastaD;

	private String fechaProcDesde;
	
	private Date fechaProcDesdeD;

	private String fechaProcHasta;
	
	private Date fechaProcHastaD;
	
	

	
	public Date getFechaProcHastaD() {
		return fechaProcHastaD;
	}

	public void setFechaProcHastaD(Date fechaProcHastaD) {
		this.fechaProcHastaD = fechaProcHastaD;
	}

	public Date getFechaPagoDesdeD() {
		return fechaPagoDesdeD;
	}

	public void setFechaPagoDesdeD(Date fechaPagoDesdeD) {
		this.fechaPagoDesdeD = fechaPagoDesdeD;
	}

	public Date getFechaPagoHastaD() {
		return fechaPagoHastaD;
	}

	public void setFechaPagoHastaD(Date fechaPagoHastaD) {
		this.fechaPagoHastaD = fechaPagoHastaD;
	}

	public Date getFechaProcDesdeD() {
		return fechaProcDesdeD;
	}

	public void setFechaProcDesdeD(Date fechaProcDesdeD) {
		this.fechaProcDesdeD = fechaProcDesdeD;
	}
	

	public void reset() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaPagoDesde = fecha;
		this.fechaPagoHasta = fecha;
		this.fechaProcDesde = fecha;
		this.fechaProcHasta = fecha;
		//this.tipoVista = Constants.TIPO_VISTA_BANCO;
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
	 * @return Returns the tipoOrigen.
	 */
	public String getTipoOrigen() {
		return tipoOrigen;
	}

	/**
	 * @param tipoOrigen
	 *            The tipoOrigen to set.
	 * 
	 */
	public void setTipoOrigen(String tipoOrigen) {
		this.tipoOrigen = tipoOrigen;
	}
	
	/**
	 * @return Returns the tipoVista.
	 */
	public String getTipoVista() {
		return tipoVista;
	}

	/**
	 * @param tipoVista
	 *            The tipoVista to set.
	 * 
	 */
	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
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
