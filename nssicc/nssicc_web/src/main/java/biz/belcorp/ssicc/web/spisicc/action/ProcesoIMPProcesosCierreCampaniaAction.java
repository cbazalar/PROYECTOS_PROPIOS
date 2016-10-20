package biz.belcorp.ssicc.web.spisicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.gen.ProcesoGENProcesarCierreService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazIMPEnviarDocumentosMatricialesForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazLETRecepcionarCursosForm;
import biz.belcorp.ssicc.web.spisicc.form.ProcesoIMPProcesosCierreCampaniaForm;

@ManagedBean
@SessionScoped
public class ProcesoIMPProcesosCierreCampaniaAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 668561206856142770L;

	private List siccMarcaList;
	private List siccCanalList;
	private List impInterfacesPaquete;
	
	private boolean opcionPeriodoFecha;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoIMPProcesosCierreCampaniaForm interfazForm = new ProcesoIMPProcesosCierreCampaniaForm();
		return interfazForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoIMPProcesosCierreCampaniaForm f = (ProcesoIMPProcesosCierreCampaniaForm)this.formInterfaz;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
		// Carga de los combos Marca y Canal
		this.siccMarcaList=interfazSiCCService.getMarcas();
		this.siccCanalList= interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
	
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		f.setFechaFacturacion(controlFacturacion.getFechaProceso());
		f.setCodigoPeriodoActual(controlFacturacion.getCodigoPeriodo());
		
		//String codigoProcesoBatch = request.getParameter("codigoProcesoBatch");
		//sesion.setAttribute("codigoProcesoBatch", codigoProcesoBatch);
		
		String tipoCierre = this.parametrosPantalla.get("tipoCierre");
		
		f.setTipoCierre(tipoCierre);
		
		List lista = new ArrayList() ;		
		this.impInterfacesPaquete=new ArrayList() ;
		
		criteria.put("codigoSistema", "IMP");
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());
		
		lista = interfazSiCCService.getListaProcesosGenCierreCampania(criteria);
		
		ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) getBean("spusicc.procesoGENProcesarCierreService");
		
