package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCCargaOrdenesRetailService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCConsultaOrdenesRetailForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes" })
public class ProcesoINCConsultaOrdenesRetailAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 4697321769339094108L;

	private List incCargaRetailEcmList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction
	 * #devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoINCConsultaOrdenesRetailForm procesoForm = new ProcesoINCConsultaOrdenesRetailForm();
		return procesoForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.incCargaRetailEcmList = new ArrayList();
		this.mostrarListaBusqueda = true;
		this.mostrarBotonExecute = false;
		this.mostrarBotonActualizarDatos = false;
		ProcesoINCConsultaOrdenesRetailForm f = (ProcesoINCConsultaOrdenesRetailForm) this.formProceso;
		ProcesoINCCargaOrdenesRetailService service = (ProcesoINCCargaOrdenesRetailService) getBean("spusicc.procesoINCCargaOrdenesRetailService");

		// Obtengo las listas a mostrar
		List cargasEjecutadasECM = service.getCargasEjecutadasECM(null);
		this.incCargaRetailEcmList = cargasEjecutadasECM;
		this.listaBusqueda = this.incCargaRetailEcmList;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);

		// Consulta de PeriodoProceso y Fecha Facturacion
		f.setOidControlCarga("");

	}

	/**
	 * Elimina
	 * @param evt
	 */
	public void eliminar(ActionEvent evt) {
		try {
			ProcesoINCConsultaOrdenesRetailForm f = (ProcesoINCConsultaOrdenesRetailForm) this.formProceso;
			Map<String, Object> params = BeanUtils.describe(f);
			this.executeProcess(params);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

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

		log.debug("Los parametros del Proceso en el executeProcess son: "
				+ params.toString());
		// params = super.prepareParamsBeforeExecute(params, form);
		HashMap valores = (HashMap) this.beanRegistroSeleccionado;
		BigDecimal oidControlCarga = (BigDecimal) valores.get("oid");
		params.put("oidControlCarga", oidControlCarga.toString());
		ProcesoINCCargaOrdenesRetailService service = (ProcesoINCCargaOrdenesRetailService) getBean("spusicc.procesoINCCargaOrdenesRetailService");
		service.executeAnularCargaOrdenesRetail(params);

		this.incCargaRetailEcmList = new ArrayList();
		List cargasEjecutadasECM = service.getCargasEjecutadasECM(null);

		this.incCargaRetailEcmList = cargasEjecutadasECM;
		this.listaBusqueda = this.incCargaRetailEcmList;
		this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		return params;
	}

	/**
	 * @return
	 */
	public List getIncCargaRetailEcmList() {
		return incCargaRetailEcmList;
	}

	/**
	 * @param incCargaRetailEcmList
	 */
	public void setIncCargaRetailEcmList(List incCargaRetailEcmList) {
		this.incCargaRetailEcmList = incCargaRetailEcmList;
	}

}
