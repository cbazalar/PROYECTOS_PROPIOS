package biz.belcorp.ssicc.web.spusicc.cronograma.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DetalleBonos;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAMatrizDiasService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMBonosForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAMatrizDiasPopupForm;
import biz.belcorp.ssicc.web.spusicc.cronograma.form.MantenimientoCRAMatrizDiasSearchForm;

@SessionScoped
@ManagedBean
public class MantenimientoCRAMatrizDiasSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6195605017525923365L;
	private List craCargaMatrizDiasList;
	private List craCargaMatrizDiasFueraPeriodoList;
	private List siccGrupoFacturacionList;
	private List siccActividadList;
	private String diasDesplazamiento;
	private String ordenDesplazado;
	private String ordenActividadOrigen;

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
		// TODO Auto-generated method stub
		MantenimientoCRAMatrizDiasSearchForm form = new MantenimientoCRAMatrizDiasSearchForm();
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
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		log.debug("setAddAttributes method ....");
		MantenimientoCRAMatrizDiasSearchForm f = (MantenimientoCRAMatrizDiasSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		f.setCodigoPais(pais.getCodigo());

		f.setActividad(null);
		f.setGrupoZona(null);

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		// obteniendo las lista de grupode facturacion y actividad
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Long oidMarca = clienteService
				.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService
				.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);

		Map params = new HashMap();
		params.put("codigoPais", pais.getCodigo());
		params.put(
				"oidPais",
				new Long(reporteService.getOidString("getOidPaisByCodigoPais",
						params)));
		params.put("oidMarca", oidMarca);
		params.put("oidCanal", oidCanal);

		siccGrupoFacturacionList = reporteService.getGrupoFacturacion(params);
		siccActividadList = reporteService.getActividad(params);
		findList(f);

		this.mostrarBotonBuscar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonConsultar = false;

	}

	public void findList(MantenimientoCRAMatrizDiasSearchForm f) {

		log.debug("find");
		MantenimientoCRAMatrizDiasService service = (MantenimientoCRAMatrizDiasService) getBean("spusicc.cronograma.mantenimientoCRAMatrizDiasService");
		Map criteria = new HashMap();

		criteria.put("codigoPais", f.getCodigoPais());

		List listaMatri = service.getCargaMatrizDias(criteria);
		pintaCronograma(listaMatri);

	}

	private void pintaCronograma(List listaMatri) {

		List listaMatriDentro = new ArrayList();
		List listaMatriFuera = new ArrayList();

		String grupoZonaAnt = "";
		String oidGrupoZonaAnt = "";
		Map matriGrupoZona = new HashMap();
		Map matriGrupoZonaIni = new HashMap();

		String actividades[] = new String[22];

		try {
			// primera fila cabecera fechas
			matriGrupoZonaIni.put("grupoZona", "Grupo Zona");
			for (int i = 1; i <= 21; i++) {
				actividades[i] = "" + i;
			}
			matriGrupoZonaIni.put("actividades", actividades);
			listaMatriDentro.add(matriGrupoZonaIni);

			// Resto de filas actividades
			actividades = new String[22];

			HashMap params = new HashMap();

			params.put("oidGrupoZona", "xxx");
			params.put("grupoZona", "xxx");

			listaMatri.add(params);

			for (int i = 0; i < listaMatri.size(); i++) {

				matriGrupoZona = (Map) listaMatri.get(i);

				if ((!oidGrupoZonaAnt.equals(matriGrupoZona.get("oidGrupoZona")
						.toString())) && (i != 0)) {
					// quiebre
					Map matri = new HashMap();
					matri.put("actividades", actividades);
					matri.put("oidGrupoZona", oidGrupoZonaAnt);
					matri.put("grupoZona", grupoZonaAnt);

					listaMatriDentro.add(matri);
					actividades = new String[22];

				}
				if (!matriGrupoZona.get("grupoZona").toString().equals("xxx")) {
					int orden = Integer.parseInt(matriGrupoZona.get("orden")
							.toString());
					if ((orden >= 0) && (orden < 21)) {
						actividades[orden] = matriGrupoZona.get("actividad")
								.toString();
					} else {
						String[] actividad = StringUtils.split(matriGrupoZona
								.get("actividad").toString(), "|");

						for (int j = 0; j < actividad.length; j++) {

							Map matriFuera = new HashMap();

							matriFuera.put("oidGrupoZona",
									matriGrupoZona.get("oidGrupoZona"));
							matriFuera.put("grupoZona",
									matriGrupoZona.get("grupoZona"));
							matriFuera.put("orden", Integer
									.parseInt(matriGrupoZona.get("orden")
											.toString()) + 1);
							matriFuera.put("nombreActividad", actividad[j]);
							listaMatriFuera.add(matriFuera);
						}

					}

					grupoZonaAnt = matriGrupoZona.get("grupoZona").toString();
					oidGrupoZonaAnt = matriGrupoZona.get("oidGrupoZona")
							.toString();
				}

			}

			// ## METODO CREADO PARA ELIMINAR LOS DELIMITADORES
			// ## YA QUE NO EXISTE LA LIBRERIA DE FORTOKENS EN XHTML

			for (int i = 0; i < listaMatriDentro.size(); i++) {
				// Creo la lista actividad2 para no perjudicar la lógica
				List actividades2 = new ArrayList();
				Map map = (Map) listaMatriDentro.get(i);
				String[] act = (String[]) map.get("actividades");
				for (int j = 0; j < 21; j++) {
					List lista = new ArrayList();
					Map map2 = new HashMap();
					List listaActividades = new ArrayList();
					Map map3 = new HashMap();
					String actividad = act[j];
					if (actividad == null)
						actividad = "";
					StringTokenizer st = new StringTokenizer(actividad, "|");
					while (st.hasMoreTokens())
						listaActividades.add(st.nextToken());

					map3.put("listaB", listaActividades);
					map3.put("index", "" + j + "");
					lista.add(map3);

					map2.put("lista", lista);
					actividades2.add(map2);
				}

				map.put("actividades2", actividades2);
			}
			// #############################

			craCargaMatrizDiasList = listaMatriDentro;
			craCargaMatrizDiasFueraPeriodoList = listaMatriFuera;
		} catch (Exception e) {
			// TODO: handle exception
			addError("Error", "El proceso no se ha completado. Evalue los siguiente detalles:");
		}

	}

	public void openActividadPopup(ActionEvent event) {
		log.debug("Executing action : setViewAttributes. ");

		MantenimientoCRAMatrizDiasPopupForm f = new MantenimientoCRAMatrizDiasPopupForm();
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String accion = externalContext.getRequestParameterMap().get("DATOS");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		String oidGrupoZona = StringUtils.split(accion, "|")[0];
		String actividad = StringUtils.split(accion, "|")[1];
		String orden = StringUtils.split(accion, "|")[2];
		String nombreGrupoZona = StringUtils.split(accion, "|")[3];

		MantenimientoCRAMatrizDiasService service = (MantenimientoCRAMatrizDiasService) getBean("spusicc.cronograma.mantenimientoCRAMatrizDiasService");

		Map criteria = new HashMap();

		criteria.put("oidGrupoZona", oidGrupoZona);
		criteria.put("nombreActividad", actividad);
		criteria.put("codigoPais", f.getCodigoPais());

		Map datosMatriz = (HashMap) service.getMatrizDias(criteria).get(0);

		f.setOidMatrizDias(datosMatriz.get("oidMatrizDias").toString());

		if ((String) datosMatriz.get("nombreActividadOrigen") != null) {
			f.setNombreActividadOrigen(datosMatriz.get("nombreActividadOrigen")
					.toString());
		}
		f.setOrdenActividadOrigen(datosMatriz.get("ordenActividadOrigen")
				.toString());
		f.setNombreGrupoZona(nombreGrupoZona);
		f.setNombreActividad(actividad);
		f.setOrden(String.valueOf(Integer.parseInt(orden) + 1));
		f.setOidGrupoZona(oidGrupoZona);
		setOrdenActividadOrigen(datosMatriz.get("ordenActividadOrigen")
				.toString());
		setDiasDesplazamiento(null);
		setOrdenDesplazado(null);
		this.formMantenimiento = f;
		this.getRequestContext().execute("PF('viewMatrizDiasPopup').show()");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		if (accion.equals("INSERTAR_POPUP")) {
			// ############### VALIDAR CAMPOS REQUERIDOS #######################
			if (StringUtils.isBlank(getDiasDesplazamiento()))
				return "Días Desplazamiento es un campor requerido";

		}
		return null;
	}

	/**
	 * @param event
	 * @throws Exception
	 */
	public void anadirActividad(ActionEvent event) throws Exception {
		this.anadirActividad();
		this.redireccionarPagina("mantenimientoCRAMatrizDiasSearchForm");
		// messages.add(ActionMessages.GLOBAL_MESSAGE, new
		// ActionMessage("mantenimientoCRAMatrizDias.msj.registrar"));

	}
	
	
	/**
	 * @throws Exception
	 */
	public String anadirActividad() throws Exception {
		log.debug("anadirActividad");

		MantenimientoCRAMatrizDiasSearchForm f = (MantenimientoCRAMatrizDiasSearchForm) this.formBusqueda;
		MantenimientoCRAMatrizDiasService service = (MantenimientoCRAMatrizDiasService) getBean("spusicc.cronograma.mantenimientoCRAMatrizDiasService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoUsuario = usuario.getLogin();

		String[] listaGrupoZona = f.getGrupoZona();
		String[] listaActividad = f.getActividad();

		ArrayList lista = new ArrayList();

		for (int i = 0; i < listaGrupoZona.length; i++) {

			for (int j = 0; j < listaActividad.length; j++) {

				Map criteria = new HashMap();

				criteria.put("oidGrupoZona", listaGrupoZona[i]);
				criteria.put("oidActividad", listaActividad[j]);
				criteria.put("codigoPais", f.getCodigoPais());
				criteria.put("usuario", codigoUsuario);

				lista.add(criteria);
			}
		}

		service.insertActividadMatrizDias(lista);
		findList(f);
		return "mantenimientoCRAMatrizDiasSearchForm";
		//this.redireccionarPagina("mantenimientoCRAMatrizDiasSearchForm");
		// messages.add(ActionMessages.GLOBAL_MESSAGE, new
		// ActionMessage("mantenimientoCRAMatrizDias.msj.registrar"));

	}

	public void setViewPopup(ActionEvent event) {
		log.debug("Executing action : setViewAttributes. ");

		MantenimientoCRAMatrizDiasPopupForm f = (MantenimientoCRAMatrizDiasPopupForm) this.formMantenimiento;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		String id = "";
		log.debug("----- id ----- " + id);

		String oidGrupoZona = StringUtils.split(id, "|")[0];
		String actividad = StringUtils.split(id, "|")[1];
		String orden = StringUtils.split(id, "|")[2];
		String nombreGrupoZona = StringUtils.split(id, "|")[3];

		MantenimientoCRAMatrizDiasService service = (MantenimientoCRAMatrizDiasService) getBean("spusicc.cronograma.mantenimientoCRAMatrizDiasService");

		Map criteria = new HashMap();

		criteria.put("oidGrupoZona", oidGrupoZona);
		criteria.put("nombreActividad", actividad);
		criteria.put("codigoPais", f.getCodigoPais());

		Map datosMatriz = (HashMap) service.getMatrizDias(criteria).get(0);

		f.setOidMatrizDias(datosMatriz.get("oidMatrizDias").toString());

		if ((String) datosMatriz.get("nombreActividadOrigen") != null) {
			f.setNombreActividadOrigen(datosMatriz.get("nombreActividadOrigen")
					.toString());
		}
		f.setOrdenActividadOrigen(datosMatriz.get("ordenActividadOrigen")
				.toString());
		f.setNombreGrupoZona(nombreGrupoZona);
		f.setNombreActividad(actividad);
		f.setOrden(String.valueOf(Integer.parseInt(orden) + 1));
		f.setOidGrupoZona(oidGrupoZona);

	}

	public void setSavePopup(ActionEvent event) throws IOException {
		log.debug("Executing action : setSaveAttributes. ");

		MantenimientoCRAMatrizDiasPopupForm f = (MantenimientoCRAMatrizDiasPopupForm) this.formMantenimiento;
		MantenimientoCRAMatrizDiasSearchForm form = (MantenimientoCRAMatrizDiasSearchForm) this.formBusqueda;
		MantenimientoCRAMatrizDiasService service = (MantenimientoCRAMatrizDiasService) getBean("spusicc.cronograma.mantenimientoCRAMatrizDiasService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoUsuario = usuario.getLogin();

		Map criteria = new HashMap();
		criteria.put("numeroDias", getDiasDesplazamiento());
		criteria.put("oidMatrizDias", f.getOidMatrizDias());
		criteria.put("usuario", codigoUsuario);

		service.updateMatrizDias(criteria);
		findList(form);

		this.redireccionarPagina("mantenimientoCRAMatrizDiasSearchForm");

		// messages.add(ActionMessages.GLOBAL_MESSAGE, new
		// ActionMessage("mantenimientoCRAMatrizDias.msj.actualizar"));

	}

	/**
	 * @return the craCargaMatrizDiasList
	 */
	public List getCraCargaMatrizDiasList() {
		return craCargaMatrizDiasList;
	}

	/**
	 * @param craCargaMatrizDiasList
	 *            the craCargaMatrizDiasList to set
	 */
	public void setCraCargaMatrizDiasList(List craCargaMatrizDiasList) {
		this.craCargaMatrizDiasList = craCargaMatrizDiasList;
	}

	/**
	 * @return the craCargaMatrizDiasFueraPeriodoList
	 */
	public List getCraCargaMatrizDiasFueraPeriodoList() {
		return craCargaMatrizDiasFueraPeriodoList;
	}

	/**
	 * @param craCargaMatrizDiasFueraPeriodoList
	 *            the craCargaMatrizDiasFueraPeriodoList to set
	 */
	public void setCraCargaMatrizDiasFueraPeriodoList(
			List craCargaMatrizDiasFueraPeriodoList) {
		this.craCargaMatrizDiasFueraPeriodoList = craCargaMatrizDiasFueraPeriodoList;
	}

	/**
	 * @return the siccGrupoFacturacionList
	 */
	public List getSiccGrupoFacturacionList() {
		return siccGrupoFacturacionList;
	}

	/**
	 * @param siccGrupoFacturacionList
	 *            the siccGrupoFacturacionList to set
	 */
	public void setSiccGrupoFacturacionList(List siccGrupoFacturacionList) {
		this.siccGrupoFacturacionList = siccGrupoFacturacionList;
	}

	/**
	 * @return the siccActividadList
	 */
	public List getSiccActividadList() {
		return siccActividadList;
	}

	/**
	 * @param siccActividadList
	 *            the siccActividadList to set
	 */
	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}

	/**
	 * @return the diasDesplazamiento
	 */
	public String getDiasDesplazamiento() {
		return diasDesplazamiento;
	}

	/**
	 * @param diasDesplazamiento
	 *            the diasDesplazamiento to set
	 */
	public void setDiasDesplazamiento(String diasDesplazamiento) {
		this.diasDesplazamiento = diasDesplazamiento;
	}

	/**
	 * @return the ordenDesplazado
	 */
	public String getOrdenDesplazado() {
		return ordenDesplazado;
	}

	/**
	 * @param ordenDesplazado
	 *            the ordenDesplazado to set
	 */
	public void setOrdenDesplazado(String ordenDesplazado) {
		this.ordenDesplazado = ordenDesplazado;
	}

	/**
	 * @return the ordenActividadOrigen
	 */
	public String getOrdenActividadOrigen() {
		return ordenActividadOrigen;
	}

	/**
	 * @param ordenActividadOrigen
	 *            the ordenActividadOrigen to set
	 */
	public void setOrdenActividadOrigen(String ordenActividadOrigen) {
		this.ordenActividadOrigen = ordenActividadOrigen;
	}

}
