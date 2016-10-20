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
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRCargaPedidoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarOCSWebDDForm;

/**
 * Action del procesamiento del consolidado de archivos de texto de OCS de la
 * Interfaz OCR.
 * 
 */
@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarOCSWebDDAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8766672286488454570L;
	private List ocrTipoRecepcionWebddList;

	@Override
	protected void setViewAtributes() throws Exception {
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();
		// Carga de los combos
		MantenimientoOCRCargaPedidoService service = (MantenimientoOCRCargaPedidoService) getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");
		this.ocrTipoRecepcionWebddList = service
				.getTiposRecepcionMICAOCSWebDD();

		InterfazOCRRecepcionarOCSWebDDForm form = (InterfazOCRRecepcionarOCSWebDDForm) this.formInterfaz;

		GenericoService genericoService = (GenericoService) getBean("genericoService");
		String showValiAutoPediDigi = genericoService.getParametroPais(
				codigoPais, Constants.SISTEMA_PED,
				Constants.PED_PARAM_SHOW_VALI_PEDI_DIGI);
		form.setShowValiAutoPediDigi(showValiAutoPediDigi);
		form.setIndValidacionSTO(Constants.SI);
		form.setIndValidacionSTOBool(true);
		
	    String codigoInterfaz=form.getCodigoInterfaz();
		form.setTipoRecepcion(codigoInterfaz);
	
		this.indicadorActualizarProcesoBatchEnAction = Constants.SI;
	}
	
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		InterfazOCRRecepcionarOCSWebDDForm f = (InterfazOCRRecepcionarOCSWebDDForm) this.formInterfaz;
		if(!f.getIndValidacionSTOBool()){
			f.setIndValidacionSTO(Constants.NO);
		}
		params = super.prepareParamsBeforeExecute(params, form);
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();	 
	     
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		
		
		ProcesoSTOService stoService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");		
		String numLoteSTO = stoService.updateLoteSTO(new TipoDocumentoDigitadoPK(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC));
		
		
		params.put("codigoPais", codigoPais);
		params.put("codigoInterfaz", f.getTipoRecepcion());		 
		params.put("usuario", usuario);	 
		params.put("fechaDemanda", null);		
		params.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("indOrigen", Constants.STO_ORIGEN_WEB);	
		params.put("indicadorOCRComercial", pais.getIndicadorOCRComercial());
		params.put("numLoteSTO",numLoteSTO); 
		params.put("indValidacionSTO",f.getIndValidacionSTO());
		params.put("codigoProcesoBatch", f.getCodigoProcesoBatch());
		
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(params);
		
		//f.setNumLoteSTO(numLoteSTO);
		
		return params;

	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params, InterfazExecutionResult interfazExecutionResult)  throws Exception{
		super.afterExecuteInterfaz(params, interfazExecutionResult);
		if (interfazExecutionResult.ejecucionCompletada()){
        String codigoPais = (String)params.get("codigoPais");
        String	indValidacionSTO = (String)params.get("indValidacionSTO");
        
        if (indValidacionSTO!=null && indValidacionSTO.equals(Constants.SI)){
	        ProcesoSTOExecutionService procesoSTOExecutionService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
	    	AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC,Constants.STO_ACCI_VALI_AUTO);    			
		        procesoSTOExecutionService.execute(accionTipoDocumento,params, null);	
			}
		}
        
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRRecepcionarOCSWebDDForm form = new InterfazOCRRecepcionarOCSWebDDForm();
		return form;
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
			InterfazOCRRecepcionarOCSWebDDForm f  =  (InterfazOCRRecepcionarOCSWebDDForm) this.formInterfaz;
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
	
	

	/**
	 * @return the ocrTipoRecepcionWebddList
	 */
	public List getOcrTipoRecepcionWebddList() {
		return ocrTipoRecepcionWebddList;
	}

	/**
	 * @param ocrTipoRecepcionWebddList
	 *            the ocrTipoRecepcionWebddList to set
	 */
	public void setOcrTipoRecepcionWebddList(List ocrTipoRecepcionWebddList) {
		this.ocrTipoRecepcionWebddList = ocrTipoRecepcionWebddList;
	}
	
	
	

}
