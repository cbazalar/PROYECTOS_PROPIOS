
package biz.belcorp.ssicc.web.sisicc.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.primefaces.event.data.PageEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ConsultaPolizas;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.scsicc.action.ReporteRECEnviaCabeceraProductosAction;
import biz.belcorp.ssicc.web.scsicc.action.ReporteRECEnviaDetalleProductosAction;
import biz.belcorp.ssicc.web.sisicc.form.InterfazRECProductosReclamadosForm;

/**
 * <p>
 * <a href="InterfazRECProductosReclamadosAction.java.html"> <i>View Source </i>
 * </a>
 * </p>
 *
  */
@ManagedBean
@SessionScoped
public class InterfazRECProductosReclamadosAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = 4244216716479325587L;
	

	private LabelValue[] siccRegionList;
    private LabelValue[] siccZonaList = {};
    private List siccOperacionesList;
    private List siccTipoIngresoList;
    private List siccSubaccesoList;
    private Integer subTotalGuias=0;
    private Integer subUnidadAdevolver=0;
    private Integer subToGuias=0;
    private Integer subToUnidadAdevolver=0;
    private boolean mostrarToolbarAdicional;
    private List listInterfazRecProducto;
    private Integer pagina=0;
    private Boolean mostrarDatatable;
    
    @ManagedProperty(value = "#{reporteRECEnviaCabeceraProductosAction}")
    private ReporteRECEnviaCabeceraProductosAction reporteRECEnviaCabeceraProductos;

    @ManagedProperty(value = "#{reporteRECEnviaDetalleProductosAction}")
    private ReporteRECEnviaDetalleProductosAction reporteRECEnviaDetalleProductos;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		return new InterfazRECProductosReclamadosForm();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	protected void setViewAtributes() throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		this.mostrarDatatable = false;
		pagina=0;
	     this.subTotalGuias = 0;
			this.subUnidadAdevolver= 0;
		this.listInterfazRecProducto = new ArrayList();
		 this.mostrarBotonBuscar=true;
		 this.mostrarListaBusqueda=true;
		 setMostrarToolbarAdicional(false);
		 this.mostrarListaBusqueda = false;
	
		  InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		  MantenimientoRECOperacionService operacionService = (MantenimientoRECOperacionService) getBean("spusicc.mantenimientoRECOperacionService");

		  
		  Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
	      Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
	      
	      Map criteriaSubAcceso = new HashMap();
	      this.siccSubaccesoList = interfazSiCCService.getInterfazRECProductosReclamadosSubAccesos(criteriaSubAcceso);

	      Map criteriaTipoIngreso = new HashMap();
	      criteriaTipoIngreso.put("codigoISO", usuario.getIdioma().getCodigoISO());
	      criteriaTipoIngreso.put("codigoTipoIng", Constants.CODIGO_TIPO_INGRESO_M);

	      this.siccTipoIngresoList = interfazSiCCService.getTiposIngresoByCodigoISO(criteriaTipoIngreso);

	       Map criteriaOperacion = new HashMap();
	       criteriaOperacion.put("codigoPais", pais.getCodigo());
	      
	       this.siccOperacionesList = operacionService.getOperacionesHomologadasByCodigoPais(criteriaOperacion);

	       AjaxService aSvc = (AjaxService) getBean("ajaxService");
	       this.siccRegionList = aSvc.getRegionesByPais(pais.getCodigo());
	 
		   InterfazRECProductosReclamadosForm f = (InterfazRECProductosReclamadosForm) this.formInterfaz;
