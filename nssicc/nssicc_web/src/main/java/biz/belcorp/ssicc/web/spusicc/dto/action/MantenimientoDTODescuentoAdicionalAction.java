package biz.belcorp.ssicc.web.spusicc.dto.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.spusicc.dto.MantenimientoDTODescuentoAdicionalService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTODescuentoAdicionalForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTODescuentoAdicionalSearchForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTOGrupoDescuentoForm;
import biz.belcorp.ssicc.web.spusicc.dto.form.MantenimientoDTOGrupoDescuentoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoDTODescuentoAdicionalAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2044485946542449988L;

	@Override
	protected String getSalirForward() {
		return "mantenimientoDTODescuentoAdicionalList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoDTODescuentoAdicionalForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoDTODescuentoAdicionalSearchForm objForm = new MantenimientoDTODescuentoAdicionalSearchForm();
		return objForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoDTODescuentoAdicionalSearchForm f = (MantenimientoDTODescuentoAdicionalSearchForm) this.formBusqueda;
		MantenimientoDTODescuentoAdicionalService service = (MantenimientoDTODescuentoAdicionalService) this
				.getBean("spusicc.mantenimientoDTODescuentoAdicionalService");

		/* obteniendo valores */
		Map criteria = BeanUtils.describe(f);

		/* Obteniendo Lista */
		List resultado = service.getListDescuentoAdicional(criteria);

		return resultado;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map bean = (HashMap) this.beanRegistroSeleccionado;

		MantenimientoDTODescuentoAdicionalForm objForm = new MantenimientoDTODescuentoAdicionalForm();
		BeanUtils.copyProperties(objForm, bean);
		ActionMessages messages = new ActionMessages();
		String id = objForm.getCodigoAdicional();
		log.debug("row id " + id);
		if (id != null) {
			try {
				MantenimientoDTODescuentoAdicionalService service = (MantenimientoDTODescuentoAdicionalService) this
						.getBean("spusicc.mantenimientoDTODescuentoAdicionalService");

				bean.put("codigoUsuario", usuario.getLogin());
				service.deleteDescuentoAdicional(bean);

			} catch (Exception e) {
				String error = e.getMessage();
				this.addError("Error: ", this.getResourceMessage(
						"errors.detail", new Object[] { error }));
				return false;
			}
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoDTODescuentoAdicionalService service = (MantenimientoDTODescuentoAdicionalService) getBean("spusicc.mantenimientoDTODescuentoAdicionalService");
		MantenimientoDTODescuentoAdicionalForm f = (MantenimientoDTODescuentoAdicionalForm) this.formMantenimiento;

		Map params = BeanUtils.describe(f);
		params.put("codigoUsuario", usuario.getLogin());
		params.put("porcentaje", new BigDecimal(f.getPorcentaje()));

		if (!validaCampanas(f)) {
			return false;
		}
		try {
			if (this.accion.equals(this.ACCION_NUEVO)) {
				service.insertDescuentoAdicional(params);// inserta
			} else {
				service.updateDescuentoAdicional(params);// upadte
			}

		} catch (Exception e) {
			log.debug("error " + e.getMessage());
			this.addError("Error: ", this.getResourceMessage(
					"mantenimientoEDULocal.cabecera.error",
					new Object[] { e.getMessage() }));
			return false;
		}

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoDTODescuentoAdicionalForm f = new MantenimientoDTODescuentoAdicionalForm();
		f.reset();
		f.setCodigoPais(pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente
		MantenimientoOCRPedidoControlFacturacionService serviceOCR = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceOCR
				.getControlFacturacionById(criteria);

		// Carga el periodo proceso
		f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());

		if (!this.accion.equals(this.ACCION_NUEVO)) {

			Map bean = (HashMap) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, bean);
			String id = f.getCodigoAdicional();

			MantenimientoDTODescuentoAdicionalService service = (MantenimientoDTODescuentoAdicionalService) getBean("spusicc.mantenimientoDTODescuentoAdicionalService");

			log.debug("row id " + id);
			if (id != null) {
				try {
					log.debug("map " + bean);
					f.setCodigoPais(String.valueOf(bean.get("codigoPais")));
					f.setCodigoAdicional(String.valueOf(bean
							.get("codigoAdicional")));
					f.setDescripcionAdicional(String.valueOf(bean
							.get("descripcionAdicional")));
					f.setPorcentaje(String.valueOf(bean.get("porcentaje")));
					f.setPeriodoInicial(String.valueOf(bean
							.get("periodoInicial")));
					if (bean.get("periodoFinal") != null)
						f.setPeriodoFinal(String.valueOf(bean
								.get("periodoFinal")));
					f.setTipo(String.valueOf(bean.get("tipo")));

					log.debug("enviando para editar");
				} catch (Exception e) {
					String error = e.getMessage();
					if (StringUtils.isBlank(error))
						error = e.getLocalizedMessage();
					this.addError("Error: ", this.getResourceMessage(
							"errors.detail", new Object[] { error }));

				}
			}
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		MantenimientoDTODescuentoAdicionalSearchForm f = (MantenimientoDTODescuentoAdicionalSearchForm) this.formBusqueda;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		this.mostrarBotonConsultar = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {

		if (this.accion.equals(this.ACCION_NUEVO)) {
			return "mantenimientoDTODescuentoAdicionalForm.insert";
		} else {
			return "mantenimientoDTODescuentoAdicionalForm.update";
		}
	}

	public boolean validaCampanas(MantenimientoDTODescuentoAdicionalForm f) {

		int Pinicial = Integer.parseInt(f.getPeriodoInicial());
		int Pproceso = Integer.parseInt(f.getPeriodoProceso());
		int Pfinal = -1;
		if (f.getPeriodoFinal() != null)
			if (!f.getPeriodoFinal().equals(""))
				Pfinal = Integer.parseInt(f.getPeriodoFinal());
		if (Pinicial < Pproceso) {
			this.addWarn(
					"Adv. : ",
					this.getResourceMessage("mantenimientoDTODescuentoAdicionalForm.msg.periodoInicial"));
			return false;
		}

		if ( Pfinal< Pinicial && Pfinal!=-1) {
			this.addWarn(
					"Adv. : ",
					this.getResourceMessage("mantenimientoDTODescuentoAdicionalForm.msg.periodoFinal"));
			return false;
		}

		return true;
	}

}
