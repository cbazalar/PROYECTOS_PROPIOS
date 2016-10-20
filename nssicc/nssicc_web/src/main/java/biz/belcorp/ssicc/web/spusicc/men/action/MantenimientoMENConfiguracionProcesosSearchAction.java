package biz.belcorp.ssicc.web.spusicc.men.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.hxtt.sql.ba;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.men.model.BaseMensaje;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMMinimoNuevasService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENIngresoGerenteZonalesService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENPlantillaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMMinimoNuevasSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENConfiguracionProcesosForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENConfiguracionProcesosSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENEscaleraGananciaSearchForm;


@ManagedBean
@SessionScoped
public class MantenimientoMENConfiguracionProcesosSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2039671691149644047L;
	public static final int MSG_CANTIDAD_SELECCION_TOTAL = 100;
	private List msgPlantillaList;
	private List msgProcesoList;
	private List msgConfiguracionProcesoList;
	
	private boolean mostrarNombrePrograma;
	private String nombreProgramaEjecutar;
	private String titleProgramaEjecutar;
	private String indicadorEdit;
	private String flagPlantilla;
	private List msgVistaParametrosList;
	private List msgVistaPopupParametrosList;
	private List msgPlantillaDetalList;
	private List msgParametrosPlantillaList;
	
	private static final String TIPO_DATO_POPUP = "POPUP";
	private static final String TIPO_DATO_COMBOBOX = "LISTBOX";
	private static final String TIPO_DATO_TEXTBOX = "TEXTBOX";
	private static final String TIPO_DATO_CHKBOX = "CHKBOX";

	private String indicadorMultiple;
	private List msgValoresDefectoList;
	private List msgValoresDefectoPopupList;//No se usa
	private Base[][] baseData;
	
	private static final String ID_VALOR_DEFECTO = "valorDefecto"; 
	private static final String ID_CAMPANHA ="campanha";
	private static final String ID_FECHA ="fecha";
	private static final String ID_TEXTOLABEL ="textoLabel";
	private static final String ID_SELEC_HORA_INICIO ="seleccionHoraInicio";
	private static final String ID_SELEC_MIN_INICIO ="seleccionMinutoInicio";
	private static final String ID_SELEC_TIEMPO_INICIO ="seleccionTiempoInicio";
	private static final String ID_DEFECTO_POPUP = "idListaDefectoPopup";
	
	private boolean mostrarBotonSaveAdicional;
	private boolean mostrarBotonCargar;
	private int indexValid;
	private String idCompValid;
	private String tipoDatoValid;
	private int correlativo=1;
	private boolean mostrarPopupGenerico;
    private static final String POPUP_GENERICO = "POPUP_GENERICO";
	
	@ManagedProperty(value="#{busquedaGenericaPOPUPSearchAction}")
	private BusquedaGenericaPOPUPSearchAction busquedaGenericaPOPUPSearchAction;
    private String accionGenerico;
	
    private List horaActividadList;
    private List minutoActividadList;
    private List tiempoActividadList;
    
    private boolean[] disabledCombo;
    private boolean[] disabledTextPopUp;
    private boolean[] disabledPopUp;
    private boolean[] disabledFecha;
    private boolean[] disabledCampanha;
    private boolean[] disabledTextoLabel;
    private boolean[] disabledHoraMinTiemp;
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoMENConfiguracionProcesosForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		return new MantenimientoMENConfiguracionProcesosSearchForm();
	}
	
	@Override
	protected void setInvocarPopup(String accion) {
		this.accionGenerico = accion;
		int index = NumberUtils.toInt(getParam(accion, 1));
	    accion = getParam(accion, 0);
		
		if (accion.equals(this.POPUP_GENERICO)){ 
			this.mostrarPopupGenerico = true;
		}
		Map map = (Map)this.msgParametrosPlantillaList.get(index);
		this.busquedaGenericaPOPUPSearchAction.setValorPosible(map.get("valorPosible").toString());
		
	}
	/* 
	 * index: 0 --> Nombre del Accion
	 *        1 --> Indice Popup
	 * */
	public String getParam(String cad,int index){
		return StringUtils.split(cad, "|")[index];
	}
	
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setAceptarPopupHipCliente' method");
		}
		accion = this.accionGenerico;
		int index = NumberUtils.toInt(getParam(accion, 1));
		accion = getParam(accion, 0);
	
		this.mostrarProcesoBatch = true;
		this.mostrarPopupGenerico = false;
		if (accion.equals(this.POPUP_GENERICO)) {
			this.busquedaGenericaPOPUPSearchAction.verificarRegistro(event);
			if (this.busquedaGenericaPOPUPSearchAction.isSeleccionoRegistro()) {
				Map cliente = (Map) this.busquedaGenericaPOPUPSearchAction.getBeanRegistroSeleccionado();				
				MantenimientoMENConfiguracionProcesosForm f = (MantenimientoMENConfiguracionProcesosForm) this.formMantenimiento;				
				
				f.getTextPopup()[index]= cliente.get("codigo").toString();
				this.busquedaGenericaPOPUPSearchAction.setBeanRegistroSeleccionado(null);
				this.formMantenimiento =  f;				
			}
		}	
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
	}
	
	@Override
	protected void setSalirPopup() {
		this.mostrarProcesoBatch = true;
		this.mostrarPopupGenerico = false;
		this.busquedaGenericaPOPUPSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("'setViewAttributes'");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoMENConfiguracionProcesosSearchForm f = (MantenimientoMENConfiguracionProcesosSearchForm) this.formBusqueda; 
	    f.setCodigoPais(pais.getCodigo());

		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		
		f.setCodigoPais(pais.getCodigo());
		Map map= new HashMap();
		map.put("codigoPais", pais.getCodigo());
		//combo plantilla
		this.msgPlantillaList = service.getPlantillaCabecera(map);
		//combo proceso 
		this.msgProcesoList = service.getProcesos(map);
		//combo tipo proceso										
		//session.removeAttribute(Constants.MSG_CONFIGURACION_PROCESO_LIST);
		//session.removeAttribute("indicadorNuevo");
		this.mostrarNombrePrograma = false;
		this.setMostrarBotonConsultar(false);
		this.setMostrarBotonSave(false);
		this.titleProgramaEjecutar = this.getResourceMessage("mantenimientoMENConfiguracionProcesosForm.nombreProgramaEjecutar");
	}
	
	 public void loadNombrePrograma(ValueChangeEvent val){
		 if (log.isDebugEnabled()) {
				log.debug("loadNombrePrograma");
			}
		String codigoProceso = (String) val.getNewValue();

		this.nombreProgramaEjecutar = getNombrePrograma(codigoProceso);
		this.mostrarNombrePrograma = true;
	}
	 
	 public String getNombrePrograma(String codigoProceso){
		 AjaxService ajax = (AjaxService) getBean("ajaxService");
		 String nombreProgramaEjecutar = ajax.getNombreProgramaProceso(codigoProceso);
		 return nombreProgramaEjecutar;
	 }
	
	@Override
	protected void setAddAttributes() throws Exception {
		log.debug("'setAddAttributes'");
		MantenimientoMENConfiguracionProcesosForm f = (MantenimientoMENConfiguracionProcesosForm) this.formMantenimiento;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		this.mostrarNombrePrograma = false;
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.msgValoresDefectoList=  new ArrayList();
		this.msgValoresDefectoPopupList = new ArrayList();
		this.nombreProgramaEjecutar = "";
		
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoPlantilla("");
		f.setCodigoProceso("");
		f.setTipoProceso("");
		f.setOrdenEjecucion("");
		f.setOrdenEjecucionEdicion("");
		f.setObservaciones("");
		f.setIndicadorActivo(Constants.NUMERO_CERO);
		f.setbIndicadorActivo(f.getIndicadorActivo().equals(Constants.NUMERO_UNO) ? true : false);

		 this.indicadorEdit = Constants.NUMERO_CERO;
		 this.flagPlantilla = Constants.NUMERO_CERO;
		 this.setMostrarBotonSaveAdicional(false);
		 this.setMostrarBotonSave(false);
		 this.setMostrarBotonCargar(true);
	}
	
	/**
	 * Devuelve la lista de minutos
	 * @return
	 */
	private String[] getListaMinutos() {
		String [] minuto= new String [61];
		for(int i=0;i<minuto.length;i++)
			minuto[i]=StringUtils.leftPad(String.valueOf(i), 2, '0');
		return minuto;
	}

	
	/**
	 * Manda a session la lista de minutos, hora
	 * @param session
	 */
	private void setearListaHora() {
		String[] stringHora = Constants.EDU_HORA_LIST;
		List listaHora = new ArrayList();
		for(int i=0; i < stringHora.length; i++) {
			Base base = new Base();
			base.setCodigo(stringHora[i]);
			base.setDescripcion(stringHora[i]);
			listaHora.add(base);
		}
		String[] stringMin = getListaMinutos();
		List listaMin = new ArrayList();
		for(int i=0; i < stringMin.length; i++) {
			Base base = new Base();
			base.setCodigo(stringMin[i]);
			base.setDescripcion(stringMin[i]);
			listaMin.add(base);
		}
		String[] stringTiempo = Constants.EDU_TIEMPO_AM_PM_LIST;
		List listaTiempo = new ArrayList();
		for(int i=0; i < stringTiempo.length; i++) {
			Base base = new Base();
			base.setCodigo(stringTiempo[i]);
			base.setDescripcion(stringTiempo[i]);
			listaTiempo.add(base);
		}
		this.horaActividadList = listaHora;
		this.minutoActividadList = listaMin;
		this.tiempoActividadList = listaTiempo;
		
	}
	
	@Override
	protected void setEditAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setEditAttributes' method");
		}
		Map map = (Map)this.beanRegistroSeleccionado;
		MantenimientoMENConfiguracionProcesosForm f = (MantenimientoMENConfiguracionProcesosForm) this.formMantenimiento;
		
		this.msgValoresDefectoPopupList = new ArrayList();
		this.msgValoresDefectoList=  new ArrayList();
		this.flagPlantilla = Constants.NUMERO_UNO;
		this.indicadorEdit = Constants.NUMERO_UNO;
		//carag de la hora
		//setearListaHora(); 
		if (map != null) {
			try {			
				//cargamos las zonas
				log.debug("map " +map);
				f.setCodigoPais((String)map.get("codigoPais"));
				f.setCodigoPlantilla((String)map.get("codigoPlantilla"));
				f.setCodigoProceso((String)map.get("codigoProceso"));
				f.setTipoProceso((String)map.get("tipoProceso"));
				f.setOrdenEjecucion(String.valueOf(map.get("ordenEjecucion")));
				f.setOrdenEjecucionEdicion(f.getOrdenEjecucion());
				f.setObservaciones((String)map.get("observaciones"));
				f.setIndicadorActivo((String)map.get("indicadorActivo"));
				f.setbIndicadorActivo(f.getIndicadorActivo().equals(Constants.NUMERO_UNO) ? true : false);
				this.nombreProgramaEjecutar = getNombrePrograma(f.getCodigoProceso());
				cargarPlantilla(f,Constants.NUMERO_CERO);
				
				log.debug("enviando para editar");
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) {
				   error = e.getLocalizedMessage();
				}
			   throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));	
			}
		}

		this.setMostrarBotonSaveAdicional(true);
		this.setMostrarBotonCargar(false);
		this.setMostrarBotonSave(false);
	}
	
	public void generarPlanilla(ActionEvent event){
		log.debug("generarPlanilla");
		MantenimientoMENConfiguracionProcesosForm f = (MantenimientoMENConfiguracionProcesosForm) this.formMantenimiento;
		this.indicadorEdit = Constants.NUMERO_CERO;
		
		this.msgValoresDefectoPopupList = new ArrayList();
		this.msgValoresDefectoList=  new ArrayList();
		try {
			cargarPlantilla(f,Constants.NUMERO_UNO);
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));	
		}

	}
	
	/**Retorna true si existe configuracion, false caso contrario 
	 * @param mapCabecera
	 * @return
	 */
	private boolean existeConfiguracion(Map mapCabecera) {
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		List list = service.getProcesosConfigurados(mapCabecera);
		return (list.size()>0?true:false);
	}

	
	/**
	 * Setea si el combo es de parametro multiple
	 * @param listParametrosPlantilla
	 */
	private void setearParametrosMultiples(List listParametrosPlantilla) {
		Iterator it =  listParametrosPlantilla.iterator();
		List listParametrosMultiple= new ArrayList();
		while(it.hasNext()){
			Map map = (Map) it.next();
			String valorPosible = (String)map.get("valorPosible");
			log.debug("setearParametrosMultiples valorPosible "+valorPosible);
			String indicadorTabla = (String)map.get("indicadorTabla");
			map.put("indicadorMultiple", Constants.NUMERO_CERO);
			
			if(StringUtils.isNotEmpty(valorPosible) && StringUtils.equals(indicadorTabla, Constants.NUMERO_UNO)){
				String [] arr = StringUtils.split(valorPosible, ",");
				if(arr!=null && arr.length > 1){
					for(int i=0;i<arr.length;i++)
						listParametrosMultiple.add(arr[i]);
					
					map.put("indicadorMultiple", Constants.NUMERO_UNO);
				}
			}
			map.put("listParametrosMultiple", listParametrosMultiple);
		}		
	}
	
	/**
	 * Manda en session la lista de parametros que estan configurados como 
	    popup
	 * @param session
	 * @param listParametrosPlantilla
	 */
	private void setearListPopup(
			List listParametrosPlantilla) {
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		Iterator it =  listParametrosPlantilla.iterator();
		List listPopup= new ArrayList();
		while(it.hasNext()){
			Map map = (Map) it.next();
			String valorPosible = (String)map.get("valorPosible");
			String tipoDato = (String)map.get("tipoDato");
			
			if(TIPO_DATO_POPUP.equals(tipoDato)){				
				if(!"MAE_PRODU".equals(valorPosible)){
					List list = service.getSelectPlantilla(map);
					listPopup.addAll(list);
				}
				
			}			
		}
		this.msgVistaPopupParametrosList = listPopup;
	}
	
	/**
	 * Retorna la lista de productos
	 * @param valorDefecto
	 * @return
	 */
	private List getListMapProd(String valorDefecto) {
		List list = new ArrayList();
		String [] oids = StringUtils.split(valorDefecto,",");
		Map map= new HashMap();
		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		map.put("valorPosible", "MAE_PRODU");
		for(int i=0;i< oids.length;i++){
			map.put("oid", oids[i]);
			List listPrd = service.getSelectPlantilla(map);
			Iterator it = listPrd.iterator();
			while(it.hasNext()){
				Map res= (Map)it.next();
				Map mapResultado = new HashMap();
				mapResultado.put("oid", String.valueOf(res.get("oid")));
				mapResultado.put("codigo", String.valueOf(res.get("codigo")));
				mapResultado.put("descripcion", String.valueOf(res.get("descripcion")));
				list.add(mapResultado);
			}
		}
		return list;
	}
	
	/**
	 * retorna la lista de detalles d ela plantilla
	 * @param listPlantilla
	 * @param listDetalle
	 * @param f
	 * @return
	 */
	private List setDetallesPlantilla(List listPlantilla,
			List listDetalle) {
		List listValores = new ArrayList();
	//	Iterator it = listDetalle.iterator();
		Iterator itPlantilla =  listPlantilla.iterator();
		String idListaDefecto = "valorDefecto"; 
		String idListaDefectoPopup = "idListaDefectoPopup";
		String idLabel="label";
		String idOrden="orden";
		String idIndicadorVisible="indicadorVisible";
		String idIndicadorModi="indicadorModi";
		String idIndicadorObi="indicadorObi";
		String idCampanha="campanha";
		String idFecha="fecha";
		String idTextoLabel="textoLabel";
		String idSeleccionHora="seleccionHoraInicio";
		String idSeleccionMinuto="seleccionMinutoInicio";
		String idSeleccionTime="seleccionTiempoInicio";
		
        int pos=-1;
		while(itPlantilla.hasNext()){
			Map mapPlantilla = (Map)itPlantilla.next();
			pos++;
			String codigoTipoDatoPlantilla = (String)mapPlantilla.get("codigoTipoDato");
			String tipoDato = (String)mapPlantilla.get("tipoDato");
			String indicadorCampanha = (String)mapPlantilla.get("indicadorCampanha");
			String indicadorFecha = (String)mapPlantilla.get("indicadorFecha");
		    String indicadorHora = (String)mapPlantilla.get("indicadorHora");
			
			for(int j=0;j<listDetalle.size();j++){	
				Map map = (Map)listDetalle.get(j);				
				String codigoTipoDato = (String)map.get("codigoTipoDato");
				String valorSeleccionado = 	(String)map.get("valorSeleccionado");			
				if(StringUtils.equals(codigoTipoDatoPlantilla, codigoTipoDato)){
					String indicadorVisible = (String)map.get("indicadorVisible");
					String indicadorObligatorio = (String)map.get("indicadorObligatorio");
					String indicadorModificable = (String)map.get("indicadorModificado");
					String indicadorTabla = (String)map.get("indicadorTabla");
					String label = (String)map.get("label");

				    String valorDefecto = (String)map.get("valorDefecto");
				    String valorListaTabla = (String)map.get("valorListaTabla");
					int indMultiple = ((BigDecimal)map.get("indMultiple")).intValue();
					int numOrden = ((BigDecimal)map.get("numOrden")).intValue();
					
					BaseMensaje base = new BaseMensaje();
					base.setId(idIndicadorVisible+pos);
					base.setValor(indicadorVisible);
					base.setValorSeleccionado(valorSeleccionado);
					base.setTipoDato(TIPO_DATO_CHKBOX);
					listValores.add(base);
					base = new BaseMensaje();
					base.setId(idIndicadorObi+pos);
					base.setValor(indicadorObligatorio);
					base.setValorSeleccionado(valorSeleccionado);
					base.setTipoDato(TIPO_DATO_CHKBOX);
					listValores.add(base);
					base = new BaseMensaje();
					base.setId(idIndicadorModi+pos);
					base.setValor(indicadorModificable);
					base.setValorSeleccionado(valorSeleccionado);
					base.setTipoDato(TIPO_DATO_CHKBOX);
					listValores.add(base);		
					
					base = new BaseMensaje();
					base.setId(idLabel+pos);
					base.setValor(label);
					base.setValorSeleccionado(valorSeleccionado);
					base.setTipoDato(TIPO_DATO_TEXTBOX);
					listValores.add(base);
					
					base = new BaseMensaje();
					base.setId(idOrden+pos);
					base.setValor(numOrden==99?"":String.valueOf(numOrden));
					base.setValorSeleccionado(valorSeleccionado);
					base.setTipoDato(TIPO_DATO_TEXTBOX);
					listValores.add(base);
										
					
					if(StringUtils.equals(TIPO_DATO_COMBOBOX, tipoDato)){				
						//log.debug("valor selecionado "+valorSeleccionado);
						if(indMultiple>1){//SE TRATA DE COMBO MULTIPLE
							int index1=j;
							int index2=0;
							for(int i = index1; i<index1+indMultiple;i++){
								Map mapMult = (Map)listDetalle.get(i);
								
								log.debug("index "+ i);
								//if(i!=index1) itPlantilla.next();//ya se tiene el primer objeto del multiple, es x eso que aprtir del segundo se va sacando
							    valorDefecto = (String)mapMult.get("valorDefecto");
							    valorSeleccionado = (String)mapMult.get("valorSeleccionado");
								base = new BaseMensaje();
								base.setId(idListaDefecto+pos+"_"+index2);
								base.setValor(valorDefecto);
								base.setValorSeleccionado(valorSeleccionado);
								base.setTipoDato(TIPO_DATO_COMBOBOX);
								base.setIndicadorModificado(indicadorModificable);
								base.setIndicadorObligatorio(indicadorObligatorio);
								listValores.add(base);
								index2++;
							}									
						}else{//COMBO BOX SIMPLE
							 base = new BaseMensaje();
							base.setId(idListaDefecto+pos);
							base.setValor(valorDefecto);
							base.setValorSeleccionado(valorSeleccionado);
							base.setIndicadorModificado(indicadorModificable);
							base.setIndicadorObligatorio(indicadorObligatorio);
							base.setTipoDato(TIPO_DATO_COMBOBOX);
							listValores.add(base);
						}				
			    	}
			    	if(StringUtils.equals(TIPO_DATO_TEXTBOX, tipoDato)){	    	
			    		if(StringUtils.equals(Constants.NUMERO_UNO, indicadorCampanha)){

			    		    base = new BaseMensaje();
							base.setId(idCampanha+pos);
							base.setValor(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado:valorDefecto);
							base.setValorSeleccionado(valorSeleccionado);
							base.setIndicadorModificado(indicadorModificable);
							base.setIndicadorObligatorio(indicadorObligatorio);
							base.setTipoDato(TIPO_DATO_TEXTBOX);
							listValores.add(base);
			    		}
			    		if(StringUtils.equals(Constants.NUMERO_UNO, indicadorFecha)){

			    			 base = new BaseMensaje();
							 base.setId(idFecha+pos);
							 base.setValor(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado:valorDefecto);
							 base.setValorSeleccionado(valorSeleccionado);
							 base.setIndicadorModificado(indicadorModificable);
							 base.setIndicadorObligatorio(indicadorObligatorio);
							 base.setTipoDato(TIPO_DATO_TEXTBOX);
							 listValores.add(base);
			    		}
			    		if(StringUtils.equals(Constants.NUMERO_UNO, indicadorHora)){

			    			 base = new BaseMensaje();
						  	 base.setId(idSeleccionHora+pos);
						  	 base.setValor(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado.substring(0,2):valorDefecto.substring(0, 2));
							 base.setValorSeleccionado(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado.substring(0,2):"");
							 base.setIndicadorModificado(indicadorModificable);
							 base.setIndicadorObligatorio(indicadorObligatorio);
							 base.setTipoDato(TIPO_DATO_TEXTBOX);
							 listValores.add(base);
			    			 base = new BaseMensaje();
						  	 base.setId(idSeleccionMinuto+pos);
						  	 base.setValor(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado.substring(3,5):valorDefecto.substring(3, 5));
							 base.setValorSeleccionado(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado.substring(3,5):"");
							 base.setIndicadorModificado(indicadorModificable);
							 base.setIndicadorObligatorio(indicadorObligatorio);
							 base.setTipoDato(TIPO_DATO_TEXTBOX);
							 listValores.add(base);
			    			 base = new BaseMensaje();
						  	 base.setId(idSeleccionTime+pos);
						  	 base.setValor(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado.substring(6):valorDefecto.substring(6));
							 base.setValorSeleccionado(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado.substring(6):"");
							 base.setIndicadorModificado(indicadorModificable);
							 base.setIndicadorObligatorio(indicadorObligatorio);
							 base.setTipoDato(TIPO_DATO_TEXTBOX);
							 listValores.add(base);
			    		}
			    		if(StringUtils.equals(Constants.NUMERO_CERO, indicadorHora)
			    		  && StringUtils.equals(Constants.NUMERO_CERO, indicadorFecha)
			    		  && StringUtils.equals(Constants.NUMERO_CERO, indicadorCampanha)){

			    			 base = new BaseMensaje();
							 base.setId(idTextoLabel+pos);
							 base.setValor(valorDefecto);
							 base.setValorSeleccionado(valorSeleccionado);
							 base.setIndicadorModificado(indicadorModificable);
							 base.setIndicadorObligatorio(indicadorObligatorio);
							 base.setIndicadorTabla(indicadorTabla);
							 base.setTipoDato(TIPO_DATO_TEXTBOX);
							 listValores.add(base);
			    		}	    		
			    	}	    	
			    	if(StringUtils.equals(TIPO_DATO_POPUP, tipoDato)){
						base = new BaseMensaje();
						base.setId(idListaDefectoPopup+pos);
						base.setValor(StringUtils.isNotEmpty(valorSeleccionado)?valorSeleccionado:valorDefecto);
						base.setValorSeleccionado(valorSeleccionado);
						base.setTipoDato(TIPO_DATO_POPUP);
						base.setValorTabla(valorListaTabla);
						base.setIndicadorModificado(indicadorModificable);
						base.setIndicadorObligatorio(indicadorObligatorio);
						if(StringUtils.equals("MAE_PRODU", valorListaTabla)){
							//se arma la lista de MAP si valor is not null
						  if(StringUtils.isNotEmpty(base.getValor())){	
							List list = getListMapProd(base.getValor());//Map de oid,codigo,descripcion
							base.setArrProducto(list);
						  }	
						}
						listValores.add(base);							    		
			    	}
			    
			    	break;
				}	
		    	
			}
		}	

		return listValores;
	}
	
	/**
	 * Realiza la carga de la plantilla
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void cargarPlantilla(MantenimientoMENConfiguracionProcesosForm f,String nuevo)
			throws Exception {
		log.debug("cargarPlantilla");

		MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
		Map map = new HashMap();		
		map.put("codigoProceso", f.getCodigoProceso());
		map.put("tipoProceso", f.getTipoProceso());						
	   if(existeConfiguracion(map) && nuevo.equals(Constants.NUMERO_UNO)){//mapping=nul es seteado cuado es edicion distinto d enulo cuando es nuevo	    	
		   this.addWarn("", this.getResourceMessage("mantenimientoMENConfiguracionProcesosForm.cabecera.existe.configuracion"));
		   return;
   	   }
      //validamos si existe orden de ejecuion para el tipo de proceos seleccionado
	   Map mapOrden = new HashMap();
	   mapOrden.put("tipoProceso", f.getTipoProceso());
	   mapOrden.put("ordenEjecucion", f.getOrdenEjecucion());
	   if(existeConfiguracion(mapOrden) && nuevo.equals(Constants.NUMERO_UNO)){//mapping=nul es seteado cuado es edicion distinto d enulo cuando es nuevo	    	
		   this.addWarn("", this.getResourceMessage("mantenimientoMENConfiguracionProcesosForm.cabecera.existe.orden.ejecucion"));
		   return;
	   }
	   
	   map.put("codigoPlantilla", f.getCodigoPlantilla());
	   
		List listParametrosPlantilla = service.getParametrosPlantillaDetalleVisibles(map);
		setearParametrosMultiples(listParametrosPlantilla);		
		//mandamos la vista en session con indicador de referncia en 1
		map.put("indicadorRef", Constants.NUMERO_UNO);
		this.msgVistaParametrosList = service.getSelectPlantillaSeleccionado(map);				
		 //seteamos la lista de popup 
		 //excepto el mae_produ ,, si es que esta configurado como popup
		//esto es por perfomance		 
		setearListPopup(listParametrosPlantilla);		 
		//seteamos la lista de time
		setearListaHora();
		 
		map.put("codigoProceso", f.getCodigoProceso());				
		List listDetalle =service.getPlantillaDetalle(map);
		List listValores = setDetallesPlantilla(listParametrosPlantilla,listDetalle);
		
		if(listValores.size()>0){
			this.msgPlantillaDetalList = listValores;
		}
		this.msgParametrosPlantillaList = listParametrosPlantilla;
		
		int n = 0;
		for (Object objs : msgParametrosPlantillaList) {
			Map map1 = (Map)objs;
			map1.put("nameKeyTipoDato", this.getResourceMessage(map1.get("keyTipoDato").toString()));
			map1.put("index", String.valueOf(n));
			//map1.put("disabledCombo", false);
			//map1.put("disabledTextPopUp", false);
			//map1.put("disabledPopUp", false);
			msgParametrosPlantillaList.set(n,map1);
			n++;
		}
		
		//Se inicializa el array.
		int countParamPlant = CollectionUtils.size(this.msgParametrosPlantillaList);
		
		f.setValorDefecto(new String[countParamPlant]);
		f.setIdComponents(new String[countParamPlant]);
		f.setValorPopup(new String[countParamPlant][]);
		f.setTextPopup(new String[countParamPlant]);
		f.setCampanha(new String[countParamPlant]);
		f.setFecha(new String[countParamPlant]);
		f.setTextoLabel(new String[countParamPlant]);
		f.setSeleccionHoraInicio(new String[countParamPlant]);
		f.setSeleccionMinutoInicio(new String[countParamPlant]);
		f.setSeleccionTiempoInicio(new String[countParamPlant]);
		this.setDisabledCampanha(new boolean[countParamPlant]);
		this.setDisabledFecha(new boolean[countParamPlant]);
		this.setDisabledTextoLabel(new boolean[countParamPlant]);
		this.setDisabledCombo(new boolean[countParamPlant]);
		this.setDisabledTextPopUp(new boolean[countParamPlant]);
		this.setDisabledHoraMinTiemp(new boolean[countParamPlant]);
		this.setDisabledPopUp(new boolean[countParamPlant]);
		
		initArray(countParamPlant, MSG_CANTIDAD_SELECCION_TOTAL);
		String[] valorDefectos = new String[countParamPlant];
		
		int z=0;
		for (Object ppList : msgParametrosPlantillaList) {
			Map parmList = (Map)ppList;
			String index = parmList.get("index").toString();
			int iIndex = NumberUtils.toInt(index);
		
			for (Object pdList : msgPlantillaDetalList) {
				BaseMensaje msgPlanDetaList = (BaseMensaje)pdList;
				String id = msgPlanDetaList.getId();
				String[] valores = StringUtils.split(msgPlanDetaList.getValor(),",");
				String tipo = msgPlanDetaList.getTipoDato();
				String tabla =  msgPlanDetaList.getValorTabla();
				String sele = msgPlanDetaList.getValorSeleccionado();		
				String indObli = msgPlanDetaList.getIndicadorObligatorio();
			    String indModi = msgPlanDetaList.getIndicadorModificado();
			    String inTabla = msgPlanDetaList.getIndicadorTabla();
			    
			    String[] ids = {ID_VALOR_DEFECTO.concat(index),
			    			    ID_DEFECTO_POPUP.concat(index),
			    			    ID_CAMPANHA.concat(index),
			    			    ID_FECHA.concat(index),
			    			    ID_SELEC_HORA_INICIO.concat(index),
			    			    ID_SELEC_MIN_INICIO.concat(index),
			    			    ID_SELEC_TIEMPO_INICIO.concat(index),
			    			    ID_TEXTOLABEL.concat(index)};
			    
				if (ArrayUtils.indexOf(ids, id) != -1) {
					System.out.println("Igual : ".concat(id));
					   //Se inserta los ids de los componentes en la vista.
					   f.getIdComponents()[z] = id;
					   
					   if(StringUtils.equals(tipo,"CHKBOX")){
							 //  if(valor=='1')
							//	 x.checked=true;
						   break;
						 } else if(StringUtils.equals(tipo,"TEXTBOX")){
							 String indicadorCampanha = parmList.get("indicadorCampanha").toString();
							 String indicadorFecha = parmList.get("indicadorFecha").toString();
							 String indicadorHora = parmList.get("indicadorHora").toString();
							 
							if (ArrayUtils.isNotEmpty(valores)) {

							  for (int j = 0; j < valores.length; j++) {
							 
								 if (StringUtils.equals(indicadorCampanha,Constants.NUMERO_UNO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_CERO)) {
									f.getCampanha()[iIndex] = (StringUtils.equals(inTabla,Constants.NUMERO_CERO)) ? sele : valores[j];
									
									if(StringUtils.equals(indObli,Constants.NUMERO_UNO) && 
								    		  StringUtils.equals(indModi,Constants.NUMERO_CERO) && 
								    		    StringUtils.equals(this.indicadorEdit,Constants.NUMERO_UNO)){
								    	
								    	  this.disabledCampanha[iIndex]=true;
								    	
								      }	
									
								 }else if(StringUtils.equals(indicadorCampanha,Constants.NUMERO_CERO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_UNO)){
									 f.getFecha()[iIndex] = (StringUtils.equals(inTabla,Constants.NUMERO_CERO)) ? sele : valores[j];
									 
									 if(StringUtils.equals(indObli,Constants.NUMERO_UNO) && 
								    		  StringUtils.equals(indModi,Constants.NUMERO_CERO) && 
								    		    StringUtils.equals(this.indicadorEdit,Constants.NUMERO_UNO)){
								    	
								    	  this.disabledFecha[iIndex]=true;
								    	
								      }	
									 
								 }else if(StringUtils.equals(indicadorCampanha,Constants.NUMERO_CERO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_CERO) && StringUtils.equals(indicadorHora,Constants.NUMERO_CERO)){
									 f.getTextoLabel()[iIndex] = (StringUtils.equals(inTabla,Constants.NUMERO_CERO)) ? sele : valores[j];
									 
									 if(StringUtils.equals(indObli,Constants.NUMERO_UNO) && 
								    		  StringUtils.equals(indModi,Constants.NUMERO_CERO) && 
								    		    StringUtils.equals(this.indicadorEdit,Constants.NUMERO_UNO)){
								    	
								    	  this.disabledTextoLabel[iIndex]=true;
								      }	
									 
								 }else if(StringUtils.equals(indicadorHora,Constants.NUMERO_UNO)){
				
									 if(StringUtils.equals(ids[4],id)){
										 f.getSeleccionHoraInicio()[iIndex] = (StringUtils.equals(inTabla,Constants.NUMERO_CERO)) ? sele : valores[j];
									 }else if(StringUtils.equals(ids[5],id)){
										 f.getSeleccionMinutoInicio()[iIndex] = (StringUtils.equals(inTabla,Constants.NUMERO_CERO)) ? sele : valores[j];
									 }else if(StringUtils.equals(ids[6],id)){
										 f.getSeleccionTiempoInicio()[iIndex] = (StringUtils.equals(inTabla,Constants.NUMERO_CERO)) ? sele : valores[j]; 
									 }
									 
									 if(StringUtils.equals(indObli,Constants.NUMERO_UNO) && 
								    		  StringUtils.equals(indModi,Constants.NUMERO_CERO) && 
								    		    StringUtils.equals(this.indicadorEdit,Constants.NUMERO_UNO)){
								    	
								    	  this.disabledHoraMinTiemp[iIndex]=true;
								    	
								      }	
							       } 
								}
							 }

							 break;
						 } else if(StringUtils.equals(tipo,"LISTBOX")){
							   int x=0;
							   for (int j = 0; j < valores.length; j++) {
							    	String oidSeleccionado = valores[j]; 
							    	List listData = this.getListData(service, parmList, z);
							    	
									for (Object data : listData) {
										  Map dataView = (Map)data;
										  String oid = dataView.get("oid").toString();
										  if (StringUtils.equals(oid,oidSeleccionado)) {
											  Base base = new Base();
											  base.setCodigo(dataView.get("oid").toString());
											  base.setDescripcion(dataView.get("descripcion").toString());
											  baseData[z][x] = base;
											  //this.msgValoresDefectoPopupList.add(base);
											  
											  if(StringUtils.equals(sele,oidSeleccionado)){//Seleccionar el valor del combo
												  valorDefectos[z]= oidSeleccionado; 
												  f.setValorDefecto(valorDefectos);
											  }
											 ++x;
											 break;
										  }				  
								 	 }
								}

							    //buscar los oid de valor
							    //y crearlos en la lista x defecto
							      if(StringUtils.equals(indObli,Constants.NUMERO_UNO) && 
							    		  StringUtils.equals(indModi,Constants.NUMERO_CERO) && 
							    		    StringUtils.equals(this.indicadorEdit,Constants.NUMERO_UNO)){
							    	  this.disabledCombo[z]=true;
							      }	
							      
							  break;
							  
						   } else if(StringUtils.equals(tipo,"POPUP")){
							    int x=0;
							   //recorrer la lista seleccion index 
							    //y crearlos en la lista x defecto
							    if(StringUtils.equals(tabla,"MAE_PRODU")){			    
								 //recorro la nueva lista que contine un map de oid,descripcion y codigo
							      List listArrProd = msgPlanDetaList.getArrProducto();
							      for (Object objListArraProd : listArrProd) {
							    	  Map mapArr = (Map)objListArraProd;
							    	  Base base = new Base();
									  base.setCodigo(mapArr.get("codigo").toString().concat(" - ").concat(mapArr.get("descripcion").toString()));
									  base.setDescripcion(mapArr.get("oid").toString());	  
									  baseData[z][x] = base;
									  this.msgValoresDefectoPopupList.add(base);
									  ++x;
								  }

							    }
							    else{
							    
							     if (ArrayUtils.isNotEmpty(valores)) {
							    	for (int j = 0; j < valores.length; j++) {
									  String oidSeleccionado = valores[j]; 
							    	  List msgVistaPopup = this.msgVistaPopupParametrosList;
							    	    for (Object data : msgVistaPopup) {
										  Map dataViewPopUp = (Map)data;
										  String oid = dataViewPopUp.get("oid").toString();
										  String val = dataViewPopUp.get("descripcion").toString();
										  String tablaPopUp = dataViewPopUp.get("tabla").toString();
										  String codigo = dataViewPopUp.get("codigo").toString();
										  
										  if (StringUtils.equals(oid,oidSeleccionado) && StringUtils.equals(tabla,tablaPopUp)) {
											  Base base = new Base();
											  base.setCodigo(oid);
											  base.setDescripcion(codigo.concat(" - ").concat(val));
											  baseData[z][x] = base;
											  this.msgValoresDefectoPopupList.add(base);
											 ++x;
											 break;
										  }				  
								 	    }
							    	 }
							       }
							    	
							    } 
							        
							    if(StringUtils.equals(indObli,Constants.NUMERO_UNO) && 
							    		StringUtils.equals(indModi,Constants.NUMERO_CERO) && 
							    		   StringUtils.equals(indicadorEdit,Constants.NUMERO_UNO)){
							    	this.disabledTextPopUp[z]=true;
							    	this.disabledPopUp[z]=true;
							     }
							break;    
					  	 }
				   }
			   }
			
		   z++;	
		}
		
		this.flagPlantilla = Constants.NUMERO_UNO;
		this.setMostrarBotonSaveAdicional(true);
		this.setMostrarBotonCargar(false);
	}
	
	public void initArray(int sizeRow,int sizeColumn){
		baseData = new Base[sizeRow][sizeColumn];
		for (int i = 0; i < baseData.length; i++) {
			for (int j = 0; j < baseData[i].length; j++) {
				Base base = new Base();
				base.setCodigo("null");
				base.setDescripcion("null");
				baseData[i][j] = base;
			}
		}
	}
	

	public List getListData(MantenimientoMENPlantillaService service,Map map,int index){
		
        String codigoTipoDato = String.valueOf(map.get("codigoTipoDato")); 
        String indicadorTabla= (String)map.get("indicadorTabla");
        String indicadorMultiple = (String)map.get("indicadorMultiple");
        List lista=null;
        
        if(StringUtils.equals(Constants.NUMERO_UNO,indicadorTabla)){
        	
        	if(StringUtils.equals(Constants.NUMERO_CERO,indicadorMultiple))
        	   lista = service.getSelectPlantilla(map);
        	else{
        	 List listaMultiple=(List)map.get("listParametrosMultiple");
        	 String valorPosible = (String)listaMultiple.get(0);
        	 Map mapAux = new HashMap();
        	 mapAux.put("valorPosible", valorPosible);
        	 lista = service.getSelectPlantilla(mapAux);
        	}
        } else {
        	//se tratan de constantes  C1,C2,C3,...
        	String valoresPosibles = (String) map.get("valorPosible");
        	String [] listValoress =  StringUtils.split(valoresPosibles, ",");
        	lista = new ArrayList();
        	for(int i=0;i<listValoress.length;i++){
        		Map mapDatos = new HashMap();
            	mapDatos.put("oid",listValoress[i]);
            	mapDatos.put("descripcion",listValoress[i]);
            	lista.add(mapDatos);
        	}        	
        }
		
        return lista;
	}
	
	
	
	public Base findObjInListPopup(Map<String,String> tipoCodigo){
		List list = this.msgValoresDefectoPopupList;
		
		for (Object obj : list) {
			Base base = (Base)obj;
			String cod = StringUtils.trim(StringUtils.split(base.getDescripcion(),"-")[0]);
			if (tipoCodigo.get("codigoPopUp")!=null) {
				if (cod.equals(tipoCodigo.get("codigoPopUp"))) {
					return base;
				}
			} if(tipoCodigo.get("codigoOidPopUp")!=null){//
				if (base.getCodigo().equals(tipoCodigo.get("codigoOidPopUp"))) {
					return base;
				}
			}
			
		}
		return null;
	}
	
	public Map findObjVistaPopupParamList(String codigoPopUp){
		List listVista = this.msgVistaPopupParametrosList;
		
		for (Object obj : listVista) {
			Map map = (Map) obj;
			if (codigoPopUp.equals(map.get("codigo").toString())) {
				return map;
			}
		}
		return null;
	}
	
	//***Agregando a la lista multiple**/
	public void addOpcion(ActionEvent event){
		log.debug("'add'");
		MantenimientoMENConfiguracionProcesosForm f = (MantenimientoMENConfiguracionProcesosForm) this.formMantenimiento;
		
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    int index = NumberUtils.toInt(params.get("index"));
		
	    Map obj = (Map) this.msgParametrosPlantillaList.get(index);

	    
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		
		if (CollectionUtils.isEmpty(msgValoresDefectoPopupList)) {
			msgValoresDefectoPopupList = new ArrayList();
		}
		
		String codigoPopup = f.getTextPopup()[index];
        String valorTabla = obj.get("valorPosible").toString();
        
	    if(StringUtils.isNotBlank(codigoPopup)){
	    	
			if(StringUtils.equals(valorTabla,"MAE_PRODU")){	
			  String data = ajax.validarCodigoSAP(f.getCodigoPais(),codigoPopup);
			  
				 if (StringUtils.isBlank(data)) {
					addWarn("", this.getResourceMessage("mantenimientoMENPlantillaConfiguracionProcesosForm.msg.codigo.no.existe"));
					f.getTextPopup()[index]="";
					return;
				 }
			  
			} else{
				
				if (findObjVistaPopupParamList(codigoPopup) == null) {
					addWarn("", this.getResourceMessage("mantenimientoMENPlantillaConfiguracionProcesosForm.msg.codigo.no.existe"));
					f.getTextPopup()[index]="";
					return;
				}
				
			 //validar si codigo existe en el arreglo de popups
				//  alert('validando en array de popup');	
				//codigo=CAR01, indicadorSelec=1, oidRef=0, oid=1, tablaRef=null, descripcion=Nivel de riesgo, tabla=MSG_MENSA
				List listVista = this.msgVistaPopupParametrosList;
				Base base = null;
				Map<String, String> valores = new HashMap<String, String>();
				
				for (Object objVista : listVista) {
					 Map mapVista = (Map) objVista;
					 String codigoVista = mapVista.get("codigo").toString();
					 String tablaVista = mapVista.get("tabla").toString();
					 
					 if(StringUtils.equals(codigoPopup,codigoVista) 
								 && StringUtils.equals(valorTabla,tablaVista)){
						  
						  valores.put("codigoPopUp", codigoPopup);
						  
						  if(findObjInListPopup(valores) == null){	
							    base = new Base();
							    base.setCodigo(mapVista.get("oid").toString());
							    base.setDescripcion(codigoPopup.concat(" - ").concat(mapVista.get("descripcion").toString()));
							    Base[] bd = baseData[index];
							    int z=0;
							    for (Base bs : bd) {
							    	if (StringUtils.equals(bs.getCodigo(),"null") && StringUtils.equals(bs.getDescripcion(),"null")) {
							    		baseData[index][z] = base;
							    		break;
									}
							      z++;
								}

							    this.msgValoresDefectoPopupList.add(base);
							    
						  	}else{
						  		this.addWarn("", this.getResourceMessage("mantenimientoMENPlantillaConfiguracionProcesosForm.msg.codigo.existe.in.lista"));
						  		break;
						  	}
			
					 }
				}
			 	
			}			
	    
	    } else{
	    	addWarn("", this.getResourceMessage("mantenimientoMENPlantillaConfiguracionProcesosForm.msg.validar.codigoPopup"));
	    }
	    
	}
	
	public void removeOpcion(ActionEvent event){
		log.debug("'remove'");

		MantenimientoMENConfiguracionProcesosForm f = (MantenimientoMENConfiguracionProcesosForm) this.formMantenimiento;
		Map<String, String> valores = new HashMap<String, String>();
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    int index = NumberUtils.toInt(params.get("index"));
	    Base[] base = baseData[index];
				
		if (ArrayUtils.isNotEmpty(base)) {
			String[][] valorPopUp = f.getValorPopup();
		
			if (ArrayUtils.isNotEmpty(valorPopUp[index])) {
				
				for (String val : valorPopUp[index]) {
					valores.put("codigoOidPopUp", val);
					
					Base bs = findObjInListPopup(valores);
					this.msgValoresDefectoPopupList.remove(bs);
					int i=0;
					//Encontrar y Eliminar del Array
					for (Base b : base) {
						if (StringUtils.equals(b.getCodigo(),bs.getCodigo())) {
							Base bs1 =  new Base();
							bs1.setCodigo("null");
							bs1.setDescripcion("null");
							base[i] = bs1;
						}
					  i++;
					}

				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("'setFindAttributes'");
		MantenimientoMENConfiguracionProcesosSearchForm f = (MantenimientoMENConfiguracionProcesosSearchForm) this.formBusqueda;
		
		List list = null;
		try {
			
			MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
			//enviando en session los parametros de mensaje
			Map map = new HashMap();
			map.put("codigoPais", f.getCodigoPais());
			map.put("codigoPlantilla", f.getCodigoPlantilla());
			map.put("codigoProceso", f.getCodigoProceso());
			map.put("observacion", f.getObservaciones());
			map.put("indicadorActivo",f.getIndicadorActivo());
			map.put("tipoProceso", f.getTipoProceso());
			map.put("ordenEjecucion", f.getOrdenEjecucion());

			list = service.getProcesosConfigurados(map);
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
			   error = e.getLocalizedMessage();
			}
		   throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));	
		}
		
		return list;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {	
		log.debug("'setDeleteAttributes'");
		 Map map = (Map) this.beanRegistroSeleccionado;
		 //MantenimientoMENEscaleraGananciaSearchForm f = (MantenimientoMENEscaleraGananciaSearchForm) this.formBusqueda;
		 Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		  try {							

				if (map != null) {
										
					  MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");								
						
					  service.deleteProcesoPlantilla(map);
						//enviamos el mensaje de satisfaccion
						//messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
							//"mantenimientoMENConfiguracionProcesosSearchForm.cabecera.deleted"));
						//saveMessages(request.getSession(), messages);
						//this.setFindAttributes(request,form);	
				 }
			} catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) {
					error = e.getLocalizedMessage();
				}
				throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
		}
			
			
		return true;
	}
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoMENConfiguracionProcesosList";
	}
	
	
	/**
	 * Setea los tipos de datos Combo
	 * @param listPlantillaDetalle
	 * @param request 
	 * @param mapDato
	 * @param f
	 * @param index 
	 * @param valoresDefecto 
	 */
	private void setearListaPlantillaCombo(List listPlantillaDetalle,
			 Map mapDato, MantenimientoMENConfiguracionProcesosForm f, String valoresDefectoSimple,String valorDefectoMulti) {

		Map mapDetalle = new HashMap();

		String codigoTipoDato = (String)mapDato.get("codigoTipoDato");

		mapDetalle.put("codigoTipoDato", codigoTipoDato);
		mapDetalle.put("codigoPlantilla",f.getCodigoPlantilla());		
		mapDetalle.put("valorTabla", mapDato.get("valorPosible"));

		String indicadorMultiple = (String)mapDato.get("indicadorMultiple");
					   	
		if(StringUtils.equals(indicadorMultiple, Constants.NUMERO_CERO)){//simple		  
			  mapDetalle.put("valorSele", valoresDefectoSimple);
			  mapDetalle.put("numeroCorrelativo", correlativo);		
		 }else{
				//combo multiple
				log.debug("multiple ");

		 }

		listPlantillaDetalle.add(mapDetalle);
	}
	
	/**
	 * Setea los tipos de datos Popup
	 * @param listPlantillaDetalle
	 * @param request 
	 * @param mapDato
	 * @param f
	 * @param index 
	 * @param valoresDefecto 
	 */
	private void setearListaPlantillaPopup(List listPlantillaDetalle,
			 Map mapDato, MantenimientoMENConfiguracionProcesosForm f, String[] valoresDefecto) {
		log.debug("setar popup");
		Map mapDetalle = new HashMap();

		String codigoTipoDato = (String)mapDato.get("codigoTipoDato");
		String valTabla = (String)mapDato.get("valorPosible");

		mapDetalle.put("codigoTipoDato", codigoTipoDato);
		mapDetalle.put("numeroCorrelativo", correlativo);
		mapDetalle.put("codigoPlantilla",f.getCodigoPlantilla());
		mapDetalle.put("valorTabla", valTabla);
		mapDetalle.put("valorSele", StringUtils.join(valoresDefecto, ","));
		
		listPlantillaDetalle.add(mapDetalle);
	}
	
	/**
	 * Setea los tipos de datos Text
	 * @param listPlantillaDetalle
	 * @param request 
	 * @param mapDato
	 * @param f
	 * @param index 
	 * @param valoresDefecto 
	 */
	private void setearListaPlantillaText(List listPlantillaDetalle,
			 Map mapDato, MantenimientoMENConfiguracionProcesosForm f, int index) {
		Map mapDetalle = new HashMap();

		String indicadorCampanha = (String)mapDato.get("indicadorCampanha");
		String indicadorFecha = (String)mapDato.get("indicadorFecha");
	    String indicadorHora = (String)mapDato.get("indicadorHora");
		String codigoTipoDato = (String)mapDato.get("codigoTipoDato");
		int numCorrelativo =1;
		mapDetalle.put("codigoTipoDato", codigoTipoDato);
		mapDetalle.put("numeroCorrelativo", numCorrelativo);
		mapDetalle.put("codigoPlantilla",f.getCodigoPlantilla());
		
		if (StringUtils.equals(indicadorCampanha,Constants.NUMERO_UNO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_CERO)) {
				mapDetalle.put("valoresDefecto", f.getCampanha()[index]);
				mapDetalle.put("valorSele", f.getCampanha()[index]);
		  }else if(StringUtils.equals(indicadorCampanha,Constants.NUMERO_CERO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_UNO)){
			    mapDetalle.put("valoresDefecto", f.getFecha()[index]);
				mapDetalle.put("valorSele", f.getFecha()[index]);
		  }else if(StringUtils.equals(indicadorCampanha,Constants.NUMERO_CERO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_CERO) && StringUtils.equals(indicadorHora,Constants.NUMERO_CERO)){
				
				mapDetalle.put("valoresDefecto",f.getTextoLabel()[index]);
				mapDetalle.put("valorSele",f.getTextoLabel()[index]);
				 
		  }else if(StringUtils.equals(indicadorHora,Constants.NUMERO_UNO)){
				
			  mapDetalle.put("valoresDefecto",f.getSeleccionHoraInicio()[index]+":"+
						f.getSeleccionMinutoInicio()[index]+":"+
						f.getSeleccionTiempoInicio()[index]);

			  mapDetalle.put("valorSele",f.getSeleccionHoraInicio()[index]+":"+
				   f.getSeleccionMinutoInicio()[index]+":"+
				   f.getSeleccionTiempoInicio()[index]);
		}
	
		listPlantillaDetalle.add(mapDetalle);
	}
	
	@Override
	protected boolean setSaveAttributes() throws Exception {
		log.debug("entrando a: 'setSaveAttributes'");
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENConfiguracionProcesosForm f = (MantenimientoMENConfiguracionProcesosForm) this.formMantenimiento;
		
		try{
		
			MantenimientoMENPlantillaService service = (MantenimientoMENPlantillaService) getBean("spusicc.mantenimientoMENPlantillaService");
			String [] valoresDefecto = f.getValorDefecto();
			String [][] valoresPopup = f.getValorPopup();
			
		    List listProcesoCabec = new ArrayList();	  
		    List listPlantillaDetalle = new ArrayList();
		    List listParametrosPlantilla=this.msgParametrosPlantillaList;
		    Iterator it = listParametrosPlantilla.iterator();
		    //anhadiendo la cabera
		    Map mapCabecera = new HashMap();
		    mapCabecera.put("codigoPais", pais.getCodigo());
		    mapCabecera.put("codigoPlantilla", f.getCodigoPlantilla());
		    mapCabecera.put("codigoProceso", f.getCodigoProceso());
		    mapCabecera.put("observacion", f.getObservaciones());
		    mapCabecera.put("tipoProceso", f.getTipoProceso());
		    mapCabecera.put("indicadorActivo", f.isbIndicadorActivo() ? Constants.NUMERO_UNO : Constants.NUMERO_CERO);
		    mapCabecera.put("mapDetallePlantilla", listPlantillaDetalle);
		    mapCabecera.put("ordenEjecucion", f.getOrdenEjecucion());
		    listProcesoCabec.add(mapCabecera);

			//se reorrerar la lista de parametos de los tipo de datos
		    while(it.hasNext()){
		    	Map mapDato = (Map)it.next();
		    	String tipoDato = (String)mapDato.get("tipoDato");
		    	int index = NumberUtils.toInt(mapDato.get("index").toString());
		    	
		    	if(StringUtils.equals(TIPO_DATO_COMBOBOX, tipoDato)){
		    		setearListaPlantillaCombo(listPlantillaDetalle,mapDato,f,valoresDefecto[index],null);
		    	}
		    	if(StringUtils.equals(TIPO_DATO_TEXTBOX, tipoDato)){
		    		setearListaPlantillaText(listPlantillaDetalle,mapDato,f,index);
		    	}
		    	if(StringUtils.equals(TIPO_DATO_POPUP, tipoDato)){
		    		setearListaPlantillaPopup(listPlantillaDetalle,mapDato,f,valoresPopup[index]);
		    	}
		    }
		    //preguntamos si es edit 
		       String edit= this.indicadorEdit;
		       if(StringUtils.equals(edit, Constants.NUMERO_UNO)){
		    	 //validamos el orden de ejecucion si es que ha sido modificado
		    	  if(!StringUtils.equals(f.getOrdenEjecucion(),f.getOrdenEjecucionEdicion())){ 
		    		   Map mapOrden = new HashMap();
		    		   mapOrden.put("tipoProceso", f.getTipoProceso());
		    		   mapOrden.put("ordenEjecucion", f.getOrdenEjecucion());
		    		   if(existeConfiguracion(mapOrden)){
		    			  addWarn("", this.getResourceMessage("mantenimientoMENConfiguracionProcesosForm.cabecera.existe.orden.ejecucion"));
		    			  return false;
		    		   }
	    		   }
			    	 service.updateProcesoPlantilla(listProcesoCabec,usuario);	    	 

		       }
		       else{	  
		    	   //validar si ya se configuro proceos para la planilla
		    	   if(!existeConfiguracion(mapCabecera)){
		    		   //validamos el orden de ejecucion
		    		   Map mapOrden = new HashMap();
		    		   mapOrden.put("tipoProceso", f.getTipoProceso());
		    		   mapOrden.put("ordenEjecucion", f.getOrdenEjecucion());
		    		   
		    		    if(existeConfiguracion(mapOrden)){	    	
		    			  addWarn("", this.getResourceMessage("mantenimientoMENConfiguracionProcesosForm.cabecera.existe.orden.ejecucion"));
		    			  return false;
		    		   }
		    		   
			    	   service.insertProcesoPlantilla(listProcesoCabec,usuario);	    	 

		    	   }else{
		    		   addWarn("", this.getResourceMessage("mantenimientoMENConfiguracionProcesosForm.cabecera.existe.configuracion"));
		    		   return false;
		    	   }
		       }

		}catch(Exception e){
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) {
				error = e.getLocalizedMessage();
			}
			throw new Exception(this.getResourceMessage("errors.detail", new Object[]{ error }));
							
		}
		
		if (this.indicadorEdit.equals(Constants.NUMERO_CERO)) {
			this.flagPlantilla=Constants.NUMERO_CERO;
			this.setMostrarBotonSaveAdicional(false);
			this.setMostrarBotonCargar(true);
			
			f.setCodigoPais(pais.getCodigo());
			f.setCodigoPlantilla("");
			f.setCodigoProceso("");
			f.setTipoProceso("");
			f.setOrdenEjecucion("");
			f.setOrdenEjecucionEdicion("");
			f.setObservaciones("");
			f.setIndicadorActivo(Constants.NUMERO_CERO);
			f.setbIndicadorActivo(f.getIndicadorActivo().equals(Constants.NUMERO_UNO) ? true : false);

		}else{
			this.setMostrarBotonSaveAdicional(true);
			this.setMostrarBotonCargar(false);
		}
	
		return true;
	}
	
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Map mapa = (Map) this.beanRegistroSeleccionado;
		MantenimientoMENConfiguracionProcesosSearchForm form = (MantenimientoMENConfiguracionProcesosSearchForm) this.formBusqueda;
		MantenimientoMENConfiguracionProcesosForm mForm = new MantenimientoMENConfiguracionProcesosForm();
		
		BeanUtils.populate(mForm, mapa);

	
		
		if(this.accion.equals(this.ACCION_CONSULTAR)){
			this.setMostrarBotonSave(false);
		}else{
			this.setMostrarBotonSave(true);
		}
		
		if(!this.accion.equals(this.ACCION_NUEVO)){
			mForm.setNewRecord(false);
		}
		

		return mForm;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		String ind = this.indicadorEdit;
		if(ind.equals(Constants.NUMERO_CERO)) {
			return "mantenimientoMENConfiguracionProcesosForm.cabecera.insert";
		}else{
			return "mantenimientoMENConfiguracionProcesosForm.cabecera.update";
		}
	}
	
	@Override
	public String setValidarMantenimiento() {
		log.debug("setValidarMantenimiento");
		MantenimientoMENConfiguracionProcesosForm f = (MantenimientoMENConfiguracionProcesosForm) this.formMantenimiento;
		
		if (CollectionUtils.isEmpty(this.msgParametrosPlantillaList)) {
			return null;
		}
		
		for (Object ppList : this.msgParametrosPlantillaList) {
			 Map map = (Map)ppList;
			 String tipoDato = map.get("tipoDato").toString();
			 String indicadorObligatorio = map.get("indicadorObligatorio").toString();
			 String indicadorModificado = map.get("indicadorModificado").toString();
			 String label = (map.get("label") == null) ? "" : map.get("label").toString();
			 int index = NumberUtils.toInt(map.get("index").toString());
			 String indicadorMultiple = map.get("indicadorMultiple").toString();
			 String indicadorCampanha = map.get("indicadorCampanha").toString();
			 String indicadorFecha = map.get("indicadorFecha").toString();
			 String indicadorHora = map.get("indicadorHora").toString();
			
			 if(StringUtils.equals(tipoDato,"CHKBOX")){
				 
			   break;
			 } else if(StringUtils.equals(tipoDato,"TEXTBOX")){
				   
				 if (StringUtils.equals(indicadorCampanha,Constants.NUMERO_UNO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_CERO)) {
					 if(StringUtils.equals(indicadorObligatorio,Constants.NUMERO_UNO)){ 
					        if (StringUtils.isBlank(f.getCampanha()[index])) {
					        	 return label.concat(" es obligatorio");
							}
					 }
				  }else if(StringUtils.equals(indicadorCampanha,Constants.NUMERO_CERO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_UNO)){

						 if(StringUtils.equals(indicadorObligatorio,Constants.NUMERO_UNO)){ 
						        if (StringUtils.isBlank(f.getFecha()[index])) {
						        	 return label.concat(" es obligatorio");
								}
						 }
				  }else if(StringUtils.equals(indicadorCampanha,Constants.NUMERO_CERO)  && StringUtils.equals(indicadorFecha,Constants.NUMERO_CERO) && StringUtils.equals(indicadorHora,Constants.NUMERO_CERO)){
						
						 if(StringUtils.equals(indicadorObligatorio,Constants.NUMERO_UNO)){ 
						        if (StringUtils.isBlank(f.getTextoLabel()[index])) {
						        	 return label.concat(" es obligatorio");
								}
						 }
						 
					 }else if(StringUtils.equals(indicadorHora,Constants.NUMERO_UNO)){
						
						 if(StringUtils.equals(indicadorObligatorio,Constants.NUMERO_UNO)){ 
						        if (StringUtils.isBlank(f.getSeleccionHoraInicio()[index]) || 
						        		     StringUtils.isBlank(f.getSeleccionMinutoInicio()[index]) || 
						        		     	  StringUtils.isBlank(f.getSeleccionTiempoInicio()[index])) {
						        	 return label.concat(" es obligatorio");
						}
					}

				}
				 
			 } else if(StringUtils.equals(tipoDato,"LISTBOX")){
				 
				if(StringUtils.equals(indicadorMultiple,Constants.NUMERO_CERO)){

				   String valoresDefecto = f.getValorDefecto()[index];
				   String idCompValid = f.getIdComponents()[index];
				   if(StringUtils.equals(indicadorObligatorio,Constants.NUMERO_UNO)){ 
		 				   if(StringUtils.isBlank(valoresDefecto)){
		 				    // addWarn("", label.concat(" es obligatorio"));
		 					 this.setIndexValid(index);
		 					 this.setIdCompValid(idCompValid);
		 					 this.setTipoDatoValid(tipoDato);
		 				     return label.concat(" es obligatorio");
		 				 } 
	 				}
				 } 
			   } else if(StringUtils.equals(tipoDato,"POPUP")){
				   String[] valores = f.getValorPopup()[index];
				   if(StringUtils.equals(indicadorObligatorio,Constants.NUMERO_UNO)){ 
					   if (ArrayUtils.isEmpty(valores)) {
						   return label.concat(" es obligatorio");
					   }
				   }
			   }
			 
		}
		
		
		this.setIndexValid(-1);
		
		return null;
	}

	/**
	 * @return the msgPlantillaList
	 */
	public List getMsgPlantillaList() {
		return msgPlantillaList;
	}

	/**
	 * @param msgPlantillaList the msgPlantillaList to set
	 */
	public void setMsgPlantillaList(List msgPlantillaList) {
		this.msgPlantillaList = msgPlantillaList;
	}

	/**
	 * @return the msgProcesoList
	 */
	public List getMsgProcesoList() {
		return msgProcesoList;
	}

	/**
	 * @param msgProcesoList the msgProcesoList to set
	 */
	public void setMsgProcesoList(List msgProcesoList) {
		this.msgProcesoList = msgProcesoList;
	}

	/**
	 * @return the msgConfiguracionProcesoList
	 */
	public List getMsgConfiguracionProcesoList() {
		return msgConfiguracionProcesoList;
	}

	/**
	 * @param msgConfiguracionProcesoList the msgConfiguracionProcesoList to set
	 */
	public void setMsgConfiguracionProcesoList(List msgConfiguracionProcesoList) {
		this.msgConfiguracionProcesoList = msgConfiguracionProcesoList;
	}

	/**
	 * @return the mostrarNombrePrograma
	 */
	public boolean isMostrarNombrePrograma() {
		return mostrarNombrePrograma;
	}

	/**
	 * @param mostrarNombrePrograma the mostrarNombrePrograma to set
	 */
	public void setMostrarNombrePrograma(boolean mostrarNombrePrograma) {
		this.mostrarNombrePrograma = mostrarNombrePrograma;
	}

	/**
	 * @return the nombreProgramaEjecutar
	 */
	public String getNombreProgramaEjecutar() {
		return nombreProgramaEjecutar;
	}

	/**
	 * @param nombreProgramaEjecutar the nombreProgramaEjecutar to set
	 */
	public void setNombreProgramaEjecutar(String nombreProgramaEjecutar) {
		this.nombreProgramaEjecutar = nombreProgramaEjecutar;
	}

	/**
	 * @return the indicadorEdit
	 */
	public String getIndicadorEdit() {
		return indicadorEdit;
	}

	/**
	 * @param indicadorEdit the indicadorEdit to set
	 */
	public void setIndicadorEdit(String indicadorEdit) {
		this.indicadorEdit = indicadorEdit;
	}

	/**
	 * @return the flagPlantilla
	 */
	public String getFlagPlantilla() {
		return flagPlantilla;
	}

	/**
	 * @param flagPlantilla the flagPlantilla to set
	 */
	public void setFlagPlantilla(String flagPlantilla) {
		this.flagPlantilla = flagPlantilla;
	}

	/**
	 * @return the msgVistaParametrosList
	 */
	public List getMsgVistaParametrosList() {
		return msgVistaParametrosList;
	}

	/**
	 * @param msgVistaParametrosList the msgVistaParametrosList to set
	 */
	public void setMsgVistaParametrosList(List msgVistaParametrosList) {
		this.msgVistaParametrosList = msgVistaParametrosList;
	}

	/**
	 * @return the msgVistaPopupParametrosList
	 */
	public List getMsgVistaPopupParametrosList() {
		return msgVistaPopupParametrosList;
	}

	/**
	 * @param msgVistaPopupParametrosList the msgVistaPopupParametrosList to set
	 */
	public void setMsgVistaPopupParametrosList(List msgVistaPopupParametrosList) {
		this.msgVistaPopupParametrosList = msgVistaPopupParametrosList;
	}

	/**
	 * @return the msgPlantillaDetalList
	 */
	public List getMsgPlantillaDetalList() {
		return msgPlantillaDetalList;
	}

	/**
	 * @param msgPlantillaDetalList the msgPlantillaDetalList to set
	 */
	public void setMsgPlantillaDetalList(List msgPlantillaDetalList) {
		this.msgPlantillaDetalList = msgPlantillaDetalList;
	}

	/**
	 * @return the msgParametrosPlantillaList
	 */
	public List getMsgParametrosPlantillaList() {
		return msgParametrosPlantillaList;
	}

	/**
	 * @param msgParametrosPlantillaList the msgParametrosPlantillaList to set
	 */
	public void setMsgParametrosPlantillaList(List msgParametrosPlantillaList) {
		this.msgParametrosPlantillaList = msgParametrosPlantillaList;
	}

	/**
	 * @return the indicadorMultiple
	 */
	public String getIndicadorMultiple() {
		return indicadorMultiple;
	}

	/**
	 * @param indicadorMultiple the indicadorMultiple to set
	 */
	public void setIndicadorMultiple(String indicadorMultiple) {
		this.indicadorMultiple = indicadorMultiple;
	}

	/**
	 * @return the msgValoresDefectoList
	 */
	public List getMsgValoresDefectoList() {
		return msgValoresDefectoList;
	}

	/**
	 * @param msgValoresDefectoList the msgValoresDefectoList to set
	 */
	public void setMsgValoresDefectoList(List msgValoresDefectoList) {
		this.msgValoresDefectoList = msgValoresDefectoList;
	}

	/**
	 * @return the msgValoresDefectoPopupList
	 */
	public List getMsgValoresDefectoPopupList() {
		return msgValoresDefectoPopupList;
	}

	/**
	 * @param msgValoresDefectoPopupList the msgValoresDefectoPopupList to set
	 */
	public void setMsgValoresDefectoPopupList(List msgValoresDefectoPopupList) {
		this.msgValoresDefectoPopupList = msgValoresDefectoPopupList;
	}

	/**
	 * @return the baseData
	 */
	public Base[][] getBaseData() {
		return baseData;
	}

	/**
	 * @param baseData the baseData to set
	 */
	public void setBaseData(Base[][] baseData) {
		this.baseData = baseData;
	}

	/**
	 * @return the titleProgramaEjecutar
	 */
	public String getTitleProgramaEjecutar() {
		return titleProgramaEjecutar;
	}

	/**
	 * @param titleProgramaEjecutar the titleProgramaEjecutar to set
	 */
	public void setTitleProgramaEjecutar(String titleProgramaEjecutar) {
		this.titleProgramaEjecutar = titleProgramaEjecutar;
	}

	/**
	 * @return the mostrarBotonSaveAdicional
	 */
	public boolean isMostrarBotonSaveAdicional() {
		return mostrarBotonSaveAdicional;
	}

	/**
	 * @param mostrarBotonSaveAdicional the mostrarBotonSaveAdicional to set
	 */
	public void setMostrarBotonSaveAdicional(boolean mostrarBotonSaveAdicional) {
		this.mostrarBotonSaveAdicional = mostrarBotonSaveAdicional;
	}

	/**
	 * @return the mostrarBotonCargar
	 */
	public boolean isMostrarBotonCargar() {
		return mostrarBotonCargar;
	}

	/**
	 * @param mostrarBotonCargar the mostrarBotonCargar to set
	 */
	public void setMostrarBotonCargar(boolean mostrarBotonCargar) {
		this.mostrarBotonCargar = mostrarBotonCargar;
	}

	/**
	 * @return the indexValid
	 */
	public int getIndexValid() {
		return indexValid;
	}

	/**
	 * @param indexValid the indexValid to set
	 */
	public void setIndexValid(int indexValid) {
		this.indexValid = indexValid;
	}

	/**
	 * @return the idCompValid
	 */
	public String getIdCompValid() {
		return idCompValid;
	}

	/**
	 * @param idCompValid the idCompValid to set
	 */
	public void setIdCompValid(String idCompValid) {
		this.idCompValid = idCompValid;
	}

	/**
	 * @return the tipoDatoValid
	 */
	public String getTipoDatoValid() {
		return tipoDatoValid;
	}

	/**
	 * @param tipoDatoValid the tipoDatoValid to set
	 */
	public void setTipoDatoValid(String tipoDatoValid) {
		this.tipoDatoValid = tipoDatoValid;
	}

	/**
	 * @return the correlativo
	 */
	public int getCorrelativo() {
		return correlativo;
	}

	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}

	/**
	 * @return the busquedaGenericaPOPUPSearchAction
	 */
	public BusquedaGenericaPOPUPSearchAction getBusquedaGenericaPOPUPSearchAction() {
		return busquedaGenericaPOPUPSearchAction;
	}

	/**
	 * @param busquedaGenericaPOPUPSearchAction the busquedaGenericaPOPUPSearchAction to set
	 */
	public void setBusquedaGenericaPOPUPSearchAction(
			BusquedaGenericaPOPUPSearchAction busquedaGenericaPOPUPSearchAction) {
		this.busquedaGenericaPOPUPSearchAction = busquedaGenericaPOPUPSearchAction;
	}

	/**
	 * @return the mostrarPopupGenerico
	 */
	public boolean isMostrarPopupGenerico() {
		return mostrarPopupGenerico;
	}

	/**
	 * @param mostrarPopupGenerico the mostrarPopupGenerico to set
	 */
	public void setMostrarPopupGenerico(boolean mostrarPopupGenerico) {
		this.mostrarPopupGenerico = mostrarPopupGenerico;
	}

	/**
	 * @return the accionGenerico
	 */
	public String getAccionGenerico() {
		return accionGenerico;
	}

	/**
	 * @param accionGenerico the accionGenerico to set
	 */
	public void setAccionGenerico(String accionGenerico) {
		this.accionGenerico = accionGenerico;
	}

	/**
	 * @return the horaActividadList
	 */
	public List getHoraActividadList() {
		return horaActividadList;
	}

	/**
	 * @param horaActividadList the horaActividadList to set
	 */
	public void setHoraActividadList(List horaActividadList) {
		this.horaActividadList = horaActividadList;
	}

	/**
	 * @return the minutoActividadList
	 */
	public List getMinutoActividadList() {
		return minutoActividadList;
	}

	/**
	 * @param minutoActividadList the minutoActividadList to set
	 */
	public void setMinutoActividadList(List minutoActividadList) {
		this.minutoActividadList = minutoActividadList;
	}

	/**
	 * @return the tiempoActividadList
	 */
	public List getTiempoActividadList() {
		return tiempoActividadList;
	}

	/**
	 * @param tiempoActividadList the tiempoActividadList to set
	 */
	public void setTiempoActividadList(List tiempoActividadList) {
		this.tiempoActividadList = tiempoActividadList;
	}

	/**
	 * @return the disabledCombo
	 */
	public boolean[] getDisabledCombo() {
		return disabledCombo;
	}

	/**
	 * @param disabledCombo the disabledCombo to set
	 */
	public void setDisabledCombo(boolean[] disabledCombo) {
		this.disabledCombo = disabledCombo;
	}

	/**
	 * @return the disabledTextPopUp
	 */
	public boolean[] getDisabledTextPopUp() {
		return disabledTextPopUp;
	}

	/**
	 * @param disabledTextPopUp the disabledTextPopUp to set
	 */
	public void setDisabledTextPopUp(boolean[] disabledTextPopUp) {
		this.disabledTextPopUp = disabledTextPopUp;
	}

	/**
	 * @return the disabledPopUp
	 */
	public boolean[] getDisabledPopUp() {
		return disabledPopUp;
	}

	/**
	 * @param disabledPopUp the disabledPopUp to set
	 */
	public void setDisabledPopUp(boolean[] disabledPopUp) {
		this.disabledPopUp = disabledPopUp;
	}

	/**
	 * @return the disabledFecha
	 */
	public boolean[] getDisabledFecha() {
		return disabledFecha;
	}

	/**
	 * @param disabledFecha the disabledFecha to set
	 */
	public void setDisabledFecha(boolean[] disabledFecha) {
		this.disabledFecha = disabledFecha;
	}

	/**
	 * @return the disabledCampanha
	 */
	public boolean[] getDisabledCampanha() {
		return disabledCampanha;
	}

	/**
	 * @param disabledCampanha the disabledCampanha to set
	 */
	public void setDisabledCampanha(boolean[] disabledCampanha) {
		this.disabledCampanha = disabledCampanha;
	}

	/**
	 * @return the disabledTextoLabel
	 */
	public boolean[] getDisabledTextoLabel() {
		return disabledTextoLabel;
	}

	/**
	 * @param disabledTextoLabel the disabledTextoLabel to set
	 */
	public void setDisabledTextoLabel(boolean[] disabledTextoLabel) {
		this.disabledTextoLabel = disabledTextoLabel;
	}

	/**
	 * @return the disabledHoraMinTiemp
	 */
	public boolean[] getDisabledHoraMinTiemp() {
		return disabledHoraMinTiemp;
	}

	/**
	 * @param disabledHoraMinTiemp the disabledHoraMinTiemp to set
	 */
	public void setDisabledHoraMinTiemp(boolean[] disabledHoraMinTiemp) {
		this.disabledHoraMinTiemp = disabledHoraMinTiemp;
	}
    
	
   

	
	 
	
	
	
	

	
}
