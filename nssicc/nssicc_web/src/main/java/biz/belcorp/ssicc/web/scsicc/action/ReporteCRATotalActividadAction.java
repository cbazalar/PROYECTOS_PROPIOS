package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAActividadService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCRATotalActividadForm;


/**
 * The Class ReporteCRATotalActividadAction.
 *
 * @autor: Jose Pulido
 * @version: 1.0
 * 25/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteCRATotalActividadAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -5705173860302681719L;
	private String formatoReporte;
	private List siccGrupoFacturacionList;
	private List siccActividadList;
	private List siccMarcaList;
	private List siccCanalList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCRATotalActividadForm form = new ReporteCRATotalActividadForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCRATotalActividadAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
	
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		 
        this.siccMarcaList = svc.getMarcas();
        this.siccCanalList = svc.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
        
        // Obteniendo las lista de grupode facturacion y actividad
        MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
       	Long oidMarca = clienteService.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
    	Long oidCanal = clienteService.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);
    	
    	Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
    	
    	ReporteCRATotalActividadForm reporteCRAForm = (ReporteCRATotalActividadForm) this.formReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anio = sdf.format(new Date(System.currentTimeMillis()));
		reporteCRAForm.setAnio(anio);
		reporteCRAForm.setCodigoPais(pais.getCodigo());        
    	
        Map params = new HashMap();
        params.put("codigoPais", pais.getCodigo());
    	params.put("oidPais", new Long(reporteService.getOidString("getOidPaisByCodigoPais", params)));				
    	params.put("oidMarca", oidMarca);
    	params.put("oidCanal", oidCanal);  
        	        
    	this.siccGrupoFacturacionList = reporteService.getGrupoFacturacion(params);
    	this.siccActividadList = reporteService.getActividad(params);
	
    	log.debug("Todo Ok: Redireccionando");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteCRATotalActividadZonaXLS";
		else
			return "reporteMaestroHorizontal"; 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String nombre="reporteCRATotalActividadZonaPDF";
		return nombre;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		
		MantenimientoCRAActividadService serviceAct = (MantenimientoCRAActividadService)getBean("spusicc.mantenimientoCRAActividadService");					
		
		ReporteCRATotalActividadForm reporteCRAForm = (ReporteCRATotalActividadForm) this.formReporte;
		formatoReporte = reporteCRAForm.getFormatoExportacion();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", reporteCRAForm.getCodigoPais());
		criteria.put("id", reporteCRAForm.getActividad());
		
		List list = new ArrayList();
		list = serviceAct.getActividades(criteria);
		Map datos = (Map) list.get(0);
		String descActi = "";
		
		if (list.size()>0) {								
			descActi =  (String)datos.get("codigo") +" "+ (String)datos.get("nombre");			
		}

		params.put("NroReporte", " ");
		params.put("titulo", getReportResourceMessage("reporteCRATotalActividadForm.titulo")+"\n"
				   +reporteCRAForm.getAnio()+ " - "+descActi);
		params.put("anhio", new Long(reporteCRAForm.getAnio()));
		params.put("oidActividad", new Long(reporteCRAForm.getActividad()));
		
		String condicionGrupo = obtieneCondicion(reporteCRAForm.getGrupoFacturacion(),"ccg.oid_cabe_grup_zona", "'");		
		params.put("condicionGrupo", condicionGrupo);						 		
		
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccGrupoFacturacionList
	 */
	public List getSiccGrupoFacturacionList() {
		return siccGrupoFacturacionList;
	}

	/**
	 * @param siccGrupoFacturacionList the siccGrupoFacturacionList to set
	 */
	public void setSiccGrupoFacturacionList(List siccGrupoFacturacionList) {
		this.siccGrupoFacturacionList = siccGrupoFacturacionList;
	}

	/**
	 * @return the siccActividadList
	 */
	public List getSiccActividadList() {
		return siccActividadList;
	}

	/**
	 * @param siccActividadList the siccActividadList to set
	 */
	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}
	
}