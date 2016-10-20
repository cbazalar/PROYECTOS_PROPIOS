package biz.belcorp.ssicc.web.spusicc.men.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENPatronMensajeOrdenaForm;

@SessionScoped
@ManagedBean
public class MantenimientoMENPatronMensajeOrdenaAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6759545324455429682L;
	Map data;
	private String codigoPais;
	private List listMensajesDisponibles;
	private List msgMensajeDocumentoList;
	private List arbolMensaje;
	private List arbolMensajeTemp;
	private List listSecciones;
	private List listMensajePatron;

	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return "mantenimientoMENPatronMensajeList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENPatronMensajeOrdenaForm f = (MantenimientoMENPatronMensajeOrdenaForm) this.formMantenimiento;
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");

		List arbolOrden = new ArrayList();
		List listaOrdenada = ordenaMensajePatron();
		
		for (int i = 0; i < listaOrdenada.size(); i++) {
			Map marbol = (Map) listaOrdenada.get(i);
			arbolOrden.add(marbol);
		}
		log.debug("arbol " + arbolOrden.size());

		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("codigoDocumento", f.getCodigoDocumento());
		map.put("login", usuario.getLogin());
		map.put("arbol", arbolOrden);
		map.put("campanha", f.getCampanha());
		try {
			service.ordenarPatron(map);
			this.redireccionarPagina("mantenimientoMENPatronMensajeList");

		} catch (Exception e) {
			log.debug("error " + e.getMessage());
		}

		return true;
	}

	public List ordenaMensajePatron() {
		List lista = new ArrayList();
		Map valor0 = (Map) arbolMensaje.get(0);
		lista.add(valor0);
		for (int i = 0; i < listSecciones.size(); i++) {
			Map seccion = (Map) listSecciones.get(i);
			lista.add(seccion);

			for (int j = 0; j < arbolMensajeTemp.size(); j++) {
				Map msgP = (Map) arbolMensajeTemp.get(j);
				String oidSeccion = (String) msgP.get("oidSeccion");
				if (seccion.get("oid").toString().equals(oidSeccion))
					lista.add(msgP);
			}
		}
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMENPatronMensajeOrdenaForm form = (MantenimientoMENPatronMensajeOrdenaForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoMENPatronMensajeOrdenaForm.cabecera.insert";
		} else {
			return "mantenimientoMENPatronMensajeOrdenaForm.cabecera.insert";
		}
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		try {
			obtenerData();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void obtenerData() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'Ordena' method");
		}
		MantenimientoMENPatronMensajeOrdenaForm f = new MantenimientoMENPatronMensajeOrdenaForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		List resultado = new ArrayList();

		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map hmap = new HashMap();
		hmap.put("codigoPais", codigoPais);
		this.msgMensajeDocumentoList = service.getDocumentosMensaje(hmap);

		f.setCodigoPais(pais.getCodigo());
		f.setCampanha((String) data.get("campanhaProceso"));

		f.setCodigoDocumento(String.valueOf(data.get("codigoDocumento")));
		// armamos la lista para quea pintado como arbol d etres niveles
		// campanhaProceso
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("campanhaProceso", data.get("campanhaProceso"));
		criteria.put("codigoDocumento", data.get("codigoDocumento"));
		List listDocumentos = service.getDocumentosPatron(criteria);// todos
																	// los
																	// documens
																	// de la
																	// campanha
		List listSecciones = null;
		Iterator it = listDocumentos.iterator();
		while (it.hasNext()) {
			Map m = (Map) it.next();
			resultado.add(m);// anhadimos el documento nivel 0
			String codigoDocumento = String.valueOf(m.get("oid"));
			criteria.put("codigoDocumento", codigoDocumento);
			// por cada documento anhadimo sus secciones
			listSecciones = service.getSeccionDocumentoPatron(criteria);// todos
																		// los
																		// secciones
																		// del
																		// documento
																		// y
																		// campanha
			Iterator it2 = listSecciones.iterator();
			while (it2.hasNext()) {
				Map beanSecc = (Map) it2.next();
				resultado.add(beanSecc);// anhadimos la seccion nivel 1
				String oidSeccion = String.valueOf(beanSecc.get("oid"));
				criteria.put("oidSeccion", oidSeccion);
				List listMensajes = service
						.getMensajesSeccionDocumentoPatron(criteria);
				resultado.addAll(listMensajes);
			}
		}

		if (resultado.size() <= 3) {
			throw new Exception(
					this.getResourceMessage("mantenimientoMENPatronMensajeOrdenaForm.validad.msg"));

		}
		// obtenemos los mensajes disponibles: continene aquellos mensajes
		// que estan en la entidad mensajes y no estan en
		// en la entidad mensaje patron
		List listMensajesDisponibles = service.getMensajesDisponibles(criteria);
		log.debug("listMensajesDisponibles " + listMensajesDisponibles.size());
		this.listMensajesDisponibles = listMensajesDisponibles;
		this.listSecciones = listSecciones;
		this.arbolMensaje = resultado;
		this.arbolMensajeTemp = insertarSeccionIndicadores(listSecciones);
		formMantenimiento = f;
		this.redireccionarPagina("mantenimientoMENPatronMensajeOrdenaForm");

		// PagedListHolder listHolder = new PagedListHolder(resultado);
		log.debug("resultado " + resultado.size());

	}

	public List insertarSeccionIndicadores(List listSecciones) {
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map criteria = new HashMap();
		List lista = new ArrayList();
		criteria.put("codigoPais", codigoPais);
		criteria.put("campanhaProceso", data.get("campanhaProceso"));
		criteria.put("codigoDocumento", data.get("codigoDocumento"));
		for (int i = 0; i < listSecciones.size(); i++) {
			Map map = (Map) listSecciones.get(i);
			String oidSeccion = map.get("oid").toString();
			criteria.put("oidSeccion", oidSeccion);
			List mensajesByPatron = service
					.getMensajesSeccionDocumentoPatron(criteria);
			for (int j = 0; j < mensajesByPatron.size(); j++) {
				Map map2 = (Map) mensajesByPatron.get(j);
				map2.put("oidSeccion", oidSeccion);
				map2.put("indAccion", "0");
				map2.put("indModi", "0");
				lista.add(map2);
			}
		}
		return lista;
	}

	public void ordenarTopSeccion(ActionEvent event) {
		MantenimientoMENPatronMensajeOrdenaForm f = (MantenimientoMENPatronMensajeOrdenaForm) this.formMantenimiento;
		String[] val = f.getSeccion();

		if (val.length > 1) {
			addError(
					"Error",
					this.getResourceMessage("mantenimientoMENPatronMensajeOrdenaForm.seleccion.seccion.unica"));
		} else {
			List lista = new ArrayList();
			int j = 0;
			for (int i = 0; i < listSecciones.size(); i++) {
				Map data = (Map) listSecciones.get(i);
				if (data.get("oid").toString().equals(val[0])) {
					j = i;
					break;
				}
			}
			Map data = new HashMap();
			for (int i = 0; i < listSecciones.size(); i++) {

				if (i == j) {
					data = (Map) listSecciones.get(i - 1);
				} else if (i == j - 1) {
					data = (Map) listSecciones.get(i + 1);
				} else {
					data = (Map) listSecciones.get(i);
				}
				lista.add(data);
			}

			modificarOrdenSeccion(lista);
			this.listSecciones = lista;

		}
	}

	public void ordenarBottomSeccion(ActionEvent event) {
		MantenimientoMENPatronMensajeOrdenaForm f = (MantenimientoMENPatronMensajeOrdenaForm) this.formMantenimiento;
		String[] val = f.getSeccion();
		if (val.length > 1) {
			addError(
					"Error",
					this.getResourceMessage("mantenimientoMENPatronMensajeOrdenaForm.seleccion.seccion.unica"));
		} else {
			List lista = new ArrayList();
			int j = 0;
			for (int i = 0; i < listSecciones.size(); i++) {
				Map data = (Map) listSecciones.get(i);
				if (data.get("oid").toString().equals(val[0])) {
					j = i;
					break;
				}
			}
			Map data = new HashMap();
			for (int i = 0; i < listSecciones.size(); i++) {

				if (i == j) {
					data = (Map) listSecciones.get(i + 1);
				} else if (i == j + 1) {
					data = (Map) listSecciones.get(i - 1);
				} else {
					data = (Map) listSecciones.get(i);
				}
				lista.add(data);
			}

			modificarOrdenSeccion(lista);
			this.listSecciones = lista;

		}
	}

	public void ordenarTopMensaje(ActionEvent event) {
		MantenimientoMENPatronMensajeOrdenaForm f = (MantenimientoMENPatronMensajeOrdenaForm) this.formMantenimiento;
		String codSeccion = f.getSeccion()[0];
		String[] val = f.getMensajes();
		try {
			if (val.length > 1) {
				addError(
						"Error",
						this.getResourceMessage("mantenimientoMENPatronMensajeOrdenaForm.seleccion.seccion.unica"));
			} else {
				List lista = new ArrayList();
				// arbolMensajeTemp = new ArrayList();
				int j = 0;
				for (int i = 0; i < arbolMensajeTemp.size(); i++) {
					Map data = (Map) arbolMensajeTemp.get(i);
					if (data.get("oid").toString().equals(val[0])) {
						j = i;
						break;
					}
				}
				Map data = new HashMap();
				for (int i = 0; i < arbolMensajeTemp.size(); i++) {

					if (i == j) {
						data = (Map) arbolMensajeTemp.get(i - 1);
					} else if (i == j - 1) {// COGE EL VALOR SUPERIOR
						data = (Map) arbolMensajeTemp.get(i + 1);
						if (!data.get("oidSeccion").toString()
								.equals(codSeccion))
							throw new Exception();
					} else {
						data = (Map) arbolMensajeTemp.get(i);
					}
					lista.add(data);
				}

				modificarOrdenMensaje(lista);
				arbolMensajeTemp = lista;
				List listMensaje = new ArrayList();
				for (int k = 0; k < lista.size(); k++) {
					Map map = (Map) lista.get(k);
					if (map.get("oidSeccion").equals(f.getSeccion()[0]))
						listMensaje.add(map);
				}

				this.listMensajePatron = listMensaje;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void modificarOrdenSeccion(List lista) {
		int k = 0;
		for (int i = 0; i < arbolMensaje.size(); i++) {
			Map data = (Map) arbolMensaje.get(i);
			if (data.get("nivel").equals("1")) {
				Map data2 = (Map) lista.get(k);
				if (data.get("oid").toString() == data2.get("oid").toString()) {
					data2.remove("indModi");
					data2.put("indModi", "0");
				} else {
					data2.remove("indModi");
					data2.put("indModi", "1");
				}

				k++;
			}
		}
	}

	public void modificarOrdenMensaje(List lista) {
		int k = 0;
		for (int i = 0; i < arbolMensaje.size(); i++) {
			Map data = (Map) arbolMensaje.get(i);
			if (data.get("nivel").equals("2")) {
				Map data2 = (Map) lista.get(k);
				if (data.get("oid").toString() == data2.get("oid").toString()) {

				} else {
					data2.remove("indModi");
					data2.put("indModi", "1");
				}

				k++;
			}
		}
	}

	public void ordenarBottomMensaje(ActionEvent event) {
		MantenimientoMENPatronMensajeOrdenaForm f = (MantenimientoMENPatronMensajeOrdenaForm) this.formMantenimiento;
		String codSeccion = f.getSeccion()[0];
		String[] val = f.getMensajes();
		try {
			if (val.length > 1) {
				addError(
						"Error",
						this.getResourceMessage("mantenimientoMENPatronMensajeOrdenaForm.seleccion.seccion.unica"));
			} else {
				List lista = new ArrayList();
				// arbolMensajeTemp = new ArrayList();
				int j = 0;
				for (int i = 0; i < arbolMensajeTemp.size(); i++) {
					Map data = (Map) arbolMensajeTemp.get(i);
					if (data.get("oid").toString().equals(val[0])) {
						j = i;
						break;
					}
				}
				Map data = new HashMap();
				for (int i = 0; i < arbolMensajeTemp.size(); i++) {

					if (i == j) {
						data = (Map) arbolMensajeTemp.get(i + 1);
					} else if (i == j + 1) {// COGE EL VALOR SUPERIOR
						data = (Map) arbolMensajeTemp.get(i - 1);
						if (!data.get("oidSeccion").toString()
								.equals(codSeccion))
							throw new Exception();
					} else {
						data = (Map) arbolMensajeTemp.get(i);
					}
					lista.add(data);
				}

				modificarOrdenMensaje(lista);
				arbolMensajeTemp = lista;
				List listMensaje = new ArrayList();
				for (int k = 0; k < lista.size(); k++) {
					Map map = (Map) lista.get(k);
					if (map.get("oidSeccion").equals(f.getSeccion()[0]))
						listMensaje.add(map);
				}

				this.listMensajePatron = listMensaje;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void loadMensajesBySeccion(ValueChangeEvent val) {
		String[] valor = (String[]) val.getNewValue();
		List lista = new ArrayList();
		for (int j = 0; j < arbolMensajeTemp.size(); j++) {
			Map map = (Map) arbolMensajeTemp.get(j);
			String ver = (String) map.get("elimina");
			if (map.get("oidSeccion").equals(valor[0]) && ver == null)
				lista.add(map);
		}
		this.listMensajePatron = lista;
	}

	public void incluirRigth(ActionEvent event) {
		MantenimientoMENPatronMensajeOrdenaForm f = (MantenimientoMENPatronMensajeOrdenaForm) this.formMantenimiento;
		String[] val = f.getMensajes();
		for (int i = 0; i < listMensajePatron.size(); i++) {
			Map data = (Map) listMensajePatron.get(i);
			if (data.get("oid").toString().equals(val[0])) {
				listMensajePatron.remove(data);
				listMensajesDisponibles.add(data);
				arbolMensajeTemp.remove(data);
				data.put("indAccion", "0");
				data.put("indModi", "2");
				data.put("elimina", "1");
				arbolMensajeTemp.add(data);
				break;
			}
		}
	}

	public void incluirLeft(ActionEvent event) {
		MantenimientoMENPatronMensajeOrdenaForm f = (MantenimientoMENPatronMensajeOrdenaForm) this.formMantenimiento;
		if (f.getSeccion().length > 0) {
			String[] val = f.getMensajesDisponibles();
			for (int i = 0; i < listMensajesDisponibles.size(); i++) {
				Map data = (Map) listMensajesDisponibles.get(i);
				if (data.get("oid").toString().equals(val[0])) {
					listMensajesDisponibles.remove(data);
					data.remove("oidSeccion");
					data.remove("elimina");
					data.put("oidSeccion", f.getSeccion()[0]);
					data.put("indAccion", "1");
					data.put("indModi", "0");
					listMensajePatron.add(data);

					// SI AL MOMENTO DE ELIMINAR Y VOLVER
					// AGREGAR VERIFICA SI YA EXISTIA EN LA LISTA ACTUAL
					// PARA QUE NO AGREGUE EN LA LISTA TEMPORAL
					boolean bexiste = false;
					for (int w = 0; w < arbolMensaje.size(); w++) {
						Map dataw = (Map) arbolMensaje.get(w);
						if (dataw.get("oid").toString()
								.equals(data.get("oid").toString())) {
							bexiste = true;
						}
					}
					if (bexiste) {
						// SI EXISTIA EN LA LISTA ACTUAL
						// SU INDICADOR ACTIVO VUELVE A "0"
						arbolMensajeTemp.remove(data);
						data.put("indAccion", "0");
						arbolMensajeTemp.add(data);
					} else
						arbolMensajeTemp.add(data);

					// AL MOMENTO DE INCLUIR UNA NUEVA DATA LO ORDENA POR
					// SECCION
					List lista = new ArrayList();
					for (int z = 0; z < arbolMensajeTemp.size(); z++) {
						Map map = (Map) arbolMensajeTemp.get(z);
						if (map.get("oidSeccion").toString()
								.equals(f.getSeccion()[0])) {
							lista.add(map);
						}
					}

					for (int z = 0; z < arbolMensajeTemp.size(); z++) {
						Map map = (Map) arbolMensajeTemp.get(z);
						if (!map.get("oidSeccion").toString()
								.equals(f.getSeccion()[0])) {
							lista.add(map);
						}
					}

					arbolMensajeTemp = lista;
					break;
				}
			}
		}
	}

	/**
	 * @return the data
	 */
	public Map getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Map data) {
		this.data = data;
	}

	/**
	 * @return the listMensajesDisponibles
	 */
	public List getListMensajesDisponibles() {
		return listMensajesDisponibles;
	}

	/**
	 * @param listMensajesDisponibles
	 *            the listMensajesDisponibles to set
	 */
	public void setListMensajesDisponibles(List listMensajesDisponibles) {
		this.listMensajesDisponibles = listMensajesDisponibles;
	}

	/**
	 * @return the msgMensajeDocumentoList
	 */
	public List getMsgMensajeDocumentoList() {
		return msgMensajeDocumentoList;
	}

	/**
	 * @param msgMensajeDocumentoList
	 *            the msgMensajeDocumentoList to set
	 */
	public void setMsgMensajeDocumentoList(List msgMensajeDocumentoList) {
		this.msgMensajeDocumentoList = msgMensajeDocumentoList;
	}

	/**
	 * @return the arbolMensaje
	 */
	public List getArbolMensaje() {
		return arbolMensaje;
	}

	/**
	 * @param arbolMensaje
	 *            the arbolMensaje to set
	 */
	public void setArbolMensaje(List arbolMensaje) {
		this.arbolMensaje = arbolMensaje;
	}

	/**
	 * @return the listSecciones
	 */
	public List getListSecciones() {
		return listSecciones;
	}

	/**
	 * @param listSecciones
	 *            the listSecciones to set
	 */
	public void setListSecciones(List listSecciones) {
		this.listSecciones = listSecciones;
	}

	/**
	 * @return the listMensajePatron
	 */
	public List getListMensajePatron() {
		return listMensajePatron;
	}

	/**
	 * @param listMensajePatron
	 *            the listMensajePatron to set
	 */
	public void setListMensajePatron(List listMensajePatron) {
		this.listMensajePatron = listMensajePatron;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the arbolMensajeTemp
	 */
	public List getArbolMensajeTemp() {
		return arbolMensajeTemp;
	}

	/**
	 * @param arbolMensajeTemp
	 *            the arbolMensajeTemp to set
	 */
	public void setArbolMensajeTemp(List arbolMensajeTemp) {
		this.arbolMensajeTemp = arbolMensajeTemp;
	}

}
