package biz.belcorp.ssicc.web.spusicc.ventas.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.ventas.ProcesoVENService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.scsicc.action.ReporteVENRegistroVentasIngresosAction;
import biz.belcorp.ssicc.web.spusicc.ventas.form.ProcesoVENRegistroVentasIngresosForm;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli </a>
 *                        
 */
@ManagedBean
@SessionScoped
public class ProcesoVENRegistroVentasIngresosAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = -4911291036370890763L;
	private String formatoExportacion;
	private String indicadorDeshabilita;
	private String tipoReporteAMostrar;
	private boolean mostrarReporteOTXT = true;
	private boolean mostrarReporteOCSV = true;
	
	@ManagedProperty(value = "#{reporteVENRegistroVentasIngresosAction}")
	private ReporteVENRegistroVentasIngresosAction reporteVENRegistroVentasIngresosAction;
	
	
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoVENRegistroVentasIngresosForm f = new ProcesoVENRegistroVentasIngresosForm();
		return f;
	}


	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais= this.mPantallaPrincipalBean.getCurrentCountry();
		
		ProcesoVENRegistroVentasIngresosForm f =(ProcesoVENRegistroVentasIngresosForm) this.formProceso;
		f.setCodigoPais(pais.getCodigo());
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoVENService service = (ProcesoVENService)getBean("spusicc.procesoVENService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		
		
		//obteniendo mes x default
		String mesDefault= getMesDefault();
		f.setCodigoPeriodoEnviar(mesDefault);
		f.setCodigoPeriodoInformar(mesDefault);	

     // Asignamos al codigo del periodo el valor por defecto
        Map criteria = new HashMap();
        criteria.put("codigoPais", f.getCodigoPais());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
        
        //recuperamos el oid Pais
        String oidPais = clienteService.getOidPais(criteria);
        criteria.put("oidPais", oidPais);		
       
		String indicadorHabilita= service.getIndicadorHabilitacionRuv(criteria);
		//si es cero hay  qdeshabitar componentes
		this.indicadorDeshabilita = Constants.NUMERO_UNO.equals(indicadorHabilita)?Constants.NUMERO_CERO:Constants.NUMERO_UNO;
		f.setAccion(Constants.NUMERO_CERO);
		
			
	}
	
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) {
			Usuario usuario = (Usuario) params.get("usuario");
			params.put("login", usuario.getLogin());
			Map map = new HashMap();
			map.put("codigoPais", params.get("codigoPais"));
			map.put("codigoPeriodoInformar", params.get("codigoPeriodoInformar"));
			map.put("codigoPeriodoEnviar", params.get("codigoPeriodoEnviar"));
			map.put("accion", params.get("accion"));
			map.put("tipoReporte", params.get("tipoReporte"));
			map.put("tipoReporteAMostrar", params.get("tipoReporteAMostrar"));
			
			map.put("usuario", params.get("login"));
			log.debug("Los parametros del Generar en el executeProcess son: "
					+ map.toString());
			
			ProcesoVENService service = (ProcesoVENService)getBean("spusicc.procesoVENService");		
			service.executeGeneracionReporteRUV(map);
			return params;
	}


	/**
	 * Retorna el mes x default, que es el mes actual -1
	 * 
	 * @return
	 */
	private String getMesDefault() {
		String mesDefault="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String mesAnho = sdf.format(new Date(System.currentTimeMillis()));
		String anho = mesAnho.substring(0, 4);
		if("01".equals(mesAnho.substring(4))){
			mesDefault=anho+"12";	
		   return mesDefault;
		} 
		Integer imes= new Integer(mesAnho.substring(4));
		imes = imes-1;
		mesDefault=anho+StringUtils.leftPad(""+imes, 2, "0");
		return mesDefault;
	}


	
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setValidarProceso()
	 */
	@Override
	public String setValidarProceso() {
		ProcesoVENRegistroVentasIngresosForm form =(ProcesoVENRegistroVentasIngresosForm) this.formProceso;
		String codigoPeriodoEnviar = form.getCodigoPeriodoEnviar();
		String codigoPeriodoInformar = form.getCodigoPeriodoInformar();
		
		
		Integer icodigoPeriodoEnviar = new Integer(codigoPeriodoEnviar);
		Integer icodigoPeriodoInformar = new Integer(codigoPeriodoInformar);
		
		if (icodigoPeriodoEnviar.intValue() > icodigoPeriodoInformar.intValue()) {
			String error = this.getResourceMessage("procesoVENRegistroVentasIngresosForm.valid.codigoPeriodoEnviar");
			return error;
     	}
		return null;
	}

	
	/**
	 * @param event
	 */
	public void verificarEjecucionReporteNoJASPER(ActionEvent event) {
		try {
			String lsMensajeError = this.setValidarProceso();
			if (StringUtils.isNotBlank(lsMensajeError)) {
				this.setMensajeAlertaDefault(lsMensajeError);
				this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
				return ;
			}
			this.getRequestContext().execute("PF('confirmationDialogGenerarReporteNoJASPER').show()");
			return;
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/**
	 * @param actionEvent
	 */
	public String executeReporteNoJASPER() {
		try {
			ProcesoVENRegistroVentasIngresosForm f =(ProcesoVENRegistroVentasIngresosForm) this.formProceso;
			this.reporteVENRegistroVentasIngresosAction.setFormatoExportacion(this.formatoExportacion);
			this.reporteVENRegistroVentasIngresosAction.setTipoReporte(this.tipoReporteAMostrar);
			this.reporteVENRegistroVentasIngresosAction.setCodigoPeriodoEnviar(f.getCodigoPeriodoEnviar());
			this.reporteVENRegistroVentasIngresosAction.setCodigoPeriodoInformar(f.getCodigoPeriodoInformar());
			this.reporteVENRegistroVentasIngresosAction.setearValores();
			this.reporteVENRegistroVentasIngresosAction.executeReporteNoJASPER();
			
		}
		catch(Exception e) {
			String error = this.obtieneMensajeErrorException(e);
			this.mPantallaPrincipalBean.setMensajeErrorSistema(error);
			return "_reporteConError";
		}
		return null;
	}

	
	
	
	/* GET - SET */
	
	/**
	 * @return the indicadorDeshabilita
	 */
	public String getIndicadorDeshabilita() {
		return indicadorDeshabilita;
	}


	/**
	 * @param indicadorDeshabilita the indicadorDeshabilita to set
	 */
	public void setIndicadorDeshabilita(String indicadorDeshabilita) {
		this.indicadorDeshabilita = indicadorDeshabilita;
	}


	/**
	 * @return the reporteVENRegistroVentasIngresosAction
	 */
	public ReporteVENRegistroVentasIngresosAction getReporteVENRegistroVentasIngresosAction() {
		return reporteVENRegistroVentasIngresosAction;
	}


	/**
	 * @param reporteVENRegistroVentasIngresosAction the reporteVENRegistroVentasIngresosAction to set
	 */
	public void setReporteVENRegistroVentasIngresosAction(
			ReporteVENRegistroVentasIngresosAction reporteVENRegistroVentasIngresosAction) {
		this.reporteVENRegistroVentasIngresosAction = reporteVENRegistroVentasIngresosAction;
	}


	/**
	 * @return the formatoExportacion
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}


	/**
	 * @param formatoExportacion the formatoExportacion to set
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}


	/**
	 * @return the tipoReporteAMostrar
	 */
	public String getTipoReporteAMostrar() {
		return tipoReporteAMostrar;
	}


	/**
	 * @param tipoReporteAMostrar the tipoReporteAMostrar to set
	 */
	public void setTipoReporteAMostrar(String tipoReporteAMostrar) {
		this.tipoReporteAMostrar = tipoReporteAMostrar;
	}


	/**
	 * @return the mostrarReporteOTXT
	 */
	public boolean isMostrarReporteOTXT() {
		return mostrarReporteOTXT;
	}


	/**
	 * @param mostrarReporteOTXT the mostrarReporteOTXT to set
	 */
	public void setMostrarReporteOTXT(boolean mostrarReporteOTXT) {
		this.mostrarReporteOTXT = mostrarReporteOTXT;
	}


	/**
	 * @return the mostrarReporteOCSV
	 */
	public boolean isMostrarReporteOCSV() {
		return mostrarReporteOCSV;
	}


	/**
	 * @param mostrarReporteOCSV the mostrarReporteOCSV to set
	 */
	public void setMostrarReporteOCSV(boolean mostrarReporteOCSV) {
		this.mostrarReporteOCSV = mostrarReporteOCSV;
	}


	
}