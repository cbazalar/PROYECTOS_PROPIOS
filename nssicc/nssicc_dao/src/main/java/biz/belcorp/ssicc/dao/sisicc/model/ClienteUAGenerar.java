/*
 * Created on 28-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;



/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ClienteUAGenerar.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class ClienteUAGenerar extends AuditableBaseObject implements
        Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6048869333984948249L;

	private String codigoPais;

    private String codigoCliente;

    private String tipoDireccion;

    private String tipoVia;

    private String nombreVia;

    private String valorNumero;

    private Integer valorInterior;

    private Integer valorManzana;

    private Integer valorLote;

    private Integer valorKilometro;

    private String codigoUbigeoNivel1;

    private String codigoUbigeoNivel2;

    private String codigoUbigeoNivel3;

    private String codigoUbigeoNivel4;

    private String nivelSocioeconomico;

    private Long valorCoordenadaX;

    private Long valorCoordenadaY;

    private Long valorCoordenadaZ;

    private Long codigoTerritorio;

    private String estadoValidacion;

    private String codigoZona;

    private String estado;

    /**
     * @return Returns the codigoCliente.
     */
    public String getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * @param codigoCliente
     *            The codigoCliente to set.
     */
    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * @return Returns the codigoPais.
     */
    public String getCodigoPais() {
        return codigoPais;
    }

    /**
     * @param codigoPais
     *            The codigoPais to set.
     */
    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    /**
     * @return Returns the codigoTerritorio.
     */
    public Long getCodigoTerritorio() {
        return codigoTerritorio;
    }

    /**
     * @param codigoTerritorio
     *            The codigoTerritorio to set.
     */
    public void setCodigoTerritorio(Long codigoTerritorio) {
        this.codigoTerritorio = codigoTerritorio;
    }

    /**
     * @return Returns the codigoUbigeoNivel1.
     */
    public String getCodigoUbigeoNivel1() {
        return codigoUbigeoNivel1;
    }

    /**
     * @param codigoUbigeoNivel1
     *            The codigoUbigeoNivel1 to set.
     */
    public void setCodigoUbigeoNivel1(String codigoUbigeoNivel1) {
        this.codigoUbigeoNivel1 = codigoUbigeoNivel1;
    }

    /**
     * @return Returns the codigoUbigeoNivel2.
     */
    public String getCodigoUbigeoNivel2() {
        return codigoUbigeoNivel2;
    }

    /**
     * @param codigoUbigeoNivel2
     *            The codigoUbigeoNivel2 to set.
     */
    public void setCodigoUbigeoNivel2(String codigoUbigeoNivel2) {
        this.codigoUbigeoNivel2 = codigoUbigeoNivel2;
    }

    /**
     * @return Returns the codigoUbigeoNivel3.
     */
    public String getCodigoUbigeoNivel3() {
        return codigoUbigeoNivel3;
    }

    /**
     * @param codigoUbigeoNivel3
     *            The codigoUbigeoNivel3 to set.
     */
    public void setCodigoUbigeoNivel3(String codigoUbigeoNivel3) {
        this.codigoUbigeoNivel3 = codigoUbigeoNivel3;
    }

    /**
     * @return Returns the codigoUbigeoNivel4.
     */
    public String getCodigoUbigeoNivel4() {
        return codigoUbigeoNivel4;
    }

    /**
     * @param codigoUbigeoNivel4
     *            The codigoUbigeoNivel4 to set.
     */
    public void setCodigoUbigeoNivel4(String codigoUbigeoNivel4) {
        this.codigoUbigeoNivel4 = codigoUbigeoNivel4;
    }

    /**
     * @return Returns the codigoZona.
     */
    public String getCodigoZona() {
        return codigoZona;
    }

    /**
     * @param codigoZona
     *            The codigoZona to set.
     */
    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
    }

    /**
     * @return Returns the estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            The estado to set.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return Returns the estadoValidacion.
     */
    public String getEstadoValidacion() {
        return estadoValidacion;
    }

    /**
     * @param estadoValidacion
     *            The estadoValidacion to set.
     */
    public void setEstadoValidacion(String estadoValidacion) {
        this.estadoValidacion = estadoValidacion;
    }

    /**
     * @return Returns the nivelSocioeconomico.
     */
    public String getNivelSocioeconomico() {
        return nivelSocioeconomico;
    }

    /**
     * @param nivelSocioeconomico
     *            The nivelSocioeconomico to set.
     */
    public void setNivelSocioeconomico(String nivelSocioeconomico) {
        this.nivelSocioeconomico = nivelSocioeconomico;
    }

    /**
     * @return Returns the nombreVia.
     */
    public String getNombreVia() {
        return nombreVia;
    }

    /**
     * @param nombreVia
     *            The nombreVia to set.
     */
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    /**
     * @return Returns the tipoDireccion.
     */
    public String getTipoDireccion() {
        return tipoDireccion;
    }

    /**
     * @param tipoDireccion
     *            The tipoDireccion to set.
     */
    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    /**
     * @return Returns the tipoVia.
     */
    public String getTipoVia() {
        return tipoVia;
    }

    /**
     * @param tipoVia
     *            The tipoVia to set.
     */
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    /**
     * @return Returns the valorCoordenadaX.
     */
    public Long getValorCoordenadaX() {
        return valorCoordenadaX;
    }

    /**
     * @param valorCoordenadaX
     *            The valorCoordenadaX to set.
     */
    public void setValorCoordenadaX(Long valorCoordenadaX) {
        this.valorCoordenadaX = valorCoordenadaX;
    }

    /**
     * @return Returns the valorCoordenadaY.
     */
    public Long getValorCoordenadaY() {
        return valorCoordenadaY;
    }

    /**
     * @param valorCoordenadaY
     *            The valorCoordenadaY to set.
     */
    public void setValorCoordenadaY(Long valorCoordenadaY) {
        this.valorCoordenadaY = valorCoordenadaY;
    }

    /**
     * @return Returns the valorCoordenadaZ.
     */
    public Long getValorCoordenadaZ() {
        return valorCoordenadaZ;
    }

    /**
     * @param valorCoordenadaZ
     *            The valorCoordenadaZ to set.
     */
    public void setValorCoordenadaZ(Long valorCoordenadaZ) {
        this.valorCoordenadaZ = valorCoordenadaZ;
    }

    /**
     * @return Returns the valorInterior.
     */
    public Integer getValorInterior() {
        return valorInterior;
    }

    /**
     * @param valorInterior
     *            The valorInterior to set.
     */
    public void setValorInterior(Integer valorInterior) {
        this.valorInterior = valorInterior;
    }

    /**
     * @return Returns the valorKilometro.
     */
    public Integer getValorKilometro() {
        return valorKilometro;
    }

    /**
     * @param valorKilometro
     *            The valorKilometro to set.
     */
    public void setValorKilometro(Integer valorKilometro) {
        this.valorKilometro = valorKilometro;
    }

    /**
     * @return Returns the valorLote.
     */
    public Integer getValorLote() {
        return valorLote;
    }

    /**
     * @param valorLote
     *            The valorLote to set.
     */
    public void setValorLote(Integer valorLote) {
        this.valorLote = valorLote;
    }

    /**
     * @return Returns the valorManzana.
     */
    public Integer getValorManzana() {
        return valorManzana;
    }

    /**
     * @param valorManzana
     *            The valorManzana to set.
     */
    public void setValorManzana(Integer valorManzana) {
        this.valorManzana = valorManzana;
    }

    /**
     * @return Returns the valorNumero.
     */
    public String getValorNumero() {
        return valorNumero;
    }

    /**
     * @param valorNumero
     *            The valorNumero to set.
     */
    public void setValorNumero(String valorNumero) {
        this.valorNumero = valorNumero;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof ClienteUAGenerar)) {
            return false;
        }
        ClienteUAGenerar rhs = (ClienteUAGenerar) object;
        return new EqualsBuilder().append(this.codigoUbigeoNivel1,
                rhs.codigoUbigeoNivel1).append(this.estadoValidacion,
                rhs.estadoValidacion).append(this.valorKilometro,
                rhs.valorKilometro).append(this.valorLote, rhs.valorLote)
                .append(this.valorNumero, rhs.valorNumero).append(
                        this.valorCoordenadaY, rhs.valorCoordenadaY).append(
                        this.codigoUbigeoNivel2, rhs.codigoUbigeoNivel2)
                .append(this.tipoVia, rhs.tipoVia).append(this.auditInfo,
                        rhs.auditInfo).append(this.tipoDireccion,
                        rhs.tipoDireccion).append(this.valorCoordenadaZ,
                        rhs.valorCoordenadaZ).append(this.codigoTerritorio,
                        rhs.codigoTerritorio).append(this.nivelSocioeconomico,
                        rhs.nivelSocioeconomico).append(this.codigoCliente,
                        rhs.codigoCliente).append(this.codigoUbigeoNivel3,
                        rhs.codigoUbigeoNivel3).append(this.codigoPais,
                        rhs.codigoPais).append(this.valorManzana,
                        rhs.valorManzana).append(this.valorCoordenadaX,
                        rhs.valorCoordenadaX).append(this.valorInterior,
                        rhs.valorInterior).append(this.estado, rhs.estado)
                .append(this.codigoUbigeoNivel4, rhs.codigoUbigeoNivel4)
                .append(this.nombreVia, rhs.nombreVia).append(this.codigoZona,
                        rhs.codigoZona).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1861158991, 1236693735).append(
                this.codigoUbigeoNivel1).append(this.estadoValidacion).append(
                this.valorKilometro).append(this.valorLote).append(
                this.valorNumero).append(this.valorCoordenadaY).append(
                this.codigoUbigeoNivel2).append(this.tipoVia).append(
                this.auditInfo).append(this.tipoDireccion).append(
                this.valorCoordenadaZ).append(this.codigoTerritorio).append(
                this.nivelSocioeconomico).append(this.codigoCliente).append(
                this.codigoUbigeoNivel3).append(this.codigoPais).append(
                this.valorManzana).append(this.valorCoordenadaX).append(
                this.valorInterior).append(this.estado).append(
                this.codigoUbigeoNivel4).append(this.nombreVia).append(
                this.codigoZona).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this)
                .append("valorLote", this.valorLote).append("valorInterior",
                        this.valorInterior).append("codigoPais",
                        this.codigoPais)
                .append("valorNumero", this.valorNumero).append(
                        "codigoUbigeoNivel1", this.codigoUbigeoNivel1).append(
                        "codigoCliente", this.codigoCliente).append(
                        "valorCoordenadaY", this.valorCoordenadaY).append(
                        "nombreVia", this.nombreVia).append("codigoTerritorio",
                        this.codigoTerritorio).append("auditInfo",
                        this.auditInfo).append("valorCoordenadaX",
                        this.valorCoordenadaX).append("codigoUbigeoNivel4",
                        this.codigoUbigeoNivel4).append("nivelSocioeconomico",
                        this.nivelSocioeconomico).append("estado", this.estado)
                .append("estadoValidacion", this.estadoValidacion).append(
                        "codigoUbigeoNivel3", this.codigoUbigeoNivel3).append(
                        "tipoDireccion", this.tipoDireccion).append("tipoVia",
                        this.tipoVia).append("codigoUbigeoNivel2",
                        this.codigoUbigeoNivel2).append("valorManzana",
                        this.valorManzana).append("valorKilometro",
                        this.valorKilometro).append("valorCoordenadaZ",
                        this.valorCoordenadaZ).append("codigoZona",
                        this.codigoZona).toString();
    }

}
