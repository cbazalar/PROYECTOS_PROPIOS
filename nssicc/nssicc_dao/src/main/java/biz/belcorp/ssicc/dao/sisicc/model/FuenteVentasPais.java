/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.CompareToBuilder;
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

public class FuenteVentasPais extends AuditableBaseObject implements Comparable, Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2382523948405166729L;

	private long codigoFuenteVentasPais; // COR_VPPA

    private String codigoPais; // PAIS_COD_PAIS

    private String codigoSociedad; // COD_SOCI

    private String codigoAlmacen; // COD_ALMA
    
    private String codigoMarca; // COD_MARC
    
    private String codigoCanal; // COD_CANA

    private String codigoPeriodo; // COD_PERI

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
    
    private String estado; // EST_VPPA;

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

    public long getCodigoFuenteVentasPais() {
        return codigoFuenteVentasPais;
    }

    public void setCodigoFuenteVentasPais(long codigoFuenteVentasPais) {
        this.codigoFuenteVentasPais = codigoFuenteVentasPais;
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

    public String getCodigoSociedad() {
        return codigoSociedad;
    }

    public void setCodigoSociedad(String codigoSociedad) {
        this.codigoSociedad = codigoSociedad;
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
        if (!(object instanceof FuenteVentasPais)) {
            return false;
        }
        FuenteVentasPais rhs = (FuenteVentasPais) object;
        return new EqualsBuilder()
                .append(this.promedioSope, rhs.promedioSope)
                .append(this.egresos, rhs.egresos)
                .append(this.pup, rhs.pup)
                .append(this.capitalizacion, rhs.capitalizacion)
                .append(this.vene, rhs.vene)
                .append(this.codigoSociedad, rhs.codigoSociedad)
                .append(this.codigoFuenteVentasPais, rhs.codigoFuenteVentasPais)
                .append(this.egresosNetos, rhs.egresosNetos)
                .append(this.auditInfo, rhs.auditInfo)
                .append(this.porcentajeActividad, rhs.porcentajeActividad)
                .append(this.ingresos, rhs.ingresos)
                .append(this.numeroUnidades, rhs.numeroUnidades)
                .append(this.numeroPedidos, rhs.numeroPedidos)
                .append(this.codigoPais, rhs.codigoPais)
                .append(this.actividadesIniciales, rhs.actividadesIniciales)
                .append(this.reingresos, rhs.reingresos)
                .append(this.porcentajeEgresosNetos, rhs.porcentajeEgresosNetos)
                .append(this.porcentajeEgresos, rhs.porcentajeEgresos).append(
                        this.actividadesFinales, rhs.actividadesFinales)
                .append(this.codigoAlmacen, rhs.codigoAlmacen).append(this.ppu,
                        rhs.ppu)
                .append(this.numeroClientes, rhs.numeroClientes).append(
                        this.codigoCanal, rhs.codigoCanal).append(
                        this.codigoPeriodo, rhs.codigoPeriodo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1082379505, -1966770223).append(
                this.promedioSope).append(this.egresos).append(this.pup)
                .append(this.capitalizacion).append(this.vene).append(
                        this.codigoSociedad)
                .append(this.codigoFuenteVentasPais).append(this.egresosNetos)
                .append(this.auditInfo).append(this.porcentajeActividad)
                .append(this.ingresos).append(this.numeroUnidades).append(
                        this.numeroPedidos).append(this.codigoPais).append(
                        this.actividadesIniciales).append(this.reingresos)
                .append(this.porcentajeEgresosNetos).append(
                        this.porcentajeEgresos).append(this.actividadesFinales)
                .append(this.codigoAlmacen).append(this.ppu).append(
                        this.numeroClientes).append(this.codigoCanal).append(
                        this.codigoPeriodo).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("ppu", this.ppu).append(
                "porcentajeEgresos", this.porcentajeEgresos).append(
                "auditInfo", this.auditInfo).append("numeroPedidos",
                this.numeroPedidos).append("codigoPeriodo", this.codigoPeriodo)
                .append("porcentajeActividad", this.porcentajeActividad)
                .append("codigoFuenteVentasPais", this.codigoFuenteVentasPais)
                .append("ingresos", this.ingresos).append("promedioSope",
                        this.promedioSope).append("numeroClientes",
                        this.numeroClientes).append("codigoPais",
                        this.codigoPais).append("codigoAlmacen",
                        this.codigoAlmacen).append("codigoSociedad",
                        this.codigoSociedad).append("numeroUnidades",
                        this.numeroUnidades).append("porcentajeEgresosNetos",
                        this.porcentajeEgresosNetos).append("egresos",
                        this.egresos).append("capitalizacion",
                        this.capitalizacion).append("reingresos",
                        this.reingresos)
                .append("codigoCanal", this.codigoCanal).append("egresosNetos",
                        this.egresosNetos).append("actividadesFinales",
                        this.actividadesFinales).append("actividadesIniciales",
                        this.actividadesIniciales).append("pup", this.pup)
                .append("vene", this.vene).toString();
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        FuenteVentasPais myClass = (FuenteVentasPais) object;
        return new CompareToBuilder()
                .append(this.promedioSope, myClass.promedioSope)
                .append(this.egresos, myClass.egresos)
                .append(this.pup, myClass.pup)
                .append(this.capitalizacion, myClass.capitalizacion)
                .append(this.vene, myClass.vene)
                .append(this.codigoSociedad, myClass.codigoSociedad)
                .append(this.codigoFuenteVentasPais,
                        myClass.codigoFuenteVentasPais)
                .append(this.egresosNetos, myClass.egresosNetos)
                .append(this.auditInfo, myClass.auditInfo)
                .append(this.porcentajeActividad, myClass.porcentajeActividad)
                .append(this.ingresos, myClass.ingresos)
                .append(this.numeroUnidades, myClass.numeroUnidades)
                .append(this.numeroPedidos, myClass.numeroPedidos)
                .append(this.codigoPais, myClass.codigoPais)
                .append(this.actividadesIniciales, myClass.actividadesIniciales)
                .append(this.reingresos, myClass.reingresos).append(
                        this.porcentajeEgresosNetos,
                        myClass.porcentajeEgresosNetos).append(
                        this.porcentajeEgresos, myClass.porcentajeEgresos)
                .append(this.actividadesFinales, myClass.actividadesFinales)
                .append(this.codigoAlmacen, myClass.codigoAlmacen).append(
                        this.ppu, myClass.ppu).append(this.numeroClientes,
                        myClass.numeroClientes).append(this.codigoCanal,
                        myClass.codigoCanal).append(this.codigoPeriodo,
                        myClass.codigoPeriodo).toComparison();
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
    
      
}