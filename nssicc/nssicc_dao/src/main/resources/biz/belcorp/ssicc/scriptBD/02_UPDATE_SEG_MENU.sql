UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/datosbasicos/mantenimientoBASSistemaList.xhtml'
WHERE X.COD_MENU = '10010100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/datosbasicos/mantenimientoBASConfiguracionInterfazList.xhtml'
WHERE X.COD_MENU = '10010200';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/datosbasicos/mantenimientoBASNormaFormatoContenidoInterfazList.xhtml'
WHERE X.COD_MENU = '10010300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/ventas/mantenimientoBASFeriadoList.xhtml'
WHERE X.COD_MENU = '10010400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/ventas/mantenimientoBASFeriadoZonaList.xhtml'
WHERE X.COD_MENU = '10010500';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/ventas/mantenimientoVENGrupoZonaList.xhtml'
WHERE X.COD_MENU = '10010600';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/ventas/mantenimientoVENPorcentajeIgvList.xhtml'
WHERE X.COD_MENU = '10010700';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoOCRActualizacionMatrizFacturacionForm.xhtml'
WHERE X.COD_MENU = '20010118';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/consultaOCRRecepcionPedidosForm.xhtml'
WHERE X.COD_MENU = '20010120';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoOCRActualizacionGruposProcesoForm.xhtml'
WHERE X.COD_MENU = '20010121';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/procesoOCRCalculoDeudaForm.xhtml'
WHERE X.COD_MENU = '30010602';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/mantenimientoCOMOrdenEstadisticoList.xhtml'
WHERE X.COD_MENU = '20010723';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/mantenimientoCOMResponsablesUAList.xhtml'
WHERE X.COD_MENU = '20010740';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/mantenimientoCOMZonasDemandaAnticipadaList.xhtml'
WHERE X.COD_MENU = '20010746';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/mantenimientoCOMMinimoNuevasList.xhtml'
WHERE X.COD_MENU = '20010747';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cobranzas/mantenimientoCOBCronogramaCarteraList.xhtml'
WHERE X.COD_MENU = '20010907';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/mantenimientoCOMBonosList.xhtml'
WHERE X.COD_MENU = '20010726';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mantenimientoDATEstimadosList.xhtml'
WHERE X.COD_MENU = '20010303';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaPREMatrizFacturacionForm.xhtml'
WHERE X.COD_MENU = '20260800';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/men/mantenimientoMENPlantillaConfiguracionProcesosList.xhtml'
WHERE X.COD_MENU = '20250300';

/* PROCESOS DE USUARIOS - GESTION DE PEDIDOS DIGITADOS*/
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoOCRCapturaPedidosForm.xhtml'
WHERE X.COD_MENU = '20200100';

/* PROCESOS DE USUARIOS - RECLAMOS */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoRECOperacionesReclamoList.xhtml'
WHERE X.COD_MENU = '20083700'; 

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/procesoRECAnulaMasivaBorecForm.xhtml'
WHERE X.COD_MENU = '20083400'; 

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/procesoRECCierreBRForm.xhtml'
WHERE X.COD_MENU = '20082700';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/procesoRECEnviarCDRRecepcionadosForm.xhtml'
WHERE X.COD_MENU = '20083500';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoRECDigitacionCDRForm.xhtml'
WHERE X.COD_MENU = '20081500';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoRECDigitacionCDRForm.xhtml'
WHERE X.COD_MENU = '20080600';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/procesoRECEnvioReclamosDigitadosForm.xhtml'
WHERE X.COD_MENU = '20080700';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/mantenimientoZONPerfilList.xhtml'
WHERE X.COD_MENU = '20072500';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/hip/consultaHIPDatosClienteForm.xhtml'
WHERE X.COD_MENU = '40023401';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/reclamos/procesoRECEjecucionMensajesReclamosForm.xhtml'
WHERE X.COD_MENU = '20080500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/reclamos/procesoRECBloqueoCDRForm.xhtml'
WHERE X.COD_MENU = '20082300';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/men/mantenimientoMENPatronMensajeList.xhtml'
WHERE X.COD_MENU = '20250900';

/* PROCESOS BACHT */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/procesoCOMComisionRecuperacionForm.xhtml'
WHERE X.COD_MENU = '20010708';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/procesoCOMActualizacionEstatusEjecutivasForm.xhtml'
WHERE X.COD_MENU = '20010741';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/procesoCOMEliminarComisionRecuperacionForm.xhtml'
WHERE X.COD_MENU = '20010742';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/gen/procesoGENActualizacionDatosForm.xhtml'
WHERE X.COD_MENU = '30010609';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/flexipago/procesoFLXRecepcionArchivosWebManualForm.xhtml'
WHERE X.COD_MENU = '30010614';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scdf/cierreProcesosDiariosForm.xhtml'
WHERE X.COD_MENU = '30010601';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/edu/procesoEDUCierreFacturacionDiarioList.xhtml'
WHERE X.COD_MENU = '30010608';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/edu/procesoEDURecepcionarMaestrosForm.xhtml'
WHERE X.COD_MENU = '30011802';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/ventas/procesoVENGenerarHistoricoVentaCatalogoForm.xhtml'
WHERE X.COD_MENU = '30010606';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/gen/procesoGENEliminarBuzonMensajeForm.xhtml'
WHERE X.COD_MENU = '30010610';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/gen/procesoGENEliminarBuzonMensajeForm.xhtml'
WHERE X.COD_MENU = '30010613';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lideres/procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm.xhtml'
WHERE X.COD_MENU = '30011230';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lideres/procesoLIDEvaluarRecomendaciones2PedidosCierrePeriodoForm.xhtml'
WHERE X.COD_MENU = '30010703';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/zon/procesoZONRecepcionarMaestrosForm.xhtml'
WHERE X.COD_MENU = '20072202';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/love/procesoLOVEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30010708';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/procesoINCEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30010709';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/inc/procesoINCCargaPuntajeBonificadoForm.xhtml'
WHERE X.COD_MENU = '20230200';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/edu/procesoEDUCierreProcesosCampannaList.xhtml'
WHERE X.COD_MENU = '30010710';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/procesoCUPCierrePeriodoForm.xhtml'
WHERE X.COD_MENU = '30010712';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sgr/procesoSGRCancelarPolizasForm.xhtml'
WHERE X.COD_MENU = '30010721';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/lideres/procesoLIDGenerarInformacionLideresCierrePeriodoForm.xhtml'
WHERE X.COD_MENU = '30010702';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCOMEnviarLalnForm.xhtml'
WHERE X.COD_MENU = '20010704';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazSICEnviarMaestrosForm.xhtml'
WHERE X.COD_MENU = '30021906';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazFACRecepcionarDocumentoElectronicoForm.xhtml'
WHERE X.COD_MENU = '30012201';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCOMRecepcionarCodigosPlanillaForm.xhtml'
WHERE X.COD_MENU = '20010705';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCOMEnviarPagoLideresForm.xhtml'
WHERE X.COD_MENU = '20010706';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/procesoCOMComisionIngresoForm.xhtml'
WHERE X.COD_MENU = '20010709';

/* INTERFACES */

/* OCR */ 

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazWEBRecepcionarFamiliaSeguraTelemercadoForm.xhtml'
WHERE X.COD_MENU = '30020563';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/procesoOCREnviarDetallesPedidosDigitadosForm.xhtml'
WHERE X.COD_MENU = '30020509';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarBoletaRecojoForm.xhtml'
WHERE X.COD_MENU = '30020561';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarBoletaEntregaForm.xhtml'
WHERE X.COD_MENU = '30020560';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarCartaInvitacionFlexipagoForm.xhtml'
WHERE X.COD_MENU = '30020559';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarContratoEjecutivaDesarrolladoraForm.xhtml'
WHERE X.COD_MENU = '30020558';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazWEBRecepcionarFamiliaSeguraForm.xhtml'
WHERE X.COD_MENU = '30020556';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRPaqueteConsolidadoForm.xhtml'
WHERE X.COD_MENU = '30020555';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarFamiliaSeguraForm.xhtml'
WHERE X.COD_MENU = '30020554';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRProcesarConsolidadoOCSForm.xhtml'
WHERE X.COD_MENU = '30020503';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCREnviarArchivosOCRForm.xhtml'
WHERE X.COD_MENU = '30020511';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCREnviarClientesWebForm.xhtml'
WHERE X.COD_MENU = '30020541';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarActualizacionDatosWebForm.xhtml'
WHERE X.COD_MENU = '30020538';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarOrdenTransporteForm.xhtml'
WHERE X.COD_MENU = '30020550';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCREnviarOrdenesTransporteForm.xhtml'
WHERE X.COD_MENU = '30020551';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazOCRRecepcionarDuplaCyzoneForm.xhtml'
WHERE X.COD_MENU = '30020533';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRetornoCodigosAsignadosForm.xhtml'
WHERE X.COD_MENU = '30020552';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarIngresoMetasForm.xhtml'
WHERE X.COD_MENU = '30020553';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazOCRRecepcionarCuponPagoForm.xhtml'
WHERE X.COD_MENU = '30020534';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazOCRRecepcionarSolicitudCreditoCorporativaForm.xhtml'
WHERE X.COD_MENU = '30020535';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarCodigosVentaRechazadosForm.xhtml'
WHERE X.COD_MENU = '30020549';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazOCRRecepcionarSolicitudCreditoMobilForm.xhtml'
WHERE X.COD_MENU = '30020562';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazOCRRecepcionarSolicitudCreditoWebForm.xhtml'
WHERE X.COD_MENU = '30020536';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazOCRRecepcionarActualizacionDatosCorporativaForm.xhtml'
WHERE X.COD_MENU = '30020537';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazSABInterfacesFuenteVentasRealForm.xhtml'
WHERE X.COD_MENU = '30020704';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazDATEnviarAdministracionFlujosForm.xhtml'
WHERE X.COD_MENU = '30021302';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazIVREnviarCompletoIVRCorporativoForm.xhtml'
WHERE X.COD_MENU = '30022309';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazAVIEnviarAsistenteVirtualNovedadesForm.xhtml'
WHERE X.COD_MENU = '30022702';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazAPEEnviarBoletasRecojoForm.xhtml'
WHERE X.COD_MENU = '30023306';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMLBEnviarInformacionIncentivosForm.xhtml'
WHERE X.COD_MENU = '30023701';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazSGREnviarInformacionPolizaForm.xhtml'
WHERE X.COD_MENU = '30024101';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarFacturasCompleVentaDirectaForm.xhtml'
WHERE X.COD_MENU = '30020306';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazREUEnviarMaestrosForm.xhtml'
WHERE X.COD_MENU = '30020402';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazREUEnviarConsultorasForm.xhtml'
WHERE X.COD_MENU = '30020401';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMLBEnviarInformacionIncentivosForm.xhtml'
WHERE X.COD_MENU = '30023701';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarFacturasCompleVentaDirectaForm.xhtml'
WHERE X.COD_MENU = '30020306';


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazREUEnviarMaestrosForm.xhtml'
WHERE X.COD_MENU = '30020402';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazREUEnviarConsultorasForm.xhtml'
WHERE X.COD_MENU = '30020401';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarDetalleFacturasVDForm.xhtml'
WHERE X.COD_MENU = '30020309';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarPagoComisionesRetailGZForm.xhtml'
WHERE X.COD_MENU = '30020308';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarFacturasVentaDirectaForm.xhtml'
WHERE X.COD_MENU = '30020302';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazIVREnviarCompletoIVRForm.xhtml'
WHERE X.COD_MENU = '30022302';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarMatrizCampanyaForm.xhtml'
WHERE X.COD_MENU = '30020301';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarDetalleFacturasVDForm.xhtml'
WHERE X.COD_MENU = '30020309';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarMatrizCampanyaForm.xhtml'
WHERE X.COD_MENU = '30020301';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarDetalleFacturasVDForm.xhtml'
WHERE X.COD_MENU = '30020309';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarFacturasVentaDirectaForm.xhtml'
WHERE X.COD_MENU = '30020302';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazIVREnviarInterfaceDiariaForm.xhtml'
WHERE X.COD_MENU = '30022301';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazIVREnviarCompletoIVRForm.xhtml'
WHERE X.COD_MENU = '30022302';

UPDATE SEG_MENU X

SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarMatrizCampanyaForm.xhtml'
WHERE X.COD_MENU = '30020301';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarComisionesForm.xhtml'
WHERE X.COD_MENU = '30020305';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazIVREnviarCompletoIVRForm.xhtml'
WHERE X.COD_MENU = '30022302';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarMatrizCampanyaForm.xhtml'
WHERE X.COD_MENU = '30020301';

