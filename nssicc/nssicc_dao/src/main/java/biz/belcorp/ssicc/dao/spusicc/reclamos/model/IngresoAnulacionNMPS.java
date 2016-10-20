package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextjcairampoma@belcorp.biz
 * 
 */
public class IngresoAnulacionNMPS extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String num_recl;
	private String num_doc;
	private String cod_clie;
	private String cod_usua;
	private String fec_ingr;
	private String cod_pais;
	private String cod_est_proc;
	private String cod_error;
   

	public boolean equals(Object o) {
		
		return false;
	}

	public int hashCode() {
		
		return 0;
	}

	public String toString() {
		
		return null;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * @return the num_recl
	 */
	public String getNum_recl() {
		return num_recl;
	}

	/**
	 * @param num_recl the num_recl to set
	 */
	public void setNum_recl(String num_recl) {
		this.num_recl = num_recl;
	}

	/**
	 * @return the num_doc
	 */
	public String getNum_doc() {
		return num_doc;
	}

	/**
	 * @param num_doc the num_doc to set
	 */
	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}

	/**
	 * @return the cod_clie
	 */
	public String getCod_clie() {
		return cod_clie;
	}

	/**
	 * @param cod_clie the cod_clie to set
	 */
	public void setCod_clie(String cod_clie) {
		this.cod_clie = cod_clie;
	}

	/**
	 * @return the cod_usua
	 */
	public String getCod_usua() {
		return cod_usua;
	}

	/**
	 * @param cod_usua the cod_usua to set
	 */
	public void setCod_usua(String cod_usua) {
		this.cod_usua = cod_usua;
	}

	/**
	 * @return the fec_ingr
	 */
	public String getFec_ingr() {
		return fec_ingr;
	}

	/**
	 * @param fec_ingr the fec_ingr to set
	 */
	public void setFec_ingr(String fec_ingr) {
		this.fec_ingr = fec_ingr;
	}

	/**
	 * @return the cod_pais
	 */
	public String getCod_pais() {
		return cod_pais;
	}

	/**
	 * @param cod_pais the cod_pais to set
	 */
	public void setCod_pais(String cod_pais) {
		this.cod_pais = cod_pais;
	}

	/**
	 * @return the cod_est_proc
	 */
	public String getCod_est_proc() {
		return cod_est_proc;
	}

	/**
	 * @param cod_est_proc the cod_est_proc to set
	 */
	public void setCod_est_proc(String cod_est_proc) {
		this.cod_est_proc = cod_est_proc;
	}

	/**
	 * @return the cod_error
	 */
	public String getCod_error() {
		return cod_error;
	}

	/**
	 * @param cod_error the cod_error to set
	 */
	public void setCod_error(String cod_error) {
		this.cod_error = cod_error;
	}




}
