package biz.belcorp.ssicc.web.scsicc.form;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.ConsultaFuenteVentas;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author UsuarioSig
 *
 */
public class ConsultaSABFuenteVentasPrevistaForm extends BaseSearchForm implements Serializable{

		
		private static final long serialVersionUID = 3928589448568244800L;		
		
		private String codigoPais;
	    private String nombrePais;
	    
	    private String codigoSociedad;	    
	    private String codigoAlmacen;
	    private String codigoMarca;    
	    private String codigoCanal;
	    private String codigoAnio;
	    private String codigoRangoPeriodo;
	    
	    private String codigoRegion;
	    private String codigoZona;
	   
	    private String tipoSeleccion;
	    
	    
	    public ConsultaSABFuenteVentasPrevistaForm(){
	    	
	    	 	SimpleDateFormat sdf    = new SimpleDateFormat("dd/MM/yyyy");
		        this.codigoSociedad     = Constants.CODIGO_SOCIEDAD_DEFAULT;
		        this.codigoMarca		= Constants.CODIGO_MARCA_DEFAULT;
		        this.codigoCanal        = Constants.CODIGO_CANAL_DEFAULT;
		        this.codigoAnio= sdf.format(new Date(System.currentTimeMillis())).substring(6,10);
		      
		        log.debug("Constants.CODIGO_ALMACEN_DEFAULT"+ Constants.CODIGO_ALMACEN_DEFAULT);
		        this.codigoAlmacen = Constants.CODIGO_ALMACEN_DEFAULT;
		      
	    }
	    
	    	   
	    public String getCodigoAlmacen() {
	        return codigoAlmacen;
	    }
	    public void setCodigoAlmacen(String codigoAlmacen) {
	        this.codigoAlmacen = codigoAlmacen;
	    }

	    public String getCodigoAnio() {
	        return codigoAnio;
	    }
	    public void setCodigoAnio(String codigoAnio) {
	        this.codigoAnio = codigoAnio;
	    }

	    public String getCodigoCanal() {
	        return codigoCanal;
	    }
	    public void setCodigoCanal(String codigoCanal) {
	        this.codigoCanal = codigoCanal;
	    }
	    public String getCodigoPais() {
	        return codigoPais;
	    }
	    public void setCodigoPais(String codigoPais) {
	        this.codigoPais = codigoPais;
	    }
	    public String getCodigoRangoPeriodo() {
	        return codigoRangoPeriodo;
	    }
	    public void setCodigoRangoPeriodo(String codigoRangoPeriodo) {
	        this.codigoRangoPeriodo = codigoRangoPeriodo;
	    }
	    
	    public String getCodigoSociedad() {
	        return codigoSociedad;
	    }
	    public void setCodigoSociedad(String codigoSociedad) {
	        this.codigoSociedad = codigoSociedad;
	    }
	    public String getNombrePais() {
	        return nombrePais;
	    }
	    public void setNombrePais(String nombrePais) {
	        this.nombrePais = nombrePais;
	    }	    
	    public String getTipoSeleccion() {
	        return tipoSeleccion;
	    }
	    public void setTipoSeleccion(String tipoSeleccion) {
	        this.tipoSeleccion = tipoSeleccion;
	    }	    
	    public String getCodigoZona() {
	        return codigoZona;
	    }
	    public void setCodigoZona(String codigoZona) {
	        this.codigoZona = codigoZona;
	    }

	    public String getCodigoRegion() {
	        return codigoRegion;
	    }
	    public void setCodigoRegion(String codigoRegion) {
	        this.codigoRegion = codigoRegion;
	    }
	   
	    
	    // Visualizacion de Datos
	       
	    // Datos para la Matriz
	    private String c0="";
	    private String c1="";
	    private String c2="";
	    private String c3="";
	    private String c4="";
	    private String c5="";
	    private String c6="";
	    private String c7="";
	    
	    private String c1f1="0";
	    private String c1f2="0";
	    private String c1f3="0";
	    private String c1f4="0";
	    private String c1f5="0";
	    private String c1f6="0";
	    private String c1f7="0";
	    private String c1f8="0";
	    private String c1f9="0";
	    private String c1f10="0";
	    private String c1f11="0";
	    private String c1f12="0";
	    private String c1f13="0";
	    private String c1f14="0";
	    private String c1f15="0";
	    private String c1f16="0";
	    private String c1f17="0";
	    
	 // Ini efernandezo 
		private String c1f18 = "0";
		private String c1f19 = "0";
		private String c1f20 = "0";
		private String c1f21 = "0";
		private String c1f22 = "0";
		private String c1f23 = "0";
		private String c1f24 = "0";
		private String c1f25 = "0";
		private String c1f26 = "0";
		private String c1f27 = "0";
		private String c1f28 = "0";
	// Fin efernandezo

	    private String c2f1="0";
	    private String c2f2="0";
	    private String c2f3="0";
	    private String c2f4="0";
	    private String c2f5="0";
	    private String c2f6="0";
	    private String c2f7="0";
	    private String c2f8="0";
	    private String c2f9="0";
	    private String c2f10="0";
	    private String c2f11="0";
	    private String c2f12="0";
	    private String c2f13="0";
	    private String c2f14="0";
	    private String c2f15="0";
	    private String c2f16="0";
	    private String c2f17="0";
	    
	    // Ini efernandezo 
		private String c2f18 = "0";
		private String c2f19 = "0";
		private String c2f20 = "0";
		private String c2f21 = "0";
		private String c2f22 = "0";
		private String c2f23 = "0";
		private String c2f24 = "0";
		private String c2f25 = "0";
		private String c2f26 = "0";
		private String c2f27 = "0";
		private String c2f28 = "0";
		// Fin efernandezo   
	    
	    private String c3f1="0";
	    private String c3f2="0";
	    private String c3f3="0";
	    private String c3f4="0";
	    private String c3f5="0";
	    private String c3f6="0";
	    private String c3f7="0";
	    private String c3f8="0";
	    private String c3f9="0";
	    private String c3f10="0";
	    private String c3f11="0";
	    private String c3f12="0";
	    private String c3f13="0";
	    private String c3f14="0";
	    private String c3f15="0";
	    private String c3f16="0";
	    private String c3f17="0";
	    
	    // Ini efernandezo 
		private String c3f18 = "0";
		private String c3f19 = "0";
		private String c3f20 = "0";
		private String c3f21 = "0";
		private String c3f22 = "0";
		private String c3f23 = "0";
		private String c3f24 = "0";
		private String c3f25 = "0";
		private String c3f26 = "0";
		private String c3f27 = "0";
		private String c3f28 = "0";
		// Fin efernandezo     
	    
	    private String c4f1="0";
	    private String c4f2="0";
	    private String c4f3="0";
	    private String c4f4="0";
	    private String c4f5="0";
	    private String c4f6="0";
	    private String c4f7="0";
	    private String c4f8="0";
	    private String c4f9="0";
	    private String c4f10="0";
	    private String c4f11="0";
	    private String c4f12="0";
	    private String c4f13="0";
	    private String c4f14="0";
	    private String c4f15="0";
	    private String c4f16="0";
	    private String c4f17="0";
	        
	    // Ini efernandezo 
		private String c4f18 = "0";
		private String c4f19 = "0";
		private String c4f20 = "0";
		private String c4f21 = "0";
		private String c4f22 = "0";
		private String c4f23 = "0";
		private String c4f24 = "0";
		private String c4f25 = "0";
		private String c4f26 = "0";
		private String c4f27 = "0";
		private String c4f28 = "0";
		// Fin efernandezo     
	    
	    private String c5f1="0";
	    private String c5f2="0";
	    private String c5f3="0";
	    private String c5f4="0";
	    private String c5f5="0";
	    private String c5f6="0";
	    private String c5f7="0";
	    private String c5f8="0";
	    private String c5f9="0";
	    private String c5f10="0";
	    private String c5f11="0";
	    private String c5f12="0";
	    private String c5f13="0";
	    private String c5f14="0";
	    private String c5f15="0";
	    private String c5f16="0";
	    private String c5f17="0";
	    
	    // Ini efernandezo 
		private String c5f18 = "0";
		private String c5f19 = "0";
		private String c5f20 = "0";
		private String c5f21 = "0";
		private String c5f22 = "0";
		private String c5f23 = "0";
		private String c5f24 = "0";
		private String c5f25 = "0";
		private String c5f26 = "0";
		private String c5f27 = "0";
		private String c5f28 = "0";
		// Fin efernandezo    
	    
	    private String c6f1="0";
	    private String c6f2="0";
	    private String c6f3="0";
	    private String c6f4="0";
	    private String c6f5="0";
	    private String c6f6="0";
	    private String c6f7="0";
	    private String c6f8="0";
	    private String c6f9="0";
	    private String c6f10="0";
	    private String c6f11="0";
	    private String c6f12="0";
	    private String c6f13="0";
	    private String c6f14="0";
	    private String c6f15="0";
	    private String c6f16="0";
	    private String c6f17="0";
	    
	    // Ini efernandezo 
		private String c6f18 = "0";
		private String c6f19 = "0";
		private String c6f20 = "0";
		private String c6f21 = "0";
		private String c6f22 = "0";
		private String c6f23 = "0";
		private String c6f24 = "0";
		private String c6f25 = "0";
		private String c6f26 = "0";
		private String c6f27 = "0";
		private String c6f28 = "0";
		// Fin efernandezo 

	    private String c7f1="0";
	    private String c7f2="0";
	    private String c7f3="0";
	    private String c7f4="0";
	    private String c7f5="0";
	    private String c7f6="0";
	    private String c7f7="0";
	    private String c7f8="0";
	    private String c7f9="0";
	    private String c7f10="0";
	    private String c7f11="0";
	    private String c7f12="0";
	    private String c7f13="0";
	    private String c7f14="0";
	    private String c7f15="0";
	    private String c7f16="0";
	    private String c7f17="0";
	    
