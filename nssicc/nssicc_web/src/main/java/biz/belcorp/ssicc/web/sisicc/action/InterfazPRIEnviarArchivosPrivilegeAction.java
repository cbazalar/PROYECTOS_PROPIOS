package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.struts.action.ActionForm;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.scdf.InterfazSiCCService;
import biz.belcorp.ssicc.service.scdf.StickerService;
import biz.belcorp.ssicc.service.scdf.UltimasNoticiasService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPRIEnviarArchivosPrivilegeForm;

/**
 * The Class InterfazPRIEnviarArchivosPrivilegeAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 11/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazPRIEnviarArchivosPrivilegeAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -2622422417635168032L;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazPRIEnviarArchivosPrivilegeForm interfazPRIEnviarForm = new InterfazPRIEnviarArchivosPrivilegeForm();
		return interfazPRIEnviarForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {	
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'setViewAttributes' method");
		}
		
		InterfazPRIEnviarArchivosPrivilegeForm f = (InterfazPRIEnviarArchivosPrivilegeForm) this.formInterfaz;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
    }
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazHiloAbstractAction#executeProcessBeforeInterfaz(org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, java.util.Map)
     */
    protected Map executeProcessBeforeInterfaz(ActionForm form, HttpServletRequest request, Map params) throws Exception   {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'executeProcessBeforeInterfaz'");
        }
       
        // Obtenemos la información del usuario y pais
        String codigoPais = MapUtils.getString(params, "codigoPais");
        Usuario usuario = (Usuario) MapUtils.getObject(params, "usuario");

        if(log.isDebugEnabled()) {
            log.debug("Codigo de Pais: " + codigoPais);
        }
        
        // Obtenemos la referencia al service
        InterfazSiCCService siccService = (InterfazSiCCService) getBean("scdf.interfazSiCCService");
        PaisService paisService = (PaisService)getBean("paisService");
        Pais pais = paisService.getPais(codigoPais);
        
        // Realizamos la actualizacion de los Numeros de Boleta de Despacho
        if (log.isDebugEnabled()) {
            log.debug("Realizando la Actualizacion de Boletas de Despacho ...");
        }
        siccService.executeCargaNumeroBoletasDespacho(pais.getCodigo());
        if (log.isDebugEnabled()) {
            log.debug("Actualizacion de Boletas de Despacho finalizada");
        }

        // Ejecutamos el proceso encargado de generar los stickers
        StickerService stickerService = (StickerService) getBean("scdf.stickerService");
        if (log.isDebugEnabled()) {
            log.debug("Realizando la Generación de Stickers ...");
        }
        int total = stickerService.executeGeneracionStickers(pais, usuario);
        if (log.isDebugEnabled()) {
            log.debug("Generación de Stickers finalizada");
            log.debug("Se generaron " + total + " stickers.");
        }

        // Ejecutamos el proceso de generación de Ultimas Noticias Privilege
        UltimasNoticiasService ultimasNoticiasService = (UltimasNoticiasService) getBean("scdf.ultimasNoticiasService");
        if (log.isDebugEnabled()) {
            log
                    .debug("Realizando la generacion de Ultimas Noticias Privilege ...");
        }
        ultimasNoticiasService.executeGenerarUltimasNoticias(pais.getCodigo(),usuario);
        if (log.isDebugEnabled()) {
            log.debug("Generacion de Ultimas Noticias Privilege finalizada");
        }
        return params;
    }
	
	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map,
     *      biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
     */
    protected void afterExecuteInterfaz(Map params,
            InterfazExecutionResult interfazExecutionResult) throws Exception{
    	super.afterExecuteInterfaz(params, interfazExecutionResult);
        // Invocamos al metodo encargado de actualizar el status de los stickers
        // que determina si estos son enviados o no a Privilege de tal forma que
        // no sean considerados en un proximo envio
        if(interfazExecutionResult.ejecucionCompletada()) {
            String codigoPais = MapUtils.getString(params, "codigoPais");
            StickerService stickerService = (StickerService) getBean("scdf.stickerService");
            if (log.isDebugEnabled()) {
                log.debug("Actualizando el correlativo de Stickers ...");
            }
            stickerService.updateCorrelativoStickers(codigoPais);
            
            if (log.isDebugEnabled()) {
                log.debug("Realizando la Actualizacion de Status de Stickers ...");
            }
            stickerService.updateHistoricoStickerStatusByPais(codigoPais);
        }
    }

}