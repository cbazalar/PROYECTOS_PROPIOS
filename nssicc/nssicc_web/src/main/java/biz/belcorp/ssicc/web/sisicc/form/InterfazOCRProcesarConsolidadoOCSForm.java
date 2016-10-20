package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * Form del procesamiento del consolidado de archivos de texto de OCS de la
 * Interfaz OCR.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
public class InterfazOCRProcesarConsolidadoOCSForm extends BaseInterfazForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4953114058881678443L;

	private String tipoRecepcion ;
	
	private String codigoPeriodo;
	
	private String numLoteSTO;

	private String codigoBatch;

	private String indValidacionSTO;
	private String showValiAutoPediDigi;
	
	private boolean indValidacionSTOBool;
	
		/**
	 * @return the codigoBatch
	 */
	public String getCodigoBatch() {
		return codigoBatch;
	}

	/**
	 * @param codigoBatch the codigoBatch to set
	 */
	public void setCodigoBatch(String codigoBatch) {
		this.codigoBatch = codigoBatch;
	}

		/**
		 * @return Returns the numLoteSTO.
		 */
		public String getNumLoteSTO() {
			return numLoteSTO;
		}

		/**
		 * @param numLoteSTO The numLoteSTO to set.
		 */
		public void setNumLoteSTO(String numLoteSTO) {
			this.numLoteSTO = numLoteSTO;
		}
	
	public String getTipoRecepcion() {
		return tipoRecepcion;
	}
	/**
	 * @struts.validator type = "required"
	 */
	public void setTipoRecepcion(String tipoRecepcion) {
		this.tipoRecepcion = tipoRecepcion;
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
	 * @param showValiAutoPediDigi the showValiAutoPediDigi to set
	 */
	public void setShowValiAutoPediDigi(String showValiAutoPediDigi) {
		this.showValiAutoPediDigi = showValiAutoPediDigi;
	}

	/**
	 * @return the showValiAutoPediDigi
	 */
	public String getShowValiAutoPediDigi() {
		return showValiAutoPediDigi;
	}

	/**
	 * @return the indValidacionSTO
	 */
	public String getIndValidacionSTO() {
		return indValidacionSTO;
	}

	/**
	 * @param indValidacionSTO the indValidacionSTO to set
	 */
	public void setIndValidacionSTO(String indValidacionSTO) {
		this.indValidacionSTO = indValidacionSTO;
	}

	/**
	 * @return the indValidacionSTOBool
	 */
	public boolean isIndValidacionSTOBool() {
		return indValidacionSTOBool;
	}

	/**
	 * @param indValidacionSTOBool the indValidacionSTOBool to set
	 */
	public void setIndValidacionSTOBool(boolean indValidacionSTOBool) {
		this.indValidacionSTOBool = indValidacionSTOBool;
	}

	
	
	
}