SET X.PAG_XML= '/pages/sisicc/interfazIVREnviarInterfaceDiariaForm.xhtml'
WHERE X.COD_MENU = '30022301';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazIVREnviarNovedadIVRForm.xhtml'
WHERE X.COD_MENU = '30022303';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCOMEnviarCargaMasivaEjecutivaNuevaForm.xhtml'
WHERE X.COD_MENU = '30022801';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazAPEEnviarEstimadosDistribucionDAForm.xhtml'
WHERE X.COD_MENU = '30023307';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazLARRecepcionarActualizaResultadoChequeoForm.xhtml'
WHERE X.COD_MENU = '30021103';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazLARRecepcionarActualizaSecuenciacionzonasForm.xhtml'
WHERE X.COD_MENU = '30021104';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazLARRecepcionarEstimadosLeaderForm.xhtml'
WHERE X.COD_MENU = '30021108';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazLARGenerarLARFacturacionElectronicaForm.xhtml'
WHERE X.COD_MENU = '30021110';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazXRXNotaCreditoElectronicaForm.xhtml'
WHERE X.COD_MENU = '30024302';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMAERecibirInformacionForm.xhtml'
WHERE X.COD_MENU = '30024902';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazPRIEnviarCalificacionForm.xhtml'
WHERE X.COD_MENU = '30010701';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPRIEnviarArchivosPrivilegeForm.xhtml'
WHERE X.COD_MENU = '30010501';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPEDEnviarPedidosChequearForm.xhtml'
WHERE X.COD_MENU = '30010504';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazLARRecepcionarResultadoChequeoForm.xhtml'
WHERE X.COD_MENU = '30010505';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/edu/interfazDATEnviarArchivosEducacionForm.xhtml'
WHERE X.COD_MENU = '30010711';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/edu/interfazDATEnviarArchivosEducacionForm.xhtml'
WHERE X.COD_MENU = '30010713';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/flexipago/procesoFLXProcesosCierreCampaniaForm.xhtml'
WHERE X.COD_MENU = '30010735';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/flexipago/procesoFLXProcesosCierreCampaniaForm.xhtml'
WHERE X.COD_MENU = '30010736';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/generacionDetallePedidoForm.xhtml'
WHERE X.COD_MENU = '30010803';


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoOCRProcesosEspecialesOCSForm.xhtml'
WHERE X.COD_MENU = '20010105';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/procesoOCRActualizaPedidosDeudaMasivaList.xhtml'
WHERE X.COD_MENU = '20010106';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/procesoOCRActualizaPedidosBloqueoMasivoForm.xhtml'
WHERE X.COD_MENU = '20010107';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPEDAsignacionStockForm.xhtml'
WHERE X.COD_MENU = '30010304';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPEDAsignarCodigoVentaForm.xhtml'
WHERE X.COD_MENU = '20261800';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazSAMRecepcionarStockDiarioForm.xhtml'
WHERE X.COD_MENU = '30010305';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarOCSDemandaWebDDForm.xhtml'
WHERE X.COD_MENU = '30020540';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazPEDPaqueteProcesoPedidoForm.xhtml'
WHERE X.COD_MENU = '30010308';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazPRIEnviarArchivosOCRForm.xhtml'
WHERE X.COD_MENU = '30010401';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scdf/procesoPRIGenerarSolicitudesPrivilegeForm.xhtml'
WHERE X.COD_MENU = '30010404';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/procesoINCEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30010403';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/men/procesoMENGeneracionMensajesForm.xhtml'
WHERE X.COD_MENU = '30010405';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/procesoCCCCargarLotesBancariosForm.xhtml'
WHERE X.COD_MENU = '30021714';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCCondonacionDeudasCastigadasList.xhtml'
WHERE X.COD_MENU = '20220052';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazGEOEnviarZonaTerritorioClienteForm.xhtml'
WHERE X.COD_MENU = '30020101';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazADARecepcionarMovimientosDescuentosPersonalForm.xhtml'
WHERE X.COD_MENU = '30021601';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarServiciosPostventasForm.xhtml'
WHERE X.COD_MENU = '30020529';

/* MY EBEL */ 
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMYEEnviarPercepcionesVentaDirectaForm.xhtml'
WHERE X.COD_MENU = '30020204';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMYEEnviarMaestrosForm.xhtml'
WHERE X.COD_MENU = '30020205';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMYEEnviarArchivoPremiosForm.xhtml'
WHERE X.COD_MENU = '30020209';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMYEEnviarTipoProductoCatalogoForm.xhtml'
WHERE X.COD_MENU = '30020211';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMYEEnviarFaltantesAnunciadosForm.xhtml'
WHERE X.COD_MENU = '30020212';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMYERecepcionarActivacionFlexipagoWebForm.xhtml'
WHERE X.COD_MENU = '30020215';

/* LET */ 
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazLETModuloCaribeForm.xhtml'
WHERE X.COD_MENU = '30025100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazLETEnviarTarjetasAsociadasForm.xhtml'
WHERE X.COD_MENU = '30025300';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazLETEnviarPagosForm.xhtml'
WHERE X.COD_MENU = '30025200';

/* SAP-MM */ 
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAMEnviarInicializacionStocksForm.xhtml'
WHERE X.COD_MENU = '30020601';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAMEnviarMovimientosAlmacenForm.xhtml'
WHERE X.COD_MENU = '30020602';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAMEnviarMovimientosAlmacenSiccForm.xhtml'
WHERE X.COD_MENU = '30020604';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAMEnviarReservaPROLForm.xhtml'
WHERE X.COD_MENU = '30020605';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAMEnviarCantidadProductoForm.xhtml'
WHERE X.COD_MENU = '30020606';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazAPEEnviarCostoCajaForm.xhtml'
WHERE X.COD_MENU = '30022502';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazACCEnviarTablasClientesForm.xhtml'
WHERE X.COD_MENU = '30023101';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCYZEnviarDespachoProductosForm.xhtml'
WHERE X.COD_MENU = '30023201';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm.xhtml'
WHERE X.COD_MENU = '30022802';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETEnviarInformacionRetailForm.xhtml'
WHERE X.COD_MENU = '30020310';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazFLXEnvioConsultorasObjetadasForm.xhtml'
WHERE X.COD_MENU = '30024203';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazEMPEnvioVariablesForm.xhtml'
WHERE X.COD_MENU = '30024601';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazMAEEnviarConsultorasBloqueadasDesbloquedasForm.xhtml'
WHERE X.COD_MENU = '30024901';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETRecepcionarVentasRetailForm.xhtml'
WHERE X.COD_MENU = '30020303';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRETRecepcionarVentasRetailCabDetForm.xhtml'
WHERE X.COD_MENU = '30020307';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazBELEnviarInterfaceDiariaForm.xhtml'
WHERE X.COD_MENU = '30020807';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRProcesarConsolidadoOCSForm.xhtml'
WHERE X.COD_MENU = '30020503';


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRProcesarConsolidadoOCSForm.xhtml'
WHERE X.COD_MENU = '30020503';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarActualizacionDatosCorporativaForm.xhtml'
WHERE X.COD_MENU = '30020537';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarOCSWebDDForm.xhtml'
WHERE X.COD_MENU = '30020532';

update seg_menu x
   set x.pag_xml = '/pages/sisicc/interfazOCRRecepcionarServiciosPostventasForm.xhtml'
 where x.cod_menu = '30020539';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazAPEPedidosDespachadosForm.xhtml'
WHERE X.COD_MENU = '30023310';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ruv/reporteRUVDocumentosContablesForm.xhtml'
WHERE X.COD_MENU = '30023403';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIPagoLetForm.xhtml'
WHERE X.COD_MENU = '30023507';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIReprocesoCorporativoForm.xhtml'
WHERE X.COD_MENU = '30023505';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazYOBCargaLotesTrazabilidadForm.xhtml'
WHERE X.COD_MENU = '30021111';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ruv/reporteRUVDocumentosContablesForm.xhtml'
WHERE X.COD_MENU = '30023403';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/procesoINCEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30010303';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIPagoLetForm.xhtml'
WHERE X.COD_MENU = '30023507';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/gen/procesoGENProcesarGP3Form.xhtml'
WHERE X.COD_MENU = '30010306';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIReprocesoCorporativoForm.xhtml'
WHERE X.COD_MENU = '30023505';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ruv/reporteRUVDocumentosContablesForm.xhtml'
WHERE X.COD_MENU = '30023403';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIPagoLetForm.xhtml'
WHERE X.COD_MENU = '30023507';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIReprocesoCorporativoForm.xhtml'
WHERE X.COD_MENU = '30023505';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ruv/reporteRUVDocumentosContablesForm.xhtml'
WHERE X.COD_MENU = '30023403';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIPagoLetForm.xhtml'
WHERE X.COD_MENU = '30023507';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIReprocesoCorporativoForm.xhtml'
WHERE X.COD_MENU = '30023505';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ruv/reporteRUVDocumentosContablesForm.xhtml'
WHERE X.COD_MENU = '30023403';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIPagoLetForm.xhtml'
WHERE X.COD_MENU = '30023507';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIReprocesoCorporativoForm.xhtml'
WHERE X.COD_MENU = '30023505';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazYOBCargaLotesTrazabilidadForm.xhtml'
WHERE X.COD_MENU = '30021111';


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarActualizacionDatosCorporativaForm.xhtml'
WHERE X.COD_MENU = '30020537';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazOCRRecepcionarOCSWebDDForm.xhtml'
WHERE X.COD_MENU = '30020532';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazYOBCargaLotesTrazabilidadForm.xhtml'
WHERE X.COD_MENU = '30021111';

/* APE GENEXUS */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazAPEEnviarEstimadosDistribucionForm.xhtml'
WHERE X.COD_MENU = '30023302';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazAPERecepcionarChequeoForm.xhtml'
WHERE X.COD_MENU = '30023304';

/* Ruv */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazRUVEnviarRegistroUnicoVentasForm.xhtml'
WHERE X.COD_MENU = '30023401';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazHIPRecepcionarRegistroVentasForm.xhtml'
WHERE X.COD_MENU = '30023405';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/procesoRUVReprocesarInterfaseForm.xhtml'
WHERE X.COD_MENU = '30023404';

/* SAP-BPS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSABEnviarFuenteVentasPrevistaForm.xhtml'
WHERE X.COD_MENU = '30020701';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSABEnviarRentabilidadPorZonaForm.xhtml'
WHERE X.COD_MENU = '30020702';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSABEnviarIncentivosConsultorasForm.xhtml'
WHERE X.COD_MENU = '30020703';


/* GIS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazGISEnviarDireccionConsultorasForm.xhtml'
WHERE X.COD_MENU = '30020901';


/* PERCEPCIONES */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPEREnviarSolicitudesMonetariasForm.xhtml'
WHERE X.COD_MENU = '30021002';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPERRecepcionarConsolidadosOtrosCanalesForm.xhtml'
WHERE X.COD_MENU = '30021001';


/* REUTILIZACION */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazREUEnviarCronogramaFacturacionForm.xhtml'
WHERE X.COD_MENU = '30020406';


/* LEADER LIST */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazLLIRecepcionarActualizaEstimadosYobelForm.xhtml'
WHERE X.COD_MENU = '30022203'

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazLLIEnviarVentaRealDiariaAcumuladaForm.xhtml'
WHERE X.COD_MENU = '30022202'

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCOMEnviarFicheroPagoComisionEjecutivaForm.xhtml'
WHERE X.COD_MENU = '30022803';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazREUEnviarDocumentosAnuladosForm.xhtml'
WHERE X.COD_MENU = '30020404';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazLLIRecepcionarCargaProductosPlanitForm.xhtml'
WHERE X.COD_MENU = '30022204'


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCOMEnviarFicheroPagoComisionEjecutivaForm.xhtml'
WHERE X.COD_MENU = '30022803';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazREUEnviarDocumentosAnuladosForm.xhtml'
WHERE X.COD_MENU = '30020404';

/* FLEXIPAGO */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazFLXRecepcionarConsultorasComunicacionForm.xhtml'
WHERE X.COD_MENU = '30024202';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazFLXRecepcionarConsultorasHabilesForm.xhtml'
WHERE X.COD_MENU = '30024201';

/* SEGUROS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSGRRecepcionarPolizasCanceladasForm.xhtml'
WHERE X.COD_MENU = '30024102';

/* SMS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSMSEnviarConsultorasPedidosForm.xhtml'
WHERE X.COD_MENU = '30023801';

/* ECOMMERCE */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazECMRecepcionarMovimientosAbonosConsultoraForm.xhtml'
WHERE X.COD_MENU = '30021901';

/* SAT */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarDivisionArmadoCDPForm.xhtml'
WHERE X.COD_MENU = '30022519';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarOrdenImpresionAPESATForm.xhtml'
WHERE X.COD_MENU = '30022521';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarExcepcionesFechaEntregaExactaForm.xhtml'
WHERE X.COD_MENU = '30022523';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarImpresionBoletasEntregaForm.xhtml'
WHERE X.COD_MENU = '30022525';

/* AVI */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazAVIRecepcionarLogrosForm.xhtml'
WHERE X.COD_MENU = '30022703';

/* CYZONE */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCYZRecepcionarProductosSolicitadosForm.xhtml'
WHERE X.COD_MENU = '30023202';


/* XEROX */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazXRXBoletaVentaElectronicaForm.xhtml'
WHERE X.COD_MENU = '30024301';

/* SAP-FI */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAFEnviarComisionEjecutivaForm.xhtml'
WHERE X.COD_MENU = '30023504';

/* SOA */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazOCREnviarArchivosOCRForm.xhtml'
WHERE X.COD_MENU = '30024501';

/* PROCESOS */

/* PROCESOS DE IMPRESION */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazIMPEnviarDocumentosElectronicosForm.xhtml'
WHERE X.COD_MENU = '30010809';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazIMPEnviarDocumentosMatricialesForm.xhtml'
WHERE X.COD_MENU = '30010804';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spisicc/procesoIMPGeneracionSpoolLaserMultihiloForm.xhtml'
WHERE X.COD_MENU = '30010808';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spisicc/procesoIMPGeneracionSpoolLaserMultihiloForm.xhtml'
WHERE X.COD_MENU = '30010811';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spisicc/procesoIMPGeneracionDocumentosLaserColorForm.xhtml'
WHERE X.COD_MENU = '30010807';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPRIEnviarArchivoImpresionStickersForm.xhtml'
WHERE X.COD_MENU = '30010801';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spisicc/mantenimientoIMPParametroProcesoImpresionList.xhtml'
WHERE X.COD_MENU = '20430400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/mantenimientoINCConfiguracionConcursoList.xhtml'
WHERE X.COD_MENU = '20231200';

/* PERCEPCIONES */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/procesoPERCruceSaldoPositivoNegativoForm.xhtml'
WHERE X.COD_MENU = '30010904';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/percepciones/procesoPERGenerarCuentaCorrienteDocumentoLegalForm.xhtml'
WHERE X.COD_MENU = '30010905';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/procesoPERCargaVentaDirectaForm.xhtml'
WHERE X.COD_MENU = '20010803';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/percepciones/procesoPERCargarPagosBancariosMasivosUpload.xhtml'
WHERE X.COD_MENU = '20010805';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mantenimientoPERPercepcionesOtrosCanalesList.xhtml'
WHERE X.COD_MENU = '20010802';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mantenimientoPERMovimientosBancariosList.xhtml'
WHERE X.COD_MENU = '20010801';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mantenimientoPERNumeracionComprobantesSunatList.xhtml'
WHERE X.COD_MENU = '20010804';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/mantenimientoSTOIngresoCuponForm.xhtml'
WHERE X.COD_MENU = '20050900';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTORolSearchForm.xhtml'
WHERE X.COD_MENU = '20050800';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTOParametroValidacionList.xhtml'
WHERE X.COD_MENU = '20053100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/lideres/mantenimientoLIDCondicionDespachoCanastaForm.xhtml'
WHERE X.COD_MENU = '20020500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lideres/mantenimientoLIDObjetivosPorVariableAsignacionPuntajeList.xhtml'
WHERE X.COD_MENU = '20020300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/mantenimientoCOMPorcentajeComisionList.xhtml'
WHERE X.COD_MENU = '20010736';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/mantenimientoCOMCalificacionComisionList.xhtml'
WHERE X.COD_MENU = '20010717';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoOCRPreProductosICEList.xhtml'
WHERE X.COD_MENU = '20260300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDConfiguracionOfertasPorConcursosList.xhtml'
WHERE X.COD_MENU = '20261300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoOCRReemplazosList.xhtml'
WHERE X.COD_MENU = '20260600';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mantenimientoPRECambioCodigoVentaList.xhtml'
WHERE X.COD_MENU = '20260400';


