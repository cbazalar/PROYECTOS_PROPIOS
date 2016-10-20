package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReportePRYProyeccionFaltanteDiaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.ProcesoPRYProyeccionFaltanteDiaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReportePRYProyeccionFaltanteDiaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1435748424609950127L;
	private String formatoReporte;
	private List siccPresentacionList;
	private LabelValue[] siccVersionesList = {};
	private String tipoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePRYProyeccionFaltanteDiaForm reporteForm = new ReportePRYProyeccionFaltanteDiaForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReportePRYProyeccionFaltanteDiaAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;

		ArrayList resultado = new ArrayList();
		Base[] mes = new Base[4];

		ReportePRYProyeccionFaltanteDiaForm f = (ReportePRYProyeccionFaltanteDiaForm) this.formReporte;
		ProcesoPRYProyeccionFaltanteDiaService serviceProy = (ProcesoPRYProyeccionFaltanteDiaService) getBean("spusicc.procesoPRYProyeccionFaltanteDiaService");

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));

		/* Obteniendo maximo nro de version */
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("fechaFacturacion", fecha);
		String numeroVersion = serviceProy
				.getMaximoVersionProyeccionFaltanteDia(criteria);
		if (StringUtils.isBlank(numeroVersion))
			numeroVersion = "1";
		f.setNumeroVersion(numeroVersion);
		f.setFechaFacturacionD(DateUtil.convertStringToDate(fecha));

		/* Visualizando tipo de reportes */
		String[] presentaciones = { "Resumen", "Detallado", "Resumen PROL",
				"Detallado PROL" };
		for (int i = 0; i < 4; i++) {
			mes[i] = new Base();
			mes[i].setCodigo("" + (i + 1));
			mes[i].setDescripcion(presentaciones[i]);
			resultado.add(mes[i]);
		}
		this.siccPresentacionList = resultado;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		this.siccVersionesList = ajaxService.getNumeroVersionesFaltanteDia(
				pais.getCodigo(), fecha);

		log.debug("Todo Ok: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reportePRYProyeccionFaltanteDiaService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePRYProyeccionFaltanteDiaForm form = (ReportePRYProyeccionFaltanteDiaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion())) {
			if ("1".equals(tipoReporte))
				return "reportePRYProyFaltXLS";
			if ("2".equals(tipoReporte))
				return "reportePRYProyFaltDetalleXLS";
			if ("3".equals(tipoReporte))
				return "reportePRYProyFaltProlXLS";
			if ("4".equals(tipoReporte))
				return "reportePRYProyFaltDetalleProlXLS";
		} else
			return "reporteMaestroHorizontal";

		return "";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReportePRYProyeccionFaltanteDiaForm form = (ReportePRYProyeccionFaltanteDiaForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF".equals(formReporte.getFormatoExportacion())) {
			if ("1".equals(tipoReporte))
				return "reportePRYProyeccionFaltante";
			if ("2".equals(tipoReporte))
				return "reportePRYProyeccionFaltanteDetalle";
			if ("3".equals(tipoReporte))
				return "reportePRYProyeccionFaltanteProl";
			if ("4".equals(tipoReporte))
				return "reportePRYProyeccionFaltanteDetalleProl";
		}
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReportePRYProyeccionFaltanteDiaForm f = (ReportePRYProyeccionFaltanteDiaForm) this.formReporte;
		String presentacion = f.getPresentacion();
		String fechaFactura = DateUtil.convertDateToString(f
				.getFechaFacturacionD());
		f.setFechaFacturacion(fechaFactura);
		this.formatoReporte = f.getFormatoExportacion();
		this.tipoReporte = presentacion;

		params.put("tipoPresentacion", presentacion);
		params.put("NroReporte", " ");
		params.put("fechaFacturacion", fechaFactura);

		/* colocando titulo */
		if ("1".equals(tipoReporte)) {
			params.put(
					"titulo",
					getResourceMessage("reportePRYProyeccionFaltanteDiaForm.titulo1"));
		} else if ("2".equals(tipoReporte)) {
			params.put(
					"titulo",
					getResourceMessage("reportePRYProyeccionFaltanteDiaForm.titulo2"));
		} else if ("3".equals(tipoReporte)) {
			params.put(
					"titulo",
					getResourceMessage("reportePRYProyeccionFaltanteDiaForm.titulo3"));
		} else if ("4".equals(tipoReporte)) {
			params.put(
					"titulo",
					getResourceMessage("reportePRYProyeccionFaltanteDiaForm.titulo4"));
		}

		return params;
	}

	/**
	 * Metodo para Cambiar versiones
	 * 
	 * @param val
	 */
	public void loadVersion() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			ReportePRYProyeccionFaltanteDiaForm f = (ReportePRYProyeccionFaltanteDiaForm) this.formReporte;

			String fecgaFac = format.format(f.getFechaFacturacionD());

			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			this.siccVersionesList = (ajaxService.getNumeroVersionesFaltanteDia(
					pais.getCodigo(), fecgaFac));
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
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
	 * @return the siccPresentacionList
	 */
	public List getSiccPresentacionList() {
		return siccPresentacionList;
	}

	/**
	 * @param siccPresentacionList
	 *            the siccPresentacionList to set
	 */
	public void setSiccPresentacionList(List siccPresentacionList) {
		this.siccPresentacionList = siccPresentacionList;
	}

	/**
	 * @return the siccVersionesList
	 */
	public LabelValue[] getSiccVersionesList() {
		return siccVersionesList;
	}

	/**
	 * @param siccVersionesList
	 *            the siccVersionesList to set
	 */
	public void setSiccVersionesList(LabelValue[] siccVersionesList) {
		this.siccVersionesList = siccVersionesList;
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
}