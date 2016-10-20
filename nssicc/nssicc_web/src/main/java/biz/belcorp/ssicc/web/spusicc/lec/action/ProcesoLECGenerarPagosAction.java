package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECGenerarPagosService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.lec.form.ProcesoLECGenerarPagosForm;

/**
 * @author JPPS
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoLECGenerarPagosAction extends BaseProcesoAbstractAction {

	private static final long serialVersionUID = 4719206813800027889L;
	private List siccRegionList;
	private LabelValue[] lecTipoPagoList;
	private List lecGrupoRegionList;
	private LabelValue[] lecTipoGananciaList;
	private LabelValue[] lecRegionList;
	private String attachment;
	private String lecIndTipoGrupoPago = "";
	private boolean esAdicional;
	private boolean cargaArchivo = false;
	private boolean viewValida;
	private boolean btnExecute;
	private List letProgramaArchivoList = new ArrayList();
	private boolean esRegular;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		this.mostrarBotonExecute = false;
		Map criteria = new HashMap();
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) formProceso;
		f.setCodigoPais(pais.getCodigo());		
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		ProcesoLECCargaDatosExcelService servicePEJ = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");
		ProcesoLECGenerarPagosService serviceGP = (ProcesoLECGenerarPagosService) getBean("spusicc.procesoLECGenerarPagosService");
		LabelValue[] list0 = new LabelValue[] {};
		criteria.put("indPago", "1");

		List lecTipoTemp = new ArrayList();
		lecTipoTemp = serviceGP.getTipoPago(criteria);
		this.lecTipoPagoList = new LabelValue[lecTipoTemp.size()];
		int i = 0;
		for (Object object : lecTipoTemp) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.lecTipoPagoList[i] = labelValue;
			i++;
		}

		this.lecTipoGananciaList = list0;
		f.setTipoPago(lecTipoPagoList[0].getValue()); // Revisar
		Map result = service.getPeriodoDefault();
		f.setGrupoPago("1");
		String codigoPeriodo = (String) result.get("codigoPeriodo");
		String fechaProceso = (String) result.get("fechaProceso");
		f.setCodigoPeriodo(codigoPeriodo);
		f.setFechaProceso(fechaProceso);
		f.setFechaProcesoD(DateUtil.convertStringToDate(f.getFechaProceso()));
		// seteamos la ruta temporal dodne guardar el excel
		ProcesoZONActualizarUnidadesGeograficasService serviceUnidad = (ProcesoZONActualizarUnidadesGeograficasService) getBean("spusicc.procesoZONActualizarUnidadesGeograficasService");
		f.setDirectorioTemporal(serviceUnidad.obtenerPathUpload(pais
				.getCodigo()));

		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("campana", codigoPeriodo);
		List list = servicePEJ.getPrograma(criteria);
		Base m = (Base) list.get(0);

		f.setCodigoPrograma(m.getCodigo());
		f.setCodigoPais(pais.getCodigo());
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
		
		this.viewValida = false;
		this.letProgramaArchivoList = new ArrayList();
		this.mostrarPanelAdicionalProceso= false; 
		this.btnExecute = true;

		// lecTipoGananciaList = ajax.getTipoGanancia(f.getTipoPago());

		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");

		// INICIO: Delta Modificado

		list = lecService.getTipoGrupoRegion(criteria);
		this.lecGrupoRegionList = list;

		Map map2 = new HashMap();
		map2.put("codigoPais", pais.getCodigo());
		// INI PER-SiCC-2015-0150
		String indTipoGrupoPago = getIndicadorGrupoPago(pais);
		map2.put("indTipoGrupoPago", indTipoGrupoPago);
		List gruposPago = lecService.getGruposPago(map2);
		// FIN PER-SiCC-2015-0150

		this.lecGrupoRegionList = gruposPago;

		if (gruposPago != null && gruposPago.size() > 0) {
			String codigoGrupoPago = ((Base) gruposPago.get(0)).getCodigo();
			LabelValue[] regionList = ajax.getRegionesZonasByTipoGrupo(
					pais.getCodigo(), codigoGrupoPago);
			this.lecRegionList = regionList;
		} else {
			this.lecRegionList = new LabelValue[] {};
		}

		this.lecIndTipoGrupoPago = indTipoGrupoPago;

		f.setHabilitadorHidden("0");
		f.setHabilitadorRecaudoHidden("0");
		f.setHabilitadorPeriodo("0");
		f.setCodigoPeriodoRecaudo("");
		String habilitadorCampanna = this.getIndicadorCampannaProceso();
		if (StringUtils.isNotBlank(habilitadorCampanna))
			f.setHabilitadorHidden(habilitadorCampanna);
		f.setHabilitadorPeriodo(habilitadorCampanna);
		this.esAdicional = false;
		this.esRegular = true;

	}

	/**
	 * Obtiene indicador de Mostrar GrupoPago
	 * 
	 */
	private String getIndicadorGrupoPago(Pais pais) {

		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", pais.getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", Constants.LEC_IND_TIPO_GRUPO_PAGO);
		return ((MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService"))
				.getParametroGenericoSistema(criteriaParam);
	}

	/**
	 * @return
	 */
	private String getIndicadorCampannaProceso() {
		Map criteriaParam = new HashMap();
		criteriaParam.put("codigoPais", mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteriaParam.put("codigoSistema", "LET");
		criteriaParam.put("nombreParametro", "INDCAMBIOCAMPANA");
		return ((MantenimientoSTOBloqueoControlService) getBean("spusicc.mantenimientoSTOBloqueoControlService"))
				.getParametroGenericoSistema(criteriaParam);
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
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) this.formProceso;
		if(f.getFechaProcesoD()!= null){
			f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));
		}else{
			f.setFechaProceso("");
		}
			
		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();
		ProcesoLECGenerarPagosService service = (ProcesoLECGenerarPagosService) getBean("spusicc.procesoLECGenerarPagosService");
		params.put("codigoPais", f.getCodigoPais());
		params.put("codigoPrograma", f.getCodigoPrograma());
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("codigoTipoPago", f.getTipoPago()); //
		params.put("fechaProceso", f.getFechaProceso());
		params.put("codigoUsuario", usuario.getCodigo());
		params.put("numeroCarga", f.getNumeroCarga());
		params.put("codigoPeriodoRecaudo", f.getCodigoPeriodoRecaudo());
		params.put("codigoPeriodoBono", f.getCodigoPeriodoBono());
		// INI PER-SiCC-2015-0150
		params.put(
				"codigoGrupoPago",
				(f.getCodigoGrupoPago() == null) ? new ArrayList() : Arrays
						.asList(f.getCodigoGrupoPago()));
		// FIN PER-SiCC-2015-0150
		if (f.getGrupoPago() != null && f.getGrupoPago().compareTo("1") == 0)
			service.executeGenerarPagoRegular(params);
		if (f.getGrupoPago() != null && f.getGrupoPago().compareTo("0") == 0){
			params.put("codigoPeriodoRecaudo", f.getCodigoPeriodoBono());
			service.executeGenerarPagoAdicional(params);
		}	
		f.setFlagBotonValidar(true);
		f.setFlagBotonActualizar(false);

		return params;
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
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) this.formProceso;
		f.setFlagBotonValidar(false);
		f.setFlagBotonActualizar(false);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoLECGenerarPagosForm form = new ProcesoLECGenerarPagosForm();
		return form;
	}

	
	/**
	 * Actualiza la lista UA, segun Grupo UA
	 * 
	 * @param val
	 */
	public void loadRegionesZonas(ValueChangeEvent val) {
		log.debug(">>loadRegionesZonas ");
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String[] regiones = (String[]) val.getNewValue();
			if(regiones.length > 0){
				this.lecRegionList = new LabelValue[]{};
				if (!ArrayUtils.isEmpty(regiones)) {
					AjaxService aSvc = (AjaxService) getBean("ajaxService");
					this.lecRegionList = aSvc.getRegionesZonasByTiposGrupo(pais.getCodigo(),regiones);
				} 				
			}			
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void cargar(FileUploadEvent event) {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'cargar' method");
		}
		try {
			ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) this.formProceso;
			String codigoPeriodoRecaudo = f.getCodigoPeriodoRecaudo();
			String codigoPeriodoBono = f.getCodigoPeriodoBono();
			String grupoPago = f.getGrupoPago();
			if(StringUtils.isBlank(codigoPeriodoRecaudo) && this.btnExecute && StringUtils.equalsIgnoreCase(grupoPago, Constants.NUMERO_UNO)){
				this.addError("Error: ", this.getResourceMessage("procesoLECGenerarPagosForm.recaudo.requerido"));
				return;
				
			}
			
			if(StringUtils.isBlank(codigoPeriodoBono)){
				this.addError("Error: ", this.getResourceMessage("procesoLECGenerarPagosForm.bono.requerido"));
				return;
				
			}
			
			
			f.setArchivo(event.getFile());
			f.setNombreArchivo(event.getFile().getFileName());	
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			ProcesoLECCargaDatosExcelService service = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");
			if(f.getGrupoPago().compareTo("0")==0){
							
				Map criteria1 = new HashMap();
				criteria1.put("indGrup", "A");
				List lista = service.getTipoCarga(criteria1);
				this.lecTipoPagoList = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					Base base = (Base) lista.get(i);
					LabelValue labelValue = new LabelValue();					
					labelValue.setLabel(base.getDescripcion());
					labelValue.setValue(base.getCodigo());
					this.lecTipoPagoList [i] = labelValue;
				}				
				f.setTipoPago(lecTipoPagoList[0].getValue()); 
				
			
			}

			// Cargamos el archivo de la maquina del cliente al servidor
			uploadArchivo();
			
			// Obtenemos la extension del archivo
			String extensionArchivo = obtenerExtensionArchivo(f.getNombreArchivo());
			f.setExtensionArchivo(extensionArchivo);
		
			Map criteria = new HashMap();
			criteria.put("directorioTemporal", f.getDirectorioTemporal());
			criteria.put("nombreArchivo", f.getNombreArchivo());
			criteria.put("usuario", usuario);
			criteria.put("tipoCarga", f.getTipoPago());
			criteria.put("codigoPeriodo", f.getCodigoPeriodo());
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPrograma", f.getCodigoPrograma());
			//List tipoCargaList = (List)sesion.getAttribute(Constants.LEC_TIPO_CARGA_LIST);	
			//validamos el archivo excel y en criteria mandamos que estructura es sin period o con periodo
			
			Map resultados = service.cargarArchivoExcel(criteria);
			int totalRegistros = Integer.parseInt((String)resultados.get("totalRegistros"));
			if(totalRegistros==0){
				this.viewValida = false;
				this.mostrarPanelAdicionalProceso= false; 
				this.addError("Error: ", this.getResourceMessage("procesoLETCargaDatosExcelForm.existe.registros"));				
				return;
			}else{
				this.viewValida = true; //flag para mostrar el resultado de la validacion
				this.mostrarPanelAdicionalProceso= true; 
			}
			
			f.setNumeroCarga((String) resultados.get("numeroCarga"));
			f.setNumRegistros((String) resultados.get("totalRegistros"));
			f.setNumRegistrosInsertados((String) resultados
					.get("totalRegistrosInsertados"));
			f.setNumRegistrosError(Constants.NUMERO_CERO);
			f.setNumRegistrosValido(Constants.NUMERO_CERO);
			Integer numRegNoInsertados = Integer.parseInt(f.getNumRegistros())
					- Integer.parseInt(f.getNumRegistrosInsertados());
			f.setNumRegistrosNoInsertados(numRegNoInsertados.toString());
			this.letProgramaArchivoList = new ArrayList();
			borrarFichero(f.getDirectorioTemporal(), f.getNombreArchivo());

			f.setFlagBotonValidar(true);
			f.setFlagBotonActualizar(false);
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	
	private void uploadArchivo() throws Exception {
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) this.formProceso;
		// recuperamos el fichero
		UploadedFile archivo = f.getArchivo();
		f.setNombreArchivo(archivo.getFileName());
		log.debug("Nombre Archivo Upload: " + f.getNombreArchivo());
		
		// leemos el stream de entrada
		InputStream is = archivo.getInputstream();
		// abrimos el stream de escritura, ubicacion al cual se grabara el
		// archivo del cliente
		FileOutputStream os = new FileOutputStream(new File(f.getDirectorioTemporal(), f.getNombreArchivo()));
		// grabamos cada 1024 bytes
		int bytesRead = 0;
		byte[] buffer = new byte[1024];
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		os.close();
		f.setArchivo(null);
	}
	
	/**
	 * @param nombreArchivo
	 * @return
	 * @throws Exception
	 */
	private String obtenerExtensionArchivo(String nombreArchivo)
			throws Exception {
		return nombreArchivo.substring(nombreArchivo.length() - 3);
	}

	/**
	 * @param path
	 * @param nombreArchivo
	 */
	private void borrarFichero(String path, String nombreArchivo) {
		try {
			File file = new File(path, nombreArchivo);
			file.delete();
			log.debug("Se eliminó el archivo");
		} catch (Exception ex) {
			this.addError("Error : ", this.obtieneMensajeErrorException(ex));
		}
	}
	
	/**
	 * Validar
	 */
	public void validar(ActionEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'validar' method");
			}

			ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) this.formProceso;
			String codigoPeriodoRecaudo = f.getCodigoPeriodoRecaudo();
			String codigoPeriodoBono = f.getCodigoPeriodoBono();
			String grupoPago = f.getGrupoPago();
			
			if(StringUtils.isBlank(codigoPeriodoRecaudo) && this.btnExecute && StringUtils.equalsIgnoreCase(grupoPago, Constants.NUMERO_UNO)){
				this.addError("Error: ", this.getResourceMessage("procesoLECGenerarPagosForm.recaudo.requerido"));
				return;
				
			}
			
			if(StringUtils.isBlank(codigoPeriodoBono)){
				this.addError("Error: ", this.getResourceMessage("procesoLECGenerarPagosForm.bono.requerido"));
				return;
				
			}			
			
			Map params = BeanUtils.describe(f);

			ProcesoLECCargaDatosExcelService service = (ProcesoLECCargaDatosExcelService) getBean("spusicc.procesoLECCargaDatosExcelService");

			int totalErrores = 0;
			params.put("indicadorCarga", null);
			// validamos los datos cargados del archivo excel
			params.put("tipoCarga", f.getTipoPago());
			List resultados = service.executeValidarCargaDatos(params);
			for (int i = 0; i < resultados.size(); i++) {
				Map errores = (Map) resultados.get(i);
				if (errores.get("codigoMotivo") != null) {
					totalErrores++;
				}
			}

			int totalValidos = Integer.parseInt(f.getNumRegistros())
					- totalErrores;

			f.setNumRegistrosError(String.valueOf(totalErrores));
			f.setNumRegistrosValido(String.valueOf(totalValidos));

			this.letProgramaArchivoList = resultados;

			if (totalErrores > 0) {
				f.setFlagBotonValidar(true);
				f.setFlagBotonActualizar(false);
			} else {
				f.setFlagBotonValidar(false);
				f.setFlagBotonActualizar(true);
			}

			this.cargaArchivo = true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * @param val
	 */
	public void cambiarAdicional(ValueChangeEvent val) {
		try {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) this.formProceso;
			String valor = (String) val.getNewValue();	
			if(StringUtils.isNotBlank(valor)){
				if (valor.equals("1")) {
					this.esRegular = true;
					this.esAdicional = false;
					this.btnExecute = true;
					this.lecTipoPagoList = ajax.getTipoPago("1");
					f.setTipoPago(lecTipoPagoList[0].getValue()); 
				} else {
					this.esRegular = false;
					this.esAdicional = true;
					this.btnExecute = false;
					this.lecTipoPagoList = ajax.getTipoPago("0");
					f.setCodigoPeriodoRecaudo("");
					f.setTipoPago(lecTipoPagoList[0].getValue()); // Revisar
				}			
			}			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #setValidarProceso()
	 */
	@Override
	public String setValidarProceso() {
		String mensaje = null;

		if (esAdicional) {
			if (!this.cargaArchivo) {
				mensaje = "Primero debe cargar el archivo";
			}
		}
		return mensaje;
	}
	
	public void procesarPagoRegular(ActionEvent event){
		
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) formProceso;
		String tipoPago = f.getTipoPago();
		String grupoPago = f.getGrupoPago();
		String codigoPeriodoRecaudo = f.getCodigoPeriodoRecaudo();
		String codigoPeriodoBono = f.getCodigoPeriodoBono();
		
		if(StringUtils.isBlank(codigoPeriodoBono)){
			this.addError("Error: ", this.getResourceMessage("procesoLECGenerarPagosForm.bono.requerido"));
			return;
			
		}
		
		if(StringUtils.isBlank(codigoPeriodoRecaudo)&& StringUtils.equalsIgnoreCase(grupoPago, Constants.NUMERO_UNO)){
			this.addError("Error: ", this.getResourceMessage("procesoLECGenerarPagosForm.recaudo.requerido"));
			return;
			
		}
		
//		if(procesoenejecucion.value == "0") {
		if(StringUtils.isBlank(tipoPago)){
			this.addError("Error: ", "Tipo Pago Valor Requerido");
			return;
			
		}
		this.executeProceso();
		
//		}
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		String msj = null;
		ProcesoLECGenerarPagosForm f = (ProcesoLECGenerarPagosForm) this.formProceso;
		String codigoPeriodoRecaudo = f.getCodigoPeriodoRecaudo();
		String codigoPeriodoBono = f.getCodigoPeriodoBono();
		String grupoPago = f.getGrupoPago();
		if(StringUtils.isBlank(codigoPeriodoRecaudo)&& StringUtils.equalsIgnoreCase(grupoPago, Constants.NUMERO_UNO)){
			msj = this.getResourceMessage("procesoLECGenerarPagosForm.recaudo.requerido");
			return msj;
			
		}
		
		if(StringUtils.isBlank(codigoPeriodoBono)){
			msj = this.getResourceMessage("procesoLECGenerarPagosForm.bono.requerido");
			return msj;
			
		}
		
		if(Integer.parseInt(f.getNumRegistrosError())>0){
			msj = this.getResourceMessage("procesoLECGenerarPagosForm.process.valido.errores");
			return msj;
			
		}
		return msj;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the lecTipoPagoList
	 */
	public LabelValue[] getLecTipoPagoList() {
		return lecTipoPagoList;
	}

	/**
	 * @param lecTipoPagoList the lecTipoPagoList to set
	 */
	public void setLecTipoPagoList(LabelValue[] lecTipoPagoList) {
		this.lecTipoPagoList = lecTipoPagoList;
	}

	/**
	 * @return the lecGrupoRegionList
	 */
	public List getLecGrupoRegionList() {
		return lecGrupoRegionList;
	}

	/**
	 * @param lecGrupoRegionList the lecGrupoRegionList to set
	 */
	public void setLecGrupoRegionList(List lecGrupoRegionList) {
		this.lecGrupoRegionList = lecGrupoRegionList;
	}

	/**
	 * @return the lecTipoGananciaList
	 */
	public LabelValue[] getLecTipoGananciaList() {
		return lecTipoGananciaList;
	}

	/**
	 * @param lecTipoGananciaList the lecTipoGananciaList to set
	 */
	public void setLecTipoGananciaList(LabelValue[] lecTipoGananciaList) {
		this.lecTipoGananciaList = lecTipoGananciaList;
	}

	/**
	 * @return the lecRegionList
	 */
	public LabelValue[] getLecRegionList() {
		return lecRegionList;
	}

	/**
	 * @param lecRegionList the lecRegionList to set
	 */
	public void setLecRegionList(LabelValue[] lecRegionList) {
		this.lecRegionList = lecRegionList;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}	

	/**
	 * @return the lecIndTipoGrupoPago
	 */
	public String getLecIndTipoGrupoPago() {
		return lecIndTipoGrupoPago;
	}

	/**
	 * @param lecIndTipoGrupoPago the lecIndTipoGrupoPago to set
	 */
	public void setLecIndTipoGrupoPago(String lecIndTipoGrupoPago) {
		this.lecIndTipoGrupoPago = lecIndTipoGrupoPago;
	}

	/**
	 * @return the esAdicional
	 */
	public boolean isEsAdicional() {
		return esAdicional;
	}

	/**
	 * @param esAdicional the esAdicional to set
	 */
	public void setEsAdicional(boolean esAdicional) {
		this.esAdicional = esAdicional;
	}

	/**
	 * @return the cargaArchivo
	 */
	public boolean isCargaArchivo() {
		return cargaArchivo;
	}

	/**
	 * @param cargaArchivo the cargaArchivo to set
	 */
	public void setCargaArchivo(boolean cargaArchivo) {
		this.cargaArchivo = cargaArchivo;
	}

	/**
	 * @return the viewValida
	 */
	public boolean isViewValida() {
		return viewValida;
	}

	/**
	 * @param viewValida the viewValida to set
	 */
	public void setViewValida(boolean viewValida) {
		this.viewValida = viewValida;
	}

	/**
	 * @return the letProgramaArchivoList
	 */
	public List getLetProgramaArchivoList() {
		return letProgramaArchivoList;
	}

	/**
	 * @param letProgramaArchivoList the letProgramaArchivoList to set
	 */
	public void setLetProgramaArchivoList(List letProgramaArchivoList) {
		this.letProgramaArchivoList = letProgramaArchivoList;
	}

	/**
	 * @return the btnExecute
	 */
	public boolean isBtnExecute() {
		return btnExecute;
	}

	/**
	 * @param btnExecute the btnExecute to set
	 */
	public void setBtnExecute(boolean btnExecute) {
		this.btnExecute = btnExecute;
	}

	public boolean isEsRegular() {
		return esRegular;
	}

	public void setEsRegular(boolean esRegular) {
		this.esRegular = esRegular;
	}
}