/* CUENTA CORRIENTE */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/mantenimientoCCCFacturarInteresList.xhtml'
WHERE X.COD_MENU = '20220051';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCActualizarSaldosSeguimientoLevantamientosForm.xhtml'
WHERE X.COD_MENU = '20220011';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCCierreFacturacionForm.xhtml'
WHERE X.COD_MENU = '30011603';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCGenerarInformacionDatamartForm.xhtml'
WHERE X.COD_MENU = '30021715';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCCargarDeudasWebForm.xhtml'
WHERE X.COD_MENU = '30021719';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCCargarArchivoNominaForm.xhtml'
WHERE X.COD_MENU = '30021722';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/procesoCCCGenerarInformacionSAPFIForm.xhtml'
WHERE X.COD_MENU = '30021712';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/procesoCCCRecepcionarPagosWebForm.xhtml'
WHERE X.COD_MENU = '30021718';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/procesoCCCGeneracionArchivosMorosasForm.xhtml'
WHERE X.COD_MENU = '30021721';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazCCCRecepcionarMovimientosBancariosForm.xhtml'
WHERE X.COD_MENU = '30021701';

/* VENTAS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ventas/procesoVENCalculoRecuperacionCobranzaForm.xhtml'
WHERE X.COD_MENU = '30011001';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENCabecerasFacturasAnuladasForm.xhtml'
WHERE X.COD_MENU = '40021225';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENDetalleNCMarcaUNForm.xhtml'
WHERE X.COD_MENU = '40021224';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENDetalleProductosAtendidosUNForm.xhtml'
WHERE X.COD_MENU = '40021226';

/* DIRECTORIO DE VENTAS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/zon/procesoZONEnviarEntidadesDirectorioForm.xhtml'
WHERE X.COD_MENU = '30024802';

/*  PROCESOS DE USUARIO */

/*  Comision */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/consultaCOMComisionPerdidasList.xhtml'
WHERE X.COD_MENU = '20010730';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/retail/procesoRETAsignacionVentasRetailForm.xhtml'
WHERE X.COD_MENU = '20010732';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/retail/procesoRETReasignacionGerenteZonaForm.xhtml'
WHERE X.COD_MENU = '20010733';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/retail/procesoRETCalculoComisionRetailForm.xhtml'
WHERE X.COD_MENU = '20010734';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/retail/mantenimientoRETPorcentajeComisionList.xhtml'
WHERE X.COD_MENU = '20010735';

/*  Cobranzas */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/procesoCOBAsignacionCarteraForm.xhtml'
WHERE X.COD_MENU = '20010902';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/procesoCOBActualizarCarteraForm.xhtml'
WHERE X.COD_MENU = '20010901';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/procesoCOBAsignacionAutomaticaCarteraForm.xhtml'
WHERE X.COD_MENU = '20010910';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/procesoCOBRecuperacionCarteraFFVVForm.xhtml'
WHERE X.COD_MENU = '20011300';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/procesoCOBGeneracionReportesFFVVFTPForm.xhtml'
WHERE X.COD_MENU = '20012200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/procesoCOBGeneracionArchivosProveedoresFTPForm.xhtml'
WHERE X.COD_MENU = '20012400';

/*  Cronograma Actividades */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cronograma/mantenimientoCRAActividadList.xhtml'
WHERE X.COD_MENU = '20460200';

/* CONSULTAS Y REPORTES */
	/* RETAIL */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/inc/procesoINCConsultaOrdenesRetailForm.xhtml'
WHERE X.COD_MENU = '40023803';


/*  Reportes */

/**
 * Cobranzas
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOBDetalladoCobranza31DiasForm.xhtml'
WHERE X.COD_MENU = '40021524';

update seg_menu t
set t.pag_xml = '/pages/spusicc/cobranzas/procesoCOBCargarCronogramaUpload.xhtml'
where t.cod_menu in (20011400);

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/procesoCOBGenerarCronogramaForm.xhtml'
WHERE X.COD_MENU = '20010903';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOBReporteDetalladoCobranza21DiasForm.xhtml'
WHERE X.COD_MENU = '40021535';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBReporteRecuperacion31DiasForm.xhtml'
WHERE X.COD_MENU = '40021541';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBHistoricoOperacionCrediticiaForm.xhtml'
WHERE X.COD_MENU = '40021537';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBDetalladoCobranza42DiasForm.xhtml'
WHERE X.COD_MENU = '40021528';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBDetalladoRebajasCarteraForm.xhtml'
WHERE X.COD_MENU = '40021525';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBSaldosUltimoPedidoForm.xhtml'
WHERE X.COD_MENU = '40021516';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBConsolidadoCobranza31DiasForm.xhtml'
WHERE X.COD_MENU = '40021526';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBSaldosCampanaForm.xhtml'
WHERE X.COD_MENU = '40021502';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBPrimerSegundoTercerPedidoConDeudaForm.xhtml'
WHERE X.COD_MENU = '40021608';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBSaldosPendientesForm.xhtml'
WHERE X.COD_MENU = '40021501';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOBComisionAbogadosForm.xhtml'
WHERE X.COD_MENU = '40021603';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBPedidosFacturadosConDeudaForm.xhtml'
WHERE X.COD_MENU = '40021604';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBPrimerosSegundoPedidosConDeudaForm.xhtml'
WHERE X.COD_MENU = '40021607';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePEDBonificacionesForm.xhtml'
WHERE X.COD_MENU = '40024304';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERConsolidadoPagosBancariosPorFechaProcesoForm.xhtml'
WHERE X.COD_MENU = '40024302';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/consultaCOBCronogramaCarteraList.xhtml'
WHERE X.COD_MENU = '40021507';



UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/edu/consultaEDUStatusConsultoraList.xhtml'
WHERE X.COD_MENU = '40024606';


/**
 * SAP-FI
 */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSAPFiDescuentoVolumenForm.xhtml'
WHERE X.COD_MENU = '20415000';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSAPFiVentaLineaDevolucionForm.xhtml'
WHERE X.COD_MENU = '20415100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSAPFiDescuentoComercialForm.xhtml'
WHERE X.COD_MENU = '20415200';


/**
 * PAISES
 */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECConsolidadoCDRAprobadoForm.xhtml'
WHERE X.COD_MENU = '40023206';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECBoletaRecojoStatusForm.xhtml'
WHERE X.COD_MENU = '40023203';

/**
 * SAP-BPS
 */


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteFuenteVentasPrevistaForm.xhtml'
WHERE X.COD_MENU = '40020502';

/**
 * SAP-BPS PROCESOS
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/procesoSABCalculoFuenteVentasPrevistaForm.xhtml'
WHERE X.COD_MENU = '20010502';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/procesoSABCalculoFuenteVentasPrevistaForm.xhtml'
WHERE X.COD_MENU = '20010502';

/**
 * Comision
 */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMLideresNuevasForm.xhtml'
WHERE X.COD_MENU = '40020802';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMComisionComercializacionForm.xhtml'
WHERE X.COD_MENU = '40020807';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaCOMComisionGerenteZonaList.xhtml'
WHERE X.COD_MENU = '40020810';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMComisionRecuperacionEjecutivasForm.xhtml'
WHERE X.COD_MENU = '40020817';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaCOMComisionLideresList.xhtml'
WHERE X.COD_MENU = '40020811';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMSeguimientoCalificacionCampanaForm.xhtml'
WHERE X.COD_MENU = '40020812';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMResumenEvaluacionSeccionForm.xhtml'
WHERE X.COD_MENU = '40020813';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMComisionPagoEjecutivasForm.xhtml'
WHERE X.COD_MENU = '40020815';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMComisionPagoEjecutivasSuspendidasForm.xhtml'
WHERE X.COD_MENU = '40020816';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMEjecutivasForm.xhtml'
WHERE X.COD_MENU = '40020818';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMResultadosEvaluacionEjecutivaForm.xhtml'
WHERE X.COD_MENU = '40020822';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMDetalleComisionRecuperacionForm.xhtml'
WHERE X.COD_MENU = '40020823';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMComparativoRetailSiccForm.xhtml'
WHERE X.COD_MENU = '40020824';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaCOMComisionGerenteZonaEscalonadaList.xhtml'
WHERE X.COD_MENU = '40020825';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCOMComisionAsignacionesGzForm.xhtml'
WHERE X.COD_MENU = '40020827';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaCOMComisionGerenteRegionList.xhtml'
WHERE X.COD_MENU = '40020828';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/procesoCOMComisionGerenteZonaForm.xhtml'
WHERE X.COD_MENU = '40020829';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/procesoCOMComisionGenerarArchivoNominaForm.xhtml'
WHERE X.COD_MENU = '40020830';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/consultaCOMComisionGerenteRegionObjetivoForm.xhtml'
WHERE X.COD_MENU = '40020831';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/consultaCOMComisionRetailForm.xhtml'
WHERE X.COD_MENU = '40020832';

/**
 * Incentivos 
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCConsolidadoPremioDespachadoForm.xhtml'
WHERE X.COD_MENU = '40020109';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCConfiguracionConcursoForm.xhtml'
WHERE X.COD_MENU = '40020101';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCProyeccionPremiosForm.xhtml'
WHERE X.COD_MENU = '40020102';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCPuntObtenidosPuntFaltantesForm.xhtml'
WHERE X.COD_MENU = '40020103';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCTotalPremiosFacturadosForm.xhtml'
WHERE X.COD_MENU = '40020104';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCPuntObtenidosBolsaFaltantesIncForm.xhtml'
WHERE X.COD_MENU = '40020105';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCRecomendacionesConcursoForm.xhtml'
WHERE X.COD_MENU = '40020106';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCIndicadorGestionIncentivosCDRForm.xhtml'
WHERE X.COD_MENU = '40020107';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCPremiosEntregadosForm.xhtml'
WHERE X.COD_MENU = '40020108';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCPremiosEntregadosForm.xhtml'
WHERE X.COD_MENU = '40020109';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteLIDPuntajeVariableCampanaForm.xhtml'
WHERE X.COD_MENU = '40020111';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/reporteINCEstadoPremioDespachadoForm.xhtml'
WHERE X.COD_MENU = '40020110';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCGanadorasConcursoForm.xhtml'
WHERE X.COD_MENU = '40020112';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCPremiosElegidosForm.xhtml'
WHERE X.COD_MENU = '40020114';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCPremiosElegidosForm.xhtml'
WHERE X.COD_MENU = '40021900';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteLIDActividadFinalZonaForm.xhtml'
WHERE X.COD_MENU = '40020119';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteLIDResumenZonaForm.xhtml'
WHERE X.COD_MENU = '40020115';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCConsultorasPuntajeUbicacionForm.xhtml'
WHERE X.COD_MENU = '40020116';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCAnalisisPremiosAtendidosFaltantesForm.xhtml'
WHERE X.COD_MENU = '40020117';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCProgramaReconocimientoForm.xhtml'
WHERE X.COD_MENU = '40021602';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCPuntajeBancoSueniosForm.xhtml'
WHERE X.COD_MENU = '40020120';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCPuntosConcursoCampanaForm.xhtml'
WHERE X.COD_MENU = '40020121';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCGanadorasConcurso2Form.xhtml'
WHERE X.COD_MENU = '40020122';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteINCProgramaReconocimiento2Form.xhtml'
WHERE X.COD_MENU = '40020123';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteINCCuponesElectronicosForm.xhtml'
WHERE X.COD_MENU = '40020124';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteINCPremiosBancoSuenhosForm.xhtml'
WHERE X.COD_MENU = '40020125';

/*
 * Material de Campahna 
 */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMAVPersonalVentasForm.xhtml'
WHERE X.COD_MENU = '40021600';


/*
 * BOLETAS DE RECOJO
 */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECControlBoletaRecojoxZonaForm.xhtml'
WHERE X.COD_MENU = '40023103';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECControlBoletaRecojoNoExitosasNoEnviadasForm.xhtml'
WHERE X.COD_MENU = '40023101';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECControlBoletaRecojoForm.xhtml'
WHERE X.COD_MENU = '40023102';

/*
 * PRODUCTOS
 */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaMAEIndicadorCajaBolsaProductoList.xhtml'
WHERE X.COD_MENU = '40024001';

