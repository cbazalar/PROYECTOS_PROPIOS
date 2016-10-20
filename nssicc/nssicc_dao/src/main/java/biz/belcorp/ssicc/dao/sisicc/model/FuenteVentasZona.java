/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.math.BigDecimal;

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

public class FuenteVentasZona extends AuditableBaseObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6993187559402461046L;

	private long codigoFuenteVentasZona; // COR_VPZO

    private String codigoPais; // PAIS_COD_PAIS

    private String codigoSociedad; // COD_SOCI

    private String codigoAlmacen; // COD_ALMA
    
    private String codigoMarca; // COD_MARC
    
    private String codigoCanal; // COD_CANA

    private String codigoPeriodo; // COD_PERI

    private String codigoRegion; // COD_REGI

    private String codigoZona; // COD_ZONA


    private double actividadesIniciales; // NUM_ACIN;

    private double ingresos; // NUM_INGR;

    private double reingresos; // NUM_REIN;

    private double egresosNetos; // NUM_EGNE;
    
    private double porcentajeEgresosNetos; // POR_EGNE;

    private double egresos; // NUM_EGPU;
    
    private double porcentajeEgresos; // POR_EGPU;

    private double capitalizacion; // VAL_CAPI;
    
    private double actividadesFinales; // NUM_ACFI;

    private double porcentajeActividad; // POR_ACTI;

    private double numeroPedidos; // NUM_PEDI;

    private double numeroUnidades; // NUM_UNID;

    private double numeroClientes; // NUM_CLIE;

    private double promedioSope; // PRO_SOPE;

    private double vene; // VAL_VENE;
    
    private double ppu; // VAL_PPU;

    private double pup; // VAL_PUP;
    
    private String estado; // EST_VPZO;

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
    
    public double getActividadesFinales() {
        return actividadesFinales;
    }

    public void setActividadesFinales(double actividadesFinales) {
        this.actividadesFinales = actividadesFinales;
    }

    public double getActividadesIniciales() {
        return actividadesIniciales;
    }

    public void setActividadesIniciales(double actividadesIniciales) {
        this.actividadesIniciales = actividadesIniciales;
    }

    public double getCapitalizacion() {
        return capitalizacion;
    }

    public void setCapitalizacion(double capitalizacion) {
        this.capitalizacion = capitalizacion;
    }

    public String getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(String codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getCodigoCanal() {
        return codigoCanal;
    }

    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

    public long getCodigoFuenteVentasZona() {
        return codigoFuenteVentasZona;
    }

    public void setCodigoFuenteVentasZona(long codigoFuenteVentasZona) {
        this.codigoFuenteVentasZona = codigoFuenteVentasZona;
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

    public double getEgresos() {
        return egresos;
    }

    public void setEgresos(double egresos) {
        this.egresos = egresos;
    }

    public double getEgresosNetos() {
        return egresosNetos;
    }

    public void setEgresosNetos(double egresosNetos) {
        this.egresosNetos = egresosNetos;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getNumeroClientes() {
        return numeroClientes;
    }

    public void setNumeroClientes(double numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    public double getNumeroPedidos() {
        return numeroPedidos;
    }

    public void setNumeroPedidos(double numeroPedidos) {
        this.numeroPedidos = numeroPedidos;
    }

    public double getNumeroUnidades() {
        return numeroUnidades;
    }

    public void setNumeroUnidades(double numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public double getPorcentajeActividad() {
        return porcentajeActividad;
    }

    public void setPorcentajeActividad(double porcentajeActividad) {
        this.porcentajeActividad = porcentajeActividad;
    }

    public double getPorcentajeEgresos() {
        return porcentajeEgresos;
    }

    public void setPorcentajeEgresos(double porcentajeEgresos) {
        this.porcentajeEgresos = porcentajeEgresos;
    }

    public double getPorcentajeEgresosNetos() {
        return porcentajeEgresosNetos;
    }

    public void setPorcentajeEgresosNetos(double porcentajeEgresosNetos) {
        this.porcentajeEgresosNetos = porcentajeEgresosNetos;
    }

    public double getPpu() {
        return ppu;
    }

    public void setPpu(double ppu) {
        this.ppu = ppu;
    }

    public double getPromedioSope() {
        return promedioSope;
    }

    public void setPromedioSope(double promedioSope) {
        this.promedioSope = promedioSope;
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

    public double getVene() {
        return vene;
    }

    public void setVene(double vene) {
        this.vene = vene;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof FuenteVentasZona)) {
            return false;
        }
        FuenteVentasZona rhs = (FuenteVentasZona) object;
        return new EqualsBuilder()
                .append(this.egresos, rhs.egresos)
                .append(this.promedioSope, rhs.promedioSope)
                .append(this.pup, rhs.pup)
                .append(this.vene, rhs.vene)
                .append(this.auditInfo, rhs.auditInfo)
                .append(this.porcentajeActividad, rhs.porcentajeActividad)
                .append(this.numeroUnidades, rhs.numeroUnidades)
                .append(this.codigoPais, rhs.codigoPais)
                .append(this.reingresos, rhs.reingresos)
                .append(this.porcentajeEgresosNetos, rhs.porcentajeEgresosNetos)
                .append(this.codigoRegion, rhs.codigoRegion)
                .append(this.porcentajeEgresos, rhs.porcentajeEgresos)
                .append(this.codigoAlmacen, rhs.codigoAlmacen)
                .append(this.capitalizacion, rhs.capitalizacion)
                .append(this.codigoSociedad, rhs.codigoSociedad)
                .append(this.egresosNetos, rhs.egresosNetos)
                .append(this.ingresos, rhs.ingresos)
                .append(this.numeroPedidos, rhs.numeroPedidos)
                .append(this.actividadesIniciales, rhs.actividadesIniciales)
                .append(this.actividadesFinales, rhs.actividadesFinales)
                .append(this.codigoFuenteVentasZona, rhs.codigoFuenteVentasZona)
                .append(this.codigoZona, rhs.codigoZona).append(
                        this.numeroClientes, rhs.numeroClientes).append(
                        this.ppu, rhs.ppu).append(this.codigoPeriodo,
                        rhs.codigoPeriodo).append(this.codigoCanal,
                        rhs.codigoCanal).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1506948267, -1606320935).append(
                this.egresos).append(this.promedioSope).append(this.pup)
                .append(this.vene).append(this.auditInfo).append(
                        this.porcentajeActividad).append(this.numeroUnidades)
                .append(this.codigoPais).append(this.reingresos).append(
                        this.porcentajeEgresosNetos).append(this.codigoRegion)
                .append(this.porcentajeEgresos).append(this.codigoAlmacen)
                .append(this.capitalizacion).append(this.codigoSociedad)
                .append(this.egresosNetos).append(this.ingresos).append(
                        this.numeroPedidos).append(this.actividadesIniciales)
                .append(this.actividadesFinales).append(
                        this.codigoFuenteVentasZona).append(this.codigoZona)
                .append(this.numeroClientes).append(this.ppu).append(
                        this.codigoPeriodo).append(this.codigoCanal)
                .toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("codigoRegion",
                this.codigoRegion).append("ppu", this.ppu).append(
                "porcentajeEgresos", this.porcentajeEgresos).append(
                "auditInfo", this.auditInfo).append("numeroPedidos",
                this.numeroPedidos).append("codigoPeriodo", this.codigoPeriodo)
                .append("porcentajeActividad", this.porcentajeActividad)
                .append("ingresos", this.ingresos).append("promedioSope",
                        this.promedioSope)
                .append("codigoZona", this.codigoZona).append("numeroClientes",
                        this.numeroClientes).append("codigoPais",
                        this.codigoPais).append("codigoAlmacen",
                        this.codigoAlmacen).append("codigoSociedad",
                        this.codigoSociedad).append("numeroUnidades",
                        this.numeroUnidades).append("porcentajeEgresosNetos",
                        this.porcentajeEgresosNetos).append("egresos",
                        this.egresos).append("codigoFuenteVentasZona",
                        this.codigoFuenteVentasZona).append("capitalizacion",
                        this.capitalizacion).append("reingresos",
                        this.reingresos)
                .append("codigoCanal", this.codigoCanal).append("egresosNetos",
                        this.egresosNetos).append("actividadesFinales",
                        this.actividadesFinales).append("actividadesIniciales",
                        this.actividadesIniciales).append("vene", this.vene)
                .append("pup", this.pup).toString();
    }

   public void formatear()
   {
    actividadesIniciales = this.redondear(actividadesIniciales,0);  
    ingresos = this.redondear(ingresos,0);  
    reingresos = this.redondear(reingresos,0);  
    egresosNetos = this.redondear(egresosNetos,0);  
    porcentajeEgresosNetos = this.redondear(porcentajeEgresosNetos,1);  
    egresos = this.redondear(egresos,0);  
    porcentajeEgresos = this.redondear(porcentajeEgresos,1);  
    capitalizacion = this.redondear(capitalizacion,0);  
    actividadesFinales = this.redondear(actividadesFinales,0);  
    porcentajeActividad = this.redondear(porcentajeActividad,2);  
    numeroPedidos = this.redondear(numeroPedidos,0);  
    numeroUnidades = this.redondear(numeroUnidades,0);  
    numeroClientes = this.redondear(numeroClientes,0);  
    promedioSope = this.redondear(promedioSope,2);  
    vene = this.redondear(vene,2);  
    ppu = this.redondear(ppu,2);  
    pup = this.redondear(pup,2);  
   }
    
   public double redondear(double doubleNumber, int decimales ){
       double result;
       BigDecimal bd = new BigDecimal(doubleNumber);
       bd = bd.setScale(decimales,BigDecimal.ROUND_HALF_UP);
       result = bd.doubleValue();
       return result;
   }

/**
 * @return Returns the estado.
 */
public String getEstado() {
	return estado;
}

/**
 * @param estado The estado to set.
 */
public void setEstado(String estado) {
	this.estado = estado;
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