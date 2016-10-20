/**
 * 
 */
package biz.belcorp.ssicc.reportes.web.scsicc.form;

import java.io.Serializable;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
public class ReporteINCRecomendacionesConcursoForm extends ReporteINCPuntObtenidosBolsaFaltantesIncForm
implements Serializable {

	private static final long serialVersionUID = 5831146315667946243L;
	private String codBaseCalculo;
	private String reporteTotalizado;
	private String anhio;

	/**
	 * @return codBaseCalculo
	 */
	public String getCodBaseCalculo() {
		return codBaseCalculo;
	}

	/**
	 * @param codBaseCalculo
	 */
	public void setCodBaseCalculo(String codBaseCalculo) {
		this.codBaseCalculo = codBaseCalculo;
	}

	/**
	 * @return reporteTotalizado
	 */
	public String getReporteTotalizado() {
		return reporteTotalizado;
	}

	/**
	 * @param reporteTotalizado
	 */
	public void setReporteTotalizado(String reporteTotalizado) {
		this.reporteTotalizado = reporteTotalizado;
	}

	/**
	 * @return anhio
	 */
	public String getAnhio() {
		return anhio;
	}

	/**
	 * @param anhio The anhio to set.
	 * @struts.validator type = "required"
	 */
	public void setAnhio(String anhio) {
		this.anhio = anhio;
	}
	
}
