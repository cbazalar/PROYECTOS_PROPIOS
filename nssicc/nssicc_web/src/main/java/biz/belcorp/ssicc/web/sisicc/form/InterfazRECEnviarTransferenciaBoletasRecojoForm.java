/*
 * Created on 11-jul-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * <p>
 * <a href="InterfazRECEnviarTransferenciaBoletasRecojoForm.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jos� A. Cairampoma</a>
 * 
 * @struts.form name = "interfazRECEnviarTransferenciaBoletasRecojoForm" extends =
 *              "baseInterfazForm"
 */
public class InterfazRECEnviarTransferenciaBoletasRecojoForm extends BaseInterfazForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String[] regionList;
	
	private String codigoResultado;
	
	private String fechaInicial;
	
	private Date fechaInicialD;
	
	private Date fechaFinalD;
	
	private String fechaFinal;
	
	private String codigoOperacion;
	
	private String formatoExportacion;
	
	
	
	
	public Date getFechaInicialD() {
		return fechaInicialD;
	}
	public void setFechaInicialD(Date fechaInicialD) {
		this.fechaInicialD = fechaInicialD;
	}
	public Date getFechaFinalD() {
		return fechaFinalD;
	}
	public void setFechaFinalD(Date fechaFinalD) {
		this.fechaFinalD = fechaFinalD;
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaInicial = sdf.format(new Date(System.currentTimeMillis()));
		this.fechaFinal = sdf.format(new Date(System.currentTimeMillis()));
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	/**
	 * @return the codigoResultado
	 */
	public String getCodigoResultado() {
		return codigoResultado;
	}
	/**
	 * @param codigoResultado the codigoResultado to set  
	 */
	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}
	
	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}
	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

}