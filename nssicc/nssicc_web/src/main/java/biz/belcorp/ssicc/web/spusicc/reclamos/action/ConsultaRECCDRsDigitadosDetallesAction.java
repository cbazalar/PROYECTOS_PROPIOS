package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.CabecerasCDRDigitadas;
import biz.belcorp.ssicc.service.spusicc.reclamos.ConsultaRECCDRsDigitadosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.ConsultaRECCDRsDigitadosDetallesForm;

@ManagedBean
@SessionScoped
public class ConsultaRECCDRsDigitadosDetallesAction extends BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = 1L;
	private List recCDRDigitadosList;
	private String id;
	private String tipoConsulta;
	private DataTableModel detallePopupTableModel;
	
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
		ConsultaRECCDRsDigitadosDetallesForm form = new ConsultaRECCDRsDigitadosDetallesForm();
		return form;
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
		this.mostrarBotonSalir = false;
		this.mostrarBotonSave = false;
		
		
		
	}
	
	public void inicializandoValores() {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		log.debug("Executing action : setViewAttributes.");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		
		ConsultaRECCDRsDigitadosDetallesForm f=(ConsultaRECCDRsDigitadosDetallesForm)this.formBusqueda;
		f.setCodigoPais(pais.getCodigo());
		String codigoIso=usuario.getIdioma().getCodigoISO();
		
		ConsultaRECCDRsDigitadosService service = (ConsultaRECCDRsDigitadosService) getBean("spusicc.consultaRECCDRsDigitadosService");
		
		String id = this.id;
		//String id = request.getParameter("id");
		
		//int posicionCaracter=id.indexOf('|');
		//String posicion=id.substring(0,posicionCaracter);
		
		  
		List cabecerasDigitadasList = this.recCDRDigitadosList;
		//CabecerasCDRDigitadas cabecera=(CabecerasCDRDigitadas)cabecerasDigitadasList.get(Integer.parseInt(posicion)-1);
    //	String numero=id.substring(posicionCaracter+1,id.length());
		
		Map criteria = new HashMap(); 
		criteria.put("codigoPais",f.getCodigoPais());
		criteria.put("numeroDocumento",id);
		criteria.put("codigoIso",codigoIso);
		
		List listaDetalles= new ArrayList();
		if(getTipoConsulta().compareToIgnoreCase("1")==0)
			listaDetalles = service.getListaDetalles(criteria);
		else
			listaDetalles = service.getListaDetallesHistoricos(criteria);
	
		this.recCDRDigitadosList = listaDetalles;
		this.detallePopupTableModel = new DataTableModel(
				this.recCDRDigitadosList);
	}

	/**
	 * @return the recCDRDigitadosList
	 */
	public List getRecCDRDigitadosList() {
		return recCDRDigitadosList;
	}

	/**
	 * @param recCDRDigitadosList the recCDRDigitadosList to set
	 */
	public void setRecCDRDigitadosList(List recCDRDigitadosList) {
		this.recCDRDigitadosList = recCDRDigitadosList;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public void salirPopup(ActionEvent event) {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering my method 'salirUA'");
			}
			
			String ventana = "PF('dialogMantenimientoForm2').hide()";
			this.getRequestContext().execute(ventana);
			this.mostrarBotonSalir = true;
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @return the tipoConsulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * @param tipoConsulta the tipoConsulta to set
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * @return the detallePopupTableModel
	 */
	public DataTableModel getDetallePopupTableModel() {
		return detallePopupTableModel;
	}

	/**
	 * @param detallePopupTableModel the detallePopupTableModel to set
	 */
	public void setDetallePopupTableModel(DataTableModel detallePopupTableModel) {
		this.detallePopupTableModel = detallePopupTableModel;
	}	
	
	
	
}