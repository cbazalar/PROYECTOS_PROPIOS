package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextsapaza
 *
 */
public class ClienteDireccion extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oid;
	private Long oidCliente;
	private Long oidTipoDireccion;
	private Long oidTipoVia;
	private Long oidTerritorio;
	private Long oidVia;
	private String numeroPrincipal;
	private String nombreVia;
	
	private String codigoPostal;
	private Integer interior;
	private Integer manzana;
	private Integer lote;
	private Integer kilometro;
	private String observaciones;

	private String nombreFichero;
	private String coordenadaX;
	private String coordenadaY;
	private String coordenadaZ;
	private Integer indicadorDireccionPrincipal;
	private String indicadorEstandarizacionGIS;
	private String codigoUnidadGeografica;
	private Integer indicadorEliminacion;

	private String barrio;
	
	private boolean isEliminar;
	
	/* INI SA PER-SiCC-2012-0459 */
	private String codigoUbigeo1;
	private String codigoCiudad;
	private String villaPoblacion;
	/* FIN SA PER-SiCC-2012-0459 */
	
	/* INI SA PER-SiCC-2012-0365 */
	private String codigoTipoDireccion;
	private String codigoPeriodoInicio;
	private String codigoPeriodoFin;
	private String codigoPeriodoInicioActual;
	private String codigoPeriodoFinActual;
	/* FIN SA PER-SiCC-2012-0365 */
	
	/* INI SA PER-SiCC-2013-0586 */
	private String codigoUsuario;
	/* FIN SA PER-SiCC-2013-0586 */
	
	private String correVia;//Correlativo Via
	
	//INI ECU-SiCC-2015-0036
	private String valNomManzana;
	private String valNomBarrio;
	private String valEtapaConjunto;
	private String valCallePrincipal;
	private String valCalleSecundaria;
	//FIN ECU-SiCC-2015-0036
	
	private String valCodigoTerritorialCorresponde;
	private String indicadorCamposAdicionales;
	
	/**
	 * @return the isEliminar
	 */
	public boolean isEliminar() {
		return isEliminar;
	}

	public String getValNomManzana() {
		return valNomManzana;
	}

	public void setValNomManzana(String valNomManzana) {
		this.valNomManzana = valNomManzana;
	}

	public String getValNomBarrio() {
		return valNomBarrio;
	}

	public void setValNomBarrio(String valNomBarrio) {
		this.valNomBarrio = valNomBarrio;
	}

	public String getValEtapaConjunto() {
		return valEtapaConjunto;
	}

	public void setValEtapaConjunto(String valEtapaConjunto) {
		this.valEtapaConjunto = valEtapaConjunto;
	}

	public String getValCallePrincipal() {
		return valCallePrincipal;
	}

	public void setValCallePrincipal(String valCallePrincipal) {
		this.valCallePrincipal = valCallePrincipal;
	}

	public String getValCalleSecundaria() {
		return valCalleSecundaria;
	}

	public void setValCalleSecundaria(String valCalleSecundaria) {
		this.valCalleSecundaria = valCalleSecundaria;
	}

	/**
	 * @param isEliminar the isEliminar to set
	 */
	public void setEliminar(boolean isEliminar) {
		this.isEliminar = isEliminar;
	}

	public ClienteDireccion() {
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return Returns the codigoPostal.
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal The codigoPostal to set.
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return Returns the codigoUnidadGeografica.
	 */
	public String getCodigoUnidadGeografica() {
		return codigoUnidadGeografica;
	}

	/**
	 * @param codigoUnidadGeografica The codigoUnidadGeografica to set.
	 */
	public void setCodigoUnidadGeografica(String codigoUnidadGeografica) {
		this.codigoUnidadGeografica = codigoUnidadGeografica;
	}

	/**
	 * @return Returns the coordenadaX.
	 */
	public String getCoordenadaX() {
		return coordenadaX;
	}

	/**
	 * @param coordenadaX The coordenadaX to set.
	 */
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}

	/**
	 * @return Returns the coordenadaY.
	 */
	public String getCoordenadaY() {
		return coordenadaY;
	}

	/**
	 * @param coordenadaY The coordenadaY to set.
	 */
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}

	/**
	 * @return Returns the coordenadaZ.
	 */
	public String getCoordenadaZ() {
		return coordenadaZ;
	}

	/**
	 * @param coordenadaZ The coordenadaZ to set.
	 */
	public void setCoordenadaZ(String coordenadaZ) {
		this.coordenadaZ = coordenadaZ;
	}

	/**
	 * @return Returns the indicadorDireccionPrincipal.
	 */
	public Integer getIndicadorDireccionPrincipal() {
		return indicadorDireccionPrincipal;
	}

	/**
	 * @param indicadorDireccionPrincipal The indicadorDireccionPrincipal to set.
	 */
	public void setIndicadorDireccionPrincipal(Integer indicadorDireccionPrincipal) {
		this.indicadorDireccionPrincipal = indicadorDireccionPrincipal;
	}

	/**
	 * @return Returns the indicadorEliminacion.
	 */
	public Integer getIndicadorEliminacion() {
		return indicadorEliminacion;
	}

	/**
	 * @param indicadorEliminacion The indicadorEliminacion to set.
	 */
	public void setIndicadorEliminacion(Integer indicadorEliminacion) {
		this.indicadorEliminacion = indicadorEliminacion;
	}

	/**
	 * @return Returns the indicadorEstandarizacionGIS.
	 */
	public String getIndicadorEstandarizacionGIS() {
		return indicadorEstandarizacionGIS;
	}

	/**
	 * @param indicadorEstandarizacionGIS The indicadorEstandarizacionGIS to set.
	 */
	public void setIndicadorEstandarizacionGIS(String indicadorEstandarizacionGIS) {
		this.indicadorEstandarizacionGIS = indicadorEstandarizacionGIS;
	}

	/**
	 * @return Returns the interior.
	 */
	public Integer getInterior() {
		return interior;
	}

	/**
	 * @param interior The interior to set.
	 */
	public void setInterior(Integer interior) {
		this.interior = interior;
	}

	/**
	 * @return Returns the kilometro.
	 */
	public Integer getKilometro() {
		return kilometro;
	}

	/**
	 * @param kilometro The kilometro to set.
	 */
	public void setKilometro(Integer kilometro) {
		this.kilometro = kilometro;
	}

	/**
	 * @return Returns the lote.
	 */
	public Integer getLote() {
		return lote;
	}

	/**
	 * @param lote The lote to set.
	 */
	public void setLote(Integer lote) {
		this.lote = lote;
	}

	/**
	 * @return Returns the manzana.
	 */
	public Integer getManzana() {
		return manzana;
	}

	/**
	 * @param manzana The manzana to set.
	 */
	public void setManzana(Integer manzana) {
		this.manzana = manzana;
	}

	/**
	 * @return Returns the nombreFichero.
	 */
	public String getNombreFichero() {
		return nombreFichero;
	}

	/**
	 * @param nombreFichero The nombreFichero to set.
	 */
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	/**
	 * @return Returns the nombreVia.
	 */
	public String getNombreVia() {
		return nombreVia;
	}

	/**
	 * @param nombreVia The nombreVia to set.
	 */
	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	/**
	 * @return Returns the numeroPrincipal.
	 */
	public String getNumeroPrincipal() {
		return numeroPrincipal;
	}

	/**
	 * @param numeroPrincipal The numeroPrincipal to set.
	 */
	public void setNumeroPrincipal(String numeroPrincipal) {
		this.numeroPrincipal = numeroPrincipal;
	}

	/**
	 * @return Returns the observaciones.
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones The observaciones to set.
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return Returns the oid.
	 */
	public Long getOid() {
		return oid;
	}

	/**
	 * @param oid The oid to set.
	 */
	public void setOid(Long oid) {
		this.oid = oid;
	}

	/**
	 * @return Returns the oidCliente.
	 */
	public Long getOidCliente() {
		return oidCliente;
	}

	/**
	 * @param oidCliente The oidCliente to set.
	 */
	public void setOidCliente(Long oidCliente) {
		this.oidCliente = oidCliente;
	}

	/**
	 * @return Returns the oidTerritorio.
	 */
	public Long getOidTerritorio() {
		return oidTerritorio;
	}

	/**
	 * @param oidTerritorio The oidTerritorio to set.
	 */
	public void setOidTerritorio(Long oidTerritorio) {
		this.oidTerritorio = oidTerritorio;
	}

	/**
	 * @return Returns the oidTipoDireccion.
	 */
	public Long getOidTipoDireccion() {
		return oidTipoDireccion;
	}

	/**
	 * @param oidTipoDireccion The oidTipoDireccion to set.
	 */
	public void setOidTipoDireccion(Long oidTipoDireccion) {
		this.oidTipoDireccion = oidTipoDireccion;
	}

	/**
	 * @return Returns the oidTipoVia.
	 */
	public Long getOidTipoVia() {
		return oidTipoVia;
	}

	/**
	 * @param oidTipoVia The oidTipoVia to set.
	 */
	public void setOidTipoVia(Long oidTipoVia) {
		this.oidTipoVia = oidTipoVia;
	}

	/**
	 * @return Returns the oidVia.
	 */
	public Long getOidVia() {
		return oidVia;
	}

	/**
	 * @param oidVia The oidVia to set.
	 */
	public void setOidVia(Long oidVia) {
		this.oidVia = oidVia;
	}

	/**
	 * @return the barrio
	 */
	public String getBarrio() {
		return barrio;
	}

	/**
	 * @param barrio the barrio to set
	 */
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}	
	
	/**
	 * @return the codigoUbigeo1
	 */
	public String getCodigoUbigeo1() {
		return codigoUbigeo1;
	}

	/**
	 * @param codigoUbigeo1 the codigoUbigeo1 to set
	 */
	public void setCodigoUbigeo1(String codigoUbigeo1) {
		this.codigoUbigeo1 = codigoUbigeo1;
	}

	/**
	 * @return the codigoCiudad
	 */
	public String getCodigoCiudad() {
		return codigoCiudad;
	}

	/**
	 * @param codigoCiudad the codigoCiudad to set
	 */
	public void setCodigoCiudad(String codigoCiudad) {
		this.codigoCiudad = codigoCiudad;
	}

	/**
	 * @return the villaPoblacion
	 */
	public String getVillaPoblacion() {
		return villaPoblacion;
	}

	/**
	 * @param villaPoblacion the villaPoblacion to set
	 */
	public void setVillaPoblacion(String villaPoblacion) {
		this.villaPoblacion = villaPoblacion;
	}

	/**
	 * @return the codigoTipoDireccion
	 */
	public String getCodigoTipoDireccion() {
		return codigoTipoDireccion;
	}

	/**
	 * @param codigoTipoDireccion the codigoTipoDireccion to set
	 */
	public void setCodigoTipoDireccion(String codigoTipoDireccion) {
		this.codigoTipoDireccion = codigoTipoDireccion;
	}
	
	/**
	 * @return the codigoPeriodoInicio
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio the codigoPeriodoInicio to set
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	public boolean isTienePeriodosModificados() {
		boolean modificado = true;
		
		String codigoInicio = this.codigoPeriodoInicio==null?"":this.codigoPeriodoInicio;
		String codigoInicioActual = this.codigoPeriodoInicioActual==null?"":this.codigoPeriodoInicioActual;
		String codigoFin = this.codigoPeriodoFin==null?"":this.codigoPeriodoFin;
		String codigoFinActual = this.codigoPeriodoFinActual==null?"":this.codigoPeriodoFinActual;
		
		if(codigoInicio.equals(codigoInicioActual) && codigoFin.equals(codigoFinActual))
			modificado = false;
		
		return modificado;
	}

	/**
	 * @return the codigoPeriodoInicioActual
	 */
	public String getCodigoPeriodoInicioActual() {
		return codigoPeriodoInicioActual;
	}

	/**
	 * @param codigoPeriodoInicioActual the codigoPeriodoInicioActual to set
	 */
	public void setCodigoPeriodoInicioActual(String codigoPeriodoInicioActual) {
		this.codigoPeriodoInicioActual = codigoPeriodoInicioActual;
	}

	/**
	 * @return the codigoPeriodoFinActual
	 */
	public String getCodigoPeriodoFinActual() {
		return codigoPeriodoFinActual;
	}

	/**
	 * @param codigoPeriodoFinActual the codigoPeriodoFinActual to set
	 */
	public void setCodigoPeriodoFinActual(String codigoPeriodoFinActual) {
		this.codigoPeriodoFinActual = codigoPeriodoFinActual;
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
	 * @return the correVia
	 */
	public String getCorreVia() {
		return correVia;
	}

	/**
	 * @param correVia the correVia to set
	 */
	public void setCorreVia(String correVia) {
		this.correVia = correVia;
	}

	/**
	 * @return the valCodigoTerritorialCorresponde
	 */
	public String getValCodigoTerritorialCorresponde() {
		return valCodigoTerritorialCorresponde;
	}

	/**
	 * @param valCodigoTerritorialCorresponde the valCodigoTerritorialCorresponde to set
	 */
	public void setValCodigoTerritorialCorresponde(String valCodigoTerritorialCorresponde) {
		this.valCodigoTerritorialCorresponde = valCodigoTerritorialCorresponde;
	}

	/**
	 * @return the indicadorCamposAdicionales
	 */
	public String getIndicadorCamposAdicionales() {
		return indicadorCamposAdicionales;
	}

	/**
	 * @param indicadorCamposAdicionales the indicadorCamposAdicionales to set
	 */
	public void setIndicadorCamposAdicionales(String indicadorCamposAdicionales) {
		this.indicadorCamposAdicionales = indicadorCamposAdicionales;
	}
	
	
	
}
