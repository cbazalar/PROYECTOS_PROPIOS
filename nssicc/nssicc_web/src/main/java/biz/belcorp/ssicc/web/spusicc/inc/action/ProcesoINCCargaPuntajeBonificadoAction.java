package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaPuntajeBonificadoService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCCargaPuntajeBonificadoForm;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class ProcesoINCCargaPuntajeBonificadoAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = -3992820994447174683L;
	private List incentivosConcursosHabilitadosList;
	private List incentivosMotivosCargaList;
	private List incentivosArchivolist;
	private UploadedFile file;
	private boolean fileCargado;
	private boolean viewValida;
	private String msjDialog;
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoINCCargaPuntajeBonificadoForm form = new ProcesoINCCargaPuntajeBonificadoForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
		f.setCodigoPais(pais.getCodigo());
		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		MantenimientoINCHabilitacionConcursoCargaPuntajeService service = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais.getCodigo()));
		// cargando en session la lista de concursos habilitados
		this.incentivosConcursosHabilitadosList = service.getListConcursoHabilitado();
		// cargando en session la lista de motivos
		this.incentivosMotivosCargaList = service.getListMotivoConcursos();
		
		// limpiando el flag de validacion de archivo
		f.setIndicadorConsultoras(Constants.NUMERO_CERO);
		this.mostrarBotonExecute = false;
		f.setIndicadorConsultorasB(false);
		
		//limpiando el flag de validacion de archivo
		this.viewValida = false;
		this.incentivosArchivolist = new ArrayList();
	}

	/**
	 * Valida el excel que viene del request
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void validar() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'validar' method");
		}

		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
			MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");

			// obtenemos el periodo desde la fecha de proceso
			InterfazSiCCService serviceSiCC = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			String codigoPeriodo = serviceSiCC.getPeriodoDefaultByPaisCanal(f.getCodigoPais(), Constants.CODIGO_CANAL_DEFAULT);

			// obtenemos el servicio
			ProcesoINCCargaPuntajeBonificadoService service = (ProcesoINCCargaPuntajeBonificadoService) getBean("spusicc.procesoINCCargaPuntajeBonificadoService");
			// Cargamos el archivo de la maquina del cliente al servidor
			uploadArchivo(f);
			// Leemos la primera linea del archivo, para recuperar los campos
			// filtro
			// que nos servira para determinar que campo corresponde a que nivel
			// de
			//
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);

			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("numeroConcurso",
					getNumeroConcurso(f.getOidConcurso()));
			criteria.put("codigoMotivo", f.getCodigoMotivo());
			criteria.put("usuario", usuario);
			criteria.put("codigoPais", f.getCodigoPais());
			String oidPais = clienteService.getOidPais(criteria);
			criteria.put("oidPais", oidPais);
			criteria.put("periodo", codigoPeriodo);
			// validamos el archivo excel y en criteria mandamos que estructura
			// es
			// sin period o con periodo
			boolean isValido = service.validarArchivoExcel(criteria);
			// cargamos toda los registros del excel con la sgte informacion y
			// simultaneamente cargara la
			// informcion en la taba de puntaje
			// num fila codconsultora mensaje error num errores , este campo
			// sera
			// llenado y acualizado en el ultimo registro
			List listaArchivo = new ArrayList();
			if (isValido) {
				listaArchivo = service.cargarArchivoExcel(criteria);
				f.setNumRegistros(String.valueOf(listaArchivo.size()));
				Map map = (Map) listaArchivo.get(listaArchivo.size() - 1);
				f.setNumRegistrosError(String.valueOf(map.get("numErrores")));
				f.setNumeroLote(String.valueOf(map.get("numeroLote")));
				f.setNumRegistrosValido(String.valueOf(listaArchivo.size() - Integer.parseInt(f.getNumRegistrosError())));
				// es valido si por lo menos hay un registro por cargar , es
				// decir
				// numero errors < num registros
				if (Integer.parseInt(f.getNumRegistrosError()) < Integer
						.parseInt(f.getNumRegistros())) {
					f.setIndicadorValido(Constants.NUMERO_UNO);
				} else {
					f.setIndicadorValido(Constants.NUMERO_CERO);
				}
				
				borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());
				this.viewValida = true;//flag para mostrar el resultado de la validacion
				this.incentivosArchivolist = listaArchivo;
			} else {
				this.viewValida = true;//flag para mostrar el resultado de la validacion
				this.incentivosArchivolist = new ArrayList();

				f.setIndicadorValido(Constants.NUMERO_CERO);
				f.setNumRegistrosError(Constants.NUMERO_UNO);
				f.setNumRegistros(Constants.NUMERO_UNO);
				f.setNumRegistrosValido(Constants.NUMERO_CERO);

				// mandamos infomacion del archivo
				Map mapFila = new HashMap();
				mapFila.put("numeroFila", Constants.NUMERO_UNO);
				mapFila.put("codigoCliente", "");
				mapFila.put("mensajeError", this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.error.noFormatoExcel"));
				listaArchivo.add(mapFila);
				this.incentivosArchivolist = listaArchivo;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * obtiene el numero del concurso
	 * 
	 * @param sesion
	 * @param oidConcurso
	 * @return
	 */
	private String getNumeroConcurso(String oidConcurso) {
		String numeroConcurso = "";
		List listConcursos = this.incentivosConcursosHabilitadosList;
		Iterator it = listConcursos.iterator();
		while (it.hasNext()) {
			Base concurso = (Base) it.next();
			if (oidConcurso.equals(concurso.getCodigo())) {
				numeroConcurso = (StringUtils.split(concurso.getDescripcion(), "-")[0]).trim();
				break;
			}
		}
		return numeroConcurso;
	}

	/**
	 * Carga el archivo excel.
	 * 
	 * @param f
	 *            - Objeto ProcesoINCCargaPuntajeBonificadoForm
	 */
	public void uploadArchivo(ProcesoINCCargaPuntajeBonificadoForm f) {
		// recuperamos el fichero
		f.setNombreArchivo(this.file.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());

		if (this.file != null) {
			try {
				InputStream is = this.file.getInputstream();
				// abrimos el stream de escritura, ubicacion al cual se grabara
				// el
				// archivo del cliente
				FileOutputStream os = new FileOutputStream(new File(
						f.getDirectorioTemporal(), f.getNombreArchivo()));
				// grabamos cada 1024 bytes
				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				try {
					is = file.getInputstream();
				} catch (Exception ex) {
					this.addError("Error : ",
							this.obtieneMensajeErrorException(ex));
				} finally {
					os.flush();
					os.close();
					is.close();
				}
			} catch (Exception ex) {
				this.addError("Error : ", this.obtieneMensajeErrorException(ex));
			}
		}
		f.setArchivo(null);
	}

	/**
	 * obtiene la extension del archivo
	 * 
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * elimina el fichero del temporal
	 * 
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se elimino el archivo");
		} catch (Exception ex) {
			log.debug("No se pudo eliminar el archivo " + ex.getMessage());
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {
		params = super.prepareParamsBeforeExecute(params, form);

		ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Map criteria = new HashMap();

		if (f.getIndicadorConsultoras().equals(Constants.NUMERO_UNO)) {
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

			PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
			params.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());
		} else {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			criteria.put("codigoPais", pais.getCodigo());
			String oidPais = clienteService.getOidPais(criteria);

			ProcesoINCCargaPuntajeBonificadoService serviceInc = (ProcesoINCCargaPuntajeBonificadoService) getBean("spusicc.procesoINCCargaPuntajeBonificadoService");

			String mensaje = serviceInc.validarDatosConsultora(oidPais,
					f.getCodigoPeriodo(), f.getCodigoCliente(), usuario);

			if (StringUtils.isNotEmpty(mensaje)) {
				throw new Exception(mensaje);
			}
		}

		params.put("numeroLote", f.getNumeroLote());
		return params;
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
		ProcesoINCCargaPuntajeBonificadoService service = (ProcesoINCCargaPuntajeBonificadoService) getBean("spusicc.procesoINCCargaPuntajeBonificadoService");
		ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		if (!f.getIndicadorConsultoras().equals(Constants.NUMERO_UNO)) {
			params.put("numeroConcurso", getNumeroConcurso(f.getOidConcurso()));
			params.put("usuario", usuario);
		}

		service.executeInsercionCuentaCorrientePuntaje(params);
		return params;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #afterExecuteProcessSuccess()
	 */
	protected void afterExecuteProcessSuccess() {

		this.viewValida = false;
		this.incentivosArchivolist = new ArrayList();
		ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
		f.setIndicadorConsultoras(Constants.NUMERO_CERO);
		f.setIndicadorConsultorasB(false);
		
		this.addInfo("Info: ", this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.proceso.ok"));
		reset();

	}
	
	/**
	 * Setea valores a vacios
	 * 
	 */
	private void reset(){

		ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
		f.setOidConcurso("");
		f.setCodigoMotivo("");
		f.setNumeroLote("");
		f.setCodigoPeriodo("");
		f.setCodigoCliente("");
		f.setNumeroPuntos("");
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		String mensaje = "";
		ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
		
		if (f.getIndicadorConsultorasB()) {
			
			int numError = Integer.parseInt(f.getNumRegistrosError());
			if(numError > 0){
				this.msjDialog = this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.process.valido.errores");
			}
			else {
				this.msjDialog = this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.process.valido");
			}
			
		} else {
			
			if(StringUtils.isBlank(f.getOidConcurso())){
				return this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.oidConcurso.requerido");			
			}
			
			if(StringUtils.isBlank(f.getCodigoMotivo())){
				return this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.codigoMotivo.requerido");			
			}			
			
			if (StringUtils.isBlank(f.getCodigoPeriodo()) || f.getCodigoPeriodo() == null) {
				return this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.codigoPeriodo.requerido");
			}
			
			if (StringUtils.isBlank(f.getCodigoCliente()) || f.getCodigoCliente() == null) {
				return this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.codigoCliente.requerido");
			}
			
			if (StringUtils.isBlank(f.getNumeroPuntos()) || f.getNumeroPuntos() == null) {
				return this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.numeroPuntos.requerido");
			}else{
				if(Integer.parseInt(f.getNumeroPuntos()) == 0){
					return this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.numeroPuntos.diferenteCero");
				}
			}
			
			this.msjDialog = this.getResourceMessage("confirm.execute.process");
		}
			
		return mensaje;
		
	}

	/**
	 * biz.belcorp.ssicc.web.spusicc.inc.action.
	 * ProcesoINCCargaPuntajeBonificadoAction#indicadorConsultora;
	 * 
	 * @param event
	 */
	public void indicadorConsultora(ValueChangeEvent event) {
		try {
			ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
			f.setIndicadorConsultorasB((Boolean) event.getNewValue());
			f.setIndicadorConsultoras(f.getIndicadorConsultorasB() ? "1" : "0");
			if (f.getIndicadorConsultorasB()) {
				if (fileCargado) {
					this.viewValida = true;
				} else {
					this.viewValida = false;
				}
			} else {
				this.viewValida = false;
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * Carga el archivo del cliente y lo guarda en una variable file.
	 * 
	 * @param ev
	 *            El evento desencadenado en la interfaz de usuario.
	 */
	public void cargarArchivo(FileUploadEvent ev) {
		
		ProcesoINCCargaPuntajeBonificadoForm f = (ProcesoINCCargaPuntajeBonificadoForm) this.formProceso;
		
		if(StringUtils.isBlank(f.getOidConcurso())){
			this.addError("Error: ", this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.oidConcurso.requerido"));
			return ;			
		}
		
		if(StringUtils.isBlank(f.getCodigoMotivo())){
			this.addError("Error: ", this.getResourceMessage("procesoINCCargaPuntajeBonificadoForm.codigoMotivo.requerido"));
			return ;			
		}
		
		
		file = ev.getFile();
		fileCargado = true;
		this.validar();
	}

	/**
	 * @return the incentivosConcursosHabilitadosList
	 */
	public List getIncentivosConcursosHabilitadosList() {
		return incentivosConcursosHabilitadosList;
	}

	/**
	 * @param incentivosConcursosHabilitadosList
	 *            the incentivosConcursosHabilitadosList to set
	 */
	public void setIncentivosConcursosHabilitadosList(
			List incentivosConcursosHabilitadosList) {
		this.incentivosConcursosHabilitadosList = incentivosConcursosHabilitadosList;
	}

	/**
	 * @return the incentivosMotivosCargaList
	 */
	public List getIncentivosMotivosCargaList() {
		return incentivosMotivosCargaList;
	}

	/**
	 * @param incentivosMotivosCargaList
	 *            the incentivosMotivosCargaList to set
	 */
	public void setIncentivosMotivosCargaList(List incentivosMotivosCargaList) {
		this.incentivosMotivosCargaList = incentivosMotivosCargaList;
	}

	/**
	 * @return the incentivosArchivolist
	 */
	public List getIncentivosArchivolist() {
		return incentivosArchivolist;
	}

	/**
	 * @param incentivosArchivolist
	 *            the incentivosArchivolist to set
	 */
	public void setIncentivosArchivolist(List incentivosArchivolist) {
		this.incentivosArchivolist = incentivosArchivolist;
	}

	/**
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/**
	 * @return the fileCargado
	 */
	public boolean isFileCargado() {
		return fileCargado;
	}

	/**
	 * @param fileCargado
	 *            the fileCargado to set
	 */
	public void setFileCargado(boolean fileCargado) {
		this.fileCargado = fileCargado;
	}

	/**
	 * @return the viewValida
	 */
	public boolean isViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida
	 *            the viewValida to set
	 */
	public void setViewValida(boolean viewValida) {
		this.viewValida = viewValida;
	}

	/**
	 * @return the msjDialog
	 */
	public String getMsjDialog() {
		return msjDialog;
	}

	/**
	 * @param msjDialog the msjDialog to set
	 */
	public void setMsjDialog(String msjDialog) {
		this.msjDialog = msjDialog;
	}
}
