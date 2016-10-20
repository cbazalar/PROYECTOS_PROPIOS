package biz.belcorp.ssicc.web.spusicc.pedidos.action;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.event.data.PageEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Region;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizaPedidosBloqueoMasivoForm;

@ManagedBean
@SessionScoped
public class ProcesoOCRActualizaPedidosBloqueoMasivoAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6330530152063292115L;
	
	private LabelValue [] siccRegionList = {};
	private List siccPeriodoList = new ArrayList();
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccTerritorioList = {};
	private List pedActualizaBloqueoMasivoList = new ArrayList();
	private boolean btnAdicionales;
	private int total = 0;
	private int subTotal = 0;
	private int pagina;
	
	@Override
	protected void setViewAtributes() throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.btnAdicionales = false;
		this.mostrarListaBusqueda = false;
		
		ProcesoOCRActualizaPedidosBloqueoMasivoForm f = (ProcesoOCRActualizaPedidosBloqueoMasivoForm)this.formBusqueda;		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		// Removemos atributos session
		//request.getSession().removeAttribute(Constants.PED_ACTUALIZA_BLOQUEO_MASIVO_LIST);
		// Carga de los combos
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		List list = reporteService.getListaPeriodosByBasCtrlFact(pais.getCodigo(), Constants.NUMERO_CERO);

		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccRegionList = aSvc.getRegionesByPais(pais.getCodigo());
		this.siccPeriodoList = list;
		Base base = (Base)list.get(0);
		f.setCodigoPeriodo(base.getCodigo());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaIni = sdf.format(new Date(System.currentTimeMillis()));
        String fechaFin= sdf.format(new Date(System.currentTimeMillis()));
        f.setFechaInicio(fechaIni);
        f.setFechaInicioDate(DateUtil.convertStringToDate(fechaIni));
        f.setFechaFin(fechaFin);
        f.setFechaFinDate(DateUtil.convertStringToDate(fechaFin));
        f.setSubAccesos(new String[]{Constants.REC_SUBACCESO_DEFAULT});
        f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
        f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
        		
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ProcesoOCRActualizaPedidosBloqueoMasivoForm form = new ProcesoOCRActualizaPedidosBloqueoMasivoForm();
		return form;
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
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind() {
		ProcesoOCRActualizaPedidosBloqueoMasivoForm f = (ProcesoOCRActualizaPedidosBloqueoMasivoForm) this.formBusqueda;
		Date date1 = f.getFechaInicioDate();
		Date date2 = f.getFechaFinDate();
		if (date1 == null || date2 == null) 
			return null;
		if(!validarFechas(f.getFechaInicioDate(),f.getFechaFinDate()))
        {
			return this.getResourceMessage("errors.compare.dates");
        }
		
		return null;
	}


	@Override
	protected List setFindAttributes() throws Exception {

		if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method" );
        }
		this.pedActualizaBloqueoMasivoList = null;
		List reg = new ArrayList();
        List zon = new ArrayList();
        List terri = new ArrayList();
    	this.total  = 0;
    	this.subTotal  = 0;
        
		ProcesoOCRActualizaPedidosBloqueoMasivoForm f = (ProcesoOCRActualizaPedidosBloqueoMasivoForm) this.formBusqueda;
		f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioDate()));
		f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinDate()));
        f.setSelectedItems(new String[]{});
               
        	// Obtenemos las propiedades del bean como un 'Map'
            Map criteria =  BeanUtils.describe(f);
            criteria.put("estado", Constants.ESTADO_ACTIVO);
            if(f.getRegiones().length == 0 || f.getRegiones()==null)
            {
            	criteria.put("regionList", reg);
            }
            else
            {	//Este if es para detectar al todos como vacio
            	if(StringUtils.isBlank(f.getRegiones()[0])){       
            		
            		criteria.put("regionList", reg);
            	}else{
            		reg = Arrays.asList(f.getRegiones());            	
                    criteria.put("regionList",  reg);            		
            	}            	  	            
            }

            if(f.getZonas().length == 0 || f.getZonas()==null)
            {
            	criteria.put("zonaList", zon);
            }
            else
            {	//Este if es para detectar al todos como vacio
            	if(StringUtils.isBlank(f.getZonas()[0])){       
            		
            		criteria.put("zonaList", zon);
            	}else{
            		zon = Arrays.asList(f.getZonas());            	
                    criteria.put("zonaList",  zon);            		
            	}
            }
            
            if(f.getTerritorios().length == 0 || f.getTerritorios()==null)
            {
            	criteria.put("territorioList", terri);
            }
            else
            {	//Este if es para detectar al todos como vacio
            	if(StringUtils.isBlank(f.getTerritorios()[0])){
            		
            		criteria.put("territorioList", terri);
            	}else{
            		terri =  Arrays.asList(f.getTerritorios());
                	criteria.put("territorioList", terri);
            	}            	
            }
           
            if (f.getResumen().equalsIgnoreCase("0")){
                criteria.put("zona", null);
                criteria.put("territorio", null);
            }else if (f.getResumen().equalsIgnoreCase("1")){
            	criteria.put("zona", "SI");
            	criteria.put("territorio", null);        	
            }else if (f.getResumen().equalsIgnoreCase("2")) {
            	criteria.put("zona", "SI");
            	criteria.put("territorio", "SI");        	
    		}
            if (StringUtils.isNotEmpty(f.getFechaInicio())){
                criteria.put("fecha", "SI");
            }else if (f.getResumen().equalsIgnoreCase("1")){
            	criteria.put("fecha", null);
            }
            
            if (log.isDebugEnabled()) {
                log.debug("criteria search " + criteria.toString());
            }
            criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);
            
            MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
            List list = service.getBloqueoPedidosMasivoByCriteria(criteria);
            this.pedActualizaBloqueoMasivoList = list;
            if(list.size()>0)
            {
            	int filas = 10;
            	for (int i = 0; i < list.size(); i++) {
            		Map map = (Map) list.get(i);
            		this.total = this.total + Integer.parseInt(map.get("pedidos").toString());
					
				}
            	if(list.size()<=filas)
            	{
            		for (int i = 0; i < list.size(); i++) {
                		Map map = (Map) list.get(i);
                		this.subTotal = this.subTotal + Integer.parseInt(map.get("pedidos").toString());
    					
    				}
            	}
            	else
            	{
            		for (int i = 0; i < filas; i++) {
                		Map map = (Map) list.get(i);
                		this.subTotal = this.subTotal + Integer.parseInt(map.get("pedidos").toString());
    					
    				}
            	}
            	
            	this.btnAdicionales = true;
            	
            }else
            {
            	this.btnAdicionales = false;
            }
            
            service.deleteTemporalBloqueoMasivo();
            service.insertTemporalBloqueoMasivo(criteria);
            
            AjaxService aSvc = (AjaxService) getBean("ajaxService");
            String [] regArray = new String[reg.size()];
            for (int i = 0; i < reg.size(); i++) {
            	regArray[i] = (String) reg.get(i);				
			}
            this.siccZonaList = aSvc.getZonasByMultiplePeriodoBasCtrlFact(f.getCodigoPais(),f.getCodigoPeriodo(),regArray," ");
            
            
            if (f.getRegiones().length > 0 && f.getZonas().length> 0)
            {
            	this.siccTerritorioList = aSvc.getTerritoriosMultipleByPaisMarcaCanalRegionZona(f.getCodigoPais(),Constants.CODIGO_MARCA_DEFAULT,Constants.CODIGO_CANAL_DEFAULT,
            			reg,zon, " ");
            }
                    
    		return this.pedActualizaBloqueoMasivoList  ;
  
	}
	
	public void onPage(PageEvent e) {
		int paginas = e.getPage();
		this.pagina = paginas;
		calcularSubtotal();
	}
	
	public void calcularSubtotal()
	{
		int filas = 10, valorInicial = 0, valorFinal = 0;
		
		valorInicial = (this.pagina)*filas;
		valorFinal = valorInicial + filas;
		this.subTotal = 0;
		
		if(this.pedActualizaBloqueoMasivoList.size()<valorFinal){
			for (int i = valorInicial; i < this.pedActualizaBloqueoMasivoList.size(); i++) {
				Map map = (Map) this.pedActualizaBloqueoMasivoList.get(i);
				this.subTotal = this.subTotal + Integer.parseInt(map.get("pedidos").toString());
			}
		}
		else{
			for (int i = valorInicial; i < valorFinal; i++) {
				Map map = (Map) this.pedActualizaBloqueoMasivoList.get(i);
				this.subTotal = this.subTotal + Integer.parseInt(map.get("pedidos").toString());
			}
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

	public void loadZonas(ValueChangeEvent e)
	{
		String[] valorRegion = (String[])e.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ProcesoOCRActualizaPedidosBloqueoMasivoForm f = (ProcesoOCRActualizaPedidosBloqueoMasivoForm) this.formBusqueda;
		
		if(valorRegion.length>0)
		{
			this.siccZonaList = ajax.getZonasByMultiplePeriodoBasCtrlFact( f.getCodigoPais(), f.getCodigoPeriodo(),valorRegion,Constants.TODAS);
			this.siccTerritorioList = null;
		}
		else{

			this.siccZonaList = null;
			this.siccTerritorioList = null;
			
		}
		
		
	}
	
	public void loadTerritorios(ValueChangeEvent e)
	{
		String [] valorZona= (String [])e.getNewValue();
		AjaxService ajax = (AjaxService)getBean("ajaxService");
		ProcesoOCRActualizaPedidosBloqueoMasivoForm f = (ProcesoOCRActualizaPedidosBloqueoMasivoForm) this.formBusqueda;
		
		List regionesList = new ArrayList();
		List zonasList = new ArrayList();
		if(valorZona.length>0)
		{
			for (String reg : f.getRegiones()) {
			 regionesList.add(reg);
			}
			
			for(String zon : valorZona)
			{
				zonasList.add(zon);
			}
			
				this.siccTerritorioList = ajax.getTerritoriosMultipleByPaisMarcaCanalRegionZona( f.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, "VD", regionesList,zonasList,Constants.TODAS);
		}
		else
		{
			this.siccTerritorioList = null;
		}
		
	}
	
	public void grabar(ActionEvent event) throws Exception{
		if (log.isDebugEnabled()) {
            log.debug("Entering 'grabar' method");
        }
		String msj;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
	    ProcesoOCRActualizaPedidosBloqueoMasivoForm actualizaPedidosBloqueoMasivoForm = (ProcesoOCRActualizaPedidosBloqueoMasivoForm)this.formBusqueda;
	    Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	    SolicitudConsolidadoCabecera cabecera = new SolicitudConsolidadoCabecera();
        BeanUtils.copyProperties(cabecera, actualizaPedidosBloqueoMasivoForm);

        cabecera.setObservaciones("Levantamiento Masivo");
        cabecera.setIndErrorOCSBloqueada(Constants.IND_ERRO_BLOQ_OCS_ACT);	
        service.updateBloqueoGeneral(cabecera, usuario );
        msj = "depura.bolsa.bloqueo.updated";
        this.addInfo("Información", this.getResourceMessage(msj));
     	 
		
	}
	
	public void desbloquear (ActionEvent event) throws Exception{
		if (log.isDebugEnabled()) {
            log.debug("Entering 'desbloquear' method");
        }
		String msj;
		ProcesoOCRActualizaPedidosBloqueoMasivoForm actualizaPedidosBloqueoMasivoForm = (ProcesoOCRActualizaPedidosBloqueoMasivoForm)this.formBusqueda;
	    MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
	    Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	    SolicitudConsolidadoCabecera cabecera = new SolicitudConsolidadoCabecera();
        cabecera.setIndErrorOCSBloqueada(Constants.IND_ERRO_BLOQ_OCS_INACT);     
        BeanUtils.copyProperties(cabecera, actualizaPedidosBloqueoMasivoForm);
        service.updateBloqueoGeneral(cabecera, usuario );
        
        msj = "depura.bolsa.desbloqueo.updated";
        this.addInfo("Información", this.getResourceMessage(msj));
        
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {
		
		ProcesoOCRActualizaPedidosBloqueoMasivoForm f = (ProcesoOCRActualizaPedidosBloqueoMasivoForm)this.formBusqueda;
		
		if(accion.equals("BLOQUEAR")|| accion.equals("DESBLOQUEAR"))
		{
			if(!validarFechas(f.getFechaInicioDate(),f.getFechaFinDate()))
				return this.getResourceMessage("errors.compare.dates");
		}

		return null;
	}
	
	public boolean validarFechas(Date fechaInicioDate, Date fechaFinDate){
		
		if(fechaInicioDate.compareTo(fechaFinDate)>0){
			return false;
		}
		return true;
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
	 * @return the pedActualizaBloqueoMasivoList
	 */
	public List getPedActualizaBloqueoMasivoList() {
		return pedActualizaBloqueoMasivoList;
	}

	/**
	 * @param pedActualizaBloqueoMasivoList the pedActualizaBloqueoMasivoList to set
	 */
	public void setPedActualizaBloqueoMasivoList(List pedActualizaBloqueoMasivoList) {
		this.pedActualizaBloqueoMasivoList = pedActualizaBloqueoMasivoList;
	}

	/**
	 * @return the btnAdicionales
	 */
	public boolean isBtnAdicionales() {
		return btnAdicionales;
	}

	/**
	 * @param btnAdicionales the btnAdicionales to set
	 */
	public void setBtnAdicionales(boolean btnAdicionales) {
		this.btnAdicionales = btnAdicionales;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @return the subTotal
	 */
	public int getSubTotal() {
		return subTotal;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @return the pagina
	 */
	public int getPagina() {
		return pagina;
	}

	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
}
