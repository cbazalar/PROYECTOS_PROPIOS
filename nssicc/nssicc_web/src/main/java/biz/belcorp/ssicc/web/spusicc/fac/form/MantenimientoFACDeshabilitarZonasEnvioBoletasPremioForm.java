package biz.belcorp.ssicc.web.spusicc.fac.form;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFACDeshabilitarZonasEnvioBoletasPremioForm extends BaseSearchForm  {
	
    private static final long serialVersionUID = 1L;
    
    private String codigoPais;
	private String correlativo;
	private String oidActividad;
	private String oidActividadTipoOferta;
	private String oidProducto;
	private String codigoPeriodo;
	private String codigoActividad;
	private String codigoTipoOferta;
	private String codigoProducto;
	private String descripcionProducto;
	private String oidTipoCliente;
	private String codigoTipoCliente;
	private String descripcionTipoCliente;
	private String codigoEstado;
	private String indicadorOrigen;
	private String indicadorEnviaMensaje;
	private String formaCobro;
	private String descripcionFormaCobro;
	private String formaPago;	
	private String descripcionFormaPago;
	private String tipoDespacho;
	private String descripcionTipoDespacho;
	private String envioSolicitud;
	private String descripcionEnvioSolicitud;
	private String numeroUnidades;
	private String precio;
	private String indicadorUnidad;	
	private String indicadorActivo;
	protected boolean editable = true;
	
	private String tabSeleccion;
	
	private String codigoConsideracion;
	private String codigoRestriccion;
	
	private String [] selectedItemsConsideracion;
	private String [] selectedItemsRestriccion;
	
	private String condicionRest1;
	private String condicionRest2;
	private String condicionRest1S;
	
	private String condicion1;
	private String condicion2;
	private String condicion1S;
	
	private String condicionMonto;
	private String condicionPeriodoInicio;
	private String condicionPeriodoFin;
	private String condicionUnidades;
	private String condicionMarca;
	private String condicionNegocio;
	private String condicionUnidadNegocio;
	private String condicionCatalogo;
	
	private String condicionRestMonto;
	private String condicionRestPeriodoInicio;
	private String condicionRestPeriodoFin;
	private String condicionRestUnidades;
	private String condicionRestMarca;
	private String condicionRestNegocio;
	private String condicionRestUnidadNegocio;
	private String condicionRestCatalogo;
	
	private String indicadorConsideracion;
	private String indicadorRestriccion;
	
	private String consideracionPedidoSuperaMonto;
	private String consideracionPedidoSuperaMontoMarca;
	private String consideracionPedidoSuperaMontoUnidad;
	private String consideracionPedidoSuperaMontoNegocio;
	private String consideracionPedidoSuperaMontoCatalogo;
	private String consideracionPedidoNoSuperaMonto;
	private String consideracionPedidoNoSuperaMontoMarca;
	private String consideracionPedidoNoSuperaMontoUnidad;
	private String consideracionPedidoNoSuperaMontoNegocio;
	private String consideracionPedidoNoSuperaMontoCatalogo;
	private String consideracionInscripcionNuevaDupla;
	
	private String restriccionPedidoSuperaMonto;
	private String restriccionPedidoSuperaMontoMarca;
	private String restriccionPedidoSuperaMontoUnidad;
	private String restriccionPedidoSuperaMontoNegocio;
	private String restriccionPedidoSuperaMontoCatalogo;
	private String restriccionPedidoNoSuperaMonto;
	private String restriccionPedidoNoSuperaMontoMarca;
	private String restriccionPedidoNoSuperaMontoUnidad;
	private String restriccionPedidoNoSuperaMontoNegocio;
	private String restriccionPedidoNoSuperaMontoCatalogo;
	private String restriccionInscripcionNuevaDupla;
	
	private String oidTipoClienteAux;
	private String oidTipoClienteAux2;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;
	private String oidClasificacion;
	
	private String regionList;
	private String zonaList;
	private String seccionList;
	private String territorioList;
	private String numeroUnidadesPopup;
	
	private String descripcionRegionList;
	private String descripcionZonaList;
	private String descripcionTerritorioList;
	private String descripcionSeccionList;
	
	private String oidPais;
	private String codigoCliente;
	private String nombreCliente;
	
	private String [] selectedItemsPopup;
	
	private String condicionDobleUnidades;
	private String condicionUnicoUnidades;
	private String condicionSimpleUnidades;
	
	private String condicionDobleRestUnidades;
	private String condicionUnicoRestUnidades;
	private String condicionSimpleRestUnidades;
	
	private String indicadorUnidadPopup;
	
	private String codigoVenta;
	private String unidadesEstimadas;
	
	/* INI SA PER-SiCC-2013-0432 */
	private String descripcionAlmacen;
	/* FIN SA PER-SiCC-2013-0432 */
	
	/* INI SA PER-SiCC-2013-0440 */
	private String indicadorValidaPrecio;
	private String observaciones;
	
	private String descripcionEstadoList;
	private String estadoList;
	
	private String[] regionListMultiple;
	private String[] zonaListMultiple;
	private String[] seccionListMultiple;
	private String[] territorioListMultiple;
	private String[] capacitadoraListMultiple;
	
	private String totalUnidadesRegion;
	private String totalUnidadesZona;
	private String totalUnidadesCapacitadora;
	/* FIN SA PER-SiCC-2013-0440 */
	
	private String indicadorCapacitadora;
	private String indicadorTipoSeleccionCapacitadora;
	
	private String totalRegiones;
	private String totalZonas;
	private String totalCapacitadoras;
	
	private String numeroUnidadesPopupUniDifNO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.form.BaseSearchForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {	
		this.codigoPais=this.correlativo=null;
		this.oidActividad = this.oidActividadTipoOferta = null;
		this.oidProducto =null;
		this.codigoPeriodo =null;
		this.codigoActividad = this.codigoTipoOferta = null;
		this.codigoProducto = this.descripcionProducto = this.oidTipoCliente = null;
		this.codigoTipoCliente = this.descripcionTipoCliente = null;
		this.codigoEstado = this.indicadorOrigen = null;
		this.indicadorEnviaMensaje = this.formaCobro = null;
		this.descripcionFormaCobro = this.formaPago = null;
		this.descripcionFormaPago = this.tipoDespacho = null;
		this.descripcionTipoDespacho = this.envioSolicitud = null;
		this.descripcionEnvioSolicitud = this.numeroUnidades = null;
		this.precio = this.indicadorActivo = this.indicadorUnidad = null;
		
		this.codigoConsideracion = null;
		this.codigoRestriccion = null;
		this.condicion1=this.condicion2=this.condicionRest1=this.condicionRest2="";
		this.condicion1S=this.condicionRest1S="";
		
		this.condicionMonto = this.condicionPeriodoInicio = this.condicionPeriodoFin = null;
		this.condicionUnidades = this.condicionMarca = null;
		this.condicionNegocio = this.condicionUnidadNegocio = this.condicionCatalogo = null;
		this.condicionRestMonto = this.condicionRestPeriodoInicio = this.condicionRestPeriodoFin = null;
		this.condicionRestUnidades = this.condicionRestMarca = null;
		this.condicionRestNegocio = this.condicionRestUnidadNegocio = this.condicionRestCatalogo = null;
		
		this.indicadorConsideracion = Constants.NRO_UNO;
		this.indicadorRestriccion = Constants.NRO_UNO;
		
		this.consideracionPedidoSuperaMonto = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO);
		this.consideracionPedidoSuperaMontoMarca = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA);
		this.consideracionPedidoSuperaMontoUnidad = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD);
		this.consideracionPedidoSuperaMontoNegocio = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO);
		this.consideracionPedidoSuperaMontoCatalogo = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO);
		this.consideracionPedidoNoSuperaMonto = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO);
		this.consideracionPedidoNoSuperaMontoMarca = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA);
		this.consideracionPedidoNoSuperaMontoUnidad = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD);
		this.consideracionPedidoNoSuperaMontoNegocio = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO);
		this.consideracionPedidoNoSuperaMontoCatalogo = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO);
		this.consideracionInscripcionNuevaDupla = String.valueOf(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA);
		
		this.restriccionPedidoSuperaMonto = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_REST);
		this.restriccionPedidoSuperaMontoMarca = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_MARCA_REST);
		this.restriccionPedidoSuperaMontoUnidad = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_UNIDAD_REST);
		this.restriccionPedidoSuperaMontoNegocio = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_NEGOCIO_REST);
		this.restriccionPedidoSuperaMontoCatalogo = String.valueOf(Constants.MAV_CONRES_PEDIDO_SUPERA_MONTO_CATALOGO_REST);
		this.restriccionPedidoNoSuperaMonto = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_REST);
		this.restriccionPedidoNoSuperaMontoMarca = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_MARCA_REST);
		this.restriccionPedidoNoSuperaMontoUnidad = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_UNIDAD_REST);
		this.restriccionPedidoNoSuperaMontoNegocio = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_NEGOCIO_REST);
		this.restriccionPedidoNoSuperaMontoCatalogo = String.valueOf(Constants.MAV_CONRES_PEDIDO_NO_SUPERA_MONTO_CATALOGO_REST);
		this.restriccionInscripcionNuevaDupla = String.valueOf(Constants.MAV_CONRES_INSCRIPCION_NUEVA_DUPLA_REST);
		
		this.oidTipoClienteAux=this.oidSubTipoCliente=	this.oidTipoClasificacion=	this.oidClasificacion="";	
		this.descripcionRegionList=this.descripcionZonaList=this.descripcionSeccionList=this.descripcionTerritorioList="";
		this.regionList=this.zonaList=this.seccionList=this.territorioList="";
		this.numeroUnidadesPopup=this.oidTipoClienteAux2="";
		this.codigoCliente=this.nombreCliente=this.oidPais=null;
		this.selectedItemsPopup=null;
		this.condicionDobleUnidades=this.condicionUnicoUnidades=this.condicionSimpleUnidades="";
		this.condicionDobleRestUnidades=this.condicionUnicoRestUnidades=this.condicionSimpleRestUnidades="";
		this.indicadorUnidadPopup=this.codigoVenta=this.unidadesEstimadas="";
		
		/* INI SA PER-SiCC-2013-0432 */
		this.descripcionAlmacen="";
		/* FIN SA PER-SiCC-2013-0432 */
		
		/* INI SA PER-SiCC-2013-0440 */
		this.observaciones="";
		this.estadoList=this.descripcionEstadoList="";
		this.regionListMultiple=this.zonaListMultiple=this.seccionListMultiple=this.territorioListMultiple=null;
		this.totalUnidadesRegion=this.totalUnidadesZona="";
		/* FIN SA PER-SiCC-2013-0440 */
		
		this.indicadorTipoSeleccionCapacitadora = Constants.INDICADOR_TIPO_SELECCION_CAPACITADORA_REGION;
	}
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the correlativo
	 */
	public String getCorrelativo() {
		return correlativo;
	}
	/**
	 * @param correlativo the correlativo to set
	 */
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	/**
	 * @return the oidActividad
	 */
	public String getOidActividad() {
		return oidActividad;
	}
	/**
	 * @param oidActividad the oidActividad to set
	 */
	public void setOidActividad(String oidActividad) {
		this.oidActividad = oidActividad;
	}
	/**
	 * @return the oidActividadTipoOferta
	 */
	public String getOidActividadTipoOferta() {
		return oidActividadTipoOferta;
	}
	/**
	 * @param oidActividadTipoOferta the oidActividadTipoOferta to set
	 */
	public void setOidActividadTipoOferta(String oidActividadTipoOferta) {
		this.oidActividadTipoOferta = oidActividadTipoOferta;
	}
	/**
	 * @return the oidProducto
	 */
	public String getOidProducto() {
		return oidProducto;
	}
	/**
	 * @param oidProducto the oidProducto to set
	 */
	public void setOidProducto(String oidProducto) {
		this.oidProducto = oidProducto;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoActividad
	 */
	public String getCodigoActividad() {
		return codigoActividad;
	}
	/**
	 * @param codigoActividad the codigoActividad to set
	 */
	public void setCodigoActividad(String codigoActividad) {
		this.codigoActividad = codigoActividad;
	}
	/**
	 * @return the codigoTipoOferta
	 */
	public String getCodigoTipoOferta() {
		return codigoTipoOferta;
	}
	/**
	 * @param codigoTipoOferta the codigoTipoOferta to set
	 */
	public void setCodigoTipoOferta(String codigoTipoOferta) {
		this.codigoTipoOferta = codigoTipoOferta;
	}
	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}
	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}
	/**
	 * @return the codigoTipoCliente
	 */
	public String getCodigoTipoCliente() {
		return codigoTipoCliente;
	}
	/**
	 * @param codigoTipoCliente the codigoTipoCliente to set
	 */
	public void setCodigoTipoCliente(String codigoTipoCliente) {
		this.codigoTipoCliente = codigoTipoCliente;
	}
	/**
	 * @return the descripcionTipoCliente
	 */
	public String getDescripcionTipoCliente() {
		return descripcionTipoCliente;
	}
	/**
	 * @param descripcionTipoCliente the descripcionTipoCliente to set
	 */
	public void setDescripcionTipoCliente(String descripcionTipoCliente) {
		this.descripcionTipoCliente = descripcionTipoCliente;
	}
	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}
	/**
	 * @param codigoEstado the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	/**
	 * @return the indicadorOrigen
	 */
	public String getIndicadorOrigen() {
		return indicadorOrigen;
	}
	/**
	 * @param indicadorOrigen the indicadorOrigen to set
	 */
	public void setIndicadorOrigen(String indicadorOrigen) {
		this.indicadorOrigen = indicadorOrigen;
	}
	/**
	 * @return the indicadorEnviaMensaje
	 */
	public String getIndicadorEnviaMensaje() {
		return indicadorEnviaMensaje;
	}
	/**
	 * @param indicadorEnviaMensaje the indicadorEnviaMensaje to set
	 */
	public void setIndicadorEnviaMensaje(String indicadorEnviaMensaje) {
		this.indicadorEnviaMensaje = indicadorEnviaMensaje;
	}
	/**
	 * @return the formaCobro
	 */
	public String getFormaCobro() {
		return formaCobro;
	}
	/**
	 * @param formaCobro the formaCobro to set
	 */
	public void setFormaCobro(String formaCobro) {
		this.formaCobro = formaCobro;
	}
	/**
	 * @return the descripcionFormaCobro
	 */
	public String getDescripcionFormaCobro() {
		return descripcionFormaCobro;
	}
	/**
	 * @param descripcionFormaCobro the descripcionFormaCobro to set
	 */
	public void setDescripcionFormaCobro(String descripcionFormaCobro) {
		this.descripcionFormaCobro = descripcionFormaCobro;
	}
	/**
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}
	/**
	 * @param formaPago the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	/**
	 * @return the descripcionFormaPago
	 */
	public String getDescripcionFormaPago() {
		return descripcionFormaPago;
	}
	/**
	 * @param descripcionFormaPago the descripcionFormaPago to set
	 */
	public void setDescripcionFormaPago(String descripcionFormaPago) {
		this.descripcionFormaPago = descripcionFormaPago;
	}
	/**
	 * @return the tipoDespacho
	 */
	public String getTipoDespacho() {
		return tipoDespacho;
	}
	/**
	 * @param tipoDespacho the tipoDespacho to set
	 */
	public void setTipoDespacho(String tipoDespacho) {
		this.tipoDespacho = tipoDespacho;
	}
	/**
	 * @return the descripcionTipoDespacho
	 */
	public String getDescripcionTipoDespacho() {
		return descripcionTipoDespacho;
	}
	/**
	 * @param descripcionTipoDespacho the descripcionTipoDespacho to set
	 */
	public void setDescripcionTipoDespacho(String descripcionTipoDespacho) {
		this.descripcionTipoDespacho = descripcionTipoDespacho;
	}
	/**
	 * @return the envioSolicitud
	 */
	public String getEnvioSolicitud() {
		return envioSolicitud;
	}
	/**
	 * @param envioSolicitud the envioSolicitud to set
	 */
	public void setEnvioSolicitud(String envioSolicitud) {
		this.envioSolicitud = envioSolicitud;
	}
	/**
	 * @return the descripcionEnvioSolicitud
	 */
	public String getDescripcionEnvioSolicitud() {
		return descripcionEnvioSolicitud;
	}
	/**
	 * @param descripcionEnvioSolicitud the descripcionEnvioSolicitud to set
	 */
	public void setDescripcionEnvioSolicitud(String descripcionEnvioSolicitud) {
		this.descripcionEnvioSolicitud = descripcionEnvioSolicitud;
	}
	/**
	 * @return the numeroUnidades
	 */
	public String getNumeroUnidades() {
		return numeroUnidades;
	}
	/**
	 * @param numeroUnidades the numeroUnidades to set
	 */
	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}
	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}
	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return the tabSeleccion
	 */
	public String getTabSeleccion() {
		return tabSeleccion;
	}

	/**
	 * @param tabSeleccion the tabSeleccion to set
	 */
	public void setTabSeleccion(String tabSeleccion) {
		this.tabSeleccion = tabSeleccion;
	}

	/**
	 * @return the codigoConsideracion
	 */
	public String getCodigoConsideracion() {
		return codigoConsideracion;
	}

	/**
	 * @param codigoConsideracion the codigoConsideracion to set
	 */
	public void setCodigoConsideracion(String codigoConsideracion) {
		this.codigoConsideracion = codigoConsideracion;
	}

	/**
	 * @return the codigoRestriccion
	 */
	public String getCodigoRestriccion() {
		return codigoRestriccion;
	}

	/**
	 * @param codigoRestriccion the codigoRestriccion to set
	 */
	public void setCodigoRestriccion(String codigoRestriccion) {
		this.codigoRestriccion = codigoRestriccion;
	}

	/**
	 * @return the selectedItemsConsideracion
	 */
	public String[] getSelectedItemsConsideracion() {
		return selectedItemsConsideracion;
	}

	/**
	 * @param selectedItemsConsideracion the selectedItemsConsideracion to set
	 */
	public void setSelectedItemsConsideracion(String[] selectedItemsConsideracion) {
		this.selectedItemsConsideracion = selectedItemsConsideracion;
	}

	/**
	 * @return the selectedItemsRestriccion
	 */
	public String[] getSelectedItemsRestriccion() {
		return selectedItemsRestriccion;
	}

	/**
	 * @param selectedItemsRestriccion the selectedItemsRestriccion to set
	 */
	public void setSelectedItemsRestriccion(String[] selectedItemsRestriccion) {
		this.selectedItemsRestriccion = selectedItemsRestriccion;
	}

	/**
	 * @return the condicionRest1
	 */
	public String getCondicionRest1() {
		return condicionRest1;
	}

	/**
	 * @param condicionRest1 the condicionRest1 to set
	 */
	public void setCondicionRest1(String condicionRest1) {
		this.condicionRest1 = condicionRest1;
	}

	/**
	 * @return the condicionRest2
	 */
	public String getCondicionRest2() {
		return condicionRest2;
	}

	/**
	 * @param condicionRest2 the condicionRest2 to set
	 */
	public void setCondicionRest2(String condicionRest2) {
		this.condicionRest2 = condicionRest2;
	}

	/**
	 * @return the condicionRest1S
	 */
	public String getCondicionRest1S() {
		return condicionRest1S;
	}

	/**
	 * @param condicionRest1S the condicionRest1S to set
	 */
	public void setCondicionRest1S(String condicionRest1S) {
		this.condicionRest1S = condicionRest1S;
	}

	/**
	 * @return the condicion1
	 */
	public String getCondicion1() {
		return condicion1;
	}

	/**
	 * @param condicion1 the condicion1 to set
	 */
	public void setCondicion1(String condicion1) {
		this.condicion1 = condicion1;
	}

	/**
	 * @return the condicion2
	 */
	public String getCondicion2() {
		return condicion2;
	}

	/**
	 * @param condicion2 the condicion2 to set
	 */
	public void setCondicion2(String condicion2) {
		this.condicion2 = condicion2;
	}

	/**
	 * @return the condicion1S
	 */
	public String getCondicion1S() {
		return condicion1S;
	}

	/**
	 * @param condicion1s the condicion1S to set
	 */
	public void setCondicion1S(String condicion1s) {
		condicion1S = condicion1s;
	}

	/**
	 * @return the indicadorConsideracion
	 */
	public String getIndicadorConsideracion() {
		return indicadorConsideracion;
	}

	/**
	 * @param indicadorConsideracion the indicadorConsideracion to set
	 */
	public void setIndicadorConsideracion(String indicadorConsideracion) {
		this.indicadorConsideracion = indicadorConsideracion;
	}

	/**
	 * @return the indicadorRestriccion
	 */
	public String getIndicadorRestriccion() {
		return indicadorRestriccion;
	}

	/**
	 * @param indicadorRestriccion the indicadorRestriccion to set
	 */
	public void setIndicadorRestriccion(String indicadorRestriccion) {
		this.indicadorRestriccion = indicadorRestriccion;
	}

	/**
	 * @return the condicionMonto
	 */
	public String getCondicionMonto() {
		return condicionMonto;
	}

	/**
	 * @param condicionMonto the condicionMonto to set
	 */
	public void setCondicionMonto(String condicionMonto) {
		this.condicionMonto = condicionMonto;
	}

	/**
	 * @return the condicionPeriodoInicio
	 */
	public String getCondicionPeriodoInicio() {
		return condicionPeriodoInicio;
	}

	/**
	 * @param condicionPeriodoInicio the condicionPeriodoInicio to set
	 */
	public void setCondicionPeriodoInicio(String condicionPeriodoInicio) {
		this.condicionPeriodoInicio = condicionPeriodoInicio;
	}

	/**
	 * @return the condicionPeriodoFin
	 */
	public String getCondicionPeriodoFin() {
		return condicionPeriodoFin;
	}

	/**
	 * @param condicionPeriodoFin the condicionPeriodoFin to set
	 */
	public void setCondicionPeriodoFin(String condicionPeriodoFin) {
		this.condicionPeriodoFin = condicionPeriodoFin;
	}

	/**
	 * @return the condicionUnidades
	 */
	public String getCondicionUnidades() {
		return condicionUnidades;
	}

	/**
	 * @param condicionUnidades the condicionUnidades to set
	 */
	public void setCondicionUnidades(String condicionUnidades) {
		this.condicionUnidades = condicionUnidades;
	}

	/**
	 * @return the condicionMarca
	 */
	public String getCondicionMarca() {
		return condicionMarca;
	}

	/**
	 * @param condicionMarca the condicionMarca to set
	 */
	public void setCondicionMarca(String condicionMarca) {
		this.condicionMarca = condicionMarca;
	}

	/**
	 * @return the condicionNegocio
	 */
	public String getCondicionNegocio() {
		return condicionNegocio;
	}

	/**
	 * @param condicionNegocio the condicionNegocio to set
	 */
	public void setCondicionNegocio(String condicionNegocio) {
		this.condicionNegocio = condicionNegocio;
	}

	/**
	 * @return the condicionUnidadNegocio
	 */
	public String getCondicionUnidadNegocio() {
		return condicionUnidadNegocio;
	}

	/**
	 * @param condicionUnidadNegocio the condicionUnidadNegocio to set
	 */
	public void setCondicionUnidadNegocio(String condicionUnidadNegocio) {
		this.condicionUnidadNegocio = condicionUnidadNegocio;
	}

	/**
	 * @return the condicionCatalogo
	 */
	public String getCondicionCatalogo() {
		return condicionCatalogo;
	}

	/**
	 * @param condicionCatalogo the condicionCatalogo to set
	 */
	public void setCondicionCatalogo(String condicionCatalogo) {
		this.condicionCatalogo = condicionCatalogo;
	}

	/**
	 * @return the condicionRestMonto
	 */
	public String getCondicionRestMonto() {
		return condicionRestMonto;
	}

	/**
	 * @param condicionRestMonto the condicionRestMonto to set
	 */
	public void setCondicionRestMonto(String condicionRestMonto) {
		this.condicionRestMonto = condicionRestMonto;
	}

	/**
	 * @return the condicionRestPeriodoInicio
	 */
	public String getCondicionRestPeriodoInicio() {
		return condicionRestPeriodoInicio;
	}

	/**
	 * @param condicionRestPeriodoInicio the condicionRestPeriodoInicio to set
	 */
	public void setCondicionRestPeriodoInicio(String condicionRestPeriodoInicio) {
		this.condicionRestPeriodoInicio = condicionRestPeriodoInicio;
	}

	/**
	 * @return the condicionRestPeriodoFin
	 */
	public String getCondicionRestPeriodoFin() {
		return condicionRestPeriodoFin;
	}

	/**
	 * @param condicionRestPeriodoFin the condicionRestPeriodoFin to set
	 */
	public void setCondicionRestPeriodoFin(String condicionRestPeriodoFin) {
		this.condicionRestPeriodoFin = condicionRestPeriodoFin;
	}

	/**
	 * @return the condicionRestUnidades
	 */
	public String getCondicionRestUnidades() {
		return condicionRestUnidades;
	}

	/**
	 * @param condicionRestUnidades the condicionRestUnidades to set
	 */
	public void setCondicionRestUnidades(String condicionRestUnidades) {
		this.condicionRestUnidades = condicionRestUnidades;
	}

	/**
	 * @return the condicionRestMarca
	 */
	public String getCondicionRestMarca() {
		return condicionRestMarca;
	}

	/**
	 * @param condicionRestMarca the condicionRestMarca to set
	 */
	public void setCondicionRestMarca(String condicionRestMarca) {
		this.condicionRestMarca = condicionRestMarca;
	}

	/**
	 * @return the condicionRestNegocio
	 */
	public String getCondicionRestNegocio() {
		return condicionRestNegocio;
	}

	/**
	 * @param condicionRestNegocio the condicionRestNegocio to set
	 */
	public void setCondicionRestNegocio(String condicionRestNegocio) {
		this.condicionRestNegocio = condicionRestNegocio;
	}

	/**
	 * @return the condicionRestUnidadNegocio
	 */
	public String getCondicionRestUnidadNegocio() {
		return condicionRestUnidadNegocio;
	}

	/**
	 * @param condicionRestUnidadNegocio the condicionRestUnidadNegocio to set
	 */
	public void setCondicionRestUnidadNegocio(String condicionRestUnidadNegocio) {
		this.condicionRestUnidadNegocio = condicionRestUnidadNegocio;
	}

	/**
	 * @return the condicionRestCatalogo
	 */
	public String getCondicionRestCatalogo() {
		return condicionRestCatalogo;
	}

	/**
	 * @param condicionRestCatalogo the condicionRestCatalogo to set
	 */
	public void setCondicionRestCatalogo(String condicionRestCatalogo) {
		this.condicionRestCatalogo = condicionRestCatalogo;
	}

	/**
	 * @return the consideracionPedidoSuperaMonto
	 */
	public String getConsideracionPedidoSuperaMonto() {
		return consideracionPedidoSuperaMonto;
	}

	/**
	 * @param consideracionPedidoSuperaMonto the consideracionPedidoSuperaMonto to set
	 */
	public void setConsideracionPedidoSuperaMonto(
			String consideracionPedidoSuperaMonto) {
		this.consideracionPedidoSuperaMonto = consideracionPedidoSuperaMonto;
	}

	/**
	 * @return the consideracionPedidoSuperaMontoMarca
	 */
	public String getConsideracionPedidoSuperaMontoMarca() {
		return consideracionPedidoSuperaMontoMarca;
	}

	/**
	 * @param consideracionPedidoSuperaMontoMarca the consideracionPedidoSuperaMontoMarca to set
	 */
	public void setConsideracionPedidoSuperaMontoMarca(
			String consideracionPedidoSuperaMontoMarca) {
		this.consideracionPedidoSuperaMontoMarca = consideracionPedidoSuperaMontoMarca;
	}

	/**
	 * @return the consideracionPedidoSuperaMontoUnidad
	 */
	public String getConsideracionPedidoSuperaMontoUnidad() {
		return consideracionPedidoSuperaMontoUnidad;
	}

	/**
	 * @param consideracionPedidoSuperaMontoUnidad the consideracionPedidoSuperaMontoUnidad to set
	 */
	public void setConsideracionPedidoSuperaMontoUnidad(
			String consideracionPedidoSuperaMontoUnidad) {
		this.consideracionPedidoSuperaMontoUnidad = consideracionPedidoSuperaMontoUnidad;
	}

	/**
	 * @return the consideracionPedidoSuperaMontoNegocio
	 */
	public String getConsideracionPedidoSuperaMontoNegocio() {
		return consideracionPedidoSuperaMontoNegocio;
	}

	/**
	 * @param consideracionPedidoSuperaMontoNegocio the consideracionPedidoSuperaMontoNegocio to set
	 */
	public void setConsideracionPedidoSuperaMontoNegocio(
			String consideracionPedidoSuperaMontoNegocio) {
		this.consideracionPedidoSuperaMontoNegocio = consideracionPedidoSuperaMontoNegocio;
	}

	/**
	 * @return the consideracionPedidoSuperaMontoCatalogo
	 */
	public String getConsideracionPedidoSuperaMontoCatalogo() {
		return consideracionPedidoSuperaMontoCatalogo;
	}

	/**
	 * @param consideracionPedidoSuperaMontoCatalogo the consideracionPedidoSuperaMontoCatalogo to set
	 */
	public void setConsideracionPedidoSuperaMontoCatalogo(
			String consideracionPedidoSuperaMontoCatalogo) {
		this.consideracionPedidoSuperaMontoCatalogo = consideracionPedidoSuperaMontoCatalogo;
	}

	/**
	 * @return the consideracionPedidoNoSuperaMonto
	 */
	public String getConsideracionPedidoNoSuperaMonto() {
		return consideracionPedidoNoSuperaMonto;
	}

	/**
	 * @param consideracionPedidoNoSuperaMonto the consideracionPedidoNoSuperaMonto to set
	 */
	public void setConsideracionPedidoNoSuperaMonto(
			String consideracionPedidoNoSuperaMonto) {
		this.consideracionPedidoNoSuperaMonto = consideracionPedidoNoSuperaMonto;
	}

	/**
	 * @return the consideracionPedidoNoSuperaMontoMarca
	 */
	public String getConsideracionPedidoNoSuperaMontoMarca() {
		return consideracionPedidoNoSuperaMontoMarca;
	}

	/**
	 * @param consideracionPedidoNoSuperaMontoMarca the consideracionPedidoNoSuperaMontoMarca to set
	 */
	public void setConsideracionPedidoNoSuperaMontoMarca(
			String consideracionPedidoNoSuperaMontoMarca) {
		this.consideracionPedidoNoSuperaMontoMarca = consideracionPedidoNoSuperaMontoMarca;
	}

	/**
	 * @return the consideracionPedidoNoSuperaMontoUnidad
	 */
	public String getConsideracionPedidoNoSuperaMontoUnidad() {
		return consideracionPedidoNoSuperaMontoUnidad;
	}

	/**
	 * @param consideracionPedidoNoSuperaMontoUnidad the consideracionPedidoNoSuperaMontoUnidad to set
	 */
	public void setConsideracionPedidoNoSuperaMontoUnidad(
			String consideracionPedidoNoSuperaMontoUnidad) {
		this.consideracionPedidoNoSuperaMontoUnidad = consideracionPedidoNoSuperaMontoUnidad;
	}

	/**
	 * @return the consideracionPedidoNoSuperaMontoNegocio
	 */
	public String getConsideracionPedidoNoSuperaMontoNegocio() {
		return consideracionPedidoNoSuperaMontoNegocio;
	}

	/**
	 * @param consideracionPedidoNoSuperaMontoNegocio the consideracionPedidoNoSuperaMontoNegocio to set
	 */
	public void setConsideracionPedidoNoSuperaMontoNegocio(
			String consideracionPedidoNoSuperaMontoNegocio) {
		this.consideracionPedidoNoSuperaMontoNegocio = consideracionPedidoNoSuperaMontoNegocio;
	}

	/**
	 * @return the consideracionPedidoNoSuperaMontoCatalogo
	 */
	public String getConsideracionPedidoNoSuperaMontoCatalogo() {
		return consideracionPedidoNoSuperaMontoCatalogo;
	}

	/**
	 * @param consideracionPedidoNoSuperaMontoCatalogo the consideracionPedidoNoSuperaMontoCatalogo to set
	 */
	public void setConsideracionPedidoNoSuperaMontoCatalogo(
			String consideracionPedidoNoSuperaMontoCatalogo) {
		this.consideracionPedidoNoSuperaMontoCatalogo = consideracionPedidoNoSuperaMontoCatalogo;
	}

	/**
	 * @return the consideracionInscripcionNuevaDupla
	 */
	public String getConsideracionInscripcionNuevaDupla() {
		return consideracionInscripcionNuevaDupla;
	}

	/**
	 * @param consideracionInscripcionNuevaDupla the consideracionInscripcionNuevaDupla to set
	 */
	public void setConsideracionInscripcionNuevaDupla(
			String consideracionInscripcionNuevaDupla) {
		this.consideracionInscripcionNuevaDupla = consideracionInscripcionNuevaDupla;
	}

	/**
	 * @return the restriccionPedidoSuperaMonto
	 */
	public String getRestriccionPedidoSuperaMonto() {
		return restriccionPedidoSuperaMonto;
	}

	/**
	 * @param restriccionPedidoSuperaMonto the restriccionPedidoSuperaMonto to set
	 */
	public void setRestriccionPedidoSuperaMonto(String restriccionPedidoSuperaMonto) {
		this.restriccionPedidoSuperaMonto = restriccionPedidoSuperaMonto;
	}

	/**
	 * @return the restriccionPedidoSuperaMontoMarca
	 */
	public String getRestriccionPedidoSuperaMontoMarca() {
		return restriccionPedidoSuperaMontoMarca;
	}

	/**
	 * @param restriccionPedidoSuperaMontoMarca the restriccionPedidoSuperaMontoMarca to set
	 */
	public void setRestriccionPedidoSuperaMontoMarca(
			String restriccionPedidoSuperaMontoMarca) {
		this.restriccionPedidoSuperaMontoMarca = restriccionPedidoSuperaMontoMarca;
	}

	/**
	 * @return the restriccionPedidoSuperaMontoUnidad
	 */
	public String getRestriccionPedidoSuperaMontoUnidad() {
		return restriccionPedidoSuperaMontoUnidad;
	}

	/**
	 * @param restriccionPedidoSuperaMontoUnidad the restriccionPedidoSuperaMontoUnidad to set
	 */
	public void setRestriccionPedidoSuperaMontoUnidad(
			String restriccionPedidoSuperaMontoUnidad) {
		this.restriccionPedidoSuperaMontoUnidad = restriccionPedidoSuperaMontoUnidad;
	}

	/**
	 * @return the restriccionPedidoSuperaMontoNegocio
	 */
	public String getRestriccionPedidoSuperaMontoNegocio() {
		return restriccionPedidoSuperaMontoNegocio;
	}

	/**
	 * @param restriccionPedidoSuperaMontoNegocio the restriccionPedidoSuperaMontoNegocio to set
	 */
	public void setRestriccionPedidoSuperaMontoNegocio(
			String restriccionPedidoSuperaMontoNegocio) {
		this.restriccionPedidoSuperaMontoNegocio = restriccionPedidoSuperaMontoNegocio;
	}

	/**
	 * @return the restriccionPedidoSuperaMontoCatalogo
	 */
	public String getRestriccionPedidoSuperaMontoCatalogo() {
		return restriccionPedidoSuperaMontoCatalogo;
	}

	/**
	 * @param restriccionPedidoSuperaMontoCatalogo the restriccionPedidoSuperaMontoCatalogo to set
	 */
	public void setRestriccionPedidoSuperaMontoCatalogo(
			String restriccionPedidoSuperaMontoCatalogo) {
		this.restriccionPedidoSuperaMontoCatalogo = restriccionPedidoSuperaMontoCatalogo;
	}

	/**
	 * @return the restriccionPedidoNoSuperaMonto
	 */
	public String getRestriccionPedidoNoSuperaMonto() {
		return restriccionPedidoNoSuperaMonto;
	}

	/**
	 * @param restriccionPedidoNoSuperaMonto the restriccionPedidoNoSuperaMonto to set
	 */
	public void setRestriccionPedidoNoSuperaMonto(
			String restriccionPedidoNoSuperaMonto) {
		this.restriccionPedidoNoSuperaMonto = restriccionPedidoNoSuperaMonto;
	}

	/**
	 * @return the restriccionPedidoNoSuperaMontoMarca
	 */
	public String getRestriccionPedidoNoSuperaMontoMarca() {
		return restriccionPedidoNoSuperaMontoMarca;
	}

	/**
	 * @param restriccionPedidoNoSuperaMontoMarca the restriccionPedidoNoSuperaMontoMarca to set
	 */
	public void setRestriccionPedidoNoSuperaMontoMarca(
			String restriccionPedidoNoSuperaMontoMarca) {
		this.restriccionPedidoNoSuperaMontoMarca = restriccionPedidoNoSuperaMontoMarca;
	}

	/**
	 * @return the restriccionPedidoNoSuperaMontoUnidad
	 */
	public String getRestriccionPedidoNoSuperaMontoUnidad() {
		return restriccionPedidoNoSuperaMontoUnidad;
	}

	/**
	 * @param restriccionPedidoNoSuperaMontoUnidad the restriccionPedidoNoSuperaMontoUnidad to set
	 */
	public void setRestriccionPedidoNoSuperaMontoUnidad(
			String restriccionPedidoNoSuperaMontoUnidad) {
		this.restriccionPedidoNoSuperaMontoUnidad = restriccionPedidoNoSuperaMontoUnidad;
	}

	/**
	 * @return the restriccionPedidoNoSuperaMontoNegocio
	 */
	public String getRestriccionPedidoNoSuperaMontoNegocio() {
		return restriccionPedidoNoSuperaMontoNegocio;
	}

	/**
	 * @param restriccionPedidoNoSuperaMontoNegocio the restriccionPedidoNoSuperaMontoNegocio to set
	 */
	public void setRestriccionPedidoNoSuperaMontoNegocio(
			String restriccionPedidoNoSuperaMontoNegocio) {
		this.restriccionPedidoNoSuperaMontoNegocio = restriccionPedidoNoSuperaMontoNegocio;
	}

	/**
	 * @return the restriccionPedidoNoSuperaMontoCatalogo
	 */
	public String getRestriccionPedidoNoSuperaMontoCatalogo() {
		return restriccionPedidoNoSuperaMontoCatalogo;
	}

	/**
	 * @param restriccionPedidoNoSuperaMontoCatalogo the restriccionPedidoNoSuperaMontoCatalogo to set
	 */
	public void setRestriccionPedidoNoSuperaMontoCatalogo(
			String restriccionPedidoNoSuperaMontoCatalogo) {
		this.restriccionPedidoNoSuperaMontoCatalogo = restriccionPedidoNoSuperaMontoCatalogo;
	}

	/**
	 * @return the restriccionInscripcionNuevaDupla
	 */
	public String getRestriccionInscripcionNuevaDupla() {
		return restriccionInscripcionNuevaDupla;
	}

	/**
	 * @param restriccionInscripcionNuevaDupla the restriccionInscripcionNuevaDupla to set
	 */
	public void setRestriccionInscripcionNuevaDupla(
			String restriccionInscripcionNuevaDupla) {
		this.restriccionInscripcionNuevaDupla = restriccionInscripcionNuevaDupla;
	}

	/**
	 * @return the oidTipoClienteAux
	 */
	public String getOidTipoClienteAux() {
		return oidTipoClienteAux;
	}

	/**
	 * @param oidTipoClienteAux the oidTipoClienteAux to set
	 */
	public void setOidTipoClienteAux(String oidTipoClienteAux) {
		this.oidTipoClienteAux = oidTipoClienteAux;
	}

	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the regionList
	 */
	public String getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList the regionList to set
	 */
	public void setRegionList(String regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return the zonaList
	 */
	public String getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList the zonaList to set
	 */
	public void setZonaList(String zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return the seccionList
	 */
	public String getSeccionList() {
		return seccionList;
	}

	/**
	 * @param seccionList the seccionList to set
	 */
	public void setSeccionList(String seccionList) {
		this.seccionList = seccionList;
	}

	/**
	 * @return the territorioList
	 */
	public String getTerritorioList() {
		return territorioList;
	}

	/**
	 * @param territorioList the territorioList to set
	 */
	public void setTerritorioList(String territorioList) {
		this.territorioList = territorioList;
	}

	/**
	 * @return the descripcionRegionList
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}

	/**
	 * @param descripcionRegionList the descripcionRegionList to set
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		this.descripcionRegionList = descripcionRegionList;
	}

	/**
	 * @return the descripcionZonaList
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}

	/**
	 * @param descripcionZonaList the descripcionZonaList to set
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		this.descripcionZonaList = descripcionZonaList;
	}

	/**
	 * @return the descripcionTerritorioList
	 */
	public String getDescripcionTerritorioList() {
		return descripcionTerritorioList;
	}

	/**
	 * @param descripcionTerritorioList the descripcionTerritorioList to set
	 */
	public void setDescripcionTerritorioList(String descripcionTerritorioList) {
		this.descripcionTerritorioList = descripcionTerritorioList;
	}

	/**
	 * @return the descripcionSeccionList
	 */
	public String getDescripcionSeccionList() {
		return descripcionSeccionList;
	}

	/**
	 * @param descripcionSeccionList the descripcionSeccionList to set
	 */
	public void setDescripcionSeccionList(String descripcionSeccionList) {
		this.descripcionSeccionList = descripcionSeccionList;
	}

	/**
	 * @return the selectedItemsPopup
	 */
	public String[] getSelectedItemsPopup() {
		return selectedItemsPopup;
	}

	/**
	 * @param selectedItemsPopup the selectedItemsPopup to set
	 */
	public void setSelectedItemsPopup(String[] selectedItemsPopup) {
		this.selectedItemsPopup = selectedItemsPopup;
	}

	/**
	 * @return the numeroUnidadesPopup
	 */
	public String getNumeroUnidadesPopup() {
		return numeroUnidadesPopup;
	}

	/**
	 * @param numeroUnidadesPopup the numeroUnidadesPopup to set
	 */
	public void setNumeroUnidadesPopup(String numeroUnidadesPopup) {
		this.numeroUnidadesPopup = numeroUnidadesPopup;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}

	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}

	/**
	 * @return the indicadorUnidad
	 */
	public String getIndicadorUnidad() {
		return indicadorUnidad;
	}

	/**
	 * @param indicadorUnidad the indicadorUnidad to set
	 */
	public void setIndicadorUnidad(String indicadorUnidad) {
		this.indicadorUnidad = indicadorUnidad;
	}

	/**
	 * @return the oidTipoClienteAux2
	 */
	public String getOidTipoClienteAux2() {
		return oidTipoClienteAux2;
	}

	/**
	 * @param oidTipoClienteAux2 the oidTipoClienteAux2 to set
	 */
	public void setOidTipoClienteAux2(String oidTipoClienteAux2) {
		this.oidTipoClienteAux2 = oidTipoClienteAux2;
	}

	/**
	 * @return the condicionDobleUnidades
	 */
	public String getCondicionDobleUnidades() {
		return condicionDobleUnidades;
	}

	/**
	 * @param condicionDobleUnidades the condicionDobleUnidades to set
	 */
	public void setCondicionDobleUnidades(String condicionDobleUnidades) {
		this.condicionDobleUnidades = condicionDobleUnidades;
	}

	/**
	 * @return the condicionUnicoUnidades
	 */
	public String getCondicionUnicoUnidades() {
		return condicionUnicoUnidades;
	}

	/**
	 * @param condicionUnicoUnidades the condicionUnicoUnidades to set
	 */
	public void setCondicionUnicoUnidades(String condicionUnicoUnidades) {
		this.condicionUnicoUnidades = condicionUnicoUnidades;
	}

	/**
	 * @return the condicionSimpleUnidades
	 */
	public String getCondicionSimpleUnidades() {
		return condicionSimpleUnidades;
	}

	/**
	 * @param condicionSimpleUnidades the condicionSimpleUnidades to set
	 */
	public void setCondicionSimpleUnidades(String condicionSimpleUnidades) {
		this.condicionSimpleUnidades = condicionSimpleUnidades;
	}

	/**
	 * @return the condicionDobleRestUnidades
	 */
	public String getCondicionDobleRestUnidades() {
		return condicionDobleRestUnidades;
	}

	/**
	 * @param condicionDobleRestUnidades the condicionDobleRestUnidades to set
	 */
	public void setCondicionDobleRestUnidades(String condicionDobleRestUnidades) {
		this.condicionDobleRestUnidades = condicionDobleRestUnidades;
	}

	/**
	 * @return the condicionUnicoRestUnidades
	 */
	public String getCondicionUnicoRestUnidades() {
		return condicionUnicoRestUnidades;
	}

	/**
	 * @param condicionUnicoRestUnidades the condicionUnicoRestUnidades to set
	 */
	public void setCondicionUnicoRestUnidades(String condicionUnicoRestUnidades) {
		this.condicionUnicoRestUnidades = condicionUnicoRestUnidades;
	}

	/**
	 * @return the condicionSimpleRestUnidades
	 */
	public String getCondicionSimpleRestUnidades() {
		return condicionSimpleRestUnidades;
	}

	/**
	 * @param condicionSimpleRestUnidades the condicionSimpleRestUnidades to set
	 */
	public void setCondicionSimpleRestUnidades(String condicionSimpleRestUnidades) {
		this.condicionSimpleRestUnidades = condicionSimpleRestUnidades;
	}

	/**
	 * @return the indicadorUnidadPopup
	 */
	public String getIndicadorUnidadPopup() {
		return indicadorUnidadPopup;
	}

	/**
	 * @param indicadorUnidadPopup the indicadorUnidadPopup to set
	 */
	public void setIndicadorUnidadPopup(String indicadorUnidadPopup) {
		this.indicadorUnidadPopup = indicadorUnidadPopup;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the unidadesEstimadas
	 */
	public String getUnidadesEstimadas() {
		return unidadesEstimadas;
	}

	/**
	 * @param unidadesEstimadas the unidadesEstimadas to set
	 */
	public void setUnidadesEstimadas(String unidadesEstimadas) {
		this.unidadesEstimadas = unidadesEstimadas;
	}

	/**
	 * @return the descripcionAlmacen
	 */
	public String getDescripcionAlmacen() {
		return descripcionAlmacen;
	}

	/**
	 * @param descripcionAlmacen the descripcionAlmacen to set
	 */
	public void setDescripcionAlmacen(String descripcionAlmacen) {
		this.descripcionAlmacen = descripcionAlmacen;
	}

	/**
	 * @return the indicadorValidaPrecio
	 */
	public String getIndicadorValidaPrecio() {
		return indicadorValidaPrecio;
	}

	/**
	 * @param indicadorValidaPrecio the indicadorValidaPrecio to set
	 */
	public void setIndicadorValidaPrecio(String indicadorValidaPrecio) {
		this.indicadorValidaPrecio = indicadorValidaPrecio;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the descripcionEstadoList
	 */
	public String getDescripcionEstadoList() {
		return descripcionEstadoList;
	}

	/**
	 * @param descripcionEstadoList the descripcionEstadoList to set
	 */
	public void setDescripcionEstadoList(String descripcionEstadoList) {
		this.descripcionEstadoList = descripcionEstadoList;
	}

	/**
	 * @return the estadoList
	 */
	public String getEstadoList() {
		return estadoList;
	}

	/**
	 * @param estadoList the estadoList to set
	 */
	public void setEstadoList(String estadoList) {
		this.estadoList = estadoList;
	}

	/**
	 * @return the regionListMultiple
	 */
	public String[] getRegionListMultiple() {
		return regionListMultiple;
	}

	/**
	 * @param regionListMultiple the regionListMultiple to set
	 */
	public void setRegionListMultiple(String[] regionListMultiple) {
		this.regionListMultiple = regionListMultiple;
	}

	/**
	 * @return the zonaListMultiple
	 */
	public String[] getZonaListMultiple() {
		return zonaListMultiple;
	}

	/**
	 * @param zonaListMultiple the zonaListMultiple to set
	 */
	public void setZonaListMultiple(String[] zonaListMultiple) {
		this.zonaListMultiple = zonaListMultiple;
	}

	/**
	 * @return the seccionListMultiple
	 */
	public String[] getSeccionListMultiple() {
		return seccionListMultiple;
	}

	/**
	 * @param seccionListMultiple the seccionListMultiple to set
	 */
	public void setSeccionListMultiple(String[] seccionListMultiple) {
		this.seccionListMultiple = seccionListMultiple;
	}

	/**
	 * @return the territorioListMultiple
	 */
	public String[] getTerritorioListMultiple() {
		return territorioListMultiple;
	}

	/**
	 * @param territorioListMultiple the territorioListMultiple to set
	 */
	public void setTerritorioListMultiple(String[] territorioListMultiple) {
		this.territorioListMultiple = territorioListMultiple;
	}

	/**
	 * @return the totalUnidadesRegion
	 */
	public String getTotalUnidadesRegion() {
		return totalUnidadesRegion;
	}

	/**
	 * @param totalUnidadesRegion the totalUnidadesRegion to set
	 */
	public void setTotalUnidadesRegion(String totalUnidadesRegion) {
		this.totalUnidadesRegion = totalUnidadesRegion;
	}

	/**
	 * @return the totalUnidadesZona
	 */
	public String getTotalUnidadesZona() {
		return totalUnidadesZona;
	}

	/**
	 * @param totalUnidadesZona the totalUnidadesZona to set
	 */
	public void setTotalUnidadesZona(String totalUnidadesZona) {
		this.totalUnidadesZona = totalUnidadesZona;
	}

	/**
	 * @return the indicadorCapacitadora
	 */
	public String getIndicadorCapacitadora() {
		return indicadorCapacitadora;
	}

	/**
	 * @param indicadorCapacitadora the indicadorCapacitadora to set
	 */
	public void setIndicadorCapacitadora(String indicadorCapacitadora) {
		this.indicadorCapacitadora = indicadorCapacitadora;
	}

	/**
	 * @return the capacitadoraListMultiple
	 */
	public String[] getCapacitadoraListMultiple() {
		return capacitadoraListMultiple;
	}

	/**
	 * @param capacitadoraListMultiple the capacitadoraListMultiple to set
	 */
	public void setCapacitadoraListMultiple(String[] capacitadoraListMultiple) {
		this.capacitadoraListMultiple = capacitadoraListMultiple;
	}

	/**
	 * @return the indicadorTipoSeleccionCapacitadora
	 */
	public String getIndicadorTipoSeleccionCapacitadora() {
		return indicadorTipoSeleccionCapacitadora;
	}

	/**
	 * @param indicadorTipoSeleccionCapacitadora the indicadorTipoSeleccionCapacitadora to set
	 */
	public void setIndicadorTipoSeleccionCapacitadora(
			String indicadorTipoSeleccionCapacitadora) {
		this.indicadorTipoSeleccionCapacitadora = indicadorTipoSeleccionCapacitadora;
	}

	/**
	 * @return the totalUnidadesCapacitadora
	 */
	public String getTotalUnidadesCapacitadora() {
		return totalUnidadesCapacitadora;
	}

	/**
	 * @param totalUnidadesCapacitadora the totalUnidadesCapacitadora to set
	 */
	public void setTotalUnidadesCapacitadora(String totalUnidadesCapacitadora) {
		this.totalUnidadesCapacitadora = totalUnidadesCapacitadora;
	}

	/**
	 * @return the totalRegiones
	 */
	public String getTotalRegiones() {
		return totalRegiones;
	}

	/**
	 * @param totalRegiones the totalRegiones to set
	 */
	public void setTotalRegiones(String totalRegiones) {
		this.totalRegiones = totalRegiones;
	}

	/**
	 * @return the totalZonas
	 */
	public String getTotalZonas() {
		return totalZonas;
	}

	/**
	 * @param totalZonas the totalZonas to set
	 */
	public void setTotalZonas(String totalZonas) {
		this.totalZonas = totalZonas;
	}

	/**
	 * @return the totalCapacitadoras
	 */
	public String getTotalCapacitadoras() {
		return totalCapacitadoras;
	}

	/**
	 * @param totalCapacitadoras the totalCapacitadoras to set
	 */
	public void setTotalCapacitadoras(String totalCapacitadoras) {
		this.totalCapacitadoras = totalCapacitadoras;
	}

	/**
	 * @return the numeroUnidadesPopupUniDifNO
	 */
	public String getNumeroUnidadesPopupUniDifNO() {
		return numeroUnidadesPopupUniDifNO;
	}

	/**
	 * @param numeroUnidadesPopupUniDifNO the numeroUnidadesPopupUniDifNO to set
	 */
	public void setNumeroUnidadesPopupUniDifNO(String numeroUnidadesPopupUniDifNO) {
		this.numeroUnidadesPopupUniDifNO = numeroUnidadesPopupUniDifNO;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}