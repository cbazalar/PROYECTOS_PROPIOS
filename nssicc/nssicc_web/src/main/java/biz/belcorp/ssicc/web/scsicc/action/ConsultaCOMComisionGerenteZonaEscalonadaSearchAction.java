package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroTramoComision;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionGerenteZonaService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaCOMComisionGerenteZonaEscalonadaSearchForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaCOMComisionGerenteZonaEscalonadaSearchAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private List siccComisionList;
	private List siccRegionList;
	private List consultaCOMComisionGerenteZonaEscalonadaList;
	private ParametroTramoComision parametroTramo1;
	private ParametroTramoComision parametroTramo2;	


	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaCOMComisionGerenteZonaEscalonadaSearchForm form = new ConsultaCOMComisionGerenteZonaEscalonadaSearchForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ConsultaCOMComisionGerenteZonaEscalonadaSearchForm form = (ConsultaCOMComisionGerenteZonaEscalonadaSearchForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
	
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteCOMComisionGteZonaEscXLS";
		} 
		else {
			return "reporteMaestroHorizontal";
		} 
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteZonaEscalonadaSearchAction.setFindAttributes' method");
		}	

		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		ConsultaCOMComisionGerenteZonaEscalonadaSearchForm f = (ConsultaCOMComisionGerenteZonaEscalonadaSearchForm) this.formReporte;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPeriodoIni",  f.getCodigoPeriodoIni());
		criteria.put("codigoPeriodoFin",  f.getCodigoPeriodoFin());
		
		String codigoRegion = f.getCodigoRegion();
		
		if (StringUtils.isNotBlank(codigoRegion) && codigoRegion != "Todos" ) {
			criteria.put("codigoRegion", f.getCodigoRegion());
		}
		consultaCOMComisionGerenteZonaEscalonadaList = ListUtils.EMPTY_LIST;
		
		Map criteriaID = null;
		
		for(int i = 0; i < f.getCodigoComision().length; i++){
			criteria.put("codigoComision", f.getCodigoComision()[i]);
			criteriaID = new HashMap();
			criteriaID.put("codigoComision", f.getCodigoComision()[i]);
			Integer idComision = service.getDevuelveIDComision(criteriaID); 
			criteria.put("idComision", idComision);
			
			/* Obteniendo Parametros Tramo 1 */
			criteria.put("numeroTramo", new Integer(1));
			parametroTramo1 = service.getParametrosTramoComision(criteria);
			if (parametroTramo1 == null) {
				parametroTramo1 = new ParametroTramoComision();
			}
			
			/* Obteniendo Parametros Tramo 2 */
			criteria.put("numeroTramo", new Integer(2));
			parametroTramo2 = service.getParametrosTramoComision(criteria);
			if (parametroTramo2 == null) {
				parametroTramo2 = new ParametroTramoComision();
			}
			
			/* Obteniendo Lista */
			consultaCOMComisionGerenteZonaEscalonadaList = new ArrayList();
			consultaCOMComisionGerenteZonaEscalonadaList.addAll(service.getComisionPeriodoGerenteZonaEscalonadaByCriteria(criteria));
		}

		return consultaCOMComisionGerenteZonaEscalonadaList;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteZonaEscalonadaSearchAction.setViewAtributes' method");
		}
		
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		this.exportarDataTableBusqueda = false;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService)getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		ConsultaCOMComisionGerenteZonaEscalonadaSearchForm f = (ConsultaCOMComisionGerenteZonaEscalonadaSearchForm) this.formReporte;
		
		Map criteria = new HashMap();
		//criteria.put("codigoTipoComision", Constants.COM_COMISION_TIPO_COMISION_GERENTE_ZONA);
		//criteria.put("indicadorEscalonada", Constants.COM_COMISION_INDICADOR_ESCALONADA_UNO);
		String[] codigoBase = new String[1];
		codigoBase[0] = "01";
		criteria.put("codigoBase", codigoBase);
		setSiccComisionList(tramoService.getComisionByTipo(criteria));
		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodoIni(codigoPeriodo);
		f.setCodigoPeriodoFin(codigoPeriodo);
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion));
			
		log.debug("Todo Ok: Redireccionando");	
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ConsultaCOMComisionGerenteZonaEscalonadaSearchForm  reporteForm = (ConsultaCOMComisionGerenteZonaEscalonadaSearchForm)  this.formReporte;
		
		String codigoRegion = reporteForm.getCodigoRegion();

		if (StringUtils.isNotBlank(codigoRegion)){
			params.put("condicionRegion", " AND A.COD_REGI = '" + codigoRegion + "'");
		}else{
			params.put("condicionRegion", " " );
		}
		
		params.put("codigoComision", obtieneCondicion(reporteForm.getCodigoComision(), "A.COD_COMI", "'"));
		params.put("codigoPeriodoIni", reporteForm.getCodigoPeriodoIni());
		params.put("codigoPeriodoFin", reporteForm.getCodigoPeriodoFin());
		
		return params;
	}

	public List getSiccComisionList() {
		return siccComisionList;
	}

	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getConsultaCOMComisionGerenteZonaEscalonadaList() {
		return consultaCOMComisionGerenteZonaEscalonadaList;
	}

	public void setConsultaCOMComisionGerenteZonaEscalonadaList(
			List consultaCOMComisionGerenteZonaEscalonadaList) {
		this.consultaCOMComisionGerenteZonaEscalonadaList = consultaCOMComisionGerenteZonaEscalonadaList;
	}
}