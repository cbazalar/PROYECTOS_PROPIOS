package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ActualizacionDatos;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOActualizacionDatosForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOOrdenCompraDetalleForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOSolicitudCreditoForm;

@ManagedBean
@SessionScoped
public class MantenimientoSTOActualizacionDatosAction extends BaseMantenimientoSTOGestionAction{

	private static final long serialVersionUID = -461096052515285447L;	
	
	private List stoTipoDocList;
	private List stoTipoViaList;
	private LabelValue[] maeClienteNivel1List;
	private LabelValue[] maeClienteNivel2List;
	private LabelValue[] maeClienteNivel3List;
	private LabelValue[] maeClienteNivel4List;
	private LabelValue[] maeCiudadList;
	private LabelValue[] maeCiudadListEntr;
		
	private String sCodDepaCliente;
	private String sCodProvCliente;
	private String sCodDistCliente;
	private String sCodSectCliente;
	
	private boolean editable;
	private boolean indValidacionOK;
	private boolean indDesmarcaCampos;
	private String msjeCampo1;
	private String msjeCampo2;
	private String msjeCampo3;	
	
	private List stoNacionalidadList;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOActualizacionDatosForm  form = new MantenimientoSTOActualizacionDatosForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {	
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=true;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonModificar=false;	
		this.indDesmarcaCampos=false;
		this.indValidacionOK=false;		
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Map criteria = new HashMap(); 
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;
		f.setSalirPantalla(Constants.SI);
		
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("numDocumento",f.getNumSecuencia());
		criteria.put("numLote",f.getNumLote());
		criteria.put("codPeriodo",f.getCodPeriodo());
		criteria.put("codCliente",f.getCodCliente());
		criteria.put("codCompania",f.getCodCompania());
		
		if(f.getFechaProcesoDate()!=null)
			f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoDate()));
		if(this.indValidacionOK)
			f.setIndicadorValidacionOK(Constants.NUMERO_UNO);
		else
			f.setIndicadorValidacionOK(Constants.NUMERO_CERO);
		
		//////////////////////////////////////////////////
		String indCampos = f.getIndicadorDesmarcaCampos();
		if (indCampos.equals(Constants.NUMERO_CERO)){
			criteria.put("codDepaCliente",f.getCodDepaCliente());
			 
			if(f.getCodDistCliente().compareToIgnoreCase("")==0){
				criteria.put("codDistCliente",f.getCodDistCliente());
			}
			else{
				criteria.put("codDistCliente",f.getCodDistCliente().substring(12,18));
			}

			if(f.getCodProvCliente().compareToIgnoreCase("")==0){
				criteria.put("codProvCliente",f.getCodProvCliente());
			}
			else{
				 criteria.put("codProvCliente",f.getCodProvCliente().substring(6,12));
			}

			if(f.getCodSectCliente().compareToIgnoreCase("")==0){
				criteria.put("codSectCliente",f.getCodSectCliente());
			}
			else{
				criteria.put("codSectCliente",f.getCodSectCliente().substring(18,24));
			}
		}
		else{
			criteria.put("codDepaCliente",this.sCodDepaCliente);
			criteria.put("codDistCliente",this.sCodDistCliente);
			criteria.put("codProvCliente",this.sCodProvCliente);
			criteria.put("codSectCliente",this.sCodSectCliente);		
		}
		
		//////////////////////////////////////////////////////////////////////
		criteria.put("codTipoDocumento",f.getCodTipoDocumento());
		 
		criteria.put("indEstaProceso",f.getIndEstaProceso());
		criteria.put("indMotivoRechazo",f.getIndMotivoRechazo());
		 
		criteria.put("indVentaDirecta",f.getIndVentaDirecta());
		criteria.put("numDireCliente",f.getNumDireCliente());
		 
		criteria.put("numDocuIdentidad",f.getNumDocuIdentidad());
		criteria.put("numRUC",f.getNumRUC());
		 
		criteria.put("tipoDocumento",f.getTipoDocumento());
		 
		criteria.put("tipoViaCliente",f.getTipoViaCliente());
		 
		criteria.put("uniAdministrativa",f.getUnidadAdministrativa());
		criteria.put("apellido1",f.getValApellido1());
		 
		criteria.put("apellido2",f.getValApellido2());
		 
		criteria.put("barrioCliente",f.getValBarrCliente());
		 
		criteria.put("celularCliente",f.getValCeluCliente());
		
		criteria.put("ciudadCliente",f.getValCiudCliente());
		 
		criteria.put("depaCliente",f.getValDepaCliente());
		 
		criteria.put("direcCliente",f.getValDirecCliente());
		 
		criteria.put("nombre1",f.getValNombre1());
		 
		criteria.put("nombre2",f.getValNombre2());
		
		criteria.put("nombreVia",f.getValNombreVia());

		criteria.put("telfCliente",f.getValTelfCliente());
		criteria.put("telfTrabajoCliente",f.getValTelfTrabajo());

		criteria.put("codRegion",f.getValRegionArribo());
		criteria.put("codZona",f.getValZonaArribo());
		criteria.put("email",f.getValMailCliente());
		criteria.put("fecProceso",f.getFechaProceso());
		criteria.put("direccionEntrega",f.getDireccionEntrega());
		criteria.put("telefonoEntrega",f.getTelefonoEntrega());
		criteria.put("celularEntrega",f.getCelularEntrega());
		
		criteria.put("nacionalidad",f.getNacionalidad());
		criteria.put("territorioCorresponde",f.getTerritorioCorresponde());
		criteria.put("direccionDomicilioIgualDireccionEntrega",f.getDireccionDomicilioIgualDireccionEntrega());
		criteria.put("dirDomManzana",f.getDirDomManzana());
		criteria.put("dirDomEtapa",f.getDirDomEtapa());
		criteria.put("dirDomCallePrincipal",f.getDirDomCallePrincipal());
		criteria.put("dirDomCalleSecundaria",f.getDirDomCalleSecundaria());
		criteria.put("dirDomNumero",f.getDirDomNumero());
		criteria.put("dirDomReferencia",f.getDirDomReferencia());
		criteria.put("dirEntBarrio",f.getDirEntBarrio());
		criteria.put("dirEntManzana",f.getDirEntManzana());
		criteria.put("dirEntEtapa",f.getDirEntEtapa());
		criteria.put("dirEntCallePrincipal",f.getDirEntCallePrincipal());
		criteria.put("dirEntCalleSecundaria",f.getDirEntCalleSecundaria());
		criteria.put("dirEntNumero",f.getDirEntNumero());
		criteria.put("dirEntReferencia",f.getDirEntReferencia());

		if (StringUtils.equals(f.getIndicadorValidacionOK(), Constants.NUMERO_CERO))
			criteria.put("indicadorValidacionOK",Constants.NUMERO_CERO);
		else
			criteria.put("indicadorValidacionOK",Constants.NUMERO_UNO);

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

		//sb PER-SiCC-2012-0460 fin
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = 
			 (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		procesoSTOEjecucionValidacionesService.updateActualizacionDatos(criteria);	
		return true;
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		f.setSalirPantalla(Constants.NO);		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		MantenimientoRECDigitacionCDRService mantenimientoRECDigitacionCDRService = (MantenimientoRECDigitacionCDRService)getBean("spusicc.mantenimientoRECDigitacionCDRService");
		GenericoService serviceGen = (GenericoService) getBean("genericoService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");		
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);		
		f.setOidPais(String.valueOf(oidPais));		
	
		Map criteria = new HashMap();
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");		
		this.stoTipoViaList= procesoSTOEjecucionValidacionesService.getTipoViaCliente();
    	
		GestionDocumento gestion = (GestionDocumento) this.getRegistroSeleccionado();
		criteria.put("codigoPais",pais.getCodigo());
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
		List ListaActualizacionDatos=procesoSTOEjecucionValidacionesService.getActualizacionDatos(criteria);		
		ActualizacionDatos actualizacionDatos = (ActualizacionDatos)ListaActualizacionDatos.get(0);	
		this.maeClienteNivel1List=ajaxService.getUnidadesGeograficas(oidPais, "");		
		
		if(actualizacionDatos.getCodDepaCliente()!= null ){
			if(actualizacionDatos.getCodDepaCliente().compareToIgnoreCase("")!=0 )
				this.maeClienteNivel2List=ajaxService.getUnidadesGeograficas(oidPais, actualizacionDatos.getCodDepaCliente());			
		}
		if(actualizacionDatos.getCodProvCliente()!=null){
			if(actualizacionDatos.getCodProvCliente().compareToIgnoreCase("")!=0)
				this.maeClienteNivel3List=ajaxService.getUnidadesGeograficas(oidPais, actualizacionDatos.getCodDepaCliente()+actualizacionDatos.getCodProvCliente());		
		}
		if(actualizacionDatos.getCodDistCliente()!=null){
			if( actualizacionDatos.getCodDistCliente().compareToIgnoreCase("")!=0)
				this.maeClienteNivel4List=ajaxService.getUnidadesGeograficas(oidPais, actualizacionDatos.getCodDepaCliente()+actualizacionDatos.getCodProvCliente()+actualizacionDatos.getCodDistCliente());	
		}
		
		setActualizacionDatos(f,actualizacionDatos);
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());
		
    	//-- Setea combo tipo documento		
		this.stoTipoDocList= procesoSTOEjecucionValidacionesService.getTipoDocumento();
		
		this.stoNacionalidadList = procesoSTOEjecucionValidacionesService.getNacionalidadCodigo();
		
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
		
		ParametroPais paramPais = new ParametroPais();		
		ParametroPais resultPais = new ParametroPais();
		paramPais.setCodigoPais(f.getCodigoPais());
		paramPais.setCodigoSistema(Constants.SISTEMA_OCR);
		paramPais.setCodigoParametro(Constants.OCR_MOSTRAR_IND_CAMPOS_ADICIONALES_SAD);		
		paramPais.setNombreParametro(Constants.OCR_NOM_PARA_CAMPOS_ADICIONALES_SAD);
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
	
	/**
	 * Metodo que setea los valores del jsp de actualizacion de datos	
	 */
	private  void setActualizacionDatos(MantenimientoSTOActualizacionDatosForm f,ActualizacionDatos actualizacionDatos) throws ParseException {
		
		f.setCodigoPais(actualizacionDatos.getCodPais());
		f.setNumLote(actualizacionDatos.getNumLote());
		f.setCodCliente(actualizacionDatos.getCodCliente());
		f.setCodCompania(actualizacionDatos.getCodCompania());
		f.setCodDepaCliente(actualizacionDatos.getCodDepaCliente());
		f.setCodDistCliente(actualizacionDatos.getCodDepaCliente()+actualizacionDatos.getCodProvCliente()+actualizacionDatos.getCodDistCliente());
		f.setCodPeriodo(actualizacionDatos.getCodPeriodo());
		f.setCodProvCliente(actualizacionDatos.getCodDepaCliente()+actualizacionDatos.getCodProvCliente());
		f.setCodSectCliente(actualizacionDatos.getCodDepaCliente()+actualizacionDatos.getCodProvCliente()+actualizacionDatos.getCodDistCliente()+actualizacionDatos.getCodSectCliente());
		f.setCodTipoDocumento(actualizacionDatos.getCodTipoDocumento());		
		f.setFechaProceso(actualizacionDatos.getFechaProceso());
		if(StringUtils.isNotBlank(f.getFechaProceso()))
			f.setFechaProcesoDate(DateUtil.convertStringToDate(f.getFechaProceso()));
		f.setIndEstaProceso(actualizacionDatos.getIndEstaProceso());
		f.setIndMotivoRechazo(actualizacionDatos.getIndMotivoRechazo());
		f.setNumDireCliente(actualizacionDatos.getNumDireCliente());
		
		f.setNumDocuIdentidad(actualizacionDatos.getNumDocuIdentidad());
		f.setNumDocumento(actualizacionDatos.getNumDocumento());
		f.setNumRUC(actualizacionDatos.getNumRUC());
		f.setTipoDocumento(actualizacionDatos.getTipoDocumento());
		f.setTipoViaCliente(actualizacionDatos.getTipoViaCliente());
		f.setUnidadAdministrativa(actualizacionDatos.getUnidadAdministrativa());
		f.setValApellido1(actualizacionDatos.getValApellido1());
		f.setValApellido2(actualizacionDatos.getValApellido2());
		f.setValBarrCliente(actualizacionDatos.getValBarrCliente());
		f.setValCeluCliente(actualizacionDatos.getValCeluCliente());
		f.setValCiudCliente(actualizacionDatos.getValCiudCliente());
		f.setValDepaCliente(actualizacionDatos.getValDepaCliente());
		f.setValDirecCliente(actualizacionDatos.getValDirecCliente());
		f.setValNombre1(actualizacionDatos.getValNombre1());
		f.setValNombre2(actualizacionDatos.getValNombre2());
		f.setValNombreVia(actualizacionDatos.getValNombreVia());
		f.setValRegionArribo(actualizacionDatos.getValRegionArribo());
		f.setValTelfCliente(actualizacionDatos.getValTelfCliente());
		f.setValTelfTrabajo(actualizacionDatos.getValTelfTrabajo());
		f.setValZonaArribo(actualizacionDatos.getValZonaArribo());
		f.setNumSecuencia(actualizacionDatos.getNumSecuencia());
		f.setValMailCliente(actualizacionDatos.getValMailCliente());
		f.setDireccionEntrega(actualizacionDatos.getDireccionEntrega());
		f.setTelefonoEntrega(actualizacionDatos.getTelefonoEntrega());
		f.setCelularEntrega(actualizacionDatos.getCelularEntrega());

		f.setIndicadorValidacionOK(actualizacionDatos.getIndicadorValidacionOK());
		f.setIndicadorValidacionOKHidden(actualizacionDatos.getIndicadorValidacionOK());
		if(StringUtils.equals(f.getIndicadorValidacionOK(), "1"))
			this.indValidacionOK=true;
		else
			this.indValidacionOK=false;
		
		/************************************************************************************/
		Map map = new HashMap();
		map.put("codigoCliente", actualizacionDatos.getCodCliente());
		map.put("codigoPais", actualizacionDatos.getCodPais());
		map.put("codigoParametro", Constants.STO_GENER_ESTR_GEO);
		
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = 
			(ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		List ListaActualizacionDatos = procesoSTOEjecucionValidacionesService.getDatosClienteActual(map);
		Map criteria = new HashMap();
		criteria = (Map)ListaActualizacionDatos.get(0);
		f.setNombreActual((String)criteria.get("nombreCliente"));
		f.setStatus((String)criteria.get("estatus"));
		f.setCampanaUltimoPedido((String)criteria.get("campana"));
		f.setDireccion((String)criteria.get("direccionCliente"));
		f.setUnidadGeografica((String)criteria.get("unidadGeografica"));
		f.setRegion((String)criteria.get("region"));
		f.setZona((String)criteria.get("zona"));
		f.setSeccion((String)criteria.get("seccion"));
		f.setTerritorio((String)criteria.get("territorio"));
		f.setIndicadorDesmarcaCampos(procesoSTOEjecucionValidacionesService.getParametroSTO(map));
		if(StringUtils.equals(f.getIndicadorDesmarcaCampos(),Constants.NUMERO_UNO))
			this.indDesmarcaCampos=true;
		else
			this.indDesmarcaCampos=false;
		
		this.sCodDepaCliente = actualizacionDatos.getCodDepaCliente();
		this.sCodProvCliente = actualizacionDatos.getCodProvCliente();
		this.sCodDistCliente = actualizacionDatos.getCodDistCliente();
		this.sCodSectCliente = actualizacionDatos.getCodSectCliente();
		
		//sb PER-SiCC-2012-0460 ini
		if(StringUtils.isNotEmpty(actualizacionDatos.getCodigoCiudadDomicilio())){
			f.setCodigoCiudadDomicilio(actualizacionDatos.getCodDepaCliente()+"__"+actualizacionDatos.getCodigoCiudadDomicilio());
			f.setCodigoCiudadDomicilioUbigeo(actualizacionDatos.getCodDepaCliente()+"__"+actualizacionDatos.getCodigoCiudadDomicilio());
		}
		if(StringUtils.isNotEmpty(actualizacionDatos.getCodigoCiudadEntrega())){
			f.setCodigoCiudadEntrega(actualizacionDatos.getCodDepaCliente()+"__"+actualizacionDatos.getCodigoCiudadEntrega());
			f.setCodigoCiudadEntregaUbigeo(actualizacionDatos.getCodDepaCliente()+"__"+actualizacionDatos.getCodigoCiudadEntregaUbigeo());
		}				
		f.setVillaPoblacionDomicilio(actualizacionDatos.getVillaPoblacionDomicilio());
		f.setVillaPoblacionEntrega(actualizacionDatos.getVillaPoblacionEntrega());
		//sb PER-SiCC-2012-0460 fin
		
		f.setNacionalidad(actualizacionDatos.getNacionalidad());
		f.setTerritorioCorresponde(actualizacionDatos.getTerritorioCorresponde());
		f.setDireccionDomicilioIgualDireccionEntrega(actualizacionDatos.getDireccionDomicilioIgualDireccionEntrega());
		f.setDirDomManzana(actualizacionDatos.getDirDomManzana());
		f.setDirDomEtapa(actualizacionDatos.getDirDomEtapa());
		f.setDirDomCallePrincipal(actualizacionDatos.getDirDomCallePrincipal());
		f.setDirDomCalleSecundaria(actualizacionDatos.getDirDomCalleSecundaria());
		f.setDirDomNumero(actualizacionDatos.getDirDomNumero());
		f.setDirDomReferencia(actualizacionDatos.getDirDomReferencia());
		f.setDirEntBarrio(actualizacionDatos.getDirEntBarrio());
		f.setDirEntManzana(actualizacionDatos.getDirEntManzana());
		f.setDirEntEtapa(actualizacionDatos.getDirEntEtapa());
		f.setDirEntCallePrincipal(actualizacionDatos.getDirEntCallePrincipal());
		f.setDirEntCalleSecundaria(actualizacionDatos.getDirEntCalleSecundaria());
		f.setDirEntNumero(actualizacionDatos.getDirEntNumero());
		f.setDirEntReferencia(actualizacionDatos.getDirEntReferencia());
	}
	
	//Mostrar Provincia
	public void loadNivel2CT(ValueChangeEvent val) {
		log.debug(">>loadProvincia ");
		log.debug("val: " + val.getNewValue().toString());	
		
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;
		String depa = val.getNewValue().toString();
		if (!val.equals(null) && StringUtils.isNotEmpty(depa)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeClienteNivel2List=ajax.getUnidadesGeograficas(f.getOidPais(), depa);			
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
	
	//Mostrar Distrito
	public void loadNivel3CT(ValueChangeEvent val) {
		log.debug(">>load Distrito");
		log.debug("val: " + val.getNewValue().toString());	
			
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;
		String provincia = val.getNewValue().toString();
		if (!val.equals(null) && StringUtils.isNotEmpty(provincia)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeClienteNivel3List=ajax.getUnidadesGeograficas(f.getOidPais(), provincia);			 
			f.setCodDistCliente("");
			f.setCodSectCliente("");
			this.maeClienteNivel4List=null;
		}else {			
			this.maeClienteNivel3List = null;			
			f.setCodDistCliente("");
			f.setCodSectCliente("");			
		}		
	}
	
	//Mostrar Sector
	public void loadNivel4(ValueChangeEvent val) {
		log.debug(">>load Sector");
		log.debug("val: " + val.getNewValue().toString());	
				
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;
		String distrito = val.getNewValue().toString();
		if (!val.equals(null) && StringUtils.isNotEmpty(distrito)) {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.maeClienteNivel4List=ajax.getUnidadesGeograficas(f.getOidPais(),distrito);			
			f.setCodSectCliente("");
		}else {			
			this.maeClienteNivel4List = null;				
			f.setCodSectCliente("");			
		}		
	}
	
	@Override
	public String setValidarMantenimiento(){
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;
		String email=f.getValMailCliente();
		if(StringUtils.isNotBlank(email)){
			String expresion = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";		
			Pattern p = Pattern.compile(expresion);
			Matcher m = p.matcher(email);
			if (!m.matches())
				return "El email del cliente no es valido";
		}
		
	    if(StringUtils.isNotBlank(f.getNumDocuIdentidad())){
	    	if(StringUtils.isNotBlank(f.getCodigoPais()) && StringUtils.isNotBlank(f.getTipoDocumento())){
	    		AjaxService ajax = (AjaxService) getBean("ajaxService");
				String datos=ajax.validarDocumentoIdentidadSTO(f.getCodigoPais(), f.getTipoDocumento(),f.getNumDocuIdentidad());
				if(StringUtils.isNotBlank(datos))
					return datos;
			}
	    }
	    /*
	    if(!validarCamposEntradaCaracteres1())
	    	return this.msjeCampo1;
	    	 */
	    if(!validarCamposEntradaCaracteres2())
	    	return this.msjeCampo2;
	    if(!validarCamposEntradaCaracteres3())
	    	return this.msjeCampo3;
	   
		return "";
	}
	
	public boolean validarCamposEntradaCaracteres1() {
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;
		String campos1="";
		String cadena=f.getCadenaCaracteresNV1();
		if(!f.isValidarCaracteres1())
			return true;
		if(f.isValidarCaracteres1()){
			boolean b_codCompania=validarTextoCaracteresNoValidos(cadena, f.getCodCompania());			
			boolean b_codCliente =           validarTextoCaracteresNoValidos(cadena, f.getCodCliente());
			boolean b_numDocumento =         validarTextoCaracteresNoValidos(cadena, f.getNumDocumento());
			boolean b_unidadAdministrativa = validarTextoCaracteresNoValidos(cadena, f.getUnidadAdministrativa());
			boolean b_codPeriodo =           validarTextoCaracteresNoValidos(cadena, f.getCodPeriodo());
			boolean b_valApellido1 =         validarTextoCaracteresNoValidos(cadena, f.getValApellido1());
			boolean b_valApellido2 =         validarTextoCaracteresNoValidos(cadena, f.getValApellido2());
			boolean b_valNombre1 =           validarTextoCaracteresNoValidos(cadena, f.getValNombre1());
			boolean b_valNombre2 =           validarTextoCaracteresNoValidos(cadena, f.getValNombre2());
			boolean b_numDocuIdentidad =     validarTextoCaracteresNoValidos(cadena, f.getNumDocuIdentidad());
			boolean b_numRUC =               validarTextoCaracteresNoValidos(cadena, f.getNumRUC());
			boolean b_valDirecCliente =      validarTextoCaracteresNoValidos(cadena, f.getValDirecCliente());
			boolean b_valBarrCliente =       validarTextoCaracteresNoValidos(cadena, f.getValBarrCliente());
			boolean b_valCiudCliente =       validarTextoCaracteresNoValidos(cadena, f.getValCiudCliente());
			boolean b_valDepaCliente =       validarTextoCaracteresNoValidos(cadena, f.getValDepaCliente());
			boolean b_valRegionArribo =      validarTextoCaracteresNoValidos(cadena, f.getValRegionArribo());
			boolean b_valZonaArribo =        validarTextoCaracteresNoValidos(cadena, f.getValZonaArribo());
			boolean b_tipoViaCliente =       validarTextoCaracteresNoValidos(cadena, f.getTipoViaCliente());
			boolean b_numDireCliente =       validarTextoCaracteresNoValidos(cadena, f.getNumDireCliente());
			boolean b_fecProceso =           validarTextoCaracteresNoValidos(cadena, f.getFechaProceso());
			boolean b_direccionEntrega = 	 validarTextoCaracteresNoValidos(cadena, f.getDireccionEntrega());
			
			if(!b_codCompania)campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.codCompania");			
			if(!b_codCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.codCliente");			
    		if(!b_numDocumento) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.numDocumento");    		
    		if(!b_unidadAdministrativa) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.unidadAdministrativa");    		
    		if(!b_codPeriodo) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.codPeriodo");
    		if(!b_valApellido1) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valApellido1");    		
    		if(!b_valApellido2) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valApellido2");
    		if(!b_valNombre1) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valNombre1");
    		if(!b_valNombre2) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valNombre2");    		
    		if(!b_numDocuIdentidad) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.numDocuIdentidad");
    		if(!b_numRUC) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.numRUC");
    		if(!b_valDirecCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valDirecCliente");    		
    		if(!b_valBarrCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valBarrCliente");
    		if(!b_valCiudCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valCiudCliente");
    		if(!b_valDepaCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valDepaCliente");
    		
    		if(b_valRegionArribo) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valRegionArribo");
    		if(b_valZonaArribo) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valZonaArribo");
    		if(b_tipoViaCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.tipoViaCliente");
    		if(b_numDireCliente) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.numDireCliente");
    		if(b_fecProceso) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.fecProceso");
    		if(b_direccionEntrega) campos1 = campos1 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.direccionEntrega");

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
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;
		String cadena2=f.getCadenaCaracteresV2();
		String campos2 = "";
		if(!f.isValidarCaracteres2())
			return true;
		
		if(f.isValidarCaracteres2()){
			boolean b_valTelfCliente =   validarTextoCaracteresValidos(cadena2, f.getValTelfCliente());
			boolean b_valCeluCliente =   validarTextoCaracteresValidos(cadena2, f.getValCeluCliente());
			boolean b_valTelfTrabajo =   validarTextoCaracteresValidos(cadena2, f.getValTelfTrabajo());
			boolean b_telefonoEntrega =  validarTextoCaracteresValidos(cadena2, f.getTelefonoEntrega());
			boolean b_celularEntrega = 	 validarTextoCaracteresValidos(cadena2, f.getCelularEntrega());

    		if(!b_valTelfCliente) campos2 = campos2 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valTelfCliente") ;
    		if(!b_valCeluCliente) campos2 = campos2 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valCeluCliente");
    		if(!b_valTelfTrabajo) campos2 = campos2 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valTelfTrabajo") ;
    		if(!b_telefonoEntrega) campos2 = campos2 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.telefonoEntrega");
    		if(!b_celularEntrega) campos2 = campos2 + "," + this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.celularEntrega") ;

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
		MantenimientoSTOActualizacionDatosForm f =(MantenimientoSTOActualizacionDatosForm)this.formBusqueda;	
		String campos3 = "";
		if(!f.isValidarCaracteres3())
			return true;
		if(f.isValidarCaracteres3()){		
    		boolean b_valMailCliente = validarTextoCaracteresNoValidos(f.getCadenaCaracteresNV3(), f.getValMailCliente());
    		if(!b_valMailCliente) campos3 = this.getResourceMessage("mantenimientoSTOActualizacionDatosForm.valMailCliente");
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
	
	//Salir
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

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

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getsCodDepaCliente() {
		return sCodDepaCliente;
	}

	public void setsCodDepaCliente(String sCodDepaCliente) {
		this.sCodDepaCliente = sCodDepaCliente;
	}

	public String getsCodProvCliente() {
		return sCodProvCliente;
	}

	public void setsCodProvCliente(String sCodProvCliente) {
		this.sCodProvCliente = sCodProvCliente;
	}

	public String getsCodDistCliente() {
		return sCodDistCliente;
	}

	public void setsCodDistCliente(String sCodDistCliente) {
		this.sCodDistCliente = sCodDistCliente;
	}

	public String getsCodSectCliente() {
		return sCodSectCliente;
	}

	public void setsCodSectCliente(String sCodSectCliente) {
		this.sCodSectCliente = sCodSectCliente;
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

	public boolean isIndValidacionOK() {
		return indValidacionOK;
	}

	public void setIndValidacionOK(boolean indValidacionOK) {
		this.indValidacionOK = indValidacionOK;
	}

	public boolean isIndDesmarcaCampos() {
		return indDesmarcaCampos;
	}

	public void setIndDesmarcaCampos(boolean indDesmarcaCampos) {
		this.indDesmarcaCampos = indDesmarcaCampos;
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
}
