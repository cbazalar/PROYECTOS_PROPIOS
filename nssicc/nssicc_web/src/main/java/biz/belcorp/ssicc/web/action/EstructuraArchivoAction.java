package biz.belcorp.ssicc.web.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivoPK;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.EstructuraArchivoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.TipoDatoService;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.web.form.EstructuraArchivoForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
@ManagedBean
@SessionScoped
public class EstructuraArchivoAction extends BaseMantenimientoSearchAbstractAction {

	private Interfaz interfazData;  
	private List listaTiposDato;	  
	private Boolean mostrarDecimales;
	
	private DataTableModel listaEstructuraArchivoModel;
	private Object[] columnasSeleccionadas;

	private String seleccionoRegistros = Constants.NUMERO_CERO;
	
	private Interfaz interfazbusqueda;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4790291285638465844L;

	
	/**
	 * @return the interfazData
	 */
	public Interfaz getInterfazData() {
		return interfazData;
	}

	/**
	 * @param interfazData the interfazData to set
	 */
	public void setInterfazData(Interfaz interfazData) {
		this.interfazData = interfazData;
	}

	/**
	 * @return the listaTiposDato
	 */
	public List getListaTiposDato() {
		return listaTiposDato;
	}

	/**
	 * @param listaTiposDato the listaTiposDato to set
	 */
	public void setListaTiposDato(List listaTiposDato) {
		this.listaTiposDato = listaTiposDato;
	}

	/**
	 * @return the mostrarDecimales
	 */
	public Boolean getMostrarDecimales() {
		return mostrarDecimales;
	}

	/**
	 * @param mostrarDecimales the mostrarDecimales to set
	 */
	public void setMostrarDecimales(Boolean mostrarDecimales) {
		this.mostrarDecimales = mostrarDecimales;
	}

	/**
	 * @return the listaEstructuraArchivoModel
	 */
	public DataTableModel getListaEstructuraArchivoModel() {
		return listaEstructuraArchivoModel;
	}

