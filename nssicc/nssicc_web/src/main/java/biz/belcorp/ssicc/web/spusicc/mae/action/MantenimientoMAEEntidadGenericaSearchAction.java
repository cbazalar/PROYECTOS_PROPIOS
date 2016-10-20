package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoMAEEntidadGenericaSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1987501815173788281L;
	
	private List maeCodigoEntidadGenericaList;
	private List maeEntidadGenericaList;
	private List maeCodigoTipoEstatusList;
	private List maeCodigoTipoClienteList;
	private LabelValue[] maeCodigoTipoClienteListC;
	private LabelValue[] maeCodigoTipoClienteListTC;
	private List maeCodigoSubTipoClienteList;
	private LabelValue[] maeCodigoTipoClasificacionList;
	private List maeMarcasList;
	private List maeCriteriosList;
	private List maeTipoBloqueoAccionList;
	private List maeProcesoBloqueoAccionList;
	private LabelValue[] maeAccionBloqueoAccionList;
	
	private String maeCodigoInterfazInc = Constants.MAE_CODIGO_INTERFAZ_INC;
	private String maeCodigoInterfazMae = Constants.MAE_CODIGO_INTERFAZ_MAE;
	
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;
	
	//flag para mostrar los campos
	private Boolean mostrarMaeCriteBusqueda;
	private Boolean mostrarMaeTipoClasiClien;
	private Boolean mostrarMaeTipoDocum;
	private Boolean mostrarMaeTipoEstatClien;
	private Boolean mostrarMaeTipoBloqu;
	private Boolean mostrarIncProduGaran;
	private Boolean mostrarMaeTipoClien;
	private Boolean mostrarMaeClasi;
	private Boolean mostrarMaeSubtiClien;
	private Boolean mostrarMaeEstatClien;
	private Boolean mostrarMaeEstadCivil;
	private Boolean mostrarMaeTipoDirec;
	private Boolean mostrarMaeTipoComun;
	private Boolean mostrarMaeTipoVincu;
	private Boolean mostrarMaeAccioProceBloqu;
	
	//instancia de manage para las segunda pantalla
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaAccionesProcesoBloqueoAction}")	
	private MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoAction mantenimientoProcesoBloqueo;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaClasificacionAction}")
	private MantenimientoMAEEntidadGenericaClasificacionAction mantenimientoClasificaciones;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaCriterioBusquedaAction}")
	private MantenimientoMAEEntidadGenericaCriterioBusquedaAction mantenimientoBusqueda;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaTipoClasificacionAction}")
	private MantenimientoMAEEntidadGenericaTipoClasificacionAction mantenimientoTipoClasificaciones;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaTipoComunicacionAction}")
	private MantenimientoMAEEntidadGenericaTipoComunicacionAction mantenimientoTipoComunicacion;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaTipoDireccionAction}")
	private MantenimientoMAEEntidadGenericaTipoDireccionAction mantenimientoTipoDireccion;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaTipoDocumentoAction}")
	private MantenimientoMAEEntidadGenericaTipoDocumentoAction mantenimientoTipoDocumento;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaEstadoCivilAction}")
	private MantenimientoMAEEntidadGenericaEstadoCivilAction mantenimientoEstadoCivil;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaTipoEstatusClienteAction}")
	private MantenimientoMAEEntidadGenericaTipoEstatusClienteAction mantenimientoTipoEstatusCliente;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaTipoVinculoAction}")
	private MantenimientoMAEEntidadGenericaTipoVinculoAction mantenimientoTipoVinculo;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaTipoBloqueoAction}")
	private MantenimientoMAEEntidadGenericaTipoBloqueoAction mantenimientoTipoBloqueo;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaTipoClienteAction}")
	private MantenimientoMAEEntidadGenericaTipoClienteAction mantenimientoTipoCliente;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaSubtipoClienteAction}")
	private MantenimientoMAEEntidadGenericaSubtipoClienteAction mantenimientoSubTipoCliente;
	
	@ManagedProperty(value="#{mantenimientoMAEEntidadGenericaEstatusClienteAction}")
	private MantenimientoMAEEntidadGenericaEstatusClienteAction mantenimientoEstatusCliente;

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
		MantenimientoMAEEntidadGenericaSearchForm f = new MantenimientoMAEEntidadGenericaSearchForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes - MantenimientoMAEEntidadGenericaSearchAction");
		}
		
		MantenimientoMAEEntidadGenericaSearchForm f = (MantenimientoMAEEntidadGenericaSearchForm) this.formBusqueda;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String codigoInterfaz = this.parametrosPantalla.get("codigoInterfaz");
		if(StringUtils.isNotBlank(codigoInterfaz)){
			f.setCodigoInterfaz(codigoInterfaz);
		}
		String descripcion = f.getDescripcion();
		//para tipo de cliente
		String descripcionTipoCliente = f.getDescripcionTipoCliente();
		String codigoTipoCliente = f.getCodigoTipoCliente();
		String indicadorEvaluarEstatus = f.getIndicadorEvaluarEstatus();
		String indicadorEmpleado = f.getIndicadorEmpleado();
		
		//para subtipo de cliente
		String codigoSubtipoCliente= f.getCodigoSubtipoCliente();
		String descripcionSubtipoCliente = f.getDescripcionSubtipoCliente();
		String codigoTipoClienteST = f.getCodigoTipoClienteST();
		
		//para estatus cliente
		String codigoEstadoCliente = f.getCodigoEstatusCliente();
		String descripcionEstatusCliente = f.getDescripcionEstatusCliente();
		String codigoTipoEstatus = f.getCodigoTipoEstatus();
		String estatusPosteriorPosible = f.getEstatusPosteriorPosible();
		
		//para garantías de premios
		String codigoSAP = f.getCodigoSAP();
		
		//para Clasificaciones
		
		String tipoCliente = f.getCodigoTipoClienteCL();
		String tipoSubCliente = f.getCodigoTipoSubCliente();
		String tipoClasificacion = f.getCodigoTipoClasificacion();
		String codigoClasificacion = f.getCodigoClasificacion();
		String descClasificacion = f.getDescripcionClasificacion();
		
		//para Tipo Clasificacion
		String codTipoClasificacion = f.getCodTipoClasificacion();
		String descTipoClasificacion = f.getDescripcionTipoClasificacion();
		String codTCCliente = f.getCodigoTipoClienteTC();
		String codTCSubCliente = f.getCodigoTipoSubClienteTC();
		
		//Para tipo Estatus
		String marcaTE = f.getMarcaTipoEstatus();
		String codigoTE = f.getCodigoTipoEstatus();
		String descTE = f.getDescripcionTipoEstatus();
		
		// Para Estado Civil
		
		String codigoCivil = f.getCodigoCivil();
		String descripcionCivil = f.getDescripcionCivil();
		String codigoTipoDirec = f.getCodigoTipoDirec();
		String descripcionTipoDirec = f.getDescripcionTipoDirec();
		String codigoTipoComu = f.getCodigoTipoComu();
		String descripcionTipoComu = f.getDescripcionTipoComu();
		String codigoTipoVinculo = f.getCodigoTipoVinculo();
		String descripcionVinculo = f.getDescripcionVinculo();
		String recomendado = f.getRecomendado();
		
		String codTipoDocumento = f.getCodTipoDocumento();
		String siglas = f.getSiglas();
		String descripcionTipoDocumento = f.getDescripcionTipoDocumento();
		
		String atributo1 = f.getAtributo1();
		String atributo2 = f.getAtributo2();
		
		//para acciones proceso bloqueo
		String oidTipoBloqueo = f.getOidTipoBloqueo();
		String oidProcesoBloqueo = f.getOidProcesoBloqueo();
		String oidAccionBloqueo = f.getOidAccionBloqueo();
		String indicadorEstado = f.getIndicadorEstado();
		
		
		Map params = BeanUtils.describe(f);
		params.put("descripcion", "%" + (StringUtils.isBlank(descripcion)?"":StringUtils.trim(descripcion)));
		params.put("descripcionTipoCliente", "%" + (StringUtils.isBlank(descripcionTipoCliente)?"":StringUtils.trim(descripcionTipoCliente)));
		params.put("codigoTipoCliente", (StringUtils.isBlank(codigoTipoCliente)?"":StringUtils.trim(codigoTipoCliente)));
		params.put("indicadorEvaluarEstatus", (StringUtils.isBlank(indicadorEvaluarEstatus)?"":StringUtils.trim(indicadorEvaluarEstatus)));
		params.put("indicadorEmpleado", (StringUtils.isBlank(indicadorEmpleado)?"":StringUtils.trim(indicadorEmpleado)));
		params.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		
		//subtipoCliente
		params.put("codigoSubtipoCliente", (StringUtils.isBlank(codigoSubtipoCliente)?"":StringUtils.trim(codigoSubtipoCliente)));
		params.put("descripcionSubtipoCliente", "%" + (StringUtils.isBlank(descripcionSubtipoCliente)?"":StringUtils.trim(descripcionSubtipoCliente)));						
		params.put("oidTipoCliente", (StringUtils.isBlank(codigoTipoClienteST)?"":StringUtils.trim(codigoTipoClienteST)));
		
		//estatusCliente
		params.put("codigoEstadoCliente", (StringUtils.isBlank(codigoEstadoCliente)?"":StringUtils.trim(codigoEstadoCliente)));
		params.put("descripcionEstatusCliente", "%" + (StringUtils.isBlank(descripcionEstatusCliente)?"":StringUtils.trim(descripcionEstatusCliente)));						
		params.put("codigoTipoEstatus", (StringUtils.isBlank(codigoTipoEstatus)?"":StringUtils.trim(codigoTipoEstatus)));
		params.put("estatusPosteriorPosible", (StringUtils.isBlank(estatusPosteriorPosible)?"":StringUtils.trim(estatusPosteriorPosible)));
		
		//Garantias Premios
		params.put("codigoSAP", (StringUtils.isBlank(codigoSAP)?"":StringUtils.trim(codigoSAP)));
		
		//Clasificaciones
		params.put("oidTipoClienteCL", (StringUtils.isBlank(tipoCliente)?"":StringUtils.trim(tipoCliente)));
		params.put("oidSubTipoCliente", (StringUtils.isBlank(tipoSubCliente)?"":StringUtils.trim(tipoSubCliente)));
		params.put("oidTipoClasificacion", (StringUtils.isBlank(tipoClasificacion)?"":StringUtils.trim(tipoClasificacion)));
		params.put("codClas", (StringUtils.isBlank(codigoClasificacion)?"":StringUtils.trim(codigoClasificacion)));
		params.put("descripcionClas", (StringUtils.isBlank(descClasificacion)?"":StringUtils.trim(descClasificacion)));
		
		//Tipo Clasificación
		params.put("tipoCodClas", (StringUtils.isBlank(codTipoClasificacion)?"":StringUtils.trim(codTipoClasificacion)));
		params.put("descripcionTipoClas", (StringUtils.isBlank(descTipoClasificacion)?"":StringUtils.trim(descTipoClasificacion)));
		params.put("oidTipoClienteTCL", (StringUtils.isBlank(codTCCliente)?"":StringUtils.trim(codTCCliente)));
		params.put("oidSubTipoClienteTCL", (StringUtils.isBlank(codTCSubCliente)?"":StringUtils.trim(codTCSubCliente)));
		
		//TIPO ESTATUS
		params.put("marca", (StringUtils.isBlank(marcaTE)?"":StringUtils.trim(marcaTE)));
		params.put("descripcionEstatus", (StringUtils.isBlank(descTE)?"":StringUtils.trim(descTE)));
		params.put("codigo", (StringUtils.isBlank(codigoTE)?"":StringUtils.trim(codigoTE)));
		
		params.put("codigoCivil", (StringUtils.isBlank(codigoCivil)?"":StringUtils.trim(codigoCivil)));
		params.put("descripcionCivil", (StringUtils.isBlank(descripcionCivil)?"":StringUtils.trim(descripcionCivil)));
		params.put("codigoTipoDirec", (StringUtils.isBlank(codigoTipoDirec)?"":StringUtils.trim(codigoTipoDirec)));
		params.put("descripcionTipoDirec", (StringUtils.isBlank(descripcionTipoDirec)?"":StringUtils.trim(descripcionTipoDirec)));
		params.put("codigoTipoComu", (StringUtils.isBlank(codigoTipoComu)?"":StringUtils.trim(codigoTipoComu)));
		params.put("descripcionTipoComu", (StringUtils.isBlank(descripcionTipoComu)?"":StringUtils.trim(descripcionTipoComu)));
		params.put("codigoTipoVinculo", (StringUtils.isBlank(codigoTipoVinculo)?"":StringUtils.trim(codigoTipoVinculo)));
		params.put("descripcionVinculo", (StringUtils.isBlank(descripcionVinculo)?"":StringUtils.trim(descripcionVinculo)));
		params.put("recomendado", (StringUtils.isBlank(recomendado)?"":StringUtils.trim(recomendado)));
		
		
		params.put("codigoTDOC", (StringUtils.isBlank(codTipoDocumento)?"":StringUtils.trim(codTipoDocumento)));
		params.put("siglasTDOC", (StringUtils.isBlank(siglas)?"":StringUtils.trim(siglas)));
		params.put("descripcionTDOC", (StringUtils.isBlank(descripcionTipoDocumento)?"":StringUtils.trim(descripcionTipoDocumento)));
		
		params.put("atributo1", (StringUtils.isBlank(atributo1)?"":StringUtils.trim(atributo1)));
		params.put("atributo2", (StringUtils.isBlank(atributo2)?"":StringUtils.trim(atributo2)));
		
		//ACCIONES PROCESO BLOQUEO
		params.put("oidTipoBloqueo", (StringUtils.isBlank(oidTipoBloqueo)?"":StringUtils.trim(oidTipoBloqueo)));
		params.put("oidProcesoBloqueo", (StringUtils.isBlank(oidProcesoBloqueo)?"":StringUtils.trim(oidProcesoBloqueo)));
		params.put("oidAccionBloqueo", (StringUtils.isBlank(oidAccionBloqueo)?"":StringUtils.trim(oidAccionBloqueo)));
		params.put("indicadorEstado", (StringUtils.isBlank(indicadorEstado)?"":StringUtils.trim(indicadorEstado)));
		
		List motivos = (List)service.getDatosEntidadGenericaByCriteria(params);
		this.maeEntidadGenericaList = motivos;	
		
		return motivos;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		String id = ""; //request.getParameter("id");
		Map bean = (Map)this.beanRegistroSeleccionado;
		
		String codigoEntidad =  "";//request.getParameter("codigoEntidad");
		
		if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_BLOQUEO))
		{
			service.removeTipoBloqueo(id, usuario);
		}else {
			if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLIENTE))
			{
				service.removeTipoCliente(id, usuario);
			}else {
				if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_SUBTIPO_CLIENTE))
				{
					service.removeSubtipoCliente(id, usuario);
				}else {
					if(StringUtils.equalsIgnoreCase(codigoEntidad, Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTATUS_CLIENTE))
					{
						service.removeEstatusCliente(id, usuario);
					}
				}
			}
		}
				
		this.addInfo("", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.deleted"));
			
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if(log.isDebugEnabled()){
			log.debug("setViewAttributes - MantenimientoMAEEntidadGenericaSearchAction");
		}
		String codigoInterfaz = this.parametrosPantalla.get("codigoInterfaz");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
				
		Map entidad = new HashMap();
		entidad.put("codigoInterfaz", codigoInterfaz);
		entidad.put("codigoPais", pais.getCodigo());
		
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService)getBean("spusicc.mantenimientoMAEEntidadGenericaService");
				
		this.maeCodigoEntidadGenericaList = service.getEntidades(entidad);
		this.maeEntidadGenericaList = new ArrayList();
		
		this.maeCodigoTipoEstatusList = service.getTipoEstadosCliente();
		
		Map criteria = new HashMap();
		criteria.put("codigoIdioma", usuario.getIdioma().getCodigoISO());
		this.maeCodigoTipoClienteList = service.getTipoClienteList(criteria);
		
		this.maeCodigoSubTipoClienteList = new ArrayList();
		this.maeMarcasList = service.getMarcas();
		this.maeCriteriosList = service.getCriterios();
		
		this.maeTipoBloqueoAccionList = service.getTipoBloqueoList(criteria);
		this.maeProcesoBloqueoAccionList = service.getProcesoBloqueoList();
		
		MantenimientoMAEEntidadGenericaSearchForm f = (MantenimientoMAEEntidadGenericaSearchForm) this.formBusqueda;
	
		f.setCodigoInterfaz(codigoInterfaz);	
		f.setCodigoPais("");
		f.setCodigoEntidad("");
		f.setCodigo("");
		f.setDescripcion("");
		f.setCodigoTipoCliente("");
		f.setDescripcionTipoCliente("");
		f.setIndicadorEmpleado("");
		f.setIndicadorEvaluarEstatus("");
		f.setCodigoTipoClienteST("");
		f.setCodigoSubtipoCliente("");
		f.setDescripcionSubtipoCliente("");
		f.setCodigoTipoEstatus("");
		f.setCodigoEstatusCliente("");
		f.setDescripcionEstatusCliente("");
		f.setEstatusPosteriorPosible("");
		f.setCodigoSAP("");
		f.setIndicadorRegistro("");
		f.setNumDias("");
		
		f.setCodigoTipoClienteCL("");
		f.setCodigoTipoClasificacion("");
		f.setCodigoTipoSubCliente("");
		f.setCodigoClasificacion("");
		f.setDescripcionClasificacion("");
		
		f.setCodigoTipoSubClienteTC("");
		f.setCodigoTipoClienteTC("");
		f.setCodTipoClasificacion("");
		f.setDescripcionTipoClasificacion("");
		
		f.setDescripcionTipoEstatus("");
		f.setMarcaTipoEstatus("");
		
		f.setCodigoCivil("");
		f.setDescripcionCivil("");
		f.setCodigoTipoDirec("");
		f.setDescripcionTipoDirec("");
		f.setCodigoTipoComu("");
		f.setDescripcionTipoComu("");
		f.setCodigoTipoVinculo("");
		f.setDescripcionVinculo("");
		f.setRecomendado("");
		
		f.setCodTipoDocumento("");
		f.setSiglas("");
		f.setDescripcionTipoDocumento("");
		
		f.setAtributo1("");
		f.setAtributo2("");
		f.setSubTipoClasificacion("");
		f.setIdcodigoTipoSubCliente("");
		f.setIdcodigoTipoClasificacion("");		
		
		this.mostrarMaeCriteBusqueda = false; 
		this.mostrarMaeTipoClasiClien = false;
		this.mostrarMaeTipoDocum = false;
		this.mostrarMaeTipoEstatClien = false;
		this.mostrarMaeTipoBloqu = false;
		this.mostrarIncProduGaran = false;
		this.mostrarMaeTipoClien = false;
		this.mostrarMaeClasi = false;
		this.mostrarMaeSubtiClien = false;
		this.mostrarMaeEstatClien = false;	
		this.mostrarMaeEstadCivil = false;
		this.mostrarMaeTipoDirec = false;
		this.mostrarMaeTipoComun = false;
		this.mostrarMaeTipoVincu = false;
		this.mostrarMaeAccioProceBloqu = false;
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarListaBusqueda = false;
	}
	
	public void mostrarFiltros(ValueChangeEvent event) 
	{
		String valor = (String) event.getNewValue();
		this.listaBusqueda = new ArrayList();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		
		if (valor == null) 
		{
			this.mostrarMaeCriteBusqueda = false;
			this.mostrarMaeTipoClasiClien = false;
			this.mostrarMaeTipoDocum = false;
			this.mostrarMaeTipoEstatClien = false;
			this.mostrarMaeTipoBloqu = false;
			this.mostrarIncProduGaran = false;
			this.mostrarMaeTipoClien = false;
			this.mostrarMaeClasi = false;
			this.mostrarMaeSubtiClien = false;
			this.mostrarMaeEstatClien = false;
			this.mostrarMaeEstadCivil = false;
			this.mostrarMaeTipoDirec = false;
			this.mostrarMaeTipoComun = false;
			this.mostrarMaeTipoVincu = false;
			this.mostrarMaeAccioProceBloqu = false;
		} else 
		{
			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_CRITERIO_BUSQUEDA)) {
				this.mostrarMaeCriteBusqueda = true;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLASIFICACION)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = true;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DOCUMENTO)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = true;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_ESTATUS_CLIENTE)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = true;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_BLOQUEO)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = true;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_GARANTIAS)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = true;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLIENTE)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = true;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_CLASIFICACION)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = true;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_SUBTIPO_CLIENTE)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = true;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTATUS_CLIENTE)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = true;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTADO_CIVIL)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = true;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DIRECCION)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = true;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_COMUNICACION)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = true;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_VINCULO)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = true;
				this.mostrarMaeAccioProceBloqu = false;
			}

			if (valor.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ACCION_PROCESO_BLOQUEO)) {
				this.mostrarMaeCriteBusqueda = false;
				this.mostrarMaeTipoClasiClien = false;
				this.mostrarMaeTipoDocum = false;
				this.mostrarMaeTipoEstatClien = false;
				this.mostrarMaeTipoBloqu = false;
				this.mostrarIncProduGaran = false;
				this.mostrarMaeTipoClien = false;
				this.mostrarMaeClasi = false;
				this.mostrarMaeSubtiClien = false;
				this.mostrarMaeEstatClien = false;
				this.mostrarMaeEstadCivil = false;
				this.mostrarMaeTipoDirec = false;
				this.mostrarMaeTipoComun = false;
				this.mostrarMaeTipoVincu = false;
				this.mostrarMaeAccioProceBloqu = true;
			}
		}
	}
	
	public void loadTCSubTiposClientes(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ArrayList valores = new ArrayList();
		if(valor!=null)
		{
			valores.add(valor);
			this.maeCodigoTipoClienteListTC = ajax.getSubTiposClientesPorPaisTipoClientesOID(pais.getCodigoIdiomaIso(), valores);
		}else
		{
			this.maeCodigoTipoClienteListTC = null;			
		}
		
		
	}
		
	public void loadSubTiposClientes(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ArrayList valores = new ArrayList();
		if(valor!=null)
		{
			valores.add(valor);
			this.maeCodigoTipoClienteListC = ajax.getSubTiposClientesPorPaisTipoClientesOID(pais.getCodigoIdiomaIso(), valores);
			this.maeCodigoTipoClasificacionList = null;
		}else{
			this.maeCodigoTipoClienteListC = null;
			this.maeCodigoTipoClasificacionList = null;
		}
	}
	
	public void loadTiposClasificaciones(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		ArrayList valores = new ArrayList();
		
		if(valor!=null)
		{
			valores.add(valor);
			this.maeCodigoTipoClasificacionList = ajax.getTipoClasificacionMultipleByOidSubTipoCliente(valores, "T");
		}else
		{
			this.maeCodigoTipoClasificacionList = null;
		}
	}
	
	public void IdTiposClasificaciones(ValueChangeEvent event)
	{
		
	}
	
	public void loadAccionBloqueo(ValueChangeEvent event)
	{
		String valor = (String)event.getNewValue();
		AjaxService ajax =(AjaxService)getBean("ajaxService");
		
		if(valor!=null)
			this.maeAccionBloqueoAccionList = ajax.getDesAccionesByProcesosBloqueo(valor);
		else
			this.maeAccionBloqueoAccionList = null;		
	}

	public void mantenimientoBotonNuevo(ActionEvent event) 
	{
		try {
			MantenimientoMAEEntidadGenericaSearchForm f = (MantenimientoMAEEntidadGenericaSearchForm) this.formBusqueda;
			String codigoEntidad = f.getCodigoEntidad();
			this.accion = this.ACCION_NUEVO;

			if (StringUtils.isBlank(codigoEntidad)) {
				this.setMensajeAlertaDefault("'Entidad' es un campo requerido.");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);

			} else {
				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_CRITERIO_BUSQUEDA)) {
					mantenimientoBusqueda.setNombreEntidad(getNombreEntidad());
					mantenimientoBusqueda.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaCriterioBusquedaForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLASIFICACION)) {
					mantenimientoTipoClasificaciones.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoClasificaciones.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoClasificacionForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DOCUMENTO)) {
					mantenimientoTipoDocumento.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoDocumento.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoDocumentoForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_ESTATUS_CLIENTE)) {
					mantenimientoTipoEstatusCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoEstatusCliente.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoEstatusClienteForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_BLOQUEO)) {
					mantenimientoTipoBloqueo.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoBloqueo.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoBloqueoForm");
				}

