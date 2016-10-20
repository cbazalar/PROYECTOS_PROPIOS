package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class ProgramaCupon extends AuditableBaseObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pk;

	private String id;

	private String codigoPais;

	private String codigoPrograma;

	private String campanhaInicial;

	private String campanhaFinal;

	private String codigoVentCupIni;

	private String codigoVentCupFin;

	private String estadoProg;

	private String numVigencia;	

	private String codigoMarca;

	private String descripcionMarca;

	private String codigoCanal;

	private String descripcionCanal;

	private String situacion;
	
	private String indicadorConstanciaCupon;
	
	private String indicadorConstanciaPremio;
	
	private String indicadorCupon;
	
	private String indicadorConstanciaPremioElectivo;
	
	private String indicadorProgramaObligatorio;
	
	private String indicadorPremioElectivo;
	
	private String indicadorGeneraMensaje;
	
	private String indicadorRegaloPedido;
	private String montoMinimo;
	private String indicadorTodasUA;
	private String indicadorTodasDES;
	private List listUnidadesAdministrativas;
	private boolean indActualizarUAS;
	private List listDescuentos;
	private boolean indActualizarDES;
	private String codigoUsuario;
	private String indicadorPremioIncentivo;
	
	private String numeroPedidos;
	private String tipoPedido;
	private String indicadorPedidoMixto;
	private String numeroNiveles;
	private String indicadorPremioWeb;

	private String indicadorVigencia;
	
	private String indicadorCuponReutilizable;
	
	public String getCampanhaFinal() {
		return campanhaFinal;
	}

	public void setCampanhaFinal(String campanhaFinal) {
		this.campanhaFinal = campanhaFinal;
	}

	public String getCampanhaInicial() {
		return campanhaInicial;
	}

	public void setCampanhaInicial(String campanhaInicial) {
		this.campanhaInicial = campanhaInicial;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	public String getCodigoVentCupFin() {
		return codigoVentCupFin;
	}

	public void setCodigoVentCupFin(String codigoVentCupFin) {
		this.codigoVentCupFin = codigoVentCupFin;
	}

	public String getCodigoVentCupIni() {
		return codigoVentCupIni;
	}

	public void setCodigoVentCupIni(String codigoVentCupIni) {
		this.codigoVentCupIni = codigoVentCupIni;
	}

	public String getEstadoProg() {
		return estadoProg;
	}

	public void setEstadoProg(String estadoProg) {
		this.estadoProg = estadoProg;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProgramaCupon)) {
			return false;
		}

		ProgramaCupon rhs = (ProgramaCupon) object;

		return new EqualsBuilder().append(this.codigoVentCupIni,
				rhs.codigoVentCupIni).append(this.codigoPais, rhs.codigoPais)
				.append(this.codigoVentCupFin, rhs.codigoVentCupFin).append(
						this.estadoProg, rhs.estadoProg).append(
						this.campanhaInicial, rhs.campanhaInicial).append(
						this.codigoPrograma, rhs.codigoPrograma).append(
						this.campanhaFinal, rhs.campanhaFinal).append(
						this.numVigencia, rhs.numVigencia).append(
						this.codigoCanal, rhs.codigoCanal).append(
						this.codigoMarca, rhs.codigoMarca).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2022562655, 96184653).append(
				this.codigoVentCupIni).append(this.codigoPais).append(
				this.codigoVentCupFin).append(this.estadoProg).append(
				this.campanhaInicial).append(this.codigoPrograma).append(
				this.campanhaFinal).append(this.numVigencia).append(
				this.codigoCanal).append(this.codigoMarca).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).toString();
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	/**
	 * @return Returns the numVigencia.
	 */
	public String getNumVigencia() {
		return numVigencia;
	}

	/**
	 * @param numVigencia
	 *            The numVigencia to set.
	 */
	public void setNumVigencia(String numVigencia) {
		this.numVigencia = numVigencia;
	}

	/**
	 * @return Returns the codigoCanal.
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 *            The codigoCanal to set.
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 *            The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	/**
	 * @return the indicadorConstanciaCupon
	 */
	public String getIndicadorConstanciaCupon() {
		return indicadorConstanciaCupon;
	}

	/**
	 * @param indicadorConstanciaCupon the indicadorConstanciaCupon to set
	 */
	public void setIndicadorConstanciaCupon(String indicadorConstanciaCupon) {
		this.indicadorConstanciaCupon = indicadorConstanciaCupon;
	}

	/**
	 * @return the indicadorConstanciaPremio
	 */
	public String getIndicadorConstanciaPremio() {
		return indicadorConstanciaPremio;
	}

	/**
	 * @param indicadorConstanciaPremio the indicadorConstanciaPremio to set
	 */
	public void setIndicadorConstanciaPremio(String indicadorConstanciaPremio) {
		this.indicadorConstanciaPremio = indicadorConstanciaPremio;
	}

	/**
	 * @return the indicadorCupon
	 */
	public String getIndicadorCupon() {
		return indicadorCupon;
	}

	/**
	 * @param indicadorCupon the indicadorCupon to set
	 */
	public void setIndicadorCupon(String indicadorCupon) {
		this.indicadorCupon = indicadorCupon;
	}

	/**
	 * @return the indicadorConstanciaPremioElectivo
	 */
	public String getIndicadorConstanciaPremioElectivo() {
		return indicadorConstanciaPremioElectivo;
	}

	/**
	 * @param indicadorConstanciaPremioElectivo the indicadorConstanciaPremioElectivo to set
	 */
	public void setIndicadorConstanciaPremioElectivo(
			String indicadorConstanciaPremioElectivo) {
		this.indicadorConstanciaPremioElectivo = indicadorConstanciaPremioElectivo;
	}

	/**
	 * @return the indicadorProgramaObligatorio
	 */
	public String getIndicadorProgramaObligatorio() {
		return indicadorProgramaObligatorio;
	}

	/**
	 * @param indicadorProgramaObligatorio the indicadorProgramaObligatorio to set
	 */
	public void setIndicadorProgramaObligatorio(String indicadorProgramaObligatorio) {
		this.indicadorProgramaObligatorio = indicadorProgramaObligatorio;
	}

	/**
	 * @return the indicadorPremioElectivo
	 */
	public String getIndicadorPremioElectivo() {
		return indicadorPremioElectivo;
	}

	/**
	 * @param indicadorPremioElectivo the indicadorPremioElectivo to set
	 */
	public void setIndicadorPremioElectivo(String indicadorPremioElectivo) {
		this.indicadorPremioElectivo = indicadorPremioElectivo;
	}

	/**
	 * @return the indicadorGeneraMensaje
	 */
	public String getIndicadorGeneraMensaje() {
		return indicadorGeneraMensaje;
	}

	/**
	 * @param indicadorGeneraMensaje the indicadorGeneraMensaje to set
	 */
	public void setIndicadorGeneraMensaje(String indicadorGeneraMensaje) {
		this.indicadorGeneraMensaje = indicadorGeneraMensaje;
	}

	/**
	 * @return the indicadorRegaloPedido
	 */
	public String getIndicadorRegaloPedido() {
		return indicadorRegaloPedido;
	}

	/**
	 * @param indicadorRegaloPedido the indicadorRegaloPedido to set
	 */
	public void setIndicadorRegaloPedido(String indicadorRegaloPedido) {
		this.indicadorRegaloPedido = indicadorRegaloPedido;
	}

	/**
	 * @return the montoMinimo
	 */
	public String getMontoMinimo() {
		return montoMinimo;
	}

	/**
	 * @param montoMinimo the montoMinimo to set
	 */
	public void setMontoMinimo(String montoMinimo) {
		this.montoMinimo = montoMinimo;
	}

	/**
	 * @return the indicadorTodasUA
	 */
	public String getIndicadorTodasUA() {
		return indicadorTodasUA;
	}

	/**
	 * @param indicadorTodasUA the indicadorTodasUA to set
	 */
	public void setIndicadorTodasUA(String indicadorTodasUA) {
		this.indicadorTodasUA = indicadorTodasUA;
	}

	/**
	 * @return the listUnidadesAdministrativas
	 */
	public List getListUnidadesAdministrativas() {
		return listUnidadesAdministrativas;
	}

	/**
	 * @param listUnidadesAdministrativas the listUnidadesAdministrativas to set
	 */
	public void setListUnidadesAdministrativas(List listUnidadesAdministrativas) {
		this.listUnidadesAdministrativas = listUnidadesAdministrativas;
	}

	/**
	 * @return the indActualizarUAS
	 */
	public boolean isIndActualizarUAS() {
		return indActualizarUAS;
	}

	/**
	 * @param indActualizarUAS the indActualizarUAS to set
	 */
	public void setIndActualizarUAS(boolean indActualizarUAS) {
		this.indActualizarUAS = indActualizarUAS;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the indicadorPremioIncentivo
	 */
	public String getIndicadorPremioIncentivo() {
		return indicadorPremioIncentivo;
	}

	/**
	 * @param indicadorPremioIncentivo the indicadorPremioIncentivo to set
	 */
	public void setIndicadorPremioIncentivo(String indicadorPremioIncentivo) {
		this.indicadorPremioIncentivo = indicadorPremioIncentivo;
	}

	/**
	 * @return the numeroPedidos
	 */
	public String getNumeroPedidos() {
		return numeroPedidos;
	}

	/**
	 * @param numeroPedidos the numeroPedidos to set
	 */
	public void setNumeroPedidos(String numeroPedidos) {
		this.numeroPedidos = numeroPedidos;
	}

	/**
	 * @return the tipoPedido
	 */
	public String getTipoPedido() {
		return tipoPedido;
	}

	/**
	 * @param tipoPedido the tipoPedido to set
	 */
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}

	/**
	 * @return the indicadorPedidoMixto
	 */
	public String getIndicadorPedidoMixto() {
		return indicadorPedidoMixto;
	}

	/**
	 * @param indicadorPedidoMixto the indicadorPedidoMixto to set
	 */
	public void setIndicadorPedidoMixto(String indicadorPedidoMixto) {
		this.indicadorPedidoMixto = indicadorPedidoMixto;
	}

	/**
	 * @return the numeroNiveles
	 */
	public String getNumeroNiveles() {
		return numeroNiveles;
	}

	/**
	 * @param numeroNiveles the numeroNiveles to set
	 */
	public void setNumeroNiveles(String numeroNiveles) {
		this.numeroNiveles = numeroNiveles;
	}

	/**
	 * @return the indicadorPremioWeb
	 */
	public String getIndicadorPremioWeb() {
		return indicadorPremioWeb;
	}

	/**
	 * @param indicadorPremioWeb the indicadorPremioWeb to set
	 */
	public void setIndicadorPremioWeb(String indicadorPremioWeb) {
		this.indicadorPremioWeb = indicadorPremioWeb;
	}
	
	/**
	 * @return the indicadorVigencia
	 */
	public String getIndicadorVigencia() {
		return indicadorVigencia;
	}

	/**
	 * @param indicadorVigencia the indicadorVigencia to set
	 */
	public void setIndicadorVigencia(String indicadorVigencia) {
		this.indicadorVigencia = indicadorVigencia;
	}

	/**
	 * @return the descripcionMarca
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	/**
	 * @param descripcionMarca the descripcionMarca to set
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	/**
	 * @return the descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	/**
	 * @param descripcionCanal the descripcionCanal to set
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	/**
	 * @return the indicadorTodasDES
	 */
	public String getIndicadorTodasDES() {
		return indicadorTodasDES;
	}

	/**
	 * @param indicadorTodasDES the indicadorTodasDES to set
	 */
	public void setIndicadorTodasDES(String indicadorTodasDES) {
		this.indicadorTodasDES = indicadorTodasDES;
	}

	/**
	 * @return the listDescuentos
	 */
	public List getListDescuentos() {
		return listDescuentos;
	}

	/**
	 * @param listDescuentos the listDescuentos to set
	 */
	public void setListDescuentos(List listDescuentos) {
		this.listDescuentos = listDescuentos;
	}

	/**
	 * @return the indActualizarDES
	 */
	public boolean isIndActualizarDES() {
		return indActualizarDES;
	}

	/**
	 * @param indActualizarDES the indActualizarDES to set
	 */
	public void setIndActualizarDES(boolean indActualizarDES) {
		this.indActualizarDES = indActualizarDES;
	}
	

	/**
	 * @return the indicadorCuponReutilizable
	 */
	public String getIndicadorCuponReutilizable() {
		return indicadorCuponReutilizable;
	}

	/**
	 * @param indicadorCuponReutilizable the indicadorCuponReutilizable to set
	 */
	public void setIndicadorCuponReutilizable(String indicadorCuponReutilizable) {
		this.indicadorCuponReutilizable = indicadorCuponReutilizable;
	}
	
}