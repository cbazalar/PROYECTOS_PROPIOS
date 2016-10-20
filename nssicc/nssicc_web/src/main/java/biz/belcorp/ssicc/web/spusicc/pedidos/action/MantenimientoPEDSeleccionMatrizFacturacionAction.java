package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDConfiguracionOfertaService;
import biz.belcorp.ssicc.web.MPantallaPrincipalBean;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoPEDSeleccionMatrizFacturacionForm;
import biz.belcorp.ssicc.web.spusicc.pre.action.ProcesoPRERegistroAutomaticoAction;
import biz.belcorp.ssicc.web.spusicc.sto.action.MantenimientoSTOOrdenCompraCabeceraAction;

@SessionScoped
@ManagedBean
public class MantenimientoPEDSeleccionMatrizFacturacionAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -6965448052629636680L;
	private String codigoAccionRetorno = null;
	private List pedOfertaMatrizFacturacionPeriodoList;
	private String pedMatrizSeleccionada;
	private MatrizFacturacion matrizVariable;
	private String paginaDireccionar;
	private boolean flagPaginaDireccionar = false;
	
	
	@ManagedProperty(value="#{mantenimientoPEDDefinirOfertaAction}")	
	private MantenimientoPEDDefinirOfertaAction mantenimientoPEDDefinirOferta;
	@ManagedProperty(value="#{procesoPEDAsignarCodigoVentaAction}")	
	private ProcesoPEDAsignarCodigoVentaAction procesoPEDAsignarCodigoVenta;
	@ManagedProperty(value="#{procesoPRERegistroAutomaticoAction}")	
	private ProcesoPRERegistroAutomaticoAction procesoPRERegistroAutomatico;

	public String getSaveForward() {

		if (StringUtils.equals(codigoAccionRetorno,
				Constants.PED_CODIGO_PANTALLA_PROCESO_ASIGNACION)) {
			log.debug("Redireccionando a la pantalla de Proceso de Asignacion de CUV...");

			return "asignacion";
		} else if (StringUtils.equals(codigoAccionRetorno,
				Constants.PED_CODIGO_PANTALLA_DEFINIR_OFERTA)) {
			log.debug("Redireccionando a la pantalla de Definicion de Oferta...");

			return "definicion";
		} else {
			return "view";
		}

	}

	@Override
	protected String getSalirForward() {
		return "mantenimientoPEDSeleccionMatrizFacturacionList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoPEDSeleccionMatrizFacturacionForm form = new MantenimientoPEDSeleccionMatrizFacturacionForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		return false;
	}
	
	

	public void save(ActionEvent evt) {
		String mensaje = "";
		try {
			MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService) getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");
			MantenimientoPEDSeleccionMatrizFacturacionForm editForm = (MantenimientoPEDSeleccionMatrizFacturacionForm) this.formBusqueda;
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			Map criteria = new HashMap();
			criteria.put("codigoPeriodo", editForm.getCodigoPeriodo());

			List lista = service.getEstimadosMatrizFacturacion(criteria);

			MatrizFacturacion matriz = new MatrizFacturacion();

			if (lista != null && lista.size() > 0)
				matriz = (MatrizFacturacion) lista.get(0);
			
			
			BeanUtils.copyProperties(editForm, matriz);
			editForm.setCodigoPais(pais.getCodigo());
			this.matrizVariable = matriz;
			mPantallaPrincipalBean.setPedMatrizSeleccionada(matriz);
			mensaje = this.getResourceMessage("mantenimientoPEDSeleccionMatrizFacturacionForm.grabada");
			this.addInfo("Info : ", mensaje);
			
			if(StringUtils.equals(this.codigoAccionRetorno, Constants.PED_CODIGO_PANTALLA_PROCESO_ASIGNACION))
			{		
				
				log.debug("Redireccionando a la pantalla de Definicion de Oferta...");
				this.mPantallaPrincipalBean.setCodigoAccionRetorno(null);
				this.procesoPEDAsignarCodigoVenta.inicializarValores();
				this.paginaDireccionar = "procesoPEDAsignarCodigoVentaForm";
				this.flagPaginaDireccionar = true;
			}
			
			if(StringUtils.equals(codigoAccionRetorno, Constants.PED_CODIGO_PANTALLA_REGISTRO_AUTOMATICO))
			{
				log.debug("Redireccionando a la pantalla de Registro Automatico...");
				this.mPantallaPrincipalBean.setCodigoAccionRetorno(null);
				this.procesoPRERegistroAutomatico.inicializarValores();
				this.paginaDireccionar = "procesoPRERegistroAutomaticoForm";
				this.flagPaginaDireccionar = true;
			}
			
			if(StringUtils.equals(codigoAccionRetorno, Constants.PED_CODIGO_PANTALLA_DEFINIR_OFERTA))
			{
				log.debug("Redireccionando a la pantalla de Definicion de Oferta...");
				this.mPantallaPrincipalBean.setCodigoAccionRetorno(null);
				this.mantenimientoPEDDefinirOferta.inicializarValores();
				this.paginaDireccionar = "mantenimientoPEDDefinirOfertaForm";
				this.flagPaginaDireccionar = true;
			}
					
			if (this.flagPaginaDireccionar) {
				this.setMensajeAlertaDefaultAction(this.getResourceMessage("mantenimientoPEDSeleccionMatrizFacturacionForm.grabada"));
				RequestContext.getCurrentInstance().update("principalFormAlertAction:textoMensajeAlerta");
				String ventana = "PF('principalFormAlertAction_alertDialogAction').show()";
				this.getRequestContext().execute(ventana);
		    }

		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void setConfirmarAlertDialogAction() throws Exception {
		   this.redireccionarPagina(this.paginaDireccionar);
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	public void inicializar(){
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
	}
	
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		inicializar();
		MantenimientoPEDConfiguracionOfertaService service = (MantenimientoPEDConfiguracionOfertaService) getBean("spusicc.mantenimientoPEDConfiguracionOfertaService");

		MantenimientoPEDSeleccionMatrizFacturacionForm editForm = (MantenimientoPEDSeleccionMatrizFacturacionForm) this.formBusqueda;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		if(StringUtils.equals(this.mPantallaPrincipalBean.getCurrentMenu(),Constants.CODIGO_MENU_PEDSELECCIONMATRIZFACTURACION)){
			this.mPantallaPrincipalBean.setCodigoAccionRetorno(null);
		}

		this.codigoAccionRetorno = this.mPantallaPrincipalBean.getCodigoAccionRetorno();

		List periodos = service.getEstimadosMatrizFacturacion(null);

		this.pedOfertaMatrizFacturacionPeriodoList = periodos;

		// Buscamos en session la matriz seleccionada
		MatrizFacturacion matriz = this.mPantallaPrincipalBean.getPedMatrizSeleccionada();
//		MatrizFacturacion matriz = this.matrizVariable;

		if (matriz == null) {
			// seteamos la matriz que corresponde a la campa�a activa
			Map criteriaPeriodo = new HashMap();
			MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			criteriaPeriodo.put("codigoPais", pais.getCodigo());
			criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
			criteriaPeriodo.put("indicadorActiva", Constants.NUMERO_UNO);

			PedidoControlFacturacion controlFacturacion = controlFactService
					.getControlFacturacionById(criteriaPeriodo);

			criteriaPeriodo.put("codigoPeriodo",
					controlFacturacion.getCodigoPeriodo());
			List lista = service.getEstimadosMatrizFacturacion(criteriaPeriodo);

			if (lista != null && lista.size() > 0)
				matriz = (MatrizFacturacion) lista.get(0);
		}

		BeanUtils.copyProperties(editForm, matriz);
		editForm.setCodigoPais(pais.getCodigo());

	}

	/**
	 * @return the codigoAccionRetorno
	 */
	public String getCodigoAccionRetorno() {
		return codigoAccionRetorno;
	}

	/**
	 * @param codigoAccionRetorno the codigoAccionRetorno to set
	 */
	public void setCodigoAccionRetorno(String codigoAccionRetorno) {
		this.codigoAccionRetorno = codigoAccionRetorno;
	}

	/**
	 * @return the pedOfertaMatrizFacturacionPeriodoList
	 */
	public List getPedOfertaMatrizFacturacionPeriodoList() {
		return pedOfertaMatrizFacturacionPeriodoList;
	}

	/**
	 * @param pedOfertaMatrizFacturacionPeriodoList the pedOfertaMatrizFacturacionPeriodoList to set
	 */
	public void setPedOfertaMatrizFacturacionPeriodoList(
			List pedOfertaMatrizFacturacionPeriodoList) {
		this.pedOfertaMatrizFacturacionPeriodoList = pedOfertaMatrizFacturacionPeriodoList;
	}

	/**
	 * @return the pedMatrizSeleccionada
	 */
	public String getPedMatrizSeleccionada() {
		return pedMatrizSeleccionada;
	}

	/**
	 * @param pedMatrizSeleccionada the pedMatrizSeleccionada to set
	 */
	public void setPedMatrizSeleccionada(String pedMatrizSeleccionada) {
		this.pedMatrizSeleccionada = pedMatrizSeleccionada;
	}

	/**
	 * @return the matrizVariable
	 */
	public MatrizFacturacion getMatrizVariable() {
		return matrizVariable;
	}

	/**
	 * @param matrizVariable the matrizVariable to set
	 */
	public void setMatrizVariable(MatrizFacturacion matrizVariable) {
		this.matrizVariable = matrizVariable;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the mantenimientoPEDDefinirOferta
	 */
	public MantenimientoPEDDefinirOfertaAction getMantenimientoPEDDefinirOferta() {
		return mantenimientoPEDDefinirOferta;
	}

	/**
	 * @param mantenimientoPEDDefinirOferta the mantenimientoPEDDefinirOferta to set
	 */
	public void setMantenimientoPEDDefinirOferta(
			MantenimientoPEDDefinirOfertaAction mantenimientoPEDDefinirOferta) {
		this.mantenimientoPEDDefinirOferta = mantenimientoPEDDefinirOferta;
	}

	/**
	 * @return the paginaDireccionar
	 */
	public String getPaginaDireccionar() {
		return paginaDireccionar;
	}

	/**
	 * @param paginaDireccionar the paginaDireccionar to set
	 */
	public void setPaginaDireccionar(String paginaDireccionar) {
		this.paginaDireccionar = paginaDireccionar;
	}

	/**
	 * @return the flagPaginaDireccionar
	 */
	public boolean isFlagPaginaDireccionar() {
		return flagPaginaDireccionar;
	}

	/**
	 * @param flagPaginaDireccionar the flagPaginaDireccionar to set
	 */
	public void setFlagPaginaDireccionar(boolean flagPaginaDireccionar) {
		this.flagPaginaDireccionar = flagPaginaDireccionar;
	}

	/**
	 * @return the procesoPEDAsignarCodigoVenta
	 */
	public ProcesoPEDAsignarCodigoVentaAction getProcesoPEDAsignarCodigoVenta() {
		return procesoPEDAsignarCodigoVenta;
	}

	/**
	 * @param procesoPEDAsignarCodigoVenta the procesoPEDAsignarCodigoVenta to set
	 */
	public void setProcesoPEDAsignarCodigoVenta(
			ProcesoPEDAsignarCodigoVentaAction procesoPEDAsignarCodigoVenta) {
		this.procesoPEDAsignarCodigoVenta = procesoPEDAsignarCodigoVenta;
	}

	/**
	 * @return the procesoPRERegistroAutomatico
	 */
	public ProcesoPRERegistroAutomaticoAction getProcesoPRERegistroAutomatico() {
		return procesoPRERegistroAutomatico;
	}

	/**
	 * @param procesoPRERegistroAutomatico the procesoPRERegistroAutomatico to set
	 */
	public void setProcesoPRERegistroAutomatico(
			ProcesoPRERegistroAutomaticoAction procesoPRERegistroAutomatico) {
		this.procesoPRERegistroAutomatico = procesoPRERegistroAutomatico;
	}
	
	
	
	
}