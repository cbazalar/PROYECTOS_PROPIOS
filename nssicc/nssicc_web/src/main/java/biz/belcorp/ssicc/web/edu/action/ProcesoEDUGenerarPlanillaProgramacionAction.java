package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUGenerarPlanillaProgramacionService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.edu.form.ProcesoEDUGenerarPlanillaProgramacionForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;


@ManagedBean
@SessionScoped
public class ProcesoEDUGenerarPlanillaProgramacionAction extends BaseProcesoAbstractAction{

	
	private static final long serialVersionUID = 2675155434931653091L;
	
	private List eduEmpresaComercializadoraList;
	private List eduTipoProcesoList;
	private List cargaArchivos;
	private LabelValue[] eduRegionesCursosList;

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoEDUGenerarPlanillaProgramacionForm procesoForm =new ProcesoEDUGenerarPlanillaProgramacionForm();		
		return procesoForm;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {		
		ProcesoEDUGenerarPlanillaProgramacionForm f = (ProcesoEDUGenerarPlanillaProgramacionForm) this.formProceso;
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
			
		f.setCodigoPais(pais.getCodigo());
		loadCombos();
			
			/*Inicializamos la Empresa, siempre despues de LoadCombos*/
		List listaEmpresa = this.eduEmpresaComercializadoraList;
		if ((listaEmpresa!=null) && (listaEmpresa.size() > 0)){			
			EmpresaComercializadora empresa = new EmpresaComercializadora();
			empresa = (EmpresaComercializadora) listaEmpresa.get(0);
				
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());
		}
		f.setTipoProceso(Constants.EDU_TIPO_PROCESO_NORMAL);
		f.setCampanhaProceso(getCampanhaProceso(f.getCodigoPais(),f.getCodigoEmpresa()));		
			//Adiconar Lista de Procesos - Sergio Buchelli
		
			/* Carga Prametros */
		cargaParametros();
		setListProcesosPorMostrar();	
	
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("Los parametros del Proceso GenerarPlanilla en el executeProcess son: "
				+ params);
		ProcesoEDUGenerarPlanillaProgramacionForm f = (ProcesoEDUGenerarPlanillaProgramacionForm) this.formProceso;
		ProcesoEDUGenerarPlanillaProgramacionService service = 
			(ProcesoEDUGenerarPlanillaProgramacionService)getBean("edu.procesoEDUGenerarPlanillaProgramacionService");
		
        // Invocando al Proceso de Actualización de Planillas Programación  
		//Esto simrpe y cuando el parametro de Niv Administartivo este activado
		log.debug("Unudad Administartiva " + f);
		if (!Constants.EDU_PARAM_CURSO_NO_GENERA_PLANILLA.equals(f.getNivelUnidadAdm())){ 
		 service.executeGenerarPlanillaProgramacion(params);
		 //setMensajeError(params,request);//lo que proviend e oracle es un keymesssage
		} 
		
