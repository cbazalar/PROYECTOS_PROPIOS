package biz.belcorp.ssicc.web.spusicc.men.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.men.MantenimientoMENGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.men.form.MantenimientoMENPatronMensajeConsultaForm;

@SessionScoped
@ManagedBean
public class MantenimientoMENPatronMensajeConsultaAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 526294695075050716L;
	Map data;
	private String codigoPais;
	private TreeNode rootMenu;
	private TreeNode selectedNode;
	private TreeNode[] selectedNodes;
	private List arbolMensaje;
	private List msgMensajeDocumentoList;
	private Object seleccionable;


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
		MantenimientoMENPatronMensajeConsultaForm f = new MantenimientoMENPatronMensajeConsultaForm();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		List resultado = new ArrayList();

		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		Map hmap = new HashMap();
		hmap.put("codigoPais", codigoPais);
		this.msgMensajeDocumentoList = service.getDocumentosMensaje(hmap);
		
		f.setCodigoPais(codigoPais);
		f.setCampanha((String) data.get("campanhaProceso"));

		f.setCodigoDocumento(String.valueOf(data.get("codigoDocumento")));
		// armamos la lista para quea pintado como arbol d etres niveles
		// campanhaProceso
		Map criteria = new HashMap();
		criteria.put("campanhaProceso", data.get("campanhaProceso"));
		criteria.put("codigoDocumento", data.get("codigoDocumento"));
		List listDocumentos = service.getDocumentosPatron(criteria);// todos los
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

		// PagedListHolder listHolder = new PagedListHolder(resultado);
		log.debug("resultado " + resultado.size());

		this.formMantenimiento = f;
		this.arbolMensaje = resultado;
		this.organizarTreeMenues(listSecciones);
		mostrarBotonSave=false;
		this.redireccionarPagina("mantenimientoMENPatronMensajeConsultaForm");

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
		
		int z = 1;
		for (int i = 0; i < 1; i++) {
			Map docu = (Map) arbolMensaje.get(0);
			docu.put("index", "" + z + "");
			TreeNode tdocu;
			tdocu = new DefaultTreeNode(docu, rootMenu);
			tdocu.setExpanded(true);
			for (int x = 0; x < listSecciones.size(); x++) {
				z++;
				Map m = (Map) listSecciones.get(x);
				m.put("index", "" + z + "");
				TreeNode tmenu;
				tmenu = new DefaultTreeNode(m, tdocu);
				tmenu.setExpanded(true);

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
					tsubmenu.setExpanded(true);
				}
			}
			z++;
		}

		return;
	}
	
	public void abrirPopup(ActionEvent event){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'viewPopup' method");
		}
		MantenimientoMENPatronMensajeConsultaForm f = (MantenimientoMENPatronMensajeConsultaForm) this.formMantenimiento;
		MantenimientoMENGenericoService service = (MantenimientoMENGenericoService) getBean("spusicc.mantenimientoMENGenericoService");
		try{
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String oid = externalContext.getRequestParameterMap().get("parametroAccion");
			
			Map map = new HashMap();
			map.put("oid", oid);
			map.put("campanhaProceso", data.get("campanhaProceso"));
			List list = service.getMensajeByOid(map);
			//log.debug("list " +list.size());
			Map msg = (Map)list.get(0);
			f.setDescripcionMensaje((String)msg.get("descripcionMensaje"));
			f.setTextoMensaje((String)msg.get("valorTexto"));
			f.setTextoMensajeHtml((String)msg.get("valorTextoHtml"));
			//log.debug("texto " +f.getTextoMensaje());
			this.getRequestContext().execute("PF('abrirPopup').show()");	
			
		}catch (Exception e) {
			addError("Error", obtieneMensajeErrorException(e));
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
	 * @return the rootMenu
	 */
	public TreeNode getRootMenu() {
		return rootMenu;
	}

	/**
	 * @param rootMenu the rootMenu to set
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
	 * @param selectedNode the selectedNode to set
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
	 * @param selectedNodes the selectedNodes to set
	 */
	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	/**
	 * @return the arbolMensaje
	 */
	public List getArbolMensaje() {
		return arbolMensaje;
	}

	/**
	 * @param arbolMensaje the arbolMensaje to set
	 */
	public void setArbolMensaje(List arbolMensaje) {
		this.arbolMensaje = arbolMensaje;
	}

	/**
	 * @return the msgMensajeDocumentoList
	 */
	public List getMsgMensajeDocumentoList() {
		return msgMensajeDocumentoList;
	}

	/**
	 * @param msgMensajeDocumentoList the msgMensajeDocumentoList to set
	 */
	public void setMsgMensajeDocumentoList(List msgMensajeDocumentoList) {
		this.msgMensajeDocumentoList = msgMensajeDocumentoList;
	}

	/**
	 * @return the seleccionable
	 */
	public Object getSeleccionable() {
		return seleccionable;
	}

	/**
	 * @param seleccionable the seleccionable to set
	 */
	public void setSeleccionable(Object seleccionable) {
		this.seleccionable = seleccionable;
	}
	
	

}
