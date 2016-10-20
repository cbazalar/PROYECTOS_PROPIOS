CREATE OR REPLACE PACKAGE "VEN_PKG_REPOR_RUNIC" IS
  /* Declaracion de Tipos */
  TYPE TIPOCURSOR IS  REF CURSOR;

  TYPE tRegReporteRegistroVentaResum IS RECORD (
     BOL_DESP                VARCHAR2(50),
     COD_CLIE                VARCHAR2(100),
     TIDO_OID_TIPO_DOCU      VARCHAR2(50),
     VAL_SERI_DOCU_LEGA      VARCHAR2(50),
     VAL_NUME_DOCU_LEGA      VARCHAR2(50),
     VAL_NUME_IDEN_FISC      VARCHAR2(50),
     TIDO_TIPO_DOCU_REFE     VARCHAR2(50),
     VAL_SERI_DOCU_REFE      VARCHAR2(50),
     VAL_NUME_DOCU_REFE      VARCHAR2(50),
     DOC_INI                 VARCHAR2(50),
     DOC_FINA                VARCHAR2(50),
     VAL_BASE_IMPO           VARCHAR2(50),
     VAL_DESC                VARCHAR2(50),
     VAL_BASE_IMPO_NETO      VARCHAR2(50),
     OPINAF                  VARCHAR2(50),
     IMP_IMPU                VARCHAR2(50),
     IMP_TOTA                VARCHAR2(50),
     IND_ESTA                VARCHAR2(50),
     IND_TRAN_GRAT           VARCHAR2(50),
     DOC_INI_AUX             NUMBER,
     DOC_FINA_AUX            NUMBER,
     VAL_BASE_IMPO_AUX       FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO%TYPE,
     VAL_DESC_AUX            FAC_REGIS_UNICO_VENTA.VAL_DESC%TYPE,
     VAL_BASE_IMPO_NETO_AUX  FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO_NETO%TYPE,
     OPINAF_AUX              NUMBER,
     IMP_IMPU_AUX            FAC_REGIS_UNICO_VENTA.IMP_IMPU%TYPE,
     IMP_TOTA_AUX            FAC_REGIS_UNICO_VENTA.IMP_TOTA%TYPE,
     FEC_EMISION             FAC_REGIS_UNICO_VENTA.FEC_EMIS%TYPE,
     DCCA_OID_CABE           FAC_REGIS_UNICO_VENTA.DCCA_OID_CABE%TYPE ,
     SBAC_OID_SBAC           FAC_REGIS_UNICO_VENTA.SBAC_OID_SBAC%TYPE
   );

   TYPE tRegReporteRegistroVentaDet IS RECORD (
     BOL_DESP             VARCHAR2(50),
     TIDO_OID_TIPO_DOCU   VARCHAR2(50),
     VAL_SERI_DOCU_LEGA   VARCHAR2(50),
     VAL_NUME_DOCU_LEGA   VARCHAR2(50),
     VAL_NUME_IDEN_FISC   VARCHAR2(50),
     COD_CLIE             VARCHAR2(50),
     NOMBRE               VARCHAR2(100),
     NUM_DOCU_IDEN        VARCHAR2(100),
     TIDO_TIPO_DOCU_REFE  VARCHAR2(50),
     VAL_SERI_DOCU_REFE   VARCHAR2(50),
     VAL_NUME_DOCU_REFE   VARCHAR2(50),
     VAL_BASE_IMPO        VARCHAR2(50),
     VAL_DESC             VARCHAR2(50),
     VAL_BASE_IMPO_NETO   VARCHAR2(50),
     OPINAF               VARCHAR2(50),
     IMP_IMPU             VARCHAR2(50),
     IMP_TOTA             VARCHAR2(50),
     IND_ESTA             VARCHAR2(50),
     IND_TRAN_GRAT        VARCHAR2(50),
     VAL_BASE_IMPO_AUX    FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO%TYPE,
     VAL_DESC_AUX         FAC_REGIS_UNICO_VENTA.VAL_DESC%TYPE,
     VAL_BASE_IMPO_NETO_AUX FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO_NETO%TYPE,
     OPINAF_AUX           NUMBER,
     IMP_IMPU_AUX         FAC_REGIS_UNICO_VENTA.IMP_IMPU%TYPE,
     IMP_TOTA_AUX         FAC_REGIS_UNICO_VENTA.IMP_TOTA%TYPE,
     CLIE_OID_CLIE        NUMBER(12),
     DCCA_OID_CABE        FAC_REGIS_UNICO_VENTA.DCCA_OID_CABE%TYPE,
     SBAC_OID_SBAC        FAC_REGIS_UNICO_VENTA.SBAC_OID_SBAC%TYPE
   );

   TYPE tRegReporteRegistroVentaConsol IS RECORD (
     SIS_EMI              VARCHAR2(50),
     PUNTO_EMI            VARCHAR2(50),
     TIDO_OID_TIPO_DOCU   VARCHAR2(50),
     VAL_SERI_DOCU_LEGA   VARCHAR2(50),
     TI_OID_TIPO          VARCHAR2(50),
     VAL_NUM_DOC          VARCHAR2(50),
     DOC_INI              VARCHAR2(50),
     DOC_FINA             VARCHAR2(50),
     CANT_DOC             NUMBER,
     DOC_NO_REG           NUMBER,
     CANT_DOC_ANUL        NUMBER,
     VAL_BASE_IMPO        FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO%TYPE,
     VAL_DESC             FAC_REGIS_UNICO_VENTA.VAL_DESC%TYPE,
     VAL_BASE_IMPO_NETO   FAC_REGIS_UNICO_VENTA.VAL_BASE_IMPO_NETO%TYPE,
     OPINAF               NUMBER,
     IMP_IMPU             FAC_REGIS_UNICO_VENTA.IMP_IMPU%TYPE,
     IMP_TOTA             FAC_REGIS_UNICO_VENTA.IMP_TOTA%TYPE,
     SUM_TRA_GRAT         FAC_REGIS_UNICO_VENTA.IMP_IMPU%TYPE
   );

  TYPE tRegReporteRegVentaDetNotaC IS RECORD (
     codigoCliente       mae_clien.cod_clie%TYPE,
     nombreCliente       VARCHAR2(100),
     fechaEmision        fac_regis_unico_venta.fec_emis%TYPE,
     fechaContable       fac_docum_conta_cabec.fec_cont%TYPE,
     tasaImpuesto        ped_tasa_impue.val_tasa_impu%TYPE,
     baseImponibleNeta   fac_regis_unico_venta.val_base_impo_neto%TYPE,
     baseImponible       fac_regis_unico_venta.val_base_impo%TYPE,
     numeroFacturas      NUMBER,
     numeroInterno       fac_regis_unico_venta.val_docu_inte%TYPE
   );

  TYPE tRegReporteRegVentaDetVentas IS RECORD (
     codigoCliente               mae_clien.cod_clie%TYPE,
     numeroIdentidadFiscal       fac_regis_unico_venta.val_nume_iden_fisc%TYPE,
     nombrecliente               VARCHAR2(100),
     fechaemision                fac_regis_unico_venta.fec_emis%TYPE,
     fechacontable               fac_docum_conta_cabec.fec_cont%TYPE,
     porcIVA                     ped_tasa_impue.val_tasa_impu%TYPE,
     valorIVA                    fac_regis_unico_venta.imp_impu%TYPE,
     baseimponibleNeta           fac_regis_unico_venta.val_base_impo_neto%TYPE,
     baseImpoGravadaServ         VARCHAR2(20),
     baseimponibleTarifa         fac_regis_unico_venta.val_flet%TYPE,
     numeroFactura               fac_regis_unico_venta.val_nume_docu_lega%TYPE
   );




  TYPE tablaRegVenResumen IS TABLE OF tRegReporteRegistroVentaResum;
  TYPE tablaRegVenDetalle IS TABLE OF tRegReporteRegistroVentaDet;
  TYPE tablaRegConsolidado IS TABLE OF tRegReporteRegistroVentaConsol;
  TYPE tRegIDPeriodo IS RECORD (
     OID_PERI    CRA_PERIO.OID_PERI%TYPE
   );
  TYPE TABLA_OID_PERIODO IS TABLE OF tRegIDPeriodo ;
  TYPE tablaRegVentaDetNotaC IS TABLE OF tRegReporteRegVentaDetNotaC;
  TYPE tablaRegVentaDetVenta IS TABLE OF tRegReporteRegVentaDetVentas;

  /* Declaracion de Variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Devuelve Comprobante de Pago formateado a N ceros a
                    la izquierda
Fecha Creacion    : 27/11/2007
Autor             : Carlos Bazalar La Rosa
Parametros        :
           pnValNumeDocum:  Numero de Comprobante de Pago
           pnValFormato:    Numero de Ceros a formatear de derecha a
                            Izquierda
***************************************************************************/
FUNCTION VEN_FN_DEVUE_COMPR_PAGO(
    pnValNumeDocum NUMBER,
    pnValFormato   NUMBER)
