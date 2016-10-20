package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.zon.model.ConsultoraATrasvasar;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.DBFUtil;
import biz.belcorp.ssicc.service.util.ExcelUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.zon.form.ArchivoSubidoForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONActualizarUnidadesAdministrativasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProcesoZONActualizarUnidadesAdministrativasAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = -7404610534614027459L;

	private List siccMarcaList;
	private List siccCanalList;
	private List siccPeriodoCorporativoList;
	private List siccRegionList;
	private List zonCampoList;
	private List siccZonaList;
	private DataTableModel listaArchivoModel;
	private String attachment = "";
	private List archivosSeleccionados;
	private List listaArchivos;
	private Boolean reporteGenerado;
	
	private LabelValue[] siccSeccionList = {};
	private LabelValue[] siccTerritoriosList = {};

	private DataTableModel dtTrasvasa;
	private List listaComboSeccion;
	private List listaComboTerritorio;
	private String mensajeAlert;
	
	private List zonaRegionList;
	private boolean inhabilitaZonas;
	private List zonUnidadAdminCrearList;
	private DataTableModel zonUnidadAdminCrearModel;
	private boolean showZonasxPais;

	@ManagedProperty(value = "#{reporteZONValidacionActUniAdmAction}")
	private ReporteZONValidacionActUniAdmAction reporteZonValidacionActUniAdm;
	
	@ManagedProperty(value = "#{reporteZONErroresCargaDatosActUniAdmAction}")
	private ReporteZONErroresCargaDatosActUniAdmAction  reporteZONErroresCargaDatosActUniAdm;
	
	@ManagedProperty(value = "#{reporteZONErroresEliminacionActUniAdmAction}")
	private ReporteZONErroresEliminacionActUniAdmAction  reporteZONErroresEliminacionActUniAdm;

	
	//Error de carga de Datos -Reporte
	public void generarReporteCargaDatos(ActionEvent event) {		
		try {
			ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
			this.reporteGenerado = false;

			this.reporteZONErroresCargaDatosActUniAdm.setFormatoExportacion("PDF");		
			this.reporteZONErroresCargaDatosActUniAdm.getFormReporte().setFormatoExportacion("PDF");
			f.setFlagReporteVisto("S");
			this.reporteZONErroresCargaDatosActUniAdm.executeReporte(event);
			this.reporteGenerado = true;
			this.redireccionarPagina("reporteZONErroresCargaDatosActUniAdmForm");
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	//Reporte Errores de Eliminacion
	public void generarReporteErrorEliminacion(ActionEvent event) {		
		try {
			ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
			this.reporteGenerado = false;

			this.reporteZONErroresEliminacionActUniAdm.setFormatoExportacion("PDF");		
			this.reporteZONErroresEliminacionActUniAdm.getFormReporte().setFormatoExportacion("PDF");
			f.setFlagReporteVisto("S");
			this.reporteZONErroresEliminacionActUniAdm.executeReporte(event);
			this.reporteGenerado = true;
			this.redireccionarPagina("reporteZONErroresEliminacionActUniAdmForm");
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}		
	}
	
	// Reporte de Validacion
	public void generarReporteValidacion(ActionEvent e) {
		if (log.isDebugEnabled()) {
			log.debug("ConsultaFLXConsultoraAction - ejecutarReporte()");
		}
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		try {
			this.reporteGenerado = false;

			this.reporteZonValidacionActUniAdm.setFormatoExportacion("PDF");		
			this.reporteZonValidacionActUniAdm.getFormReporte().setFormatoExportacion("PDF");
			f.setFlagReporteVisto("S");
			this.reporteZonValidacionActUniAdm.executeReporte(e);
			this.reporteGenerado = true;
			this.redireccionarPagina("reporteZONValidacionActUniAdmForm");
		} catch (Exception e2) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e2));
		}

	}
	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoZONActualizarUnidadesAdministrativasForm p = new ProcesoZONActualizarUnidadesAdministrativasForm();
		return p;
	}

	/**
	 * @param evt
	 */
	public void aprobarReactivaciones(ActionEvent evt) {
		try {
			ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
			f.setOperacion("AprobarReactivaciones");
			this.ejecutarProcesar();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * 
	 */
	public Boolean validarExecuteProceso() {
		if (this.reporteGenerado) {
			ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
			if ((f.getOperacion().equalsIgnoreCase("AprobarCreaciones") && f
					.getCantidadRegistrosAlta().equalsIgnoreCase("0"))
					|| (f.getOperacion().equalsIgnoreCase(
							"AprobarModificaciones") && f
							.getCantidadRegistrosModificacion()
							.equalsIgnoreCase("0"))
					|| (f.getOperacion().equalsIgnoreCase(
							"AprobarReactivaciones") && f
							.getCantidadRegistrosReactivacion()
							.equalsIgnoreCase("0"))
					|| (f.getOperacion().equalsIgnoreCase("AprobarTrasvases") && f
							.getCantidadRegistrosTrasvase().equalsIgnoreCase(
									"0"))
					|| (f.getOperacion().equalsIgnoreCase(
							"AprobarEliminaciones") && f
							.getCantidadRegistrosBaja().equalsIgnoreCase("0"))) {

				this.addError("Error : ", this.getResourceMessage("procesoZONActualizarUnidadesAdministrativasForm.msg.sinRegistrosProcesar"));
				if (StringUtils.equals(f.getOperacion(), "AprobarReactivaciones")) {
					f.setFlagHabilitaAprobarCreaciones(true);
					f.setFlagHabilitaAprobarReactivaciones(false);
				} else if (StringUtils.equals(f.getOperacion(), "AprobarCreaciones")) {
					f.setFlagHabilitaAprobarCreaciones(false);
					f.setFlagHabilitaAprobarModificaciones(true);
				} else if (StringUtils.equals(f.getOperacion(), "AprobarModificaciones")) {
					f.setFlagHabilitaAprobarTrasvases(true);
					f.setFlagHabilitaAprobarModificaciones(false);
				} else if (StringUtils.equals(f.getOperacion(), "AprobarTrasvases")) {
					f.setFlagHabilitaAprobarTrasvases(false);
					f.setFlagHabilitaAprobarEliminaciones(true);
				} else if (StringUtils.equals(f.getOperacion(), "AprobarEliminaciones")) {
					f.setFlagHabilitaAprobarEliminaciones(true);
				}

				return false;
			}
		} else {
			this.addWarn("Error : ",this.getResourceMessage("procesoZONActualizarUnidadesGeograficasForm.msg.Aprobar.reporte"));
			return false;
		}
		return true;
	}

	/**
	 * 
	 */
	public void ejecutarProcesar() {
		try {
			boolean validar = this.validarExecuteProceso();
			if (!validar) 
				return;			
			ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
			Map<String, Object> params = null;
			params = BeanUtils.describe(f);
			this.executeProcess(params);
			this.addInfo("Información", this.getResourceMessage("proceso.ok"));
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProceso(javax.faces.event.ActionEvent)
	 */
	public void executeProceso(ActionEvent evt) {
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		try {			
			f.setOperacion("AprobarCreaciones");
			this.ejecutarProcesar();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	public void executeProcesoModificaciones(ActionEvent evt) {
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		try {
			// this.validarExecuteProceso();
			f.setOperacion("AprobarModificaciones");
			this.ejecutarProcesar();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	public void executeProcesoTrasvases(ActionEvent evt) {
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		try {			
			f.setOperacion("AprobarTrasvases");
			this.ejecutarProcesar();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		f.setFlagHayErroresEliminacion(false);

		log.debug("Ejecutando la operacion..." + params.get("operacion"));
		ProcesoZONActualizarUnidadesAdministrativasService service = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");
		service.ejecutarActualizacionUnidadesAdministrativas(params);

		String codRetorno = (String) params.get("codRetorno");
		// String operacion = (String)params.get("operacion");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String operacion = externalContext.getRequestParameterMap().get("parametroAccion");
		params.put("operacion", operacion);
		f.setOperacion(operacion);

		if (StringUtils.isNotBlank(codRetorno) && !StringUtils.equals(Constants.NUMERO_CERO, codRetorno)) {
			String msgRetorno = (String) params.get("msgRetorno");
			String keyMensaje = "";
			if (operacion.equals("AprobarCreaciones")) {
				// No reporta ningun codigo de error
			} else if (operacion.equals("AprobarModificaciones")) {
				if (codRetorno.equals("1"))
					keyMensaje = "procesoZONActualizarUnidadesAdministrativasForm.errorAprobModific1";
			} else if (operacion.equals("AprobarTrasvases")) {
				if (codRetorno.equals("1"))
					keyMensaje = "procesoZONActualizarUnidadesAdministrativasForm.errorAprobTrasvas1";				
			}

			String texto = this.getResourceMessage(keyMensaje); 
			params.put("msgRetorno", texto);

			/* INI JJ PER-SiCC-2012-0582 */
			if (StringUtils.equals(operacion, "AprobarReactivaciones")) {
				f.setFlagHabilitaAprobarCreaciones(false);
				f.setFlagHabilitaAprobarModificaciones(false);
				f.setFlagHabilitaAprobarTrasvases(false);
				f.setFlagHabilitaAprobarEliminaciones(false);
				f.setFlagHayErroresEliminacion(false);
				f.setFlagHabilitaAprobarReactivaciones(true);
			} else if (StringUtils.equals(operacion, "AprobarCreaciones")) {
				f.setFlagHabilitaAprobarCreaciones(true);
				f.setFlagHabilitaAprobarModificaciones(false);
				f.setFlagHabilitaAprobarTrasvases(false);
				f.setFlagHabilitaAprobarEliminaciones(false);
				f.setFlagHayErroresEliminacion(false);
				f.setFlagHabilitaAprobarReactivaciones(false);
			} else if (StringUtils.equals(operacion, "AprobarModificaciones")) {
				f.setFlagHabilitaAprobarCreaciones(false);
				f.setFlagHabilitaAprobarModificaciones(true);
				f.setFlagHabilitaAprobarTrasvases(false);
				f.setFlagHabilitaAprobarEliminaciones(false);
				f.setFlagHayErroresEliminacion(false);
				f.setFlagHabilitaAprobarReactivaciones(false);
			} else if (StringUtils.equals(operacion, "AprobarTrasvases")) {
				f.setFlagHabilitaAprobarCreaciones(false);
				f.setFlagHabilitaAprobarModificaciones(false);
				f.setFlagHabilitaAprobarTrasvases(true);
				f.setFlagHabilitaAprobarEliminaciones(false);
				f.setFlagHayErroresEliminacion(false);
				f.setFlagHabilitaAprobarReactivaciones(false);
			}
			/* FIN JJ PER-SiCC-2012-0582 */
		} else {
			/* INI JJ PER-SiCC-2012-0582 */
			if (StringUtils.equals(operacion, "AprobarReactivaciones")) {
				f.setFlagHabilitaAprobarCreaciones(true);
				f.setFlagHabilitaAprobarModificaciones(false);
				f.setFlagHabilitaAprobarTrasvases(false);
				f.setFlagHabilitaAprobarEliminaciones(false);
				f.setFlagHayErroresEliminacion(false);
				f.setFlagHabilitaAprobarReactivaciones(false);
			} else if (StringUtils.equals(operacion, "AprobarCreaciones")) {
				f.setFlagHabilitaAprobarCreaciones(false);
				f.setFlagHabilitaAprobarModificaciones(true);
				f.setFlagHabilitaAprobarTrasvases(false);
				f.setFlagHabilitaAprobarEliminaciones(false);
				f.setFlagHayErroresEliminacion(false);
				f.setFlagHabilitaAprobarReactivaciones(false);
			} else if (StringUtils.equals(operacion, "AprobarModificaciones")) {
				f.setFlagHabilitaAprobarCreaciones(false);
				f.setFlagHabilitaAprobarModificaciones(false);
				f.setFlagHabilitaAprobarTrasvases(true);
				f.setFlagHabilitaAprobarEliminaciones(false);
				f.setFlagHayErroresEliminacion(false);
				f.setFlagHabilitaAprobarReactivaciones(false);
			} else if (StringUtils.equals(operacion, "AprobarTrasvases")) {
				f.setFlagHabilitaAprobarCreaciones(false);
				f.setFlagHabilitaAprobarModificaciones(false);
				f.setFlagHabilitaAprobarTrasvases(false);
				f.setFlagHabilitaAprobarEliminaciones(true);
				f.setFlagHayErroresEliminacion(false);
				f.setFlagHabilitaAprobarReactivaciones(false);
			}
			/* FIN JJ PER-SiCC-2012-0582 */
		}
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		this.reporteGenerado = false;

		this.mostrarBotonExecute = false;
		this.mensajeAlert="";

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		if (f.getCodigoCanal() == null)
			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		if (f.getCodigoMarca() == null)
			f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		if (f.getPeriodoCorporativo() == null)
			f.setPeriodoCorporativo(service.getPeriodoDefaultByPaisCanal(
					pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));

		ProcesoZONActualizarUnidadesGeograficasService service2 = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(service2.obtenerPathUpload(pais.getCodigo()));

		this.siccMarcaList = reporteService.getMarcas(); // request.getSession()).getIdioma().getCodigoISO()
		this.siccCanalList = reporteService.getCanalesByCodigoISO(usuario
				.getIdioma().getCodigoISO());
		this.siccPeriodoCorporativoList = reporteService
				.getPeriodosCorporativosPorTipo(Constants.TIPO_PERIOODO_CORPORATIVO_CAMPANIA);

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		criteriaOperacion.put("codigoMarca", f.getCodigoMarca());
		criteriaOperacion.put("codigoCanal", f.getCodigoCanal());

		this.siccRegionList = reporteService.getListaGenerico("getRegionesByPais", criteriaOperacion);
		this.zonUnidadAdminCrearList= new ArrayList();
		this.zonUnidadAdminCrearModel = new DataTableModel();
		
		ParametroPais paraPais = new ParametroPais();
		paraPais.setCodigoPais(pais.getCodigo());
		paraPais.setCodigoSistema(Constants.ZON_CODIGO_SISTEMA);
		paraPais.setNombreParametro(Constants.ZON_UA_REGION_ZONA);		
		List listaParametro=serviceGen.getParametrosPais(paraPais);				
		if(listaParametro.isEmpty())
			this.showZonasxPais=false;
		else{
			String codigoPara=((ParametroPais)(listaParametro.get(0))).getValorParametro();
			if(StringUtils.equals(codigoPara, Constants.NRO_UNO))
				this.showZonasxPais=true;
			else
				this.showZonasxPais=false;
		}
	}

	/**
	 * Accion que realiza el trasvase de las consultoras cuyos territorios seran
	 * eliminados
	 * 
	 * 
	 */
	public void trasvasarConsultoras(ActionEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'trasvasarConsultoras' method");
		}
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		ProcesoZONActualizarUnidadesAdministrativasService service = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map params = new HashMap();
		params.put("codigoUsuario", usuario.getLogin());
		params.put("operacion", "AprobarEliminaciones");

		try {
			service.grabarNuevosTerritorioConsultoraATrasvasar(f.getConsultorasATrasvasar(), params);
		} catch (Exception ex) {
			params.put("codRetorno", "4");
			params.put("msgRetorno", ex.getMessage());
		}

		String codRetorno = (String) params.get("codRetorno");
		String vista = "procesoZONActualizarUnidadAdministrativaForm";

		if (StringUtils.isNotBlank(codRetorno) && !StringUtils.equals(Constants.NUMERO_CERO, codRetorno)) {
			String keyError = "procesoZONActualizarUnidadesAdministrativasForm.errorTrasvaseErrorEliminacion";
			String msgRetorno = (String) params.get("msgRetorno");
			String retorno = "";
			vista = "procesoZONTrasvasaConsultorasForm";

			if (StringUtils.equals(codRetorno, "3")) {
				keyError = "procesoZONActualizarUnidadesAdministrativasForm.errorAprobElimina3";
			}

			if (StringUtils.isNotBlank(msgRetorno)) {
				retorno = msgRetorno;
			} else {
				retorno = codRetorno;
			}
			String texto = this.getResourceMessage(keyError, new String[]{retorno});
			this.addError("proceso.error", texto);
			return;
		} else {
			addInfo("Mensaje", this.getResourceMessage("proceso.ok"));
			this.mensajeAlert=this.getResourceMessage("proceso.ok");
		}
		
		this.redireccionarPagina(vista);

	}

	/**
	 * Accion que valida las regiones seleccionadas no hayan cerrado en el
	 * periodo indicado y que las regiones no hayan iniciado su facturacion
	 * 
	 * 
	 */
	public void validarRegion(ActionEvent evt) {
		try {
		ProcesoZONActualizarUnidadesAdministrativasService service = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		if(!this.showZonasxPais)
			f.setIndRegionZona(Constants.ZON_UNID_ADMIN_IND_REGION);
		
			if(StringUtils.isBlank(f.getIndRegionZona())){
				this.addWarn("Error: ", "Debe seleccionar Regiones o Zonas");
				return;
			}
			
			boolean indicadorRegion=false;
			boolean indicadorZona=false;
			if(StringUtils.equals(f.getIndRegionZona(), Constants.ZON_UNID_ADMIN_IND_REGION)){
				if(f.getCodigosRegion().length>0)
					indicadorRegion=true;
				else{
					this.addError("Mensaje: ", "Indique al menos una región");
					return;
				}
			}
			
			if(StringUtils.equals(f.getIndRegionZona(), Constants.ZON_UNID_ADMIN_IND_ZONA)){
				if(f.getCodigosZona().length>0)
					indicadorZona=true;
				else{
					this.addError("Mensaje: ", "Indique al menos una Zona");
					return;
				}
			}		
		
			Map params = new HashMap();
			params.put("codigoPais", pais.getCodigo());
			params.put("codigoMarca", f.getCodigoMarca());
			params.put("codigoCanal", f.getCodigoCanal());
			params.put("codigoPeriodo", f.getPeriodoCorporativo());
			params.put("codigosRegion", f.getCodigosRegion());
			params.put("validarRegionCerrada", "1");
			params.put("validarRegionFacturando", "1");
			params.put("codigosZona", f.getCodigosZona());

			// Evalua por Region
			if(indicadorRegion){
				service.validarRegiones(params);
				String codRetorno = (String) params.get("codRetorno");
	
				if (StringUtils.equals(codRetorno, Constants.NUMERO_UNO)) {
					String msgRetorno = (String) params.get("msgRetorno");
					String mensaje = this.getResourceMessage("procesoZONActualizarUnidadesAdministrativasForm.errorValidarRegiones",
									new Object[] { msgRetorno });
					this.addError("Error : ", mensaje);
					return;
				}
			}
			
			//Evalua por Zona
			if(indicadorZona){
				service.validarZonas(params);
				String codRetorno = (String) params.get("codRetorno");
	
				if (StringUtils.equals(codRetorno, Constants.NUMERO_UNO)) {
					String msgRetorno = (String) params.get("msgRetorno");
					String mensaje = this.getResourceMessage("procesoZONActualizarUnidadesAdministrativasForm.errorValidarZonas",
									new Object[] { msgRetorno });
					this.addError("Error : ", mensaje);
					return;
				}
			}
		
			
			f.setArchivosSubidos(null);
			this.redireccionarPagina("procesoZONActualizarUniAdmUploadForm");

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		return;
	}

	/**
	 * Accion que permite agregar archivos a la lista de archivos
	 * 
	 * 
	 */
	public void load(FileUploadEvent event) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'uploadProceso' method");
		}

		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;

		String nomArchivo =event.getFile().getFileName();
		if(nomArchivo.length()>40){
			this.addError("Error: ", "Nombre de Archivo: Máximo 40 caracteres, actual "+nomArchivo.length());
			return;
		}
		this.setAttachment(event.getFile().getFileName());
		f.setArchivo(event.getFile());

		try {

			if (f.getArchivo() != null && f.getArchivo().getFileName() != null
					&& f.getArchivo().getFileName().trim().length() > 0) {
				uploadArchivo(f);

				ArchivoSubidoForm archivo = new ArchivoSubidoForm();
				archivo.setNombreArchivo(f.getNombreArchivo());
				archivo.setNombreCompleto(f.getNombreCompletoArchivo());

				ArchivoSubidoForm[] archivosSubidos = f.getArchivosSubidos();
				f.setArchivosSubidos((ArchivoSubidoForm[]) ArrayUtils.add(
						archivosSubidos, archivo));
				this.listaArchivos = new ArrayList();

				if (archivosSubidos != null) {
					for (ArchivoSubidoForm archivoSubidoForm : f
							.getArchivosSubidos()) {
						this.listaArchivos.add(archivoSubidoForm);
					}
				} else {
					f.setArchivosSubidos(new ArchivoSubidoForm[] { archivo });
					listaArchivos.add(archivo);
				}

				// this.setListaArchivoModel(new DataTableModel(lista));

			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		return;
	}

	/**
	 * permite la carga del archivo de la maquina del cliente a un directorio de
	 * la maquina del Servidor
	 * 
	 * @param f
	 * @throws Exception
	 */
	private void uploadArchivo(ProcesoZONActualizarUnidadesAdministrativasForm f)
			throws Exception {

		// recuperamos el fichero
		// ProcesoZONActualizarUnidadesAdministrativasForm f =
		// (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		UploadedFile archivo = f.getArchivo();

		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());

		// leyemos el stream de entrada
		InputStream is = archivo.getInputstream();

		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(
				f.getDirectorioTemporal(), f.getNombreArchivo()));

		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();

		f.setArchivo(null);
		String mensaje = "Se subió el archivo al servidor";
		addInfo("Mensaje", mensaje);
	}

	/**
	 * Accion que permite remover archivos
	 * 
	 * 
	 */
	public void removerFile() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'removerArchivo' method");
		}

		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		// List seleccionado = (ArchivoSubidoForm) this.archivosSeleccionados;

		// String nombreArchivo = seleccionado.getNombreArchivo();
		// String nombreCompleto = seleccionado.getNombreCompleto();

		if (f.getNombreArchivo() == null) {
			String mensaje = "No ha cargado ningún archivo";
			addInfo("Mensaje: ", mensaje);
			return;
		}

		try {

			if (this.archivosSeleccionados != null) {
				for (int i = 0; i < this.listaArchivos.size(); i++) {
					for (int j = 0; j < this.archivosSeleccionados.size(); j++) {
						if (((ArchivoSubidoForm) this.archivosSeleccionados
								.get(j)).getNombreArchivo().equals(
								((ArchivoSubidoForm) this.listaArchivos.get(i))
										.getNombreArchivo())) {
							this.listaArchivos.remove(i);
							f.setArchivosSubidos((ArchivoSubidoForm[]) ArrayUtils
									.remove(f.getArchivosSubidos(), i));
							String mensaje = "Se removío el archivo seleccionado";
							addInfo("Mensaje: ", mensaje);
						}
					}
				}
			} else {
				String mensaje = "Seleccione un elemento de la lista para remover";
				addInfo("Mensaje: ", mensaje);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		return;
	}

	/**
	 * Accion que permite cargar archivos de la maquina del cliente al servidor
	 * 
	 * 
	 */
	public void aceptarArchivos() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'aceptarArchivos' method");
		}

		// Validando uniformidad de cabeceras
		// Se tomara como referencia de comparacion a cabecera del primer
		// archivo
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;

		try {
			if (f.getArchivosSubidos() == null) {
				String mensaje = "Ingrese al menos un archivo para proceder a la ejecución";
				addInfo("Mensaje: ", mensaje);
				return;
			}

			ArchivoSubidoForm[] archivos = f.getArchivosSubidos();

			StringBuffer mensajeError = new StringBuffer();
			boolean todosIguales = true;
			List cabeceras = null;
			String extension = null;

			if (archivos != null && archivos.length > 0) {
				extension = obtenerExtensionArchivo(archivos[0]
						.getNombreArchivo());
				if (extension
						.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL))
					cabeceras = leerCabeceraArchivoExcel(
							f.getDirectorioTemporal(),
							archivos[0].getNombreArchivo());
				if (extension.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_DBF))
					cabeceras = leerCabeceraArchivoDBF(
							f.getDirectorioTemporal(),
							archivos[0].getNombreArchivo());

				for (int i = 1; i < archivos.length; i++) {
					List cabeceras_i = null;
					String extension_i = obtenerExtensionArchivo(archivos[i]
							.getNombreArchivo());
					if (!extension.equals(extension_i)) {
						todosIguales = false;
						if (mensajeError.length() > 0)
							mensajeError.append(",");
						mensajeError.append(archivos[i].getNombreCompleto()
								+ "=ext " + extension_i);
						continue;
					}
					if (extension
							.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_EXCEL)) {
						cabeceras_i = leerCabeceraArchivoExcel(
								f.getDirectorioTemporal(),
								archivos[i].getNombreArchivo());
					}
					if (extension
							.equalsIgnoreCase(Constants.EXTENSION_ARCHIVO_DBF)) {
						cabeceras_i = leerCabeceraArchivoDBF(
								f.getDirectorioTemporal(),
								archivos[i].getNombreArchivo());
					}

					if (!cabecerasIguales(cabeceras, cabeceras_i)) {
						todosIguales = false;
						if (mensajeError.length() > 0)
							mensajeError.append(",");
						mensajeError.append(archivos[i].getNombreCompleto()
								+ "=" + leerCamposDeCabecera(cabeceras_i));
					}
				}
			}

			if (!todosIguales) {
				addError(
						"Error",
						getResourceMessage("procesoZONActualizarUnidadesAdministrativasForm.errorArchivosDiferentes"));

				// messages.add(ActionMessages.GLOBAL_MESSAGE, new
				// ActionMessage("procesoZONActualizarUnidadesAdministrativasForm.errorArchivosDiferentes",
				// "Archivo Base="+leerCamposDeCabecera(cabeceras),
				// mensajeError));

			}

			this.zonCampoList = cabeceras;

			f.setExtensionArchivos(extension);
			f.setFlagHabilitarCombos(true);
			f.setFlagHabilitaCargaDatos(true);

			f.setColumnaSGV(null);
			f.setColumnaRegion(null);
			f.setColumnaZona(null);
			f.setColumnaSeccion(null);
			f.setColumnaTerritorio(null);
			f.setColumnaUbigeo(null);
			f.setColumnaNSE(null);
			f.setFlagMostrarPDFErrorCargaDatos(false);
			f.setFlagHabilitaValidarDatos(false);
			f.setFlagHabilitaGeneraReporte(false);
			f.setFlagHabilitaAprobarCreaciones(false);
			f.setFlagHabilitaAprobarModificaciones(false);
			f.setFlagHabilitaAprobarTrasvases(false);
			f.setFlagHabilitaAprobarEliminaciones(false);
			f.setFlagHabilitaAprobarReactivaciones(false);

			this.redireccionarPagina("procesoZONActualizarUnidadAdministrativaForm");

		}

		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Obtiene la extension del archivo
	 * 
	 * 
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * Lee la primera fila del archivo excel
	 * 
	 * 
	 */
	private List leerCabeceraArchivoExcel(String directorioTemp,
			String nombreArchivo) throws Exception {
		ExcelUtil excelUtil = new ExcelUtil(directorioTemp, nombreArchivo);
		List listCamposArchivo = excelUtil.getFilaConColumnaNoNulos(0, 0);
		excelUtil.cerrar();
		return listCamposArchivo;
	}

	/**
	 * Lee los campos de la tabla DBF
	 * 
	 * 
	 */
	private List leerCabeceraArchivoDBF(String directorioTemp,
			String nombreArchivo) throws Exception {
		DBFUtil dbfUtil = new DBFUtil(directorioTemp, nombreArchivo);
		List listCamposArchivo = dbfUtil.obtenerBaseCampos();
		dbfUtil.cerrar();
		return listCamposArchivo;
	}

	/**
	 * Compara cabeceras de diferentes archivos
	 * 
	 * 
	 */
	private boolean cabecerasIguales(List a, List b) {

		if (a.size() != b.size()) {
			log.debug("Tamaños diferentes:" + a.size() + ", " + b.size());
			return false;
		}
		for (int i = 0; i < a.size(); i++) {
			Base a_i = (Base) a.get(i);
			Base b_i = (Base) a.get(i);
			if (a_i != null && b_i != null) {
				String cod_a_i = a_i.getCodigo();
				String cod_b_i = b_i.getCodigo();
				if (cod_a_i != null && cod_b_i != null
						&& !cod_a_i.equals(cod_b_i)) {
					log.debug("Codigos diferentes:" + cod_a_i + ", " + cod_b_i);
					return false;
				} else if ((cod_a_i != null && cod_b_i == null)
						|| (cod_a_i == null && cod_b_i != null)) {
					log.debug("Codigos uno no es nulo:" + cod_a_i + ", "
							+ cod_b_i);
					return false;
				}
				String des_a_i = a_i.getDescripcion();
				String des_b_i = b_i.getDescripcion();
				if (des_a_i != null && des_b_i != null
						&& !des_a_i.equals(des_b_i)) {
					log.debug("Descripciones diferentes:" + des_a_i + ", "
							+ des_b_i);
					return false;
				} else if ((des_a_i != null && des_b_i == null)
						|| (des_a_i == null && des_b_i != null)) {
					log.debug("Descripciones uno no es nulo:" + des_a_i + ", "
							+ des_b_i);
					return false;
				}
			} else {
				if ((a_i != null && b_i == null)
						|| (a_i == null && b_i != null)) {
					log.debug("Elementos de lista uno no es nulo:" + a_i + ", "
							+ b_i);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * funcion que permite leer los campos de la lista
	 * 
	 * 
	 */
	private String leerCamposDeCabecera(List cabecera) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < cabecera.size(); i++) {
			Base columna = (Base) cabecera.get(i);
			if (i > 0)
				sb.append(",");
			sb.append(columna.getDescripcion());
		}
		sb.append("]");

		return sb.toString();
	}

	/**
	 * Carga los datos de los archivos recogidos a la BD
	 * 
	 * 
	 */
	public void cargarDatos(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargarDatos' method");
		}
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		try {
			String[] archivos = new String[f.getArchivosSubidos().length];
			for (int i = 0; i < f.getArchivosSubidos().length; i++) {
				archivos[i] = f.getArchivosSubidos()[i].getNombreArchivo();
			}

			Map params = new HashMap();
			params.put("directorioTemporal", f.getDirectorioTemporal());
			params.put("archivos", archivos);
			params.put("extensionArchivo", f.getExtensionArchivos());
			params.put("codigoPais", pais.getCodigo());
			params.put("codigoMarca", f.getCodigoMarca());
			params.put("codigoCanal", f.getCodigoCanal());
			params.put("codigoPeriodo", f.getPeriodoCorporativo());
			params.put("codigosRegion", Arrays.asList(f.getCodigosRegion()));
			params.put("colSgv", f.getColumnaSGV());
			params.put("colReg", f.getColumnaRegion());
			params.put("colZon", f.getColumnaZona());
			params.put("colSec", f.getColumnaSeccion());
			params.put("colTer", f.getColumnaTerritorio());
			params.put("colUbi", f.getColumnaUbigeo());
			params.put("colNse", f.getColumnaNSE());
			if(f.getCodigosZona()!=null)
				params.put("codigosZona", Arrays.asList(f.getCodigosZona()));
			params.put("indRegionZona", f.getIndRegionZona());

			ProcesoZONActualizarUnidadesAdministrativasService service = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");
			service.iniciarTablas(params);
			service.cargarArchivos(params);

			String codRetorno = (String) params.get("codRetorno");
			if (StringUtils.isNotBlank(codRetorno) && !StringUtils.equals(Constants.NUMERO_CERO, codRetorno)) {
				String keyError = null;
				String msgRetorno = (String) params.get("msgRetorno");
				if (StringUtils.equals(Constants.NUMERO_UNO, codRetorno)) {
					f.setFlagMostrarPDFErrorCargaDatos(true);
					keyError = "procesoZONActualizarUnidadesAdministrativasForm.errorCargarArchivos1";					
					throw new Exception(this.getResourceMessage(keyError));
				} else {					
					keyError = this.getResourceMessage("procesoZONActualizarUnidadesAdministrativasForm.errorCargarArchivos",
							new Object[] { msgRetorno });
					throw new Exception(keyError);
				}
			}

			f.setFlagHabilitaValidarDatos(true);
			f.setFlagMostrarPDFErrorCargaDatos(false);
			f.setFlagHabilitarCombos(false);
			f.setFlagHabilitaCargaDatos(false);

			// verificamos si ha selecionado la columna de Nivel SocioEconomico
			if (f.getColumnaNSE().length() > 0) {
				f.setIndicadorNivelSocioEconomico(Constants.NUMERO_UNO);
			} else {
				f.setIndicadorNivelSocioEconomico(Constants.NUMERO_CERO);
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		return;
	}

	/**
	 * Accion que realiza validaciones de estructura y de negocio
	 * 
	 * 
	 */
	public void validarDatos(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validarDatos' method");
		}
		try {
			this.mensajeAlert="";
			ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Map params = new HashMap();
			params.put("indicadorNivelSocioEconomico",f.getIndicadorNivelSocioEconomico());

			ProcesoZONActualizarUnidadesAdministrativasService service = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");
			service.ejecutarValidacion(params);
			/* INI JJ PER-SiCC-2012-0582 */
			f.setOperacion("ValidarDatos");
			f.setFlagReporteVisto("N");
			this.reporteGenerado = false;
			f.setFlagHabilitaGeneraReporte(true);
			f.setFlagHabilitaAprobarCreaciones(false);
			f.setFlagHabilitaAprobarModificaciones(false);
			f.setFlagHabilitaAprobarTrasvases(false);
			f.setFlagHabilitaAprobarEliminaciones(false);
			f.setFlagHayErroresEliminacion(false);
			f.setFlagHabilitaAprobarReactivaciones(true);
			//f.setFlagMostrarPDFErrorCargaDatos(true);
			//f.setFlagHabilitaValidarDatos(true);

			// Recuperamos la cantidad de registros a procesar por Altas, Bajas,
			// Modificaciones, Trasvase
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("operacion", "A");
			f.setCantidadRegistrosAlta(service
					.obtenerCantidadRegistrosOperacion(criteria));
			criteria.put("operacion", "B");
			f.setCantidadRegistrosBaja(service
					.obtenerCantidadRegistrosOperacion(criteria));
			criteria.put("operacion", "T");
			f.setCantidadRegistrosTrasvase(service
					.obtenerCantidadRegistrosOperacion(criteria));
			criteria.put("operacion", "M");
			f.setCantidadRegistrosModificacion(service
					.obtenerCantidadRegistrosOperacion(criteria));
			criteria.put("operacion", "R");
			f.setCantidadRegistrosReactivacion(service
					.obtenerCantidadRegistrosOperacion(criteria));

			// Validar si hay creaciones de Zonas
			// si las hay mostrar pantalla para completar descripciones
			List uniAdmACrear = service.obtenerUniAdmACrear();
			if (uniAdmACrear != null && uniAdmACrear.size() > 0) {
				f.setUnidadesAdministrativasACrear(uniAdmACrear);
				this.zonUnidadAdminCrearList=uniAdmACrear;
				this.zonUnidadAdminCrearModel= new DataTableModel(this.zonUnidadAdminCrearList);
				this.redireccionarPagina("procesoZONCompletaDescripcionUniAdmACrearForm");
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Accion que graba las descripcion de las nuevas zonas a crear
	 * * 
	 */
	public void grabarDescripciones(ActionEvent evt){
		try {
			ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;		
			ProcesoZONActualizarUnidadesAdministrativasService service = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");
			service.grabarDescripcionesUniAdmACrear(this.zonUnidadAdminCrearList);
			this.redireccionarPagina("procesoZONActualizarUnidadAdministrativaForm");
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Accion que realiza las eliminaciones de las unidades administrativas
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void aprobarEliminaciones(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'aprobarEliminaciones' method");
		}
		try {
			ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
			f.setOperacion("AprobarEliminaciones");

			if (this.reporteGenerado)
				if ((f.getOperacion().equalsIgnoreCase("AprobarEliminaciones") && f
						.getCantidadRegistrosBaja().equalsIgnoreCase("0")))

					throw new Exception(
							this.getResourceMessage("procesoZONActualizarUnidadesAdministrativasForm.msg.sinRegistrosProcesar"));

			ProcesoZONActualizarUnidadesAdministrativasService service = (ProcesoZONActualizarUnidadesAdministrativasService) getBean("spusicc.procesoZONUniAdmService");
			Map params = new HashMap();
			params = prepareParamsBeforeExecute(params, f);

			/* INI JJ PER-SiCC-2012-0582 */
			f.setFlagHabilitaAprobarCreaciones(false);
			f.setFlagHabilitaAprobarModificaciones(false);
			f.setFlagHabilitaAprobarTrasvases(false);
			f.setFlagHabilitaAprobarReactivaciones(false);
			/* FIN JJ PER-SiCC-2012-0582 */

			f.setFlagHayErroresEliminacion(false);
			log.debug("Ejecutando la operacion..." + params.get("operacion"));

			try {
				service.ejecutarActualizacionUnidadesAdministrativas(params);
			} catch (Exception e) {
				addError("Error", getResourceMessage("proceso.error"));
			}

			String codRetorno = (String) params.get("codRetorno");

			if (StringUtils.isNotBlank(codRetorno)
					&& !Constants.NUMERO_CERO.equals(codRetorno.trim())) {
				String msgRetorno = (String) params.get("msgRetorno");
				String retorno = "";
				String keyError = "";
				if (StringUtils.isNotBlank(msgRetorno))
					retorno = msgRetorno;
				else
					retorno = codRetorno;

				if (codRetorno.equals("2")) {
					// Se redirecciona para trasvasar los clientes en
					// terriotrios a eliminar
					this.siccZonaList = service.obtenerZonasDefinitivas(params);

					f.setConsultorasATrasvasar(service
							.obtenerConsultorasATrasvasar());
					
					
					
					Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
					AjaxService ajaxService = (AjaxService) getBean("ajaxService");
					Base baseZona = (Base)this.siccZonaList.get(0);
					
					LabelValue[] lbAuxiliarSeccion = ajaxService.getSeccionesTrasvaseEnActUniAdm(pais.getCodigo(), f.getCodigoMarca(), f.getCodigoCanal(), baseZona.getCodigo(), ""+0);
					
					int cont = 0;
					if(lbAuxiliarSeccion!=null && lbAuxiliarSeccion.length>0){
						this.siccSeccionList = new LabelValue[lbAuxiliarSeccion.length-1];						
						for (int i = 1; i < lbAuxiliarSeccion.length; i++) {
							this.siccSeccionList[cont]=lbAuxiliarSeccion[i];
							cont++;
						}
					}
					
					cont=0;
					LabelValue[] lbAuxiliarTerritorio = ajaxService.getTerritoriosTrasvaseEnActUniAdm(pais.getCodigo(), f.getCodigoMarca(), f.getCodigoCanal(), baseZona.getCodigo(), siccSeccionList[0].getValue(), ""+0);
					if(lbAuxiliarTerritorio!=null && lbAuxiliarTerritorio.length>0){
						this.siccTerritoriosList = new LabelValue[lbAuxiliarTerritorio.length-1];
						for (int i = 1; i < lbAuxiliarTerritorio.length; i++) {
							this.siccTerritoriosList[cont]=lbAuxiliarTerritorio[i];
							cont++;
						}
					}
					
					this.listaComboSeccion = new ArrayList();
					this.listaComboTerritorio = new ArrayList();
					
					if(f.getConsultorasATrasvasar()!=null){
						
						for (int i = 0; i < f.getConsultorasATrasvasar().size(); i++) {
							ConsultoraATrasvasar obj =  (ConsultoraATrasvasar)f.getConsultorasATrasvasar().get(i);
							if(baseZona!=null){
								obj.setNuevoCodigoZona(baseZona.getCodigo());
							}
							if(siccSeccionList!=null){
								obj.setNuevoCodigoSeccion(siccSeccionList[0].getValue());
							}
							if(siccTerritoriosList!=null){
								obj.setNuevoCodigoTerritorio(siccTerritoriosList[0].getValue());
							}
						}
						
						for (int i = 0; i < f.getConsultorasATrasvasar().size(); i++) {
							listaComboSeccion.add(siccSeccionList);
						}
						
						for (int i = 0; i < f.getConsultorasATrasvasar().size(); i++) {
							listaComboTerritorio.add(siccTerritoriosList);
						}
					}
					
					
					this.dtTrasvasa = new DataTableModel(f.getConsultorasATrasvasar());

					this.redireccionarPagina("procesoZONTrasvasaConsultorasForm");
					return;
				} else {
					if (codRetorno.equals("1")) {
						keyError = "procesoZONActualizarUnidadesAdministrativasForm.errorAprobElimina1";
					} else if (codRetorno.equals("3")) {
						keyError = "procesoZONActualizarUnidadesAdministrativasForm.errorAprobElimina3";
						f.setFlagHayErroresEliminacion(true);
					}

					String texto = keyError + " & " + retorno;
					this.addError("Error", "proceso.error" + texto);
					return;
				}

			}

			f.setFlagHabilitaAprobarEliminaciones(false);

			this.addInfo(
					"Mensaje",
					getResourceMessage("procesoZONActualizarUnidadesAdministrativasForm.eliminaciones.msg.ok"));


		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * Recupera y envia el error de negocio correspondiente (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #setErrorLogicaNegocio(java.util.Map)
	 */
	protected String setErrorLogicaNegocio(Map params) {
		String retorno = null;
		String codRetorno = (String) params.get("codRetorno");
		if (StringUtils.isNotBlank(codRetorno)
				&& !Constants.NUMERO_CERO.equals(codRetorno.trim())) {
			String msgRetorno = (String) params.get("msgRetorno");
			if (msgRetorno != null && msgRetorno.trim().length() > 0) {
				retorno = msgRetorno;
			} else {
				retorno = codRetorno;
			}
		}

		return retorno;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #afterExecuteProcess
	 * (biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm)
	 */
	@Override
	public void afterExecuteProcess(BaseProcesoForm form) throws Exception {
		super.afterExecuteProcess(form);

		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		// Desactivar la opcion clickeada
		f.setFlagHabilitaValidarDatos(false);
		if (f.getOperacion().equals("AprobarCreaciones")) {
			f.setFlagHabilitaAprobarCreaciones(false);
		}
		if (f.getOperacion().equals("AprobarModificaciones")) {
			f.setFlagHabilitaAprobarModificaciones(false);
		}
		if (f.getOperacion().equals("AprobarTrasvases")) {
			f.setFlagHabilitaAprobarTrasvases(false);
		}
	}
	
	
	public void loadSecciones() {
		
		
		String indice = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");		
		String combo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("valorCombo");
		log.info(indice);
		
		int i = new Integer(indice).intValue();
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		ConsultoraATrasvasar obj = (ConsultoraATrasvasar)f.getConsultorasATrasvasar().get(i);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
		obj.setNuevoCodigoZona(combo);
		
		
		LabelValue[] lbAuxiliarSeccion = ajaxService.getSeccionesTrasvaseEnActUniAdm(pais.getCodigo(), f.getCodigoMarca(), f.getCodigoCanal(), obj.getNuevoCodigoZona(), ""+i);
		int cont = 0;
		if(lbAuxiliarSeccion!=null){
			this.siccSeccionList = new LabelValue[lbAuxiliarSeccion.length-1];
			for (int j = 1; j < lbAuxiliarSeccion.length; j++) {
				this.siccSeccionList[cont]=lbAuxiliarSeccion[j];
				cont++;
			}
			
			listaComboSeccion.set(i, this.siccSeccionList);
			obj.setNuevoCodigoSeccion(siccSeccionList[0].getValue());
		}
		
		cont=0;
		LabelValue[] lbAuxiliarTerritorio = ajaxService.getTerritoriosTrasvaseEnActUniAdm(pais.getCodigo(), f.getCodigoMarca(), f.getCodigoCanal(), obj.getNuevoCodigoZona(), obj.getNuevoCodigoSeccion(), ""+i);
		if(lbAuxiliarTerritorio!=null){
			this.siccTerritoriosList = new LabelValue[lbAuxiliarTerritorio.length-1];
			for (int j = 1; j < lbAuxiliarTerritorio.length; j++) {
				this.siccTerritoriosList[cont]=lbAuxiliarTerritorio[j];
				cont++;
			}
			
			listaComboTerritorio.set(i, this.siccTerritoriosList);
			obj.setNuevoCodigoTerritorio(siccTerritoriosList[0].getValue());
		}
		
		
		
	}
	
	public void loadTerritorios() {
		
		
		String indice = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");		
		String combo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("valorCombo");
		log.info(indice);
		
		int i = new Integer(indice).intValue();
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		ConsultoraATrasvasar obj = (ConsultoraATrasvasar)f.getConsultorasATrasvasar().get(i);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");		
		obj.setNuevoCodigoSeccion(combo);
		
		
		LabelValue[] lbAuxiliarTerritorio = ajaxService.getTerritoriosTrasvaseEnActUniAdm(pais.getCodigo(), obj.getCodigoMarca(), obj.getCodigoCanal(), obj.getNuevoCodigoZona(), obj.getNuevoCodigoSeccion(), ""+i);
		if(lbAuxiliarTerritorio!=null){
			this.siccTerritoriosList = new LabelValue[lbAuxiliarTerritorio.length-1];
			int cont = 0;
			for (int j = 1; j < lbAuxiliarTerritorio.length; j++) {
				this.siccTerritoriosList[cont]=lbAuxiliarTerritorio[j];
				cont++;
			}
			obj.setNuevoCodigoTerritorio(this.siccTerritoriosList[0].getValue());
			listaComboTerritorio.set(i, this.siccTerritoriosList);
		}		
	}
	
	public void actualizaTerritorio() {		
		String indice = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");		
		String combo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("valorCombo");
		log.info(indice);
		
		int i = new Integer(indice).intValue();
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		ConsultoraATrasvasar obj = (ConsultoraATrasvasar)f.getConsultorasATrasvasar().get(i);	
		obj.setNuevoCodigoTerritorio(combo);		
	}
	
	public void loadZonas(ValueChangeEvent val) {	
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		String []valorZona=(String[]) val.getNewValue();
		LabelValue zonas[] = ajax.getZonasMultipleByPaisMarcaCanalRegion(pais.getCodigo(), "T", "VD", valorZona, "N");
		if(zonas != null && zonas.length > 0) 
			this.zonaRegionList = Arrays.asList(zonas);			
		 else 
			this.zonaRegionList = new ArrayList();		
			
	}
	
	public void habilitaZonaxRegion() {	
		ProcesoZONActualizarUnidadesAdministrativasForm f = (ProcesoZONActualizarUnidadesAdministrativasForm) this.formProceso;
		if(StringUtils.equals(f.getIndRegionZona(), Constants.ZON_UNID_ADMIN_IND_REGION))
			this.inhabilitaZonas=true;
		else
			this.inhabilitaZonas=false;			
			
	}
	
	


	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccPeriodoCorporativoList
	 */
	public List getSiccPeriodoCorporativoList() {
		return siccPeriodoCorporativoList;
	}

	/**
	 * @param siccPeriodoCorporativoList
	 *            the siccPeriodoCorporativoList to set
	 */
	public void setSiccPeriodoCorporativoList(List siccPeriodoCorporativoList) {
		this.siccPeriodoCorporativoList = siccPeriodoCorporativoList;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the zonCampoList
	 */
	public List getZonCampoList() {
		return zonCampoList;
	}

	/**
	 * @param zonCampoList
	 *            the zonCampoList to set
	 */
	public void setZonCampoList(List zonCampoList) {
		this.zonCampoList = zonCampoList;
	}

	/**
	 * @return the siccZonaList
	 */
	public List getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(List siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the listaArchivoModel
	 */
	public DataTableModel getListaArchivoModel() {
		return listaArchivoModel;
	}

	/**
	 * @param listaArchivoModel
	 *            the listaArchivoModel to set
	 */
	public void setListaArchivoModel(DataTableModel listaArchivoModel) {
		this.listaArchivoModel = listaArchivoModel;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the archivosSeleccionados
	 */
	public List getArchivosSeleccionados() {
		return archivosSeleccionados;
	}

	/**
	 * @param archivosSeleccionados
	 *            the archivosSeleccionados to set
	 */
	public void setArchivosSeleccionados(List archivosSeleccionados) {
		this.archivosSeleccionados = archivosSeleccionados;
	}

	/**
	 * @return the listaArchivos
	 */
	public List getListaArchivos() {
		return listaArchivos;
	}

	/**
	 * @param listaArchivos
	 *            the listaArchivos to set
	 */
	public void setListaArchivos(List listaArchivos) {
		this.listaArchivos = listaArchivos;
	}

	/**
	 * @return the reporteZonValidacionActUniAdm
	 */
	public ReporteZONValidacionActUniAdmAction getReporteZonValidacionActUniAdm() {
		return reporteZonValidacionActUniAdm;
	}

	/**
	 * @param reporteZonValidacionActUniAdm
	 *            the reporteZonValidacionActUniAdm to set
	 */
	public void setReporteZonValidacionActUniAdm(
			ReporteZONValidacionActUniAdmAction reporteZonValidacionActUniAdm) {
		this.reporteZonValidacionActUniAdm = reporteZonValidacionActUniAdm;
	}

	/**
	 * @return the reporteGenerado
	 */
	public Boolean getReporteGenerado() {
		return reporteGenerado;
	}

	/**
	 * @param reporteGenerado
	 *            the reporteGenerado to set
	 */
	public void setReporteGenerado(Boolean reporteGenerado) {
		this.reporteGenerado = reporteGenerado;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

	/**
	 * @return the siccTerritoriosList
	 */
	public LabelValue[] getSiccTerritoriosList() {
		return siccTerritoriosList;
	}

	/**
	 * @param siccTerritoriosList the siccTerritoriosList to set
	 */
	public void setSiccTerritoriosList(LabelValue[] siccTerritoriosList) {
		this.siccTerritoriosList = siccTerritoriosList;
	}


	/**
	 * @return the dtTrasvasa
	 */
	public DataTableModel getDtTrasvasa() {
		return dtTrasvasa;
	}

	/**
	 * @param dtTrasvasa the dtTrasvasa to set
	 */
	public void setDtTrasvasa(DataTableModel dtTrasvasa) {
		this.dtTrasvasa = dtTrasvasa;
	}

	/**
	 * @return the listaComboSeccion
	 */
	public List getListaComboSeccion() {
		return listaComboSeccion;
	}

	/**
	 * @param listaComboSeccion the listaComboSeccion to set
	 */
	public void setListaComboSeccion(List listaComboSeccion) {
		this.listaComboSeccion = listaComboSeccion;
	}

	/**
	 * @return the listaComboTerritorio
	 */
	public List getListaComboTerritorio() {
		return listaComboTerritorio;
	}

	/**
	 * @param listaComboTerritorio the listaComboTerritorio to set
	 */
	public void setListaComboTerritorio(List listaComboTerritorio) {
		this.listaComboTerritorio = listaComboTerritorio;
	}

	/**
	 * @return the mensajeAlert
	 */
	public String getMensajeAlert() {
		return mensajeAlert;
	}

	/**
	 * @param mensajeAlert the mensajeAlert to set
	 */
	public void setMensajeAlert(String mensajeAlert) {
		this.mensajeAlert = mensajeAlert;
	}

	/**
	 * @return the zonaRegionList
	 */
	public List getZonaRegionList() {
		return zonaRegionList;
	}

	/**
	 * @param zonaRegionList the zonaRegionList to set
	 */
	public void setZonaRegionList(List zonaRegionList) {
		this.zonaRegionList = zonaRegionList;
	}

	/**
	 * @return the inhabilitaZonas
	 */
	public boolean isInhabilitaZonas() {
		return inhabilitaZonas;
	}

	/**
	 * @param inhabilitaZonas the inhabilitaZonas to set
	 */
	public void setInhabilitaZonas(boolean inhabilitaZonas) {
		this.inhabilitaZonas = inhabilitaZonas;
	}

	/**
	 * @return the zonUnidadAdminCrearList
	 */
	public List getZonUnidadAdminCrearList() {
		return zonUnidadAdminCrearList;
	}

	/**
	 * @param zonUnidadAdminCrearList the zonUnidadAdminCrearList to set
	 */
	public void setZonUnidadAdminCrearList(List zonUnidadAdminCrearList) {
		this.zonUnidadAdminCrearList = zonUnidadAdminCrearList;
	}

	/**
	 * @return the zonUnidadAdminCrearModel
	 */
	public DataTableModel getZonUnidadAdminCrearModel() {
		return zonUnidadAdminCrearModel;
	}

	/**
	 * @param zonUnidadAdminCrearModel the zonUnidadAdminCrearModel to set
	 */
	public void setZonUnidadAdminCrearModel(DataTableModel zonUnidadAdminCrearModel) {
		this.zonUnidadAdminCrearModel = zonUnidadAdminCrearModel;
	}

	/**
	 * @return the reporteZONErroresCargaDatosActUniAdm
	 */
	public ReporteZONErroresCargaDatosActUniAdmAction getReporteZONErroresCargaDatosActUniAdm() {
		return reporteZONErroresCargaDatosActUniAdm;
	}

	/**
	 * @param reporteZONErroresCargaDatosActUniAdm the reporteZONErroresCargaDatosActUniAdm to set
	 */
	public void setReporteZONErroresCargaDatosActUniAdm(
			ReporteZONErroresCargaDatosActUniAdmAction reporteZONErroresCargaDatosActUniAdm) {
		this.reporteZONErroresCargaDatosActUniAdm = reporteZONErroresCargaDatosActUniAdm;
	}

	/**
	 * @return the reporteZONErroresEliminacionActUniAdm
	 */
	public ReporteZONErroresEliminacionActUniAdmAction getReporteZONErroresEliminacionActUniAdm() {
		return reporteZONErroresEliminacionActUniAdm;
	}

	/**
	 * @param reporteZONErroresEliminacionActUniAdm the reporteZONErroresEliminacionActUniAdm to set
	 */
	public void setReporteZONErroresEliminacionActUniAdm(
			ReporteZONErroresEliminacionActUniAdmAction reporteZONErroresEliminacionActUniAdm) {
		this.reporteZONErroresEliminacionActUniAdm = reporteZONErroresEliminacionActUniAdm;
	}

	/**
	 * @return the showZonasxPais
	 */
	public boolean isShowZonasxPais() {
		return showZonasxPais;
	}

	/**
	 * @param showZonasxPais the showZonasxPais to set
	 */
	public void setShowZonasxPais(boolean showZonasxPais) {
		this.showZonasxPais = showZonasxPais;
	}	
	
	

}