package biz.belcorp.ssicc.web.spusicc.cobranzas.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ProcesoCOBGenerarCronogramaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.cobranzas.form.ConsultaCOBCronogramaCarteraSearchForm;

@ManagedBean
@SessionScoped
public class ConsultaCOBCronogramaCarteraSearchAction extends BaseConsultaAbstractAction{
	
	private static final long serialVersionUID = 1525532707012208637L;
	
	private List siccSociedadList;
	private LabelValue[] siccEtapaDeudaList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List consultaCronogramaCartera;	
	

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaCOBCronogramaCarteraSearchForm form = new ConsultaCOBCronogramaCarteraSearchForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ConsultaCOBCronogramaCarteraSearchForm f = (ConsultaCOBCronogramaCarteraSearchForm) this.formBusqueda;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codpais=pais.getCodigo();
		f.setCodigoPais(codpais);
		
		this.siccSociedadList=service.getSociedadesByCodigoPais(codpais);
		Base base = (Base) this.siccSociedadList.get(0);
		f.setCodigoSociedad(base.getCodigo());
		
		this.siccEtapaDeudaList = ajax.getEtapasDeudaByPaisSociedad(f.getCodigoPais(), base.getCodigo());
	
		log.debug("Todo Ok: Redireccionando");
				
	}
	

	@Override
	protected List setFindAttributes() throws Exception {
		
	
		ProcesoCOBGenerarCronogramaService service = (ProcesoCOBGenerarCronogramaService)getBean("spusicc.procesoCOBGenerarCronogramaService");
		ConsultaCOBCronogramaCarteraSearchForm f = (ConsultaCOBCronogramaCarteraSearchForm) this.formBusqueda;	
		
		String nFecha=DateUtil.convertDateToString(f.getFechaGeneracionD());
		f.setFechaGeneracion(nFecha);
		/* obteniendo valores */
		if(StringUtils.isBlank(f.getcodigoEtapaDeuda()))
			f.setCodigoEtapaDeuda(null);
		if(StringUtils.isBlank(f.getCodigoRegion()))
			f.setCodigoRegion(null);
		if(StringUtils.isBlank(f.getCodigoZona()))
			f.setCodigoZona(null);
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoSociedad",f.getCodigoSociedad());
		criteria.put("codigoEtapaDeuda",f.getcodigoEtapaDeuda());
		criteria.put("codigoPeriodo",  f.getCodigoPeriodo());
		criteria.put("codigoRegion",  f.getCodigoRegion());
		criteria.put("codigoZona",  f.getCodigoZona());
		criteria.put("fechaGeneracion",  f.getFechaGeneracion());
		
		/*
		String codigoRegion = f.getCodigoRegion();
		if (StringUtils.isNotBlank(codigoRegion) && codigoRegion != "Todos" ) {
			criteria.put("codigoRegion", f.getCodigoRegion());
		}
		String codigoZona = f.getCodigoZona();
		if (StringUtils.isNotBlank(codigoZona) && codigoZona != "Todos" ) {
			criteria.put("codigoZona", f.getCodigoZona());
		}
		*/
												
		//obteniendo lista
		List resultado = service.getCronogramaByPaisSociedadEtapaPeriodo(criteria);
		this.consultaCronogramaCartera=resultado;
		return resultado;
	}
	
	public void cargarZonas(ValueChangeEvent val) {
		log.debug(">>Cargar zonas ");
		log.debug("val: " + val.getNewValue().toString());		
		
		ConsultaCOBCronogramaCarteraSearchForm f = (ConsultaCOBCronogramaCarteraSearchForm) this.formBusqueda;
		AjaxService ajax= (AjaxService) getBean("ajaxService");
		String region=val.getNewValue().toString();
		if(StringUtils.isNotBlank(region))
			this.siccZonaList=
					ajax.getZonasByPaisSociedadEtapaDeudaRegion(f.getCodigoPais(), f.getCodigoSociedad(),f.getCodigoEtapaDeuda(), region);
		else{
			setSiccZonaList(null);
		}
		
		
	}
	
	public void cargarRegion(ValueChangeEvent val) {
		log.debug(">>Cargar Region");
		log.debug("val: " + val.getNewValue().toString());		
		
		ConsultaCOBCronogramaCarteraSearchForm f = (ConsultaCOBCronogramaCarteraSearchForm) this.formBusqueda;
		AjaxService ajax= (AjaxService) getBean("ajaxService");
		String etapa=val.getNewValue().toString();
		if(StringUtils.isNotBlank(etapa)){
			f.setCodigoEtapaDeuda(etapa);
			this.siccRegionList=ajax.getRegionesByPaisSociedadEtapaDeuda(f.getCodigoPais(), f.getCodigoSociedad(),etapa);
		}else{
			setSiccRegionList(null);
			setSiccZonaList(null);
			f.setCodigoRegion(null);
		}
		
	}
	
	public void cargarEtapas(ValueChangeEvent val) {
		log.debug(">>Cargar Etapas");
		log.debug("val: " + val.getNewValue().toString());		
		
		ConsultaCOBCronogramaCarteraSearchForm f = (ConsultaCOBCronogramaCarteraSearchForm) this.formBusqueda;
		AjaxService ajax= (AjaxService) getBean("ajaxService");
		String sociedad=val.getNewValue().toString();
		String s=f.getCodigoSociedad();
		this.siccEtapaDeudaList=ajax.getEtapasDeudaByPaisSociedad(f.getCodigoPais(), sociedad);
	}

		
	public List getSiccSociedadList() {
		return siccSociedadList;
	}

	public void setSiccSociedadList(List siccSociedadList) {
		this.siccSociedadList = siccSociedadList;
	}	

	public List getConsultaCronogramaCartera() {
		return consultaCronogramaCartera;
	}

	public void setConsultaCronogramaCartera(List consultaCronogramaCartera) {
		this.consultaCronogramaCartera = consultaCronogramaCartera;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public LabelValue[] getSiccEtapaDeudaList() {
		return siccEtapaDeudaList;
	}

	public void setSiccEtapaDeudaList(LabelValue[] siccEtapaDeudaList) {
		this.siccEtapaDeudaList = siccEtapaDeudaList;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}
	
	
}
