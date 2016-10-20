package biz.belcorp.ssicc.web.spusicc.lec.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.lec.model.TarjetaPago;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECTarjetaPagoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.lec.form.MantenimientoLECTarjetaPagoForm;
import biz.belcorp.ssicc.web.spusicc.lec.form.MantenimientoLECTarjetaPagoSearchForm;

@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class MantenimientoLECTarjetaPagoSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -1502057419011934473L;
	private List lecTarjetaPagoList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoLECTarjetaPagoList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoLECTarjetaPagoForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {

		MantenimientoLECTarjetaPagoSearchForm m = new MantenimientoLECTarjetaPagoSearchForm();
		return m;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {

		MantenimientoLECTarjetaPagoSearchForm f = (MantenimientoLECTarjetaPagoSearchForm) this.formBusqueda;
		Map params = BeanUtils.describe(f);
		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService");
		List lista = service.getTarjetaPagoByCriteria(params);

		this.lecTarjetaPagoList = lista;

		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {

		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService");

		Map bean = (Map) this.beanRegistroSeleccionado;
		String codigoTarjeta = bean.get("codigoTarjeta").toString();
		TarjetaPago tarjetaPago = new TarjetaPago();
		tarjetaPago = service.getTarjetaPago(codigoTarjeta);

		if (tarjetaPago.getCodigoEstado().equals(Constants.LEST_COD_ESTA)) {
			service.deleteTarjetaPago(tarjetaPago);
		}

		return true;

	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		MantenimientoLECTarjetaPagoForm f = (MantenimientoLECTarjetaPagoForm) this.formMantenimiento;
		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		boolean isNew = f.isNewRecord();

		TarjetaPago tarjetaPago = new TarjetaPago();

		BeanUtils.copyProperties(tarjetaPago, f);

		Map params = BeanUtils.describe(f);

		List lista = service.getTarjetaPagoByCriteria(params);

		if (isNew) {

			if (lista != null && lista.size() > 0) {
				addInfo("Mensaje: ",
						getResourceMessage("mantenimientoLECTarjetaPagoForm.error.duplicado"));

				return false;
			} else {
				service.insertTarjetaPago(tarjetaPago, usuario);

			}
		} else {

			boolean actualizar = true;
			if (lista != null && lista.size() > 0) {
				if (lista.size() == 1) {
					String oid = MapUtils.getString((Map) lista.get(0),
							"numeroTarjeta", "");
					if (StringUtils.equals(oid, tarjetaPago.getNumeroTarjeta())) {
						actualizar = false;
					}

					else {
						actualizar = true;
					}
				} else {
					actualizar = false;
				}
			}

			if (actualizar) {
				service.updateTarjetaPago(tarjetaPago, usuario);
				addInfo("Mensaje: ",
						getReportResourceMessage("mantenimientoLECTarjetaPagoForm.updated"));

			} else {
				addInfo("Mensaje: ",
						getResourceMessage("mantenimientoLECTarjetaPagoForm.error.duplicado"));
				return false;
			}
		}

		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("setEditAttributes - MantenimientoLECTarjetaPagoAction");
		}

		MantenimientoLECTarjetaPagoForm f = new MantenimientoLECTarjetaPagoForm();
		MantenimientoLECTarjetaPagoService service = (MantenimientoLECTarjetaPagoService) getBean("spusicc.mantenimientoLECTarjetaPagoService");

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			Map bean = (Map) this.beanRegistroSeleccionado;
			String id = bean.get("codigoTarjeta").toString();
			this.mostrarBotonSave = true;

			if (StringUtils.isNotBlank(id)) {
				TarjetaPago tarjetaPago = service.getTarjetaPago(id);

				if (this.accion.equals(this.ACCION_MODIFICAR)) {
					if (tarjetaPago.getCodigoEstado().equals(
							Constants.LEST_COD_ESTA)) {
						f.setNewRecord(false);
						BeanUtils.copyProperties(f, tarjetaPago);
					} else {
						throw new Exception(
								this.getResourceMessage("mantenimientoLECTarjetaPagoForm.error.editar"));
					}
				}

				if (this.accion.equals(this.ACCION_CONSULTAR)) {
					this.mostrarBotonSave = false;
					f.setNewRecord(false);
					BeanUtils.copyProperties(f, tarjetaPago);
				}
			}
		}

		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

	}

	// @Override
	// protected void setConsultarAttributes() throws Exception {
	//
	// MantenimientoLECTarjetaPagoForm f = (MantenimientoLECTarjetaPagoForm)
	// this.formMantenimiento;
	// MantenimientoLECTarjetaPagoService service =
	// (MantenimientoLECTarjetaPagoService)getBean("spusicc.mantenimientoLECTarjetaPagoService");
	// Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	//
	// Map bean = (Map) this.beanRegistroSeleccionado;
	// String id = bean.get("id").toString();
	//
	// if(StringUtils.isNotBlank(id))
	// {
	//
	// TarjetaPago tarjetaPago = service.getTarjetaPago(id);
	//
	// Map params = BeanUtils.describe(f);
	// BeanUtils.copyProperties(f, tarjetaPago);
	// params = BeanUtils.describe(f);
	// BeanUtils.copyProperties(f, tarjetaPago);
	//
	//
	// }
	//
	// }

	/**
	 * 
	 */
	public void completarNumeroTarjeta() {
		MantenimientoLECTarjetaPagoForm f = (MantenimientoLECTarjetaPagoForm) this.formMantenimiento;
		String numeroTarjeta = f.getNumeroTarjeta();
		int longitudCodigoCliente = 16;
		String caracter = "0";

		numeroTarjeta = completarCaracteres(numeroTarjeta,
				longitudCodigoCliente, caracter);

		f.setNumeroTarjeta(numeroTarjeta);
	}

	/**
	 * @param numTar
	 * @param longitud
	 * @param caracter
	 * @return
	 */
	private String completarCaracteres(String numTar, int longitud,
			String caracter) {
		String valorAux = "";

		if (numTar.length() > 0) {
			int faltante = longitud - numTar.length();

			if (faltante >= 0) {
				for (int i = 0; i < faltante; i++) {
					valorAux = valorAux + caracter;
				}
				valorAux = valorAux + numTar;
			}

		}

		return valorAux;
	}

	/**
	 * @return
	 */
	public List getLecTarjetaPagoList() {
		return lecTarjetaPagoList;
	}

	/**
	 * @param lecTarjetaPagoList
	 */
	public void setLecTarjetaPagoList(List lecTarjetaPagoList) {
		this.lecTarjetaPagoList = lecTarjetaPagoList;
	}

}