	    // Ini efernandezo 
		private String c7f18 = "0";
		private String c7f19 = "0";
		private String c7f20 = "0";
		private String c7f21 = "0";
		private String c7f22 = "0";
		private String c7f23 = "0";
		private String c7f24 = "0";
		private String c7f25 = "0";
		private String c7f26 = "0";
		private String c7f27 = "0";
		private String c7f28 = "0";
		// Fin efernandezo
		// Ini efernandezo 
	    
	    private double activasIniciales = 0;
	    private double ingresos = 0;
	    private double reingresos = 0;
	    private double egresosNetos = 0;
	    private double porcentajeEgresosNetos = 0; 
	    private double egresosPuros = 0;
	    private double porcentajeEgresosPuros = 0;
	    private double capitalizacion = 0;
	    private double porcentajeActividad = 0; 
	    private double promedioVenta = 0;
	    private double venta = 0;
	    private double ppu = 0;
	    private double pup = 0;
	    private double unidades = 0;
	    private double pedidos = 0;
	    private double clientes = 0;
	    private double activasFinales = 0;
		
	    private double actividadLider = 0;
	    private long numeroConsultoraCliPrivilege = 0;
	    private long numeroClientesInsPrivilege = 0;
	    private long numeroClientesTrsPrivelege = 0;
	    private long retencionSdoPeriodo = 0;
	    private long retencionTerPeriodo = 0; 
	    private long retencionCuaPeriodo = 0;
	    private long retencionActivas = 0;
	    private double porcentajeRotacion = 0;
	    private long posiblesEgresos = 0;
	    private long retencionPosEgresos = 0;
	    // Fin efernandezo
	    // Recibe una lista y convierte a Matriz
	    public void listaACampos(ArrayList lista)
	    {  ConsultaFuenteVentas fuenteVentas;
	       if (lista!=null)
	       if (lista.size()>0)
	          {
	               fuenteVentas = new ConsultaFuenteVentas();
	               fuenteVentas = (ConsultaFuenteVentas)lista.get(0);
	               this.c1  =String.valueOf(fuenteVentas.getPeriodo());
	               this.c1f1=String.valueOf((int)fuenteVentas.getActivasIniciales());
	               this.c1f2=String.valueOf((int)fuenteVentas.getIngresos());
	               this.c1f3=String.valueOf(((int)(fuenteVentas.getReingresos()*100))/100);
	               this.c1f4=String.valueOf((int) fuenteVentas.getEgresosNetos() );
	               this.c1f5=String.valueOf( fuenteVentas.getPorcentajeEgresosNetos() );
	               this.c1f6=String.valueOf( fuenteVentas.getEgresosPuros() );
	               this.c1f7=String.valueOf( fuenteVentas.getPorcentajeEgresosPuros() );
	               this.c1f8=String.valueOf( fuenteVentas.getCapitalizacion() );
	               this.c1f9=String.valueOf( fuenteVentas.getPorcentajeActividad() );
	               this.c1f10=String.valueOf( fuenteVentas.getPromedioVenta() );
	               this.c1f11=String.valueOf( fuenteVentas.getVenta() );
	               this.c1f12=String.valueOf( fuenteVentas.getPpu() );
	               this.c1f13=String.valueOf( fuenteVentas.getPup() );
	               this.c1f14=String.valueOf((int) fuenteVentas.getUnidades() );
	               this.c1f15=String.valueOf((int) fuenteVentas.getPedidos() );
	               this.c1f16=String.valueOf((int) fuenteVentas.getClientes() );
	               this.c1f17=String.valueOf((int) fuenteVentas.getActivasFinales() );
	               /*Ini efernandezo*/
	               this.c1f18 =String.valueOf(fuenteVentas.getActividadLider());
	               this.c1f19 =String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
	               this.c1f20 =String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
	               this.c1f21 =String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
	               this.c1f22 =String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
	               this.c1f23 =String.valueOf(fuenteVentas.getRetencionTerPeriodo());
	               this.c1f24 =String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
	               this.c1f25 =String.valueOf(fuenteVentas.getRetencionActivas());
	               this.c1f26 =String.valueOf(fuenteVentas.getPorcentajeRotacion());
	               this.c1f27 =String.valueOf(fuenteVentas.getPosiblesEgresos());
	               this.c1f28 =String.valueOf(fuenteVentas.getRetencionPosEgresos());
	               
	               
	               activasIniciales 	   = activasIniciales 		+ (int)fuenteVentas.getActivasIniciales();    
	               ingresos                = ingresos               + (int)fuenteVentas.getIngresos();            
	               reingresos              = reingresos             + ((((int)fuenteVentas.getReingresos()*100))/100);
	               egresosNetos            = egresosNetos           + (int)fuenteVentas.getEgresosNetos();       
	               porcentajeEgresosNetos  = porcentajeEgresosNetos + fuenteVentas.getPorcentajeEgresosNetos();  
	               egresosPuros            = egresosPuros           + fuenteVentas.getEgresosPuros();            
	               porcentajeEgresosPuros  = porcentajeEgresosPuros + fuenteVentas.getPorcentajeEgresosPuros();  
	               capitalizacion          = capitalizacion         + fuenteVentas.getCapitalizacion();          
	               porcentajeActividad     = porcentajeActividad    + fuenteVentas.getPorcentajeActividad();     
	               promedioVenta           = promedioVenta          + fuenteVentas.getPromedioVenta();           
	               venta                   = venta                  + fuenteVentas.getVenta();                   
	               ppu                     = ppu                    + fuenteVentas.getPpu();                     
	               pup                     = pup                    + fuenteVentas.getPup();                     
	               unidades                = unidades               + fuenteVentas.getUnidades();                
	               pedidos                 = pedidos                + fuenteVentas.getPedidos();                 
	               clientes                = clientes               + fuenteVentas.getClientes();                
	               activasFinales          = activasFinales         + fuenteVentas.getActivasFinales();                       
	               
	               
	               actividadLider 				= actividadLider 			   + fuenteVentas.getActividadLider();
	               numeroConsultoraCliPrivilege = numeroConsultoraCliPrivilege + fuenteVentas.getNumeroConsultoraCliPrivilege();
	               numeroClientesInsPrivilege   = numeroClientesInsPrivilege   + fuenteVentas.getNumeroClientesInsPrivilege();               
	               numeroClientesTrsPrivelege   = numeroClientesTrsPrivelege   + fuenteVentas.getNumeroClientesTrsPrivelege();
	               retencionSdoPeriodo          = retencionSdoPeriodo          + fuenteVentas.getRetencionSdoPeriodo();
	               retencionTerPeriodo          = retencionTerPeriodo          + fuenteVentas.getRetencionTerPeriodo();
	               retencionCuaPeriodo          = retencionCuaPeriodo          + fuenteVentas.getRetencionCuaPeriodo();
	               retencionActivas             = retencionActivas             + fuenteVentas.getRetencionActivas();
	               porcentajeRotacion           = porcentajeRotacion           + fuenteVentas.getPorcentajeRotacion();
	               posiblesEgresos              = posiblesEgresos              + fuenteVentas.getPosiblesEgresos ();
	               retencionPosEgresos          = retencionPosEgresos          + fuenteVentas.getRetencionPosEgresos();

	               
	               /*Fin efernandezo*/
	               if (lista.size()>1)
	               {
	               fuenteVentas = new ConsultaFuenteVentas();
	               fuenteVentas = (ConsultaFuenteVentas)lista.get(1);
	               this.c2  =String.valueOf(fuenteVentas.getPeriodo());
	               this.c2f1=String.valueOf((int)fuenteVentas.getActivasIniciales());
	               this.c2f2=String.valueOf((int) fuenteVentas.getIngresos() );
	               this.c2f3=String.valueOf((int)fuenteVentas.getReingresos() );
	               this.c2f4=String.valueOf((int) fuenteVentas.getEgresosNetos() );
	               this.c2f5=String.valueOf( fuenteVentas.getPorcentajeEgresosNetos() );
	               this.c2f6=String.valueOf( fuenteVentas.getEgresosPuros() );
	               this.c2f7=String.valueOf( fuenteVentas.getPorcentajeEgresosPuros() );
	               this.c2f8=String.valueOf( fuenteVentas.getCapitalizacion() );
	               this.c2f9=String.valueOf( fuenteVentas.getPorcentajeActividad() );
	               this.c2f10=String.valueOf( fuenteVentas.getPromedioVenta() );
	               this.c2f11=String.valueOf( fuenteVentas.getVenta() );
	               this.c2f12=String.valueOf( fuenteVentas.getPpu() );
	               this.c2f13=String.valueOf( fuenteVentas.getPup() );
	               this.c2f14=String.valueOf((int) fuenteVentas.getUnidades() );
	               this.c2f15=String.valueOf((int) fuenteVentas.getPedidos() );
	               this.c2f16=String.valueOf((int) fuenteVentas.getClientes() );
	               this.c2f17=String.valueOf((int) fuenteVentas.getActivasFinales() );
	               /*Ini efernandezo*/
	               this.c2f18 =String.valueOf(fuenteVentas.getActividadLider());
	               this.c2f19 =String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
	               this.c2f20 =String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
	               this.c2f21 =String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
	               this.c2f22 =String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
	               this.c2f23 =String.valueOf(fuenteVentas.getRetencionTerPeriodo());
	               this.c2f24 =String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
	               this.c2f25 =String.valueOf(fuenteVentas.getRetencionActivas());
	               this.c2f26 =String.valueOf(fuenteVentas.getPorcentajeRotacion());
	               this.c2f27 =String.valueOf(fuenteVentas.getPosiblesEgresos());
	               this.c2f28 =String.valueOf(fuenteVentas.getRetencionPosEgresos());

	               activasIniciales 	   = activasIniciales 		+ (int)fuenteVentas.getActivasIniciales();    
	               ingresos                = ingresos               + (int)fuenteVentas.getIngresos();            
	               reingresos              = reingresos             + ((((int)fuenteVentas.getReingresos()*100))/100);
	               egresosNetos            = egresosNetos           + (int)fuenteVentas.getEgresosNetos();       
	               porcentajeEgresosNetos  = porcentajeEgresosNetos + fuenteVentas.getPorcentajeEgresosNetos();  
	               egresosPuros            = egresosPuros           + fuenteVentas.getEgresosPuros();            
	               porcentajeEgresosPuros  = porcentajeEgresosPuros + fuenteVentas.getPorcentajeEgresosPuros();  
	               capitalizacion          = capitalizacion         + fuenteVentas.getCapitalizacion();          
	               porcentajeActividad     = porcentajeActividad    + fuenteVentas.getPorcentajeActividad();     
	               promedioVenta           = promedioVenta          + fuenteVentas.getPromedioVenta();           
	               venta                   = venta                  + fuenteVentas.getVenta();                   
	               ppu                     = ppu                    + fuenteVentas.getPpu();                     
	               pup                     = pup                    + fuenteVentas.getPup();                     
	               unidades                = unidades               + fuenteVentas.getUnidades();                
	               pedidos                 = pedidos                + fuenteVentas.getPedidos();                 
	               clientes                = clientes               + fuenteVentas.getClientes();                
	               activasFinales          = activasFinales         + fuenteVentas.getActivasFinales();                
	               
	               actividadLider 				= actividadLider 			   + fuenteVentas.getActividadLider();
	               numeroConsultoraCliPrivilege = numeroConsultoraCliPrivilege + fuenteVentas.getNumeroConsultoraCliPrivilege();
	               numeroClientesInsPrivilege   = numeroClientesInsPrivilege   + fuenteVentas.getNumeroClientesInsPrivilege();               
	               numeroClientesTrsPrivelege   = numeroClientesTrsPrivelege   + fuenteVentas.getNumeroClientesTrsPrivelege();
	               retencionSdoPeriodo          = retencionSdoPeriodo          + fuenteVentas.getRetencionSdoPeriodo();
	               retencionTerPeriodo          = retencionTerPeriodo          + fuenteVentas.getRetencionTerPeriodo();
	               retencionCuaPeriodo          = retencionCuaPeriodo          + fuenteVentas.getRetencionCuaPeriodo();
	               retencionActivas             = retencionActivas             + fuenteVentas.getRetencionActivas();
	               porcentajeRotacion           = porcentajeRotacion           + fuenteVentas.getPorcentajeRotacion();
	               posiblesEgresos              = posiblesEgresos              + fuenteVentas.getPosiblesEgresos ();
	               retencionPosEgresos          = retencionPosEgresos          + fuenteVentas.getRetencionPosEgresos();               
	               
	               
	               /*Fin efernandezo*/
	               }
	               if (lista.size()>2)
	               {
	               fuenteVentas = new ConsultaFuenteVentas();
	               fuenteVentas = (ConsultaFuenteVentas)lista.get(2);
	               this.c3  =String.valueOf(fuenteVentas.getPeriodo());
	               this.c3f1=String.valueOf((int)fuenteVentas.getActivasIniciales());
	               this.c3f2=String.valueOf((int) fuenteVentas.getIngresos() );
	               this.c3f3=String.valueOf((int) fuenteVentas.getReingresos() );
	               this.c3f4=String.valueOf((int) fuenteVentas.getEgresosNetos() );
	               this.c3f5=String.valueOf( fuenteVentas.getPorcentajeEgresosNetos() );
	               this.c3f6=String.valueOf( fuenteVentas.getEgresosPuros() );
	               this.c3f7=String.valueOf( fuenteVentas.getPorcentajeEgresosPuros() );
	               this.c3f8=String.valueOf( fuenteVentas.getCapitalizacion() );
	               this.c3f9=String.valueOf( fuenteVentas.getPorcentajeActividad() );
	               this.c3f10=String.valueOf( fuenteVentas.getPromedioVenta() );
	               this.c3f11=String.valueOf( fuenteVentas.getVenta() );
	               this.c3f12=String.valueOf( fuenteVentas.getPpu() );
	               this.c3f13=String.valueOf( fuenteVentas.getPup() );
	               this.c3f14=String.valueOf((int) fuenteVentas.getUnidades() );
	               this.c3f15=String.valueOf((int) fuenteVentas.getPedidos() );
	               this.c3f16=String.valueOf((int)fuenteVentas.getClientes() );
	               this.c3f17=String.valueOf((int) fuenteVentas.getActivasFinales() );
	               /*Ini efernandezo*/
	               this.c3f18 =String.valueOf(fuenteVentas.getActividadLider());
	               this.c3f19 =String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
	               this.c3f20 =String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
	               this.c3f21 =String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
	               this.c3f22 =String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
	               this.c3f23 =String.valueOf(fuenteVentas.getRetencionTerPeriodo());
	               this.c3f24 =String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
	               this.c3f25 =String.valueOf(fuenteVentas.getRetencionActivas());
	               this.c3f26 =String.valueOf(fuenteVentas.getPorcentajeRotacion());
	               this.c3f27 =String.valueOf(fuenteVentas.getPosiblesEgresos());
	               this.c3f28 =String.valueOf(fuenteVentas.getRetencionPosEgresos());    
	               
	               activasIniciales 	   = activasIniciales 		+ (int)fuenteVentas.getActivasIniciales();    
	               ingresos                = ingresos               + (int)fuenteVentas.getIngresos();            
	               reingresos              = reingresos             + ((((int)fuenteVentas.getReingresos()*100))/100);
	               egresosNetos            = egresosNetos           + (int)fuenteVentas.getEgresosNetos();       
	               porcentajeEgresosNetos  = porcentajeEgresosNetos + fuenteVentas.getPorcentajeEgresosNetos();  
	               egresosPuros            = egresosPuros           + fuenteVentas.getEgresosPuros();            
	               porcentajeEgresosPuros  = porcentajeEgresosPuros + fuenteVentas.getPorcentajeEgresosPuros();  
	               capitalizacion          = capitalizacion         + fuenteVentas.getCapitalizacion();          
	               porcentajeActividad     = porcentajeActividad    + fuenteVentas.getPorcentajeActividad();     
	               promedioVenta           = promedioVenta          + fuenteVentas.getPromedioVenta();           
	               venta                   = venta                  + fuenteVentas.getVenta();                   
	               ppu                     = ppu                    + fuenteVentas.getPpu();                     
	               pup                     = pup                    + fuenteVentas.getPup();                     
	               unidades                = unidades               + fuenteVentas.getUnidades();                
	               pedidos                 = pedidos                + fuenteVentas.getPedidos();                 
	               clientes                = clientes               + fuenteVentas.getClientes();                
	               activasFinales          = activasFinales         + fuenteVentas.getActivasFinales();                
	               
	               actividadLider 				= actividadLider 			   + fuenteVentas.getActividadLider();
	               numeroConsultoraCliPrivilege = numeroConsultoraCliPrivilege + fuenteVentas.getNumeroConsultoraCliPrivilege();
	               numeroClientesInsPrivilege   = numeroClientesInsPrivilege   + fuenteVentas.getNumeroClientesInsPrivilege();               
	               numeroClientesTrsPrivelege   = numeroClientesTrsPrivelege   + fuenteVentas.getNumeroClientesTrsPrivelege();
	               retencionSdoPeriodo          = retencionSdoPeriodo          + fuenteVentas.getRetencionSdoPeriodo();
	               retencionTerPeriodo          = retencionTerPeriodo          + fuenteVentas.getRetencionTerPeriodo();
	               retencionCuaPeriodo          = retencionCuaPeriodo          + fuenteVentas.getRetencionCuaPeriodo();
	               retencionActivas             = retencionActivas             + fuenteVentas.getRetencionActivas();
	               porcentajeRotacion           = porcentajeRotacion           + fuenteVentas.getPorcentajeRotacion();
	               posiblesEgresos              = posiblesEgresos              + fuenteVentas.getPosiblesEgresos ();
	               retencionPosEgresos          = retencionPosEgresos          + fuenteVentas.getRetencionPosEgresos();
	               
	               /*Fin efernandezo*/
	               }
	               if (lista.size()>3)
	               {
	               fuenteVentas = new ConsultaFuenteVentas();
	               fuenteVentas = (ConsultaFuenteVentas)lista.get(3);
	               this.c4  =String.valueOf(fuenteVentas.getPeriodo());
	               this.c4f1=String.valueOf((int)fuenteVentas.getActivasIniciales());
	               this.c4f2=String.valueOf((int) fuenteVentas.getIngresos() );
	               this.c4f3=String.valueOf((int) fuenteVentas.getReingresos() );
	               this.c4f4=String.valueOf((int) fuenteVentas.getEgresosNetos() );
	               this.c4f5=String.valueOf( fuenteVentas.getPorcentajeEgresosNetos() );
	               this.c4f6=String.valueOf( fuenteVentas.getEgresosPuros() );
	               this.c4f7=String.valueOf( fuenteVentas.getPorcentajeEgresosPuros() );
	               this.c4f8=String.valueOf( fuenteVentas.getCapitalizacion() );
	               this.c4f9=String.valueOf( fuenteVentas.getPorcentajeActividad() );
	               this.c4f10=String.valueOf( fuenteVentas.getPromedioVenta() );
	               this.c4f11=String.valueOf( fuenteVentas.getVenta() );
	               this.c4f12=String.valueOf( fuenteVentas.getPpu() );
	               this.c4f13=String.valueOf( fuenteVentas.getPup() );
	               this.c4f14=String.valueOf((int) fuenteVentas.getUnidades() );
	               this.c4f15=String.valueOf((int) fuenteVentas.getPedidos() );
	               this.c4f16=String.valueOf((int) fuenteVentas.getClientes() );
	               this.c4f17=String.valueOf((int) fuenteVentas.getActivasFinales() );
	               /*Ini efernandezo*/
	               this.c4f18 =String.valueOf(fuenteVentas.getActividadLider());
	               this.c4f19 =String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
	               this.c4f20 =String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
	               this.c4f21 =String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
	               this.c4f22 =String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
	               this.c4f23 =String.valueOf(fuenteVentas.getRetencionTerPeriodo());
	               this.c4f24 =String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
	               this.c4f25 =String.valueOf(fuenteVentas.getRetencionActivas());
	               this.c4f26 =String.valueOf(fuenteVentas.getPorcentajeRotacion());
	               this.c4f27 =String.valueOf(fuenteVentas.getPosiblesEgresos());
	               this.c4f28 =String.valueOf(fuenteVentas.getRetencionPosEgresos());    

	               activasIniciales 	   = activasIniciales 		+ (int)fuenteVentas.getActivasIniciales();    
	               ingresos                = ingresos               + (int)fuenteVentas.getIngresos();            
	               reingresos              = reingresos             + ((((int)fuenteVentas.getReingresos()*100))/100);
	               egresosNetos            = egresosNetos           + (int)fuenteVentas.getEgresosNetos();       
	               porcentajeEgresosNetos  = porcentajeEgresosNetos + fuenteVentas.getPorcentajeEgresosNetos();  
	               egresosPuros            = egresosPuros           + fuenteVentas.getEgresosPuros();            
	               porcentajeEgresosPuros  = porcentajeEgresosPuros + fuenteVentas.getPorcentajeEgresosPuros();  
	               capitalizacion          = capitalizacion         + fuenteVentas.getCapitalizacion();          
	               porcentajeActividad     = porcentajeActividad    + fuenteVentas.getPorcentajeActividad();     
	               promedioVenta           = promedioVenta          + fuenteVentas.getPromedioVenta();           
	               venta                   = venta                  + fuenteVentas.getVenta();                   
	               ppu                     = ppu                    + fuenteVentas.getPpu();                     
	               pup                     = pup                    + fuenteVentas.getPup();                     
	               unidades                = unidades               + fuenteVentas.getUnidades();                
	               pedidos                 = pedidos                + fuenteVentas.getPedidos();                 
	               clientes                = clientes               + fuenteVentas.getClientes();                
	               activasFinales          = activasFinales         + fuenteVentas.getActivasFinales();                
	               
	               actividadLider 				= actividadLider 			   + fuenteVentas.getActividadLider();
	               numeroConsultoraCliPrivilege = numeroConsultoraCliPrivilege + fuenteVentas.getNumeroConsultoraCliPrivilege();
	               numeroClientesInsPrivilege   = numeroClientesInsPrivilege   + fuenteVentas.getNumeroClientesInsPrivilege();               
	               numeroClientesTrsPrivelege   = numeroClientesTrsPrivelege   + fuenteVentas.getNumeroClientesTrsPrivelege();
	               retencionSdoPeriodo          = retencionSdoPeriodo          + fuenteVentas.getRetencionSdoPeriodo();
	               retencionTerPeriodo          = retencionTerPeriodo          + fuenteVentas.getRetencionTerPeriodo();
	               retencionCuaPeriodo          = retencionCuaPeriodo          + fuenteVentas.getRetencionCuaPeriodo();
	               retencionActivas             = retencionActivas             + fuenteVentas.getRetencionActivas();
	               porcentajeRotacion           = porcentajeRotacion           + fuenteVentas.getPorcentajeRotacion();
	               posiblesEgresos              = posiblesEgresos              + fuenteVentas.getPosiblesEgresos ();
	               retencionPosEgresos          = retencionPosEgresos          + fuenteVentas.getRetencionPosEgresos();
	               
	               /*Fin efernandezo*/
	               }
	               if (lista.size()>4)
	               {
	               fuenteVentas = new ConsultaFuenteVentas();
	               fuenteVentas = (ConsultaFuenteVentas)lista.get(4);
	               this.c5  =String.valueOf(fuenteVentas.getPeriodo());
	               this.c5f1=String.valueOf((int)fuenteVentas.getActivasIniciales());
	               this.c5f2=String.valueOf((int)fuenteVentas.getIngresos() );
	               this.c5f3=String.valueOf((int)fuenteVentas.getReingresos() );
	               this.c5f4=String.valueOf((int) fuenteVentas.getEgresosNetos() );
	               this.c5f5=String.valueOf( fuenteVentas.getPorcentajeEgresosNetos() );
	               this.c5f6=String.valueOf( fuenteVentas.getEgresosPuros() );
	               this.c5f7=String.valueOf( fuenteVentas.getPorcentajeEgresosPuros() );
	               this.c5f8=String.valueOf( fuenteVentas.getCapitalizacion() );
	               this.c5f9=String.valueOf( fuenteVentas.getPorcentajeActividad() );
	               this.c5f10=String.valueOf( fuenteVentas.getPromedioVenta() );
	               this.c5f11=String.valueOf( fuenteVentas.getVenta() );
	               this.c5f12=String.valueOf( fuenteVentas.getPpu() );
	               this.c5f13=String.valueOf( fuenteVentas.getPup() );
	               this.c5f14=String.valueOf( (int)fuenteVentas.getUnidades() );
	               this.c5f15=String.valueOf( (int)fuenteVentas.getPedidos() );
	               this.c5f16=String.valueOf( (int)fuenteVentas.getClientes() );
	               this.c5f17=String.valueOf( (int)fuenteVentas.getActivasFinales() );
	               /*Ini efernandezo*/
	               this.c5f18 =String.valueOf(fuenteVentas.getActividadLider());
	               this.c5f19 =String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
	               this.c5f20 =String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
	               this.c5f21 =String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
	               this.c5f22 =String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
	               this.c5f23 =String.valueOf(fuenteVentas.getRetencionTerPeriodo());
	               this.c5f24 =String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
	               this.c5f25 =String.valueOf(fuenteVentas.getRetencionActivas());
	               this.c5f26 =String.valueOf(fuenteVentas.getPorcentajeRotacion());
	               this.c5f27 =String.valueOf(fuenteVentas.getPosiblesEgresos());
	               this.c5f28 =String.valueOf(fuenteVentas.getRetencionPosEgresos());

	               activasIniciales 	   = activasIniciales 		+ (int)fuenteVentas.getActivasIniciales();    
	               ingresos                = ingresos               + (int)fuenteVentas.getIngresos();            
	               reingresos              = reingresos             + ((((int)fuenteVentas.getReingresos()*100))/100);
	               egresosNetos            = egresosNetos           + (int)fuenteVentas.getEgresosNetos();       
	               porcentajeEgresosNetos  = porcentajeEgresosNetos + fuenteVentas.getPorcentajeEgresosNetos();  
	               egresosPuros            = egresosPuros           + fuenteVentas.getEgresosPuros();            
	               porcentajeEgresosPuros  = porcentajeEgresosPuros + fuenteVentas.getPorcentajeEgresosPuros();  
	               capitalizacion          = capitalizacion         + fuenteVentas.getCapitalizacion();          
	               porcentajeActividad     = porcentajeActividad    + fuenteVentas.getPorcentajeActividad();     
	               promedioVenta           = promedioVenta          + fuenteVentas.getPromedioVenta();           
	               venta                   = venta                  + fuenteVentas.getVenta();                   
	               ppu                     = ppu                    + fuenteVentas.getPpu();                     
	               pup                     = pup                    + fuenteVentas.getPup();                     
	               unidades                = unidades               + fuenteVentas.getUnidades();                
	               pedidos                 = pedidos                + fuenteVentas.getPedidos();                 
	               clientes                = clientes               + fuenteVentas.getClientes();                
	               activasFinales          = activasFinales         + fuenteVentas.getActivasFinales(); 
	               
	               actividadLider 				= actividadLider 			   + fuenteVentas.getActividadLider();
	               numeroConsultoraCliPrivilege = numeroConsultoraCliPrivilege + fuenteVentas.getNumeroConsultoraCliPrivilege();
	               numeroClientesInsPrivilege   = numeroClientesInsPrivilege   + fuenteVentas.getNumeroClientesInsPrivilege();               
	               numeroClientesTrsPrivelege   = numeroClientesTrsPrivelege   + fuenteVentas.getNumeroClientesTrsPrivelege();
	               retencionSdoPeriodo          = retencionSdoPeriodo          + fuenteVentas.getRetencionSdoPeriodo();
	               retencionTerPeriodo          = retencionTerPeriodo          + fuenteVentas.getRetencionTerPeriodo();
	               retencionCuaPeriodo          = retencionCuaPeriodo          + fuenteVentas.getRetencionCuaPeriodo();
	               retencionActivas             = retencionActivas             + fuenteVentas.getRetencionActivas();
	               porcentajeRotacion           = porcentajeRotacion           + fuenteVentas.getPorcentajeRotacion();
	               posiblesEgresos              = posiblesEgresos              + fuenteVentas.getPosiblesEgresos ();
	               retencionPosEgresos          = retencionPosEgresos          + fuenteVentas.getRetencionPosEgresos();
	               
	               /*Fin efernandezo*/
	               }
	               if (lista.size()>5)
	               {
	               fuenteVentas = new ConsultaFuenteVentas();
	               fuenteVentas = (ConsultaFuenteVentas)lista.get(5);
	               this.c6  =String.valueOf(fuenteVentas.getPeriodo());
	               this.c6f1=String.valueOf((int)fuenteVentas.getActivasIniciales());
	               this.c6f2=String.valueOf( (int)fuenteVentas.getIngresos() );
	               this.c6f3=String.valueOf( (int)fuenteVentas.getReingresos() );
	               this.c6f4=String.valueOf( (int)fuenteVentas.getEgresosNetos() );
	               this.c6f5=String.valueOf( fuenteVentas.getPorcentajeEgresosNetos() );
	               this.c6f6=String.valueOf( fuenteVentas.getEgresosPuros() );
	               this.c6f7=String.valueOf( fuenteVentas.getPorcentajeEgresosPuros() );
	               this.c6f8=String.valueOf( fuenteVentas.getCapitalizacion() );
	               this.c6f9=String.valueOf( fuenteVentas.getPorcentajeActividad() );
	               this.c6f10=String.valueOf( fuenteVentas.getPromedioVenta() );
	               this.c6f11=String.valueOf( fuenteVentas.getVenta() );
	               this.c6f12=String.valueOf( fuenteVentas.getPpu() );
	               this.c6f13=String.valueOf( fuenteVentas.getPup() );
	               this.c6f14=String.valueOf( (int)fuenteVentas.getUnidades() );
	               this.c6f15=String.valueOf( (int)fuenteVentas.getPedidos() );
	               this.c6f16=String.valueOf( (int)fuenteVentas.getClientes() );
	               this.c6f17=String.valueOf( (int)fuenteVentas.getActivasFinales() );
	               /*Ini efernandezo*/
	               this.c6f18 =String.valueOf(fuenteVentas.getActividadLider());
	               this.c6f19 =String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
	               this.c6f20 =String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
	               this.c6f21 =String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
	               this.c6f22 =String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
	               this.c6f23 =String.valueOf(fuenteVentas.getRetencionTerPeriodo());
	               this.c6f24 =String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
	               this.c6f25 =String.valueOf(fuenteVentas.getRetencionActivas());
	               this.c6f26 =String.valueOf(fuenteVentas.getPorcentajeRotacion());
	               this.c6f27 =String.valueOf(fuenteVentas.getPosiblesEgresos());
	               this.c6f28 =String.valueOf(fuenteVentas.getRetencionPosEgresos());

	               activasIniciales 	   = activasIniciales 		+ (int)fuenteVentas.getActivasIniciales();    
	               ingresos                = ingresos               + (int)fuenteVentas.getIngresos();            
	               reingresos              = reingresos             + ((((int)fuenteVentas.getReingresos()*100))/100);
	               egresosNetos            = egresosNetos           + (int)fuenteVentas.getEgresosNetos();       
	               porcentajeEgresosNetos  = porcentajeEgresosNetos + fuenteVentas.getPorcentajeEgresosNetos();  
	               egresosPuros            = egresosPuros           + fuenteVentas.getEgresosPuros();            
	               porcentajeEgresosPuros  = porcentajeEgresosPuros + fuenteVentas.getPorcentajeEgresosPuros();  
	               capitalizacion          = capitalizacion         + fuenteVentas.getCapitalizacion();          
	               porcentajeActividad     = porcentajeActividad    + fuenteVentas.getPorcentajeActividad();     
	               promedioVenta           = promedioVenta          + fuenteVentas.getPromedioVenta();           
	               venta                   = venta                  + fuenteVentas.getVenta();                   
	               ppu                     = ppu                    + fuenteVentas.getPpu();                     
	               pup                     = pup                    + fuenteVentas.getPup();                     
	               unidades                = unidades               + fuenteVentas.getUnidades();                
	               pedidos                 = pedidos                + fuenteVentas.getPedidos();                 
	               clientes                = clientes               + fuenteVentas.getClientes();                
	               activasFinales          = activasFinales         + fuenteVentas.getActivasFinales(); 
	               
	               actividadLider 				= actividadLider 			   + fuenteVentas.getActividadLider();
	               numeroConsultoraCliPrivilege = numeroConsultoraCliPrivilege + fuenteVentas.getNumeroConsultoraCliPrivilege();
	               numeroClientesInsPrivilege   = numeroClientesInsPrivilege   + fuenteVentas.getNumeroClientesInsPrivilege();               
	               numeroClientesTrsPrivelege   = numeroClientesTrsPrivelege   + fuenteVentas.getNumeroClientesTrsPrivelege();
	               retencionSdoPeriodo          = retencionSdoPeriodo          + fuenteVentas.getRetencionSdoPeriodo();
	               retencionTerPeriodo          = retencionTerPeriodo          + fuenteVentas.getRetencionTerPeriodo();
	               retencionCuaPeriodo          = retencionCuaPeriodo          + fuenteVentas.getRetencionCuaPeriodo();
	               retencionActivas             = retencionActivas             + fuenteVentas.getRetencionActivas();
	               porcentajeRotacion           = porcentajeRotacion           + fuenteVentas.getPorcentajeRotacion();
	               posiblesEgresos              = posiblesEgresos              + fuenteVentas.getPosiblesEgresos ();
	               retencionPosEgresos          = retencionPosEgresos          + fuenteVentas.getRetencionPosEgresos();
	               
	               /*Fin efernandezo*/
	               }
	               if (lista.size()>6)
	               { 
	               fuenteVentas = new ConsultaFuenteVentas();
	               fuenteVentas = (ConsultaFuenteVentas)lista.get(6);
	               //comment x efernandezo
	               this.c7 = "Total";
	               this.c7f1=String.valueOf((int)fuenteVentas.getActivasIniciales());
	               this.c7f2=String.valueOf((int)fuenteVentas.getIngresos() );
	               this.c7f3=String.valueOf((int) fuenteVentas.getReingresos() );
	               this.c7f4=String.valueOf((int) fuenteVentas.getEgresosNetos() );
	               this.c7f5=String.valueOf( fuenteVentas.getPorcentajeEgresosNetos() );
	               this.c7f6=String.valueOf( fuenteVentas.getEgresosPuros() );
	               this.c7f7=String.valueOf( fuenteVentas.getPorcentajeEgresosPuros() );
	               this.c7f8=String.valueOf( fuenteVentas.getCapitalizacion() );
	               this.c7f9=String.valueOf( fuenteVentas.getPorcentajeActividad() );
	               this.c7f10=String.valueOf( fuenteVentas.getPromedioVenta() );
	               this.c7f11=String.valueOf( fuenteVentas.getVenta() );
	               this.c7f12=String.valueOf( fuenteVentas.getPpu() );
	               this.c7f13=String.valueOf( fuenteVentas.getPup() );
	               this.c7f14=String.valueOf((int) fuenteVentas.getUnidades() );
	               this.c7f15=String.valueOf((int) fuenteVentas.getPedidos() );
	               this.c7f16=String.valueOf((int) fuenteVentas.getClientes() );
	               this.c7f17=String.valueOf( (int)fuenteVentas.getActivasFinales() );
	               
	               /*Ini efernandezo*/
	               /*
	               this.c7f1 = String.valueOf(activasIniciales);
	               this.c7f2 = String.valueOf(ingresos);
	               this.c7f3 = String.valueOf(reingresos);
	               this.c7f4 = String.valueOf(egresosNetos);
	               this.c7f5 = String.valueOf(porcentajeEgresosNetos);
	               this.c7f6 = String.valueOf(egresosPuros);
	               this.c7f7 = String.valueOf(porcentajeEgresosPuros);
	               this.c7f8 = String.valueOf(capitalizacion);
	               this.c7f9 = String.valueOf(porcentajeActividad);
	               this.c7f10= String.valueOf(promedioVenta);
	               this.c7f11= String.valueOf(venta);
	               this.c7f12= String.valueOf(ppu);
	               this.c7f13= String.valueOf(pup);
	               this.c7f14= String.valueOf(unidades);
	               this.c7f15= String.valueOf(pedidos);
	               this.c7f16= String.valueOf(clientes);
	               this.c7f17= String.valueOf(activasFinales);
	               */
	               
	             	   NumberFormat l_nf = NumberFormat.getInstance(); 
	             	   l_nf.setMaximumFractionDigits(2);
	             	   l_nf.setMinimumFractionDigits(2);

	               this.c7f18 =String.valueOf(l_nf.format(actividadLider));               
	               this.c7f19 =String.valueOf(numeroConsultoraCliPrivilege); 
	               this.c7f20 =String.valueOf(numeroClientesInsPrivilege);        
	               this.c7f21 =String.valueOf(numeroClientesTrsPrivelege); 
	               this.c7f22 =String.valueOf(retencionSdoPeriodo); 
	               this.c7f23 =String.valueOf(retencionTerPeriodo); 
	               this.c7f24 =String.valueOf(retencionCuaPeriodo); 
	               this.c7f25 =String.valueOf(retencionActivas); 
	               this.c7f26 =String.valueOf(l_nf.format(porcentajeRotacion)); 
	               this.c7f27 =String.valueOf(posiblesEgresos); 
	               this.c7f28 =String.valueOf(retencionPosEgresos);                
	               /*
	               this.c7f18 =String.valueOf(fuenteVentas.getActividadLider());
	               this.c7f19 =String.valueOf(fuenteVentas.getNumeroConsultoraCliPrivilege());
	               this.c7f20 =String.valueOf(fuenteVentas.getNumeroClientesInsPrivilege());
	               this.c7f21 =String.valueOf(fuenteVentas.getNumeroClientesTrsPrivelege());
	               this.c7f22 =String.valueOf(fuenteVentas.getRetencionSdoPeriodo());
	               this.c7f23 =String.valueOf(fuenteVentas.getRetencionTerPeriodo());
	               this.c7f24 =String.valueOf(fuenteVentas.getRetencionCuaPeriodo());
	               this.c7f25 =String.valueOf(fuenteVentas.getRetencionActivas());
	               this.c7f26 =String.valueOf(fuenteVentas.getPorcentajeRotacion());
	               this.c7f27 =String.valueOf(fuenteVentas.getPosiblesEgresos());
	               this.c7f28 =String.valueOf(fuenteVentas.getRetencionPosEgresos());
	               */
	               /*Fin efernandezo*/
	               
	               
	               
	               
	               }
	       }
	   }

