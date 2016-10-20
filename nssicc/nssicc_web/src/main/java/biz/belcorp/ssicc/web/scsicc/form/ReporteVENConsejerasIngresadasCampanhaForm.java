package biz.belcorp.ssicc.web.scsicc.form;
import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReporteVENConsejerasIngresadasCampanhaForm extends BaseReporteForm	implements Serializable{

	private static final long serialVersionUID = 7685987460650442631L;

	private String codigoPais;

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;

	private String codigoPeriodo;

	private String codigoRegion;

	private String descripcionRegion;

	private String codigoZona;

	private String descripcionZona;

	private String codigoSeccion;

	private String descripcionSeccion;

	private String codigoTerritorio;

	private String descripcionTerritorio;

	private String []codigoTipoCliente;
	
	private String []codigoSubTipoCliente;

	
	
	/**
	 * Valores por default
	 */
	public ReporteVENConsejerasIngresadasCampanhaForm(){
		this.codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		this.codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		this.codigoRegion = null;
		this.codigoZona = null;
		this.codigoTerritorio = null;
		this.codigoSeccion = null;	
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
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}



	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}



	/**
	 * @return the descripcionMarca
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}



	/**
	 * @param descripcionMarca the descripcionMarca to set
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}



	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}



	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}



	/**
	 * @return the descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}



	/**
	 * @param descripcionCanal the descripcionCanal to set
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
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
	public String getCodigoRegion() {
		return codigoRegion;
	}



	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}



	/**
	 * @return the descripcionRegion
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}



	/**
	 * @param descripcionRegion the descripcionRegion to set
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}



	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}



	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}



	/**
	 * @return the descripcionZona
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}



	/**
	 * @param descripcionZona the descripcionZona to set
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}



	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}



	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}



	/**
	 * @return the descripcionSeccion
	 */
	public String getDescripcionSeccion() {
		return descripcionSeccion;
	}



	/**
	 * @param descripcionSeccion the descripcionSeccion to set
	 */
	public void setDescripcionSeccion(String descripcionSeccion) {
		this.descripcionSeccion = descripcionSeccion;
	}



	/**
	 * @return the codigoTerritorio
	 */
	public String getCodigoTerritorio() {
		return codigoTerritorio;
	}



	/**
	 * @param codigoTerritorio the codigoTerritorio to set
	 */
	public void setCodigoTerritorio(String codigoTerritorio) {
		this.codigoTerritorio = codigoTerritorio;
	}



	/**
	 * @return the descripcionTerritorio
	 */
	public String getDescripcionTerritorio() {
		return descripcionTerritorio;
	}



	/**
	 * @param descripcionTerritorio the descripcionTerritorio to set
	 */
	public void setDescripcionTerritorio(String descripcionTerritorio) {
		this.descripcionTerritorio = descripcionTerritorio;
	}



	/**
	 * @return the codigoTipoCliente
	 */
	public String[] getCodigoTipoCliente() {
		return codigoTipoCliente;
	}



	/**
	 * @param codigoTipoCliente the codigoTipoCliente to set
	 */
	public void setCodigoTipoCliente(String[] codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}



	/**
	 * @return the codigoSubTipoCliente
	 */
	public String[] getCodigoSubTipoCliente() {
		return codigoSubTipoCliente;
	}



	/**
	 * @param codigoSubTipoCliente the codigoSubTipoCliente to set
	 */
	public void setCodigoSubTipoCliente(String[] codigoSubTipoCliente) {
		this.codigoSubTipoCliente = codigoSubTipoCliente;
	}
	
	
	
	
}


