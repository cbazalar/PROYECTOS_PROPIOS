package biz.belcorp.ssicc.web.spusicc.sgr.form;



import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;


/**
* TODO Include class description here.
* 
* <p>
* <a href="MantenimientoSGRPolizaForm.java.html"> <i>View Source</i> </a>
* </p>
* 
* @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
* 
*/
public class MantenimientoSGRPolizaForm extends BaseEditForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoPoliza;
	private String descripcionPoliza;
	private String precioPoliza;
	private String edadMinima;
	private String edadMaxima;
	private String indicadorActivo;
	private String numeroCampanhasAntiguedad;	
	private String numDiasFacturacion;
	private String coberturaPEGS;
	
	//tab vigencias
	private String codigoPeriodo;
	private String codigoRegion;
	private String fechaInicial;
	private String fechaFinal;
	private String indicadorNuevoVigencia;
	private String correlativoVigencia;
	//tab Kit
	private String campanhaProceso;
	private String codigoVenta;
	private String indicadorNuevoKit;
	//tab Descuento
	private String campInicialDscto;
	private String campFinalDscto;
	private String montInicialVenta;
	private String montoFinalVenta;
	private String montDscto;
	private String indicadorNuevoDscto;
	private String correlativoDscto;
	//tab Estatus
	private String indicadorNuevoEstatus;
	private String codigoEstatus;
	//
	//tab Campa�as Gratuitas
	private String campInicialCampGratuitas;
	private String campFinalCampGratuitas;
	private String codigoBeneficiario;
	private String correlativoCampaniaGratuita;
	
	/* INI SA PER-SiCC-2012-0786 */
	private String numeroPeriodosEvaluar;
	private String numeroMaximoDescuento;
	/* FIN SA PER-SiCC-2012-0786 */
	
	//
	private String  tabSeleccion;
	private String campanhaActual;
	
	protected String[] selectedItemsKit = {};	
	protected String selectedItemKit = null;
	
	protected String[] selectedItemsDscto = {};	
	protected String selectedItemDscto = null;
	
	protected String[] selectedItemsCampaniasGratuitas = {};
	protected String selectedItemCampaniasGratuitas = null;
	
	protected String[] selectedItemsEstatus = {};
	protected String selectedItemEstatus = null;
	
	private String indicadorNuevoCampaniasGratuitas;
	
	/**
	 * @return
	 */
	public String getCorrelativoCampaniaGratuita() {
		return correlativoCampaniaGratuita;
	}

	/**
	 * @param correlativoCampaniaGratuita
	 */
	public void setCorrelativoCampaniaGratuita(String correlativoCampaniaGratuita) {
		this.correlativoCampaniaGratuita = correlativoCampaniaGratuita;
	}

	/**
	 * @return
	 */
	public String[] getSelectedItemsCampaniasGratuitas() {
		return selectedItemsCampaniasGratuitas;
	}

	/**
	 * @param selectedItemsCampaniasGratuitas
	 */
	public void setSelectedItemsCampaniasGratuitas(String[] selectedItemsCampaniasGratuitas) {
		this.selectedItemsCampaniasGratuitas = selectedItemsCampaniasGratuitas;
	}

	/**
	 * @return
	 */
	public String getSelectedItemCampaniasGratuitas() {
		return selectedItemCampaniasGratuitas;
	}

	/**
	 * @param selectedItemCampaniasGratuitas
	 */
	public void setSelectedItemCampaniasGratuitas(String selectedItemCampaniasGratuitas) {
		this.selectedItemCampaniasGratuitas = selectedItemCampaniasGratuitas;
	}

	/**
	 * @return
	 */
	public String getCampInicialCampGratuitas() {
		return campInicialCampGratuitas;
	}

	/**
	 * @param campInicialCampGratuitas
	 */
	public void setCampInicialCampGratuitas(String campInicialCampGratuitas) {
		this.campInicialCampGratuitas = campInicialCampGratuitas;
	}

	/**
	 * @return
	 */
	public String getCampFinalCampGratuitas() {
		return campFinalCampGratuitas;
	}

	/**
	 * @param campFinalCampGratuitas
	 */
	public void setCampFinalCampGratuitas(String campFinalCampGratuitas) {
		this.campFinalCampGratuitas = campFinalCampGratuitas;
	}

	/**
	 * @return
	 */
	public String getCodigoBeneficiario() {
		return codigoBeneficiario;
	}

	/**
	 * @param codigoBeneficiario
	 */
	public void setCodigoBeneficiario(String codigoBeneficiario) {
		this.codigoBeneficiario = codigoBeneficiario;
	}

	/**
	 * @return
	 */
	public String getIndicadorNuevoCampaniasGratuitas() {
		return indicadorNuevoCampaniasGratuitas;
	}

	/**
	 * @param indicadorNuevoCampaniasGratuitas
	 */
	public void setIndicadorNuevoCampaniasGratuitas(String indicadorNuevoCampaniasGratuitas) {
		this.indicadorNuevoCampaniasGratuitas = indicadorNuevoCampaniasGratuitas;
	}

	/**
	 * @return
	 */
	public String[] getSelectedItemsEstatus() {
		return selectedItemsEstatus;
	}

	/**
	 * @param selectedItemsEstatus
	 */
	public void setSelectedItemsEstatus(String[] selectedItemsEstatus) {
		this.selectedItemsEstatus = selectedItemsEstatus;
	}

	/**
	 * @return
	 */
	public String getSelectedItemEstatus() {
		return selectedItemEstatus;
	}

	/**
	 * @param selectedItemEstatus
	 */
	public void setSelectedItemEstatus(String selectedItemEstatus) {
		this.selectedItemEstatus = selectedItemEstatus;
	}

	/**
	 * @return
	 */
	public String getCodigoEstatus() {
		return codigoEstatus;
	}

	/**
	 * @param codigoEstatus
	 */
	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}

	/**
	 * @return
	 */
	public String getIndicadorNuevoEstatus() {
		return indicadorNuevoEstatus;
	}

	/**
	 * @param indicadorNuevoEstatus
	 */
	public void setIndicadorNuevoEstatus(String indicadorNuevoEstatus) {
		this.indicadorNuevoEstatus = indicadorNuevoEstatus;
	}

	/**
	 * @return
	 */
	public String getNumDiasFacturacion() {
		return numDiasFacturacion;
	}

	/**
	 * @param numDiasFacturacion
	 * @struts.validator type = "required"
	 */
	public void setNumDiasFacturacion(String numDiasFacturacion) {
		this.numDiasFacturacion = numDiasFacturacion;
	}

	/**
	 * @return the correlativoDscto
	 */
	public String getCorrelativoDscto() {
		return correlativoDscto;
	}



	/**
	 * @param correlativoDscto the correlativoDscto to set
	 */
	public void setCorrelativoDscto(String correlativoDscto) {
		this.correlativoDscto = correlativoDscto;
	}



	/**
	 * @return the campanhaActual
	 */
	public String getCampanhaActual() {
		return campanhaActual;
	}



	/**
	 * @param campanhaActual the campanhaActual to set
	 */
	public void setCampanhaActual(String campanhaActual) {
		this.campanhaActual = campanhaActual;
	}



		/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}



	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}




	/**
	 * @return the codigoPoliza
	 */
	public String getCodigoPoliza() {
		return codigoPoliza;
	}



	/**
	 * @param codigoPoliza the codigoPoliza to set
	 * @struts.validator type = "required" 
	 */
	public void setCodigoPoliza(String codigoPoliza) {
		this.codigoPoliza = codigoPoliza;
	}



	/**
	 * @return the descripcionPoliza
	 */
	public String getDescripcionPoliza() {
		return descripcionPoliza;
	}



	/**
	 * @param descripcionPoliza the descripcionPoliza to set
	 * @struts.validator type = "required" 
	 */
	public void setDescripcionPoliza(String descripcionPoliza) {
		this.descripcionPoliza = descripcionPoliza;
	}



	/**
	 * @return the precioPoliza
	 */
	public String getPrecioPoliza() {
		return precioPoliza;
	}



	/**
	 * @param precioPoliza the precioPoliza to set
	 * @struts.validator type = "required" 
	 */
	public void setPrecioPoliza(String precioPoliza) {
		this.precioPoliza = precioPoliza;
	}



	/**
	 * @return the edadMinima
	 */
	public String getEdadMinima() {
		return edadMinima;
	}



	/**
	 * @param edadMinima the edadMinima to set
	 * @struts.validator type = "required" 
	 */
	public void setEdadMinima(String edadMinima) {
		this.edadMinima = edadMinima;
	}



	/**
	 * @return the edadMaxima
	 */
	public String getEdadMaxima() {
		return edadMaxima;
	}



	/**
	 * @param edadMaxima the edadMaxima to set
	 * @struts.validator type = "required" 
	 */
	public void setEdadMaxima(String edadMaxima) {
		this.edadMaxima = edadMaxima;
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
	 * @return the numeroCampanhasAntiguedad
	 */
	public String getNumeroCampanhasAntiguedad() {
		return numeroCampanhasAntiguedad;
	}



	/**
	 * @param numeroCampanhasAntiguedad the numeroCampanhasAntiguedad to set
	 */
	public void setNumeroCampanhasAntiguedad(String numeroCampanhasAntiguedad) {
		this.numeroCampanhasAntiguedad = numeroCampanhasAntiguedad;
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
	 * @return the fechaInicial
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}



	/**
	 * @param fechaInicial the fechaInicial to set
	 */
	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}



	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}



	/**
	 * @param fechaFinal the fechaFinal to set
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}



	/**
	 * @return the campanhaProceso
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}



	/**
	 * @param campanhaProceso the campanhaProceso to set
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
	}



	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}



	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}



	/**
	 * @return the campInicialDscto
	 */
	public String getCampInicialDscto() {
		return campInicialDscto;
	}



	/**
	 * @param campInicialDscto the campInicialDscto to set
	 */
	public void setCampInicialDscto(String campInicialDscto) {
		this.campInicialDscto = campInicialDscto;
	}



	/**
	 * @return the campFinalDscto
	 */
	public String getCampFinalDscto() {
		return campFinalDscto;
	}



	/**
	 * @param campFinalDscto the campFinalDscto to set
	 */
	public void setCampFinalDscto(String campFinalDscto) {
		this.campFinalDscto = campFinalDscto;
	}



	/**
	 * @return the montInicialVenta
	 */
	public String getMontInicialVenta() {
		return montInicialVenta;
	}



	/**
	 * @param montInicialVenta the montInicialVenta to set
	 */
	public void setMontInicialVenta(String montInicialVenta) {
		this.montInicialVenta = montInicialVenta;
	}



	/**
	 * @return the montoFinalVenta
	 */
	public String getMontoFinalVenta() {
		return montoFinalVenta;
	}



	/**
	 * @param montoFinalVenta the montoFinalVenta to set
	 */
	public void setMontoFinalVenta(String montoFinalVenta) {
		this.montoFinalVenta = montoFinalVenta;
	}



	/**
	 * @return the montDscto
	 */
	public String getMontDscto() {
		return montDscto;
	}



	/**
	 * @param montDscto the montDscto to set
	 */
	public void setMontDscto(String montDscto) {
		this.montDscto = montDscto;
	}



	/**
	 * @return the indicadorNuevoVigencia
	 */
	public String getIndicadorNuevoVigencia() {
		return indicadorNuevoVigencia;
	}



	/**
	 * @param indicadorNuevoVigencia the indicadorNuevoVigencia to set
	 */
	public void setIndicadorNuevoVigencia(String indicadorNuevoVigencia) {
		this.indicadorNuevoVigencia = indicadorNuevoVigencia;
	}



	/**
	 * @return the indicadorNuevoKit
	 */
	public String getIndicadorNuevoKit() {
		return indicadorNuevoKit;
	}



	/**
	 * @param indicadorNuevoKit the indicadorNuevoKit to set
	 */
	public void setIndicadorNuevoKit(String indicadorNuevoKit) {
		this.indicadorNuevoKit = indicadorNuevoKit;
	}



	/**
	 * @return the indicadorNuevoDscto
	 */
	public String getIndicadorNuevoDscto() {
		return indicadorNuevoDscto;
	}



	/**
	 * @param indicadorNuevoDscto the indicadorNuevoDscto to set
	 */
	public void setIndicadorNuevoDscto(String indicadorNuevoDscto) {
		this.indicadorNuevoDscto = indicadorNuevoDscto;
	}



	/**
	 * @return the tabSeleccion
	 */
	public String getTabSeleccion() {
		return tabSeleccion;
	}



	/**
	 * @param tabSeleccion the tabSeleccion to set
	 */
	public void setTabSeleccion(String tabSeleccion) {
		this.tabSeleccion = tabSeleccion;
	}



	/**
	 * @return the correlativoVigencia
	 */
	public String getCorrelativoVigencia() {
		return correlativoVigencia;
	}



	/**
	 * @param correlativoVigencia the correlativoVigencia to set
	 */
	public void setCorrelativoVigencia(String correlativoVigencia) {
		this.correlativoVigencia = correlativoVigencia;
	}



	/**
	 * @return the selectedItemsKit
	 */
	public String[] getSelectedItemsKit() {
		return selectedItemsKit;
	}



	/**
	 * @param selectedItemsKit the selectedItemsKit to set
	 */
	public void setSelectedItemsKit(String[] selectedItemsKit) {
		this.selectedItemsKit = selectedItemsKit;
	}



	/**
	 * @return the selectedItemKit
	 */
	public String getSelectedItemKit() {
		return selectedItemKit;
	}



	/**
	 * @param selectedItemKit the selectedItemKit to set
	 */
	public void setSelectedItemKit(String selectedItemKit) {
		this.selectedItemKit = selectedItemKit;
	}



	/**
	 * @return the selectedItemsDscto
	 */
	public String[] getSelectedItemsDscto() {
		return selectedItemsDscto;
	}



	/**
	 * @param selectedItemsDscto the selectedItemsDscto to set
	 */
	public void setSelectedItemsDscto(String[] selectedItemsDscto) {
		this.selectedItemsDscto = selectedItemsDscto;
	}



	/**
	 * @return the selectedItemDscto
	 */
	public String getSelectedItemDscto() {
		return selectedItemDscto;
	}



	/**
	 * @param selectedItemDscto the selectedItemDscto to set
	 */
	public void setSelectedItemDscto(String selectedItemDscto) {
		this.selectedItemDscto = selectedItemDscto;
	}

	/**
	 * @return the numeroPeriodosEvaluar
	 */
	public String getNumeroPeriodosEvaluar() {
		return numeroPeriodosEvaluar;
	}

	/**
	 * @param numeroPeriodosEvaluar the numeroPeriodosEvaluar to set
	 */
	public void setNumeroPeriodosEvaluar(String numeroPeriodosEvaluar) {
		this.numeroPeriodosEvaluar = numeroPeriodosEvaluar;
	}

	/**
	 * @return the numeroMaximoDescuento
	 */
	public String getNumeroMaximoDescuento() {
		return numeroMaximoDescuento;
	}

	/**
	 * @param numeroMaximoDescuento the numeroMaximoDescuento to set
	 */
	public void setNumeroMaximoDescuento(String numeroMaximoDescuento) {
		this.numeroMaximoDescuento = numeroMaximoDescuento;
	}

	/**
	 * @return the coberturaPEGS
	 */
	public String getCoberturaPEGS() {
		return coberturaPEGS;
	}

	/**
	 * @param coberturaPEGS the coberturaPEGS to set
	 */
	public void setCoberturaPEGS(String coberturaPEGS) {
		this.coberturaPEGS = coberturaPEGS;
	}
	
}