package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteFLXCuentasProcesadasForm;

@ManagedBean
@SessionScoped
public class ReporteFLXCuentasProcesadasAction extends
		BaseReporteAbstractAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1287345381112301276L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] flxEstadosList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteFLXCuentasProcesadasForm form = new ReporteFLXCuentasProcesadasForm();
		return form;
	}


	@Override
	protected void setViewAtributes() throws Exception {

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteria);

		LabelValue[] estadoList = null;
		estadoList = new LabelValue[3];
		estadoList[0] = new LabelValue("ACTIVA", "A");
		estadoList[1] = new LabelValue("INACTIVA", "I");
		estadoList[2] = new LabelValue("CANCELADA (Desactivada por SAC)", "C");
		this.flxEstadosList = estadoList;

	}

	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

		String[] valores = (String[]) val.getNewValue();
		if (!val.equals(null) && valores.length > 0) {
			String[] regiones = (String[]) val.getNewValue();

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList = aSvc
					.getZonasMultipleByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones,
							Constants.FORMATO_TOTAL);

		} else {
			setSiccZonaList(null);
			setSiccSeccionList(null);

		}

	}

	//
	public void loadseccion(ValueChangeEvent val) {
		log.debug("loadseccion");
		ReporteFLXCuentasProcesadasForm form = (ReporteFLXCuentasProcesadasForm) this.formReporte;
		String[] regiones = (String[]) form.getRegionList();
		String[] zonas = (String[]) val.getNewValue();
		if (regiones.length > 0 && zonas.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccSeccionList(aSvc
					.getSeccionMultipleByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, regiones, zonas,
							Constants.FORMATO_TOTAL));

		} else {
			setSiccSeccionList(null);

		}

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)) {
			return "reporteFLXCuentasProcesadasXLS";
		} else {
			return "reporteMaestroHorizontal";
		}

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteFLXCuentasProcesadasForm f = (ReporteFLXCuentasProcesadasForm) this.formReporte;
		formatoReporte = f.getFormatoExportacion();
		
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		f.setFechaInicio("");
		f.setFechaFin("");
		if(f.getFechaInicioD()!=null){
			f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		}
		if(f.getFechaFinD()!=null){
			f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		}
		//this.setVirtualizador(true);
		
		String condicionRegion = this.obtieneCondicion(f.getRegionList(), "ZR.COD_REGI", "'");
		params.put("condicionRegion", condicionRegion);
		log.debug("condicionRegion"+condicionRegion.toString());
		
		String condicionZona = this.obtieneCondicion(f.getZonaList(), "ZZ.COD_ZONA", "'");
		params.put("condicionZona", condicionZona);
		log.debug("condicionZona"+condicionZona.toString());
		
		String condicionSeccion = this.obtieneCondicion(f.getSeccionList(), "ZS.COD_SECC", "'");
		params.put("condicionSeccion", condicionSeccion);
		log.debug("condicionSeccion"+condicionSeccion.toString());
		
		if(StringUtils.isNotBlank(f.getCodigoCampana())){
			params.put("codigoCampana", f.getCodigoCampana());
			}
		else 
			params.put("codigoCampana", null);
		
		if(StringUtils.isNotBlank(f.getIndicadorContrato())){
			params.put("indicadorContrato", StringUtils.equals(f.getIndicadorContrato(),"1")?"SI":"NO");
			}
		else 
			params.put("indicadorContrato", null);
		
		if(StringUtils.isNotBlank(f.getFechaInicio())){
			params.put("fechaInicio", f.getFechaInicio());
			}
		else 
			params.put("fechaInicio", null);
		
		if(StringUtils.isNotBlank(f.getFechaFin())){
			params.put("fechaFin", f.getFechaFin());
			}
		else 
			params.put("fechaFin", null);
		
		if(StringUtils.isNotBlank(f.getTipoCuentaFlexipagoList())){
			if(StringUtils.equals(f.getTipoCuentaFlexipagoList(), "C"))
			{
				params.put("tipoCuentaFlexipago", " and ch.ind_canc = 1 ");
			}else{
				if(StringUtils.equals(f.getTipoCuentaFlexipagoList(), "I"))
				{
					params.put("tipoCuentaFlexipago", " and ch.ind_canc = 0 and ch.ind_acti = 0 ");
				}else{
					if(StringUtils.equals(f.getTipoCuentaFlexipagoList(), "A"))
					{
						params.put("tipoCuentaFlexipago", " and ch.ind_canc = 0 and ch.ind_acti = 1 ");
					}else
						params.put("tipoCuentaFlexipago", "");
				}
			}
			}
			else {
				params.put("tipoCuentaFlexipago", "");
			}
		
		params.put("NroReporte", "");				
		
		String titulo = getReportResourceMessage("reporteFLXCuentasProcesadas.titulo");
		params.put("titulo", titulo );

		return params;
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

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}


	/**
	 * @return the flxEstadosList
	 */
	public LabelValue[] getFlxEstadosList() {
		return flxEstadosList;
	}


	/**
	 * @param flxEstadosList the flxEstadosList to set
	 */
	public void setFlxEstadosList(LabelValue[] flxEstadosList) {
		this.flxEstadosList = flxEstadosList;
	}
	
	
}
