package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.sap.ProcesoSAPNuevaCargaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.sisicc.form.ProcesoSAPNuevaCargaForm;

@SessionScoped
@ManagedBean
public class ProcesoSAPNuevaCargaAction extends BaseProcesoAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8426832821836788730L;
	private String SAP_TIPO_CAMBIO_TODOS = Constants.SAP_TIPO_CAMBIO_TODOS;
	private String SAP_TIPO_CAMBIO_VENTAS = Constants.SAP_TIPO_CAMBIO_VENTAS;
	private String SAP_TIPO_CAMBIO_ABONOS = Constants.SAP_TIPO_CAMBIO_ABONOS;

	/**
	 * 
	 */

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoSAPNuevaCargaForm form = new ProcesoSAPNuevaCargaForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		ProcesoSAPNuevaCargaForm f = (ProcesoSAPNuevaCargaForm) this.formProceso;
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		ProcesoSAPNuevaCargaService procesoSAPNuevaCargaService = (ProcesoSAPNuevaCargaService) getBean("spusicc.procesoSAPNuevaCargaService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("fechaInicio", f.getFechaInicio());
		criteria.put("fechaFinal", f.getFechaFin());

		criteria.put("oidPais",
				reporteService.getOidString("getOidPaisByCodigoPais", criteria));

		if (f.getTipoCambio().equalsIgnoreCase("T"))
			criteria.put("tipoCambio", "");
		else
			criteria.put("tipoCambio", f.getTipoCambio());

		procesoSAPNuevaCargaService.executeUpdateRegistros(criteria);

		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		log.debug("Executing action : setViewAttributes.");
		// Obteniendo valores de la sesion
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codigoUsuario = usuario.getLogin();
		String codigoPais = pais.getCodigo();

		Map criteria = new HashMap();

		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoUsuario", codigoUsuario);

		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", codigoPais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente
		this.mostrarBotonBuscar = true;
		// request.setAttribute("flagTipoCambioDefecto", "1");

	}

	@Override
	protected List setFindAttributes() throws Exception {
		log.debug("setFindAttributes.");

		ProcesoSAPNuevaCargaForm f = (ProcesoSAPNuevaCargaForm) this.formProceso;
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		ProcesoSAPNuevaCargaService procesoSAPNuevaCargaService = (ProcesoSAPNuevaCargaService) getBean("spusicc.procesoSAPNuevaCargaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("fechaInicio", f.getFechaInicio());
		criteria.put("fechaFinal", f.getFechaFin());

		criteria.put("oidPais",
				reporteService.getOidString("getOidPaisByCodigoPais", criteria));

		if (f.getTipoCambio().equalsIgnoreCase("T"))
			criteria.put("tipoCambio", "");
		else
			criteria.put("tipoCambio", f.getTipoCambio());

		List list = cantidadRegistrosList(f, procesoSAPNuevaCargaService,
				criteria);
		list.add("listFake");
		return list;

	}

	/**
	 * @param f
	 * @param procesoSAPNuevaCargaService
	 * @param criteria
	 * @return
	 */
	private List cantidadRegistrosList(ProcesoSAPNuevaCargaForm f,
			ProcesoSAPNuevaCargaService procesoSAPNuevaCargaService,
			Map criteria) {

		Base cantidadRegistros = procesoSAPNuevaCargaService
				.getCantidadRegistros(criteria);

		List list = new ArrayList();

		if (cantidadRegistros != null) {
			f.setRegistrosAptos(cantidadRegistros.getCodigo());
			f.setRegistrosEnviados(cantidadRegistros.getDescripcion());
			list.add(cantidadRegistros);
		} else {
			f.setRegistrosAptos("0");
			f.setRegistrosEnviados("0");
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.web.framework.action.BaseProcesoAbstractAction
	 * #afterExecuteProcess(org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest)
	 */
	public void afterExecuteProcess() {
		ProcesoSAPNuevaCargaForm f = (ProcesoSAPNuevaCargaForm) this.formProceso;
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioD()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinD()));
		ProcesoSAPNuevaCargaService procesoSAPNuevaCargaService = (ProcesoSAPNuevaCargaService) getBean("spusicc.procesoSAPNuevaCargaService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("fechaInicio", f.getFechaInicio());
		criteria.put("fechaFinal", f.getFechaFin());

		criteria.put("oidPais",
				reporteService.getOidString("getOidPaisByCodigoPais", criteria));

		if (f.getTipoCambio().equalsIgnoreCase("T"))
			criteria.put("tipoCambio", "");
		else
			criteria.put("tipoCambio", f.getTipoCambio());

		cantidadRegistrosList(f, procesoSAPNuevaCargaService, criteria);

	}

	/**
	 * @return the sAP_TIPO_CAMBIO_TODOS
	 */
	public String getSAP_TIPO_CAMBIO_TODOS() {
		return SAP_TIPO_CAMBIO_TODOS;
	}

	/**
	 * @param sAP_TIPO_CAMBIO_TODOS
	 *            the sAP_TIPO_CAMBIO_TODOS to set
	 */
	public void setSAP_TIPO_CAMBIO_TODOS(String sAP_TIPO_CAMBIO_TODOS) {
		SAP_TIPO_CAMBIO_TODOS = sAP_TIPO_CAMBIO_TODOS;
	}

	/**
	 * @return the sAP_TIPO_CAMBIO_VENTAS
	 */
	public String getSAP_TIPO_CAMBIO_VENTAS() {
		return SAP_TIPO_CAMBIO_VENTAS;
	}

	/**
	 * @param sAP_TIPO_CAMBIO_VENTAS
	 *            the sAP_TIPO_CAMBIO_VENTAS to set
	 */
	public void setSAP_TIPO_CAMBIO_VENTAS(String sAP_TIPO_CAMBIO_VENTAS) {
		SAP_TIPO_CAMBIO_VENTAS = sAP_TIPO_CAMBIO_VENTAS;
	}

	/**
	 * @return the sAP_TIPO_CAMBIO_ABONOS
	 */
	public String getSAP_TIPO_CAMBIO_ABONOS() {
		return SAP_TIPO_CAMBIO_ABONOS;
	}

	/**
	 * @param sAP_TIPO_CAMBIO_ABONOS
	 *            the sAP_TIPO_CAMBIO_ABONOS to set
	 */
	public void setSAP_TIPO_CAMBIO_ABONOS(String sAP_TIPO_CAMBIO_ABONOS) {
		SAP_TIPO_CAMBIO_ABONOS = sAP_TIPO_CAMBIO_ABONOS;
	}

}
