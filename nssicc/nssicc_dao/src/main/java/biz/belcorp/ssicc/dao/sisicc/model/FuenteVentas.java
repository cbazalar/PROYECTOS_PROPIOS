/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="Sistema.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class FuenteVentas extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9040983541411127659L;

	private long codigoFuenteVentas; // COR_VEPR

    private String codigoPais; // PAIS_COD_PAIS

    private String codigoSociedad; // COD_SOCI

    private String codigoAlmacen; // COD_ALMA

    private String codigoRegion; // COD_REGI
    
    private String codigoMarca;	//COD_MARC

    private String codigoCanal; // COD_CANA

    private String codigoZona; // COD_ZONA

    private String codigoPeriodo; // COD_PERI

    private long actividadesIniciales; // NUM_ACIN;

    private long ingresos; // NUM_INGR;

    private long reingresos; // NUM_REIN;

    private long egresos; // NUM_EGPU;

    private double porcentajeActividad; // POR_ACTI;

    private double ppu; // VAL_PPU;

    private double pup; // VAL_PUP;

    private long numeroClientes; // NUM_CLIE;

    private String estado; // EST_VEPR;

    private String codigoAnio; // 2005, 2006,..

    private String codigoRangoPeriodo; // 01->01-06 02->07-12 ....

    private String codigoPeriodoAnio; // 01, 02, 03 ...

    private int secuencia; // del 1 al 6

    private Date fechaFinPeriodo; // Fecha en la que finaliza el perodo.

    private String estadoAbierto = "S"; // S--> Se puede modificar, N--> No se
                                        // puede modificar

    // Ini efernandezo
    
    private double actividadLider;
    
    private long numeroConsultoraCliPrivilege;

     private long numeroClientesInsPrivilege; 
     
     private long numeroClientesTrsPrivelege; 

     private long retencionSdoPeriodo; 
     
     private long retencionTerPeriodo; 
     
     private long retencionCuaPeriodo; 
     
     private long retencionActivas; 
     
     private double porcentajeRotacion;
     
     private long posiblesEgresos; 
     
     private long retencionPosEgresos;     

     // Fin efernandezo        
     
     
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof FuenteVentas)) {
            return false;
        }
        FuenteVentas rhs = (FuenteVentas) object;
        return new EqualsBuilder().append(this.egresos, rhs.egresos).append(
                this.secuencia, rhs.secuencia).append(this.codigoPeriodoAnio,
                rhs.codigoPeriodoAnio).append(this.pup, rhs.pup).append(
                this.codigoSociedad, rhs.codigoSociedad).append(this.auditInfo,
                rhs.auditInfo).append(this.ingresos, rhs.ingresos).append(
                this.porcentajeActividad, rhs.porcentajeActividad).append(
                this.fechaFinPeriodo, rhs.fechaFinPeriodo).append(
                this.codigoPais, rhs.codigoPais).append(
                this.actividadesIniciales, rhs.actividadesIniciales).append(
                this.reingresos, rhs.reingresos).append(this.codigoRegion,
                rhs.codigoRegion).append(this.codigoFuenteVentas,
                rhs.codigoFuenteVentas).append(this.estadoAbierto,
                rhs.estadoAbierto).append(this.codigoRangoPeriodo,
                rhs.codigoRangoPeriodo).append(this.estado, rhs.estado).append(
                this.codigoAlmacen, rhs.codigoAlmacen).append(this.codigoAnio,
                rhs.codigoAnio).append(this.numeroClientes, rhs.numeroClientes)
                .append(this.ppu, rhs.ppu).append(this.codigoZona,
                        rhs.codigoZona).append(this.codigoCanal,
                        rhs.codigoCanal).append(this.codigoPeriodo,
                        rhs.codigoPeriodo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1542432755, 1102820585).append(this.egresos)
                .append(this.secuencia).append(this.codigoPeriodoAnio).append(
                        this.pup).append(this.codigoSociedad).append(
                        this.auditInfo).append(this.ingresos).append(
                        this.porcentajeActividad).append(this.fechaFinPeriodo)
                .append(this.codigoPais).append(this.actividadesIniciales)
                .append(this.reingresos).append(this.codigoRegion).append(
                        this.codigoFuenteVentas).append(this.estadoAbierto)
                .append(this.codigoRangoPeriodo).append(this.estado).append(
                        this.codigoAlmacen).append(this.codigoAnio).append(
                        this.numeroClientes).append(this.ppu).append(
                        this.codigoZona).append(this.codigoCanal).append(
                        this.codigoPeriodo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("auditInfo", this.auditInfo)
                .toString();
    }

    public long getActividadesIniciales() {
        return actividadesIniciales;
    }

    public void setActividadesIniciales(long actividadesIniciales) {
        this.actividadesIniciales = actividadesIniciales;
    }

    public String getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(String codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getCodigoAnio() {
        return codigoAnio;
    }

    public void setCodigoAnio(String codigoAnio) {
        this.codigoAnio = codigoAnio;
    }

    public String getCodigoCanal() {
        return codigoCanal;
    }

    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

    public long getCodigoFuenteVentas() {
        return codigoFuenteVentas;
    }

    public void setCodigoFuenteVentas(long codigoFuenteVentas) {
        this.codigoFuenteVentas = codigoFuenteVentas;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(String codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public String getCodigoPeriodoAnio() {
        return codigoPeriodoAnio;
    }

    public void setCodigoPeriodoAnio(String codigoPeriodoAnio) {
        this.codigoPeriodoAnio = codigoPeriodoAnio;
    }

    public String getCodigoRangoPeriodo() {
        return codigoRangoPeriodo;
    }

    public void setCodigoRangoPeriodo(String codigoRangoPeriodo) {
        this.codigoRangoPeriodo = codigoRangoPeriodo;
    }

    public String getCodigoRegion() {
        return codigoRegion;
    }

    public void setCodigoRegion(String codigoRegion) {
        this.codigoRegion = codigoRegion;
    }

    public String getCodigoSociedad() {
        return codigoSociedad;
    }

    public void setCodigoSociedad(String codigoSociedad) {
        this.codigoSociedad = codigoSociedad;
    }

    public String getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
    }

    public long getEgresos() {
        return egresos;
    }

    public void setEgresos(long egresos) {
        this.egresos = egresos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoAbierto() {
        return estadoAbierto;
    }

    public void setEstadoAbierto(String estadoAbierto) {
        this.estadoAbierto = estadoAbierto;
    }

    public Date getFechaFinPeriodo() {
        return fechaFinPeriodo;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo;
    }

    public long getIngresos() {
        return ingresos;
    }

    public void setIngresos(long ingresos) {
        this.ingresos = ingresos;
    }

    public long getNumeroClientes() {
        return numeroClientes;
    }

    public void setNumeroClientes(long numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    public double getPorcentajeActividad() {
        return porcentajeActividad;
    }

    public void setPorcentajeActividad(double porcentajeActividad) {
        this.porcentajeActividad = porcentajeActividad;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public double getPup() {
        return pup;
    }

    public void setPup(double pup) {
        this.pup = pup;
    }

    public long getReingresos() {
        return reingresos;
    }

    public void setReingresos(long reingresos) {
        this.reingresos = reingresos;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public void formatear()
    {
    // actividadesIniciales = this.redondear(actividadesIniciales,0);  
    // ingresos = this.redondear(ingresos,0);  
    // reingresos = this.redondear(reingresos,0);  
    // egresos = this.redondear(egresos,0);  
     porcentajeActividad = this.redondear(porcentajeActividad,2);  
     ppu = this.redondear(ppu,2);  
     pup = this.redondear(pup,2);  
    // numeroClientes = this.redondear(numeroClientes,0);  
       
    }
     
    public double redondear(double doubleNumber, int decimales ){
        double result;
        BigDecimal bd = new BigDecimal(doubleNumber);
        bd = bd.setScale(decimales,BigDecimal.ROUND_HALF_UP);
        result = bd.doubleValue();
        return result;
    }

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
    
	public long getRetencionSdoPeriodo() {
		return retencionSdoPeriodo;
	}

	public long getNumeroConsultoraCliPrivilege() {
		return numeroConsultoraCliPrivilege;
	}

	public void setNumeroConsultoraCliPrivilege(long numeroConsultoraCliPrivilege) {
		this.numeroConsultoraCliPrivilege = numeroConsultoraCliPrivilege;
	}

	public long getNumeroClientesInsPrivilege() {
		return numeroClientesInsPrivilege;
	}

	public void setNumeroClientesInsPrivilege(long numeroClientesInsPrivilege) {
		this.numeroClientesInsPrivilege = numeroClientesInsPrivilege;
	}

	public long getNumeroClientesTrsPrivelege() {
		return numeroClientesTrsPrivelege;
	}

	public void setNumeroClientesTrsPrivelege(long numeroClientesTrsPrivelege) {
		this.numeroClientesTrsPrivelege = numeroClientesTrsPrivelege;
	}

	public void setRetencionSdoPeriodo(long retencionSdoPeriodo) {
		this.retencionSdoPeriodo = retencionSdoPeriodo;
	}

	public long getRetencionTerPeriodo() {
		return retencionTerPeriodo;
	}

	public void setRetencionTerPeriodo(long retencionTerPeriodo) {
		this.retencionTerPeriodo = retencionTerPeriodo;
	}

	public long getRetencionCuaPeriodo() {
		return retencionCuaPeriodo;
	}

	public void setRetencionCuaPeriodo(long retencionCuaPeriodo) {
		this.retencionCuaPeriodo = retencionCuaPeriodo;
	}

	public long getRetencionActivas() {
		return retencionActivas;
	}

	public void setRetencionActivas(long retencionActivas) {
		this.retencionActivas = retencionActivas;
	}

	public double getPorcentajeRotacion() {
		return porcentajeRotacion;
	}

	public void setPorcentajeRotacion(double porcentajeRotacion) {
		this.porcentajeRotacion = porcentajeRotacion;
	}

	public long getPosiblesEgresos() {
		return posiblesEgresos;
	}

	public void setPosiblesEgresos(long posiblesEgresos) {
		this.posiblesEgresos = posiblesEgresos;
	}

	public long getRetencionPosEgresos() {
		return retencionPosEgresos;
	}

	public void setRetencionPosEgresos(long retencionPosEgresos) {
		this.retencionPosEgresos = retencionPosEgresos;
	}

	public double getActividadLider() {
		return actividadLider;
	}

	public void setActividadLider(double actividadLider) {
		this.actividadLider = actividadLider;
	}

    
    
}