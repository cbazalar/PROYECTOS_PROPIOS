package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoPRECambioCodigoVentaModificaCUVForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3389471295875359918L;
	private String codigoPais;
	private String codigoVenta;
	private String numPagina;
	private String preCatalogo;
	private String preContable;
	private String facRepeticion;

	private String indicadorCodigoVenta;
	private String indicadorNumeroPagina;
	private String indicadorPrecioCatalogo;
	private String indicadorPrecioContable;
	private String indicadorFactorRepeticion;
	private Integer indicadorCUVFacturado;
	
	private String codSAP;
	private String valTextoBreve;
    private String indDigitable;
    private String indImprimible;
    private String impCosteEsta;
    private String numUnidEstim;
    private String impVenNetaEstim;
    private String tipoOferta;
    
    private String indicadorValTextoBreve;
    private String indicadorIndDigitable;
    private String indicadorIndImprimible;
    private String indicadorImpCosteEsta;
    private String indicadorNumUnidEstim;
    private String indicadorImpVenNetaEstim;
    private String indicadorTipoOferta;
	
	
	//seccion grupos
		private String numGrupo;
		private String tipoGrupo;
		private String indicadorCuadre;
		private String indCuadre;
		private String oid;
		private String descripcion;
		private String factorCuadre;
		private String indicadorNumeroGrupo;
		private String indicadorTipoGrupo;
		private String indicadorIndicadorCuadre;
		private String indicadorFactorCuadre;
		
		//seccion condiciones
		private String tipoCuadre;
		private String factorCuadreSecc;
		private String codigoCatalogo;
		private String paginaInicial;
		private String codigoProducto;
		private String descripcionProducto;
		private String tipoRango;
		private String paginaFinal;
		private String indicadorExclusion;
		
		//indicador para las secciones
		private String indicadorSeccionGrupos;
		private String indicadorSeccionCondiciones;
		
		private String grupoAnterior;
		
	
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
	 * @return the numPagina
	 */
	public String getNumPagina() {
		return numPagina;
	}
	/**
	 * @param numPagina the numPagina to set
	 */
	public void setNumPagina(String numPagina) {
		this.numPagina = numPagina;
	}
	/**
	 * @return the preCatalogo
	 */
	public String getPreCatalogo() {
		return preCatalogo;
	}
	/**
	 * @param preCatalogo the preCatalogo to set
	 */
	public void setPreCatalogo(String preCatalogo) {
		this.preCatalogo = preCatalogo;
	}
	/**
	 * @return the preContable
	 */
	public String getPreContable() {
		return preContable;
	}
	/**
	 * @param preContable the preContable to set
	 */
	public void setPreContable(String preContable) {
		this.preContable = preContable;
	}
	/**
	 * @return the facRepeticion
	 */
	public String getFacRepeticion() {
		return facRepeticion;
	}
	/**
	 * @param facRepeticion the facRepeticion to set
	 */
	public void setFacRepeticion(String facRepeticion) {
		this.facRepeticion = facRepeticion;
	}
	
	/**
	 * @return the indicadorCodigoVenta
	 */
	public String getIndicadorCodigoVenta() {
		return indicadorCodigoVenta;
	}
	/**
	 * @param indicadorCodigoVenta the indicadorCodigoVenta to set
	 */
	public void setIndicadorCodigoVenta(String indicadorCodigoVenta) {
		this.indicadorCodigoVenta = indicadorCodigoVenta;
	}
	/**
	 * @return the indicadorNumeroPagina
	 */
	public String getIndicadorNumeroPagina() {
		return indicadorNumeroPagina;
	}
	/**
	 * @param indicadorNumeroPagina the indicadorNumeroPagina to set
	 */
	public void setIndicadorNumeroPagina(String indicadorNumeroPagina) {
		this.indicadorNumeroPagina = indicadorNumeroPagina;
	}
	/**
	 * @return the indicadorPrecioCatalogo
	 */
	public String getIndicadorPrecioCatalogo() {
		return indicadorPrecioCatalogo;
	}
	/**
	 * @param indicadorPrecioCatalogo the indicadorPrecioCatalogo to set
	 */
	public void setIndicadorPrecioCatalogo(String indicadorPrecioCatalogo) {
		this.indicadorPrecioCatalogo = indicadorPrecioCatalogo;
	}
	/**
	 * @return the indicadorPrecioContable
	 */
	public String getIndicadorPrecioContable() {
		return indicadorPrecioContable;
	}
	/**
	 * @param indicadorPrecioContable the indicadorPrecioContable to set
	 */
	public void setIndicadorPrecioContable(String indicadorPrecioContable) {
		this.indicadorPrecioContable = indicadorPrecioContable;
	}
	/**
	 * @return the indicadorFactorRepeticion
	 */
	public String getIndicadorFactorRepeticion() {
		return indicadorFactorRepeticion;
	}
	/**
	 * @param indicadorFactorRepeticion the indicadorFactorRepeticion to set
	 */
	public void setIndicadorFactorRepeticion(String indicadorFactorRepeticion) {
		this.indicadorFactorRepeticion = indicadorFactorRepeticion;
	}
	
	/**
	 * @return the indicadorCUVFacturado
	 */
	public Integer getIndicadorCUVFacturado() {
		return indicadorCUVFacturado;
	}
	/**
	 * @param indicadorCUVFacturado the indicadorCUVFacturado to set
	 */
	public void setIndicadorCUVFacturado(Integer indicadorCUVFacturado) {
		this.indicadorCUVFacturado = indicadorCUVFacturado;
	}
	/**
	 * @return the numGrupo
	 */
	public String getNumGrupo() {
		return numGrupo;
	}
	/**
	 * @param numGrupo the numGrupo to set
	 */
	public void setNumGrupo(String numGrupo) {
		this.numGrupo = numGrupo;
	}
	/**
	 * @return the tipoGrupo
	 */
	public String getTipoGrupo() {
		return tipoGrupo;
	}
	/**
	 * @param tipoGrupo the tipoGrupo to set
	 */
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	/**
	 * @return the indicadorCuadre
	 */
	public String getIndicadorCuadre() {
		return indicadorCuadre;
	}
	/**
	 * @param indicadorCuadre the indicadorCuadre to set
	 */
	public void setIndicadorCuadre(String indicadorCuadre) {
		this.indicadorCuadre = indicadorCuadre;
	}
	/**
	 * @return the indCuadre
	 */
	public String getIndCuadre() {
		return indCuadre;
	}
	/**
	 * @param indCuadre the indCuadre to set
	 */
	public void setIndCuadre(String indCuadre) {
		this.indCuadre = indCuadre;
	}
	/**
	 * @return the oid
	 */
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the factorCuadre
	 */
	public String getFactorCuadre() {
		return factorCuadre;
	}
	/**
	 * @param factorCuadre the factorCuadre to set
	 */
	public void setFactorCuadre(String factorCuadre) {
		this.factorCuadre = factorCuadre;
	}
	/**
	 * @return the indicadorNumeroGrupo
	 */
	public String getIndicadorNumeroGrupo() {
		return indicadorNumeroGrupo;
	}
	/**
	 * @param indicadorNumeroGrupo the indicadorNumeroGrupo to set
	 */
	public void setIndicadorNumeroGrupo(String indicadorNumeroGrupo) {
		this.indicadorNumeroGrupo = indicadorNumeroGrupo;
	}
	/**
	 * @return the indicadorTipoGrupo
	 */
	public String getIndicadorTipoGrupo() {
		return indicadorTipoGrupo;
	}
	/**
	 * @param indicadorTipoGrupo the indicadorTipoGrupo to set
	 */
	public void setIndicadorTipoGrupo(String indicadorTipoGrupo) {
		this.indicadorTipoGrupo = indicadorTipoGrupo;
	}
	/**
	 * @return the indicadorIndicadorCuadre
	 */
	public String getIndicadorIndicadorCuadre() {
		return indicadorIndicadorCuadre;
	}
	/**
	 * @param indicadorIndicadorCuadre the indicadorIndicadorCuadre to set
	 */
	public void setIndicadorIndicadorCuadre(String indicadorIndicadorCuadre) {
		this.indicadorIndicadorCuadre = indicadorIndicadorCuadre;
	}
	/**
	 * @return the indicadorFactorCuadre
	 */
	public String getIndicadorFactorCuadre() {
		return indicadorFactorCuadre;
	}
	/**
	 * @param indicadorFactorCuadre the indicadorFactorCuadre to set
	 */
	public void setIndicadorFactorCuadre(String indicadorFactorCuadre) {
		this.indicadorFactorCuadre = indicadorFactorCuadre;
	}
	/**
	 * @return the tipoCuadre
	 */
	public String getTipoCuadre() {
		return tipoCuadre;
	}
	/**
	 * @param tipoCuadre the tipoCuadre to set
	 */
	public void setTipoCuadre(String tipoCuadre) {
		this.tipoCuadre = tipoCuadre;
	}
	/**
	 * @return the factorCuadreSecc
	 */
	public String getFactorCuadreSecc() {
		return factorCuadreSecc;
	}
	/**
	 * @param factorCuadreSecc the factorCuadreSecc to set
	 */
	public void setFactorCuadreSecc(String factorCuadreSecc) {
		this.factorCuadreSecc = factorCuadreSecc;
	}
	/**
	 * @return the codigoCatalogo
	 */
	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}
	/**
	 * @param codigoCatalogo the codigoCatalogo to set
	 */
	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}
	/**
	 * @return the paginaInicial
	 */
	public String getPaginaInicial() {
		return paginaInicial;
	}
	/**
	 * @param paginaInicial the paginaInicial to set
	 */
	public void setPaginaInicial(String paginaInicial) {
		this.paginaInicial = paginaInicial;
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
	 * @return the tipoRango
	 */
	public String getTipoRango() {
		return tipoRango;
	}
	/**
	 * @param tipoRango the tipoRango to set
	 */
	public void setTipoRango(String tipoRango) {
		this.tipoRango = tipoRango;
	}
	/**
	 * @return the paginaFinal
	 */
	public String getPaginaFinal() {
		return paginaFinal;
	}
	/**
	 * @param paginaFinal the paginaFinal to set
	 */
	public void setPaginaFinal(String paginaFinal) {
		this.paginaFinal = paginaFinal;
	}
	/**
	 * @return the indicadorExclusion
	 */
	public String getIndicadorExclusion() {
		return indicadorExclusion;
	}
	/**
	 * @param indicadorExclusion the indicadorExclusion to set
	 */
	public void setIndicadorExclusion(String indicadorExclusion) {
		this.indicadorExclusion = indicadorExclusion;
	}
	/**
	 * @return the indicadorSeccionGrupos
	 */
	public String getIndicadorSeccionGrupos() {
		return indicadorSeccionGrupos;
	}
	/**
	 * @param indicadorSeccionGrupos the indicadorSeccionGrupos to set
	 */
	public void setIndicadorSeccionGrupos(String indicadorSeccionGrupos) {
		this.indicadorSeccionGrupos = indicadorSeccionGrupos;
	}
	/**
	 * @return the indicadorSeccionCondiciones
	 */
	public String getIndicadorSeccionCondiciones() {
		return indicadorSeccionCondiciones;
	}
	/**
	 * @param indicadorSeccionCondiciones the indicadorSeccionCondiciones to set
	 */
	public void setIndicadorSeccionCondiciones(String indicadorSeccionCondiciones) {
		this.indicadorSeccionCondiciones = indicadorSeccionCondiciones;
	}
	/**
	 * @return the codSAP
	 */
	public String getCodSAP() {
		return codSAP;
	}
	/**
	 * @param codSAP the codSAP to set
	 */
	public void setCodSAP(String codSAP) {
		this.codSAP = codSAP;
	}
	/**
	 * @return the valTextoBreve
	 */
	public String getValTextoBreve() {
		return valTextoBreve;
	}
	/**
	 * @param valTextoBreve the valTextoBreve to set
	 */
	public void setValTextoBreve(String valTextoBreve) {
		this.valTextoBreve = valTextoBreve;
	}
	/**
	 * @return the indDigitable
	 */
	public String getIndDigitable() {
		return indDigitable;
	}
	/**
	 * @param indDigitable the indDigitable to set
	 */
	public void setIndDigitable(String indDigitable) {
		this.indDigitable = indDigitable;
	}
	/**
	 * @return the indImprimible
	 */
	public String getIndImprimible() {
		return indImprimible;
	}
	/**
	 * @param indImprimible the indImprimible to set
	 */
	public void setIndImprimible(String indImprimible) {
		this.indImprimible = indImprimible;
	}
	/**
	 * @return the impCosteEsta
	 */
	public String getImpCosteEsta() {
		return impCosteEsta;
	}
	/**
	 * @param impCosteEsta the impCosteEsta to set
	 */
	public void setImpCosteEsta(String impCosteEsta) {
		this.impCosteEsta = impCosteEsta;
	}
	/**
	 * @return the numUnidEstim
	 */
	public String getNumUnidEstim() {
		return numUnidEstim;
	}
	/**
	 * @param numUnidEstim the numUnidEstim to set
	 */
	public void setNumUnidEstim(String numUnidEstim) {
		this.numUnidEstim = numUnidEstim;
	}
	/**
	 * @return the impVenNetaEstim
	 */
	public String getImpVenNetaEstim() {
		return impVenNetaEstim;
	}
	/**
	 * @param impVenNetaEstim the impVenNetaEstim to set
	 */
	public void setImpVenNetaEstim(String impVenNetaEstim) {
		this.impVenNetaEstim = impVenNetaEstim;
	}
	/**
	 * @return the indicadorValTextoBreve
	 */
	public String getIndicadorValTextoBreve() {
		return indicadorValTextoBreve;
	}
	/**
	 * @param indicadorValTextoBreve the indicadorValTextoBreve to set
	 */
	public void setIndicadorValTextoBreve(String indicadorValTextoBreve) {
		this.indicadorValTextoBreve = indicadorValTextoBreve;
	}
	/**
	 * @return the indicadorIndDigitable
	 */
	public String getIndicadorIndDigitable() {
		return indicadorIndDigitable;
	}
	/**
	 * @param indicadorIndDigitable the indicadorIndDigitable to set
	 */
	public void setIndicadorIndDigitable(String indicadorIndDigitable) {
		this.indicadorIndDigitable = indicadorIndDigitable;
	}
	/**
	 * @return the indicadorIndImprimible
	 */
	public String getIndicadorIndImprimible() {
		return indicadorIndImprimible;
	}
	/**
	 * @param indicadorIndImprimible the indicadorIndImprimible to set
	 */
	public void setIndicadorIndImprimible(String indicadorIndImprimible) {
		this.indicadorIndImprimible = indicadorIndImprimible;
	}
	/**
	 * @return the indicadorImpCosteEsta
	 */
	public String getIndicadorImpCosteEsta() {
		return indicadorImpCosteEsta;
	}
	/**
	 * @param indicadorImpCosteEsta the indicadorImpCosteEsta to set
	 */
	public void setIndicadorImpCosteEsta(String indicadorImpCosteEsta) {
		this.indicadorImpCosteEsta = indicadorImpCosteEsta;
	}
	/**
	 * @return the indicadorNumUnidEstim
	 */
	public String getIndicadorNumUnidEstim() {
		return indicadorNumUnidEstim;
	}
	/**
	 * @param indicadorNumUnidEstim the indicadorNumUnidEstim to set
	 */
	public void setIndicadorNumUnidEstim(String indicadorNumUnidEstim) {
		this.indicadorNumUnidEstim = indicadorNumUnidEstim;
	}
	/**
	 * @return the indicadorImpVenNetaEstim
	 */
	public String getIndicadorImpVenNetaEstim() {
		return indicadorImpVenNetaEstim;
	}
	/**
	 * @param indicadorImpVenNetaEstim the indicadorImpVenNetaEstim to set
	 */
	public void setIndicadorImpVenNetaEstim(String indicadorImpVenNetaEstim) {
		this.indicadorImpVenNetaEstim = indicadorImpVenNetaEstim;
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
	 * @return the indicadorTipoOferta
	 */
	public String getIndicadorTipoOferta() {
		return indicadorTipoOferta;
	}
	/**
	 * @param indicadorTipoOferta the indicadorTipoOferta to set
	 */
	public void setIndicadorTipoOferta(String indicadorTipoOferta) {
		this.indicadorTipoOferta = indicadorTipoOferta;
	}
	/**
	 * @return the grupoAnterior
	 */
	public String getGrupoAnterior() {
		return grupoAnterior;
	}
	/**
	 * @param grupoAnterior the grupoAnterior to set
	 */
	public void setGrupoAnterior(String grupoAnterior) {
		this.grupoAnterior = grupoAnterior;
	}
	
	

}