	    public String getC0() {
			return c0;
		}
	    
	    public void setC0(String c0) {
			this.c0 = c0;
		}

	    public String getC1() {
	        return c1;
	    }



	    public void setC1(String c1) {
	        this.c1 = c1;
	    }



	    public String getC1f1() {
	        return c1f1;
	    }



	    public void setC1f1(String c1f1) {
	        this.c1f1 = c1f1;
	    }



	    public String getC1f10() {
	        return c1f10;
	    }



	    public void setC1f10(String c1f10) {
	        this.c1f10 = c1f10;
	    }



	    public String getC1f11() {
	        return c1f11;
	    }



	    public void setC1f11(String c1f11) {
	        this.c1f11 = c1f11;
	    }



	    public String getC1f12() {
	        return c1f12;
	    }



	    public void setC1f12(String c1f12) {
	        this.c1f12 = c1f12;
	    }



	    public String getC1f13() {
	        return c1f13;
	    }



	    public void setC1f13(String c1f13) {
	        this.c1f13 = c1f13;
	    }



	    public String getC1f14() {
	        return c1f14;
	    }



	    public void setC1f14(String c1f14) {
	        this.c1f14 = c1f14;
	    }



	    public String getC1f15() {
	        return c1f15;
	    }



	    public void setC1f15(String c1f15) {
	        this.c1f15 = c1f15;
	    }



