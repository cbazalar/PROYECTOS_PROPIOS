package biz.belcorp.ssicc.web.scsicc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAENuevasForm;


/**
 * The Class ReporteSACFacturacionDetalleAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 28/08/2014
 */
@ManagedBean
@SessionScoped
public class ReporteMAENuevasAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList = {};
	
	private LabelValue[] siccZonaList = {};
	
	private LabelValue[] maeEstadoClienteList = {};
	
	private boolean mostrarFechaInicio = true;
	private boolean mostrarFechaFin = true;
	private boolean mostrarSinSaldo = true;
	private boolean mostrarCodigoPeriodo = true;
	private boolean mostrarRegionAbierta = true;
	private boolean	mostrarSinPedido = true;
	private boolean mostrarEstado = true;
	private boolean mostrarCondicionActiva=false;
	private boolean indicadorSinEstado=false;
	private boolean mostrarTipoArchivo=false;
	private boolean mostrarEstadoActiva=false;
	private String attachment = "";
	private String [] codClienteDocumento={};
	private boolean mostrarClientesBloqueados = false;
	private List tipoDocumentoList;
	private boolean indicadorCamposAdicionales = false;
	private boolean indLlamadaBienvenida;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAENuevasForm form = new ReporteMAENuevasForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("devuelveNombreReporte");
		}
		ReporteMAENuevasForm form = (ReporteMAENuevasForm)this.formReporte;
		
		String fe = form.getFormatoExportacion() + form.getTipoConsulta();
		
		if ("XLS0".equals(fe))
			return "reporteMAENuevasXLS";
		else if ("XLS1".equals(fe))
			return "reporteMAENuevasRechazadasXLS";
		else if ("XLS2".equals(fe))
			return "reporteMAENuevasPrimerPedidoXLS";
		else if ("XLS3".equals(fe))
			return "reporteMAENuevasSegundoPedidoXLS";
		else if ("XLS4".equals(fe))
			return "reporteMAENuevasTercerPedidoXLS";
		else if ("XLS5".equals(fe))
			return "reporteMAENuevasConsultorasActivasXLS";
		else if ("XLS6".equals(fe))
			return "reporteMAENuevasConsultorasInactivasXLS";
		else if (StringUtils.equals(fe, "XLS7"))
			return "reporteMAENuevasConsultorasLLamadaBievenidaXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteMAENuevasForm form = (ReporteMAENuevasForm)this.formReporte;
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOCSV = true;
		
		// Carga de los Periodos				
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
	    
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteriaPeriodo);
		form.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		List listado = reporteService.getTipoReporteList();
		this.maeEstadoClienteList = new LabelValue[listado.size()];
		int i = 0;
		for (Object object : listado) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getMaeEstadoClienteList()[i] = labelValue;
			i++;
		}
		this.valoresByDefault(form.getCodigoPeriodo());
		this.loadRegiones();
		this.camposInactivos("0");
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		form.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));		
		
		this.mostrarClientesBloqueados = true;
		
		Map criteria = new HashMap();
		pais.getOidPais();
		criteria.put("oidPais", pais.getOidPais());		
		this.tipoDocumentoList = clienteService.getTiposDocumentoIdentidad(criteria);
		form.setCodigoTipoDocu("");
		form.setCodigoPais(pais.getCodigo());
		
		ParametroPais paramPais01 = new ParametroPais();
		paramPais01.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		paramPais01.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPais01.setNombreParametro(Constants.MAE_PARAM_INDICADOR_CAMPOS_ADICIONALES);
		paramPais01.setValorParametro(Constants.NUMERO_UNO);		
		
		List listParametros01 = genericoService.getParametrosPais(paramPais01);
		this.indicadorCamposAdicionales = false;
		if (listParametros01 != null && listParametros01.size() > 0) {
			this.indicadorCamposAdicionales =true ;
		}
		
		//Opcion Llamada Bienvenida
		ParametroPais paramPais02 = new ParametroPais();
		paramPais02.setCodigoPais(form.getCodigoPais());
		paramPais02.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
		paramPais02.setNombreParametro(Constants.MAE_PARAM_INDICADOR_LLAMADA_BIENVENIDA);
		paramPais02.setValorParametro(Constants.NUMERO_UNO);
		paramPais02.setIndicadorActivo(Constants.NUMERO_UNO);
		List listParametros02 = genericoService.getParametrosPais(paramPais02);
		this.indLlamadaBienvenida = false;
		if(!listParametros02.isEmpty())
			this.indLlamadaBienvenida = true;
		

	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteMAENuevasForm form = (ReporteMAENuevasForm) this.formReporte;
