package biz.belcorp.ssicc.web.spusicc.let.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoLETProgramaCorporativoRangoRetencionForm extends BaseEditForm{
	
		private static final long serialVersionUID = 4379012763914104631L;
		
		//Pestańa - Rango Retencion
		private String codigoRetencion;
		private String ingresosIniciales;
		private String ingresosFinales;
		private String ganancia22;
		private String ganancia33;
		private String ganancia44;
		private String retencion22;
		private String retencion33;
		private String retencion44;
		private String indicadorPremio;
		
		private String correlativo;
		private String estado;
			
		
		public String getCodigoRetencion() {
			return codigoRetencion;
		}
		/**
		 * @param codigoRetencion the codigoRetencion to set
		 * @struts.validator type = "required"
		 */
		public void setCodigoRetencion(String codigoRetencion) {
			this.codigoRetencion = codigoRetencion;
		}
		/**
		 * @return the ingresosIniciales
		 */
		public String getIngresosIniciales() {
			return ingresosIniciales;
		}
		/**
		 * @param ingresosIniciales the ingresosIniciales to set
		 * @struts.validator type = "required"
		 */
		public void setIngresosIniciales(String ingresosIniciales) {
			this.ingresosIniciales = ingresosIniciales;
		}
		/**
		 * @return the ingresosFinales
		 */
		public String getIngresosFinales() {
			return ingresosFinales;
		}
		/**
		 * @param ingresosFinales the ingresosFinales to set
		 * @struts.validator type = "required"
		 */
		public void setIngresosFinales(String ingresosFinales) {
			this.ingresosFinales = ingresosFinales;
		}
		/**
		 * @return the ganancia22
		 */
		public String getGanancia22() {
			return ganancia22;
		}
		/**
		 * @param ganancia22 the ganancia22 to set
		 * @struts.validator type = "required"
		 */
		public void setGanancia22(String ganancia22) {
			this.ganancia22 = ganancia22;
		}
		/**
		 * @return the ganancia33
		 */
		public String getGanancia33() {
			return ganancia33;
		}
		/**
		 * @param ganancia33 the ganancia33 to set
		 * @struts.validator type = "required"
		 */
		public void setGanancia33(String ganancia33) {
			this.ganancia33 = ganancia33;
		}
		/**
		 * @return the ganancia44
		 */
		public String getGanancia44() {
			return ganancia44;
		}
		/**
		 * @param ganancia44 the ganancia44 to set
		 * @struts.validator type = "required"
		 */
		public void setGanancia44(String ganancia44) {
			this.ganancia44 = ganancia44;
		}
		/**
		 * @return the retencion22
		 */
		public String getRetencion22() {
			return retencion22;
		}
		/**
		 * @param retencion22 the retencion22 to set
		 * @struts.validator type = "required"
		 */
		public void setRetencion22(String retencion22) {
			this.retencion22 = retencion22;
		}
		/**
		 * @return the retencion33
		 */
		public String getRetencion33() {
			return retencion33;
		}
		/**
		 * @param retencion33 the retencion33 to set
		 * @struts.validator type = "required"
		 */
		public void setRetencion33(String retencion33) {
			this.retencion33 = retencion33;
		}
		/**
		 * @return the retencion44
		 */
		public String getRetencion44() {
			return retencion44;
		}
		/**
		 * @param retencion44 the retencion44 to set
		 * @struts.validator type = "required"
		 */
		public void setRetencion44(String retencion44) {
			this.retencion44 = retencion44;
		}
		/**
		 * @return the indicadorPremio
		 */
		public String getIndicadorPremio() {
			return indicadorPremio;
		}
		/**
		 * @param indicadorPremio the indicadorPremio to set
		 */
		public void setIndicadorPremio(String indicadorPremio) {
			this.indicadorPremio = indicadorPremio;
		}
		/**
		 * @return the correlativo
		 */
		public String getCorrelativo() {
			return correlativo;
		}
		/**
		 * @param correlativo the correlativo to set
		 */
		public void setCorrelativo(String correlativo) {
			this.correlativo = correlativo;
		}
		/**
		 * @return the estado
		 */
		public String getEstado() {
			return estado;
		}
		/**
		 * @param estado the estado to set
		 */
		public void setEstado(String estado) {
			this.estado = estado;
		}
	
}