//		List listaProcesos = procesoService.getProcesosCierrePeriodo(criteria);
		List listaProcesos = new ArrayList();
		//sesion.setAttribute("totalProcesosSICC", listaProcesos.size());
		int  totalProcesosSICC=listaProcesos.size();
		listaProcesos.addAll(lista);
		
		this.impInterfacesPaquete=listaProcesos;
		
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		boolean validacionCierre =  procesoService.validaPeriodoACerrar(criteria);
		
		f.setIndicadorSeleccionInterfaces(Constants.INDICADOR_SELECCION_INTERFAZ);
		/* INI JJ  PER-SiCC-2012-0361 */
		String indTipoValid = this.parametrosPantalla.get("indTipoValid");
		f.setIndTipoValid(indTipoValid);
		/* FIN JJ  PER-SiCC-2012-0361 */
		if(!validacionCierre) { 
			if(Constants.NO.equals(f.getIndicadorSeleccionInterfaces())) {
				f.setMostrarBotonProcesar(false);				
				throw new Exception(this.getResourceMessage("procesoIMPProcesosCierreCampaniaForm.msg.validacionPeriodoAProcesar"));
			}	
		} else {
			boolean validacionRegiones =  procesoService.existeRegionesSinProcesar(criteria);			
			if(!validacionRegiones) {
				f.setMostrarBotonProcesar(false);
				throw new Exception(this.getResourceMessage("procesoGENProcesosCierreCampaniaForm.msg.validacionRegionesAbiertas"));				
			} 
		}	
		
		f.setIndicadorModEducacion(procesoService.getIndicadorModEducacion(pais.getCodigo()));
		
		String frecuenciaSGR = this.parametrosPantalla.get("frecuenciaSGR");
		f.setFrecuenciaSGR(frecuenciaSGR);
		
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal( Constants.CODIGO_CANAL_DEFAULT);
		f.setHabilitadorHidden(Constants.NUMERO_CERO);
		this.opcionPeriodoFecha=true;
		
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {		
		
		params = super.prepareParamsBeforeExecute(params, form);
		
		MantenimientoEDUGenericoService mantenimientoEDUGenericoService = (MantenimientoEDUGenericoService)getBean("edu.mantenimientoEDUGenericoService");
		
		ProcesoIMPProcesosCierreCampaniaForm f = (ProcesoIMPProcesosCierreCampaniaForm)this.formInterfaz;

		if(Constants.NO.equals(f.getIndicadorEjecucionSICC())) {
			InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			Integer contCierreCampa = interfazSiCCService.getContCierreCampaByCodigoPeriodo((String)params.get("codigoPeriodo"));		
		} else {
			ProcesoGENProcesarCierreService procesoService = (ProcesoGENProcesarCierreService) getBean("spusicc.procesoGENProcesarCierreService");
			
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			boolean validacionCierre =  procesoService.validaPeriodoACerrar(criteria);
			
			if(!validacionCierre) {
				String mensaje = this.getResourceMessage("procesoIMPProcesosCierreCampaniaForm.msg.validacionPeriodoAProcesar");
				throw new Exception(mensaje);
			}	
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		
		//sesion.setAttribute("codigoUsuario", usuario.getLogin());
		params.put("codigoPais", codigoPais);
		
		String servidor =  this.getRequest().getServerName();
		params.put("nombreServidor", servidor);
		params.put("codigoRegion", "-1");
		/* INI JJ  PER-SiCC-2012-0361 */
		params.put("login", usuario.getLogin());
		params.put("periodoProceso", f.getCodigoPeriodo());
		params.put("fechaProceso", f.getFechaFacturacion());
		params.put("indTipoValid", f.getIndTipoValid());
		/* FIN JJ  PER-SiCC-2012-0361 */
		EmpresaComercializadora empresaComercializadora = new EmpresaComercializadora();
		empresaComercializadora.setCodigoPais(codigoPais);
		empresaComercializadora.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		if(f.getIndicadorModEducacion().equals(Constants.NUMERO_UNO)){
			params.put("codigoEmpresa", ((EmpresaComercializadora)mantenimientoEDUGenericoService.getEmpresasComercializadorasByPais(empresaComercializadora).get(0)).getCodigoEmpresa());
			
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			LabelValue [] lv= ajaxService.getRegionesEDUByPaisEmpresa(codigoPais,(String)params.get("codigoEmpresa"));
			String [] regionList = getRegionList(lv);
			
			params.put("regionList", regionList);
		}
		
		params.put("frecuenciaSGR", f.getFrecuenciaSGR());
		
		params.put("indicadorProceso", "P");
		params.put("tipoProceso", Constants.CODIGO_TIPO_CIERRE_PERIODO);
		
		return params;
	}
	
	/**
	 * Obtengo la lista de solo codgos de regiones
	 * @param lv
	 * @return
	 */
	private String[] getRegionList(LabelValue[] lv) {
		String [] regionList = new String[lv.length];
		for(int i=0;i<lv.length;i++){
			LabelValue l =  lv[i];
			regionList[i]=l.getValue();
		}
		log.debug("list region "+regionList);
		return regionList;
	}
	
	@Override
	protected Map<String, Object> executeProcessBeforeInterfaz(Map<String, Object> params) throws Exception {
		String indicadorEjecucionSICC = (String)params.get("indicadorEjecucionSICC");
		if(Constants.SI.equals(indicadorEjecucionSICC)) {
			ProcesoGENProcesarCierreService service = (ProcesoGENProcesarCierreService) getBean("spusicc.procesoGENProcesarCierreService");			
			service.executeProcesarCierrePeriodo(params);
		}		
		return params;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#continueExecuteInterfaz(java.util.Map)
	 */
	protected boolean continueExecuteInterfaz(Map<String, Object> params) {
		boolean flag = true;
		
		String indicadorSeleccion = (String)params.get("indicadorSeleccionInterfaces");
		if(Constants.SI.equals(indicadorSeleccion)) {
			String indicadorEjecucionInterfaces = (String)params.get("indicadorEjecucionInterfaces");
			
			if(Constants.SI.equals(indicadorEjecucionInterfaces)) 
				flag = true;
			else
				flag = false;
		}
		
		return flag;
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

	public boolean isOpcionPeriodoFecha() {
		return opcionPeriodoFecha;
	}

	public void setOpcionPeriodoFecha(boolean opcionPeriodoFecha) {
		this.opcionPeriodoFecha = opcionPeriodoFecha;
	}

	public List getImpInterfacesPaquete() {
		return impInterfacesPaquete;
	}

	public void setImpInterfacesPaquete(List impInterfacesPaquete) {
		this.impInterfacesPaquete = impInterfacesPaquete;
	}
	
	
}