		log.debug("== >  executeGenerarPlanillaProgramacion ");
		return params;
	}
	
	/**
	 * Seteando Combos	 
	 */
	private void loadCombos()throws Exception {		
		
		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();	
		AjaxService ajax= (AjaxService) getBean("ajaxService");
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		List listaEmpresa = siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		this.eduEmpresaComercializadoraList=listaEmpresa;		
		
		this.eduRegionesCursosList=ajax.getRegionesEDUByPaisEmpresa(pais.getCodigo(),parametroEmpresa.getCodigoEmpresa());
		
		/* Tipos de Proceso */
		List resultado =loadProcesos(); 
		this.eduTipoProcesoList=resultado;		
	}
	
	private List loadProcesos() {
		List resultado=new ArrayList();
		Base[] mes = new Base[3];
		
		String mensajeTipoEnvioNormal = this.getResourceMessage("procesoEDUGenerarPlanillaProgramacionForm.tipoProcesoNormal");			
		String mensajeTipoProcesoReproceso =this.getResourceMessage("procesoEDUGenerarPlanillaProgramacionForm.tipoProcesoReproceso");	
		String mensajeTipoEnvioReenvio =this.getResourceMessage("procesoEDUGenerarPlanillaProgramacionForm.tipoProcesoReenvio");				
	
		mes[0] = new Base();
		mes[0].setCodigo(Constants.EDU_TIPO_PROCESO_NORMAL);
		mes[0].setDescripcion(mensajeTipoEnvioNormal);
		resultado.add(mes[0]);
		
		mes[1] = new Base();
		mes[1].setCodigo(Constants.EDU_TIPO_PROCESO_REPROCESO);
		mes[1].setDescripcion(mensajeTipoProcesoReproceso);
		resultado.add(mes[1]);
		
		mes[2] = new Base();
		mes[2].setCodigo(Constants.EDU_TIPO_PROCESO_REENVIO);
		mes[2].setDescripcion(mensajeTipoEnvioReenvio);
		resultado.add(mes[2]);
		
		return resultado;
	}
	
	private String getCampanhaProceso(String codigoPais,String codigoEmpresa){
		// Obteniendo Campańa de Proceso
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) 
				getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = service.getCampannaActualProceso(criteria);
		return codigoPeriodo;
	}
	
	private void cargaParametros() {
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		ProcesoEDUGenerarPlanillaProgramacionForm f = (ProcesoEDUGenerarPlanillaProgramacionForm) this.formProceso;
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(f.getCodigoPais());
		parametro.setCodigoEmpresa(f.getCodigoEmpresa());
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro =	service.getParametroCurso(parametro);
		f.setNivelUnidadAdm(parametro.getNivelUnidadAdm());
		f.setIndicadorEnvioProgramadas(parametro.getIndicadorEnvioProgramadas());
		f.setIndicadorEnvioResumen(parametro.getIndicadorEnvioResumen());
		f.setIndicadorConsultoraRezagada(parametro.getIndicadorConsultoraRezagada());
	}

	private void setListProcesosPorMostrar() {		
		/* Colocando procesos a ejecutar */
		ProcesoEDUGenerarPlanillaProgramacionForm f = (ProcesoEDUGenerarPlanillaProgramacionForm) this.formProceso;
		ArrayList resultado = new ArrayList();		
		for(int i=1, j=1; i <= 6; i++ ) {
			String cadena = new Integer(i).toString().trim();
			String cadenaMostrar = new Integer(j).toString().trim();
			switch (i) {
			case 1: 
				if (Constants.NUMERO_UNO.equals(f.getIndicadorConsultoraRezagada())) { 
				 this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
				 j++;
				} 
				 break;
			case 2: 
				 this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
				 j++;
				 break;

			case 3: 
				 this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
				 j++;
				 break;
				 
			case 4: 
				if (!Constants.EDU_PARAM_CURSO_NO_GENERA_PLANILLA.equals(f.getNivelUnidadAdm())) { 
					this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
					j++;
				}
				break;
			case 5:
				if (Constants.EDU_INDICADOR_ENVIO_PROGRAMADAS_SI.equals(f.getIndicadorEnvioProgramadas())) { 
					this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
					j++;
				}
				break;
			case 6:
				if (Constants.EDU_INDICADOR_ENVIO_RESUMEN_SI.equals(f.getIndicadorEnvioResumen())) { 
					this.adicionarProceso(resultado, cadenaMostrar, "procesoEDUGenerarPlanillaProgramacionForm.proceso0" + cadena);
					j++;
				}
				break;	
			}
				
		}		
		this.cargaArchivos=resultado;
	}
	
	private void adicionarProceso(ArrayList resultado, String codigo, String keyMensaje) {
		Base bean = new Base();
		String proceso = this.getResourceMessage(keyMensaje);
		bean.setCodigo(codigo);
		bean.setDescripcion(proceso);			
		resultado.add(bean);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoHiloAbstractAction#prepareParamsBeforeExecute(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}		
		ProcesoEDUGenerarPlanillaProgramacionForm f = (ProcesoEDUGenerarPlanillaProgramacionForm) this.formProceso;		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params = super.prepareParamsBeforeExecute(params, f);
		params.put("codigoProceso", f.getCodigoProcesoBatch());
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoEmpresa", f.getCodigoEmpresa());
		params.put("usuario", usuario);
		params.put("tipoProceso", f.getTipoProceso());		
		params.put("regionList",
                (f.getRegiones() == null) ? new ArrayList()
                                          : Arrays.asList(f.getRegiones()));
		params.put("codigoPeriodo",f.getCampanhaProceso());
		params.put("indicadorConsultoraRezagada",f.getIndicadorConsultoraRezagada());
		//se usa este parametro por reutizacion de un proceso anhadido en la geeracion de planilla
		params.put("usuarioLogin",usuario.getLogin());
		return params;
	}

	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	public List getEduTipoProcesoList() {
		return eduTipoProcesoList;
	}

	public void setEduTipoProcesoList(List eduTipoProcesoList) {
		this.eduTipoProcesoList = eduTipoProcesoList;
	}

	public List getCargaArchivos() {
		return cargaArchivos;
	}

	public void setCargaArchivos(List cargaArchivos) {
		this.cargaArchivos = cargaArchivos;
	}

	public LabelValue[] getEduRegionesCursosList() {
		return eduRegionesCursosList;
	}

	public void setEduRegionesCursosList(LabelValue[] eduRegionesCursosList) {
		this.eduRegionesCursosList = eduRegionesCursosList;
	}	
	
	
	
	


	

}
