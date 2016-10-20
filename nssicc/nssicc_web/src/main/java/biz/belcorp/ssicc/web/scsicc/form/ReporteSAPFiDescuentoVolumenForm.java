package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteSAPFiDescuentoVolumenForm extends BaseReporteForm	implements Serializable {
	
	
	private static final long serialVersionUID = -7105145924259359781L;

	private String codigoPais;	
	private String codigoTipoReporte;	
	private String fechaInicio;	
	private String fechaFin;
	private Date fechaInicioD;
	private Date fechaFinD;


	public ReporteSAPFiDescuentoVolumenForm(){
		this.fechaInicio = null;
		this.fechaFin = null;
		this.codigoTipoReporte = Constants.SAP_TIPO_REPORTE_MATERIAL_PROM; 
	}
	
	public String getCodigoTipoReporte() {
		return codigoTipoReporte;
	}

	public void setCodigoTipoReporte(String codigoTipoReporte) {
		this.codigoTipoReporte = codigoTipoReporte;
	}

	
	public String getFechaInicio() {
		return fechaInicio;
	}

	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getCodigoPais() {
		return this.codigoPais;
	}

	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	public Date getFechaFinD() {
		return fechaFinD;
	}

	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}



		
}
