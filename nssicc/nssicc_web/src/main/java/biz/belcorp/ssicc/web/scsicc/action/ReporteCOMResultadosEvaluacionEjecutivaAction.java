package biz.belcorp.ssicc.web.scsicc.action;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMResultadosEvaluacionEjecutivaForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ReporteCOMResultadosEvaluacionEjecutivaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1433183063072147618L;
	private List siccMarcaList;
	private List siccCanalList;
	private List comTipoComisionistaList;
	private List comTramoList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMResultadosEvaluacionEjecutivaForm  form = new ReporteCOMResultadosEvaluacionEjecutivaForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteCOMResultadosEvaluacionEjecutivaXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMResultadosEvaluacionEjecutivaAction.prepareParameterMap' method");
		}
		try{
			ReporteCOMResultadosEvaluacionEjecutivaForm form = (ReporteCOMResultadosEvaluacionEjecutivaForm) this.formReporte;
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
			
			String formatoExportacion = form.getFormatoExportacion();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
			String codigoPais = pais.getCodigo();
			
			form.setTitulo(this.getResourceMessage("reporteCOMResultadosEvaluacionEjecutivaForm.titulo"));
			form.setFormatoExportacion(formatoExportacion);
		
			params.put("NroReporte", "");
			params.put("titulo", this.getReportResourceMessage("reporteCOMResultadosEvaluacionEjecutivaForm.titulo"));
			params.put("formatoExportacion",formatoExportacion);		
			params.put("codigoPais", codigoPais);
			params.put("codigoCanal", form.getCodigoCanal());
			params.put("codigoMarca", form.getCodigoMarca());
			params.put("codigoTramo", form.getCodigoTramo());
			params.put("anhoInicialTramo", form.getAnioInicial());
			
			Map map = reporteService.getPeriodosResultadosEvaluacionEjecutiva(params);
			params.put("nombreColumnaTramoAnterior", String.valueOf(map.get("nombreColumnaAnterior")));
			params.put("nombreColumnaTramoActual", String.valueOf(map.get("nombreColumnaActual")));		
			params.put("codigoTramoAnterior",String.valueOf(map.get("codigoTramoAnterior")));
			params.put("anhoAnteriorTramo",String.valueOf(map.get("anhoAnteriorTramo")));
		}catch(Exception e){
			e.printStackTrace();
		}
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMResultadosEvaluacionEjecutivaAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) 
																getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		
		ReporteCOMResultadosEvaluacionEjecutivaForm f = (ReporteCOMResultadosEvaluacionEjecutivaForm) this.formReporte;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String codigoProceso = this.mPantallaPrincipalBean.getCodigoProcesoBatch();
		//String codigoSistema = this.mPantallaPrincipalBean.getCodigoSistema();
		
		setSiccMarcaList(service.getMarcas());
		setSiccCanalList(service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO()));
		
		setComTipoComisionistaList(tramoService.getTiposComisionistas(pais.getCodigo()));
		setComTramoList(tramoService.getTramos(pais.getCodigo()));
		
		f.setCodigoPais(pais.getCodigo());
		f.setDescPais(pais.getDescripcion());
		f.setCodigoCanal(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoProcesoBatch(codigoProceso);
		
		
		//Asignamos al codigo del periodo el valor por defecto
        Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        
        List periodos = service.getPeriodosDefaultByPMC(criteria);
        
        if(periodos != null && periodos.size() > 0) {
            Base base = (Base)periodos.get(0);
            f.setAnioInicial(base.getCodigo().substring(0,4));
        }
		
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

	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	public List getComTramoList() {
		return comTramoList;
	}

	public void setComTramoList(List comTramoList) {
		this.comTramoList = comTramoList;
	}
	
	
	
	
	
}
