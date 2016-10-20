package biz.belcorp.ssicc.web.scsicc.hip.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author <a href="mailto:jaltamirano@sigcomt.com">Juan José Altamirano
 *         Huamán</a>
 * 
 */
/**
 * @author Sigcomt
 *
 */
public class ConsultaRECBoletaRecojoSearchForm extends BaseSearchForm{

	private static final long serialVersionUID = 6409374363684581938L;
	private String codigoPais;
	private String descripcionPais;
	private String codigoConsultora;
	private String nombreConsultora;
	private String indicadorBloqueada;	
	private String zona;
	private String gerente;
	private String numeroBoletaRecojo;
	
	private String codigo;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	private String codigoCliente;
	private String nombreCliente;
	private String numeroBoleta;
	private String numeroRecojo;
	private String gestion;
	private String resultado;
	private String fechaEmision1;
	private String fechaRecojo1;
	private String impreso1;
	private String enviado1;
	private String regreso1;
	private String loteEnvio1;
	private String loteRegreso1;
	private String fechaEmision2;
	private String fechaRecojo2;
	private String impreso2;
	private String enviado2;
	private String regreso2;
	private String loteEnvio2;
	private String loteRegreso2;
	

	public ConsultaRECBoletaRecojoSearchForm() {
		codigoConsultora="";
		nombreConsultora="";
		indicadorBloqueada="";
		zona="";
		gerente="";
		numeroBoletaRecojo = "";
	}	
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	
	public String getDescripcionPais() {
		return descripcionPais;
	}

