package biz.belcorp.ssicc.service.sisicc.framework.formatter.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.NormaInterfaz;
import biz.belcorp.ssicc.dao.sisicc.model.TipoFormatoArchivo;
import biz.belcorp.ssicc.service.DelimitadorService;
import biz.belcorp.ssicc.service.EstructuraArchivoService;
import biz.belcorp.ssicc.service.FormatoService;
import biz.belcorp.ssicc.service.NormaInterfazService;
import biz.belcorp.ssicc.service.TipoFormatoArchivoService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.formatter.InterfazFormatServiceFacade;

/**
 * @author pecbazalar
 *
 */
@Service("sisicc.interfazFormatServiceFacade")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazFormatServiceFacadeImpl implements
		InterfazFormatServiceFacade {
	protected final Log log = LogFactory.getLog(getClass());
	
	@Resource(name="sisicc.delimitadorService")
	private DelimitadorService delimitadorService;

	@Resource(name="sisicc.estructuraArchivoService")
	private EstructuraArchivoService estructuraArchivoService;

	@Resource(name="sisicc.formatoService")
	private FormatoService formatoService;

	@Resource(name="sisicc.tipoFormatoArchivoService")
	private TipoFormatoArchivoService tipoFormatoArchivoService;

	@Resource(name="sisicc.normaInterfazService")
	private NormaInterfazService normaInterfazService;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.formatter.InterfazFormatServiceFacade#getInterfazFormat(biz.belcorp.ssicc.dao.sisicc.model.Interfaz)
	 */
	public InterfazFormat getInterfazFormat(Interfaz interfaz) {
		log.debug("Entering 'getInterfazFormatter' method");
		InterfazFormat interfazFormatter = new InterfazFormat();
		InterfazPK interfazPK = new InterfazPK(interfaz.getCodigoPais(),
				interfaz.getCodigoSistema(), interfaz.getCodigo());

		interfazFormatter.setInterfaz(interfaz);
		interfazFormatter.setDelimitador(delimitadorService
				.getDelimitador(interfaz.getCodigoDelimitador()));
		interfazFormatter.setEstructurasArchivo(estructuraArchivoService
				.getEstructuraArchivo(interfazPK));
		interfazFormatter.setFormato(formatoService.getFormato(interfaz
				.getCodigoFormato()));
		interfazFormatter.setNormaInterfaz(getNormaInterfaz(interfaz));
		return interfazFormatter;
	}

	/**
	 * @param interfaz
	 * @return
	 */
	private NormaInterfaz getNormaInterfaz(Interfaz interfaz) {
		TipoFormatoArchivo tipoFormatoArchivo = tipoFormatoArchivoService
				.getTipoFormatoArchivo(interfaz.getTipoFormatoArchivo());
		NormaInterfaz criteriaNorma = new NormaInterfaz();
		criteriaNorma.setCodigoPais(interfaz.getCodigoPais());
		criteriaNorma.setCodigoTipoFormatoArchivo(tipoFormatoArchivo
				.getCodigo());
		return normaInterfazService.getNormaInterfazByCriteria(criteriaNorma);
	}

	
}
