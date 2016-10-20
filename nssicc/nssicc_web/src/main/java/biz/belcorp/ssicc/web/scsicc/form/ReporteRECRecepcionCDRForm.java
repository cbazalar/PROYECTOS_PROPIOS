package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



/**
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 * 
 * 
 * @struts.form name="reporteRECRecepcionCDRForm" extends="baseReporteForm"
 */

public class ReporteRECRecepcionCDRForm extends BaseReporteForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoUsuario;
	private String tipoIndicadorCDR;
	private String codigoCampanhaCDR;
	private String codigoCampanhaPedido;
	private String fechaIngreso;
	private Date fechaIngresoDt;
	
	
	public Date getFechaIngresoDt() {
		return fechaIngresoDt;
	}

	public void setFechaIngresoDt(Date fechaIngresoDt) {
		this.fechaIngresoDt = fechaIngresoDt;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the tipoIndicadorCDR
	 */
	public String getTipoIndicadorCDR() {
		return tipoIndicadorCDR;
	}

	
	public void setTipoIndicadorCDR(String tipoIndicadorCDR) {
		this.tipoIndicadorCDR = tipoIndicadorCDR;
	}


	public String getCodigoCampanhaCDR() {
		return codigoCampanhaCDR;
	}

	public void setCodigoCampanhaCDR(String codigoCampanhaCDR) {
		this.codigoCampanhaCDR = codigoCampanhaCDR;
	}


	public String getCodigoCampanhaPedido() {
		return codigoCampanhaPedido;
	}

	public void setCodigoCampanhaPedido(String codigoCampanhaPedido) {
		this.codigoCampanhaPedido = codigoCampanhaPedido;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}


	public String getFechaIngreso() {
		return fechaIngreso;
	}

	
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	

	
}