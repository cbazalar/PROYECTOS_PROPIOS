package biz.belcorp.ssicc.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Calendario;
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.service.bas.MantenimientoBASParametroPaisService;
import biz.belcorp.ssicc.service.spusicc.ventas.ProcesoVENService;
import biz.belcorp.ssicc.web.form.MantenimientoBASParametroPaisForm;
import biz.belcorp.ssicc.web.form.MantenimientoBASParametroPaisSearchForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.ventas.form.MantenimientoBASFeriadoForm;

@ManagedBean
@SessionScoped
public class MantenimientoBASParametroPaisSearchAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2182745424800699888L;
	private List mantenimientoParametroPaisList;
	
	protected boolean idAnioGenerado = false;
	private List allSistemas;


	public List getMantenimientoParametroPaisList() {
		return mantenimientoParametroPaisList;
	}

	public void setMantenimientoParametroPaisList(
			List mantenimientoParametroPaisList) {
		this.mantenimientoParametroPaisList = mantenimientoParametroPaisList;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoBASParametroPaisForm";
	}
	
	@Override
	protected String getSalirForward() {		
		return "mantenimientoBASParametroPaisList";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {		
		MantenimientoBASParametroPaisSearchForm e = new MantenimientoBASParametroPaisSearchForm();
		return e;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		
		MantenimientoBASParametroPaisService service = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");		
		MantenimientoBASParametroPaisSearchForm f = (MantenimientoBASParametroPaisSearchForm) formBusqueda;
	
		BASParametroPais bean = new BASParametroPais();
    	BeanUtils.copyProperties(bean,f);		
        if (f.getCodigoSistema().equals(Constants.FORMATEAR_TODOS)) {
        	bean.setCodigoPais(null);
        	bean.setCodigoParametro(null);
        	bean.setCodigoSistema(null);
		}
		List lista = service.getParametroPais(bean);
				
		return lista;
	}
			
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		BASParametroPais sistemabusqueda = (BASParametroPais)this.beanRegistroSeleccionado;
		try {
			
		String codigoPais = sistemabusqueda.getCodigoPais();
    	String codigoSistema = sistemabusqueda.getCodigoSistema();
    	String codigoParametro = sistemabusqueda.getCodigoParametro();
	
		if (codigoPais != null && codigoSistema != null && codigoParametro != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + codigoPais);
			}			
				
		//		String[] cads = StringUtils.split(id, '|');
				MantenimientoBASParametroPaisService service = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");
				BASParametroPais baspp = new BASParametroPais();
				baspp.setCodigoPais(codigoPais);
				baspp.setCodigoSistema(codigoSistema);
				baspp.setCodigoParametro(codigoParametro);
				List list = service.getParametroPais(baspp);
				BASParametroPais baspp1 = (BASParametroPais) list.get(0);
			
				service.deleteParametroPais(baspp1);
//				BeanUtils.copyProperties(f, baspp);
//				this.setFindAttributes(request,f);	
//				
//				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
//					"mantenimientoBASParametroPaisForm.deleted"));
//				saveMessages(request.getSession(), messages);
	
		}
			}catch (Exception e) {
				String error = e.getMessage();
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				throw new Exception(this.getResourceMessage("errors.detail",new Object[]{error}));
			}		
		return true;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		Pais pais = mPantallaPrincipalBean.getCurrentCountry();
		//Usuario usuario = getUsuario(session);

		SistemaService sistemaService = (SistemaService) getBean("sisicc.sistemaService");
		
		Sistema sistema = new Sistema();
	    sistema.setCodigoPais(pais.getCodigo());
	    mantenimientoParametroPaisList = sistemaService.getSistemas(sistema); //llenar el combo
//		request.getSession().setAttribute(Constants.ALL_SISTEMAS,
//				sistemaService.getSistemas(sistema));
	}
	
	public void setIdAnioGenerado(boolean idAnioGenerado) {
		this.idAnioGenerado = idAnioGenerado;
	}

	public boolean isIdAnioGenerado() {
		return idAnioGenerado;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}
		MantenimientoBASParametroPaisForm f = (MantenimientoBASParametroPaisForm) this.formMantenimiento;
		MantenimientoBASParametroPaisService service = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");		
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry(); // se corrigio el valor nulo de pais
		BASParametroPais pp = new BASParametroPais();
		f.setCodigoPais(pais.getCodigo());  // se corrigio el valor nulo de pais
		try {
			BeanUtils.copyProperties(pp, f);
			AuditInfo audi = usuario.getAuditInfo();
			if (!f.isNewRecord()) {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA MODIFICACION");
				};
				audi.setUpdatedBy(usuario.getLogin());
				pp.setAuditInfo(audi);
				service.updateParametroPais(pp);
			} else {
				if (log.isDebugEnabled()) {
					log.debug("EN EL CASO QUE SEA CREACION");
				}
			
			 /*   List list = service.getParametroPais(pp);
				if (CollectionUtils.size(list) > 0) {
					messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
							"errors.detail","mantenimientoBASParametroPaisForm.violentionPK"));
					saveErrors(request, messages);	
				}else{*/
				 audi.setCreatedBy(usuario.getLogin());
				 pp.setAuditInfo(audi);
				 service.insertParametroPais(pp);
				//}
			}
		}catch (Exception e) {
            throw new Exception(this.getResourceMessage(e.getMessage()));
		}
		return true;
	}
		   
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoBASParametroPaisForm f = (MantenimientoBASParametroPaisForm) this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if(isNew){
			return "mantenimientoBASParametroPaisForm.add";
		}else{
			return "mantenimientoBASParametroPaisForm.updated";
		}	
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		MantenimientoBASParametroPaisForm f = new MantenimientoBASParametroPaisForm();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if (f.isNewRecord()) {
			f.setCodigoParametro(StringUtils.EMPTY);
			f.setCodigoSistema(Constants.FORMATEAR_TODOS);
			f.setValorParametro(StringUtils.EMPTY);
			f.setNombreParametro(StringUtils.EMPTY);
			f.setEstado(Constants.ESTADO_ACTIVO);
			f.setObsParametro(StringUtils.EMPTY);
		}
		
		
		SistemaService sistemaService = (SistemaService) getBean("sisicc.sistemaService");
		
		Sistema sistema = new Sistema();
	    sistema.setCodigoPais(pais.getCodigo());
	    
	    mantenimientoParametroPaisList = sistemaService.getSistemas(sistema);
	    
	    BASParametroPais sistemabusqueda = (BASParametroPais)this.beanRegistroSeleccionado;
	    
	    if (!this.accion.equals(this.ACCION_NUEVO) )
	    {
			String codigoPais = sistemabusqueda.getCodigoPais();
	    	String codigoSistema = sistemabusqueda.getCodigoSistema();
	    	String codigoParametro = sistemabusqueda.getCodigoParametro();
	    	
			 if (codigoPais != null && codigoSistema != null && codigoParametro != null ) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigoPais);
				}
//				String[] cads = StringUtils.split(id, '|');
				MantenimientoBASParametroPaisService service = (MantenimientoBASParametroPaisService) getBean("bas.mantenimientoBASParametroPaisService");
				BASParametroPais baspp = new BASParametroPais();
				baspp.setCodigoPais(codigoPais);
				baspp.setCodigoSistema(codigoSistema);
				baspp.setCodigoParametro(codigoParametro);
				List list = service.getParametroPais(baspp);
				BASParametroPais baspp1 = (BASParametroPais) list.get(0);
								
				BeanUtils.copyProperties(f, baspp1);	
				f.setNewRecord(false);
			}
	    }
	    
	    return f;		
	}
	
	
}
