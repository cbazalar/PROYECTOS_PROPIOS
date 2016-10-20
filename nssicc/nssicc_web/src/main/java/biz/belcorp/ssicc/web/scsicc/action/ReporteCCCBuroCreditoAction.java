package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCCCBuroCreditoForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteCCCBuroCreditoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -6803346770285040515L;

	private String formatoReporte;
	private List siccSociedadList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCBuroCreditoForm reporteForm = new ReporteCCCBuroCreditoForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		this.mostrarReporteOCSV = true;
		ReporteCCCBuroCreditoForm f = (ReporteCCCBuroCreditoForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();
		siccSociedadList = service.getSociedadesByCodigoPais(codpais);

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCCCBuroCreditoXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCBuroCreditoService";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		log.debug(" Imprimiendo parįmetros");
		log.debug(params);
		log.debug("Fin parįmetros");
		return params;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

}
