package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.service.spusicc.inc.ProcesoINCReemplazoPremioBolsaFaltantesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.inc.form.ProcesoINCReemplazoPremioBolsaFaltantesForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ProcesoINCReemplazoPremioBolsaFaltantesAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = 1L;
	private List incConcursosFaltantesList;
	private List incAmbitoList;
	private List incDetalleReemplazosList;
	private String codigoVentaPremioElegido;
	private String oidReemplazoElegido;
	private LabelValue[] premiosFaltantes;
	private LabelValue[] premiosReemplazos;

	/**
	 * consultar codigo de venta
	 */
	public void consultar(ValueChangeEvent value) {		
		try {
			ProcesoINCReemplazoPremioBolsaFaltantesForm f = (ProcesoINCReemplazoPremioBolsaFaltantesForm) this.formProceso;
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");

			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			String oidConcurso = reporteService.getOidConcursoByNumConc(f.getNumeroConcurso());
		
			if (!value.equals(null)) {
				String valor= value.getNewValue().toString();
				f.setOidReemplazo(valor);
				Map criteria = new HashMap();
				criteria.put("oidConcurso", oidConcurso);
				criteria.put("oidReemplazo", f.getOidReemplazo());

				List listFaltantes = service.getFaltantesConcurso(criteria);
				Map mapFaltante = (Map) listFaltantes.get(0);

				f.setNumeroUnidades(String.valueOf(mapFaltante.get("numeroUnidades")));
				f.setPrecio(String.valueOf(mapFaltante.get("precio")));

				// RECUPERAMOS LA LISTA DE AMBITOS GEOGRAFICOS DEL REEMPLAZO
				List listReemplazoPremioAmbito = service.getListReemplazoPremioAmbito(f.getOidReemplazo());
				this.incAmbitoList = listReemplazoPremioAmbito;

				// RECUPERAMOS LA LISTA DETALLE DE REEMPLAZOS RELACIONADOS A UNO
				// TIPO COMPUESTA
				Map criteriaAux = new HashMap();
				criteriaAux.put("oidCompuesto", f.getOidReemplazo());
				criteriaAux.put("oidConcurso", oidConcurso);
				criteriaAux.put("indicadorCompuestos", "indicadorCompuestos");
				criteriaAux.put("indActivo", "indActivo");
				this.incDetalleReemplazosList = service.getFaltantesConcurso(criteriaAux);
			} else {
				f.setNumeroUnidades("");
				f.setPrecio("");
				this.incAmbitoList = new ArrayList();
				this.incDetalleReemplazosList = new ArrayList();
			}
			this.codigoVentaPremioElegido = f.getCodigoVentaPremio();
			this.oidReemplazoElegido = f.getOidReemplazo();
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#devuelveFormProceso()
	 */
	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		ProcesoINCReemplazoPremioBolsaFaltantesForm form = new ProcesoINCReemplazoPremioBolsaFaltantesForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction#executeProcess(java.util.Map)
	 */
	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		if (params != null) {
			ProcesoINCReemplazoPremioBolsaFaltantesService procesoINCReemplazoPremioBolsaFaltantesService = (ProcesoINCReemplazoPremioBolsaFaltantesService) getBean("spusicc.procesoINCReemplazoPremioBolsaFaltantesService");

			procesoINCReemplazoPremioBolsaFaltantesService
					.executeReemplazoPremioBolsaFaltantes(params);
		}
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executin action : view.");

		ProcesoINCReemplazoPremioBolsaFaltantesService procesoINCReemplazoPremioBolsaFaltantesService = (ProcesoINCReemplazoPremioBolsaFaltantesService) getBean("spusicc.procesoINCReemplazoPremioBolsaFaltantesService");

		List listConcursos = procesoINCReemplazoPremioBolsaFaltantesService
				.getListConcursosFaltantes();
		this.incConcursosFaltantesList = listConcursos;

	}

	/**
	 * @param e
	 */
	public void loadPremioFaltante(ValueChangeEvent e){
		try {
			String valor = e.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(!StringUtils.isBlank(valor)){
				this.premiosFaltantes = ajax.getListPremiosFaltantes(valor);
				this.premiosReemplazos = ajax.getListPremiosReemplazos(valor);
			}
			else{
				this.premiosFaltantes = null;
				this.premiosReemplazos = null;

			}	
		} catch (Exception e2) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e2));
		}
			
	}	

	/**
	 * @return the incConcursosFaltantesList
	 */
	public List getIncConcursosFaltantesList() {
		return incConcursosFaltantesList;
	}

	/**
	 * @param incConcursosFaltantesList the incConcursosFaltantesList to set
	 */
	public void setIncConcursosFaltantesList(List incConcursosFaltantesList) {
		this.incConcursosFaltantesList = incConcursosFaltantesList;
	}

	/**
	 * @return the incAmbitoList
	 */
	public List getIncAmbitoList() {
		return incAmbitoList;
	}

	/**
	 * @param incAmbitoList the incAmbitoList to set
	 */
	public void setIncAmbitoList(List incAmbitoList) {
		this.incAmbitoList = incAmbitoList;
	}

	/**
	 * @return the incDetalleReemplazosList
	 */
	public List getIncDetalleReemplazosList() {
		return incDetalleReemplazosList;
	}

	/**
	 * @param incDetalleReemplazosList the incDetalleReemplazosList to set
	 */
	public void setIncDetalleReemplazosList(List incDetalleReemplazosList) {
		this.incDetalleReemplazosList = incDetalleReemplazosList;
	}

	/**
	 * @return the codigoVentaPremioElegido
	 */
	public String getCodigoVentaPremioElegido() {
		return codigoVentaPremioElegido;
	}

	/**
	 * @param codigoVentaPremioElegido the codigoVentaPremioElegido to set
	 */
	public void setCodigoVentaPremioElegido(String codigoVentaPremioElegido) {
		this.codigoVentaPremioElegido = codigoVentaPremioElegido;
	}

	/**
	 * @return the oidReemplazoElegido
	 */
	public String getOidReemplazoElegido() {
		return oidReemplazoElegido;
	}

	/**
	 * @param oidReemplazoElegido the oidReemplazoElegido to set
	 */
	public void setOidReemplazoElegido(String oidReemplazoElegido) {
		this.oidReemplazoElegido = oidReemplazoElegido;
	}

	/**
	 * @return the premiosFaltantes
	 */
	public LabelValue[] getPremiosFaltantes() {
		return premiosFaltantes;
	}

	/**
	 * @param premiosFaltantes the premiosFaltantes to set
	 */
	public void setPremiosFaltantes(LabelValue[] premiosFaltantes) {
		this.premiosFaltantes = premiosFaltantes;
	}

	/**
	 * @return the premiosReemplazos
	 */
	public LabelValue[] getPremiosReemplazos() {
		return premiosReemplazos;
	}

	/**
	 * @param premiosReemplazos the premiosReemplazos to set
	 */
	public void setPremiosReemplazos(LabelValue[] premiosReemplazos) {
		this.premiosReemplazos = premiosReemplazos;
	}
}