	    public String getC1f16() {
	        return c1f16;
	    }



	    public void setC1f16(String c1f16) {
	        this.c1f16 = c1f16;
	    }



	    public String getC1f17() {
	        return c1f17;
	    }



	    public void setC1f17(String c1f17) {
	        this.c1f17 = c1f17;
	    }



	    public String getC1f2() {
	        return c1f2;
	    }



	    public void setC1f2(String c1f2) {
	        this.c1f2 = c1f2;
	    }



	    public String getC1f3() {
	        return c1f3;
	    }



	    public void setC1f3(String c1f3) {
	        this.c1f3 = c1f3;
	    }



	    public String getC1f4() {
	        return c1f4;
	    }



	    public void setC1f4(String c1f4) {
	        this.c1f4 = c1f4;
	    }



	    public String getC1f5() {
	        return c1f5;
	    }



	    public void setC1f5(String c1f5) {
	        this.c1f5 = c1f5;
	    }



	    public String getC1f6() {
	        return c1f6;
	    }



	    public void setC1f6(String c1f6) {
	        this.c1f6 = c1f6;
	    }



	    public String getC1f7() {
	        return c1f7;
	    }



	    public void setC1f7(String c1f7) {
	        this.c1f7 = c1f7;
	    }



	    public String getC1f8() {
	        return c1f8;
	    }



