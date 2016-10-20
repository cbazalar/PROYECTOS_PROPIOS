package biz.belcorp.ssicc.web.sisicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import biz.belcorp.ssicc.web.sisicc.form.InterfazOCRRecepcionarOCSDemandaWebDDForm;

@ManagedBean
@SessionScoped
public class InterfazOCRRecepcionarOCSDemandaWebDDAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5611170690946634336L;

	private List ocrTipoRecepcionDemandaWebddList;
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazOCRRecepcionarOCSDemandaWebDDForm formInterfaz = new InterfazOCRRecepcionarOCSDemandaWebDDForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception
	{		
		String codigoPais = this.mPantallaPrincipalBean.getCurrentCountry().getCodigo();
		MantenimientoOCRCargaPedidoService service = (MantenimientoOCRCargaPedidoService) getBean("spusicc.pedidos.mantenimientoOCRCargaPedidoService");
		this.ocrTipoRecepcionDemandaWebddList = service.getTiposRecepcionMICAOCSDemandaWebDD();
		
		InterfazOCRRecepcionarOCSDemandaWebDDForm f = (InterfazOCRRecepcionarOCSDemandaWebDDForm) this.formInterfaz;
				
		GenericoService genericoService = (GenericoService)getBean("genericoService");
		String showValiAutoPediDigi = genericoService.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_PARAM_SHOW_VALI_PEDI_DIGI);		
		f.setTipoRecepcion(f.getCodigoInterfaz());
		f.setShowValiAutoPediDigi(showValiAutoPediDigi);		
		f.setIndValidacionSTO(Constants.SI);		
		f.setIndValidacionSTOBool(true);
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		
		InterfazOCRRecepcionarOCSDemandaWebDDForm f = (InterfazOCRRecepcionarOCSDemandaWebDDForm) this.formInterfaz;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		 
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if(!f.getIndValidacionSTOBool()){
			f.setIndValidacionSTO(Constants.NO);
		}
		
		Map queryParams = super.prepareParamsBeforeExecute(params, form);		
		String codigoPais = pais.getCodigo();
		
		ProcesoSTOService stoService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");		
		String numLoteSTO = stoService.updateLoteSTO(new TipoDocumentoDigitadoPK(codigoPais,Constants.STO_TIPO_DOCUMENTO_OCC));
				
		queryParams.put("codigoPais", codigoPais);
		queryParams.put("codigoInterfaz", f.getTipoRecepcion());	
		queryParams.put("usuario", usuario);
		 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String fechaDemanda = sdf.format(new Date(System.currentTimeMillis()));
		queryParams.put("fechaDemanda", fechaDemanda);
		queryParams.put("codTipoDocu", Constants.STO_TIPO_DOCUMENTO_OCC);
		queryParams.put("codigoUsuario", usuario.getLogin());
		queryParams.put("indOrigen", Constants.STO_ORIGEN_WEB);	
		queryParams.put("indicadorOCRComercial", pais.getIndicadorOCRComercial());
		queryParams.put("numLoteSTO",numLoteSTO); 
		queryParams.put("indValidacionSTO",f.getIndValidacionSTO());
		 
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		interfazSiCCService.updateInterfazProcesoBatch(queryParams);
			
		return queryParams;
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
			InterfazOCRRecepcionarOCSDemandaWebDDForm f  =  (InterfazOCRRecepcionarOCSDemandaWebDDForm) this.formInterfaz;
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

	public List getOcrTipoRecepcionDemandaWebddList() {
		return ocrTipoRecepcionDemandaWebddList;
	}

	public void setOcrTipoRecepcionDemandaWebddList(
			List ocrTipoRecepcionDemandaWebddList) {
		this.ocrTipoRecepcionDemandaWebddList = ocrTipoRecepcionDemandaWebddList;
	}
}
