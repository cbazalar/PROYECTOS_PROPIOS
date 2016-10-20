package biz.belcorp.ssicc.web.spusicc.lec.form;

import java.util.Arrays;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoLECProgramaCorporativoForm extends BaseEditForm{

	private static final long serialVersionUID = 7282153652395056973L;
	
		//Datos Generales
		private boolean indicadorEdicionCabecera = true;
		private boolean indicadorEdicionNiveles = true;
		private boolean indicadorEdicionCobranza = true;
		private boolean indicadorEdicionBono = true;
		private boolean indicadorEdicionIncentivos = true;
		private boolean indicadorEdicionCanasta = true;
		private boolean indicadorEdicionGestion = true;
		private boolean indicadorEdicionRanking = true;
		private boolean indicadorMostrarGrabar = true;
		
		private String codigoPais;
		private String codigoPrograma;
		private String descripcionPrograma;
		private String periodoInicio;
		private String periodoFin;
		private Integer menorPesoNivel;
		
		private String indicadorActivoNivel;
		private String indicadorActivoAmbGeoNivel;
		private String indicadorActivoAmbGeoPedidoLider;
		private String indicadorActivoCobTramo;
		private String indicadorActivoCobAmbGeo;
		private String indicadorActivoCob;
		private String indicadorActivoBonCampDesem;
		private String indicadorActivoBonProduc;
		private String indicadorActivoBonNivel;
		private String indicadorActivoBonCicloVida22;
		private String indicadorActivoBonCicloVida33;
		private String indicadorActivoBonCicloVida44;
		private String indicadorActivoBonNivelAce;
		private String indicadorActivoIncAmbGeo;
		private String indicadorActivoIncNivel;
		private String indicadorActivoIncCanasta;
		private String indicadorActivoGestDesem;
		
		private String indicadorActivoCanasta;
		private String mostrarIngresoCanasta;
		private String mostrarIncentivo;
		//Sección
		private String numeroMinimoActSec;
		private String numeroMinimoIngresos;
		private String porcentajeActividad;
		private String alcanceRecomendaciones;
		
		//Ganancia
		private String montoDsctoToleranciaPedidos;
		private String indExigenciaPedidoWeb;
		private String porcentajePenalidad;
		
		private String indGananciaLiderNueva;
		private String numeroCampanasGracia;
		
		private String numeroCampanasCambiarNivel;
		private String numeroCampanasMantNivelGananciaPlus;
		
		private String tabSeleccion;
		
		private String campanyaPremioBuscar;
		
		protected String[] selectedItemsNivel = {};
		protected String selectedItemRangoNivel = null;
		
		protected String[] selectedItemsObjCob = {};
		protected String selectedItemObjCob = null;
		
		
		protected String[] selectedItemsRangoRetencion = {};
		protected String selectedItemRangoRetencion = null;
		
		protected String[] selectedItemsTramos = {};
		protected String selectedItemTramos = null;
		
		protected String[] selectedItemsPremios = {};
		protected String selectedItemPremios = null;
		/// temporalesOtros
		private String programaReconoTmp;
		private String pedidoWebTmp;
		private String actCobranzaTmp;	
		private String insPortalFFVVTmp;
		private String insSistComeTmp;
		private String insPosiLiderTmp;
		private String exigPedPersonaTmp;
		private String exigCursoCambTmp;
		private String excluirTmp;
		private String excluirNivelTmp;
		/// Otros
		private String exigPedPersona;
		private String exigCursoCamb;
		private String excluir;
		private String excluirNivel;
		private String programaRecono;
		private String pedidoWeb;
		private String nroMinActSec;
		private String actCobranza;
		//Inscripcion
		private String insPortalFFVV;
		private String insSistCome;
		private String insPosiLider;
		//Nivel Exito
		private String nivNroCampMante;
		private String nivNroCampTole;
		
		
		//Calendario Cobranza
		private String fechaFeriado;
		private String codigoAlcance;
		private String codigoRegion;
		private String codigoZona;
		private String tipoMedicionCob;
		private String codigoTramo;
		//objetivos cobranza
		private String codigoTipoGeo;
		private String codigoTipoMedi;
		private String nroDiasExtra;
		private String indDiasExtra;
		private String indPais;
		private String campReg;
		private String indExclusion;	
	    private String[] regionObjCob = {};
	    private String[] zonaObjCob = {};
	    private String nroDias;
		private String porcCob;
		private String procMinCob;
		private String numAmbGeo;
		private String region;
		private String zona;
		private String numTramo;
		protected String[] selectedItemsAmbGeo = {};
		protected String selectedItemAmbGeo = null;
		
		private String[] listaGrillaCobranzaCampo01= {};
		private String[] listaGrillaCobranzaCampo02= {};
		private String[] listaGrillaCobranzaCampo03= {};
		private String[] listaGrillaCobranzaCampo04= {};
		
		//Gestion Desempeńo
		private String[] listaCampanias = {};
		private String[] listaCampaniaEvaluar = {};
		private String nroAmedirEtapa;
		private String[] tipoDesem = {};
		private String[] nroVecExtIni = {};
		private String[] nroVecExtFin = {};
		private String[] listaGrillaDesempenioCampo01= {};
		private String[] listaGrillaDesempenioCampo02= {};

		protected String[] selectedGestionDesemp = {};
		private String indicadorExitoNuevas;
		//Bonos
		private String[] listaCamp = {};
		private String[] listaCampEvaluar = {};
		private String listaCampBono;
		private String listaCampBonoEvaluar;
		private String mostrarBonoLanzamiento;
		private String mostrarBonoCicloVida;
		private String mostrarCambioNivelAcelerado;
		private String mostrarPeriodoGraciaNueva;
		private String campLanzamiento;
		private String tipoMedicion;
		private String nivelRete22;
		private String nivelRete33;
		private String nivelRete44;
		private String nivelCamAce;
		private String camPerGrac;
		private String codNivelRete22;
		private String codNivelRete33;
		private String codNivelRete44;
		private String codNivelCamAce;
		private String codCamPerGrac;
		private String tipoBono;
		private String codTipoBono;
		
		private String nivelLanzamiento;
		private String codNivelLanzamiento;
		private String bononivel;
		private String codBonoNivel;
		private String secuenciaBonoNivel;
		private String campanaLanzamiento;
		private String nroLanzamiento;
		private String valorPorcObj;
		private String valorPorSobreCum;
		private String nroMinIngr;
		private String nroMaxIngr;
		private String porReten;
		private String codTipoPrem;
		private String tipoPrem;
		private String codTipoMedicion;
		private String nroSecuenciaProducto;
		private String[] selelanza = {};
		private String[] selebono = {};
		private String[] seleretenivel = {};
		private String[] selerete= {};
	    private String[] selerete22 = {};
	    private String[] selerete33 = {};
	    private String[] selerete44 = {};
	    private String[] seleretenivelAce = {};
	    private String[] selePeriGra = {};
	    private String exigencia;
	    private String sobExigencia;
	    private String nroMinIng22;
	    private String porRetExig22;
	    private String nroMinIng33;
	    private String porRetExig33;
	    private String nroMinIng44;
	    private String porRetExig44;
	    private String nroCampMax;
	    private String nroMinIncPed;
	    private String codigoSAProductoLanza;
	    private String campLanzamientoProdLanza;
	    private String descripcionProductoLanza;
	    private String monPrem;
	    
	    private String codNivelRete;
	    private String nroMinIng;
	    private String nroMaxIng;
	    private String porRetExig;
	    private String nivelRete;
	    
	    private String nroMinRete;
	    private String nroMaxRete;
	    
	    private String[] listaGrillaBonoRetencion01= {};
	    private String[] listaGrillaBonoRetencion02= {};
	    private String[] listaGrillaBonoRetencion03= {};
	    private String[] listaGrillaBonoRetencion04= {};
	        
	    private String[] listaGrillaNivelAceleradoCampo01= {};
	    private String[] listaGrillaNivelAceleradoCampo02= {};
	    private String[] listaGrillaNivelAceleradoCampo03= {};
	    
	    private String[] listaGrillaNBonoLanzamientoCampo01= {};
	    private String[] listaGrillaNBonoLanzamientoCampo02= {};
	    
	    private String montoPremioCicloVida;
	    private String montoPremioNivelAcelerado;
	    private String campannaInicialNivelAcelerado;
	    private String campannaFinalNivelAcelerado;
	    private String montoPeriodoGracia;
	    private String codigoNivelPeriodoGracia;
	    private String nivelPeriodoGracia;
	    protected String[] selectedPeriodoGracia = {};
	    private String[] listaGrillaPeriodoGraciaCampo01= {};
	        
	  //Incentivos
	    private String codigoIncTipoGeo;
	    private String codExPedLider;
	  	private String[] listaIncReg = {};
	  	private String[] listaIncRegEvaluar = {};
	  	private String[] listaIncZon = {};
	  	private String[] listaIncZonEvaluar = {};
	  	private String incTipoComi;
	  	private String codTipoComi;
	  	private String incCodNivelTipCom;
	  	private String pagPedConsecu;
	  	private String pagPedNoConsecu;
	  	private String porPedConsecu;
	  	private String porPedNoConsecu;
	  	private String porTolePedi;
	  	private String[] listaGrillaIncentivo1Campo01= {};
	  	private String[] listaGrillaIncentivo1Campo02= {};
	  	private String[] listaGrillaIncentivo1Campo03= {};
	  	private String[] listaGrillaIncentivo3Campo01= {};
	  	private String[] listaGrillaIncentivo3Campo02= {};
	  	private String[] listaGrillaIncentivo3Campo03= {};
		


		private String incCanasProd;
	    private String incCodNivelCanast;
	    private String incCodNivel;
	    private String pagTolePedi;
	    private String incCanasta;
	    private String incExigTramo;
	    private String incCodTramo;
	    private String[] seleIncCanasProd = {};
	  	private String[] seleIncTipoComi = {};
	  	private String[] seleIncMontoRecu = {};
	    private String niveltipoComi;
	    private String nivelCanast;
	    private String regionSelec;
	    private String zonaSelec;
	  	private String canasta;
	  	private String tramo;
	  	private String tmpZonaSele;
	  	private String tmpRegionSele;
	  	
	  	private String[] listaGrillaCanasta1Campo01= {};
	  	private String[] listaGrillaCanasta2Campo01= {};
	  	private String campanyaActivacionCanastaIncentivos;	  
	    private String codigoRangoComision;
	    private String descriRangoComision;
	    private String codigoNivelRango;
	  	
		//NIVEL
		private String codigoNivel;
		private String nivel;
		private String nroPedidoInicial;
		private String nroPedidoFinal;
		private String gananciaCumplimiento;
		private String gananciaSobrecumplimiento;
		private String estado;
		private String tolePedido;
	    private String tolePorc;
	    private String codigoTipoComi;
	    private String porcComiPediCons;
	    private String porcComiPediNCon;
	    private String porcComiTole;
	    private String montPediCons;
	    private String montPediNCon;
	    private String montTole;
	    private String periodoInicioNivel;
		private String periodoFinNivel;
		private String codigoTipoGeoNivel;
	    private String[] regionNiv = {};
	    private String[] zonaNiv = {};
	    private String mostrarExigeCurso;
	    private String[] seleAmbGeoNiv = {};
	    private String[] seleAmbGeoPedLid = {};
	    
	    private String[] listaGrillaNivelCampo01= {};
		private String[] listaGrillaNivelCampo02= {};
		private String[] listaGrillaNivelCampo03= {};
		private String[] listaGrillaNivelCampo04= {};
		private String[] listaGrillaNivelCampo05= {};
	    private String campannaInicialNivel;
	    private String campannaFinalNivel;
	    private String montoVentaMinimo;
	    private String montoVentaMaximo;
	    private String montoVentaTolerancia;
	    
	    private String indicadorModificarNivelTramo = Constants.NO;
	    private int idModificarNivelTramo;
	    
	    //RCR PER-SICC-2015-0429
	    private String ingresoMeta;
	    private String indicadorConsiderarMetasIngresos;
	    
	    //PER-SiCC-2015-0548
	    private String condCapitalizacion;
	    private String metaCapitalizacion;
	    private String indicadorconsiderarCapitalizacion;
	    
	    
	    //-- RANKING --
	    private String codigoTipoRanking;
	    private String campanyaInicio;
	    private String campanyaFin;
	    private String numeroCampanyasCumplimentoPedido;
	    private String codigoTipoMedicion;
	    
	    private String[] selectedItemsRanking = {};
	    private String[] selectedItemsRankingNivel = {};
	    
	    private String[] listaGrillaRankingCampanyaInicio = {};
	    private String[] listaGrillaRankingCampanyaFin = {};
	    private String[] listaGrillaRankingNumeroCampanyasCumplimentoPedido = {};
	    private String[] listaGrillaRankingCodigoTipoMedicion = {};
	    
	   
	    private String indicadorFeriado;
	    
	    private String indicadorTipoMeta;
	    private String codigoTipoPremiacion;
	    private String codigoTipoCanasta;
	    private String indicadorConsiderarNuevas;
	    
	    
	    /* (non-Javadoc)
		 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
		 */
		public void reset() {
			this.descripcionPrograma = "";
			this.periodoInicio = "";
			this.periodoFin = "";	
			this.nroSecuenciaProducto = "";
			this.numeroMinimoActSec = "";
			this.numeroMinimoIngresos = "";
			this.porcentajeActividad = "";
			this.alcanceRecomendaciones = "";
			
			this.montoDsctoToleranciaPedidos = "";
			this.indExigenciaPedidoWeb = Constants.NUMERO_CERO;
			this.porcentajePenalidad = "";
			
			this.indGananciaLiderNueva = Constants.NUMERO_CERO;
			this.numeroCampanasGracia = "";
			
			this.numeroCampanasCambiarNivel = "";
			this.numeroCampanasMantNivelGananciaPlus = "";
			
			this.nroMinActSec = "";
			this.nivNroCampMante = "";
			this.nivNroCampTole = "";
			this.fechaFeriado = "";
			this.programaRecono = "";
			this.pedidoWeb = "";
			this.actCobranza = "";
			this.insPortalFFVV = "";
			this.insSistCome = "";
			this.insPosiLider = "";
			this.nroAmedirEtapa = "";
			this.incExigTramo = "";
			
			this.nombreCanasta = "";
			this.valorCanasta = "";
			this.numeroProductosCanasta = "";	    
			this.codigoTipoOfertaProductoCanasta = "";
			this.codigoSAPProductoCanasta = "";
			this.descripcionProductoCanasta = "";
			this.precioProductoCanasta = "";
			this.codigoFormaPagoProductoCanasta = Constants.LEC_CODIGO_FORMA_PAGO_PRODUCTO_CANASTA;
		    this.selectedItemsCanasta = new String[]{};
		    this.selectedItemsProductoCanasta = new String[]{};
		    this.indicadorModificarNivelTramo = Constants.NO;
		    this.idModificarNivelTramo = 0;
		    this.indicadorFeriado = Constants.NUMERO_CERO;
		    
		    //RCR PER-SICC-2015-0429
		    this.ingresoMeta = Constants.NUMERO_CERO;
		    //PER-SiCC-2015-0548
		    this.metaCapitalizacion = Constants.NUMERO_CERO;
		}
		
	    
	    
	    public String getPorPedConsecu() {
			return porPedConsecu;
		}
	    
		public String getPorPedNoConsecu() {
			return porPedNoConsecu;
		}

		public String getPorTolePedi() {
			return porTolePedi;
		}

		public void setPorPedConsecu(String porPedConsecu) {
			this.porPedConsecu = porPedConsecu;
		}

		public void setPorPedNoConsecu(String porPedNoConsecu) {
			this.porPedNoConsecu = porPedNoConsecu;
		}

		public void setPorTolePedi(String porTolePedi) {
			this.porTolePedi = porTolePedi;
		}

	    public String getExigPedPersonaTmp() {
			return exigPedPersonaTmp;
		}

		public String getExigCursoCambTmp() {
			return exigCursoCambTmp;
		}

		public String getExcluirTmp() {
			return excluirTmp;
		}

		public String getExigPedPersona() {
			return exigPedPersona;
		}

		public String getExigCursoCamb() {
			return exigCursoCamb;
		}

		public String getExcluir() {
			return excluir;
		}

		public void setExigPedPersonaTmp(String exigPedPersonaTmp) {
			this.exigPedPersonaTmp = exigPedPersonaTmp;
		}

		public void setExigCursoCambTmp(String exigCursoCambTmp) {
			this.exigCursoCambTmp = exigCursoCambTmp;
		}

		public void setExcluirTmp(String excluirTmp) {
			this.excluirTmp = excluirTmp;
		}

		public void setExigPedPersona(String exigPedPersona) {
			this.exigPedPersona = exigPedPersona;
		}

		public void setExigCursoCamb(String exigCursoCamb) {
			this.exigCursoCamb = exigCursoCamb;
		}

		public void setExcluir(String excluir) {
			this.excluir = excluir;
		}

		public String getIndicadorActivoNivel() {
			return indicadorActivoNivel;
		}

		public String getIndicadorActivoAmbGeoNivel() {
			return indicadorActivoAmbGeoNivel;
		}

		public String getIndicadorActivoCobTramo() {
			return indicadorActivoCobTramo;
		}

		public String getIndicadorActivoCobAmbGeo() {
			return indicadorActivoCobAmbGeo;
		}

		public String getIndicadorActivoCob() {
			return indicadorActivoCob;
		}

		public String getIndicadorActivoIncNivel() {
			return indicadorActivoIncNivel;
		}

		public String getIndicadorActivoIncCanasta() {
			return indicadorActivoIncCanasta;
		}

		public String getIndicadorActivoGestDesem() {
			return indicadorActivoGestDesem;
		}

		public String getIndicadorActivoBonCampDesem() {
			return indicadorActivoBonCampDesem;
		}

		public String getIndicadorActivoBonProduc() {
			return indicadorActivoBonProduc;
		}

		public String getIndicadorActivoBonNivel() {
			return indicadorActivoBonNivel;
		}

		public String getIndicadorActivoBonCicloVida22() {
			return indicadorActivoBonCicloVida22;
		}

		public String getIndicadorActivoBonCicloVida33() {
			return indicadorActivoBonCicloVida33;
		}

		public String getIndicadorActivoBonCicloVida44() {
			return indicadorActivoBonCicloVida44;
		}

		public String getIndicadorActivoBonNivelAce() {
			return indicadorActivoBonNivelAce;
		}

		public String getIndicadorActivoCanasta() {
			return indicadorActivoCanasta;
		}

		public void setIndicadorActivoNivel(String indicadorActivoNivel) {
			this.indicadorActivoNivel = indicadorActivoNivel;
		}

		public void setIndicadorActivoAmbGeoNivel(String indicadorActivoAmbGeoNivel) {
			this.indicadorActivoAmbGeoNivel = indicadorActivoAmbGeoNivel;
		}

		public void setIndicadorActivoCobTramo(String indicadorActivoCobTramo) {
			this.indicadorActivoCobTramo = indicadorActivoCobTramo;
		}

		public void setIndicadorActivoCobAmbGeo(String indicadorActivoCobAmbGeo) {
			this.indicadorActivoCobAmbGeo = indicadorActivoCobAmbGeo;
		}

		public void setIndicadorActivoCob(String indicadorActivoCob) {
			this.indicadorActivoCob = indicadorActivoCob;
		}

		public void setIndicadorActivoIncNivel(String indicadorActivoIncNivel) {
			this.indicadorActivoIncNivel = indicadorActivoIncNivel;
		}

		public void setIndicadorActivoIncCanasta(String indicadorActivoIncCanasta) {
			this.indicadorActivoIncCanasta = indicadorActivoIncCanasta;
		}

		public void setIndicadorActivoGestDesem(String indicadorActivoGestDesem) {
			this.indicadorActivoGestDesem = indicadorActivoGestDesem;
		}

		public void setIndicadorActivoBonCampDesem(String indicadorActivoBonCampDesem) {
			this.indicadorActivoBonCampDesem = indicadorActivoBonCampDesem;
		}

		public void setIndicadorActivoBonProduc(String indicadorActivoBonProduc) {
			this.indicadorActivoBonProduc = indicadorActivoBonProduc;
		}

		public void setIndicadorActivoBonNivel(String indicadorActivoBonNivel) {
			this.indicadorActivoBonNivel = indicadorActivoBonNivel;
		}

		public void setIndicadorActivoBonCicloVida22(
				String indicadorActivoBonCicloVida22) {
			this.indicadorActivoBonCicloVida22 = indicadorActivoBonCicloVida22;
		}

		public void setIndicadorActivoBonCicloVida33(
				String indicadorActivoBonCicloVida33) {
			this.indicadorActivoBonCicloVida33 = indicadorActivoBonCicloVida33;
		}

		public void setIndicadorActivoBonCicloVida44(
				String indicadorActivoBonCicloVida44) {
			this.indicadorActivoBonCicloVida44 = indicadorActivoBonCicloVida44;
		}

		public void setIndicadorActivoBonNivelAce(String indicadorActivoBonNivelAce) {
			this.indicadorActivoBonNivelAce = indicadorActivoBonNivelAce;
		}

		public void setIndicadorActivoCanasta(String indicadorActivoCanasta) {
			this.indicadorActivoCanasta = indicadorActivoCanasta;
		}
		
	    public String getProgramaReconoTmp() {
			return programaReconoTmp;
		}

		public String getPedidoWebTmp() {
			return pedidoWebTmp;
		}

		public String getActCobranzaTmp() {
			return actCobranzaTmp;
		}

		public String getInsPortalFFVVTmp() {
			return insPortalFFVVTmp;
		}

		public String getInsSistComeTmp() {
			return insSistComeTmp;
		}

		public String getInsPosiLiderTmp() {
			return insPosiLiderTmp;
		}

		public void setProgramaReconoTmp(String programaReconoTmp) {
			this.programaReconoTmp = programaReconoTmp;
		}

		public void setPedidoWebTmp(String pedidoWebTmp) {
			this.pedidoWebTmp = pedidoWebTmp;
		}

		public void setActCobranzaTmp(String actCobranzaTmp) {
			this.actCobranzaTmp = actCobranzaTmp;
		}

		public void setInsPortalFFVVTmp(String insPortalFFVVTmp) {
			this.insPortalFFVVTmp = insPortalFFVVTmp;
		}

		public void setInsSistComeTmp(String insSistComeTmp) {
			this.insSistComeTmp = insSistComeTmp;
		}

		public void setInsPosiLiderTmp(String insPosiLiderTmp) {
			this.insPosiLiderTmp = insPosiLiderTmp;
		}

		public String getPeriodoInicioNivel() {
			return periodoInicioNivel;
		}

		public String getPeriodoFinNivel() {
			return periodoFinNivel;
		}

		public String getCodigoTipoGeoNivel() {
			return codigoTipoGeoNivel;
		}

		public String[] getRegionNiv() {
			return regionNiv;
		}

		public String[] getZonaNiv() {
			return zonaNiv;
		}

		public String getMostrarExigeCurso() {
			return mostrarExigeCurso;
		}

		public String[] getSeleAmbGeoNiv() {
			return seleAmbGeoNiv;
		}

		public void setPeriodoInicioNivel(String periodoInicioNivel) {
			this.periodoInicioNivel = periodoInicioNivel;
		}

		public void setPeriodoFinNivel(String periodoFinNivel) {
			this.periodoFinNivel = periodoFinNivel;
		}

		public void setCodigoTipoGeoNivel(String codigoTipoGeoNivel) {
			this.codigoTipoGeoNivel = codigoTipoGeoNivel;
		}

		public void setRegionNiv(String[] regionNiv) {
			this.regionNiv = regionNiv;
		}

		public void setZonaNiv(String[] zonaNiv) {
			this.zonaNiv = zonaNiv;
		}

		public void setMostrarExigeCurso(String mostrarExigeCurso) {
			this.mostrarExigeCurso = mostrarExigeCurso;
		}

		public void setSeleAmbGeoNiv(String[] seleAmbGeoNiv) {
			this.seleAmbGeoNiv = seleAmbGeoNiv;
		}


		//tipo desempenio
	    private String codTipoDesem;
		private String nroVecsExitosaIni;
	    private String nroVecsExitosaFin;
	    private String descripTipoDesem;
	    private String selecCampEvaluar;
	    
	    //Canasta
	    private String nombreCanasta;
	    private String valorCanasta;
	    private String numeroProductosCanasta;
	    
	    private String codigoTipoOfertaProductoCanasta;
	    private String codigoSAPProductoCanasta;
	    private String descripcionProductoCanasta;
	    private String precioProductoCanasta;
	    private String codigoFormaPagoProductoCanasta;
	    
	    private String selectedItemsCanasta[] = {};
	    private String selectedItemsProductoCanasta[] = {};
	    
	    private String campanyaRegistro;
	    private String campanyaActivacionCanasta;
	        
	    
		public String getCodigoSAProductoLanza() {
			return codigoSAProductoLanza;
		}

		public String getCampLanzamientoProdLanza() {
			return campLanzamientoProdLanza;
		}

		public String getDescripcionProductoLanza() {
			return descripcionProductoLanza;
		}

		public void setCodigoSAProductoLanza(String codigoSAProductoLanza) {
			this.codigoSAProductoLanza = codigoSAProductoLanza;
		}

		public void setCampLanzamientoProdLanza(String campLanzamientoProdLanza) {
			this.campLanzamientoProdLanza = campLanzamientoProdLanza;
		}

		public void setDescripcionProductoLanza(String descripcionProductoLanza) {
			this.descripcionProductoLanza = descripcionProductoLanza;
		}

		public String[] getSelectedGestionDesemp() {
			return selectedGestionDesemp;
		}

		public void setSelectedGestionDesemp(String[] selectedGestionDesemp) {
			this.selectedGestionDesemp = selectedGestionDesemp;
		}	   
	    
	    public String[] getSelectedItemsAmbGeo() {
			return selectedItemsAmbGeo;
		}

		public String getSelectedItemAmbGeo() {
			return selectedItemAmbGeo;
		}

		public void setSelectedItemsAmbGeo(String[] selectedItemsAmbGeo) {
			this.selectedItemsAmbGeo = selectedItemsAmbGeo;
		}

		public void setSelectedItemAmbGeo(String selectedItemAmbGeo) {
			this.selectedItemAmbGeo = selectedItemAmbGeo;
		}

		public String getCodTipoDesem() {
			return codTipoDesem;
		}

		public String getNroVecsExitosaIni() {
			return nroVecsExitosaIni;
		}

		public String getNroVecsExitosaFin() {
			return nroVecsExitosaFin;
		}

		public void setCodTipoDesem(String codTipoDesem) {
			this.codTipoDesem = codTipoDesem;
		}

		public void setNroVecsExitosaIni(String nroVecsExitosaIni) {
			this.nroVecsExitosaIni = nroVecsExitosaIni;
		}

		public void setNroVecsExitosaFin(String nroVecsExitosaFin) {
			this.nroVecsExitosaFin = nroVecsExitosaFin;
		}	
		
		public String getTmpZonaSele() {
			return tmpZonaSele;
		}
		
		public String getTmpRegionSele() {
			return tmpRegionSele;
		}

		public void setTmpZonaSele(String tmpZonaSele) {
			this.tmpZonaSele = tmpZonaSele;
		}

		public void setTmpRegionSele(String tmpRegionSele) {
			this.tmpRegionSele = tmpRegionSele;
		}

		public String getNiveltipoComi() {
			return niveltipoComi;
		}

		public String getNivelCanast() {
			return nivelCanast;
		}
		
		public String getRegionSelec() {
			return regionSelec;
		}

		public String getZonaSelec() {
			return zonaSelec;
		}

		public void setNiveltipoComi(String niveltipoComi) {
			this.niveltipoComi = niveltipoComi;
		}
		
		public void setNivelCanast(String nivelCanast) {
			this.nivelCanast = nivelCanast;
		}
		
		public void setRegionSelec(String regionSelec) {
			this.regionSelec = regionSelec;
		}

		public void setZonaSelec(String zonaSelec) {
			this.zonaSelec = zonaSelec;
		}

		public String getCodigoIncTipoGeo() {
			return codigoIncTipoGeo;
		}

		public void setCodigoIncTipoGeo(String codigoIncTipoGeo) {
			this.codigoIncTipoGeo = codigoIncTipoGeo;
		}

		public String getPagTolePedi() {
			return pagTolePedi;
		}

		public void setPagTolePedi(String pagTolePedi) {
			this.pagTolePedi = pagTolePedi;
		}
	  	
		public String getCodExPedLider() {
			return codExPedLider;
		}

		public String[] getListaIncReg() {
			return listaIncReg;
		}

		public String[] getListaIncRegEvaluar() {
			return listaIncRegEvaluar;
		}

		public String[] getListaIncZon() {
			return listaIncZon;
		}

		public String[] getListaIncZonEvaluar() {
			return listaIncZonEvaluar;
		}

		public String getIncTipoComi() {
			return incTipoComi;
		}

		public String getCodTipoComi() {
			return codTipoComi;
		}

		public String getIncCodNivelTipCom() {
			return incCodNivelTipCom;
		}

		public String getPagPedConsecu() {
			return pagPedConsecu;
		}

		public String getPagPedNoConsecu() {
			return pagPedNoConsecu;
		}

		public String getIncCanasProd() {
			return incCanasProd;
		}

		public String getIncCodNivelCanast() {
			return incCodNivelCanast;
		}

		public String getIncCanasta() {
			return incCanasta;
		}		

		public String getIncExigTramo() {
			return incExigTramo;
		}

		public String getIncCodTramo() {
			return incCodTramo;
		}

		public String[] getSeleIncCanasProd() {
			return seleIncCanasProd;
		}

		public String[] getSeleIncTipoComi() {
			return seleIncTipoComi;
		}

		public void setCodExPedLider(String codExPedLider) {
			this.codExPedLider = codExPedLider;
		}

		public void setListaIncReg(String[] listaIncReg) {
			this.listaIncReg = listaIncReg;
		}

		public void setListaIncRegEvaluar(String[] listaIncRegEvaluar) {
			this.listaIncRegEvaluar = listaIncRegEvaluar;
		}

		public void setListaIncZon(String[] listaIncZon) {
			this.listaIncZon = listaIncZon;
		}

		public void setListaIncZonEvaluar(String[] listaIncZonEvaluar) {
			this.listaIncZonEvaluar = listaIncZonEvaluar;
		}

		public void setIncTipoComi(String incTipoComi) {
			this.incTipoComi = incTipoComi;
		}

		public void setCodTipoComi(String codTipoComi) {
			this.codTipoComi = codTipoComi;
		}

		public void setIncCodNivelTipCom(String incCodNivelTipCom) {
			this.incCodNivelTipCom = incCodNivelTipCom;
		}

		public void setPagPedConsecu(String pagPedConsecu) {
			this.pagPedConsecu = pagPedConsecu;
		}

		public void setPagPedNoConsecu(String pagPedNoConsecu) {
			this.pagPedNoConsecu = pagPedNoConsecu;
		}

		public void setIncCanasProd(String incCanasProd) {
			this.incCanasProd = incCanasProd;
		}

		public void setIncCodNivelCanast(String incCodNivelCanast) {
			this.incCodNivelCanast = incCodNivelCanast;
		}

		public void setIncCanasta(String incCanasta) {
			this.incCanasta = incCanasta;
		}

		public void setIncExigTramo(String incExigTramo) {
			this.incExigTramo = incExigTramo;
		}

		public void setIncCodTramo(String incCodTramo) {
			this.incCodTramo = incCodTramo;
		}

		public void setSeleIncCanasProd(String[] seleIncCanasProd) {
			this.seleIncCanasProd = seleIncCanasProd;
		}

		public void setSeleIncTipoComi(String[] seleIncTipoComi) {
			this.seleIncTipoComi = seleIncTipoComi;
		}

		public String getCodNivelRete22() {
			return codNivelRete22;
		}

		public String getCodNivelRete33() {
			return codNivelRete33;
		}

		public String getCodNivelRete44() {
			return codNivelRete44;
		}

		public String getCodNivelCamAce() {
			return codNivelCamAce;
		}

		public String getCodCamPerGrac() {
			return codCamPerGrac;
		}
		
		public void setCodNivelRete22(String codNivelRete22) {
			this.codNivelRete22 = codNivelRete22;
		}

		public void setCodNivelRete33(String codNivelRete33) {
			this.codNivelRete33 = codNivelRete33;
		}

		public void setCodNivelRete44(String codNivelRete44) {
			this.codNivelRete44 = codNivelRete44;
		}

		public void setCodNivelCamAce(String codNivelCamAce) {
			this.codNivelCamAce = codNivelCamAce;
		}

		public void setCodCamPerGrac(String codCamPerGrac) {
			this.codCamPerGrac = codCamPerGrac;
		}

		public String[] getSelePeriGra() {
			return selePeriGra;
		}

		public String getExigencia() {
			return exigencia;
		}

		public String getSobExigencia() {
			return sobExigencia;
		}

		public String getNroMinIng22() {
			return nroMinIng22;
		}

		public String getPorRetExig22() {
			return porRetExig22;
		}

		public String getNroMinIng33() {
			return nroMinIng33;
		}

		public String getPorRetExig33() {
			return porRetExig33;
		}

		public String getNroMinIng44() {
			return nroMinIng44;
		}

		public String getPorRetExig44() {
			return porRetExig44;
		}

		public String getNroCampMax() {
			return nroCampMax;
		}

		public String getNroMinIncPed() {
			return nroMinIncPed;
		}

		public void setSelePeriGra(String[] selePeriGra) {
			this.selePeriGra = selePeriGra;
		}

		public void setExigencia(String exigencia) {
			this.exigencia = exigencia;
		}

		public void setSobExigencia(String sobExigencia) {
			this.sobExigencia = sobExigencia;
		}

		public void setNroMinIng22(String nroMinIng22) {
			this.nroMinIng22 = nroMinIng22;
		}

		public void setPorRetExig22(String porRetExig22) {
			this.porRetExig22 = porRetExig22;
		}

		public void setNroMinIng33(String nroMinIng33) {
			this.nroMinIng33 = nroMinIng33;
		}

		public void setPorRetExig33(String porRetExig33) {
			this.porRetExig33 = porRetExig33;
		}

		public void setNroMinIng44(String nroMinIng44) {
			this.nroMinIng44 = nroMinIng44;
		}

		public void setPorRetExig44(String porRetExig44) {
			this.porRetExig44 = porRetExig44;
		}

		public void setNroCampMax(String nroCampMax) {
			this.nroCampMax = nroCampMax;
		}

		public void setNroMinIncPed(String nroMinIncPed) {
			this.nroMinIncPed = nroMinIncPed;
		}

		public String getMostrarBonoLanzamiento() {
			return mostrarBonoLanzamiento;
		}

		public String getMostrarBonoCicloVida() {
			return mostrarBonoCicloVida;
		}

		public String getMostrarCambioNivelAcelerado() {
			return mostrarCambioNivelAcelerado;
		}

		public String getMostrarPeriodoGraciaNueva() {
			return mostrarPeriodoGraciaNueva;
		}

		public void setMostrarBonoLanzamiento(String mostrarBonoLanzamiento) {
			this.mostrarBonoLanzamiento = mostrarBonoLanzamiento;
		}
		
		public void setMostrarBonoCicloVida(String mostrarBonoCicloVida) {
			this.mostrarBonoCicloVida = mostrarBonoCicloVida;
		}

		public void setMostrarCambioNivelAcelerado(String mostrarCambioNivelAcelerado) {
			this.mostrarCambioNivelAcelerado = mostrarCambioNivelAcelerado;
		}

		public void setMostrarPeriodoGraciaNueva(String mostrarPeriodoGraciaNueva) {
			this.mostrarPeriodoGraciaNueva = mostrarPeriodoGraciaNueva;
		}	
	   
		public String getTipoBono() {
			return tipoBono;
		}

		public String getCodTipoBono() {
			return codTipoBono;
		}

		public String getBononivel() {
			return bononivel;
		}

		public String getCodBonoNivel() {
			return codBonoNivel;
		}

		public String getSecuenciaBonoNivel() {
			return secuenciaBonoNivel;
		}

		public String getCampanaLanzamiento() {
			return campanaLanzamiento;
		}

		public String getNroLanzamiento() {
			return nroLanzamiento;
		}

		public String getValorPorcObj() {
			return valorPorcObj;
		}

		public String getValorPorSobreCum() {
			return valorPorSobreCum;
		}

		public String getNroMinIngr() {
			return nroMinIngr;
		}
		
		public String getPorReten() {
			return porReten;
		}

		public String getCodTipoPrem() {
			return codTipoPrem;
		}

		public String getTipoPrem() {
			return tipoPrem;
		}

		public String getCodTipoMedicion() {
			return codTipoMedicion;
		}

		public String getNroSecuenciaProducto() {
			return nroSecuenciaProducto;
		}

		public String[] getSeleretenivel() {
			return seleretenivel;
		}

		public String[] getSelerete22() {
			return selerete22;
		}

		public String[] getSelerete33() {
			return selerete33;
		}

		public String[] getSelerete44() {
			return selerete44;
		}

		public String[] getSelelanza() {
			return selelanza;
		}

		public String[] getSelebono() {
			return selebono;
		}

		public void setTipoBono(String tipoBono) {
			this.tipoBono = tipoBono;
		}

		public void setCodTipoBono(String codTipoBono) {
			this.codTipoBono = codTipoBono;
		}

		public void setBononivel(String bononivel) {
			this.bononivel = bononivel;
		}

		public void setCodBonoNivel(String codBonoNivel) {
			this.codBonoNivel = codBonoNivel;
		}

		public void setSecuenciaBonoNivel(String secuenciaBonoNivel) {
			this.secuenciaBonoNivel = secuenciaBonoNivel;
		}

		public void setCampanaLanzamiento(String campanaLanzamiento) {
			this.campanaLanzamiento = campanaLanzamiento;
		}

		public void setNroLanzamiento(String nroLanzamiento) {
			this.nroLanzamiento = nroLanzamiento;
		}

		public void setValorPorcObj(String valorPorcObj) {
			this.valorPorcObj = valorPorcObj;
		}

		public void setValorPorSobreCum(String valorPorSobreCum) {
			this.valorPorSobreCum = valorPorSobreCum;
		}

		public void setNroMinIngr(String nroMinIngr) {
			this.nroMinIngr = nroMinIngr;
		}

		public void setPorReten(String porReten) {
			this.porReten = porReten;
		}

		public void setCodTipoPrem(String codTipoPrem) {
			this.codTipoPrem = codTipoPrem;
		}

		public void setTipoPrem(String tipoPrem) {
			this.tipoPrem = tipoPrem;
		}

		public void setCodTipoMedicion(String codTipoMedicion) {
			this.codTipoMedicion = codTipoMedicion;
		}

		public void setNroSecuenciaProducto(String nroSecuenciaProducto) {
			this.nroSecuenciaProducto = nroSecuenciaProducto;
		}
		
		public void setSeleretenivel(String[] seleretenivel) {
			this.seleretenivel = seleretenivel;
		}

		public void setSelerete22(String[] selerete22) {
			this.selerete22 = selerete22;
		}

		public void setSelerete33(String[] selerete33) {
			this.selerete33 = selerete33;
		}

		public void setSelerete44(String[] selerete44) {
			this.selerete44 = selerete44;
		}

		public void setSelelanza(String[] selelanza) {
			this.selelanza = selelanza;
		}

		public void setSelebono(String[] selebono) {
			this.selebono = selebono;
		}
		
		public String[] getSelectedItemsObjCob() {
			return selectedItemsObjCob;
		}

		public void setSelectedItemsObjCob(String[] selectedItemsObjCob) {
			this.selectedItemsObjCob = selectedItemsObjCob;
		}

		public String getSelectedItemObjCob() {
			return selectedItemObjCob;
		}

		public void setSelectedItemObjCob(String selectedItemObjCob) {
			this.selectedItemObjCob = selectedItemObjCob;
		}

		public String[] getTipoDesem() {
			return tipoDesem;
		}

		public void setTipoDesem(String[] tipoDesem) {
			this.tipoDesem = tipoDesem;
		}

		public String[] getNroVecExtIni() {
			return nroVecExtIni;
		}

		public void setNroVecExtIni(String[] nroVecExtIni) {
			this.nroVecExtIni = nroVecExtIni;
		}

		public String[] getNroVecExtFin() {
			return nroVecExtFin;
		}

		public void setNroVecExtFin(String[] nroVecExtFin) {
			this.nroVecExtFin = nroVecExtFin;
		}

		public String getNroAmedirEtapa() {
			return nroAmedirEtapa;
		}
	
		public void setNroAmedirEtapa(String nroAmedirEtapa) {
			this.nroAmedirEtapa = nroAmedirEtapa;
		}

		public String[] getListaCampanias() {
			return listaCampanias;
		}

		public void setListaCampanias(String[] listaCampanias) {
			this.listaCampanias = listaCampanias;
		}

		public String[] getListaCampaniaEvaluar() {
			return listaCampaniaEvaluar;
		}

		public void setListaCampaniaEvaluar(String[] listaCampaniaEvaluar) {
			this.listaCampaniaEvaluar = listaCampaniaEvaluar;
		}

		public String getFechaFeriado() {
			return fechaFeriado;
		}

		public void setFechaFeriado(String fechaFeriado) {
			this.fechaFeriado = fechaFeriado;
		}

		public String getCodigoAlcance() {
			return codigoAlcance;
		}

		public void setCodigoAlcance(String codigoAlcance) {
			this.codigoAlcance = codigoAlcance;
		}

		public String getCodigoRegion() {
			return codigoRegion;
		}

		public void setCodigoRegion(String codigoRegion) {
			this.codigoRegion = codigoRegion;
		}

		public String getCodigoZona() {
			return codigoZona;
		}

		public void setCodigoZona(String codigoZona) {
			this.codigoZona = codigoZona;
		}

		public String getInsPortalFFVV() {
			return insPortalFFVV;
		}

		public void setInsPortalFFVV(String insPortalFFVV) {
			this.insPortalFFVV = insPortalFFVV;
		}

		public String getInsSistCome() {
			return insSistCome;
		}

		public void setInsSistCome(String insSistCome) {
			this.insSistCome = insSistCome;
		}

		public String getInsPosiLider() {
			return insPosiLider;
		}

		public void setInsPosiLider(String insPosiLider) {
			this.insPosiLider = insPosiLider;
		}

		public String getNivNroCampMante() {
			return nivNroCampMante;
		}

		public void setNivNroCampMante(String nivNroCampMante) {
			this.nivNroCampMante = nivNroCampMante;
		}

		public String getNivNroCampTole() {
			return nivNroCampTole;
		}

		public void setNivNroCampTole(String nivNroCampTole) {
			this.nivNroCampTole = nivNroCampTole;
		}

		public String getActCobranza() {
			return actCobranza;
		}

		public void setActCobranza(String actCobranza) {
			this.actCobranza = actCobranza;
		}
	
		public String getNroMinActSec() {
			return nroMinActSec;
		}

		public void setNroMinActSec(String nroMinActSec) {
			this.nroMinActSec = nroMinActSec;
		}

		public String getPedidoWeb() {
			return pedidoWeb;
		}

		public void setPedidoWeb(String pedidoWeb) {
			this.pedidoWeb = pedidoWeb;
		}

		public String getProgramaRecono() {
			return programaRecono;
		}

		public void setProgramaRecono(String programaRecono) {
			this.programaRecono = programaRecono;
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
		 * @return the descripcionPrograma
		 */
		public String getDescripcionPrograma() {
			return descripcionPrograma;
		}
		/**
		 * @param descripcionPrograma the descripcionPrograma to set
		 */
		public void setDescripcionPrograma(String descripcionPrograma) {
			this.descripcionPrograma = descripcionPrograma;
		}
		/**
		 * @return the periodoInicio
		 */
		public String getPeriodoInicio() {
			return periodoInicio;
		}
		/**
		 * @param periodoInicio the periodoInicio to set
	     */
		public void setPeriodoInicio(String periodoInicio) {
			this.periodoInicio = periodoInicio;
		}
		/**
		 * @return the periodoFin
		 */
		public String getPeriodoFin() {
			return periodoFin;
		}
		/**
		 * @param periodoFin the periodoFin to set
	     */
		public void setPeriodoFin(String periodoFin) {
			this.periodoFin = periodoFin;
		}
		/**
		 * @return the numeroMinimoActSec
		 */
		public String getNumeroMinimoActSec() {
			return numeroMinimoActSec;
		}
		/**
		 * @param numeroMinimoActSec the numeroMinimoActSec to set
		 */
		public void setNumeroMinimoActSec(String numeroMinimoActSec) {
			this.numeroMinimoActSec = numeroMinimoActSec;
		}
		/**
		 * @return the numeroMinimoIngresos
		 */
		public String getNumeroMinimoIngresos() {
			return numeroMinimoIngresos;
		}
		/**
		 * @param numeroMinimoIngresos the numeroMinimoIngresos to set
		 */
		public void setNumeroMinimoIngresos(String numeroMinimoIngresos) {
			this.numeroMinimoIngresos = numeroMinimoIngresos;
		}
		/**
		 * @return the porcentajeActividad
		 */
		public String getPorcentajeActividad() {
			return porcentajeActividad;
		}
		/**
		 * @param porcentajeActividad the porcentajeActividad to set
		 */
		public void setPorcentajeActividad(String porcentajeActividad) {
			this.porcentajeActividad = porcentajeActividad;
		}
		/**
		 * @return the alcanceRecomendaciones
		 */
		public String getAlcanceRecomendaciones() {
			return alcanceRecomendaciones;
		}
		/**
		 * @param alcanceRecomendaciones the alcanceRecomendaciones to set
		 */
		public void setAlcanceRecomendaciones(String alcanceRecomendaciones) {
			this.alcanceRecomendaciones = alcanceRecomendaciones;
		}
		/**
		 * @return the montoDsctoToleranciaPedidos
		 */
		public String getMontoDsctoToleranciaPedidos() {
			return montoDsctoToleranciaPedidos;
		}
		/**
		 * @param montoDsctoToleranciaPedidos the montoDsctoToleranciaPedidos to set
		 */
		public void setMontoDsctoToleranciaPedidos(String montoDsctoToleranciaPedidos) {
			this.montoDsctoToleranciaPedidos = montoDsctoToleranciaPedidos;
		}
		/**
		 * @return the indExigenciaPedidoWeb
		 */
		public String getIndExigenciaPedidoWeb() {
			return indExigenciaPedidoWeb;
		}
		/**
		 * @param indExigenciaPedidoWeb the indExigenciaPedidoWeb to set
		 */
		public void setIndExigenciaPedidoWeb(String indExigenciaPedidoWeb) {
			this.indExigenciaPedidoWeb = indExigenciaPedidoWeb;
		}
		/**
		 * @return the porcentajePenalidad
		 */
		public String getPorcentajePenalidad() {
			return porcentajePenalidad;
		}
		/**
		 * @param porcentajePenalidad the porcentajePenalidad to set
		 */
		public void setPorcentajePenalidad(String porcentajePenalidad) {
			this.porcentajePenalidad = porcentajePenalidad;
		}
		/**
		 * @return the indGananciaLiderNueva
		 */
		public String getIndGananciaLiderNueva() {
			return indGananciaLiderNueva;
		}
		/**
		 * @param indGananciaLiderNueva the indGananciaLiderNueva to set
		 */
		public void setIndGananciaLiderNueva(String indGananciaLiderNueva) {
			this.indGananciaLiderNueva = indGananciaLiderNueva;
		}
		/**
		 * @return the numeroCampanasGracia
		 */
		public String getNumeroCampanasGracia() {
			return numeroCampanasGracia;
		}
		/**
		 * @param numeroCampanasGracia the numeroCampanasGracia to set
		 */
		public void setNumeroCampanasGracia(String numeroCampanasGracia) {
			this.numeroCampanasGracia = numeroCampanasGracia;
		}
		/**
		 * @return the numeroCampanasCambiarNivel
		 */
		public String getNumeroCampanasCambiarNivel() {
			return numeroCampanasCambiarNivel;
		}
		/**
		 * @param numeroCampanasCambiarNivel the numeroCampanasCambiarNivel to set
		 */
		public void setNumeroCampanasCambiarNivel(String numeroCampanasCambiarNivel) {
			this.numeroCampanasCambiarNivel = numeroCampanasCambiarNivel;
		}
		/**
		 * @return the numeroCampanasMantNivelGananciaPlus
		 */
		public String getNumeroCampanasMantNivelGananciaPlus() {
			return numeroCampanasMantNivelGananciaPlus;
		}
		/**
		 * @param numeroCampanasMantNivelGananciaPlus the numeroCampanasMantNivelGananciaPlus to set
		 */
		public void setNumeroCampanasMantNivelGananciaPlus(String numeroCampanasMantNivelGananciaPlus) {
			this.numeroCampanasMantNivelGananciaPlus = numeroCampanasMantNivelGananciaPlus;
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
		 * @return the selectedItemsNivel
		 */
		public String[] getSelectedItemsNivel() {
			return selectedItemsNivel;
		}

		/**
		 * @param selectedItemsNivel the selectedItemsNivel to set
		 */
		public void setSelectedItemsNivel(String[] selectedItemsNivel) {
			this.selectedItemsNivel = selectedItemsNivel;
		}

		/**
		 * @return the selectedItemRangoNivel
		 */
		public String getSelectedItemRangoNivel() {
			return selectedItemRangoNivel;
		}

		/**
		 * @param selectedItemRangoNivel the selectedItemRangoNivel to set
		 */
		public void setSelectedItemRangoNivel(String selectedItemRangoNivel) {
			this.selectedItemRangoNivel = selectedItemRangoNivel;
		}

		/**
		 * @return the selectedItemsRangoRetencion
		 */
		public String[] getSelectedItemsRangoRetencion() {
			return selectedItemsRangoRetencion;
		}

		/**
		 * @param selectedItemsRangoRetencion the selectedItemsRangoRetencion to set
		 */
		public void setSelectedItemsRangoRetencion(String[] selectedItemsRangoRetencion) {
			this.selectedItemsRangoRetencion = selectedItemsRangoRetencion;
		}

		/**
		 * @return the selectedItemRangoRetencion
		 */
		public String getSelectedItemRangoRetencion() {
			return selectedItemRangoRetencion;
		}

		/**
		 * @param selectedItemRangoRetencion the selectedItemRangoRetencion to set
		 */
		public void setSelectedItemRangoRetencion(String selectedItemRangoRetencion) {
			this.selectedItemRangoRetencion = selectedItemRangoRetencion;
		}

		/**
		 * @return the selectedItemsTramos
		 */
		public String[] getSelectedItemsTramos() {
			return selectedItemsTramos;
		}

		/**
		 * @param selectedItemsTramos the selectedItemsTramos to set
		 */
		public void setSelectedItemsTramos(String[] selectedItemsTramos) {
			this.selectedItemsTramos = selectedItemsTramos;
		}

		/**
		 * @return the selectedItemTramos
		 */
		public String getSelectedItemTramos() {
			return selectedItemTramos;
		}

		/**
		 * @param selectedItemTramos the selectedItemTramos to set
		 */
		public void setSelectedItemTramos(String selectedItemTramos) {
			this.selectedItemTramos = selectedItemTramos;
		}

		/**
		 * @return the selectedItemsPremios
		 */
		public String[] getSelectedItemsPremios() {
			return selectedItemsPremios;
		}

		/**
		 * @param selectedItemsPremios the selectedItemsPremios to set
		 */
		public void setSelectedItemsPremios(String[] selectedItemsPremios) {
			this.selectedItemsPremios = selectedItemsPremios;
		}

		/**
		 * @return the selectedItemPremios
		 */
		public String getSelectedItemPremios() {
			return selectedItemPremios;
		}

		/**
		 * @param selectedItemPremios the selectedItemPremios to set
		 */
		public void setSelectedItemPremios(String selectedItemPremios) {
			this.selectedItemPremios = selectedItemPremios;
		}



		/**
		 * @return the campanyaPremioBuscar
		 */
		public String getCampanyaPremioBuscar() {
			return campanyaPremioBuscar;
		}

		/**
		 * @param campanyaPremioBuscar the campanyaPremioBuscar to set
		 */
		public void setCampanyaPremioBuscar(String campanyaPremioBuscar) {
			this.campanyaPremioBuscar = campanyaPremioBuscar;
		}
		
	/*
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "MantenimientoLETProgramaCorporativoForm [codigoPais="
					+ codigoPais + ", codigoPrograma=" + codigoPrograma
					+ ", descripcionPrograma=" + descripcionPrograma
					+ ", periodoInicio=" + periodoInicio + ", periodoFin="
					+ periodoFin + ", indicadorActivoRangoNivel="
					+ ", numeroMinimoActSec="
					+ numeroMinimoActSec + ", numeroMinimoIngresos="
					+ numeroMinimoIngresos + ", porcentajeActividad="
					+ porcentajeActividad + ", alcanceRecomendaciones="
					+ alcanceRecomendaciones + ", numeroCampanasMantNivelExito="
					+ montoDsctoToleranciaPedidos + ", indExigenciaPedidoWeb="
					+ indExigenciaPedidoWeb + ", porcentajePenalidad="
					+ porcentajePenalidad + ", indGananciaLiderNueva="
					+ indGananciaLiderNueva + ", numeroCampanasGracia="
					+ numeroCampanasGracia + ", numeroCampanasCambiarNivel="
					+ numeroCampanasCambiarNivel
					+ ", numeroCampanasMantNivelGananciaPlus="
					+ numeroCampanasMantNivelGananciaPlus + ", tabSeleccion="
					+ tabSeleccion + ", campanyaPremioBuscar="
					+ campanyaPremioBuscar + ", selectedItemsNivel="
					+ Arrays.toString(selectedItemsNivel)
					+ ", selectedItemRangoNivel=" + selectedItemRangoNivel
					+ ", selectedItemsRangoRetencion="
					+ Arrays.toString(selectedItemsRangoRetencion)
					+ ", selectedItemRangoRetencion=" + selectedItemRangoRetencion
					+ ", selectedItemsTramos="
					+ Arrays.toString(selectedItemsTramos)
					+ ", selectedItemTramos=" + selectedItemTramos
					+ ", selectedItemsPremios="
					+ Arrays.toString(selectedItemsPremios)
					+ ", selectedItemPremios=" + selectedItemPremios + "]";
		}

		public String[] getListaCamp() {
			return listaCamp;
		}

		public void setListaCamp(String[] listaCamp) {
			this.listaCamp = listaCamp;
		}

		public String[] getListaCampEvaluar() {
			return listaCampEvaluar;
		}

		public void setListaCampEvaluar(String[] listaCampEvaluar) {
			this.listaCampEvaluar = listaCampEvaluar;
		}

		public String getCampLanzamiento() {
			return campLanzamiento;
		}

		public void setCampLanzamiento(String campLanzamiento) {
			this.campLanzamiento = campLanzamiento;
		}

		public String getTipoMedicion() {
			return tipoMedicion;
		}

		public void setTipoMedicion(String tipoMedicion) {
			this.tipoMedicion = tipoMedicion;
		}

		public String getNivelRete22() {
			return nivelRete22;
		}

		public void setNivelRete22(String nivelRete22) {
			this.nivelRete22 = nivelRete22;
		}

		public String getNivelRete33() {
			return nivelRete33;
		}

		public void setNivelRete33(String nivelRete33) {
			this.nivelRete33 = nivelRete33;
		}

		public String getNivelRete44() {
			return nivelRete44;
		}

		public void setNivelRete44(String nivelRete44) {
			this.nivelRete44 = nivelRete44;
		}

		public String getNivelCamAce() {
			return nivelCamAce;
		}

		public void setNivelCamAce(String nivelCamAce) {
			this.nivelCamAce = nivelCamAce;
		}

		public String getCamPerGrac() {
			return camPerGrac;
		}

		public void setCamPerGrac(String camPerGrac) {
			this.camPerGrac = camPerGrac;
		}
		
		public String getCodigoNivel() {
			return codigoNivel;
		}

		public String getNivel() {
			return nivel;
		}

		public String getNroPedidoInicial() {
			return nroPedidoInicial;
		}

		public String getNroPedidoFinal() {
			return nroPedidoFinal;
		}

		public String getGananciaCumplimiento() {
			return gananciaCumplimiento;
		}

		public String getGananciaSobrecumplimiento() {
			return gananciaSobrecumplimiento;
		}

		public String getEstado() {
			return estado;
		}

		public String getTolePedido() {
			return tolePedido;
		}

		public String getTolePorc() {
			return tolePorc;
		}

		public String getCodigoTipoComi() {
			return codigoTipoComi;
		}

		public String getPorcComiPediCons() {
			return porcComiPediCons;
		}

		public String getPorcComiPediNCon() {
			return porcComiPediNCon;
		}

		public String getPorcComiTole() {
			return porcComiTole;
		}

		public String getMontPediCons() {
			return montPediCons;
		}

		public String getMontPediNCon() {
			return montPediNCon;
		}

		public String getMontTole() {
			return montTole;
		}

		public void setCodigoNivel(String codigoNivel) {
			this.codigoNivel = codigoNivel;
		}

		public void setNivel(String nivel) {
			this.nivel = nivel;
		}

		public void setNroPedidoInicial(String nroPedidoInicial) {
			this.nroPedidoInicial = nroPedidoInicial;
		}

		public void setNroPedidoFinal(String nroPedidoFinal) {
			this.nroPedidoFinal = nroPedidoFinal;
		}

		public void setGananciaCumplimiento(String gananciaCumplimiento) {
			this.gananciaCumplimiento = gananciaCumplimiento;
		}

		public void setGananciaSobrecumplimiento(String gananciaSobrecumplimiento) {
			this.gananciaSobrecumplimiento = gananciaSobrecumplimiento;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public void setTolePedido(String tolePedido) {
			this.tolePedido = tolePedido;
		}

		public void setTolePorc(String tolePorc) {
			this.tolePorc = tolePorc;
		}

		public void setCodigoTipoComi(String codigoTipoComi) {
			this.codigoTipoComi = codigoTipoComi;
		}

		public void setPorcComiPediCons(String porcComiPediCons) {
			this.porcComiPediCons = porcComiPediCons;
		}

		public void setPorcComiPediNCon(String porcComiPediNCon) {
			this.porcComiPediNCon = porcComiPediNCon;
		}

		public void setPorcComiTole(String porcComiTole) {
			this.porcComiTole = porcComiTole;
		}

		public void setMontPediCons(String montPediCons) {
			this.montPediCons = montPediCons;
		}

		public void setMontPediNCon(String montPediNCon) {
			this.montPediNCon = montPediNCon;
		}

		public void setMontTole(String montTole) {
			this.montTole = montTole;
		}

		public String getTipoMedicionCob() {
			return tipoMedicionCob;
		}

		public void setTipoMedicionCob(String tipoMedicionCob) {
			this.tipoMedicionCob = tipoMedicionCob;
		}
		
		public String getCodigoTipoGeo() {
			return codigoTipoGeo;
		}

		public String getCodigoTipoMedi() {
			return codigoTipoMedi;
		}

		public String getNroDiasExtra() {
			return nroDiasExtra;
		}

		public String getIndDiasExtra() {
			return indDiasExtra;
		}

		public String getIndPais() {
			return indPais;
		}

		public String getCampReg() {
			return campReg;
		}

		public String getIndExclusion() {
			return indExclusion;
		}

		public String[] getRegionObjCob() {
			return regionObjCob;
		}

		public String[] getZonaObjCob() {
			return zonaObjCob;
		}

		public void setCodigoTipoGeo(String codigoTipoGeo) {
			this.codigoTipoGeo = codigoTipoGeo;
		}

		public void setCodigoTipoMedi(String codigoTipoMedi) {
			this.codigoTipoMedi = codigoTipoMedi;
		}

		public void setNroDiasExtra(String nroDiasExtra) {
			this.nroDiasExtra = nroDiasExtra;
		}

		public void setIndDiasExtra(String indDiasExtra) {
			this.indDiasExtra = indDiasExtra;
		}

		public void setIndPais(String indPais) {
			this.indPais = indPais;
		}

		public void setCampReg(String campReg) {
			this.campReg = campReg;
		}

		public void setIndExclusion(String indExclusion) {
			this.indExclusion = indExclusion;
		}

		public void setRegionObjCob(String[] regionObjCob) {
			this.regionObjCob = regionObjCob;
		}

		public void setZonaObjCob(String[] zonaObjCob) {
			this.zonaObjCob = zonaObjCob;
		}

		public String getNroDias() {
			return nroDias;
		}

		public void setNroDias(String nroDias) {
			this.nroDias = nroDias;
		}

		public String getPorcCob() {
			return porcCob;
		}

		public void setPorcCob(String porcCob) {
			this.porcCob = porcCob;
		}

		public String getProcMinCob() {
			return procMinCob;
		}

		public void setProcMinCob(String procMinCob) {
			this.procMinCob = procMinCob;
		}

		public String getNumAmbGeo() {
			return numAmbGeo;
		}

		public void setNumAmbGeo(String numAmbGeo) {
			this.numAmbGeo = numAmbGeo;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getZona() {
			return zona;
		}

		public void setZona(String zona) {
			this.zona = zona;
		}

		public String getNumTramo() {
			return numTramo;
		}

		public void setNumTramo(String numTramo) {
			this.numTramo = numTramo;
		}

		public String[] getSeleretenivelAce() {
			return seleretenivelAce;
		}

		public void setSeleretenivelAce(String[] seleretenivelAce) {
			this.seleretenivelAce = seleretenivelAce;
		}

		public String getListaCampBono() {
			return listaCampBono;
		}

		public void setListaCampBono(String listaCampBono) {
			this.listaCampBono = listaCampBono;
		}

		public String getListaCampBonoEvaluar() {
			return listaCampBonoEvaluar;
		}

		public void setListaCampBonoEvaluar(String listaCampBonoEvaluar) {
			this.listaCampBonoEvaluar = listaCampBonoEvaluar;
		}

		public String getCanasta() {
			return canasta;
		}

		public void setCanasta(String canasta) {
			this.canasta = canasta;
		}

		public String getTramo() {
			return tramo;
		}

		public void setTramo(String tramo) {
			this.tramo = tramo;
		}

		public String getDescripTipoDesem() {
			return descripTipoDesem;
		}

		public void setDescripTipoDesem(String descripTipoDesem) {
			this.descripTipoDesem = descripTipoDesem;
		}

		public String getSelecCampEvaluar() {
			return selecCampEvaluar;
		}

		public void setSelecCampEvaluar(String selecCampEvaluar) {
			this.selecCampEvaluar = selecCampEvaluar;
		}
		/**
		 * @return the nombreCanasta
		 */
		public String getNombreCanasta() {
			return nombreCanasta;
		}

		/**
		 * @param nombreCanasta the nombreCanasta to set
		 */
		public void setNombreCanasta(String nombreCanasta) {
			this.nombreCanasta = nombreCanasta;
		}
		/**
		 * @return the valorCanasta
		 */
		public String getValorCanasta() {
			return valorCanasta;
		}

		/**
		 * @param valorCanasta the valorCanasta to set
		 */
		public void setValorCanasta(String valorCanasta) {
			this.valorCanasta = valorCanasta;
		}

		/**
		 * @return the numeroProductosCanasta
		 */
		public String getNumeroProductosCanasta() {
			return numeroProductosCanasta;
		}
		/**
		 * @param numeroProductosCanasta the numeroProductosCanasta to set
		 */
		public void setNumeroProductosCanasta(String numeroProductosCanasta) {
			this.numeroProductosCanasta = numeroProductosCanasta;
		}
		
		/**
		 * @return the codigoTipoOfertaProductoCanasta
		 */
		public String getCodigoTipoOfertaProductoCanasta() {
			return codigoTipoOfertaProductoCanasta;
		}

		/**
		 * @param codigoTipoOfertaProductoCanasta the codigoTipoOfertaProductoCanasta to set
		 */
		public void setCodigoTipoOfertaProductoCanasta(
				String codigoTipoOfertaProductoCanasta) {
			this.codigoTipoOfertaProductoCanasta = codigoTipoOfertaProductoCanasta;
		}

		/**
		 * @return the codigoSAPProductoCanasta
		 */
		public String getCodigoSAPProductoCanasta() {
			return codigoSAPProductoCanasta;
		}

		/**
		 * @param codigoSAPProductoCanasta the codigoSAPProductoCanasta to set
		 */
		public void setCodigoSAPProductoCanasta(String codigoSAPProductoCanasta) {
			this.codigoSAPProductoCanasta = codigoSAPProductoCanasta;
		}

		/**
		 * @return the precioProductoCanasta
		 */
		public String getPrecioProductoCanasta() {
			return precioProductoCanasta;
		}

		/**
		 * @param precioProductoCanasta the precioProductoCanasta to set
		 */
		public void setPrecioProductoCanasta(String precioProductoCanasta) {
			this.precioProductoCanasta = precioProductoCanasta;
		}

		/**
		 * @return the codigoFormaPagoProductoCanasta
		 */
		public String getCodigoFormaPagoProductoCanasta() {
			return codigoFormaPagoProductoCanasta;
		}

		/**
		 * @param codigoFormaPagoProductoCanasta the codigoFormaPagoProductoCanasta to set
		 */
		public void setCodigoFormaPagoProductoCanasta(
				String codigoFormaPagoProductoCanasta) {
			this.codigoFormaPagoProductoCanasta = codigoFormaPagoProductoCanasta;
		}

		/**
		 * @return the descripcionProductoCanasta
		 */
		public String getDescripcionProductoCanasta() {
			return descripcionProductoCanasta;
		}

		/**
		 * @param descripcionProductoCanasta the descripcionProductoCanasta to set
		 */
		public void setDescripcionProductoCanasta(String descripcionProductoCanasta) {
			this.descripcionProductoCanasta = descripcionProductoCanasta;
		}

		/**
		 * @return the selectedItemsCanasta
		 */
		public String[] getSelectedItemsCanasta() {
			return selectedItemsCanasta;
		}

		/**
		 * @param selectedItemsCanasta the selectedItemsCanasta to set
		 */
		public void setSelectedItemsCanasta(String[] selectedItemsCanasta) {
			this.selectedItemsCanasta = selectedItemsCanasta;
		}

		/**
		 * @return the selectedItemsProductoCanasta
		 */
		public String[] getSelectedItemsProductoCanasta() {
			return selectedItemsProductoCanasta;
		}

		/**
		 * @param selectedItemsProductoCanasta the selectedItemsProductoCanasta to set
		 */
		public void setSelectedItemsProductoCanasta(
				String[] selectedItemsProductoCanasta) {
			this.selectedItemsProductoCanasta = selectedItemsProductoCanasta;
		}

		/**
		 * @return the campanyaRegistro
		 */
		public String getCampanyaRegistro() {
			return campanyaRegistro;
		}

		/**
		 * @param campanyaRegistro the campanyaRegistro to set
		 */
		public void setCampanyaRegistro(String campanyaRegistro) {
			this.campanyaRegistro = campanyaRegistro;
		}

		public String getIndicadorActivoIncAmbGeo() {
			return indicadorActivoIncAmbGeo;
		}

		public void setIndicadorActivoIncAmbGeo(String indicadorActivoIncAmbGeo) {
			this.indicadorActivoIncAmbGeo = indicadorActivoIncAmbGeo;
		}

		public String[] getSeleAmbGeoPedLid() {
			return seleAmbGeoPedLid;
		}

		public void setSeleAmbGeoPedLid(String[] seleAmbGeoPedLid) {
			this.seleAmbGeoPedLid = seleAmbGeoPedLid;
		}

		public String getIndicadorActivoAmbGeoPedidoLider() {
			return indicadorActivoAmbGeoPedidoLider;
		}

		public void setIndicadorActivoAmbGeoPedidoLider(
				String indicadorActivoAmbGeoPedidoLider) {
			this.indicadorActivoAmbGeoPedidoLider = indicadorActivoAmbGeoPedidoLider;
		}

		public String getMostrarIngresoCanasta() {
			return mostrarIngresoCanasta;
		}
		
		public void setMostrarIngresoCanasta(String mostrarIngresoCanasta) {
			this.mostrarIngresoCanasta = mostrarIngresoCanasta;
		}
		
		public String[] getSeleIncMontoRecu() {
			return seleIncMontoRecu;
		}

		public void setSeleIncMontoRecu(String[] seleIncMontoRecu) {
			this.seleIncMontoRecu = seleIncMontoRecu;
		}

		public String getIncCodNivel() {
			return incCodNivel;
		}

		public void setIncCodNivel(String incCodNivel) {
			this.incCodNivel = incCodNivel;
		}

		public String getMostrarIncentivo() {
			return mostrarIncentivo;
		}
		
		public void setMostrarIncentivo(String mostrarIncentivo) {
			this.mostrarIncentivo = mostrarIncentivo;
		}
		/**
		 * @return the indicadorModificarNivelTramo
		 */
		public String getIndicadorModificarNivelTramo() {
			return indicadorModificarNivelTramo;
		}
		/**
		 * @param indicadorModificarNivelTramo the indicadorModificarNivelTramo to set
		 */
		public void setIndicadorModificarNivelTramo(String indicadorModificarNivelTramo) {
			this.indicadorModificarNivelTramo = indicadorModificarNivelTramo;
		}
		/**
		 * @return the idModificarNivelTramo
		 */
		public int getIdModificarNivelTramo() {
			return idModificarNivelTramo;
		}
		/**
		 * @param idModificarNivelTramo the idModificarNivelTramo to set
		 */
		public void setIdModificarNivelTramo(int idModificarNivelTramo) {
			this.idModificarNivelTramo = idModificarNivelTramo;
		}
		/**
		 * @return the codigoTramo
		 */
		public String getCodigoTramo() {
			return codigoTramo;
		}

		/**
		 * @param codigoTramo the codigoTramo to set
		 */
		public void setCodigoTramo(String codigoTramo) {
			this.codigoTramo = codigoTramo;
		}

		/**
		 * @return the listaGrillaCobranzaCampo01
		 */
		public String[] getListaGrillaCobranzaCampo01() {
			return listaGrillaCobranzaCampo01;
		}

		public void setListaGrillaCobranzaCampo01(String[] listaGrillaCobranzaCampo01) {
			this.listaGrillaCobranzaCampo01 = listaGrillaCobranzaCampo01;
		}

		public String[] getListaGrillaCobranzaCampo02() {
			return listaGrillaCobranzaCampo02;
		}

		public void setListaGrillaCobranzaCampo02(String[] listaGrillaCobranzaCampo02) {
			this.listaGrillaCobranzaCampo02 = listaGrillaCobranzaCampo02;
		}

		public String[] getListaGrillaCobranzaCampo03() {
			return listaGrillaCobranzaCampo03;
		}

		public void setListaGrillaCobranzaCampo03(String[] listaGrillaCobranzaCampo03) {
			this.listaGrillaCobranzaCampo03 = listaGrillaCobranzaCampo03;
		}

		public String[] getListaGrillaCobranzaCampo04() {
			return listaGrillaCobranzaCampo04;
		}

		public void setListaGrillaCobranzaCampo04(String[] listaGrillaCobranzaCampo04) {
			this.listaGrillaCobranzaCampo04 = listaGrillaCobranzaCampo04;
		}

		public String getCodNivelRete() {
			return codNivelRete;
		}

		public void setCodNivelRete(String codNivelRete) {
			this.codNivelRete = codNivelRete;
		}

		public String getNroMinIng() {
			return nroMinIng;
		}

		public void setNroMinIng(String nroMinIng) {
			this.nroMinIng = nroMinIng;
		}

		public String getPorRetExig() {
			return porRetExig;
		}

		/**
		 * @param porRetExig the porRetExig to set
		 */
		public void setPorRetExig(String porRetExig) {
			this.porRetExig = porRetExig;
		}

		/**
		 * @return the nivelRete
		 */
		public String getNivelRete() {
			return nivelRete;
		}

		/**
		 * @param nivelRete the nivelRete to set
		 */
		public void setNivelRete(String nivelRete) {
			this.nivelRete = nivelRete;
		}

		public String[] getSelerete() {
			return selerete;
		}

		public void setSelerete(String[] selerete) {
			this.selerete = selerete;
		}

		public String[] getListaGrillaBonoRetencion01() {
			return listaGrillaBonoRetencion01;
		}

		public void setListaGrillaBonoRetencion01(String[] listaGrillaBonoRetencion01) {
			this.listaGrillaBonoRetencion01 = listaGrillaBonoRetencion01;
		}

		public String[] getListaGrillaBonoRetencion02() {
			return listaGrillaBonoRetencion02;
		}

		public void setListaGrillaBonoRetencion02(String[] listaGrillaBonoRetencion02) {
			this.listaGrillaBonoRetencion02 = listaGrillaBonoRetencion02;
		}

		public String[] getListaGrillaNivelCampo01() {
			return listaGrillaNivelCampo01;
		}

		public void setListaGrillaNivelCampo01(String[] listaGrillaNivelCampo01) {
			this.listaGrillaNivelCampo01 = listaGrillaNivelCampo01;
		}

		public String[] getListaGrillaNivelCampo02() {
			return listaGrillaNivelCampo02;
		}

		/**
		 * @param listaGrillaNivelCampo02 the listaGrillaNivelCampo02 to set
		 */
		public void setListaGrillaNivelCampo02(String[] listaGrillaNivelCampo02) {
			this.listaGrillaNivelCampo02 = listaGrillaNivelCampo02;
		}

		/**
		 * @return the listaGrillaNivelCampo03
		 */
		public String[] getListaGrillaNivelCampo03() {
			return listaGrillaNivelCampo03;
		}

		/**
		 * @param listaGrillaNivelCampo03 the listaGrillaNivelCampo03 to set
		 */
		public void setListaGrillaNivelCampo03(String[] listaGrillaNivelCampo03) {
			this.listaGrillaNivelCampo03 = listaGrillaNivelCampo03;
		}

		/**
		 * @return the listaGrillaNivelAceleradoCampo01
		 */
		public String[] getListaGrillaNivelAceleradoCampo01() {
			return listaGrillaNivelAceleradoCampo01;
		}
		
		/**
		 * @param listaGrillaNivelAceleradoCampo01 the listaGrillaNivelAceleradoCampo01 to set
		 */
		public void setListaGrillaNivelAceleradoCampo01(
				String[] listaGrillaNivelAceleradoCampo01) {
			this.listaGrillaNivelAceleradoCampo01 = listaGrillaNivelAceleradoCampo01;
		}

		public String[] getListaGrillaNivelAceleradoCampo02() {
			return listaGrillaNivelAceleradoCampo02;
		}

		public void setListaGrillaNivelAceleradoCampo02(
				String[] listaGrillaNivelAceleradoCampo02) {
			this.listaGrillaNivelAceleradoCampo02 = listaGrillaNivelAceleradoCampo02;
		}

		public String[] getListaGrillaNBonoLanzamientoCampo01() {
			return listaGrillaNBonoLanzamientoCampo01;
		}

		public void setListaGrillaNBonoLanzamientoCampo01(
				String[] listaGrillaNBonoLanzamientoCampo01) {
			this.listaGrillaNBonoLanzamientoCampo01 = listaGrillaNBonoLanzamientoCampo01;
		}

		public String[] getListaGrillaNBonoLanzamientoCampo02() {
			return listaGrillaNBonoLanzamientoCampo02;
		}

		public void setListaGrillaNBonoLanzamientoCampo02(
				String[] listaGrillaNBonoLanzamientoCampo02) {
			this.listaGrillaNBonoLanzamientoCampo02 = listaGrillaNBonoLanzamientoCampo02;
		}

		public String getMonPrem() {
			return monPrem;
		}

		public void setMonPrem(String monPrem) {
			this.monPrem = monPrem;
		}

		public String getCodNivelLanzamiento() {
			return codNivelLanzamiento;
		}

		public void setCodNivelLanzamiento(String codNivelLanzamiento) {
			this.codNivelLanzamiento = codNivelLanzamiento;
		}
		
		public String getNivelLanzamiento() {
			return nivelLanzamiento;
		}

		public void setNivelLanzamiento(String nivelLanzamiento) {
			this.nivelLanzamiento = nivelLanzamiento;
		}

		public String getExcluirNivelTmp() {
			return excluirNivelTmp;
		}

		public void setExcluirNivelTmp(String excluirNivelTmp) {
			this.excluirNivelTmp = excluirNivelTmp;
		}

		public String getExcluirNivel() {
			return excluirNivel;
		}

		public void setExcluirNivel(String excluirNivel) {
			this.excluirNivel = excluirNivel;
		}

		public String[] getListaGrillaIncentivo1Campo01() {
			return listaGrillaIncentivo1Campo01;
		}

		public void setListaGrillaIncentivo1Campo01(
				String[] listaGrillaIncentivo1Campo01) {
			this.listaGrillaIncentivo1Campo01 = listaGrillaIncentivo1Campo01;
		}

		public String[] getListaGrillaIncentivo1Campo02() {
			return listaGrillaIncentivo1Campo02;
		}

		public void setListaGrillaIncentivo1Campo02(
				String[] listaGrillaIncentivo1Campo02) {
			this.listaGrillaIncentivo1Campo02 = listaGrillaIncentivo1Campo02;
		}

		public String[] getListaGrillaIncentivo1Campo03() {
			return listaGrillaIncentivo1Campo03;
		}

		public void setListaGrillaIncentivo1Campo03(
				String[] listaGrillaIncentivo1Campo03) {
			this.listaGrillaIncentivo1Campo03 = listaGrillaIncentivo1Campo03;
		}

		public String[] getListaGrillaIncentivo3Campo01() {
			return listaGrillaIncentivo3Campo01;
		}

		public void setListaGrillaIncentivo3Campo01(
				String[] listaGrillaIncentivo3Campo01) {
			this.listaGrillaIncentivo3Campo01 = listaGrillaIncentivo3Campo01;
		}

		public String[] getListaGrillaIncentivo3Campo02() {
			return listaGrillaIncentivo3Campo02;
		}

		public void setListaGrillaIncentivo3Campo02(
				String[] listaGrillaIncentivo3Campo02) {
			this.listaGrillaIncentivo3Campo02 = listaGrillaIncentivo3Campo02;
		}

		public String[] getListaGrillaIncentivo3Campo03() {
			return listaGrillaIncentivo3Campo03;
		}

		public void setListaGrillaIncentivo3Campo03(
				String[] listaGrillaIncentivo3Campo03) {
			this.listaGrillaIncentivo3Campo03 = listaGrillaIncentivo3Campo03;
		}

		public String[] getListaGrillaCanasta1Campo01() {
			return listaGrillaCanasta1Campo01;
		}

		public void setListaGrillaCanasta1Campo01(String[] listaGrillaCanasta1Campo01) {
			this.listaGrillaCanasta1Campo01 = listaGrillaCanasta1Campo01;
		}

		public String[] getListaGrillaCanasta2Campo01() {
			return listaGrillaCanasta2Campo01;
		}

		public void setListaGrillaCanasta2Campo01(String[] listaGrillaCanasta2Campo01) {
			this.listaGrillaCanasta2Campo01 = listaGrillaCanasta2Campo01;
		}

		public String getMontoPremioCicloVida() {
			return montoPremioCicloVida;
		}

		public void setMontoPremioCicloVida(String montoPremioCicloVida) {
			this.montoPremioCicloVida = montoPremioCicloVida;
		}

		public String getMontoPremioNivelAcelerado() {
			return montoPremioNivelAcelerado;
		}

		public void setMontoPremioNivelAcelerado(String montoPremioNivelAcelerado) {
			this.montoPremioNivelAcelerado = montoPremioNivelAcelerado;
		}

		public String getCampannaInicialNivelAcelerado() {
			return campannaInicialNivelAcelerado;
		}

		public void setCampannaInicialNivelAcelerado(
				String campannaInicialNivelAcelerado) {
			this.campannaInicialNivelAcelerado = campannaInicialNivelAcelerado;
		}

		public String getCampannaFinalNivelAcelerado() {
			return campannaFinalNivelAcelerado;
		}

		public void setCampannaFinalNivelAcelerado(String campannaFinalNivelAcelerado) {
			this.campannaFinalNivelAcelerado = campannaFinalNivelAcelerado;
		}

		public String[] getListaGrillaBonoRetencion03() {
			return listaGrillaBonoRetencion03;
		}

		public void setListaGrillaBonoRetencion03(String[] listaGrillaBonoRetencion03) {
			this.listaGrillaBonoRetencion03 = listaGrillaBonoRetencion03;
		}

		public String[] getListaGrillaNivelAceleradoCampo03() {
			return listaGrillaNivelAceleradoCampo03;
		}

		public void setListaGrillaNivelAceleradoCampo03(
				String[] listaGrillaNivelAceleradoCampo03) {
			this.listaGrillaNivelAceleradoCampo03 = listaGrillaNivelAceleradoCampo03;
		}

		public String getCampannaInicialNivel() {
			return campannaInicialNivel;
		}

		public void setCampannaInicialNivel(String campannaInicialNivel) {
			this.campannaInicialNivel = campannaInicialNivel;
		}

		public String getCampannaFinalNivel() {
			return campannaFinalNivel;
		}

		public void setCampannaFinalNivel(String campannaFinalNivel) {
			this.campannaFinalNivel = campannaFinalNivel;
		}

		public String[] getListaGrillaNivelCampo04() {
			return listaGrillaNivelCampo04;
		}

		public void setListaGrillaNivelCampo04(String[] listaGrillaNivelCampo04) {
			this.listaGrillaNivelCampo04 = listaGrillaNivelCampo04;
		}

		public String[] getListaGrillaNivelCampo05() {
			return listaGrillaNivelCampo05;
		}

		public void setListaGrillaNivelCampo05(String[] listaGrillaNivelCampo05) {
			this.listaGrillaNivelCampo05 = listaGrillaNivelCampo05;
		}

		public String getMontoVentaMinimo() {
			return montoVentaMinimo;
		}

		public void setMontoVentaMinimo(String montoVentaMinimo) {
			this.montoVentaMinimo = montoVentaMinimo;
		}

		public String getMontoVentaMaximo() {
			return montoVentaMaximo;
		}

		public void setMontoVentaMaximo(String montoVentaMaximo) {
			this.montoVentaMaximo = montoVentaMaximo;
		}

		public String getMontoPeriodoGracia() {
			return montoPeriodoGracia;
		}

		public void setMontoPeriodoGracia(String montoPeriodoGracia) {
			this.montoPeriodoGracia = montoPeriodoGracia;
		}

		public String getCodigoNivelPeriodoGracia() {
			return codigoNivelPeriodoGracia;
		}

		public void setCodigoNivelPeriodoGracia(String codigoNivelPeriodoGracia) {
			this.codigoNivelPeriodoGracia = codigoNivelPeriodoGracia;
		}

		public String[] getSelectedPeriodoGracia() {
			return selectedPeriodoGracia;
		}

		public void setSelectedPeriodoGracia(String[] selectedPeriodoGracia) {
			this.selectedPeriodoGracia = selectedPeriodoGracia;
		}

		public String[] getListaGrillaPeriodoGraciaCampo01() {
			return listaGrillaPeriodoGraciaCampo01;
		}

		public void setListaGrillaPeriodoGraciaCampo01(
				String[] listaGrillaPeriodoGraciaCampo01) {
			this.listaGrillaPeriodoGraciaCampo01 = listaGrillaPeriodoGraciaCampo01;
		}

		public String getNivelPeriodoGracia() {
			return nivelPeriodoGracia;
		}

		public void setNivelPeriodoGracia(String nivelPeriodoGracia) {
			this.nivelPeriodoGracia = nivelPeriodoGracia;
		}

		public Integer getMenorPesoNivel() {
			return menorPesoNivel;
		}

		public void setMenorPesoNivel(Integer menorPesoNivel) {
			this.menorPesoNivel = menorPesoNivel;
		}

		public String getCodigoTipoRanking() {
			return codigoTipoRanking;
		}

		public void setCodigoTipoRanking(String codigoTipoRanking) {
			this.codigoTipoRanking = codigoTipoRanking;
		}

		public String getCampanyaInicio() {
			return campanyaInicio;
		}

		public void setCampanyaInicio(String campanyaInicio) {
			this.campanyaInicio = campanyaInicio;
		}

		public String getCampanyaFin() {
			return campanyaFin;
		}

		public void setCampanyaFin(String campanyaFin) {
			this.campanyaFin = campanyaFin;
		}

		public String getNumeroCampanyasCumplimentoPedido() {
			return numeroCampanyasCumplimentoPedido;
		}

		public void setNumeroCampanyasCumplimentoPedido(
				String numeroCampanyasCumplimentoPedido) {
			this.numeroCampanyasCumplimentoPedido = numeroCampanyasCumplimentoPedido;
		}

		public String getCodigoTipoMedicion() {
			return codigoTipoMedicion;
		}

		public void setCodigoTipoMedicion(String codigoTipoMedicion) {
			this.codigoTipoMedicion = codigoTipoMedicion;
		}

		public String[] getSelectedItemsRanking() {
			return selectedItemsRanking;
		}

		public void setSelectedItemsRanking(String[] selectedItemsRanking) {
			this.selectedItemsRanking = selectedItemsRanking;
		}

		public String[] getListaGrillaRankingCampanyaInicio() {
			return listaGrillaRankingCampanyaInicio;
		}

		public void setListaGrillaRankingCampanyaInicio(
				String[] listaGrillaRankingCampanyaInicio) {
			this.listaGrillaRankingCampanyaInicio = listaGrillaRankingCampanyaInicio;
		}

		public String[] getListaGrillaRankingCampanyaFin() {
			return listaGrillaRankingCampanyaFin;
		}

		public void setListaGrillaRankingCampanyaFin(
				String[] listaGrillaRankingCampanyaFin) {
			this.listaGrillaRankingCampanyaFin = listaGrillaRankingCampanyaFin;
		}

		public String[] getListaGrillaRankingNumeroCampanyasCumplimentoPedido() {
			return listaGrillaRankingNumeroCampanyasCumplimentoPedido;
		}

		public void setListaGrillaRankingNumeroCampanyasCumplimentoPedido(
				String[] listaGrillaRankingNumeroCampanyasCumplimentoPedido) {
			this.listaGrillaRankingNumeroCampanyasCumplimentoPedido = listaGrillaRankingNumeroCampanyasCumplimentoPedido;
		}

		public String[] getListaGrillaRankingCodigoTipoMedicion() {
			return listaGrillaRankingCodigoTipoMedicion;
		}

		public void setListaGrillaRankingCodigoTipoMedicion(
				String[] listaGrillaRankingCodigoTipoMedicion) {
			this.listaGrillaRankingCodigoTipoMedicion = listaGrillaRankingCodigoTipoMedicion;
		}

		public String[] getSelectedItemsRankingNivel() {
			return selectedItemsRankingNivel;
		}

		public void setSelectedItemsRankingNivel(String[] selectedItemsRankingNivel) {
			this.selectedItemsRankingNivel = selectedItemsRankingNivel;
		}

		public String[] getListaGrillaDesempenioCampo01() {
			return listaGrillaDesempenioCampo01;
		}

		public void setListaGrillaDesempenioCampo01(
				String[] listaGrillaDesempenioCampo01) {
			this.listaGrillaDesempenioCampo01 = listaGrillaDesempenioCampo01;
		}

		public String[] getListaGrillaDesempenioCampo02() {
			return listaGrillaDesempenioCampo02;
		}

		public void setListaGrillaDesempenioCampo02(
				String[] listaGrillaDesempenioCampo02) {
			this.listaGrillaDesempenioCampo02 = listaGrillaDesempenioCampo02;
		}

		public boolean isIndicadorEdicionCabecera() {
			return indicadorEdicionCabecera;
		}

		public void setIndicadorEdicionCabecera(boolean indicadorEdicionCabecera) {
			this.indicadorEdicionCabecera = indicadorEdicionCabecera;
		}

		public boolean isIndicadorEdicionNiveles() {
			return indicadorEdicionNiveles;
		}

		public void setIndicadorEdicionNiveles(boolean indicadorEdicionNiveles) {
			this.indicadorEdicionNiveles = indicadorEdicionNiveles;
		}

		public boolean isIndicadorEdicionCobranza() {
			return indicadorEdicionCobranza;
		}

		public void setIndicadorEdicionCobranza(boolean indicadorEdicionCobranza) {
			this.indicadorEdicionCobranza = indicadorEdicionCobranza;
		}

		public boolean isIndicadorEdicionBono() {
			return indicadorEdicionBono;
		}

		public void setIndicadorEdicionBono(boolean indicadorEdicionBono) {
			this.indicadorEdicionBono = indicadorEdicionBono;
		}

		public boolean isIndicadorEdicionIncentivos() {
			return indicadorEdicionIncentivos;
		}

		public void setIndicadorEdicionIncentivos(boolean indicadorEdicionIncentivos) {
			this.indicadorEdicionIncentivos = indicadorEdicionIncentivos;
		}

		public boolean isIndicadorEdicionCanasta() {
			return indicadorEdicionCanasta;
		}

		public void setIndicadorEdicionCanasta(boolean indicadorEdicionCanasta) {
			this.indicadorEdicionCanasta = indicadorEdicionCanasta;
		}

		public boolean isIndicadorEdicionGestion() {
			return indicadorEdicionGestion;
		}

		public void setIndicadorEdicionGestion(boolean indicadorEdicionGestion) {
			this.indicadorEdicionGestion = indicadorEdicionGestion;
		}

		public boolean isIndicadorEdicionRanking() {
			return indicadorEdicionRanking;
		}

		public void setIndicadorEdicionRanking(boolean indicadorEdicionRanking) {
			this.indicadorEdicionRanking = indicadorEdicionRanking;
		}

		public boolean isIndicadorMostrarGrabar() {
			return indicadorMostrarGrabar;
		}

		public void setIndicadorMostrarGrabar(boolean indicadorMostrarGrabar) {
			this.indicadorMostrarGrabar = indicadorMostrarGrabar;
		}

		public String getIndicadorFeriado() {
			return indicadorFeriado;
		}

		public void setIndicadorFeriado(String indicadorFeriado) {
			this.indicadorFeriado = indicadorFeriado;
		}

		public String getCampanyaActivacionCanasta() {
			return campanyaActivacionCanasta;
		}

		public void setCampanyaActivacionCanasta(String campanyaActivacionCanasta) {
			this.campanyaActivacionCanasta = campanyaActivacionCanasta;
		}

		public String getCampanyaActivacionCanastaIncentivos() {
			return campanyaActivacionCanastaIncentivos;
		}

		public void setCampanyaActivacionCanastaIncentivos(
				String campanyaActivacionCanastaIncentivos) {
			this.campanyaActivacionCanastaIncentivos = campanyaActivacionCanastaIncentivos;
		}

		public String getNroMaxIngr() {
			return nroMaxIngr;
		}

		public void setNroMaxIngr(String nroMaxIngr) {
			this.nroMaxIngr = nroMaxIngr;
		}

		public String getNroMaxIng() {
			return nroMaxIng;
		}

		public void setNroMaxIng(String nroMaxIng) {
			this.nroMaxIng = nroMaxIng;
		}

		public String getNroMinRete() {
			return nroMinRete;
		}

		public void setNroMinRete(String nroMinRete) {
			this.nroMinRete = nroMinRete;
		}

		public String getNroMaxRete() {
			return nroMaxRete;
		}

		public void setNroMaxRete(String nroMaxRete) {
			this.nroMaxRete = nroMaxRete;
		}

		public String[] getListaGrillaBonoRetencion04() {
			return listaGrillaBonoRetencion04;
		}

		public void setListaGrillaBonoRetencion04(String[] listaGrillaBonoRetencion04) {
			this.listaGrillaBonoRetencion04 = listaGrillaBonoRetencion04;
		}

		public String getMontoVentaTolerancia() {
			return montoVentaTolerancia;
		}

		public void setMontoVentaTolerancia(String montoVentaTolerancia) {
			this.montoVentaTolerancia = montoVentaTolerancia;
		}

		public String getIndicadorTipoMeta() {
			return indicadorTipoMeta;
		}

		public void setIndicadorTipoMeta(String indicadorTipoMeta) {
			this.indicadorTipoMeta = indicadorTipoMeta;
		}

		public String getCodigoTipoPremiacion() {
			return codigoTipoPremiacion;
		}
		
		public void setCodigoTipoPremiacion(String codigoTipoPremiacion) {
			this.codigoTipoPremiacion = codigoTipoPremiacion;
		}

		public String getCodigoTipoCanasta() {
			return codigoTipoCanasta;
		}

		public void setCodigoTipoCanasta(String codigoTipoCanasta) {
			this.codigoTipoCanasta = codigoTipoCanasta;
		}

		public String getIndicadorConsiderarNuevas() {
			return indicadorConsiderarNuevas;
		}

		public void setIndicadorConsiderarNuevas(String indicadorConsiderarNuevas) {
			this.indicadorConsiderarNuevas = indicadorConsiderarNuevas;
		}
		
		public String getIngresoMeta() {
			return ingresoMeta;
		}

		public void setIngresoMeta(String ingresoMeta) {
			this.ingresoMeta = ingresoMeta;
		}
		
		public String getIndicadorConsiderarMetasIngresos() {
			return indicadorConsiderarMetasIngresos;
		}

		public void setIndicadorConsiderarMetasIngresos(
				String indicadorConsiderarMetasIngresos) {
			this.indicadorConsiderarMetasIngresos = indicadorConsiderarMetasIngresos;
		}

		public String getMetaCapitalizacion() {
			return metaCapitalizacion;
		}

		public void setMetaCapitalizacion(String metaCapitalizacion) {
			this.metaCapitalizacion = metaCapitalizacion;
		}

		public String getIndicadorconsiderarCapitalizacion() {
			return indicadorconsiderarCapitalizacion;
		}

		public void setIndicadorconsiderarCapitalizacion(
				String indicadorconsiderarCapitalizacion) {
			this.indicadorconsiderarCapitalizacion = indicadorconsiderarCapitalizacion;
		}

		/**
		 * @return the condCapitalizacion
		 */
		public String getCondCapitalizacion() {
			return condCapitalizacion;
		}

		/**
		 * @param condCapitalizacion the condCapitalizacion to set
		 */
		public void setCondCapitalizacion(String condCapitalizacion) {
			this.condCapitalizacion = condCapitalizacion;
		}

		/**
		 * @return the indicadorExitoNuevas
		 */
		public String getIndicadorExitoNuevas() {
			return indicadorExitoNuevas;
		}

		/**
		 * @param indicadorExitoNuevas the indicadorExitoNuevas to set
		 */
		public void setIndicadorExitoNuevas(String indicadorExitoNuevas) {
			this.indicadorExitoNuevas = indicadorExitoNuevas;
		}		

		/**
		 * @return the codigoRangoComision
		 */
		public String getCodigoRangoComision() {
			return codigoRangoComision;
		}

		/**
		 * @param codigoRangoComision the codigoRangoComision to set
		 */
		public void setCodigoRangoComision(String codigoRangoComision) {
			this.codigoRangoComision = codigoRangoComision;
		}

		/**
		 * @return the descriRangoComision
		 */
		public String getDescriRangoComision() {
			return descriRangoComision;
		}

		/**
		 * @param descriRangoComision the descriRangoComision to set
		 */
		public void setDescriRangoComision(String descriRangoComision) {
			this.descriRangoComision = descriRangoComision;
		}
		
		/**
		 * @return the codigoNivelRango
		 */
		public String getCodigoNivelRango() {
			return codigoNivelRango;
		}
		
		/**
		 * @param codigoNivelRango the codigoNivelRango to set
		 */
		public void setCodigoNivelRango(String codigoNivelRango) {
			this.codigoNivelRango = codigoNivelRango;
		}	
		
		
	}