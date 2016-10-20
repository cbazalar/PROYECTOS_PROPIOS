package biz.belcorp.ssicc.web.edu.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.springframework.dao.DataAccessException;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.edu.form.InterfazDATEnviarArchivosEducacionForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;


@ManagedBean
@SessionScoped
public class InterfazDATEnviarArchivosEducacionAction  extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5624318101901890890L;
	private List eduEmpresaComercializadoraList;
	private LabelValue[] eduParametrosRegionList={};
	private List eduTipoEnvioInterfaceDatamartList;
	private String indicadorSistema;
	private String indicadorCierre;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazDATEnviarArchivosEducacionForm formInterfaz = new  InterfazDATEnviarArchivosEducacionForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		  if (log.isDebugEnabled()) {
	            log.debug("Entering 'setViewAttributes' method");
	        }
	        
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			InterfazDATEnviarArchivosEducacionForm f = (InterfazDATEnviarArchivosEducacionForm) this.formInterfaz;
			f.setCodigoPais(pais.getCodigo());
			
			/* Encontrando la empresa */
			EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
			parametroEmpresa.setCodigoPais(pais.getCodigo());
			parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
			
			MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
			List listaEmpresa = siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
			this.eduEmpresaComercializadoraList=listaEmpresa;
	        
			if (listaEmpresa != null && listaEmpresa.size() > 0) {
				EmpresaComercializadora empresa = (EmpresaComercializadora) listaEmpresa.get(0);
				ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");
				f.setCodigoEmpresa(empresa.getCodigoEmpresa());
				
				// Obteniendo Campaña de Proceso 
				Map criteria = new HashMap();
				criteria.put("codigoPais", f.getCodigoPais());
				criteria.put("codigoEmpresa", f.getCodigoEmpresa());
				String codigoPeriodo = service.getCampannaActualProceso(criteria);
				f.setCodigoPeriodo(codigoPeriodo);
			}
			
			/* Encontrando Regiones */
			RegionCursoCapacitacion regionCursoCapacitacion = new RegionCursoCapacitacion();
			regionCursoCapacitacion.setCodigoPais(pais.getCodigo());
			regionCursoCapacitacion.setCodigoEmpresa(f.getCodigoEmpresa());
			LabelValue[] regiones = getRegiones(regionCursoCapacitacion);
			this.eduParametrosRegionList=regiones;
			
			/*Periodo Actual*/
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			String codigoPeriodo= ajax.getCampannaActualEDUByPaisEmpresa(pais.getCodigo(), f.getCodigoEmpresa());
			f.setCodigoPeriodo(codigoPeriodo);
			
			/* Tipos de Envio */
			ArrayList resultado = new ArrayList();
			Base[] mes = new Base[1];
//			String mensajeTipoEnvioNormal = messageResources.getMessage(
//					"interfazDATEnviarArchivosEducacionForm.tipoEnvioNormal");
//			String mensajeTipoEnvioReenvio = messageResources.getMessage(
//					"interfazDATEnviarArchivosEducacionForm.tipoEnvioReenvio");
			String mensajeTipoEnvioReproceso =  this.getResourceMessage("interfazDATEnviarArchivosEducacionForm.tipoEnvioReproceso");

//			mes[0] = new Base();
//			mes[0].setCodigo(Constants.EDU_TIPO_ENVIO_NORMAL);
//			mes[0].setDescripcion(mensajeTipoEnvioNormal);
//			resultado.add(mes[0]);
			
