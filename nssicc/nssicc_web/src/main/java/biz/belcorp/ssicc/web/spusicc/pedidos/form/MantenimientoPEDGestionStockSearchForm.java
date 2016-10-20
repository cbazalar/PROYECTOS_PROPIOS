package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import org.primefaces.model.UploadedFile;

public class MantenimientoPEDGestionStockSearchForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoCampanha;
	private String codigoVentaPrincipal;
	private String indicadorActivo;
	private UploadedFile codigoVentaFile;
	private UploadedFile codigoSapFile;

	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoVentaPrincipal
	 */
	public String getCodigoVentaPrincipal() {
		return codigoVentaPrincipal;
	}
	/**
	 * @return the codigoCampanha
	 */
	public String getCodigoCampanha() {
		return codigoCampanha;
	}
	/**
	 * @param codigoCampanha the codigoCampanha to set
	 * @struts.validator type="required"
	 */
	public void setCodigoCampanha(String codigoCampanha) {
		this.codigoCampanha = codigoCampanha;
	}
	/**
	 * @param codigoVentaPrincipal the codigoVentaPrincipal to set
	 */
	public void setCodigoVentaPrincipal(String codigoVentaPrincipal) {
		this.codigoVentaPrincipal = codigoVentaPrincipal;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	/**
	 * @return the codigoVentaFile
	 */
	public UploadedFile getCodigoVentaFile() {
		return codigoVentaFile;
	}
	/**
	 * @return the codigoSapFile
	 */
	public UploadedFile getCodigoSapFile() {
		return codigoSapFile;
	}
	/**
	 * @param codigoVentaFile the codigoVentaFile to set
	 */
	public void setCodigoVentaFile(UploadedFile codigoVentaFile) {
		this.codigoVentaFile = codigoVentaFile;
	}
	/**
	 * @param codigoSapFile the codigoSapFile to set
	 */
	public void setCodigoSapFile(UploadedFile codigoSapFile) {
		this.codigoSapFile = codigoSapFile;
	}
}