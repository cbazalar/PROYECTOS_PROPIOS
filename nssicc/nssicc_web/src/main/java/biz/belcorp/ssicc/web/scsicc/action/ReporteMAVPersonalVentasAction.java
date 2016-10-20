package biz.belcorp.ssicc.web.scsicc.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.BaseOID;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAVPersonalVentasForm;

@ManagedBean
@SessionScoped
public class ReporteMAVPersonalVentasAction extends BaseReporteAbstractAction{
	
	
	private static final long serialVersionUID = 7617091221633824602L;
	
	
	private String tipoReporte;
	private List siccActividadList;
	
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAVPersonalVentasForm reporteForm = new ReporteMAVPersonalVentasForm();
		return reporteForm;
	}
	
	
	@Override
	protected void setViewAtributes() throws Exception {

		
		this.mostrarReporteXLS = true;
			
		ReporteMAVPersonalVentasForm f = (ReporteMAVPersonalVentasForm) this.formReporte;  
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
	    String codpais = pais.getCodigo();
	    
	    
		Map criteria = new HashMap();
		criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteria.put("codigoPais", codpais);
		
		String codigoPeriodo=service.getPeriodoDefaultByPaisCanal(codpais, Constants.CODIGO_CANAL_DEFAULT);
		this.siccActividadList= reporteService.getListaGenerico("getListaActividadByMarcaCanal", criteria);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = sdf.format(new Date(System.currentTimeMillis()));			
		f.setCodigoPeriodo(codigoPeriodo);		
		f.setFechaReporte(fecha);
		f.setFechaReporteD(new Date(System.currentTimeMillis()));
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (StringUtils.equals(tipoReporte, "PDF"))
			return "reporteMaestroHorizontal";
		else
			return "MAVPersonalVentasZXLS";
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if (StringUtils.equals(tipoReporte, "PDF"))
			return "reporteMAVPersonalVentasZPDF";		
		else
			return "";		
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		
		ReporteMAVPersonalVentasForm f = (ReporteMAVPersonalVentasForm) this.formReporte;		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		if (StringUtils.equals(StringUtils.substring(f.getFormatoExportacion(), 0, 1), "V"))
			tipoReporte = "PDF";
		else
			tipoReporte =  f.getFormatoExportacion();
		
		String descripcionActividad = "";
		for (int i=0; i < f.getCodigoActividad().length; i++) {
			String codigoActividad =  f.getCodigoActividad()[i].equals("Todos")?"0":f.getCodigoActividad()[i];
			for (int j=0; j < this.siccActividadList.size(); j++) {
				BaseOID base = (BaseOID) this.siccActividadList.get(j);
				if (base.getOid().intValue() == new Integer( codigoActividad).intValue()) {
					descripcionActividad = descripcionActividad + base.getDescripcion() + "\n";
					break;
				}
			}
		}
		f.setDescripcionActividad(descripcionActividad);
		
		String condicionActividad = obtieneCondicion(f.getCodigoActividad(), "MAV_ACTIV.OID_ACTI", "");
		String titulo = "MATERIAL DE CAMPAŃA";
			titulo = titulo
					+ "\n RELACION DE PRODUCTOS MAV ENVIADOS AL PERSONAL DE VENTAS";

		Map criteria = params;
		
		String oidPais =reporteService.getOidString("getOidPaisByCodigoPais",criteria);
		criteria.put("codigoPeriodo",f.getCodigoPeriodo());
		String oidPeriodo =reporteService.getOidString("getOidPeriodoByCodigoPeriodo",criteria);
		
		String nFecha=DateUtil.convertDateToString(f.getFechaReporteD());
		f.setFechaReporte(nFecha);
		
		params.put("NroReporte", "");
		params.put("titulo", titulo);
		params.put("oidPeriodo", oidPeriodo);
		params.put("oidPais", oidPais);
		params.put("condicion", condicionActividad);
		params.put("fechaReporte", nFecha);
		params.put("descripcionActividad", descripcionActividad);
		return params;
	}


	public String getTipoReporte() {
		return tipoReporte;
	}


	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}


	public List getSiccActividadList() {
		return siccActividadList;
	}


	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}


	
}
