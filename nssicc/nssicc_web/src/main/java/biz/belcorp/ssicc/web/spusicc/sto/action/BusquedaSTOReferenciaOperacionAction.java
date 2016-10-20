package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.BusquedaSTOReferenciaOperacionForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOSolicitudPostVentaDetalleForm;

@ManagedBean
@SessionScoped
public class BusquedaSTOReferenciaOperacionAction extends BaseMantenimientoSearchAbstractAction{
	
	private static final long serialVersionUID = -4448674970576932918L;
	
	private List stoReferenciaOperacionList;
	private String codCliente;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		BusquedaSTOReferenciaOperacionForm form = new BusquedaSTOReferenciaOperacionForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarBotonConsultar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarBotonNuevo=false;
		this.mostrarBotonSalir=true;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonModificar=false;
		
	}
	
	public void iniciaValores(){
		BusquedaSTOReferenciaOperacionForm f=(BusquedaSTOReferenciaOperacionForm) this.formBusqueda;
		
		String codigoCliente = this.getCodCliente();		
		f.setCodigoCliente(codigoCliente);
		ProcesoSTOEjecucionValidacionesService service = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		
		List lista=service.getReferenciaOperacionList();
		this.stoReferenciaOperacionList=lista;		
	}
	
	public void salir(ActionEvent actionEvent) {
		try {
			this.redireccionarPagina("mantenimientoSTOSolicitudPostVentaDetalleForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
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

	public List getStoReferenciaOperacionList() {
		return stoReferenciaOperacionList;
	}

	public void setStoReferenciaOperacionList(List stoReferenciaOperacionList) {
		this.stoReferenciaOperacionList = stoReferenciaOperacionList;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	

}
