package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoRECCodigoVentaOperaForm extends BaseEditForm{
	
	private static final long serialVersionUID = -1653892220807158518L;
	
	private String codigoPais;
	private String codigoOperacion[];
	private String codigoTipoOperacion[];
	private String codigoVenta;
	private String tipoOferta;
	private String codigoCatalogo;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private UploadedFile codigoVentaFile; // objeto que se utilizara para el upload
	
	private String codigoTipoOperacionModifica;
	private String codigoVentaModifica; 
	

	public String getCodigoPais() {
		return codigoPais;
	}
	
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	public String[] getCodigoOperacion() {
		return codigoOperacion;
	}
	
	public void setCodigoOperacion(String[] codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	
	public String[] getCodigoTipoOperacion() {
		return codigoTipoOperacion;
	}

	public void setCodigoTipoOperacion(String[] codigoTipoOperacion) {
		this.codigoTipoOperacion = codigoTipoOperacion;
	}

	public String getCodigoVenta() {
		return codigoVenta;
	}
	
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	
	public String getTipoOferta() {
		return tipoOferta;
	}
	
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}
	
	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}
	
	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}

	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	public String getCodigoTipoOperacionModifica() {
		return codigoTipoOperacionModifica;
	}

	public void setCodigoTipoOperacionModifica(String codigoTipoOperacionModifica) {
		this.codigoTipoOperacionModifica = codigoTipoOperacionModifica;
	}

	public String getCodigoVentaModifica() {
		return codigoVentaModifica;
	}

	public void setCodigoVentaModifica(String codigoVentaModifica) {
		this.codigoVentaModifica = codigoVentaModifica;
	}

	public UploadedFile getCodigoVentaFile() {
		return codigoVentaFile;
	}

	public void setCodigoVentaFile(UploadedFile codigoVentaFile) {
		this.codigoVentaFile = codigoVentaFile;
	}
	
}
