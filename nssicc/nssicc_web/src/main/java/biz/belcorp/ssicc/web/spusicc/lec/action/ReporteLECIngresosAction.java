package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ReporteLECIngresosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteLECIngresosAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -8283811688652696544L;

	private String formatoReporte;
	private List siccRegionList;
	private List lecGrupoRegionList;
	private LabelValue[] lecRegionList;
	private String indTipoGrupoRegion1;
	private String indTipoGrupoPago;
	private String ZON_TIPO_UA_REGION;
	private String ZON_TIPO_UA_ZONA;

	/**
	 * @return
	 */
	public String getIndTipoGrupoRegion1() {
		return indTipoGrupoRegion1;
	}

	/**
	 * @param indTipoGrupoRegion1
	 */
	public void setIndTipoGrupoRegion1(String indTipoGrupoRegion1) {
		this.indTipoGrupoRegion1 = indTipoGrupoRegion1;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLECIngresosForm reporteForm = new ReporteLECIngresosForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		String reporte = "";

		if ("XLS".equals(formatoReporte)) { // XLS
			reporte = "reporteLECIngresosXLS";
		} else {
			reporte = "reporteMaestroHorizontalLECIngresos"; // PDF
		}
		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		String reporte = "reporteLECIngresosPDF";

		if ("XLS".equals(formatoReporte)) {
			reporte = "";
		}
		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteLECIngresosForm reporteForm = (ReporteLECIngresosForm) this.formReporte;
		
		this.formatoReporte = reporteForm.getFormatoExportacion();
		
		ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteLECIngresos" + JASPER_EXTENSION);	
		params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
			
		String regionList[] = null;
		String condicionRegion = "";
		String condicionRegion1 = "";

		if (StringUtils.isNotBlank(reporteForm.getCodigoGrupoPago())) {
			regionList = reporteForm.getRegion();
			if (ArrayUtils.isEmpty(regionList)) {
				regionList = (String[]) ArrayUtils.add(regionList,reporteForm.getCodigoGrupoPago());
			}
			
		}else{
			regionList = (String[]) ArrayUtils.add(regionList,reporteForm.getCodigoRegion());
		}

 		if (!regionList[0].equals(Constants.OPCION_TODOS)) {
 			if(StringUtils.equals(this.indTipoGrupoPago, Constants.ZON_TIPO_UA_ZONA))
 			{
 	 			condicionRegion = this.obtieneCondicion(regionList, "dr.cod_zona", "'");
 	 			condicionRegion1 = this.obtieneCondicion(regionList, "dtr.cod_zona", "'");
 			}
 			else if(StringUtils.equals(this.indTipoGrupoPago, Constants.ZON_TIPO_UA_REGION))
 			{
 	 			condicionRegion = this.obtieneCondicion(regionList, "dr.cod_regi", "'");
 	 			condicionRegion1 = this.obtieneCondicion(regionList, "dtr.cod_regi", "'");
 			}
 			else
 			{
 	 			condicionRegion = this.obtieneCondicion(regionList, "dr.cod_regi", "'");
 	 			condicionRegion1 = this.obtieneCondicion(regionList, "dtr.cod_regi", "'");
 			}
		}

		params.put("condicionRegion", condicionRegion);
		params.put("condicionRegion1", condicionRegion1);
		params.put("condicionTramos", reporteForm.getTramo());
		
		params.put("NroReporte", "");
		params.put("campanyaProceso", reporteForm.getCampanyaProceso());
		params.put("titulo",this.getResourceMessage("reporteLECIngresosForm.titulo"));
		
		return params;
	}
	
	/**
	 * Obtiene indicador de Mostrar GrupoPago
	 * @param request
	 * @return indTipoGrupoPago
	 */
	private String getIndicadorGrupoPago() {
		Map criteriaParam = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", Constants.LEC_IND_TIPO_GRUPO_PAGO);
		return ((MantenimientoSTOBloqueoControlService)
			getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		ReporteLECIngresosForm f = (ReporteLECIngresosForm) this.formReporte;
		Map result = service.getPeriodoDefault();
		String codigoPeriodo = (String) result.get("codigoPeriodo");
		f.setCampanyaProceso(codigoPeriodo);
		this.ZON_TIPO_UA_REGION = Constants.ZON_TIPO_UA_REGION;
		this.ZON_TIPO_UA_ZONA = Constants.ZON_TIPO_UA_ZONA;


		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		String indTipoGrupoRegion = getIndicadorGrupoRegion();
		this.indTipoGrupoPago = indTipoGrupoRegion;
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("indTipoGrupoPago", this.indTipoGrupoPago);
		List list = lecService.getGruposPago(map);
		this.siccRegionList = reporteService.getListaGenerico("getRegionesPEJ",
				map);
		this.lecGrupoRegionList = list;
		
	
	}

	/**
	 * @return
	 */
	private String getIndicadorGrupoRegion() {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro",Constants.LEC_IND_TIPO_GRUPO_PAGO);
		return ((MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService"))
				.getParametroGenericoSistema(criteriaParam);

	}

	/**
	 * @param val
	 */
	public void loadRegiones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadRegiones");
		}
		try {
			String valor = (String) val.getNewValue();
			if (valor.trim().length() > 0) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				setLecRegionList(ajaxService.getRegionesZonasByTipoGrupo(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), valor));
			} else {
				setLecRegionList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @return
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public List getLecGrupoRegionList() {
		return lecGrupoRegionList;
	}

	/**
	 * @param lecGrupoRegionList
	 */
	public void setLecGrupoRegionList(List lecGrupoRegionList) {
		this.lecGrupoRegionList = lecGrupoRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getLecRegionList() {
		return lecRegionList;
	}

	/**
	 * @param lecRegionList
	 */
	public void setLecRegionList(LabelValue[] lecRegionList) {
		this.lecRegionList = lecRegionList;
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
	 * @return the indTipoGrupoPago
	 */
	public String getIndTipoGrupoPago() {
		return indTipoGrupoPago;
	}

	/**
	 * @param indTipoGrupoPago the indTipoGrupoPago to set
	 */
	public void setIndTipoGrupoPago(String indTipoGrupoPago) {
		this.indTipoGrupoPago = indTipoGrupoPago;
	}

	/**
	 * @return the zON_TIPO_UA_REGION
	 */
	public String getZON_TIPO_UA_REGION() {
		return ZON_TIPO_UA_REGION;
	}

	/**
	 * @param zON_TIPO_UA_REGION the zON_TIPO_UA_REGION to set
	 */
	public void setZON_TIPO_UA_REGION(String zON_TIPO_UA_REGION) {
		ZON_TIPO_UA_REGION = zON_TIPO_UA_REGION;
	}

	/**
	 * @return the zON_TIPO_UA_ZONA
	 */
	public String getZON_TIPO_UA_ZONA() {
		return ZON_TIPO_UA_ZONA;
	}

	/**
	 * @param zON_TIPO_UA_ZONA
	 *            the zON_TIPO_UA_ZONA to set
	 */
	public void setZON_TIPO_UA_ZONA(String zON_TIPO_UA_ZONA) {
		ZON_TIPO_UA_ZONA = zON_TIPO_UA_ZONA;
	}
}