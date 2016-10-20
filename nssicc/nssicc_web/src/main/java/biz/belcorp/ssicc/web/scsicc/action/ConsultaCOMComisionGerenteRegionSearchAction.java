package biz.belcorp.ssicc.web.scsicc.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionGerenteZonaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaCOMComisionGerenteRegionSearchForm;


/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano Huamán</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaCOMComisionGerenteRegionSearchAction extends BaseConsultaAbstractAction {

	private static final long serialVersionUID = -6590561586843033846L;
	private String formatoReporte;
	private List siccComisionList;
	private List consultaCOMComisionGerenteRegionList;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaCOMComisionGerenteRegionSearchForm form = new ConsultaCOMComisionGerenteRegionSearchForm();
		return form;
	}
	
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteRegionSearchAction.setFindAttributes' method");
		}	
		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) 
															 this.getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		ConsultaCOMComisionGerenteRegionSearchForm f = (ConsultaCOMComisionGerenteRegionSearchForm) this.formBusqueda;

		/* obteniendo valores */
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
	    criteria.put("codigoPeriodoIni",  f.getCodigoPeriodoIni());
		criteria.put("codigoPeriodoFin",  f.getCodigoPeriodoFin());
		criteria.put("codigoComision", f.getCodigoComision());
		
		List resultado = service.getComisionRegionList(criteria);  
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#view()
	 */
	@Override
	@PostConstruct
	public void view() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		
		try {
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			GenericoService genericoService = (GenericoService) getBean("genericoService");
	
			String codigoMenu = this.getmPantallaPrincipalBean().getCurrentMenu();
			if(StringUtils.isNotEmpty(codigoMenu)) {
				this.codigoMenu = codigoMenu;
			} 
			
			ParametroPais parametroPais = new ParametroPais();
			parametroPais.setCodigoPais(pais.getCodigo());
			parametroPais.setCodigoSistema(Constants.COM_CODIGO_SISTEMA);
			parametroPais.setCodigoParametro(Constants.INDICADOR_CONSULTA_COMISION_GERENTE_REGION);
			parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
			List lstParametros = genericoService.getParametrosPais(parametroPais);
			
			ParametroPais parametro = null;
			if(lstParametros.size() == 1){
				parametro = (ParametroPais) lstParametros.get(0);
				
				if(StringUtils.equals(parametro.getValorParametro(), "01")){
					//return mapping.findForward(this.getComisionRegionObjetivoVentaForward());
					this.redireccionarPagina("consultaCOMComisionGerenteRegionObjetivo02Form");
				}
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ConsultaCOMComisionGerenteRegionSearchAction.setViewAtributes' method");
		}	
		
		InterfazSiCCService siccService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		MantenimientoCOMComisionGerenteZonaService service = (MantenimientoCOMComisionGerenteZonaService) 
				this.getBean("spusicc.mantenimientoCOMComisionGerenteZonaService");
		
		ConsultaCOMComisionGerenteRegionSearchForm form = (ConsultaCOMComisionGerenteRegionSearchForm) this.formBusqueda;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		Map criteria = new HashMap();
		criteria.put("estadoRegistro", "02");
		criteria.put("codigoBase", "03");
		
		List list = service.getComisionVal(criteria);
		setSiccComisionList(list);
		
		form.setCodigoPais(pais.getCodigo());
		form.setDescPais(pais.getDescripcion());
		
		String codigoPeriodo = siccService.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		form.setCodigoPeriodo(codigoPeriodo);
		
		
		
		
	}
	
	public void buscar() throws Exception {
		setFindAttributes();
	}
	
	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	public List getSiccComisionList() {
		return siccComisionList;
	}

	public void setSiccComisionList(List siccComisionList) {
		this.siccComisionList = siccComisionList;
	}

	public List getConsultaCOMComisionGerenteRegionList() {
		return consultaCOMComisionGerenteRegionList;
	}

	public void setConsultaCOMComisionGerenteRegionList(
			List consultaCOMComisionGerenteRegionList) {
		this.consultaCOMComisionGerenteRegionList = consultaCOMComisionGerenteRegionList;
	}	
}