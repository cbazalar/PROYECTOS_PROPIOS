package biz.belcorp.ssicc.web.spncd.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCUPProgramaNuevCuponesForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4183626016075096000L;
	private String id;

	private String codigoPais;

	private String codigoPrograma;

	private String campanhaInicial;

	private String campanhaFinal;

	private String numVigencia;

	private String codigoVentCupIni;

	private String codigoVentCupFin;

	private String estadoProg;

	private String codigoMarca;

	private String codigoCanal;
	
	private boolean periodoInicialEditable;
	
	private String periodoFinal;
	
	private String cuponInicial; 
	
	private String cuponFinal;

	protected String[] selectedItems = {};

	protected String selectedItem = null;
	
	private String indicadorConstanciaCupon;
	
	private String indicadorConstanciaPremio;
	
	private String indicadorCupon;
	
	private String indicadorConstanciaPremioElectivo;
	
	private String indicadorProgramaObligatorio;
	
	private String indicadorPremioElectivo; 
	
	private String indicadorGeneraMensaje;

	private boolean indicadorEditable;

	private String indicadorRegaloPedido;
	private String montoMinimo;
	private String indicadorTodasUA;
	private String[] regionList;
	private String[] zonaList;
	private String periodoActual;
	private String[] selectedItemsUAS;
	private boolean indActualizarUAS;
	private String[] selectedItemsDES;
	private boolean indActualizarDES;
	private boolean indicadorBorraDES;
	private boolean indicadorCampoEditable;
	private boolean indicadorBorraUA;
	private String indicadorPremioIncentivo;
	private boolean indRegionTodos;
	private boolean indTodaRegion;
	
	private String periodoProceso;
	
	private String numeroPedidos;
	private String tipoPedido;
	private String indicadorPedidoMixto;
	private String numeroNiveles;
	private String indicadorPremioWeb;

	private String indicadorVigencia;
	
	
	private String nivelDescuentos;
	private String campanhaInicioDescuentos;
	private String campanhaFinDescuentos;
	private String montoVentaExigidoDescuentos;
	private String montoDescuentos;
	private String codigoVentaDescuentos;
	
	private String[] listaGrillaDescuentoCampo01= {};
	private String[] listaGrillaDescuentoCampo02= {};
	private String[] listaGrillaDescuentoCampo03= {};
	private String[] listaGrillaDescuentoCampo04= {};
	private String[] listaGrillaDescuentoCampo05= {};
	private String[] listaGrillaDescuentoCampo06= {};
	
	private String tabSeleccion;
	
	private boolean validacionCodigoVenta;
	
	private String indicadorCuponReutilizable;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the campanhaInicial
	 */
	public String getCampanhaInicial() {
		return campanhaInicial;
	}

	/**
	 * @param campanhaInicial the campanhaInicial to set
	 */
	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}

	/**
	 * @return the campanhaFinal
	 */
	public String getCampanhaFinal() {
		return campanhaFinal;
	}

	/**
	 * @param campanhaFinal the campanhaFinal to set
	 */
	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}

	/**
	 * @return the numVigencia
	 */
	public String getNumVigencia() {
		return numVigencia;
	}

	/**
	 * @param numVigencia the numVigencia to set
	 */
	public void setNumVigencia(String numVigencia) {
		this.numVigencia = numVigencia;
	}

	/**
	 * @return the codigoVentCupIni
	 */
	public String getCodigoVentCupIni() {
		return codigoVentCupIni;
	}

	/**
	 * @param codigoVentCupIni the codigoVentCupIni to set
	 */
	public void setCodigoVentCupIni(String codigoVentCupIni) {
		this.codigoVentCupIni = codigoVentCupIni;
	}

	/**
	 * @return the codigoVentCupFin
	 */
	public String getCodigoVentCupFin() {
		return codigoVentCupFin;
	}

	/**
	 * @param codigoVentCupFin the codigoVentCupFin to set
	 */
	public void setCodigoVentCupFin(String codigoVentCupFin) {
		this.codigoVentCupFin = codigoVentCupFin;
	}

	/**
	 * @return the estadoProg
	 */
	public String getEstadoProg() {
		return estadoProg;
	}

	/**
	 * @param estadoProg the estadoProg to set
	 */
	public void setEstadoProg(String estadoProg) {
		this.estadoProg = estadoProg;
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
	 * @return the periodoInicialEditable
	 */
	public boolean isPeriodoInicialEditable() {
		return periodoInicialEditable;
	}

	/**
	 * @param periodoInicialEditable the periodoInicialEditable to set
	 */
	public void setPeriodoInicialEditable(boolean periodoInicialEditable) {
		this.periodoInicialEditable = periodoInicialEditable;
	}

	/**
	 * @return the periodoFinal
	 */
	public String getPeriodoFinal() {
		return periodoFinal;
	}

	/**
	 * @param periodoFinal the periodoFinal to set
	 */
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	/**
	 * @return the cuponInicial
	 */
	public String getCuponInicial() {
		return cuponInicial;
	}

	/**
	 * @param cuponInicial the cuponInicial to set
	 */
	public void setCuponInicial(String cuponInicial) {
		this.cuponInicial = cuponInicial;
	}

	/**
	 * @return the cuponFinal
	 */
	public String getCuponFinal() {
		return cuponFinal;
	}

	/**
	 * @param cuponFinal the cuponFinal to set
	 */
	public void setCuponFinal(String cuponFinal) {
		this.cuponFinal = cuponFinal;
	}

	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return the selectedItem
	 */
	public String getSelectedItem() {
		return selectedItem;
	}

	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	/**
	 * @return the indicadorConstanciaCupon
	 */
	public String getIndicadorConstanciaCupon() {
		return indicadorConstanciaCupon;
	}

	/**
	 * @param indicadorConstanciaCupon the indicadorConstanciaCupon to set
	 */
	public void setIndicadorConstanciaCupon(String indicadorConstanciaCupon) {
		this.indicadorConstanciaCupon = indicadorConstanciaCupon;
	}

	/**
	 * @return the indicadorConstanciaPremio
	 */
	public String getIndicadorConstanciaPremio() {
		return indicadorConstanciaPremio;
	}

	/**
	 * @param indicadorConstanciaPremio the indicadorConstanciaPremio to set
	 */
	public void setIndicadorConstanciaPremio(String indicadorConstanciaPremio) {
		this.indicadorConstanciaPremio = indicadorConstanciaPremio;
	}

	/**
	 * @return the indicadorCupon
	 */
	public String getIndicadorCupon() {
		return indicadorCupon;
	}

	/**
	 * @param indicadorCupon the indicadorCupon to set
	 */
	public void setIndicadorCupon(String indicadorCupon) {
		this.indicadorCupon = indicadorCupon;
	}

	/**
	 * @return the indicadorConstanciaPremioElectivo
	 */
	public String getIndicadorConstanciaPremioElectivo() {
		return indicadorConstanciaPremioElectivo;
	}

	/**
	 * @param indicadorConstanciaPremioElectivo the indicadorConstanciaPremioElectivo to set
	 */
	public void setIndicadorConstanciaPremioElectivo(
			String indicadorConstanciaPremioElectivo) {
		this.indicadorConstanciaPremioElectivo = indicadorConstanciaPremioElectivo;
	}

	/**
	 * @return the indicadorProgramaObligatorio
	 */
	public String getIndicadorProgramaObligatorio() {
		return indicadorProgramaObligatorio;
	}

	/**
	 * @param indicadorProgramaObligatorio the indicadorProgramaObligatorio to set
	 */
	public void setIndicadorProgramaObligatorio(String indicadorProgramaObligatorio) {
		this.indicadorProgramaObligatorio = indicadorProgramaObligatorio;
	}

	/**
	 * @return the indicadorPremioElectivo
	 */
	public String getIndicadorPremioElectivo() {
		return indicadorPremioElectivo;
	}

	/**
	 * @param indicadorPremioElectivo the indicadorPremioElectivo to set
	 */
	public void setIndicadorPremioElectivo(String indicadorPremioElectivo) {
		this.indicadorPremioElectivo = indicadorPremioElectivo;
	}

	/**
	 * @return the indicadorGeneraMensaje
	 */
	public String getIndicadorGeneraMensaje() {
		return indicadorGeneraMensaje;
	}

	/**
	 * @param indicadorGeneraMensaje the indicadorGeneraMensaje to set
	 */
	public void setIndicadorGeneraMensaje(String indicadorGeneraMensaje) {
		this.indicadorGeneraMensaje = indicadorGeneraMensaje;
	}

	/**
	 * @return the indicadorEditable
	 */
	public boolean isIndicadorEditable() {
		return indicadorEditable;
	}

	/**
	 * @param indicadorEditable the indicadorEditable to set
	 */
	public void setIndicadorEditable(boolean indicadorEditable) {
		this.indicadorEditable = indicadorEditable;
	}

	/**
	 * @return the indicadorRegaloPedido
	 */
	public String getIndicadorRegaloPedido() {
		return indicadorRegaloPedido;
	}

	/**
	 * @param indicadorRegaloPedido the indicadorRegaloPedido to set
	 */
	public void setIndicadorRegaloPedido(String indicadorRegaloPedido) {
		this.indicadorRegaloPedido = indicadorRegaloPedido;
	}

	/**
	 * @return the montoMinimo
	 */
	public String getMontoMinimo() {
		return montoMinimo;
	}

	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	/**
	 * @return the indicadorTodasUA
	 */
	public String getIndicadorTodasUA() {
		return indicadorTodasUA;
	}

	/**
	 * @param indicadorTodasUA the indicadorTodasUA to set
	 */
	public void setIndicadorTodasUA(String indicadorTodasUA) {
		this.indicadorTodasUA = indicadorTodasUA;
	}

	/**
	 * @return the regionList
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the periodoActual
	 */
	public String getPeriodoActual() {
		return periodoActual;
	}

	/**
	 * @param periodoActual the periodoActual to set
	 */
	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}

	/**
	 * @return the selectedItemsUAS
	 */
	public String[] getSelectedItemsUAS() {
		return selectedItemsUAS;
	}

	/**
	 * @param selectedItemsUAS the selectedItemsUAS to set
	 */
	public void setSelectedItemsUAS(String[] selectedItemsUAS) {
		this.selectedItemsUAS = selectedItemsUAS;
	}

	/**
	 * @return the indActualizarUAS
	 */
	public boolean isIndActualizarUAS() {
		return indActualizarUAS;
	}

	/**
	 * @param indActualizarUAS the indActualizarUAS to set
	 */
	public void setIndActualizarUAS(boolean indActualizarUAS) {
		this.indActualizarUAS = indActualizarUAS;
	}

	/**
	 * @return the selectedItemsDES
	 */
	public String[] getSelectedItemsDES() {
		return selectedItemsDES;
	}

	/**
	 * @param selectedItemsDES the selectedItemsDES to set
	 */
	public void setSelectedItemsDES(String[] selectedItemsDES) {
		this.selectedItemsDES = selectedItemsDES;
	}

	/**
	 * @return the indActualizarDES
	 */
	public boolean isIndActualizarDES() {
		return indActualizarDES;
	}

	/**
	 * @param indActualizarDES the indActualizarDES to set
	 */
	public void setIndActualizarDES(boolean indActualizarDES) {
		this.indActualizarDES = indActualizarDES;
	}

	/**
	 * @return the indicadorBorraDES
	 */
	public boolean isIndicadorBorraDES() {
		return indicadorBorraDES;
	}

	/**
	 * @param indicadorBorraDES the indicadorBorraDES to set
	 */
	public void setIndicadorBorraDES(boolean indicadorBorraDES) {
		this.indicadorBorraDES = indicadorBorraDES;
	}

	/**
	 * @return the indicadorCampoEditable
	 */
	public boolean isIndicadorCampoEditable() {
		return indicadorCampoEditable;
	}

	/**
	 * @param indicadorCampoEditable the indicadorCampoEditable to set
	 */
	public void setIndicadorCampoEditable(boolean indicadorCampoEditable) {
		this.indicadorCampoEditable = indicadorCampoEditable;
	}

	/**
	 * @return the indicadorBorraUA
	 */
	public boolean isIndicadorBorraUA() {
		return indicadorBorraUA;
	}

	/**
	 * @param indicadorBorraUA the indicadorBorraUA to set
	 */
	public void setIndicadorBorraUA(boolean indicadorBorraUA) {
		this.indicadorBorraUA = indicadorBorraUA;
	}

	/**
	 * @return the indicadorPremioIncentivo
	 */
	public String getIndicadorPremioIncentivo() {
		return indicadorPremioIncentivo;
	}

	/**
	 * @param indicadorPremioIncentivo the indicadorPremioIncentivo to set
	 */
	public void setIndicadorPremioIncentivo(String indicadorPremioIncentivo) {
		this.indicadorPremioIncentivo = indicadorPremioIncentivo;
	}

	/**
	 * @return the indRegionTodos
	 */
	public boolean isIndRegionTodos() {
		return indRegionTodos;
	}

	/**
	 * @param indRegionTodos the indRegionTodos to set
	 */
	public void setIndRegionTodos(boolean indRegionTodos) {
		this.indRegionTodos = indRegionTodos;
	}

	/**
	 * @return the indTodaRegion
	 */
	public boolean isIndTodaRegion() {
		return indTodaRegion;
	}

	/**
	 * @param indTodaRegion the indTodaRegion to set
	 */
	public void setIndTodaRegion(boolean indTodaRegion) {
		this.indTodaRegion = indTodaRegion;
	}

	/**
	 * @return the periodoProceso
	 */
	public String getPeriodoProceso() {
		return periodoProceso;
	}

	/**
	 * @param periodoProceso the periodoProceso to set
	 */
	public void setPeriodoProceso(String periodoProceso) {
		this.periodoProceso = periodoProceso;
	}

	/**
	 * @return the numeroPedidos
	 */
	public String getNumeroPedidos() {
		return numeroPedidos;
	}

	/**
	 * @param numeroPedidos the numeroPedidos to set
	 */
	public void setNumeroPedidos(String numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

	/**
	 * @return the tipoPedido
	 */
	public String getTipoPedido() {
		return tipoPedido;
	}

	/**
	 * @param tipoPedido the tipoPedido to set
	 */
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	/**
	 * @return the indicadorPedidoMixto
	 */
	public String getIndicadorPedidoMixto() {
		return indicadorPedidoMixto;
	}

	/**
	 * @param indicadorPedidoMixto the indicadorPedidoMixto to set
	 */
	public void setIndicadorPedidoMixto(String indicadorPedidoMixto) {
		this.indicadorPedidoMixto = indicadorPedidoMixto;
	}

	/**
	 * @return the numeroNiveles
	 */
	public String getNumeroNiveles() {
		return numeroNiveles;
	}

	/**
	 * @param numeroNiveles the numeroNiveles to set
	 */
	public void setNumeroNiveles(String numeroNiveles) {
		this.numeroNiveles = numeroNiveles;
	}

	/**
	 * @return the indicadorPremioWeb
	 */
	public String getIndicadorPremioWeb() {
		return indicadorPremioWeb;
	}

	/**
	 * @param indicadorPremioWeb the indicadorPremioWeb to set
	 */
	public void setIndicadorPremioWeb(String indicadorPremioWeb) {
		this.indicadorPremioWeb = indicadorPremioWeb;
	}

	/**
	 * @return the indicadorVigencia
	 */
	public String getIndicadorVigencia() {
		return indicadorVigencia;
	}

	/**
	 * @param indicadorVigencia the indicadorVigencia to set
	 */
	public void setIndicadorVigencia(String indicadorVigencia) {
		this.indicadorVigencia = indicadorVigencia;
	}

	/**
	 * @return the nivelDescuentos
	 */
	public String getNivelDescuentos() {
		return nivelDescuentos;
	}

	/**
	 * @param nivelDescuentos the nivelDescuentos to set
	 */
	public void setNivelDescuentos(String nivelDescuentos) {
		this.nivelDescuentos = nivelDescuentos;
	}

	/**
	 * @return the campanhaInicioDescuentos
	 */
	public String getCampanhaInicioDescuentos() {
		return campanhaInicioDescuentos;
	}

	/**
	 * @param campanhaInicioDescuentos the campanhaInicioDescuentos to set
	 */
	public void setCampanhaInicioDescuentos(String campanhaInicioDescuentos) {
		this.campanhaInicioDescuentos = campanhaInicioDescuentos;
	}

	/**
	 * @return the campanhaFinDescuentos
	 */
	public String getCampanhaFinDescuentos() {
		return campanhaFinDescuentos;
	}

	/**
	 * @param campanhaFinDescuentos the campanhaFinDescuentos to set
	 */
	public void setCampanhaFinDescuentos(String campanhaFinDescuentos) {
		this.campanhaFinDescuentos = campanhaFinDescuentos;
	}

	/**
	 * @return the montoVentaExigidoDescuentos
	 */
	public String getMontoVentaExigidoDescuentos() {
		return montoVentaExigidoDescuentos;
	}

	/**
	 * @param montoVentaExigidoDescuentos the montoVentaExigidoDescuentos to set
	 */
	public void setMontoVentaExigidoDescuentos(String montoVentaExigidoDescuentos) {
		this.montoVentaExigidoDescuentos = montoVentaExigidoDescuentos;
	}

	/**
	 * @return the montoDescuentos
	 */
	public String getMontoDescuentos() {
		return montoDescuentos;
	}

	/**
	 * @param montoDescuentos the montoDescuentos to set
	 */
	public void setMontoDescuentos(String montoDescuentos) {
		this.montoDescuentos = montoDescuentos;
	}

	/**
	 * @return the codigoVentaDescuentos
	 */
	public String getCodigoVentaDescuentos() {
		return codigoVentaDescuentos;
	}

	/**
	 * @param codigoVentaDescuentos the codigoVentaDescuentos to set
	 */
	public void setCodigoVentaDescuentos(String codigoVentaDescuentos) {
		this.codigoVentaDescuentos = codigoVentaDescuentos;
	}

	/**
	 * @return the listaGrillaDescuentoCampo01
	 */
	public String[] getListaGrillaDescuentoCampo01() {
		return listaGrillaDescuentoCampo01;
	}

	/**
	 * @param listaGrillaDescuentoCampo01 the listaGrillaDescuentoCampo01 to set
	 */
	public void setListaGrillaDescuentoCampo01(String[] listaGrillaDescuentoCampo01) {
		this.listaGrillaDescuentoCampo01 = listaGrillaDescuentoCampo01;
	}

	/**
	 * @return the listaGrillaDescuentoCampo02
	 */
	public String[] getListaGrillaDescuentoCampo02() {
		return listaGrillaDescuentoCampo02;
	}

	/**
	 * @param listaGrillaDescuentoCampo02 the listaGrillaDescuentoCampo02 to set
	 */
	public void setListaGrillaDescuentoCampo02(String[] listaGrillaDescuentoCampo02) {
		this.listaGrillaDescuentoCampo02 = listaGrillaDescuentoCampo02;
	}

	/**
	 * @return the listaGrillaDescuentoCampo03
	 */
	public String[] getListaGrillaDescuentoCampo03() {
		return listaGrillaDescuentoCampo03;
	}

	/**
	 * @param listaGrillaDescuentoCampo03 the listaGrillaDescuentoCampo03 to set
	 */
	public void setListaGrillaDescuentoCampo03(String[] listaGrillaDescuentoCampo03) {
		this.listaGrillaDescuentoCampo03 = listaGrillaDescuentoCampo03;
	}

	/**
	 * @return the listaGrillaDescuentoCampo04
	 */
	public String[] getListaGrillaDescuentoCampo04() {
		return listaGrillaDescuentoCampo04;
	}

	/**
	 * @param listaGrillaDescuentoCampo04 the listaGrillaDescuentoCampo04 to set
	 */
	public void setListaGrillaDescuentoCampo04(String[] listaGrillaDescuentoCampo04) {
		this.listaGrillaDescuentoCampo04 = listaGrillaDescuentoCampo04;
	}

	/**
	 * @return the listaGrillaDescuentoCampo05
	 */
	public String[] getListaGrillaDescuentoCampo05() {
		return listaGrillaDescuentoCampo05;
	}

	/**
	 * @param listaGrillaDescuentoCampo05 the listaGrillaDescuentoCampo05 to set
	 */
	public void setListaGrillaDescuentoCampo05(String[] listaGrillaDescuentoCampo05) {
		this.listaGrillaDescuentoCampo05 = listaGrillaDescuentoCampo05;
	}

	/**
	 * @return the listaGrillaDescuentoCampo06
	 */
	public String[] getListaGrillaDescuentoCampo06() {
		return listaGrillaDescuentoCampo06;
	}

	/**
	 * @param listaGrillaDescuentoCampo06 the listaGrillaDescuentoCampo06 to set
	 */
	public void setListaGrillaDescuentoCampo06(String[] listaGrillaDescuentoCampo06) {
		this.listaGrillaDescuentoCampo06 = listaGrillaDescuentoCampo06;
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
	 * @return the validacionCodigoVenta
	 */
	public boolean isValidacionCodigoVenta() {
		return validacionCodigoVenta;
	}

	/**
	 * @param validacionCodigoVenta the validacionCodigoVenta to set
	 */
	public void setValidacionCodigoVenta(boolean validacionCodigoVenta) {
		this.validacionCodigoVenta = validacionCodigoVenta;
	}

	public String getIndicadorCuponReutilizable() {
		return indicadorCuponReutilizable;
	}

	public void setIndicadorCuponReutilizable(String indicadorCuponReutilizable) {
		this.indicadorCuponReutilizable = indicadorCuponReutilizable;
	}
	
	
	
	
	
	

}
