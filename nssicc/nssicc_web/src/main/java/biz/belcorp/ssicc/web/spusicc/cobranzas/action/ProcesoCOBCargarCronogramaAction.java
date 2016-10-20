package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBCargarCronogramaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ProcesoCOBCargarCronogramaForm;


/**
 * The Class ProcesoCOBCargarCronogramaAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/05/2014
 */

@ManagedBean  
@SessionScoped
public class ProcesoCOBCargarCronogramaAction extends BaseCargaArchivoAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List listaTipoEtapa = new ArrayList();
	private List cobCargarCronogramaList = new ArrayList();

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		ProcesoCOBCargarCronogramaService service = (ProcesoCOBCargarCronogramaService) getBean("spusicc.procesoCOBCargarCronogramaService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		criteria.put("indicadorCobranzaExterna", Constants.SI);
		this.listaTipoEtapa = service.getTiposEtapa(criteria);
		ProcesoCOBCargarCronogramaForm f = (ProcesoCOBCargarCronogramaForm)this.formCargaArchivo;
		this.cobCargarCronogramaList = new ArrayList(); 
		f.setFlagUpload(true);
		f.setFlagValidar(false);		
		f.setFlagProcesar(false);
		f.setFlagMostrarErrores(false);
		
	}	
	
	@Override
	public BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria) throws Exception {
		ProcesoCOBCargarCronogramaForm f = (ProcesoCOBCargarCronogramaForm) this.formCargaArchivo;
		
		ProcesoCOBCargarCronogramaService service = (ProcesoCOBCargarCronogramaService) getBean("spusicc.procesoCOBCargarCronogramaService");
		String ruta = service.obtenerPathUpload(criteria);//!=null?
				//service.obtenerPathUpload(criteria): service.obtenerPathUploadEstandar(criteria)!=null?
					//	service.obtenerPathUploadEstandar(criteria):this.obtenerPathPersonalizado();
		log.debug("ruta: " + ruta);
		f.setDirectorioTemporal(ruta);
		
		f.setFlagValidar(true);
		f.setFlagUpload(false);
		return f;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setValidarAttributes(java.util.Map)
	 */
	protected List setValidarAttributes(Map<String, Object> datos) throws Exception {
		ProcesoCOBCargarCronogramaForm f = (ProcesoCOBCargarCronogramaForm)this.formCargaArchivo;
		String strMessage = null;	
		datos.put("codigoEtapa", f.getTipoEtapa());
		datos.put("descripcionEtapa", f.getDescripcionTipoEtapa());
				
		ProcesoCOBCargarCronogramaService service = (ProcesoCOBCargarCronogramaService) getBean("spusicc.procesoCOBCargarCronogramaService");
		this.cobCargarCronogramaList =  service.executeValidarCargarCronograma(datos);
	    
	    String descripcionEtapa = (String) datos.get("descripcionEtapa");
		f.setDescripcionTipoEtapa(descripcionEtapa);
		String registroOK = (String) datos.get("registroOK");				
		
//		if (Constants.SI.equals(registroOK)) {
//			strMessage = "procesoCCCCargarCADMasivosUpload.msg.CargaDatos.ok";
//			this.errorValidar = false;
//			this.mostrarListaCarga = false;
//			this.addInfo("Información: ", this.getResourceMessage(strMessage));
//		}
//			
//		else {
//			this.errorValidar = true;
//			this.mostrarListaCarga = true;
//			strMessage = "procesoCCCCargarCADMasivosUpload.msg.CargaDatos.error";
//			this.addError("Error: ", this.getResourceMessage(strMessage));	
//		}	
		
		f.setFlagValidar(false);
		f.setFlagProcesar(false);
		this.mostrarListaCarga = false;
		this.errorValidar = true;
		if (Constants.SI.equals(registroOK)) {
			f.setFlagProcesar(true);
			this.mostrarListaCarga = true;
			this.errorValidar = false;
		}
		
		return this.cobCargarCronogramaList;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setAfterValidarAttributes(java.util.Map)
	 */
	@Override
	protected void setAfterValidarAttributes(Map<String, Object> datos)
			throws Exception {
		this.mostrarBotonValidar = false ;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#setProcesarAttributes(java.util.Map)
	 */
	protected void setProcesarAttributes(Map<String, Object> datos) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'procesar' method");
		}
		
		ProcesoCOBCargarCronogramaService service = (ProcesoCOBCargarCronogramaService) getBean("spusicc.procesoCOBCargarCronogramaService");
		ProcesoCOBCargarCronogramaForm f = (ProcesoCOBCargarCronogramaForm)this.formCargaArchivo;
		datos.put("lista", this.cobCargarCronogramaList);   
		
		service.executeProcesarCargarCronograma(datos);		
		return;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#getMensajeProcesarOK()
	 */
	protected String getMensajeProcesarOK() {
		String mensaje = "procesoCOBCargarCronogramaUpload.msg.procesa.ok";
		return this.getResourceMessage(mensaje);
	}
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseCargaArchivoForm devuelveFormCarga() throws Exception {
		ProcesoCOBCargarCronogramaForm f = new ProcesoCOBCargarCronogramaForm();
		return f;
	}
	
	/**
	 * Metodos GET -- SET
	 * 
	 */

	public List getListaTipoEtapa() {
		return listaTipoEtapa;
	}

	public void setListaTipoEtapa(List listaTipoEtapa) {
		this.listaTipoEtapa = listaTipoEtapa;
	}

	

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}




	

}