RETURN VARCHAR2;


/***************************************************************************
Descripcion       : Obtiene el monto importe Neto, verificando si es inafecto la
                    tasa
Fecha Creacion    : 26/07/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_IMPOR_NETO(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
  RETURN  NUMBER;
/***************************************************************************
Descripcion       : Obtiene el monto Base Importe,verificando si es inafecto la
                    tasa
Fecha Creacion    : 26/07/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_BASE_IMPOR(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
    RETURN  NUMBER ;
/***************************************************************************
Descripcion       : Obtiene el monto de las operaciones inafectas
Fecha Creacion    : 26/07/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_OPERA_INAFE(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
   RETURN  NUMBER  ;
/***************************************************************************
Descripcion       : Devuelve Listado de Resumen de Ventas
Fecha Creacion    : 21/02/2007
Autor             : Jose Nunez Mori
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VENTA_RESUM(
    psCodPais    VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin   VARCHAR2
    )
RETURN  tablaRegVenResumen PIPELINED;
/***************************************************************************
Descripcion       : Devuelve Listado de Registro de Resumen de Ventas Detallado
Fecha Creacion    : 21/02/2007
Autor             : Jose Nunez Mori
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VENTA_DETAL(
    psCodPais    VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin   VARCHAR2
    )
RETURN  tablaRegVenDetalle PIPELINED;
/***************************************************************************
Descripcion       : Devuelve el codigo de Homologacion en Sunat de un documento
                    Inicial y final
Fecha Creacion    : 26/01/2007
Autor             : Jose Nunez M.
***************************************************************************/
FUNCTION VEN_FN_DEVUE_HOMO_SUNAT(
   pnOidTipoDoc FAC_TIPO_DOCUM.Oid_Tipo_Docu%TYPE,
   pnOidPais FAC_REGIS_UNICO_VENTA.PAIS_OID_PAIS%TYPE
)
RETURN VARCHAR2;



/***************************************************************************
Descripcion       : Devuelve el codigo de homologacion de sunat de un documento
Fecha Creacion    : 26/02/2007
Autor             : Jose Nunez M.
***************************************************************************/
FUNCTION VEN_FN_DEVUE_HOMO_SUNAT(
   pnOidTipoDoc FAC_TIPO_DOCUM.Oid_Tipo_Docu%TYPE
)
RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Devuelve el un documento legal, conformado por el oid del tipo
                    de docuemto y su numero
Fecha Creacion    : 26/01/2007
Autor             : Jose Nunez M.
***************************************************************************/
FUNCTION VEN_FN_DEVUE_DOCUM_LEGAL(
   pnOidTipoDoc FAC_TIPO_DOCUM.Oid_Tipo_Docu%TYPE,
   pnOidPais FAC_REGIS_UNICO_VENTA.PAIS_OID_PAIS%TYPE,
   pnValNumDoc FAC_REGIS_UNICO_VENTA.VAL_NUME_DOCU_LEGA%TYPE
)
RETURN VARCHAR2;
/***************************************************************************
Descripcion       :  Procedimiento que carga una tabla temporal, para ser usada
                     por el reporte de registro de venta consolidado.
Fecha Creacion    : 26/01/2007
Autor             : Jose Nunez M.
***************************************************************************/
PROCEDURE VEN_PR_CARGA_VENTA_CONSO(
    psCodPais    VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin   VARCHAR2
);

/***************************************************************************
Descripcion       : Devuelve Listado de Registro de Resumen de Ventas Detallado
                    de Notas de Crédito
Fecha Creacion    : 07/01/2008
Autor             : Jose A. Cairampoma
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VENTA_DETAL_NOTAC(
    psCodPais     VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin    VARCHAR2)
RETURN tablaRegVentaDetNotaC PIPELINED;

/***************************************************************************
Descripcion       : Devuelve Listado de Registro de Resumen de Ventas Detallado
                    de ventas
Fecha Creacion    : 07/01/2008
Autor             : Jose A. Cairampoma
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VENTA_DETAL_VENTA(
    psCodPais     VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin    VARCHAR2)
RETURN tablaRegVentaDetVenta PIPELINED;

END VEN_PKG_REPOR_RUNIC;
/
CREATE OR REPLACE PACKAGE BODY "VEN_PKG_REPOR_RUNIC"
IS

/***************************************************************************
Descripcion       : Devuelve Comprobante de Pago formateado a N ceros a
                    la izquierda
Fecha Creacion    : 27/11/2007
Autor             : Carlos Bazalar La Rosa
Parametros        :
           pnValNumeDocum:  Numero de Comprobante de Pago
           pnValFormato:    Numero de Ceros a formatear de derecha a
                            Izquierda
***************************************************************************/
FUNCTION VEN_FN_DEVUE_COMPR_PAGO(
    pnValNumeDocum NUMBER,
    pnValFormato   NUMBER)
RETURN VARCHAR2
IS
   lsCadena VARCHAR2(100);
BEGIN
   lsCadena := TRIM(to_char(pnValNumeDocum));
   IF length(lsCadena) <= pnValFormato THEN
      lsCadena := to_char(pnValNumeDocum,'00000000');
   ELSE
      lsCadena := substr(lsCadena, length(lsCadena) - pnValFormato + 1, pnValFormato);
   END IF;
   RETURN lsCadena;
END VEN_FN_DEVUE_COMPR_PAGO;


