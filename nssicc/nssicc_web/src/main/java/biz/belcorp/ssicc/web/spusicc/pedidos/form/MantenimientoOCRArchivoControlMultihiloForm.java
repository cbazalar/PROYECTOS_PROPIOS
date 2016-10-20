package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoOCRArchivoControlMultihiloForm extends BaseEditForm{
	
	private static final long serialVersionUID = -7699023453925552868L;
	
	private String oid;
	private String codigoPais;
	private String numeroLote;
	private String tipoDocumento;
	private String numeroRegistros;
	private String indicadorCarga;
	private String usuDigi;
	private String fechaDigi;
	private String idProcBatch;
	
	
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the numeroRegistros
	 */
	public String getNumeroRegistros() {
		return numeroRegistros;
	}
	/**
	 * @param numeroRegistros the numeroRegistros to set
	 */
	public void setNumeroRegistros(String numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	/**
	 * @return the indicadorCarga
	 */
	public String getIndicadorCarga() {
		return indicadorCarga;
	}
	/**
	 * @param indicadorCarga the indicadorCarga to set
	 */
	public void setIndicadorCarga(String indicadorCarga) {
		this.indicadorCarga = indicadorCarga;
	}
	/**
	 * @return the usuDigi
	 */
	public String getUsuDigi() {
		return usuDigi;
	}
	/**
	 * @param usuDigi the usuDigi to set
	 */
	public void setUsuDigi(String usuDigi) {
		this.usuDigi = usuDigi;
	}
	/**
	 * @return the fechaDigi
	 */
	public String getFechaDigi() {
		return fechaDigi;
	}
	/**
	 * @param fechaDigi the fechaDigi to set
	 */
	public void setFechaDigi(String fechaDigi) {
		this.fechaDigi = fechaDigi;
	}
	/**
	 * @return the idProcBatch
	 */
	public String getIdProcBatch() {
		return idProcBatch;
	}
	/**
	 * @param idProcBatch the idProcBatch to set
	 */
	public void setIdProcBatch(String idProcBatch) {
		this.idProcBatch = idProcBatch;
	}
}
