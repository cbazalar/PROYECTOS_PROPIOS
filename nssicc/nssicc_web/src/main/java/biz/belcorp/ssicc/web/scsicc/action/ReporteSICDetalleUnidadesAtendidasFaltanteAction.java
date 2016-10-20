package biz.belcorp.ssicc.web.scsicc.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECIngresoAtencionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICDetalleUnidadesAtendidasFaltanteForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaProductoSearchAction;

/**
 * The Class ReporteSICDetalleUnidadesAtendidasFaltanteAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 26/11/2013
 */
@ManagedBean
@SessionScoped
public class ReporteSICDetalleUnidadesAtendidasFaltanteAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = 1L;
	
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private String codigoIdiomaISO;
	private String longitudCampoClientes; 
	private List repSicDetalleUnidadesAtendidasFaltantesClientesList;
	private List repSacDetalleTipoOfertaList;
	private List maeEstadoClienteList;
	private boolean mostrarPopupProducto;
	private static final String POPUP_SACPRODUCTO = "SACPRODUCTO";
	private String nombreArchivo;
	private static final String destination="D:\\tmp\\";
	private UploadedFile file;
	private File file1;
	private boolean virtualizador;
	
	@ManagedProperty(value="#{busquedaProductoSearchAction}")
	private BusquedaProductoSearchAction busquedaProductoSearchAction;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setAceptarPopup(javax.faces.event.ActionEvent, java.lang.String)
	 */
	@Override
	protected void setAceptarPopup(ActionEvent event, String accion) {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'PopupHipCliente' method");
		}
		if (accion.equals(this.POPUP_SACPRODUCTO)) {
			this.busquedaProductoSearchAction.verificarRegistro(event);
			if (this.busquedaProductoSearchAction.isSeleccionoRegistro()) {
				Map clienteHipMap = (Map)this.busquedaProductoSearchAction.getBeanRegistroSeleccionado(); 
				ReporteSICDetalleUnidadesAtendidasFaltanteForm f = (ReporteSICDetalleUnidadesAtendidasFaltanteForm)this.formReporte;
				f.setCodigoSap(((String)clienteHipMap.get("codigoSap")));
				this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setSalirPopup()
	 */
	@Override
	protected void setSalirPopup() {
		this.mostrarPopupProducto = false;
		this.busquedaProductoSearchAction.setBeanRegistroSeleccionado(null);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setInvocarPopup(java.lang.String)
	 */
	@Override
	protected void setInvocarPopup(String accion) {
		this.mostrarProcesoBatch = false;
		if (accion.equals(this.POPUP_SACPRODUCTO)){ 
			this.mostrarPopupProducto = true;
		}
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICDetalleUnidadesAtendidasFaltanteForm reporteForm = new ReporteSICDetalleUnidadesAtendidasFaltanteForm();
		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(this.formReporte.getFormatoExportacion())) {
			return "reporteSICDetalleUnidadesAtendidasFaltanteXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(this.formReporte.getFormatoExportacion())) {
			return "reporteSICDetalleUnidadesAtendidasFaltantePDF";
		} else {
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSICDetalleUnidadesAtendidasFaltanteForm reporteSICForm = (ReporteSICDetalleUnidadesAtendidasFaltanteForm) formReporte;
		ReporteService reporteService = (ReporteService) this.getBeanService("scsicc.reporteService");
		
		Map criteria = params;
		
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", criteria);
		String condicionRegion = this.obtieneCondicion(reporteSICForm.getCodigoRegion(), "g.COD_REGI", "'");
		String condicionZona = this.obtieneCondicion(reporteSICForm.getCodigoZona(),"f.COD_ZONA", "'");
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		
		String condicionAdicional="";
		
		if(StringUtils.isNotBlank(reporteSICForm.getTipoReporte()) && StringUtils.isNotEmpty(reporteSICForm.getTipoReporte())){
			if (reporteSICForm.getTipoReporte().equals("0")) {
				condicionAdicional = " and y.NUM_UNID_ATEN>0";
			} else if(reporteSICForm.getTipoReporte().equals("1")) {
				condicionAdicional = " and y.NUM_UNID_DEMA_REAL-y.NUM_UNID_ATEN<>0";
			} 
		}
		
		String codigoCliente = reporteSICForm.getCodigoCliente();
		Long longitudPais=this.getmPantallaPrincipalBean().getCurrentCountry().getLongitudCodigoCliente();
		
		repSicDetalleUnidadesAtendidasFaltantesClientesList.clear();
		
		// ** Inicio Proceso de Lectura de Archivo de clientes
		try{
			MantenimientoRECIngresoAtencionesService service = (MantenimientoRECIngresoAtencionesService)this.getBeanService("spusicc.mantenimientoRECIngresoAtencionesService");
					
			List listaClientesBuffer = new ArrayList();
			Map criteriaList = new HashMap();
			InputStream is = this.getFile().getInputstream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));		

			String linea = "";
			String codigoConsultora = "";
			
			while (true) {
				linea = br.readLine();
				if (linea == null) {
					break;
				}
	
				codigoConsultora = StringUtils.leftPad(linea.trim(), longitudPais.intValue(), '0');
				criteriaList.put("codigoConsultora",codigoConsultora);
				LabelValue bean = new LabelValue(codigoConsultora, service.getCodigoConsultora(criteriaList));
			
				if(bean.getValue() != null) {
					listaClientesBuffer.add(codigoConsultora);
				}
			}
		
			log.info("ActionForward loadfile REP_SIC_DET_UNIDS_ATENDIDAS_FALTANTES_CLIENTES_LIST : "+listaClientesBuffer);
			this.setRepSicDetalleUnidadesAtendidasFaltantesClientesList(listaClientesBuffer);
		}catch(Exception e){
			log.info("ReporteSACDetallePedidosCodigoSAPAction Error de Proceso de Lectura");						
			this.getRepSicDetalleUnidadesAtendidasFaltantesClientesList().clear();
		}
		// ** Fin Proceso de Lectura de Archivo de clientes
		
		// ** Inicio Proceso llenado de clientes en consulta (Map Criteria)   
		List clienteListResult = new ArrayList(); 		
		List listaClientes = this.getRepSicDetalleUnidadesAtendidasFaltantesClientesList();
					
		// SI cargo consultoras por el archivo
		if(listaClientes != null){			
			for (int i = 0; i < listaClientes.size(); i++) {
				String codCliente = (String)listaClientes.get(i);		
				clienteListResult.add(StringUtils.leftPad(codCliente, longitudPais.intValue(), '0'));
			}
		}
	
		// Si es cargado por la caja de texto					
		if (codigoCliente != null && codigoCliente.length()>0) {
			clienteListResult.add(StringUtils.leftPad(codigoCliente, longitudPais.intValue(), '0'));
		} 
		
		this.setRepSicDetalleUnidadesAtendidasFaltantesClientesList(clienteListResult);

		StringBuffer listCliente=new StringBuffer();
		int nclientes=0;
				
		if(clienteListResult != null && clienteListResult.size()>0){
			/* INI CB RI SiCCVE 20124443 */
			/*nclientes=clienteListResult.size();
			if(nclientes==1){
				listCliente.append((String)clienteListResult.get(0));
			}else{				
				for(int i=0;i<nclientes;i++){
					listCliente.append((String)clienteListResult.get(i));
					if(i<nclientes-1){
						listCliente.append(",");
					}
				}
			}			
			
			log.info(listCliente);
			condicionAdicional = condicionAdicional + " AND (a.cod_clie in ("+listCliente+")) ";
			*/
			String[] listCliente2 = new String[clienteListResult.size()];
			for (int x=0; x<clienteListResult.size(); x++) {
				listCliente2[x] = (String)clienteListResult.get(x);
			}
			log.info(listCliente2);
			condicionAdicional = condicionAdicional + this.obtieneCondicion(listCliente2, "a.cod_clie", "'");
			/* FIN CB RI SiCCVE 20124443 */		
		}
		// ** Fin Proceso llenado de clientes en consulta (Map criteria)
		
//		this.setVirtualizador(true);
//		super.prepareParameterMap(params);
			
		if(reporteSICForm.isFiltroImporte()){
			params.put("filtroReporte", "and y.val_prec_cata_unit_loca = 0");
		}else{
			params.put("filtroReporte", ""); 
		}
		
		if(reporteSICForm.isFiltroOrdenCompra()){
			params.put("filtroOrdenCompra", " AND tspa_oid_tipo_soli_pais = (select tsp.oid_tipo_soli_pais "
											                                 + " from ped_tipo_solic_pais tsp," 
											                                 + "     ped_tipo_solic ts," 
											                                 + "     v_gen_i18n_sicc i"
											                                + " where i.idio_oid_idio = 1"
											                                  + " and i.attr_enti = 'PED_TIPO_SOLIC'"
											                                  + " and i.val_oid = ts.oid_tipo_soli"
											                                  + " and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli"
											                                  + " and ts.cod_tipo_soli = 'SOC')");
		}else{
			params.put("filtroOrdenCompra", ""); 
		}
		
		params.put("NroReporte", "");
		params.put("oidPeriodo", oidPeriodo);
		params.put("condicion", condicionRegion + condicionZona	+ condicionAdicional);
		params.put("titulo", this.getReportResourceMessage("reporteSICDetalleUnidadesAtendidasFaltanteForm.titulo"));
		
		if(StringUtils.isNotBlank(reporteSICForm.getTipoOferta())){
			params.put("tipoOferta", "AND (SELECT tof.COD_TIPO_OFER FROM pre_tipo_ofert tof, pre_ofert_detal od WHERE tof.OID_TIPO_OFER=od.TOFE_OID_TIPO_OFER and y.OFDE_OID_DETA_OFER=od.OID_DETA_OFER) = '" + reporteSICForm.getTipoOferta() + "'");
		}else{
			params.put("tipoOferta", "");
		}

		if(StringUtils.isNotBlank(reporteSICForm.getCodigoEstadoCliente())){
			params.put("statusCliente", " AND EXISTS ( SELECT 1 FROM MAE_CLIEN_DATOS_ADICI DAC WHERE DAC.CLIE_OID_CLIE = A.OID_CLIE AND DAC.ESTA_OID_ESTA_CLIE = " + reporteSICForm.getCodigoEstadoCliente() + ") ");
		}
		else{
			params.put("statusCliente", "");
		}
		
		if(reporteSICForm.getFechaFacturacionInicioDate() != null){
			reporteSICForm.setFechaFacturacionInicio(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSICForm.getFechaFacturacionInicioDate()));
		}
		
		if(reporteSICForm.getFechaFacturacionFinDate() != null){
			reporteSICForm.setFechaFacturacionFin(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, reporteSICForm.getFechaFacturacionFinDate()));
		}
		
		params.put("fechaFacturacionInicio", reporteSICForm.getFechaFacturacionInicio());
		params.put("fechaFacturacionFin", reporteSICForm.getFechaFacturacionFin());
		
		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Finish 'setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;	
		this.mostrarReporteOCSV=true;
	
		
		ReporteSICDetalleUnidadesAtendidasFaltanteForm f = (ReporteSICDetalleUnidadesAtendidasFaltanteForm) this.formReporte;
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		this.setCodigoIdiomaISO(this.mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
				
		ClienteUAGenerarService clienteService = (ClienteUAGenerarService) this.getBean("sisicc.clienteUAGenerarService");		
		this.setLongitudCampoClientes(clienteService.getTamanhoNumeroCliente(pais.getCodigo()).toString());
	
		this.setRepSicDetalleUnidadesAtendidasFaltantesClientesList(new ArrayList());
		this.setRepSacDetalleTipoOfertaList(reporteService.getTipoOfertas());		
		this.setMaeEstadoClienteList(reporteService.getListaGenerico("getEstadosConsultoraByReporte", null));
		this.inicializar();
	}
	
	
	/**
	 * Inicializar.
	 */
	public void inicializar(){
		if (log.isDebugEnabled()) {
			log.debug("inicializar");
		}
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		Map criteriaOperacion = new HashMap(); 
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		
		List listaRegiones = reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		
		int i=0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base)object).getDescripcion());
			labelValue.setValue(((Base)object).getCodigo());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}
	}
	
	/**
	 * Obtiene combo de Zonas
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val){
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String[] valor = (String[]) val.getNewValue();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		ReporteSICDetalleUnidadesAtendidasFaltanteForm f = (ReporteSICDetalleUnidadesAtendidasFaltanteForm) this.formReporte;
		this.siccZonaList = ajax.getZonasMultipleByPeriodoIntEviPerioRegioZona(
				this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), 
				f.getCodigoPeriodo(),
				Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, 
				valor, 
				CollectionUtils.cardinality(new String("T"),Arrays.asList(valor))>0?"T":"");
		f.setCodigoZona(null);
	}
		
    /**
     * Metodo que se encarga de copiar el archivo a una ruta especifica y definida.
     *
     * @param fileName the file name
     * @param in the in
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void copyFile(String fileName, InputStream in)throws IOException {
//		write the inputStream to a FileOutputStream
    	OutputStream out = new FileOutputStream(new File(destination + fileName));
    	int read = 0;
        byte[] bytes = new byte[1024];         
        while ((read = in.read(bytes)) != -1) {
        	out.write(bytes, 0, read);
        }         
        in.close();
        out.flush();
        out.close();         
    }
    
    /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSICDetalleUnidadesAtendidasFaltanteService";
	}
	
	/**
	 * Metodos Get y Set
	 * @return
	 */
	public String getLongitudCampoClientes() {
		return longitudCampoClientes;
	}

	public void setLongitudCampoClientes(String longitudCampoClientes) {
		this.longitudCampoClientes = longitudCampoClientes;
	}

	public String getCodigoIdiomaISO() {
		return codigoIdiomaISO;
	}

	public void setCodigoIdiomaISO(String codigoIdiomaISO) {
		this.codigoIdiomaISO = codigoIdiomaISO;
	}

	public List getRepSicDetalleUnidadesAtendidasFaltantesClientesList() {
		return repSicDetalleUnidadesAtendidasFaltantesClientesList;
	}

	public void setRepSicDetalleUnidadesAtendidasFaltantesClientesList(
			List repSicDetalleUnidadesAtendidasFaltantesClientesList) {
		this.repSicDetalleUnidadesAtendidasFaltantesClientesList = repSicDetalleUnidadesAtendidasFaltantesClientesList;
	}

	public List getRepSacDetalleTipoOfertaList() {
		return repSacDetalleTipoOfertaList;
	}

	public void setRepSacDetalleTipoOfertaList(List repSacDetalleTipoOfertaList) {
		this.repSacDetalleTipoOfertaList = repSacDetalleTipoOfertaList;
	}

	public List getMaeEstadoClienteList() {
		return maeEstadoClienteList;
	}

	public void setMaeEstadoClienteList(List maeEstadoClienteList) {
		this.maeEstadoClienteList = maeEstadoClienteList;
	}

	public boolean isMostrarPopupProducto() {
		return mostrarPopupProducto;
	}

	public void setMostrarPopupProducto(boolean mostrarPopupProducto) {
		this.mostrarPopupProducto = mostrarPopupProducto;
	}

	public BusquedaProductoSearchAction getBusquedaProductoSearchAction() {
		return busquedaProductoSearchAction;
	}

	public void setBusquedaProductoSearchAction(
			BusquedaProductoSearchAction busquedaProductoSearchAction) {
		this.busquedaProductoSearchAction = busquedaProductoSearchAction;
	}

	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
	}

	public boolean isVirtualizador() {
		return virtualizador;
	}

	public void setVirtualizador(boolean virtualizador) {
		this.virtualizador = virtualizador;
	}		
}
