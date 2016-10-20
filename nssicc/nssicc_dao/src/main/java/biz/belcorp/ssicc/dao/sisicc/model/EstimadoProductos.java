/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.model;

/**
 * @author COMMACIAS
 *
 */
public class EstimadoProductos {

	private String compania;
	private String fechaProceso;
	private String lote;
	private String tipoPeriodo;
	private String periodo;
	private String codigoProducto;
	private String descripcion;
	private String unidades;
	private int codigoEstimado;
	private String codigoSAP;
	private int oidProducto;
	private int indicadorFP;
	private int procedencia;
	private int linea;
	private String flagFuePed;
	public String getFlagFuePed() {
		return flagFuePed;
	}
	public void setFlagFuePed(String flagFuePed) {
		this.flagFuePed = flagFuePed;
	}
	public String getCodSap() {
		return codSap;
	}
	public void setCodSap(String codSap) {
		this.codSap = codSap;
	}
	public String getDesSap() {
		return desSap;
	}
	public void setDesSap(String desSap) {
		this.desSap = desSap;
	}
	public String getOidProd() {
		return oidProd;
	}
	public void setOidProd(String oidProd) {
		this.oidProd = oidProd;
	}
	private String codSap;
	private String desSap;
	private String oidProd;
	
	public int getIndicadorFP() {
		return indicadorFP;
	}
	public void setIndicadorFP(int indicadorFP) {
		this.indicadorFP = indicadorFP;
	}
	public int getLinea() {
		return linea;
	}
	public void setLinea(int linea) {
		this.linea = linea;
	}
	public int getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(int procedencia) {
		this.procedencia = procedencia;
	}
	public int getOidProducto() {
		return oidProducto;
	}
	public void setOidProducto(int oidProducto) {
		this.oidProducto = oidProducto;
	}
	public String getCodigoSAP() {
		return codigoSAP;
	}
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getCompania() {
		return compania;
	}
	public void setCompania(String compania) {
		this.compania = compania;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getTipoPeriodo() {
		return tipoPeriodo;
	}
	public void setTipoPeriodo(String tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}
	public String getUnidades() {
		return unidades;
	}
	public void setUnidades(String unidades) {
		this.unidades = unidades.trim();
	}
	public int getCodigoEstimado() {
		return codigoEstimado;
	}
	public void setCodigoEstimado(int codigoEstimado) {
		this.codigoEstimado = codigoEstimado;
	}
}
