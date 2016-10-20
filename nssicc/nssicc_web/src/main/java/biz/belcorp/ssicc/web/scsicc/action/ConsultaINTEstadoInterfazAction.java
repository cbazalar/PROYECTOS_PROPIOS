package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.SistemaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaINTEstadoInterfazForm;

/**
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 */
@ManagedBean
@SessionScoped
public class ConsultaINTEstadoInterfazAction extends BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7486059226448764105L;
	private List allSistemas;
	private List allInterfaces;
	private boolean mostarInterfaz;
	private Historico historico;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {

		return new ConsultaINTEstadoInterfazForm();
	}

	public void showInterfazXSistema(ValueChangeEvent val) {
		log.debug(">>showInterfazXSistema ");
		log.debug("val: " + val.getNewValue());
		allInterfaces.clear();

		ConsultaINTEstadoInterfazForm form = (ConsultaINTEstadoInterfazForm) this.formBusqueda;
		String codigoSistema = (String) val.getNewValue();
		InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");

		if (StringUtils.isNotBlank(codigoSistema)) {

			if (codigoSistema.equals("SiCC REAL")) {
				mostarInterfaz = false;
			} else {
				AjaxService ajax = (AjaxService) getBean("ajaxService");

				LabelValue[] lv = ajax.getInterfacesBySistema(
						form.getCodigoPais(), codigoSistema);
				for (LabelValue labelValue : lv) {
					Interfaz in = new Interfaz();
					in.setCodigo(labelValue.getValue());
					in.setDescripcion(labelValue.getLabel());
					allInterfaces.add(in);
				}
				mostarInterfaz = true;
			}

		} else {
			allInterfaces = interfazService.getInterfaces(null);
		}

		form.setCodigoInterfaz(null);
	}

	@Override
	protected List setFindAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		HistoricoService service = (HistoricoService) getBean("sisicc.historicoService");

		ConsultaINTEstadoInterfazForm searchForm = (ConsultaINTEstadoInterfazForm) this.formBusqueda;
		searchForm.setFechaInicioProceso(null);
		if (searchForm.getFechaInicioProcesoDate() != null)
			searchForm.setFechaInicioProceso(DateUtil.convertDateToString(searchForm.getFechaInicioProcesoDate()));
		String codSistema = searchForm.getCodigoSistema();
		String numLote = searchForm.getNumeroLote();

		String idProceso = null;
		/*
		 * if (StringUtils.isNotBlank(request.getParameter("idProceso")))
		 * idProceso = request.getParameter("idProceso");
		 */

		if (StringUtils.isNotBlank(codSistema)
				&& StringUtils.isNotEmpty(codSistema)) {
			searchForm.setCodigoSistema(codSistema);
			// Obtenemos los sistemas del pais
			Map criteria = new HashMap();

		}

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);
		String numeroLote = (String) criteria.get("numeroLote");
		if (StringUtils.isNotBlank(numLote))
			searchForm.setNumeroLote(numLote);
		else
			searchForm.setNumeroLote(null);

		String codigoSistema = (String) criteria.get("codigoSistema");
		if (StringUtils.isBlank(codigoSistema)) {
			criteria.put("codigoInterfazTexto", "");
		}
		if (StringUtils.isNotBlank(idProceso))
			criteria.put("idProceso", new Long(idProceso));

		List historico = service.getHistoricosByCriteria(criteria);

		return historico;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		SistemaService sistemaService = (SistemaService) getBean("sisicc.sistemaService");
		InterfazService interfazService = (InterfazService) getBean("sisicc.interfazService");

		allSistemas = sistemaService.getSistemas(null);
		allInterfaces = interfazService.getInterfaces(null);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaINTEstadoInterfazForm searchForm = (ConsultaINTEstadoInterfazForm) this.formBusqueda;
		searchForm.setCodigoPais(pais.getCodigo());
		mostarInterfaz = true;
		this.mPantallaPrincipalBean.setMostrarTextoBotones(false);
	}

	public List getAllSistemas() {
		return allSistemas;
	}

	public void setAllSistemas(List allSistemas) {
		this.allSistemas = allSistemas;
	}

	public List getAllInterfaces() {
		return allInterfaces;
	}

	public void setAllInterfaces(List allInterfaces) {
		this.allInterfaces = allInterfaces;
	}

	public boolean isMostarInterfaz() {
		return mostarInterfaz;
	}

	public void setMostarInterfaz(boolean mostarInterfaz) {
		this.mostarInterfaz = mostarInterfaz;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

}
