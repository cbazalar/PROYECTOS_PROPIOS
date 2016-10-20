package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.FamiliaSegura;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.ConsultaHIPDatosClienteAction;
import biz.belcorp.ssicc.web.scsicc.hip.form.ConsultaHIPDatosClienteForm;
import biz.belcorp.ssicc.web.spusicc.sgr.action.MantenimientoSGRInscripcionPolizaSearchAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOFamiliaSeguraForm;
import biz.belcorp.ssicc.web.util.StringUtil;


@ManagedBean
@SessionScoped
public class MantenimientoSTOFamiliaSeguraAction extends BaseMantenimientoSTOGestionAction{
	
	private static final long serialVersionUID = 1827017213582224432L;
	
	private List stoTipoDocList;
	private boolean editable;
	
	@ManagedProperty(value = "#{consultaHIPDatosClienteAction}")
	protected ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction;	
	
	//Modulo Registro de Poliza
	@ManagedProperty(value = "#{mantenimientoSGRInscripcionPolizaSearchAction}")
	protected MantenimientoSGRInscripcionPolizaSearchAction mantSGRInscripcionPolizaSearch;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOFamiliaSeguraForm  form = new MantenimientoSTOFamiliaSeguraForm();
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
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		Map criteria = new HashMap(); 
		MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;
		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = 
			(ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		f.setSalirPantalla(Constants.SI);
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("numSecDocumento",f.getNumSecuencia());
		criteria.put("numLote",f.getNumLote());
		criteria.put("codigoCliente",f.getCodCliente());
		criteria.put("oidCliente",reporteService.getOidString("getOidClienteByCodigoCliente",criteria));
		criteria.put("codTipoDocuIdent",f.getCodTipoDocumentoIdentidad());
		criteria.put("numDocuIdent",f.getCodNumeDocumentoIdentidad());
		criteria.put("numCoasegurado",f.getNumCoasegurado());
		criteria.put("beneficiario1",f.getNomBeneficiario1());
		criteria.put("codTipoDocuIdent1",f.getCodTipoDocumentoIdentidad1());
		criteria.put("numDocuIdent1",f.getCodNumeDocumentoIdentidad1());
		criteria.put("beneficiario2",f.getNomBeneficiario2());
		criteria.put("codTipoDocuIdent2",f.getCodTipoDocumentoIdentidad2());
		criteria.put("numDocuIdent2",f.getCodNumeDocumentoIdentidad2());
		criteria.put("codigoRegion",f.getCodigoRegion());
		criteria.put("codigoZona",f.getCodigoZona());
		criteria.put("firmaBlanco",f.getFirmaBlanco());

		procesoSTOEjecucionValidacionesService.updateFamiliaSegura(criteria);	

		return true;
	}
	
	public void inicializarValores() throws Exception {	
		this.obtenerValores();
		this.activarGrabarWindowClose=true;
		this.activarVentanaConfirmacionSave=false;
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		Map criteria = new HashMap();

		f.setCodigoPais(pais.getCodigo());
		f.setSalirPantalla(Constants.NO);

		criteriaOperacion.put("codigoPais", pais.getCodigo());
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteriaOperacion);
		f.setOidPais(String.valueOf(oidPais));

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = 
			(ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
    
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

		List ListaFamiliaSegura = procesoSTOEjecucionValidacionesService.getFamiliaSegura(criteria);

		FamiliaSegura familia = null;

		if (ListaFamiliaSegura.size()>0){
			familia = (FamiliaSegura)ListaFamiliaSegura.get(0);
		}
		else{
			familia = new FamiliaSegura();
		}

		setFamiliaSegura(f,familia);

		criteria.put("codCliente",f.getCodCliente());
		f.setNombreConsultora(procesoSTOEjecucionValidacionesService.getNombreConsultora(criteria));
		f.setDetalle(gestion.getValidacion()+" - "+gestion.getDesValidacion()+" - "+gestion.getDesValidacionLarga());

		//-- Setea combo tipo documento		
		this.stoTipoDocList=procesoSTOEjecucionValidacionesService.getTipoDocumento();
		String codValidacion = gestion.getValidacion();
		
		if (codValidacion.equals("FAS-1") || codValidacion.equals("FAS-6")){
			f.setIndicadorLink(true);
		}
		else{
			f.setIndicadorLink(false);
		}
		
		//PER-SiCC-2013-0910 @ghuertasa inicio
		
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) 
		getBean("spusicc.mantenimientoMAEClienteService");
		Map criteria1 = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		//longitud de codigo de cliente para el pais
		f.setLongitudCodigoCliente(clienteService.getLongitudCodigoCliente(criteria));
		
		
		String indicadorCompletarCerosNumDocumento  = clienteService.getValorModuloxPaisTipoValidacion(f.getCodigoPais(), Constants.HIP_VALID_COMPLETA_CEROS_DOCUMENTO_IDENTIDAD);
		
