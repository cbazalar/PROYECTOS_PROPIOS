package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaMAVEstadoAtencionesCampanaForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaMAVEstadoAtencionesCampanaAction extends
BaseConsultaAbstractAction {

	private static final long serialVersionUID = 1L;   

	

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteZonaEscalonadaSearchAction.setFindAttributes' method");
		}

		if (log.isDebugEnabled())
			log.debug("search");
		ConsultaMAVEstadoAtencionesCampanaForm reporteForm = (ConsultaMAVEstadoAtencionesCampanaForm) this.formBusqueda;

		Map criteria = new HashMap();
		ReporteService service = (ReporteService) getBean("scsicc.reporteService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		String codPeriodo = reporteForm.getCodigoPeriodo();
		criteria.put("codigoPeriodo", codPeriodo);
		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);

		/* Obteniendo Lista */
		List resultado = service.getEstadoAtencionesMAV(oidPeriodo);

		return resultado;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		ConsultaMAVEstadoAtencionesCampanaForm f = (ConsultaMAVEstadoAtencionesCampanaForm) this.formBusqueda;
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Carga del Periodo
		f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(
				mPantallaPrincipalBean.getCurrentCountry().getCodigo(),
				Constants.CODIGO_CANAL_DEFAULT));

	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaMAVEstadoAtencionesCampanaForm form = new ConsultaMAVEstadoAtencionesCampanaForm();
		return form;
	}


}