package biz.belcorp.ssicc.dao.spusicc.inc.model;

import java.io.Serializable;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ConcursoNivelPremiacion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Integer numeroNivel;
	private Integer cantidadFijaPuntos;
	private Integer cantidadInicialPuntos;
	private Integer cantidadFinalPuntos;
	private Integer indicadorNivelSelectivo;
	private Integer puntajeServicio;
	private Integer numeroAspirantes;
	private Long oidPremiacion;
	private Long oidTipoPremio;
	private Integer puntosProductosExigidos;
    private String descripcionNivel;
    
    private ConcursoPremioArticulo concursoPremioArticulo;
    private List listConcursoLotePremioArticulo;

    private Integer plazoEntrega;

	public ConcursoNivelPremiacion() {
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConcursoNivelPremiacion [oid=" + oid + ", numeroNivel="
				+ numeroNivel + ", cantidadFijaPuntos=" + cantidadFijaPuntos
				+ ", cantidadInicialPuntos=" + cantidadInicialPuntos
				+ ", cantidadFinalPuntos=" + cantidadFinalPuntos
				+ ", indicadorNivelSelectivo=" + indicadorNivelSelectivo
				+ ", puntajeServicio=" + puntajeServicio
				+ ", numeroAspirantes=" + numeroAspirantes + ", oidPremiacion="
				+ oidPremiacion + ", oidTipoPremio=" + oidTipoPremio
				+ ", puntosProductosExigidos=" + puntosProductosExigidos
				+ ", descripcionNivel=" + descripcionNivel
				+ ", concursoPremioArticulo=" + concursoPremioArticulo
				+ ", listConcursoLotePremioArticulo="
				+ listConcursoLotePremioArticulo + ", plazoEntrega="
				+ plazoEntrega + "]";
	}

	/**
	 * @return the oid
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return the numeroNivel
	 */
	public Integer getNumeroNivel() {
		return numeroNivel;
	}

	/**
	 * @param numeroNivel the numeroNivel to set
	 */
	public void setNumeroNivel(Integer numeroNivel) {
		this.numeroNivel = numeroNivel;
	}

	/**
	 * @return the cantidadFijaPuntos
	 */
	public Integer getCantidadFijaPuntos() {
		return cantidadFijaPuntos;
	}

	/**
	 * @param cantidadFijaPuntos the cantidadFijaPuntos to set
	 */
	public void setCantidadFijaPuntos(Integer cantidadFijaPuntos) {
		this.cantidadFijaPuntos = cantidadFijaPuntos;
	}

	/**
	 * @return the cantidadInicialPuntos
	 */
	public Integer getCantidadInicialPuntos() {
		return cantidadInicialPuntos;
	}

	/**
	 * @param cantidadInicialPuntos the cantidadInicialPuntos to set
	 */
	public void setCantidadInicialPuntos(Integer cantidadInicialPuntos) {
		this.cantidadInicialPuntos = cantidadInicialPuntos;
	}

	/**
	 * @return the cantidadFinalPuntos
	 */
	public Integer getCantidadFinalPuntos() {
		return cantidadFinalPuntos;
	}

	/**
	 * @param cantidadFinalPuntos the cantidadFinalPuntos to set
	 */
	public void setCantidadFinalPuntos(Integer cantidadFinalPuntos) {
		this.cantidadFinalPuntos = cantidadFinalPuntos;
	}

	/**
	 * @return the indicadorNivelSelectivo
	 */
	public Integer getIndicadorNivelSelectivo() {
		return indicadorNivelSelectivo;
	}

	/**
	 * @param indicadorNivelSelectivo the indicadorNivelSelectivo to set
	 */
	public void setIndicadorNivelSelectivo(Integer indicadorNivelSelectivo) {
		this.indicadorNivelSelectivo = indicadorNivelSelectivo;
	}

	/**
	 * @return the puntajeServicio
	 */
	public Integer getPuntajeServicio() {
		return puntajeServicio;
	}

	/**
	 * @param puntajeServicio the puntajeServicio to set
	 */
	public void setPuntajeServicio(Integer puntajeServicio) {
		this.puntajeServicio = puntajeServicio;
	}

	/**
	 * @return the numeroAspirantes
	 */
	public Integer getNumeroAspirantes() {
		return numeroAspirantes;
	}

	/**
	 * @param numeroAspirantes the numeroAspirantes to set
	 */
	public void setNumeroAspirantes(Integer numeroAspirantes) {
		this.numeroAspirantes = numeroAspirantes;
	}

	/**
	 * @return the oidPremiacion
	 */
	public Long getOidPremiacion() {
		return oidPremiacion;
	}

	/**
	 * @param oidPremiacion the oidPremiacion to set
	 */
	public void setOidPremiacion(Long oidPremiacion) {
		this.oidPremiacion = oidPremiacion;
	}

	/**
	 * @return the oidTipoPremio
	 */
	public Long getOidTipoPremio() {
		return oidTipoPremio;
	}

	/**
	 * @param oidTipoPremio the oidTipoPremio to set
	 */
	public void setOidTipoPremio(Long oidTipoPremio) {
		this.oidTipoPremio = oidTipoPremio;
	}

	/**
	 * @return the puntosProductosExigidos
	 */
	public Integer getPuntosProductosExigidos() {
		return puntosProductosExigidos;
	}

	/**
	 * @param puntosProductosExigidos the puntosProductosExigidos to set
	 */
	public void setPuntosProductosExigidos(Integer puntosProductosExigidos) {
		this.puntosProductosExigidos = puntosProductosExigidos;
	}

	/**
	 * @return the descripcionNivel
	 */
	public String getDescripcionNivel() {
		return descripcionNivel;
	}

	/**
	 * @param descripcionNivel the descripcionNivel to set
	 */
	public void setDescripcionNivel(String descripcionNivel) {
		this.descripcionNivel = descripcionNivel;
	}

	/**
	 * @return the concursoPremioArticulo
	 */
	public ConcursoPremioArticulo getConcursoPremioArticulo() {
		return concursoPremioArticulo;
	}

	/**
	 * @param concursoPremioArticulo the concursoPremioArticulo to set
	 */
	public void setConcursoPremioArticulo(
			ConcursoPremioArticulo concursoPremioArticulo) {
		this.concursoPremioArticulo = concursoPremioArticulo;
	}

	/**
	 * @return the listConcursoLotePremioArticulo
	 */
	public List getListConcursoLotePremioArticulo() {
		return listConcursoLotePremioArticulo;
	}

	/**
	 * @param listConcursoLotePremioArticulo the listConcursoLotePremioArticulo to set
	 */
	public void setListConcursoLotePremioArticulo(
			List listConcursoLotePremioArticulo) {
		this.listConcursoLotePremioArticulo = listConcursoLotePremioArticulo;
	}

	/**
	 * @return the plazoEntrega
	 */
	public Integer getPlazoEntrega() {
		return plazoEntrega;
	}

	/**
	 * @param plazoEntrega the plazoEntrega to set
	 */
	public void setPlazoEntrega(Integer plazoEntrega) {
		this.plazoEntrega = plazoEntrega;
	}

}
