package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECConsolidadoCDRSForm;

@ManagedBean
@SessionScoped
public class ReporteRECConsolidadoCDRSAction extends BaseReporteAbstractAction
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7054679091584808093L;
	private String formatoReporte;
	private String tipoReporte;
	private List siccOperacionesList;
	private LabelValue[] siccTipoOperacionList= {};
	private ArrayList siccPresentacionList;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private List siccTipoMovimiento;
	private List siccEstado;
	private List siccMarcaList;

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
//		################# LISTA TIPO CONSULTA #####################
		 String[] presentaciones = {
		 "Por Tipo CDR",
		 "Por Consultora"};
		 ArrayList resultado = new ArrayList();
		 Base[] base = new Base[presentaciones.length];
		 for (int i = 0; i < presentaciones.length; i++) {
		 base[i] = new Base();
		 base[i].setCodigo("" + (i + 1));
		 base[i].setDescripcion(presentaciones[i]);
		 resultado.add(base[i]);
		 }
		 this.siccPresentacionList = resultado;
		 
//		 ################## LISTA OPERACION #####################
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

			Map criteriaOperacion = new HashMap();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			criteriaOperacion.put("codigoPais", pais.getCodigo());
			this.siccOperacionesList = interfazSiCCService
					.getOperacionesByCodigoPais(criteriaOperacion);
			
//		################### LISTA Tipo Movimiento ################
			this.siccTipoMovimiento =  reporteService.getListaGenerico("getTipoMovimiento",criteriaOperacion);
			
			
//	    ################### LISTA ESTADO ################
			this.siccEstado =  reporteService.getListaGenerico("getEstado",criteriaOperacion);
			
			
//		################### LISTA MARCA ################
			this.siccMarcaList =  reporteService.getListaGenerico("getMarcaProdu",criteriaOperacion);
						 
			
