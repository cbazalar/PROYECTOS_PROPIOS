package biz.belcorp.ssicc.web.spusicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERProcesarMovimientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoPERProcesarMovimientoForm;

/**
 * The Class ProcesoPERProcesarMovimientoAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 09/02/2015
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@ManagedBean
@SessionScoped
public class ProcesoPERProcesarMovimientoAction extends BaseInterfazAbstractAction {
	
	private static final long serialVersionUID = 8067354659205417420L;
	private String idNumeroLote;
	private String tipoOrigenDescripcion;
	
	private String pantallaPadre;
	private boolean activarSalirPadre = false;
	

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		ProcesoPERProcesarMovimientoForm form = new ProcesoPERProcesarMovimientoForm();
		return form;
	}
	
	@Override
	protected void setAntesBeforeViewAtributes() throws Exception {
		String codigoInterfaz  	= "PER-P2";	
		String codigoSistema  	= "PER";	
		String codigoProcesoBatch = "03";
		this.parametrosPantalla = new HashMap<String, String>();
		this.parametrosPantalla.put("codigoProcesoBatch",codigoProcesoBatch);	
		this.parametrosPantalla.put("codigoInterfaz", codigoInterfaz);
		this.parametrosPantalla.put("codigoSistema", codigoSistema);
		
	}
		
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParamsBeforeExecute' method");
		}
		
		params = super.prepareParamsBeforeExecute(params, form);
		
		/* Inicializando valores */
		String codigoInterfaz  	= "PER-P2";		
		String codigoSistema   	= codigoInterfaz.substring(0, 3);
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		/* Inicializando Service */
		Map paramTipoOrigen = new HashMap();
		InterfazSiCCService interfazService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		paramTipoOrigen.put("descripcion", this.tipoOrigenDescripcion);
		List baseList = interfazService.getTipoOrigenDatos2(paramTipoOrigen);
		Base base = (Base) baseList.get(0);
		String tipoOrigenDatos = base.getCodigo();
		
		/* Seteando valores para el form */
		ProcesoPERProcesarMovimientoForm f = (ProcesoPERProcesarMovimientoForm) this.formInterfaz;
		f.setCodigoInterfaz(codigoInterfaz);
		f.setCodigoSistema(codigoSistema);
		f.setNumeroLoteInterno(this.idNumeroLote);
		f.setCodigoTipoOrigenDatos(tipoOrigenDatos);
			
		/* Seteando para valores para la ejecucion del proceso */
		params.put("numeroLote","");
		params.put("codigoTipoOrigenDatos",tipoOrigenDatos);
		params.put("numeroLoteInterno", this.idNumeroLote);
		
		/* Encontrando descripcion */
		InterfazService interfazPKService = (InterfazService) getBean("sisicc.interfazService");
		InterfazPK interfazPK = new InterfazPK(pais.getCodigo(), codigoSistema, codigoInterfaz);
		Interfaz interfaz = interfazPKService.getInterfaz(interfazPK);
		params.put("descripcion", interfaz.getDescripcion());
		return params;
	}
	
	
	@Override
	protected Map<String, Object> executeProcessBeforeInterfaz(Map<String, Object> params) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'executeProcessBeforeInterfaz' method");
		}
		/* Inicializando valores */

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		String numeroLoteInterno = (String) params.get("numeroLoteInterno");
		String tipoOrigenDatos   = (String) params.get("codigoTipoOrigenDatos");
		
		/* Seteando para valores para la ejecucion del proceso */
		Map paramsProceso = new HashMap();
		paramsProceso.put("codigoPais", pais.getCodigo());
		paramsProceso.put("numeroLote", numeroLoteInterno);
		paramsProceso.put("numeroLoteExterno","");
		paramsProceso.put("codigoTipoOrigenDatos",tipoOrigenDatos);
		
		/* Invocando al metodo del proceso de procesar Movimiento */
		String exito = "-1";
		params.put("exito", exito);
		ProcesoPERProcesarMovimientoService service = (ProcesoPERProcesarMovimientoService) getBean("spusicc.procesoPERProcesarMovimientoService");
		service.executeProcesarMovimiento(paramsProceso);
		params.put("numeroLote",paramsProceso.get("numeroLoteExterno"));
		params.put("numeroLoteSolicitud",paramsProceso.get("numeroLoteExterno"));
		
		log.debug("Mostrando el numero de Lote " + params.get("numeroLote"));
		exito = "1";
		params.put("exito", exito);		
		return params;
		
	}

	@Override
	protected boolean continueExecuteInterfaz(Map<String, Object> params) {
		boolean flag = false;
		if(params!=null)
		{	if (((String)params.get("exito")).equalsIgnoreCase("1"))
				flag = true;
			else
				flag = false;
		}
		return flag;
	}
	
	/**
	 * Metodo para regresar a la Pantalla Padre de Movimientos Bancarios
	 * @param event
	 */
	public void salirPadre(ActionEvent event) {
		try {
			this.find();
			this.redireccionarPagina(this.pantallaPadre);

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.validarListaInterfaceSalida = false;
	}

	
	/**
	 * @throws Exception
	 */
	public void invocarInterfaz() throws Exception {
		
		this.esProcesoBatch = true;
		this.enEjecucion = true;
		this.viewLogicaNegocio();
		this.executeInterfaz();
		
	}

	/**
	 * @return the idNumeroLote
	 */
	public String getIdNumeroLote() {
		return idNumeroLote;
	}

	/**
	 * @param idNumeroLote the idNumeroLote to set
	 */
	public void setIdNumeroLote(String idNumeroLote) {
		this.idNumeroLote = idNumeroLote;
	}

	/**
	 * @return the tipoOrigenDescripcion
	 */
	public String getTipoOrigenDescripcion() {
		return tipoOrigenDescripcion;
	}

	/**
	 * @param tipoOrigenDescripcion the tipoOrigenDescripcion to set
	 */
	public void setTipoOrigenDescripcion(String tipoOrigenDescripcion) {
		this.tipoOrigenDescripcion = tipoOrigenDescripcion;
	}

	/**
	 * @return the pantallaPadre
	 */
	public String getPantallaPadre() {
		return pantallaPadre;
	}

	/**
	 * @param pantallaPadre the pantallaPadre to set
	 */
	public void setPantallaPadre(String pantallaPadre) {
		this.pantallaPadre = pantallaPadre;
	}

	/**
	 * @return the activarSalirPadre
	 */
	public boolean isActivarSalirPadre() {
		return activarSalirPadre;
	}

	/**
	 * @param activarSalirPadre the activarSalirPadre to set
	 */
	public void setActivarSalirPadre(boolean activarSalirPadre) {
		this.activarSalirPadre = activarSalirPadre;
	}
	
	
	
	
}