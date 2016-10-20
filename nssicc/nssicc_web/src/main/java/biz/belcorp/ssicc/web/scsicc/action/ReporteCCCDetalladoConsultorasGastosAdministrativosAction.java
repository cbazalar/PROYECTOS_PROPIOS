package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCDetalladoConsultorasGastosAdministrativosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCDetalladoConsultorasGastosAdministrativosAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 6467992741175535675L;
	
	private String codigoIdiomaIso;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};
	private String tipoReporte;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #getFormatoExportacion()
	 */
	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setFormatoExportacion(java.lang.String)
	 */
	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteCCCDetalladoConsultorasGastosAdministrativosForm f1 = (ReporteCCCDetalladoConsultorasGastosAdministrativosForm) this.formReporte;
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f1.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteria);

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
		return "reporteCCCDetalladoGastosAdm" + this.tipoReporte + "XLS";

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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCDetalladoConsultorasGastosAdministrativosForm r = (ReporteCCCDetalladoConsultorasGastosAdministrativosForm) this.formReporte;
		String vFechaInicio = DateUtil.getDate(r.getFechaInicioD());
		String vFechaFinD = DateUtil.getDate(r.getFechaFinD());
		if (!vFechaInicio.isEmpty() && !vFechaFinD.isEmpty()) {
			if (r.getFechaInicioD().compareTo(r.getFechaFinD()) > 0)
				return getResourceMessage("reporteRETDetalleComisionVentaRetailForm.validar.fechas");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteCCCDetalladoConsultorasGastosAdministrativosForm f = (ReporteCCCDetalladoConsultorasGastosAdministrativosForm) this.formReporte;

		f.setFechaInicio(DateUtil.getDate(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.getDate(f.getFechaFinD()));
		this.tipoReporte = f.getTipoReporte();
		params.put("fechaInicio", DateUtil.convertDateToString(f.getFechaInicioD()));
		params.put("fechaFin", DateUtil.convertDateToString(f.getFechaFinD()));

		String condicionRegion = this.obtieneCondicion(f.getRegionList(),
				"ZR.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion" + condicionRegion.toString());

		String condicionZona = this.obtieneCondicion(f.getZonaList(),
				"ZZ.COD_ZONA", "'");
		params.put("condicionZona", condicionZona);
		log.debug("condicionZona" + condicionZona.toString());

		String condicionSeccion = this.obtieneCondicion(f.getSeccionList(),
				"ZS.COD_SECC", "'");
		params.put("condicionSeccion", condicionSeccion);
		log.debug("condicionSeccion" + condicionSeccion.toString());

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
		ReporteCCCDetalladoConsultorasGastosAdministrativosForm reporteForm = new ReporteCCCDetalladoConsultorasGastosAdministrativosForm();
		return reporteForm;
	}

	/**
	 * Obtener Lista de Zonas	 
	 */
	public void showZonasxRegion(ValueChangeEvent val) {		
		try {
			ReporteCCCDetalladoConsultorasGastosAdministrativosForm form = (ReporteCCCDetalladoConsultorasGastosAdministrativosForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,	Constants.CODIGO_CANAL_DEFAULT, regiones,Constants.FORMATO_TOTAL));
				form.setZonaList(null);
				form.setSeccionList(null);
				this.siccSeccionList=null;
			} else {
				this.siccZonaList = null;
				form.setZonaList(null);
				this.siccSeccionList = null;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Buscar seccion por zona	
	 */
	public void showSeccionxZona(ValueChangeEvent val) {	
		try {
			ReporteCCCDetalladoConsultorasGastosAdministrativosForm form = (ReporteCCCDetalladoConsultorasGastosAdministrativosForm) this.formReporte;
			String[] zonas = (String[]) val.getNewValue();
			if (!val.equals(null) && zonas.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccSeccionList(aSvc.getSeccionMultipleByPaisMarcaCanalRegionZona(form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,	Constants.CODIGO_CANAL_DEFAULT,	form.getRegionList(), zonas,
								Constants.FORMATO_TOTAL));
				form.setSeccionList(null);
			} else {
				siccSeccionList = null;
				form.setSeccionList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
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
}