	    public void setC1f8(String c1f8) {
	        this.c1f8 = c1f8;
	    }



	    public String getC1f9() {
	        return c1f9;
	    }



	    public void setC1f9(String c1f9) {
	        this.c1f9 = c1f9;
	    }



	    public String getC2() {
	        return c2;
	    }



	    public void setC2(String c2) {
	        this.c2 = c2;
	    }



	    public String getC2f1() {
	        return c2f1;
	    }



	    public void setC2f1(String c2f1) {
	        this.c2f1 = c2f1;
	    }



	    public String getC2f10() {
	        return c2f10;
	    }



	    public void setC2f10(String c2f10) {
	        this.c2f10 = c2f10;
	    }



	    public String getC2f11() {
	        return c2f11;
	    }



	    public void setC2f11(String c2f11) {
	        this.c2f11 = c2f11;
	    }



	    public String getC2f12() {
	        return c2f12;
	    }



	    public void setC2f12(String c2f12) {
	        this.c2f12 = c2f12;
	    }



	    public String getC2f13() {
	        return c2f13;
	    }



	    public void setC2f13(String c2f13) {
	        this.c2f13 = c2f13;
	    }



	    public String getC2f14() {
	        return c2f14;
	    }



	    public void setC2f14(String c2f14) {
	        this.c2f14 = c2f14;
	    }



