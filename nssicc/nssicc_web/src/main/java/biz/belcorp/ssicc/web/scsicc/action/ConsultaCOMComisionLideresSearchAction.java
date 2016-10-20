package biz.belcorp.ssicc.web.scsicc.action;


import java.math.BigDecimal;
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
import biz.belcorp.ssicc.dao.spusicc.comision.model.ParametroTramoComision;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionGerenteZonaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaCOMComisionLideresSearchForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaCOMComisionLideresSearchAction extends BaseConsultaAbstractAction {

	private static final long serialVersionUID = -7640801582391821783L;
	private List siccComisionList;
	private List siccRegionList;
	private LabelValue[] siccZonaList;
	private List consultaCOMComisionLideresList;
	private ParametroTramoComision parametroTramo1;
	private ParametroTramoComision parametroTramo2;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaCOMComisionLideresSearchForm form = new ConsultaCOMComisionLideresSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionLideresSearchAction.setFindAttributes' method");
		}	
		
		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) 
				getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		ConsultaCOMComisionLideresSearchForm f = (ConsultaCOMComisionLideresSearchForm) this.formBusqueda;
		
		/* obteniendo valores */
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPeriodo",  f.getCodigoPeriodo());
		criteria.put("codigoComision", f.getCodigoComision());
		criteria.put("codigoComisionIngreso", f.getCodigoComisionIngreso());
		String codigoRegion = f.getCodigoRegion();
		if (StringUtils.isNotBlank(codigoRegion) && codigoRegion != "Todos" ) {
			criteria.put("codigoRegion", f.getCodigoRegion());
		}
		String codigoZona = f.getCodigoZona();
		if (StringUtils.isNotBlank(codigoZona) && codigoZona != "Todos" ) {
			criteria.put("codigoZona", f.getCodigoZona());
		}
		
		/* obteniendo id comision */
		Map criteriaID = new HashMap();
		criteriaID.put("codigoComision", f.getCodigoComision());
		Integer idComision = service.getDevuelveIDComision(criteriaID); 
		criteria.put("idComision", idComision);
		
		/* obteniendo indicadorDsctoImpuesto */
		criteriaID.put("oidComision", idComision);
		Map indicadorDsctoImpuesto = service.getIndicadoresComision(criteriaID);
		BigDecimal indicador=(BigDecimal)indicadorDsctoImpuesto.get("indicadorDsctoImpuesto");
		
		criteria.put("indicadorDsctoImpuesto", indicador);
		
		
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
	
				
		//Paso previo ejecutando sp para llenar tabla temporal 
		reporteService.executeReporteSQL("executeComisionRecuperacionSeccion", criteria);
		
		//obteniendo lista
		consultaCOMComisionLideresList = service.getComisionPeriodoLideresByCriteria(criteria);
	
		return consultaCOMComisionLideresList;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionLideresSearchAction.setViewAtributes' method");
		}
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		
		ConsultaCOMComisionLideresSearchForm f = (ConsultaCOMComisionLideresSearchForm) this.formBusqueda;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		setSiccComisionList(service.getComision());
		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		
		f.setCodigoPais(pais.getCodigo());
		f.setDescPais(pais.getDescripcion());
		f.setCodigoPeriodo(codigoPeriodo);
		
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		//Regiones
		setSiccRegionList(reporteService.getListaGenerico("getRegionesByPaisActivasNoActivas", criteriaOperacion));
		//Zonas
		setSiccZonaList(aSvc.getZonasByPais(pais.getCodigo()));
		
	}
	
	
	public void showZonasxRegion(ValueChangeEvent val){
		try{
			log.debug(">>showZonasxRegion...");
			log.debug(">>val: "+val);
			String codRegion = val.getNewValue().toString();
			String codPais = this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			siccZonaList = aSvc.getZonasByPaisActivasNoActivas(codPais, codRegion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
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

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getConsultaCOMComisionLideresList() {
		return consultaCOMComisionLideresList;
	}

	public void setConsultaCOMComisionLideresList(
			List consultaCOMComisionLideresList) {
		this.consultaCOMComisionLideresList = consultaCOMComisionLideresList;
	}

	public ParametroTramoComision getParametroTramo1() {
		return parametroTramo1;
	}

	public void setParametroTramo1(ParametroTramoComision parametroTramo1) {
		this.parametroTramo1 = parametroTramo1;
	}

	public ParametroTramoComision getParametroTramo2() {
		return parametroTramo2;
	}

	public void setParametroTramo2(ParametroTramoComision parametroTramo2) {
		this.parametroTramo2 = parametroTramo2;
	}

	
	

}
