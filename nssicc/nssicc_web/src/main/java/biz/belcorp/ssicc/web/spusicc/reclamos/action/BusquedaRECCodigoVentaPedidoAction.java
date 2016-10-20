package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.BusquedaRECCodigoVentaPedidoForm;

@ManagedBean
@SessionScoped
public class BusquedaRECCodigoVentaPedidoAction extends BasePopupAbstractAction {
			
	private static final long serialVersionUID = 1L;
	private List stoCodigoVentaPedidoList;

//	@ManagedProperty(value="#{mantenimientoRECDigitacionCDRAction}")
//	private MantenimientoRECDigitacionCDRAction mantenimientoRECDigitacionCDRAction;
	
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaRECCodigoVentaPedidoForm busquedaRECCodigoVentaPedidoForm = new BusquedaRECCodigoVentaPedidoForm(); 
		return busquedaRECCodigoVentaPedidoForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		List lista = new ArrayList();
		BusquedaRECCodigoVentaPedidoForm f=(BusquedaRECCodigoVentaPedidoForm) this.formBusqueda;
		if(StringUtils.isNotBlank(f.getNumeroCruce())
				&& StringUtils.isNotEmpty(f.getNumeroCruce())){
			
			String codigoIdiomaISO = this.getmPantallaPrincipalBean().getCurrentCountry().getCodigoIdiomaIso();
			Map criteria = new HashMap();
			criteria.put("descripcion",f.getDescripcion());
			criteria.put("codigoVenta",f.getCodigoVenta());
			criteria.put("codigoSAP",f.getCodigoSAP());
			criteria.put("numeroCruce",f.getNumeroCruce());		
			criteria.put("codigoIdiomaISO",codigoIdiomaISO);
			
			this.stoCodigoVentaPedidoList = new ArrayList(); 
	
			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			MantenimientoRECDigitacionCDRService serviceCDR = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");
			criteria.put("numeroBoleta", f.getNumeroCruce());
			LabelValueCDR result = serviceCDR.getConsultoraReclamoByCodigo(criteria);
			criteria.put("codigoCliente",result.getLabel());
	
			lista = service.getCodigoVentaPedidoList(criteria);
			
			log.debug("cantidad de elementos esperados : " + lista.size());
			
			this.stoCodigoVentaPedidoList = lista;
			
		}
		return lista;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		BusquedaRECCodigoVentaPedidoForm f= (BusquedaRECCodigoVentaPedidoForm)this.formBusqueda;
		/*if(StringUtils.isNotEmpty(f.getNumeroCruce())
				&& StringUtils.isNotBlank(f.getNumeroCruce())){
			String numeroCruceX = f.getNumeroCruce();
			String numeroCruce = StringUtils.split(numeroCruceX, "|")[0];
			try {
				String indice = StringUtils.split(numeroCruceX, "|")[1];
				f.setIndice(indice);
				String cajaTexto = StringUtils.split(numeroCruceX, "|")[2];
				f.setCajaTexto(cajaTexto);
			} catch (Exception e) {
				e.printStackTrace();
			}*/		
			
			
		    	
			Map criteria = new HashMap();
			Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
			String codigoIdiomaISO = pais.getCodigoIdiomaIso();
			
			criteria.put("codigoIdiomaISO",codigoIdiomaISO);
			//f.setNumeroCruce(numeroCruce);
			criteria.put("numeroCruce",f.getNumeroCruce());
			
			stoCodigoVentaPedidoList = new ArrayList();		
	
			ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
			MantenimientoRECDigitacionCDRService serviceCDR = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");
			criteria.put("numeroBoleta", f.getNumeroCruce());
			LabelValueCDR result = serviceCDR.getConsultoraReclamoByCodigo(criteria);
			
			criteria.put("codigoCliente",null);
			
			//f.setNumPedido(numeroCruce);
			if (result!=null){
				if (StringUtils.isNotBlank(result.getLabel())){
					criteria.put("codigoCliente",result.getLabel());
					f.setCodConsejera(result.getLabel());
				}
				if(StringUtils.isNotBlank(result.getValue())){
					f.setNombreConsejera(result.getValue());
				}
			}
			
			
			
			f.setCodigoSAP("");
			f.setCodigoVenta("");
			f.setDescripcion("");
			
			//Obtenemos el valor del parametro STO_IND_BOLELEC
			criteria.put("codigoPais", pais.getCodigo());
			criteria.put("codigoParametro", Constants.STO_IND_BOLELEC);
			String valorParametro = service.getParametroSTO(criteria);
			//request.getSession(true).setAttribute(Constants.STO_IND_BOLELEC, valorParametro);
			//
			
			List lista=service.getCodigoVentaPedidoList(criteria);
			
			this.setListaBusqueda(lista);
			if(listaBusqueda.size()>0){
				this.setDatatableBusqueda(new DataTableModel(this.getListaBusqueda()));
			}
		
	}
	

	public List getStoCodigoVentaPedidoList() {
		return stoCodigoVentaPedidoList;
	}

	public void setStoCodigoVentaPedidoList(List stoCodigoVentaPedidoList) {
		this.stoCodigoVentaPedidoList = stoCodigoVentaPedidoList;
	}

//	public MantenimientoRECDigitacionCDRAction getMantenimientoRECDigitacionCDRAction() {
//		return mantenimientoRECDigitacionCDRAction;
//	}
//
//	public void setMantenimientoRECDigitacionCDRAction(
//			MantenimientoRECDigitacionCDRAction mantenimientoRECDigitacionCDRAction) {
//		this.mantenimientoRECDigitacionCDRAction = mantenimientoRECDigitacionCDRAction;
//	}

	
	
}