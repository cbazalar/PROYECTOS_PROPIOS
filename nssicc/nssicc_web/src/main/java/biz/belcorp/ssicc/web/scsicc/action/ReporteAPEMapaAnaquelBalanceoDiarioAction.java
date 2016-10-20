package biz.belcorp.ssicc.web.scsicc.action;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEAsignarCajaEmbalajeLineaService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEOrdenAnaquelesService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPESubLineaArmadoService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPETiposAnaquelesMapaCDService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEUnidadesAdministrativasLineaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPEMapaAnaquelBalanceoDiarioForm;


/**
 * @author <a href="mailto:nlopez@csigcomt.com">Nicolás López</a>
 */
@ManagedBean
@SessionScoped
public class ReporteAPEMapaAnaquelBalanceoDiarioAction extends BaseReporteAbstractAction implements Serializable {
			
	private static final long serialVersionUID = 7496906172990857522L;

	private List siccCentroDList;
	private List siccMapaList;
	private List siccLineaList;
	//private List siccTipoSolList;
	private List siccMarcaList;
	private List siccCanalList;
	
	private String secuencia;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
	    return new ReporteAPEMapaAnaquelBalanceoDiarioForm();
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteAPEMapaAnaquelBalanceoDiarioForm form = (ReporteAPEMapaAnaquelBalanceoDiarioForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return "reporteAPEMapaAnaquelBalDiarioXLS";
		else
		   return "reporteMaestroHorizontal";
	   
	
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteAPEMapaAnaquelBalanceoDiarioForm form = (ReporteAPEMapaAnaquelBalanceoDiarioForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion()))
			return null;
		else
		   return "reporteAPEMapaAnaquelBalDiarioPDF";

	}
	
	public void showLineYMapa(ValueChangeEvent val){
		log.debug(">>showLineYMapa ");
		log.debug("val: "+ ArrayUtils.toString(val.getNewValue()));
		ReporteAPEMapaAnaquelBalanceoDiarioForm form = (ReporteAPEMapaAnaquelBalanceoDiarioForm) this.formReporte;
		String centro = (String) val.getNewValue();		
	      
		if(StringUtils.isNotBlank(centro)){
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			LabelValue[] lv = null;
			Base b = null;
			
			lv = aSvc.getLinea(centro);
			siccLineaList = new  ArrayList();
			if (ArrayUtils.isNotEmpty(lv)) {
				for (LabelValue labelValue : lv) {
					if (StringUtils.isNotBlank(labelValue.getLabel()) && 
							StringUtils.isNotBlank(labelValue.getValue())){
						b = new Base();
						b.setCodigo(labelValue.getValue());
						b.setDescripcion(labelValue.getLabel());
						siccLineaList.add(b);
					}
				}
			}
			
			lv = aSvc.getMapaByCentro(centro);
			siccMapaList = new ArrayList();
			if (ArrayUtils.isNotEmpty(lv)) {
				for (LabelValue labelValue : lv) {
					if (StringUtils.isNotBlank(labelValue.getLabel()) && 
							StringUtils.isNotBlank(labelValue.getValue())){
					
						b = new Base();
						b.setCodigo(labelValue.getValue());
						b.setDescripcion(labelValue.getLabel());
						siccMapaList.add(b);
					}
				}
			}
			
		}else{
			setSiccLineaList(null);
			setSiccMapaList(null);
		}
		
		form.setCodigoLinea(null);
		form.setMapa(null);
	}

	
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoAPEUnidadesAdministrativasLineaService serviceUA = 
			(MantenimientoAPEUnidadesAdministrativasLineaService)this.getBean("spusicc.mantenimientoAPEUnidadesAdministrativasLineaService");
		MantenimientoAPEOrdenAnaquelesService serviceOA =
			(MantenimientoAPEOrdenAnaquelesService)this.getBean("spusicc.mantenimientoAPEOrdenAnaquelesService");
		MantenimientoAPETiposAnaquelesMapaCDService serviceTA =
			(MantenimientoAPETiposAnaquelesMapaCDService)this.getBean("spusicc.mantenimientoAPETiposAnaquelesMapaCDService");
		MantenimientoAPESubLineaArmadoService serviceSL = 
			(MantenimientoAPESubLineaArmadoService)this.getBean("spusicc.mantenimientoAPESubLineaArmadoService");
		MantenimientoAPEAsignarCajaEmbalajeLineaService serviceCE = 
			(MantenimientoAPEAsignarCajaEmbalajeLineaService)this.getBean("spusicc.mantenimientoAPEAsignarCajaEmbalajeLineaService");
		
		ReporteAPEMapaAnaquelBalanceoDiarioForm reporteAPEForm = (ReporteAPEMapaAnaquelBalanceoDiarioForm) this.formReporte;

		String OidCentroDistribucion = "";
		String descripcionCentroDistribucion = "";
		String OidMapaCentroDistribucion = "";
		String descripcionMapaCentroDistribucion = "";
		String OidLineaArmado = "";
		String descripcionLineaArmado = "";
		
		
		Map criteriaPais = new HashMap();
		criteriaPais.put("codigoPais", reporteAPEForm.getCodigoPais());
		
		
		params.put("NroReporte", " ");
		params.put("titulo", getResourceMessage("reporteAPEMapaAnaquelBalanceoDiarioForm.titulo"));
		
		// Obtenemos el correlativo para la generación de la tabla temporal
		
		secuencia = reporteService.getObtenerSecuenciaTempMapaAnaquel(params);

		System.out.println("La Sucuencia para la tabla temporal de Reporte es ----> :"+secuencia);
		

		String codigoPais =  reporteAPEForm.getCodigoPais();
	    String marca = reporteAPEForm.getCodigoMarca();
        String canal = reporteAPEForm.getCodigoCanal();
        String periodo = reporteAPEForm.getCodigoPeriodo();
        String mapacd = reporteAPEForm.getMapa();
        String linea = reporteAPEForm.getCodigoLinea();
        Date fecha   = reporteAPEForm.getFechaFacturacion();
        
        params.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteriaPais));
        params.put("codCentro",reporteAPEForm.getCodigoCentro());
		OidCentroDistribucion = serviceUA.getOidCentroDistribucionPais(params);
		params.put("oidCentro", OidCentroDistribucion);
		params.put("nombreTablaCentroDistribucion", "APP_CONFI_CENTR_DISTR");
		descripcionCentroDistribucion = serviceOA.getDescripcionCentroDistribucion(params);
		
		params.put("codMapCentrDist", mapacd);
		OidMapaCentroDistribucion = serviceTA.getOidMapaCentroDistribucion(params);
		
		params.put("oidMapaCentro", OidMapaCentroDistribucion);
		params.put("nombreTablaMapaCentroDistribucion", "APE_MAPA_CENTR_DISTR_CABEC");
		descripcionMapaCentroDistribucion = serviceOA.getDescripcionMapaCentroDistribucion(params);
		
		params.put("codLinea", linea);
		String oIdLineaArmado = serviceSL.getOidLineaArmadobyCodigo(params);
		params.put("oidLinea", oIdLineaArmado);
		params.put("nombreTablaLinea", "APE_LINEA_ARMAD");
		params.put("oidIdiomaIso", "1");
		descripcionLineaArmado = serviceCE.getDescripcionLineabyOid(params);
        
		params.put("codigoPais", codigoPais);	
		params.put("codigoMarca", marca);
		params.put("codigoCanal", canal);
		params.put("codigoPeriodo", periodo);
		params.put("codigoSecuencia", secuencia);

		params.put("codigoMapaCD", mapacd);
		params.put("codigoLineaArmado", linea);
		params.put("fechaFacturacion", fecha == null ? StringUtils.EMPTY : DateFormatUtils.format(fecha, "dd/MM/yyyy"));
		
		params.put("descCentroDistribucion", descripcionCentroDistribucion);
		params.put("descMapaCentroDistribucion", descripcionMapaCentroDistribucion);
		params.put("descLineaArmado", descripcionLineaArmado);
		
		// Se envian los parametros para la creación de la tabla temporal
		reporteService.executeGenerarReporteMapaAnaquelBalanceoDiario(params);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.info("Entro a setViewAttributes");

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		
		ReporteAPEMapaAnaquelBalanceoDiarioForm form = (ReporteAPEMapaAnaquelBalanceoDiarioForm) this.formReporte;
		
		// parametros generales
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
		
		String codigoPeriodoActual = this.getmPantallaPrincipalBean().getCodigoPeriodoActual();

		if (StringUtils.isBlank(codigoPeriodoActual)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String periodo = sdf.format(new Date(System.currentTimeMillis()));
			codigoPeriodoActual = periodo;
		}
		form.setCodigoPais(pais.getCodigo());
		
		form.setCodigoPeriodo(codigoPeriodoActual);
		form.setFechaFacturacion(new Date());
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		
		// Variables
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
	    
		// Crear Pojo
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		

		siccCentroDList = service.getCentroDistribucionByPais(criteria);
		        // Lógica de Negocio

		siccLineaList = service.getLinea(criteria);
		siccMapaList = service.getMapaByCentro(criteria);
		//siccTipoSolList =  service.getSublineaxLinea(criteria);

		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoPeriodo", Constants.SICC_PERIODO_INICIAL_LIST);

		siccMarcaList = service.getMarcas();
		siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());

	}

	/**
	 * @return the siccCentroDList
	 */
	public List getSiccCentroDList() {
		return siccCentroDList;
	}

	/**
	 * @param siccCentroDList the siccCentroDList to set
	 */
	public void setSiccCentroDList(List siccCentroDList) {
		this.siccCentroDList = siccCentroDList;
	}

	/**
	 * @return the siccMapaList
	 */
	public List getSiccMapaList() {
		return siccMapaList;
	}

	/**
	 * @param siccMapaList the siccMapaList to set
	 */
	public void setSiccMapaList(List siccMapaList) {
		this.siccMapaList = siccMapaList;
	}

	/**
	 * @return the siccLineaList
	 */
	public List getSiccLineaList() {
		return siccLineaList;
	}

	/**
	 * @param siccLineaList the siccLineaList to set
	 */
	public void setSiccLineaList(List siccLineaList) {
		this.siccLineaList = siccLineaList;
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

	/**
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * @param secuencia the secuencia to set
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	

	
}