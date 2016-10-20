package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPPPedidosBolsaForm;

@ManagedBean
@SessionScoped
public class ReporteAPPPedidosBolsaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 5935561866180894084L;

	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteAPPPedidosBolsaForm reporteForm = new ReporteAPPPedidosBolsaForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		

		ReporteAPPPedidosBolsaForm f = (ReporteAPPPedidosBolsaForm) this.formReporte;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());

		log.debug("Todo Ok: Redireccionando");
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteAPPPedidosBolsa";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}

		params.put("NroReporte", " ");
		params.put("titulo", "PORCENTAJE DE PEDIDOS FACTURADOS");
		return params;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

}
