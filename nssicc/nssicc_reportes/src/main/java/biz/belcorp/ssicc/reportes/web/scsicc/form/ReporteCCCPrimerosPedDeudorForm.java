package biz.belcorp.ssicc.reportes.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;



public class ReporteCCCPrimerosPedDeudorForm extends BaseReporteForm {

	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoSociedad;
		
	private String codigoPeriodoInicial;
	
	private String codigoPeriodoFinal;
	
	private String tipoVista;
					
	

public void reset() {
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		String periodo = sdf.format(new Date(System.currentTimeMillis()));
//		 
//		this.codigoPeriodoInicial = (String) request.getSession().getAttribute(
//				"periodoActual");
//		if (StringUtils.isEmpty(this.codigoPeriodoInicial))
//			this.codigoPeriodoInicial = periodo;
//		
//		this.codigoPeriodoFinal = (String) request.getSession().getAttribute(
//		"periodoActual");
//		if (StringUtils.isEmpty(this.codigoPeriodoFinal))
//			this.codigoPeriodoFinal = periodo;
				
	}
	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * 
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
		

	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}				
	
	/**
	 * @return the codigoPeriodoInicial
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}
	

	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
	
	/**
	 * @return the codigoPeriodoFinal
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}
	

	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}
				
			
	/**
	 * @return the tipoVista
	 */
	public String getTipoVista() {
		return tipoVista;
	}


	public void setTipoVista(String tipoVista) {
		this.tipoVista = tipoVista;
	}
	
	

}
