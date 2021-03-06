package biz.belcorp.ssicc.web.spusicc.lideres.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoLIDGenerarMensajePuntajeObtenidoForm extends BaseProcesoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2459843262996621888L;

	private String codigoPais;
	//private List marcaList;
	private String codigoMarca;
	private String periodoProceso;
	private String codigoIdiomaISO;
	private String fechaProceso;
	private Date fechaProcesoD;
	private String indicadorEjecucion;
	
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoMarca() {
		return codigoMarca;
	}
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	public String getPeriodoProceso() {
		return periodoProceso;
	}
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}
	public String getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	public Date getFechaProcesoD() {
		return fechaProcesoD;
	}
	public void setFechaProcesoD(Date fechaProcesoD) {
		this.fechaProcesoD = fechaProcesoD;
	}
	public String getIndicadorEjecucion() {
		return indicadorEjecucion;
	}
	public void setIndicadorEjecucion(String indicadorEjecucion) {
		this.indicadorEjecucion = indicadorEjecucion;
	}
	
	
}