/*
 * SAC 
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRMontoMinimoForm.xhtml'
WHERE X.COD_MENU = '40020701';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRMontoMaximoForm.xhtml'
WHERE X.COD_MENU = '40020702';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRMontoMayorForm.xhtml'
WHERE X.COD_MENU = '40020703';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRDeudaForm.xhtml'
WHERE X.COD_MENU = '40020704';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRPrimerosPedidosForm.xhtml'
WHERE X.COD_MENU = '40020705';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRDeudaEcuatorianasForm.xhtml'
WHERE X.COD_MENU = '40020706';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRPedidosNoFacturadosBloqueoForm.xhtml'
WHERE X.COD_MENU = '40020707';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRConsultorasPasaronPedidoForm.xhtml'
WHERE X.COD_MENU = '40020708';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRPedidosDigitadosForm.xhtml'
WHERE X.COD_MENU = '40020709';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaOCREnviarOCSForm.xhtml'
WHERE X.COD_MENU = '40020710';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRMasVeinteUnidadesForm.xhtml'
WHERE X.COD_MENU = '40020711';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaOCRConsultorasInactivasForm.xhtml'
WHERE X.COD_MENU = '40020712';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteSACFacturacionDetalleForm.xhtml'
WHERE X.COD_MENU = '40020713';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteOCRSeguimientoDeudaForm.xhtml'
WHERE X.COD_MENU = '40020714';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaOCRDetalladoAptasForm.xhtml'
WHERE X.COD_MENU = '40020717';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACHojaRuteoForm.xhtml'
WHERE X.COD_MENU = '40020719';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaOCRPedidosDuplicadosForm.xhtml'
WHERE X.COD_MENU = '40020716';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACControlDHLForm.xhtml'
WHERE X.COD_MENU = '40020720';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteOCRConsolidadoPedidoForm.xhtml'
WHERE X.COD_MENU = '40020721';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteSACSaldoConsultorasForm.xhtml'
WHERE X.COD_MENU = '40020722';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACPedidosChequeadosForm.xhtml'
WHERE X.COD_MENU = '40020724';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACPedidoZonaForm.xhtml'
WHERE X.COD_MENU = '40020723';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACListaConsejerasHPSalvadasForm.xhtml'
WHERE X.COD_MENU = '40020725';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACAnalisisVentaDiariayAcumuladaForm.xhtml'
WHERE X.COD_MENU = '40020726';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACFacturacionResumenForm.xhtml'
WHERE X.COD_MENU = '40020727';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACAsistenciaCompartamosEsikaForm.xhtml'
WHERE X.COD_MENU = '40020728';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACActivasSaldoForm.xhtml'
WHERE X.COD_MENU = '40020729';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACActualizacionDireccionesForm.xhtml'
WHERE X.COD_MENU = '40020731';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACDetalladoDeudaEsikaLbelConsultorasForm.xhtml'
WHERE X.COD_MENU = '40020730';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACFacturacionAdicionalForm.xhtml'
WHERE X.COD_MENU = '40020733';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCodigosInexistentesForm.xhtml'
WHERE X.COD_MENU = '40020736';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePEDPedidosFacturadosYobelForm.xhtml'
WHERE X.COD_MENU = '40020738';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACPedidosRecibidosPorOrigenForm.xhtml'
WHERE X.COD_MENU = '40020740';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACAutorizacionFacturacionElectronicaForm.xhtml'
WHERE X.COD_MENU = '40020741';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACRecallTrazabilidadForm.xhtml'
WHERE X.COD_MENU = '40020743';

/* SAC - INDICADORES */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteSICDetalleUnidadesAtendidasFaltanteForm.xhtml'
WHERE X.COD_MENU = '40021706';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteSICFacturacionForm.xhtml'
WHERE X.COD_MENU = '40021701'

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICFacturacionEcuadorForm.xhtml'
WHERE X.COD_MENU = '40021708';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICFletesForm.xhtml'
WHERE X.COD_MENU = '40021702';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPedidosAfectadosFaltanteNoAnunciadoForm.xhtml'
WHERE X.COD_MENU IN ('40021703');

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPedidosSustAlmenosUnProductoForm.xhtml'
WHERE X.COD_MENU IN ('40021704');

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPedidosRecupAlMenosUnProductoForm.xhtml'
WHERE X.COD_MENU IN ('40021705');

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICFaltantesForm.xhtml'
WHERE X.COD_MENU = '40021718';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACFaltanteNoAnunciadoForm.xhtml'
WHERE X.COD_MENU = '40021720';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSACIndicadoresForm.xhtml'
WHERE X.COD_MENU = '40021723';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPedidosDigitadosZonaForm.xhtml'
WHERE X.COD_MENU = '40021724';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICDetalleUnidadesAtendidasFaltanteResumenForm.xhtml'
WHERE X.COD_MENU = '40021725';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPedidosAfectadosDetFaltanteNoAnunciadoForm.xhtml'
WHERE X.COD_MENU = '40021726';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePEDPedidosFacturadosCampanaForm.xhtml'
WHERE X.COD_MENU = '40021727';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPedidosDigitadosRegionForm.xhtml'
WHERE X.COD_MENU = '40021728';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPedidosIngresadosOrigenForm.xhtml'
WHERE X.COD_MENU = '40021729';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPedidosIngresadosOrigenForm.xhtml'
WHERE X.COD_MENU = '40021729';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteOCRPedidosGP1SinErrorForm.xhtml'
WHERE X.COD_MENU = '70000201';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/let/reporteLETConfiguracionProgramaForm.xhtml'
WHERE X.COD_MENU = '40024518';

/**
 * Matriz de facturacion
 */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICApoyadosParaPromocionesForm.xhtml'
WHERE X.COD_MENU = '40021808';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICFacturacionMatrizForm.xhtml'
WHERE X.COD_MENU = '40021707';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICGuiaProductosForm.xhtml'
WHERE X.COD_MENU = '40021809';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICPrecioCeroForm.xhtml'
WHERE X.COD_MENU = '40021810';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICBoletaConsultoraForm.xhtml'
WHERE X.COD_MENU = '40021811';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePREConfZonasConLimiteVentasForm.xhtml'
WHERE X.COD_MENU = '40021812';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICReemplazosConfiguradosForm.xhtml'
WHERE X.COD_MENU = '40021813';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICRecuperacionesConfiguradosForm.xhtml'
WHERE X.COD_MENU = '40021814';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICLimitesVentaConfiguradosForm.xhtml'
WHERE X.COD_MENU = '40021815';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICAlternativosConfiguradosForm.xhtml'
WHERE X.COD_MENU = '40021816';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECIndFactTransaccionesForm.xhtml'
WHERE X.COD_MENU = '40022204';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECIndFactDevolucionesProductoForm.xhtml'
WHERE X.COD_MENU = '40022207';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERLiquidacionCobranzasForm.xhtml'
WHERE X.COD_MENU = '40020907';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOControlEscaneoForm.xhtml'
WHERE X.COD_MENU = '40024401';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePROLPedidosFacturadosForm.xhtml'
WHERE X.COD_MENU = '40025501';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTODuplicadosySinDetalleForm.xhtml'
WHERE X.COD_MENU = '40030600';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteOCRRechazoBoletinComercialForm.xhtml'
WHERE X.COD_MENU = '40031500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOInformeEstatusSolicitudesCreditoForm.xhtml'
WHERE X.COD_MENU = '40030001';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOCUVsErradosRecepcionadosForm.xhtml'
WHERE X.COD_MENU = '40031400';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePROLFaltantesForm.xhtml'
WHERE X.COD_MENU = '40025502';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOProductividadForm.xhtml'
WHERE X.COD_MENU = '40030500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteLLIProductosNoRegistradosEnLLForm.xhtml'
WHERE X.COD_MENU = '40023001';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERLibroPercepcionesForm.xhtml'
WHERE X.COD_MENU = '40020904';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/consultaPERConsolidadoPercepcionesAcumuladoForm.xhtml'
WHERE X.COD_MENU = '40020905';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePEDConsultorasChequearForm.xhtml'
WHERE X.COD_MENU = '40024104';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERConsolidadoCobranzasForm.xhtml'
WHERE X.COD_MENU = '40020906';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECListadoRecAreasForm.xhtml'
WHERE X.COD_MENU = '40023204';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPPPedidosServicioForm.xhtml'
WHERE X.COD_MENU = '40021101';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPPPedidosBolsaForm.xhtml'
WHERE X.COD_MENU = '40021102';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENEvaluacionVentaVariableSeccionForm.xhtml'
WHERE X.COD_MENU = '40021207';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENConsejerasIngresadasCampanhaForm.xhtml'
WHERE X.COD_MENU = '40021208';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENConsolidadoRUVForm.xhtml'
WHERE X.COD_MENU = '40021216';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENFacturaNotaCreditoForm.xhtml'
WHERE X.COD_MENU = '40021217';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ventas/reporteVENLibroVentaMensualForm.xhtml'
WHERE X.COD_MENU = '40021218';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBCarteraSinGestionForm.xhtml'
WHERE X.COD_MENU = '40021529';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBEjecutivasConsultorasExcluidasForm.xhtml'
WHERE X.COD_MENU = '40021530';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBReportePrimerosPedidosForm.xhtml'
WHERE X.COD_MENU = '40021534';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBReporteDetalladoCobranza36DiasForm.xhtml'
WHERE X.COD_MENU = '40021539';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBPrimerPedidoConDeudaForm.xhtml'
WHERE X.COD_MENU = '40021606';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECIndFactDevolucionesVentaForm.xhtml'
WHERE X.COD_MENU = '40022203';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECConsolidadoCDRSForm.xhtml'
WHERE X.COD_MENU = '40023205';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCRAInicioFinCampanhaForm.xhtml'
WHERE X.COD_MENU = '40023502';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteZONUnidadesGeograficasForm.xhtml'
WHERE X.COD_MENU = '40023906';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePEDResultadoChequeoGerenteZonaForm.xhtml'
WHERE X.COD_MENU = '40024102';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/reporteLETMetasLideresCampanaForm.xhtml'
WHERE X.COD_MENU = '40024502';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/reporteLETResultadosLideresCampaniaForm.xhtml'
WHERE X.COD_MENU = '40024503';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/reporteLETObjetivosPorSeccionForm.xhtml'
WHERE X.COD_MENU = '40024519';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/app/consultaAPPSecuenciaTerritorioForm.xhtml'
WHERE X.COD_MENU = '40024802';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCUPNuevaUnidadAtendidaForm.xhtml'
WHERE X.COD_MENU = '40025002';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCAuditoriaSaldoCuentasPorCobrarForm.xhtml'
WHERE X.COD_MENU = '40020979';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCGastoCuponForm.xhtml'
WHERE X.COD_MENU = '40020984';



/* Consultas */
/*
 * Indicadores Facturacion de Reclamos
 */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteRECIndFactVentasZonaForm.xhtml'
WHERE X.COD_MENU = '40022201';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECIndFactDevolucionesZonaForm.xhtml'
WHERE X.COD_MENU = '40022202';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECIndFactFaltanteForm.xhtml'
WHERE X.COD_MENU = '40022205';

/* Seguridad */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/seguridad/mantenimientoSICCUsuarioList.xhtml'
WHERE X.COD_MENU = '70000100';

/*CUENTA CORRIENTE */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalladoSaldosMenoresAcreedoresForm.xhtml'
WHERE X.COD_MENU = '40020939';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCSaldosPendientesAuditoriaForm.xhtml'
WHERE X.COD_MENU = '40020953';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCProcesoFamiliaProtegidaForm.xhtml'
WHERE X.COD_MENU = '40020985';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCTarjetaBancariaForm.xhtml'
WHERE X.COD_MENU = '40020971';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCPrimSegPedDeudForm.xhtml'
WHERE X.COD_MENU = '40020973';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCPagosPorRegularizarForm.xhtml'
WHERE X.COD_MENU = '40020929';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDuracionLiquidacionLotesBancariosForm.xhtml'
WHERE X.COD_MENU = '40020977';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalladoSaldosNegativosForm.xhtml'
WHERE X.COD_MENU = '40020934';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCReporteVentaClienteForm.xhtml'
WHERE X.COD_MENU = '40020941';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCBuroCreditoForm.xhtml'
WHERE X.COD_MENU = '40020956';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCGenerarInformacionBuroCrediticioForm.xhtml'
WHERE X.COD_MENU = '30021723';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/reporteCCCDeudorasConMasUnaCampanhaForm.xhtml'
WHERE X.COD_MENU = '40020975';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCConsolidadoCuentaCorrienteContableForm.xhtml'
WHERE X.COD_MENU = '40020935';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalleCuentaCorrienteContableForm.xhtml'
WHERE X.COD_MENU = '40020922';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERBaseRecuperacionCampanhasForm.xhtml'
WHERE X.COD_MENU = '40020908';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCResumenVentasForm.xhtml'
WHERE X.COD_MENU = '40020923';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCResumenAbonosForm.xhtml'
WHERE X.COD_MENU = '40020924';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalleDocumentosContablesForm.xhtml'
WHERE X.COD_MENU = '40020925';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCConsultorasBloqueadasForm.xhtml'
WHERE X.COD_MENU = '40020927';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCCargosAbonosDirectosForm.xhtml'
WHERE X.COD_MENU = '40020928';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalladoCuponesPendientesConciliacionForm.xhtml'
WHERE X.COD_MENU = '40020940';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalladoPagosPorregularizarForm.xhtml'
WHERE X.COD_MENU = '40020942';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERVentaForm.xhtml'
WHERE X.COD_MENU = '40020920';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCPagosLbelEsikaForm.xhtml'
WHERE X.COD_MENU = '40020965';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCCuadreSAPFIForm.xhtml'
WHERE X.COD_MENU = '40020969';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDiasCarteraForm.xhtml'
WHERE X.COD_MENU = '40020974';




/*   RECLAMOS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECReclaIncePendForm.xhtml'
WHERE X.COD_MENU = '40021015';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECMercaderiaSiniestradaForm.xhtml'
WHERE X.COD_MENU = '40021025';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECEmisionNotaCreditoForm.xhtml'
WHERE X.COD_MENU = '40021026';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECOperacionesPedidosFacturadosForm.xhtml'
WHERE X.COD_MENU = '40021018';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECCodigoCuvPremioForm.xhtml'
WHERE X.COD_MENU = '40021020';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECOperacionesReclamoPedidoForm.xhtml'
WHERE X.COD_MENU = '40021017';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECValidacionSolicitudesRecForm.xhtml'
WHERE X.COD_MENU = '40021010';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECOperacionesUnidadAdmForm.xhtml'
WHERE X.COD_MENU = '40021004';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECProductosMasReclamadosForm.xhtml'
WHERE X.COD_MENU = '40021002';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECConsultaStockProductosForm.xhtml'
WHERE X.COD_MENU = '40021009';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECEstadisticaRecDetalladoForm.xhtml'
WHERE X.COD_MENU = '40021006';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECListadoRecEstatusForm.xhtml'
WHERE X.COD_MENU = '40021007';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECListadoDeudaPendPeriodoForm.xhtml'
WHERE X.COD_MENU = '40021013';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECReclamosMotDevolucionForm.xhtml'
WHERE X.COD_MENU = '40021003';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteOCRPedidosGP1SinErrorForm.xhtml'
WHERE X.COD_MENU = '70000201';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECListadoReclamosDetalleForm.xhtml'
WHERE X.COD_MENU = '40021016';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECConsultaStockPremiosForm.xhtml'
WHERE X.COD_MENU = '40021014';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECControlBoletaRecojoForm.xhtml'
WHERE X.COD_MENU = '40021019';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECVentasMensualesForm.xhtml'
WHERE X.COD_MENU = '40021022';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECCDRsCategoriaProductoForm.xhtml'
WHERE X.COD_MENU = '40021030';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/reporteRECPedidosRobadosForm.xhtml'
WHERE X.COD_MENU = '40021031';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECSolicitudesAtencionExpressNMPForm.xhtml'
WHERE X.COD_MENU = '40021032';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECExcepcionValidacionesForm.xhtml'
WHERE X.COD_MENU = '40021036';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaRECSecuenciasZonasDiariasForm.xhtml'
WHERE X.COD_MENU = '40021021';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoRECGestionIngresoAnulacionNmpsSearch.xhtml'
WHERE X.COD_MENU = '20080300';

/* PERCEPCIONES */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaPERConsolidadoPercepcionesAcumuladoDiaList.xhtml'
WHERE X.COD_MENU = '40020936';

