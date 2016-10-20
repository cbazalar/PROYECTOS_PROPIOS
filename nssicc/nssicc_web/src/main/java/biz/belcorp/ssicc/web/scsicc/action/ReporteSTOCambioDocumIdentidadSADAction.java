package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
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
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOCambioDocumIdentidadSADForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTOCambioDocumIdentidadSADAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 6467992741175535675L;
	private String formatoExportacion;
	private String codigoIdiomaIso;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};

	/**
	 * @return
	 */
	public String getCodigoIdiomaIso() {
		return codigoIdiomaIso;
	}

	/**
	 * @param codigoIdiomaIso
	 */
	public void setCodigoIdiomaIso(String codigoIdiomaIso) {
		this.codigoIdiomaIso = codigoIdiomaIso;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#getFormatoExportacion()
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setFormatoExportacion(java.lang.String)
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = true;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteSTOCambioDocumIdentidadSADForm reporteSICForm = (ReporteSTOCambioDocumIdentidadSADForm) this.formReporte;
		reporteSICForm.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", reporteSICForm.getCodigoPais());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);
		reporteSICForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		reporteSICForm.reset();
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
		
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String primerPeriodo = aSvc.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT,reporteSICForm.getCodigoPeriodo());
		reporteSICForm.setFechaInicioD(DateUtil.convertStringToDate(primerPeriodo));
		String segundoPeriodo = aSvc.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(),Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT,reporteSICForm.getCodigoPeriodo());
		reporteSICForm.setFechaFinD(DateUtil.convertStringToDate(segundoPeriodo));
		
		this.siccZonaList = new LabelValue[1];
		LabelValue labelValue = new LabelValue();
		labelValue.setLabel("Todos");
		labelValue.setValue("");
		this.siccZonaList[0] = labelValue;
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
		log.debug("Formato Exportacion Reporte =====>" + this.formatoExportacion);
		if ("XLS".equals(this.formatoExportacion))
			return "reporteSTOCambiosDocumIdentidadXLS";
		else
			return "reporteMaestroHorizontal";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteSTOCambioDocumIdentidadSADForm f = (ReporteSTOCambioDocumIdentidadSADForm) this.formReporte;
		f.setFormatoExportacion(this.getFormatoExportacion());
		this.formatoExportacion = f.getFormatoExportacion();
		params.put("codigoTipoDoc", Constants.STO_TIPO_DOCUMENTO_SAD);
		params.put("codigoValidDNI", Constants.STO_CAMBIODOCIDEN_DNI);
		params.put("codigoValidRUC", Constants.STO_CAMBIODOCIDEN_RUC);
		f.setFechaInicio(DateUtil.getDate(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.getDate(f.getFechaFinD()));
		params.put("fechaInicio", f.getFechaInicio());
		params.put("fechaFin", f.getFechaFin());

		params.put("condicionRegion",
				obtieneCondicion(f.getCodigoRegion(), "sd.COD_REGI", "'"));
		params.put("condicionZona",
				obtieneCondicion(f.getCodigoZona(), "sd.COD_ZONA", "'"));

		params.put("NroReporte", "");
		params.put(
				"titulo",
				getResourceMessage("reporteSTOCambioDocumIdentidadSADForm.title"));

		params.put("codigoPeriodo", f.getCodigoPeriodo());

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOCambioDocumIdentidadSADForm reporteForm = new ReporteSTOCambioDocumIdentidadSADForm();
		return reporteForm;
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
		log.debug("Formato Exportacion Sub Reporte =====>" + this.formatoExportacion);
		if ("PDF".equals(this.formatoExportacion))
			return "reporteSTOCambiosDocumIdentidadPDF";
		return null;
	}
	
	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion s ");
		try {
			ReporteSTOCambioDocumIdentidadSADForm form = (ReporteSTOCambioDocumIdentidadSADForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(
						form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, regiones,
						Constants.FORMATO_TOTAL);
				form.setCodigoZona(null);
			} else {
				this.siccZonaList = null;
				form.setCodigoZona(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	public void loadFechasPeriodo(String valor) throws Exception
	{
		if(StringUtils.isNotBlank(valor) && valor.length() == 6)
		{
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			ReporteSTOCambioDocumIdentidadSADForm form = (ReporteSTOCambioDocumIdentidadSADForm) this.formReporte;
			
			form.setFechaInicio(ajax.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, valor));
			
			form.setFechaFin(ajax.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT, 
					Constants.CODIGO_CANAL_DEFAULT, valor));
			
			form.setFechaInicioD(DateUtil.convertStringToDate(form.getFechaInicio()));
			form.setFechaFinD(DateUtil.convertStringToDate(form.getFechaFin()));
		}
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
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
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteSTOCambioDocumIdentidadSADForm form = (ReporteSTOCambioDocumIdentidadSADForm) this.formReporte;
		if (form.getFechaFinD() != null && form.getFechaInicioD() != null) {
			if (form.getFechaFinD().compareTo(form.getFechaInicioD()) < 0) {
				String mensaje = this
						.getResourceMessage("errors.compare.dates");
				return mensaje;
			}
		}
		return null;
	}

}