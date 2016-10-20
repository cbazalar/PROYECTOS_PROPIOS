package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCCondonacionDeudasCastigadasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.MantenimientoCCCCondonacionDeudasCastigadasSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoCCCCondonacionDeudasCastigadasSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1920349948982214046L;
	private List cccCondonacionDeudasCastigadasList;
	private boolean validarBoolean;

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
		MantenimientoCCCCondonacionDeudasCastigadasSearchForm form = new MantenimientoCCCCondonacionDeudasCastigadasSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("setFindAttributes - MantenimientoCondonacionDeudasCastigadasSearchAction");
		}

		MantenimientoCCCCondonacionDeudasCastigadasSearchForm f = (MantenimientoCCCCondonacionDeudasCastigadasSearchForm) this.formBusqueda;
		Map params = BeanUtils.describe(f);
		MantenimientoCCCCondonacionDeudasCastigadasService service = (MantenimientoCCCCondonacionDeudasCastigadasService) getBean("spusicc.mantenimientoCCCCondonacionDeudasCastigadasService");
		List lista = service.getCondonacionDeudasCastigadasList(params);

		cccCondonacionDeudasCastigadasList = lista;

		return lista;
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
			log.debug("setViewAttributes - MantenimientoCondonacionDeudasCastigadasSearchAction");
		}
		MantenimientoCCCCondonacionDeudasCastigadasSearchForm f = (MantenimientoCCCCondonacionDeudasCastigadasSearchForm) this.formBusqueda;
		f.setCodigoConsultora("");
		f.setCedulaIdentidad("");
		this.cccCondonacionDeudasCastigadasList = new ArrayList();

		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;

	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void ejecutar(ActionEvent event) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("JFA Entering 'ejecutar' method");
		}
		if (!this.verificarRegistroSeleccionado())
			return;
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		String id = "";
        id=sistemabusqueda.get("oid").toString();
 
		MantenimientoCCCCondonacionDeudasCastigadasService service = (MantenimientoCCCCondonacionDeudasCastigadasService) getBean("spusicc.mantenimientoCCCCondonacionDeudasCastigadasService");

		Map datos = new HashMap();

		if (id != null) {
			if (log.isDebugEnabled()) {
				log.debug("Id seleccionado de la lista: " + id);
			}
			datos.put("oid", Integer.parseInt(id));
		}
	

		if (log.isDebugEnabled()) {
			log.debug("JFA Parameter Map : " + datos.toString());
		}

		service.executeCondonacionDeudaCastigo(datos);

		addError(
				"Mensaje",
				this.getResourceMessage("mantenimientoCCCCondonacionDeudasCastigadasSearchForm.updated"));
		this.cccCondonacionDeudasCastigadasList.remove(sistemabusqueda);
		setFindAttributes();		
		// saveMessages(request.getSession(), messages);
		// setFindAttributes( request, form);
		// return mapping.findForward("view");
	}

	public void validaSeleccion(ValueChangeEvent val) {
		MantenimientoCCCCondonacionDeudasCastigadasSearchForm f = (MantenimientoCCCCondonacionDeudasCastigadasSearchForm) this.formBusqueda;
		String valor = val.getNewValue().toString();
		if (valor.equals("CC")) {
			validarBoolean = false;
			f.setCedulaIdentidad(null);
		} else {
			validarBoolean = true;
			f.setCodigoConsultora(null);
		}
	}

	public List getCccCondonacionDeudasCastigadasList() {
		return cccCondonacionDeudasCastigadasList;
	}

	public void setCccCondonacionDeudasCastigadasList(
			List cccCondonacionDeudasCastigadasList) {
		this.cccCondonacionDeudasCastigadasList = cccCondonacionDeudasCastigadasList;
	}

	public boolean isValidarBoolean() {
		return validarBoolean;
	}

	public void setValidarBoolean(boolean validarBoolean) {
		this.validarBoolean = validarBoolean;
	}

}