/***************************************************************************
Descripcion       : Devuelve Listado  de Registro de Resumen de Venta
Fecha Creacion    : 23/02/2007
Autor             : Jose Nunez M.
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VENTA_RESUM(
    psCodPais     VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin    VARCHAR2)
RETURN  tablaRegVenResumen PIPELINED
IS
  tablaRegistro   tablaRegVenResumen;
  REG tRegReporteRegistroVentaResum;

  CURSOR cursorRegistro1
  (vnIdPais NUMBER, vsFechaini VARCHAR2, vsFechafin VARCHAR2, vnBol NUMBER, vnTick NUMBER, vnBolPre NUMBER)
  IS
      SELECT
           TO_CHAR(F.OID_REGI) AS BOLDES,
           '' AS COD_CLIE,
           VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(F.TIDO_OID_TIPO_DOCU,F.PAIS_OID_PAIS) AS DOC_LEG,
           F.VAL_SERI_DOCU_LEGA,
           VEN_FN_DEVUE_COMPR_PAGO(F.VAL_NUME_DOCU_LEGA,8) AS VAL_NUME_DOCU_LEGA,
           F.VAL_NUME_IDEN_FISC,
           VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(F.TIDO_TIPO_DOCU_REFE,F.PAIS_OID_PAIS) AS DOC_REF,
           F.VAL_SERI_DOCU_REFE,
           F.VAL_NUME_DOCU_REFE,
           '' AS DOC_INI,
           '' AS DOC_FINA,
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_BASE_IMPOR(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO ) AS VAL_BASE_IMPO,
           DECODE (F.TAIM_OID_TASA_IMPU,2002,0,ABS(F.VAL_DESC)) VAL_DESC,
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_IMPOR_NETO(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO_NETO) AS VAL_BASE_IMPO_NETO,
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_OPERA_INAFE(F.TAIM_OID_TASA_IMPU, abs(F.VAL_BASE_IMPO)) as OPINAF,
           ABS(F.IMP_IMPU) AS IMP_IMPU,
           ABS(F.IMP_TOTA) AS IMP_TOTA,
           F.IND_ESTA,
           F.IND_TRAN_GRAT,
           '' AS DOC_INI_AUX,
           '' AS DOC_FINA_AUX,
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_BASE_IMPOR(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO ) AS VAL_BASE_IMPO_AUX,
           DECODE (F.TAIM_OID_TASA_IMPU,2002,0, ABS(F.VAL_DESC)) VAL_DESC_AUX,
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_IMPOR_NETO(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO_NETO) AS VAL_BASE_IMPO_NETO_AUX,
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_OPERA_INAFE(F.TAIM_OID_TASA_IMPU, abs(F.VAL_BASE_IMPO)) as OPINAF_AUX,
           ABS(F.IMP_IMPU) as IMP_IMPU_AUX,
           ABS(F.IMP_TOTA) AS IMP_TOTA_AUX,
           F.FEC_EMIS AS FEC_EMISION,
           F.DCCA_OID_CABE,
           F.SBAC_OID_SBAC
      FROM FAC_REGIS_UNICO_VENTA F
      WHERE F.PAIS_OID_PAIS=vnIdPais
        AND (F.FEC_EMIS >= TO_DATE(vsFechaini,'DD/MM/YYYY') AND F.FEC_EMIS <= TO_DATE(vsFechafin,'DD/MM/YYYY'))
        AND F.TIDO_OID_TIPO_DOCU NOT  IN (vnBol,vnTick,vnBolPre)

      ORDER BY F.FEC_EMIS, DOC_LEG, F.VAL_SERI_DOCU_LEGA, F.VAL_NUME_DOCU_LEGA ;

  CURSOR cursorRegistro2
  (vnIdPais NUMBER, vsFechaini VARCHAR2, vsFechafin VARCHAR2, vnBol NUMBER, vnTick NUMBER, vnBolPre NUMBER)
  IS
        SELECT
           '' AS BOLDES,
           '' AS COD_CLIE,
           VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(F.TIDO_OID_TIPO_DOCU,F.PAIS_OID_PAIS) AS DOC_LEG,
           F.VAL_SERI_DOCU_LEGA,
           '' AS VAL_NUME_DOCU_LEGA,
           '' AS VAL_NUME_IDEN_FISC,
           '' AS  TIDO_TIPO_DOCU_REFE,
           '' AS  VAL_SERI_DOCU_REFE,
           '' AS VAL_NUME_DOCU_REFE,
           VEN_FN_DEVUE_COMPR_PAGO(MIN(F.VAL_NUME_DOCU_LEGA),8) AS MIN_DOC,
           VEN_FN_DEVUE_COMPR_PAGO(MAX (VAL_NUME_DOCU_LEGA),8) AS MAX_DOC,
           SUM(VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_BASE_IMPOR(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO )) AS VAL_BASE_IMPO,
           SUM(ABS(F.VAL_DESC)) AS VAL_DESC,
           SUM(VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_IMPOR_NETO(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO_NETO)) AS VAL_BASE_IMPO_NETO,
           SUM(VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_OPERA_INAFE(F.TAIM_OID_TASA_IMPU, abs(F.VAL_BASE_IMPO))) as OPINAF,
           SUM(ABS(F.IMP_IMPU)) AS IGV,
           SUM(ABS(F.IMP_TOTA)) AS IMP_TOT,
           '' AS IND_ESTA,
           '' AS IND_FACT_GRAT,
           VEN_FN_DEVUE_COMPR_PAGO(MIN(F.VAL_NUME_DOCU_LEGA),8) AS MIN_DOC,
           VEN_FN_DEVUE_COMPR_PAGO(MAX(F.VAL_NUME_DOCU_LEGA),8) AS MAX_DOC,
           SUM(VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_BASE_IMPOR(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO )) AS VAL_BASE_IMPO,
           SUM(ABS(F.VAL_DESC)) AS VAL_DESC,
           SUM(VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_IMPOR_NETO(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO_NETO)) AS VAL_BASE_IMPO_NETO,
           SUM(VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_OPERA_INAFE(F.TAIM_OID_TASA_IMPU, abs(F.VAL_BASE_IMPO))) as OPINAF,
           SUM(ABS(F.IMP_IMPU)) AS IGV,
           SUM(ABS(F.IMP_TOTA)) AS IMP_TOT,
           F.FEC_EMIS AS FEC_EMISION,
           NULL,
           NULL
        FROM FAC_REGIS_UNICO_VENTA F
        WHERE
            F.PAIS_OID_PAIS = vnIdPais
            AND (F.FEC_EMIS >= TO_DATE(vsFechaini,'DD/MM/YYYY') AND F.FEC_EMIS <= TO_DATE(vsFechafin,'DD/MM/YYYY'))
            AND F.TIDO_OID_TIPO_DOCU IN (vnBol,vnTick,vnBolPre)
       GROUP BY F.FEC_EMIS, F.TIDO_OID_TIPO_DOCU, F.VAL_SERI_DOCU_LEGA, VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(F.TIDO_OID_TIPO_DOCU,F.PAIS_OID_PAIS);

 --Declaracion de variables a usar
 lnIdPais         SEG_PAIS.OID_PAIS%TYPE;
 lnBol            FAC_TIPO_DOCUM.OID_TIPO_DOCU%TYPE;
 lntick           FAC_TIPO_DOCUM.OID_TIPO_DOCU%TYPE;
 lnBolPre         FAC_TIPO_DOCUM.OID_TIPO_DOCU%TYPE;
 lsCodSbac        SEG_SUBAC.Cod_Sbac%TYPE;

 BEGIN
    /* obteniendo id's */
   lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);-- id del pais consultante

   -- obtiene el oid de la boleta de venta
   SELECT T.OID_TIPO_DOCU INTO lnBol
   FROM FAC_TIPO_DOCUM T
   WHERE T.DES_TIPO_DOCU='Boleta Venta';

   -- obtiene el oid de la boleta de venta Premios
   SELECT T.OID_TIPO_DOCU INTO lnBolPre
   FROM FAC_TIPO_DOCUM T
   WHERE T.DES_TIPO_DOCU='Boleta Venta Premios';

   -- obtiene el oid del ticket
   SELECT T.OID_TIPO_DOCU INTO lntick
   FROM FAC_TIPO_DOCUM T
   WHERE T.DES_TIPO_DOCU='Ticket';

   --PRIMERA VALIDACION
   OPEN cursorRegistro1(lnIdPais, psFechaInicio, psFechaFin, lnBol,lntick,lnBolPre);
      LOOP
           FETCH cursorRegistro1 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 REG :=tablaRegistro(x);

                 /* Obteniendo el numero de solicitud */
                 BEGIN
                     SELECT TO_CHAR(S.VAL_NUME_SOLI)
                     INTO REG.BOL_DESP
                     FROM FAC_REGIS_UNICO_VENTA F,
                          FAC_DOCUM_CONTA_CABEC D,
                          PED_SOLIC_CABEC S
                     WHERE F.OID_REGI = to_number(reg.BOL_DESP)
                       AND F.DCCA_OID_CABE=D.OID_CABE
                       AND D.SOCA_OID_SOLI_CABE=S.OID_SOLI_CABE ;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     REG.BOL_DESP := NULL;
                 END;

                 -- SI EL DOCUMENTO ES NOTA DE CREDITO
                 IF REG.TIDO_OID_TIPO_DOCU = '07' THEN
                    REG.BOL_DESP := NULL;

                    /* Buscando Nro de documento de Referencia */
                    BEGIN
                      SELECT A.COD_SBAC
                      INTO lsCodSbac
                      FROM SEG_SUBAC A
                      WHERE A.OID_SBAC = REG.SBAC_OID_SBAC;
                    EXCEPTION
                    WHEN no_data_found THEN
                         lsCodSbac := NULL;
                    END;
                    IF lsCodSbac = '000' THEN
                       BEGIN
                          SELECT C.VAL_NUME_SOLI
                          INTO
                               REG.VAL_NUME_DOCU_REFE
                          FROM FAC_DOCUM_CONTA_CABEC A,
                               PED_SOLIC_CABEC B,
                               PED_SOLIC_CABEC C
                          WHERE A.OID_CABE = REG.DCCA_OID_CABE
                            AND B.OID_SOLI_CABE = A.SOCA_OID_SOLI_CABE
                            AND C.OID_SOLI_CABE = B.SOCA_OID_DOCU_REFE;
                       EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                            REG.VAL_NUME_DOCU_REFE := NULL;
                       END;
                    END IF;


                    IF REG.TIDO_TIPO_DOCU_REFE IS NULL THEN
                       REG.TIDO_TIPO_DOCU_REFE := 'BD';
                    END IF;
                       REG.VAL_BASE_IMPO:= (-1)*REG.VAL_BASE_IMPO;
                       REG.VAL_DESC:= (-1)*REG.VAL_DESC;
                       REG.VAL_BASE_IMPO_NETO := (-1)*REG.VAL_BASE_IMPO_NETO;
                       REG.IMP_IMPU := (-1)*REG.IMP_IMPU;
                       REG.IMP_TOTA := (-1)*REG.IMP_TOTA;
                       REG.OPINAF := (-1)*REG.OPINAF;

                       REG.VAL_BASE_IMPO_AUX:= (-1)*REG.VAL_BASE_IMPO_AUX;
                       REG.VAL_DESC_AUX:= (-1)*REG.VAL_DESC_AUX;
                       REG.VAL_BASE_IMPO_NETO_AUX := (-1)*REG.VAL_BASE_IMPO_NETO_AUX;
                       REG.IMP_IMPU_AUX := (-1)*REG.IMP_IMPU_AUX;
                       REG.IMP_TOTA_AUX := (-1)*REG.IMP_TOTA_AUX;
                       REG.OPINAF_AUX := (-1)*REG.OPINAF_AUX;
                 END IF;

                 /* En caso sea Boleta de Despacho */
                 IF REG.TIDO_TIPO_DOCU_REFE = 'BD' THEN
                    REG.VAL_SERI_DOCU_REFE := '000';
                 END IF;

                 /* Si es registro anulado */
                 IF(REG.IND_ESTA='1')THEN
                    REG.COD_CLIE:='*  *  *  *  *  *  A   N   U   L   A   D   O  *  *  *  *  *  *';
                    REG.VAL_NUME_IDEN_FISC:='';
                    REG.TIDO_TIPO_DOCU_REFE:='';
                    REG.VAL_SERI_DOCU_REFE:='';
                    REG.VAL_NUME_DOCU_REFE:='';
                    REG.VAL_BASE_IMPO:='';
                    REG.VAL_DESC:='';
                    REG.VAL_BASE_IMPO_NETO:='';
                    REG.OPINAF:='';
                    REG.IMP_IMPU:='';
                    REG.IMP_TOTA:='';
                    -- SE SETEA A CERO LOS CAMPOS AUXILIARES A UTILIZAR
                    REG.DOC_INI_AUX:=0;
                    REG.DOC_FINA_AUX:=0;
                    REG.VAL_BASE_IMPO_AUX:=0;
                    REG.VAL_DESC_AUX:=0;
                    REG.VAL_BASE_IMPO_NETO_AUX:=0;
                    REG.OPINAF_AUX:=0;
                    REG.IMP_IMPU_AUX:=0;
                    REG.IMP_TOTA_AUX:=0;
                 END IF;

                /* IF(REG.IND_TRAN_GRAT='1')THEN
                    REG.COD_CLIE:='*  *  *  * T R A N S F E R E N C I A    G R A T U I T A *  *  *  *';
                    REG.VAL_NUME_IDEN_FISC:='';
                    REG.TIDO_TIPO_DOCU_REFE:='';
                    REG.VAL_SERI_DOCU_REFE:='';
                    REG.VAL_NUME_DOCU_REFE:='';
                    REG.VAL_BASE_IMPO:='';
                    REG.OPINAF:='';
                    REG.VAL_DESC:='';
                    REG.VAL_BASE_IMPO_NETO:='';
                    REG.IMP_IMPU:='';
                    REG.IMP_TOTA:='';
                    -- SE SETEA A CERO LOS CAMPOS AUXILIARES A UTILIZAR
                    REG.DOC_INI_AUX:=0;
                    REG.DOC_FINA_AUX:=0;
                    REG.VAL_BASE_IMPO_AUX:=0;
                    REG.VAL_DESC_AUX:=0;
                    REG.VAL_BASE_IMPO_NETO_AUX:=0;
                    REG.OPINAF_AUX:=0;
                    REG.IMP_IMPU_AUX:=0;
                    REG.IMP_TOTA_AUX:=0;
                 END IF;*/
                 PIPE ROW(REG);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro1%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro1;

      -- Segunda validacion
      OPEN cursorRegistro2(lnIdPais, psFechaInicio, psFechaFin, lnBol,lntick,lnBolPre);
      LOOP
           FETCH cursorRegistro2 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 REG :=tablaRegistro(x);

                 /* En caso sea Nota de Credito */
                 IF REG.TIDO_OID_TIPO_DOCU = '07' THEN
                    REG.BOL_DESP := NULL;
                    REG.VAL_NUME_DOCU_REFE := NULL;
                    IF REG.TIDO_TIPO_DOCU_REFE IS NULL THEN
                       REG.TIDO_TIPO_DOCU_REFE := 'BD';
                    END IF;

                    REG.VAL_BASE_IMPO:= (-1)*REG.VAL_BASE_IMPO;
                    REG.VAL_DESC:= (-1)*REG.VAL_DESC;
                    REG.VAL_BASE_IMPO_NETO := (-1)*REG.VAL_BASE_IMPO_NETO;
                    REG.IMP_IMPU := (-1)*REG.IMP_IMPU;
                    REG.IMP_TOTA := (-1)*REG.IMP_TOTA;
                    REG.OPINAF := (-1)*REG.OPINAF;

                    REG.VAL_BASE_IMPO_AUX:= (-1)*REG.VAL_BASE_IMPO_AUX;
                    REG.VAL_DESC_AUX:= (-1)*REG.VAL_DESC_AUX;
                    REG.VAL_BASE_IMPO_NETO_AUX := (-1)*REG.VAL_BASE_IMPO_NETO_AUX;
                    REG.IMP_IMPU_AUX := (-1)*REG.IMP_IMPU_AUX;
                    REG.IMP_TOTA_AUX := (-1)*REG.IMP_TOTA_AUX;
                    REG.OPINAF_AUX := (-1)*REG.OPINAF_AUX;
                 END IF;

                 /* En caso sea Boleta de Despacho */
                 IF REG.TIDO_TIPO_DOCU_REFE = 'BD' THEN
                    REG.VAL_SERI_DOCU_REFE := '000';
                 END IF;


                 PIPE ROW(REG);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro2%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro2;
      RETURN;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_VENTA_RESUM: '||ls_sqlerrm);
