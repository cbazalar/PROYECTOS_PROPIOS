/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.spusicc.pedidos.model.DetallePedidoFolio;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.DetallePedidoFolioForm;

@ManagedBean  
@SessionScoped
public class DetallePedidoFolioAction extends BasePopupAbstractAction {
	
	private static final long serialVersionUID = -6749676948574503566L;

	private static final String PEDIDO_FOLIO = "PEDIDOFOLIO";
	
	private String oid;
	private String nAccion;
	
	private boolean indMostrarDatos;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		DetallePedidoFolioForm detallePedidoFolioForm = new DetallePedidoFolioForm();				
		return detallePedidoFolioForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'DetallePedidoFolioAction - setViewAtributes' method");
		}	
		DetallePedidoFolioForm detallePedidoFolioForm = (DetallePedidoFolioForm)this.getFormBusqueda();
		detallePedidoFolioForm.setCkhVerDatos(false);
		this.indMostrarDatos=false;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'DetallePedidoFolioAction - setFindAttributes' method");
		}
		
		DetallePedidoFolioForm detallePedidoFolioForm = (DetallePedidoFolioForm)this.getFormBusqueda();
		
		List lista = new ArrayList();
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("accion");
		
		log.debug("accion:" + accion);
		
		if(accion!=null){
			
			if(accion.equals(this.PEDIDO_FOLIO)){
				
				ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) 
						this.getBeanService("spusicc.procesoSTOEjecucionValidacionesService");
				
				String parametro = externalContext.getRequestParameterMap().get("parametro");
				this.nAccion="PEDIDO_FOLIO";
				
				lista = procesoSTOEjecucionValidacionesService.getDetallePedidoFolio(parametro);
				
				//			
				if(lista != null && lista.size() > 0)
				{
					//Objeto a session
					DetallePedidoFolio folio = (DetallePedidoFolio)lista.get(0);				
					try {
						BeanUtils.copyProperties(detallePedidoFolioForm, folio);
					} 
					catch (Exception e) {
						log.error(e.getMessage(), e);
					}				
				}	
				
			}
			
		}		
		if(StringUtils.equals(this.getnAccion(),"ABRIR_VENTANA")){
			ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) 
					this.getBeanService("spusicc.procesoSTOEjecucionValidacionesService");
			
			String parametro =this.getOid();
			
			lista = procesoSTOEjecucionValidacionesService.getDetallePedidoFolio(parametro);			
			//			
			if(lista != null && lista.size() > 0)
			{
				//Objeto a session
				DetallePedidoFolio folio = (DetallePedidoFolio)lista.get(0);				
				try {
					BeanUtils.copyProperties(detallePedidoFolioForm, folio);
				} 
				catch (Exception e) {
					log.error(e.getMessage(), e);
				}				
			}	
			
		}		
				
		return lista;
	}
	
	public void iniciaValores(){
		DetallePedidoFolioForm detallePedidoFolioForm = (DetallePedidoFolioForm)this.getFormBusqueda();
		detallePedidoFolioForm.setCkhVerDatos(false);
		this.indMostrarDatos=false;
		this.find();
		
	}
	
	public void salirVentana(ActionEvent event){
		try {
			this.redireccionarPagina("busquedaSTOReferenciaOperacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void mostrarDatos(){
		DetallePedidoFolioForm f = (DetallePedidoFolioForm)this.formBusqueda;
		if(this.indMostrarDatos)
			f.setCkhVerDatos(true);
		else
			f.setCkhVerDatos(false);
			
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getnAccion() {
		return nAccion;
	}

	public void setnAccion(String nAccion) {
		this.nAccion = nAccion;
	}

	public boolean isIndMostrarDatos() {
		return indMostrarDatos;
	}

	public void setIndMostrarDatos(boolean indMostrarDatos) {
		this.indMostrarDatos = indMostrarDatos;
	}
	
	

}
