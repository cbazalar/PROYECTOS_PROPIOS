/*
 * Created on 08-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.ventas.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * 
 * <p>
 * <a href="AuditableBaseObject.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose A, Cairampoma</a>
 * 
 */

public class Estimado extends AuditableBaseObject implements Serializable {

    private String codigoPais; 

    private String codigoPeriodo;

    private String codigoMarca;

    private String codigoCanal;
    
    private String codigoRegion;

    private String codigoZona;

    private String codigoSeccion;

    private Long estimadoVendidas;

    private Double estimadoVentaNeta;

    private Long estimadoPedidos;

    private Long estimadoClientes;

    private Long estimadoActivas;

    private Long estimadoIngresos;

    private Long estimadoEgresos;

    private Long estimadoReingresos;

    private Long objetivoPedidos;

    private Long objetivoActivas;

    private Double realTCPromedio;

    private Double estimadoTCPromedio;
    
    private String indModiPedidosObjetivo;
    
    private Integer secuencia; // del 1 al 6

    private Date fechaFinPeriodo; 

    public boolean equals(Object object) {
        if (!(object instanceof Estimado)) {
            return false;
        }
        Estimado rhs = (Estimado) object;
        return new EqualsBuilder().append(this.codigoPais,rhs.codigoPais)

				.append(this.codigoPeriodo,rhs.codigoPeriodo)

				.append(this.codigoMarca,rhs.codigoMarca)

				.append(this.codigoCanal,rhs.codigoCanal)

				.append(this.codigoRegion,rhs.codigoRegion)

				.append(this.codigoZona,rhs.codigoZona)

				.append(this.codigoSeccion,rhs.codigoSeccion)

				.append(this.estimadoVendidas,rhs.estimadoVendidas)

				.append(this.estimadoVentaNeta,rhs.estimadoVentaNeta)

				.append(this.estimadoPedidos,rhs.estimadoPedidos)

				.append(this.estimadoClientes,rhs.estimadoClientes)

				.append(this.estimadoActivas,rhs.estimadoActivas)

				.append(this.estimadoIngresos,rhs.estimadoIngresos)

				.append(this.estimadoEgresos,rhs.estimadoEgresos)

				.append(this.estimadoReingresos,rhs.estimadoReingresos)

				.append(this.objetivoPedidos,rhs.objetivoPedidos)

				.append(this.objetivoActivas,rhs.objetivoActivas)

				.append(this.realTCPromedio,rhs.realTCPromedio)

				.append(this.estimadoTCPromedio,rhs.estimadoTCPromedio)

				.append(this.secuencia,rhs.secuencia)

				.append(this.fechaFinPeriodo,rhs.fechaFinPeriodo).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
       	
        return new HashCodeBuilder(1542432755, 1102820585).append(this.codigoPais)

		.append(this.codigoPeriodo)

		.append(this.codigoMarca)

		.append(this.codigoCanal)

		.append(this.codigoRegion)

		.append(this.codigoZona)

		.append(this.codigoSeccion)

		.append(this.estimadoVendidas)

		.append(this.estimadoVentaNeta)

		.append(this.estimadoPedidos)

		.append(this.estimadoClientes)

		.append(this.estimadoActivas)

		.append(this.estimadoIngresos)

		.append(this.estimadoEgresos)

		.append(this.estimadoReingresos)

		.append(this.objetivoPedidos)

		.append(this.objetivoActivas)

		.append(this.realTCPromedio)

		.append(this.estimadoTCPromedio)

		.append(this.secuencia)

		.append(this.fechaFinPeriodo).toHashCode();
    }

    /**
	 * @see java.lang.Object#toString()
	 */
    public String toString() {
        return new ToStringBuilder(this).append("auditInfo", this.auditInfo)
                .toString();
    }

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
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

	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public Long getEstimadoActivas() {
		return estimadoActivas;
	}

	public void setEstimadoActivas(Long estimadoActivas) {
		this.estimadoActivas = estimadoActivas;
	}

	public Long getEstimadoClientes() {
		return estimadoClientes;
	}

	public void setEstimadoClientes(Long estimadoClientes) {
		this.estimadoClientes = estimadoClientes;
	}

	public Long getEstimadoEgresos() {
		return estimadoEgresos;
	}

	public void setEstimadoEgresos(Long estimadoEgresos) {
		this.estimadoEgresos = estimadoEgresos;
	}

	public Long getEstimadoIngresos() {
		return estimadoIngresos;
	}

	public void setEstimadoIngresos(Long estimadoIngresos) {
		this.estimadoIngresos = estimadoIngresos;
	}

	public Long getEstimadoPedidos() {
		return estimadoPedidos;
	}

	public void setEstimadoPedidos(Long estimadoPedidos) {
		this.estimadoPedidos = estimadoPedidos;
	}

	public Long getEstimadoReingresos() {
		return estimadoReingresos;
	}

	public void setEstimadoReingresos(Long estimadoReingresos) {
		this.estimadoReingresos = estimadoReingresos;
	}

	public Double getEstimadoTCPromedio() {
		return estimadoTCPromedio;
	}

	public void setEstimadoTCPromedio(Double estimadoTCPromedio) {
		this.estimadoTCPromedio = estimadoTCPromedio;
	}

	public Long getEstimadoVendidas() {
		return estimadoVendidas;
	}

	public void setEstimadoVendidas(Long estimadoVendidas) {
		this.estimadoVendidas = estimadoVendidas;
	}

	public Double getEstimadoVentaNeta() {
		return estimadoVentaNeta;
	}

	public void setEstimadoVentaNeta(Double estimadoVentaNeta) {
		this.estimadoVentaNeta = estimadoVentaNeta;
	}

	public Date getFechaFinPeriodo() {
		return fechaFinPeriodo;
	}

	public void setFechaFinPeriodo(Date fechaFinPeriodo) {
		this.fechaFinPeriodo = fechaFinPeriodo;
	}

	public Long getObjetivoActivas() {
		return objetivoActivas;
	}

	public void setObjetivoActivas(Long objetivoActivas) {
		this.objetivoActivas = objetivoActivas;
	}

	public Long getObjetivoPedidos() {
		return objetivoPedidos;
	}

	public void setObjetivoPedidos(Long objetivoPedidos) {
		this.objetivoPedidos = objetivoPedidos;
	}

	public Double getRealTCPromedio() {
		return realTCPromedio;
	}

	public void setRealTCPromedio(Double realTCPromedio) {
		this.realTCPromedio = realTCPromedio;
	}

	public Integer getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(Integer secuencia) {
		this.secuencia = secuencia;
	}

	public String getIndModiPedidosObjetivo() {
		return indModiPedidosObjetivo;
	}

	public void setIndModiPedidosObjetivo(String indModiPedidosObjetivo) {
		this.indModiPedidosObjetivo = indModiPedidosObjetivo;
	}

	

	

	

	
}