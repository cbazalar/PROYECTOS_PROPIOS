package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.SolicitudCredito;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WS00230301Endpoint;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WebServiceEquifax;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.WebServiceEquifaxLocator;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Dato;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.EstructuraServicio16;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Header;
import biz.belcorp.ssicc.service.spusicc.com.equifax.WebService.types.Integrante;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpservice;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpserviceLocator;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaHIPClientePOPUPSearchAction;
import biz.belcorp.ssicc.web.spusicc.mae.action.ConsultaMAEClienteSearchAction;
import biz.belcorp.ssicc.web.spusicc.mae.action.MantenimientoMAEBloqueoDesbloqueoClienteAction;
import biz.belcorp.ssicc.web.spusicc.mae.action.MantenimientoMAEInformacionClienteAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEBloqueoDesbloqueoClienteSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEInformacionClienteForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOSolicitudCreditoForm;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class MantenimientoSTOSolicitudCreditoAction extends BaseMantenimientoSTOGestionAction{

	private static final long serialVersionUID = 8312010844948706133L;
	
	private List stoTipoDocList;
	private List stoTipoViaList;
	private List stoMotivosGestion;	
	private LabelValue[] maeCiudadList;
	private LabelValue[] maeCiudadListEntr;	
	private LabelValue[] maeClienteNivel1List;
	private LabelValue[] maeClienteNivel2List;
	private LabelValue[] maeClienteNivel3List;
	private LabelValue[] maeClienteNivel4List;
	private LabelValue[] maeFiadorNivel1List;
	private LabelValue[] maeFiadorNivel2List;
	private LabelValue[] maeFiadorNivel3List;
	private LabelValue[] maeFiadorNivel4List;
	
	private boolean editable;
	private boolean mostrarRojo;
	private boolean mostrarVerde;
	private boolean mostrarAmarillo;
	private boolean bIndTelefonoOK;
	private boolean bIndSituacionCrediticia;
	private boolean bIndSinSaldoAmbasMarcas;
	private boolean bIndFacturacionElectronica;
	private String oidPais;
	
	private String msjeCampo1;
	private String msjeCampo2;
	private String msjeCampo3;
	
	private List stoEstadoCivilList;
	private List stoTipoPersonaList;
	private List stoNacionalidadList;
	private List stoOrigenIngresoList;
	
	private boolean mostrarPopupCliente;
	private static final String POPUP_CLIENTE = "POPUP_CLIENTE";
	
	@ManagedProperty(value="#{busquedaHIPClientePOPUPSearchAction}")	
	private BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction;
	
	//primera ventana(boton)
	@ManagedProperty(value = "#{mantenimientoMAEInformacionClienteAction}")
	protected MantenimientoMAEInformacionClienteAction mantMAEInformacionClienteAction;
	
	//segunda Ventana(boton)
	@ManagedProperty(value = "#{mantenimientoMAEBloqueoDesbloqueoClienteAction}")
	protected MantenimientoMAEBloqueoDesbloqueoClienteAction mantMAEBloqueoDesbloqueoClienteAction;
	
	//tercera Ventana(boton)
	@ManagedProperty(value = "#{consultaMAEClienteSearchAction}")
	protected ConsultaMAEClienteSearchAction consultaMAEClienteSearchAction;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOSolicitudCreditoForm form = new MantenimientoSTOSolicitudCreditoForm();
		return form;
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		 Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		 MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
		 if(f.getFechaProcesoDate()!=null)
			 f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoDate()));
		 if(f.getFechaNacimientoDate()!=null)
			 f.setFechaNacimiento(DateUtil.convertDateToString(f.getFechaNacimientoDate()));
		 if(this.bIndFacturacionElectronica)
			 f.setIndicadorFacturacionElectronica("N");
		 else
			 f.setIndicadorFacturacionElectronica("S");
		 
		 Map criteria = new HashMap();		
		 criteria.put("codigoPais",f.getCodigoPais());
		 criteria.put("numDocumento",f.getNumSecuencia());
		 criteria.put("numLote",f.getNumLote());
		 criteria.put("codPeriodo",f.getCodPeriodo());
		 criteria.put("codCliente",f.getCodCliente());
		 criteria.put("codClienteRetenido",f.getCodClienteRetenido());
		 criteria.put("codigoLiderRecomendante",f.getCodigoLiderRecomendante());
		 criteria.put("codCompania",f.getCodCompania());
		 criteria.put("codDepaCliente",f.getCodDepaCliente());
		 criteria.put("codDepaFiador",f.getCodDepaFiador());
		 criteria.put("indicadorFacturacionElectronica",f.getIndicadorFacturacionElectronica());
		
		 if(f.getCodDistCliente() == null ||f.getCodDistCliente().compareToIgnoreCase("")==0)
		 criteria.put("codDistCliente",f.getCodDistCliente());
		 else			 
			 criteria.put("codDistCliente",f.getCodDistCliente().substring(12,18));
		
		 
		 if(f.getCodDistFiador() == null || f.getCodDistFiador().compareToIgnoreCase("")==0)
			 criteria.put("codDistFiador",f.getCodDistFiador());
		 else
			 criteria.put("codDistFiador",f.getCodDistFiador().substring(12,18));	 
		
		 
		 criteria.put("codDocuFiador",f.getCodDocuFiador());
		 criteria.put("codPremiOo",f.getCodPremio());
		 if(f.getCodProvCliente()==null || f.getCodProvCliente().compareToIgnoreCase("")==0)			 
			 criteria.put("codProvCliente",f.getCodProvCliente());		 
		 else			 
			 criteria.put("codProvCliente",f.getCodProvCliente().substring(6,12));		 
		 
		 if(f.getCodProvFiador() == null || f.getCodProvFiador().compareToIgnoreCase("")==0)
			 criteria.put("codProvFiador",f.getCodProvFiador());
		 else
			 criteria.put("codProvFiador",f.getCodProvFiador().substring(6,12));		 
		
		 if(f.getCodSectCliente()==null || f.getCodSectCliente().compareToIgnoreCase("")==0)
			 criteria.put("codSectCliente",f.getCodSectCliente());		 
		 else
			 criteria.put("codSectCliente",f.getCodSectCliente().substring(18,24));		 
		 
		 if(StringUtils.isBlank(f.getCodSectFiador()))
			 criteria.put("codSectFiador",f.getCodSectFiador());
		 else{
			 String cadena=StringUtils.substring(f.getCodSectFiador(), 18);			 
			 if(StringUtils.equals(cadena, "null"))
				 criteria.put("codSectFiador","null");
			 else
				 criteria.put("codSectFiador",f.getCodSectFiador().substring(18,24)); 
		 }
			 
		 
		
		 criteria.put("codTipoDocumento",f.getCodTipoDocumento());
		 criteria.put("indEstaCivil",f.getIndEstaCivil());
		 criteria.put("indEstaProceso",f.getIndEstaProceso());
		 criteria.put("indMotivoRechazo",f.getIndMotivoRechazo());
		 criteria.put("indNivelEducativo",f.getIndNivelEducativo());
		 criteria.put("indVentaDirecta",f.getIndVentaDirecta());
		 criteria.put("numDireCliente",f.getNumDireCliente());
		 criteria.put("numDireFiador",f.getNumDireFiador());
		 criteria.put("numDocuIdentidad",f.getNumDocuIdentidad());
		 criteria.put("numRUC",f.getNumRUC());
		 criteria.put("tipoDocuFiador",f.getTipoDocuFiador());
		 criteria.put("tipoDocumento",f.getTipoDocumento());
		 criteria.put("tipoIngreso",f.getTipoIngreso());
		 criteria.put("tipoViaCliente",f.getTipoViaCliente());
		 criteria.put("tipoViaFiador",f.getTipoViaFiador());
		 criteria.put("uniAdministrativa",f.getUnidadAdministrativa());
		 criteria.put("apellido1",f.getValApellido1());
		 criteria.put("apellido1Fiador",f.getValApellido1Fiador());
		 criteria.put("apellido2",f.getValApellido2());
		 criteria.put("apellido2Fiador",f.getValApellido2Fiador());
		 criteria.put("barrioCliente",f.getValBarrCliente());
		 criteria.put("barrioFiador",f.getValBarrFiador());
		 criteria.put("celularCliente",f.getValCeluCliente());
		 criteria.put("celularFiador",f.getValCeluFiador());
		 criteria.put("ciudadCliente",f.getValCiudCliente());
		 criteria.put("ciudadFiador",f.getValCiudFiador());
		 criteria.put("depaCliente",f.getValDepaCliente());
		 criteria.put("depaFiador",f.getValDepaFiador());
		 criteria.put("direcCliente",f.getValDirecCliente());
		 criteria.put("direcFiador",f.getValDireFiador());
		 criteria.put("nombre1",f.getValNombre1());
		 criteria.put("nombre1Fiador",f.getValNombre1Fiador());
		 criteria.put("nombre2",f.getValNombre2());
		 criteria.put("nombre2Fiador",f.getValNombre2Fiador());
		 criteria.put("nombreVia",f.getValNombreVia());
		 criteria.put("nombreViaFiador",f.getValNomViaFiador());
		 criteria.put("telfCliente",f.getValTelfCliente());
		 criteria.put("telfTrabajoCliente",f.getValTelfTrabajo());
		 criteria.put("telfFiador",f.getValTelfFiador());
		 criteria.put("telfTrabajoFiador",f.getValTelfTrabaFiador());
		 criteria.put("codRegion",f.getValRegionArribo());
		 criteria.put("codZona",f.getValZonaArribo());
		 criteria.put("email",f.getValMailCliente());
		 criteria.put("fecProceso",f.getFechaProceso());
		 criteria.put("fecNacimiento",f.getFechaNacimiento());
		 criteria.put("codSexoCliente",f.getCodSexoCliente());
		 
		 criteria.put("oidMotiGes",f.getOidMotiGes());
		 criteria.put("valObseGestion", f.getValObseGestion());
		 
		 	
		 log.debug("f.getIndicadorTelefonoOK() "+f.getIndicadorTelefonoOK());
		 log.debug("f.indicadorSituacionCrediticia() "+f.getIndicadorSituacionCrediticia());
		 log.debug("f.indicadorSinSaldoAmbasMarcas() "+f.getIndicadorSinSaldoAmbasMarcas());
		 
		 if(StringUtils.equals(f.getIndInformacionOk(), "1")){
			if(!bIndTelefonoOK){
			 	f.setIndTelefonoOK("0");
			 	criteria.put("indicadorTelefonoOK",Constants.IND_CHECK_OFF);
			}else{
			 	f.setIndTelefonoOK("1");
			 	criteria.put("indicadorTelefonoOK",Constants.IND_CHECK_ON);
			}
		}
		if(StringUtils.equals(f.getIndSitCrediticia(), "1")){
			if(!bIndSituacionCrediticia){
				f.setIndSitCrediticia("0");
				 criteria.put("indicadorSituacionCrediticia",Constants.IND_CHECK_OFF);
			}else{
				f.setIndSitCrediticia("1");
				criteria.put("indicadorSituacionCrediticia",Constants.IND_CHECK_ON);
			}
		}
		if(StringUtils.equals(f.getIndSinSalAmbas(), "1")){
			if(!bIndSinSaldoAmbasMarcas){
				f.setIndSinSalAmbas("0");
				criteria.put("indicadorSinSaldoAmbasMarcas",Constants.IND_CHECK_OFF);	
			}else{
				f.setIndSinSalAmbas("1");
				criteria.put("indicadorSinSaldoAmbasMarcas",Constants.IND_CHECK_ON);
			}
		}
		
		 criteria.put("delegacionCliente",f.getDelegacionCliente());
		 criteria.put("codigoPostalCliente",f.getCodigoPostalCliente());
		 criteria.put("direccionEntrega",f.getDireccionEntrega());
		 criteria.put("barrioEntrega",f.getBarrioEntrega());
		 criteria.put("delegacionEntrega",f.getDelegacionEntrega());
		 criteria.put("departamentoEntrega",f.getDepartamentoEntrega());
		 criteria.put("telefonoEntrega",f.getTelefonoEntrega());
		 criteria.put("celularEntrega",f.getCelularEntrega());
		 criteria.put("apellido1RefFamiliar",f.getApellido1RefFamiliar());
		 criteria.put("nombre1RefFamiliar",f.getNombre1RefFamiliar());
		 criteria.put("direccionRefFamiliar",f.getDireccionRefFamiliar());
		 criteria.put("barrioRefFamiliar",f.getBarrioRefFamiliar());
		 criteria.put("delegacionRefFamiliar",f.getDelegacionRefFamiliar());
		 criteria.put("ciudadRefFamiliar",f.getCiudadRefFamiliar());
		 criteria.put("departamentoRefFamiliar",f.getDepartamentoRefFamiliar());
		 criteria.put("telefonoRefFamiliar",f.getTelefonoRefFamiliar());
		 criteria.put("celularRefFamiliar",f.getCelularRefFamiliar());
		 criteria.put("tipoVinculoRefFamiliar",f.getTipoVinculoRefFamiliar());
		 criteria.put("apellido1RefNoFamiliar",f.getApellido1RefNoFamiliar());
		 criteria.put("nombre1RefNoFamiliar",f.getNombre1RefNoFamiliar());
		 criteria.put("telefonoRefNoFamiliar",f.getTelefonoRefNoFamiliar());
		 criteria.put("celularRefNoFamiliar",f.getCelularRefNoFamiliar());
		 criteria.put("tipoVinculoRefNoFamiliar",f.getTipoVinculoRefNoFamiliar());
		 criteria.put("nombreEmpresaFiador",f.getNombreEmpresaFiador());
		 criteria.put("direccionEmpresaFiador",f.getDireccionEmpresaFiador());
		 criteria.put("cargoFiador",f.getCargoFiador());
		 criteria.put("tipoVinculoFiador",f.getTipoVinculoFiador());
		 criteria.put("requiereFactura",f.getRequiereFactura());
		 criteria.put("direccionContribuyente",f.getDireccionContribuyente());
		 criteria.put("barrioContribuyente",f.getBarrioContribuyente());
		 criteria.put("delegacionContribuyente",f.getDelegacionContribuyente());
		 criteria.put("ciudadContribuyente",f.getCiudadContribuyente());
		 criteria.put("departamentoContribuyente",f.getDepartamentoContribuyente());
		 criteria.put("codigoPostalContribuyente",f.getCodigoPostalContribuyente());
		 criteria.put("numeroContribuyente",f.getNumeroContribuyente());
		 criteria.put("direccionRefNoFamiliar",f.getDireccionRefNoFamiliar());
		 
		 criteria.put("nacionalidad",f.getNacionalidad());
		 criteria.put("tipoPersona",f.getTipoPersona());
		 criteria.put("origenIngreso",f.getOrigenIngreso());
		 criteria.put("territorioCorresponde",f.getTerritorioCorresponde());
		 criteria.put("direccionDomicilioIgualDireccionEntrega",f.getDireccionDomicilioIgualDireccionEntrega());
		 criteria.put("dirDomManzana",f.getDirDomManzana());
		 criteria.put("dirDomEtapa",f.getDirDomEtapa());
		 criteria.put("dirDomCallePrincipal",f.getDirDomCallePrincipal());
		 criteria.put("dirDomCalleSecundaria",f.getDirDomCalleSecundaria());
		 criteria.put("dirDomNumero",f.getDirDomNumero());
		 criteria.put("dirDomReferencia",f.getDirDomReferencia());
		 criteria.put("dirEntManzana",f.getDirEntManzana());
		 criteria.put("dirEntEtapa",f.getDirEntEtapa());
		 criteria.put("dirEntNumero",f.getDirEntNumero());
		 criteria.put("dirEntCallePrincipal",f.getDirEntCallePrincipal());
		 criteria.put("dirEntCalleSecundaria",f.getDirEntCalleSecundaria());
		 criteria.put("dirEntReferencia",f.getDirEntReferencia());
		 
		//sb PER-SiCC-2012-0460 ini
		 if(f.isMostrarCiudad() && StringUtils.isNotEmpty(f.getCodigoCiudadDomicilio())){
			if(f.getCodigoCiudadDomicilio().indexOf("__")!=-1){
				String [] sp = StringUtils.split(f.getCodigoCiudadDomicilio(), "__");
				criteria.put("codigoCiudadDomicilio", sp[1]);//000020__0003
				criteria.put("codigoCiudadDomicilioUbigeo", sp[1]);								
			}else{
				criteria.put("codigoCiudadDomicilio","");//000020__0003
				criteria.put("codigoCiudadDomicilioUbigeo", "");
			}
		 }
		 
		 if(f.isMostrarCiudad() && StringUtils.isNotEmpty(f.getCodigoCiudadEntrega())){
			if(f.getCodigoCiudadEntrega().indexOf("__")!=-1){
				String [] sp = StringUtils.split(f.getCodigoCiudadEntrega(), "__");
				criteria.put("codigoCiudadEntrega", sp[1]);
				criteria.put("codigoCiudadEntregaUbigeo",sp[1]);
			}else{
				criteria.put("codigoCiudadEntrega", "");
				criteria.put("codigoCiudadEntregaUbigeo","");
			}
		 }
			criteria.put("villaPoblacionDomicilio", f.getVillaPoblacionDomicilio());
			criteria.put("villaPoblacionEntrega", f.getVillaPoblacionEntrega());
		
			
			criteria.put("tipoMeta",f.getTipoMeta());
			criteria.put("descripcionMeta",f.getDescripcionMeta());
			criteria.put("montoMeta",f.getMontoMeta());
			
			criteria.put("oidMotiGes",f.getOidMotiGes());
			criteria.put("valObseGestion", f.getValObseGestion());  
			
		
			
		 //invocacion del web service boletin electronico
		 GenericoService serviceGen = (GenericoService) getBean("genericoService");
		 String indActivaWS = serviceGen.getParametroPais(f.getCodigoPais(), Constants.SISTEMA_OCR, Constants.OCR_IND_ACTIVA_WS_SOCCRED);
		 log.debug("indActivaWS "+indActivaWS);
			if(Constants.NUMERO_UNO.equals(indActivaWS)){
				
				if(Constants.PAIS_CLE.equals(f.getCodigoPais())){
					String user = usuario.getLogin();
					String host =  InetAddress.getLocalHost().getHostAddress();					 
					criteria = invocarBoletinComercial(criteria,f.getCodigoPais(),host,user);
				}else{
					//infocoorp 
					criteria = invocarInfocorp(criteria, f.getCodigoPais());
				}
			}
		//	
		//sb PER-SiCC-2012-0460 fin		 
			
		 ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		 procesoSTOEjecucionValidacionesService.updateSolicitudCredito(criteria);	
		 
		return true;
	}
	
	public void inicializarValores() throws Exception {
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setSalirPantalla(Constants.NO);
		Map criteriaOperacion = new HashMap();
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		MantenimientoSTOBloqueoControlService serviceSTOBloqueoCS = 
				(MantenimientoSTOBloqueoControlService)getBean("spusicc.mantenimientoSTOBloqueoControlService");
		
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		this.oidPais=reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		
		f.setOidPais(String.valueOf(oidPais));
		Map criteria = new HashMap();	
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    	
    	GestionDocumento gestion = (GestionDocumento) this.getRegistroSeleccionado();
		criteria.put("codigoPais",pais.getCodigo());
		
		
		f.setIndInformacionOk(procesoSTOEjecucionValidacionesService.getIndInformacionOk(criteria));
		f.setIndSitCrediticia(procesoSTOEjecucionValidacionesService.getIndSitCrediticia(criteria));
		f.setIndSinSalAmbas(procesoSTOEjecucionValidacionesService.getIndSinSalAmbas(criteria));
		
		criteria.put("codigoTipo",gestion.getDocumento());
		criteria.put("numDocumento",gestion.getNumeroDocumento());
		criteria.put("numLote",gestion.getLote());
		
		criteriaOperacion.put("codigoUsuario", usuario.getLogin());
    	criteriaOperacion.put("codigoAccion", Constants.STO_PANTALLA_EDITABLE);
    	criteriaOperacion.put("codigoTipo", gestion.getDocumento());    	
    	
    	this.editable=false;
    	if(procesoSTOEjecucionValidacionesService.getAccionEditable(criteriaOperacion)==null){
    		this.editable=false;
    	}
    	else
    		this.editable=true;    	
		
		List ListaSolicitudCredito=procesoSTOEjecucionValidacionesService.getSolicitudCredito(criteria);		
		SolicitudCredito solicitudCredito = (SolicitudCredito)ListaSolicitudCredito.get(0);
	
		
		this.maeFiadorNivel1List=ajaxService.getUnidadesGeograficas(oidPais, "");	
		this.maeClienteNivel1List=ajaxService.getUnidadesGeograficas(oidPais, "");	
		//Setea los combos de tipos de via
		this.stoTipoViaList= procesoSTOEjecucionValidacionesService.getTipoViaCliente();
		
		if(solicitudCredito.getCodDepaCliente()!= null ){			
			if(solicitudCredito.getCodDepaCliente().compareToIgnoreCase("")!=0 )
				this.maeClienteNivel2List=ajaxService.getUnidadesGeograficas(oidPais, solicitudCredito.getCodDepaCliente());			
		}
		if(solicitudCredito.getCodProvCliente()!=null){
			if(solicitudCredito.getCodProvCliente().compareToIgnoreCase("")!=0)
				this.maeClienteNivel3List=ajaxService.getUnidadesGeograficas(oidPais, solicitudCredito.getCodDepaCliente()+solicitudCredito.getCodProvCliente());				
		}		
		if(solicitudCredito.getCodDistCliente()!=null){
			if( solicitudCredito.getCodDistCliente().compareToIgnoreCase("")!=0)
				this.maeClienteNivel4List=ajaxService.getUnidadesGeograficas(oidPais, solicitudCredito.getCodDepaCliente()+solicitudCredito.getCodProvCliente()+solicitudCredito.getCodDistCliente());		
		}
		if(solicitudCredito.getCodDepaFiador()!= null ){
			if(solicitudCredito.getCodDepaFiador().compareToIgnoreCase("")!=0 )
				this.maeFiadorNivel2List=ajaxService.getUnidadesGeograficas(oidPais, solicitudCredito.getCodDepaFiador());		
		}
		if(solicitudCredito.getCodProvFiador()!=null){
			if(solicitudCredito.getCodProvFiador().compareToIgnoreCase("")!=0)
				this.maeFiadorNivel3List=ajaxService.getUnidadesGeograficas(oidPais, solicitudCredito.getCodDepaFiador()+solicitudCredito.getCodProvFiador());		
		}
		if(solicitudCredito.getCodDistFiador()!=null){
			if( solicitudCredito.getCodDistFiador().compareToIgnoreCase("")!=0)
				this.maeFiadorNivel4List=ajaxService.getUnidadesGeograficas(oidPais, solicitudCredito.getCodDepaFiador()+solicitudCredito.getCodProvFiador()+solicitudCredito.getCodDistFiador());
		}
		
		setSolicitudCredito(f,solicitudCredito);
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());
		
		// Seteando datos actuales de consultora
		criteria.put("codigoCliente",solicitudCredito.getCodCliente());
		//List listaDatosCliente = procesoSTOEjecucionValidacionesService.getDatosClienteActual(criteria);
		
		
		criteria.put("tipoDocumento",solicitudCredito.getTipoDocumento());
		criteria.put("numeroDocIdentidad",solicitudCredito.getNumDocuIdentidad());
		
		List listaDatosCliente = procesoSTOEjecucionValidacionesService.getDatosClienteActualNumDocIdent(criteria);
		
		Map datos = new HashMap();
		datos = (Map)listaDatosCliente.get(0);
		
		String nombreConsultora = (String)datos.get("nombreCliente");
		
		if (nombreConsultora == null){
			f.setIndMostrarDatos(Constants.NO);
		}
		else{
			f.setIndMostrarDatos(Constants.SI);
		}
				
		f.setCodigoConsultora((String)datos.get("codigo_consultora"));
		f.setNombreConsultora((String)datos.get("nombreCliente"));
		f.setStatusConsultora((String)datos.get("estatus"));
		f.setCampaniaUltConsultora((String)datos.get("campana"));
		f.setDireccionConsultora((String)datos.get("direccionCliente"));
		f.setUnidadGeoConsultora((String)datos.get("unidadGeografica"));
		f.setBloqueoConsultora((String)datos.get("bloqueo"));
		f.setRegionConsultora((String)datos.get("region"));
		f.setZonaConsultora((String)datos.get("zona"));
		f.setSeccionConsultora((String)datos.get("seccion"));
		f.setTerritorioConsultora((String)datos.get("territorio"));	
		f.setActivaConsultora((String)datos.get("activa"));	//
		
    	//-- Setea combo tipo documento		
		this.stoTipoDocList=procesoSTOEjecucionValidacionesService.getTipoDocumento();
		
		this.stoEstadoCivilList = procesoSTOEjecucionValidacionesService.getEstadoCivil();
		this.stoTipoPersonaList = procesoSTOEjecucionValidacionesService.getTipoPersonaCodigo();
		this.stoNacionalidadList = procesoSTOEjecucionValidacionesService.getNacionalidadCodigo();
		this.stoOrigenIngresoList = procesoSTOEjecucionValidacionesService.getOrigenIngresoCodigo();
		
		//Validarores
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		String validarCaractererNV1 = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_CARACTER_NOVALIDO1);
		String validarCaractererNV2 = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_CARACTER_NOVALIDO2);
		String validarCaractererNV3 = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_CARACTER_NOVALIDO3);
		
		if(validarCaractererNV1 != null) {
			f.setValidarCaracteres1(true);
			f.setCadenaCaracteresV1(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO1, "S"));
			f.setCadenaCaracteresNV1(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO1, "N"));
		}
		if(validarCaractererNV2 != null) {
			f.setValidarCaracteres2(true);
			f.setCadenaCaracteresV2(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO2, "S"));
			f.setCadenaCaracteresNV2(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO2, "N"));
		}
		if(validarCaractererNV3 != null) {
			f.setValidarCaracteres3(true);
			f.setCadenaCaracteresV3(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO3, "S"));
			f.setCadenaCaracteresNV3(clienteService.getCaracteresxModuloValidacion(Constants.MAE_VALID_CARACTER_NOVALIDO3, "N"));
		}
		
		criteria.put("codigoSistema", Constants.STO_TIPO_DOCUMENTO_SCC);
		criteria.put("nombreParametro", Constants.STO_INDICADOR_BLOQUEO_CAMPANHA);
		String valor = serviceSTOBloqueoCS.getParametroGenericoSistema(criteria);

		// Si es "S" Si se debe bloquear la campańa de ingreso
		if (valor.equals("S")){
			f.setEditableCampana(false);
		}else{
		// Si es "N" No se debe bloquear la campańa de ingreso	
			f.setEditableCampana(true);
		}
		
		//sb PER-SiCC-2012-0460 ini	
		String mostrarCiudad = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_CIUDAD);
		String mostrarVillaPoblacion = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.MAE_VALID_VILLA);		
		if(mostrarCiudad != null) {
			f.setMostrarCiudad(true);			
			this.maeCiudadList=ajaxService.getCiudadesByRegion(f.getCodDepaCliente());
			this.maeCiudadListEntr=ajaxService.getCiudadesByRegion(f.getCodDepaCliente());		
		}
		
		if(mostrarVillaPoblacion != null) 
			f.setMostrarVillaPoblacion(true);		

		//sb PER-SiCC-2012-0460 fin

		//inicio boletin comercial carga de limites 
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		String indActivaWS = serviceGen.getParametroPais(f.getCodigoPais(), Constants.SISTEMA_OCR, Constants.OCR_IND_ACTIVA_WS_SOCCRED);
		f.setIndActivaWS(indActivaWS);		
		
		try {
			f.setIndicadorMostrarFacturacionElectronica(false);	
			String indicadorMostrarFacturacionElectronica = serviceGen.getParametroPais(f.getCodigoPais(), Constants.SISTEMA_OCR, Constants.OCR_IND_FACTURACION_ELECTRONICA);
			if (Constants.SI.equals(indicadorMostrarFacturacionElectronica))
				f.setIndicadorMostrarFacturacionElectronica(true);	
		}
		catch(Exception e) {
			f.setIndicadorMostrarFacturacionElectronica(false);	
		}
		
		String limInfVerde = serviceGen.getParametroPais(pais.getCodigo(), Constants.SISTEMA_OCR, Constants.OCR_LIM_INF_VERDE);
		String limSupVerde = serviceGen.getParametroPais(pais.getCodigo(), Constants.SISTEMA_OCR, Constants.OCR_LIM_SUP_VERDE);
		
		String limInfAmari = serviceGen.getParametroPais(pais.getCodigo(), Constants.SISTEMA_OCR, Constants.OCR_LIM_INF_AMARI);
		String limSupAmari = serviceGen.getParametroPais(pais.getCodigo(), Constants.SISTEMA_OCR, Constants.OCR_LIM_SUP_AMARI);
		
		String limInfRojo = serviceGen.getParametroPais(pais.getCodigo(), Constants.SISTEMA_OCR, Constants.OCR_LIM_INF_ROJO);
		String limSupRojo = serviceGen.getParametroPais(pais.getCodigo(), Constants.SISTEMA_OCR, Constants.OCR_LIM_SUP_ROJO);
		
		f.setLimInfVerde(StringUtils.isEmpty(limInfVerde)?"0":limInfVerde);
		f.setLimSupVerde(StringUtils.isEmpty(limSupVerde)?"0":limSupVerde);
		
		f.setLimInfAmarillo(StringUtils.isEmpty(limInfAmari)?"0":limInfAmari);
		f.setLimSupAmarillo(StringUtils.isEmpty(limSupAmari)?"0":limSupAmari);
		
		f.setLimInfRojo(StringUtils.isEmpty(limInfRojo)?"0":limInfRojo);
		f.setLimSupRojo(StringUtils.isEmpty(limSupRojo)?"0":limSupRojo);
		//Color estado Comercial
		this.marcarEstadoComercial();
		//fin boletin comercial
		
		// Se mostrara la seccion de datos adicionales segun parametro 
		
		String indMuestraDatos = serviceGen.getParametroPais(f.getCodigoPais(), Constants.SISTEMA_OCR, Constants.OCR_MOSTRAR_INFO_ADICIONAL);
		try {
			if (indMuestraDatos.equals("N"))
				f.setIndMostrarDatos(Constants.NO);
		} catch (Exception e) {
			f.setIndMostrarDatos(Constants.NO);
		}		
		
	
		//inicio PER-SiCC-2013-0778 - @ghuertasa
		f.setIndViewMotiGest(gestion.getIndViewMotiGest());
		Map criteriaMostrarObservacion = new HashMap();
		criteriaMostrarObservacion.put("validacion", f.getValidacion());
		criteriaMostrarObservacion.put("codigoTipoDocumento", Constants.STO_TIPO_DOCUMENTO_SCC);
		
		
		 //inicio @ghuertasa
		
		//mostrar si es "S", no en caso contrario.
		if (f.getIndViewMotiGest()!=null){
			
			if(f.getIndViewMotiGest().equals(Constants.SI)){
	    		f.setMostrarMotivoObservacion(true);
	    		//obtener la lista para el llenado del combo.
	    		List lista= procesoSTOEjecucionValidacionesService.getMotivosGestionDocumento(criteriaMostrarObservacion);
	    		this.stoMotivosGestion=lista;	    		
	    	}else{
	    		f.setMostrarMotivoObservacion(false);
	    	}
		}
		//fin @ghuertasa
		
		// Indicador para mostrar pestańa de metas
		f.setMuestraMetas(serviceGen.getParametroPais(f.getCodigoPais(), Constants.SISTEMA_OCR, Constants.OCR_MOSTRAR_TAB_METAS));
		// Indicador buzon
		f.setMuestraIndicadorBuzon(serviceGen.getParametroPais(f.getCodigoPais(), Constants.SISTEMA_OCR, Constants.OCR_MOSTRAR_IND_BUZON));
		criteria.put("unidadAdministrativa", f.getUnidadAdministrativa());
		f.setIndicadorBuzon(procesoSTOEjecucionValidacionesService.getIndicadorTerritorioBuzon(criteria));
		
		
		//Indicador Lider Recomendante
		f.setIndicadorLiderRecomendante(false);
		MantenimientoRECDigitacionCDRService mantenimientoRECDigitacionCDRService = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		Map params = new HashMap();
		params.put("codigoPais", f.getCodigoPais());
		params.put("codPara", Constants.SCC_NOMBRE_LIOER_RECOMENDANTE);
		String parametro = mantenimientoRECDigitacionCDRService.getValorParam(params);
		if (StringUtils.equals(Constants.SI, parametro))
			f.setIndicadorLiderRecomendante(true);
		
		ParametroPais paramPais = new ParametroPais();		
		ParametroPais resultPais = new ParametroPais();
		paramPais.setCodigoPais(f.getCodigoPais());
		paramPais.setCodigoSistema(Constants.SISTEMA_OCR);
		paramPais.setCodigoParametro(Constants.OCR_MOSTRAR_IND_CAMPOS_ADICIONALES_SCC);		
		paramPais.setNombreParametro(Constants.OCR_NOM_PARA_CAMPOS_ADICIONALES_SCC);
		List lstParametros = serviceGen.getParametrosPais(paramPais);
		if(lstParametros.size()>0){
			resultPais =(ParametroPais)lstParametros.get(0);
			String valorPara=resultPais.getValorParametro();
			f.setMuestraIndicadorCamposAdicionales(valorPara);
		}
		
		
		Map criteriaOCR = new HashMap();
		criteriaOCR.put("codigoPais", f.getCodigoPais());
		criteriaOCR.put("codigoParametro", Constants.STO_VALID_NUM);
		String datoNumerico = mantenimientoRECDigitacionCDRService.getSTOParametroGeneralOCR(criteriaOCR);
		if(StringUtils.equals(datoNumerico, Constants.NUMERO_UNO))
			f.setIndValidaDatosNumericos(true);
		else
			f.setIndValidaDatosNumericos(false);
			
		
	}
	
	@Override
	protected void setViewAtributes() throws Exception {		
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=true;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonModificar=false;	
		this.bIndSinSaldoAmbasMarcas=false;
		this.bIndSituacionCrediticia=false;
		this.bIndTelefonoOK=false;		
		
		
	} 
	
	
	//CLIENTE
	//leer Provincia- cuidad-ciudad Entrega
	public void loadNivel2CT(ValueChangeEvent val) {		
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;		
		if (!val.getNewValue().equals(null) && !val.getNewValue().equals("")) {
			String depa = val.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeClienteNivel2List=ajax.getUnidadesGeograficas(this.oidPais, depa);			
			f.setCodProvCliente("");
			f.setCodDistCliente("");
			f.setCodSectCliente("");
			this.maeClienteNivel3List=null;
			this.maeClienteNivel4List=null;
			this.maeCiudadList= ajax.getCiudadesByRegion(depa);
			f.setCodigoCiudadDomicilio("");
			this.maeCiudadListEntr= ajax.getCiudadesByRegion(depa);
			f.setCodigoCiudadEntrega("");
		}else {			
			this.maeClienteNivel2List = null;
			f.setCodProvCliente("");
			f.setCodDistCliente("");
			f.setCodSectCliente("");
			this.maeCiudadList=null;
			f.setCodigoCiudadDomicilio("");
			this.maeCiudadListEntr=null;
			f.setCodigoCiudadEntrega("");
		}		
	}
	
	//leer Distrito
	public void loadNivel3CT(ValueChangeEvent val) {			
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;		
		if (!val.getNewValue().equals(null) && !val.getNewValue().equals("")) {
			String provincia = val.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeClienteNivel3List=ajax.getUnidadesGeograficas(this.oidPais, provincia);			 
			f.setCodDistCliente("");
			f.setCodSectCliente("");
			this.maeClienteNivel4List=null;
		}else {			
			this.maeClienteNivel3List = null;			
			f.setCodDistCliente("");
			f.setCodSectCliente("");			
		}		
	}
	
	//leer Sector
	public void loadNivel4(ValueChangeEvent val) {				
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;		
		if (!val.getNewValue().equals(null) && !val.getNewValue().equals("")) {
			String distrito = val.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeClienteNivel4List=ajax.getUnidadesGeograficas(this.oidPais,distrito);			
			f.setCodSectCliente("");
		}else {			
			this.maeClienteNivel4List = null;				
			f.setCodSectCliente("");			
		}		
	}
	
	
	//FIADOR
	//leer Provincia
	public void loadProvinciaFiador(ValueChangeEvent val) {					
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;		
		if (!val.getNewValue().equals(null) && !val.getNewValue().equals("")) {
			String depa = val.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeFiadorNivel2List=ajax.getUnidadesGeograficas(this.oidPais, depa);			
			f.setCodProvFiador("");
			f.setCodDistFiador("");
			f.setCodSectFiador("");	
			this.maeFiadorNivel3List=null;
			this.maeFiadorNivel4List=null;
		}else {			
			this.maeFiadorNivel2List = null;
			f.setCodProvFiador("");
			f.setCodDistFiador("");
			f.setCodSectFiador("");				
		}		
	}
		
	//leer Distrito
	public void loadDistritoFiador(ValueChangeEvent val) {				
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;		
		if (!val.getNewValue().equals(null) && !val.getNewValue().equals("")) {
			String provincia = val.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeFiadorNivel3List=ajax.getUnidadesGeograficas(this.oidPais,provincia);	
			f.setCodDistFiador("");
			f.setCodSectFiador("");			
			this.maeFiadorNivel4List=null;
		}else {			
			this.maeFiadorNivel3List = null;			
			f.setCodDistFiador("");
			f.setCodSectFiador("");				
		}		
	}
		
	//leer Sector
	public void loadSectorFiador(ValueChangeEvent val) {		
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;		
		if (!val.getNewValue().equals(null) && !val.getNewValue().equals("")) {
			String distrito = val.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeFiadorNivel4List=ajax.getUnidadesGeograficas(this.oidPais,distrito);			
			f.setCodSectFiador("");
		}else {			
			this.maeFiadorNivel4List = null;				
			f.setCodSectFiador("");			
		}		
	}	
	
	
	//salir de la pantalla	
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	//POPUP --busqueda Clientes
	@SuppressWarnings("static-access")
	@Override
	protected void setInvocarPopup(String accion) {
		if (accion.equals(this.POPUP_CLIENTE)){ 
			this.mostrarPopupCliente = true;
		}
	}	
	
	@SuppressWarnings("static-access")
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {			
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;
		if (accion.equals(this.POPUP_CLIENTE)) {
			this.busquedaHIPClientePOPUPSearchAction.verificarRegistro(event);			
			if (this.busquedaHIPClientePOPUPSearchAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaHIPClientePOPUPSearchAction.getBeanRegistroSeleccionado();
				MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
				f.setCodClienteRetenido(cliente.get("codigoCliente").toString());
				this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);					
				this.formBusqueda=f;
			}
		}	
	}
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupCliente = false;		
		this.busquedaHIPClientePOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	public void marcarEstadoComercial(){
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
		this.mostrarAmarillo=false;
		this.mostrarRojo=false;
		this.mostrarVerde=false;
		if(StringUtils.isNotBlank(f.getLimSupVerde()) && StringUtils.isNotBlank(f.getLimInfVerde()) && StringUtils.isNotBlank(f.getLimSupRojo()) &&
				StringUtils.isNotBlank(f.getLimInfRojo())	&& StringUtils.isNotBlank(f.getLimSupAmarillo()) && 
				StringUtils.isNotBlank(f.getLimInfAmarillo()) && StringUtils.isNotBlank(f.getEstadoComercial())){
		int superVerde=Integer.parseInt(f.getLimSupVerde());
		int infeVerde=Integer.parseInt(f.getLimInfVerde());
		int superRojo=Integer.parseInt(f.getLimSupRojo());
		int infeRojo=Integer.parseInt(f.getLimInfRojo());
		int superAma=Integer.parseInt(f.getLimSupAmarillo());
		int infeAma=Integer.parseInt(f.getLimInfAmarillo());
		int estadoComercial=Integer.parseInt(f.getEstadoComercial());
		if(superVerde>=estadoComercial && estadoComercial>=infeVerde)
			this.mostrarVerde=true;
		if(superRojo>=estadoComercial && estadoComercial>=infeRojo)
			this.mostrarRojo=true;
		if(superAma>=estadoComercial && estadoComercial>=infeAma)
			  this.mostrarAmarillo=true;			  
		}
	}
	
	private  void setSolicitudCredito(MantenimientoSTOSolicitudCreditoForm f,SolicitudCredito solicitudCredito) throws Exception{		
		f.setCodigoPais(solicitudCredito.getCodPais());
		f.setNumLote(solicitudCredito.getNumLote());
		f.setCodCliente(solicitudCredito.getCodCliente());
		f.setCodClienteRetenido(solicitudCredito.getCodClienteRetenido());
		f.setCodCompania(solicitudCredito.getCodCompania());
		f.setCodDepaCliente(solicitudCredito.getCodDepaCliente());
		f.setCodDepaFiador(solicitudCredito.getCodDepaFiador());
		f.setCodDistCliente(solicitudCredito.getCodDepaCliente()+solicitudCredito.getCodProvCliente()+solicitudCredito.getCodDistCliente());
		f.setCodDistFiador(solicitudCredito.getCodDepaFiador()+solicitudCredito.getCodProvFiador()+solicitudCredito.getCodDistFiador());
		f.setCodDocuFiador(solicitudCredito.getCodDocuFiador());
		f.setCodPeriodo(solicitudCredito.getCodPeriodo());
		f.setCodPremio(solicitudCredito.getCodPremio());
		f.setCodProvCliente(solicitudCredito.getCodDepaCliente()+solicitudCredito.getCodProvCliente());
		f.setCodProvFiador(solicitudCredito.getCodDepaFiador()+solicitudCredito.getCodProvFiador());
		f.setCodSectCliente(solicitudCredito.getCodDepaCliente()+solicitudCredito.getCodProvCliente()+solicitudCredito.getCodDistCliente()+solicitudCredito.getCodSectCliente());
		f.setCodSectFiador(solicitudCredito.getCodDepaFiador()+solicitudCredito.getCodProvFiador()+solicitudCredito.getCodDistFiador()+solicitudCredito.getCodSectFiador());
		f.setCodTipoDocumento(solicitudCredito.getCodTipoDocumento());
		f.setFechaNacimiento(solicitudCredito.getFechaNacimiento());
		
		f.setFechaNacimientoDate(null);
		if(StringUtils.isNotBlank(f.getFechaNacimiento()))
			f.setFechaNacimientoDate(DateUtil.convertStringToDate(f.getFechaNacimiento()));
		f.setFechaProceso(solicitudCredito.getFechaProceso());
		
		f.setFechaProcesoDate(null);
		if(StringUtils.isNotBlank(f.getFechaProceso()))
			f.setFechaProcesoDate(DateUtil.convertStringToDate(f.getFechaProceso()));
		
		f.setIndEstaCivil(solicitudCredito.getIndEstaCivil());
		f.setIndEstaProceso(solicitudCredito.getIndEstaProceso());
		f.setIndMotivoRechazo(solicitudCredito.getIndMotivoRechazo());
		f.setIndNivelEducativo(solicitudCredito.getIndNivelEducativo());
		f.setIndVentaDirecta(solicitudCredito.getIndVentaDirecta());
		f.setNumDireCliente(solicitudCredito.getNumDireCliente());
		f.setNumDireFiador(solicitudCredito.getNumDireFiador());
		f.setNumDocuIdentidad(solicitudCredito.getNumDocuIdentidad());
		f.setNumDocumento(solicitudCredito.getNumDocumento());
		f.setNumRUC(solicitudCredito.getNumRUC());
		f.setTipoDocuFiador(solicitudCredito.getTipoDocuFiador());
		f.setTipoDocumento(solicitudCredito.getTipoDocumento());
		f.setTipoIngreso(solicitudCredito.getTipoIngreso());
		f.setTipoViaCliente(solicitudCredito.getTipoViaCliente());
		f.setTipoViaFiador(solicitudCredito.getTipoViaFiador());
		f.setUnidadAdministrativa(solicitudCredito.getUnidadAdministrativa());
		f.setValApellido1(solicitudCredito.getValApellido1());
		f.setValApellido1Fiador(solicitudCredito.getValApellido1Fiador());
		f.setValApellido2(solicitudCredito.getValApellido2());
		f.setValApellido2Fiador(solicitudCredito.getValApellido2Fiador());
		f.setValBarrCliente(solicitudCredito.getValBarrCliente());
		f.setValCeluCliente(solicitudCredito.getValCeluCliente());
		f.setValCeluFiador(solicitudCredito.getValCeluFiador());
		f.setValCiudCliente(solicitudCredito.getValCiudCliente());
		f.setValCiudFiador(solicitudCredito.getValCiudFiador());
		f.setValDepaCliente(solicitudCredito.getValDepaCliente());
		f.setValDepaFiador(solicitudCredito.getValDepaFiador());
		f.setValDirecCliente(solicitudCredito.getValDirecCliente());
		f.setValDireFiador(solicitudCredito.getValDireFiador());
		f.setValNombre1(solicitudCredito.getValNombre1());
		f.setValNombre1Fiador(solicitudCredito.getValNombre1Fiador());
		f.setValNombre2(solicitudCredito.getValNombre2());
		f.setValNombre2Fiador(solicitudCredito.getValNombre2Fiador());
		f.setValNombreVia(solicitudCredito.getValNombreVia());
		f.setValNomViaFiador(solicitudCredito.getValNomViaFiador());
		f.setValRegionArribo(solicitudCredito.getValRegionArribo());
		f.setValTelfCliente(solicitudCredito.getValTelfCliente());
		f.setValTelfFiador(solicitudCredito.getValTelfFiador());
		f.setValTelfTrabaFiador(solicitudCredito.getValTelfTrabaFiador());
		f.setValTelfTrabajo(solicitudCredito.getValTelfTrabajo());
		f.setValZonaArribo(solicitudCredito.getValZonaArribo());
		f.setNumSecuencia(solicitudCredito.getNumSecuencia());
		f.setValMailCliente(solicitudCredito.getValMailCliente());
		
		f.setIndTelefonoOK(solicitudCredito.getIndicadorTelefonoOK());
		f.setIndSituacionCrediticia(solicitudCredito.getIndicadorSituacionCrediticia());
		f.setIndSinSaldoAmbasMarcas(solicitudCredito.getIndicadorSinSaldoAmbasMarcas());	
		if(StringUtils.equals(f.getIndInformacionOk(), "1")){
			if(StringUtils.equals(f.getIndTelefonoOK(),"0"))
				this.bIndTelefonoOK=false;
			else
				this.bIndTelefonoOK=true;
		}
		if(StringUtils.equals(f.getIndSitCrediticia(), "1")){
			if(StringUtils.equals(f.getIndSituacionCrediticia(),"0"))
				this.bIndSituacionCrediticia=false;
			else
				this.bIndSituacionCrediticia=true;
		}
		if(StringUtils.equals(f.getIndSinSalAmbas(), "1"))
			if(StringUtils.equals(f.getIndSinSaldoAmbasMarcas(), "0"))
				this.bIndSinSaldoAmbasMarcas=false;
			else
				this.bIndSinSaldoAmbasMarcas=true;	

		
		f.setDelegacionCliente(solicitudCredito.getDelegacionCliente());
		f.setCodigoPostalCliente(solicitudCredito.getCodigoPostalCliente());
		f.setDireccionEntrega(solicitudCredito.getDireccionEntrega());
		f.setBarrioEntrega(solicitudCredito.getBarrioEntrega());
		f.setDelegacionEntrega(solicitudCredito.getDelegacionEntrega());
		f.setDepartamentoEntrega(solicitudCredito.getDepartamentoEntrega());
		f.setTelefonoEntrega(solicitudCredito.getTelefonoEntrega());
		f.setCelularEntrega(solicitudCredito.getCelularEntrega());
		f.setApellido1RefFamiliar(solicitudCredito.getApellido1RefFamiliar());
		f.setNombre1RefFamiliar(solicitudCredito.getNombre1RefFamiliar());
		f.setDireccionRefFamiliar(solicitudCredito.getDireccionRefFamiliar());
		f.setBarrioRefFamiliar(solicitudCredito.getBarrioRefFamiliar());
		f.setDelegacionRefFamiliar(solicitudCredito.getDelegacionRefFamiliar());
		f.setCiudadRefFamiliar(solicitudCredito.getCiudadRefFamiliar());
		f.setDepartamentoRefFamiliar(solicitudCredito.getDepartamentoRefFamiliar());
		f.setTelefonoRefFamiliar(solicitudCredito.getTelefonoRefFamiliar());  
		f.setCelularRefFamiliar(solicitudCredito.getCelularRefFamiliar());  
		f.setTipoVinculoRefFamiliar(solicitudCredito.getTipoVinculoRefFamiliar());  
		f.setApellido1RefNoFamiliar(solicitudCredito.getApellido1RefNoFamiliar());  
		f.setNombre1RefNoFamiliar(solicitudCredito.getNombre1RefNoFamiliar());  
		f.setTelefonoRefNoFamiliar(solicitudCredito.getTelefonoRefNoFamiliar());  
		f.setCelularRefNoFamiliar(solicitudCredito.getCelularRefNoFamiliar());  
		f.setTipoVinculoRefNoFamiliar(solicitudCredito.getTipoVinculoRefNoFamiliar());
		f.setNombreEmpresaFiador(solicitudCredito.getNombreEmpresaFiador());  
		f.setDireccionEmpresaFiador(solicitudCredito.getDireccionEmpresaFiador());  
		f.setCargoFiador(solicitudCredito.getCargoFiador());  
		f.setTipoVinculoFiador(solicitudCredito.getTipoVinculoFiador());  
		f.setRequiereFactura(solicitudCredito.getRequiereFactura());  
		f.setDireccionContribuyente(solicitudCredito.getDireccionContribuyente());  
		f.setBarrioContribuyente(solicitudCredito.getBarrioContribuyente());  
		f.setDelegacionContribuyente(solicitudCredito.getDelegacionContribuyente());
		f.setCiudadContribuyente(solicitudCredito.getCiudadContribuyente());  
		f.setDepartamentoContribuyente(solicitudCredito.getDepartamentoContribuyente());
		f.setCodigoPostalContribuyente(solicitudCredito.getCodigoPostalContribuyente());
		f.setNumeroContribuyente(solicitudCredito.getNumeroContribuyente());   
		f.setDireccionRefNoFamiliar(solicitudCredito.getDireccionRefNoFamiliar());		
		f.setCodSexoCliente(solicitudCredito.getCodSexoCliente());
		
		//sb PER-SiCC-2012-0460 ini
		if(StringUtils.isNotEmpty(solicitudCredito.getCodigoCiudadDomicilio())){
			f.setCodigoCiudadDomicilio(solicitudCredito.getCodDepaCliente()+"__"+solicitudCredito.getCodigoCiudadDomicilio());
			f.setCodigoCiudadDomicilioUbigeo(solicitudCredito.getCodDepaCliente()+"__"+solicitudCredito.getCodigoCiudadDomicilio());
		}
		if(StringUtils.isNotEmpty(solicitudCredito.getCodigoCiudadEntrega())){
			f.setCodigoCiudadEntrega(solicitudCredito.getCodDepaCliente()+"__"+solicitudCredito.getCodigoCiudadEntrega());
			f.setCodigoCiudadEntregaUbigeo(solicitudCredito.getCodDepaCliente()+"__"+solicitudCredito.getCodigoCiudadEntregaUbigeo());
		}				
		f.setVillaPoblacionDomicilio(solicitudCredito.getVillaPoblacionDomicilio());
		f.setVillaPoblacionEntrega(solicitudCredito.getVillaPoblacionEntrega());
		//sb PER-SiCC-2012-0460 fin
		//sb inicio boletin
		f.setMontoInfoComercial(solicitudCredito.getMontoInfoComercial()!= null?String.valueOf(solicitudCredito.getMontoInfoComercial()):"0.00");
		f.setEstadoComercial(solicitudCredito.getEstadoComercial());
		//sb fin boletin
		
		f.setNombresInfocorp(solicitudCredito.getNombresInfocorp());	
		f.setExplicacionInfocorp(solicitudCredito.getExplicacionInfocorp());
		
		f.setTipoMeta(solicitudCredito.getTipoMeta());
		f.setMontoMeta(solicitudCredito.getMontoMeta());
		f.setDescripcionMeta(solicitudCredito.getDescripcionMeta());
		f.setOidMotiGes(solicitudCredito.getOidMotiGes());
		f.setValObseGestion(solicitudCredito.getValObseGestion());
		f.setCodigoLiderRecomendante(solicitudCredito.getCodigoLiderRecomendante());
		f.setIndicadorFacturacionElectronica(solicitudCredito.getIndicadorFacturacionElectronica());
		f.setIndicadorFacturacionElectronicaTempo(solicitudCredito.getIndicadorFacturacionElectronica());
		if(StringUtils.equals(f.getIndicadorFacturacionElectronica(), "S"))
			this.bIndFacturacionElectronica=false;
		else
			this.bIndFacturacionElectronica=true;
		
		f.setIndEstaCivil(solicitudCredito.getIndEstaCivil());
		f.setNacionalidad(solicitudCredito.getNacionalidad());
		f.setTipoPersona(solicitudCredito.getTipoPersona());
		f.setOrigenIngreso(solicitudCredito.getOrigenIngreso());
		f.setTerritorioCorresponde(solicitudCredito.getTerritorioCorresponde());
		f.setDireccionDomicilioIgualDireccionEntrega(solicitudCredito.getDireccionDomicilioIgualDireccionEntrega());
		f.setDirDomManzana(solicitudCredito.getDirDomManzana());
		f.setDirDomEtapa(solicitudCredito.getDirDomEtapa());
		f.setDirDomCallePrincipal(solicitudCredito.getDirDomCallePrincipal());
		f.setDirDomCalleSecundaria(solicitudCredito.getDirDomCalleSecundaria());
		f.setDirDomNumero(solicitudCredito.getDirDomNumero());
		f.setDirDomReferencia(solicitudCredito.getDirDomReferencia());
		f.setDirEntManzana(solicitudCredito.getDirEntManzana());
		f.setDirEntEtapa(solicitudCredito.getDirEntEtapa());
		f.setDirEntNumero(solicitudCredito.getDirEntNumero());
		f.setDirEntCallePrincipal(solicitudCredito.getDirEntCallePrincipal());
		f.setDirEntCalleSecundaria(solicitudCredito.getDirEntCalleSecundaria());
		f.setDirEntReferencia(solicitudCredito.getDirEntReferencia());
	}
	
	private Map invocarBoletinComercial(Map criteria,String codigoPais,
			String host, String usuario) throws Exception{
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		String urlWSOCR = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_WS_SOCCRED);
		Integer port = new Integer(serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_PORT_SOCCRED));
		String hostParametro = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_HOST_SOCCRED);
		String serie="";
		String zona="";		
		//	
		if(StringUtils.isEmpty(hostParametro)) hostParametro = host;
		
		IConsultaBelcorpservice locator = new IConsultaBelcorpserviceLocator();
		log.debug("URL ws Boletin electronico >>> "+urlWSOCR);
		IConsultaBelcorp service =locator.getIConsultaBelcorpPort(new java.net.URL(urlWSOCR));
		log.debug("service ws conectado "+service);
		//map.put("numLoteSTO", "00005219");//prueba
		log.debug("host "+hostParametro);
		log.debug("port "+port);
		String rut = (String)criteria.get("numDocuIdentidad");
		log.debug("rut "+rut);
			//invocamos ws
			String res=null;
			try{
				if(StringUtils.isNotEmpty(rut))
					res= service.getEvaluacionBelcorp(hostParametro, port, rut, serie, zona, usuario);
				
			}catch(Exception e){
				log.debug("error conexion web service "+ e.getMessage());
				res=null;
				throw new Exception(
						"Error en conectarse al servicio web boletin electronico: "
								+ e.getMessage());
			}
			log.debug("res "+res);
			if(StringUtils.isNotEmpty(res)){
				String [] split = StringUtils.split(res,"|");//reporte|montoBic|MontoMic|MontoInfoCom|Estado|Mensaje|Error
				if(split.length == 7){
					log.debug("split "+split[3]+" --- "+split[4]+"---"+split[6]);
					criteria.put("montoInfoCom", split[3]);
					criteria.put("estado", split[4]);
					criteria.put("error", split[6]);
				}
			}
			//
		
		return criteria;
	}
	

	/**
	 * Invoca el servicio web infocorp
	 * @param criteria
	 * @param codigoPais
	 * @return
	 */
	private Map invocarInfocorp(Map criteria, String codigoPais) throws Exception {
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		String urlWSOCR = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_WS_SOCCRED);
		//Integer port = new Integer(serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_PORT_SOCCRED));
		//String hostParametro = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_URL_HOST_SOCCRED);
		//	
		//if(StringUtils.isEmpty(hostParametro)) hostParametro = host;
		String usuarioEquifax = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_USU_EQUI_SOCCRED);
		String claveEquifax = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_PWD_EQUI_SOCCRED);
		String canalEquifax = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_CANAL_EQUI_SOCCRED);
		String modeloEquifax = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_MODEL_EQUI_SOCCRED);
		String nombreDato = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_NOM_DATO_EQUI_SOCCRED);
		String tipoDato = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_TIPO_DATO_EQUI_SOCCRED);
		String valorDato = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_VALOR_DATO_EQUI_SOCCRED);
		String tipoDocEqui = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_OCR, Constants.OCR_TIPO_DOC_EQUI_SOCCRED);
		
		WebServiceEquifax locator = new WebServiceEquifaxLocator();
		log.debug("URL ws Boletin electronico infocorp  >>> "+urlWSOCR);
		WS00230301Endpoint serviceEquifax =locator.getWS00230301EndpointPort(new java.net.URL(urlWSOCR));
		log.debug("service ws conectado "+serviceEquifax);
		
		Header header = new Header();
		header.setCanal(canalEquifax);
		header.setClave(claveEquifax);
		header.setUsuario(usuarioEquifax);
		header.setModelo(modeloEquifax);
		EstructuraServicio16 estructuraServicio16_1 = new EstructuraServicio16();
		estructuraServicio16_1.setHeader(header );
		Integrante[] integrantesServicio = new Integrante[1];
		
		Dato datosEntrada = new Dato();
		datosEntrada.setNombreDato(nombreDato);
		datosEntrada.setTipoDato(tipoDato);
		datosEntrada.setValor(valorDato);
		Integrante integrante=new Integrante();
		integrante.setDatosEntrada(new Dato[]{datosEntrada});
		integrantesServicio[0] = integrante;
		estructuraServicio16_1.setIntegrantesServicio(integrantesServicio );
		
		//
		String rut = (String)criteria.get("numDocuIdentidad");
		log.debug("rut "+rut);
			//invocamos ws
			String res=null;
			try{
				if(StringUtils.isNotEmpty(rut)){
					integrante.setNumeroDocumento(rut);
					integrante.setTipoDocumento(StringUtils.isNotEmpty(tipoDocEqui)?Integer.parseInt(tipoDocEqui):1);//por default 1:dni
					res= serviceEquifax.consultaServicio16(estructuraServicio16_1);
				}
				
			}catch(Exception e){
				log.debug("error conexion web service "+ e.getMessage());
				res=null;
				throw new Exception(
						"Error en conectarse al servicio web infocorp: "
								+ e.getMessage());
			}
			log.debug("res "+res);

			if(StringUtils.isNotEmpty(res) && res.length()>0){
				int index =res.indexOf(Constants.EQUIFAX_PATRON_INICIAL);
				if(index!=-1){
					int indexf =res.indexOf(Constants.EQUIFAX_PATRON_FINAL);					
					String valor= res.substring(index + Constants.EQUIFAX_PATRON_INICIAL.length(), indexf);
					log.debug("valor "+valor);								
					criteria.put("estado", Constants.EQUIFAX_ESTADO_APROBADO.equals(valor)?"1":"12");//definidos en el rcr	
					
					// NUEVO										
					
					String nombresInfocorp = "";
					String apellidoMaternoInfocorp = "";
					String apellidoPaternoInfocorp = "";
					String explicacionInfocorp = "";
					
					try {
												
						int indexExini =res.indexOf(Constants.EQUIFAX_PATRON_INICIAL,indexf); // <Valor>
						if(indexExini!=-1){
							int indexExfin =res.indexOf(Constants.EQUIFAX_PATRON_FINAL,indexf + Constants.EQUIFAX_PATRON_INICIAL.length());					
							explicacionInfocorp = res.substring(indexExini + Constants.EQUIFAX_PATRON_INICIAL.length(), indexExfin);
							log.debug("explicacionInfocorp "+explicacionInfocorp);									
						}	
						
						int indexAPini =res.indexOf(Constants.EQUIFAX_PATRON_APE_PAT_INICIAL); // <ApellidoPaterno>
						if(indexAPini!=-1){
							int indexAPfin =res.indexOf(Constants.EQUIFAX_PATRON_APE_PAT_FINAL);					
							apellidoPaternoInfocorp = res.substring(indexAPini + Constants.EQUIFAX_PATRON_APE_PAT_INICIAL.length(), indexAPfin);
							log.debug("apellidoPaternoInfocorp "+apellidoPaternoInfocorp);									
						}	
						
						int indexAMini =res.indexOf(Constants.EQUIFAX_PATRON_APE_MAT_INICIAL); // <ApellidoMaterno>
						if(indexAMini!=-1){
							int indexAMfin =res.indexOf(Constants.EQUIFAX_PATRON_APE_MAT_FINAL);	 // </ApellidoMaterno>				
							apellidoMaternoInfocorp = res.substring(indexAMini + Constants.EQUIFAX_PATRON_APE_MAT_INICIAL.length(), indexAMfin); 
							log.debug("apellidoMaternoInfocorp "+apellidoMaternoInfocorp);								
						}
						
						int indexNombresini =res.indexOf(Constants.EQUIFAX_PATRON_NOMBRES_INICIAL); // <Nombres>
						if(indexNombresini!=-1){
							int indexNombresfin =res.indexOf(Constants.EQUIFAX_PATRON_NOMBRES_FINAL);	// </Nombres>				
							nombresInfocorp = res.substring(indexNombresini + Constants.EQUIFAX_PATRON_NOMBRES_INICIAL.length(), indexNombresfin); 
							log.debug("nombresInfocorp "+nombresInfocorp);								
						}
																														
					} catch (Exception e) {
						log.debug("Error : " + e.getMessage());
					}
					
					criteria.put("nombresInfocorp", apellidoPaternoInfocorp + " " + apellidoMaternoInfocorp + ", " +nombresInfocorp);
					criteria.put("explicacionInfocorp", explicacionInfocorp);	
					//
				}
			}
		
			//
		return criteria;
	}	

	// Abrir el popup de busqueda
	public void abrirPrimeraVentana(ActionEvent event) {
		try {
			MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
			MantenimientoMAEInformacionClienteForm searchForm=(MantenimientoMAEInformacionClienteForm )mantMAEInformacionClienteAction.getFormBusqueda();
			searchForm.setCodigoClienteBuscar(f.getCodigoConsultora());	
			searchForm.setIndicadorSolicitudCredito("1");
			this.mantMAEInformacionClienteAction.setCodCliente(f.getCodigoConsultora());
			this.mantMAEInformacionClienteAction.setPaginaPadre("mantenimientoSTOSolicitudCreditoForm");
			//this.mantMAEInformacionClienteAction.setEsOtraPantalla(true);			
			this.mantMAEInformacionClienteAction.find();
			this.mantMAEInformacionClienteAction.setHijoPopup(true);
			this.redireccionarPagina("mantenimientoMAEInformacionClienteFormSTO");			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
				
		}
	}
	
	public void abrirSegundaVentana(ActionEvent event) {
		try {
			MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
			MantenimientoMAEBloqueoDesbloqueoClienteSearchForm searchForm=(MantenimientoMAEBloqueoDesbloqueoClienteSearchForm)mantMAEBloqueoDesbloqueoClienteAction.getFormBusqueda();			
			searchForm.setCodigoCliente(f.getCodigoConsultora());
			searchForm.setIndicadorSolicitudCredito("1");
			this.mantMAEBloqueoDesbloqueoClienteAction.setOcultarInfo(true);
			this.mantMAEBloqueoDesbloqueoClienteAction.setMostrarBotonBuscar(false);
			this.mantMAEBloqueoDesbloqueoClienteAction.find();
			this.redireccionarPagina("mantenimientoMAEBloqueoDesbloqueoClienteListSTO");			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
				
		}
	}
	
	public void abrirTerceraVentana(ActionEvent event) {
		try {
			MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;	
			this.consultaMAEClienteSearchAction.setCodigoConsultora(f.getCodigoConsultora());
			this.consultaMAEClienteSearchAction.iniciaValores();
			this.consultaMAEClienteSearchAction.setPaginaPadre("mantenimientoSTOSolicitudCreditoForm");
			this.redireccionarPagina("mantenimientoMAEModificacionClienteFormSTO");			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
				
		}
	}
	
	//validaciones
	
	@Override
	public String setValidarMantenimiento(){
		
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
		
		log.debug("Entering validarDocumentoIdentidadSTO" );
		if(StringUtils.isNotBlank(f.getNumDocuIdentidad())){
	    	if(StringUtils.isNotBlank(f.getCodigoPais()) && StringUtils.isNotBlank(f.getTipoDocumento())){
	    		AjaxService ajax = (AjaxService) getBean("ajaxService");
				String datos=ajax.validarDocumentoIdentidadSTO(f.getCodigoPais(), f.getTipoDocumento(),f.getNumDocuIdentidad());
				if(StringUtils.isNotBlank(datos))
					return datos;
			}
	    }
		
		log.debug("Entering validarCamposEntradaCaracteres1" );
	    if(!validarCamposEntradaCaracteres1())
	    	return this.msjeCampo1;	   
	    
	    log.debug("Entering validarCamposEntradaCaracteres2" );
	    if(!validarCamposEntradaCaracteres2())
	    	return this.msjeCampo2;
	    
	    log.debug("Entering validarCamposEntradaCaracteres3" );
	    if(!validarCamposEntradaCaracteres3())
	    	return this.msjeCampo3;
	   
		return "";
	}
	public boolean validarCamposEntradaCaracteres1() {
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
		String campos1="";
		String cadena=f.getCadenaCaracteresNV1();
		if(!f.isValidarCaracteres1())
			return true;
		if(f.isValidarCaracteres1()){	
			
			boolean b_codCompania              = validarTextoCaracteresNoValidos(cadena, f.getCodCompania());
			boolean b_codCliente               = validarTextoCaracteresNoValidos(cadena, f.getCodCliente());
			boolean b_numDocumento             = validarTextoCaracteresNoValidos(cadena, f.getNumDocumento());
			boolean b_unidadAdministrativa     = validarTextoCaracteresNoValidos(cadena, f.getUnidadAdministrativa());     
			boolean b_tipoIngreso              = validarTextoCaracteresNoValidos(cadena, f.getTipoIngreso());
			boolean b_codPeriodo               = validarTextoCaracteresNoValidos(cadena, f.getCodPeriodo());
			boolean b_codClienteRetenido       = validarTextoCaracteresNoValidos(cadena, f.getCodClienteRetenido());
			boolean b_codPremio                = validarTextoCaracteresNoValidos(cadena, f.getCodPremio());                
			boolean b_valApellido1             = validarTextoCaracteresNoValidos(cadena, f.getValApellido1());             
			boolean b_valApellido2             = validarTextoCaracteresNoValidos(cadena, f.getValApellido2());             
			boolean b_valNombre1               = validarTextoCaracteresNoValidos(cadena, f.getValNombre1());               
			boolean b_valNombre2               = validarTextoCaracteresNoValidos(cadena, f.getValNombre2());               
			boolean b_numDocuIdentidad         = validarTextoCaracteresNoValidos(cadena, f.getNumDocuIdentidad());         
			boolean b_numRUC                   = validarTextoCaracteresNoValidos(cadena, f.getNumRUC());                   
			boolean b_indEstaCivil             = validarTextoCaracteresNoValidos(cadena, f.getIndEstaCivil());
    		
			boolean b_indNivelEducativo        = validarTextoCaracteresNoValidos(cadena, f.getIndNivelEducativo());        
			boolean b_valBarrCliente           = validarTextoCaracteresNoValidos(cadena, f.getValBarrCliente());           
			boolean b_valDirecCliente          = validarTextoCaracteresNoValidos(cadena, f.getValDirecCliente());          
			boolean b_valCiudCliente           = validarTextoCaracteresNoValidos(cadena, f.getValCiudCliente());           
			boolean b_valDepaCliente           = validarTextoCaracteresNoValidos(cadena, f.getValDepaCliente());           
			boolean b_indVentaDirecta          = validarTextoCaracteresNoValidos(cadena, f.getIndVentaDirecta());              
			boolean b_fecNacimiento            = validarTextoCaracteresNoValidos(cadena, f.getFecNacimiento());            
			boolean b_valRegionArribo          = validarTextoCaracteresNoValidos(cadena, f.getValRegionArribo());         
			boolean b_valZonaArribo            = validarTextoCaracteresNoValidos(cadena, f.getValZonaArribo());            
			boolean b_valNombreVia             = validarTextoCaracteresNoValidos(cadena, f.getValNombreVia());             
			boolean b_numDireCliente           = validarTextoCaracteresNoValidos(cadena, f.getNumDireCliente());           
			boolean b_fecProceso               = validarTextoCaracteresNoValidos(cadena, f.getFecProceso());               
			boolean b_delegacionCliente        = validarTextoCaracteresNoValidos(cadena, f.getDelegacionCliente());
			boolean b_codigoPostalCliente      = validarTextoCaracteresNoValidos(cadena, f.getCodigoPostalCliente());      
			boolean b_barrioEntrega            = validarTextoCaracteresNoValidos(cadena, f.getBarrioEntrega());            
			boolean b_delegacionEntrega        = validarTextoCaracteresNoValidos(cadena, f.getDelegacionEntrega());        
			boolean b_direccionEntrega         = validarTextoCaracteresNoValidos(cadena, f.getDireccionEntrega());
    		
			boolean b_departamentoEntrega      = validarTextoCaracteresNoValidos(cadena, f.getDepartamentoEntrega());      
			boolean b_codDocuFiador            = validarTextoCaracteresNoValidos(cadena, f.getCodDocuFiador());            
			boolean b_valApellido1Fiador       = validarTextoCaracteresNoValidos(cadena, f.getValApellido1Fiador());       
			boolean b_valApellido2Fiador       = validarTextoCaracteresNoValidos(cadena, f.getValApellido2Fiador());       
			boolean b_valNombre1Fiador         = validarTextoCaracteresNoValidos(cadena, f.getValNombre1Fiador());         
			boolean b_valNombre2Fiador         = validarTextoCaracteresNoValidos(cadena, f.getValNombre2Fiador());         
			boolean b_valDireFiador            = validarTextoCaracteresNoValidos(cadena, f.getValDireFiador());            
			boolean b_valBarrFiador            = validarTextoCaracteresNoValidos(cadena, f.getValBarrFiador());            
			boolean b_valCiudFiador            = validarTextoCaracteresNoValidos(cadena, f.getValCiudFiador());            
			boolean b_valDepaFiador            = validarTextoCaracteresNoValidos(cadena, f.getValDepaFiador());            
			boolean b_valNomViaFiador          = validarTextoCaracteresNoValidos(cadena, f.getValNomViaFiador());          
			boolean b_numDireFiador            = validarTextoCaracteresNoValidos(cadena, f.getNumDireFiador());            
			boolean b_nombreEmpresaFiador      = validarTextoCaracteresNoValidos(cadena, f.getNombreEmpresaFiador());      
			boolean b_cargoFiador              = validarTextoCaracteresNoValidos(cadena, f.getCargoFiador());              
			boolean b_direccionEmpresaFiador   = validarTextoCaracteresNoValidos(cadena, f.getDireccionEmpresaFiador());   
			boolean b_tipoVinculoFiador        = validarTextoCaracteresNoValidos(cadena, f.getTipoVinculoFiador());        
			boolean b_apellido1RefFamiliar     = validarTextoCaracteresNoValidos(cadena, f.getApellido1RefFamiliar());     
			boolean b_nombre1RefFamiliar       = validarTextoCaracteresNoValidos(cadena, f.getNombre1RefFamiliar());       
			boolean b_barrioRefFamiliar        = validarTextoCaracteresNoValidos(cadena, f.getBarrioRefFamiliar());        
			boolean b_direccionRefFamiliar     = validarTextoCaracteresNoValidos(cadena, f.getDireccionRefFamiliar());     
			boolean b_delegacionRefFamiliar    = validarTextoCaracteresNoValidos(cadena, f.getDelegacionRefFamiliar());    
			boolean b_ciudadRefFamiliar        = validarTextoCaracteresNoValidos(cadena, f.getCiudadRefFamiliar());        
			boolean b_departamentoRefFamiliar  = validarTextoCaracteresNoValidos(cadena, f.getDepartamentoRefFamiliar());
    		
			boolean b_tipoVinculoRefFamiliar   = validarTextoCaracteresNoValidos(cadena, f.getTipoVinculoRefFamiliar());   
			boolean b_apellido1RefNoFamiliar   = validarTextoCaracteresNoValidos(cadena, f.getApellido1RefNoFamiliar());   
			boolean b_nombre1RefNoFamiliar     = validarTextoCaracteresNoValidos(cadena, f.getNombre1RefNoFamiliar());     
			boolean b_tipoVinculoRefNoFamiliar = validarTextoCaracteresNoValidos(cadena, f.getTipoVinculoRefNoFamiliar()); 
			boolean b_direccionRefNoFamiliar   = validarTextoCaracteresNoValidos(cadena, f.getDireccionRefNoFamiliar());   
			boolean b_requiereFactura          = validarTextoCaracteresNoValidos(cadena, f.getRequiereFactura());          
			boolean b_barrioContribuyente      = validarTextoCaracteresNoValidos(cadena, f.getBarrioContribuyente());      
			boolean b_delegacionContribuyente  = validarTextoCaracteresNoValidos(cadena, f.getDelegacionContribuyente());  
			boolean b_direccionContribuyente   = validarTextoCaracteresNoValidos(cadena, f.getDireccionContribuyente());   
			boolean b_ciudadContribuyente      = validarTextoCaracteresNoValidos(cadena, f.getCiudadContribuyente());      
			boolean b_departamentoContribuyente =validarTextoCaracteresNoValidos(cadena, f.getDepartamentoContribuyente());
			boolean b_codigoPostalContribuyente =validarTextoCaracteresNoValidos(cadena, f.getCodigoPostalContribuyente());
			boolean b_numeroContribuyente      = validarTextoCaracteresNoValidos(cadena, f.getNumeroContribuyente()); 
			
			if(!b_codCompania)campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.codCompania");
			
			if(!b_codCompania) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.codCompania");
    		if(!b_codCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.codCliente");
    		if(!b_numDocumento) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.numDocumento");
    		if(!b_unidadAdministrativa) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.unidadAdministrativa");
    		if(!b_tipoIngreso) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.tipoIngreso");
    		if(!b_codPeriodo) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.codPeriodo");
    		if(!b_codClienteRetenido) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.codClienteRetenido");
    		if(!b_codPremio) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.codPremio");
    		if(!b_valApellido1) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valApellido1");
    		if(!b_valApellido2) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valApellido2");
    		if(!b_valNombre1) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valNombre1");
    		if(!b_valNombre2) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valNombre2");
    		if(!b_numDocuIdentidad) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.numDocuIdentidad");
    		if(!b_numRUC) campos1 = campos1 + "," +this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.numRUC");
    		if(!b_indEstaCivil) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.indEstaCivil");
    		if(!b_indNivelEducativo) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.indNivelEducativo");
    		if(!b_valBarrCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valBarrCliente");
    		if(!b_valDirecCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valDirecCliente");
    		if(!b_valCiudCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valCiudCliente");
    		if(!b_valDepaCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valDepaCliente");
    		if(!b_indVentaDirecta) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.indVentaDirecta");
    		if(!b_fecNacimiento) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.fecNacimiento");
    		if(!b_valRegionArribo) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valRegionArribo");
    		if(!b_valZonaArribo) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valZonaArribo");
    		if(!b_valNombreVia) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valNombreVia");
    		if(!b_numDireCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.numDireCliente");
    		if(!b_fecProceso) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.fecProceso");
    		if(!b_delegacionCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.delegacionCliente");
    		if(!b_codigoPostalCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.codigoPostalCliente");
    		if(!b_barrioEntrega) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.barrioEntrega");
    		if(!b_delegacionEntrega) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.delegacionEntrega");
    		if(!b_direccionEntrega) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.direccionEntrega");
    		if(!b_departamentoEntrega) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.departamentoEntrega");
    		if(!b_codDocuFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.codDocuFiador");
    		if(!b_valApellido1Fiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valApellido1Fiador");
    		if(!b_valApellido2Fiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valApellido2Fiador");
    		if(!b_valNombre1Fiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valNombre1Fiador");
    		if(!b_valNombre2Fiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valNombre2Fiador");
    		if(!b_valDireFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valDireFiador");
    		if(!b_valBarrFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valBarrFiador");
    		if(!b_valCiudFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valCiudFiador");
    		if(!b_valDepaFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valDepaFiador");
    		if(!b_valNomViaFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valNomViaFiador");
    		if(!b_numDireFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.numDireFiador");
    		if(!b_nombreEmpresaFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.nombreEmpresaFiador");
    		if(!b_cargoFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.cargoFiador");
    		if(!b_direccionEmpresaFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.direccionEmpresaFiador");
    		if(!b_tipoVinculoFiador) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.tipoVinculoFiador");
    		if(!b_apellido1RefFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.apellido1RefFamiliar");
    		if(!b_nombre1RefFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.nombre1RefFamiliar");
    		if(!b_barrioRefFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.barrioRefFamilia");
    		if(!b_direccionRefFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.direccionRefFamiliar");
    		if(!b_delegacionRefFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.delegacionRefFamiliar");
    		if(!b_ciudadRefFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.ciudadRefFamiliar");
    		if(!b_departamentoRefFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.departamentoRefFamiliar");
    		if(!b_tipoVinculoRefFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.tipoVinculoRefFamiliar");
    		if(!b_apellido1RefNoFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.apellido1RefNoFamiliar");
    		if(!b_nombre1RefNoFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.nombre1RefNoFamiliar");
    		if(!b_tipoVinculoRefNoFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.tipoVinculoRefNoFamiliar");
    		if(!b_direccionRefNoFamiliar) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.direccionRefNoFamiliar");
    		if(!b_requiereFactura) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.requiereFactura");
    		if(!b_barrioContribuyente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.barrioContribuyente");
    		if(!b_delegacionContribuyente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.delegacionContribuyente");
    		if(!b_direccionContribuyente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.direccionContribuyente");
    		if(!b_ciudadContribuyente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.ciudadContribuyente");
    		if(!b_departamentoContribuyente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.departamentoContribuyente");
    		if(!b_codigoPostalContribuyente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.codigoPostalContribuyente");
    		if(!b_numeroContribuyente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.numeroContribuyente");
    		if(campos1.length()>1) campos1 = campos1.substring(1);	
		}
		if (campos1.length() > 0) {			
			String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.msg.validarCaracteresInicio");
			String msgValidarCaracteresFinal1 = this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.msg.validarCaracteresFinal1");
    		campos1 = msgValidarCaracteresInicio + campos1 + msgValidarCaracteresFinal1 + " ";
    		campos1 = campos1 + obtenerListaCaracteres(cadena);
    		this.msjeCampo1=campos1;    		
    		return false;
    	}
    	return true;		
	}
	
	public boolean validarCamposEntradaCaracteres2(){
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;
		String cadena2=f.getCadenaCaracteresV2();
		String campos2 = "";
		if(!f.isValidarCaracteres2())
			return true;
		
		if(f.isValidarCaracteres2()){
			
			boolean b_valTelfCliente           =  validarTextoCaracteresValidos(cadena2, f.getValTelfCliente());       
			boolean b_valCeluCliente           =  validarTextoCaracteresValidos(cadena2, f.getValCeluCliente());     
			boolean b_valTelfTrabajo           =  validarTextoCaracteresValidos(cadena2, f.getValTelfTrabajo());       
			boolean b_telefonoEntrega          =  validarTextoCaracteresValidos(cadena2, f.getTelefonoEntrega());      
			boolean b_celularEntrega           =  validarTextoCaracteresValidos(cadena2, f.getCelularEntrega());       
			boolean b_valTelfFiador            =  validarTextoCaracteresValidos(cadena2, f.getValTelfFiador());        
			boolean b_valCeluFiador            =  validarTextoCaracteresValidos(cadena2, f.getValCeluFiador());        
			boolean b_valTelfTrabaFiador       =  validarTextoCaracteresValidos(cadena2, f.getValTelfTrabaFiador());   
			boolean b_telefonoRefFamiliar      =  validarTextoCaracteresValidos(cadena2, f.getTelefonoRefFamiliar());  
			boolean b_celularRefFamiliar       =  validarTextoCaracteresValidos(cadena2, f.getCelularRefFamiliar());   
			boolean b_telefonoRefNoFamiliar    =  validarTextoCaracteresValidos(cadena2, f.getTelefonoRefNoFamiliar());
			boolean b_celularRefNoFamiliar     =  validarTextoCaracteresValidos(cadena2, f.getCelularRefNoFamiliar()); 
			
			if(!b_valTelfCliente) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valTelfCliente");
    		if(!b_valCeluCliente) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valCeluCliente");
    		if(!b_valTelfTrabajo) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valTelfTrabajo");
    		if(!b_telefonoEntrega) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.telefonoEntrega");
    		if(!b_celularEntrega) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.celularEntrega");
    		if(!b_valTelfFiador) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valTelfFiador");
    		if(!b_valCeluFiador) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valCeluFiador");
    		if(!b_valTelfTrabaFiador) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valTelfTrabaFiador");
    		if(!b_telefonoRefFamiliar) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.telefonoRefFamiliar");
    		if(!b_celularRefFamiliar) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.celularRefFamiliar");
    		if(!b_telefonoRefNoFamiliar) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.telefonoRefNoFamiliar");
    		if(!b_celularRefNoFamiliar) campos2 = campos2 + "," +  this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.celularRefNoFamiliar"); 							
    	
    		if(campos2.length()>1) campos2 = campos2.substring(1);				
		}
		
		if (campos2.length() > 0) {
			String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.msg.validarCaracteresInicio");
			String msgValidarCaracteresFinal2 = this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.msg.validarCaracteresFinal2");
    		campos2 = msgValidarCaracteresInicio + campos2 + msgValidarCaracteresFinal2 + " ";
    		campos2 = campos2 + obtenerListaCaracteres(cadena2);
    		this.msjeCampo2=campos2;    		
    		return false;
    	}
    	return true;		
	}
	
	public boolean validarCamposEntradaCaracteres3(){
		MantenimientoSTOSolicitudCreditoForm f =(MantenimientoSTOSolicitudCreditoForm)this.formBusqueda;	
		String campos3 = "";
		if(!f.isValidarCaracteres3())
			return true;
		if(f.isValidarCaracteres3()){			
    		boolean b_valMailCliente = validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV3(), f.getValMailCliente());
    		if(!b_valMailCliente) campos3 = this.getResourceMessage("mantenimientoSTOSolicitudCreditoForm.valMailCliente");
		}
		
		if (campos3.length() > 0) {
			String msgValidarCaracteresInicio = this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.msg.validarCaracteresInicio");
			String msgValidarCaracteresFinal1 = this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.msg.validarCaracteresFinal1");
    		campos3 = msgValidarCaracteresInicio + campos3 + msgValidarCaracteresFinal1 + " ";
    		campos3 = campos3 + obtenerListaCaracteres(f.getCadenaCaracteresNV3());
    		this.msjeCampo3=campos3;    		
    		return false;
    	}
    	return true;
	}
	
	
	public boolean validarTextoCaracteresNoValidos(String cadenaCV, String texto){
		String cadena="__" + cadenaCV;
		if(StringUtils.isBlank(texto))
			return true;
		
		for(int i=0; i<texto.length(); i++) {			
			int codePoint = Character.codePointAt(texto, i);
			String aux = "__" + codePoint + "__";			
			if(cadena.indexOf(aux)>=0) {
				return false;
			}
		}
		return true;		
	}
	
	public boolean validarTextoCaracteresValidos(String cadenaCV, String texto){
		String cadena="__" + cadenaCV;
		if(StringUtils.isBlank(texto))
			return true;
		
		for(int i=0; i<texto.length(); i++) {			
			int codePoint = Character.codePointAt(texto, i);
			String aux = "__" + codePoint + "__";			
			if(cadena.indexOf(aux)<0) {
				return false;
			}
		}
		return true;
	}
	
	public String obtenerListaCaracteres(String codigoCaracteres) {
		String cadenaCaracteres ="";
		int codepoint=0;
		String[] listaCaracteres = codigoCaracteres.split("__");
		for(int i=0; i<listaCaracteres.length; i++){
				 codepoint=Integer.parseInt(listaCaracteres[i].toString());
			   cadenaCaracteres = cadenaCaracteres + " " + (char)codepoint;
		}
		return cadenaCaracteres;	
			
	}	


	public List getStoTipoDocList() {
		return stoTipoDocList;
	}

	public void setStoTipoDocList(List stoTipoDocList) {
		this.stoTipoDocList = stoTipoDocList;
	}

	public List getStoTipoViaList() {
		return stoTipoViaList;
	}

	public void setStoTipoViaList(List stoTipoViaList) {
		this.stoTipoViaList = stoTipoViaList;
	}

	public List getStoMotivosGestion() {
		return stoMotivosGestion;
	}

	public void setStoMotivosGestion(List stoMotivosGestion) {
		this.stoMotivosGestion = stoMotivosGestion;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public LabelValue[] getMaeCiudadList() {
		return maeCiudadList;
	}

	public void setMaeCiudadList(LabelValue[] maeCiudadList) {
		this.maeCiudadList = maeCiudadList;
	}

	public LabelValue[] getMaeCiudadListEntr() {
		return maeCiudadListEntr;
	}

	public void setMaeCiudadListEntr(LabelValue[] maeCiudadListEntr) {
		this.maeCiudadListEntr = maeCiudadListEntr;
	}

	public LabelValue[] getMaeClienteNivel1List() {
		return maeClienteNivel1List;
	}

	public void setMaeClienteNivel1List(LabelValue[] maeClienteNivel1List) {
		this.maeClienteNivel1List = maeClienteNivel1List;
	}

	public LabelValue[] getMaeClienteNivel2List() {
		return maeClienteNivel2List;
	}

	public void setMaeClienteNivel2List(LabelValue[] maeClienteNivel2List) {
		this.maeClienteNivel2List = maeClienteNivel2List;
	}

	public LabelValue[] getMaeClienteNivel3List() {
		return maeClienteNivel3List;
	}

	public void setMaeClienteNivel3List(LabelValue[] maeClienteNivel3List) {
		this.maeClienteNivel3List = maeClienteNivel3List;
	}

	public LabelValue[] getMaeClienteNivel4List() {
		return maeClienteNivel4List;
	}

	public void setMaeClienteNivel4List(LabelValue[] maeClienteNivel4List) {
		this.maeClienteNivel4List = maeClienteNivel4List;
	}

	public LabelValue[] getMaeFiadorNivel1List() {
		return maeFiadorNivel1List;
	}

	public void setMaeFiadorNivel1List(LabelValue[] maeFiadorNivel1List) {
		this.maeFiadorNivel1List = maeFiadorNivel1List;
	}

	public LabelValue[] getMaeFiadorNivel2List() {
		return maeFiadorNivel2List;
	}

	public void setMaeFiadorNivel2List(LabelValue[] maeFiadorNivel2List) {
		this.maeFiadorNivel2List = maeFiadorNivel2List;
	}

	public LabelValue[] getMaeFiadorNivel3List() {
		return maeFiadorNivel3List;
	}

	public void setMaeFiadorNivel3List(LabelValue[] maeFiadorNivel3List) {
		this.maeFiadorNivel3List = maeFiadorNivel3List;
	}

	public LabelValue[] getMaeFiadorNivel4List() {
		return maeFiadorNivel4List;
	}

	public void setMaeFiadorNivel4List(LabelValue[] maeFiadorNivel4List) {
		this.maeFiadorNivel4List = maeFiadorNivel4List;
	}

	public boolean isMostrarPopupCliente() {
		return mostrarPopupCliente;
	}

	public void setMostrarPopupCliente(boolean mostrarPopupCliente) {
		this.mostrarPopupCliente = mostrarPopupCliente;
	}

	public BusquedaHIPClientePOPUPSearchAction getBusquedaHIPClientePOPUPSearchAction() {
		return busquedaHIPClientePOPUPSearchAction;
	}

	public void setBusquedaHIPClientePOPUPSearchAction(
			BusquedaHIPClientePOPUPSearchAction busquedaHIPClientePOPUPSearchAction) {
		this.busquedaHIPClientePOPUPSearchAction = busquedaHIPClientePOPUPSearchAction;
	}

	public static String getPopupCliente() {
		return POPUP_CLIENTE;
	}

	public boolean isMostrarRojo() {
		return mostrarRojo;
	}

	public void setMostrarRojo(boolean mostrarRojo) {
		this.mostrarRojo = mostrarRojo;
	}

	public boolean isMostrarVerde() {
		return mostrarVerde;
	}

	public void setMostrarVerde(boolean mostrarVerde) {
		this.mostrarVerde = mostrarVerde;
	}

	public boolean isMostrarAmarillo() {
		return mostrarAmarillo;
	}

	public void setMostrarAmarillo(boolean mostrarAmarillo) {
		this.mostrarAmarillo = mostrarAmarillo;
	}

	public String getOidPais() {
		return oidPais;
	}

	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	
	public MantenimientoMAEInformacionClienteAction getMantMAEInformacionClienteAction() {
		return mantMAEInformacionClienteAction;
	}

	public void setMantMAEInformacionClienteAction(
			MantenimientoMAEInformacionClienteAction mantMAEInformacionClienteAction) {
		this.mantMAEInformacionClienteAction = mantMAEInformacionClienteAction;
	}

	public MantenimientoMAEBloqueoDesbloqueoClienteAction getMantMAEBloqueoDesbloqueoClienteAction() {
		return mantMAEBloqueoDesbloqueoClienteAction;
	}

	public void setMantMAEBloqueoDesbloqueoClienteAction(
			MantenimientoMAEBloqueoDesbloqueoClienteAction mantMAEBloqueoDesbloqueoClienteAction) {
		this.mantMAEBloqueoDesbloqueoClienteAction = mantMAEBloqueoDesbloqueoClienteAction;
	}

	public boolean isbIndTelefonoOK() {
		return bIndTelefonoOK;
	}

	public void setbIndTelefonoOK(boolean bIndTelefonoOK) {
		this.bIndTelefonoOK = bIndTelefonoOK;
	}

	public boolean isbIndSituacionCrediticia() {
		return bIndSituacionCrediticia;
	}

	public void setbIndSituacionCrediticia(boolean bIndSituacionCrediticia) {
		this.bIndSituacionCrediticia = bIndSituacionCrediticia;
	}

	public boolean isbIndSinSaldoAmbasMarcas() {
		return bIndSinSaldoAmbasMarcas;
	}

	public void setbIndSinSaldoAmbasMarcas(boolean bIndSinSaldoAmbasMarcas) {
		this.bIndSinSaldoAmbasMarcas = bIndSinSaldoAmbasMarcas;
	}

	public String getMsjeCampo1() {
		return msjeCampo1;
	}

	public void setMsjeCampo1(String msjeCampo1) {
		this.msjeCampo1 = msjeCampo1;
	}

	public String getMsjeCampo2() {
		return msjeCampo2;
	}

	public void setMsjeCampo2(String msjeCampo2) {
		this.msjeCampo2 = msjeCampo2;
	}

	public String getMsjeCampo3() {
		return msjeCampo3;
	}

	public void setMsjeCampo3(String msjeCampo3) {
		this.msjeCampo3 = msjeCampo3;
	}

	public boolean isbIndFacturacionElectronica() {
		return bIndFacturacionElectronica;
	}

	public void setbIndFacturacionElectronica(boolean bIndFacturacionElectronica) {
		this.bIndFacturacionElectronica = bIndFacturacionElectronica;
	}

	public ConsultaMAEClienteSearchAction getConsultaMAEClienteSearchAction() {
		return consultaMAEClienteSearchAction;
	}

	public void setConsultaMAEClienteSearchAction(
			ConsultaMAEClienteSearchAction consultaMAEClienteSearchAction) {
		this.consultaMAEClienteSearchAction = consultaMAEClienteSearchAction;
	}

	/**
	 * @return the stoEstadoCivilList
	 */
	public List getStoEstadoCivilList() {
		return stoEstadoCivilList;
	}

	/**
	 * @param stoEstadoCivilList the stoEstadoCivilList to set
	 */
	public void setStoEstadoCivilList(List stoEstadoCivilList) {
		this.stoEstadoCivilList = stoEstadoCivilList;
	}

	/**
	 * @return the stoTipoPersonaList
	 */
	public List getStoTipoPersonaList() {
		return stoTipoPersonaList;
	}

	/**
	 * @param stoTipoPersonaList the stoTipoPersonaList to set
	 */
	public void setStoTipoPersonaList(List stoTipoPersonaList) {
		this.stoTipoPersonaList = stoTipoPersonaList;
	}

	/**
	 * @return the stoNacionalidadList
	 */
	public List getStoNacionalidadList() {
		return stoNacionalidadList;
	}

	/**
	 * @param stoNacionalidadList the stoNacionalidadList to set
	 */
	public void setStoNacionalidadList(List stoNacionalidadList) {
		this.stoNacionalidadList = stoNacionalidadList;
	}

	/**
	 * @return the stoOrigenIngresoList
	 */
	public List getStoOrigenIngresoList() {
		return stoOrigenIngresoList;
	}

	/**
	 * @param stoOrigenIngresoList the stoOrigenIngresoList to set
	 */
	public void setStoOrigenIngresoList(List stoOrigenIngresoList) {
		this.stoOrigenIngresoList = stoOrigenIngresoList;
	}
}