	    public String getC2f15() {
	        return c2f15;
	    }



	    public void setC2f15(String c2f15) {
	        this.c2f15 = c2f15;
	    }



	    public String getC2f16() {
	        return c2f16;
	    }



	    public void setC2f16(String c2f16) {
	        this.c2f16 = c2f16;
	    }



	    public String getC2f17() {
	        return c2f17;
	    }



	    public void setC2f17(String c2f17) {
	        this.c2f17 = c2f17;
	    }



	    public String getC2f2() {
	        return c2f2;
	    }



	    public void setC2f2(String c2f2) {
	        this.c2f2 = c2f2;
	    }



	    public String getC2f3() {
	        return c2f3;
	    }



	    public void setC2f3(String c2f3) {
	        this.c2f3 = c2f3;
	    }



	    public String getC2f4() {
	        return c2f4;
	    }



	    public void setC2f4(String c2f4) {
	        this.c2f4 = c2f4;
	    }



	    public String getC2f5() {
	        return c2f5;
	    }



	    public void setC2f5(String c2f5) {
	        this.c2f5 = c2f5;
	    }



	    public String getC2f6() {
	        return c2f6;
	    }



	    public void setC2f6(String c2f6) {
	        this.c2f6 = c2f6;
	    }



	    public String getC2f7() {
	        return c2f7;
	    }



	    public void setC2f7(String c2f7) {
	        this.c2f7 = c2f7;
	    }



	    public String getC2f8() {
	        return c2f8;
	    }



	    public void setC2f8(String c2f8) {
	        this.c2f8 = c2f8;
	    }



	    public String getC2f9() {
	        return c2f9;
	    }



	    public void setC2f9(String c2f9) {
	        this.c2f9 = c2f9;
	    }



	    public String getC3() {
	        return c3;
	    }



	    public void setC3(String c3) {
	        this.c3 = c3;
	    }



	    public String getC3f1() {
	        return c3f1;
	    }



	    public void setC3f1(String c3f1) {
	        this.c3f1 = c3f1;
	    }



	    public String getC3f10() {
	        return c3f10;
	    }



	    public void setC3f10(String c3f10) {
	        this.c3f10 = c3f10;
	    }



	    public String getC3f11() {
	        return c3f11;
	    }



	    public void setC3f11(String c3f11) {
	        this.c3f11 = c3f11;
	    }



	    public String getC3f12() {
	        return c3f12;
	    }



	    public void setC3f12(String c3f12) {
	        this.c3f12 = c3f12;
	    }



	    public String getC3f13() {
	        return c3f13;
	    }



	    public void setC3f13(String c3f13) {
	        this.c3f13 = c3f13;
	    }



	    public String getC3f14() {
	        return c3f14;
	    }



	    public void setC3f14(String c3f14) {
	        this.c3f14 = c3f14;
	    }



	    public String getC3f15() {
	        return c3f15;
	    }



	    public void setC3f15(String c3f15) {
	        this.c3f15 = c3f15;
	    }



	    public String getC3f16() {
	        return c3f16;
	    }



	    public void setC3f16(String c3f16) {
	        this.c3f16 = c3f16;
	    }



	    public String getC3f17() {
	        return c3f17;
	    }



	    public void setC3f17(String c3f17) {
	        this.c3f17 = c3f17;
	    }



	    public String getC3f2() {
	        return c3f2;
	    }



	    public void setC3f2(String c3f2) {
	        this.c3f2 = c3f2;
	    }



	    public String getC3f3() {
	        return c3f3;
	    }



	    public void setC3f3(String c3f3) {
	        this.c3f3 = c3f3;
	    }



	    public String getC3f4() {
	        return c3f4;
	    }



	    public void setC3f4(String c3f4) {
	        this.c3f4 = c3f4;
	    }



	    public String getC3f5() {
	        return c3f5;
	    }



	    public void setC3f5(String c3f5) {
	        this.c3f5 = c3f5;
	    }



	    public String getC3f6() {
	        return c3f6;
	    }



	    public void setC3f6(String c3f6) {
	        this.c3f6 = c3f6;
	    }



	    public String getC3f7() {
	        return c3f7;
	    }



	    public void setC3f7(String c3f7) {
	        this.c3f7 = c3f7;
	    }



	    public String getC3f8() {
	        return c3f8;
	    }



	    public void setC3f8(String c3f8) {
	        this.c3f8 = c3f8;
	    }



	    public String getC3f9() {
	        return c3f9;
	    }



	    public void setC3f9(String c3f9) {
	        this.c3f9 = c3f9;
	    }



	    public String getC4() {
	        return c4;
	    }



	    public void setC4(String c4) {
	        this.c4 = c4;
	    }



	    public String getC4f1() {
	        return c4f1;
	    }



	    public void setC4f1(String c4f1) {
	        this.c4f1 = c4f1;
	    }



	    public String getC4f10() {
	        return c4f10;
	    }



	    public void setC4f10(String c4f10) {
	        this.c4f10 = c4f10;
	    }



	    public String getC4f11() {
	        return c4f11;
	    }



	    public void setC4f11(String c4f11) {
	        this.c4f11 = c4f11;
	    }



	    public String getC4f12() {
	        return c4f12;
	    }



	    public void setC4f12(String c4f12) {
	        this.c4f12 = c4f12;
	    }



	    public String getC4f13() {
	        return c4f13;
	    }



	    public void setC4f13(String c4f13) {
	        this.c4f13 = c4f13;
	    }



	    public String getC4f14() {
	        return c4f14;
	    }



	    public void setC4f14(String c4f14) {
	        this.c4f14 = c4f14;
	    }



	    public String getC4f15() {
	        return c4f15;
	    }



	    public void setC4f15(String c4f15) {
	        this.c4f15 = c4f15;
	    }



	    public String getC4f16() {
	        return c4f16;
	    }



	    public void setC4f16(String c4f16) {
	        this.c4f16 = c4f16;
	    }



	    public String getC4f17() {
	        return c4f17;
	    }



	    public void setC4f17(String c4f17) {
	        this.c4f17 = c4f17;
	    }



	    public String getC4f2() {
	        return c4f2;
	    }



	    public void setC4f2(String c4f2) {
	        this.c4f2 = c4f2;
	    }



	    public String getC4f3() {
	        return c4f3;
	    }



	    public void setC4f3(String c4f3) {
	        this.c4f3 = c4f3;
	    }



	    public String getC4f4() {
	        return c4f4;
	    }



	    public void setC4f4(String c4f4) {
	        this.c4f4 = c4f4;
	    }



	    public String getC4f5() {
	        return c4f5;
	    }



	    public void setC4f5(String c4f5) {
	        this.c4f5 = c4f5;
	    }



	    public String getC4f6() {
	        return c4f6;
	    }



	    public void setC4f6(String c4f6) {
	        this.c4f6 = c4f6;
	    }



	    public String getC4f7() {
	        return c4f7;
	    }



