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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECReclaIncePendForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECReclaIncePendAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	private List siccOperacionesList = new ArrayList();
	private LabelValue[] siccTerritorioList = {};
	private String codigoIdiomaISO;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private Boolean habilitarOperacion = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECReclaIncePendForm form = new ReporteRECReclaIncePendForm();
		return form;
	}

//	/**
//	 * @param form
//	 * @return
//	 */
//	public String setValidarReporte(ReporteRECReclaIncePendForm form) {
//		if (form.getFechaFinIngreso().compareTo(form.getFechaInicioIngreso()) < 0)
//			return "Usted debe de ingresar una fecha diferente";
//		return null;
//	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		this.mostrarReporteXLS = true;
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);

		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);

		this.codigoIdiomaISO = this.mPantallaPrincipalBean.getOidIdiomaIso();

		ReporteRECReclaIncePendForm form = (ReporteRECReclaIncePendForm) this.formReporte;
		form.setCodigoPais(pais.getCodigo());

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		form.setFechaInicioIngreso((sdf.format(new Date(System
				.currentTimeMillis()))));
		form.setFechaInicioIngresoD(new Date(System.currentTimeMillis()));

		form.setFechaFinIngreso((sdf.format(new Date(System.currentTimeMillis()))));
		form.setFechaFinIngresoD(new Date(System.currentTimeMillis()));

		form.setFechaFacturacion(((sdf.format(new Date(System
				.currentTimeMillis())))));
		form.setFechaFacturacionD((new Date(System.currentTimeMillis())));
	}

	/**
	 * Desabilita la opcion Operacion
	 */
	public void desahabilitarOperacion(ValueChangeEvent val) {
		try {
			log.info("el valor es ");
			log.info("val " + val.getNewValue().toString());
			String obj = val.getNewValue().toString();
			if (obj.equals("1")) {
				this.setHabilitarOperacion(true);
			} else {
				this.setHabilitarOperacion(false);
			}
			log.info(this.getHabilitarOperacion().toString());
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS0".equals(formatoReporte))
			return "reporteRECReclaIncePendReclamosXLS";
		else if ("XLS1".equals(formatoReporte))
			return "reporteRECReclaIncePendIncentivosXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF0".equals(formatoReporte))
			return "reporteRECReclaIncePendReclamosPDF";
		else
			return "reporteRECReclaIncePendIncentivosPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECReclaIncePendForm form = (ReporteRECReclaIncePendForm) this.formReporte;
		Integer fecha1, fecha2;
		Date fecha1D, fecha2D, fecha3D;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());
		fecha2D = form.getFechaFinIngresoD();
		fecha1D = form.getFechaInicioIngresoD();
		fecha3D = form.getFechaFacturacionD();
		
		if(fecha1!= null && fecha2!=null){
			if (fecha1 > fecha2) {
				String mensaje = this.getResourceMessage("reporteRECOperacionesReclamoPedidoForm.validacionFechas");
				return mensaje;
			}
			
		}
		
		if(fecha3D!=null){
			
			String fechaFacturacion = DateUtil.convertDateToString(fecha3D);
			String []fechasPeriodoFinal = loadFechasPeriodoReferenciaFinal();	  
			String []fechasPeriodoInicial= loadFechasPeriodoReferenciaInicial(); 	
		    int resultado = DateUtil.compareDates(fechaFacturacion, fechasPeriodoInicial[1], "dd/MM/yyyy");
		    int resultado1 = DateUtil.compareDates(fechasPeriodoFinal[0], fechaFacturacion, "dd/MM/yyyy");
		    
		    if ( resultado == 1 || resultado1==1) 
	        {	
	            String mensaje = this.getResourceMessage("reporteRECReclaIncePendForm.fechas.facturacion.validacion", new Object[]{fechasPeriodoFinal[0],fechasPeriodoInicial[1]});
	            return mensaje;
	        }
		       
		}		

		return null;
	}
	
	/**
	 * Carga Fechas de periodo final
	 *
	 * @return
	 */
	private String[] loadFechasPeriodoReferenciaFinal(){
		
		ReporteRECReclaIncePendForm form = (ReporteRECReclaIncePendForm) this.formReporte;
		String [] resultado = new String[2];
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		resultado[0]=ajax.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.TODAS,Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoFinal());
		resultado[1]=ajax.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.TODAS,Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoFinal());		
		
		return resultado;
		
	}

	
	/**
	 * Carga fechas de periodo inicial
	 * 
	 * @return
	 */
	private String[] loadFechasPeriodoReferenciaInicial(){
		
		ReporteRECReclaIncePendForm form = (ReporteRECReclaIncePendForm) this.formReporte;
		String [] resultado = new String[2];
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		resultado[0]=ajax.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.TODAS,Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial());
		resultado[1]=ajax.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.TODAS,Constants.CODIGO_CANAL_DEFAULT, form.getCodigoPeriodoInicial());		
		
		return resultado;
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug(">>ENTRO A prepareParameterMap ");

		ReporteRECReclaIncePendForm reporteRECForm = (ReporteRECReclaIncePendForm) this.formReporte;

		this.formatoReporte = reporteRECForm.getFormatoExportacion()+ reporteRECForm.getTipoReporte();
		
