/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.sisicc.ws.beans;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazResultado.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno </a>
 */
public class InterfazResultado implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7765225595778654240L;

	private int numeroRegistros;
    
    private String mensaje;    

    private boolean completado;
    
	private String nombreArchivoEntradaSalida;
	
	private String numeroLote;	

    /** Creates a new instance of InterfazResultado */
    public InterfazResultado() {
    }

    /**
     * Getter for property numeroRegistros.
     * 
     * @return Value of property numeroRegistros.
     */
    public int getNumeroRegistros() {
        return numeroRegistros;
    }

    /**
     * Setter for property numeroRegistros.
     * 
     * @param numeroRegistros
     *            New value of property numeroRegistros.
     */
    public void setNumeroRegistros(int numeroRegistros) {
        this.numeroRegistros = numeroRegistros;
    }

	public boolean isCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombreArchivoEntradaSalida() {
		return nombreArchivoEntradaSalida;
	}

	public void setNombreArchivoEntradaSalida(String nombreArchivoEntradaSalida) {
		this.nombreArchivoEntradaSalida = nombreArchivoEntradaSalida;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	 public String toString(){
			return new ToStringBuilder(this)
			.append("completado",this.completado)
			.append("mensaje",this.mensaje)
//			.append("nombreArchivoEntradaSalida",this.nombreArchivoEntradaSalida)
//			.append("numeroLote",this.numeroLote)
//			.append("numeroRegistros",this.numeroRegistros)		
			.toString();
			
		}

}
