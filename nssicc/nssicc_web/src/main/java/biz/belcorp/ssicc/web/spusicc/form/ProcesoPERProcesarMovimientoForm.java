package biz.belcorp.ssicc.web.spusicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazPaqueteForm;


public class ProcesoPERProcesarMovimientoForm extends BaseInterfazPaqueteForm  {

	private static final long serialVersionUID = -6597249242061089900L;

	private String codigoTipoOrigenDatos;
	
	private String numeroLoteInterno;
	
		/**
	 * @return Returns the codigoTipoOrigenDatos.
	 */
	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}

	/**
	 * @param codigoTipoOrigenDatos The codigoTipoOrigenDatos to set.
	 */
	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}

	/**
	 * @return Returns the numeroLoteInterno.
	 */
	public String getNumeroLoteInterno() {
		return numeroLoteInterno;
	}


	/**
	 * @param numeroLoteInterno The numeroLoteInterno to set.
	 */
	public void setNumeroLoteInterno(String numeroLoteInterno) {
		this.numeroLoteInterno = numeroLoteInterno;
	}	
	

}
