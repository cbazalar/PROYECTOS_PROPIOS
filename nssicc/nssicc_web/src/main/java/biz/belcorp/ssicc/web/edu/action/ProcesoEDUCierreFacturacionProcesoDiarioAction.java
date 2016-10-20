package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.edu.form.ProcesoEDUCierreFacturacionDiarioForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ProcesoEDUCierreFacturacionProcesoDiarioAction extends BaseProcesoAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5084772924833497453L;

	
	private List cargaArchivos;
	private List eduEmpresaComercializadoraList;
	private List listaCampanhas;
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception 
	{
		ProcesoEDUCierreFacturacionDiarioForm formProceso = new ProcesoEDUCierreFacturacionDiarioForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception
	{
		log.debug("Los parametros del Reporte en el executeProcess son: " + params.toString());
		
		ProcesoEDUComercialService procesoComercial = (ProcesoEDUComercialService) getBean("edu.procesoEDUComercialService");
		String codigoPais = (String) params.get("codigoPais");
					
        // Invocando al Proceso de Carga Historica de Aptas a Facturar
		// procesoComercial.executeProcesoEDUEnviarHistoricoCapacitadas(codigoPais, params);
		procesoComercial.executeProcesoEDURecepcionarPedidosFacturados(codigoPais, params);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoEDUCierreFacturacionDiarioForm f = (ProcesoEDUCierreFacturacionDiarioForm) this.formProceso;
		
		f.setCodigoEmpresa("");
		f.setCodigoPeriodo(null);
		f.setFechaFacturacion(null);
		f.setFechaFacturacionDate(null);
		
		f.setCodigoPais(pais.getCodigo());
		loadCombos(f);
		cargarArchivoControlFacturacion(f);// carga la informacion del arcivo EDctrlfa , mas ya no vuelve a copiar el archivo de control de facturacion
		
		/* Colocando procesos a ejecutar */
		ArrayList resultado = new ArrayList();
		
		for (int i = 1; i <= 1; i++) {
			String cadena = new Integer(i).toString().trim();
			this.adicionarProceso(resultado, /*messageResources,*/ cadena,
					"procesoEDUCierreFacturacionDiarioForm.proceso0" + cadena);
		}
		this.cargaArchivos = resultado;
	}
	
	
	private void cargarArchivoControlFacturacion(ProcesoEDUCierreFacturacionDiarioForm f) throws Exception
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		/* Cargando archivo de control de Facturacion */
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoEmpresa", f.getCodigoEmpresa());
		criteria.put("usuario", usuario);
		criteria.put("noCopiarArchivos", Constants.SI);
		ProcesoEDUComercialService	procesoEDUComercialService = (ProcesoEDUComercialService) 
					getBean("edu.procesoEDUComercialService");
		procesoEDUComercialService.executeProcesoEDUCargarControlFacturacion(f.getCodigoPais(), criteria);		
	}
	
	
	/**
	 * Seteando Combos
	 * @param request
	 * @throws Exception
	 */
	private void loadCombos(ProcesoEDUCierreFacturacionDiarioForm f) throws Exception
	{
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);

		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		List listaEmpresa = siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		this.eduEmpresaComercializadoraList = listaEmpresa;

		/* Obteniendo Campaña de Proceso */
		if (listaEmpresa != null && listaEmpresa.size() > 0) 
		{
			EmpresaComercializadora empresa = (EmpresaComercializadora) listaEmpresa.get(0);
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());

			/* Obteniendo parametros del Pais y Empresa */
			ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
			parametro.setCodigoPais(f.getCodigoPais());
			parametro.setCodigoEmpresa(f.getCodigoEmpresa());
			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			parametro = siccService.getParametroCurso(parametro);
			f.setIndicadorEnvioCronograma(parametro.getIndicadorEnvioCronograma());
		}
		// Obteniendo Campaña de Proceso
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoEmpresa", f.getCodigoEmpresa());
		criteria.put("noCopiarArchivos", Constants.SI);

		String codigoPeriodo = "";
		String fechaFacturacion = "";
		ProcesoEDUComercialService procesoEDUComercialService = (ProcesoEDUComercialService) getBean("edu.procesoEDUComercialService");
		
		List listCruceCampanhas = procesoEDUComercialService.getListCampanhasActivas(criteria);
		
		if (listCruceCampanhas.size() == 1) {// una sola campanha activa
			codigoPeriodo = service.getCampannaActualProceso(criteria);
			fechaFacturacion = service.getFechaProcesoFacturacion(criteria);
		}
		
		if (listCruceCampanhas.size() > 1)
		{
			// Cruce de Campanha (2 campañas activas)
			this.listaCampanhas = listCruceCampanhas;
			Map map = (Map) listCruceCampanhas.get(0);
			codigoPeriodo = (String) map.get("codigoPeriodo");
			fechaFacturacion = (String) map.get("fechafacturacion");
		}
		
		f.setCodigoPeriodo(codigoPeriodo);
		f.setFechaFacturacion(fechaFacturacion);
		f.setFechaFacturacionDate(DateUtil.convertStringToDate(f.getFechaFacturacion()));
	}
	
	@Override
	protected String getCodigoErrorBatch(Map params) 
	{
		String codigoError = (String) params.get("codigoError");
		return codigoError;
	}
	
	
	/**
	 * Obtiene descripcion del proceso del archivo de recursos
	 * @param resultado
	 * @param messageResources
	 * @param codigo
	 * @param keyMensaje
	 */
	private void adicionarProceso(ArrayList resultado, /*MessageResources messageResources,*/ String codigo, String keyMensaje) {
		Base bean = new Base();
//		String proceso = messageResources.getMessage(keyMensaje);
		String proceso = this.getResourceMessage(keyMensaje);
		bean.setCodigo(codigo);
		bean.setDescripcion(proceso);			
		resultado.add(bean);
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map<String, Object> params, BaseForm form) throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
				
		ProcesoEDUCierreFacturacionDiarioForm f = (ProcesoEDUCierreFacturacionDiarioForm) this.formProceso;
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		f.setFechaFacturacion(DateUtil.convertDateToString(f.getFechaFacturacionDate()));
		
		params = super.prepareParamsBeforeExecute(params, form);
		
		String codigoPais = pais.getCodigo();
		params.put("codigoPais", codigoPais);
		params.put("codigoEmpresa", f.getCodigoEmpresa());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		//
		params.put("fechaFacturacion",f.getFechaFacturacion());
		//
		
		params.put("usuario", usuario);
		
		ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
		parametro.setCodigoPais(codigoPais);
		parametro.setCodigoEmpresa(f.getCodigoEmpresa());
		parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
		parametro =	siccService.getParametroCurso(parametro);
		params.put("indicadorEnvioCronograma", parametro.getIndicadorEnvioCronograma());
		
		
		return params;
	}
	
	public List getCargaArchivos() {
		return cargaArchivos;
	}

	public void setCargaArchivos(List cargaArchivos) {
		this.cargaArchivos = cargaArchivos;
	}

	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	public List getListaCampanhas() {
		return listaCampanhas;
	}

	public void setListaCampanhas(List listaCampanhas) {
		this.listaCampanhas = listaCampanhas;
	}
}
