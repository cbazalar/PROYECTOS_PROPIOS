package biz.belcorp.ssicc.web.spusicc.fdv.form;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ProcesoFDVClusterizacion;
import biz.belcorp.ssicc.dao.spusicc.fdv.model.GenericBean;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

@SuppressWarnings("rawtypes")
public class MantenimientoFDVClusterizacionForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codProc;
	private String nomProc;
	private String arcZona;
	private String arcSecc;
	private String arcVari;
	private String arcNore;
	private String estProc;
	private String staProc;
	private String codigoPais;
    private String descPais;
    private String directorioTemporal;
    
    private File zonaFile;
    private File seccionFile;
    private File variablesExogFile;
    private File noReconstruidaFile;
    
    private String nroGrpo;
    private String perProc;
    private String anyoPerProc;
    
	private String ordVacl;
    private BigDecimal valMevd;
    private String camProc;
    private String anyProc;
    private String camAnyProc;
    
    private String stFlaNse;
	private String stFlaRlur;
	private String stFlaPobl;
	private String stFlaVar1;
	private String stFlaVar2;
	private String stClose;
		
	private boolean checkAllListZoneOffice;
	private boolean checkAllListZoneNoReliableData;
	private boolean checkAllListZoneNoReliableDataVarVenta;
    
    protected GenericBean[] zoneOffice;
    protected GenericBean[] zoneNoReliableData;
    protected GenericBean[] zoneNoReliableDataVarVenta;
    
    protected GenericBean[] zoneAsigSist;
    protected ProcesoFDVClusterizacion[] zoneAsigPais;
    
    private String[] listCodZonaPorSist = {};
	private String[] listAsigPaisPorSist = {};
	private String[] listCodZonaPorAsig = {};
	private String[] listAsigPaisPorAsig = {};
	
	private String[] listCodZonaOffice = {};
	private String[] listSelectedOffice = {};
	private String[] listCodZonaNoReliableData = {};
	private String[] listSelectedNoReliableData = {};
	private String[] listSelectedNoReliableDataVarVenta = {};
	
	private String[] listCodParaDist = {};
	private String[] listValParaDist = {};
	private String[] listCodZonaDist = {};
	private String[] listCamCaseDist = {};
	private String[] listNroSecoDist = {};
	
	private String[] listCodZonaAjus = {};
	private String[] listValZonaAjus = {};
	
	private List listDist;
	private List listSecc;
	private List listCamp;
    private List listZonaAjus;
	
    private String totalMetVenAjuPer;
    private String totalMetVenAjuDv;
    private String diferenciaMetas;
    
    private Integer versionProceso;
    private String nombreProcesoDetallado;
    
    private String stFlaClus;
    
    private UploadedFile ajusteMetaFile;    
    
    public MantenimientoFDVClusterizacionForm() {
    	this.zoneOffice = new GenericBean[0];
    	this.zoneNoReliableData = new GenericBean[0];
    	this.zoneNoReliableDataVarVenta = new GenericBean[0];
    	this.zoneAsigSist = new GenericBean[0];
    	this.zoneAsigPais = new ProcesoFDVClusterizacion[0];
    	this.listDist = new ArrayList();
    	this.listSecc = new ArrayList();
    	this.listCamp = new ArrayList();
    	this.valMevd = new BigDecimal(0);
    	this.totalMetVenAjuPer = "0";
    	this.totalMetVenAjuDv = "0";
    	this.diferenciaMetas = "0";
    	
    	this.listZonaAjus = new ArrayList();
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.anyoPerProc = sdf.format(new Date(System.currentTimeMillis())).substring(6, 10);
    }
    
    public String getAnyoPerProc() {
		return this.anyoPerProc;
	}

	public String getAnyProc() {
		return this.anyProc;
	}
    
	public String getArcNore() {
		return this.arcNore;
	}

	public String getArcSecc() {
		return this.arcSecc;
	}

	public String getArcVari() {
		return this.arcVari;
	}

	public String getArcZona() {
		return this.arcZona;
	}

	public String getCamAnyProc() {
		return this.camAnyProc;
	}

	public String getCamProc() {
		return this.camProc;
	}

	public String getCodigoPais() {
		return this.codigoPais;
	}

	public String getCodProc() {
		return this.codProc;
	}

	public String getDescPais() {
		return this.descPais;
	}

	public String getDirectorioTemporal() {
		return this.directorioTemporal;
	}
	
	public String getEstProc() {
		return this.estProc;
	}

	public String[] getListAsigPaisPorAsig() {
		return this.listAsigPaisPorAsig;
	}

	public String[] getListAsigPaisPorSist() {
		return this.listAsigPaisPorSist;
	}

	public String[] getListCamCaseDist() {
		return this.listCamCaseDist;
	}

	public List getListCamp() {
		return this.listCamp;
	}

	public String[] getListCodParaDist() {
		return this.listCodParaDist;
	}

	public String[] getListCodZonaDist() {
		return this.listCodZonaDist;
	}

	public String[] getListCodZonaNoReliableData() {
		return this.listCodZonaNoReliableData;
	}

	public String[] getListCodZonaOffice() {
		return this.listCodZonaOffice;
	}

	public String[] getListCodZonaPorAsig() {
		return this.listCodZonaPorAsig;
	}

	public String[] getListCodZonaPorSist() {
		return this.listCodZonaPorSist;
	}

	public List getListDist() {
		return this.listDist;
	}

	public String[] getListNroSecoDist() {
		return this.listNroSecoDist;
	}

	public List getListSecc() {
		return this.listSecc;
	}

	public String[] getListSelectedNoReliableData() {
		return this.listSelectedNoReliableData;
	}

	/**
	 * @return the listSelectedNoReliableDataVarVenta
	 */
	public String[] getListSelectedNoReliableDataVarVenta() {
		return this.listSelectedNoReliableDataVarVenta;
	}

	public String[] getListSelectedOffice() {
		return this.listSelectedOffice;
	}

	public String[] getListValParaDist() {
		return this.listValParaDist;
	}
	
    public String getNomProc() {
		return this.nomProc;
	}

	public File getNoReconstruidaFile() {
		return this.noReconstruidaFile;
	}

	public String getNroGrpo() {
		return this.nroGrpo;
	}

	public String getOrdVacl() {
		return this.ordVacl;
	}

	public String getPerProc() {
		return this.perProc;
	}

	public File getSeccionFile() {
		return this.seccionFile;
	}

	public String getStaProc() {
		return this.staProc;
	}

	public String getStClose() {
		return this.stClose;
	}

	public String getStFlaNse() {
		return this.stFlaNse;
	}

	public String getStFlaPobl() {
		return this.stFlaPobl;
	}

	public String getStFlaRlur() {
		return this.stFlaRlur;
	}

	public String getStFlaVar1() {
		return this.stFlaVar1;
	}

	public String getStFlaVar2() {
		return this.stFlaVar2;
	}

	public BigDecimal getValMevd() {
		return this.valMevd;
	}

	public File getVariablesExogFile() {
		return this.variablesExogFile;
	}

	public File getZonaFile() {
		return this.zonaFile;
	}

	public ProcesoFDVClusterizacion[] getZoneAsigPais() {
		return this.zoneAsigPais;
	}

	public GenericBean[] getZoneAsigSist() {
		return this.zoneAsigSist;
	}

	public GenericBean[] getZoneNoReliableData() {
		return this.zoneNoReliableData;
	}

	public GenericBean[] getZoneNoReliableDataVarVenta() {
		return this.zoneNoReliableDataVarVenta;
	}
	
	public GenericBean[] getZoneOffice() {
		return this.zoneOffice;
	}

	public boolean isCheckAllListZoneNoReliableData() {
		return this.checkAllListZoneNoReliableData;
	}

	public boolean isCheckAllListZoneNoReliableDataVarVenta() {
		return this.checkAllListZoneNoReliableDataVarVenta;
	}

	public boolean isCheckAllListZoneOffice() {
		return this.checkAllListZoneOffice;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
        // reset any boolean data types to false
    }

	public void setAnyoPerProc(String anyoPerProc) {
		this.anyoPerProc = anyoPerProc;
	}

	public void setAnyProc(String anyProc) {
		this.anyProc = anyProc;
	}

	/**
     * @struts.validator type = "required"
     */
	public void setArcNore(String arcNore) {
		this.arcNore = arcNore;
	}

	/**
     * @struts.validator type = "required"
     */
	public void setArcSecc(String arcSecc) {
		this.arcSecc = arcSecc;
	}

	/**
     * @struts.validator type = "required"
     */
	public void setArcVari(String arcVari) {
		this.arcVari = arcVari;
	}
	
	/**
     * @struts.validator type = "required"
     */
	public void setArcZona(String arcZona) {
		this.arcZona = arcZona;
	}

	public void setCamAnyProc(String camAnyProc) {
		this.camAnyProc = camAnyProc;
	}

	public void setCamProc(String camProc) {
		this.camProc = camProc;
	}

	public void setCheckAllListZoneNoReliableData(
			boolean checkAllListZoneNoReliableData) {
		this.checkAllListZoneNoReliableData = checkAllListZoneNoReliableData;
	}

	public void setCheckAllListZoneNoReliableDataVarVenta(
			boolean checkAllListZoneNoReliableDataVarVenta) {
		this.checkAllListZoneNoReliableDataVarVenta = checkAllListZoneNoReliableDataVarVenta;
	}

	public void setCheckAllListZoneOffice(boolean checkAllListZoneOffice) {
		this.checkAllListZoneOffice = checkAllListZoneOffice;
	}

	/**
     * @struts.validator type = "required"
     */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public void setCodProc(String codProc) {
		this.codProc = codProc;
	}

	public void setDescPais(String descPais) {
		this.descPais = descPais;
	}

	public void setDirectorioTemporal(String directorioTemporal) {
		this.directorioTemporal = directorioTemporal;
	}

	public void setEstProc(String estProc) {
		this.estProc = estProc;
	}

	public void setListAsigPaisPorAsig(String[] listAsigPaisPorAsig) {
		this.listAsigPaisPorAsig = listAsigPaisPorAsig;
	}

	public void setListAsigPaisPorSist(String[] listAsigPaisPorSist) {
		this.listAsigPaisPorSist = listAsigPaisPorSist;
	}

	public void setListCamCaseDist(String[] listCamCaseDist) {
		this.listCamCaseDist = listCamCaseDist;
	}

	public void setListCamp(List listCamp) {
		this.listCamp = listCamp;
	}

	public void setListCodParaDist(String[] listCodParaDist) {
		this.listCodParaDist = listCodParaDist;
	}

	public void setListCodZonaDist(String[] listCodZonaDist) {
		this.listCodZonaDist = listCodZonaDist;
	}

	public void setListCodZonaNoReliableData(String[] listCodZonaNoReliableData) {
		this.listCodZonaNoReliableData = listCodZonaNoReliableData;
	}

	public void setListCodZonaOffice(String[] listCodZonaOffice) {
		this.listCodZonaOffice = listCodZonaOffice;
	}

	public void setListCodZonaPorAsig(String[] listCodZonaPorAsig) {
		this.listCodZonaPorAsig = listCodZonaPorAsig;
	}

	public void setListCodZonaPorSist(String[] listCodZonaPorSist) {
		this.listCodZonaPorSist = listCodZonaPorSist;
	}

	public void setListDist(List listDist) {
		this.listDist = listDist;
	}

	public void setListNroSecoDist(String[] listNroSecoDist) {
		this.listNroSecoDist = listNroSecoDist;
	}

	public void setListSecc(List listSecc) {
		this.listSecc = listSecc;
	}

	public void setListSelectedNoReliableData(String[] listSelectedNoReliableData) {
		this.listSelectedNoReliableData = listSelectedNoReliableData;
	}

	/**
	 * @param listSelectedNoReliableDataVarVenta the listSelectedNoReliableDataVarVenta to set
	 */
	public void setListSelectedNoReliableDataVarVenta(
			String[] listSelectedNoReliableDataVarVenta) {
		this.listSelectedNoReliableDataVarVenta = listSelectedNoReliableDataVarVenta;
	}

	public void setListSelectedOffice(String[] listSelectedOffice) {
		this.listSelectedOffice = listSelectedOffice;
	}

	public void setListValParaDist(String[] listValParaDist) {
		this.listValParaDist = listValParaDist;
	}

	/**
     * @struts.validator type = "required"
     */
	public void setNomProc(String nomProc) {
		this.nomProc = nomProc;
	}

	public void setNoReconstruidaFile(File noReconstruidaFile) {
		this.noReconstruidaFile = noReconstruidaFile;
	}

	public void setNroGrpo(String nroGrpo) {
		this.nroGrpo = nroGrpo;
	}

	public void setOrdVacl(String ordVacl) {
		this.ordVacl = ordVacl;
	}
	
	public void setPerProc(String perProc) {
		this.perProc = perProc;
	}

	public void setSeccionFile(File seccionFile) {
		this.seccionFile = seccionFile;
	}

	public void setStaProc(String staProc) {
		this.staProc = staProc;
	}

	public void setStClose(String stClose) {
		this.stClose = stClose;
	}

	public void setStFlaNse(String stFlaNse) {
		this.stFlaNse = stFlaNse;
	}

	public void setStFlaPobl(String stFlaPobl) {
		this.stFlaPobl = stFlaPobl;
	}

	public void setStFlaRlur(String stFlaRlur) {
		this.stFlaRlur = stFlaRlur;
	}

	public void setStFlaVar1(String stFlaVar1) {
		this.stFlaVar1 = stFlaVar1;
	}

	public void setStFlaVar2(String stFlaVar2) {
		this.stFlaVar2 = stFlaVar2;
	}

	public void setValMevd(BigDecimal valMevd) {
		this.valMevd = valMevd;
	}

	public void setVariablesExogFile(File variablesExogFile) {
		this.variablesExogFile = variablesExogFile;
	}
	
	public void setZonaFile(File zonaFile) {
		this.zonaFile = zonaFile;
	}

	public void setZoneAsigPais(ProcesoFDVClusterizacion[] zoneAsigPais) {
		this.zoneAsigPais = zoneAsigPais;
	}
	
	public void setZoneAsigSist(GenericBean[] zoneAsigSist) {
		this.zoneAsigSist = zoneAsigSist;
	}

	public void setZoneNoReliableData(GenericBean[] zoneNoReliableData) {
		this.zoneNoReliableData = zoneNoReliableData;
	}
	
	public void setZoneNoReliableDataVarVenta(
			GenericBean[] zoneNoReliableDataVarVenta) {
		this.zoneNoReliableDataVarVenta = zoneNoReliableDataVarVenta;
	}

	public void setZoneOffice(GenericBean[] zoneOffice) {
		this.zoneOffice = zoneOffice;
	}

	/**
	 * @return the listCodZonaAjus
	 */
	public String[] getListCodZonaAjus() {
		return listCodZonaAjus;
	}

	/**
	 * @param listCodZonaAjus the listCodZonaAjus to set
	 */
	public void setListCodZonaAjus(String[] listCodZonaAjus) {
		this.listCodZonaAjus = listCodZonaAjus;
	}

	/**
	 * @return the listValZonaAjus
	 */
	public String[] getListValZonaAjus() {
		return listValZonaAjus;
	}

	/**
	 * @param listValZonaAjus the listValZonaAjus to set
	 */
	public void setListValZonaAjus(String[] listValZonaAjus) {
		this.listValZonaAjus = listValZonaAjus;
	}

	/**
	 * @return the listZonaAjus
	 */
	public List getListZonaAjus() {
		return listZonaAjus;
	}

	/**
	 * @param listZonaAjus the listZonaAjus to set
	 */
	public void setListZonaAjus(List listZonaAjus) {
		this.listZonaAjus = listZonaAjus;
	}

	/**
	 * @return the totalMetVenAjuPer
	 */
	public String getTotalMetVenAjuPer() {
		return totalMetVenAjuPer;
	}

	/**
	 * @param totalMetVenAjuPer the totalMetVenAjuPer to set
	 */
	public void setTotalMetVenAjuPer(String totalMetVenAjuPer) {
		this.totalMetVenAjuPer = totalMetVenAjuPer;
	}

	/**
	 * @return the totalMetVenAjuDv
	 */
	public String getTotalMetVenAjuDv() {
		return totalMetVenAjuDv;
	}

	/**
	 * @param totalMetVenAjuDv the totalMetVenAjuDv to set
	 */
	public void setTotalMetVenAjuDv(String totalMetVenAjuDv) {
		this.totalMetVenAjuDv = totalMetVenAjuDv;
	}

	/**
	 * @return the diferenciaMetas
	 */
	public String getDiferenciaMetas() {
		return diferenciaMetas;
	}

	/**
	 * @param diferenciaMetas the diferenciaMetas to set
	 */
	public void setDiferenciaMetas(String diferenciaMetas) {
		this.diferenciaMetas = diferenciaMetas;
	}

	/**
	 * @return the versionProceso
	 */
	public Integer getVersionProceso() {
		return versionProceso;
	}

	/**
	 * @param versionProceso the versionProceso to set
	 */
	public void setVersionProceso(Integer versionProceso) {
		this.versionProceso = versionProceso;
	}

	/**
	 * @return the nombreProcesoDetallado
	 */
	public String getNombreProcesoDetallado() {
		String nombre = "";
        if(StringUtils.isBlank(anyoPerProc))
        {
        	nombre = nomProc;
        }
        else
        {
        	nombre = String.format(Constants.FORMATO_NOMBRE_PROCESO_LARGO_FDV, anyoPerProc, perProc, versionProceso, nomProc);
        }
        
        return nombre;
	}

	/**
	 * @param nombreProcesoDetallado the nombreProcesoDetallado to set
	 */
	public void setNombreProcesoDetallado(String nombreProcesoDetallado) {
		this.nombreProcesoDetallado = nombreProcesoDetallado;
	}

	/**
	 * @return the stFlaClus
	 */
	public String getStFlaClus() {
		return stFlaClus;
	}

	/**
	 * @param stFlaClus the stFlaClus to set
	 */
	public void setStFlaClus(String stFlaClus) {
		this.stFlaClus = stFlaClus;
	}

	/**
	 * @return the ajusteMetaFile
	 */
	public UploadedFile getAjusteMetaFile() {
		return this.ajusteMetaFile;
	}

	/**
	 * @param ajusteMetaFile the ajusteMetaFile to set
	 */
	public void setAjusteMetaFile(UploadedFile ajusteMetaFile) {
		this.ajusteMetaFile = ajusteMetaFile;
	}

	/*public String getFlaNse() {
		return flaNse;
	}

	public void setFlaNse(String flaNse) {
		this.flaNse = flaNse;
	}

	public String getFlaRlur() {
		return flaRlur;
	}

	public void setFlaRlur(String flaRlur) {
		this.flaRlur = flaRlur;
	}

	public String getFlaPobl() {
		return flaPobl;
	}

	public void setFlaPobl(String flaPobl) {
		this.flaPobl = flaPobl;
	}

	public String getFlaVar1() {
		return flaVar1;
	}

	public void setFlaVar1(String flaVar1) {
		this.flaVar1 = flaVar1;
	}

	public String getFlaVar2() {
		return flaVar2;
	}

	public void setFlaVar2(String flaVar2) {
		this.flaVar2 = flaVar2;
	}

	public String getCluAsigSist() {
		return cluAsigSist;
	}

	public void setCluAsigSist(String cluAsigSist) {
		this.cluAsigSist = cluAsigSist;
	}

	public String getCluAsigPais() {
		return cluAsigPais;
	}

	public void setCluAsigPais(String cluAsigPais) {
		this.cluAsigPais = cluAsigPais;
	}

	public String getUpdatedByProcess() {
		return updatedByProcess;
	}

	public void setUpdatedByProcess(String updatedByProcess) {
		this.updatedByProcess = updatedByProcess;
	}

	public Timestamp getLastUpdatedProcess() {
		return lastUpdatedProcess;
	}

	public void setLastUpdatedProcess(Timestamp lastUpdatedProcess) {
		this.lastUpdatedProcess = lastUpdatedProcess;
	}

	public String getFlaZonc() {
		return flaZonc;
	}

	public void setFlaZonc(String flaZonc) {
		this.flaZonc = flaZonc;
	}

	public String getFlaZofi() {
		return flaZofi;
	}

	public void setFlaZofi(String flaZofi) {
		this.flaZofi = flaZofi;
	}

	public String getCodZona() {
		return codZona;
	}

	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}

	public String getVarRlur() {
		return varRlur;
	}

	public void setVarRlur(String varRlur) {
		this.varRlur = varRlur;
	}

	public String getVarNse() {
		return varNse;
	}

	public void setVarNse(String varNse) {
		this.varNse = varNse;
	}

	public String getVarGrpo() {
		return varGrpo;
	}

	public void setVarGrpo(String varGrpo) {
		this.varGrpo = varGrpo;
	}

	public String getVarVar1() {
		return varVar1;
	}

	public void setVarVar1(String varVar1) {
		this.varVar1 = varVar1;
	}

	public String getVarVar2() {
		return varVar2;
	}

	public void setVarVar2(String varVar2) {
		this.varVar2 = varVar2;
	}

	public String getDesGrpo() {
		return desGrpo;
	}

	public void setDesGrpo(String desGrpo) {
		this.desGrpo = desGrpo;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public String getOrdenVar() {
		return ordenVar;
	}

	public void setOrdenVar(String ordenVar) {
		this.ordenVar = ordenVar;
	}

	public String getFlaClus() {
		return flaClus;
	}

	public void setFlaClus(String flaClus) {
		this.flaClus = flaClus;
	}*/
}