package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECOperacionesPedidosFacturadosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECOperacionesPedidosFacturadosAction extends BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private String formatoReporte;

	private String tipoReporte;
	private LabelValue[] siccTerritorioList = {};
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private String codigoIdiomaISO;
	private String codigoPeriodo;
	private List siccPresentacionList;
	private String periodoActual = null;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		this.mostrarReporteXLS = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		this.codigoIdiomaISO =  this.mPantallaPrincipalBean.getOidIdiomaIso();
		
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		
		ArrayList resultado = new ArrayList();
		Base[] presentacion = new Base[3];
		String[] presentaciones = {
				getResourceMessage("reporteRECOperacionesPedidosFacturadosForm.region"),
				getResourceMessage("reporteRECOperacionesPedidosFacturadosForm.zona"),
				getResourceMessage("reporteRECOperacionesPedidosFacturadosForm.territorio")};
		for (int i = 0; i < 3; i++) {
			presentacion[i] = new Base();
			presentacion[i].setCodigo(presentaciones[i].substring(0, 1));
			presentacion[i].setDescripcion(presentaciones[i]);
			resultado.add(presentacion[i]);
		}		
		this.siccPresentacionList = resultado;
		
	   ReporteRECOperacionesPedidosFacturadosForm reporteRECForm = (ReporteRECOperacionesPedidosFacturadosForm) this.formReporte;
	
	   this.codigoPeriodo = interfazSiCCService.getPeriodoDefaultByPaisCanal(pais.getCodigo(),Constants.CODIGO_CANAL_DEFAULT);
	  
	   reporteRECForm.setCodigoPeriodoInicial(codigoPeriodo);
	   reporteRECForm.setCodigoPeriodoFinal(codigoPeriodo);
	   reporteRECForm.setCodigoPais(pais.getCodigo());
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		this.periodoActual= this.mPantallaPrincipalBean.getAnyoActualperiodo();
		reporteRECForm.setCodigoPeriodoInicial(periodoActual);
		if (StringUtils.isEmpty(reporteRECForm.getCodigoPeriodoInicial()));
			reporteRECForm.setCodigoPeriodoInicial(periodo);
		reporteRECForm.setCodigoPeriodoFinal(periodoActual);
		if (StringUtils.isEmpty(reporteRECForm.getCodigoPeriodoFinal()));
			reporteRECForm.setCodigoPeriodoFinal(periodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteRECOperacionesPedidosFacturados" + tipoReporte
					+ "XLS";
		else
			return "reporteMaestroVertical";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECOperacionesPedidosFacturadosForm form = (ReporteRECOperacionesPedidosFacturadosForm)this.formReporte;
		Integer fecha1,fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (fecha1 > fecha2){
			String mensaje =  "El Periodo Inicial debe ser mayor o igual al Periodo Final";
			return mensaje;
		}
		//siccRegionList = new ArrayList<String>();    					
	    return null;
	}	
	
	/**
	 * Obtener Lista de Zonas
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		try {
			ReporteRECOperacionesPedidosFacturadosForm form = (ReporteRECOperacionesPedidosFacturadosForm) this.formReporte;
			String[] regiones = (String [])val.getNewValue();
			if(!val.equals(null) && regiones.length > 0 ){	
//			List<String> strings = 
//				     new ArrayList<String>(Arrays.asList(regiones));
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
			form.setZonaList(null);
			}else {
				this.siccZonaList= null;
				form.setZonaList(null);	
				this.siccTerritorioList = null;
				form.setTerritorioList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * @param val
	 * Obtener lista de territorios por zona
	 */
	public void showTerritorioxZona(ValueChangeEvent val){
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteRECOperacionesPedidosFacturadosForm form = (ReporteRECOperacionesPedidosFacturadosForm) this.formReporte;
			
			String[] regiones = (String [])form.getRegionList();
			
			String[] zonas = (String [])val.getNewValue();
			if(!val.equals(null) && zonas.length > 0 ){	
			
			List<String> listaRegiones = 
				     new ArrayList<String>(Arrays.asList(regiones));
			
			List<String> listaZonas = 
				     new ArrayList<String>(Arrays.asList(zonas));	
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccTerritorioList(aSvc.getTerritoriosMultipleByPaisMarcaCanalRegionZona(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, listaRegiones, listaZonas, Constants.FORMATO_TOTAL));

			form.setTerritorioList(null);
			}else {
				this.siccTerritorioList = null;
				form.setTerritorioList(null);		
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @return
	 */
	protected String getSubReporteFileName() {
		return "reporteRECOperacionesPedidosFacturados" + tipoReporte + "PDF";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteRECOperacionesPedidosFacturadosForm reporteRECForm = (ReporteRECOperacionesPedidosFacturadosForm) this.formReporte;
		this.formatoReporte = reporteRECForm.getFormatoExportacion();
		this.tipoReporte = reporteRECForm.getTipoReporte();

		String condicionTerritorio = obtieneCondicion(reporteRECForm
				.getTerritorioList(), "Cod_Terr", "'");
		String condicionZonas = obtieneCondicion(reporteRECForm.getZonaList(),
				"COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(reporteRECForm
				.getRegionList(), "COD_REGI", "'");
		String condicionUbigeo = condicionRegion + condicionZonas
				+ condicionTerritorio;

		params.put("condicion", condicionUbigeo);
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteria = params;
		criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoInicial());
		String oidPeriodoInicial = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodoInicial", oidPeriodoInicial);
		criteria.put("codigoPeriodo", reporteRECForm.getCodigoPeriodoFinal());
		String oidPeriodoFinal = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodoFinal", oidPeriodoFinal);
		params.put("codigoPais", "'" + reporteRECForm.getCodigoPais() + "'");
		params.put("NroReporte", getReportResourceMessage(
				"reporteRECOperacionesPedidosFacturadosForm.numero.reporte"));
		params.put("titulo", getReportResourceMessage(
				"reporteRECOperacionesPedidosFacturadosForm.titulo"));
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECOperacionesPedidosFacturadosForm form = new ReporteRECOperacionesPedidosFacturadosForm();
		return form;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteRECOperacionesPedidosFacturados" + tipoReporte + "PDF";
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
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the codigoIdiomaISO
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO the codigoIdiomaISO to set
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the siccPresentacionList
	 */
	public List getSiccPresentacionList() {
		return siccPresentacionList;
	}

	/**
	 * @param siccPresentacionList the siccPresentacionList to set
	 */
	public void setSiccPresentacionList(List siccPresentacionList) {
		this.siccPresentacionList = siccPresentacionList;
	}
}