package biz.belcorp.ssicc.web.spusicc.pedidos.action;

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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.pedidos.form.ProcesoOCRActualizaPedidosDeudaMasivaForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ProcesoOCRActualizaPedidosDeudaMasivaAction extends BaseMantenimientoSearchAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5513889293057239474L;
	
	private LabelValue[] siccRegionList = {};
	private List siccPeriodoList;
	private LabelValue[] siccZonaList = {};
	private List pedidosDeudaMasivaList;
	private int subTotal = 0;
	private int pagina;
	private int total = 0;
	private Boolean btnAdicionales;

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
		ProcesoOCRActualizaPedidosDeudaMasivaForm searchForm =new ProcesoOCRActualizaPedidosDeudaMasivaForm(); 
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
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
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		ProcesoOCRActualizaPedidosDeudaMasivaForm f = (ProcesoOCRActualizaPedidosDeudaMasivaForm) this.formBusqueda;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaInicio(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaInicioDate(DateUtil.convertStringToDate(f.getFechaInicio()));
		f.setFechaFin(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaFinDate(DateUtil.convertStringToDate(f.getFechaFin()));
		f.setSubAccesos(new String[]{ Constants.REC_SUBACCESO_DEFAULT });     
		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);	
		f.setCodigoPais(pais.getCodigo());
		
		// Carga de los combos
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		List list = reporteService.getListaPeriodosByBasCtrlFact(pais.getCodigo(), Constants.NUMERO_CERO);
		f.setCodigoPeriodo(((Base)list.get(0)).getCodigo());
		
        AjaxService aSvc = (AjaxService) getBean("ajaxService");
        this.siccRegionList = aSvc.getRegionesByPais(pais.getCodigo());
		
		this.siccPeriodoList = list;
		
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarBotonNuevo = false;
		this.mostrarBotonBuscar = false;
	}
	
	public void buscar(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}

		try {
			ProcesoOCRActualizaPedidosDeudaMasivaForm f = (ProcesoOCRActualizaPedidosDeudaMasivaForm) this.formBusqueda;
			f.setFechaInicio(DateUtil.convertDateToString(f.getFechaInicioDate()));
			f.setFechaFin(DateUtil.convertDateToString(f.getFechaFinDate()));
			f.setSelectedItems(new String[] {});

			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			this.total = 0;
			this.subTotal = 0;

			if (DateUtil.compareDates(f.getFechaInicio(), f.getFechaFin(), "dd/MM/yyyy") > 0) 
			{
				this.addWarn("", this.getResourceMessage("errors.compare.dates"));
			} else {
				// Obtenemos las propiedades del bean como un 'Map'
				Map criteria;

				criteria = BeanUtils.describe(f);
				criteria.put("estado", Constants.ESTADO_ACTIVO);
				criteria.put("regionList", f.getRegiones() == null || f.getRegiones().length == 0 || StringUtils.isBlank(f.getRegiones()[0]) ? new ArrayList()
								: Arrays.asList(f.getRegiones()));
				criteria.put("zonaList", f.getZonas() == null || f.getZonas().length == 0 ? new ArrayList()
								: Arrays.asList(f.getZonas()));

				if (f.getResumen().equalsIgnoreCase("0")) {
					criteria.put("zona", null);
				} else if (f.getResumen().equalsIgnoreCase("1")) {
					criteria.put("zona", "SI");
				}
				if (StringUtils.isNotEmpty(f.getFechaInicio())) {
					criteria.put("fecha", "SI");
				} else if (f.getResumen().equalsIgnoreCase("1")) {
					criteria.put("fecha", null);
				}

				if (log.isDebugEnabled()) {
					log.debug("criteria search " + criteria.toString());
				}

				criteria.put("tipoDocumento", Constants.STO_TIPO_DOCUMENTO_OCC);

				MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
				List lista = service.getDeudaPedidosMasivaByCriteria(criteria);
				this.pedidosDeudaMasivaList = lista;

				 if(lista.size()>0)
		            {
		            	int filas = 10;
		            	for (int i = 0; i < lista.size(); i++) {
		            		Map map = (Map) lista.get(i);
		            		this.total = this.total + Integer.parseInt(map.get("pedidos").toString());							
						}
		            	if(lista.size()<=filas)
		            	{
		            		for (int i = 0; i < lista.size(); i++) {
		                		Map map = (Map) lista.get(i);
		                		this.subTotal = this.subTotal + Integer.parseInt(map.get("pedidos").toString());		    					
		    				}
		            	}else
		            	{
		            		for (int i = 0; i < filas; i++) {
		                		Map map = (Map) lista.get(i);
		                		this.subTotal = this.subTotal + Integer.parseInt(map.get("pedidos").toString());		    					
		    				}
		            	}
		            	
		            	this.btnAdicionales = true;		            	
		            }else
		            	this.btnAdicionales = false;				
				
				service.deleteTemporalDeudaMasiva();
				service.insertTemporalDeudaMasiva(criteria);

				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.siccZonaList = aSvc.getZonasByMultiplePeriodoBasCtrlFact(
						pais.getCodigo(), f.getCodigoPeriodo(),
						f.getRegiones(), " ");
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void loadZonas(ValueChangeEvent event)
	{
		String[] valores = (String[])event.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ProcesoOCRActualizaPedidosDeudaMasivaForm f = (ProcesoOCRActualizaPedidosDeudaMasivaForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		if(valores.length > 0)
			this.siccZonaList = ajax.getZonasByMultiplePeriodoBasCtrlFact(pais.getCodigo(), f.getCodigoPeriodo(), valores, "");
		else
			this.siccZonaList = null;
		
	}

	public void grabar(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'grabar' method");
		}
		try {

			MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			ProcesoOCRActualizaPedidosDeudaMasivaForm actualizaPedidosDeudaMasivaForm = (ProcesoOCRActualizaPedidosDeudaMasivaForm) this.formBusqueda;

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			SolicitudConsolidadoCabecera cabecera = new SolicitudConsolidadoCabecera();
			BeanUtils.copyProperties(cabecera, actualizaPedidosDeudaMasivaForm);

			cabecera.setObservaciones("Levantamiento Masivo");
			cabecera.setIndErrorAdminCartera(Constants.IND_ERRO_ADM_CARTERA_ACT);
			service.updateDeudaGeneralMasiva(cabecera, usuario);

			this.addInfo("", this.getResourceMessage("deuda.updated"));
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		
		if(accion.equals("Grabar"))
		{
			
		}
		
		return mensaje;
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
		
		if(this.pedidosDeudaMasivaList.size()<valorFinal){
			for (int i = valorInicial; i < this.pedidosDeudaMasivaList.size(); i++) {
				Map map = (Map) this.pedidosDeudaMasivaList.get(i);
				this.subTotal = this.subTotal + Integer.parseInt(map.get("pedidos").toString());
			}
		}
		else{
			for (int i = valorInicial; i < valorFinal; i++) {
				Map map = (Map) this.pedidosDeudaMasivaList.get(i);
				this.subTotal = this.subTotal + Integer.parseInt(map.get("pedidos").toString());
			}
		}		
	}
	
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public List getPedidosDeudaMasivaList() {
		return pedidosDeudaMasivaList;
	}

	public void setPedidosDeudaMasivaList(List pedidosDeudaMasivaList) {
		this.pedidosDeudaMasivaList = pedidosDeudaMasivaList;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Boolean getBtnAdicionales() {
		return btnAdicionales;
	}

	public void setBtnAdicionales(Boolean btnAdicionales) {
		this.btnAdicionales = btnAdicionales;
	}	
}