/* ZON */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/zon/procesoZONCargarTerritorioUnidadGeograficaForm.xhtml'
WHERE X.COD_MENU = '20072700';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/zon/consultaZONNovedadesHistoricoList.xhtml'
WHERE X.COD_MENU = '40023904';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/zon/consultaZONDirectorioVentasList.xhtml'
WHERE X.COD_MENU = '40023905';

/* CIERRE */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/fac/reporteFACProgramacionCierreForm.xhtml'
WHERE X.COD_MENU = '40028001';


/*  VENTAS  */ 

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENResumenRegistroVentasForm.xhtml'
WHERE X.COD_MENU = '40021219';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENInformeProductividadTerritorioForm.xhtml'
WHERE X.COD_MENU = '40021202';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaCOMResponsablesUAList.xhtml'
WHERE X.COD_MENU = '40021209';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENFlujoResultadosEconomicosForm.xhtml'
WHERE X.COD_MENU = '40021210';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENUnidadesVentaEstadisticableForm.xhtml'
WHERE X.COD_MENU = '40021212';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ventas/procesoVENRegistroVentasIngresosForm.xhtml'
WHERE X.COD_MENU = '40021215';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENGeneralConsultoraEgresadaForm.xhtml'
WHERE X.COD_MENU = '40021201';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENRegistroVentasConsolidadoForm.xhtml'
WHERE X.COD_MENU = '40021206';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ruv/reporteRUVDocumentosContablesConsultaForm.xhtml'
WHERE X.COD_MENU = '40021222';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENVentaVariableForm.xhtml'
WHERE X.COD_MENU = '40021203';

/* INDICADORES DE FACTURACION  */ 

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECIndFactVentasProductoForm.xhtml'
WHERE X.COD_MENU = '40022206';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPETotalArticulosAFPForm.xhtml'
WHERE X.COD_MENU = '40021719';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERAntiguedadDeudasForm.xhtml'
WHERE X.COD_MENU = '40020902';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERRegistroControlClienteForm.xhtml'
WHERE X.COD_MENU = '40020903';40024511
select * from seg_menu where cod_menu='40020903';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEListaPickingSublineaForm.xhtml'
WHERE X.COD_MENU = '40022419';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEUnidadesPicadasDiaForm.xhtml'
WHERE X.COD_MENU = '40020718';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRETDetalleComisionVentaRetail.xhtml'
WHERE X.COD_MENU = '40023802';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteLETAlertaBajaForm.xhtml'
WHERE X.COD_MENU = '40024511';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaMAVNoPasaronPedidosForm.xhtml'
WHERE X.COD_MENU = '40023706';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEListaAFPConsultoraForm.xhtml'
WHERE X.COD_MENU = '40050020';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEOrdenImpresionForm.xhtml'
WHERE X.COD_MENU = '40050004';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteGEOClientesZonasTerritoriosPendientesForm.xhtml'
WHERE X.COD_MENU = '40020302';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECRecepcionCDRForm.xhtml'
WHERE X.COD_MENU = '40021035';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECBoletasRecojoNovedadForm.xhtml'
WHERE X.COD_MENU = '40023201';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMAVConfiguracionPorCampanaForm.xhtml'
WHERE X.COD_MENU = '40023701';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRETResumenComisionVentaRetailForm.xhtml'
WHERE X.COD_MENU = '40023801';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRETResumenComisionVentaRetailConsultoraForm.xhtml'
WHERE X.COD_MENU = '40023804';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteZONMovimientoTerritorioCampanaForm.xhtml'
WHERE X.COD_MENU = '40023903';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECBoletasRecojoGestionadasForm.xhtml'
WHERE X.COD_MENU = '40023202';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePEDEstadisticaResultadoChequeo.xhtml'
WHERE X.COD_MENU = '40024103';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/reporteLETParametriaConcurso.xhtml'
WHERE X.COD_MENU = '40024501';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCBoletaDepositoForm.xhtml'
WHERE X.COD_MENU = '40020970';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOLControlAsistenciaTriunfadorasForm.xhtml'
WHERE X.COD_MENU = '40020980';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/reporteLETResultadosLideresConcursoForm.xhtml'
WHERE X.COD_MENU = '40024504';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSGRControlAbonosForm.xhtml'
WHERE X.COD_MENU = '40024705';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENGeneralRegistroVentasDetalladoForm.xhtml'
WHERE X.COD_MENU = '40021204';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENProyectadoVariablesActividadConsultorasForm.xhtml'
WHERE X.COD_MENU = '40021211';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/edu/reporteEDUCronogramaDictadoForm.xhtml'
WHERE X.COD_MENU = '40024605';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSGRSolicitudesForm.xhtml'
WHERE X.COD_MENU = '40024702';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSGRSolicitudesRechazadasForm.xhtml'
WHERE X.COD_MENU = '40024703';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSGRControlCargosForm.xhtml'
WHERE X.COD_MENU = '40024704';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteFLXDetalladoConsultorasHabilesForm.xhtml'
WHERE X.COD_MENU = '40025201';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteFLXCuentasProcesadasForm.xhtml'
WHERE X.COD_MENU = '40025205';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteFLXSeguimientoFinanciamientoForm.xhtml'
WHERE X.COD_MENU = '40025206';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cronograma/mantenimientoCRAMatrizDiasSearchForm.xhtml'
WHERE X.COD_MENU = '20460500';



UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/app/consultaAPPSecuenciaZonasForm.xhtml'
WHERE X.COD_MENU = '40024801';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENOrdenesCompraConsultoraPrevioCierreRegionForm.xhtml'
WHERE X.COD_MENU = '40021213';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENEvaluacionCoberturaNivelesSeccionForm.xhtml'
WHERE X.COD_MENU = '40021214';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ventas/reporteVENDetalleAnulacionesDevolucionesForm.xhtml'
WHERE X.COD_MENU = '40021221';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBConsolidadoRecuperacionCarteraEjecutivoForm.xhtml'
WHERE X.COD_MENU = '40021518';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBDetalladoRecuperacionCarteraForm.xhtml'
WHERE X.COD_MENU = '40021503';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBDetalladoRecuperacionCarteraCobradorForm.xhtml'
WHERE X.COD_MENU = '40021520';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBCarteraSupervisorForm.xhtml'
WHERE X.COD_MENU = '40021506';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBDetalladoGestionCobranzaForm.xhtml'
WHERE X.COD_MENU = '40021517';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBEstadisticoRecuperacionCarteraForm.xhtml'
WHERE X.COD_MENU = '40021505';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBDetalladoSaldosUnidadAdministrativaForm.xhtml'
WHERE X.COD_MENU = '40021522';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCRATotalActividadForm.xhtml'
WHERE X.COD_MENU = '40023504';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMSGValidacionesMensajesForm.xhtml'
WHERE X.COD_MENU = '40024201';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERResumenCuentasCorrientesForm.xhtml'
WHERE X.COD_MENU = '40020901';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBDetalladoSaldosCampanaForm.xhtml'
WHERE X.COD_MENU = '40021521';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBRecuperacionCarteraVentasForm.xhtml'
WHERE X.COD_MENU = '40021508';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOConsolidadoEntregaForm.xhtml'
WHERE X.COD_MENU = '40024403';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTODetalleEntregaForm.xhtml'
WHERE X.COD_MENU = '40024404';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteLETResultadosCampanaForm.xhtml'
WHERE X.COD_MENU = '40024509';

/* COSTOS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOSDevolucionRangosFechaForm.xhtml'
WHERE X.COD_MENU = '40022102';

/*   EJECUTIVO VIRTUAL  */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteEVIMicaRecepcionPedidosZonaForm.xhtml'
WHERE X.COD_MENU = '40020201';

/*MAE*/

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMAEClasificacionPorClienteForm.xhtml'
WHERE X.COD_MENU = '40022308';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMAEEstadisticoClienteForm.xhtml'
WHERE X.COD_MENU = '40022301';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMAENuevasForm.xhtml'
WHERE X.COD_MENU = '40022302';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMAEVinculosClienteActionForm.xhtml'
WHERE X.COD_MENU = '40022307';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMAEConsejerasReactivadasForm.xhtml'
WHERE X.COD_MENU = '40022303';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteMAEConsejerasBloqueadasDesbloqueadasForm.xhtml'
WHERE X.COD_MENU = '40022304';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteMAEClienteForm.xhtml'
WHERE X.COD_MENU = '40022305';


/* GP1  */ 
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteOCRPedidosGP1SinErrorForm.xhtml'
WHERE X.COD_MENU = '30010106';

/* GP3  */ 

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePRYProyeccionFaltanteDiaForm.xhtml'
WHERE X.COD_MENU = '30010302';


/*APE*/
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEDistribucionRecuperacionDeFletesForm.xhtml'
WHERE X.COD_MENU = '40050024';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEArmadoWHFACTForm.xhtml'
WHERE X.COD_MENU = '40050021';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEArmadoMgpedxdiaForm.xhtml'
WHERE X.COD_MENU = '40050023';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEDistribucionCdrsRecojosForm.xhtml'
WHERE X.COD_MENU = '40050025';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEDistribucionCdrsFacturacionRealForm.xhtml'
WHERE X.COD_MENU = '40050026';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEMapaAnaquelBalanceoDiarioForm.xhtml'
WHERE X.COD_MENU = '40022452';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteAPEMapaAnaquelForm.xhtml'
WHERE X.COD_MENU = '40050015';

/*
 * CRONOGRAMA ACTIVIDAD
 */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCRADiferenciaVencimientosForm.xhtml'
WHERE X.COD_MENU = '40023501';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCRAInicioFinCampanhaForm.xhtml'
WHERE X.COD_MENU = '40023502';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCRATotalGrupoForm.xhtml'
WHERE X.COD_MENU = '40023503';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCRACronogramaFase2Form.xhtml'
WHERE X.COD_MENU = '40023505';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCRAZonasFacturaFechaForm.xhtml'
WHERE X.COD_MENU = '40023506';

/*
 * PRIVILEGE
 */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePRIConfiguracionPuntajeCampanaForm.xhtml'
WHERE X.COD_MENU = '40023601';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCMediosMagneticosForm.xhtml'
WHERE X.COD_MENU = '40020981';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCRecuperacionFamiliaProtegidaForm.xhtml'
WHERE X.COD_MENU = '40020955';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCPrimerosPedDeudor.xhtml'
WHERE X.COD_MENU = '40020972';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCContableSaldosCampaniasForm.xhtml'
WHERE X.COD_MENU = '40020982';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECNoAtendidosBajoStockForm.xhtml'
WHERE X.COD_MENU = '40021008';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteRECSolicitudesPendientesAtencionForm.xhtml'
WHERE X.COD_MENU = '40021012';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteGEOClientesZonasTerritoriosErradosForm.xhtml'
WHERE X.COD_MENU = '40020301';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCVentasPorDiaForm.xhtml'
WHERE X.COD_MENU = '40020949';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCAbonosPorDiaForm.xhtml'
WHERE X.COD_MENU = '40020950';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERFacturasPendientesSeccionForm.xhtml'
WHERE X.COD_MENU = '40020909';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reportePERDetalleCtaCteForm.xhtml'
WHERE X.COD_MENU = '40020912';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCLiquidacionCobranzasForm.xhtml'
WHERE X.COD_MENU = '40020917';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCLiquidacionCobranzasAplicacionForm.xhtml'
WHERE X.COD_MENU = '40020933';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalladoConsultorasSinDeudaForm.xhtml'
WHERE X.COD_MENU = '40020944';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteVENResumenVentasForm.xhtml'
WHERE X.COD_MENU = '40021205';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCCargosDirectosForm.xhtml'
WHERE X.COD_MENU = '40020961';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCAbonosDirectosForm.xhtml'
WHERE X.COD_MENU = '40020962';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalladoConsultorasGastosAdministrativosForm.xhtml'
WHERE X.COD_MENU = '40020960';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaEVIConsultaMicaRecepcionForm.xhtml'
WHERE X.COD_MENU = '40020203';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaPEDResultadoChequeoConsultoraForm.xhtml'
WHERE X.COD_MENU = '40024101';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCDetalladoPagosRegularizadosForm.xhtml'
WHERE X.COD_MENU = '40020943';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCChequesBancariosForm.xhtml'
WHERE X.COD_MENU = '40020963';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/reporteMAEConsejerasReactivadasForm.xhtml'
WHERE X.COD_MENU = '40024507';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBConsultorasConDeudaPorDiaForm.xhtml'
WHERE X.COD_MENU = '40021519';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBEgresadasSinDeudaForm.xhtml'
WHERE X.COD_MENU = '40021540';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBDetalladoRecuperacionIncobrableForm.xhtml'
WHERE X.COD_MENU = '40021538';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBSeguimientoCobradorForm.xhtml'
WHERE X.COD_MENU = '40021536';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteFLXDetalladoConsultorasHabilesForm.xhtml'
WHERE X.COD_MENU = '40020957';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOCambioDocumIdentidadSADForm.xhtml'
WHERE X.COD_MENU = '40031100';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteFDVFuerzaDeVentaForm.xhtml'
WHERE X.COD_MENU = '40025101';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCOBCierreRegionForm.xhtml'
WHERE X.COD_MENU = '40021523';

/*   STO */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOConsolidadoPostVentaRechazadosForm.xhtml'
WHERE X.COD_MENU = '40030100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOErroresValidacionForm.xhtml'
WHERE X.COD_MENU = '40030300';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOEstadisticoForm.xhtml'
WHERE X.COD_MENU = '40030400';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOEnvioNotificacionUsuariosForm.xhtml'
WHERE X.COD_MENU = '40031300';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOConsejerasCantidadesModificadasValidadasForm.xhtml'
WHERE X.COD_MENU = '40030200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTORechazadasForm.xhtml'
WHERE X.COD_MENU = '40031000';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOLimiteVentaFocalizadoForm.xhtml'
WHERE X.COD_MENU = '40031200';

/*   STO  PROCESOS*/

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOGrabarPedRecForm.xhtml'
WHERE X.COD_MENU = '20050400';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOAlmacenamientoHistoricoForm.xhtml'
WHERE X.COD_MENU = '20050600';

