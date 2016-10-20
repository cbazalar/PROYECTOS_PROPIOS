package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECCodigoVentaOperaSearchForm extends BaseSearchForm{
	
	private static final long serialVersionUID = -3673725099499846231L;
	
	private String codigoPais;
	private String codigoOperacion[];
	private String codigoTipoOperacion[];
	private String codigoVenta;
	private String tipoOferta;
	private String codigoCatalogo;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String tipoBusqueda;
	
	private String lineaDefecto;
	private String lineaMaxima;
	
	
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

	public String getTipoBusqueda() {
		return tipoBusqueda;
	}

	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}

	public String getLineaDefecto() {
		return lineaDefecto;
	}

	public void setLineaDefecto(String lineaDefecto) {
		this.lineaDefecto = lineaDefecto;
	}

	public String getLineaMaxima() {
		return lineaMaxima;
	}

	public void setLineaMaxima(String lineaMaxima) {
		this.lineaMaxima = lineaMaxima;
	}
}
