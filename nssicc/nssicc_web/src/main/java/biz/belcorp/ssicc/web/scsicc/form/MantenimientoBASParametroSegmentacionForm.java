package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoBASParametroSegmentacionForm extends BaseSearchForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
    private String nroServidoresBatch;
    private String numeroColasFACProcesosGP2;
    private String numeroColasFACProcesosGP4;
    private String tamanioSegmentosGP2;
    private String tamanioSegmentosGP4;
    private String maximaCantSegmentosGP2 ;
    private String maximaCantSegmentosGP4 ;
    private String tamanioSubSegmentosGP2  ;
    private String tamanioSubSegmentosGP4 ;
    private String indicadorSegmentacion ;
    
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"  
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}


	/**
	 * @return the nroServidoresBatch
	 */
	public String getNroServidoresBatch() {
		return nroServidoresBatch;
	}


	/**
	 * @param nroServidoresBatch the nroServidoresBatch to set
	 * @struts.validator type = "required"  
	 */
	public void setNroServidoresBatch(String nroServidoresBatch) {
		this.nroServidoresBatch = nroServidoresBatch;
	}


	/**
	 * @return the numeroColasFACProcesosGP2
	 */
	public String getNumeroColasFACProcesosGP2() {
		return numeroColasFACProcesosGP2;
	}


	/**
	 * @param numeroColasFACProcesosGP2 the numeroColasFACProcesosGP2 to set
	 * @struts.validator type = "required"  
	 */
	public void setNumeroColasFACProcesosGP2(String numeroColasFACProcesosGP2) {
		this.numeroColasFACProcesosGP2 = numeroColasFACProcesosGP2;
	}


	/**
	 * @return the numeroColasFACProcesosGP4
	 */
	public String getNumeroColasFACProcesosGP4() {
		return numeroColasFACProcesosGP4;
	}


	/**
	 * @param numeroColasFACProcesosGP4 the numeroColasFACProcesosGP4 to set
	 * @struts.validator type = "required"  
	 */
	public void setNumeroColasFACProcesosGP4(String numeroColasFACProcesosGP4) {
		this.numeroColasFACProcesosGP4 = numeroColasFACProcesosGP4;
	}


	/**
	 * @return the tamanioSegmentosGP2
	 */
	public String getTamanioSegmentosGP2() {
		return tamanioSegmentosGP2;
	}


	/**
	 * @param tamanioSegmentosGP2 the tamanioSegmentosGP2 to set
	 * @struts.validator type = "required"  
	 */
	public void setTamanioSegmentosGP2(String tamanioSegmentosGP2) {
		this.tamanioSegmentosGP2 = tamanioSegmentosGP2;
	}


	/**
	 * @return the tamanioSegmentosGP4
	 */
	public String getTamanioSegmentosGP4() {
		return tamanioSegmentosGP4;
	}


	/**
	 * @param tamanioSegmentosGP4 the tamanioSegmentosGP4 to set
	 * @struts.validator type = "required"  
	 */
	public void setTamanioSegmentosGP4(String tamanioSegmentosGP4) {
		this.tamanioSegmentosGP4 = tamanioSegmentosGP4;
	}


	/**
	 * @return the maximaCantSegmentosGP2
	 */
	public String getMaximaCantSegmentosGP2() {
		return maximaCantSegmentosGP2;
	}


	/**
	 * @param maximaCantSegmentosGP2 the maximaCantSegmentosGP2 to set
	 * @struts.validator type = "required"  
	 */
	public void setMaximaCantSegmentosGP2(String maximaCantSegmentosGP2) {
		this.maximaCantSegmentosGP2 = maximaCantSegmentosGP2;
	}


	/**
	 * @return the maximaCantSegmentosGP4
	 */
	public String getMaximaCantSegmentosGP4() {
		return maximaCantSegmentosGP4;
	}


	/**
	 * @param maximaCantSegmentosGP4 the maximaCantSegmentosGP4 to set
	 * @struts.validator type = "required"  
	 */
	public void setMaximaCantSegmentosGP4(String maximaCantSegmentosGP4) {
		this.maximaCantSegmentosGP4 = maximaCantSegmentosGP4;
	}


	/**
	 * @return the tamanioSubSegmentosGP2
	 */
	public String getTamanioSubSegmentosGP2() {
		return tamanioSubSegmentosGP2;
	}


	/**
	 * @param tamanioSubSegmentosGP2 the tamanioSubSegmentosGP2 to set
	 * @struts.validator type = "required"  
	 */
	public void setTamanioSubSegmentosGP2(String tamanioSubSegmentosGP2) {
		this.tamanioSubSegmentosGP2 = tamanioSubSegmentosGP2;
	}


	/**
	 * @return the tamanioSubSegmentosGP4
	 */
	public String getTamanioSubSegmentosGP4() {
		return tamanioSubSegmentosGP4;
	}


	/**
	 * @param tamanioSubSegmentosGP4 the tamanioSubSegmentosGP4 to set
	 * @struts.validator type = "required"  
	 */
	public void setTamanioSubSegmentosGP4(String tamanioSubSegmentosGP4) {
		this.tamanioSubSegmentosGP4 = tamanioSubSegmentosGP4;
	}

	/**
	 * @return the indicadorSegmentacion
	 */
	public String getIndicadorSegmentacion() {
		return indicadorSegmentacion;
	}


	/**
	 * @param indicadorSegmentacion the indicadorSegmentacion to set
	 * @struts.validator type = "required"  
	 */
	public void setIndicadorSegmentacion(String indicadorSegmentacion) {
		this.indicadorSegmentacion = indicadorSegmentacion;
	}
}
