package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICPedidosDigitadosZonaForm;



/**
 * The Class ReporteSICPedidosDigitadosZonaAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 27/05/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICPedidosDigitadosZonaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private LabelValue[] siccRegionList = {};
	
	private String indicadorReporte;
	private String codigoPerido;
	private String[] listaRegiones;
	private String[] listaZonas;
	private String[] listaTotal;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICPedidosDigitadosZonaForm form = new ReporteSICPedidosDigitadosZonaForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICPedidosDigitadosZonaForm form = (ReporteSICPedidosDigitadosZonaForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(this.formReporte.getFormatoExportacion()) || "VPDF".equals(this.formReporte.getFormatoExportacion())){
			return "reporteMaestroHorizontal";
		}else{
			return "reporteSICPedidosDigitadosZona";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICPedidosDigitadosZonaForm form = (ReporteSICPedidosDigitadosZonaForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String subReporte = null;
		if ("PDF".equals(this.formReporte.getFormatoExportacion()) || "VPDF".equals(this.formReporte.getFormatoExportacion())){
			subReporte ="reporteSICPedidosDigitadosZona";
		}
		return subReporte;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		String service = "sic.mailReporteGenerarPedidosDigitadosZonaService";		
		return service;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICPedidosDigitadosZonaForm f = (ReporteSICPedidosDigitadosZonaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		Map criteria = params;
		
		//Parametros estandar que se envían a los reportes
		params.put("NroReporte", "");
		params.put("titulo",getResourceMessage("ReporteSICPedidosDigitadosZonaForm.titulo"));
		params.put("codigoPais", f.getPais().getCodigo());	
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		
		params.put("titulo", this.getResourceMessage("reporteSICPedidosDigitadosZona.titulo"));
		params.put("formatoExportacion",f.getFormatoExportacion());
		
		//Parametros del reporte
		criteria.put("codigoPeriodo", f.getPeriodo());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);	
		params.put("oidPeriodo", new Long(oidPeriodo));
		params.put("codigoPeriodo", f.getPeriodo());
		params.put("periodoAnterior", reporteService.getOidString("getDesPeriodoByCodigoPeriodoAnterior", params));
		params.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", params));
		params.put("tipoSolicitud", Constants.REPORTE_SIC_PEDIDOS_DIGITADOS_SOLICITUD);
		params.put("oidTipoSolicitud", new Long(reporteService.getOidString("getTipoSolicitud", params)));
		
		//oid marca //oid canal
		Long oidMarca = clienteService.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);
		
		params.put("oidMarca", oidMarca);
		params.put("oidCanal", oidCanal);
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "a.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		params.put("condicionZona", "");	
		
		
		//obteniendo los peridods anteriores -1,-2,-3
		String oidPeriodoMenosUno = getPeriodoAnterior(oidPeriodo);
		params.put("oidPeriodoMenosUno", new Long(oidPeriodoMenosUno));
		
		String oidPeriodoMenosDos = getPeriodoAnterior(oidPeriodoMenosUno);
		params.put("oidPeriodoMenosDos", new Long(oidPeriodoMenosDos));
		
		String oidPeriodoMenosTres = getPeriodoAnterior(oidPeriodoMenosDos);
		params.put("oidPeriodoMenosTres", new Long(oidPeriodoMenosTres));
		
		if (this.formReporte.isEnvioEmail()) {
			params.put("titulo", this.getResourceMessage("reporteSICGenerarPedidosDigitadosZonaForm.titulo"));
			  
			String temp = this.listaTotal[this.getNroReporteProcesando() - 1 ];
		    int pos = StringUtils.indexOf(temp, '|');
		    if (pos < 0) {
		    	this.indicadorReporte = Constants.REPORTE_SIC_GENERACION_PEDIDOS_DIGITADOS_REGION;
		    	params.put("codigoRegion", temp);
		    	params.put("codigoZona", null);	
		    	String condicionRegion2 = "and a.COD_REGI in ('"+temp+"')";
		    	params.put("condicionRegion", condicionRegion2);
		    	params.put("condicionZona", "");
		    }
		    else {
		    	this.indicadorReporte = Constants.REPORTE_SIC_GENERACION_PEDIDOS_DIGITADOS_ZONA;
		    	params.put("codigoRegion", StringUtils.substringBefore(temp,"|"));
		    	params.put("codigoZona", StringUtils.substringAfter(temp,"|"));
//			    	String condicionRegion = "grupo.COD_REGI in ('"+temp+"')";
		    	params.put("condicionRegion", "");
		    	String condicionZona = "and a.cod_zona in ('"+StringUtils.substringAfter(temp,"|")+"')";
		    	params.put("condicionZona", condicionZona);
		    }
		    params.put("indicadorReporte", this.indicadorReporte);
			
		}
		
		return params;
		
	}
	
	/**
	 * Retorna el periodo Anterior
	 * @param oidPeriodo
	 * @return
	 */
	private String getPeriodoAnterior(String oidPeriodo) {
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("oidPeriodo", oidPeriodo);
		Base basePeriodoFin =  clienteService.getPeriodoAnterior(criteriaPeriodo);
		String oidPeriodoFin = basePeriodoFin.getCodigo();
		return oidPeriodoFin;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReporteMailXLS = true;
		ReporteSICPedidosDigitadosZonaForm f = (ReporteSICPedidosDigitadosZonaForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) this.getBean("sisicc.interfazSiCCService");
		ReporteService reportService = (ReporteService) this.getBean("scsicc.reporteService");
		
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		//this.indicadorMarca = pais.getIndicadorPaisMarca();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());
		
		//Carga la lista de Regiones
		List listaRegiones = reportService.getListaGenerico("getRegionesByPais",criteria);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int i = 0;
		for(Object object : listaRegiones){
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo().toString());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}
		
		//Se setean los valores por default de los filtros
		f.setPeriodo(service.getPeriodoDefaultByPaisCanal(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#grabarReporte(javax.faces.event.ActionEvent)
	 */
	public void grabarReporte(ActionEvent actionEvent){
		log.debug("grabarReporte");
		ReporteSICPedidosDigitadosZonaForm f = (ReporteSICPedidosDigitadosZonaForm) this.formReporte;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("nombreReporte","reporteSICPedidosDigitadosZona");
		Map paramReporte = reporteService.getParametrosReporte(criteria);
		if(paramReporte == null){
			String msgWarning = this.getResourceMessage(
					"reporteSICPedidosDigitadosZonaForm.no.configuracion.envio.mail");
			this.addError("Error", msgWarning);
			return;
		}
		super.grabarReporte(actionEvent);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte()
	 */
	protected String errorKeyGenerarMultipleReporte() {
		String key = "reporteSICGenerarPedidosDigitadosZona.msg.envioMailPedidosDigitadosZona.vacio";		
		return key;		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {
		ReporteSICPedidosDigitadosZonaForm f = (ReporteSICPedidosDigitadosZonaForm) this.formReporte;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		boolean encontrotodos = false;
		this.codigoPerido = f.getPeriodo();
		this.listaRegiones = f.getRegionList();	
		log.debug("Lista de Regiones :"+listaRegiones.length);
		for (int i=0; i < this.listaRegiones.length; i++ ) {
			if ("".equals(this.listaRegiones[i])) {
				encontrotodos = true;
				break;
			}
		}
		
		/* En caso haya seleccionado Todos */
		if (encontrotodos) {
			
			LabelValue[] resultadoReg =  aSvc.getRegionesByPaisMarcaCanal(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
					 Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT);
			
			this.listaRegiones = new String[resultadoReg.length];
			log.debug("Lista de Regiones :"+resultadoReg.length);
			
			for (int i=0; i < resultadoReg.length; i++ ) {
				this.listaRegiones[i] = resultadoReg[i].getValue();
			}	
		}
		
		/*Se eliminan las regiones que no tienen datos*/
		Map criteria = new HashMap();
		
		criteria.put("codigoPeriodo",f.getPeriodo());
		
		List listaRAux = new ArrayList();
		int nContadorRegiones = 0;
		int indListaRegiones = 0;
		
		for (int i=0; i<this.listaRegiones.length; i++){
			criteria.put("codigoRegion", this.listaRegiones [i]);
			criteria.put("codigoZona", "");
			nContadorRegiones = Integer.parseInt(reporteService.getContadorListaRegionesZonas(criteria));

			if (nContadorRegiones > 0) {
				listaRAux.add(this.listaRegiones[i]);
				indListaRegiones++;
			}else{
				return -1;//indica al framework que debe enviar un mensaje de advertencia o error				
			}
		}
		
		String[] listaRegionesAux = new String[indListaRegiones];
		listaRegionesAux = (String [])listaRAux.toArray(listaRegionesAux);
		
		this.listaRegiones = listaRegionesAux;
		
		/* obteniendo lista de zonas */	
		LabelValue[] resultadoRegZona = aSvc.getZonasMultipleByPaisMarcaCanalRegion(f.getCodigoPais(),
					  Constants.CODIGO_MARCA_DEFAULT,
					  Constants.CODIGO_CANAL_DEFAULT,
					  this.listaRegiones,
					  "");

		this.listaZonas = new String[resultadoRegZona.length];
			
		for (int i=0; i < resultadoRegZona.length; i++ ) {
			this.listaZonas[i] = resultadoRegZona[i].getValue();
		}	

		/*Se eliminan las zonas que no tienen datos*/
		List listaZAux = new ArrayList();
		int nContadorZonas = 0;
		int indListaZonas = 0;
		
		for (int i=0; i<this.listaZonas.length; i++){
			criteria.put("codigoRegion", "");
			criteria.put("codigoZona",this.listaZonas [i]);
			nContadorZonas = Integer.parseInt(reporteService.getContadorListaRegionesZonas(criteria));

			if (nContadorZonas > 0) {
				listaZAux.add(this.listaZonas[i]);
				indListaZonas++;
			}
		}
		
		String[] listaZonasAux = new String[indListaZonas];
		
		listaZonasAux = (String [])listaZAux.toArray(listaZonasAux);
		this.listaZonas = listaZonasAux;
		
		//ya se obtuvo la listaRegiones y listZonas totalmente depuradas
			
		/* obteniendo lista total */
		int tamanno = this.listaZonas.length;//this.listaRegiones.length + this.listaZonas.length;
		this.listaTotal = new String[tamanno];
			
//		for (int j=0; j < this.listaRegiones.length; j++) {
//			this.listaTotal[j] = this.listaRegiones[j]; 
//		}
			
		String codRegionFake = "";//Se utiliza para concatenar a la lista de zonas
		for (int i=0, k=0; i < listaZonas.length; i++, k++) {
			this.listaTotal[k] = codRegionFake + "|" + this.listaZonas[i];
			log.debug("this.listaZonas[i] " + this.listaZonas[i]);
		}
				
		for (int i=0; i< listaTotal.length; i++) {		
			log.debug("this.listaTotal[i] " + this.listaTotal[i]);
		}
		return this.listaTotal.length;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String periodo = (String)parameterMap.get("codigoPeriodo");
		String codigoRegion = (String)parameterMap.get("codigoRegion");
		String regionZona = this.listaTotal[this.getNroReporteProcesando() - 1 ];
		log.debug("regionZona before "+regionZona );
		String [] split = StringUtils.split(regionZona, "|");
		if(split.length>1)
		  regionZona = split[1];
		else
		 regionZona = split[0];
		log.debug("regionZona after "+regionZona );
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		if(Constants.REPORTE_SIC_GENERACION_PEDIDOS_DIGITADOS_ZONA.equals(indicadorReporte)){
			regionZona=codigoRegion+ regionZona;
		}
		nombreArchivoReporte = regionZona + "_" + periodo + "_" + 
								sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#afterGrabarReporte()
	 */
	protected void afterGrabarReporte() {
		log.debug("afterGrabarReporte");
		ReporteSICPedidosDigitadosZonaForm f = (ReporteSICPedidosDigitadosZonaForm) this.formReporte;
		String key ="reporteINCGenerarReporteIncentivos.msg.envioMailCronograma";		
		if(f.isErrorEnvioEmail()){
			f.setMensajeErrorMail(this.messageErrorEmail);
			this.addError("Error", this.messageErrorEmail);
		}else{
		  	this.addInfo("Informacion", this.getResourceMessage(key));
		}
	}
	
	
	
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the listaRegiones
	 */
	public String[] getListaRegiones() {
		return listaRegiones;
	}

	/**
	 * @param listaRegiones the listaRegiones to set
	 */
	public void setListaRegiones(String[] listaRegiones) {
		this.listaRegiones = listaRegiones;
	}

	/**
	 * @return the listaZonas
	 */
	public String[] getListaZonas() {
		return listaZonas;
	}

	/**
	 * @param listaZonas the listaZonas to set
	 */
	public void setListaZonas(String[] listaZonas) {
		this.listaZonas = listaZonas;
	}

	/**
	 * @return the listaTotal
	 */
	public String[] getListaTotal() {
		return listaTotal;
	}

	/**
	 * @param listaTotal the listaTotal to set
	 */
	public void setListaTotal(String[] listaTotal) {
		this.listaTotal = listaTotal;
	}

	/**
	 * @return the codigoPerido
	 */
	public String getCodigoPerido() {
		return codigoPerido;
	}

	/**
	 * @param codigoPerido the codigoPerido to set
	 */
	public void setCodigoPerido(String codigoPerido) {
		this.codigoPerido = codigoPerido;
	}
	
	
}
