package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCConfiguracionConcursoForm extends BaseEditForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3118242011564624505L;
	private String codigoPais;

	private String oidConcurso;
	private String oidTipoConcurso;
	private String numeroConcurso;
	private String nombreConcurso;
	private String oidTipoPrograma;
	private String oidBaseCalculo;
	private String oidBaseCalculoAux;
	private String indicadorActivarConcurso;
	private String oidPlantilla;

	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String indicadorNoGeneraPuntaje;

	private String oidTipoConcursoIVR;
	private String estadoConcurso;
	private String vigenciaConcurso;

	private String indicadorDevoluciones;
	private String indicadorAnulaciones;
	private String faltantesNoAnunciados;

	private String observaciones;
	private String factorConversion;
	private String numeroPuntosAsignar;

	private String indicadorMultiMarca;
	private String puntosAbonar;

	private String indicadorPuntajeAcumulativo;
	private String indicadorActividad;
	private String indicadorConstancia;

	private String indicadorObtencionPuntos;
	private String indicadorAutomaticoManual;
	private String codigoMensajePuntos;
	private String codigoMensajeDespacho;

	private String tabSeleccion;

	// valores default
	private String oidTipoProgramaRetiro;
	private String oidTipoProgramaBonificacion;
	private String oidTipoProgramaConcurso;
	private String oidTipoConcursoRecomendacion;
	private String oidTipoConcursoParatiParaMi;
	private String oidTipoConcursoDuplaCyzone;
	private String oidTipoConcursoConstancia;
	private String oidTipoConcursoBolsaPremio;
	private String oidBaseCalculoRecomedadas;
	private String codigoMensajePuntosRecomendacion; // Para tipos Programa =
														// Retiro de Bienes
	private String codigoMensajePuntosBonificacion; // Para tipos Programa =
													// Bonificacion
	private String codigoMensajePuntosConcurso; // Para tipos Programa =
												// Concurso
	private String codigoMensajePuntosDuplaCyzone;
	private String codigoMensajeDespachoDuplaCyzone;
	private String codigoMensajeDespachoNoDuplaCyzone;
	private String oidTipoPremiacionBolsaPremios;

	// Seccion Ambito Geografico
	private String oidSubgerencia;
	private String descripcionSubgerencia;
	private String[] regiones;
	private String[] zonas;
	private boolean indActualizarAmbitoGeografico;
	private String[] selectedItemsAmbito;

	// Seccion Ambito Participantes
	private String tipoEvaluacion;
	private String montoMinimoPedido;
	private String unidadesMinimasPedido;
	private String numeroMinimoPedidos;
	private String oidTipoVenta;
	private String indicadorReingresoPierdePuntaje;

	private String indicadorRecomendacionEfectiva;
	private String oidTipoExigencia;
	private String codigoPeriodoEvaluacion;
	private String periodosEvaluacion;
	private String numeroMinimoPedidosRecomendada;
	private String indicadorPremioCampEfect;
	private String generarPuntajeARecomendadas;
	private String oidConcursoPuntajeRecomendada;
	private String numeroConcursoPuntajeRecomendada;

	private String montoVentaMinimo;
	private String montoVentaUnidad;
	private String montoVentaTotal;
	private String montoVentaPromedio;

	// Seccion Productos
	private String codigoBloqueProducto;
	private String oidTipoProducto;
	private String codigoSAP;
	private String codigoPeriodoDesde;
	private String codigoPeriodoHasta;
	private String oidTipoOferta;
	private String oidCicloVida;
	private String oidTipoAgrupacion;
	private String oidMarcaProducto;
	private String oidUnidadNegocio;
	private String oidNegocio;
	private String oidSuperGenerico;
	private String oidGenerico;
	private String codigoSuperGenerico;
	private String codigoGenerico;
	private String descripcionSuperGenerico;
	private String descripcionGenerico;

	private String oidTipoAgrupacionOferta;
	private String oidTipoAgrupacionNegocio;

	private String puntosUnidad;
	private String factorMultiplicador;

	private String unidadesExigidas;
	private String montoExigido;
	private String puntosExigidos;

	private String[] selectedItemsProductosValidos;
	private String[] selectedItemsProductosBonificados;
	private String[] selectedItemsProductosExcluidos;
	private String[] selectedItemsProductosExigidos;

	private boolean indRedefinirProductosValidos;
	private boolean indRedefinirProductosBonificados;
	private boolean indRedefinirProductosExcluidos;
	private boolean indRedefinirProductosExigidos;

	private boolean indTieneProductosExigidos; // indica si el concurso tiene
												// asociado un producto exigido

	private String codigoPeriodoCUV;
	private String CUV;

	// Seccion Ambito Premiacion
	private String montoMinimoConcurso;
	private String numeroPedidos;
	private String cuotaIngreso;
	private String indicadorPedidoEnPeriodo;
	private String montoMinimoPedidoPremiacion;

	private String oidTipoPremiacion;
	private String numeroNiveles;
	private String codigoPeriodoDespacho;
	private String codigoPeriodoDespachoInicio;

	private String indicadorPremiosElectivos;
	private String oidTipoEleccion;
	private String premiosAcumulativosNiveles;
	private String hastaNivel;
	private String indicadorNivelesRotativos;
	private String numeroRotaciones;
	private String accesoNivelSuperior;
	private List listNivelesPremiacion;
	private String codigoPeriodoProceso;
	private String numeroPeriodosSinPedido;
	private String indicadorRangoPedidos;
	private String indicadorPorPedido;
	private boolean editableCampannaInicio;
	private boolean indicadorGrabarSoloPremiacion = false;
	private String indRestriccionMantenimiento;
	private String descripcionMensajePuntos;
	private String oidTipoConcursoReconocimiento;
	private String oidTipoConcursoFicticio;
	private String oidTipoAgrupacionCUV;
	private boolean indicadorGrabarParametrosGenerales;
	private String descripcionPrograma;
	private boolean editableParametrosGenerales;
	private String indicadorComunicacion;
	private String indicadorCPP;
	private String codigoCPP;
	private String descripcionCPP;
	private String oidCPP;
	private String codigoTipoDespachoCPP;
	private String indicadorExigePasarPedidoCampanyaAnteriorCPP;
	private String puntosAbonarRecomendacionEfectivaCPP;
	private String campanyasSinPedidoParaCancelacionPuntosCPP;
	private String porcentajeMaximoDescuentoCPP;
	private String tipoCuadre;
	private String indicadorPriorizaWeb;
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.oidConcurso=this.oidTipoConcurso=this.numeroConcurso=this.nombreConcurso=null;
		this.oidTipoPrograma=this.oidBaseCalculo=this.oidBaseCalculoAux=this.oidPlantilla=null;
		this.indicadorActivarConcurso=Constants.NUMERO_CERO;
		
		this.codigoPeriodoInicio=this.codigoPeriodoFin=null;
		this.indicadorNoGeneraPuntaje=this.oidTipoConcursoIVR=null;
		
		this.estadoConcurso=this.vigenciaConcurso=null;
		this.indicadorDevoluciones=this.indicadorAnulaciones=Constants.NUMERO_CERO;
		
		this.faltantesNoAnunciados=Constants.NUMERO_UNO;
		
		this.observaciones=this.factorConversion=this.numeroPuntosAsignar=this.puntosAbonar=null;
		this.indicadorMultiMarca=this.indicadorPuntajeAcumulativo=Constants.NUMERO_CERO;
		this.indicadorActividad=this.indicadorConstancia=Constants.NUMERO_CERO;
		this.indicadorRangoPedidos=this.indicadorPorPedido=Constants.NUMERO_CERO;
		
		this.indicadorObtencionPuntos=this.indicadorAutomaticoManual=Constants.NUMERO_CERO;
		this.codigoMensajeDespacho=this.codigoMensajePuntos=null;
		
		this.oidSubgerencia=this.descripcionSubgerencia=null;
		this.regiones=this.zonas=null;
		this.selectedItemsAmbito=null;
		
		this.tipoEvaluacion=this.montoMinimoPedido=this.unidadesMinimasPedido=this.numeroMinimoPedidos=null;
		this.oidTipoVenta=this.codigoPeriodoEvaluacion=this.periodosEvaluacion=null;
		this.numeroMinimoPedidosRecomendada=this.oidConcursoPuntajeRecomendada=this.oidTipoExigencia=null;
		this.indicadorReingresoPierdePuntaje=this.indicadorPremioCampEfect=Constants.NUMERO_CERO;
		this.generarPuntajeARecomendadas=Constants.NUMERO_CERO;
		this.indicadorRecomendacionEfectiva=Constants.NUMERO_UNO;
		this.numeroConcursoPuntajeRecomendada=null;
		
		this.montoVentaMinimo=this.montoVentaPromedio=this.montoVentaTotal=this.montoVentaUnidad=null;
		
		this.montoMinimoConcurso=this.numeroPedidos=this.cuotaIngreso=null;
		this.montoMinimoPedidoPremiacion=Constants.NUMERO_UNO;
		this.indicadorPedidoEnPeriodo=Constants.NUMERO_CERO;
		
		this.codigoBloqueProducto=this.oidTipoProducto=this.codigoSAP=null;
		this.codigoPeriodoDesde=this.codigoPeriodoHasta=this.oidTipoOferta=null;
		this.oidTipoAgrupacion=this.oidMarcaProducto=null;
		this.oidCicloVida=Constants.INC_CICLO_VIDA_DEFAULT;
		this.oidUnidadNegocio=this.oidNegocio=this.oidSuperGenerico=null;
		this.oidGenerico=this.descripcionGenerico=this.descripcionSuperGenerico=null;
		this.codigoSuperGenerico=this.codigoGenerico=null;
		this.oidTipoAgrupacionNegocio=this.oidTipoAgrupacionOferta=null;
		
		this.puntosUnidad=this.factorMultiplicador=null;
		this.unidadesExigidas=this.montoExigido=this.puntosExigidos=null;
		
		this.oidTipoPremiacion=this.numeroNiveles=this.codigoPeriodoDespacho=this.oidTipoEleccion=null;
		this.hastaNivel=this.numeroRotaciones=null;
		this.indicadorPremiosElectivos=this.premiosAcumulativosNiveles=Constants.NUMERO_CERO;
		this.indicadorNivelesRotativos=this.accesoNivelSuperior=Constants.NUMERO_CERO;
		
		this.selectedItemsProductosValidos=this.selectedItemsProductosBonificados=null;
		this.selectedItemsProductosExcluidos=this.selectedItemsProductosExigidos=null;
		this.indTieneProductosExigidos = false;
		this.codigoPeriodoProceso="";
		this.numeroPeriodosSinPedido="";
		this.codigoPeriodoDespachoInicio = null;
		this.editableCampannaInicio = true;
		this.indicadorGrabarSoloPremiacion = false;
		this.indRestriccionMantenimiento = null;
		
		this.CUV=this.codigoPeriodoCUV=null;
		this.descripcionMensajePuntos=null;
		this.oidTipoAgrupacionCUV=null;
		this.indicadorGrabarParametrosGenerales=true;
		this.editableParametrosGenerales=false;
		this.indicadorComunicacion = Constants.NUMERO_CERO;
		this.indicadorCPP = Constants.NUMERO_CERO;
		this.indicadorExigePasarPedidoCampanyaAnteriorCPP = Constants.NUMERO_CERO;		
		this.puntosAbonarRecomendacionEfectivaCPP = Constants.NUMERO_CERO;
		this.campanyasSinPedidoParaCancelacionPuntosCPP = Constants.NUMERO_CERO;		
		this.indicadorPriorizaWeb = Constants.NUMERO_CERO;		
	}
	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the oidConcurso
	 */
	public String getOidConcurso() {
		return oidConcurso;
	}

	/**
	 * @param oidConcurso
	 *            the oidConcurso to set
	 */
	public void setOidConcurso(String oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	/**
	 * @return the oidTipoConcurso
	 */
	public String getOidTipoConcurso() {
		return oidTipoConcurso;
	}

	/**
	 * @param oidTipoConcurso
	 *            the oidTipoConcurso to set
	 */
	public void setOidTipoConcurso(String oidTipoConcurso) {
		this.oidTipoConcurso = oidTipoConcurso;
	}

	/**
	 * @return the numeroConcurso
	 */
	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	/**
	 * @param numeroConcurso
	 *            the numeroConcurso to set
	 */
	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}

	/**
	 * @return the nombreConcurso
	 */
	public String getNombreConcurso() {
		return nombreConcurso;
	}

	/**
	 * @param nombreConcurso
	 *            the nombreConcurso to set
	 */
	public void setNombreConcurso(String nombreConcurso) {
		this.nombreConcurso = nombreConcurso;
	}

	/**
	 * @return the oidTipoPrograma
	 */
	public String getOidTipoPrograma() {
		return oidTipoPrograma;
	}

	/**
	 * @param oidTipoPrograma
	 *            the oidTipoPrograma to set
	 */
	public void setOidTipoPrograma(String oidTipoPrograma) {
		this.oidTipoPrograma = oidTipoPrograma;
	}

	/**
	 * @return the oidBaseCalculo
	 */
	public String getOidBaseCalculo() {
		return oidBaseCalculo;
	}

	/**
	 * @param oidBaseCalculo
	 *            the oidBaseCalculo to set
	 */
	public void setOidBaseCalculo(String oidBaseCalculo) {
		this.oidBaseCalculo = oidBaseCalculo;
	}

	/**
	 * @return the oidBaseCalculoAux
	 */
	public String getOidBaseCalculoAux() {
		return oidBaseCalculoAux;
	}

	/**
	 * @param oidBaseCalculoAux
	 *            the oidBaseCalculoAux to set
	 */
	public void setOidBaseCalculoAux(String oidBaseCalculoAux) {
		this.oidBaseCalculoAux = oidBaseCalculoAux;
	}

	/**
	 * @return the indicadorActivarConcurso
	 */
	public String getIndicadorActivarConcurso() {
		return indicadorActivarConcurso;
	}

	/**
	 * @param indicadorActivarConcurso
	 *            the indicadorActivarConcurso to set
	 */
	public void setIndicadorActivarConcurso(String indicadorActivarConcurso) {
		this.indicadorActivarConcurso = indicadorActivarConcurso;
	}

	/**
	 * @return the oidPlantilla
	 */
	public String getOidPlantilla() {
		return oidPlantilla;
	}

	/**
	 * @param oidPlantilla
	 *            the oidPlantilla to set
	 */
	public void setOidPlantilla(String oidPlantilla) {
		this.oidPlantilla = oidPlantilla;
	}

	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio
	 *            the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin
	 *            the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return the indicadorNoGeneraPuntaje
	 */
	public String getIndicadorNoGeneraPuntaje() {
		return indicadorNoGeneraPuntaje;
	}

	/**
	 * @param indicadorNoGeneraPuntaje
	 *            the indicadorNoGeneraPuntaje to set
	 */
	public void setIndicadorNoGeneraPuntaje(String indicadorNoGeneraPuntaje) {
		this.indicadorNoGeneraPuntaje = indicadorNoGeneraPuntaje;
	}

	/**
	 * @return the oidTipoConcursoIVR
	 */
	public String getOidTipoConcursoIVR() {
		return oidTipoConcursoIVR;
	}

	/**
	 * @param oidTipoConcursoIVR
	 *            the oidTipoConcursoIVR to set
	 */
	public void setOidTipoConcursoIVR(String oidTipoConcursoIVR) {
		this.oidTipoConcursoIVR = oidTipoConcursoIVR;
	}

	/**
	 * @return the estadoConcurso
	 */
	public String getEstadoConcurso() {
		return estadoConcurso;
	}

	/**
	 * @param estadoConcurso
	 *            the estadoConcurso to set
	 */
	public void setEstadoConcurso(String estadoConcurso) {
		this.estadoConcurso = estadoConcurso;
	}

	/**
	 * @return the vigenciaConcurso
	 */
	public String getVigenciaConcurso() {
		return vigenciaConcurso;
	}

	/**
	 * @param vigenciaConcurso
	 *            the vigenciaConcurso to set
	 */
	public void setVigenciaConcurso(String vigenciaConcurso) {
		this.vigenciaConcurso = vigenciaConcurso;
	}

	/**
	 * @return the indicadorDevoluciones
	 */
	public String getIndicadorDevoluciones() {
		return indicadorDevoluciones;
	}

	/**
	 * @param indicadorDevoluciones
	 *            the indicadorDevoluciones to set
	 */
	public void setIndicadorDevoluciones(String indicadorDevoluciones) {
		this.indicadorDevoluciones = indicadorDevoluciones;
	}

	/**
	 * @return the indicadorAnulaciones
	 */
	public String getIndicadorAnulaciones() {
		return indicadorAnulaciones;
	}

	/**
	 * @param indicadorAnulaciones
	 *            the indicadorAnulaciones to set
	 */
	public void setIndicadorAnulaciones(String indicadorAnulaciones) {
		this.indicadorAnulaciones = indicadorAnulaciones;
	}

	/**
	 * @return the faltantesNoAnunciados
	 */
	public String getFaltantesNoAnunciados() {
		return faltantesNoAnunciados;
	}

	/**
	 * @param faltantesNoAnunciados
	 *            the faltantesNoAnunciados to set
	 */
	public void setFaltantesNoAnunciados(String faltantesNoAnunciados) {
		this.faltantesNoAnunciados = faltantesNoAnunciados;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones
	 *            the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the factorConversion
	 */
	public String getFactorConversion() {
		return factorConversion;
	}

	/**
	 * @param factorConversion
	 *            the factorConversion to set
	 */
	public void setFactorConversion(String factorConversion) {
		this.factorConversion = factorConversion;
	}

	/**
	 * @return the numeroPuntosAsignar
	 */
	public String getNumeroPuntosAsignar() {
		return numeroPuntosAsignar;
	}

	/**
	 * @param numeroPuntosAsignar
	 *            the numeroPuntosAsignar to set
	 */
	public void setNumeroPuntosAsignar(String numeroPuntosAsignar) {
		this.numeroPuntosAsignar = numeroPuntosAsignar;
	}

	/**
	 * @return the indicadorMultiMarca
	 */
	public String getIndicadorMultiMarca() {
		return indicadorMultiMarca;
	}

	/**
	 * @param indicadorMultiMarca
	 *            the indicadorMultiMarca to set
	 */
	public void setIndicadorMultiMarca(String indicadorMultiMarca) {
		this.indicadorMultiMarca = indicadorMultiMarca;
	}

	/**
	 * @return the puntosAbonar
	 */
	public String getPuntosAbonar() {
		return puntosAbonar;
	}

	/**
	 * @param puntosAbonar
	 *            the puntosAbonar to set
	 */
	public void setPuntosAbonar(String puntosAbonar) {
		this.puntosAbonar = puntosAbonar;
	}

	/**
	 * @return the indicadorPuntajeAcumulativo
	 */
	public String getIndicadorPuntajeAcumulativo() {
		return indicadorPuntajeAcumulativo;
	}

	/**
	 * @param indicadorPuntajeAcumulativo
	 *            the indicadorPuntajeAcumulativo to set
	 */
	public void setIndicadorPuntajeAcumulativo(
			String indicadorPuntajeAcumulativo) {
		this.indicadorPuntajeAcumulativo = indicadorPuntajeAcumulativo;
	}

	/**
	 * @return the indicadorActividad
	 */
	public String getIndicadorActividad() {
		return indicadorActividad;
	}

	/**
	 * @param indicadorActividad
	 *            the indicadorActividad to set
	 */
	public void setIndicadorActividad(String indicadorActividad) {
		this.indicadorActividad = indicadorActividad;
	}

	/**
	 * @return the indicadorConstancia
	 */
	public String getIndicadorConstancia() {
		return indicadorConstancia;
	}

	/**
	 * @param indicadorConstancia
	 *            the indicadorConstancia to set
	 */
	public void setIndicadorConstancia(String indicadorConstancia) {
		this.indicadorConstancia = indicadorConstancia;
	}

	/**
	 * @return the indicadorObtencionPuntos
	 */
	public String getIndicadorObtencionPuntos() {
		return indicadorObtencionPuntos;
	}

	/**
	 * @param indicadorObtencionPuntos
	 *            the indicadorObtencionPuntos to set
	 */
	public void setIndicadorObtencionPuntos(String indicadorObtencionPuntos) {
		this.indicadorObtencionPuntos = indicadorObtencionPuntos;
	}

	/**
	 * @return the indicadorAutomaticoManual
	 */
	public String getIndicadorAutomaticoManual() {
		return indicadorAutomaticoManual;
	}

	/**
	 * @param indicadorAutomaticoManual
	 *            the indicadorAutomaticoManual to set
	 */
	public void setIndicadorAutomaticoManual(String indicadorAutomaticoManual) {
		this.indicadorAutomaticoManual = indicadorAutomaticoManual;
	}

	/**
	 * @return the codigoMensajePuntos
	 */
	public String getCodigoMensajePuntos() {
		return codigoMensajePuntos;
	}

	/**
	 * @param codigoMensajePuntos
	 *            the codigoMensajePuntos to set
	 */
	public void setCodigoMensajePuntos(String codigoMensajePuntos) {
		this.codigoMensajePuntos = codigoMensajePuntos;
	}

	/**
	 * @return the codigoMensajeDespacho
	 */
	public String getCodigoMensajeDespacho() {
		return codigoMensajeDespacho;
	}

	/**
	 * @param codigoMensajeDespacho
	 *            the codigoMensajeDespacho to set
	 */
	public void setCodigoMensajeDespacho(String codigoMensajeDespacho) {
		this.codigoMensajeDespacho = codigoMensajeDespacho;
	}

	/**
	 * @return the tabSeleccion
	 */
	public String getTabSeleccion() {
		return tabSeleccion;
	}

	/**
	 * @param tabSeleccion
	 *            the tabSeleccion to set
	 */
	public void setTabSeleccion(String tabSeleccion) {
		this.tabSeleccion = tabSeleccion;
	}

	/**
	 * @return the oidTipoProgramaRetiro
	 */
	public String getOidTipoProgramaRetiro() {
		return oidTipoProgramaRetiro;
	}

	/**
	 * @param oidTipoProgramaRetiro
	 *            the oidTipoProgramaRetiro to set
	 */
	public void setOidTipoProgramaRetiro(String oidTipoProgramaRetiro) {
		this.oidTipoProgramaRetiro = oidTipoProgramaRetiro;
	}

	/**
	 * @return the oidTipoProgramaBonificacion
	 */
	public String getOidTipoProgramaBonificacion() {
		return oidTipoProgramaBonificacion;
	}

	/**
	 * @param oidTipoProgramaBonificacion
	 *            the oidTipoProgramaBonificacion to set
	 */
	public void setOidTipoProgramaBonificacion(
			String oidTipoProgramaBonificacion) {
		this.oidTipoProgramaBonificacion = oidTipoProgramaBonificacion;
	}

	/**
	 * @return the oidTipoProgramaConcurso
	 */
	public String getOidTipoProgramaConcurso() {
		return oidTipoProgramaConcurso;
	}

	/**
	 * @param oidTipoProgramaConcurso
	 *            the oidTipoProgramaConcurso to set
	 */
	public void setOidTipoProgramaConcurso(String oidTipoProgramaConcurso) {
		this.oidTipoProgramaConcurso = oidTipoProgramaConcurso;
	}

	/**
	 * @return the oidTipoConcursoRecomendacion
	 */
	public String getOidTipoConcursoRecomendacion() {
		return oidTipoConcursoRecomendacion;
	}

	/**
	 * @param oidTipoConcursoRecomendacion
	 *            the oidTipoConcursoRecomendacion to set
	 */
	public void setOidTipoConcursoRecomendacion(
			String oidTipoConcursoRecomendacion) {
		this.oidTipoConcursoRecomendacion = oidTipoConcursoRecomendacion;
	}

	/**
	 * @return the oidTipoConcursoParatiParaMi
	 */
	public String getOidTipoConcursoParatiParaMi() {
		return oidTipoConcursoParatiParaMi;
	}

	/**
	 * @param oidTipoConcursoParatiParaMi
	 *            the oidTipoConcursoParatiParaMi to set
	 */
	public void setOidTipoConcursoParatiParaMi(
			String oidTipoConcursoParatiParaMi) {
		this.oidTipoConcursoParatiParaMi = oidTipoConcursoParatiParaMi;
	}

	/**
	 * @return the oidTipoConcursoDuplaCyzone
	 */
	public String getOidTipoConcursoDuplaCyzone() {
		return oidTipoConcursoDuplaCyzone;
	}

	/**
	 * @param oidTipoConcursoDuplaCyzone
	 *            the oidTipoConcursoDuplaCyzone to set
	 */
	public void setOidTipoConcursoDuplaCyzone(String oidTipoConcursoDuplaCyzone) {
		this.oidTipoConcursoDuplaCyzone = oidTipoConcursoDuplaCyzone;
	}

	/**
	 * @return the oidTipoConcursoConstancia
	 */
	public String getOidTipoConcursoConstancia() {
		return oidTipoConcursoConstancia;
	}

	/**
	 * @param oidTipoConcursoConstancia
	 *            the oidTipoConcursoConstancia to set
	 */
	public void setOidTipoConcursoConstancia(String oidTipoConcursoConstancia) {
		this.oidTipoConcursoConstancia = oidTipoConcursoConstancia;
	}

	/**
	 * @return the oidTipoConcursoBolsaPremio
	 */
	public String getOidTipoConcursoBolsaPremio() {
		return oidTipoConcursoBolsaPremio;
	}

	/**
	 * @param oidTipoConcursoBolsaPremio
	 *            the oidTipoConcursoBolsaPremio to set
	 */
	public void setOidTipoConcursoBolsaPremio(String oidTipoConcursoBolsaPremio) {
		this.oidTipoConcursoBolsaPremio = oidTipoConcursoBolsaPremio;
	}

	/**
	 * @return the oidBaseCalculoRecomedadas
	 */
	public String getOidBaseCalculoRecomedadas() {
		return oidBaseCalculoRecomedadas;
	}

	/**
	 * @param oidBaseCalculoRecomedadas
	 *            the oidBaseCalculoRecomedadas to set
	 */
	public void setOidBaseCalculoRecomedadas(String oidBaseCalculoRecomedadas) {
		this.oidBaseCalculoRecomedadas = oidBaseCalculoRecomedadas;
	}

	/**
	 * @return the codigoMensajePuntosRecomendacion
	 */
	public String getCodigoMensajePuntosRecomendacion() {
		return codigoMensajePuntosRecomendacion;
	}

	/**
	 * @param codigoMensajePuntosRecomendacion
	 *            the codigoMensajePuntosRecomendacion to set
	 */
	public void setCodigoMensajePuntosRecomendacion(
			String codigoMensajePuntosRecomendacion) {
		this.codigoMensajePuntosRecomendacion = codigoMensajePuntosRecomendacion;
	}

	/**
	 * @return the codigoMensajePuntosBonificacion
	 */
	public String getCodigoMensajePuntosBonificacion() {
		return codigoMensajePuntosBonificacion;
	}

	/**
	 * @param codigoMensajePuntosBonificacion
	 *            the codigoMensajePuntosBonificacion to set
	 */
	public void setCodigoMensajePuntosBonificacion(
			String codigoMensajePuntosBonificacion) {
		this.codigoMensajePuntosBonificacion = codigoMensajePuntosBonificacion;
	}

	/**
	 * @return the codigoMensajePuntosConcurso
	 */
	public String getCodigoMensajePuntosConcurso() {
		return codigoMensajePuntosConcurso;
	}

	/**
	 * @param codigoMensajePuntosConcurso
	 *            the codigoMensajePuntosConcurso to set
	 */
	public void setCodigoMensajePuntosConcurso(
			String codigoMensajePuntosConcurso) {
		this.codigoMensajePuntosConcurso = codigoMensajePuntosConcurso;
	}

	/**
	 * @return the codigoMensajePuntosDuplaCyzone
	 */
	public String getCodigoMensajePuntosDuplaCyzone() {
		return codigoMensajePuntosDuplaCyzone;
	}

	/**
	 * @param codigoMensajePuntosDuplaCyzone
	 *            the codigoMensajePuntosDuplaCyzone to set
	 */
	public void setCodigoMensajePuntosDuplaCyzone(
			String codigoMensajePuntosDuplaCyzone) {
		this.codigoMensajePuntosDuplaCyzone = codigoMensajePuntosDuplaCyzone;
	}

	/**
	 * @return the codigoMensajeDespachoDuplaCyzone
	 */
	public String getCodigoMensajeDespachoDuplaCyzone() {
		return codigoMensajeDespachoDuplaCyzone;
	}

	/**
	 * @param codigoMensajeDespachoDuplaCyzone
	 *            the codigoMensajeDespachoDuplaCyzone to set
	 */
	public void setCodigoMensajeDespachoDuplaCyzone(
			String codigoMensajeDespachoDuplaCyzone) {
		this.codigoMensajeDespachoDuplaCyzone = codigoMensajeDespachoDuplaCyzone;
	}

	/**
	 * @return the codigoMensajeDespachoNoDuplaCyzone
	 */
	public String getCodigoMensajeDespachoNoDuplaCyzone() {
		return codigoMensajeDespachoNoDuplaCyzone;
	}

	/**
	 * @param codigoMensajeDespachoNoDuplaCyzone
	 *            the codigoMensajeDespachoNoDuplaCyzone to set
	 */
	public void setCodigoMensajeDespachoNoDuplaCyzone(
			String codigoMensajeDespachoNoDuplaCyzone) {
		this.codigoMensajeDespachoNoDuplaCyzone = codigoMensajeDespachoNoDuplaCyzone;
	}

	/**
	 * @return the oidTipoPremiacionBolsaPremios
	 */
	public String getOidTipoPremiacionBolsaPremios() {
		return oidTipoPremiacionBolsaPremios;
	}

	/**
	 * @param oidTipoPremiacionBolsaPremios
	 *            the oidTipoPremiacionBolsaPremios to set
	 */
	public void setOidTipoPremiacionBolsaPremios(
			String oidTipoPremiacionBolsaPremios) {
		this.oidTipoPremiacionBolsaPremios = oidTipoPremiacionBolsaPremios;
	}

	/**
	 * @return the oidSubgerencia
	 */
	public String getOidSubgerencia() {
		return oidSubgerencia;
	}

	/**
	 * @param oidSubgerencia
	 *            the oidSubgerencia to set
	 */
	public void setOidSubgerencia(String oidSubgerencia) {
		this.oidSubgerencia = oidSubgerencia;
	}

	/**
	 * @return the descripcionSubgerencia
	 */
	public String getDescripcionSubgerencia() {
		return descripcionSubgerencia;
	}

	/**
	 * @param descripcionSubgerencia
	 *            the descripcionSubgerencia to set
	 */
	public void setDescripcionSubgerencia(String descripcionSubgerencia) {
		this.descripcionSubgerencia = descripcionSubgerencia;
	}

	/**
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones
	 *            the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}

	/**
	 * @return the zonas
	 */
	public String[] getZonas() {
		return zonas;
	}

	/**
	 * @param zonas
	 *            the zonas to set
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}

	/**
	 * @return the indActualizarAmbitoGeografico
	 */
	public boolean isIndActualizarAmbitoGeografico() {
		return indActualizarAmbitoGeografico;
	}

	/**
	 * @param indActualizarAmbitoGeografico
	 *            the indActualizarAmbitoGeografico to set
	 */
	public void setIndActualizarAmbitoGeografico(
			boolean indActualizarAmbitoGeografico) {
		this.indActualizarAmbitoGeografico = indActualizarAmbitoGeografico;
	}

	/**
	 * @return the selectedItemsAmbito
	 */
	public String[] getSelectedItemsAmbito() {
		return selectedItemsAmbito;
	}

	/**
	 * @param selectedItemsAmbito
	 *            the selectedItemsAmbito to set
	 */
	public void setSelectedItemsAmbito(String[] selectedItemsAmbito) {
		this.selectedItemsAmbito = selectedItemsAmbito;
	}

	/**
	 * @return the tipoEvaluacion
	 */
	public String getTipoEvaluacion() {
		return tipoEvaluacion;
	}

	/**
	 * @param tipoEvaluacion
	 *            the tipoEvaluacion to set
	 */
	public void setTipoEvaluacion(String tipoEvaluacion) {
		this.tipoEvaluacion = tipoEvaluacion;
	}

	/**
	 * @return the montoMinimoPedido
	 */
	public String getMontoMinimoPedido() {
		return montoMinimoPedido;
	}

	/**
	 * @param montoMinimoPedido
	 *            the montoMinimoPedido to set
	 */
	public void setMontoMinimoPedido(String montoMinimoPedido) {
		this.montoMinimoPedido = montoMinimoPedido;
	}

	/**
	 * @return the unidadesMinimasPedido
	 */
	public String getUnidadesMinimasPedido() {
		return unidadesMinimasPedido;
	}

	/**
	 * @param unidadesMinimasPedido
	 *            the unidadesMinimasPedido to set
	 */
	public void setUnidadesMinimasPedido(String unidadesMinimasPedido) {
		this.unidadesMinimasPedido = unidadesMinimasPedido;
	}

	/**
	 * @return the numeroMinimoPedidos
	 */
	public String getNumeroMinimoPedidos() {
		return numeroMinimoPedidos;
	}

	/**
	 * @param numeroMinimoPedidos
	 *            the numeroMinimoPedidos to set
	 */
	public void setNumeroMinimoPedidos(String numeroMinimoPedidos) {
		this.numeroMinimoPedidos = numeroMinimoPedidos;
	}

	/**
	 * @return the oidTipoVenta
	 */
	public String getOidTipoVenta() {
		return oidTipoVenta;
	}

	/**
	 * @param oidTipoVenta
	 *            the oidTipoVenta to set
	 */
	public void setOidTipoVenta(String oidTipoVenta) {
		this.oidTipoVenta = oidTipoVenta;
	}

	/**
	 * @return the indicadorReingresoPierdePuntaje
	 */
	public String getIndicadorReingresoPierdePuntaje() {
		return indicadorReingresoPierdePuntaje;
	}

	/**
	 * @param indicadorReingresoPierdePuntaje
	 *            the indicadorReingresoPierdePuntaje to set
	 */
	public void setIndicadorReingresoPierdePuntaje(
			String indicadorReingresoPierdePuntaje) {
		this.indicadorReingresoPierdePuntaje = indicadorReingresoPierdePuntaje;
	}

	/**
	 * @return the indicadorRecomendacionEfectiva
	 */
	public String getIndicadorRecomendacionEfectiva() {
		return indicadorRecomendacionEfectiva;
	}

	/**
	 * @param indicadorRecomendacionEfectiva
	 *            the indicadorRecomendacionEfectiva to set
	 */
	public void setIndicadorRecomendacionEfectiva(
			String indicadorRecomendacionEfectiva) {
		this.indicadorRecomendacionEfectiva = indicadorRecomendacionEfectiva;
	}

	/**
	 * @return the oidTipoExigencia
	 */
	public String getOidTipoExigencia() {
		return oidTipoExigencia;
	}

	/**
	 * @param oidTipoExigencia
	 *            the oidTipoExigencia to set
	 */
	public void setOidTipoExigencia(String oidTipoExigencia) {
		this.oidTipoExigencia = oidTipoExigencia;
	}

	/**
	 * @return the codigoPeriodoEvaluacion
	 */
	public String getCodigoPeriodoEvaluacion() {
		return codigoPeriodoEvaluacion;
	}

	/**
	 * @param codigoPeriodoEvaluacion
	 *            the codigoPeriodoEvaluacion to set
	 */
	public void setCodigoPeriodoEvaluacion(String codigoPeriodoEvaluacion) {
		this.codigoPeriodoEvaluacion = codigoPeriodoEvaluacion;
	}

	/**
	 * @return the periodosEvaluacion
	 */
	public String getPeriodosEvaluacion() {
		return periodosEvaluacion;
	}

	/**
	 * @param periodosEvaluacion
	 *            the periodosEvaluacion to set
	 */
	public void setPeriodosEvaluacion(String periodosEvaluacion) {
		this.periodosEvaluacion = periodosEvaluacion;
	}

	/**
	 * @return the numeroMinimoPedidosRecomendada
	 */
	public String getNumeroMinimoPedidosRecomendada() {
		return numeroMinimoPedidosRecomendada;
	}

	/**
	 * @param numeroMinimoPedidosRecomendada
	 *            the numeroMinimoPedidosRecomendada to set
	 */
	public void setNumeroMinimoPedidosRecomendada(
			String numeroMinimoPedidosRecomendada) {
		this.numeroMinimoPedidosRecomendada = numeroMinimoPedidosRecomendada;
	}

	/**
	 * @return the indicadorPremioCampEfect
	 */
	public String getIndicadorPremioCampEfect() {
		return indicadorPremioCampEfect;
	}

	/**
	 * @param indicadorPremioCampEfect
	 *            the indicadorPremioCampEfect to set
	 */
	public void setIndicadorPremioCampEfect(String indicadorPremioCampEfect) {
		this.indicadorPremioCampEfect = indicadorPremioCampEfect;
	}

	/**
	 * @return the generarPuntajeARecomendadas
	 */
	public String getGenerarPuntajeARecomendadas() {
		return generarPuntajeARecomendadas;
	}

	/**
	 * @param generarPuntajeARecomendadas
	 *            the generarPuntajeARecomendadas to set
	 */
	public void setGenerarPuntajeARecomendadas(
			String generarPuntajeARecomendadas) {
		this.generarPuntajeARecomendadas = generarPuntajeARecomendadas;
	}

	/**
	 * @return the oidConcursoPuntajeRecomendada
	 */
	public String getOidConcursoPuntajeRecomendada() {
		return oidConcursoPuntajeRecomendada;
	}

	/**
	 * @param oidConcursoPuntajeRecomendada
	 *            the oidConcursoPuntajeRecomendada to set
	 */
	public void setOidConcursoPuntajeRecomendada(
			String oidConcursoPuntajeRecomendada) {
		this.oidConcursoPuntajeRecomendada = oidConcursoPuntajeRecomendada;
	}

	/**
	 * @return the numeroConcursoPuntajeRecomendada
	 */
	public String getNumeroConcursoPuntajeRecomendada() {
		return numeroConcursoPuntajeRecomendada;
	}

	/**
	 * @param numeroConcursoPuntajeRecomendada
	 *            the numeroConcursoPuntajeRecomendada to set
	 */
	public void setNumeroConcursoPuntajeRecomendada(
			String numeroConcursoPuntajeRecomendada) {
		this.numeroConcursoPuntajeRecomendada = numeroConcursoPuntajeRecomendada;
	}

	/**
	 * @return the montoVentaMinimo
	 */
	public String getMontoVentaMinimo() {
		return montoVentaMinimo;
	}

	/**
	 * @param montoVentaMinimo
	 *            the montoVentaMinimo to set
	 */
	public void setMontoVentaMinimo(String montoVentaMinimo) {
		this.montoVentaMinimo = montoVentaMinimo;
	}

	/**
	 * @return the montoVentaUnidad
	 */
	public String getMontoVentaUnidad() {
		return montoVentaUnidad;
	}

	/**
	 * @param montoVentaUnidad
	 *            the montoVentaUnidad to set
	 */
	public void setMontoVentaUnidad(String montoVentaUnidad) {
		this.montoVentaUnidad = montoVentaUnidad;
	}

	/**
	 * @return the montoVentaTotal
	 */
	public String getMontoVentaTotal() {
		return montoVentaTotal;
	}

	/**
	 * @param montoVentaTotal
	 *            the montoVentaTotal to set
	 */
	public void setMontoVentaTotal(String montoVentaTotal) {
		this.montoVentaTotal = montoVentaTotal;
	}

	/**
	 * @return the montoVentaPromedio
	 */
	public String getMontoVentaPromedio() {
		return montoVentaPromedio;
	}

	/**
	 * @param montoVentaPromedio
	 *            the montoVentaPromedio to set
	 */
	public void setMontoVentaPromedio(String montoVentaPromedio) {
		this.montoVentaPromedio = montoVentaPromedio;
	}

	/**
	 * @return the codigoBloqueProducto
	 */
	public String getCodigoBloqueProducto() {
		return codigoBloqueProducto;
	}

	/**
	 * @param codigoBloqueProducto
	 *            the codigoBloqueProducto to set
	 */
	public void setCodigoBloqueProducto(String codigoBloqueProducto) {
		this.codigoBloqueProducto = codigoBloqueProducto;
	}

	/**
	 * @return the oidTipoProducto
	 */
	public String getOidTipoProducto() {
		return oidTipoProducto;
	}

	/**
	 * @param oidTipoProducto
	 *            the oidTipoProducto to set
	 */
	public void setOidTipoProducto(String oidTipoProducto) {
		this.oidTipoProducto = oidTipoProducto;
	}

	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP
	 *            the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/**
	 * @return the codigoPeriodoDesde
	 */
	public String getCodigoPeriodoDesde() {
		return codigoPeriodoDesde;
	}

	/**
	 * @param codigoPeriodoDesde
	 *            the codigoPeriodoDesde to set
	 */
	public void setCodigoPeriodoDesde(String codigoPeriodoDesde) {
		this.codigoPeriodoDesde = codigoPeriodoDesde;
	}

	/**
	 * @return the codigoPeriodoHasta
	 */
	public String getCodigoPeriodoHasta() {
		return codigoPeriodoHasta;
	}

	/**
	 * @param codigoPeriodoHasta
	 *            the codigoPeriodoHasta to set
	 */
	public void setCodigoPeriodoHasta(String codigoPeriodoHasta) {
		this.codigoPeriodoHasta = codigoPeriodoHasta;
	}

	/**
	 * @return the oidTipoOferta
	 */
	public String getOidTipoOferta() {
		return oidTipoOferta;
	}

	/**
	 * @param oidTipoOferta
	 *            the oidTipoOferta to set
	 */
	public void setOidTipoOferta(String oidTipoOferta) {
		this.oidTipoOferta = oidTipoOferta;
	}

	/**
	 * @return the oidCicloVida
	 */
	public String getOidCicloVida() {
		return oidCicloVida;
	}

	/**
	 * @param oidCicloVida
	 *            the oidCicloVida to set
	 */
	public void setOidCicloVida(String oidCicloVida) {
		this.oidCicloVida = oidCicloVida;
	}

	/**
	 * @return the oidTipoAgrupacion
	 */
	public String getOidTipoAgrupacion() {
		return oidTipoAgrupacion;
	}

	/**
	 * @param oidTipoAgrupacion
	 *            the oidTipoAgrupacion to set
	 */
	public void setOidTipoAgrupacion(String oidTipoAgrupacion) {
		this.oidTipoAgrupacion = oidTipoAgrupacion;
	}

	/**
	 * @return the oidMarcaProducto
	 */
	public String getOidMarcaProducto() {
		return oidMarcaProducto;
	}

	/**
	 * @param oidMarcaProducto
	 *            the oidMarcaProducto to set
	 */
	public void setOidMarcaProducto(String oidMarcaProducto) {
		this.oidMarcaProducto = oidMarcaProducto;
	}

	/**
	 * @return the oidUnidadNegocio
	 */
	public String getOidUnidadNegocio() {
		return oidUnidadNegocio;
	}

	/**
	 * @param oidUnidadNegocio
	 *            the oidUnidadNegocio to set
	 */
	public void setOidUnidadNegocio(String oidUnidadNegocio) {
		this.oidUnidadNegocio = oidUnidadNegocio;
	}

	/**
	 * @return the oidNegocio
	 */
	public String getOidNegocio() {
		return oidNegocio;
	}

	/**
	 * @param oidNegocio
	 *            the oidNegocio to set
	 */
	public void setOidNegocio(String oidNegocio) {
		this.oidNegocio = oidNegocio;
	}

	/**
	 * @return the oidSuperGenerico
	 */
	public String getOidSuperGenerico() {
		return oidSuperGenerico;
	}

	/**
	 * @param oidSuperGenerico
	 *            the oidSuperGenerico to set
	 */
	public void setOidSuperGenerico(String oidSuperGenerico) {
		this.oidSuperGenerico = oidSuperGenerico;
	}

	/**
	 * @return the oidGenerico
	 */
	public String getOidGenerico() {
		return oidGenerico;
	}

	/**
	 * @param oidGenerico
	 *            the oidGenerico to set
	 */
	public void setOidGenerico(String oidGenerico) {
		this.oidGenerico = oidGenerico;
	}

	/**
	 * @return the codigoSuperGenerico
	 */
	public String getCodigoSuperGenerico() {
		return codigoSuperGenerico;
	}

	/**
	 * @param codigoSuperGenerico
	 *            the codigoSuperGenerico to set
	 */
	public void setCodigoSuperGenerico(String codigoSuperGenerico) {
		this.codigoSuperGenerico = codigoSuperGenerico;
	}

	/**
	 * @return the codigoGenerico
	 */
	public String getCodigoGenerico() {
		return codigoGenerico;
	}

	/**
	 * @param codigoGenerico
	 *            the codigoGenerico to set
	 */
	public void setCodigoGenerico(String codigoGenerico) {
		this.codigoGenerico = codigoGenerico;
	}

	/**
	 * @return the descripcionSuperGenerico
	 */
	public String getDescripcionSuperGenerico() {
		return descripcionSuperGenerico;
	}

	/**
	 * @param descripcionSuperGenerico
	 *            the descripcionSuperGenerico to set
	 */
	public void setDescripcionSuperGenerico(String descripcionSuperGenerico) {
		this.descripcionSuperGenerico = descripcionSuperGenerico;
	}

	/**
	 * @return the descripcionGenerico
	 */
	public String getDescripcionGenerico() {
		return descripcionGenerico;
	}

	/**
	 * @param descripcionGenerico
	 *            the descripcionGenerico to set
	 */
	public void setDescripcionGenerico(String descripcionGenerico) {
		this.descripcionGenerico = descripcionGenerico;
	}

	/**
	 * @return the oidTipoAgrupacionOferta
	 */
	public String getOidTipoAgrupacionOferta() {
		return oidTipoAgrupacionOferta;
	}

	/**
	 * @param oidTipoAgrupacionOferta
	 *            the oidTipoAgrupacionOferta to set
	 */
	public void setOidTipoAgrupacionOferta(String oidTipoAgrupacionOferta) {
		this.oidTipoAgrupacionOferta = oidTipoAgrupacionOferta;
	}

	/**
	 * @return the oidTipoAgrupacionNegocio
	 */
	public String getOidTipoAgrupacionNegocio() {
		return oidTipoAgrupacionNegocio;
	}

	/**
	 * @param oidTipoAgrupacionNegocio
	 *            the oidTipoAgrupacionNegocio to set
	 */
	public void setOidTipoAgrupacionNegocio(String oidTipoAgrupacionNegocio) {
		this.oidTipoAgrupacionNegocio = oidTipoAgrupacionNegocio;
	}

	/**
	 * @return the puntosUnidad
	 */
	public String getPuntosUnidad() {
		return puntosUnidad;
	}

	/**
	 * @param puntosUnidad
	 *            the puntosUnidad to set
	 */
	public void setPuntosUnidad(String puntosUnidad) {
		this.puntosUnidad = puntosUnidad;
	}

	/**
	 * @return the factorMultiplicador
	 */
	public String getFactorMultiplicador() {
		return factorMultiplicador;
	}

	/**
	 * @param factorMultiplicador
	 *            the factorMultiplicador to set
	 */
	public void setFactorMultiplicador(String factorMultiplicador) {
		this.factorMultiplicador = factorMultiplicador;
	}

	/**
	 * @return the unidadesExigidas
	 */
	public String getUnidadesExigidas() {
		return unidadesExigidas;
	}

	/**
	 * @param unidadesExigidas
	 *            the unidadesExigidas to set
	 */
	public void setUnidadesExigidas(String unidadesExigidas) {
		this.unidadesExigidas = unidadesExigidas;
	}

	/**
	 * @return the montoExigido
	 */
	public String getMontoExigido() {
		return montoExigido;
	}

	/**
	 * @param montoExigido
	 *            the montoExigido to set
	 */
	public void setMontoExigido(String montoExigido) {
		this.montoExigido = montoExigido;
	}

	/**
	 * @return the puntosExigidos
	 */
	public String getPuntosExigidos() {
		return puntosExigidos;
	}

	/**
	 * @param puntosExigidos
	 *            the puntosExigidos to set
	 */
	public void setPuntosExigidos(String puntosExigidos) {
		this.puntosExigidos = puntosExigidos;
	}

	/**
	 * @return the selectedItemsProductosValidos
	 */
	public String[] getSelectedItemsProductosValidos() {
		return selectedItemsProductosValidos;
	}

	/**
	 * @param selectedItemsProductosValidos
	 *            the selectedItemsProductosValidos to set
	 */
	public void setSelectedItemsProductosValidos(
			String[] selectedItemsProductosValidos) {
		this.selectedItemsProductosValidos = selectedItemsProductosValidos;
	}

	/**
	 * @return the selectedItemsProductosBonificados
	 */
	public String[] getSelectedItemsProductosBonificados() {
		return selectedItemsProductosBonificados;
	}

	/**
	 * @param selectedItemsProductosBonificados
	 *            the selectedItemsProductosBonificados to set
	 */
	public void setSelectedItemsProductosBonificados(
			String[] selectedItemsProductosBonificados) {
		this.selectedItemsProductosBonificados = selectedItemsProductosBonificados;
	}

	/**
	 * @return the selectedItemsProductosExcluidos
	 */
	public String[] getSelectedItemsProductosExcluidos() {
		return selectedItemsProductosExcluidos;
	}

	/**
	 * @param selectedItemsProductosExcluidos
	 *            the selectedItemsProductosExcluidos to set
	 */
	public void setSelectedItemsProductosExcluidos(
			String[] selectedItemsProductosExcluidos) {
		this.selectedItemsProductosExcluidos = selectedItemsProductosExcluidos;
	}

	/**
	 * @return the selectedItemsProductosExigidos
	 */
	public String[] getSelectedItemsProductosExigidos() {
		return selectedItemsProductosExigidos;
	}

	/**
	 * @param selectedItemsProductosExigidos
	 *            the selectedItemsProductosExigidos to set
	 */
	public void setSelectedItemsProductosExigidos(
			String[] selectedItemsProductosExigidos) {
		this.selectedItemsProductosExigidos = selectedItemsProductosExigidos;
	}

	/**
	 * @return the indRedefinirProductosValidos
	 */
	public boolean isIndRedefinirProductosValidos() {
		return indRedefinirProductosValidos;
	}

	/**
	 * @param indRedefinirProductosValidos
	 *            the indRedefinirProductosValidos to set
	 */
	public void setIndRedefinirProductosValidos(
			boolean indRedefinirProductosValidos) {
		this.indRedefinirProductosValidos = indRedefinirProductosValidos;
	}

	/**
	 * @return the indRedefinirProductosBonificados
	 */
	public boolean isIndRedefinirProductosBonificados() {
		return indRedefinirProductosBonificados;
	}

	/**
	 * @param indRedefinirProductosBonificados
	 *            the indRedefinirProductosBonificados to set
	 */
	public void setIndRedefinirProductosBonificados(
			boolean indRedefinirProductosBonificados) {
		this.indRedefinirProductosBonificados = indRedefinirProductosBonificados;
	}

	/**
	 * @return the indRedefinirProductosExcluidos
	 */
	public boolean isIndRedefinirProductosExcluidos() {
		return indRedefinirProductosExcluidos;
	}

	/**
	 * @param indRedefinirProductosExcluidos
	 *            the indRedefinirProductosExcluidos to set
	 */
	public void setIndRedefinirProductosExcluidos(
			boolean indRedefinirProductosExcluidos) {
		this.indRedefinirProductosExcluidos = indRedefinirProductosExcluidos;
	}

	/**
	 * @return the indRedefinirProductosExigidos
	 */
	public boolean isIndRedefinirProductosExigidos() {
		return indRedefinirProductosExigidos;
	}

	/**
	 * @param indRedefinirProductosExigidos
	 *            the indRedefinirProductosExigidos to set
	 */
	public void setIndRedefinirProductosExigidos(
			boolean indRedefinirProductosExigidos) {
		this.indRedefinirProductosExigidos = indRedefinirProductosExigidos;
	}

	/**
	 * @return the indTieneProductosExigidos
	 */
	public boolean isIndTieneProductosExigidos() {
		return indTieneProductosExigidos;
	}

	/**
	 * @param indTieneProductosExigidos
	 *            the indTieneProductosExigidos to set
	 */
	public void setIndTieneProductosExigidos(boolean indTieneProductosExigidos) {
		this.indTieneProductosExigidos = indTieneProductosExigidos;
	}

	/**
	 * @return the codigoPeriodoCUV
	 */
	public String getCodigoPeriodoCUV() {
		return codigoPeriodoCUV;
	}

	/**
	 * @param codigoPeriodoCUV
	 *            the codigoPeriodoCUV to set
	 */
	public void setCodigoPeriodoCUV(String codigoPeriodoCUV) {
		this.codigoPeriodoCUV = codigoPeriodoCUV;
	}

	/**
	 * @return the cUV
	 */
	public String getCUV() {
		return CUV;
	}

	/**
	 * @param cUV
	 *            the cUV to set
	 */
	public void setCUV(String cUV) {
		CUV = cUV;
	}

	/**
	 * @return the montoMinimoConcurso
	 */
	public String getMontoMinimoConcurso() {
		return montoMinimoConcurso;
	}

	/**
	 * @param montoMinimoConcurso
	 *            the montoMinimoConcurso to set
	 */
	public void setMontoMinimoConcurso(String montoMinimoConcurso) {
		this.montoMinimoConcurso = montoMinimoConcurso;
	}

	/**
	 * @return the numeroPedidos
	 */
	public String getNumeroPedidos() {
		return numeroPedidos;
	}

	/**
	 * @param numeroPedidos
	 *            the numeroPedidos to set
	 */
	public void setNumeroPedidos(String numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

	/**
	 * @return the cuotaIngreso
	 */
	public String getCuotaIngreso() {
		return cuotaIngreso;
	}

	/**
	 * @param cuotaIngreso
	 *            the cuotaIngreso to set
	 */
	public void setCuotaIngreso(String cuotaIngreso) {
		this.cuotaIngreso = cuotaIngreso;
	}

	/**
	 * @return the indicadorPedidoEnPeriodo
	 */
	public String getIndicadorPedidoEnPeriodo() {
		return indicadorPedidoEnPeriodo;
	}

	/**
	 * @param indicadorPedidoEnPeriodo
	 *            the indicadorPedidoEnPeriodo to set
	 */
	public void setIndicadorPedidoEnPeriodo(String indicadorPedidoEnPeriodo) {
		this.indicadorPedidoEnPeriodo = indicadorPedidoEnPeriodo;
	}

	/**
	 * @return the montoMinimoPedidoPremiacion
	 */
	public String getMontoMinimoPedidoPremiacion() {
		return montoMinimoPedidoPremiacion;
	}

	/**
	 * @param montoMinimoPedidoPremiacion
	 *            the montoMinimoPedidoPremiacion to set
	 */
	public void setMontoMinimoPedidoPremiacion(
			String montoMinimoPedidoPremiacion) {
		this.montoMinimoPedidoPremiacion = montoMinimoPedidoPremiacion;
	}

	/**
	 * @return the oidTipoPremiacion
	 */
	public String getOidTipoPremiacion() {
		return oidTipoPremiacion;
	}

	/**
	 * @param oidTipoPremiacion
	 *            the oidTipoPremiacion to set
	 */
	public void setOidTipoPremiacion(String oidTipoPremiacion) {
		this.oidTipoPremiacion = oidTipoPremiacion;
	}

	/**
	 * @return the numeroNiveles
	 */
	public String getNumeroNiveles() {
		return numeroNiveles;
	}

	/**
	 * @param numeroNiveles
	 *            the numeroNiveles to set
	 */
	public void setNumeroNiveles(String numeroNiveles) {
		this.numeroNiveles = numeroNiveles;
	}

	/**
	 * @return the codigoPeriodoDespacho
	 */
	public String getCodigoPeriodoDespacho() {
		return codigoPeriodoDespacho;
	}

	/**
	 * @param codigoPeriodoDespacho
	 *            the codigoPeriodoDespacho to set
	 */
	public void setCodigoPeriodoDespacho(String codigoPeriodoDespacho) {
		this.codigoPeriodoDespacho = codigoPeriodoDespacho;
	}

	/**
	 * @return the codigoPeriodoDespachoInicio
	 */
	public String getCodigoPeriodoDespachoInicio() {
		return codigoPeriodoDespachoInicio;
	}

	/**
	 * @param codigoPeriodoDespachoInicio
	 *            the codigoPeriodoDespachoInicio to set
	 */
	public void setCodigoPeriodoDespachoInicio(
			String codigoPeriodoDespachoInicio) {
		this.codigoPeriodoDespachoInicio = codigoPeriodoDespachoInicio;
	}

	/**
	 * @return the indicadorPremiosElectivos
	 */
	public String getIndicadorPremiosElectivos() {
		return indicadorPremiosElectivos;
	}

	/**
	 * @param indicadorPremiosElectivos
	 *            the indicadorPremiosElectivos to set
	 */
	public void setIndicadorPremiosElectivos(String indicadorPremiosElectivos) {
		this.indicadorPremiosElectivos = indicadorPremiosElectivos;
	}

	/**
	 * @return the oidTipoEleccion
	 */
	public String getOidTipoEleccion() {
		return oidTipoEleccion;
	}

	/**
	 * @param oidTipoEleccion
	 *            the oidTipoEleccion to set
	 */
	public void setOidTipoEleccion(String oidTipoEleccion) {
		this.oidTipoEleccion = oidTipoEleccion;
	}

	/**
	 * @return the premiosAcumulativosNiveles
	 */
	public String getPremiosAcumulativosNiveles() {
		return premiosAcumulativosNiveles;
	}

	/**
	 * @param premiosAcumulativosNiveles
	 *            the premiosAcumulativosNiveles to set
	 */
	public void setPremiosAcumulativosNiveles(String premiosAcumulativosNiveles) {
		this.premiosAcumulativosNiveles = premiosAcumulativosNiveles;
	}

	/**
	 * @return the hastaNivel
	 */
	public String getHastaNivel() {
		return hastaNivel;
	}

	/**
	 * @param hastaNivel
	 *            the hastaNivel to set
	 */
	public void setHastaNivel(String hastaNivel) {
		this.hastaNivel = hastaNivel;
	}

	/**
	 * @return the indicadorNivelesRotativos
	 */
	public String getIndicadorNivelesRotativos() {
		return indicadorNivelesRotativos;
	}

	/**
	 * @param indicadorNivelesRotativos
	 *            the indicadorNivelesRotativos to set
	 */
	public void setIndicadorNivelesRotativos(String indicadorNivelesRotativos) {
		this.indicadorNivelesRotativos = indicadorNivelesRotativos;
	}

	/**
	 * @return the numeroRotaciones
	 */
	public String getNumeroRotaciones() {
		return numeroRotaciones;
	}

	/**
	 * @param numeroRotaciones
	 *            the numeroRotaciones to set
	 */
	public void setNumeroRotaciones(String numeroRotaciones) {
		this.numeroRotaciones = numeroRotaciones;
	}

	/**
	 * @return the accesoNivelSuperior
	 */
	public String getAccesoNivelSuperior() {
		return accesoNivelSuperior;
	}

	/**
	 * @param accesoNivelSuperior
	 *            the accesoNivelSuperior to set
	 */
	public void setAccesoNivelSuperior(String accesoNivelSuperior) {
		this.accesoNivelSuperior = accesoNivelSuperior;
	}

	/**
	 * @return the listNivelesPremiacion
	 */
	public List getListNivelesPremiacion() {
		return listNivelesPremiacion;
	}

	/**
	 * @param listNivelesPremiacion
	 *            the listNivelesPremiacion to set
	 */
	public void setListNivelesPremiacion(List listNivelesPremiacion) {
		this.listNivelesPremiacion = listNivelesPremiacion;
	}

	/**
	 * @return the codigoPeriodoProceso
	 */
	public String getCodigoPeriodoProceso() {
		return codigoPeriodoProceso;
	}

	/**
	 * @param codigoPeriodoProceso
	 *            the codigoPeriodoProceso to set
	 */
	public void setCodigoPeriodoProceso(String codigoPeriodoProceso) {
		this.codigoPeriodoProceso = codigoPeriodoProceso;
	}

	/**
	 * @return the numeroPeriodosSinPedido
	 */
	public String getNumeroPeriodosSinPedido() {
		return numeroPeriodosSinPedido;
	}

	/**
	 * @param numeroPeriodosSinPedido
	 *            the numeroPeriodosSinPedido to set
	 */
	public void setNumeroPeriodosSinPedido(String numeroPeriodosSinPedido) {
		this.numeroPeriodosSinPedido = numeroPeriodosSinPedido;
	}

	/**
	 * @return the indicadorRangoPedidos
	 */
	public String getIndicadorRangoPedidos() {
		return indicadorRangoPedidos;
	}

	/**
	 * @param indicadorRangoPedidos
	 *            the indicadorRangoPedidos to set
	 */
	public void setIndicadorRangoPedidos(String indicadorRangoPedidos) {
		this.indicadorRangoPedidos = indicadorRangoPedidos;
	}

	/**
	 * @return the indicadorPorPedido
	 */
	public String getIndicadorPorPedido() {
		return indicadorPorPedido;
	}

	/**
	 * @param indicadorPorPedido
	 *            the indicadorPorPedido to set
	 */
	public void setIndicadorPorPedido(String indicadorPorPedido) {
		this.indicadorPorPedido = indicadorPorPedido;
	}

	/**
	 * @return the editableCampannaInicio
	 */
	public boolean isEditableCampannaInicio() {
		return editableCampannaInicio;
	}

	/**
	 * @param editableCampannaInicio
	 *            the editableCampannaInicio to set
	 */
	public void setEditableCampannaInicio(boolean editableCampannaInicio) {
		this.editableCampannaInicio = editableCampannaInicio;
	}

	/**
	 * @return the indicadorGrabarSoloPremiacion
	 */
	public boolean isIndicadorGrabarSoloPremiacion() {
		return indicadorGrabarSoloPremiacion;
	}

	/**
	 * @param indicadorGrabarSoloPremiacion
	 *            the indicadorGrabarSoloPremiacion to set
	 */
	public void setIndicadorGrabarSoloPremiacion(
			boolean indicadorGrabarSoloPremiacion) {
		this.indicadorGrabarSoloPremiacion = indicadorGrabarSoloPremiacion;
	}

	/**
	 * @return the indRestriccionMantenimiento
	 */
	public String getIndRestriccionMantenimiento() {
		return indRestriccionMantenimiento;
	}

	/**
	 * @param indRestriccionMantenimiento
	 *            the indRestriccionMantenimiento to set
	 */
	public void setIndRestriccionMantenimiento(
			String indRestriccionMantenimiento) {
		this.indRestriccionMantenimiento = indRestriccionMantenimiento;
	}

	/**
	 * @return the descripcionMensajePuntos
	 */
	public String getDescripcionMensajePuntos() {
		return descripcionMensajePuntos;
	}

	/**
	 * @param descripcionMensajePuntos
	 *            the descripcionMensajePuntos to set
	 */
	public void setDescripcionMensajePuntos(String descripcionMensajePuntos) {
		this.descripcionMensajePuntos = descripcionMensajePuntos;
	}

	/**
	 * @return the oidTipoConcursoReconocimiento
	 */
	public String getOidTipoConcursoReconocimiento() {
		return oidTipoConcursoReconocimiento;
	}

	/**
	 * @param oidTipoConcursoReconocimiento
	 *            the oidTipoConcursoReconocimiento to set
	 */
	public void setOidTipoConcursoReconocimiento(
			String oidTipoConcursoReconocimiento) {
		this.oidTipoConcursoReconocimiento = oidTipoConcursoReconocimiento;
	}

	/**
	 * @return the oidTipoConcursoFicticio
	 */
	public String getOidTipoConcursoFicticio() {
		return oidTipoConcursoFicticio;
	}

	/**
	 * @param oidTipoConcursoFicticio
	 *            the oidTipoConcursoFicticio to set
	 */
	public void setOidTipoConcursoFicticio(String oidTipoConcursoFicticio) {
		this.oidTipoConcursoFicticio = oidTipoConcursoFicticio;
	}

	/**
	 * @return the oidTipoAgrupacionCUV
	 */
	public String getOidTipoAgrupacionCUV() {
		return oidTipoAgrupacionCUV;
	}

	/**
	 * @param oidTipoAgrupacionCUV
	 *            the oidTipoAgrupacionCUV to set
	 */
	public void setOidTipoAgrupacionCUV(String oidTipoAgrupacionCUV) {
		this.oidTipoAgrupacionCUV = oidTipoAgrupacionCUV;
	}

	/**
	 * @return the indicadorGrabarParametrosGenerales
	 */
	public boolean isIndicadorGrabarParametrosGenerales() {
		return indicadorGrabarParametrosGenerales;
	}

	/**
	 * @param indicadorGrabarParametrosGenerales
	 *            the indicadorGrabarParametrosGenerales to set
	 */
	public void setIndicadorGrabarParametrosGenerales(
			boolean indicadorGrabarParametrosGenerales) {
		this.indicadorGrabarParametrosGenerales = indicadorGrabarParametrosGenerales;
	}

	/**
	 * @return the descripcionPrograma
	 */
	public String getDescripcionPrograma() {
		return descripcionPrograma;
	}

	/**
	 * @param descripcionPrograma
	 *            the descripcionPrograma to set
	 */
	public void setDescripcionPrograma(String descripcionPrograma) {
		this.descripcionPrograma = descripcionPrograma;
	}

	/**
	 * @return the editableParametrosGenerales
	 */
	public boolean isEditableParametrosGenerales() {
		return editableParametrosGenerales;
	}

	/**
	 * @param editableParametrosGenerales
	 *            the editableParametrosGenerales to set
	 */
	public void setEditableParametrosGenerales(
			boolean editableParametrosGenerales) {
		this.editableParametrosGenerales = editableParametrosGenerales;
	}

	/**
	 * @return the indicadorComunicacion
	 */
	public String getIndicadorComunicacion() {
		return indicadorComunicacion;
	}

	/**
	 * @param indicadorComunicacion the indicadorComunicacion to set
	 */
	public void setIndicadorComunicacion(String indicadorComunicacion) {
		this.indicadorComunicacion = indicadorComunicacion;
	}

	/**
	 * @return the indicadorCPP
	 */
	public String getIndicadorCPP() {
		return indicadorCPP;
	}

	/**
	 * @param indicadorCPP the indicadorCPP to set
	 */
	public void setIndicadorCPP(String indicadorCPP) {
		this.indicadorCPP = indicadorCPP;
	}

	/**
	 * @return the codigoCPP
	 */
	public String getCodigoCPP() {
		return codigoCPP;
	}

	/**
	 * @param codigoCPP the codigoCPP to set
	 */
	public void setCodigoCPP(String codigoCPP) {
		this.codigoCPP = codigoCPP;
	}

	/**
	 * @return the descripcionCPP
	 */
	public String getDescripcionCPP() {
		return descripcionCPP;
	}

	/**
	 * @param descripcionCPP the descripcionCPP to set
	 */
	public void setDescripcionCPP(String descripcionCPP) {
		this.descripcionCPP = descripcionCPP;
	}

	/**
	 * @return the oidCPP
	 */
	public String getOidCPP() {
		return oidCPP;
	}

	/**
	 * @param oidCPP the oidCPP to set
	 */
	public void setOidCPP(String oidCPP) {
		this.oidCPP = oidCPP;
	}

	/**
	 * @return the codigoTipoDespachoCPP
	 */
	public String getCodigoTipoDespachoCPP() {
		return codigoTipoDespachoCPP;
	}

	/**
	 * @param codigoTipoDespachoCPP the codigoTipoDespachoCPP to set
	 */
	public void setCodigoTipoDespachoCPP(String codigoTipoDespachoCPP) {
		this.codigoTipoDespachoCPP = codigoTipoDespachoCPP;
	}

	/**
	 * @return the indicadorExigePasarPedidoCampanyaAnteriorCPP
	 */
	public String getIndicadorExigePasarPedidoCampanyaAnteriorCPP() {
		return indicadorExigePasarPedidoCampanyaAnteriorCPP;
	}

	/**
	 * @param indicadorExigePasarPedidoCampanyaAnteriorCPP the indicadorExigePasarPedidoCampanyaAnteriorCPP to set
	 */
	public void setIndicadorExigePasarPedidoCampanyaAnteriorCPP(
			String indicadorExigePasarPedidoCampanyaAnteriorCPP) {
		this.indicadorExigePasarPedidoCampanyaAnteriorCPP = indicadorExigePasarPedidoCampanyaAnteriorCPP;
	}

	/**
	 * @return the puntosAbonarRecomendacionEfectivaCPP
	 */
	public String getPuntosAbonarRecomendacionEfectivaCPP() {
		return puntosAbonarRecomendacionEfectivaCPP;
	}

	/**
	 * @param puntosAbonarRecomendacionEfectivaCPP the puntosAbonarRecomendacionEfectivaCPP to set
	 */
	public void setPuntosAbonarRecomendacionEfectivaCPP(
			String puntosAbonarRecomendacionEfectivaCPP) {
		this.puntosAbonarRecomendacionEfectivaCPP = puntosAbonarRecomendacionEfectivaCPP;
	}

	/**
	 * @return the campanyasSinPedidoParaCancelacionPuntosCPP
	 */
	public String getCampanyasSinPedidoParaCancelacionPuntosCPP() {
		return campanyasSinPedidoParaCancelacionPuntosCPP;
	}

	/**
	 * @param campanyasSinPedidoParaCancelacionPuntosCPP the campanyasSinPedidoParaCancelacionPuntosCPP to set
	 */
	public void setCampanyasSinPedidoParaCancelacionPuntosCPP(
			String campanyasSinPedidoParaCancelacionPuntosCPP) {
		this.campanyasSinPedidoParaCancelacionPuntosCPP = campanyasSinPedidoParaCancelacionPuntosCPP;
	}


	/**
	 * @return the porcentajeMaximoDescuentoCPP
	 */
	public String getPorcentajeMaximoDescuentoCPP() {
		return porcentajeMaximoDescuentoCPP;
	}


	/**
	 * @param porcentajeMaximoDescuentoCPP the porcentajeMaximoDescuentoCPP to set
	 */
	public void setPorcentajeMaximoDescuentoCPP(String porcentajeMaximoDescuentoCPP) {
		this.porcentajeMaximoDescuentoCPP = porcentajeMaximoDescuentoCPP;
	}


	/**
	 * @return the tipoCuadre
	 */
	public String getTipoCuadre() {
		return tipoCuadre;
	}


	/**
	 * @param tipoCuadre the tipoCuadre to set
	 */
	public void setTipoCuadre(String tipoCuadre) {
		this.tipoCuadre = tipoCuadre;
	}


	/**
	 * @return the indicadorPriorizaWeb
	 */
	public String getIndicadorPriorizaWeb() {
		return indicadorPriorizaWeb;
	}


	/**
	 * @param indicadorPriorizaWeb the indicadorPriorizaWeb to set
	 */
	public void setIndicadorPriorizaWeb(String indicadorPriorizaWeb) {
		this.indicadorPriorizaWeb = indicadorPriorizaWeb;
	}	
	
	
	
}
