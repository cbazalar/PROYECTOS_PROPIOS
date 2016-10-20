package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaMAVNoPasaronPedidosForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaMAVNoPasaronPedidosAction extends
		BaseReporteAbstractAction {
	private String formatoExportacion;
	private String formatoReporte;
	private List siccCentrodList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private String[] lisZona;
	private List reporteMAVNoPasaronPedidosList;

	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

	public List getReporteMAVNoPasaronPedidosList() {
		return reporteMAVNoPasaronPedidosList;
	}

	public void setReporteMAVNoPasaronPedidosList(
			List reporteMAVNoPasaronPedidosList) {
		this.reporteMAVNoPasaronPedidosList = reporteMAVNoPasaronPedidosList;
	}

	public String[] getLisZona() {
		return lisZona;
	}

	public void setLisZona(String[] lisZona) {
		this.lisZona = lisZona;
	}

	



	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccCentrodList() {
		return siccCentrodList;
	}

	public void setSiccCentrodList(List siccCentrodList) {
		this.siccCentrodList = siccCentrodList;
	}

	private static final long serialVersionUID = 5452137025798023588L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaMAVNoPasaronPedidosForm reporteForm = new ConsultaMAVNoPasaronPedidosForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {

		if ("XLS".equals(formatoExportacion)) {
			// return "reporteMAVEstAtenCampanaXLS";
			return "reporteMAVNoPasaPedXLS";
		} else
			return "reporteMaestroHorizontal";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */

	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteAPETotalArticulosAFPForm.setViewAtributes' method");
		}
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReporteXLS = false;
		this.mostrarReportePDF = false;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		ConsultaMAVNoPasaronPedidosForm f = (ConsultaMAVNoPasaronPedidosForm) this.formReporte;
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),
				Constants.CODIGO_CANAL_DEFAULT));


		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();

		criteria.put("codigoPais", getmPantallaPrincipalBean()
				.getCurrentCountry().getCodigo());

		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);

		siccCentrodList = service.getCentroDistribucionByPais(criteria);
		


		Map criteriaOperacion = new HashMap();		
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());		
		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);		
		if(listaRegiones.size()>0){
			this.siccRegionList = new LabelValue[listaRegiones.size()];
			int i = 0;
			for(Object object : listaRegiones){
				LabelValue labelValue = new LabelValue();
				labelValue.setLabel(((Base)object).getDescripcion());
				labelValue.setValue(((Base)object).getCodigo());
				this.getSiccRegionList()[i] = labelValue;
				i++;
			}
		}

		

	}
	
	public void cambiaZonasByRegion(ValueChangeEvent val){
		if(log.isDebugEnabled()){
			log.debug("cambiaZonas...");
		}
		String[] valores = (String[]) val.getNewValue();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		ConsultaMAVNoPasaronPedidosForm form = (ConsultaMAVNoPasaronPedidosForm) this.formReporte;
		this.setSiccZonaList(ajaxService.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),  Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, valores, Constants.OPCION_TODOS));
	}

	protected List setFindAttributes() throws Exception {

		ConsultaMAVNoPasaronPedidosForm reporteForm = (ConsultaMAVNoPasaronPedidosForm) this.formReporte;
		ReporteService service = (ReporteService) getBean("scsicc.reporteService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		reporteForm
				.setListCodigoZona(this.parseArray(reporteForm.getZonaList()));
		Map criteria = new HashMap();

		// Periodo
		String codPeriodo = reporteForm.getCodigoPeriodo();
		criteria.put("codigoPeriodo", codPeriodo);
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		criteria.put("oidPeriodo", oidPeriodo);

		// Region y zona
		String formaRegion = this.formaCondicion(reporteForm.getRegionList(),
				"zon.COD_REGI", "'");
		String[] lisZona = reporteForm.getZonaList();
		this.setLisZona(null);
		this.setLisZona(lisZona);

		criteria.put("formaRegion", formaRegion);
		criteria.put("lisZona", this.getLisZona());

		// Codigo SAP
		if (!StringUtils.equals(reporteForm.getCodigoSAP(), ""))
			criteria.put("codigoSAP", reporteForm.getCodigoSAP());

		// Obteniendo Lista
		List resultado = service.getNoPasaronPedidosMAV(criteria);
		reporteMAVNoPasaronPedidosList = resultado;

		// session.setAttribute(Constants.REPORTE_MAV_NO_PASARON_PEDIDOS,resultado);

		return resultado;
	}

	private String formaCondicion(String[] lista, String parametro,
			String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isEmpty(dato)
						|| StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ")";
			resultado = " IN " + resultado;
			return resultado;
		}
	}

	private String parseArray(Object[] arr) {
		StringBuffer sb = new StringBuffer("");
		if (arr != null)
			for (int i = 0; i < arr.length; i++) {
				if (i > 0) {
					sb.append(",");
				}
				sb.append(arr[i].toString());
			}
		return sb.toString();
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ConsultaMAVNoPasaronPedidosForm reporteForm = (ConsultaMAVNoPasaronPedidosForm) this.formReporte;	

		
		formatoExportacion="XLS";
		Map criteria = params;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		String codigoPeriodo=new String();
		codigoPeriodo= reporteForm.getCodigoPeriodo();
		criteria.put("codigoPeriodo", codigoPeriodo);
        String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		params.put("oidPeriodo", oidPeriodo);	
		
		String[] listaZona = reporteForm.getZonaList();
		String lisZona = this.obtieneCondicion(listaZona, "zon.COD_ZONA", "'");
		//String lisZona = session.getAttribute("listZona");
		criteria.put("lisZona", lisZona);
		
		//Codigo SAP
		if (!StringUtils.equals(reporteForm.getCodigoSAP(), ""))
			criteria.put("codigoSAP", reporteForm.getCodigoSAP());
		else 
			criteria.put("codigoSAP", null);
		
		params.put("formatoExportacion","XLS");

		return params;
	}

}