//				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_GARANTIAS)) {
//				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLIENTE)) {
					mantenimientoTipoCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoCliente.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoClienteForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_CLASIFICACION)) {
					mantenimientoClasificaciones.setNombreEntidad(getNombreEntidad());
					mantenimientoClasificaciones.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaClasificacionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_SUBTIPO_CLIENTE)) {					
					mantenimientoSubTipoCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoSubTipoCliente.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaSubtipoClienteForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTATUS_CLIENTE)) {
					mantenimientoEstatusCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoEstatusCliente.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaEstatusClienteForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTADO_CIVIL)) {
					mantenimientoEstadoCivil.setNombreEntidad(getNombreEntidad());
					mantenimientoEstadoCivil.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaEstadoCivilForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DIRECCION)) {
					mantenimientoTipoDireccion.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoDireccion.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoDireccionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_COMUNICACION)) {
					mantenimientoTipoComunicacion.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoComunicacion.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoComunicacionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_VINCULO)) {
					mantenimientoTipoVinculo.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoVinculo.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoVinculoForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ACCION_PROCESO_BLOQUEO)) {
					mantenimientoProcesoBloqueo.setNombreEntidad(getNombreEntidad());
					mantenimientoProcesoBloqueo.inicializaPantallaMantenimiento();
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm");
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	

	public void mantenimientoBotonModificar(ActionEvent event) 
	{
		try {
			MantenimientoMAEEntidadGenericaSearchForm f = (MantenimientoMAEEntidadGenericaSearchForm) this.formBusqueda;
			String codigoEntidad = f.getCodigoEntidad();
			this.accion = this.ACCION_MODIFICAR;

			if (this.beanRegistroSeleccionado == null) {
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
			} else {

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_CRITERIO_BUSQUEDA)) {
					mantenimientoBusqueda.setAccion(this.accion);
					mantenimientoBusqueda.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoBusqueda.setNombreEntidad(getNombreEntidad());
					mantenimientoBusqueda.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaCriterioBusquedaForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLASIFICACION)) {
					mantenimientoTipoClasificaciones.setAccion(this.accion);
					mantenimientoTipoClasificaciones.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoClasificaciones.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoClasificaciones.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoClasificacionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DOCUMENTO)) {
					mantenimientoTipoDocumento.setAccion(this.accion);
					mantenimientoTipoDocumento.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoDocumento.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoDocumento.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoDocumentoForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_ESTATUS_CLIENTE)) {
					mantenimientoTipoEstatusCliente.setAccion(this.accion);
					mantenimientoTipoEstatusCliente.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoEstatusCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoEstatusCliente.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoEstatusClienteForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_BLOQUEO)) {
					mantenimientoTipoBloqueo.setAccion(this.accion);
					mantenimientoTipoBloqueo.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoBloqueo.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoBloqueo.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoBloqueoForm");
				}

				if (codigoEntidad
						.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_GARANTIAS)) {

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLIENTE)) {					
					mantenimientoTipoCliente.setAccion(this.accion);
					mantenimientoTipoCliente.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoCliente.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoClienteForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_CLASIFICACION)) {
					mantenimientoClasificaciones.setAccion(this.accion);
					mantenimientoClasificaciones.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoClasificaciones.setNombreEntidad(getNombreEntidad());
					mantenimientoClasificaciones.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaClasificacionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_SUBTIPO_CLIENTE)) {
					mantenimientoSubTipoCliente.setAccion(this.accion);
					mantenimientoSubTipoCliente.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoSubTipoCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoSubTipoCliente.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaSubtipoClienteForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTATUS_CLIENTE)) {
					mantenimientoEstatusCliente.setAccion(this.accion);
					mantenimientoEstatusCliente.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoEstatusCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoEstatusCliente.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaEstatusClienteForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTADO_CIVIL)) {
					mantenimientoEstadoCivil.setAccion(this.accion);
					mantenimientoEstadoCivil.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoEstadoCivil.setNombreEntidad(getNombreEntidad());
					mantenimientoEstadoCivil.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaEstadoCivilForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DIRECCION)) {
					mantenimientoTipoDireccion.setAccion(this.accion);
					mantenimientoTipoDireccion.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoDireccion.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoDireccion.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoDireccionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_COMUNICACION)) {
					mantenimientoTipoComunicacion.setAccion(this.accion);
					mantenimientoTipoComunicacion.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoComunicacion.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoComunicacion.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoComunicacionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_VINCULO)) {
					mantenimientoTipoVinculo.setAccion(this.accion);
					mantenimientoTipoVinculo.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoVinculo.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoVinculo.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoVinculoForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ACCION_PROCESO_BLOQUEO)) {
					mantenimientoProcesoBloqueo.setAccion(this.accion);
					mantenimientoProcesoBloqueo.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoProcesoBloqueo.setNombreEntidad(getNombreEntidad());
					mantenimientoProcesoBloqueo.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm");
				}
			}

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
			System.out.println(e);
		}
	}
	
	
	public void mantenimientoBotonConsultar(ActionEvent event) 
	{
		try {
			MantenimientoMAEEntidadGenericaSearchForm f = (MantenimientoMAEEntidadGenericaSearchForm) this.formBusqueda;
			String codigoEntidad = f.getCodigoEntidad();
			this.accion = this.ACCION_CONSULTAR;

			if (this.beanRegistroSeleccionado == null) {
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.select.item"));
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
			} else {
				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_CRITERIO_BUSQUEDA)) {
					mantenimientoBusqueda.setAccion(this.accion);
					mantenimientoBusqueda.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoBusqueda.setNombreEntidad(getNombreEntidad());
					mantenimientoBusqueda.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaCriterioBusquedaForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLASIFICACION)) {
					mantenimientoTipoClasificaciones.setAccion(this.accion);
					mantenimientoTipoClasificaciones.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoClasificaciones.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoClasificaciones.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoClasificacionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DOCUMENTO)) {
					mantenimientoTipoDocumento.setAccion(this.accion);
					mantenimientoTipoDocumento.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoDocumento.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoDocumento.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoDocumentoForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_ESTATUS_CLIENTE)) {
					mantenimientoTipoEstatusCliente.setAccion(this.accion);
					mantenimientoTipoEstatusCliente.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoEstatusCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoEstatusCliente.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoEstatusClienteForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_BLOQUEO)) {					
					mantenimientoTipoBloqueo.setAccion(this.accion);
					mantenimientoTipoBloqueo.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoBloqueo.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoBloqueo.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoBloqueoForm");
				}

