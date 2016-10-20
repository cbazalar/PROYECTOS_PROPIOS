package biz.belcorp.ssicc.web._ejemplos;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.web.MPantallaPrincipalBean;
import biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF;
 
@ManagedBean
@SessionScoped
public class MEjemploDataTableParte2 extends MBaseSistemaAbstractJSF {

	private static final long serialVersionUID = -7390354807304299547L;
	private TreeNode rootMenu;  
	private TreeNode selectedNode;  
	private TreeNode[] selectedNodes;  
	
	@ManagedProperty(value="#{mPantallaPrincipalBean}")
	private MPantallaPrincipalBean mPantallaPrincipalBean;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	public void setViewAtributes() {
		log.debug("ini post Construct MEjemploDataTableParte2");
		//this.organizarTreeMenues(this.mPantallaPrincipalBean.getMenues());
		log.debug("fin post Construct MEjemploDataTableParte2");
	}

	/**
	 * Ir a la Pagina Anterior
	 * @return
	 */
	public String irPaginaAnterior() {
		return "ejemploDatatable";
	}
	
	
	/**
     * Obtiene el treeArbol en base al List del Menu
     * @param menues
     */
    private void organizarTreeMenues(List<Menu> menues) {
    	this.rootMenu = new DefaultTreeNode("rootMenu", null);
    	this.rootMenu.setExpanded(true);
    	for (int x = 0; x < menues.size(); x++) {
			Menu m = (Menu) menues.get(x);
			TreeNode tmenu;
			if (m.getMenuPadre() == null) {
				tmenu = new DefaultTreeNode(m, rootMenu);
				tmenu.setExpanded(true);
				List<Menu> listasubmenu = m.getSubmenues();
				for (int y = 0; y < listasubmenu.size(); y++) {
					Menu submenu = (Menu) listasubmenu.get(y);
					TreeNode tsubmenu = new DefaultTreeNode(submenu, tmenu);
					tsubmenu.setExpanded(true);
					List<Menu> listasubsubmenu = submenu.getSubmenues();
					for (int z = 0; z < listasubsubmenu.size(); z++) {
						Menu subsubmenu = (Menu) listasubsubmenu.get(z);
						TreeNode tsubsubmenu = new DefaultTreeNode(subsubmenu, tsubmenu);
						tsubsubmenu.setExpanded(true);
						List<Menu> listasubsubsubmenu = subsubmenu.getSubmenues();
						for (int k = 0; k < listasubsubsubmenu.size(); k++) {
							Menu subsubsubmenu = (Menu) listasubsubsubmenu.get(k);
							TreeNode tsubsubsubmenu = new DefaultTreeNode(subsubsubmenu, tsubsubmenu);
							tsubsubsubmenu.setExpanded(true);
						}
					}	
				}	
			}
		}
    	return;
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
	 * @return the mPantallaPrincipalBean
	 */
	public MPantallaPrincipalBean getmPantallaPrincipalBean() {
		return mPantallaPrincipalBean;
	}

	/**
	 * @param mPantallaPrincipalBean the mPantallaPrincipalBean to set
	 */
	public void setmPantallaPrincipalBean(
			MPantallaPrincipalBean mPantallaPrincipalBean) {
		this.mPantallaPrincipalBean = mPantallaPrincipalBean;
	}
	
    
		
}