	public void setDescripcionPais(String descripcionPais) {
		this.descripcionPais = descripcionPais;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 * @struts.validator type="required"
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}

	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}

	/**
	 * @return the indicadorBloqueada
	 */
	public String getIndicadorBloqueada() {
		return indicadorBloqueada;
	}

	/**
	 * @param indicadorBloqueada the indicadorBloqueada to set
	 */
	public void setIndicadorBloqueada(String indicadorBloqueada) {
		this.indicadorBloqueada = indicadorBloqueada;
	}

	/**
	 * @return the zona
	 */
	public String getZona() {
		return zona;
	}

	/**
	 * @param zona the zona to set
	 */
	public void setZona(String zona) {
		this.zona = zona;
	}

	/**
	 * @return the gerente
	 */
	public String getGerente() {
		return gerente;
	}

	/**
	 * @param gerente the gerente to set
	 */
	public void setGerente(String gerente) {
		this.gerente = gerente;
	}

	/**
	 * @return the numeroBoletaRecojo
	 */
	public String getNumeroBoletaRecojo() {
		return numeroBoletaRecojo;
	}

	/**
	 * @param numeroBoletaRecojo the numeroBoletaRecojo to set
	 * @struts.validator type="required"
	 */
	public void setNumeroBoletaRecojo(String numeroBoletaRecojo) {
		this.numeroBoletaRecojo = numeroBoletaRecojo;
	}

	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return the numeroBoleta
	 */
	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	/**
	 * @param numeroBoleta the numeroBoleta to set
	 */
	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	/**
	 * @return the numeroRecojo
	 */
	public String getNumeroRecojo() {
		return numeroRecojo;
	}

	/**
	 * @param numeroRecojo the numeroRecojo to set
	 */
	public void setNumeroRecojo(String numeroRecojo) {
		this.numeroRecojo = numeroRecojo;
	}

	/**
	 * @return the gestion
	 */
	public String getGestion() {
		return gestion;
	}

	/**
	 * @param gestion the gestion to set
	 */
	public void setGestion(String gestion) {
		this.gestion = gestion;
	}

	/**
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the fechaEmision1
	 */
	public String getFechaEmision1() {
		return fechaEmision1;
	}

	/**
	 * @param fechaEmision1 the fechaEmision1 to set
	 */
	public void setFechaEmision1(String fechaEmision1) {
		this.fechaEmision1 = fechaEmision1;
	}

	/**
	 * @return the fechaRecojo1
	 */
	public String getFechaRecojo1() {
		return fechaRecojo1;
	}

	/**
	 * @param fechaRecojo1 the fechaRecojo1 to set
	 */
	public void setFechaRecojo1(String fechaRecojo1) {
		this.fechaRecojo1 = fechaRecojo1;
	}

	/**
	 * @return the impreso1
	 */
	public String getImpreso1() {
		return impreso1;
	}

	/**
	 * @param impreso1 the impreso1 to set
	 */
	public void setImpreso1(String impreso1) {
		this.impreso1 = impreso1;
	}

	/**
	 * @return the enviado1
	 */
	public String getEnviado1() {
		return enviado1;
	}

	/**
	 * @param enviado1 the enviado1 to set
	 */
	public void setEnviado1(String enviado1) {
		this.enviado1 = enviado1;
	}

	/**
	 * @return the regreso1
	 */
	public String getRegreso1() {
		return regreso1;
	}

	/**
	 * @param regreso1 the regreso1 to set
	 */
	public void setRegreso1(String regreso1) {
		this.regreso1 = regreso1;
	}

	/**
	 * @return the loteEnvio1
	 */
	public String getLoteEnvio1() {
		return loteEnvio1;
	}

	/**
	 * @param loteEnvio1 the loteEnvio1 to set
	 */
	public void setLoteEnvio1(String loteEnvio1) {
		this.loteEnvio1 = loteEnvio1;
	}

	/**
	 * @return the loteRegreso1
	 */
	public String getLoteRegreso1() {
		return loteRegreso1;
	}

	/**
	 * @param loteRegreso1 the loteRegreso1 to set
	 */
	public void setLoteRegreso1(String loteRegreso1) {
		this.loteRegreso1 = loteRegreso1;
	}

	/**
	 * @return the fechaEmision2
	 */
	public String getFechaEmision2() {
		return fechaEmision2;
	}

	/**
	 * @param fechaEmision2 the fechaEmision2 to set
	 */
	public void setFechaEmision2(String fechaEmision2) {
		this.fechaEmision2 = fechaEmision2;
	}

	/**
	 * @return the fechaRecojo2
	 */
	public String getFechaRecojo2() {
		return fechaRecojo2;
	}

	/**
	 * @param fechaRecojo2 the fechaRecojo2 to set
	 */
	public void setFechaRecojo2(String fechaRecojo2) {
		this.fechaRecojo2 = fechaRecojo2;
	}

	/**
	 * @return the impreso2
	 */
	public String getImpreso2() {
		return impreso2;
	}

	/**
	 * @param impreso2 the impreso2 to set
	 */
	public void setImpreso2(String impreso2) {
		this.impreso2 = impreso2;
	}

	/**
	 * @return the enviado2
	 */
	public String getEnviado2() {
		return enviado2;
	}

	/**
	 * @param enviado2 the enviado2 to set
	 */
	public void setEnviado2(String enviado2) {
		this.enviado2 = enviado2;
	}

	/**
	 * @return the regreso2
	 */
	public String getRegreso2() {
		return regreso2;
	}

	/**
	 * @param regreso2 the regreso2 to set
	 */
	public void setRegreso2(String regreso2) {
		this.regreso2 = regreso2;
	}

	/**
	 * @return the loteEnvio2
	 */
	public String getLoteEnvio2() {
		return loteEnvio2;
	}

	/**
	 * @param loteEnvio2 the loteEnvio2 to set
	 */
	public void setLoteEnvio2(String loteEnvio2) {
		this.loteEnvio2 = loteEnvio2;
	}

	/**
	 * @return the loteRegreso2
	 */
	public String getLoteRegreso2() {
		return loteRegreso2;
	}

	/**
	 * @param loteRegreso2 the loteRegreso2 to set
	 */
	public void setLoteRegreso2(String loteRegreso2) {
		this.loteRegreso2 = loteRegreso2;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
		
}
