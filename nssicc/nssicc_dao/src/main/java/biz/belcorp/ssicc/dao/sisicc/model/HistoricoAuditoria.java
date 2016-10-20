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
public class HistoricoAuditoria extends AuditableBaseObject implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6024967593400703722L;
	
	private Long codigoHistoricoAuditoria;

	private String codigoPais;
	private String codigoMenu;
	private String descripcionMenu;
	
	private String codigoAccion;	
	private String ipMaquina;
	private String codigoUsuario;
	private String codigoPeriodo;
	
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	
	private Long duracionSegundos;
	private String indicadorEstado;
	
	
	/**
	 * @return the codigoHistoricoAuditoria
	 */
	public Long getCodigoHistoricoAuditoria() {
		return codigoHistoricoAuditoria;
	}

	/**
	 * @param codigoHistoricoAuditoria the codigoHistoricoAuditoria to set
	 */
	public void setCodigoHistoricoAuditoria(Long codigoHistoricoAuditoria) {
		this.codigoHistoricoAuditoria = codigoHistoricoAuditoria;
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
	 * @return the codigoAccion
	 */
	public String getCodigoAccion() {
		return codigoAccion;
	}

	/**
	 * @param codigoAccion the codigoAccion to set
	 */
	public void setCodigoAccion(String codigoAccion) {
		this.codigoAccion = codigoAccion;
	}

	/**
	 * @return the indicadorEstado
	 */
	public String getIndicadorEstado() {
		return indicadorEstado;
	}

	/**
	 * @param indicadorEstado the indicadorEstado to set
	 */
	public void setIndicadorEstado(String indicadorEstado) {
		this.indicadorEstado = indicadorEstado;
	}

	
	@Override
	public String toString() {
		
		return new ToStringBuilder(this)
			.append(this.codigoHistoricoAuditoria.toString())
			.append(this.codigoPais)
			.append(this.codigoMenu)
			.append(this.descripcionMenu)
			.append(this.codigoAccion)
			.append(this.ipMaquina)
			.append(this.codigoUsuario)
			.append(this.codigoPeriodo)
			.append(this.fechaInicio.toString())
			.append(this.fechaFin.toString())
			.append(this.duracionSegundos.toString())			
			.append(this.indicadorEstado).toString();
		
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof HistoricoAuditoria)) {			
            return false;
        }
		
		HistoricoAuditoria historicoAuditoria = (HistoricoAuditoria)object;		
		return new EqualsBuilder()
			.append(this.codigoHistoricoAuditoria, historicoAuditoria.codigoHistoricoAuditoria)
			.append(this.codigoPais, historicoAuditoria.codigoPais)
			.append(this.codigoMenu, historicoAuditoria.codigoMenu)
			.append(this.descripcionMenu, historicoAuditoria.descripcionMenu)
			.append(this.codigoAccion, historicoAuditoria.codigoAccion)					
			.append(this.ipMaquina, historicoAuditoria.ipMaquina)
			.append(this.codigoUsuario, historicoAuditoria.codigoUsuario)
			.append(this.codigoPeriodo, historicoAuditoria.codigoPeriodo)
			.append(this.fechaInicio, historicoAuditoria.fechaInicio)
			.append(this.fechaFin.toString(), historicoAuditoria.fechaFin)
			.append(this.duracionSegundos, historicoAuditoria.duracionSegundos)			
			.append(this.indicadorEstado, historicoAuditoria.indicadorEstado).isEquals();							
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(892135675, -258454515)
				.append(this.codigoHistoricoAuditoria)
				.append(this.codigoPais)
				.append(this.codigoMenu)
				.append(this.descripcionMenu)
				.append(this.codigoAccion)
				.append(this.ipMaquina)
				.append(this.codigoUsuario)
				.append(this.codigoPeriodo)
				.append(this.fechaInicio)
				.append(this.fechaFin)
				.append(this.duracionSegundos)			
				.append(this.indicadorEstado)
                .toHashCode();
	}

	/**
	 * @return the descripcionMenu
	 */
	public String getDescripcionMenu() {
		return descripcionMenu;
	}

	/**
	 * @param descripcionMenu the descripcionMenu to set
	 */
	public void setDescripcionMenu(String descripcionMenu) {
		this.descripcionMenu = descripcionMenu;
	}
	
	

}