	/**
	 * @param listaEstructuraArchivoModel the listaEstructuraArchivoModel to set
	 */
	public void setListaEstructuraArchivoModel(
			DataTableModel listaEstructuraArchivoModel) {
		this.listaEstructuraArchivoModel = listaEstructuraArchivoModel;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public Object[] getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(Object[] columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the seleccionoRegistros
	 */
	public String getSeleccionoRegistros() {
		return seleccionoRegistros;
	}

	/**
	 * @param seleccionoRegistros the seleccionoRegistros to set
	 */
	public void setSeleccionoRegistros(String seleccionoRegistros) {
		this.seleccionoRegistros = seleccionoRegistros;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoBASConfiguracionInterfazList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		// Extraemos atributos y parmetros a usar
		EstructuraArchivoForm estructuraArchivoForm = (EstructuraArchivoForm) this.formMantenimiento;

		boolean isNew = estructuraArchivoForm.isNewRecord();

		// Extreamos el usuario de la sesin
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		EstructuraArchivoService service = (EstructuraArchivoService) getBean("sisicc.estructuraArchivoService");
		EstructuraArchivo estructuraArchivo = new EstructuraArchivo();
		BeanUtils.copyProperties(estructuraArchivo, estructuraArchivoForm);
		InterfazPK pk = new InterfazPK(estructuraArchivo.getCodigoPais(), estructuraArchivo.getCodigoSistema(), estructuraArchivo.getCodigoInterfaz());

		try {
			// agregamos los mensajes de exito
			if (isNew) {
				service.insertEstructuraArchivo(estructuraArchivo, usuario);
			} 
			else 
			{
				service.updateEstructuraArchivo(estructuraArchivo, usuario);
			}

			// Recargamos la lista de campos
			service.updatePosicion(pk, usuario);
			
			List listaEstructura = service.getEstructuraArchivo(pk);
			this.listaEstructuraArchivoModel = new DataTableModel(listaEstructura);
			
			estructuraArchivoForm = new EstructuraArchivoForm();
			estructuraArchivoForm.setCodigoInterfaz(pk.getCodigo());
			estructuraArchivoForm.setCodigoPais(pk.getCodigoPais());
			estructuraArchivoForm.setCodigoSistema(pk.getCodigoSistema());
			
			this.formMantenimiento = estructuraArchivoForm;
			
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			throw new Exception(this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			throw new Exception(this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAtributes' method");
		}
		
		this.mostrarBotonSave = false;
		this.setKeyMensajeAlertaDefault("errors.select.unique.item");
		this.salirGrabarPantallaPadre = false;
	}
	
	    
    /**
     * Modifica Estructura de Archivos
     */
    public void editarEstructuraArchivo() {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'editarEstructuraArchivo' method");
		}
		//Si no se ha seleccionado nada se muestra el mensaje de error en la cabecera
		//Si ha seleccionado mas de 1, se muestra el alert
		if (this.columnasSeleccionadas == null || this.columnasSeleccionadas.length == 0) 	{
			seleccionoRegistros = Constants.NUMERO_CERO;
			this.addWarn("Warning: ", this.getResourceMessage("errors.select.item"));
			return;
		}
		else 	{
			this.seleccionoRegistros = Integer.toString(columnasSeleccionadas.length);
			
			if(StringUtils.equals(seleccionoRegistros, Constants.NUMERO_UNO))	{
				try {
					
					EstructuraArchivo estructuraArchivo = (EstructuraArchivo)columnasSeleccionadas[0];
					
					EstructuraArchivoForm estructuraArchivoForm = new EstructuraArchivoForm();
					estructuraArchivoForm.setCodigoInterfaz(estructuraArchivo.getCodigoInterfaz());
					estructuraArchivoForm.setCodigoPais(estructuraArchivo.getCodigoPais());
					estructuraArchivoForm.setCodigoSistema(estructuraArchivo.getCodigoSistema());
										
					EstructuraArchivoPK pk = new EstructuraArchivoPK(
							estructuraArchivo.getCodigoPais(), 
							estructuraArchivo.getCodigoSistema(), 
							estructuraArchivo.getCodigoInterfaz(), 
							estructuraArchivo.getCodigo());
					EstructuraArchivoService service = (EstructuraArchivoService) getBean("sisicc.estructuraArchivoService");
					
					estructuraArchivo = service.getItemEstructuraArchivo(pk);
					BeanUtils.copyProperties(estructuraArchivoForm, estructuraArchivo);
					estructuraArchivoForm.setNewRecord(false);
					this.formMantenimiento = estructuraArchivoForm;
	
				}
				catch(Exception ex) {
					this.addError("Error: ", ex.getMessage());
				}
			}
		}				
	}

	
	
	/**
	 * Verifica si se ha seleccionado el registro 
	 * @param actionEvent
	 */
	public void verificarRegistro(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'verificarRegistro' method");
		}
		
		if (this.columnasSeleccionadas == null || this.columnasSeleccionadas.length == 0)
		{
			seleccionoRegistros = Constants.NUMERO_CERO;
			this.addWarn("Warning: ", this.getResourceMessage("errors.select.item"));
		}
		else
		{
			this.seleccionoRegistros = Integer.toString(columnasSeleccionadas.length);
		}
	}

	/**
	 * Elimina Estructura de Archivos
	 * @param actionEvent
	 */
	public void delete(ActionEvent actionEvent) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}
		try {
			EstructuraArchivoService service = (EstructuraArchivoService) getBean("sisicc.estructuraArchivoService");
			EstructuraArchivo estructuraArchivo = new EstructuraArchivo();
			for (int i = 0; i < this.columnasSeleccionadas.length; i++) {
				estructuraArchivo = (EstructuraArchivo)this.columnasSeleccionadas[i];
				EstructuraArchivoPK pk = new EstructuraArchivoPK(
						 estructuraArchivo.getCodigoPais(), 
						 estructuraArchivo.getCodigoSistema(), 
						 estructuraArchivo.getCodigoInterfaz(), 
						 estructuraArchivo.getCodigo());
				service.removeEstructuraArchivo(pk);
			}

			InterfazPK pk = new InterfazPK(
					estructuraArchivo.getCodigoPais(), 
					estructuraArchivo.getCodigoSistema(),
					estructuraArchivo.getCodigoInterfaz());
			List listaEstructura = service.getEstructuraArchivo(pk);
			this.listaEstructuraArchivoModel = new DataTableModel(listaEstructura);
			
			this.addInfo("Info: ", this.getResourceMessage("estructuraArchivo.deleted"));
		}
		catch (Exception e) {
			this.addError("Error: ", e.getMessage());
		}
	}
	
	/**
	 * Cancela la operacion a realizar
	 */
	public void cancelar()  {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cancelar' method");
		}
		
