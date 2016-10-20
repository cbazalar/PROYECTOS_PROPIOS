package org.sistema.sisgen.web._framework.manage;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.StringUtils;
import org.sistema.framework.dao.model.Base;
import org.sistema.framework.web.base.manage.MBaseSistemaAbstractJSF;
import org.sistema.sisgen.web.base.manage.MPantallaPrincipalBean;


public abstract class MBaseSistemaSisgenAbstractJSF extends MBaseSistemaAbstractJSF {
	
    private static final long serialVersionUID = -3678150751042396478L;
    
	@ManagedProperty(value = "#{mPantallaPrincipalBean}")
	protected MPantallaPrincipalBean mPantallaPrincipalBean;
	
	@Override
	protected abstract void setViewAtributes() throws Exception;
	
	/* (non-Javadoc)
	 * @see org.sistema.framework.web.base.manage.MBaseSistemaAbstractJSF#adicionarManageListaSession()
	 */
	protected final void adicionarManageListaSession() {
		List listaManageBeanSession = this.mPantallaPrincipalBean.getListaManageBeanSession();
		String nroSession = (String) this.parametrosPantalla.get("nroSession");
		this.nroSessionManage = nroSession;
		
		String manage = getClass().getSimpleName().toLowerCase().trim();
		
		if (StringUtils.isBlank(nroSession)) return;
		boolean adicionar = true;
		for (int i=1; i<listaManageBeanSession.size(); i++) {
			Base beanSession = (Base) listaManageBeanSession.get(i);
			String bnroSession = beanSession.getCodigo();
			String bmanage = beanSession.getDescripcion();
			
			if (StringUtils.equals(nroSession, bnroSession) && StringUtils.equals(manage, bmanage)) {
				adicionar = false;
			}
		}
		
		if (adicionar) {
			Base mapaSession = new Base();
			mapaSession.setCodigo(nroSession);
			mapaSession.setDescripcion(manage);
			listaManageBeanSession.add(mapaSession);
			this.mPantallaPrincipalBean.setListaManageBeanSession(listaManageBeanSession);
		}
	}

	
	/**
	 * Obtiene el manage de Session MPantallaPrincipalBean
	 * @return
	 */
	protected MPantallaPrincipalBean getMPantallaPrincipalBean() {
		MPantallaPrincipalBean beanPantalla = (MPantallaPrincipalBean) this.findManageBean("mPantallaPrincipalBean");
		return beanPantalla;
	}
	
	
	
	/* GET - SET */
	
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
