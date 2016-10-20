package biz.belcorp.ssicc.web.scsicc.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaEVIConsultaMicaRecepcionForm;

/**
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaEVIConsultaMicaRecepcionAction extends BaseConsultaAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6712020878314851740L;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private LabelValue[] siccPeriodoList;
	private List zonaMicaList2;
	private DataTableModel zonaMicaDataModel;
	private List subTable;
	private boolean desactivaZona;
	private Boolean mostrarListaZona;
	private Boolean mostrarListaRegion;
	
	private List recProdList;
	private DataTableModel dtRECProdlist; 
	
	private List recProdListZona;
	private DataTableModel dtRECProdlistZona; 
	
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		ConsultaEVIConsultaMicaRecepcionForm form = new ConsultaEVIConsultaMicaRecepcionForm();
		return form;
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes()...");
		}
		
		this.mostrarListaBusqueda=false;
        this.mostrarListaZona=false;
        this.mostrarListaRegion=false;
        this.mostrarBotonBuscar=false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		ConsultaEVIConsultaMicaRecepcionForm actionForm = (ConsultaEVIConsultaMicaRecepcionForm) this.formBusqueda;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		this.siccPeriodoList = ajax.getListaPeriodosByBasCtrlFact(pais.getCodigo(), "", "");
		
		LabelValue[] periodosActivos = ajax.getListaPeriodosByBasCtrlFact(pais.getCodigo(), "0", "");
		
		if(periodosActivos != null && periodosActivos.length > 0) {
			String codigoPeriodo = periodosActivos[0].getValue();
            actionForm.setCodigoPeriodo(codigoPeriodo);
            
            this.siccRegionList =  ajax.getRegionesByPeriodoIntEviPerioRegio(pais.getCodigo(), actionForm.getCodigoPeriodo() ,"T");
		}else{
			this.siccRegionList = null;
		}
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        actionForm.setFechaRecepcion(sdf.format(new Date(System.currentTimeMillis())));	
        actionForm.setFechaRecepcionD(new Date(System.currentTimeMillis()));

        actionForm.setCodigoPais(pais.getCodigo());
        this.desactivaZona=true;
        this.siccZonaList=null;
	}
	

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ConsultaEVIConsultaMicaRecepcionForm f = (ConsultaEVIConsultaMicaRecepcionForm) this.formBusqueda;
		List listProdREC = new ArrayList();
		
		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(f);
		// La busqueda solo la realizaremos en los sistemas activos
		
		
		/* Es para obtener el gerente de Zona ya que requiere de marca y canal */
		String[] zonaList = f.getZonaList();
		List listaZona = new ArrayList();
		try {
			if (zonaList == null || StringUtils.isBlank(zonaList[0])) {
				listaZona = new ArrayList();
			}
			else {
				listaZona = Arrays.asList(f.getZonaList());
			}
		}
		catch(Exception e) {
			listaZona = new ArrayList();
		}
		criteria.put("zonaList", listaZona);	
		
		String[] regionList = f.getRegionList();
		List listaRegion = new ArrayList();
		try {
			if (regionList == null || StringUtils.isBlank(regionList[0])) {
				listaRegion = new ArrayList();
			}
			else {
				listaRegion = Arrays.asList(f.getRegionList());
			}
		}
		catch(Exception e) {
			listaRegion = new ArrayList();
		}
		criteria.put("regionList", listaRegion);	
		
		
		
		String fecha1 = DateUtil.getDate(f.getFechaRecepcionD());
		f.setFechaRecepcion(fecha1);
		criteria.put("fechaRecepcion", fecha1);
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			
		if (f.getTipoReporte().equals("0")) {
			this.mostrarListaZona=true;
			this.mostrarListaRegion=false;
			listProdREC = reporteService.getListaReporte("getConsultaEVIMicaRecepcionPedidosZona", criteria);
		} else {
			this.mostrarListaZona=false;
			this.mostrarListaRegion=true;
			listProdREC = reporteService.getListaReporte("getConsultaEVIMicaRecepcionPedidosRegion", criteria);
		}
		
		
		
		//ARREGLO DE LISTA PARA ZONA
		if(mostrarListaZona){
			this.recProdListZona=listProdREC;
			this.dtRECProdlistZona = new DataTableModel(this.recProdListZona);

			
			String nCodigoZona= "-1";
			String nGerente= "-1";
			String nPedEsti= "-1";
			String nCAnt= "-1";
			String nPedReal= "-1";
			String nEstReal= "-1";
			String nPedRec= "-1";
			String nDiferencia= "-1";			
			String nTotFacReg= "-1";
			String nTotFacBol= "-1";
			

			int contador=0;
			for (int i = 0; i < listProdREC.size(); i++) {
				Map params = (Map) listProdREC.get(i);
				
				String codigoZona = (String)params.get("COD_ZONA");
				String descripcionZona = (String)params.get("DES_ZONA");
				
				String gerente = (String)params.get("GER_ZONA");
				
				BigDecimal bgPedEsti = (BigDecimal)params.get("NUM_PEDI_ESTI");
				
				String pedEsti = bgPedEsti.toString();
				
				BigDecimal bgCAnt = (BigDecimal)params.get("NUM_PEDI_ANTE");
				
				String cAnt = bgCAnt.toString();
				
				BigDecimal bgPedReal = (BigDecimal)params.get("NUM_PEDI_REAL_CAB");
				
				String pedReal = bgPedReal.toString();
				
				BigDecimal bgEstReal = (BigDecimal)params.get("VAL_PORC_RECE_CAB");
				
				String estReal = bgEstReal.toString();
				
				BigDecimal bgPedRec = (BigDecimal)params.get("NUM_PEDI_RECA_CAB");
				
				String pedRec = bgPedRec.toString();
				
				BigDecimal bgDiferencia = (BigDecimal)params.get("VAL_DIFE_REAL_ESTI");
				
				String diferencia = bgDiferencia.toString();
				
				BigDecimal bgTotFacReg = (BigDecimal)params.get("TOT_FACT_REGU_CAB");
				
				String totFacReg = bgTotFacReg.toString();
				
				BigDecimal bgTotFacBol = (BigDecimal)params.get("TOT_FACT_BOLS_CAB");
				
				String totFacBol = bgTotFacBol.toString();
				
				params.put("COD_ZONAExcel", codigoZona);
				params.put("DES_ZONAExcel", descripcionZona);
				params.put("GER_ZONAExcel", gerente);
				params.put("NUM_PEDI_ESTIExcel", pedEsti);
				params.put("NUM_PEDI_ANTEExcel", cAnt);
				params.put("NUM_PEDI_REAL_CABExcel", pedReal);
				params.put("VAL_PORC_RECE_CABExcel", estReal);
				params.put("NUM_PEDI_RECA_CABExcel", pedRec);
				params.put("VAL_DIFE_REAL_ESTIExcel", diferencia);
				params.put("TOT_FACT_REGU_CABExcel", totFacReg);
				params.put("TOT_FACT_BOLS_CABExcel", totFacBol);
				contador++;
				
				
				if (contador == this.nroPaginacionDatatable + 1) {
					contador = 1;
					
				    nCodigoZona= "-1";
					nGerente= "-1";
					nPedEsti= "-1";
					nCAnt= "-1";
					nPedReal= "-1";
					nEstReal= "-1";
					nPedRec= "-1";
					nDiferencia= "-1";			
					nTotFacReg= "-1";
					nTotFacBol= "-1";
				}
				
				
				/*COD_ZONA & DES_ZONA*/
				if (StringUtils.isNotBlank(codigoZona)) {
					if (codigoZona.equals(nCodigoZona)) {
						codigoZona = "";
		            	descripcionZona = "";
					}else {
						nCodigoZona = codigoZona;
		            }
				}
				
				/*GER_ZONA*/
				if(StringUtils.isNotBlank(gerente)){
					if(gerente.equals(nGerente)){
						gerente="";
					}else{
						nGerente=gerente;
					}					
				}
				
				
				/*NUM_PEDI_ESTI*/
				if(StringUtils.isNotBlank(pedEsti)){
					if(pedEsti.equals(nPedEsti)){
						pedEsti="";
					}else{
						nPedEsti=pedEsti;
					}					
				}
				/*NUM_PEDI_ANTE*/
				if(StringUtils.isNotBlank(cAnt)){
					if(cAnt.equals(nCAnt)){
						cAnt="";
					}else{
						nCAnt=cAnt;
					}					
				}
				/*NUM_PEDI_REAL_CAB*/
				if(StringUtils.isNotBlank(pedReal)){
					if(pedReal.equals(nPedReal)){
						pedReal="";
					}else{
						nPedReal=pedReal;
					}					
				}
				
				/*VAL_PORC_RECE_CAB*/
				if(StringUtils.isNotBlank(estReal)){
					if(estReal.equals(nEstReal)){
						estReal="";
					}else{
						nEstReal=estReal;
					}					
				}
				
				/*NUM_PEDI_RECA_CAB*/
				if(StringUtils.isNotBlank(pedRec)){
					if(pedRec.equals(nPedRec)){
						pedRec="";
					}else{
						nPedRec=pedRec;
					}					
				}
				
				/*VAL_DIFE_REAL_ESTI*/
				if(StringUtils.isNotBlank(diferencia)){
					if(diferencia.equals(nDiferencia)){
						diferencia="";
					}else{
						nDiferencia=diferencia;
					}					
				}
				
				/*TOT_FACT_REGU_CAB*/
				if(StringUtils.isNotBlank(totFacReg)){
					if(totFacReg.equals(nTotFacReg)){
						totFacReg="";
					}else{
						nTotFacReg=totFacReg;
					}					
				}
				
				/*TOT_FACT_REGU_CAB*/
				if(StringUtils.isNotBlank(totFacBol)){
					if(totFacBol.equals(nTotFacBol)){
						totFacBol="";
					}else{
						nTotFacBol=totFacBol;
					}					
				}
				
				
				params.put("COD_ZONA", codigoZona);
				params.put("DES_ZONA", descripcionZona);
				params.put("GER_ZONA", gerente);			
				params.put("NUM_PEDI_ESTI", pedEsti);
				params.put("NUM_PEDI_ANTE", cAnt);
				params.put("NUM_PEDI_REAL_CAB", pedReal);
				params.put("VAL_PORC_RECE_CAB", estReal);
				params.put("NUM_PEDI_RECA_CAB", pedRec);
				params.put("VAL_DIFE_REAL_ESTI", diferencia);
				params.put("TOT_FACT_REGU_CAB", totFacReg);
				params.put("TOT_FACT_BOLS_CAB", totFacBol);
				listProdREC.set(i, params);
			}
		}
		

		
		
		//ARREGLO DE LISTA PARA REGION
		if(mostrarListaRegion){
			this.recProdList=listProdREC;
			this.dtRECProdlist = new DataTableModel(this.recProdList);

			
			String ncodigoRegion = "-1";
			String nPedEsti = "-1";
			String nCAnt = "-1";
			String nPedReal = "-1";
			String nEstReal = "-1";
			String nPedRec = "-1";
			String nDiferencia = "-1";
			String nTotFacReg = "-1";
			String nTotFacBol = "-1";
			
			int contador=0;
			
			
			for (int i = 0; i < listProdREC.size(); i++) {
				Map params = (Map) listProdREC.get(i);
				
				String codigoRegion = (String)params.get("COD_REGI");
				String descripcionRegion = (String)params.get("DES_REGI");
				
				BigDecimal bgPedEsti = (BigDecimal)params.get("NUM_PEDI_ESTI");
				
				String pedEsti = bgPedEsti.toString();
				
				BigDecimal bgCAnt = (BigDecimal)params.get("NUM_PEDI_ANTE");
				
				String cAnt = bgCAnt.toString();
				
				BigDecimal bgPedReal = (BigDecimal)params.get("NUM_PEDI_REAL_CAB");
				
				String pedReal = bgPedReal.toString();
				
				BigDecimal bgEstReal = (BigDecimal)params.get("VAL_PORC_RECE_CAB");
				
				String estReal = bgEstReal.toString();
				
				BigDecimal bgPedRec = (BigDecimal)params.get("NUM_PEDI_RECA_CAB");
				
				String pedRec = bgPedRec.toString();
				
				BigDecimal bgDiferencia = (BigDecimal)params.get("VAL_DIFE_REAL_ESTI");
				
				String diferencia = bgDiferencia.toString();
				
				BigDecimal bgTotFacReg = (BigDecimal)params.get("TOT_FACT_REGU_CAB");
				
				String totFacReg = bgTotFacReg.toString();
				
				BigDecimal bgTotFacBol = (BigDecimal)params.get("TOT_FACT_BOLS_CAB");
				
				String totFacBol = bgTotFacBol.toString();
				
				
				params.put("COD_REGIExcel", codigoRegion);
				params.put("DES_REGIExcel", descripcionRegion);
				params.put("NUM_PEDI_ESTIExcel", pedEsti);
				params.put("NUM_PEDI_ANTEExcel", cAnt);
				params.put("NUM_PEDI_REAL_CABExcel", pedReal);
				params.put("VAL_PORC_RECE_CABExcel", estReal);
				params.put("NUM_PEDI_RECA_CABExcel", pedRec);
				params.put("VAL_DIFE_REAL_ESTIExcel", diferencia);
				params.put("TOT_FACT_REGU_CABExcel", totFacReg);
				params.put("TOT_FACT_BOLS_CABExcel", totFacBol);
				contador++;
				
				if (contador == this.nroPaginacionDatatable + 1) {
					contador = 1;
					ncodigoRegion = "-1";
					nPedEsti = "-1";
					nCAnt = "-1";
					nPedReal = "-1";
					nEstReal = "-1";
					nPedRec = "-1";
					nDiferencia = "-1";
					nTotFacReg = "-1";
					nTotFacBol = "-1";
				}
				
				/*COD_REGI & DES_REGI*/
				if (StringUtils.isNotBlank(codigoRegion)) {
					if (codigoRegion.equals(ncodigoRegion)) {
						codigoRegion = "";
		            	descripcionRegion = "";
					}else {
		            	ncodigoRegion = codigoRegion;
		            }
				}
				/*NUM_PEDI_ESTI*/
				if(StringUtils.isNotBlank(pedEsti)){
					if(pedEsti.equals(nPedEsti)){
						pedEsti="";
					}else{
						nPedEsti=pedEsti;
					}					
				}
				/*NUM_PEDI_ANTE*/
				if(StringUtils.isNotBlank(cAnt)){
					if(cAnt.equals(nCAnt)){
						cAnt="";
					}else{
						nCAnt=cAnt;
					}					
				}
				/*NUM_PEDI_REAL_CAB*/
				if(StringUtils.isNotBlank(pedReal)){
					if(pedReal.equals(nPedReal)){
						pedReal="";
					}else{
						nPedReal=pedReal;
					}					
				}
				
				/*VAL_PORC_RECE_CAB*/
				if(StringUtils.isNotBlank(estReal)){
					if(estReal.equals(nEstReal)){
						estReal="";
					}else{
						nEstReal=estReal;
					}					
				}
				
				/*NUM_PEDI_RECA_CAB*/
				if(StringUtils.isNotBlank(pedRec)){
					if(pedRec.equals(nPedRec)){
						pedRec="";
					}else{
						nPedRec=pedRec;
					}					
				}
				
				/*VAL_DIFE_REAL_ESTI*/
				if(StringUtils.isNotBlank(diferencia)){
					if(diferencia.equals(nDiferencia)){
						diferencia="";
					}else{
						nDiferencia=diferencia;
					}					
				}
				
				/*TOT_FACT_REGU_CAB*/
				if(StringUtils.isNotBlank(totFacReg)){
					if(totFacReg.equals(nTotFacReg)){
						totFacReg="";
					}else{
						nTotFacReg=totFacReg;
					}					
				}
				
				/*TOT_FACT_REGU_CAB*/
				if(StringUtils.isNotBlank(totFacBol)){
					if(totFacBol.equals(nTotFacBol)){
						totFacBol="";
					}else{
						nTotFacBol=totFacBol;
					}					
				}
				
				params.put("COD_REGI", codigoRegion);
				params.put("DES_REGI", descripcionRegion);
				params.put("NUM_PEDI_ESTI", pedEsti);
				params.put("NUM_PEDI_ANTE", cAnt);
				params.put("NUM_PEDI_REAL_CAB", pedReal);
				params.put("VAL_PORC_RECE_CAB", estReal);
				params.put("NUM_PEDI_RECA_CAB", pedRec);
				params.put("VAL_DIFE_REAL_ESTI", diferencia);
				params.put("TOT_FACT_REGU_CAB", totFacReg);
				params.put("TOT_FACT_BOLS_CAB", totFacBol);
				listProdREC.set(i, params);
			}
		}
		
		
		
		
		
		return listProdREC;
	}

	
	
	/**
	 * Obtiene lista de Zonas por Region
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		log.debug(">>showZonasxRegion ");
		log.debug("val: "+val.getNewValue().toString());
		
		String[] regiones = (String []) val.getNewValue();
		ConsultaEVIConsultaMicaRecepcionForm form = (ConsultaEVIConsultaMicaRecepcionForm) this.formBusqueda;
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		this.siccZonaList = aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
				Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL);
		form.setZonaList(null);
	}
	
	public void loadRegiones(ValueChangeEvent val){
		ConsultaEVIConsultaMicaRecepcionForm f= (ConsultaEVIConsultaMicaRecepcionForm) this.formBusqueda;
		if(val!=null){
			String periodo=val.getNewValue().toString();
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.siccRegionList =  ajax.getRegionesByPeriodoIntEviPerioRegio(f.getCodigoPais(),periodo,"T");
			f.setRegionList(null);
		}
		
		
	}
	
	public void loadTipoConsulta(ValueChangeEvent val){
		String valor=val.getNewValue().toString();
		if(StringUtils.equals(valor, "1")){
			this.siccZonaList=null;
			this.desactivaZona=true;
		}
		
		if(StringUtils.equals(valor, "0")){
			//this.siccZonaList=null;
			this.desactivaZona=false;
		}
	}
	
	//Metodo para abrir el Popup Detalle- colum Codigo
	public void openPopupDetalle(ActionEvent event){
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			String codigoValor = externalContext.getRequestParameterMap().get("codigo");
			
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

			ConsultaEVIConsultaMicaRecepcionForm f = (ConsultaEVIConsultaMicaRecepcionForm) this.formBusqueda;
			List listProdREC = null;

			// Obtenemos las propiedades del bean como un 'Map'
			Map criteria = BeanUtils.describe(f);		

			/* Es para obtener el gerente de Zona ya que requiere de marca y canal */
			criteria.put("codigoMarca", "T");
			criteria.put("codigoCanal", "VD");

			criteria.put("zonaList", new ArrayList());

			ArrayList tempoRegion = new ArrayList();
			
			tempoRegion.add(0, codigoValor);
			criteria.put("regionList", tempoRegion);

			listProdREC = reporteService.getListaReporte("getConsultaEVIMicaRecepcionPedidosZonaDet", criteria);
			this.zonaMicaList2=listProdREC;
		
			if (StringUtils.isNotBlank(codigoValor)) {
				f.setCodigoRegion(codigoValor);				
			}
			
			this.zonaMicaDataModel= new DataTableModel(this.zonaMicaList2);
			this.mostrarListaBusqueda=false;
			this.mostrarBotonBuscar=false;
			this.mostrarBotonLimpiar=false;
			this.subTable=new ArrayList();
			Base base=new Base();
			base.setCodigo("01");
			this.subTable.add(base);
			

			
			String nCodigoZona= "-1";
			String nGerente= "-1";
			String nPedEsti= "-1";
			String nCAnt= "-1";
			String nPedReal= "-1";
			String nEstReal= "-1";
			String nPedRec= "-1";
			String nDiferencia= "-1";			
			String nTotFacReg= "-1";
			String nTotFacBol= "-1";
			

			int contador=0;
			for (int i = 0; i < listProdREC.size(); i++) {
				Map params = (Map) listProdREC.get(i);
				
				/*Controla el ordenamiento de subtotal*/
				String codigoZonaGuia = (String)params.get("COD_ZONA");
				/*Controla el ordenamiento de subtotal*/
				
				String codigoZona = (String)params.get("COD_ZONA");
				String descripcionZona = (String)params.get("DES_ZONA");
				
				String gerente = (String)params.get("GER_ZONA");
				
				BigDecimal bgPedEsti = (BigDecimal)params.get("NUM_PEDI_ESTI");
				
				String pedEsti = bgPedEsti.toString();
				
				BigDecimal bgCAnt = (BigDecimal)params.get("NUM_PEDI_ANTE");
				
				String cAnt = bgCAnt.toString();
				
				BigDecimal bgPedReal = (BigDecimal)params.get("NUM_PEDI_REAL_CAB");
				
				String pedReal = bgPedReal.toString();
				
				BigDecimal bgEstReal = (BigDecimal)params.get("VAL_PORC_RECE_CAB");
				
				String estReal = bgEstReal.toString();
				
				BigDecimal bgPedRec = (BigDecimal)params.get("NUM_PEDI_RECA_CAB");
				
				String pedRec = bgPedRec.toString();
				
				BigDecimal bgDiferencia = (BigDecimal)params.get("VAL_DIFE_REAL_ESTI");
				
				String diferencia = bgDiferencia.toString();
				
				BigDecimal bgTotFacReg = (BigDecimal)params.get("TOT_FACT_REGU_CAB");
				
				String totFacReg = bgTotFacReg.toString();
				
				BigDecimal bgTotFacBol = (BigDecimal)params.get("TOT_FACT_BOLS_CAB");
				
				String totFacBol = bgTotFacBol.toString();
				
				params.put("COD_ZONAExcel", codigoZona);
				params.put("DES_ZONAExcel", descripcionZona);
				params.put("GER_ZONAExcel", gerente);
				params.put("NUM_PEDI_ESTIExcel", pedEsti);
				params.put("NUM_PEDI_ANTEExcel", cAnt);
				params.put("NUM_PEDI_REAL_CABExcel", pedReal);
				params.put("VAL_PORC_RECE_CABExcel", estReal);
				params.put("NUM_PEDI_RECA_CABExcel", pedRec);
				params.put("VAL_DIFE_REAL_ESTIExcel", diferencia);
				params.put("TOT_FACT_REGU_CABExcel", totFacReg);
				params.put("TOT_FACT_BOLS_CABExcel", totFacBol);
				contador++;
				
				
				if (contador == this.nroPaginacionDatatable + 1) {
					contador = 1;
					nCodigoZona= "-1";
					nGerente= "-1";
					nPedEsti= "-1";
					nCAnt= "-1";
					nPedReal= "-1";
					nEstReal= "-1";
					nPedRec= "-1";
					nDiferencia= "-1";			
					nTotFacReg= "-1";
					nTotFacBol= "-1";
				}
				
				
				/*COD_ZONA & DES_ZONA*/
				if (StringUtils.isNotBlank(codigoZona)) {
					if (codigoZona.equals(nCodigoZona)) {
						codigoZona = "";
		            	descripcionZona = "";
					}else {
						nCodigoZona = codigoZona;
		            }
				}
				
				/*GER_ZONA*/
				if(StringUtils.isNotBlank(gerente)){
					if(gerente.equals(nGerente)){
						gerente="";
					}else{
						nGerente=gerente;
					}					
				}
				
				
				/*NUM_PEDI_ESTI*/
				if(StringUtils.isNotBlank(pedEsti)){
					if(pedEsti.equals(nPedEsti)){
						pedEsti="";
					}else{
						nPedEsti=pedEsti;
					}					
				}
				/*NUM_PEDI_ANTE*/
				if(StringUtils.isNotBlank(cAnt)){
					if(cAnt.equals(nCAnt)){
						cAnt="";
					}else{
						nCAnt=cAnt;
					}					
				}
				/*NUM_PEDI_REAL_CAB*/
				if(StringUtils.isNotBlank(pedReal)){
					if(pedReal.equals(nPedReal)){
						pedReal="";
					}else{
						nPedReal=pedReal;
					}					
				}
				
				/*VAL_PORC_RECE_CAB*/
				if(StringUtils.isNotBlank(estReal)){
					if(estReal.equals(nEstReal)){
						estReal="";
					}else{
						nEstReal=estReal;
					}					
				}
				
				/*NUM_PEDI_RECA_CAB*/
				if(StringUtils.isNotBlank(pedRec)){
					if(pedRec.equals(nPedRec)){
						pedRec="";
					}else{
						nPedRec=pedRec;
					}					
				}
				
				/*VAL_DIFE_REAL_ESTI*/
				if(StringUtils.isNotBlank(diferencia)){
					if(diferencia.equals(nDiferencia)){
						diferencia="";
					}else{
						nDiferencia=diferencia;
					}					
				}
				
				/*TOT_FACT_REGU_CAB*/
				if(StringUtils.isNotBlank(totFacReg)){
					if(totFacReg.equals(nTotFacReg)){
						totFacReg="";
					}else{
						nTotFacReg=totFacReg;
					}					
				}
				
				/*TOT_FACT_REGU_CAB*/
				if(StringUtils.isNotBlank(totFacBol)){
					if(totFacBol.equals(nTotFacBol)){
						totFacBol="";
					}else{
						nTotFacBol=totFacBol;
					}					
				}
				
				params.put("COD_ZONAGuia", codigoZonaGuia);
				
				params.put("COD_ZONA", codigoZona);
				params.put("DES_ZONA", descripcionZona);
				params.put("GER_ZONA", gerente);			
				params.put("NUM_PEDI_ESTI", pedEsti);
				params.put("NUM_PEDI_ANTE", cAnt);
				params.put("NUM_PEDI_REAL_CAB", pedReal);
				params.put("VAL_PORC_RECE_CAB", estReal);
				params.put("NUM_PEDI_RECA_CAB", pedRec);
				params.put("VAL_DIFE_REAL_ESTI", diferencia);
				params.put("TOT_FACT_REGU_CAB", totFacReg);
				params.put("TOT_FACT_BOLS_CAB", totFacBol);
				listProdREC.set(i, params);
			}
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
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

	public LabelValue[] getSiccPeriodoList() {
		return siccPeriodoList;
	}

	public void setSiccPeriodoList(LabelValue[] siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	public boolean isDesactivaZona() {
		return desactivaZona;
	}

	public void setDesactivaZona(boolean desactivaZona) {
		this.desactivaZona = desactivaZona;
	}

	public List getZonaMicaList2() {
		return zonaMicaList2;
	}

	public void setZonaMicaList2(List zonaMicaList2) {
		this.zonaMicaList2 = zonaMicaList2;
	}

	public DataTableModel getZonaMicaDataModel() {
		return zonaMicaDataModel;
	}

	public void setZonaMicaDataModel(DataTableModel zonaMicaDataModel) {
		this.zonaMicaDataModel = zonaMicaDataModel;
	}

	public List getSubTable() {
		return subTable;
	}

	public void setSubTable(List subTable) {
		this.subTable = subTable;
	}

	/**
	 * @return the recProdList
	 */
	public List getRecProdList() {
		return recProdList;
	}

	/**
	 * @param recProdList the recProdList to set
	 */
	public void setRecProdList(List recProdList) {
		this.recProdList = recProdList;
	}

	/**
	 * @return the dtRECProdlist
	 */
	public DataTableModel getDtRECProdlist() {
		return dtRECProdlist;
	}

	/**
	 * @param dtRECProdlist the dtRECProdlist to set
	 */
	public void setDtRECProdlist(DataTableModel dtRECProdlist) {
		this.dtRECProdlist = dtRECProdlist;
	}

	/**
	 * @return the mostrarListaZona
	 */
	public Boolean getMostrarListaZona() {
		return mostrarListaZona;
	}

	/**
	 * @param mostrarListaZona the mostrarListaZona to set
	 */
	public void setMostrarListaZona(Boolean mostrarListaZona) {
		this.mostrarListaZona = mostrarListaZona;
	}

	/**
	 * @return the mostrarListaRegion
	 */
	public Boolean getMostrarListaRegion() {
		return mostrarListaRegion;
	}

	/**
	 * @param mostrarListaRegion the mostrarListaRegion to set
	 */
	public void setMostrarListaRegion(Boolean mostrarListaRegion) {
		this.mostrarListaRegion = mostrarListaRegion;
	}

	/**
	 * @return the recProdListZona
	 */
	public List getRecProdListZona() {
		return recProdListZona;
	}

	/**
	 * @param recProdListZona the recProdListZona to set
	 */
	public void setRecProdListZona(List recProdListZona) {
		this.recProdListZona = recProdListZona;
	}

	/**
	 * @return the dtRECProdlistZona
	 */
	public DataTableModel getDtRECProdlistZona() {
		return dtRECProdlistZona;
	}

	/**
	 * @param dtRECProdlistZona the dtRECProdlistZona to set
	 */
	public void setDtRECProdlistZona(DataTableModel dtRECProdlistZona) {
		this.dtRECProdlistZona = dtRECProdlistZona;
	}

	
	
	
	

}
