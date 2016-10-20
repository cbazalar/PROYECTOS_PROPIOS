/**
 * 
 */
package biz.belcorp.ssicc.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.web.form.InterfazComponentesPaqueteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class InterfazComponentesPaqueteAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5735318099317699990L;
	
	private List interfazNoAsignadasList = new ArrayList();
	
	private List interfazAsignadasList = new ArrayList();
	private DataTableModel dataModelInterfazAsignadasList = new DataTableModel();
	private Map selection = new HashMap();
	
	private Boolean boolIndicadorControl = false;
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoBASConfiguracionInterfazList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		// TODO Auto-generated method stub
		return "interfazComponentesPaqueteForm.created";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - InterfazComponentesPaqueteAction");
		}
		
		InterfazComponentesPaqueteForm f = (InterfazComponentesPaqueteForm) this.formMantenimiento;
		InterfazService svc = (InterfazService) getBean("sisicc.interfazService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				
		boolean isNew = f.isNewRecord();
		
		
        try {
            if (isNew) {
            	
            	if(this.boolIndicadorControl)
            		f.setIndicadorControl(Constants.NUMERO_UNO);
            	else
            		f.setIndicadorControl(Constants.NUMERO_CERO);
            	
        		Map criteria = new HashMap();
        		criteria.put("codigoPais", f.getCodigoPais());
        		criteria.put("codigoSistema", f.getCodigoSistema());
        		criteria.put("codigoInterfazPaquete", f.getCodigoInterfaz());
        		criteria.put("codigoInterfazUnitaria", f.getInterfacesNoAsignadas());
        		criteria.put("ordenEjecucion", f.getOrdenEjecucion());
        		criteria.put("ordenHilo", f.getOrdenMultihilo());
        		criteria.put("nivelHilo", f.getNivelMultihilo());
        		criteria.put("indicadorControl", f.getIndicadorControl());
        		
        		if(StringUtils.equalsIgnoreCase(svc.getInterfazComponente(criteria), Constants.NUMERO_CERO)){
        			svc.insertComponenteInterfazPaqueteMante(criteria);			    	
        		}else{       			
        			this.addError("Error:", this.getResourceMessage("interfazComponentesPaqueteForm.error"));
    	            return false;
        		}
		    	//volvemos a cargar las listas
//		    	InterfazPK pk = new InterfazPK(f.getCodigoPais(),f.getCodigoSistema(),f.getCodigoInterfaz());
//				
//				Interfaz interfaz = svc.getInterfaz(pk);
				
//				request.getSession().setAttribute(Constants.INTERFAZ_DATA,interfaz);
//				request.getSession().setAttribute(Constants.INTERFAZ_NO_ASIGNADAS_LIST,svc.getInterfacesNoAsignadas(interfaz));
//				request.getSession().setAttribute(Constants.INTERFAZ_ASIGNADAS_LIST,svc.getInterfacesAsignadasList(interfaz));
            }
        }
        catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();            
            this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
            return false;
        }
        catch (InvalidDescriptionException ide) {
            String descripcion = ide.getDescription();         
            this.addError("Error: ", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
            return false;
        }        
		return true;		
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		
		if(log.isDebugEnabled()){
			log.debug("setAddAttributes - InterfazComponentesPaqueteAction");
		}
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazComponentesPaqueteForm f = new InterfazComponentesPaqueteForm(); 
		this.boolIndicadorControl = false;
		this.interfazAsignadasList = new ArrayList();
		this.dataModelInterfazAsignadasList = new DataTableModel();
		this.selection = new HashMap();
		
		Interfaz interfazSeleccionado = (Interfaz) this.beanRegistroSeleccionado;
		String id = interfazSeleccionado.getCodigo();
		
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		
		if (id != null) {
			InterfazService svc = (InterfazService) getBean("sisicc.interfazService");
			
			InterfazPK pk = new InterfazPK(interfazSeleccionado.getCodigoPais(), interfazSeleccionado.getCodigoSistema(), interfazSeleccionado.getCodigo());
			
			Interfaz interfaz = svc.getInterfaz(pk);
			
			BeanUtils.copyProperties(f, interfaz);
			f.setCodigoInterfaz(interfaz.getCodigo());
			f.setInterfacesNoAsignadas(null);
			f.setOrdenEjecucion(null);
			f.setOrdenMultihilo(null);
			f.setNivelMultihilo(null);
			f.setIndicadorControl(null);

			this.interfazNoAsignadasList = svc.getInterfacesNoAsignadas(interfaz);
			this.interfazAsignadasList = svc.getInterfacesAsignadasList(interfaz);
			this.dataModelInterfazAsignadasList = new DataTableModel(this.interfazAsignadasList);
		}
		
		return f;
	}
	
	public void delete(ActionEvent event){
		
		try {
			InterfazService svc = (InterfazService) getBean("sisicc.interfazService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			InterfazComponentesPaqueteForm f = (InterfazComponentesPaqueteForm) this.formMantenimiento;
			
			Map seleccionado = this.selection;
			
//			String id = request.getParameter("id");		
			String codigoPais = (String) seleccionado.get("codigoPais"); 
			String codigoSistema = (String) seleccionado.get("codigoSistema"); 
			String codigoInterfazPaquete = (String) seleccionado.get("codigoPaquete"); 
			String codigoInterfazUnitaria = (String) seleccionado.get("codigoInterfaz"); 
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoSistema", codigoSistema);
			criteria.put("codigoInterfazPaquete", codigoInterfazPaquete);
			criteria.put("codigoInterfazUnitaria", codigoInterfazUnitaria);
			
				svc.deleteComponentesInterfazPaqueteMante(criteria);
			
			//volvemos a cargar las listas
	    	InterfazPK pk = new InterfazPK(f.getCodigoPais(),f.getCodigoSistema(),f.getCodigoInterfaz());
			
			Interfaz interfaz = svc.getInterfaz(pk);
			
			this.interfazNoAsignadasList = svc.getInterfacesNoAsignadas(interfaz);
			this.interfazAsignadasList = svc.getInterfacesAsignadasList(interfaz);
			this.dataModelInterfazAsignadasList = new DataTableModel(this.interfazAsignadasList);							
			
			this.setMensajeAlertaDefaultAction(this.getResourceMessage("interfazComponentesPaqueteForm.deleted"));
			RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
			String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
			this.getRequestContext().execute(ventana);
			
			
		} catch (Exception e) {

			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		String mensaje = null;
		
		if(StringUtils.equals(accion, "ELIMINAR")){			
			if (this.selection == null) {
				mensaje = this.getResourceMessage("errors.select.item");
			}  
		}
		
		return mensaje;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		this.salirGrabarPantallaPadre = true;
		this.invocarFindLuegoGrabar = false;
	}

	/**
	 * @return the interfazNoAsignadasList
	 */
	public List getInterfazNoAsignadasList() {
		return interfazNoAsignadasList;
	}

	/**
	 * @param interfazNoAsignadasList the interfazNoAsignadasList to set
	 */
	public void setInterfazNoAsignadasList(List interfazNoAsignadasList) {
		this.interfazNoAsignadasList = interfazNoAsignadasList;
	}

	/**
	 * @return the interfazAsignadasList
	 */
	public List getInterfazAsignadasList() {
		return interfazAsignadasList;
	}

	/**
	 * @param interfazAsignadasList the interfazAsignadasList to set
	 */
	public void setInterfazAsignadasList(List interfazAsignadasList) {
		this.interfazAsignadasList = interfazAsignadasList;
	}

	/**
	 * @return the boolIndicadorControl
	 */
	public Boolean getBoolIndicadorControl() {
		return boolIndicadorControl;
	}

	/**
	 * @param boolIndicadorControl the boolIndicadorControl to set
	 */
	public void setBoolIndicadorControl(Boolean boolIndicadorControl) {
		this.boolIndicadorControl = boolIndicadorControl;
	}

	/**
	 * @return the dataModelInterfazAsignadasList
	 */
	public DataTableModel getDataModelInterfazAsignadasList() {
		return dataModelInterfazAsignadasList;
	}

	/**
	 * @param dataModelInterfazAsignadasList the dataModelInterfazAsignadasList to set
	 */
	public void setDataModelInterfazAsignadasList(
			DataTableModel dataModelInterfazAsignadasList) {
		this.dataModelInterfazAsignadasList = dataModelInterfazAsignadasList;
	}

	/**
	 * @return the selection
	 */
	public Map getSelection() {
		return selection;
	}

	/**
	 * @param selection the selection to set
	 */
	public void setSelection(Map selection) {
		this.selection = selection;
	}	
}