		try{
			EstructuraArchivoForm estructuraArchivo = (EstructuraArchivoForm)this.formMantenimiento;
			
			EstructuraArchivoForm estructuraArchivoForm = new EstructuraArchivoForm();
			estructuraArchivoForm.setCodigoInterfaz(estructuraArchivo.getCodigoInterfaz());
			estructuraArchivoForm.setCodigoPais(estructuraArchivo.getCodigoPais());
			estructuraArchivoForm.setCodigoSistema(estructuraArchivo.getCodigoSistema());
			estructuraArchivoForm.setNewRecord(true);
			this.formMantenimiento = estructuraArchivoForm;
			
			//String parametros = String.format("?codigoInterfaz=%s&codigoPais=%s&codigoSistema=%s", estructuraArchivo.getCodigoInterfaz(), estructuraArchivo.getCodigoPais(), estructuraArchivo.getCodigoSistema());
			//this.redireccionarPagina("mantenimientoBASEstructuraArchivoForm", parametros);
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			return;
		}
		return;
	}
	
	/**
	 * @param e
	 */
	public void cambiarTipoDato(ValueChangeEvent e)
	{
		if(log.isDebugEnabled())
			log.debug("cambiarTipoDato");
		
		EstructuraArchivoForm estructuraForm = (EstructuraArchivoForm) this.formMantenimiento;
		
		String tipoDato = (String)e.getNewValue();
		estructuraForm.setCodigoTipoDato(tipoDato);
		
		if(StringUtils.equals(tipoDato, Constants.CODIGO_TIPO_DATO_NUMERICO)){
			this.mostrarDecimales = true;
		}
		else
		{
			this.mostrarDecimales = false;
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		EstructuraArchivoForm estructuraForm = (EstructuraArchivoForm) this.formMantenimiento;
		boolean isNew = estructuraForm.isNewRecord();
		if(isNew){
			return "estructuraArchivo.added";
		}else{
			return "estructuraArchivo.updated";
		}	
	}
	
	
	public void dummy(){ 
        if (log.isDebugEnabled()) {
            log.debug("Entering 'dummy' method");
        }
        log.debug("Entering 'dummy' method");
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

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Interfaz interfazbusqueda = this.interfazbusqueda;
		EstructuraArchivoForm estructuraArchivoForm = new EstructuraArchivoForm();
		
		log.debug("Accion: " + this.accion);

    	String codigoInterfaz = interfazbusqueda.getCodigo();
    	String codigoPais = interfazbusqueda.getCodigoPais();
    	String codigoSistema = interfazbusqueda.getCodigoSistema();
    	//String codigoColumna = this.parametrosPantalla.get("codigoColumna");

		EstructuraArchivoService service = (EstructuraArchivoService) getBean("sisicc.estructuraArchivoService");
    	
    	if(!StringUtils.isBlank(codigoInterfaz) && !StringUtils.isBlank(codigoPais) && !StringUtils.isBlank(codigoSistema))
    	{
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + codigoInterfaz + " " + codigoPais + " " + codigoSistema);
			}
			
			InterfazService svc = (InterfazService) getBean("sisicc.interfazService");
			TipoDatoService tdSvc = (TipoDatoService) getBean("sisicc.tipoDatoService");

			InterfazPK pk = new InterfazPK(codigoPais, codigoSistema, codigoInterfaz);
			
			List listaEstructura = service.getEstructuraArchivo(pk);
			this.listaEstructuraArchivoModel = new DataTableModel(listaEstructura);
			this.interfazData = svc.getInterfaz(pk);
			this.listaTiposDato = tdSvc.getTiposDato();
			
			estructuraArchivoForm.setCodigoInterfaz(codigoInterfaz);
			estructuraArchivoForm.setCodigoPais(codigoPais);
			estructuraArchivoForm.setCodigoSistema(codigoSistema);
    	}
    	
    	/*if(!StringUtils.isBlank(codigoColumna))
    	{
			EstructuraArchivoPK pk = new EstructuraArchivoPK(codigoPais, codigoSistema, codigoInterfaz, codigoColumna);
			EstructuraArchivo estructuraArchivo = service.getItemEstructuraArchivo(pk);
			BeanUtils.copyProperties(estructuraArchivoForm, estructuraArchivo);
			estructuraArchivoForm.setNewRecord(false);
    	}
		*/
		return estructuraArchivoForm;
	}

	/**
	 * @return the interfazbusqueda
	 */
	public Interfaz getInterfazbusqueda() {
		return interfazbusqueda;
	}

	/**
	 * @param interfazbusqueda the interfazbusqueda to set
	 */
	public void setInterfazbusqueda(Interfaz interfazbusqueda) {
		this.interfazbusqueda = interfazbusqueda;
	}     
	
	
	
}
