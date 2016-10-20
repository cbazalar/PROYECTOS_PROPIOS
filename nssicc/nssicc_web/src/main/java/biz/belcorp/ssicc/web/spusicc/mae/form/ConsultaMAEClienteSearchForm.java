package biz.belcorp.ssicc.web.spusicc.mae.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class ConsultaMAEClienteSearchForm extends BaseSearchForm{

	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String codigoCliente;	
		private String codigoPais;   
		private String numeroDocIdentidad;	
	    private String nombre1;	
	    private String nombre2;	
		private String apellido1;	
		private String apellido2;
		private String codigoZona;
		private String codigoTerritorio;
		private String subTipoCliente;
		private String indicadorActivo;
		private String clienteUnico;
		
		private String longitudCodigoCliente;
		private String indicadorCaracterDocumento;
	        

		 /**
		 * @return the codigoPais
		 */
		public String getCodigoPais() {
			return codigoPais;
		}


		public void setCodigoPais(String codigoPais) {
			this.codigoPais = codigoPais;
		}
		
		
	    /**
		 * @return the codigoZona
		 */
		public String getCodigoZona() {
			return codigoZona;
		}

		/**
		 * @param codigoZona the codigoZona to set
		 */
		public void setCodigoZona(String codigoZona) {
			this.codigoZona = codigoZona;
		}

		/**
		 * @return the codigoTerritorio
		 */
		public String getCodigoTerritorio() {
			return codigoTerritorio;
		}

		/**
		 * @param codigoTerritorio the codigoTerritorio to set
		 */
		public void setCodigoTerritorio(String codigoTerritorio) {
			this.codigoTerritorio = codigoTerritorio;
		}

		/**
		 * @return the subTipoCliente
		 */
		public String getSubTipoCliente() {
			return subTipoCliente;
		}

		/**
		 * @param subTipoCliente the subTipoCliente to set
		 */
		public void setSubTipoCliente(String subTipoCliente) {
			this.subTipoCliente = subTipoCliente;
		}

		/**
		 * @return the indicadorActivo
		 */
		public String getIndicadorActivo() {
			return indicadorActivo;
		}

		/**
		 * @param indicadorActivo the indicadorActivo to set
		 */
		public void setIndicadorActivo(String indicadorActivo) {
			this.indicadorActivo = indicadorActivo;
		}

		public void reset() {
	    	this.codigoCliente=this.numeroDocIdentidad=this.nombre1=null;
	    	this.nombre2=this.apellido1=this.apellido2=null;
	    	this.codigoZona=this.codigoTerritorio=null;
	    	this.subTipoCliente=this.indicadorActivo=null;
	    	this.clienteUnico="";
	    }

		/**
		 * @return
		 */
		public String getCodigoCliente() {
			return codigoCliente;
		}


		/**
		 * @param codigoCliente
		 */
		public void setCodigoCliente(String codigoCliente) {
			this.codigoCliente = codigoCliente;
		}


		/**
		 * @return
		 */
		public String getNumeroDocIdentidad() {
			return numeroDocIdentidad;
		}


		/**
		 * @param numeroDocIdentidad
		 */
		public void setNumeroDocIdentidad(String numeroDocIdentidad) {
			this.numeroDocIdentidad = numeroDocIdentidad;
		}


		/**
		 * @return
		 */
		public String getNombre1() {
			return nombre1;
		}


		/**
		 * @param nombre1
		 */
		public void setNombre1(String nombre1) {
			this.nombre1 = nombre1;
		}


		/**
		 * @return
		 */
		public String getNombre2() {
			return nombre2;
		}


		/**
		 * @param nombre2
		 */
		public void setNombre2(String nombre2) {
			this.nombre2 = nombre2;
		}


		/**
		 * @return
		 */
		public String getApellido1() {
			return apellido1;
		}


		/**
		 * @param apellido1
		 */
		public void setApellido1(String apellido1) {
			this.apellido1 = apellido1;
		}


		/**
		 * @return
		 */
		public String getApellido2() {
			return apellido2;
		}


		/**
		 * @param apellido2
		 */
		public void setApellido2(String apellido2) {
			this.apellido2 = apellido2;
		}

		/**
		 * @return the clienteUnico
		 */
		public String getClienteUnico() {
			return clienteUnico;
		}

		/**
		 * @param clienteUnico the clienteUnico to set
		 */
		public void setClienteUnico(String clienteUnico) {
			this.clienteUnico = clienteUnico;
		}


		public String getLongitudCodigoCliente() {
			return longitudCodigoCliente;
		}

		
		public void setLongitudCodigoCliente(String longitudCodigoCliente) {
			this.longitudCodigoCliente = longitudCodigoCliente;
		}

		/**
		 * @return the indicadorCaracterDocumento
		 */
		public String getIndicadorCaracterDocumento() {
			return indicadorCaracterDocumento;
		}

		/**
		 * @param indicadorCaracterDocumento the indicadorCaracterDocumento to set
		 */
		public void setIndicadorCaracterDocumento(String indicadorCaracterDocumento) {
			this.indicadorCaracterDocumento = indicadorCaracterDocumento;
		}

		
}
