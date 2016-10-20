package biz.belcorp.ssicc.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author <a href="mailto:ghuertas@sigcomt.com">Gonzalo Huertas </a>
 * 
 */
public class ReportePEDPedidosWebZonaForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;
	
	private String[] codigoRegion;
	
	private String[] codigoZona;


	/*
	 * public void reset(ActionMapping mapping, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		this.codigoPeriodo = (String) request.getSession().getAttribute(
				"periodoActual");
		if (StringUtils.isEmpty(this.codigoPeriodo))
			this.codigoPeriodo = periodo;
	}
	 * */
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	
	
}
