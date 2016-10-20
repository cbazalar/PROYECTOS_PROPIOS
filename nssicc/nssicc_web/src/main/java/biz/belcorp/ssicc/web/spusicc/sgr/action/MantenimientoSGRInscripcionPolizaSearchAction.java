package biz.belcorp.ssicc.web.spusicc.sgr.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.geometry.spherical.oned.ArcsSet.Split;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.DocumentoDigitadoPK;
import biz.belcorp.ssicc.dao.spusicc.sto.model.GestionDocumento;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sgr.MantenimientoSGRGenericoService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.sgr.form.MantenimientoSGRInscripcionPolizaForm;
import biz.belcorp.ssicc.web.spusicc.sgr.form.MantenimientoSGRInscripcionPolizaSearchForm;

@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class MantenimientoSGRInscripcionPolizaSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 4261073419581378133L;
	private List siccSgrEstadoList;
	private List siccSgrOrigenList;
	private List stoTipoDocList;
	private List sgrBeneficiariosList;
	private List sgrMantenimientoInscripcionList;
	private String codigoMotivoRechazo;
	private boolean indicadorValidaTipoDoc;
	private Boolean mostrarBGuardarP;
	private String motivoRechazo;
	private String mostrarBeneficiarios;
	private String seguir;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction
	 * #getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSGRInscripcionPolizaForm";
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
		MantenimientoSGRInscripcionPolizaSearchForm searchForm = new MantenimientoSGRInscripcionPolizaSearchForm();
		return searchForm;
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
		MantenimientoSGRInscripcionPolizaSearchForm f = (MantenimientoSGRInscripcionPolizaSearchForm) this.formBusqueda;
		String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
		f.setCodigoPais(codigoPais);
		this.mostrarBotonEliminar = false;
		this.mostrarBotonSave = false;
		ArrayList resultado = new ArrayList();
		String[] descripcion = { "ACEPTADA / REGISTRADA", "RECHAZADA",
				"ACTIVA", "CANCELADA" };
		Base estadoIni = new Base();
		estadoIni.setCodigo("");
		estadoIni.setDescripcion("");
		resultado.add(estadoIni);
		for (int i = 0; i < descripcion.length; i++) {
			Base estado = new Base();
			estado.setCodigo("" + (i + 1));
			estado.setDescripcion(descripcion[i]);
			resultado.add(estado);
		}
		this.siccSgrEstadoList = resultado;

		ArrayList resultadoOrigen = new ArrayList();
		String[] codigoOrigen = { "C", "W", "O", "B" };
		String[] descripcionOrigen = { "SISTEMA COMERCIAL", "WEB", "OCR",
				"BLACKBERRY" };
		Base origenIni = new Base();
		origenIni.setCodigo("");
		origenIni.setDescripcion("");
		resultadoOrigen.add(origenIni);

		for (int i = 0; i < descripcionOrigen.length; i++) {
			Base origen = new Base();
			origen.setCodigo(codigoOrigen[i]);
			origen.setDescripcion(descripcionOrigen[i]);
			resultadoOrigen.add(origen);
		}
		this.siccSgrOrigenList = resultadoOrigen;

		ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");

		this.stoTipoDocList = procesoSTOEjecucionValidacionesService
				.getTipoDocumento();

		this.log.debug("Todo Ok: Redireccionando");
		
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
		MantenimientoSGRInscripcionPolizaSearchForm f = (MantenimientoSGRInscripcionPolizaSearchForm) this.formBusqueda;
		MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService) this
				.getBean("spusicc.mantenimientoSGRGenericoService");

		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);
		/* Obteniendo Lista */
		List resultado = service.getInscripcionPoliza(criteria);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		if (!this.accion.equals(this.ACCION_NUEVO)) {
		if (this.beanRegistroSeleccionado != null) {

			Map map = (Map) this.beanRegistroSeleccionado;
			String estado = (String) map.get("estado");
			if (StringUtils.isNotEmpty(estado)) {
				if (!estado.equals("1")) {
					String error = this
							.getResourceMessage("mantenimientoSGRInscripcionPolizaForm.poliza.estado.registrado");

					return error;
				}
			}
		}
		}
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		String parametroVentana = externalContext.getRequestParameterMap().get(
				"parametroVentana");
		if (parametroVentana.equals("confirmDialogGuardar")) {
			MantenimientoSGRInscripcionPolizaForm f = (MantenimientoSGRInscripcionPolizaForm) this.formMantenimiento;
//			if (StringUtils.isBlank(f.getNumeroDocumentoIdent())) {
//				return "Numero de documento de Identidad Requerido";
//
//			}
//
//			if (StringUtils.isBlank(f.getTipoDocumentoIdentidad())) {
//				return "Tipo de documento de Identidad Requerido";
//
//			}

			if (StringUtils.isBlank(f.getCodigoPoliza())) {
				return "Numero de Poliza Requerido";

			}

			if (StringUtils.isBlank(f.getCodigoCliente())) {
				return "Codigo de Cliente Requerido";

			}

		}

		return null;
	}

	public void eliminarPersonalizado(ActionEvent evt) {
		try {
			String codigoPais = this.mPantallaPrincipalBean.getCountryCode();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService) this
					.getBean("spusicc.mantenimientoSGRGenericoService");
			Map map = (Map) this.beanRegistroSeleccionado;

			map.put("codigoPais", codigoPais);
			map.put("login", usuario.getLogin());

			String estado = (String) map.get("estado");

			if (StringUtils.isNotEmpty(estado)) {
				if (estado.equals("3")) {

					// generamos el map para obtener la fecha y campa�a activa
					Map criteria = new HashMap();
					criteria.put("codigoPais", codigoPais);
					criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																			// Campanha
																			// Activa
					criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																				// Campanha
																				// activa
																				// q
																				// se
																				// procesa
																				// actualmente
					MantenimientoOCRPedidoControlFacturacionService servicePed = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
					List listPed = servicePed
							.getControlFacturacionByCriteria(criteria);
					PedidoControlFacturacion pedido = (PedidoControlFacturacion) listPed
							.get(0);

					// adicionamos los datos a modificar
					map.put("fechaFacturacion", pedido.getFechaProceso());
					map.put("campanhaCancelacion", pedido.getCodigoPeriodo());
					service.updateInscripcionPolizaActiva(map);
					this.addInfo(
							"Info : ",
							this.getResourceMessage("mantenimientoSGRInscripcionPolizaSearchForm.cabecera.actualizada"));
					this.listaBusqueda = this.setFindAttributes();
					this.datatableBusqueda = new DataTableModel(
							this.listaBusqueda);
					return;
				}
			}

			if (StringUtils.isNotEmpty(estado)) {
				if (!estado.equals("1")) {
					String error = this
							.getResourceMessage("mantenimientoSGRInscripcionPolizaSearchForm.cabecera.deleted.validarRegistrada");
					this.addError("Error : ", error);
					return;
				}
			}

			service.deleteInscripcionPoliza(map);
			// enviamos el mensaje de satisfaccion
			this.addInfo(
					"Info : ",
					this.getResourceMessage("mantenimientoSGRInscripcionPolizaSearchForm.cabecera.deleted"));

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param evt
	 */
	public void guardarPersonalizado(ActionEvent evt) {
		try{
			MantenimientoSGRInscripcionPolizaForm f = (MantenimientoSGRInscripcionPolizaForm) this.formMantenimiento;

			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			Map criteria = new HashMap();
	        criteria.put("codigoPais", f.getCodigoPais());
	        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
	        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
	        criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
	        
	        //recuperamos el oid Pais
	        String oidPais = clienteService.getOidPais(criteria);
	        f.setOidPais(oidPais);
	        criteria.put("oidPais", oidPais);
	        //recuperamos la longitud del codigo de cliente para el pais logueado
	        f.setLongitudCodigoCliente(clienteService.getLongitudCodigoCliente(criteria));
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			
			String valores = ajax.getExisteCodigoCliente(f.getOidPais(), f.getCodigoCliente());
			if(!StringUtils.isBlank(valores)){
				String[] valorArreglo = StringUtils.split(valores, "|");
				f.setNombreCliente(valorArreglo[1]);
		  	  	String documentoIdentidad = ajax.getNumDocumentoIdentByCodigoCliente(f.getCodigoCliente(),f.getTipoDocumentoIdentidad());
		  	  	f.setNumeroDocumentoIdent(documentoIdentidad);
		  	  	Integer valorExisteConsultora =  ajax.getExisteConsultoraPolizaActiva(f.getCodigoCliente());
		  	  	if(valorExisteConsultora*1 > 0){
		  	  	this.addWarn("Aviso : ", this.getResourceMessage("mantenimientoSGRInscripcionPolizaForm.consultora.poliza.activa"));
		  	  	return;
		  	  	}
			}
			
		MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService) 
															getBean("spusicc.mantenimientoSGRGenericoService");
		
		Map map = BeanUtils.describe(f);
		map.put("login",usuario.getLogin());
//		String seguir =  request.getParameter("seguir");
		log.debug("seguir "+ seguir);
		List list= this.sgrBeneficiariosList;
			
			if(f.isNewRecord()){
				map.put("indicadorNuevo", Constants.NRO_UNO);//1:INSERTAR 0:ACTUALIZAR
				if(list.size()>=0){
					map.put("listBenef",list);			
					map.put("numeroAsegurados", String.valueOf(list.size()));
					if(StringUtils.isNotEmpty(seguir) && Constants.NRO_UNO.equals(seguir)){
						//Map beanResultado = (Map)session.getAttribute("beanResultado");
						//String codigoMotivoRechazo = (String)session.getAttribute("codigoMotivoRechazo");
						
						map.put("codigoMotivoRechazo", codigoMotivoRechazo==null?Constants.NUMERO_CERO:codigoMotivoRechazo);
						//si es 0:beneficiarios traen tipo de doucmneto nulos
						map.put("indicadorAccion", Constants.NRO_UNO);//0:VALIDACION 1: RECHAZO
						setDatosBenef(map,list);
						service.executeValidacionesInscripcionPoliza(map);												
						this.sgrBeneficiariosList = new ArrayList();
					
				       
						f.setNombreCliente("");
						//se llama a STO
						//Ejecutando Validaciones STO
						Map params = setDatosSTO(map);
						params.put("codPais", pais.getCodigo());						
						params.put("codTipoDocu", "FAS");
						params.put("codigoPeriodo", "");
																		
						ProcesoSTOService procesoSTOService = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
						
						List lista = procesoSTOService.getDocumentoDigitadoPKByLote(params);
						
						List listaSTO = new ArrayList();
						for (Iterator iterator = lista.iterator(); iterator.hasNext();) {
				    		 DocumentoDigitadoPK documentoDigitadoPK = (DocumentoDigitadoPK) iterator.next();
				    		 GestionDocumento gestionDocumento = new GestionDocumento();
				    		 gestionDocumento.setCodigoPais(documentoDigitadoPK.getCodPais());
				    		 gestionDocumento.setNumeroDocumento(documentoDigitadoPK.getSecNumeDocu());
				    		 gestionDocumento.setLote(documentoDigitadoPK.getNumLote());
				    		 gestionDocumento.setDocumento(documentoDigitadoPK.getCodTipoDocu());
				    		 listaSTO.add(gestionDocumento);
				    		 
				  		}		
						params.put("codigoPais", pais.getCodigo());//usa esta variable en ves de codPais
						params.put("usuario",usuario);
						ProcesoSTOExecutionService execService = (ProcesoSTOExecutionService) getBean("spusicc.procesoSTOExecutionService");
						AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(pais.getCodigo(),Constants.STO_TIPO_DOCUMENTO_FAS,Constants.STO_ACCI_VALI_ON_LINE);
						execService.execute(accionTipoDocumento,params,listaSTO);						
						this.addInfo("Info : " , this.getResourceMessage("mantenimientoSGRInscripcionPolizaForm.cabecera.insert.rechazo"));
						this.mostrarBeneficiarios  = "";
							
						return;
					}
					//se procedera haceer las validaciones correspondientes y/o rechazos
					map.put("codigoMotivoRechazo", "");
					map.put("indicadorAccion", Constants.NRO_CERO);//0:VALIDACION 1: RECHAZO
					service.executeValidacionesInscripcionPoliza(map); 					
					Map bean = setDatos(map);//beanResultado					
					String indicadorRechazo = (String)bean.get("indicadorRechazoSTO");
					
					if(Constants.NUMERO_UNO.equals(indicadorRechazo)){//hay rechazo
						String motivoRechazo = (String)bean.get("motivoRechazo");
						String codigoMotivoRechazo = (String)bean.get("codigoMotivoRechazo");
						this.motivoRechazo = motivoRechazo;
						this.codigoMotivoRechazo = codigoMotivoRechazo;
						this.sgrBeneficiariosList = list;
						mostrarBeneficiarios = Constants.NRO_UNO;	
			            return;
						//session.setAttribute("beanResultado", bean);
					}else{
						//insertamos campa de proceos
						InterfazSiCCService serviceSicc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
				        String campanhaProceso = serviceSicc.getPeriodoDefaultByPaisCanal(pais.getCodigo(),
																Constants.CODIGO_CANAL_DEFAULT);
				        map.put("campanhaProceso",campanhaProceso );
						map.putAll(bean);
						service.saveInscripcionPoliza(map);
						f.setNombreCliente("");
						this.sgrBeneficiariosList = new ArrayList();
						this.mostrarBeneficiarios = "";
						this.addInfo("Info : ", this.getResourceMessage("mantenimientoSGRInscripcionPolizaForm.cabecera.insert"));
						this.salirGrabarPantallaPadre = true;					
					}									
				}									
			}else{
				map.put("indicadorNuevo", Constants.NRO_CERO);//1:INSERTAR 0:ACTUALIZAR
				if(list.size()>=0){
					map.put("listBenef",list);			
					map.put("numeroAsegurados", String.valueOf(list.size()));
					service.updateInscripcionPoliza(map);
					f.setNombreCliente("");
					this.sgrBeneficiariosList = new ArrayList();
					this.mostrarBeneficiarios = "";
					this.addInfo("Info : ", "mantenimientoSGRInscripcionPolizaForm.cabecera.insert");
					this.salirGrabarPantallaPadre = true;					
				}
			}
			log.debug("enviado find");
			List resultado = service.getInscripcionPoliza(map);
			this.sgrMantenimientoInscripcionList = resultado;
			return;
		}catch(Exception e){
			this.addError("Error : ", this.getResourceMessage("mantenimientoSGRInscripcionPolizaForm.cabecera.insert.constraint"));
			return;
		}
		

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
		return false;

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSGRInscripcionPolizaList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;
		MantenimientoSGRInscripcionPolizaForm mantenimientoForm = new MantenimientoSGRInscripcionPolizaForm();
		mantenimientoForm.setCodigoPais(this.mPantallaPrincipalBean
				.getCountryCode());
		this.sgrBeneficiariosList = new ArrayList();
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			BeanUtils.copyProperties(mantenimientoForm, mapa);
			mantenimientoForm.setNewRecord(false);
			this.mostrarBGuardarP = false;
		} else {
			mantenimientoForm.setNewRecord(true);
			MantenimientoSGRGenericoService service = (MantenimientoSGRGenericoService) getBean("spusicc.mantenimientoSGRGenericoService");
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
			// obtener poilza activa
			Map criteria = new HashMap();
			criteria.put("indicadorActivo", Constants.NRO_UNO);
			List resultado = service.getPoliza(criteria);
			if (resultado.size() == 0) {
				mantenimientoForm.setCodigoPoliza("");
				throw new Exception(
						this.getResourceMessage("mantenimientoSGRPolizaForm.poliza.noactiva"));
			}

			Map map = (Map) resultado.get(0);
			mantenimientoForm.setCodigoPoliza((String) map.get("codigoPoliza"));
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			setParametrosIniciales(usuario, clienteService, mantenimientoForm);
			mantenimientoForm.setIndicadorInsertar(Constants.NRO_UNO);
			mantenimientoForm.setNombreCliente(null);

			//
			String indicadorValidaTipoDoc = null;

			GenericoService genericoService = (GenericoService) getBean("genericoService");

			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(mantenimientoForm.getCodigoPais());
			parametroPais.setCodigoSistema("SGR");
			parametroPais.setNombreParametro("indValidaTipoDoc");
			parametroPais.setIndicadorActivo(Constants.NUMERO_UNO);

			List lstParametros = genericoService
					.getParametrosPais(parametroPais);

			if (lstParametros != null && lstParametros.size() > 0) {
				ParametroPais ps = (ParametroPais) lstParametros.get(0);
				indicadorValidaTipoDoc = ps.getValorParametro();
			}
			this.indicadorValidaTipoDoc = indicadorValidaTipoDoc != null
					&& Integer.valueOf(indicadorValidaTipoDoc) == 1 ? Boolean.FALSE
					: Boolean.TRUE;
			//

			// Obtenemos el digito inicial de la poliza
			String valDigitoInicialPoliza = "";
			String indicadorActivo = "";
			parametroPais.setNombreParametro("valDigitoInicialPoliza");
			parametroPais.setIndicadorActivo("");
			lstParametros = genericoService.getParametrosPais(parametroPais);
			if (lstParametros != null && lstParametros.size() > 0) {
				ParametroPais ps = (ParametroPais) lstParametros.get(0);
				indicadorActivo = ps.getIndicadorActivo();
				if (Constants.NUMERO_UNO.equals(indicadorActivo)) {
					valDigitoInicialPoliza = ps.getValorParametro();
				}
			}
			mantenimientoForm.setNumeroInicialPoliza(StringUtils
					.isBlank(valDigitoInicialPoliza) ? ""
					: valDigitoInicialPoliza);
			mantenimientoForm.setIndicadorActivo(indicadorActivo);
			// Seteamos el valor DNI
			mantenimientoForm
					.setTipoDocumentoIdentidad(Constants.MAE_ESTADO_REGISTRADA);
		}

		return mantenimientoForm;
	}

	/**
	 * Pone en session el oid del pais y la longitud de caracteres del cliente
	 * 
	 * @param usuario
	 * @param pais
	 * @param clienteService
	 * @param f
	 */
	private void setParametrosIniciales(Usuario usuario,
			MantenimientoMAEClienteService clienteService,
			MantenimientoSGRInscripcionPolizaForm f) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());

		// recuperamos el oid Pais
		String oidPais = clienteService.getOidPais(criteria);
		f.setOidPais(oidPais);
		criteria.put("oidPais", oidPais);
		// recuperamos la longitud del codigo de cliente para el pais logueado
		f.setLongitudCodigoCliente(clienteService
				.getLongitudCodigoCliente(criteria));
	}

	/**
	 * Se setea los datos resuktantes d ela validacion
	 * 
	 * @param map
	 * @return
	 */
	private Map setDatos(Map map) {
		Map bean = new HashMap();
		String resultado = (String) map.get("mensajeResultado");
		log.debug("mensaje Resultado : " + resultado);
		String[] parametros = StringUtils.split(resultado, ",");
		bean.put("indicadorRechazoSTO", parametros[0]);
		bean.put("oidCliente", parametros[1]);
		bean.put("campanhaRegistro", parametros[2]);
		bean.put("sexo", parametros[3]);
		bean.put("estadoCivil", parametros[4]);
		bean.put("fechaNacimiento", parametros[5].trim());
		bean.put("edadCliente", parametros[6]);
		bean.put("numeroCampanhasAnt", parametros[7]);
		bean.put("codigoMotivoRechazo", parametros[8]);
		bean.put("motivoRechazo", parametros[9]);
		return bean;
	}

	/**
	 * Se setea los datos resuktantes d ela validacion
	 * 
	 * @param map
	 * @return
	 */
	private Map setDatosSTO(Map map) {
		Map bean = new HashMap();
		String resultado = (String) map.get("mensajeResultado");
		log.debug("mensaje Resultado STO : " + resultado);
		String[] parametros = StringUtils.split(resultado, ",");
		bean.put("indicadorRechazoSTO", parametros[0]);
		bean.put("numSec", parametros[1]);
		bean.put("numLote", parametros[2]);
		return bean;
	}

	/**
	 * Completa los datos del beneficiario en el Map
	 * 
	 * @param map
	 * @param list
	 */
	private void setDatosBenef(Map map, List list) {
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map mapBenef = (Map) list.get(i);
				String nombre = (String) mapBenef.get("nombreBeneficiario")
						+ " " + (String) mapBenef.get("apellidoBeneficiario");
				String tipoDocu = (String) mapBenef
						.get("tipoDocumentoIdentidadBenef");
				String numDocu = (String) mapBenef
						.get("numeroDocumentoIdentBenef");
				switch (i) {
				case 0:
					map.put("nombreBenef1", nombre);
					map.put("tipoDocuBenef1", tipoDocu);
					map.put("numDocuBenef1", numDocu);
					break;
				case 1:
					map.put("nombreBenef2", nombre);
					map.put("tipoDocuBenef2", tipoDocu);
					map.put("numDocuBenef2", numDocu);
					return;
				default:
					break;
				}
			}
		}
	}

	/**
	 * 
	 */
	public void validaNumeroPoliza() {
		MantenimientoSGRInscripcionPolizaForm f = (MantenimientoSGRInscripcionPolizaForm) this.formMantenimiento;
		f.setNumeroCertificado(completarCaracteres(f.getNumeroCertificado(),
				"8", "0"));
	}

	/**
	 * @param valor
	 * @param longitud
	 * @param caracter
	 * @return
	 */
	private String completarCaracteres(String valor, String longitud,
			String caracter) {
		String valorAux = new String("");
		if (valor.length() != 0) {
			Integer faltante = Integer.parseInt(longitud) - valor.length();
			valorAux = new String("");

			if (faltante >= 0) {
				for (int i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + valor;
			} else {
				faltante = valor.length() - Integer.parseInt(longitud);
				valorAux = valor
						.substring(faltante, Integer.parseInt(longitud));
			}
		}
		return valorAux;
	}

	/**
	 * 
	 */
	public void validaCodigoCliente() {
		MantenimientoSGRInscripcionPolizaForm f = (MantenimientoSGRInscripcionPolizaForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoCliente = f.getCodigoCliente();
		String idPais = f.getCodigoPais();
		String longitudCodigoCliente = f.getLongitudCodigoCliente();
		String numeroInicialPoliza = f.getNumeroInicialPoliza();
		String numeroCertificado = f.getNumeroCertificado();
		String indicadorActivo = f.getIndicadorActivo();

		if (!codigoCliente.isEmpty() && !numeroCertificado.isEmpty()) {
			codigoCliente = completarCaracteres(codigoCliente,
					longitudCodigoCliente, "0");
			if (!numeroInicialPoliza.isEmpty()) {
				String nroCertificadoAux = numeroCertificado.substring(0,
						numeroInicialPoliza.length());
				if (!nroCertificadoAux.equals(numeroInicialPoliza)) {
					this.addWarn(
							"Info: ",
							this.getResourceMessage(
									"mantenimientoSGRInscripcionPolizaForm.numeroPoliza.incorrecto")
									.concat(numeroInicialPoliza));
					return;
				}
			} else {
				if (indicadorActivo.isEmpty()) {
					this.addWarn(
							"Info: ",
							this.getResourceMessage("mantenimientoSGRInscripcionPolizaForm.numeroPoliza.noexiste"));
					return;
				}
			}
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			f.setCodigoPais(pais.getCodigo());
	        Map criteria = new HashMap();
	        criteria.put("codigoPais", f.getCodigoPais());
	        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
	        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
	        criteria.put("codigoISO", usuario.getIdioma().getCodigoISO());
	        
	        //recuperamos el oid Pais
	        String oidPais = clienteService.getOidPais(criteria);
	        f.setOidPais(oidPais);
	        criteria.put("oidPais", oidPais);
	        //recuperamos la longitud del codigo de cliente para el pais logueado
	        f.setLongitudCodigoCliente(clienteService.getLongitudCodigoCliente(criteria));
			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			
			String valores = ajax.getExisteCodigoCliente(f.getOidPais(), codigoCliente);
			if(!StringUtils.isBlank(valores)){
				String[] valorArreglo = StringUtils.split(valores, "|");
				f.setNombreCliente(valorArreglo[1]);
		  	  	String documentoIdentidad = ajax.getNumDocumentoIdentByCodigoCliente(codigoCliente,f.getTipoDocumentoIdentidad());
		  	  	f.setNumeroDocumentoIdent(documentoIdentidad);
			}else{
				this.addWarn("Aviso : ", "El codigo de cliente no existe");
			}
			this.mostrarBGuardarP = true;
		} else {
			f.setNombreCliente("");
			this.mostrarBGuardarP = false;
		}
	}

	/**
	 * @param actionEvent
	 */
	public void salirAPantallaPadre(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("mantenimientoSTOFamiliaSeguraForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param actionEvent
	 */
	public void salirAPantallaHijo(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("mantenimientoSGRInscripcionPolizaListSTO");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @return the siccSgrEstadoList
	 */
	public List getSiccSgrEstadoList() {
		return siccSgrEstadoList;
	}

	/**
	 * @param siccSgrEstadoList
	 *            the siccSgrEstadoList to set
	 */
	public void setSiccSgrEstadoList(List siccSgrEstadoList) {
		this.siccSgrEstadoList = siccSgrEstadoList;
	}

	/**
	 * @return the siccSgrOrigenList
	 */
	public List getSiccSgrOrigenList() {
		return siccSgrOrigenList;
	}

	/**
	 * @param siccSgrOrigenList
	 *            the siccSgrOrigenList to set
	 */
	public void setSiccSgrOrigenList(List siccSgrOrigenList) {
		this.siccSgrOrigenList = siccSgrOrigenList;
	}

	/**
	 * @return the stoTipoDocList
	 */
	public List getStoTipoDocList() {
		return stoTipoDocList;
	}

	/**
	 * @param stoTipoDocList
	 *            the stoTipoDocList to set
	 */
	public void setStoTipoDocList(List stoTipoDocList) {
		this.stoTipoDocList = stoTipoDocList;
	}

	/**
	 * @return the sgrBeneficiariosList
	 */
	public List getSgrBeneficiariosList() {
		return sgrBeneficiariosList;
	}

	/**
	 * @param sgrBeneficiariosList
	 *            the sgrBeneficiariosList to set
	 */
	public void setSgrBeneficiariosList(List sgrBeneficiariosList) {
		this.sgrBeneficiariosList = sgrBeneficiariosList;
	}

	/**
	 * @return the sgrMantenimientoInscripcionList
	 */
	public List getSgrMantenimientoInscripcionList() {
		return sgrMantenimientoInscripcionList;
	}

	/**
	 * @param sgrMantenimientoInscripcionList
	 *            the sgrMantenimientoInscripcionList to set
	 */
	public void setSgrMantenimientoInscripcionList(
			List sgrMantenimientoInscripcionList) {
		this.sgrMantenimientoInscripcionList = sgrMantenimientoInscripcionList;
	}

	/**
	 * @return the codigoMotivoRechazo
	 */
	public String getCodigoMotivoRechazo() {
		return codigoMotivoRechazo;
	}

	/**
	 * @param codigoMotivoRechazo
	 *            the codigoMotivoRechazo to set
	 */
	public void setCodigoMotivoRechazo(String codigoMotivoRechazo) {
		this.codigoMotivoRechazo = codigoMotivoRechazo;
	}

	/**
	 * @return the indicadorValidaTipoDoc
	 */
	public boolean isIndicadorValidaTipoDoc() {
		return indicadorValidaTipoDoc;
	}

	/**
	 * @param indicadorValidaTipoDoc
	 *            the indicadorValidaTipoDoc to set
	 */
	public void setIndicadorValidaTipoDoc(boolean indicadorValidaTipoDoc) {
		this.indicadorValidaTipoDoc = indicadorValidaTipoDoc;
	}

	/**
	 * @return the mostrarBGuardarP
	 */
	public Boolean getMostrarBGuardarP() {
		return mostrarBGuardarP;
	}

	/**
	 * @param mostrarBGuardarP the mostrarBGuardarP to set
	 */
	public void setMostrarBGuardarP(Boolean mostrarBGuardarP) {
		this.mostrarBGuardarP = mostrarBGuardarP;
	}
}