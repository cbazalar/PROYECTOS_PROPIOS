package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/**
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */

public class ReporteINCProgramaReconocimientoForm extends BaseReporteForm  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String numConcurso;
	private String codigoPeriodo;
	private String puntajeMinimo;
	private String puntajeMaximo;


	/**
	 * @return the numConcurso
	 */
	public String getNumConcurso() {
		return numConcurso;
	}

	/**
	 * @param numConcurso the numConcurso to set
	 * @struts.validator type = "required"
	 */
	public void setNumConcurso(String numConcurso) {
		this.numConcurso = numConcurso;
	}

	/**
	 * @return Returns the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */ 
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
		
//    public void reset(ActionMapping mapping, HttpServletRequest request) {
//				
//    	this.numConcurso = null;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
//		String periodo = sdf.format(new Date(System.currentTimeMillis()));
//		 
//		this.codigoPeriodo = (String) request.getSession().getAttribute(
//				"periodoActual");
//		if (StringUtils.isEmpty(this.codigoPeriodo))
//			this.codigoPeriodo = periodo;
//		
//		//-- Valores x defecto Puntaje minimo y maximo
//		this.puntajeMinimo = "0";
//		this.puntajeMaximo = "9999999";
//		
//	}

	public void setFormatoExportacion(String formatoExportacion) {
		
	}

	/**
	 * @return the puntajeMinimo
	 */
	public String getPuntajeMinimo() {
		return puntajeMinimo;
	}

	/**
	 * The puntajeMinimo to set.
	 * @param puntajeMinimo
	 */
	public void setPuntajeMinimo(String puntajeMinimo) {
		this.puntajeMinimo = puntajeMinimo;
	}

	/**
	 * @return the puntajeMaximo
	 */
	public String getPuntajeMaximo() {
		return puntajeMaximo;
	}

	/**
	 * The puntajeMaximo to set.
	 * @param puntajeMaximo
	 */
	public void setPuntajeMaximo(String puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}
	
	
	
	
}