package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCLiquidacionLoteBancarioService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCLiquidacionLoteBancarioSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoCCCLiquidacionLoteBancarioSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List cccLotesBancariosList;
	private List siccCuentaCorrienteList;
	private List cccEstadosLoteBancarioList;

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion){
		if (this.beanRegistroSeleccionado == null) {
			String mensaje = this.getResourceMessage("errors.select.item");
			return mensaje;
		}
		return "";
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void ejecutar(ActionEvent evt) {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'ejecutar' method");
		}
		if (!this.verificarRegistroSeleccionado())
			return;

		String mensaje = "";
		try {
			MantenimientoCCCLiquidacionLoteBancarioSearchForm f = (MantenimientoCCCLiquidacionLoteBancarioSearchForm) this.formBusqueda;

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			log.debug("usuario :" + usuario.getLogin());
			HashMap valoresSeleccionados = (HashMap) this.beanRegistroSeleccionado;
			String id = valoresSeleccionados.get("numeroLote").toString();
			if (!StringUtils.isBlank(id)) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + id);
				}
				MantenimientoCCCLiquidacionLoteBancarioService service = (MantenimientoCCCLiquidacionLoteBancarioService) getBean("spusicc.mantenimientoCCCLiquidacionLoteBancarioService");

				Map datos = new HashMap();
				datos.put("codigoPais", f.getCodigoPais());
				datos.put("numeroLote", id);
				datos.put("codigoUsuario", usuario.getLogin());

				if (log.isDebugEnabled()) {
					log.debug("JFA Parameter Map : " + datos.toString());
				}
				service.executeLiquidarLoteBancario(datos);
				mensaje = this
						.getResourceMessage("mantenimientoCCCLiquidacionLoteBancarioList.updated");
				this.addInfo("Info : ", mensaje);
				this.listaBusqueda = setFindAttributes();
				this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCCCLiquidacionLoteBancarioList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCCCLiquidacionLoteBancarioForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCCCLiquidacionLoteBancarioSearchForm form = new MantenimientoCCCLiquidacionLoteBancarioSearchForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'find' method");
		}

		MantenimientoCCCLiquidacionLoteBancarioSearchForm f = (MantenimientoCCCLiquidacionLoteBancarioSearchForm) this.formBusqueda;
		f.setFechaProceso(DateUtil.convertDateToString(f.getFechaProcesoD()));

		MantenimientoCCCLiquidacionLoteBancarioService service = (MantenimientoCCCLiquidacionLoteBancarioService) getBean("spusicc.mantenimientoCCCLiquidacionLoteBancarioService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoCuentaCorriente", f.getCodigoCuentaCorriente());
		criteria.put("estadoLote", f.getEstadoLote());
		criteria.put("fechaProceso", f.getFechaProceso());
		criteria.put("numeroLote", f.getNumeroLote());

		if (log.isDebugEnabled()) {
			log.debug("JFA Parameter Map : " + criteria.toString());
		}
		List resultado = service.getLotesBancariosList(criteria);

		String cantidadRegistrosDevueltos = String.valueOf(resultado.size());
		this.cccLotesBancariosList = new ArrayList();
		this.cccLotesBancariosList = resultado;

		if (log.isDebugEnabled()) {
			log.debug("JFA Return resultado");
			log.debug(cantidadRegistrosDevueltos);
		}

		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'delete' method");
		}

		HashMap valoresSeleccionados = (HashMap) this.beanRegistroSeleccionado;
		String id = valoresSeleccionados.get("numeroLote").toString();

		if (!StringUtils.isBlank(id)) {

			MantenimientoCCCLiquidacionLoteBancarioService service = (MantenimientoCCCLiquidacionLoteBancarioService) getBean("spusicc.mantenimientoCCCLiquidacionLoteBancarioService");

			Map datos = new HashMap();
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoCCCLiquidacionLoteBancarioSearchForm f = (MantenimientoCCCLiquidacionLoteBancarioSearchForm) this.formBusqueda;

			datos.put("codigoPais", f.getCodigoPais());
			datos.put("codigoUsuario", usuario.getLogin());
			datos.put("numeroLote", id);

			service.executeEliminarLoteBancario(datos);
			this.listaBusqueda = new ArrayList();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(listaBusqueda);
			return true;
		}
		return false;
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
		return null;
	}

	/**
	 * Inicializando
	 */
	public void inicializar() {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'view' method");
		}
		inicializar();

		MantenimientoCCCLiquidacionLoteBancarioSearchForm f = (MantenimientoCCCLiquidacionLoteBancarioSearchForm) this.formBusqueda;

		ConsultaCCCGenericoService serviceGenericoCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		// Map para almacenar los parametros
		Map criteria = new HashMap();

		criteria.put("codigoPais", pais.getCodigo());

		f.setCodigoPais(pais.getCodigo());

		// Lista de Cuentas Corrientes para el Pa�s
		// Obteniedo el listado de las Cuentas Corrientes Bancarias
		ConsultaCCCGenericoService serviceCCC = (ConsultaCCCGenericoService) getBean("spusicc.consultaCCCGenericoService");
		this.siccCuentaCorrienteList = serviceCCC
				.getCuentasCorrientesBancariasList(criteria);

		// Obtener los Estados de Los Lotes
		this.cccEstadosLoteBancarioList = serviceGenericoCCC
				.getEstadosLoteBancarioList();

		if (log.isDebugEnabled()) {
			log.debug("JFA Todo Ok: Redireccionando");
		}
	}

	/**
	 * @return the cccLotesBancariosList
	 */
	public List getCccLotesBancariosList() {
		return cccLotesBancariosList;
	}

	/**
	 * @param cccLotesBancariosList
	 *            the cccLotesBancariosList to set
	 */
	public void setCccLotesBancariosList(List cccLotesBancariosList) {
		this.cccLotesBancariosList = cccLotesBancariosList;
	}

	/**
	 * @return the siccCuentaCorrienteList
	 */
	public List getSiccCuentaCorrienteList() {
		return siccCuentaCorrienteList;
	}

	/**
	 * @param siccCuentaCorrienteList
	 *            the siccCuentaCorrienteList to set
	 */
	public void setSiccCuentaCorrienteList(List siccCuentaCorrienteList) {
		this.siccCuentaCorrienteList = siccCuentaCorrienteList;
	}

	/**
	 * @return the cccEstadosLoteBancarioList
	 */
	public List getCccEstadosLoteBancarioList() {
		return cccEstadosLoteBancarioList;
	}

	/**
	 * @param cccEstadosLoteBancarioList
	 *            the cccEstadosLoteBancarioList to set
	 */
	public void setCccEstadosLoteBancarioList(List cccEstadosLoteBancarioList) {
		this.cccEstadosLoteBancarioList = cccEstadosLoteBancarioList;
	}
}