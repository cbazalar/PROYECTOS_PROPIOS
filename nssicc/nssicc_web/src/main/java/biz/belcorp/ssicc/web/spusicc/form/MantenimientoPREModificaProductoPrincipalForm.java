package biz.belcorp.ssicc.web.spusicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPREModificaProductoPrincipalForm extends BaseEditForm implements Serializable{

	private static final long serialVersionUID = -676111745037990693L;
	
	private String codigoPais;
	private String codigoVenta;
	private String numPagina;
	private String preCatalogo;
	private String preContable;
	private String facRepeticion;
	private String codSAP;
	private String valTextoBreve;
    private String indDigitable;
    private String indImprimible;
    private String impCosteEsta;
    private String numUnidEstim;
    private String impVenNetaEstim;
    private String tipoOferta;
    
    private String codSap;
    private String desSap;
    private String oidCatalogo;
    private String desCatalogo;
    private String oidTipoOferta;
    private String codTipoOferta;
    private String desTipoOferta;
	
    private String oidDetaOferta;
    private String numOferta;
    private String numGrupo;
    private String tipGrupo;
    private String oidOferta;
    private String oidFormaPago;
    private String estado;
    private String desEstrategia;
    private String oidTipoEstrategia;
    private String codCatalogo;
    private String oidGrupoOferta;
        
	private boolean modificar;
	
	public MantenimientoPREModificaProductoPrincipalForm(){
		this.modificar = false;
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
	 * @return the modificar
	 */
	public boolean isModificar() {
		return modificar;
	}

	/**
	 * @param modificar the modificar to set
	 */
	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	/**
	 * @return the codSap
	 */
	public String getCodSap() {
		return codSap;
	}

	/**
	 * @param codSap the codSap to set
	 */
	public void setCodSap(String codSap) {
		this.codSap = codSap;
	}

	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}

	/**
	 * @param oidCatalogo the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
	}

	/**
	 * @return the oidTipoOferta
	 */
	public String getOidTipoOferta() {
		return oidTipoOferta;
	}

	/**
	 * @param oidTipoOferta the oidTipoOferta to set
	 */
	public void setOidTipoOferta(String oidTipoOferta) {
		this.oidTipoOferta = oidTipoOferta;
	}

	/**
	 * @return the codTipoOferta
	 */
	public String getCodTipoOferta() {
		return codTipoOferta;
	}

	/**
	 * @param codTipoOferta the codTipoOferta to set
	 */
	public void setCodTipoOferta(String codTipoOferta) {
		this.codTipoOferta = codTipoOferta;
	}

	/**
	 * @return the desSap
	 */
	public String getDesSap() {
		return desSap;
	}

	/**
	 * @param desSap the desSap to set
	 */
	public void setDesSap(String desSap) {
		this.desSap = desSap;
	}

	/**
	 * @return the desCatalogo
	 */
	public String getDesCatalogo() {
		return desCatalogo;
	}

	/**
	 * @param desCatalogo the desCatalogo to set
	 */
	public void setDesCatalogo(String desCatalogo) {
		this.desCatalogo = desCatalogo;
	}

	/**
	 * @return the desTipoOferta
	 */
	public String getDesTipoOferta() {
		return desTipoOferta;
	}

	/**
	 * @param desTipoOferta the desTipoOferta to set
	 */
	public void setDesTipoOferta(String desTipoOferta) {
		this.desTipoOferta = desTipoOferta;
	}

	/**
	 * @return the oidDetaOferta
	 */
	public String getOidDetaOferta() {
		return oidDetaOferta;
	}

	/**
	 * @param oidDetaOferta the oidDetaOferta to set
	 */
	public void setOidDetaOferta(String oidDetaOferta) {
		this.oidDetaOferta = oidDetaOferta;
	}

	/**
	 * @return the numOferta
	 */
	public String getNumOferta() {
		return numOferta;
	}

	/**
	 * @param numOferta the numOferta to set
	 */
	public void setNumOferta(String numOferta) {
		this.numOferta = numOferta;
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
	 * @return the tipGrupo
	 */
	public String getTipGrupo() {
		return tipGrupo;
	}

	/**
	 * @param tipGrupo the tipGrupo to set
	 */
	public void setTipGrupo(String tipGrupo) {
		this.tipGrupo = tipGrupo;
	}

	/**
	 * @return the oidOferta
	 */
	public String getOidOferta() {
		return oidOferta;
	}

	/**
	 * @param oidOferta the oidOferta to set
	 */
	public void setOidOferta(String oidOferta) {
		this.oidOferta = oidOferta;
	}

	/**
	 * @return the oidFormaPago
	 */
	public String getOidFormaPago() {
		return oidFormaPago;
	}

	/**
	 * @param oidFormaPago the oidFormaPago to set
	 */
	public void setOidFormaPago(String oidFormaPago) {
		this.oidFormaPago = oidFormaPago;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the desEstrategia
	 */
	public String getDesEstrategia() {
		return desEstrategia;
	}

	/**
	 * @param desEstrategia the desEstrategia to set
	 */
	public void setDesEstrategia(String desEstrategia) {
		this.desEstrategia = desEstrategia;
	}

	/**
	 * @return the oidTipoEstrategia
	 */
	public String getOidTipoEstrategia() {
		return oidTipoEstrategia;
	}

	/**
	 * @param oidTipoEstrategia the oidTipoEstrategia to set
	 */
	public void setOidTipoEstrategia(String oidTipoEstrategia) {
		this.oidTipoEstrategia = oidTipoEstrategia;
	}

	/**
	 * @return the codCatalogo
	 */
	public String getCodCatalogo() {
		return codCatalogo;
	}

	/**
	 * @param codCatalogo the codCatalogo to set
	 */
	public void setCodCatalogo(String codCatalogo) {
		this.codCatalogo = codCatalogo;
	}

	/**
	 * @return the oidGrupoOferta
	 */
	public String getOidGrupoOferta() {
		return oidGrupoOferta;
	}

	/**
	 * @param oidGrupoOferta the oidGrupoOferta to set
	 */
	public void setOidGrupoOferta(String oidGrupoOferta) {
		this.oidGrupoOferta = oidGrupoOferta;
	}
}