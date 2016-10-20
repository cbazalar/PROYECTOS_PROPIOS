package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class Opcion extends AuditableBaseObject implements Serializable {

		
	private static final long serialVersionUID = -901918423001356509L;
	
	protected String codigoOpcion;
	protected String descripcion;
	protected String estadoOpcion;
	
	
	
	 public String getCodigoOpcion() {
		return codigoOpcion;
	}

	public void setCodigoOpcion(String codigoOpcion) {
		this.codigoOpcion = codigoOpcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoOpcion() {
		return estadoOpcion;
	}

	public void setEstadoOpcion(String estadoOpcion) {
		this.estadoOpcion = estadoOpcion;
	}

	/**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof Rol)) {
            return false;
        }
     
        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
    		return 0;    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Opcion [codigoOpcion=" + codigoOpcion + ", descripcion="
				+ descripcion + ", estadoOpcion=" + estadoOpcion + "]";
	}
}
