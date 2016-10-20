package biz.belcorp.ssicc.web.framework.base.form;

import java.util.List;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;



/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseSearchForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 */
public class BaseInterfazForm extends BaseSearchForm {
	
    private static final long serialVersionUID = -353653178148455631L;
	
    private String codigoInterfaz;
    private String codigoSistema;
    private String codigoProcesoBatch;
    private String observaciones;
	private String[] listaInterfaces;
	private String[] listaInterfacesSeleccionadas;
	private String descripcion;
	private String numeroLote;
	
	private Interfaz interfaz;
	
	private List<Interfaz> listaInterfazArchivos;
	private List<Interfaz> listaSeleccionadas;
	
	private String checkAllListaInterfaces;
	
	/**
	 * @return the listaInterfaces
	 */
	public String[] getListaInterfaces() {
		return listaInterfaces;
	}

	/**
	 * @param listaInterfaces the listaInterfaces to set
	 */
	public void setListaInterfaces(String[] listaInterfaces) {
		this.listaInterfaces = listaInterfaces;
	}

	/**
	 * @return the listaInterfacesSeleccionadas
	 */
	public String[] getListaInterfacesSeleccionadas() {
		return listaInterfacesSeleccionadas;
	}

	/**
	 * @param listaInterfacesSeleccionadas the listaInterfacesSeleccionadas to set
	 */
	public void setListaInterfacesSeleccionadas(
			String[] listaInterfacesSeleccionadas) {
		this.listaInterfacesSeleccionadas = listaInterfacesSeleccionadas;
	}

	/**
	 * @return the codigoInterfaz
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}

	/**
	 * @param codigoInterfaz the codigoInterfaz to set
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}

	/**
	 * @return the codigoSistema
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema the codigoSistema to set
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return the codigoProcesoBatch
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}

	/**
	 * @param codigoProcesoBatch the codigoProcesoBatch to set
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}

	
	
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the listaInterfazArchivos
	 */
	public List<Interfaz> getListaInterfazArchivos() {
		return listaInterfazArchivos;
	}

	/**
	 * @param listaInterfazArchivos the listaInterfazArchivos to set
	 */
	public void setListaInterfazArchivos(List<Interfaz> listaInterfazArchivos) {
		this.listaInterfazArchivos = listaInterfazArchivos;
	}

	/**
	 * @return the interfaz
	 */
	public Interfaz getInterfaz() {
		return interfaz;
	}

	/**
	 * @param interfaz the interfaz to set
	 */
	public void setInterfaz(Interfaz interfaz) {
		this.interfaz = interfaz;
	}

	/**
	 * @return the listaSeleccionadas
	 */
	public List<Interfaz> getListaSeleccionadas() {
		return listaSeleccionadas;
	}

	/**
	 * @param listaSeleccionadas the listaSeleccionadas to set
	 */
	public void setListaSeleccionadas(List<Interfaz> listaSeleccionadas) {
		this.listaSeleccionadas = listaSeleccionadas;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	 * @return the checkAllListaInterfaces
	 */
	public String getCheckAllListaInterfaces() {
		return checkAllListaInterfaces;
	}

	/**
	 * @param checkAllListaInterfaces the checkAllListaInterfaces to set
	 */
	public void setCheckAllListaInterfaces(String checkAllListaInterfaces) {
		this.checkAllListaInterfaces = checkAllListaInterfaces;
	}
	
	
		
}