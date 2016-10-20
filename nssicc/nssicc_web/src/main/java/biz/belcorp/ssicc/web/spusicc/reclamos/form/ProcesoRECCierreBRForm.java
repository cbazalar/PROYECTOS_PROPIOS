/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoRECCierreBRForm extends BaseSearchForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1423302125866842744L;
	
	private String codigoPais;
	private String numeroLote;
	private String codigoResultadoBR;
	
	//Lista de Resultado BR
	private String[] listaNumeroBr;
	private String[] listaNroRecojo;
	private String[] listaCodigoCliente;
	private String[] listaNombre;
	private String[] listaFlag;
	private String[] listaObservacion;
	
	//Resultado BR
	private String numeroBR;
	private String nroRecojo;
	private String codigoCliente;
	private String nombre;
	private String flag;
	private String observacion;
	private boolean boolNumBr;
	
	private boolean ocultarCampos;
	
	private String indicadorOcultarBoton;
	
	

	/**
	 * @return the numeroBR
	 */
	public String getNumeroBR() {
		return numeroBR;
	}

	/**
	 * @param numeroBR the numeroBR to set
	 */
	public void setNumeroBR(String numeroBR) {
		this.numeroBR = numeroBR;
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
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the codigoResultadoBR
	 */
	public String getCodigoResultadoBR() {
		return codigoResultadoBR;
	}

	/**
	 * @param codigoResultadoBR the codigoResultadoBR to set
	 */
	public void setCodigoResultadoBR(String codigoResultadoBR) {
		this.codigoResultadoBR = codigoResultadoBR;
	}

	/**
	 * @return the listaNumeroBr
	 */
	public String[] getListaNumeroBr() {
		return listaNumeroBr;
	}

	/**
	 * @param listaNumeroBr the listaNumeroBr to set
	 */
	public void setListaNumeroBr(String[] listaNumeroBr) {
		this.listaNumeroBr = listaNumeroBr;
	}

	/**
	 * @return the listaNroRecojo
	 */
	public String[] getListaNroRecojo() {
		return listaNroRecojo;
	}

	/**
	 * @param listaNroRecojo the listaNroRecojo to set
	 */
	public void setListaNroRecojo(String[] listaNroRecojo) {
		this.listaNroRecojo = listaNroRecojo;
	}

	/**
	 * @return the listaCodigoCliente
	 */
	public String[] getListaCodigoCliente() {
		return listaCodigoCliente;
	}

	/**
	 * @param listaCodigoCliente the listaCodigoCliente to set
	 */
	public void setListaCodigoCliente(String[] listaCodigoCliente) {
		this.listaCodigoCliente = listaCodigoCliente;
	}

	/**
	 * @return the listaNombre
	 */
	public String[] getListaNombre() {
		return listaNombre;
	}

	/**
	 * @param listaNombre the listaNombre to set
	 */
	public void setListaNombre(String[] listaNombre) {
		this.listaNombre = listaNombre;
	}

	/**
	 * @return the listaFlag
	 */
	public String[] getListaFlag() {
		return listaFlag;
	}

	/**
	 * @param listaFlag the listaFlag to set
	 */
	public void setListaFlag(String[] listaFlag) {
		this.listaFlag = listaFlag;
	}

	/**
	 * @return the listaObservacion
	 */
	public String[] getListaObservacion() {
		return listaObservacion;
	}

	/**
	 * @param listaObservacion the listaObservacion to set
	 */
	public void setListaObservacion(String[] listaObservacion) {
		this.listaObservacion = listaObservacion;
	}

	/**
	 * @return the ocultarCampos
	 */
	public boolean isOcultarCampos() {
		return ocultarCampos;
	}

	/**
	 * @param ocultarCampos the ocultarCampos to set
	 */
	public void setOcultarCampos(boolean ocultarCampos) {
		this.ocultarCampos = ocultarCampos;
	}

	/**
	 * @return the indicadorOcultarBoton
	 */
	public String getIndicadorOcultarBoton() {
		return indicadorOcultarBoton;
	}

	/**
	 * @param indicadorOcultarBoton the indicadorOcultarBoton to set
	 */
	public void setIndicadorOcultarBoton(String indicadorOcultarBoton) {
		this.indicadorOcultarBoton = indicadorOcultarBoton;
	}

	/**
	 * @return the nroRecojo
	 */
	public String getNroRecojo() {
		return nroRecojo;
	}

	/**
	 * @param nroRecojo the nroRecojo to set
	 */
	public void setNroRecojo(String nroRecojo) {
		this.nroRecojo = nroRecojo;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the boolNumBr
	 */
	public boolean isBoolNumBr() {
		return boolNumBr;
	}

	/**
	 * @param boolNumBr the boolNumBr to set
	 */
	public void setBoolNumBr(boolean boolNumBr) {
		this.boolNumBr = boolNumBr;
	}
	
	
	

}
