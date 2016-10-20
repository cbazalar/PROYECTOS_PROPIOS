/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;

/**
 *  
 * <p>
 * <a href="HistoricoTipoDocumento.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cariampoma</a>
 * 
 */
public class HistoricoValidacion implements Serializable{

	private String codPais;
	
	private String codTipoDocu;
	
	private String codVali;

	private String numProc;
	
	private String flagError;

	private String logError;

	public HistoricoValidacion(String codPais, String codTipoDocu,String codVali, String numProc) {
		
		super();
		this.codPais = codPais;
		this.codTipoDocu = codTipoDocu;		
		this.codVali = codVali;
		this.numProc = numProc;
		this.flagError = Constants.NO;
		this.logError ="";
		
	}

	/**
	 * @return the codPais
	 */
	public String getCodPais() {
		return codPais;
	}

	/**
	 * @param codPais the codPais to set
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	/**
	 * @return the codTipoDocu
	 */
	public String getCodTipoDocu() {
		return codTipoDocu;
	}

	/**
	 * @param codTipoDocu the codTipoDocu to set
	 */
	public void setCodTipoDocu(String codTipoDocu) {
		this.codTipoDocu = codTipoDocu;
	}

	/**
	 * @return the codVali
	 */
	public String getCodVali() {
		return codVali;
	}

	/**
	 * @param codVali the codVali to set
	 */
	public void setCodVali(String codVali) {
		this.codVali = codVali;
	}

	/**
	 * @return the numProc
	 */
	public String getNumProc() {
		return numProc;
	}

	/**
	 * @param numProc the numProc to set
	 */
	public void setNumProc(String numProc) {
		this.numProc = numProc;
	}

	/**
	 * @return the flagError
	 */
	public String getFlagError() {
		return flagError;
	}

	/**
	 * @param flagError the flagError to set
	 */
	public void setFlagError(String flagError) {
		this.flagError = flagError;
	}

	/**
	 * @return the logError
	 */
	public String getLogError() {
		return logError;
	}

	/**
	 * @param logError the logError to set
	 */
	public void setLogError(String logError) {
		this.logError = logError;
	}

	/**
	 * @param e
	 */
	public void updateOnError(Exception e) {
		this.flagError = Constants.SI;
		this.logError = e.toString();
		
	}

	


	
}
