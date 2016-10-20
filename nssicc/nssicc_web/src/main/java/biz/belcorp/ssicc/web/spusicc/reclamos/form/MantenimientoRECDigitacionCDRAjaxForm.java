package biz.belcorp.ssicc.web.spusicc.reclamos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoRECDigitacionCDRAjaxForm extends BaseSearchForm {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;


	private String codigoPais;
	private String numeroCDR;
	private String numeroBoletaDespacho;
	private String codigoConsejera;
	private String periodo;
	private String nombreConsejera;
		
	private String[] listaOperacion;
	private String[] listaCUVCambia;
	private String[] listaCantidadCambia;
	private String[] listaMotivo;
	private String[] listaIgualEnvio;
	private String[] listaCUVDesea;
	private String[] listaCantidadDesea;
	
	private String[] listaDescripcionCambia;
	private String[] listaPrecioCambia;
	
	private String strComboOperacion;
	
	private String periodoActivo;
	
	private String[] selectedItemsDelete;
	
	private String indicadorModifica;
	private String indicadorHayRegistros;
	
	private String indicadorExpress;
	
	private String indicadorExpressHidden;
	private String zona;
	
	private String indicadorOnline;
	
	private String indicadorValidaDevolucion;
	private String indicadorValidaFaltantes;
	private String indicadorValidaCambios;
	
	private String indicadorPedidoChequeado;
	private String codigoResultadoChequeo;
	
	/* INI JR PER-SiCC-2012-0304 */
	private String indicadorCDRRechazo;
	private String observacionCDR;
	private String muestraIndicador;
	
	private String direccionDomicilio;
	private String ubicacionGeografica;
	
	/* INI PER-SiCC-2012-0558 */
	private String campana;
	private String fechaPedido;
	private String[] listaIdentSolicPos; 
	private String periodoCDR;
	private String indicadorValCDRLinea ;
	private String excedeDias;
	private String codigoOperacionCorrecto;
	/* FIN PER-SiCC-2012-0558 */
	
	// INI PER-SiCC-2012-0642 
	private String codigoMotivoRechazoDef;
	// FIN PER-SiCC-2012-0642 
	
	private String strComboOperacionDeuda;
	private String strComboMotivoDeuda;
	
	private String[] listaIdentFila;
	
	private String[] listaIdentFilaPadre;
		
	private String indicadorDevolucionOferta;
	
	private String indicadorLinea18;
	
	private String valorDesviacionPrecioUnitarioTrueque;
	
	private String indicadorProductoCambiaIgualDesea;
	
	private String codigoClienteDocumentoReferencia;
	
	private String indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto;
	
	private String indicadorValorAceptaCDR;

	
	/**
	 * @return the listaIdentFilaPadre
	 */
	public String[] getListaIdentFilaPadre() {
		return listaIdentFilaPadre;
	}


	/**
	 * @param listaIdentFilaPadre the listaIdentFilaPadre to set
	 */
	public void setListaIdentFilaPadre(String[] listaIdentFilaPadre) {
		this.listaIdentFilaPadre = listaIdentFilaPadre;
	}


	/**
	 * @return the listaIdentFila
	 */
	public String[] getListaIdentFila() {
		return listaIdentFila;
	}


	/**
	 * @param listaIdentFila the listaIdentFila to set
	 */
	public void setListaIdentFila(String[] listaIdentFila) {
		this.listaIdentFila = listaIdentFila;
	}


	/**
	 * @return the strComboOperacionDeuda
	 */
	public String getStrComboOperacionDeuda() {
		return strComboOperacionDeuda;
	}


	/**
	 * @param strComboOperacionDeuda the strComboOperacionDeuda to set
	 */
	public void setStrComboOperacionDeuda(String strComboOperacionDeuda) {
		this.strComboOperacionDeuda = strComboOperacionDeuda;
	}


	/**
	 * @return the strComboMotivoDeuda
	 */
	public String getStrComboMotivoDeuda() {
		return strComboMotivoDeuda;
	}


	/**
	 * @param strComboMotivoDeuda the strComboMotivoDeuda to set
	 */
	public void setStrComboMotivoDeuda(String strComboMotivoDeuda) {
		this.strComboMotivoDeuda = strComboMotivoDeuda;
	}
	private String usuario;
	
	
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}


	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	/**
	 * @return the codigoMotivoRechazoDef
	 */
	public String getCodigoMotivoRechazoDef() {
		return codigoMotivoRechazoDef;
	}


	/**
	 * @param codigoMotivoRechazoDef the codigoMotivoRechazoDef to set
	 */
	public void setCodigoMotivoRechazoDef(String codigoMotivoRechazoDef) {
		this.codigoMotivoRechazoDef = codigoMotivoRechazoDef;
	}


	/**
	 * @return the muestraIndicador
	 */
	public String getMuestraIndicador() {
		return muestraIndicador;
	}


	/**
	 * @return the excedeDias
	 */
	public String getExcedeDias() {
		return excedeDias;
	}

	/**
	 * @param excedeDias the excedeDias to set
	 */
	public void setExcedeDias(String excedeDias) {
		this.excedeDias = excedeDias;
	}

	/**
	 * @return the codigoOperacionCorrecto
	 */
	public String getCodigoOperacionCorrecto() {
		return codigoOperacionCorrecto;
	}

	/**
	 * @param codigoOperacionCorrecto the codigoOperacionCorrecto to set
	 */
	public void setCodigoOperacionCorrecto(String codigoOperacionCorrecto) {
		this.codigoOperacionCorrecto = codigoOperacionCorrecto;
	}


	/**
	 * @return the indicadorValCDRLinea
	 */
	public String getIndicadorValCDRLinea() {
		return indicadorValCDRLinea;
	}

	/**
	 * @param indicadorValCDRLinea the indicadorValCDRLinea to set
	 */
	public void setIndicadorValCDRLinea(String indicadorValCDRLinea) {
		this.indicadorValCDRLinea = indicadorValCDRLinea;
	}

	/**
	 * @return the periodoCDR
	 */
	public String getPeriodoCDR() {
		return periodoCDR;
	}

	/**
	 * @param periodoCDR the periodoCDR to set
	 */
	public void setPeriodoCDR(String periodoCDR) {
		this.periodoCDR = periodoCDR;
	}

	/**
	 * @return the campana
	 */
	public String getCampana() {
		return campana;
	}

	/**
	 * @param campana the campana to set
	 */
	public void setCampana(String campana) {
		this.campana = campana;
	}

	/**
	 * @return the fechaPedido
	 */
	public String getFechaPedido() {
		return fechaPedido;
	}

	/**
	 * @param fechaPedido the fechaPedido to set
	 */
	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	/**
	 * @return the listaIdentSolicPos
	 */
	public String[] getListaIdentSolicPos() {
		return listaIdentSolicPos;
	}

	/**
	 * @param listaIdentSolicPos the listaIdentSolicPos to set
	 */
	public void setListaIdentSolicPos(String[] listaIdentSolicPos) {
		this.listaIdentSolicPos = listaIdentSolicPos;
	}

	/**
	 * @param muestraIndicador the muestraIndicador to set
	 */
	public void setMuestraIndicador(String muestraIndicador) {
		this.muestraIndicador = muestraIndicador;
	}

	/**
	 * @return the indicadorCDRRechazo
	 */
	public String getIndicadorCDRRechazo() {
		return indicadorCDRRechazo;
	}

	/**
	 * @param indicadorCDRRechazo the indicadorCDRRechazo to set
	 */
	public void setIndicadorCDRRechazo(String indicadorCDRRechazo) {
		this.indicadorCDRRechazo = indicadorCDRRechazo;
	}

	/**
	 * @return the observacionCDR
	 */
	public String getObservacionCDR() {
		return observacionCDR;
	}

	/**
	 * @param observacionCDR the observacionCDR to set
	 */
	public void setObservacionCDR(String observacionCDR) {
		this.observacionCDR = observacionCDR;
	}
	/* FIN JR PER-SiCC-2012-0304 */

	/**
	 * @return the indicadorOnline
	 */
	public String getIndicadorOnline() {
		return indicadorOnline;
	}

	/**
	 * @param indicadorOnline the indicadorOnline to set
	 */
	public void setIndicadorOnline(String indicadorOnline) {
		this.indicadorOnline = indicadorOnline;
	}

	/**
	 * @return the periodoActivo
	 */
	public String getPeriodoActivo() {
		return periodoActivo;
	}

	/**
	 * @param periodoActivo the periodoActivo to set
	 */
	public void setPeriodoActivo(String periodoActivo) {
		this.periodoActivo = periodoActivo;
	}

	/**
	 * @return the strComboOperacion
	 */
	public String getStrComboOperacion() {
		return strComboOperacion;
	}

	/**
	 * @param strComboOperacion the strComboOperacion to set
	 */
	public void setStrComboOperacion(String strComboOperacion) {
		this.strComboOperacion = strComboOperacion;
	}

	/**
	 * @return the listaDescripcionCambia
	 */
	public String[] getListaDescripcionCambia() {
		return listaDescripcionCambia;
	}

	/**
	 * @param listaDescripcionCambia the listaDescripcionCambia to set
	 */
	public void setListaDescripcionCambia(String[] listaDescripcionCambia) {
		this.listaDescripcionCambia = listaDescripcionCambia;
	}

	/**
	 * @return the listaPrecioCambia
	 */
	public String[] getListaPrecioCambia() {
		return listaPrecioCambia;
	}

	/**
	 * @param listaPrecioCambia the listaPrecioCambia to set
	 */
	public void setListaPrecioCambia(String[] listaPrecioCambia) {
		this.listaPrecioCambia = listaPrecioCambia;
	}

	/**
	 * @return the strComboMotivo
	 */
	public String getStrComboMotivo() {
		return strComboMotivo;
	}

	/**
	 * @param strComboMotivo the strComboMotivo to set
	 */
	public void setStrComboMotivo(String strComboMotivo) {
		this.strComboMotivo = strComboMotivo;
	}
	private String strComboMotivo;
	
	
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
	 * @return the numeroCDR
	 */
	public String getNumeroCDR() {
		return numeroCDR;
	}
	/**
	 * @param numeroCDR the numeroCDR to set
	 * @struts.validator type="required"
	 */
	public void setNumeroCDR(String numeroCDR) {
		this.numeroCDR = numeroCDR;
	}
	/**
	 * @return the numeroBoletaDespacho
	 */
	public String getNumeroBoletaDespacho() {
		return numeroBoletaDespacho;
	}
	/**
	 * @param numeroBoletaDespacho the numeroBoletaDespacho to set
	 * @struts.validator type="required"
	 */
	public void setNumeroBoletaDespacho(String numeroBoletaDespacho) {
		this.numeroBoletaDespacho = numeroBoletaDespacho;
	}
	/**
	 * @return the codigoConsejera
	 */
	public String getCodigoConsejera() {
		return codigoConsejera;
	}
	/**
	 * @param codigoConsejera the codigoConsejera to set
	 * @struts.validator type="required"
	 */
	public void setCodigoConsejera(String codigoConsejera) {
		this.codigoConsejera = codigoConsejera;
	}
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	/**
	 * @param periodo the periodo to set
	 * @struts.validator type="required"
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	/**
	 * @return the listaOperacion
	 */
	public String[] getListaOperacion() {
		return listaOperacion;
	}
	/**
	 * @param listaOperacion the listaOperacion to set
	 */
	public void setListaOperacion(String[] listaOperacion) {
		this.listaOperacion = listaOperacion;
	}
	/**
	 * @return the listaCUVCambia
	 */
	public String[] getListaCUVCambia() {
		return listaCUVCambia;
	}
	/**
	 * @param listaCUVCambia the listaCUVCambia to set
	 */
	public void setListaCUVCambia(String[] listaCUVCambia) {
		this.listaCUVCambia = listaCUVCambia;
	}
	/**
	 * @return the listaCantidadCambia
	 */
	public String[] getListaCantidadCambia() {
		return listaCantidadCambia;
	}
	/**
	 * @param listaCantidadCambia the listaCantidadCambia to set
	 */
	public void setListaCantidadCambia(String[] listaCantidadCambia) {
		this.listaCantidadCambia = listaCantidadCambia;
	}
	/**
	 * @return the listaMotivo
	 */
	public String[] getListaMotivo() {
		return listaMotivo;
	}
	/**
	 * @param listaMotivo the listaMotivo to set
	 */
	public void setListaMotivo(String[] listaMotivo) {
		this.listaMotivo = listaMotivo;
	}
	/**
	 * @return the listaIgualEnvio
	 */
	public String[] getListaIgualEnvio() {
		return listaIgualEnvio;
	}
	/**
	 * @param listaIgualEnvio the listaIgualEnvio to set
	 */
	public void setListaIgualEnvio(String[] listaIgualEnvio) {
		this.listaIgualEnvio = listaIgualEnvio;
	}
	/**
	 * @return the listaCUVDesea
	 */
	public String[] getListaCUVDesea() {
		return listaCUVDesea;
	}
	/**
	 * @param listaCUVDesea the listaCUVDesea to set
	 */
	public void setListaCUVDesea(String[] listaCUVDesea) {
		this.listaCUVDesea = listaCUVDesea;
	}
	/**
	 * @return the listaCantidadDesea
	 */
	public String[] getListaCantidadDesea() {
		return listaCantidadDesea;
	}
	/**
	 * @param listaCantidadDesea the listaCantidadDesea to set
	 */
	public void setListaCantidadDesea(String[] listaCantidadDesea) {
		this.listaCantidadDesea = listaCantidadDesea;
	}
	/**
	 * @return the nombreConsejera
	 */
	public String getNombreConsejera() {
		return nombreConsejera;
	}

	/**
	 * @param nombreConsejera the nombreConsejera to set
	 */
	public void setNombreConsejera(String nombreConsejera) {
		this.nombreConsejera = nombreConsejera;
	}

	/**
	 * @return the selectedItemsDelete
	 */
	public String[] getSelectedItemsDelete() {
		return selectedItemsDelete;
	}

	/**
	 * @param selectedItemsDelete the selectedItemsDelete to set
	 */
	public void setSelectedItemsDelete(String[] selectedItemsDelete) {
		this.selectedItemsDelete = selectedItemsDelete;
	}

	/**
	 * @return the indicadorModifica
	 */
	public String getIndicadorModifica() {
		return indicadorModifica;
	}

	/**
	 * @param indicadorModifica the indicadorModifica to set
	 */
	public void setIndicadorModifica(String indicadorModifica) {
		this.indicadorModifica = indicadorModifica;
	}

	/**
	 * @return the indicadorHayRegistros
	 */
	public String getIndicadorHayRegistros() {
		return indicadorHayRegistros;
	}

	/**
	 * @param indicadorHayRegistros the indicadorHayRegistros to set
	 */
	public void setIndicadorHayRegistros(String indicadorHayRegistros) {
		this.indicadorHayRegistros = indicadorHayRegistros;
	}	

	/**
	 * @return the indicadorExpress
	 */
	public String getIndicadorExpress() {
		return indicadorExpress;
	}

	/**
	 * @param indicadorExpress the indicadorExpress to set
	 */
	public void setIndicadorExpress(String indicadorExpress) {
		this.indicadorExpress = indicadorExpress;
	}

	/**
	 * @return the indicadorExpressHidden
	 */
	public String getIndicadorExpressHidden() {
		return indicadorExpressHidden;
	}

	/**
	 * @param indicadorExpressHidden the indicadorExpressHidden to set
	 */
	public void setIndicadorExpressHidden(String indicadorExpressHidden) {
		this.indicadorExpressHidden = indicadorExpressHidden;
	}

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the indicadorValidaDevolucion
	 */
	public String getIndicadorValidaDevolucion() {
		return indicadorValidaDevolucion;
	}

	/**
	 * @param indicadorValidaDevolucion the indicadorValidaDevolucion to set
	 */
	public void setIndicadorValidaDevolucion(String indicadorValidaDevolucion) {
		this.indicadorValidaDevolucion = indicadorValidaDevolucion;
	}

	/**
	 * @return the indicadorPedidoChequeado
	 */
	public String getIndicadorPedidoChequeado() {
		return indicadorPedidoChequeado;
	}

	/**
	 * @param indicadorPedidoChequeado the indicadorPedidoChequeado to set
	 */
	public void setIndicadorPedidoChequeado(String indicadorPedidoChequeado) {
		this.indicadorPedidoChequeado = indicadorPedidoChequeado;
	}

	/**
	 * @return the codigoResultadoChequeo
	 */
	public String getCodigoResultadoChequeo() {
		return codigoResultadoChequeo;
	}

	/**
	 * @param codigoResultadoChequeo the codigoResultadoChequeo to set
	 */
	public void setCodigoResultadoChequeo(String codigoResultadoChequeo) {
		this.codigoResultadoChequeo = codigoResultadoChequeo;
	}

	/**
	 * @return the direccionDomicilio
	 */
	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}

	/**
	 * @param direccionDomicilio the direccionDomicilio to set
	 */
	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}

	/**
	 * @return the ubicacionGeografica
	 */
	public String getUbicacionGeografica() {
		return ubicacionGeografica;
	}

	/**
	 * @param ubicacionGeografica the ubicacionGeografica to set
	 */
	public void setUbicacionGeografica(String ubicacionGeografica) {
		this.ubicacionGeografica = ubicacionGeografica;
	}


	/**
	 * @return the indicadorDevolucionOferta
	 */
	public String getIndicadorDevolucionOferta() {
		return indicadorDevolucionOferta;
	}


	/**
	 * @param indicadorDevolucionOferta the indicadorDevolucionOferta to set
	 */
	public void setIndicadorDevolucionOferta(String indicadorDevolucionOferta) {
		this.indicadorDevolucionOferta = indicadorDevolucionOferta;
	}


	/**
	 * @return the indicadorLinea18
	 */
	public String getIndicadorLinea18() {
		return indicadorLinea18;
	}


	/**
	 * @param indicadorLinea18 the indicadorLinea18 to set
	 */
	public void setIndicadorLinea18(String indicadorLinea18) {
		this.indicadorLinea18 = indicadorLinea18;
	}


	/**
	 * @return the valorDesviacionPrecioUnitarioTrueque
	 */
	public String getValorDesviacionPrecioUnitarioTrueque() {
		return valorDesviacionPrecioUnitarioTrueque;
	}


	/**
	 * @param valorDesviacionPrecioUnitarioTrueque the valorDesviacionPrecioUnitarioTrueque to set
	 */
	public void setValorDesviacionPrecioUnitarioTrueque(
			String valorDesviacionPrecioUnitarioTrueque) {
		this.valorDesviacionPrecioUnitarioTrueque = valorDesviacionPrecioUnitarioTrueque;
	}


	/**
	 * @return the indicadorProductoCambiaIgualDesea
	 */
	public String getIndicadorProductoCambiaIgualDesea() {
		return indicadorProductoCambiaIgualDesea;
	}


	/**
	 * @param indicadorProductoCambiaIgualDesea the indicadorProductoCambiaIgualDesea to set
	 */
	public void setIndicadorProductoCambiaIgualDesea(
			String indicadorProductoCambiaIgualDesea) {
		this.indicadorProductoCambiaIgualDesea = indicadorProductoCambiaIgualDesea;
	}


	/**
	 * @return the codigoClienteDocumentoReferencia
	 */
	public String getCodigoClienteDocumentoReferencia() {
		return codigoClienteDocumentoReferencia;
	}


	/**
	 * @param codigoClienteDocumentoReferencia the codigoClienteDocumentoReferencia to set
	 */
	public void setCodigoClienteDocumentoReferencia(
			String codigoClienteDocumentoReferencia) {
		this.codigoClienteDocumentoReferencia = codigoClienteDocumentoReferencia;
	}


	/**
	 * @return the indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto
	 */
	public String getIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto() {
		return indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto;
	}


	/**
	 * @param indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto the indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto to set
	 */
	public void setIndicadorDesviacionPrecioUnitarioTruequeValorAbsoluto(
			String indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto) {
		this.indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto = indicadorDesviacionPrecioUnitarioTruequeValorAbsoluto;
	}


	/**
	 * @return the indicadorValidaFaltantes
	 */
	public String getIndicadorValidaFaltantes() {
		return indicadorValidaFaltantes;
	}


	/**
	 * @param indicadorValidaFaltantes the indicadorValidaFaltantes to set
	 */
	public void setIndicadorValidaFaltantes(String indicadorValidaFaltantes) {
		this.indicadorValidaFaltantes = indicadorValidaFaltantes;
	}


	/**
	 * @return the indicadorValidaCambios
	 */
	public String getIndicadorValidaCambios() {
		return indicadorValidaCambios;
	}


	/**
	 * @param indicadorValidaCambios the indicadorValidaCambios to set
	 */
	public void setIndicadorValidaCambios(String indicadorValidaCambios) {
		this.indicadorValidaCambios = indicadorValidaCambios;
	}
	
	/**
	 * @return the indicadorValorAceptaCDR
	 */
	public String getIndicadorValorAceptaCDR() {
		return indicadorValorAceptaCDR;
	}


	/**
	 * @param indicadorValorAceptaCDR the indicadorValorAceptaCDR to set
	 */
	public void setIndicadorValorAceptaCDR(String indicadorValorAceptaCDR) {
		this.indicadorValorAceptaCDR = indicadorValorAceptaCDR;
	}

}