		if(StringUtils.isEmpty(indicadorCompletarCerosNumDocumento)) {
		f.setIndicadorCompletarCerosNumDocumento(true);
		//longitud de nśmero de documento de identidad para el paĶs
		f.setLongitudNumeroDocIdentidad(clienteService.getLongitudNumeroDocIdentidad(criteria));
		}
		else
		f.setIndicadorCompletarCerosNumDocumento(false);
		
		//PER-SiCC-2013-0910 @ghuertasa fin
		//PER-SiCC-2014-0634 @ghuertasa ini
		//solo en el caso que no exista edad
		if(StringUtils.isBlank(f.getValEdad()))
		{
			//revisamos que exista fecha de nacimiento
			if(StringUtils.isBlank(f.getFechaNacimiento()))
			{
				Map criteriaFecha = new HashMap();
				//si no existe fecha de nacimiento entonces realizamos la consulta
				if(StringUtils.isNotBlank(f.getCodigoCliente())){
					//se verifica que de base de datos llego el codigo del cliente, tambien puede venir vacķo
					criteriaFecha.put("codigoCliente", f.getCodigoCliente());
					String fechaNacimiento=procesoSTOEjecucionValidacionesService.getFechaNacimientoByCodigoCliente(criteriaFecha);
					if(fechaNacimiento!=null && StringUtils.isNotBlank(fechaNacimiento)){
						f.setFechaNacimiento(fechaNacimiento);
					}	
				}
			}
			//puede que el query no devuelva fecha o no se haya realizado el query se vuelve a preguntar si existe fecha de nacimiento
			if(StringUtils.isNotBlank(f.getFechaNacimiento())){
				int edad = StringUtil.calcularEdad(f.getFechaNacimiento());
				if(edad>0)
					f.setValEdad(String.valueOf(edad));	
			}
		}	
	}
	
	/**
	 * Metodo que setea los valores del jsp de Familia Segura	
	 */
	private void setFamiliaSegura(MantenimientoSTOFamiliaSeguraForm f, FamiliaSegura familia ) {

		f.setCodigoPais(familia.getCodPais());
		f.setCodCompania(familia.getCodCompania());
		f.setCodCliente(familia.getCodCliente());
		f.setCodTipoDocumentoIdentidad(familia.getCodTipoDocumentoIdentidad());
		f.setCodNumeDocumentoIdentidad(familia.getCodNumeDocumentoIdentidad());
		f.setFechaProceso(familia.getFechaProceso());
		f.setCodCampanaProc(familia.getCodCampanaProc());
		f.setCodCampanaInicio(familia.getCodCampanaInicio());
		f.setNumCoasegurado(familia.getNumCoasegurado());
		f.setNumLote(familia.getNumLote());
		f.setNumSecuencia(familia.getNumSecuencia());
		f.setCodCampanaRegistro(familia.getCodCampanaRegistro());
		f.setNomBeneficiario1(familia.getNomBeneficiario1());
		f.setCodTipoDocumentoIdentidad1(familia.getCodTipoDocumentoIdentidad1());
		f.setCodNumeDocumentoIdentidad1(familia.getCodNumeDocumentoIdentidad1());
		f.setNomBeneficiario2(familia.getNomBeneficiario2());
		f.setCodTipoDocumentoIdentidad2(familia.getCodTipoDocumentoIdentidad2());
		f.setCodNumeDocumentoIdentidad2(familia.getCodNumeDocumentoIdentidad2());
		f.setFechaNacimiento(familia.getFechaNacimiento());
		f.setCodSexo(familia.getCodSexo());
		f.setCodEstadoCivil(familia.getCodEstadoCivil());
		f.setValEdad(familia.getValEdad());
		f.setCodEstadoOCR(familia.getCodEstadoOCR());
		f.setMotRechazoOCR(familia.getMotRechazoOCR());
		f.setCodigoRegion(familia.getCodigoRegion());
		f.setCodigoZona(familia.getCodigoZona());
		f.setFirmaBlanco(familia.getFirmaBlanco());

	}
	
	public String completarCaracteres(String valor, String longitud, String caracter){
		String valorAux = "";
		int faltante=0;
		int nLongitud=Integer.parseInt(longitud) ;
		if (valor.length() != 0) {
			faltante = nLongitud - valor.length();
			valorAux = "";
			
			if (faltante >= 0) {
				for (int i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + valor;
			}
			else {
			
				faltante = valor.length() -nLongitud;
				valorAux = valor.substring(faltante, nLongitud);
			}
		}
		
		return valorAux;
	}
	//Completar codigo de Cliente, 
	public void completarCodigoCliente(){
		String codigoNuevo="";
		MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;		
		if(StringUtils.isNotBlank(f.getCodCliente()) && StringUtils.isNotBlank(f.getLongitudCodigoCliente())){
			codigoNuevo= completarCaracteres(f.getCodCliente(), f.getLongitudCodigoCliente(),"0");
			f.setCodCliente(codigoNuevo);
		}
    	
  		
	}
	
	// Valida Cod Cliente y completa nombreCliente, tipo doc y num doc Identidad
	public void validarCodigoCliente(){
		MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.completarCodigoCliente();
		this.getNombre();
    	String data=ajax.getCodigoNumeroTipoDocumentoConsultoraPorCodigo(f.getCodCliente());    	
    	if(StringUtils.isBlank(data)){
			f.setCodCliente("");
		}else{
			String matriz[] = data.split("-",4);
			f.setCodTipoDocumentoIdentidad(matriz[0]);
			f.setCodNumeDocumentoIdentidad(matriz[1]);
			f.setCodCliente(matriz[2]);	
		}   
	}
	
	//Completar nro dni
	public void completarNumeroDocIdentidad(){
		String numDocIdentidad ="";
		MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;		
		if(f.isIndicadorCompletarCerosNumDocumento())		
			numDocIdentidad = completarCaracteres(f.getCodNumeDocumentoIdentidad(), f.getLongitudNumeroDocIdentidad(), "0");
		f.setCodNumeDocumentoIdentidad(numDocIdentidad);   	
		
	}
	//Valida el nro de DNI completa cod Cliente, Nro Doc y Tipo Doc
	public void validarNumeroDocIdentidad(){
		MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;	
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		completarNumeroDocIdentidad();
		String data=ajax.getCodigoNumeroTipoDocumentoConsultoraPorDocumento(f.getCodNumeDocumentoIdentidad());
		this.getNombre();
		if(StringUtils.isBlank(data)){			
			f.setCodNumeDocumentoIdentidad("");			
		}else{
			String matriz[] = data.split("-",4);
			f.setCodTipoDocumentoIdentidad(matriz[0]);
			f.setCodNumeDocumentoIdentidad(matriz[1]);
			f.setCodCliente(matriz[2]);	
		} 		
	}
	
	//Obtener nombre del Cliente
	public void getNombre(){
		MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;	
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		 if(StringUtils.isNotBlank(f.getCodCliente())){
	  	    	String nombre=ajax.getNombreCliente(f.getCodCliente());
	  	    	if(StringUtils.isNotBlank(nombre))
	  	    		f.setNombreConsultora(nombre);
	  	    	else
	  	    		f.setNombreConsultora("");
	  	} else    	
	  		  f.setNombreConsultora("");  	
	}
	
	public String setValidarMantenimiento(){
		MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;	
		if(StringUtils.isBlank(f.getNombreConsultora()))
			return this.getResourceMessage("mensaje.mantenimientoSTOFamiliaSeguraForm.codigoClienteNoExiste");
		return "";
	}
	
	public void openModuloRegistroPoliza(ActionEvent actionEvent) {
		try {
			this.mantSGRInscripcionPolizaSearch.setMostrarBotonSalir(false);
			this.redireccionarPagina("mantenimientoSGRInscripcionPolizaListSTO");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("procesoSTOLevantamientoErroresValidacionForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	//Abrir HiperConsulta
	public void linkHiperconsulta(ActionEvent event) {
		try {
			MantenimientoSTOFamiliaSeguraForm f =(MantenimientoSTOFamiliaSeguraForm)this.formBusqueda;	
			ConsultaHIPDatosClienteForm searchForm = (ConsultaHIPDatosClienteForm)  this.consultaHIPDatosClienteAction.getFormBusqueda();	
			searchForm.setCodigoClienteBuscar(f.getCodCliente());
			searchForm.setCodigoPais(f.getCodigoPais());
			this.consultaHIPDatosClienteAction.setPaginaPadre("mantenimientoSTOFamiliaSeguraForm");
			this.consultaHIPDatosClienteAction.find();
			this.redireccionarPagina("consultaHIPDatosClienteFormSTO");
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

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public ConsultaHIPDatosClienteAction getConsultaHIPDatosClienteAction() {
		return consultaHIPDatosClienteAction;
	}

	public void setConsultaHIPDatosClienteAction(
			ConsultaHIPDatosClienteAction consultaHIPDatosClienteAction) {
		this.consultaHIPDatosClienteAction = consultaHIPDatosClienteAction;
	}

	public MantenimientoSGRInscripcionPolizaSearchAction getMantSGRInscripcionPolizaSearch() {
		return mantSGRInscripcionPolizaSearch;
	}

	public void setMantSGRInscripcionPolizaSearch(
			MantenimientoSGRInscripcionPolizaSearchAction mantSGRInscripcionPolizaSearch) {
		this.mantSGRInscripcionPolizaSearch = mantSGRInscripcionPolizaSearch;
	}
	
	


}
