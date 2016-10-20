/**
 * 
 */
package biz.belcorp.ssicc.web.seguridad.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.model.UsuarioOpcionHiperConsulta;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.seguridad.form.UsuarioHiperConsultaForm;
import biz.belcorp.ssicc.web.seguridad.form.UsuarioOpcionHiperConsultaForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class UsuarioOpcionHiperconsultaAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7850320668451794224L;
	
	private Usuario usuarioBusqueda ;
	private List listOpcionConsultaHiperconsulta = new ArrayList();
	
	private List listPermisoBloqueo = new ArrayList();
	private DataTableModel dataModelPermisoBloqueo = new DataTableModel();
	private UsuarioHiperConsultaForm[] selectionListPermisoBloqueo;

	@Override
	protected String getSalirForward() {
		return "usuarioList";
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
		return "usuarioOpcionHiperConsultaForm.updated";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'salvarOpcionHiperconsulta' method");
        }
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		UsuarioService service = (UsuarioService) this.getBean("usuarioService");
		String mensajes = null;
		
		UsuarioOpcionHiperConsultaForm usuarioOpcionHiperConsultaForm = (UsuarioOpcionHiperConsultaForm) this.formMantenimiento;
			       
	        List bloqueos = new ArrayList();                
	        if (this.listPermisoBloqueo != null && this.listPermisoBloqueo.size() > 0) {            
	            for (int i = 0; i < this.listPermisoBloqueo.size(); i++) {
	            	UsuarioOpcionHiperConsulta usuarioBloqueo = new  UsuarioOpcionHiperConsulta();
	            	UsuarioHiperConsultaForm aux = (UsuarioHiperConsultaForm) this.listPermisoBloqueo.get(i);
	                BeanUtils.copyProperties(usuarioBloqueo, aux);
	                bloqueos.add(usuarioBloqueo);
	            }            
	        }
		
	        //Ya tenemos todos los valores en una lista
	        //Lo que hacemos es enviarlo a la Base de datos
	        Map criteria = new HashMap();
	        criteria.put("codigoPais", usuarioOpcionHiperConsultaForm.getCodigoPais());
	        criteria.put("codigoUsuario", usuarioOpcionHiperConsultaForm.getCodigoUsuarioBloqueo());
	        service.removeOpcionesConsultaHiperConsultaUsuario(criteria);
	        service.insertOpcionesConsultaHiperConsultaUsuario(bloqueos);        
	        
	        return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'opcionHiperconsultaUsuario' method");
        }
	
		this.listPermisoBloqueo = new ArrayList();
		this.dataModelPermisoBloqueo = new DataTableModel(this.listPermisoBloqueo);
		this.selectionListPermisoBloqueo = null;
		 
		UsuarioOpcionHiperConsultaForm usuarioOpcionHiperConsultaForm = new UsuarioOpcionHiperConsultaForm();
		String id = this.usuarioBusqueda.getCodigo();
		// Si el id ha sido enviado, buscamos la informacion
        // en caso contrario, no hacemos nada, se esta insertando
        // un nuevo registro a la aplicación
		if (id != null) {
			UsuarioService usuarioService = (UsuarioService) this.getBean("usuarioService");
            PaisService paisService = (PaisService) this.getBean("paisService");
            
            Usuario usuario = usuarioService.getUsuario(id);
            Pais pais = paisService.getPais(usuario.getCodigoPais());
            
            usuarioOpcionHiperConsultaForm.setCodigoPais(usuario.getCodigoPais());
            usuarioOpcionHiperConsultaForm.setCodigoUsuarioBloqueo(usuario.getLogin());            
            usuarioOpcionHiperConsultaForm.setNombrePais(pais.getDescripcion());
            usuarioOpcionHiperConsultaForm.setLoginUsuario(usuario.getLogin());
            
          //Obtenemos los bloqueos del usuario     
            Map criteria = new HashMap();
            criteria.put("codigoPais", pais.getCodigo());
            List listaOpcionesConsulta =  usuarioService.getOpcionesConsultaHiperConsulta(criteria);
            this.listOpcionConsultaHiperconsulta = listaOpcionesConsulta;           
            
            List bloqueos = usuarioService.getOpcionesConsultaHiperConsultaByUsuario(usuario.getLogin());
            if (bloqueos != null) {
            	UsuarioHiperConsultaForm[] permisoBloqueoForm = new UsuarioHiperConsultaForm[bloqueos.size()];
                for (int i = 0; i < bloqueos.size(); i++) {
                	permisoBloqueoForm[i] = new UsuarioHiperConsultaForm();
                    BeanUtils.copyProperties(permisoBloqueoForm[i], bloqueos.get(i));
                    this.listPermisoBloqueo.add(permisoBloqueoForm[i]);
                }
                usuarioOpcionHiperConsultaForm.setOpcionHiperConsultaForm(permisoBloqueoForm);
                this.dataModelPermisoBloqueo = new DataTableModel(this.listPermisoBloqueo);
            }
            usuarioOpcionHiperConsultaForm.setOpcionHiperConsultaEliminarForm(new UsuarioHiperConsultaForm[0]);
		}
		
		return usuarioOpcionHiperConsultaForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		this.salirGrabarPantallaPadre = true;
        this.invocarFindLuegoGrabar = false;
		
	}

	/**
	 * Agrega parametros en la grilla hiperconsulta
	 * 
	 */
	public void addParametros(){
	       
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'addParametros' method");
        }
		
		String mensajes = null;
		
        // Extraemos atributos y parámetros a usar
        UsuarioOpcionHiperConsultaForm usuarioOpcionHiperConsultaForm = (UsuarioOpcionHiperConsultaForm) this.formMantenimiento;
        UsuarioHiperConsultaForm opcionConsultaForm = new UsuarioHiperConsultaForm();
        
        try {
			
        	BeanUtils.copyProperties(opcionConsultaForm, usuarioOpcionHiperConsultaForm);
            
            if (this.listPermisoBloqueo != null && this.listPermisoBloqueo.size() >0) {
            	//Buscamos si ya existe el tipo
            	boolean existe = false;
            	UsuarioHiperConsultaForm usuarioHiperConsultaFormAux = new UsuarioHiperConsultaForm();
            	for(int i=0; i< listPermisoBloqueo.size(); i++)
            	{
            		usuarioHiperConsultaFormAux = (UsuarioHiperConsultaForm) this.listPermisoBloqueo.get(i);
            		if(StringUtils.equals(usuarioOpcionHiperConsultaForm.getCodigoOpcionConsulta(), usuarioHiperConsultaFormAux.getCodigoOpcionConsulta()))
            		{
            			existe = true;
            			break;
            		}        		
            	}
            	
            	//Si no existe lo agregamos
        		if(!existe) {  
        			for (int i = 0; i < this.listOpcionConsultaHiperconsulta.size(); i++) {   
        				LabelValue lbv = (LabelValue) this.listOpcionConsultaHiperconsulta.get(i);
        				if(StringUtils.equals(usuarioOpcionHiperConsultaForm.getCodigoOpcionConsulta(), lbv.getValue())){
        					opcionConsultaForm.setNombreOpcionConsulta(lbv.getLabel());	
        					break;
        				}
        										
					}
        			
        			this.listPermisoBloqueo.add(opcionConsultaForm);
        			this.addInfo("Info: ", this.getResourceMessage("usuarioPermisoBloqueo.added"));
        		}
        		else
        		{	
        			this.addError("Error: ", this.getResourceMessage("usuarioPermisoBloqueo.already.exists"));	        			
        			return;
        		} 
            	
            }else{
            	this.listPermisoBloqueo = new ArrayList();
            	for (int i = 0; i < this.listOpcionConsultaHiperconsulta.size(); i++) {   
    				LabelValue lbv = (LabelValue) this.listOpcionConsultaHiperconsulta.get(i);
    				if(StringUtils.equals(usuarioOpcionHiperConsultaForm.getCodigoOpcionConsulta(), lbv.getValue())){
    					opcionConsultaForm.setNombreOpcionConsulta(lbv.getLabel());	
    					break;
    				}
            	}
            	this.listPermisoBloqueo.add(opcionConsultaForm);
            	this.addInfo("Info: ", this.getResourceMessage("usuarioPermisoBloqueo.added"));
            }
        	

            if (this.log.isDebugEnabled()) {
                this.log.debug("Nro de Opciones: " + (this.listPermisoBloqueo == null ? 0 : this.listPermisoBloqueo.size()));
            }
            
            //Limpiamos los valores agregados
            usuarioOpcionHiperConsultaForm.setCodigoOpcionConsulta("");
            usuarioOpcionHiperConsultaForm.setNombreOpcionConsulta("");
            
            this.formMantenimiento = usuarioOpcionHiperConsultaForm;
            this.dataModelPermisoBloqueo = new DataTableModel(this.listPermisoBloqueo);
		} catch (Exception e) {
			this.addError("Error:", this.obtieneMensajeErrorException(e));
		}
           
	}
	
	
	/**
	 * Elimina parametros en la grilla hiperconsulta
	 * 
	 */
	public void removeParametros(){
        if (log.isDebugEnabled()) {
            log.debug("Entering 'removeParametros' method");
        }  

        String mensajes = null;
        
        // Extraemos atributos y parámetros a usar
        UsuarioOpcionHiperConsultaForm usuarioOpcionHiperConsultaForm = (UsuarioOpcionHiperConsultaForm) this.formMantenimiento;
        
        if(verificarRegistrosSeleccionado()){
        	if (this.selectionListPermisoBloqueo != null) {
                for (int i = 0; i < this.selectionListPermisoBloqueo.length; i++) {
                	this.listPermisoBloqueo.remove(this.selectionListPermisoBloqueo[i]);	
				}
                
                this.dataModelPermisoBloqueo = new DataTableModel(this.listPermisoBloqueo);
               	//Salvamos la lista de permisos a eliminar
                usuarioOpcionHiperConsultaForm.setOpcionHiperConsultaEliminarForm(this.selectionListPermisoBloqueo);
            }
        	
        	mensajes = "usuarioOpcionHiperConsultaForm.removed";
            this.addInfo("Info: ", this.getResourceMessage(mensajes));
        }
	}
	
	/**
	 * Verifica si se selecciono fila a eliminar de grilla hiperconsulta
	 * 
	 * @return
	 */
	protected final boolean verificarRegistrosSeleccionado() {
		boolean verificar= true;		 
		try {
			if (this.selectionListPermisoBloqueo.length <= 0)															
				verificar = false;
		}	
		catch (Exception e) {		
			verificar = false;
		}
		if (!verificar) 
			this.addWarn("Warning: ", this.getResourceMessage("usuarioOpcionHiperConsultaForm.error.elemento.required"));
		return verificar;
	}
	
	/**
	 * @return the usuarioBusqueda
	 */
	public Usuario getUsuarioBusqueda() {
		return usuarioBusqueda;
	}

	/**
	 * @param usuarioBusqueda the usuarioBusqueda to set
	 */
	public void setUsuarioBusqueda(Usuario usuarioBusqueda) {
		this.usuarioBusqueda = usuarioBusqueda;
	}

	/**
	 * @return the listOpcionConsultaHiperconsulta
	 */
	public List getListOpcionConsultaHiperconsulta() {
		return listOpcionConsultaHiperconsulta;
	}

	/**
	 * @param listOpcionConsultaHiperconsulta the listOpcionConsultaHiperconsulta to set
	 */
	public void setListOpcionConsultaHiperconsulta(
			List listOpcionConsultaHiperconsulta) {
		this.listOpcionConsultaHiperconsulta = listOpcionConsultaHiperconsulta;
	}

	/**
	 * @return the listPermisoBloqueo
	 */
	public List getListPermisoBloqueo() {
		return listPermisoBloqueo;
	}

	/**
	 * @param listPermisoBloqueo the listPermisoBloqueo to set
	 */
	public void setListPermisoBloqueo(List listPermisoBloqueo) {
		this.listPermisoBloqueo = listPermisoBloqueo;
	}

	/**
	 * @return the dataModelPermisoBloqueo
	 */
	public DataTableModel getDataModelPermisoBloqueo() {
		return dataModelPermisoBloqueo;
	}

	/**
	 * @param dataModelPermisoBloqueo the dataModelPermisoBloqueo to set
	 */
	public void setDataModelPermisoBloqueo(DataTableModel dataModelPermisoBloqueo) {
		this.dataModelPermisoBloqueo = dataModelPermisoBloqueo;
	}

	/**
	 * @return the selectionListPermisoBloqueo
	 */
	public UsuarioHiperConsultaForm[] getSelectionListPermisoBloqueo() {
		return selectionListPermisoBloqueo;
	}

	/**
	 * @param selectionListPermisoBloqueo the selectionListPermisoBloqueo to set
	 */
	public void setSelectionListPermisoBloqueo(
			UsuarioHiperConsultaForm[] selectionListPermisoBloqueo) {
		this.selectionListPermisoBloqueo = selectionListPermisoBloqueo;
	}
	
	
} 
