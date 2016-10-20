/*
 * Created on 30-nov-2005
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
 * <a href="InterfazSABIncentivosConsultoras.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazSABIncentivosConsultoras implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2161853328010954730L;
	public String codigoPais;
    public String codigoSociedad;
    public String codigoCanal;
    public String ejercicio;
    public String periodo;
    public String numeroConcurso;
    public Integer nivelPremio;
    public Integer numeroPremio;
    public String codigoProducto;
    public String posicionPlan;
    public Integer cantidadPremiosEntregados;
    public String unidadMedida;
    public Double precioPublicoPremio;
    public String codigoMonedaLocal;
    
    public Integer getCantidadPremiosEntregados() {
        return cantidadPremiosEntregados;
    }
    public void setCantidadPremiosEntregados(Integer cantidadPremiosEntregados) {
        this.cantidadPremiosEntregados = cantidadPremiosEntregados;
    }
    public String getCodigoCanal() {
        return codigoCanal;
    }
    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }
    public String getCodigoMonedaLocal() {
        return codigoMonedaLocal;
    }
    public void setCodigoMonedaLocal(String codigoMonedaLocal) {
        this.codigoMonedaLocal = codigoMonedaLocal;
    }
    public String getCodigoPais() {
        return codigoPais;
    }
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }
    public String getCodigoProducto() {
        return codigoProducto;
    }
    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
    public String getCodigoSociedad() {
        return codigoSociedad;
    }
    public void setCodigoSociedad(String codigoSociedad) {
        this.codigoSociedad = codigoSociedad;
    }
    public String getEjercicio() {
        return ejercicio;
    }
    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }
    public Integer getNivelPremio() {
        return nivelPremio;
    }
    public void setNivelPremio(Integer nivelPremio) {
        this.nivelPremio = nivelPremio;
    }
    public String getNumeroConcurso() {
        return numeroConcurso;
    }
    public void setNumeroConcurso(String numeroConcurso) {
        this.numeroConcurso = numeroConcurso;
    }
    public Integer getNumeroPremio() {
        return numeroPremio;
    }
    public void setNumeroPremio(Integer numeroPremio) {
        this.numeroPremio = numeroPremio;
    }
    public String getPeriodo() {
        return periodo;
    }
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public String getPosicionPlan() {
        return posicionPlan;
    }
    public void setPosicionPlan(String posicionPlan) {
        this.posicionPlan = posicionPlan;
    }
    public Double getPrecioPublicoPremio() {
        return precioPublicoPremio;
    }
    public void setPrecioPublicoPremio(Double precioPublicoPremio) {
        this.precioPublicoPremio = precioPublicoPremio;
    }
    public String getUnidadMedida() {
        return unidadMedida;
    }
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

}










