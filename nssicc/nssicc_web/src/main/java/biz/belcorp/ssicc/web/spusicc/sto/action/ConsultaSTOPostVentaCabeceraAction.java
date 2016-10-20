package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.spusicc.sto.model.PostVenta;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.ConsultaSTOPostVentaCabeceraForm;

@ManagedBean
@SessionScoped
public class ConsultaSTOPostVentaCabeceraAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -4725718144618173476L;	
	
	private String nroDocumentoRuc;
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaSTOPostVentaCabeceraForm form = new ConsultaSTOPostVentaCabeceraForm();
		return form;
	}
	
	public void setConsultarAttributes(){
		Map criteria = new HashMap();
		
		ConsultaSTOPostVentaCabeceraForm f = (ConsultaSTOPostVentaCabeceraForm)this.formBusqueda;
		f.setNumDocuRUC(this.nroDocumentoRuc);
		
		ProcesoSTOService service = (ProcesoSTOService) getBean("spusicc.procesoSTOService");
		criteria.put("nroPedido", f.getNumDocuRUC());
		
		//-- Logica de negocio
		List resultado = service.getConsultaPedidoPostVenta(criteria);
		PostVenta bean = (PostVenta)resultado.get(0);
		
		f.setConsultora(bean.getConsultora()+" "+bean.getNombre());
		f.setZona(bean.getZona());
	}
	
	// metodo que sale del popup
	public void salirPopup(ActionEvent event) {
		try {
			String ventana = "PF('dialogCliente').hide()";
			this.getRequestContext().execute(ventana);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
			
	}	

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonBuscar=false;
		this.mostrarBotonConsultar=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonSave=false;
		this.mostrarBotonSalir=false;
	}

	public String getNroDocumentoRuc() {
		return nroDocumentoRuc;
	}

	public void setNroDocumentoRuc(String nroDocumentoRuc) {
		this.nroDocumentoRuc = nroDocumentoRuc;
	}
	
	

}
