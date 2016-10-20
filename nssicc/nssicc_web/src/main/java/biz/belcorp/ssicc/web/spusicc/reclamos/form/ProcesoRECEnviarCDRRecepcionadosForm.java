package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;
import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

@ManagedBean
@SessionScoped
public class ProcesoRECEnviarCDRRecepcionadosForm extends BaseProcesoForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoUsuario;
	private String tipoIndicadorCDR;
	private String codigoCampanhaCDR;
	private String codigoCampanhaPedido;
	private String fechaIngreso;
	private Date fechaIngresoD;
	
	private boolean mostrarBotonProceso = false;
	
	/**
	 * @return the tipoIndicadorCDR
	 */
	public String getTipoIndicadorCDR() {
		return tipoIndicadorCDR;
	}

	/**
	 * @param tipoIndicadorCDR the tipoIndicadorCDR to set
	 */
	public void setTipoIndicadorCDR(String tipoIndicadorCDR) {
		this.tipoIndicadorCDR = tipoIndicadorCDR;
	}

	/**
	 * @return the codigoCampanhaCDR
	 */
	public String getCodigoCampanhaCDR() {
		return codigoCampanhaCDR;
	}

	/**
	 * @param codigoCampanhaCDR the codigoCampanhaCDR to set
	 */
	public void setCodigoCampanhaCDR(String codigoCampanhaCDR) {
		this.codigoCampanhaCDR = codigoCampanhaCDR;
	}

	/**
	 * @return the codigoCampanhaPedido
	 */
	public String getCodigoCampanhaPedido() {
		return codigoCampanhaPedido;
	}

	/**
	 * @param codigoCampanhaPedido the codigoCampanhaPedido to set
	 */
	public void setCodigoCampanhaPedido(String codigoCampanhaPedido) {
		this.codigoCampanhaPedido = codigoCampanhaPedido;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the fechaIngreso
	 */
	public String getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @param fechaIngreso the fechaIngreso to set
	 */
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/**
	 * @return the mostrarBotonProceso
	 */
	public boolean isMostrarBotonProceso() {
		return mostrarBotonProceso;
	}

	/**
	 * @param mostrarBotonProceso the mostrarBotonProceso to set
	 */
	public void setMostrarBotonProceso(boolean mostrarBotonProceso) {
		this.mostrarBotonProceso = mostrarBotonProceso;
	}

	/**
	 * @return the fechaIngresoD
	 */
	public Date getFechaIngresoD() {
		return fechaIngresoD;
	}

	/**
	 * @param fechaIngresoD the fechaIngresoD to set
	 */
	public void setFechaIngresoD(Date fechaIngresoD) {
		this.fechaIngresoD = fechaIngresoD;
	}
}