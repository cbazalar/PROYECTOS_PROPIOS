package biz.belcorp.ssicc.web.spusicc.mae.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.mae.model.EstadoCivil;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEEntidadGenericaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEEntidadGenericaEstadoCivilForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoMAEEntidadGenericaEstadoCivilAction extends BaseMantenimientoSearchAbstractAction 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795458685125820331L;

	private String nombreEntidad;

	@Override
	protected String getSalirForward() 
	{
		MantenimientoMAEEntidadGenericaSearchAction man = this.findManageBean("mantenimientoMAEEntidadGenericaSearchAction");
		man.find();
		return "mantenimientoMAEEntidadGenericaList";
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
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoMAEEntidadGenericaEstadoCivilAction");
		}

		MantenimientoMAEEntidadGenericaEstadoCivilForm f = (MantenimientoMAEEntidadGenericaEstadoCivilForm) this.formMantenimiento;
		MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService) getBean("spusicc.mantenimientoMAEEntidadGenericaService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		boolean isNew = f.isNewRecord();

		EstadoCivil estadoCivil = new EstadoCivil();
		BeanUtils.copyProperties(estadoCivil, f);

		try {
			if (isNew) {
				System.out.println("isnew");
				if (!service.getExisteTipoEstadoCivil(estadoCivil)) {
					service.insertEstadoCivil(estadoCivil, usuario);
				} else {
					this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
					return false;
				}
			} else {

				Map params = new HashMap();
				params.put("oidEstadoCivil", f.getOidEstadoCivil());

				EstadoCivil eCivil = service.getTipoEstadoCivil(params);

				if (eCivil.getCodigo().equals(f.getCodigo())) {
					if (service.getExisteTipoEstadoCivil(estadoCivil)) {
						service.updateEstadoCivil(estadoCivil, usuario);
					} else {
						this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
						return false;
					}
				} else {
					if (!service.getExisteTipoEstadoCivil(estadoCivil)) {
						service.updateEstadoCivil(estadoCivil, usuario);
					} else {
						this.addError("Error: ", this.getResourceMessage("mantenimientoMAEEntidadGenericaForm.error"));
						return false;
					}
				}
			}
		} catch (InvalidIdentifierException iie) {
			String codigo = iie.getIdentifier().toString();
			this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[] { codigo }));
			return false;
		} catch (InvalidDescriptionException ide) {
			String descripcion = ide.getDescription();
			this.addError("Error: ", this.getResourceMessage("errors.invalid.description", new Object[] { descripcion }));
			return false;
		}

		this.invocarFindLuegoGrabar = false;
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

	}

	public void inicializaPantallaMantenimiento() {
		try {
			MantenimientoMAEEntidadGenericaEstadoCivilForm f = new MantenimientoMAEEntidadGenericaEstadoCivilForm();
			MantenimientoMAEEntidadGenericaService service = (MantenimientoMAEEntidadGenericaService) getBean("spusicc.mantenimientoMAEEntidadGenericaService");

			// RESET
			f.setCodigo("");
			f.setEstado(Constants.ACTIVO);
			f.setDescripcion("");
			// FIN RESET
			f.setNombreEntidad(getNombreEntidad());

			this.mostrarBotonSave = true;
			this.formMantenimiento = f;

			if (this.accion.equals(this.ACCION_MODIFICAR) || this.accion.equals(this.ACCION_CONSULTAR)) {												
				Map bean = (Map)this.beanRegistroSeleccionado;
				String id = bean.get("oidEstadoCivil").toString(); 
				
				if(StringUtils.isNotBlank(id)){
					
					Map params = new HashMap();
					params.put("oidEstadoCivil", id);
					
					EstadoCivil estadoCivil = service.getTipoEstadoCivil(params);
					BeanUtils.copyProperties(f, estadoCivil);
					f.setNombreEntidad(getNombreEntidad());
					f.setOidEstadoCivil(id);
					f.setNewRecord(false);
					this.formMantenimiento = f;
					
					if(this.accion.equals(this.ACCION_CONSULTAR))
						this.mostrarBotonSave = false;
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoMAEEntidadGenericaEstadoCivilForm f = (MantenimientoMAEEntidadGenericaEstadoCivilForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if (registro)
			return "mantenimientoMAEEntidadGenericaForm.created";
		else
			return "mantenimientoMAEEntidadGenericaForm.updated";
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

}
