package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteSICDetalleUnidadesAtendidasFaltanteForm extends	BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String[] codigoRegion;
	
	private String[] codigoZona;

	private String codigoSap;

	private String descripcionCorta;

	private String fechaFacturacionInicio;

	private String codigoPeriodo;

	private String codigoVenta;

	private String codigoPais;
	
	private String tipoReporte;

	private String fechaFacturacionFin;
	
//	private FormFile clienteFile;
	
	private String codigoCliente;
	
	private boolean filtroImporte;
		
	private String tipoOferta;
	
	private String codigoEstadoCliente;
	
	private Date fechaFacturacionInicioDate;
	private Date fechaFacturacionFinDate;
	
	private boolean filtroOrdenCompra;

	/**
	 * @return Returns the tipoReporte.
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte The tipoReporte to set.
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return Returns the codigoSap.
	 */
	public String getCodigoSap() {
		return codigoSap;
	}

	/**
	 * @param codigoSap
	 *            The codigoSap to set.
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	/**
	 * @return Returns the descripcionCorta.
	 */
	public String getDescripcionCorta() {
		return descripcionCorta;
	}

	/**
	 * @param descripcionCorta
	 *            The descripcionCorta to set.
	 */
	public void setDescripcionCorta(String descripcionCorta) {
		this.descripcionCorta = descripcionCorta;
	}

	/**
	 * @return Returns the codigoVenta.
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta
	 *            The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 *            The codigoPeriodo to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the fechaFacturacionInicio.
	 */
	public String getFechaFacturacionInicio() {
		return fechaFacturacionInicio;
	}

	/**
	 * @param fechaFacturacionInicio
	 *            The fechaFacturacionInicio to set.
	 * @struts.validator type="date"
	 * @struts.validator-var name="datePatternStrict"
	 *                       value="${defaultDatePattern}"
	 */
	public void setFechaFacturacionInicio(String fechaFacturacionInicio) {
		this.fechaFacturacionInicio = fechaFacturacionInicio;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 *            The codigoRegion to set.
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return Returns the codigoZona.
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the fechaFacturacionFin
	 */
	public String getFechaFacturacionFin() {
		return fechaFacturacionFin;
	}

	/**
	 * @param fechaFacturacionFin the fechaFacturacionFin to set
	 */
	public void setFechaFacturacionFin(String fechaFacturacionFin) {
		this.fechaFacturacionFin = fechaFacturacionFin;
	}
	
	/**
	 * @return the clienteFile
	 */
//	public FormFile getClienteFile() {
//		return clienteFile;
//	}

	/**
	 * @param clienteFile the clienteFile to set
	 */
//	public void setClienteFile(FormFile clienteFile) {
//		this.clienteFile = clienteFile;
//	}
	
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
	 * @return the tipoOferta
	 */
	public String getTipoOferta() {
		return tipoOferta;
	}

	/**
	 * @param tipoOferta the tipoOferta to set
	 */
	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	/**
	 * @return the codigoEstadoCliente
	 */
	public String getCodigoEstadoCliente() {
		return codigoEstadoCliente;
	}

	/**
	 * @param codigoEstadoCliente the codigoEstadoCliente to set
	 */
	public void setCodigoEstadoCliente(String codigoEstadoCliente) {
		this.codigoEstadoCliente = codigoEstadoCliente;
	}

	public boolean isFiltroImporte() {
		return filtroImporte;
	}

	public void setFiltroImporte(boolean filtroImporte) {
		this.filtroImporte = filtroImporte;
	}

	/**
	 * @return the fechaFacturacionInicioDate
	 */
	public Date getFechaFacturacionInicioDate() {
		return fechaFacturacionInicioDate;
	}

	/**
	 * @param fechaFacturacionInicioDate the fechaFacturacionInicioDate to set
	 */
	public void setFechaFacturacionInicioDate(Date fechaFacturacionInicioDate) {
		this.fechaFacturacionInicioDate = fechaFacturacionInicioDate;
	}

	/**
	 * @return the fechaFacturacionFinDate
	 */
	public Date getFechaFacturacionFinDate() {
		return fechaFacturacionFinDate;
	}

	/**
	 * @param fechaFacturacionFinDate the fechaFacturacionFinDate to set
	 */
	public void setFechaFacturacionFinDate(Date fechaFacturacionFinDate) {
		this.fechaFacturacionFinDate = fechaFacturacionFinDate;
	}

	/**
	 * @return the filtroOrdenCompra
	 */
	public boolean isFiltroOrdenCompra() {
		return filtroOrdenCompra;
	}

	/**
	 * @param filtroOrdenCompra the filtroOrdenCompra to set
	 */
	public void setFiltroOrdenCompra(boolean filtroOrdenCompra) {
		this.filtroOrdenCompra = filtroOrdenCompra;
	}
}