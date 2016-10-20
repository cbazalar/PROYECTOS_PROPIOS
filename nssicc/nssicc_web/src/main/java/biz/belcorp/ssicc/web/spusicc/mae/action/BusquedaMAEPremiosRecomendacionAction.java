package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteConcursoPremio;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Concurso;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Premio;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.BusquedaMAEPremiosRecomendacionForm;

@SessionScoped
@ManagedBean
public class BusquedaMAEPremiosRecomendacionAction extends
		BaseMantenimientoSearchAbstractAction {
	private String idPrincipal;
	private String idPremiosMaeModificacion = "";
	private String codigoZonaConcurso = "";
	private List busquedaConcursosList = new ArrayList();
	private List busquedaPremiosList = new ArrayList();
	private Object premioSeleccionado;
	private Object concursoSeleccionado;
	private List clienteConcursoPremioList = new ArrayList();
	private Object selectedPremio;
	private String mensajeFinal;

	public String getMensajeFinal() {
		return mensajeFinal;
	}

	public void setMensajeFinal(String mensajeFinal) {
		this.mensajeFinal = mensajeFinal;
	}

	public Object getPremioSeleccionado() {
		return premioSeleccionado;
	}

	public void setPremioSeleccionado(Object premioSeleccionado) {
		this.premioSeleccionado = premioSeleccionado;
	}

	public Object getConcursoSeleccionado() {
		return concursoSeleccionado;
	}

	public void setConcursoSeleccionado(Object concursoSeleccionado) {
		this.concursoSeleccionado = concursoSeleccionado;
	}

	public List getBusquedaPremiosList() {
		return busquedaPremiosList;
	}

	public void setBusquedaPremiosList(List busquedaPremiosList) {
		this.busquedaPremiosList = busquedaPremiosList;
	}

	public Object getSelectedPremio() {
		return selectedPremio;
	}

	public void setSelectedPremio(Object selectedPremio) {
		this.selectedPremio = selectedPremio;
	}

	public List getClienteConcursoPremioList() {
		return clienteConcursoPremioList;
	}

	public void setClienteConcursoPremioList(List clienteConcursoPremioList) {
		this.clienteConcursoPremioList = clienteConcursoPremioList;
	}

	public String getIdPrincipal() {
		return idPrincipal;
	}

	public void setIdPrincipal(String idPrincipal) {
		this.idPrincipal = idPrincipal;
	}

	public String getIdPremiosMaeModificacion() {
		return idPremiosMaeModificacion;
	}

	public void setIdPremiosMaeModificacion(String idPremiosMaeModificacion) {
		this.idPremiosMaeModificacion = idPremiosMaeModificacion;
	}

	public String getCodigoZonaConcurso() {
		return codigoZonaConcurso;
	}

	public void setCodigoZonaConcurso(String codigoZonaConcurso) {
		this.codigoZonaConcurso = codigoZonaConcurso;
	}

	public List getBusquedaConcursosList() {
		return busquedaConcursosList;
	}

	public void setBusquedaConcursosList(List busquedaConcursosList) {
		this.busquedaConcursosList = busquedaConcursosList;
	}

	/**
	 * Juan Pablo Pescorán
	 */
	private static final long serialVersionUID = -3303938592762630869L;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoRECProductosFFNNEEList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaMAEPremiosRecomendacionForm b = new BusquedaMAEPremiosRecomendacionForm();
		return b;
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
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		BusquedaMAEPremiosRecomendacionForm f = (BusquedaMAEPremiosRecomendacionForm) formBusqueda;
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		List listClienteConcursoPremio = clienteConcursoPremioList;

		Concurso c = (Concurso) concursoSeleccionado;
		Premio p = (Premio) premioSeleccionado;

		String idPremio = idPrincipal;// f.getSelectedItem();
		log.debug("___idPremio = " + p.getOidPremio());

		String idConcurso = f.getSelectedItems()[0];
		log.debug("___idConcurso = " + idConcurso);

		Iterator it = listClienteConcursoPremio.iterator();
		while (it.hasNext()) {
			ClienteConcursoPremio concursoPremio = (ClienteConcursoPremio) it
					.next();

			String oidConcurso = p.getOidConcurso().toString();
			String oidPremio = p.getOidConcurso().toString();
			String numeroPremio = p.getNumeroPremio().toString();

			if (concursoPremio.getOidConcurso().toString().equals(oidConcurso)) {
				concursoPremio.setOidNivelPremio(new Long(oidPremio));
				concursoPremio.setNumeroPremio(new Integer(numeroPremio));
			}
		}

		clienteService.updateClienteRecomendado(listClienteConcursoPremio);

		f.setGraboOK(true);

		if (f.isGrabar()) {
			addInfo(
					"Mensaje",
					getResourceMessage(
							"mantenimientoMAEClienteForm.added",
							new Object[] {this.mensajeFinal
									}));
			this.redireccionarPagina("mantenimientoMAEClienteForm");
	
			return true;// mapping.findForward("list");
		} else
			return false;// mapping.findForward("listPremios");
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void inicializarValores(String mensaje) throws Exception {
		log.debug("__entro a ActionForward view");

		// Removemos el form bean
		// removeFormBean(mapping, request);

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		BusquedaMAEPremiosRecomendacionForm f = (BusquedaMAEPremiosRecomendacionForm) formBusqueda;
		Map criteria = new HashMap();

		String id = this.idPrincipal;// request.getParameter("id");

		if (id == null) {
			id = idPremiosMaeModificacion;
			f.setGrabar(false);
		} else
			f.setGrabar(true);

		log.debug("__id = " + id);

		String codigoZona = codigoZonaConcurso;

		criteria.put("oidPeriodo", StringUtils.split(id, "|")[0]);
		criteria.put("oidPais", StringUtils.split(id, "|")[1]);
		criteria.put("oidDirigConsul", Constants.MAE_OID_DIRIG_CONSUL);
		criteria.put("oidBaseCalcu", Constants.MAE_OID_BASE_CALCU_RECOMEN);
		criteria.put("codigoZona", codigoZona);
		criteria.put("codigoCliente", StringUtils.split(id, "|")[3]);

		List listConcursos = clienteService.getConcursos(criteria);
		validarConcursosMostrar(clienteService, listConcursos, id);
		busquedaConcursosList = listConcursos;

		f.setGraboOK(false);
		f.setOidPais(StringUtils.split(id, "|")[1]);

		if (listConcursos.size() == 1) {
			Concurso concurso = (Concurso) listConcursos.get(0);
			Map criteria2 = new HashMap();
			criteria2.put("oidConcurso", concurso.getOidConcurso().toString());

			List listPremios = clienteService.getPremios(criteria2);

			if (listPremios.size() >= 1) {
				Premio premio = (Premio) listPremios.get(0);

				// f.setSelectedItem(premio.getOidConcurso().toString() + "|"
				// + premio.getOidPremio().toString() + "|"
				// + premio.getNumeroPremio().toString());
				f.setFoco("grabar");
			} else {
				f.setFoco("premio");
			}

			String[] valConcurso = { concurso.getOidConcurso().toString() };
			f.setSelectedItems(valConcurso);

			busquedaPremiosList = listPremios;
			addInfo("Mensaje", mensaje);
			this.mensajeFinal = mensaje;

		} else {
			f.setFoco("concurso");
			f.setSelectedItems(null);
			// f.setSelectedItem(null);

			busquedaConcursosList.clear();
		}

	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonBuscar = false;
	}

	private void validarConcursosMostrar(
			MantenimientoMAEClienteService clienteService, List listConcursos,
			String id) {
		String oidPeriodo = StringUtils.split(id, "|")[0];
		String clienteRecomendado = StringUtils.split(id, "|")[2];
		String clienteRecomendante = StringUtils.split(id, "|")[3];

		List listClienteConcursoPremio = new ArrayList();
		Iterator it = listConcursos.iterator();

		while (it.hasNext()) {
			Concurso concurso = (Concurso) it.next();

			// si no pertenece a la zona geografica del concurso, no se le
			// asigna dicho concurso a la consultora
			if (concurso.getTotalGeografico().intValue() > 0
					&& concurso.getCodigoZona() == null) {
				it.remove();
				continue;
			}

			// Si el Concurso de Rec. No tiene Niveles Electivos, no se muestra
			if (concurso.getNumeroNivelSelectivo().intValue() == 0) {
				/*
				 * //si el concurso es de mas de un Nivel, no se muestra
				 * if(concurso.getNumeroNivel().intValue() > 1) {
				 */
				it.remove();
			} else {
				Map criteria = new HashMap();
				criteria.put("oidConcurso", concurso.getOidConcurso()
						.toString());
				List listPremios = clienteService.getPremios(criteria);

				// si el premio es de un nivel y con premio unico no se muestra
				if (listPremios.size() <= 1)
					it.remove();
				else {
					ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
					concursoPremio
							.setCodigoClienteRecomendante(clienteRecomendante);
					concursoPremio.setOidConcurso(concurso.getOidConcurso());

					concursoPremio
							.setCodigoClienteRecomendado(clienteRecomendado);
					concursoPremio.setOidPeriodo(new Long(oidPeriodo));

					Premio premio = (Premio) listPremios.get(0);
					concursoPremio.setOidNivelPremio(premio.getOidPremio());
					concursoPremio.setNumeroPremio(premio.getNumeroPremio());

					listClienteConcursoPremio.add(concursoPremio);
				}
			}
		}
		clienteConcursoPremioList = listClienteConcursoPremio;

	}

}
