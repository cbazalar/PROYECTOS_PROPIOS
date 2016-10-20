package biz.belcorp.ssicc.web.sisicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ProcesoBatchService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRProcesarConsolidadoOCSForm;

/**
 * Action del procesamiento del consolidado de archivos de texto de OCS de la
 * Interfaz OCR.
 * 
 */
@ManagedBean
@SessionScoped
public class InterfazOCRProcesarConsolidadoOCSAction extends BaseInterfazAbstractAction {
	
	private static final long serialVersionUID = -1743040139565376734L;
	private List listaTipoRecepcion ;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRProcesarConsolidadoOCSForm interfazOCRProcesarConsolidadoOCSForm = new InterfazOCRProcesarConsolidadoOCSForm();
		return interfazOCRProcesarConsolidadoOCSForm;
	}
	
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		
		// Carga Periodo
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        this.listaTipoRecepcion = service.getTiposRecepcionMICAyOCS();
        InterfazOCRProcesarConsolidadoOCSForm form  =  (InterfazOCRProcesarConsolidadoOCSForm) this.formInterfaz;
        form.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(codigoPais, Constants.CODIGO_CANAL_DEFAULT));
  	
		String codigoInterfaz = form.getCodigoInterfaz();
		form.setTipoRecepcion(codigoInterfaz);
		
		GenericoService genericoService = (GenericoService)getBean("genericoService");
		String showValiAutoPediDigi = genericoService.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_PARAM_SHOW_VALI_PEDI_DIGI);		
		form.setShowValiAutoPediDigi(showValiAutoPediDigi);		
		form.setIndValidacionSTO(Constants.SI);
		form.setIndValidacionSTOBool(true);
		
		this.indicadorActualizarProcesoBatchEnAction = Constants.SI;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		
		params =  super.prepareParamsBeforeExecute(params, form);
		InterfazOCRProcesarConsolidadoOCSForm f  =  (InterfazOCRProcesarConsolidadoOCSForm) this.formInterfaz;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		
		ProcesoSTOService stoService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");		
		String numLoteSTO = stoService.updateLoteSTO(new TipoDocumentoDigitadoPK(codigoPais, Constants.STO_TIPO_DOCUMENTO_OCC));
		
		log.info("numLoteSTO : "+ numLoteSTO);
		if (f.isIndValidacionSTOBool()) 
			f.setIndValidacionSTO(Constants.SI);
		else
			f.setIndValidacionSTO(Constants.NO);
		
		params.put("codigoPais", codigoPais);
		params.put("usuario", usuario);
		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("indOrigen", Constants.STO_ORIGEN_OCR);		
		params.put("indicadorOCRComercial", pais.getIndicadorOCRComercial());
		params.put("numLoteSTO",numLoteSTO);
		params.put("indValidacionSTO", f.getIndValidacionSTO());
	
		f.setNumLoteSTO(numLoteSTO);

		return params;
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#executeProcessBeforeInterfaz(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcessBeforeInterfaz(
			Map<String, Object> params) throws Exception {
		params = super.executeProcessBeforeInterfaz(params);
		Usuario usuario = (Usuario) params.get("usuario");
		params.put("descripcionEtapaProceso", Constants.DESCRIPCION_RECEPCIONAR_OCR_PROCESO);
		ProcesoBatchService procesoBatchService = (ProcesoBatchService) getBean("scsicc.procesoBatchService");
		procesoBatchService.updateProcesoBatchEtapaProceso(params, usuario);
		return params;
	}

	
	/**
	 * Actualiza pantalla en base al Tipo de Recepcion
	 * @param val
	 */
	public void loadArchivos(ValueChangeEvent val){
		log.debug(">>loadArchivos ");
		
		log.debug("val: "+val.getNewValue().toString());
		String codigoTipoRecepcion = (String) val.getNewValue();
		try {
			InterfazOCRProcesarConsolidadoOCSForm f  =  (InterfazOCRProcesarConsolidadoOCSForm) this.formInterfaz;
			String codigoInterfaz = this.formInterfaz.getCodigoInterfaz();
			
			this.paginaRutaCompletaXHTML = StringUtils.replace(this.paginaRutaCompletaXHTML, codigoInterfaz, codigoTipoRecepcion, 2) ;
			//FacesContext.getCurrentInstance().getExternalContext().redirect(this.paginaRutaCompletaXHTML );
			codigoInterfaz = codigoTipoRecepcion;
			this.parametrosPantalla = new HashMap<String, String>();
			this.parametrosPantalla.put("codigoInterfaz",codigoInterfaz);
			this.parametrosPantalla.put("tipoRecepcion",codigoTipoRecepcion);
			this.parametrosPantalla.put("codigoProcesoBatch","00");
			this.parametrosPantalla.put("codigoMenu",this.codigoMenu);
			f.setCodigoInterfaz(codigoInterfaz);
			f.setTipoRecepcion(codigoInterfaz);
			this.formInterfaz = f;
			this.viewLogicaNegocio();
			
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		return;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult)  throws Exception{
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		log.debug("Entering 'afterExecuteInterfaz' method " + interfazExecutionResult.isEjecutarSTO());
		//if (interfazExecutionResult.isEjecutarSTO()){
		if(interfazExecutionResult.ejecucionCompletada()){
        String codigoPais = (String)params.get("codigoPais");
        String	indValidacionSTO = (String)params.get("indValidacionSTO");
         if (indValidacionSTO!=null && indValidacionSTO.equals(Constants.SI)){
	        ProcesoSTOExecutionService procesoSTOExecutionService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
	    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_AUTO);    			
	        procesoSTOExecutionService.execute(accionTipoDocumento,params, null);
         }
	   }
        
	}
	

	/**
	 * @return the listaTipoRecepcion
	 */
	public List getListaTipoRecepcion() {
		return listaTipoRecepcion;
	}


	/**
	 * @param listaTipoRecepcion the listaTipoRecepcion to set
	 */
	public void setListaTipoRecepcion(List listaTipoRecepcion) {
		this.listaTipoRecepcion = listaTipoRecepcion;
	}

	
	
	
	

	
  
}