//				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_GARANTIAS)) {
//
//				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_CLIENTE)) {					
					mantenimientoTipoCliente.setAccion(this.accion);
					mantenimientoTipoCliente.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoCliente.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoClienteForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_CLASIFICACION)) {
					mantenimientoClasificaciones.setAccion(this.accion);
					mantenimientoClasificaciones.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoClasificaciones.setNombreEntidad(getNombreEntidad());
					mantenimientoClasificaciones.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaClasificacionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_SUBTIPO_CLIENTE)) {
					mantenimientoSubTipoCliente.setAccion(this.accion);
					mantenimientoSubTipoCliente.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoSubTipoCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoSubTipoCliente.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaSubtipoClienteForm");

				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTATUS_CLIENTE)) {
					mantenimientoEstatusCliente.setAccion(this.accion);
					mantenimientoEstatusCliente.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoEstatusCliente.setNombreEntidad(getNombreEntidad());
					mantenimientoEstatusCliente.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaEstatusClienteForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ESTADO_CIVIL)) {
					mantenimientoEstadoCivil.setAccion(this.accion);
					mantenimientoEstadoCivil.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoEstadoCivil.setNombreEntidad(getNombreEntidad());
					mantenimientoEstadoCivil.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaEstadoCivilForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_DIRECCION)) {
					mantenimientoTipoDireccion.setAccion(this.accion);
					mantenimientoTipoDireccion.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoDireccion.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoDireccion.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoDireccionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_COMUNICACION)) {
					mantenimientoTipoComunicacion.setAccion(this.accion);
					mantenimientoTipoComunicacion.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoComunicacion.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoComunicacion.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoComunicacionForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_TIPO_VINCULO)) {
					mantenimientoTipoVinculo.setAccion(this.accion);
					mantenimientoTipoVinculo.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoTipoVinculo.setNombreEntidad(getNombreEntidad());
					mantenimientoTipoVinculo.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaTipoVinculoForm");
				}

				if (codigoEntidad.equals(Constants.MAE_CODIGO_ENTIDAD_GENERICA_ACCION_PROCESO_BLOQUEO)) {
					mantenimientoProcesoBloqueo.setAccion(this.accion);
					mantenimientoProcesoBloqueo.setBeanRegistroSeleccionado(this.beanRegistroSeleccionado);
					mantenimientoProcesoBloqueo.setNombreEntidad(getNombreEntidad());
					mantenimientoProcesoBloqueo.inicializaPantallaMantenimiento();					
					this.redireccionarPagina("mantenimientoMAEEntidadGenericaAccionesProcesoBloqueoForm");
				}
			}

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	private String getNombreEntidad() 
	{
		MantenimientoMAEEntidadGenericaSearchForm f = (MantenimientoMAEEntidadGenericaSearchForm) this.formBusqueda;
		String codigoEntidad = f.getCodigoEntidad();
		String nombre = "";

		List entidades = this.maeCodigoEntidadGenericaList;

		if (entidades != null) {
			for (int i = 0; i < entidades.size(); i++) {
				if (StringUtils.equals(((Base) entidades.get(i)).getCodigo(), codigoEntidad)) {
					nombre = ((Base) entidades.get(i)).getDescripcion();
					break;
				}
			}
		}
		return nombre;
	}
	
	@Override
	public String setValidarFind() 
	{
		String mensaje = null;
		MantenimientoMAEEntidadGenericaSearchForm f = (MantenimientoMAEEntidadGenericaSearchForm) this.formBusqueda;
		if(StringUtils.isBlank(f.getCodigoEntidad()))
			return mensaje = "'Entidad' es un campo requerido.";
		
		return mensaje;	
	}
	
	public List getMaeCodigoEntidadGenericaList() {
		return maeCodigoEntidadGenericaList;
	}

	public void setMaeCodigoEntidadGenericaList(List maeCodigoEntidadGenericaList) {
		this.maeCodigoEntidadGenericaList = maeCodigoEntidadGenericaList;
	}

	public List getMaeEntidadGenericaList() {
		return maeEntidadGenericaList;
	}

	public void setMaeEntidadGenericaList(List maeEntidadGenericaList) {
		this.maeEntidadGenericaList = maeEntidadGenericaList;
	}

	public List getMaeCodigoTipoEstatusList() {
		return maeCodigoTipoEstatusList;
	}

	public void setMaeCodigoTipoEstatusList(List maeCodigoTipoEstatusList) {
		this.maeCodigoTipoEstatusList = maeCodigoTipoEstatusList;
	}

	public List getMaeCodigoTipoClienteList() {
		return maeCodigoTipoClienteList;
	}

	public void setMaeCodigoTipoClienteList(List maeCodigoTipoClienteList) {
		this.maeCodigoTipoClienteList = maeCodigoTipoClienteList;
	}

	public LabelValue[] getMaeCodigoTipoClienteListC() {
		return maeCodigoTipoClienteListC;
	}

	public void setMaeCodigoTipoClienteListC(LabelValue[] maeCodigoTipoClienteListC) {
		this.maeCodigoTipoClienteListC = maeCodigoTipoClienteListC;
	}

	public LabelValue[] getMaeCodigoTipoClienteListTC() {
		return maeCodigoTipoClienteListTC;
	}

	public void setMaeCodigoTipoClienteListTC(
			LabelValue[] maeCodigoTipoClienteListTC) {
		this.maeCodigoTipoClienteListTC = maeCodigoTipoClienteListTC;
	}

	public List getMaeCodigoSubTipoClienteList() {
		return maeCodigoSubTipoClienteList;
	}

	public void setMaeCodigoSubTipoClienteList(List maeCodigoSubTipoClienteList) {
		this.maeCodigoSubTipoClienteList = maeCodigoSubTipoClienteList;
	}

	public LabelValue[] getMaeCodigoTipoClasificacionList() {
		return maeCodigoTipoClasificacionList;
	}

	public void setMaeCodigoTipoClasificacionList(LabelValue[] maeCodigoTipoClasificacionList) {
		this.maeCodigoTipoClasificacionList = maeCodigoTipoClasificacionList;
	}

	public List getMaeMarcasList() {
		return maeMarcasList;
	}

	public void setMaeMarcasList(List maeMarcasList) {
		this.maeMarcasList = maeMarcasList;
	}

	public List getMaeCriteriosList() {
		return maeCriteriosList;
	}

	public void setMaeCriteriosList(List maeCriteriosList) {
		this.maeCriteriosList = maeCriteriosList;
	}

	public List getMaeTipoBloqueoAccionList() {
		return maeTipoBloqueoAccionList;
	}

	public void setMaeTipoBloqueoAccionList(List maeTipoBloqueoAccionList) {
		this.maeTipoBloqueoAccionList = maeTipoBloqueoAccionList;
	}

	public List getMaeProcesoBloqueoAccionList() {
		return maeProcesoBloqueoAccionList;
	}

	public void setMaeProcesoBloqueoAccionList(List maeProcesoBloqueoAccionList) {
		this.maeProcesoBloqueoAccionList = maeProcesoBloqueoAccionList;
	}

	public LabelValue[] getMaeAccionBloqueoAccionList() {
		return maeAccionBloqueoAccionList;
	}

	public void setMaeAccionBloqueoAccionList(LabelValue[] maeAccionBloqueoAccionList) {
		this.maeAccionBloqueoAccionList = maeAccionBloqueoAccionList;
	}

	public String getMaeCodigoInterfazInc() {
		return maeCodigoInterfazInc;
	}

	public void setMaeCodigoInterfazInc(String maeCodigoInterfazInc) {
		this.maeCodigoInterfazInc = maeCodigoInterfazInc;
	}

	public String getMaeCodigoInterfazMae() {
		return maeCodigoInterfazMae;
	}

	public void setMaeCodigoInterfazMae(String maeCodigoInterfazMae) {
		this.maeCodigoInterfazMae = maeCodigoInterfazMae;
	}

	public String getNumeroUno() {
		return numeroUno;
	}

	public void setNumeroUno(String numeroUno) {
		this.numeroUno = numeroUno;
	}

	public String getNumeroCero() {
		return numeroCero;
	}

	public void setNumeroCero(String numeroCero) {
		this.numeroCero = numeroCero;
	}

	public Boolean getMostrarMaeCriteBusqueda() {
		return mostrarMaeCriteBusqueda;
	}

	public void setMostrarMaeCriteBusqueda(Boolean mostrarMaeCriteBusqueda) {
		this.mostrarMaeCriteBusqueda = mostrarMaeCriteBusqueda;
	}

	public Boolean getMostrarMaeTipoClasiClien() {
		return mostrarMaeTipoClasiClien;
	}

	public void setMostrarMaeTipoClasiClien(Boolean mostrarMaeTipoClasiClien) {
		this.mostrarMaeTipoClasiClien = mostrarMaeTipoClasiClien;
	}

	public Boolean getMostrarMaeTipoDocum() {
		return mostrarMaeTipoDocum;
	}

	public void setMostrarMaeTipoDocum(Boolean mostrarMaeTipoDocum) {
		this.mostrarMaeTipoDocum = mostrarMaeTipoDocum;
	}

	public Boolean getMostrarMaeTipoEstatClien() {
		return mostrarMaeTipoEstatClien;
	}

	public void setMostrarMaeTipoEstatClien(Boolean mostrarMaeTipoEstatClien) {
		this.mostrarMaeTipoEstatClien = mostrarMaeTipoEstatClien;
	}

	public Boolean getMostrarTipoBloqu() {
		return mostrarMaeTipoBloqu;
	}

	public void setMostrarTipoBloqu(Boolean mostrarMaeTipoBloqu) {
		this.mostrarMaeTipoBloqu = mostrarMaeTipoBloqu;
	}

	public Boolean getMostrarIncProduGaran() {
		return mostrarIncProduGaran;
	}

	public void setMostrarIncProduGaran(Boolean mostrarIncProduGaran) {
		this.mostrarIncProduGaran = mostrarIncProduGaran;
	}

	public Boolean getMostrarMaeTipoClien() {
		return mostrarMaeTipoClien;
	}

	public void setMostrarMaeTipoClien(Boolean mostrarMaeTipoClien) {
		this.mostrarMaeTipoClien = mostrarMaeTipoClien;
	}

	public Boolean getMostrarMaeClasi() {
		return mostrarMaeClasi;
	}

	public void setMostrarMaeClasi(Boolean mostrarMaeClasi) {
		this.mostrarMaeClasi = mostrarMaeClasi;
	}

	public Boolean getMostrarMaeSubtiClien() {
		return mostrarMaeSubtiClien;
	}

	public void setMostrarMaeSubtiClien(Boolean mostrarMaeSubtiClien) {
		this.mostrarMaeSubtiClien = mostrarMaeSubtiClien;
	}

	public Boolean getMostrarMaeEstatClien() {
		return mostrarMaeEstatClien;
	}

	public void setMostrarMaeEstatClien(Boolean mostrarMaeEstatClien) {
		this.mostrarMaeEstatClien = mostrarMaeEstatClien;
	}

	public Boolean getMostrarMaeTipoBloqu() {
		return mostrarMaeTipoBloqu;
	}

	public void setMostrarMaeTipoBloqu(Boolean mostrarMaeTipoBloqu) {
		this.mostrarMaeTipoBloqu = mostrarMaeTipoBloqu;
	}

	public Boolean getMostrarMaeEstadCivil() {
		return mostrarMaeEstadCivil;
	}

	public void setMostrarMaeEstadCivil(Boolean mostrarMaeEstadCivil) {
		this.mostrarMaeEstadCivil = mostrarMaeEstadCivil;
	}

	public Boolean getMostrarMaeTipoDirec() {
		return mostrarMaeTipoDirec;
	}

	public void setMostrarMaeTipoDirec(Boolean mostrarMaeTipoDirec) {
		this.mostrarMaeTipoDirec = mostrarMaeTipoDirec;
	}

	public Boolean getMostrarMaeTipoComun() {
		return mostrarMaeTipoComun;
	}

	public void setMostrarMaeTipoComun(Boolean mostrarMaeTipoComun) {
		this.mostrarMaeTipoComun = mostrarMaeTipoComun;
	}

	public Boolean getMostrarMaeTipoVincu() {
		return mostrarMaeTipoVincu;
	}

	public void setMostrarMaeTipoVincu(Boolean mostrarMaeTipoVincu) {
		this.mostrarMaeTipoVincu = mostrarMaeTipoVincu;
	}

	public Boolean getMostrarMaeAccioProceBloqu() {
		return mostrarMaeAccioProceBloqu;
	}

	public void setMostrarMaeAccioProceBloqu(Boolean mostrarMaeAccioProceBloqu) {
		this.mostrarMaeAccioProceBloqu = mostrarMaeAccioProceBloqu;
	}

	public MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoAction getMantenimientoProcesoBloqueo() {
		return mantenimientoProcesoBloqueo;
	}

	public void setMantenimientoProcesoBloqueo(
			MantenimientoMAEEntidadGenericaAccionesProcesoBloqueoAction mantenimientoProcesoBloqueo) {
		this.mantenimientoProcesoBloqueo = mantenimientoProcesoBloqueo;
	}

	public MantenimientoMAEEntidadGenericaClasificacionAction getMantenimientoClasificaciones() {
		return mantenimientoClasificaciones;
	}

	public void setMantenimientoClasificaciones(
			MantenimientoMAEEntidadGenericaClasificacionAction mantenimientoClasificaciones) {
		this.mantenimientoClasificaciones = mantenimientoClasificaciones;
	}

	public MantenimientoMAEEntidadGenericaCriterioBusquedaAction getMantenimientoBusqueda() {
		return mantenimientoBusqueda;
	}

	public void setMantenimientoBusqueda(
			MantenimientoMAEEntidadGenericaCriterioBusquedaAction mantenimientoBusqueda) {
		this.mantenimientoBusqueda = mantenimientoBusqueda;
	}

	public MantenimientoMAEEntidadGenericaTipoClasificacionAction getMantenimientoTipoClasificaciones() {
		return mantenimientoTipoClasificaciones;
	}

	public void setMantenimientoTipoClasificaciones(
			MantenimientoMAEEntidadGenericaTipoClasificacionAction mantenimientoTipoClasificaciones) {
		this.mantenimientoTipoClasificaciones = mantenimientoTipoClasificaciones;
	}

	public MantenimientoMAEEntidadGenericaTipoComunicacionAction getMantenimientoTipoComunicacion() {
		return mantenimientoTipoComunicacion;
	}

	public void setMantenimientoTipoComunicacion(
			MantenimientoMAEEntidadGenericaTipoComunicacionAction mantenimientoTipoComunicacion) {
		this.mantenimientoTipoComunicacion = mantenimientoTipoComunicacion;
	}

	public MantenimientoMAEEntidadGenericaTipoDireccionAction getMantenimientoTipoDireccion() {
		return mantenimientoTipoDireccion;
	}

	public void setMantenimientoTipoDireccion(
			MantenimientoMAEEntidadGenericaTipoDireccionAction mantenimientoTipoDireccion) {
		this.mantenimientoTipoDireccion = mantenimientoTipoDireccion;
	}

	public MantenimientoMAEEntidadGenericaTipoDocumentoAction getMantenimientoTipoDocumento() {
		return mantenimientoTipoDocumento;
	}

	public void setMantenimientoTipoDocumento(
			MantenimientoMAEEntidadGenericaTipoDocumentoAction mantenimientoTipoDocumento) {
		this.mantenimientoTipoDocumento = mantenimientoTipoDocumento;
	}

	public MantenimientoMAEEntidadGenericaEstadoCivilAction getMantenimientoEstadoCivil() {
		return mantenimientoEstadoCivil;
	}

	public void setMantenimientoEstadoCivil(
			MantenimientoMAEEntidadGenericaEstadoCivilAction mantenimientoEstadoCivil) {
		this.mantenimientoEstadoCivil = mantenimientoEstadoCivil;
	}

	public MantenimientoMAEEntidadGenericaTipoEstatusClienteAction getMantenimientoTipoEstatusCliente() {
		return mantenimientoTipoEstatusCliente;
	}

	public void setMantenimientoTipoEstatusCliente(
			MantenimientoMAEEntidadGenericaTipoEstatusClienteAction mantenimientoTipoEstatusCliente) {
		this.mantenimientoTipoEstatusCliente = mantenimientoTipoEstatusCliente;
	}

	public MantenimientoMAEEntidadGenericaTipoVinculoAction getMantenimientoTipoVinculo() {
		return mantenimientoTipoVinculo;
	}

	public void setMantenimientoTipoVinculo(
			MantenimientoMAEEntidadGenericaTipoVinculoAction mantenimientoTipoVinculo) {
		this.mantenimientoTipoVinculo = mantenimientoTipoVinculo;
	}

	public MantenimientoMAEEntidadGenericaTipoBloqueoAction getMantenimientoTipoBloqueo() {
		return mantenimientoTipoBloqueo;
	}

	public void setMantenimientoTipoBloqueo(
			MantenimientoMAEEntidadGenericaTipoBloqueoAction mantenimientoTipoBloqueo) {
		this.mantenimientoTipoBloqueo = mantenimientoTipoBloqueo;
	}

	public MantenimientoMAEEntidadGenericaTipoClienteAction getMantenimientoTipoCliente() {
		return mantenimientoTipoCliente;
	}

	public void setMantenimientoTipoCliente(
			MantenimientoMAEEntidadGenericaTipoClienteAction mantenimientoTipoCliente) {
		this.mantenimientoTipoCliente = mantenimientoTipoCliente;
	}

	public MantenimientoMAEEntidadGenericaSubtipoClienteAction getMantenimientoSubTipoCliente() {
		return mantenimientoSubTipoCliente;
	}

	public void setMantenimientoSubTipoCliente(
			MantenimientoMAEEntidadGenericaSubtipoClienteAction mantenimientoSubTipoCliente) {
		this.mantenimientoSubTipoCliente = mantenimientoSubTipoCliente;
	}

	public MantenimientoMAEEntidadGenericaEstatusClienteAction getMantenimientoEstatusCliente() {
		return mantenimientoEstatusCliente;
	}

	public void setMantenimientoEstatusCliente(
			MantenimientoMAEEntidadGenericaEstatusClienteAction mantenimientoEstatusCliente) {
		this.mantenimientoEstatusCliente = mantenimientoEstatusCliente;
	}
	
}