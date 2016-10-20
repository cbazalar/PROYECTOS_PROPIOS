package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.BusquedaSTODocumentoReferenciaSearchForm;

@SessionScoped
@ManagedBean
public class BusquedaSTODocumentoReferenciaSearchAction extends BasePopupAbstractAction {

	private static final long serialVersionUID = 4299863193972215312L;
	
	private String codigoCliente;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaSTODocumentoReferenciaSearchForm f= new BusquedaSTODocumentoReferenciaSearchForm() ;
		return f;
		
	}

	@Override
	protected List setFindAttributes() throws Exception {
		BusquedaSTODocumentoReferenciaSearchForm f=(BusquedaSTODocumentoReferenciaSearchForm) this.formBusqueda;
		
		String codigoCliente = this.getCodigoCliente();
		log.debug("el codigo de cliente es"+codigoCliente);
		f.setCodigoCliente(codigoCliente);

		Map criteria = new HashMap();
		criteria.put("codigoCliente",codigoCliente);
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		List lista=service.getDocumentosReferenciaConsultora(criteria);		
		return lista;
	}

	@Override
	protected void setViewAtributes() throws Exception {		
		this.mostrarBotonBuscar=false;	
	}
	
	public void iniciaValores(){
		BusquedaSTODocumentoReferenciaSearchForm f=(BusquedaSTODocumentoReferenciaSearchForm) this.formBusqueda;
		f.setCodigoCliente(this.getCodigoCliente());
		this.find();
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	
	

}
