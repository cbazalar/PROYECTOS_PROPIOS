/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;

/**
 * @author Sigcomt
 *
 */
public class ProcesoCCCCargarCADDocumentoLegalMasivosForm extends BaseCargaArchivoForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2602041694093271296L;
	
	private String codigoPais;
	private String codigoSociedad;
	private String tipoDocumentoLegal;
	private String numeroLote;
	private String codigoUsuario;
	private String codigoModulo;
	private String codigoProceso;
	private String cantidadRegistrosCargados;
	private String importeRegistrosCargados;
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
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad the codigoSociedad to set
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	/**
	 * @return the tipoDocumentoLegal
	 */
	public String getTipoDocumentoLegal() {
		return tipoDocumentoLegal;
	}
	/**
	 * @param tipoDocumentoLegal the tipoDocumentoLegal to set
	 */
	public void setTipoDocumentoLegal(String tipoDocumentoLegal) {
		this.tipoDocumentoLegal = tipoDocumentoLegal;
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
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	/**
	 * @return the codigoModulo
	 */
	public String getCodigoModulo() {
		return codigoModulo;
	}
	/**
	 * @param codigoModulo the codigoModulo to set
	 */
	public void setCodigoModulo(String codigoModulo) {
		this.codigoModulo = codigoModulo;
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
	 * @return the cantidadRegistrosCargados
	 */
	public String getCantidadRegistrosCargados() {
		return cantidadRegistrosCargados;
	}
	/**
	 * @param cantidadRegistrosCargados the cantidadRegistrosCargados to set
	 */
	public void setCantidadRegistrosCargados(String cantidadRegistrosCargados) {
		this.cantidadRegistrosCargados = cantidadRegistrosCargados;
	}
	/**
	 * @return the importeRegistrosCargados
	 */
	public String getImporteRegistrosCargados() {
		return importeRegistrosCargados;
	}
	/**
	 * @param importeRegistrosCargados the importeRegistrosCargados to set
	 */
	public void setImporteRegistrosCargados(String importeRegistrosCargados) {
		this.importeRegistrosCargados = importeRegistrosCargados;
	}
				
}