//		################### LISTA REGION ################
			this.siccRegionList =  reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
	

	
	
		// request.getSession().setAttribute(
		// Constants.SICC_UNIDAD_NEGOCIO_LIST,
		// reporteService.getListaGenerico("getListaUnidadNegocio",
		// criteriaOperacion));
		// request.getSession().setAttribute(
		// Constants.SICC_ESTADO_OPERACION_LIST,
		// reporteService.getListaGenerico("getListaEstadoOperacion",
		// criteriaOperacion));
		// request.getSession().setAttribute(
		// Constants.SICC_ESTADO_RECLAMO_LIST,
		// reporteService.getListaGenerico("getListaEstadoReclamo",
		// criteriaOperacion));
		// request.getSession().setAttribute(
		// Constants.SICC_NEGOCIO_LIST,
		// reporteService.getListaGenerico("getListaNegocio",
		// criteriaOperacion));
	

		
		// request.getSession().setAttribute(Constants.SICC_TERRITORIO_LIST,
		// new ArrayList());
		// request.getSession().setAttribute("codigoIdiomaISO",
		// getUsuario(request.getSession()).getIdioma().getCodigoISO());

	

		
	
		

	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECConsolidadoCDRSForm form = new ReporteRECConsolidadoCDRSForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECConsolidadoCDRSForm form = (ReporteRECConsolidadoCDRSForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion())) {
			if ("1".equals(tipoReporte)) {
				return "reporteRECConsolidadoCDRTipoCDR";
			} else if ("2".equals(tipoReporte)) {
				return "reporteRECConsolidadoCDRConsultora";
			}

		}
		return "";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		
		ReporteRECConsolidadoCDRSForm reporteRECForm = (ReporteRECConsolidadoCDRSForm) this.formReporte;
		formatoReporte = reporteRECForm.getFormatoExportacion();
		tipoReporte = reporteRECForm.getTipoConsulta();
		params.put("CodigoPais", reporteRECForm.getCodigoPais());
		params.put("codigoPeriodo", reporteRECForm.getCodigoPeriodo());
		
		String varNullFec = "";
		
		String fechaPro="";
		if(reporteRECForm.getFechaProceso() != null ){
			fechaPro=DateUtil.convertDateToString(reporteRECForm.getFechaProcesoD());
		}
		String preCondicionFechaProceso = fechaPro == null || fechaPro.equals("") ? varNullFec : 
			"AND REC_CABEC_RECLA.FEC_INGR = to_date("+"'"+ fechaPro+"','DD/MM/YYYY'"+")";
		params.put("fechaProceso", preCondicionFechaProceso);
		//params.put("tipoConsulta",reporteRECForm.getTipoConsulta());

		String[] varNull = null;
		String[] preCondicion = reporteRECForm.getTipoOperacionList() == null || 
				 StringUtils.equals(StringUtils.substring(reporteRECForm.
				 getDescripcionTipoOperacionList(),0, 5),"Todos") ? varNull : 
					 reporteRECForm.getTipoOperacionList();
		String tipoOperacionList = this.obtieneCondicion(preCondicion, "REC_OPERA.COD_OPER||'-'||REC_TIPOS_OPERA.VAL_TIPO_OPER", "'");
		params.put("tipoOperacionList",	tipoOperacionList);
	
		params.put("tipoMovimiento",reporteRECForm.getTipoMovimiento());

		String condicionEstado = this.obtieneCondicion(reporteRECForm.getEstado(), "REC_ESTAD_RECLA.COD_ESTA", "'");
		params.put("estado",condicionEstado);
		
		String[] preCondicionMarca = reporteRECForm.getTipoOperacionList() == null || 
		 StringUtils.equals(StringUtils.substring(reporteRECForm.
				 getDescripcionMarcaList(),0, 5),"Todos") ? varNull : 
			 reporteRECForm.getMarcaList();
		String marcaList = this.obtieneCondicionIN(preCondicionMarca, "", "'");
		if(marcaList==null || marcaList.equals("")){
			marcaList = "LIKE '%' ";
		}
		params.put("marcaList",	marcaList);

		String condicionRegion = this.obtieneCondicion(reporteRECForm.getRegionList(), "ZON_REGIO.COD_REGI", "'");

		String[] varNullZona = null;
		String[] preCondicionZona = reporteRECForm.getZonaList() == null || 
		 StringUtils.equals(StringUtils.substring(reporteRECForm.getDescripcionZonaList(),0, 5),"Todos")
		 ? varNullZona : reporteRECForm.getZonaList();

		String condicionZona = this.obtieneCondicion(preCondicionZona, "ZON_ZONA.COD_ZONA", "'");
		log.debug("condicionZonaXX : " + condicionZona);
		params.put("zonaList",condicionZona);
		params.put("regionList",condicionRegion);

		log.debug("tipoReportexx : " + tipoReporte);

		if ("1".equals(tipoReporte))
		params.put("titulo", getResourceMessage("reporteRECConsolidadoCDRTipoCDR.titulo"));		
		else if ("2".equals(tipoReporte))
		params.put("titulo", getResourceMessage("reporteRECConsolidadoCDRConsultora.titulo"));		
		
		log.debug("Parametros Cargados");
		return params;
	}
	
	
	/**
	 * Metodo para obtener Lista de Tipo Operaciones
	 * @param val
	 */
	public void loadTipoOperaciones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoOperaciones");
		}

		String[] valor = (String[]) val.getNewValue();
		ArrayList valorArray = new ArrayList<String>(Arrays.asList(valor));

		if (valorArray.size()>0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			setSiccTipoOperacionList(ajaxService
					.getTiposOperaByOperaDesList(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(),valorArray,
							Constants.OPCION_TODOS));
		}else{
			setSiccTipoOperacionList(null);
		}
	}
	
	/**
	 * Metodo para obtener Lista de Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}

		String[] valor = (String[]) val.getNewValue();

		if (valor.length>0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			setSiccZonaList(ajaxService
					.getZonasMultipleByPaisMarcaCanalRegion(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor,
							Constants.OPCION_TODOS));
		}else{
			setSiccTipoOperacionList(null);
		}
	}

	
	
	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 *            the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the siccTipoOperacionList
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList
	 *            the siccTipoOperacionList to set
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

	/**
	 * @return the siccPresentacionList
	 */
	public ArrayList getSiccPresentacionList() {
		return siccPresentacionList;
	}

	/**
	 * @param siccPresentacionList the siccPresentacionList to set
	 */
	public void setSiccPresentacionList(ArrayList siccPresentacionList) {
		this.siccPresentacionList = siccPresentacionList;
	}

	/**
	 * @return the siccTipoMovimiento
	 */
	public List getSiccTipoMovimiento() {
		return siccTipoMovimiento;
	}

	/**
	 * @param siccTipoMovimiento the siccTipoMovimiento to set
	 */
	public void setSiccTipoMovimiento(List siccTipoMovimiento) {
		this.siccTipoMovimiento = siccTipoMovimiento;
	}

	/**
	 * @return the siccEstado
	 */
	public List getSiccEstado() {
		return siccEstado;
	}

	/**
	 * @param siccEstado the siccEstado to set
	 */
	public void setSiccEstado(List siccEstado) {
		this.siccEstado = siccEstado;
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
	
	
	
	
	
	
	
	

}