//		this.setDescargaCSV(true);
		String fecha1,fecha2;
			fecha1 = DateUtil.getDate(form.getFechaInicioDate());
			fecha2 = DateUtil.getDate(form.getFechaFinDate());
			form.setFechaInicio(fecha1);
			form.setFechaFin(fecha2);

		params.put("fechaInicio", form.getFechaInicio());
		params.put("fechaFin", form.getFechaFin());
		
		String codigoPeriodo = form.getCodigoPeriodo();
		if (StringUtils.isBlank(form.getFechaInicio())) {
			params.put("condicionFechaInicio", null);
			params.put("condicionFechaInicio2", null);	
			params.put("fechaInicioCumpleanios", form.getFechaInicio());
		} else {
			String condicionFecha = " AND ( trunc(mc.fec_crea) >= TO_DATE('" + form.getFechaInicio().trim() + "','dd/mm/yyyy') OR " +
                                    "      (mcda.fec_acti IS NOT NULL AND trunc(mcda.fec_acti) >= TO_DATE('" + form.getFechaInicio().trim() + "','dd/mm/yyyy') ) )"  ;
			
            
			if (StringUtils.isNotBlank(form.getFechaFin())) {
				condicionFecha = " AND ( trunc(mc.fec_crea) >= TO_DATE('" + form.getFechaInicio().trim() + "','dd/mm/yyyy') OR " +
                        "      (mcda.fec_acti IS NOT NULL AND trunc(mcda.fec_acti) >= TO_DATE('" + form.getFechaInicio().trim() + "','dd/mm/yyyy') AND trunc(mcda.fec_acti) <= TO_DATE('" + form.getFechaInicio().trim() + "','dd/mm/yyyy') ) )"  ;
			}
			
			params.put("condicionFechaInicio", condicionFecha);
			params.put("condicionFechaInicio2", " AND trunc(b.fec_modi) >= TO_DATE('" + form.getFechaInicio().trim() + "','dd/mm/yyyy') ");
			params.put("fechaInicioCumpleanios", form.getFechaInicio());
		}
		
		if (StringUtils.isBlank(form.getFechaFin())) {
			params.put("condicionFechaFin", null);
			params.put("condicionFechaFin2", null);
			params.put("fechaFinCumpleanios", form.getFechaFin());
		} else {
			String condicionFecha = " AND ( trunc(mc.fec_crea) <= TO_DATE('" + form.getFechaFin().trim() + "','dd/mm/yyyy') OR " +
                    "      (mcda.fec_acti IS NOT NULL AND trunc(mcda.fec_acti) <= TO_DATE('" + form.getFechaFin().trim() + "','dd/mm/yyyy') ) )"  ;
			
			if (StringUtils.isBlank(form.getFechaInicio())) {
				
			}
			params.put("condicionFechaFin", condicionFecha);
			params.put("condicionFechaFin2", " AND trunc(b.fec_modi) <= TO_DATE('" + form.getFechaFin().trim() + "','dd/mm/yyyy') ");
			params.put("fechaFinCumpleanios", form.getFechaFin());
		}	
		
		//Condicionales para los reportes 'reporteMAENuevas' y 'reporteMAENuevasRechazadas'
		if (StringUtils.isBlank(obtieneCondicion(form.getCodigoRegion(), "zr.COD_REGI", "'"))) {
			params.put("condicionRegion1", null);
		} else {
			params.put("condicionRegion1", obtieneCondicion(form.getCodigoRegion(), "zr.COD_REGI", "'"));
		}
		
		if (StringUtils.isBlank(obtieneCondicion(form.getCodigoZona(), "zz.COD_ZONA", "'"))) {
			params.put("condicionZona1", null);
		} else {
			params.put("condicionZona1", obtieneCondicion(form.getCodigoZona(), "zz.COD_ZONA", "'"));
		}
		
		if (StringUtils.isBlank(obtieneCondicion(form.getCodigoRegion(), "SUBSTR (a.uni_admi, 1, 2)", "'"))) {
			params.put("condicionRegion2", null);
		} else {
			params.put("condicionRegion2", obtieneCondicion(form.getCodigoRegion(), "SUBSTR (a.uni_admi, 1, 2)", "'"));
		}
		
		if (StringUtils.isBlank(obtieneCondicion(form.getCodigoZona(), "SUBSTR (a.uni_admi, 3, 4)", "'"))) {
			params.put("condicionZona2", null);
		} else {
			params.put("condicionZona2", obtieneCondicion(form.getCodigoZona(), "SUBSTR (a.uni_admi, 3, 4)", "'"));
		}
		
		//Se obtiene el codigo del pais
		params.put("codigoPais",this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		//Se obtiene el tipo de reporte
		params.put("tipoReporte", form.getTipoConsulta());
		//Se obtiene el codigo de usuario
		params.put("codigoUsuario", this.getmPantallaPrincipalBean().getCurrentUser().getLogin());
		// Se obtiene el oid del pais
		params.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", params));
		// Se obtiene el oid del periodo
		params.put("oidPeriodo", reporteService.getOidPeriodo(params));
		
		// Se obtiene el Oid de la actividad
		params.put("codActividad", "FA");
		params.put("oidActividad", reporteService.getOidActividad(params));
		//Condición para Reporte Activas e Inactivas
		//params.put("condicionBloqueo", obtieneCondicionBloqueo(form.getCodigoTipoBloqueo(), "'"));
		
		params.put("codigoPeriodo", form.getCodigoPeriodo());
		params.put("condicionRegion", obtieneCondicion(form.getCodigoRegion(), "zreg.cod_regi", "'"));
		params.put("condicionZona", obtieneCondicion(form.getCodigoZona(), "zon.cod_zona", "'"));
		if(this.indicadorSinEstado && StringUtils.equals(form.getTipoConsulta(), Constants.REP_MAE_NUEVAS_CONSULTORAS_ACTIVAS))
			params.put("condicionEstado",null);
		else
			params.put("condicionEstado", obtieneCondicionEstado(form.getEstado(), "cda.esta_oid_esta_clie", "'", form.getTipoConsulta()));

		if (StringUtils.equals(form.getIndicadorSinSaldo(), Constants.SI)) {
			params.put("condicionSaldo", " AND cli.sal_deud_ante <= 0 ");
		} else {
			params.put("condicionSaldo", null);
		}
				
		if (StringUtils.equals(form.getIndicadorSinPedido(), Constants.SI)) {
			
			String condicionPedido ="";
			if (/*StringUtils.equals(form.getTipoConsulta(), Constants.REP_MAE_NUEVAS_PRIMER_PEDIDO) ||
				StringUtils.equals(form.getTipoConsulta(), Constants.REP_MAE_NUEVAS_SEGUNDO_PEDIDO) ||
				StringUtils.equals(form.getTipoConsulta(), Constants.REP_MAE_NUEVAS_TERCER_PEDIDO) ||*/
				StringUtils.equals(form.getTipoConsulta(), Constants.REP_MAE_NUEVAS_CONSULTORAS_ACTIVAS) ||
				StringUtils.equals(form.getTipoConsulta(), Constants.REP_MAE_NUEVAS_CONSULTORAS_INACTIVAS)) {
				
				condicionPedido = " AND cli.oid_clie NOT IN(SELECT cu.clie_oid_clie FROM ped_solic_cabec_acum2 cu WHERE cu.perd_oid_peri = " +
										"(SELECT p.oid_peri FROM seg_perio_corpo c, cra_perio p WHERE c.oid_peri = p.peri_oid_peri AND c.cod_peri = " +
										"(SELECT actual.cod_peri FROM bas_ctrl_fact actual WHERE sta_camp = 0 )))";			
			} 
			
			params.put("condicionPedido", condicionPedido);
			
		} else {
			params.put("condicionPedido", null);
		}
		String conDocuPrincipal="";
		String conTipoDocu="";
		
		if(StringUtils.isNotBlank(form.getCodigoTipoDocu())){
			conTipoDocu= "and ced.tdoc_oid_tipo_docu = '"+form.getCodigoTipoDocu()+"'";
			conDocuPrincipal=null;
			params.put("condicionDocuPrincipal", conTipoDocu);	
		}else{
			conTipoDocu=null;
			conDocuPrincipal="AND ced.val_iden_docu_prin = 1";
			params.put("condicionDocuPrincipal", conDocuPrincipal);
		}
			
			
		if(StringUtils.isNotBlank(form.getTipoArchivoActiva())){
			//Cod Consultora
			String condCliente="";
			if(StringUtils.equals(form.getTipoArchivoActiva(), "CLIE")){
				condCliente=this.obtieneCondicion(this.codClienteDocumento, "cli.cod_clie", "'");				
			}else{
				//nro Documento de Identidad
				condCliente=this.obtieneCondicion(this.codClienteDocumento, "ced.num_docu_iden", "'");
			}								
			params.put("condicionClienteDocu", condCliente);
		}else
			params.put("condicionClienteDocu", null);
		
		
		
		
//		Map parameterMap = new HashMap();
//		parameterMap.put("nombreArchivo", "reporteGenerico");
//		parameterMap.put("extensionArchivo", CSV_EXTENSION);
//		parameterMap.put("directorioArchivo", request.getRealPath("/"));
//		parameterMap.put("nombreArchivoDescarga", getReporteFileName() + CSV_EXTENSION);
//		request.getSession().setAttribute("parameterMap", parameterMap);
		
		if(form.isIndicadorClientesBloqueados()){
			params.put("indicadorDesbloqueadas", Constants.NUMERO_UNO);
		}else{
			params.put("indicadorDesbloqueadas", Constants.NUMERO_CERO);
		}
		
		if(log.isDebugEnabled()){
			log.debug("indicadorDesbloqueadas: "+MapUtils.getString(params, "indicadorDesbloqueadas"));
		}
		
		//INI ECU-SiCC-2015-0036
        ParametroPais paramPais = new ParametroPais();
      	paramPais.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
      	paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
      	paramPais.setNombreParametro(Constants.MAE_PARAM_INDICADOR_CAMPOS_ADICIONALES);
      	paramPais.setValorParametro(Constants.NUMERO_UNO);

      	GenericoService genericoService = (GenericoService) getBean("genericoService");
      	List listParametros = genericoService.getParametrosPais(paramPais);

      		if (listParametros != null && listParametros.size() > 0) {
      			params.put("indCamposAdicionales", true);
      		} else {
      			params.put("indCamposAdicionales", false);
      		}
       //FIN ECU-SiCC-2015-0036
      		
      		if(log.isDebugEnabled()){
      			log.debug("Fin de prepare, datos del Form: "+form.toString());
      			log.debug("/*********************************************/");
    			log.debug("Fin de prepare, datos del params: "+params.toString());
    		}		
		
		return params;
	}
	
	public void valoresByDefault(String codigoPeriodo){
		if(log.isDebugEnabled()){
			log.debug("valoresByDefault");
		}		
		this.loadFechasPeriodo(codigoPeriodo);
	}
	
	public void loadRegiones(){
		if(log.isDebugEnabled()){
			log.debug("loadRegiones");
		}
		ReporteMAENuevasForm form = (ReporteMAENuevasForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		this.siccRegionList = ajaxService.getRegionesByPaisMarcaCanal(
									this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
									Constants.CODIGO_MARCA_DEFAULT,
									Constants.CODIGO_CANAL_DEFAULT);
		this.loadZonas();
	}
	
	public void loadFechasPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo");
		}

		ReporteMAENuevasForm form = (ReporteMAENuevasForm) this.formReporte;
		if (StringUtils.isNotBlank(valor) && valor.length() == 6) {
			AjaxService ajaxService = (AjaxService) this.getBean("ajaxService");
			form.setFechaInicio(ajaxService
					.getFechaInicioPeriodoByPaisMarcaCanal(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor));
			// form.getCodigoPeriodo()));
			form.setFechaFin(ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
					this.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, valor));
			// form.getCodigoPeriodo()));

			try {
				form.setFechaInicioDate(DateUtil.convertStringToDate(
						Constants.DEFAULT_DATE_FORMAT, form.getFechaInicio()));
				form.setFechaFinDate(DateUtil.convertStringToDate(
						Constants.DEFAULT_DATE_FORMAT, form.getFechaFin()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadZonas(){
		if(log.isDebugEnabled()){
			log.debug("loadZonas");
		}
		ReporteMAENuevasForm form = (ReporteMAENuevasForm) this.formReporte;
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		if(form.getCodigoRegion()!=null){
			this.siccZonaList = ajaxService.getZonasByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
					Constants.CODIGO_CANAL_DEFAULT,
					Constants.CODIGO_MARCA_DEFAULT,form.getCodigoRegion()[0]);
		}
	}
	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("showZonasxRegion:ValueChangeEvent");
		}
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		log.debug(val.getNewValue().toString());
		if(StringUtils.isNotEmpty(val.getNewValue().toString()) 
				|| StringUtils.isNotBlank(val.getNewValue().toString())){
			String[] regionListado = (String[])val.getNewValue();
			log.debug(regionListado.length);
			if(regionListado.length>0){
				this.siccZonaList = ajaxService.getZonasByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
						Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT,regionListado[0]);
			}
		}		
	}
	
	public void loadEstadosConsultora(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("loadEstadosConsultora");
		}
		AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");
		String valor = (String)val.getNewValue();
		if(StringUtils.isNotBlank(valor) 
				&& StringUtils.isNotEmpty(valor)){
			this.setMaeEstadoClienteList(ajaxService.getEstadosConsultoraByReporte((String) val.getNewValue()));
			this.camposInactivos((String)val.getNewValue());
		}
		
		if(StringUtils.equalsIgnoreCase(valor, Constants.NUMERO_CERO) || (StringUtils.equalsIgnoreCase(valor, Constants.NUMERO_SIETE))){
			this.mostrarClientesBloqueados = true;
		}else{
			this.mostrarClientesBloqueados = false;
		}
		
	}
	
	public void camposInactivos(String valor){
		if(log.isDebugEnabled()){
			log.debug("loadEstadosConsultora");
		}
		if(StringUtils.equals(valor, "2")  || 
				StringUtils.equals(valor, "3") || 
				StringUtils.equals(valor, "4") || 
				StringUtils.equals(valor, "5") || 
				StringUtils.equals(valor, "6")){
			this.setMostrarFechaInicio(false);
			this.setMostrarFechaFin(false);
			this.setMostrarSinSaldo(true);
//    		fechaInicio.style.display = "none";
//			fechaFin.style.display = "none";
//			sinSaldoTitulo.style.display ="block";
//    		sinSaldoControl.style.display ="block";
    	}else{
    		this.setMostrarFechaInicio(true);
			this.setMostrarFechaFin(true);
    		this.setMostrarSinSaldo(false);
//			fechaInicio.style.display = "block";
//			fechaFin.style.display = "block";
//			sinSaldoTitulo.style.display ="none";
//    		sinSaldoControl.style.display ="none";
    	}
    	
    	if(StringUtils.equals(valor, "5") || 
    			StringUtils.equals(valor, "6")){
    		this.setMostrarCodigoPeriodo(false);
//    		codigoPeriodo.style.display = "none";
    	}else{
    		this.setMostrarCodigoPeriodo(true);
//    		codigoPeriodo.style.display = "block";
    	}

    	if(StringUtils.equals(valor, "5")  || 
    			StringUtils.equals(valor, "6")){
    		this.setMostrarRegionAbierta(true);
    		this.setMostrarSinSaldo(true);
    		this.setMostrarSinPedido(true);
    		this.setMostrarEstado(true);
    		this.setMostrarCodigoPeriodo(true);
//    		regionAbierta.style.display ="block";
//    		sinSaldoTitulo.style.display ="block";
//    		sinSaldoControl.style.display ="block";
//    		sinPedidoTitulo.style.display ="block";
//    		sinPedidoControl.style.display ="block";
//    		estadoTitulo.style.display ="block";
//    		estadoControl.style.display ="block";
//    		codigoPeriodo.style.display = "block";
    	}else{
    		this.setMostrarRegionAbierta(false);
    		this.setMostrarSinPedido(false);
    		this.setMostrarEstado(false);//    		
        }
    	
    	if(StringUtils.equals(valor, "5"))
    		this.mostrarCondicionActiva=true;
	}
	
	public void sinEstado(ValueChangeEvent e){
		Boolean flag = ((Boolean)e.getNewValue()).booleanValue();
		this.indicadorSinEstado=flag;
		if(this.indicadorSinEstado)
			this.mostrarEstadoActiva=true;
		else
			this.mostrarEstadoActiva=false;
		
	}
	
	//Mostrar La Carga de Archivos
	public void loadTipoArchivo(ValueChangeEvent e){
		String valor=e.getNewValue().toString();		
		if(StringUtils.isNotBlank(valor))
			this.mostrarTipoArchivo=true;
		else
			this.mostrarTipoArchivo=false;
		
	}	
	
	public void handleFileUpload(FileUploadEvent event) throws Exception {		
		ReporteMAENuevasForm f = (ReporteMAENuevasForm) this.formReporte;
		if (event != null) {			
			f.setClienteFile(event.getFile());
			this.attachment=event.getFile().getFileName();			
			this.uploadArchivo();
			ExcelUtil excelUtil = new ExcelUtil(f.getDirectorioTemporal(), this.attachment);		
			String codigos="";
			List<String> codigoLista= new ArrayList<String>();
			excelUtil.initSheet(0);
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.next();
				codigos = mapRow.get("0").toString().trim();
				if(StringUtils.isNotBlank(codigos))
					codigoLista.add(codigos);
				
			}
			excelUtil.cerrar();
			String []temp= new String [codigoLista.size()];
			temp=codigoLista.toArray(temp);		
			this.codClienteDocumento=temp;			
		}
	}
	
	public void uploadArchivo(){	
		try {			
			ReporteMAENuevasForm f = (ReporteMAENuevasForm) this.formReporte;			
			UploadedFile archivo = f.getClienteFile();				
			// leyemos el stream de entrada
			InputStream is = archivo.getInputstream();
			// abrimos el stream de escritura, ubicacion al cual se grabara el
			// archivo del cliente
			OutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), this.attachment));
			// grabamos cada 1024 bytes
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();					
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteMAENuevasService";
	}
	
	/**
	 * Obtiene condicion estado.
	 *
	 * @param lista the lista
	 * @param parametro the parametro
	 * @param comilla the comilla
	 * @param tipoReporte the tipo reporte
	 * @return the string
	 */
	protected String obtieneCondicionEstado(String[] lista, String parametro, String comilla, String tipoReporte) {
		
		if(StringUtils.equals(tipoReporte, Constants.REP_MAE_NUEVAS_CONSULTORAS_ACTIVAS) ||
				StringUtils.equals(tipoReporte, Constants.REP_MAE_NUEVAS_CONSULTORAS_INACTIVAS))
		{
			String resultado = "";	
			if (lista == null || lista.length == 0){				
				
					if(StringUtils.equals(tipoReporte, Constants.REP_MAE_NUEVAS_CONSULTORAS_ACTIVAS)) 
						return  " AND " + parametro + " IN (2, 3, 4, 6, 8) ";					
					else		
						return  " AND " + parametro + " IN (1,5,7) ";	
					
			}
			else {
				for (int i = 0; i < lista.length; i++) {
					String dato = lista[i];
					if (StringUtils.isEmpty(dato) || StringUtils.equals(dato, "Todos")){			
						
						if(StringUtils.equals(tipoReporte, Constants.REP_MAE_NUEVAS_CONSULTORAS_ACTIVAS)) 
							return  " AND " + parametro + " IN (2, 3, 4, 6, 8) ";					
						else		
							return  " AND " + parametro + " IN (1,5,7) ";							
					}
					/*if (StringUtils.equals(dato, "Todos"))
					{
						if (StringUtils.equals(tipoReporte, Constants.REP_MAE_NUEVAS_CONSULTORAS_ACTIVAS)) {
							resultado = resultado + "(SELECT MEC.OID_ESTA_CLIE AS CODIGO " + 
													" FROM   MAE_ESTAT_CLIEN MEC, GEN_I18N_SICC_COMUN GISC " + 
													" WHERE  MEC.OID_ESTA_CLIE = GISC.VAL_OID AND  GISC.ATTR_ENTI = 'MAE_ESTAT_CLIEN'" + 
													" AND MEC.OID_ESTA_CLIE NOT IN (1,5,7))";			
						} 
						else if (StringUtils.equals(tipoReporte, Constants.REP_MAE_NUEVAS_CONSULTORAS_INACTIVAS)) {
							resultado = resultado + "(SELECT MEC.OID_ESTA_CLIE AS CODIGO " + 
							" FROM   MAE_ESTAT_CLIEN MEC, GEN_I18N_SICC_COMUN GISC " + 
							" WHERE  MEC.OID_ESTA_CLIE = GISC.VAL_OID AND  GISC.ATTR_ENTI = 'MAE_ESTAT_CLIEN'" + 
							" AND MEC.OID_ESTA_CLIE IN (1,5,7))";
						}
						
						resultado = " AND " + parametro + " IN " + resultado;
						return resultado;
					}*/
					if (i == 0)
						resultado = resultado + "(" + comilla + dato + comilla;
					else
						resultado = resultado + "," + comilla + dato + comilla;
				}
				resultado = resultado + ")";
				resultado = " AND " + parametro + " IN " + resultado;
				return resultado;
			}
		}
		else
			return "";		
	}
	
	/**
	 * Obtiene condicion bloqueo.
	 *
	 * @param lista the lista
	 * @param comilla the comilla
	 * @return the string
	 */
	protected String obtieneCondicionBloqueo(String[] lista, String comilla) {		
		if (lista == null || lista.length == 0)
			return "";
		else {			 			 
			 String parametroNiguno = " cli.oid_clie not in ";
			 String parametroOtros = " cli.oid_clie in ";
			 String parametroSelectAll = "SELECT VAL_OID FROM GEN_I18N_SICC_COMUN gen,mae_tipo_bloqu mtb WHERE ATTR_ENTI = 'MAE_TIPO_BLOQU' and mtb.oid_tipo_bloq=gen.val_oid";
			 String parametroQery = 
					"  ( SELECT    mcb.clie_oid_clie " +						                    
			                 "FROM mae_clien_bloqu mcb, mae_tipo_bloqu mtb, gen_i18n_sicc_comun gen " +						                            
			                 "WHERE  mcb.tibq_oid_tipo_bloq=mtb.oid_tipo_bloq AND mtb.oid_tipo_bloq=gen.val_oid AND gen.attr_enti LIKE 'MAE_TIPO_BLOQU' " +
			                 "and mcb.fec_desb is NULL AND cli.oid_clie = mcb.clie_oid_clie AND mtb.oid_tipo_bloq ";

			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				
				//Si no se selecciona ningun valor de la lista
				if (StringUtils.isEmpty(dato))
					return "";
				
				//Si se selecciona el valor "Todos" de la lista
				else if( StringUtils.equals(dato, "Todos")){
					return resultado = " AND " + parametroOtros + parametroQery + " IN (" + parametroSelectAll + ")) ";
				}					
				
				//Si se selecciona el valor "Ninguno" de la lista
				else if( StringUtils.equals(dato, "0")){
					return resultado = " AND " + parametroNiguno + parametroQery + " IN (" + parametroSelectAll + ")) ";
				}
				
				//Si se selecciona algún valor de la lista
				else{
					if (i == 0)
						resultado = resultado + "(" + comilla + dato + comilla;
					else
						resultado = resultado + "," + comilla + dato + comilla;		
				}
			}
			resultado = resultado + ")";
			resultado = " AND " + parametroOtros + parametroQery + " IN " + resultado + ") ";
			return resultado;
		}
	}
	
	public String setValidarReporte(){
		ReporteMAENuevasForm f = (ReporteMAENuevasForm) this.formReporte;	
		if(StringUtils.equals(f.getTipoConsulta(), Constants.REP_MAE_NUEVAS_CONSULTORAS_ACTIVAS)){
			if(this.codClienteDocumento.length==0 && StringUtils.isNotBlank(f.getTipoArchivoActiva())) {
				if(StringUtils.equals(f.getTipoArchivoActiva(), "CLIE"))
					return "Debe ingresar Archivo con Códigos de Clientes";
				else
					return "Debe ingresar Archivo con Nro. de Documentos";			
			}			
		}			
		return "";
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getMaeEstadoClienteList() {
		return maeEstadoClienteList;
	}

	/**
	 * @param maeEstadoClienteList
	 */
	public void setMaeEstadoClienteList(LabelValue[] maeEstadoClienteList) {
		this.maeEstadoClienteList = maeEstadoClienteList;
	}

	/**
	 * @return
	 */
	public boolean isMostrarFechaInicio() {
		return mostrarFechaInicio;
	}

	/**
	 * @param mostrarFechaInicio
	 */
	public void setMostrarFechaInicio(boolean mostrarFechaInicio) {
		this.mostrarFechaInicio = mostrarFechaInicio;
	}

	/**
	 * @return
	 */
	public boolean isMostrarFechaFin() {
		return mostrarFechaFin;
	}

	/**
	 * @param mostrarFechaFin
	 */
	public void setMostrarFechaFin(boolean mostrarFechaFin) {
		this.mostrarFechaFin = mostrarFechaFin;
	}

	/**
	 * @return
	 */
	public boolean isMostrarCodigoPeriodo() {
		return mostrarCodigoPeriodo;
	}

	/**
	 * @param mostrarCodigoPeriodo
	 */
	public void setMostrarCodigoPeriodo(boolean mostrarCodigoPeriodo) {
		this.mostrarCodigoPeriodo = mostrarCodigoPeriodo;
	}

	/**
	 * @return
	 */
	public boolean isMostrarRegionAbierta() {
		return mostrarRegionAbierta;
	}

	/**
	 * @param mostrarRegionAbierta
	 */
	public void setMostrarRegionAbierta(boolean mostrarRegionAbierta) {
		this.mostrarRegionAbierta = mostrarRegionAbierta;
	}

	/**
	 * @return
	 */
	public boolean isMostrarSinSaldo() {
		return mostrarSinSaldo;
	}

	/**
	 * @param mostrarSinSaldo
	 */
	public void setMostrarSinSaldo(boolean mostrarSinSaldo) {
		this.mostrarSinSaldo = mostrarSinSaldo;
	}

	/**
	 * @return
	 */
	public boolean isMostrarSinPedido() {
		return mostrarSinPedido;
	}

	/**
	 * @param mostrarSinPedido
	 */
	public void setMostrarSinPedido(boolean mostrarSinPedido) {
		this.mostrarSinPedido = mostrarSinPedido;
	}

	/**
	 * @return
	 */
	public boolean isMostrarEstado() {
		return mostrarEstado;
	}

	/**
	 * @param mostrarEstado
	 */
	public void setMostrarEstado(boolean mostrarEstado) {
		this.mostrarEstado = mostrarEstado;
	}

	/**
	 * @return the mostrarCondicionActiva
	 */
	public boolean isMostrarCondicionActiva() {
		return mostrarCondicionActiva;
	}

	/**
	 * @param mostrarCondicionActiva the mostrarCondicionActiva to set
	 */
	public void setMostrarCondicionActiva(boolean mostrarCondicionActiva) {
		this.mostrarCondicionActiva = mostrarCondicionActiva;
	}

	/**
	 * @return the indicadorSinEstado
	 */
	public boolean isIndicadorSinEstado() {
		return indicadorSinEstado;
	}

	/**
	 * @param indicadorSinEstado the indicadorSinEstado to set
	 */
	public void setIndicadorSinEstado(boolean indicadorSinEstado) {
		this.indicadorSinEstado = indicadorSinEstado;
	}

	/**
	 * @return the mostrarTipoArchivo
	 */
	public boolean isMostrarTipoArchivo() {
		return mostrarTipoArchivo;
	}

	/**
	 * @param mostrarTipoArchivo the mostrarTipoArchivo to set
	 */
	public void setMostrarTipoArchivo(boolean mostrarTipoArchivo) {
		this.mostrarTipoArchivo = mostrarTipoArchivo;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the mostrarEstadoActiva
	 */
	public boolean isMostrarEstadoActiva() {
		return mostrarEstadoActiva;
	}

	/**
	 * @param mostrarEstadoActiva the mostrarEstadoActiva to set
	 */
	public void setMostrarEstadoActiva(boolean mostrarEstadoActiva) {
		this.mostrarEstadoActiva = mostrarEstadoActiva;
	}

	/**
	 * @return the codClienteDocumento
	 */
	public String[] getCodClienteDocumento() {
		return codClienteDocumento;
	}

	/**
	 * @param codClienteDocumento the codClienteDocumento to set
	 */
	public void setCodClienteDocumento(String[] codClienteDocumento) {
		this.codClienteDocumento = codClienteDocumento;
	}

	/**
	 * @return the mostrarClientesBloqueados
	 */
	public boolean getMostrarClientesBloqueados() {
		return mostrarClientesBloqueados;
	}

	/**
	 * @param mostrarClientesBloqueados the mostrarClientesBloqueados to set
	 */
	public void setMostrarClientesBloqueados(boolean mostrarClientesBloqueados) {
		this.mostrarClientesBloqueados = mostrarClientesBloqueados;
	}

	/**
	 * @return the tipoDocumentoList
	 */
	public List getTipoDocumentoList() {
		return tipoDocumentoList;
	}

	/**
	 * @param tipoDocumentoList the tipoDocumentoList to set
	 */
	public void setTipoDocumentoList(List tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}

	/**
	 * @return the indicadorCamposAdicionales
	 */
	public boolean isIndicadorCamposAdicionales() {
		return indicadorCamposAdicionales;
	}

	/**
	 * @param indicadorCamposAdicionales the indicadorCamposAdicionales to set
	 */
	public void setIndicadorCamposAdicionales(boolean indicadorCamposAdicionales) {
		this.indicadorCamposAdicionales = indicadorCamposAdicionales;
	}

	/**
	 * @return the indLlamadaBienvenida
	 */
	public boolean isIndLlamadaBienvenida() {
		return indLlamadaBienvenida;
	}

	/**
	 * @param indLlamadaBienvenida the indLlamadaBienvenida to set
	 */
	public void setIndLlamadaBienvenida(boolean indLlamadaBienvenida) {
		this.indLlamadaBienvenida = indLlamadaBienvenida;
	}
	
	
	
	
}
