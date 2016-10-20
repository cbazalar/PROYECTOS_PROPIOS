package biz.belcorp.ssicc.web.spusicc.comision.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Zona;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionCalculo;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionClasificacion;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionEscalonada;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisiones;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosRegionesSubGerencia;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMComisionesCobranzaForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMComisionesCobranzaSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoCOMComisionesCobranzaSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 7817102156299341177L;

	private String indicadorTipoProcesoComision;
	private List comTipoComisionistaList;
	private LabelValue[] comBaseComisionList;
	private String comCodigoSubGerencia;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List comTipoClasificacionList;
	private LabelValue[] comClasificacionList;

	private boolean indicadorModificar = false;
	private String indicadorModificaCombo = Constants.NUMERO_CERO;

	private List comComisionDetalList;
	private DatosRegionesSubGerencia regionSeleccionada;

	private List datosCOMComisionesList;
	private DatosComisionCalculo comisionSeleccionada;

	private List datosCOMComisionesEscalonadasList;
	private DatosComisionEscalonada comisionEscalonadaSeleccionada;

	private List datosCOMComisionesEscalonadasList2;
	private DatosComisionEscalonada comisionEscalonadaSeleccionada2;

	private List datosCOMComisionesEscalonadasList3;
	private DatosComisionEscalonada comisionEscalonadaSeleccionada3;

	private List datosCOMComisionesClasificacionList;
	private DatosComisionClasificacion comisionClasificacionSeleccionada;

	private boolean displayClasificacionMostrar;
	private boolean datosRegiTabDisabled;

	private boolean displayDiv3;
	private boolean displayDiv4;
	private boolean displayDiv5;
	private boolean displayDiv6;
	private boolean displayDiv7;
	private boolean displayDiv8;
	private boolean displayDiv10;

	private int mantenimientoComisionesTabActivo;

	private boolean mantenimientoComisionesTabDatosRegionDisabled;
	private boolean mantenimientoComisionesTabDatosComisionesDisabled;
	private boolean mantenimientoComisionesTabDatosEscalonadaDisabled;
	private boolean mantenimientoComisionesTabDatosClasificacionDisabled;

	private String siccRegionSeleccionadaList = "";
	private String siccZonaSeleccionadaList = "";

	@Override
	protected String getSalirForward() {
		return "mantenimientoCOMComisionesCobranzaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCOMComisionesCobranzaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCOMComisionesCobranzaSearchForm searchForm = new MantenimientoCOMComisionesCobranzaSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoCOMComisionesCobranzaSearchAction");
		}

		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		MantenimientoCOMComisionesCobranzaSearchForm f = (MantenimientoCOMComisionesCobranzaSearchForm) this.formBusqueda;

		/* obteniendo valores */
		Map criteria = new HashMap();
		criteria.put("codigoComision", f.getCodigoComision());
		criteria.put("descripcionComision", f.getDescripcionComision());

		/* Obteniendo Lista */
		List resultado = service.getComisionesRecuperacionDetalle(criteria);

		return resultado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setAddAttributes()
	 */
	@Override
	protected void setAddAttributes() throws Exception {

		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String codigoPais = mPantallaPrincipalBean.getCurrentUser()
				.getCodigoPais();
		
		ConvertUtils.register(new DateConverter(null), Date.class);
		BeanUtils.copyProperties(f,
				new MantenimientoCOMComisionesCobranzaForm());
        //f = new MantenimientoCOMComisionesCobranzaForm();
		f.setCodigoPais(codigoPais);
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);

		f.setIndicadorComisionEscalonada(Constants.NUMERO_CERO);
		f.setIndicadorConsideraSD(Constants.NUMERO_CERO);
		f.setIndicadorDctoImpuesto(Constants.NUMERO_CERO);
		f.setIndicadorDsctoReclamoFacturacion(Constants.NUMERO_CERO);
		f.setIndicadorConsiderarCronograma(Constants.NUMERO_CERO);

		f.setIndicadorComisionEscalonadaBoolean(false);
		f.setIndicadorConsideraSDBoolean(false);
		f.setIndicadorDctoImpuestoBoolean(false);
		f.setIndicadorDsctoReclamoFacturacionBoolean(false);
		f.setIndicadorConsiderarCronogramaBoolean(false);

		this.setIndicadorModificar(false);

		String codigoComision = service.getCodigoComisionSiguiente();
		if (StringUtils.isBlank(codigoComision)) {
			codigoComision = "00001";
		}

		this.siccRegionList = aSvc.getRegionByPaisMarcaCanalSubGerencia(
				codigoPais, Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT,
				comCodigoSubGerencia.split("_")[0].toString());
		this.siccZonaList = new LabelValue[] {};

		f.setCodigoSubGerencia(comCodigoSubGerencia);
		f.setCodigoComision(codigoComision);

		this.datosCOMComisionesList = new ArrayList();
		this.datosCOMComisionesEscalonadasList = new ArrayList();
		this.datosCOMComisionesEscalonadasList2 = new ArrayList();
		this.datosCOMComisionesEscalonadasList3 = new ArrayList();
		this.datosCOMComisionesClasificacionList = new ArrayList();
		this.comComisionDetalList = new ArrayList();

		f.setOcultarSubGerenciaRegiones(false);
		f.setMostrarCheckEscalonada(true);

		this.displayClasificacionMostrar = true;

		f.setIndicadorBaseComision(false);// False = Reiniciará todo el combo de
											// tipo de Base Comision

		if (this.comTipoComisionistaList != null
				&& this.comTipoComisionistaList.size() > 0) {
			String tipoComisionista = ((Base) this.comTipoComisionistaList
					.get(0)).getCodigo();
			f.setCodigoTipoComisionista(tipoComisionista);
			this.comBaseComisionList = aSvc.getBasesComision(tipoComisionista);

			if (this.comBaseComisionList != null
					&& this.comBaseComisionList.length > 0) {
				f.setCodigoBaseComision(this.comBaseComisionList[0].getValue());
			}
		}

		this.mantenimientoComisionesTabActivo = Constants.MANTENIMIENTO_COMISIONES_TAB_REGIONES;

		this.mostrarCampos(f);
		this.ocultarDivEscalonada(f);
		this.getIndicadorEscalonada(f);

		this.getRegionesZonasSeleccionadas(null);

	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setDeleteAttributes' method - MantenimientoCOMComisionesCobranzaAction");
		}
		if (!this.verificarRegistroSeleccionado()) {
			return false;
		}

		HashMap sistemabusqueda = (HashMap) this.beanRegistroSeleccionado;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String id = sistemabusqueda.get("oidComision").toString();

		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		Map criteria = new HashMap();
		int resultadoDelete = 0;
		String mensaje = "";
		if (!StringUtils.isBlank(id)) {
			if (log.isDebugEnabled()) {
				log.debug("Eliminar Comisiones");
			}
			criteria.put("oidComision", id);
			resultadoDelete = service.deleteComisionRecuperacion(criteria);

			switch (resultadoDelete) {
			case 0:
				mensaje = this
						.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.delete");				
				break;
			case 1:
				mensaje = this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.comisionesCalculadasxRegion.data");
				throw new Exception(mensaje);
			case 2:
				mensaje = this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.comisionesCalculadasxZona.data");
				throw new Exception(mensaje);

			case 3:
				mensaje = this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.comisionesCalculadas.data");
				throw new Exception(mensaje);
			}
			log.debug("se eliminó el registro ....");
		}
		this.listaBusqueda = this.setFindAttributes();
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoCOMComisionesCobranzaAction");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		List comisionComisionEscalonadaList = null;
		boolean record = false;

		//
		f.setIndicadorComisionEscalonada(f
				.isIndicadorComisionEscalonadaBoolean() ? Constants.NUMERO_UNO
				: Constants.NUMERO_CERO);
		f.setIndicadorConsiderarCronograma(f
				.isIndicadorConsiderarCronogramaBoolean() ? Constants.NUMERO_UNO
				: Constants.NUMERO_CERO);
		f.setIndicadorConsideraSD(f.isIndicadorConsideraSDBoolean() ? Constants.NUMERO_UNO
				: Constants.NUMERO_CERO);
		f.setIndicadorDsctoReclamoFacturacion(f
				.isIndicadorDsctoReclamoFacturacionBoolean() ? Constants.NUMERO_UNO
				: Constants.NUMERO_CERO);
		f.setIndicadorDctoImpuesto(f.isIndicadorDctoImpuestoBoolean() ? Constants.NUMERO_UNO
				: Constants.NUMERO_CERO);
		//

		log.debug("Indicador Comision Escalonada: "
				+ f.getIndicadorComisionEscalonada());

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		try {

			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_05)
					|| StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_07)
					|| (StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_02) && StringUtils
							.equals(f.getIndicadorComisionEscalonada(), "0"))) {

				if (this.datosCOMComisionesList != null
						&& this.datosCOMComisionesList.size() > 0) {
					record = true;
				} else {
					this.setIndicadorModificar(false);
					this.addError(
							"Error: ",
							this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.message.comisionDatosComision.no.ingresados"));
					return false;
				}
			}

			if (!record) {
				if (StringUtils.equals(f.getCodigoBaseComision(),
						Constants.CODIGO_BASE_COMISION_01)
						|| StringUtils.equals(f.getCodigoBaseComision(),
								Constants.CODIGO_BASE_COMISION_03)
						|| StringUtils.equals(f.getCodigoBaseComision(),
								Constants.CODIGO_BASE_COMISION_04)
						|| (StringUtils.equals(f.getCodigoBaseComision(),
								Constants.CODIGO_BASE_COMISION_02) && StringUtils
								.equals(f.getIndicadorComisionEscalonada(), "1"))
						|| StringUtils.equals(f.getCodigoBaseComision(),
								Constants.CODIGO_BASE_COMISION_02)) {

					comisionComisionEscalonadaList = new ArrayList();

					if (StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_01)
							|| StringUtils.equals(f.getCodigoBaseComision(),
									Constants.CODIGO_BASE_COMISION_02)) {
						comisionComisionEscalonadaList = this.datosCOMComisionesEscalonadasList;

					} else if (StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_03)) {
						comisionComisionEscalonadaList = this.datosCOMComisionesEscalonadasList2;

					} else if (StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_04)) {
						comisionComisionEscalonadaList = this.datosCOMComisionesEscalonadasList3;
					}

					if (comisionComisionEscalonadaList != null
							&& comisionComisionEscalonadaList.size() > 0) {
						record = true;
					} else {
						this.setIndicadorModificar(false);
						this.addError(
								"Error: ",
								this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.message.comisionComisionEscalonada.no.ingresados"));
						return false;
					}
				}
			}

			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_06))
				record = true;

			if (record) {
				DatosComisiones datos = new DatosComisiones();
				datos.setCodigoBaseComision(f.getCodigoBaseComision());
				datos.setCodigoCanal(f.getCodigoCanal());
				datos.setCodigoComision(f.getCodigoComision());
				datos.setCodigoEstado(f.getCodigoEstado());
				datos.setCodigoMarca(f.getCodigoMarca());
				datos.setCodigoPais(mPantallaPrincipalBean.getCurrentUser()
						.getCodigoPais());
				datos.setCodigoTipoComisionista(f.getCodigoTipoComisionista());
				datos.setDescripcion(f.getDescripcion());
				datos.setNombreUsuario(usuario.getLogin());
				datos.setIndicadorComisionEscalonada(Integer.parseInt(f
						.getIndicadorComisionEscalonada()));
				datos.setIndicadorConsideraSD(Integer.parseInt(f
						.getIndicadorConsideraSD()));
				datos.setIndicadorDctoImpuesto(Integer.parseInt(f
						.getIndicadorDctoImpuesto()));
				datos.setIndicadorDsctoReclamoFacturacion(Integer.parseInt(f
						.getIndicadorDsctoReclamoFacturacion()));
				datos.setIndicadorConsiderarCronograma(Integer.parseInt(f
						.getIndicadorConsiderarCronograma()));
				datos.setMontoFijoComision(f.getMontoFijoComision());
				datos.setComisionRetirada(f.getPorcentajeComisionRetirada());
				datos.setPorcentajeVentaConsultoras(f
						.getPorcentajeVentaConsultoras());
				datos.setPorcentajeComisionRetail(f
						.getPorcentajeComisionRetail());
				datos.setNroDiasRecuperacion(f
						.getComiEscalonadaNroDiasRecuperacion() + "");

				Map criteria = new HashMap();
				criteria.put("codigoMarca", f.getCodigoMarca());
				criteria.put("codigoPais", mPantallaPrincipalBean
						.getCurrentUser().getCodigoPais());
				criteria.put("codigoCanal", f.getCodigoCanal());
				criteria.put("codigoComision", f.getCodigoComision());
				criteria.put("indicadorComisionEscalonada",
						f.getIndicadorComisionEscalonada());

				if (!f.isNewRecord()) {
					if (log.isDebugEnabled()) {
						log.debug("Iniciando para modificar Comisiones");
					}
					datos.setOidComision(f.getOidComision());
					service.updateComisiones(datos, this.comComisionDetalList,
							this.datosCOMComisionesList,
							comisionComisionEscalonadaList,
							this.datosCOMComisionesClasificacionList, criteria);

				} else {
					if (log.isDebugEnabled()) {
						log.debug("Iniciando para registrar Comisiones");
					}
					service.insertComisiones(datos, this.comComisionDetalList,
							this.datosCOMComisionesList,
							comisionComisionEscalonadaList,
							this.datosCOMComisionesClasificacionList, criteria);
				}
				this.setIndicadorModificar(false);
			}
			f.setIndicadorComisionEscalonada(Constants.NUMERO_CERO);
			f.setIndicadorComisionEscalonadaBoolean(false);
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();

			this.addError("Error: ", this.getResourceMessage("errors.detail",
					new String[] { error }));

			this.setIndicadorModificar(false);
		}

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoCOMComisionesCobranzaForm f = new MantenimientoCOMComisionesCobranzaForm();
		if (!this.accion.equals(this.ACCION_NUEVO)) {

			if (this.accion.equals(this.ACCION_MODIFICAR)) {
				indicadorModificaCombo = Constants.NUMERO_UNO;

				// Cargar datos
				cargarDatos(f);
				//

				f.setEditable(true);
				this.getRegionesZonasSeleccionadas(this.comComisionDetalList);
				this.mostrarBotonSave = true;
			} else if (this.accion.equals(this.ACCION_CONSULTAR)) {
				cargarDatos(f);
				f.setEditable(false);
				this.mostrarBotonSave = false;
			}

			f.setNewRecord(false);
		}
		//this.mantenimientoComisionesTabDatosComisionesDisabled=true;
		return f;

	}

	/**
	 * 
	 */
	private void cargarDatos(MantenimientoCOMComisionesCobranzaForm f) {
		Map seleccionado = (Map) this.beanRegistroSeleccionado;

		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		f.setRegionesSelectedItems(null);
		f.setDatosSelectedItems(null);
		f.setEscalonadaSelectedItems(null);
		f.setEscalonadaSelectedItems2(null);
		f.setEscalonadaSelectedItems3(null);

		this.regionSeleccionada = null;
		this.comisionSeleccionada = null;
		this.comisionEscalonadaSeleccionada = null;
		this.comisionEscalonadaSeleccionada2 = null;
		this.comisionEscalonadaSeleccionada3 = null;
		this.comisionClasificacionSeleccionada = null;

		// Comision Escalonada

		if (StringUtils.equals(indicadorModificaCombo, Constants.NUMERO_CERO)) {
			// No se puede Modificar - CONSULTAR
			f.setOcultarSubGerenciaRegiones(true);
			f.setMostrarCheckEscalonada(false);
		} else {
			// Se puede modificar - MODIFICAR
			f.setOcultarSubGerenciaRegiones(false);
			f.setMostrarCheckEscalonada(true);
		}

		String id = MapUtils.getString(seleccionado, "oidComision");

		log.debug("mi ID es: " + id);

		Map criteria = new HashMap();
		criteria.put("oidComision", id);
		DatosComisiones comision = service
				.getDatosComisionRecuperacion(criteria);
		List comisionRegionSubGerenciaList = service
				.getDatosComisionesZonasSubGerencia(criteria);

		criteria.put("codigoComision", comision.getCodigoComision());

		List comisionClasificacionList = service
				.getClasificacionByCodigoComisionList(criteria);
		try {
			this.datosCOMComisionesList = null;
			this.datosCOMComisionesEscalonadasList = null;
			this.datosCOMComisionesEscalonadasList2 = null;
			this.datosCOMComisionesEscalonadasList3 = null;
			this.comComisionDetalList = null;
			this.datosCOMComisionesClasificacionList = null;

			String valorSubGerencia = this.comCodigoSubGerencia;

			// Paso de los valores de Comision
			f.setOidComision(comision.getOidComision());
			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			f.setCodigoComision(comision.getCodigoComision());
			f.setDescripcion(comision.getDescripcion());
			f.setCodigoSubGerencia(valorSubGerencia);
			f.setEstadoComision(comision.getEstadoComision());
			if (!StringUtils.isBlank(comision.getCodigoTipoComisionista())) {
				f.setCodigoTipoComisionista(comision
						.getCodigoTipoComisionista());
			}
			f.setIndicadorComisionEscalonada(comision
					.getIndicadorComisionEscalonada() + "");
			f.setIndicadorConsideraSD(comision.getIndicadorConsideraSD() + "");
			f.setIndicadorDctoImpuesto(comision.getIndicadorDctoImpuesto() + "");
			f.setIndicadorDsctoReclamoFacturacion(comision
					.getIndicadorDsctoReclamoFacturacion() + "");
			f.setIndicadorConsiderarCronograma(comision
					.getIndicadorConsiderarCronograma() + "");
			f.setCodigoBaseComision(comision.getCodigoBaseComision());

			f.setPorcentajeComisionRetirada(null);
			f.setPorcentajeVentaConsultoras(null);
			f.setPorcentajeComisionRetail(comision
					.getPorcentajeComisionRetail());
			f.setComiEscalonadaNroDiasRecuperacion(null);

			this.comComisionDetalList = comisionRegionSubGerenciaList;

			List comisionCalculoList = service.getDatosComisiones(criteria);
			for (int i = 0; i < comisionCalculoList.size(); i++) {
				DatosComisionCalculo p = (DatosComisionCalculo) comisionCalculoList
						.get(i);
				p.setContador(i + 1);
				comisionCalculoList.set(i, p);
			}

			this.datosCOMComisionesList = comisionCalculoList;

			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_01)
					|| StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_02)) {

				// Se llena la lista 1 - Comision Escalonada
				List comisionEscalonadaList = service
						.getComisionEscalonada(criteria);
				DatosComisionEscalonada p = new DatosComisionEscalonada();
				for (int i = 0; i < comisionEscalonadaList.size(); i++) {
					p = (DatosComisionEscalonada) comisionEscalonadaList.get(i);
					// p.setContador(i+1); //Validar que acepte los 3 tipos de
					// lista
					p.setNroEscala1(p.getNroTramo());
					comisionEscalonadaList.set(i, p);
				}
				f.setComiEscalonadaNroDiasRecuperacion(p
						.getNroDiasRecuperacion());
				this.datosCOMComisionesEscalonadasList = comisionEscalonadaList;

			} else if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_03)) {

				// Se llena la lista 2- Comision Escalonada
				List comisionEscalonadaList = service
						.getComisionEscalonada(criteria);
				for (int i = 0; i < comisionEscalonadaList.size(); i++) {
					DatosComisionEscalonada p = (DatosComisionEscalonada) comisionEscalonadaList
							.get(i);
					// p.setContador(i+1); //Validar que acepte los 3 tipos de
					// lista
					p.setNroEscala2(p.getNroTramo());
					p.setValPorcInicial(p.getValPorcRecuperacionInicial());
					p.setValPorcFinal(p.getValPorcRecuperacionFinal());
					comisionEscalonadaList.set(i, p);
				}
				f.setMontoFijoComision(comision.getMontoFijoComision());
				this.datosCOMComisionesEscalonadasList2 = comisionEscalonadaList;

			} else if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_04)) {

				// Se llena la lista 3 - Comision Escalonada
				List comisionEscalonadaList = service
						.getComisionEscalonada(criteria);
				DatosComisionEscalonada p = new DatosComisionEscalonada();
				for (int i = 0; i < comisionEscalonadaList.size(); i++) {
					p = (DatosComisionEscalonada) comisionEscalonadaList.get(i);
					// p.setContador(i+1); //Validar que acepte los 3 tipos de
					// lista
					p.setNroEscala3(p.getNroTramo());
					p.setValPorcInicialRecuperacion(p
							.getValPorcRecuperacionInicial());
					p.setValPorcFinalRecuperacion(p
							.getValPorcRecuperacionFinal());
					comisionEscalonadaList.set(i, p);
				}
				f.setComiEscalonadaNroDiasRecuperacion(p
						.getNroDiasRecuperacion());
				f.setPorcentajeComisionRetirada(comision.getComisionRetirada());
				f.setPorcentajeVentaConsultoras(comision
						.getPorcentajeVentaConsultoras());

				this.datosCOMComisionesEscalonadasList3 = comisionEscalonadaList;
				this.datosCOMComisionesClasificacionList = comisionClasificacionList;
			}

			AjaxService aSvc = (AjaxService) getBean("ajaxService");

			this.comBaseComisionList = null;

			this.comBaseComisionList = aSvc.getBasesComision(f
					.getCodigoTipoComisionista());

			f.setRegionesSelectedItems(null);
			f.setDatosSelectedItems(null);
			f.setEscalonadaSelectedItems(null);
			indicadorModificaCombo = Constants.NUMERO_CERO;

			// Seteo de Tipo Base Comision
			f.setIndicadorBaseComision(true);
			// *******************************

			// Datos Comisión
			f.setComisionNroTramo(null);
			f.setComisionNroDiasComision(null);
			f.setComisionValPorcRecuperacion("");
			f.setComisionValPorcComision("");

			// Segunda Lista Comision Escalonada
			f.setComiEscalonadaValPorcInicial("");
			f.setComiEscalonadaValPorcFinal("");
			f.setComiEscalonadaPorcComision2("");
			// Lista 3
			f.setComiEscalonadaValPorcInicialVenta("");
			f.setComiEscalonadaValPorcFinalVenta("");
			f.setComiEscalonadaValPorcInicialRecuperacion("");
			f.setComiEscalonadaValPorcFinalRecuperacion("");
			f.setComiEscalonadaPorcComision3("");
			this.siccZonaList = new LabelValue[] {};

			this.getRegionesZonasSeleccionadas(null);

			f.setIndicadorComisionEscalonadaBoolean(StringUtils.equals(
					f.getIndicadorComisionEscalonada(), Constants.NUMERO_UNO) ? true
					: false);
			f.setIndicadorConsiderarCronogramaBoolean(StringUtils.equals(
					f.getIndicadorConsiderarCronograma(), Constants.NUMERO_UNO) ? true
					: false);
			f.setIndicadorConsideraSDBoolean(StringUtils.equals(
					f.getIndicadorConsideraSD(), Constants.NUMERO_UNO) ? true
					: false);
			f.setIndicadorDsctoReclamoFacturacionBoolean(StringUtils.equals(
					f.getIndicadorDsctoReclamoFacturacion(),
					Constants.NUMERO_UNO) ? true : false);
			f.setIndicadorDctoImpuestoBoolean(StringUtils.equals(
					f.getIndicadorDctoImpuesto(), Constants.NUMERO_UNO) ? true
					: false);

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();

			this.addError("Error: ", this.getResourceMessage("errors.detail",
					new String[] { error }));

			this.setIndicadorModificar(false);
		}

		this.mostrarCampos(f);
		this.ocultarDivEscalonada(f);
		this.getIndicadorEscalonada(f);
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setViewAttributes - MantenimientoCOMComisionesCobranzaSearchAction");
		}

		MantenimientoCOMComisionesCobranzaSearchForm f = (MantenimientoCOMComisionesCobranzaSearchForm) this.formBusqueda;
		f.setCodigoPais(mPantallaPrincipalBean.getCurrentUser().getCodigoPais());

		loadCombos();

		GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais parametroPais = new ParametroPais();

		parametroPais.setCodigoPais(mPantallaPrincipalBean.getCurrentUser()
				.getCodigoPais());
		parametroPais.setCodigoSistema(Constants.COM_CODIGO_SISTEMA);
		parametroPais
				.setCodigoParametro(Constants.COM_CODIGO_PARAMETRO_TIPO_PROCESO_COMISION);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);

		List parametros = genericoService.getParametrosPais(parametroPais);

		this.indicadorTipoProcesoComision = Constants.NUMERO_CERO;

		if (parametros != null && parametros.size() > 0) {
			ParametroPais p = (ParametroPais) parametros.get(0);
			this.indicadorTipoProcesoComision = p.getValorParametro();
		}

		this.salirGrabarPantallaPadre = true;
		this.invocarFindLuegoGrabar = true;
		setMantenimientoComisionesTabDatosComisionesDisabled(false);

	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCOMComisionesCobranzaForm sistemaForm = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;
		boolean isNew = sistemaForm.isNewRecord();
		if (isNew) {
			return "mantenimientoCOMComisionesCobranzaForm.add";
		} else {
			return "mantenimientoCOMComisionesCobranzaForm.update";
		}
	}

	private void loadCombos() {
		log.debug("Loading Combos.");

		String codigoPais = mPantallaPrincipalBean.getCurrentUser()
				.getCodigoPais();

		ProcesoCOMCalculoCalificacionTramoService tramoService = (ProcesoCOMCalculoCalificacionTramoService) getBean("spusicc.procesoCOMCalculoCalificacionTramoService");
		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		this.comTipoComisionistaList = tramoService
				.getTiposComisionistas(codigoPais);
		this.comBaseComisionList = new LabelValue[] {};

		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		LabelValue[] listaSubGerencia = aSvc.getSubGerenciaxPaisMarcaCanal(
				codigoPais, Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);

		// captura de Subgerencia
		this.comCodigoSubGerencia = listaSubGerencia[0].getValue() + "_"
				+ listaSubGerencia[0].getLabel();
		this.siccRegionList = aSvc.getRegionByPaisMarcaCanalSubGerencia(
				codigoPais, Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT,
				comCodigoSubGerencia.split("_")[0].toString());
		this.comTipoClasificacionList = service.getTipoClasificacionList();
		this.comClasificacionList = new LabelValue[] {};
	}

	/**
	 * 
	 * @param val
	 */
	public void tipoComisionistaChange(ValueChangeEvent val) {
		log.debug("tipoComisionistaChange");

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;
		String valor = (String) val.getNewValue();
		loadBaseComision(valor);
		mostrarCampos(f);
		limpiarChecks(f);
		ocultarDivEscalonada(f);
		getValidaPestanhasComision(f);
		getIndicadorEscalonada(f);
	}

	/**
	 * 
	 * @param val
	 */
	public void codigoBaseComisionChange(ValueChangeEvent val) {
		log.debug("codigoBaseComisionChange");

		String valor = (String) val.getNewValue();

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;
		f.setCodigoBaseComision(valor);

		mostrarCampos(f);
		limpiarChecks(f);
		ocultarDivEscalonada(f);
		getValidaPestanhasComision(f);
		getIndicadorEscalonada(f);
	}

	/**
	 * 
	 * @param val
	 */
	public void tipoClasificacionChange(ValueChangeEvent val) {
		log.debug("tipoClasificacionChange");

		String valor = (String) val.getNewValue();
		if (valor.trim().length() > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.comClasificacionList = aSvc.getClasificacionComision(valor);
		}

		if (this.comClasificacionList == null) {
			this.comClasificacionList = new LabelValue[] {};
		}

	}

	/**
	 * 
	 * @param val
	 */
	public void indicadorComisionEscalonadaChange(ValueChangeEvent val) {

		String valor = val.getNewValue().toString();

		log.debug("indicadorComisionEscalonadaChange: valor = " + valor);

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		if (StringUtils.equalsIgnoreCase(valor, "true")) {
			f.setIndicadorComisionEscalonada(Constants.NUMERO_UNO);
			f.setIndicadorComisionEscalonadaBoolean(true);
		} else {
			f.setIndicadorComisionEscalonada(Constants.NUMERO_CERO);
			f.setIndicadorComisionEscalonadaBoolean(false);
		}

		this.mostrarCampos(f);
		this.ocultarDivEscalonada(f);
		this.getValidaPestanhasComision(f);
		this.getIndicadorEscalonada(f);
	}

	/**
	 * 
	 * @param val
	 */
	public void codigoRegionChange(ValueChangeEvent val) {
		log.debug("codigoRegionChange");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		String[] regiones = (String[]) val.getNewValue();

		if (regiones != null && regiones.length > 0) {
			this.siccZonaList = aSvc.getloadZonasDisponibles2(
					this.mPantallaPrincipalBean.getCountryCode(), regiones,
					this.siccZonaSeleccionadaList, "T");
		} else {
			this.siccZonaList = new LabelValue[1];
			this.siccZonaList[0] = new LabelValue("Todos", "");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		String[] todos = new String[1];
		todos[0] = "";
		f.setCodigoZona(todos);

	}

	/**
	 * 
	 * @param codigoTipoComision
	 */
	private void loadBaseComision(String codigoTipoComision) {
		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		if (codigoTipoComision.trim().length() > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.comBaseComisionList = aSvc
					.getBasesComision(codigoTipoComision);

			if (this.comBaseComisionList != null
					&& this.comBaseComisionList.length > 0) {
				f.setCodigoBaseComision(comBaseComisionList[0].getValue());
			} else {
				f.setCodigoBaseComision(null);
			}
		}

		if (this.comBaseComisionList == null) {
			this.comBaseComisionList = new LabelValue[] {};
		}
	}

	/**
	 * 
	 */
	private void mostrarCampos(MantenimientoCOMComisionesCobranzaForm f) {
		this.datosRegiTabDisabled = false;

		if (f.isEditable()) {
			String indicador = this.indicadorTipoProcesoComision;

			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_02)) {
				f.setIndicadorComisionEscalonadaDisabled(false);
			} else {
				f.setIndicadorComisionEscalonada(Constants.NUMERO_CERO);
				f.setIndicadorComisionEscalonadaBoolean(false);
				f.setIndicadorComisionEscalonadaDisabled(true);
			}

			if ((StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_01)
					|| StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_02)
					|| StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_04) || StringUtils
						.equals(f.getCodigoBaseComision(),
								Constants.CODIGO_BASE_COMISION_07))
					&& StringUtils.equals(indicador, Constants.NUMERO_UNO)) {
				f.setIndicadorConsiderarCronogramaDisabled(false);
				f.setIndicadorConsideraSDDisabled(false);
				f.setIndicadorDsctoReclamoFacturacionDisabled(false);
				f.setIndicadorDctoImpuestoDisabled(false);
			} else {
				f.setIndicadorConsiderarCronogramaDisabled(true);
				f.setIndicadorConsideraSDDisabled(true);
				f.setIndicadorDsctoReclamoFacturacionDisabled(true);
				f.setIndicadorDctoImpuestoDisabled(true);
			}

			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_01)
					|| StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_04)) {
				f.setComiEscalonadaNroDiasRecuperacionDisabled(false);

			} else if ((StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_02) || StringUtils.equals(
					f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_07))
					&& StringUtils.equals(f.getIndicadorComisionEscalonada(),
							Constants.NUMERO_UNO)) {
				f.setComiEscalonadaNroDiasRecuperacionDisabled(false);
			} else {
				f.setComiEscalonadaNroDiasRecuperacionDisabled(true);
				f.setComiEscalonadaNroDiasRecuperacion(null);
				f.setIndicadorComisionEscalonada(Constants.NUMERO_CERO);
				f.setIndicadorComisionEscalonadaBoolean(false);
			}

			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_03)) {
				f.setMontoFijoComisionDisabled(false);
			} else {
				f.setMontoFijoComisionDisabled(true);
				f.setMontoFijoComision("");
			}

			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_04)) {
				f.setPorcentajeVentaConsultorasDisabled(false);
			} else {
				f.setPorcentajeVentaConsultorasDisabled(true);
			}

		}

		if (StringUtils.equals(f.getCodigoBaseComision(),
				Constants.CODIGO_BASE_COMISION_06)) {
			f.setPorcentajeComisionRetailDisabled(false);
		} else {
			f.setPorcentajeComisionRetailDisabled(true);
		}

	}

	/**
	 * 
	 */
	private void limpiarChecks(MantenimientoCOMComisionesCobranzaForm f) {
		f.setIndicadorComisionEscalonada(Constants.NUMERO_CERO);
		f.setIndicadorConsiderarCronograma(Constants.NUMERO_CERO);
		f.setIndicadorConsideraSD(Constants.NUMERO_CERO);
		f.setIndicadorDsctoReclamoFacturacion(Constants.NUMERO_CERO);
		f.setIndicadorDctoImpuesto(Constants.NUMERO_CERO);

		f.setIndicadorComisionEscalonadaBoolean(false);
		f.setIndicadorConsiderarCronogramaBoolean(false);
		f.setIndicadorConsideraSDBoolean(false);
		f.setIndicadorDsctoReclamoFacturacionBoolean(false);
		f.setIndicadorDctoImpuestoBoolean(false);
	}

	/**
	 * 
	 */
	private void ocultarDivEscalonada(MantenimientoCOMComisionesCobranzaForm f) {
		log.debug("f.getCodigoBaseComision(): " + f.getCodigoBaseComision());

		if (StringUtils.isNotBlank(f.getCodigoBaseComision())) {
			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_01)
					|| StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_02)) {
				displayDiv3 = true;
				displayDiv6 = true;
				displayDiv4 = false;
				displayDiv7 = false;
				displayDiv5 = false;
				displayDiv8 = false;
				displayDiv10 = false;
			} else if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_03)) {
				displayDiv3 = false;
				displayDiv6 = false;
				displayDiv4 = true;
				displayDiv7 = true;
				displayDiv5 = false;
				displayDiv8 = false;
				displayDiv10 = false;
			} else if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_04)) {
				displayDiv3 = false;
				displayDiv6 = false;
				displayDiv4 = false;
				displayDiv7 = false;
				displayDiv5 = true;
				displayDiv8 = true;
				displayDiv10 = true;
			} else {
				displayDiv3 = true;
				displayDiv6 = true;
				displayDiv4 = false;
				displayDiv7 = false;
				displayDiv5 = false;
				displayDiv8 = false;
				displayDiv10 = false;
			}
		} else {
			// Si el codigo de Comision esta en vacio, mostrar por defecto el
			// primer formulario
			displayDiv3 = true;
			displayDiv6 = true;
			displayDiv4 = false;
			displayDiv7 = false;
			displayDiv5 = false;
			displayDiv8 = false;
			displayDiv10 = false;
		}
	}

	/**
	 * 
	 * @param f
	 */
	private void getValidaPestanhasComision(
			MantenimientoCOMComisionesCobranzaForm f) {
		log.debug("f.getCodigoBaseComision(): " + f.getCodigoBaseComision());

		if (StringUtils.equals(f.getCodigoBaseComision(),
				Constants.CODIGO_BASE_COMISION_07)) {
			this.mantenimientoComisionesTabActivo = Constants.MANTENIMIENTO_COMISIONES_TAB_COMISION;
		} else {
			this.mantenimientoComisionesTabActivo = Constants.MANTENIMIENTO_COMISIONES_TAB_REGIONES;
		}
	}

	/**
	 * 
	 */
	private void getIndicadorEscalonada(MantenimientoCOMComisionesCobranzaForm f) {
		log.debug("f.getCodigoBaseComision(): " + f.getCodigoBaseComision());
		log.debug("f.getIndicadorComisionEscalonada(): "
				+ f.getIndicadorComisionEscalonada());

		if (StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_04)) {
			this.mantenimientoComisionesTabDatosClasificacionDisabled = false;
			mantenimientoComisionesTabActivo=Constants.MANTENIMIENTO_COMISIONES_TAB_CLASIFICACION;
		} else {
			this.mantenimientoComisionesTabDatosClasificacionDisabled = true;
		}
		
		if (StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_01) 
				|| StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_03)
				|| StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_04)
				|| (StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) && StringUtils.equals(f.getIndicadorComisionEscalonada(), Constants.NUMERO_UNO))) {
			this.mantenimientoComisionesTabDatosEscalonadaDisabled = false;
			mantenimientoComisionesTabActivo=Constants.MANTENIMIENTO_COMISIONES_TAB_ESCALONADA;
		} else {
			this.mantenimientoComisionesTabDatosEscalonadaDisabled = true;
		}
		
		if (StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_05)
				|| StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_07)
				|| (StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) && StringUtils.equals(f.getIndicadorComisionEscalonada(), Constants.NUMERO_CERO))) {
			this.mantenimientoComisionesTabDatosComisionesDisabled = false;
			mantenimientoComisionesTabActivo=Constants.MANTENIMIENTO_COMISIONES_TAB_COMISION;
		} else {
			this.mantenimientoComisionesTabDatosComisionesDisabled = true;
		}
		
		if (StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_07)) {
			this.mantenimientoComisionesTabDatosRegionDisabled = true;
		} else {
			this.mantenimientoComisionesTabDatosRegionDisabled = false;
			mantenimientoComisionesTabActivo=Constants.MANTENIMIENTO_COMISIONES_TAB_REGIONES;
		}

		/*
		if (StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) && StringUtils.equals(f.getIndicadorComisionEscalonada(), Constants.NUMERO_CERO)) {
			this.mantenimientoComisionesTabDatosComisionesDisabled = false;
		} else {
			this.mantenimientoComisionesTabDatosComisionesDisabled = true;
		}
		
		if (StringUtils.equals(f.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_05)){
			this.mantenimientoComisionesTabDatosComisionesDisabled = false;
			this.mantenimientoComisionesTabDatosRegionDisabled = false;
		}
		*/
	}

	/**
	 * Obtiene el listado de regiones seleccionadas
	 * 
	 * @param detalList
	 * @return
	 */
	private void getRegionesZonasSeleccionadas(List detalList) {
		StringBuilder regiones = new StringBuilder();
		StringBuilder zonas = new StringBuilder();

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String codigoPais = mPantallaPrincipalBean.getCurrentUser()
				.getCodigoPais();

		this.siccRegionSeleccionadaList = "";
		this.siccZonaSeleccionadaList = "";

		if (detalList != null && detalList.size() > 0) {
			for (int i = 0; i < detalList.size(); i++) {
				DatosRegionesSubGerencia unidad = (DatosRegionesSubGerencia) detalList
						.get(i);
				if (StringUtils.isBlank(unidad.getCodigoZona()))
					regiones.append(unidad.getCodigoRegion()).append("|");
				else
					zonas.append(unidad.getCodigoZona()).append("|");
			}
		}

		this.siccRegionSeleccionadaList = regiones.toString();
		this.siccZonaSeleccionadaList = zonas.toString();

		this.siccRegionList = aSvc.getloadRegionesDisponibles2(codigoPais,
				this.siccRegionSeleccionadaList);
	}

	/**
	 * 
	 * @param event
	 */
	public void insertarRegionSubGerencia(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertarRegionSubGerencia' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;
		String mensaje = "";

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		MantenimientoCOMComisionService mantenimientoCOMComisionService = (MantenimientoCOMComisionService) this
				.getBean("spusicc.mantenimientoCOMComisionService");

		List comiDetalList = this.comComisionDetalList;
		int salida = 0;

		String valorSubGerencia = comCodigoSubGerencia;
		f.setCodigoSubGerencia(valorSubGerencia);
		String[] codigoZona = f.getCodigoZona();
		String[] codigoRegion = f.getCodigoRegion();

		// Validaciones de pantalla
		if (f.getFechaAntiguedadDesdeD() != null
				&& f.getFechaAntiguedadHastaD() != null) {
			int c = f.getFechaAntiguedadDesdeD().compareTo(
					f.getFechaAntiguedadHastaD());
			if (c > 0) {
				mensaje = this
						.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.error.fechas");
				this.addError("Error: ", mensaje);
				return;
			}
		}

		if (f.getCodigoRegion() == null || f.getCodigoRegion().length == 0) {
			mensaje = this
					.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.error.region");
			this.addError("Error: ", mensaje);
			return;
		}
		//

		for (int w = 0; w < codigoRegion.length; w++) {

			if (!this.isIndicadorModificar()) {
				// Agregar
				if (comiDetalList == null || comiDetalList.size() == 0) {
					comiDetalList = new ArrayList();
					salida = 1;
				}
				boolean verificarZonas = this.findTodos(f.getCodigoZona());

				// Verificar que no se repitan las regiones (siempre y cuando NO
				// se haya seleccionado Zonas)
				salida = 1;
				if (verificarZonas)
					for (int i = 0; i < comiDetalList.size(); i++) {
						DatosRegionesSubGerencia p = new DatosRegionesSubGerencia();
						p = (DatosRegionesSubGerencia) comiDetalList.get(i);
						if (StringUtils.equals(codigoRegion[w],
								p.getCodigoRegion())) {
							mensaje = this
									.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.codigoRegion.repetido");
							salida = 0;
							break;
						}
					}
				else
					// Verificar que no se repitan Zonas
					for (int i = 0; i < comiDetalList.size(); i++) {
						DatosRegionesSubGerencia p = new DatosRegionesSubGerencia();
						p = (DatosRegionesSubGerencia) comiDetalList.get(i);
						for (int j = 0; j < codigoZona.length; j++) {
							if (StringUtils.isNotBlank(p.getCodigoZona())) {
								if (StringUtils.equals(codigoZona[j],
										p.getCodigoZona())) {
									mensaje = this
											.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.codigoZona.repetido");
									salida = 0;
									break;
								}
								if (salida == 0)
									break;
							} else {
								if (StringUtils.equals(codigoRegion[w],
										p.getCodigoRegion())) {
									mensaje = this
											.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.codigoRegion.repetido");
									salida = 0;
									break;
								}
								if (salida == 0)
									break;
							}
						}
					}

				/* Si todo esta OK se inserta en la lista en Memoria */
				if (salida == 1) {

					LabelValue[] regiones = (LabelValue[]) aSvc
							.getRegionByPaisMarcaCanalSubGerencia(
									mPantallaPrincipalBean.getCurrentUser()
											.getCodigoPais(), f
											.getCodigoMarca(), f
											.getCodigoCanal(),
									f.getCodigoSubGerencia().split("_")[0]
											.toString());
					// Capturar descripcion de Region
					String descripcionRegion = new String();
					for (int i = 0; i < regiones.length; i++) {
						if (StringUtils.equals(codigoRegion[w],
								regiones[i].getValue())) {
							descripcionRegion = regiones[i].getLabel();
							break;
						}
					}

					if (verificarZonas) {

						DatosRegionesSubGerencia datosRegi = new DatosRegionesSubGerencia();

						// Captura del código de SubGerencia
						datosRegi.setDescripcionSubGerencia(f
								.getCodigoSubGerencia().split("_")[1]
								.toString());
						datosRegi.setCodigoSubGerencia(f.getCodigoSubGerencia()
								.split("_")[0].toString());
						datosRegi.setDescripcionRegion(descripcionRegion);
						if (f.getFechaAntiguedadDesdeD() != null) {
							datosRegi.setFechaAntiguedadDesde(f
									.getFechaAntiguedadDesdeD());
							datosRegi.setStrfechaAntiguedadDesde(DateUtil
									.convertDateToString(f
											.getFechaAntiguedadDesdeD()));
						}
						if (f.getFechaAntiguedadHastaD() != null) {
							datosRegi.setFechaAntiguedadHasta(f
									.getFechaAntiguedadHastaD());
							datosRegi.setStrfechaAntiguedadHasta(DateUtil
									.convertDateToString(f
											.getFechaAntiguedadHastaD()));
						}
						datosRegi.setCodigoRegion(codigoRegion[w]);
						datosRegi.setCodigoZona(null);
						datosRegi.setDescripcionZona(null);
						comiDetalList.add(datosRegi);
					} else {
						for (int i = 0; i < codigoZona.length; i++) {
							DatosRegionesSubGerencia datosRegi = new DatosRegionesSubGerencia();

							// Captura del código de SubGerencia
							datosRegi.setDescripcionSubGerencia(f
									.getCodigoSubGerencia().split("_")[1]
									.toString());
							datosRegi.setCodigoSubGerencia(f
									.getCodigoSubGerencia().split("_")[0]
									.toString());
							datosRegi.setDescripcionRegion(descripcionRegion);
							if (f.getFechaAntiguedadDesdeD() != null) {
								datosRegi.setFechaAntiguedadDesde(f
										.getFechaAntiguedadDesdeD());
								datosRegi.setStrfechaAntiguedadDesde(DateUtil
										.convertDateToString(f
												.getFechaAntiguedadDesdeD()));
							}
							if (f.getFechaAntiguedadHastaD() != null) {
								datosRegi.setFechaAntiguedadHasta(f
										.getFechaAntiguedadHastaD());
								datosRegi.setStrfechaAntiguedadHasta(DateUtil
										.convertDateToString(f
												.getFechaAntiguedadHastaD()));
							}
							datosRegi.setCodigoRegion(codigoRegion[w]);
							datosRegi.setCodigoZona(codigoZona[i]);

							Zona zona = new Zona();
							zona.setCodigoPais(mPantallaPrincipalBean
									.getCurrentUser().getCodigoPais());
							zona.setCodigo(codigoZona[i]);
							zona = mantenimientoCOMComisionService
									.getZona(zona);
							datosRegi.setDescripcionZona(zona.getDescripcion());

							comiDetalList.add(datosRegi);
						}
					}
				}

			} else {

				// Verificar que no se repitan las regiones
				for (int i = 0; i < comiDetalList.size(); i++) {
					DatosRegionesSubGerencia p = new DatosRegionesSubGerencia();
					p = (DatosRegionesSubGerencia) comiDetalList.get(i);
					boolean validar = false;

					if (StringUtils.isNotBlank(f.getCodigoZonaModificar()))
						validar = StringUtils.equals(codigoRegion[w],
								p.getCodigoRegion())
								&& StringUtils.equals(
										f.getCodigoZonaModificar(),
										p.getCodigoZona());
					else
						validar = StringUtils.equals(codigoRegion[w],
								p.getCodigoRegion());

					if (validar) {

						if (f.getFechaAntiguedadDesdeD() != null) {
							p.setFechaAntiguedadDesde(f
									.getFechaAntiguedadDesdeD());
							p.setStrfechaAntiguedadDesde(DateUtil
									.convertDateToString(f
											.getFechaAntiguedadDesdeD()));
						}

						if (f.getFechaAntiguedadHastaD() != null) {
							p.setFechaAntiguedadHasta(f
									.getFechaAntiguedadHastaD());
							p.setStrfechaAntiguedadHasta(DateUtil
									.convertDateToString(f
											.getFechaAntiguedadHastaD()));
						}

						comiDetalList.set(i, p);
						break;
					}
				}

			}
		}

		this.comComisionDetalList = comiDetalList;

		f.setCodigoRegion(null);
		f.setCodigoZona(null);
		this.siccZonaList = null;
		f.setFechaAntiguedadDesdeD(null);
		f.setFechaAntiguedadHastaD(null);
		f.setOcultarSubGerenciaRegiones(false);
		this.setIndicadorModificar(false);
		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		this.getRegionesZonasSeleccionadas(comiDetalList);

		if (StringUtils.isNotBlank(mensaje)) {
			this.addError("Error: ", mensaje);
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void agregarComision(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregarComision' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		// Validaciones de pantalla
		if (f.getComisionNroDiasComision() == null
				|| f.getComisionNroDiasComision() <= 0) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.comisionNroDiasComision.validation"));
			return;
		}

		double porcentajeRecuperacion = this.getDoubleValue(
				f.getComisionValPorcRecuperacion(), 0);

		if (porcentajeRecuperacion < 1 || porcentajeRecuperacion >= 100) {
			if (!StringUtils.equals(f.getCodigoBaseComision(), "07")) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.comisionValPorcRecuperacion.validation"));
				return;
			} else {
				f.setComisionValPorcRecuperacion(Constants.NUMERO_CERO);
			}
		}

		double porcentajeComision = this.getDoubleValue(
				f.getComisionValPorcComision(), -1);

		if (porcentajeComision < 0) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.comisionValPorcComision.validation"));
			return;
		}
		//

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		if (!this.isIndicadorModificar()) {
			// INSERTAR
			int contador = 1;
			int tramo = 1;
			if (this.datosCOMComisionesList == null
					|| this.datosCOMComisionesList.size() == 0) {
				this.datosCOMComisionesList = new ArrayList();
			} else {
				// Valida que el nro días comisión sea > al del tramo anterior
				DatosComisionCalculo a = (DatosComisionCalculo) this.datosCOMComisionesList
						.get(this.datosCOMComisionesList.size() - 1);
				if (a.getNroTramo() != null) {
					contador = this.datosCOMComisionesList.size() + 1;
				}
				tramo = a.getNroTramo() + 1;
				log.debug("mi contador es: " + contador);

				// Valida que el nro días comisión sea > al del tramo anterior
				if (f.getComisionNroDiasComision() <= a.getNroDiasComision()) {
					this.setIndicadorModificar(false);
					this.addError(
							"Error: ",
							this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.validationNroDias"));
					return;
				}
			}

			DatosComisionCalculo bean = new DatosComisionCalculo();
			bean.setContador(contador);
			bean.setNroTramo(tramo);
			bean.setNroDiasComision(f.getComisionNroDiasComision());
			bean.setValPorcComision(Double.parseDouble(f
					.getComisionValPorcComision()));
			// bean.setValPorcComisionActividad(Double.parseDouble(f.getComisionValPorcComisionActividad()));
			bean.setValPorcRecuperacion(Double.parseDouble(f
					.getComisionValPorcRecuperacion()));
			this.datosCOMComisionesList.add(bean);
			log.debug(this.datosCOMComisionesList);
			log.debug("terminó el metodo");

		} else {
			// MODIFICAR
			// Valida que el nro días comisión sea > al del tramo anterior
			int diaAnterior = 0;
			int diaPosterior = 0;

			if (this.datosCOMComisionesList.size() - 2 >= 0) {
				if (f.getComisionNroTramo() - 2 >= 0) {
					DatosComisionCalculo dca = (DatosComisionCalculo) this.datosCOMComisionesList
							.get(f.getContador() - 2);
					diaAnterior = dca.getNroDiasComision();
				}
			}

			if (this.datosCOMComisionesList.size() >= f.getContador() + 1) {
				DatosComisionCalculo dcp = (DatosComisionCalculo) this.datosCOMComisionesList
						.get(f.getContador());
				diaPosterior = dcp.getNroDiasComision();
			} else
				diaPosterior = f.getComisionNroDiasComision() + 1;
			// DatosComisionCalculo dcc =
			// (DatosComisionCalculo)this.datosCOMComisionesList.get(f.getComisionNroTramo()
			// - 1);

			log.debug("el tramo elegido es: " + f.getComisionNroTramo());

			// Valida que el nro días comisión sea >= al del tramo anterior
			if (diaAnterior >= f.getComisionNroDiasComision()
					|| f.getComisionNroDiasComision() >= diaPosterior) {
				// this.setIndicadorModificar(false);
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.validationNroDias"));
				return;
			}

			for (int i = 0; i < this.datosCOMComisionesList.size(); i++) {
				DatosComisionCalculo a = (DatosComisionCalculo) this.datosCOMComisionesList
						.get(i);
				if (f.getContador() == a.getContador()) {
					a.setContador(f.getContador());
					a.setNroDiasComision(f.getComisionNroDiasComision());
					a.setNroTramo(f.getComisionNroTramo());
					a.setValPorcComision(Double.parseDouble(f
							.getComisionValPorcComision()));
					a.setValPorcRecuperacion(Double.parseDouble(f
							.getComisionValPorcRecuperacion()));
					this.datosCOMComisionesList.set(i, a);
					break;
				}
			}
		}
		this.setIndicadorModificar(false);
		f.setComisionNroTramo(null);
		f.setComisionNroDiasComision(null);
		// f.setComisionValPorcComisionActividad("");
		f.setComisionValPorcComision("");
		f.setComisionValPorcRecuperacion("");
		f.setDatosSelectedItems(null);
	}

	/**
	 * 
	 * @param event
	 */
	public void agregarComisionEscalonada(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregarComisionEscalonada' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		// Validaciones de pantalla

		String codigoBase = f.getCodigoBaseComision();

		if (StringUtils.equals(codigoBase, Constants.CODIGO_BASE_COMISION_02)
				|| StringUtils.equals(codigoBase,
						Constants.CODIGO_BASE_COMISION_01)) {
			// Primera lista comiEscalonadaPorcRecuperacionInicial
			double porcRecuperacionInicial = getDoubleValue(
					f.getComiEscalonadaPorcRecuperacionInicial(), 0);
			double porcRecuperacionFinal = getDoubleValue(
					f.getComiEscalonadaPorcRecuperacionFinal(), 0);

			if (porcRecuperacionInicial < 1) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOmComisionesCobranzaForm.comiEscalonadaPorcRecuperacionInicial.validation"));
				return;
			}
			if (porcRecuperacionFinal < 1) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOmComisionesCobranzaForm.comiEscalonadaPorcRecuperacionFinal.validation"));
				return;
			}

			if (porcRecuperacionInicial > porcRecuperacionFinal) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOmComisionesCobranzaForm.comiEscalonadaPorcRecuperacionFinal.mayor.Inicial.validation"));
				return;
			}

			if (StringUtils.isBlank(f.getComiEscalonadaPorcComision())) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOmComisionesCobranzaForm.comiEscalonadaPorcComision.validation"));
				return;
			}
			if (StringUtils.isBlank(f.getComiEscalonadaPorcComisionAdicional())) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOmComisionesCobranzaForm.comiEscalonadaPorcComisionAdicional.validation"));
				return;
			}

			if (porcRecuperacionFinal < porcRecuperacionInicial) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcRecupFinal.menor.porcRecupInicial"));
				return;
			}
		}

		if (StringUtils.equals(codigoBase, Constants.CODIGO_BASE_COMISION_03)) {
			// Segunda Lista
			double porcInicial = getDoubleValue(
					f.getComiEscalonadaValPorcInicial(), -1);
			double porcFinal = getDoubleValue(
					f.getComiEscalonadaValPorcFinal(), -1);
			double porcComision2 = getDoubleValue(
					f.getComiEscalonadaPorcComision2(), 0);

			if (porcInicial < 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcInicial.validation"));
				return;
			}

			if (porcFinal < 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcFinal.validation"));
				return;
			}

			if (porcComision2 <= 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcComision2.validation"));
				return;
			}

			if (porcFinal < porcInicial) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcInicial.menor.porcFinal.validation"));
				return;
			}
		}

		if (StringUtils.equals(codigoBase, Constants.CODIGO_BASE_COMISION_04)) {
			// Tercera Lista
			double porcInicialVenta = getDoubleValue(
					f.getComiEscalonadaValPorcInicialVenta(), -1);
			double porcFinalVenta = getDoubleValue(
					f.getComiEscalonadaValPorcFinalVenta(), -1);
			double porcInicialRecuperacion = getDoubleValue(
					f.getComiEscalonadaValPorcInicialRecuperacion(), -1);
			double porcFinalRecuperacion = getDoubleValue(
					f.getComiEscalonadaValPorcFinalRecuperacion(), -1);
			double porcComision3 = getDoubleValue(
					f.getComiEscalonadaPorcComision3(), -1);

			if (porcInicialVenta < 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcInicialVenta.validation"));
				return;
			}
			if (porcFinalVenta < 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcFinalVenta.validation"));
				return;
			}
			if (porcInicialRecuperacion < 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcInicialRecuperacion.validation"));
				return;
			}
			if (porcFinalRecuperacion < 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcFinalRecuperacion.validation"));
				return;
			}
			if (porcComision3 < 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcComision3.validation"));
				return;
			}

			if (porcFinalVenta < porcInicialVenta) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcInicialVenta.menor.porcFinalVenta.validation"));
				return;
			}

			if (porcFinalRecuperacion < porcInicialRecuperacion) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcInicialRecuperacion.menor.porcFinalRecuperacion.validation"));
				return;
			}
		}

		//

		int contador = 1;

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		// AGREGAR COMISION ESCALONADA
		if (!this.isIndicadorModificar()) {
			// Lista 1 de Comision Escalonada
			DatosComisionEscalonada bean = new DatosComisionEscalonada();
			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_01)
					|| StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_02)) {

				if (this.datosCOMComisionesEscalonadasList == null
						|| this.datosCOMComisionesEscalonadasList.size() == 0)
					this.datosCOMComisionesEscalonadasList = new ArrayList();

				if (this.datosCOMComisionesEscalonadasList != null
						&& this.datosCOMComisionesEscalonadasList.size() > 0) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList
							.get(this.datosCOMComisionesEscalonadasList.size() - 1);
					contador = a.getNroEscala1() + 1;
					log.debug("mi contador es: " + contador);

					if (a.getValPorcRecuperacionFinal() >= Double.parseDouble(f
							.getComiEscalonadaPorcRecuperacionInicial())) {
						// this.setIndicadorModificar(false);
						this.addError(
								"Error: ",
								this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcRecupFinal.mayor"));
						return;
					}
				}

				// bean.setContador(contador);
				bean.setNroEscala1(contador);
				bean.setNroDiasComision(f
						.getComiEscalonadaNroDiasRecuperacion()); // Se eliminó
																	// de la
																	// grilla
				bean.setNroDiasRecuperacion(f
						.getComiEscalonadaNroDiasRecuperacion()); // Se eliminó
																	// de la
																	// grilla
				bean.setNroTramo(contador);
				bean.setValPorcRecuperacionInicial(Double.parseDouble(f
						.getComiEscalonadaPorcRecuperacionInicial()));
				bean.setValPorcRecuperacionFinal(Double.parseDouble(f
						.getComiEscalonadaPorcRecuperacionFinal()));
				bean.setValPorcComision(Double.parseDouble(f
						.getComiEscalonadaPorcComision()));
				bean.setValPorcComisionAdicional(Double.parseDouble(f
						.getComiEscalonadaPorcComisionAdicional()));
				this.datosCOMComisionesEscalonadasList.add(bean);

				log.debug(this.datosCOMComisionesEscalonadasList.size());

			} else if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_03)) {

				if (this.datosCOMComisionesEscalonadasList2 == null
						|| this.datosCOMComisionesEscalonadasList2.size() == 0) {
					this.datosCOMComisionesEscalonadasList2 = new ArrayList();
				}

				if (this.datosCOMComisionesEscalonadasList2 != null
						&& this.datosCOMComisionesEscalonadasList2.size() > 0) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList2
							.get(this.datosCOMComisionesEscalonadasList2.size() - 1);

					contador = a.getNroEscala2() + 1;
					log.debug("mi contador es: " + contador);

					if (Double.parseDouble(f.getComiEscalonadaValPorcInicial()) <= a
							.getValPorcFinal()) {
						// this.setIndicadorModificar(false);
						this.addError(
								"Error: ",
								this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.porcInicial.actual.mayor.anterior"));
						return;
					}
				}

				// bean.setContador(contador);
				bean.setNroEscala2(contador);
				bean.setValPorcFinal(Double.parseDouble(f
						.getComiEscalonadaValPorcFinal()));
				bean.setValPorcInicial(Double.parseDouble(f
						.getComiEscalonadaValPorcInicial()));
				bean.setValPorcComision(Double.parseDouble(f
						.getComiEscalonadaPorcComision2()));
				this.datosCOMComisionesEscalonadasList2.add(bean);

				log.debug(this.datosCOMComisionesEscalonadasList2.size());

			} else if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_04)) {

				if (this.datosCOMComisionesEscalonadasList3 == null
						|| this.datosCOMComisionesEscalonadasList3.size() == 0) {
					this.datosCOMComisionesEscalonadasList3 = new ArrayList();
				}

				if (this.datosCOMComisionesEscalonadasList3 != null
						&& this.datosCOMComisionesEscalonadasList3.size() > 0) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList3
							.get(this.datosCOMComisionesEscalonadasList3.size() - 1);
					contador = a.getNroEscala3() + 1;
					log.debug("mi contador2 es: " + contador);

					if ((a.getValPorcInicialVenta() == Double.parseDouble(f
							.getComiEscalonadaValPorcInicialVenta()))
							&& (a.getValPorcFinalVenta() == Double
									.parseDouble(f
											.getComiEscalonadaValPorcFinalVenta()))) {
						// Se procede solamente a validar lo Valores de
						// Recuperacoin Inicial y Recuperacion Final

						if (Double.parseDouble(f
								.getComiEscalonadaValPorcInicialRecuperacion()) <= a
								.getValPorcFinalRecuperacion()) {
							// this.setIndicadorModificar(false);
							this.addError(
									"Error: ",
									this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.validationPorcentajeRecuperacion"));
							return;
						}
					}

					if ((a.getValPorcInicialRecuperacion() == Double
							.parseDouble(f
									.getComiEscalonadaValPorcInicialRecuperacion()))
							&& (a.getValPorcFinalRecuperacion() == Double
									.parseDouble(f
											.getComiEscalonadaValPorcFinalRecuperacion()))) {
						// Se procede solamente a validar lo Valores de
						// Recuperacoin Inicial y Recuperacion Final

						if (Double.parseDouble(f
								.getComiEscalonadaValPorcInicialVenta()) <= a
								.getValPorcFinalVenta()) {
							// this.setIndicadorModificar(false);
							this.addError(
									"Error: ",
									this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.validationPorcentajeRecuperacion"));
							return;
						}
					}

				}

				// bean.setContador(contador);
				bean.setNroEscala3(contador);
				bean.setNroDiasComision(f.getComiEscalonadaNroDiasComision());
				bean.setNroDiasRecuperacion(f
						.getComiEscalonadaNroDiasRecuperacion());
				bean.setValPorcInicialVenta(Double.parseDouble(f
						.getComiEscalonadaValPorcInicialVenta()));
				bean.setValPorcFinalVenta(Double.parseDouble(f
						.getComiEscalonadaValPorcFinalVenta()));
				bean.setValPorcInicialRecuperacion(Double.parseDouble(f
						.getComiEscalonadaValPorcInicialRecuperacion()));
				bean.setValPorcFinalRecuperacion(Double.parseDouble(f
						.getComiEscalonadaValPorcFinalRecuperacion()));
				bean.setValPorcComision(Double.parseDouble(f
						.getComiEscalonadaPorcComision3()));
				this.datosCOMComisionesEscalonadasList3.add(bean);

				log.debug(this.datosCOMComisionesEscalonadasList3.size());

			}

		}
		// MODIFICAR COMISION ESCALONADA
		else {

			if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_01)
					|| StringUtils.equals(f.getCodigoBaseComision(),
							Constants.CODIGO_BASE_COMISION_02)) {
				// PRIMERA LISTA
				if (!validarUpdateComisionEscalonadaCorrecta(
						this.datosCOMComisionesEscalonadasList,
						f.getCodigoBaseComision(), f.getNroEscala(),
						f.getComiEscalonadaPorcRecuperacionInicial(),
						f.getComiEscalonadaPorcRecuperacionFinal(), null)) {

					// this.setIndicadorModificar(false);
					this.addError(
							"Error: ",
							this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.validationPorcRecuperacion"));
					return;

				} else {
					for (int i = 0; i < this.datosCOMComisionesEscalonadasList
							.size(); i++) {
						DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList
								.get(i);
						if (f.getNroEscala() == a.getNroEscala1()) {
							// a.setNroEscala1(f.getNroEscala());
							a.setNroDiasComision(f
									.getComiEscalonadaNroDiasComision());// Se
																			// eliminó
																			// de
																			// la
																			// grilla
							a.setNroDiasRecuperacion(f
									.getComiEscalonadaNroDiasRecuperacion());// Se
																				// eliminó
																				// de
																				// la
																				// grilla
							a.setValPorcRecuperacionInicial(Double.parseDouble(f
									.getComiEscalonadaPorcRecuperacionInicial()));
							a.setValPorcRecuperacionFinal(Double.parseDouble(f
									.getComiEscalonadaPorcRecuperacionFinal()));
							a.setValPorcComision(Double.parseDouble(f
									.getComiEscalonadaPorcComision()));
							a.setValPorcComisionAdicional(Double.parseDouble(f
									.getComiEscalonadaPorcComisionAdicional()));
							this.datosCOMComisionesEscalonadasList.set(i, a);
							break;
						}
					}
				}
			} else if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_03)) {
				// SEGUNDA LISTA
				if (!validarUpdateComisionEscalonadaCorrecta(
						this.datosCOMComisionesEscalonadasList2,
						f.getCodigoBaseComision(), f.getNroEscala(),
						f.getComiEscalonadaValPorcInicial(),
						f.getComiEscalonadaValPorcFinal(), null)) {

					// this.setIndicadorModificar(false);
					this.addError(
							"Error: ",
							this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.validationPorcentajeInicialFinal"));
					return;
				} else {
					for (int i = 0; i < this.datosCOMComisionesEscalonadasList2
							.size(); i++) {
						DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList2
								.get(i);
						if (f.getNroEscala() == a.getNroEscala2()) {
							// a.setNroEscala2(f.getNroEscala());
							a.setValPorcFinal(Double.parseDouble(f
									.getComiEscalonadaValPorcFinal()));
							a.setValPorcInicial(Double.parseDouble(f
									.getComiEscalonadaValPorcInicial()));
							a.setValPorcComision(Double.parseDouble(f
									.getComiEscalonadaPorcComision2()));
							this.datosCOMComisionesEscalonadasList2.set(i, a);
							break;
						}
					}
				}

			} else if (StringUtils.equals(f.getCodigoBaseComision(),
					Constants.CODIGO_BASE_COMISION_04)) {

				// TERCERA LISTA
				/*
				 * o % inicial Venta Obligatorio, numérico >= 0 o % final Venta
				 * Obligatorio, numérico >= 0 o % inicial Recup Obligatorio,
				 * numérico >= 0 o % final Recup Obligatorio, numérico >= 0 o %
				 * Comisión : Obligatorio, numérico >= 0.
				 */

				boolean resultado = true;
				if (this.datosCOMComisionesEscalonadasList3 != null
						&& this.datosCOMComisionesEscalonadasList3.size() > 0) {

					DatosComisionEscalonada tramiSiguiente = new DatosComisionEscalonada();
					if (this.datosCOMComisionesEscalonadasList3.size() >= f
							.getNroEscala() + 1) {
						// SI se ha encontrado un tramo en el item seleccionado
						tramiSiguiente = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList3
								.get(f.getNroEscala());
					} else {
						tramiSiguiente = new DatosComisionEscalonada();
						tramiSiguiente
								.setValPorcInicialRecuperacion(Double.parseDouble(f
										.getComiEscalonadaValPorcFinalRecuperacion()) + 1);
					}

					// Verificar si tengo Porcentaje de
					// TODO
					if (this.datosCOMComisionesEscalonadasList3.size() - 2 >= 0) {

						if (f.getNroEscala() - 2 >= 0) {
							DatosComisionEscalonada tramoAnterior = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList3
									.get(f.getNroEscala() - 2);

							// Validar que sean iguales
							if ((tramoAnterior.getValPorcInicialRecuperacion() == Double
									.parseDouble(f
											.getComiEscalonadaValPorcInicialRecuperacion()))
									&& (tramoAnterior
											.getValPorcFinalRecuperacion() == Double
											.parseDouble(f
													.getComiEscalonadaValPorcFinalRecuperacion()))) {
								// Se procede solamente a validar lo Valores de
								// Recuperacoin Inicial y Recuperacion Final

								if (Double
										.parseDouble(f
												.getComiEscalonadaValPorcInicialVenta()) <= tramoAnterior
										.getValPorcFinalVenta()) {
									// this.setIndicadorModificar(false);
									this.addError(
											"Error: ",
											this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.validationPorcentajeRecuperacion"));
									return;
								}
							}

							if (Double.parseDouble(f
									.getComiEscalonadaValPorcInicialVenta()) == tramoAnterior
									.getValPorcInicialVenta()
									&& Double
											.parseDouble(f
													.getComiEscalonadaValPorcFinalVenta()) == tramoAnterior
											.getValPorcFinalVenta()) {

								if (Double
										.parseDouble(f
												.getComiEscalonadaValPorcInicialVenta()) != tramiSiguiente
										.getValPorcInicialVenta()
										&& Double
												.parseDouble(f
														.getComiEscalonadaValPorcFinalVenta()) != tramiSiguiente
												.getValPorcFinalVenta()) {
									tramiSiguiente
											.setValPorcInicialRecuperacion(Double.parseDouble(f
													.getComiEscalonadaValPorcFinalRecuperacion()) + 1);
								}

								if (Double
										.parseDouble(f
												.getComiEscalonadaValPorcInicialRecuperacion()) <= tramoAnterior
										.getValPorcFinalRecuperacion()
										|| Double
												.parseDouble(f
														.getComiEscalonadaValPorcFinalRecuperacion()) >= tramiSiguiente
												.getValPorcInicialRecuperacion()) {
									// this.setIndicadorModificar(false);
									this.addError(
											"Error: ",
											this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.validationPorcentajeRecuperacion"));
									return;
								}
							} else {
								// No son iguales, se validan los campos
								/*
								 * if
								 * (this.datosCOMComisionesEscalonadasList3.size
								 * () >= f.getNroEscala()+1) { if
								 * (Double.parseDouble
								 * (f.getComiEscalonadaValPorcInicialVenta()) !=
								 * tramiSiguiente.getValPorcInicialVenta() &&
								 * Double
								 * .parseDouble(f.getComiEscalonadaValPorcFinalVenta
								 * ()) != tramiSiguiente.getValPorcFinalVenta())
								 * {
								 * 
								 * //TERCERA LISTA resultado =
								 * validarUpdateComisionEscalonadaCorrecta
								 * (this.datosCOMComisionesEscalonadasList3,
								 * f.getCodigoBaseComision(), f.getNroEscala(),
								 * f.getComiEscalonadaValPorcInicialVenta(),
								 * f.getComiEscalonadaValPorcFinalVenta(),"0");
								 * if (!resultado) { this.addError("Error: ",
								 * this.getResourceMessage(
								 * "mantenimientoCOMComisionesCobranzaForm.validationPorcentajeVentas"
								 * )); return;
								 * 
								 * } }
								 * 
								 * }
								 */
							}
						}
					}
				}

				if (resultado) {
					for (int i = 0; i < this.datosCOMComisionesEscalonadasList3
							.size(); i++) {
						DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList3
								.get(i);
						if (f.getNroEscala() == a.getNroEscala3()) {
							// a.setNroEscala3(f.getNroEscala());
							a.setNroDiasComision(f
									.getComiEscalonadaNroDiasComision());
							a.setNroDiasRecuperacion(f
									.getComiEscalonadaNroDiasRecuperacion());
							a.setValPorcInicialVenta(Double.parseDouble(f
									.getComiEscalonadaValPorcInicialVenta()));
							a.setValPorcFinalVenta(Double.parseDouble(f
									.getComiEscalonadaValPorcFinalVenta()));
							a.setValPorcInicialRecuperacion(Double.parseDouble(f
									.getComiEscalonadaValPorcInicialRecuperacion()));
							a.setValPorcFinalRecuperacion(Double.parseDouble(f
									.getComiEscalonadaValPorcFinalRecuperacion()));
							a.setValPorcComision(Double.parseDouble(f
									.getComiEscalonadaPorcComision3()));
							this.datosCOMComisionesEscalonadasList3.set(i, a);
							break;
						}
					}
				}
			}
		}
		// Lista 1
		f.setComiEscalonadaPorcRecuperacionInicial("");
		f.setComiEscalonadaPorcRecuperacionFinal("");
		f.setComiEscalonadaPorcComision("");
		f.setComiEscalonadaPorcComisionAdicional("");
		f.setEscalonadaSelectedItems(null);
		f.setEscalonadaSelectedItems2(null);
		f.setEscalonadaSelectedItems3(null);
		// f.setIndicadorModificarNumeroDias("1");
		this.setIndicadorModificar(false);

		// Lista 2
		f.setComiEscalonadaValPorcFinal("");
		f.setComiEscalonadaValPorcInicial("");
		f.setComiEscalonadaPorcComision2("");

		// Lista 3
		f.setComiEscalonadaValPorcFinalRecuperacion("");
		f.setComiEscalonadaValPorcFinalVenta("");
		f.setComiEscalonadaValPorcInicialRecuperacion("");
		f.setComiEscalonadaValPorcInicialVenta("");
		f.setComiEscalonadaPorcComision3("");

		f.setIndicadorComisionEscalonada(Constants.NUMERO_CERO);
		f.setIndicadorComisionEscalonadaBoolean(true);

		log.debug("terminó el metodo");

	}

	/**
	 * 
	 * @param event
	 */
	public void agregarComisionClasificacion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'agregarComisionClasificacion' method");
		}

		MantenimientoCOMComisionService service = (MantenimientoCOMComisionService) getBean("spusicc.mantenimientoCOMComisionService");
		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;
		DatosComisionClasificacion clasificacion = new DatosComisionClasificacion();

		if (StringUtils.isBlank(f.getCodigoTipoClasificacion())) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.clasificacion.codigoTipo"));
			return;
		}
		if (StringUtils.isBlank(f.getCodigoClasificacion())) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.clasificacion.codigo"));
			return;
		}

		Map criteria = new HashMap();
		try {
			// Validar que no se repitan
			if (this.datosCOMComisionesClasificacionList != null
					&& this.datosCOMComisionesClasificacionList.size() > 0) {
				for (int i = 0; i < this.datosCOMComisionesClasificacionList
						.size(); i++) {
					DatosComisionClasificacion a = (DatosComisionClasificacion) this.datosCOMComisionesClasificacionList
							.get(i);
					if (StringUtils.equals(
							a.getOidListaClasificacion(),
							f.getCodigoTipoClasificacion() + "_"
									+ f.getCodigoClasificacion())) {
						f.setCodigoTipoClasificacion("");
						this.addError(
								"Error: ",
								this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.clasificacion.duplicadas"));
						return;
					}
				}
			}

			// Captura la descripcion del Tipo de Clasificacion
			if (this.comTipoClasificacionList != null
					&& this.comTipoClasificacionList.size() > 0) {
				for (int i = 0; i < this.comTipoClasificacionList.size(); i++) {
					Base t = new Base();
					t = (Base) this.comTipoClasificacionList.get(i);
					if (StringUtils.equals(f.getCodigoTipoClasificacion(),
							t.getCodigo())) {
						clasificacion.setOidTipoClasificacion(t.getCodigo());
						clasificacion.setCodigoTipoClasificacion(t
								.getDescripcion().substring(0, 2));
						clasificacion.setDescripcionTipoClasificacion(t
								.getDescripcion());
						break;
					}
				}
			}

			criteria.put("oidTipoClasificacion",
					clasificacion.getOidTipoClasificacion());
			List cboListaClasificacion = service
					.getClasificacionComisionList(criteria);

			if (cboListaClasificacion != null
					&& cboListaClasificacion.size() > 0) {
				for (int i = 0; i < cboListaClasificacion.size(); i++) {
					Base c = new Base();
					c = (Base) cboListaClasificacion.get(i);
					if (StringUtils.equals(f.getCodigoClasificacion(),
							c.getCodigo())) {
						clasificacion.setCodigoClasificacion(c.getCodigo());
						clasificacion.setDescripcionClasificacion(c
								.getDescripcion());
						break;
					}
				}
			} else {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.comisionClasificacion.lista.vacia"));
				return;
			}

			clasificacion.setOidListaClasificacion(clasificacion
					.getOidTipoClasificacion()
					+ "_"
					+ clasificacion.getCodigoClasificacion());

			this.datosCOMComisionesClasificacionList.add(clasificacion);

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail",
					new String[] { error }));
			return;
		}

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************
		f.setCodigoClasificacion("");
		f.setCodigoTipoClasificacion("");
	}

	/**
	 * Devuelve TRUE si la validacion salió correcta (SIN ERRORES) listaComision
	 * = Lista que se va a validar codigoBase = Código base que se encuentra en
	 * el formulario nroEscala = Contador y el index de la listaç
	 * valPorcentajeInicial = Porcentaje Inicial a Evaluar valPorcentajeFinal =
	 * Porcentaje Final a Evaluar indicadorComisionLista3 = indicador para
	 * capturar el valor Anterior (SOLO se utiliza cuando codigo Base = '04')
	 * indicadorComisionLista3 = '1'(ValPorcFinalRecuperacion)
	 * '0'(ValPorcFinalVenta)
	 **/
	private boolean validarUpdateComisionEscalonadaCorrecta(List listaComision,
			String codigoBase, int nroEscala, String valPorcentajeInicial,
			String valPorcentajeFinal, String indicadorComisionLista3) {
		double porcAnterior = 0;
		double porcPosterior = 0;
		boolean resultado = false;

		// Ingreso por primera vez
		if (listaComision.size() == 0 || listaComision == null) {
			return true;
		}

		// Valida que el % Recuperación Inicial sea mayor al porcentaje del
		// $Recuperación Final
		if (listaComision.size() - 2 >= 0) {
			if (nroEscala - 2 >= 0) {
				DatosComisionEscalonada dcea = (DatosComisionEscalonada) listaComision
						.get(nroEscala - 2);
				if (StringUtils.equals(codigoBase,
						Constants.CODIGO_BASE_COMISION_01)
						|| StringUtils.equals(codigoBase,
								Constants.CODIGO_BASE_COMISION_02)) {

					porcAnterior = dcea.getValPorcRecuperacionFinal();

				} else if (StringUtils.equals(codigoBase,
						Constants.CODIGO_BASE_COMISION_03)) {

					porcAnterior = dcea.getValPorcFinal();

				} else if (StringUtils.equals(codigoBase,
						Constants.CODIGO_BASE_COMISION_04)) {
					if (StringUtils.equals(indicadorComisionLista3, "1")) {
						porcAnterior = dcea.getValPorcFinalRecuperacion();
					} else {
						porcAnterior = dcea.getValPorcFinalVenta();
					}
				}

			}
		}

		if (listaComision.size() >= nroEscala + 1) {
			DatosComisionEscalonada dcep = (DatosComisionEscalonada) listaComision
					.get(nroEscala);
			if (StringUtils.equals(codigoBase,
					Constants.CODIGO_BASE_COMISION_01)
					|| StringUtils.equals(codigoBase,
							Constants.CODIGO_BASE_COMISION_02)) {

				porcPosterior = dcep.getValPorcRecuperacionInicial();

			} else if (StringUtils.equals(codigoBase,
					Constants.CODIGO_BASE_COMISION_03)) {

				porcPosterior = dcep.getValPorcInicial();

			} else if (StringUtils.equals(codigoBase,
					Constants.CODIGO_BASE_COMISION_04)) {

				if (StringUtils.equals(indicadorComisionLista3, "1")) {
					porcPosterior = dcep.getValPorcInicialRecuperacion();
				} else {
					porcPosterior = dcep.getValPorcInicialVenta();
				}
			}

		}

		log.debug("el tramo elegido es: " + nroEscala);

		double porcInicial = Double.parseDouble(valPorcentajeInicial);
		double porcFinal = Double.parseDouble(valPorcentajeFinal);

		if (porcAnterior == 0 && porcPosterior == 0)
			return true;

		if (!resultado) {
			// No existe un campo anterior
			if (porcPosterior == 0) {
				log.debug("No existe un campo anterior");
				if (porcAnterior < porcInicial) {
					return true;
				}
			} else {
				if (porcAnterior > porcInicial || porcFinal > porcPosterior) {
					log.debug("El porcentaje Inicial y Final no se encuentra en el rango del porcentaje Anterior y al Porcentaje posterior");
					resultado = false;
				} else {
					return true;
				}
			}
		}

		return resultado;
	}

	/**
	 * 
	 * @param strValue
	 * @return
	 */
	private double getDoubleValue(String strValue, double defaultValue) {
		double value = defaultValue;
		try {
			value = Double.parseDouble(strValue);
		} catch (Exception ex) {
		}

		return value;
	}

	/**
	 * 
	 * @param event
	 */
	public void borrarRegionesSubGerencia(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'borrarRegionesSubGerencia' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		if (this.comComisionDetalList == null
				|| comComisionDetalList.size() == 0) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.delete.validation"));
			return;
		}

		if (this.regionSeleccionada == null) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
			return;
		}

		DatosRegionesSubGerencia datosID = new DatosRegionesSubGerencia();
		this.setKeyDatosComisionUA(this.regionSeleccionada.getClaveRegistro(),
				datosID);

		for (int i = 0; i < this.comComisionDetalList.size(); i++) {
			DatosRegionesSubGerencia datos = (DatosRegionesSubGerencia) comComisionDetalList
					.get(i);
			boolean validar = false;

			if (StringUtils.isNotBlank(datosID.getCodigoZona()))
				validar = StringUtils.equals(datosID.getCodigoRegion(),
						datos.getCodigoRegion())
						&& StringUtils.equals(datosID.getCodigoZona(),
								datos.getCodigoZona());
			else
				validar = StringUtils.equals(datosID.getCodigoRegion(),
						datos.getCodigoRegion());

			if (validar) {
				comComisionDetalList.remove(i);
				i = i - 1;
				break;
			}

		}

		if (comComisionDetalList.size() == 0) {
			comComisionDetalList = new ArrayList();
		}

		f.setRegionesSelectedItems(null);
		f.setCodigoRegion(null);
		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		this.regionSeleccionada = null;
		this.getRegionesZonasSeleccionadas(comComisionDetalList);

	}

	/**
	 * 
	 * @param event
	 */
	public void borrarComision(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'borrarComision' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		if (this.datosCOMComisionesList == null
				|| datosCOMComisionesList.size() == 0) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.delete.validation"));
			return;
		}

		if (this.comisionSeleccionada == null) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
			return;
		}

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		for (int i = 0; i < this.datosCOMComisionesList.size(); i++) {
			DatosComisionCalculo datos = (DatosComisionCalculo) this.datosCOMComisionesList
					.get(i);
			if (datos.getContador() == this.comisionSeleccionada.getContador()) {
				this.datosCOMComisionesList.remove(i);
				break;
			}
		}

		for (int i = 0; i < this.datosCOMComisionesList.size(); i++) {
			DatosComisionCalculo a = new DatosComisionCalculo();
			a = (DatosComisionCalculo) this.datosCOMComisionesList.get(i);
			a.setContador(i + 1);
			this.datosCOMComisionesList.set(i, a);
		}

		this.comisionSeleccionada = null;
	}

	/**
	 * 
	 * @param event
	 */
	public void borrarComisionEscalonada(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'borrarComisionEscalonada' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;
		DatosComisionEscalonada datos = new DatosComisionEscalonada();

		if (StringUtils.equals(f.getCodigoBaseComision(),
				Constants.CODIGO_BASE_COMISION_01)
				|| StringUtils.equals(f.getCodigoBaseComision(),
						Constants.CODIGO_BASE_COMISION_02)) {
			if (this.datosCOMComisionesEscalonadasList == null
					|| datosCOMComisionesEscalonadasList.size() == 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.delete.validation"));
				return;
			}

			if (this.comisionEscalonadaSeleccionada == null) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
				return;
			}

			// Cuerpo de eliminar Lista 1
			for (int i = 0; i < this.datosCOMComisionesEscalonadasList.size(); i++) {
				datos = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList
						.get(i);
				if (datos.getNroEscala1() == comisionEscalonadaSeleccionada
						.getNroEscala1()) {
					this.datosCOMComisionesEscalonadasList.remove(i);
					break;
				}
			}

			if (this.datosCOMComisionesEscalonadasList != null
					&& this.datosCOMComisionesEscalonadasList.size() > 0) {
				for (int i = 0; i < this.datosCOMComisionesEscalonadasList
						.size(); i++) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList
							.get(i);
					a.setNroEscala1(i + 1);
					this.datosCOMComisionesEscalonadasList.set(i, a);
				}
			}

		} else if (StringUtils.equals(f.getCodigoBaseComision(),
				Constants.CODIGO_BASE_COMISION_03)) {
			if (this.datosCOMComisionesEscalonadasList2 == null
					|| datosCOMComisionesEscalonadasList2.size() == 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.delete.validation"));
				return;
			}

			if (this.comisionEscalonadaSeleccionada2 == null) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
				return;
			}

			// Cuerpo de eliminar Lista 2
			for (int i = 0; i < datosCOMComisionesEscalonadasList2.size(); i++) {
				datos = (DatosComisionEscalonada) datosCOMComisionesEscalonadasList2
						.get(i);

				if (datos.getNroEscala2() == comisionEscalonadaSeleccionada2
						.getNroEscala2()) {
					datosCOMComisionesEscalonadasList2.remove(i);
					break;
				}
			}

			if (datosCOMComisionesEscalonadasList2 != null
					&& datosCOMComisionesEscalonadasList2.size() > 0) {
				for (int i = 0; i < datosCOMComisionesEscalonadasList2.size(); i++) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) datosCOMComisionesEscalonadasList2
							.get(i);
					a.setNroEscala2(i + 1);
					datosCOMComisionesEscalonadasList2.set(i, a);
				}
			}

		} else if (StringUtils.equals(f.getCodigoBaseComision(),
				Constants.CODIGO_BASE_COMISION_04)) {
			if (this.datosCOMComisionesEscalonadasList3 == null
					|| datosCOMComisionesEscalonadasList3.size() == 0) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.delete.validation"));
				return;
			}

			if (this.comisionEscalonadaSeleccionada3 == null) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
				return;
			}

			// Cuerpo de eliminar Lista 3
			for (int i = 0; i < this.datosCOMComisionesEscalonadasList3.size(); i++) {
				datos = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList3
						.get(i);

				if (datos.getNroEscala3() == comisionEscalonadaSeleccionada3
						.getNroEscala3()) {
					datosCOMComisionesEscalonadasList3.remove(i);
					break;
				}
			}

			if (this.datosCOMComisionesEscalonadasList3 != null
					&& this.datosCOMComisionesEscalonadasList3.size() > 0) {
				for (int i = 0; i < this.datosCOMComisionesEscalonadasList3
						.size(); i++) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList3
							.get(i);
					a.setNroEscala3(i + 1);
					this.datosCOMComisionesEscalonadasList3.set(i, a);
				}
			}
		}

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		f.setEscalonadaSelectedItems(null);
		f.setEscalonadaSelectedItems2(null);
		f.setEscalonadaSelectedItems3(null);

		this.comisionEscalonadaSeleccionada = null;
		this.comisionEscalonadaSeleccionada2 = null;
		this.comisionEscalonadaSeleccionada3 = null;
	}

	/**
	 * 
	 * @param event
	 */
	public void borrarComisionClasificacion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'borrarComisionClasificacion' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		try {
			if (this.comisionClasificacionSeleccionada == null) {
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
				return;
			} else {
				// Lista de la Grilla de la pestaña de 'Datos Clasificacion'
				for (int i = 0; i < this.datosCOMComisionesClasificacionList
						.size(); i++) {
					DatosComisionClasificacion datos = (DatosComisionClasificacion) this.datosCOMComisionesClasificacionList
							.get(i);
					if (StringUtils.equals(datos.getOidListaClasificacion(),
							this.comisionClasificacionSeleccionada
									.getOidListaClasificacion())) {
						this.datosCOMComisionesClasificacionList.remove(i);
						i = i - 1;
						break;
					}
				}
			}

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			this.addError("Error: ", this.getResourceMessage("errors.detail",
					new String[] { error }));
			return;
		}

		f.setClasificacionSelectedItems(null);

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		this.comisionClasificacionSeleccionada = null;
	}

	/**
	 * 
	 * @param event
	 */
	public void modificarRegionSubGerencia(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'modificarRegionSubGerencia' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		if (this.comComisionDetalList == null
				|| comComisionDetalList.size() == 0) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.modify.validation"));
			return;
		}

		if (this.regionSeleccionada == null) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
			return;
		}

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		String valorSubGerencia = comCodigoSubGerencia;
		f.setCodigoSubGerencia(valorSubGerencia);

		LabelValue[] regiones = (LabelValue[]) aSvc
				.getRegionByPaisMarcaCanalSubGerencia(mPantallaPrincipalBean
						.getCurrentUser().getCodigoPais(), f.getCodigoMarca(),
						f.getCodigoCanal(),
						f.getCodigoSubGerencia().split("_")[0].toString());

		this.siccRegionList = regiones;

		DatosRegionesSubGerencia datosID = new DatosRegionesSubGerencia();
		setKeyDatosComisionUA(this.regionSeleccionada.getClaveRegistro(),
				datosID);

		for (int i = 0; i < comComisionDetalList.size(); i++) {
			DatosRegionesSubGerencia a = (DatosRegionesSubGerencia) comComisionDetalList
					.get(i);
			if (StringUtils.equals(a.getCodigoRegion(),
					datosID.getCodigoRegion())
					&& StringUtils.equals(a.getCodigoZona(),
							datosID.getCodigoZona())) {
				f.setFechaAntiguedadDesdeD(a.getFechaAntiguedadDesde());
				f.setFechaAntiguedadHastaD(a.getFechaAntiguedadHasta());

				String[] codigoRegion = new String[1];
				codigoRegion[0] = a.getCodigoRegion();
				f.setCodigoRegion(codigoRegion);
				f.setCodigoZonaModificar(a.getCodigoZona());
				String[] codigoZona = new String[1];
				codigoZona[0] = a.getCodigoZona();
				f.setCodigoZona(codigoZona);
				comComisionDetalList.set(i, a);
				break;
			}
		}
		f.setRegionesSelectedItems(null);
		this.setIndicadorModificar(true);

		String[] codigoRegion = new String[1];
		codigoRegion[0] = datosID.getCodigoRegion();
		f.setCodigoRegion(codigoRegion);

		f.setOcultarSubGerenciaRegiones(true);

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		this.getRegionesZonasSeleccionadas(null);

	}

	/**
	 * 
	 * @param event
	 */
	public void modificarComision(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'modificarComision' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		if (this.datosCOMComisionesList == null
				|| datosCOMComisionesList.size() == 0) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.modify.validation"));
			return;
		}

		if (this.comisionSeleccionada == null) {
			this.addError(
					"Error: ",
					this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
			return;
		}

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		this.setIndicadorModificar(true);

		if (StringUtils.isNotBlank(f.getEstadoComision())) {
			if (!StringUtils.equals(f.getEstadoComision(), "02")) {
				this.setIndicadorModificar(false);

				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.selection.comision.eliminado"));
				return;
			}
		}
		for (int i = 0; i < this.datosCOMComisionesList.size(); i++) {
			DatosComisionCalculo a = (DatosComisionCalculo) this.datosCOMComisionesList
					.get(i);
			if (a.getContador() == this.comisionSeleccionada.getContador()) {
				f.setContador(a.getContador());
				f.setComisionNroDiasComision(a.getNroDiasComision());
				f.setComisionNroTramo(a.getNroTramo());
				f.setComisionValPorcComision(a.getValPorcComision() + "");
				f.setComisionValPorcRecuperacion(a.getValPorcRecuperacion()
						+ "");
				this.datosCOMComisionesList.set(i, a);
				break;
			}
		}

		f.setDatosSelectedItems(null);

	}

	/**
	 * 
	 * @param event
	 */
	public void modificarComisionEscalonada(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'modificarComisionEscalonada' method");
		}

		MantenimientoCOMComisionesCobranzaForm f = (MantenimientoCOMComisionesCobranzaForm) this.formMantenimiento;

		// Seteo de Tipo Base Comision
		f.setIndicadorBaseComision(true);
		// *******************************

		if (StringUtils.equals(f.getCodigoBaseComision(),
				Constants.CODIGO_BASE_COMISION_01)
				|| StringUtils.equals(f.getCodigoBaseComision(),
						Constants.CODIGO_BASE_COMISION_02)) {

			// Modificar Lista 1
			if (this.comisionEscalonadaSeleccionada == null) {
				this.setIndicadorModificar(false);
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
				return;
			} else {
				for (int i = 0; i < this.datosCOMComisionesEscalonadasList
						.size(); i++) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList
							.get(i);
					if (a.getNroEscala1() == this.comisionEscalonadaSeleccionada
							.getNroEscala1()) {
						f.setNroEscala(a.getNroEscala1());
						f.setComiEscalonadaNroDiasComision(a
								.getNroDiasComision());
						f.setComiEscalonadaNroDiasRecuperacion(a
								.getNroDiasRecuperacion());
						f.setComiEscalonadaPorcRecuperacionInicial(a
								.getValPorcRecuperacionInicial() + "");
						f.setComiEscalonadaPorcRecuperacionFinal(a
								.getValPorcRecuperacionFinal() + "");
						f.setComiEscalonadaPorcComision(a.getValPorcComision()
								+ "");
						f.setComiEscalonadaPorcComisionAdicional(a
								.getValPorcComisionAdicional() + "");
						break;
					}
				}

				f.setEscalonadaSelectedItems(null);
				this.setIndicadorModificar(true);
			}
		} else if (StringUtils.equals(f.getCodigoBaseComision(),
				Constants.CODIGO_BASE_COMISION_03)) {
			// Modificar Lista 2
			if (this.comisionEscalonadaSeleccionada2 == null) {
				this.setIndicadorModificar(false);
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
				return;
			} else {
				for (int i = 0; i < this.datosCOMComisionesEscalonadasList2
						.size(); i++) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList2
							.get(i);
					if (a.getNroEscala2() == this.comisionEscalonadaSeleccionada2
							.getNroEscala2()) {
						f.setNroEscala(a.getNroEscala2());
						f.setComiEscalonadaValPorcFinal(a.getValPorcFinal()
								+ "");
						f.setComiEscalonadaValPorcInicial(a.getValPorcInicial()
								+ "");
						f.setComiEscalonadaPorcComision2(a.getValPorcComision()
								+ "");
						break;
					}
				}

				f.setEscalonadaSelectedItems(null);
				this.setIndicadorModificar(true);
			}
		} else {

			// Modificar Lista 3
			if (this.comisionEscalonadaSeleccionada3 == null) {
				this.setIndicadorModificar(false);
				this.addError(
						"Error: ",
						this.getResourceMessage("mantenimientoCOMComisionesCobranzaForm.lista.noSelection.validation"));
				return;
			} else {
				for (int i = 0; i < this.datosCOMComisionesEscalonadasList3
						.size(); i++) {
					DatosComisionEscalonada a = (DatosComisionEscalonada) this.datosCOMComisionesEscalonadasList3
							.get(i);
					if (a.getNroEscala3() == this.comisionEscalonadaSeleccionada3
							.getNroEscala3()) {
						f.setNroEscala(a.getNroEscala3());
						f.setComiEscalonadaValPorcFinalRecuperacion(a
								.getValPorcFinalRecuperacion() + "");
						f.setComiEscalonadaValPorcFinalVenta(a
								.getValPorcFinalVenta() + "");
						f.setComiEscalonadaValPorcInicialRecuperacion(a
								.getValPorcInicialRecuperacion() + "");
						f.setComiEscalonadaValPorcInicialVenta(a
								.getValPorcInicialVenta() + "");
						f.setComiEscalonadaPorcComision3(a.getValPorcComision()
								+ "");
						break;
					}
				}

				f.setEscalonadaSelectedItems(null);
				this.setIndicadorModificar(true);
			}
		}

		f.setIndicadorComisionEscalonada(Constants.NUMERO_CERO);
		f.setIndicadorComisionEscalonadaBoolean(true);
		// f.setCodigoBaseComision("02");

	}

	/**
	 * 
	 * @param event
	 */
	public void onListaRegionSubgerenciaRowSelect(SelectEvent event) {
		this.regionSeleccionada = (DatosRegionesSubGerencia) event.getObject();
	}

	/**
	 * 
	 * @param event
	 */
	public void onListaComisionRowSelect(SelectEvent event) {
		this.comisionSeleccionada = (DatosComisionCalculo) event.getObject();
	}

	/**
	 * 
	 * @param event
	 */
	public void onListaComisionEscalonadaRowSelect(SelectEvent event) {
		this.comisionEscalonadaSeleccionada = (DatosComisionEscalonada) event
				.getObject();
	}

	/**
	 * 
	 * @param event
	 */
	public void onListaComisionEscalonada2RowSelect(SelectEvent event) {
		this.comisionEscalonadaSeleccionada2 = (DatosComisionEscalonada) event
				.getObject();
	}

	/**
	 * 
	 * @param event
	 */
	public void onListaComisionEscalonada3RowSelect(SelectEvent event) {
		this.comisionEscalonadaSeleccionada3 = (DatosComisionEscalonada) event
				.getObject();
	}

	/**
	 * 
	 * @param event
	 */
	public void onListaComisionClasificacionRowSelect(SelectEvent event) {
		this.comisionClasificacionSeleccionada = (DatosComisionClasificacion) event
				.getObject();
	}

	/**
	 * Coloca id de la Lista seleccionada de Comisiones UA
	 * 
	 * @param id
	 * @param datos
	 */
	private void setKeyDatosComisionUA(String id, DatosRegionesSubGerencia datos) {
		String[] split = id.split("_");
		datos.setCodigoRegion(split[0]);
		if (split.length > 1)
			datos.setCodigoZona(split[1]);
		else
			datos.setCodigoZona(null);
	}

	/**
	 * Revisa si en la lista existe seleccionado Todos
	 * 
	 * @param listaDetalles
	 * @return
	 */
	private boolean findTodos(String listaDetalles[]) {
		boolean lbRetorno = false;
		if ((listaDetalles != null) && (listaDetalles.length > 0)) {
			for (int i = 0; i < listaDetalles.length; i++) {
				if ("00".equals(listaDetalles[i])
						|| StringUtils.isBlank(listaDetalles[i])) {
					lbRetorno = true;
					break;
				}
			}
		}
		return lbRetorno;
	}

	/* GET -SET */

	/**
	 * @return the indicadorTipoProcesoComision
	 */
	public String getIndicadorTipoProcesoComision() {
		return indicadorTipoProcesoComision;
	}

	/**
	 * @param indicadorTipoProcesoComision
	 *            the indicadorTipoProcesoComision to set
	 */
	public void setIndicadorTipoProcesoComision(
			String indicadorTipoProcesoComision) {
		this.indicadorTipoProcesoComision = indicadorTipoProcesoComision;
	}

	/**
	 * @return the comTipoComisionistaList
	 */
	public List getComTipoComisionistaList() {
		return comTipoComisionistaList;
	}

	/**
	 * @param comTipoComisionistaList
	 *            the comTipoComisionistaList to set
	 */
	public void setComTipoComisionistaList(List comTipoComisionistaList) {
		this.comTipoComisionistaList = comTipoComisionistaList;
	}

	/**
	 * @return the comBaseComisionList
	 */
	public LabelValue[] getComBaseComisionList() {
		return comBaseComisionList;
	}

	/**
	 * @param comBaseComisionList
	 *            the comBaseComisionList to set
	 */
	public void setComBaseComisionList(LabelValue[] comBaseComisionList) {
		this.comBaseComisionList = comBaseComisionList;
	}

	/**
	 * @return the comCodigoSubGerencia
	 */
	public String getComCodigoSubGerencia() {
		return comCodigoSubGerencia;
	}

	/**
	 * @param comCodigoSubGerencia
	 *            the comCodigoSubGerencia to set
	 */
	public void setComCodigoSubGerencia(String comCodigoSubGerencia) {
		this.comCodigoSubGerencia = comCodigoSubGerencia;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the comTipoClasificacionList
	 */
	public List getComTipoClasificacionList() {
		return comTipoClasificacionList;
	}

	/**
	 * @param comTipoClasificacionList
	 *            the comTipoClasificacionList to set
	 */
	public void setComTipoClasificacionList(List comTipoClasificacionList) {
		this.comTipoClasificacionList = comTipoClasificacionList;
	}

	/**
	 * @return the comClasificacionList
	 */
	public LabelValue[] getComClasificacionList() {
		return comClasificacionList;
	}

	/**
	 * @param comClasificacionList
	 *            the comClasificacionList to set
	 */
	public void setComClasificacionList(LabelValue[] comClasificacionList) {
		this.comClasificacionList = comClasificacionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the regionSeleccionada
	 */
	public DatosRegionesSubGerencia getRegionSeleccionada() {
		return regionSeleccionada;
	}

	/**
	 * @param regionSeleccionada
	 *            the regionSeleccionada to set
	 */
	public void setRegionSeleccionada(
			DatosRegionesSubGerencia regionSeleccionada) {
		this.regionSeleccionada = regionSeleccionada;
	}

	/**
	 * @return the comComisionDetalList
	 */
	public List getComComisionDetalList() {
		return comComisionDetalList;
	}

	/**
	 * @param comComisionDetalList
	 *            the comComisionDetalList to set
	 */
	public void setComComisionDetalList(List comComisionDetalList) {
		this.comComisionDetalList = comComisionDetalList;
	}

	/**
	 * @return the indicadorModificar
	 */
	public boolean isIndicadorModificar() {
		return indicadorModificar;
	}

	/**
	 * @param indicadorModificar
	 *            the indicadorModificar to set
	 */
	public void setIndicadorModificar(boolean indicadorModificar) {
		this.indicadorModificar = indicadorModificar;
	}

	/**
	 * @return the indicadorModificaCombo
	 */
	public String getIndicadorModificaCombo() {
		return indicadorModificaCombo;
	}

	/**
	 * @param indicadorModificaCombo
	 *            the indicadorModificaCombo to set
	 */
	public void setIndicadorModificaCombo(String indicadorModificaCombo) {
		this.indicadorModificaCombo = indicadorModificaCombo;
	}

	/**
	 * @return the datosCOMComisionesList
	 */
	public List getDatosCOMComisionesList() {
		return datosCOMComisionesList;
	}

	/**
	 * @param datosCOMComisionesList
	 *            the datosCOMComisionesList to set
	 */
	public void setDatosCOMComisionesList(List datosCOMComisionesList) {
		this.datosCOMComisionesList = datosCOMComisionesList;
	}

	/**
	 * @return the datosCOMComisionesEscalonadasList
	 */
	public List getDatosCOMComisionesEscalonadasList() {
		return datosCOMComisionesEscalonadasList;
	}

	/**
	 * @param datosCOMComisionesEscalonadasList
	 *            the datosCOMComisionesEscalonadasList to set
	 */
	public void setDatosCOMComisionesEscalonadasList(
			List datosCOMComisionesEscalonadasList) {
		this.datosCOMComisionesEscalonadasList = datosCOMComisionesEscalonadasList;
	}

	/**
	 * @return the datosCOMComisionesEscalonadasList2
	 */
	public List getDatosCOMComisionesEscalonadasList2() {
		return datosCOMComisionesEscalonadasList2;
	}

	/**
	 * @param datosCOMComisionesEscalonadasList2
	 *            the datosCOMComisionesEscalonadasList2 to set
	 */
	public void setDatosCOMComisionesEscalonadasList2(
			List datosCOMComisionesEscalonadasList2) {
		this.datosCOMComisionesEscalonadasList2 = datosCOMComisionesEscalonadasList2;
	}

	/**
	 * @return the datosCOMComisionesEscalonadasList3
	 */
	public List getDatosCOMComisionesEscalonadasList3() {
		return datosCOMComisionesEscalonadasList3;
	}

	/**
	 * @param datosCOMComisionesEscalonadasList3
	 *            the datosCOMComisionesEscalonadasList3 to set
	 */
	public void setDatosCOMComisionesEscalonadasList3(
			List datosCOMComisionesEscalonadasList3) {
		this.datosCOMComisionesEscalonadasList3 = datosCOMComisionesEscalonadasList3;
	}

	/**
	 * @return the datosCOMComisionesClasificacionList
	 */
	public List getDatosCOMComisionesClasificacionList() {
		return datosCOMComisionesClasificacionList;
	}

	/**
	 * @param datosCOMComisionesClasificacionList
	 *            the datosCOMComisionesClasificacionList to set
	 */
	public void setDatosCOMComisionesClasificacionList(
			List datosCOMComisionesClasificacionList) {
		this.datosCOMComisionesClasificacionList = datosCOMComisionesClasificacionList;
	}

	/**
	 * @return the displayClasificacionMostrar
	 */
	public boolean isDisplayClasificacionMostrar() {
		return displayClasificacionMostrar;
	}

	/**
	 * @param displayClasificacionMostrar
	 *            the displayClasificacionMostrar to set
	 */
	public void setDisplayClasificacionMostrar(
			boolean displayClasificacionMostrar) {
		this.displayClasificacionMostrar = displayClasificacionMostrar;
	}

	/**
	 * @return the datosRegiTabDisabled
	 */
	public boolean isDatosRegiTabDisabled() {
		return datosRegiTabDisabled;
	}

	/**
	 * @param datosRegiTabDisabled
	 *            the datosRegiTabDisabled to set
	 */
	public void setDatosRegiTabDisabled(boolean datosRegiTabDisabled) {
		this.datosRegiTabDisabled = datosRegiTabDisabled;
	}

	/**
	 * @return the comisionSeleccionada
	 */
	public DatosComisionCalculo getComisionSeleccionada() {
		return comisionSeleccionada;
	}

	/**
	 * @param comisionSeleccionada
	 *            the comisionSeleccionada to set
	 */
	public void setComisionSeleccionada(
			DatosComisionCalculo comisionSeleccionada) {
		this.comisionSeleccionada = comisionSeleccionada;
	}

	/**
	 * @return the comisionEscalonadaSeleccionada
	 */
	public DatosComisionEscalonada getComisionEscalonadaSeleccionada() {
		return comisionEscalonadaSeleccionada;
	}

	/**
	 * @param comisionEscalonadaSeleccionada
	 *            the comisionEscalonadaSeleccionada to set
	 */
	public void setComisionEscalonadaSeleccionada(
			DatosComisionEscalonada comisionEscalonadaSeleccionada) {
		this.comisionEscalonadaSeleccionada = comisionEscalonadaSeleccionada;
	}

	/**
	 * @return the comisionClasificacionSeleccionada
	 */
	public DatosComisionClasificacion getComisionClasificacionSeleccionada() {
		return comisionClasificacionSeleccionada;
	}

	/**
	 * @param comisionClasificacionSeleccionada
	 *            the comisionClasificacionSeleccionada to set
	 */
	public void setComisionClasificacionSeleccionada(
			DatosComisionClasificacion comisionClasificacionSeleccionada) {
		this.comisionClasificacionSeleccionada = comisionClasificacionSeleccionada;
	}

	/**
	 * @return the displayDiv3
	 */
	public boolean isDisplayDiv3() {
		return displayDiv3;
	}

	/**
	 * @param displayDiv3
	 *            the displayDiv3 to set
	 */
	public void setDisplayDiv3(boolean displayDiv3) {
		this.displayDiv3 = displayDiv3;
	}

	/**
	 * @return the displayDiv4
	 */
	public boolean isDisplayDiv4() {
		return displayDiv4;
	}

	/**
	 * @param displayDiv4
	 *            the displayDiv4 to set
	 */
	public void setDisplayDiv4(boolean displayDiv4) {
		this.displayDiv4 = displayDiv4;
	}

	/**
	 * @return the displayDiv5
	 */
	public boolean isDisplayDiv5() {
		return displayDiv5;
	}

	/**
	 * @param displayDiv5
	 *            the displayDiv5 to set
	 */
	public void setDisplayDiv5(boolean displayDiv5) {
		this.displayDiv5 = displayDiv5;
	}

	/**
	 * @return the displayDiv6
	 */
	public boolean isDisplayDiv6() {
		return displayDiv6;
	}

	/**
	 * @param displayDiv6
	 *            the displayDiv6 to set
	 */
	public void setDisplayDiv6(boolean displayDiv6) {
		this.displayDiv6 = displayDiv6;
	}

	/**
	 * @return the displayDiv7
	 */
	public boolean isDisplayDiv7() {
		return displayDiv7;
	}

	/**
	 * @param displayDiv7
	 *            the displayDiv7 to set
	 */
	public void setDisplayDiv7(boolean displayDiv7) {
		this.displayDiv7 = displayDiv7;
	}

	/**
	 * @return the displayDiv8
	 */
	public boolean isDisplayDiv8() {
		return displayDiv8;
	}

	/**
	 * @param displayDiv8
	 *            the displayDiv8 to set
	 */
	public void setDisplayDiv8(boolean displayDiv8) {
		this.displayDiv8 = displayDiv8;
	}

	/**
	 * @return the displayDiv10
	 */
	public boolean isDisplayDiv10() {
		return displayDiv10;
	}

	/**
	 * @param displayDiv10
	 *            the displayDiv10 to set
	 */
	public void setDisplayDiv10(boolean displayDiv10) {
		this.displayDiv10 = displayDiv10;
	}

	/**
	 * @return the mantenimientoComisionesTabActivo
	 */
	public int getMantenimientoComisionesTabActivo() {
		return mantenimientoComisionesTabActivo;
	}

	/**
	 * @param mantenimientoComisionesTabActivo
	 *            the mantenimientoComisionesTabActivo to set
	 */
	public void setMantenimientoComisionesTabActivo(
			int mantenimientoComisionesTabActivo) {
		this.mantenimientoComisionesTabActivo = mantenimientoComisionesTabActivo;
	}

	/**
	 * @return the mantenimientoComisionesTabDatosRegionDisabled
	 */
	public boolean isMantenimientoComisionesTabDatosRegionDisabled() {
		return mantenimientoComisionesTabDatosRegionDisabled;
	}

	/**
	 * @param mantenimientoComisionesTabDatosRegionDisabled
	 *            the mantenimientoComisionesTabDatosRegionDisabled to set
	 */
	public void setMantenimientoComisionesTabDatosRegionDisabled(
			boolean mantenimientoComisionesTabDatosRegionDisabled) {
		this.mantenimientoComisionesTabDatosRegionDisabled = mantenimientoComisionesTabDatosRegionDisabled;
	}

	/**
	 * @return the mantenimientoComisionesTabDatosComisionesDisabled
	 */
	public boolean isMantenimientoComisionesTabDatosComisionesDisabled() {
		return mantenimientoComisionesTabDatosComisionesDisabled;
	}

	/**
	 * @param mantenimientoComisionesTabDatosComisionesDisabled
	 *            the mantenimientoComisionesTabDatosComisionesDisabled to set
	 */
	public void setMantenimientoComisionesTabDatosComisionesDisabled(
			boolean mantenimientoComisionesTabDatosComisionesDisabled) {
		this.mantenimientoComisionesTabDatosComisionesDisabled = mantenimientoComisionesTabDatosComisionesDisabled;
	}

	/**
	 * @return the mantenimientoComisionesTabDatosEscalonadaDisabled
	 */
	public boolean isMantenimientoComisionesTabDatosEscalonadaDisabled() {
		return mantenimientoComisionesTabDatosEscalonadaDisabled;
	}

	/**
	 * @param mantenimientoComisionesTabDatosEscalonadaDisabled
	 *            the mantenimientoComisionesTabDatosEscalonadaDisabled to set
	 */
	public void setMantenimientoComisionesTabDatosEscalonadaDisabled(
			boolean mantenimientoComisionesTabDatosEscalonadaDisabled) {
		this.mantenimientoComisionesTabDatosEscalonadaDisabled = mantenimientoComisionesTabDatosEscalonadaDisabled;
	}

	/**
	 * @return the mantenimientoComisionesTabDatosClasificacionDisabled
	 */
	public boolean isMantenimientoComisionesTabDatosClasificacionDisabled() {
		return mantenimientoComisionesTabDatosClasificacionDisabled;
	}

	/**
	 * @param mantenimientoComisionesTabDatosClasificacionDisabled
	 *            the mantenimientoComisionesTabDatosClasificacionDisabled to
	 *            set
	 */
	public void setMantenimientoComisionesTabDatosClasificacionDisabled(
			boolean mantenimientoComisionesTabDatosClasificacionDisabled) {
		this.mantenimientoComisionesTabDatosClasificacionDisabled = mantenimientoComisionesTabDatosClasificacionDisabled;
	}

	/**
	 * @return the siccRegionSeleccionadaList
	 */
	public String getSiccRegionSeleccionadaList() {
		return siccRegionSeleccionadaList;
	}

	/**
	 * @param siccRegionSeleccionadaList
	 *            the siccRegionSeleccionadaList to set
	 */
	public void setSiccRegionSeleccionadaList(String siccRegionSeleccionadaList) {
		this.siccRegionSeleccionadaList = siccRegionSeleccionadaList;
	}

	/**
	 * @return the siccZonaSeleccionadaList
	 */
	public String getSiccZonaSeleccionadaList() {
		return siccZonaSeleccionadaList;
	}

	/**
	 * @param siccZonaSeleccionadaList
	 *            the siccZonaSeleccionadaList to set
	 */
	public void setSiccZonaSeleccionadaList(String siccZonaSeleccionadaList) {
		this.siccZonaSeleccionadaList = siccZonaSeleccionadaList;
	}

	/**
	 * @return the comisionEscalonadaSeleccionada2
	 */
	public DatosComisionEscalonada getComisionEscalonadaSeleccionada2() {
		return comisionEscalonadaSeleccionada2;
	}

	/**
	 * @param comisionEscalonadaSeleccionada2
	 *            the comisionEscalonadaSeleccionada2 to set
	 */
	public void setComisionEscalonadaSeleccionada2(
			DatosComisionEscalonada comisionEscalonadaSeleccionada2) {
		this.comisionEscalonadaSeleccionada2 = comisionEscalonadaSeleccionada2;
	}

	/**
	 * @return the comisionEscalonadaSeleccionada3
	 */
	public DatosComisionEscalonada getComisionEscalonadaSeleccionada3() {
		return comisionEscalonadaSeleccionada3;
	}

	/**
	 * @param comisionEscalonadaSeleccionada3
	 *            the comisionEscalonadaSeleccionada3 to set
	 */
	public void setComisionEscalonadaSeleccionada3(
			DatosComisionEscalonada comisionEscalonadaSeleccionada3) {
		this.comisionEscalonadaSeleccionada3 = comisionEscalonadaSeleccionada3;
	}

	/* */

}
