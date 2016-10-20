package biz.belcorp.ssicc.web.spusicc.men.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENPatronMensajeReplicaForm;

@SessionScoped
@ManagedBean
public class MantenimientoMENPatronMensajeReplicaAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8892978242688823167L;
	private String codigoPais;
	private String mensajeReplica;
	private TreeNode rootMenu;
	private TreeNode selectedNode;
	private TreeNode[] selectedNodes;
	private List arbolMensaje;
	Map data;

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
		return false;
	}

	public void grabarReplica(ActionEvent event) throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoMENPatronMensajeReplicaForm f = (MantenimientoMENPatronMensajeReplicaForm) this.formMantenimiento;
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");

		try {
			int[] selectedItems = validarListaReplica();
			String[] selectedItems2 = new String[selectedItems.length];
			Arrays.sort(selectedItems);
			for (int i = 0; i < selectedItems.length; i++) {
				selectedItems2[i]=""+selectedItems[i]+"";
			}			
			List arbol = this.arbolMensaje;
			Map map = new HashMap();
			map.put("codigoPais", codigoPais);
			map.put("login", usuario.getLogin());
			map.put("indexSeleccionados", selectedItems2);
			map.put("arbol", arbol);
			map.put("campanhaOrigen", f.getCampanhaOrigen());
			map.put("campanhaDestino", f.getCampanhaDestino());
			service.replicarPatron(map);
			addInfo("",
					this.getResourceMessage("mantenimientoMENPatronMensajeReplicaForm.cabecera.insert"));
			this.redireccionarPagina("mantenimientoMENPatronMensajeList");
		} catch (Exception e) {
			addError("", obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		MantenimientoMENPatronMensajeReplicaForm f = (MantenimientoMENPatronMensajeReplicaForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");

		if (accion.equals("EXECUTE")) {
			if (StringUtils.isBlank(f.getCampanhaDestino()))
				return "Campaña Destino es un campo requerido";

			int codOri = Integer.parseInt(f.getCampanhaOrigen());
			int codDes = Integer.parseInt(f.getCampanhaDestino());
			if (codDes <= codOri) {
				return this
						.getResourceMessage("mantenimientoMENPatronMensajeReplicaForm.campanha.information");
			}

			if (selectedNodes == null)
				return this
						.getResourceMessage("mantenimientoMENPatronMensajeReplicaForm.seleccionar.item");

			Integer data = ajax.getValidaPatron(f.getCampanhaDestino(),
					f.getCodigoDocumento());
			if (data == 1) {
				mensajeReplica = this
						.getResourceMessage("mantenimientoMENPatronMensajeReplicaForm.update.information");
			} else {
				mensajeReplica = this
						.getResourceMessage("mantenimientoMENPatronMensajeReplicaForm.save.information")
						+ " "
						+ f.getCampanhaDestino()
						+ " "
						+ this.getResourceMessage("mantenimientoMENPatronMensajeReplicaForm.continuar.information");
			}

		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */

	public int[] validarListaReplica() {
		int tamaño = selectedNodes.length;
		int[] selectedItems = new int[tamaño];

		int i = 0;
		if (tamaño == arbolMensaje.size()) {
			for (TreeNode node : selectedNodes) {
				Map data = (Map) node.getData();
				selectedItems[i] = Integer.parseInt(data.get("index").toString());
				i++;
			}

		} else {
			selectedItems = new int[tamaño + 1];
			selectedItems[0] = Integer.parseInt("1");
			i = 1;
			for (TreeNode node : selectedNodes) {
				Map data = (Map) node.getData();
				selectedItems[i] = Integer.parseInt(data.get("index").toString());
				i++;
			}
		}

		return selectedItems;

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
			this.mostrarBotonSave = false;
			obtenerData();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void obtenerData() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'Ordena' method");
		}
		if (log.isDebugEnabled()) {
			log.debug("Entering 'replica' method");
		}
		MantenimientoMENPatronMensajeReplicaForm f = new MantenimientoMENPatronMensajeReplicaForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		List resultado = new ArrayList();

		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		//
		f.setCodigoPais(codigoPais);
		f.setCampanhaOrigen((String) data.get("campanhaProceso"));
		f.setCampanhaDestino("");
		f.setCodigoDocumento(String.valueOf(data.get("codigoDocumento")));
		// armamos la lista para quea pintado como arbol d etres niveles
		// campanhaProceso
		Map criteria = new HashMap();
		criteria.put("campanhaProceso", data.get("campanhaProceso"));
		criteria.put("codigoDocumento", data.get("codigoDocumento"));
		List listDocumentos = service.getDocumentosPatron(criteria);// todos
																	// los
																	// documens
																	// de la
																	// campanha
		Iterator it = listDocumentos.iterator();
		List listSecciones = new ArrayList();
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
		this.formMantenimiento = f;
		this.arbolMensaje = resultado;
		this.organizarTreeMenues(listSecciones);
		this.redireccionarPagina("mantenimientoMENPatronMensajeReplicaForm");

	}

	/**
	 * Obtiene el treeArbol en base al List del Menu
	 * 
	 * @param menues
	 */
	private void organizarTreeMenues(List listSecciones) {
		this.rootMenu = new DefaultTreeNode("rootMenu", null);
		this.rootMenu.setExpanded(true);
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		List documento = new ArrayList();
		Map doc = new HashMap();
		doc.put("codigo", data.get("codigoDocumento"));
		doc.put("descripcion", data.get("descripcionDocumento"));
		documento.add(doc);
		int z = 1;
		for (int i = 0; i < documento.size(); i++) {
			Map docu = (Map) documento.get(i);
			docu.put("index", "" + z + "");
			TreeNode tdocu;
			tdocu = new DefaultTreeNode(docu, rootMenu);
			tdocu.setExpanded(true);
			tdocu.setSelected(true);
			for (int x = 0; x < listSecciones.size(); x++) {
				z++;
				Map m = (Map) listSecciones.get(x);
				m.put("index", "" + z + "");
				TreeNode tmenu;
				tmenu = new DefaultTreeNode(m, tdocu);
				tmenu.setExpanded(false);
				tmenu.setSelected(true);

				Map criteria = new HashMap();
				criteria.put("codigoPais", codigoPais);
				criteria.put("campanhaProceso", data.get("campanhaProceso"));
				criteria.put("codigoDocumento", data.get("codigoDocumento"));
				criteria.put("oidSeccion", m.get("oid").toString());
				List listMensajePatron = service
						.getMensajesSeccionDocumentoPatron(criteria);
				for (int y = 0; y < listMensajePatron.size(); y++) {
					z++;
					Map submenu = (Map) listMensajePatron.get(y);
					submenu.put("index", "" + z + "");
					TreeNode tsubmenu = new DefaultTreeNode(submenu, tmenu);
					tsubmenu.setExpanded(false);
					tsubmenu.setSelected(true);
				}
			}
			z++;
		}

		return;
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
	 * @return the rootMenu
	 */
	public TreeNode getRootMenu() {
		return rootMenu;
	}

	/**
	 * @param rootMenu
	 *            the rootMenu to set
	 */
	public void setRootMenu(TreeNode rootMenu) {
		this.rootMenu = rootMenu;
	}

	/**
	 * @return the selectedNode
	 */
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	/**
	 * @param selectedNode
	 *            the selectedNode to set
	 */
	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	/**
	 * @return the selectedNodes
	 */
	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	/**
	 * @param selectedNodes
	 *            the selectedNodes to set
	 */
	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	/**
	 * @return the mensajeReplica
	 */
	public String getMensajeReplica() {
		return mensajeReplica;
	}

	/**
	 * @param mensajeReplica
	 *            the mensajeReplica to set
	 */
	public void setMensajeReplica(String mensajeReplica) {
		this.mensajeReplica = mensajeReplica;
	}

}
