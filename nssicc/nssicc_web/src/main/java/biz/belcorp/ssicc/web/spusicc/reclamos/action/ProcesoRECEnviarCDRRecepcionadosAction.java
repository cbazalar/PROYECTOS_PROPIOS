package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECBloqueoCDRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ProcesoRECEnviarCDRRecepcionadosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoRECEnviarCDRRecepcionadosAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = 1L;
	private Map paramProcesar = new HashMap();
	private List recEnviarCDRRecepcionadosList;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}		
		ProcesoRECEnviarCDRRecepcionadosForm form = (ProcesoRECEnviarCDRRecepcionadosForm)this.formProceso;
		form.setMostrarBotonProceso(false);
		Map params = BeanUtils.describe(form);
		params = this.obtenerCriteriosBusqueda(params);
		this.paramProcesar = params;
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		List lista = reporteService.getListaProcesoRECEnviarCDRRecepcionados(params);
		if (lista.size() > 0) {
			this.mostrarBotonExecute =true;
			form.setMostrarBotonProceso(true);
			this.recEnviarCDRRecepcionadosList = lista;
			return lista;
		}
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoRECEnviarCDRRecepcionadosForm form = new ProcesoRECEnviarCDRRecepcionadosForm();
		return form;
	}

	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Exectuting action : prepareParamsBeforeExecute.");
		}
		params = super.prepareParamsBeforeExecute(params, form);
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoRECEnviarCDRRecepcionadosForm f = (ProcesoRECEnviarCDRRecepcionadosForm)this.formProceso;
		params = super.prepareParamsBeforeExecute(params, f);
		
		String fechaIngreso = DateUtil.convertDateToString(f.getFechaIngresoD());
		f.setFechaIngreso(fechaIngreso);
		ProcesoRECBloqueoCDRService service = (ProcesoRECBloqueoCDRService)getBean("spusicc.procesoRECBloqueoCDRService");
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		List lista = reporteService.getExisteListaProcesoRECEnviarCDRRecepcionados(this.paramProcesar);
		params.put("listaCDR", lista);
		params.put("usuario", usuario);
		params.put("codigoUsuario", usuario.getLogin());
		service.executeProcesoRECEnviarCDRRecepcionados(params);	
		f.setMostrarBotonProceso(false);
		this.recEnviarCDRRecepcionadosList =  new ArrayList();
		this.listaBusqueda =  new ArrayList();
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");		
		ProcesoRECEnviarCDRRecepcionadosForm f = (ProcesoRECEnviarCDRRecepcionadosForm)this.formProceso;
		f.setMostrarBotonProceso(false);
		this.mostrarBotonBuscar = true;
		this.mostrarBotonExecute = false;
		this.mostrarListaBusqueda = true;
		this.recEnviarCDRRecepcionadosList =  new ArrayList();			
	}

	/**
	 * Obtener los criterios de busqueda para generar la lista de resultados
	 * @param form
	 * @param params
	 * @return
	 */
	private Map obtenerCriteriosBusqueda(Map params) {
		
		Map criteria = new HashMap();
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		ProcesoRECEnviarCDRRecepcionadosForm f = (ProcesoRECEnviarCDRRecepcionadosForm)this.formProceso;
		
		String condicionUsuario = new String("");
		String condicionPeriodoCDR = new String("");
		String condicionPeriodoPedido = new String("");
		String condicionFechaIngreso = new String("");
		
		if (StringUtils.isNotBlank(f.getCodigoUsuario())) {
			condicionUsuario = " AND rcc.USU_INGR_RECL ='" + f.getCodigoUsuario() + "' " ;
		}
		if (StringUtils.isNotBlank(f.getCodigoCampanhaCDR())) {
			criteria.put("codigoPeriodo", f.getCodigoCampanhaCDR());
			int oidPeriodoCDR = reporteService.getOidPeriodo(criteria);
			condicionPeriodoCDR = " AND rcc.oid_peri_recl =" + oidPeriodoCDR ;
		}
		if (StringUtils.isNotBlank(f.getCodigoCampanhaPedido())) {
			criteria.put("codigoPeriodo", f.getCodigoCampanhaPedido());
			int oidPeriodo = reporteService.getOidPeriodo(criteria);
			condicionPeriodoPedido = " AND rcc.oid_peri_docu_Refe =" + oidPeriodo ;
		}
		if (StringUtils.isNotBlank(f.getFechaIngreso())) {
			condicionFechaIngreso = " AND trunc(rcc.FEC_INGR_RECL) = TO_DATE('" + f.getFechaIngreso() + "', 'dd/mm/yyyy') " ;
		}
		params.put("condicionUsuarioCDR", condicionUsuario);
		params.put("condicionPeriodoCDR", condicionPeriodoCDR);
		params.put("condicionPeriodoPedido", condicionPeriodoPedido);
		params.put("condicionFechaIngreso", condicionFechaIngreso);
		return params;
	}

	/**
	 * @return the paramProcesar
	 */
	public Map getParamProcesar() {
		return paramProcesar;
	}

	/**
	 * @param paramProcesar the paramProcesar to set
	 */
	public void setParamProcesar(Map paramProcesar) {
		this.paramProcesar = paramProcesar;
	}

	/**
	 * @return the recEnviarCDRRecepcionadosList
	 */
	public List getRecEnviarCDRRecepcionadosList() {
		return recEnviarCDRRecepcionadosList;
	}

	/**
	 * @param recEnviarCDRRecepcionadosList the recEnviarCDRRecepcionadosList to set
	 */
	public void setRecEnviarCDRRecepcionadosList(List recEnviarCDRRecepcionadosList) {
		this.recEnviarCDRRecepcionadosList = recEnviarCDRRecepcionadosList;
	}
}