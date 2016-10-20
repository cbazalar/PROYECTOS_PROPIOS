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
import biz.belcorp.ssicc.web.scsicc.form.ReporteAPPPedidosServicioForm;

@ManagedBean
@SessionScoped
public class ReporteAPPPedidosServicioAction extends BaseReporteAbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4285981763584087239L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteAPPPedidosServicioForm reporteForm = new ReporteAPPPedidosServicioForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteAPPPedidosServicioAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;

		ReporteAPPPedidosServicioForm f = (ReporteAPPPedidosServicioForm) this.formReporte;

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
		return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteAPPPedidosServicio";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		params.put("NroReporte", " ");
		params.put("titulo", "INFORMACION DE PEDIDOS DE SERVICIO");
		return params;
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
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

}
