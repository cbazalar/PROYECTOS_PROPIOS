package biz.belcorp.ssicc.web.sisicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazEDURecepcionarConsultoraEstablecidasForm;


@ManagedBean
@SessionScoped
public class InterfazEDURecepcionarConsultoraEstablecidasAction extends BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3109730785885097499L;

	List listaEmpresas;
	
	
	
	/**
	 * @return the listaEmpresas
	 */
	public List getListaEmpresas() {
		return listaEmpresas;
	}

	/**
	 * @param listaEmpresas the listaEmpresas to set
	 */
	public void setListaEmpresas(List listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazEDURecepcionarConsultoraEstablecidasForm objForm = new InterfazEDURecepcionarConsultoraEstablecidasForm();
		return objForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		try {
			InterfazEDURecepcionarConsultoraEstablecidasForm f = (InterfazEDURecepcionarConsultoraEstablecidasForm) this.formInterfaz;
			if (log.isDebugEnabled()) {
				log.debug("Entering 'setViewAttributes' method");
			}
			loadCombos(null);
			//List listaEmpresa = (List) session.getAttribute(Constants.EDU_EMPRESA_COMERCIALIZADORA_LIST);
			if ((this.listaEmpresas!=null) && (this.listaEmpresas.size() > 0)){
				EmpresaComercializadora empresa = new EmpresaComercializadora();
				empresa = (EmpresaComercializadora) this.listaEmpresas.get(0);
				//session.setAttribute("EMPRESA_DEFAULT",empresa.getCodigoEmpresa());
				f.setCodigoEmpresa(empresa.getCodigoEmpresa());
			}
			//session.setAttribute(Constants.EDU_CURSO_LIST, new ArrayList());
		} catch (Exception e) {
			log.error("error en interfaz recepcionar consultora a demanda "+e.getMessage());
		}
	}


	private void loadCombos(HttpServletRequest request)throws Exception {
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		this.listaEmpresas = siccService.getEmpresasComercializadorasByPais(parametroEmpresa);
		//session.removeAttribute(Constants.EDU_EMPRESA_COMERCIALIZADORA_LIST);
		//session.setAttribute(Constants.EDU_EMPRESA_COMERCIALIZADORA_LIST, siccService.getEmpresasComercializadorasByPais(parametroEmpresa));
		

	}

}
