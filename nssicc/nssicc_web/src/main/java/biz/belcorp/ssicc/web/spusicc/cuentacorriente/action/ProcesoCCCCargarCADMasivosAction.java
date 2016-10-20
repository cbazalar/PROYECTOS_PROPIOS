/*
 * 
 */
package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCDigitacionCADService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarCADMasivosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseCargaArchivoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseCargaArchivoForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarCADMasivosForm;



@ManagedBean
@SessionScoped
public class ProcesoCCCCargarCADMasivosAction extends BaseCargaArchivoAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List siccSociedadList = new ArrayList();
	private List cccTiposCargoAbonosDirectosList = new ArrayList();
	private List cccErroresCargaMasivaList = new ArrayList();
	private String attachment;
 
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAtributes");
		}	
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();			
		
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
                        
		siccSociedadList =	svc.getSociedadesByCodigoPais(pais.getCodigo());
								
		List listaTipoCAD = new ArrayList();
		
		MantenimientoCCCDigitacionCADService service = (MantenimientoCCCDigitacionCADService)getBean("spusicc.mantenimientoCCCDigitacionCADService");
		listaTipoCAD = service.getTiposCargoAbonoDirectos();						
		cccTiposCargoAbonosDirectosList = listaTipoCAD;
				
		ProcesoCCCCargarCADMasivosForm f = (ProcesoCCCCargarCADMasivosForm)this.formCargaArchivo;
		
		InterfazSiCCService serviceInt = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		String periodoActual = serviceInt.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		f.setCodigoPeriodo(periodoActual);
		f.setFlagUpload(true);
		f.setFlagValidar(false);		
		f.setFlagProcesar(false);
		f.setFlagMostrarErrores(false);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));
		Date dFecha = DateUtil.convertStringToDate(fecha);
		
		f.setFechaVencimiento(fecha);	
		f.setCantidadRegistrosCargados(null);
		f.setImporteRegistrosCargados(null);
		f.setFechaVencimientoDate(dFecha);
		
		
		//realizamos la consulta para obtener 
		Map datos = new HashMap();
		datos.put("indicadorParametro", Constants.CCC_VALOR_PARAMETRO_HABILITAR_PROCESO_CAD_MASIVO);
		String indicador=service.getIndicadorParametro(datos);
		if(indicador!=null && StringUtils.isNotBlank(indicador)&& StringUtils.equals("S", indicador)){
			f.setFlagHabilitarCadMasivo(true);
		}else{
			f.setFlagHabilitarCadMasivo(false);
		}
		this.mostrarBotonProcesar = false;
	}
	
	@Override
	protected BaseCargaArchivoForm devuelveFormCarga() throws Exception {
		ProcesoCCCCargarCADMasivosForm form = new ProcesoCCCCargarCADMasivosForm();
		return form;
	}
	
	
	@Override
	public BaseCargaArchivoForm setUploadAttibuttes(Map<String, Object> criteria) {
		ProcesoCCCCargarCADMasivosForm f = (ProcesoCCCCargarCADMasivosForm)this.formCargaArchivo;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		
		ProcesoCCCCargarCADMasivosService service = (ProcesoCCCCargarCADMasivosService) getBean("spusicc.procesoCCCCargarCADMasivosService");
								
		f.setDirectorioTemporal(service.obtenerPathUpload(criteria));
		return f;
	}

	
	@Override
	protected List setValidarAttributes(Map<String, Object> datos)
			throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoCCCCargarCADMasivosForm f = (ProcesoCCCCargarCADMasivosForm)this.formCargaArchivo;
		String strMessage = null;	
		
		Map criteria = new HashMap();
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
			
		datos.put("directorioTemporal", f.getDirectorioTemporal());
		datos.put("nombreArchivo", f.getNombreArchivo());
		datos.put("extensionArchivo", f.getExtensionArchivo());
		datos.put("tipoCarga", f.getTipoCarga());
		datos.put("codigoPais", pais.getCodigo());
		datos.put("oidCodPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));				
		f.setFlagMostrarErrores(false);
		this.cccErroresCargaMasivaList = new ArrayList();
		
		// Obteniendo el Numero de Lote												
		ConsultaCCCGenericoService serviceGenericoCCC = (ConsultaCCCGenericoService)getBean("spusicc.consultaCCCGenericoService");
										
		serviceGenericoCCC.getNumeroLote(datos);	
		
		f.setNumeroLote(datos.get("numeroLote").toString());
		
		if (log.isDebugEnabled()) {
			log.debug("JFA : Parametros " + datos.toString());	
		}						
																																										
		ProcesoCCCCargarCADMasivosService service = (ProcesoCCCCargarCADMasivosService) getBean("spusicc.procesoCCCCargarCADMasivosService");
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Llamando deleteTablasCargaCargosAbonosMasivos");
		}
		service.deleteTablasCargaCargosAbonosMasivos(datos);
						
		if (log.isDebugEnabled()) {
			log.debug("JFA Llamando executeValidarCargosAbonosMasivos");
		}
		service.executeValidarCargosAbonosMasivos(datos);
		String error = (String) datos.get("error");				
		
		if (log.isDebugEnabled()) {
			log.debug("JFA Validando Errores");
		}
		
		if (error.equals("0")){
			this.mostrarListaCarga = false;
			this.errorValidar = false;
			strMessage = "procesoCCCCargarCADMasivosUpload.msg.CargaDatos.ok";
			f.setCantidadRegistrosCargados((String) datos.get("cantidadRegistrosCargados"));
			f.setImporteRegistrosCargados((String) datos.get("importeRegistrosCargados"));
			this.addInfo("Información: ", this.getResourceMessage(strMessage));				
		}
		else{		
			this.mostrarListaCarga = true;
			this.errorValidar = true;
			f.setFlagMostrarErrores(true);
			service.deleteTablasCargaCargosAbonosMasivos(datos);
								
			this.cccErroresCargaMasivaList = service.getErroresCargaCargosAbonosMasivos(datos);
			strMessage = "procesoCCCCargarCADMasivosUpload.msg.CargaDatos.error";
			this.addError("Error: ", this.getResourceMessage(strMessage));	
		}
		return this.cccErroresCargaMasivaList;
	}

	@Override
	protected void setProcesarAttributes(Map<String, Object> datos)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'procesar' method");
		}		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		ProcesoCCCCargarCADMasivosService service = (ProcesoCCCCargarCADMasivosService) getBean("spusicc.procesoCCCCargarCADMasivosService");
		ProcesoCCCCargarCADMasivosForm f = (ProcesoCCCCargarCADMasivosForm)this.formCargaArchivo;
		Date fecha = f.getFechaVencimientoDate();
		String fechaS = DateUtil.convertDateToString(fecha);
		f.setFechaVencimiento(fechaS);
		
		datos.put("codigoPais", pais.getCodigo());      
        datos.put("codigoPeriodo",f.getCodigoPeriodo());
        datos.put("tipoCarga", f.getTipoCarga());
        datos.put("fechaVencimiento",f.getFechaVencimiento());
        datos.put("tipoCAD",f.getTipoCAD());
        datos.put("numeroLote", f.getNumeroLote());
        
        								
		service.executeProcesarCargosAbonosMasivos(datos);		
		
		return;
	}	
	
	
	@Override
	protected String getMensajeProcesarOK() {
		String mensaje = "procesoCCCCargarCADMasivosUpload.msg.procesa.ok";
		return this.getResourceMessage(mensaje);
	}
	

	
	
	

	
	
	
	/* GET - SET */
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}

	public List getCccTiposCargoAbonosDirectosList() {
		return cccTiposCargoAbonosDirectosList;
	}

	public void setCccTiposCargoAbonosDirectosList(
			List cccTiposCargoAbonosDirectosList) {
		this.cccTiposCargoAbonosDirectosList = cccTiposCargoAbonosDirectosList;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public List getCccErroresCargaMasivaList() {
		return cccErroresCargaMasivaList;
	}

	public void setCccErroresCargaMasivaList(List cccErroresCargaMasivaList) {
		this.cccErroresCargaMasivaList = cccErroresCargaMasivaList;
	}

	

	
}