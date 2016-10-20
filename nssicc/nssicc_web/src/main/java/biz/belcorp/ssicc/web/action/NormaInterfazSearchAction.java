package biz.belcorp.ssicc.web.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.sisicc.model.TipoFormatoArchivo;
import biz.belcorp.ssicc.service.NormaInterfazService;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.TipoFormatoArchivoService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.web.form.NormaInterfazForm;
import biz.belcorp.ssicc.web.form.NormaInterfazSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@ManagedBean
@SessionScoped
public class NormaInterfazSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private List listaTiposFormatoArchivo;
	private boolean flagRellenoNumerico;
	private boolean flagRellenoAlfanumerico;
	private boolean flagAlineamientoNumerico;
	private boolean flagAlineamientoAlfanumerico;
	private boolean flagFecha;
	private boolean flagTruncamientoNumerico;
	private boolean flagTruncamientoAlfanumerico;

	private List normaInterfazList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoBASNormaFormatoContenidoInterfazForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		NormaInterfazSearchForm searchForm = new NormaInterfazSearchForm();
		return searchForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setFindAttributes' method");
		}

		NormaInterfazSearchForm searchForm = (NormaInterfazSearchForm) this.formBusqueda;

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);

		// La busqueda solo la realizaremos en las interfaces activas
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		NormaInterfazService service = (NormaInterfazService) this
				.getBeanService("sisicc.normaInterfazService");

		List lista = service.getNormasByCriteria(criteria);

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		// Creamos las instancias de los objetos a usar
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		SistemaService sistemaService = (SistemaService) getBean("sisicc.sistemaService");
		Sistema sistema = new Sistema();
		sistema.setCodigoPais(pais.getCodigo());

		normaInterfazList = sistemaService.getSistemas(sistema);
		NormaInterfaz registro = (NormaInterfaz) this.beanRegistroSeleccionado;

		String codigoPais = registro.getCodigoPais();
		String codigo = registro.getCodigo();

		if (log.isDebugEnabled()) {
			log.debug("Id seleccionado de la lista: " + codigoPais);
		}
		// Todas las excepciones son capturadas por ActionExceptionHandler
		NormaInterfazService service = (NormaInterfazService) getBean("sisicc.normaInterfazService");
		NormaInterfazPK pk = new NormaInterfazPK(codigoPais, codigo);
		service.removeNormaInterfaz(pk, usuario);

		addInfo("Mensaje", getResourceMessage("normaInterfaz.deleted"));

		if (log.isDebugEnabled()) {
			log.debug("ID normaInterfaz eliminado: " + codigoPais + codigo);
		}

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes
	 * ()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		TipoFormatoArchivoService tipoFormatoArchivoService = (TipoFormatoArchivoService) this
				.getBeanService("sisicc.tipoFormatoArchivoService");

		this.listaTiposFormatoArchivo = tipoFormatoArchivoService
				.getTiposFormatoArchivo(null);

		this.mostrarBotonConsultar = false;
	}

	/**
	 * @return the listaTiposFormatoArchivo
	 */
	public List getListaTiposFormatoArchivo() {
		return listaTiposFormatoArchivo;
	}

	/**
	 * @param listaTiposFormatoArchivo
	 *            the listaTiposFormatoArchivo to set
	 */
	public void setListaTiposFormatoArchivo(List listaTiposFormatoArchivo) {
		this.listaTiposFormatoArchivo = listaTiposFormatoArchivo;
	}

	@Override
	protected String getSalirForward() {

		return "mantenimientoBASNormaFormatoContenidoInterfazList";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		// Extraemos atributos y parámetros a usar

		NormaInterfazForm f = (NormaInterfazForm) this.formMantenimiento;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		if (flagRellenoNumerico)
			f.setFlagRellenoNumerico("S");
		else
			f.setFlagRellenoNumerico("N");

		if (flagRellenoAlfanumerico)
			f.setFlagRellenoAlfanumerico("S");
		else
			f.setFlagRellenoAlfanumerico("N");

		if (flagAlineamientoNumerico)
			f.setFlagAlineamientoNumerico("S");
		else
			f.setFlagAlineamientoNumerico("N");

		if (flagAlineamientoAlfanumerico)
			f.setFlagAlineamientoAlfanumerico("S");
		else
			f.setFlagAlineamientoAlfanumerico("S");

		if (flagFecha)
			f.setFlagFecha("S");
		else
			f.setFlagFecha("N");

		if (flagTruncamientoNumerico)
			f.setFlagTruncamientoNumerico("S");
		else
			f.setFlagTruncamientoNumerico("N");

		if (flagTruncamientoAlfanumerico)
			f.setFlagTruncamientoAlfanumerico("S");
		else
			f.setFlagTruncamientoAlfanumerico("N"); 

		boolean isNew = f.isNewRecord();

		// Extreamos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Creamos la instancia del servicio y le asignamos el usuario que va a
		// realizar las operaciones
		NormaInterfazService normaService = (NormaInterfazService) getBean("sisicc.normaInterfazService");
		TipoFormatoArchivo tipo = new TipoFormatoArchivo();
		tipo.setCodigo(f.getCodigoTipoFormatoArchivo());
		tipo.setDescripcion(f.getTipoFormatoArchivo());
		tipo.setEstado(f.getEstado());
				
		NormaInterfaz norma = new NormaInterfaz();
//		BeanUtils.copyProperties(norma, f);	
		
		norma.setCodigoPais(f.getCodigoPais());
		norma.setCodigo(f.getCodigo());
		norma.setCodigoTipoFormatoArchivo(f.getCodigoTipoFormatoArchivo());
		norma.setTipoFormatoArchivo(tipo);
		norma.setFlagRellenoNumerico(f.getFlagRellenoNumerico());
		norma.setRellenoNumerico(f.getRellenoNumerico());
		norma.setFlagRellenoAlfanumerico(f.getFlagRellenoAlfanumerico());
		norma.setRellenoAlfanumerico(f.getRellenoAlfanumerico());
		norma.setFlagAlineamientoNumerico(f.getFlagAlineamientoNumerico());
		norma.setAlineamientoNumerico(f.getAlineamientoNumerico());
		norma.setFlagAlineamientoAlfanumerico(f.getFlagAlineamientoAlfanumerico());
		norma.setAlineamientoAlfanumerico(f.getAlineamientoAlfanumerico());
		norma.setFlagFecha(f.getFlagFecha());
		norma.setFormatoFecha(f.getFormatoFecha());
		norma.setFlagTruncamientoNumerico(f.getFlagTruncamientoNumerico());
		norma.setTruncamientoNumerico(f.getTruncamientoNumerico());
		norma.setFlagTruncamientoAlfanumerico(f.getFlagTruncamientoAlfanumerico());
		norma.setTruncamientoAlfanumerico(f.getTruncamientoNumerico());
		norma.setEstado(f.getEstado());
		
		try {
				// agregamos los mensajes de exito
			if (isNew) {
				normaService.insertNormaInterfaz(norma, usuario);
				/*
				 * messages.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage( "normaInterfaz.added")); // salvamos los
				 * mensajes en la sesión para que persistan luego del redirect
				 */
				addInfo("Mensaje", getResourceMessage("normaInterfaz.added"));
				return true;
			} else {
				normaService.updateNormaInterfaz(norma, usuario);
				/*
				 * messages.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage("normaInterfaz.updated"));
				 * saveMessages(request, messages);
				 */
				addInfo("Mensaje", getResourceMessage("normaInterfaz.updated"));
				return true;
			}
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			/*
			 * messages.add(ActionErrors.GLOBAL_MESSAGE, new
			 * ActionMessage("errors.invalid.id", codigo)); saveErrors(request,
			 * messages);
			 */
			addInfo("Mensaje", getResourceMessage("errors.invalid.id"));
			return false;
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			/*
			 * messages.add(ActionErrors.GLOBAL_MESSAGE, new
			 * ActionMessage("errors.invalid.description", descripcion));
			 * saveErrors(request, messages);
			 */
			addInfo("Mensaje", getResourceMessage("errors.invalid.description"));
			return false;
		}

		// Actualizamos las listas que se encuentran en el contexto
		// StartupListener.setupContext(getServlet().getServletContext());

	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}

		NormaInterfazForm f = new NormaInterfazForm();

		String id=null;
		NormaInterfaz registro = (NormaInterfaz) this.beanRegistroSeleccionado;
		if(registro!=null){
			 id = registro.getCodigo();
		}
		TipoFormatoArchivoService tipoFormatoArchivoService = (TipoFormatoArchivoService) getBean("sisicc.tipoFormatoArchivoService");
		NormaInterfazService normaService = (NormaInterfazService) getBean("sisicc.normaInterfazService");

		// request.getSession().setAttribute(Constants.ALL_TIPOS_FORMATO_ARCHIVO,
		// tipoFormatoArchivoService.getTiposFormatoArchivo(null));

		// Si el id ha sido enviado, buscamos la informacion
		// en caso contrario, no hacemos nada, se esta insertando
		// un nuevo registro a la aplicación
			
		if (id != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
		NormaInterfazPK pk = new NormaInterfazPK(registro.getCodigoPais(),
				registro.getCodigo());
		NormaInterfaz norma = normaService.getNormaInterfaz(pk);

		// Copiamos los atributos del bean al form
		BeanUtils.copyProperties(f, norma);
		TipoFormatoArchivo tipoFormatoArchivo;
		tipoFormatoArchivo = (TipoFormatoArchivo) norma.getTipoFormatoArchivo();
		f.setTipoFormatoArchivo(tipoFormatoArchivo.getDescripcion());
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		if (registro.getFlagRellenoNumerico().equals("S"))
			setFlagRellenoNumerico(true);

		if (registro.getFlagRellenoAlfanumerico().equals("S"))
			setFlagRellenoAlfanumerico(true);

		if (registro.getFlagAlineamientoNumerico().equals("S"))
			setFlagAlineamientoNumerico(true);

		if (registro.getFlagAlineamientoAlfanumerico().equals("S"))
			setFlagAlineamientoAlfanumerico(true);

		if (registro.getFlagFecha().equals("S"))
			setFlagFecha(true);

		if (registro.getFlagTruncamientoNumerico().equals("S"))
			setFlagTruncamientoNumerico(true);

		if (registro.getFlagTruncamientoAlfanumerico().equals("S"))
			setFlagTruncamientoAlfanumerico(true);

		// updateFormBean(mapping, request, normaInterfazForm);
		f.setNewRecord(false);
		this.beanRegistroSeleccionado = null;
		
		}
		return f;
		
	}

	/**
	 * @return the flagRellenoNumerico
	 */
	public boolean isFlagRellenoNumerico() {
		return flagRellenoNumerico;
	}

	/**
	 * @param flagRellenoNumerico
	 *            the flagRellenoNumerico to set
	 */
	public void setFlagRellenoNumerico(boolean flagRellenoNumerico) {
		this.flagRellenoNumerico = flagRellenoNumerico;
	}

	/**
	 * @return the normaInterfazList
	 */
	public List getNormaInterfazList() {
		return normaInterfazList;
	}

	/**
	 * @param normaInterfazList
	 *            the normaInterfazList to set
	 */
	public void setNormaInterfazList(List normaInterfazList) {
		this.normaInterfazList = normaInterfazList;
	}

	/**
	 * @return the flagRellenoAlfanumerico
	 */
	public boolean isFlagRellenoAlfanumerico() {
		return flagRellenoAlfanumerico;
	}

	/**
	 * @param flagRellenoAlfanumerico
	 *            the flagRellenoAlfanumerico to set
	 */
	public void setFlagRellenoAlfanumerico(boolean flagRellenoAlfanumerico) {
		this.flagRellenoAlfanumerico = flagRellenoAlfanumerico;
	}

	/**
	 * @return the flagAlineamientoNumerico
	 */
	public boolean isFlagAlineamientoNumerico() {
		return flagAlineamientoNumerico;
	}

	/**
	 * @param flagAlineamientoNumerico
	 *            the flagAlineamientoNumerico to set
	 */
	public void setFlagAlineamientoNumerico(boolean flagAlineamientoNumerico) {
		this.flagAlineamientoNumerico = flagAlineamientoNumerico;
	}

	/**
	 * @return the flagAlineamientoAlfanumerico
	 */
	public boolean isFlagAlineamientoAlfanumerico() {
		return flagAlineamientoAlfanumerico;
	}

	/**
	 * @param flagAlineamientoAlfanumerico
	 *            the flagAlineamientoAlfanumerico to set
	 */
	public void setFlagAlineamientoAlfanumerico(
			boolean flagAlineamientoAlfanumerico) {
		this.flagAlineamientoAlfanumerico = flagAlineamientoAlfanumerico;
	}

	/**
	 * @return the flagFecha
	 */
	public boolean isFlagFecha() {
		return flagFecha;
	}

	/**
	 * @param flagFecha
	 *            the flagFecha to set
	 */
	public void setFlagFecha(boolean flagFecha) {
		this.flagFecha = flagFecha;
	}

	/**
	 * @return the flagTruncamientoNumerico
	 */
	public boolean isFlagTruncamientoNumerico() {
		return flagTruncamientoNumerico;
	}

	/**
	 * @param flagTruncamientoNumerico
	 *            the flagTruncamientoNumerico to set
	 */
	public void setFlagTruncamientoNumerico(boolean flagTruncamientoNumerico) {
		this.flagTruncamientoNumerico = flagTruncamientoNumerico;
	}

	/**
	 * @return the flagTruncamientoAlfanumerico
	 */
	public boolean isFlagTruncamientoAlfanumerico() {
		return flagTruncamientoAlfanumerico;
	}

	/**
	 * @param flagTruncamientoAlfanumerico
	 *            the flagTruncamientoAlfanumerico to set
	 */
	public void setFlagTruncamientoAlfanumerico(
			boolean flagTruncamientoAlfanumerico) {
		this.flagTruncamientoAlfanumerico = flagTruncamientoAlfanumerico;
	}

}
