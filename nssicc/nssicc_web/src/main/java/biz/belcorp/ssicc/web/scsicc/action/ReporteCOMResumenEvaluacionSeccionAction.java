package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
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
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMResumenEvaluacionSeccionForm;
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
public class ReporteCOMResumenEvaluacionSeccionAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 1502720306248795168L;
	private String tipoResumen;
	private List siccMarcaLista;
	private List siccCanalLista;
	private List comTipoResumenList;
	private List comTipoComisionistaList; 
	private List comTramoList;
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCOMResumenEvaluacionSeccionForm reporteForm = new ReporteCOMResumenEvaluacionSeccionForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		this.tipoResumen = ((ReporteCOMResumenEvaluacionSeccionForm) this.formReporte).getTipoResumen();
		if ("1".equals(tipoResumen))
			return "reporteCOMResumenEvaluacionSeccion";
		if ("2".equals(tipoResumen))
			return "reporteCOMResumenEvaluacionZona";		
		
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteCOMResumenEvaluacionSeccionAction.prepareParameterMap' method");
		}
		ReporteCOMResumenEvaluacionSeccionForm form = (ReporteCOMResumenEvaluacionSeccionForm) this.formReporte;
		
		this.tipoResumen = form.getTipoResumen();
		
		if ("1".equals(tipoResumen)){ //POR SECCION
			form.setTitulo(this.getReportResourceMessage("reporteCOMResumenEvaluacionSeccionForm.tituloSeccion"));
		}else{ //POR ZONA
			form.setTitulo(this.getReportResourceMessage("reporteCOMResumenEvaluacionSeccionForm.tituloZona"));
		}
	
		params.put("tipoResumen", tipoResumen);
		params.put("NroReporte", " ");
		params.put("titulo", form.getTitulo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map mapTramos = reporteService.getTramosEvaluacionResumenPorSeccion(params);
		String tituloTramo = this.getReportResourceMessage("reporteCOMResumenEvaluacionSeccionForm.evaluacion"); 
		if (mapTramos.get("Tramo1")!=null) { 
			params.put("EvaluacionTramo1", tituloTramo + " " +  mapTramos.get("Tramo1"));
			params.put("Tramo1", mapTramos.get("Tramo1"));
		}
		if (mapTramos.get("Tramo2")!=null) { 
			params.put("EvaluacionTramo2", tituloTramo + " " +  mapTramos.get("Tramo2"));
			params.put("Tramo2", mapTramos.get("Tramo2"));
		}
		if (mapTramos.get("Tramo3")!=null) { 
			params.put("EvaluacionTramo3", tituloTramo + " " +  mapTramos.get("Tramo3"));
			params.put("Tramo3", mapTramos.get("Tramo3"));
		}
		
		form.setBeforeExecuteReporte(true);	
		return params;
	}
	
	

	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCOMResumenEvaluacionSeccionService";
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'ReporteCOMComisionRecuperacionAction.setViewAtributes' method");            
        }
		ReporteCOMResumenEvaluacionSeccionForm form = (ReporteCOMResumenEvaluacionSeccionForm) this.formReporte;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		String codigoProceso = this.mPantallaPrincipalBean.getCodigoProcesoBatch();
		//String codigoSistema = this.mPantallaPrincipalBean.getCodigoSistema();
		
		/*String codigoProceso = request.getParameter("codigoProcesoBatch");
		String codigoSistema = request.getParameter("codigoSistema");
		session.setAttribute("codigoProcesoBatch", codigoProceso);	
		session.setAttribute("codigoSistema", codigoSistema);		
		*/
		this.siccMarcaLista = service.getMarcas();
		this.siccCanalLista = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.comTipoComisionistaList = tramoService.getTiposComisionistas(pais.getCodigo());
		this.comTramoList = tramoService.getTramos(pais.getCodigo());
		
		//Asignamos al codigo del periodo el valor por defecto
        Map criteria = new HashMap();
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        
        form.setCodigoProcesoBatch(codigoProceso);
        //form.setCodigoSistema(); No existe variable para asignar.
		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		form.setCodigoPeriodo(codigoPeriodo);
		form.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		form.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		
        List periodos = service.getPeriodosDefaultByPMC(criteria);
        
        if(periodos != null && periodos.size() > 0) {
            Base base = (Base)periodos.get(0);
            form.setAnioInicial(base.getCodigo().substring(0,4));
        }
        
		ArrayList resultado = new ArrayList();
		Base[] tipoResumen = new Base[2];
		String[] presentaciones = { "Resumen por Sección", "Resumen por Zona"};
		for (int i = 0; i < 2; i++) {
			tipoResumen[i] = new Base();
			tipoResumen[i].setCodigo("" + (i + 1));
			tipoResumen[i].setDescripcion(presentaciones[i]);
			resultado.add(tipoResumen[i]);
		}
		
		this.comTipoResumenList = resultado;
		//session.setAttribute(Constants.COM_TIPO_RESUMEN_LIST, resultado);
		
	}

	public String getTipoResumen() {
		return tipoResumen;
	}

	public void setTipoResumen(String tipoResumen) {
		this.tipoResumen = tipoResumen;
	}

	public List getSiccMarcaLista() {
		return siccMarcaLista;
	}

	public void setSiccMarcaLista(List siccMarcaLista) {
		this.siccMarcaLista = siccMarcaLista;
	}

	public List getSiccCanalLista() {
		return siccCanalLista;
	}

	public void setSiccCanalLista(List siccCanalLista) {
		this.siccCanalLista = siccCanalLista;
	}

	public List getComTipoResumenList() {
		return comTipoResumenList;
	}

	public void setComTipoResumenList(List comTipoResumenList) {
		this.comTipoResumenList = comTipoResumenList;
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