//			mes[1] = new Base();
//			mes[1].setCodigo(Constants.EDU_TIPO_ENVIO_REENVIO);
//			mes[1].setDescripcion(mensajeTipoEnvioReenvio);
//			resultado.add(mes[1]);
			
			mes[0] = new Base();
			mes[0].setCodigo(Constants.EDU_TIPO_ENVIO_REPROCESO);
			mes[0].setDescripcion(mensajeTipoEnvioReproceso);
			resultado.add(mes[0]);
				
			this.eduTipoEnvioInterfaceDatamartList=resultado;
			this.indicadorSistema = (String) this.parametrosPantalla.get("indicadorSistema");
			this.indicadorCierre = (String) this.parametrosPantalla.get("indicadorCierre");
	

		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		InterfazDATEnviarArchivosEducacionForm f = (InterfazDATEnviarArchivosEducacionForm) this.formInterfaz;
		log.debug("cod Region "+f.getCodigoRegion());
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoPrograma", Constants.EDU_PARAMETROS_PROGRAMA_LBEL );
		params.put("usuarioLogin", usuario.getLogin());
		params.put("codigoRegion", f.getCodigoRegion().equals("00")? null : f.getCodigoRegion());
		
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		String tipoEnvio = (String) params.get("tipoEnvio");
		
		/* En caso sea un reproceso se borra primero Tabla de Historico de Interfaz Datamart */
		//debido auq ya no hya reenvio 
		service.deleteTemporalInterfazDatamart(params);
		
		if (Constants.EDU_TIPO_ENVIO_REPROCESO.equals(tipoEnvio)) {
			//service.deleteTemporalInterfazDatamart(params);
			params.put("tipoEnvio",Constants.EDU_TIPO_ENVIO_NORMAL);
		}	
		return params;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult) throws Exception{
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		if (log.isDebugEnabled()) {
			log.debug("Entering 'afterExecuteInterfaz' method");
			log.debug(params);
		}
		MantenimientoEDUGenericoService service = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		if (interfazExecutionResult.ejecucionCompletada()) {
			String tipoEnvio = (String) params.get("tipoEnvio");
			if (Constants.EDU_TIPO_ENVIO_NORMAL.equals(tipoEnvio)) {
				service.updateNroLoteInterfazDatamart(params);
			}	
		}
		log.debug("Fin 'afterExecuteInterfaz' method");
	}

	

	
	/**
	 * @param val
	 */
	public void loadPeriodoMarca(ValueChangeEvent val) 
	{	
		// String marca = (String) val.getNewValue();
		// InterfazLLIEnviarVentaRealDiariaAcumuladaForm f =
		// (InterfazLLIEnviarVentaRealDiariaAcumuladaForm) this.formInterfaz;
		// SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		//
		// f.setCodigoPeriodo(periodoRequerido(marca, f.getCodigoCanal()));
		// f.setFechaFacturacionInicial(loadFechaInicial(marca,
		// f.getCodigoCanal(), f.getCodigoPeriodo()));
		// f.setFechaFacturacionFinal(loadFechaFinal(marca, f.getCodigoCanal(),
		// f.getCodigoPeriodo()));
		//
		// if (f.getFechaFacturacionInicial() != null &&
		// f.getFechaFacturacionInicial() != "")
		// try {
		// f.setFechaFacturacionInicialDate(df.parse(f.getFechaFacturacionInicial()));
		// } catch (ParseException e) { }
		// else
		// f.setFechaFacturacionInicialDate(null);
		//
		// if(f.getFechaFacturacionFinal()!= null &&
		// f.getFechaFacturacionFinal() != "")
		// try {
		// f.setFechaFacturacionFinalDate(df.parse(f.getFechaFacturacionFinal()));
		// } catch (ParseException e) { }
		// else
		// f.setFechaFacturacionFinalDate(null);		
	}
	
	

	
	/**
	 * @param regionCursoCapacitacion
	 * @return
	 */
	private LabelValue[] getRegiones(RegionCursoCapacitacion regionCursoCapacitacion) {
		LabelValue[] result = null;
			try {
				MantenimientoEDUCursoCapacitacionService service = (MantenimientoEDUCursoCapacitacionService) getBean("edu.mantenimientoEDUCursoCapacitacionService");						
				List regiones = service
						.getRegion(regionCursoCapacitacion);
				if (regiones != null && regiones.size() > 0) {
						result = new LabelValue[regiones.size()];
						for (int i = 0; i < regiones.size(); i++) {
							Base region = new Base();
							RegionCursoCapacitacion detalle = new RegionCursoCapacitacion();
							detalle = (RegionCursoCapacitacion) regiones.get(i);
							region.setCodigo(detalle.getCodigoRegion());
							region.setDescripcion(detalle.getCodigoRegion()+" - "+detalle.getDescripcionRegion());
							// Construimos la descripcion
							LabelValue lv = new LabelValue(region
									.getDescripcion(), region.getCodigo());
							result[i] = lv;
					}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}

			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		return result;
	}

	/**
	 * @return the eduEmpresaComercializadoraList
	 */
	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	/**
	 * @param eduEmpresaComercializadoraList the eduEmpresaComercializadoraList to set
	 */
	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	/**
	 * @return the eduParametrosRegionList
	 */
	public LabelValue[] getEduParametrosRegionList() {
		return eduParametrosRegionList;
	}

	/**
	 * @param eduParametrosRegionList the eduParametrosRegionList to set
	 */
	public void setEduParametrosRegionList(LabelValue[] eduParametrosRegionList) {
		this.eduParametrosRegionList = eduParametrosRegionList;
	}

	/**
	 * @return the eduTipoEnvioInterfaceDatamartList
	 */
	public List getEduTipoEnvioInterfaceDatamartList() {
		return eduTipoEnvioInterfaceDatamartList;
	}

	/**
	 * @param eduTipoEnvioInterfaceDatamartList the eduTipoEnvioInterfaceDatamartList to set
	 */
	public void setEduTipoEnvioInterfaceDatamartList(
			List eduTipoEnvioInterfaceDatamartList) {
		this.eduTipoEnvioInterfaceDatamartList = eduTipoEnvioInterfaceDatamartList;
	}

	/**
	 * @return the indicadorSistema
	 */
	public String getIndicadorSistema() {
		return indicadorSistema;
	}

	/**
	 * @param indicadorSistema the indicadorSistema to set
	 */
	public void setIndicadorSistema(String indicadorSistema) {
		this.indicadorSistema = indicadorSistema;
	}

	/**
	 * @return the indicadorCierre
	 */
	public String getIndicadorCierre() {
		return indicadorCierre;
	}

	/**
	 * @param indicadorCierre the indicadorCierre to set
	 */
	public void setIndicadorCierre(String indicadorCierre) {
		this.indicadorCierre = indicadorCierre;
	}
	
	
	
}

