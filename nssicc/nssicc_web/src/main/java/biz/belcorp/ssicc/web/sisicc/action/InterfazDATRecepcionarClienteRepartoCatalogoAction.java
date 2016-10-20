package biz.belcorp.ssicc.web.sisicc.action;

import java.io.File;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazDATRecepcionarClienteRepartoCatalogoForm;

@ManagedBean
@SessionScoped
public class InterfazDATRecepcionarClienteRepartoCatalogoAction extends BaseInterfazAbstractAction{

	private static final long serialVersionUID = 403282976068972262L;


	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazDATRecepcionarClienteRepartoCatalogoForm f = new  InterfazDATRecepcionarClienteRepartoCatalogoForm();		
		return f;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#executeInterfaz(javax.faces.event.ActionEvent)
	 */
	public void executeInterfaz(ActionEvent actionEvent) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'executeInterfaz' method");
			}
			InterfazDATRecepcionarClienteRepartoCatalogoForm f = (InterfazDATRecepcionarClienteRepartoCatalogoForm) formInterfaz;
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
	        Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser();
	        super.executeInterfaz(actionEvent);
			
            try {
       
    			if (log.isDebugEnabled()) {
    				log.debug("Entering 'Uniendo archivos' method");
    			}
    			
    			 String nombreArchivoConsolidado = String.format("%s%s.%s", (String)this.getParamfiltros().get("nombreLogArchivo"), (String)this.getParamfiltros().get("numeroLote"), (String)this.getParamfiltros().get("extension"));
    			 
    			if (log.isDebugEnabled()) {
    				log.debug("Nombre archivo consolidado :'"+nombreArchivoConsolidado+"' method");
    			}
    			if (log.isDebugEnabled()) {
    				log.debug("Ubicacion archivo consolidado :'"+(String)this.getParamfiltros().get("directorioCabecera")+"' method");
    			}
    			


    			if (log.isDebugEnabled()) {
    				log.debug("Entering 'Enviando Mail' method");
    			}
           
    	         
    	         
    	         
    			BaseMailService mailService = (BaseMailService) this.getBean(this.getMailService());
    	        
    	         //correoOrigen
    	         //correoDefault
    	         this.getParamfiltros().put("correosDestinos", (String) this.getParamfiltros().get("destLogErrores"));
    	         //subject
    	         //fileAttachment	         
    	         File archivoAtachado = new File((String)this.getParamfiltros().get("directorioCabecera"),nombreArchivoConsolidado);
    	         this.getParamfiltros().put("fileAttachment", archivoAtachado);
    	         //nombreArchivoReporte
    	         this.getParamfiltros().put("nombreArchivoReporte", nombreArchivoConsolidado);
    	         
    	         this.getParamfiltros().put("bodyTxt", "MailTxtMensajeLibre.vm");
    	         this.getParamfiltros().put("bodyHtml", "MailTxtMensajeLibre.vm");
    	         String linea="<html> <head> <meta content='text/html; charset=ISO-8859-1' http-equiv='content-type'> <title> LOG DAT</title> </head> <body> <table style='text-align: left; width: 490px; height: 461px;' border='0' cellpadding='0' cellspacing='0'> <tbody> <tr> <td style='height: 172px; width: 256px;'> <font color='#4188b2' face='Arial' size='3'>Se ha realizado la recepción de <strong> Reparto Cátalogo </strong>&nbsp;desde el Sistema SSiCC correspondiente al pais&nbsp;<strong>"+pais.getDescripcion()+"</strong> <strong> </font> </td> </tr> </tbody> </table> <font face='Arial' size='1'><br> <strong> NOTA: Por favor no responda a este mensaje, es generado automáticamente desde una cuenta no monitoreada. </strong> <br> Proceso ejecutado por el Usuario <strong>"+usuario.getLogin()+"</strong>. <br> <br> Se adjunta el archivo correspondiente: </font> <br> <br> <br> </body> </html>";
    		        
    	         this.getParamfiltros().put("body", linea);
    	         
    	         this.getParamfiltros().put("parameterMap",this.getParamfiltros());
    	         
    	         MailParams mailParams = new MailParams();	
    	         mailParams.setQueryParams(this.getParamfiltros());
    	         
    	         mailService.enviarMail(mailParams);
    	         
    	         if (log.isDebugEnabled()) {
    					log.debug("Entering 'Envio exitoso' method");
    			 }
    	         
    		} catch (Exception ex) {
    			this.log.error(ex.getMessage(), ex);
    		}
    		
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
//	@Override
//
//    protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
//		InterfazLETEnviarTarjetasAsociadasForm f = (InterfazLETEnviarTarjetasAsociadasForm) formInterfaz;
//		
//		params = super.prepareParamsBeforeExecute(params, form);
//		
//		return params;
//    }
	
	
	public String getMailService () {
		String service = "sisicc.mailUtil";		
		
		return service;
	}
	 
}
