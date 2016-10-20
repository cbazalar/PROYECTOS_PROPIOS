package biz.belcorp.ssicc.web.scsicc.form;



import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReportePEDSimulacionFaltantesForm extends BaseReporteForm	implements Serializable{
	
	private static final long serialVersionUID = -539533121751908199L;
	
	
	private String codigoPais;
	private String numeroPedidosProyectar; 
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionDate;
	private String tipoPup;
	private String promedioMontoPedido;
	private String porcentajeMaximoFaltante;	
	
	
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the numeroPedidosProyectar
	 */
	public String getNumeroPedidosProyectar() {
		return numeroPedidosProyectar;
	}


	public void setNumeroPedidosProyectar(String numeroPedidosProyectar) {
		this.numeroPedidosProyectar = numeroPedidosProyectar;
	}

	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}


	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return the promedioMontoPedido
	 */
	public String getPromedioMontoPedido() {
		return promedioMontoPedido;
	}


	public void setPromedioMontoPedido(String promedioMontoPedido) {
		this.promedioMontoPedido = promedioMontoPedido;
	}

	/**
	 * @return the porcentajeMaximoFaltante
	 */
	public String getPorcentajeMaximoFaltante() {
		return porcentajeMaximoFaltante;
	}

	/**
	 * @param porcentajeMaximoFaltante the porcentajeMaximoFaltante to set
	 */
	public void setPorcentajeMaximoFaltante(String porcentajeMaximoFaltante) {
		this.porcentajeMaximoFaltante = porcentajeMaximoFaltante;
	}

	/**
	 * @return the tipoPup
	 */
	public String getTipoPup() {
		return tipoPup;
	}

	/**
	 * @param tipoPup the tipoPup to set
	 */
	public void setTipoPup(String tipoPup) {
		this.tipoPup = tipoPup;
	}

	/**
	 * @return the fechaFacturacionDate
	 */
	public Date getFechaFacturacionDate() {
		return fechaFacturacionDate;
	}

	/**
	 * @param fechaFacturacionDate the fechaFacturacionDate to set
	 */
	public void setFechaFacturacionDate(Date fechaFacturacionDate) {
		this.fechaFacturacionDate = fechaFacturacionDate;
	}
	
	
	
}