END VEN_FN_OBTIE_VENTA_RESUM;

 /***************************************************************************
Descripcion       : Obtiene el monto importe Neto, verificando si es inafecto la
                    tasa
Fecha Creacion    : 26/07/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_IMPOR_NETO(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
  RETURN  NUMBER  IS
  indicadorImpuesto VARCHAR2(3);
  indicadorImpuestoComparar VARCHAR2(3);
  BEGIN
  indicadorImpuesto :='';
  indicadorImpuestoComparar:= 'EXP';

  SELECT PED_TASA_IMPUE.Val_Indi_Impu
  INTO indicadorImpuesto
  FROM PED_TASA_IMPUE
  WHERE PED_TASA_IMPUE.Oid_Tasa_Impu = oidTasaImpuesto;

  IF indicadorImpuesto = indicadorImpuestoComparar THEN
     RETURN 0;
  ELSE
      RETURN abs(valorRetorno);
  END IF;
  EXCEPTION
  WHEN NO_DATA_FOUND  THEN
       RETURN abs(valorRetorno);
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_IMPOR_NETO: '||ls_sqlerrm);
 END VEN_FN_OBTIE_IMPOR_NETO;
 /***************************************************************************
Descripcion       : Obtiene el monto Base Importe,verificando si es inafecto la
                    tasa
Fecha Creacion    : 26/07/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_BASE_IMPOR(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
    RETURN  NUMBER  IS
  indicadorImpuesto VARCHAR2(3);
  indicadorImpuestoComparar VARCHAR2(3);
  BEGIN
    indicadorImpuesto :='';
    indicadorImpuestoComparar:= 'EXP';

    SELECT PED_TASA_IMPUE.Val_Indi_Impu
    INTO indicadorImpuesto
    FROM PED_TASA_IMPUE
    WHERE PED_TASA_IMPUE.Oid_Tasa_Impu = oidTasaImpuesto;

    IF indicadorImpuesto = indicadorImpuestoComparar THEN
       RETURN 0;
    ELSE
        RETURN abs(valorRetorno);
    END IF;
  EXCEPTION
  WHEN NO_DATA_FOUND  THEN
       RETURN abs(valorRetorno);
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_BASE_IMPOR: '||ls_sqlerrm);
 END VEN_FN_OBTIE_BASE_IMPOR;
/***************************************************************************
Descripcion       : Obtiene el monto de las operaciones inafectas
Fecha Creacion    : 26/07/2007
Autor             : Marco Antonio Agurto Jimenez
***************************************************************************/
FUNCTION VEN_FN_OBTIE_OPERA_INAFE(
    oidTasaImpuesto     NUMBER,
    valorRetorno NUMBER)
   RETURN  NUMBER  IS
  indicadorImpuesto VARCHAR2(3);
  indicadorImpuestoComparar VARCHAR2(3);
  BEGIN
  indicadorImpuesto :='';
  indicadorImpuestoComparar:= 'EXP';

  SELECT PED_TASA_IMPUE.Val_Indi_Impu
  INTO indicadorImpuesto
  FROM PED_TASA_IMPUE
  WHERE PED_TASA_IMPUE.Oid_Tasa_Impu = oidTasaImpuesto;

  IF indicadorImpuesto = indicadorImpuestoComparar THEN
     RETURN abs(valorRetorno);
  ELSE
      RETURN 0;
  END IF;
  EXCEPTION
  WHEN NO_DATA_FOUND  THEN
       RETURN 0;
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_OPERA_INAFE: '||ls_sqlerrm);
 END VEN_FN_OBTIE_OPERA_INAFE;
