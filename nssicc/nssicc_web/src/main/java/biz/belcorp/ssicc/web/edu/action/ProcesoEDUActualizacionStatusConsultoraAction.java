package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.util.MessageResources;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUActualizacionStatusConsultoraService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.edu.form.ProcesoEDUActualizacionStatusConsultoraForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;


@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ProcesoEDUActualizacionStatusConsultoraAction  extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6327438914514810358L;
	private LabelValue[] regionesList;
	private List listaEmpresa;
	private List resultados;
	
	
	/**
	 * @return the resultados
	 */
	public List getResultados() {
		return resultados;
	}

	/**
	 * @param resultados the resultados to set
	 */
	public void setResultados(List resultados) {
		this.resultados = resultados;
	}

	/**
	 * @return the regionesList
	 */
	public LabelValue[] getRegionesList() {
		return regionesList;
	}

	/**
	 * @param regionesList the regionesList to set
	 */
	public void setRegionesList(LabelValue[] regionesList) {
		this.regionesList = regionesList;
	}

	/**
	 * @return the listaEmpresa
	 */
	public List getListaEmpresa() {
		return listaEmpresa;
	}

	/**
	 * @param listaEmpresa the listaEmpresa to set
	 */
	public void setListaEmpresa(List listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoEDUActualizacionStatusConsultoraForm objForm = new ProcesoEDUActualizacionStatusConsultoraForm();
		return objForm;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Los parametros del Proceso GenerarPlanilla en el executeProcess son: "
				+ params);
		
		ProcesoEDUActualizacionStatusConsultoraService service = (ProcesoEDUActualizacionStatusConsultoraService)getBean("edu.procesoEDUActualizacionStatusConsultoraService");
		
        // Invocando al Proceso de Actualización de Planillas Programación  
		//Esto simrpe y cuando el parametro de Niv Administartivo este activado
		 service.executeActualizacionStatusConsultora(params);
		 setMensajeError(params);//lo que proviend e oracle es un keymesssage
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		try {
			//super.setViewAttributes(request, form);
			
			//HttpSession session = request.getSession(true);
			//session.removeAttribute(Constants.SICC_REPORTE_LOG_LIST);	
			
			ProcesoEDUActualizacionStatusConsultoraForm f = (ProcesoEDUActualizacionStatusConsultoraForm) this.formProceso;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			f.setCodigoPais(pais.getCodigo());
			loadCombos(null);
			/*Inicializamos la Empresa, siempre despues de LoadCombos*/
			//List listaEmpresa = (List) session.getAttribute(Constants.EDU_EMPRESA_COMERCIALIZADORA_LIST);
			if ((listaEmpresa!=null) && (listaEmpresa.size() > 0)){
				EmpresaComercializadora empresa = new EmpresaComercializadora();
				empresa = (EmpresaComercializadora) listaEmpresa.get(0);
				f.setCodigoEmpresa(empresa.getCodigoEmpresa());
			}
			f.setCampanhaProceso(getCampanhaProceso(f.getCodigoPais(),f.getCodigoEmpresa()));
			
			//CARGANDO LAS REGIONES
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			
			this.regionesList = ajaxService.getRegionesEDUByPaisEmpresa(f.getCodigoPais(), f.getCodigoEmpresa());
			
			
			//Adiconar Lista de Procesos - Sergio Buchelli		
			/* Carga Prametros */
			 
			setListProcesosPorMostrar(null, f);
			if(this.resultados.size() >0){
				Base bean = new Base();
				bean = (Base) this.resultados.get(0);
				
				String mensaje = this.getResourceMessage(bean.getDescripcion());
				String n = bean.getCodigo()+"  "+ mensaje;
				f.setProceso(n);
			}
		}
		catch (Exception e) {
			String error = e.getMessage();
			this.getResourceMessage("errors.detail", new Object[]{error});
		}
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
		ProcesoEDUActualizacionStatusConsultoraForm f = (ProcesoEDUActualizacionStatusConsultoraForm) form;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String codigoPais = pais.getCodigo();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		
		Map pms = new HashMap();
		pms.put("codigoPais", codigoPais);
		pms.put("codigoEmpresa", f.getCodigoEmpresa());
		pms.put("usuario", usuario);
		pms.put("regionList",
                (f.getRegiones() == null) ? new ArrayList()
                                          : Arrays.asList(f.getRegiones()));
		pms.put("codigoPeriodo",f.getCampanhaProceso());
		pms.put("codigoProceso", Constants.EDU_PARAM_PROC_REGION);
		pms.put("usuarioLogin",usuario.getLogin());
		return pms;
	}
	
	/**
	 * @param request
	 * @param f
	 */
	private void setListProcesosPorMostrar(HttpServletRequest request, ProcesoEDUActualizacionStatusConsultoraForm f) {
		/* Colocando procesos a ejecutar */
		ArrayList resultado = new ArrayList();
		
		for(int i=1, j=1; i <= 1; i++ ) {
			String cadena = new Integer(i).toString().trim();
			String cadenaMostrar = new Integer(j).toString().trim();
			switch (i) {
			case 1: 
				 this.adicionarProceso(resultado, null, cadenaMostrar, "procesoEDUActualizacionStatusConsultoraForm.proceso0" + cadena);
				 j++;
				 break;
			}
		}	
		
	}
	
	/**
	 * Seteando Combos
	 * @param request
	 * @throws Exception
	 */
	private void loadCombos(HttpServletRequest request)throws Exception {
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		this.listaEmpresa = siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		//session.setAttribute(Constants.EDU_EMPRESA_COMERCIALIZADORA_LIST, listaEmpresa);
	}
	
	private String getCampanhaProceso(String codigoPais,String codigoEmpresa){
		// Obteniendo Campaña de Proceso
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) 
				getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = service.getCampannaActualProceso(criteria);
		return codigoPeriodo;
	}
	
	
	/**
	 * Obtiene descripcion del proceso del archivo de recursos
	 * @param resultado
	 * @param messageResources
	 * @param codigo
	 * @param keyMensaje
	 */
	private void adicionarProceso(ArrayList resultado, MessageResources messageResources, String codigo, String keyMensaje) {
		Base bean = new Base();
		bean.setCodigo(codigo);
		bean.setDescripcion(keyMensaje);	
		this.resultados = new ArrayList();
		this.resultados.add(bean);
		resultado.add(bean);
	}
	
	private void setMensajeError(Map params) {
		String keyMensaje = (String) params.get("mensajeError");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		log.debug("keyMessage "+keyMensaje);
		params.put("mensajeError",keyMensaje);
		
		if (StringUtils.isNotEmpty(keyMensaje)){
			
			String periodo=ajaxService.getCampannaActualEDUByPaisEmpresa((String) params.get("codigoPais"),(String) params.get("codigoEmpresa"));
			String mensaje = this.getResourceMessage("errors.detail", new Object[]{periodo}); //messageResources.getMessage(keyMensaje,periodo);
			
			if(StringUtils.isNotEmpty(mensaje)){
				params.put("mensajeError",mensaje);
				this.setMensajeAlertaDefault(mensaje);
			}
			else{
				params.put("mensajeError",keyMensaje);	
				this.setMensajeAlertaDefault(keyMensaje);
			}
			
			this.getRequestContext().execute("PF('principalFormAlert_alertDialog').show()");
		}
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction#setErrorLogicaNegocio(java.util.Map)
	 */
	protected String setErrorLogicaNegocio(Map params) {
		String mensaje = (String) params.get("mensajeError");
		if (StringUtils.isBlank(mensaje)) 
			return null;
		return mensaje;
	}

	
}
