/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ConsultaFuenteVentas.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class ConsultaFuenteVentas extends AuditableBaseObject implements Comparable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7889485857949740497L;

	private String periodo;
    
    private double activasIniciales; 
    
    private double ingresos;
    
    private double reingresos; 
    
    private double egresosNetos;
    
    private double porcentajeEgresosNetos; 
    
    private double egresosPuros; 
    
    private double porcentajeEgresosPuros;
    
    private double capitalizacion;
    
    private double porcentajeActividad; 
    
    private double promedioVenta;
    
    private double venta;
    
    private double ppu;
    
    private double pup;

    private double unidades;

    private double pedidos;
    
    private double clientes;
    
    private double activasFinales;

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

    public double getActivasFinales() {
        return activasFinales;
    }

    public void setActivasFinales(double activasFinales) {
        this.activasFinales = activasFinales;
    }

    public double getActivasIniciales() {
        return activasIniciales;
    }

    public void setActivasIniciales(double activasIniciales) {
        this.activasIniciales = activasIniciales;
    }

    public double getCapitalizacion() {
        return capitalizacion;
    }

    public void setCapitalizacion(double capitalizacion) {
        this.capitalizacion = capitalizacion;
    }

    public double getClientes() {
        return clientes;
    }

    public void setClientes(double clientes) {
        this.clientes = clientes;
    }

    public double getEgresosNetos() {
        return egresosNetos;
    }

    public void setEgresosNetos(double egresosNetos) {
        this.egresosNetos = egresosNetos;
    }

    public double getEgresosPuros() {
        return egresosPuros;
    }

    public void setEgresosPuros(double egresosPuros) {
        this.egresosPuros = egresosPuros;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getPedidos() {
        return pedidos;
    }

    public void setPedidos(double pedidos) {
        this.pedidos = pedidos;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getPorcentajeActividad() {
        return porcentajeActividad;
    }

    public void setPorcentajeActividad(double porcentajeActividad) {
        this.porcentajeActividad = porcentajeActividad;
    }

    public double getPorcentajeEgresosNetos() {
        return porcentajeEgresosNetos;
    }

    public void setPorcentajeEgresosNetos(double porcentajeEgresosNetos) {
        this.porcentajeEgresosNetos = porcentajeEgresosNetos;
    }

    public double getPorcentajeEgresosPuros() {
        return porcentajeEgresosPuros;
    }

    public void setPorcentajeEgresosPuros(double porcentajeEgresosPuros) {
        this.porcentajeEgresosPuros = porcentajeEgresosPuros;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public double getPromedioVenta() {
        return promedioVenta;
    }

    public void setPromedioVenta(double promedioVenta) {
        this.promedioVenta = promedioVenta;
    }

    public double getPup() {
        return pup;
    }

    public void setPup(double pup) {
        this.pup = pup;
    }

    public double getReingresos() {
        return reingresos;
    }

    public void setReingresos(double reingresos) {
        this.reingresos = reingresos;
    }

    public double getUnidades() {
        return unidades;
    }

    public void setUnidades(double unidades) {
        this.unidades = unidades;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        ConsultaFuenteVentas myClass = (ConsultaFuenteVentas) object;
        return new CompareToBuilder().append(this.periodo, myClass.periodo)
                .append(this.pedidos, myClass.pedidos).append(
                        this.promedioVenta, myClass.promedioVenta).append(
                        this.pup, myClass.pup)
                .append(this.venta, myClass.venta).append(this.capitalizacion,
                        myClass.capitalizacion).append(this.activasFinales,
                        myClass.activasFinales).append(this.egresosNetos,
                        myClass.egresosNetos).append(this.auditInfo,
                        myClass.auditInfo).append(this.ingresos,
                        myClass.ingresos).append(this.porcentajeActividad,
                        myClass.porcentajeActividad).append(
                        this.activasIniciales, myClass.activasIniciales)
                .append(this.clientes, myClass.clientes).append(
                        this.reingresos, myClass.reingresos).append(
                        this.unidades, myClass.unidades).append(
                        this.porcentajeEgresosNetos,
                        myClass.porcentajeEgresosNetos).append(
                        this.egresosPuros, myClass.egresosPuros).append(
                        this.porcentajeEgresosPuros,
                        myClass.porcentajeEgresosPuros).append(this.ppu,
                        myClass.ppu).toComparison();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ConsultaFuenteVentas)) {
            return false;
        }
        ConsultaFuenteVentas rhs = (ConsultaFuenteVentas) object;
        return new EqualsBuilder()
                .append(this.periodo, rhs.periodo)
                .append(this.pedidos, rhs.pedidos)
                .append(this.promedioVenta, rhs.promedioVenta)
                .append(this.pup, rhs.pup)
                .append(this.venta, rhs.venta)
                .append(this.capitalizacion, rhs.capitalizacion)
                .append(this.activasFinales, rhs.activasFinales)
                .append(this.egresosNetos, rhs.egresosNetos)
                .append(this.auditInfo, rhs.auditInfo)
                .append(this.ingresos, rhs.ingresos)
                .append(this.porcentajeActividad, rhs.porcentajeActividad)
                .append(this.activasIniciales, rhs.activasIniciales)
                .append(this.clientes, rhs.clientes)
                .append(this.reingresos, rhs.reingresos)
                .append(this.unidades, rhs.unidades)
                .append(this.porcentajeEgresosNetos, rhs.porcentajeEgresosNetos)
                .append(this.egresosPuros, rhs.egresosPuros)
                .append(this.porcentajeEgresosPuros, rhs.porcentajeEgresosPuros)
                .append(this.ppu, rhs.ppu).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-667453965, -323568423).append(this.periodo)
                .append(this.pedidos).append(this.promedioVenta).append(
                        this.pup).append(this.venta)
                .append(this.capitalizacion).append(this.activasFinales)
                .append(this.egresosNetos).append(this.auditInfo).append(
                        this.ingresos).append(this.porcentajeActividad).append(
                        this.activasIniciales).append(this.clientes).append(
                        this.reingresos).append(this.unidades).append(
                        this.porcentajeEgresosNetos).append(this.egresosPuros)
                .append(this.porcentajeEgresosPuros).append(this.ppu)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("promedioVenta",
                this.promedioVenta).append("ppu", this.ppu).append(
                "porcentajeEgresosPuros", this.porcentajeEgresosPuros).append(
                "venta", this.venta).append("porcentajeEgresosNetos",
                this.porcentajeEgresosNetos)
                .append("auditInfo", this.auditInfo).append("reingresos",
                        this.reingresos).append("capitalizacion",
                        this.capitalizacion).append("activasIniciales",
                        this.activasIniciales).append("periodo", this.periodo)
                .append("porcentajeActividad", this.porcentajeActividad)
                .append("unidades", this.unidades).append("egresosNetos",
                        this.egresosNetos).append("egresosPuros",
                        this.egresosPuros).append("activasFinales",
                        this.activasFinales).append("pedidos", this.pedidos)
                .append("clientes", this.clientes).append("ingresos",
                        this.ingresos).append("pup", this.pup).toString();
    }
       
	public double getActividadLider() {
		return actividadLider;
	}

	public void setActividadLider(double actividadLider) {
		this.actividadLider = actividadLider;
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

	public long getRetencionSdoPeriodo() {
		return retencionSdoPeriodo;
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
       
      
}