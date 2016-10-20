package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaMAEIndicadorCajaBolsaProductoSearchForm;

@ManagedBean
@SessionScoped
public class ConsultaMAEIndicadorCajaBolsaProductoSearchAction extends BaseConsultaAbstractAction{
	
	
	private static final long serialVersionUID = -9033604201344524007L;
	
	private List indicadorList;
	private List productoList;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaMAEIndicadorCajaBolsaProductoSearchForm form = new ConsultaMAEIndicadorCajaBolsaProductoSearchForm();
		return form;
	}
	
	@Override
	protected  void setViewAtributes() throws Exception {
		log.info("Entro a ConsultaMAEIndicadorCajaBolsaProductoSearchAction - setViewAttributes");
		
		//-- Cargar lista Indicador
		ReporteService service = (ReporteService) getBean("scsicc.reporteService");
		this.indicadorList=service.getIndicadoresCajaBolsa();
				
		log.info("Salio a ConsultaMAEIndicadorCajaBolsaProductoSearchAction - setViewAttributes");
	}	
	
	protected List setFindAttributes()	throws Exception {
		ConsultaMAEIndicadorCajaBolsaProductoSearchForm f = (ConsultaMAEIndicadorCajaBolsaProductoSearchForm) this.formBusqueda;
		ReporteService service = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoIdiomaISO =pais.getCodigoIdiomaIso();
		
		/* obteniendo valores */
		Map criterios = new HashMap();
		criterios.put("codigoSAP", f.getCodigoSAP());
		criterios.put("indicador", f.getIndicador());		
		criterios.put("codigoPais", codigoIdiomaISO);

		/* Obteniendo Lista */
		this.productoList=service.getIndicadorCajaBolsaProducto(criterios);
		List resultado = service.getIndicadorCajaBolsaProducto(criterios);		

		return resultado;
	}

	public List getIndicadorList() {
		return indicadorList;
	}

	public void setIndicadorList(List indicadorList) {
		this.indicadorList = indicadorList;
	}

	public List getProductoList() {
		return productoList;
	}

	public void setProductoList(List productoList) {
		this.productoList = productoList;
	}
	
}
