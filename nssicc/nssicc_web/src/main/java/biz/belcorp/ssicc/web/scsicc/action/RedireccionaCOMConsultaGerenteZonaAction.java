package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseRedirecionaAbstractAction;

@ManagedBean
@SessionScoped
public class RedireccionaCOMConsultaGerenteZonaAction extends BaseRedirecionaAbstractAction{

	private static final long serialVersionUID = 1L;

	@Override
	protected String setRedireccionarAtributes() throws Exception {
		GenericoService genericoService = (GenericoService) getBean("genericoService");
        ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		parametroPais.setCodigoSistema(Constants.COM_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.INDICADOR_CONSULTA_COMISION_GERENTE_ZONA);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		List lstParametros = genericoService.getParametrosPais(parametroPais);
		
		ParametroPais parametro = null;
		if(CollectionUtils.size(lstParametros)==1){
			parametro = (ParametroPais) lstParametros.get(0);
			if(StringUtils.equals(parametro.getValorParametro(), "01")){
				return "consultaCOMComisionGerenteZonaList";
			}
			if(StringUtils.equals(parametro.getValorParametro(), "02")){
				return "consultaCOMComisionGerenteZonaEscalonadaList";
			}		
			if(StringUtils.equals(parametro.getValorParametro(), "03")){
				return "/pages/spusicc/comision/procesoCOMComisionGerenteZonaForm";
			}
		}
		return null;
	}


}
