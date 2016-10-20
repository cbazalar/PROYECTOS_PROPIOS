package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECCodigoVentaMatrizForm;

@ManagedBean
@SessionScoped
public class BusquedaRECCodigoVentaMatrizAction extends BasePopupAbstractAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaRECCodigoVentaMatrizForm form = new BusquedaRECCodigoVentaMatrizForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setFindAttributes");
		}
		BusquedaRECCodigoVentaMatrizForm f = (BusquedaRECCodigoVentaMatrizForm)this.formBusqueda;
		
		Map criteria = new HashMap();
		criteria.put("descripcion",f.getDescripcion());
		criteria.put("codigoVenta",f.getCodigoVenta());
		criteria.put("codigoSAP",f.getCodigoSAP());
		criteria.put("numeroCruce",f.getNumeroCruce());
		criteria.put("precioInicial",f.getPrecioInicial());
		criteria.put("precioFinal",f.getPrecioFinal());
		
		List lista=new ArrayList();
		
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		if(f.getMatriz()==null){
			lista=service.getCodigoVentaMatrizList(criteria);
		}
		else{
			if(f.getMatriz().compareToIgnoreCase("1")==0){
				lista=service.getCodigoVentaMatrizPrecioList(criteria);
			}
			else
				lista=service.getCodigoVentaMatrizIncentivoList(criteria);
		}
		return lista;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		BusquedaRECCodigoVentaMatrizForm f = (BusquedaRECCodigoVentaMatrizForm)this.formBusqueda;
		Map criteria = new HashMap();
		criteria.put("numeroCruce",f.getNumeroCruce());
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		String periodo= service.getPeriodoReferencia(criteria);
		f.setCodigoPeriodo(periodo);
//		if(StringUtils.equalsIgnoreCase(request.getParameter("metParam"), "asignar")){
//			f.setCodigoPeriodo(request.getParameter("campanaDespacho"));
//		}
		List lista=service.getCodigoVentaMatrizPrecioList(criteria);
		
		//this.stoCodigoVentaMatrizList = lista;
		
		this.setListaBusqueda(lista);
		if(listaBusqueda.size()>0){
			this.setDatatableBusqueda(new DataTableModel(this.getListaBusqueda()));
		}
		f.setCodigoSAP("");
		f.setCodigoVenta("");
		f.setDescripcion("");
		f.setMatriz("1");
		f.setPrecioInicial("");
		f.setPrecioFinal("");
	}
}