package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.zon.model.PerfilDirectorio;
import biz.belcorp.ssicc.dao.spusicc.zon.model.RolDirectorio;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONDirectorioService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.zon.form.MantenimientoZONCargosForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoZONCargosAction extends BasePopupAbstractAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8413775548396016101L;
	
	private List listaCargos;
	private Map registroSeleccionadoPrincipal;
	
	//pantalla mantenimiento de cargos
	private List zonPerfilList;
	private List zonRolList;
	private List zonCodCargosList;
	private Boolean indicadorAdmin;
	private String estadoInactivo = Constants.ESTADO_INACTIVO;
	private Boolean ocultarBotones;
	private List listaFiltrada;
		
	//pantalla cambiar cargos
	private String zonMantCambiarTipoCargoCerrar;
	private List zonMantCambiarTipoCargoList;
	private LabelValue[] zonMantCambiarRegionList = {};
	private  LabelValueCDR[] zonMantCambiarZonaList = {};
	private String zonMantOidIdioma;
	
	//pantalla rotar cargos
	private String zonMantRotarTipoCargoCerrar;
	private List listaRotar;
	private DataTableModel listaRotarModel;
	private String tipoUnidAdmi;
	private List listaRegiDisp;
	private List listaZonaDisp;
	private Date fechaRotacionDate;
	private Boolean flagOcultarCalendar;
		
	//pantalla retirar personal 
	private List lstUnidades;
	private DataTableModel lstUnidadesModel;
	private Map directorioDetalle;
	private String fecFacturacion;
	
	// flag para ocultar/mostrar select multiple de region y zona
	private Boolean flagOcultarMultiple;
	
	// flag ocultar/mostrar zonas
	private Boolean flagOcultarZonas;
	
	// determina que pantalla mostrara
	private String accion;
	
	//verifica que la grabacion se haya realizado correctamente
	private String verificaGrabado;
	private String verificaRotarGrabar;
	
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoZONCargosForm f = new MantenimientoZONCargosForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		return this.getListaCargos();
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		Map params = new HashMap();			 
		setListaCargos(service.getCargosList(params)); 
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
		f.setSelectedItems(new String[0]);
		limpiarCamposCargo(f);		
		f.setIndicadorRegistrar("0");
		f.setIndicadorEditar("0");
		f.setIndicadorEliminar("0");
		f.setIndicadorAdmin(Constants.NUMERO_CERO);
		this.indicadorAdmin = false;
		
		params.put("indicadorActivo", Constants.UNO);
		List perfiles = service.getPerfilesByCriteria(params);
		List roles = service.getRolesByCriteria(params);
		
		this.zonPerfilList = perfiles;
		this.zonRolList = roles;
		
		Map criteria = new HashMap();
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		List codigoCargList = service.getCodigoCargosList(criteria);
		this.zonCodCargosList = codigoCargList;
		
		//parametro de los botones que abren el popup
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("parametroAccion");
		
		//pantalla mantenimiento de cargos
		this.ocultarBotones = true;
		this.mostrarListaBusqueda = false;
		
		//inicializa popup cambiar cargo
		if(accion != null && accion.equalsIgnoreCase("CambiarCargos"))
			vistaCambiarCargo();
		
		//inicializa popup rotar cargo
		if(accion != null && accion.equalsIgnoreCase("rotarCargos"))
			vistaRotar();
		
		//inicializa popup retiro de personal
		if(accion != null && accion.equalsIgnoreCase("retirarPersonal"))
			vistaRetirarPersonal();
		
		ConvertUtils.register(new DateConverter(null), Date.class);
	}
	
	private void limpiarCamposCargo(MantenimientoZONCargosForm f)
	{
		f.setCodigoCargo("");
		f.setDescripcionCargo("");
		f.setCodigoTipoUniAdmi("");
		f.setCantUniAdmi("");
		f.setCodigoTitular("");
		f.setPosicion("");
		f.setIndicadorAdmin("");
		this.indicadorAdmin = false;
		f.setCodigoCargoBase("");
	}
	
	/**
	 * Agrega un nuevo registro a la tabla en el popup  Mantenimiento de cargos
	 * @param event
	 */
	public void agregar(ActionEvent event)
	{
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
		f.setIndicadorAdmin(this.indicadorAdmin == true? Constants.NUMERO_UNO: Constants.NUMERO_CERO);
		Map map = null;
		
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregar' method");
		}
		try{			
			if(getListaCargos() == null || getListaCargos().size() ==0)
			{
				this.listaCargos = new ArrayList();			 
				 map =  new HashMap();
				 llenarValores(map,f);
				 map.put("posicion", listaCargos.size()+1);
				 listaCargos.add(map);
			}else{								
				if(StringUtils.isNotBlank(f.getPosicion())){
					for(int i=0; i<listaCargos.size();i++){
						map = (Map)listaCargos.get(i);
						if(MapUtils.getString(map, "posicion").equals(f.getPosicion())){
							//chanca
							llenarValores(map,f);
							break;
						}
					}
					limpiarCamposCargo(f);
				}else{	
					String statusVal = validarExistencia(f.getCodigoCargo(),listaCargos);
					String statusValDesc = validarDescripcionCargo("", f.getDescripcionCargo(), listaCargos);
					
					if(StringUtils.equals(statusVal, Constants.NUMERO_CERO)){
						//Existe en la Pantalla
						this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.existe.cargo"));
					}
					else if(StringUtils.equals(statusVal, Constants.NUMERO_UNO))
					{
						//Existe en la BD
						this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.existe.cargo.eliminado"));
					}
					else{
						if(StringUtils.equals(statusValDesc, Constants.NUMERO_CERO)){
							//Existe en la Pantalla
							this.addWarn("Advertencia", this.getResourceMessage("mantenimientoZONCargosForm.existe.descripcion"));
						}else if(StringUtils.equals(statusValDesc, Constants.NUMERO_UNO)){
							//Existe en la BD
							this.addWarn("Advertencia: ", this.getResourceMessage("mantenimientoZONCargosForm.existe.descripcion.existedbd"));
						}else{
							map =  new HashMap();
							llenarValores(map,f);
							map.put("posicion", listaCargos.size()+1);
							listaCargos.add(map);
							limpiarCamposCargo(f);
							f.setIndicadorRegistrar(Constants.NUMERO_UNO);
						}
					}
				}				
			}	
		}catch (Exception e) 
		{			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));		
		}		
		f.setIndicadorEditar("0");	
	}
		
	/**
	 * Guarda los cambios en el popup  Mantenimiento de cargos
	 * @param event
	 */
	public void guardar(ActionEvent event) throws Exception 
	{
		try {
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this
					.getBean("spusicc.mantenimientoZonDirectorioService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
			f.setIndicadorAdmin(this.indicadorAdmin == true ? Constants.NUMERO_UNO : Constants.NUMERO_CERO);

			Map criteria = new HashMap();
			List lstTipoCargo = null;
			String msgAccion = new String();

			for (int i = 0; i < getListaCargos().size(); i++) 
			{
				Map map = (Map) listaCargos.get(i);
				map.put("indicadorAdmin", MapUtils.getString(map, "indicadorAdmin"));
				map.put("login", usuario.getLogin());
				criteria.put("codigoCargo", MapUtils.getString(map, "codigoCargo"));
				lstTipoCargo = service.getTipoCargo(criteria);

				RolDirectorio rd = service.getRol(MapUtils.getString(map, "codigoRol"));
				map.put("tipoUnidadAdministrativa", rd.getCodigoTipoUniAdmi());

				if (lstTipoCargo.size() != 1) {
					service.insertCargo(map);
					msgAccion = "insert";
				}

				String estado = MapUtils.getString(map, "estado");
				if (f.getIndicadorEliminar().equals("1") && StringUtils.isNotBlank(estado)) {
					service.updateCargo(map);
				}
			}

			if (f.getIndicadorEditar().equals("1")) {
				Map mapSeteo = new HashMap();
				mapSeteo.put("descripcion", StringUtils.trim(f.getDescripcionCargo()));
				mapSeteo.put("cantidadUnidadAdministrativa", f.getCantUniAdmi());
				mapSeteo.put("codigoCargo", f.getCodigoCargo());
				mapSeteo.put("login", usuario.getLogin());
				mapSeteo.put("codigoPerfil", f.getCodigoTitular());
				mapSeteo.put("codigoRol", f.getCodigoTipoUniAdmi());
				mapSeteo.put("indicadorAdmin", f.getIndicadorAdmin());
				mapSeteo.put("codigoCargoBase", f.getCodigoCargoBase());

				RolDirectorio rd = service.getRol(MapUtils.getString(mapSeteo, "codigoRol"));
				mapSeteo.put("tipoUnidadAdministrativa", rd.getCodigoTipoUniAdmi());

				if (StringUtils.equals(Constants.ZON_CODIGO_PERFIL_TITULAR, f.getCodigoTitular()))
					mapSeteo.put("titular", Constants.NUMERO_UNO);
				else
					mapSeteo.put("titular", Constants.NUMERO_CERO);

				msgAccion = "edit";

				// Validar Nombres duplicados
				String statusValDesc = validarDescripcionCargo(f.getCodigoCargo(), f.getDescripcionCargo(), listaCargos);

				if (StringUtils.equals(statusValDesc, Constants.NUMERO_CERO)) {
					// Existe en la Pantalla
					this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.existe.descripcion"));
					return;
				} else if (StringUtils.equals(statusValDesc, Constants.NUMERO_UNO)) {
					// Existe en la BD
					this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.existe.descripcion.existedbd"));
					return;
				}

				service.updateCargo(mapSeteo);

				Map params = new HashMap();
				setListaCargos(service.getCargosList(params));
				this.listaBusqueda = this.setFindAttributes();
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			}

			if (msgAccion.equals("edit")) {
				this.addInfo("", this.getResourceMessage("mantenimientoZONCargosForm.datos.update"));
			} else {
				this.addInfo("", this.getResourceMessage("mantenimientoZONCargosForm.datos.insert"));
			}

			f.setSelectedItems(new String[0]);
			f.setIndicadorEditar("0");
			f.setIndicadorEliminar("0");

			limpiarCamposCargo(f);
			this.ocultarBotones = true;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Edita un registro en el popup  Mantenimiento de cargos
	 * @param event
	 */
	public void editar(ActionEvent event)
	{
		try{
		Map registroSeleccionado = (Map)this.beanRegistroSeleccionado;	
		if(registroSeleccionado != null)
		{
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm)this.formBusqueda ;		
			String posicion = registroSeleccionado.get("posicion").toString();
			Map map = null;
			for(int i=0;i<getListaCargos().size();i++)
			{
				map = (Map)getListaCargos().get(i);
					if(MapUtils.getString(map, "posicion").equals(posicion))
					{
						f.setCodigoCargo(MapUtils.getString(map, "codigoCargo"));
						f.setDescripcionCargo(MapUtils.getString(map, "descripcion"));
						f.setCantUniAdmi(MapUtils.getString(map, "cantidadUnidadAdministrativa"));
						f.setPosicion(MapUtils.getString(map, "posicion"));
						f.setCodigoCargoEditar(MapUtils.getString(map, "codigoCargo"));
						
						f.setCodigoTipoUniAdmi(MapUtils.getString(map, "codigoRol"));
						f.setCodigoTitular(MapUtils.getString(map, "codigoPerfil"));
						f.setIndicadorAdmin(MapUtils.getString(map, "indicadorAdmin"));
						f.setCodigoCargoBase(MapUtils.getString(map, "codigoCargoBase"));
						this.indicadorAdmin = f.getIndicadorAdmin().equals(Constants.NUMERO_CERO)? false: true;
						break;
				}
			}
			f.setSelectedItems(new String[0]);	
			f.setIndicadorEditar("1");
			this.ocultarBotones = false;			
		}
		else
			this.addWarn("",this.getResourceMessage("mantenimientoZONCargosForm.message.error.selected.item"));	
		}catch(Exception e)
		{
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Eliminar del popup Mantenimiento de cargos
	 * @param event
	 */
	public void eliminar(ActionEvent event) 
	{
		try {
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this
					.getBean("spusicc.mantenimientoZonDirectorioService");
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;

			Map map = null;
			Map registroSeleccionado = (Map) this.beanRegistroSeleccionado;
			String posicion = registroSeleccionado.get("posicion").toString();

			List listaNuevaCargos = new ArrayList();
			Map criteriaValidacion = new HashMap();

			if (log.isDebugEnabled()) {
				log.debug("Entering 'delete' method");
			}

			for (int i = 0; i < getListaCargos().size(); i++) 
			{
				map = (Map) getListaCargos().get(i);
				if (MapUtils.getString(map, "posicion").equals(posicion)) 
				{
					criteriaValidacion.put("tipoCargo", (String) map.get("codigoCargo"));
					int listaCargosAsignados = service.getValidarTipoCargoDirectorioVenta(criteriaValidacion);
					if (listaCargosAsignados > 0) 
					{
						this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.delete.existe.directorio"));
					} else {
						map.put("estado", Constants.ESTADO_INACTIVO);
						f.setIndicadorEliminar("1");
					}
				}
				
				listaNuevaCargos.add(map);
			}
			// guarda la lista original con los objetos de ESTADO_INACTIVO
			setListaCargos(listaNuevaCargos);
						
			//lista que se mostrara en la pantalla
			this.datatableBusqueda = new DataTableModel(getListaCargos());
			
			f.setSelectedItems(new String[0]);
			limpiarCamposCargo(f);

			f.setIndicadorEditar("0");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	private void llenarValores(Map map, MantenimientoZONCargosForm f) 
	{
		map.put("codigoCargo", f.getCodigoCargo());
		map.put("descripcion", StringUtils.trim(f.getDescripcionCargo()));
		map.put("cantidadUnidadAdministrativa", f.getCantUniAdmi());

		map.put("codigoPerfil", f.getCodigoTitular());
		map.put("codigoRol", f.getCodigoTipoUniAdmi());
		
		if(StringUtils.equals(Constants.ZON_CODIGO_PERFIL_TITULAR, f.getCodigoTitular()))
			map.put("titular", Constants.NUMERO_UNO);
		else
			map.put("titular", Constants.NUMERO_CERO);
				
		map.put("tipoUnidadAdministrativa", StringUtils.substring(f.getCodigoTipoUniAdmi(), 1,2));
		map.put("indicadorAdmin", f.getIndicadorAdmin());
		map.put("codigoCargoBase", f.getCodigoCargoBase());
		
		String descripTipoUnidades = "";
		String descripCodTitular = "";
		
		for (Object objeto : this.zonRolList) {
			RolDirectorio aux = (RolDirectorio)objeto;
			if(aux.getCodigo().equalsIgnoreCase(f.getCodigoTipoUniAdmi()))
				descripTipoUnidades = aux.getDescripcion();
		}
		
		for (Object objeto : this.zonPerfilList) {
			PerfilDirectorio aux = (PerfilDirectorio)objeto;
			if(aux.getCodigo().equalsIgnoreCase(f.getCodigoTitular()))
				descripCodTitular = aux.getDescripcion();		
		}
		//Descripciones
		map.put("descripTipoUnidades", descripTipoUnidades);
		map.put("descripCantUnidades", f.getCantUniAdmi());
		map.put("descripCodTitular", descripCodTitular);
		
	}
	
	private String validarExistencia(String codigoCargo, List listaCargos) 
	{
		for(int i=0; i<listaCargos.size(); i++){
			Map map = (Map)listaCargos.get(i);
			if(StringUtils.equals(MapUtils.getString(map, "codigoCargo", ""), codigoCargo)  && !StringUtils.equals(MapUtils.getString(map, "estado", ""), Constants.ESTADO_INACTIVO)){
				return Constants.NUMERO_CERO;
			}			
		}
		
		//Valida los eliminados en DB
		
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		Map criteria = new HashMap();
		criteria.put("codigoCargo", codigoCargo);
		criteria.put("estado", Constants.ESTADO_INACTIVO);
		
		List lista = service.getTipoCargo(criteria);
		
		if(lista != null && lista.size() > 0)
			return Constants.NUMERO_UNO;
		
		return Constants.NUMERO_DOS;
	}
	
	private String validarDescripcionCargo(String codigoCargo, String descripcionCargo, List listaCargos) 
	{
		for(int i=0; i<listaCargos.size(); i++)
		{
			Map map = (Map)listaCargos.get(i);	
			if(StringUtils.equals(StringUtils.trim(MapUtils.getString(map, "descripcion", "")), StringUtils.trim(descripcionCargo)) 
					&& !StringUtils.equals(MapUtils.getString(map, "estado", ""), Constants.ESTADO_INACTIVO) 
					&& !StringUtils.equals(MapUtils.getString(map, "codigoCargo", ""), codigoCargo))
			{
				return Constants.NUMERO_CERO;
			}
		}
		
		//Valida los eliminados en DB
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
		Map criteria = new HashMap();
		criteria.put("descripcionCargo", StringUtils.trim(descripcionCargo));
		
		List lista = service.getCargosList(criteria);
		
		if(lista != null && lista.size() > 0)
		{
			Map obj = (Map)lista.get(0);
			if(!StringUtils.equals(MapUtils.getString(obj, "codigoCargo", ""), codigoCargo))
			{
				return Constants.NUMERO_UNO;
			}
		}			
		
		return Constants.NUMERO_DOS;
	}
	
	/**
	 *  Insertar de popup Mantenimiento de Cargos
	 * @param event
	 */
	public void validarInsertar(ActionEvent event) 
	{
		try {
			// parametro de los botones que abren el popup
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			this.accion = externalContext.getRequestParameterMap().get("parametroAccion");

			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;

			if (StringUtils.isBlank(f.getCodigoCargo())) {
				this.setMensajeAlertaDefault("Ingresar código de cargo");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			if (StringUtils.isBlank(f.getDescripcionCargo())) {
				this.setMensajeAlertaDefault("Registrar descripción");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			if (StringUtils.isBlank(f.getCodigoTipoUniAdmi())) {
				this.setMensajeAlertaDefault("Seleccione Rol");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			if (StringUtils.isBlank(f.getCantUniAdmi())) {
				this.setMensajeAlertaDefault("Seleccione Cantidad Unidad Administrativa");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			if (StringUtils.isBlank(f.getCodigoTitular())) {
				this.setMensajeAlertaDefault("Seleccione Perfil");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}

			agregar(event);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	private void limpiarZonas(String codigoCargo, Map criteria)
	{
		//Limpiamos las zonas que vienen con NO_DATA
		//verificamos que Tipo de UA sea R y limpiamos
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		String valor = ajax.getVerificarCargoTitular(codigoCargo);
		String []valores = StringUtils.split(valor, "|");
		if(StringUtils.equals(valores[1], "R"))
		{
			//Se verifica posteriormente este valor para setear el tipo de UA para las plantillas de correo
			criteria.put("codigoZona", null);
		}
	}
	
	public void seteaFocoCodigoConsultora(AjaxBehaviorEvent e)
	{
		String valor = (String) ((UIOutput)e.getSource()).getValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
		String resultado = null;
		
		if(valor != null)
			 resultado = ajax.obtenerDatosConsultoraAsignarCargo(pais.getCodigo(), pais.getCodigoConexionExterna(), valor);
		
		if(resultado.compareTo("1") == 0)
		{
			f.setNombreCliente("No se encuentra");
			f.setDocCliente("No se encuentra");
		}else
		{
			f.setNombreCliente(resultado.split("_")[0]);
			f.setNombresCompletosConsultora(resultado.split("_")[0]);
			f.setDocCliente(resultado.split("_")[1]);			
		}		
	}
	
	
	
	public void seteaCampanias(ActionEvent event) 
	{
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("parametroAccion");
				
		if (accion.equalsIgnoreCase("AsignarCargos")) 
		{
			f.setFechaIngreso(DateUtil.convertDateToString(f.getFechaIngresoDate()));

			if (StringUtils.isNotBlank(f.getFechaIngreso())) 
				obtenerDatosCampanias(f.getFechaIngreso());								
			else
				this.addWarn("", "Seleccione fecha de ingreso.");
		}
		
		if(accion.equalsIgnoreCase("CambiarCargos"))
		{
			f.setFechaNombramiento(DateUtil.convertDateToString(f.getFechaNombramientoDate()));
			if (StringUtils.isNotBlank(f.getFechaNombramiento()))
				obtenerDatosCampanias(f.getFechaNombramiento());
			else
				this.addWarn("", "Seleccione fecha de Nombramiento.");			
		}
		
		if (accion.equalsIgnoreCase("rotarCargos")) 
		{
			f.setFechaRotacion(DateUtil.convertDateToString(this.fechaRotacionDate));
			if (StringUtils.isNotBlank(f.getFechaRotacion()))
				obtenerDatosCampanias(f.getFechaRotacion());
			else
				this.addWarn("", "Seleccione fecha de Rotación.");			
		}
		
		if (accion.equalsIgnoreCase("retirarPersonal")) 
		{
			f.setFechaRetiro(DateUtil.convertDateToString(f.getFechaRetiroDate()));
			if (StringUtils.isNotBlank(f.getFechaRetiro()))
				obtenerDatosCampanias(f.getFechaRetiro());
			else
				this.addWarn("", "Seleccione fecha de Retiro.");			
		}
	}
	
	private void obtenerDatosCampanias(String fecha)
	{
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String codigoPeriodo = ajax.getPeriodoByFecha(pais.getCodigo(), pais.getCodigoConexionExterna(), fecha);
		String periodoCampania = ajax.getPeriodoNSiguiente(pais.getCodigo(), codigoPeriodo, "1");
	
		if(StringUtils.isNotBlank(codigoPeriodo))
			f.setCampanyaFacturacion(codigoPeriodo);
		else
			f.setCampanyaFacturacion("Fecha sin campaña.");
		
		if(StringUtils.isNotBlank(periodoCampania))
			f.setCampanyaVenta(periodoCampania);
		else
			f.setCampanyaVenta("Fecha sin campaña.");		
	}
			
	/**
	 * Metodo que carga las regiones en el popup Cambiar Cargos
	 */
	public void loadRegionxTitularCambiar(ValueChangeEvent event) 
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String valorResultado1 = null;

		if (valor != null) 
		{
			valorResultado1 = ajax.getVerificarCargoTitular(valor);
			String indicadorTitular = valorResultado1.split("\\|")[0];
			String tipoUA = valorResultado1.split("\\|")[1];

			if (!indicadorTitular.equals("1")) {
				this.flagOcultarMultiple = false;
				loadRegionCambiar(indicadorTitular, valor);
			} else {
				// Envia '1' indica que No titular y cantidad de unidades
				// administrativas a cargo es mayor 1
				this.flagOcultarMultiple = true;
				loadRegionCambiar(indicadorTitular, valor);
			}

			if (tipoUA.equalsIgnoreCase("R")) {
				// ocultar lo puse yo
				this.flagOcultarZonas = true;
			} else {
				// mostrar lo puse yo
				this.flagOcultarZonas = false;
			}
		} else {
			this.zonMantCambiarRegionList = null;
			this.zonMantCambiarZonaList = null;
			this.flagOcultarMultiple = false;
		}
	}
	
	private void loadRegionCambiar(String indicadorTitular, String cargo)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		this.zonMantCambiarRegionList = ajax.getRegionesAllDirectorioMantenimientoZON(pais.getCodigo(), pais.getCodigoConexionExterna(), indicadorTitular, cargo);	
	}
		
	/**
	 * Inicializa popup Cambiar Cargos
	 */
	public void vistaCambiarCargo()
	{
		log.debug("Entering my method 'vistaCambiarCargo'");
		this.zonMantCambiarRegionList = null;
		this.zonMantCambiarZonaList = null;
		this.zonMantCambiarTipoCargoList = null;
		
		this.zonMantCambiarTipoCargoCerrar = Constants.NUMERO_CERO;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date hoy = new Date(System.currentTimeMillis());
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		try {
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
			MantenimientoOCRPedidoControlFacturacionService serviceFacturacion = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			GenericoService genericoService = (GenericoService)getBean("genericoService");
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			
			BeanUtils.copyProperties(f, new MantenimientoZONCargosForm());
			//Capturar campaña Proceso
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("codigoPais", pais.getCodigo());
			criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente
			
			if (StringUtils.equals(pais.getCodigoConexionExterna(),Constants.CONEXION_EXTERNA_FOX)) {
				Map mapControl = service.getControlFacturacionByCriteriaFOX(criteriaPeriodo);
				f.setFechaNombramiento(MapUtils.getString(mapControl, "fechaProceso"));
			}else{			
			PedidoControlFacturacion controlFacturacion = serviceFacturacion.getControlFacturacionById(criteriaPeriodo);
			f.setFechaNombramiento(controlFacturacion.getFechaProceso());
			f.setFechaNombramientoDate(DateUtil.convertStringToDate(f.getFechaNombramiento()));
			}
			
			f.setFechaCambioCargo(sdf.format(hoy));
			//Obtener CampañaFacturacion y CampañaVenta a partir de la fechaIngreso obtenida.
			criteriaPeriodo.put("fecha", f.getFechaNombramiento());
			criteriaPeriodo.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteriaPeriodo.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteriaPeriodo.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			
			String campanyaRelacionada = genericoService.getPeriodoByFecha(criteriaPeriodo);
			f.setCampanyaFacturacion(campanyaRelacionada);
			
			criteriaPeriodo.put("codigoPeriodo", campanyaRelacionada);
			criteriaPeriodo.put("numeroPeriodo", 1);
			String campanyaRelacionadaSgte  = genericoService.getPeriodoNSiguiente(criteriaPeriodo);
			f.setCampanyaVenta(campanyaRelacionadaSgte);
						
			Map registroSeleccionado = (Map) this.beanRegistroSeleccionado;
			
			if (registroSeleccionado != null) 
			{	
				Map criteria = new HashMap();
				criteria.put("estado", Constants.ESTADO_ACTIVO);
				criteria.put("indCargoNoFuturo", Constants.ESTADO_ACTIVO);
				
				List cargoList = service.getTipoCargo(criteria);
				List cargoListModificada = new ArrayList();
				
				Map map = null;
				String tipoCargo;
				String descripcionTMP;
				for(int i=0;i<cargoList.size();i++){
					descripcionTMP = "";
					map = (Map)cargoList.get(i);
					tipoCargo = (String)map.get("codigoCargo");
					if(!tipoCargo.equals(registroSeleccionado.get("codigoCargo").toString())){
						descripcionTMP = MapUtils.getString(map, "codigoCargo") + " - " + MapUtils.getString(map, "descripcion");
						map.put("descripcion", descripcionTMP);
						cargoListModificada.add(map);						
					}					
				}
				
				this.zonMantCambiarTipoCargoList = cargoListModificada;
				
				//session.setAttribute(Constants.ZON_MANT_ASIGNAR_REGION_LIST,reporteService.getListaGenerico("getRegionesByPais",criteria));
				
				//Obtener el objeto del detalle de Directorio de Venta
				criteria.clear();
				criteria.put("numDetalle", registroSeleccionado.get("oidDirecVentDetal"));
				criteria.put("tipoOperacion", registroSeleccionado.get("codigoOperacion"));
				criteria.put("codigoCargo", registroSeleccionado.get("codigoCargo"));
				criteria.put("codigoCliente", registroSeleccionado.get("codigoCliente"));
				criteria.put("fechaRegistro", registroSeleccionado.get("fechaIngreso"));
				criteria.put("campanaProceso", registroSeleccionado.get("campana"));
				criteria.put("correlativoCabecera", registroSeleccionado.get("correlativoCabecera"));				
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
				
				Map resultado = service.obtenerDirectorioVentaDetalle(criteria);
				
				f.setCodigoConsultora((String)resultado.get("codigoCliente"));
				f.setCodigoCargoActual((String)resultado.get("codigoCargo"));
				f.setFechaRegistro((String)resultado.get("fechaRegistro"));
				f.setCampanyaProceso(registroSeleccionado.get("campana")== null?null:registroSeleccionado.get("campana").toString());
				f.setOidIdioma(this.zonMantOidIdioma);
				f.setCodigoPais(pais.getCodigo());
				f.setCodigoCargoAnterior(registroSeleccionado.get("codigoCargo") == null?null:registroSeleccionado.get("codigoCargo").toString());
				f.setCodigoRegionAnterior(registroSeleccionado.get("codigoRegion") == null?null:registroSeleccionado.get("codigoRegion").toString());
				f.setCodigoZonaAnterior(registroSeleccionado.get("codigoZona")==null?null:registroSeleccionado.get("codigoZona").toString());
				f.setCorrelativoCabecera(registroSeleccionado.get("correlativoCabecera").toString());	
				
				//Setear el Rol y Perfil para el Cargo Actual
				String rolPerfil = ajaxService.getRolPerfil((String)criteria.get("codigoCargo")).trim();
				String[] arrayRP = StringUtils.split(rolPerfil, "|");
				f.setRolDesc(arrayRP[0]);
				f.setPerfilDesc(arrayRP[1]);
				
				// muestra nombres y Dni
				String resultadoDatos = ajaxService.obtenerDatosConsultora(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getCodigoConsultora());
				
				if(!resultadoDatos.equals("1"))
				{
					f.setNombresCompletosConsultora(resultadoDatos.split("_")[0]);
					f.setDocCliente(resultadoDatos.split("_")[1]);
				}
				// parametros para iniciar pantalla
				setListaCargos(null);
				this.listaBusqueda = null;
				this.flagOcultarMultiple = false;
				this.flagOcultarZonas = false;
			}
		} catch (Exception e) {
			// parametros para iniciar pantalla
			setListaCargos(null);
			this.listaBusqueda = null;
			this.flagOcultarMultiple = false;
			this.flagOcultarZonas = false;
			
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) 
				error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
		}
	}
	
	/**
	 * Guardar de popup Cambiar Cargo
	 */
	public void guardarCambiarCargo(ActionEvent event)
	{
		log.debug("Entering my method 'guardarCambiarCargo'");
				
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			if(!this.flagOcultarMultiple){
				f.setCodigoRegion(new String[]{f.getCodigoRegionUnico()});
				if(!this.flagOcultarZonas)
					f.setCodigoZona(new String[]{f.getCodigoZonaUnico()});
			}
			
			Map criteria = new HashMap();
			String oidClieGerente = "";
			String codClieGerente = "";
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoConsultora", f.getCodigoConsultora());
			criteria.put("codigoCargo", f.getCodigoNuevoCargo());
			
			Map result = new HashMap();
			result = service.getTipoCargoFuturas(criteria);
			if(result != null){
				String codRolTitu = (String)result.get("codigoRol");
				//Validacion de Cargo Titular
				if ((StringUtils.equals(codRolTitu, Constants.ZON_MANT_GERENTE_REGION) 
						|| StringUtils.equals(codRolTitu, Constants.ZON_MANT_GERENTE_ZONA)) && 
						!StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX))
				{					
					//Validacion de subtipo
					if (StringUtils.equals(codRolTitu, Constants.ZON_MANT_GERENTE_REGION))
					{
						criteria.put("codigoSubTipoCliente", Constants.CODIGO_SUBTIPO_CLIENTE_GERENTE_REGION);
						criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
						
						oidClieGerente = service.getObtenerGerentexTipo(criteria);

						if(oidClieGerente == null){
							this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.no.corresponde.gr"));
							this.verificaGrabado = "N";
							return;
						}
						
					}else if (StringUtils.equals(codRolTitu, Constants.ZON_MANT_GERENTE_ZONA)){
						criteria.put("codigoSubTipoCliente", Constants.CODIGO_SUBTIPO_CLIENTE_GERENTE_ZONA);
						criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
						
						oidClieGerente = service.getObtenerGerentexTipo(criteria);

						if(oidClieGerente == null){
							this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.no.corresponde.gz"));		
							this.verificaGrabado = "N";
							return;
						}
					}
				}else if ((StringUtils.equals(codRolTitu, Constants.ZON_MANT_GERENTE_REGION) || 
						StringUtils.equals(codRolTitu, Constants.ZON_MANT_GERENTE_ZONA)) && 
						StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX)) {
					
					//Validacion de subtipo
					if (StringUtils.equals(codRolTitu, Constants.ZON_MANT_GERENTE_REGION)){
						criteria.put("codigoSubTipoCliente", Constants.CODIGO_SUBTIPO_CLIENTE_GERENTE_REGION);
						criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
						
						codClieGerente = service.getObtenerGerentexTipo(criteria);
			
						if(codClieGerente == null){
							this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.no.corresponde.gr"));
							this.verificaGrabado = "N";
							return;
						}
						
					}else 
						if (StringUtils.equals(codRolTitu, Constants.ZON_MANT_GERENTE_ZONA))
					{
						criteria.put("codigoSubTipoCliente", Constants.CODIGO_SUBTIPO_CLIENTE_GERENTE_ZONA);
						criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
						
						codClieGerente = service.getObtenerGerentexTipo(criteria);
			
						if(codClieGerente == null){
							this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.cliente.no.corresponde.gz"));
							this.verificaGrabado = "N";
							return;
						}
					}
				}	
			}
			
			criteria.put("oidCliente", oidClieGerente);
			criteria.put("codigoTipoCargo", f.getCodigoNuevoCargo());
			criteria.put("fechaIngreso",DateUtil.convertDateToString("dd/MM/yyyy", DateUtil.convertStringToDate(f.getFechaNombramiento())));
			if(StringUtils.isNotBlank(f.getFechaIngresoHasta()))
				criteria.put("fechaIngresoHasta",DateUtil.convertDateToString("dd/MM/yyyy", DateUtil.convertStringToDate(f.getFechaIngresoHasta())));
			
			String fechaCam = ajax.getPeriodoByFecha(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getFechaNombramiento());
			if(fechaCam !=null){
				if(!fechaCam.equals(f.getCampanyaProceso())){
					criteria.put("campanyaProceso", fechaCam);
				}else
					criteria.put("campanyaProceso", f.getCampanyaProceso());
			}else{
				this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.fecha.sincampania"));
				this.verificaGrabado = "N";
				return;
			}
			
			criteria.put("usuarioLogin", usuario.getLogin());
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("nombresCompletosConsultora", f.getNombresCompletosConsultora());
			criteria.put("tipoOperacion", Constants.ZON_MANT_CODIGO_OPERACION_NM);
			criteria.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_ACTIVA);
			criteria.put("codigoRegion", f.getCodigoRegion());
			criteria.put("codigoZona", f.getCodigoZona());
			criteria.put("estadoRegistro", Constants.ACTIVO);
			criteria.put("envioCorreo", Constants.SI);
			criteria.put("codigoCargoAnterior", f.getCodigoCargoAnterior());
			criteria.put("descripcionCargoAnterior", f.getCodigoCargoActual());
			criteria.put("codigoRegionAnterior", f.getCodigoRegionAnterior());
			criteria.put("codigoZonaAnterior", f.getCodigoZonaAnterior());
			criteria.put("fechaRegistroCargoAnterior", f.getFechaRegistro());
			criteria.put("correlativoCabecera", f.getCorrelativoCabecera());
			criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			criteria.put("codigoEnvio", Constants.MAV_CODIGO_INDICADOR_ENVIO_P);
			
			limpiarZonas(f.getCodigoNuevoCargo(), criteria);
			
			criteria.put("tipoUnidadAdministrativaAnteriorMail",obtenerTipoUA(f.getCodigoCargoAnterior()));
						
			service.insertDirectorioVenta(criteria);
			
//			this.addInfo("", this.getResourceMessage("mantenimientoZONCargosForm.datos.insert"));
			this.zonMantCambiarTipoCargoCerrar = Constants.NUMERO_UNO;
							
			f.setCodigoCargoAnterior(null);
			f.setCodigoRegionAnterior(null);
			f.setCodigoZonaAnterior(null);
			
			// muestra mensjae de ejecucion correcta
			mostrarMensaje(this.getResourceMessage("mantenimientoZONCargosForm.datos.insert"));
//			actualizarGrilla();
						
			this.verificaGrabado = "S";	
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.verificaGrabado = "N";
		}		
	}
	
	private String obtenerTipoUA(String codigoCargo)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		String valor = ajax.getVerificarCargoTitular(codigoCargo);
		String []valores = StringUtils.split(valor, "|");
		
		return valores[1];		
	}
	
	/**
	 * metodo que carga las zonas en el popup Cambiar Cargos
	 * @param event
	 */
	public void loadZonasCambiar(ValueChangeEvent event)
	{
		String[] valores = null;  
		
		try{
			String valor = (String)event.getNewValue();
			valores = new String[]{valor};
		}catch(Exception e)
		{
			String[] valor = (String[])event.getNewValue();
			valores = new String[valor.length];
			for (int i = 0; i < valor.length; i++) {
				valores[i] = valor[i];				
			}
		}
		finally{
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
			
			String valorResultado1 = ajax.getVerificarCargoTitular(f.getCodigoNuevoCargo());
			String indicadorTitular = valorResultado1.split("\\|")[0];
			
			if(valores != null && valores.length > 0)
			{
				this.zonMantCambiarZonaList = ajax.getZonasAllDirectorioAsignarCargo(pais.getCodigo(), pais.getCodigoConexionExterna(), 
						indicadorTitular, valores, "0", f.getCodigoNuevoCargo());					
			}
			else
				this.zonMantCambiarZonaList = null;			
		}
	}
	
	/**
	 * inicializa popup rotar Cargos
	 */
	public void vistaRotar() 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'vistaRotar' method ");
		}

		try {
			this.listaZonaDisp = null;
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this
					.getBean("spusicc.mantenimientoZonDirectorioService");
			GenericoService genericoService = (GenericoService) getBean("genericoService");
			MantenimientoOCRPedidoControlFacturacionService serviceFacturacion = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			this.zonMantRotarTipoCargoCerrar = Constants.NUMERO_CERO;

			Map registroSeleccionado = (Map) this.beanRegistroSeleccionado;
			
			String oidVentDetal = registroSeleccionado.get("oidDirecVentDetal").toString();
			String operacion = registroSeleccionado.get("codigoOperacion").toString(); 
			String cargo = registroSeleccionado.get("codigoCargo").toString();
			String cliente = registroSeleccionado.get("codigoCliente").toString();
			String fechaRegistro = registroSeleccionado.get("fechaIngreso").toString();
			String campanaProceso = registroSeleccionado.get("campana").toString();
			Map map = new HashMap();
			map.put("oidVentDetal", oidVentDetal);
			map.put("codigoOperacion", operacion);
			map.put("codigoCargo", cargo);
			map.put("codigoCliente", cliente);
			map.put("fechaRegistro", fechaRegistro);
			map.put("codigoPais", pais.getCodigo());
			map.put("campanaProceso", campanaProceso);
			map.put("correlativoCabecera", registroSeleccionado.get("correlativoCabecera").toString());
			map.put("codigoConexionExterna", pais.getCodigoConexionExterna());

			f.setCorrelativoCabecera(registroSeleccionado.get("correlativoCabecera").toString());
			f.setCodigoRegionNueva("");
			f.setCodigoZonaNueva("");
			List lstDirecVentCabec = service.getZonDirecVentaCabecList(map);
			String codigoRegionConsultoraARotar = "";
			String codigoZonaConsultoraARotar = "";

			String tipoUnidAdmi = "";
			if (CollectionUtils.isNotEmpty(lstDirecVentCabec)) {
				List lstDirVentDetal = service.getZonDirecVentaDetalList(map);

				if (lstDirVentDetal.size() == 1) {
					Map mapDetal = (Map) lstDirVentDetal.get(0);
					f.setCodigoClienteBuscar(MapUtils.getString(mapDetal, "codigoCliente"));
					f.setNombreCliente(MapUtils.getString(mapDetal, "nombre"));
					f.setDescripcionCargo(MapUtils.getString(mapDetal, "descCargo"));
					f.setDocCliente("Documento de Identidad :  " + MapUtils.getString(mapDetal, "docCliente", ""));
					f.setFechaRegistro(MapUtils.getString(mapDetal, "fechaRegistro"));

					f.setCodigoCargo(MapUtils.getString(mapDetal, "codigoCargo"));
					f.setCodigoOperacion(MapUtils.getString(mapDetal, "codigoOperacion"));
					f.setCampanyaProceso(MapUtils.getString(mapDetal, "campanaProceso"));
					f.setCorrelativoCabecera(MapUtils.getString(mapDetal, "correlativoCabecera"));

					tipoUnidAdmi = MapUtils.getString(mapDetal, "tipoUnidAdmi");
					this.tipoUnidAdmi = tipoUnidAdmi;
					this.listaRotar = lstDirVentDetal;
					this.listaRotarModel = new DataTableModel(listaRotar);
					
					codigoRegionConsultoraARotar = MapUtils.getString(mapDetal, "codigoRegion", "");
					codigoZonaConsultoraARotar = MapUtils.getString(mapDetal, "codigoZona", "");

					f.setCodigoRegionAnterior(codigoRegionConsultoraARotar);
					f.setCodigoZonaAnterior(codigoZonaConsultoraARotar);

					// Setear el Rol y Perfil para el Cargo Actual
					String rolPerfil = ajaxService.getRolPerfil(f.getCodigoCargo()).trim();
					String[] arrayRP = StringUtils.split(rolPerfil, "|");
					f.setRolDesc(arrayRP[0]);
					f.setPerfilDesc(arrayRP[1]);

					// Capturar campaña Proceso
					Map criteriaPeriodo = new HashMap();
					criteriaPeriodo.put("codigoPais", pais.getCodigo());
					criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
					criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

					if (StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX)) {
						Map mapControl = service.getControlFacturacionByCriteriaFOX(criteriaPeriodo);
						f.setFechaRotacion(MapUtils.getString(mapControl, "fechaProceso"));
						this.setFechaRotacionDate(DateUtil.convertStringToDate(f.getFechaRotacion()));
					} else {
						PedidoControlFacturacion controlFacturacion = serviceFacturacion.getControlFacturacionById(criteriaPeriodo);
						f.setFechaRotacion(controlFacturacion.getFechaProceso());
						setFechaRotacionDate(DateUtil.convertStringToDate(f.getFechaRotacion()));
					}

					// Obtener CampañaFacturacion y CampañaVenta a partir de la fechaRotacion obtenida.
					criteriaPeriodo.put("fecha", f.getFechaRotacion());
					criteriaPeriodo.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					criteriaPeriodo.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					criteriaPeriodo.put("codigoConexionExterna", pais.getCodigoConexionExterna());
					String campanyaRelacionada = genericoService.getPeriodoByFecha(criteriaPeriodo);

					f.setCampanyaFacturacion(campanyaRelacionada);

					criteriaPeriodo.put("codigoPeriodo", campanyaRelacionada);
					criteriaPeriodo.put("numeroPeriodo", 1);
					String campanyaRelacionadaSgte = genericoService.getPeriodoNSiguiente(criteriaPeriodo);

					f.setCampanyaVenta(campanyaRelacionadaSgte);
				}

				if (StringUtils.equals(tipoUnidAdmi, Constants.ZON_TIPO_UA_REGION))
					map.put("codigosRegionesAsignadas", new String[] { codigoRegionConsultoraARotar });

				this.listaRegiDisp = service.getRegionesDisp(map);
			} else {
				this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.datos.no.validos"));
			}

			f.setFechaRotacionActual(DateUtil.convertDateToString(new Date()));
			
			// parametros para iniciar pantalla
			setListaCargos(null);
			this.listaBusqueda = null;
			this.flagOcultarCalendar = false;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * Guarda los cambios en el popup rotar Cargos
	 * @param event
	 */
	public void grabarRotacion(ActionEvent event)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'grabarRotacion' method");
		}
		try{
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm)this.formBusqueda ;
			Map map = new HashMap();
		
			map.put("tipoOperacion", Constants.ZON_MANT_CODIGO_OPERACION_RO);
			map.put("codigoConsultora", f.getCodigoClienteBuscar());
			map.put("codigoTipoCargo", f.getCodigoCargo());
			map.put("fechaIngreso", f.getFechaRotacion());   
			
			String fechaCam = ajax.getPeriodoByFecha(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getFechaRotacion());
			if(fechaCam !=null){
				if(!fechaCam.equals(f.getCampanyaProceso())){
					map.put("campanyaProceso", fechaCam);
				}else
					map.put("campanyaProceso", f.getCampanyaProceso());
			}else{
				this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.fecha.sincampania"));
				this.verificaGrabado = "N";
				return;
			}
			
			map.put("codigoRegion", f.getCodigoRegionNueva());
			map.put("codigoZona", f.getCodigoZonaNueva());
			map.put("usuarioLogin", usuario.getLogin());
			map.put("codigoEstadoCargo",Constants.ESTADO_ENTIDAD_ACTIVO);
			map.put("estadoRegistro", Constants.ACTIVO );
			map.put("codigoPais", f.getCodigoPais());
			map.put("codigoOperacion", Constants.ZON_MANT_CODIGO_OPERACION_RO);
			map.put("codigoCliente", f.getCodigoClienteBuscar());
			map.put("codigoCargo", f.getCodigoCargo());
			map.put("fechaRegistro", f.getFechaRotacion());
			map.put("correlativoCabecera", f.getCorrelativoCabecera());
			map.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			map.put("codigoEnvio", Constants.MAV_CODIGO_INDICADOR_ENVIO_P);
			
			//en este caso el metod insertDirectorioVenta no debe de enviar el correo
			map.put("envioCorreo", Constants.NO);
			if(StringUtils.isNotBlank(f.getFechaIngresoHasta()))
				map.put("fechaIngresoHasta",DateUtil.convertDateToString("dd/MM/yyyy", DateUtil.convertStringToDate(f.getFechaIngresoHasta())));
			
			
			List listaRotar = this.listaRotar;
			List listaRotarEmail = new ArrayList();
			List listaOrdenParaEmail = new ArrayList();
			
			for(int i=listaRotar.size()-1; i>=0; i--)
			{	
				Map registro = (Map)listaRotar.get(i);
				if(StringUtils.isNotBlank(MapUtils.getString(registro, "codigoRegionNueva")))
				{
					listaRotarEmail.add(registro);
					
					map.put("codigoRegion", new String[]{MapUtils.getString(registro, "codigoRegionNueva")});
					map.put("codigoRegionAnterior", MapUtils.getString(registro, "codigoRegion"));
					
					if(StringUtils.isNotBlank(MapUtils.getString(registro, "codigoZonaNueva", ""))){
						map.put("codigoZona", new String[]{MapUtils.getString(registro, "codigoZonaNueva")});
						map.put("codigoZonaAnterior", MapUtils.getString(registro, "codigoZona"));
					}else{
						map.put("codigoZona", null);
						map.put("codigoZonaAnterior", null);
					}
					
					map.put("codigoConsultora", MapUtils.getString(registro, "codigoCliente"));
					map.put("fechaRegistro", MapUtils.getString(registro, "fechaRegistro"));
					map.put("correlativoCabecera", MapUtils.getString(registro, "correlativoCabecera"));
					
					if(!StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX))
					{
						MantenimientoZONDirectorioService mantenimientoZONDirectorioService = (MantenimientoZONDirectorioService)this.getBean("spusicc.mantenimientoZonDirectorioService");
						String oidClieGerente = mantenimientoZONDirectorioService.getObtenerGerentexTipo(map);
						map.put("oidCliente", oidClieGerente);
					}
					
					service.insertDirectorioVentaRotacion(map);				
				}
			}
			
			//Reordenar Lista para ser enviada por email.
			for(int x=listaRotarEmail.size()-1; x>=0; x--){
				Map registro = (Map)listaRotarEmail.get(x);
				listaOrdenParaEmail.add(registro);
			}
			
			//enviamos el correo
			map.put("listaRotar", listaOrdenParaEmail);
			service.enviarCorreo(map);
						
			// muestra mensjae de ejecucion correcta
			mostrarMensaje(this.getResourceMessage("mantenimientoZONCargosForm.datos.registrados"));
//			actualizarGrilla();
			
			this.verificaGrabado = "S";						
		}catch (Exception e) {			
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.verificaGrabado = "N";
		}	
	}
	
	public void cargarZonasDisponibles(ValueChangeEvent event)
	{
		System.out.println("entro al metodo cargarZonasDisponibles");
		
		Map map = (Map)this.listaRotar.get(0);
		String valor = (String)event.getNewValue();
			
		String fechaRegistroActual = map.get("fechaRegistro").toString();
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
		f.setCodigoRegionNueva(valor);
		f.setFechaRotacion(DateUtil.convertDateToString(this.fechaRotacionDate));
		f.setFechaIngresoHasta(DateUtil.convertDateToString(f.getFechaIngresoHastaDate()));
		this.verificaRotarGrabar = ""; 
		
		//Validamos la fecha de registro actual y la fecha de rotacion
		if(DateUtil.compareDates(fechaRegistroActual, f.getFechaRotacion(), "dd/MM/yyyy") > 0)
		{
			f.setCodigoRegionNueva("");
			this.setMensajeAlertaDefault("'Fecha de Rotación no puede ser menor a la de ingreso.");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			this.verificaRotarGrabar = "ErrorRegion;1";  
			return;			
		}
		
		if(StringUtils.isNotBlank(f.getCodigoRegionNueva()))
		{
			listarHistorico("1");
		}
	}
	
	/**
	 * Metodo utilizado en el popup rotar personal
	 */
	private void listarHistorico(String cargarZonas)
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'listarHistorico' method "  );
		}
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm)this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService)	this.getBean("spusicc.mantenimientoZonDirectorioService");
				
		if(StringUtils.isNotBlank(cargarZonas))
		{
			Map mapCodigos = new HashMap();
			mapCodigos.put("codigoRegion", f.getCodigoRegionNueva());
			
			String codigosZonasAsignadas[] = getZonasAsignadas();
			codigosZonasAsignadas[codigosZonasAsignadas.length-1] = f.getCodigoZonaAnterior();
			
			mapCodigos.put("codigosZonasAsignadas", codigosZonasAsignadas);			
			mapCodigos.put("codigoPais", pais.getCodigo());
			mapCodigos.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			
			List lstResul = service.getZonaDisp(mapCodigos);
				
			this.listaZonaDisp = lstResul;			
		}
		else
		{
			Map map = new HashMap();
			map.put("codigoRegion", f.getCodigoRegionNueva());
			if(StringUtils.isNotBlank(f.getCodigoZonaNueva())){
				map.put("codigoZona", f.getCodigoZonaNueva());	
			}
			map.put("codigoPais", pais.getCodigo());
			map.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			
			//Obtenemos el codigo de subgerencia y codigo de region
			String codigoSubgerenciaRegion = "";
			if(StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX))
				codigoSubgerenciaRegion = Constants.ZON_CODIGO_SUBGERENCIA_DEFAULT + f.getCodigoRegionNueva();
			else
				codigoSubgerenciaRegion = service.getSubgerenciaRegion(map);			
			
			map.put("codigoUA", String.format("%s%s", codigoSubgerenciaRegion, (StringUtils.isBlank(f.getCodigoZonaNueva())?"":f.getCodigoZonaNueva())));
			List lista = service.getHistoricoList(map);

			List listaRotar = this.listaRotar;
			
			//Seteamos a la lista el nuevo codigo de region y el nuevo codigo de zona
			Map registro = (Map)listaRotar.get(listaRotar.size() - 1);
			registro.put("codigoRegionNueva", f.getCodigoRegionNueva());
			registro.put("codigoZonaNueva", f.getCodigoZonaNueva());
			registro.put("nombreRegionNueva", obtenerNombreUA(f.getCodigoRegionNueva(), ""));
			registro.put("nombreZonaNueva", obtenerNombreUA("", f.getCodigoZonaNueva()));
			registro.put("estadoRegistro", Constants.ESTADO_INACTIVO); //Para ocultar el combo
			
			//Buscamos el ID del registro a rotar
			Map searchCriteria = new HashMap();			
			searchCriteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			searchCriteria.put("codigoPais", pais.getCodigo());
			searchCriteria.put("codigoCliente", MapUtils.getString(registro, "codigoCliente", ""));
			searchCriteria.put("codigoCargo", f.getCodigoCargo());
			searchCriteria.put("codigoRegion", MapUtils.getString(registro, "codigoRegion", ""));
			searchCriteria.put("codigoZona", StringUtils.isNotBlank(MapUtils.getString(registro, "codigoZona", "")) ? MapUtils.getString(registro, "codigoZona", "") : null);
			searchCriteria.put("codigoEstadoCargoRetirarPersonal", Constants.ZON_MANT_ESTADO_CARGO_ACTIVA);
			
			List lstDirVentDetal = service.getZonDirecVentaDetalList(searchCriteria);
			
			if(lstDirVentDetal != null && lstDirVentDetal.size() > 0)
			{
				registro.put("correlativoCabecera", MapUtils.getString((Map)lstDirVentDetal.get(0), "correlativoCabecera", ""));
			}
					
			listaRotar.set(listaRotar.size() - 1, registro);
			
			listaRotar.addAll(lista);

			this.listaRotar = listaRotar;
			this.listaRotarModel = new DataTableModel(this.listaRotar);
			
			String tipoUnidadAdministrativa = this.tipoUnidAdmi;
			
			if(StringUtils.equals(tipoUnidadAdministrativa, Constants.ZON_TIPO_UA_ZONA)){
				Map mapCodigos = new HashMap();
				mapCodigos.put("codigoRegion", f.getCodigoRegionNueva());
				
				String codigosZonasAsignadas[] = getZonasAsignadas();
				codigosZonasAsignadas[codigosZonasAsignadas.length - 1] = f.getCodigoZonaAnterior();
				
				mapCodigos.put("codigosZonasAsignadas", codigosZonasAsignadas);
				mapCodigos.put("codigoPais", pais.getCodigo());
				mapCodigos.put("codigoConexionExterna", pais.getCodigoConexionExterna());
				
				List lstResul = service.getZonaDisp(mapCodigos);
					
				this.listaZonaDisp = lstResul;
			}
			else
			{
				Map params = new HashMap();
				params.put("codigoCargo", f.getCodigoCargo());
				
				String codigosRegionesAsignadas[] = getRegionesAsignadas();
				codigosRegionesAsignadas[codigosRegionesAsignadas.length - 1] = f.getCodigoRegionAnterior();
				params.put("codigosRegionesAsignadas", codigosRegionesAsignadas);
				params.put("codigoPais", pais.getCodigo());
				params.put("codigoConexionExterna", pais.getCodigoConexionExterna());
				
				this.listaRegiDisp = service.getRegionesDisp(params);
			}
			
			f.setCodigoRegionNueva("");
			f.setCodigoZonaNueva("");
		}		
	}
	
	private String[] getRegionesAsignadas()
	{
		List listaRotar = this.listaRotar;		
		String []asignados = null;
		
		if(listaRotar != null && listaRotar.size() > 0)
		{
			asignados = new String[listaRotar.size() + 1];
			for(int i=0; i<listaRotar.size(); i++)
			{
				Map registro = (Map)listaRotar.get(i);
				
				String codRegion = MapUtils.getString(registro, "codigoRegionNueva", "00");
				
				asignados[i] = codRegion;
			}
		}
		
		return asignados;
	}
	
	private String[] getZonasAsignadas()
	{
		List listaRotar = this.listaRotar;		
		String []asignados = null;
		
		if(listaRotar != null && listaRotar.size() > 0)
		{
			asignados = new String[listaRotar.size() + 1];
			for(int i=0; i<listaRotar.size(); i++)
			{
				Map registro = (Map)listaRotar.get(i);				
				String codZona = MapUtils.getString(registro, "codigoZonaNueva", "0000");				
				asignados[i] = codZona;
			}
		}
		
		return asignados;
	}
	
	private String obtenerNombreUA(String codigoRegion, String codigoZona)
	{
		String nombre = "";
		String codigo = "";
		List lista = null;
		if(StringUtils.isNotBlank(codigoRegion) && StringUtils.isBlank(codigoZona))
		{
			codigo = codigoRegion;
			lista = this.listaRegiDisp;
		}
		else if(StringUtils.isBlank(codigoRegion) && StringUtils.isNotBlank(codigoZona))
		{
			codigo = codigoZona;
			lista = this.listaZonaDisp;
		}

		if(lista != null)
		{
			for(int i=0; i<lista.size(); i++)
			{
				if(StringUtils.equals(MapUtils.getString((Map)lista.get(i), "codigo"), codigo))
				{
					nombre = MapUtils.getString((Map)lista.get(i), "descripcion");
					break;
				}
			}
		}

		return StringUtils.remove(nombre, "Disponible");
	}
	
	public void cargarListaHistorica(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm)this.formBusqueda;
		f.setCodigoZonaNueva(valor);
		String resultado = null;
		Map map = (Map)this.listaRotar.get(0);
		String fechaRegistroActual = map.get("fechaRegistro").toString();
		f.setFechaRotacion(DateUtil.convertDateToString(this.fechaRotacionDate));
		f.setFechaIngresoHasta(DateUtil.convertDateToString(f.getFechaIngresoHastaDate()));
		
		this.verificaRotarGrabar = "";
		
		//Validamos la fecha de registro actual y l fecha de rotacion
		if(DateUtil.compareDates(fechaRegistroActual, f.getFechaRotacion(), "dd/MM/yyyy") > 0){
			f.setCodigoZonaNueva("");
			this.setMensajeAlertaDefault("'Fecha de Rotación no puede ser menor a la de ingreso.");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			this.verificaRotarGrabar = "Error;Zona";
			return;
		}
		
		resultado = getValidarFechaIngreso(f.getCodigoCargo(),f.getFechaRotacion(),f.getCodigoRegionNueva(), f.getCodigoZonaNueva());
				 
		if(StringUtils.isNotBlank(resultado)){
			f.setCodigoZonaNueva("");
			this.setMensajeAlertaDefault(resultado);
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			this.verificaRotarGrabar = "Error;Zona";
			return;		
		}
		
		resultado = getValidarCruceFechaGeren(f.getFechaRotacion(), f.getFechaIngresoHasta(), f.getCodigoRegionNueva(), 
				f.getCodigoZonaNueva(), f.getCodigoCargo(), null);
		
		if(StringUtils.isNotBlank(resultado)){
			f.setCodigoZonaNueva("");
			this.setMensajeAlertaDefault(resultado);
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			this.verificaRotarGrabar = "Error;Zona";
			return;		
		}
		
		if(StringUtils.isNotBlank(f.getCodigoRegionNueva()) || StringUtils.isNotBlank(f.getCodigoZonaNueva())){			 
			listarHistorico("");
			this.flagOcultarCalendar = true;
		 }		
	}
	
	public void cargarListaHistoricaRegion(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm)this.formBusqueda;
		f.setCodigoRegionNueva(valor);
		String resultado = null;
		Map map = (Map)this.listaRotar.get(0);
		String fechaRegistroActual = map.get("fechaRegistro").toString();
		f.setFechaRotacion(DateUtil.convertDateToString(this.fechaRotacionDate));
		f.setFechaIngresoHasta(DateUtil.convertDateToString(f.getFechaIngresoHastaDate()));
		
		this.verificaRotarGrabar = "";
		
		//Validamos la fecha de registro actual y l fecha de rotacion
		if(DateUtil.compareDates(fechaRegistroActual, f.getFechaRotacion(), "dd/MM/yyyy") > 0){
			f.setCodigoRegionNueva("");
			this.setMensajeAlertaDefault("'Fecha de Rotación no puede ser menor a la de ingreso.");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			this.verificaRotarGrabar = "ErrorRegion;2";
			return;
		}
		
		resultado = getValidarFechaIngreso(f.getCodigoCargo(),f.getFechaRotacion(),f.getCodigoRegionNueva(), f.getCodigoZonaNueva());
				 
		if(StringUtils.isNotBlank(resultado)){
			f.setCodigoRegionNueva("");
			this.setMensajeAlertaDefault(resultado);
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			this.verificaRotarGrabar = "ErrorRegion;2";
			return;		
		}
		
		resultado = getValidarCruceFechaGeren(f.getFechaRotacion(), f.getFechaIngresoHasta(), f.getCodigoRegionNueva(), 
				f.getCodigoZonaNueva(), f.getCodigoCargo(), null);
		
		if(StringUtils.isNotBlank(resultado)){
			f.setCodigoRegionNueva("");
			this.setMensajeAlertaDefault(resultado);
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			this.verificaRotarGrabar = "ErrorRegion;2";
			return;		
		}
		
		if(StringUtils.isNotBlank(f.getCodigoRegionNueva()) || StringUtils.isNotBlank(f.getCodigoZonaNueva())){			 
			listarHistorico("");
			this.flagOcultarCalendar = true;
		 }		
	}
	
	/**
	 * Valida Fecha Ingreso
	 * @return
	 */
	private String getValidarFechaIngreso(String codigoCargo, String fecha, String region, String zona)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
		String mensaje = null;
		String resultado1 = ajax.getValidarFechaIngreso(codigoCargo, pais.getCodigo(), fecha, region, zona, pais.getCodigoConexionExterna());
		
		if (!resultado1.equals("-1")) {
			if (resultado1.equals("0")) {
				mensaje = "Fecha fuera de rango permitido";
				return mensaje;
			} else if (resultado1.equals("2")) {
				mensaje = "Fecha de campaña cerrada";
				return mensaje;
			} else if (resultado1.equals("3")) {
				mensaje = "No hay campaña vigente";
				return mensaje;
			}
		}
		
		return mensaje;
	}
	
	/**
	 * Valida Cruce Fecha Gerente
	 * @return
	 */
	private String getValidarCruceFechaGeren(String fechaIngresoInicio, String fechaIngresoFin, String codigoRegion, 
			String codigoZona, String tipoCargo, String tipoOperacion)
	{
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
		String mensaje = null;
		
		String resultado2 = ajax.getValidarCruceFechaGeren(pais.getCodigo(), fechaIngresoInicio, fechaIngresoFin, codigoRegion, 
							codigoZona, tipoCargo, tipoOperacion, pais.getCodigoConexionExterna());
		
		if (!resultado2.equals("-1")) 
		 {
			mensaje = "Hay cruces de fechas con la consultora "+resultado2;
			return mensaje;
		  }
		
		return mensaje;
	}
		
	/**
	 *  inicializa popup retiro de personal
	 */
	public void vistaRetirarPersonal() 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'vistaRetirarPersonal' method ");
		}
		
		try {
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this
					.getBean("spusicc.mantenimientoZonDirectorioService");
			GenericoService genericoService = (GenericoService) getBean("genericoService");
			MantenimientoOCRPedidoControlFacturacionService serviceFacturacion = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			
			// parametros para iniciar pantalla
			setListaCargos(null);
			this.listaBusqueda = null;

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			// Fecha de Facturacion y/o Proceso
			Map mapFecha = new HashMap();
			mapFecha.put("codigoPais", pais.getCodigo());
			mapFecha.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			mapFecha.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

			String fechaProceso = "";
			if (StringUtils.equals(pais.getCodigoConexionExterna(), Constants.CONEXION_EXTERNA_FOX)) {
				Map mapControl = service.getControlFacturacionByCriteriaFOX(mapFecha);
				this.fecFacturacion = MapUtils.getString(mapControl, "fechaProceso");
				fechaProceso = MapUtils.getString(mapControl, "fechaProceso");
			} else {
				PedidoControlFacturacion controlFacturacion = serviceFacturacion.getControlFacturacionById(mapFecha);
				this.fecFacturacion = controlFacturacion.getFechaProceso();
				fechaProceso = controlFacturacion.getFechaProceso();
			}

			Map registroSeleccionado = (Map) this.beanRegistroSeleccionado;
			String oidVentDetal = registroSeleccionado.get("oidDirecVentDetal").toString();//ids[0];
			String operacion = registroSeleccionado.get("codigoOperacion").toString();//ids[1];
			String cargo = registroSeleccionado.get("codigoCargo").toString();//ids[2];
			String cliente = registroSeleccionado.get("codigoCliente").toString();//ids[3];
			String fechaRegistro = registroSeleccionado.get("fechaIngreso").toString();//ids[4];
			String campanaProceso = registroSeleccionado.get("campana").toString();//ids[5];

			Map map = new HashMap();
			map.put("oidVentDetal", oidVentDetal);
			map.put("codigoOperacion", operacion);
			map.put("codigoCargo", cargo);
			map.put("codigoCliente", cliente);
			map.put("fechaRegistro", fechaRegistro);
			map.put("codigoPais", pais.getCodigo());
			map.put("campanaProceso", campanaProceso);
			map.put("correlativoCabecera", registroSeleccionado.get("correlativoCabecera").toString());
			map.put("codigoConexionExterna", pais.getCodigoConexionExterna());

			List lstDirecVentCabec = service.getZonDirecVentaCabecList(map);

			Map mapVentCabec = (Map) lstDirecVentCabec.get(0);

			if (!MapUtils.getString(mapVentCabec, "codigoOperacion").equals(Constants.ZON_MANT_CODIGO_OPERACION_RE)) 
			{
				List lstTipoCargo = service.getTipoCargo(mapVentCabec);
				List lstDirVentDetal = null;
				if (CollectionUtils.isNotEmpty(lstTipoCargo) && lstTipoCargo.size() == 1) {
					lstDirVentDetal = service.getZonDirecVentaDetalList(map);
				}

				if (lstDirVentDetal.size() == 1) {
					Map mapDetal = (Map) lstDirVentDetal.get(0);
					f.setCodigoClienteBuscar(MapUtils.getString(mapDetal, "codigoCliente"));
					f.setNombreCliente(MapUtils.getString(mapDetal, "nombre"));
					f.setDescripcionCargo(MapUtils.getString(mapDetal, "descCargo"));
					f.setDocCliente("Documento de Identidad :  " + MapUtils.getString(mapDetal, "docCliente", ""));
					f.setFechaRetiro(fechaProceso);
					f.setFechaRetiroDate(DateUtil.convertStringToDate(f.getFechaRetiro()));
					f.setCodigoCargo(MapUtils.getString(mapDetal, "codigoCargo", ""));
					// Convertimos nuevamente fechaRegistro a del formato original
					// 'yyyy/MM/dd' a 'dd/MM/yyyy'
					Date fregi = DateUtil.convertStringToDate("yyyy/MM/dd", fechaRegistro);
					f.setFechaRegistro(DateUtil.convertDateToString("dd/MM/yyyy", fregi));
					f.setCorrelativoCabecera(registroSeleccionado.get("correlativoCabecera").toString());

					// obtener el listado de zonas y regiones
					Map criteria = new HashMap();

					criteria.put("codigoCliente", cliente);
					criteria.put("codigoPais", pais.getCodigo());

					map.put("codigoZona", MapUtils.getString(mapDetal, "codigoZona"));
					map.put("codigoRegion", MapUtils.getString(mapDetal, "codigoRegion"));

					criteria.put("codigoEstadoCargoRetirarPersonal", Constants.ZON_MANT_ESTADO_CARGO_ACTIVA);
					criteria.put("correlativoCabecera", MapUtils.getString(mapDetal, "correlativoCabecera", ""));
					criteria.put("codigoConexionExterna", pais.getCodigoConexionExterna());

					List lstUnidades = service.getZonDirecVentaDetalList(criteria);

					if (lstUnidades == null || lstUnidades.size() == 0) {
						criteria.put("codigoEstadoCargoRetirarPersonal", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA_TEMPORAL);
						lstUnidades = service.getZonDirecVentaDetalList(criteria);
					}

					this.lstUnidades = lstUnidades;
					this.tipoUnidAdmi = ((Map)lstUnidades.get(0)).get("tipoUnidAdmi").toString();
					this.lstUnidadesModel = new DataTableModel(this.lstUnidades);
					this.directorioDetalle = map;
				}
			} else {
				this.addInfo("", this.getResourceMessage("mantenimientoZONCargosForm.retirado"));
				return;
			}

			// Setear el Rol y Perfil para el Cargo Actual
			String rolPerfil = ajaxService.getRolPerfil(f.getCodigoCargo()).trim();
			String[] arrayRP = StringUtils.split(rolPerfil, "|");
			f.setRolDesc(arrayRP[0]);
			f.setPerfilDesc(arrayRP[1]);

			// Obtener CampañaFacturacion y CampañaVenta a partir de la fechaIngreso obtenida.
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("codigoPais", pais.getCodigo());
			criteriaPeriodo.put("fecha", f.getFechaRetiro());
			criteriaPeriodo.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteriaPeriodo.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteriaPeriodo.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			String campanyaRelacionada = genericoService.getPeriodoByFecha(criteriaPeriodo);

			f.setCampanyaFacturacion(campanyaRelacionada);

			criteriaPeriodo.put("codigoPeriodo", campanyaRelacionada);
			criteriaPeriodo.put("numeroPeriodo", 1);
			String campanyaRelacionadaSgte = genericoService.getPeriodoNSiguiente(criteriaPeriodo);

			f.setCampanyaVenta(campanyaRelacionadaSgte);
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	/**
	 * Guarda los cambios en el popup retiro de personal 
	 * @param event
	 */
	public void grabarRetiro(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'grabarRetiro' method");
		}
		
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

			MantenimientoZONDirectorioService service = (MantenimientoZONDirectorioService) this
					.getBean("spusicc.mantenimientoZonDirectorioService");
			AjaxService ajax = (AjaxService) getBean("ajaxService");

			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;

			Map map = this.directorioDetalle;

			Map cabec = new HashMap();
			String fechaCam = ajax.getPeriodoByFecha(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getFechaRetiro());
			
			if (fechaCam != null) {
				if (!fechaCam.equals(MapUtils.getString(map, "campanaProceso"))) {
					cabec.put("campanyaProceso", fechaCam);
				} else
					cabec.put("campanyaProceso", MapUtils.getString(map, "campanaProceso"));
			} else {
				this.addError("Error: ", this.getResourceMessage("mantenimientoZONCargosForm.message.error.fecha.sincampania"));
				this.verificaGrabado = "N";
				return;
			}
			
			cabec.put("codigoConsultora", MapUtils.getString(map, "codigoCliente"));
			cabec.put("codigoTipoCargo", MapUtils.getString(map, "codigoCargo"));
			cabec.put("tipoOperacion", Constants.ZON_MANT_CODIGO_OPERACION_RE);
			cabec.put("fechaIngreso", f.getFechaRetiro());
			cabec.put("fechaIngresoHasta", f.getFechaRetiro());
			cabec.put("codigoPais", MapUtils.getString(map, "codigoPais"));
			cabec.put("usuarioLogin", usuario.getLogin());
			cabec.put("nombresCompletosConsultora", f.getNombresCompletosConsultora());
			cabec.put("estadoRegistro", Constants.ESTADO_ACTIVO);
			cabec.put("codigoEstadoCargo", Constants.ZON_MANT_ESTADO_CARGO_INACTIVA);
			cabec.put("envioCorreo", Constants.SI);
			cabec.put("correlativoCabecera", f.getCorrelativoCabecera());
			cabec.put("codigoConexionExterna", pais.getCodigoConexionExterna());
			cabec.put("directorioDetalle", map);

			List lstDet = this.lstUnidades;
			service.insertDirectorioVentaRetiro(cabec, lstDet);
			
			// muestra mensjae de ejecucion correcta
			mostrarMensaje(this.getResourceMessage("mantenimientoZONCargosForm.persona.retirada"));								
//			actualizarGrilla();						
			this.verificaGrabado = "S";
			RequestContext.getCurrentInstance().update("idBody_encabezado_detalle");			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			this.verificaGrabado = "N";
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
				
		if(accion.equalsIgnoreCase("CambiarCargos"))
		{
			f.setFechaNombramiento(DateUtil.convertDateToString(f.getFechaNombramientoDate()));
			f.setFechaIngresoHasta(DateUtil.convertDateToString(f.getFechaIngresoHastaDate()));
			
			if (StringUtils.isBlank(f.getFechaNombramiento())){
				mensaje = "'Fecha Nombramiento' es un campo requerido.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			if(StringUtils.isBlank(f.getCodigoNuevoCargo()))
			{
				mensaje = "'Nuevo Cargo' es un campo requerido.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			if(this.flagOcultarMultiple) {	
				if (f.getCodigoRegion() == null || f.getCodigoRegion().length == 0) {
					mensaje = "'Región' es un campo requerido.";
					System.out.println("mensaje: " + mensaje);
					return mensaje;
					}
				
				if(!this.flagOcultarZonas){
					for (String zona : f.getCodigoZona()) {
						if (StringUtils.isBlank(zona)) {
							mensaje = "'Zona' es un campo requerido.";
							System.out.println("mensaje: "+mensaje);
							return mensaje;							
						}
					}					
				}				
			} else {
				if (StringUtils.isBlank(f.getCodigoRegionUnico())) {
					mensaje = "'Región' es un campo requerido.";
					System.out.println("mensaje: " + mensaje);
					return mensaje;
				}
				
				if(!this.flagOcultarZonas){
					if(StringUtils.isBlank(f.getCodigoZonaUnico())){
						mensaje = "'Zona' es un campo requerido.";
						System.out.println("mensaje: "+mensaje);
						return mensaje;						
					}					
				}			
			}			
			
			if(DateUtil.compareDates(f.getFechaRegistro(), f.getFechaNombramiento(), "dd/MM/yyyy") > 0)
			{
				mensaje = "Fecha de Nombramiento no puede ser menor a la de ingreso.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;				
			}
			
			int resultadoFechas = DateUtil.compareDates(f.getFechaNombramiento(), f.getFechaIngresoHasta(),"dd/MM/yyyy");
        	if(resultadoFechas == 1){
        		mensaje = "La Fecha de Ingreso Hasta debe ser mayor o igual a la Fecha Ingreso Desde";
        		return mensaje;
        	}
			
        	//Inicio : Valida Fecha Ingreso (Desde - Hasta)			
			if(this.flagOcultarZonas)
			{ 
				f.setCodigoZona(new String[]{""});
				f.setCodigoZonaUnico("");
			}
			
			mensaje = getValidarFechaIngreso(f.getCodigoNuevoCargo(), f.getFechaNombramiento(), 
					f.getCodigoRegionUnico(), f.getCodigoZonaUnico());
			if(StringUtils.isNotBlank(mensaje))
				return mensaje;				
			//Fin : Valida Fecha Ingreso (Desde - Hasta)
			
			//Inicio : Valida Cruce Fecha Gerente (Desde - Hasta)						
			 if(StringUtils.isNotBlank(f.getFechaNombramiento()) && StringUtils.isNotBlank(f.getFechaIngresoHasta()))
			 {
				 if(this.flagOcultarZonas)
				{
					f.setCodigoZona(new String[]{""});
					f.setCodigoZonaUnico("");
				}
				 
				mensaje = getValidarCruceFechaGeren(f.getFechaNombramiento(),  f.getFechaIngresoHasta(), f.getCodigoRegionUnico(), 
						 f.getCodigoZonaUnico(), f.getCodigoNuevoCargo(), null);				
				 
				 if(StringUtils.isNotBlank(mensaje))
					 return mensaje;
			  }
			 // Fin : Valida Cruce Fecha Gerente (Desde - Hasta)	
			
			 if(StringUtils.isNotBlank(f.getCampanyaFacturacion()) && f.getCampanyaFacturacion().equalsIgnoreCase("Fecha sin campaña."))
				{
					mensaje = "Fecha sin campaña.";
					System.out.println("mensaje: "+mensaje);
					return mensaje;
		    	}		
		}
		
		if(accion.equals("rotarCargos"))
		{
			f.setFechaRotacion(DateUtil.convertDateToString(this.fechaRotacionDate));
			f.setFechaIngresoHasta(DateUtil.convertDateToString(f.getFechaIngresoHastaDate()));
			
			if(StringUtils.isBlank(f.getFechaRotacion()))
			{
				mensaje = "'Fecha de Rotación' es un campo requerido.";
				return mensaje;						
			}
			
			boolean flag = false;
			
			for (Object objeto : this.listaRotar) {
				Map estado= (Map) objeto;
				if(estado.get("estadoRegistro")!= null)
					flag= true;
				else
					flag= false;
			} 
			
			if(!flag)
			{
				if (StringUtils.isBlank(f.getCodigoRegionNueva())) {
					mensaje = "El campo 'Nueva Region' es requerida.";
					System.out.println("mensaje: " + mensaje);
					return mensaje;
				}

				if (StringUtils.isBlank(f.getCodigoZonaNueva())) {
					mensaje = "El campo 'Nueva Zona' es requerida.";
					System.out.println("mensaje: " + mensaje);
					return mensaje;
				}				
			}			
			        	
			if(DateUtil.compareDates(f.getFechaRotacion(), f.getFechaIngresoHasta(), "dd/MM/yyyy") == 1){
				mensaje = "La Fecha de Rotación Hasta debe ser mayor o igual a la Fecha Rotación Desde.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			mensaje = getValidarFechaIngreso(f.getCodigoCargo(), f.getFechaRotacion(), null, null);
			if(StringUtils.isNotBlank(mensaje))
				return mensaje;		
			
			 if(StringUtils.isNotBlank(f.getCampanyaFacturacion()) && f.getCampanyaFacturacion().equalsIgnoreCase("Fecha sin campaña."))
				{
					mensaje = "Fecha sin campaña.";
					System.out.println("mensaje: "+mensaje);
					return mensaje;
		    	}				
		}
		
		if(accion.equals("retirarPersonal"))
		{
			f.setFechaRetiro(DateUtil.convertDateToString(f.getFechaRetiroDate()));
			if(StringUtils.isBlank(f.getFechaRetiro()))
			{
				mensaje = "El campo Fecha Retiro es requerido.";
				System.out.println("mensaje: "+mensaje);
				return mensaje;
			}
			
			//Se valida solo cargos Base.
			String cbase = ajax.getCargoBaseById(f.getCodigoCargo());
			String fechaProcesoFacturacion = ajax.getPeriodoFechaCampanyaActivaSF(pais.getCodigo(), pais.getCodigoConexionExterna())[1];
			
			if(cbase.equals("S"))
			{
				//La fecha de retiro no puede ser mayor que la fecha de facturación.
				if(DateUtil.compareDates(f.getFechaRetiro(), fechaProcesoFacturacion, "dd/MM/yyyy") > 0 )
				{
					mensaje = "La Fecha de Retiro no puede ser mayor a la de facturación: " + fechaProcesoFacturacion;
					System.out.println("mensaje: "+mensaje);
					return mensaje;
	        	}
				
				//La fecha de retiro no puede ser menor que la fecha de registro.
				if(DateUtil.compareDates(f.getFechaRegistro(), f.getFechaRetiro(), "dd/MM/yyyy") > 0 )
				{
					mensaje = "La Fecha de Retiro no puede ser menor a la de ingreso: " + f.getFechaRegistro();
					System.out.println("mensaje: "+mensaje);
					return mensaje;
    			}
			}
			
			if (StringUtils.isNotBlank(f.getCampanyaFacturacion()) && f.getCampanyaFacturacion().equalsIgnoreCase("Fecha sin campaña.")) 
			{
				mensaje = "Fecha sin campaña.";
				System.out.println("mensaje: " + mensaje);
				return mensaje;
			}			
		}		
		return mensaje;
	}
	
	@Override
	public String setAlertasAntesdeConfirmar(String accion) {
		String mensaje = null;

		if (accion.equals("AsignarCargos")) 
		{
			MantenimientoZONCargosForm f = (MantenimientoZONCargosForm) this.formBusqueda;
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); 
			
			String resultado = ajax.obtenerDatosConsultoraAsignarCargo(pais.getCodigo(), pais.getCodigoConexionExterna(), f.getCodigoClienteBuscar());
			
			String codigoEstadoRegistro = resultado.split("_")[2];		   //IND_ESTA	        --DE LA TABLA ZON_DIREC_VENTA_CABEC	
			String codigoEstadoCargo = resultado.split("_")[3];	   //ESCA_COD_ESTA_CARG --DE LA TABLA ZON_DIREC_VENTA_CABEC
			String codigoEstadoDVCabecera = resultado.split("_")[4]; //EST_REGI 			--DE LA TABLA ZON_DIREC_VENTA_CABEC
						
			if (codigoEstadoCargo.equals("A") && codigoEstadoDVCabecera.equals("1")) 
			{
				mensaje = this.getResourceMessage("mantenimientoZONCargosForm.cargo.asignar.activo");
				return mensaje;
			}
		}

		return mensaje;
	}
	
	private void mostrarMensaje(String mensaje)
	{
		this.setMensajeAlertaDefaultAction(mensaje);
		RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
		String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
		this.getRequestContext().execute(ventana);
	}
	
	private void actualizarGrilla() throws Exception
	{
		MantenimientoZONDirectorioVentasSearchAction mante = this.findManageBean("mantenimientoZONDirectorioVentasSearchAction");
		mante.find();
		mante.setListaBusqueda(mante.setFindAttributes());
		mante.setDatatableBusqueda(new DataTableModel(mante.getListaBusqueda()));
		RequestContext.getCurrentInstance().update("listaBusquedaForm");
	}

	public List getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(List listaCargos) {
		this.listaCargos = listaCargos;
	}

	public Map getRegistroSeleccionadoPrincipal() {
		return registroSeleccionadoPrincipal;
	}

	public void setRegistroSeleccionadoPrincipal(Map registroSeleccionadoPrincipal) {
		this.registroSeleccionadoPrincipal = registroSeleccionadoPrincipal;
	}

	public List getZonPerfilList() {
		return zonPerfilList;
	}

	public void setZonPerfilList(List zonPerfilList) {
		this.zonPerfilList = zonPerfilList;
	}

	public List getZonRolList() {
		return zonRolList;
	}

	public void setZonRolList(List zonRolList) {
		this.zonRolList = zonRolList;
	}

	public List getZonCodCargosList() {
		return zonCodCargosList;
	}

	public void setZonCodCargosList(List zonCodCargosList) {
		this.zonCodCargosList = zonCodCargosList;
	}

	public Boolean getIndicadorAdmin() {
		return indicadorAdmin;
	}

	public void setIndicadorAdmin(Boolean indicadorAdmin) {
		this.indicadorAdmin = indicadorAdmin;
	}

	public String getEstadoInactivo() {
		return estadoInactivo;
	}

	public void setEstadoInactivo(String estadoInactivo) {
		this.estadoInactivo = estadoInactivo;
	}

	public Boolean getOcultarBotones() {
		return ocultarBotones;
	}

	public void setOcultarBotones(Boolean ocultarBotones) {
		this.ocultarBotones = ocultarBotones;
	}

	public String getZonMantOidIdioma() {
		return zonMantOidIdioma;
	}

	public void setZonMantOidIdioma(String zonMantOidIdioma) {
		this.zonMantOidIdioma = zonMantOidIdioma;
	}

	public String getZonMantCambiarTipoCargoCerrar() {
		return zonMantCambiarTipoCargoCerrar;
	}

	public void setZonMantCambiarTipoCargoCerrar(
			String zonMantCambiarTipoCargoCerrar) {
		this.zonMantCambiarTipoCargoCerrar = zonMantCambiarTipoCargoCerrar;
	}

	public List getZonMantCambiarTipoCargoList() {
		return zonMantCambiarTipoCargoList;
	}

	public void setZonMantCambiarTipoCargoList(List zonMantCambiarTipoCargoList) {
		this.zonMantCambiarTipoCargoList = zonMantCambiarTipoCargoList;
	}

	public LabelValue[] getZonMantCambiarRegionList() {
		return zonMantCambiarRegionList;
	}

	public void setZonMantCambiarRegionList(LabelValue[] zonMantCambiarRegionList) {
		this.zonMantCambiarRegionList = zonMantCambiarRegionList;
	}

	public LabelValueCDR[] getZonMantCambiarZonaList() {
		return zonMantCambiarZonaList;
	}

	public void setZonMantCambiarZonaList(LabelValueCDR[] zonMantCambiarZonaList) {
		this.zonMantCambiarZonaList = zonMantCambiarZonaList;
	}

	public String getZonMantRotarTipoCargoCerrar() {
		return zonMantRotarTipoCargoCerrar;
	}

	public void setZonMantRotarTipoCargoCerrar(String zonMantRotarTipoCargoCerrar) {
		this.zonMantRotarTipoCargoCerrar = zonMantRotarTipoCargoCerrar;
	}

	public List getListaRotar() {
		return listaRotar;
	}

	public void setListaRotar(List listaRotar) {
		this.listaRotar = listaRotar;
	}

	public DataTableModel getListaRotarModel() {
		return listaRotarModel;
	}

	public void setListaRotarModel(DataTableModel listaRotarModel) {
		this.listaRotarModel = listaRotarModel;
	}

	public String getTipoUnidAdmi() {
		return tipoUnidAdmi;
	}

	public void setTipoUnidAdmi(String tipoUnidAdmi) {
		this.tipoUnidAdmi = tipoUnidAdmi;
	}

	public List getListaRegiDisp() {
		return listaRegiDisp;
	}

	public void setListaRegiDisp(List listaRegiDisp) {
		this.listaRegiDisp = listaRegiDisp;
	}

	public List getListaZonaDisp() {
		return listaZonaDisp;
	}

	public void setListaZonaDisp(List listaZonaDisp) {
		this.listaZonaDisp = listaZonaDisp;
	}

	public Date getFechaRotacionDate() {
		return fechaRotacionDate;
	}

	public void setFechaRotacionDate(Date fechaRotacionDate) {
		this.fechaRotacionDate = fechaRotacionDate;
	}

	public Boolean getFlagOcultarCalendar() {
		return flagOcultarCalendar;
	}

	public void setFlagOcultarCalendar(Boolean flagOcultarCalendar) {
		this.flagOcultarCalendar = flagOcultarCalendar;
	}

	public List getLstUnidades() {
		return lstUnidades;
	}

	public void setLstUnidades(List lstUnidades) {
		this.lstUnidades = lstUnidades;
	}

	public DataTableModel getLstUnidadesModel() {
		return lstUnidadesModel;
	}

	public void setLstUnidadesModel(DataTableModel lstUnidadesModel) {
		this.lstUnidadesModel = lstUnidadesModel;
	}

	public Map getDirectorioDetalle() {
		return directorioDetalle;
	}

	public void setDirectorioDetalle(Map directorioDetalle) {
		this.directorioDetalle = directorioDetalle;
	}

	public Boolean getFlagOcultarMultiple() {
		return flagOcultarMultiple;
	}

	public void setFlagOcultarMultiple(Boolean flagOcultarMultiple) {
		this.flagOcultarMultiple = flagOcultarMultiple;
	}

	public Boolean getFlagOcultarZonas() {
		return flagOcultarZonas;
	}

	public void setFlagOcultarZonas(Boolean flagOcultarZonas) {
		this.flagOcultarZonas = flagOcultarZonas;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getFecFacturacion() {
		return fecFacturacion;
	}

	public void setFecFacturacion(String fecFacturacion) {
		this.fecFacturacion = fecFacturacion;
	}

	public List getListaFiltrada() {
		return listaFiltrada;
	}

	public void setListaFiltrada(List listaFiltrada) {
		this.listaFiltrada = listaFiltrada;
	}

	/**
	 * @return the verificaGrabado
	 */
	public String getVerificaGrabado() {
		return verificaGrabado;
	}

	/**
	 * @param verificaGrabado the verificaGrabado to set
	 */
	public void setVerificaGrabado(String verificaGrabado) {
		this.verificaGrabado = verificaGrabado;
	}

	/**
	 * @return the verificaRotarGrabar
	 */
	public String getVerificaRotarGrabar() {
		return verificaRotarGrabar;
	}

	/**
	 * @param verificaRotarGrabar the verificaRotarGrabar to set
	 */
	public void setVerificaRotarGrabar(String verificaRotarGrabar) {
		this.verificaRotarGrabar = verificaRotarGrabar;
	}
	

}
