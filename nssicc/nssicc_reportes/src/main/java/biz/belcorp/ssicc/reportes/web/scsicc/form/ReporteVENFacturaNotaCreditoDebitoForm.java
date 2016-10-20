package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteVENFacturaNotaCreditoDebitoForm extends BaseReporteForm{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6759079348932850924L;

		private String codigoPais;
		
		private String fechaInicio;
		
		private String fechaFin;
		
		private String tipoReporte;
		
		private String idiomaReporte;
		
		private String paisReporte;
		
		private Date calendarInicio;	
		private Date calendarFin;

		public String getCodigoPais() {
			return codigoPais;
		}


		public void setCodigoPais(String codigoPais) {
			this.codigoPais = codigoPais;
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


		public String getTipoReporte() {
			return tipoReporte;
		}


		public void setTipoReporte(String tipoReporte) {
			this.tipoReporte = tipoReporte;
		}


		public String getIdiomaReporte() {
			return idiomaReporte;
		}


		public void setIdiomaReporte(String idiomaReporte) {
			this.idiomaReporte = idiomaReporte;
		}


		public String getPaisReporte() {
			return paisReporte;
		}


		public void setPaisReporte(String paisReporte) {
			this.paisReporte = paisReporte;
		}


		public Date getCalendarInicio() {
			return calendarInicio;
		}


		public void setCalendarInicio(Date calendarInicio) {
			this.calendarInicio = calendarInicio;
		}


		public Date getCalendarFin() {
			return calendarFin;
		}


		public void setCalendarFin(Date calendarFin) {
			this.calendarFin = calendarFin;
		}
}
