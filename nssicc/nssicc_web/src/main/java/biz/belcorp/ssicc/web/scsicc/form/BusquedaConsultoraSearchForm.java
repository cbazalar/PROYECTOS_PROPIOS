package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class BusquedaConsultoraSearchForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 12/02/2014
 */
public class BusquedaConsultoraSearchForm extends BaseSearchForm implements Serializable{
	private static final long serialVersionUID = 1L;

	private String codigoConsultora;
	
    private String apellidoPaterno;
	
    private String apellidoMaterno;
	
    private String nombre;
        
	
    public void inicializar()
    {
        this.codigoConsultora="";
                
        this.apellidoPaterno="";
        
        this.apellidoMaterno="";
        
        this.nombre="";
        
    }
    
    /**
	 * @return Returns the apellidoMaterno.
	 */
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	/**
	 * @param apellidoMaterno The apellidoMaterno to set.
	 */
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	/**
	 * @return Returns the apellidoPaterno.
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno The apellidoPaterno to set.
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return Returns the nombre.
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre The nombre to set.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
