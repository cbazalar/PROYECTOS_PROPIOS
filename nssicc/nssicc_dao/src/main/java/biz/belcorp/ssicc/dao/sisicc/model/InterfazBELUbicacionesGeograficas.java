/**
 * Created on 17-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;


/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazBELUbicacionesGeograficas.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazBELUbicacionesGeograficas implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7941740408642990877L;
	private String codigoUbicacionGeografica;
    private String codigoUbicacionGeograficaPostal;
    private String descCompletaDepartamento;
    private String descCompletaProvincia;
    private String descCompletaDistrito;
    private String descAbreviadaDepartamento;
    private String descAbreviadaProvincia;
    private String descAbreviadaDistrito;

    public String getCodigoUbicacionGeografica() {
        return codigoUbicacionGeografica;
    }
    public void setCodigoUbicacionGeografica(String codigoUbicacionGeografica) {
        this.codigoUbicacionGeografica = codigoUbicacionGeografica;
    }
    public String getCodigoUbicacionGeograficaPostal() {
        return codigoUbicacionGeograficaPostal;
    }
    public void setCodigoUbicacionGeograficaPostal(
            String codigoUbicacionGeograficaPostal) {
        this.codigoUbicacionGeograficaPostal = codigoUbicacionGeograficaPostal;
    }
    public String getDescAbreviadaDepartamento() {
        return descAbreviadaDepartamento;
    }
    public void setDescAbreviadaDepartamento(String descAbreviadaDepartamento) {
        this.descAbreviadaDepartamento = descAbreviadaDepartamento;
    }
    public String getDescAbreviadaDistrito() {
        return descAbreviadaDistrito;
    }
    public void setDescAbreviadaDistrito(String descAbreviadaDistrito) {
        this.descAbreviadaDistrito = descAbreviadaDistrito;
    }
    public String getDescAbreviadaProvincia() {
        return descAbreviadaProvincia;
    }
    public void setDescAbreviadaProvincia(String descAbreviadaProvincia) {
        this.descAbreviadaProvincia = descAbreviadaProvincia;
    }
    public String getDescCompletaDepartamento() {
        return descCompletaDepartamento;
    }
    public void setDescCompletaDepartamento(String descCompletaDepartamento) {
        this.descCompletaDepartamento = descCompletaDepartamento;
    }
    public String getDescCompletaDistrito() {
        return descCompletaDistrito;
    }
    public void setDescCompletaDistrito(String descCompletaDistrito) {
        this.descCompletaDistrito = descCompletaDistrito;
    }
    public String getDescCompletaProvincia() {
        return descCompletaProvincia;
    }
    public void setDescCompletaProvincia(String descCompletaProvincia) {
        this.descCompletaProvincia = descCompletaProvincia;
    }
    
    
    
    
}
