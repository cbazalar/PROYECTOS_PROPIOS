package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDReasignacionDocumentosLegalesService;
import biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVDocumentosContablesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoPEDReasignacionDocumentosLegalesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ProcesoPEDReasignacionDocumentosLegalesAction extends BaseProcesoAbstractAction{
	
	private static final long serialVersionUID = 5486680011621754048L;
	
	private String indicadorNumeroControlDocumentoLegal;
	private List siccCanalList;
	private List tipoDocumentoContableList;
	private List siccAccesoList;
	private List siccSubaccesoList;

	
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPEDReasignacionDocumentosLegalesForm formProceso = new ProcesoPEDReasignacionDocumentosLegalesForm();
		return formProceso;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params) throws Exception {
		log.debug("executeProcess - ProcesoPEDReasignacionDocumentosLegalesAction");
		
		ProcesoPEDReasignacionDocumentosLegalesService procesoPEDReasignacionDocumentosLegalesService = (ProcesoPEDReasignacionDocumentosLegalesService)getBean("spusicc.procesoPEDReasignacionDocumentosLegalesService");
		ProcesoPEDReasignacionDocumentosLegalesForm f = (ProcesoPEDReasignacionDocumentosLegalesForm)this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		params.put("tipoDocumentoContable", Integer.valueOf(f.getTipoDocumentoContable()));
		params.put("codigoSubacceso", f.getCodigoSubacceso());
		params.put("ejercicio", f.getEjercicio());
		params.put("rangoDesdeDocLegal", Integer.valueOf(StringUtils.isNotBlank(f.getRangoDesdeDocLegal()) ? f.getRangoDesdeDocLegal() : "0"));
		params.put("rangoDesdeNrControl", Integer.valueOf(StringUtils.isNotBlank(f.getRangoDesdeNrControl()) ? f.getRangoDesdeNrControl() : "0"));
		params.put("indicadorNumeroControlDocumentoLegal", pais.getIndicadorNumeroControlDocumentoLegal());
		params.put("rangoDesdeDocInterno", Integer.valueOf(f.getRangoDesdeDocInterno()));
		params.put("rangoHastaDocInterno", Integer.valueOf(f.getRangoHastaDocInterno()));
		params.put("serieDocLegal", f.getSerieDocLegal());
		
		procesoPEDReasignacionDocumentosLegalesService.executeReasignacionDocumentosLegales(params);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		ProcesoPEDReasignacionDocumentosLegalesForm f = (ProcesoPEDReasignacionDocumentosLegalesForm)this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoPEDReasignacionDocumentosLegalesService procesoPEDReasignacionDocumentosLegalesService = (ProcesoPEDReasignacionDocumentosLegalesService)getBean("spusicc.procesoPEDReasignacionDocumentosLegalesService");
		MantenimientoRUVDocumentosContablesService serviceDocCon = (MantenimientoRUVDocumentosContablesService)getBean("spusicc.mantenimientoRUVDocumentosContablesService");
		
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
	    f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
	    f.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_DEFAULT);
	    
	    
	    log.info("Entro a ProcesoPEDReasignacionDocumentosLegalesAction - setViewAttributes");
		
		//-- Variables ----------------------------------------------
		String indicador = pais.getIndicadorNumeroControlDocumentoLegal();		
		String codigoSistema = this.parametrosPantalla.get("codigoSistema");
		String codigoParametro = this.parametrosPantalla.get("codigoParametro");
		
		//-- Recuperar indicador de activacion ----------------------
		//-- de documento contable
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoSistema", codigoSistema);
		criteria.put("codigoParametro", codigoParametro);
		
		String indicadorActDocCon = serviceDocCon.getIndicadorActivacionDocumentoContable(criteria).toLowerCase().trim();
		
		//-- Inicializar atributos ------------------------------
		f.setIndicadorActDocCon(indicadorActDocCon);
		
		if(f.getIndicadorActDocCon().equalsIgnoreCase("S")){
			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			f.setCodigoAcceso(Constants.CODIGO_ACCESO_DEFAULT);
			f.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_000);
		}		
		
		//-- Peticiones ---------------------------------------------	
		this.indicadorNumeroControlDocumentoLegal = indicador;
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		this.tipoDocumentoContableList = procesoPEDReasignacionDocumentosLegalesService.getTipoDocumentoContableAllList();
		this.siccAccesoList =  getAccesoList(Constants.CODIGO_CANAL_DEFAULT);		
		this.siccSubaccesoList = getSubAccesoList(Constants.CODIGO_ACCESO_DEFAULT);
		this.mostrarBotonExecute = false;
		

		
		
	}
	
	public void setAcceso(ValueChangeEvent e){
		String valor = (String)e.getNewValue();
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		if(valor != null)		
			this.siccAccesoList = getAccesoList(valor);
		else		
			this.siccAccesoList = service.getAccesosTodosByCanalByCodigoISO(usuario.getIdioma().getCodigoISO());		
	}
	
	public void cargarListaSubaccesos(ValueChangeEvent e){
		String valor = (String)e.getNewValue();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ProcesoPEDReasignacionDocumentosLegalesForm f = (ProcesoPEDReasignacionDocumentosLegalesForm)this.formProceso;
		
		if(valor != null)
			this.siccSubaccesoList = getSubAccesoList(valor);
		else{
			this.siccSubaccesoList = svc.getSubaccesosByCodigoISO(usuario.getIdioma().getCodigoISO());
			f.setCodigoSubacceso(Constants.CODIGO_SUBACCESO_DEFAULT);
		}		
	}
	
	//Metodo para asignar doc. Legales
	public void asignar(ActionEvent event){
		try {
			if(validarAsignar())
				return;
			
			AjaxService ajax = (AjaxService)getBean("ajaxService");
			ProcesoPEDReasignacionDocumentosLegalesForm f = (ProcesoPEDReasignacionDocumentosLegalesForm)this.formProceso;
			String[] resultado;
			String cantDocImp,numRegNoNulo;
			
			resultado = ajax.getCantDocImpr(f.getCodigoPais(), f.getTipoDocumentoContable(), f.getCodigoSubacceso(),
					f.getEjercicio(), f.getSerieDocLegal(), f.getRangoDesdeDocInterno(), f.getRangoHastaDocInterno());
			
			cantDocImp = resultado[0];
			numRegNoNulo = resultado[1];
			if( new Integer(numRegNoNulo) == 0 || new Integer(numRegNoNulo) > 0){
				int aux = Integer.parseInt(cantDocImp);
				aux = Integer.parseInt(f.getRangoDesdeDocLegal()) + aux -1;
				f.setRangoHastaDocLegal(Integer.toString(aux));
				if(StringUtils.equals(this.indicadorNumeroControlDocumentoLegal, "1")){				
					int aux1 = Integer.parseInt(cantDocImp);
					aux1 = Integer.parseInt(f.getRangoDesdeNrControl()) + aux1 -1;
					f.setRangoHastaNrControl(Integer.toString(aux1));
				}
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	@Override
	public String setValidarProceso() {
		String mensaje = null;
		ProcesoPEDReasignacionDocumentosLegalesForm f = (ProcesoPEDReasignacionDocumentosLegalesForm)this.formProceso;	
		
		
		if(StringUtils.isBlank(f.getTipoDocumentoContable())){
			mensaje="Tipo de Documento Contable - Campo Requerido";
			return mensaje;
		}
		
		
		if(StringUtils.isBlank(f.getCodigoCanal())){
			mensaje="Canal - Campo Requerido";
			return mensaje;
		}
	
		if(StringUtils.isBlank(f.getCodigoAcceso())){
			mensaje="Acceso - Campo Requerido";
			return mensaje;
		}	
		
		if(StringUtils.isBlank(f.getCodigoSubacceso())){
			mensaje="SubAcceso - Campo Requerido";
			return mensaje;
		}
		
		if(StringUtils.isBlank(f.getEjercicio())){
			mensaje="Número de Ejercicio - Campo Requerido";
			return mensaje;
		}
		
		if(StringUtils.isBlank(f.getSerieDocLegal())){
			mensaje="Número de serie de Documento Legal - Campo Requerido";
			return mensaje;
		}		
		
		if(StringUtils.isBlank(f.getRangoDesdeDocInterno())){
			mensaje="Rango desde Doc. Interno - Campo Requerido";
			return mensaje;
		}		
		
		if(StringUtils.isBlank(f.getRangoDesdeDocInterno())){
			mensaje="Rango desde Doc. Interno - Campo Requerido";
			return mensaje;
		}		
		
		if(StringUtils.isBlank(f.getRangoHastaDocInterno())){
			mensaje="Rango hasta Doc. Interno - Campo Requerido";
			return mensaje;
		}	
		
		if(StringUtils.isBlank(f.getRangoDesdeDocLegal())){
			mensaje="Rango desde Doc. Legal - Campo Requerido";
			return mensaje;
		}	
		
		if(StringUtils.isBlank(f.getRangoDesdeDocLegal())){
			mensaje="Rango desde Doc. Legal - Campo Requerido";
			return mensaje;
		}
		
		if(StringUtils.equals(this.indicadorNumeroControlDocumentoLegal, "1")){
			if(StringUtils.isBlank(f.getRangoDesdeNrControl())){
				mensaje="Rango desde Doc. Legal - Campo Requerido";	
				return mensaje;
			}
		}	
		
		return mensaje;		
	}
	
	
	//validaciones Previas antes de Asignar
	public boolean validarAsignar(){
		ProcesoPEDReasignacionDocumentosLegalesForm f = (ProcesoPEDReasignacionDocumentosLegalesForm)this.formProceso;
		if(StringUtils.isBlank(f.getTipoDocumentoContable())){
			this.setMensajeAlertaDefault("Tipo de Documento Contable - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getCodigoCanal())){
			this.setMensajeAlertaDefault("Canal - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}

		if(StringUtils.isBlank(f.getCodigoAcceso())){
			this.setMensajeAlertaDefault("Acceso - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getCodigoSubacceso())){
			this.setMensajeAlertaDefault("SubAcceso - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getEjercicio())){
			this.setMensajeAlertaDefault("Número de Ejercicio - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getSerieDocLegal())){
			this.setMensajeAlertaDefault("Número de serie de Documento Legal - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getRangoDesdeDocInterno())){
			this.setMensajeAlertaDefault("Rango desde Doc. Interno - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getRangoDesdeDocInterno())){
			this.setMensajeAlertaDefault("Rango desde Doc. Interno - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getRangoHastaDocInterno())){
			this.setMensajeAlertaDefault("Rango hasta Doc. Interno - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getRangoDesdeDocLegal())){
			this.setMensajeAlertaDefault("Rango desde Doc. Legal - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.isBlank(f.getRangoDesdeDocLegal())){
			this.setMensajeAlertaDefault("Rango desde Doc. Legal - Campo Requerido");
			String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
			this.getRequestContext().execute(ventanaConfirmar);
			return true;	
		}
		
		if(StringUtils.equals(this.indicadorNumeroControlDocumentoLegal, "1")){
			if(StringUtils.isBlank(f.getRangoDesdeNrControl())){
				this.setMensajeAlertaDefault("Rango desde Doc. Legal - Campo Requerido");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return true;	
			}
		}
		return false;		
	}


	public String getIndicadorNumeroControlDocumentoLegal() {
		return indicadorNumeroControlDocumentoLegal;
	}

	public void setIndicadorNumeroControlDocumentoLegal(
			String indicadorNumeroControlDocumentoLegal) {
		this.indicadorNumeroControlDocumentoLegal = indicadorNumeroControlDocumentoLegal;
	}

	public List getSiccCanalList() {
		return siccCanalList;
	}

	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	public List getTipoDocumentoContableList() {
		return tipoDocumentoContableList;
	}

	public void setTipoDocumentoContableList(List tipoDocumentoContableList) {
		this.tipoDocumentoContableList = tipoDocumentoContableList;
	}

	public List getSiccAccesoList() {
		return siccAccesoList;
	}

	public void setSiccAccesoList(List siccAccesoList) {
		this.siccAccesoList = siccAccesoList;
	}

	public List getSiccSubaccesoList() {
		return siccSubaccesoList;
	}

	public void setSiccSubaccesoList(List siccSubaccesoList) {
		this.siccSubaccesoList = siccSubaccesoList;
	}

}