/***************************************************************************
Descripcion       : Devuelve Listado  de Registro de Ventas Detallado
Fecha Creacion    : 23/02/2007
Autor             : Jose Nunez M.
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VENTA_DETAL(
    psCodPais     VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin    VARCHAR2)
RETURN tablaRegVenDetalle PIPELINED
IS
  tablaRegistro   tablaRegVenDetalle;
  REG             tRegReporteRegistroVentaDet;
  CURSOR cursorRegistro1
  (vnIdPais NUMBER, vsFechaini VARCHAR2, vsFechafin VARCHAR2)
  IS

       SELECT
           TO_CHAR(F.OID_REGI) AS BOLDES,
           VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(F.TIDO_OID_TIPO_DOCU,F.PAIS_OID_PAIS) AS DOC_LEG,
           F.VAL_SERI_DOCU_LEGA,
           F.VAL_NUME_DOCU_LEGA,
           F.VAL_NUME_IDEN_FISC,
           '' AS COD_CLIE,
           '' AS NOMBRE,
           '' AS NUM_DOCU_IDEN,
           F.TIDO_TIPO_DOCU_REFE,
           F.VAL_SERI_DOCU_REFE,
           F.VAL_NUME_DOCU_REFE,
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_BASE_IMPOR(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO),
           ABS(F.VAL_DESC),
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_IMPOR_NETO(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO_NETO),
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_OPERA_INAFE(F.TAIM_OID_TASA_IMPU, abs(F.VAL_BASE_IMPO)),
           ABS(F.IMP_IMPU),
           ABS(F.IMP_TOTA),
           F.IND_ESTA,
           F.IND_TRAN_GRAT,
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_BASE_IMPOR(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO),
           ABS(F.VAL_DESC),
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_IMPOR_NETO(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO_NETO),
           VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_OPERA_INAFE(F.TAIM_OID_TASA_IMPU, abs(F.VAL_BASE_IMPO)),
           ABS(F.IMP_IMPU),
           ABS(F.IMP_TOTA),
           F.CLIE_OID_CLIE,
           F.DCCA_OID_CABE,
           F.SBAC_OID_SBAC
      FROM FAC_REGIS_UNICO_VENTA F
      WHERE
            F.PAIS_OID_PAIS=vnIdPais
           AND (F.FEC_EMIS >= TO_DATE(vsFechaini,'DD/MM/YYYY') AND F.FEC_EMIS <= TO_DATE(vsFechafin,'DD/MM/YYYY'))
      ORDER BY F.FEC_EMIS, DOC_LEG ,F.VAL_SERI_DOCU_LEGA, F.VAL_NUME_DOCU_LEGA;


 --Declaracion de variables a usar
 lnIdPais   SEG_PAIS.OID_PAIS%TYPE;
 lsCodSbac        SEG_SUBAC.Cod_Sbac%TYPE;
 BEGIN
    /* obteniendo id's */
   lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);-- id del pais consultante
   OPEN cursorRegistro1(lnIdPais, psFechaInicio, psFechaFin);
      LOOP
           FETCH cursorRegistro1 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 REG := tablaRegistro(x);

                 /* Obteniendo el numero de solicitud */
                 BEGIN
                     SELECT TO_CHAR(S.VAL_NUME_SOLI)
                     INTO REG.BOL_DESP
                     FROM FAC_REGIS_UNICO_VENTA F,
                          FAC_DOCUM_CONTA_CABEC D,
                          PED_SOLIC_CABEC S
                     WHERE F.OID_REGI = to_number(reg.BOL_DESP)
                       AND F.DCCA_OID_CABE=D.OID_CABE
                       AND D.SOCA_OID_SOLI_CABE=S.OID_SOLI_CABE ;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     REG.BOL_DESP := NULL;
                 END;

                 -- SI EL DOCUMENTO ES NOTA DE CREDITO
                 IF REG.TIDO_OID_TIPO_DOCU = '07' THEN
                    IF REG.TIDO_TIPO_DOCU_REFE IS NULL THEN
                       REG.TIDO_TIPO_DOCU_REFE := 'BD';
                    END IF;

                    REG.BOL_DESP := NULL;

                    /* Buscando Nro de documento de Referencia */
                    BEGIN
                      SELECT A.COD_SBAC
                      INTO lsCodSbac
                      FROM SEG_SUBAC A
                      WHERE A.OID_SBAC = REG.SBAC_OID_SBAC;
                    EXCEPTION
                    WHEN no_data_found THEN
                         lsCodSbac := NULL;
                    END;
                    IF lsCodSbac = '000' THEN
                       BEGIN
                          SELECT C.VAL_NUME_SOLI
                          INTO
                               REG.VAL_NUME_DOCU_REFE
                          FROM FAC_DOCUM_CONTA_CABEC A,
                               PED_SOLIC_CABEC B,
                               PED_SOLIC_CABEC C
                          WHERE A.OID_CABE = REG.DCCA_OID_CABE
                            AND B.OID_SOLI_CABE = A.SOCA_OID_SOLI_CABE
                            AND C.OID_SOLI_CABE = B.SOCA_OID_DOCU_REFE;
                       EXCEPTION
                       WHEN NO_DATA_FOUND THEN
                            REG.VAL_NUME_DOCU_REFE := NULL;
                       END;
                    END IF;

                     REG.VAL_BASE_IMPO:= (-1)*REG.VAL_BASE_IMPO;
                     REG.VAL_DESC:= (-1)*REG.VAL_DESC;
                     REG.VAL_BASE_IMPO_NETO := (-1)*REG.VAL_BASE_IMPO_NETO;
                     REG.IMP_IMPU := (-1)*REG.IMP_IMPU;
                     REG.IMP_TOTA := (-1)*REG.IMP_TOTA;
                     REG.OPINAF := (-1)*REG.OPINAF;

                     REG.VAL_BASE_IMPO_AUX:= (-1)*REG.VAL_BASE_IMPO_AUX;
                     REG.VAL_DESC_AUX:= (-1)*REG.VAL_DESC_AUX;
                     REG.VAL_BASE_IMPO_NETO_AUX := (-1)*REG.VAL_BASE_IMPO_NETO_AUX;
                     REG.IMP_IMPU_AUX := (-1)*REG.IMP_IMPU_AUX;
                     REG.IMP_TOTA_AUX := (-1)*REG.IMP_TOTA_AUX;
                     REG.OPINAF_AUX := (-1)*REG.OPINAF_AUX;

                 END IF;

                 /* En caso sea Boleta de Despacho */
                 IF REG.TIDO_TIPO_DOCU_REFE = 'BD' THEN
                    REG.VAL_SERI_DOCU_REFE := '000';
                 END IF;

                 IF reg.CLIE_OID_CLIE IS NOT NULL THEN
                    /* Encontrando datos del cliente */
                    BEGIN
                      SELECT A.COD_CLIE, A.VAL_NOM1||' '||A.VAL_NOM2||' '||A.VAL_APE1||' '||A.VAL_APE2
                      INTO
                           REG.COD_CLIE, REG.NOMBRE
                      FROM MAE_CLIEN A
                      WHERE A.OID_CLIE = reg.CLIE_OID_CLIE;
                    EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                          REG.COD_CLIE := NULL;
                          REG.NOMBRE   := NULL;
                    END;

                    /* Encontrando n¿mero de identidad del cliente */
                    BEGIN
                      SELECT A.NUM_DOCU_IDEN
                      INTO
                           REG.NUM_DOCU_IDEN
                      FROM MAE_CLIEN_IDENT A
                      WHERE A.CLIE_OID_CLIE = reg.CLIE_OID_CLIE
                        AND A.VAL_IDEN_DOCU_PRIN = 1;
                    EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                          REG.NUM_DOCU_IDEN := NULL;
                    END;
                 END IF;

                 IF(REG.IND_ESTA='1')THEN
                    REG.COD_CLIE:='***ANULADO***';
                    REG.NOMBRE:='***ANULADO***';
                    REG.TIDO_TIPO_DOCU_REFE:='***ANULADO***';
                    REG.VAL_SERI_DOCU_REFE:='***ANULADO***';
                    REG.VAL_NUME_DOCU_REFE:='***ANULADO***';
                    REG.VAL_BASE_IMPO:='***ANULADO***';
                    REG.VAL_BASE_IMPO_NETO:='***ANULADO***';
                    REG.VAL_DESC:='***ANULADO***';
                    REG.OPINAF:='***ANULADO***';
                    REG.IMP_IMPU:='***ANULADO***';
                    REG.IMP_TOTA:='***ANULADO***';
                    -- SE SETEA A CERO LOS CAMPOS AUXILIARES A UTILIZAR
                    REG.VAL_BASE_IMPO_AUX:=0;
                    REG.VAL_BASE_IMPO_NETO_AUX:=0;
                    REG.VAL_DESC_AUX:=0;
                    REG.OPINAF_AUX:=0;
                    REG.IMP_IMPU_AUX:=0;
                    REG.IMP_TOTA_AUX:=0;
                 END IF;
                 IF(REG.IND_TRAN_GRAT='1')THEN
                    REG.VAL_BASE_IMPO:='***TRANSFERENCIA GRATUITA***';
                    REG.VAL_BASE_IMPO_NETO:='***TRANSFERENCIA GRATUITA***';
                    REG.VAL_DESC:='***TRANSFERENCIA GRATUITA***';
                    REG.OPINAF:='***TRANSFERENCIA GRATUITA***';
                    REG.IMP_IMPU:='***TRANSFERENCIA GRATUITA***';
                    REG.IMP_TOTA:='***TRANSFERENCIA GRATUITA***';
                    -- SE SETEA A CERO LOS CAMPOS AUXILIARES A UTILIZAR
                    REG.VAL_BASE_IMPO_AUX:=0;
                    REG.VAL_BASE_IMPO_NETO_AUX:=0;
                    REG.VAL_DESC_AUX:=0;
                    REG.OPINAF_AUX:=0;
                    REG.IMP_IMPU_AUX:=0;
                    REG.IMP_TOTA_AUX:=0;

                 END IF;

                 PIPE ROW(REG);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro1%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro1;
      RETURN;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_VENTA_DETAL: '||ls_sqlerrm);
 END VEN_FN_OBTIE_VENTA_DETAL;

