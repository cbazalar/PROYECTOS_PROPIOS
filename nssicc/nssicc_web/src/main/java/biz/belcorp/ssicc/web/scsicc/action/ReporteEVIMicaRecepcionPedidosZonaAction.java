package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteEVIMicaRecepcionPedidosZonaForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteEVIMicaRecepcionPedidosZonaAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -2393630922224464627L;
	private List siccRegionList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteEVIMicaRecepcionPedidosZonaForm f = (ReporteEVIMicaRecepcionPedidosZonaForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// para la obtencion del periodo default : Canal VD y Marca TODAS

		Map criteria = new HashMap();
		String codigoCanal = Constants.CODIGO_CANAL_DEFAULT;
		String codigoMarca = Constants.CODIGO_MARCA_DEFAULT;
		criteria.put("codigoCanal", codigoCanal);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoPais", usuario.getPais().getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), ""));
		f.setCodigoCanal(codigoCanal);
		f.setCodigoMarca(codigoMarca);
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
		return "reporteEVIMicaRecepcionPedidosZonaPDF";
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
		return "reporteMaestroHorizontal";
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
		ReporteEVIMicaRecepcionPedidosZonaForm form = new ReporteEVIMicaRecepcionPedidosZonaForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteEVIMicaRecepcionPedidosZonaForm reporteForm = (ReporteEVIMicaRecepcionPedidosZonaForm) this.formReporte;
		params.put(
				"titulo",
				getReportResourceMessage("reporteEVIMicaRecepcionPedidosZonaForm.titulo")
						+ " "
						+ DateUtil.getDate(new Date())
						+ "\n"
						+ getReportResourceMessage("reporteEVIMicaRecepcionPedidosZonaForm.campana")
						+ " " + reporteForm.getCodigoPeriodo());
		params.put("NroReporte", "");
		return params;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
}