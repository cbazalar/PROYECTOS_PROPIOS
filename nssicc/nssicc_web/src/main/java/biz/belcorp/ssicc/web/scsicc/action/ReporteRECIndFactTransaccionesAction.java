package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECIndFactTransaccionesForm;

@ManagedBean
@SessionScoped
public class ReporteRECIndFactTransaccionesAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -327119423812976776L;
	private String formatoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECIndFactTransaccionesForm reporteForm = new ReporteRECIndFactTransaccionesForm();
		return reporteForm;
	}



	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteRECIndFactTransaccionesAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReporteRECIndFactTransaccionesForm f = (ReporteRECIndFactTransaccionesForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		criteriaOperacion.put("codigoPais", pais.getCodigo());

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int i = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}

		log.debug("Todo OK: Redireccionando");

	}
	
	public String setValidarReporte() {
		ReporteRECIndFactTransaccionesForm form = (ReporteRECIndFactTransaccionesForm)this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicio());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFin());
		if(codperfin<codperini){
			String mensaje =  this.getResourceMessage("reporteRECIndFactTransaccionesForm.errorInicioMayor");
			return mensaje;
		}

	    					
	    return null;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECIndFactTransaccionesForm form = (ReporteRECIndFactTransaccionesForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "ReporteRECIndFactTransaccionesXLS";
		else
			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteRECIndFactTransaccionesForm f = (ReporteRECIndFactTransaccionesForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();

		params.put("CodigoPeriodoInicio", f.getCodigoPeriodoInicio());
		params.put("CodigoPeriodoFin", f.getCodigoPeriodoFin());
		params.put("ZonaList", f.getZonaList());
		params.put("titulo", getResourceMessage("reporteRECIndFactTransaccionesForm.title"));	
		log.info("Salio a ReporteRECIndFactTransaccionesAction - prepareParameterMap");
		return params;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String valor = (String) val.getNewValue();
		String[] valores = new String[1];
		valores[0] = valor;
		if (valores.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajaxService
					.getZonasMultipleByPaisMarcaCanalRegion(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valores,
							Constants.OPCION_TODOS));
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