/*   STO MANTENIMIENTOS*/

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOLiberacionRechazoList.xhtml'
WHERE X.COD_MENU = '20054500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/consultaSTOSeguimientoPedidosForm.xhtml'
WHERE X.COD_MENU = '20052800';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTOCuponList.xhtml'
WHERE X.COD_MENU = '20051000';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/consultaSTOPedidosForm.xhtml'
WHERE X.COD_MENU = '20051200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOEliminarPedidosForm.xhtml'
WHERE X.COD_MENU = '20051100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOConsultaValidacionesForm.xhtml'
WHERE X.COD_MENU = '20050500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOEliminarCDRForm.xhtml'
WHERE X.COD_MENU = '20051300';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTORevertirPedidosGP1Form.xhtml'
WHERE X.COD_MENU = '20052000';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOEliminarPolizasFamiliaSeguraForm.xhtml'
WHERE X.COD_MENU = '20052300';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/reclamos/mantenimientoExcepcionesValidacionesList.xhtml'
WHERE X.COD_MENU = '20054000';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTOBloqueoControlList.xhtml'
WHERE X.COD_MENU = '20052500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTOLimiteVentaFocalizadoConsejeraList.xhtml'
WHERE X.COD_MENU = '20052200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTOBeneficioDeudaList.xhtml'
WHERE X.COD_MENU = '20051600';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTOControlFnePorcentajeList.xhtml'
WHERE X.COD_MENU = '20052700';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTOControlDevolucionesList.xhtml'
WHERE X.COD_MENU = '20051800';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/mantenimientoSTOFormaPagoClasificacionSearchForm.xhtml'
WHERE X.COD_MENU = '20053300';


/* EDUCACION */ 
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/edu/reporteEDUPlanillasEmitidasForm.xhtml'
WHERE X.COD_MENU = '40024609';

/* PROGRAMA LET */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/reporteLETLideresForm.xhtml'
WHERE X.COD_MENU = '40024505';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lec/consultaLETTarjetasPagoForm.xhtml'
WHERE X.COD_MENU = '40024521';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lec/reporteLECProyeccionForm.xhtml'
WHERE X.COD_MENU = '40024520';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lec/reporteLECResultadosForm.xhtml'
WHERE X.COD_MENU = '40024513';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lec/reporteLECIngresosForm.xhtml'
WHERE X.COD_MENU = '40024516';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTORechazosForm.xhtml'
WHERE X.COD_MENU = '40031600';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCUPNuevasCuponesForm.xhtml'
WHERE X.COD_MENU = '40025003';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOInformeResumenPrimerosPedidosForm.xhtml'
WHERE X.COD_MENU = '40030900';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/edu/reporteEDUPlanillaProgramacionForm.xhtml'
WHERE X.COD_MENU = '40024604';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCUPIngresoMetasForm.xhtml'
WHERE X.COD_MENU = '40025001';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/edu/reporteEDUResumenProgramadasPlanillaForm.xhtml'
WHERE X.COD_MENU = '40024608';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSTOContratoEjecutivaForm.xhtml'
WHERE X.COD_MENU = '40031700';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteFLXGestionServicioSACForm.xhtml'
WHERE X.COD_MENU = '40025203';

/* GIS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteGISEnviarDireccionConsultorasForm.xhtml'
WHERE X.COD_MENU = '40020401';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazACCEnviarNovedadesForm.xhtml'
WHERE X.COD_MENU = '30023102';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazACCRecepcionarRecomendantesRecomendadasForm.xhtml'
WHERE X.COD_MENU = '30023103';

/* EJECUTIVO VIRTUAL */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazEVIEnviarEjecutivoVirtualForm.xhtml'
WHERE X.COD_MENU = '30021502';

/* COBRANZAS INTERFACE */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBEnviarInformacionProveedoresCobranzaForm.xhtml'
WHERE X.COD_MENU = '30022006';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBEnviarDatosInfocorpForm.xhtml'
WHERE X.COD_MENU = '30022001';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBEnviarArchivoTransaccionCobranzaForm.xhtml'
WHERE X.COD_MENU = '30022002';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBEnviarCobranzaSaldoPendienteForm.xhtml'
WHERE X.COD_MENU = '30022003';



/* MY EBEL */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazMYEEnviarHistoricoPegsForm.xhtml'
WHERE X.COD_MENU = '30020214';

/* retail */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazRETEnviarComplementoFacturasVentaDirectaForm.xhtml'
WHERE X.COD_MENU = '30020304';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazAPEEnviarFacturacionGenexusForm.xhtml'
WHERE X.COD_MENU = '30023301';

/* PROYECCION PARCIAL */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPRYEnviarProyeccionParcialForm.xhtml'
WHERE X.COD_MENU = '30022101';
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPRYEnviarProyeccionParcialCentroForm.xhtml'
WHERE X.COD_MENU = '30022102';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazDANEnviarAdministracionFlujosForm.xhtml'
WHERE X.COD_MENU = '30024001';

/* IVR */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazIVREnviarCompletoIVRCorporativoForm.xhtml'
WHERE X.COD_MENU = '30022306';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazFLXEnvioResultadoProgramasForm.xhtml'
WHERE X.COD_MENU = '30024204';

/* 	ECOMMERCE */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSICEnviarVentaBaseConsultorasForm.xhtml'
WHERE X.COD_MENU = '30021902';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazECMEnviarMatrizProductosOfertaCumpleanhosForm.xhtml'
WHERE X.COD_MENU = '30021905';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazECMEnviarClientesForm.xhtml'
WHERE X.COD_MENU = '30021907';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSICEnviarVentasPuntajesXCampanyaForm.xhtml'
WHERE X.COD_MENU = '30021903';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSICEnviarInscritasForm.xhtml'
WHERE X.COD_MENU = '30021904';


/* PERCEPCIONES */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPEREnviarArchivoPDTForm.xhtml'
WHERE X.COD_MENU = '30021003';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAFEnviarFacturacionForm.xhtml'
WHERE X.COD_MENU = '30023501';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBEnviarCobranzaPeriodoZonaForm.xhtml'
WHERE X.COD_MENU = '30022004';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBEnviarRecuperacionCobranzaPorCobradorForm.xhtml'
WHERE X.COD_MENU = '30022005';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBEnviarRecuCobranzaFFVVDatamartForm.xhtml'
WHERE X.COD_MENU = '30022008';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBGenerarInformacionAcovediForm.xhtml'
WHERE X.COD_MENU = '30022009';


/* LARISSA */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazLAREnviarVisibilidadCronogramaFacturacionForm.xhtml'
WHERE X.COD_MENU = '30021102';

/* OTROS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSICEnviarClasificacionConsultorasNuevasForm.xhtml'
WHERE X.COD_MENU = '30021401';

/*CUENTA CORRIENTE*/
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCCCEnviarSaldosConsultorasForm.xhtml'
WHERE X.COD_MENU = '30021702';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCCCEnviarNovedadesConsultorasForm.xhtml'
WHERE X.COD_MENU = '30021705';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazLAREnvioDocumentosCabeceraComplementoPaqueteForm.xhtml'
WHERE X.COD_MENU = '30021109';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazRECEnviarBoletaRecojoForm.xhtml'
WHERE X.COD_MENU = '30021202';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazDATEnviarIncentivosForm.xhtml'
WHERE X.COD_MENU = '30021305';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazDATEnviarDatamartSacForm.xhtml'
WHERE X.COD_MENU = '30021304';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazLAREnvioEstimadosYobelForm.xhtml'
WHERE X.COD_MENU = '30021106';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPEREnviarRecaudosBancariosForm.xhtml'
WHERE X.COD_MENU = '30021004';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOBGenerarInformacionDatacreditoForm.xhtml'
WHERE X.COD_MENU = '30022010';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazLLIEnviarVentaPeriodoForm.xhtml'
WHERE X.COD_MENU = '30022201';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCRegistroVentasBoliviaForm.xhtml'
WHERE X.COD_MENU = '40020991';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCRegistroAbonosBoliviaForm.xhtml'
WHERE X.COD_MENU = '40020992';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteCCCConsolidadoRecaudoCampanaForm.xhtml'
WHERE X.COD_MENU = '40020989';


/* ECOMMERCE */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazECMRecepcionarMovimientosAbonosConsultoraForm.xhtml'
WHERE X.COD_MENU = '30021901';

/* SAT */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarDivisionArmadoCDPForm.xhtml'
WHERE X.COD_MENU = '30022519';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarOrdenImpresionAPESATForm.xhtml'
WHERE X.COD_MENU = '30022521';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarExcepcionesFechaEntregaExactaForm.xhtml'
WHERE X.COD_MENU = '30022523';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarImpresionBoletasEntregaForm.xhtml'
WHERE X.COD_MENU = '30022525';

/* AVI */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazAVIRecepcionarLogrosForm.xhtml'
WHERE X.COD_MENU = '30022703';

/* CYZONE */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCYZRecepcionarProductosSolicitadosForm.xhtml'
WHERE X.COD_MENU = '30023202';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarParametrizacionCalculoFechaEntregaExactaForm.xhtml'
WHERE X.COD_MENU = '30022522';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSATRecepcionarSeguimientoPedidoForm.xhtml'
WHERE X.COD_MENU = '30022524';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazAVIRecepcionarDatosTelefonicosForm.xhtml'
WHERE X.COD_MENU = '30022704';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazACCRecepcionarReferidasForm.xhtml'
WHERE X.COD_MENU = '30023104';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazAPERecepcionarAnaquelesForm.xhtml'
WHERE X.COD_MENU = '30023303';

/*  GP1 INTERFACE  */ 

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazPRIRecepcionarArchivosPrivilegeForm.xhtml'
WHERE X.COD_MENU = '30010101';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/procesoMAEClasificacionClientesForm.xhtml'
WHERE X.COD_MENU = '30010102';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/procesoCYZActualizaClasificacionProgramaForm.xhtml'
WHERE X.COD_MENU = '30010103';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCYZRecepcionarProductosSolicitadosForm.xhtml'
WHERE X.COD_MENU = '30010104';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mae/procesoMAEClasificacionLoveClientesForm.xhtml'
WHERE X.COD_MENU = '30010105';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mae/mantenimientoMAEExcencionFleteList.xhtml'
WHERE X.COD_MENU = '20092700';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mav/procesosMAVGP1Form.xhtml'
WHERE X.COD_MENU = '30010109';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/procesoPERGeneracionCtaCteDocumentoLegalForm.xhtml'
WHERE X.COD_MENU = '30010901';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/gen/procesoGENProcesarGP1Form.xhtml'
WHERE X.COD_MENU = '30010108';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/procesoSAPNuevaCargaForm.xhtml'
WHERE X.COD_MENU = '30023502';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/procesoCUPDespachoProductoForm.xhtml'
WHERE X.COD_MENU = '20010203';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/procesoCUPGenerarCCCForm.xhtml'
WHERE X.COD_MENU = '20010204';


/*FACTURACION ELECTRONICA INTERFAZ	*/

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/interfazFACEnviarReprocesoPendientesForm.xhtml'
WHERE X.COD_MENU = '30012202';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/interfazFACRecepcionarNotasCreditoRetailForm.xhtml'
WHERE X.COD_MENU = '30012203';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazRECProductosReclamadosForm.xhtml'
WHERE X.COD_MENU = '30021201';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazRECEnviarUnidadesAlmacenVirtualForm.xhtml'
WHERE X.COD_MENU = '30021206';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazMYEEnviarMovimientosCuentaCorrienteForm.xhtml'
WHERE X.COD_MENU = '30020201';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazMYEEnviarZonasForm.xhtml'
WHERE X.COD_MENU = '30020203';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazMYEEnviarRegionesForm.xhtml'
WHERE X.COD_MENU = '30020202';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazMYEEnviarInterfacesDiariasForm.xhtml'
WHERE X.COD_MENU = '30020207';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazMYEEnviarCabeceraPedidosForm.xhtml'
WHERE X.COD_MENU = '30020208';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazMYEEnviarUtilitariosPostVentaForm.xhtml'
WHERE X.COD_MENU = '30020210';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAMRecepcionarStockMAVForm.xhtml'
WHERE X.COD_MENU = '30010307';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/procesoCYZEnviarMensajesForm.xhtml'
WHERE X.COD_MENU = '30010402';


/* INICIO CAMPAÑA */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/gen/procesoGENInicioCampanyaForm.xhtml'
WHERE X.COD_MENU = '30011806';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/procesoLETEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30011804';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mae/procesoMAEEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30011805';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ventas/procesoVENCalculoFuenteVentasRealForm.xhtml'
WHERE X.COD_MENU = '30010612';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lideres/procesoLIDGenerarMensajePuntajeObtenidoForm.xhtml'
WHERE X.COD_MENU = '30010603';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazIVRRecepcionarPedidosForm.xhtml'
WHERE X.COD_MENU = '30022308';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOMRecepcionarActualizaCodigoProveedorForm.xhtml'
WHERE X.COD_MENU = '30022804';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOMRecepcionarActualizaCodigoProveedorForm.xhtml'
WHERE X.COD_MENU = '20010721';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mic/procesoMICGestorInterfaceForm.xhtml'
WHERE X.COD_MENU = '30022901';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lideres/procesoLIDCierreProcesosDiariosForm.xhtml'
WHERE X.COD_MENU = '30010607';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/procesoLETEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30010611';

/* CIERRE DE ZONA*/

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/inc/procesoINCEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30011502';

/*COMISIONES*/
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOMEnviarArchivoAdamForm.xhtml'
WHERE X.COD_MENU = '20010707';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/procesoCOMCalculoComisionRecuperacionEjecutivasForm.xhtml'
WHERE X.COD_MENU = '20010718';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/procesoCOMComisionComercializacionForm.xhtml'
WHERE X.COD_MENU = '20010710';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/procesoCOMCalculoVariablesNivelSeccionCampaniaForm.xhtml'
WHERE X.COD_MENU = '20010716';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/comision/procesoCOMCalculoCalificacionTramoForm.xhtml'
WHERE X.COD_MENU = '20010715';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOMEnviarArchivoEjecutivaNuevaAspiranteForm.xhtml'
WHERE X.COD_MENU = '20010719';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazCOMEnviarFicheroPagoComisionEjecutivaForm.xhtml'
WHERE X.COD_MENU = '20010720';
/* FIN COMISIONES*/

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/love/procesoLOVEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30011501';

