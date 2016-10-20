/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * @author Sigcomt
 *
 */
public class MantenimientoRECOperacionesReclamoForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4360782808662109385L;
	
	private String codigoPais;
	private String oidOperacionReclamo;
	private String codigoOperacion;
	private String descripcion;
	private String indAnulacion;
	private String indDevFisicoFact;
	private String nroCampHistoria;
	private String tipoPrecio;
	private String tipoPrecioEnvia;
	private String codigoMotivoBloqueo;
	private String indFaltanteMercaderia;
	private String indEnvia;
	private String tipoSolicitud;
	private String indEnviaGeneraDev;
	private String indDevuelve;
	private String tipoSolicitudGenera;
	private String almacen;
	private String indDevuelveGeneraEnv;
	private String codigoMovimientoAlmacen;
	
	//Datos Tipo Operacion
	private String codigoTipoOperacion;
	private String indCampReferenciaUnica;
	private String numDiasHaciaAtras;
	private String indInfoBelcorpNoticias;
	private String indDevuelveEstaFactura;
	private String indEnviaEstaFactura;
	private String codigoMotivoRechazo;
	private String descMotivoRechazo;
	
	protected String[] selectedItemsTiposOperaciones = {};
	protected String selectedItemTiposOperaciones = null;
	
	private String indicadorNuevoTipoOperacion;

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
	 * @return the oidOperacionReclamo
	 */
	public String getOidOperacionReclamo() {
		return oidOperacionReclamo;
	}

	/**
	 * @param oidOperacionReclamo the oidOperacionReclamo to set
	 */
	public void setOidOperacionReclamo(String oidOperacionReclamo) {
		this.oidOperacionReclamo = oidOperacionReclamo;
	}

	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
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
	 * @return the indAnulacion
	 */
	public String getIndAnulacion() {
		return indAnulacion;
	}

	/**
	 * @param indAnulacion the indAnulacion to set
	 */
	public void setIndAnulacion(String indAnulacion) {
		this.indAnulacion = indAnulacion;
	}

	/**
	 * @return the indDevFisicoFact
	 */
	public String getIndDevFisicoFact() {
		return indDevFisicoFact;
	}

	/**
	 * @param indDevFisicoFact the indDevFisicoFact to set
	 */
	public void setIndDevFisicoFact(String indDevFisicoFact) {
		this.indDevFisicoFact = indDevFisicoFact;
	}

	/**
	 * @return the nroCampHistoria
	 */
	public String getNroCampHistoria() {
		return nroCampHistoria;
	}

	/**
	 * @param nroCampHistoria the nroCampHistoria to set
	 */
	public void setNroCampHistoria(String nroCampHistoria) {
		this.nroCampHistoria = nroCampHistoria;
	}

	/**
	 * @return the tipoPrecio
	 */
	public String getTipoPrecio() {
		return tipoPrecio;
	}

	/**
	 * @param tipoPrecio the tipoPrecio to set
	 */
	public void setTipoPrecio(String tipoPrecio) {
		this.tipoPrecio = tipoPrecio;
	}

	/**
	 * @return the tipoPrecioEnvia
	 */
	public String getTipoPrecioEnvia() {
		return tipoPrecioEnvia;
	}

	/**
	 * @param tipoPrecioEnvia the tipoPrecioEnvia to set
	 */
	public void setTipoPrecioEnvia(String tipoPrecioEnvia) {
		this.tipoPrecioEnvia = tipoPrecioEnvia;
	}

	/**
	 * @return the codigoMotivoBloqueo
	 */
	public String getCodigoMotivoBloqueo() {
		return codigoMotivoBloqueo;
	}

	/**
	 * @param codigoMotivoBloqueo the codigoMotivoBloqueo to set
	 */
	public void setCodigoMotivoBloqueo(String codigoMotivoBloqueo) {
		this.codigoMotivoBloqueo = codigoMotivoBloqueo;
	}

	/**
	 * @return the indFaltanteMercaderia
	 */
	public String getIndFaltanteMercaderia() {
		return indFaltanteMercaderia;
	}

	/**
	 * @param indFaltanteMercaderia the indFaltanteMercaderia to set
	 */
	public void setIndFaltanteMercaderia(String indFaltanteMercaderia) {
		this.indFaltanteMercaderia = indFaltanteMercaderia;
	}

	/**
	 * @return the indEnvia
	 */
	public String getIndEnvia() {
		return indEnvia;
	}

	/**
	 * @param indEnvia the indEnvia to set
	 */
	public void setIndEnvia(String indEnvia) {
		this.indEnvia = indEnvia;
	}

	/**
	 * @return the tipoSolicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * @param tipoSolicitud the tipoSolicitud to set
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * @return the indEnviaGeneraDev
	 */
	public String getIndEnviaGeneraDev() {
		return indEnviaGeneraDev;
	}

	/**
	 * @param indEnviaGeneraDev the indEnviaGeneraDev to set
	 */
	public void setIndEnviaGeneraDev(String indEnviaGeneraDev) {
		this.indEnviaGeneraDev = indEnviaGeneraDev;
	}

	/**
	 * @return the indDevuelve
	 */
	public String getIndDevuelve() {
		return indDevuelve;
	}

	/**
	 * @param indDevuelve the indDevuelve to set
	 */
	public void setIndDevuelve(String indDevuelve) {
		this.indDevuelve = indDevuelve;
	}

	/**
	 * @return the tipoSolicitudGenera
	 */
	public String getTipoSolicitudGenera() {
		return tipoSolicitudGenera;
	}

	/**
	 * @param tipoSolicitudGenera the tipoSolicitudGenera to set
	 */
	public void setTipoSolicitudGenera(String tipoSolicitudGenera) {
		this.tipoSolicitudGenera = tipoSolicitudGenera;
	}

	/**
	 * @return the almacen
	 */
	public String getAlmacen() {
		return almacen;
	}

	/**
	 * @param almacen the almacen to set
	 */
	public void setAlmacen(String almacen) {
		this.almacen = almacen;
	}

	/**
	 * @return the indDevuelveGeneraEnv
	 */
	public String getIndDevuelveGeneraEnv() {
		return indDevuelveGeneraEnv;
	}

	/**
	 * @param indDevuelveGeneraEnv the indDevuelveGeneraEnv to set
	 */
	public void setIndDevuelveGeneraEnv(String indDevuelveGeneraEnv) {
		this.indDevuelveGeneraEnv = indDevuelveGeneraEnv;
	}

	/**
	 * @return the codigoMovimientoAlmacen
	 */
	public String getCodigoMovimientoAlmacen() {
		return codigoMovimientoAlmacen;
	}

	/**
	 * @param codigoMovimientoAlmacen the codigoMovimientoAlmacen to set
	 */
	public void setCodigoMovimientoAlmacen(String codigoMovimientoAlmacen) {
		this.codigoMovimientoAlmacen = codigoMovimientoAlmacen;
	}

	/**
	 * @return the codigoTipoOperacion
	 */
	public String getCodigoTipoOperacion() {
		return codigoTipoOperacion;
	}

	/**
	 * @param codigoTipoOperacion the codigoTipoOperacion to set
	 */
	public void setCodigoTipoOperacion(String codigoTipoOperacion) {
		this.codigoTipoOperacion = codigoTipoOperacion;
	}

	/**
	 * @return the indCampReferenciaUnica
	 */
	public String getIndCampReferenciaUnica() {
		return indCampReferenciaUnica;
	}

	/**
	 * @param indCampReferenciaUnica the indCampReferenciaUnica to set
	 */
	public void setIndCampReferenciaUnica(String indCampReferenciaUnica) {
		this.indCampReferenciaUnica = indCampReferenciaUnica;
	}

	/**
	 * @return the numDiasHaciaAtras
	 */
	public String getNumDiasHaciaAtras() {
		return numDiasHaciaAtras;
	}

	/**
	 * @param numDiasHaciaAtras the numDiasHaciaAtras to set
	 */
	public void setNumDiasHaciaAtras(String numDiasHaciaAtras) {
		this.numDiasHaciaAtras = numDiasHaciaAtras;
	}

	/**
	 * @return the indInfoBelcorpNoticias
	 */
	public String getIndInfoBelcorpNoticias() {
		return indInfoBelcorpNoticias;
	}

	/**
	 * @param indInfoBelcorpNoticias the indInfoBelcorpNoticias to set
	 */
	public void setIndInfoBelcorpNoticias(String indInfoBelcorpNoticias) {
		this.indInfoBelcorpNoticias = indInfoBelcorpNoticias;
	}

	/**
	 * @return the indDevuelveEstaFactura
	 */
	public String getIndDevuelveEstaFactura() {
		return indDevuelveEstaFactura;
	}

	/**
	 * @param indDevuelveEstaFactura the indDevuelveEstaFactura to set
	 */
	public void setIndDevuelveEstaFactura(String indDevuelveEstaFactura) {
		this.indDevuelveEstaFactura = indDevuelveEstaFactura;
	}

	/**
	 * @return the indEnviaEstaFactura
	 */
	public String getIndEnviaEstaFactura() {
		return indEnviaEstaFactura;
	}

	/**
	 * @param indEnviaEstaFactura the indEnviaEstaFactura to set
	 */
	public void setIndEnviaEstaFactura(String indEnviaEstaFactura) {
		this.indEnviaEstaFactura = indEnviaEstaFactura;
	}

	/**
	 * @return the codigoMotivoRechazo
	 */
	public String getCodigoMotivoRechazo() {
		return codigoMotivoRechazo;
	}

	/**
	 * @param codigoMotivoRechazo the codigoMotivoRechazo to set
	 */
	public void setCodigoMotivoRechazo(String codigoMotivoRechazo) {
		this.codigoMotivoRechazo = codigoMotivoRechazo;
	}

	/**
	 * @return the descMotivoRechazo
	 */
	public String getDescMotivoRechazo() {
		return descMotivoRechazo;
	}

	/**
	 * @param descMotivoRechazo the descMotivoRechazo to set
	 */
	public void setDescMotivoRechazo(String descMotivoRechazo) {
		this.descMotivoRechazo = descMotivoRechazo;
	}

	/**
	 * @return the selectedItemsTiposOperaciones
	 */
	public String[] getSelectedItemsTiposOperaciones() {
		return selectedItemsTiposOperaciones;
	}

	/**
	 * @param selectedItemsTiposOperaciones the selectedItemsTiposOperaciones to set
	 */
	public void setSelectedItemsTiposOperaciones(
			String[] selectedItemsTiposOperaciones) {
		this.selectedItemsTiposOperaciones = selectedItemsTiposOperaciones;
	}

	/**
	 * @return the selectedItemTiposOperaciones
	 */
	public String getSelectedItemTiposOperaciones() {
		return selectedItemTiposOperaciones;
	}

	/**
	 * @param selectedItemTiposOperaciones the selectedItemTiposOperaciones to set
	 */
	public void setSelectedItemTiposOperaciones(String selectedItemTiposOperaciones) {
		this.selectedItemTiposOperaciones = selectedItemTiposOperaciones;
	}

	/**
	 * @return the indicadorNuevoTipoOperacion
	 */
	public String getIndicadorNuevoTipoOperacion() {
		return indicadorNuevoTipoOperacion;
	}

	/**
	 * @param indicadorNuevoTipoOperacion the indicadorNuevoTipoOperacion to set
	 */
	public void setIndicadorNuevoTipoOperacion(String indicadorNuevoTipoOperacion) {
		this.indicadorNuevoTipoOperacion = indicadorNuevoTipoOperacion;
	}

}
