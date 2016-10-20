CREATE OR REPLACE PACKAGE REC_PKG_PROCE is

  PROCEDURE REC_PR_PROCE_INGRE_ANULA_NMPS(psnumeroLote    VARCHAR2,
                                          psnum_recl      VARCHAR2,
                                          psnum_docu_refe VARCHAR2,
                                          pscod_clie      VARCHAR2,
                                          pscod_usua      VARCHAR2,
                                          psfec_ingr      VARCHAR2,
                                          pscod_pais      VARCHAR2,
                                          pscod_esta_proc OUT VARCHAR2,
                                          pscod_erro      OUT VARCHAR2,
                                          --      pscod_corr           VARCHAR2,
                                          psflagNotaMercaderia VARCHAR2,
                                          psflagGenerarEnvia   VARCHAR2,
                                          psnumCampana         VARCHAR2,
                                          psusuario            VARCHAR2,
                                          psdeserror           OUT VARCHAR2,
										  psmotivodevolucion   VARCHAR2,
                                          pscodoperanul        VARCHAR2,
                                          psobservaciones      VARCHAR2
                      );

   /**************************************************************************
  Descripcion         : REC_PR_PROCE_ENVIA_RECLA_DIGIT
                        Inserta Cabeceras Detalles hacia Tablas de Consolidados
  Fecha Creacion      : 26/11/2008
  Parametros Entrada:
      psUsuario       : Codigo de Usuario
  Autor               : Dennys Oliva Iriate
  ***************************************************************************/
  PROCEDURE rec_pr_proce_envia_histo_anula
  (
    pscod_pais           VARCHAR2,
    psnumCampana         VARCHAR2,
    pscod_clie           VARCHAR2,
    pssecnumedocu        NUMBER
  );

   /**************************************************************************
  Descripcion         : REC_PR_PROCE_ENVIA_RECLA_DIGIT
                        Inserta Cabeceras Detalles hacia Tablas de Consolidados
  Fecha Creacion      : 26/11/2008
  Parametros Entrada:
      psUsuario       : Codigo de Usuario
  Autor               : Dennys Oliva Iriate
  ***************************************************************************/
  PROCEDURE rec_pr_proce_envia_recla_digit
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumerolote         VARCHAR2,
    pscodigodocumento VARCHAR2
  );


  PROCEDURE REC_PR_PROCE_APRUE_RECLA_DIGIT(
      pscodigopais VARCHAR2,
      pscodigoboletarecojo VARCHAR2, 
      psCodigoUsuario VARCHAR2  
      );

/**************************************************************************
  Descripcion         : REC_PR_PROCE_INGRE_ATENC
                        Proceso de Ingreso de Atenciones
  Fecha Creacion      : 29/12/2008
  Parametros Entrada:
      psTipoProducto       : Tipo de Producto
      psTipoAtencion       : Tipo de Atencion
      psCodigoCliente      : Codigo de cliente
      psCampanhaProceso    : Campnha de proceso
      psCampanhaReferencia : Campanha de referencia
      psCodigoUsuario      : Codigo Usuario
      psMensajeError       :MensajeError
  Autor               : Dennys Oliva Iriate
***************************************************************************/
  PROCEDURE REC_PR_PROCE_INGRE_ATENC(psNumeroLote   VARCHAR2,
                                     psTipoProducto VARCHAR2,
                                     psTipoAtencion VARCHAR2,
                                     psCodigoCliente VARCHAR2,
                                     psCampanhaProceso VARCHAR2,
                                     psCampanhaReferencia VARCHAR2,
                                     psCodigoPais VARCHAR2,
                                     psCodigoUsuario VARCHAR2,
                                     psMensajeError OUT VARCHAR2,
                                     psTipoOperacion VARCHAR2

  );

/**************************************************************************
  Descripcion         : REC_PR_PROCE_ACTUA_DIGIT_SIMPL
                        Actualiza valores por la digitacion simplificada
  Fecha Creacion      : 26/11/2008
  Parametros Entrada:
      pscodigopais         : Codigo de Pais
      pscodigoboletarecojo : Codigo de Boleta de Recojo
      pscodigonovedad      : Codigo de novedad
  Autor               : Dennys Oliva Iriate
  ***************************************************************************/
 PROCEDURE REC_PR_PROCE_ACTUA_DIGIT_SIMPL(pscodigopais              VARCHAR2,
                                          pscodigoboletarecojo      VARCHAR2,
                                          pscodigonovedad           VARCHAR2,
                                          pscodigolineaboletarecojo VARCHAR2,
                                          psnumeroeliminados        NUMBER);

/**************************************************************************
  Descripcion         : Funcion que verifica si se excluye al cliente del
                        control de devoluciones
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente      : oid Cliente
  Devuelve:
      S : SE EXCLUYE
      N : NO SE EXCLUYE
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_VERIF_EXCLU_DEVOL(pn_Oid_Cliente     NUMBER,
                                    pn_oid_periodo     NUMBER) RETURN VARCHAR2;

/**************************************************************************
  Descripcion         : Funcion que Retorna el porcentaje de devolucion
                        que debera de aplicar en la validacion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_PORCE_MONTO_DEVOL(pn_Oid_Cliente     NUMBER,
                                    pscodigopais       VARCHAR2,
                                    pscodigoperiodo    VARCHAR2) RETURN NUMBER ;

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total del pedido y
                        el monto total de devoluciones
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_EVALU_DEVOL(pnValNumeSoli     NUMBER,
                                   pnMontoPedido     OUT NUMBER,
                                   pnMontoDevolucion OUT NUMBER);

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total de gestion y
                        el monto del documento por gestion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_GESTI_DEVOL(pnValNumeSoli              NUMBER,
                                   pnMontoTotalGestion    OUT NUMBER,
                                   psNumLote                  VARCHAR2,
                                   psNumDocu                  VARCHAR2,
                                   psCodClie                  VARCHAR2,
                                   psCodPeri                  VARCHAR2,
                                   psCodPais                  VARCHAR2,
                                   pnMontoDocGestion      OUT NUMBER);

/**************************************************************************
  Descripcion         : Funcion que devuelve el codigo del chequeo
                        realizado
  Fecha Creacion      : 22/03/2011
  Parametros Entrada:
      psNumPedido      : numero pedido

  Autor               : Jose Luis Rodriguez
***************************************************************************/
  FUNCTION REC_FN_RESUL_CHEQU_PEDID(psNumPedido  VARCHAR2) RETURN VARCHAR2;

/**************************************************************************
  Descripcion         : Procedure que elimina un lote de registros solo
                        cuando se procesan atenciones.
  Fecha Creacion      : 06/09/2011
  Parametros Entrada:
      psNumLote       : Numero de Lote
      psUsuario       : Codigo de Usuario
      psMensajeError  : MensajeError

  Autor               : Francesco Rodriguez
***************************************************************************/
PROCEDURE REC_PR_VALID_ELIMI_LOTE_ATENC(psNumLote VARCHAR2,
                                  psUsuario OUT VARCHAR2,
                                  psMensajeError  OUT VARCHAR2);

/**************************************************************************
  Descripcion         : Procedure que elimina un lote de registros
                        de ingreso de atenciones
  Fecha Creacion      : 06/09/2011
  Parametros Entrada:
      psNumLote       : Numero de Lote
      psUsuario       : Codigo de Usuario
      psMensajeError  : MensajeError

  Autor               : Francesco Rodriguez
***************************************************************************/
PROCEDURE REC_PR_ELIMI_LOTE_ATENC(psNumLote VARCHAR2,
                                  psUsuario       VARCHAR2,
                                  psMensajeError  OUT VARCHAR2);

/**************************************************************************
  Descripcion         : Procedure que revisa la oferta del producto que se
                        esta devolviendo
  Fecha Creacion      : 22/01/2013
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_REVIS_OFERT_DEVOL(pnOidSoliPosi     NUMBER,
                                   pnNroUnidades     NUMBER);

/**************************************************************************
  Descripcion         : Procedure que revisa la oferta del producto que se
                        esta devolviendo por simplificacion
  Fecha Creacion      : 22/01/2013
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_REVIS_OFERT_DEVOL_SIMP(pnOidSoliPosi     NUMBER,
                                   pnNroUnidades     NUMBER);

/**************************************************************************
  Descripcion         : REC_PR_DESCA_ARCHI_ANULA
                        Proceso de Descarga de Archivo de Anulaciones y NMPs
  Fecha Creacion      : 22/04/2013
  Autor               : Eduardo Sanchez Castillo
  Parametros:
      psNumeroLote    : Numero de Lote
      psCodigoPais    : Codigo del Pais
      psCodigoSistema : Codigo del Sistema
***************************************************************************/
  PROCEDURE REC_PR_DESCA_ARCHI_ANULA(psNumeroLote       VARCHAR2,
                                     psCodigoPais       VARCHAR2,
                                     psCodigoSistema    VARCHAR2);

/**************************************************************************
  Descripcion         : Funcion que verifica si se excluye al cliente del
                        control de faltantes
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente      : oid Cliente
  Devuelve:
      S : SE EXCLUYE
      N : NO SE EXCLUYE
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_VERIF_EXCLU_FALTA(pn_Oid_Cliente     NUMBER,
                                    pn_oid_periodo     NUMBER) RETURN VARCHAR2;

/**************************************************************************
  Descripcion         : Funcion que Retorna el porcentaje de faltante
                        que debera de aplicar en la validacion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_PORCE_MONTO_FALTA(pn_Oid_Cliente     NUMBER,
                                    pscodigopais       VARCHAR2,
                                    pscodigoperiodo    VARCHAR2) RETURN NUMBER ;

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total del pedido y
                        el monto total de faltante
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_EVALU_FALTA(pnValNumeSoli     NUMBER,
                                   pnMontoPedido     OUT NUMBER,
                                   pnMontoDevolucion OUT NUMBER);

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total de gestion y
                        el monto del documento por gestion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_GESTI_FALTA(pnValNumeSoli              NUMBER,
                                   pnMontoTotalGestion    OUT NUMBER,
                                   psNumLote                  VARCHAR2,
                                   psNumDocu                  VARCHAR2,
                                   psCodClie                  VARCHAR2,
                                   psCodPeri                  VARCHAR2,
                                   psCodPais                  VARCHAR2,
                                   pnMontoDocGestion      OUT NUMBER);

/**************************************************************************
  Descripcion         : REC_PR_PROCE_INGRE_ATENC
                        Proceso de Ingreso de Atenciones
  Fecha Creacion      : 29/12/2008
  Parametros Entrada:
      psTipoProducto       : Tipo de Producto
      psTipoAtencion       : Tipo de Atencion
      psCodigoCliente      : Codigo de cliente
      psCampanhaProceso    : Campnha de proceso
      psCampanhaReferencia : Campanha de referencia
      psCodigoUsuario      : Codigo Usuario
      psMensajeError       :MensajeError
  Autor               : Dennys Oliva Iriate
***************************************************************************/
  PROCEDURE REC_PR_PROCE_INGRE_ATEN_MAS(psNumeroLote   VARCHAR2,
                                     psTipoProducto VARCHAR2,
                                     psTipoAtencion VARCHAR2,
                                     psCodigoCliente VARCHAR2,
                                     psCampanhaProceso VARCHAR2,
                                     psCampanhaReferencia VARCHAR2,
                                     psCodigoPais VARCHAR2,
                                     psCodigoUsuario VARCHAR2,
                                     psMensajeError OUT VARCHAR2,
                                     psTipoOperacion VARCHAR2

  );


/**************************************************************************
  Descripcion         : REC_PR_RECUP_PV_PREM
                        Proceso de Recuperacion de Postventas y Premios
  Fecha Creacion      : 29/12/2008
  Parametros Entrada:
      p_oidsoli       : Solicitud de Referencia
      p_tipo          : Motivo, Anulado, Faltante
      p_periodo       : Periodo Actual o Siguiente
      p_CodigoPais    : Codigo Pais
  Autor               : Jorge Yepez
***************************************************************************/
PROCEDURE REC_PR_RECUP_PV_PREM(p_oidsoli   NUMBER,
                               p_tipo VARCHAR2,
                               p_periodo NUMBER,
                               p_CodigoPais VARCHAR2
                                   );

/**************************************************************************
  Descripcion         : Funcion que devuelve la descripcion a mostrar en la hiper cta cte
  Fecha Creacion      : 22/03/2011
  Parametros Entrada:
      psNumPedido      : numero pedido

  Autor               : Jose Luis Rodriguez
***************************************************************************/
  FUNCTION REC_FN_SOLIC_PEDID_HIPER(psOidPedido  VARCHAR2) RETURN VARCHAR2;

/**************************************************************************
  Descripcion         : Funcion que verifica si se excluye al cliente del
                        control de faltantes
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente      : oid Cliente
  Devuelve:
      S : SE EXCLUYE
      N : NO SE EXCLUYE
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_VERIF_EXCLU_CAMBI(pn_Oid_Cliente     NUMBER,
                                    pn_oid_periodo     NUMBER) RETURN VARCHAR2;

/**************************************************************************
  Descripcion         : Funcion que Retorna el porcentaje de faltante
                        que debera de aplicar en la validacion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_PORCE_MONTO_CAMBI(pn_Oid_Cliente     NUMBER,
                                    pscodigopais       VARCHAR2,
                                    pscodigoperiodo    VARCHAR2) RETURN NUMBER ;

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total del pedido y
                        el monto total de faltante
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_EVALU_CAMBI(pnValNumeSoli     NUMBER,
                                   pnMontoPedido     OUT NUMBER,
                                   pnMontoDevolucion OUT NUMBER);

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total de gestion y
                        el monto del documento por gestion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_GESTI_CAMBI(pnValNumeSoli              NUMBER,
                                   pnMontoTotalGestion    OUT NUMBER,
                                   psNumLote                  VARCHAR2,
                                   psNumDocu                  VARCHAR2,
                                   psCodClie                  VARCHAR2,
                                   psCodPeri                  VARCHAR2,
                                   psCodPais                  VARCHAR2,
                                   pnMontoDocGestion      OUT NUMBER);

/**************************************************************************
  Descripcion         : Funcion que devuelve el abono pendiente de la consutora
  Fecha Creacion      : 12/05/2015
  Parametros Entrada:
      pn_Oid_Cliente      : oid Cliente
  Devuelve:
                    importe pendiente
  Autor               : Sandro Quintana Aponte
***************************************************************************/
 FUNCTION REC_FN_ABONO_PENDI_CONSU(pn_Oid_Cliente     NUMBER,
                                   pn_oid_periodo     NUMBER) 
    RETURN NUMBER;
                                    
END REC_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY REC_PKG_PROCE is
 /* Declaracion de Variables */
   ln_sqlcode     NUMBER(10);
   ls_sqlerrm     VARCHAR2(1000);


  PROCEDURE REC_PR_PROCE_INGRE_ANULA_NMPS(psNumeroLote         VARCHAR2,
                                          psnum_recl           VARCHAR2,
                                          psnum_docu_refe      VARCHAR2,
                                          pscod_clie           VARCHAR2,
                                          pscod_usua           VARCHAR2,
                                          psfec_ingr           VARCHAR2,
                                          pscod_pais           VARCHAR2,
                                          pscod_esta_proc      OUT VARCHAR2,
                                          pscod_erro           OUT VARCHAR2,
                                          psflagNotaMercaderia VARCHAR2,
                                          psflagGenerarEnvia   VARCHAR2,
                                          psnumCampana         VARCHAR2,
                                          psusuario            VARCHAR2,
                                          psdeserror           OUT VARCHAR2,
										  psmotivodevolucion   VARCHAR2,
                                          pscodoperanul        VARCHAR2,
                                          psobservaciones      VARCHAR2
                                          ) IS
    W_FILAS NUMBER := 1000;
    e_encuentra_rows EXCEPTION;
    e_misma_campana  EXCEPTION;
    e_misma_campana_AX  EXCEPTION;
    e_no_pertenece   EXCEPTION;
    ---
    varOidOper         number(12);
    varOidTipoOper     number(12);
    varTspaDevuelve    number(12);
    varTspaEnvia       number(12);
    varOCDev           number(1);
    varOCEnv           number(1);
    varTspaEnviaPremio number(12);
    varCount           integer;
    ---
    varNroBolRecojo    number;
    varMensaje         rec_repos_anula.val_obse_anul%TYPE;
    varUsuario         rec_repos_anula.cod_usua%TYPE;
    ---
    varCodOper VARCHAR2(2);
    ----
    varOidCabeRecla  number(12);
    varOidOperaRecla number(12);

    varFecDocuRefe  date;
    v_fec_proc      date;
    varOidPeriRefe  number(12);
    varOidPais      number(12);
    varOidSoliCabe  number(12);
    varOidClie      number(12);
    varOidTerrAdmin number(12);
    varSubtipoClie  number(12);
    varTipoClie     number(12);
    varTipoDocum    number(12);
    varOidDireClie  number(12);
    varOidZonClie   number(12);
    varTipoDocum2   number(12);
    -------------
    varTotDev NUMBER(10, 2);
    -------------
    varTerri                number(12);
    varZona                 number(12);
    varVepo                 number(12);
    copa_oid_para_gral      number(12);
    tido_oid_tipo_docu_cont number(12);
    vtido_oid_tipo_docu     number(12);
    varTipoDocIdent         number(12);
    varSocie                number(12);
    varSBAC_OID_SBAC        number(12);
    ICTP_OID_TIPO_PROG      number(6);
    varOidCabe              number(12);
    --------------
    varFormaPagoDev     number(12);
    varFechaProgFactDev date;
    varClaseSolicDev    number(12);
    varOidAlmacDev      number(12);
    vartsolOidTipoCons  number(12);

    varTipoConcu    number(6);
    varOidSoliRefe  number(12);
    varTipoSoliCons number(12);
    varTipoSoliConsE number(12);
    varCopa         number(12);
    varPais         number(12);
    varNumLine      NUMBER(4);
    varNumeSoli     number(12);
    varNumeSoliSeq  number(12);
    varNumeAtenRecla  Rec_Cabec_Recla.Num_Aten%TYPE;
    -----------
    varFormaPagoEnv     number(12);
    varFechaProgFactEnv date;
    varClaseSolicEnv    number(12);
    varOidAlmacEnv      number(12);
    --vartotal            number(12);
    varOidPeriodoProceso number;
    varOidPeriodoPedido  number;
    varSecuencia         number;
    ------------
    varMsg          varchar2(2);
    varRec_Lior_Seq number;

    lnIdPeriodoREGI         NUMBER;

    ------------
    varIndExenFlete number(1);
    varBaseFleteLocal number(12,2);
    varImpoFleteLocal number(12,2);
    varRecaFleteLocal number(12,2);
    varOtroRecaLocal number(12,2);
    varTotalFleteLocal number(12,2);
    varImpoFletTotaLoca number(12,2);
    varImpoDesc3TotaLoca number(12,2);
    varTotaPagaLoca number(12,2);
    varTotaGastAdmi number(12,2);
    varTotaGastAdmi2 number(12,2);

    varOidSoliCabeSOC    NUMBER;

    varMotAnulCam         number;
    varMotAnulPer         number;

    	varNumLote           rec_repos_anula.NUM_LOTE%TYPE;
    	varPedOidSoliCabe    rec_repos_anula.OID_SOLI_CABE%TYPE;
    	varRecOidCabeRecl    rec_repos_anula.OID_CABE_RECL%TYPE;

    varFactor         number;

    varModoAnul          varchar2(2);


    ------------
    varSecNumeDocu  Sto_Docum_Digit.Sec_Nume_Docu%TYPE;
    ------------

    l_oid_peri      NUMBER := 0;
    l_oid_peri_cdr  NUMBER := 0;

    CURSOR C_PEDIDOS is
     /* select terr_oid_terr,
             zzon_oid_zona,
             vepo_oid_valo_estr_geop,
             copa_oid_para_gral,
             tido_oid_tipo_docu_cont,
             TDOC_OID_TIPO_DOCU,
             SOCI_OID_SOCI,
             SBAC_OID_SBAC,
             datox,
             oid_soli_cabe,
             varPais
        from (SELECT DISTINCT a.terr_oid_terr,
                              a.zzon_oid_zona,
                              a.vepo_oid_valo_estr_geop,
                              d.copa_oid_para_gral,
                              e.tido_oid_tipo_docu_cont,
                              a.TDOC_OID_TIPO_DOCU,
                              a.SOCI_OID_SOCI,
                              a.SBAC_OID_SBAC,
                              (select ICTP_OID_TIPO_PROG
                                 from inc_concu_param_gener
                                where oid_para_gral = d.copa_oid_para_gral) as datox,
                              --a.ictp_oid_tipo_prog as datox,
                              a.oid_soli_cabe,
                              a.pais_oid_pais as varPais
              --                          ped_soca_seq.nextval as varOidCabe
                FROM ped_solic_cabec       a,
                     ped_solic_cabec       x,
                     rec_cabec_recla       b,
                     rec_opera_recla       c,
                     rec_linea_opera_recla d,
                     fac_tipo_docum        e
               WHERE a.val_nume_soli = to_number(psnum_docu_refe)
                 AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                 AND b.oid_cabe_recl = c.care_oid_cabe_recl
                 AND c.oid_oper_recl = d.opre_oid_oper_recl
                 AND e.oid_tipo_docu = x.tido_oid_tipo_docu
                 AND a.oid_soli_cabe = x.soca_oid_soli_cabe),
             inc_concu_param_gener
       where copa_oid_para_gral = oid_para_gral(+);*/

select terr_oid_terr,
             zzon_oid_zona,
             vepo_oid_valo_estr_geop,
             ---SQA 10.05.2012 decode(datox,null,null,decode(copa_oid_para_gene,null,(select min(oid_para_gral) from inc_concu_param_gener),copa_oid_para_gene)) copa_oid_para_gene,
             copa_oid_para_gene copa_oid_para_gene,
             tido_oid_tipo_docu_cont,
             TDOC_OID_TIPO_DOCU,
             SOCI_OID_SOCI,
             SBAC_OID_SBAC,
             datox,
             oid_soli_cabe,
             almc_oid_alma,
             varPais/*,
             varOidTsp,
             varCodTsp*/
        from (SELECT distinct a.terr_oid_terr,
                              a.zzon_oid_zona,
                              a.vepo_oid_valo_estr_geop,
                              ---SQA 10.05.2012 decode(z.ictp_oid_tipo_prog, null, null, z.copa_oid_para_gene) copa_oid_para_gene,
                              z.copa_oid_para_gene copa_oid_para_gene,
                              e.tido_oid_tipo_docu_cont,
                              a.TDOC_OID_TIPO_DOCU,
                              a.SOCI_OID_SOCI,
                              a.SBAC_OID_SBAC,
                              z.ictp_oid_tipo_prog as datox,
                              a.oid_soli_cabe,
                              a.almc_oid_alma,
                              a.pais_oid_pais as varPais/*,
                              Z.TSPA_OID_TIPO_SOLI_PAIS as varOidTsp,
                              ( select TS.COD_TIPO_SOLI
                                from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                                where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                and tsp.oid_tipo_soli_pais = Z.TSPA_OID_TIPO_SOLI_PAIS) as varCodTsp */
                FROM ped_solic_cabec       a,
                     ped_solic_cabec       z,
                     fac_tipo_docum        e
               WHERE a.val_nume_soli = to_number(psnum_docu_refe)
                 AND e.oid_tipo_docu = z.tido_oid_tipo_docu
                 and (select sum(num_unid_aten) from ped_solic_posic where soca_oid_soli_cabe=z.oid_soli_cabe)>0
                 and a.SBAC_OID_SBAC=888
                 and Z.TSPA_OID_TIPO_SOLI_PAIS not in( select tsp.oid_tipo_soli_pais
                                                         from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                                                         where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                                         and TS.COD_TIPO_SOLI in (select val_param from sto_param_gener_occrr where cod_para like 'STO_IND_SOL_ANUL%' union select 'SCUF' val_param from dual ) )
                                                         ----and TS.COD_TIPO_SOLI = 'SCUF' )
                 AND a.oid_soli_cabe = z.soca_oid_soli_cabe),
             inc_concu_param_gener
       where copa_oid_para_gene = oid_para_gral(+);

    TYPE pedidoRec IS RECORD(
      varTerri                number(12),
      varZona                 number(12),
      varVepo                 number(12),
      varCopa                 number(12),
      tido_oid_tipo_docu_cont number(12),
      varTipoDocIdent         number(12),
      varSocie                number(12),
      varSBAC_OID_SBAC        number(12),
      varTipoConcu            number(6),
      varOidSoliRefe          number(12),
      varAlmcOidAlma          number(12),
      varPais                 number(12)/*,
      varOidTsp               number(12),
      varCodTsp               VARCHAR2(4) */   );

    TYPE pedidoRecTab IS TABLE OF pedidoRec;
    pedidoRecord pedidoRecTab;

    CURSOR C_MERCADERIA(varTspaEnviaparm number, varOidPeriodoProcesoparm number, varZonaparm number, varTipoDocum2parm number) is
      select a.FOPA_OID_FORM_PAGO,
             --b.FEC_INIC,
             -- SQA 23-07 decode(b.FEC_INIC,null,(select fec_fina from cra_perio where oid_peri=varOidPeriodoProcesoparm), b.fec_inic) fec_inic,
             (select fec_fina from cra_perio where oid_peri=varOidPeriodoProcesoparm) fec_inic,
             d.OID_CLAS_SOLI,
             a.ALMC_OID_ALMA, --, a.TSOL_OID_TIPO_CONS
             decode(a.TIDO_OID_TIPO_DOCU,
                    null,
                    varTipoDocum2parm,
                    a.TIDO_OID_TIPO_DOCU) as tido_oid_tipo_docu,
             a.tsol_oid_tipo_cons
        from ped_tipo_solic_pais a,
             -- SQA 23-07 cra_crono           b,
             ped_tipo_solic      c,
             ped_clase_solic     d
       where a.OID_TIPO_SOLI_PAIS =
             decode(varTipoConcu, null, varTspaEnvia, varTspaEnviaPremio) --varTspaEnviaparm
         -- SQA 23-07 and a.CACT_OID_ACTI = b.CACT_OID_ACTI(+)
         -- SQA 23-07 and b.PERD_OID_PERI(+) = varOidPeriodoProcesoparm --< oid de periodo de proceso >, ???????
         -- SQA 23-07 and b.ZZON_OID_ZONA = varZonaparm
         and a.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
         and c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;

    TYPE mercaderiaRec IS RECORD(
      varFormaPagoEnv     number(12),
      varFechaProgFactEnv date,
      varClaseSolicEnv    number(12),
      varOidAlmacEnv      number(12),
      varTipoDocum        number(12),
      varTipoSolicCons    number(12)
      );

    TYPE mercaderiaRecTab IS TABLE OF mercaderiaRec;
    mercaderiaRecord mercaderiaRecTab;

    lsparametrobolec   sto_param_gener_occrr.val_param%TYPE;
    lsparametroconso   sto_param_gener_occrr.val_param%TYPE;
    lsparametropunta   sto_param_gener_occrr.val_param%TYPE;
    lsparametrorecped   sto_param_gener_occrr.val_param%TYPE;
    lsparametroultped   sto_param_gener_occrr.val_param%TYPE;
    lsparametrocarfs   sto_param_gener_occrr.val_param%TYPE;
    lsparametromav     sto_param_gener_occrr.val_param%TYPE;
    lsparametrodesc     sto_param_gener_occrr.val_param%TYPE;
    lsparametroflete   sto_param_gener_occrr.val_param%TYPE;
    lsparametroanufal  sto_param_gener_occrr.val_param%TYPE;
    lsparametrofecha   VARCHAR2(10);

    varOidOperaReclaBol number(12);

    lsValRecuCdr     REC_MOTIV_DEVOL.Val_Recu_Cdr%TYPE;
    lsValRecuPre     REC_MOTIV_DEVOL.Val_Recu_Pre%TYPE;

    lsValRecu        REC_MOTIV_DEVOL.Val_Recu_Cdr%TYPE;

  BEGIN

    insert into rec_repos_anula
      (num_recl,
       num_docu_refe,
       cod_clie,
       cod_usua,
       fec_ingr,
       cod_pais,
       cod_esta_proc,
       cod_erro,
       cod_corr,
       num_camp,
       num_lote,
       VAL_OBSE_ANUL,
       VAL_FLAG_NMP,
       VAL_FLAG_ENVI,
       VAL_MOTI_DEVO,
       VAL_COD_ANUL)
    values
      (psnum_recl,
       psnum_docu_refe,
       pscod_clie,
       pscod_usua,
       psfec_ingr,
       pscod_pais,
       0,
       pscod_erro,
       REC_SEQ_INGRE_ANULA_NMPS.NEXTVAL,
       psnumCampana,
       psNumeroLote,
       psobservaciones,
       psflagNotaMercaderia,
       psflagGenerarEnvia,
       psmotivodevolucion,
       pscodoperanul);


      ----- Si es un motivo de anulacion que se podra cambiar el pedido, activa una validacion nueva
        select count(*) into varMotAnulCam
        from REC_MOTIV_DEVOL rmd,
                (select val_param from STO_PARAM_GENER_OCCRR
                 where cod_para = 'STO_MOT_ANUL_GEST') spg
        where RMD.COD_MOTI_DEVO = spg.val_param
        and RMD.OID_MOTI_DEVO = psmotivodevolucion;

      ----- Si es un motivo de anulacion que se puede recuperar por perdida
        select count(*) into varMotAnulPer
        from REC_MOTIV_DEVOL rmd,
                (select val_param from STO_PARAM_GENER_OCCRR
                 where cod_para = 'STO_MOT_ANUL_PERD') spg
        where RMD.COD_MOTI_DEVO = spg.val_param
        and RMD.OID_MOTI_DEVO = psmotivodevolucion;

      ----- Datos del motivo de anulacion
        select Val_Recu_Cdr,Val_Recu_Pre into lsValRecuCdr, lsValRecuPre
        from REC_MOTIV_DEVOL rmd
        where RMD.OID_MOTI_DEVO = psmotivodevolucion;

      ----- Ubica el periodo actual
      SELECT c.oid_peri INTO l_oid_peri
        FROM bas_ctrl_fact   a, seg_perio_corpo b, cra_perio       c
       WHERE a.cod_peri = b.cod_peri
         AND b.oid_peri = c.peri_oid_peri
         AND a.sta_camp = 0
         AND a.ind_camp_act = 1;

      ----- Calcula el periodo que caeria el CDR
      l_oid_peri_cdr := sto_pkg_proce_valid_spvc.sto_fn_spvc_perio(pscod_clie,'0','L',0);


    varOidPeriodoProceso := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psnumCampana,
                                                                       2003,
                                                                       2001);

    ----lsparametroconso := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_CONSOL');
    lsparametrobolec := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_BOLELEC');
    lsparametroconso := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_TIPO_CALC_FACT'),'1');
    lsparametropunta := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_DESPUNT');
    lsparametrorecped := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_RECPED');
    lsparametroultped := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_ULTPED');
    lsparametrocarfs  := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_ANU_CARFS');
    lsparametromav   := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_ANU_MAV');
    lsparametrodesc  := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_ANU_DESC');
    lsparametroflete  := sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_ANU_FLETE');
    lsparametroanufal := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscod_pais,'STO_IND_ANU_FALT'),'N') ;

    SELECT to_char(sysdate,'dd/mm/yyyy') into lsparametrofecha FROM dual;


    DBMS_OUTPUT.PUT_LINE('varOidPeriodoProceso' || ' - ' || varOidPeriodoProceso);

    varPedOidSoliCabe := null;
    varRecOidCabeRecl := null;
    varNumLote := null;



    SELECT REC_SEQ_INGRE_ANULA_NMPS.CURRVAL INTO varSecuencia FROM DUAL;

    varMsg := '01';
    SELECT CASE
             WHEN psflagNotaMercaderia = 'S' THEN
              R.COD_OPER_NMPS
             ELSE
              R.COD_OPER_ANUL
           END
      INTO varCodOper
      FROM REC_PARAM_ANULA R
     WHERE R.NUM_CAMP = psnumCampana
     and R.COD_OPER_ANUL = pscodoperanul;  /* Se agrega el tipo de Anulacion*/

    DBMS_OUTPUT.PUT_LINE('varCodOper: ' || varCodOper);
    varMsg := '02';  ---- Valida si el pedido le pertenece al cliente

    begin
    select a.clie_oid_clie, a.perd_oid_peri
      INTO varOidClie, varOidPeriodoPedido
      from ped_solic_cabec a,mae_clien b
    where a.clie_oid_clie = b.oid_clie
    and a.val_nume_soli = to_number(psnum_docu_refe)
    and b.cod_clie = pscod_clie;
    exception
       when others then

           update rec_repos_anula
             set COD_ESTA_PROC = '9',
                 COD_ERRO = '68',
                 DES_ERRO = 'Pedido no pertenece a consultora'
             where num_camp = psnumCampana
               and num_recl = psnum_recl
               and cod_corr = varSecuencia
               and num_lote = psNumeroLote;
            RAISE e_no_pertenece;

    end;

    IF varOidPeriodoProceso <> varOidPeriodoPedido and
       pscodoperanul = 'AR' THEN

       varMsg := '69';  ---- Valida si es AR y de otro periodo

       DBMS_OUTPUT.PUT_LINE('Periodo diferente: ' || varCount);

       update rec_repos_anula
         set COD_ESTA_PROC = '9',
             COD_ERRO = varMsg,
             DES_ERRO = psdeserror
         where num_camp = psnumCampana
           and num_recl = psnum_recl
           and cod_corr = varSecuencia
           and num_lote = psNumeroLote;
        RAISE e_misma_campana;
     END IF;


    IF varOidPeriodoProceso <> varOidPeriodoPedido and
       pscodoperanul = 'AX' and varMotAnulPer <> 0  THEN

       varMsg := '70';  ---- Valida si es AX conr efacturar y de otro periodo

       DBMS_OUTPUT.PUT_LINE('Periodo diferente con AX: ' || varCount);

       update rec_repos_anula
         set COD_ESTA_PROC = '9',
             COD_ERRO = varMsg,
             DES_ERRO = psdeserror
         where num_camp = psnumCampana
           and num_recl = psnum_recl
           and cod_corr = varSecuencia
           and num_lote = psNumeroLote;
        RAISE e_misma_campana_AX;
     END IF;

   DBMS_OUTPUT.PUT_LINE('varCodOper: ' || varCodOper);

    varMsg := '03';

    select a.OID_OPER,
           b.OID_TIPO_OPER,
           a.TSPA_OID_SOLI_PAIS_GENE,
           a.TSPA_OID_SOLI_CON_STOC,
           g.IND_ORDE_COMP OC_DEV,
           h.IND_ORDE_COMP OC_ENV,
           a.TSPA_OID_SOLI_SIN_STOC
      INTO varOidOper,
           varOidTipoOper,
           varTspaDevuelve,
           varTspaEnvia,
           varOCDev,
           varOCEnv,
           varTspaEnviaPremio
      from rec_opera           a,
           rec_tipos_opera     b,
           ped_tipo_solic_pais c,
           ped_tipo_solic_pais d,
           ped_tipo_solic      e,
           ped_tipo_solic      f,
           ped_clase_solic     g,
           ped_clase_solic     h
     where a.OID_OPER = b.ROPE_OID_OPER
       and a.COD_OPER = varCodOper
       and b.VAL_TIPO_OPER = '01'
       and a.TSPA_OID_SOLI_CON_STOC = d.OID_TIPO_SOLI_PAIS(+)
       and c.TSOL_OID_TIPO_SOLI = e.OID_TIPO_SOLI
       and a.TSPA_OID_SOLI_PAIS_GENE = c.OID_TIPO_SOLI_PAIS
       and d.TSOL_OID_TIPO_SOLI = f.OID_TIPO_SOLI(+)
       and e.CLSO_OID_CLAS_SOLI = g.OID_CLAS_SOLI
       and f.CLSO_OID_CLAS_SOLI = h.OID_CLAS_SOLI(+);

    DBMS_OUTPUT.PUT_LINE('varCodOper: ' || varCodOper);
    DBMS_OUTPUT.PUT_LINE('varTspaEnvia: ' || varTspaEnvia);
    DBMS_OUTPUT.PUT_LINE('varTspaDevuelve: ' || varTspaDevuelve);

    DBMS_OUTPUT.PUT_LINE('REC_SEQ_INGRE_ANULA_NMPS.NEXTVAL' || ' - ' ||
                         varSecuencia);
    varMsg := '04';
    select COUNT(1)
      INTO varCount
      from ped_solic_cabec a,
           rec_cabec_recla b,
           rec_opera_recla c,
           rec_tipos_opera d,
           rec_opera       e,
           int_solic_conso_poven_cabec f
     where a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
       and b.OID_CABE_RECL = c.CARE_OID_CABE_RECL
       and c.TIOP_OID_TIPO_OPER = d.OID_TIPO_OPER
       and d.ROPE_OID_OPER = e.OID_OPER
       and b.oid_cabe_recl = f.oid_cabe_recl_refe(+)
       and (e.VAL_INGR_DEVU = 1 or e.IND_ENVI_GENE_DEVU = 1)
       and a.VAL_NUME_SOLI = TO_NUMBER(psnum_docu_refe)
       and b.esre_oid_esta_recl <> 5  ---- rechazado
       and ( lsparametroanufal = 'N' or
             ( lsparametroanufal = 'S' and
               E.COD_OPER not in('FA') ) or
             ( lsparametroanufal = 'M' and
               ( ( e.COD_OPER  not in ('FA','FM')
                   and nvl(f.IND_ORIG,'C') = 'M') or
                 nvl(f.IND_ORIG,'C') <> 'M') ) )
       AND a.tspa_oid_tipo_soli_pais IN
           (SELECT tspa_oid_tipo_soli_pais
              FROM int_lar_tipo_solici_pedido_dis);

    IF varCount > 0 THEN
      DBMS_OUTPUT.PUT_LINE('varCount: ' || varCount);

      ----DELETE FROM rec_repos_anula S
     update rec_repos_anula
       set COD_ESTA_PROC = '9',
           COD_ERRO = varMsg,
           DES_ERRO = psdeserror
       where num_camp = psnumCampana
         and num_recl = psnum_recl
         and cod_corr = varSecuencia
         and num_lote = psNumeroLote;
      RAISE e_encuentra_rows;
    END IF;

    /* Si no hay error continuamos */
    varMsg := '05';
    ------
    SELECT a.FEC_FACT,
           a.PERD_OID_PERI,
           a.PAIS_OID_PAIS,
           a.OID_SOLI_CABE,
           a.CLIE_OID_CLIE,
           a.ZTAD_OID_TERR_ADMI,
           a.SBTI_OID_SUBT_CLIE,
           a.TICL_OID_TIPO_CLIE,
           d.TIDO_OID_TIPO_DOCU,
           b.OID_CLIE_DIRE,
           rec_care_seq.nextval,
           rec_opre_seq.nextval,
           a.ZZON_OID_ZONA, -- ojo
           a.IND_EXEN_FLET,
           a.VAL_BASE_FLET_LOCA,
           a.VAL_IMPO_FLET_LOCA,
           a.VAL_RECA_FLET_LOCA,
           a.VAL_OTRO_RECA_LOCA,
           a.VAL_TOTA_FLET_LOCA,
           a.VAL_IMPO_FLET_TOTA_LOCA,
           a.val_impo_desc_3_tota_loca,
           a.VAL_TOTA_PAGA_LOCA,
           a.val_tota_gast_admi,
           a.val_tota_gast_admi2,
           d.tido_oid_tipo_docu
      INTO varFecDocuRefe,
           varOidPeriRefe,
           varOidPais,
           varOidSoliCabe,
           varOidClie,
           varOidTerrAdmin,
           varSubtipoClie,
           varTipoClie,
           varTipoDocum2, --- REEMPLA a varTipoDocum2
           varOidDireClie,
           varOidCabeRecla,
           varOidOperaRecla,
           varOidZonClie,
           varIndExenFlete,
           varBaseFleteLocal,
           varImpoFleteLocal,
           varRecaFleteLocal,
           varOtroRecaLocal,
           varTotalFleteLocal,
           varImpoFletTotaLoca,
           varImpoDesc3TotaLoca,
           varTotaPagaLoca,
           varTotaGastAdmi,
           varTotaGastAdmi2,
           vtido_oid_tipo_docu
          from ped_solic_cabec a,
           mae_clien_direc b,
           mae_clien_ident c,
           mae_tipo_docum  d
     where a.VAL_NUME_SOLI = to_number(psnum_docu_refe) --
       and a.CLIE_OID_CLIE = b.CLIE_OID_CLIE
       and a.CLIE_OID_CLIE = c.CLIE_OID_CLIE
       and c.TDOC_OID_TIPO_DOCU = d.OID_TIPO_DOCU
       and c.VAL_IDEN_DOCU_PRIN = 1
       and b.IND_ELIM = 0
       and b.IND_DIRE_PPAL = 1
       AND a.tspa_oid_tipo_soli_pais IN
           (SELECT tspa_oid_tipo_soli_pais
              FROM int_lar_tipo_solici_pedido_dis);


    select NVL(sum(decode(c.VAL_PREC_CATA_UNIT_LOCA,
                          0,
                          0,
                          c.VAL_PREC_FACT_TOTA_LOCA)),
               0)
      into varTotDev
      from ped_solic_cabec a, ped_solic_cabec b, ped_solic_posic c
     where a.VAL_NUME_SOLI = to_number(psnum_docu_refe)
       and a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
       and b.OID_SOLI_CABE = c.SOCA_OID_SOLI_CABE
       AND a.tspa_oid_tipo_soli_pais IN
           (SELECT tspa_oid_tipo_soli_pais
              FROM int_lar_tipo_solici_pedido_dis);

    ---- Verifica si el pedido a anular tiene una OC SOC
    select nvl(max(b.oid_soli_cabe),0)
      into varOidSoliCabeSOC
      from ped_solic_cabec a, ped_solic_cabec b
     where a.VAL_NUME_SOLI = to_number(psnum_docu_refe)
       and a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
       and b.tspa_oid_tipo_soli_pais = int_pkg_recla.gen_fn_devue_oid_tipo_solpa('SOC') ;


    DBMS_OUTPUT.PUT_LINE('varTotDev: ' || varTotDev);
    DBMS_OUTPUT.PUT_LINE('varOidOperaRecla: ' || varOidOperaRecla);

    varMsg := '06';

      select to_char(sysdate, 'yy') || lpad(VAL_ULTI_NUME_SOLI+1, 8, '0')
            INTO varNumeAtenRecla
            from ped_numer_solic
           where val_oper = 'REC034'
             and cod_suba = '_'
             and cod_pais = pscod_pais
             and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));

          update ped_numer_solic
             set VAL_ULTI_NUME_SOLI = VAL_ULTI_NUME_SOLI + 1
           where val_oper = 'REC034'
             and cod_suba = '_'
             and cod_pais = pscod_pais
             and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));

    varRecOidCabeRecl := varOidCabeRecla;

    ---lnIdPeriodoREGI := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psnumCampana);
    lnIdPeriodoREGI := l_oid_peri ;

    INSERT INTO REC_CABEC_RECLA
      (OID_CABE_RECL,
       NUM_ATEN,
       NUM_RECL,
       FEC_DOCU_REFE,
       NUM_TOTA_ENVI,
       NUM_TOTA_DEVU,
       IMP_SALD_PAGA,
       COD_USUA_INGR,
       FEC_INGR,
       PAIS_OID_PAIS,
       SOCA_OID_SOLI_CABE,
       PERD_OID_PERI_DOCU_REFE,
       CLIE_OID_CLIE,
       MRDB_OID_MOTI_RECH,
       PERD_OID_PERI_RECL,
       MRDB_OID_MOTI_DESB,
       ZTAD_OID_TERR_ADMI,
       SBTI_OID_SUBT_CLIE,
       TIDO_OID_TIPO_DOCU,
       ESRE_OID_ESTA_RECL,
       MOBL_OID_MOTI_BLOQ,
       TIIN_OID_TIPO_INGR,
       TICL_OID_TIPO_CLIE,
       FEC_ULTI_ACTU,
       NUM_ATEN_INTE,
       PERD_OID_PERI_REGI)
    VALUES
      (varOidCabeRecla,
       varNumeAtenRecla,
       psnum_recl,
       --to_number(psnum_docu_refe),
       varFecDocuRefe,
       0,
       varTotDev,
       varTotDev * -1,
       psusuario, ---  codigo de usuario ?????
       trunc(sysdate),
       varOidPais,
       varOidSoliCabe,
       varOidPeriRefe,
       varOidClie,
       NULL,
       varOidPeriodoProceso, --< oid de periodo de proceso >, --- ??
       NULL,
       varOidTerrAdmin,
       varSubtipoClie,
       varTipoDocum,
       6,
       NULL,
       2,
       varTipoClie,
       sysdate,
       NULL,
       lnIdPeriodoREGI );
    DBMS_OUTPUT.PUT_LINE('X1');
    varMsg := '07';
    INSERT INTO REC_OPERA_RECLA
      (OID_OPER_RECL,
       NUM_SECU_OPER,
       IMP_MONT_PERD,
       VAL_PORC_PERD,
       CARE_OID_CABE_RECL,
       TIBL_OID_TIPO_BLOQ,
       MOBL_OID_MOTI_BLOQ,
       INEM_OID_INDI_ENTR_MERC,
       ASPE_OID_ASUM_PERD,
       PROD_OID_PROD,
       CLIE_OID_CLIE,
       MRDB_OID_MOTI_RECH_DESB,
       MRDB_OID_MOTI_DESB,
       PPER_OID_PREC_PERD,
       ESOP_OID_ESTA_OPER,
       CLIE_OID_RESP_PERD,
       TPOS_OID_TIPO_POSI,
       SOCA_OID_SOLI_CABE,
       PERD_OID_PERI_RECL,
       TSPA_OID_TIPO_SOLI_PAIS,
       TIOP_OID_TIPO_OPER,
       IND_ATEN,
       FEC_FACT,
       FEC_ULTI_ACTU)
    VALUES
      (varOidOperaRecla,
       1,
       0,
       NULL,
       varOidCabeRecla,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       2,
       NULL,
       NULL,
       varOidSoliCabe,
       varOidPeriodoProceso, ---< oid de periodo de proceso >, ?? FALTA
       NULL,
       varOidTipoOper,
       1,
       NULL,
       sysdate);

    DBMS_OUTPUT.PUT_LINE('varTspaDevuelve 1 : ' || varTspaDevuelve);
    DBMS_OUTPUT.PUT_LINE('varOidOperaRecla: 2 : ' || varOidOperaRecla);
    varMsg := '08';

    INSERT INTO REC_LINEA_OPERA_RECLA
      (TSPA_OID_TIPO_SOLI_PAIS, --
       OID_LINE_OPER_RECL,
       NUM_LINE,
       NUM_UNID_RECL,
       VAL_PREC,
       IMP_ABON,
       IMP_CARG,
       NUM_UNID_DEVU,
       IND_ESTA,
       IMP_MONT_PERD,
       IND_ATEN,
       OPRE_OID_OPER_RECL,
       TOFE_OID_TIPO_OFER,
       PPER_OID_PREC_PERD,
       TPOS_OID_TIPO_POSI,
       TIMO_OID_TIPO_MOVI,
       PROD_OID_PROD,
       MAFA_OID_MATR_FACT,
       MODV_OID_MOTI_DEVO,
       MRDB_OID_MOTI_RECH_DESB,
       --     TSPA_OID_TIPO_SOLI_PAIS, ---
       SOPO_OID_SOLI_POSI,
       COPA_OID_PARA_GRAL,
       PANP_OID_PARA_NIVE_PREM,
       LOPA_OID_LOTE_PREM_ARTI,
       VAL_PREC_CONT)
      (select varTspaDevuelve,
              rec_lior_seq.nextval,
              rownum,
              num_unid_aten -
                  ( select decode( lsparametroanufal,'N',0,
                             decode( lsparametroanufal,'S',nvl(sum(num_unid_recl),0),
                               decode( lsparametroanufal,'M',
                                       nvl(sum(num_unid_recl),0),0)
                                                      )
                                                      ) num_unid_recl
                    from rec_linea_opera_recla rlo,
                            rec_opera_recla ror,
                            rec_cabec_recla rcr,
                            rec_tipos_opera rto,
                            rec_opera       ro,
                            int_solic_conso_poven_cabec isp
                    where RLO.OPRE_OID_OPER_RECL = ROR.OID_OPER_RECL
                    and ROR.CARE_OID_CABE_RECL = RCR.OID_CABE_RECL
                    and ROR.TIOP_OID_TIPO_OPER = RTO.OID_TIPO_OPER
                    and RTO.ROPE_OID_OPER = RO.OID_OPER
                    and rcr.oid_cabe_recl = isp.oid_cabe_recl_refe(+)
                    and ( ( lsparametroanufal <> 'M' and RO.COD_OPER in('FA')) or
                          ( lsparametroanufal = 'M' and RO.COD_OPER in('FA','FM')
                            and nvl( isp.ind_orig,'C') = 'M' )
                            and rlo.timo_oid_tipo_movi = 2 )
                    and rcr.esre_oid_esta_recl <> 5  ---- rechazado
                    and RLO.SOPO_OID_SOLI_POSI = C.OID_SOLI_POSI) ,
              --c.val_prec_cata_unit_loca,
              --c.val_prec_cata_tota_loca,
              decode(c.val_prec_cata_unit_loca,0,0,c.val_prec_fact_unit_loca),
              decode(c.val_prec_cata_unit_loca,0,0,c.val_prec_fact_tota_loca),
              0,
              0,
              'E',
              NULL,
              1,
              varOidOperaRecla,
              d.TOFE_OID_TIPO_OFER,
              NULL,
              NULL,
              2,
              c.PROD_OID_PROD,
              e.OID_MATR_FACT,
              TO_NUMBER(psmotivodevolucion),
              NULL,
              --            NULL,
              c.OID_SOLI_POSI,
              b.COPA_OID_PARA_GENE,
              (select h.PANP_OID_PARA_NIVE_PREM
                 from inc_param_gener_premi f,
                      inc_param_nivel_premi g,
                      inc_premi_artic       h,
                      inc_lote_premi_artic  i,
                      inc_artic_lote        j
                where f.OID_PARA_GENE_PREM = g.PAGP_OID_PARA_GENE_PREM
                  and g.OID_PARA_NIVE_PREM = h.PANP_OID_PARA_NIVE_PREM
                  and h.OID_PREM_ARTI = i.PRAR_OID_PREM_ARTI
                  and f.COPA_OID_PARA_GRAL = b.COPA_OID_PARA_GENE
                  and i.NUM_PREM = b.NUM_PREM
                  and i.OID_LOTE_PREM_ARTI = j.LOPA_OID_LOTE_PREM_ARTI
                  and j.PROD_OID_PROD = c.PROD_OID_PROD) aa,
              (select i.OID_LOTE_PREM_ARTI
                 from inc_param_gener_premi f,
                      inc_param_nivel_premi g,
                      inc_premi_artic       h,
                      inc_lote_premi_artic  i,
                      inc_artic_lote        j
                where f.OID_PARA_GENE_PREM = g.PAGP_OID_PARA_GENE_PREM
                  and g.OID_PARA_NIVE_PREM = h.PANP_OID_PARA_NIVE_PREM
                  and h.OID_PREM_ARTI = i.PRAR_OID_PREM_ARTI
                  and f.COPA_OID_PARA_GRAL = b.COPA_OID_PARA_GENE
                  and i.NUM_PREM = b.NUM_PREM
                  and i.OID_LOTE_PREM_ARTI = j.LOPA_OID_LOTE_PREM_ARTI
                  and j.PROD_OID_PROD = c.PROD_OID_PROD) bb,
              c.VAL_PREC_CONT_UNIT_LOCA
         from ped_solic_cabec                a,
              ped_solic_cabec                b,
              ped_solic_posic                c,
              pre_ofert_detal                d,
              pre_matri_factu                e,
              INT_LAR_TIPO_SOLICI_PEDIDO_DIS dis
        where a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
          and a.TSPA_OID_TIPO_SOLI_PAIS = dis.TSPA_OID_TIPO_SOLI_PAIS
          and b.OID_SOLI_CABE = c.SOCA_OID_SOLI_CABE
          and a.VAL_NUME_SOLI = to_number(psnum_docu_refe)
          and c.OFDE_OID_DETA_OFER = d.OID_DETA_OFER(+)
          and d.OID_DETA_OFER = e.OFDE_OID_DETA_OFER(+)
          and c.num_unid_aten>0
          and b.TSPA_OID_TIPO_SOLI_PAIS not in( select tsp.oid_tipo_soli_pais
                                                from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                                                where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                                and TS.COD_TIPO_SOLI in (select val_param from sto_param_gener_occrr where cod_para like 'STO_IND_SOL_ANUL%' union select 'SCUF' val_param from dual ) )
                                              ----  and TS.COD_TIPO_SOLI = 'SCUF' )
          and c.espo_oid_esta_posi<>2
          );

    --- Si esta activo el parametro de Boleta Electronica
    if lsparametrobolec = '1' then

       varOidOperaReclaBol := varOidOperaRecla;

       sto_pkg_envio_valid_sicc.sto_pr_actua_boleta_elect (varOidOperaReclaBol);

    END IF;

    varMsg := '09';
    SELECT rec_lior_seq.nextval - 1 INTO varRec_Lior_Seq FROM DUAL;

    varMsg := '10';
    select max(NUM_LINE)+1
      INTO varNumLine
      from REC_LINEA_OPERA_RECLA
     WHERE OPRE_OID_OPER_RECL = varOidOperaRecla;


    IF psflagNotaMercaderia = 'S' THEN
      varMsg := '11';
      INSERT INTO REC_LINEA_OPERA_RECLA
        (TSPA_OID_TIPO_SOLI_PAIS,
         OID_LINE_OPER_RECL,
         NUM_LINE,
         NUM_UNID_RECL,
         VAL_PREC,
         IMP_CARG,
         NUM_UNID_DEVU,
         IND_ESTA,
         IMP_MONT_PERD,
         IND_ATEN,
         OPRE_OID_OPER_RECL,
         TOFE_OID_TIPO_OFER,
         PPER_OID_PREC_PERD,
         TPOS_OID_TIPO_POSI,
         TIMO_OID_TIPO_MOVI,
         PROD_OID_PROD,
         MAFA_OID_MATR_FACT,
         MODV_OID_MOTI_DEVO,
         MRDB_OID_MOTI_RECH_DESB,
         SOPO_OID_SOLI_POSI,
         COPA_OID_PARA_GRAL,
         PANP_OID_PARA_NIVE_PREM,
         LOPA_OID_LOTE_PREM_ARTI,
         VAL_PREC_CONT)
        (select varTspaEnvia,
                rec_lior_seq.nextval,
                rownum + varNumLine,
                num_unid_aten,
                decode(c.val_prec_cata_unit_loca,0,0,c.val_prec_fact_unit_loca),
                decode(c.val_prec_cata_unit_loca,0,0,c.val_prec_fact_tota_loca),
                0,
                'E',
                NULL,
                1,
                varOidOperaRecla,
                d.TOFE_OID_TIPO_OFER,
                NULL,
                NULL,
                1,
                c.PROD_OID_PROD,
                e.OID_MATR_FACT,
                TO_NUMBER(psmotivodevolucion),
                NULL,
                c.OID_SOLI_POSI,
                ----SQA 10.05.2012 decode(b.ictp_oid_tipo_prog,null,null,decode(b.copa_oid_para_gene,null,(select min(oid_para_gral) from inc_concu_param_gener),b.copa_oid_para_gene)) copa_oid_para_gene,
                b.copa_oid_para_gene copa_oid_para_gene,
                (select h.PANP_OID_PARA_NIVE_PREM
                   from inc_param_gener_premi f,
                        inc_param_nivel_premi g,
                        inc_premi_artic       h,
                        inc_lote_premi_artic  i,
                        inc_artic_lote        j
                  where f.OID_PARA_GENE_PREM = g.PAGP_OID_PARA_GENE_PREM
                    and g.OID_PARA_NIVE_PREM = h.PANP_OID_PARA_NIVE_PREM
                    and h.OID_PREM_ARTI = i.PRAR_OID_PREM_ARTI
                    and f.COPA_OID_PARA_GRAL = b.COPA_OID_PARA_GENE
                    and i.NUM_PREM = b.NUM_PREM
                    and i.OID_LOTE_PREM_ARTI = j.LOPA_OID_LOTE_PREM_ARTI
                    and j.PROD_OID_PROD = c.PROD_OID_PROD) aa,
                (select i.OID_LOTE_PREM_ARTI
                   from inc_param_gener_premi f,
                        inc_param_nivel_premi g,
                        inc_premi_artic       h,
                        inc_lote_premi_artic  i,
                        inc_artic_lote        j
                  where f.OID_PARA_GENE_PREM = g.PAGP_OID_PARA_GENE_PREM
                    and g.OID_PARA_NIVE_PREM = h.PANP_OID_PARA_NIVE_PREM
                    and h.OID_PREM_ARTI = i.PRAR_OID_PREM_ARTI
                    and f.COPA_OID_PARA_GRAL = b.COPA_OID_PARA_GENE
                    and i.NUM_PREM = b.NUM_PREM
                    and i.OID_LOTE_PREM_ARTI = j.LOPA_OID_LOTE_PREM_ARTI
                    and j.PROD_OID_PROD = c.PROD_OID_PROD) bb,
                c.VAL_PREC_CONT_UNIT_LOCA
           from ped_solic_cabec                a,
                ped_solic_cabec                b,
                ped_solic_posic                c,
                pre_ofert_detal                d,
                pre_matri_factu                e,
                INT_LAR_TIPO_SOLICI_PEDIDO_DIS dis
          where a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
            and a.TSPA_OID_TIPO_SOLI_PAIS = dis.TSPA_OID_TIPO_SOLI_PAIS
            and b.OID_SOLI_CABE = c.SOCA_OID_SOLI_CABE
            and a.VAL_NUME_SOLI = to_number(psnum_docu_refe)
            and c.OFDE_OID_DETA_OFER = d.OID_DETA_OFER(+)
            and d.OID_DETA_OFER = e.OFDE_OID_DETA_OFER(+)
            and c.num_unid_aten>0
            and b.TSPA_OID_TIPO_SOLI_PAIS not in( select tsp.oid_tipo_soli_pais
                                                from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                                                where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                                and TS.COD_TIPO_SOLI = 'SCUF' )
            and c.espo_oid_esta_posi<>2
            );
    END IF;

    varMsg := '13';

    OPEN C_PEDIDOS;
    LOOP
      FETCH C_PEDIDOS BULK COLLECT
        INTO pedidoRecord LIMIT W_FILAS;
      IF pedidoRecord.COUNT > 0 THEN
        FOR x IN pedidoRecord.FIRST .. pedidoRecord.LAST LOOP
          varTerri                := pedidoRecord(x).varTerri;
          varZona                 := pedidoRecord(x).varZona;
          varVepo                 := pedidoRecord(x).varVepo;
          varCopa                 := pedidoRecord(x).varCopa;
          tido_oid_tipo_docu_cont := pedidoRecord(x)
                                    .tido_oid_tipo_docu_cont;
          varTipoDocIdent         := pedidoRecord(x).varTipoDocIdent;
          varSocie                := pedidoRecord(x).varSocie;
          varSBAC_OID_SBAC        := pedidoRecord(x).varSBAC_OID_SBAC; -- ***
          varTipoConcu            := pedidoRecord(x).varTipoConcu;
          varOidSoliRefe          := pedidoRecord(x).varOidSoliRefe;
          varPais                 := pedidoRecord(x).varPais; --***

          varMsg := '12';
          SELECT ped_soca_seq.nextval INTO varOidCabe FROM DUAL;

          varMsg := '14';/*
          select a.FOPA_OID_FORM_PAGO,
                 b.FEC_INIC,
                 d.OID_CLAS_SOLI,
                 a.ALMC_OID_ALMA,
                 a.TSOL_OID_TIPO_CONS TSOL_OID_TIPO_CONS
            into varFormaPagoDev,
                 varFechaProgFactDev,
                 varClaseSolicDev,
                 varOidAlmacDev,
                 vartsolOidTipoCons
            from ped_tipo_solic_pais a,
                 cra_crono           b,
                 ped_tipo_solic      c,
                 ped_clase_solic     d
           where a.OID_TIPO_SOLI_PAIS = varTspaDevuelve
             and a.CACT_OID_ACTI = b.CACT_OID_ACTI
             and b.PERD_OID_PERI = varOidPeriodoProceso --< oid de periodo de  proceso >  ????
             and b.ZZON_OID_ZONA = varOidZonClie
             and a.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
             and c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;
*/
select a.FOPA_OID_FORM_PAGO,
                 d.OID_CLAS_SOLI,
                 a.ALMC_OID_ALMA,
                 a.TSOL_OID_TIPO_CONS TSOL_OID_TIPO_CONS
            into varFormaPagoDev,
                 varClaseSolicDev,
                 varOidAlmacDev,
                 vartsolOidTipoCons
            from ped_tipo_solic_pais a,
                 ped_tipo_solic      c,
                 ped_clase_solic     d
           where a.OID_TIPO_SOLI_PAIS = varTspaDevuelve
             and a.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
             and c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;

            select b.FEC_fina
            into varFechaProgFactDev
            from cra_perio           b
           where b.oid_peri = varOidPeriodoProceso ;

          varTipoSoliCons := vartsolOidTipoCons;

          ---- Se toma el almacen del pedido
          varOidAlmacDev  := pedidoRecord(x).varAlmcOidAlma ;


          DBMS_OUTPUT.PUT_LINE('varOidAlmacDev' || ' - ' || varOidAlmacDev);

          varMsg := '15';

          /*select to_char(sysdate, 'yy') || lpad(VAL_ULTI_NUME_SOLI+1, 8, '0')
            INTO varNumeSoli
            from ped_numer_solic
           where val_oper = 'PED001'
             and cod_cana = 'VD'
             and cod_acce = 'GZ'
             and cod_suba = '000'
             and cod_pais = pscod_pais
             and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));

          varMsg := '16';
          update ped_numer_solic
             set VAL_ULTI_NUME_SOLI = VAL_ULTI_NUME_SOLI + 2
           where val_oper = 'PED001'
             and cod_cana = 'VD'
             and cod_acce = 'GZ'
             and cod_suba = '000'
             and cod_pais = pscod_pais
             and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));*/

          varNumeSoliSeq := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscod_pais,
                                                                    'PED001',
                                                                    '000',
                                                                    1);

          varNumeSoli := to_char(SYSDATE,'YY') || lpad(varNumeSoliSeq,8,'0') + 1;

    varPedOidSoliCabe := varOidCabe;


         ---- Factor para que el flete no se cobre en incentivos
         varFactor := 1;
         if lsparametroflete = 'S' and
            (varCopa is not null )
            /*substr(pedidoRecord(x).varCodTsp,1,2) = 'SI' */
            then
                varFactor := 0;
         end if;

          varMsg := '17';
          INSERT INTO PED_SOLIC_CABEC
            (OID_SOLI_CABE,
             FEC_PROG_FACT,
             TSPA_OID_TIPO_SOLI_PAIS,
             TIDS_OID_TIPO_DESP,
             ALMC_OID_ALMA,
             MODU_OID_MODU,
             TICL_OID_TIPO_CLIE,
             PERD_OID_PERI,
             CLIE_OID_CLIE,
             CLIE_OID_CLIE_RECE_FACT,
             CLIE_OID_CLIE_PAGA,
             CLIE_OID_CLIE_DEST,
             CLDI_OID_CLIE_DIRE,
             TDOC_OID_TIPO_DOCU,
             SOCI_OID_SOCI,
             SBAC_OID_SBAC, -- *****
             TERR_OID_TERR,
             ZZON_OID_ZONA,
             VAL_NUME_SOLI, -- *****
             VAL_USUA,
             VAL_TASA_IMPU,
             FEC_CRON,
             IND_PERM_UNIO_SOL,
             NUM_DOCU_ORIG,
             VAL_BASE_FLET_LOCA,
             VAL_IMPO_FLET_LOCA,
             VAL_IMPO_FLET_TOTA_LOCA,
             VAL_IMPO_FLET_SIN_IMPU_TOTA,
             VAL_RECA_FLET_LOCA,
             VAL_OTRO_RECA_LOCA,
             VAL_TOTA_PAGA_LOCA,
             VAL_PREC_CATA_TOTA_LOCA,
             VAL_PREC_CATA_SIN_IMPU_TOTA,
             VAL_PREC_FACT_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_LOCA,
             VAL_IMPO_DESC_1_TOTA_LOCA,
             VAL_IMPO_DESC_1_TOTA_DOCU,
             VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
             VAL_IMPO_DESC_3_TOTA_DOCU,
             VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
             VAL_IMPO_DESC_TOTA_LOCA,
             VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
             VAL_IMPO_REDO_LOCA,
             VAL_BASE_FLET_DOCU,
             VAL_IMPO_FLET_DOCU,
             VAL_IMPO_DESC_TOTA_DOCU,
             VAL_IMPO_FLET_SIN_IMPU_DOCU,
             VAL_RECA_FLET_DOCU,
             VAL_OTRO_RECA_DOCU,
             VAL_TOTA_FLET_DOCU,
             VAL_IMPO_FLET_TOTA_DOCU,
             VAL_TOTA_FLET_LOCA,
             VAL_TOTA_PAGA_DOCU,
             VAL_PREC_CATA_TOTA_DOCU,
             VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
             VAL_PREC_CONT_TOTA_LOCA,
             VAL_PREC_CONT_SIN_IMPU_TOTA,
             VAL_PREC_CONT_SIN_IMPU_TOTA_1,
             VAL_PREC_FACT_TOTA_DOCU,
             VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
             VAL_PREC_NETO_TOTA_DOCU,
             VAL_PREC_NETO_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_DOCU,
             VAL_IMPO_REDO_DOCU,
             VAL_IMPO_REDO_CONS_LOCA,
             VAL_IMPO_REDO_CONS_DOCU,
             IND_OC,
             IND_PEDI_PRUE,
             IND_TS_NO_CONSO,
             NUM_PREM,
             VAL_IMPO_DESC_3_TOTA_LOCA,
             VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
             PAIS_OID_PAIS, -- *****
             TIDO_OID_TIPO_DOCU,
             VEPO_OID_VALO_ESTR_GEOP,
             ESSO_OID_ESTA_SOLI,
             COPA_OID_PARA_GENE,
             GRPR_OID_GRUP_PROC,
             SBTI_OID_SUBT_CLIE,
             TSPA_OID_TIPO_SOLI_PAIS_CONS,
             FOPA_OID_FORM_PAGO,
             CLSO_OID_CLAS_SOLI,
             ZTAD_OID_TERR_ADMI,
             OPER_OID_OPER,
             PROC_OID_PROC,
             SOCA_OID_DOCU_REFE,
             IND_INTE_LARI_GENE,
             ICTP_OID_TIPO_PROG,
             IND_EXEN_FLET,
             VAL_GLOS_OBSE,
             VAL_TOTA_GAST_ADMI,
             VAL_TOTA_GAST_ADMI2
             )
          VALUES
            ( --varOidCabe,
             varOidCabe,
             varFechaProgFactDev,
             varTspaDevuelve,
             3,
             NVL(varOidAlmacDev, 2001),
             15,
             varTipoClie,
             varOidPeriodoProceso, --< oid de periodo de proceso >, ???????
             varOidClie,
             varOidClie,
             varOidClie,
             varOidClie,
             varOidDireClie,
             varTipoDocIdent,
             varSocie,
             varSBAC_OID_SBAC, -- *****
             varTerri,
             varZona,
             varNumeSoli, -- CODIGO NEW SOLICITUD  -- *****
             psusuario, --< codigo de usuario >,
             0,
             TRUNC(sysdate),
             1,
             to_number(psnum_docu_refe),
             decode(varTipoConcu, null, varBaseFleteLocal*varFactor*-1,0),
             decode(varTipoConcu, null, varImpoFleteLocal*varFactor*-1,0),
             decode(varTipoConcu, null, varImpoFletTotaLoca*varFactor*-1,0),
             0,
             decode(varTipoConcu, null, varRecaFleteLocal*varFactor*-1,0),
             decode(varTipoConcu, null, varOtroRecaLocal*-1,0),
             decode(varTipoConcu, null, varTotaPagaLoca*-1,0),
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             decode(varTipoConcu, null, varBaseFleteLocal*varFactor*-1,0),
             decode(varTipoConcu, null, varImpoFleteLocal*varFactor*-1,0),
             0,
             0,
             decode(varTipoConcu, null, varRecaFleteLocal*varFactor*-1,0),
             decode(varTipoConcu, null, varOtroRecaLocal*-1,0),
             decode(varTipoConcu, null, varTotalFleteLocal*varFactor*-1,0),
             decode(varTipoConcu, null, varImpoFletTotaLoca*varFactor*-1,0),
             decode(varTipoConcu, null, varTotalFleteLocal*varFactor*-1,0),
             decode(varTipoConcu, null, varTotaPagaLoca*-1,0),
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             1,
             0,
             1,
             NULL,
             nvl(varImpoDesc3TotaLoca,0) , --- 0,
             0,
             varPais, -- *****
             tido_oid_tipo_docu_cont,
             --varTipoDocum,
             varVepo,
             1,
             varCopa,
             3,
             varSubtipoClie,
             varTipoSoliCons,
             varFormaPagoDev,
             varClaseSolicDev,
             varOidTerrAdmin,
             21,
             1,
             varOidSoliRefe,
             0,
             varTipoConcu,
             varIndExenFlete,
             'ANULACION DE PEDIDO',
             decode(varTipoConcu, null, varTotaGastAdmi*varFactor*-1,0),
             decode(varTipoConcu, null, varTotaGastAdmi2*varFactor*-1,0)
             );

          varMsg := '18';
          /**/
          INSERT INTO PED_SOLIC_POSIC
            (OID_SOLI_POSI,
             COD_POSI,
             NUM_UNID_DEMA,
             NUM_UNID_POR_ATEN,
             VAL_TASA_IMPU,
             SOCA_OID_SOLI_CABE,
             TPOS_OID_TIPO_POSI,
             PROD_OID_PROD,
             VAL_PREC_CATA_UNIT_LOCA,
             VAL_PREC_CONT_UNIT_LOCA,
             VAL_PREC_CATA_UNIT_DOCU,
             VAL_PREC_CONTA_UNIT_DOCU,
             VAL_PREC_FACT_UNIT_LOCA,
             VAL_PREC_FACT_UNIT_DOCU,
             VAL_PREC_SIN_IMPU_UNIT_LOCA,
             VAL_PREC_SIN_IMPU_UNIT_DOCU,
             VAL_PREC_SIN_IMPU_TOTA_DOCU,
             VAL_IMPO_DESC_UNIT_LOCA,
             VAL_IMPO_DESC_UNIT_DOCU,
             VAL_PREC_NETO_UNIT_LOCA,
             VAL_PREC_NETO_TOTA_DOCU,
             VAL_PREC_NETO_UNIT_DOCU,
             VAL_PREC_TOTA_TOTA_LOCA,
             VAL_PREC_TOTA_TOTA_DOCU,
             VAL_IMPO_IMPU_UNIT_LOCA,
             VAL_IMPO_IMPU_UNIT_DOCU,
             VAL_IMPO_DESC_TOTA_DOCU,
             VAL_IMPO_IMPU_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_DOCU,
             VAL_IMPO_DESC_TOTA_LOCA,
             VAL_PREC_TOTA_UNIT_LOCA,
             VAL_PREC_TOTA_UNIT_DOCU,
             VAL_PREC_CONT_TOTA_LOCA,
             VAL_PREC_CATA_TOTA_LOCA,
             VAL_PREC_CATA_TOTA_DOCU,
             VAL_PREC_CONT_TOTA_DOCU,
             VAL_PORC_DESC,
             VAL_PREC_CATA_TOTA_LOCA_UNID,
             NUM_UNID_DEMA_REAL,
             NUM_UNID_COMPR,
             VAL_CODI_VENT_FICT,
             VAL_PREC_FACT_TOTA_LOCA,
             VAL_PREC_FACT_TOTA_DOCU,
             VAL_PREC_SIN_IMPU_TOTA_LOCA,
             VAL_PREC_NETO_TOTA_LOCA,
             OFDE_OID_DETA_OFER,
             ESPO_OID_ESTA_POSI,
             STPO_OID_SUBT_POSI,
             VAL_CODI_VENT,
             OID_LINE_OPER_RECL)
            (select ped_sopo_seq.nextval,
                    rownum,
                    z.num_unid_recl * -1,
                    z.num_unid_recl * -1,
                    0,
                    varOidCabe,
                    10,
                    z.prod_oid_prod,
                    w.val_prec_cata_unit_loca,
                    w.val_prec_cont_unit_loca,
                    0,--w.val_prec_cata_unit_loca,
                    0,
                    z.val_prec,
                    0,
                    0,
                    0,
                    0,
                    w.VAL_IMPO_DESC_UNIT_LOCA,
                    w.VAL_IMPO_DESC_UNIT_LOCA,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    w.val_porc_desc,
                    0,
                    z.num_unid_recl * -1,
                    z.num_unid_recl * -1,
                    decode(z.copa_oid_para_gral,
                           null,
                           null,
                           w.VAL_CODI_VENT_FICT),
                    0,
                    0,
                    0,
                    0,
                    w.ofde_oid_deta_ofer,
                    4,
                    14,
                    decode(z.copa_oid_para_gral,
                           null,
                           w.VAL_CODI_VENT,
                           null),
                    z.OID_LINE_OPER_RECL
               from rec_cabec_recla       x,
                    rec_opera_recla       y,
                    rec_linea_opera_recla z,
                    ped_solic_posic       w
              where x.oid_cabe_recl = y.care_oid_cabe_recl
                and y.oid_oper_recl = z.opre_oid_oper_recl
                and z.sopo_oid_soli_posi = w.oid_soli_posi
                and x.oid_cabe_recl = varOidCabeRecla
                and nvl(z.COPA_OID_PARA_GRAL, 0) = nvl(varCopa, 0)
                and z.TIMO_OID_TIPO_MOVI = 2
                and w.espo_oid_esta_posi<>2
                );

               insert into rec_solic_opera values (rec_soop_seq.nextval,null,varOidOperaRecla,varOidCabe,varTspaDevuelve);

                ---- Genera Consolidados SQA
                ---if (lsparametroconso = '1' or lsparametroconso = '2') then
                if (lsparametroconso is not null) then
                   sto_pkg_envio_valid_sicc.sto_pr_genera_consolidado(varOidCabe,
                                                                      lsparametrofecha,
                                                                      pscod_pais,
                                                                      lsparametroconso,
                                                                      'A');

                   --- Actualiza estados en reclamos
                    update REC_CABEC_RECLA set ESRE_OID_ESTA_RECL = 4 where OID_CABE_RECL = varOidCabeRecla;

                    update REC_OPERA_RECLA set ESOP_OID_ESTA_OPER = 5 where OID_OPER_RECL = varOidOperaRecla;

                   --- Descuenta puntaje si no es Anulacion especial
                    if lsparametropunta = '1' and varCodOper <> 'AE' then
                       IF psflagNotaMercaderia = 'S' AND psflagGenerarEnvia = 'S' THEN
                          DBMS_OUTPUT.PUT_LINE('No se descuenta puntaje para una NMP con envia' );
                       else
                       INC_PKG_PROCE_INCEN.INC_PR_TRATA_ANULA_SOLIC(pscod_pais,varOidCabe,psusuario);
                    end if;
                    end if;

                  /*   --- Elimina el cargo de Familia segura por anulacion
                    if lsparametrocarfs = '1' then
                       CCC_PKG_PROCE.CCC_PR_GENER_ANUL_FAMIL_SEGUR(varOidSoliCabe);
                    end if;
                  */

                end if;


          --new 18-02-2009
          IF psflagNotaMercaderia = 'S' AND psflagGenerarEnvia = 'S' THEN
            varMsg := '19';
            OPEN C_MERCADERIA(varTspaEnvia,
                              varOidPeriodoProceso,
                              varOidZonClie,
                              varTipoDocum2);
            LOOP
              FETCH C_MERCADERIA BULK COLLECT
                INTO mercaderiaRecord LIMIT W_FILAS;
              IF mercaderiaRecord.COUNT > 0 THEN
                FOR x IN mercaderiaRecord.FIRST .. mercaderiaRecord.LAST LOOP

                  varFormaPagoEnv     := mercaderiaRecord(x)
                                        .varFormaPagoEnv;
                  varFechaProgFactEnv := mercaderiaRecord(x)
                                        .varFechaProgFactEnv;
                  varClaseSolicEnv    := mercaderiaRecord(x)
                                        .varClaseSolicEnv;
                  varOidAlmacEnv      := mercaderiaRecord(x).varOidAlmacEnv;
                  varTipoDocum        := mercaderiaRecord(x).varTipoDocum;
                  varTipoSoliConsE        := mercaderiaRecord(x).varTipoSolicCons;

                  varMsg := '20';

                  /*select to_char(sysdate, 'yy') ||
                         lpad(VAL_ULTI_NUME_SOLI+1, 8, '0')
                    into varNumeSoli
                    from ped_numer_solic
                   where val_oper = 'PED001'
                     and cod_cana = 'VD'
                     and cod_acce = 'GZ'
                     and cod_suba = '000'
                     and cod_pais = pscod_pais
                     and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')))
                     for update
                     ;

                  varMsg := '21';
                  update ped_numer_solic
                     set VAL_ULTI_NUME_SOLI = VAL_ULTI_NUME_SOLI + 2
                   where val_oper = 'PED001'
                     and cod_cana = 'VD'
                     and cod_acce = 'GZ'
                     and cod_suba = '000'
                     and cod_pais = pscod_pais
                     and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));*/

                  varNumeSoliSeq := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscod_pais,
                                                                            'PED001',
                                                                            '000',
                                                                            1);

                  varNumeSoli := to_char(SYSDATE,'YY') || lpad(varNumeSoliSeq,8,'0') + 1;

                 /* select --cod_peri,
                        fec_proc
                  into --v_cod_peri,
                        v_fec_proc
                  from bas_ctrl_fact
                  where sta_camp=0
                  and ind_camp_act=1 ;  */

                  select b.FEC_fina
                  into v_fec_proc
                  from cra_perio b
                  where b.oid_peri = varOidPeriodoProceso;

                  --if(psnumCampana = v_cod_peri) then
                     varFechaProgFactEnv := v_fec_proc;
                  --end if;

                  varMsg := '22';

                  SELECT ped_soca_seq.nextval INTO varOidCabe FROM DUAL;

                  INSERT INTO PED_SOLIC_CABEC
                    (OID_SOLI_CABE,
                     FEC_PROG_FACT,
                     TSPA_OID_TIPO_SOLI_PAIS,
                     TIDS_OID_TIPO_DESP,
                     ALMC_OID_ALMA,
                     MODU_OID_MODU,
                     TICL_OID_TIPO_CLIE,
                     PERD_OID_PERI,
                     CLIE_OID_CLIE,
                     CLIE_OID_CLIE_RECE_FACT,
                     CLIE_OID_CLIE_PAGA,
                     CLIE_OID_CLIE_DEST,
                     CLDI_OID_CLIE_DIRE,
                     TDOC_OID_TIPO_DOCU,
                     SOCI_OID_SOCI,
                     SBAC_OID_SBAC,
                     TERR_OID_TERR,
                     ZZON_OID_ZONA,
                     VAL_NUME_SOLI,
                     VAL_USUA,
                     VAL_TASA_IMPU,
                     FEC_CRON,
                     IND_PERM_UNIO_SOL,
                     NUM_DOCU_ORIG,
                     VAL_BASE_FLET_LOCA,
                     VAL_IMPO_FLET_LOCA,
                     VAL_IMPO_FLET_TOTA_LOCA,
                     VAL_IMPO_FLET_SIN_IMPU_TOTA,
                     VAL_RECA_FLET_LOCA,
                     VAL_OTRO_RECA_LOCA,
                     VAL_TOTA_PAGA_LOCA,
                     VAL_PREC_CATA_TOTA_LOCA,
                     VAL_PREC_CATA_SIN_IMPU_TOTA,
                     VAL_PREC_FACT_TOTA_LOCA,
                     VAL_IMPO_IMPU_TOTA_LOCA,
                     VAL_IMPO_DESC_1_TOTA_LOCA,
                     VAL_IMPO_DESC_1_TOTA_DOCU,
                     VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
                     VAL_IMPO_DESC_3_TOTA_DOCU,
                     VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
                     VAL_IMPO_DESC_TOTA_LOCA,
                     VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
                     VAL_IMPO_REDO_LOCA,
                     VAL_BASE_FLET_DOCU,
                     VAL_IMPO_FLET_DOCU,
                     VAL_IMPO_DESC_TOTA_DOCU,
                     VAL_IMPO_FLET_SIN_IMPU_DOCU,
                     VAL_RECA_FLET_DOCU,
                     VAL_OTRO_RECA_DOCU,
                     VAL_TOTA_FLET_DOCU,
                     VAL_IMPO_FLET_TOTA_DOCU,
                     VAL_TOTA_FLET_LOCA,
                     VAL_TOTA_PAGA_DOCU,
                     VAL_PREC_CATA_TOTA_DOCU,
                     VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
                     VAL_PREC_CONT_TOTA_LOCA,
                     VAL_PREC_CONT_SIN_IMPU_TOTA,
                     VAL_PREC_CONT_SIN_IMPU_TOTA_1,
                     VAL_PREC_FACT_TOTA_DOCU,
                     VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
                     VAL_PREC_NETO_TOTA_DOCU,
                     VAL_PREC_NETO_TOTA_LOCA,
                     VAL_IMPO_IMPU_TOTA_DOCU,
                     VAL_IMPO_REDO_DOCU,
                     VAL_IMPO_REDO_CONS_LOCA,
                     VAL_IMPO_REDO_CONS_DOCU,
                     IND_OC,
                     IND_PEDI_PRUE,
                     IND_TS_NO_CONSO,
                     NUM_PREM,
                     VAL_IMPO_DESC_3_TOTA_LOCA,
                     VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
                     PAIS_OID_PAIS,
                     TIDO_OID_TIPO_DOCU,
                     VEPO_OID_VALO_ESTR_GEOP,
                     ESSO_OID_ESTA_SOLI,
                     COPA_OID_PARA_GENE,
                     GRPR_OID_GRUP_PROC,
                     SBTI_OID_SUBT_CLIE,
                     TSPA_OID_TIPO_SOLI_PAIS_CONS,
                     FOPA_OID_FORM_PAGO,
                     CLSO_OID_CLAS_SOLI,
                     ZTAD_OID_TERR_ADMI,
                     OPER_OID_OPER,
                     PROC_OID_PROC,
                     SOCA_OID_DOCU_REFE,
                     IND_INTE_LARI_GENE,
                     ICTP_OID_TIPO_PROG)
                  VALUES
                    (varOidCabe,
                     varFechaProgFactEnv,
                     decode(varCopa, null, varTspaEnvia, varTspaEnviaPremio),
                     3,
                     varOidAlmacEnv,
                     15,
                     varTipoClie,
                     varOidPeriodoProceso, --< oid de periodo de proceso >, ???????
                     varOidClie,
                     varOidClie,
                     varOidClie,
                     varOidClie,
                     varOidDireClie,
                     varTipoDocIdent,
                     varSocie,
                     varSBAC_OID_SBAC,
                     varTerri,
                     varZona,
                     varNumeSoli,
                     psusuario, --< codigo de usuario >
                     0,
                     TRUNC(sysdate),
                     1,
                     to_number(psnum_docu_refe),
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     1,
                     NULL,
                     0,
                     0,
                     varPais,
                     varTipoDocum,
                     varVepo,
                     1,
                     varCopa,
                     3,
                     varSubtipoClie,
                     varTipoSoliConsE,
                     varFormaPagoEnv,
                     varClaseSolicEnv,
                     varOidTerrAdmin,
                     21,
                     1,
                     varOidSoliRefe,
                     0,
                     varTipoConcu);

                  varMsg := '23';
                  /**/
                  INSERT INTO PED_SOLIC_POSIC
                    (OID_SOLI_POSI,
                     COD_POSI,
                     NUM_UNID_DEMA,
                     NUM_UNID_POR_ATEN,
                     VAL_TASA_IMPU,
                     SOCA_OID_SOLI_CABE,
                     TPOS_OID_TIPO_POSI,
                     PROD_OID_PROD,
                     VAL_PREC_CATA_UNIT_LOCA,
                     VAL_PREC_CONT_UNIT_LOCA,
                     VAL_PREC_CATA_UNIT_DOCU,
                     VAL_PREC_CONTA_UNIT_DOCU,
                     VAL_PREC_FACT_UNIT_LOCA,
                     VAL_PREC_FACT_UNIT_DOCU,
                     VAL_PREC_SIN_IMPU_UNIT_LOCA,
                     VAL_PREC_SIN_IMPU_UNIT_DOCU,
                     VAL_PREC_SIN_IMPU_TOTA_DOCU,
                     VAL_IMPO_DESC_UNIT_LOCA,
                     VAL_IMPO_DESC_UNIT_DOCU,
                     VAL_PREC_NETO_UNIT_LOCA,
                     VAL_PREC_NETO_TOTA_DOCU,
                     VAL_PREC_NETO_UNIT_DOCU,
                     VAL_PREC_TOTA_TOTA_LOCA,
                     VAL_PREC_TOTA_TOTA_DOCU,
                     VAL_IMPO_IMPU_UNIT_LOCA,
                     VAL_IMPO_IMPU_UNIT_DOCU,
                     VAL_IMPO_DESC_TOTA_DOCU,
                     VAL_IMPO_IMPU_TOTA_LOCA,
                     VAL_IMPO_IMPU_TOTA_DOCU,
                     VAL_IMPO_DESC_TOTA_LOCA,
                     VAL_PREC_TOTA_UNIT_LOCA,
                     VAL_PREC_TOTA_UNIT_DOCU,
                     VAL_PREC_CONT_TOTA_LOCA,
                     VAL_PREC_CATA_TOTA_LOCA,
                     VAL_PREC_CATA_TOTA_DOCU,
                     VAL_PREC_CONT_TOTA_DOCU,
                     VAL_PORC_DESC,
                     VAL_PREC_CATA_TOTA_LOCA_UNID,
                     NUM_UNID_DEMA_REAL,
                     NUM_UNID_COMPR,
                     VAL_CODI_VENT_FICT,
                     VAL_PREC_FACT_TOTA_LOCA,
                     VAL_PREC_FACT_TOTA_DOCU,
                     VAL_PREC_SIN_IMPU_TOTA_LOCA,
                     VAL_PREC_NETO_TOTA_LOCA,
                     OFDE_OID_DETA_OFER,
                     ESPO_OID_ESTA_POSI,
                     STPO_OID_SUBT_POSI,
                     VAL_CODI_VENT,
                     OID_LINE_OPER_RECL)
                    (select ped_sopo_seq.nextval,
                            rownum,
                            z.num_unid_recl,
                            z.num_unid_recl,
                            0,
                            varOidCabe,
                            10,
                            z.prod_oid_prod,
                            w.val_prec_cata_unit_loca,
                            w.val_prec_cont_unit_loca,--0,
                            0,--w.val_prec_cata_unit_loca,
                            0,
                            z.val_prec,
                            0,
                            0,
                            0,
                            0,
                            w.VAL_IMPO_DESC_UNIT_LOCA,
                            w.VAL_IMPO_DESC_UNIT_LOCA,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            0,
                            w.val_porc_desc,
                            0,
                            z.num_unid_recl,
                            z.num_unid_recl,
                            decode(z.copa_oid_para_gral,
                                   null,
                                   null,
                                   w.VAL_CODI_VENT_FICT),
                            0,
                            0,
                            0,
                            0,
                            w.ofde_oid_deta_ofer,
                            4,
                            14,
                            decode(z.copa_oid_para_gral,
                                   null,
                                   w.VAL_CODI_VENT,
                                   null),
                            z.Oid_Line_Oper_Recl
                       from rec_cabec_recla       x,
                            rec_opera_recla       y,
                            rec_linea_opera_recla z,
                            ped_solic_posic       w
                      where x.oid_cabe_recl = y.care_oid_cabe_recl
                        and y.oid_oper_recl = z.opre_oid_oper_recl
                        and z.sopo_oid_soli_posi = w.oid_soli_posi
                        and x.oid_cabe_recl = varOidCabeRecla
                        and nvl(z.COPA_OID_PARA_GRAL, 0) = nvl(varCopa, 0)
                        and z.TIMO_OID_TIPO_MOVI = 1
                        and w.espo_oid_esta_posi<>2
                        );

                  insert into rec_solic_opera values (rec_soop_seq.nextval,null,varOidOperaRecla,varOidCabe,decode(varCopa, null, varTspaEnvia, varTspaEnviaPremio));



                END LOOP;
              END IF;
              EXIT WHEN C_MERCADERIA%NOTFOUND;
            END LOOP;
            CLOSE C_MERCADERIA;
          END IF;
          --  Fin Condicion IF --
        END LOOP;
      END IF;
      EXIT WHEN C_PEDIDOS%NOTFOUND;
    END LOOP;
    CLOSE C_PEDIDOS;

     --- Elimina el cargo de Familia segura por anulacion
      if lsparametrocarfs = '1' then
         CCC_PKG_PROCE.CCC_PR_GENER_ANUL_FAMIL_SEGUR(varOidSoliCabe,psnumCampana,varPedOidSoliCabe);
      end if;

     --- Invoca el SP para invocar la eliminacion de MAV
      if lsparametromav = '1' and varOidSoliCabeSOC <> 0 then
         sto_pkg_proce_gener.sto_pr_elimi_mav_envio(varOidSoliCabeSOC);
      end if;

     --- Invoca el SP para invocar la eliminacion de MAV
      if lsparametrodesc = '1'  then

          -- ANULACION DE DESCUENTO EN CAMPA?A DE ASIGNACION
          UPDATE NVS_CLIEN_DESCU
             SET EST_DESC = 'A', -- anulado
                 CAM_ANUL = gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(l_oid_peri),
                 USU_MODI = pscod_usua,
                 FEC_MODI = sysdate
          WHERE COD_CLIE = pscod_clie
             AND EST_REGI <> 9
             AND EST_DESC = 'S'   -- ASIGNADO
             AND CAM_ASIG = gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(l_oid_peri);

      end if;

    --- Elimina la historia del pedido
    IF psflagNotaMercaderia <> 'S' THEN
      begin
        --- Recupera el OID del pedido para buscar en Int_solic_conso_cabec
        select b.SEC_NUME_DOCU
        into varSecNumeDocu
        from ped_solic_cabec a, int_solic_conso_cabec b
        where a.soca_oid_soli_cabe in
        (select oid_soli_cabe
        FROM ped_solic_cabec
        ---WHERE val_nume_soli = 1001184723)
        WHERE val_nume_soli = to_number(psnum_docu_refe))
        and a.TSPA_OID_TIPO_SOLI_PAIS in
        (SELECT tsp.oid_tipo_soli_pais
        FROM ped_tipo_solic_pais tsp,
             ped_tipo_solic      ts
        WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
        AND ts.cod_tipo_soli = 'SOC')
        and a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE_REFE;
      EXCEPTION
      WHEN others THEN
           varSecNumeDocu := 0;
      end;

    varModoAnul := pscodoperanul;

    if lsparametrorecped = 'S' then

          select psc.oid_soli_cabe
          into varOidSoliCabe
          from ped_solic_cabec psc
          where psc.val_nume_soli = psnum_docu_refe;

          lsValRecu := lsValRecuCdr;


          --- Si es anulacion por perdida y el motivo recupera pedido
          --- trabaja como si fuera un AR, sin mover almacen
          if varModoAnul = 'AX' and varMotAnulPer <> 0 then
            varModoAnul := 'AR';
          end if;

          case varModoAnul
               when 'AR' then
                    lsValRecu := lsValRecuCdr;
                    IF varSecNumeDocu <> 0 THEN
                          --- activa registro en sto_docum_digit
                          update sto_docum_digit sdd
                          set sdd.ind_envi = '0'
                          where sdd.sec_nume_docu = varSecNumeDocu;

                          --- activa registro en sto_docum_digit
                          update sto_docum_digit sdd
                          set sdd.ind_envi = '0'
                          where sdd.sec_nume_docu_cabe = varSecNumeDocu;

                          --- activa registro en int_solic_conso_cabec
                          update int_solic_conso_cabec iscc
                          set iscc.ind_ocs_proc = '0',
                              iscc.ind_proc_gp2 = '0',
                              iscc.ind_ped_rec_anul = decode(varMotAnulCam,0,'1','2')
                          where iscc.sec_nume_docu = varSecNumeDocu;

                        Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'2','A',lsValRecu);

                     end if;
               when 'AS' then
                    lsValRecu := lsValRecuCdr;
                    --- Si no coloco parametria
                    if lsValRecuCdr is null and lsValRecuPre is null then
                       Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'2','A',lsValRecu);
                    else
                       if lsValRecuCdr is not null then
                          Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'3','A',lsValRecu);
                       end if;
                       lsValRecu := lsValRecuPre;
                       if lsValRecuPre is not null then
                          Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'4','A',lsValRecu);
                       end if;
                    end if;
               else
                    lsValRecu := lsValRecuCdr;
                    --- Si no coloco parametria
                    if lsValRecuCdr is null and lsValRecuPre is null then
                       if l_oid_peri_cdr = l_oid_peri then
                          Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'2','A',lsValRecu);
                       else
                          Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'2','P',lsValRecu);
                       end if;
                    else
                       if lsValRecuCdr is not null then
                          Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'3','P',lsValRecu);
                       end if;
                       lsValRecu := lsValRecuPre;
                       if lsValRecuPre is not null then
                          Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'4','P',lsValRecu);
                       end if;
                    end if;
          end case;

        /*  if pscodoperanul = 'AR' then
            IF varSecNumeDocu <> 0 THEN
                  --- activa registro en sto_docum_digit
                  update sto_docum_digit sdd
                  set sdd.ind_envi = '0'
                  where sdd.sec_nume_docu = varSecNumeDocu;

                  --- activa registro en sto_docum_digit
                  update sto_docum_digit sdd
                  set sdd.ind_envi = '0'
                  where sdd.sec_nume_docu_cabe = varSecNumeDocu;

                  --- activa registro en int_solic_conso_cabec
                  update int_solic_conso_cabec iscc
                  set iscc.ind_ocs_proc = '0',
                      iscc.ind_proc_gp2 = '0',
                      iscc.ind_ped_rec_anul = decode(varMotAnulCam,0,'1','2')
                  where iscc.sec_nume_docu = varSecNumeDocu;

                Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'2','A',lsValRecu);

             end if;
          else
            if pscodoperanul = 'AS' then
               Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'2','A',lsValRecu);
            else
               if l_oid_peri_cdr = l_oid_peri then
                  Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'2','A',lsValRecu);
               else
                  Ped_pkg_cuadr_ofert.ped_pr_gener_solic(pscod_pais, varOidSoliCabe,'2','P',lsValRecu);
            end if;
          end if;
          end if; */

          --- Recupera la Boleta de recojo que se imprimio con el pedido
          select max(cod_cabe_bore) into varNroBolRecojo
          from (
            select br.cod_cabe_bore
            from Int_Rec_Cabec_Borec br
            where br.val_nume_soli_ped1 = psnum_docu_refe
            union
            select br.cod_cabe_bore
            from Int_Rec_Cabec_Borec br
            where br.val_nume_soli_ped2 = psnum_docu_refe) boleta;

          --- Activa la Boleta de recojo si es que se encontro algun valor
          if varNroBolRecojo is not null then

             varUsuario := 'ANULA-' || pscodoperanul ;
             varMensaje := '';
             int_pkg_recla.int_pr_rec_activa_borec(varNroBolRecojo,varMensaje,varUsuario);

             if varMensaje = 'Boleta de Recojo cerrada' then
                varMensaje := '';
             else
               --- Limpia el pedido de la Boleta de recojo
               update Int_Rec_Cabec_Borec br
               set br.val_nume_soli_ped1 = null
               where br.val_nume_soli_ped1 = psnum_docu_refe;

               update Int_Rec_Cabec_Borec br
               set br.val_nume_soli_ped2 = null
               where br.val_nume_soli_ped2 = psnum_docu_refe;
             end if;

          end if;

    end if;

      IF (varSecNumeDocu <> 0 and
          pscodoperanul <> 'AR' and
          varModoAnul <> 'AR') THEN
        rec_pkg_proce.rec_pr_proce_envia_histo_anula(pscod_pais, psnumCampana, pscod_clie, varSecNumeDocu);

      END IF;
    END IF;

    ---------

    varMsg := '24';
    update rec_repos_anula
       set COD_ESTA_PROC = '1',
           OID_SOLI_CABE = varPedOidSoliCabe,
           OID_CABE_RECL = varRecOidCabeRecl
     where num_camp = psnumCampana
       and num_recl = psnum_recl
       and cod_corr = varSecuencia
       and num_lote=psNumeroLote;

     --- MArca como anulado el pedido

update ped_solic_cabec set esso_oid_esta_soli=4 where oid_soli_cabe=varOidSoliRefe;

     --- Actualiza datos del ultimo pedido
     if lsparametroultped is null or lsparametroultped = 'S' then
        PED_PKG_PROCE.ped_pr_actua_ultim_pedid(varOidClie);
     end if;


    pscod_esta_proc := '1';
    psdeserror      := 'Ok';
  EXCEPTION
    WHEN e_encuentra_rows THEN
      pscod_esta_proc := '0';
      pscod_erro      := '01';
      psdeserror      := 'Pedido con reclamo previo. No anulable.';

     update rec_repos_anula
       set COD_ESTA_PROC = '9',
           COD_ERRO = varMsg,
           DES_ERRO = psdeserror
       where num_camp = psnumCampana
         and num_recl = psnum_recl
         and cod_corr = varSecuencia
         and num_lote=psNumeroLote;

    WHEN e_misma_campana_AX THEN
      pscod_esta_proc := '0';
      pscod_erro      := '70';
      psdeserror      := 'Pedido no puede anularse por AX y refacturar es de otra campaña';

     update rec_repos_anula
       set COD_ESTA_PROC = '9',
           COD_ERRO = varMsg,
           DES_ERRO = psdeserror
       where num_camp = psnumCampana
         and num_recl = psnum_recl
         and cod_corr = varSecuencia
         and num_lote=psNumeroLote;

    WHEN e_misma_campana THEN
      pscod_esta_proc := '0';
      pscod_erro      := '69';
      psdeserror      := 'Pedido no puede anularse por AR (verificar campa?as).';

     update rec_repos_anula
       set COD_ESTA_PROC = '9',
           COD_ERRO = varMsg,
           DES_ERRO = psdeserror
       where num_camp = psnumCampana
         and num_recl = psnum_recl
         and cod_corr = varSecuencia
         and num_lote=psNumeroLote;

    WHEN e_no_pertenece THEN


           update rec_repos_anula
             set COD_ESTA_PROC = '9',
                 COD_ERRO = '68',
                 DES_ERRO = 'Pedido no pertenece a consultora'
             where num_camp = psnumCampana
               and num_recl = psnum_recl
               and cod_corr = varSecuencia
               and num_lote = psNumeroLote;

    WHEN NO_DATA_FOUND THEN
      pscod_erro      := '01';
      pscod_esta_proc := '0';
      IF varMsg = '01' THEN
        psdeserror := 'No encontro Campa?a';
      ELSIF varMsg = '10' THEN
        psdeserror := 'No datos para Inserta en la tabla REC_LINEA_OPERA_RECLA';
      else
        psdeserror := 'No encontro datos ' || varMsg;
      END IF;

 ---     DELETE FROM rec_repos_anula S
     update rec_repos_anula
       set COD_ESTA_PROC = '9',
           COD_ERRO = varMsg,
           DES_ERRO = psdeserror
       where num_camp = psnumCampana
         and num_recl = psnum_recl
         and cod_corr = varSecuencia
         and num_lote = psNumeroLote;

    WHEN OTHERS THEN
      pscod_erro      := '01';
      pscod_esta_proc := '0';
      psdeserror      := substr(varMsg || '-' || SQLERRM, 1, 300);

---      DELETE FROM rec_repos_anula S

     update rec_repos_anula
       set COD_ESTA_PROC = '9',
           COD_ERRO = varMsg,
           DES_ERRO = psdeserror
       where num_camp = psnumCampana
         and num_recl = psnum_recl
         and cod_corr = varSecuencia
         and num_lote = psNumeroLote;

  END REC_PR_PROCE_INGRE_ANULA_NMPS;

/**************************************************************************
  Descripcion         : REC_PR_PROCE_ENVIA_RECLA_DIGIT
                        Inserta Cabeceras Detalles hacia Tablas de Consolidados
  Fecha Creacion      : 26/11/2008
  Parametros Entrada:
      psUsuario       : Codigo de Usuario
  Autor               : Dennys Oliva Iriate
  ***************************************************************************/
PROCEDURE rec_pr_proce_envia_histo_anula
(
    pscod_pais           VARCHAR2,
    psnumCampana         VARCHAR2,
    pscod_clie           VARCHAR2,
    pssecnumedocu        NUMBER
) AS

    TYPE sdd_tab_t IS TABLE OF STO_DOCUM_DIGIT%ROWTYPE INDEX BY BINARY_INTEGER;
    sdd_tab sdd_tab_t; -- In-memory table

    CURSOR c_STO_DOCUM_DIGIT
      IS
        select *
        from STO_DOCUM_DIGIT
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = pssecnumedocu
        or sec_nume_docu= pssecnumedocu));

    TYPE sae_tab_t IS TABLE OF STO_AUDIT_EXCEP%ROWTYPE INDEX BY BINARY_INTEGER;
    sae_tab sae_tab_t; -- In-memory table

    CURSOR c_STO_AUDIT_EXCEP
      IS
        select *
        from STO_AUDIT_EXCEP
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = pssecnumedocu
        or sec_nume_docu= pssecnumedocu));

    TYPE sdde_tab_t IS TABLE OF STO_DETAL_DOCUM_EXCEP%ROWTYPE INDEX BY BINARY_INTEGER;
    sdde_tab sdde_tab_t; -- In-memory table

    CURSOR c_STO_DETAL_DOCUM_EXCEP
      IS
        select *
        from STO_DETAL_DOCUM_EXCEP
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = pssecnumedocu
        or sec_nume_docu= pssecnumedocu));

    TYPE iscc_tab_t IS TABLE OF int_solic_conso_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    iscc_tab iscc_tab_t; -- In-memory table

    CURSOR c_int_solic_conso_cabec
      IS
        select *
        from int_solic_conso_cabec
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu = 'OCC'
        and sec_nume_docu= pssecnumedocu);

    TYPE iscd_tab_t IS TABLE OF int_solic_conso_detal%ROWTYPE INDEX BY BINARY_INTEGER;
    iscd_tab iscd_tab_t; -- In-memory table

    CURSOR c_int_solic_conso_detal
      IS
        select *
        from int_solic_conso_detal
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu = 'OCD'
        and sec_nume_docu_cabe = pssecnumedocu);

  rows NATURAL := 1000; -- Number of rows to process at a time
  i    BINARY_INTEGER := 0;
  j    BINARY_INTEGER := 0;
  k    BINARY_INTEGER := 0;
  l    BINARY_INTEGER := 0;
  x    BINARY_INTEGER := 0;
  lscodigocia bas_pais_compa.cod_comp%TYPE;
BEGIN

    --- Pasar de STO_DOCUM_DIGIT al STO_HISTO_DOCUM_DIGIT con cambio del lote
    OPEN c_STO_DOCUM_DIGIT;
    LOOP
      FETCH c_STO_DOCUM_DIGIT BULK COLLECT
        INTO sdd_tab LIMIT 1000;
      EXIT WHEN sdd_tab.count = 0;

      FOR x IN sdd_tab.first .. sdd_tab.last
      LOOP
        sdd_tab(x).num_lote := 'A' || substr(sdd_tab(x).num_lote, 2, length(sdd_tab(x).num_lote) - 1);

        INSERT INTO STO_HISTO_DOCUM_DIGIT VALUES sdd_tab (x);

      END LOOP;

    END LOOP;
    CLOSE c_STO_DOCUM_DIGIT;

    --- Pasar de STO_AUDIT_EXCEP al STO_HISTO_AUDIT_EXCEP con cambio del lote
    OPEN c_STO_AUDIT_EXCEP;
    LOOP
      FETCH c_STO_AUDIT_EXCEP BULK COLLECT
        INTO sae_tab LIMIT 1000;
      EXIT WHEN sae_tab.count = 0;

      FOR x IN sae_tab.first .. sae_tab.last
      LOOP
        sae_tab(x).num_lote := 'A' || substr(sae_tab(x).num_lote, 2, length(sae_tab(x).num_lote) - 1);

        INSERT INTO STO_HISTO_AUDIT_EXCEP VALUES sae_tab (x);

      END LOOP;

    END LOOP;
    CLOSE c_STO_AUDIT_EXCEP;

    --- Pasar de SSTO_DETAL_DOCUM_EXCEP al SSTO_HISTO_DETAL_DOCUM_EXCEP con cambio del lote
    OPEN c_STO_DETAL_DOCUM_EXCEP;
    LOOP
      FETCH c_STO_DETAL_DOCUM_EXCEP BULK COLLECT
        INTO sdde_tab LIMIT 1000;
      EXIT WHEN sdde_tab.count = 0;

      FOR x IN sdde_tab.first .. sdde_tab.last
      LOOP
        sdde_tab(x).num_lote := 'A' || substr(sdde_tab(x).num_lote, 2, length(sdde_tab(x).num_lote) - 1);

        INSERT INTO STO_HISTO_DETAL_DOCUM_EXCEP VALUES sdde_tab (x);

      END LOOP;

    END LOOP;
    CLOSE c_STO_DETAL_DOCUM_EXCEP;

    --- Pasar de int_solic_conso_cabec al PED_HISTO_SOLIC_CONSO_CABEC con cambio del lote
    OPEN c_int_solic_conso_cabec;
    LOOP
      FETCH c_int_solic_conso_cabec BULK COLLECT
        INTO iscc_tab LIMIT 1000;
      EXIT WHEN iscc_tab.count = 0;

      FOR x IN iscc_tab.first .. iscc_tab.last
      LOOP
        iscc_tab(x).num_lote := 'A' || substr(iscc_tab(x).num_lote, 2, length(iscc_tab(x).num_lote) - 1);

        INSERT INTO PED_HISTO_SOLIC_CONSO_CABEC VALUES iscc_tab (x);

      END LOOP;

    END LOOP;
    CLOSE c_int_solic_conso_cabec;


    --- Pasar de int_solic_conso_detal al PED_HISTO_SOLIC_CONSO_DETAL con cambio del lote
    OPEN c_int_solic_conso_detal;
    LOOP
      FETCH c_int_solic_conso_detal BULK COLLECT
        INTO iscd_tab LIMIT 1000;
      EXIT WHEN iscd_tab.count = 0;

      FOR x IN iscd_tab.first .. iscd_tab.last
      LOOP
        iscd_tab(x).num_lote := 'A' || substr(iscd_tab(x).num_lote, 2, length(iscd_tab(x).num_lote) - 1);

        INSERT INTO PED_HISTO_SOLIC_CONSO_DETAL VALUES iscd_tab (x);

      END LOOP;

    END LOOP;
    CLOSE c_int_solic_conso_detal;


        --- Borra en STO_AUDIT_EXCEP
        delete from STO_AUDIT_EXCEP
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = pssecnumedocu
        or sec_nume_docu= pssecnumedocu)
        );

        --- Borra en STO_DETAL_DOCUM_EXCEP
        delete from STO_DETAL_DOCUM_EXCEP
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = pssecnumedocu
        or sec_nume_docu= pssecnumedocu)
        );

        --- Borra en int_solic_conso_detal
        delete from int_solic_conso_detal
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu = 'OCD'
        and sec_nume_docu_cabe = pssecnumedocu
        );

        --- Borra en int_solic_conso_cabec
        delete from int_solic_conso_cabec
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu = 'OCC'
        and sec_nume_docu= pssecnumedocu
        );

        --- Borra en STO_DOCUM_DIGIT
        delete from STO_DOCUM_DIGIT
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = pssecnumedocu
        or sec_nume_docu= pssecnumedocu)
        );

        --- Borra en ped_solic_digit_detal
        delete from ped_solic_digit_detal
        where COD_PAIS = pscod_pais
        and COD_PERI = psnumCampana
        and COD_CLIE = pscod_clie;

        --- Borra en ped_solic_digit_cabec
        delete from ped_solic_digit_cabec
        where COD_PAIS = pscod_pais
        and COD_PERI = psnumCampana
        and COD_CLIE = pscod_clie;

        /*
        --- Pasar de STO_DOCUM_DIGIT al STO_HISTO_DOCUM_DIGIT con cambio del lote
        insert into STO_HISTO_DOCUM_DIGIT
        (COD_PAIS           ,
        COD_TIPO_DOCU      ,
        NUM_LOTE           ,
        SEC_NUME_DOCU      ,
        NUM_DOCU           ,
        COD_ULTI_VALI_EJEC ,
        COD_ULTI_VALI_EXIT ,
        COD_ULTI_VALI_ERRO ,
        IND_ENVI           ,
        IND_RECH           ,
        FEC_DIGI           ,
        USU_DIGI           ,
        FEC_MODI           ,
        USU_MODI           ,
        NUM_PROC           ,
        COD_ZONA           ,
        COD_CLIE           ,
        COD_REGI           ,
        SEC_NUME_DOCU_CABE ,
        COD_PERI           ,
        COD_MOTI_RECH      ,
        VAL_OBSE_RECH_DEFI ,
        IND_ELIM           ,
        IND_RECE_OCR       ,
        IND_RECE_WEB       ,
        IND_RECE_DD        ,
        IND_RECE_DIGI      ,
        IND_RECE_CC        ,
        IND_RECE_MENS)
        (select
        COD_PAIS           ,
        COD_TIPO_DOCU      ,
        'A' || substr(num_lote, 2, length(num_lote) - 1) NUM_LOTE     ,
        SEC_NUME_DOCU      ,
        NUM_DOCU           ,
        COD_ULTI_VALI_EJEC ,
        COD_ULTI_VALI_EXIT ,
        COD_ULTI_VALI_ERRO ,
        IND_ENVI           ,
        IND_RECH           ,
        FEC_DIGI           ,
        USU_DIGI           ,
        FEC_MODI           ,
        USU_MODI           ,
        NUM_PROC           ,
        COD_ZONA           ,
        COD_CLIE           ,
        COD_REGI           ,
        SEC_NUME_DOCU_CABE ,
        COD_PERI           ,
        COD_MOTI_RECH      ,
        VAL_OBSE_RECH_DEFI ,
        IND_ELIM           ,
        IND_RECE_OCR       ,
        IND_RECE_WEB       ,
        IND_RECE_DD        ,
        IND_RECE_DIGI      ,
        IND_RECE_CC        ,
        IND_RECE_MENS
        from STO_DOCUM_DIGIT
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = varSecNumeDocu
        or sec_nume_docu= varSecNumeDocu)
        )
        );

        --- Pasar de STO_AUDIT_EXCEP al STO_HISTO_AUDIT_EXCEP con cambio del lote
        insert into STO_HISTO_AUDIT_EXCEP
        (COD_PAIS      ,
        COD_TIPO_DOCU ,
        NUM_LOTE      ,
        COD_VALI      ,
        SEC_NUME_DOCU ,
        PROC_NUM_PROC ,
        IND_GEST      ,
        FEC_DIGI      ,
        USU_DIGI      ,
        COD_MENS)
        (select
        COD_PAIS      ,
        COD_TIPO_DOCU ,
        'A' || substr(num_lote, 2, length(num_lote) - 1) NUM_LOTE ,
        COD_VALI      ,
        SEC_NUME_DOCU ,
        PROC_NUM_PROC ,
        IND_GEST      ,
        FEC_DIGI      ,
        USU_DIGI      ,
        COD_MENS
        from STO_AUDIT_EXCEP
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = varSecNumeDocu
        or sec_nume_docu= varSecNumeDocu)
        )
        );

        --- Pasar de SSTO_DETAL_DOCUM_EXCEP al SSTO_HISTO_DETAL_DOCUM_EXCEP con cambio del lote
        insert into STO_HISTO_DETAL_DOCUM_EXCEP
        (COD_PAIS     ,
        COD_TIPO_DOCU ,
        NUM_LOTE      ,
        COD_VALI      ,
        SEC_NUME_DOCU ,
        DOCU_NUM_DOCU ,
        IND_APRO      ,
        IND_GEST      ,
        FEC_APRO      ,
        USU_APRO      ,
        FEC_DIGI      ,
        USU_DIGI      ,
        FEC_MODI      ,
        USU_MODI      ,
        IND_LEVA_ERRO ,
        COD_MENS)
        (select
        COD_PAIS     ,
        COD_TIPO_DOCU ,
        'A' || substr(num_lote, 2, length(num_lote) - 1) NUM_LOTE,
        COD_VALI      ,
        SEC_NUME_DOCU ,
        DOCU_NUM_DOCU ,
        IND_APRO      ,
        IND_GEST      ,
        FEC_APRO      ,
        USU_APRO      ,
        FEC_DIGI      ,
        USU_DIGI      ,
        FEC_MODI      ,
        USU_MODI      ,
        IND_LEVA_ERRO ,
        COD_MENS
        from STO_DETAL_DOCUM_EXCEP
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = varSecNumeDocu
        or sec_nume_docu= varSecNumeDocu)
        )
        );


        --- Pasar de int_solic_conso_cabec al PED_HISTO_SOLIC_CONSO_CABEC con cambio del lote
        insert into PED_HISTO_SOLIC_CONSO_CABEC
          (COD_PAIS                      ,
          COD_PERI                      ,
          COD_CLIE                      ,
          FEC_SOLI                      ,
          NUM_CLIE                      ,
          TIPO_SOLI                     ,
          COD_SBAC                      ,
          COD_ACCE                      ,
          TIP_DESP                      ,
          STA_PROC                      ,
          NOM_CLIE                      ,
          COD_REGI                      ,
          DES_REGI                      ,
          COD_ZONA                      ,
          DES_ZONA                      ,
          COD_TERR                      ,
          TIP_ORDE                      ,
          VAL_MONT_PEDI                 ,
          VAL_SALD_DEUD                 ,
          IND_ERRO_RECH                 ,
          IND_ERRO_DEUD                 ,
          IND_ERRO_MTMI                 ,
          IND_ERRO_MTMA                 ,
          IND_ERRO_UNMA                 ,
          IND_ERROR_SGPE                ,
          IND_ERRO_NODE                 ,
          IND_OCS_PROC                  ,
          IND_BLOQ_ADMI                 ,
          IND_OCS_BLOQ                  ,
          IND_ADMI_CART                 ,
          IND_BLOQ_FINA                 ,
          IND_COMP_MONT                 ,
          USU_DIGI                      ,
          FEC_DIGI                      ,
          USU_MODI                      ,
          FEC_MODI                      ,
          COD_MARC                      ,
          DES_MARC                      ,
          COD_CANA                      ,
          DES_CANA                      ,
          IND_ERRO_REMP                 ,
          USU_ADMI_CART                 ,
          FEC_ADMI_CART                 ,
          OBS_PRUB                      ,
          IND_ORIG_CABE                 ,
          NUM_LOTE                      ,
          FEC_PROG_FACT                 ,
          IND_PROC_GP2                  ,
          IND_CUP_PROG                  ,
          IND_CUP_DESP                  ,
          IND_CUP_CTA                   ,
          IND_CONT_ACT                  ,
          IND_ANUL                      ,
          SEC_NUME_DOCU                 ,
          DOCU_NUM_DOCU                 ,
          COD_MOTI_RECH                 ,
          COD_REGI_ARRI                 ,
          COD_ZONA_ARRI                 ,
          OID_SOLI_CABE                 ,
          NUM_CLIEN                     ,
          TSPA_OID_TIPO_SOLI_PAIS       ,
          MONE_OID_MONE                 ,
          TIDS_OID_TIPO_DESP            ,
          ALMC_OID_ALMA                 ,
          MODU_OID_MODU                 ,
          TICL_OID_TIPO_CLIE            ,
          TAIM_OID_TASA_IMPU            ,
          PERD_OID_PERI                 ,
          CLIE_OID_CLIE                 ,
          CLIE_OID_CLIE_RECE_FACT       ,
          CLIE_OID_CLIE_PAGA            ,
          CLIE_OID_CLIE_DEST            ,
          CLDI_OID_CLIE_DIRE            ,
          TDOC_OID_TIPO_DOCU            ,
          SOCI_OID_SOCI                 ,
          SBAC_OID_SBAC                 ,
          TERR_OID_TERR                 ,
          ZZON_OID_ZONA                 ,
          VAL_NUME_SOLI                 ,
          VAL_USUA                      ,
          VAL_TASA_IMPU                 ,
          FEC_CRON                      ,
          IND_PERM_UNIO_SOL             ,
          VAL_TIPO_CAMB                 ,
          NUM_DOCU_ORIG                 ,
          VAL_UNID_DEMA_REAL_TOTA       ,
          NUM_UNID_POR_ATEN_TOTA        ,
          NUM_UNID_ATEN_TOTA            ,
          IND_OC                        ,
          IND_PEDI_PRUE                 ,
          IND_TS_NO_CONSO               ,
          VAL_GLOS_OBSE                 ,
          VAL_OBSE_REVI                 ,
          VAL_IMPO_DESC_3_TOTA_LOCA     ,
          VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
          PAIS_OID_PAIS                 ,
          TIDO_OID_TIPO_DOCU            ,
          VEPO_OID_VALO_ESTR_GEOP       ,
          ESSO_OID_ESTA_SOLI            ,
          COPA_OID_PARA_GENE            ,
          GRPR_OID_GRUP_PROC            ,
          SBTI_OID_SUBT_CLIE            ,
          ACFI_OID_ACCE_FISI            ,
          TSPA_OID_TIPO_SOLI_PAIS_CONS  ,
          FOPA_OID_FORM_PAGO            ,
          CLSO_OID_CLAS_SOLI            ,
          ZTAD_OID_TERR_ADMI            ,
          OPER_OID_OPER                 ,
          PROC_OID_PROC                 ,
          SOCA_OID_DOCU_REFE            ,
          IND_INTE_LARI_GENE            ,
          FEC_PROG_FACT_COMP            ,
          CACT_OID_ACTI                 ,
          ESTA_OID_ESTA_CLIE            ,
          IND_FAC_REFAC                 ,
          IND_ENVI_STO                  ,
          SOCA_OID_SOLI_CABE_REFE       ,
          IND_VAL_MTMI                  ,
          IND_VAL_MTMA                  ,
          NIRI_OID_NIVE_RIES            ,
          VAL_TOTA_PAGA_PROM            ,
          IND_RECE_OCR                  ,
          IND_RECE_WEB                  ,
          IND_RECE_DD                   ,
          IND_RECE_DIGI                 ,
          IND_RECE_CC                   ,
          IND_RECE_MENS)
          ( select COD_PAIS                      ,
          COD_PERI                      ,
          COD_CLIE                      ,
          FEC_SOLI                      ,
          NUM_CLIE                      ,
          TIPO_SOLI                     ,
          COD_SBAC                      ,
          COD_ACCE                      ,
          TIP_DESP                      ,
          STA_PROC                      ,
          NOM_CLIE                      ,
          COD_REGI                      ,
          DES_REGI                      ,
          COD_ZONA                      ,
          DES_ZONA                      ,
          COD_TERR                      ,
          TIP_ORDE                      ,
          VAL_MONT_PEDI                 ,
          VAL_SALD_DEUD                 ,
          IND_ERRO_RECH                 ,
          IND_ERRO_DEUD                 ,
          IND_ERRO_MTMI                 ,
          IND_ERRO_MTMA                 ,
          IND_ERRO_UNMA                 ,
          IND_ERROR_SGPE                ,
          IND_ERRO_NODE                 ,
          IND_OCS_PROC                  ,
          IND_BLOQ_ADMI                 ,
          IND_OCS_BLOQ                  ,
          IND_ADMI_CART                 ,
          IND_BLOQ_FINA                 ,
          IND_COMP_MONT                 ,
          USU_DIGI                      ,
          FEC_DIGI                      ,
          USU_MODI                      ,
          FEC_MODI                      ,
          COD_MARC                      ,
          DES_MARC                      ,
          COD_CANA                      ,
          DES_CANA                      ,
          IND_ERRO_REMP                 ,
          USU_ADMI_CART                 ,
          FEC_ADMI_CART                 ,
          OBS_PRUB                      ,
          IND_ORIG_CABE                 ,
          'A' || substr(num_lote, 2, length(num_lote) - 1) NUM_LOTE ,
          FEC_PROG_FACT                 ,
          IND_PROC_GP2                  ,
          IND_CUP_PROG                  ,
          IND_CUP_DESP                  ,
          IND_CUP_CTA                   ,
          IND_CONT_ACT                  ,
          IND_ANUL                      ,
          SEC_NUME_DOCU                 ,
          DOCU_NUM_DOCU                 ,
          COD_MOTI_RECH                 ,
          COD_REGI_ARRI                 ,
          COD_ZONA_ARRI                 ,
          OID_SOLI_CABE                 ,
          NUM_CLIEN                     ,
          TSPA_OID_TIPO_SOLI_PAIS       ,
          MONE_OID_MONE                 ,
          TIDS_OID_TIPO_DESP            ,
          ALMC_OID_ALMA                 ,
          MODU_OID_MODU                 ,
          TICL_OID_TIPO_CLIE            ,
          TAIM_OID_TASA_IMPU            ,
          PERD_OID_PERI                 ,
          CLIE_OID_CLIE                 ,
          CLIE_OID_CLIE_RECE_FACT       ,
          CLIE_OID_CLIE_PAGA            ,
          CLIE_OID_CLIE_DEST            ,
          CLDI_OID_CLIE_DIRE            ,
          TDOC_OID_TIPO_DOCU            ,
          SOCI_OID_SOCI                 ,
          SBAC_OID_SBAC                 ,
          TERR_OID_TERR                 ,
          ZZON_OID_ZONA                 ,
          VAL_NUME_SOLI                 ,
          VAL_USUA                      ,
          VAL_TASA_IMPU                 ,
          FEC_CRON                      ,
          IND_PERM_UNIO_SOL             ,
          VAL_TIPO_CAMB                 ,
          NUM_DOCU_ORIG                 ,
          VAL_UNID_DEMA_REAL_TOTA       ,
          NUM_UNID_POR_ATEN_TOTA        ,
          NUM_UNID_ATEN_TOTA            ,
          IND_OC                        ,
          IND_PEDI_PRUE                 ,
          IND_TS_NO_CONSO               ,
          VAL_GLOS_OBSE                 ,
          VAL_OBSE_REVI                 ,
          VAL_IMPO_DESC_3_TOTA_LOCA     ,
          VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
          PAIS_OID_PAIS                 ,
          TIDO_OID_TIPO_DOCU            ,
          VEPO_OID_VALO_ESTR_GEOP       ,
          ESSO_OID_ESTA_SOLI            ,
          COPA_OID_PARA_GENE            ,
          GRPR_OID_GRUP_PROC            ,
          SBTI_OID_SUBT_CLIE            ,
          ACFI_OID_ACCE_FISI            ,
          TSPA_OID_TIPO_SOLI_PAIS_CONS  ,
          FOPA_OID_FORM_PAGO            ,
          CLSO_OID_CLAS_SOLI            ,
          ZTAD_OID_TERR_ADMI            ,
          OPER_OID_OPER                 ,
          PROC_OID_PROC                 ,
          SOCA_OID_DOCU_REFE            ,
          IND_INTE_LARI_GENE            ,
          FEC_PROG_FACT_COMP            ,
          CACT_OID_ACTI                 ,
          ESTA_OID_ESTA_CLIE            ,
          IND_FAC_REFAC                 ,
          IND_ENVI_STO                  ,
          SOCA_OID_SOLI_CABE_REFE       ,
          IND_VAL_MTMI                  ,
          IND_VAL_MTMA                  ,
          NIRI_OID_NIVE_RIES            ,
          VAL_TOTA_PAGA_PROM            ,
          IND_RECE_OCR                  ,
          IND_RECE_WEB                  ,
          IND_RECE_DD                   ,
          IND_RECE_DIGI                 ,
          IND_RECE_CC                   ,
          IND_RECE_MENS
        from int_solic_conso_cabec
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu = 'OCC'
        and sec_nume_docu= varSecNumeDocu
        )
        );

        --- Pasar de int_solic_conso_detal al PED_HISTO_SOLIC_CONSO_DETAL con cambio del lote
        insert into PED_HISTO_SOLIC_CONSO_DETAL
          (COD_PAIS                ,
          COD_PERI                ,
          COD_CLIE                ,
          COD_VENT                ,
          TIP_POSIC               ,
          DES_PROD                ,
          VAL_UNID_DEM            ,
          STA_PROC                ,
          IND_COMP_MTMI           ,
          USU_DIGI                ,
          FEC_DIGI                ,
          USU_MODI                ,
          FEC_MODI                ,
          VAL_UNID_COMP           ,
          FEC_SOLI                ,
          IND_ERRO_RECH           ,
          NUM_LOTE                ,
          IND_ERRO_SSE            ,
          SEC_NUME_DOCU           ,
          DOCU_NUM_DOCU           ,
          COD_MOTI_RECH           ,
          OID_SOLI_POSI           ,
          SOCA_OID_SOLI_CABE      ,
          COD_POSI                ,
          NUM_UNID_DEMA           ,
          NUM_UNID_POR_ATEN       ,
          TPOS_OID_TIPO_POSI      ,
          PROD_OID_PROD           ,
          FOPA_OID_FORM_PAGO      ,
          VAL_CODI_VENT           ,
          ESPO_OID_ESTA_POSI      ,
          STPO_OID_SUBT_POSI      ,
          VAL_CODI_VENT_FICT      ,
          NUM_UNID_DEMA_REAL      ,
          VAL_PREC_CATA_UNIT_LOCA ,
          VAL_PREC_CONT_UNIT_LOCA ,
          VAL_PREC_CATA_UNIT_DOCU ,
          VAL_PREC_CONTA_UNIT_DOCU,
          VAL_PORC_DESC           ,
          VAL_IMPO_DESC_UNIT_DOCU ,
          OFDE_OID_DETA_OFER      ,
          SOPO_OID_SOLI_POSI      ,
          NUM_UNID_COMPR          ,
          IND_NO_IMPR             ,
          NUM_PREM                ,
          COPA_OID_PARA_GRAL      ,
          PANP_OID_PARA_NIVE_PREM ,
          IND_ENVI_STO            ,
          VAL_FACT_REPE           ,
          VAL_UNID_DEMA_ANTE      ,
          IND_RECE_OCR            ,
          IND_RECE_WEB            ,
          IND_RECE_DD             ,
          ACCE_OID_ACCE           ,
          IND_RECE_DIGI           ,
          IND_RECE_CC             ,
          IND_RECE_MENS)
          (select COD_PAIS                ,
          COD_PERI                ,
          COD_CLIE                ,
          COD_VENT                ,
          TIP_POSIC               ,
          DES_PROD                ,
          VAL_UNID_DEM            ,
          STA_PROC                ,
          IND_COMP_MTMI           ,
          USU_DIGI                ,
          FEC_DIGI                ,
          USU_MODI                ,
          FEC_MODI                ,
          VAL_UNID_COMP           ,
          FEC_SOLI                ,
          IND_ERRO_RECH           ,
          'A' || substr(num_lote, 2, length(num_lote) - 1) NUM_LOTE,
          IND_ERRO_SSE            ,
          SEC_NUME_DOCU           ,
          DOCU_NUM_DOCU           ,
          COD_MOTI_RECH           ,
          OID_SOLI_POSI           ,
          SOCA_OID_SOLI_CABE      ,
          COD_POSI                ,
          NUM_UNID_DEMA           ,
          NUM_UNID_POR_ATEN       ,
          TPOS_OID_TIPO_POSI      ,
          PROD_OID_PROD           ,
          FOPA_OID_FORM_PAGO      ,
          VAL_CODI_VENT           ,
          ESPO_OID_ESTA_POSI      ,
          STPO_OID_SUBT_POSI      ,
          VAL_CODI_VENT_FICT      ,
          NUM_UNID_DEMA_REAL      ,
          VAL_PREC_CATA_UNIT_LOCA ,
          VAL_PREC_CONT_UNIT_LOCA ,
          VAL_PREC_CATA_UNIT_DOCU ,
          VAL_PREC_CONTA_UNIT_DOCU,
          VAL_PORC_DESC           ,
          VAL_IMPO_DESC_UNIT_DOCU ,
          OFDE_OID_DETA_OFER      ,
          SOPO_OID_SOLI_POSI      ,
          NUM_UNID_COMPR          ,
          IND_NO_IMPR             ,
          NUM_PREM                ,
          COPA_OID_PARA_GRAL      ,
          PANP_OID_PARA_NIVE_PREM ,
          IND_ENVI_STO            ,
          VAL_FACT_REPE           ,
          VAL_UNID_DEMA_ANTE      ,
          IND_RECE_OCR            ,
          IND_RECE_WEB            ,
          IND_RECE_DD             ,
          ACCE_OID_ACCE           ,
          IND_RECE_DIGI           ,
          IND_RECE_CC             ,
          IND_RECE_MENS
        from int_solic_conso_detal
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu = 'OCD'
        and sec_nume_docu_cabe = varSecNumeDocu
        )
        );

        --- Borra en STO_AUDIT_EXCEP
        delete from STO_AUDIT_EXCEP
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = varSecNumeDocu
        or sec_nume_docu= varSecNumeDocu)
        );

        --- Borra en STO_DETAL_DOCUM_EXCEP
        delete from STO_DETAL_DOCUM_EXCEP
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = varSecNumeDocu
        or sec_nume_docu= varSecNumeDocu)
        );

        --- Borra en int_solic_conso_detal
        delete from int_solic_conso_detal
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu = 'OCD'
        and sec_nume_docu_cabe = varSecNumeDocu
        );

        --- Borra en int_solic_conso_cabec
        delete from int_solic_conso_cabec
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu = 'OCC'
        and sec_nume_docu= varSecNumeDocu
        );

        --- Borra en STO_DOCUM_DIGIT
        delete from STO_DOCUM_DIGIT
        where sec_nume_docu in(
        select sec_nume_docu from sto_docum_digit
        where cod_tipo_docu like 'OC%'
        and (sec_nume_docu_cabe = varSecNumeDocu
        or sec_nume_docu= varSecNumeDocu)
        );

        --- Borra en ped_solic_digit_detal
        delete from ped_solic_digit_detal
        where COD_PAIS = pscod_pais
        and COD_PERI = psnumCampana
        and COD_CLIE = pscod_clie;

        --- Borra en ped_solic_digit_cabec
        delete from ped_solic_digit_cabec
        where COD_PAIS = pscod_pais
        and COD_PERI = psnumCampana
        and COD_CLIE = pscod_clie;
        */


EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,
                            'ERROR rec_pr_proce_envia_histo_anula: ' ||
                             ls_sqlerrm);

END rec_pr_proce_envia_histo_anula;


 /**************************************************************************
  Descripcion         : REC_PR_PROCE_ENVIA_RECLA_DIGIT
                        Inserta Cabeceras Detalles hacia Tablas de Consolidados
  Fecha Creacion      : 26/11/2008
  Parametros Entrada:
      psUsuario       : Codigo de Usuario
  Autor               : Dennys Oliva Iriate
  ***************************************************************************/
PROCEDURE rec_pr_proce_envia_recla_digit
(
  pscodigopais      VARCHAR2,
  pscodigousuario   VARCHAR2,
  psnumerolote      VARCHAR2,
  pscodigodocumento VARCHAR2
) AS
  CURSOR curinsconsolcabec(vscodcia VARCHAR2) IS
    SELECT cod_pais,
           vscodcia,
           num_docu,
           cod_peri,
           cod_clie,
           num_docu_cruc,
           tip_soli,
           cod_sub_acces,
           acc_fisi,
           fec_proc_doc,
           cod_regi_arri,
           cod_zona_arri,
           sta_proc,
           cod_moti_rech,
           cod_tipo_docu,
           '2',
           ind_expr,
           psnumerolote,
           ind_cdr_rech,
           val_obse_rech_defi,
           COD_MOT_RECH_DEFI   --PER-SiCC-2012-0642
      FROM rec_digit_cabec_servi;

  CURSOR curinsconsoldetal(vscodcia VARCHAR2) IS
    SELECT cod_pais,
           vscodcia,
           num_docu,
           cod_clie,
           tip_refe,
           cod_vent_devu,
           cod_vent_dese,
           can_prod_devu,
           can_prod_dese,
           sta_proc,
           cod_moti_rech,
           mot_spv,
           cod_oper,
           cod_tipo_oper,
           cod_tipo_docu,
           cod_zona,
           cod_regi,
           NULL ind_acci,
           psnumerolote,
           COD_MOT_RECH_DEFI   --PER-SiCC-2012-0642
      FROM rec_digit_detal_servi;

	CURSOR curinshistoconsocabec IS
      SELECT c.*
        FROM rec_digit_cabec_servi c;

    CURSOR curinshistoconsodetal IS
      SELECT d.*
        FROM rec_digit_detal_servi d;

  TYPE solic_cab_tab_t IS TABLE OF int_ocr_cabec_servi_postv%ROWTYPE INDEX BY BINARY_INTEGER;
  sol_cab_tab solic_cab_tab_t; -- In-memory table

  TYPE solic_det_tab_t IS TABLE OF int_ocr_detal_servi_postv%ROWTYPE INDEX BY BINARY_INTEGER;
  sol_det_tab solic_det_tab_t; -- In-memory table

  TYPE solic_his_cab_tab_t IS TABLE OF rec_digit_cabec_servi_histo%ROWTYPE INDEX BY BINARY_INTEGER;
  sol_his_cab_tab solic_his_cab_tab_t; -- In-memory table

  TYPE solic_his_det_tab_t IS TABLE OF rec_digit_detal_servi_histo%ROWTYPE INDEX BY BINARY_INTEGER;
  sol_his_det_tab solic_his_det_tab_t; -- In-memory table

  rows NATURAL := 1000; -- Number of rows to process at a time
  i    BINARY_INTEGER := 0;
  j    BINARY_INTEGER := 0;
  k    BINARY_INTEGER := 0;
  l    BINARY_INTEGER := 0;
  lscodigocia bas_pais_compa.cod_comp%TYPE;
BEGIN

  SELECT cod_comp
    INTO lscodigocia
    FROM bas_pais_compa
   WHERE cod_pais = pscodigopais;

  -- LLeno la tabla INT_SOLIC_CONSO_POVEN_CABEC
  -- LLeno la tabla INT_SOLIC_CONSO_POVEN_CABEC
  OPEN curinsconsolcabec(lscodigocia);
  LOOP
    FETCH curinsconsolcabec BULK COLLECT
      INTO sol_cab_tab LIMIT rows;

    EXIT WHEN sol_cab_tab.COUNT = 0;

    FORALL i IN sol_cab_tab.FIRST .. sol_cab_tab.LAST
      INSERT INTO int_ocr_cabec_servi_postv
      VALUES sol_cab_tab
        (i);

  END LOOP;
  CLOSE curinsconsolcabec;

  -- Lleno la tabla INT_SOLIC_CONSO_POVEN_DETAL
  OPEN curinsconsoldetal(lscodigocia);
  LOOP
    FETCH curinsconsoldetal BULK COLLECT
      INTO sol_det_tab LIMIT rows;
    EXIT WHEN sol_det_tab.COUNT = 0;

    FORALL j IN sol_det_tab.FIRST .. sol_det_tab.LAST
      INSERT INTO int_ocr_detal_servi_postv
      VALUES sol_det_tab
        (j);

  END LOOP;
  CLOSE curinsconsoldetal;

  OPEN curinshistoconsocabec;
  LOOP
    FETCH curinshistoconsocabec BULK COLLECT
      INTO sol_his_cab_tab LIMIT rows;

    EXIT WHEN sol_his_cab_tab.COUNT = 0;

    FORALL k IN sol_his_cab_tab.FIRST .. sol_his_cab_tab.LAST
      INSERT INTO rec_digit_cabec_servi_histo
      VALUES sol_his_cab_tab
        (k);
  END LOOP;
  CLOSE curinshistoconsocabec;

  OPEN curinshistoconsodetal;
  LOOP
    FETCH curinshistoconsodetal BULK COLLECT
      INTO sol_his_det_tab LIMIT rows;

    EXIT WHEN sol_his_det_tab.COUNT = 0;

    FORALL l IN sol_his_det_tab.FIRST .. sol_his_det_tab.LAST
      INSERT INTO rec_digit_detal_servi_histo
      VALUES sol_his_det_tab
        (l);

  END LOOP;
  CLOSE curinshistoconsodetal;

 int_pkg_occrr.ocr_pr_conso_post_venta(pscodigopais,
                                        pscodigousuario,
                                        psnumerolote,
                                        pscodigodocumento);

  -- Limpia las tablas de digitacion de reclamos
  DELETE rec_digit_cabec_servi;
  DELETE rec_digit_detal_servi;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    raise_application_error(-20123,
                            'ERROR REC_PR_PROCE_ENVIA_RECLA_DIGIT: ' ||
                             ls_sqlerrm);

END rec_pr_proce_envia_recla_digit;

 /**************************************************************************
  Descripcion         : REC_PR_PROCE_APRUE_RECLA_DIGIT
                        Inserta Cabeceras Detalles hacia Tablas de Consolidados
  Fecha Creacion      : 26/11/2008
  Parametros Entrada:
      pscodigopais    : Codigo de Pais
      pscodigoboletarecojo : Codigo de Boleta de Recojo
  Autor               : Dennys Oliva Iriate
  ***************************************************************************/

 PROCEDURE REC_PR_PROCE_APRUE_RECLA_DIGIT
  (
    pscodigopais         VARCHAR2,
    pscodigoboletarecojo VARCHAR2,
    psCodigoUsuario VARCHAR2

  ) IS
    lsImpCargo    NUMBER(12,2);
    lsImpAbono    NUMBER(12,2);
    lsIndOCS  VARCHAR2(1);
	lsindBoletaRecojo STO_PARAM_GENER_OCCRR.VAL_PARAM%TYPE;
    lsparametrofecha VARCHAR2(10);
    lsparametroconso sto_param_gener_occrr.val_param%TYPE;
    lsOidSoliCabeCCC    int_rec_cabec_borec.soca_oid_soli_cabe_cccc%TYPE;
    lsOidSoliCabeABO    int_rec_cabec_borec.soca_oid_soli_cabe_abon%TYPE;

  BEGIN

      SELECT to_char(SYSDATE,'dd/mm/yyyy') INTO lsparametrofecha FROM dual;

      /*lsparametroconso := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_IND_CONSOL');*/
      lsparametroconso := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'STO_TIPO_CALC_FACT'),'1');

  	  lsindBoletaRecojo  :=  sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'OCR_IND_LIQ_BR_DET');

      SELECT cab.imp_cargo,
             cab.imp_abono,
             cab.ind_ocs_proc
      INTO   lsImpCargo,lsImpAbono,lsIndOCS
      FROM   int_rec_cabec_borec cab
      WHERE  cab.COD_PAIS = pscodigopais
        AND  cab.COD_CABE_BORE = pscodigoboletarecojo;

	IF(lsindBoletaRecojo = '0') THEN
      if (lsImpCargo > 0 and lsIndOCS = 'F') then
         INT_PKG_RECLA.INT_PR_REC_PROCE_BOREC_NOEXI(pscodigopais,pscodigoboletarecojo);
      end if;
      if (lsImpAbono > 0 and lsIndOCS = 'F') then
         INT_PKG_RECLA.INT_PR_REC_PROCE_BOREC_DISCR(pscodigopais,pscodigoboletarecojo);
      end if;
    ELSE
		  IF(lsindBoletaRecojo = '1') THEN
		      if (lsImpCargo > 0 and lsIndOCS = 'F') then
		         INT_PKG_RECLA.INT_PR_REC_PROCE_BOREC_NOEX2(pscodigopais,pscodigoboletarecojo);
		      end if;
		      if (lsImpAbono > 0 and lsIndOCS = 'F') then
		         INT_PKG_RECLA.INT_PR_REC_PROCE_BOREC_DISC2(pscodigopais,pscodigoboletarecojo);
		      end if;
		  END IF;
	END IF;

      if (lsIndOCS = 'F') then

      UPDATE int_rec_cabec_borec c
       SET c.ind_ocs_proc = 'V', c.usu_mod_liq = psCodigoUsuario
     WHERE c.cod_pais = pscodigopais
       ---AND c.usu_mod_liq = psCodigoUsuario
       AND c.cod_cabe_bore = pscodigoboletarecojo;

       UPDATE int_rec_linea_borec d
     SET d.ind_ocs_proc = 'V'
   WHERE d.cod_pais = pscodigopais
     AND d.cod_cabe_bore = pscodigoboletarecojo;

     end if;

   ---- Proceso para generar los CARGOS / ABONOS en linea
   ---IF (lsparametroconso = '1' or lsparametroconso = '2') and lsIndOCS = 'F' THEN
   IF (lsparametroconso is not null) and lsIndOCS = 'F' THEN

       ---- Obtiene el oid de la solicitud generada para CARGO
       select SOCA_OID_SOLI_CABE_CCCC
       into lsOidSoliCabeCCC
       from int_rec_cabec_borec c
       WHERE c.cod_pais = pscodigopais
       AND c.cod_cabe_bore = pscodigoboletarecojo;

       ---- Obtiene el oid de la solicitud generada para ABONO
       select SOCA_OID_SOLI_CABE_ABON
       into lsOidSoliCabeABO
       from int_rec_cabec_borec c
       WHERE c.cod_pais = pscodigopais
       AND c.cod_cabe_bore = pscodigoboletarecojo;

       ---- Ejecuta el CARGO en linea
       if lsOidSoliCabeCCC > 0 then
          sto_pkg_envio_valid_sicc.sto_pr_genera_consolidado(lsOidSoliCabeCCC,
                                                             lsparametrofecha,
                                                             pscodigopais,
                                                             lsparametroconso,
                                                             'C');
          ---- update para poder imrpimri el SMS
          update REC_AUDIT_BOREC
          set IND_ENVI_SMS = '1'
          where COD_CABE_BORE = pscodigoboletarecojo
            and NUM_RECO = 2;

       end if;

       ---- Ejecuta el ABONO en linea
       if lsOidSoliCabeABO > 0 then
          sto_pkg_envio_valid_sicc.sto_pr_genera_consolidado(lsOidSoliCabeABO,
                                                             lsparametrofecha,
                                                             pscodigopais,
                                                             lsparametroconso,
                                                             'A');
       end if;

   end if;

    /*UPDATE int_rec_cabec_borec
       SET ind_ocs_proc = 'V'
     WHERE cod_cabe_bore = pscodigoboletarecojo
       AND ind_ocs_proc = 'F';*/

  END REC_PR_PROCE_APRUE_RECLA_DIGIT;

/**************************************************************************
  Descripcion         : REC_PR_PROCE_INGRE_ATENC
                        Proceso de Ingreso de Atenciones
  Fecha Creacion      : 29/12/2008
  Parametros Entrada:
      psTipoProducto       : Tipo de Producto
      psTipoAtencion       : Tipo de Atencion
      psCodigoCliente      : Codigo de cliente
      psCampanhaProceso    : Campnha de proceso
      psCampanhaReferencia : Campanha de referencia
      psCodigoPais         : Codigo Pais
      psCodigoUsuario      : Codigo Usuario
      psMensajeError       : Mensaje Error
  Autor               : Dennys Oliva Iriate
***************************************************************************/
PROCEDURE REC_PR_PROCE_INGRE_ATENC(psNumeroLote   VARCHAR2,
                                   psTipoProducto VARCHAR2,
                                   psTipoAtencion VARCHAR2,
                                   psCodigoCliente VARCHAR2,
                                   psCampanhaProceso VARCHAR2,
                                   psCampanhaReferencia VARCHAR2,
                                   psCodigoPais VARCHAR2,
                                   psCodigoUsuario VARCHAR2,
                                   psMensajeError OUT VARCHAR2,
                                   psTipoOperacion VARCHAR2
                                   )
   AS
      CURSOR curIngresoAtencion
      IS
    		select  COD_VENT      ,
                VAL_PREC      ,
                VAL_PREC_CONT ,
                COD_SAP       ,
                DES_PROD      ,
                OID_TIPO_OFER ,
                OID_PROD      ,
                OID_MATR_FACT ,
                OID_OPER_RECL ,
                OID_SOLI      ,
                OID_PARA_NIVE ,
                OID_LOTE_ARTI ,
                OID_TIPO_CONC ,
                OID_DETAL_OFER,
                OID_FORM_PAGO ,
                NUM_UNID
    		from REC_GTT_DETAL_INGRE_ATENC;

      TYPE ingre_aten_tab_t IS TABLE OF REC_GTT_DETAL_INGRE_ATENC%ROWTYPE INDEX BY BINARY_INTEGER;
      ing_aten_tab ingre_aten_tab_t;            -- In-memory table

      rows                 NATURAL        := 1000;   -- Number of rows to process at a time
      i                    BINARY_INTEGER := 0;

      varCodOper           varchar2(2);
      varTipoOper          varchar2(2);
      varOidSoliCabe       ped_solic_cabec.oid_soli_cabe%TYPE;
      varFecDocuRefe       ped_solic_cabec.fec_fact%TYPE;

      varOidOper           rec_opera.OID_OPER%TYPE;
      varOidTipoOper       rec_tipos_opera.OID_TIPO_OPER%TYPE;
      varTspaEnvia         rec_opera.TSPA_OID_SOLI_CON_STOC%TYPE;
      varOCEnv             ped_clase_solic.IND_ORDE_COMP%TYPE;

      varTerri             zon_terri.oid_terr%TYPE;
      varVepo              zon_terri.vepo_oid_valo_estr_geop%TYPE;
      varOidClie           mae_clien.OID_CLIE%TYPE;
      varOidTerrAdmin      mae_clien_unida_admin.ZTAD_OID_TERR_ADMI%TYPE;
      varSubtipoClie       mae_clien_tipo_subti.SBTI_OID_SUBT_CLIE%TYPE;
      varTipoClie          mae_clien_tipo_subti.TICL_OID_TIPO_CLIE%TYPE;
      varTipoDocum         mae_tipo_docum.TIDO_OID_TIPO_DOCU%TYPE;
      varOidDireClie       mae_clien_direc.OID_CLIE_DIRE%TYPE;
      varOidZonClie        zon_zona.OID_ZONA%TYPE;
      varTipoDocIdent      mae_tipo_docum.TIDO_OID_TIPO_DOCU%TYPE;
      lsparametroGrpAte    sto_param_gener_occrr.val_param%TYPE;

      varOidCabeRecla      NUMBER;
      varOidOperaRecla     NUMBER;

      varTotEnv            NUMBER;

      --varOidSoliCabe       NUMBER;

      varFormaPagoEnv      ped_tipo_solic_pais.FOPA_OID_FORM_PAGO%TYPE;
    	varFechaProgFactEnv  cra_crono.FEC_INIC%TYPE;
    	varClaseSolicEnv     ped_clase_solic.OID_CLAS_SOLI%TYPE;
    	varOidAlmacEnv       ped_tipo_solic_pais.ALMC_OID_ALMA%TYPE;
    	varTipoSoliCons      ped_tipo_solic_pais.TSOL_OID_TIPO_CONS%TYPE;
    	varTipoDocum2        ped_tipo_solic_pais.TIDO_OID_TIPO_DOCU%TYPE;
    	varSubac             ped_tipo_solic.SBAC_OID_SBAC%TYPE;
    	varSocie             ped_tipo_solic_pais.SOCI_OID_SOCI%TYPE;

     lnIdPeriodoREGI         NUMBER;


      varNumeSoliSeq       number;
      varNumeSoli          VARCHAR2(10);
      varNumeAtenSeq       NUMBER;
      varNumeAten          NUMBER;

      varOidCabe           NUMBER;
      varNumePedi          NUMBER;
      lnseqsolioper        NUMBER;

    	varNumLote           rec_audit_info.NUM_LOTE%TYPE;
    	varPedOidSoliCabe    rec_audit_info.OID_SOLI_CABE%TYPE;
    	varRecOidCabeRecl    rec_audit_info.OID_CABE_RECL%TYPE;
    	varPedOidSoliPosi    rec_audit_info.OID_SOLI_POSI%TYPE;

      --excepciones
        ingreso_atencion_exception EXCEPTION;


  BEGIN
    varTipoOper := psTipoOperacion;
    varPedOidSoliCabe := null;
    varPedOidSoliPosi := null;
    varRecOidCabeRecl := null;
    varNumLote := psNumeroLote;

    lsparametroGrpAte := sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_IND_CONSOL_GP');

    if ((lsparametroGrpAte <> '1' and lsparametroGrpAte <> '3') or
         lsparametroGrpAte is null ) then
       lsparametroGrpAte := '1';
    end if;

    ---- Periodo actual
    SELECT c.oid_peri
      INTO lnIdPeriodoREGI
      FROM bas_ctrl_fact   a,
           seg_perio_corpo b,
           cra_perio       c
     WHERE a.cod_peri = b.cod_peri
       AND b.oid_peri = c.peri_oid_peri
       AND a.sta_camp = 0
       AND a.ind_camp_act = 1;


    BEGIN

    SELECT a.val_nume_soli,
           a.oid_soli_cabe,
           a.fec_fact
    INTO   varNumePedi,
           varOidSoliCabe,
           varFecDocuRefe
    FROM   ped_solic_cabec a,
           mae_clien b,
           ped_tipo_solic_pais c,
           ped_tipo_solic d
    WHERE a.clie_oid_clie = b.oid_clie
      And a.perd_oid_peri = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanhaReferencia)
      AND b.cod_clie = psCodigoCliente
      AND a.tspa_oid_tipo_soli_pais=c.oid_tipo_soli_pais
      AND c.tsol_oid_tipo_soli=d.oid_tipo_soli
      AND d.cod_tipo_soli='C1'
      AND EXISTS (
          SELECT 1
          FROM ped_solic_cabec p,
               ped_tipo_solic_pais pc,
               ped_tipo_solic pd
          WHERE p.soca_oid_soli_cabe = a.oid_soli_cabe
          and p.tspa_oid_tipo_soli_pais = pc.oid_tipo_soli_pais
          and pc.tsol_oid_tipo_soli = pd.oid_tipo_soli
          and pd.cod_tipo_soli = 'SOC'
      )
      AND ROWNUM = 1;

      EXCEPTION
     WHEN NO_DATA_FOUND THEN
           varNumePedi := null;
           varOidSoliCabe := null;
           varFecDocuRefe := null;
     END;

   begin
    if psTipoProducto = 'matriz' then
  		if psTipoAtencion = 'normal' then
  			if varOidSoliCabe is not null then --Si numero de pedido es distinto de null
  				SELECT COD_OPER_ENV_MAT_NRM_CON_REF INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
  		 	else
  				SELECT COD_OPER_ENV_MAT_NRM_SIN_REF INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
        end if;
  		else
  			if varFecDocuRefe is not null then--Campa?a Referencia es distinto de null
  				SELECT COD_OPER_ENV_MAT_EXP_CON_REF INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
  			else
            SELECT COD_OPER_ENV_MAT_EXP_CON_REF INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
        end if;
  		end if;
    elsif psTipoProducto = 'matrizPedido' then
        if psTipoAtencion = 'normal' then
            if varOidSoliCabe is not null then --Si numero de pedido es distinto de null
                  SELECT COD_OPER_ENV_MAT_NRM_CON_PED INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
            else
                  SELECT COD_OPER_ENV_MAT_NRM_CON_PED INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
            end if;
        else
            if varFecDocuRefe is not null then--Campa?a Referencia es distinto de null
                  SELECT COD_OPER_ENV_MAT_EXP_CON_PED INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
            else
                SELECT COD_OPER_ENV_MAT_EXP_CON_PED INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
            end if;
        end if;
  	elsif psTipoProducto = 'Activacion' then
  		if psTipoAtencion = 'Producto' then
  			varCodOper := 'RM';
  		else   ---- 'Premio'
  			varCodOper := 'RP';
    	end if;
    else      
  		if psTipoAtencion = 'normal' then
     		if psTipoOperacion = '99' then
      			SELECT COD_OPER_ENV_PRE_NRM_ESP INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
        else
  			SELECT COD_OPER_ENV_PRE_NRM INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
        end if;
  		else
     		if psTipoOperacion = '99' then
  	    		SELECT COD_OPER_ENV_PRE_EXP_ESP INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
  	  	else
  			    SELECT COD_OPER_ENV_PRE_EXP INTO varCodOper FROM REC_PARAM_INGRE_ATENC;
        end if;
    	end if;
  	end if;



    select a.OID_OPER,
             b.OID_TIPO_OPER,
             a.TSPA_OID_SOLI_CON_STOC,
             h.IND_ORDE_COMP OC_ENV
       into  varOidOper,
           varOidTipoOper,
               varTspaEnvia,
               varOCEnv
        from rec_opera           a,
             rec_tipos_opera     b,
             ped_tipo_solic_pais d,
             ped_tipo_solic      f,
             ped_clase_solic     h
       where a.OID_OPER = b.ROPE_OID_OPER
         and a.COD_OPER = varCodOper
         and b.VAL_TIPO_OPER = psTipoOperacion -----'01'
         and a.TSPA_OID_SOLI_CON_STOC = d.OID_TIPO_SOLI_PAIS(+)
         and d.TSOL_OID_TIPO_SOLI = f.OID_TIPO_SOLI(+)
         and f.CLSO_OID_CLAS_SOLI = h.OID_CLAS_SOLI(+);
   exception
    when no_data_found then
      psMensajeError:='No se encuentra codigo operacion';
      raise ingreso_atencion_exception;
      --return;
   end;

   begin
    select  g.OID_TERR,
            CASE
               WHEN (substr(c.cod_unid_geog, 19, 6) IS NULL) THEN
                (SELECT veg.oid_valo_estr_geop
                   FROM zon_valor_estru_geopo veg
                  WHERE veg.orde_1 = substr(c.cod_unid_geog, 0, 6)
                    AND veg.orde_2 = substr(c.cod_unid_geog, 7, 6)
                    AND veg.orde_3 = substr(c.cod_unid_geog, 13, 6)
                    AND veg.orde_4 IS NULL)
               ELSE
                (SELECT veg.oid_valo_estr_geop
                   FROM zon_valor_estru_geopo veg
                  WHERE veg.orde_1 = substr(c.cod_unid_geog, 0, 6)
                    AND veg.orde_2 = substr(c.cod_unid_geog, 7, 6)
                    AND veg.orde_3 = substr(c.cod_unid_geog, 13, 6)
                    AND veg.orde_4 = substr(c.cod_unid_geog, 19, 6))
            END vepo_oid_valo_estr_geop,
            a.OID_CLIE,
      			b.ZTAD_OID_TERR_ADMI,
      			j.SBTI_OID_SUBT_CLIE,
      			j.TICL_OID_TIPO_CLIE,
      			e.TIDO_OID_TIPO_DOCU,
      			c.OID_CLIE_DIRE,
      			i.OID_ZONA,
      			d.TDOC_OID_TIPO_DOCU
      	into varTerri,
      	     varVepo,
      	     varOidClie,
      	     varOidTerrAdmin,
      	     varSubtipoClie,
      	     varTipoClie,
      	     varTipoDocum,
      	     varOidDireClie,
      	     varOidZonClie,
      	     varTipoDocIdent
      	  from mae_clien             a,
      	       mae_clien_unida_admin b,
      	       mae_clien_direc       c,
      	       mae_clien_ident       d,
      	       mae_tipo_docum        e,
      	       zon_terri_admin       f,
      	       zon_terri             g,
      	       zon_secci             h,
      	       zon_zona              i,
      	       mae_clien_tipo_subti  j
      	 where a.OID_CLIE = b.CLIE_OID_CLIE
      	   and a.OID_CLIE = c.CLIE_OID_CLIE
      	   and a.OID_CLIE = d.CLIE_OID_CLIE
      	   and a.OID_CLIE = j.CLIE_OID_CLIE
      	   and d.TDOC_OID_TIPO_DOCU = e.OID_TIPO_DOCU
      	   and b.ZTAD_OID_TERR_ADMI = f.OID_TERR_ADMI
      	   and f.ZSCC_OID_SECC = h.OID_SECC
      	   and f.TERR_OID_TERR = g.OID_TERR
      	   and h.ZZON_OID_ZONA = i.OID_ZONA
      	   and b.PERD_OID_PERI_FIN is null
      	   and c.IND_ELIM = 0
      	   and c.IND_DIRE_PPAL = 1
      	   and j.TICL_OID_TIPO_CLIE = 2
           and d.VAL_IDEN_DOCU_PRIN=1
      	   and a.COD_CLIE = psCodigoCliente;
    exception
     when no_data_found then
      psMensajeError:='No se encuentra unidad geografica del cliente';
      raise ingreso_atencion_exception;
    end;

    select rec_care_seq.nextval into varOidCabeRecla from dual;
  	select rec_opre_seq.nextval into varOidOperaRecla from dual;

  	--varTotEnv := (PrecioCatalogo / (PrecioFactura + PrecioContable)) * NumeroUnidades;
   begin
    select sum((VAL_PREC + VAL_PREC_CONT)*NUM_UNID) into varTotEnv from REC_GTT_DETAL_INGRE_ATENC;


    select a.FOPA_OID_FORM_PAGO,
  	       --b.FEC_INIC,
  	       d.OID_CLAS_SOLI,
  	       a.ALMC_OID_ALMA,
  	       a.TSOL_OID_TIPO_CONS,
  	       a.TIDO_OID_TIPO_DOCU,
  	       c.SBAC_OID_SBAC,
  	       a.SOCI_OID_SOCI
  	into 	varFormaPagoEnv,
    			--varFechaProgFactEnv,
    			varClaseSolicEnv,
    			varOidAlmacEnv,
    			varTipoSoliCons,
    			varTipoDocum2,
    			varSubac,
  		    varSocie
  	  from ped_tipo_solic_pais a,
  	       --cra_crono           b,
  	       ped_tipo_solic      c,
  	       ped_clase_solic     d
  	 where a.OID_TIPO_SOLI_PAIS = varTspaEnvia
  	   --and a.CACT_OID_ACTI      = b.CACT_OID_ACTI
  	   --and b.PERD_OID_PERI 		= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanhaProceso)
  	   --and b.ZZON_OID_ZONA		= varOidZonClie
  	   and a.TSOL_OID_TIPO_SOLI = c.OID_TIPO_SOLI
  	   and c.CLSO_OID_CLAS_SOLI = d.OID_CLAS_SOLI;

       SELECT b.fec_fina
     	 INTO varFechaProgFactEnv
    	 FROM cra_perio b
  	   WHERE b.OID_PERI	= GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanhaProceso);
   exception
    when no_data_found then
    psMensajeError:='No hay datos para el tipo solicitud pais ' || varTspaEnvia;
    raise ingreso_atencion_exception;
   end;

      if varTipoDocum2 is not null then
         varTipoDocum := varTipoDocum2;
      end if;
    --------------------------------

    --------------------------------

    if varOidSoliCabe is not null then

    /*select to_char(sysdate, 'yy') || lpad(VAL_ULTI_NUME_SOLI+1, 8, '0')
      into varNumeAten
      from ped_numer_solic
     where val_oper = 'REC034'
       and cod_cana = '_'
       and cod_acce = '_'
       and cod_suba = '_'
       and cod_pais = psCodigoPais
       and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));

      update ped_numer_solic
         set VAL_ULTI_NUME_SOLI = VAL_ULTI_NUME_SOLI + 1
       where val_oper = 'REC034'
         and cod_cana = '_'
         and cod_acce = '_'
         and cod_suba = '_'
         and cod_pais = psCodigoPais
         and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));*/

      ---- SQA Reemplaza Generacion de secuencial
        varNumeAtenSeq:= sto_pkg_gener.sto_fn_resrv_secue_nsoli(psCodigoPais,
                                                                  'REC034',
                                                                  '_',
                                                                  1);
        varNumeAten := to_char(SYSDATE,'YY') || lpad(varNumeAtenSeq,8,'0') + 1;

        varRecOidCabeRecl := varOidCabeRecla;

      ---  lnIdPeriodoREGI := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCampanhaProceso);


        INSERT INTO REC_CABEC_RECLA
          (OID_CABE_RECL,
           NUM_ATEN,
           NUM_RECL,
           FEC_DOCU_REFE,
           NUM_TOTA_ENVI,
           NUM_TOTA_DEVU,
           IMP_SALD_PAGA,
           COD_USUA_INGR,
           FEC_INGR,
           PAIS_OID_PAIS,
           SOCA_OID_SOLI_CABE,
           PERD_OID_PERI_DOCU_REFE,
           CLIE_OID_CLIE,
           MRDB_OID_MOTI_RECH,
           PERD_OID_PERI_RECL,
           MRDB_OID_MOTI_DESB,
           ZTAD_OID_TERR_ADMI,
           SBTI_OID_SUBT_CLIE,
           TIDO_OID_TIPO_DOCU,
           ESRE_OID_ESTA_RECL,
           MOBL_OID_MOTI_BLOQ,
           TIIN_OID_TIPO_INGR,
           TICL_OID_TIPO_CLIE,
           FEC_ULTI_ACTU,
           NUM_ATEN_INTE,
           PERD_OID_PERI_REGI)
        VALUES
          (varOidCabeRecla,
           varNumeAten,
           varNumePedi,
           varFecDocuRefe,
           varTotEnv,
           0,
           varTotEnv,
           psCodigoUsuario, ---- psCodigoCliente,
           sysdate,
           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais),
           varOidSoliCabe,
           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanhaReferencia),
           varOidClie,
           NULL,
           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanhaProceso),
           NULL,
           varOidTerrAdmin,
           varSubtipoClie,
           varTipoDocum,
           6,
           NULL,
           2,
           varTipoClie,
           sysdate,
           NULL,
           lnIdPeriodoREGI );

           INSERT INTO REC_OPERA_RECLA
              (OID_OPER_RECL,
               NUM_SECU_OPER,
               IMP_MONT_PERD,
               VAL_PORC_PERD,
               CARE_OID_CABE_RECL,
               TIBL_OID_TIPO_BLOQ,
               MOBL_OID_MOTI_BLOQ,
               INEM_OID_INDI_ENTR_MERC,
               ASPE_OID_ASUM_PERD,
               PROD_OID_PROD,
               CLIE_OID_CLIE,
               MRDB_OID_MOTI_RECH_DESB,
               MRDB_OID_MOTI_DESB,
               PPER_OID_PREC_PERD,
               ESOP_OID_ESTA_OPER,
               CLIE_OID_RESP_PERD,
               TPOS_OID_TIPO_POSI,
               SOCA_OID_SOLI_CABE,
               PERD_OID_PERI_RECL,
               TSPA_OID_TIPO_SOLI_PAIS,
               TIOP_OID_TIPO_OPER,
               IND_ATEN,
               FEC_FACT,
               FEC_ULTI_ACTU)
            VALUES
              (varOidOperaRecla,
               1,
               0,
               NULL,
               varOidCabeRecla,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               2,
               NULL,
               NULL,
               varOidSoliCabe,
               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanhaProceso),
               NULL,
               varOidTipoOper,
               1,
               NULL,
               sysdate);

        OPEN curIngresoAtencion;
    	  LOOP
    	    FETCH curIngresoAtencion BULK COLLECT INTO ing_aten_tab LIMIT rows;
    	    EXIT WHEN ing_aten_tab.COUNT = 0;
             FOR i IN ing_aten_tab.FIRST .. ing_aten_tab.LAST loop
                 INSERT INTO REC_LINEA_OPERA_RECLA
                  (TSPA_OID_TIPO_SOLI_PAIS,
                   OID_LINE_OPER_RECL,
                   NUM_LINE,
                   NUM_UNID_RECL,
                   VAL_PREC,
                   IMP_CARG,
                   IMP_ABON,
                   NUM_UNID_DEVU,
                   IND_ESTA,
                   IMP_MONT_PERD,
                   IND_ATEN,
                   OPRE_OID_OPER_RECL,
                   TOFE_OID_TIPO_OFER,
                   PPER_OID_PREC_PERD,
                   TPOS_OID_TIPO_POSI,
                   TIMO_OID_TIPO_MOVI,
                   PROD_OID_PROD,
                   MAFA_OID_MATR_FACT,
                   MODV_OID_MOTI_DEVO,
                   MRDB_OID_MOTI_RECH_DESB,
                   SOPO_OID_SOLI_POSI,
                   COPA_OID_PARA_GRAL,
                   PANP_OID_PARA_NIVE_PREM,
                   LOPA_OID_LOTE_PREM_ARTI,
                   VAL_PREC_CONT)
                VALUES
                  (varTspaEnvia,
                   rec_lior_seq.nextval,
                   i,
                   ing_aten_tab(i).NUM_UNID,
                   nvl(ing_aten_tab(i).VAL_PREC,0),
                   (nvl(ing_aten_tab(i).VAL_PREC,0) * ing_aten_tab(i).NUM_UNID),
                   0,
                   0,
                   'E',
                   NULL,
                   1,
                   varOidOperaRecla,
                   ing_aten_tab(i).OID_TIPO_OFER,
                   NULL,
                   NULL,
                   1,
                   ing_aten_tab(i).OID_PROD,
                   ing_aten_tab(i).OID_MATR_FACT,
                   NULL,
                   NULL,
                   NULL,
                   ing_aten_tab(i).OID_SOLI,
                   ing_aten_tab(i).OID_PARA_NIVE,
                   ing_aten_tab(i).OID_LOTE_ARTI,
                   nvl(ing_aten_tab(i).VAL_PREC_CONT,0)
                   );

             END LOOP;
    	  END LOOP;
    	  CLOSE curIngresoAtencion;

    end if;

    --if psTipoProducto = 'matriz' then

    select ped_soca_seq.nextval into varOidCabe from dual;

    /*select to_char(sysdate, 'yy') || lpad(VAL_ULTI_NUME_SOLI+1, 8, '0')
      into varNumeSoli
      from ped_numer_solic
     where val_oper = 'PED001'
       and cod_cana = 'VD'
       and cod_acce = 'GZ'
       and cod_suba = '000'
       and cod_pais = psCodigoPais
       and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));

      update ped_numer_solic
         set VAL_ULTI_NUME_SOLI = VAL_ULTI_NUME_SOLI + 1
       where val_oper = 'PED001'
         and cod_cana = 'VD'
         and cod_acce = 'GZ'
         and cod_suba = '000'
         and cod_pais = psCodigoPais
         and val_anio = to_char(to_number(to_char(SYSDATE,
                                                'YY')));*/


      --- SQA nueva forma de generar el sequencial
      varNumeSoliSeq := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                                'PED001',
                                                                '000',
                                                                1);

      varNumeSoli := to_char(SYSDATE,'YY') || lpad(varNumeSoliSeq,8,'0') + 1;


      varPedOidSoliCabe := varOidCabe;

      INSERT INTO PED_SOLIC_CABEC
            (OID_SOLI_CABE,
             FEC_PROG_FACT,
             TSPA_OID_TIPO_SOLI_PAIS,
             TIDS_OID_TIPO_DESP,
             ALMC_OID_ALMA,
             MODU_OID_MODU,
             TICL_OID_TIPO_CLIE,
             PERD_OID_PERI,
             CLIE_OID_CLIE,
             CLIE_OID_CLIE_RECE_FACT,
             CLIE_OID_CLIE_PAGA,
             CLIE_OID_CLIE_DEST,
             CLDI_OID_CLIE_DIRE,
             TDOC_OID_TIPO_DOCU,
             SOCI_OID_SOCI,
             SBAC_OID_SBAC,
             TERR_OID_TERR,
             ZZON_OID_ZONA,
             VAL_NUME_SOLI,
             VAL_USUA,
             VAL_TASA_IMPU,
             FEC_CRON,
             IND_PERM_UNIO_SOL,
             NUM_DOCU_ORIG,
             VAL_BASE_FLET_LOCA,
             VAL_IMPO_FLET_LOCA,
             VAL_IMPO_FLET_TOTA_LOCA,
             VAL_IMPO_FLET_SIN_IMPU_TOTA,
             VAL_RECA_FLET_LOCA,
             VAL_OTRO_RECA_LOCA,
             VAL_TOTA_PAGA_LOCA,
             VAL_PREC_CATA_TOTA_LOCA,
             VAL_PREC_CATA_SIN_IMPU_TOTA,
             VAL_PREC_FACT_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_LOCA,
             VAL_IMPO_DESC_1_TOTA_LOCA,
             VAL_IMPO_DESC_1_TOTA_DOCU,
             VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
             VAL_IMPO_DESC_3_TOTA_DOCU,
             VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
             VAL_IMPO_DESC_TOTA_LOCA,
             VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
             VAL_IMPO_REDO_LOCA,
             VAL_BASE_FLET_DOCU,
             VAL_IMPO_FLET_DOCU,
             VAL_IMPO_DESC_TOTA_DOCU,
             VAL_IMPO_FLET_SIN_IMPU_DOCU,
             VAL_RECA_FLET_DOCU,
             VAL_OTRO_RECA_DOCU,
             VAL_TOTA_FLET_DOCU,
             VAL_IMPO_FLET_TOTA_DOCU,
             VAL_TOTA_FLET_LOCA,
             VAL_TOTA_PAGA_DOCU,
             VAL_PREC_CATA_TOTA_DOCU,
             VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
             VAL_PREC_CONT_TOTA_LOCA,
             VAL_PREC_CONT_SIN_IMPU_TOTA,
             VAL_PREC_CONT_SIN_IMPU_TOTA_1,
             VAL_PREC_FACT_TOTA_DOCU,
             VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
             VAL_PREC_NETO_TOTA_DOCU,
             VAL_PREC_NETO_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_DOCU,
             VAL_IMPO_REDO_DOCU,
             VAL_IMPO_REDO_CONS_LOCA,
             VAL_IMPO_REDO_CONS_DOCU,
             IND_OC,
             IND_PEDI_PRUE,
             IND_TS_NO_CONSO,
             NUM_PREM,
             VAL_IMPO_DESC_3_TOTA_LOCA,
             VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
             PAIS_OID_PAIS,
             TIDO_OID_TIPO_DOCU,
             VEPO_OID_VALO_ESTR_GEOP,
             ESSO_OID_ESTA_SOLI,
             COPA_OID_PARA_GENE,
             GRPR_OID_GRUP_PROC,
             SBTI_OID_SUBT_CLIE,
             TSPA_OID_TIPO_SOLI_PAIS_CONS,
             FOPA_OID_FORM_PAGO,
             CLSO_OID_CLAS_SOLI,
             ZTAD_OID_TERR_ADMI,
             OPER_OID_OPER,
             PROC_OID_PROC,
             SOCA_OID_DOCU_REFE,
             IND_INTE_LARI_GENE,
             ICTP_OID_TIPO_PROG,
             VAL_TIPO_CAMB,
             VAL_GLOS_OBSE,
             VAL_OBSE_REVI,
             VAL_PUNT_EMIS
             )
          VALUES
            (varOidCabe,
             varFechaProgFactEnv,
             varTspaEnvia,
             1,
             varOidAlmacEnv,
             decode(varOidSoliCabe,null,1,15),
             --15,
             varTipoClie,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanhaProceso),
             varOidClie,
             varOidClie,
             varOidClie,
             varOidClie,
             varOidDireClie,
             varTipoDocIdent,
             varSocie,
             varSubac,
             varTerri,
             varOidZonClie,
             varNumeSoli,
             psCodigoUsuario,
             0,
             trunc(sysdate),
             1,
             varNumePedi,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             1,
             NULL,
             0,
             0,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais),
             varTipoDocum,
             varVepo,
             1,
             --NULL,--(select OID_SOLI from REC_GTT_DETAL_INGRE_ATENC where rownum=1),
             (select OID_SOLI from REC_GTT_DETAL_INGRE_ATENC where rownum=1),
             --decode(varOidSoliCabe,null,3,1),
             lsparametroGrpAte, ----- 1,
             varSubtipoClie,
             varTipoSoliCons,
             nvl(nvl(varFormaPagoEnv,(select fopa_oid_form_pago from mae_clien where oid_clie=varOidClie)),(select fopa_oid_form_pago from seg_pais where oid_pais=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais))),
             --varFormaPagoEnv,
             varClaseSolicEnv,
             varOidTerrAdmin,
             21,
             1,
             varOidSoliCabe,
             0,
             --NULL--(select OID_TIPO_CONC from REC_GTT_DETAL_INGRE_ATENC where rownum=1)--,< OID_TIPO_CONCU >
             (select OID_TIPO_CONC from REC_GTT_DETAL_INGRE_ATENC where rownum=1),--,< OID_TIPO_CONCU >
             nvl((select val_tipo_camb from pre_matri_factu_cabec where perd_oid_peri=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCampanhaProceso)),1),
             'ATENCIONES MASIVAS',
             psNumeroLote,
             INT_PKG_RECLA.GEN_FN_PED_BR(varOidClie)
             );

             SELECT rec_soop_seq.NEXTVAL
                INTO lnseqsolioper
                FROM dual;
              --SELECT PED_SOCA_SEQ INTO lnseqsolicabe FROM dual;
              if varOidSoliCabe is not null then

              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 varOidOperaRecla,
                 varOidCabe,
                 varTspaEnvia);
             end if;
      OPEN curIngresoAtencion;
    	  LOOP
    	    FETCH curIngresoAtencion BULK COLLECT INTO ing_aten_tab LIMIT rows;
    	    EXIT WHEN ing_aten_tab.COUNT = 0;
             FOR i IN ing_aten_tab.FIRST .. ing_aten_tab.LAST loop

                 select ped_sopo_seq.nextval into varPedOidSoliPosi from dual;

                 INSERT INTO PED_SOLIC_POSIC
                    (OID_SOLI_POSI,
                     COD_POSI,
                     NUM_UNID_DEMA,
                     NUM_UNID_POR_ATEN,
                     VAL_TASA_IMPU,
                     SOCA_OID_SOLI_CABE,
                     TPOS_OID_TIPO_POSI,
                     PROD_OID_PROD,
                     VAL_PREC_CATA_UNIT_LOCA,
                     VAL_PREC_CONT_UNIT_LOCA,
                     VAL_PREC_CATA_UNIT_DOCU,
                     VAL_PREC_CONTA_UNIT_DOCU,
                     VAL_PREC_FACT_UNIT_LOCA,
                     VAL_PREC_FACT_UNIT_DOCU,
                     VAL_PREC_SIN_IMPU_UNIT_LOCA,
                     VAL_PREC_SIN_IMPU_UNIT_DOCU,
                     VAL_PREC_SIN_IMPU_TOTA_DOCU,
                     VAL_IMPO_DESC_UNIT_LOCA,
                     VAL_IMPO_DESC_UNIT_DOCU,
                     VAL_PREC_NETO_UNIT_LOCA,
                     VAL_PREC_NETO_TOTA_DOCU,
                     VAL_PREC_NETO_UNIT_DOCU,
                     VAL_PREC_TOTA_TOTA_LOCA,
                     VAL_PREC_TOTA_TOTA_DOCU,
                     VAL_IMPO_IMPU_UNIT_LOCA,
                     VAL_IMPO_IMPU_UNIT_DOCU,
                     VAL_IMPO_DESC_TOTA_DOCU,
                     VAL_IMPO_IMPU_TOTA_LOCA,
                     VAL_IMPO_IMPU_TOTA_DOCU,
                     VAL_IMPO_DESC_TOTA_LOCA,
                     VAL_PREC_TOTA_UNIT_LOCA,
                     VAL_PREC_TOTA_UNIT_DOCU,
                     VAL_PREC_CONT_TOTA_LOCA,
                     VAL_PREC_CATA_TOTA_LOCA,
                     VAL_PREC_CATA_TOTA_DOCU,
                     VAL_PREC_CONT_TOTA_DOCU,
                     VAL_PORC_DESC,
                     VAL_PREC_CATA_TOTA_LOCA_UNID,
                     NUM_UNID_DEMA_REAL,
                     NUM_UNID_COMPR,
                     VAL_CODI_VENT_FICT,
                     VAL_PREC_FACT_TOTA_LOCA,
                     VAL_PREC_FACT_TOTA_DOCU,
                     VAL_PREC_SIN_IMPU_TOTA_LOCA,
                     VAL_PREC_NETO_TOTA_LOCA,
                     OFDE_OID_DETA_OFER,
                     ESPO_OID_ESTA_POSI,
                     STPO_OID_SUBT_POSI,
                     VAL_CODI_VENT,
                     FOPA_OID_FORM_PAGO)
                  VALUES
                    (varPedOidSoliPosi,   ----  ped_sopo_seq.nextval,
                     i,
                     ing_aten_tab(i).NUM_UNID,
                     ing_aten_tab(i).NUM_UNID,
                     0,
                     varOidCabe,
                     10,
                     ing_aten_tab(i).OID_PROD,
                     nvl(ing_aten_tab(i).VAL_PREC,0),
                     decode(nvl(ing_aten_tab(i).VAL_PREC,0),0,nvl(ing_aten_tab(i).VAL_PREC_CONT,0),0),
                     nvl(ing_aten_tab(i).VAL_PREC,0),
                     decode(nvl(ing_aten_tab(i).VAL_PREC,0),0,nvl(ing_aten_tab(i).VAL_PREC_CONT,0),0),
                     --0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     NULL,
                     0,
                     ing_aten_tab(i).NUM_UNID,
                     0,
                     decode(ing_aten_tab(i).OID_SOLI, null, null, ing_aten_tab(i).COD_VENT),
                     0,
                     0,
                     0,
                     0,
                     ing_aten_tab(i).OID_DETAL_OFER,
                     4,
                     14,
                     decode(ing_aten_tab(i).OID_SOLI, null, ing_aten_tab(i).COD_VENT, null),
                     ing_aten_tab(i).OID_FORM_PAGO);

                     --Inserto en las tablas de auditoria
                     INSERT INTO REC_AUDIT_INFO
                           (COD_CLIE,
                            COD_CAMP,
                            COD_VENT,
                            NUM_UNID,
                            COD_USUA,
                            FEC_INGR,
                            COD_OPER,
                            VAL_TIPO_OPER,
                            NUM_LOTE,
                            OID_SOLI_CABE,
                            OID_CABE_RECL,
                            COD_CAMP_REFE,
                            OID_SOLI_POSI)
                        VALUES(
                            psCodigoCliente,
                            psCampanhaProceso,
                            ing_aten_tab(i).COD_VENT,
                            ing_aten_tab(i).NUM_UNID,
                            psCodigoUsuario,
                            sysdate,
                            varCodOper,
                            psTipoOperacion,
                            varNumLote,
                            varPedOidSoliCabe,
                            varRecOidCabeRecl,
                            psCampanhaReferencia,
                            varPedOidSoliPosi
                        );

             END LOOP;
    	  END LOOP;
    	CLOSE curIngresoAtencion;

     -- ELSE
      --
     -- null;
     -- END IF;

    EXCEPTION
        WHEN ingreso_atencion_exception THEN
                        INSERT INTO REC_AUDIT_INFO
                           (COD_CLIE,
                            COD_CAMP,
                            COD_VENT,
                            NUM_UNID,
                            COD_USUA,
                            FEC_INGR,
                            DES_ERRO,
                            COD_OPER,
                            VAL_TIPO_OPER,
                            NUM_LOTE,
                            COD_CAMP_REFE)
                        VALUES(
                            psCodigoCliente,
                            psCampanhaProceso,
                            NULL,
                            NULL,
                            psCodigoUsuario,
                            sysdate,
                            psMensajeError,
                            varCodOper,
                            psTipoOperacion,
                            varNumLote,
                            psCampanhaReferencia
                        );

        WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        psMensajeError := substr(sqlerrm,1,250);
           --Inserto en las tablas de auditoria
                     INSERT INTO REC_AUDIT_INFO
                           (COD_CLIE,
                            COD_CAMP,
                            COD_VENT,
                            NUM_UNID,
                            COD_USUA,
                            FEC_INGR,
                            DES_ERRO,
                            COD_OPER,
                            VAL_TIPO_OPER,
                            NUM_LOTE,
                            COD_CAMP_REFE)
                        VALUES(
                            psCodigoCliente,
                            psCampanhaProceso,
                            null,
                            null,
                            psCodigoUsuario,
                            sysdate,
                            psMensajeError,
                            varCodOper,
                            psTipoOperacion,
                            varNumLote,
                            psCampanhaReferencia
                        );
        --RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_PROCE_INGRE_ATENC: '||ls_sqlerrm);
  END REC_PR_PROCE_INGRE_ATENC;


/**************************************************************************
  Descripcion         : REC_PR_PROCE_ACTUA_DIGIT_SIMPL
                        Actualiza valores por la digitacion simplificada
  Fecha Creacion      : 23/04/2009
  Parametros Entrada:
      pscodigopais         : Codigo de Pais
      pscodigoboletarecojo : Codigo de Boleta de Recojo
      pscodigonovedad      : Codigo de novedad
  Autor               : Dennys Oliva Iriate
***************************************************************************/
PROCEDURE REC_PR_PROCE_ACTUA_DIGIT_SIMPL(pscodigopais              VARCHAR2,
                                         pscodigoboletarecojo      VARCHAR2,
                                         pscodigonovedad           VARCHAR2,
                                         pscodigolineaboletarecojo VARCHAR2,
                                         psnumeroeliminados        NUMBER)
IS
    lsestabor    int_rec_cabec_borec.esbo_oid_esta_bor1%type;
    lsnumreco    int_rec_cabec_borec.num_reco%type;
    lsnumloteenvi2 int_rec_cabec_borec.num_lote_env2%type;
  BEGIN
      select a.esbo_oid_esta_bor1,
             a.num_reco,
             a.num_lote_env2
        into lsestabor,
             lsnumreco,
             lsnumloteenvi2
        from int_rec_cabec_borec a
       where a.val_nume_bore = pscodigoboletarecojo
         and a.cod_pais = pscodigopais;

       -- Mercaderia recepcionada -- 07
       if pscodigonovedad = '07' then
         if lsestabor = 1 and lsnumreco = 1 then
            update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 5,
                  num_tota_unid_reco = num_tota_unid_recl,
                  fec_reco = sysdate,
                  fec_cierr = sysdate,
                  num_reco = 1,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

              update INT_REC_LINEA_BOREC
              set num_unid_reco = num_unid_recl,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                       where val_nume_bore = pscodigoboletarecojo
                                         and cod_pais = pscodigopais);
         end if;

         if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is not null then
            update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 5,
                  num_tota_unid_reco = num_tota_unid_recl,
                  fec_reco = sysdate,
                  fec_cierr = sysdate,
                  num_reco = 2,
                  ind_regr_yob2 = 1,
                  num_lote_dev2 = nvl(num_lote_dev2,to_char(sysdate,'YYYYMMDD'))
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_reco = num_unid_recl-num_unid_elim,
                  ind_regr_yob2 = 1,
                  num_lote_dev2 = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                       where val_nume_bore = pscodigoboletarecojo
                                         and cod_pais = pscodigopais);
         end if;

         if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is null then
            update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 5,
                  num_tota_unid_reco = num_tota_unid_recl,
                  fec_reco = sysdate,
                  fec_cierr = sysdate,
                  num_reco = 1,
                  ind_regr_yobe = 1,
                  num_lote_devu = nvl(num_lote_devu,to_char(sysdate,'YYYYMMDD'))
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_reco = num_unid_recl-num_unid_elim,
                  ind_regr_yobe = 1,
                  num_lote_devu = nvl(num_lote_devu, to_char(sysdate,'YYYYMMDD'))
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                       where val_nume_bore = pscodigoboletarecojo
                                         and cod_pais = pscodigopais);
         end if;
       end if;

       -- Unidades incompletas      -- 01
       -- No entrego unidades       -- 02
       -- No se ubico domicilio     -- 03
       -- No se encontro consultora -- 04
       if pscodigonovedad = '01' or pscodigonovedad = '02' or
          pscodigonovedad = '03' or pscodigonovedad = '04'  then
          if lsestabor = 1 and lsnumreco = 1 then
             update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 3,
                  esbo_oid_esta_bor2 = 6,
                  num_tota_unid_reco = 0,
                  fec_reco = sysdate,
                  MORE_OID_MOTN_RECO_BORE = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo),
                  num_reco = 1,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

              update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                      where val_nume_bore = pscodigoboletarecojo
                                        and cod_pais = pscodigopais);
          end if;

          if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is not null then
            update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 6,
                  num_tota_unid_reco = 0,
                  fec_rec2 = sysdate,
                  fec_cierr = sysdate,
                  MORE_OID_MOTN_RECO_BOR2 = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo),
                  num_reco = 2,
                  ind_regr_yob2 = 1,
                  num_lote_dev2 = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  ind_regr_yob2 = 1,
                  num_lote_dev2 = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                      where val_nume_bore = pscodigoboletarecojo
                                        and cod_pais = pscodigopais);
         end if;

         if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is null then
            update INT_REC_CABEC_BOREC
              set MORE_OID_MOTN_RECO_BORE = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo)
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

         end if;
       end if;

       -- No Entregada por Proveedor/Transportista -- 09
       if pscodigonovedad = '09' then
           if lsestabor = 1 and lsnumreco = 1 then
             update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 8,
                  num_tota_unid_reco = 0,
                  fec_reco = sysdate,
                  fec_cierr = sysdate,
                  num_reco = 1,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

              update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                      where val_nume_bore = pscodigoboletarecojo
                                        and cod_pais = pscodigopais);
          end if;

          if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is not null then
            update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 8,
                  num_tota_unid_reco = 0,
                  fec_rec2 = sysdate,
                  fec_cierr = sysdate,
                  num_reco = 2,
                  ind_regr_yob2 = 1,
                  num_lote_dev2 = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  ind_regr_yob2 = 1,
                  num_lote_dev2 = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                      where val_nume_bore = pscodigoboletarecojo
                                        and cod_pais = pscodigopais);
         end if;

         if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is null then
            update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 8,
                  num_tota_unid_reco = 0,
                  fec_reco = sysdate,
                  fec_cierr = sysdate,
                  num_reco = 1,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                       where val_nume_bore = pscodigoboletarecojo
                                         and cod_pais = pscodigopais);
         end if;

       end if;

        -- No se realizo Postventa -- 06
       if pscodigonovedad = '06' then
          if lsestabor = 1 and lsnumreco = 1 then
             update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 6,
                  num_tota_unid_reco = 0,
                  fec_reco = sysdate,
                  MORE_OID_MOTN_RECO_BORE = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo),
                  fec_cierr = sysdate,
                  num_reco = 1,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

              update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                      where val_nume_bore = pscodigoboletarecojo
                                        and cod_pais = pscodigopais);
          end if;

          if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is not null then
            update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 6,
                  num_tota_unid_reco = 0,
                  fec_rec2 = sysdate,
                  MORE_OID_MOTN_RECO_BOR2 = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo),
                  fec_cierr = sysdate,
                  num_reco = 2,
                  ind_regr_yob2 = 1,
                  num_lote_dev2 = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  ind_regr_yob2 = 1,
                  num_lote_dev2 = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                      where val_nume_bore = pscodigoboletarecojo
                                        and cod_pais = pscodigopais);
         end if;

         if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is null then
            update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 4,
                  esbo_oid_esta_bor2 = 6,
                  num_tota_unid_reco = 0,
                  fec_reco = sysdate,
                  MORE_OID_MOTN_RECO_BORE = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo),
                  fec_cierr = sysdate,
                  num_reco = 1,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                       where val_nume_bore = pscodigoboletarecojo
                                         and cod_pais = pscodigopais);
         end if;
       end if;

       -- Errores postventa  -- 08
       if pscodigonovedad = '08' then
          if lsestabor = 1 and lsnumreco = 1 then
             update INT_REC_CABEC_BOREC
              set esbo_oid_esta_bor1 = 3,
                  esbo_oid_esta_bor2 = 6,
                  num_tota_unid_reco = 0,
                  fec_reco = sysdate,
                  MORE_OID_MOTN_RECO_BORE = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo),
                  num_reco = 2,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

              update INT_REC_LINEA_BOREC
              set num_unid_reco = 0,
                  num_unid_elim = psnumeroeliminados,
                  ind_regr_yobe = 1,
                  num_lote_devu = to_char(sysdate,'YYYYMMDD')
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                      where val_nume_bore = pscodigoboletarecojo
                                        and cod_pais = pscodigopais)
                and cod_line_bore = pscodigolineaboletarecojo;
          end if;

          if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is not null then
            update INT_REC_CABEC_BOREC
              set MORE_OID_MOTN_RECO_BOR2 = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo)
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_elim = psnumeroeliminados
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                      where val_nume_bore = pscodigoboletarecojo
                                        and cod_pais = pscodigopais)
                and cod_line_bore = pscodigolineaboletarecojo;
         end if;

         if lsestabor = 3 and lsnumreco = 2 and lsnumloteenvi2 is null then
            update INT_REC_CABEC_BOREC
              set MORE_OID_MOTN_RECO_BORE = (select oid_motn_reco_bore
                                               from int_rec_motno_recoj_borec
                                              where cod_pais = pscodigopais
                                                and cod_motn_reco_bore = pscodigoboletarecojo)
              where val_nume_bore = pscodigoboletarecojo
                 and cod_pais = pscodigopais;

             update INT_REC_LINEA_BOREC
              set num_unid_elim = psnumeroeliminados
              where cod_cabe_bore = (select cod_cabe_bore
                                       from INT_REC_CABEC_BOREC
                                       where val_nume_bore = pscodigoboletarecojo
                                         and cod_pais = pscodigopais)
                and cod_line_bore = pscodigolineaboletarecojo;
         end if;

       end if;

  END REC_PR_PROCE_ACTUA_DIGIT_SIMPL;

/**************************************************************************
  Descripcion         : Funcion que verifica si se excluye al cliente del
                        control de devoluciones
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
  Devuelve:
      S : SE EXCLUYE
      N : NO SE EXCLUYE
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_VERIF_EXCLU_DEVOL(pn_Oid_Cliente     NUMBER,
                                    pn_oid_periodo     NUMBER) RETURN VARCHAR2
   IS
      lnoidperiodo     cra_perio.oid_peri%type;
      lnaux number :=0;
      lsresult varchar2(1) := 'N';
   BEGIN

        lnoidperiodo := pn_oid_periodo;

        -- Evalua si existe el cliente
        select count(1)
          into lnaux
          from sto_bloqu_contr s
          where s.cod_tipo_docu = 'SPVD'
            and s.oid_peri = lnoidperiodo
            and s.clie_oid_clie = pn_Oid_Cliente
            and s.ind_tipo_bloq = '1'
            and s.ind_acti = '1'
            and s.val_moti_bloq = 'C';

        IF lnaux = 0 THEN
           -- Evalua si existe la tipologia del cliente como exclusion
           select count(1)
             into lnaux
             from mae_clien_tipo_subti t,
                  mae_clien_clasi c ,
                  sto_bloqu_contr s
            where t.clie_oid_clie = pn_Oid_Cliente
              and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
              and s.cod_tipo_docu = 'SPVD'
              and s.ind_tipo_bloq = '1'
              and s.ind_acti = '1'
              and s.val_moti_bloq = 'C'
              and s.oid_peri = lnoidperiodo
              and s.oid_tipo_clie = t.ticl_oid_tipo_clie
              and s.oid_subt_clie = t.sbti_oid_subt_clie
              and s.oid_clas_clie = c.clas_oid_clas
              and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi;

          IF lnaux = 0 THEN
             -- Si NO existe Tipologia, se verifica que exista Zona + Tipologia en blanco
             select count(1)
               into lnaux
               from sto_bloqu_contr s,
                    MAE_CLIEN,
                    MAE_CLIEN_UNIDA_ADMIN,
                    ZON_TERRI_ADMIN,
                    ZON_SECCI,
                    ZON_ZONA,
                    ZON_REGIO
              where s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '1'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'C'
                and s.oid_peri = lnoidperiodo
                and s.oid_tipo_clie is null
                and s.oid_subt_clie is null
                and s.oid_clas_clie is null
                and s.oid_tipo_clas_clie is null
                AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                AND ZON_ZONA.OID_ZONA = s.oid_zona;

                IF lnaux = 0 THEN
                   -- Tipologia en blanco + Zona en Blanco + Region
                   select count(1)
                     into lnaux
                     from sto_bloqu_contr s,
                          MAE_CLIEN,
                          MAE_CLIEN_UNIDA_ADMIN,
                          ZON_TERRI_ADMIN,
                          ZON_SECCI,
                          ZON_ZONA,
                          ZON_REGIO
                    where s.cod_tipo_docu = 'SPVD'
                      and s.ind_tipo_bloq = '1'
                      and s.ind_acti = '1'
                      and s.val_moti_bloq = 'C'
                      and s.oid_peri = lnoidperiodo
                      and s.oid_tipo_clie is null
                      and s.oid_subt_clie is null
                      and s.oid_clas_clie is null
                      and s.oid_tipo_clas_clie is null
                      AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                      AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                      AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                      AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                      AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                      AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                      AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                      AND ZON_REGIO.OID_REGI = s.oid_regi
                      AND s.oid_zona is null ;

                   IF lnaux != 0 THEN
                      -- Existe Tipologia en blanco + Zona en Blanco + Region
                      lsresult := 'S';
                   END IF;

                ELSE
                    -- Existe Tipologia en blanco + Zona
                    lsresult := 'S';
                END IF;

          ELSE
              -- Si existe Tipologia, se verifica que exista Tipologia + Zona
             select count(1)
               into lnaux
               from mae_clien_tipo_subti t,
                    mae_clien_clasi c ,
                    sto_bloqu_contr s,
                    MAE_CLIEN,
                    MAE_CLIEN_UNIDA_ADMIN,
                    ZON_TERRI_ADMIN,
                    ZON_SECCI,
                    ZON_ZONA,
                    ZON_REGIO
              where t.clie_oid_clie = pn_Oid_Cliente
                and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                and s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '1'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'C'
                and s.oid_peri = lnoidperiodo
                and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                and s.oid_subt_clie = t.sbti_oid_subt_clie
                and s.oid_clas_clie = c.clas_oid_clas
                and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                 AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                 AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                 AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                 AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                 AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                 AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                 AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                 AND ZON_ZONA.OID_ZONA = s.oid_zona;

              IF lnaux = 0 THEN
                 -- Si existe Tipologia pero sin zonas, se verifica que exista Tipologia + Region + ZOna en blanco
                 select count(1)
                   into lnaux
                   from mae_clien_tipo_subti t,
                        mae_clien_clasi c ,
                        sto_bloqu_contr s,
                        MAE_CLIEN,
                        MAE_CLIEN_UNIDA_ADMIN,
                        ZON_TERRI_ADMIN,
                        ZON_SECCI,
                        ZON_ZONA,
                        ZON_REGIO
                  where t.clie_oid_clie = pn_Oid_Cliente
                    and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                    and s.cod_tipo_docu = 'SPVD'
                    and s.ind_tipo_bloq = '1'
                    and s.ind_acti = '1'
                    and s.val_moti_bloq = 'C'
                    and s.oid_peri = lnoidperiodo
                    and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                    and s.oid_subt_clie = t.sbti_oid_subt_clie
                    and s.oid_clas_clie = c.clas_oid_clas
                    and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                     AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                     AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                     AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                     AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                     AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                     AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                     AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                     AND s.oid_zona is null
                     AND ZON_REGIO.OID_REGI = s.oid_regi;

                     IF lnaux = 0 THEN
                       -- Si existe Tipologia pero sin zonas, se verifica que exista Tipologia + Region en blanco + Zona en blanco
                       select count(1)
                         into lnaux
                         from mae_clien_tipo_subti t,
                              mae_clien_clasi c ,
                              sto_bloqu_contr s
                        where t.clie_oid_clie = pn_Oid_Cliente
                          and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                          and s.cod_tipo_docu = 'SPVD'
                          and s.ind_tipo_bloq = '1'
                          and s.ind_acti = '1'
                          and s.val_moti_bloq = 'C'
                          and s.oid_peri = lnoidperiodo
                          and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                          and s.oid_subt_clie = t.sbti_oid_subt_clie
                          and s.oid_clas_clie = c.clas_oid_clas
                          and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                          and s.oid_regi is null
                          and s.oid_zona is null;

                          IF lnaux != 0 THEN
                             -- Existe Tipologia + Region en blanco + Zona en blanco
                             lsresult := 'S';
                          END IF;

                     ELSE
                         -- Existe Tipologia + Region + Zona en blanco
                         lsresult := 'S';
                     END IF;

              ELSE
                 -- Existe Tipologia + Zona
                 lsresult := 'S';
              END IF;

          END IF;

        ELSE
            -- Existe el cliente
            lsresult := 'S';

        END IF;

        return lsresult;

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_VERIF_EXCLU_DEVOL: '||ls_sqlerrm);
END REC_FN_VERIF_EXCLU_DEVOL;

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total del pedido y
                        el monto total de devoluciones
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_EVALU_DEVOL(pnValNumeSoli     NUMBER,
                                   pnMontoPedido     OUT NUMBER,
                                   pnMontoDevolucion OUT NUMBER)IS

   BEGIN

       select nvl(max(a.VAL_TOTA_PAGA_LOCA),0) MontoPedido,
              nvl(sum(f.VAL_TOTA_PAGA_LOCA),0) MontoDevolucion
         into pnMontoPedido,
              pnMontoDevolucion
         from ped_solic_cabec a,
              rec_cabec_recla b,
              rec_opera_recla c,
              rec_solic_opera d,
              ped_solic_cabec e,
              ped_solic_cabec f,
              ped_tipo_solic_pais tsp,
              ped_tipo_solic ts
        where a.VAL_NUME_SOLI = pnValNumeSoli
        and a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
        and b.oid_cabe_recl = c.CARE_OID_CABE_RECL
        and c.OID_OPER_RECL  = d.opre_oid_oper_recl
        and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
        and oid_tipo_soli_pais = d.TSPA_OID_TIPO_SOLI_PAIS
        and cod_tipo_soli in ('SDDN')
        and d.SOCA_OID_SOLI_CABE = e.OID_SOLI_CABE
        and e.SOCA_OID_SOLI_CABE = f.OID_SOLI_CABE;

        if pnMontoPedido = 0 then
           select nvl(max(a.VAL_TOTA_PAGA_LOCA),0) MontoPedido
             into pnMontoPedido
             from ped_solic_cabec a
            where a.VAL_NUME_SOLI = pnValNumeSoli;

        end if;

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_MONTO_EVALU_DEVOL: '||ls_sqlerrm);
END REC_PR_MONTO_EVALU_DEVOL;



/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total de gestion y
                        el monto del documento por gestion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_GESTI_DEVOL(pnValNumeSoli              NUMBER,
                                   pnMontoTotalGestion    OUT NUMBER,
                                   psNumLote                  VARCHAR2,
                                   psNumDocu                  VARCHAR2,
                                   psCodClie                  VARCHAR2,
                                   psCodPeri                  VARCHAR2,
                                   psCodPais                  VARCHAR2,
                                   pnMontoDocGestion      OUT NUMBER) IS
   BEGIN
     select nvl(sum(b.VAL_PREC_CATA_DEVU * b.CAN_VENT_DEVU),0) MontoTotalxGestion
       into pnMontoTotalGestion
       from int_solic_conso_poven_cabec a,
            int_solic_conso_poven_detal b,
            sto_docum_digit             dig
      where a.NUM_LOTE = b.NUM_LOTE
        and a.NUM_DOCU = b.NUM_DOCU
        and a.COD_CLIE = b.COD_CLIE
        and a.COD_PERI = b.COD_PERI
        and a.COD_PAIS = b.COD_PAIS
        AND dig.sec_nume_docu = b.sec_nume_docu
        and a.NUM_DOCU_CRUC = pnValNumeSoli
        and a.cod_pais = psCodPais
        and a.cod_clie = psCodClie
        and b.cod_oper = 'DN'
        AND dig.ind_envi = 0
        AND dig.ind_rech = 0;

      select nvl(sum(b.VAL_PREC_CATA_DEVU * b.CAN_VENT_DEVU),0) MontoDocxGestion
       into pnMontoDocGestion
       from int_solic_conso_poven_cabec a,
            int_solic_conso_poven_detal b,
            sto_docum_digit             dig
      where a.NUM_LOTE = b.NUM_LOTE
        and a.NUM_DOCU = b.NUM_DOCU
        and a.COD_CLIE = b.COD_CLIE
        and a.COD_PERI = b.COD_PERI
        and a.COD_PAIS = b.COD_PAIS
        AND dig.sec_nume_docu = b.sec_nume_docu
        and a.NUM_DOCU_CRUC = pnValNumeSoli
        and a.NUM_LOTE = psNumLote
        and a.NUM_DOCU = psNumDocu
        and a.COD_CLIE = psCodClie
        and a.COD_PERI = psCodPeri
        and a.COD_PAIS = psCodPais
        and b.cod_oper = 'DN'
        AND dig.ind_envi = 0
        AND dig.ind_rech = 0;

   EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_MONTO_GESTI_DEVOL: '||ls_sqlerrm);
END REC_PR_MONTO_GESTI_DEVOL;

----------------------------------
/**************************************************************************
  Descripcion         : Funcion que Retorna el porcentaje de devolucion
                        que debera de aplicar en la validacion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_PORCE_MONTO_DEVOL(pn_Oid_Cliente     NUMBER,
                                    pscodigopais       VARCHAR2,
                                    pscodigoperiodo    VARCHAR2) RETURN NUMBER IS
      lnoidperiodo     cra_perio.oid_peri%type;
      lnaux            number :=0;
      lsresult         sto_bloqu_contr.val_pmon_devu%type ;

      --lsmondevparam varchar2(10);
      lnvalporcentaje  sto_bloqu_contr.val_pmon_devu%type;
   BEGIN

       lnoidperiodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);

       IF REC_FN_VERIF_EXCLU_DEVOL(pn_Oid_Cliente, lnoidperiodo) = 'S' THEN
          lsresult := 100;
       ELSE
           lsresult := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_PMON_DEV');



        begin
          -- Evalua si existe el cliente
          select count(1), s.val_pmon_devu
            into lnaux,
                 lnvalporcentaje
            from sto_bloqu_contr s
           where s.clie_oid_clie = pn_Oid_Cliente
             and s.cod_tipo_docu = 'SPVD'
             and s.ind_tipo_bloq = '0'
             and s.ind_acti = '1'
             and s.val_moti_bloq = 'C'
             --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
           group by s.val_pmon_devu;

           lsresult:= lnvalporcentaje;

        exception
        when no_data_found then

           begin

             -- Evalua si existe la tipologia del cliente
             select count(1), s.val_pmon_devu
               into lnaux,
                    lnvalporcentaje
               from mae_clien_tipo_subti t,
                    mae_clien_clasi c ,
                    sto_bloqu_contr s
              where t.clie_oid_clie = pn_Oid_Cliente
                and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                and s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '0'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'C'
                --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                and s.oid_subt_clie = t.sbti_oid_subt_clie
                and s.oid_clas_clie = c.clas_oid_clas
                and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
              group by s.val_pmon_devu;


            begin
                 -- Si existe, evalua la tipologia + Zona
                 select count(1), s.val_pmon_devu
                 into lnaux, lnvalporcentaje
                 from mae_clien_tipo_subti t,
                      mae_clien_clasi c ,
                      sto_bloqu_contr s,
                      MAE_CLIEN,
                      MAE_CLIEN_UNIDA_ADMIN,
                      ZON_TERRI_ADMIN,
                      ZON_SECCI,
                      ZON_ZONA,
                      ZON_REGIO
                where t.clie_oid_clie = pn_Oid_Cliente
                  and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                  and s.cod_tipo_docu = 'SPVD'
                  and s.ind_tipo_bloq = '0'
                  and s.ind_acti = '1'
                  and s.val_moti_bloq = 'C'
                  --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                  and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                  and s.oid_subt_clie = t.sbti_oid_subt_clie
                  and s.oid_clas_clie = c.clas_oid_clas
                  and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                   AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                   AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                   AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                   AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                   AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                   AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                   AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                   AND ZON_ZONA.OID_ZONA = s.oid_zona
                group by s.val_pmon_devu;

                lsresult:= lnvalporcentaje;

              exception
      				when no_data_found then

                  begin
                     -- Si existe, evalua la tipologia + Region + Zona en blanco
                     select count(1), s.val_pmon_devu
                     into lnaux, lnvalporcentaje
                     from mae_clien_tipo_subti t,
                          mae_clien_clasi c ,
                          sto_bloqu_contr s,
                          MAE_CLIEN,
                          MAE_CLIEN_UNIDA_ADMIN,
                          ZON_TERRI_ADMIN,
                          ZON_SECCI,
                          ZON_ZONA,
                          ZON_REGIO
                    where t.clie_oid_clie = pn_Oid_Cliente
                      and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                      and s.cod_tipo_docu = 'SPVD'
                      and s.ind_tipo_bloq = '0'
                      and s.ind_acti = '1'
                      and s.val_moti_bloq = 'C'
                      --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                      and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                      and s.oid_subt_clie = t.sbti_oid_subt_clie
                      and s.oid_clas_clie = c.clas_oid_clas
                      and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                       AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                       AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                       AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                       AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                       AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                       AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                       AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                       AND s.oid_zona is null
                       AND ZON_REGIO.Oid_Regi = s.oid_regi
                    group by s.val_pmon_devu;

                    lsresult:= lnvalporcentaje;

                  exception
                      when no_data_found then

                         begin

                             -- Si existe, evalua la tipologia + Region en blanco + Zona en blanco
                             select count(1), s.val_pmon_devu
                             into lnaux, lnvalporcentaje
                             from mae_clien_tipo_subti t,
                                  mae_clien_clasi c ,
                                  sto_bloqu_contr s,
                                  MAE_CLIEN,
                                  MAE_CLIEN_UNIDA_ADMIN,
                                  ZON_TERRI_ADMIN,
                                  ZON_SECCI,
                                  ZON_ZONA,
                                  ZON_REGIO
                            where t.clie_oid_clie = pn_Oid_Cliente
                              and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                              and s.cod_tipo_docu = 'SPVD'
                              and s.ind_tipo_bloq = '0'
                              and s.ind_acti = '1'
                              and s.val_moti_bloq = 'C'
                              --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                              and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                              and s.oid_subt_clie = t.sbti_oid_subt_clie
                              and s.oid_clas_clie = c.clas_oid_clas
                              and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                               AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                               AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                               AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                               AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                               AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                               AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                               AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                               AND s.oid_zona is null
                               AND s.oid_regi is null
                            group by s.val_pmon_devu;

                         lsresult:= lnvalporcentaje;

                         exception
                             when no_data_found then
                               null;
                         end;
                  end;

              end;

            exception
            		when no_data_found then
                   begin
                     -- Si NO existe Tipologia, se verifica que exista Zona + Tipologia en blanco
                     select count(1),s.val_pmon_devu
                       into lnaux,
                            lnvalporcentaje
                       from sto_bloqu_contr s,
                            MAE_CLIEN,
                            MAE_CLIEN_UNIDA_ADMIN,
                            ZON_TERRI_ADMIN,
                            ZON_SECCI,
                            ZON_ZONA,
                            ZON_REGIO
                      where s.cod_tipo_docu = 'SPVD'
                        and s.ind_tipo_bloq = '0'
                        and s.ind_acti = '1'
                        and s.val_moti_bloq = 'C'
                        --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                        and s.oid_tipo_clie is null
                        and s.oid_subt_clie is null
                        and s.oid_clas_clie is null
                        and s.oid_tipo_clas_clie is null
                        AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                        AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                        AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                        AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                        AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                        AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                        AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                        AND ZON_ZONA.OID_ZONA = s.oid_zona
                      group by s.val_pmon_devu;

                      lsresult:= lnvalporcentaje;

                 exception
                     when no_data_found then
                       begin
                         -- Tipologia en blanco + Zona en Blanco + Region
                         select count(1),s.val_pmon_devu
                           into lnaux,
                                lnvalporcentaje
                           from sto_bloqu_contr s,
                                MAE_CLIEN,
                                MAE_CLIEN_UNIDA_ADMIN,
                                ZON_TERRI_ADMIN,
                                ZON_SECCI,
                                ZON_ZONA,
                                ZON_REGIO
                          where s.cod_tipo_docu = 'SPVD'
                            and s.ind_tipo_bloq = '0'
                            and s.ind_acti = '1'
                            and s.val_moti_bloq = 'C'
                            --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                            and s.oid_tipo_clie is null
                            and s.oid_subt_clie is null
                            and s.oid_clas_clie is null
                            and s.oid_tipo_clas_clie is null
                            AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                            AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                            AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                            AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                            AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                            AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                            AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                            AND ZON_REGIO.OID_REGI = s.oid_regi
                            AND s.oid_zona is null
                          group by s.val_pmon_devu ;

                          lsresult:= lnvalporcentaje;

                      exception
                         when no_data_found then
                              null;
                      end;

                 end;

              end;

           end;

       END IF;

       return nvl(lsresult,100);

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_PORCE_MONTO_DEVOL: '||ls_sqlerrm);
END REC_FN_PORCE_MONTO_DEVOL;

/**************************************************************************
  Descripcion         : Funcion que devuelve el codigo del chequeo
                        realizado
  Fecha Creacion      : 22/03/2011
  Parametros Entrada:
      psNumPedido      : numero pedido

  Autor               : Jose Luis Rodriguez
***************************************************************************/
 FUNCTION REC_FN_RESUL_CHEQU_PEDID(psNumPedido  VARCHAR2) RETURN VARCHAR2
 IS
   lsresult  ped_resul_chequ_cabec.cod_resu_cheq%TYPE;
   lnNumPedido  NUMBER;

 BEGIN
   lnNumPedido := TO_NUMBER(psNumPedido);

   BEGIN

     SELECT pch.cod_resu_cheq
       INTO lsresult
       FROM ped_solic_cabec ped,
            ped_pedid_chequ pxch,
            ped_resul_chequ_cabec pch
      WHERE ped.oid_soli_cabe = pxch.oid_pedi_cheq
        AND pxch.cod_pais = pch.cod_pais
        AND pxch.cod_tipo_cheq = pch.cod_tipo_cheq
        AND pxch.oid_pedi_cheq = pch.oid_pedi_cheq
        AND ped.val_nume_soli = lnNumPedido;

   EXCEPTION
     WHEN no_data_found THEN
       lsresult:='00';
   END;

   RETURN lsresult;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_RESUL_CHEQU_PEDID: '||ls_sqlerrm);
 END REC_FN_RESUL_CHEQU_PEDID;

 /**************************************************************************
   Descripcion         : Procedure que realiza las validaciones previas
                         a la eliminacion de un lote.
   Fecha Creacion      : 06/09/2011
   Parametros Entrada:
       psNumLote       : Numero de Lote
       psUsuario       : Codigo de Usuario
       psMensajeError  : MensajeError

   Autor               : Francesco Rodriguez
 ***************************************************************************/
 PROCEDURE REC_PR_VALID_ELIMI_LOTE_ATENC(psNumLote VARCHAR2,
                                   psUsuario OUT VARCHAR2,
                                   psMensajeError  OUT VARCHAR2) IS
  liCountLoteEliminado INTEGER;
  liCountLoteProcesadoParcial INTEGER;

  BEGIN
   psMensajeError := '';
   liCountLoteEliminado := 0;
   liCountLoteProcesadoParcial := 0;
   psUsuario := '';

   select count(1)
     into liCountLoteEliminado
     from REC_AUDIT_INFO
    where num_lote = psNumLote and cod_esta_proc = '9';

   if liCountLoteEliminado > 0 then
     begin
       select USU_ELIM || ' - ' || trunc(FEC_ELIM)
         into psUsuario
         from REC_AUDIT_INFO
        where num_lote = psNumLote
          and cod_esta_proc = '9'
          and rownum = 1;
     exception
       when NO_DATA_FOUND then
         psUsuario := ' ';
     end;
     psMensajeError := 'mantenimientoRECIngresoAtencionesForm.loteEliminado';
   else
      select count(1)
        into liCountLoteProcesadoParcial
        from ped_solic_cabec
       where oid_soli_cabe in
           ( select oid_soli_cabe
               from REC_AUDIT_INFO
              where num_lote = psNumLote
                and oid_soli_cabe is not null )
         and grpr_oid_grup_proc >= 4;
     /* if liCountLoteProcesadoParcial > 0 then
        psMensajeError := 'mantenimientoRECIngresoAtencionesForm.loteProcesadoParcial';
      end if;*/
   end if;

 END REC_PR_VALID_ELIMI_LOTE_ATENC;

 /**************************************************************************
   Descripcion         : Procedure que elimina un lote de registros
                         de ingreso de atenciones
   Fecha Creacion      : 06/09/2011
   Parametros Entrada:
       psNumLote       : Numero de Lote
       psUsuario       : Codigo de Usuario
       psMensajeError  : MensajeError

   Autor               : Francesco Rodriguez
 ***************************************************************************/
 PROCEDURE REC_PR_ELIMI_LOTE_ATENC(psNumLote VARCHAR2,
                                  psUsuario       VARCHAR2,
                                  psMensajeError  OUT VARCHAR2) IS
   W_FILAS NUMBER := 1000;

  liCountLoteProcesadoParcial INTEGER;

   TYPE cabeReclRec IS RECORD ( oid_cabe_recl rec_audit_info.oid_cabe_recl%TYPE );
   TYPE cabeReclRecTab IS TABLE OF cabeReclRec;
   cabeReclRecord cabeReclRecTab;

   TYPE soliCabe IS RECORD ( oid_soli_cabe rec_audit_info.oid_soli_cabe%TYPE );
   TYPE soliCabeTab IS TABLE OF soliCabe;
   soliCabeRecord soliCabeTab;

   CURSOR c_oid_cabe_recl(varOidCabeRecl NUMBER) IS
     select oid_cabe_recl
       from rec_cabec_recla
      where oid_cabe_recl = varOidCabeRecl
        and oid_cabe_recl is not null;
   TYPE operReclRec IS RECORD ( oid_oper_recl rec_opera_recla.oid_oper_recl%TYPE );
   TYPE operReclRecTab IS TABLE OF operReclRec;
   operReclRecRecord operReclRecTab;

   CURSOR c_oid_oper_recl(varCareOidCabeRecl NUMBER) IS
     select oid_oper_recl
       from rec_opera_recla
      where care_oid_cabe_recl = varCareOidCabeRecl
        and oid_oper_recl is not null;
   TYPE recCabeReclRec IS RECORD ( oid_cabe_recl rec_cabec_recla.oid_cabe_recl%TYPE );
   TYPE recCabeReclRecTab IS TABLE OF recCabeReclRec;
   recCabeReclRecord recCabeReclRecTab;

   CURSOR c_soca_oid_soli_cabe(varOperRecl NUMBER) IS
     select soca_oid_soli_cabe
       from rec_solic_opera
      where opre_oid_oper_recl = varOperRecl
        and soca_oid_soli_cabe is not null;
   TYPE socaSoliCabe IS RECORD ( soca_oid_soli_cabe rec_solic_opera.soca_oid_soli_cabe%TYPE );
   TYPE socaSoliCabeTab IS TABLE OF socaSoliCabe;
   socaSoliCabeRecord socaSoliCabeTab;

   CURSOR c_oid_soli_cabe(varOidSoliCabe NUMBER) IS
     select oid_soli_cabe
       from ped_solic_cabec
      where oid_soli_cabe = varOidSoliCabe
        and oid_soli_cabe is not null;
   TYPE pedSolicPosic IS RECORD ( soca_oid_soli_cabe ped_solic_posic.soca_oid_soli_cabe%TYPE );
   TYPE pedSolicPosicTab IS TABLE OF pedSolicPosic;
   pedSolicPosicRecord pedSolicPosicTab;

 BEGIN

   liCountLoteProcesadoParcial := 0;

      select count(1)
        into liCountLoteProcesadoParcial
        from ped_solic_cabec
       where oid_soli_cabe in
           ( select oid_soli_cabe
               from REC_AUDIT_INFO
              where num_lote = psNumLote
                and oid_soli_cabe is not null )
         and grpr_oid_grup_proc >= 4;

      if liCountLoteProcesadoParcial = 0 then
          -- ELIMINACION TOTAL
   select distinct oid_cabe_recl
     bulk collect into cabeReclRecord
     from rec_audit_info
    where num_lote = psNumLote
      and oid_cabe_recl is not null;

   if cabeReclRecord.count > 0 then
     for i in cabeReclRecord.first .. cabeReclRecord.last
       loop
         open c_oid_cabe_recl(cabeReclRecord(i).oid_cabe_recl);
           loop fetch c_oid_cabe_recl bulk collect into recCabeReclRecord LIMIT W_FILAS;
             if recCabeReclRecord.count > 0 then
               for i in recCabeReclRecord.first .. recCabeReclRecord.last
                 loop
                   open c_oid_oper_recl(recCabeReclRecord(i).oid_cabe_recl);
                    loop fetch c_oid_oper_recl bulk collect into operReclRecRecord LIMIT W_FILAS;
                     if operReclRecRecord.count > 0 then
                       for i in operReclRecRecord.first .. operReclRecRecord.last
                         loop
                           open c_soca_oid_soli_cabe(operReclRecRecord(i).oid_oper_recl);
                             loop fetch c_soca_oid_soli_cabe bulk collect into socaSoliCabeRecord LIMIT W_FILAS;
                               if socaSoliCabeRecord.count > 0 then
                                 for i in socaSoliCabeRecord.first .. socaSoliCabeRecord.last
                                   loop
                                     open c_oid_soli_cabe(socaSoliCabeRecord(i).soca_oid_soli_cabe);
                                       loop fetch c_oid_soli_cabe bulk collect into pedSolicPosicRecord LIMIT W_FILAS;
                                         if pedSolicPosicRecord.count > 0 then
                                           for i in pedSolicPosicRecord.first .. pedSolicPosicRecord.last
                                             loop
                                               delete
                                                 from ped_solic_posic
                                                where soca_oid_soli_cabe = pedSolicPosicRecord(i).soca_oid_soli_cabe;
                                             end loop;
                                         end if;
                                         exit when c_oid_soli_cabe%notfound;
                                       end loop;
                                     close c_oid_soli_cabe;

                                     delete
                             from rec_solic_opera
                            where SOCA_OID_SOLI_CABE  = socaSoliCabeRecord(i).soca_oid_soli_cabe;

                                     delete
                                       from ped_solic_cabec
                                      where oid_soli_cabe = socaSoliCabeRecord(i).soca_oid_soli_cabe;
                                   end loop;
                               end if;
                               exit when c_soca_oid_soli_cabe%notfound;
                             end loop;
                           close c_soca_oid_soli_cabe;

                           /*delete
                             from rec_solic_opera
                            where opre_oid_oper_recl = operReclRecRecord(i).oid_oper_recl;*/

                           delete
                             from rec_linea_opera_recla
                            where opre_oid_oper_recl = operReclRecRecord(i).oid_oper_recl;
                         end loop;
                     end if;
                     exit when c_oid_oper_recl%notfound;
                    end loop;
                   close c_oid_oper_recl;

                   delete
                     from rec_opera_recla
                    where care_oid_cabe_recl = recCabeReclRecord(i).oid_cabe_recl;
                 end loop;
             end if;
             exit when c_oid_cabe_recl%notfound;
           end loop;
         close c_oid_cabe_recl;

         delete
           from rec_cabec_recla
          where oid_cabe_recl = cabeReclRecord(i).oid_cabe_recl;
       end loop;
   end if;

   select oid_soli_cabe
     bulk collect into soliCabeRecord
     from REC_AUDIT_INFO
    where num_lote = psNumLote
      and oid_cabe_recl is null;

   if soliCabeRecord.count > 0 then
     for i in soliCabeRecord.first .. soliCabeRecord.last
       loop
         delete
           from ped_solic_posic
          where soca_oid_soli_cabe = soliCabeRecord(i).oid_soli_cabe;

         delete
           from ped_solic_cabec
          where oid_soli_cabe = soliCabeRecord(i).oid_soli_cabe;
       end loop;
   end if;

   update rec_audit_info
      set cod_esta_proc = '9',
          usu_elim = psUsuario,
          fec_elim = sysdate(),
          des_erro = 'Anulado por '|| psUsuario || ' el ' || trunc(sysdate())
    where num_lote = psNumLote;

      else
          -- ELIMINACION PARCIAL
          -- Paso 01 Borra en rec_solic_opera
          delete  from rec_solic_opera
          where opre_oid_oper_recl in (
          select oid_oper_recl from rec_opera_recla
          where care_oid_cabe_recl in (
          select oid_cabe_recl from rec_cabec_recla
          where oid_cabe_recl in(
          select oid_cabe_recl from REC_AUDIT_INFO a, ped_solic_cabec b
          where a.num_lote = psNumLote and A.OID_SOLI_CABE = B.OID_SOLI_CABE and B.GRPR_OID_GRUP_PROC < 5 )
          )
          );

          -- Paso 02 Borra en rec_linea_opera_recla
          delete from rec_linea_opera_recla
          where opre_oid_oper_recl in (
          select oid_oper_recl from rec_opera_recla
          where care_oid_cabe_recl in (
          select oid_cabe_recl from rec_cabec_recla
          where oid_cabe_recl in(
          select oid_cabe_recl from REC_AUDIT_INFO a, ped_solic_cabec b
          where a.num_lote = psNumLote and A.OID_SOLI_CABE = B.OID_SOLI_CABE and B.GRPR_OID_GRUP_PROC < 5 )
          )
          );

          -- Paso 03 Borra en rec_opera_recla
          delete from rec_opera_recla
          where care_oid_cabe_recl in (
          select oid_cabe_recl from rec_cabec_recla
          where oid_cabe_recl in(
          select oid_cabe_recl from REC_AUDIT_INFO a, ped_solic_cabec b
          where a.num_lote = psNumLote and A.OID_SOLI_CABE = B.OID_SOLI_CABE and B.GRPR_OID_GRUP_PROC < 5 )
          );


          -- Paso 04 Borra en rec_cabec_recla
          delete from rec_cabec_recla
          where oid_cabe_recl in(
          select oid_cabe_recl from REC_AUDIT_INFO a, ped_solic_cabec b
          where a.num_lote = psNumLote  and A.OID_SOLI_CABE = B.OID_SOLI_CABE and B.GRPR_OID_GRUP_PROC < 5 )
          ;

          --- Paso 05  Actualiza datos
          update REC_AUDIT_INFO
             set des_erro = 'Eliminado Parcialmente a solicitud de Usuario '|| psUsuario || ' el ' || trunc(sysdate()),
                 usu_elim = psUsuario, fec_elim = sysdate()
          where oid_soli_cabe  in
          (select a.oid_soli_cabe
          from REC_AUDIT_INFO a, ped_solic_cabec b
          where a.num_lote = psNumLote and A.OID_SOLI_CABE = B.OID_SOLI_CABE (+) and (B.GRPR_OID_GRUP_PROC< 5 or B.GRPR_OID_GRUP_PROC is null) );

          ---- Paso 06 Borra en ped_solic_cabec

          delete  from ped_solic_posic
          where soca_oid_soli_cabe in(
          select a.oid_soli_cabe from REC_AUDIT_INFO a, ped_solic_cabec b
          where a.num_lote = psNumLote and A.OID_SOLI_CABE = B.OID_SOLI_CABE  and B.GRPR_OID_GRUP_PROC < 5 );

          ---- Paso 07 Borra en ped_solic_cabec
          delete from ped_solic_cabec
          where oid_soli_cabe in (
          select oid_soli_cabe from REC_AUDIT_INFO a where  num_lote = psNumLote
          )  and   GRPR_OID_GRUP_PROC < 5;

      end if;


   psMensajeError := 'mantenimientoRECIngresoAtencionesForm.deleted';

 EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_ELIMI_LOTE_ATENC: '||ls_sqlerrm);

 END REC_PR_ELIMI_LOTE_ATENC;

/**************************************************************************
  Descripcion         : Procedure que revisa la oferta del producto que se
                        esta devolviendo
  Fecha Creacion      : 22/01/2013
  Parametros Entrada:
      pnOidSoliPosi   : Oid del producto que desea devolver
      pnNroUnidades   : Numero de unidades que devuelve
  Autor               : Sandro Quintana
***************************************************************************/
PROCEDURE REC_PR_REVIS_OFERT_DEVOL(pnOidSoliPosi     NUMBER,
                                   pnNroUnidades     NUMBER)IS

    lnnumregistros BINARY_INTEGER := 0;
    lnFactorOferta BINARY_INTEGER := 0;
    lnIndExclOfert BINARY_INTEGER := 0;

    lnNumPedido  NUMBER;
    lsoidniveofer          ped_solic_posic.oid_nive_ofer%TYPE;
    lsoidniveoferrang      ped_solic_posic.oid_nive_ofer_rang%TYPE;
    lsoidniveoferconc      ped_solic_posic.oid_nive_ofer%TYPE;
    lsoidniveoferrangconc  ped_solic_posic.oid_nive_ofer_rang%TYPE;


   BEGIN

       ---- Valida si es de simplificacion de cuvs  
       BEGIN
          select pos.oid_nive_ofer, pos.oid_nive_ofer_rang,
                 pos.oid_nive_ofer_conc, pos.oid_nive_ofer_rang_conc
            into lsoidniveofer, lsoidniveoferrang,
                 lsoidniveoferconc,lsoidniveoferrangconc
          from ped_solic_posic pos
          where POS.OID_SOLI_POSI = pnOidSoliPosi;

          EXCEPTION 
         WHEN NO_DATA_FOUND THEN
               lsoidniveofer := null;
               lsoidniveoferrang := null;
               lsoidniveoferconc := null;
               lsoidniveoferrangconc := null;
       END;

       ---- si  es simplicacion de cuv envia al nuevo SP
       if (nvl(lsoidniveofer,0) <> 0 or  nvl(lsoidniveoferconc,0) <> 0) then

         ---- invoca al nuevo SP
         rec_pkg_proce.REC_PR_REVIS_OFERT_DEVOL_SIMP(pnOidSoliPosi,pnNroUnidades);

       else
         
           ---- borra los registros
           delete REC_GTT_OBTIE_OFERT;

           ---- Obtiene el factor de la oferta basado en el numero de unidades solciitadas
           ---- y el indicador para excluir esta oferta
            select
                     case when pnNroUnidades < POD.VAL_FACT_REPE then  1
                            else ceil(pnNroUnidades / POD.VAL_FACT_REPE)  end FACTOR_OFERTA,
                     (SELECT count(*)
                            FROM sto_param_gener_occrr spo
                           WHERE spo.cod_para LIKE 'STO_EXCL_OFERTA%'
                           and TRIM(spo.val_param) =  TRIM(tofe.cod_tipo_ofer)) ind_excl
            into lnFactorOferta,lnIndExclOfert
            from ped_solic_posic psp,pre_ofert_Detal pod, pre_ofert pof, pre_estra pe, pre_tipo_ofert  tofe
            where PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
            and POD.OFER_OID_OFER = POF.OID_OFER
            and POF.COES_OID_ESTR = PE.OID_ESTR
            and pod.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
            and PSP.OID_SOLI_POSI = pnOidSoliPosi;

            --- si el cuv que se busca no es de una oferta excluida se inserta todos los registros
            if lnIndExclOfert = 0 then
           ---- Inserta los registros teniendo en cuenta las ofertas
           insert into REC_GTT_OBTIE_OFERT
            select detalle.*,
                     case when UNID_ATEN <  UNID_X_DEVOL then  UNID_ATEN
                            else UNID_X_DEVOL end UNID_a_DEVOL,
                     pnNroUnidades, lnFactorOferta, ''
            from
            (
            select PSP.OID_SOLI_POSI      POS_BUSCA,
                     PSP1.OID_SOLI_POSI    POS_OFERTA,
                     decode( PSP.OID_SOLI_POSI , PSP1.OID_SOLI_POSI,'1','0') IND_BUSCA,
                     PSP1.VAL_CODI_VENT   CODI_VENT,
                     PSP1.NUM_UNID_ATEN   UNID_ATEN,
                     POD1.VAL_FACT_REPE   FACT_REPE,
                     ( lnFactorOferta * POD1.VAL_FACT_REPE ) UNID_X_DEVOL,
                     ---PSP1.VAL_PREC_FACT_UNIT_LOCA PREC_PROD,
                     decode ( PSP1.VAL_PREC_CATA_UNIT_LOCA ,0,0, PSP1.VAL_PREC_FACT_UNIT_LOCA) PREC_PROD,
                     OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(psp1.prod_oid_prod)  DESC_PROD
            from ped_solic_posic psp,
                 pre_ofert_Detal pod,
                 pre_ofert pof,
                 ped_solic_posic psp1,
                 pre_ofert_Detal pod1,
                     pre_estra pe,
                     pre_tipo_ofert  tofe
            where PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
            and POD.OFER_OID_OFER = POF.OID_OFER
            and PSP.SOCA_OID_SOLI_CABE = PSP1.SOCA_OID_SOLI_CABE
            and PSP1.OFDE_OID_DETA_OFER = POD1.OID_DETA_OFER
            and POD1.OFER_OID_OFER = POF.OID_OFER
            and POF.COES_OID_ESTR = PE.OID_ESTR
                and pod1.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
            and PSP.OID_SOLI_POSI = pnOidSoliPosi ---- 41739640
            ---and POF.COES_OID_ESTR in ('2002','2006')
            and PE.COD_ESTR in ('002','006','004')
                AND TRIM(tofe.cod_tipo_ofer) NOT IN
                         (SELECT TRIM(val_param)
                            FROM sto_param_gener_occrr spo
                           WHERE spo.cod_para LIKE 'STO_EXCL_OFERTA%')
            order by 3 desc
            ) detalle;
           end if;

           ---- Cuenta registros insertados
           select count(*)
           into lnnumregistros
           from REC_GTT_OBTIE_OFERT;

           ---- si no encuentra los registros devuelve datos de un soloc codigo
           if lnnumregistros = 0 then
                insert into REC_GTT_OBTIE_OFERT
                select detalle.*,
                         case when UNID_ATEN <  UNID_X_DEVOL then  UNID_ATEN
                                else UNID_X_DEVOL end UNID_a_DEVOL,
                     pnNroUnidades, lnFactorOferta, ''
                from
                (
                select PSP.OID_SOLI_POSI      POS_BUSCA,
                         PSP.OID_SOLI_POSI    POS_OFERTA,
                         decode( PSP.OID_SOLI_POSI , PSP.OID_SOLI_POSI,'1','0') IND_BUSCA,
                         PSP.VAL_CODI_VENT   CODI_VENT,
                         PSP.NUM_UNID_ATEN   UNID_ATEN,
                         POD.VAL_FACT_REPE   FACT_REPE,
                         ( lnFactorOferta * POD.VAL_FACT_REPE ) UNID_X_DEVOL,
                         --- PSP.VAL_PREC_FACT_UNIT_LOCA PREC_PROD,
                         decode ( PSP.VAL_PREC_CATA_UNIT_LOCA ,0,0, PSP.VAL_PREC_FACT_UNIT_LOCA) PREC_PROD,
                         OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(psp.prod_oid_prod)  DESC_PROD
                from ped_solic_posic psp,pre_ofert_Detal pod, pre_ofert pof, pre_estra pe
                where PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
                and POD.OFER_OID_OFER = POF.OID_OFER
                and POF.COES_OID_ESTR = PE.OID_ESTR
                and PSP.OID_SOLI_POSI = pnOidSoliPosi ---- 41739640
                ---and POF.COES_OID_ESTR in ('2002','2006')
                ---and PE.COD_ESTR in ('002','006','004')
                order by 3 desc
                ) detalle;
          end if;

          ------ Actualiza mensaje del archivo registro principal
          update REC_GTT_OBTIE_OFERT
          set val_mens = (case when (unid_soli < fact_repe and unid_soli < unid_aten)  then
                                         'Unidades a reclamar menores que el factor '|| fact_repe
                                 when unid_soli < unid_a_devo then
                                         'Unidades deben ser multiplo del factor ' || fact_repe
                                 when unid_soli > unid_a_devo then
                                         'Unidades excede a lo despachado ' || unid_a_devo
                                 else
                                         ''  end)
          where ind_busc = 1;

      end if;


   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_REVIS_OFERT_DEVOL: '||ls_sqlerrm);

END REC_PR_REVIS_OFERT_DEVOL;

/**************************************************************************
  Descripcion         : Procedure que revisa la oferta del producto que se
                        esta devolviendo por simplificacion de cuvs
  Fecha Creacion      : 22/01/2013
  Parametros Entrada:
      pnOidSoliPosi   : Oid del producto que desea devolver
      pnNroUnidades   : Numero de unidades que devuelve
      
      STO_IND_DEV_OFERTASC  = '1' ofertas Nx
                            = '2' Ofertas gratis
                            = '3' Ofertas NX y gratis
                            = '0' No procesa nada
                            
  Autor               : Sandro Quintana
***************************************************************************/
PROCEDURE REC_PR_REVIS_OFERT_DEVOL_SIMP(pnOidSoliPosi     NUMBER,
                                   pnNroUnidades     NUMBER)IS

    lnnumregistros BINARY_INTEGER := 0;
    lnFactorOferta BINARY_INTEGER := 0;
    lnIndExclOfert BINARY_INTEGER := 0;
    pscodigopais       sto_param_gener_occrr.cod_pais%TYPE;
    lsparametroval     sto_param_gener_occrr.val_param%TYPE;
    lntipoofer   BINARY_INTEGER := 0;
    
   BEGIN
     
        select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;
        
        lsparametroval := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_DEV_OFERTASC'),'0');      

        --- si es diferente a cero es una oferta Nx
        select count(0) into lntipoofer
        from ped_solic_posic pos, ped_solic_posic pos1
        where POS.SOCA_OID_SOLI_CABE = POS1.SOCA_OID_SOLI_CABE
        and POS.OID_NIVE_OFER = POS1.OID_NIVE_OFER
        and POS.OID_NIVE_OFER_RANG = POS1.OID_NIVE_OFER_RANG 
        and POS.OID_SOLI_POSI = pnOidSoliPosi
        and POS1.VAL_CODI_ORIG is not null;

   
       ---- borra los registros
       delete REC_GTT_OBTIE_OFERT;

       ---- Obtiene el factor de la oferta basado en el numero de unidades solciitadas
       ---- y el indicador para excluir esta oferta
        select
                 case when pnNroUnidades < POD.VAL_FACT_REPE then  1
                        else ceil(pnNroUnidades / POD.VAL_FACT_REPE)  end FACTOR_OFERTA,
                 (SELECT count(*)
                        FROM sto_param_gener_occrr spo
                       WHERE spo.cod_para LIKE 'STO_EXCL_OFERTA%'
                       and TRIM(spo.val_param) =  TRIM(tofe.cod_tipo_ofer)) ind_excl
        into lnFactorOferta,lnIndExclOfert
        from ped_solic_posic psp,pre_ofert_Detal pod, pre_ofert pof, pre_estra pe, pre_tipo_ofert  tofe
        where PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
        and POD.OFER_OID_OFER = POF.OID_OFER
        and POF.COES_OID_ESTR = PE.OID_ESTR
        and pod.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
        and PSP.OID_SOLI_POSI = pnOidSoliPosi;

        --- Evalua el parametro:
        --- lsparametroval = '0'  no se validad nada
        --- lsparametroval = '1' y lntipoofer = 0 no se validad nada
        --- lsparametroval = '2' y lntipoofer <> 0 no se validad nada
        if (  lsparametroval = '0' or
             (lsparametroval = '1'  and lntipoofer = 0) or 
             (lsparametroval = '2'  and lntipoofer <> 0) )
             then
           lnFactorOferta := lnFactorOferta * 1;  ---- Momentaneo
        else

            --- si el cuv que se busca no es de una oferta excluida se inserta todos los registros
            if lnIndExclOfert = 0 then
               ---- Inserta los registros teniendo en cuenta las ofertas
               insert into REC_GTT_OBTIE_OFERT
                select detalle.*,
                         case when UNID_ATEN <  UNID_X_DEVOL then  UNID_ATEN
                                else UNID_X_DEVOL end UNID_a_DEVOL,
                         pnNroUnidades, lnFactorOferta, ''
                from
                (
                select PSP.OID_SOLI_POSI      POS_BUSCA,
                         PSP1.OID_SOLI_POSI    POS_OFERTA,
                         decode( PSP.OID_SOLI_POSI , PSP1.OID_SOLI_POSI,'1','0') IND_BUSCA,
                         PSP1.VAL_CODI_VENT   CODI_VENT,
                         PSP1.NUM_UNID_ATEN   UNID_ATEN,
                         POD1.VAL_FACT_REPE   FACT_REPE,
                         ( lnFactorOferta * POD1.VAL_FACT_REPE ) UNID_X_DEVOL,
                         ---PSP1.VAL_PREC_FACT_UNIT_LOCA PREC_PROD,
                         decode ( PSP1.VAL_PREC_CATA_UNIT_LOCA ,0,0, PSP1.VAL_PREC_FACT_UNIT_LOCA) PREC_PROD,
                         OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(psp1.prod_oid_prod)  DESC_PROD
                from ped_solic_posic psp,
                     pre_ofert_Detal pod,
                     pre_ofert pof,
                     ped_solic_posic psp1,
                     pre_ofert_Detal pod1,
                         pre_estra pe,
                         pre_tipo_ofert  tofe
                where PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
                and POD.OFER_OID_OFER = POF.OID_OFER
                and PSP.SOCA_OID_SOLI_CABE = PSP1.SOCA_OID_SOLI_CABE
                and PSP1.OFDE_OID_DETA_OFER = POD1.OID_DETA_OFER
                and POD1.OFER_OID_OFER = POF.OID_OFER
                and POF.COES_OID_ESTR = PE.OID_ESTR
                    and pod1.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
                and PSP.OID_SOLI_POSI = pnOidSoliPosi ---- 41739640
                ---and POF.COES_OID_ESTR in ('2002','2006')
                and PE.COD_ESTR in ('002','006','004')
                    AND TRIM(tofe.cod_tipo_ofer) NOT IN
                             (SELECT TRIM(val_param)
                                FROM sto_param_gener_occrr spo
                               WHERE spo.cod_para LIKE 'STO_EXCL_OFERTA%')
                order by 3 desc
                ) detalle;
           end if;
        end if;

       ---- Cuenta registros insertados
       select count(*)
       into lnnumregistros
       from REC_GTT_OBTIE_OFERT;

       ---- si no encuentra los registros devuelve datos de un soloc codigo
       if lnnumregistros = 0 then
            insert into REC_GTT_OBTIE_OFERT
            select detalle.*,
                     case when UNID_ATEN <  UNID_X_DEVOL then  UNID_ATEN
                            else UNID_X_DEVOL end UNID_a_DEVOL,
                 pnNroUnidades, lnFactorOferta, ''
            from
            (
            select PSP.OID_SOLI_POSI      POS_BUSCA,
                     PSP.OID_SOLI_POSI    POS_OFERTA,
                     decode( PSP.OID_SOLI_POSI , PSP.OID_SOLI_POSI,'1','0') IND_BUSCA,
                     PSP.VAL_CODI_VENT   CODI_VENT,
                     PSP.NUM_UNID_ATEN   UNID_ATEN,
                     POD.VAL_FACT_REPE   FACT_REPE,
                     ( lnFactorOferta * POD.VAL_FACT_REPE ) UNID_X_DEVOL,
                     --- PSP.VAL_PREC_FACT_UNIT_LOCA PREC_PROD,
                     decode ( PSP.VAL_PREC_CATA_UNIT_LOCA ,0,0, PSP.VAL_PREC_FACT_UNIT_LOCA) PREC_PROD,
                     OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(psp.prod_oid_prod)  DESC_PROD
            from ped_solic_posic psp,pre_ofert_Detal pod, pre_ofert pof, pre_estra pe
            where PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
            and POD.OFER_OID_OFER = POF.OID_OFER
            and POF.COES_OID_ESTR = PE.OID_ESTR
            and PSP.OID_SOLI_POSI = pnOidSoliPosi ---- 41739640
            ---and POF.COES_OID_ESTR in ('2002','2006')
            ---and PE.COD_ESTR in ('002','006','004')
            order by 3 desc
            ) detalle;
      end if;

      ------ Actualiza mensaje del archivo registro principal
      update REC_GTT_OBTIE_OFERT
      set val_mens = (case when (unid_soli < fact_repe and unid_soli < unid_aten)  then
                                     'Unidades a reclamar menores que el factor '|| fact_repe
                             when unid_soli < unid_a_devo then
                                     'Unidades deben ser multiplo del factor ' || fact_repe
                             when unid_soli > unid_a_devo then
                                     'Unidades excede a lo despachado ' || unid_a_devo
                             else
                                     ''  end)
      where ind_busc = 1;


   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_REVIS_OFERT_DEVOL_SIMP: '||ls_sqlerrm);

END REC_PR_REVIS_OFERT_DEVOL_SIMP;


/**************************************************************************
  Descripcion         : REC_PR_DESCA_ARCHI_ANULA
                        Proceso de Descarga de Archivo de Anulaciones y NMPs
  Fecha Creacion      : 22/04/2013
  Autor               : Eduardo Sanchez Castillo
  Parametros:
      psNumeroLote    : Numero de Lote
      psCodigoPais    : Codigo del Pais
      psCodigoSistema : Codigo del Sistema
***************************************************************************/
  PROCEDURE REC_PR_DESCA_ARCHI_ANULA(
    psNumeroLote       VARCHAR2,
    psCodigoPais       VARCHAR2,
    psCodigoSistema    VARCHAR2
 ) IS
    CURSOR c_interfaz IS
      SELECT DECODE ((SELECT (SUM (NVL (soc.ind_oc, 0)))
                      FROM ped_solic_cabec soc
                     WHERE soc.soca_oid_soli_cabe = psc.oid_soli_cabe),
                   0, '02', '01') ped_serv,
             psc.val_nume_soli,
             rra.cod_clie
        FROM rec_repos_anula rra, ped_solic_cabec psc
       WHERE rra.num_docu_refe = psc.val_nume_soli
         AND rra.num_lote = psNumeroLote
         AND rra.cod_esta_proc = '1'
         AND rra.oid_soli_cabe IS NOT NULL;

    TYPE interfazrec IS RECORD(
      ped_serv          VARCHAR2(2),
      val_nume_soli     PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE,
      cod_clie          REC_REPOS_ANULA.COD_CLIE%TYPE
    );

    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;

    /* Variables usadas para la generacion del archivo de texto */
    lsDirTempo              BAS_INTER.DIR_TEMP%TYPE;
    v_handle                UTL_FILE.FILE_TYPE;
    W_FILAS                 NUMBER := 1000;
    lsLinea                 VARCHAR2(1000);
    lsNombreArchivo         VARCHAR2(100);
    lsFlagProceso           VARCHAR2(100);
    lsPrefijoNombreArchivo  VARCHAR2(50);
    lbAbrirUtlFile          BOOLEAN;
    lbFlagArchivoCreado     BOOLEAN := FALSE;
BEGIN
    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones

    BEGIN
        SELECT val_para val_para
        into lsFlagProceso
        FROM BAS_PARAM_PAIS
        WHERE cod_pais = psCodigoPais
        AND cod_sist = psCodigoSistema
        AND nom_para = 'indGenerarArchivoAnul'
        AND ind_acti = 1;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            lsFlagProceso := '';
    END;

    IF lsFlagProceso = '1' THEN

        BEGIN
            SELECT val_para
            INTO lsDirTempo
            FROM BAS_PARAM_PAIS
            WHERE cod_pais = psCodigoPais
            AND cod_sist = psCodigoSistema
            AND nom_para = 'dirArchivoAnul'
            AND ind_acti = 1;

        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                lsDirTempo := '';
        END;

        BEGIN
            SELECT val_para
            INTO lsPrefijoNombreArchivo
            FROM BAS_PARAM_PAIS
            WHERE cod_pais = psCodigoPais
            AND cod_sist = psCodigoSistema
            AND nom_para = 'preArchivoAnul'
            AND ind_acti = 1;

        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                lsPrefijoNombreArchivo := '';
        END;

        IF LENGTH(lsDirTempo) > 0 AND LENGTH(lsPrefijoNombreArchivo) > 0 THEN
            /* Creando directorio a crear para el UTL_FILE */
            GEN_PKG_INTER_ARCHI.GEN_PR_CREA_DIREC('SICC_DIR',lsDirTempo);

            /* Generando Nombre del Archivo TMP */
            lsNombreArchivo := lsPrefijoNombreArchivo || psNumeroLote ||'.TXT';

            OPEN c_interfaz;
            LOOP
               FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

               IF interfazRecord.COUNT > 0 THEN

                  IF lbFlagArchivoCreado = FALSE THEN
                    /* Generando Archivo de Texto (Cabecera) */
                    v_handle   := UTL_FILE.FOPEN('SICC_DIR', lsNombreArchivo,'W');

                    lbFlagArchivoCreado:= TRUE;
                  END IF;

                  FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                      lsLinea :=
                                  interfazRecord(x).ped_serv        ||';'||
                                  interfazRecord(x).val_nume_soli   ||';'||
                                  interfazRecord(x).cod_clie;

                      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                  END LOOP;
               END IF;
               EXIT WHEN c_interfaz%NOTFOUND;
            END LOOP;
            CLOSE c_interfaz;

            IF lbFlagArchivoCreado = TRUE THEN
            ---CERRAR EL ARCHIVO
            UTL_FILE.FCLOSE(v_handle);
            END IF;

            UPDATE rec_repos_anula rra
               SET ind_envi_ape = 'S'
             WHERE rra.num_lote = psNumeroLote
               AND rra.cod_esta_proc = '1'
               AND rra.oid_soli_cabe IS NOT NULL;

        END IF;

    END IF;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_DESCA_ARCHI_ANULA: '||ls_sqlerrm);
END REC_PR_DESCA_ARCHI_ANULA;

/**************************************************************************
  Descripcion         : Funcion que verifica si se excluye al cliente del
                        control de Faltantes
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
  Devuelve:
      S : SE EXCLUYE
      N : NO SE EXCLUYE
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_VERIF_EXCLU_FALTA(pn_Oid_Cliente     NUMBER,
                                    pn_oid_periodo     NUMBER) RETURN VARCHAR2
   IS
      lnoidperiodo     cra_perio.oid_peri%type;
      lnaux number :=0;
      lsresult varchar2(1) := 'N';
   BEGIN

        lnoidperiodo := pn_oid_periodo;

        -- Evalua si existe el cliente
        select count(1)
          into lnaux
          from sto_bloqu_contr s
          where s.cod_tipo_docu = 'SPVD'
            and s.oid_peri = lnoidperiodo
            and s.clie_oid_clie = pn_Oid_Cliente
            and s.ind_tipo_bloq = '1'
            and s.ind_acti = '1'
            and s.val_moti_bloq = 'F';

        IF lnaux = 0 THEN
           -- Evalua si existe la tipologia del cliente como exclusion
           select count(1)
             into lnaux
             from mae_clien_tipo_subti t,
                  mae_clien_clasi c ,
                  sto_bloqu_contr s
            where t.clie_oid_clie = pn_Oid_Cliente
              and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
              and s.cod_tipo_docu = 'SPVD'
              and s.ind_tipo_bloq = '1'
              and s.ind_acti = '1'
              and s.val_moti_bloq = 'F'
              and s.oid_peri = lnoidperiodo
              and s.oid_tipo_clie = t.ticl_oid_tipo_clie
              and s.oid_subt_clie = t.sbti_oid_subt_clie
              and s.oid_clas_clie = c.clas_oid_clas
              and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi;

          IF lnaux = 0 THEN
             -- Si NO existe Tipologia, se verifica que exista Zona + Tipologia en blanco
             select count(1)
               into lnaux
               from sto_bloqu_contr s,
                    MAE_CLIEN,
                    MAE_CLIEN_UNIDA_ADMIN,
                    ZON_TERRI_ADMIN,
                    ZON_SECCI,
                    ZON_ZONA,
                    ZON_REGIO
              where s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '1'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'F'
                and s.oid_peri = lnoidperiodo
                and s.oid_tipo_clie is null
                and s.oid_subt_clie is null
                and s.oid_clas_clie is null
                and s.oid_tipo_clas_clie is null
                AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                AND ZON_ZONA.OID_ZONA = s.oid_zona;

                IF lnaux = 0 THEN
                   -- Tipologia en blanco + Zona en Blanco + Region
                   select count(1)
                     into lnaux
                     from sto_bloqu_contr s,
                          MAE_CLIEN,
                          MAE_CLIEN_UNIDA_ADMIN,
                          ZON_TERRI_ADMIN,
                          ZON_SECCI,
                          ZON_ZONA,
                          ZON_REGIO
                    where s.cod_tipo_docu = 'SPVD'
                      and s.ind_tipo_bloq = '1'
                      and s.ind_acti = '1'
                      and s.val_moti_bloq = 'F'
                      and s.oid_peri = lnoidperiodo
                      and s.oid_tipo_clie is null
                      and s.oid_subt_clie is null
                      and s.oid_clas_clie is null
                      and s.oid_tipo_clas_clie is null
                      AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                      AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                      AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                      AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                      AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                      AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                      AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                      AND ZON_REGIO.OID_REGI = s.oid_regi
                      AND s.oid_zona is null ;

                   IF lnaux != 0 THEN
                      -- Existe Tipologia en blanco + Zona en Blanco + Region
                      lsresult := 'S';
                   END IF;

                ELSE
                    -- Existe Tipologia en blanco + Zona
                    lsresult := 'S';
                END IF;

          ELSE
              -- Si existe Tipologia, se verifica que exista Tipologia + Zona
             select count(1)
               into lnaux
               from mae_clien_tipo_subti t,
                    mae_clien_clasi c ,
                    sto_bloqu_contr s,
                    MAE_CLIEN,
                    MAE_CLIEN_UNIDA_ADMIN,
                    ZON_TERRI_ADMIN,
                    ZON_SECCI,
                    ZON_ZONA,
                    ZON_REGIO
              where t.clie_oid_clie = pn_Oid_Cliente
                and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                and s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '1'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'F'
                and s.oid_peri = lnoidperiodo
                and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                and s.oid_subt_clie = t.sbti_oid_subt_clie
                and s.oid_clas_clie = c.clas_oid_clas
                and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                 AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                 AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                 AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                 AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                 AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                 AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                 AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                 AND ZON_ZONA.OID_ZONA = s.oid_zona;

              IF lnaux = 0 THEN
                 -- Si existe Tipologia pero sin zonas, se verifica que exista Tipologia + Region + ZOna en blanco
                 select count(1)
                   into lnaux
                   from mae_clien_tipo_subti t,
                        mae_clien_clasi c ,
                        sto_bloqu_contr s,
                        MAE_CLIEN,
                        MAE_CLIEN_UNIDA_ADMIN,
                        ZON_TERRI_ADMIN,
                        ZON_SECCI,
                        ZON_ZONA,
                        ZON_REGIO
                  where t.clie_oid_clie = pn_Oid_Cliente
                    and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                    and s.cod_tipo_docu = 'SPVD'
                    and s.ind_tipo_bloq = '1'
                    and s.ind_acti = '1'
                    and s.val_moti_bloq = 'F'
                    and s.oid_peri = lnoidperiodo
                    and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                    and s.oid_subt_clie = t.sbti_oid_subt_clie
                    and s.oid_clas_clie = c.clas_oid_clas
                    and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                     AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                     AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                     AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                     AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                     AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                     AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                     AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                     AND s.oid_zona is null
                     AND ZON_REGIO.OID_REGI = s.oid_regi;

                     IF lnaux = 0 THEN
                       -- Si existe Tipologia pero sin zonas, se verifica que exista Tipologia + Region en blanco + Zona en blanco
                       select count(1)
                         into lnaux
                         from mae_clien_tipo_subti t,
                              mae_clien_clasi c ,
                              sto_bloqu_contr s
                        where t.clie_oid_clie = pn_Oid_Cliente
                          and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                          and s.cod_tipo_docu = 'SPVD'
                          and s.ind_tipo_bloq = '1'
                          and s.ind_acti = '1'
                          and s.val_moti_bloq = 'F'
                          and s.oid_peri = lnoidperiodo
                          and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                          and s.oid_subt_clie = t.sbti_oid_subt_clie
                          and s.oid_clas_clie = c.clas_oid_clas
                          and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                          and s.oid_regi is null
                          and s.oid_zona is null;

                          IF lnaux != 0 THEN
                             -- Existe Tipologia + Region en blanco + Zona en blanco
                             lsresult := 'S';
                          END IF;

                     ELSE
                         -- Existe Tipologia + Region + Zona en blanco
                         lsresult := 'S';
                     END IF;

              ELSE
                 -- Existe Tipologia + Zona
                 lsresult := 'S';
              END IF;

          END IF;

        ELSE
            -- Existe el cliente
            lsresult := 'S';

        END IF;

        return lsresult;

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_VERIF_EXCLU_FALTA: '||ls_sqlerrm);
END REC_FN_VERIF_EXCLU_FALTA;

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total del pedido y
                        el monto total de faltante
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_EVALU_FALTA(pnValNumeSoli     NUMBER,
                                   pnMontoPedido     OUT NUMBER,
                                   pnMontoDevolucion OUT NUMBER)IS

   BEGIN

       select nvl(max(a.VAL_TOTA_PAGA_LOCA),0) MontoPedido,
              nvl(sum(f.VAL_TOTA_PAGA_LOCA),0) MontoDevolucion
         into pnMontoPedido,
              pnMontoDevolucion
         from ped_solic_cabec a,
              rec_cabec_recla b,
              rec_opera_recla c,
              rec_solic_opera d,
              ped_solic_cabec e,
              ped_solic_cabec f,
              ped_tipo_solic_pais tsp,
              ped_tipo_solic ts
        where a.VAL_NUME_SOLI = pnValNumeSoli
        and a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
        and b.oid_cabe_recl = c.CARE_OID_CABE_RECL
        and c.OID_OPER_RECL  = d.opre_oid_oper_recl
        and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
        and oid_tipo_soli_pais = d.TSPA_OID_TIPO_SOLI_PAIS
        and cod_tipo_soli in ('SDFM','SDFA','SDMF')
        and d.SOCA_OID_SOLI_CABE = e.OID_SOLI_CABE
        and e.SOCA_OID_SOLI_CABE = f.OID_SOLI_CABE;

        if pnMontoPedido = 0 then
           select nvl(max(a.VAL_TOTA_PAGA_LOCA),0) MontoPedido
             into pnMontoPedido
             from ped_solic_cabec a
            where a.VAL_NUME_SOLI = pnValNumeSoli;

        end if;

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_MONTO_EVALU_FALTA: '||ls_sqlerrm);
END REC_PR_MONTO_EVALU_FALTA;



/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total de gestion y
                        el monto del documento por gestion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_GESTI_FALTA(pnValNumeSoli              NUMBER,
                                   pnMontoTotalGestion    OUT NUMBER,
                                   psNumLote                  VARCHAR2,
                                   psNumDocu                  VARCHAR2,
                                   psCodClie                  VARCHAR2,
                                   psCodPeri                  VARCHAR2,
                                   psCodPais                  VARCHAR2,
                                   pnMontoDocGestion      OUT NUMBER) IS
   BEGIN
     select nvl(sum(b.VAL_PREC_CATA_DEVU * b.CAN_VENT_DEVU),0) MontoTotalxGestion
       into pnMontoTotalGestion
       from int_solic_conso_poven_cabec a,
            int_solic_conso_poven_detal b,
            sto_docum_digit             dig
      where a.NUM_LOTE = b.NUM_LOTE
        and a.NUM_DOCU = b.NUM_DOCU
        and a.COD_CLIE = b.COD_CLIE
        and a.COD_PERI = b.COD_PERI
        and a.COD_PAIS = b.COD_PAIS
        AND dig.sec_nume_docu = b.sec_nume_docu
        and a.NUM_DOCU_CRUC = pnValNumeSoli
        and a.cod_pais = psCodPais
        and a.cod_clie = psCodClie
        and b.cod_oper in('FM','FA')
        AND dig.ind_envi = 0
        AND dig.ind_rech = 0;

      select nvl(sum(b.VAL_PREC_CATA_DEVU * b.CAN_VENT_DEVU),0) MontoDocxGestion
       into pnMontoDocGestion
       from int_solic_conso_poven_cabec a,
            int_solic_conso_poven_detal b,
            sto_docum_digit             dig
      where a.NUM_LOTE = b.NUM_LOTE
        and a.NUM_DOCU = b.NUM_DOCU
        and a.COD_CLIE = b.COD_CLIE
        and a.COD_PERI = b.COD_PERI
        and a.COD_PAIS = b.COD_PAIS
        AND dig.sec_nume_docu = b.sec_nume_docu
        and a.NUM_DOCU_CRUC = pnValNumeSoli
        and a.NUM_LOTE = psNumLote
        and a.NUM_DOCU = psNumDocu
        and a.COD_CLIE = psCodClie
        and a.COD_PERI = psCodPeri
        and a.COD_PAIS = psCodPais
        and b.cod_oper in('FM','FA')
        AND dig.ind_envi = 0
        AND dig.ind_rech = 0;

   EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_MONTO_GESTI_FALTA: '||ls_sqlerrm);
END REC_PR_MONTO_GESTI_FALTA;

----------------------------------
/**************************************************************************
  Descripcion         : Funcion que Retorna el porcentaje de faltante
                        que debera de aplicar en la validacion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_PORCE_MONTO_FALTA(pn_Oid_Cliente     NUMBER,
                                    pscodigopais       VARCHAR2,
                                    pscodigoperiodo    VARCHAR2) RETURN NUMBER IS
      lnoidperiodo     cra_perio.oid_peri%type;
      lnaux            number :=0;
      lsresult         sto_bloqu_contr.val_pmon_devu%type ;

      --lsmondevparam varchar2(10);
      lnvalporcentaje  sto_bloqu_contr.val_pmon_devu%type;
   BEGIN

       lnoidperiodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);

       IF REC_FN_VERIF_EXCLU_FALTA(pn_Oid_Cliente, lnoidperiodo) = 'S' THEN
          lsresult := 100;
       ELSE
           lsresult := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_PMON_FM');



        begin
          -- Evalua si existe el cliente
          select count(1), s.val_pmon_devu
            into lnaux,
                 lnvalporcentaje
            from sto_bloqu_contr s
           where s.clie_oid_clie = pn_Oid_Cliente
             and s.cod_tipo_docu = 'SPVD'
             and s.ind_tipo_bloq = '0'
             and s.ind_acti = '1'
             and s.val_moti_bloq = 'F'
             --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
           group by s.val_pmon_devu;

           lsresult:= lnvalporcentaje;

        exception
        when no_data_found then

           begin

             -- Evalua si existe la tipologia del cliente
             select count(1), s.val_pmon_devu
               into lnaux,
                    lnvalporcentaje
               from mae_clien_tipo_subti t,
                    mae_clien_clasi c ,
                    sto_bloqu_contr s
              where t.clie_oid_clie = pn_Oid_Cliente
                and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                and s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '0'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'F'
                --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                and s.oid_subt_clie = t.sbti_oid_subt_clie
                and s.oid_clas_clie = c.clas_oid_clas
                and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
              group by s.val_pmon_devu;


            begin
                 -- Si existe, evalua la tipologia + Zona
                 select count(1), s.val_pmon_devu
                 into lnaux, lnvalporcentaje
                 from mae_clien_tipo_subti t,
                      mae_clien_clasi c ,
                      sto_bloqu_contr s,
                      MAE_CLIEN,
                      MAE_CLIEN_UNIDA_ADMIN,
                      ZON_TERRI_ADMIN,
                      ZON_SECCI,
                      ZON_ZONA,
                      ZON_REGIO
                where t.clie_oid_clie = pn_Oid_Cliente
                  and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                  and s.cod_tipo_docu = 'SPVD'
                  and s.ind_tipo_bloq = '0'
                  and s.ind_acti = '1'
                  and s.val_moti_bloq = 'F'
                  --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                  and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                  and s.oid_subt_clie = t.sbti_oid_subt_clie
                  and s.oid_clas_clie = c.clas_oid_clas
                  and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                   AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                   AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                   AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                   AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                   AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                   AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                   AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                   AND ZON_ZONA.OID_ZONA = s.oid_zona
                group by s.val_pmon_devu;

                lsresult:= lnvalporcentaje;

              exception
      				when no_data_found then

                  begin
                     -- Si existe, evalua la tipologia + Region + Zona en blanco
                     select count(1), s.val_pmon_devu
                     into lnaux, lnvalporcentaje
                     from mae_clien_tipo_subti t,
                          mae_clien_clasi c ,
                          sto_bloqu_contr s,
                          MAE_CLIEN,
                          MAE_CLIEN_UNIDA_ADMIN,
                          ZON_TERRI_ADMIN,
                          ZON_SECCI,
                          ZON_ZONA,
                          ZON_REGIO
                    where t.clie_oid_clie = pn_Oid_Cliente
                      and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                      and s.cod_tipo_docu = 'SPVD'
                      and s.ind_tipo_bloq = '0'
                      and s.ind_acti = '1'
                      and s.val_moti_bloq = 'F'
                      --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                      and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                      and s.oid_subt_clie = t.sbti_oid_subt_clie
                      and s.oid_clas_clie = c.clas_oid_clas
                      and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                       AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                       AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                       AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                       AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                       AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                       AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                       AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                       AND s.oid_zona is null
                       AND ZON_REGIO.Oid_Regi = s.oid_regi
                    group by s.val_pmon_devu;

                    lsresult:= lnvalporcentaje;

                  exception
                      when no_data_found then

                         begin

                             -- Si existe, evalua la tipologia + Region en blanco + Zona en blanco
                             select count(1), s.val_pmon_devu
                             into lnaux, lnvalporcentaje
                             from mae_clien_tipo_subti t,
                                  mae_clien_clasi c ,
                                  sto_bloqu_contr s,
                                  MAE_CLIEN,
                                  MAE_CLIEN_UNIDA_ADMIN,
                                  ZON_TERRI_ADMIN,
                                  ZON_SECCI,
                                  ZON_ZONA,
                                  ZON_REGIO
                            where t.clie_oid_clie = pn_Oid_Cliente
                              and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                              and s.cod_tipo_docu = 'SPVD'
                              and s.ind_tipo_bloq = '0'
                              and s.ind_acti = '1'
                              and s.val_moti_bloq = 'F'
                              --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                              and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                              and s.oid_subt_clie = t.sbti_oid_subt_clie
                              and s.oid_clas_clie = c.clas_oid_clas
                              and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                               AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                               AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                               AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                               AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                               AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                               AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                               AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                               AND s.oid_zona is null
                               AND s.oid_regi is null
                            group by s.val_pmon_devu;

                         lsresult:= lnvalporcentaje;

                         exception
                             when no_data_found then
                               null;
                         end;
                  end;

              end;

            exception
            		when no_data_found then
                   begin
                     -- Si NO existe Tipologia, se verifica que exista Zona + Tipologia en blanco
                     select count(1),s.val_pmon_devu
                       into lnaux,
                            lnvalporcentaje
                       from sto_bloqu_contr s,
                            MAE_CLIEN,
                            MAE_CLIEN_UNIDA_ADMIN,
                            ZON_TERRI_ADMIN,
                            ZON_SECCI,
                            ZON_ZONA,
                            ZON_REGIO
                      where s.cod_tipo_docu = 'SPVD'
                        and s.ind_tipo_bloq = '0'
                        and s.ind_acti = '1'
                        and s.val_moti_bloq = 'F'
                        --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                        and s.oid_tipo_clie is null
                        and s.oid_subt_clie is null
                        and s.oid_clas_clie is null
                        and s.oid_tipo_clas_clie is null
                        AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                        AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                        AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                        AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                        AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                        AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                        AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                        AND ZON_ZONA.OID_ZONA = s.oid_zona
                      group by s.val_pmon_devu;

                      lsresult:= lnvalporcentaje;

                 exception
                     when no_data_found then
                       begin
                         -- Tipologia en blanco + Zona en Blanco + Region
                         select count(1),s.val_pmon_devu
                           into lnaux,
                                lnvalporcentaje
                           from sto_bloqu_contr s,
                                MAE_CLIEN,
                                MAE_CLIEN_UNIDA_ADMIN,
                                ZON_TERRI_ADMIN,
                                ZON_SECCI,
                                ZON_ZONA,
                                ZON_REGIO
                          where s.cod_tipo_docu = 'SPVD'
                            and s.ind_tipo_bloq = '0'
                            and s.ind_acti = '1'
                            and s.val_moti_bloq = 'F'
                            --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                            and s.oid_tipo_clie is null
                            and s.oid_subt_clie is null
                            and s.oid_clas_clie is null
                            and s.oid_tipo_clas_clie is null
                            AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                            AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                            AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                            AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                            AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                            AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                            AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                            AND ZON_REGIO.OID_REGI = s.oid_regi
                            AND s.oid_zona is null
                          group by s.val_pmon_devu ;

                          lsresult:= lnvalporcentaje;

                      exception
                         when no_data_found then
                              null;
                      end;

                 end;

              end;

           end;

       END IF;

       return nvl(lsresult,100);

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_PORCE_MONTO_FALTA: '||ls_sqlerrm);
END REC_FN_PORCE_MONTO_FALTA;

/**************************************************************************
  Descripcion         : REC_PR_PROCE_INGRE_ATENC
                        Proceso de Ingreso de Atenciones
  Fecha Creacion      : 29/12/2008
  Parametros Entrada:
      psTipoProducto       : Tipo de Producto
      psTipoAtencion       : Tipo de Atencion
      psCodigoCliente      : Codigo de cliente
      psCampanhaProceso    : Campnha de proceso
      psCampanhaReferencia : Campanha de referencia
      psCodigoPais         : Codigo Pais
      psCodigoUsuario      : Codigo Usuario
      psMensajeError       : Mensaje Error
  Autor               : Dennys Oliva Iriate
***************************************************************************/
PROCEDURE REC_PR_PROCE_INGRE_ATEN_MAS(psNumeroLote   VARCHAR2,
                                   psTipoProducto VARCHAR2,
                                   psTipoAtencion VARCHAR2,
                                   psCodigoCliente VARCHAR2,
                                   psCampanhaProceso VARCHAR2,
                                   psCampanhaReferencia VARCHAR2,
                                   psCodigoPais VARCHAR2,
                                   psCodigoUsuario VARCHAR2,
                                   psMensajeError OUT VARCHAR2,
                                   psTipoOperacion VARCHAR2
                                   )
   IS
   i              BINARY_INTEGER := 0;
   j              BINARY_INTEGER := 0;
   lsmensajeerror VARCHAR2(4000);
   lscodigocliente VARCHAR2(15);


   BEGIN

   FOR y IN (SELECT distinct REC_GTT_DETAL_INGRE_ATEN_MAS.cod_clie
             from REC_GTT_DETAL_INGRE_ATEN_MAS
             where REC_GTT_DETAL_INGRE_ATEN_MAS.des_error is null
  ) LOOP

    lscodigocliente := y.cod_clie;

     delete  REC_GTT_DETAL_INGRE_ATENC;

     insert into REC_GTT_DETAL_INGRE_ATENC
       (COD_VENT,
        VAL_PREC,
        VAL_PREC_CONT,
        COD_SAP,
        DES_PROD,
        OID_TIPO_OFER,
        OID_PROD,
        OID_MATR_FACT,
        OID_OPER_RECL,
        OID_SOLI,
        OID_PARA_NIVE,
        OID_LOTE_ARTI,
        OID_TIPO_CONC,
        OID_DETAL_OFER,
        OID_FORM_PAGO,
        NUM_UNID)
       select COD_VENT,
                 VAL_PREC,
                 VAL_PREC_CONT,
                 COD_SAP,
                 DES_PROD,
                 OID_TIPO_OFER,
                 OID_PROD,
                 OID_MATR_FACT,
                 OID_OPER_RECL,
                 OID_SOLI,
                 OID_PARA_NIVE,
                 OID_LOTE_ARTI,
                 OID_TIPO_CONC,
                 OID_DETAL_OFER,
                 OID_FORM_PAGO,
                 NUM_UNID
        from REC_GTT_DETAL_INGRE_ATEN_MAS atenciones
        where atenciones.cod_clie = lscodigocliente
        and atenciones.des_error is null;

     -- Call the procedure
     rec_pkg_proce.rec_pr_proce_ingre_atenc(psNumeroLote   ,
                                            psTipoProducto ,
                                            psTipoAtencion ,
                                            lscodigocliente ,
                                            psCampanhaProceso ,
                                            psCampanhaReferencia ,
                                            psCodigoPais ,
                                            psCodigoUsuario ,
                                            psMensajeError  ,
                                            psTipoOperacion );

     --- Actualiza error en la GTT
     update REC_GTT_DETAL_INGRE_ATEN_MAS
     set des_Error = psMensajeError
     where cod_clie = lscodigocliente
     and des_error is null;

  end loop ;


   EXCEPTION
     WHEN OTHERS THEN

       ls_sqlerrm := substr(SQLERRM, 1, 250);

       raise_application_error(-20123, 'ERROR REC_PR_PROCE_INGRE_ATEN_MAS: ' || ls_sqlerrm);


 END REC_PR_PROCE_INGRE_ATEN_MAS;


/**************************************************************************
  Descripcion         : REC_PR_RECUP_PV_PREM
                        Proceso de Recuperacion de Postventas y Premios
  Fecha Creacion      : 29/12/2008
  Parametros Entrada:
      p_oidsoli       : Solicitud de Referencia
      p_tipo          : Motivo, Anulado, Faltante
      p_periodo       : Periodo Actual o Siguiente
      p_CodigoPais    : Codigo Pais
  Autor               : Jorge Yepez
***************************************************************************/
PROCEDURE REC_PR_RECUP_PV_PREM(p_oidsoli   NUMBER,
                               p_tipo VARCHAR2,
                               p_periodo NUMBER,
                               p_CodigoPais VARCHAR2
                                   )
   AS

CURSOR c_spv IS
select a.val_nume_soli nume_soli_ref,
       a.oid_soli_cabe oid_cabe_ref,
       b.oid_soli_cabe oid_cabe,
       a.fec_fact fec_fact_ref,
       c.cod_clie,
       c.oid_clie,
       (select sum(num_unid_dema_real*(val_prec_cata_unit_loca+val_prec_cont_unit_loca)) from ped_solic_posic where soca_oid_soli_cabe=b.oid_soli_cabe) tot_env,
       a.perd_oid_peri oid_peri_ref,
       a.ticl_oid_tipo_clie,
       a.sbti_oid_subt_clie,
       a.ztad_oid_terr_admi,
       a.cldi_oid_clie_dire,
       a.vepo_oid_valo_estr_geop,
       a.terr_oid_terr,
       a.zzon_oid_zona,
       a.tido_oid_tipo_docu,
       a.tdoc_oid_tipo_docu
from ped_solic_cabec a, ped_solic_cabec b, mae_clien c
where a.oid_soli_cabe=p_oidsoli
and a.oid_soli_cabe=b.soca_oid_soli_cabe
and a.clie_oid_clie=c.oid_clie
and (b.modu_oid_modu=15 or (b.modu_oid_modu=1 and b.ind_oc=0))
;

r_spv c_spv%ROWTYPE;


CURSOR c_prem IS
select a.val_nume_soli nume_soli_ref,
       a.oid_soli_cabe oid_cabe_ref,
       b.oid_soli_cabe oid_cabe,
       a.fec_fact fec_fact_ref,
       c.cod_clie,
       c.oid_clie,
       (select sum(num_unid_dema_real*(val_prec_cata_unit_loca+val_prec_cont_unit_loca)) from ped_solic_posic where soca_oid_soli_cabe=b.oid_soli_cabe) tot_env,
       a.perd_oid_peri oid_peri_ref,
       a.ticl_oid_tipo_clie,
       a.sbti_oid_subt_clie,
       a.ztad_oid_terr_admi,
       a.cldi_oid_clie_dire,
       a.vepo_oid_valo_estr_geop,
       a.terr_oid_terr,
       a.zzon_oid_zona,
       a.tido_oid_tipo_docu,
       a.tdoc_oid_tipo_docu,
       b.ictp_oid_tipo_prog,
       b.copa_oid_para_gene
from ped_solic_cabec a, ped_solic_cabec b, mae_clien c
where a.oid_soli_cabe=p_oidsoli
and a.oid_soli_cabe=b.soca_oid_soli_cabe
and a.clie_oid_clie=c.oid_clie
and (b.modu_oid_modu=15 or (b.modu_oid_modu=1 and b.ind_oc=0))
;

r_prem c_prem%ROWTYPE;

      rows                 NATURAL        := 1000;   -- Number of rows to process at a time
      i                    BINARY_INTEGER := 0;

      varCodOperSpv           varchar2(2);
      varTipoOperSpv          varchar2(2);
      varCodOperPrem           varchar2(2);
      varTipoOperPrem          varchar2(2);

      varCampProc          varchar2(6);
      varOidCampProc          NUMBER;


      varOidSoliCabe       ped_solic_cabec.oid_soli_cabe%TYPE;
      varFecDocuRefe       ped_solic_cabec.fec_fact%TYPE;

      varOidOper           rec_opera.OID_OPER%TYPE;
      varOidTipoOper       rec_tipos_opera.OID_TIPO_OPER%TYPE;
      varTspaEnvia         rec_opera.TSPA_OID_SOLI_CON_STOC%TYPE;
      varOCEnv             ped_clase_solic.IND_ORDE_COMP%TYPE;


      varOidCabeRecla      NUMBER;
      varOidOperaRecla     NUMBER;


      --varOidSoliCabe       NUMBER;

      varFormaPagoEnv      ped_tipo_solic_pais.FOPA_OID_FORM_PAGO%TYPE;
    	varFechaProgFactEnv  cra_crono.FEC_INIC%TYPE;
    	varClaseSolicEnv     ped_clase_solic.OID_CLAS_SOLI%TYPE;
    	varOidAlmacEnv       ped_tipo_solic_pais.ALMC_OID_ALMA%TYPE;
    	varTipoSoliCons      ped_tipo_solic_pais.TSOL_OID_TIPO_CONS%TYPE;
    	varTipoDocum2        ped_tipo_solic_pais.TIDO_OID_TIPO_DOCU%TYPE;
    	varSubac             ped_tipo_solic.SBAC_OID_SBAC%TYPE;
    	varSocie             ped_tipo_solic_pais.SOCI_OID_SOCI%TYPE;

      varNumeSoliSeq       number;
      varNumeSoli          VARCHAR2(10);
      varNumeAtenSeq       NUMBER;
      varNumeAten          NUMBER;

      varOidCabe           NUMBER;
      varNumePedi          NUMBER;
      lnseqsolioper        NUMBER;

    	varNumLote           rec_audit_info.NUM_LOTE%TYPE;
    	varPedOidSoliCabe    rec_audit_info.OID_SOLI_CABE%TYPE;
    	varRecOidCabeRecl    rec_audit_info.OID_CABE_RECL%TYPE;
    	varPedOidSoliPosi    rec_audit_info.OID_SOLI_POSI%TYPE;

      --excepciones
        ingreso_atencion_exception EXCEPTION;


  BEGIN
    varTipoOperSpv := '01';
    varTipoOperPrem := '01';

    select cod_peri into varCampProc
    from bas_ctrl_fact z where z.ind_camp_act=1 and z.sta_camp=0;

    varOidCampProc:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(varCampProc);

    if p_Tipo = '1' then
  				SELECT COD_OPER_ENV_MAT_NRM_CON_REF INTO varCodOperSpv FROM REC_PARAM_INGRE_ATENC;
  	else
  		if p_Tipo = '2' then
      			SELECT COD_OPER_ENV_PRE_NRM_ESP INTO varCodOperSpv FROM REC_PARAM_INGRE_ATENC;
  		else
  	    		SELECT COD_OPER_ENV_PRE_EXP_ESP INTO varCodOperSpv FROM REC_PARAM_INGRE_ATENC;
      end if;
  	end if;


---  Se empiezan con los postventas

   begin

    select a.OID_OPER,
             b.OID_TIPO_OPER,
             a.TSPA_OID_SOLI_CON_STOC,
             h.IND_ORDE_COMP OC_ENV,
             d.FOPA_OID_FORM_PAGO,
             --b.FEC_INIC,
             h.OID_CLAS_SOLI,
             d.ALMC_OID_ALMA,
             d.TSOL_OID_TIPO_CONS,
             d.TIDO_OID_TIPO_DOCU,
             f.SBAC_OID_SBAC,
             d.SOCI_OID_SOCI
       into  varOidOper,
           varOidTipoOper,
           varTspaEnvia,
           varOCEnv,
           varFormaPagoEnv,
    			 varClaseSolicEnv,
    			 varOidAlmacEnv,
    			 varTipoSoliCons,
    			 varTipoDocum2,
    			 varSubac,
  		     varSocie
        from rec_opera           a,
             rec_tipos_opera     b,
             ped_tipo_solic_pais d,
             ped_tipo_solic      f,
             ped_clase_solic     h
       where a.OID_OPER = b.ROPE_OID_OPER
         and a.COD_OPER = varCodOperSpv
         and b.VAL_TIPO_OPER = varTipoOperSpv -----'01'
         and a.TSPA_OID_SOLI_CON_STOC = d.OID_TIPO_SOLI_PAIS(+)
         and d.TSOL_OID_TIPO_SOLI = f.OID_TIPO_SOLI(+)
         and f.CLSO_OID_CLAS_SOLI = h.OID_CLAS_SOLI(+);
   exception
    when no_data_found then
      --psMensajeError:='No se encuentra codigo operacion';
      raise ingreso_atencion_exception;
      --return;
   end;



    OPEN c_spv;
    LOOP
    FETCH c_spv INTO r_spv;
    EXIT WHEN c_spv%NOTFOUND;
        BEGIN


    select rec_care_seq.nextval into varOidCabeRecla from dual;
  	select rec_opre_seq.nextval into varOidOperaRecla from dual;


      ---- SQA Reemplaza Generacion de secuencial
        varNumeAtenSeq:= sto_pkg_gener.sto_fn_resrv_secue_nsoli(p_CodigoPais,
                                                                  'REC034',
                                                                  '_',
                                                                  1);
        varNumeAten := to_char(SYSDATE,'YY') || lpad(varNumeAtenSeq,8,'0') + 1;

        varRecOidCabeRecl := varOidCabeRecla;

        INSERT INTO REC_CABEC_RECLA
          (OID_CABE_RECL,
           NUM_ATEN,
           NUM_RECL,
           FEC_DOCU_REFE,
           NUM_TOTA_ENVI,
           NUM_TOTA_DEVU,
           IMP_SALD_PAGA,
           COD_USUA_INGR,
           FEC_INGR,
           PAIS_OID_PAIS,
           SOCA_OID_SOLI_CABE,
           PERD_OID_PERI_DOCU_REFE,
           CLIE_OID_CLIE,
           MRDB_OID_MOTI_RECH,
           PERD_OID_PERI_RECL,
           MRDB_OID_MOTI_DESB,
           ZTAD_OID_TERR_ADMI,
           SBTI_OID_SUBT_CLIE,
           TIDO_OID_TIPO_DOCU,
           ESRE_OID_ESTA_RECL,
           MOBL_OID_MOTI_BLOQ,
           TIIN_OID_TIPO_INGR,
           TICL_OID_TIPO_CLIE,
           FEC_ULTI_ACTU,
           NUM_ATEN_INTE)
        VALUES
          (varOidCabeRecla,
           varNumeAten,
           r_spv.nume_soli_ref,
           r_spv.fec_fact_ref,
           r_spv.tot_env,
           0,
           r_spv.tot_env,
           r_spv.cod_clie,
           sysdate,
           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_CodigoPais),
           r_spv.oid_cabe_ref,
           r_spv.oid_peri_ref,
           r_spv.oid_clie,
           NULL,
           varOidCampProc,
           NULL,
           r_spv.ztad_oid_terr_admi,
           r_spv.sbti_oid_subt_clie,
           r_spv.tido_oid_tipo_docu,
           6,
           NULL,
           2,
           r_spv.ticl_oid_tipo_clie,
           sysdate,
           NULL);


           INSERT INTO REC_OPERA_RECLA
              (OID_OPER_RECL,
               NUM_SECU_OPER,
               IMP_MONT_PERD,
               VAL_PORC_PERD,
               CARE_OID_CABE_RECL,
               TIBL_OID_TIPO_BLOQ,
               MOBL_OID_MOTI_BLOQ,
               INEM_OID_INDI_ENTR_MERC,
               ASPE_OID_ASUM_PERD,
               PROD_OID_PROD,
               CLIE_OID_CLIE,
               MRDB_OID_MOTI_RECH_DESB,
               MRDB_OID_MOTI_DESB,
               PPER_OID_PREC_PERD,
               ESOP_OID_ESTA_OPER,
               CLIE_OID_RESP_PERD,
               TPOS_OID_TIPO_POSI,
               SOCA_OID_SOLI_CABE,
               PERD_OID_PERI_RECL,
               TSPA_OID_TIPO_SOLI_PAIS,
               TIOP_OID_TIPO_OPER,
               IND_ATEN,
               FEC_FACT,
               FEC_ULTI_ACTU)
            VALUES
              (varOidOperaRecla,
               1,
               0,
               NULL,
               varOidCabeRecla,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               2,
               NULL,
               NULL,
               r_spv.oid_cabe_ref,
               varOidCampProc,
               NULL,
               varOidTipoOper,
               1,
               NULL,
               sysdate);

                 INSERT INTO REC_LINEA_OPERA_RECLA
                  (TSPA_OID_TIPO_SOLI_PAIS,
                   OID_LINE_OPER_RECL,
                   NUM_LINE,
                   NUM_UNID_RECL,
                   VAL_PREC,
                   IMP_CARG,
                   IMP_ABON,
                   NUM_UNID_DEVU,
                   IND_ESTA,
                   IMP_MONT_PERD,
                   IND_ATEN,
                   OPRE_OID_OPER_RECL,
                   TOFE_OID_TIPO_OFER,
                   PPER_OID_PREC_PERD,
                   TPOS_OID_TIPO_POSI,
                   TIMO_OID_TIPO_MOVI,
                   PROD_OID_PROD,
                   MAFA_OID_MATR_FACT,
                   MODV_OID_MOTI_DEVO,
                   MRDB_OID_MOTI_RECH_DESB,
                   SOPO_OID_SOLI_POSI,
                   COPA_OID_PARA_GRAL,
                   PANP_OID_PARA_NIVE_PREM,
                   LOPA_OID_LOTE_PREM_ARTI,
                   VAL_PREC_CONT)
                   select
                   varTspaEnvia,
                   rec_lior_seq.nextval,
                   x.cod_posi,
                   x.num_unid_dema_real,
                   x.val_prec_cata_unit_loca,
                   x.val_prec_cata_tota_loca,
                   0,
                   0,
                   'E',
                   NULL,
                   1,
                   varOidOperaRecla,
                   y.tofe_OID_TIPO_OFER,
                   NULL,
                   NULL,
                   1,
                   x.prod_OID_PROD,
                   z.OID_MATR_FACT,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   NULL,
                   x.val_prec_cont_unit_loca
                   from ped_solic_posic x, pre_ofert_detal y, pre_matri_factu z
                   where soca_oid_soli_cabe=r_spv.oid_cabe
                   and x.ofde_oid_deta_ofer=y.oid_deta_ofer
                   and y.oid_deta_ofer=z.ofde_oid_deta_ofer
                   ;



    select ped_soca_seq.nextval into varOidCabe from dual;



      --- SQA nueva forma de generar el sequencial
      varNumeSoliSeq := sto_pkg_gener.sto_fn_resrv_secue_nsoli(p_codigopais,
                                                                'PED001',
                                                                '000',
                                                                1);

      varNumeSoli := to_char(SYSDATE,'YY') || lpad(varNumeSoliSeq,8,'0') + 1;


      varPedOidSoliCabe := varOidCabe;

      INSERT INTO PED_SOLIC_CABEC
            (OID_SOLI_CABE,
             FEC_PROG_FACT,
             TSPA_OID_TIPO_SOLI_PAIS,
             TIDS_OID_TIPO_DESP,
             ALMC_OID_ALMA,
             MODU_OID_MODU,
             TICL_OID_TIPO_CLIE,
             PERD_OID_PERI,
             CLIE_OID_CLIE,
             CLIE_OID_CLIE_RECE_FACT,
             CLIE_OID_CLIE_PAGA,
             CLIE_OID_CLIE_DEST,
             CLDI_OID_CLIE_DIRE,
             TDOC_OID_TIPO_DOCU,
             SOCI_OID_SOCI,
             SBAC_OID_SBAC,
             TERR_OID_TERR,
             ZZON_OID_ZONA,
             VAL_NUME_SOLI,
             VAL_USUA,
             VAL_TASA_IMPU,
             FEC_CRON,
             IND_PERM_UNIO_SOL,
             NUM_DOCU_ORIG,
             VAL_BASE_FLET_LOCA,
             VAL_IMPO_FLET_LOCA,
             VAL_IMPO_FLET_TOTA_LOCA,
             VAL_IMPO_FLET_SIN_IMPU_TOTA,
             VAL_RECA_FLET_LOCA,
             VAL_OTRO_RECA_LOCA,
             VAL_TOTA_PAGA_LOCA,
             VAL_PREC_CATA_TOTA_LOCA,
             VAL_PREC_CATA_SIN_IMPU_TOTA,
             VAL_PREC_FACT_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_LOCA,
             VAL_IMPO_DESC_1_TOTA_LOCA,
             VAL_IMPO_DESC_1_TOTA_DOCU,
             VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
             VAL_IMPO_DESC_3_TOTA_DOCU,
             VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
             VAL_IMPO_DESC_TOTA_LOCA,
             VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
             VAL_IMPO_REDO_LOCA,
             VAL_BASE_FLET_DOCU,
             VAL_IMPO_FLET_DOCU,
             VAL_IMPO_DESC_TOTA_DOCU,
             VAL_IMPO_FLET_SIN_IMPU_DOCU,
             VAL_RECA_FLET_DOCU,
             VAL_OTRO_RECA_DOCU,
             VAL_TOTA_FLET_DOCU,
             VAL_IMPO_FLET_TOTA_DOCU,
             VAL_TOTA_FLET_LOCA,
             VAL_TOTA_PAGA_DOCU,
             VAL_PREC_CATA_TOTA_DOCU,
             VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
             VAL_PREC_CONT_TOTA_LOCA,
             VAL_PREC_CONT_SIN_IMPU_TOTA,
             VAL_PREC_CONT_SIN_IMPU_TOTA_1,
             VAL_PREC_FACT_TOTA_DOCU,
             VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
             VAL_PREC_NETO_TOTA_DOCU,
             VAL_PREC_NETO_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_DOCU,
             VAL_IMPO_REDO_DOCU,
             VAL_IMPO_REDO_CONS_LOCA,
             VAL_IMPO_REDO_CONS_DOCU,
             IND_OC,
             IND_PEDI_PRUE,
             IND_TS_NO_CONSO,
             NUM_PREM,
             VAL_IMPO_DESC_3_TOTA_LOCA,
             VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
             PAIS_OID_PAIS,
             TIDO_OID_TIPO_DOCU,
             VEPO_OID_VALO_ESTR_GEOP,
             ESSO_OID_ESTA_SOLI,
             COPA_OID_PARA_GENE,
             GRPR_OID_GRUP_PROC,
             SBTI_OID_SUBT_CLIE,
             TSPA_OID_TIPO_SOLI_PAIS_CONS,
             FOPA_OID_FORM_PAGO,
             CLSO_OID_CLAS_SOLI,
             ZTAD_OID_TERR_ADMI,
             OPER_OID_OPER,
             PROC_OID_PROC,
             SOCA_OID_DOCU_REFE,
             IND_INTE_LARI_GENE,
             ICTP_OID_TIPO_PROG,
             VAL_TIPO_CAMB
             )
          VALUES
            (varOidCabe,
             (select fec_fina from cra_perio where oid_peri=varOidCampProc),
             varTspaEnvia,
             1,
             varOidAlmacEnv,
             15,
             r_spv.ticl_oid_tipo_clie,
             varOidCampProc,
             r_spv.oid_clie,
             r_spv.oid_clie,
             r_spv.oid_clie,
             r_spv.oid_clie,
             r_spv.cldi_oid_clie_dire,
             r_spv.tdoc_oid_tipo_docu,
             varSocie,
             varSubac,
             r_spv.terr_oid_terr,
             r_spv.zzon_oid_zona,
             varNumeSoli,
             'SISTEMA',
             0,
             trunc(sysdate),
             1,
             r_spv.nume_soli_ref,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             1,
             NULL,
             0,
             0,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_CodigoPais),
             r_spv.tido_oid_tipo_docu,
             r_spv.vepo_oid_valo_estr_geop,
             1,
             NULL,
             1,
             r_spv.sbti_oid_subt_clie,
             varTipoSoliCons,
             nvl(nvl(varFormaPagoEnv,(select fopa_oid_form_pago from mae_clien where oid_clie=r_spv.oid_clie)),(select fopa_oid_form_pago from seg_pais where oid_pais=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_CodigoPais))),
             --varFormaPagoEnv,
             varClaseSolicEnv,
             r_spv.ztad_oid_terr_admi,
             21,
             1,
             r_spv.oid_cabe_ref,
             0,
             --NULL--(select OID_TIPO_CONC from REC_GTT_DETAL_INGRE_ATENC where rownum=1)--,< OID_TIPO_CONCU >
             (select OID_TIPO_CONC from REC_GTT_DETAL_INGRE_ATENC where rownum=1),--,< OID_TIPO_CONCU >
             nvl((select val_tipo_camb from pre_matri_factu_cabec where perd_oid_peri=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(varCampProc)),1)
             );

             SELECT rec_soop_seq.NEXTVAL
                INTO lnseqsolioper
                FROM dual;

              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 varOidOperaRecla,
                 varOidCabe,
                 varTspaEnvia);

                 INSERT INTO PED_SOLIC_POSIC
                    (OID_SOLI_POSI,
                     COD_POSI,
                     NUM_UNID_DEMA,
                     NUM_UNID_POR_ATEN,
                     VAL_TASA_IMPU,
                     SOCA_OID_SOLI_CABE,
                     TPOS_OID_TIPO_POSI,
                     PROD_OID_PROD,
                     VAL_PREC_CATA_UNIT_LOCA,
                     VAL_PREC_CONT_UNIT_LOCA,
                     VAL_PREC_CATA_UNIT_DOCU,
                     VAL_PREC_CONTA_UNIT_DOCU,
                     VAL_PREC_FACT_UNIT_LOCA,
                     VAL_PREC_FACT_UNIT_DOCU,
                     VAL_PREC_SIN_IMPU_UNIT_LOCA,
                     VAL_PREC_SIN_IMPU_UNIT_DOCU,
                     VAL_PREC_SIN_IMPU_TOTA_DOCU,
                     VAL_IMPO_DESC_UNIT_LOCA,
                     VAL_IMPO_DESC_UNIT_DOCU,
                     VAL_PREC_NETO_UNIT_LOCA,
                     VAL_PREC_NETO_TOTA_DOCU,
                     VAL_PREC_NETO_UNIT_DOCU,
                     VAL_PREC_TOTA_TOTA_LOCA,
                     VAL_PREC_TOTA_TOTA_DOCU,
                     VAL_IMPO_IMPU_UNIT_LOCA,
                     VAL_IMPO_IMPU_UNIT_DOCU,
                     VAL_IMPO_DESC_TOTA_DOCU,
                     VAL_IMPO_IMPU_TOTA_LOCA,
                     VAL_IMPO_IMPU_TOTA_DOCU,
                     VAL_IMPO_DESC_TOTA_LOCA,
                     VAL_PREC_TOTA_UNIT_LOCA,
                     VAL_PREC_TOTA_UNIT_DOCU,
                     VAL_PREC_CONT_TOTA_LOCA,
                     VAL_PREC_CATA_TOTA_LOCA,
                     VAL_PREC_CATA_TOTA_DOCU,
                     VAL_PREC_CONT_TOTA_DOCU,
                     VAL_PORC_DESC,
                     VAL_PREC_CATA_TOTA_LOCA_UNID,
                     NUM_UNID_DEMA_REAL,
                     NUM_UNID_COMPR,
                     VAL_CODI_VENT_FICT,
                     VAL_PREC_FACT_TOTA_LOCA,
                     VAL_PREC_FACT_TOTA_DOCU,
                     VAL_PREC_SIN_IMPU_TOTA_LOCA,
                     VAL_PREC_NETO_TOTA_LOCA,
                     OFDE_OID_DETA_OFER,
                     ESPO_OID_ESTA_POSI,
                     STPO_OID_SUBT_POSI,
                     VAL_CODI_VENT,
                     FOPA_OID_FORM_PAGO)
                  select
                    ped_sopo_seq.nextval,   ----  ped_sopo_seq.nextval,
                     x.cod_posi,
                     x.num_unid_dema_real,
                     x.num_unid_dema_real,
                     0,
                     r_spv.oid_cabe,
                     10,
                     x.prod_OID_PROD,
                     x.val_prec_cata_unit_loca,
                     x.val_prec_cont_unit_loca,
                     x.val_prec_cata_unit_docu,
                     x.val_prec_conta_unit_docu,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     NULL,
                     0,
                     x.num_unid_dema_real,
                     0,
                     x.val_codi_vent_fict,
                     0,
                     0,
                     0,
                     0,
                     x.ofde_OID_DETA_OFER,
                     4,
                     14,
                     x.val_codi_vent,
                     x.fopa_oid_form_pago
                     from ped_solic_posic x
                     where soca_oid_soli_cabe=r_spv.oid_cabe
                     ;


        END;
    END LOOP;
    CLOSE c_spv;


---  Se empiezan con los premios

   begin

    select a.OID_OPER,
             b.OID_TIPO_OPER,
             a.TSPA_OID_SOLI_CON_STOC,
             h.IND_ORDE_COMP OC_ENV,
             d.FOPA_OID_FORM_PAGO,
             --b.FEC_INIC,
             h.OID_CLAS_SOLI,
             d.ALMC_OID_ALMA,
             d.TSOL_OID_TIPO_CONS,
             d.TIDO_OID_TIPO_DOCU,
             f.SBAC_OID_SBAC,
             d.SOCI_OID_SOCI
       into  varOidOper,
           varOidTipoOper,
           varTspaEnvia,
           varOCEnv,
           varFormaPagoEnv,
    			 varClaseSolicEnv,
    			 varOidAlmacEnv,
    			 varTipoSoliCons,
    			 varTipoDocum2,
    			 varSubac,
  		     varSocie
        from rec_opera           a,
             rec_tipos_opera     b,
             ped_tipo_solic_pais d,
             ped_tipo_solic      f,
             ped_clase_solic     h
       where a.OID_OPER = b.ROPE_OID_OPER
         and a.COD_OPER = varCodOperPrem
         and b.VAL_TIPO_OPER = varTipoOperPrem -----'01'
         and a.TSPA_OID_SOLI_CON_STOC = d.OID_TIPO_SOLI_PAIS(+)
         and d.TSOL_OID_TIPO_SOLI = f.OID_TIPO_SOLI(+)
         and f.CLSO_OID_CLAS_SOLI = h.OID_CLAS_SOLI(+);
   exception
    when no_data_found then
      --psMensajeError:='No se encuentra codigo operacion';
      raise ingreso_atencion_exception;
      --return;
   end;



    OPEN c_prem;
    LOOP
    FETCH c_prem INTO r_prem;
    EXIT WHEN c_prem%NOTFOUND;
        BEGIN


    select rec_care_seq.nextval into varOidCabeRecla from dual;
  	select rec_opre_seq.nextval into varOidOperaRecla from dual;


      ---- SQA Reemplaza Generacion de secuencial
        varNumeAtenSeq:= sto_pkg_gener.sto_fn_resrv_secue_nsoli(p_CodigoPais,
                                                                  'REC034',
                                                                  '_',
                                                                  1);
        varNumeAten := to_char(SYSDATE,'YY') || lpad(varNumeAtenSeq,8,'0') + 1;

        varRecOidCabeRecl := varOidCabeRecla;

        INSERT INTO REC_CABEC_RECLA
          (OID_CABE_RECL,
           NUM_ATEN,
           NUM_RECL,
           FEC_DOCU_REFE,
           NUM_TOTA_ENVI,
           NUM_TOTA_DEVU,
           IMP_SALD_PAGA,
           COD_USUA_INGR,
           FEC_INGR,
           PAIS_OID_PAIS,
           SOCA_OID_SOLI_CABE,
           PERD_OID_PERI_DOCU_REFE,
           CLIE_OID_CLIE,
           MRDB_OID_MOTI_RECH,
           PERD_OID_PERI_RECL,
           MRDB_OID_MOTI_DESB,
           ZTAD_OID_TERR_ADMI,
           SBTI_OID_SUBT_CLIE,
           TIDO_OID_TIPO_DOCU,
           ESRE_OID_ESTA_RECL,
           MOBL_OID_MOTI_BLOQ,
           TIIN_OID_TIPO_INGR,
           TICL_OID_TIPO_CLIE,
           FEC_ULTI_ACTU,
           NUM_ATEN_INTE)
        VALUES
          (varOidCabeRecla,
           varNumeAten,
           r_prem.nume_soli_ref,
           r_prem.fec_fact_ref,
           r_prem.tot_env,
           0,
           r_prem.tot_env,
           r_prem.cod_clie,
           sysdate,
           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_CodigoPais),
           r_prem.oid_cabe_ref,
           r_prem.oid_peri_ref,
           r_prem.oid_clie,
           NULL,
           varOidCampProc,
           NULL,
           r_prem.ztad_oid_terr_admi,
           r_prem.sbti_oid_subt_clie,
           r_prem.tido_oid_tipo_docu,
           6,
           NULL,
           2,
           r_prem.ticl_oid_tipo_clie,
           sysdate,
           NULL);


           INSERT INTO REC_OPERA_RECLA
              (OID_OPER_RECL,
               NUM_SECU_OPER,
               IMP_MONT_PERD,
               VAL_PORC_PERD,
               CARE_OID_CABE_RECL,
               TIBL_OID_TIPO_BLOQ,
               MOBL_OID_MOTI_BLOQ,
               INEM_OID_INDI_ENTR_MERC,
               ASPE_OID_ASUM_PERD,
               PROD_OID_PROD,
               CLIE_OID_CLIE,
               MRDB_OID_MOTI_RECH_DESB,
               MRDB_OID_MOTI_DESB,
               PPER_OID_PREC_PERD,
               ESOP_OID_ESTA_OPER,
               CLIE_OID_RESP_PERD,
               TPOS_OID_TIPO_POSI,
               SOCA_OID_SOLI_CABE,
               PERD_OID_PERI_RECL,
               TSPA_OID_TIPO_SOLI_PAIS,
               TIOP_OID_TIPO_OPER,
               IND_ATEN,
               FEC_FACT,
               FEC_ULTI_ACTU)
            VALUES
              (varOidOperaRecla,
               1,
               0,
               NULL,
               varOidCabeRecla,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               NULL,
               2,
               NULL,
               NULL,
               r_prem.oid_cabe_ref,
               varOidCampProc,
               NULL,
               varOidTipoOper,
               1,
               NULL,
               sysdate);

                 INSERT INTO REC_LINEA_OPERA_RECLA
                  (TSPA_OID_TIPO_SOLI_PAIS,
                   OID_LINE_OPER_RECL,
                   NUM_LINE,
                   NUM_UNID_RECL,
                   VAL_PREC,
                   IMP_CARG,
                   IMP_ABON,
                   NUM_UNID_DEVU,
                   IND_ESTA,
                   IMP_MONT_PERD,
                   IND_ATEN,
                   OPRE_OID_OPER_RECL,
                   TOFE_OID_TIPO_OFER,
                   PPER_OID_PREC_PERD,
                   TPOS_OID_TIPO_POSI,
                   TIMO_OID_TIPO_MOVI,
                   PROD_OID_PROD,
                   MAFA_OID_MATR_FACT,
                   MODV_OID_MOTI_DEVO,
                   MRDB_OID_MOTI_RECH_DESB,
                   SOPO_OID_SOLI_POSI,
                   COPA_OID_PARA_GRAL,
                   PANP_OID_PARA_NIVE_PREM,
                   LOPA_OID_LOTE_PREM_ARTI,
                   VAL_PREC_CONT)
                   select
                   varTspaEnvia,
                   rec_lior_seq.nextval,
                   x.cod_posi,
                   x.num_unid_dema_real,
                   x.val_prec_cata_unit_loca,
                   x.val_prec_cata_tota_loca,
                   0,
                   0,
                   'E',
                   NULL,
                   1,
                   varOidOperaRecla,
                   y.tofe_OID_TIPO_OFER,
                   NULL,
                   NULL,
                   1,
                   x.prod_OID_PROD,
                   z.OID_MATR_FACT,
                   NULL,
                   NULL,
                   NULL,
                   r_prem.copa_oid_para_gene,
                   NULL,
                   NULL,
                   x.val_prec_cont_unit_loca
                   from ped_solic_posic x, pre_ofert_detal y, pre_matri_factu z
                   where soca_oid_soli_cabe=r_prem.oid_cabe
                   and x.ofde_oid_deta_ofer=y.oid_deta_ofer
                   and y.oid_deta_ofer=z.ofde_oid_deta_ofer
                   ;



    select ped_soca_seq.nextval into varOidCabe from dual;



      --- SQA nueva forma de generar el sequencial
      varNumeSoliSeq := sto_pkg_gener.sto_fn_resrv_secue_nsoli(p_codigopais,
                                                                'PED001',
                                                                '000',
                                                                1);

      varNumeSoli := to_char(SYSDATE,'YY') || lpad(varNumeSoliSeq,8,'0') + 1;


      varPedOidSoliCabe := varOidCabe;

      INSERT INTO PED_SOLIC_CABEC
            (OID_SOLI_CABE,
             FEC_PROG_FACT,
             TSPA_OID_TIPO_SOLI_PAIS,
             TIDS_OID_TIPO_DESP,
             ALMC_OID_ALMA,
             MODU_OID_MODU,
             TICL_OID_TIPO_CLIE,
             PERD_OID_PERI,
             CLIE_OID_CLIE,
             CLIE_OID_CLIE_RECE_FACT,
             CLIE_OID_CLIE_PAGA,
             CLIE_OID_CLIE_DEST,
             CLDI_OID_CLIE_DIRE,
             TDOC_OID_TIPO_DOCU,
             SOCI_OID_SOCI,
             SBAC_OID_SBAC,
             TERR_OID_TERR,
             ZZON_OID_ZONA,
             VAL_NUME_SOLI,
             VAL_USUA,
             VAL_TASA_IMPU,
             FEC_CRON,
             IND_PERM_UNIO_SOL,
             NUM_DOCU_ORIG,
             VAL_BASE_FLET_LOCA,
             VAL_IMPO_FLET_LOCA,
             VAL_IMPO_FLET_TOTA_LOCA,
             VAL_IMPO_FLET_SIN_IMPU_TOTA,
             VAL_RECA_FLET_LOCA,
             VAL_OTRO_RECA_LOCA,
             VAL_TOTA_PAGA_LOCA,
             VAL_PREC_CATA_TOTA_LOCA,
             VAL_PREC_CATA_SIN_IMPU_TOTA,
             VAL_PREC_FACT_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_LOCA,
             VAL_IMPO_DESC_1_TOTA_LOCA,
             VAL_IMPO_DESC_1_TOTA_DOCU,
             VAL_IMPO_DESC_1_SIN_IMPU_TOTA,
             VAL_IMPO_DESC_3_TOTA_DOCU,
             VAL_IMPO_DESC_3_SIN_IMPU_TOTA,
             VAL_IMPO_DESC_TOTA_LOCA,
             VAL_IMPO_DTO_1_SIN_IMP_TOT_LOC,
             VAL_IMPO_REDO_LOCA,
             VAL_BASE_FLET_DOCU,
             VAL_IMPO_FLET_DOCU,
             VAL_IMPO_DESC_TOTA_DOCU,
             VAL_IMPO_FLET_SIN_IMPU_DOCU,
             VAL_RECA_FLET_DOCU,
             VAL_OTRO_RECA_DOCU,
             VAL_TOTA_FLET_DOCU,
             VAL_IMPO_FLET_TOTA_DOCU,
             VAL_TOTA_FLET_LOCA,
             VAL_TOTA_PAGA_DOCU,
             VAL_PREC_CATA_TOTA_DOCU,
             VAL_PREC_CATA_SIN_IMPU_TOTA_DO,
             VAL_PREC_CONT_TOTA_LOCA,
             VAL_PREC_CONT_SIN_IMPU_TOTA,
             VAL_PREC_CONT_SIN_IMPU_TOTA_1,
             VAL_PREC_FACT_TOTA_DOCU,
             VAL_PREC_CATA_TOTA_LOC_UNI_DEM,
             VAL_PREC_NETO_TOTA_DOCU,
             VAL_PREC_NETO_TOTA_LOCA,
             VAL_IMPO_IMPU_TOTA_DOCU,
             VAL_IMPO_REDO_DOCU,
             VAL_IMPO_REDO_CONS_LOCA,
             VAL_IMPO_REDO_CONS_DOCU,
             IND_OC,
             IND_PEDI_PRUE,
             IND_TS_NO_CONSO,
             NUM_PREM,
             VAL_IMPO_DESC_3_TOTA_LOCA,
             VAL_IMPO_DTO_3_SIN_IMP_TOT_LOC,
             PAIS_OID_PAIS,
             TIDO_OID_TIPO_DOCU,
             VEPO_OID_VALO_ESTR_GEOP,
             ESSO_OID_ESTA_SOLI,
             COPA_OID_PARA_GENE,
             GRPR_OID_GRUP_PROC,
             SBTI_OID_SUBT_CLIE,
             TSPA_OID_TIPO_SOLI_PAIS_CONS,
             FOPA_OID_FORM_PAGO,
             CLSO_OID_CLAS_SOLI,
             ZTAD_OID_TERR_ADMI,
             OPER_OID_OPER,
             PROC_OID_PROC,
             SOCA_OID_DOCU_REFE,
             IND_INTE_LARI_GENE,
             ICTP_OID_TIPO_PROG,
             VAL_TIPO_CAMB
             )
          VALUES
            (varOidCabe,
             (select fec_fina from cra_perio where oid_peri=varOidCampProc),
             varTspaEnvia,
             1,
             varOidAlmacEnv,
             15,
             r_prem.ticl_oid_tipo_clie,
             varOidCampProc,
             r_prem.oid_clie,
             r_prem.oid_clie,
             r_prem.oid_clie,
             r_prem.oid_clie,
             r_prem.cldi_oid_clie_dire,
             r_prem.tdoc_oid_tipo_docu,
             varSocie,
             varSubac,
             r_prem.terr_oid_terr,
             r_prem.zzon_oid_zona,
             varNumeSoli,
             'SISTEMA',
             0,
             trunc(sysdate),
             1,
             r_prem.nume_soli_ref,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             1,
             NULL,
             0,
             0,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_CodigoPais),
             r_prem.tido_oid_tipo_docu,
             r_prem.vepo_oid_valo_estr_geop,
             1,
             r_prem.copa_oid_para_gene,
             1,
             r_prem.sbti_oid_subt_clie,
             varTipoSoliCons,
             nvl(nvl(varFormaPagoEnv,(select fopa_oid_form_pago from mae_clien where oid_clie=r_prem.oid_clie)),(select fopa_oid_form_pago from seg_pais where oid_pais=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_CodigoPais))),
             --varFormaPagoEnv,
             varClaseSolicEnv,
             r_prem.ztad_oid_terr_admi,
             21,
             1,
             r_prem.oid_cabe_ref,
             0,
             --NULL--(select OID_TIPO_CONC from REC_GTT_DETAL_INGRE_ATENC where rownum=1)--,< OID_TIPO_CONCU >
             r_prem.ictp_oid_tipo_prog,
             nvl((select val_tipo_camb from pre_matri_factu_cabec where perd_oid_peri=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(varCampProc)),1)
             );

             SELECT rec_soop_seq.NEXTVAL
                INTO lnseqsolioper
                FROM dual;

              INSERT INTO rec_solic_opera
              VALUES
                (lnseqsolioper,
                 NULL,
                 varOidOperaRecla,
                 varOidCabe,
                 varTspaEnvia);


                 INSERT INTO PED_SOLIC_POSIC
                    (OID_SOLI_POSI,
                     COD_POSI,
                     NUM_UNID_DEMA,
                     NUM_UNID_POR_ATEN,
                     VAL_TASA_IMPU,
                     SOCA_OID_SOLI_CABE,
                     TPOS_OID_TIPO_POSI,
                     PROD_OID_PROD,
                     VAL_PREC_CATA_UNIT_LOCA,
                     VAL_PREC_CONT_UNIT_LOCA,
                     VAL_PREC_CATA_UNIT_DOCU,
                     VAL_PREC_CONTA_UNIT_DOCU,
                     VAL_PREC_FACT_UNIT_LOCA,
                     VAL_PREC_FACT_UNIT_DOCU,
                     VAL_PREC_SIN_IMPU_UNIT_LOCA,
                     VAL_PREC_SIN_IMPU_UNIT_DOCU,
                     VAL_PREC_SIN_IMPU_TOTA_DOCU,
                     VAL_IMPO_DESC_UNIT_LOCA,
                     VAL_IMPO_DESC_UNIT_DOCU,
                     VAL_PREC_NETO_UNIT_LOCA,
                     VAL_PREC_NETO_TOTA_DOCU,
                     VAL_PREC_NETO_UNIT_DOCU,
                     VAL_PREC_TOTA_TOTA_LOCA,
                     VAL_PREC_TOTA_TOTA_DOCU,
                     VAL_IMPO_IMPU_UNIT_LOCA,
                     VAL_IMPO_IMPU_UNIT_DOCU,
                     VAL_IMPO_DESC_TOTA_DOCU,
                     VAL_IMPO_IMPU_TOTA_LOCA,
                     VAL_IMPO_IMPU_TOTA_DOCU,
                     VAL_IMPO_DESC_TOTA_LOCA,
                     VAL_PREC_TOTA_UNIT_LOCA,
                     VAL_PREC_TOTA_UNIT_DOCU,
                     VAL_PREC_CONT_TOTA_LOCA,
                     VAL_PREC_CATA_TOTA_LOCA,
                     VAL_PREC_CATA_TOTA_DOCU,
                     VAL_PREC_CONT_TOTA_DOCU,
                     VAL_PORC_DESC,
                     VAL_PREC_CATA_TOTA_LOCA_UNID,
                     NUM_UNID_DEMA_REAL,
                     NUM_UNID_COMPR,
                     VAL_CODI_VENT_FICT,
                     VAL_PREC_FACT_TOTA_LOCA,
                     VAL_PREC_FACT_TOTA_DOCU,
                     VAL_PREC_SIN_IMPU_TOTA_LOCA,
                     VAL_PREC_NETO_TOTA_LOCA,
                     OFDE_OID_DETA_OFER,
                     ESPO_OID_ESTA_POSI,
                     STPO_OID_SUBT_POSI,
                     VAL_CODI_VENT,
                     FOPA_OID_FORM_PAGO)
                  select
                    ped_sopo_seq.nextval,   ----  ped_sopo_seq.nextval,
                     x.cod_posi,
                     x.num_unid_dema_real,
                     x.num_unid_dema_real,
                     0,
                     r_prem.oid_cabe,
                     10,
                     x.prod_OID_PROD,
                     x.val_prec_cata_unit_loca,
                     x.val_prec_cont_unit_loca,
                     x.val_prec_cata_unit_docu,
                     x.val_prec_conta_unit_docu,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     NULL,
                     0,
                     x.num_unid_dema_real,
                     0,
                     x.val_codi_vent_fict,
                     0,
                     0,
                     0,
                     0,
                     x.ofde_OID_DETA_OFER,
                     4,
                     14,
                     x.val_codi_vent,
                     x.fopa_oid_form_pago
                     from ped_solic_posic x
                     where soca_oid_soli_cabe=r_spv.oid_cabe
                     ;


        END;
    END LOOP;
    CLOSE c_prem;




    EXCEPTION
        WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;

        RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_RECUP_PV_PREM: '||ls_sqlerrm);
  END REC_PR_RECUP_PV_PREM;

/**************************************************************************
  Descripcion         : Funcion que devuelve el codigo del chequeo
                        realizado
  Fecha Creacion      : 22/03/2011
  Parametros Entrada:
      psNumPedido      : numero pedido

  Autor               : Jose Luis Rodriguez
***************************************************************************/
 FUNCTION REC_FN_SOLIC_PEDID_HIPER(psOidPedido  VARCHAR2) RETURN VARCHAR2
 IS
   lsresult  gen_i18n_sicc_comun.val_i18n%TYPE;
   lnOidPedido  NUMBER;

 BEGIN
   lnOidPedido := TO_NUMBER(psOidPedido);

   BEGIN

      select gen_pkg_gener.GEN_FN_DEVUE_DESCR_TIPOS_SOLIC(psc.tspa_OID_TIPO_SOLI_PAIS)
      into lsresult
      from
      ( select X.TSPA_OID_TIPO_SOLI_PAIS
        from ped_solic_Cabec x
        where X.SOCA_OID_SOLI_CABE = lnOidPedido
--        and x.modu_oid_modu<>23
        order by val_tota_paga_loca desc) psc
      where  rownum = 1;

   EXCEPTION
     WHEN no_data_found THEN
       lsresult := '';
   END;

   RETURN lsresult;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_SOLIC_PEDID_HIPER: '||ls_sqlerrm);
 END REC_FN_SOLIC_PEDID_HIPER;

/**************************************************************************
  Descripcion         : Funcion que verifica si se excluye al cliente del
                        control de CAMBIOS TRUEQUES
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
  Devuelve:
      S : SE EXCLUYE
      N : NO SE EXCLUYE
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_VERIF_EXCLU_CAMBI(pn_Oid_Cliente     NUMBER,
                                    pn_oid_periodo     NUMBER) RETURN VARCHAR2
   IS
      lnoidperiodo     cra_perio.oid_peri%type;
      lnaux number :=0;
      lsresult varchar2(1) := 'N';
   BEGIN

        lnoidperiodo := pn_oid_periodo;

        -- Evalua si existe el cliente
        select count(1)
          into lnaux
          from sto_bloqu_contr s
          where s.cod_tipo_docu = 'SPVD'
            and s.oid_peri = lnoidperiodo
            and s.clie_oid_clie = pn_Oid_Cliente
            and s.ind_tipo_bloq = '1'
            and s.ind_acti = '1'
            and s.val_moti_bloq = 'T';

        IF lnaux = 0 THEN
           -- Evalua si existe la tipologia del cliente como exclusion
           select count(1)
             into lnaux
             from mae_clien_tipo_subti t,
                  mae_clien_clasi c ,
                  sto_bloqu_contr s
            where t.clie_oid_clie = pn_Oid_Cliente
              and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
              and s.cod_tipo_docu = 'SPVD'
              and s.ind_tipo_bloq = '1'
              and s.ind_acti = '1'
              and s.val_moti_bloq = 'T'
              and s.oid_peri = lnoidperiodo
              and s.oid_tipo_clie = t.ticl_oid_tipo_clie
              and s.oid_subt_clie = t.sbti_oid_subt_clie
              and s.oid_clas_clie = c.clas_oid_clas
              and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi;

          IF lnaux = 0 THEN
             -- Si NO existe Tipologia, se verifica que exista Zona + Tipologia en blanco
             select count(1)
               into lnaux
               from sto_bloqu_contr s,
                    MAE_CLIEN,
                    MAE_CLIEN_UNIDA_ADMIN,
                    ZON_TERRI_ADMIN,
                    ZON_SECCI,
                    ZON_ZONA,
                    ZON_REGIO
              where s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '1'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'T'
                and s.oid_peri = lnoidperiodo
                and s.oid_tipo_clie is null
                and s.oid_subt_clie is null
                and s.oid_clas_clie is null
                and s.oid_tipo_clas_clie is null
                AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                AND ZON_ZONA.OID_ZONA = s.oid_zona;

                IF lnaux = 0 THEN
                   -- Tipologia en blanco + Zona en Blanco + Region
                   select count(1)
                     into lnaux
                     from sto_bloqu_contr s,
                          MAE_CLIEN,
                          MAE_CLIEN_UNIDA_ADMIN,
                          ZON_TERRI_ADMIN,
                          ZON_SECCI,
                          ZON_ZONA,
                          ZON_REGIO
                    where s.cod_tipo_docu = 'SPVD'
                      and s.ind_tipo_bloq = '1'
                      and s.ind_acti = '1'
                      and s.val_moti_bloq = 'T'
                      and s.oid_peri = lnoidperiodo
                      and s.oid_tipo_clie is null
                      and s.oid_subt_clie is null
                      and s.oid_clas_clie is null
                      and s.oid_tipo_clas_clie is null
                      AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                      AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                      AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                      AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                      AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                      AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                      AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                      AND ZON_REGIO.OID_REGI = s.oid_regi
                      AND s.oid_zona is null ;

                   IF lnaux != 0 THEN
                      -- Existe Tipologia en blanco + Zona en Blanco + Region
                      lsresult := 'S';
                   END IF;

                ELSE
                    -- Existe Tipologia en blanco + Zona
                    lsresult := 'S';
                END IF;

          ELSE
              -- Si existe Tipologia, se verifica que exista Tipologia + Zona
             select count(1)
               into lnaux
               from mae_clien_tipo_subti t,
                    mae_clien_clasi c ,
                    sto_bloqu_contr s,
                    MAE_CLIEN,
                    MAE_CLIEN_UNIDA_ADMIN,
                    ZON_TERRI_ADMIN,
                    ZON_SECCI,
                    ZON_ZONA,
                    ZON_REGIO
              where t.clie_oid_clie = pn_Oid_Cliente
                and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                and s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '1'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'T'
                and s.oid_peri = lnoidperiodo
                and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                and s.oid_subt_clie = t.sbti_oid_subt_clie
                and s.oid_clas_clie = c.clas_oid_clas
                and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                 AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                 AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                 AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                 AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                 AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                 AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                 AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                 AND ZON_ZONA.OID_ZONA = s.oid_zona;

              IF lnaux = 0 THEN
                 -- Si existe Tipologia pero sin zonas, se verifica que exista Tipologia + Region + ZOna en blanco
                 select count(1)
                   into lnaux
                   from mae_clien_tipo_subti t,
                        mae_clien_clasi c ,
                        sto_bloqu_contr s,
                        MAE_CLIEN,
                        MAE_CLIEN_UNIDA_ADMIN,
                        ZON_TERRI_ADMIN,
                        ZON_SECCI,
                        ZON_ZONA,
                        ZON_REGIO
                  where t.clie_oid_clie = pn_Oid_Cliente
                    and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                    and s.cod_tipo_docu = 'SPVD'
                    and s.ind_tipo_bloq = '1'
                    and s.ind_acti = '1'
                    and s.val_moti_bloq = 'T'
                    and s.oid_peri = lnoidperiodo
                    and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                    and s.oid_subt_clie = t.sbti_oid_subt_clie
                    and s.oid_clas_clie = c.clas_oid_clas
                    and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                     AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                     AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                     AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                     AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                     AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                     AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                     AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                     AND s.oid_zona is null
                     AND ZON_REGIO.OID_REGI = s.oid_regi;

                     IF lnaux = 0 THEN
                       -- Si existe Tipologia pero sin zonas, se verifica que exista Tipologia + Region en blanco + Zona en blanco
                       select count(1)
                         into lnaux
                         from mae_clien_tipo_subti t,
                              mae_clien_clasi c ,
                              sto_bloqu_contr s
                        where t.clie_oid_clie = pn_Oid_Cliente
                          and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                          and s.cod_tipo_docu = 'SPVD'
                          and s.ind_tipo_bloq = '1'
                          and s.ind_acti = '1'
                          and s.val_moti_bloq = 'T'
                          and s.oid_peri = lnoidperiodo
                          and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                          and s.oid_subt_clie = t.sbti_oid_subt_clie
                          and s.oid_clas_clie = c.clas_oid_clas
                          and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                          and s.oid_regi is null
                          and s.oid_zona is null;

                          IF lnaux != 0 THEN
                             -- Existe Tipologia + Region en blanco + Zona en blanco
                             lsresult := 'S';
                          END IF;

                     ELSE
                         -- Existe Tipologia + Region + Zona en blanco
                         lsresult := 'S';
                     END IF;

              ELSE
                 -- Existe Tipologia + Zona
                 lsresult := 'S';
              END IF;

          END IF;

        ELSE
            -- Existe el cliente
            lsresult := 'S';

        END IF;

        return lsresult;

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_VERIF_EXCLU_CAMBI: '||ls_sqlerrm);
END REC_FN_VERIF_EXCLU_CAMBI;

/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total del pedido y
                        el monto total de cambios trueuqes
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_EVALU_CAMBI(pnValNumeSoli     NUMBER,
                                   pnMontoPedido     OUT NUMBER,
                                   pnMontoDevolucion OUT NUMBER)IS

   BEGIN

       select nvl(max(a.VAL_TOTA_PAGA_LOCA),0) MontoPedido,
              nvl(sum(f.VAL_TOTA_PAGA_LOCA),0) MontoDevolucion
         into pnMontoPedido,
              pnMontoDevolucion
         from ped_solic_cabec a,
              rec_cabec_recla b,
              rec_opera_recla c,
              rec_solic_opera d,
              ped_solic_cabec e,
              ped_solic_cabec f,
              ped_tipo_solic_pais tsp,
              ped_tipo_solic ts
        where a.VAL_NUME_SOLI = pnValNumeSoli
        and a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
        and b.oid_cabe_recl = c.CARE_OID_CABE_RECL
        and c.OID_OPER_RECL  = d.opre_oid_oper_recl
        and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
        and oid_tipo_soli_pais = d.TSPA_OID_TIPO_SOLI_PAIS
        and cod_tipo_soli in ('SDTM','SDTM','SDCM','SDMC')
        and d.SOCA_OID_SOLI_CABE = e.OID_SOLI_CABE
        and e.SOCA_OID_SOLI_CABE = f.OID_SOLI_CABE;

        if pnMontoPedido = 0 then
           select nvl(max(a.VAL_TOTA_PAGA_LOCA),0) MontoPedido
             into pnMontoPedido
             from ped_solic_cabec a
            where a.VAL_NUME_SOLI = pnValNumeSoli;

        end if;

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_MONTO_EVALU_CAMBI: '||ls_sqlerrm);
END REC_PR_MONTO_EVALU_CAMBI;



/**************************************************************************
  Descripcion         : Procedure que Retorna el monto total de gestion y
                        el monto del documento por gestion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
PROCEDURE REC_PR_MONTO_GESTI_CAMBI(pnValNumeSoli              NUMBER,
                                   pnMontoTotalGestion    OUT NUMBER,
                                   psNumLote                  VARCHAR2,
                                   psNumDocu                  VARCHAR2,
                                   psCodClie                  VARCHAR2,
                                   psCodPeri                  VARCHAR2,
                                   psCodPais                  VARCHAR2,
                                   pnMontoDocGestion      OUT NUMBER) IS
   BEGIN
     select nvl(sum(b.VAL_PREC_CATA_DEVU * b.CAN_VENT_DEVU),0) MontoTotalxGestion
       into pnMontoTotalGestion
       from int_solic_conso_poven_cabec a,
            int_solic_conso_poven_detal b,
            sto_docum_digit             dig
      where a.NUM_LOTE = b.NUM_LOTE
        and a.NUM_DOCU = b.NUM_DOCU
        and a.COD_CLIE = b.COD_CLIE
        and a.COD_PERI = b.COD_PERI
        and a.COD_PAIS = b.COD_PAIS
        AND dig.sec_nume_docu = b.sec_nume_docu
        and a.NUM_DOCU_CRUC = pnValNumeSoli
        and a.cod_pais = psCodPais
        and a.cod_clie = psCodClie
        and b.cod_oper in('CM','TM','MC','MT')
        AND dig.ind_envi = 0
        AND dig.ind_rech = 0;

      select nvl(sum(b.VAL_PREC_CATA_DEVU * b.CAN_VENT_DEVU),0) MontoDocxGestion
       into pnMontoDocGestion
       from int_solic_conso_poven_cabec a,
            int_solic_conso_poven_detal b,
            sto_docum_digit             dig
      where a.NUM_LOTE = b.NUM_LOTE
        and a.NUM_DOCU = b.NUM_DOCU
        and a.COD_CLIE = b.COD_CLIE
        and a.COD_PERI = b.COD_PERI
        and a.COD_PAIS = b.COD_PAIS
        AND dig.sec_nume_docu = b.sec_nume_docu
        and a.NUM_DOCU_CRUC = pnValNumeSoli
        and a.NUM_LOTE = psNumLote
        and a.NUM_DOCU = psNumDocu
        and a.COD_CLIE = psCodClie
        and a.COD_PERI = psCodPeri
        and a.COD_PAIS = psCodPais
        and b.cod_oper in('CM','TM','MC','MT')
        AND dig.ind_envi = 0
        AND dig.ind_rech = 0;

   EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_PR_MONTO_GESTI_CAMBI: '||ls_sqlerrm);
END REC_PR_MONTO_GESTI_CAMBI;

----------------------------------
/**************************************************************************
  Descripcion         : Funcion que Retorna el porcentaje de cambios truques
                        que debera de aplicar en la validacion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_PORCE_MONTO_CAMBI(pn_Oid_Cliente     NUMBER,
                                    pscodigopais       VARCHAR2,
                                    pscodigoperiodo    VARCHAR2) RETURN NUMBER IS
      lnoidperiodo     cra_perio.oid_peri%type;
      lnaux            number :=0;
      lsresult         sto_bloqu_contr.val_pmon_devu%type ;

      --lsmondevparam varchar2(10);
      lnvalporcentaje  sto_bloqu_contr.val_pmon_devu%type;
   BEGIN

       lnoidperiodo := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(pscodigoperiodo);

       IF REC_FN_VERIF_EXCLU_CAMBI(pn_Oid_Cliente, lnoidperiodo) = 'S' THEN
          lsresult := 100;
       ELSE
           lsresult := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_PMON_TM');



        begin
          -- Evalua si existe el cliente
          select count(1), s.val_pmon_devu
            into lnaux,
                 lnvalporcentaje
            from sto_bloqu_contr s
           where s.clie_oid_clie = pn_Oid_Cliente
             and s.cod_tipo_docu = 'SPVD'
             and s.ind_tipo_bloq = '0'
             and s.ind_acti = '1'
             and s.val_moti_bloq = 'T'
             --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
           group by s.val_pmon_devu;

           lsresult:= lnvalporcentaje;

        exception
        when no_data_found then

           begin

             -- Evalua si existe la tipologia del cliente
             select count(1), s.val_pmon_devu
               into lnaux,
                    lnvalporcentaje
               from mae_clien_tipo_subti t,
                    mae_clien_clasi c ,
                    sto_bloqu_contr s
              where t.clie_oid_clie = pn_Oid_Cliente
                and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                and s.cod_tipo_docu = 'SPVD'
                and s.ind_tipo_bloq = '0'
                and s.ind_acti = '1'
                and s.val_moti_bloq = 'T'
                --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                and s.oid_subt_clie = t.sbti_oid_subt_clie
                and s.oid_clas_clie = c.clas_oid_clas
                and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
              group by s.val_pmon_devu;


            begin
                 -- Si existe, evalua la tipologia + Zona
                 select count(1), s.val_pmon_devu
                 into lnaux, lnvalporcentaje
                 from mae_clien_tipo_subti t,
                      mae_clien_clasi c ,
                      sto_bloqu_contr s,
                      MAE_CLIEN,
                      MAE_CLIEN_UNIDA_ADMIN,
                      ZON_TERRI_ADMIN,
                      ZON_SECCI,
                      ZON_ZONA,
                      ZON_REGIO
                where t.clie_oid_clie = pn_Oid_Cliente
                  and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                  and s.cod_tipo_docu = 'SPVD'
                  and s.ind_tipo_bloq = '0'
                  and s.ind_acti = '1'
                  and s.val_moti_bloq = 'T'
                  --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                  and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                  and s.oid_subt_clie = t.sbti_oid_subt_clie
                  and s.oid_clas_clie = c.clas_oid_clas
                  and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                   AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                   AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                   AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                   AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                   AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                   AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                   AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                   AND ZON_ZONA.OID_ZONA = s.oid_zona
                group by s.val_pmon_devu;

                lsresult:= lnvalporcentaje;

              exception
      				when no_data_found then

                  begin
                     -- Si existe, evalua la tipologia + Region + Zona en blanco
                     select count(1), s.val_pmon_devu
                     into lnaux, lnvalporcentaje
                     from mae_clien_tipo_subti t,
                          mae_clien_clasi c ,
                          sto_bloqu_contr s,
                          MAE_CLIEN,
                          MAE_CLIEN_UNIDA_ADMIN,
                          ZON_TERRI_ADMIN,
                          ZON_SECCI,
                          ZON_ZONA,
                          ZON_REGIO
                    where t.clie_oid_clie = pn_Oid_Cliente
                      and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                      and s.cod_tipo_docu = 'SPVD'
                      and s.ind_tipo_bloq = '0'
                      and s.ind_acti = '1'
                      and s.val_moti_bloq = 'T'
                      --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                      and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                      and s.oid_subt_clie = t.sbti_oid_subt_clie
                      and s.oid_clas_clie = c.clas_oid_clas
                      and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                       AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                       AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                       AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                       AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                       AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                       AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                       AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                       AND s.oid_zona is null
                       AND ZON_REGIO.Oid_Regi = s.oid_regi
                    group by s.val_pmon_devu;

                    lsresult:= lnvalporcentaje;

                  exception
                      when no_data_found then

                         begin

                             -- Si existe, evalua la tipologia + Region en blanco + Zona en blanco
                             select count(1), s.val_pmon_devu
                             into lnaux, lnvalporcentaje
                             from mae_clien_tipo_subti t,
                                  mae_clien_clasi c ,
                                  sto_bloqu_contr s,
                                  MAE_CLIEN,
                                  MAE_CLIEN_UNIDA_ADMIN,
                                  ZON_TERRI_ADMIN,
                                  ZON_SECCI,
                                  ZON_ZONA,
                                  ZON_REGIO
                            where t.clie_oid_clie = pn_Oid_Cliente
                              and c.ctsu_oid_clie_tipo_subt = t.oid_clie_tipo_subt
                              and s.cod_tipo_docu = 'SPVD'
                              and s.ind_tipo_bloq = '0'
                              and s.ind_acti = '1'
                              and s.val_moti_bloq = 'T'
                              --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                              and s.oid_tipo_clie = t.ticl_oid_tipo_clie
                              and s.oid_subt_clie = t.sbti_oid_subt_clie
                              and s.oid_clas_clie = c.clas_oid_clas
                              and s.oid_tipo_clas_clie = c.tccl_oid_tipo_clasi
                               AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                               AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                               AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                               AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                               AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                               AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                               AND MAE_CLIEN.OID_CLIE = t.clie_oid_clie
                               AND s.oid_zona is null
                               AND s.oid_regi is null
                            group by s.val_pmon_devu;

                         lsresult:= lnvalporcentaje;

                         exception
                             when no_data_found then
                               null;
                         end;
                  end;

              end;

            exception
            		when no_data_found then
                   begin
                     -- Si NO existe Tipologia, se verifica que exista Zona + Tipologia en blanco
                     select count(1),s.val_pmon_devu
                       into lnaux,
                            lnvalporcentaje
                       from sto_bloqu_contr s,
                            MAE_CLIEN,
                            MAE_CLIEN_UNIDA_ADMIN,
                            ZON_TERRI_ADMIN,
                            ZON_SECCI,
                            ZON_ZONA,
                            ZON_REGIO
                      where s.cod_tipo_docu = 'SPVD'
                        and s.ind_tipo_bloq = '0'
                        and s.ind_acti = '1'
                        and s.val_moti_bloq = 'T'
                        --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                        and s.oid_tipo_clie is null
                        and s.oid_subt_clie is null
                        and s.oid_clas_clie is null
                        and s.oid_tipo_clas_clie is null
                        AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                        AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                        AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                        AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                        AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                        AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                        AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                        AND ZON_ZONA.OID_ZONA = s.oid_zona
                      group by s.val_pmon_devu;

                      lsresult:= lnvalporcentaje;

                 exception
                     when no_data_found then
                       begin
                         -- Tipologia en blanco + Zona en Blanco + Region
                         select count(1),s.val_pmon_devu
                           into lnaux,
                                lnvalporcentaje
                           from sto_bloqu_contr s,
                                MAE_CLIEN,
                                MAE_CLIEN_UNIDA_ADMIN,
                                ZON_TERRI_ADMIN,
                                ZON_SECCI,
                                ZON_ZONA,
                                ZON_REGIO
                          where s.cod_tipo_docu = 'SPVD'
                            and s.ind_tipo_bloq = '0'
                            and s.ind_acti = '1'
                            and s.val_moti_bloq = 'T'
                            --and nvl(s.oid_peri,lnoidperiodo) = lnoidperiodo
                            and s.oid_tipo_clie is null
                            and s.oid_subt_clie is null
                            and s.oid_clas_clie is null
                            and s.oid_tipo_clas_clie is null
                            AND ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
                            AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
                            AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
                            AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
                            AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                            AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
                            AND MAE_CLIEN.OID_CLIE = pn_Oid_Cliente
                            AND ZON_REGIO.OID_REGI = s.oid_regi
                            AND s.oid_zona is null
                          group by s.val_pmon_devu ;

                          lsresult:= lnvalporcentaje;

                      exception
                         when no_data_found then
                              null;
                      end;

                 end;

              end;

           end;

       END IF;

       return nvl(lsresult,100);

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_PORCE_MONTO_CAMBI: '||ls_sqlerrm);
END REC_FN_PORCE_MONTO_CAMBI;

/**************************************************************************
  Descripcion         : Funcion que Retorna el porcentaje de cambios truques
                        que debera de aplicar en la validacion
  Fecha Creacion      : 16/11/2010
  Parametros Entrada:
      pn_Oid_Cliente  : oid Cliente
      pscodigopais    : codigo pais
  Autor               : Dennys Oliva Iriarte
***************************************************************************/
  FUNCTION REC_FN_ABONO_PENDI_CONSU(pn_Oid_Cliente     NUMBER,
                                    pn_oid_periodo     NUMBER) 
    RETURN NUMBER IS
      lnAbonoPendi  ped_solic_cabec.val_tota_paga_loca%type;
   BEGIN

        select abs( nvl(sum( ( nvl(VAL_PREC_CATA_UNIT_LOCA,0) - nvl(val_impo_desc_unit_loca,0) ) * nvl(num_unid_dema,0) ) ,0) ) porabonar 
          into lnAbonoPendi
        from rec_cabec_recla rcr, rec_opera_recla ror, rec_solic_opera rso, ped_solic_Cabec psc, ped_solic_posic psp,  ped_tipo_solic_pais tsp , ped_tipo_solic ts
        where RCR.OID_CABE_RECL = ROR.CARE_OID_CABE_RECL 
        and ROR.OID_OPER_RECL = RSO.OPRE_OID_OPER_RECL
        and RSO.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
        and PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.oid_tipo_soli_pais
        and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
        and ts.cod_tipo_soli like 'SD%'
        and RCR.CLIE_OID_CLIE = pn_Oid_Cliente ----49519415
        and PSC.GRPR_OID_GRUP_PROC = 3  ---- 5
        and RCR.IND_ABON_PDTE = 1
        and nvl(pn_oid_periodo, rcr.perd_oid_peri_docu_refe) = rcr.perd_oid_peri_docu_refe;

       return lnAbonoPendi;

   EXCEPTION

   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR REC_FN_ABONO_PENDI_CONSU: '||ls_sqlerrm);
END REC_FN_ABONO_PENDI_CONSU;

END REC_PKG_PROCE;
/
