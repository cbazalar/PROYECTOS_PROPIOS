package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;


import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteCOMComisionPagoEjecutivasForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.scdf.InterfazSiCCService;
import biz.belcorp.ssicc.web.edu.form.ReporteEDUPlanillasEmitidasForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * 
 * @author <a <href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 *
 */

@ManagedBean
@SessionScoped
public class ReporteEDUPlanillasEmitidasAction extends
		BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6438019453939507948L;
	
	
	private List eduEmpresaComercializadoraList;
	private LabelValue[] eduRegionesCursosList ={};
	private LabelValue[] eduParametrosCursoRegionList={};
	/* 
	 * Realiza la carga Inicial de la pantalla 
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.action.BaseAbstractAction#setViewAttributes(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionForm)
	 */
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception{
		ReporteEDUPlanillasEmitidasForm reporteForm = new ReporteEDUPlanillasEmitidasForm();
		return reporteForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception{
		
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteEDUPlanillasEmitidasAction.setViewAtributes' method");
		}
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteEDUPlanillasEmitidasForm f = (ReporteEDUPlanillasEmitidasForm) this.formReporte;
		f.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) 
				getBean("edu.mantenimientoEDUGenericoService");
		
		this.eduEmpresaComercializadoraList=siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		
		/*Inicializamos la Empresa, siempre despues de LoadCombos*/
		List listaEmpresa = this.eduEmpresaComercializadoraList;
		if ((listaEmpresa!=null) && (listaEmpresa.size() > 0)){
			EmpresaComercializadora empresa = new EmpresaComercializadora();
			empresa = (EmpresaComercializadora) listaEmpresa.get(0);
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());
		}
		this.eduRegionesCursosList=ajaxService.getRegionesEDUByPaisEmpresa(f.getCodigoPais(),f.getCodigoEmpresa());
		this.eduParametrosCursoRegionList=ajaxService.getCursosByPaisEmpresa(pais.getCodigo(), f.getCodigoEmpresa());
		
		f.setCampanhaProceso(getCampanhaProceso(f.getCodigoPais(),f.getCodigoEmpresa()));
	}
	
	
	

	

	/* 
	 * Devuelve el nombre del reporte
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#getReporteFileName()
	 */
	protected String devuelveNombreReporte() throws Exception{
		return "reporteMaestroVertical";
	}

	/* 
	 * Devuelve el nombre del subreporte
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseSubReporteAbstractAction#getSubReporteFileName()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteEDUPlanillasEmitidasPDF";
	}

	/* 
	 * Prepara los parametros para ser enviados en el map de objetos
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.web.framework.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteEDUPlanillasEmitidasForm reporteForm = (ReporteEDUPlanillasEmitidasForm) this.formReporte;
		
		
		/* Colocando valores al Reporte */
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
		params.put("NroReporte", "");
		params.put("titulo", getResourceMessage("reporteEDUPlanillasEmitidasForm.titulo"));
		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("codigoEmpresa", reporteForm.getCodigoEmpresa());
		params.put("codigoRegion", reporteForm.getCodigoRegion());
		params.put("codigoCurso", reporteForm.getCodigoCurso());
		params.put("campanhaProceso", reporteForm.getCampanhaProceso());
		
		return params;
		
	}

	/**
	 * Devuelve la campanha de proceso
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @return
	 */
	private String getCampanhaProceso(String codigoPais,String codigoEmpresa){
		// Obteniendo Campaa de Proceso
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) 
			getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = service.getCampannaActualProceso(criteria);
		return codigoPeriodo;
	}

	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	public LabelValue[] getEduRegionesCursosList() {
		return eduRegionesCursosList;
	}

	public void setEduRegionesCursosList(LabelValue[] eduRegionesCursosList) {
		this.eduRegionesCursosList = eduRegionesCursosList;
	}

	public LabelValue[] getEduParametrosCursoRegionList() {
		return eduParametrosCursoRegionList;
	}

	public void setEduParametrosCursoRegionList(
			LabelValue[] eduParametrosCursoRegionList) {
		this.eduParametrosCursoRegionList = eduParametrosCursoRegionList;
	}
	
	
		
	
}