//		   f.setRegiones(new String[1]);
//		   f.setZonas(new String[1]);
//		   f.setRegionListSearch(new String[1]);
		   //f.setZonaListSearch(new String[1]);
		   
		   f.setCodigoPais(pais.getCodigo());
		   f.setFechaInicio(new Date(System.currentTimeMillis()));
           f.setFechaFin(new Date(System.currentTimeMillis()));
		   f.setSubAccesos(new String[]{ Constants.REC_SUBACCESO_DEFAULT });
		   f.setTipoIngreso(Constants.CODIGO_TIPO_INGRESO_LINEA);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#setValidarFind()
	 */
	@Override
	public String setValidarFind() {
	
       InterfazRECProductosReclamadosForm form = (InterfazRECProductosReclamadosForm) this.formInterfaz;
       log.debug(form.getFechaFin());
		Date fechaInicio = form.getFechaInicio();
	    Date fechaFinal = form.getFechaFin();
	
	    if (fechaInicio.compareTo(fechaFinal) > 0) {
	    	String mensaje = getResourceMessage("errors.compare.dates");
			return mensaje;
        
		}  	  
	      
	    return null;
	
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#setValidarInterfaz()
	 */
	@Override
	public String setValidarInterfaz() {
		InterfazRECProductosReclamadosForm form = (InterfazRECProductosReclamadosForm) this.formInterfaz;
	       log.debug(form.getFechaFin());
			Date fechaInicio = form.getFechaInicio();
		    Date fechaFinal = form.getFechaFin();
		
		    if (fechaInicio.compareTo(fechaFinal) > 0) {
		    	String mensaje = getResourceMessage("errors.compare.dates");
				return mensaje;
	        
			}  	  
		      
		return null;
	}

	 /**
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		try {
			log.debug(">>showZonasxRegion ");
			InterfazRECProductosReclamadosForm form = (InterfazRECProductosReclamadosForm) this.formInterfaz;
			String[] regiones = (String []) val.getNewValue();		
		      
			if(!ArrayUtils.isEmpty(regiones)){
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, 
						Constants.CODIGO_CANAL_DEFAULT, regiones, Constants.FORMATO_TOTAL));
			}else{
				setSiccZonaList(null);
			}
			
			form.setZonas(null);
		} catch (Exception e) {
			 this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception{

        params =  super.prepareParamsBeforeExecute(params, form);
    	
        InterfazRECProductosReclamadosForm f = (InterfazRECProductosReclamadosForm) this.formInterfaz;

        // Obtenemos el objeto que encapsula lista con los productos
       /* PagedListHolder listHolder = (PagedListHolder) request.getSession().getAttribute(Constants.PRODUCT_PAGED_RECLAM_LIST);

        // Tambien obtenemos la lista con los productos  
        List codigos = (List) request.getSession().getAttribute(Constants.CODS_PRODUCT_RECLAM_LIST);

        // Ahora cargamos todas los productos, seleccionadas o no seleccionadas
        if (codigos != null) {
            List codigosProd = new ArrayList();
            List list = new ArrayList();

            for (int i = 1; i <= listHolder.getNrOfElements(); i++) {
                String codigoList = request.getParameter("codigoList" + i);
                String estado = request.getParameter("estadoList" + i);

                if (StringUtils.isBlank(estado)) {
                    estado = Constants.ESTADO_INACTIVO;
                } else {
                    list.add(codigoList);
                }

                Map map = new HashMap();
                map.put("codigoCOL", codigoList);
                map.put("estado", estado);
                codigosProd.add(map);
            }

            f.setSelectedItems((String[]) list.toArray(new String[list.size()]));

            // Actualizamos la lista en la sesion
            request.getSession().setAttribute(Constants.CODS_PRODUCT_RECLAM_LIST,codigosProd);
        }


        // Agrego los parametros necesarios
        

 
        params.put("selectedItems", f.getSelectedItems());
 */
        
        params.put("regionList",(f.getRegiones() == null) ? new ArrayList(): Arrays.asList(f.getRegiones()));
        
        if(f.getZonas()!= null){
		    if(f.getZonas().length == 1){
		    	if(f.getZonas()[0] == null || f.getZonas()[0].compareToIgnoreCase("") == 0){
		    		f.setZonas(null);
		    	}
		    }
		}

        params.put("zonaList",(f.getZonas()== null) ? new ArrayList(): Arrays.asList(f.getZonas()));

        if (f.getSubAccesos().length > 0 && f.getSubAccesos()[0].equalsIgnoreCase(Constants.REC_SUBACCESO_DEFAULT) ){
        	params.put("subacList",new ArrayList());
        	params.put("subacGZ",Constants.NUMERO_UNO);        	
        }else {
        	params.put("subacList",
                    (f.getSubAccesos() == null) ? new ArrayList()
                                                : Arrays.asList(f.getSubAccesos()));
        	params.put("subacGZ",null);            
        }
        
        MantenimientoRECOperacionService operacionService = (MantenimientoRECOperacionService) getBean("spusicc.mantenimientoRECOperacionService");
        
        Map criteriaOperacion = new HashMap();
        criteriaOperacion.put("codigoPais", f.getCodigoPais());
        criteriaOperacion.put("codigoOperacionHomologada", f.getOperacion());        
        
        params.put("operacionList", operacionService.getOperacionesByOperacionHomologada(criteriaOperacion));
        
        params.put("tipoIngreso", f.getTipoIngreso());
        params.put("fechaInicio", DateFormatUtils.format(f.getFechaInicio(),"dd/MM/yyyy"));
        params.put("fechaFin", DateFormatUtils.format(f.getFechaFin(),"dd/MM/yyyy"));
        
        
        if (f.getOperacion().equalsIgnoreCase("E")){
        	params.put("errorSacado", "SI");
        }else{
        	params.put("errorSacado", null);
        }
        
       //Se obtiene el numero de secuencia de la sesion para asignarlo al usuario
        String numSecUsuario = operacionService.getNumeroSecuenciaUsuario();

        //Guardamos el numero de secuencia en el map
        params.put("numeroSecuencia", numSecUsuario);
        
        params.put("usuario", this.mPantallaPrincipalBean.getCurrentUser());
        params.put("codigoPais", this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
        
        if (log.isDebugEnabled()) {
            log.debug("######## form " + f);
        }
		

		return params;
	}
	
	/**
	 * Metodo que captura la pagina del datatable
	 * 
	 * @param e
	 */
	public void onPage(PageEvent e) {
		int paginas = e.getPage();
		this.pagina = paginas;
		
		calcularSubtotales();
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#setFindAttributes()
	 */
	protected List setFindAttributes() throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("Entering 'search' method");
        }
		this.mostrarDatatable = false;

		this.listInterfazRecProducto = new ArrayList();

        this.subTotalGuias = 0;
		this.subUnidadAdevolver= 0;
		this.subToGuias = 0;
		this.subToUnidadAdevolver = 0;
        InterfazRECProductosReclamadosForm f = (InterfazRECProductosReclamadosForm) this.formInterfaz;
        
        GenericoService genericoService = (GenericoService) getBean("genericoService");
		ParametroPais parametroPais = new ParametroPais();
		
		parametroPais.setCodigoPais(f.getCodigoPais());
		parametroPais.setCodigoSistema(Constants.CODIGO_SISTEMA_REC);
		parametroPais.setCodigoParametro(Constants.CODIGO_PARAMETRO_MUESTRA_STOCK_FISICO);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		
		List parametros = genericoService.getParametrosPais(parametroPais);
        
		String parametroMuestraStockFisico = "";
		
		if(parametros != null && parametros.size() > 0) {
			ParametroPais p = (ParametroPais)parametros.get(0);
			parametroMuestraStockFisico = p.getValorParametro();
		}
	
        
        f.setSelectedItems(new String[] {  });

        List listProdRECxLote = null;

        // Obtenemos las propiedades del bean como un 'Map'
        Map criteria = BeanUtils.describe(f);
        
        criteria.put("parametroMuestraStockFisico", parametroMuestraStockFisico);

        // La busqueda solo la realizaremos en los sistemas activos
        criteria.put("estado", Constants.ESTADO_ACTIVO);
        criteria.put("codigoLineaList", new ArrayList());
        criteria.put("regionList",(f.getRegiones() == null) ? new ArrayList(): Arrays.asList(f.getRegiones()));
        
        if(f.getZonas()!= null){
		    if(f.getZonas().length == 1){
		    	if(f.getZonas()[0] == null || f.getZonas()[0].compareToIgnoreCase("") == 0){
		    		f.setZonas(null);
		    	}
		    }
		}

        criteria.put("zonaList",(f.getZonas()== null) ? new ArrayList(): Arrays.asList(f.getZonas()));
      //  f.setZonaList2((f.getZonas()== null) ? new ArrayList(): Arrays.asList(f.getZonas()));

        if (f.getSubAccesos()!=null) {
	        if (f.getSubAccesos().length > 0 && f.getSubAccesos()[0].equalsIgnoreCase(Constants.REC_SUBACCESO_DEFAULT) ){
	        	criteria.put("subacList",new ArrayList());
	        	criteria.put("subacGZ",Constants.NUMERO_UNO);        	
	        }else{
	        	criteria.put("subacList", Arrays.asList(f.getSubAccesos()));
	        	criteria.put("subacGZ",null);   
	        }
        }else {
            criteria.put("subacList",new ArrayList());
        	criteria.put("subacGZ",null);            
        }

        MantenimientoRECOperacionService operacionService = (MantenimientoRECOperacionService) getBean("spusicc.mantenimientoRECOperacionService");
        
        Map criteriaOperacion = new HashMap();
        criteriaOperacion.put("codigoPais",f.getCodigoPais());
        criteriaOperacion.put("codigoOperacionHomologada", f.getOperacion()); 
        
        if (f.getOperacion().equalsIgnoreCase("E")){
            criteria.put("errorSacado", "SI");
        }else{
        	criteria.put("errorSacado", null);
        }
        
        criteria.put("operacionList", operacionService.getOperacionesByOperacionHomologada(criteriaOperacion));
        
        // Modificamos los valores que requieren el caracter '%'
        if (StringUtils.isNotBlank(f.getCodigoSistema())) {
            criteria.put("codigoSistema", f.getCodigoSistema() + "%");
        }
	
        criteria.put("fechaInicio", DateFormatUtils.format(f.getFechaInicio(),"dd/MM/yyyy"));
        criteria.put("fechaFin", DateFormatUtils.format(f.getFechaFin(),"dd/MM/yyyy"));

        if (log.isDebugEnabled()) {
            log.debug("criteria search " + criteria.toString());
        }

        InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        
        listProdRECxLote = interfazSiCCService.getRECProductosListxLote(criteria);
   	 	List productosRecListxLoteConsolidado = listProdRECxLote;
		this.listInterfazRecProducto = productosRecListxLoteConsolidado;

        if(listProdRECxLote.size() > 0){
    		this.mostrarDatatable = true;

             if (CollectionUtils.isEmpty(productosRecListxLoteConsolidado)) {
             	setMostrarToolbarAdicional(false);
     		}else{
     			setMostrarToolbarAdicional(true);
     		}
             
             for (Object obj : productosRecListxLoteConsolidado) {
             	  Map map = (Map) obj;
             	  BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
             	  BigDecimal unidadAdevolver = (BigDecimal) map.get("unidadAdevolver");
     			  this.subTotalGuias+= totalGuia.intValue();
     			  this.subUnidadAdevolver+= unidadAdevolver.intValue();
     		}
             
             //subtotales
             
             int valorPagina = 0;
     		int filasMuestra = 10;
     		int valorFinal = 0;		
     		int valorInicial = 0;
     			
     		valorInicial = valorPagina;
     		valorFinal = filasMuestra + valorInicial;		

     			// validando si se recibe menos de lo paginado
     			if (productosRecListxLoteConsolidado.size() <= 9) {

     				for (int i = 0; i < productosRecListxLoteConsolidado.size(); i++) {

     					  Map map = (Map) productosRecListxLoteConsolidado.get(i);
     		        	  BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
     		        	  BigDecimal unidadAdevolver = (BigDecimal) map.get("unidadAdevolver");
     					  this.subToGuias+= totalGuia.intValue();
     					  this.subToUnidadAdevolver+= unidadAdevolver.intValue();
     				}
     			}
     			// si es igual al paginado, calcular subtotal
     			else {
     				for (int i = 0; i <= 9; i++) {

     					 Map map = (Map) productosRecListxLoteConsolidado.get(i);
     		        	 BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
     		        	 BigDecimal unidadAdevolver = (BigDecimal) map.get("unidadAdevolver");
     					 this.subToGuias+= totalGuia.intValue();
     					 this.subToUnidadAdevolver+= unidadAdevolver.intValue();
     				}

     			}
             
             
        }
       
        setFilterSearch(f);
        //Seteamos los valores para pasarlos a los reportes
    	this.reporteRECEnviaCabeceraProductos.setMapProperties(criteria);
    	this.reporteRECEnviaDetalleProductos.setMapProperties(criteria);
        
        return productosRecListxLoteConsolidado;
	}
	
	/**
	 * calcular los subtotales
	 */
	public void calcularSubtotales() {
		
		 this.subToGuias = 0;
		  this.subToUnidadAdevolver =0;
		
		int valorPagina = this.pagina + 1;
		int filasMuestra = 10;
		int valorFinal = 0;
		valorFinal = valorPagina * filasMuestra;

		int valorInicial = 0;
		valorInicial = valorFinal - filasMuestra;

		// capturando la ultima pagina
		int ultimapagina = 0;
		boolean ultimaP = false;
		int ultimo = (this.listInterfazRecProducto.size() / 10) + 1;
		int residuoUltimo = (this.listInterfazRecProducto.size() % 10) + valorInicial;
		if (valorPagina == ultimo) {
			ultimaP = true;
		}
		if (residuoUltimo == 0) {

		} else {
			ultimapagina = residuoUltimo;
		}

		// Validando que sea la primera pagina
		if (this.pagina == 0) {
			// validando si se recibe menos de lo paginado
			if (this.listInterfazRecProducto.size() <= 9) {

				for (int i = 0; i < this.listInterfazRecProducto.size(); i++) {

					 Map map = (Map) this.listInterfazRecProducto.get(i);
		        	  BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
		        	  BigDecimal unidadAdevolver = (BigDecimal) map.get("unidadAdevolver");
					  this.subToGuias+= totalGuia.intValue();
					  this.subToUnidadAdevolver+= unidadAdevolver.intValue();
				}
			}
			// si es igual al pagino, calcular subtotal
			else {

				for (int i = 0; i <= 9; i++) {

					Map map = (Map) this.listInterfazRecProducto.get(i);
		        	  BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
		        	  BigDecimal unidadAdevolver = (BigDecimal) map.get("unidadAdevolver");
					  this.subToGuias+= totalGuia.intValue();
					  this.subToUnidadAdevolver+= unidadAdevolver.intValue();
				}

			}

		} 
		//validando que sea la ultima pagina, y que tenga residuo
		else if (ultimaP && residuoUltimo != 0) {
			for (int i = valorInicial; i <= residuoUltimo - 1; i++) {
				Map map = (Map) this.listInterfazRecProducto.get(i);
	        	  BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
	        	  BigDecimal unidadAdevolver = (BigDecimal) map.get("unidadAdevolver");
				  this.subToGuias+= totalGuia.intValue();
				  this.subToUnidadAdevolver+= unidadAdevolver.intValue();
			}
		}

		// si es una lista normal con 10 registro entrara aca.
		else {
			for (int i = valorInicial; i <= valorFinal - 1; i++) {
				Map map = (Map) this.listInterfazRecProducto.get(i);
	        	  BigDecimal totalGuia = (BigDecimal) map.get("totalGuias");
	        	  BigDecimal unidadAdevolver = (BigDecimal) map.get("unidadAdevolver");
				  this.subToGuias+= totalGuia.intValue();
				  this.subToUnidadAdevolver+= unidadAdevolver.intValue();
			}
		}
	}

	
	/**
     * Guarda los filtros seleccionados
     * @param f
     * @param request
     */
    private void setFilterSearch(InterfazRECProductosReclamadosForm f){
    	
    	f.setRegionListSearch(f.getRegiones());
    	String [] zonas = f.getZonas();
    	String cadZona="";
    	for(int i=0;zonas!=null && i<zonas.length;i++){
    	   if(i!=zonas.length-1)	
    		 cadZona += zonas[i]+",";
    	   else
    		 cadZona += zonas[i];  
    	}
    	log.debug("cadena zona "+cadZona);
		//f.setZonaListSearch(cadZona);

	}

    
    public void generarReporteCabecera(ActionEvent actionEvent) throws Exception{
    	this.reporteRECEnviaCabeceraProductos
		.setFormatoExportacion("XLS");
    	reporteRECEnviaCabeceraProductos.executeReporte();
    }
    
    public void generarReporteDetalle(ActionEvent actionEvent) throws Exception{
    	this.reporteRECEnviaDetalleProductos
		.setFormatoExportacion("XLS");
    	reporteRECEnviaDetalleProductos.executeReporte();
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
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}


	/**
	 * @param siccOperacionesList the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}


	/**
	 * @return the siccTipoIngresoList
	 */
	public List getSiccTipoIngresoList() {
		return siccTipoIngresoList;
	}


	/**
	 * @param siccTipoIngresoList the siccTipoIngresoList to set
	 */
	public void setSiccTipoIngresoList(List siccTipoIngresoList) {
		this.siccTipoIngresoList = siccTipoIngresoList;
	}


	/**
	 * @return the siccSubaccesoList
	 */
	public List getSiccSubaccesoList() {
		return siccSubaccesoList;
	}


	/**
	 * @param siccSubaccesoList the siccSubaccesoList to set
	 */
	public void setSiccSubaccesoList(List siccSubaccesoList) {
		this.siccSubaccesoList = siccSubaccesoList;
	}


	/**
	 * @return the subTotalGuias
	 */
	public Integer getSubTotalGuias() {
		return subTotalGuias;
	}


	/**
	 * @param subTotalGuias the subTotalGuias to set
	 */
	public void setSubTotalGuias(Integer subTotalGuias) {
		this.subTotalGuias = subTotalGuias;
	}


	/**
	 * @return the subUnidadAdevolver
	 */
	public Integer getSubUnidadAdevolver() {
		return subUnidadAdevolver;
	}


	/**
	 * @param subUnidadAdevolver the subUnidadAdevolver to set
	 */
	public void setSubUnidadAdevolver(Integer subUnidadAdevolver) {
		this.subUnidadAdevolver = subUnidadAdevolver;
	}


	/**
	 * @return the reporteRECEnviaCabeceraProductos
	 */
	public ReporteRECEnviaCabeceraProductosAction getReporteRECEnviaCabeceraProductos() {
		return reporteRECEnviaCabeceraProductos;
	}


	/**
	 * @param reporteRECEnviaCabeceraProductos the reporteRECEnviaCabeceraProductos to set
	 */
	public void setReporteRECEnviaCabeceraProductos(
			ReporteRECEnviaCabeceraProductosAction reporteRECEnviaCabeceraProductos) {
		this.reporteRECEnviaCabeceraProductos = reporteRECEnviaCabeceraProductos;
	}


	/**
	 * @return the reporteRECEnviaDetalleProductos
	 */
	public ReporteRECEnviaDetalleProductosAction getReporteRECEnviaDetalleProductos() {
		return reporteRECEnviaDetalleProductos;
	}


	/**
	 * @param reporteRECEnviaDetalleProductos the reporteRECEnviaDetalleProductos to set
	 */
	public void setReporteRECEnviaDetalleProductos(
			ReporteRECEnviaDetalleProductosAction reporteRECEnviaDetalleProductos) {
		this.reporteRECEnviaDetalleProductos = reporteRECEnviaDetalleProductos;
	}


	/**
	 * @return the mostrarToolbarAdicional
	 */
	public boolean isMostrarToolbarAdicional() {
		return mostrarToolbarAdicional;
	}


	/**
	 * @param mostrarToolbarAdicional the mostrarToolbarAdicional to set
	 */
	public void setMostrarToolbarAdicional(boolean mostrarToolbarAdicional) {
		this.mostrarToolbarAdicional = mostrarToolbarAdicional;
	}


	/**
	 * @return the subToGuias
	 */
	public Integer getSubToGuias() {
		return subToGuias;
	}


	/**
	 * @param subToGuias the subToGuias to set
	 */
	public void setSubToGuias(Integer subToGuias) {
		this.subToGuias = subToGuias;
	}


	/**
	 * @return the subToUnidadAdevolver
	 */
	public Integer getSubToUnidadAdevolver() {
		return subToUnidadAdevolver;
	}


	/**
	 * @param subToUnidadAdevolver the subToUnidadAdevolver to set
	 */
	public void setSubToUnidadAdevolver(Integer subToUnidadAdevolver) {
		this.subToUnidadAdevolver = subToUnidadAdevolver;
	}


	/**
	 * @return the listInterfazRecProducto
	 */
	public List getListInterfazRecProducto() {
		return listInterfazRecProducto;
	}


	/**
	 * @param listInterfazRecProducto the listInterfazRecProducto to set
	 */
	public void setListInterfazRecProducto(List listInterfazRecProducto) {
		this.listInterfazRecProducto = listInterfazRecProducto;
	}


	/**
	 * @return the pagina
	 */
	public Integer getPagina() {
		return pagina;
	}


	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}


	/**
	 * @return the mostrarDatatable
	 */
	public Boolean getMostrarDatatable() {
		return mostrarDatatable;
	}


	/**
	 * @param mostrarDatatable the mostrarDatatable to set
	 */
	public void setMostrarDatatable(Boolean mostrarDatatable) {
		this.mostrarDatatable = mostrarDatatable;
	}
	
	
}