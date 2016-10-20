package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.spusicc.inc.form.ReporteINCEstadoPremioDespachadoForm;
import biz.belcorp.ssicc.reportes.web.spusicc.inc.form.ReporteINCGenerarReporteIncentivosForm;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCGenerarReporteIncentivosService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * 
 * @author <a href="">Sergio Buchelli Silva</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteINCGenerarReporteIncentivosAction extends BaseReporteAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3077245934908815253L;
	private String numeroLote;
	private int numRegistros;
	
	private List listaMarca;
	private List listaCanal;
	private List listaTipoCierre;
	
	private String formatoExportacion;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		//ReporteINCGenerarReporteIncentivosForm reporteForm = new ReporteINCGenerarReporteIncentivosForm();
		ReporteINCEstadoPremioDespachadoForm reporteForm = new ReporteINCEstadoPremioDespachadoForm();
		
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {				
		String nombreReporte = null;
		
		if("PDF".equals(formatoExportacion))
			nombreReporte ="reporteMaestroHorizontal";
		else
			nombreReporte = "reporteINCPremiosDespachadosXLS";
		return nombreReporte;		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {		
		String subReporte = "";
		
		if("PDF".equals(formatoExportacion))
			subReporte ="reporteINCPremiosDespachadosPDF";
		return subReporte;		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService(){
		return "reportes.reporteINCGenerarReporteIncentivosService";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte()
	 */
	protected String errorKeyGenerarMultipleReporte() {
		return "reporteINCGenerarReporteIncentivos.msg.envioMailCronograma.vacio";						
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception{	

		log.debug("Entering to ReporteINCConsolidadoPremioDespachadoAction - prepareParameterMap");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		String codigoPais = pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		//ReporteINCGenerarReporteIncentivosForm f = (ReporteINCGenerarReporteIncentivosForm) this.formReporte;
		ReporteINCEstadoPremioDespachadoForm f = (ReporteINCEstadoPremioDespachadoForm) this.formReporte;
		
		params.put("NroReporte", "");
		params.put("titulo", this.getReportResourceMessage("reporteINCGenerarReporteIncentivosForm.titulo"));
		params.put("formatoExportacion","PDF");
		params.put("codigoPais", codigoPais);
		params.put("codigoCanal", f.getCodigoCanal());
		params.put("codigoMarca", f.getCodigoMarca());
		params.put("tipoCierre", f.getTipoCierre());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("fechaProceso", f.getFechaProceso());
		params.put("usuario", usuario);
		params.put("codigoUsuario",usuario.getLogin());
		params.put("numeroLote",f.getNumeroLote());
		
		formatoExportacion = f.getFormatoExportacion();
		return params;
		
	}
	
	/* 
	 * Devuelve la cantida de reportes a generar en base ala region y sus zonas correspondientes
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {		
		//ReporteINCGenerarReporteIncentivosForm f = (ReporteINCGenerarReporteIncentivosForm) this.formReporte;
		ReporteINCEstadoPremioDespachadoForm f = (ReporteINCEstadoPremioDespachadoForm) this.formReporte;
		
		Map  map = new HashMap();
		ProcesoINCGenerarReporteIncentivosService service =
						(ProcesoINCGenerarReporteIncentivosService)																
									getBean("spusicc.procesoINCGenerarReporteIncentivosService");
		map.put("tipoCierre", f.getTipoCierre());
		map.put("fechaProceso",f.getFechaProceso());
		this.numeroLote = f.getNumeroLote();//setando el numero de lote 
		Integer size =service.getSizeEstadoPremioDespacho(map);
		if(size!=null && size.intValue()==0){		
			numRegistros=0;
			String mensaje = this.getResourceMessage("reporteINCGenerarReporteIncentivosForm.titulo");
			this.addError("Error", mensaje);
			return 0;
		}
		numRegistros= size.intValue();
		return 1;
	}
	
	/*
	 * Devuelve el nombre del Archivo del Reporte
	 *  (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.scsicc.service.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String numeroLote = (String)parameterMap.get("numeroLote");
//		String tipoCierre = (String)parameterMap.get("tipoCierre");
//		String codigoPeriodo = (String)parameterMap.get("codigoPeriodo");
//		String fechaProceso = (String)parameterMap.get("fechaProceso");
		log.debug("getNombreArchivoReporte params " +parameterMap);
//		String [] arr =StringUtils.split(fechaProceso,"/");
//		if(arr!=null ){
//			fechaProceso = arr[2]+arr[1]+arr[0];
//		}else {
//			fechaProceso ="";
//		}	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		/*SETEANDO el num d elote para verificaciones posteriores donde se ernnevia la proceso o reporte (jsp)*/
		if(StringUtils.isNotEmpty(numeroLote)){
		  this.numeroLote = numeroLote;	
		}else{
			this.numeroLote = null;	
		}	
		try {
			nombreArchivoReporte= this.devuelveNombreSubReporte()+"_"+sdf.format(new Date(System.currentTimeMillis()));	
		}
		catch(Exception e) {
			nombreArchivoReporte= "_"+sdf.format(new Date(System.currentTimeMillis()));	
		}
		return nombreArchivoReporte;
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		return "inc.mailReporteGenerarReporteIncentivosService";
	}
	

	
	/* Realiza la carga Inicial 
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'ReporteINCEstadoPremioDespachadoAction.setViewAtributes' method");            
        }
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		//ReporteINCGenerarReporteIncentivosForm f = (ReporteINCGenerarReporteIncentivosForm) this.formReporte;
		ReporteINCEstadoPremioDespachadoForm f = (ReporteINCEstadoPremioDespachadoForm) this.formReporte;
		
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceOCR.getControlFacturacionById(criteriaPeriodo);
		
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setTipoCierre(Constants.INC_TIPO_CIERRE_DIARIO);
		
		this.mostrarReporteMailPDF = true;
		this.mostrarReporteXLS = true;
		
		setListaMarca(service.getMarcas());
		setListaCanal(service.getCanalesByCodigoISO(this.getmPantallaPrincipalBean().getCurrentIdioma().getCodigoISO()));
		
		ArrayList<Base> reporteTipo = new ArrayList<Base>();
		Base[] baseTipo = new Base[4];
		
		baseTipo[0] = new Base();
		baseTipo[0].setCodigo(Constants.INC_TIPO_CIERRE_DIARIO);
		baseTipo[0].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoDiario"));
		reporteTipo.add(baseTipo[0]);
		
		baseTipo[1] = new Base();
		baseTipo[1].setCodigo(Constants.INC_TIPO_CIERRE_REGION);
		baseTipo[1].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoRegion"));
		reporteTipo.add(baseTipo[1]);
		
		baseTipo[2] = new Base();
		baseTipo[2].setCodigo(Constants.INC_TIPO_CIERRE_ZONA);
		baseTipo[2].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoZona"));
		reporteTipo.add(baseTipo[2]);
		
		baseTipo[3] = new Base();
		baseTipo[3].setCodigo(Constants.INC_TIPO_CIERRE_CAMPANA);
		baseTipo[3].setDescripcion(this.mPantallaPrincipalBean.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoCampana"));
		reporteTipo.add(baseTipo[3]);
		
		listaTipoCierre = reporteTipo;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeGrabarReporte(biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public void beforeGrabarReporte() {
		/*primera accion q se ejecuta */
		super.beforeGrabarReporte();		
		//ReporteINCGenerarReporteIncentivosForm form = (ReporteINCGenerarReporteIncentivosForm) this.formReporte;
		ReporteINCEstadoPremioDespachadoForm form = (ReporteINCEstadoPremioDespachadoForm) this.formReporte;
		
		//recuperamos el lote si es qe vino de proceso de generacion , seria nullo si viene de envio de reporte de la pagna de reporte
		String numeroLote = form.getNumeroLoteReporteDespachoInc();
		form.setNumeroLote(numeroLote);
		log.debug("beforeGrabarReporte T Cierre "+form.getTipoCierre());
		log.debug("beforeGrabarReporte N lote "+form.getNumeroLote());
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#afterGrabarReporte(biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public void afterGrabarReporte(){
		
		//ReporteINCGenerarReporteIncentivosForm f = (ReporteINCGenerarReporteIncentivosForm) this.formReporte;
		ReporteINCEstadoPremioDespachadoForm f = (ReporteINCEstadoPremioDespachadoForm) this.formReporte;
		
		if(numRegistros>0){
			super.afterGrabarReporte();
			log.debug("afterGrabarReporte");
			String key ="";
			if(StringUtils.isNotEmpty(f.getNumeroLote())){
				key ="reporteINCGenerarReporteIncentivos.msg.proceso.envioMailCronograma";				
			}else{
				key= "reporteINCGenerarReporteIncentivos.msg.envioMailCronograma";
			}
			String mensaje = this.getResourceMessage(key);
			this.addInfo("Error", mensaje);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#exitoKeyEnvioReporteViaMail()
	 */
	protected String exitoKeyEnvioReporteViaMail() {
		return "reporteINCGenerarReporteIncentivos.msg.envioMailCronograma";						
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#mensajeErrorValidacionPrevia()
	 */
	public String setValidarReporte(){
		
        ReporteINCEstadoPremioDespachadoForm f = (ReporteINCEstadoPremioDespachadoForm) this.getFormReporte();
        //ReporteINCGenerarReporteIncentivosForm f = (ReporteINCGenerarReporteIncentivosForm) this.getFormReporte();
		
		Map  map = new HashMap();
		ProcesoINCGenerarReporteIncentivosService service =	(ProcesoINCGenerarReporteIncentivosService)
				this.getBeanService("spusicc.procesoINCGenerarReporteIncentivosService");									
		map.put("tipoCierre", f.getTipoCierre());
		map.put("fechaProceso",f.getFechaProceso());
		
		Integer size =service.getSizeEstadoPremioDespacho(map);
		
		if(size!=null && size.intValue()==0){		
			//request.getSession().removeAttribute("numeroLoteReporteDespachoInc");
			//f.setNumRegistros(0);						
			String msg1 = this.getResourceMessage("reporteINCEstadoPremioDespachadoForm.no.informacion.reporte1");
			String msg2 = this.getResourceMessage("reporteINCEstadoPremioDespachadoForm.no.informacion.reporte2");
			String m2 = "";
			
			String tipoCierre = f.getTipoCierre();
			
			if (tipoCierre.equals(Constants.INC_TIPO_CIERRE_DIARIO)){
				m2 = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoDiario");
			}else if(tipoCierre.equals(Constants.INC_TIPO_CIERRE_REGION)){
				m2 = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoRegion");
			}else if(tipoCierre.equals(Constants.INC_TIPO_CIERRE_ZONA)){
				m2 = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoZona");
			}else if(tipoCierre.equals(Constants.INC_TIPO_CIERRE_CAMPANA)){
				m2 = this.getResourceMessage("procesoINCGenerarReporteIncentivosForm.tipoCampana");
			}
					
			return msg1 + " " + f.getFechaProceso() + " " + msg2 + " "+ m2;
		}
		
		//f.setNumRegistros(size.intValue());
		return "";
	}

	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}

	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	/**
	 * @return the numRegistros
	 */
	public int getNumRegistros() {
		return numRegistros;
	}

	/**
	 * @param numRegistros the numRegistros to set
	 */
	public void setNumRegistros(int numRegistros) {
		this.numRegistros = numRegistros;
	}

	/**
	 * @return the listaMarca
	 */
	public List getListaMarca() {
		return listaMarca;
	}

	/**
	 * @param listaMarca the listaMarca to set
	 */
	public void setListaMarca(List listaMarca) {
		this.listaMarca = listaMarca;
	}

	/**
	 * @return the listaCanal
	 */
	public List getListaCanal() {
		return listaCanal;
	}

	/**
	 * @param listaCanal the listaCanal to set
	 */
	public void setListaCanal(List listaCanal) {
		this.listaCanal = listaCanal;
	}

	/**
	 * @return the listaTipoCierre
	 */
	public List getListaTipoCierre() {
		return listaTipoCierre;
	}

	/**
	 * @param listaTipoCierre the listaTipoCierre to set
	 */
	public void setListaTipoCierre(List listaTipoCierre) {
		this.listaTipoCierre = listaTipoCierre;
	}
}