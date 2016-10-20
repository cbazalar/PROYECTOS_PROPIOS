package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.PaisService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.MantenimientoBASParametroSegmentacionForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes"})
public class MantenimientoBASParametroSegmentacionAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2670609490950526638L;

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
		MantenimientoBASParametroSegmentacionForm searchForm = new MantenimientoBASParametroSegmentacionForm();
		return searchForm;
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
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonBuscar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarListaBusqueda = false;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		//Usuario usuario = getUsuario(session);
		MantenimientoBASParametroSegmentacionForm f = (MantenimientoBASParametroSegmentacionForm) this.formBusqueda;
		PaisService paisService = (PaisService) getBean("paisService");
		List lista = paisService.getParametrosSegmentacionByPais(pais.getCodigo());
		if (lista != null && lista.size() > 0) {
			Map parametros = (Map) lista.get(0);
			BeanUtils.populate(f, parametros);
		}		
	}
	
	public void guardar(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'guardar' method");
		}
		try {
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoBASParametroSegmentacionForm f = (MantenimientoBASParametroSegmentacionForm) this.formBusqueda;

			Map criteria = BeanUtils.describe(f);
			PaisService paisService = (PaisService) getBean("paisService");
			List lista = paisService.getParametrosSegmentacionByPais(pais.getCodigo());
			if (lista != null && lista.size() > 0) {
				paisService.updateParametrosSegmentacion(criteria);
				this.addInfo("", this.getResourceMessage("mantenimientoBASParametroSegmentacionForm.updated"));
			} else {
				paisService.insertParametrosSegmentacion(criteria);
				this.addInfo("", this.getResourceMessage("mantenimientoBASParametroSegmentacionForm.add"));
			}

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
}