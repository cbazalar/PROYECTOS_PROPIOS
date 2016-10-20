package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;


	import java.io.Serializable;

	public class DatosConsultoraBasico implements Serializable {

		private static final long serialVersionUID = 1L;
		private String codigoConsultora;		                     			
		private String nombreConsultora;
		private String tipoDocumento;
		private String documentoIdentidad;
		/**
		 * @return the codigoConsultora
		 */
		public String getCodigoConsultora() {
			return codigoConsultora;
		}
		/**
		 * @param codigoConsultora the codigoConsultora to set
		 */
		public void setCodigoConsultora(String codigoConsultora) {
			this.codigoConsultora = codigoConsultora;
		}
		/**
		 * @return the nombreConsultora
		 */
		public String getNombreConsultora() {
			return nombreConsultora;
		}
		/**
		 * @param nombreConsultora the nombreConsultora to set
		 */
		public void setNombreConsultora(String nombreConsultora) {
			this.nombreConsultora = nombreConsultora;
		}
		/**
		 * @return the tipoDocumento
		 */
		public String getTipoDocumento() {
			return tipoDocumento;
		}
		/**
		 * @param tipoDocumento the tipoDocumento to set
		 */
		public void setTipoDocumento(String tipoDocumento) {
			this.tipoDocumento = tipoDocumento;
		}
		/**
		 * @return the documentoIdentidad
		 */
		public String getDocumentoIdentidad() {
			return documentoIdentidad;
		}
		/**
		 * @param documentoIdentidad the documentoIdentidad to set
		 */
		public void setDocumentoIdentidad(String documentoIdentidad) {
			this.documentoIdentidad = documentoIdentidad;
		}						

}