/***************************************************************************
Descripcion       : Devuelve el codigo de homologacion de sunat de un documento
Fecha Creacion    : 26/02/2007
Autor             : Jose Nunez M.
***************************************************************************/
FUNCTION VEN_FN_DEVUE_HOMO_SUNAT(
   pnOidTipoDoc FAC_TIPO_DOCUM.Oid_Tipo_Docu%TYPE,
   pnOidPais FAC_REGIS_UNICO_VENTA.PAIS_OID_PAIS%TYPE
)
RETURN VARCHAR2
IS
  lscodsuna        VARCHAR2(3);
BEGIN
 lscodsuna:='';
 SELECT B.COD_HOMO_SUNA INTO lscodsuna
 FROM BAS_HOMOL_DOCUM B,
      FAC_TIPO_DOCUM F,
      SEG_PAIS P
 WHERE
       F.OID_TIPO_DOCU=pnOidTipoDoc
       AND F.COD_TIPO_DOCU=B.COD_TIPO_DOCU
       AND P.OID_PAIS=pnOidPais
       AND P.COD_PAIS=B.COD_PAIS;
RETURN lscodsuna;
EXCEPTION
WHEN NO_DATA_FOUND  THEN
     RETURN '';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_DEVUE_HOMO_SUNAT: '||ls_sqlerrm);