	    public void setC4f7(String c4f7) {
	        this.c4f7 = c4f7;
	    }



	    public String getC4f8() {
	        return c4f8;
	    }



	    public void setC4f8(String c4f8) {
	        this.c4f8 = c4f8;
	    }



	    public String getC4f9() {
	        return c4f9;
	    }



	    public void setC4f9(String c4f9) {
	        this.c4f9 = c4f9;
	    }



	    public String getC5() {
	        return c5;
	    }



	    public void setC5(String c5) {
	        this.c5 = c5;
	    }



	    public String getC5f1() {
	        return c5f1;
	    }



	    public void setC5f1(String c5f1) {
	        this.c5f1 = c5f1;
	    }



	    public String getC5f10() {
	        return c5f10;
	    }



	    public void setC5f10(String c5f10) {
	        this.c5f10 = c5f10;
	    }



	    public String getC5f11() {
	        return c5f11;
	    }



	    public void setC5f11(String c5f11) {
	        this.c5f11 = c5f11;
	    }



	    public String getC5f12() {
	        return c5f12;
	    }



	    public void setC5f12(String c5f12) {
	        this.c5f12 = c5f12;
	    }



	    public String getC5f13() {
	        return c5f13;
	    }



	    public void setC5f13(String c5f13) {
	        this.c5f13 = c5f13;
	    }



	    public String getC5f14() {
	        return c5f14;
	    }



	    public void setC5f14(String c5f14) {
	        this.c5f14 = c5f14;
	    }



	    public String getC5f15() {
	        return c5f15;
	    }



	    public void setC5f15(String c5f15) {
	        this.c5f15 = c5f15;
	    }



	    public String getC5f16() {
	        return c5f16;
	    }



	    public void setC5f16(String c5f16) {
	        this.c5f16 = c5f16;
	    }



	    public String getC5f17() {
	        return c5f17;
	    }



	    public void setC5f17(String c5f17) {
	        this.c5f17 = c5f17;
	    }



	    public String getC5f2() {
	        return c5f2;
	    }



	    public void setC5f2(String c5f2) {
	        this.c5f2 = c5f2;
	    }



	    public String getC5f3() {
	        return c5f3;
	    }



	    public void setC5f3(String c5f3) {
	        this.c5f3 = c5f3;
	    }



	    public String getC5f4() {
	        return c5f4;
	    }



	    public void setC5f4(String c5f4) {
	        this.c5f4 = c5f4;
	    }



	    public String getC5f5() {
	        return c5f5;
	    }



	    public void setC5f5(String c5f5) {
	        this.c5f5 = c5f5;
	    }



	    public String getC5f6() {
	        return c5f6;
	    }



	    public void setC5f6(String c5f6) {
	        this.c5f6 = c5f6;
	    }



	    public String getC5f7() {
	        return c5f7;
	    }



	    public void setC5f7(String c5f7) {
	        this.c5f7 = c5f7;
	    }



	    public String getC5f8() {
	        return c5f8;
	    }



	    public void setC5f8(String c5f8) {
	        this.c5f8 = c5f8;
	    }



	    public String getC5f9() {
	        return c5f9;
	    }



	    public void setC5f9(String c5f9) {
	        this.c5f9 = c5f9;
	    }



	    public String getC6() {
	        return c6;
	    }



	    public void setC6(String c6) {
	        this.c6 = c6;
	    }



	    public String getC6f1() {
	        return c6f1;
	    }



	    public void setC6f1(String c6f1) {
	        this.c6f1 = c6f1;
	    }



	    public String getC6f10() {
	        return c6f10;
	    }



	    public void setC6f10(String c6f10) {
	        this.c6f10 = c6f10;
	    }



	    public String getC6f11() {
	        return c6f11;
	    }



	    public void setC6f11(String c6f11) {
	        this.c6f11 = c6f11;
	    }



	    public String getC6f12() {
	        return c6f12;
	    }



	    public void setC6f12(String c6f12) {
	        this.c6f12 = c6f12;
	    }



	    public String getC6f13() {
	        return c6f13;
	    }



	    public void setC6f13(String c6f13) {
	        this.c6f13 = c6f13;
	    }



	    public String getC6f14() {
	        return c6f14;
	    }



	    public void setC6f14(String c6f14) {
	        this.c6f14 = c6f14;
	    }



	    public String getC6f15() {
	        return c6f15;
	    }



	    public void setC6f15(String c6f15) {
	        this.c6f15 = c6f15;
	    }



	    public String getC6f16() {
	        return c6f16;
	    }



	    public void setC6f16(String c6f16) {
	        this.c6f16 = c6f16;
	    }



	    public String getC6f17() {
	        return c6f17;
	    }



	    public void setC6f17(String c6f17) {
	        this.c6f17 = c6f17;
	    }



	    public String getC6f2() {
	        return c6f2;
	    }



	    public void setC6f2(String c6f2) {
	        this.c6f2 = c6f2;
	    }



	    public String getC6f3() {
	        return c6f3;
	    }



	    public void setC6f3(String c6f3) {
	        this.c6f3 = c6f3;
	    }



	    public String getC6f4() {
	        return c6f4;
	    }



	    public void setC6f4(String c6f4) {
	        this.c6f4 = c6f4;
	    }



	    public String getC6f5() {
	        return c6f5;
	    }



	    public void setC6f5(String c6f5) {
	        this.c6f5 = c6f5;
	    }



	    public String getC6f6() {
	        return c6f6;
	    }



	    public void setC6f6(String c6f6) {
	        this.c6f6 = c6f6;
	    }



	    public String getC6f7() {
	        return c6f7;
	    }



	    public void setC6f7(String c6f7) {
	        this.c6f7 = c6f7;
	    }



	    public String getC6f8() {
	        return c6f8;
	    }



	    public void setC6f8(String c6f8) {
	        this.c6f8 = c6f8;
	    }



	    public String getC6f9() {
	        return c6f9;
	    }



	    public void setC6f9(String c6f9) {
	        this.c6f9 = c6f9;
	    }



        public String getC7() {
			return c7;
		}
        
        public void setC7(String c7) {
			this.c7 = c7;
		}



	    public String getC7f1() {
	        return c7f1;
	    }



	    public void setC7f1(String c7f1) {
	        this.c7f1 = c7f1;
	    }



	    public String getC7f10() {
	        return c7f10;
	    }



	    public void setC7f10(String c7f10) {
	        this.c7f10 = c7f10;
	    }



	    public String getC7f11() {
	        return c7f11;
	    }



	    public void setC7f11(String c7f11) {
	        this.c7f11 = c7f11;
	    }



	    public String getC7f12() {
	        return c7f12;
	    }



	    public void setC7f12(String c7f12) {
	        this.c7f12 = c7f12;
	    }



	    public String getC7f13() {
	        return c7f13;
	    }



	    public void setC7f13(String c7f13) {
	        this.c7f13 = c7f13;
	    }



	    public String getC7f14() {
	        return c7f14;
	    }



	    public void setC7f14(String c7f14) {
	        this.c7f14 = c7f14;
	    }



	    public String getC7f15() {
	        return c7f15;
	    }



	    public void setC7f15(String c7f15) {
	        this.c7f15 = c7f15;
	    }



	    public String getC7f16() {
	        return c7f16;
	    }



	    public void setC7f16(String c7f16) {
	        this.c7f16 = c7f16;
	    }



	    public String getC7f17() {
	        return c7f17;
	    }



	    public void setC7f17(String c7f17) {
	        this.c7f17 = c7f17;
	    }



	    public String getC7f2() {
	        return c7f2;
	    }



	    public void setC7f2(String c7f2) {
	        this.c7f2 = c7f2;
	    }



	    public String getC7f3() {
	        return c7f3;
	    }



	    public void setC7f3(String c7f3) {
	        this.c7f3 = c7f3;
	    }



	    public String getC7f4() {
	        return c7f4;
	    }



	    public void setC7f4(String c7f4) {
	        this.c7f4 = c7f4;
	    }



	    public String getC7f5() {
	        return c7f5;
	    }



	    public void setC7f5(String c7f5) {
	        this.c7f5 = c7f5;
	    }



	    public String getC7f6() {
	        return c7f6;
	    }



	    public void setC7f6(String c7f6) {
	        this.c7f6 = c7f6;
	    }



	    public String getC7f7() {
	        return c7f7;
	    }



	    public void setC7f7(String c7f7) {
	        this.c7f7 = c7f7;
	    }



	    public String getC7f8() {
	        return c7f8;
	    }



	    public void setC7f8(String c7f8) {
	        this.c7f8 = c7f8;
	    }



	    public String getC7f9() {
	        return c7f9;
	    }



	    public void setC7f9(String c7f9) {
	        this.c7f9 = c7f9;
	    }



	   



		/**
		 * @return Returns the codigoMarca.
		 */
		public String getCodigoMarca() {
			return codigoMarca;
		}



		/**
		 * @param codigoMarca The codigoMarca to set.
		 */
		public void setCodigoMarca(String codigoMarca) {
			this.codigoMarca = codigoMarca;
		}



		public String getC1f18() {
			return c1f18;
		}



		public void setC1f18(String c1f18) {
			this.c1f18 = c1f18;
		}



		public String getC1f19() {
			return c1f19;
		}



		public void setC1f19(String c1f19) {
			this.c1f19 = c1f19;
		}



		public String getC1f20() {
			return c1f20;
		}



		public void setC1f20(String c1f20) {
			this.c1f20 = c1f20;
		}



		public String getC1f21() {
			return c1f21;
		}



		public void setC1f21(String c1f21) {
			this.c1f21 = c1f21;
		}



		public String getC1f22() {
			return c1f22;
		}



		public void setC1f22(String c1f22) {
			this.c1f22 = c1f22;
		}



		public String getC1f23() {
			return c1f23;
		}



		public void setC1f23(String c1f23) {
			this.c1f23 = c1f23;
		}



		public String getC1f24() {
			return c1f24;
		}



		public void setC1f24(String c1f24) {
			this.c1f24 = c1f24;
		}



