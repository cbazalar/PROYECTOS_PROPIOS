package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteEVIMicaRecepcionPedidosRegionForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteEVIMicaRecepcionPedidosRegionAction extends
		BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2738796949590860015L;
	private String formatoReporte;
	private List siccRegionList;

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteEVIMicaRecepcionPedidosRegionForm reporteForm = new ReporteEVIMicaRecepcionPedidosRegionForm();
		return reporteForm;
	}

	protected String devuelveNombreReporte() throws Exception {

		return "reporteMaestroHorizontal";
	}

	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteEVIMicaRecepcionPedidosRegionPDF";
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.mostrarReportePDF = true;

		// servicios y formulario
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteEVIMicaRecepcionPedidosRegionForm f = (ReporteEVIMicaRecepcionPedidosRegionForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		// parametros generales
		Pais pais = getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = getmPantallaPrincipalBean().getCurrentUser();
		// reset
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		// Map para almacenar los parametros
		Map criteria = new HashMap();
		String codigoCanal = f.getCodigoCanal();
		String codigoMarca = f.getCodigoMarca();
		criteria.put("codigoCanal", codigoCanal);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoPais", usuario.getPais().getCodigo());
		// seteando

		siccRegionList = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), f.getCodigoCanal()));
		log.debug("Todo Ok: Redireccionando");

	}

	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCDetalladoPagosPorRegularizarEliminadosForm.prepareParameterMap' method");
		}

		
		ReporteEVIMicaRecepcionPedidosRegionForm reporteForm = (ReporteEVIMicaRecepcionPedidosRegionForm) this.formReporte;
		params.put(
				"titulo",
				getResourceMessage(
						"reporteEVIMicaRecepcionPedidosRegionForm.title")
						+ " "
						+ DateUtil.getDate(new Date())
						+ "\n"
						+ getResourceMessage(
								"reporteEVIMicaRecepcionPedidosRegionForm.campana") + " " + reporteForm.getCodigoPeriodo());

		params.put("NroReporte", "");
		return params;

	}

}