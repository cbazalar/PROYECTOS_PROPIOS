/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * @author Sigcomt
 *
 */
public class MantenimientoPEDDefinirOfertaForm extends BaseSearchForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6216427666158955257L;
	
	private String codigoPais;
	private String codigoPeriodo;
	private String oidPeriodo;
	private String oidEstrategia;
	private String oidAcceso;
	private String oidSubacceso;
	private String oidCatalogo;
	private String oidMatriz;
	
	private boolean flagCabeceraSoloLectura;
	private boolean flagDatosOFertaSoloLectura;
	private boolean flagDatosGrupoSoloLectura;
	
	/**/
	private String numeroGrupos;
	private String numeroPaquetes;
	private String oidIndicadorCuadre;
	private String numeroGruposCondicionantes;
	private String condicionantes;
	private String numeroGruposCondicionados;
	private String condicionados;
	private String flagDespachoCompleto;
	private String flagDespachoAutomatico;
	private String oidFormaPago;	
	/**/
	
	private String oidTipoEstrategia;
		
	private String grupoActual;
	private String grupoNumeroTotalGrupos;
	private String grupoTipo;
	private String grupoOidIndicadorCuadre;
	private String grupoFactorCuadre;
	private String grupoAnterior;
	private String grupoNumeroMensaje;
	
	private String[] grupoProductosSelectedItems = {};
	
	private String condicionesPromocionOidTipoCuadre;
	private String condicionesPromocionFactorCuadre;
	
	private String criteriosOidCatalogo;
	private String criteriosCodigoTipoRango;
	private String criteriosPaginaInicial;
	private String criteriosPaginaFinal;
	private String criteriosCodigoProducto;
	private String criteriosIndicadorExclusion;
	
	private String[] criteriosSelectedItems = {};
	
	private boolean newRecord = true;

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
	 * @return the oidPeriodo
	 */
	public String getOidPeriodo() {
		return oidPeriodo;
	}

	/**
	 * @param oidPeriodo the oidPeriodo to set
	 */
	public void setOidPeriodo(String oidPeriodo) {
		this.oidPeriodo = oidPeriodo;
	}

	/**
	 * @return the oidEstrategia
	 */
	public String getOidEstrategia() {
		return oidEstrategia;
	}

	/**
	 * @param oidEstrategia the oidEstrategia to set
	 */
	public void setOidEstrategia(String oidEstrategia) {
		this.oidEstrategia = oidEstrategia;
	}

	/**
	 * @return the oidAcceso
	 */
	public String getOidAcceso() {
		return oidAcceso;
	}

	/**
	 * @param oidAcceso the oidAcceso to set
	 */
	public void setOidAcceso(String oidAcceso) {
		this.oidAcceso = oidAcceso;
	}

	/**
	 * @return the oidSubacceso
	 */
	public String getOidSubacceso() {
		return oidSubacceso;
	}

	/**
	 * @param oidSubacceso the oidSubacceso to set
	 */
	public void setOidSubacceso(String oidSubacceso) {
		this.oidSubacceso = oidSubacceso;
	}

	/**
	 * @return the oidCatalogo
	 */
	public String getOidCatalogo() {
		return oidCatalogo;
	}

	/**
	 * @param oidCatalogo the oidCatalogo to set
	 */
	public void setOidCatalogo(String oidCatalogo) {
		this.oidCatalogo = oidCatalogo;
	}

	/**
	 * @return the oidMatriz
	 */
	public String getOidMatriz() {
		return oidMatriz;
	}

	/**
	 * @param oidMatriz the oidMatriz to set
	 */
	public void setOidMatriz(String oidMatriz) {
		this.oidMatriz = oidMatriz;
	}

	/**
	 * @return the flagCabeceraSoloLectura
	 */
	public boolean isFlagCabeceraSoloLectura() {
		return flagCabeceraSoloLectura;
	}

	/**
	 * @param flagCabeceraSoloLectura the flagCabeceraSoloLectura to set
	 */
	public void setFlagCabeceraSoloLectura(boolean flagCabeceraSoloLectura) {
		this.flagCabeceraSoloLectura = flagCabeceraSoloLectura;
	}

	/**
	 * @return the flagDatosOFertaSoloLectura
	 */
	public boolean isFlagDatosOFertaSoloLectura() {
		return flagDatosOFertaSoloLectura;
	}

	/**
	 * @param flagDatosOFertaSoloLectura the flagDatosOFertaSoloLectura to set
	 */
	public void setFlagDatosOFertaSoloLectura(boolean flagDatosOFertaSoloLectura) {
		this.flagDatosOFertaSoloLectura = flagDatosOFertaSoloLectura;
	}

	/**
	 * @return the flagDatosGrupoSoloLectura
	 */
	public boolean isFlagDatosGrupoSoloLectura() {
		return flagDatosGrupoSoloLectura;
	}

	/**
	 * @param flagDatosGrupoSoloLectura the flagDatosGrupoSoloLectura to set
	 */
	public void setFlagDatosGrupoSoloLectura(boolean flagDatosGrupoSoloLectura) {
		this.flagDatosGrupoSoloLectura = flagDatosGrupoSoloLectura;
	}

	/**
	 * @return the numeroGrupos
	 */
	public String getNumeroGrupos() {
		return numeroGrupos;
	}

	/**
	 * @param numeroGrupos the numeroGrupos to set
	 */
	public void setNumeroGrupos(String numeroGrupos) {
		this.numeroGrupos = numeroGrupos;
	}

	/**
	 * @return the numeroPaquetes
	 */
	public String getNumeroPaquetes() {
		return numeroPaquetes;
	}

	/**
	 * @param numeroPaquetes the numeroPaquetes to set
	 */
	public void setNumeroPaquetes(String numeroPaquetes) {
		this.numeroPaquetes = numeroPaquetes;
	}

	/**
	 * @return the oidIndicadorCuadre
	 */
	public String getOidIndicadorCuadre() {
		return oidIndicadorCuadre;
	}

	/**
	 * @param oidIndicadorCuadre the oidIndicadorCuadre to set
	 */
	public void setOidIndicadorCuadre(String oidIndicadorCuadre) {
		this.oidIndicadorCuadre = oidIndicadorCuadre;
	}

	/**
	 * @return the numeroGruposCondicionantes
	 */
	public String getNumeroGruposCondicionantes() {
		return numeroGruposCondicionantes;
	}

	/**
	 * @param numeroGruposCondicionantes the numeroGruposCondicionantes to set
	 */
	public void setNumeroGruposCondicionantes(String numeroGruposCondicionantes) {
		this.numeroGruposCondicionantes = numeroGruposCondicionantes;
	}

	/**
	 * @return the condicionantes
	 */
	public String getCondicionantes() {
		return condicionantes;
	}

	/**
	 * @param condicionantes the condicionantes to set
	 */
	public void setCondicionantes(String condicionantes) {
		this.condicionantes = condicionantes;
	}

	/**
	 * @return the numeroGruposCondicionados
	 */
	public String getNumeroGruposCondicionados() {
		return numeroGruposCondicionados;
	}

	/**
	 * @param numeroGruposCondicionados the numeroGruposCondicionados to set
	 */
	public void setNumeroGruposCondicionados(String numeroGruposCondicionados) {
		this.numeroGruposCondicionados = numeroGruposCondicionados;
	}

	/**
	 * @return the condicionados
	 */
	public String getCondicionados() {
		return condicionados;
	}

	/**
	 * @param condicionados the condicionados to set
	 */
	public void setCondicionados(String condicionados) {
		this.condicionados = condicionados;
	}

	/**
	 * @return the flagDespachoCompleto
	 */
	public String getFlagDespachoCompleto() {
		return flagDespachoCompleto;
	}

	/**
	 * @param flagDespachoCompleto the flagDespachoCompleto to set
	 */
	public void setFlagDespachoCompleto(String flagDespachoCompleto) {
		this.flagDespachoCompleto = flagDespachoCompleto;
	}

	/**
	 * @return the flagDespachoAutomatico
	 */
	public String getFlagDespachoAutomatico() {
		return flagDespachoAutomatico;
	}

	/**
	 * @param flagDespachoAutomatico the flagDespachoAutomatico to set
	 */
	public void setFlagDespachoAutomatico(String flagDespachoAutomatico) {
		this.flagDespachoAutomatico = flagDespachoAutomatico;
	}

	/**
	 * @return the oidFormaPago
	 */
	public String getOidFormaPago() {
		return oidFormaPago;
	}

	/**
	 * @param oidFormaPago the oidFormaPago to set
	 */
	public void setOidFormaPago(String oidFormaPago) {
		this.oidFormaPago = oidFormaPago;
	}

	/**
	 * @return the oidTipoEstrategia
	 */
	public String getOidTipoEstrategia() {
		return oidTipoEstrategia;
	}

	/**
	 * @param oidTipoEstrategia the oidTipoEstrategia to set
	 */
	public void setOidTipoEstrategia(String oidTipoEstrategia) {
		this.oidTipoEstrategia = oidTipoEstrategia;
	}

	/**
	 * @return the grupoActual
	 */
	public String getGrupoActual() {
		return grupoActual;
	}

	/**
	 * @param grupoActual the grupoActual to set
	 */
	public void setGrupoActual(String grupoActual) {
		this.grupoActual = grupoActual;
	}

	/**
	 * @return the grupoNumeroTotalGrupos
	 */
	public String getGrupoNumeroTotalGrupos() {
		return grupoNumeroTotalGrupos;
	}

	/**
	 * @param grupoNumeroTotalGrupos the grupoNumeroTotalGrupos to set
	 */
	public void setGrupoNumeroTotalGrupos(String grupoNumeroTotalGrupos) {
		this.grupoNumeroTotalGrupos = grupoNumeroTotalGrupos;
	}

	/**
	 * @return the grupoTipo
	 */
	public String getGrupoTipo() {
		return grupoTipo;
	}

	/**
	 * @param grupoTipo the grupoTipo to set
	 */
	public void setGrupoTipo(String grupoTipo) {
		this.grupoTipo = grupoTipo;
	}

	/**
	 * @return the grupoOidIndicadorCuadre
	 */
	public String getGrupoOidIndicadorCuadre() {
		return grupoOidIndicadorCuadre;
	}

	/**
	 * @param grupoOidIndicadorCuadre the grupoOidIndicadorCuadre to set
	 */
	public void setGrupoOidIndicadorCuadre(String grupoOidIndicadorCuadre) {
		this.grupoOidIndicadorCuadre = grupoOidIndicadorCuadre;
	}

	/**
	 * @return the grupoFactorCuadre
	 */
	public String getGrupoFactorCuadre() {
		return grupoFactorCuadre;
	}

	/**
	 * @param grupoFactorCuadre the grupoFactorCuadre to set
	 */
	public void setGrupoFactorCuadre(String grupoFactorCuadre) {
		this.grupoFactorCuadre = grupoFactorCuadre;
	}

	/**
	 * @return the grupoAnterior
	 */
	public String getGrupoAnterior() {
		return grupoAnterior;
	}

	/**
	 * @param grupoAnterior the grupoAnterior to set
	 */
	public void setGrupoAnterior(String grupoAnterior) {
		this.grupoAnterior = grupoAnterior;
	}

	/**
	 * @return the grupoNumeroMensaje
	 */
	public String getGrupoNumeroMensaje() {
		return grupoNumeroMensaje;
	}

	/**
	 * @param grupoNumeroMensaje the grupoNumeroMensaje to set
	 */
	public void setGrupoNumeroMensaje(String grupoNumeroMensaje) {
		this.grupoNumeroMensaje = grupoNumeroMensaje;
	}

	/**
	 * @return the grupoProductosSelectedItems
	 */
	public String[] getGrupoProductosSelectedItems() {
		return grupoProductosSelectedItems;
	}

	/**
	 * @param grupoProductosSelectedItems the grupoProductosSelectedItems to set
	 */
	public void setGrupoProductosSelectedItems(String[] grupoProductosSelectedItems) {
		this.grupoProductosSelectedItems = grupoProductosSelectedItems;
	}

	/**
	 * @return the condicionesPromocionOidTipoCuadre
	 */
	public String getCondicionesPromocionOidTipoCuadre() {
		return condicionesPromocionOidTipoCuadre;
	}

	/**
	 * @param condicionesPromocionOidTipoCuadre the condicionesPromocionOidTipoCuadre to set
	 */
	public void setCondicionesPromocionOidTipoCuadre(
			String condicionesPromocionOidTipoCuadre) {
		this.condicionesPromocionOidTipoCuadre = condicionesPromocionOidTipoCuadre;
	}

	/**
	 * @return the condicionesPromocionFactorCuadre
	 */
	public String getCondicionesPromocionFactorCuadre() {
		return condicionesPromocionFactorCuadre;
	}

	/**
	 * @param condicionesPromocionFactorCuadre the condicionesPromocionFactorCuadre to set
	 */
	public void setCondicionesPromocionFactorCuadre(
			String condicionesPromocionFactorCuadre) {
		this.condicionesPromocionFactorCuadre = condicionesPromocionFactorCuadre;
	}

	/**
	 * @return the criteriosOidCatalogo
	 */
	public String getCriteriosOidCatalogo() {
		return criteriosOidCatalogo;
	}

	/**
	 * @param criteriosOidCatalogo the criteriosOidCatalogo to set
	 */
	public void setCriteriosOidCatalogo(String criteriosOidCatalogo) {
		this.criteriosOidCatalogo = criteriosOidCatalogo;
	}

	/**
	 * @return the criteriosCodigoTipoRango
	 */
	public String getCriteriosCodigoTipoRango() {
		return criteriosCodigoTipoRango;
	}

	/**
	 * @param criteriosCodigoTipoRango the criteriosCodigoTipoRango to set
	 */
	public void setCriteriosCodigoTipoRango(String criteriosCodigoTipoRango) {
		this.criteriosCodigoTipoRango = criteriosCodigoTipoRango;
	}

	/**
	 * @return the criteriosPaginaInicial
	 */
	public String getCriteriosPaginaInicial() {
		return criteriosPaginaInicial;
	}

	/**
	 * @param criteriosPaginaInicial the criteriosPaginaInicial to set
	 */
	public void setCriteriosPaginaInicial(String criteriosPaginaInicial) {
		this.criteriosPaginaInicial = criteriosPaginaInicial;
	}

	/**
	 * @return the criteriosPaginaFinal
	 */
	public String getCriteriosPaginaFinal() {
		return criteriosPaginaFinal;
	}

	/**
	 * @param criteriosPaginaFinal the criteriosPaginaFinal to set
	 */
	public void setCriteriosPaginaFinal(String criteriosPaginaFinal) {
		this.criteriosPaginaFinal = criteriosPaginaFinal;
	}

	/**
	 * @return the criteriosCodigoProducto
	 */
	public String getCriteriosCodigoProducto() {
		return criteriosCodigoProducto;
	}

	/**
	 * @param criteriosCodigoProducto the criteriosCodigoProducto to set
	 */
	public void setCriteriosCodigoProducto(String criteriosCodigoProducto) {
		this.criteriosCodigoProducto = criteriosCodigoProducto;
	}

	/**
	 * @return the criteriosIndicadorExclusion
	 */
	public String getCriteriosIndicadorExclusion() {
		return criteriosIndicadorExclusion;
	}

	/**
	 * @param criteriosIndicadorExclusion the criteriosIndicadorExclusion to set
	 */
	public void setCriteriosIndicadorExclusion(String criteriosIndicadorExclusion) {
		this.criteriosIndicadorExclusion = criteriosIndicadorExclusion;
	}

	/**
	 * @return the criteriosSelectedItems
	 */
	public String[] getCriteriosSelectedItems() {
		return criteriosSelectedItems;
	}

	/**
	 * @param criteriosSelectedItems the criteriosSelectedItems to set
	 */
	public void setCriteriosSelectedItems(String[] criteriosSelectedItems) {
		this.criteriosSelectedItems = criteriosSelectedItems;
	}

	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

}
