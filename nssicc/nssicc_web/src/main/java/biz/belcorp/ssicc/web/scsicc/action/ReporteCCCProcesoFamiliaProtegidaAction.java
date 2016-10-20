package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCProcesoFamiliaProtegidaForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteCCCProcesoFamiliaProtegidaAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 6467992741175535675L;
	private String tipoVista;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String periodoActual = new String();

		ReporteCCCProcesoFamiliaProtegidaForm f = (ReporteCCCProcesoFamiliaProtegidaForm) this.formReporte;

		// -- Logica
		periodoActual = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(),
				Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPais(pais.getCodigo());

		f.setCodigoPeriodoInicial(periodoActual);
		f.setCodigoPeriodoFinal(periodoActual);
		this.siccRegionList = aSvc.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteCCCProcesoFamiliaProtegidaForm form = (ReporteCCCProcesoFamiliaProtegidaForm) this.formReporte;
		Integer fecha1, fecha2;
		fecha1 = Integer.parseInt(form.getCodigoPeriodoInicial());
		fecha2 = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (fecha1 > fecha2) {
			String mensaje = "Periodo Final no debe ser menor a Periodo Inicial";
			return mensaje;
		}
		return null;
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
		return "reporteCCCProcesoFamiliaProtegida" + tipoVista + "XLS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCProcesoFamiliaProtegidaForm reporteCCCForm = (ReporteCCCProcesoFamiliaProtegidaForm) this.formReporte;
		this.tipoVista = reporteCCCForm.getTipoVista();
		String condicionZonas = obtieneCondicion(reporteCCCForm.getZonaList(),
				"COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reporteCCCForm.getRegionList(), "COD_REGI", "'");

		String condicion = condicionZonas + condicionRegion;
		params.put("condicion", condicion);
		log.debug(" Imprimiendo parmetros");
		log.debug(params);
		log.debug("Fin parmetros");

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
		ReporteCCCProcesoFamiliaProtegidaForm reporteForm = new ReporteCCCProcesoFamiliaProtegidaForm();
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
		return null;
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteCCCProcesoFamiliaProtegidaForm form = (ReporteCCCProcesoFamiliaProtegidaForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
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
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
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
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}
}