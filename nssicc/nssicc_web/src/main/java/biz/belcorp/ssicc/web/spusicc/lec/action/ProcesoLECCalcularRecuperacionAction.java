package biz.belcorp.ssicc.web.spusicc.lec.action;

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
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUComercialService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.lec.form.ProcesoLECCalcularRecuperacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class ProcesoLECCalcularRecuperacionAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1415627464638554188L;
	
	private List lecGrupoRegionList;
	private LabelValue[] lecRegionList ={};
	private boolean opcionPeriodoFecha;
	private boolean opcionPeriodoFechaRecaudo;
	private String indTipoGrupoRegion;
	private boolean habilita;
	private String indTipoGrupoPago;
	private boolean mostrarPopup = true;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		ProcesoLECCalcularRecuperacionForm formInterfaz = new ProcesoLECCalcularRecuperacionForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		log.debug("Executin action : view.");
		ProcesoLECCalcularRecuperacionForm f = (ProcesoLECCalcularRecuperacionForm)this.formInterfaz;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		String codigoPais = pais.getCodigo();
		f.setCodigoPais(codigoPais);
		String conexionExterna = pais.getCodigoConexionExterna();
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
        criteria.put("estadoCampanha",Constants.NUMERO_CERO);
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO);
        criteria.put("codigoConexionExterna", conexionExterna);
        
        MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
              
	    MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		
		f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
					      
        f.setCodigoConexionExterna(conexionExterna);
        
		String indicadorProceso = this.parametrosPantalla.get("indicadorProceso");
		f.setIndicadorProceso(indicadorProceso);
		/* INI JJ  PER-SiCC-2012-0361 */
		String indTipoValid = this.parametrosPantalla.get("indTipoValid");
		f.setIndTipoValid(indTipoValid);
		/* FIN JJ  PER-SiCC-2012-0361 */
		criteria.put("codigoSistema", "LEC");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());
				
		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService)getBean("spusicc.procesoGENProcesarCierreService");
		
		f.setIndicadorModEducacion(procesoService.getIndicadorModEducacion(codigoPais));
		
		if(f.getIndicadorModEducacion().equals(Constants.NUMERO_UNO)){		
				//cargarArchivoControlFacturacion(f,pais);
		}		
		
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);	
		Map map1 = lecService.getEncontrarProgramaLecCorporativo(f.getPeriodoProceso());
		
		f.setCodigoPrograma(map1.get("codigoPrograma").toString());
		f.setDescPrograma(map1.get("descPrograma").toString());
		
		String frecuenciaSGR = this.parametrosPantalla.get("frecuenciaSGR");
		f.setFrecuenciaSGR(frecuenciaSGR);		
		
		List list = lecService.getTipoGrupoRegion(map);
		this.lecGrupoRegionList = list;
		
		Map map2 = new HashMap();
		map2.put("codigoPais", codigoPais);
		map2.put("codigoTipoGroup", ((Base)list.get(0)).getCodigo());
				
		String tipoGrupoRegion = getIndicadorGrupoRegion(pais);
		this.indTipoGrupoRegion = tipoGrupoRegion!= null? tipoGrupoRegion:Constants.NRO_CERO;
		
		String indTipoGrupoPago = getIndicadorGrupoPago(pais);
		this.indTipoGrupoPago = indTipoGrupoPago!= null? indTipoGrupoPago:Constants.NRO_CERO;

		map.put("indTipoGrupoPago", indTipoGrupoPago);
		List gruposPago = lecService.getGruposPago(map);
		this.lecGrupoRegionList = gruposPago;
		
		if(gruposPago != null && gruposPago.size() > 0)
		{
			String codigoGrupoPago = ((Base)gruposPago.get(0)).getCodigo();
			LabelValue[] regionList = ajaxService.getRegionesZonasByTipoGrupo(pais.getCodigo(), codigoGrupoPago);
			this.lecRegionList = regionList;
		}
		else
		{
			this.lecRegionList = new LabelValue[]{};
		}
		
		f.setHabilitadorHidden(Constants.NUMERO_CERO);
		f.setHabilitadorRecaudoHidden("0");
		f.setHabilitadorPeriodo("0");
		f.setCodigoPeriodoRecaudo("");

		String habilitadorCampanna = this.getIndicadorCampannaProceso(pais);
		if (StringUtils.isNotBlank(habilitadorCampanna))
			f.setHabilitadorHidden(habilitadorCampanna);
		f.setHabilitadorPeriodo(habilitadorCampanna);
				    
		
		if(f.getHabilitadorRecaudoHidden().equals("1"))
		{
			setOpcionPeriodoFechaRecaudo(false);
		}
		else
		{
			setOpcionPeriodoFechaRecaudo(true);
		}
		
		if(f.getHabilitadorHidden().equals("1") && f.getHabilitadorPeriodo().equals("1"))
		{
			setHabilita(true);
			setOpcionPeriodoFecha(false);
		}
		else{
			setHabilita(false);
			setOpcionPeriodoFecha(true);
		}
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form); 
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String codigoPais = pais.getCodigo();
		ProcesoLECCalcularRecuperacionForm f = (ProcesoLECCalcularRecuperacionForm) this.formInterfaz;
		params.put("codigoUsuario", usuario.getLogin());
		params.put("login", usuario.getLogin());
		params.put("codigoPais", pais.getCodigo());
		params.put("codigoConexionExterna", pais.getCodigoConexionExterna());

		String indicadorValMae = getIndicadorValMae(pais);
		params.put("indicadorValMae", indicadorValMae!= null? indicadorValMae:Constants.NRO_CERO);
		//if(Constants.NRO_UNO.equals(indicadorValMae)){
			params.put("periodoProceso", f.getPeriodoProceso());
		//	params.put("fechaProceso", f.getFechaProceso());
		//}
		/* INI JJ PER-SiCC-2012-0167 */
	   // params.put("fechaFacturacion", f.getFechaProceso());
	    /* FIN JJ PER-SiCC-2012-0167 */
		params.put("codigoPeriodo", f.getPeriodoProceso());
		params.put("campannaRecaudo", f.getCodigoPeriodoRecaudo());
		params.put("fechaFacturacion", "");
		params.put("codigoTramo", new Integer(1));
		
		params.put("periodo", f.getPeriodoProceso());
		params.put("codigoUsuario", usuario.getLogin());
		params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		/* INI JJ  PER-SiCC-2012-0361 */
		params.put("indTipoValid", f.getIndTipoValid());
		/* FIN JJ  PER-SiCC-2012-0361 */
		String servidor = getRequest().getServerName();
		params.put("nombreServidor", servidor);
		
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
			params.put("indicadorEnvioCronograma", parametro.getIndicadorEnvioCronograma());
		}
		
		// proceso de Reactivacion de licencias

		/* obtiene ultima periodo Activo */
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		String conexionExterna = pais.getCodigoConexionExterna();
		criteria.put("codigoConexionExterna", conexionExterna);

		if (StringUtils.equals(conexionExterna,
				Constants.CONEXION_EXTERNA_ORACLE)) {
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
			String periodoActual = controlFacturacion.getCodigoPeriodo();

			params.put("periodoActivo", periodoActual);
		} else {
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
		params.put("codigoGrupoRegion", StringUtils.isBlank(f.getGrupoRegion()) ? "" : f.getGrupoRegion());
		params.put("codigoGrupoPago02", StringUtils.isBlank(f.getGrupoRegion()) ? "" : f.getGrupoRegion());
		
		return params;
	}
	
	/**
	 * Obtiene indicador de Mostrar GrupoRegion
	 * @param pais
	 * @return indTipoGrupoRegion
	 */
	private String getIndicadorGrupoRegion(Pais pais) {
		Map criteriaParam = new HashMap();

		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", "indTipoGrupoRegion");
		return ((MantenimientoSTOBloqueoControlService)
				getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}
	
	/**
	 * @param pais
	 * @return
	 */
	private String getIndicadorCampannaProceso(Pais pais) {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", "INDCAMBIOCAMPANA");
		return ((MantenimientoSTOBloqueoControlService)
			getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}
	
	/**
	 * Obtiene indicador de Validacion de Informacion MAE
	 * @param pais
	 * @return indicadorValMae
	 */
	private String getIndicadorValMae(Pais pais) {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "MAE");
		criteriaParam.put("nombreParametro", "indValidacionMAE");
		return ((MantenimientoSTOBloqueoControlService)
			getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}
	
	
	/**
	 * @param f
	 * @param session
	 * @throws Exception
	 */
	private void cargarArchivoControlFacturacion(ProcesoLECCalcularRecuperacionForm f, Pais pais) throws Exception
	{
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoEDUGenericoService mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService)getBean("edu.mantenimientoEDUGenericoService");
		
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

	/**
	 * Obtiene indicador de Mostrar GrupoPago
	 * @param request
	 * @return indTipoGrupoPago
	 */
	private String getIndicadorGrupoPago(Pais pais) {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", Constants.LEC_IND_TIPO_GRUPO_PAGO);
		return ((MantenimientoSTOBloqueoControlService)
			getBean("spusicc.mantenimientoSTOBloqueoControlService")).getParametroGenericoSistema(criteriaParam);
	}
	
	
	public void loadPeriodoFecha(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoFecha");
		}
		String opcion = (String) val.getNewValue().toString();

		if (opcion == "true") {
			setOpcionPeriodoFecha(false);
		} else {
			setOpcionPeriodoFecha(true);
		}
	}
	
	public void loadPeriodoFechaRecaudo(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadPeriodoFechaRecaudo");
		}
		String opcion = (String) val.getNewValue().toString();

		if (opcion == "true") {
			setOpcionPeriodoFechaRecaudo(false);
		} else {
			setOpcionPeriodoFechaRecaudo(true);
		}
	}
	
	public void loadRegiones(ValueChangeEvent value)
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ProcesoLECCalcularRecuperacionForm f = (ProcesoLECCalcularRecuperacionForm)this.formInterfaz;
		String valor = value.getNewValue().toString();
		
		this.lecRegionList = ajax.getRegionesZonasByTipoGrupo(f.getCodigoPais(), valor);
	}

	@Override
	public String setValidarInterfaz() 
	{
		String mensaje = null;
		ProcesoLECCalcularRecuperacionForm f = (ProcesoLECCalcularRecuperacionForm)this.formInterfaz;
		
		if(StringUtils.isBlank(f.getPeriodoProceso()))
		{
			mensaje = "'Campaña Proceso' es un campo requerido.";
			return mensaje;
		}
		
		if(StringUtils.isBlank(f.getCodigoPeriodoRecaudo()))
		{
			mensaje ="'Campaña Recaudo' es un campo requerido.";
			return mensaje;
		}
		
		return mensaje;	
	}
	
	public List getLecGrupoRegionList() {
		return lecGrupoRegionList;
	}

	public void setLecGrupoRegionList(List lecGrupoRegionList) {
		this.lecGrupoRegionList = lecGrupoRegionList;
	}

	public LabelValue[] getLecRegionList() {
		return lecRegionList;
	}

	public void setLecRegionList(LabelValue[] lecRegionList) {
		this.lecRegionList = lecRegionList;
	}

	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

	public boolean isOpcionPeriodoFechaRecaudo() {
		return opcionPeriodoFechaRecaudo;
	}

	public void setOpcionPeriodoFechaRecaudo(boolean opcionPeriodoFechaRecaudo) {
		this.opcionPeriodoFechaRecaudo = opcionPeriodoFechaRecaudo;
	}

	public String getIndTipoGrupoRegion() {
		return indTipoGrupoRegion;
	}

	public void setIndTipoGrupoRegion(String indTipoGrupoRegion) {
		this.indTipoGrupoRegion = indTipoGrupoRegion;
	}

	public boolean isHabilita() {
		return habilita;
	}

	public void setHabilita(boolean habilita) {
		this.habilita = habilita;
	}

	public String getIndTipoGrupoPago() {
		return indTipoGrupoPago;
	}

	public void setIndTipoGrupoPago(String indTipoGrupoPago) {
		this.indTipoGrupoPago = indTipoGrupoPago;
	}

	public boolean isMostrarPopup() {
		return mostrarPopup;
	}

	public void setMostrarCheckBoxPopup(boolean mostrarPopup) {
		this.mostrarPopup = mostrarPopup;
	}
}
