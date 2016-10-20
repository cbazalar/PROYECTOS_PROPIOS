package biz.belcorp.ssicc.web.spusicc.fdv.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

@SuppressWarnings("rawtypes")
public class MantenimientoParametroProcesoFDVForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private String codigoProceso;
	private String codigoPais;
	private String nombreProceso;
	private String campanyaProceso;
	private String anyoProceso;
	private String[] codigoParametroList = {};
	private String[] valorParametroList = {};
	
	
	private List parametros;
	
	public MantenimientoParametroProcesoFDVForm()
	{
		this.parametros = new ArrayList();
	}
	
	/**
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}
	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
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
	 * @return the nombreProceso
	 */
	public String getNombreProceso() {
		return nombreProceso;
	}
	/**
	 * @param nombreProceso the nombreProceso to set
	 */
	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}
	/**
	 * @return the codigoParametroList
	 */
	public String[] getCodigoParametroList() {
		return codigoParametroList;
	}
	/**
	 * @param codigoParametroList the codigoParametroList to set
	 */
	public void setCodigoParametroList(String[] codigoParametroList) {
		this.codigoParametroList = codigoParametroList;
	}
	/**
	 * @return the valorParametroList
	 */
	public String[] getValorParametroList() {
		return valorParametroList;
	}
	/**
	 * @param valorParametroList the valorParametroList to set
	 */
	public void setValorParametroList(String[] valorParametroList) {
		this.valorParametroList = valorParametroList;
	}

	/**
	 * @return the parametros
	 */
	public List getParametros() {
		return parametros;
	}

	/**
	 * @param parametros the parametros to set
	 */
	public void setParametros(List parametros) {
		this.parametros = parametros;
	}

	/**
	 * @return the campanyaProceso
	 */
	public String getCampanyaProceso() {
		return campanyaProceso;
	}

	/**
	 * @param campanyaProceso the campanyaProceso to set
	 */
	public void setCampanyaProceso(String campanyaProceso) {
		this.campanyaProceso = campanyaProceso;
	}

	/**
	 * @return the anyoProceso
	 */
	public String getAnyoProceso() {
		return anyoProceso;
	}

	/**
	 * @param anyoProceso the anyoProceso to set
	 */
	public void setAnyoProceso(String anyoProceso) {
		this.anyoProceso = anyoProceso;
	}	
}