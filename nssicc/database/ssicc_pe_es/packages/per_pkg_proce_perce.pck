CREATE OR REPLACE PACKAGE "PER_PKG_PROCE_PERCE" IS

 /* Declaracion de Tipos */
 TYPE TIPOCURSOR IS REF CURSOR;
 TYPE TABLA_PER_CUENT_CORRI_DOCLE IS TABLE OF
 PER_CUENT_CORRI_DOCLE%ROWTYPE INDEX BY BINARY_INTEGER;
 TYPE TABLA_CCC_MARCA_TIPO_ABONO IS TABLE OF
 ccc_marca_tipo_abono%ROWTYPE INDEX BY BINARY_INTEGER;
 TYPE TABLA_PER_PROCE_SUBPR_PROGR IS TABLE OF
 PER_PROCE_SUBPR_PROGR%ROWTYPE INDEX BY BINARY_INTEGER;
 TYPE TABLA_PER_MOVIM_BANCA_DETAL IS TABLE OF
 PER_MOVIM_BANCA_DETAL%ROWTYPE INDEX BY BINARY_INTEGER;
 TYPE TABLA_CCC_MOVIM_CUENT_CORRI IS TABLE OF
 CCC_MOVIM_CUENT_CORRI%ROWTYPE INDEX BY BINARY_INTEGER;
 TYPE tRegCargoAbonoDirecto IS RECORD(
 oid_movi_cc ccc_movim_cuent_corri.oid_movi_cc%TYPE,
 soci_oid_soci ccc_movim_cuent_corri.soci_oid_soci%TYPE,
 masi_oid_marc_situ ccc_movim_cuent_corri.masi_oid_marc_situ%TYPE,
 mone_oid_mone ccc_movim_cuent_corri.mone_oid_mone%TYPE,
 perd_oid_peri ccc_movim_cuent_corri.perd_oid_peri%TYPE,
 tcab_oid_tcab_ulti ccc_movim_cuent_corri.tcab_oid_tcab_ulti%TYPE,
 tcab_oid_tcab_crea ccc_movim_cuent_corri.tcab_oid_tcab_crea%TYPE,
 sbac_oid_sbac ccc_movim_cuent_corri.sbac_oid_sbac%TYPE,
 subp_oid_subp_ulti ccc_movim_cuent_corri.subp_oid_subp_ulti%TYPE,
 subp_oid_subp_crea ccc_movim_cuent_corri.subp_oid_subp_crea%TYPE,
 cuco_oid_cuen_cont_orig ccc_movim_cuent_corri.cuco_oid_cuen_cont_orig%TYPE,
 cuco_oid_cuen_cont_cuot ccc_movim_cuent_corri.cuco_oid_cuen_cont_cuot%TYPE,
 clie_oid_clie ccc_movim_cuent_corri.clie_oid_clie%TYPE,
 val_docu_anio ccc_movim_cuent_corri.val_docu_anio%TYPE,
 val_docu_mes_seri ccc_movim_cuent_corri.val_docu_mes_seri%TYPE,
 val_docu_nume ccc_movim_cuent_corri.val_docu_nume%TYPE,
 val_ejer_cuot ccc_movim_cuent_corri.val_ejer_cuot%TYPE,
 fec_conta ccc_movim_cuent_corri.fec_conta%TYPE,
 fec_docu ccc_movim_cuent_corri.fec_docu%TYPE,
 fec_ulti_movi ccc_movim_cuent_corri.fec_ulti_movi%TYPE,
 fec_valo ccc_movim_cuent_corri.fec_valo%TYPE,
 fec_venc ccc_movim_cuent_corri.fec_venc%TYPE,
 imp_movi ccc_movim_cuent_corri.imp_movi%TYPE,
 imp_divi ccc_movim_cuent_corri.imp_divi%TYPE,
 imp_paga ccc_movim_cuent_corri.imp_paga%TYPE,
 imp_paga_divi ccc_movim_cuent_corri.imp_paga_divi%TYPE,
 imp_pend ccc_movim_cuent_corri.imp_pend%TYPE,
 num_iden_cuot ccc_movim_cuent_corri.num_iden_cuot%TYPE,
 val_nume_lote_cont ccc_movim_cuent_corri.val_nume_lote_cont%TYPE,
 num_orde_cuot ccc_movim_cuent_corri.num_orde_cuot%TYPE,
 val_obse ccc_movim_cuent_corri.val_obse%TYPE,
 val_refe_nume_docu_exte ccc_movim_cuent_corri.val_refe_nume_docu_exte%TYPE,
 val_ulti_docu_anio ccc_movim_cuent_corri.val_ulti_docu_anio%TYPE,
 val_ulti_docu_mes_seri ccc_movim_cuent_corri.val_ulti_docu_mes_seri%TYPE,
 val_ulti_docu_nume ccc_movim_cuent_corri.val_ulti_docu_nume%TYPE,
 val_ulti_nume_hist ccc_movim_cuent_corri.val_ulti_nume_hist%TYPE,
 mpab_oid_medi_pago ccc_movim_cuent_corri.mpab_oid_medi_pago%TYPE,
 ztad_oid_terr_admi ccc_movim_cuent_corri.ztad_oid_terr_admi%TYPE,
 ticl_oid_tipo_clie ccc_movim_cuent_corri.ticl_oid_tipo_clie%TYPE,
 marc_oid_marc ccc_movim_cuent_corri.marc_oid_marc%TYPE,
 imp_pago ccc_movim_cuent_corri.imp_pago%TYPE,
 imp_pago_divi ccc_movim_cuent_corri.imp_pago_divi%TYPE,
 imp_movi_cuen ccc_movim_cuent_corri.imp_movi_cuen%TYPE,
 imp_movi_divi ccc_movim_cuent_corri.imp_movi_divi%TYPE,
 num_lote_fact ccc_movim_cuent_corri.num_lote_fact%TYPE,
 cod_usua ccc_movim_cuent_corri.cod_usua%TYPE,
 ind_tipo_camb ccc_movim_cuent_corri.ind_tipo_camb%TYPE,
 ind_dto_carg_apli ccc_movim_cuent_corri.ind_dto_carg_apli%TYPE,
 zscc_oid_secc ccc_movim_cuent_corri.zscc_oid_secc%TYPE,
 zsgv_oid_subg_vent ccc_movim_cuent_corri.zsgv_oid_subg_vent%TYPE,
 soca_oid_soli_cabe ccc_movim_cuent_corri.soca_oid_soli_cabe%TYPE,
 sbti_oid_subt_clie ccc_movim_cuent_corri.sbti_oid_subt_clie%TYPE,
 tspa_oid_tipo_soli_pais ccc_movim_cuent_corri.tspa_oid_tipo_soli_pais%TYPE,
 tipe_oid_tipo_peri ccc_movim_cuent_corri.tipe_oid_tipo_peri%TYPE,
 zorg_oid_regi ccc_movim_cuent_corri.zorg_oid_regi%TYPE,
 tido_oid_tipo_docu ccc_movim_cuent_corri.tido_oid_tipo_docu%TYPE,
 fec_ulti_actu ccc_movim_cuent_corri.fec_ulti_actu%TYPE,
 oid_proc PER_GTT_PROCE_PROGR.oid_proc%TYPE,
 cod_proc PER_GTT_PROCE_PROGR.cod_proc%TYPE,
 oid_subp PER_GTT_PROCE_PROGR.oid_subp%TYPE,
 cod_subp PER_GTT_PROCE_PROGR.cod_subp%TYPE,
 val_indi_actu_cuot PER_GTT_PROCE_PROGR.val_indi_actu_cuot%TYPE,
 tip_soli_impo_posi PER_GTT_PROCE_PROGR.tip_soli_impo_posi%TYPE,
 tip_soli_impo_nega PER_GTT_PROCE_PROGR.tip_soli_impo_nega%TYPE,
 ind_reca_impo_nega PER_GTT_PROCE_PROGR.ind_reca_impo_nega%TYPE,
 oid_tipo_abon_subp PER_GTT_PROCE_PROGR.oid_tipo_abon_subp%TYPE,
 tcab_oid_tcab PER_GTT_PROCE_PROGR.tcab_oid_tcab%TYPE,
 cod_tipo_carg_abon PER_GTT_PROCE_PROGR.cod_tipo_carg_abon%TYPE,
 oid_marc_situ PER_GTT_PROCE_PROGR.oid_marc_situ%TYPE,
 cod_marc_situ PER_GTT_PROCE_PROGR.cod_marc_situ%TYPE,
 oid_Deta_cargo CCC_DETAL_CARGO_ABONO_DIREC.OID_DETA_CARG_ABON_DIRE%TYPE
 );

 /* Declaracion de Variables */
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(1000);
 gn_lineaError NUMBER(10);

 /* Declaracion de Funciones */
 /**************************************************************************
Descripcion : Devuelve Correlativo de tabla PER_CONTR_REGIS_PROGR
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_CORRE_REGIS_PROGR(
 psCodPais VARCHAR2,
 psCodProg VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion : Actualiza Correlativo en tabla PER_CONTR_REGIS_PROGR
Fecha Creacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_UPDAT_CORRE_REGIS_PROGR(
 psCodPais VARCHAR2,
 psCodProg VARCHAR2,
 pnCorrelativo NUMBER);

/**************************************************************************
Descripcion : Actualiza Correlativo Agrupado en la Tabla de
 Solicitudes Monetarias por el Proceso de Generacion de
 Cta Corriente
Fecha Creacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_CORRE_MONET_AGRUP_FACTU(
 psCodPais VARCHAR2,
 psNumLote VARCHAR2,
 psTipoOrigen VARCHAR2,
 psCodProg VARCHAR2);

/**************************************************************************
Descripcion : Actualiza Correlativo Agrupado en la Tabla de
 Solicitudes Monetarias por el Proceso de
 Procesar Movimiento
Fecha Creacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_CORRE_MONET_AGRUP_MOVIM(
 psCodPais VARCHAR2,
 psNumLote VARCHAR2,
 psTipoOrigen VARCHAR2,
 psCodProg VARCHAR2);

 /**************************************************************************
 Descripcion : Devuelve indicador de Emision de Vencimiento
 Fecha Creacion : 19/09/2006
 Autor : Carlos Bazalar
 ***************************************************************************/
 FUNCTION PER_FN_DEVUE_INDIC_EMIS_VENC (pn_idpais NUMBER)
 RETURN VARCHAR2;

 /**************************************************************************
 Descripcion : Devuelve ultimo OID de la la tabla de Movimientos Cta Cte
 CCC_MOVIM_CUENT_CORRI
 Fecha Creacion : 19/09/2006
 Autor : Carlos Bazalar
 ***************************************************************************/
 FUNCTION PER_FN_ULTI_OID_MOVI_CC(ps_CodPais VARCHAR2, ps_codProg VARCHAR2, pn_oidUltimoRegistro NUMBER)
 RETURN NUMBER;


 /**************************************************************************
 Descripcion : Devuelve correlativo siguiente de la Tabla PER_CUENT_CORRI_DOCLE
 correspondiente al Secuencial de Numero de Orden de Couta
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pRegistro : Registro de Tabla PER_CUENT_CORRI_DOCLE
 Autor : Carlos Bazalar
 ***************************************************************************/
 FUNCTION PER_FN_SGTE_SEC_ORDEN_COUTA(pRegistro PER_CUENT_CORRI_DOCLE%ROWTYPE)
 RETURN NUMBER;

 /**************************************************************************
 Descripcion : Devuelve correlativo siguiente de la Tabla PER_REG_PAGO
 correspondiente al Secuencial de Abono
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pRegistroPago : Registro de Tabla PER_REG_PAGO
 Autor : Carlos Bazalar
 ***************************************************************************/
 FUNCTION PER_FN_SGTE_SEC_ABON(pRegistroPago per_regis_pagos%ROWTYPE) RETURN NUMBER;

 /**************************************************************************
 Descripcion : Devuelve correlativo siguiente de la Tabla PER_CUENT_CORRI_DOCLE
 Fecha Creacion : 21/09/2006
 Autor : Carlos Bazalar
 ***************************************************************************/
 FUNCTION PER_FN_SGTE_CORRE_CORRI_DOCLE RETURN NUMBER;

 /**************************************************************************
 Descripcion : Devuelve correlativo siguiente de la Tabla PER_REG_PAGO
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 psCodigoPais : Codigo de Pais
 Autor : Carlos Bazalar
 ***************************************************************************/
 FUNCTION PER_FN_SGTE_CORRE_REG_PAGO RETURN NUMBER;

 /**************************************************************************
 Descripcion : Devuelve correlativo siguiente de la Tabla PER_SOLI_MONE
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 psCodigoPais : Codigo de Pais
 Autor : Carlos Bazalar
 ***************************************************************************/
 FUNCTION PER_FN_SGTE_CORRE_SOLI_MONE RETURN NUMBER;

 /**************************************************************************
 Descripcion : Procedimiento auxiliar de Cargo y Abono directo e Incobrables
 Fecha Creacion : 19/09/2006
 Autor : Carlos Bazalar
 ***************************************************************************/
 FUNCTION PER_FN_RECU_CARGO_ABONO_DIREC(
 psCodPais VARCHAR2,
 regRecupera tRegCargoAbonoDirecto,
 pbInsertarSiempre BOOLEAN := FALSE,
 pnImporteDocu NUMBER := 0)
 RETURN per_cuent_corri_docle%ROWTYPE;


 /* Declaracion de Procedures */
 /**************************************************************************
 Descripcion : Inserta Registro en la Tabla PER_CUENT_CORRI_DOCLE
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pRegistro : Registro Rowtype que se usara para insertar en la Tabla
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE INSERT_PER_CUENT_CORRI_DOCLE(pRegistro per_cuent_corri_docle%ROWTYPE);

 /**************************************************************************
 Descripcion : Inserta Registro en la Tabla PER_REGIS_PAGOS
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pRegistro : Registro Rowtype que se usara para insertar en la Tabla
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE INSERT_PER_REGIS_PAGOS(pRegistro per_regis_pagos%ROWTYPE);

 /**************************************************************************
 Descripcion : Inserta Registro en la Tabla PER_SOLI_MONE
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pRegistro : Registro Rowtype que se usara para insertar en la Tabla
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE INSERT_PER_SOLI_MONE(pRegistro Per_Solic_Monet%ROWTYPE);

 /**************************************************************************
 Descripcion : Procedimiento que efectua la Cancelacion Automatica de
 Deuda
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 psTipoOrigen : Tipo de Origen de Datos
 psNumeroLote : Numero de Lote
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE PER_PR_CANCE_AUTOM_DEUDA
 (pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2);


 /**************************************************************************
 Descripcion : Procedimiento que efectua la Cancelacion Automatica de
 Deuda
 Fecha Creacion : 21/09/2006
 Parametros Entrada :
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 pbCargoAbonoIncobrale : False: Por defecto
 True: Procedimiento es invocado desde
 CU Procesar cargos y abonos directos e incobrables
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE PER_PR_DEUDA_POSIT(pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pbCargoAbonoIncobrale BOOLEAN := FALSE,
 psCodProcCtaCancelacion VARCHAR2 := NULL,
 psCodSProcCtaCancelacion VARCHAR2 := NULL );

 /**************************************************************************
 Descripcion : Procedimiento que efectua Cargo y Abono directo e Incobrables
 desde Generacion de Cta Cte
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 psCodPais : Codigo de Pais
 psTipoOrigen : Tipo de Origen de Datos
 psNumeroLote : Numero de Lote
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE PER_PR_PROCE_CARGO_ABONO_DIREC(psCodPais VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pnUltimoOid IN OUT NUMBER);

 /**************************************************************************
 Descripcion : Procedimiento que efectua Cargo y Abono directo e Incobrables

 desde el Menu de la aplicacion
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 psCodPais : Codigo de Pais
 psTipoOrigen : Tipo de Origen de Datos
 psNumeroLote : Numero de Lote
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE PER_PR_PROCE_CARGO_ABONO_MENU(psCodPais VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2);


 /**************************************************************************
 Descripcion : Procedimiento auxiliar del procedure PER_PR_CANCE_AUTOM_DEUDA que
 actualiza los montos de la Couta a Cancelar
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pn_val_indi_actu : Indicador de Actualiza Couta con importe pagado
 ps_codMarcaSalida : Marca de Salida
 ps_tipo_Cargo_Abon: Tipo de Cargo Abono
 psTipoOrigen : Tipo de Origen de datos
 psNumeroLote : Numero de Lote
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta de Cancelacion
 pregCuentaACancelar : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta a Cancelar
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE PER_PR_ACTU_IMPORTE_CANCELAR
 ( pn_val_indi_actu NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 pregCuentaACancelar per_cuent_corri_docle%ROWTYPE);

 /**************************************************************************
 Descripcion : Procedimiento auxiliar del procedure PER_PR_DEUDA_POSITIVA que
 actualiza los montos de la Couta a Cancelar
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pn_val_indi_actu : Indicador de Actualiza Couta con importe pagado
 ps_codMarcaSalida : Marca de Salida
 ps_tipo_Cargo_Abon: Tipo de Cargo Abono
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta de Cancelacion
 pregCuentaACancelar : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta a Cancelar
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE PER_PR_ACTU_IMPORTE_ABONO
 ( pn_val_indi_actu NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 ps_cod_peri VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 pregCuentaACancelar per_cuent_corri_docle%ROWTYPE);

 /**************************************************************************
 Descripcion : Funcion auxiliar del procedure PER_PR_DEUDA_POSITIVA que
 actualiza los montos de la Couta a Cancelar
 Fecha Creacion : 19/09/2006
 Parametros Entrada :
 pn_val_indi_actu : Indicador de Actualiza Couta con importe pagado
 ps_codMarcaSalida : Marca de Salida
 ps_tipo_Cargo_Abon: Tipo de Cargo Abono
 psTipoSoliExcesoPosi : Tipo de Solicitud Pagos en exceso positivos
 psTipoSoliApliPago : Tipo de Solicitud Aplicacion Pagos en exceso
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta de Cancelacion
 pregCuentaACancelar : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta a Cancelar
 Autor : Carlos Bazalar
 ***************************************************************************/
 PROCEDURE PER_PR_ACTU_IMPORTE_PAGO_EXCE
 ( pn_val_indi_actu NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 psTipoSoliExcesoPosi VARCHAR2,
 psTipoSoliApliPago VARCHAR2,
 psTipoSoliPerc VARCHAR2,
 ps_cod_peri VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 pregCuentaACancelar per_movim_banca_detal%ROWTYPE);


 PROCEDURE PER_PR_ACTU_IMPORTE_RECUPERADA
 ( pn_val_indi_actu NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 ps_tipo_soli VARCHAR2,
 ps_ind_recauda VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 psCodPeri VARCHAR2,
 pnImporteMovim IN OUT NUMBER,
 psCodProc VARCHAR2,
 psCodSubp VARCHAR2,
 pregCuentaACancelar per_cuent_corri_docle%ROWTYPE);




/************************************************************************************************
Descripcion : Generacion de Documentos Contables Cabecera o Solicitudes Cabecera
Fecha Creacion : 15/08/2006
Fecha Modificacion : 07/09/2006
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
 p_usuDigi : Usuario Logueado
Parametros Salida :
Autor : David Toledo - dtoledo@belcorp.biz
Modificado por : Jose Martinez - jamartinez@belcorp.biz
Version : Beta (Beta|Final)
Cambios Importantes : Ninguno
*************************************************************************************************/
 PROCEDURE PER_PR_CABEC_DETAL_DOLSO (p_codigoPais IN VARCHAR2,
 p_usuDigi IN VARCHAR2,
 p_tipOrigenDatos IN VARCHAR2,
 p_codigoInterfaz IN VARCHAR2,
 p_numeroLote OUT VARCHAR2);

/***************************************************************************************************
Descripcion : Inserta registros de Cabecera y Detalle en Temporal
Fecha Creacion : 06/09/2006
Fecha Modificacion : 06/09/2006
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
Parametros Salida :
Autor : Jose Martinez Vargas
Version : Beta (Beta|Final)
Cambios Importantes : Ninguno
****************************************************************************************************/
 PROCEDURE PER_PR_CABEC_DETAL_DOLSO_TEMPO (p_codigoPais IN VARCHAR2, p_ultimoRegistro OUT NUMBER);


/************************************************************************************************
Descripcion : Inserta en la estructura desglose forma de pagos
Fecha Creacion : 07/09/2006
Fecha Modificacion :
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
Parametros Salida :
Autor : Richard de lso Reyes - rdelosreyes@belcorp.biz
Version : Beta (Beta|Final)
Cambios Importantes : Ninguno
*************************************************************************************************/
PROCEDURE PER_PR_CUENT_CORRI_DOCLE (p_codigoPais IN VARCHAR2, p_usuario IN VARCHAR2,
 p_tipOrigenDatos IN VARCHAR2, p_codigoInterfaz IN VARCHAR2,
 p_numeroLote IN OUT VARCHAR2);

/************************************************************************************************
Descripcion : Inserta en la estructura desglose forma de pagos
Fecha Creacion : 07/09/2006
Fecha Modificacion :
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
Parametros Salida :
Autor : Richard de lso Reyes - rdelosreyes@belcorp.biz
Version : Beta (Beta|Final)
Cambios Importantes : Ninguno
*************************************************************************************************/
PROCEDURE PER_PR_DESGL_FORMA_PAGO (p_codigoPais IN VARCHAR2, p_usuario IN VARCHAR2);

/**************************************************************************
Descripcion : Procedimiento que efectua Procesar Movimiento
Fecha Creacion : 19/09/2006
Parametros Entrada :
 psCodPais : Codigo de Pais
 psTipoOrigen : Tipo de Origen de Datos
 psNumeroLote : Numero de Lote
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PE_PROCE_MOVIM
(p_CodPais IN VARCHAR2,
 p_tipoOrigen IN VARCHAR2,
 p_NumeroLote IN VARCHAR2,
 p_NumeroLoteExterno OUT VARCHAR2);

/**************************************************************************
Descripcion : Procedimiento que auxiliar de Procesar Movimiento
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_ACTU_IMPOR_MBANC
( pn_val_indi_actu NUMBER,
 pn_Oidpais NUMBER,
 pn_oidCanal NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 ps_tipo_soli VARCHAR2,
 ps_tipo_soli_exceso_venta VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pRegMovimientoDetalle IN OUT PER_MOVIM_BANCA_DETAL%ROWTYPE,
 pregCuentaACancelar PER_CUENT_CORRI_DOCLE%ROWTYPE,
 pnCorrelativo NUMBER );

/**************************************************************************
Descripcion : Procedimiento que efectua Cruce de Saldos Positivos
 y Negativos
Fecha Creacion : 19/09/2006
Parametros Entrada :
 psCodPais : Codigo de Pais
 psNumeroLote : Numero de Lote
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_CRUCE_SALDO_POSI_NEGA (psCodPais VARCHAR2, Psnumerolote IN OUT VARCHAR2);


FUNCTION PER_FN_DEVUE_MONTO_PERCE_VTADI(
 psCodPais VARCHAR2,
 psSerCope VARCHAR2,
 psNumCope VARCHAR2,
 psCodClie VARCHAR2
 )
RETURN NUMBER;

/**************************************************************************
Descripcion : Procedimiento del PDT que devuelve la razon social
 del cliente ingresado como parametro
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_RAZON_SOCIAL(psCodPais VARCHAR2, psCodClas VARCHAR2, psTipoDocle VARCHAR2, psCodClie VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion : Procedimiento del PDT que devuelve nombre, ap paterno o
 ap. materno del cliente ingresado como parametro
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_NOMBRES(
 psCodPais VARCHAR2,
 psCodClas VARCHAR2,
 psTipoDocle VARCHAR2,
 psCodClie VARCHAR2,
 psTipo VARCHAR2,
 psNombreAlternativo VARCHAR2
)
RETURN VARCHAR2;

FUNCTION PER_FN_DEVUE_INDI_DEREC_CREFI(psCodPais VARCHAR2, psCodClas VARCHAR2, psTipoDocle VARCHAR2)
RETURN VARCHAR2;

FUNCTION PER_FN_DEVUE_INDI_CLIEN_LISTA(psCodPais VARCHAR2, psIdTipo VARCHAR2, psNumDocum VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion : Funci?n utilizada en la Interfaz PDT que devuelve la
 Suma correspondiente al Monto de Pago
Fecha Modificacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_MONTO_PAGO(
 psCodClie VARCHAR2,
 psSerCope VARCHAR2,
 psNumCope VARCHAR2,
 psFechaDesde VARCHAR2,
 psFechaHasta VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion : Funci?n utilizada en la Interfaz PDT que devuelve el
 Monto Total
Fecha Modificacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_MONTO_TOTAL(
 psCodClie VARCHAR2,
 psSerDole VARCHAR2,
 psNumDole VARCHAR2,
 psFechaDesde VARCHAR2,
 psFechaHasta VARCHAR2)
RETURN NUMBER;

 PROCEDURE PER_PR_CARGA_MOVIM_BANCA(
 psCodigoPais VARCHAR2,
 psCodigoTipoOrigenDatos VARCHAR2,
 psNumeroLoteInterno VARCHAR2);


/**************************************************************************
Descripcion : Procedimiento del PDT que segun el valor del campo, busca el caracter a reempalzar
 y devuelve el valor cambiado
Fecha Creacion : 15/10/2009
Autor : Sergio Buchelli
parametros :
 psCodPais Codigo Pais,
 psCodBusq Codigo Busqueda,
 psValorCampo Valor Campo ,
 psCadenaTexto : Es el valor de la cadena original que va hacer reemplazadsa
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_CAMPO_REEMP(
 psCodPais VARCHAR2,
 psCodBusq VARCHAR2,
 psValorCampo VARCHAR2,
 psCadenaTexto VARCHAR2)
RETURN VARCHAR2;

/**************************************************************************
Descripcion : Funci?n utilizada en la Interfaz PDT que devuelve la
 Suma correspondiente al Monto de Pago
Fecha Modificacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_MONTO_PAGO2(
 psCodClie VARCHAR2,
 psSerCope VARCHAR2,
 psNumCope VARCHAR2,
 psFechaDesde VARCHAR2,
 psFechaHasta VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion : Funci?n utilizada en la Interfaz PDT que devuelve el
 Monto Total
Fecha Modificacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_MONTO_TOTAL2(
 psCodClie VARCHAR2,
 psSerDole VARCHAR2,
 psNumDole VARCHAR2,
 psFechaDesde VARCHAR2,
 psFechaHasta VARCHAR2)
RETURN NUMBER;

/**************************************************************************
Descripcion : Generar Resumen Diario Percepciones SUNAT
Fecha Modificacion : 11/02/2016
Autor : Karina Valencia
***************************************************************************/
 PROCEDURE PER_PR_GENER_RESUM_PERCE(
 psCodigoPais                   VARCHAR2,
 psFechaGenerar                 VARCHAR2,
 psUsuario                      VARCHAR2);
 
 /**************************************************************************
Descripcion : Reporte Resumen Diario Percepciones SUNAT -TXT
Fecha Modificacion : 11/02/2016
Autor : Karina Valencia
***************************************************************************/
PROCEDURE PER_PR_GENER_REPOR_SUNAT_TXT(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psFechaGeneracion		    VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    );

/**************************************************************************
Descripcion : Reporte Resumen Diario Percepciones SUNAT -CSV
Fecha Modificacion : 12/02/2016
Autor : Karina Valencia
***************************************************************************/   
PROCEDURE PER_PR_GENER_REPOR_SUNAT_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psFechaGeneracion       VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    ) ;


END PER_PKG_PROCE_PERCE;
/
CREATE OR REPLACE PACKAGE BODY "PER_PKG_PROCE_PERCE" IS

   TYPE gt_tab_oid_clie             IS TABLE OF mae_clien.oid_clie%TYPE;
   gc_cod_iden_proc_proc            CONSTANT VARCHAR2(1):='P';

/**************************************************************************
Descripcion : Devuelve Correlativo de tabla PER_CONTR_REGIS_PROGR
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_CORRE_REGIS_PROGR(
 psCodPais VARCHAR2,
 psCodProg VARCHAR2)
RETURN NUMBER
IS
 lnCorrelativo NUMBER;
BEGIN
 SELECT OID_ULTI_REGI_PROC
 INTO lnCorrelativo
 FROM PER_CONTR_REGIS_PROGR
 WHERE PAIS_COD_PAIS = psCodPais
 AND COD_PROG = psCodProg
 AND EST_CTRL_REGI_PROG = '1';
 RETURN lnCorrelativo ;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN 0;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_CORRE_REGIS_PROGR: '||ls_sqlerrm);
END PER_FN_DEVUE_CORRE_REGIS_PROGR;

/**************************************************************************
Descripcion : Actualiza Correlativo en tabla PER_CONTR_REGIS_PROGR
Fecha Creacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_UPDAT_CORRE_REGIS_PROGR(
 psCodPais VARCHAR2,
 psCodProg VARCHAR2,
 pnCorrelativo NUMBER)
IS

BEGIN
 UPDATE PER_CONTR_REGIS_PROGR A
 SET A.OID_ULTI_REGI_PROC = pnCorrelativo
 WHERE A.PAIS_COD_PAIS = psCodPais
 AND A.COD_PROG = psCodProg
 AND A.EST_CTRL_REGI_PROG = '1';
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_UPDAT_CORRE_REGIS_PROGR: '||ls_sqlerrm);
END PER_PR_UPDAT_CORRE_REGIS_PROGR;



/**************************************************************************
Descripcion : Actualiza Correlativo Agrupado en la Tabla de
 Solicitudes Monetarias
Fecha Creacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_CORRE_MONET_AGRUP_FACTU(
 psCodPais VARCHAR2,
 psNumLote VARCHAR2,
 psTipoOrigen VARCHAR2,
 psCodProg VARCHAR2)
IS
 CURSOR cSoli IS
 SELECT DISTINCT
 A.COD_CLIE,
 A.CON_TRAN,
 A.NUM_DOCU_REFE,
 A.COD_FORM_PAGO,
 A.COD_TIPO_SOLI

 FROM PER_SOLIC_MONET A
 WHERE A.PAIS_COD_PAIS = psCodPais
 --AND A.TIOR_TIPO_ORIG_DATO = psTipoOrigen
 AND A.NUM_LOTE = psNumLote
 AND A.COR_AGRU IS NULL
 ORDER BY
 A.COD_CLIE,
 A.CON_TRAN,
 A.NUM_DOCU_REFE,
 A.COD_FORM_PAGO,
 A.COD_TIPO_SOLI ;

 lnCorreBancario NUMBER;
BEGIN
 lnCorreBancario := PER_FN_DEVUE_CORRE_REGIS_PROGR(psCodPais, psCodProg);
 FOR C1 IN cSoli LOOP
 lnCorreBancario := lnCorreBancario + 1;
 UPDATE PER_SOLIC_MONET A
 SET A.COR_AGRU = lnCorreBancario
 WHERE A.PAIS_COD_PAIS = psCodPais
 --AND A.TIOR_TIPO_ORIG_DATO = psTipoOrigen
 AND A.NUM_LOTE = psNumLote
 AND A.COR_AGRU IS NULL
 AND A.COD_CLIE = C1.COD_CLIE
 AND NVL(A.NUM_DOCU_REFE, A.NUM_LOTE) = NVL(C1.NUM_DOCU_REFE, A.NUM_LOTE)
 AND NVL(A.COD_FORM_PAGO, A.NUM_LOTE) = NVL(C1.COD_FORM_PAGO, A.NUM_LOTE)
 AND NVL(A.COD_TIPO_SOLI, A.NUM_LOTE) = NVL(C1.COD_TIPO_SOLI, A.NUM_LOTE)
 AND NVL(A.CON_TRAN, A.NUM_LOTE) = NVL(C1.CON_TRAN, A.NUM_LOTE);

 END LOOP;

 /* Actualizando Correlativo */
 PER_PR_UPDAT_CORRE_REGIS_PROGR(psCodPais, psCodProg, lnCorreBancario);

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_CORRE_MONET_AGRUP_FACTU: '||ls_sqlerrm);
END PER_PR_CORRE_MONET_AGRUP_FACTU;


/**************************************************************************
Descripcion : Actualiza Correlativo Agrupado en la Tabla de
 Solicitudes Monetarias por el Proceso de
 Procesar Movimiento
Fecha Creacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_CORRE_MONET_AGRUP_MOVIM(
 psCodPais VARCHAR2,
 psNumLote VARCHAR2,
 psTipoOrigen VARCHAR2,
 psCodProg VARCHAR2)
IS
 CURSOR cSoli IS
 SELECT DISTINCT
 A.COD_CLIE,
 A.CON_TRAN,
 A.COD_FORM_PAGO,
 A.COD_TIPO_SOLI

 FROM PER_SOLIC_MONET A
 WHERE A.PAIS_COD_PAIS = psCodPais
 --AND A.TIOR_TIPO_ORIG_DATO = psTipoOrigen
 AND A.NUM_LOTE = psNumLote
 AND A.COR_AGRU IS NULL
 ORDER BY
 A.COD_CLIE,
 A.CON_TRAN,
 A.COD_FORM_PAGO,
 A.COD_TIPO_SOLI ;

 lnCorreBancario NUMBER;
BEGIN
 lnCorreBancario := PER_FN_DEVUE_CORRE_REGIS_PROGR(psCodPais, psCodProg);
 FOR C1 IN cSoli LOOP
 lnCorreBancario := lnCorreBancario + 1;
 UPDATE PER_SOLIC_MONET A
 SET A.COR_AGRU = lnCorreBancario
 WHERE A.PAIS_COD_PAIS = psCodPais
 --AND A.TIOR_TIPO_ORIG_DATO = psTipoOrigen
 AND A.NUM_LOTE = psNumLote
 AND A.COR_AGRU IS NULL
 AND A.COD_CLIE = C1.COD_CLIE
 AND NVL(A.COD_FORM_PAGO, A.NUM_LOTE) = NVL(C1.COD_FORM_PAGO, A.NUM_LOTE)
 AND NVL(A.COD_TIPO_SOLI, A.NUM_LOTE) = NVL(C1.COD_TIPO_SOLI, A.NUM_LOTE)
 AND NVL(A.CON_TRAN, A.NUM_LOTE) = NVL(C1.CON_TRAN, A.NUM_LOTE);

 END LOOP;

 /* Actualizando Correlativo */
 PER_PR_UPDAT_CORRE_REGIS_PROGR(psCodPais, psCodProg, lnCorreBancario);

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_CORRE_MONET_AGRUP_MOVIM: '||ls_sqlerrm);
END PER_PR_CORRE_MONET_AGRUP_MOVIM;


/**************************************************************************
Descripcion : Devuelve indicador de Emision de Vencimiento
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_INDIC_EMIS_VENC (pn_idpais NUMBER)
RETURN VARCHAR2
IS
 ln_IndEmis SEG_PAIS.IND_EMIS_VENC%TYPE;
BEGIN
 SELECT A.IND_EMIS_VENC
 INTO
 ln_IndEmis
 FROM SEG_PAIS A
 WHERE
 A.OID_PAIS = pn_idpais;
 RETURN ln_IndEmis;

EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN '0';
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_ULTI_OID_MOVI_CC: '||ls_sqlerrm);
END PER_FN_DEVUE_INDIC_EMIS_VENC;

/**************************************************************************
Descripcion : Devuelve ultimo OID de la la tabla de Movimientos Cta Cte
 CCC_MOVIM_CUENT_CORRI
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_ULTI_OID_MOVI_CC(ps_CodPais VARCHAR2, ps_codProg VARCHAR2, pn_oidUltimoRegistro NUMBER)
RETURN NUMBER
IS
 l_ultimooid NUMBER;
BEGIN
 WITH TEMPORAL AS
 (SELECT PER_PROCE_SUBPR_PROGR.PAIS_COD_PAIS,
 SEG_PAIS.OID_PAIS,
 CCC_PROCE.OID_PROC,
 CCC_SUBPR.OID_SUBP
 FROM PER_PROCE_SUBPR_PROGR,
 SEG_PAIS,
 CCC_PROCE,
 CCC_SUBPR
 WHERE PAIS_COD_PAIS = ps_CodPais
 AND CREP_COD_PROG = ps_codProg
 AND EST_PROC_SUBP_PROG = '1'
 AND PER_PROCE_SUBPR_PROGR.PAIS_COD_PAIS = SEG_PAIS.COD_PAIS
 AND CCC_PROCE.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS
 AND PER_PROCE_SUBPR_PROGR.COD_PROC = CCC_PROCE.COD_PROC
 AND CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC
 AND PER_PROCE_SUBPR_PROGR.COD_SUBP = CCC_SUBPR.COD_SUBP
 )

 SELECT NVL(MAX(CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC),0)
 INTO l_ultimooid
 FROM CCC_MOVIM_CUENT_CORRI,
 TEMPORAL,
 CCC_SUBPR,
 CCC_PROCE
 WHERE ((CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC > pn_oidUltimoRegistro)
 AND (CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE IS NOT NULL)
 AND (CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_CREA = CCC_SUBPR.OID_SUBP)
 AND (CCC_SUBPR.CCPR_OID_PROC = CCC_PROCE.OID_PROC)
 AND (CCC_PROCE.OID_PROC = TEMPORAL.OID_PROC)
 AND (CCC_SUBPR.OID_SUBP = TEMPORAL.OID_SUBP));
 RETURN l_ultimooid;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_ULTI_OID_MOVI_CC: '||ls_sqlerrm);
END PER_FN_ULTI_OID_MOVI_CC;



/**************************************************************************
Descripcion : Devuelve correlativo siguiente de la Tabla PER_REG_PAGO
 correspondiente al Secuencial de Abono
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pRegistro : Registro de Tabla PER_REG_PAGO
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_SGTE_SEC_ABON(pRegistroPago per_regis_pagos%ROWTYPE)
RETURN NUMBER
IS
 ln_secuencia PER_REGIS_PAGOS.Sec_Abon%TYPE;
BEGIN
 ln_secuencia := 0;
 SELECT NVL(MAX(H.SEC_ABON) + 1, 1)
 INTO ln_secuencia
 FROM PER_REGIS_PAGOS H
 WHERE
 H.PAIS_COD_PAIS = pRegistroPago.PAIS_COD_PAIS AND
 H.COD_SOCI = pRegistroPago.COD_SOCI AND
 H.COD_CANA = pRegistroPago.COD_CANA AND
 H.COD_ACCE = pRegistroPago.COD_ACCE AND
 H.COD_SBAC = pRegistroPago.COD_SBAC AND
 H.NUM_SOCO = pRegistroPago.Num_Soco AND
 H.NUM_CUOT = pRegistroPago.NUM_CUOT;
 RETURN ln_secuencia;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_SGTE_SEC_ABON: '||ls_sqlerrm);
END PER_FN_SGTE_SEC_ABON;

/**************************************************************************
Descripcion : Devuelve correlativo siguiente de la Tabla PER_CUENT_CORRI_DOCLE
 correspondiente al Secuencial de Numero de Orden de Couta
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pRegistro : Registro de Tabla PER_CUENT_CORRI_DOCLE
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_SGTE_SEC_ORDEN_COUTA(pRegistro PER_CUENT_CORRI_DOCLE%ROWTYPE)
RETURN NUMBER
IS
 ln_secuencia PER_CUENT_CORRI_DOCLE.NUM_ORDE_CUOT%TYPE;
BEGIN
 ln_secuencia := 0;
 SELECT nvl(MAX(H.NUM_ORDE_CUOT),0)
 INTO ln_secuencia
 FROM PER_CUENT_CORRI_DOCLE H
 WHERE
 H.PAIS_COD_PAIS = pRegistro.PAIS_COD_PAIS AND
 H.COD_SOCI = pRegistro.COD_SOCI AND
 H.COD_CANA = pRegistro.COD_CANA AND
 H.COD_ACCE = pRegistro.COD_ACCE AND
 H.COD_SBAC = pRegistro.COD_SBAC AND
 H.NUM_SOLI_CONS = pRegistro.Num_Soli_Cons;
 RETURN ln_secuencia + 1;
EXCEPTION
WHEN no_data_found THEN
 RETURN 1;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR GEN_FN_SGTE_SEC_ORDEN_COUTA: '||ls_sqlerrm);
END PER_FN_SGTE_SEC_ORDEN_COUTA;



/**************************************************************************
Descripcion : Devuelve correlativo siguiente de la Tabla PER_CUENT_CORRI_DOCLE
Fecha Creacion : 21/09/2006
Parametros Entrada :
 psCodigoPais : Codigo de Pais
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_SGTE_CORRE_CORRI_DOCLE
RETURN NUMBER
IS
 ln_devuelve NUMBER;
BEGIN
 /* Obteniendo el valor maximo */
 SELECT PER_SEQ_CUENT_CORRI_DOCLE.nextval
 INTO ln_devuelve
 FROM DUAL;
 RETURN ln_devuelve;

EXCEPTION
WHEN no_data_found THEN
 RETURN 1;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_SGTE_CORRE_CORRI_DOCLE: '||ls_sqlerrm);
END PER_FN_SGTE_CORRE_CORRI_DOCLE;


/**************************************************************************
Descripcion : Devuelve correlativo siguiente de la Tabla PER_REG_PAGO
Fecha Creacion : 19/09/2006
Parametros Entrada :
 psCodigoPais : Codigo de Pais
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_SGTE_CORRE_REG_PAGO
RETURN NUMBER
IS
 ln_devuelve NUMBER;
BEGIN
 /* Obteniendo el valor maximo */
 SELECT PER_SEQ_REGIS_PAGOS.nextval
 INTO ln_devuelve
 FROM DUAL;

 /* Retornando sgte valor */
 RETURN ln_devuelve;

EXCEPTION
WHEN no_data_found THEN
 RETURN 1;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_SGTE_CORRE_REG_PAGO: '||ls_sqlerrm);
END PER_FN_SGTE_CORRE_REG_PAGO;

/**************************************************************************
Descripcion : Devuelve correlativo siguiente de la Tabla PER_SOLI_MONE
Fecha Creacion : 19/09/2006
Parametros Entrada :
 psCodigoPais : Codigo de Pais
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_SGTE_CORRE_SOLI_MONE
RETURN NUMBER
IS
 ln_devuelve NUMBER;
BEGIN
 ln_devuelve :=1;
 /* Obteniendo el valor maximo */
 SELECT PER_SEQ_SOLIC_MONET.nextval
 INTO ln_devuelve
 FROM DUAL;
 RETURN ln_devuelve;

EXCEPTION
WHEN no_data_found THEN
 RETURN 1;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_SGTE_CORRE_SOLI_MONE: '||ls_sqlerrm);
END PER_FN_SGTE_CORRE_SOLI_MONE;


/**************************************************************************
Descripcion : Inserta Registro en la Tabla PER_CUENT_CORRI_DOCLE
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pRegistro : Registro Rowtype que se usara para insertar en la Tabla
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INSERT_PER_CUENT_CORRI_DOCLE(pRegistro per_cuent_corri_docle%ROWTYPE)
IS
BEGIN
 /* Insertando registro en la tabla respectiva */
 INSERT INTO per_cuent_corri_docle
 VALUES pRegistro;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'NO SE PUDO INSERTAR EN TABLA PER_CUENT_CORRI_DOCLE: '||ls_sqlerrm);
END INSERT_PER_CUENT_CORRI_DOCLE;

/**************************************************************************
Descripcion : Inserta Registro en la Tabla PER_REGIS_PAGOS
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pRegistro : Registro Rowtype que se usara para insertar en la Tabla
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INSERT_PER_REGIS_PAGOS(pRegistro per_regis_pagos%ROWTYPE)
IS
BEGIN
 /* Insertando registro en la tabla respectiva */
 INSERT INTO PER_REGIS_PAGOS
 VALUES pRegistro;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'NO SE PUDO INSERTAR EN TABLA PER_REGIS_PAGOS: '||ls_sqlerrm);
END INSERT_PER_REGIS_PAGOS;

/**************************************************************************
Descripcion : Inserta Registro en la Tabla PER_SOLI_MONE
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pRegistro : Registro Rowtype que se usara para insertar en la Tabla
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE INSERT_PER_SOLI_MONE(pRegistro Per_Solic_Monet%ROWTYPE)
IS
BEGIN
 /* Insertando registro en la tabla respectiva */
 INSERT INTO Per_Solic_Monet
 VALUES pRegistro;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'NO SE PUDO INSERTAR EN TABLA PER_SOLI_MONE: '||ls_sqlerrm);
END INSERT_PER_SOLI_MONE;

/**************************************************************************
Descripcion : Procedimiento que efectua la Cancelacion Automatica de
 Deuda
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 psTipoOrigen : Tipo de Origen de Datos
 psNumeroLote : Numero de Lote
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_CANCE_AUTOM_DEUDA
 (pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2 )
IS
 CURSOR c_deuda01(oidTipoAbono CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP%TYPE) IS
 WITH TEMPORAL AS
 ( SELECT Y.COD_MARC_SITU
 FROM CCC_MARCA_TIPO_ABONO X,
 CCC_MARCA_SITUA Y
 WHERE
 X.TASP_OID_TIPO_ABON_SUBP = oidTipoAbono AND
 X.IND_ENTR_SALI = 'E' AND
 Y.OID_MARC_SITU = X.MASI_OID_MARC_SALI
 )
 SELECT A.*
 FROM
 PER_CUENT_CORRI_DOCLE A,
 TEMPORAL B
 WHERE
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_CLIE = pRegistro.cod_clie AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.COD_CANA = pRegistro.Cod_Cana AND
 A.COD_ACCE = pRegistro.Cod_Acce AND
 A.COD_SBAC = pRegistro.Cod_Sbac AND
 A.NUM_SOLI_CONS = pRegistro.num_soli_cons_refe AND
 A.COD_MARC_SITU = B.COD_MARC_SITU AND
 A.IMP_CUOT_PEND > 0
 ORDER BY A.FEC_VENC_CUOT;

 CURSOR c_deuda02(oidTipoAbono CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP%TYPE) IS
 WITH TEMPORAL AS
 ( SELECT Y.COD_MARC_SITU
 FROM CCC_MARCA_TIPO_ABONO X,
 CCC_MARCA_SITUA Y
 WHERE
 X.TASP_OID_TIPO_ABON_SUBP = oidTipoAbono AND
 X.IND_ENTR_SALI = 'E' AND
 Y.OID_MARC_SITU = X.MASI_OID_MARC_SALI
 )
 SELECT A.*
 FROM
 PER_CUENT_CORRI_DOCLE A,
 TEMPORAL B
 WHERE
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_CLIE = pRegistro.cod_clie AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.COD_CANA = pRegistro.Cod_Cana AND
 A.COD_ACCE = pRegistro.Cod_Acce AND
 A.COD_SBAC = pRegistro.Cod_Sbac AND
 A.NUM_SOLI_CONS = pRegistro.num_soli_cons_refe AND
 A.COD_MARC_SITU = B.COD_MARC_SITU AND
 A.IMP_CUOT_PEND > 0
 ORDER BY A.FEC_FACT;

 CURSOR c_deudaAntigua01(oidTipoAbono CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP%TYPE) IS
 WITH TEMPORAL AS
 ( SELECT Y.COD_MARC_SITU
 FROM CCC_MARCA_TIPO_ABONO X,
 CCC_MARCA_SITUA Y
 WHERE
 X.TASP_OID_TIPO_ABON_SUBP = oidTipoAbono AND
 X.IND_ENTR_SALI = 'E' AND
 Y.OID_MARC_SITU = X.MASI_OID_MARC_SALI
 )
 SELECT A.*
 FROM
 PER_CUENT_CORRI_DOCLE A,
 TEMPORAL B
 WHERE
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_CLIE = pRegistro.cod_clie AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.COD_MARC_SITU = B.COD_MARC_SITU AND
 A.IMP_CUOT_PEND > 0
 ORDER BY A.FEC_VENC_CUOT;

 CURSOR c_deudaAntigua02(oidTipoAbono CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP%TYPE) IS
 WITH TEMPORAL AS
 ( SELECT Y.COD_MARC_SITU
 FROM CCC_MARCA_TIPO_ABONO X,
 CCC_MARCA_SITUA Y
 WHERE
 X.TASP_OID_TIPO_ABON_SUBP = oidTipoAbono AND
 X.IND_ENTR_SALI = 'E' AND
 Y.OID_MARC_SITU = X.MASI_OID_MARC_SALI
 )
 SELECT A.*
 FROM
 PER_CUENT_CORRI_DOCLE A,
 TEMPORAL B
 WHERE
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_CLIE = pRegistro.cod_clie AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.COD_MARC_SITU = B.COD_MARC_SITU AND
 A.IMP_CUOT_PEND > 0
 ORDER BY A.FEC_FACT;

 W_FILAS NUMBER := 5000 ;

 tablaMarcaAbonoEntrada TABLA_CCC_MARCA_TIPO_ABONO;
 regMarcaAbonoSalida ccc_marca_tipo_abono%ROWTYPE;
 regTipoAbonoSubpr ccc_tipo_abono_subpr%ROWTYPE;
 ln_idproc ccc_proce.oid_proc%TYPE;
 ln_idpais seg_pais.oid_pais%TYPE;
 ln_idsubproc ccc_subpr.oid_subp%TYPE;
 ln_val_indi_actu ccc_subpr.val_indi_actu_cuot%TYPE;
 ls_tipo_Cargo_Abon ccc_tipo_cargo_abono.cod_tipo_carg_abon%TYPE;
 ls_codMarcaSitu ccc_marca_situa.cod_marc_situ%TYPE;
 ls_codMarcaSituSalida ccc_marca_situa.cod_marc_situ%TYPE;
 regCuentaCorriDocle per_cuent_corri_docle%ROWTYPE;
 tablaCuentaACancelar TABLA_PER_CUENT_CORRI_DOCLE;
 regCuentaACancelar per_cuent_corri_docle%ROWTYPE;

 j NUMBER;
 buscarDeudaAntigua BOOLEAN;
 ln_contador NUMBER;
 ln_IndEmis NUMBER;
 lsCod_Proc_CreaIni per_cuent_corri_docle.cod_proc_crea%TYPE;
 lsCod_Subp_CreaIni per_cuent_corri_docle.cod_subp_crea%TYPE;
 ln_oidProcApli NUMBER;
 ln_oidSubpApli NUMBER;

BEGIN
 lscod_Proc_Creaini := Pregistro.Cod_proc_Crea;
 lsCod_Subp_CreaIni := Pregistro.Cod_Subp_Crea;

 /* Obteniendo id del pais */
 ln_idpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pRegistro.Pais_Cod_Pais);

 /* Obteniendo id del proceso */
 ln_idproc := gen_pkg_gener.gen_fn_devuelve_id_proceso(ln_idpais, pRegistro.Cod_Proc_Crea);

 /* Obteniendo id subproceso */
 SELECT a.oid_subp
 INTO ln_idsubproc
 FROM ccc_subpr a
 WHERE
 a.ccpr_oid_proc = ln_idproc AND
 a.cod_subp = pRegistro.Cod_Subp_Crea;

 /* Obteniendo proceso y subproceso de aplicacion de coutas */
 BEGIN
 SELECT T.SUBP_OID_SUBP_APLI_CUOT
 INTO ln_oidSubpApli
 FROM CCC_ASIGN_SUBPR_TIPO_SOLIC T
 WHERE
 T.SUBP_OID_SUBP_CREA_CUOT = ln_idsubproc;

 ln_idsubproc := ln_oidSubpApli;

 SELECT a.cod_subp, a.val_indi_actu_cuot, a.ccpr_oid_proc
 INTO Pregistro.Cod_Subp_Crea, ln_val_indi_actu, ln_idproc
 FROM ccc_subpr a
 WHERE
 a.oid_subp = ln_idsubproc;

 SELECT a.cod_proc
 INTO Pregistro.Cod_Proc_Crea
 FROM ccc_proce a
 WHERE
 a.oid_proc = ln_idproc;

 EXCEPTION
 WHEN no_data_found THEN
 Pregistro.Cod_Proc_Crea := 'CCC002';
 Pregistro.Cod_Subp_Crea := 2;
 ln_idproc := gen_pkg_gener.gen_fn_devuelve_id_proceso(ln_idpais, pRegistro.Cod_Proc_Crea);

 /* Obteniendo id subproceso */
 SELECT a.oid_subp, a.val_indi_actu_cuot
 INTO ln_idsubproc, ln_val_indi_actu
 FROM ccc_subpr a
 WHERE
 a.ccpr_oid_proc = ln_idproc AND
 a.cod_subp = pRegistro.Cod_Subp_Crea;
 END;

 /* Obteniendo tipo de abono */
 SELECT *
 INTO regTipoAbonoSubpr
 FROM ccc_tipo_abono_subpr a
 WHERE
 a.subp_oid_subp = ln_idsubproc AND
 ROWNUM < 2;

 /* Obteniendo las Marcas de abono de Salida.
 Se asume solo un registro*/
 SELECT *
 INTO regMarcaAbonoSalida
 FROM ccc_marca_tipo_abono a
 WHERE
 a.tasp_oid_tipo_abon_subp = regTipoAbonoSubpr.Oid_Tipo_Abon_Subp AND
 a.ind_entr_sali = 'S' AND
 rownum = 1;

 /* Obteniendo Tipo de Cargo de abono */
 SELECT a.cod_tipo_carg_abon
 INTO ls_tipo_Cargo_Abon
 FROM ccc_tipo_cargo_abono a
 WHERE
 a.oid_tipo_carg_abon = Regtipoabonosubpr.Tcab_Oid_Tcab;

 /* Obteniendo Marca de Situacion de Salida
 Se asume solo un registro*/
 SELECT a.cod_marc_situ
 INTO ls_CodMarcaSituSalida
 FROM
 ccc_marca_situa a
 WHERE
 a.oid_marc_situ = regMarcaAbonoSalida.Masi_Oid_Marc_Sali;


 /* Viendo criterio por cual debemos ordenar el Select de Coutas */
 ln_IndEmis := PER_FN_DEVUE_INDIC_EMIS_VENC(ln_idpais);

 /* En caso posee informacion en nro de documento de referencia */
 buscarDeudaAntigua := FALSE;

-- COMMIT;

 IF pRegistro.Num_Soli_Cons_Refe IS NOT NULL THEN
 IF (ln_IndEmis = 0) THEN
 /* Recorriendo Registros de la Tabla Temporal */
 OPEN c_deuda01(regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 LOOP
 FETCH c_deuda01 BULK COLLECT INTO tablaCuentaACancelar LIMIT W_FILAS;
 IF tablaCuentaACancelar.COUNT > 0 THEN
 FOR x IN tablaCuentaACancelar.FIRST .. tablaCuentaACancelar.LAST LOOP
 regCuentaACancelar := tablaCuentaACancelar(x);
 -- DBMS_OUTPUT.PUT_LINE('Entro Cancel.Automa '||x);
 /* Actualizando Deuda por Cancelar */
 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 PER_PR_ACTU_IMPORTE_CANCELAR(
 ln_val_indi_actu,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 psTipoOrigen,
 psNumeroLote,
 pRegistro,
 regCuentaACancelar);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deuda01%NOTFOUND;
 END LOOP;
 CLOSE c_deuda01;
 ELSE
 /* Recorriendo Registros de la Tabla Temporal */
 OPEN c_deuda02(regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 LOOP
 FETCH c_deuda02 BULK COLLECT INTO tablaCuentaACancelar LIMIT W_FILAS;
 IF tablaCuentaACancelar.COUNT > 0 THEN
 FOR x IN tablaCuentaACancelar.FIRST .. tablaCuentaACancelar.LAST LOOP
 regCuentaACancelar := tablaCuentaACancelar(x);
 /* Actualizando Deuda por Cancelar */
 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 PER_PR_ACTU_IMPORTE_CANCELAR(
 ln_val_indi_actu,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 psTipoOrigen,
 psNumeroLote,
 pRegistro,
 regCuentaACancelar);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deuda02%NOTFOUND;
 END LOOP;
 CLOSE c_deuda02;
 END IF;

 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 buscarDeudaAntigua := TRUE;
 END IF;
 ELSE
 buscarDeudaAntigua := TRUE;
 END IF;

-- COMMIT;

 /* Buscando por la deuda mas antigua */
 IF buscarDeudaAntigua THEN

 /* Obteniendo deudas mas antiguas */
 IF (ln_IndEmis = 0) THEN
 /* Recorriendo Registros de la Tabla Temporal (Deudas mas antiguas) */
 --dbms_output.put_line('A '||ln_idproc||' '||pRegistro.Cod_Subp_Crea||' '||pRegistro.Pais_Cod_Pais ||' ' ||pRegistro.cod_soci||' '||pRegistro.cod_clie||' '|| regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 OPEN c_deudaAntigua01(regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 LOOP
 FETCH c_deudaAntigua01 BULK COLLECT INTO tablaCuentaACancelar LIMIT W_FILAS;
 IF tablaCuentaACancelar.COUNT > 0 THEN
 FOR x IN tablaCuentaACancelar.FIRST .. tablaCuentaACancelar.LAST LOOP
 regCuentaACancelar := tablaCuentaACancelar(x);
 /* Actualizando Deuda por Cancelar */
 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 PER_PR_ACTU_IMPORTE_CANCELAR (
 ln_val_indi_actu,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 psTipoOrigen,
 psNumeroLote,
 pRegistro,
 regCuentaACancelar);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deudaAntigua01%NOTFOUND;
 END LOOP;
 CLOSE c_deudaAntigua01;
 ELSE
 --dbms_output.put_line('B ' ||pRegistro.Pais_Cod_Pais ||' ' ||pRegistro.cod_soci||' '||pRegistro.cod_clie||' '|| regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 /* Recorriendo Registros de la Tabla Temporal (Deudas mas antiguas) */
 OPEN c_deudaAntigua02(regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 LOOP
 FETCH c_deudaAntigua02 BULK COLLECT INTO tablaCuentaACancelar LIMIT W_FILAS;
 IF tablaCuentaACancelar.COUNT > 0 THEN
 FOR x IN tablaCuentaACancelar.FIRST .. tablaCuentaACancelar.LAST LOOP
 regCuentaACancelar := tablaCuentaACancelar(x);
 /* Actualizando Deuda por Cancelar */
 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 PER_PR_ACTU_IMPORTE_CANCELAR (
 ln_val_indi_actu,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 psTipoOrigen,
 psNumeroLote,
 pRegistro,
 regCuentaACancelar);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deudaAntigua02%NOTFOUND;
 END LOOP;
 CLOSE c_deudaAntigua02;
 END IF;
 END IF;

-- COMMIT;

 Pregistro.Cod_proc_Crea := lscod_Proc_Creaini;
 Pregistro.Cod_Subp_Crea := lsCod_Subp_CreaIni;


EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_AUTO_DEUDA: '||ls_sqlerrm);
END PER_PR_CANCE_AUTOM_DEUDA;


/**************************************************************************
Descripcion : Procedimiento que efectua la Cancelacion Automatica de
 Deuda
Fecha Creacion : 21/09/2006
Parametros Entrada :
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
pbCargoAbonoIncobrale : False: Por defecto
 True: Procedimiento es invocado desde
 CU Procesar cargos y abonos directos e incobrables
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_DEUDA_POSIT(pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pbCargoAbonoIncobrale BOOLEAN := FALSE,
 psCodProcCtaCancelacion VARCHAR2 := NULL,
 psCodSProcCtaCancelacion VARCHAR2 := NULL)
IS

 /* Cursor de Aplicacion de las Coutas Negativas (Abonos)
 (Por Fecha de Vencimiento) */
 CURSOR c_deuda01(oidTipoAbono CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP%TYPE) IS
 WITH TEMPORAL AS
 ( SELECT Y.COD_MARC_SITU
 FROM CCC_MARCA_TIPO_ABONO X,
 CCC_MARCA_SITUA Y
 WHERE
 X.TASP_OID_TIPO_ABON_SUBP = oidTipoAbono AND
 X.IND_ENTR_SALI = 'E' AND
 Y.OID_MARC_SITU = X.MASI_OID_MARC_SALI
 )
 SELECT A.*
 FROM
 PER_CUENT_CORRI_DOCLE A,
 TEMPORAL B
 WHERE
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_CLIE = pRegistro.cod_clie AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.COD_MARC_SITU = B.COD_MARC_SITU AND
 A.IMP_CUOT_PEND < 0
 ORDER BY A.FEC_VENC_CUOT;

 /* Cursor de Aplicacion de las Coutas Negativas (Abonos)
 (Por Fecha de Facturacion) */
 CURSOR c_deuda02(oidTipoAbono CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP%TYPE) IS
 WITH TEMPORAL AS
 ( SELECT Y.COD_MARC_SITU
 FROM CCC_MARCA_TIPO_ABONO X,
 CCC_MARCA_SITUA Y
 WHERE
 X.TASP_OID_TIPO_ABON_SUBP = oidTipoAbono AND
 X.IND_ENTR_SALI = 'E' AND
 Y.OID_MARC_SITU = X.MASI_OID_MARC_SALI
 )
 SELECT A.*
 FROM
 PER_CUENT_CORRI_DOCLE A,
 TEMPORAL B
 WHERE
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_CLIE = pRegistro.cod_clie AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.COD_MARC_SITU = B.COD_MARC_SITU AND
 A.IMP_CUOT_PEND < 0
 ORDER BY A.FEC_FACT;

 /* Cursor de Movimientos Bancarios */
 CURSOR c_movim01 IS
 SELECT B.*
 FROM PER_MOVIM_BANCA_CABEC A,
 PER_MOVIM_BANCA_DETAL B
 WHERE
 B.PAIS_COD_PAIS = A.PAIS_COD_PAIS AND
 B.TIOR_TIPO_ORIG_DATO = A.TIOR_TIPO_ORIG_DATO AND
 B.MOCA_NUM_LOTE_INTE = A.NUM_LOTE_INTE AND
 B.COD_CONS = pRegistro.cod_clie AND
 B.IMP_PAGO_PEND > 0 AND
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.STA_LOTE = 'C'
 ORDER BY b.fec_pago;

 /* Cursor de Movimientos Bancarios */
 CURSOR c_movim02 IS
 SELECT b.*
 FROM PER_MOVIM_BANCA_CABEC A,
 PER_MOVIM_BANCA_DETAL B
 WHERE
 B.PAIS_COD_PAIS = A.PAIS_COD_PAIS AND
 B.TIOR_TIPO_ORIG_DATO = A.TIOR_TIPO_ORIG_DATO AND
 B.MOCA_NUM_LOTE_INTE = A.NUM_LOTE_INTE AND
 B.COD_CONS = pRegistro.cod_clie AND
 B.IMP_PAGO_PEND > 0 AND
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.STA_LOTE = 'C'
 ORDER BY b.fec_pago;

 W_FILAS NUMBER := 5000 ;
 tablaMarcaAbonoEntrada TABLA_CCC_MARCA_TIPO_ABONO;
 regMarcaAbonoSalida ccc_marca_tipo_abono%ROWTYPE;
 regTipoAbonoSubpr ccc_tipo_abono_subpr%ROWTYPE;
 ln_idproc ccc_proce.oid_proc%TYPE;
 ln_idpais seg_pais.oid_pais%TYPE;
 ln_idsubproc ccc_subpr.oid_subp%TYPE;
 ln_val_indi_actu ccc_subpr.val_indi_actu_cuot%TYPE;
 ls_tipo_Cargo_Abon ccc_tipo_cargo_abono.cod_tipo_carg_abon%TYPE;
 ls_codMarcaSitu ccc_marca_situa.cod_marc_situ%TYPE;
 ls_codMarcaSituSalida ccc_marca_situa.cod_marc_situ%TYPE;
 regCuentaCorriDocle per_cuent_corri_docle%ROWTYPE;
 tablaCuentaACancelar TABLA_PER_CUENT_CORRI_DOCLE;
 regCuentaACancelar per_cuent_corri_docle%ROWTYPE;
 tablaMovimientoBancario TABLA_PER_MOVIM_BANCA_DETAL;
 regMovimientoBancario per_movim_banca_detal%ROWTYPE;
 j NUMBER;
 buscarDeudaAntigua BOOLEAN;
 ln_contador NUMBER;
 ln_IndEmis NUMBER;
 ls_codsubpr ccc_subpr.cod_subp%TYPE;
 ls_tipoPagoExcePosi per_param_abono_exter.tip_soli_pago_exce_posi%TYPE;
 ls_tipoSoliApliPago per_param_abono_exter.tip_soli_apli_pago_exve%TYPE;
 ln_id_marca seg_marca.oid_marc%TYPE;
 ln_id_canal seg_canal.oid_cana%TYPE;
 --ln_id_peri cra_perio.peri_oid_peri%TYPE;
 ls_cod_peri seg_perio_corpo.cod_peri%TYPE;
 lsCod_Proc_CreaIni per_cuent_corri_docle.cod_proc_crea%TYPE;
 lsCod_Subp_CreaIni per_cuent_corri_docle.cod_subp_crea%TYPE;
 ln_oidProcApli NUMBER;
 ln_oidSubpApli NUMBER;
 ld_fecha DATE;
 ls_fecha VARCHAR2(10);
 ls_tipoPerc PER_PARAM_ABONO_EXTER.Tip_Soli_Perc%TYPE;

BEGIN
 lscod_Proc_Creaini := Pregistro.Cod_proc_Crea;
 lsCod_Subp_CreaIni := Pregistro.Cod_Subp_Crea;

 /* Obteniendo id del pais */
 ln_idpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pRegistro.Pais_Cod_Pais);

 /* Obteniendo id de marca */
 ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca(pRegistro.Cod_Marc);

 /* Obteniendo id del canal */
 ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');

 /* Obteniendo entidad periodos */
 ld_fecha := SYSDATE;
 ls_fecha := to_char(SYSDATE,'DD/MM/YYYY');
 ld_fecha := to_date(ls_fecha,'DD/MM/YYYY');

 ls_cod_peri := pRegistro.Cod_Peri;

 /* Obteniendo id del proceso */
 ln_idproc := gen_pkg_gener.gen_fn_devuelve_id_proceso(ln_idpais, pRegistro.Cod_Proc_Crea, TRUE);

 /* Obteniendo id subproceso */
 SELECT a.oid_subp
 INTO ln_idsubproc
 FROM ccc_subpr a
 WHERE
 a.ccpr_oid_proc = ln_idproc AND
 a.cod_subp = pRegistro.Cod_Subp_Crea;


 /* Obteniendo proceso y subproceso de aplicacion de coutas */
 BEGIN
 SELECT T.SUBP_OID_SUBP_APLI_CUOT
 INTO ln_oidSubpApli
 FROM CCC_ASIGN_SUBPR_TIPO_SOLIC T
 WHERE
 T.SUBP_OID_SUBP_CREA_CUOT = ln_idsubproc;

 ln_idsubproc := ln_oidSubpApli;

 SELECT a.cod_subp, a.val_indi_actu_cuot, a.ccpr_oid_proc
 INTO Pregistro.Cod_Subp_Crea, ln_val_indi_actu, ln_idproc
 FROM ccc_subpr a
 WHERE
 a.oid_subp = ln_idsubproc;

 SELECT a.cod_proc
 INTO Pregistro.Cod_Proc_Crea
 FROM ccc_proce a
 WHERE
 a.oid_proc = ln_idproc;

 EXCEPTION
 WHEN no_data_found THEN
 Pregistro.Cod_Proc_Crea := 'CCC002';
 Pregistro.Cod_Subp_Crea := 2;
 ln_idproc := gen_pkg_gener.gen_fn_devuelve_id_proceso(ln_idpais, pRegistro.Cod_Proc_Crea);

 /* Obteniendo id subproceso */
 SELECT a.oid_subp, a.val_indi_actu_cuot
 INTO ln_idsubproc, ln_val_indi_actu
 FROM ccc_subpr a
 WHERE
 a.ccpr_oid_proc = ln_idproc AND
 a.cod_subp = pRegistro.Cod_Subp_Crea;
 END;

 /* Obteniendo tipo de abono */
 SELECT *
 INTO regTipoAbonoSubpr
 FROM ccc_tipo_abono_subpr a
 WHERE
 a.subp_oid_subp = ln_idsubproc AND
 ROWNUM < 2;

 /* Obteniendo las Marcas de abono de Salida.
 Se asume solo un registro*/
 SELECT *
 INTO regMarcaAbonoSalida
 FROM ccc_marca_tipo_abono a
 WHERE
 a.tasp_oid_tipo_abon_subp = regTipoAbonoSubpr.Oid_Tipo_Abon_Subp AND
 a.ind_entr_sali = 'S' AND
 rownum < 2;

 /* Obteniendo Tipo de Cargo de abono */
 SELECT a.cod_tipo_carg_abon
 INTO ls_tipo_Cargo_Abon
 FROM ccc_tipo_cargo_abono a
 WHERE
 a.oid_tipo_carg_abon = Regtipoabonosubpr.Tcab_Oid_Tcab;


 /* Obteniendo Marca de Situacion de Salida
 Se asume solo un registro*/
 SELECT a.cod_marc_situ
 INTO ls_CodMarcaSituSalida
 FROM
 ccc_marca_situa a
 WHERE
 a.oid_marc_situ = regMarcaAbonoSalida.Masi_Oid_Marc_Sali;

 /* Viendo criterio por cual debemos ordenar el Select de Coutas */
 ln_IndEmis := PER_FN_DEVUE_INDIC_EMIS_VENC(ln_idpais);


 /* APLICACION DE LAS COUTAS NEGATIVAS (ABONOS) */
 IF NOT
 ((pbCargoAbonoIncobrale) AND
 (psCodProcCtaCancelacion IS NOT NULL AND psCodProcCtaCancelacion = 'CCC003') AND
 (psCodSProcCtaCancelacion IS NOT NULL AND psCodSProcCtaCancelacion = 4)) THEN


 /* Buscando coutas negativas */
 IF (ln_IndEmis = 0) THEN
 /* Recorriendo Registros de las Coutas Negativas de la Tabla Temporal */
 OPEN c_deuda01(regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 LOOP
 FETCH c_deuda01 BULK COLLECT INTO tablaCuentaACancelar LIMIT W_FILAS;
 IF tablaCuentaACancelar.COUNT > 0 THEN
 FOR x IN tablaCuentaACancelar.FIRST .. tablaCuentaACancelar.LAST LOOP
 -- dbms_output.put_line('Entro Deuda Positiva 1 '|| x);

 regCuentaACancelar := tablaCuentaACancelar(x);
 /* Actualizando Deuda por Cancelar */
 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 PER_PR_ACTU_IMPORTE_ABONO(
 ln_val_indi_actu,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 ls_cod_peri,
 psTipoOrigen,
 psNumeroLote,
 pRegistro,
 regCuentaACancelar);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deuda01%NOTFOUND;
 END LOOP;
 CLOSE c_deuda01;
 ELSE
 /* Recorriendo Registros de las Coutas Negativas de la Tabla Temporal */
 OPEN c_deuda02(regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 LOOP
 FETCH c_deuda02 BULK COLLECT INTO tablaCuentaACancelar LIMIT W_FILAS;
 IF tablaCuentaACancelar.COUNT > 0 THEN
 FOR x IN tablaCuentaACancelar.FIRST .. tablaCuentaACancelar.LAST LOOP
 regCuentaACancelar := tablaCuentaACancelar(x);
 --dbms_output.put_line('Entro Deuda Positiva 2 '|| x);

 /* Actualizando Deuda por Cancelar */
 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 PER_PR_ACTU_IMPORTE_ABONO(
 ln_val_indi_actu,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 ls_cod_peri,
 psTipoOrigen,
 psNumeroLote,
 pRegistro,
 regCuentaACancelar);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deuda02%NOTFOUND;
 END LOOP;
 CLOSE c_deuda02;
 END IF;
 END IF;


 /* APLICACION DE LOS PAGOS EN EXCESO (Movimientos Bancarios) */
 IF (pRegistro.Imp_Cuot_Pend > 0 ) THEN
 /* Obteniendo id del proceso */
 pRegistro.Cod_Proc_Crea := 'TES002';
 pRegistro.Cod_Subp_Crea := 1;
 ln_idproc := gen_pkg_gener.gen_fn_devuelve_id_proceso(ln_idpais, pRegistro.Cod_Proc_Crea );

 /* Obteniendo subproceso */
 SELECT a.oid_subp
 INTO ln_idsubproc
 FROM ccc_subpr a
 WHERE
 a.ccpr_oid_proc = ln_idproc AND
 a.cod_subp = pRegistro.Cod_Subp_Crea;

 /* Obteniendo tipo de abono */
 SELECT *
 INTO regTipoAbonoSubpr
 FROM ccc_tipo_abono_subpr a
 WHERE
 a.subp_oid_subp = ln_idsubproc AND
 ROWNUM < 2;

 /* Obteniendo Tipo de Cargo de abono */
 SELECT a.cod_tipo_carg_abon
 INTO ls_tipo_Cargo_Abon
 FROM ccc_tipo_cargo_abono a
 WHERE
 a.oid_tipo_carg_abon = Regtipoabonosubpr.Tcab_Oid_Tcab;

 /* Obteniendo tipo solicitud pago en exceso positivo
 Parametros abonos externos */
 SELECT a.tip_soli_pago_exce_posi,
 a.tip_soli_apli_pago_exve,
 a.tip_soli_perc
 INTO ls_tipoPagoExcePosi, ls_tipoSoliApliPago, ls_tipoPerc
 FROM PER_PARAM_ABONO_EXTER a
 WHERE a.pais_cod_pais = pRegistro.Pais_Cod_Pais AND
 a.tior_tipo_orig_dato = '05' AND
 a.cue_corr_banc IS NULL AND
 rownum < 2;

 /* Buscando los pagos en exceso (Movimientos Bancarios) */
 IF (ln_IndEmis = 0) THEN
 OPEN c_movim01;
 LOOP
 FETCH c_movim01 BULK COLLECT INTO TablaMovimientoBancario LIMIT W_FILAS;
 IF TablaMovimientoBancario.COUNT > 0 THEN
 FOR x IN TablaMovimientoBancario.FIRST .. TablaMovimientoBancario.LAST LOOP
 regMovimientoBancario := TablaMovimientoBancario(x);
 --dbms_output.put_line('Entro Deuda Positiva 3 '|| x);

 /* Actualizando Pago x exceso */
 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 PER_PR_ACTU_IMPORTE_PAGO_EXCE(
 ln_val_indi_actu,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 ls_tipoPagoExcePosi,
 ls_tipoSoliApliPago,
 ls_tipoPerc,
 ls_cod_peri,
 Pstipoorigen,
 Psnumerolote,
 pRegistro,
 regMovimientoBancario);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_movim01%NOTFOUND;
 END LOOP;
 CLOSE c_movim01;
 ELSE
 OPEN c_movim02;
 LOOP
 FETCH c_movim02 BULK COLLECT INTO TablaMovimientoBancario LIMIT W_FILAS;
 IF TablaMovimientoBancario.COUNT > 0 THEN
 FOR x IN TablaMovimientoBancario.FIRST .. TablaMovimientoBancario.LAST LOOP
 regMovimientoBancario := TablaMovimientoBancario(x);
 --dbms_output.put_line('Entro Deuda Positiva 4 '|| x);

 /* Actualizando Pago x exceso */
 IF (pRegistro.Imp_Cuot_Pend <> 0) THEN
 PER_PR_ACTU_IMPORTE_PAGO_EXCE(
 ln_val_indi_actu,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 ls_tipoPagoExcePosi,
 ls_tipoSoliApliPago,
 ls_tipoPerc,
 ls_cod_peri,
 Pstipoorigen,
 Psnumerolote,
 pRegistro,
 regMovimientoBancario);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_movim02%NOTFOUND;
 END LOOP;
 CLOSE c_movim02;
 END IF;
 END IF;

 Pregistro.Cod_proc_Crea := lscod_Proc_Creaini;
 Pregistro.Cod_Subp_Crea := lsCod_Subp_CreaIni;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_DEUDA_POSIT: '||ls_sqlerrm);
END PER_PR_DEUDA_POSIT;

/**************************************************************************
Descripcion : Procedimiento que efectua Cargo y Abono directo e Incobrables
 desde el Menu de la aplicacion
Fecha Creacion : 19/09/2006
Parametros Entrada :
 psCodPais : Codigo de Pais
 psTipoOrigen : Tipo de Origen de Datos
 psNumeroLote : Numero de Lote
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_PROCE_CARGO_ABONO_MENU(psCodPais VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2)
IS
 lnUltimoId NUMBER:=0;
BEGIN
 PER_PR_PROCE_CARGO_ABONO_DIREC(psCodPais,
 psTipoOrigen,
 psNumeroLote,
 lnUltimoId);
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_PROCE_CARGO_ABONO_DIREC_M: '||ls_sqlerrm);
END PER_PR_PROCE_CARGO_ABONO_MENU;


/**************************************************************************
Descripcion : Procedimiento que efectua Cargo y Abono directo e Incobrables
 desde la Generacion de Cta Cte
Fecha Creacion : 19/09/2006
Parametros Entrada :
 psCodPais : Codigo de Pais
 psTipoOrigen : Tipo de Origen de Datos
 psNumeroLote : Numero de Lote
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_PROCE_CARGO_ABONO_DIREC(psCodPais VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pnUltimoOid IN OUT NUMBER)
IS
 TYPE tTablaRecupera IS TABLE OF tRegCargoAbonoDirecto INDEX BY BINARY_INTEGER;

 CURSOR c_facturaReversion(
 pn_idUltiRegi PER_CONTR_REGIS_PROGR.OID_ULTI_REGI_PROC%TYPE,
 pd_fecUlti PER_CONTR_REGIS_PROGR.Fec_Ulti_Ejec%TYPE)
 IS
 SELECT
 A.OID_MOVI_CC,
 A.SOCI_OID_SOCI,
 A.MASI_OID_MARC_SITU,
 A.MONE_OID_MONE,
 A.PERD_OID_PERI,
 A.TCAB_OID_TCAB_ULTI,
 A.TCAB_OID_TCAB_CREA,
 A.SBAC_OID_SBAC,
 A.SUBP_OID_SUBP_ULTI,
 A.SUBP_OID_SUBP_CREA,
 A.CUCO_OID_CUEN_CONT_ORIG,
 A.CUCO_OID_CUEN_CONT_CUOT,
 A.CLIE_OID_CLIE,
 A.VAL_DOCU_ANIO,
 A.VAL_DOCU_MES_SERI,
 A.VAL_DOCU_NUME,
 A.VAL_EJER_CUOT,
 A.FEC_CONTA,
 A.FEC_DOCU,
 A.FEC_ULTI_MOVI,
 A.FEC_VALO,
 A.FEC_VENC,
 A.IMP_MOVI,
 A.IMP_DIVI,
 A.IMP_PAGA,
 A.IMP_PAGA_DIVI,
 A.IMP_PEND,
 A.NUM_IDEN_CUOT,
 A.VAL_NUME_LOTE_CONT,
 A.NUM_ORDE_CUOT,
 A.VAL_OBSE,
 A.VAL_REFE_NUME_DOCU_EXTE,
 A.VAL_ULTI_DOCU_ANIO,
 A.VAL_ULTI_DOCU_MES_SERI,
 A.VAL_ULTI_DOCU_NUME,
 A.VAL_ULTI_NUME_HIST,
 A.MPAB_OID_MEDI_PAGO,
 A.ZTAD_OID_TERR_ADMI,
 A.TICL_OID_TIPO_CLIE,
 A.MARC_OID_MARC,
 A.IMP_PAGO,
 A.IMP_PAGO_DIVI,
 A.IMP_MOVI_CUEN,
 A.IMP_MOVI_DIVI,
 A.NUM_LOTE_FACT,
 A.COD_USUA,
 A.IND_TIPO_CAMB,
 A.IND_DTO_CARG_APLI,
 A.ZSCC_OID_SECC,
 A.ZSGV_OID_SUBG_VENT,
 A.SOCA_OID_SOLI_CABE,
 A.SBTI_OID_SUBT_CLIE,
 A.TSPA_OID_TIPO_SOLI_PAIS,
 A.TIPE_OID_TIPO_PERI,
 A.ZORG_OID_REGI,
 A.TIDO_OID_TIPO_DOCU,
 A.FEC_ULTI_ACTU,
 B.OID_PROC,
 B.COD_PROC,
 B.OID_SUBP,
 B.COD_SUBP,
 B.VAL_INDI_ACTU_CUOT,
 B.TIP_SOLI_IMPO_POSI,
 B.TIP_SOLI_IMPO_NEGA,
 B.IND_RECA_IMPO_NEGA,
 B.OID_TIPO_ABON_SUBP,
 B.TCAB_OID_TCAB,
 B.COD_TIPO_CARG_ABON,
 B.OID_MARC_SITU,
 B.COD_MARC_SITU,
 0 AS oid_Deta_cargo
 FROM
 CCC_MOVIM_CUENT_CORRI A,
 PER_GTT_PROCE_PROGR B
 WHERE
 A.OID_MOVI_CC > pn_idUltiRegi AND
 A.SOCA_OID_SOLI_CABE IS NULL AND
 B.OID_SUBP = A.SUBP_OID_SUBP_CREA
 ORDER BY
 A.OID_MOVI_CC;

 CURSOR c_Incobrable(
 pn_idUltiRegi PER_CONTR_REGIS_PROGR.OID_ULTI_REGI_PROC%TYPE,
 pd_fecUlti PER_CONTR_REGIS_PROGR.Fec_Ulti_Ejec%TYPE)
 IS
 SELECT
 A.OID_MOVI_CC,
 A.SOCI_OID_SOCI,
 A.MASI_OID_MARC_SITU,
 A.MONE_OID_MONE,
 A.PERD_OID_PERI,
 A.TCAB_OID_TCAB_ULTI,
 A.TCAB_OID_TCAB_CREA,
 A.SBAC_OID_SBAC,
 A.SUBP_OID_SUBP_ULTI,
 A.SUBP_OID_SUBP_CREA,
 A.CUCO_OID_CUEN_CONT_ORIG,
 A.CUCO_OID_CUEN_CONT_CUOT,
 A.CLIE_OID_CLIE,
 A.VAL_DOCU_ANIO,
 A.VAL_DOCU_MES_SERI,
 A.VAL_DOCU_NUME,
 A.VAL_EJER_CUOT,
 A.FEC_CONTA,
 A.FEC_DOCU,
 A.FEC_ULTI_MOVI,
 A.FEC_VALO,
 A.FEC_VENC,
 C.IMP AS IMP_MOVI,
 A.IMP_DIVI,
 A.IMP_PAGA,
 A.IMP_PAGA_DIVI,
 A.IMP_PEND,
 A.NUM_IDEN_CUOT,
 A.VAL_NUME_LOTE_CONT,
 A.NUM_ORDE_CUOT,
 A.VAL_OBSE,
 A.VAL_REFE_NUME_DOCU_EXTE,
 A.VAL_ULTI_DOCU_ANIO,
 A.VAL_ULTI_DOCU_MES_SERI,
 A.VAL_ULTI_DOCU_NUME,
 A.VAL_ULTI_NUME_HIST,
 A.MPAB_OID_MEDI_PAGO,
 A.ZTAD_OID_TERR_ADMI,
 A.TICL_OID_TIPO_CLIE,
 A.MARC_OID_MARC,
 A.IMP_PAGO,
 A.IMP_PAGO_DIVI,
 A.IMP_MOVI_CUEN,
 A.IMP_MOVI_DIVI,
 A.NUM_LOTE_FACT,
 A.COD_USUA,
 A.IND_TIPO_CAMB,
 A.IND_DTO_CARG_APLI,
 A.ZSCC_OID_SECC,
 A.ZSGV_OID_SUBG_VENT,
 A.SOCA_OID_SOLI_CABE,
 A.SBTI_OID_SUBT_CLIE,
 A.TSPA_OID_TIPO_SOLI_PAIS,
 A.TIPE_OID_TIPO_PERI,
 A.ZORG_OID_REGI,
 A.TIDO_OID_TIPO_DOCU,
 A.FEC_ULTI_ACTU,
 B.OID_PROC,
 B.COD_PROC,
 B.OID_SUBP,
 B.COD_SUBP,
 B.VAL_INDI_ACTU_CUOT,
 B.TIP_SOLI_IMPO_POSI,
 B.TIP_SOLI_IMPO_NEGA,
 B.IND_RECA_IMPO_NEGA,
 B.OID_TIPO_ABON_SUBP,
 B.TCAB_OID_TCAB,
 B.COD_TIPO_CARG_ABON,
 B.OID_MARC_SITU,
 B.COD_MARC_SITU,
 C.OID_DETA_CARG_ABON_DIRE
 FROM
 CCC_MOVIM_CUENT_CORRI A,
 PER_GTT_PROCE_PROGR B,
 CCC_DETAL_CARGO_ABONO_DIREC C,
 CCC_TIPO_ABONO_SUBPR D
 WHERE
 C.OID_DETA_CARG_ABON_DIRE > pn_idUltiRegi AND
 D.OID_TIPO_ABON_SUBP = C.TASP_OID_TIPO_ABON_SUBP AND
 B.OID_SUBP = D.SUBP_OID_SUBP AND
 A.OID_MOVI_CC = C.MVCC_OID_MOVI_CC AND
 C.MVCC_OID_MOVI_CC IS NOT NULL
 ORDER BY
 C.OID_DETA_CARG_ABON_DIRE;

 CURSOR c_deuda(pRegistro per_cuent_corri_docle%ROWTYPE)
 IS
 SELECT *
 FROM
 PER_CUENT_CORRI_DOCLE A
 WHERE
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_CLIE = pRegistro.cod_clie AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.COD_CANA = pRegistro.Cod_Cana AND
 A.COD_ACCE = pRegistro.Cod_Acce AND
 A.COD_SBAC = pRegistro.Cod_Sbac AND
 A.NUM_SOLI_CONS = pRegistro.num_soli_cons AND
 A.IMP_CUOT_PEND > 0
 ORDER BY A.COR_CUEN_CORR_DOLE, A.NUM_ORDE_CUOT;

 CURSOR c_deudaIncobrable(pRegistro per_cuent_corri_docle%ROWTYPE)
 IS
 SELECT *
 FROM
 PER_CUENT_CORRI_DOCLE A
 WHERE
 A.PAIS_COD_PAIS = pRegistro.Pais_Cod_Pais AND
 A.COD_CLIE = pRegistro.cod_clie AND
 A.COD_SOCI = pRegistro.cod_soci AND
 A.COD_CANA = pRegistro.Cod_Cana AND
 A.COD_ACCE = pRegistro.Cod_Acce AND
 A.COD_SBAC = pRegistro.Cod_Sbac AND
 A.NUM_SOLI_CONS = pRegistro.num_soli_cons
 ORDER BY A.COR_CUEN_CORR_DOLE, A.NUM_ORDE_CUOT;

 W_FILAS NUMBER := 5000 ;
 x NUMBER;
 ln_idproc ccc_proce.oid_proc%TYPE;
 ln_idsubproc ccc_subpr.oid_subp%TYPE;
 ln_val_indi_actu ccc_subpr.val_indi_actu_cuot%TYPE;
 regMarcaAbonoSalida ccc_marca_tipo_abono%ROWTYPE;
 regTipoAbonoSubpr ccc_tipo_abono_subpr%ROWTYPE;
 ls_tipo_Cargo_Abon ccc_tipo_cargo_abono.cod_tipo_carg_abon%TYPE;

 ls_codMarcaSitu ccc_marca_situa.cod_marc_situ%TYPE;
 ls_codMarcaSituSalida ccc_marca_situa.cod_marc_situ%TYPE;
 regCuentaCorriDocle per_cuent_corri_docle%ROWTYPE;
 tablaCuentaACancelar TABLA_PER_CUENT_CORRI_DOCLE;
 regCuentaACancelar per_cuent_corri_docle%ROWTYPE;
 regRecupera tRegCargoAbonoDirecto;
 tablaRecupera tTablaRecupera;

 regPorcePerce PER_PORCE_PERCE%ROWTYPE;
 ln_idUltiRegi PER_CONTR_REGIS_PROGR.OID_ULTI_REGI_PROC%TYPE;
 ls_codprog PER_CONTR_REGIS_PROGR.COD_PROG%TYPE;
 tempoPrograma TABLA_PER_PROCE_SUBPR_PROGR;
 regPrograma PER_PROCE_SUBPR_PROGR%ROWTYPE;
 tablaDeuda TABLA_PER_CUENT_CORRI_DOCLE;
 regDeuda per_cuent_corri_docle%ROWTYPE;
 ln_idpais seg_pais.oid_pais%TYPE;
 ln_contador NUMBER;
 ls_error VARCHAR2(100);
 ld_fecUlti DATE;
 ls_fecUlti VARCHAR2(15);
 lnMontoCtaCte NUMBER;
 lnMontoMovimiento NUMBER;
 lbGrabarCtaCte BOOLEAN;


BEGIN
 /* Borrando tablas temporales */
 DELETE FROM PER_GTT_PROCE_PROGR;

 /* obtenemos codigo de programa */
 ls_codprog := '01';
 IF (psTipoOrigen = '07') THEN
 ls_codprog := '02';
 END IF;

 -- Obtenemos los parametros de Percepciones --
 SELECT *
 INTO regPorcePerce
 FROM PER_PORCE_PERCE
 WHERE PAIS_COD_PAIS = psCodPais
 AND EST_PORC_PERC = '1';

 -- Obtenemos los parametros de ultima ejecucion --
 SELECT TO_CHAR(FEC_ULTI_EJEC,'DD/MM/YYYY'), OID_ULTI_REGI_PROC
 INTO ls_fecUlti, ln_idUltiRegi
 FROM PER_CONTR_REGIS_PROGR
 WHERE PAIS_COD_PAIS = psCodPais
 AND COD_PROG = ls_codprog
 AND EST_CTRL_REGI_PROG = '1';

 ld_fecUlti := to_date(ls_fecUlti, 'DD/MM/YYYY');

 /* Obteniendo procesos /subprocesos
 Se guarda en una tabla de registros en memoria */
 SELECT *
 BULK COLLECT INTO Tempoprograma
 FROM
 PER_PROCE_SUBPR_PROGR a
 WHERE
 a.pais_cod_pais = psCodPais AND
 a.crep_cod_prog = ls_codprog ;

 ln_idpais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
 FOR x IN Tempoprograma.FIRST .. Tempoprograma.LAST LOOP
 regPrograma := Tempoprograma(x);
 BEGIN
 ln_idproc := gen_pkg_gener.gen_fn_devuelve_id_proceso(ln_idpais, regPrograma.cod_proc, TRUE);

 /* Obteniendo subproceso */
 SELECT a.oid_subp, a.val_indi_actu_cuot
 INTO ln_idsubproc, ln_val_indi_actu
 FROM ccc_subpr a
 WHERE
 a.ccpr_oid_proc = ln_idproc AND
 a.cod_subp = regPrograma.COD_SUBP;

 /* Obteniendo tipo de abono */
 SELECT *
 INTO regTipoAbonoSubpr
 FROM ccc_tipo_abono_subpr a
 WHERE
 a.subp_oid_subp = ln_idsubproc AND
 ROWNUM < 2;

 /* Obteniendo las Marcas de abono de Salida.
 Se asume solo un registro*/
 SELECT *
 INTO regMarcaAbonoSalida
 FROM ccc_marca_tipo_abono a
 WHERE
 a.tasp_oid_tipo_abon_subp = regTipoAbonoSubpr.Oid_Tipo_Abon_Subp AND
 a.ind_entr_sali = 'S' AND
 rownum < 2;

 /* Obteniendo Tipo de Cargo de abono */
 SELECT a.cod_tipo_carg_abon
 INTO ls_tipo_Cargo_Abon
 FROM ccc_tipo_cargo_abono a
 WHERE
 a.oid_tipo_carg_abon = Regtipoabonosubpr.Tcab_Oid_Tcab;

 /* Obteniendo Marca de Situacion de Salida
 Se asume solo un registro*/
 SELECT a.cod_marc_situ
 INTO ls_CodMarcaSituSalida
 FROM
 ccc_marca_situa a
 WHERE
 a.oid_marc_situ = regMarcaAbonoSalida.Masi_Oid_Marc_Sali;

 INSERT INTO PER_GTT_PROCE_PROGR(
 OID_PROC, COD_PROC,
 OID_SUBP, COD_SUBP,
 VAL_INDI_ACTU_CUOT, TIP_SOLI_IMPO_POSI,
 TIP_SOLI_IMPO_NEGA, IND_RECA_IMPO_NEGA,
 OID_TIPO_ABON_SUBP, TCAB_OID_TCAB,
 COD_TIPO_CARG_ABON,
 OID_MARC_SITU, COD_MARC_SITU)
 VALUES
 ( ln_idproc, regPrograma.cod_proc,
 ln_idsubproc, regPrograma.cod_subp,
 ln_val_indi_actu, regPrograma.Tip_Soli_Impo_Posi,
 Regprograma.Tip_Soli_Impo_Nega, regprograma.Ind_Reca_Impo_Nega,
 regTipoAbonoSubpr.Oid_Tipo_Abon_Subp, Regtipoabonosubpr.Tcab_Oid_Tcab,
 ls_tipo_Cargo_Abon,
 regMarcaAbonoSalida.Masi_Oid_Marc_Sali, ls_CodMarcaSituSalida);

 dbms_output.put_line('subprocesos PER_GTT_PROCE_PROGR: (Reversion) '|| ln_idsubproc);
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 ls_error := 'NO SE ENCONTRO REGISTRO';
 END;
 END LOOP;

 -- invocado solo desde Generar cuenta corriente /* Recorriendo Registros de Cuenta Corriente */
 IF (psTipoOrigen = '06') THEN

 /* Facturas Reversion */
 OPEN c_facturaReversion(ln_idUltiRegi, ld_feculti);
 LOOP
 FETCH c_facturaReversion BULK COLLECT INTO tablaRecupera LIMIT W_FILAS;
 IF tablaRecupera.COUNT > 0 THEN
 FOR x IN tablaRecupera.FIRST .. tablaRecupera.LAST LOOP
 regRecupera := tablaRecupera(x);
 regCuentaCorriDocle := PER_FN_RECU_CARGO_ABONO_DIREC(psCodPais, regRecupera);
 -- dbms_output.put_line('Entro a CARGO ABONO '|| x);

 /* Procesos de abonos directos */
 IF (regCuentaCorriDocle.Cod_Proc_Crea = 'CCC003' AND regCuentaCorriDocle.Cod_Subp_Crea = 1 ) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCR03' AND regCuentaCorriDocle.Cod_Subp_Crea = 1 ) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCS03' AND regCuentaCorriDocle.Cod_Subp_Crea = 1 ) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCC006' AND regCuentaCorriDocle.Cod_Subp_Crea = 1 ) THEN
 OPEN c_deuda(regCuentaCorriDocle);
 LOOP
 FETCH c_deuda BULK COLLECT INTO tablaDeuda LIMIT W_FILAS;
 IF tablaDeuda.COUNT > 0 THEN
 FOR y IN tablaDeuda.FIRST .. tablaDeuda.LAST LOOP
 regDeuda := tablaDeuda(y);
 /* Actualizando Deuda por Cancelar */
 IF regRecupera.imp_movi <> 0 THEN
 PER_PR_ACTU_IMPORTE_RECUPERADA(
 regRecupera.VAL_INDI_ACTU_CUOT,
 regRecupera.COD_MARC_SITU,
 regRecupera.COD_TIPO_CARG_ABON,
 regRecupera.TIP_SOLI_IMPO_POSI,
 regRecupera.IND_RECA_IMPO_NEGA,
 psTipoOrigen,
 psNumeroLote,
 regCuentaCorriDocle.cod_peri,
 regRecupera.imp_movi,
 regRecupera.cod_proc,
 regRecupera.cod_subp,
 regDeuda);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deuda%NOTFOUND;
 END LOOP;
 CLOSE c_deuda;
 END IF;

 /* Validacion del Importe */
 IF (regRecupera.imp_movi < 0 AND regRecupera.cod_proc = 'CCC001' AND regRecupera.cod_subp = 1) THEN
 PER_PR_CANCE_AUTOM_DEUDA(regCuentaCorriDocle,pstipoorigen, psnumerolote);
 END IF;
 IF (regRecupera.imp_movi > 0) AND
 ((regRecupera.cod_proc = 'CCC001' AND regRecupera.cod_subp = 1) /*OR
 (regRecupera.cod_proc = 'CCC003' AND regRecupera.cod_subp = 4)*/) THEN
 PER_PR_DEUDA_POSIT(regCuentaCorriDocle,pstipoorigen, psnumerolote, TRUE, regRecupera.cod_proc, regRecupera.cod_subp);
 END IF;
 ln_idUltiRegi := regRecupera.oid_movi_cc;
 pnUltimoOid := ln_idUltiRegi;
 END LOOP;
 END IF;
 EXIT WHEN c_facturaReversion%NOTFOUND;
 END LOOP;
 CLOSE c_facturaReversion;
 END IF;

 /* Borrando tablas temporales */
 DELETE FROM PER_GTT_PROCE_PROGR;

 /* Cambiando el tipo de programa en caso se haya
 ejecutado desde Generacion Cta cte */
 IF (psTipoOrigen = '06') THEN
 ls_codprog := '02';
 END IF;

 -- Obtenemos los parametros de ultima ejecucion --
 SELECT TO_CHAR(FEC_ULTI_EJEC,'DD/MM/YYYY'), OID_ULTI_REGI_PROC
 INTO ls_fecUlti, ln_idUltiRegi
 FROM PER_CONTR_REGIS_PROGR
 WHERE PAIS_COD_PAIS = psCodPais
 AND COD_PROG = ls_codprog
 AND EST_CTRL_REGI_PROG = '1';

 ld_fecUlti := to_date(ls_fecUlti, 'DD/MM/YYYY');

 /* Obteniendo procesos /subprocesos
 Se guarda en una tabla de registros en memoria */
 SELECT *
 BULK COLLECT INTO Tempoprograma
 FROM
 PER_PROCE_SUBPR_PROGR a
 WHERE
 a.pais_cod_pais = psCodPais AND
 a.crep_cod_prog = ls_codprog ;

 ln_idpais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
 FOR x IN Tempoprograma.FIRST .. Tempoprograma.LAST LOOP
 regPrograma := Tempoprograma(x);
 BEGIN
 ln_idproc := gen_pkg_gener.gen_fn_devuelve_id_proceso(ln_idpais, regPrograma.cod_proc, TRUE);

 /* Obteniendo subproceso */
 SELECT a.oid_subp, a.val_indi_actu_cuot
 INTO ln_idsubproc, ln_val_indi_actu
 FROM ccc_subpr a
 WHERE
 a.ccpr_oid_proc = ln_idproc AND
 a.cod_subp = regPrograma.COD_SUBP;

 /* Obteniendo tipo de abono */
 SELECT *
 INTO regTipoAbonoSubpr
 FROM ccc_tipo_abono_subpr a
 WHERE
 a.subp_oid_subp = ln_idsubproc AND
 ROWNUM < 2;

 /* Obteniendo las Marcas de abono de Salida.
 Se asume solo un registro*/
 SELECT *
 INTO regMarcaAbonoSalida
 FROM ccc_marca_tipo_abono a
 WHERE
 a.tasp_oid_tipo_abon_subp = regTipoAbonoSubpr.Oid_Tipo_Abon_Subp AND
 a.ind_entr_sali = 'S' AND
 rownum < 2;

 /* Obteniendo Tipo de Cargo de abono */
 SELECT a.cod_tipo_carg_abon
 INTO ls_tipo_Cargo_Abon
 FROM ccc_tipo_cargo_abono a
 WHERE
 a.oid_tipo_carg_abon = Regtipoabonosubpr.Tcab_Oid_Tcab;

 /* Obteniendo Marca de Situacion de Salida
 Se asume solo un registro*/
 SELECT a.cod_marc_situ
 INTO ls_CodMarcaSituSalida
 FROM
 ccc_marca_situa a
 WHERE
 a.oid_marc_situ = regMarcaAbonoSalida.Masi_Oid_Marc_Sali;

 INSERT INTO PER_GTT_PROCE_PROGR(
 OID_PROC, COD_PROC,
 OID_SUBP, COD_SUBP,
 VAL_INDI_ACTU_CUOT, TIP_SOLI_IMPO_POSI,
 TIP_SOLI_IMPO_NEGA, IND_RECA_IMPO_NEGA,
 OID_TIPO_ABON_SUBP, TCAB_OID_TCAB,
 COD_TIPO_CARG_ABON,
 OID_MARC_SITU, COD_MARC_SITU)
 VALUES
 ( ln_idproc, regPrograma.cod_proc,
 ln_idsubproc, regPrograma.cod_subp,
 ln_val_indi_actu, regPrograma.Tip_Soli_Impo_Posi,
 Regprograma.Tip_Soli_Impo_Nega, regprograma.Ind_Reca_Impo_Nega,
 regTipoAbonoSubpr.Oid_Tipo_Abon_Subp, Regtipoabonosubpr.Tcab_Oid_Tcab,
 ls_tipo_Cargo_Abon,
 regMarcaAbonoSalida.Masi_Oid_Marc_Sali, ls_CodMarcaSituSalida);

 dbms_output.put_line('subprocesos PER_GTT_PROCE_PROGR (Incobrables): '|| regPrograma.COD_PROC || ' '|| regPrograma.cod_subp ||' '|| ln_idsubproc);
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 ls_error := 'NO SE ENCONTRO REGISTRO';
 END;
 END LOOP;

 /* Incobrables */
 OPEN c_Incobrable(ln_idUltiRegi, ld_feculti);
 LOOP
 FETCH c_Incobrable BULK COLLECT INTO tablaRecupera LIMIT W_FILAS;
 IF tablaRecupera.COUNT > 0 THEN
 FOR x IN tablaRecupera.FIRST .. tablaRecupera.LAST LOOP
 regRecupera := tablaRecupera(x);
 lbGrabarCtaCte := FALSE;
 IF ((regRecupera.cod_proc = 'CCC003' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCR03' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCS03' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCC006' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCX06' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCA06' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCEV6' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCN06' AND regRecupera.cod_subp = 1)
 ) THEN
 regRecupera.imp_movi := regRecupera.imp_movi * -1;
 END IF;

 regCuentaCorriDocle := PER_FN_RECU_CARGO_ABONO_DIREC(psCodPais, regRecupera);

 /* Procesos de abonos directos */
 lnMontoCtaCte := 0;
 lnMontoMovimiento := abs(regRecupera.imp_movi);
 IF (regCuentaCorriDocle.Cod_Proc_Crea = 'CCC003' AND regCuentaCorriDocle.Cod_Subp_Crea = 1 ) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCR03' AND regCuentaCorriDocle.Cod_Subp_Crea = 1 ) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCS03' AND regCuentaCorriDocle.Cod_Subp_Crea = 1 ) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCC006' AND regCuentaCorriDocle.Cod_Subp_Crea = 1 ) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCX06' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCA06' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCEV6' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCN06' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) THEN
 OPEN c_deudaIncobrable(regCuentaCorriDocle);

 LOOP
 FETCH c_deudaIncobrable BULK COLLECT INTO tablaDeuda LIMIT W_FILAS;
 IF tablaDeuda.COUNT > 0 THEN
 FOR y IN tablaDeuda.FIRST .. tablaDeuda.LAST LOOP
 regDeuda := tablaDeuda(y);
 lbGrabarCtaCte := TRUE;
 /* Actualizando Deuda por Cancelar */
 IF regRecupera.imp_movi <> 0 AND regDeuda.IMP_CUOT_PEND > 0 THEN
 lnMontoCtaCte := lnMontoCtaCte + regDeuda.Imp_Cuot_Pend;
 PER_PR_ACTU_IMPORTE_RECUPERADA(
 regRecupera.VAL_INDI_ACTU_CUOT,
 regRecupera.COD_MARC_SITU,
 regRecupera.COD_TIPO_CARG_ABON,
 regRecupera.TIP_SOLI_IMPO_NEGA,
 regRecupera.IND_RECA_IMPO_NEGA,
 psTipoOrigen,
 psNumeroLote,
 regCuentaCorriDocle.cod_peri,
 regRecupera.imp_movi,
 regRecupera.cod_proc,
 regRecupera.cod_subp,
 regDeuda);
 END IF;

 END LOOP;
 END IF;
 EXIT WHEN c_deudaIncobrable%NOTFOUND;
 END LOOP;
 CLOSE c_deudaIncobrable;
 END IF;

 /* Generando registro en DOCLE debido que no se ha consumido totalmente el importe */
 IF lbGrabarCtaCte THEN
 IF (regCuentaCorriDocle.Cod_Proc_Crea = 'CCC003' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCR03' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCS03' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCC006' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCX06' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCA06' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCEV6' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) OR
 (regCuentaCorriDocle.Cod_Proc_Crea = 'CCCN06' AND regCuentaCorriDocle.Cod_Subp_Crea = 1) THEN
 IF regRecupera.imp_movi <> 0 THEN
 IF regRecupera.VAL_INDI_ACTU_CUOT = 0 THEN
 IF lnMontoMovimiento > lnMontoCtaCte THEN
 regCuentaCorriDocle := PER_FN_RECU_CARGO_ABONO_DIREC(psCodPais, regRecupera, TRUE, lnMontoMovimiento - lnMontoCtaCte);
 END IF;
 ELSE
 regCuentaCorriDocle := PER_FN_RECU_CARGO_ABONO_DIREC(psCodPais, regRecupera, TRUE);
 END IF;
 END IF;
 END IF;
 END IF;

 /* Validacion del Importe */
 IF (regRecupera.imp_movi < 0 AND regRecupera.cod_proc = 'CCC001' AND regRecupera.cod_subp = 1) THEN
 PER_PR_CANCE_AUTOM_DEUDA(regCuentaCorriDocle,pstipoorigen, psnumerolote);
 END IF;
 IF (regRecupera.imp_movi > 0) AND
 ((regRecupera.cod_proc = 'CCC001' AND regRecupera.cod_subp = 1) /*OR
 (regRecupera.cod_proc = 'CCC003' AND regRecupera.cod_subp = 4)*/) THEN
 PER_PR_DEUDA_POSIT(regCuentaCorriDocle,pstipoorigen, psnumerolote, TRUE, regRecupera.cod_proc, regRecupera.cod_subp );
 END IF;
 ln_idUltiRegi := regRecupera.oid_Deta_cargo;
 END LOOP;
 END IF;
 EXIT WHEN c_Incobrable%NOTFOUND;
 END LOOP;
 CLOSE c_Incobrable;

 /* Actualizando ultimo registro procesado */
 IF ln_idUltiRegi IS NOT NULL AND ln_idUltiRegi > 0 THEN
 UPDATE PER_CONTR_REGIS_PROGR
 SET
 FEC_ULTI_EJEC = SYSDATE,
 OID_ULTI_REGI_PROC = ln_idUltiRegi
 WHERE PAIS_COD_PAIS = psCodPais AND
 COD_PROG = ls_codprog AND
 EST_CTRL_REGI_PROG = '1';
 END IF;

 IF (psTipoOrigen = '06') THEN
 ls_codprog := '01';
 END IF;

 /* Borrando tablas temporales */
 DELETE FROM PER_GTT_PROCE_PROGR;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_PROCE_CARGO_ABONO_DIREC: '||ls_sqlerrm);
END PER_PR_PROCE_CARGO_ABONO_DIREC;


/**************************************************************************
Descripcion : Procedimiento auxiliar de Cargo y Abono directo e Incobrables
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_RECU_CARGO_ABONO_DIREC
 (psCodPais VARCHAR2,
 regRecupera tRegCargoAbonoDirecto,
 pbInsertarSiempre BOOLEAN := FALSE,
 pnImporteDocu NUMBER := 0)
RETURN per_cuent_corri_docle%ROWTYPE
IS
 regCuentaCorriDocle per_cuent_corri_docle%ROWTYPE;
 ls_codsubp ccc_subpr.cod_subp%TYPE;
 ls_codproc ccc_proce.cod_proc%TYPE;
 ln_idcanal cra_perio.cana_oid_cana%TYPE;
 ln_idacce seg_acces.oid_acce%TYPE;
 ls_parte1 VARCHAR2(2);
 ls_parte2 VARCHAR2(8);
 ls_parte VARCHAR2(10);
 ln_id_peri cra_perio.peri_oid_peri%TYPE;
 ln_secuencia NUMBER;

BEGIN
 regCuentaCorriDocle.Pais_Cod_Pais := pscodpais;

 /* codigo de subproceso */
 regCuentaCorriDocle.Cod_Subp_Crea := regRecupera.cod_subp;

 /* codigo de proceso */
 regCuentaCorriDocle.Cod_Proc_Crea := regRecupera.cod_proc;

 /* codigo de sociedad */
 SELECT a.cod_soci
 INTO
 regCuentaCorriDocle.Cod_Soci
 FROM seg_socie a
 WHERE
 a.oid_soci = regRecupera.soci_oid_soci;

 /* codigo de canal */
 SELECT b.cod_cana
 INTO regCuentaCorriDocle.Cod_Cana
 FROM cra_perio a,
 seg_canal b
 WHERE a.oid_peri = regRecupera.perd_oid_peri AND
 b.oid_cana = a.cana_oid_cana;

 /* acceso/subacceso */
 SELECT a.cod_sbac, b.cod_acce
 INTO regCuentaCorriDocle.Cod_Sbac, regCuentaCorriDocle.Cod_Acce
 FROM seg_subac a,
 seg_acces b
 WHERE a.oid_sbac = regRecupera.sbac_oid_sbac AND
 b.oid_acce = a.acce_oid_acce;

 /* nro de solicitud consolidado */
 ls_parte1 := LPAD(regRecupera.val_ejer_cuot , 2, '0');
 ls_parte2 := LPAD(regRecupera.num_iden_cuot , 8, '0');
 ls_parte := ls_parte1 || ls_parte2;
 regCuentaCorriDocle.Num_Soli_Cons := to_number(ls_parte);

 /* marca */
 SELECT a.cod_marc
 INTO regCuentaCorriDocle.Cod_Marc
 FROM seg_marca a
 WHERE
 a.oid_marc = regRecupera.marc_oid_marc;

 /* periodo */
 SELECT b.cod_peri
 INTO regCuentaCorriDocle.Cod_Peri
 FROM cra_perio a,
 seg_perio_corpo b
 WHERE
 a.oid_peri = regRecupera.perd_oid_peri AND
 b.oid_peri = a.peri_oid_peri;

 /* cliente */
 SELECT a.cod_clie
 INTO regCuentaCorriDocle.Cod_Clie
 FROM mae_clien a
 WHERE
 a.oid_clie = regRecupera.clie_oid_clie;

 /* tipo de documento */
 BEGIN
 SELECT a.num_docu_iden, b.cod_tipo_docu
 INTO
 regCuentaCorriDocle.Num_Iden_Fisc, regCuentaCorriDocle.Tip_Docu_Iden_Fisc
 FROM mae_clien_ident a,
 mae_tipo_docum b
 WHERE
 a.clie_oid_clie = regRecupera.clie_oid_clie AND
 b.oid_tipo_docu = a.tdoc_oid_tipo_docu AND
 b.ind_doc_iden_fisc = '1' AND
 a.VAL_IDEN_DOCU_PRIN = 1 AND
 rownum = 1;
 EXCEPTION
 WHEN no_data_found THEN
 regCuentaCorriDocle.Num_Iden_Fisc := NULL;
 regCuentaCorriDocle.Tip_Docu_Iden_Fisc := NULL;
 END;

 BEGIN
 SELECT a.num_docu_iden, b.cod_tipo_docu
 INTO
 regCuentaCorriDocle.Num_Iden_Nnal, regCuentaCorriDocle.Tip_Docu_Iden_Nnal
 FROM mae_clien_ident a,
 mae_tipo_docum b
 WHERE
 a.clie_oid_clie = regRecupera.clie_oid_clie AND
 b.oid_tipo_docu = a.tdoc_oid_tipo_docu AND
 b.ind_dni = '1' AND
 a.VAL_IDEN_DOCU_PRIN = 1 AND
 rownum = 1;
 EXCEPTION
 WHEN no_data_found THEN
 regCuentaCorriDocle.Num_Iden_Nnal := NULL;
 regCuentaCorriDocle.Tip_Docu_Iden_Nnal := NULL;
 END;

 /* tipo de documento legal */
 regCuentaCorriDocle.Tip_Docu_Lega := NULL;
 IF regRecupera.tido_oid_tipo_docu IS NOT NULL THEN
 SELECT a.cod_tipo_docu
 INTO regCuentaCorriDocle.Tip_Docu_Lega
 FROM fac_tipo_docum a
 WHERE
 a.oid_tipo_docu = regRecupera.tido_oid_tipo_docu;
 END IF;

 /* fecha emision documento */
 regCuentaCorriDocle.Fec_Emis_Docu := regRecupera.fec_docu;

 /* fecha facturacion */
 regCuentaCorriDocle.Fec_Fact := regRecupera.fec_docu;

 /* fecha vencimiento couta */
 regCuentaCorriDocle.Fec_Venc_Cuot := regRecupera.fec_venc;

 /* medio de pago */
 IF regRecupera.mpab_oid_medi_pago IS NOT NULL THEN
 SELECT a.cod_medi_pago
 INTO regCuentaCorriDocle.Cod_Medi_Pago
 FROM
 bel_medio_pago a
 WHERE
 a.oid_medi_pago = regRecupera.mpab_oid_medi_pago;
 END IF;
 IF (regRecupera.imp_movi > 0) THEN
 regCuentaCorriDocle.Ind_Reca_Perc := NULL;
 regCuentaCorriDocle.Ind_Afec := 'N';
 regCuentaCorriDocle.Tip_Soli_Perc := NULL;
 regCuentaCorriDocle.Imp_Inaf_Perc := regRecupera.imp_movi;
 ELSE
 regCuentaCorriDocle.Ind_Reca_Perc := regRecupera.ind_reca_impo_nega;
 regCuentaCorriDocle.Ind_Afec := NULL;
 regCuentaCorriDocle.Tip_Soli_Perc := regRecupera.tip_soli_impo_nega;
 regCuentaCorriDocle.Imp_Inaf_Perc := 0.00;
 END IF;

 regCuentaCorriDocle.Cod_Cana_Refe := NULL;
 regCuentaCorriDocle.Cod_Acce_Refe := NULL;
 regCuentaCorriDocle.Cod_Sbac_Refe := NULL;
 regCuentaCorriDocle.Num_Soli_Cons_Refe := NULL;

 regCuentaCorriDocle.Eje_Docu_Inte := NULL;
 regCuentaCorriDocle.Num_Docu_Inte := NULL;
 ln_secuencia := PER_FN_sgte_sec_orden_couta(regCuentaCorriDocle);
 regCuentaCorriDocle.Num_Orde_Cuot := ln_secuencia;
 regCuentaCorriDocle.Cor_Cuen_Corr_Dole := PER_FN_sgte_corre_corri_docle();

 regCuentaCorriDocle.Cod_Marc_Situ := regRecupera.cod_marc_situ;
 regCuentaCorriDocle.Tip_Carg_Abon := regRecupera.cod_tipo_carg_abon;
 regCuentaCorriDocle.Cod_Form_Pago := NULL;
 regCuentaCorriDocle.Cod_Cana_Perc := NULL;
 regCuentaCorriDocle.Cod_Acce_Perc := NULL;
 regCuentaCorriDocle.Cod_Suba_Perc := NULL;
 regCuentaCorriDocle.Num_Soli_Cons_Perc := NULL;
 regCuentaCorriDocle.Por_Perc := 0;
 regCuentaCorriDocle.Fac_Calc_Perc := 0;
 regCuentaCorriDocle.Imp_Tota_Docu := regRecupera.imp_movi;
 regCuentaCorriDocle.Imp_Cuot := regRecupera.imp_movi;
 regCuentaCorriDocle.Imp_Cuot_Paga := 0;
 regCuentaCorriDocle.Imp_Cuot_Pend := regCuentaCorriDocle.Imp_Cuot;
 regCuentaCorriDocle.Imp_Paga_Inaf_Perc := 0;
 regCuentaCorriDocle.Imp_Pend_Inaf_Perc := regCuentaCorriDocle.Imp_Inaf_Perc;
 regCuentaCorriDocle.Imp_Afec_Perc := 0;
 regCuentaCorriDocle.Imp_Paga_Afec_Perc := 0;
 regCuentaCorriDocle.Imp_Pend_Afec_Perc := 0;
 regCuentaCorriDocle.Imp_Perc_Perc := 0;
 regCuentaCorriDocle.Imp_Paga_Perc := 0;
 regCuentaCorriDocle.Imp_Pend_Perc := 0;
 regCuentaCorriDocle.Usu_Digi := USER;
 regCuentaCorriDocle.Fec_Digi := SYSDATE;
 regcuentacorridocle.est_cuen_corr_dole := '1';


 /* Insertando en PER_CUENT_CORRI_DOCLE */
 IF NOT pbInsertarSiempre THEN
 IF (regCuentaCorriDocle.Cod_Proc_Crea = 'CCC001' AND
 (regCuentaCorriDocle.Cod_Subp_Crea = 1 OR regCuentaCorriDocle.Cod_Subp_Crea = 2)) THEN
 insert_per_cuent_corri_docle(regCuentaCorriDocle);
 END IF;
 IF (regCuentaCorriDocle.Cod_Proc_Crea = 'CCC003' AND regCuentaCorriDocle.Cod_Subp_Crea = 4 ) THEN
 insert_per_cuent_corri_docle(regCuentaCorriDocle);
 END IF;
 ELSE
 IF regRecupera.VAL_INDI_ACTU_CUOT = 1 THEN
 regcuentacorridocle.imp_cuot := regRecupera.imp_movi;
 regcuentacorridocle.imp_cuot_pend := regRecupera.imp_movi;
 ELSE
 regcuentacorridocle.imp_cuot := 0;
 regcuentacorridocle.imp_cuot_pend := 0;
 END IF;
 IF ((regRecupera.cod_proc = 'CCC006' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCX06' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCA06' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCEV6' AND regRecupera.cod_subp = 1) OR
 (regRecupera.cod_proc = 'CCCN06' AND regRecupera.cod_subp = 1)) THEN
 regCuentaCorriDocle.Cod_Marc_Situ := 'DE';
 ELSE
 regCuentaCorriDocle.Cod_Marc_Situ := 'PP';
 END IF;
 IF pnImporteDocu > 0 THEN
 regCuentaCorriDocle.Imp_Tota_Docu := pnImporteDocu;
 END IF;
 insert_per_cuent_corri_docle(regCuentaCorriDocle);
 END IF;
 RETURN regCuentaCorriDocle;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_RECU_CARGO_ABONO_DIREC: '||ls_sqlerrm);

END PER_FN_RECU_CARGO_ABONO_DIREC;


/**************************************************************************
Descripcion : Procedimiento auxiliar del procedure PER_PR_CANCE_AUTOM_DEUDA que
 actualiza los montos de la Couta a Cancelar
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pn_val_indi_actu : Indicador de Actualiza Couta con importe pagado
 ps_codMarcaSalida : Marca de Salida
 ps_tipo_Cargo_Abon: Tipo de Cargo Abono
 psTipoOrigen : Tipo de Origen de datos
 psNumeroLote : Numero de Lote
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta de Cancelacion
 pregCuentaACancelar : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta a Cancelar
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_ACTU_IMPORTE_CANCELAR
( pn_val_indi_actu NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 pregCuentaACancelar per_cuent_corri_docle%ROWTYPE)
IS
 ln_importe_cancelado_per NUMBER;
 ln_importe_cancelado NUMBER;
 ln_importe_cancelado_i_per NUMBER;
 ln_importe_cancelado_a_per NUMBER;
 ln_imp_couta NUMBER;
 ln_imp_cout_paga NUMBER;
 ln_imp_paga_i_per NUMBER;
 ln_imp_paga_a_per NUMBER;
 ln_imp_paga_per NUMBER;

 ln_couta NUMBER;
 ln_couta_pagado NUMBER;
 ln_couta_pendiente NUMBER;
 ln_pagado_i_per NUMBER;
 ln_pagado_a_per NUMBER;
 ln_pagado_per NUMBER;
 ls_marca_Salida ccc_marca_situa.cod_marc_situ%TYPE;
 registroPago per_regis_pagos%ROWTYPE;
 registroSoliMone per_solic_monet%ROWTYPE;

BEGIN
 /* Inicializando valores */
 IF pn_val_indi_actu = 1 THEN --En caso sea SI
 IF ABS(pRegistro.Imp_Cuot_pend) < abs(Pregcuentaacancelar.Imp_Cuot_Pend) THEN
 ln_importe_cancelado := abs(pRegistro.Imp_Cuot_pend);
 ELSE
 ln_importe_cancelado := abs(Pregcuentaacancelar.Imp_Cuot_Pend);
 END IF;
 IF ln_importe_cancelado <= abs(Pregcuentaacancelar.Imp_Pend_Afec_Perc) THEN
 ln_importe_cancelado_a_per := ln_importe_cancelado;
 ln_importe_cancelado_i_per := 0;
 ELSE
 ln_importe_cancelado_a_per := abs(Pregcuentaacancelar.Imp_Pend_Afec_Perc);
 IF (ln_importe_cancelado - ln_importe_cancelado_a_per) >= abs(Pregcuentaacancelar.Imp_Pend_Inaf_Perc) THEN
 ln_importe_cancelado_i_per := abs(Pregcuentaacancelar.Imp_Pend_Inaf_Perc);
 ELSE
 ln_importe_cancelado_i_per := ln_importe_cancelado - ln_importe_cancelado_a_per ;
 END IF;
 END IF;
 ln_importe_cancelado_per := round((ln_importe_cancelado_a_per * Pregcuentaacancelar.Por_Perc) / 100, 2);

 ELSE -- En caso sea NO
 ln_importe_cancelado := 0;
 ln_importe_cancelado_i_per := 0;
 ln_importe_cancelado_a_per := 0;
 ln_importe_cancelado_per := 0;
 END IF;

 /* Actualizando montos Ctas a Cancelar */
 ln_couta_pagado := Pregcuentaacancelar.Imp_Cuot_Paga + ln_importe_cancelado;
 ln_couta_pendiente := Pregcuentaacancelar.Imp_Cuot_Pend - ln_importe_cancelado;
 ln_pagado_i_per := ln_importe_cancelado_i_per;
 ln_pagado_a_per := ln_importe_cancelado_a_per;
 ln_pagado_per := ln_importe_cancelado_per;
 IF (pn_val_indi_actu = 1 AND Pregcuentaacancelar.Imp_Cuot = ln_couta_pagado) OR
 (pn_val_indi_actu = 0) THEN
 ls_marca_Salida := ps_codMarcaSalida;
 ELSE
 ls_marca_Salida := pregcuentaacancelar.cod_marc_situ;
 END IF;

 UPDATE per_cuent_corri_docle a
 SET
 a.IMP_CUOT_PAGA = ln_couta_pagado,
 a.IMP_CUOT_PEND = ln_couta_pendiente,
 a.IMP_PAGA_INAF_PERC = a.imp_paga_inaf_perc + ln_pagado_i_per,
 a.IMP_PEND_INAF_PERC = a.Imp_Pend_Inaf_Perc - ln_pagado_i_per,
 a.IMP_PAGA_AFEC_PERC = a.imp_paga_afec_perc + ln_pagado_a_per,
 a.IMP_PEND_AFEC_PERC = a.Imp_Pend_Afec_Perc - ln_pagado_a_per,
 a.IMP_PAGA_PERC = a.imp_paga_perc + ln_pagado_per,
 a.IMP_PEND_PERC = a.Imp_Pend_Perc - ln_pagado_per,
 a.COD_MARC_SITU = trim(ls_marca_Salida),
 a.USU_MODI = USER,
 a.FEC_MODI = SYSDATE
 WHERE
 a.pais_cod_pais = pregCuentaACancelar.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregCuentaACancelar.cor_cuen_corr_dole;

 /* Actualizando montos de la Cta de Cancelacion */
 ln_couta_pagado := Pregistro.Imp_Cuot_Paga - ln_importe_cancelado;
 ln_couta_pendiente := Pregistro.Imp_Cuot_Pend + ln_importe_cancelado;
 ln_pagado_i_per := Pregistro.Imp_Paga_Inaf_Perc - ln_importe_cancelado_i_per;
 ln_pagado_a_per := Pregistro.Imp_Paga_Afec_Perc - ln_importe_cancelado_a_per;
 ln_pagado_per := Pregistro.Imp_Paga_Perc - ln_importe_cancelado_per;
 IF (pn_val_indi_actu = 1 AND Pregistro.Imp_Cuot = ln_couta_pagado) OR
 (pn_val_indi_actu = 0) THEN
 ls_marca_Salida := ps_codMarcaSalida;
 ELSE
 ls_marca_Salida := pregistro.cod_marc_situ;
 END IF;

 UPDATE per_cuent_corri_docle a
 SET
 a.IMP_CUOT_PAGA = ln_couta_pagado,
 a.IMP_CUOT_PEND = ln_couta_pendiente,

 a.IMP_INAF_PERC = ln_pagado_i_per,
 a.IMP_PAGA_INAF_PERC = ln_pagado_i_per,
 a.IMP_PEND_INAF_PERC = 0,

 A.IMP_AFEC_PERC = ln_pagado_a_per,
 a.IMP_PAGA_AFEC_PERC = ln_pagado_a_per,
 a.IMP_PEND_AFEC_PERC = 0,

 A.IMP_PERC_PERC = ln_pagado_per,
 a.IMP_PAGA_PERC = ln_pagado_per,
 a.IMP_PEND_PERC = 0,

 a.COD_MARC_SITU = trim(ls_marca_Salida),
 a.USU_MODI = USER,
 a.FEC_MODI = SYSDATE
 WHERE
 a.pais_cod_pais = pregistro.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregistro.cor_cuen_corr_dole;

 /* Obteniendo Registro de la Cuota de Cancelacion */
 SELECT *
 INTO pregistro
 FROM PER_CUENT_CORRI_DOCLE A
 WHERE
 a.pais_cod_pais = pregistro.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregistro.cor_cuen_corr_dole;


 /* Seteando valores para insertar en Registro de Pagos */
 IF ln_importe_cancelado <> 0 THEN
 Registropago.Pais_Cod_Pais := pregCuentaACancelar.Pais_Cod_Pais;
 Registropago.Cod_Soci := pregCuentaACancelar.Cod_Soci;
 Registropago.Cod_Cana := pregCuentaACancelar.Cod_Cana;
 Registropago.Cod_Acce := pregCuentaACancelar.Cod_Acce;
 Registropago.Cod_Sbac := pregCuentaACancelar.Cod_Sbac;
 Registropago.Num_Soco := pregCuentaACancelar.Num_Soli_Cons;
 Registropago.Tip_Dole := pregCuentaACancelar.Tip_Docu_Lega;
 Registropago.EJE_DINT := pregCuentaACancelar.Eje_Docu_Inte;
 Registropago.NUM_DINT := pregCuentaACancelar.Num_Docu_Inte;
 Registropago.Num_Cuot := pregCuentaACancelar.Num_Orde_Cuot;
 Registropago.Sec_Abon := PER_FN_SGTE_SEC_ABON(RegistroPago);
 Registropago.FEC_DOLE := pregCuentaACancelar.Fec_Emis_Docu;
 Registropago.TIP_ABON := ps_tipo_Cargo_Abon;
 Registropago.Num_Abon := NULL;
 Registropago.Cod_Cons := pregCuentaACancelar.Cod_Clie;
 IF (Registropago.Tip_Dole = '001' OR
 Registropago.Tip_Dole = '002' OR
 Registropago.Tip_Dole = '003' OR
 Registropago.Tip_Dole = '021' OR
 Registropago.Tip_Dole = '022') THEN
 Registropago.Tip_Doid := pregCuentaACancelar.Tip_Docu_Iden_Fisc;
 Registropago.Num_Doid := pregCuentaACancelar.Num_Iden_Fisc;
 ELSE
 Registropago.Tip_Doid := pregCuentaACancelar.Tip_Docu_Iden_Nnal;
 Registropago.Num_Doid := pregCuentaACancelar.Num_Iden_Nnal;
 END IF;
 Registropago.Cod_Peri := Pregistro.Cod_Peri;
 Registropago.Mon_Pago := abs(ln_importe_cancelado_a_per + ln_importe_cancelado_per);
 Registropago.Mon_Perc := abs(ln_importe_cancelado_per);
 Registropago.Mon_Todl := pregCuentaACancelar.Imp_Tota_Docu;
 Registropago.Por_Perc := pregCuentaACancelar.Por_Perc;
 Registropago.Fac_Cape := pregCuentaACancelar.Fac_Calc_Perc;
 Registropago.Fec_Pago := pRegistro.Fec_Fact; --GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 Registropago.Fec_Proc := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 IF (pRegistro.Imp_Perc_Perc = 0) THEN
 Registropago.IND_REPE := 'N';
 ELSE
 Registropago.IND_REPE := pRegistro.Ind_Reca_Perc;
 END IF;
 Registropago.Sta_Repe := '2';
 Registropago.FEC_DIGI := SYSDATE;
 Registropago.Usu_Digi := USER;
 Registropago.Est_Repa := '1';
 Registropago.Cor_Repa := PER_FN_sgte_corre_reg_pago();

 /* Insertando registro en Registro de Pagos */
 INSERT_PER_REGIS_PAGOS(Registropago);
 END IF;

 /* Seteando valores para insertar en Solicitudes Monetarias */
 IF ln_importe_cancelado_per <> 0 AND pn_val_indi_actu = 1 THEN
 registroSoliMone.Pais_Cod_Pais := Pregistro.Pais_Cod_Pais;
 registroSoliMone.Tior_Tipo_Orig_Dato := Pstipoorigen;
 registroSoliMone.Num_Lote := PsNumeroLote;
 registroSoliMone.Cod_Peri := Pregistro.Cod_Peri;
 registroSoliMone.Cod_Clie := Pregistro.Cod_Clie;
 registroSoliMone.Fec_Fact := pRegistro.Fec_Fact; --GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := ln_importe_cancelado_per;
 registroSoliMone.Cod_Cana_Refe := pregCuentaACancelar.Cod_Cana_Perc;
 registroSoliMone.Cod_Acce_Refe := pregCuentaACancelar.Cod_Acce_Perc;
 registroSoliMone.Cod_Sbac_Refe := pregCuentaACancelar.Cod_Suba_Perc;
 registroSoliMone.Num_Docu_Refe := pregCuentaACancelar.Num_Soli_Cons_Perc;
 registroSoliMone.Cod_Tipo_Soli := Pregistro.Tip_Soli_Perc;
 registroSoliMone.Cod_Acce := 'PR';
 registroSoliMone.Cod_Sbac := '290';
 registroSoliMone.Tip_Posi := 'PR';
 registroSoliMone.Val_Cant := -1;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 Registrosolimone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_Soli_Mone := PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := pregCuentaACancelar.Cor_Cuen_Corr_Dole;
 /* Insertando registro en Solicitudes Monetarias */
 INSERT_PER_SOLI_MONE(RegistroSoliMone);
 END IF;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_ACTU_IMPORTE_CANCELAR: '||ls_sqlerrm);
END PER_PR_ACTU_IMPORTE_CANCELAR;


/**************************************************************************
Descripcion : Procedimiento auxiliar del procedure PER_PR_DEUDA_POSITIVA que
 actualiza los montos de la Couta a Cancelar
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pn_val_indi_actu : Indicador de Actualiza Couta con importe pagado
 ps_codMarcaSalida : Marca de Salida
 ps_tipo_Cargo_Abon: Tipo de Cargo Abono
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta de Cancelacion
 pregCuentaACancelar : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta a Cancelar
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_ACTU_IMPORTE_ABONO
( pn_val_indi_actu NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 ps_cod_peri VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 pregCuentaACancelar per_cuent_corri_docle%ROWTYPE)
IS
 ln_importe_cancelado_per NUMBER;
 ln_importe_cancelado NUMBER;
 ln_importe_cancelado_i_per NUMBER;
 ln_importe_cancelado_a_per NUMBER;
 ln_imp_couta NUMBER;
 ln_imp_cout_paga NUMBER;
 ln_imp_paga_i_per NUMBER;
 ln_imp_paga_a_per NUMBER;
 ln_imp_paga_per NUMBER;

 ln_couta NUMBER;
 ln_couta_pagado NUMBER;
 ln_couta_pendiente NUMBER;
 ln_pagado_i_per NUMBER;
 ln_pagado_a_per NUMBER;
 ln_pagado_per NUMBER;
 ln_factorTemp NUMBER;
 ls_marca_Salida ccc_marca_situa.cod_marc_situ%TYPE;
 registroPago per_regis_pagos%ROWTYPE;
 registroSoliMone per_solic_monet%ROWTYPE;
 lnMontoPendiente NUMBER;

BEGIN
 /* Inicializando valores */
 IF pn_val_indi_actu = 1 THEN --En caso sea SI
 IF ABS(pRegistro.Imp_Cuot_pend) < abs(Pregcuentaacancelar.Imp_Cuot_Pend) THEN
 ln_importe_cancelado := abs(pRegistro.Imp_Cuot_pend);
 ELSE
 ln_importe_cancelado := abs(Pregcuentaacancelar.Imp_Cuot_Pend);
 END IF;

 IF pRegistro.Imp_pend_Afec_Perc > 0 THEN
 lnMontoPendiente := abs(pRegistro.Imp_pend_Afec_Perc);
 ELSE
 lnMontoPendiente := 0.0;
 END IF;

 IF ln_importe_cancelado >= lnMontoPendiente THEN
 ln_importe_cancelado_a_per := lnMontoPendiente;
 ln_importe_cancelado_i_per := ln_importe_cancelado - ln_importe_cancelado_a_per;
 ELSE
 ln_importe_cancelado_a_per := ln_importe_cancelado;
 ln_importe_cancelado_i_per := 0.0;
 END IF;
 ln_importe_cancelado_per := round((ln_importe_cancelado_a_per * pRegistro.Por_Perc) /100, 2);

 ELSE -- En caso sea NO
 ln_importe_cancelado := 0;
 ln_importe_cancelado_i_per := 0;
 ln_importe_cancelado_a_per := 0;
 ln_importe_cancelado_per := 0;
 END IF;

 /* Actualizando montos Ctas a Cancelar */
 ln_couta_pagado := Pregcuentaacancelar.Imp_Cuot_Paga - ln_importe_cancelado;
 ln_couta_pendiente := Pregcuentaacancelar.Imp_Cuot_Pend + ln_importe_cancelado;
 ln_pagado_i_per := -1 * ln_importe_cancelado_i_per;
 ln_pagado_a_per := -1 * ln_importe_cancelado_a_per;
 IF (pn_val_indi_actu = 1 AND abs(Pregcuentaacancelar.Imp_Cuot) = abs(ln_couta_pagado)) OR
 (pn_val_indi_actu = 0) THEN
 ls_marca_Salida := ps_codMarcaSalida;
 ELSE
 ls_marca_Salida := pregcuentaacancelar.cod_marc_situ;
 END IF;

 UPDATE per_cuent_corri_docle a
 SET
 a.IMP_CUOT_PAGA = ln_couta_pagado,
 a.IMP_CUOT_PEND = ln_couta_pendiente,

 a.IMP_INAF_PERC = a.Imp_Inaf_Perc + ln_pagado_i_per,
 a.IMP_PAGA_INAF_PERC = a.imp_paga_inaf_perc + ln_pagado_i_per,
 a.IMP_PEND_INAF_PERC = a.imp_inaf_perc - a.Imp_paga_Inaf_Perc,

 a.IMP_AFEC_PERC = a.Imp_Afec_Perc + ln_pagado_a_per,
 a.IMP_PAGA_AFEC_PERC = a.imp_paga_afec_perc + ln_pagado_a_per,
 a.IMP_PEND_AFEC_PERC = a.imp_afec_perc - a.imp_paga_afec_perc,

 a.IMP_PERC_PERC = a.Imp_perc_Perc - ln_importe_cancelado_per,
 a.IMP_PAGA_PERC = a.imp_paga_perc - ln_importe_cancelado_per,
 a.IMP_PEND_PERC = a.imp_perc_perc - a.imp_paga_perc,

 a.COD_MARC_SITU = trim(ls_marca_Salida),
 a.USU_MODI = USER,
 a.FEC_MODI = SYSDATE
 WHERE
 a.pais_cod_pais = pregCuentaACancelar.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregCuentaACancelar.cor_cuen_corr_dole;

 /* Actualizando montos de la Cta de Cancelacion */
 ln_couta_pagado := Pregistro.Imp_Cuot_Paga + ln_importe_cancelado;
 ln_couta_pendiente := Pregistro.Imp_Cuot_Pend - ln_importe_cancelado;
 ln_pagado_i_per := Pregistro.Imp_Paga_Inaf_Perc + ln_importe_cancelado_i_per;
 ln_pagado_a_per := Pregistro.Imp_Paga_Afec_Perc + ln_importe_cancelado_a_per;
 ln_pagado_per := Pregistro.Imp_Paga_Perc + ln_importe_cancelado_per;
 IF (pn_val_indi_actu = 1 AND Pregistro.Imp_Cuot = ln_couta_pagado) OR
 (pn_val_indi_actu = 0) THEN
 ls_marca_Salida := ps_codMarcaSalida;
 ELSE
 ls_marca_Salida := pregistro.cod_marc_situ;
 END IF;

 UPDATE per_cuent_corri_docle a
 SET
 a.IMP_CUOT_PAGA = ln_couta_pagado,
 a.IMP_CUOT_PEND = ln_couta_pendiente,

 a.IMP_PAGA_INAF_PERC = ln_pagado_i_per,
 a.IMP_PEND_INAF_PERC = a.imp_inaf_perc - ln_pagado_i_per,

 a.IMP_PAGA_AFEC_PERC = ln_pagado_a_per,
 a.IMP_PEND_AFEC_PERC = a.imp_afec_perc - ln_pagado_a_per,

 a.IMP_PAGA_PERC = ln_pagado_per,
 a.IMP_PEND_PERC = a.imp_perc_perc - ln_pagado_per,

 a.COD_MARC_SITU = trim(ls_marca_Salida),
 a.USU_MODI = USER,
 a.FEC_MODI = SYSDATE
 WHERE
 a.pais_cod_pais = pregistro.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregistro.cor_cuen_corr_dole;

 /* Obteniendo Registro de la Cuota de Cancelacion */
 SELECT *
 INTO pregistro
 FROM PER_CUENT_CORRI_DOCLE A
 WHERE
 a.pais_cod_pais = pregistro.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregistro.cor_cuen_corr_dole;

 /* Seteando valores para insertar en Registro de Pagos */
 IF ln_importe_cancelado <> 0 THEN
 Registropago.Pais_Cod_Pais := pRegistro.Pais_Cod_Pais;
 Registropago.Cod_Soci := pRegistro.Cod_Soci;
 Registropago.Cod_Cana := pRegistro.Cod_Cana;
 Registropago.Cod_Acce := pRegistro.Cod_Acce;
 Registropago.Cod_Sbac := pRegistro.Cod_Sbac;
 Registropago.Num_Soco := pRegistro.Num_Soli_Cons;
 Registropago.Tip_Dole := pRegistro.Tip_Docu_Lega;
 Registropago.EJE_DINT := pRegistro.Eje_Docu_Inte;
 Registropago.NUM_DINT := pRegistro.Num_Docu_Inte;
 Registropago.Num_Cuot := pRegistro.Num_Orde_Cuot;
 Registropago.Sec_Abon := PER_FN_SGTE_SEC_ABON(RegistroPago);
 Registropago.FEC_DOLE := pRegistro.Fec_Emis_Docu;
 Registropago.TIP_ABON := ps_tipo_Cargo_Abon;
 Registropago.Num_Abon := NULL;
 Registropago.Cod_Cons := pRegistro.Cod_Clie;
 IF (Registropago.Tip_Dole = '001' OR
 Registropago.Tip_Dole = '002' OR
 Registropago.Tip_Dole = '003' OR
 Registropago.Tip_Dole = '021' OR
 Registropago.Tip_Dole = '022') THEN
 Registropago.Tip_Doid := pRegistro.Tip_Docu_Iden_Fisc;
 Registropago.Num_Doid := pRegistro.Num_Iden_Fisc;
 ELSE
 Registropago.Tip_Doid := pRegistro.Tip_Docu_Iden_Nnal;
 Registropago.Num_Doid := pRegistro.Num_Iden_Nnal;
 END IF;
 Registropago.Cod_Peri := pRegistro.Cod_Peri; --ps_cod_peri;
 Registropago.Mon_Pago := abs(ln_importe_cancelado_a_per + ln_importe_cancelado_per);
 Registropago.Mon_Perc := abs(ln_importe_cancelado_per);
 Registropago.Mon_Todl := pRegistro.Imp_Tota_Docu;
 Registropago.Por_Perc := pRegistro.Por_Perc;
 Registropago.Fac_Cape := pRegistro.Fac_Calc_Perc;
 Registropago.Fec_Pago := pregCuentaACancelar.Fec_Fact; --GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 Registropago.Fec_Proc := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA(); --pRegistro.Fec_Fact;
 IF (Registropago.Mon_Perc = 0) THEN
 Registropago.IND_REPE := 'N';
 ELSE
 IF pregCuentaACancelar.Ind_Reca_Perc = 'S' THEN
 Registropago.IND_REPE := 'S';
 ELSE
 Registropago.IND_REPE := 'N';
 END IF;
 END IF;
 Registropago.Sta_Repe := '2';
 Registropago.FEC_DIGI := SYSDATE;
 Registropago.Usu_Digi := USER;
 Registropago.Est_Repa := '1';
 Registropago.Cor_Repa := PER_FN_sgte_corre_reg_pago();

 /* Insertando registro en Registro de Pagos */
 INSERT_PER_REGIS_PAGOS(Registropago);
 END IF;

 /* Seteando valores para insertar en Solicitudes Monetarias */
 IF ln_importe_cancelado_per <> 0 AND pn_val_indi_actu = 1 THEN
 registroSoliMone.Pais_Cod_Pais := Pregistro.Pais_Cod_Pais;
 Registrosolimone.Tior_Tipo_Orig_Dato := pstipoorigen;
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := pRegistro.cod_peri; -- ps_cod_peri;
 registroSoliMone.Cod_Clie := Pregistro.Cod_Clie;
 registroSoliMone.Fec_Fact := pRegistro.Fec_Fact; --GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := ln_importe_cancelado_per;
 registroSoliMone.Cod_Cana_Refe := pRegistro.Cod_Cana_Perc;
 registroSoliMone.Cod_Acce_Refe := pRegistro.Cod_Acce_Perc;
 registroSoliMone.Cod_Sbac_Refe := pRegistro.Cod_Suba_Perc;
 registroSoliMone.Num_Docu_Refe := pRegistro.Num_Soli_Cons_Perc;
 registroSoliMone.Cod_Tipo_Soli := pregCuentaACancelar.Tip_Soli_Perc;
 registroSoliMone.Cod_Acce := 'PR';
 registroSoliMone.Cod_Sbac := '290';
 registroSoliMone.Tip_Posi := 'PR';
 registroSoliMone.Val_Cant := -1;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 Registrosolimone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_Soli_Mone := PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := pregistro.Cor_Cuen_Corr_Dole;

 /* Insertando registro en Solicitudes Monetarias */
 INSERT_PER_SOLI_MONE(RegistroSoliMone);
 END IF;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_ACTU_IMPORTE_ABONO: '||ls_sqlerrm);
END PER_PR_ACTU_IMPORTE_ABONO;


/**************************************************************************
Descripcion : Funcion auxiliar del procedure PER_PR_DEUDA_POSITIVA que
 actualiza los montos de la Couta a Cancelar
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pn_val_indi_actu : Indicador de Actualiza Couta con importe pagado
 ps_codMarcaSalida : Marca de Salida
 ps_tipo_Cargo_Abon: Tipo de Cargo Abono
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta de Cancelacion
 pregCuentaACancelar : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta a Cancelar
Devuelve : True : Si importe de couta > importe pagado
 False : Si importe de couta = importe pagado
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_ACTU_IMPORTE_PAGO_EXCE
( pn_val_indi_actu NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 psTipoSoliExcesoPosi VARCHAR2,
 psTipoSoliApliPago VARCHAR2,
 psTipoSoliPerc VARCHAR2,
 ps_cod_peri VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pRegistro IN OUT per_cuent_corri_docle%ROWTYPE,
 pregCuentaACancelar per_movim_banca_detal%ROWTYPE)
IS
 ln_importe_cancelado_per NUMBER;
 ln_importe_cancelado NUMBER;
 ln_importe_cancelado_i_per NUMBER;
 ln_importe_cancelado_a_per NUMBER;
 ln_imp_couta NUMBER;
 ln_imp_cout_paga NUMBER;
 ln_imp_paga_i_per NUMBER;
 ln_imp_paga_a_per NUMBER;
 ln_imp_paga_per NUMBER;

 ln_couta NUMBER;
 ln_couta_pagado NUMBER;
 ln_couta_pendiente NUMBER;
 ln_pagado_i_per NUMBER;
 ln_pagado_a_per NUMBER;
 ln_pagado_per NUMBER;
 ls_marca_Salida ccc_marca_situa.cod_marc_situ%TYPE;
 registroPago per_regis_pagos%ROWTYPE;
 registroSoliMone per_solic_monet%ROWTYPE;

BEGIN
 /* Inicializando valores */
 IF pn_val_indi_actu = 1 THEN --En caso sea SI
 IF ABS(pRegistro.Imp_Cuot_pend + pRegistro.Imp_Pend_Perc) < abs(Pregcuentaacancelar.Imp_Pago_Pend) THEN
 ln_importe_cancelado := abs(pRegistro.Imp_Cuot_pend) + abs(pRegistro.Imp_Pend_Perc);
 ELSE
 ln_importe_cancelado := abs(Pregcuentaacancelar.Imp_Pago_Pend);
 END IF;

 IF abs(pRegistro.Imp_Pend_Afec_Perc) + abs(pRegistro.Imp_Pend_Perc) <= ln_importe_cancelado THEN
 ln_importe_cancelado_a_per := abs(pRegistro.Imp_Pend_Afec_Perc);
 ln_importe_cancelado_per := round(((ln_importe_cancelado_a_per + abs(pRegistro.Imp_Pend_Perc)) * pRegistro.Fac_Calc_Perc), 2);
 ELSE
 ln_importe_cancelado_per := round((Pregcuentaacancelar.Imp_Pago_Pend * pRegistro.Fac_Calc_Perc), 2);
 ln_importe_cancelado_a_per := ln_importe_cancelado - ln_importe_cancelado_per;
 END IF;
 ln_importe_cancelado_i_per := ln_importe_cancelado - (ln_importe_cancelado_a_per + ln_importe_cancelado_per);
 IF ln_importe_cancelado_i_per < 0 THEN
 ln_importe_cancelado_i_per := 0;
 END IF;

 ELSE -- En caso sea NO
 ln_importe_cancelado := 0;
 ln_importe_cancelado_i_per := 0;
 ln_importe_cancelado_a_per := 0;
 ln_importe_cancelado_per := 0;
 END IF;

 /* Actualizando montos Movimientos Bancarios */
 UPDATE per_movim_banca_detal a
 SET
 a.Imp_Pago_Apli = a.imp_pago_apli + ln_importe_cancelado ,
 a.imp_pago_pend = a.imp_pago_pend - ln_importe_cancelado ,
 a.imp_perc = a.imp_perc + ln_importe_cancelado_per,
 a.USU_MODI = USER,
 a.FEC_MODI = SYSDATE
 WHERE
 a.pais_cod_pais = pregCuentaACancelar.pais_cod_pais AND
 a.tior_tipo_orig_dato = pregCuentaACancelar.Tior_Tipo_Orig_Dato AND
 a.moca_num_lote_inte = pregCuentaACancelar.Moca_Num_Lote_Inte AND
 a.con_tran = pregCuentaACancelar.Con_Tran;

 /* Actualizando montos de la Cta de Cancelacion */
 ln_couta_pagado := Pregistro.Imp_Cuot_Paga + ln_importe_cancelado - ln_importe_cancelado_per;
 ln_couta_pendiente := Pregistro.Imp_Cuot_Pend - ln_importe_cancelado + ln_importe_cancelado_per;
 ln_pagado_i_per := Pregistro.Imp_Paga_Inaf_Perc + ln_importe_cancelado_i_per;
 ln_pagado_a_per := Pregistro.Imp_Paga_Afec_Perc + ln_importe_cancelado_a_per;
 ln_pagado_per := Pregistro.Imp_Paga_Perc + ln_importe_cancelado_per;
 IF (pn_val_indi_actu = 1 AND abs(Pregistro.Imp_Cuot) = abs(ln_couta_pagado)) OR
 (pn_val_indi_actu = 0) THEN
 ls_marca_Salida := ps_codMarcaSalida;
 ELSE
 ls_marca_Salida := pregistro.cod_marc_situ;
 END IF;
 /* IF ls_marca_salida IS NOT NULL THEN
 ls_marca_Salida := TRIM(ls_marca_Salida);
 END IF; */


 UPDATE per_cuent_corri_docle a
 SET
 a.IMP_CUOT_PAGA = ln_couta_pagado,
 a.IMP_CUOT_PEND = ln_couta_pendiente,

 a.IMP_PAGA_INAF_PERC = ln_pagado_i_per,
 a.IMP_PEND_INAF_PERC = A.IMP_INAF_PERC - ln_pagado_i_per ,

 a.IMP_PAGA_AFEC_PERC = ln_pagado_a_per,
 a.IMP_PEND_AFEC_PERC = A.IMP_AFEC_PERC - ln_pagado_a_per ,

 a.IMP_PAGA_PERC = ln_pagado_per,
 a.IMP_PEND_PERC = A.IMP_PERC_PERC - ln_pagado_per,

 a.COD_MARC_SITU = ls_marca_Salida,
 a.USU_MODI = USER,
 a.FEC_MODI = SYSDATE
 WHERE
 a.pais_cod_pais = pregistro.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregistro.cor_cuen_corr_dole;

 /* Obteniendo Registro de la Cuota de Cancelacion */
 SELECT *
 INTO pregistro
 FROM PER_CUENT_CORRI_DOCLE A
 WHERE
 a.pais_cod_pais = pregistro.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregistro.cor_cuen_corr_dole;

 /* Seteando valores para insertar en Registro de Pagos */
 IF ln_importe_cancelado <> 0 THEN
 Registropago.Pais_Cod_Pais := pRegistro.Pais_Cod_Pais;
 Registropago.Cod_Soci := pRegistro.Cod_Soci;
 Registropago.Cod_Cana := pRegistro.Cod_Cana;
 Registropago.Cod_Acce := pRegistro.Cod_Acce;
 Registropago.Cod_Sbac := pRegistro.Cod_Sbac;
 Registropago.Num_Soco := pRegistro.Num_Soli_Cons;
 Registropago.Tip_Dole := pRegistro.Tip_Docu_Lega;
 Registropago.EJE_DINT := pRegistro.Eje_Docu_Inte;
 Registropago.NUM_DINT := pRegistro.Num_Docu_Inte;
 Registropago.Num_Cuot := pRegistro.Num_Orde_Cuot;
 Registropago.Sec_Abon := PER_FN_SGTE_SEC_ABON(RegistroPago);
 Registropago.FEC_DOLE := pRegistro.Fec_Emis_Docu;
 Registropago.TIP_ABON := ps_tipo_Cargo_Abon;
 Registropago.Num_Abon := NULL;
 Registropago.Cod_Cons := pRegistro.Cod_Clie;
 IF (Registropago.Tip_Dole = '001' OR
 Registropago.Tip_Dole = '002' OR
 Registropago.Tip_Dole = '003' OR
 Registropago.Tip_Dole = '021' OR
 Registropago.Tip_Dole = '022') THEN
 Registropago.Tip_Doid := pRegistro.Tip_Docu_Iden_Fisc;
 Registropago.Num_Doid := pRegistro.Num_Iden_Fisc;
 ELSE
 Registropago.Tip_Doid := pRegistro.Tip_Docu_Iden_Nnal;
 Registropago.Num_Doid := pRegistro.Num_Iden_Nnal;
 END IF;
 Registropago.Cod_Peri := pRegistro.cod_peri; --ps_cod_peri;
 Registropago.Mon_Pago := abs(ln_importe_cancelado_a_per + ln_importe_cancelado_per);
 Registropago.Mon_Perc := abs(ln_importe_cancelado_per);
 Registropago.Mon_Todl := pRegistro.Imp_Tota_Docu;
 Registropago.Por_Perc := pRegistro.Por_Perc;
 Registropago.Fac_Cape := pRegistro.Fac_Calc_Perc;
 Registropago.Fec_Pago := pregCuentaACancelar.Fec_Pago; --GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 Registropago.Fec_Proc := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA(); --pRegistro.Fec_Fact;
 IF (Registropago.Mon_Perc > 0) THEN
 Registropago.IND_REPE := 'S';
 ELSE
 Registropago.IND_REPE := 'N';
 END IF;
 Registropago.Sta_Repe := '2';
 Registropago.FEC_DIGI := SYSDATE;
 Registropago.Usu_Digi := USER;
 Registropago.Est_Repa := '1';
 Registropago.Cor_Repa := PER_FN_sgte_corre_reg_pago();

 /* Insertando registro en Registro de Pagos */
 INSERT_PER_REGIS_PAGOS(Registropago);
 END IF;

 /* Seteando valores para insertar en Solicitudes Monetarias */
 IF ln_importe_cancelado_per <> 0 AND pn_val_indi_actu = 1 THEN
 registroSoliMone.Pais_Cod_Pais := Pregistro.Pais_Cod_Pais;
 Registrosolimone.Tior_Tipo_Orig_Dato := pstipoorigen;
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := pRegistro.cod_peri; --ps_cod_peri;
 registroSoliMone.Cod_Clie := Pregistro.Cod_Clie;
 registroSoliMone.Fec_Fact := pRegistro.Fec_Fact; --GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := ln_importe_cancelado_per;
 IF Pregistro.Cod_Proc_Crea = 'CCC001' AND Pregistro.Cod_Subp_Crea = 1 THEN
 registroSoliMone.Cod_Cana_Refe := Pregistro.Cod_Cana_Perc;
 registroSoliMone.Cod_Acce_Refe := Pregistro.Cod_Acce_Perc;
 registroSoliMone.Cod_Sbac_Refe := Pregistro.Cod_Suba_Perc;
 registroSoliMone.Num_Docu_Refe := Pregistro.Num_Soli_Cons_Perc;
 ELSE
 registroSoliMone.Cod_Cana_Refe := NULL;
 registroSoliMone.Cod_Acce_Refe := NULL;
 registroSoliMone.Cod_Sbac_Refe := NULL;
 registroSoliMone.Num_Docu_Refe := NULL;
 END IF;
 registroSoliMone.Cod_Tipo_Soli := psTipoSoliPerc;
 registroSoliMone.Cod_Acce := 'PR';
 registroSoliMone.Cod_Sbac := '290';
 registroSoliMone.Tip_Posi := 'PR';
 registroSoliMone.Val_Cant := -1;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 Registrosolimone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_Soli_Mone := PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := pregistro.Cor_Cuen_Corr_Dole;

 /* Insertando registro en Solicitudes Monetarias */
 INSERT_PER_SOLI_MONE(RegistroSoliMone);
 END IF;


 /* Seteando valores para insertar en Solicitudes Monetarias
 cuyo importe cancelado <> 0 */
 IF ln_importe_cancelado <> 0 THEN
 /* Insertando registro1 en Solicitudes Monetarias */
 registroSoliMone.Pais_Cod_Pais := pregCuentaACancelar.Pais_Cod_Pais;
 registroSoliMone.Tior_Tipo_Orig_Dato := pstipoorigen;
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := pRegistro.cod_peri; --ps_cod_peri;
 registroSoliMone.Cod_Clie := pregCuentaACancelar.Cod_Cons;
 registroSoliMone.Fec_Fact := pRegistro.Fec_Fact; --GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := ln_importe_cancelado ;
 registroSoliMone.Cod_Cana_Refe := NULL;
 registroSoliMone.Cod_Acce_Refe := NULL;
 registroSoliMone.Cod_Sbac_Refe := NULL;
 registroSoliMone.Num_Docu_Refe := NULL;
 registroSoliMone.Cod_Tipo_Soli := Pstiposoliexcesoposi;
 registroSoliMone.Cod_Acce := 'GZ';
 registroSoliMone.Cod_Sbac := '000';
 registroSoliMone.Tip_Posi := 'PE';
 registroSoliMone.Val_Cant := 1;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 Registrosolimone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_Soli_Mone := PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := pRegistro.Cor_Cuen_Corr_Dole;
 INSERT_PER_SOLI_MONE(RegistroSoliMone);

 /* Insertando registro2 en Solicitudes Monetarias */
 registroSoliMone.Pais_Cod_Pais := pregCuentaACancelar.Pais_Cod_Pais;
 registroSoliMone.Tior_Tipo_Orig_Dato := pstipoorigen;
 registroSoliMone.Num_Lote := Psnumerolote;

 registroSoliMone.Cod_Peri := pRegistro.cod_peri; --ps_cod_peri;
 registroSoliMone.Cod_Clie := pregCuentaACancelar.Cod_Cons;
 registroSoliMone.Fec_Fact := pRegistro.Fec_Fact; --GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := ln_importe_cancelado - ln_importe_cancelado_per;
 registroSoliMone.Cod_Cana_Refe := pregistro.Cod_Cana;
 registroSoliMone.Cod_Acce_Refe := pregistro.Cod_Acce;
 registroSoliMone.Cod_Sbac_Refe := pregistro.Cod_Sbac;
 registroSoliMone.Num_Docu_Refe := pregistro.Num_Soli_Cons;
 registroSoliMone.Cod_Tipo_Soli := Pstiposoliaplipago;
 registroSoliMone.Cod_Acce := 'GZ';
 registroSoliMone.Cod_Sbac := '000';
 registroSoliMone.Tip_Posi := 'PE';
 registroSoliMone.Val_Cant := -1;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 Registrosolimone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_Soli_Mone := PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := pregistro.Cor_Cuen_Corr_Dole;
 INSERT_PER_SOLI_MONE(RegistroSoliMone);
 END IF;



EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_ACTU_IMPORTE_PAGO_EXCESO: '||ls_sqlerrm);
END PER_PR_ACTU_IMPORTE_PAGO_EXCE;


/**************************************************************************
Descripcion : Procedimiento auxiliar del procedure PER_PR_CANCE_AUTOM_DEUDA que
 actualiza los montos de la Couta a Cancelar
Fecha Creacion : 19/09/2006
Parametros Entrada :
 pn_val_indi_actu : Indicador de Actualiza Couta con importe pagado
 ps_codMarcaSalida : Marca de Salida
 ps_tipo_Cargo_Abon: Tipo de Cargo Abono
 psTipoOrigen : Tipo de Origen de datos
 psNumeroLote : Numero de Lote
 pRegistro : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta de Cancelacion
 pregCuentaACancelar : Registro Rowtype de la tabla PER_CUENT_CORRI_DOCLE
 correspondiente a la Couta a Cancelar
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_ACTU_IMPORTE_RECUPERADA
( pn_val_indi_actu NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 ps_tipo_soli VARCHAR2,
 ps_ind_recauda VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 psCodPeri VARCHAR2,
 pnImporteMovim IN OUT NUMBER,
 psCodProc VARCHAR2,
 psCodSubp VARCHAR2,
 pregCuentaACancelar per_cuent_corri_docle%ROWTYPE)
IS
 ln_importe_cancelado_per NUMBER;
 ln_importe_cancelado NUMBER;
 ln_importe_cancelado_i_per NUMBER;
 ln_importe_cancelado_a_per NUMBER;
 ln_imp_couta NUMBER;
 ln_imp_cout_paga NUMBER;
 ln_imp_paga_i_per NUMBER;
 ln_imp_paga_a_per NUMBER;
 ln_imp_paga_per NUMBER;
 ln_imp_i_per NUMBER;
 ln_imp_a_per NUMBER;
 ln_imp_perc_per NUMBER;

 ln_couta NUMBER;
 ln_couta_pagado NUMBER;
 ln_couta_pendiente NUMBER;
 ln_pagado_i_per NUMBER;
 ln_pagado_a_per NUMBER;
 ln_pagado_per NUMBER;
 ln_i_per NUMBER;
 ln_a_per NUMBER;
 ln_perc_per NUMBER;
 ls_marca_Salida ccc_marca_situa.cod_marc_situ%TYPE;
 registroPago per_regis_pagos%ROWTYPE;
 registroSoliMone per_solic_monet%ROWTYPE;

BEGIN
 /* Inicializando valores */
 IF pn_val_indi_actu = 1 THEN --En caso sea SI
 IF ABS(pnImporteMovim) < abs(Pregcuentaacancelar.Imp_Cuot_Pend) THEN
 ln_importe_cancelado := abs(pnImporteMovim);
 ELSE
 ln_importe_cancelado := abs(Pregcuentaacancelar.Imp_Cuot_Pend);
 END IF;
 IF ln_importe_cancelado <= abs(Pregcuentaacancelar.Imp_Pend_Afec_Perc) THEN
 ln_importe_cancelado_a_per := ln_importe_cancelado;
 ln_importe_cancelado_i_per := 0;
 ELSE
 ln_importe_cancelado_a_per := abs(Pregcuentaacancelar.Imp_Pend_Afec_Perc);
 IF (ln_importe_cancelado - ln_importe_cancelado_a_per) >= abs(Pregcuentaacancelar.Imp_Pend_Inaf_Perc) THEN
 ln_importe_cancelado_i_per := abs(Pregcuentaacancelar.Imp_Pend_Inaf_Perc);
 ELSE
 ln_importe_cancelado_i_per := ln_importe_cancelado - ln_importe_cancelado_a_per ;
 END IF;
 END IF;
 ln_importe_cancelado_per := round((ln_importe_cancelado_a_per * Pregcuentaacancelar.Por_Perc) / 100, 2);

 ELSE -- En caso sea NO

 ln_importe_cancelado := 0;
 ln_importe_cancelado_i_per := 0;
 ln_importe_cancelado_a_per := 0;
 ln_importe_cancelado_per := 0;
 END IF;

 /* Actualizando montos Ctas a Cancelar */
 ln_couta_pagado := Pregcuentaacancelar.Imp_Cuot_Paga + ln_importe_cancelado;
 ln_couta_pendiente := Pregcuentaacancelar.Imp_Cuot_Pend - ln_importe_cancelado;
 ln_pagado_i_per := ln_importe_cancelado_i_per;
 ln_pagado_a_per := ln_importe_cancelado_a_per;
 ln_pagado_per := ln_importe_cancelado_per;
 IF (pn_val_indi_actu = 1 AND Pregcuentaacancelar.Imp_Cuot = ln_couta_pagado) OR
 (pn_val_indi_actu = 0) THEN
 ls_marca_Salida := ps_codMarcaSalida;
 ELSE
 ls_marca_Salida := pregcuentaacancelar.cod_marc_situ;
 END IF;
 IF pn_val_indi_actu = 0 THEN
 IF ABS(pnImporteMovim) > abs(Pregcuentaacancelar.Imp_Cuot_Pend) THEN
 ls_marca_Salida := 'DE';
 END IF;
 END IF;

 UPDATE per_cuent_corri_docle a
 SET
 a.IMP_CUOT_PAGA = ln_couta_pagado,
 a.IMP_CUOT_PEND = ln_couta_pendiente,
 a.IMP_PAGA_INAF_PERC = a.imp_paga_inaf_perc + ln_pagado_i_per,
 a.IMP_PEND_INAF_PERC = a.Imp_Pend_Inaf_Perc - ln_pagado_i_per,
 a.IMP_PAGA_AFEC_PERC = a.imp_paga_afec_perc + ln_pagado_a_per,
 a.IMP_PEND_AFEC_PERC = a.Imp_Pend_Afec_Perc - ln_pagado_a_per,
 a.IMP_PAGA_PERC = a.imp_paga_perc + ln_pagado_per,
 a.IMP_PEND_PERC = a.Imp_Pend_Perc - ln_pagado_per,
 a.COD_MARC_SITU = trim(ls_marca_Salida),
 a.USU_MODI = USER,
 a.FEC_MODI = SYSDATE
 WHERE
 a.pais_cod_pais = pregCuentaACancelar.pais_cod_pais AND
 a.cor_cuen_corr_dole = pregCuentaACancelar.cor_cuen_corr_dole;

 /* Actualizando montos de la Cta de Cancelacion */
 IF pnImporteMovim < 0 THEN
 pnImporteMovim := pnImporteMovim + ln_importe_cancelado;
 ELSE
 pnImporteMovim := pnImporteMovim - ln_importe_cancelado;
 END IF;

 /* Seteando valores para insertar en Registro de Pagos */
 IF ln_importe_cancelado <> 0 THEN
 Registropago.Pais_Cod_Pais := pregCuentaACancelar.Pais_Cod_Pais;
 Registropago.Cod_Soci := pregCuentaACancelar.Cod_Soci;
 Registropago.Cod_Cana := pregCuentaACancelar.Cod_Cana;
 Registropago.Cod_Acce := pregCuentaACancelar.Cod_Acce;
 Registropago.Cod_Sbac := pregCuentaACancelar.Cod_Sbac;
 Registropago.Num_Soco := pregCuentaACancelar.Num_Soli_Cons;
 Registropago.Tip_Dole := pregCuentaACancelar.Tip_Docu_Lega;
 Registropago.EJE_DINT := pregCuentaACancelar.Eje_Docu_Inte;
 Registropago.NUM_DINT := pregCuentaACancelar.Num_Docu_Inte;
 Registropago.Num_Cuot := pregCuentaACancelar.Num_Orde_Cuot;
 Registropago.Sec_Abon := PER_FN_SGTE_SEC_ABON(RegistroPago);
 Registropago.FEC_DOLE := pregCuentaACancelar.Fec_Emis_Docu;
 Registropago.TIP_ABON := ps_tipo_Cargo_Abon;
 Registropago.Num_Abon := NULL;
 Registropago.Cod_Cons := pregCuentaACancelar.Cod_Clie;
 IF (Registropago.Tip_Dole = '001' OR
 Registropago.Tip_Dole = '002' OR
 Registropago.Tip_Dole = '003' OR
 Registropago.Tip_Dole = '021' OR
 Registropago.Tip_Dole = '022') THEN
 Registropago.Tip_Doid := pregCuentaACancelar.Tip_Docu_Iden_Fisc;
 Registropago.Num_Doid := pregCuentaACancelar.Num_Iden_Fisc;
 ELSE
 Registropago.Tip_Doid := pregCuentaACancelar.Tip_Docu_Iden_Nnal;
 Registropago.Num_Doid := pregCuentaACancelar.Num_Iden_Nnal;
 END IF;
 Registropago.Cod_Peri := psCodPeri;
 Registropago.Mon_Pago := abs(ln_importe_cancelado_a_per + ln_importe_cancelado_per);
 Registropago.Mon_Perc := abs(ln_importe_cancelado_per);
 Registropago.Mon_Todl := pregCuentaACancelar.Imp_Tota_Docu;
 Registropago.Por_Perc := pregCuentaACancelar.Por_Perc;
 Registropago.Fac_Cape := pregCuentaACancelar.Fac_Calc_Perc;
 Registropago.Fec_Pago := SYSDATE;
 Registropago.Fec_Proc := SYSDATE;
 IF (Registropago.Mon_Perc = 0) THEN
 Registropago.IND_REPE := 'N';
 ELSE
 Registropago.IND_REPE := ps_ind_recauda;
 END IF;
 Registropago.Sta_Repe := '2';
 Registropago.FEC_DIGI := SYSDATE;
 Registropago.Usu_Digi := USER;
 Registropago.Est_Repa := '1';
 Registropago.Cor_Repa := PER_FN_sgte_corre_reg_pago();

 /* Insertando registro en Registro de Pagos */
 INSERT_PER_REGIS_PAGOS(Registropago);
 END IF;

 /* Seteando valores para insertar en Solicitudes Monetarias */
 IF (ln_importe_cancelado_per <> 0) AND
 (pn_val_indi_actu = 1) AND
 ((psCodProc = 'CCC003' AND psCodSubp = 1) OR (psCodProc = 'CCCS03' AND psCodSubp = 1)) THEN
 registroSoliMone.Pais_Cod_Pais := pregCuentaACancelar.Pais_Cod_Pais;
 registroSoliMone.Tior_Tipo_Orig_Dato := Pstipoorigen;
 registroSoliMone.Num_Lote := PsNumeroLote;
 registroSoliMone.Cod_Peri := pregCuentaACancelar.Cod_Peri;
 registroSoliMone.Cod_Clie := pregCuentaACancelar.Cod_Clie;
 registroSoliMone.Fec_Fact := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := ln_importe_cancelado_per;
 registroSoliMone.Cod_Cana_Refe := pregCuentaACancelar.Cod_Cana_Perc;
 registroSoliMone.Cod_Acce_Refe := pregCuentaACancelar.Cod_Acce_Perc;
 registroSoliMone.Cod_Sbac_Refe := pregCuentaACancelar.Cod_Suba_Perc;
 registroSoliMone.Num_Docu_Refe := pregCuentaACancelar.Num_Soli_Cons_Perc;
 registroSoliMone.Cod_Tipo_Soli := ps_tipo_soli;
 registroSoliMone.Cod_Acce := 'PR';
 registroSoliMone.Cod_Sbac := '290';
 registroSoliMone.Tip_Posi := 'PR';
 registroSoliMone.Val_Cant := -1;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 Registrosolimone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_Soli_Mone := PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := pregCuentaACancelar.Cor_Cuen_Corr_Dole;

 /* Insertando registro en Solicitudes Monetarias */
 INSERT_PER_SOLI_MONE(RegistroSoliMone);
 END IF;


EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_ACTU_IMPORTE_RECUPERADA: '||ls_sqlerrm);
END PER_PR_ACTU_IMPORTE_RECUPERADA;



PROCEDURE PER_PR_CABEC_DETAL_DOLSO (p_codigoPais IN VARCHAR2,
 p_usuDigi IN VARCHAR2,
 p_tipOrigenDatos IN VARCHAR2,
 p_codigoInterfaz IN VARCHAR2,
 p_numeroLote OUT VARCHAR2) IS

/************************************************************************************************
Descripcion : Generacion de Documentos Contables Cabecera o Solicitudes Cabecera
Fecha Creacion : 15/08/2006
Fecha Modificacion : 07/09/2006
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
 p_usuDigi : Usuario Logueado
Parametros Salida :
Autor : David Toledo - dtoledo@belcorp.biz
Modificado por : Jose Martinez - jamartinez@belcorp.biz / Carlos Bazalar cbazalar@belcorp.biz
Version : Beta (Beta|Final)
Cambios Importantes : Ninguno
*************************************************************************************************/
 l_oidSoliCabe NUMBER;
 l_count NUMBER;
 l_numeroLote bas_histo_lotes.num_lote%TYPE;

 TYPE DocLegalRec IS RECORD
 (oidSoliCabe PER_CABEC_DETAL_DOLSO_TEMPO.OID_SOLI_CABE%TYPE
 );

 TYPE DocLegalRecTab IS TABLE OF DocLegalRec;


 DocLegal_Recs                   DocLegalRecTab;
 ln_id                           NUMBER;
 W_FILAS                         NUMBER:=5000;
 LN_CONTADOR                     NUMBER;
 lv_cod_soci                     seg_socie.cod_soci%TYPE;
 lv_ind_gene_perc_fact           ccc_param_gener.val_para%TYPE;
 lv_tab_oid_clie                 gt_tab_oid_clie;
 lv_cant_perc_gene               NUMBER(12);

 /* Creacion de Cursor con los datos de la tabla PER_CABEC_DETAL_DOLSO_TEMPO */
 CURSOR DOC_LEGAL_TEMPORAL IS
 SELECT DISTINCT TEMPORAL.SOCA_OID_SOLI_CABE
 FROM PER_CABEC_DETAL_DOLSO_TEMPO TEMPORAL,
 FAC_DOCUM_CONTA_CABEC
 WHERE (TEMPORAL.OID_PAIS = FAC_DOCUM_CONTA_CABEC.PAIS_OID_PAIS )
 AND (TEMPORAL.OID_SOCI = FAC_DOCUM_CONTA_CABEC.SOCI_OID_SOCI )
 AND (TEMPORAL.OID_SOLI_CABE = FAC_DOCUM_CONTA_CABEC.SOCA_OID_SOLI_CABE)
 AND FAC_DOCUM_CONTA_CABEC.Ictp_Oid_Tipo_Prog IS NULL
 AND FAC_DOCUM_CONTA_CABEC.Val_Tota_Paga_Loca <> 0;

 CURSOR DOC_LEGAL_TEMPORAL01 IS
 SELECT DISTINCT SOCA_OID_SOLI_CABE
 FROM PER_CABEC_DETAL_DOLSO_TEMPO TEMPORAL
 WHERE
 NOT EXISTS (
 SELECT oid_cabe
 FROM
 FAC_DOCUM_CONTA_CABEC
 WHERE (FAC_DOCUM_CONTA_CABEC.PAIS_OID_PAIS = TEMPORAL.OID_PAIS)
 AND (FAC_DOCUM_CONTA_CABEC.SOCI_OID_SOCI = TEMPORAL.OID_SOCI)
 AND (FAC_DOCUM_CONTA_CABEC.SOCA_OID_SOLI_CABE = TEMPORAL.OID_SOLI_CABE)) ;

 CURSOR c_perc_gene(
  p_num_lote      per_solic_monet.num_lote%TYPE)
 IS
 SELECT mc.oid_clie
 FROM
     per_solic_monet  psm,
     mae_clien mc
 WHERE psm.pais_cod_pais = p_codigoPais
   AND psm.cod_clie = mc.cod_clie
   AND psm.tior_tipo_orig_dato = p_tipOrigenDatos
   AND psm.num_lote = p_num_lote;

 TYPE DocInserta IS RECORD (
 oidpais PER_CABEC_DETAL_DOLSO_TEMPO.OID_PAIS%TYPE,
 codpais PER_CABEC_DETAL_DOLSO_TEMPO.COD_PAIS%TYPE,
 soli_posi PER_CABEC_DETAL_DOLSO_TEMPO.TIP_SOLI_IMPO_POSI%TYPE,
 soli_nega PER_CABEC_DETAL_DOLSO_TEMPO.TIP_SOLI_IMPO_NEGA%TYPE,
 impoNega PER_CABEC_DETAL_DOLSO_TEMPO.IND_RECA_IMPO_NEGA%TYPE,
 cargAbon PER_CABEC_DETAL_DOLSO_TEMPO.COD_TIPO_CARG_ABON%TYPE,
 marcaSitu PER_CABEC_DETAL_DOLSO_TEMPO.COD_MARC_SITU%TYPE,
 soliCabe PER_CABEC_DETAL_DOLSO_TEMPO.SOCA_OID_SOLI_CABE%TYPE,
 oidproc PER_CABEC_DETAL_DOLSO_TEMPO.OID_PROC_CREA%TYPE,
 codProc PER_CABEC_DETAL_DOLSO_TEMPO.COD_PROC_CREA%TYPE,
 oidsubpr PER_CABEC_DETAL_DOLSO_TEMPO.OID_SUBP_CREA%TYPE,
 codsubpr PER_CABEC_DETAL_DOLSO_TEMPO.COD_SUBP_CREA%TYPE,
 oidSoliCabe PER_CABEC_DETAL_DOLSO_TEMPO.OID_SOLI_CABE%TYPE,
 oidSoli PER_CABEC_DETAL_DOLSO_TEMPO.OID_SOCI%TYPE,
 codSoli PER_CABEC_DETAL_DOLSO_TEMPO.COD_SOCI%TYPE,
 oidCana PER_CABEC_DETAL_DOLSO_TEMPO.OID_CANA%TYPE,
 codCana PER_CABEC_DETAL_DOLSO_TEMPO.COD_CANA%TYPE,
 codAcce PER_CABEC_DETAL_DOLSO_TEMPO.COD_ACCE%TYPE,
 codSbac PER_CABEC_DETAL_DOLSO_TEMPO.COD_SBAC%TYPE,
 valNumeSoli PER_CABEC_DETAL_DOLSO_TEMPO.VAL_NUME_SOLI%TYPE,
 oidMarca PER_CABEC_DETAL_DOLSO_TEMPO.OID_MARC%TYPE,
 codMarca PER_CABEC_DETAL_DOLSO_TEMPO.COD_MARC%TYPE,
 oidPeri PER_CABEC_DETAL_DOLSO_TEMPO.OID_PERI%TYPE,
 codPeri PER_CABEC_DETAL_DOLSO_TEMPO.COD_PERI%TYPE,
 codClie PER_CABEC_DETAL_DOLSO_TEMPO.COD_CLIE%TYPE,
 tipoDocuFisc PER_CABEC_DETAL_DOLSO_TEMPO.COD_TIPO_DOCU_FISC%TYPE,
 tipoDocuNnal PER_CABEC_DETAL_DOLSO_TEMPO.COD_TIPO_DOCU_NNAL%TYPE,
 fecFact PER_CABEC_DETAL_DOLSO_TEMPO.FEC_FACT%TYPE,
 oidZona PER_CABEC_DETAL_DOLSO_TEMPO.OID_ZONA%TYPE,
 oidFormPago FAC_DOCUM_CONTA_CABEC.FOPA_OID_FORM_PAGO%TYPE,
 totalPagaLoca FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_LOCA%TYPE,
 oidDocuRefe PER_CABEC_DETAL_DOLSO_TEMPO.SOCA_OID_DOCU_REFE%TYPE,
 codCanaRefe PER_CABEC_DETAL_DOLSO_TEMPO.COD_CANA_REFE%TYPE,
 codSbacRefe PER_CABEC_DETAL_DOLSO_TEMPO.COD_SBAC_REFE%TYPE,
 codAcceRefe PER_CABEC_DETAL_DOLSO_TEMPO.COD_ACCE_REFE%TYPE,
 valNumeSoliRefe PER_CABEC_DETAL_DOLSO_TEMPO.VAL_NUME_SOLI_REFE%TYPE,
 indAfec VARCHAR2(1),
 codTipoDocu FAC_TIPO_DOCUM.COD_TIPO_DOCU%TYPE,
 valEjerDocuInte FAC_DOCUM_CONTA_CABEC.VAL_EJER_DOCU_INTE%TYPE,
 numDocuCont FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE,
 numeIdenFisc FAC_DOCUM_CONTA_CABEC.VAL_NUME_IDEN_FISC%TYPE,
 numeIdenNnal FAC_DOCUM_CONTA_CABEC.VAL_NUME_IDEN_NNAL%TYPE,
 fecEmis FAC_DOCUM_CONTA_CABEC.FEC_EMIS%TYPE,
 fletTotaLoca FAC_DOCUM_CONTA_CABEC.IMP_FLET_TOTA_LOCA%TYPE,
 oidSoliPosi PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE,
 usuDigi VARCHAR2(20),
 fechaSistema DATE,
 usuaModi VARCHAR2(20),
 fechaModi DATE,
 indicador VARCHAR2(1)
 );

 TYPE DocInsertaTab IS TABLE OF Docinserta;
 DocInsertaReg DocInsertaTab;
 CURSOR DOC_LEGAL IS
 SELECT
 TEMPORAL.OID_PAIS,
 TEMPORAL.COD_PAIS,
 TEMPORAL.TIP_SOLI_IMPO_POSI,
 TEMPORAL.TIP_SOLI_IMPO_NEGA, TEMPORAL.IND_RECA_IMPO_NEGA,
 TEMPORAL.COD_TIPO_CARG_ABON, TEMPORAL.COD_MARC_SITU,
 TEMPORAL.SOCA_OID_SOLI_CABE, TEMPORAL.OID_PROC_CREA, TEMPORAL.COD_PROC_CREA,
 TEMPORAL.OID_SUBP_CREA, TEMPORAL.COD_SUBP_CREA,
 TEMPORAL.OID_SOLI_CABE, TEMPORAL.OID_SOCI, TEMPORAL.COD_SOCI,
 TEMPORAL.OID_CANA, TEMPORAL.COD_CANA, TEMPORAL.COD_ACCE,
 TEMPORAL.COD_SBAC, TEMPORAL.VAL_NUME_SOLI, TEMPORAL.OID_MARC,
 TEMPORAL.COD_MARC, TEMPORAL.OID_PERI, TEMPORAL.COD_PERI, TEMPORAL.COD_CLIE,
 TEMPORAL.COD_TIPO_DOCU_FISC, TEMPORAL.COD_TIPO_DOCU_NNAL, TEMPORAL.FEC_FACT,
 TEMPORAL.OID_ZONA, FAC_DOCUM_CONTA_CABEC.FOPA_OID_FORM_PAGO,
 FAC_DOCUM_CONTA_CABEC.VAL_TOTA_PAGA_LOCA, TEMPORAL.SOCA_OID_DOCU_REFE,
 TEMPORAL.COD_CANA_REFE, TEMPORAL.COD_SBAC_REFE, TEMPORAL.COD_ACCE_REFE,
 TEMPORAL.VAL_NUME_SOLI_REFE,
 case 
   when (SELECT count(*) 
                     FROM mae_clien_datos_adici adi, mae_clien mc
                     WHERE adi.clie_oid_clie = mc.oid_clie
                     AND adi.ind_gene_perc = 'N'
                     AND mc.cod_clie = TEMPORAL.Cod_Clie) > 0  THEN 
          'N'
    ELSE
      'S'
 END IND_AFEC, 
 FAC_TIPO_DOCUM.COD_TIPO_DOCU, FAC_DOCUM_CONTA_CABEC.VAL_EJER_DOCU_INTE,
 FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE, FAC_DOCUM_CONTA_CABEC.VAL_NUME_IDEN_FISC,
 FAC_DOCUM_CONTA_CABEC.VAL_NUME_IDEN_NNAL, FAC_DOCUM_CONTA_CABEC.FEC_EMIS,
 FAC_DOCUM_CONTA_CABEC.IMP_FLET_TOTA_LOCA, PED_SOLIC_POSIC.OID_SOLI_POSI,
 p_usuDigi, SYSDATE,NULL, NULL, '1'
 FROM PER_CABEC_DETAL_DOLSO_TEMPO TEMPORAL,
 FAC_DOCUM_CONTA_CABEC,
 FAC_DOCUM_CONTA_LINEA,
 PED_SOLIC_POSIC,
 FAC_TIPO_DOCUM
 WHERE ( (TEMPORAL.OID_PAIS = FAC_DOCUM_CONTA_CABEC.PAIS_OID_PAIS)
 AND (TEMPORAL.OID_SOCI = FAC_DOCUM_CONTA_CABEC.SOCI_OID_SOCI)
 AND (TEMPORAL.SOCA_OID_SOLI_CABE = FAC_DOCUM_CONTA_CABEC.SOCA_OID_SOLI_CABE)
 AND (FAC_DOCUM_CONTA_CABEC.OID_CABE = FAC_DOCUM_CONTA_LINEA.DCCA_OID_CABE)
 AND (FAC_DOCUM_CONTA_LINEA.SOPO_OID_SOLI_POSI = PED_SOLIC_POSIC.OID_SOLI_POSI)
 AND (FAC_TIPO_DOCUM.OID_TIPO_DOCU = FAC_DOCUM_CONTA_CABEC.TIDO_OID_TIPO_DOCU)
 AND FAC_DOCUM_CONTA_CABEC.Ictp_Oid_Tipo_Prog IS NULL
 AND FAC_DOCUM_CONTA_CABEC.Val_Tota_Paga_Loca <> 0 );

 ln_ultimoRegistro NUMBER:=0;
 ln_ultimoRegistroCargo NUMBER:=0;
BEGIN
 --- Eliminamos todos los registros de la tabla PER_CABEC_DETAL_DOLSO_TEMPO ---
 EXECUTE IMMEDIATE 'TRUNCATE TABLE PER_CABEC_DETAL_DOLSO_TEMPO' ;
 --gen_pkg_gener.gen_pr_EJEC_SQL_DINAM('TRUNCATE TABLE PER_CABEC_DETAL_DOLSO_TEMPO');

 --- Eliminamos todos los registros de la tabla PER_CABEC_DETAL_DOLSO ---
 EXECUTE IMMEDIATE 'TRUNCATE TABLE PER_CABEC_DETAL_DOLSO';
 -- gen_pkg_gener.gen_pr_EJEC_SQL_DINAM('TRUNCATE TABLE PER_CABEC_DETAL_DOLSO');

 --- Eliminamos todos los registros de la tabla PER_DESGL_FORMA_PAGO ---
 EXECUTE IMMEDIATE 'TRUNCATE TABLE PER_DESGL_FORMA_PAGO';
 --gen_pkg_gener.gen_pr_EJEC_SQL_DINAM('TRUNCATE TABLE PER_DESGL_FORMA_PAGO');

 --- Eliminamos todos los registros de la tabla PER_DETAL_CUOTA_FORMA_PAGO ---
 EXECUTE IMMEDIATE 'TRUNCATE TABLE PER_DETAL_CUOTA_FORMA_PAGO';
 --gen_pkg_gener.gen_pr_EJEC_SQL_DINAM('TRUNCATE TABLE PER_DETAL_CUOTA_FORMA_PAGO');

-- COMMIT;

 /* obteniendo nro de lote */
 --l_numeroLote := gen_pkg_gener.GEN_FN_DEVUELVE_NUM_LOTE_SGTE (p_codigoPais, SUBSTR(p_codigoInterfaz, 1, 3), p_codigoInterfaz);
 SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') ||
 LPAD(NVL(MAX(SUBSTR(H.NUM_LOTE, 9, 4)) + 1, 1), 4, '0')
 INTO l_numeroLote
 FROM PER_SOLIC_MONET H
 WHERE H.PAIS_COD_PAIS = p_codigoPais
 AND SUBSTR(H.NUM_LOTE, 1, 8) = TO_CHAR(SYSDATE, 'YYYYMMDD');

 p_numeroLote := l_numeroLote;

 -- Obtenemos los parametros de ultima ejecucion --
 SELECT OID_ULTI_REGI_PROC
 INTO ln_ultimoregistro
 FROM PER_CONTR_REGIS_PROGR
 WHERE PAIS_COD_PAIS = p_codigoPais
 AND COD_PROG = '01'
 AND EST_CTRL_REGI_PROG = '1';

 /* invocando a store procedure de Procesar Cargos y Abonos directos e incobrables */
 PER_PKG_PROCE_PERCE.PER_PR_PROCE_CARGO_ABONO_DIREC(p_codigoPais, p_tipOrigenDatos, l_numeroLote, ln_ultimoRegistroCargo);

-- COMMIT;

 /* Verificando si se actualiza ultimo Registro */
 IF ln_ultimoregistro IS NULL THEN
 ln_ultimoregistro := 0;
 END IF;
 IF ln_ultimoRegistroCargo IS NULL THEN
 ln_ultimoRegistroCargo := 0;
 END IF;

 /* Llamada a Store PER_PR_CABEC_DETAL_DOLSO_TEMPO */
 PER_PR_CABEC_DETAL_DOLSO_TEMPO (p_codigoPais, ln_ultimoregistro);



 IF ln_ultimoregistro IS NULL THEN
 ln_ultimoregistro := 0;
 END IF;
 IF (ln_ultimoRegistroCargo > ln_ultimoregistro) THEN
 ln_ultimoregistro := ln_ultimoRegistroCargo;
 END IF;

 --COMMIT;

 /* Actualizando Contador de Registros */
 IF ln_ultimoregistro > 0 THEN
 UPDATE PER_CONTR_REGIS_PROGR
 SET
 FEC_ULTI_EJEC = SYSDATE,
 OID_ULTI_REGI_PROC = ln_ultimoRegistro
 WHERE PAIS_COD_PAIS = p_codigoPais AND
 COD_PROG = '01' AND
 EST_CTRL_REGI_PROG = '1';
 END IF;

 /* Insertando en PER_CABEC_DETAL_DOLSO */
 OPEN DOC_LEGAL;
 LOOP
 FETCH DOC_LEGAL BULK COLLECT INTO DocInsertaReg LIMIT W_FILAS;
 IF DocInsertaReg.COUNT > 0 THEN
 FORALL ind IN DocInsertaReg.FIRST .. DocInsertaReg.LAST
 INSERT INTO PER_CABEC_DETAL_DOLSO
 VALUES DocInsertaReg(ind);
 END IF;
 EXIT WHEN DOC_LEGAL%NOTFOUND;
 END LOOP;
 CLOSE DOC_LEGAL;

 --COMMIT;

 OPEN DOC_LEGAL_TEMPORAL01;
 LOOP
 FETCH DOC_LEGAL_TEMPORAL01 BULK COLLECT INTO DocLegal_Recs LIMIT W_FILAS;
 -- Recorrido del cursor para obtener el oid_solic_cabe
 ln_id := 0;
 IF DocLegal_Recs.COUNT > 0 THEN
 FOR ind IN DocLegal_Recs.FIRST .. DocLegal_Recs.LAST LOOP
 l_oidSoliCabe := DocLegal_Recs(ind).oidSoliCabe;
 -- Si NO encontramos registro en Documentos Contables Cabecera
 INSERT INTO PER_CABEC_DETAL_DOLSO
 (COD_PAIS, OID_PAIS,
 TIP_SOLI_IMPO_POSI, TIP_SOLI_IMPO_NEGA, IND_RECA_IMPO_NEGA,
 COD_TIPO_CARG_ABON, COD_MARC_SITU,
 SOCA_OID_SOLI_CABE, OID_SUBP_CREA, COD_SUBP_CREA,
 OID_PROC_CREA, COD_PROC_CREA, OID_SOLI_CABE,
 OID_SOCI, COD_SOCI, OID_CANA, COD_CANA, COD_SBAC,
 COD_ACCE, VAL_NUME_SOLI, OID_MARC, COD_MARC,
 OID_PERI, COD_PERI, COD_CLIE,
 COD_TIPO_DOCU_FISC, COD_TIPO_DOCU_NNAL, FEC_FACT, OID_ZONA,
 FOPA_OID_FORM_PAGO, VAL_TOTA_PAGA_LOCA,
 SOCA_OID_DOCU_REFE, COD_CANA_REFE, COD_SBAC_REFE,
 COD_ACCE_REFE, VAL_NUME_SOLI_REFE,
 IND_AFEC, COD_TIPO_DOCU, FEC_EMIS_FACT, VAL_IMPO_FLET_TOTA_LOCA,
 OID_SOLI_POSI, USU_DIGI, FEC_DIGI, EST_DOCU_LEGA_SOLI
 )

 WITH TEMPO AS
 (
 SELECT T.COD_PAIS, T.OID_PAIS, T.TIP_SOLI_IMPO_POSI,
 T.TIP_SOLI_IMPO_NEGA, T.IND_RECA_IMPO_NEGA,
 T.COD_TIPO_CARG_ABON, T.COD_MARC_SITU,
 T.SOCA_OID_SOLI_CABE, T.OID_SUBP_CREA,
 T.COD_SUBP_CREA, T.OID_PROC_CREA, T.COD_PROC_CREA,
 T.OID_SOLI_CABE, T.OID_SOCI, T.COD_SOCI,
 T.OID_CANA, T.COD_CANA, T.COD_SBAC,
 T.COD_ACCE, T.VAL_NUME_SOLI, T.OID_MARC,
 T.COD_MARC, T.OID_PERI, T.COD_PERI, T.COD_CLIE,
 T.COD_TIPO_DOCU_FISC, T.COD_TIPO_DOCU_NNAL, T.FEC_FACT,
 T.OID_ZONA,
 S.FOPA_OID_FORM_PAGO, S.VAL_TOTA_PAGA_LOCA,
 T.SOCA_OID_DOCU_REFE, T.COD_CANA_REFE, T.COD_SBAC_REFE,
 T.COD_ACCE_REFE, T.VAL_NUME_SOLI_REFE,
 'N' IND_AFEC, D.COD_TIPO_DOCU, S.FEC_FACT FEC_EMIS_FACT,
 S.VAL_IMPO_FLET_TOTA_LOCA
 FROM PER_CABEC_DETAL_DOLSO_TEMPO T,
 PED_SOLIC_CABEC S,
 FAC_TIPO_DOCUM D
 WHERE ((T.SOCA_OID_SOLI_CABE = l_oidSoliCabe)
 AND (T.SOCA_OID_SOLI_CABE = S.OID_SOLI_CABE)
 AND (S.TIDO_OID_TIPO_DOCU = D.OID_TIPO_DOCU (+))
 )
 )
 SELECT TEMPO.*, PED_SOLIC_POSIC.OID_SOLI_POSI, p_usuDigi, SYSDATE,'1'
 FROM TEMPO,
 PED_SOLIC_CABEC,
 PED_SOLIC_POSIC
 WHERE TEMPO.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE
 AND PED_SOLIC_CABEC.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE;

 END LOOP;
 END IF;
 EXIT WHEN DOC_LEGAL_TEMPORAL01%NOTFOUND;

 END LOOP;
 CLOSE DOC_LEGAL_TEMPORAL01;

 --COMMIT;

 /* Llamada a Store PER_PR_CABEC_DETAL_DOLSO_TEMPO */
 PER_PR_DESGL_FORMA_PAGO (p_codigoPais, p_usuDigi);

 --COMMIT;

 /* Llamada a Store PER_PR_CUENT_CORRI_DOCLE */
 PER_PR_CUENT_CORRI_DOCLE(p_codigoPais, p_usuDigi, p_tipOrigenDatos, p_codigoInterfaz, p_numeroLote);

-- COMMIT;

 /* Actualizando correlativos de Solicitud Monetaria */
 PER_PR_CORRE_MONET_AGRUP_FACTU(p_codigoPais, p_numeroLote, p_tipOrigenDatos, '05');

 lv_ind_gene_perc_fact := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorGeneracionPercepcionFacturacion');

 IF lv_ind_gene_perc_fact = '1' THEN

  SELECT COUNT(1)
  INTO lv_cant_perc_gene
  FROM per_solic_monet psm
  WHERE psm.num_lote = p_numeroLote;

  IF lv_cant_perc_gene > 0 THEN
  lv_cod_soci := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('COD_SOCI');
  CCC_PKG_PROCE.CCC_PR_GENER_CUENT_CORRI_PERCE(p_codigoPais, lv_cod_soci , p_numeroLote);

   /* Cambiando el nro de lote */
   --l_numeroLote := gen_pkg_gener.GEN_FN_DEVUELVE_NUM_LOTE_SGTE (p_codigoPais, SUBSTR(p_codigoInterfaz, 1, 3), p_codigoInterfaz);

   SELECT TO_CHAR(SYSDATE, 'YYYYMMDD') ||
   LPAD(NVL(MAX(SUBSTR(H.NUM_LOTE, 9, 4)) + 1, 1), 4, '0')
   INTO l_numeroLote
   FROM PER_SOLIC_MONET H
   WHERE H.PAIS_COD_PAIS = p_codigoPais
   AND SUBSTR(H.NUM_LOTE, 1, 8) = TO_CHAR(SYSDATE, 'YYYYMMDD');

   p_numeroLote := l_numeroLote;

    END IF;

 END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_CABEC_DETAL_DOLSO: '||ls_sqlerrm);

END PER_PR_CABEC_DETAL_DOLSO;


PROCEDURE PER_PR_CABEC_DETAL_DOLSO_TEMPO (p_codigoPais IN VARCHAR2, p_ultimoRegistro OUT NUMBER) IS
/***************************************************************************************************
Descripcion : Inserta registros de Cabecera y Detalle en Temporal
Fecha Creacion : 06/09/2006
Fecha Modificacion : 06/09/2006
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
Parametros Salida :
Autor : Jose Martinez Vargas
Version : Beta (Beta|Final)
Cambios Importantes : Ninguno
****************************************************************************************************/

l_fechaUltimaEjecucion DATE;
l_oidUltimoRegistro NUMBER;
l_ultimooid NUMBER;
LN_CONTADOR NUMBER;

BEGIN

 -- Obtenemos los parametros de ultima ejecucion --
 SELECT FEC_ULTI_EJEC, OID_ULTI_REGI_PROC INTO l_fechaUltimaEjecucion, l_oidUltimoRegistro
 FROM PER_CONTR_REGIS_PROGR
 WHERE PAIS_COD_PAIS = p_codigoPais
 AND COD_PROG = '01'
 AND EST_CTRL_REGI_PROG = '1';

 /* Obteniendo el ultimo oid */
 l_ultimooid := PER_FN_ULTI_OID_MOVI_CC(p_codigoPais, '01', l_oidUltimoRegistro);
 IF (l_ultimooid >= l_oidUltimoRegistro) THEN

 --- Insertamos informacion de Cabecera y Detallde a la tabla PER_CABEC_DETAL_DOLSO_TEMPO ---
 INSERT INTO PER_CABEC_DETAL_DOLSO_TEMPO (
 OID_PAIS, COD_PAIS, TIP_SOLI_IMPO_POSI,
 TIP_SOLI_IMPO_NEGA, IND_RECA_IMPO_NEGA,
 COD_TIPO_CARG_ABON, COD_MARC_SITU,
 SOCA_OID_SOLI_CABE, OID_SUBP_CREA, COD_SUBP_CREA,
 OID_PROC_CREA, COD_PROC_CREA,
 OID_SOLI_CABE, OID_SOCI, COD_SOCI,
 OID_CANA, COD_CANA, COD_SBAC, COD_ACCE,
 VAL_NUME_SOLI, OID_MARC, COD_MARC,
 OID_PERI, COD_PERI, COD_CLIE,
 COD_TIPO_DOCU_FISC, COD_TIPO_DOCU_NNAL, FEC_FACT,
 OID_ZONA,
 SOCA_OID_DOCU_REFE, COD_CANA_REFE,
 COD_SBAC_REFE, COD_ACCE_REFE, VAL_NUME_SOLI_REFE
 )
 -- Obtenemos los Procesos y Subprocesos del Programa Cargos y Abonos Directos e Incoblables --
 WITH TEMPORAL AS
 (SELECT PER_PROCE_SUBPR_PROGR.PAIS_COD_PAIS,
 SEG_PAIS.OID_PAIS,
 PER_PROCE_SUBPR_PROGR.COD_PROC,
 PER_PROCE_SUBPR_PROGR.COD_SUBP,
 PER_PROCE_SUBPR_PROGR.TIP_SOLI_IMPO_POSI,
 PER_PROCE_SUBPR_PROGR.TIP_SOLI_IMPO_NEGA,
 PER_PROCE_SUBPR_PROGR.IND_RECA_IMPO_NEGA,
 CCC_PROCE.OID_PROC,
 CCC_SUBPR.OID_SUBP,

 (SELECT COD_TIPO_CARG_ABON
 FROM CCC_TIPO_ABONO_SUBPR,
 CCC_MARCA_TIPO_ABONO,
 CCC_TIPO_CARGO_ABONO
 WHERE CCC_SUBPR.OID_SUBP = CCC_TIPO_ABONO_SUBPR.SUBP_OID_SUBP
 AND CCC_TIPO_ABONO_SUBPR.OID_TIPO_ABON_SUBP = CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP
 AND CCC_TIPO_ABONO_SUBPR.TCAB_OID_TCAB = CCC_TIPO_CARGO_ABONO.OID_TIPO_CARG_ABON
 AND CCC_MARCA_TIPO_ABONO.IND_ENTR_SALI = 'S'
 AND ROWNUM = 1
 ) COD_TIPO_CARG_ABON,

 (SELECT CCC_MARCA_SITUA.COD_MARC_SITU
 FROM CCC_TIPO_ABONO_SUBPR,
 CCC_MARCA_TIPO_ABONO,
 CCC_MARCA_SITUA
 WHERE CCC_SUBPR.OID_SUBP = CCC_TIPO_ABONO_SUBPR.SUBP_OID_SUBP
 AND CCC_TIPO_ABONO_SUBPR.OID_TIPO_ABON_SUBP = CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP
 AND CCC_MARCA_TIPO_ABONO.MASI_OID_MARC_SALI = CCC_MARCA_SITUA.OID_MARC_SITU
 AND CCC_MARCA_TIPO_ABONO.IND_ENTR_SALI = 'S'
 AND ROWNUM = 1
 ) COD_MARC_SITU

 FROM PER_PROCE_SUBPR_PROGR,
 SEG_PAIS,
 CCC_PROCE,
 CCC_SUBPR
 WHERE PAIS_COD_PAIS = p_codigoPais
 AND CREP_COD_PROG = '01'
 AND EST_PROC_SUBP_PROG = '1'
 AND PER_PROCE_SUBPR_PROGR.PAIS_COD_PAIS = SEG_PAIS.COD_PAIS
 AND CCC_PROCE.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS
 AND PER_PROCE_SUBPR_PROGR.COD_PROC = CCC_PROCE.COD_PROC
 AND CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC
 AND PER_PROCE_SUBPR_PROGR.COD_SUBP = CCC_SUBPR.COD_SUBP
 )

 SELECT CABECERA.*,
 SEG_CANAL.COD_CANA COD_CANA_REFE,
 SEG_SUBAC.COD_SBAC COD_SUBAC_REFE,
 SEG_ACCES.COD_ACCE COD_ACCE_REFE,
 PED_SOLIC_CABEC.VAL_NUME_SOLI VAL_NUME_SOLI_REFE
 FROM
 (SELECT DISTINCT
 SEG_PAIS.OID_PAIS,
 SEG_PAIS.COD_PAIS,
 TEMPORAL.TIP_SOLI_IMPO_POSI,
 TEMPORAL.TIP_SOLI_IMPO_NEGA,
 TEMPORAL.IND_RECA_IMPO_NEGA,
 TEMPORAL.COD_TIPO_CARG_ABON,
 TEMPORAL.COD_MARC_SITU,
 CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE,
 CCC_SUBPR.OID_SUBP OID_SUBP_CREA,
 CCC_SUBPR.COD_SUBP COD_SUBP_CREA,
 CCC_PROCE.OID_PROC OID_PROC_CREA,
 CCC_PROCE.COD_PROC COD_PROC_CREA,
 PED_SOLIC_CABEC.OID_SOLI_CABE,
 SEG_SOCIE.OID_SOCI,
 SEG_SOCIE.COD_SOCI,
 SEG_CANAL.OID_CANA,
 SEG_CANAL.COD_CANA,
 SEG_SUBAC.COD_SBAC,
 SEG_ACCES.COD_ACCE,
 PED_SOLIC_CABEC.VAL_NUME_SOLI,
 SEG_MARCA.OID_MARC,
 SEG_MARCA.COD_MARC,
 CRA_PERIO.OID_PERI,
 SEG_PERIO_CORPO.COD_PERI,
 MAE_CLIEN.COD_CLIE,

 (SELECT MAE_TIPO_DOCUM.COD_TIPO_DOCU
 FROM MAE_CLIEN_IDENT, MAE_TIPO_DOCUM
 WHERE MAE_CLIEN_IDENT.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE
 AND MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU = MAE_TIPO_DOCUM.OID_TIPO_DOCU
 AND MAE_TIPO_DOCUM.IND_DOC_IDEN_FISC = 1
 AND MAE_CLIEN_IDENT.VAL_IDEN_DOCU_PRIN = 1
 AND ROWNUM = 1) AS COD_TIPO_DOCU_FISC,

 (SELECT MAE_TIPO_DOCUM.COD_TIPO_DOCU
 FROM MAE_CLIEN_IDENT, MAE_TIPO_DOCUM
 WHERE MAE_CLIEN_IDENT.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE
 AND MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU = MAE_TIPO_DOCUM.OID_TIPO_DOCU
 AND MAE_TIPO_DOCUM.IND_DNI = 1
 AND MAE_CLIEN_IDENT.VAL_IDEN_DOCU_PRIN = 1
 AND ROWNUM = 1) AS COD_TIPO_DOCU_NNAL,

 PED_SOLIC_CABEC.FEC_FACT,
 PED_SOLIC_CABEC.ZZON_OID_ZONA,
 PED_SOLIC_CABEC.SOCA_OID_DOCU_REFE
 FROM
 SEG_PAIS,
 CCC_MOVIM_CUENT_CORRI,
 TEMPORAL,
 CCC_SUBPR,
 CCC_PROCE,
 PED_SOLIC_CABEC,
 SEG_SOCIE,
 CRA_PERIO,
 SEG_CANAL,
 SEG_SUBAC,
 SEG_ACCES,
 SEG_MARCA,
 SEG_PERIO_CORPO,
 MAE_CLIEN
 WHERE ((CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC > l_oidUltimoRegistro)
 AND (CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC <= l_ultimooid )
 AND (CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE IS NOT NULL)
 AND (CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_CREA = CCC_SUBPR.OID_SUBP)
 AND (CCC_SUBPR.CCPR_OID_PROC = CCC_PROCE.OID_PROC)
 AND (CCC_PROCE.OID_PROC = TEMPORAL.OID_PROC)
 AND (CCC_SUBPR.OID_SUBP = TEMPORAL.OID_SUBP)
 AND (CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE)
 AND (PED_SOLIC_CABEC.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
 AND (SEG_PAIS.COD_PAIS = p_codigoPais)
 AND (SEG_SOCIE.OID_SOCI = PED_SOLIC_CABEC.SOCI_OID_SOCI)
 AND (CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI)
 AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
 AND (SEG_SUBAC.OID_SBAC = PED_SOLIC_CABEC.SBAC_OID_SBAC)
 AND (SEG_ACCES.OID_ACCE = SEG_SUBAC.ACCE_OID_ACCE)
 AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
 AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
 AND (PED_SOLIC_CABEC.CLIE_OID_CLIE_RECE_FACT = MAE_CLIEN.OID_CLIE)
 )
 ) CABECERA, PED_SOLIC_CABEC, CRA_PERIO, SEG_CANAL,
 SEG_SUBAC, SEG_ACCES
 WHERE ((CABECERA.SOCA_OID_DOCU_REFE = PED_SOLIC_CABEC.OID_SOLI_CABE (+))
 AND (CRA_PERIO.OID_PERI (+)= PED_SOLIC_CABEC.PERD_OID_PERI)
 AND (SEG_CANAL.OID_CANA (+)= CRA_PERIO.CANA_OID_CANA)
 AND (SEG_SUBAC.OID_SBAC (+)= PED_SOLIC_CABEC.SBAC_OID_SBAC)
 AND (SEG_ACCES.OID_ACCE (+)= SEG_SUBAC.ACCE_OID_ACCE)
 )
 ORDER BY CABECERA.SOCA_OID_SOLI_CABE;

 /* Actualizando ultimo registro procesado */
 p_ultimoregistro := l_ultimooid;
 END IF;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_CABEC_DETAL_DOLSO_TEMPO: '||ls_sqlerrm);

END PER_PR_CABEC_DETAL_DOLSO_TEMPO;


PROCEDURE PER_PR_CUENT_CORRI_DOCLE (
 p_codigoPais IN VARCHAR2,
 p_usuario IN VARCHAR2,
 p_tipOrigenDatos IN VARCHAR2,
 p_codigoInterfaz IN VARCHAR2,
 p_numeroLote IN OUT VARCHAR2) IS
/************************************************************************************************
Descripcion : Inserta en la estructura desglose forma de pagos
Fecha Creacion : 07/09/2006
Fecha Modificacion :
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
Parametros Salida :
Autor : Richard de lso Reyes - rdelosreyes@belcorp.biz / Carlos Bazalar
Version : Beta (Beta|Final)
Cambios Importantes : Ninguno
*************************************************************************************************/

 /** Agregados el 13/09/2006 */
 l_indAfec PER_CUENT_CORRI_DOCLE.IND_AFEC%TYPE;
 l_indRecaPerc PER_CUENT_CORRI_DOCLE.IND_RECA_PERC%TYPE;
 l_tipSoliPerc PER_CUENT_CORRI_DOCLE.TIP_SOLI_PERC%TYPE;
 l_valPorcPerc NUMBER;
 l_valFactCalcPerc NUMBER;
 l_impInaf NUMBER;
 l_impAfec NUMBER;
 l_impPerc NUMBER;
 l_esAgente NUMBER;
 l_numdias NUMBER;
 l_codFormPago PER_CUENT_CORRI_DOCLE.COD_FORM_PAGO%TYPE;

 l_codPais PER_CUENT_CORRI_DOCLE.PAIS_COD_PAIS%TYPE;
 l_codSoci PER_CUENT_CORRI_DOCLE.COD_SOCI%TYPE;
 l_codCana PER_CUENT_CORRI_DOCLE.COD_CANA%TYPE;
 l_codAcce PER_CUENT_CORRI_DOCLE.COD_ACCE%TYPE;
 l_codSbac PER_CUENT_CORRI_DOCLE.COD_SBAC%TYPE;
 l_numSoliCons NUMBER;
 l_tipDocuLega PER_CUENT_CORRI_DOCLE.TIP_DOCU_LEGA%TYPE;
 l_ejeDocuInte PER_CUENT_CORRI_DOCLE.EJE_DOCU_INTE%TYPE;
 l_numDocuInte NUMBER;
 l_numOrdeCuot NUMBER;
 l_existeCodFormPago NUMBER;
 l_numeroLote PER_SOLIC_MONET.NUM_LOTE%TYPE;
 regCuentaCancelacion PER_CUENT_CORRI_DOCLE%ROWTYPE;
 ln_correlativo PER_CUENT_CORRI_DOCLE.COR_CUEN_CORR_DOLE%TYPE;
 pRegistro PER_CUENT_CORRI_DOCLE%ROWTYPE;
 ln_secuencia NUMBER;
 ln_pagina NUMBER:=0;
 ldFechaActual DATE;
 lsFechaActual VARCHAR2(10);
 lsCodPeriMone VARCHAR2(6);
 W_FILAS NUMBER := 5000;
 lv_cod_peri SEG_PERIO_CORPO.COD_PERI%TYPE;

 TYPE DetalleCuotaFormaPago IS RECORD
 ( codigoPais PER_CUENT_CORRI_DOCLE.PAIS_COD_PAIS%TYPE,
 codSoci PER_CUENT_CORRI_DOCLE.COD_SOCI%TYPE,
 codCana PER_CUENT_CORRI_DOCLE.COD_CANA%TYPE,
 codAcce PER_CUENT_CORRI_DOCLE.COD_ACCE%TYPE,
 codSbac PER_CUENT_CORRI_DOCLE.COD_SBAC%TYPE,
 numSoliCons PER_CUENT_CORRI_DOCLE.NUM_SOLI_CONS%TYPE,
 tipDocuLega PER_CUENT_CORRI_DOCLE.TIP_DOCU_LEGA%TYPE,
 ejeDocuInte PER_CUENT_CORRI_DOCLE.EJE_DOCU_INTE%TYPE,
 numDocuInte PER_CUENT_CORRI_DOCLE.NUM_DOCU_INTE%TYPE ,
 numOrdeCuot PER_CUENT_CORRI_DOCLE.NUM_ORDE_CUOT%TYPE,
 codMarc PER_CUENT_CORRI_DOCLE.COD_MARC%TYPE,
 codPeri PER_CUENT_CORRI_DOCLE.COD_PERI%TYPE,
 codClie PER_CUENT_CORRI_DOCLE.COD_CLIE%TYPE,
 tipDocuIdenFisc PER_CUENT_CORRI_DOCLE.TIP_DOCU_IDEN_FISC%TYPE,
 numIdenFisc PER_CUENT_CORRI_DOCLE.NUM_IDEN_FISC%TYPE,
 tipDocuIdenNNal PER_CUENT_CORRI_DOCLE.TIP_DOCU_IDEN_NNAL%TYPE,
 numIdenNNal PER_CUENT_CORRI_DOCLE.NUM_IDEN_NNAL%TYPE,
 fecEmisDocu PER_CUENT_CORRI_DOCLE.FEC_EMIS_DOCU%TYPE,
 fecFact PER_CUENT_CORRI_DOCLE.FEC_FACT%TYPE ,
 fecVencCuot PER_CUENT_CORRI_DOCLE.FEC_VENC_CUOT%TYPE,
 formPago PER_CUENT_CORRI_DOCLE.COD_FORM_PAGO%TYPE,
 codMediPago PER_CUENT_CORRI_DOCLE.COD_MEDI_PAGO%TYPE,
 marcSitu PER_CUENT_CORRI_DOCLE.COD_MARC_SITU%TYPE,
 tipCargAbon PER_CUENT_CORRI_DOCLE.TIP_CARG_ABON%TYPE,
 codProcCrea PER_CUENT_CORRI_DOCLE.COD_PROC_CREA%TYPE,
 codSubpCrea PER_CUENT_CORRI_DOCLE.COD_SUBP_CREA%TYPE,
 codCanaRefe PER_CUENT_CORRI_DOCLE.COD_CANA_REFE%TYPE,
 codAcceRefe PER_CUENT_CORRI_DOCLE.COD_ACCE_REFE%TYPE,
 codSbacRefe PER_CUENT_CORRI_DOCLE.COD_SBAC_REFE%TYPE ,
 numSoliConsRefe PER_CUENT_CORRI_DOCLE.NUM_SOLI_CONS_REFE%TYPE,
 canaPerc PER_CUENT_CORRI_DOCLE.COD_CANA_PERC%TYPE,
 accePerc PER_CUENT_CORRI_DOCLE.COD_CANA_PERC%TYPE,
 sbacPerc PER_CUENT_CORRI_DOCLE.COD_SUBA_PERC%TYPE,
 numSoliConsPerc PER_CUENT_CORRI_DOCLE.NUM_SOLI_CONS_PERC%TYPE,
 indAfec PER_CUENT_CORRI_DOCLE.IND_AFEC%TYPE,
 impTotaDocu PER_CUENT_CORRI_DOCLE.IMP_TOTA_DOCU%TYPE, -- VAL_TOTA_PAGA_LOCA
 impCuot PER_CUENT_CORRI_DOCLE.IMP_CUOT%TYPE, -- IMP_CUOT_FRAC_LOCA
 impCuotPend PER_CUENT_CORRI_DOCLE.IMP_CUOT_PEND%TYPE, -- IMP_CUOT_FRAC_LOCA
 tipSoliImpoPosi PER_CABEC_DETAL_DOLSO.TIP_SOLI_IMPO_POSI%TYPE,
 impFletFracLoca PER_DETAL_CUOTA_FORMA_PAGO.IMP_FLET_FRAC_LOCA%TYPE,
 tipSoliImpoNega PER_CABEC_DETAL_DOLSO.TIP_SOLI_IMPO_NEGA%TYPE,
 indRecaImpoNega PER_CABEC_DETAL_DOLSO.IND_RECA_IMPO_NEGA%TYPE,
 codPeriFactu VARCHAR2(6)
 );

 TYPE DetalleCuotaFormaPagoTab IS TABLE OF DetalleCuotaFormaPago;
 DetalleCuotaFormaPago_Recs DetalleCuotaFormaPagoTab;

 /* Obtenemos los Detalles de Cuotas de Forma de Pago */
 CURSOR DETAL_CUOTA_FORMA_PAGO IS
 SELECT A.COD_PAIS,
 A.COD_SOCI,
 A.COD_CANA,
 A.COD_ACCE,
 A.COD_SBAC,
 A.VAL_NUME_SOLI, -- NUMERO SOLICITUD CONSOLIDADO
 A.COD_TIPO_DOCU, -- TIPO DOCUMENTO LEGAL
 A.VAL_EJER_DOCU_INTE,
 A.NUM_DOCU_CONT_INTE ,
 1 NUM_ORDE,
 A.COD_MARC,
 A.COD_PERI,
 A.COD_CLIE,
 A.COD_TIPO_DOCU_FISC, -- TIPO DOCUMENTO FISCAL
 A.VAL_NUME_IDEN_FISC,
 A.COD_TIPO_DOCU_NNAL, -- TIPO DOCUMENTO NACIONAL
 A.VAL_NUME_IDEN_NNAL,
 A.FEC_EMIS_FACT,
 A.FEC_FACT ,
 A.FEC_VENC, -- FECHA VENCIMIENTO CUOTA
 A.COD_FORM_PAGO_CAPO,
 A.COD_MEDI_PAGO,
 A.COD_MARC_SITU,
 A.COD_TIPO_CARG_ABON,
 A.COD_PROC_CREA,
 A.COD_SUBP_CREA,
 A.COD_CANA_REFE,
 A.COD_ACCE_REFE,
 A.COD_SBAC_REFE ,
 A.VAL_NUME_SOLI_REFE,
 NULL, -- CANAL PERCEPCIONES
 NULL, -- ACCESO PERCEPCIONES
 NULL, -- SUBACCESO PERCEPCIONES
 NULL, -- NUMERO SOLICITUD CONSOLIDADO PERCEPCIONES
 A.IND_AFEC,
 A.VAL_TOTA_PAGA_LOCA,
 A.IMP_CUOT_FRAC_LOCA, -- IMPORTE CUOTA
 A.IMP_CUOT_FRAC_LOCA, -- IMPORTE CUOTA PENDIENTE
 A.TIP_SOLI_IMPO_POSI,
 A.IMP_FLET_FRAC_LOCA,
 A.TIP_SOLI_IMPO_NEGA,
 A.IND_RECA_IMPO_NEGA,
 B.COD_PERI
 FROM PER_DETAL_CUOTA_FORMA_PAGO A,
 PER_GTT_PERIO B
 WHERE B.COD_MARC = A.COD_MARC
 AND B.COD_CANA = A.COD_CANA
 AND B.FEC_FACT = A.FEC_FACT
 AND B.COD_PERI = A.COD_PERI
 ORDER BY A.VAL_NUME_SOLI, A.FEC_VENC, A.OID_MEDI_PAGO, A.OID_SOLI_POSI, A.OID_FORM_PAGO_CAPO;

 CURSOR cVerificaCruceCampa IS
 SELECT DISTINCT
 A.COD_MARC,
 A.COD_CANA,
 A.FEC_FACT
 FROM PER_DETAL_CUOTA_FORMA_PAGO A;

 lnVerifica NUMBER;
 lsCodPeri VARCHAR2(6);
BEGIN

 l_numOrdeCuot:=1;
 l_numeroLote := p_numeroLote;

 /* Agregado para solucionar incidencias de CDR 02/10/2008 */

 FOR v_per IN (SELECT DISTINCT
 A.COD_MARC,
 A.COD_CANA,
 A.FEC_FACT
 FROM PER_DETAL_CUOTA_FORMA_PAGO A) LOOP

 lv_cod_peri:= GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(
 p_codigoPais,
 v_per.COD_MARC,
 v_per.COD_CANA,
 v_per.FEC_FACT);

 UPDATE PER_DETAL_CUOTA_FORMA_PAGO A
 SET A.COD_PERI=lv_cod_peri
 WHERE a.cod_pais=p_codigoPais
 AND A.COD_MARC= v_per.COD_MARC
 AND A.COD_CANA= v_per.COD_CANA
 AND A.FEC_FACT= v_per.FEC_FACT;

 END LOOP;

 /* Obtenemos los periodos a enlazar con Forma de Pago */
 DELETE FROM PER_GTT_PERIO;

 INSERT INTO PER_GTT_PERIO
 (COD_MARC, COD_CANA, FEC_FACT)
 SELECT DISTINCT
 A.COD_MARC,
 A.COD_CANA,
 A.FEC_FACT
 FROM PER_DETAL_CUOTA_FORMA_PAGO A;

 UPDATE PER_GTT_PERIO A
 SET A.COD_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(
 p_codigoPais,
 A.COD_MARC,
 A.COD_CANA,
 A.FEC_FACT);

 /* Modificacion 16-10-2008 */
 FOR cFecFactu IN cVerificaCruceCampa LOOP
 lsCodPeri := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_FECHA(
 p_codigoPais,
 cFecFactu.COD_MARC,
 cFecFactu.COD_CANA,
 cFecFactu.FEC_FACT);

 UPDATE PER_DETAL_CUOTA_FORMA_PAGO A
 SET A.COD_PERI=lsCodPeri
 WHERE a.cod_pais=p_codigoPais
 AND A.COD_MARC= cFecFactu.COD_MARC
 AND A.COD_CANA= cFecFactu.COD_CANA
 AND A.FEC_FACT= cFecFactu.FEC_FACT;
 END LOOP;
 /* Cierre Modificacion 16-10-2008 */

 /* Verificando si hay Cruce de Campa?a */
 FOR cCruce IN cVerificaCruceCampa LOOP
 lnVerifica := GEN_PKG_GENER.GEN_FN_VERIF_CRUCE_CAMPA(
 p_codigoPais,
 cCruce.COD_MARC,
 cCruce.COD_CANA,
 cCruce.FEC_FACT);
 IF lnVerifica = 1 THEN
 INSERT INTO PER_GTT_PERIO(
 COD_MARC, COD_CANA, FEC_FACT, COD_PERI)
 VALUES (
 cCruce.COD_MARC,
 cCruce.COD_CANA,
 cCruce.FEC_FACT,
 GEN_PKG_GENER.GEN_FN_DEVUE_PERIO_MAYOR_FECHA(
 p_codigoPais,
 cCruce.COD_MARC,
 cCruce.COD_CANA,
 cCruce.FEC_FACT)
 );
 END IF;
 END LOOP;


 /* obteniendo las coutas formas de pago */
 OPEN DETAL_CUOTA_FORMA_PAGO;
 LOOP
 FETCH DETAL_CUOTA_FORMA_PAGO BULK COLLECT INTO DetalleCuotaFormaPago_Recs LIMIT W_FILAS;
 ln_pagina := ln_pagina + 1;

 IF DetalleCuotaFormaPago_Recs.COUNT > 0 THEN
 FOR ynd IN DetalleCuotaFormaPago_Recs.FIRST .. DetalleCuotaFormaPago_Recs.LAST LOOP

 gn_lineaError := ynd;
 /* Si Importe Fraccionado Local es Mayor a 0 */
 IF DetalleCuotaFormaPago_Recs(ynd).impCuot > 0 THEN
 l_indRecaPerc := NULL;
 l_indAfec := DetalleCuotaFormaPago_Recs(ynd).indAfec;


 /* Si es Afecto a Percepcion*/
 IF DetalleCuotaFormaPago_Recs(ynd).indAfec = 'S' THEN
 l_tipSoliPerc:=DetalleCuotaFormaPago_Recs(ynd).tipSoliImpoPosi;

 SELECT COUNT(1) INTO l_esAgente
 FROM PER_AGENT_PERCE
 WHERE PAIS_COD_PAIS = p_codigoPais
 AND RUC_AGEN_PERC = DetalleCuotaFormaPago_Recs(ynd).numIdenFisc
 AND EST_AGEN_PERC = '1';

 /* Si es Agente de Percepcion */
 IF l_esAgente > 0 THEN
 SELECT POR_AGEN_PERC, FAC_CALC_AGEN_PERC INTO l_valPorcPerc, l_valFactCalcPerc
 FROM PER_PORCE_PERCE
 WHERE PAIS_COD_PAIS = p_codigoPais
 AND EST_PORC_PERC = '1';
 ELSE
 SELECT POR_AGEN_NPER, FAC_CALC_AGEN_NPER INTO l_valPorcPerc, l_valFactCalcPerc
 FROM PER_PORCE_PERCE
 WHERE PAIS_COD_PAIS = p_codigoPais
 AND EST_PORC_PERC = '1';
 END IF;
 l_impInaf:=DetalleCuotaFormaPago_Recs(ynd).impFletFracLoca;
 l_impAfec:=DetalleCuotaFormaPago_Recs(ynd).impCuot - DetalleCuotaFormaPago_Recs(ynd).impFletFracLoca;
 l_impPerc:=(l_impAfec * l_valPorcPerc) / 100; --l_valFactCalcPerc;
 ELSE

 l_tipSoliPerc:=NULL;
 l_valPorcPerc:=0;
 l_valFactCalcPerc:=0;
 l_impInaf:=DetalleCuotaFormaPago_Recs(ynd).impCuot;
 l_impAfec:=0;
 l_impPerc:=0;
 END IF;
 ELSE

 l_tipSoliPerc := DetalleCuotaFormaPago_Recs(ynd).tipSoliImpoNega;
 l_indAfec := NULL;
 l_indRecaPerc := DetalleCuotaFormaPago_Recs(ynd).indRecaImpoNega;
 l_valPorcPerc := 0;
 l_valFactCalcPerc := 0;
 l_impInaf := 0;
 l_impAfec := 0;
 l_impPerc := 0;
 END IF;

 /* Insertamos Movimientos en Cuenta Corriente Documento Legal */
 l_impPerc := round(l_impPerc,2);
 IF DetalleCuotaFormaPago_Recs(ynd).impCuot != 0 AND
 DetalleCuotaFormaPago_Recs(ynd).impCuot IS NOT NULL THEN
 pRegistro.pais_cod_pais := p_codigoPais;
 pRegistro.cod_soci := DetalleCuotaFormaPago_Recs(ynd).codSoci;
 pRegistro.cod_cana := DetalleCuotaFormaPago_Recs(ynd).codCana;
 pRegistro.cod_acce := DetalleCuotaFormaPago_Recs(ynd).codAcce;
 pRegistro.cod_sbac := DetalleCuotaFormaPago_Recs(ynd).codSbac;
 pRegistro.num_soli_cons := DetalleCuotaFormaPago_Recs(ynd).numSoliCons;

 l_numOrdeCuot:= PER_FN_SGTE_SEC_ORDEN_COUTA(pRegistro);

 /* Obtiene el siguiente correlativo */
 ln_correlativo := PER_FN_SGTE_CORRE_CORRI_DOCLE ();

 INSERT INTO PER_CUENT_CORRI_DOCLE
 (PAIS_COD_PAIS, COR_CUEN_CORR_DOLE, COD_SOCI,
 COD_CANA, COD_ACCE, COD_SBAC, NUM_SOLI_CONS,
 TIP_DOCU_LEGA, EJE_DOCU_INTE, NUM_DOCU_INTE,
 NUM_ORDE_CUOT, COD_MARC, COD_PERI, COD_CLIE,
 TIP_DOCU_IDEN_FISC, NUM_IDEN_FISC, TIP_DOCU_IDEN_NNAL,
 NUM_IDEN_NNAL, FEC_EMIS_DOCU, FEC_FACT,
 FEC_VENC_CUOT, COD_FORM_PAGO, COD_MEDI_PAGO,
 COD_MARC_SITU, TIP_CARG_ABON, COD_PROC_CREA,
 COD_SUBP_CREA, COD_CANA_REFE, COD_ACCE_REFE,
 COD_SBAC_REFE, NUM_SOLI_CONS_REFE, COD_CANA_PERC,
 COD_ACCE_PERC, COD_SUBA_PERC, NUM_SOLI_CONS_PERC,
 TIP_SOLI_PERC, IND_AFEC, IND_RECA_PERC,
 POR_PERC, FAC_CALC_PERC, IMP_TOTA_DOCU,
 IMP_CUOT, IMP_CUOT_PAGA, IMP_CUOT_PEND,
 IMP_INAF_PERC, IMP_PAGA_INAF_PERC, IMP_PEND_INAF_PERC,
 IMP_AFEC_PERC, IMP_PAGA_AFEC_PERC, IMP_PEND_AFEC_PERC,
 IMP_PERC_PERC, IMP_PAGA_PERC, IMP_PEND_PERC,
 USU_DIGI, FEC_DIGI, USU_MODI, FEC_MODI, EST_CUEN_CORR_DOLE
 )
 VALUES
 (p_codigoPais, ln_correlativo,
 DetalleCuotaFormaPago_Recs(ynd).codSoci, DetalleCuotaFormaPago_Recs(ynd).codCana,
 DetalleCuotaFormaPago_Recs(ynd).codAcce, DetalleCuotaFormaPago_Recs(ynd).codSbac,
 DetalleCuotaFormaPago_Recs(ynd).numSoliCons, DetalleCuotaFormaPago_Recs(ynd).tipDocuLega,
 DetalleCuotaFormaPago_Recs(ynd).ejeDocuInte, DetalleCuotaFormaPago_Recs(ynd).numDocuInte,
 l_numOrdeCuot, DetalleCuotaFormaPago_Recs(ynd).codMarc,
 DetalleCuotaFormaPago_Recs(ynd).codPeri, DetalleCuotaFormaPago_Recs(ynd).codClie,
 DetalleCuotaFormaPago_Recs(ynd).tipDocuIdenFisc, DetalleCuotaFormaPago_Recs(ynd).numIdenFisc,
 DetalleCuotaFormaPago_Recs(ynd).tipDocuIdenNNal, DetalleCuotaFormaPago_Recs(ynd).numIdenNNal,
 DetalleCuotaFormaPago_Recs(ynd).fecEmisDocu, DetalleCuotaFormaPago_Recs(ynd).fecFact,
 DetalleCuotaFormaPago_Recs(ynd).fecVencCuot, DetalleCuotaFormaPago_Recs(ynd).formPago,
 DetalleCuotaFormaPago_Recs(ynd).codMediPago, DetalleCuotaFormaPago_Recs(ynd).marcSitu,
 DetalleCuotaFormaPago_Recs(ynd).tipCargAbon, DetalleCuotaFormaPago_Recs(ynd).codProcCrea,
 DetalleCuotaFormaPago_Recs(ynd).codSubpCrea, DetalleCuotaFormaPago_Recs(ynd).codCanaRefe,
 DetalleCuotaFormaPago_Recs(ynd).codAcceRefe, DetalleCuotaFormaPago_Recs(ynd).codSbacRefe,
 DetalleCuotaFormaPago_Recs(ynd).numSoliConsRefe, DetalleCuotaFormaPago_Recs(ynd).canaPerc,
 DetalleCuotaFormaPago_Recs(ynd).accePerc, DetalleCuotaFormaPago_Recs(ynd).sbacPerc,
 DetalleCuotaFormaPago_Recs(ynd).numSoliConsPerc, l_tipSoliPerc,
 l_indAfec, l_indRecaPerc, l_valPorcPerc, l_valFactCalcPerc,
 DetalleCuotaFormaPago_Recs(ynd).impTotaDocu, DetalleCuotaFormaPago_Recs(ynd).impCuot,
 0, DetalleCuotaFormaPago_Recs(ynd).impCuotPend,
 l_impInaf, 0, l_impInaf,
 l_impAfec, 0, l_impAfec,
 l_impPerc, 0, l_impPerc,
 p_usuario, SYSDATE, NULL, NULL, '1'
 );
 END IF;

 -- COMMIT;

 /* Setea valores para ser enviados a proceso de Cancelacion */
 regCuentaCancelacion.pais_cod_pais := p_codigoPais;
 regCuentaCancelacion.cor_cuen_corr_dole := ln_correlativo;
 regCuentaCancelacion.cod_soci := DetalleCuotaFormaPago_Recs(ynd).codSoci;
 regCuentaCancelacion.cod_cana := DetalleCuotaFormaPago_Recs(ynd).codCana;
 regCuentaCancelacion.cod_acce := DetalleCuotaFormaPago_Recs(ynd).codAcce;
 regCuentaCancelacion.cod_sbac := DetalleCuotaFormaPago_Recs(ynd).codSbac;
 regCuentaCancelacion.num_soli_cons := DetalleCuotaFormaPago_Recs(ynd).numSoliCons;
 regCuentaCancelacion.tip_docu_lega := DetalleCuotaFormaPago_Recs(ynd).tipDocuLega;
 regCuentaCancelacion.eje_docu_inte := DetalleCuotaFormaPago_Recs(ynd).ejeDocuInte;
 regCuentaCancelacion.num_docu_inte := DetalleCuotaFormaPago_Recs(ynd).numDocuInte;
 regCuentaCancelacion.num_orde_cuot := l_numOrdeCuot;
 regCuentaCancelacion.cod_marc := DetalleCuotaFormaPago_Recs(ynd).codMarc;
 regCuentaCancelacion.cod_peri := DetalleCuotaFormaPago_Recs(ynd).codPeri;
 regCuentaCancelacion.cod_clie := DetalleCuotaFormaPago_Recs(ynd).codClie;
 regCuentaCancelacion.tip_docu_iden_fisc := DetalleCuotaFormaPago_Recs(ynd).tipDocuIdenFisc;
 regCuentaCancelacion.num_iden_fisc := DetalleCuotaFormaPago_Recs(ynd).numIdenFisc;
 regCuentaCancelacion.tip_docu_iden_nnal := DetalleCuotaFormaPago_Recs(ynd).tipDocuIdenNNal;
 regCuentaCancelacion.num_iden_nnal := DetalleCuotaFormaPago_Recs(ynd).numIdenNNal;
 regCuentaCancelacion.fec_emis_docu := DetalleCuotaFormaPago_Recs(ynd).fecEmisDocu;
 regCuentaCancelacion.fec_fact := DetalleCuotaFormaPago_Recs(ynd).fecFact;
 regCuentaCancelacion.fec_venc_cuot := DetalleCuotaFormaPago_Recs(ynd).fecVencCuot;
 regCuentaCancelacion.cod_form_pago := DetalleCuotaFormaPago_Recs(ynd).formPago;
 regCuentaCancelacion.cod_medi_pago := DetalleCuotaFormaPago_Recs(ynd).codMediPago;
 regCuentaCancelacion.cod_marc_situ := DetalleCuotaFormaPago_Recs(ynd).marcSitu;
 regCuentaCancelacion.tip_carg_abon := DetalleCuotaFormaPago_Recs(ynd).tipCargAbon;
 regCuentaCancelacion.cod_proc_crea := DetalleCuotaFormaPago_Recs(ynd).codProcCrea;
 regCuentaCancelacion.cod_subp_crea := DetalleCuotaFormaPago_Recs(ynd).codSubpCrea;
 regCuentaCancelacion.cod_cana_refe := DetalleCuotaFormaPago_Recs(ynd).codCanaRefe;
 regCuentaCancelacion.cod_acce_refe := DetalleCuotaFormaPago_Recs(ynd).codAcceRefe;
 regCuentaCancelacion.cod_sbac_refe := DetalleCuotaFormaPago_Recs(ynd).codSbacRefe;
 regCuentaCancelacion.num_soli_cons_refe := DetalleCuotaFormaPago_Recs(ynd).numSoliConsRefe;
 regCuentaCancelacion.cod_cana_perc := DetalleCuotaFormaPago_Recs(ynd).canaPerc;
 regCuentaCancelacion.cod_acce_perc := DetalleCuotaFormaPago_Recs(ynd).accePerc;
 regCuentaCancelacion.cod_suba_perc := DetalleCuotaFormaPago_Recs(ynd).sbacPerc;
 regCuentaCancelacion.num_soli_cons_perc := DetalleCuotaFormaPago_Recs(ynd).numSoliConsPerc;
 regCuentaCancelacion.tip_soli_perc := l_tipSoliPerc;
 regCuentaCancelacion.ind_afec := DetalleCuotaFormaPago_Recs(ynd).indAfec;
 regCuentaCancelacion.ind_reca_perc := l_indRecaPerc;
 regCuentaCancelacion.por_perc := l_valPorcPerc;
 regCuentaCancelacion.fac_calc_perc := l_valFactCalcPerc;
 regCuentaCancelacion.imp_tota_docu := DetalleCuotaFormaPago_Recs(ynd).impTotaDocu;
 regCuentaCancelacion.imp_cuot := DetalleCuotaFormaPago_Recs(ynd).impCuot;
 regCuentaCancelacion.imp_cuot_paga := 0;
 regCuentaCancelacion.imp_cuot_pend := DetalleCuotaFormaPago_Recs(ynd).impCuotPend;
 regCuentaCancelacion.imp_inaf_perc := l_impInaf;
 regCuentaCancelacion.imp_paga_inaf_perc := 0;
 regCuentaCancelacion.imp_pend_inaf_perc := l_impInaf;
 regCuentaCancelacion.imp_afec_perc := l_impAfec;
 regCuentaCancelacion.imp_paga_afec_perc := 0;
 regCuentaCancelacion.imp_pend_afec_perc := l_impAfec;
 regCuentaCancelacion.imp_perc_perc := l_impPerc;
 regCuentaCancelacion.imp_paga_perc := 0;
 regCuentaCancelacion.imp_pend_perc := l_impPerc;
 regCuentaCancelacion.usu_digi := p_usuario;
 regCuentaCancelacion.fec_digi := SYSDATE;
 regCuentaCancelacion.usu_modi := NULL;
 regCuentaCancelacion.fec_modi := NULL;
 regCuentaCancelacion.est_cuen_corr_dole := '1';


 /* Insertamos movimientos en Solicitudes Monetarias
 Si Importe a Percibir Percepcion es mayor a 0 y esta Afecto a Percepcion */
 IF l_impPerc > 0 AND DetalleCuotaFormaPago_Recs(ynd).indAfec = 'S' THEN
 lsFechaActual := to_char(SYSDATE,'dd/mm/yyyy');
 ldFechaActual := to_date(lsFechaActual, 'dd/mm/yyyy');
 l_numdias := DetalleCuotaFormaPago_Recs(ynd).fecVencCuot - ldFechaActual; ---DetalleCuotaFormaPago_Recs(ynd).fecFact;
 IF l_numdias < 0 OR l_numdias > 99 THEN
 l_codFormPago:='CRA';
 ELSE
 l_codFormPago := 'P'||LPAD(l_numDias, 2, '0');
 SELECT COUNT(1) INTO l_existeCodFormPago
 FROM BEL_FORMA_PAGO A,
 BEL_FORMA_PAGO_DETAL B,
 SEG_PAIS P
 WHERE P.COD_PAIS = p_codigoPais
 AND A.OID_FORM_PAGO = B.FOPA_OID_FORM_PAGO
 AND A.PAIS_OID_PAIS = P.OID_PAIS
 AND A.COD_FORM_PAGO = l_codFormPago;

 IF l_existeCodFormPago = 0 THEN
 l_codFormPago:='CRA';
 END IF;
 END IF;

 lsCodPeriMone := DetalleCuotaFormaPago_Recs(ynd).CodPeriFactu;

 SELECT PER_SEQ_SOLIC_MONET.nextval
 INTO ln_secuencia
 FROM DUAL;

 INSERT INTO PER_SOLIC_MONET
 ( PAIS_COD_PAIS, COR_SOLI_MONE, COR_CUEN_CORR_DOLE,
 TIOR_TIPO_ORIG_DATO, NUM_LOTE,
 COD_PERI, COD_CLIE,
 FEC_FACT, FEC_VENC,
 VAL_IMPO, COD_CANA_REFE,
 COD_ACCE_REFE, COD_SBAC_REFE,
 NUM_DOCU_REFE, COD_TIPO_SOLI,
 COD_ACCE, COD_SBAC,
 TIP_POSI, VAL_CANT,
 COD_FORM_PAGO, USU_DIGI,
 FEC_DIGI, USU_MODI, FEC_MODI, EST_SOLI_MONE)
 VALUES(
 p_codigoPais, ln_secuencia, ln_correlativo,
 p_tipOrigenDatos, l_numeroLote,
 lsCodPeriMone, DetalleCuotaFormaPago_Recs(ynd).codClie,
 DetalleCuotaFormaPago_Recs(ynd).fecFact, DetalleCuotaFormaPago_Recs(ynd).fecVencCuot,
 l_impPerc, DetalleCuotaFormaPago_Recs(ynd).codCana,
 DetalleCuotaFormaPago_Recs(ynd).codAcce, DetalleCuotaFormaPago_Recs(ynd).codSbac,
 DetalleCuotaFormaPago_Recs(ynd).numSoliCons, l_tipSoliPerc,
 'PR', '290',
 'PR', 1,
 l_codFormPago, p_usuario,
 SYSDATE, NULL, NULL, '1'
 );
 p_numeroLote:=l_numeroLote;
 END IF;

 /* Invocamos al Procedimiento de Cancelacion Automatica de Deuda */
 IF DetalleCuotaFormaPago_Recs(ynd).impCuot < 0 THEN
 --dbms_output.put_line('CAUTOM '||ln_correlativo || ' ' || regCuentaCancelacion.num_soli_cons || ' ' || regCuentaCancelacion.num_orde_cuot );
 PER_PR_CANCE_AUTOM_DEUDA (regCuentaCancelacion, p_tipOrigenDatos, l_numerolote);
 END IF;

 -- COMMIT;

 /* Invocamos al Procedimiento de Aplicacion Automatica de Deuda Positiva */
 IF DetalleCuotaFormaPago_Recs(ynd).impCuot > 0 THEN
 --dbms_output.put_line('DPOS ' || ln_correlativo || ' ' || regCuentaCancelacion.num_soli_cons || ' ' || regCuentaCancelacion.num_orde_cuot );
 PER_PR_DEUDA_POSIT(regCuentaCancelacion, p_tipOrigenDatos, l_numerolote);
 END IF;

 -- COMMIT;

 END LOOP;
 END IF;
 EXIT WHEN DETAL_CUOTA_FORMA_PAGO%NOTFOUND;
 END LOOP;
 CLOSE DETAL_CUOTA_FORMA_PAGO;
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'Pagina ' || ln_Pagina ||' Fila: '||gn_lineaError||' ERROR PER_PR_CUENT_CORRI_DOCLE: '||ls_sqlerrm);
END PER_PR_CUENT_CORRI_DOCLE;


PROCEDURE PER_PR_DESGL_FORMA_PAGO (p_codigoPais IN VARCHAR2, p_usuario IN VARCHAR2) IS
/************************************************************************************************
Descripcion : Inserta en la estructura desglose forma de pagos
Fecha Creacion : 07/09/2006
Fecha Modificacion :
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
Parametros Salida :
Autor : Richard de lso Reyes - rdelosreyes@belcorp.biz / Carlos Bazalar
Version : Beta (Beta|Final)
Cambios Importantes : Ninguno
*************************************************************************************************/
 l_oidSoliCabe NUMBER;
 l_count NUMBER;

 l_formaPagoCabe NUMBER;
 l_totalLocaCabe NUMBER;
 l_fleteLocaCabe NUMBER;

 l_formaPagoPosi NUMBER;
 l_precioCatalogoAcum NUMBER;
 l_impFracLoca NUMBER;
 l_sumaImpFracLoca NUMBER;
 l_impFletFracLoca NUMBER;
 l_impFletAFracLoca NUMBER;
 l_fechaVenc DATE;
 l_fechaFact DATE;
 l_fechaInicio DATE;
 l_fechaFinal DATE;
 l_codActi NUMBER(12);

 /** Agregados el 13/09/2006 */
 l_indRecaPerc VARCHAR2(10);
 l_tipSoliPerc VARCHAR2(4);
 l_valPorcPerc NUMBER;
 l_valFactCalcPerc NUMBER;
 l_impInaf NUMBER;
 l_impAfec NUMBER;
 l_impPerc NUMBER;
 l_esAgente NUMBER;
 l_numdias NUMBER;
 l_codFormPago VARCHAR2(3);

 TYPE DocLegalRec IS RECORD
 ( oidSoliCabe NUMBER(12),
 fopaOidFormPago NUMBER(12),
 valTotaPagaLoca NUMBER(12,2),
 valImpoFletTotaLoca NUMBER(12,2),
 codPais VARCHAR2(3),
 codSoci VARCHAR2(4),
 codCana VARCHAR2(3),
 codAcce VARCHAR2(3),
 codSbac VARCHAR2(3),
 valNumeSoli NUMBER(10),
 codTipoDocu VARCHAR2(3),
 valEjerDocuInte VARCHAR2(2),
 numDocuContInte NUMBER(10),
 codMarc VARCHAR2(3),
 codPeri VARCHAR2(6),
 codClie VARCHAR2(15),
 codTipoDocuFisc VARCHAR2(2),
 valNumeIdenFisc VARCHAR2(30),
 codTipoDocuNnal VARCHAR2(2),
 valNumeIdenNnal VARCHAR2(30),
 fecEmisFact DATE,
 fecFact DATE,
 codMarcSitu VARCHAR2(2),
 codTipoCargAbon VARCHAR2(3),
 codProcCrea VARCHAR2(6),
 codSubpCrea NUMBER(1),
 codCanaRefe VARCHAR2(3),
 codAcceRefe VARCHAR2(3),
 codSbacRefe VARCHAR2(3),
 valNumeSoliRefe NUMBER(10),
 indAfec VARCHAR2(1),
 tipSoliImpoPosi VARCHAR2(4),
 tipSoliImpoNega VARCHAR2(4),
 indRecaImpoNega VARCHAR2(1)
 );

 TYPE DocLegalRecTab IS TABLE OF DocLegalRec;
 DocLegal_Recs DocLegalRecTab;
 DocLegalCtaCte_Recs DocLegalRecTab;

 TYPE DetalleCuota IS RECORD
 ( codigoPais VARCHAR2(3),
 impAFracLoca NUMBER(12,2),
 impFletAFracLoca NUMBER(12,2),
 oidSoliPosi NUMBER(12),
 oidFormPagoCabe NUMBER(12),
 oidFormPagoCuot NUMBER(12),
 fechaFact DATE,
 oidMarc NUMBER(12),
 oidCana NUMBER(12),
 oidPeri NUMBER(12),
 oidZona NUMBER(12)
 );

 TYPE DetalleCuotaTab IS TABLE OF DetalleCuota;
 DetalleCuota_Recs DetalleCuotaTab;

 TYPE FormaPagoDetalle IS RECORD
 ( codFormPago VARCHAR2(3),
 oidFormaPagoDeta NUMBER(12),
 fopaOidFormaPago NUMBER(12),
 valPorcPago NUMBER(3),
 codIndiDias VARCHAR2(1),
 numDias NUMBER(3),
 numPosiDeta NUMBER(3),
 cactOidActi NUMBER(12),
 mpabOidMediPago NUMBER(12),
 oidMediPago NUMBER(12),
 codMediPago VARCHAR2(3)
 );

 TYPE FormaPagoDetalleTab IS TABLE OF FormaPagoDetalle;
 FormaPagoDetalle_Recs FormaPagoDetalleTab;
 W_FILAS NUMBER:=5000;

 /* Creacion de Cursor con los datos de la tabla PER_CABEC_DETAL_DOLSO */
 CURSOR DOC_LEGAL_TEMPORAL IS
 SELECT DISTINCT
 C.SOCA_OID_SOLI_CABE, C.FOPA_OID_FORM_PAGO, C.VAL_TOTA_PAGA_LOCA, C.VAL_IMPO_FLET_TOTA_LOCA,
 C.COD_PAIS, C.COD_SOCI, C.COD_CANA, C.COD_ACCE, C.COD_SBAC, C.VAL_NUME_SOLI, C.COD_TIPO_DOCU,
 C.VAL_EJER_DOCU_INTE, C.NUM_DOCU_CONT_INTE, C.COD_MARC, C.COD_PERI, C.COD_CLIE,
 C.COD_TIPO_DOCU_FISC, C.VAL_NUME_IDEN_FISC, C.COD_TIPO_DOCU_NNAL, C.VAL_NUME_IDEN_NNAL,
 C.FEC_EMIS_FACT, C.FEC_FACT, C.COD_MARC_SITU, C.COD_TIPO_CARG_ABON, C.COD_PROC_CREA,
 C.COD_SUBP_CREA, C.COD_CANA_REFE, C.COD_ACCE_REFE, C.COD_SBAC_REFE, C.VAL_NUME_SOLI_REFE,
 C.IND_AFEC, C.TIP_SOLI_IMPO_POSI, C.TIP_SOLI_IMPO_NEGA, C.IND_RECA_IMPO_NEGA
 FROM PER_CABEC_DETAL_DOLSO C,
 PED_SOLIC_POSIC D
 WHERE C.OID_SOLI_POSI = D.OID_SOLI_POSI
 ORDER BY C.SOCA_OID_SOLI_CABE;

 TYPE DetalleCoutaTipo IS TABLE OF PER_DETAL_CUOTA_FORMA_PAGO%ROWTYPE INDEX BY BINARY_INTEGER;

 DetalleCoutaTipoReg PER_DETAL_CUOTA_FORMA_PAGO%ROWTYPE;
 DetalleCoutaTipoTabla DetalleCoutaTipo;
 DetalleCoutaTipoVacio DetalleCoutaTipo;
 ContadorDetalle NUMBER;

BEGIN
 ContadorDetalle := 0;

 OPEN DOC_LEGAL_TEMPORAL;
 LOOP
 FETCH DOC_LEGAL_TEMPORAL BULK COLLECT INTO DocLegal_Recs LIMIT W_FILAS;
 /* Recorrido del cursor por cada registro del Documento Legal/Solicitud Cabecera */

 IF DocLegal_Recs.COUNT > 0 THEN
 FOR ind IN DocLegal_Recs.FIRST .. DocLegal_Recs.LAST LOOP

 l_oidSoliCabe := DocLegal_Recs(ind).oidSoliCabe;
 l_formaPagoCabe := DocLegal_Recs(ind).fopaOidFormPago;
 l_totalLocaCabe := DocLegal_Recs(ind).valTotaPagaLoca;
 l_fleteLocaCabe := DocLegal_Recs(ind).valImpoFletTotaLoca;

 IF (DocLegal_Recs(ind).numDocuContInte IS NULL AND DocLegal_Recs(ind).valEjerDocuInte IS NULL) THEN
 -- Insertamos las Formas de Pago de la Posicion la cual se considerara como filtro
 -- el codigo de documento y el importe Val_paga_tota_loca
 INSERT INTO PER_DESGL_FORMA_PAGO
 (COD_PAIS, SOCA_OID_SOLI_CABE, OID_FORM_PAGO_CABE,
 OID_SOLI_POSI, OID_FORM_PAGO_POSI, VAL_IMPO_LOCA,
 VAL_IMPO_FLET_LOCA, NUM_DOCU_CONT_INTE, VAL_EJER_DOCU_INTE, USU_DIGI, FEC_DIGI,
 USU_MODI, FEC_MODI, EST_DESG_FORM_PAGO, COD_TIPO_DOCU, VAL_TOTA_PAGA_LOCA )
 SELECT DISTINCT
 C.COD_PAIS AS COD_PAIS,
 C.SOCA_OID_SOLI_CABE AS SOCA_OID_SOLI_CABE,
 C.FOPA_OID_FORM_PAGO AS OID_FORM_PAGO_CABE,
 C.OID_SOLI_POSI AS OID_SOLI_POSI,
 D.FOPA_OID_FORM_PAGO AS OID_FORM_PAGO_POSI,
 D.VAL_PREC_CATA_TOTA_LOCA AS VAL_IMPO_LOCA,
 0 AS VAL_IMPO_FLET_LOCA,
 C.NUM_DOCU_CONT_INTE,
 C.VAL_EJER_DOCU_INTE,
 p_usuario AS USU_DIGI,
 SYSDATE AS FEC_DIGI,
 NULL AS USU_MODI,
 NULL AS FEC_MODI,
 '1' AS EST_DESG_FROM_PAGO,
 C.COD_TIPO_DOCU,
 C.VAL_TOTA_PAGA_LOCA
 FROM PER_CABEC_DETAL_DOLSO C,
 PED_SOLIC_POSIC D
 WHERE C.SOCA_OID_SOLI_CABE = l_oidSoliCabe
 AND C.NUM_DOCU_CONT_INTE IS NULL
 AND C.VAL_EJER_DOCU_INTE IS NULL
 AND C.COD_TIPO_DOCU = DocLegal_Recs(ind).codTipoDocu
 AND C.VAL_TOTA_PAGA_LOCA = DocLegal_Recs(ind).valTotaPagaLoca

 AND C.OID_SOLI_POSI = D.OID_SOLI_POSI
 AND NVL(C.FOPA_OID_FORM_PAGO,0) != D.FOPA_OID_FORM_PAGO
 AND D.FOPA_OID_FORM_PAGO IS NOT NULL ;

 -- Obtengo el acumulado de la Formas de Pago de la Posicion la cual se considerara como filtro
 -- el codigo de documento y el importe Val_paga_tota_loca
 SELECT SUM(D.VAL_PREC_CATA_TOTA_LOCA) INTO l_precioCatalogoAcum
 FROM PER_CABEC_DETAL_DOLSO C,
 PED_SOLIC_POSIC D
 WHERE C.SOCA_OID_SOLI_CABE = l_oidSoliCabe
 AND C.NUM_DOCU_CONT_INTE IS NULL
 AND C.VAL_EJER_DOCU_INTE IS NULL
 AND C.COD_TIPO_DOCU = DocLegal_Recs(ind).codTipoDocu
 AND C.VAL_TOTA_PAGA_LOCA = DocLegal_Recs(ind).valTotaPagaLoca
 AND C.OID_SOLI_POSI = D.OID_SOLI_POSI
 AND NVL(C.FOPA_OID_FORM_PAGO,0) <> D.FOPA_OID_FORM_PAGO
 AND D.FOPA_OID_FORM_PAGO IS NOT NULL ;

 ELSE
 -- Insertamos las Formas de Pago de la Posicion
 INSERT INTO PER_DESGL_FORMA_PAGO
 (COD_PAIS, SOCA_OID_SOLI_CABE, OID_FORM_PAGO_CABE,
 OID_SOLI_POSI, OID_FORM_PAGO_POSI, VAL_IMPO_LOCA,
 VAL_IMPO_FLET_LOCA, NUM_DOCU_CONT_INTE, VAL_EJER_DOCU_INTE, USU_DIGI, FEC_DIGI,
 USU_MODI, FEC_MODI, EST_DESG_FORM_PAGO, COD_TIPO_DOCU, VAL_TOTA_PAGA_LOCA )
 SELECT DISTINCT
 C.COD_PAIS AS COD_PAIS,
 C.SOCA_OID_SOLI_CABE AS SOCA_OID_SOLI_CABE,
 C.FOPA_OID_FORM_PAGO AS OID_FORM_PAGO_CABE,
 C.OID_SOLI_POSI AS OID_SOLI_POSI,
 D.FOPA_OID_FORM_PAGO AS OID_FORM_PAGO_POSI,
 D.VAL_PREC_CATA_TOTA_LOCA AS VAL_IMPO_LOCA,
 0 AS VAL_IMPO_FLET_LOCA,
 C.NUM_DOCU_CONT_INTE,
 C.VAL_EJER_DOCU_INTE,
 p_usuario AS USU_DIGI,
 SYSDATE AS FEC_DIGI,
 NULL AS USU_MODI,
 NULL AS FEC_MODI,
 '1' AS EST_DESG_FROM_PAGO,
 C.COD_TIPO_DOCU,
 C.VAL_TOTA_PAGA_LOCA
 FROM PER_CABEC_DETAL_DOLSO C,
 PED_SOLIC_POSIC D
 WHERE C.SOCA_OID_SOLI_CABE = l_oidSoliCabe
 AND (( DocLegal_Recs(ind).numDocuContInte IS NULL AND C.NUM_DOCU_CONT_INTE IS NULL) OR
 ( DocLegal_Recs(ind).numDocuContInte IS NOT NULL AND
 C.NUM_DOCU_CONT_INTE = DocLegal_Recs(ind).numDocuContInte)
 )
 AND (( DocLegal_Recs(ind).valEjerDocuInte IS NULL AND C.VAL_EJER_DOCU_INTE IS NULL) OR
 ( DocLegal_Recs(ind).valEjerDocuInte IS NOT NULL AND
 C.VAL_EJER_DOCU_INTE = DocLegal_Recs(ind).valEjerDocuInte)
 )
 AND C.OID_SOLI_POSI = D.OID_SOLI_POSI
 AND NVL(C.FOPA_OID_FORM_PAGO,0) != D.FOPA_OID_FORM_PAGO
 AND D.FOPA_OID_FORM_PAGO IS NOT NULL ;

 -- Obtengo el acumulado de la Formas de Pago de la Posicion
 SELECT SUM(D.VAL_PREC_CATA_TOTA_LOCA) INTO l_precioCatalogoAcum
 FROM PER_CABEC_DETAL_DOLSO C,
 PED_SOLIC_POSIC D
 WHERE C.SOCA_OID_SOLI_CABE = l_oidSoliCabe
 AND (( DocLegal_Recs(ind).numDocuContInte IS NULL AND C.NUM_DOCU_CONT_INTE IS NULL) OR
 ( DocLegal_Recs(ind).numDocuContInte IS NOT NULL AND
 C.NUM_DOCU_CONT_INTE = DocLegal_Recs(ind).numDocuContInte)
 )
 AND (( DocLegal_Recs(ind).valEjerDocuInte IS NULL AND C.VAL_EJER_DOCU_INTE IS NULL) OR
 ( DocLegal_Recs(ind).valEjerDocuInte IS NOT NULL AND
 C.VAL_EJER_DOCU_INTE = DocLegal_Recs(ind).valEjerDocuInte)
 )
 AND C.OID_SOLI_POSI = D.OID_SOLI_POSI
 AND NVL(C.FOPA_OID_FORM_PAGO,0) <> D.FOPA_OID_FORM_PAGO
 AND D.FOPA_OID_FORM_PAGO IS NOT NULL ;
 END IF;

 IF l_precioCatalogoAcum IS NULL THEN
 l_precioCatalogoAcum := 0;
 END IF;

 /* Insertamos las Formas de Pago de la Cabecera */
 INSERT INTO PER_DESGL_FORMA_PAGO
 (COD_PAIS, SOCA_OID_SOLI_CABE, OID_FORM_PAGO_CABE,
 OID_SOLI_POSI, OID_FORM_PAGO_POSI, VAL_IMPO_LOCA,
 VAL_IMPO_FLET_LOCA, NUM_DOCU_CONT_INTE, VAL_EJER_DOCU_INTE, USU_DIGI, FEC_DIGI,
 USU_MODI, FEC_MODI, EST_DESG_FORM_PAGO, COD_TIPO_DOCU, VAL_TOTA_PAGA_LOCA)
 VALUES(
 p_codigoPais,
 l_oidSoliCabe,
 l_formaPagoCabe,
 NULL,
 NULL,
 (l_totalLocaCabe - l_precioCatalogoAcum),
 l_fleteLocaCabe,
 DocLegal_Recs(ind).numDocuContInte,
 DocLegal_Recs(ind).valEjerDocuInte,
 p_usuario,
 SYSDATE,
 NULL,
 NULL,
 '1',
 DocLegal_Recs(ind).codTipoDocu,
 DocLegal_Recs(ind).valTotaPagaLoca
 );
 END LOOP;

 /* Para obtener los detalles de las Formas de Pago */
 FOR ind IN DocLegal_Recs.FIRST .. DocLegal_Recs.LAST LOOP
 l_oidSoliCabe := DocLegal_Recs(ind).oidSoliCabe;
 l_formaPagoCabe := DocLegal_Recs(ind).fopaOidFormPago;
 l_totalLocaCabe := DocLegal_Recs(ind).valTotaPagaLoca;
 l_fleteLocaCabe := DocLegal_Recs(ind).valImpoFletTotaLoca;

 /* Obtener detalle de cuotas segun la forma de pago*/
 IF (DocLegal_Recs(ind).numDocuContInte IS NULL AND DocLegal_Recs(ind).valEjerDocuInte IS NULL) THEN
 WITH TEMPORAL AS (
 SELECT SOCA_OID_SOLI_CABE, FEC_FACT,OID_MARC, OID_CANA, OID_PERI, OID_ZONA
 FROM PER_CABEC_DETAL_DOLSO
 WHERE SOCA_OID_SOLI_CABE = l_oidSoliCabe AND
 ROWNUM = 1
 )
 SELECT C.COD_PAIS,
 C.VAL_IMPO_LOCA,
 C.VAL_IMPO_FLET_LOCA,
 C.OID_SOLI_POSI,
 C.OID_FORM_PAGO_CABE,
 C.OID_FORM_PAGO_POSI,
 D.FEC_FACT,
 D.OID_MARC,
 D.OID_CANA,
 D.OID_PERI,
 D.OID_ZONA
 BULK COLLECT INTO DetalleCuota_Recs
 FROM PER_DESGL_FORMA_PAGO C,
 TEMPORAL D
 WHERE C.SOCA_OID_SOLI_CABE = l_oidSoliCabe
 AND C.NUM_DOCU_CONT_INTE IS NULL
 AND C.VAL_EJER_DOCU_INTE IS NULL
 AND C.COD_TIPO_DOCU = DocLegal_Recs(ind).codTipoDocu
 AND C.VAL_TOTA_PAGA_LOCA = DocLegal_Recs(ind).valTotaPagaLoca
 AND D.SOCA_OID_SOLI_CABE = C.SOCA_OID_SOLI_CABE
 ORDER BY C.ROWID;

 ELSE
 WITH TEMPORAL AS (
 SELECT SOCA_OID_SOLI_CABE, FEC_FACT,OID_MARC, OID_CANA, OID_PERI, OID_ZONA
 FROM PER_CABEC_DETAL_DOLSO
 WHERE SOCA_OID_SOLI_CABE = l_oidSoliCabe AND
 ROWNUM = 1
 )
 SELECT C.COD_PAIS,
 C.VAL_IMPO_LOCA,
 C.VAL_IMPO_FLET_LOCA,
 C.OID_SOLI_POSI,
 C.OID_FORM_PAGO_CABE,
 C.OID_FORM_PAGO_POSI,
 D.FEC_FACT,
 D.OID_MARC,
 D.OID_CANA,
 D.OID_PERI,
 D.OID_ZONA
 BULK COLLECT INTO DetalleCuota_Recs
 FROM PER_DESGL_FORMA_PAGO C,
 TEMPORAL D
 WHERE C.SOCA_OID_SOLI_CABE = l_oidSoliCabe
 AND (( DocLegal_Recs(ind).numDocuContInte IS NULL AND C.NUM_DOCU_CONT_INTE IS NULL) OR
 ( DocLegal_Recs(ind).numDocuContInte IS NOT NULL AND
 C.NUM_DOCU_CONT_INTE = DocLegal_Recs(ind).numDocuContInte)
 )
 AND (( DocLegal_Recs(ind).valEjerDocuInte IS NULL AND C.VAL_EJER_DOCU_INTE IS NULL) OR
 ( DocLegal_Recs(ind).valEjerDocuInte IS NOT NULL AND
 C.VAL_EJER_DOCU_INTE = DocLegal_Recs(ind).valEjerDocuInte)
 )
 AND D.SOCA_OID_SOLI_CABE = C.SOCA_OID_SOLI_CABE
 ORDER BY C.ROWID;
 END IF;
 /** Este For barre las formas de Pago de la Posicion */
 IF DetalleCuota_Recs.COUNT > 0 THEN
 FOR jnd IN DetalleCuota_Recs.FIRST .. DetalleCuota_Recs.LAST LOOP

 SELECT X.COD_FORM_PAGO,
 Y.OID_FORM_PAGO_DETA,
 Y.FOPA_OID_FORM_PAGO,
 Y.VAL_PORC_PAGO,
 Y.COD_INDI_DIAS,
 Y.NUM_DIAS,
 Y.NUM_POSI_DETA,
 Y.CACT_OID_ACTI,
 Y.MPAB_OID_MEDI_PAGO,
 M.OID_MEDI_PAGO,
 M.COD_MEDI_PAGO
 BULK COLLECT INTO FormaPagoDetalle_Recs
 FROM BEL_FORMA_PAGO X,
 BEL_FORMA_PAGO_DETAL Y,
 SEG_PAIS P,
 BEL_MEDIO_PAGO M
 WHERE P.COD_PAIS = p_CodigoPais
 AND X.PAIS_OID_PAIS = P.OID_PAIS
 AND X.OID_FORM_PAGO = Y.FOPA_OID_FORM_PAGO
 AND Y.MPAB_OID_MEDI_PAGO = M.OID_MEDI_PAGO
 AND M.PAIS_OID_PAIS = P.OID_PAIS
 AND Y.FOPA_OID_FORM_PAGO = NVL(nvl(DetalleCuota_Recs(jnd).oidFormPagoCuot,DetalleCuota_Recs(jnd).oidFormPagoCabe) ,0);

 l_sumaImpFracLoca:=0;
 l_impFletAFracLoca:=DetalleCuota_Recs(jnd).impFletAFracLoca;

 /* Este For barre el detalle de las Formas de Pago */
 IF FormaPagoDetalle_Recs.COUNT > 0 THEN
 FOR knd IN FormaPagoDetalle_Recs.FIRST .. FormaPagoDetalle_Recs.LAST LOOP

 /* Calculo del Importe de la cuota fraccionado local*/
 IF knd < FormaPagoDetalle_Recs.LAST THEN
 l_impFracLoca := round((FormaPagoDetalle_Recs(knd).valPorcPago * DetalleCuota_Recs(jnd).impAFracLoca) / 100,2);
 l_sumaImpFracLoca:= round(l_sumaImpFracLoca + l_impFracLoca,2);
 ELSE
 l_impFracLoca:= round(DetalleCuota_Recs(jnd).impAFracLoca - l_sumaImpFracLoca,2);
 END IF;

 /* Calculo del Importe flete fraccionado local*/
 IF abs(l_impFletAFracLoca) > abs(l_impFracLoca) AND abs(l_impFletAFracLoca) <> 0 THEN
 l_impFletFracLoca:=l_impFracLoca;
 ELSE
 l_impFletFracLoca:=l_impFletAFracLoca;
 END IF;

 l_impFletAFracLoca:=l_impFletAFracLoca - l_impFletFracLoca;

 /*Calculo de la Fecha de Vencimiento */
 CASE
 WHEN FormaPagoDetalle_Recs(knd).codIndiDias = 'N' THEN
 l_fechaVenc:=DetalleCuota_Recs(jnd).fechaFact + FormaPagoDetalle_Recs(knd).numDias;

 WHEN FormaPagoDetalle_Recs(knd).codIndiDias = 'A' THEN
 BEGIN
 SELECT A.OID_ACTI INTO l_codActi
 FROM CRA_ACTIV A
 WHERE A.OID_ACTI = FormaPagoDetalle_Recs(knd).cactOidActi
 AND A.MARC_OID_MARC = DetalleCuota_Recs(jnd).oidMarc
 AND A.CANA_OID_CANA = DetalleCuota_Recs(jnd).oidCana;

 SELECT FEC_INIC INTO l_fechaVenc
 FROM CRA_CRONO
 WHERE PERD_OID_PERI = DetalleCuota_Recs(jnd).oidPeri
 AND ZZON_OID_ZONA = DetalleCuota_Recs(jnd).oidZona
 AND CACT_OID_ACTI = l_codActi
 AND ROWNUM = 1;

 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 l_fechaVenc := DetalleCuota_Recs(jnd).fechaFact ;
 END;


 WHEN FormaPagoDetalle_Recs(knd).codIndiDias = 'D' THEN
 BEGIN
 SELECT FEC_INIC,
 FEC_FINA
 INTO l_fechaInicio, l_fechaFinal
 FROM CRA_PERIO A,
 SEG_PAIS B
 WHERE A.PAIS_OID_PAIS = B.OID_PAIS
 AND A.MARC_OID_MARC = DetalleCuota_Recs(jnd).oidMarc
 AND A.CANA_OID_CANA = DetalleCuota_Recs(jnd).oidCana
 AND A.OID_PERI = DetalleCuota_Recs(jnd).oidPeri
 AND B.COD_PAIS = p_codigoPais;

 l_fechaVenc:=DetalleCuota_Recs(jnd).fechaFact + (l_fechaFinal - l_fechaInicio + 1);
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 l_fechaVenc := DetalleCuota_Recs(jnd).fechaFact ;
 END;


 WHEN FormaPagoDetalle_Recs(knd).codIndiDias = 'F' THEN
 BEGIN
 SELECT FEC_FINA INTO l_fechaFinal
 FROM CRA_PERIO A, SEG_PAIS B
 WHERE A.PAIS_OID_PAIS = B.OID_PAIS
 AND A.MARC_OID_MARC = DetalleCuota_Recs(jnd).oidMarc
 AND A.CANA_OID_CANA = DetalleCuota_Recs(jnd).oidCana
 AND A.OID_PERI = DetalleCuota_Recs(jnd).oidPeri
 AND B.COD_PAIS = p_codigoPais;

 l_fechaVenc:=l_fechaFinal;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 l_fechaVenc := DetalleCuota_Recs(jnd).fechaFact ;
 END;

 ELSE
 l_fechaVenc:=DetalleCuota_Recs(jnd).fechaFact;

 END CASE;

 IF DetalleCuota_Recs(jnd).oidFormPagoCuot IS NULL THEN
 DetalleCuota_Recs(jnd).oidFormPagoCuot := DetalleCuota_Recs(jnd).oidFormPagoCabe;
 END IF;

 DetalleCoutaTipoReg.Cod_Pais := p_codigoPais;
 DetalleCoutaTipoReg.Cod_Soci := DocLegal_Recs(ind).codSoci;
 DetalleCoutaTipoReg.Cod_Cana := DocLegal_Recs(ind).codCana;
 DetalleCoutaTipoReg.Cod_Sbac := DocLegal_Recs(ind).codSbac;
 DetalleCoutaTipoReg.Cod_Acce := DocLegal_Recs(ind).CodAcce;
 DetalleCoutaTipoReg.Val_Nume_Soli := DocLegal_Recs(ind).valNumeSoli;
 DetalleCoutaTipoReg.Cod_Tipo_Docu := DocLegal_Recs(ind).codTipoDocu;
 DetalleCoutaTipoReg.Val_Ejer_Docu_Inte := DocLegal_Recs(ind).valEjerDocuInte;
 DetalleCoutaTipoReg.Num_Docu_Cont_Inte := DocLegal_Recs(ind).numDocuContInte;
 DetalleCoutaTipoReg.Cod_Marc := DocLegal_Recs(ind).codMarc;
 DetalleCoutaTipoReg.Cod_Peri := DocLegal_Recs(ind).codPeri;
 DetalleCoutaTipoReg.Cod_Clie := DocLegal_Recs(ind).codClie;
 DetalleCoutaTipoReg.Cod_Tipo_Docu_Fisc := DocLegal_Recs(ind).codTipoDocuFisc;
 DetalleCoutaTipoReg.Val_Nume_Iden_Fisc := DocLegal_Recs(ind).valNumeIdenFisc;
 DetalleCoutaTipoReg.Cod_Tipo_Docu_Nnal := DocLegal_Recs(ind).codTipoDocuNnal;
 DetalleCoutaTipoReg.Val_Nume_Iden_Nnal := DocLegal_Recs(ind).valNumeIdenNnal;
 DetalleCoutaTipoReg.Fec_Emis_Fact := DocLegal_Recs(ind).fecEmisFact;
 DetalleCoutaTipoReg.Fec_Fact := DocLegal_Recs(ind).fecFact;
 DetalleCoutaTipoReg.Cod_Marc_Situ := DocLegal_Recs(ind).codMarcSitu;
 DetalleCoutaTipoReg.Cod_Tipo_Carg_Abon := DocLegal_Recs(ind).codTipoCargAbon;
 DetalleCoutaTipoReg.Cod_Proc_Crea := DocLegal_Recs(ind).codProcCrea;
 DetalleCoutaTipoReg.Cod_Subp_Crea := DocLegal_Recs(ind).codSubpCrea;
 DetalleCoutaTipoReg.Cod_Cana_Refe := DocLegal_Recs(ind).codCanaRefe;
 DetalleCoutaTipoReg.Cod_Acce_Refe := DocLegal_Recs(ind).codAcceRefe;
 DetalleCoutaTipoReg.Cod_Sbac_Refe := DocLegal_Recs(ind).codSbacRefe;
 DetalleCoutaTipoReg.Val_Nume_Soli_Refe := DocLegal_Recs(ind).valNumeSoliRefe;
 DetalleCoutaTipoReg.Ind_Afec := DocLegal_Recs(ind).indAfec;
 DetalleCoutaTipoReg.Val_Tota_Paga_Loca := l_totalLocaCabe;
 DetalleCoutaTipoReg.Tip_Soli_Impo_Posi := DocLegal_Recs(ind).tipSoliImpoPosi;
 DetalleCoutaTipoReg.Tip_Soli_Impo_Nega := DocLegal_Recs(ind).tipSoliImpoNega;
 DetalleCoutaTipoReg.Ind_Reca_Impo_Nega := DocLegal_Recs(ind).indRecaImpoNega;
 DetalleCoutaTipoReg.Soca_Oid_Soli_Cabe := l_oidSoliCabe;
 DetalleCoutaTipoReg.Oid_Soli_Posi := DetalleCuota_Recs(jnd).oidSoliPosi;
 DetalleCoutaTipoReg.Oid_Form_Pago_Capo := DetalleCuota_Recs(jnd).oidFormPagoCuot;
 DetalleCoutaTipoReg.Cod_Form_Pago_Capo := FormaPagoDetalle_Recs(knd).codFormPago;
 DetalleCoutaTipoReg.Num_Orde := FormaPagoDetalle_Recs(knd).numPosiDeta;
 DetalleCoutaTipoReg.Oid_Medi_Pago := FormaPagoDetalle_Recs(knd).oidMediPago;
 DetalleCoutaTipoReg.Cod_Medi_Pago := FormaPagoDetalle_Recs(knd).codMediPago;
 DetalleCoutaTipoReg.Imp_Cuot_Frac_Loca := l_impFracLoca;
 DetalleCoutaTipoReg.Imp_Flet_Frac_Loca := l_impFletFracLoca;
 DetalleCoutaTipoReg.Fec_Venc := l_fechaVenc;
 DetalleCoutaTipoReg.Usu_Digi := p_usuario;
 DetalleCoutaTipoReg.Fec_Digi := SYSDATE;
 DetalleCoutaTipoReg.Usu_Modi := NULL;
 DetalleCoutaTipoReg.Fec_Modi := NULL;
 DetalleCoutaTipoReg.Est_Deta_Cuot_Fopa := '1';

 Contadordetalle := contadorDetalle + 1;
 DetalleCoutaTipoTabla(Contadordetalle) := DetalleCoutaTipoReg;

 IF Contadordetalle = 5000 THEN
 IF DetalleCoutaTipoTabla.COUNT > 0 THEN
 FORALL x IN DetalleCoutaTipoTabla.FIRST .. DetalleCoutaTipoTabla.LAST
 INSERT INTO PER_DETAL_CUOTA_FORMA_PAGO
 VALUES DetalleCoutaTipoTabla(x);
 END IF;
 Contadordetalle := 0;
 DetalleCoutaTipoTabla := DetalleCoutaTipoVacio;
 END IF;
 END LOOP; -- Fin de knd
 END IF; --Fin de FormaPagoDetalle_Recs.Count > 0
 END LOOP; -- Fin de jnd
 END IF; -- Fin de DetalleCuota_Recs.COUNT > 0
 END LOOP; -- Fin de ind
 END IF; -- Fin DocLegal_Recs.COUNT > 0
 EXIT WHEN DOC_LEGAL_TEMPORAL%NOTFOUND;
 END LOOP;
 CLOSE DOC_LEGAL_TEMPORAL;

 /* Insertando en el detalle */
 IF DetalleCoutaTipoTabla.COUNT > 0 THEN
 FORALL x IN DetalleCoutaTipoTabla.FIRST .. DetalleCoutaTipoTabla.LAST
 INSERT INTO PER_DETAL_CUOTA_FORMA_PAGO
 VALUES DetalleCoutaTipoTabla(x);
 END IF;


EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_DESGL_FORMA_PAGO: '||ls_sqlerrm);
END PER_PR_DESGL_FORMA_PAGO;



PROCEDURE PER_PR_GENER_DATOS_PDT (p_codigoPais IN VARCHAR2,
 p_fechaInicial IN VARCHAR2,
 p_fechaFinal IN VARCHAR2,
 v_tabla OUT TIPOCURSOR) IS
/***************************************************************************************************
Descripcion : Obtiene la generacion de Datos para el Archivo
Fecha Creacion : 24/12/2006
Fecha Modificacion : 24/25/2006
Parametros Entrada : p_codigoPais : Codigo del Pais del Usuario Logueado (ejm: PE es de PERU)
 p_fechaInicial : Fecha Inicial
 p_fechaFinal : Fecha Final
 p_usuDigi : Usuario Digitacion
Parametros Salida : p_cantidadRegistros: Cantidad de Registros Procesados
Autor : Richard de los Reyes
Version : Final (Beta|Final)
Cambios Importantes : Inclusion del comentario
****************************************************************************************************/
l_cons PER_PERCE_CONSO.COD_CLIE%TYPE;
l_seriCompPerc PER_PERCE_CONSO.SER_COPE%TYPE;
l_numeCompPerc PER_PERCE_CONSO.NUM_COPE%TYPE;
l_seriCompPercAuxi PER_PERCE_CONSO.SER_COPE%TYPE;
l_numeCompPercAuxi PER_PERCE_CONSO.NUM_COPE%TYPE;
l_nomCons VARCHAR2(104);
l_monApli NUMBER;
l_monDocu NUMBER;
l_tipDocuIden PER_TIPO_DOCUM_IDENT_LEGAL.COD_HOMO%TYPE;
l_numDocuIden PER_PERCE_CONSO.NUM_DOID%TYPE;
l_razSoci VARCHAR2(104);
l_valApelPate MAE_CLIEN.VAL_APE1%TYPE;
l_valApelMate MAE_CLIEN.VAL_APE2%TYPE;
l_valNomb VARCHAR2(52);
l_indGeneCredFisc VARCHAR2(1);
l_indClieList VARCHAR2(1);
l_monPagoPerc NUMBER;
l_tipCompPago PER_TIPO_DOCUM_IDENT_LEGAL.COD_HOMO%TYPE;

TYPE tipMontPerc IS TABLE OF PER_PERCE_CONSO.MON_PERC%TYPE;
tipMontPerc_Recs tipMontPerc;

BEGIN

 l_monApli:=0;
 DBMS_OUTPUT.put_line ('Al Inicio del SP');

 SELECT MON_PERC BULK COLLECT INTO tipMontPerc_Recs
 FROM PER_PERCE_CONSO
 WHERE FEC_COPE BETWEEN TO_DATE(p_fechaInicial,'YYYYMMDD') AND TO_DATE(p_fechaFinal,'YYYYMMDD')
 GROUP BY TIP_CLIE, COD_CLIE, SER_COPE, NUM_COPE;

 FOR ind IN tipMontPerc_Recs.FIRST .. tipMontPerc_Recs.LAST LOOP

 IF (l_seriCompPercAuxi = l_seriCompPerc) AND (l_numeCompPercAuxi = l_numeCompPerc) THEN
 l_monApli:=l_monApli + tipMontPerc_Recs(ind);
 ELSE
 l_monApli:=0;
 END IF;

 END LOOP;

 OPEN V_TABLA FOR
 SELECT
 (SELECT COD_HOMO FROM PER_TIPO_DOCUM_IDENT_LEGAL B
 WHERE B.PAIS_COD_PAIS = p_codigoPais
 AND COD_CLAS = 'DI'
 AND COD_AHOM = A.TIP_DOID) TIP_DOID,
 NUM_DOID,
 REPLACE(UPPER((SELECT VAL_APE1||' '||VAL_APE2||', '||VAL_NOM1||' '||VAL_NOM2 FROM MAE_CLIEN WHERE COD_CLIE = A.COD_CLIE AND ROWNUM=1)), '?', 'N') RAZ_SOCI,
 REPLACE(UPPER((SELECT VAL_APE1 FROM MAE_CLIEN WHERE COD_CLIE = A.COD_CLIE AND ROWNUM=1)), '?', 'N') APE_PATE,
 REPLACE(UPPER((SELECT VAL_APE2 FROM MAE_CLIEN WHERE COD_CLIE = A.COD_CLIE AND ROWNUM=1)), '?', 'N') APE_MATE,
 REPLACE(UPPER((SELECT VAL_NOM1||' '||VAL_NOM2 FROM MAE_CLIEN WHERE COD_CLIE = A.COD_CLIE AND ROWNUM=1)), '?', 'N') VAL_NOMB,
 SER_COPE,
 NUM_COPE,
 FEC_COPE,
 '1' IND_DCFI,
 '1' IND_CLLI,
 MON_PERC MON_PAGO,
 (SELECT COD_HOMO FROM PER_TIPO_DOCUM_IDENT_LEGAL
 WHERE PAIS_COD_PAIS = p_codigoPais
 AND COD_CLAS = 'TC'
 AND COD_AHOM = A.TIP_DOLE) TIP_COPA,
 SER_DOLE,
 NUM_DOLE,
 FEC_DOLE,
 MON_PAGO
 FROM PER_PERCE_CONSO A
 WHERE FEC_COPE BETWEEN TO_DATE(p_fechaInicial,'YYYYMMDD') AND TO_DATE(p_fechaFinal,'YYYYMMDD');

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_DATOS_PDT: '||ls_sqlerrm);
END PER_PR_GENER_DATOS_PDT;

/**************************************************************************
Descripcion : Procedimiento que efectua Procesar Movimiento
Fecha Creacion : 19/09/2006
Parametros Entrada :
 psCodPais : Codigo de Pais
 psTipoOrigen : Tipo de Origen de Datos
 psNumeroLote : Numero de Lote
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PE_PROCE_MOVIM(
 p_CodPais IN VARCHAR2,
 p_tipoOrigen IN VARCHAR2,
 p_NumeroLote IN VARCHAR2,
 p_NumeroLoteExterno OUT VARCHAR2)
IS
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(150);
 lsNumeroLoteExterno VARCHAR2(12);
 lsCodSoci per_movim_banca_cabec.cod_soci%TYPE;

 /* Cursor de Deuda ordenado por fecha de facturacion */
 CURSOR c_deuda01(
 ps_cod_Clie VARCHAR2,
 ps_cod_soci VARCHAR2,
 pn_pago NUMBER,
 pn_oidAbono NUMBER)
 IS
 WITH TEMPORAL AS
 (SELECT Y.COD_MARC_SITU
 FROM CCC_MARCA_TIPO_ABONO X,
 CCC_MARCA_SITUA Y
 WHERE
 X.TASP_OID_TIPO_ABON_SUBP = pn_oidAbono AND
 X.IND_ENTR_SALI = 'E' AND
 Y.OID_MARC_SITU = X.MASI_OID_MARC_SALI
 )
 SELECT A.*
 FROM
 PER_CUENT_CORRI_DOCLE A,
 TEMPORAL B
 WHERE
 A.PAIS_COD_PAIS = p_codpais AND
 A.COD_CLIE = ps_cod_clie AND
 A.COD_SOCI = ps_cod_soci AND
 A.COD_MARC_SITU = B.COD_MARC_SITU AND
 ((pn_pago < 0 AND a.imp_cuot_pend < 0) OR (pn_pago > 0 AND a.imp_cuot_pend > 0))
 ORDER BY a.fec_fact;

 /* Cursor de Deuda ordenado por fecha de emision */
 CURSOR c_deuda02(
 ps_cod_Clie VARCHAR2,
 ps_cod_soci VARCHAR2,
 pn_pago NUMBER,
 pn_oidAbono NUMBER)
 IS
 WITH TEMPORAL AS
 (SELECT Y.COD_MARC_SITU
 FROM CCC_MARCA_TIPO_ABONO X,
 CCC_MARCA_SITUA Y
 WHERE
 X.TASP_OID_TIPO_ABON_SUBP = pn_oidAbono AND
 X.IND_ENTR_SALI = 'E' AND
 Y.OID_MARC_SITU = X.MASI_OID_MARC_SALI
 )
 SELECT A.*
 FROM
 PER_CUENT_CORRI_DOCLE A,
 TEMPORAL B
 WHERE
 A.PAIS_COD_PAIS = p_CodPais AND
 A.COD_CLIE = ps_cod_clie AND
 A.COD_SOCI = ps_cod_soci AND
 A.COD_MARC_SITU = B.COD_MARC_SITU AND
 ((pn_pago < 0 AND a.imp_cuot_pend < 0) OR (pn_pago > 0 AND a.imp_cuot_pend > 0))
 ORDER BY a.fec_emis_docu;

 /* Entidad de Detalle de movimientos */
 CURSOR c_movimiento
 IS
 SELECT *
 FROM PER_MOVIM_BANCA_DETAL A
 WHERE
 A.PAIS_COD_PAIS = p_CodPais AND
 A.TIOR_TIPO_ORIG_DATO = p_tipoOrigen AND
 A.MOCA_NUM_LOTE_INTE = p_NumeroLote AND
 A.STA_MOVI = 'P'
 ORDER BY A.PAIS_COD_PAIS, A.TIOR_TIPO_ORIG_DATO, A.MOCA_NUM_LOTE_INTE, A.CON_TRAN ;

 /*Pagos Bancarios Realizados*/
  CURSOR c_pagos
  IS
  SELECT mc.oid_clie
  FROM
     per_movim_banca_detal a,
     mae_clien mc
  WHERE a.PAIS_COD_PAIS = p_CodPais
    AND a.cod_cons = mc.cod_clie
    AND a.TIOR_TIPO_ORIG_DATO = p_tipoOrigen
    AND a.MOCA_NUM_LOTE_INTE = p_NumeroLote
    AND a.STA_MOVI = 'C';

 tablaMovimientoBancario per_pkg_proce_perce.TABLA_PER_MOVIM_BANCA_DETAL;
 regMovimientoBancario PER_MOVIM_BANCA_DETAL%ROWTYPE;
 tablaCuentaACancelar per_pkg_proce_perce.TABLA_PER_CUENT_CORRI_DOCLE;
 regCuentaACancelar per_cuent_corri_docle%ROWTYPE;
 ls_codMarcaSituSalida ccc_marca_situa.cod_marc_situ%TYPE;

 regMovimientoCabecera             PER_MOVIM_BANCA_CABEC%ROWTYPE;
 regParamAbonoExterno              PER_PARAM_ABONO_EXTER%ROWTYPE;
 ls_desTipoOrigen                  PER_TIPO_ORIGE_DATOS.DES_TIPO_ORIG_DATO%TYPE;
 ln_oidpais                        SEG_PAIS.OID_PAIS%TYPE;
 ln_oidCanal                       SEG_CANAL.OID_CANA%TYPE;
 ls_codProc                        CCC_PROCE.COD_PROC%TYPE;
 ls_codSubp                        CCC_SUBPR.COD_SUBP%TYPE;
 regTipoAbonoSubpr                 CCC_TIPO_ABONO_SUBPR%ROWTYPE;
 regMarcaAbonoSalida               CCC_MARCA_TIPO_ABONO%ROWTYPE;
 ln_idproc                         CCC_PROCE.OID_PROC%TYPE;
 ln_idsubproc                      CCC_SUBPR.OID_SUBP%TYPE;
 ln_val_indi_actu                  CCC_SUBPR.VAL_INDI_ACTU_CUOT%TYPE;
 ls_tipo_Cargo_Abon                CCC_TIPO_CARGO_ABONO.COD_TIPO_CARG_ABON%TYPE;
 registroSoliMone                  PER_SOLIC_MONET%ROWTYPE;
 lv_ind_carg_banc                  ccc_param_gener.val_para%TYPE;
 ln_IndEmis                        NUMBER;
 lnContador                        NUMBER;
 ln_importe_no_consumido           NUMBER;
 W_FILAS                           NUMBER:=1000;
 lnMontoIni                        NUMBER;
 ls_cod_peri                       SEG_PERIO_CORPO.COD_PERI%TYPE;
 lnCorreBancario                   NUMBER;
 lnIndicadorLiquidacion            CCC_PAIS_SOCIE_PARAM.Ind_Liqu_Banc_Auto%TYPE;
 lnIndicadorPercepcion             CCC_PAIS_SOCIE_PARAM.Ind_Gene_Perc_Auto%TYPE;
 lnIndiApliBancPerc                CCC_PARAM_GENER.VAL_PARA%TYPE;
 lv_vali_soli_mone                 NUMBER(12):=0;

 lv_tab_oid_clie                   gt_tab_oid_clie;

BEGIN
 /* Invocacion a Generacion de Cta Cte */
 PER_PR_CABEC_DETAL_DOLSO (p_codpais, USER, '06', 'PER-P1',lsNumeroLoteExterno);
 p_NumeroLoteExterno := lsNumeroLoteExterno;

 /* Obtenemos correlativo bancario */
 lnCorreBancario := PER_FN_DEVUE_CORRE_REGIS_PROGR(p_codpais,'05');

 /* Recuperamos datos de la cabecera de Movimientos */
 SELECT *
 INTO regMovimientoCabecera
 FROM PER_MOVIM_BANCA_CABEC A
 WHERE
 A.PAIS_COD_PAIS = p_CodPais AND
 A.TIOR_TIPO_ORIG_DATO = p_tipoOrigen AND
 A.NUM_LOTE_INTE = p_NumeroLote AND
 A.STA_LOTE = 'P';

 /* Obteniendo Tipo Origen de Datos */
 SELECT A.DES_TIPO_ORIG_DATO
 INTO ls_desTipoOrigen
 FROM PER_TIPO_ORIGE_DATOS A
 WHERE
 A.COD_TIPO_ORIG_DATO = p_tipoOrigen;

 /* Obtenemos parametros Abonos externos */
 SELECT *
 INTO regParamAbonoExterno
 FROM PER_PARAM_ABONO_EXTER A
 WHERE
 A.PAIS_COD_PAIS = p_CodPais AND
 A.TIOR_TIPO_ORIG_DATO = p_tipoOrigen AND
 A.CUE_CORR_BANC = regMovimientoCabecera.Cod_Ccba;

 /* obtenemos id de pais */
 ln_oidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(p_CodPais);

 /* obtenemos id de canal */
 ln_oidCanal := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL('VD');

 ls_CodProc := 'CCC002';
 ln_idproc := gen_pkg_gener.gen_fn_devuelve_id_proceso(Ln_Oidpais, ls_CodProc);
 IF p_tipoOrigen = '01' OR p_tipoOrigen = '02' OR p_tipoOrigen = '09' THEN
 ls_codSubp := '1';
 END IF;
 IF p_tipoOrigen = '03' OR p_tipoOrigen = '04' THEN
 ls_codSubp := '2';
 END IF;

 /* obtenemos el subproceso */
 SELECT a.oid_subp, a.val_indi_actu_cuot
 INTO ln_idsubproc, ln_val_indi_actu
 FROM CCC_SUBPR A
 WHERE
 a.ccpr_oid_proc = ln_idproc AND
 a.cod_subp = ls_codSubp;

 /* Obteniendo tipo de abono */
 SELECT *
 INTO regTipoAbonoSubpr
 FROM ccc_tipo_abono_subpr a
 WHERE
 a.subp_oid_subp = ln_idsubproc AND
 ROWNUM < 2;

 /* Obteniendo la Marcas de abono de Salida.
 Se asume solo un registro*/
 SELECT *
 INTO regMarcaAbonoSalida
 FROM ccc_marca_tipo_abono a
 WHERE
 a.tasp_oid_tipo_abon_subp = regTipoAbonoSubpr.Oid_Tipo_Abon_Subp AND
 a.ind_entr_sali = 'S' AND
 rownum < 2;

 /* Obteniendo Tipo de Cargo de abono */
 SELECT a.cod_tipo_carg_abon
 INTO ls_tipo_Cargo_Abon
 FROM ccc_tipo_cargo_abono a
 WHERE
 a.oid_tipo_carg_abon = Regtipoabonosubpr.Tcab_Oid_Tcab;

 /* Obteniendo Marca de Situacion de Salida
 Se asume solo un registro*/
 SELECT a.cod_marc_situ
 INTO ls_CodMarcaSituSalida
 FROM
 ccc_marca_situa a
 WHERE
 a.oid_marc_situ = regMarcaAbonoSalida.Masi_Oid_Marc_Sali;

 /* Viendo criterio por cual debemos ordenar el Select de Coutas */
 ln_IndEmis := PER_FN_DEVUE_INDIC_EMIS_VENC(ln_oidpais);

 /* Recorriendo los movimientos respectivos */
 OPEN c_movimiento;
 LOOP
 FETCH c_movimiento BULK COLLECT INTO TablaMovimientoBancario LIMIT W_FILAS;
 IF TablaMovimientoBancario.COUNT > 0 THEN
 FOR ind IN TablaMovimientoBancario.FIRST .. TablaMovimientoBancario.LAST LOOP
 regMovimientoBancario := TablaMovimientoBancario(ind);
 lnCorreBancario := lnCorreBancario + 1;

 IF (ln_IndEmis = 0) THEN
 /* Recorriendo Registros de las Coutas x Fecha de Facturacion */
 OPEN c_deuda01(
 regMovimientoBancario.Cod_Cons,
 regmovimientocabecera.cod_soci,
 regMovimientoBancario.Imp_Pago_Pend,
 regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 LOOP
 FETCH c_deuda01 BULK COLLECT INTO tablaCuentaACancelar LIMIT W_FILAS;
 IF tablaCuentaACancelar.COUNT > 0 THEN
 FOR jnd IN tablaCuentaACancelar.FIRST .. tablaCuentaACancelar.LAST LOOP
 regCuentaACancelar := tablaCuentaACancelar(jnd);
 IF regMovimientoBancario.Imp_Pago_Pend <> 0 THEN
 PER_PKG_PROCE_PERCE.PER_PR_ACTU_IMPOR_MBANC
 ( ln_val_indi_actu,
 ln_oidpais,
 ln_oidCanal,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 regParamAbonoExterno.Tip_Soli_Perc,
 regParamAbonoExterno.Tip_Soli_Apli_Pago_Exve,
 p_tipoOrigen,
 lsNumeroLoteExterno,
 regMovimientoBancario,
 regCuentaACancelar,
 lnCorreBancario);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deuda01%NOTFOUND;
 END LOOP;
 CLOSE c_deuda01;
 ELSE
 /* Recorriendo Registros de las Coutas x Fecha de Emision */
 OPEN c_deuda02(
 regMovimientoBancario.Cod_Cons,
 regmovimientocabecera.cod_soci,
 regMovimientoBancario.Imp_Pago_Pend,
 regTipoAbonoSubpr.Oid_Tipo_Abon_Subp);
 LOOP
 FETCH c_deuda02 BULK COLLECT INTO tablaCuentaACancelar LIMIT W_FILAS;
 IF tablaCuentaACancelar.COUNT > 0 THEN
 FOR jnd IN tablaCuentaACancelar.FIRST .. tablaCuentaACancelar.LAST LOOP
 regCuentaACancelar := tablaCuentaACancelar(jnd);
 IF regMovimientoBancario.Imp_Pago_Pend <> 0 THEN
 PER_PKG_PROCE_PERCE.PER_PR_ACTU_IMPOR_MBANC
 ( ln_val_indi_actu,
 ln_oidpais,
 ln_oidCanal,
 ls_CodMarcaSituSalida,
 ls_tipo_Cargo_Abon,
 regParamAbonoExterno.Tip_Soli_Perc,
 regParamAbonoExterno.Tip_Soli_Apli_Pago_Exve,
 p_tipoOrigen,
 lsNumeroLoteExterno,
 regMovimientoBancario,
 regCuentaACancelar,
 lnCorreBancario);
 END IF;
 END LOOP;
 END IF;
 EXIT WHEN c_deuda02%NOTFOUND;
 END LOOP;
 CLOSE c_deuda02;
 END IF;

 /* Registrar solicitudes monetarias de pagos en exceso */
 IF round(regMovimientoBancario.Imp_Pago_Pend,2) > 0 THEN
 ls_cod_peri := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(ln_oidpais, 'T', ln_oidCanal);

 registroSoliMone.Pais_Cod_Pais := p_CodPais;
 Registrosolimone.Tior_Tipo_Orig_Dato := p_tipoOrigen;
 registroSoliMone.Num_Lote := lsNumeroLoteExterno;
 registroSoliMone.Cod_Peri := ls_cod_peri;
 registroSoliMone.Cod_Clie := regMovimientoBancario.Cod_Cons;
 registroSoliMone.Fec_Fact := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := abs(regMovimientoBancario.Imp_Pago_Pend);
 registroSoliMone.Cod_Cana_Refe := NULL;
 registroSoliMone.Cod_Acce_Refe := NULL;
 registroSoliMone.Cod_Sbac_Refe := NULL;
 registroSoliMone.Num_Docu_Refe := NULL;
 registroSoliMone.Cod_Tipo_Soli := regParamAbonoExterno.Tip_Soli_Pago_Exce_Nega;
 registroSoliMone.Cod_Acce := 'GZ';
 registroSoliMone.Cod_Sbac := '000';
 registroSoliMone.Tip_Posi := 'PE';
 registroSoliMone.Val_Cant := -1;
 registroSoliMone.Num_Lote_Gene:= p_numerolote;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Cor_Soli_Mone := PER_PKG_PROCE_PERCE.PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := 0;
 registroSoliMone.Fec_Digi := SYSDATE;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_Agru := NULL;
 registroSoliMone.Con_Tran := regMovimientoBancario.Con_Tran;

 /* Insertando registro en Solicitudes Monetarias */
 PER_PKG_PROCE_PERCE.INSERT_PER_SOLI_MONE(RegistroSoliMone);

 /* Actualizando estado del Movimiento Bancario */
 UPDATE PER_MOVIM_BANCA_DETAL A
 SET
 A.STA_MOVI = 'C'
 WHERE
 A.PAIS_COD_PAIS = regMovimientoBancario.PAIS_COD_PAIS AND
 A.TIOR_TIPO_ORIG_DATO = regMovimientoBancario.TIOR_TIPO_ORIG_DATO AND
 A.MOCA_NUM_LOTE_INTE= regMovimientoBancario.MOCA_NUM_LOTE_INTE AND
 A.CON_TRAN = regMovimientoBancario.CON_TRAN;

 END IF;

 END LOOP;
 END IF;
 EXIT WHEN c_movimiento%NOTFOUND;
 END LOOP;
 CLOSE c_movimiento;

 /* Actualizando correlativos de Solicitud Monetaria */
 PER_PR_CORRE_MONET_AGRUP_MOVIM(p_CodPais, lsNumeroLoteExterno, p_tipoOrigen, '05');

 /* Actualizando estado del movimiento bancario cabecera */
 SELECT COUNT(1)
 INTO lnContador
 FROM PER_MOVIM_BANCA_DETAL A
 WHERE
 A.PAIS_COD_PAIS = p_CodPais AND
 A.TIOR_TIPO_ORIG_DATO = p_tipoOrigen AND
 A.MOCA_NUM_LOTE_INTE = p_numerolote AND
 A.STA_MOVI = 'P';

 IF lnContador = 0 THEN
 UPDATE PER_MOVIM_BANCA_CABEC A
 SET
 A.STA_LOTE= 'C'
 WHERE
 A.PAIS_COD_PAIS = p_CodPais AND
 A.TIOR_TIPO_ORIG_DATO = p_tipoOrigen AND
 A.NUM_LOTE_INTE = p_numerolote;
 END IF;

   /* Genera BAN-1*/
   PER_PR_CARGA_MOVIM_BANCA(p_CodPais, p_tipoOrigen, p_numerolote);

   SELECT pc.cod_soci
   INTO lsCodSoci
   FROM per_movim_banca_cabec pc
   WHERE pc.pais_cod_pais = p_CodPais
   AND pc.tior_tipo_orig_dato = p_tipoOrigen
   AND pc.num_lote_inte = p_NumeroLote;

   SELECT cp.ind_liqu_banc_auto, cp.ind_gene_perc_auto
   INTO lnIndicadorLiquidacion , lnIndicadorPercepcion
   FROM ccc_pais_socie_param cp
   WHERE cp.cod_pais=p_CodPais;

   IF lnIndicadorLiquidacion=1 THEN
      --CCC_PKG_PROCE.CCC_PR_LIQUI_LOTE_BANCA(p_CodPais,lsCodSoci,p_NumeroLote,USER);
      CCC_PKG_PROCE.CCC_PR_REGIS_LOTE_BANCA_PERCE(p_CodPais,lsCodSoci,p_NumeroLote,USER);
   END IF;

   lv_ind_carg_banc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorCargaBancoTotal');

   SELECT COUNT(*)
   INTO lv_vali_soli_mone
   FROM  per_solic_monet psm
   WHERE psm.pais_cod_pais = p_CodPais
     AND  psm.num_lote = lsNumeroLoteExterno
      AND NOT EXISTS (
      SELECT 1
      FROM per_param_exclu_tipo_solic p
      WHERE p.cod_tipo_soli = psm.cod_tipo_soli);
      
   IF lnIndicadorPercepcion=1 AND lv_ind_carg_banc IS NULL AND lv_vali_soli_mone > 0 THEN
      ccc_pkg_proce.CCC_PR_GENER_CUENT_CORRI_PERCE(p_CodPais,lsCodSoci,lsNumeroLoteExterno);
   END IF;

   lnIndiApliBancPerc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorAplicacionBancoPercepcion');

   IF lnIndiApliBancPerc = 1 THEN

      -- Aplicacion en Cuenta Corriente --
      OPEN c_pagos;
      LOOP
         FETCH c_pagos BULK COLLECT INTO lv_tab_oid_clie LIMIT W_FILAS;
         IF lv_tab_oid_clie.COUNT > 0 THEN
            FOR x IN lv_tab_oid_clie.FIRST .. lv_tab_oid_clie.LAST LOOP
               CCC_PKG_PROCE.CCC_PR_APLIC_ABONO_CARGO_CLIEN(lv_tab_oid_clie(x));
         END LOOP;
         END IF;

         EXIT WHEN c_pagos%NOTFOUND;

      END LOOP;
      CLOSE c_pagos;

   END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PE_PROCE_MOVIM: '||ls_sqlerrm);

END PER_PE_PROCE_MOVIM;

/**************************************************************************
Descripcion : Procedimiento auxiliar de Procesar Movimiento
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_ACTU_IMPOR_MBANC
( pn_val_indi_actu NUMBER,
 pn_Oidpais NUMBER,
 pn_oidCanal NUMBER,
 ps_codMarcaSalida VARCHAR2,
 ps_tipo_Cargo_Abon VARCHAR2,
 ps_tipo_soli VARCHAR2,
 ps_tipo_soli_exceso_venta VARCHAR2,
 psTipoOrigen VARCHAR2,
 psNumeroLote VARCHAR2,
 pRegMovimientoDetalle IN OUT PER_MOVIM_BANCA_DETAL%ROWTYPE,
 pregCuentaACancelar PER_CUENT_CORRI_DOCLE%ROWTYPE,
 pnCorrelativo NUMBER )
IS
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(150);

 ln_importe_consumido NUMBER(12,2);
 ln_importe_no_consumido NUMBER(12,2);
 ln_importe_cancelado_per NUMBER(12,2);
 ln_importe_cancelado NUMBER(12,2);
 ln_importe_cancelado_i_per NUMBER(12,2);
 ln_importe_cancelado_a_per NUMBER(12,2);
 ln_imp_couta NUMBER(12,2);
 ln_imp_cout_paga NUMBER(12,2);
 ln_imp_paga_i_per NUMBER(12,2);
 ln_imp_paga_a_per NUMBER(12,2);
 ln_imp_paga_per NUMBER(12,2);

 ln_couta NUMBER(12,2);
 ln_couta_pagado NUMBER(12,2);
 ln_couta_pendiente NUMBER(12,2);
 ln_pagado_i_per NUMBER(12,2);
 ln_pagado_a_per NUMBER(12,2);
 ln_pagado_per NUMBER(12,2);
 ld_fecha DATE;
 ls_fecha VARCHAR2(10);
 ls_cod_peri SEG_PERIO_CORPO.COD_PERI%TYPE;
 ln_oidMarca SEG_MARCA.OID_MARC%TYPE;
 ls_marca_Salida CCC_MARCA_SITUA.COD_MARC_SITU%TYPE;
 registroPago PER_REGIS_PAGOS%ROWTYPE;
 registroSoliMone PER_SOLIC_MONET%ROWTYPE;

BEGIN
 /* obtieniendo periodo */
 ls_cod_peri := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(pn_Oidpais, pregCuentaACancelar.Cod_Marc, pn_oidCanal);
 IF ls_cod_peri IS NULL THEN
 RETURN;
 END IF;

 /* Inicializando valores correspondientes a importes*/
 IF pn_val_indi_actu = 1 THEN --En caso sea SI
 IF ABS(Pregcuentaacancelar.Imp_Cuot_pend + Pregcuentaacancelar.Imp_Pend_Perc) < abs(pRegMovimientoDetalle.Imp_Pago_Pend) THEN
 ln_importe_cancelado := abs(Pregcuentaacancelar.Imp_Cuot_pend) + abs(Pregcuentaacancelar.Imp_Pend_Perc);
 ELSE
 ln_importe_cancelado := abs(pRegMovimientoDetalle.Imp_Pago_Pend);
 END IF;

 IF abs(Pregcuentaacancelar.Imp_Pend_Afec_Perc) + abs(Pregcuentaacancelar.Imp_Pend_Perc) <= ln_importe_cancelado THEN
 ln_importe_cancelado_a_per := abs(Pregcuentaacancelar.Imp_Pend_Afec_Perc);
 ln_importe_cancelado_per := round(((ln_importe_cancelado_a_per + abs(Pregcuentaacancelar.Imp_Pend_Perc)) * Pregcuentaacancelar.Fac_Calc_Perc), 2);
 IF abs(Pregcuentaacancelar.Imp_Pend_Afec_Perc) + abs(Pregcuentaacancelar.Imp_Pend_Perc) + abs(Pregcuentaacancelar.Imp_Pend_Inaf_Perc) = ln_importe_cancelado THEN
 ln_importe_cancelado_a_per := abs(Pregcuentaacancelar.Imp_Pend_Afec_Perc);
 ln_importe_cancelado_per := abs(Pregcuentaacancelar.Imp_Pend_Perc);
 END IF;
 ELSE
 ln_importe_cancelado_per := round((pRegMovimientoDetalle.Imp_Pago_Pend * Pregcuentaacancelar.Fac_Calc_Perc), 2);
 ln_importe_cancelado_a_per := ln_importe_cancelado - ln_importe_cancelado_per;
 END IF;
 ln_importe_cancelado_i_per := ln_importe_cancelado - (ln_importe_cancelado_a_per + ln_importe_cancelado_per);
 IF ln_importe_cancelado_i_per < 0 THEN
 ln_importe_cancelado_i_per := 0;
 END IF;

 ELSE -- En caso sea NO
 ln_importe_cancelado := 0;
 ln_importe_cancelado_i_per := 0;
 ln_importe_cancelado_a_per := 0;
 ln_importe_cancelado_per := 0;
 END IF;

 /* Actualizando montos de la Cta de Cancelar */
 ln_couta_pagado := Pregcuentaacancelar.Imp_Cuot_Paga + ln_importe_cancelado - ln_importe_cancelado_per;
 ln_couta_pendiente := Pregcuentaacancelar.Imp_Cuot_Pend - ln_importe_cancelado + ln_importe_cancelado_per;
 ln_pagado_i_per := Pregcuentaacancelar.Imp_Paga_Inaf_Perc + ln_importe_cancelado_i_per;
 ln_pagado_a_per := Pregcuentaacancelar.Imp_Paga_Afec_Perc + ln_importe_cancelado_a_per;
 ln_pagado_per := Pregcuentaacancelar.Imp_Paga_Perc + ln_importe_cancelado_per;
 IF (pn_val_indi_actu = 1 AND abs(Pregcuentaacancelar.Imp_Cuot) = abs(ln_couta_pagado)) OR
 (pn_val_indi_actu = 0) THEN
 ls_marca_Salida := ps_codMarcaSalida;
 ELSE
 ls_marca_Salida := Pregcuentaacancelar.cod_marc_situ;
 END IF;

 UPDATE per_cuent_corri_docle a
 SET
 a.IMP_CUOT_PAGA = ln_couta_pagado,
 a.IMP_CUOT_PEND = ln_couta_pendiente,

 a.IMP_PAGA_INAF_PERC = ln_pagado_i_per,
 a.IMP_PEND_INAF_PERC = A.IMP_INAF_PERC - ln_pagado_i_per ,

 a.IMP_PAGA_AFEC_PERC = ln_pagado_a_per,
 a.IMP_PEND_AFEC_PERC = A.IMP_AFEC_PERC - ln_pagado_a_per ,

 a.IMP_PAGA_PERC = ln_pagado_per,
 a.IMP_PEND_PERC = A.IMP_PERC_PERC - ln_pagado_per,

 a.COD_MARC_SITU = trim(ls_marca_Salida),
 a.USU_MODI = USER,
 a.FEC_MODI = SYSDATE
 WHERE
 a.pais_cod_pais = Pregcuentaacancelar.pais_cod_pais AND
 a.cor_cuen_corr_dole = Pregcuentaacancelar.cor_cuen_corr_dole;

 /* Actualizar Recaudo Bancario tratado */
 ln_importe_no_consumido := pRegMovimientoDetalle.Imp_Pago_Pend - ln_importe_cancelado;
 ln_importe_consumido := pRegMovimientoDetalle.Val_Pago - ln_importe_no_consumido;

 UPDATE PER_MOVIM_BANCA_DETAL A
 SET
 A.STA_MOVI = 'C',
 A.IMP_PAGO_APLI = ln_importe_consumido,
 A.IMP_PAGO_PEND = ln_importe_no_consumido,
 A.IMP_RECA_GENE = A.IMP_RECA_GENE + (ln_importe_cancelado - ln_importe_cancelado_per),
 A.IMP_PERC = A.IMP_PERC + ln_importe_cancelado_per
 WHERE
 A.PAIS_COD_PAIS = pRegMovimientoDetalle.PAIS_COD_PAIS AND
 A.TIOR_TIPO_ORIG_DATO = pRegMovimientoDetalle.TIOR_TIPO_ORIG_DATO AND
 A.MOCA_NUM_LOTE_INTE= pRegMovimientoDetalle.MOCA_NUM_LOTE_INTE AND
 A.CON_TRAN = pRegMovimientoDetalle.CON_TRAN;

 -- COMMIT;

 /* Obteniendo Registro de la Cuota de Cancelacion */
 SELECT *
 INTO pRegMovimientoDetalle
 FROM PER_MOVIM_BANCA_DETAL A
 WHERE
 A.PAIS_COD_PAIS = pRegMovimientoDetalle.PAIS_COD_PAIS AND
 A.TIOR_TIPO_ORIG_DATO = pRegMovimientoDetalle.TIOR_TIPO_ORIG_DATO AND
 A.MOCA_NUM_LOTE_INTE= pRegMovimientoDetalle.MOCA_NUM_LOTE_INTE AND
 A.CON_TRAN = pRegMovimientoDetalle.CON_TRAN;

 /* Seteando valores para insertar en Registro de Pagos */
 IF ln_importe_cancelado <> 0 THEN
 Registropago.Pais_Cod_Pais := pregCuentaACancelar.Pais_Cod_Pais;
 Registropago.Cod_Soci := pregCuentaACancelar.Cod_Soci;
 Registropago.Cod_Cana := pregCuentaACancelar.Cod_Cana;
 Registropago.Cod_Acce := pregCuentaACancelar.Cod_Acce;
 Registropago.Cod_Sbac := pregCuentaACancelar.Cod_Sbac;
 Registropago.Num_Soco := pregCuentaACancelar.Num_Soli_Cons;
 Registropago.Tip_Dole := pregCuentaACancelar.Tip_Docu_Lega;
 Registropago.EJE_DINT := pregCuentaACancelar.Eje_Docu_Inte;
 Registropago.NUM_DINT := pregCuentaACancelar.Num_Docu_Inte;
 Registropago.Num_Cuot := pregCuentaACancelar.Num_Orde_Cuot;
 Registropago.Sec_Abon := PER_PKG_PROCE_PERCE.PER_FN_SGTE_SEC_ABON(RegistroPago);
 Registropago.FEC_DOLE := pregCuentaACancelar.Fec_Emis_Docu;
 Registropago.TIP_ABON := ps_tipo_Cargo_Abon;
 Registropago.Num_Abon := NULL;
 Registropago.Cod_Cons := pregCuentaACancelar.Cod_Clie;
 IF (Registropago.Tip_Dole = '001' OR
 Registropago.Tip_Dole = '002' OR
 Registropago.Tip_Dole = '003' OR
 Registropago.Tip_Dole = '021' OR
 Registropago.Tip_Dole = '022') THEN
 Registropago.Tip_Doid := pregCuentaACancelar.Tip_Docu_Iden_Fisc;
 Registropago.Num_Doid := pregCuentaACancelar.Num_Iden_Fisc;
 ELSE
 Registropago.Tip_Doid := pregCuentaACancelar.Tip_Docu_Iden_Nnal;
 Registropago.Num_Doid := pregCuentaACancelar.Num_Iden_Nnal;
 END IF;
 Registropago.Cod_Peri := ls_cod_peri;
 Registropago.Mon_Pago := abs(ln_importe_cancelado_a_per + ln_importe_cancelado_per);
 Registropago.Mon_Perc := abs(ln_importe_cancelado_per);
 Registropago.Mon_Todl := pregCuentaACancelar.Imp_Tota_Docu;
 Registropago.Por_Perc := pregCuentaACancelar.Por_Perc;
 Registropago.Fac_Cape := pregCuentaACancelar.Fac_Calc_Perc;
 Registropago.Fec_Pago := pRegMovimientoDetalle.Fec_Pago; --SYSDATE;
 Registropago.Fec_Proc := SYSDATE;
 IF (Registropago.Mon_Perc > 0) THEN
 Registropago.IND_REPE := 'S';
 ELSE
 Registropago.IND_REPE := 'N';
 END IF;
 Registropago.Sta_Repe := '2';
 Registropago.FEC_DIGI := SYSDATE;
 Registropago.Usu_digi := USER;
 Registropago.Est_Repa := '1';
 Registropago.Cor_Repa := PER_PKG_PROCE_PERCE.PER_FN_SGTE_CORRE_REG_PAGO();

 /* Insertando registro en Registro de Pagos */
 PER_PKG_PROCE_PERCE.INSERT_PER_REGIS_PAGOS(Registropago);
 END IF;

 /* Seteando valores para insertar en Solicitudes Monetarias */
 IF ln_importe_cancelado_per <> 0 AND pn_val_indi_actu = 1 THEN
 registroSoliMone.Pais_Cod_Pais := pregCuentaACancelar.Pais_Cod_Pais;
 Registrosolimone.Tior_Tipo_Orig_Dato := pstipoorigen;
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := ls_cod_peri;
 registroSoliMone.Cod_Clie := pRegMovimientoDetalle.Cod_Cons;
 registroSoliMone.Fec_Fact := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := ln_importe_cancelado_per;
 registroSoliMone.Cod_Cana_Refe := pregCuentaACancelar.Cod_Cana_Perc;
 registroSoliMone.Cod_Acce_Refe := pregCuentaACancelar.Cod_Acce_Perc;
 registroSoliMone.Cod_Sbac_Refe := pregCuentaACancelar.Cod_Suba_Perc;
 registroSoliMone.Num_Docu_Refe := pregCuentaACancelar.Num_Soli_Cons_Perc;
 registroSoliMone.Cod_Tipo_Soli := ps_tipo_soli;
 registroSoliMone.Cod_Acce := 'PR';
 registroSoliMone.Cod_Sbac := '290';
 registroSoliMone.Tip_Posi := 'PR';
 registroSoliMone.Val_Cant := -1;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Cor_Soli_Mone := PER_PKG_PROCE_PERCE.PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := pregCuentaACancelar.Cor_Cuen_Corr_Dole;
 registroSoliMone.Fec_Digi := SYSDATE;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_agru := NULL;
 registroSoliMone.Con_Tran := pRegMovimientoDetalle.Con_Tran;

 /* Insertando registro en Solicitudes Monetarias */
 PER_PKG_PROCE_PERCE.INSERT_PER_SOLI_MONE(RegistroSoliMone);
 END IF;

 IF psTipoOrigen = '03' OR psTipoOrigen = '04' THEN
 IF ln_importe_consumido - ln_importe_cancelado_per <> 0 THEN
 registroSoliMone.Pais_Cod_Pais := pregCuentaACancelar.Pais_Cod_Pais;
 Registrosolimone.Tior_Tipo_Orig_Dato := pstipoorigen;
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := ls_cod_peri;
 registroSoliMone.Cod_Clie := pRegMovimientoDetalle.Cod_Cons;
 registroSoliMone.Fec_Fact := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := NULL;
 registroSoliMone.Val_Impo := abs(ln_importe_consumido) - ln_importe_cancelado_per;
 registroSoliMone.Cod_Cana_Refe := NULL;
 registroSoliMone.Cod_Acce_Refe := NULL;
 registroSoliMone.Cod_Sbac_Refe := NULL;
 registroSoliMone.Num_Docu_Refe := NULL;
 registroSoliMone.Cod_Tipo_Soli := ps_tipo_soli_exceso_venta;
 registroSoliMone.Cod_Acce := 'GZ';
 registroSoliMone.Cod_Sbac := '000';
 IF psTipoOrigen = '03' THEN
 registroSoliMone.Tip_Posi := 'DP';
 ELSE
 registroSoliMone.Tip_Posi := 'CC';
 END IF;
 registroSoliMone.Val_Cant := -1;
 registroSoliMone.Cod_Form_Pago := NULL;
 registroSoliMone.Fec_Digi := SYSDATE;
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Est_Soli_Mone := '1';
 registroSoliMone.Cor_Soli_Mone := PER_PKG_PROCE_PERCE.PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := 0;
 registroSoliMone.Cor_agru := NULL;
 registroSoliMone.Con_Tran := pRegMovimientoDetalle.Con_Tran;

 /* Insertando registro en Solicitudes Monetarias */
 PER_PKG_PROCE_PERCE.INSERT_PER_SOLI_MONE(RegistroSoliMone);
 END IF;
 END IF;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_ACTU_IMPOR_MBANC: '||ls_sqlerrm);
END PER_PR_ACTU_IMPOR_MBANC;


/**************************************************************************
Descripcion : Procedimiento que efectua Cruce de Saldos Positivos
 y Negativos
Fecha Creacion : 19/09/2006
Parametros Entrada :
 psCodPais : Codigo de Pais
 psNumeroLote : Numero de Lote
Autor : Carlos Bazalar
***************************************************************************/
PROCEDURE PER_PR_CRUCE_SALDO_POSI_NEGA (
 psCodPais VARCHAR2, Psnumerolote IN OUT VARCHAR2)
IS

 TYPE tRegCoutas IS RECORD(
 clien_oid_clien NUMBER,
 oid_Marca NUMBER,
 sum_posi NUMBER,
 sum_nega NUMBER,
 fecha_venci DATE
 );
 TYPE tTablaCoutas IS TABLE OF tRegCoutas INDEX BY BINARY_INTEGER;

 regCoutas tRegCoutas;
 tablaCuotas tTablaCoutas;

 W_FILAS NUMBER:=5000;
 ln_sqlcode NUMBER(10);
 ls_sqlerrm VARCHAR2(150);
 ls_nroLoteCtaCte VARCHAR2(12);
 ld_fecha DATE;
 ls_fecha VARCHAR2(10);
 ln_importe_percepciones NUMBER;
 ln_numeroDias NUMBER;
 lsCodClien MAE_CLIEN.COD_CLIE%TYPE;
 ls_formaPago PER_SOLIC_MONET.COD_FORM_PAGO%TYPE;
 ln_idFormaPago BEL_FORMA_PAGO.OID_FORM_PAGO%TYPE;
 ln_idpais SEG_PAIS.OID_PAIS%TYPE;
 ln_idmarca SEG_MARCA.OID_MARC%TYPE;
 ln_idcanal SEG_CANAL.OID_CANA%TYPE;
 ln_id_peri CRA_PERIO.PERI_OID_PERI%TYPE;
 ls_cod_peri SEG_PERIO_CORPO.COD_PERI%TYPE;
 ln_idMarcaDiferente1 CCC_MARCA_SITUA.OID_MARC_SITU%TYPE;
 ln_idMarcaDiferente2 CCC_MARCA_SITUA.OID_MARC_SITU%TYPE;
 ln_idMarcaPercepcion CCC_MARCA_SITUA.OID_MARC_SITU%TYPE;
 regParamPercepcion PER_PROCE_SUBPR_PROGR%ROWTYPE;
 regParamNoPercepcion PER_PROCE_SUBPR_PROGR%ROWTYPE;
 Registrosolimone PER_SOLIC_MONET%ROWTYPE;
 lb_positivo BOOLEAN;

 CURSOR curDiferente(vnIdMarca1 NUMBER, vnIdMarca2 NUMBER) IS
 SELECT
 A.CLIE_OID_CLIE,
 A.MARC_OID_MARC,
 SUM((CASE WHEN A.IMP_PEND > 0 THEN A.IMP_PEND ELSE 0 END)) AS SUM_POSI ,
 SUM((CASE WHEN A.IMP_PEND < 0 THEN A.IMP_PEND ELSE 0 END)) AS SUM_NEGA,
 MAX(A.FEC_VENC) AS FEC_VENCI
 FROM CCC_MOVIM_CUENT_CORRI A
 WHERE
 (A.MASI_OID_MARC_SITU = vnIdMarca1 OR A.MASI_OID_MARC_SITU = vnIdMarca2) AND
 A.IMP_PEND <> 0
 GROUP BY
 A.CLIE_OID_CLIE, A.MARC_OID_MARC;

 CURSOR curPercepcion(vnIdMarca NUMBER) IS
 SELECT
 A.CLIE_OID_CLIE,
 A.MARC_OID_MARC,
 SUM((CASE WHEN A.IMP_PEND > 0 THEN A.IMP_PEND ELSE 0 END)) AS SUM_POSI ,
 SUM((CASE WHEN A.IMP_PEND < 0 THEN A.IMP_PEND ELSE 0 END)) AS SUM_NEGA,
 MAX(A.FEC_VENC) AS FEC_VENCI
 FROM CCC_MOVIM_CUENT_CORRI A
 WHERE
 A.MASI_OID_MARC_SITU = vnIdMarca AND
 A.IMP_PEND <> 0
 GROUP BY
 A.CLIE_OID_CLIE, A.MARC_OID_MARC;

BEGIN
 /* Invocando al caso de uso Generar Cuenta Corriente x Documento Legal */
 DELETE FROM PER_GTT_TABLA_REPOS;
 PER_PKG_PROCE_PERCE.PER_PR_CABEC_DETAL_DOLSO (psCodPais, USER, '06', 'PER-P1',ls_nroLoteCtaCte);
 DELETE FROM PER_GTT_TABLA_REPOS;

 Psnumerolote := ls_nroLoteCtaCte;

 /* Recuperando datos de parametros de percepciones */
 SELECT *
 INTO regParamPercepcion
 FROM
 PER_PROCE_SUBPR_PROGR a
 WHERE
 a.pais_cod_pais = psCodPais AND
 a.crep_cod_prog = '04' ;

 /* Recuperando datos de parametros diferentes de percepciones */
 SELECT *
 INTO regParamNoPercepcion
 FROM
 PER_PROCE_SUBPR_PROGR a
 WHERE
 a.pais_cod_pais = psCodPais AND
 a.crep_cod_prog = '03' ;

 /* Obteniendo id del pais */
 ln_idpais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);

 /* Obteniendo id de marca */
 ln_idmarca := gen_pkg_gener.gen_fn_devuelve_id_marca('T');

 /* Obteniendo id de canal */
 ln_idcanal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');

 /* Obteniendo Marca Situacion diferente a Percepion */
 SELECT A.OID_MARC_SITU
 INTO ln_idMarcaDiferente1
 FROM
 CCC_MARCA_SITUA A
 WHERE
 A.PAIS_OID_PAIS = ln_idpais AND
 A.COD_MARC_SITU = 'PP';

 SELECT A.OID_MARC_SITU
 INTO ln_idMarcaDiferente2
 FROM
 CCC_MARCA_SITUA A
 WHERE
 A.PAIS_OID_PAIS = ln_idpais AND
 A.COD_MARC_SITU = 'CI';

 /* Obteniendo Marca Situacion de Percepion */
 SELECT A.OID_MARC_SITU
 INTO ln_idMarcaPercepcion
 FROM
 CCC_MARCA_SITUA A
 WHERE
 A.PAIS_OID_PAIS = ln_idpais AND
 A.COD_MARC_SITU = 'P1';

 ld_fecha := SYSDATE;
 ls_fecha := to_char(SYSDATE,'DD/MM/YYYY');
 ld_fecha := to_date(ls_fecha,'DD/MM/YYYY');

 /* Busqueda de coutas diferentes a percepciones */
 OPEN curDiferente(ln_idMarcaDiferente1, ln_idMarcaDiferente2);
 LOOP
 FETCH curDiferente BULK COLLECT INTO tablaCuotas LIMIT W_FILAS;
 IF tablaCuotas.COUNT > 0 THEN
 FOR x IN tablaCuotas.FIRST .. tablaCuotas.LAST LOOP
 regCoutas := tablaCuotas(x);

 /* Obteniendo entidad periodos PARA LAS DIFERENTES*/
 BEGIN
 SELECT
 A.PERI_OID_PERI, B.COD_PERI
 INTO
 ln_id_peri, ls_cod_peri
 FROM CRA_PERIO A,
 SEG_PERIO_CORPO B
 WHERE
 A.PAIS_OID_PAIS = ln_idpais AND
 A.MARC_OID_MARC = regCoutas.oid_Marca AND
 A.CANA_OID_CANA = ln_idcanal AND
 A.FEC_INIC <= ld_fecha AND
 A.FEC_FINA >= ld_fecha AND
 B.OID_PERI = A.PERI_OID_PERI AND
 ROWNUM = 1;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RAISE_APPLICATION_ERROR(-20123,'No se encontro Periodo para las Coutas diferentes de PERCEPCION ');
 END ;

 /* Verificando que la sumatoria de importes positivos sea mayor a cero y
 sumatoria de importes negativos sea menor a cero efectuar insercion
 solicitudes monetarias */
 IF nvl(regCoutas.sum_posi,0) > 0 AND nvl(regCoutas.sum_nega,0) < 0 THEN

 /*ln_importe_percepciones := nvl(abs(regCoutas.sum_posi),0);
 IF ln_importe_percepciones < nvl(abs(regCoutas.sum_nega),0) THEN
 ln_importe_percepciones := nvl(abs(regCoutas.sum_nega),0);
 END IF;*/

 ln_importe_percepciones := nvl(abs(regCoutas.sum_nega),0);

 /* Encontrando forma de pago */
 ls_formaPago := 'CTD';

 /* Insertando en Solicitudes Monetarias
 Tipo de solicitud positiva */
 registroSoliMone.Pais_Cod_Pais := psCodPais;
 Registrosolimone.Tior_Tipo_Orig_Dato := '08';
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := ls_cod_peri;

 SELECT A.COD_CLIE
 INTO lsCodClien
 FROM MAE_CLIEN A
 WHERE A.OID_CLIE = regCoutas.clien_oid_clien;

 registroSoliMone.Cod_Clie := lsCodClien;
 registroSoliMone.Fec_Fact := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Val_Impo := ln_importe_percepciones;
 registroSoliMone.Cod_Cana_Refe := NULL;
 registroSoliMone.Cod_Acce_Refe := NULL;
 registroSoliMone.Cod_Sbac_Refe := NULL;
 registroSoliMone.Num_Docu_Refe := NULL;
 registroSoliMone.Cod_Tipo_Soli := regParamNoPercepcion.Tip_Soli_Impo_Posi;
 registroSoliMone.Val_Cant := 1;

 registroSoliMone.Cod_Acce := 'GZ';
 registroSoliMone.Cod_Sbac := '000';
 registroSoliMone.Tip_Posi := 'CS';

 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 registrosolimone.Est_Soli_Mone := '1';
 registroSoliMone.Cod_Form_Pago := ls_formaPago;
 registroSoliMone.Cor_Soli_Mone := PER_PKG_PROCE_PERCE.PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := 0;

 /* Insertando registro en Solicitudes Monetarias */
 PER_PKG_PROCE_PERCE.INSERT_PER_SOLI_MONE(RegistroSoliMone);

 /* Insertando en Solicitudes Monetarias
 Tipo de solicitud negativa */
 registroSoliMone.Pais_Cod_Pais := psCodPais;
 Registrosolimone.Tior_Tipo_Orig_Dato := '08';
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := ls_cod_peri;

 SELECT A.COD_CLIE
 INTO lsCodClien
 FROM MAE_CLIEN A
 WHERE A.OID_CLIE = regCoutas.clien_oid_clien;

 registroSoliMone.Cod_Clie := lsCodClien;
 registroSoliMone.Fec_Fact := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Val_Impo := ln_importe_percepciones;
 registroSoliMone.Cod_Cana_Refe := NULL;
 registroSoliMone.Cod_Acce_Refe := NULL;
 registroSoliMone.Cod_Sbac_Refe := NULL;
 registroSoliMone.Num_Docu_Refe := NULL;

 registroSoliMone.Cod_Tipo_Soli := regParamNoPercepcion.Tip_Soli_Impo_Nega;
 registroSoliMone.Val_Cant := -1;

 registroSoliMone.Cod_Acce := 'GZ';
 registroSoliMone.Cod_Sbac := '000';
 registroSoliMone.Tip_Posi := 'CS';

 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 registrosolimone.Est_Soli_Mone := '1';

 registroSoliMone.Cod_Form_Pago := ls_formaPago;
 registroSoliMone.Cor_Soli_Mone := PER_PKG_PROCE_PERCE.PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := 0;

 /* Insertando registro en Solicitudes Monetarias */
 PER_PKG_PROCE_PERCE.INSERT_PER_SOLI_MONE(RegistroSoliMone);

 END IF;
 END LOOP;
 END IF;
 EXIT WHEN curDiferente%NOTFOUND;
 END LOOP;
 CLOSE curDiferente;

 /* Busqueda de coutas de percepciones */
 OPEN curPercepcion(Ln_Idmarcapercepcion);
 LOOP
 FETCH curPercepcion BULK COLLECT INTO tablaCuotas LIMIT W_FILAS;
 IF tablaCuotas.COUNT > 0 THEN
 FOR x IN tablaCuotas.FIRST .. tablaCuotas.LAST LOOP
 regCoutas := tablaCuotas(x);

 /* Obteniendo entidad periodos PARA LAS PERCEPCIONES*/
 BEGIN
 SELECT
 A.PERI_OID_PERI, B.COD_PERI
 INTO
 ln_id_peri, ls_cod_peri
 FROM CRA_PERIO A,
 SEG_PERIO_CORPO B
 WHERE
 A.PAIS_OID_PAIS = ln_idpais AND
 A.MARC_OID_MARC = regCoutas.oid_Marca AND
 A.CANA_OID_CANA = ln_idcanal AND
 A.FEC_INIC <= ld_fecha AND
 A.FEC_FINA >= ld_fecha AND
 B.OID_PERI = A.PERI_OID_PERI AND
 ROWNUM = 1;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 RAISE_APPLICATION_ERROR(-20123,'No se encontro Periodo para las Coutas de PERCEPCION ');
 END ;

 lb_positivo := TRUE;
 /* Verificando que la sumatoria de importes positivos sea mayor a cero y
 sumatoria de importes negativos sea menor a cero efectuar insercion
 solicitudes monetarias */
 IF nvl(regCoutas.sum_posi,0) > 0 AND nvl(regCoutas.sum_nega,0) < 0 THEN
 ln_importe_percepciones := nvl(abs(regCoutas.sum_nega),0);
 /* Encontrando forma de pago */
 ls_formaPago := 'CTD';

 /* Insertando en Solicitudes Monetarias
 Tipo de solicitud positiva */
 registroSoliMone.Pais_Cod_Pais := psCodPais;
 Registrosolimone.Tior_Tipo_Orig_Dato := '08';
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := ls_cod_peri;

 SELECT A.COD_CLIE
 INTO lsCodClien
 FROM MAE_CLIEN A
 WHERE A.OID_CLIE = regCoutas.clien_oid_clien;

 registroSoliMone.Cod_Clie := lsCodClien;
 registroSoliMone.Fec_Fact := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Val_Impo := ln_importe_percepciones;
 registroSoliMone.Cod_Cana_Refe := NULL;
 registroSoliMone.Cod_Acce_Refe := NULL;
 registroSoliMone.Cod_Sbac_Refe := NULL;
 registroSoliMone.Num_Docu_Refe := NULL;
 registroSoliMone.Cod_Tipo_Soli := regParamPercepcion.Tip_Soli_Impo_Posi;
 registroSoliMone.Val_Cant := 1;

 registroSoliMone.Cod_Acce := 'PR';
 registroSoliMone.Cod_Sbac := '290';
 registroSoliMone.Tip_Posi := 'PR';
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 registrosolimone.Est_Soli_Mone := '1';

 registroSoliMone.Cod_Form_Pago := ls_formaPago;
 registroSoliMone.Cor_Soli_Mone := PER_PKG_PROCE_PERCE.PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := 0;

 /* Insertando registro en Solicitudes Monetarias */
 PER_PKG_PROCE_PERCE.INSERT_PER_SOLI_MONE(RegistroSoliMone);

 /* Insertando en Solicitudes Monetarias
 Tipo de solicitud negativa */
 registroSoliMone.Pais_Cod_Pais := psCodPais;
 Registrosolimone.Tior_Tipo_Orig_Dato := '08';
 registroSoliMone.Num_Lote := Psnumerolote;
 registroSoliMone.Cod_Peri := ls_cod_peri;

 SELECT A.COD_CLIE
 INTO lsCodClien
 FROM MAE_CLIEN A
 WHERE A.OID_CLIE = regCoutas.clien_oid_clien;

 registroSoliMone.Cod_Clie := lsCodClien;
 registroSoliMone.Fec_Fact := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Fec_Venc := GEN_PKG_GENER.GEN_FN_FECHA_ACTUAL_SIN_HORA();
 registroSoliMone.Val_Impo := ln_importe_percepciones;
 registroSoliMone.Cod_Cana_Refe := NULL;
 registroSoliMone.Cod_Acce_Refe := NULL;
 registroSoliMone.Cod_Sbac_Refe := NULL;
 registroSoliMone.Num_Docu_Refe := NULL;

 registroSoliMone.Cod_Tipo_Soli := regParamPercepcion.Tip_Soli_Impo_Nega;
 registroSoliMone.Val_Cant := -1;

 registroSoliMone.Cod_Acce := 'PR';
 registroSoliMone.Cod_Sbac := '290';
 registroSoliMone.Tip_Posi := 'PR';
 registroSoliMone.Usu_Digi := USER;
 registroSoliMone.Fec_Digi := SYSDATE;
 registrosolimone.Est_Soli_Mone := '1';

 registroSoliMone.Cod_Form_Pago := ls_formaPago;
 registroSoliMone.Cor_Soli_Mone := PER_PKG_PROCE_PERCE.PER_FN_SGTE_CORRE_SOLI_MONE();
 registroSoliMone.Cor_Cuen_Corr_Dole := 0;

 /* Insertando registro en Solicitudes Monetarias */
 PER_PKG_PROCE_PERCE.INSERT_PER_SOLI_MONE(RegistroSoliMone);
 END IF;

 END LOOP;
 END IF;
 EXIT WHEN curPercepcion%NOTFOUND;
 END LOOP;
 CLOSE curPercepcion;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 IF ln_sqlcode < 0 THEN
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_CRUCE_SALDO_POSI_NEGA: '||ls_sqlerrm);
 END IF;
END PER_PR_CRUCE_SALDO_POSI_NEGA;


/**************************************************************************
Descripcion : Procedimiento del PDT que devuelve la razon social
 del cliente ingresado como parametro
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_RAZON_SOCIAL(psCodPais VARCHAR2, psCodClas VARCHAR2, psTipoDocle VARCHAR2, psCodClie VARCHAR2)
RETURN VARCHAR2
IS
 lsCodHomo PER_TIPO_DOCUM_IDENT_LEGAL.Cod_Homo%TYPE;
 ls_devuelve VARCHAR2(20);
 lsValIden MAE_CLIEN_IDENT.val_iden_pers_empr%TYPE;
 lsValApe1 MAE_CLIEN.VAL_APE1%TYPE;
 lsValApe2 MAE_CLIEN.VAL_APE2%TYPE;
 lsValNom1 MAE_CLIEN.VAL_NOM1%TYPE;
 lsValNom2 MAE_CLIEN.VAL_NOM2%TYPE;

BEGIN
 ls_devuelve := '';
 SELECT COD_HOMO
 INTO lsCodHomo
 FROM PER_TIPO_DOCUM_IDENT_LEGAL B
 WHERE B.PAIS_COD_PAIS = psCodPais AND
 B.COD_CLAS = psCodClas AND
 B.COD_AHOM = psTipoDocle;

 --IF lsCodHomo = '01' THEN
     BEGIN
         /* Verificando que el Tipo Documento sea RUC para ello verifica que TIDO_OID_TIPO_DOCU = 1 */
         SELECT
 TRIM(PER_FN_DEVUE_PDT_CAMPO_REEMP(psCodPais,'PER','VAL_APE1',y.val_ape1)),
 TRIM(PER_FN_DEVUE_PDT_CAMPO_REEMP(psCodPais,'PER','VAL_APE2',y.val_ape2)),
 TRIM(PER_FN_DEVUE_PDT_CAMPO_REEMP(psCodPais,'PER','VAL_NOM1',y.val_nom1)),
 TRIM(PER_FN_DEVUE_PDT_CAMPO_REEMP(psCodPais,'PER','VAL_NOM2',y.val_nom2))
         INTO lsValApe1, lsValApe2, lsValNom1, lsValNom2
 FROM MAE_CLIEN y,
              MAE_CLIEN_IDENT z,
              mae_tipo_docum x
         WHERE y.cod_clie = psCodClie
           AND z.clie_oid_clie = y.oid_clie
           AND z.val_iden_docu_prin = 1
           AND X.OID_TIPO_DOCU = Z.TDOC_OID_TIPO_DOCU
           AND x.VAL_SIGL = 'RUC'
           AND ROWNUM = 1;

 ls_devuelve := substr(REPLACE(UPPER(lsValApe1||' '||lsValApe2||' '||lsValNom1||' '||lsValNom2), '?', 'N'),1,20);
     EXCEPTION
     WHEN NO_DATA_FOUND THEN
          ls_devuelve := '';
     END;
 --END IF;
 RETURN ls_devuelve;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN '';
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_PDT_RAZON_SOCIAL: '||ls_sqlerrm);
END PER_FN_DEVUE_PDT_RAZON_SOCIAL;

/**************************************************************************
Descripcion : Procedimiento del PDT que devuelve nombre, ap paterno o
 ap. materno del cliente ingresado como parametro
Fecha Creacion : 19/09/2006
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_NOMBRES(
 psCodPais VARCHAR2,
 psCodClas VARCHAR2,
 psTipoDocle VARCHAR2,
 psCodClie VARCHAR2,
 psTipo VARCHAR2,
 psNombreAlternativo VARCHAR2)
RETURN VARCHAR2
IS
 lsCodHomo PER_TIPO_DOCUM_IDENT_LEGAL.Cod_Homo%TYPE;
 ls_devuelve VARCHAR2(100);
 lsValIden MAE_CLIEN_IDENT.val_iden_pers_empr%TYPE;
 lsValApe1 MAE_CLIEN.VAL_APE1%TYPE;
 lsValApe2 MAE_CLIEN.VAL_APE2%TYPE;
 lsValNom1 MAE_CLIEN.VAL_NOM1%TYPE;
 lsValNom2 MAE_CLIEN.VAL_NOM2%TYPE;
 lsRazonSocial VARCHAR2(100);
BEGIN
 /* En caso poseea Razon Social no se buscar? el nombre o apellidos del cliente */
 lsRazonSocial := PER_FN_DEVUE_PDT_RAZON_SOCIAL(psCodPais, psCodClas, psTipoDocle , psCodClie);
 IF lsRazonSocial IS NOT NULL THEN
 RETURN '';
 END IF ;

 ls_devuelve := '';
 SELECT COD_HOMO
 INTO lsCodHomo
 FROM PER_TIPO_DOCUM_IDENT_LEGAL B
 WHERE B.PAIS_COD_PAIS = psCodPais AND
 B.COD_CLAS = psCodClas AND
 B.COD_AHOM = psTipoDocle;

 BEGIN
 SELECT Z.VAL_IDEN_PERS_EMPR,
 TRIM(PER_FN_DEVUE_PDT_CAMPO_REEMP(psCodPais,'PER','VAL_APE1',Y.VAL_APE1)),
 TRIM(PER_FN_DEVUE_PDT_CAMPO_REEMP(psCodPais,'PER','VAL_APE2',Y.VAL_APE2)),
 TRIM(PER_FN_DEVUE_PDT_CAMPO_REEMP(psCodPais,'PER','VAL_NOM1',Y.VAL_NOM1)),
 TRIM(PER_FN_DEVUE_PDT_CAMPO_REEMP(psCodPais,'PER','VAL_NOM2',Y.VAL_NOM2))
 INTO lsValIden, lsValApe1, lsValApe2, lsValNom1, lsValNom2
 FROM MAE_CLIEN y,
 MAE_CLIEN_IDENT z
 WHERE
 Y.COD_CLIE = psCodClie AND
 Z.CLIE_OID_CLIE = y.oid_clie AND
 Z.VAL_IDEN_DOCU_PRIN = 1 AND ROWNUM = 1;

 IF lsCodHomo <> '01' OR lsValIden = 'P' THEN
 IF psTipo = 'PATERNO' THEN
 ls_devuelve := REPLACE(UPPER(lsValApe1), '?', 'N');
 ELSIF psTipo = 'MATERNO' THEN
 ls_devuelve := REPLACE(UPPER(lsValApe2), '?', 'N');
 ELSIF psTipo = 'NOMBRE' THEN
 ls_devuelve := REPLACE(UPPER(lsValNom1||' '||lsValNom2), '?', 'N');
 END IF;
 END IF;
 IF ls_devuelve IS NULL THEN
 ls_devuelve := psNombreAlternativo;
 END IF;
 EXCEPTION
 WHEN NO_DATA_FOUND THEN
 ls_devuelve := psNombreAlternativo;
 END;
 IF ls_devuelve IS NULL THEN
 ls_devuelve := 'SN';
 END IF;
 IF length(ls_devuelve) >= 20 THEN
 ls_devuelve := trim(substr(ls_devuelve,1,20));
 END IF;

 RETURN ls_devuelve;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN 'SN';
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_PDT_NOMBRES: '||ls_sqlerrm);
END PER_FN_DEVUE_PDT_NOMBRES;


FUNCTION PER_FN_DEVUE_INDI_DEREC_CREFI(psCodPais VARCHAR2, psCodClas VARCHAR2, psTipoDocle VARCHAR2)
RETURN VARCHAR2
IS
 lsCodHomo PER_TIPO_DOCUM_IDENT_LEGAL.Cod_Homo%TYPE;
 ls_devuelve VARCHAR2(1);

BEGIN
 ls_devuelve := '';
 SELECT COD_HOMO
 INTO lsCodHomo
 FROM PER_TIPO_DOCUM_IDENT_LEGAL B
 WHERE B.PAIS_COD_PAIS = psCodPais AND
 B.COD_CLAS = psCodClas AND
 B.COD_AHOM = psTipoDocle;

 IF lsCodHomo = '01' THEN
 ls_devuelve := '1';
 ELSE
 ls_devuelve := '0';
 END IF;

 RETURN ls_devuelve;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN '';
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_INDI_DEREC_CREFI: '||ls_sqlerrm);
END PER_FN_DEVUE_INDI_DEREC_CREFI;


FUNCTION PER_FN_DEVUE_INDI_CLIEN_LISTA(psCodPais VARCHAR2, psIdTipo VARCHAR2, psNumDocum VARCHAR2)
RETURN VARCHAR2
IS
 lsCodHomo PER_TIPO_DOCUM_IDENT_LEGAL.Cod_Homo%TYPE;
 ls_devuelve VARCHAR2(1);
 lnContador NUMBER;
BEGIN
 ls_devuelve := '';
 SELECT COD_HOMO
 INTO lsCodHomo
 FROM PER_TIPO_DOCUM_IDENT_LEGAL B
 WHERE B.PAIS_COD_PAIS = psCodPais AND
 COD_CLAS = 'DI' AND
 COD_AHOM = psIdTipo;

 IF lsCodHomo <> '06' THEN
 RETURN '0';
 END IF;

 SELECT COUNT(1)
 INTO lnContador
 FROM
 PER_AGENT_PERCE A
 WHERE
 A.PAIS_COD_PAIS = psCodPais AND
 A.RUC_AGEN_PERC = psNumDocum;

 IF lnContador > 0 THEN
 RETURN '1';
 END IF;
 RETURN '0';

 RETURN ls_devuelve;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN '';
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_INDI_CLIEN_LISTA: '||ls_sqlerrm);
END PER_FN_DEVUE_INDI_CLIEN_LISTA;


FUNCTION PER_FN_DEVUE_MONTO_PERCE_VTADI(
 psCodPais VARCHAR2,
 psSerCope VARCHAR2,
 psNumCope VARCHAR2,
 psCodClie VARCHAR2
 )
RETURN NUMBER
IS
 lnMonto PER_PERCE_VENTA_DIREC.MON_PERC%TYPE;
BEGIN
 SELECT SUM(A.MON_PERC)
 INTO lnMonto
 FROM PER_PERCE_VENTA_DIREC A
 WHERE
 A.PAIS_COD_PAIS = psCodPais AND
 A.SER_COPE = psSerCope AND
 A.NUM_COPE = psNumCope AND
 A.COD_CONS = psCodClie ;
 RETURN lnMonto;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN 0;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_MONTO_PERCE_VTADI: '||ls_sqlerrm);
END PER_FN_DEVUE_MONTO_PERCE_VTADI;

/**************************************************************************
Descripcion : Funci?n utilizada en la Interfaz PDT que devuelve la
 Suma correspondiente al Monto de Pago
Fecha Modificacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_MONTO_PAGO(
 psCodClie VARCHAR2,
 psSerCope VARCHAR2,
 psNumCope VARCHAR2,
 psFechaDesde VARCHAR2,
 psFechaHasta VARCHAR2 )
RETURN NUMBER
IS
 ln_MontoPago PER_PERCE_CONSO.MON_PAGO%TYPE;

BEGIN
 ln_MontoPago := 0;
 IF psCodClie IS NULL THEN
 SELECT NVL(SUM(A.MON_PAGO),0)
 INTO ln_MontoPago
 FROM PER_PERCE_CONSO A
 WHERE
 A.SER_COPE = psSerCope AND
 A.NUM_COPE = psNumCope AND
 A.FEC_COPE >= TO_DATE(psfechaDesde,'DD/MM/YYYY') AND
 A.FEC_COPE < TO_DATE(psfechaHasta,'DD/MM/YYYY') + 1 ;

 ELSE
 SELECT NVL(SUM(A.MON_PAGO),0)
 INTO ln_MontoPago
 FROM PER_PERCE_CONSO A
 WHERE
 A.COD_CLIE = psCodClie AND
 A.SER_COPE = psSerCope AND
 A.NUM_COPE = psNumCope AND
 A.FEC_COPE >= TO_DATE(psfechaDesde,'DD/MM/YYYY') AND
 A.FEC_COPE < TO_DATE(psfechaHasta,'DD/MM/YYYY') + 1;

 END IF;
 RETURN ln_MontoPago;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN 0;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_PDT_MONTO_PAGO: '||ls_sqlerrm);
END PER_FN_DEVUE_PDT_MONTO_PAGO;


/**************************************************************************
Descripcion : Funci?n utilizada en la Interfaz PDT que devuelve el
 Monto Total
Fecha Modificacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_MONTO_TOTAL(
 psCodClie VARCHAR2,
 psSerDole VARCHAR2,
 psNumDole VARCHAR2,
 psFechaDesde VARCHAR2,
 psFechaHasta VARCHAR2 )
RETURN NUMBER
IS
 ln_MontoPago PER_PERCE_CONSO.MON_TODL%TYPE;

BEGIN
 ln_MontoPago := 0;
 IF psCodClie IS NULL THEN
 SELECT NVL(A.MON_TODL,0)
 INTO ln_MontoPago
 FROM PER_PERCE_CONSO A
 WHERE
 A.SER_DOLE = psSerDole AND
 A.NUM_DOLE = psNumDole AND
 A.FEC_COPE >= TO_DATE(psfechaDesde,'DD/MM/YYYY') AND
 A.FEC_COPE < TO_DATE(psfechaHasta,'DD/MM/YYYY') + 1 AND
 ROWNUM = 1;
 ELSE
 SELECT NVL(A.MON_TODL,0)
 INTO ln_MontoPago
 FROM PER_PERCE_CONSO A
 WHERE
 A.COD_CLIE = psCodClie AND
 A.SER_DOLE = psSerDole AND
 A.NUM_DOLE = psNumDole AND
 A.FEC_COPE >= TO_DATE(psfechaDesde,'DD/MM/YYYY') AND
 A.FEC_COPE < TO_DATE(psfechaHasta,'DD/MM/YYYY') + 1 AND
 ROWNUM = 1;
 END IF;

 RETURN ln_MontoPago;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN 0;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_PDT_MONTO_TOTAL: '||ls_sqlerrm);
END PER_FN_DEVUE_PDT_MONTO_TOTAL;

 PROCEDURE PER_PR_CARGA_MOVIM_BANCA(
  psCodigoPais                   IN   VARCHAR2,
  psCodigoTipoOrigenDatos        IN   VARCHAR2,
  psNumeroLoteInterno            IN   VARCHAR2)
 IS

 TYPE t_tab_ccc_movim_banca IS TABLE OF ccc_movim_banca%ROWTYPE;

 lv_tab_ccc_movim_banca t_tab_ccc_movim_banca;
 lv_oid_pais seg_pais.oid_pais%TYPE;
 lv_oid_soci seg_socie.oid_soci%TYPE;
 lv_cod_soci seg_socie.cod_soci%TYPE;
 lv_oid_subp_crea_banc ccc_subpr.oid_subp%TYPE;
 lv_oid_tcab_crea ccc_tipo_cargo_abono.oid_tipo_carg_abon%TYPE;
 lv_oid_ccba ccc_cuent_corri_banca.oid_cuen_corr_banc%TYPE;
 lv_cod_subp_reca ccc_subpr.cod_subp%TYPE;
  lv_ind_carg_banc                 ccc_param_gener.val_para%TYPE;

 w_filas NUMBER:=10000;
 lv_cod_erro CONSTANT VARCHAR2(2):='00';
 lv_cod_iden_proc CONSTANT VARCHAR2(1):='T';
 lv_val_esta_movi_pend CONSTANT VARCHAR2(1):='P';
 lv_cod_proc_reca CONSTANT VARCHAR2(6):='TES001';
 lv_cod_subp_reca_auto CONSTANT NUMBER(1):=1;
 lv_cod_subp_reca_manu CONSTANT NUMBER(1):=2;

 CURSOR c_ban(
 psCodigoPais VARCHAR2,
 psCodigoSociedad VARCHAR2,
 psCodigoTipoOrigenDatos VARCHAR2,
 psNumeroLoteInterno VARCHAR2)
 IS
 SELECT
 ccc_cmba_seq.NEXTVAL,
 lv_oid_soci,
 lv_oid_subp_crea_banc,
 lv_oid_subp_crea_banc,
 lv_oid_tcab_crea,
 lv_oid_tcab_crea,
 tt.oid_tipo_tran,
 lv_oid_ccba,
 d.con_tran,
 cb.num_lote_inte,
 d.cod_cons,
 lv_cod_erro,
 d.dig_cheq,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 lv_val_esta_movi_pend,
 NULL,
 d.fec_pago,
 trunc(SYSDATE),
 SYSDATE,
 d.val_hora,
 lv_cod_iden_proc,
 d.imp_reca_gene,
 d.nom_ofic,
 d.num_cupo,
 d.num_docu,
 d.num_fabo,
 NULL,
 d.des_obse,
 d.ofi_reca,
 USER,
 SYSDATE,
 d.imp_reca_gene,
 USER,
 cb.num_lote_exte,
 0,
 0,
 d.imp_reca_gene,
 lv_oid_pais,
 NULL,
 NULL,
 (SELECT mc.oid_clie
 FROM mae_clien mc
 WHERE mc.cod_clie=d.cod_cons),
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
 NULL,
  0,
 0
 FROM
  per_movim_banca_cabec cb,
 per_movim_banca_detal d,
 ccc_tipo_trans tt
		 WHERE cb.pais_cod_pais = pscodigoPais
			 AND cb.tior_tipo_orig_dato = psCodigoTipoOrigenDatos
			 AND cb.num_lote_inte = psNumeroLoteInterno
 AND cb.cod_soci = lv_cod_soci
 AND d.pais_cod_pais = pscodigoPais
 AND d.tior_tipo_orig_dato = psCodigoTipoOrigenDatos
 AND d.moca_num_lote_inte = psNumeroLoteInterno
			 AND d.pais_cod_pais = cb.pais_cod_pais
			 AND d.tior_tipo_orig_dato = cb.tior_tipo_orig_dato
			 AND d.moca_num_lote_inte = cb.num_lote_inte
 AND d.tip_tran = tt.cod_tipo_tran
 AND d.imp_reca_gene>0;

  CURSOR c_ban_tota(
  psCodigoPais VARCHAR2,
  psCodigoSociedad VARCHAR2,
  psCodigoTipoOrigenDatos VARCHAR2,
  psNumeroLoteInterno VARCHAR2)
 IS
 SELECT
  ccc_cmba_seq.NEXTVAL,
  lv_oid_soci,
  lv_oid_subp_crea_banc,
  lv_oid_subp_crea_banc,
  lv_oid_tcab_crea,
  lv_oid_tcab_crea,
  tt.oid_tipo_tran,
  lv_oid_ccba,
  d.con_tran,
  cb.num_lote_inte,
  d.cod_cons,
  lv_cod_erro,
  d.dig_cheq,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  lv_val_esta_movi_pend,
  NULL,
  d.fec_pago,
  trunc(SYSDATE),
  SYSDATE,
  d.val_hora,
  lv_cod_iden_proc,
  d.imp_reca_gene,
  d.nom_ofic,
  d.num_cupo,
  d.num_docu,
  d.num_fabo,
  NULL,
  d.des_obse,
  d.ofi_reca,
  USER,
  SYSDATE,
  d.val_pago,
  USER,
  cb.num_lote_exte,
  0,
  0,
  d.val_pago,
  lv_oid_pais,
  NULL,
  NULL,
  (SELECT mc.oid_clie
   FROM mae_clien mc
   WHERE mc.cod_clie=d.cod_cons),
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  NULL,
  0,
  0
 FROM
  per_movim_banca_cabec cb,
  per_movim_banca_detal d,
  ccc_tipo_trans tt
 WHERE cb.pais_cod_pais = pscodigoPais
	 AND cb.tior_tipo_orig_dato = psCodigoTipoOrigenDatos
	 AND cb.num_lote_inte = psNumeroLoteInterno
   AND cb.cod_soci = lv_cod_soci
   AND d.pais_cod_pais = pscodigoPais
   AND d.tior_tipo_orig_dato = psCodigoTipoOrigenDatos
   AND d.moca_num_lote_inte = psNumeroLoteInterno
	 AND d.pais_cod_pais = cb.pais_cod_pais
   AND d.tior_tipo_orig_dato = cb.tior_tipo_orig_dato
	 AND d.moca_num_lote_inte = cb.num_lote_inte
   AND d.tip_tran = tt.cod_tipo_tran
   AND d.val_pago > 0;

 BEGIN

 lv_oid_pais:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

 SELECT cb.cod_soci
 INTO lv_cod_soci
 FROM per_movim_banca_cabec cb
 WHERE cb.pais_cod_pais=psCodigoPais
 AND cb.tior_tipo_orig_dato=psCodigoTipoOrigenDatos
 AND cb.num_lote_inte=psNumeroLoteInterno;

 lv_oid_soci:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_SOCIE(lv_cod_soci);

 IF psCodigoTipoOrigenDatos='01' THEN
 lv_cod_subp_reca:=lv_cod_subp_reca_manu;
 ELSIF (psCodigoTipoOrigenDatos='02') OR (psCodigoTipoOrigenDatos='09') THEN
 lv_cod_subp_reca:=lv_cod_subp_reca_auto;
 END IF;

 -- Subproceso de Creacion
 lv_oid_subp_crea_banc:=CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_OID_SUBPR(lv_cod_proc_reca,lv_cod_subp_reca);
 -- Tipo Cargo Abono de Creacion
 SELECT tas.tcab_oid_tcab
 INTO lv_oid_tcab_crea
 FROM ccc_tipo_abono_subpr tas
 WHERE tas.subp_oid_subp=lv_oid_subp_crea_banc;

 SELECT ccb.oid_cuen_corr_banc
 INTO lv_oid_ccba
  FROM
   per_movim_banca_cabec mbc,
 ccc_cuent_corri_banca ccb
 WHERE mbc.pais_cod_pais=psCodigoPais
 AND mbc.tior_tipo_orig_dato=psCodigoTipoOrigenDatos
 AND mbc.num_lote_inte=psNumeroLoteInterno
 AND ccb.pais_oid_pais=lv_oid_pais
 AND mbc.cod_ccba=ccb.cod_cc;

  lv_ind_carg_banc := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('IndicadorCargaBancoTotal');

  IF lv_ind_carg_banc = 'S' THEN

   OPEN c_ban_tota(psCodigoPais, lv_cod_soci,psCodigoTipoOrigenDatos,psNumeroLoteInterno);
   LOOP
   FETCH c_ban_tota BULK COLLECT INTO lv_tab_ccc_movim_banca LIMIT w_filas;
    IF lv_tab_ccc_movim_banca.COUNT > 0 THEN
     FORALL x IN lv_tab_ccc_movim_banca.FIRST .. lv_tab_ccc_movim_banca.LAST
      INSERT INTO ccc_movim_banca VALUES lv_tab_ccc_movim_banca(x);
    END IF;
    EXIT WHEN c_ban%NOTFOUND;
   END LOOP;
   CLOSE c_ban;

  ELSE

 OPEN c_ban(psCodigoPais, lv_cod_soci,psCodigoTipoOrigenDatos,psNumeroLoteInterno);
 LOOP
 FETCH c_ban BULK COLLECT INTO lv_tab_ccc_movim_banca LIMIT w_filas;
 IF lv_tab_ccc_movim_banca.COUNT > 0 THEN
 FORALL x IN lv_tab_ccc_movim_banca.FIRST .. lv_tab_ccc_movim_banca.LAST
 INSERT INTO ccc_movim_banca VALUES lv_tab_ccc_movim_banca(x);
 END IF;
 EXIT WHEN c_ban%NOTFOUND;
 END LOOP;
 CLOSE c_ban;

  END IF;
 EXCEPTION
 WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_CARGA_MOVIM_BANCA: '||ls_sqlerrm);

 END PER_PR_CARGA_MOVIM_BANCA;



/**************************************************************************
Descripcion : Procedimiento del PDT que segun el valor del campo, busca el caracter a reempalzar
 y devuelve el valor cambiado
Fecha Creacion : 15/10/2009
Autor : Sergio Buchelli
parametros :
 psCodPais Codigo Pais,
 psCodBusq Codigo Busqueda,
 psValorCampo Valor Campo
 psCadenaTexto : Es el valor de la cadena original que va hacer reemplazadsa
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_CAMPO_REEMP(
 psCodPais VARCHAR2,
 psCodBusq VARCHAR2,
 psValorCampo VARCHAR2,
 psCadenaTexto VARCHAR2)
RETURN VARCHAR2
IS
 ls_devuelve VARCHAR2(100);
 lsCaracterBuscado VARCHAR2(1);
 lsCaracterReemp VARCHAR2(1);
 CURSOR cursorReemplazo
 IS
 SELECT X.VAL_CARA_BUSQ , X.VAL_CARA_REEM
 FROM INT_PARAM_PDT_REEMP X
 WHERE X.COD_PAIS = psCodPais
 AND X.COD_BUSQ = psCodBusq
 AND X.VAL_CAMP = psValorCampo
 AND X.IND_ACTI='1';

BEGIN
 ls_devuelve:= psCadenaTexto;

 FOR cReemplazo IN cursorReemplazo LOOP
 ls_devuelve :=REPLACE(ls_devuelve,cReemplazo.VAL_CARA_BUSQ,cReemplazo.VAL_CARA_REEM);

 END LOOP;

 --ls_devuelve :=REPLACE(psCadenaTexto,lsCaracterBuscado,lsCaracterReemp);
 RETURN ls_devuelve;

EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_PDT_CAMPO_REEMP: '||ls_sqlerrm);
END PER_FN_DEVUE_PDT_CAMPO_REEMP;

/**************************************************************************
Descripcion : Funci?n utilizada en la Interfaz PDT que devuelve la
 Suma correspondiente al Monto de Pago
Fecha Modificacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_MONTO_PAGO2(
 psCodClie VARCHAR2,
 psSerCope VARCHAR2,
 psNumCope VARCHAR2,
 psFechaDesde VARCHAR2,
 psFechaHasta VARCHAR2 )
RETURN NUMBER
IS
 ln_MontoPago PER_PERCE_CONSO.MON_PAGO%TYPE;

BEGIN
 ln_MontoPago := 0;
 IF psCodClie IS NULL THEN
 SELECT NVL(SUM(A.MON_PAGO),0)
 INTO ln_MontoPago
 FROM per_tmp_perce_conso A
 WHERE
 A.SER_COPE = psSerCope AND
 A.NUM_COPE = psNumCope AND
 A.FEC_COPE >= TO_DATE(psfechaDesde,'DD/MM/YYYY') AND
 A.FEC_COPE < TO_DATE(psfechaHasta,'DD/MM/YYYY') + 1 ;

 ELSE
 SELECT NVL(SUM(A.MON_PAGO),0)
 INTO ln_MontoPago
 FROM per_tmp_perce_conso A
 WHERE
 A.COD_CLIE = psCodClie AND
 A.SER_COPE = psSerCope AND
 A.NUM_COPE = psNumCope AND
 A.FEC_COPE >= TO_DATE(psfechaDesde,'DD/MM/YYYY') AND
 A.FEC_COPE < TO_DATE(psfechaHasta,'DD/MM/YYYY') + 1;

 END IF;
 RETURN ln_MontoPago;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN 0;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_PDT_MONTO_PAGO2: '||ls_sqlerrm);
END PER_FN_DEVUE_PDT_MONTO_PAGO2;


/**************************************************************************
Descripcion : Funci?n utilizada en la Interfaz PDT que devuelve el
 Monto Total
Fecha Modificacion : 09/05/2007
Autor : Carlos Bazalar
***************************************************************************/
FUNCTION PER_FN_DEVUE_PDT_MONTO_TOTAL2(
 psCodClie VARCHAR2,
 psSerDole VARCHAR2,
 psNumDole VARCHAR2,
 psFechaDesde VARCHAR2,
 psFechaHasta VARCHAR2 )
RETURN NUMBER
IS
 ln_MontoPago PER_PERCE_CONSO.MON_TODL%TYPE;

BEGIN
 ln_MontoPago := 0;
 IF psCodClie IS NULL THEN
 SELECT NVL(A.MON_TODL,0)
 INTO ln_MontoPago
 FROM per_tmp_perce_conso A
 WHERE
 A.SER_DOLE = psSerDole AND
 A.NUM_DOLE = psNumDole AND
 A.FEC_COPE >= TO_DATE(psfechaDesde,'DD/MM/YYYY') AND
 A.FEC_COPE < TO_DATE(psfechaHasta,'DD/MM/YYYY') + 1 AND
 ROWNUM = 1;
 ELSE
 SELECT NVL(A.MON_TODL,0)
 INTO ln_MontoPago
 FROM per_tmp_perce_conso A
 WHERE
 A.COD_CLIE = psCodClie AND
 A.SER_DOLE = psSerDole AND
 A.NUM_DOLE = psNumDole AND
 A.FEC_COPE >= TO_DATE(psfechaDesde,'DD/MM/YYYY') AND
 A.FEC_COPE < TO_DATE(psfechaHasta,'DD/MM/YYYY') + 1 AND
 ROWNUM = 1;
 END IF;

 RETURN ln_MontoPago;
EXCEPTION
WHEN NO_DATA_FOUND THEN
 RETURN 0;
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_FN_DEVUE_PDT_MONTO_TOTAL2: '||ls_sqlerrm);
END PER_FN_DEVUE_PDT_MONTO_TOTAL2;

/**************************************************************************
Descripcion : Generar Resumen Diario Percepciones SUNAT
Fecha Modificacion : 11/02/2016
Autor : Karina Valencia
***************************************************************************/
PROCEDURE PER_PR_GENER_RESUM_PERCE(
 psCodigoPais                   VARCHAR2,
 psFechaGenerar                 VARCHAR2,
 psUsuario                      VARCHAR2)

IS
 W_FILAS             NUMBER := 5000 ;
 
CURSOR cursorPercepcion IS
       SELECT '1' CON_INTE, --1 Conx Internet.
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'SER_COPE',
                                                        p.ser_cope) SER_COPE, --2 serie comprobante
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'NUM_COPE',
                                                        p.num_cope) NUM_COPE, --3 numero comprobante
       p.fec_cope FEC_INI, --4 fecha emision comprobante,             
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'NUM_DOID',
                                                        p.num_doid) DOC_IDENT, --5 documento identidad
       (SELECT cod_homo
          FROM per_tipo_docum_ident_legal b
         WHERE b.pais_cod_pais = psCodigoPais
           AND cod_clas = 'DI'
           AND cod_ahom = p.tip_doid) TIPO_DOCU, --6 tipo documento identidad                                                                               
       (SELECT UPPER(mc.val_ape1 || ' ' || mc.val_ape2 || ' ' || mc.val_nom1 || ' ' ||
                     mc.val_nom2)
          FROM MAE_CLIEN mc
         where mc.cod_clie = p.cod_cons) VAL_NOMBRE, --7 nombre                                                                                 
       '01' VENTA_DIRE, --8 Percepciones venta interna
       p.por_perc POR_PERC, --9 tasa de percepcion
       do.imp_perc_perc IMP_TOTA_PERC, --10 importe total percibido
       nvl(do.imp_tota_docu, 0) + nvl(do.imp_perc_perc, 0) IMP_TOTA_COBRA, --11  importe total cobrado
       (SELECT cod_homo
          FROM per_tipo_docum_ident_legal
         WHERE pais_cod_pais = psCodigoPais
           AND cod_clas = 'TC'
           AND cod_ahom = p.tip_dole) TIPO_DOCU_RELA, --12 tipo de documento relacionado
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'SER_DOLE',
                                                        p.ser_dole) SER_DOLE, --13 serie de documento relacionado
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'NUM_DOLE',
                                                        p.num_dole) NUM_DOLE, --14 num_dole
       p.fec_dole FEC_DOLE, --15 fecha de emision doc relacionado
       do.imp_tota_docu IMP_TOTA_DOCU, --16 importe total doc relacionado
       'PEN' TIPO_MONEDA, --17 tipo de moneda
       pa.fec_pago FEC_PAGO, --18 fecha de cobro
       p.cor_pevd COR_PEVD, --19 Numero de cobro oid
       nvl(pa.mon_pago, 0) - nvl(pa.mon_perc, 0) IMP_COBRO_SPERCE, --20 Importe de cobro sin percepcion 
       'PEN' MONEDA_COBRO, --21 Moneda del importe de cobro
       do.imp_perc_perc IMP_PERC_PERC, --22 Importe percibido de la percepcion
       p.fec_cope FEC_COPE, --23 Fecha de percepción
       pa.mon_perc MON_PERC --24 Monto total a cobrar      
  FROM per_regis_pagos       pa,
       per_cuent_corri_docle do,
       PER_PERCE_VENTA_DIREC p
 where pa.num_soco = do.num_soli_cons
   and p.REPA_COR_REPA = pa.COR_REPA
   and p.num_soco = pa.num_soco
   and trunc(p.fec_cope) = to_date(psFechaGenerar, 'dd/mm/yyyy')
union
SELECT '1' CON_INTE, --1 Conx Internet.
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'SER_COPE',
                                                        con.ser_cope) SER_COPE, --2 serie comprobante
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'NUM_COPE',
                                                        con.num_cope) NUM_COPE, --3 numero comprobante
       con.fec_cope FEC_INI, --4 fecha emision comprobante,             
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'NUM_DOID',
                                                        con.num_doid) DOC_IDENT, --5 documento identidad
       (SELECT cod_homo
          FROM per_tipo_docum_ident_legal b
         WHERE b.pais_cod_pais = psCodigoPais
           AND cod_clas = 'DI'
           AND cod_ahom = con.tip_doid) TIPO_DOCU, --6 tipo documento identidad                                                                                
       (SELECT UPPER(mc.val_ape1 || ' ' || mc.val_ape2 || ' ' || mc.val_nom1 || ' ' ||
                     mc.val_nom2)
          FROM MAE_CLIEN mc
         where mc.cod_clie = con.cod_clie) VAL_NOMBRE, --7 nombre                                                                                 
       '01' VENTA_DIRE, --8 Percepciones venta interna
       con.por_perc POR_PERC, --9 tasa de percepcion
       con.mon_perc IMP_TOTA_PERC, --10 monto total percibido,
       con.mon_pago IMP_TOTA_COBRA, --11  importe total cobrado
       (SELECT cod_homo
          FROM per_tipo_docum_ident_legal
         WHERE pais_cod_pais = psCodigoPais
           AND cod_clas = 'TC'
           AND cod_ahom = con.tip_dole) TIPO_DOCU_RELA, --12 tipo de documento relacionado
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'SER_DOLE',
                                                        con.ser_dole) SER_DOLE, --13 serie de documento relacionado
       per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                        'PER',
                                                        'NUM_DOLE',
                                                        con.num_dole) NUM_COPE, --14 num_dole
       con.fec_dole FEC_DOLE, --15 fecha de emision doc relacionado
       con.mon_todl IMP_TOTA_DOCU, --16 importe total doc relacionado
       'PEN' TIPO_MONEDA, --17 tipo de moneda
       con.fec_dole FEC_PAGO, --18 fecha de cobro
       con.cor_peco COR_PEVD, --19 Numero de cobro oid
       con.mon_todl IMP_COBRO_SPERCE, --20 Importe de cobro sin percepcion
       'PEN' MONEDA_COBRO, --21 Moneda del importe de cobro
       con.mon_perc IMP_PERC_PERC, --22 Importe percibido de la percepcion
       con.fec_cope FEC_COPE, --23 Fecha de percepción
       con.mon_perc MON_PERC --24 Monto total a cobrar       
  from PER_PERCE_CONSO con
 where con.cod_cana <> 'VD'
   and trunc(con.fec_cope) = to_date(psFechaGenerar, 'dd/mm/yyyy');

TYPE interfazTipo IS RECORD (
    CON_INTE          VARCHAR2(1),
    SER_COPE	        VARCHAR2(3),
    NUM_COPE	        VARCHAR2(8),
    FEC_INI		        DATE,
    DOC_IDENT	        VARCHAR2(30),
    TIPO_DOCU         VARCHAR2(2),
    VAL_NOMBRE	      VARCHAR2(100),
    VENTA_DIRE	      VARCHAR2(2),
    POR_PERC	        NUMBER(5,2),
    IMP_TOTA_PERCE    NUMBER(12,2),
    IMP_TOTA_COBRA	  NUMBER(12,2),
    TIPO_DOCU_RELA    VARCHAR2(3),
    SER_DOLE	        VARCHAR2(10),
    NUM_DOLE	        VARCHAR2(10),
    FEC_DOLE	        DATE,
    IMP_TOTA_DOCU     NUMBER(12,2),
    TIPO_MONEDA	      VARCHAR2(3),
    FEC_PAGO	        DATE,
    COR_PEVD	        NUMBER(10),
    IMP_COBRO_SPERCE  NUMBER(12,2),
    MONEDA_COBRO      VARCHAR2(3),
    IMP_PERC_PERC	    NUMBER(12,2),
    FEC_COPE	        DATE,
    MON_PERC	        NUMBER(12,2)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   
BEGIN
  DELETE PER_RESUM_HISTO_REPOR_SUNAT 
  WHERE trunc(FEC_COPE) = to_date(psFechaGenerar, 'dd/mm/yyyy');
  
  --Inserta data a PER_RESUM_HISTO_REPOR_SUNAT
  OPEN cursorPercepcion;
    LOOP
      FETCH cursorPercepcion BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
        IF interfazRecord.COUNT > 0 THEN
               FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP    
                 INSERT INTO  PER_RESUM_HISTO_REPOR_SUNAT(CON_INTE, SER_COPE, NUM_COPE,           
                        FEC_ENI_PERC,  NUM_DOID, TIP_DOID,           
                        VAL_NOMB, TIP_PERC,  POR_PERC,           
                        IMP_TOTA_PERCE, IMP_TOTA_COBRA , TIP_DOLE,          
                        SER_DOLE,  NUM_DOLE ,  FEC_DOLE,           
                        IMP_TOTA_DOLE,  TIP_MONE,  FEC_PAGO,           
                        COR_PEVD,  MON_PERC, TIP_MONE_REFE,      
                        IMP_PERCE, FEC_COPE, IMP_COBRA )
                 VALUES(
                        interfazRecord(x).CON_INTE,
                        interfazRecord(x).SER_COPE,
                        interfazRecord(x).NUM_COPE,
                        interfazRecord(x).FEC_INI,
                        interfazRecord(x).DOC_IDENT,
                        interfazRecord(x).TIPO_DOCU,
                        interfazRecord(x).VAL_NOMBRE,
                        interfazRecord(x).VENTA_DIRE,
                        interfazRecord(x).POR_PERC,
                        interfazRecord(x).IMP_TOTA_PERCE,
                        interfazRecord(x).IMP_TOTA_COBRA,
                        interfazRecord(x).TIPO_DOCU_RELA,
                        interfazRecord(x).SER_DOLE,
                        interfazRecord(x).NUM_DOLE,
                        interfazRecord(x).FEC_DOLE,
                        interfazRecord(x).IMP_TOTA_DOCU,
                        interfazRecord(x).TIPO_MONEDA,
                        interfazRecord(x).FEC_PAGO,
                        interfazRecord(x).COR_PEVD,
                        interfazRecord(x).IMP_COBRO_SPERCE,
                        interfazRecord(x).MONEDA_COBRO,
                        interfazRecord(x).IMP_PERC_PERC,
                        interfazRecord(x).FEC_COPE,                        
                        interfazRecord(x).MON_PERC                       
                 );      

               END LOOP;
            END IF;

         EXIT WHEN cursorPercepcion%NOTFOUND;
      END LOOP;
    CLOSE cursorPercepcion;
  
EXCEPTION
WHEN OTHERS THEN
 ln_sqlcode := SQLCODE;
 ls_sqlerrm := substr(sqlerrm,1,250);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_RESUM_PERCE: '||ls_sqlerrm);
END PER_PR_GENER_RESUM_PERCE;

/**************************************************************************
Descripcion : Reporte Resumen Diario Percepciones SUNAT -TXT
Fecha Modificacion : 11/02/2016
Autor : Karina Valencia
***************************************************************************/
PROCEDURE PER_PR_GENER_REPOR_SUNAT_TXT(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psFechaGeneracion		    VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(4000);
  lsNombreArchivo     VARCHAR2(50);
  W_FILAS             NUMBER := 5000 ;
  
  CURSOR c_interfaz IS
    SELECT
      CON_INTE ||'|'||
      trim(SER_COPE)||'|'||
      trim(NUM_COPE)||'|'||
      to_char(FEC_ENI_PERC,'dd/mm/yyyy')||'|'||
      trim(NUM_DOID)||'|'||
      trim(TIP_DOID)||'|'||
      trim(VAL_NOMB)||'|'||
      trim(TIP_PERC)||'|'||
      trim(to_char(POR_PERC,'99990.00'))||'|'||    
      trim(to_char(IMP_TOTA_PERCE,'999999999990.00'))||'|'||
      trim(to_char(IMP_TOTA_COBRA,'999999999990.00'))||'|'||    
      trim(TIP_DOLE)||'|'||    
      trim(SER_DOLE)||'|'||    
      trim(NUM_DOLE)||'|'||    
      to_char(FEC_DOLE,'dd/mm/yyyy')||'|'||
      trim(to_char(IMP_TOTA_DOLE,'999999999990.00'))||'|'||    
      trim(TIP_MONE)||'|'||
      to_char(FEC_PAGO,'dd/mm/yyyy')||'|'||        
      trim(COR_PEVD)||'|'||
      trim(to_char(MON_PERC,'999999999990.00'))||'|'||        
      trim(TIP_MONE_REFE)||'|'||
      trim(to_char(IMP_PERCE,'999999999990.00'))||'|'||    
      to_char(FEC_COPE,'dd/mm/yyyy')||'|'||  
      trim(to_char(IMP_COBRA,'999999999990.00'))||'|'
     as campo  
    FROM PER_RESUM_HISTO_REPOR_SUNAT 
    WHERE trunc(FEC_COPE) = to_date(psFechaGeneracion, 'dd/mm/yyyy');
  
TYPE interfazTipo IS RECORD
(
   campo VARCHAR2(4000)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.txt', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).campo ;
          UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
  EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;

 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_REPOR_SUNAT_TXT: '||ls_sqlerrm);
END PER_PR_GENER_REPOR_SUNAT_TXT;

/**************************************************************************
Descripcion : Reporte Resumen Diario Percepciones SUNAT -CSV
Fecha Modificacion : 12/02/2016
Autor : Karina Valencia
***************************************************************************/
PROCEDURE PER_PR_GENER_REPOR_SUNAT_CSV(
    psCodigoPais            VARCHAR2,
    psNombreArchivo         VARCHAR2,
    psFechaGeneracion       VARCHAR2,
    psTitulo                VARCHAR2,
    psDirectorio       OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;  
  lsLinea             VARCHAR2(4000); 
  
  CURSOR c_interfaz IS
    SELECT
        trim(CON_INTE)as CON_INTE,
        trim(SER_COPE) as SER_COPE,
        trim(NUM_COPE) as NUM_COPE,
        to_char(FEC_ENI_PERC,'dd/mm/yyyy') as FEC_ENI_PERC,
        trim(NUM_DOID) as NUM_DOID,
        trim(TIP_DOID) as TIP_DOID,
        trim(VAL_NOMB) as VAL_NOMB,
        trim(TIP_PERC) as TIP_PERC,        
        trim(to_char(POR_PERC,'99990.00')) as  POR_PERC,      
        trim(to_char(IMP_TOTA_PERCE,'999999999990.00')) as IMP_TOTA_PERCE,       
        trim(to_char(IMP_TOTA_COBRA,'999999999990.00')) as IMP_TOTA_COBRA,    
        trim(TIP_DOLE) as TIP_DOLE,   
        trim(SER_DOLE) as SER_DOLE,   
        trim(NUM_DOLE) as NUM_DOLE,    
        trim(to_char(FEC_DOLE,'dd/mm/yyyy')) as FEC_DOLE,        
        trim(to_char(IMP_TOTA_DOLE,'999999999990.00')) as IMP_TOTA_DOLE,  
        trim(TIP_MONE) as TIP_MONE,
        to_char(FEC_PAGO,'dd/mm/yyyy') as FEC_PAGO,       
        COR_PEVD,        
        trim(to_char(MON_PERC,'999999999990.00')) as MON_PERC,       
        trim(TIP_MONE_REFE) as TIP_MONE_REFE,      
        trim(to_char(IMP_PERCE,'999999999990.00')) as IMP_PERCE,     
        to_char(FEC_COPE,'dd/mm/yyyy') as FEC_COPE, 
        trim(to_char(IMP_COBRA,'999999999990.00')) as IMP_COBRA                 
      FROM PER_RESUM_HISTO_REPOR_SUNAT 
      WHERE trunc(FEC_COPE) = to_date(psFechaGeneracion, 'dd/mm/yyyy');

TYPE interfazTipo IS RECORD (
   CON_INTE         PER_RESUM_HISTO_REPOR_SUNAT.CON_INTE%TYPE,
   SER_COPE         PER_RESUM_HISTO_REPOR_SUNAT.SER_COPE%TYPE,
   NUM_COPE         PER_RESUM_HISTO_REPOR_SUNAT.NUM_COPE%TYPE,
   FEC_ENI_PERC     VARCHAR2(10),
   NUM_DOID         PER_RESUM_HISTO_REPOR_SUNAT.NUM_DOID%TYPE,
   TIP_DOID         PER_RESUM_HISTO_REPOR_SUNAT.TIP_DOID%TYPE,
   VAL_NOMB         PER_RESUM_HISTO_REPOR_SUNAT.VAL_NOMB%TYPE,
   TIP_PERC         PER_RESUM_HISTO_REPOR_SUNAT.TIP_PERC%TYPE,
   POR_PERC         VARCHAR2(20),
   IMP_TOTA_PERCE   VARCHAR2(20),
   IMP_TOTA_COBRA   VARCHAR2(20),
   TIP_DOLE         PER_RESUM_HISTO_REPOR_SUNAT.TIP_DOLE%TYPE,
   SER_DOLE         PER_RESUM_HISTO_REPOR_SUNAT.SER_DOLE%TYPE,
   NUM_DOLE         PER_RESUM_HISTO_REPOR_SUNAT.NUM_DOLE%TYPE,
   FEC_DOLE         VARCHAR2(10),
   IMP_TOTA_DOLE    VARCHAR2(20),
   TIP_MONE         PER_RESUM_HISTO_REPOR_SUNAT.TIP_MONE%TYPE,
   FEC_PAGO         VARCHAR2(10),
   COR_PEVD         PER_RESUM_HISTO_REPOR_SUNAT.COR_PEVD%TYPE,
   MON_PERC         VARCHAR2(20),
   TIP_MONE_REFE    PER_RESUM_HISTO_REPOR_SUNAT.TIP_MONE_REFE%TYPE,
   IMP_PERCE        VARCHAR2(20),
   FEC_COPE         VARCHAR2(10),
   IMP_COBRA       VARCHAR2(20)
);
   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;
   
BEGIN
  lbAbrirUtlFile := TRUE;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
  
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psDirectorio := lsDirTempo;
      lbAbrirUtlFile := FALSE;
   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          lsLinea := interfazRecord(x).CON_INTE ||','||
                    interfazRecord(x).SER_COPE ||','||
                    interfazRecord(x).NUM_COPE ||','||
                    interfazRecord(x).FEC_ENI_PERC ||','||
                    interfazRecord(x).NUM_DOID ||','||
                    interfazRecord(x).TIP_DOID ||','||
                    interfazRecord(x).VAL_NOMB ||','||
                    interfazRecord(x).TIP_PERC ||','||
                    interfazRecord(x).POR_PERC ||','||
                    interfazRecord(x).IMP_TOTA_PERCE ||','||
                    interfazRecord(x).IMP_TOTA_COBRA ||','||
                    interfazRecord(x).TIP_DOLE ||','||
                    interfazRecord(x).SER_DOLE ||','||
                    interfazRecord(x).NUM_DOLE ||','||
                    interfazRecord(x).FEC_DOLE ||','||
                    interfazRecord(x).IMP_TOTA_DOLE ||','||                   
                    interfazRecord(x).TIP_MONE ||','||
                    interfazRecord(x).FEC_PAGO ||','||                                                          
                    interfazRecord(x).COR_PEVD ||','||
                    interfazRecord(x).MON_PERC ||','||
                    interfazRecord(x).TIP_MONE_REFE ||','||
                    interfazRecord(x).IMP_PERCE ||','||
                    interfazRecord(x).FEC_COPE ||','||               
                    interfazRecord(x).IMP_COBRA  ;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
 
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_GENER_REPOR_SUNAT_CSV: '||ls_sqlerrm);
END PER_PR_GENER_REPOR_SUNAT_CSV;


END PER_PKG_PROCE_PERCE;
/
