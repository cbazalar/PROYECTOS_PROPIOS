package biz.belcorp.ssicc.web.spusicc.ventas.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.ventas.GrupoZonaVENService;
import biz.belcorp.ssicc.web.form.BusquedaZonaPOPUPSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaClientesPOPUPSearchAction;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaZonaPOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.ProcesoCOMActualizacionEstatusEjecutivasForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoVENGrupoZonaBuscarZonaForm;
/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BusquedaZonaPOPUPSearchForm.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="jpescoran@gmail.com">Juan Pablo Pescoran</a>
 *
 */
@ManagedBean
@SessionScoped
public class MantenimientoVENGrupoZonaAction2 extends
		BaseMantenimientoSearchAbstractAction {

	private String codigo;
	private String descripcion;
	private List mantenimientoGrupoZonaBuscarZonaList;
	
	private boolean mostrarPopupBuscarZona;
	private static final String POPUP_BuscarZona = "POPUP_BuscarZona";
	
	@ManagedProperty(value="#{busquedaZonaPOPUPSearchAction}")
	private BusquedaZonaPOPUPSearchAction busquedaZonaPOPUPSearchAction;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoVENGrupoZonaBuscarZonaForm c = new MantenimientoVENGrupoZonaBuscarZonaForm();
		return c;
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

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		return null;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonBuscar = false;

		// Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		// MantenimientoVENGrupoZonaBuscarZonaForm f = new
		// MantenimientoVENGrupoZonaBuscarZonaForm();
		// f.setCodigoPais(pais.getCodigo());
		GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
		GrupoZona bgrupofiltro = new GrupoZona();
		GrupoZona bgrupo = new GrupoZona();
		bgrupo.setCodigo(codigo); //
		List resultado = service.getGrupoZona(bgrupo);
		bgrupofiltro = (GrupoZona) resultado.get(0);

		// BeanUtils.copyProperties(f,bgrupofiltro);
		resultado = service.getGrupoZonaZonas(bgrupo);

		this.listaBusqueda = resultado;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
	}

	public void calcularValores(String cod, String desc) {
		this.codigo = cod;
		this.descripcion = desc;

		GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
		GrupoZona bgrupofiltro = new GrupoZona();
		GrupoZona bgrupo = new GrupoZona();
		bgrupo.setCodigo(codigo); //
		List resultado = service.getGrupoZona(bgrupo);
		bgrupofiltro = (GrupoZona) resultado.get(0);

		// BeanUtils.copyProperties(f,bgrupofiltro);
		resultado = service.getGrupoZonaZonas(bgrupo);

		this.listaBusqueda = resultado;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

	}

	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_BuscarZona)){ 
			this.mostrarPopupBuscarZona = true;
		}
	}
	
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupBuscarZona' method");
		}
		
		this.mostrarProcesoBatch = true;
		this.mostrarPopupBuscarZona = false;
		if (accion.equals(this.POPUP_BuscarZona)) {
			this.busquedaZonaPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaZonaPOPUPSearchAction.isSeleccionoRegistro()) {				
				GrupoZona sistemaBusqueda = (GrupoZona)this.busquedaZonaPOPUPSearchAction.getBeanRegistroSeleccionado();
				MantenimientoVENGrupoZonaBuscarZonaForm f = new MantenimientoVENGrupoZonaBuscarZonaForm();				
				String codigoZona = sistemaBusqueda.getCodigoZona();
				f.setCodigoZona(codigoZona);
				//String nombre = cliente.get("nombre1").toString() +" " + cliente.get("apellido1").toString()+" "+cliente.get("apellido2").toString();				
				//f.setNombreCliente(nombre);
				this.busquedaZonaPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formBusqueda =  f;				
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupBuscarZona = false;
		this.busquedaZonaPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the descripcion 
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List getMantenimientoGrupoZonaBuscarZonaList() {
		return mantenimientoGrupoZonaBuscarZonaList;
	}

	public void setMantenimientoGrupoZonaBuscarZonaList(List mantenimientoGrupoZonaBuscarZonaList) {
		this.mantenimientoGrupoZonaBuscarZonaList = mantenimientoGrupoZonaBuscarZonaList;
	}
	
	
	public void cancelar(ActionEvent actionEvent)  
	{
		try {
			this.redireccionarPagina("mantenimientoVENGrupoZonaList");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	} 
	
	/*Insertar Zona*/
	public boolean InsertarZona(ActionEvent actionEvent)
	{

		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		
		MantenimientoVENGrupoZonaBuscarZonaForm f = (MantenimientoVENGrupoZonaBuscarZonaForm)this.formBusqueda;
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
		GrupoZona bgrupo = new GrupoZona();
		bgrupo.setCodigo(getCodigo());
		bgrupo.setCodigoZona(f.getCodigoZona());
		
		try {	
			GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
			service.insertZonaGrupoZona(bgrupo, usuario);
				
			List resultado = service.getGrupoZonaZonas(bgrupo);
			//session.setAttribute(Constants.MANTENIMIENTO_GRUPOZONA_BUSCAR_ZONA_LIST,	resultado);
			setMantenimientoGrupoZonaBuscarZonaList(resultado);			
			this.listaBusqueda = resultado;
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			
			/*messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"sistema.added"));
			saveMessages(request, messages);*/
			addInfo("Mensaje", getResourceMessage("sistema.added"));
			return true;
			
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			/*messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid.id", codigo));
			saveErrors(request, messages);*/
			this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
			return false;
			
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			/*messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
					"errors.invalid.description", descripcion));
			saveErrors(request, messages);	*/
			this.addError("Error: ", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
			return false;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return false;
		}	
			
	}
	
	/*Borrar Zona*/
	public boolean borrarZona(ActionEvent actionEvent) 

	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		
		GrupoZona registro = (GrupoZona)this.beanRegistroSeleccionado;
		MantenimientoVENGrupoZonaBuscarZonaForm f = new MantenimientoVENGrupoZonaBuscarZonaForm();
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		
		//if (id != null) {
			GrupoZona bgrupo = new GrupoZona();
			bgrupo.setCodigo(getCodigo());
			bgrupo.setCodigoZona(registro.getCodigoZona());
			//f.setCodigo(codigoGrupo);
			
			try {
				GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
				service.deleteZonaGrupoZona(bgrupo, usuario);
				List resultado = service.getGrupoZonaZonas(bgrupo);
				//session.setAttribute(Constants.MANTENIMIENTO_GRUPOZONA_BUSCAR_ZONA_LIST, resultado);
				setMantenimientoGrupoZonaBuscarZonaList(resultado);
				this.listaBusqueda = resultado;
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
				/*messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"sistema.deleted"));
				saveMessages(request, messages);*/
				addInfo("Mensaje", getResourceMessage("sistema.deleted"));
				return true;
				
			} catch (InvalidIdentifierException iie) {
				String codigo = iie.getIdentifier().toString();
				/*messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						"errors.invalid.id", codigo));
				saveErrors(request, messages);*/
				this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
				return false;
			} catch (InvalidDescriptionException ide) {
				String descripcion = ide.getDescription();
				/*messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
						"errors.invalid.description", descripcion));
				saveErrors(request, messages);	*/
				this.addError("Error: ", this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
				return false;
			}	catch (Exception e) {
				this.addError("Error: ", this.obtieneMensajeErrorException(e));
				return false;
			}
	
	}

	
	
	/**
	 * @return the busquedaZonaPOPUPSearchAction
	 */
	public BusquedaZonaPOPUPSearchAction getBusquedaZonaPOPUPSearchAction() {
		return busquedaZonaPOPUPSearchAction;
	}

	/**
	 * @param busquedaZonaPOPUPSearchAction the busquedaZonaPOPUPSearchAction to set
	 */
	public void setBusquedaZonaPOPUPSearchAction(
			BusquedaZonaPOPUPSearchAction busquedaZonaPOPUPSearchAction) {
		this.busquedaZonaPOPUPSearchAction = busquedaZonaPOPUPSearchAction;
	}

	/**
	 * @return the mostrarPopupBuscarZona
	 */
	public boolean isMostrarPopupBuscarZona() {
		return mostrarPopupBuscarZona;
	}

	/**
	 * @param mostrarPopupBuscarZona the mostrarPopupBuscarZona to set
	 */
	public void setMostrarPopupBuscarZona(boolean mostrarPopupBuscarZona) {
		this.mostrarPopupBuscarZona = mostrarPopupBuscarZona;
	}
}
