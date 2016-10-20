package biz.belcorp.ssicc.web.spusicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.ProcesoPERCargaVentaDirectaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ProcesoPERCargaVentaDirectaForm;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ManagedBean
@SessionScoped
public class ProcesoPERCargaVentaDirectaAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 5343862956726716844L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoPERCargaVentaDirectaForm form = new ProcesoPERCargaVentaDirectaForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing :  setViewAttributes. ");
		ProcesoPERCargaVentaDirectaForm f = (ProcesoPERCargaVentaDirectaForm) this.formProceso;
		Date fechaActual = new Date();
		f.setFechaInicialD(fechaActual);
		f.setFechaFinalD(fechaActual);
		f.setFechaCorteD(fechaActual);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaInicial(sdf.format(fechaActual));
		f.setFechaFinal(sdf.format(fechaActual));
		f.setFechaCorte(sdf.format(fechaActual));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * prepareParamsBeforeExecute(java.util.Map,
	 * biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params,
			BaseForm form) throws Exception {

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProcesoPERCargaVentaDirectaService service = (ProcesoPERCargaVentaDirectaService) getBean("spusicc.procesoPERCargaVentaDirectaService");
		ProcesoPERCargaVentaDirectaForm f = (ProcesoPERCargaVentaDirectaForm) form;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaInicial(sdf.format(f.getFechaInicialD()));
		f.setFechaFinal(sdf.format(f.getFechaFinalD()));
		f.setFechaCorte(sdf.format(f.getFechaCorteD()));

		params = super.prepareParamsBeforeExecute(params, form);

		params.put("usuarioDigitacion", usuario.getLogin());
		params.put("totalRegistros", "0");
		params.put("anioBloqueo", f.getFechaFinal().substring(6, 10));

		// Verificamos bloqueo de la entidad de control anual
		String estadoControlAnual = service.bloqueoControlAnual(params);

		if ("1".equals(estadoControlAnual)) {
			params.put("procesoRegistros.notfound",
					this.getResourceMessage("procesoRegistros.notfound"));
			params.put("procesoRegistros.error",
					this.getResourceMessage("procesoRegistros.error"));
		} else {
			String mensajeBloqueo = this
					.getResourceMessage("procesoPERCargaVentaDirectaForm.msgVerificaBloqueo");
			throw new Exception(mensajeBloqueo);
		}

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {

		log.debug("Los parametros del Proceso en el executeProcess son:"
				+ params.toString());

		ProcesoPERCargaVentaDirectaService service = (ProcesoPERCargaVentaDirectaService) getBean("spusicc.procesoPERCargaVentaDirectaService");

		String totalRegistros = service.executeCargaVentaDirecta(params);
		log.debug("El numero de registros ejecutados es " + totalRegistros);

		String mensajeTotalRegistros = "";

		if (StringUtils.isNotEmpty(totalRegistros)) {
			if (Integer.parseInt(totalRegistros) > 0) {
				// mensajeTotalRegistros =
				// messageResources.getMessage("procesoRegistros.ok");
			} else {
				mensajeTotalRegistros = (String) params
						.get("procesoRegistros.notfound");
			}
		} else {
			mensajeTotalRegistros = (String) params
					.get("procesoRegistros.error");
		}
		params.put("mensajeTotalRegistros", mensajeTotalRegistros);

		service.desbloqueoControlAnual(params);
		
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #setErrorLogicaNegocio(java.util.Map)
	 */
	protected String setErrorLogicaNegocio(Map params) {
		String mensajeTotalRegistros = (String) params
				.get("mensajeTotalRegistros");
		return mensajeTotalRegistros;
	}

}