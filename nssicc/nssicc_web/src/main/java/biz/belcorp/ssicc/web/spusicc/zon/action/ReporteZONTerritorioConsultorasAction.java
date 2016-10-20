package biz.belcorp.ssicc.web.spusicc.zon.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesAdministrativasService;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONActualizarUnidadesGeograficasService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.form.ReporteZONTerritorioConsultorasForm;
import biz.belcorp.ssicc.web.spusicc.zon.form.ProcesoZONModificarTerritorioConsultorasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked","rawtypes"})
public class ReporteZONTerritorioConsultorasAction extends BaseReporteAbstractAction {
	private static final long serialVersionUID = 1L;
	private String formatoReporte;
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteZONTerritorioConsultorasForm f=new ReporteZONTerritorioConsultorasForm();
		return f;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return "reporteZONTerritorioConsultoraXLS";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return "reporteZONTerritorioConsultoraXLS";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		String formato= "XLS";
		this.setFormatoReporte(formato);
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
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

}
