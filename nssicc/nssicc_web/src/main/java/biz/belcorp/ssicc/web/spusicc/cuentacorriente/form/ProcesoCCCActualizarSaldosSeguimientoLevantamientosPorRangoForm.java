package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;

public class ProcesoCCCActualizarSaldosSeguimientoLevantamientosPorRangoForm  extends BaseProcesoForm {


	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -8226578863379393867L;
	
	private String codigoPais;
	private String codigoSociedad;
	private String codigoPeriodoInicial;
	private String codigoPeriodoFinal;
    		
	
	public String getCodigoPais() {
		return codigoPais;
	}


	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
	




}
