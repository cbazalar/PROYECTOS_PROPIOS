package biz.belcorp.ssicc.web.scsicc.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroTramoComision;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionGerenteZonaService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaCOMComisionGerenteZonaSearchForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */

@ManagedBean
@SessionScoped
public class ConsultaCOMComisionGerenteZonaSearchAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	private ParametroTramoComision parametroTramoComision1;
	private ParametroTramoComision parametroTramoComision2;
	private List siccComisionList;
	private List siccRegionList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ConsultaCOMComisionGerenteZonaSearchForm form = new ConsultaCOMComisionGerenteZonaSearchForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ConsultaCOMComisionGerenteZonaSearchForm form = (ConsultaCOMComisionGerenteZonaSearchForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(form.getFormatoExportacion())) {
			return "reporteCOMComisionGerenteZonaXLS";
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
	protected Map prepareParameterMap(Map params) throws Exception {
		ConsultaCOMComisionGerenteZonaSearchForm reporteForm = (ConsultaCOMComisionGerenteZonaSearchForm) this.formReporte;
		
		String codigoRegion = reporteForm.getCodigoRegion();
		if (StringUtils.isNotBlank(codigoRegion)){
			params.put("codigoRegion", " AND A.COD_REGI = '" + codigoRegion + "'");
		}else{
			params.put("codigoRegion", " " );
		}
		params.put("codigoComision", obtieneCondicion(reporteForm.getCodigoComision(), "A.COD_COMI", "'"));
		params.put("codigoPeriodoIni", reporteForm.getCodigoPeriodoIni());
		params.put("codigoPeriodoFin", reporteForm.getCodigoPeriodoFin());
		
		return params;
	}
	

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteZonaSearchAction.setFindAttributes' method");
		}	
		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService)getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		ConsultaCOMComisionGerenteZonaSearchForm f = (ConsultaCOMComisionGerenteZonaSearchForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		List resultado = new ArrayList();
		/* obteniendo valores */
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPeriodoIni",  f.getCodigoPeriodoIni());
		
		if(StringUtils.equals(f.getCodigoPais(), "PE")){
	
		
		criteria.put("codigoPeriodoFin",  f.getCodigoPeriodoFin());
		String codigoRegion = f.getCodigoRegion();
		
		if (StringUtils.isNotBlank(codigoRegion) && codigoRegion != "Todos" ) {
			criteria.put("codigoRegion", f.getCodigoRegion());
		}
		
		
		Map criteriaID = null;
		for(int i = 0; i < f.getCodigoComision().length; i++){
			criteria.put("codigoComision", f.getCodigoComision()[i]);
			criteriaID = new HashMap();
			criteriaID.put("codigoComision", f.getCodigoComision()[i]);
			Integer idComision = service.getDevuelveIDComision(criteriaID); 
			criteria.put("idComision", idComision);
			
			/* Obteniendo Parametros Tramo 1 */
			criteria.put("numeroTramo", new Integer(1));
			setParametroTramoComision1(service.getParametrosTramoComision(criteria));
			if (getParametroTramoComision1() == null) {
				setParametroTramoComision1(new ParametroTramoComision());
			}
			
			/* Obteniendo Parametros Tramo 2 */
			criteria.put("numeroTramo", new Integer(2));
			setParametroTramoComision2(service.getParametrosTramoComision(criteria));
			if (getParametroTramoComision2() == null) {
				setParametroTramoComision2(new ParametroTramoComision());
			}
			
			/* Obteniendo Lista */
			if(CollectionUtils.isEmpty(resultado)){
				resultado = new java.util.ArrayList();
			}
			resultado.addAll(service.getComisionPeriodoGerenteZonaByCriteria(criteria));
		}
	
		}else if(StringUtils.equals(f.getCodigoPais(), "CLE")){
			
		}else if(StringUtils.equals(f.getCodigoPais(), "COL")){
			reporteService.executeCalcularVentaZona(criteria);
		}		
		
		return resultado;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#view()
	 */
	@Override
	@PostConstruct
	public void view() {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		GenericoService genericoService = (GenericoService) getBean("genericoService");
		this.exportarDataTableBusqueda = false;
		
		try {
			this.parametrosPantalla = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String codigoMenu = (String) this.parametrosPantalla.get("codigoMenu");
			if(StringUtils.isNotBlank(codigoMenu)) {
				this.codigoMenu = codigoMenu;
				this.mPantallaPrincipalBean.setCurrentMenu(this.codigoMenu);
			}
			
	        ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(pais.getCodigo());
			parametroPais.setCodigoSistema(Constants.COM_CODIGO_SISTEMA);
			parametroPais.setCodigoParametro(Constants.INDICADOR_CONSULTA_COMISION_GERENTE_ZONA);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			List lstParametros = genericoService.getParametrosPais(parametroPais);
			
			ParametroPais parametro = null;
			if(CollectionUtils.size(lstParametros)==1){
				parametro = (ParametroPais) lstParametros.get(0);
				
				if(StringUtils.equals(parametro.getValorParametro(), "02")){
					this.redireccionarPagina("consultaCOMComisionGerenteZonaEscalonadaList");
				}		
				else if(StringUtils.equals(parametro.getValorParametro(), "03")){
					this.redireccionarPagina("procesoCOMComisionGerenteZona02Form");
				}else{
					this.viewLogicaNegocio();
				}
			}
			
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteZonaSearchAction.setViewAtributes' method");
		}
		
		this.mostrarListaBusqueda = true;
		this.mostrarBotonBuscar = true;
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService)getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		ConsultaCOMComisionGerenteZonaSearchForm f = (ConsultaCOMComisionGerenteZonaSearchForm) this.formReporte;
		
		
		Map criteria = new HashMap();
		criteria.put("codigoTipoComision", Constants.COM_COMISION_TIPO_COMISION_GERENTE_ZONA);
		criteria.put("indicadorEscalonada", Constants.COM_COMISION_INDICADOR_ESCALONADA_CERO);
		
		setSiccComisionList(tramoService.getComisionByTipo(criteria));
		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setDescPais(this.mPantallaPrincipalBean.getCurrentCountry().getDescripcion());
		
		f.setCodigoPeriodoIni(codigoPeriodo);
		f.setCodigoPeriodoFin(codigoPeriodo);
		
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		
		setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion));
		
	}	
	
	
	/**
	 * @param val
	 */
	public void showListaComision(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+val.getNewValue().toString());
		
		ConsultaCOMComisionGerenteZonaSearchForm f = (ConsultaCOMComisionGerenteZonaSearchForm) this.formReporte;
		f.setCodigoComision(null)	;
	}
	
	

	public List getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public ParametroTramoComision getParametroTramoComision1() {
		return parametroTramoComision1;
	}

	public void setParametroTramoComision1(
			ParametroTramoComision parametroTramoComision1) {
		this.parametroTramoComision1 = parametroTramoComision1;
	}

	public ParametroTramoComision getParametroTramoComision2() {
		return parametroTramoComision2;
	}

	public void setParametroTramoComision2(
			ParametroTramoComision parametroTramoComision2) {
		this.parametroTramoComision2 = parametroTramoComision2;
	}

	public List getSiccComisionList() {
		return siccComisionList;
	}

	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

}
