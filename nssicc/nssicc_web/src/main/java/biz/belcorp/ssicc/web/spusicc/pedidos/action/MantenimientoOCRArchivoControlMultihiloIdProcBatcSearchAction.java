package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.service.spusicc.ocr.MantenimientoOCRArchivoControlMultihiloService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.app.action.ProcesoAPPSecuenciarZonaTerritorioPopupAction;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.MantenimientoOCRArchivoControlMultihiloSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -723653969334446986L;
	
	private List orcArchivoMUltihiloIdProcBat;
	private String idValor;
	

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
		MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchForm searchForm = new MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		
		String idProcBatc =this.idValor;
		MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchForm f = (MantenimientoOCRArchivoControlMultihiloIdProcBatcSearchForm) this.formBusqueda;
		MantenimientoOCRArchivoControlMultihiloService service = (MantenimientoOCRArchivoControlMultihiloService) getBean("spusicc.mantenimientoOCRArchivoControlMultihiloService");
		f.setIdProcBatc(idProcBatc);
		
		Map criteria = new HashMap();
		criteria.put("idProcBatc", idProcBatc);
		List lista = service.getBasHistoLotes(criteria);				
		this.orcArchivoMUltihiloIdProcBat=lista;
		return lista;
	}
	
	public void inicializarValores(){
		this.find();		
	}
	
	public void salirPopup(ActionEvent event) {
		try {
			
			String ventana = "PF('dialogMantenimientoArchivoControl').hide()";
			this.getRequestContext().execute(ventana);			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
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
		this.mostrarBotonSave=false;
		this.mostrarBotonSalir=false;
		
	}

	public String getIdValor() {
		return idValor;
	}

	public void setIdValor(String idValor) {
		this.idValor = idValor;
	}

	public List getOrcArchivoMUltihiloIdProcBat() {
		return orcArchivoMUltihiloIdProcBat;
	}

	public void setOrcArchivoMUltihiloIdProcBat(List orcArchivoMUltihiloIdProcBat) {
		this.orcArchivoMUltihiloIdProcBat = orcArchivoMUltihiloIdProcBat;
	}
	
	

}
