package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValueCDR;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECDigitacionCDRService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BasePopupAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.action.DetallePedidoFolioAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.BusquedaSTOCodigoVentaMatrizForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.BusquedaSTOCodigoVentaPedidoForm;

@ManagedBean
@SessionScoped
public class BusquedaSTOCodigoVentaPedidoAction extends BasePopupAbstractAction{

	
	private static final long serialVersionUID = -1401486609241802382L;
	
	private String stoIndBolelec;
	private String nroCruce;
	private Boolean flag;
	
	//Detalle Popup
	@ManagedProperty(value="#{detallePedidoFolioAction}")	
	private DetallePedidoFolioAction detallePedidoFolioAction;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaSTOCodigoVentaPedidoForm f= new BusquedaSTOCodigoVentaPedidoForm() ;
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		BusquedaSTOCodigoVentaPedidoForm f=(BusquedaSTOCodigoVentaPedidoForm)this.formBusqueda;
		String codigoIdiomaISO = pais.getCodigoIdiomaIso();	
		
		//Valida Codigo Venta
		if(!this.validarCodigoVenta()){
			throw new Exception("No existe CUV"); 
		}
		Map criteria = new HashMap();
		criteria.put("descripcion",f.getDescripcion());
		criteria.put("codigoVenta",f.getCodigoVenta());
		criteria.put("codigoSAP",f.getCodigoSAP());
		criteria.put("numeroCruce",f.getNumeroCruce());
		criteria.put("codigoIdiomaISO",codigoIdiomaISO);
		
		
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		MantenimientoRECDigitacionCDRService serviceCDR = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");
		
		criteria.put("numeroBoleta", f.getNumeroCruce());
		LabelValueCDR result = serviceCDR.getConsultoraReclamoByCodigo(criteria);		
		criteria.put("codigoCliente",result.getLabel());
		
		List lista=service.getCodigoVentaPedidoList(criteria);
		return lista;		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void iniciaValores(){
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		BusquedaSTOCodigoVentaPedidoForm f=(BusquedaSTOCodigoVentaPedidoForm) this.formBusqueda;
		
		String numeroCruce = this.getNroCruce();
		
		String codigoIdiomaISO = pais.getCodigoIdiomaIso();	
		Map criteria = new HashMap();
		f.setNumeroCruce(numeroCruce);
		criteria.put("numeroCruce",numeroCruce);			
		criteria.put("codigoIdiomaISO",codigoIdiomaISO);		

		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		MantenimientoRECDigitacionCDRService serviceCDR = (MantenimientoRECDigitacionCDRService) getBean("spusicc.mantenimientoRECDigitacionCDRService");
		log.debug("numero Cruce :"+numeroCruce);
		criteria.put("numeroBoleta", numeroCruce);
		LabelValueCDR result = serviceCDR.getConsultoraReclamoByCodigo(criteria);
		
		f.setNumPedido(numeroCruce);
		
		if (result!=null){
			criteria.put("codigoCliente",result.getLabel());
			f.setCodConsejera(result.getLabel()+"  "+result.getValue());
		}else{
			criteria.put("codigoCliente",null);
			f.setCodConsejera("");
		}

		List lista=service.getCodigoVentaPedidoList(criteria);		
		
		f.setCodigoSAP("");
		f.setCodigoVenta("");
		f.setDescripcion("");
		
		log.debug("numero Cruce :"+numeroCruce);
		
		//Obtenemos el valor del parametro STO_IND_BOLELEC
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoParametro", Constants.STO_IND_BOLELEC);
		String valorParametro = service.getParametroSTO(criteria);
		this.stoIndBolelec=valorParametro;	
		this.find();
		
	}
	
	public boolean validarCodigoVenta(){
		BusquedaSTOCodigoVentaPedidoForm f=(BusquedaSTOCodigoVentaPedidoForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if(StringUtils.isNotBlank(f.getCodigoVenta())){			
			String valor=ajax.validarCodigosVentaProducto(f.getDescripcion(),f.getCodigoVenta(),f.getCodigoSAP(),f.getNumeroCruce());
			if(!StringUtils.equals(valor, "0"))
				return true;
			else
				return false;			
			
		}else
			return true;		
	}
	//abrir popup
	public void abrirDetalleDevuelve(ActionEvent event){
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String accion = externalContext.getRequestParameterMap().get("accion");
			String oid=externalContext.getRequestParameterMap().get("oid");
			this.detallePedidoFolioAction.setnAccion(accion);
			this.detallePedidoFolioAction.setOid(oid);
			this.detallePedidoFolioAction.iniciaValores();			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}	
	
	public String getStoIndBolelec() {
		return stoIndBolelec;
	}

	public void setStoIndBolelec(String stoIndBolelec) {
		this.stoIndBolelec = stoIndBolelec;
	}

	public String getNroCruce() {
		return nroCruce;
	}

	public void setNroCruce(String nroCruce) {
		this.nroCruce = nroCruce;
	}

	public DetallePedidoFolioAction getDetallePedidoFolioAction() {
		return detallePedidoFolioAction;
	}

	public void setDetallePedidoFolioAction(
			DetallePedidoFolioAction detallePedidoFolioAction) {
		this.detallePedidoFolioAction = detallePedidoFolioAction;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	

}