END VEN_FN_DEVUE_HOMO_SUNAT;


/***************************************************************************
Descripcion       : Devuelve el codigo de homologacion de sunat de un documento
Fecha Creacion    : 26/02/2007
Autor             : Jose Nunez M.
***************************************************************************/
FUNCTION VEN_FN_DEVUE_HOMO_SUNAT(
   pnOidTipoDoc FAC_TIPO_DOCUM.Oid_Tipo_Docu%TYPE
)
RETURN VARCHAR2
IS
  lscodsuna        VARCHAR2(3);
  lnOidPais        NUMBER;
BEGIN
 lscodsuna:='';
 lnOidPais := gen_pkg_gener.gen_fn_devuelve_id_pais('PE');
 SELECT B.COD_HOMO_SUNA INTO lscodsuna
 FROM BAS_HOMOL_DOCUM B,
      FAC_TIPO_DOCUM F,
      SEG_PAIS P
 WHERE
       F.OID_TIPO_DOCU=pnOidTipoDoc
       AND F.COD_TIPO_DOCU=B.COD_TIPO_DOCU
       AND P.OID_PAIS=lnOidPais
       AND P.COD_PAIS=B.COD_PAIS;
RETURN lscodsuna;
EXCEPTION
WHEN NO_DATA_FOUND  THEN
     RETURN '';
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_DEVUE_HOMO_SUNAT: '||ls_sqlerrm);
END VEN_FN_DEVUE_HOMO_SUNAT;


/***************************************************************************
Descripcion       : Devuelve el un documento legal, conformado por el oid del tipo
                    de docuemto y su numero
Fecha Creacion    : 26/01/2007
Autor             : Jose Nunez M.
***************************************************************************/
FUNCTION VEN_FN_DEVUE_DOCUM_LEGAL(
   pnOidTipoDoc FAC_TIPO_DOCUM.Oid_Tipo_Docu%TYPE,
   pnOidPais    FAC_REGIS_UNICO_VENTA.PAIS_OID_PAIS%TYPE,
   pnValNumDoc  FAC_REGIS_UNICO_VENTA.VAL_NUME_DOCU_LEGA%TYPE
)
RETURN VARCHAR2
IS
 lscodLeg VARCHAR2(25);
BEGIN
  SELECT VEN_FN_DEVUE_HOMO_SUNAT(pnOidTipoDoc,pnOidPais) INTO lscodLeg
  FROM DUAL;
  RETURN lscodLeg||pnValNumDoc;
  EXCEPTION
  WHEN NO_DATA_FOUND  THEN
       RETURN ''||pnValNumDoc;
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := substr(sqlerrm,1,250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_DEVUE_DOCUM_LEGAL: '||ls_sqlerrm);
END VEN_FN_DEVUE_DOCUM_LEGAL;

/***************************************************************************
Descripcion       :  Procedimiento que carga una tabla temporal, para ser usada
                     por el reporte de registro de venta consolidado.
Fecha Creacion    : 26/01/2007
Autor             : Jose Nunez M.
***************************************************************************/
PROCEDURE VEN_PR_CARGA_VENTA_CONSO(
    psCodPais     VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin    VARCHAR2
)
IS
  lnIdPais         SEG_PAIS.OID_PAIS%TYPE;
BEGIN
   EXECUTE IMMEDIATE 'TRUNCATE TABLE VEN_REPOR_REGIS_VENTA_CONSO';

   lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);-- id del pais consultante
   INSERT INTO VEN_REPOR_REGIS_VENTA_CONSO
  (IND_ORDN, COD_ACCE, COD_CANA, TIDO_OID_TIPO_DOCU,
   VAL_NUME_DOCU_LEGA, DOC_LEGAL, VAL_SERI_DOCU_LEGA,
   IND_ESTA, IND_TRAN_GRAT,VAL_BASE_IMPO,
   VAL_DESC, VAL_BASE_IMPO_NETO, IMP_IMPU,IMP_TOTA, COD_TIPO_DOCU, OPE_INAF, EST_REGI)
  SELECT
       '1',
       P.DES_PNTO_EMI,
       T.DES_SIST_EMI,
       F.TIDO_OID_TIPO_DOCU,
       DECODE(F.VAL_NUME_DOCU_LEGA,NULL,0,F.VAL_NUME_DOCU_LEGA),
       VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_DOCUM_LEGAL(F.TIDO_OID_TIPO_DOCU,F.PAIS_OID_PAIS,F.VAL_NUME_DOCU_LEGA) AS DOC_LEG,
       F.VAL_SERI_DOCU_LEGA,
       F.IND_ESTA,
       F.IND_TRAN_GRAT,
       VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_BASE_IMPOR(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO ),
       ABS(F.VAL_DESC),
       VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_IMPOR_NETO(F.TAIM_OID_TASA_IMPU, F.VAL_BASE_IMPO_NETO),
       ABS(F.IMP_IMPU),
       ABS(F.IMP_TOTA),
       VEN_PKG_REPOR_RUNIC.VEN_FN_DEVUE_HOMO_SUNAT(F.TIDO_OID_TIPO_DOCU, lnIdPais) as COD_TIPO_DOCU,
       VEN_PKG_REPOR_RUNIC.VEN_FN_OBTIE_OPERA_INAFE(F.TAIM_OID_TASA_IMPU, abs(F.VAL_BASE_IMPO)),
       IND_ESTA
FROM  FAC_REGIS_UNICO_VENTA F,
      SEG_SUBAC S,
      BAS_HOMOL_PUNTO_EMISI B,
      BAS_PUNTO_EMISI P,
      BAS_SISTE_EMISI T
