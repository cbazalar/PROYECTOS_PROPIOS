package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOLimiteVentaFocalizadoConsejeraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOLimiteVentaFocalizadoConsejeraForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -9101034491694659451L;
	private List stoLevantamientoErroresClientesList;
	private List stoResumenClientesList;
	private List stoLimiteVentaFocalizadoConsejeraList;
	private String attachment = "";
	private Boolean mostrarGrilla;
	private Integer longitudCampoClientes;


	/**
	 * Metodo para invocar al uploadArchivo()
	 * @param event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("handleFileUpload");
		}
		String mensaje = "";
		try {
			MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm) this.formBusqueda;
			if (event != null) {
				f.setClienteFile(event.getFile());
				this.setAttachment(event.getFile().getFileName());
				this.uploadArchivo();
			}			
		} catch (Exception e) {
			mensaje = e.getMessage().toString();
			this.addError("Error : ", mensaje);
		}		
	}

	/**
	 * Cargar archivo
	 * @throws Exception
	 */
	public void uploadArchivo(){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'load Clientes from file' method");
		}
		try {
			MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm) this.formBusqueda;
			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService) getBean("spusicc.mantenimientoRECIngresoAtencionesService");
			GenericoService genericoService = (GenericoService) getBean("genericoService");
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			List listaClientes = new ArrayList();
			String indValidaCodConsultoraDocIdentidad = null;

			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(pais.getCodigo());
			parametroPais.setCodigoSistema("STO");
			parametroPais.setNombreParametro("indValidaCodConsultoraDocIdentidad");

			List lstParametros = genericoService.getParametrosPais(parametroPais);

			if (lstParametros != null && lstParametros.size() > 0) {
				ParametroPais ps = (ParametroPais) lstParametros.get(0);
				indValidaCodConsultoraDocIdentidad = ps.getValorParametro();
			}

			UploadedFile archivo = f.getClienteFile();
			Map criteria = new HashMap();
			InputStream is = archivo.getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			Long longitudPais = pais.getLongitudCodigoCliente();
			String linea = "";
			String codigoConsultora = "";
			int cont = 0;
			int numRegistros = 0;

			while (true) {
				linea = br.readLine();
				if (linea == null)
					break;

				codigoConsultora = StringUtils.leftPad(linea.trim(),
						longitudPais.intValue(), '0');
				criteria.put("codigoConsultora", codigoConsultora);
				LabelValue bean = new LabelValue(codigoConsultora,
						service.getCodigoConsultora(criteria));

				if (bean.getValue() == null
						&& StringUtils.equals(indValidaCodConsultoraDocIdentidad,
								Constants.SI)) {
					criteria.put("documentoIdentidad", codigoConsultora);
					String codigoConsultoraPorDocIden = service
							.getCodigoConsultoraPorDocumentoIdentidad(criteria);

					if (codigoConsultoraPorDocIden == null) {
						bean = new LabelValue(
								codigoConsultora,
								service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
					} else {
						bean = new LabelValue(
								codigoConsultoraPorDocIden,
								service.getCodigoConsultoraPorDocumentoIdentidad(criteria));
					}
				}

				listaClientes.add(bean);

				if (bean.getValue() == null)
					cont++;

				numRegistros++;
			}

			// Se inserta en la tabla temporal
			String oidCarga = service.getOidCargaCliente();
			criteria.put("oid", oidCarga);
			service.insertaClienteFile(listaClientes, criteria);

			// Se obtiene la lista de la tabla temporal
			List list = new ArrayList();
			list = service.getCargaClienteList(criteria);

			f.setCodigosErradosFile("");

			if (cont != 0)
				f.setCodigosErradosFile("Existe(n) " + cont
						+ " codigo(s) errado(s)");

			criteria.put("numRegistros", numRegistros);
			List list2 = new ArrayList();
			list2 = service.getResumenCargaClienteList(criteria);

			this.stoLevantamientoErroresClientesList = listaClientes;
			if (stoLevantamientoErroresClientesList != null) {
				this.mostrarGrilla = true;
			}

			this.stoResumenClientesList = list2;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoSTOLimiteVentaFocalizadoConsejeraList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOLimiteVentaFocalizadoConsejeraForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm form = new MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchAction - setFindAttributes");

		limpiar();

		MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOLimiteVentaFocalizadoConsejeraService service = (MantenimientoSTOLimiteVentaFocalizadoConsejeraService) getBean("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService");

		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());

		if (StringUtils.isNotBlank(f.getCodigoCliente()))
			criteria.put("codigoCliente", f.getCodigoCliente());
		else
			criteria.put("codigoCliente", null);

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		String codigoCliente3 = f.getCodigoCliente();
		List listaClientes = (List) stoLevantamientoErroresClientesList;// grilla
		// del
		// archivo

		Map map = new HashMap();
		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			if (listaClientes.size() > 0) {
				for (int i = 0; i < listaClientes.size(); i++) {

					LabelValue bean = (LabelValue) listaClientes.get(i);
					codigoCliente3 = bean.getLabel();
					if (codigoCliente3 != null) {
						clienteList.add(codigoCliente3);
					}
					// map = (Map) listaClientes.get(i);
					// codigoCliente3 = (String) map.get("codigoCliente");
				}
			}
		}

		// Si es cargado por la caja de texto
		if (codigoCliente != null && codigoCliente.length() > 0)
			arrlistaClientes = codigoCliente.split(",+");

		for (int i = 0; i < arrlistaClientes.length; i++) {
			codigoCliente = StringUtils.leftPad(arrlistaClientes[i],
					longitudPais.intValue(), '0');

			clienteList.add(codigoCliente);
		}

		/*
		 * if(clienteList != null) criteria.put("clienteList",clienteList);
		 */
		criteria.put("clienteList", (clienteList == null) ? new ArrayList()
				: clienteList);
		/*-------------------------*/

		List list = new ArrayList();

		list = service.getLimiteVentaFocalizadoConsejeraList(criteria);

		stoLimiteVentaFocalizadoConsejeraList = list;

		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.debug("MantenimientoSTOLimiteVentaFocalizadoConsejeraSearchAction - setDeleteAttributes");

		HashMap<String, Object> objBeanSelect = (HashMap<String, Object>) this.beanRegistroSeleccionado;

		String codigoPais = (String) objBeanSelect.get("codigoPais");
		String codigoCliente = (String) objBeanSelect.get("codigoCliente");
		String codigoPeriodo = (String) objBeanSelect.get("codigoPeriodo");

		MantenimientoSTOLimiteVentaFocalizadoConsejeraService service = (MantenimientoSTOLimiteVentaFocalizadoConsejeraService) getBean("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map parametros = new HashMap();

		String[] scripts = new String[1];
		scripts[0] = codigoPais + "-" + codigoPeriodo + "-" + codigoCliente;
		parametros.put("usuario", usuario.getLogin());

		parametros.put("idSeleccionados", scripts);

		service.deleteLimiteVentaFocalizadoConsejera(parametros);

		return true;
	}
	
	/**
	 * Metodo para validar el codigo de consultora
	 *
	 */
	public void validarCodigoConsultora(){
		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm) this.formMantenimiento;
		String codigoCliente = f.getCodigoCliente();
		String codigoPais = f.getCodigoPais();
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		String valor = aSvc.getCodigosErrados( codigoCliente, codigoPais);
		
		LabelDatosConsultoraValue[] consultora = null;
		consultora = aSvc.getCabeceraConsultoraSimple(codigoPais, codigoCliente);
		
		String mensaje= null;
			
		if(consultora != null){
			return;
		}else{
			mensaje = "Los codigo(s): " + valor +" no son válidos";
			this.addError("Error : ", mensaje);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("MantenimientoSTOLimiteVentaFocalizadoConsejeraAction - setSaveAttributes");

		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm) this.formMantenimiento;
		MantenimientoSTOLimiteVentaFocalizadoConsejeraService service = (MantenimientoSTOLimiteVentaFocalizadoConsejeraService) getBean("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService");

		boolean isArchivo = false;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("usuario", usuario.getLogin());
		criteria.put("numUnidadesLimite",
				Integer.valueOf(f.getNumUnidadesLimite()));

		/*-------------------------*/
		String[] arrlistaClientes = new String[0];
		List clienteList = new ArrayList(); // result
		Long longitudPais = pais.getLongitudCodigoCliente();
		String codigoCliente = f.getCodigoCliente();
		List listaClientes = this.stoLevantamientoErroresClientesList; // grilla
																		// del
																		// archivo

		// SI cargo consultoras por el archivo
		if (listaClientes != null) {
			isArchivo = true;
			for (int i = 0; i < listaClientes.size(); i++) {

				LabelValue bean = (LabelValue) listaClientes.get(i);
				codigoCliente = bean.getLabel();
				if (codigoCliente != null) {
					clienteList.add(codigoCliente);
				}
			}
		} else {
			// Si es cargado por la caja de texto
			if (codigoCliente.length() > 0)
				arrlistaClientes = codigoCliente.split(",+");
			for (int i = 0; i < arrlistaClientes.length; i++) {
				clienteList.add(StringUtils.leftPad(arrlistaClientes[i],
						longitudPais.intValue(), '0'));
			}
		}
		criteria.put("clienteList", (clienteList == null) ? new ArrayList()
				: clienteList);
		/*-------------------------*/

		f.setEditable(false);

		if (!this.accion.equals(this.ACCION_NUEVO)) {

			Map criteria1 = new HashMap();

			criteria1.put("numUnidadesLimite", f.getNumUnidadesLimite());
			criteria1.put("usuario", usuario.getLogin());
			criteria1.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria1.put("codigoPais", f.getCodigoPais());
			criteria1.put("codigoCliente", f.getCodigoCliente());

			service.updateObjetoSTOLimiteVentaFocalizadoConsejera(criteria1);
			f.setEditable(false);
			f.setNewRecord(true);
		}
		return true;
	}
	
	/**
	 * Metodo para validar el codigo consultora
	 * @return
	 */
	public Boolean validarConsultora(){
		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm) this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		LabelDatosConsultoraValue[] consultora = null;
		consultora = aSvc.getCabeceraConsultoraSimple(f.getCodigoPais(), f.getCodigoCliente());
		if(consultora != null){
			return true;			
		}else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {
		int flag = 0;
		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = (MantenimientoSTOLimiteVentaFocalizadoConsejeraForm) this.formMantenimiento;
		if (f.isNewRecord()) {
			if (f.getCodigoPeriodo() != null && StringUtils.isBlank(f.getCodigoPeriodo())) {
				String mensaje = this
						.getResourceMessage("mensaje.mantenimientoSTOLimiteVentaFocalizadoConsejeraForm.periodo.obligatorio");
				addError("Error : ", mensaje);
				return "";
			}
			if (stoLevantamientoErroresClientesList != null) {
				flag = 1;
			}

			if (flag == 0 && f.getCodigoCliente() != null
					&& StringUtils.isBlank(f.getCodigoCliente()) && f.getClienteFile() != null) {
				String mensaje = this
						.getResourceMessage("mensaje.mantenimientoSTOLimiteVentaFocalizadoConsejeraForm.noCodigonoClienteFile");
				addError("Error : ", mensaje);
				return "";
			}
			
			 if(!validarConsultora()){
				String mensaje = "Antes de Guardar por favor corrija el Codigo del Cliente";
				addError("Error : ", mensaje);
				return "";
		        }
			 
			 if( flag == 0 && StringUtils.isBlank(f.getCodigoCliente())){
		            String mensaje = "Codigo de Cliente deben estar ingresadas";
					addError("Error : ", mensaje);
					return "";
		        }

		}
		
		   if(f.getNumUnidadesLimite() != null && StringUtils.isBlank(f.getNumUnidadesLimite())){
				String mensaje = "mensaje.mantenimientoSTOLimiteVentaFocalizadoConsejeraForm.numUnidadesLimite";
				addError("Error : ", mensaje);
				return "";
	        }
		   
		return "";

	}

	/**
	 * Limpiando las listas del action
	 */
	public void limpiara() {
		this.stoResumenClientesList = null;
		this.stoLevantamientoErroresClientesList = null;
		this.mostrarGrilla = false;
		this.attachment = null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		log.debug("MantenimientoSTOLimiteVentaFocalizadoConsejeraAction - setEditAttributes");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoSTOLimiteVentaFocalizadoConsejeraService service = (MantenimientoSTOLimiteVentaFocalizadoConsejeraService) getBean("spusicc.mantenimientoSTOLimiteVentaFocalizadoConsejeraService");

		MantenimientoSTOLimiteVentaFocalizadoConsejeraForm f = new MantenimientoSTOLimiteVentaFocalizadoConsejeraForm();
		f.setNewRecord(false);
		f.setCodigoPais(pais.getCodigo());
		limpiar();

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			f.setNewRecord(true);
			HashMap<String, Object> objBeanSelect = (HashMap<String, Object>) this.beanRegistroSeleccionado;

			String codigoCliente = (String) objBeanSelect.get("codigoCliente");

			f.setNewRecord(false);

			if (StringUtils.isNotBlank(codigoCliente)) {

				String codigoPeriodo = (String) objBeanSelect
						.get("codigoPeriodo");
				String codigoPais = (String) objBeanSelect.get("codigoPais");
				f.setNewRecord(true);

				Map criteria = new HashMap();

				criteria.put("codigoPais", codigoPais);
				criteria.put("codigoPeriodo", codigoPeriodo);
				criteria.put("codigoCliente", codigoCliente);

				Map objeto = service
						.getObjectoSTOLimiteVentaFocalizadoConsejera(criteria);


				f.setCodigoPais((String) objeto.get("codigoPais"));
				f.setCodigoPeriodo((String) objeto.get("codigoPeriodo"));
				f.setCodigoCliente((String) objeto.get("codigoCliente"));
				f.setNumUnidadesLimite(String.valueOf(((BigDecimal) objeto
						.get("numUnidadesLimite")).intValue()));
			}
		}
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarGrilla = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		ClienteUAGenerarService svc = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		limpiara();
		this.longitudCampoClientes = svc.getTamanhoNumeroCliente(pais.getCodigo());
		this.stoLevantamientoErroresClientesList = new ArrayList();
	}

	/**
	 * @return the stoLevantamientoErroresClientesList
	 */
	public List getStoLevantamientoErroresClientesList() {
		return stoLevantamientoErroresClientesList;
	}

	/**
	 * @param stoLevantamientoErroresClientesList
	 *            the stoLevantamientoErroresClientesList to set
	 */
	public void setStoLevantamientoErroresClientesList(
			List stoLevantamientoErroresClientesList) {
		this.stoLevantamientoErroresClientesList = stoLevantamientoErroresClientesList;
	}

	/**
	 * @return the stoResumenClientesList
	 */
	public List getStoResumenClientesList() {
		return stoResumenClientesList;
	}

	/**
	 * @param stoResumenClientesList
	 *            the stoResumenClientesList to set
	 */
	public void setStoResumenClientesList(List stoResumenClientesList) {
		this.stoResumenClientesList = stoResumenClientesList;
	}

	/**
	 * @return the stoLimiteVentaFocalizadoConsejeraList
	 */
	public List getStoLimiteVentaFocalizadoConsejeraList() {
		return stoLimiteVentaFocalizadoConsejeraList;
	}

	/**
	 * @param stoLimiteVentaFocalizadoConsejeraList
	 *            the stoLimiteVentaFocalizadoConsejeraList to set
	 */
	public void setStoLimiteVentaFocalizadoConsejeraList(
			List stoLimiteVentaFocalizadoConsejeraList) {
		this.stoLimiteVentaFocalizadoConsejeraList = stoLimiteVentaFocalizadoConsejeraList;
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
	 * @return the mostrarGrilla
	 */
	public Boolean getMostrarGrilla() {
		return mostrarGrilla;
	}

	/**
	 * @param mostrarGrilla
	 *            the mostrarGrilla to set
	 */
	public void setMostrarGrilla(Boolean mostrarGrilla) {
		this.mostrarGrilla = mostrarGrilla;
	}

	/**
	 * @return the longitudCampoClientes
	 */
	public Integer getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	/**
	 * @param longitudCampoClientes the longitudCampoClientes to set
	 */
	public void setLongitudCampoClientes(Integer longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}	
}