		public String getC1f25() {
			return c1f25;
		}



		public void setC1f25(String c1f25) {
			this.c1f25 = c1f25;
		}



		public String getC1f26() {
			return c1f26;
		}



		public void setC1f26(String c1f26) {
			this.c1f26 = c1f26;
		}



		public String getC1f27() {
			return c1f27;
		}



		public void setC1f27(String c1f27) {
			this.c1f27 = c1f27;
		}



		public String getC1f28() {
			return c1f28;
		}



		public void setC1f28(String c1f28) {
			this.c1f28 = c1f28;
		}



		public String getC2f18() {
			return c2f18;
		}



		public void setC2f18(String c2f18) {
			this.c2f18 = c2f18;
		}



		public String getC2f19() {
			return c2f19;
		}



		public void setC2f19(String c2f19) {
			this.c2f19 = c2f19;
		}



		public String getC2f20() {
			return c2f20;
		}



		public void setC2f20(String c2f20) {
			this.c2f20 = c2f20;
		}



		public String getC2f21() {
			return c2f21;
		}



		public void setC2f21(String c2f21) {
			this.c2f21 = c2f21;
		}



		public String getC2f22() {
			return c2f22;
		}



		public void setC2f22(String c2f22) {
			this.c2f22 = c2f22;
		}



		public String getC2f23() {
			return c2f23;
		}



		public void setC2f23(String c2f23) {
			this.c2f23 = c2f23;
		}



		public String getC2f24() {
			return c2f24;
		}



		public void setC2f24(String c2f24) {
			this.c2f24 = c2f24;
		}



		public String getC2f25() {
			return c2f25;
		}



		public void setC2f25(String c2f25) {
			this.c2f25 = c2f25;
		}



		public String getC2f26() {
			return c2f26;
		}



		public void setC2f26(String c2f26) {
			this.c2f26 = c2f26;
		}



		public String getC2f27() {
			return c2f27;
		}



		public void setC2f27(String c2f27) {
			this.c2f27 = c2f27;
		}



		public String getC2f28() {
			return c2f28;
		}



		public void setC2f28(String c2f28) {
			this.c2f28 = c2f28;
		}



		public String getC3f18() {
			return c3f18;
		}



		public void setC3f18(String c3f18) {
			this.c3f18 = c3f18;
		}



		public String getC3f19() {
			return c3f19;
		}



		public void setC3f19(String c3f19) {
			this.c3f19 = c3f19;
		}



		public String getC3f20() {
			return c3f20;
		}



		public void setC3f20(String c3f20) {
			this.c3f20 = c3f20;
		}



		public String getC3f21() {
			return c3f21;
		}



		public void setC3f21(String c3f21) {
			this.c3f21 = c3f21;
		}



		public String getC3f22() {
			return c3f22;
		}



		public void setC3f22(String c3f22) {
			this.c3f22 = c3f22;
		}



		public String getC3f23() {
			return c3f23;
		}



		public void setC3f23(String c3f23) {
			this.c3f23 = c3f23;
		}



		public String getC3f24() {
			return c3f24;
		}



		public void setC3f24(String c3f24) {
			this.c3f24 = c3f24;
		}



		public String getC3f25() {
			return c3f25;
		}



		public void setC3f25(String c3f25) {
			this.c3f25 = c3f25;
		}



		public String getC3f26() {
			return c3f26;
		}



		public void setC3f26(String c3f26) {
			this.c3f26 = c3f26;
		}



		public String getC3f27() {
			return c3f27;
		}



		public void setC3f27(String c3f27) {
			this.c3f27 = c3f27;
		}



		public String getC3f28() {
			return c3f28;
		}



		public void setC3f28(String c3f28) {
			this.c3f28 = c3f28;
		}



		public String getC4f18() {
			return c4f18;
		}



		public void setC4f18(String c4f18) {
			this.c4f18 = c4f18;
		}



		public String getC4f19() {
			return c4f19;
		}



		public void setC4f19(String c4f19) {
			this.c4f19 = c4f19;
		}



		public String getC4f20() {
			return c4f20;
		}



		public void setC4f20(String c4f20) {
			this.c4f20 = c4f20;
		}



		public String getC4f21() {
			return c4f21;
		}



		public void setC4f21(String c4f21) {
			this.c4f21 = c4f21;
		}



		public String getC4f22() {
			return c4f22;
		}



		public void setC4f22(String c4f22) {
			this.c4f22 = c4f22;
		}



		public String getC4f23() {
			return c4f23;
		}



		public void setC4f23(String c4f23) {
			this.c4f23 = c4f23;
		}



		public String getC4f24() {
			return c4f24;
		}



		public void setC4f24(String c4f24) {
			this.c4f24 = c4f24;
		}



		public String getC4f25() {
			return c4f25;
		}



		public void setC4f25(String c4f25) {
			this.c4f25 = c4f25;
		}



		public String getC4f26() {
			return c4f26;
		}



		public void setC4f26(String c4f26) {
			this.c4f26 = c4f26;
		}



		public String getC4f27() {
			return c4f27;
		}



		public void setC4f27(String c4f27) {
			this.c4f27 = c4f27;
		}



		public String getC4f28() {
			return c4f28;
		}



		public void setC4f28(String c4f28) {
			this.c4f28 = c4f28;
		}



		public String getC5f18() {
			return c5f18;
		}



		public void setC5f18(String c5f18) {
			this.c5f18 = c5f18;
	    }



		public String getC5f19() {
			return c5f19;
	    }



		public void setC5f19(String c5f19) {
			this.c5f19 = c5f19;
	    }



		public String getC5f20() {
			return c5f20;
	    }



		public void setC5f20(String c5f20) {
			this.c5f20 = c5f20;
	    }



		public String getC5f21() {
			return c5f21;
	    }



		public void setC5f21(String c5f21) {
			this.c5f21 = c5f21;
	    }



		public String getC5f22() {
			return c5f22;
	    }



		public void setC5f22(String c5f22) {
			this.c5f22 = c5f22;
	    }



		public String getC5f23() {
			return c5f23;
	    }



		public void setC5f23(String c5f23) {
			this.c5f23 = c5f23;
	    }



		public String getC5f24() {
			return c5f24;
	    }



		public void setC5f24(String c5f24) {
			this.c5f24 = c5f24;
	    }



		public String getC5f25() {
			return c5f25;
	    }



		public void setC5f25(String c5f25) {
			this.c5f25 = c5f25;
	    }



		public String getC5f26() {
			return c5f26;
	    }



		public void setC5f26(String c5f26) {
			this.c5f26 = c5f26;
	    }



		public String getC5f27() {
			return c5f27;
	    }



		public void setC5f27(String c5f27) {
			this.c5f27 = c5f27;
	    }



		public String getC5f28() {
			return c5f28;
	    }



		public void setC5f28(String c5f28) {
			this.c5f28 = c5f28;
	    }



		public String getC6f18() {
			return c6f18;
	    }



		public void setC6f18(String c6f18) {
			this.c6f18 = c6f18;
	    }



		public String getC6f19() {
			return c6f19;
	    }



		public void setC6f19(String c6f19) {
			this.c6f19 = c6f19;
	    }



		public String getC6f20() {
			return c6f20;
	    }



		public void setC6f20(String c6f20) {
			this.c6f20 = c6f20;
	    }



		public String getC6f21() {
			return c6f21;
	    }



		public void setC6f21(String c6f21) {
			this.c6f21 = c6f21;
	    }



		public String getC6f22() {
			return c6f22;
	    }



		public void setC6f22(String c6f22) {
			this.c6f22 = c6f22;
	    }



		public String getC6f23() {
			return c6f23;
	    }



		public void setC6f23(String c6f23) {
			this.c6f23 = c6f23;
	    }



		public String getC6f24() {
			return c6f24;
	    }



		public void setC6f24(String c6f24) {
			this.c6f24 = c6f24;
		}



		public String getC6f25() {
			return c6f25;
		}



		public void setC6f25(String c6f25) {
			this.c6f25 = c6f25;
		}



		public String getC6f26() {
			return c6f26;
		}



		public void setC6f26(String c6f26) {
			this.c6f26 = c6f26;
		}



		public String getC6f27() {
			return c6f27;
		}



		public void setC6f27(String c6f27) {
			this.c6f27 = c6f27;
		}



		public String getC6f28() {
			return c6f28;
		}



		public void setC6f28(String c6f28) {
			this.c6f28 = c6f28;
		}



		public String getC7f18() {
			return c7f18;
		}



		public void setC7f18(String c7f18) {
			this.c7f18 = c7f18;
		}



		public String getC7f19() {
			return c7f19;
		}



		public void setC7f19(String c7f19) {
			this.c7f19 = c7f19;
		}



		public String getC7f20() {
			return c7f20;
		}



		public void setC7f20(String c7f20) {
			this.c7f20 = c7f20;
		}



		public String getC7f21() {
			return c7f21;
		}



		public void setC7f21(String c7f21) {
			this.c7f21 = c7f21;
		}



		public String getC7f22() {
			return c7f22;
		}



		public void setC7f22(String c7f22) {
			this.c7f22 = c7f22;
		}



		public String getC7f23() {
			return c7f23;
		}



		public void setC7f23(String c7f23) {
			this.c7f23 = c7f23;
		}



		public String getC7f24() {
			return c7f24;
		}



		public void setC7f24(String c7f24) {
			this.c7f24 = c7f24;
		}



		public String getC7f25() {
			return c7f25;
		}



		public void setC7f25(String c7f25) {
			this.c7f25 = c7f25;
		}



		public String getC7f26() {
			return c7f26;
		}



		public void setC7f26(String c7f26) {
			this.c7f26 = c7f26;
		}



		public String getC7f27() {
			return c7f27;
		}



		public void setC7f27(String c7f27) {
			this.c7f27 = c7f27;
		}



		public String getC7f28() {
			return c7f28;
		}



		public void setC7f28(String c7f28) {
			this.c7f28 = c7f28;
		}
				
	    
}
