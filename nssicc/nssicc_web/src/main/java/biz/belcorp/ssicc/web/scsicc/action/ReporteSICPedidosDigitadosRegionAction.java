package biz.belcorp.ssicc.web.scsicc.action;

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
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICPedidosDigitadosRegionForm;

/**
 * The Class ReporteSICPedidosDigitadosRegionAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 02/06/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICPedidosDigitadosRegionAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private LabelValue[] siccRegionList = {};
	
	private String   indicadorReporte;
	private String[] listaRegiones;
	private String[] listaZonas;
	private String[] listaTotal;
	private String codigoPerido;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICPedidosDigitadosRegionForm form = new ReporteSICPedidosDigitadosRegionForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICPedidosDigitadosRegionForm form = (ReporteSICPedidosDigitadosRegionForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(this.formReporte.getFormatoExportacion())){
			return "reporteMaestroHorizontal";
		}else{
			return "reporteSICPedidosDigitadosRegionXLS";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICPedidosDigitadosRegionForm form = (ReporteSICPedidosDigitadosRegionForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		String subReporte = null;
		if ("PDF".equals(this.formReporte.getFormatoExportacion()) ){
			subReporte ="reporteSICPedidosDigitadosRegionPDF";
		}else{
			subReporte = "reporteSICPedidosDigitadosRegionXLS";
		}
		return subReporte;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICPedidosDigitadosRegionForm f = (ReporteSICPedidosDigitadosRegionForm) this.formReporte;	
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		Map criteria = params;
		
		//Parametros estandar que se envían a los reportes
		params.put("NroReporte", "");
		params.put("titulo",getResourceMessage("reporteSICPedidosDigitadosRegion.titulo"));
		params.put("formatoExportacion",f.getFormatoExportacion());
		
		//Parametros del reporte
		criteria.put("codigoPeriodo", f.getPeriodo());
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPerio", new Long(oidPeriodo));
		criteria.put("codPeriodo", f.getPeriodo());
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "A.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		
		if (this.formReporte.isEnvioEmail()) {
			String temp = this.listaTotal[this.getNroReporteProcesando() - 1 ];
		    int pos = StringUtils.indexOf(temp, '|');
		  
	    	this.indicadorReporte = Constants.REPORTE_SIC_GENERACION_PEDIDOS_DIGITADOS_REGION;
	    	params.put("codigoRegion", StringUtils.substringBefore(temp,"|"));
	    	params.put("codigoZona", null);	
	    	String condicionRegion2 = "and a.COD_REGI in ('"+temp+"')";
	    	params.put("condicionRegion", condicionRegion2);
	    	params.put("condicionZona", "");

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
		this.mostrarExcelXLSX = false;
//		this.mostrarReporteXLS97 = true;
		this.mostrarReporteXLS = true;
		this.mostrarReporteMailXLS = true;
		ReporteSICPedidosDigitadosRegionForm f = (ReporteSICPedidosDigitadosRegionForm) this.formReporte;
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
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte(){
		if (this.ejecucionReporteMail) {
			ReporteSICPedidosDigitadosRegionForm f = (ReporteSICPedidosDigitadosRegionForm) this.formReporte;
			ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("nombreReporte","reporteSICPedidosDigitadosRegion");
			
			/// Envia parametro Cod Zona
			// Enviar correos
			//params
			Map paramReporte = reporteService.getParametrosReporte(criteria);
			if(paramReporte == null){
				String msgWarning = this.getResourceMessage("reporteSICPedidosDigitadosRegionForm.no.configuracion.envio.mail");
				return msgWarning;
			}
			
		}
		return "";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService(){
		String service = "sic.mailReporteGenerarPedidosDigitadosZonaService";		
		return service;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#errorKeyGenerarMultipleReporte()
	 */
	protected String errorKeyGenerarMultipleReporte() {
		String key = "reporteSICGenerarPedidosDigitadosRegion.msg.envioMailPedidosDigitadosRegion.vacio";		
		return key;		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNroReportesAGenerar()
	 */
	protected int getNroReportesAGenerar() {
		ReporteSICPedidosDigitadosRegionForm f = (ReporteSICPedidosDigitadosRegionForm) this.formReporte;
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
			
			LabelValue[] resultadoReg =  aSvc.getRegionesByPaisMarcaCanal(
					this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), 
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
		
		//ya se obtuvo la listaRegiones depuradas
			
		/* obteniendo lista total */
			//this.listaRegiones.length + this.listaZonas.length;
		int tamanno = this.listaRegiones.length;  // this.listaZonas.length;
		this.listaTotal = new String[tamanno];
			
		for (int j=0; j < this.listaRegiones.length; j++) {
			this.listaTotal[j] = this.listaRegiones[j]; 
		}
			
		String codRegionFake = "";//Se utiliza para concatenar a la lista de zonas
		
		for (int i=0; i< listaTotal.length; i++) {		
			log.debug("this.listaTotal[i] " + this.listaTotal[i]);
		}
		return this.listaTotal.length;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getNombreArchivoReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams)
	 */
	protected String getNombreArchivoReporte(ReporteParams reporteParams) {
		ReporteSICPedidosDigitadosRegionForm form = (ReporteSICPedidosDigitadosRegionForm)this.formReporte;
		if (!form.isEnvioEmail()) {
			String reporte = super.getNombreArchivoReporte(reporteParams);
			return reporte;
		}
		
		String nombreArchivoReporte;
		Map parameterMap =(Map) (reporteParams.getQueryParams()).get("parameterMap");
		String periodo = (String)parameterMap.get("codigoPeriodo");
		String codigoRegion = (String)parameterMap.get("codigoRegion");
		String regionZona = this.listaTotal[this.getNroReporteProcesando() - 1 ];
		log.debug("regionZona before---->"+regionZona );
		String [] split = StringUtils.split(regionZona, "|");
		if(split.length>1)
		  regionZona = split[1];
		else
		 regionZona = split[0];
		log.debug("regionZona after "+regionZona );
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		
		if (Constants.REPORTE_SIC_GENERACION_PEDIDOS_DIGITADOS_REGION.equals(indicadorReporte)){
			regionZona=codigoRegion;
		}
		
		nombreArchivoReporte = regionZona + "_" + periodo + "_" + 
								sdf.format(new Date(System.currentTimeMillis()));
		return nombreArchivoReporte;
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#afterGrabarReporte()
	 */
	protected void afterGrabarReporte() {
		super.afterGrabarReporte();
		log.debug("afterGrabarReporte");
		ReporteSICPedidosDigitadosRegionForm f = (ReporteSICPedidosDigitadosRegionForm) this.formReporte;
		String key ="reporteINCGenerarReporteIncentivos.msg.envioMailCronograma";		
		if(f.isErrorEnvioEmail()){
			f.setMensajeErrorMail(this.messageErrorEmail);
			this.addError("Error", this.messageErrorEmail);
		}else{
		  	this.addInfo("Informacion", this.getResourceMessage(key));
		}
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
	
	
}