/* CIERRE DE REGION*/

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/inc/procesoINCGenerarReporteIncentivosForm.xhtml'
WHERE X.COD_MENU = '30011212';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/inc/procesoINCEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30011502';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/lideres/procesoLIDEvaluarNumeroPedidosSeccionCierrePeriodoForm.xhtml'
WHERE X.COD_MENU = '30010706';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/love/procesoLOVEjecutarProcesosForm.xhtml'
WHERE X.COD_MENU = '30011207';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/ape/consultaAPESistemaPicadoSearchForm.xhtml'
WHERE X.COD_MENU = '20310400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/let/procesoLETCargaPedidosObjetivosRezonificacionForm.xhtml'
WHERE X.COD_MENU = '20371800';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/lec/procesoLECCalcularRecuperacionForm.xhtml'
WHERE X.COD_MENU = '20370500';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/edu/procesoEDUEnvioCronogramaDictadoForm.xhtml'
WHERE X.COD_MENU = '20390206';


/* COMISIONES */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/procesoCOMGenerarLideresNuevasForm.xhtml'
WHERE X.COD_MENU = '20010701';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazSAPFIReportesSAPFIForm.xhtml'
WHERE X.COD_MENU = '30023506';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/interfazSAPFIEnviarInformacionSociasEmpresariasForm.xhtml'
WHERE X.COD_MENU = '30023508';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/mantenimientoCOMComisionGerenteZonaList.xhtml'
WHERE X.COD_MENU = '20010713';


/**
 *	PROCESOS DE USUARIO - STO 	
 */
 
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/procesoCYZActualizaClasificacionProgramaForm.xhtml'
WHERE X.COD_MENU = '20053000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/procesoSTOEjecutarGP2OC.xhtml'
WHERE X.COD_MENU = '20052400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/procesoSTORecepcionarCuponForm.xhtml'
WHERE X.COD_MENU = '20052600';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/consultaSTOSolicitudesCreditoErrorReferidasForm.xhtml'
WHERE X.COD_MENU = '20052900';

UPDATE SEG_MENU X 
SET X.PAG_XML='/pages/spusicc/sms/interfazSMSGenerarMensajeForm.xhtml'
WHERE X.COD_MENU='20480200';

UPDATE SEG_MENU X
SET X.PAG_XML='/pages/spusicc/fdv/consultaFDVParametroList.xhtml'
WHERE COD_MENU='20420200';

UPDATE SEG_MENU X
SET X.PAG_XML='/pages/edu/procesoEDUActualizacionStatusConsultoraList.xhtml'
WHERE COD_MENU='20390210';

UPDATE SEG_MENU X
SET X.PAG_XML='/pages/sisicc/interfazEDURecepcionarConsultoraEstablecidasForm.xhtml'
WHERE COD_MENU='20390208';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/app/procesoAPPZonasTerritoriosSinSecuenciaForm.xhtml'
WHERE X.COD_MENU = '20330300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoRECLiquidacionBoletaRecojoForm.xhtml'
WHERE X.COD_MENU = '20080800';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/mantenimientoCUPProgramaNuevCuponesList.xhtml'
WHERE X.COD_MENU = '20010201';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOConsultaClientesForm.xhtml'
WHERE X.COD_MENU = '20050300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/procesoSTOAprobacionMasivaBuroCreditoForm.xhtml'
WHERE X.COD_MENU = '20054100';

/* OCR PROCESOS */ 

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoOCRFechaProgramadaFacturacionForm.xhtml'
WHERE X.COD_MENU = '20010122';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/procesoSTOCambioTipoOrdenPedidosCargadosForm.xhtml'
WHERE X.COD_MENU = '20010123';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mantenimientoSICConcursoDuplaCyzoneList.xhtml'
WHERE X.COD_MENU = '20010301';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/datosbasicos/productoList.xhtml'
WHERE X.COD_MENU = '20010401';

/**
 *	PROCESOS DE USUARIO - COMISIONES 	
 */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/procesoCOMCargaCuentasDetraccionForm.xhtml'
WHERE X.COD_MENU = '20010743';

/**
 *	PROCESOS DE USUARIO - CUENTA CORRIENTE 	
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/procesoCCCCargarCADMasivosUpload.xhtml'
WHERE X.COD_MENU = '20220003';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/procesoCCCCargarPagosBancariosMasivosUpload.xhtml'
WHERE X.COD_MENU = '20220006';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCDepuracionSaldosMenoresDeudoresForm.xhtml'
WHERE X.COD_MENU = '20220042';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCDepuracionSaldosMenoresAcreedoresForm.xhtml'
WHERE X.COD_MENU = '20220043';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCDepuracionPagosPorRegularizarForm.xhtml'
WHERE X.COD_MENU = '20220044';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cobranzas/mantenimientoCOBCobradorPaisList.xhtml'
WHERE X.COD_MENU = '20010981';

/**
 *	PROCESOS DE USUARIO - ZON 	
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/mantenimientoZONUnidadGeograficaList.xhtml'
WHERE X.COD_MENU = '20072300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/mantenimientoZONCallesList.xhtml'
WHERE X.COD_MENU = '20072600';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/zon/mantenimientoZONTipoCargoList.xhtml'
WHERE X.COD_MENU = '20070500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/zon/mantenimientoZONIngresoDirectorioForm.xhtml'
WHERE X.COD_MENU = '20070600';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/mantenimientoZONParametrosRutasList.xhtml'
WHERE X.COD_MENU = '20071500';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/mantenimientoZONSeccionesNoAptasSearchList.xhtml'
WHERE X.COD_MENU = '20072000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/mantenimientoZONUnidadAdministrativaList.xhtml'
WHERE X.COD_MENU = '20072100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/mantenimientoZONRolList.xhtml'
WHERE X.COD_MENU = '20072400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/procesoZONCargaSeccionesNoAptasForm.xhtml'
WHERE X.COD_MENU = '20071900';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/let/mantenimientoLETLideresForm.xhtml'
WHERE X.COD_MENU = '20070300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mae/mantenimientoMAEInformacionClienteForm.xhtml'
WHERE X.COD_MENU = '20092000';

/* PROCESOS COBRANZAS - MANTENIMIENTOS
*/

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/mantenimientoCUPEquivalenciaMatrizList.xhtml'
WHERE X.COD_MENU = '20010206';


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cobranzas/mantenimientoCOBZonaNoCriticaList.xhtml'
WHERE X.COD_MENU = '20010983';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cobranzas/mantenimientoCOBEtapaDeudaList.xhtml'
WHERE X.COD_MENU = '20010985';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cobranzas/mantenimientoCOBZonaNoCriticaList.xhtml'
WHERE X.COD_MENU = '20010983';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cobranzas/mantenimientoCOBAsignacionCarteraCobradorList.xhtml'
WHERE X.COD_MENU = '20010984';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mantenimientoDATParametrosCDRList.xhtml'
WHERE X.COD_MENU = '20010302';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/mantenimientoCOMDesmarcarEnvioSAPList.xhtml'
WHERE X.COD_MENU = '20010722';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoOCRCapturaSolicitudesCreditoList.xhtml'
WHERE X.COD_MENU = '20200300';

/*
 * CUPONES MANTENIMIENTOS
 * 
 * */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/mantenimientoCUPRegistrarExcepcionesList.xhtml'
WHERE X.COD_MENU = '20010210';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/mantenimientoCUPProductoPrimerPedidoList.xhtml'
WHERE X.COD_MENU = '20010211';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/mantenimientoCUPDespachoProductoList.xhtml'
WHERE X.COD_MENU = '20010202';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPEDModificacionCUVMaterialesForm.xhtml'
WHERE X.COD_MENU = '20240300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/dto/mantenimientoDTOMatrizDescuentoList.xhtml'
WHERE X.COD_MENU = '20450300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/mantenimientoINCCampanaDespachoDiferidaForm.xhtml'
WHERE X.COD_MENU = '20230700';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/mantenimientoINCConfiguracionConcursoCuponElectronicoList.xhtml'
WHERE X.COD_MENU = '20231500';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spncd/mantenimientoCUPProgramaPeriodoList.xhtml'
WHERE X.COD_MENU = '20010205';


/*
 * MODELO FLEXIPAGO- MANTENIMIENTOS
 * 
 * */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/procesoFLXCargaManualConsultoraDeshabilitarForm.xhtml'
WHERE X.COD_MENU = '20490300';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/procesoFLXCargaManualLineaCreditoForm.xhtml'
WHERE X.COD_MENU = '20490200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/mantenimientoFLXGruposList.xhtml'
WHERE X.COD_MENU = '20490101';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/mantenimientoFLXMotivosRechazoList.xhtml'
WHERE X.COD_MENU = '20490104';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/mantenimientoFLXMotivosRecomendacionList.xhtml'
WHERE X.COD_MENU = '20490106';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/mantenimientoFLXGruposRegionesList.xhtml'
WHERE X.COD_MENU = '20490102';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/mantenimientoFLXEstatusRechazoList.xhtml'
WHERE X.COD_MENU = '20490105';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/mantenimientoFLXEstatusRecomendacionList.xhtml'
WHERE X.COD_MENU = '20490107';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/dto/mantenimientoDTODescuentoAdicionalList.xhtml'
WHERE X.COD_MENU = '20450401';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/mantenimientoINCBloqueoPremiosList.xhtml'
WHERE X.COD_MENU = '20231100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mantenimientoCOMLibretaAhorroList.xhtml'
WHERE X.COD_MENU = '20010702';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mantenimientoGEOZonaTerritorioClienteList.xhtml'
WHERE X.COD_MENU = '20010601';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPEDReasignacionDocumentosLegalesForm.xhtml'
WHERE X.COD_MENU = '20240200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaCOMResponsablesUAList.xhtml'
WHERE X.COD_MENU = '40023902';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaINTEstadoInterfazForm.xhtml'
WHERE X.COD_MENU = '40010100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/consultaSABFuenteVentasPrevistaForm.xhtml'
WHERE X.COD_MENU = '40020501';

/*  LOGRO MANTENIMIENTOS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spncd/mantenimientoCUPLogrosList.xhtml'
WHERE X.COD_MENU = '20010213';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ape/mantenimientoAPEFactoresConversionSearchForm.xhtml'
WHERE X.COD_MENU = '20311002';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ape/mantenimientoAPETiposCajaEmbalajeSearchForm.xhtml'
WHERE X.COD_MENU = '20311005';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ape/mantenimientoAPEGenerarOlasForm.xhtml'
WHERE X.COD_MENU = '20311006';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ape/mantenimientoAPEConfiguracionTextosVariablesSearchForm.xhtml'
WHERE X.COD_MENU = '20311103';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/ape/mantenimientoAPEUnidadesAdministrativasLineaSearchForm.xhtml'
WHERE X.COD_MENU = '20310300';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mae/mantenimientoMAEActualizacionPeriodosRetiradasForm.xhtml'
WHERE X.COD_MENU = '10011000';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/sisicc/procesoBASLoggerForm.xhtml'
WHERE X.COD_MENU = '10010800';

/* RUV- MANTENIMIENTOS   * */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/procesoRUVEliminacionRUVOtrosCanalesForm.xhtml'
WHERE X.COD_MENU = '20500100';

/* SEGURIDAD - MANTENIMIENTOS   * */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/seguridad/paisList.xhtml'
WHERE X.COD_MENU = '50010000';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/seguridad/idiomaList.xhtml'
WHERE X.COD_MENU = '50020000';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/seguridad/menuList.xhtml'
WHERE X.COD_MENU = '50030000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/seguridad/rolList.xhtml'
WHERE X.COD_MENU = '50040000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/seguridad/usuarioList.xhtml'
WHERE X.COD_MENU = '50050000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/seguridad/opcionList.xhtml'
WHERE X.COD_MENU = '50060000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/seguridad/accesoList.xhtml'
WHERE X.COD_MENU = '50070000';

/* SEGURIDAD SICC - MANTENIMIENTOS   * */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/seguridad/mantenimientoRolSICCList.xhtml'
WHERE X.COD_MENU = '70000200';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/mantenimientoINCConfiguracionFaltanteList.xhtml'
WHERE X.COD_MENU = '20231400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDMontoMaximoSearchForm.xhtml'
WHERE X.COD_MENU = '20241000';

/* OCR MANTENIMIENTOS*/ 

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoOCRAnulaPedidosFacturadosList.xhtml'
WHERE X.COD_MENU = '20010108';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDResultadoChequeoList.xhtml'
WHERE X.COD_MENU = '20280300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDClasificacionesChequeoList.xhtml'
WHERE X.COD_MENU = '20280400';

/**
 * PROCESOS DE USUARIO - COMISIONES - BONOS
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/comision/procesoCOMCargaBonosForm.xhtml'
WHERE X.COD_MENU = '20010727';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/mantenimientoSTOExcepcionValidaDeudaList.xhtml'
WHERE X.COD_MENU = '20051700';

/**
 * PROCESOS DE USUARIO - PEDIDOS
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDNumerosFacturacionSearchForm.xhtml'
WHERE X.COD_MENU = '20241200';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPEDCargaMasivaFletesForm.xhtml'
WHERE X.COD_MENU = '20241150';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDOrigenChequeoForm.xhtml'
WHERE X.COD_MENU = '20280100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPEDCargarDemandaAnticipadaForm.xhtml'
WHERE X.COD_MENU = '20240800';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPEDCargaArchivoDesarrolladoraVentasForm.xhtml'
WHERE X.COD_MENU = '20240600';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPEDCargaCUVRecuperarSiguienteSemanaForm.xhtml'
WHERE X.COD_MENU = '20240900';

/**
 * PROCESOS DE USUARIO - MATRIZ
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPREModificacionesMasivasForm.xhtml'
WHERE X.COD_MENU = '20261200';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoPREMatrizCargaEstimadosForm.xhtml'
WHERE X.COD_MENU = '20261100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDDefinirOfertaForm.xhtml'
WHERE X.COD_MENU = '20261900';

/*  SMS  - MANTENIMIENTOS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sms/mantenimientoSMSList.xhtml'
WHERE X.COD_MENU = '20480100';


/* MENSAJES  - MANTENIMIENTOS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/men/mantenimientoMENHabilitacionProcesoMensajesForm.xhtml'
WHERE X.COD_MENU = '20250500';


/* SECUENCIACION DE ZONAS - MANTENIMIENTOS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/app/procesoAPPSecuenciarZonaTerritorioForm.xhtml'
WHERE X.COD_MENU = '20330200';


/* MAV */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mav/mantenimientoMAVConfiguracionListActividadTipoOfertaList.xhtml'
WHERE X.COD_MENU = '20350900';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mav/procesoMAVAsignacionReemplazoGerenteRegionForm.xhtml'
WHERE X.COD_MENU = '20350100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mav/procesoMAVAsignacionReemplazoGerenteZonaForm.xhtml'
WHERE X.COD_MENU = '20350200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/mav/consultaMAVAbastecimientoMaterialList.xhtml'
WHERE X.COD_MENU = '20350400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/proyeccion/mantenimientoPRYPorcentajeFaltanteList.xhtml'
WHERE X.COD_MENU = '20270100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/gen/procesoGENProcesosCierreCampaniaForm.xhtml'
WHERE X.COD_MENU = '30010732';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDTipoChequeoForm.xhtml'
WHERE X.COD_MENU = '20280200';