WHERE F.PAIS_OID_PAIS=lnIdPais
      AND (F.FEC_EMIS >= TO_DATE(psFechaInicio,'DD/MM/YYYY') AND F.FEC_EMIS <= TO_DATE(psFechaFin,'DD/MM/YYYY'))
      AND F.SBAC_OID_SBAC= S.OID_SBAC
      AND S.COD_SBAC=B.COD_SUB_ACC
      AND B.COD_PNTO_EMI=P.COD_PNTO_EMI
      AND B.COD_SIS_EMI=P.COD_SIST_EMI
      AND P.COD_SIST_EMI=T.COD_SIST_EMI;

  /* Actualizando a negativos los importes de tipo de documento = '07' */
  UPDATE VEN_REPOR_REGIS_VENTA_CONSO
  SET VAL_BASE_IMPO = (-1) * VAL_BASE_IMPO,
      VAL_DESC = (-1) * VAL_DESC,
      VAL_BASE_IMPO_NETO = (-1) * VAL_BASE_IMPO_NETO,
      IMP_IMPU = (-1) * IMP_IMPU,
      IMP_TOTA = (-1) * IMP_TOTA,
      OPE_INAF = (-1) * OPE_INAF
  WHERE
      COD_TIPO_DOCU = '07';

  /* Para el caso de los anulados */
  UPDATE VEN_REPOR_REGIS_VENTA_CONSO
  SET VAL_BASE_IMPO = 0,
      VAL_DESC = 0,
      VAL_BASE_IMPO_NETO = 0,
      IMP_IMPU = 0,
      IMP_TOTA = 0,
      OPE_INAF = 0
  WHERE
      EST_REGI = '1';


  UPDATE VEN_REPOR_REGIS_VENTA_CONSO
  SET IND_ORDN = '0'
  WHERE
      COD_ACCE = 'SISTEMA COMERCIAL';

  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_PR_CARGA_VENTA_CONSO: '||ls_sqlerrm);
END VEN_PR_CARGA_VENTA_CONSO;

/***************************************************************************
Descripcion       : Devuelve Listado de Registro de Resumen de Ventas Detallado
                    de Notas de Crédito
Fecha Creacion    : 07/01/2008
Autor             : Jose A. Cairampoma
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VENTA_DETAL_NOTAC(
    psCodPais     VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin    VARCHAR2)
RETURN tablaRegVentaDetNotaC PIPELINED
IS
  tablaRegistro   tablaRegVentaDetNotaC;
  REG             tRegReporteRegVentaDetNotaC;
  CURSOR cursorRegistro
  (vnIdPais NUMBER, vnNota NUMBER)
  IS

   SELECT d.cod_clie codigocliente,
          a.val_ape1 || ' ' || a.val_ape2 || ' ' || a.val_nom1 || ' ' ||
          a.val_nom2 nombrecliente,
          a.fec_emis fechaemision,
          b.fec_cont fechacontable,
          c.val_tasa_impu tasaimpuesto,
          a.val_base_impo_neto baseimponibleneta,
          a.val_base_impo_neto baseimponible,
          1 AS numerodefacturas,
          a.val_docu_inte numerointerno
     FROM fac_regis_unico_venta a,
          fac_docum_conta_cabec b,
          ped_tasa_impue        c,
          mae_clien             d
    WHERE a.dcca_oid_cabe = b.oid_cabe
      AND a.taim_oid_tasa_impu = c.oid_tasa_impu
      AND a.tido_oid_tipo_docu = vnNota
      AND a.clie_oid_clie = d.oid_clie
      AND a.pais_oid_pais = vnIdPais
      AND a.fec_emis >= to_date(psFechaInicio, 'DD/MM/YYYY')
      AND a.fec_emis <= to_date(psFechaFin, 'DD/MM/YYYY');


 --Declaracion de variables a usar
 lnIdPais   SEG_PAIS.OID_PAIS%TYPE;
 lnNota     FAC_TIPO_DOCUM.OID_TIPO_DOCU%TYPE;

 BEGIN
    /* obteniendo id's */
   lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);-- id del pais consultante

    -- obtiene el oid de la boleta de venta
   SELECT T.OID_TIPO_DOCU INTO lnNota
   FROM FAC_TIPO_DOCUM T
   WHERE T.DES_TIPO_DOCU='Nota Crédito Fact 1';

   OPEN cursorRegistro(lnIdPais, lnNota);
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 REG := tablaRegistro(x);
                 PIPE ROW(REG);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro;
      RETURN;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_VENTA_DETAL_NOTAC: '||ls_sqlerrm);
 END VEN_FN_OBTIE_VENTA_DETAL_NOTAC;

/***************************************************************************
Descripcion       : Devuelve Listado de Registro de Resumen de Ventas Detallado
                    de ventas
Fecha Creacion    : 07/01/2008
Autor             : Jose A. Cairampoma
***************************************************************************/
FUNCTION VEN_FN_OBTIE_VENTA_DETAL_VENTA(
    psCodPais     VARCHAR2,
    psFechaInicio VARCHAR2,
    psFechaFin    VARCHAR2)
RETURN tablaRegVentaDetVenta PIPELINED
IS
  tablaRegistro   tablaRegVentaDetVenta;
  REG             tRegReporteRegVentaDetVentas;
  CURSOR cursorRegistro
  (vnIdPais NUMBER, vnFactura1 NUMBER, vnFacturaPremio NUMBER)
  IS

   SELECT  d.cod_clie codigoCliente,
           a.val_nume_iden_fisc numeroIdentidadFiscal,
           a.val_ape1 || ' ' || a.val_ape2 || ' ' || a.val_nom1 || ' ' || a.val_nom2 nombrecliente,
           a.fec_emis fechaemision,
           trunc(b.fec_cont) fechacontable,
           c.val_tasa_impu porcIVA,
           a.imp_impu      valorIVA,
           a.val_base_impo_neto baseimponibleneta,
           ' '  baseImpoGravadaServ,
           a.val_flet baseimponibleTarifa,
           a.val_nume_docu_lega numeroFactura
      FROM fac_regis_unico_venta a,
           fac_docum_conta_cabec b,
           ped_tasa_impue        c,
           mae_clien d
     WHERE a.clie_oid_clie = d.oid_clie
       AND a.dcca_oid_cabe = b.oid_cabe
       AND a.taim_oid_tasa_impu = c.oid_tasa_impu
       AND a.tido_oid_tipo_docu IN (vnFactura1,vnFacturaPremio)
       AND a.pais_oid_pais = vnIdPais
       AND a.fec_emis >= to_date(psFechaInicio, 'DD/MM/YYYY')
       AND a.fec_emis <= to_date(psFechaFin, 'DD/MM/YYYY');


 --Declaracion de variables a usar
 lnIdPais            SEG_PAIS.OID_PAIS%TYPE;
 lnFactura1          FAC_TIPO_DOCUM.OID_TIPO_DOCU%TYPE;
 lnFacturaPremio     FAC_TIPO_DOCUM.OID_TIPO_DOCU%TYPE;

 BEGIN
    /* obteniendo id's */
   lnIdPais       := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);-- id del pais consultante

   -- obtiene el oid de la boleta de venta
   SELECT T.OID_TIPO_DOCU INTO lnFacturaPremio
   FROM FAC_TIPO_DOCUM T
   WHERE T.DES_TIPO_DOCU='Factura Premios';

   SELECT T.OID_TIPO_DOCU INTO lnFactura1
   FROM FAC_TIPO_DOCUM T
   WHERE T.DES_TIPO_DOCU='Factura 1';

   OPEN cursorRegistro(lnIdPais, lnFactura1, lnFacturaPremio);
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 REG := tablaRegistro(x);
                 PIPE ROW(REG);
             END LOOP;
           END IF;
           EXIT WHEN cursorRegistro%NOTFOUND ;
      END LOOP ;
      CLOSE cursorRegistro;
      RETURN;
  EXCEPTION
  WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR VEN_FN_OBTIE_VENTA_DETAL_VENTA: '||ls_sqlerrm);
 END VEN_FN_OBTIE_VENTA_DETAL_VENTA;

END VEN_PKG_REPOR_RUNIC;
/