//--	Seteando Fechas y enviandolos al reporte
		String fecha1="", fecha2="", fecha3="";
		fecha1 = DateUtil.convertDateToString(reporteRECForm.getFechaInicioIngresoD());
		fecha2 = DateUtil.convertDateToString(reporteRECForm.getFechaFinIngresoD());
		fecha3 = DateUtil.convertDateToString(reporteRECForm.getFechaFacturacionD());

		reporteRECForm.setFechaFacturacion(fecha3);
		reporteRECForm.setFechaInicioIngreso(fecha1);
		reporteRECForm.setFechaFinIngreso(fecha2);
		params.put("fechaFacturacion", reporteRECForm.getFechaFacturacion());
		params.put("fechaInicioIngreso", reporteRECForm.getFechaInicioIngreso());
		params.put("fechaFinIngreso", reporteRECForm.getFechaFinIngreso());
//--
		if (StringUtils.equals(reporteRECForm.getTipoPeriodo(), "0")) {
			params.put("codigoPeriodoReferenciaInicio",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("codigoPeriodoReferenciaFin",
					reporteRECForm.getCodigoPeriodoFinal());
			params.put("codigoPeriodoReclamoInicio", null);
			params.put("codigoPeriodoReclamoFin", null);
		} else {
			params.put("codigoPeriodoReclamoInicio",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("codigoPeriodoReclamoFin",
					reporteRECForm.getCodigoPeriodoFinal());
			params.put("codigoPeriodoReferenciaInicio", null);
			params.put("codigoPeriodoReferenciaFin", null);
		}
		String condicionOperaciones = obtieneCondicion(
				reporteRECForm.getCodigoOperacion(), "ope1.cod_oper", "'");
		String condicionZonas = obtieneCondicion(reporteRECForm.getZonaList(),
				"ZON.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reporteRECForm.getRegionList(), "reg.COD_REGI", "'");
		String condicionTerritorio = obtieneCondicion(
				reporteRECForm.getTerritorioList(), "TE.COD_TERR", "'");
		String condicion1 = condicionRegion + condicionZonas
				+ condicionTerritorio;
		String condicion = condicionOperaciones + condicionRegion
				+ condicionZonas + condicionTerritorio;
		String titulo;
		if (StringUtils.equals(reporteRECForm.getTipoReporte(), "0"))
			titulo = this
					.getReportResourceMessage("reporteRECReclaIncePendForm.reclamos");
		else
			titulo = this
					.getReportResourceMessage("reporteRECReclaIncePendForm.incentivos");
		params.put(
				"NroReporte",
				this.getReportResourceMessage("reporteRECReclaIncePendForm.numero.reporte"));
		params.put(
				"titulo",
				this.getReportResourceMessage("reporteRECReclaIncePendForm.titulo")
						+ titulo);
		params.put("condicion", condicion);
		params.put("condicion1", condicion1);

		return params;
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteRECReclaIncePendForm form = (ReporteRECReclaIncePendForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				List<String> strings = new ArrayList<String>(
						Arrays.asList(regiones));

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc
						.getZonasMultipleByPaisMarcaCanalRegion(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones,
								Constants.FORMATO_TOTAL));
				form.setZonaList(null);
			} else {
				this.siccZonaList = null;
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
	 *            Obtiene la lsita de territorios de acuerdo a la zonas
	 *            escogidas.
	 */
	public void showTerritorioxZona(ValueChangeEvent val) {
		log.debug(">>showTerritorioxZona ");
		try {
			ReporteRECReclaIncePendForm form = (ReporteRECReclaIncePendForm) this.formReporte;

			String[] regiones = (String[]) form.getRegionList();

			String[] zonas = (String[]) val.getNewValue();
			if (!val.equals(null) && zonas.length > 0) {

				List<String> listaRegiones = new ArrayList<String>(
						Arrays.asList(regiones));

				List<String> listaZonas = new ArrayList<String>(
						Arrays.asList(zonas));

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccTerritorioList(aSvc
						.getTerritoriosMultipleByPaisMarcaCanalRegionZona(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, listaRegiones,
								listaZonas, Constants.FORMATO_TOTAL));

				form.setTerritorioList(null);
			} else {
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
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return
	 */
	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	/**
	 * @param codigoIdiomaISO
	 */
	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
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
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the habilitarOperacion
	 */
	public Boolean getHabilitarOperacion() {
		return habilitarOperacion;
	}

	/**
	 * @param habilitarOperacion
	 *            the habilitarOperacion to set
	 */
	public void setHabilitarOperacion(Boolean habilitarOperacion) {
		this.habilitarOperacion = habilitarOperacion;
	}
}