package biz.belcorp.ssicc.web.spusicc.ventas.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.ventas.GrupoZonaVENService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoVENGrupoZonaBuscarZonaForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoVENGrupoZonaForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoVENGrupoZonaSearchForm;
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
public class MantenimientoVENGrupoZonaAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -6177009303644990499L;
	private List mantenimientoGrupoZonaList;
	private List agrupadoGrupoZonaList = new ArrayList();
	private List mantenimientoGrupoZonaBuscarZonaList = new ArrayList();
	
	@ManagedProperty(value="#{mantenimientoVENGrupoZonaAction2}")
	private MantenimientoVENGrupoZonaAction2 mantenimientoVENGrupoZonaAction2;
	
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoVENGrupoZonaForm";	
		
	}	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MantenimientoVENGrupoZonaSearchForm mantenimientoVENGrupoZonaSearchForm = new MantenimientoVENGrupoZonaSearchForm();
		return mantenimientoVENGrupoZonaSearchForm;
	}
	@Override
	protected List setFindAttributes() throws Exception {		
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'find' method");
		}
		
		GrupoZonaVENService service = (GrupoZonaVENService)this.getBeanService("spusicc.grupoZonaVENService");
		MantenimientoVENGrupoZonaSearchForm f = (MantenimientoVENGrupoZonaSearchForm)this.formBusqueda;
		
	//	Map criteria = BeanUtils.describe(f);		
		GrupoZona bgrupo = new GrupoZona();
		BeanUtils.copyProperties(bgrupo,f);
		
		List lista = service.getGrupoZona(bgrupo);
		setAgrupadoGrupoZonaList(lista);		
		
		return lista;
	}
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		
		MantenimientoVENGrupoZonaForm mantenimiento= (MantenimientoVENGrupoZonaForm)this.formMantenimiento;
		GrupoZona sistemabusqueda = (GrupoZona)this.beanRegistroSeleccionado;
		String id = sistemabusqueda.getCodigo();
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		if (id != null) {
			GrupoZona bgrupo = new GrupoZona();
			bgrupo.setCodigo(id);
			GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
			service.deleteGrupoZona(bgrupo,usuario);			
		} else {
			//mantenimientoGrupoZonaList.clear();
			
		}
	  return true;
	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoVENGrupoZonaSearchForm f = new MantenimientoVENGrupoZonaSearchForm(); 
		f.setCodigoPais(pais.getCodigo());
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);	
				
	}

	@Override
	protected String getSalirForward() {
				return "mantenimientoVENGrupoZonaList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		
		// Extraemos atributos y parámetros a usar
		
		MantenimientoVENGrupoZonaForm f = (MantenimientoVENGrupoZonaForm)this.formMantenimiento;
		
		// Extraemos el usuario de la sesión
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		// se corrigio el valor nulo de pais
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			
		GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
		
		GrupoZona bgrupo = new GrupoZona();
		BeanUtils.copyProperties(bgrupo,f);
		
		try {
			AuditInfo audi = usuario.getAuditInfo();	
			if (!f.isNewRecord()) { //Modificacion	
						audi.setUpdatedBy(usuario.getLogin());
						bgrupo.setAuditInfo(audi);
						service.updateGrupoZona(bgrupo, usuario);
						
						/*messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
								"sistema.updated"));
						saveMessages(request, messages);*/ // Cambiar por:
						List lista = service.getGrupoZona(bgrupo);
						this.listaBusqueda=lista;
						this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

						return true;					
				
				}
				else { //Insercion
					service.insertGrupoZona(bgrupo, usuario);
					List lista = service.getGrupoZona(bgrupo);
					this.listaBusqueda=lista;
					this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
					return true;
				}	
			
			
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			addInfo("Mensaje", getResourceMessage("error.invalid.id"));
			return false;
			
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			addInfo("Mensaje", getResourceMessage("error.invalid.description"));
			return false;
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {

		MantenimientoVENGrupoZonaForm f = (MantenimientoVENGrupoZonaForm)this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if(isNew){
			return "sistema.added";
		}else{
			return "sistema.updated";
		}
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		
		MantenimientoVENGrupoZonaForm f = new MantenimientoVENGrupoZonaForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setEditable(true);
		f.setNewRecord(true);
		
		if (f.isNewRecord()) {
			
			f.setCodigo(StringUtils.EMPTY);
			f.setDescripcion(StringUtils.EMPTY);
			f.setCodigoAgrupado(StringUtils.EMPTY);
		}
		
		SistemaService sistemaService = (SistemaService) getBean("sisicc.sistemaService");
		Sistema sistema = new Sistema();
		sistema.setCodigoPais(pais.getCodigo());
		
//		setMantenimientoGrupoZonaList(sistemaService.getSistemas(sistema));
		mantenimientoGrupoZonaList = sistemaService.getSistemas(sistema);
		GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
		GrupoZona sistemaBusqueda = (GrupoZona)this.beanRegistroSeleccionado;
//		String parametrosMantenimiento = sistemaBusqueda.getCodigo();
		
		if(!this.accion.equals(this.ACCION_NUEVO))
		{
			f.setNewRecord(false);
			String codigo = sistemaBusqueda.getCodigo();
			String descripcion = sistemaBusqueda.getDescripcion();
			String codigoAgrupado = sistemaBusqueda.getCodigoAgrupado();
			
			if(codigo != null && descripcion != null && codigoAgrupado != null)
			{
				GrupoZona bgrupo = new GrupoZona();
				GrupoZona bgrupofiltro = new GrupoZona();
				f.setCodigo(codigo);
				f.setDescripcion(descripcion);
				f.setCodigoAgrupado(codigoAgrupado);							
				List lista= service.getGrupoZona(bgrupo);
				bgrupofiltro = (GrupoZona)lista.get(0);
				//BeanUtils.copyProperties(f,lista);
				
				List resultadoAgrupado = service.getListaAgrupadoGrupoZona();
				//session.setAttribute(Constants.AGRUPADO_GRUPOZONA_LIST, resultadoAgrupado);
				setAgrupadoGrupoZonaList(resultadoAgrupado);
			}
		}
		
		List resultadoAgrupado = service.getListaAgrupadoGrupoZona();
		//session.setAttribute(Constants.AGRUPADO_GRUPOZONA_LIST, resultadoAgrupado);
		setAgrupadoGrupoZonaList(resultadoAgrupado);
		
		return f;
	}
	

	public List getMantenimientoGrupoZonaList() {
		return mantenimientoGrupoZonaList;
	}

	public void setMantenimientoGrupoZonaList(List mantenimientoGrupoZonaList) {
		this.mantenimientoGrupoZonaList = mantenimientoGrupoZonaList;
	}

	public List getAgrupadoGrupoZonaList() {
		return agrupadoGrupoZonaList;
	}

	public void setAgrupadoGrupoZonaList(List agrupadoGrupoZonaList) {
		this.agrupadoGrupoZonaList = agrupadoGrupoZonaList;
	}
	
	public void redireccionarpagina(ActionEvent actionEvent) throws Exception
	{

		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertazona' method");
		}
	
//		String id = request.getParameter("id");//codigo del grupo
		MantenimientoVENGrupoZonaBuscarZonaForm f = new MantenimientoVENGrupoZonaBuscarZonaForm();
		GrupoZona sistemaBusqueda = (GrupoZona)this.beanRegistroSeleccionado;
		
		String codigo = sistemaBusqueda.getCodigo();
		String descripcion = sistemaBusqueda.getDescripcion();
		String codigoAgrupado = sistemaBusqueda.getCodigoAgrupado();
		mantenimientoVENGrupoZonaAction2.setCodigo(codigo);
		mantenimientoVENGrupoZonaAction2.setDescripcion(descripcion);
		if (codigo != null && descripcion != null && codigoAgrupado != null) {
			GrupoZona bgrupo = new GrupoZona();
			GrupoZona bgrupofiltro = new GrupoZona();
			f.setCodigo(codigo);
			f.setDescripcion(descripcion);
			GrupoZonaVENService service = (GrupoZonaVENService) getBean("spusicc.grupoZonaVENService");
			List resultado= service.getGrupoZona(bgrupo);
			bgrupofiltro = (GrupoZona)resultado.get(0);
			
			BeanUtils.copyProperties(f,bgrupofiltro);
			resultado = service.getGrupoZonaZonas(bgrupo);
			
//			List resultadoAgrupado = service.getListaAgrupadoGrupoZona();
//			setMantenimientoGrupoZonaBuscarZonaList(resultadoAgrupado);
			//session.setAttribute(Constants.MANTENIMIENTO_GRUPOZONA_BUSCAR_ZONA_LIST,	resultado);
			mantenimientoVENGrupoZonaAction2.calcularValores(codigo,descripcion);
			this.redireccionarPagina("mantenimientoVENGrupoZonaBuscarZonaForm");	
		} 
		
	}
	public List getMantenimientoGrupoZonaBuscarZonaList() {
		return mantenimientoGrupoZonaBuscarZonaList;
	}
	public void setMantenimientoGrupoZonaBuscarZonaList(
			List mantenimientoGrupoZonaBuscarZonaList) {
		this.mantenimientoGrupoZonaBuscarZonaList = mantenimientoGrupoZonaBuscarZonaList;
	}
	/**
	 * @return the mantenimientoVENGrupoZonaAction2
	 */
	public MantenimientoVENGrupoZonaAction2 getMantenimientoVENGrupoZonaAction2() {
		return mantenimientoVENGrupoZonaAction2;
	}
	/**
	 * @param mantenimientoVENGrupoZonaAction2 the mantenimientoVENGrupoZonaAction2 to set
	 */
	public void setMantenimientoVENGrupoZonaAction2(
			MantenimientoVENGrupoZonaAction2 mantenimientoVENGrupoZonaAction2) {
		this.mantenimientoVENGrupoZonaAction2 = mantenimientoVENGrupoZonaAction2;
	}
}