/**
 * PROCESOS DE USUARIO - SECUENCIACION DE ZONAS Y TERRITORIOS
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/app/procesoAPPCargarSecuenciaZonaForm.xhtml'
WHERE X.COD_MENU = '20330400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/gen/procesoGENProcesarCierreZonaForm.xhtml'
WHERE X.COD_MENU = '30011503';

update SEG_MENU set pag_xml = '/pages/scsicc/consultaBASProcesoBatchHistoList.xhtml' where cod_menu = '40021400';
update SEG_MENU set pag_xml = '/pages/scsicc/consultaBASProcesoBatchActuaList.xhtml' where cod_menu = '40021300';
update SEG_MENU set pag_xml = '/pages/spusicc/lideres/mantenimientoLIDProductosCanastaList.xhtml' where cod_menu = '20020600';


/**
 * PROCESOS DE USUARIO - FLEXIPAGO
 */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/flx/mantenimientoFLXCargaContratosList.xhtml'
WHERE X.COD_MENU = '20440300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/consultaRECCierreBRForm.xhtml'
WHERE X.COD_MENU = '20082800';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/flx/mantenimientoFLXConsultoraList.xhtml'
WHERE X.COD_MENU = '20440100';

/**
 * PROCESOS DE USUARIO - EMRENDEDORAS
 */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/emprendedoras/procesoEMPCargaPreEmprendedorasUpload.xhtml'
WHERE X.COD_MENU = '20470100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoOperacionCDRUsuarioList.xhtml'
WHERE X.COD_MENU = '20082600';

/**
 * PROCESOS DE USUARIO - RECLAMOS
 */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/emprendedoras/procesoEMPCargaPreEmprendedorasUpload.xhtml'
WHERE X.COD_MENU = '20470100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/reclamos/mantenimientoRECCronogramaBRList.xhtml'
WHERE X.COD_MENU = '20081700';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/reclamos/mantenimientoRECExcepcionesTruequesForm.xhtml'
WHERE X.COD_MENU = '20082200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/reclamos/mantenimientoRECMotivoDevolucionSearchForm.xhtml'
WHERE X.COD_MENU = '20082500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/reclamos/mantenimientoRECDigitacionBoletasRecojoList.xhtml'
WHERE X.COD_MENU = '20080100';

/*CUENTA CORRIENTE MANTENIMIENTOS*/

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCDigitacionPagosBancariosManualesForm.xhtml'
WHERE X.COD_MENU = '20220005';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCDiferenciaPreciosList.xhtml'
WHERE X.COD_MENU = '20220100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCInteresesMoraForm.xhtml'
WHERE X.COD_MENU = '20220049';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCDigitacionPagosChequesList.xhtml'
WHERE X.COD_MENU = '20220038';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCRegularizacionPagosBancariosList.xhtml'
WHERE X.COD_MENU = '20220002';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/mantenimientoCCCLiquidacionLoteBancarioForm.xhtml'
WHERE X.COD_MENU = '20220004';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/mantenimientoCCCDigitacionExternaPagosBancariosForm.xhtml'
WHERE X.COD_MENU = '20220039';



/* FLX MANTENIMIENTOS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flx/mantenimientoFLXConsultoraObjetadaList.xhtml'
WHERE X.COD_MENU = '20440200';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/reclamos/mantenimientoRECCodigoVentaOperaList.xhtml'
WHERE X.COD_MENU = '20081300';


UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/men/mantenimientoMENEscaleraGananciaList.xhtml'
WHERE X.COD_MENU = '20250700';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoOCRPreAlternativosAutomaticosList.xhtml'
WHERE X.COD_MENU = '20260100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoPEDFleteSearchForm.xhtml'
WHERE X.COD_MENU = '20240500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/men/mantenimientoMENMensajeCodigoVentaList.xhtml'
WHERE X.COD_MENU = '20250800';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/mantenimientoINCPremiosElectivosForm.xhtml'
WHERE X.COD_MENU = '20230400';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/inc/mantenimientoINCPremiosElectivosList.xhtml'
WHERE X.COD_MENU = '20230300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoExcepcionesValidacionesList.xhtml'
WHERE X.COD_MENU = '20082900';

/**
 * OPERACION - EJECUCION DE PROCESOS - PROCESOS DE IMPRESION
 */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spisicc/interfazIMPGeneracionDocumentosNumeroInternoForm.xhtml'
WHERE X.COD_MENU = '30010812';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spisicc/procesoIMPGeneracionProcesoNoDependeFacturacionForm.xhtml'
WHERE X.COD_MENU = '30010814';

/* PROGRAMA LIDERES PROCESO */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/lideres/procesoLIDCargaPedidosObjetivosForm.xhtml'
WHERE X.COD_MENU = '20020700';

/* MAE - PROCESOS */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mae/procesoMAECargaBloqueoDesbloqueoMasivoForm.xhtml'
WHERE X.COD_MENU = '20092200';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mae/procesoMAECargaMasivaInformacionForm.xhtml'
WHERE X.COD_MENU = '20092300';

/* STO - PROCESOS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/sto/procesoSTOLevantamientoErroresValidacionForm.xhtml'
WHERE X.COD_MENU = '20050200';

/*FACTURACION- MANTENIMIENTOS */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/fac/mantenimientoFACDeshabilitarZonasEnvioBoletasPremioList.xhtml'
WHERE X.COD_MENU = '20400500';

/* CUENTA CORRIENTE  */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCCargarCADDocumentoLegalMasivosUpload.xhtml'
WHERE X.COD_MENU = '20220010';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/cuentacorriente/procesoCCCCargarPagosBancariosPorRegularizarMasivosUpload.xhtml'
WHERE X.COD_MENU = '20220007';

/**
 * PROCESO DE USUARIOS - COBRANZAS
 */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/consultaOCRSolicitudesCreditoForm.xhtml'
WHERE X.COD_MENU = '20011100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/procesoOCREliminarSolicitudesCreditoForm.xhtml'
WHERE X.COD_MENU = '20011200';

/*
 * INCENTIVOS REPORTE
 * */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/inc/procesoINCMigracionPuntosConsultoraForm.xhtml'
WHERE X.COD_MENU = '20231600';

/* SAP-BPS  */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/procesoSABCargarFuenteVentaPrevistaUpload.xhtml'
WHERE X.COD_MENU = '20010503';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/mantenimientoSABFuenteVentasPrevistaList.xhtml'
WHERE X.COD_MENU = '20010501';

/* MENSAJES */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/men/procesoMENCargaMasivaInformacionMensajesForm.xhtml'
WHERE X.COD_MENU = '20251100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/scsicc/reporteSICFacturacionMatrizForm.xhtml'
WHERE X.COD_MENU = '40021807';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoRECListaBlancaProductosList.xhtml'
WHERE X.COD_MENU = '20082400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/ruv/mantenimientoRUVDocumentosContablesForm.xhtml'
WHERE X.COD_MENU = '30023402';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/ruv/mantenimientoRUVDocumentosContablesVenezuelaForm.xhtml'
WHERE X.COD_MENU = '30023406';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/fdv/mantenimientoFDVClusterizacionList.xhtml'
WHERE X.COD_MENU = '20420100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoOCRCargaPedidoList.xhtml'
WHERE X.COD_MENU = '20200200';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPEDGestionStockList.xhtml'
WHERE X.COD_MENU = '20260900';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/consultaPREMatrizFacturacionNulosDuplicadosForm.xhtml'
WHERE X.COD_MENU = '20261000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCDigitacionCADForm.xhtml'
WHERE X.COD_MENU = '20220001';

/* OCR */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoOCRActualizaPedidosBloqueoMasivoList.xhtml'
WHERE X.COD_MENU = '20010107';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/procesoOCRDepuraBolsaPedidosList.xhtml'
WHERE X.COD_MENU = '20010103';

/* GESTION DE PEDIDOS DIGITADOS */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoOCREliminarPedidosDigitadosList.xhtml'
WHERE X.COD_MENU = '20200400';

/* Programa Let */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/let/mantenimientoLETProgramaCorporativoList.xhtml'
WHERE X.COD_MENU = '20372100';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lec/mantenimientoLECProgramaCorporativoList.xhtml'
WHERE X.COD_MENU = '20372500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/lec/procesoLECCargaDatosExcelForm.xhtml'
WHERE X.COD_MENU = '20370900';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/zon/mantenimientoZONDirectorioVentasSearchForm.xhtml'
WHERE X.COD_MENU = '20014100';

UPDATE SEG_MENU 
SET PAG_XML = '/pages/spusicc/comision/mantenimientoCOMComisionesCobranzaList.xhtml' 
WHERE COD_MENU = '20010744';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteRECControlRecojosForm.xhtml'
WHERE X.COD_MENU = '40023104';

/* PEDIDOS MANTENIMIENTO */

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoPEDConfiguracionOfertasPorFactorRepeticionList.xhtml'
WHERE X.COD_MENU = '20261500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoPEDMatrizFacturacionList.xhtml'
WHERE X.COD_MENU = '20261600';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoPEDConsultoraChequeoForm.xhtml'
WHERE X.COD_MENU = '20280500';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoPEDNivelesRiesgoChequearForm.xhtml'
WHERE X.COD_MENU = '20280700';

UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoPEDUnidadesAdministrativasChequeoForm.xhtml'
WHERE X.COD_MENU = '20280600';

/* GESTION DE CONSULTORAS */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/flexipago/mantenimientoFLXGestionConsultorasList.xhtml'
WHERE X.COD_MENU = '20490108';

/* FACTURACION */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spisicc/interfazIMPGeneracionDocumentosNumeroInternoForm.xhtml'
WHERE X.COD_MENU = '20400300';

/* INCENTIVOS PROCESOS */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/procesoINCReemplazoPremioBolsaFaltantesForm.xhtml'
WHERE X.COD_MENU = '20231300';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/procesoINCCargaPremiosExcelForm.xhtml'
WHERE X.COD_MENU = '20230900';

/* MAE REPORTES */

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mae/procesoMAEEliminarClasificacionClienteForm.xhtml'
WHERE X.COD_MENU = '20090400';

/* RECLAMOS CONSULTAS*/

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/consultaRECBoletaRecojoForm.xhtml'
WHERE X.COD_MENU = '20080900';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/consultaRECCDRsDigitadosList.xhtml'
WHERE X.COD_MENU = '20081100';

/*  COBRANZAS */
UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cobranzas/procesoCOBCargaMasivaGestionesUpload.xhtml'
WHERE X.COD_MENU = '20012201';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/procesoOCRActualizarUnidadesMaximasList.xhtml'
WHERE X.COD_MENU = '20010207';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCDigitacionCargosDirectosForm.xhtml'
WHERE X.COD_MENU = '20220046';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/cuentacorriente/mantenimientoCCCDigitacionAbonosDirectosForm.xhtml'
WHERE X.COD_MENU = '20220047';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/reclamos/mantenimientoRECGestionBoletasRecojoNoExitosasList.xhtml'
WHERE X.COD_MENU = '20080200';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCCCDetalladoConsultorasIncobrableForm.xhtml'
WHERE X.COD_MENU = '40020987';

/* MATRIZ */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoPEDSeleccionMatrizFacturacionList.xhtml'
WHERE X.COD_MENU = '20261700';

/* OCR */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pedidos/mantenimientoOCRArchivoControlMultihiloList.xhtml'
WHERE X.COD_MENU = '20010125';

/* PRE MANTENIMIENTO */
UPDATE SEG_MENU X
SET X.PAG_XML = '/pages/spusicc/pre/mantenimientoPREValidacionMatrizList.xhtml'
WHERE X.COD_MENU = '20261400';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/inc/mantenimientoINCProgramasConstanciaList.xhtml'
WHERE X.COD_MENU = '20232000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mae/mantenimientoINCGarantiasPremiosForm.xhtml'
WHERE X.COD_MENU = '20231900';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/datosbasicos/mantenimientoBASParametroSegmentacionForm.xhtml'
WHERE X.COD_MENU = '10012000';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mae/mantenimientoMAEExcencionSobreFleteList.xhtml'
WHERE X.COD_MENU = '20092800';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/pedidos/mantenimientoPREMatrizAlternativosList.xhtml'
WHERE X.COD_MENU = '20262100';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/mae/mantenimientoMAEEntidadGenericaList.xhtml'
WHERE X.COD_MENU = '20092900';

/* INTERFACES POL*/

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/sisicc/interfazPOLEnviarInformacionPortalForm.xhtml'
WHERE X.COD_MENU = '30026100';


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/let/reporteLETLideresForm.xhtml'
WHERE X.COD_MENU = '40024500';

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/mantenimientoMAEClienteForm.xhtml'
WHERE X.COD_MENU = '20090100';


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/sto/consultaMAEClienteList.xhtml'
WHERE X.COD_MENU = '20090200';

/* REPORTE CAL*/

UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/scsicc/reporteCALConsultorasRecomendadasForm.xhtml'
WHERE X.COD_MENU = '40020601';


UPDATE SEG_MENU X
SET X.PAG_XML= '/pages/spusicc/fac/procesoFACEliminarTablasTemporalesForm.xhtml'
WHERE X.COD_MENU = '20400600';

UPDATE SEG_MENU X
SET X.pag_jmen = x.PAG_XML
WHERE X.PAG_XML is not null AND
      X.PAG_JMEN IS NULL;

UPDATE SEG_MENU 
SET PAG_XML = '/pages/scsicc/reporteRECCuadreSAPForm.xhtml' 
WHERE COD_MENU = '40021033';
      
UPDATE SEG_MENU 
SET EST_MENU = '9' 
WHERE COD_MENU = '30011602';

UPDATE SEG_MENU
   SET PAG_XML = '/pages/spusicc/reclamos/mantenimientoRECDigitacionCDRAjaxForm.xhtml'
 WHERE COD_MENU IN ('20080600','20081500');
 
commit;