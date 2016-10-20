package biz.belcorp.ssicc.web.scsicc.form;


import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;


/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 * 
 * @struts.form name = "consultaZONNovedadesHistoricoForm"
 */
public class ConsultaZONNovedadesHistoricoForm extends BaseSearchForm {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
    private String codigoOperacion;
    private String codigoRegion;
    private String codigoZona;
    private String estado;
    private String codigoCargo;
    private String codigoClienteBuscar;
    private String oidIdioma;
    private String codigoRol;
	private String codigoPerfil;
    
    //Popup Buscar Consultora
    private String nombreCliente;
	private String docCliente;
	private String numeroDocIdentidadBuscar;
    
	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

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
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {	

		this.codigoPais = "";

	    this.codigoOperacion="";

	    this.codigoRegion=this.codigoZona="";
	    
	    this.estado = "";
	    
	    this.codigoClienteBuscar = "";
	    
	    this.nombreCliente = "";
	    
	    this.docCliente = "";
	    
	    this.numeroDocIdentidadBuscar = "";
	    
	    this.codigoCargo = "";
	    
	    this.codigoRol = "";
		
		this.codigoPerfil = "";
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 * 
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 * 
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the codigoClienteBuscar
	 */
	public String getCodigoClienteBuscar() {
		return codigoClienteBuscar;
	}

	/**
	 * @param codigoClienteBuscar the codigoClienteBuscar to set
	 * @struts.validator type = "required"
	 */
	public void setCodigoClienteBuscar(String codigoClienteBuscar) {
		this.codigoClienteBuscar = codigoClienteBuscar;
	}

	/**
	 * @return the oidIdioma
	 */
	public String getOidIdioma() {
		return oidIdioma;
	}

	/**
	 * @param oidIdioma the oidIdioma to set
	 */
	public void setOidIdioma(String oidIdioma) {
		this.oidIdioma = oidIdioma;
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
	 * @return the docCliente
	 */
	public String getDocCliente() {
		return docCliente;
	}

	/**
	 * @param docCliente the docCliente to set
	 */
	public void setDocCliente(String docCliente) {
		this.docCliente = docCliente;
	}
	
	/**
	 * @return the numeroDocIdentidadBuscar
	 */
	public String getNumeroDocIdentidadBuscar() {
		return numeroDocIdentidadBuscar;
	}
	
	/**
	 * @param numeroDocIdentidadBuscar the numeroDocIdentidadBuscar to set
	 */
	public void setNumeroDocIdentidadBuscar(String numeroDocIdentidadBuscar) {
		this.numeroDocIdentidadBuscar = numeroDocIdentidadBuscar;
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
	 * @return the codigoCargo
	 */
	public String getCodigoCargo() {
		return codigoCargo;
	}
	
	/**
	 * @param codigoCargo the codigoCargo to set
	 */
	public void setCodigoCargo(String codigoCargo) {
		this.codigoCargo = codigoCargo;
	}
	
	/**
	 * @return the codigoRol
	 */
	public String getCodigoRol() {
		return codigoRol;
	}
	
	/**
	 * @param codigoRol the codigoRol to set
	 */
	public void setCodigoRol(String codigoRol) {
		this.codigoRol = codigoRol;
	}
	
	/**
	 * @return the codigoPerfil
	 */
	public String getCodigoPerfil() {
		return codigoPerfil;
	}
	
	/**
	 * @param codigoPerfil the codigoPerfil to set
	 */
	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	
	
}