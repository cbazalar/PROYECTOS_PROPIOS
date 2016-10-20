/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelDatosConsultoraValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECCierreBRService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCDigitacionCADForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ProcesoRECCierreBRForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoRECCierreBRAction extends BaseMantenimientoSearchAbstractAction {

	private List recResultadoBrList = new ArrayList ();
	private List recListaRelacionBoletasRecojoList = new ArrayList ();
	private int recListaRelacionBoletasRecojoCantidad;
	private int cantidadLista;
	private List recListaRelacionBoletasRecojoCorrectasTemporalList = new ArrayList();
	private String recListAgregarIndicador ;
	private String codigoResultado ;
	private DataTableModel listaDataModel;
	private List seleccionTabla;
	public boolean viewValidar;
	public boolean viewNotEmpty;
	public boolean viewEmpty;
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoRECCierreBRForm form = new ProcesoRECCierreBRForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Entering my method 'ProcesoRECCierreBRAction'");
		
		ProcesoRECCierreBRService service = (ProcesoRECCierreBRService) getBean("spusicc.procesoRECCierreBRService");
		ProcesoRECCierreBRForm f = (ProcesoRECCierreBRForm) this.formBusqueda;
		
		
		this.recResultadoBrList = service.getResultadoBRList();
		
		//Eliminar datos de la tabla temporal
		service.deleteTablaTemporal();
		
		f.setCodigoResultadoBR("");
		f.setNumeroLote("");
		this.recListAgregarIndicador = "0";
		f.setIndicadorOcultarBoton(Constants.NUMERO_CERO);		
		f.setBoolNumBr(false);
		this.recListaRelacionBoletasRecojoCantidad = 0;
		this.viewValidar = false;
		this.viewEmpty = false;
		this.viewNotEmpty = false;		
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		
		List aux = new ArrayList();
		aux.add(f);
		this.recListaRelacionBoletasRecojoList = aux;
		this.listaDataModel = new DataTableModel(this.recListaRelacionBoletasRecojoList);	
		
	}
	
	public void procesoValidarRegistros (ActionEvent event){
		
		log.debug("Enter method - procesoValidarRegistros");
		ProcesoRECCierreBRService service = (ProcesoRECCierreBRService) getBean("spusicc.procesoRECCierreBRService");
		ProcesoRECCierreBRForm f = (ProcesoRECCierreBRForm) this.formBusqueda;
		
		log.debug("Código de Resultado = " + f.getCodigoResultadoBR());
		
		log.debug("Entrando al metodo: procesoValidarRegistros - Proceso Tuerca");
		try {
			List lista = service.getProcesoBoletaTemporalList();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			this.cantidadLista = this.recListaRelacionBoletasRecojoList.size();
			int cantidadLista = this.cantidadLista;
			String numeroLote = service.getNumeroLoteBoletasRecojo();
			f.setNumeroLote(numeroLote);
			this.codigoResultado = f.getCodigoResultadoBR();
			f.setCodigoResultadoBR(this.codigoResultado);
			if((lista != null && lista.size()> 0) || cantidadLista > 0){
				// procesa a ejecutar el la validacion del SP
				
				Map params = new HashMap();
					
				params.put("numeroLote", numeroLote);
				params.put("codigoResultadoBR", f.getCodigoResultadoBR());
				params.put("usuarioLogin", usuario.getLogin());
				
				List listaTemporalBoletasCorrectas =this.recListaRelacionBoletasRecojoCorrectasTemporalList;
				params.put("listaTemporalBoletasCorrectas", listaTemporalBoletasCorrectas);
				
				service.executeValidarProcesoBoleta(params);
				
				f.setIndicadorOcultarBoton(Constants.NUMERO_UNO);
				
				//Obtenemos la lista procesada
				lista = (List)MapUtils.getObject(params, "listaProcesada");
				
				this.recListaRelacionBoletasRecojoList = lista;
				if(lista!=null){
					this.recListaRelacionBoletasRecojoCantidad = lista.size();
				}
				else{
					this.recListaRelacionBoletasRecojoCantidad = 0;
				}				
				this.recListAgregarIndicador = "1";
				this.viewNotEmpty = true;
				this.viewEmpty = false;
			}
			
		} catch (Exception e) {
			
			String error = e.getMessage();
			log.debug(error);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
		}	
		
	}

	
	public void validarRegistrosCheck(ActionEvent event){
		
		log.debug("Enter method - validarRegistrosCheck");
		try {
			this.viewValidar = true;
			this.viewEmpty = true;	
			
			ProcesoRECCierreBRForm f = (ProcesoRECCierreBRForm) this.formBusqueda;
			inicializarValores(f);
			List aux = new ArrayList();
			aux.add(f);
			this.recListaRelacionBoletasRecojoList = aux;
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));			
		}
		
	}
	
	public void seteaFocoNumeroBRCambia(String indice){
		
		int i = new Integer(indice).intValue();
		String mensaje = null;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ProcesoRECCierreBRForm f = (ProcesoRECCierreBRForm) this.recListaRelacionBoletasRecojoList.get(i);
		f.setNumeroBR(f.getNumeroBR().trim());
		if(f.getNumeroBR().length() == 8){
			if(this.recListaRelacionBoletasRecojoList.size()>1){				
				for (int j = 0; j < this.recListaRelacionBoletasRecojoList.size()-1; j++) {
					ProcesoRECCierreBRForm lista = (ProcesoRECCierreBRForm) this.recListaRelacionBoletasRecojoList.get(j);
					if(lista.getNumeroBR().equals(f.getNumeroBR())){
						mensaje ="El numero de BR ya se encuentra ingresado";
						f.setNumeroBR(null);
						break;						
					}
				}
				
			}			
			if(mensaje != null){
				this.addError("", mensaje);
			}else
			{
				validaNumeroBR(f.getNumeroBR(),i);
			}				
		}
		else
		{
			String numeroBr= autoCompletar(f.getNumeroBR());
			f.setNumeroBR(numeroBr);

			if(this.recListaRelacionBoletasRecojoList.size()>1){				
				for (int j = 0; j < this.recListaRelacionBoletasRecojoList.size()-1; j++) {
					ProcesoRECCierreBRForm lista = (ProcesoRECCierreBRForm) this.recListaRelacionBoletasRecojoList.get(j);
					log.debug(lista);
					if(lista.getNumeroBR().equals(f.getNumeroBR())){
						mensaje ="El numero de BR ya se encuentra ingresado";
						f.setNumeroBR(null);
						break;						
					}
				}
				
			}			
			if(mensaje != null){
				this.addError("",mensaje);
			}else
			{
				validaNumeroBR(f.getNumeroBR(),i);
			}				
		
		}
		
	}
	
	public String autoCompletar(String numeroBr){
		
		String num="";		
		for (int i = numeroBr.length(); i < 8 ; i++) {
			num = num + "0"; 
			
		}
		numeroBr = num + numeroBr;
		return numeroBr;
		
	}
	
	
	public void validaNumeroBR(String numeroBr,int indice){
		ProcesoRECCierreBRForm f = (ProcesoRECCierreBRForm) this.recListaRelacionBoletasRecojoList.get(indice);
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		String data= ajax.getCierreBrByNumeroBR(numeroBr);		
		
		String [] resultadoCursor;
		resultadoCursor = data.split("_");
		if(resultadoCursor.length == 1){
			this.addError("", data);
			f.setNumeroBR(null);			
		}else{
						
			//Se setea los campos de la lista
			f.setNumeroBR(numeroBr);
			f.setNroRecojo( resultadoCursor[0]);
			f.setCodigoCliente(resultadoCursor[1]);
			f.setNombre(resultadoCursor[2]);
			f.setFlag(resultadoCursor[3]);
			f.setObservacion("");
			
			String numBr = f.getNumeroBR();
			String nroRecojo =  resultadoCursor[0];
			String codigoCliente = resultadoCursor[1];
			String nombre = resultadoCursor[2];
			String flag = resultadoCursor[3];
			
			// se ponen en deshabilitado
			f.setBoolNumBr(true);
			
			//Se agrega la siguiente fila vacía
			ProcesoRECCierreBRForm nuevo = new ProcesoRECCierreBRForm();
			nuevo.setCodigoResultadoBR("");
			nuevo.setNumeroLote("");
			nuevo.setIndicadorOcultarBoton(Constants.NUMERO_CERO);	
			nuevo.setBoolNumBr(false);
			this.recListaRelacionBoletasRecojoList.add(nuevo);
			this.getRequestContext().execute(
					"PrimeFaces.focus('tabla:" + (indice+1)
							+ ":numBr')");
			
			this.listaDataModel = new DataTableModel(this.recListaRelacionBoletasRecojoList);	
			
			this.recListaRelacionBoletasRecojoCantidad = this.recListaRelacionBoletasRecojoList.size()-1;
			//Focus a la caja de texto
//			form.listaNumeroBr[form.listaNumeroBr.length-1].focus();
			ajax.insertProcesoBoletaTemporal(numBr, nroRecojo, codigoCliente, flag,this.recListaRelacionBoletasRecojoCorrectasTemporalList);
			
		}
		
	}
		
	public void deleteX(ActionEvent event){
		
		log.debug("Enter method - deleteX");
		try {
			
			ProcesoRECCierreBRForm f = (ProcesoRECCierreBRForm) this.seleccionTabla.get(0);
			
			//obtiene los valores que concuerdan
			String varNumeroBR = f.getNumeroBR();
			String varNroRecojo = f.getNroRecojo();
			String varCodigoCliente = f.getCodigoCliente();
			
			if(StringUtils.isNotBlank(varNumeroBR) && StringUtils.isNotBlank(varNroRecojo) 
					&& StringUtils.isNotBlank(varCodigoCliente))
			{
				//envio de Ajax para la eliminacion de BD
				AjaxService ajax = (AjaxService)getBean("ajaxService");		
				List temporal = new ArrayList();
				ajax.deleteBoletaTemporal1(varNumeroBR, varNroRecojo, varCodigoCliente, this.recListaRelacionBoletasRecojoCorrectasTemporalList,temporal);
				this.recListaRelacionBoletasRecojoCorrectasTemporalList = temporal;
				for (int i = 0; i < this.recListaRelacionBoletasRecojoList.size(); i++) {
					ProcesoRECCierreBRForm lista = (ProcesoRECCierreBRForm) this.recListaRelacionBoletasRecojoList.get(i);
					if(lista.getNumeroBR().equals(f.getNumeroBR()))
					{
						this.recListaRelacionBoletasRecojoList.remove(i);
					}					
				}
				this.recListaRelacionBoletasRecojoCantidad = this.recListaRelacionBoletasRecojoList.size()-1;
				this.listaDataModel= new DataTableModel(this.recListaRelacionBoletasRecojoList);	
				
			}			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e)) ;
		}
	}
	
	private void inicializarValores(ProcesoRECCierreBRForm f)
	{		
		f.setNumeroBR("");
		f.setNroRecojo("");
		f.setCodigoCliente("");
		f.setNombre("");
		f.setFlag("");
		f.setObservacion("");
		
		this.recListaRelacionBoletasRecojoCorrectasTemporalList = new ArrayList();
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		String mensaje = null;
		if(accion.equals("ELIMINAR"))
		{
			int tam = this.seleccionTabla.size(); 
			if(tam==0){
				mensaje =this.getResourceMessage("procesoRECCierreBRForm.mensaje.confirm.eliminar.error");
			}
			if(tam>1){
				mensaje =this.getResourceMessage("procesoRECCierreBRForm.mensaje.confirm.eliminar.error.unItem;");
			}
		}
		
		return mensaje;	
	
	}

	/**
	 * @return the recResultadoBrList
	 */
	public List getRecResultadoBrList() {
		return recResultadoBrList;
	}

	/**
	 * @param recResultadoBrList the recResultadoBrList to set
	 */
	public void setRecResultadoBrList(List recResultadoBrList) {
		this.recResultadoBrList = recResultadoBrList;
	}

	/**
	 * @return the recListAgregarIndicador
	 */
	public String getRecListAgregarIndicador() {
		return recListAgregarIndicador;
	}

	/**
	 * @param recListAgregarIndicador the recListAgregarIndicador to set
	 */
	public void setRecListAgregarIndicador(String recListAgregarIndicador) {
		this.recListAgregarIndicador = recListAgregarIndicador;
	}

	/**
	 * @return the recListaRelacionBoletasRecojoList
	 */
	public List getRecListaRelacionBoletasRecojoList() {
		return recListaRelacionBoletasRecojoList;
	}

	/**
	 * @param recListaRelacionBoletasRecojoList the recListaRelacionBoletasRecojoList to set
	 */
	public void setRecListaRelacionBoletasRecojoList(
			List recListaRelacionBoletasRecojoList) {
		this.recListaRelacionBoletasRecojoList = recListaRelacionBoletasRecojoList;
	}

	/**
	 * @return the recListaRelacionBoletasRecojoCantidad
	 */
	public int getRecListaRelacionBoletasRecojoCantidad() {
		return recListaRelacionBoletasRecojoCantidad;
	}

	/**
	 * @param recListaRelacionBoletasRecojoCantidad the recListaRelacionBoletasRecojoCantidad to set
	 */
	public void setRecListaRelacionBoletasRecojoCantidad(
			int recListaRelacionBoletasRecojoCantidad) {
		this.recListaRelacionBoletasRecojoCantidad = recListaRelacionBoletasRecojoCantidad;
	}

	/**
	 * @return the cantidadLista
	 */
	public int getCantidadLista() {
		return cantidadLista;
	}

	/**
	 * @param cantidadLista the cantidadLista to set
	 */
	public void setCantidadLista(int cantidadLista) {
		this.cantidadLista = cantidadLista;
	}

	/**
	 * @return the recListaRelacionBoletasRecojoCorrectasTemporalList
	 */
	public List getRecListaRelacionBoletasRecojoCorrectasTemporalList() {
		return recListaRelacionBoletasRecojoCorrectasTemporalList;
	}

	/**
	 * @param recListaRelacionBoletasRecojoCorrectasTemporalList the recListaRelacionBoletasRecojoCorrectasTemporalList to set
	 */
	public void setRecListaRelacionBoletasRecojoCorrectasTemporalList(
			List recListaRelacionBoletasRecojoCorrectasTemporalList) {
		this.recListaRelacionBoletasRecojoCorrectasTemporalList = recListaRelacionBoletasRecojoCorrectasTemporalList;
	}

	/**
	 * @return the codigoResultado
	 */
	public String getCodigoResultado() {
		return codigoResultado;
	}

	/**
	 * @param codigoResultado the codigoResultado to set
	 */
	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	/**
	 * @return the listaDataModel
	 */
	public DataTableModel getListaDataModel() {
		return listaDataModel;
	}

	/**
	 * @param listaDataModel the listaDataModel to set
	 */
	public void setListaDataModel(DataTableModel listaDataModel) {
		this.listaDataModel = listaDataModel;
	}

	/**
	 * @return the seleccionTabla
	 */
	public List getSeleccionTabla() {
		return seleccionTabla;
	}

	/**
	 * @param seleccionTabla the seleccionTabla to set
	 */
	public void setSeleccionTabla(List seleccionTabla) {
		this.seleccionTabla = seleccionTabla;
	}

	/**
	 * @return the viewValidar
	 */
	public boolean isViewValidar() {
		return viewValidar;
	}

	/**
	 * @param viewValidar the viewValidar to set
	 */
	public void setViewValidar(boolean viewValidar) {
		this.viewValidar = viewValidar;
	}

	/**
	 * @return the viewNotEmpty
	 */
	public boolean isViewNotEmpty() {
		return viewNotEmpty;
	}

	/**
	 * @param viewNotEmpty the viewNotEmpty to set
	 */
	public void setViewNotEmpty(boolean viewNotEmpty) {
		this.viewNotEmpty = viewNotEmpty;
	}

	/**
	 * @return the viewEmpty
	 */
	public boolean isViewEmpty() {
		return viewEmpty;
	}

	/**
	 * @param viewEmpty the viewEmpty to set
	 */
	public void setViewEmpty(boolean viewEmpty) {
		this.viewEmpty = viewEmpty;
	}	
}
