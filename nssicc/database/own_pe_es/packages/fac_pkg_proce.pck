CREATE OR REPLACE PACKAGE FAC_PKG_PROCE IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  W_FILAS    NUMBER := 1000;

  TYPE ref_cursor IS REF CURSOR;

  /* Definicion de Constantes */
  OID_MODULO_REC NUMBER := 15;

  /**************************************************************************
  Descripcion        : Obtiene valores de posiciones de Cabecera
  Fecha Creacion     :  16/03/2011
  Parametros Entrada :
             pnOidSolicitud : oid Solicitud
             pnOidIdioma : oid Idioma
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE FAC_PR_OBTIE_POSIC_CABEC(pnOidSolicitud       NUMBER,
                                     pnOidIdioma          NUMBER,
                                     prCursor             OUT ref_cursor);

  /**************************************************************************
  Descripcion        : Obtiene documentos contables consolidados
  Fecha Creacion     :  18/03/2011
  Parametros Entrada :
             pnOidSolicitud : oid Solicitud
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE FAC_PR_OBTIE_DOCUM_CONSO(pnOidSolicitud       NUMBER,
                                     prCursor             OUT ref_cursor);

  /**************************************************************************
  Descripcion        : Obtiene documentos de identidad del cliente
  Fecha Creacion     :  21/03/2011
  Parametros Entrada :
             pnOidCliente : oid Cliente
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE MAE_PR_OBTIE_DOCUM_CLIEN(pnOidCliente         NUMBER,
                                     prCursor             OUT ref_cursor);

  /**************************************************************************
  Descripcion        : Obtiene direcciones del cliente
  Fecha Creacion     :  21/03/2011
  Parametros Entrada :
             pnOidCliente : oid Cliente
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE MAE_PR_OBTIE_DIREC_CLIEN(pnOidCliente         NUMBER,
                                     prCursor             OUT ref_cursor);

  /**************************************************************************
  Descripcion        : Obtiene los movimientos bancarios de un cliente
  Fecha Creacion     :  21/03/2011
  Parametros Entrada :
             pnOidPais : oid Pais
             pnOidCliente : oid Cliente
             pnOidEmpresa : oid Empresa
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE CCC_PR_OBTIE_MOVIM_BANCA(pnOidPais            NUMBER,
                                     pnOidCliente         NUMBER,
                                     pnOidEmpresa         NUMBER,
                                     prCursor             OUT ref_cursor);

  /**************************************************************************
  Descripcion        : Obtiene informacion de los documentos contables del cliente
  Fecha Creacion     :  22/03/2011
  Parametros Entrada :
             pnOidReceptor : oid Receptor
             pnOidSolicitud : oid Solicitud
             pnOidIdioma : oid Idioma
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE FAC_PR_OBTIE_DOCUM_CONTA(pnOidReceptor        NUMBER,
                                     pnOidSolicitud       NUMBER,
                                     pnOidIdioma          NUMBER,
                                     prCursor             OUT ref_cursor);

  /**************************************************************************
  Descripcion        : Obtiene informacion de los documentos no legales del cliente
  Fecha Creacion     :  22/03/2011
  Parametros Entrada :
             pnOidSolicitud : oid Solicitud
             pnOidReceptor : oid Receptor
             pnOidPeriodo : oid Periodo
             pnOidRegion : oid Region
             pnOidZona : oid Zona
             pnOidSeccion : oid Seccion
             pnOidTerritorio : oid Territorio
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE FAC_PR_OBTIE_DOCUM_NOLEG(pnOidSolicitud       NUMBER,
                                     pnOidReceptor        NUMBER,
                                     pnOidPeriodo         NUMBER,
                                     pnOidRegion          NUMBER,
                                     pnOidZona            NUMBER,
                                     pnOidSeccion         NUMBER,
                                     pnOidTerritorio      NUMBER,
                                     prCursor             OUT ref_cursor);

  /**************************************************************************
  Descripcion        : Obtiene datos para la impresion de los mensajes dirigidos
                       para un determinado cliente
  Fecha Creacion     :  22/03/2011
  Parametros Entrada :
             pnOidSolicitud : oid Solicitud
             pnOidCliente : oid Cliente
             pnOidPeriodo : oid Periodo
             psAmbitoGeografico : 'T' : Territorio, 'Z':Zona, 'S':Seccion, 'R':Region
             pnOidTerritorio : oid Territorio
             pnOidSeccion  : oid Seccion
             pnOidZona  : oid Zona
             pnOidRegion : oid Region
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE MSG_PR_OBTIE_DATOS_IMPRE(pnOidPais            NUMBER,
                                     pnOidCliente         NUMBER,
                                     pnOidPeriodo         NUMBER,
                                     psAmbitoGeografico   VARCHAR2,
                                     pnOidTerritorio      NUMBER,
                                     pnOidSeccion         NUMBER,
                                     pnOidZona            NUMBER,
                                     pnOidRegion          NUMBER,
                                     prCursor             OUT ref_cursor);

  /**************************************************************************
  Descripcion        : Obtiene informacion del patron de mensaje
  Fecha Creacion     :  22/03/2011
  Parametros Entrada :
             pnOidPatron : oid Receptor
             pnOidIdioma : oid Idioma
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE MSG_PR_OBTIE_PATRO_DOCUM(pnOidPatron          NUMBER,
                                     pnOidIdioma          NUMBER,
                                     prCursor             OUT ref_cursor);

END FAC_PKG_PROCE;
/

CREATE OR REPLACE PACKAGE BODY FAC_PKG_PROCE is

  /**************************************************************************
  Descripcion        : Obtiene valores de posiciones de Cabecera
  Fecha Creacion     :  16/03/2011
  Parametros Entrada :
             pnOidSolicitud : oid Solicitud
             pnOidIdioma : oid Idioma
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE FAC_PR_OBTIE_POSIC_CABEC(pnOidSolicitud       NUMBER,
                                     pnOidIdioma          NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
    SELECT POD.OCAT_OID_CATAL CATALOGO,
           PED.VAL_CODI_VENT AS CODIGOVENTA,
           MP.DES_CORT DESCRIPCIONSAP,
           PED.VAL_IMPO_DES_SIN_IMP_TOTA_DOCU AS IMPORTEDESCUENTOSINIMPUESTOSTD,
           PED.VAL_IMPO_DES_SIN_IMP_TOTA AS IMPORTEDESCUENTOSINIMPUESTOSTL,
           PED.VAL_IMPO_DES_SIN_IMP_UNIT_LOCA AS IMPORTEDESCUENTOSINIMPUESTOSUL,
           PED.VAL_IMPO_DES_SIN_IMP_UNIT_DOCU AS IMPORTEDESCUENTOSINIMPUESTOSUD,
           PED.VAL_IMPO_DESC_TOTA_DOCU AS IMPORTEDESCUENTOTD,
           PED.VAL_IMPO_DESC_TOTA_LOCA AS IMPORTEDESCUENTOTL,
           PED.VAL_IMPO_DESC_UNIT_DOCU AS IMPORTEDESCUENTOUD,
           PED.VAL_IMPO_DESC_UNIT_LOCA AS IMPORTEDESCUENTOUL,
           PED.VAL_IMPO_IMPU_TOTA_DOCU AS IMPORTEIMPUESTOTD,
           PED.VAL_IMPO_IMPU_TOTA_LOCA AS IMPORTEIMPUESTOTL,
           PED.VAL_IMPO_IMPU_UNIT_DOCU AS IMPORTEIMPUESTOUD,
           PED.VAL_IMPO_IMPU_UNIT_LOCA AS IMPORTEIMPUESTOUL,
           MP.COD_IND_DENT_CAJA AS INDICADORDENTROFUERACAJABOLSA,
           MP.IND_PROD_SERV AS INDICADORDPRODSERVICIO,
           PED.TAIM_OID_TASA_IMPU AS INDICADORIMPUESTOS,
           (SELECT case
                     when COUNT(*) > 0 then
                      1
                     else
                      0
                   end
              FROM FAC_TIPO_OFERT_EXCLU TOE
             WHERE TOE.TOFE_OID_TIPO_OFER(+) = TOF.OID_TIPO_OFER
               AND TOE.PAIS_OID_PAIS = PSC.PAIS_OID_PAIS
               AND ped.VAL_PREC_CATA_UNIT_LOCA = 0
               AND ped.VAL_PREC_CONT_UNIT_LOCA = 0
               and nvl(pod.TOFE_OID_TIPO_OFER, -1) =
                   (case when pod.TOFE_OID_TIPO_OFER is not null then
                    TOE.TOFE_OID_TIPO_OFER else - 1 end)
               AND TOE.FEC_DESD <= psc.FEC_CRON
               AND TOE.FEC_HAST >= psc.FEC_CRON) as INDICADORNOIMPRIMIBLE,
           MP.NEGO_OID_NEGO NEGOCIOPRODUCTO,
           PED.NUM_CONS AS NUMEROCONSOLIDADO,
           PED.COD_POSI AS NUMEROPOSICION,
           PED.SOCA_OID_SOLI_CABE AS OIDCABECERA,
           PED.FOPA_OID_FORM_PAGO AS OIDFORMAPAGO,
           PED.OID_SOLI_POSI AS OIDPOSICION,
           POD.NUM_PAGI_CATA PAGINA,
           PED.VAL_PREC_CATA_TOTA_DOCU AS PRECIOCATALOGOTD,
           PED.VAL_PREC_CATA_TOTA_LOCA_UNID AS PRECATALTLUNIDADESDEMANDAREAL,
           PED.VAL_PREC_CATA_TOTA_LOCA AS PRECIOCATALOGOTL,
           PED.VAL_PREC_CATA_UNIT_DOCU AS PRECIOCATALOGOUD,
           PED.VAL_PREC_CATA_UNIT_LOCA AS PRECIOCATALOGOUL,
           PED.VAL_PREC_SIN_IMPU_UNIT_DOCU AS PRECIOCONTABLESINIMPUESTOSUD,
           PED.VAL_PREC_SIN_IMPU_UNIT_LOCA AS PRECIOCONTABLESINIMPUESTOSUL,
           PED.VAL_PREC_CONT_TOTA_DOCU AS PRECIOCONTABLETD,
           PED.VAL_PREC_CONT_TOTA_LOCA AS PRECIOCONTABLETL,
           PED.VAL_PREC_CONTA_UNIT_DOCU AS PRECIOCONTABLEUD,
           PED.VAL_PREC_CONT_UNIT_LOCA AS PRECIOCONTABLEUL,
           PED.VAL_PREC_FACT_TOTA_DOCU AS PRECIOFACTURATD,
           PED.VAL_PREC_FACT_TOTA_LOCA AS PRECIOFACTURATL,
           PED.VAL_PREC_FACT_UNIT_DOCU AS PRECIOFACTURAUD,
           PED.VAL_PREC_FACT_UNIT_LOCA AS PRECIOFACTURAUL,
           PED.VAL_PREC_NETO_TOTA_DOCU AS PRECIONETOTD,
           PED.VAL_PREC_NETO_TOTA_LOCA AS PRECIONETOTL,
           PED.VAL_PREC_NETO_UNIT_DOCU AS PRECIONETOUD,
           PED.VAL_PREC_NETO_UNIT_LOCA AS PRECIONETOUL,
           PED.VAL_PREC_SIN_IMPU_TOTA_DOCU AS PRECIOSINIMPUESTOSTD,
           PED.VAL_PREC_SIN_IMPU_TOTA_LOCA AS PRECIOSINIMPUESTOSTL,
           PED.VAL_PREC_SIN_IMPU_UNIT_DOCU AS PRECIOSINIMPUESTOSUD,
           PED.VAL_PREC_SIN_IMPU_UNIT_LOCA AS PRECIOSINIMPUESTOSUL,
           PED.VAL_PREC_TOTA_TOTA_DOCU AS PRECIOTOTALTD,
           PED.VAL_PREC_TOTA_TOTA_LOCA AS PRECIOTOTALTL,
           PED.VAL_PREC_TOTA_UNIT_DOCU AS PRECIOTOTALUD,
           PED.VAL_PREC_TOTA_UNIT_LOCA AS PRECIOTOTALUL,
           PED.PROD_OID_PROD AS PRODUCTO,
           PTI.VAL_TASA_IMPU TASAIMPUESTO,
           POD.TOFE_OID_TIPO_OFER TIPOOFERTA,
           PED.NUM_UNID_ATEN AS UNIDADESATENDIDAS,
           PED.NUM_UNID_COMPR AS UNIDADESCOMPROMETIDAS,
           PED.NUM_UNID_DEMA_REAL AS UNIDADESDEMANDAREAL,
           PED.NUM_UNID_POR_ATEN AS UNIDADESPORATENDER,
           PSC.PAIS_OID_PAIS PAIS,
           PSC.FEC_CRON FECHA,
           PED.VAL_PORC_DESC AS PORCENTAJEDESCUENTO,
           TOF.COD_TIPO_OFER CODIGOTIPOOFERTA,
           PED.ESPO_OID_ESTA_POSI AS ESTADO,
           PED.TPOS_OID_TIPO_POSI AS TIPOPOSICION,
           PED.STPO_OID_SUBT_POSI AS SUBTIPOPOSICION,
           PQ_APL_AUX.Valor_Gen_I18n_Sicc(pnOidIdioma,
                                          MP.OID_PROD,
                                          'MAE_PRODU') DESC_PROD_I18N,
           ped.ofde_oid_deta_ofer OIDDETAOFER,
           MP.VAL_COST_ESTD,
           CASE
             WHEN psc.soca_oid_docu_refe IS NOT NULL AND
                  psc.modu_oid_modu = OID_MODULO_REC AND
                  TS.IND_SOLI_NEGA = 1 THEN
              (SELECT dcca.oid_cabe
                 FROM fac_docum_conta_cabec dcca,
                      fac_docum_conta_linea dcla,
                      ped_solic_posic       pos
                WHERE psc.soca_oid_docu_refe = dcca.soca_oid_soli_cabe
                  AND dcca.oid_cabe = dcla.dcca_oid_cabe
                  AND dcla.prod_oid_prod = ped.prod_oid_prod
                  AND dcla.sopo_oid_soli_posi = pos.oid_soli_posi
                  AND ((pos.val_codi_vent IS NULL AND
                      ped.val_codi_vent IS NULL) OR
                      (pos.val_codi_vent = ped.val_codi_vent))
                  AND ROWNUM = 1)
             ELSE
              0
           END documentolegalreferencia,
           PSC.COPA_OID_PARA_GENE AS OIDCONCURSO
      FROM PED_SOLIC_POSIC     ped,
           PRE_OFERT_DETAL     POD,
           MAE_PRODU           MP,
           PED_SOLIC_CABEC     PSC,
           PED_TASA_IMPUE      PTI,
           PRE_TIPO_OFERT      TOF,
           PED_TIPO_SOLIC_PAIS TSP,
           PED_TIPO_SOLIC      TS
     WHERE POD.OID_DETA_OFER(+) = PED.OFDE_OID_DETA_OFER
       AND MP.OID_PROD = PED.PROD_OID_PROD
       AND PED.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
       AND PTI.OID_TASA_IMPU(+) = PED.TAIM_OID_TASA_IMPU
       AND TOF.OID_TIPO_OFER(+) = POD.TOFE_OID_TIPO_OFER
       AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
       AND TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
       AND psc.OID_SOLI_CABE = pnOidSolicitud;

  END FAC_PR_OBTIE_POSIC_CABEC;


  /**************************************************************************
  Descripcion        : Obtiene documentos contables consolidados
  Fecha Creacion     :  18/03/2011
  Parametros Entrada :
             pnOidSolicitud : oid Solicitud
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE FAC_PR_OBTIE_DOCUM_CONSO(pnOidSolicitud       NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
    SELECT F.VAL_ALMA as CODIGOALMACEN,
         F.VAL_EJER_DOCU_INTE as EJERCICIODOCUMENTOCONTABLE,
         F.VAL_ESTA as ESTADO,
         F.FEC_EMIS as FECHAEMISION,
         F.FEC_FACT as FECHAFACTURACION,
         F.FOPA_OID_FORM_PAGO as FORMAPAGO,
         F.IMP_DES3_TOTA_DOCU as IMPORTEDESCUENTODOCUMENTO,
         F.IMP_DES3_TOTAL_LOCA as IMPORTEDESCUENTO3TOTALLOCAL,
         F.IMP_FLET_TOTA_DOCU as IMPORTEFLETETOTALDOCUMENTO,
         F.IMP_FLET_TOTA_LOCA as IMPORTEFLETETOTALLOCAL,
         F.IMP_FLET_IMPU_TOTA_LOCA as IMPORTEFLETESINIMPUESTOTAL,
         F.IND_IMPR as INDICADORIMPRESION,
         F.MONE_OID_MONE as MONEDA,
         F.NUM_DOCU_CONT_INTE as NUMERODOCUMENTOINTERNO,
         F.NUM_DOCU_ORIG as NUMERODOCUMENTOORIGEN,
         F.VAL_OBSE as OBSERVACIONES,
         F.OID_CABE as OIDCABECERASOLICITUD,
         F.SOCA_OID_SOLI_CABE as OIDSOLICITUD,
         F.PAIS_OID_PAIS as PAIS,
         F.PERD_OID_PERI as PERIODO,
         F.VAL_PUNT_EMIS as PUNTOEMISION,
         F.ZORG_OID_REGI as REGION,
         F.ZSCC_OID_SECC as SECCION,
         F.SOCI_OID_SOCI as SOCIEDAD,
         F.SBAC_OID_SBAC as SUBACCESO,
         F.ZSGV_OID_SUBG_VENT as SUBGERENCIA,
         F.TERR_OID_TERR as TERRITORIO,
         F.VAL_TIPO_CAMB as TIPOCAMBIO,
         F.TIDO_OID_TIPO_DOCU as TIPODOCUMENTOLEGAL,
         F.NUM_ABON as NUMEROABONO,
         F.CLDI_OID_CLIE_DIRE as OIDCLIENTEDIRECCION,
         F.FORS_OID_FORM as OIDFORMULARIO,
         F.VAL_TIPO_DIRE as TIPODIRECCION,
         F.ZZON_OID_ZONA as ZONA,
         F.VAL_APE1 as APELLIDO1,
         F.VAL_APE2 as APELLIDO2,
         F.VAL_NOM1 as NOMBRE1,
         F.VAL_NOM2 as NOMBRE2,
         F.NUM_DOCU_LEGA as NUMERODOCUMENTOLEGAL,
         F.VAL_NUME_IDEN_FISC as NUMEROIDENTIFICACIONFISCAL,
         F.VAL_NUME_IDEN_NNAL as NUMEROIDENTIFICACIONNACIONAL,
         F.VAL_SERI_DOCU_LEGA as SERIEDOCUMENTOLEGAL,
         F.VAL_PREC_CATA_IMPU as PRECIOCATALOGOSINIMPUESTOTOTAL,
         F.VAL_PREC_CONT_IMPU as PRECIOCONTABLESINIMPUESTOTOTAL,
         F.VAL_PREC_CATA_TOTA_LOCA_UNID as PRECIOCATALOGOTOTALLOCALUNI,
         F.VAL_PREC_CATA_TOTA_LOCA as PRECIOCATALOGOTOTALLOCAL,
         F.VAL_PREC_CONT_TOTA_LOCA as PRECIOCONTABLETOTALLOCAL,
         F.IMP_DES1_TOTA_LOCA as IMPORTEDESCUENTO1TOTALLOCAL,
         F.IMP_DES1_IMPU as IMPORTEDESCUENTOSINIMPUESTOS,
         F.VAL_PREC_FACT_TOTA_LOCA as PRECIOFACTURATOTALLOCAL,
         F.VAL_PREC_TOTA_TOTA_LOCA as PRECIOTOTALTOTALLOCAL,
         F.IMP_DES3_IMPU as IMPORTEDESCUENTO3SINIMPUESTOS,
         F.IMP_DESC_TOTA_LOCA as IMPORTEDESCUENTOTOTALLOCAL,
         F.VAL_PREC_NETO_TOTA_LOCA as PRECIONETOTOTALLOCAL,
         F.IMP_IMPU_TOTA_LOCA as IMPORTEIMPUESTOSTOTALLOCAL,
         F.VAL_TOTA_PAGA_LOCA as TOTALAPAGARLOCAL,
         F.VAL_TOTA_PAGA_DOCU as TOTALAPAGARDOCUMENTO,
         F.IMP_REDO_LOCA as IMPORTEREDONDEOLOCAL,
         F.IMP_REDO_CONS_LOCA as IMPORTEREDONDEOCONSOLIDADO,
         M.CLIE_OID_CLIE as OIDCLIENTE,
         F.NUM_UNID_ATEN_TOTA as UNIDADESATENDIDAS,
         F.VAL_PREC_CATA_TOTA_DOCU as PRECIOCATALOGOTOTALDOCUMENTO,
         F.VAL_PREC_CATA_SIN_IMPU_TOTA as PRECIOCATALOGOSINIMPUESTO,
         F.VAL_PREC_CONT_TOTA_DOCU as PRECIOCONTABLETOTALDOCUMENTO,
         F.VAL_PREC_CONT_SIN_IMPU_TOTA as PRECIOCONTASINIMPUESDOCUMENTO,
         F.IMP_DES1_TOTA_DOCU as IMPORTEDESCUENTOTOTALDOCUMENTO,
         F.IMP_DES1_SIN_IMPU_TOTA as IMPORTEDESCUENTOSINIMPUES,
         F.IMP_DES3_SIN_IMPU_TOTA as IMPORTEDESCUENTO3SINIMPUES,
         F.IMP_DESC_TOTA_DOCU as IMPORTEDESCUENTOTOTALDOCUMENTO,
         F.VAL_PREC_FACT_TOTA_DOCU as PRECIOFACTURATOTALDOCUMENTO,
         F.IMP_IMPU_TOTA_DOCU as IMPORTEIMPUESTOSTOTALDOCUMENTO,
         F.VAL_PREC_TOTA_TOTA_DOCU as PRECIOTOTALTOTALDOCUMENTO,
         F.ICTP_OID_TIPO_PROG TIPO_PROG,
         F.IMP_FLET_SIN_IMPU_TOTA_DOCU
   FROM FAC_DOCUM_CONTA_CABEC F, MAE_CLIEN_DIREC M
  WHERE M.OID_CLIE_DIRE = F.CLDI_OID_CLIE_DIRE
    AND F.SOCA_OID_SOLI_CABE = pnOidSolicitud;

  END FAC_PR_OBTIE_DOCUM_CONSO;


  /**************************************************************************
  Descripcion        : Obtiene documentos de identidad del cliente
  Fecha Creacion     :  21/03/2011
  Parametros Entrada :
             pnOidCliente : oid Cliente
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE MAE_PR_OBTIE_DOCUM_CLIEN(pnOidCliente         NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
    SELECT OID_CLIE_IDEN,
           TDOC_OID_TIPO_DOCU,
           NUM_DOCU_IDEN,
           VAL_IDEN_PERS_EMPR,
           VAL_IDEN_DOCU_PRIN
      FROM MAE_CLIEN_IDENT I
     WHERE I.CLIE_OID_CLIE = pnOidCliente
     ORDER BY OID_CLIE_IDEN ASC,
              TDOC_OID_TIPO_DOCU ASC,
              VAL_IDEN_DOCU_PRIN DESC;

  END MAE_PR_OBTIE_DOCUM_CLIEN;


  /**************************************************************************
  Descripcion        : Obtiene direcciones del cliente
  Fecha Creacion     :  21/03/2011
  Parametros Entrada :
             pnOidCliente : oid Cliente
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE MAE_PR_OBTIE_DIREC_CLIEN(pnOidCliente         NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
    SELECT dir.OID_CLIE_DIRE,
           dir.TIDC_OID_TIPO_DIRE,
           dir.TIVI_OID_TIPO_VIA,
           val.OID_VALO_ESTR_GEOP,
           dir.ZVIA_OID_VIA,
           dir.NUM_PPAL,
           dir.VAL_COD_POST,
           dir.VAL_OBSE,
           dir.IND_DIRE_PPAL,
           dir.VAL_NOMB_VIA,
           val.DES_GEOG
      FROM MAE_CLIEN_DIREC       dir,
           ZON_VALOR_ESTRU_GEOPO val,
           ZON_TERRI             terr
     WHERE dir.CLIE_OID_CLIE = pnOidCliente
       AND dir.TERR_OID_TERR = terr.OID_TERR
       AND dir.IND_ELIM = 0
       AND terr.VEPO_OID_VALO_ESTR_GEOP = val.OID_VALO_ESTR_GEOP;

  END MAE_PR_OBTIE_DIREC_CLIEN;


  /**************************************************************************
  Descripcion        : Obtiene los movimientos bancarios de un cliente
  Fecha Creacion     :  21/03/2011
  Parametros Entrada :
             pnOidPais : oid Pais
             pnOidCliente : oid Cliente
             pnOidEmpresa : oid Empresa
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE CCC_PR_OBTIE_MOVIM_BANCA(pnOidPais            NUMBER,
                                     pnOidCliente         NUMBER,
                                     pnOidEmpresa         NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
    SELECT m.*
    FROM ccc_movim_banca m
    WHERE m.pais_oid_pais = pnOidPais
      AND m.clie_oid_clie = pnOidCliente
      AND m.soci_oid_soci = pnOidEmpresa
      AND m.imp_sald_pend > 0
      AND m.cod_iden_proc = 'P'
    ORDER BY FEC_PAGO, NUM_CONS_TRAN;

  END CCC_PR_OBTIE_MOVIM_BANCA;


  /**************************************************************************
  Descripcion        : Obtiene informacion de los documentos contables del cliente
  Fecha Creacion     :  22/03/2011
  Parametros Entrada :
             pnOidReceptor : oid Receptor
             pnOidSolicitud : oid Solicitud
             pnOidIdioma : oid Idioma
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE FAC_PR_OBTIE_DOCUM_CONTA(pnOidReceptor        NUMBER,
                                     pnOidSolicitud       NUMBER,
                                     pnOidIdioma          NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
      SELECT DCC.OID_CABE as A_OID_CABE,
             DCC.PAIS_OID_PAIS as A_PAIS_OID_PAIS,
             DCC.SOCI_OID_SOCI as A_SOCI_OID_SOCI,
             DCC.SBAC_OID_SBAC as A_SBAC_OID_SBAC,
             DCC.TIDO_OID_TIPO_DOCU as A_TIDO_OID_TIPO_DOCU,
             DCC.VAL_EJER_DOCU_INTE as A_VAL_EJER_DOCU_INTE,
             DCC.NUM_DOCU_CONT_INTE as A_NUM_DOCU_CONT_INTE,
             DCC.VAL_ESTA as A_VAL_ESTA,
             DCC.FEC_EMIS as A_FEC_EMIS,
             DCC.VAL_PUNT_EMIS as A_VAL_PUNT_EMIS,
             DCC.FEC_FACT as A_FEC_FACT,
             DCC.VAL_SERI_DOCU_LEGA as A_VAL_SERI_DOCU_LEGA,
             DCC.NUM_DOCU_LEGA as A_NUM_DOCU_LEGA,
             DCC.VAL_NUME_IDEN_FISC as A_VAL_NUME_IDEN_FISC,
             DCC.VAL_NUME_IDEN_NNAL as A_VAL_NUME_IDEN_NNAL,
             DCC.VAL_OBSE as A_VAL_OBSE,
             DCC.NUM_ABON as A_NUM_ABON,
             DCC.VAL_NOM1 as A_VAL_NOM1,
             DCC.VAL_NOM2 as A_VAL_NOM2,
             DCC.VAL_APE1 as A_VAL_APE1,
             DCC.VAL_APE2 as A_VAL_APE2,
             DCC.ZORG_OID_REGI as A_ZORG_OID_REGI,
             DCC.ZSGV_OID_SUBG_VENT as A_ZSGV_OID_SUBG_VENT,
             DCC.ZZON_OID_ZONA as A_ZZON_OID_ZONA,
             DCC.TERR_OID_TERR as A_TERR_OID_TERR,
             DCC.CLDI_OID_CLIE_DIRE as A_CLDI_OID_CLIE_DIRE,
             DCC.PERD_OID_PERI as A_PERD_OID_PERI,
             DCC.FOPA_OID_FORM_PAGO as A_FOPA_OID_FORM_PAGO,
             DCC.SOCA_OID_SOLI_CABE as A_SOCA_OID_SOLI_CABE,
             DCC.FORS_OID_FORM as A_FORS_OID_FORM,
             DCC.ZSCC_OID_SECC as A_ZSCC_OID_SECC,
             DCC.MONE_OID_MONE as A_MONE_OID_MONE,
             DCC.IMP_FLET_TOTA_LOCA as A_IMP_FLET_TOTA_LOCA,
             DCC.IMP_FLET_IMPU_TOTA_LOCA as A_IMP_FLET_IMPU_TOTA_LOCA,
             DCC.VAL_TIPO_DIRE as A_VAL_TIPO_DIRE,
             DCC.NUM_DOCU_ORIG as A_NUM_DOCU_ORIG,
             DCC.VAL_ALMA as A_VAL_ALMA,
             DCC.VAL_TIPO_CAMB as A_VAL_TIPO_CAMB,
             DCC.IND_IMPR as A_IND_IMPR,
             DCC.IMP_DES3_TOTAL_LOCA as A_IMP_DES3_TOTAL_LOCA,
             DCC.IMP_DES3_SIN_IMPU_TOTA as A_IMP_DES3_SIN_IMPU_TOTA,
             DCC.IMP_FLET_TOTA_DOCU as A_IMP_FLET_TOTA_DOCU,
             DCC.IMP_DES3_TOTA_DOCU as A_IMP_DES3_TOTA_DOCU,
             DCC.VAL_PREC_CATA_IMPU as A_VAL_PREC_CATA_IMPU,
             DCC.VAL_PREC_CONT_IMPU as A_VAL_PREC_CONT_IMPU,
             DCC.VAL_PREC_CATA_TOTA_LOCA_UNID as A_VAL_PREC_CATA_TOTA_LOCA_UNID,
             DCC.VAL_PREC_CATA_TOTA_LOCA as A_VAL_PREC_CATA_TOTA_LOCA,
             DCC.VAL_PREC_CONT_TOTA_LOCA as A_VAL_PREC_CONT_TOTA_LOCA,
             DCC.IMP_DES1_TOTA_LOCA as A_IMP_DES1_TOTA_LOCA,
             DCC.IMP_DES1_IMPU as A_IMP_DES1_IMPU,
             DCC.VAL_PREC_FACT_TOTA_LOCA as A_VAL_PREC_FACT_TOTA_LOCA,
             DCC.VAL_PREC_TOTA_TOTA_LOCA as A_VAL_PREC_TOTA_TOTA_LOCA,
             DCC.IMP_DES3_IMPU as A_IMP_DES3_IMPU,
             DCC.IMP_DESC_TOTA_LOCA as A_IMP_DESC_TOTA_LOCA,
             DCC.VAL_PREC_NETO_TOTA_LOCA as A_VAL_PREC_NETO_TOTA_LOCA,
             DCC.IMP_IMPU_TOTA_LOCA as A_IMP_IMPU_TOTA_LOCA,
             DCC.VAL_TOTA_PAGA_LOCA as A_VAL_TOTA_PAGA_LOCA,
             DCC.VAL_TOTA_PAGA_DOCU as A_VAL_TOTA_PAGA_DOCU,
             DCC.IMP_REDO_LOCA as A_IMP_REDO_LOCA,
             DCC.IMP_REDO_CONS_LOCA as A_IMP_REDO_CONS_LOCA,
             DCC.NUM_UNID_ATEN_TOTA as A_NUM_UNID_ATEN_TOTA,
             DCC.VAL_PREC_CATA_TOTA_DOCU as A_VAL_PREC_CATA_TOTA_DOCU,
             DCC.VAL_PREC_CATA_SIN_IMPU_TOTA as A_VAL_PREC_CATA_SIN_IMPU_TOTA,
             DCC.VAL_PREC_CONT_TOTA_DOCU as A_VAL_PREC_CONT_TOTA_DOCU,
             DCC.VAL_PREC_CONT_SIN_IMPU_TOTA as A_VAL_PREC_CONT_SIN_IMPU_TOTA,
             DCC.IMP_DES1_TOTA_DOCU as A_IMP_DES1_TOTA_DOCU,
             DCC.IMP_DES1_SIN_IMPU_TOTA as A_IMP_DES1_SIN_IMPU_TOTA,
             DCC.IMP_DES3_SIN_IMPU_TOTA as A_IMP_DES3_SIN_IMPU_TOTA,
             DCC.IMP_DESC_TOTA_DOCU as A_IMP_DESC_TOTA_DOCU,
             DCC.VAL_PREC_FACT_TOTA_DOCU as A_VAL_PREC_FACT_TOTA_DOCU,
             DCC.IMP_IMPU_TOTA_DOCU as A_IMP_IMPU_TOTA_DOCU,
             DCC.VAL_PREC_TOTA_TOTA_DOCU as A_VAL_PREC_TOTA_TOTA_DOCU,
             DCC.VAL_PREC_NETO_TOTA_DOCU as A_VAL_PREC_NETO_TOTA_DOCU,
             DCC.IMP_FLET_SIN_IMPU_TOTA_DOCU as A_IMP_FLET_SIN_IMPU_TOTA_DOCU,
             DCC.IMP_REDO_CONS_DOCU as A_IMP_REDO_CONS_DOCU,
             DCC.IMP_REDO_DOCU as A_IMP_REDO_DOCU,
             DCC.NUM_LOTE_CONT as A_NUM_LOTE_CONT,
             DCC.FEC_CONT as A_FEC_CONT,
             DCC.VAL_DIRE_COMP as A_VAL_DIRE_COMP,
             DCC.VAL_NOMB_VIA as A_VAL_NOMB_VIA,
             DCL.SOPO_OID_SOLI_POSI as B_SOPO_OID_SOLI_POSI,
             DCL.PROD_OID_PROD as B_PROD_OID_PROD,
             DCL.MONE_OID_MONE as B_MONE_OID_MONE,
             DCL.VAL_PREC_TOTA_TOTA_DOCU as B_VAL_PREC_TOTA_TOTA_DOCU,
             DCL.VAL_PREC_NETO_TOTA_DOCU as B_VAL_PREC_NETO_TOTA_DOCU,
             DCL.IMP_IMPU_TOTA_DOCU as B_IMP_IMPU_TOTA_DOCU,
             DCL.IMP_DESC_SIN_IMPU_TOTA_DOCU as B_IMP_DESC_SIN_IMPU_TOTA_DOCU,
             DCL.IMP_DESC_TOTA_DOCU as B_IMP_DESC_TOTA_DOCU,
             DCL.VAL_PREC_FACT_TOTA_DOCU as B_VAL_PREC_FACT_TOTA_DOCU,
             DCL.VAL_PREC_CATA_TOTA_DOCU as B_VAL_PREC_CATA_TOTA_DOCU,
             DCL.VAL_PREC_CONT_TOTA_DOCU as B_VAL_PREC_CONT_TOTA_DOCU,
             DCL.VAL_PREC_TOTA_UNIT_DOCU as B_VAL_PREC_TOTA_UNIT_DOCU,
             DCL.IMP_IMPU_UNIT_DOCU as B_IMP_IMPU_UNIT_DOCU,
             DCL.VAL_PREC_NETO_UNIT_DOCU as B_VAL_PREC_NETO_UNIT_DOCU,
             DCL.IMP_DESC_SIN_IMPU_UNIT_DOCU as B_IMP_DESC_SIN_IMPU_UNIT_DOCU,
             DCL.IMP_DESC_UNIT_DOCU as B_IMP_DESC_UNIT_DOCU,
             DCL.VAL_PORC_DESC as B_VAL_PORC_DESC,
             DCL.VAL_PREC_SIN_IMPU_UNIT_DOCU as B_VAL_PREC_SIN_IMPU_UNIT_DOCU,
             DCL.VAL_PREC_FACT_UNIT_DOCU as B_VAL_PREC_FACT_UNIT_DOCU,
             DCL.VAL_PREC_CONT_UNIT_DOCU as B_VAL_PREC_CONT_UNIT_DOCU,
             DCL.VAL_PREC_CATA_UNIT_DOCU as B_VAL_PREC_CATA_UNIT_DOCU,
             DCL.VAL_PREC_TOTA_TOTA_LOCA as B_VAL_PREC_TOTA_TOTA_LOCA,
             DCL.IMP_IMPU_TOTA_LOCA as B_IMP_IMPU_TOTA_LOCA,
             DCL.VAL_PREC_NETO_TOTA_LOCA as B_VAL_PREC_NETO_TOTA_LOCA,
             DCL.IMP_DESC_SIN_IMPU_TOTA_LOCA as B_IMP_DESC_SIN_IMPU_TOTA_LOCA,
             DCL.IMP_DESC_TOTA_LOCA as B_IMP_DESC_TOTA_LOCA,
             DCL.VAL_PREC_SIN_IMPU_TOTA_LOCA as B_VAL_PREC_SIN_IMPU_TOTA_LOCA,
             DCL.VAL_PREC_FACT_TOTA_LOCA as B_VAL_PREC_FACT_TOTA_LOCA,
             DCL.VAL_PREC_CATA_LOCA_UNID_DEMA as B_VAL_PREC_CATA_LOCA_UNID_DEMA,
             DCL.VAL_PREC_CATA_TOTA_LOCA as B_VAL_PREC_CATA_TOTA_LOCA,
             DCL.VAL_PREC_CONT_TOTA_LOCA as B_VAL_PREC_CONT_TOTA_LOCA,
             DCL.VAL_PREC_TOTA_UNIT_LOCA as B_VAL_PREC_TOTA_UNIT_LOCA,
             DCL.IMP_IMPU_UNIT_LOCA as B_IMP_IMPU_UNIT_LOCA,
             DCL.VAL_PREC_NETO_UNIT_LOCA as B_VAL_PREC_NETO_UNIT_LOCA,
             DCL.IMP_DESC_SIN_IMPU_UNIT as B_IMP_DESC_SIN_IMPU_UNIT,
             DCL.IMP_DESC_UNIT_LOCA as B_IMP_DESC_UNIT_LOCA,
             DCL.VAL_PREC_SIN_IMPU_UNIT as B_VAL_PREC_SIN_IMPU_UNIT,
             DCL.VAL_PREC_FACT_UNIT_LOCA as B_VAL_PREC_FACT_UNIT_LOCA,
             DCL.VAL_PREC_CONT_UNIT_LOCA as B_VAL_PREC_CONT_UNIT_LOCA,
             DCL.VAL_PREC_CATA_UNIT_LOCA as B_VAL_PREC_CATA_UNIT_LOCA,
             DCL.VAL_PREC_SIN_IMPU_TOTA_DOCU as B_VAL_PREC_SIN_IMPU_TOTA_DOCU,
             DCL.NUM_PAGI as B_NUM_PAGI,
             DCL.IND_DENT_FUER_CAJA_BOLS as B_IND_DENT_FUER_CAJA_BOLS,
             DCL.NUM_LINEA as B_NUM_LINEA,
             DCL.NUM_UNID_ATEN as B_NUM_UNID_ATEN,
             DCL.IND_NO_IMPR as B_IND_NO_IMPR,
             DCL.VAL_CATA as B_VAL_CATA,
             DCL.OID as B_OID,
             DCL.DCCA_OID_CABE as B_DCCA_OID_CABE,
             MCD.IND_DIRE_PPAL,
             PERIO1.VAL_NOMB_PERI,
             SOCIE.VAL_IDEN_FISC,
             CASE WHEN ((SELECT COUNT(*)
                 FROM MAV_PARAM_GEREN_ZONA PGZ
                 WHERE PGZ.SBTI_OID_SUBT_CLIE = PSC1.SBTI_OID_SUBT_CLIE) >= 1) THEN
                    ' '
             ELSE
                   TO_CHAR(TERRI1.COD_TERR)
             END COD_TERR,
             CASE WHEN ((SELECT COUNT(*)
                    FROM MAV_PARAM_GEREN_ZONA PGZ
                    WHERE PGZ.SBTI_OID_SUBT_CLIE = PSC1.SBTI_OID_SUBT_CLIE) >= 1) THEN
                        (SELECT ZON1.COD_ZONA
                         FROM ZON_ZONA ZON1
                         WHERE ZON1.CLIE_OID_CLIE = PSC1.CLIE_OID_CLIE
                           AND ZON1.IND_ACTI = 1
                           AND ZON1.IND_BORR <> 1
                        )
              ELSE
                    ZON.COD_ZONA
              END COD_ZONA,
              COMUNI.VAL_TEXT_COMU,
              TCOM.COD_TIPO_COMU,
              MP.COD_SAP,
              FORMU.VAL_RUTI_DISE_FORM,
              CORPO1.COD_PERI,
              MONE.VAL_NOMB_CORT_MONE,
              PQ_APL_AUX.Valor_Gen_I18n_Sicc(pnOidIdioma, MP.OID_PROD, 'MAE_PRODU' )  DES_CORT,
              PSP.VAL_CODI_VENT,
              TERRI2.COD_TERR,
              MC.COD_CLIE,
              CASE WHEN (SUBSTR(MCD.COD_UNID_GEOG,19,6) IS NULL) THEN
                ( SELECT VEG.OID_VALO_ESTR_GEOP
                  FROM ZON_VALOR_ESTRU_GEOPO VEG
                  WHERE VEG.ORDE_1 = SUBSTR(MCD.COD_UNID_GEOG,0,6)
                    AND VEG.ORDE_2 = SUBSTR(MCD.COD_UNID_GEOG,7,6)
                    AND VEG.ORDE_3 = SUBSTR(MCD.COD_UNID_GEOG,13,6)
                    AND VEG.ORDE_4 IS NULL )
               ELSE
                ( SELECT VEG.OID_VALO_ESTR_GEOP
                  FROM ZON_VALOR_ESTRU_GEOPO VEG
                  WHERE VEG.ORDE_1 = SUBSTR(MCD.COD_UNID_GEOG,0,6)
                    AND VEG.ORDE_2 = SUBSTR(MCD.COD_UNID_GEOG,7,6)
                    AND VEG.ORDE_3 = SUBSTR(MCD.COD_UNID_GEOG,13,6)
                    AND VEG.ORDE_4 = SUBSTR(MCD.COD_UNID_GEOG,19,6))
                END OID_VALO_ESTR_GEOP,
              PQ_APL_AUX.Valor_Gen_I18n_Sicc(pnOidIdioma, TSP.TSOL_OID_TIPO_SOLI, 'PED_TIPO_SOLIC') DTS
              , case when (psc3.SOCA_OID_DOCU_REFE is not null) then
              	(
                 select corpo3.cod_peri
                   from ped_solic_cabec soli, cra_perio perio3, seg_perio_corpo corpo3
                  where soli.oid_soli_cabe = psc3.SOCA_OID_DOCU_REFE
                    and soli.perd_oid_peri = perio3.oid_peri
                    and perio3.peri_oid_peri = corpo3.oid_peri
                )
                else null
              end PERIREF,
              psc4.val_nume_soli SOLIREF
              ,PSC3.ICTP_OID_TIPO_PROG,
              (SELECT COD_TIPO_PROG FROM INC_CONCU_TIPO_PROG WHERE OID_TIPO_PROG = PSC3.ICTP_OID_TIPO_PROG) COD_TIPO_PROG,
              CASE WHEN ((SELECT COD_TIPO_PROG FROM INC_CONCU_TIPO_PROG WHERE OID_TIPO_PROG = DCC.ICTP_OID_TIPO_PROG) = 'B' ) THEN
                (SELECT 'C' || SUBSTR(SPC.COD_PERI, 5) ||'/'|| SPC.VAL_ANIO FROM INC_CONCU_PARAM_GENER CPG, CRA_PERIO PER, SEG_PERIO_CORPO SPC WHERE CPG.OID_PARA_GRAL = PSC3.COPA_OID_PARA_GENE AND PER.OID_PERI = CPG.PERD_OID_PERI_DESD AND PER.PERI_OID_PERI = SPC.OID_PERI)
              END PERI_INI_CONCU,
              CASE WHEN ((SELECT COD_TIPO_PROG FROM INC_CONCU_TIPO_PROG WHERE OID_TIPO_PROG = DCC.ICTP_OID_TIPO_PROG) = 'B' ) THEN
                (SELECT 'C' || SUBSTR(SPC.COD_PERI, 5) ||'/'|| SPC.VAL_ANIO FROM INC_CONCU_PARAM_GENER CPG, CRA_PERIO PER, SEG_PERIO_CORPO SPC WHERE CPG.OID_PARA_GRAL = PSC3.COPA_OID_PARA_GENE AND PER.OID_PERI = CPG.PERD_OID_PERI_HAST AND PER.PERI_OID_PERI = SPC.OID_PERI)
              END PERI_FIN_CONCU,
              CASE WHEN ((SELECT COD_TIPO_PROG FROM INC_CONCU_TIPO_PROG WHERE OID_TIPO_PROG = DCC.ICTP_OID_TIPO_PROG) = 'B' ) THEN
                (SELECT NUM_CONC FROM INC_CONCU_PARAM_GENER WHERE OID_PARA_GRAL = PSC3.COPA_OID_PARA_GENE)
              END NUM_CONCU
              ,PSP.VAL_CODI_VENT_FICT
              ,LPAD(SUBSTR(PSC1.VAL_NUME_SOLI, 2, 10), 8, 0) COD_BARRAS
              , DCC.VAL_TELE_FIJO TELEF_FIJO
              , DCC.VAL_TELE_CELU TELEF_CEL
              , DCC.VAL_BARR
                ,RTRIM((SELECT DES_GEOG
                 FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = SUBSTR(MCD.COD_UNID_GEOG, 0, 6)
                   AND ORDE_2 IS NULL)) DEPARTAMENTO
                ,(SELECT DES_GEOG
                 FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = SUBSTR(MCD.COD_UNID_GEOG, 0, 6)
                   AND ORDE_2 = SUBSTR(MCD.COD_UNID_GEOG, 7, 6)
                   AND ORDE_3 IS NULL) MUNICIPIO
                ,CASE WHEN ((SELECT COD_ESTA_CLIE FROM MAE_ESTAT_CLIEN MEC WHERE MEC.OID_ESTA_CLIE = DAT.ESTA_OID_ESTA_CLIE) IN ('01', '07')) THEN
                	   		'* PRIMER PEDIDO *'
                 ELSE
                   (SELECT VAL_TEXT_VARI
                   FROM
                	(SELECT CTV.VAL_TEXT_VARI
                  	 FROM APE_CONFI_TEXTO_VARIA CTV,
                	     (
                		  SELECT MTS.TICL_OID_TIPO_CLIE,
                		  	     MTS.SBTI_OID_SUBT_CLIE,
                				 MCC.CLAS_OID_CLAS,
                				 MCC.TCCL_OID_TIPO_CLASI
                		  FROM MAE_CLIEN_TIPO_SUBTI MTS,
                		  	   MAE_CLIEN_CLASI MCC
                		  WHERE MCC.CTSU_OID_CLIE_TIPO_SUBT = MTS.OID_CLIE_TIPO_SUBT
                		    AND MTS.CLIE_OID_CLIE = pnOidReceptor
                		) CLIEN
                	 WHERE CTV.TICL_OID_TIPO_CLIE = CLIEN.TICL_OID_TIPO_CLIE
                	   AND (CTV.SBTI_OID_SUBT_CLIE = CLIEN.SBTI_OID_SUBT_CLIE OR CTV.SBTI_OID_SUBT_CLIE IS NULL)
                	   AND (CTV.CLAS_OID_CLAS = CLIEN.CLAS_OID_CLAS OR CTV.CLAS_OID_CLAS IS NULL)
                	   AND (CTV.TCCL_OID_TIPO_CLAS = CLIEN.TCCL_OID_TIPO_CLASI OR CTV.TCCL_OID_TIPO_CLAS IS NULL)
                	   ORDER BY CTV.TICL_OID_TIPO_CLIE,
                	   	   CTV.SBTI_OID_SUBT_CLIE,
                	   	   CTV.TCCL_OID_TIPO_CLAS,
                	   	   CTV.CLAS_OID_CLAS
                	    ) WHERE ROWNUM = 1)
                  END PRI_PED,
                  PSP.NUM_UNID_COMPR,
                  PSP.FOPA_OID_FORM_PAGO,
                  MC.SAL_DEUD_ANTE,
                  CASE WHEN (DCC.MONE_OID_MONE IS NULL) THEN
                    (
                    SELECT NUM_DECI
                    FROM SEG_PAIS PAIS, SEG_MONED MON
                    WHERE PAIS.MONE_OID_MONE = MON.OID_MONE
                      AND PAIS.OID_PAIS = DCC.PAIS_OID_PAIS
                    )
                  ELSE
                    (
                    SELECT NUM_DECI
                    FROM SEG_MONED MON
                    WHERE MON.OID_MONE = DCC.MONE_OID_MONE
                    )
                  END NUM_DECI
          ,(SELECT NUM_DIAS_CUPO_CRUC
          FROM SEG_PARAM_INTER_PAIS
          WHERE PAIS_OID_PAIS = DCC.PAIS_OID_PAIS
            AND IND_APLI_CUPO_TRES = 1) DIAS_CRUCE,
          (SELECT COD_REGI FROM ZON_REGIO WHERE OID_REGI = ZON.ZORG_OID_REGI) COD_REGI,
          (SELECT COD_SECC FROM ZON_SECCI SEC, ZON_TERRI_ADMIN ZTA WHERE SEC.OID_SECC = ZTA.ZSCC_OID_SECC AND ZTA.TERR_OID_TERR = TERRI1.OID_TERR AND ZTA.IND_BORR <> 1 AND SEC.IND_ACTI = 1) COD_SECC
          ,DCC.VAL_IMPO_IVA_ASUM_EMPR
          ,DCL.IMP_IMPU_TOTA_PROD_NACI
      FROM FAC_DOCUM_CONTA_CABEC DCC,
          FAC_DOCUM_CONTA_LINEA DCL,
          CRA_PERIO PERIO1,
          MAE_CLIEN_DIREC MCD,
          SEG_SOCIE SOCIE,
          ZON_TERRI TERRI1,
          ZON_ZONA ZON,
          MAE_CLIEN_COMUN COMUNI,
          MAE_TIPO_COMUN TCOM,
          MAE_PRODU MP,
          FAC_FORMU FORMU,
          SEG_PERIO_CORPO CORPO1,
          SEG_MONED MONE,
          PED_SOLIC_POSIC PSP,
          ZON_TERRI TERRI2,
          MAE_CLIEN MC,
          PED_SOLIC_CABEC PSC1,
          ped_solic_cabec psc3,
          ped_solic_cabec psc4,
          PED_TIPO_SOLIC_PAIS TSP
          , MAE_CLIEN_DATOS_ADICI DAT
      WHERE DCC.OID_CABE = DCL.DCCA_OID_CABE
       AND (DCC.IND_IMPR <> 1 OR DCC.IND_IMPR IS NULL)
       AND DCC.SOCA_OID_SOLI_CABE = pnOidSolicitud
       AND DCC.PERD_OID_PERI = PERIO1.OID_PERI
       AND TERRI2.OID_TERR = MCD.TERR_OID_TERR
       AND MCD.OID_CLIE_DIRE = DCC.CLDI_OID_CLIE_DIRE
       AND SOCIE.OID_SOCI = DCC.SOCI_OID_SOCI
       AND TERRI1.OID_TERR = DCC.TERR_OID_TERR
       AND ZON.OID_ZONA = DCC.ZZON_OID_ZONA
       AND MC.OID_CLIE = pnOidReceptor
       AND COMUNI.CLIE_OID_CLIE(+) = MC.OID_CLIE
       AND TCOM.OID_TIPO_COMU(+) = COMUNI.TICM_OID_TIPO_COMU
       AND COMUNI.IND_COMU_PPAL(+) = 1
       AND MP.OID_PROD = DCL.PROD_OID_PROD
       AND FORMU.OID_FORM = DCC.FORS_OID_FORM
       AND DCC.PERD_OID_PERI = PERIO1.OID_PERI
       AND CORPO1.OID_PERI = PERIO1.PERI_OID_PERI
       AND PSP.NUM_UNID_ATEN <> 0
       AND PSP.OID_SOLI_POSI = DCL.SOPO_OID_SOLI_POSI
       AND MONE.OID_MONE(+) = DCC.MONE_OID_MONE
       AND PSC1.OID_SOLI_CABE = DCC.SOCA_OID_SOLI_CABE
       AND PSC1.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
       AND psp.soca_oid_soli_cabe = psc3.oid_soli_cabe
       AND psc3.soca_oid_docu_refe = psc4.oid_soli_cabe(+)
       AND DAT.CLIE_OID_CLIE = MC.OID_CLIE
     ORDER BY DCC.OID_CABE,
              DCL.NUM_LINEA;

  END FAC_PR_OBTIE_DOCUM_CONTA;


  /**************************************************************************
  Descripcion        : Obtiene informacion de los documentos no legales del cliente
  Fecha Creacion     :  22/03/2011
  Parametros Entrada :
             pnOidSolicitud : oid Solicitud
             pnOidReceptor : oid Receptor
             pnOidPeriodo : oid Periodo
             pnOidRegion : oid Region
             pnOidZona : oid Zona
             pnOidSeccion : oid Seccion
             pnOidTerritorio : oid Territorio
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE FAC_PR_OBTIE_DOCUM_NOLEG(pnOidSolicitud       NUMBER,
                                     pnOidReceptor        NUMBER,
                                     pnOidPeriodo         NUMBER,
                                     pnOidRegion          NUMBER,
                                     pnOidZona            NUMBER,
                                     pnOidSeccion         NUMBER,
                                     pnOidTerritorio      NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
      SELECT C.NUM_PPAL,
             C.VAL_NOMB_VIA,
             D.DES_ABRV_TIPO_VIA,
             E.COD_ZONA,
             I.COD_SECC,
             J.COD_TERR,
             F.VAL_APE1,
             F.VAL_APE2,
             F.VAL_NOM1,
             F.VAL_NOM2,
             G.VAL_NOMB_PERI,
             H.VAL_TEXT_COMU,
             R.COD_REGI,
             S.COD_PERI,
             P.NUM_UNID_ATEN_TOTA,
             F.COD_CLIE,
             G.OID_PERI,
             E.OID_ZONA,
             PIP.IND_IMPR_PROD_FUER_CAJA_BOLS
        FROM MAE_CLIEN_DIREC      C,
             SEG_TIPO_VIA         D,
             ZON_ZONA             E,
             MAE_CLIEN            F,
             CRA_PERIO            G,
             MAE_CLIEN_COMUN      H,
             ZON_SECCI            I,
             ZON_TERRI            J,
             ZON_REGIO            R,
             SEG_PERIO_CORPO      S,
             PED_SOLIC_CABEC      P,
             SEG_PARAM_INTER_PAIS PIP
       WHERE C.OID_CLIE_DIRE = P.CLDI_OID_CLIE_DIRE(+)
         AND P.OID_SOLI_CABE = pnOidSolicitud
         AND C.TIVI_OID_TIPO_VIA = D.OID_TIPO_VIA
         AND E.OID_ZONA = pnOidZona
         AND F.OID_CLIE = pnOidReceptor
         AND G.OID_PERI = pnOidPeriodo
         AND H.CLIE_OID_CLIE(+) = F.OID_CLIE
         AND H.TICM_OID_TIPO_COMU(+) = 1
         AND I.OID_SECC = pnOidSeccion
         AND J.OID_TERR = pnOidTerritorio
         AND R.OID_REGI = pnOidRegion
         AND s.OID_PERI = g.PERI_OID_PERI
         AND P.PAIS_OID_PAIS = PIP.PAIS_OID_PAIS(+);

  END FAC_PR_OBTIE_DOCUM_NOLEG;


  /**************************************************************************
  Descripcion        : Obtiene datos para la impresion de los mensajes dirigidos
                       para un determinado cliente
  Fecha Creacion     :  22/03/2011
  Parametros Entrada :
             pnOidSolicitud : oid Solicitud
             pnOidCliente : oid Cliente
             pnOidPeriodo : oid Periodo
             psAmbitoGeografico : 'T' : Territorio, 'Z':Zona, 'S':Seccion, 'R':Region
             pnOidTerritorio : oid Territorio
             pnOidSeccion  : oid Seccion
             pnOidZona  : oid Zona
             pnOidRegion : oid Region
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE MSG_PR_OBTIE_DATOS_IMPRE(pnOidPais            NUMBER,
                                     pnOidCliente         NUMBER,
                                     pnOidPeriodo         NUMBER,
                                     psAmbitoGeografico   VARCHAR2,
                                     pnOidTerritorio      NUMBER,
                                     pnOidSeccion         NUMBER,
                                     pnOidZona            NUMBER,
                                     pnOidRegion          NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
     SELECT mensa.oid_mens, mensa.val_text, mensa.tper_oid_tipo_perm, mensa.fec_perm_desd,
            mensa.fec_perm_hast, mensa.tmen_oid_tipo_mens, mensa.cod_peri,
            mensa.cod_peri2, mensa.cod_peri3, mensa.tide_oid_tipo_dest,
            buzon.oid_buzo_mens, buzon.clie_oid_clie, buzon.num_secu, buzon.dato_vari_01,
            buzon.dato_vari_02, buzon.dato_vari_03, buzon.dato_vari_04, buzon.dato_vari_05,
            buzon.dato_vari_06, buzon.dato_vari_07, buzon.dato_vari_08, buzon.dato_vari_09,
            buzon.dato_vari_10, buzon.dato_vari_11, buzon.dato_vari_12, buzon.dato_vari_13,
            buzon.dato_vari_14, buzon.dato_vari_15, buzon.dato_vari_16, buzon.dato_vari_17,
            buzon.dato_vari_18, buzon.dato_vari_19, buzon.dato_vari_20, buzon.ind_esta_mens,
            buzon.modu_oid_modu_orig, buzon.val_nom1_clie, buzon.val_nom2_clie,
            buzon.val_ape1_clie, buzon.val_ape2_clie, buzon.val_apel_casa_clie,
            buzon.ind_list_cons, buzon.ind_acti, mensa.OID_PERI, mensa.ind_excl_tipo,
            ( SELECT COUNT (1)
                FROM msg_mensa_unida_admin mu
               WHERE mu.mens_oid_mens =  mensa.oid_mens
                 AND ((psAmbitoGeografico = 'T'
                        AND ((mu.TERR_OID_TERR = pnOidTerritorio AND mu.ZSCC_OID_SECC = pnOidSeccion
                             AND mu.ZZON_OID_ZONA = pnOidZona AND mu.ZORG_OID_REGI = pnOidRegion )
                        OR (mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC = pnOidSeccion
                            AND mu.ZZON_OID_ZONA = pnOidZona AND mu.ZORG_OID_REGI = pnOidRegion)
                        OR (mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC IS NULL
                            AND mu.ZZON_OID_ZONA = pnOidZona AND mu.ZORG_OID_REGI = pnOidRegion)
                        OR (mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC IS NULL
                            AND mu.ZZON_OID_ZONA IS NULL AND mu.ZORG_OID_REGI = pnOidRegion)))

                      OR (psAmbitoGeografico = 'S'
                        AND ((mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC = pnOidSeccion
                            AND ZZON_OID_ZONA = pnOidZona AND mu.ZORG_OID_REGI = pnOidRegion)
                        OR (mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC IS NULL
                            AND ZZON_OID_ZONA = pnOidZona AND mu.ZORG_OID_REGI = pnOidRegion)
                        OR (mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC IS NULL AND mu.ZZON_OID_ZONA IS NULL
                            AND mu.ZORG_OID_REGI = pnOidRegion)))

                      OR (psAmbitoGeografico = 'Z'
                        AND ((mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC IS NULL
                            AND mu.ZZON_OID_ZONA = pnOidZona AND mu.ZORG_OID_REGI = pnOidRegion)
                        OR (mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC IS NULL AND mu.ZZON_OID_ZONA IS NULL
                            AND mu.ZORG_OID_REGI = pnOidRegion)))

                      OR (psAmbitoGeografico = 'R'
                        AND (mu.TERR_OID_TERR IS NULL AND mu.ZSCC_OID_SECC IS NULL AND mu.ZZON_OID_ZONA IS NULL
                            AND mu.ZORG_OID_REGI = pnOidRegion))

                        )) AS contador
       FROM
           (SELECT c1.oid_peri ,a.oid_mens, a.val_text, a.tper_oid_tipo_perm, a.fec_perm_desd,
                   a.fec_perm_hast, a.tmen_oid_tipo_mens, c1.oid_peri cod_peri,
           		 d.oid_peri cod_peri2, e.oid_peri cod_peri3, b.tide_oid_tipo_dest, b.oid_mens_tipo_dest, a.ind_excl_tipo
             FROM msg_mensa_tipo_asign b,
                  cra_perio c,
                  seg_perio_corpo c1,
                  seg_perio_corpo d,
                  seg_perio_corpo e,
                  msg_mensa a
             WHERE a.oid_mens = b.mens_oid_mens
              AND c.oid_peri = pnOidPeriodo
              AND a.pais_oid_pais = pnOidPais
              AND c.peri_oid_peri = c1.oid_peri
              AND a.peri_oid_peri_desd = d.oid_peri(+)
              AND a.peri_oid_peri_hast = e.oid_peri(+) ) mensa,
           (SELECT f.MENS_OID_MENS,f.oid_buzo_mens, f.clie_oid_clie, f.num_secu, f.dato_vari_01,
                   f.dato_vari_02, f.dato_vari_03, f.dato_vari_04, f.dato_vari_05,
                   f.dato_vari_06, f.dato_vari_07, f.dato_vari_08, f.dato_vari_09,
                   f.dato_vari_10, f.dato_vari_11, f.dato_vari_12, f.dato_vari_13,
                   f.dato_vari_14, f.dato_vari_15, f.dato_vari_16, f.dato_vari_17,
                   f.dato_vari_18, f.dato_vari_19, f.dato_vari_20, f.ind_esta_mens,
                   f.modu_oid_modu_orig, f.val_nom1_clie, f.val_nom2_clie,
                   f.val_ape1_clie, f.val_ape2_clie, f.val_apel_casa_clie,
                   f.ind_list_cons, f.ind_acti
              FROM msg_buzon_mensa f
              WHERE f.clie_oid_clie = pnOidCliente AND f.ind_acti = 1) buzon
      WHERE mensa.oid_mens = buzon.MENS_OID_MENS(+)
      ORDER BY mensa.oid_mens,mensa.oid_mens_tipo_dest,buzon.num_secu;

  END MSG_PR_OBTIE_DATOS_IMPRE;


  /**************************************************************************
  Descripcion        : Obtiene informacion del patron de mensaje
  Fecha Creacion     :  22/03/2011
  Parametros Entrada :
             pnOidPatron : oid Receptor
             pnOidIdioma : oid Idioma
             prCursor : cursor de Devolucion de Datos
  Autor              : Sergio Apaza
  ***************************************************************************/
  PROCEDURE MSG_PR_OBTIE_PATRO_DOCUM(pnOidPatron          NUMBER,
                                     pnOidIdioma          NUMBER,
                                     prCursor             OUT ref_cursor)
  IS
  BEGIN
    OPEN prCursor for
      SELECT A.OID_PATR,
             A.COD_PATR,
             A.DES_PATR,
             A.FORS_OID_FORM,
             B.COD_FORM,
             A.MEEP_OID_MEDI_ENVI_PAIS,
             D.DES_MEDI_ENVI,
             A.IND_ACTI,
             A.IND_PATR_PERI,
             DECODE(A.IND_PATR_PERI,
                    1,
                    (SELECT E.PATR_OID_PATR_ORIG
                       FROM MSG_PATRO_PERIO E
                      WHERE E.PATR_OID_PATR = A.OID_PATR),
                    NULL) PATR_OID_PATR_ORIG,
             DECODE(A.IND_PATR_PERI,
                    1,
                    (SELECT E.PERI_OID_PERI
                       FROM MSG_PATRO_PERIO E
                      WHERE E.PATR_OID_PATR = A.OID_PATR),
                    NULL) PERI_OID_PERI,
             DECODE(A.IND_PATR_PERI,
                    1,
                    (SELECT E.OID_PATR_PERI
                       FROM MSG_PATRO_PERIO E
                      WHERE E.PATR_OID_PATR = A.OID_PATR),
                    NULL) OID_PATR_PERI,
             A.PAIS_OID_PAIS,
             F.PATR_OID_PATR,
             F.OID_PATR_SECC,
             F.COD_SECC,
             F.NUM_ORDE_SECC,
             F.METC_OID_META,
             G.VAL_LITE_IDEN,
             H.VAL_I18N,
             I.MENS_OID_MENS,
             I.NUM_ORDE_IMPR,
             J.VAL_TEXT
        FROM MSG_PATRO            A,
             FAC_FORMU            B,
             MSG_MEDIO_ENVIO_PAIS C,
             MSG_MEDIO_ENVIO      D,
             MSG_PATRO_SECCI      F,
             MSG_METAC            G,
             V_GEN_I18N_SICC      H,
             MSG_PATRO_MENSA      I,
             MSG_MENSA            J
       WHERE A.OID_PATR = pnOidPatron
         AND A.IND_ACTI = 1
         AND B.OID_FORM = A.FORS_OID_FORM
         AND C.OID_MEDI_ENVI_PAIS = A.MEEP_OID_MEDI_ENVI_PAIS
         AND D.OID_MEDI_ENVI = C.MEEN_OID_MEDI_ENVI
         AND F.PATR_OID_PATR = A.OID_PATR
         AND F.METC_OID_META = G.OID_META(+)
         AND G.OID_META = H.VAL_OID(+)
         AND H.ATTR_ENTI(+) = 'MSG_METAC'
         AND H.IDIO_OID_IDIO(+) = pnOidIdioma
         AND I.PASE_OID_PATR_SECC = F.OID_PATR_SECC
         AND J.OID_MENS = I.MENS_OID_MENS
       ORDER BY F.NUM_ORDE_SECC, I.NUM_ORDE_IMPR;

  END MSG_PR_OBTIE_PATRO_DOCUM;


END FAC_PKG_PROCE;
/

