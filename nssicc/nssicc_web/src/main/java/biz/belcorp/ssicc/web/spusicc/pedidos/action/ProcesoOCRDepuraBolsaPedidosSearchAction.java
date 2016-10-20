/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRDepuraBolsaPedidosForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRDepuraBolsaPedidosSearchForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class ProcesoOCRDepuraBolsaPedidosSearchAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3138888406474754428L;
	
	private LabelValue [] siccRegionList = {};
	private LabelValue [] siccZonaList = {};	
	private LabelValue [] siccTerritorioList = {};
	private List pedDepuraBolsaList = new ArrayList();
	private List siccPeriodoList = new ArrayList();
	private DataTableModel listaTablaModel = new DataTableModel();
	private List columnasSeleccionadas = new ArrayList();
	private List siccMarcaList = new ArrayList();
	private List siccCanalList = new ArrayList();
	private boolean mostrarBotonGuardar = false;
	

	@Override
	protected String getSalirForward() {
		
		return "procesoOCRDepuraBolsaPedidosList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		
		return "procesoOCRDepuraBolsaPedidosForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {

		ProcesoOCRDepuraBolsaPedidosSearchForm form = new ProcesoOCRDepuraBolsaPedidosSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		this.pedDepuraBolsaList = null;

		ProcesoOCRDepuraBolsaPedidosSearchForm f = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
		f.setFechaInicialSolicitud(DateUtil.convertDateToString(f.getFechaInicialSolicitudDate()));
		f.setFechaFinSolicitud(DateUtil.convertDateToString(f.getFechaFinSolicitudDate()));

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(f);
		criteria.put("codigoPais", f.getCodigoPais());
		if (log.isDebugEnabled()) {
			log.debug("criteria search " + criteria.toString());
		}
		criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
		
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		List list = service.getBolsaPedidosByCriteria(criteria);

		List listCodigos = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			SolicitudConsolidadoCabecera element = (SolicitudConsolidadoCabecera) iter.next();
			listCodigos.add(element.getId());
		}
		f.setSelectedItems((String[]) listCodigos.toArray(new String[listCodigos.size()]));

		this.pedDepuraBolsaList = list;
		this.columnasSeleccionadas=list;
		if(list.size()>0){			
			this.listaTablaModel = new DataTableModel(this.pedDepuraBolsaList);
		}else{
			this.listaTablaModel = new DataTableModel(null);			
		}
		
		return this.pedDepuraBolsaList;
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
		return null;				
	}	
	
	public void editar(){
		try {

			if (log.isDebugEnabled()) {
	            log.debug("Entering 'edit' method");
	        }
			
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
			ProcesoOCRDepuraBolsaPedidosSearchForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
			SolicitudConsolidadoCabecera scc = (SolicitudConsolidadoCabecera) this.columnasSeleccionadas.get(0);
			bolsaPedidosForm.setId(scc.getId());
			ProcesoOCRDepuraBolsaPedidosForm f = new ProcesoOCRDepuraBolsaPedidosForm();
			BeanUtils.copyProperties(f, bolsaPedidosForm);

	        InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();		

			// Carga de los combos Marca, Canal 
			this.siccMarcaList = interfazSiCCService.getMarcas();
			this.siccCanalList = interfazSiCCService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
			
			if (isUpdate(f)) {
	            String id = f.getId(); 
	            SolicitudConsolidadoCabecera cabecera = service.getBolsaPedidosById(getCriteria(id));
	            BeanUtils.copyProperties(f, cabecera);
	            f.setIndOCSBloqueada(f.getIndErrorOCSBloqueada().equals(Constants.IND_ERRO_OCS_BLOQ_ACT));
	        }
			this.mostrarBotonSave = false;
			this.formMantenimiento = f;
			this.mostrarBotonGuardar = true;
			this.redireccionarPagina("procesoOCRDepuraBolsaPedidosForm");
			
		} catch (Exception e) {
			this.addError("Error", this.obtieneMensajeErrorException(e)) ;
		}
	}
	
	 private boolean isUpdate(ProcesoOCRDepuraBolsaPedidosForm f) {
	        boolean updateFlag = true;
	        String id = f.getId();
	        if (StringUtils.isBlank(id)){
	            updateFlag = false;
	        }
	        return updateFlag;
	    }
	 
	 private Map getCriteria(String id) {
	        Map criteria = new HashMap();
	        criteria.put("codigoPais",StringUtils.split(id, "|")[0]);
	        criteria.put("codigoCliente",StringUtils.split(id, "|")[1]);
	        criteria.put("codigoPeriodo",StringUtils.split(id, "|")[2]);
	        criteria.put("fechaSolicitud",StringUtils.split(id, "|")[3]);        
	        return criteria;
		}

	@Override
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		this.pedDepuraBolsaList = null;
		// Carga de los combos
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ProcesoOCRDepuraBolsaPedidosSearchForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
		bolsaPedidosForm.setCodigoPais(pais.getCodigo());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
		bolsaPedidosForm.setFechaSolicitud(sdf.format(new Date(System.currentTimeMillis())));
		bolsaPedidosForm.setFechaInicialSolicitud(sdf.format(new Date(System.currentTimeMillis())));
		bolsaPedidosForm.setFechaInicialSolicitudDate(new Date(System.currentTimeMillis()));
		bolsaPedidosForm.setFechaFinSolicitud(sdf.format(new Date(System.currentTimeMillis())));
		bolsaPedidosForm.setFechaFinSolicitudDate(new Date(System.currentTimeMillis()));
		
		
		List list = reporteService.getListaPeriodosByBasCtrlFact(pais.getCodigo(), Constants.NUMERO_CERO);
		Base periodo = (Base) list.get(0);
		bolsaPedidosForm.setCodigoPeriodo(periodo.getCodigo());
		bolsaPedidosForm.setSubAccesos(new String[] { Constants.REC_SUBACCESO_DEFAULT });
		bolsaPedidosForm.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		bolsaPedidosForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		
		this.siccPeriodoList = list;		
		this.siccRegionList = ajax.getRegionesByPeriodoIntEviPerioRegio( bolsaPedidosForm.getCodigoPais(),periodo.getCodigo(),Constants.TODAS);		
		this.siccZonaList = null;
		this.siccTerritorioList = null;		
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarListaBusqueda = false;
		
		
	}
	
	public void loadRegiones(ValueChangeEvent event){
		if (log.isDebugEnabled()) {
			log.debug("loadRegiones");
		}
		
		String periodo = (String) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ProcesoOCRDepuraBolsaPedidosSearchForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
		if(StringUtils.isBlank(periodo))
		{
			this.siccRegionList = null;
			this.siccZonaList= null;
			this.siccTerritorioList = null;
		}
		else
		{
			this.siccRegionList = ajax.getRegionesByPeriodoIntEviPerioRegio( bolsaPedidosForm.getCodigoPais(),periodo,Constants.TODAS);
			this.siccZonaList= null;
			this.siccTerritorioList = null;
		}
		
	}
	
	public void loadZonas (ValueChangeEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		
		String region = (String) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ProcesoOCRDepuraBolsaPedidosSearchForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
		if(StringUtils.isBlank(region))
		{
			this.siccZonaList= null;
			this.siccTerritorioList = null;
		}
		else
		{
			this.siccZonaList= ajax.getZonasByPeriodoIntEviPerioRegioZona( bolsaPedidosForm.getCodigoPais(),bolsaPedidosForm.getCodigoPeriodo(),region,Constants.TODAS);
			this.siccTerritorioList = null;
		}
		
	}
	
	
	public void loadTerritorios(ValueChangeEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("loadTerritorios");
		}
		String zona = (String) event.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ProcesoOCRDepuraBolsaPedidosSearchForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
		if(StringUtils.isBlank(zona))
		{			
			this.siccTerritorioList = null;
		}
		else
		{			
			this.siccTerritorioList = ajax.getTerritoriosByPeriodoRegioZona( bolsaPedidosForm.getCodigoPais() ,bolsaPedidosForm.getCodigoPeriodo(),
					bolsaPedidosForm.getCodigoRegion(),zona, Constants.TODAS);
		}
		
	}
	
	
	public void bloquear (ActionEvent event) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'bloquear' method");
		}

		try {
			String msj;
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			ProcesoOCRDepuraBolsaPedidosSearchForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			  if (this.columnasSeleccionadas!= null && this.columnasSeleccionadas.size() > 0)
		        {
			        String[] seleccionados = new String[this.columnasSeleccionadas.size()];
			        SolicitudConsolidadoCabecera scc = null;
			        for (int i = 0; i <this.columnasSeleccionadas.size(); i++) 
			        {	scc= (SolicitudConsolidadoCabecera) this.columnasSeleccionadas.get(i);
			        	seleccionados[i] = scc.getId();				
					}
			        bolsaPedidosForm.setSelectedItems(seleccionados);
				  	String[] codigosBloq = bolsaPedidosForm.getSelectedItems();
					SolicitudConsolidadoCabecera cabecera = new SolicitudConsolidadoCabecera();
					
					if(codigosBloq == null)
					{
						cabecera.setCodigos(new ArrayList());
					}
					else
					{
						cabecera.setCodigos(Arrays.asList(codigosBloq));
					}
	
					cabecera.setIndErrorOCSBloqueada(Constants.IND_ERRO_BLOQ_OCS_ACT);
					service.updateBolsaPedidosBloqueo(cabecera, usuario);
				  
		        }
						
			msj = "depura.bolsa.bloqueo.updated";
			this.addInfo("Información ", this.getResourceMessage(msj));
			setFindAttributes();
			
		} catch (Exception e) {
			 this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
		
	public void desbloquear (ActionEvent event) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'desbloquear' method");
		}
		try {
			String msj;
			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			ProcesoOCRDepuraBolsaPedidosSearchForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			 if (this.columnasSeleccionadas!= null && this.columnasSeleccionadas.size() > 0)
		        {
				 String[] seleccionados = new String[this.columnasSeleccionadas.size()];
			        SolicitudConsolidadoCabecera scc = null;
			        for (int i = 0; i <this.columnasSeleccionadas.size(); i++) 
			        {	scc= (SolicitudConsolidadoCabecera) this.columnasSeleccionadas.get(i);
			        	seleccionados[i] = scc.getId();				
					}
			        bolsaPedidosForm.setSelectedItems(seleccionados);
			        String[] codigosBloq = bolsaPedidosForm.getSelectedItems();
					
					SolicitudConsolidadoCabecera cabecera = new SolicitudConsolidadoCabecera();
					
					if(codigosBloq == null)
					{
						cabecera.setCodigos(new ArrayList());
					}
					else{
						cabecera.setCodigos(Arrays.asList(codigosBloq));
					}
					cabecera.setIndErrorOCSBloqueada(Constants.IND_ERRO_BLOQ_OCS_INACT);
					service.updateBolsaPedidosBloqueo(cabecera, usuario);
					
					msj = "depura.bolsa.desbloqueo.updated";
					this.addInfo("Información ", this.getResourceMessage(msj));

		        }
			
			setFindAttributes();
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	
	}

	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setValidarConfirmar' method");
		}
		
		String mensaje = null;
		ProcesoOCRDepuraBolsaPedidosSearchForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosSearchForm) this.formBusqueda;
		
		if(accion.equalsIgnoreCase("BLOQUEAR") || accion.equalsIgnoreCase("DESBLOQUEAR"))
		{
			if(this.columnasSeleccionadas.size() == 0)
				mensaje = this.getResourceMessage("errors.select.items");
	
		}
		
		if(accion.equalsIgnoreCase("MODIFICAR"))
		{
			boolean bol = false;
			if(this.columnasSeleccionadas.size() > 1)
				{
				bol = true;
				mensaje =  this.getResourceMessage("errors.select.unique.item");			
				}
			
			if(bol == false)
				{if(this.columnasSeleccionadas.size() < 1)
					{
					bol = true;
					mensaje =  this.getResourceMessage("errors.select.item");
					}
				}
			if(bol == false)
				editar();
		}

		return mensaje;
		
	}
	
	public void guardar (ActionEvent event)
	{
		try {
			 if (log.isDebugEnabled()) {
		            log.debug("Entering 'save' method");
		        }
			 
			 String mensaje = null;        
		     MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
		     ProcesoOCRDepuraBolsaPedidosForm bolsaPedidosForm = (ProcesoOCRDepuraBolsaPedidosForm)this.formMantenimiento;
		     Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		     
            SolicitudConsolidadoCabecera cabecera = new SolicitudConsolidadoCabecera();
            BeanUtils.copyProperties(cabecera, bolsaPedidosForm);
            if (isUpdate(bolsaPedidosForm)) {
                log.debug("update bean " + cabecera);
                service.updateBolsaPedidosBloqueoIndividual(getCabecera(cabecera), usuario );
                mensaje = "depura.bolsa.bloqueo.updated";
                this.addInfo("", this.getResourceMessage(mensaje));
            } else {
                log.debug("insert bean "+cabecera);
            }
            
            if(bolsaPedidosForm.isIndOCSBloqueada()){
            	bolsaPedidosForm.setIndErrorOCSBloqueada(Constants.IND_ERRO_OCS_BLOQ_ACT);
            }
            else{
            	bolsaPedidosForm.setIndErrorOCSBloqueada(Constants.IND_ERRO_OCS_BLOQ_INACT);
            }
//            setFindAttributes();
//            this.redireccionarPagina("procesoOCRDepuraBolsaPedidosList");
		} catch (Exception e) {
			this.addError("Error ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	private SolicitudConsolidadoCabecera getCabecera(SolicitudConsolidadoCabecera cabecera) throws Exception{
    	SolicitudConsolidadoCabecera consolidadoCabecera = new SolicitudConsolidadoCabecera();
    	String id = cabecera.getId();
    	BeanUtils.copyProperties(consolidadoCabecera, cabecera);
        
        consolidadoCabecera.setCodigoPais(StringUtils.split(id, "|")[0]);
        consolidadoCabecera.setCodigoCliente(StringUtils.split(id, "|")[1]);
        consolidadoCabecera.setCodigoPeriodo(StringUtils.split(id, "|")[2]);
        consolidadoCabecera.setFechaSolicitud(StringUtils.split(id, "|")[3]);

        // Si el checkbox de clave temporal esta activo modificamos
        if (cabecera.isIndOCSBloqueada()) {
            consolidadoCabecera.setIndErrorOCSBloqueada(Constants.IND_ERRO_OCS_BLOQ_ACT);
        }
        else {
        	consolidadoCabecera.setIndErrorOCSBloqueada(Constants.IND_ERRO_OCS_BLOQ_INACT);
        }
        
        return consolidadoCabecera;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccPeriodoList
	 */
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList the siccPeriodoList to set
	 */
	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * @return the siccTerritorioList
	 */
	public LabelValue[] getSiccTerritorioList() {
		return siccTerritorioList;
	}

	/**
	 * @param siccTerritorioList the siccTerritorioList to set
	 */
	public void setSiccTerritorioList(LabelValue[] siccTerritorioList) {
		this.siccTerritorioList = siccTerritorioList;
	}

	/**
	 * @return the pedDepuraBolsaList
	 */
	public List getPedDepuraBolsaList() {
		return pedDepuraBolsaList;
	}

	/**
	 * @param pedDepuraBolsaList the pedDepuraBolsaList to set
	 */
	public void setPedDepuraBolsaList(List pedDepuraBolsaList) {
		this.pedDepuraBolsaList = pedDepuraBolsaList;
	}

	/**
	 * @return the listaTablaModel
	 */
	public DataTableModel getListaTablaModel() {
		return listaTablaModel;
	}

	/**
	 * @param listaTablaModel the listaTablaModel to set
	 */
	public void setListaTablaModel(DataTableModel listaTablaModel) {
		this.listaTablaModel = listaTablaModel;
	}

	/**
	 * @return the columnasSeleccionadas
	 */
	public List getColumnasSeleccionadas() {
		return columnasSeleccionadas;
	}

	/**
	 * @param columnasSeleccionadas the columnasSeleccionadas to set
	 */
	public void setColumnasSeleccionadas(List columnasSeleccionadas) {
		this.columnasSeleccionadas = columnasSeleccionadas;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the mostrarBotonGuardar
	 */
	public boolean isMostrarBotonGuardar() {
		return mostrarBotonGuardar;
	}

	/**
	 * @param mostrarBotonGuardar the mostrarBotonGuardar to set
	 */
	public void setMostrarBotonGuardar(boolean mostrarBotonGuardar) {
		this.mostrarBotonGuardar = mostrarBotonGuardar;
	}	
	

	
}
