package biz.belcorp.ssicc.web.spusicc.gen.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.ParametroCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.gen.form.ProcesoGENEliminarBuzonMensajeForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked"})
public class ProcesoGENEliminarBuzonMensajeAction extends BaseInterfazAbstractAction
{

	private static final long serialVersionUID = -1211379269063070936L;

	private List genInterfazPaquete;
	private List cupProgramaList;
	private List lecGrupoRegionList;
	private String fechaProceso;
	private String codigoPeriodoProceso;
	private boolean opcionPeriodoFecha;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoGENEliminarBuzonMensajeForm formInterfaz = new ProcesoGENEliminarBuzonMensajeForm();
		return formInterfaz;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception 
	{
		log.debug("Executin action : view.");
		ProcesoGENEliminarBuzonMensajeForm f = (ProcesoGENEliminarBuzonMensajeForm)this.formInterfaz;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		String conexionExterna = pais.getCodigoConexionExterna();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO);
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);
        criteria.put("codigoConexionExterna", conexionExterna);
        
        MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
        
        if (StringUtils.equals(conexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) {
	        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
			f.setFechaProceso(controlFacturacion.getFechaProceso());
			f.setFechaProcesoDate(DateUtil.convertStringToDate(f.getFechaProceso()));
			f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
			f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
			
			this.fechaProceso = controlFacturacion.getFechaProceso();
			this.codigoPeriodoProceso = controlFacturacion.getCodigoPeriodo();
        }
        else {
        	InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        	Map datos = service.getPeriodoFechaCampanyaActivaSF(criteria);
        	String[] result = new String[] { MapUtils.getString(datos, "periodo", ""), MapUtils.getString(datos, "fecha", "") };
        	f.setPeriodoProceso(result[0]);
        	f.setCodigoPeriodoActual(result[0]);
			f.setFechaProceso(result[1]);
			f.setFechaProcesoDate(DateUtil.convertStringToDate(f.getFechaProceso()));
			
			this.fechaProceso = result[1];
			this.codigoPeriodoProceso = result[0];
        }
        
        f.setCodigoConexionExterna(conexionExterna);
        
        String indicadorProceso =  this.parametrosPantalla.get("indicadorProceso");
		f.setIndicadorProceso(indicadorProceso);
		
		/* INI JJ  PER-SiCC-2012-0361 */
		String indTipoValid = this.parametrosPantalla.get("indTipoValid");
		String indTipoPrev = this.parametrosPantalla.get("indTipoPrev");
		f.setIndTipoValid(indTipoValid);
		f.setIndTipoPrev(indTipoPrev);
		
		/* FIN JJ  PER-SiCC-2012-0361 */
		criteria.put("codigoSistema", "GEN");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());
		
		List lista = new ArrayList();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		lista = interfazSiCCService.getListaProcesosCierreFacturacion(criteria);
		this.genInterfazPaquete = lista;
		
		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) getBean("spusicc.procesoGENProcesarCierreService");
		
		f.setIndicadorModEducacion(procesoService.getIndicadorModEducacion(pais.getCodigo()));

		if (f.getIndicadorModEducacion().equals(Constants.NUMERO_UNO)) 
		{
			cargarArchivoControlFacturacion(f);
		}
		
		Map map = new HashMap();
		map.put("codigoPais", pais.getCodigo());
		
		List listPC = new ArrayList();
		Map epcMap = lecService.getEncontrarProgramaLecCorporativo(f.getCodigoPeriodoActual());
		
		if(epcMap != null)
			listPC.add(epcMap);
		
		this.cupProgramaList = listPC;
		
		String frecuenciaSGR = this.parametrosPantalla.get("frecuenciaSGR");
		f.setFrecuenciaSGR(frecuenciaSGR);		
		
		this.lecGrupoRegionList = lecService.getTipoGrupoRegion(map);
		
		f.setHabilitadorHidden(Constants.NUMERO_CERO);
		setOpcionPeriodoFecha(true); 
	}
	
	/**
	 * Obtiene indicador de Validacion de Informacion MAE
	 * @param request
	 * @return indicadorValMae
	 */
	private String getIndicadorValMae() 
	{
		Map criteriaParam = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "MAE");
		criteriaParam.put("nombreParametro", "indValidacionMAE");
		return ((MantenimientoSTOBloqueoControlService)
				getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}
	
	/**
	 * @param f
	 * @throws Exception
	 */
	private void cargarArchivoControlFacturacion(ProcesoGENEliminarBuzonMensajeForm f) throws Exception 
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoEDUGenericoService mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");

		EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
		empresaComercializadora.setCodigoPais(pais.getCodigo());
		empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		/* Cargando archivo de control de Facturacion */
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoEmpresa",((EmpresaComercializadora)mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(empresaComercializadora).get(0)).getCodigoEmpresa());
		criteria.put("usuario", usuario);
		criteria.put("noCopiarArchivos", Constants.SI);
		ProcesoEDUComercialService	procesoEDUComercialService = (ProcesoEDUComercialService) getBean("edu.procesoEDUComercialService");
		procesoEDUComercialService.executeProcesoEDUCargarControlFacturacion(f.getCodigoPais(), criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoGENEliminarBuzonMensajeForm f = (ProcesoGENEliminarBuzonMensajeForm)this.formInterfaz;
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoDate()));
		params = super.prepareParamsBeforeExecute(params, form);
		String codigoPais = pais.getCodigo();
		
		params.put("login", usuario.getLogin());
		params.put("codigoPais", pais.getCodigo());
		params.put("codigoConexionExterna", pais.getCodigoConexionExterna());

		String indicadorValMae = getIndicadorValMae();
		params.put("indicadorValMae", indicadorValMae!= null? indicadorValMae:Constants.NRO_CERO);
		//if(Constants.NRO_UNO.equals(indicadorValMae)){
			params.put("periodoProceso", f.getPeriodoProceso());
			params.put("fechaProceso", f.getFechaProceso());
		//}
		/* INI JJ PER-SiCC-2012-0167 */
	    params.put("fechaFacturacion", f.getFechaProceso());
	    /* FIN JJ PER-SiCC-2012-0167 */
		params.put("codigoPeriodo", f.getPeriodoProceso());
		params.put("periodo", f.getPeriodoProceso());
		params.put("codigoUsuario", usuario.getLogin());
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		/* INI JJ  PER-SiCC-2012-0361 */
		params.put("indTipoValid", f.getIndTipoValid());
		params.put("indTipoPrev", f.getIndTipoPrev());
		/* FIN JJ  PER-SiCC-2012-0361 */
		String servidor = this.getRequest().getServerName();
		params.put("nombreServidor", servidor);
		String entorno = this.getRequest().getContextPath();
		params.put("entorno", entorno);
		params.put("campannaRecaudo", "");
		params.put("codigoGrupoPago02", "");
		params.put("codigoTramo", new Integer(1));
		
		if (f.getIndicadorModEducacion().equals(Constants.NUMERO_UNO)) {

			MantenimientoEDUGenericoService mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
			MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
						
			EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
			empresaComercializadora.setCodigoPais(codigoPais);
			empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);

			params.put("codigoEmpresa", ((EmpresaComercializadora) mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(
									empresaComercializadora).get(0)).getCodigoEmpresa());

			ParametroCursoCapacitacion parametro = new ParametroCursoCapacitacion();
			parametro.setCodigoPais(codigoPais);
			parametro.setCodigoEmpresa((String) params.get("codigoEmpresa"));
			parametro.setCodigoPrograma(Constants.EDU_PARAMETROS_PROGRAMA_LBEL);
			parametro = siccService.getParametroCurso(parametro);
			params.put("indicadorEnvioCronograma",
					parametro.getIndicadorEnvioCronograma());

		}
		
		// proceso de Reactivacion de licencias

		/* obtiene ultima periodo Activo */
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		String conexionExterna = pais.getCodigoConexionExterna();
		criteria.put("codigoConexionExterna", conexionExterna);

		if (StringUtils.equals(conexionExterna, Constants.CONEXION_EXTERNA_ORACLE)) 
		{
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
			String periodoActual = controlFacturacion.getCodigoPeriodo();

			params.put("periodoActivo", periodoActual);
		} else 
		{
			InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			Map datos = service.getPeriodoFechaCampanyaActivaSF(criteria);
			String[] result = new String[] {
					MapUtils.getString(datos, "periodo", ""),
					MapUtils.getString(datos, "fecha", "") };

			String periodoActual = result[0];
			params.put("periodoActivo", periodoActual);
		}
		
		params.put("frecuenciaSGR", f.getFrecuenciaSGR());
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
		params.put("rutaPath", path);
		params.put("usuarioTemp", usuario);	
		params.put("tipoProceso", "D");
		params.put("codigoRegion", "");
		params.put("codigoGrupoRegion", "");
		params.put("codigoGrupoPago", "");
		// PROGRAMA LEC - Campaña Recaudo
		params.put("codigoPeriodoRecaudo", "");
		
		//OBTENIENDO CODIGO DE PROGRAMA 
        MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		Map maplec = lecService.getEncontrarProgramaLecCorporativo(f.getCodigoPeriodoActual());
		params.put("codigoPrograma",(String) maplec.get("codigoPrograma"));
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#setValidarInterfaz()
	 */
	@Override
	public String setValidarInterfaz() 
	{
		ProcesoGENEliminarBuzonMensajeForm f = (ProcesoGENEliminarBuzonMensajeForm) this.formInterfaz;
		String mensaje = null;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		String periodo = f.getPeriodoProceso();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, periodo);

			try {
				if(f.getFechaProcesoDate().after(DateUtil.convertStringToDate(fechaHasta)) ||
						f.getFechaProcesoDate().before(DateUtil.convertStringToDate(fechaDesde)))
				{
					mensaje = this
							.getResourceMessage("errors.compare.campaigns.periodo.fechaFacturacion")
							+ " (" + fechaDesde + " - " + fechaHasta + ")";
				}
			} catch (ParseException e) {
				this.addError("Error : ", this.obtieneMensajeErrorException(e));
			}
		
		return mensaje;
	}
	/**
	 * Carga el Periodo
	 */
	public void loadPeriodoFecha(ValueChangeEvent val){
		try {
			String opcion = (String) val.getNewValue().toString();
			ProcesoGENEliminarBuzonMensajeForm f = (ProcesoGENEliminarBuzonMensajeForm) this.formInterfaz;					
			if (opcion == "true") {
				setOpcionPeriodoFecha(false);
				f.setHabilitadorHidden(Constants.NUMERO_UNO);
			} else {
				setOpcionPeriodoFecha(true);
				f.setPeriodoProceso(f.getCodigoPeriodoActual());
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * @return
	 */
	public List getGenInterfazPaquete() {
		return genInterfazPaquete;
	}

	/**
	 * @param genInterfazPaquete
	 */
	public void setGenInterfazPaquete(List genInterfazPaquete) {
		this.genInterfazPaquete = genInterfazPaquete;
	}

	/**
	 * @return
	 */
	public List getCupProgramaList() {
		return cupProgramaList;
	}

	/**
	 * @param cupProgramaList
	 */
	public void setCupProgramaList(List cupProgramaList) {
		this.cupProgramaList = cupProgramaList;
	}

	/**
	 * @return
	 */
	public List getLecGrupoRegionList() {
		return lecGrupoRegionList;
	}

	/**
	 * @param lecGrupoRegionList
	 */
	public void setLecGrupoRegionList(List lecGrupoRegionList) {
		this.lecGrupoRegionList = lecGrupoRegionList;
	}

	/**
	 * @return
	 */
	public String getFechaProceso() {
		return fechaProceso;
	}

	/**
	 * @param fechaProceso
	 */
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodoProceso() {
		return codigoPeriodoProceso;
	}

	/**
	 * @param codigoPeriodoProceso
	 */
	public void setCodigoPeriodoProceso(String codigoPeriodoProceso) {
		this.codigoPeriodoProceso = codigoPeriodoProceso;
	}

	/**
	 * @return
	 */
	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	/**
	 * @param opcionPeriodoFecha
	 */
	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}
}