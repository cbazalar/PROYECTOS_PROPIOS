/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Danny Amaro
 *
 */
public class HistoricoReporte extends AuditableBaseObject implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 821566992599301709L;
	
	private Long codigoHistoricoReporte;
	
	private String codigoPais;
	private String codigoMenu;
	private String codigoReporte;
	private String nombreArchivoReporte;
	private String ipMaquina;
	private String codigoUsuario;
	private String codigoPeriodo;
	
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	
	private String formatoReporte;
	private Long duracionSegundos;
	private String multiReporte;
	private Long numeroMultireportes;
	private String envioMail;
	private String indicadorError;
			
	public HistoricoReporte(){
		this.numeroMultireportes = 1L;
		this.multiReporte = "N";
		this.envioMail = "N";
		this.indicadorError = "N";
	}
	
	/**
	 * @return the codigoHistoricoReporte
	 */
	public Long getCodigoHistoricoReporte() {
		return codigoHistoricoReporte;
	}

	/**
	 * @param codigoHistoricoReporte the codigoHistoricoReporte to set
	 */
	public void setCodigoHistoricoReporte(Long codigoHistoricoReporte) {
		this.codigoHistoricoReporte = codigoHistoricoReporte;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoMenu
	 */
	public String getCodigoMenu() {
		return codigoMenu;
	}

	/**
	 * @param codigoMenu the codigoMenu to set
	 */
	public void setCodigoMenu(String codigoMenu) {
		this.codigoMenu = codigoMenu;
	}

	/**
	 * @return the codigoReporte
	 */
	public String getCodigoReporte() {
		return codigoReporte;
	}

	/**
	 * @param codigoReporte the codigoReporte to set
	 */
	public void setCodigoReporte(String codigoReporte) {
		this.codigoReporte = codigoReporte;
	}

	/**
	 * @return the nombreArchivoReporte
	 */
	public String getNombreArchivoReporte() {
		return nombreArchivoReporte;
	}

	/**
	 * @param nombreArchivoReporte the nombreArchivoReporte to set
	 */
	public void setNombreArchivoReporte(String nombreArchivoReporte) {
		this.nombreArchivoReporte = nombreArchivoReporte;
	}

	/**
	 * @return the ipMaquina
	 */
	public String getIpMaquina() {
		return ipMaquina;
	}

	/**
	 * @param ipMaquina the ipMaquina to set
	 */
	public void setIpMaquina(String ipMaquina) {
		this.ipMaquina = ipMaquina;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the fechaInicio
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Timestamp getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the duracionSegundos
	 */
	public Long getDuracionSegundos() {
		return duracionSegundos;
	}

	/**
	 * @param duracionSegundos the duracionSegundos to set
	 */
	public void setDuracionSegundos(Long duracionSegundos) {
		this.duracionSegundos = duracionSegundos;
	}

	/**
	 * @return the multiReporte
	 */
	public String getMultiReporte() {
		return multiReporte;
	}

	/**
	 * @param multiReporte the multiReporte to set
	 */
	public void setMultiReporte(String multiReporte) {
		this.multiReporte = multiReporte;
	}

	/**
	 * @return the numeroMultireportes
	 */
	public Long getNumeroMultireportes() {
		return numeroMultireportes;
	}

	/**
	 * @param numeroMultireportes the numeroMultireportes to set
	 */
	public void setNumeroMultireportes(Long numeroMultireportes) {
		this.numeroMultireportes = numeroMultireportes;
	}

	/**
	 * @return the envioMail
	 */
	public String getEnvioMail() {
		return envioMail;
	}

	/**
	 * @param envioMail the envioMail to set
	 */
	public void setEnvioMail(String envioMail) {
		this.envioMail = envioMail;
	}
	
	
	/**
	 * @return the indicadorError
	 */
	public String getIndicadorError() {
		return indicadorError;
	}

	/**
	 * @param indicadorError the indicadorError to set
	 */
	public void setIndicadorError(String indicadorError) {
		this.indicadorError = indicadorError;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append(this.codigoHistoricoReporte.toString())
			.append(this.codigoPais)
			.append(this.codigoMenu)
			.append(this.codigoReporte)
			.append(this.nombreArchivoReporte)
			.append(this.ipMaquina)
			.append(this.codigoUsuario)
			.append(this.codigoPeriodo)
			.append(this.fechaInicio.toString())
			.append(this.fechaFin.toString())
			.append(this.formatoReporte)
			.append(this.duracionSegundos.toString())			
			.append(this.multiReporte)
			.append(this.numeroMultireportes.toString())
			.append(this.envioMail).toString();				
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HistoricoReporte)) {			
            return false;
        }
		
		HistoricoReporte historicoReporte = (HistoricoReporte)object;		
		return new EqualsBuilder()
			.append(this.codigoHistoricoReporte, historicoReporte.codigoHistoricoReporte)
			.append(this.codigoPais, historicoReporte.codigoPais)
			.append(this.codigoMenu, historicoReporte.codigoMenu)
			.append(this.codigoReporte, historicoReporte.codigoReporte)
			.append(this.nombreArchivoReporte, historicoReporte.nombreArchivoReporte)
			.append(this.ipMaquina, historicoReporte.ipMaquina)
			.append(this.codigoUsuario, historicoReporte.codigoUsuario)
			.append(this.codigoPeriodo, historicoReporte.codigoPeriodo)
			.append(this.fechaInicio, historicoReporte.fechaInicio)
			.append(this.fechaFin.toString(), historicoReporte.fechaFin)
			.append(this.formatoReporte, historicoReporte.formatoReporte)
			.append(this.duracionSegundos, historicoReporte.duracionSegundos)			
			.append(this.multiReporte, historicoReporte.multiReporte)
			.append(this.numeroMultireportes, historicoReporte.numeroMultireportes).isEquals();		
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(892135677, -258454513)
				.append(this.codigoHistoricoReporte)
				.append(this.codigoPais)
				.append(this.codigoMenu)
				.append(this.codigoReporte)
				.append(this.nombreArchivoReporte)
				.append(this.ipMaquina)
				.append(this.codigoUsuario)
				.append(this.codigoPeriodo)
				.append(this.fechaInicio)
				.append(this.fechaFin)
				.append(this.formatoReporte)
				.append(this.duracionSegundos)			
				.append(this.multiReporte)
				.append(this.numeroMultireportes)
				.append(this.envioMail)
                .toHashCode();
	}


}
