CREATE OR REPLACE PACKAGE "COM_PKG_REPOR_RECUP" IS

   /* Declaracion de Tipos */
   TYPE TIPOCURSOR IS  REF CURSOR;

   /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   W_FILAS      NUMBER:=1000;


   TYPE tRegComisionRecuReporteS IS RECORD (
     COD_REGI               ZON_REGIO.COD_REGI%TYPE,
     COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
     COD_SECC               ZON_SECCI.COD_SECC%TYPE,
     COD_LIDE_SECC          VARCHAR2(15),
     NOM_LIDE_SECC          VARCHAR2(200),
     LIB_ELEC               MAE_CLIEN_LIDER.NUM_DNI%TYPE,
     NRO_CUEN               MAE_CLIEN_LIDER.NUM_LIAH%TYPE,
     NUM_LIDE               NUMBER(6),
     NUM_LIDE_GANA_RECU     NUMBER(6),
     POR_LIDE_GANA_RECU     NUMBER(12,2),
     IMP_NETO               NUMBER(12,2),
     IMP_NETO_SREC_CIMP NUMBER(12,2),
     IMP_RECU               NUMBER(12,2),
     IMP_RECU_TRA2          NUMBER(12,2),
     IMP_RECU_CIMP          NUMBER(12,2),
     IMP_RECU_TRA2_CIMP     NUMBER(12,2),
     POR_RECU               NUMBER(12,2),
     POR_RECU_TRA2          NUMBER(12,2),
     IMP_COMI_RECU          NUMBER(12,2),
     IMP_VENT_INGR          NUMBER(12,2),
     IMP_COMI_INGR          NUMBER(12,2),
     IMP_COMI_INGR_ANTE     NUMBER(12,2),
     VAL_PROC_TRA1          NUMBER(12,2),
     VAL_PROC_TRA2          NUMBER(12,2),
     TOT_COMI               NUMBER(12,2),
     NUM_DIAS_COMI          NUMBER(6),
     NUM_DIAS_COMI_TRA2     NUMBER(6),
     IMP_COMI_TRA1          NUMBER(12,2),
     IMP_COMI_TRA2          NUMBER(12,2)
   );
   TYPE tTablaComisionReporteS IS TABLE OF tRegComisionRecuReporteS;

   TYPE tRegComisionRecuZona IS RECORD (
     COD_REGI               ZON_REGIO.COD_REGI%TYPE,
     COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
     COD_LIDE_ZONA          VARCHAR2(15),
     NOM_LIDE_ZONA          VARCHAR2(200),
     NUM_LIDE               NUMBER(6),
     NUM_LIDE_GANA_RECU     NUMBER(6),
     NUM_LIDE_GANA_INGR     NUMBER(6),
     POR_LIDE_GANA_RECU     NUMBER(12,2),
     POR_LIDE_GANA_INGR     NUMBER(12,2),
     IMP_NETO               NUMBER(12,2),
     IMP_NETO_SREC_CIMP     NUMBER(12,2),
     IMP_RECU               NUMBER(12,2),
     IMP_RECU_TRA2          NUMBER(12,2),
     IMP_RECU_CIMP          NUMBER(12,2),
     IMP_RECU_TRA2_CIMP     NUMBER(12,2),
     POR_RECU               NUMBER(12,2),
     POR_RECU_TRA2          NUMBER(12,2),
     IMP_COMI_RECU          NUMBER(12,2),
     IMP_VENT_INGR          NUMBER(12,2),
     IMP_COMI_INGR          NUMBER(12,2),
     IMP_COMI_INGR_ANTE     NUMBER(12,2),
     VAL_PROC_TRA1          NUMBER(12,2),
     VAL_PROC_TRA2          NUMBER(12,2),
     TOT_COMI               NUMBER(12,2),
     IMP_GANA_LIDE          NUMBER(12,2),
     NUM_DIAS_COMI          NUMBER(6),
     NUM_DIAS_COMI_TRA2     NUMBER(6),
     IMP_COMI_TRA1          NUMBER(12,2),
     IMP_COMI_TRA2          NUMBER(12,2)
   );
   TYPE tTablaComisionRecuZona IS TABLE OF tRegComisionRecuZona;

   TYPE tRegComisionRecuRegion IS RECORD (
      COD_REGI               VARCHAR2(2),
     DES_REGI               VARCHAR2(40),
     NUM_LIDE               NUMBER(6),
     NUM_LIDE_GANA_RECU     NUMBER(6),
     NUM_LIDE_GANA_INGR     NUMBER(6),
     POR_LIDE_GANA_RECU     NUMBER(12,2),
     POR_LIDE_GANA_INGR     NUMBER(12,2),
     IMP_NETO               NUMBER(12,2),
     IMP_NETO_SREC_CIMP NUMBER(12,2),
     IMP_RECU               NUMBER(12,2),
     IMP_RECU_TRA2          NUMBER(12,2),
     IMP_RECU_CIMP          NUMBER(12,2),
     IMP_RECU_TRA2_CIMP     NUMBER(12,2),
     POR_RECU               NUMBER(12,2),
     POR_RECU_TRA2          NUMBER(12,2),
     IMP_COMI_RECU          NUMBER(12,2),
     IMP_VENT_INGR          NUMBER(12,2),
     IMP_COMI_INGR          NUMBER(12,2),
     IMP_COMI_INGR_ANTE     NUMBER(12,2),
     VAL_PROC_TRA1          NUMBER(12,2),
     VAL_PROC_TRA2          NUMBER(12,2),
     TOT_COMI               NUMBER(12,2),
     NUM_DIAS_COMI          NUMBER(6),
     NUM_DIAS_COMI_TRA2     NUMBER(6),
     IMP_COMI_TRA1          NUMBER(12,2),
     IMP_COMI_TRA2          NUMBER(12,2)
   );
   TYPE tTablaComisionRecuRegion IS TABLE OF tRegComisionRecuRegion;

   TYPE tRegIdPeriodo IS RECORD (
       PERD_OID_PERI INT_FUENT_VENTA_PREVI_SAP.PERD_OID_PERI%TYPE
   );

   TYPE tRegPagoLideresSuspe IS RECORD (
     COD_REGI               ZON_REGIO.COD_REGI%TYPE,
     COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
     COD_SECC               ZON_SECCI.COD_SECC%TYPE,
     COD_LIDE_SECC          VARCHAR2(15),
     NOM_LIDE_SECC          VARCHAR2(200),
     IMP_COMI_RECU          NUMBER(12,2),
     IMP_COMI_INGR          NUMBER(12,2),
     TOT_COMI               NUMBER(12,2)
   );
   TYPE tTablaPagoLideresSuspe IS TABLE OF tRegPagoLideresSuspe;

   TYPE tRegReporteComisionComer IS RECORD (
     FEC_INIC               DATE,
     FEC_FINA               DATE,
     COD_REGI               VARCHAR2(2),
     COD_ZONA               VARCHAR2(4),
     COD_LIDE_ZONA          VARCHAR2(15),
     NOM_LIDE_ZONA          VARCHAR2(200),
     TIP_COMI               VARCHAR2(1),
     IMP_PAGO               NUMBER(12,2),
     IMP_COMI               NUMBER(12,2),
     IMP_CALC               NUMBER(12,2),
     IMP_RESU               NUMBER(12,2)
   );

   TYPE tTablaReporteComisionComer IS TABLE OF tRegReporteComisionComer;

   TYPE tRegTablaResponsablesUA IS RECORD (
     COD_SUBG_VENT          ZON_SUB_GEREN_VENTA.COD_SUBG_VENT%TYPE,
     DES_SUBG_VENT          ZON_SUB_GEREN_VENTA.DES_SUBG_VENT%TYPE,
     COD_REGI               ZON_REGIO.COD_REGI%TYPE,
     DES_REGI               ZON_REGIO.DES_REGI%TYPE,
     COD_ZONA               ZON_ZONA.COD_ZONA%TYPE,
     DES_ZONA               ZON_ZONA.DES_ZONA%TYPE,
     OID_SECC               ZON_SECCI.OID_SECC%TYPE,
     COD_SECC               ZON_SECCI.COD_SECC%TYPE,
     DES_SECC               ZON_SECCI.COD_SECC%TYPE,
     COD_CLIE               MAE_CLIEN.COD_CLIE%TYPE,
     NOM_CLIE               VARCHAR2(100),
     TEL_CLIE               MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
     NUM_ACTI_INIC          NUMBER(12),
     NUM_ACTI_FINA          NUMBER(12),
     PRO_ACTI_FINA          VARCHAR(10),
     NUM_DOCU_IDEN          MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE,
     FEC_NACI               VARCHAR2(10),
     DIERC                  VARCHAR2(479),
     /* INI JJ VEN-SiCC-2?012-0125 */
     TEL_MOVI               MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
     TEL_TRAB               MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
     VAL_EMAI               MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
     /* INI JJ VEN-SiCC-2?012-0125 */
     CAMPO_UA               ZON_HISTO_GEREN.UA%TYPE,
     FEC_DESD               ZON_HISTO_GEREN.FEC_DESD%TYPE,
     PERD_OID_PERI_DESD     VARCHAR2(100)     
   );
   TYPE tTablaResponsablesUA IS TABLE OF tRegTablaResponsablesUA;

   TYPE tRegTablaRegistradosLeaderList IS RECORD (
     COD_SAP                VARCHAR2(20),
     CODI_ANTI              VARCHAR2(18),
     DESCRIPCION            VARCHAR2(75),
     TIPO                   VARCHAR2(2)
   );

   TYPE tTablaRegistradosLeaderList IS TABLE OF tRegTablaRegistradosLeaderList;


/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Comision de Recuperacion
                    (Resumen x Secci�n)
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_COMIS_RECUP_SECC(
    psCodPais        VARCHAR2,
    psCodPeriodo     VARCHAR2,
    psCodComision    VARCHAR2,
    psCodComisionIngreso  VARCHAR2);

/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Comision de Recuperacion
                    (Resumen x Zona)
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_COMIS_RECUP_ZONA(
    psCodPeriodo     VARCHAR2,
    psCodComision    VARCHAR2,
    psCodComisionIngreso VARCHAR2);


/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Comision de Recuperacion
                    (Resumen x Region)
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_COMIS_RECUP_REGIO(
    psCodPeriodo     VARCHAR2,
    psCodComision    VARCHAR2,
    psCodComisionIngreso VARCHAR2);

/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Comision de Recuperacion
                    (Resumen x Zona)
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_COMIS_RECUP_GZONA(
    psCodPeriodo     VARCHAR2,
    psCodComision    VARCHAR2);

/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Pago de Lideres Suspendidas
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_OBTIE_COMIS_LIDER_SUSPE(
    psCodPais          VARCHAR2,
    psCodPeriodo       VARCHAR2,
    psComisionIngreso  VARCHAR2,
    psComisionRecu     VARCHAR2)
RETURN  tTablaPagoLideresSuspe PIPELINED;

/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de comision de Comercializacion
Fecha Creacion 	  : 03/05/2007
Autor             : Carlos Bazalar
 psTipoComision   : G Gerente de Zona Antigua
                    P Promotora
***************************************************************************/
FUNCTION COM_FN_OBTIE_REPOR_COMER(
    psCodPais          VARCHAR2,
    psFechaIni         VARCHAR2,
    psFechaFin         VARCHAR2,
    psCodComision      VARCHAR2,
    psTipoComision     VARCHAR2)
RETURN  tTablaReporteComisionComer PIPELINED;

/***************************************************************************
Descripcion    	  : Devuelve Listado de Responsables UA de acuerdo al
                    Tipo de Unidad
Fecha Creacion 	  : 20/06/2007
Autor             : Carlos Bazalar
 psTipoUnidad     : 1 SubGerencia
                    2 Region
                    3 Zona
                    4 Secci�n
 psTipoConsulta   : T Todas
                    C Con Responsable
                    S Sin Responsable
***************************************************************************/
FUNCTION COM_FN_OBTIE_RESPO_UA(
    psCodPais          VARCHAR2,
    psCodCanal         VARCHAR2,
    psCodMarca         VARCHAR2,
    psTipoUnidad       VARCHAR2,
    psCodPeriodo       VARCHAR2,
    psCodZona          VARCHAR2,
    psTipoConsulta     VARCHAR2
)
RETURN  tTablaResponsablesUA PIPELINED;

FUNCTION COM_FN_OBTIE_REPOR_LEADERLIST(
    psCodPais          VARCHAR2,
    psCodPeriodo       VARCHAR2
--    psCodPeriodoFin       VARCHAR2,
--    pscodigoTipoCliente VARCHAR2
)
RETURN  tTablaRegistradosLeaderList PIPELINED;

END COM_PKG_REPOR_RECUP;
/
CREATE OR REPLACE PACKAGE BODY "COM_PKG_REPOR_RECUP"
IS

/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Comision de Recuperacion
                    (Resumen x Secci�n)
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_COMIS_RECUP_SECC(
    psCodPais        VARCHAR2,
    psCodPeriodo     VARCHAR2,
    psCodComision    VARCHAR2,
    psCodComisionIngreso  VARCHAR2)
IS
 tablaRegistro      tTablaComisionReporteS;
 regRegistro        tRegComisionRecuReporteS;
 lnIndDctoImportePago com_comis.ind_desc_impo_vent_pago%TYPE;
 lnContadorIngreso  NUMBER;
 lnContadorRecupero NUMBER;
 lnIdComision       NUMBER;

 CURSOR cursorRegistro IS
   SELECT DISTINCT
     A.COD_REGI,
     A.COD_ZONA,
     A.COD_SECC,
     A.COD_LIDE_SECC,
     A.NOM_LIDE_SECC,
     '' AS LIB_ELEC,
     '' AS NRO_CUEN,
     0  AS NUM_LIDE,
     0  AS NUM_LIDE_GANA_RECU,
     0  AS POR_LIDE_GANA_RECU,
     0  AS IMP_NETO,
     0  AS IMP_NETO_SREC_CIMP,
     0  AS IMP_RECU,
     0  AS IMP_RECU_TRA2,
     0  AS IMP_RECU_CIMP,
     0  AS IMP_RECU_TRA2_CIMP,
     0  AS POR_RECU,
     0  AS POR_RECU_TRA2,
     0  AS IMP_COMI_RECU,
     0  AS IMP_VENT_INGR,
     0  AS IMP_COMI_INGR,
     0  AS IMP_COMI_INGR_ANTE,
     0  AS TOT_COMI,
     0  AS VAL_PROC_TRA1,
     0  AS VAL_PROC_TRA2,
     0  AS NUM_DIAS_COMI,
     0  AS NUM_DIAS_COMI_TRA2    ,
     0  AS IMP_COMI_TRA1,
     0  AS IMP_COMI_TRA2
   FROM COM_COMIS_PERIO_CALCU A
   WHERE A.PERI_COD_PERI = psCodPeriodo
     AND ((A.COD_COMI = psCodComision AND A.IND_COMI = '02') OR
          (A.COD_COMI = psCodComisionIngreso AND A.IND_COMI = '01'))
   ORDER BY A.COD_REGI, A.COD_ZONA, A.COD_SECC  ;

 BEGIN
   DELETE FROM COM_TEMPO_COMIS_RECUP_SECCI;
   lnIdComision   := COM_PKG_REPOR.COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

   /* Obtenemos los valores de los flags y el porcentaje de actividad */
   SELECT A.IND_DESC_IMPO_VENT_PAGO
   INTO lnIndDctoImportePago
   FROM COM_COMIS A
   WHERE A.OID_COMI = lnIdComision;

   OPEN cursorRegistro;
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 regRegistro := tablaRegistro(x);

                 /* Encontrando datos del Lider de Seccion */
                 BEGIN
                   SELECT A.NUM_DNI, A.NUM_LIAH
                   INTO
                        regRegistro.LIB_ELEC,
                        regRegistro.NRO_CUEN
                   FROM MAE_CLIEN_LIDER A
                   WHERE A.PAIS_COD_PAIS = psCodPais
                     AND A.COD_CLID = regRegistro.COD_LIDE_SECC;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                       regRegistro.LIB_ELEC := '';
                       regRegistro.NRO_CUEN := '';
                 END ;
                 /* Encontrando los Porcentajes de Retribucion*/
                 BEGIN
                   SELECT A.VAL_PORC_COMI,
                          A.VAL_PORC_COMI_TRA2,
                          A.NUM_DIAS_COMI,
                          A.NUM_DIAS_COMI_TRA2
                   INTO   regRegistro.VAL_PROC_TRA1,
                          regRegistro.VAL_PROC_TRA2,
                          regRegistro.NUM_DIAS_COMI,
                          regRegistro.NUM_DIAS_COMI_TRA2
                   FROM COM_REPOR_COMIS_RECUP A
                   WHERE A.COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND ROWNUM = 1;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                       regRegistro.VAL_PROC_TRA1 := 0;
                       regRegistro.VAL_PROC_TRA2:= 0;
                       regRegistro.NUM_DIAS_COMI:= 0;
                       regRegistro.NUM_DIAS_COMI_TRA2:= 0;
                 END ;

                /* Encontrando # de Lideres */
                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     lnContadorRecupero
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA
                     AND A.COD_SECC = regRegistro.COD_SECC;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     lnContadorRecupero := 0;
                 END ;
                 regRegistro.NUM_LIDE := lnContadorRecupero;

                 /* Encontrando Lideres Ganadoras Comision de Recupero */
                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     regRegistro.NUM_LIDE_GANA_RECU
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA
                     AND A.COD_SECC = regRegistro.COD_SECC
                     AND A.IMP_COMI_TRA1+ A.IMP_COMI_TRA2 > 0;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.NUM_LIDE_GANA_RECU := 0;
                 END ;

                 /* Encontrando importes para Comision de Recuperaci�n */
                 BEGIN
                   IF lnIndDctoImportePago = 1 THEN
                       SELECT
                         A.IMP_NETO_SIN_RECL,
                         A.IMP_NETO_SREC_CIMP,
                         A.IMP_PAGO_ANTE_LIMI,
                         A.IMP_PAGO_ANTE_LIMI_TRA2,
                         A.IMP_PAGO_ANTE_LIMI_CIMP,
                         A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
                         nvl(A.IMP_COMI_TRA1,0) + nvl(A.IMP_COMI_TRA2,0),
                         A.IMP_COMI_TRA1,
                         A.IMP_COMI_TRA2
                       INTO
                         regRegistro.IMP_NETO,
                         regRegistro.IMP_NETO_SREC_CIMP,
                         regRegistro.IMP_RECU,
                         regRegistro.IMP_RECU_TRA2,
                         regRegistro.IMP_RECU_CIMP,
                         regRegistro.IMP_RECU_TRA2_CIMP,
                         regRegistro.IMP_COMI_RECU,
                         regRegistro.IMP_COMI_TRA1,
                         regRegistro.IMP_COMI_TRA2
                       FROM COM_COMIS_PERIO_CALCU A
                       WHERE A.PERI_COD_PERI = psCodPeriodo
                         AND A.COD_COMI = psCodComision
                         AND A.IND_COMI = '02'
                         AND A.COD_REGI = regRegistro.COD_REGI
                         AND A.COD_ZONA = regRegistro.COD_ZONA
                         AND A.COD_SECC = regRegistro.COD_SECC
                         AND A.COD_LIDE_SECC = regRegistro.COD_LIDE_SECC;
                   ELSE
                       SELECT
                         A.IMP_NETO_SIN_RECL,
                         A.IMP_NETO_SREC_CIMP,
                         A.IMP_PAGO_ANTE_LIMI,
                         A.IMP_PAGO_ANTE_LIMI_TRA2,
                         A.IMP_PAGO_ANTE_LIMI_CIMP,
                         A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
                         nvl(A.IMP_COMI_TRA1,0) + nvl(A.IMP_COMI_TRA2,0),
                         A.IMP_COMI_TRA1,
                         A.IMP_COMI_TRA2
                       INTO
                         regRegistro.IMP_NETO,
                         regRegistro.IMP_NETO_SREC_CIMP,
                         regRegistro.IMP_RECU,
                         regRegistro.IMP_RECU_TRA2,
                         regRegistro.IMP_RECU_CIMP,
                         regRegistro.IMP_RECU_TRA2_CIMP,
                         regRegistro.IMP_COMI_RECU,
                         regRegistro.IMP_COMI_TRA1,
                         regRegistro.IMP_COMI_TRA2
                       FROM COM_COMIS_PERIO_CALCU A
                       WHERE A.PERI_COD_PERI = psCodPeriodo
                         AND A.COD_COMI = psCodComision
                         AND A.IND_COMI = '02'
                         AND A.COD_REGI = regRegistro.COD_REGI
                         AND A.COD_ZONA = regRegistro.COD_ZONA
                         AND A.COD_SECC = regRegistro.COD_SECC
                         AND A.COD_LIDE_SECC = regRegistro.COD_LIDE_SECC;
                   END IF;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_NETO := 0;
                     regRegistro.IMP_NETO_SREC_CIMP:=0;
                     regRegistro.IMP_RECU := 0;
                     regRegistro.IMP_RECU_TRA2 := 0;
                     regRegistro.IMP_RECU_CIMP := 0;
                     regRegistro.IMP_RECU_TRA2_CIMP := 0;
                     regRegistro.IMP_COMI_RECU := 0;
                     regRegistro.IMP_COMI_TRA1 :=0;
                     regRegistro.IMP_COMI_TRA2 :=0;
                 END ;

                 /* Encontrando importes para Comision de Ingreso */
                 BEGIN
                   SELECT
                     A.IMP_NETO_SIN_RECL,
                     A.IMP_COMI,
                     A.IMP_COMI_DCTO
                   INTO
                     regRegistro.IMP_VENT_INGR,
                     regRegistro.IMP_COMI_INGR,
                     regRegistro.IMP_COMI_INGR_ANTE
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComisionIngreso
                     AND A.IND_COMI = '01'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA
                     AND A.COD_SECC = regRegistro.COD_SECC
                     AND A.COD_LIDE_SECC = regRegistro.COD_LIDE_SECC;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_VENT_INGR := 0;
                     regRegistro.IMP_COMI_INGR := 0;
                     regRegistro.IMP_COMI_INGR_ANTE := 0;
                 END ;


                 /* Obteniendo % ganadores */
                 IF regRegistro.NUM_LIDE > 0 THEN
                    regRegistro.POR_LIDE_GANA_RECU := (regRegistro.NUM_LIDE_GANA_RECU / regRegistro.NUM_LIDE) * 100;
                 END IF;

                 /* Sumando Totales de Comision */
                 regRegistro.TOT_COMI := regRegistro.IMP_COMI_INGR + regRegistro.IMP_COMI_RECU;

                 /* Porcentaje de Recupero */
                 IF regRegistro.IMP_NETO > 0 THEN
                    regRegistro.POR_RECU := (regRegistro.IMP_RECU / regRegistro.IMP_NETO) * 100;
                    regRegistro.POR_RECU_TRA2 := ((regRegistro.IMP_RECU_TRA2 + regRegistro.IMP_RECU)/ regRegistro.IMP_NETO) * 100;
                 END IF;
                 INSERT INTO COM_TEMPO_COMIS_RECUP_SECCI(
                  COD_REGI,
                  COD_ZONA,
                  COD_SECC,
                  COD_LIDE_SECC,
                  NOM_LIDE_SECC,
                  LIB_ELEC,
                  NRO_CUEN,
                  NUM_LIDE,
                  NUM_LIDE_GANA_RECU,
                  POR_LIDE_GANA_RECU,
                  IMP_NETO,
                  IMP_RECU,
                  IMP_RECU_TRA2,
                  POR_RECU,
                  POR_RECU_TRA2,
                  IMP_COMI_RECU,
                  IMP_VENT_INGR,
                  IMP_COMI_INGR,
                  IMP_COMI_INGR_ANTE,
                  VAL_PROC_TRA1,
                  VAL_PROC_TRA2,
                  TOT_COMI,
                  NUM_DIAS_COMI,
                  NUM_DIAS_COMI_TRA2,
                  IMP_COMI_TRA1,
                  IMP_COMI_TRA2,
                  IMP_RECU_CIMP,
                  IMP_RECU_TRA2_CIMP,
                  IMP_NETO_SREC_CIMP
                  )
                  VALUES (
                  regRegistro.COD_REGI,
                  regRegistro.COD_ZONA,
                  regRegistro.COD_SECC,
                  regRegistro.COD_LIDE_SECC,
                  regRegistro.NOM_LIDE_SECC,
                  regRegistro.LIB_ELEC,
                  regRegistro.NRO_CUEN,
                  regRegistro.NUM_LIDE,
                  regRegistro.NUM_LIDE_GANA_RECU,
                  regRegistro.POR_LIDE_GANA_RECU,
                  regRegistro.IMP_NETO,
                  regRegistro.IMP_RECU,
                  regRegistro.IMP_RECU_TRA2,
                  regRegistro.POR_RECU,
                  regRegistro.POR_RECU_TRA2,
                  regRegistro.IMP_COMI_RECU,
                  regRegistro.IMP_VENT_INGR,
                  regRegistro.IMP_COMI_INGR,
                  regRegistro.IMP_COMI_INGR_ANTE,
                  regRegistro.VAL_PROC_TRA1,
                  regRegistro.VAL_PROC_TRA2,
                  regRegistro.TOT_COMI,
                  regRegistro.NUM_DIAS_COMI,
                  regRegistro.NUM_DIAS_COMI_TRA2,
                  regRegistro.IMP_COMI_TRA1,
                  regRegistro.IMP_COMI_TRA2,
                  regRegistro.IMP_RECU_CIMP,
                  regRegistro.IMP_RECU_TRA2_CIMP,
                  regRegistro.IMP_NETO_SREC_CIMP
                  );
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_OBTIE_COMIS_RECUP_SECC: '||ls_sqlerrm);
END COM_PR_OBTIE_COMIS_RECUP_SECC;


/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Comision de Recuperacion
                    (Resumen x Zona)
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_COMIS_RECUP_ZONA(
    psCodPeriodo         VARCHAR2,
    psCodComision        VARCHAR2,
    psCodComisionIngreso VARCHAR2)
IS
 tablaRegistro      tTablaComisionRecuZona;
 regRegistro        tRegComisionRecuZona;
 lnIndDctoImportePago com_comis.ind_desc_impo_vent_pago%TYPE;
 lnIdComision       NUMBER;
 lnContadorIngreso  NUMBER;
 lnContadorRecupero NUMBER;

 CURSOR cursorRegistro IS
   SELECT DISTINCT
     A.COD_REGI,
     A.COD_ZONA,
     '' AS COD_LIDE_ZONA,
     '' AS NOM_LIDE_ZONA,
     0  AS NUM_LIDE,
     0  AS NUM_LIDE_GANA_RECU,
     0  AS NUM_LIDE_GANA_INGR,
     0  AS POR_LIDE_GANA_RECU,
     0  AS POR_LIDE_GANA_INGR,
     0  AS IMP_NETO,
     0  AS IMP_NETO_SREC_CIMP,
     0  AS IMP_RECU,
     0  AS IMP_RECU_TRA2,
     0  AS IMP_RECU_CIMP,
     0  AS IMP_RECU_TRA2_CIMP,
     0  AS POR_RECU ,
     0  AS POR_RECU_TRA2,
     0  AS IMP_COMI_RECU,
     0  AS IMP_VENT_INGR,
     0  AS IMP_COMI_INGR,
     0  AS IMP_COMI_INGR_ANTE,
     0  AS TOT_COMI,
     0  AS IMP_GANA_LIDE,
     0  AS NUM_DIAS_COMI,
     0  AS NUM_DIAS_COMI_TRA2,
     0  AS IMP_COMI_TRA1,
     0  AS IMP_COMI_TRA2,
     0  AS VAL_PROC_TRA1,
     0  AS VAL_PROC_TRA2
   FROM COM_COMIS_PERIO_CALCU A
   WHERE A.PERI_COD_PERI = psCodPeriodo
    AND ((A.COD_COMI = psCodComision AND A.IND_COMI = '02') OR
         (A.COD_COMI = psCodComisionIngreso AND A.IND_COMI = '01'))
   ORDER BY A.COD_REGI, A.COD_ZONA  ;

 BEGIN
   DELETE FROM COM_TEMPO_COMIS_RECUP_ZONA;

   lnIdComision   := COM_PKG_REPOR.COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

   /* Obtenemos los valores de los flags y el porcentaje de actividad */
   SELECT A.IND_DESC_IMPO_VENT_PAGO
   INTO lnIndDctoImportePago
   FROM COM_COMIS A
   WHERE A.OID_COMI = lnIdComision;

   OPEN cursorRegistro;
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 regRegistro := tablaRegistro(x);

                 /* Encontrando datos del Lider de Zona */
                 BEGIN
                   SELECT A.COD_LIDE_ZONA, A.NOM_LIDE_ZONA
                   INTO
                        regRegistro.COD_LIDE_ZONA,
                        regRegistro.NOM_LIDE_ZONA
                   FROM COM_REPOR_COMIS_RECUP A
                   WHERE A.COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA
                     AND ROWNUM = 1;
                 EXCEPTION
                 WHEN no_data_found THEN
                      BEGIN
                          SELECT A.COD_LIDE_ZONA, A.NOM_LIDE_ZONA
                          INTO
                              regRegistro.COD_LIDE_ZONA,
                              regRegistro.NOM_LIDE_ZONA
                          FROM COM_REPOR_COMIS_INGRE A
                          WHERE A.COD_PERI_ACTU = psCodPeriodo
                            AND A.COD_COMI = psCodComisionIngreso
                            AND A.COD_REGI = regRegistro.COD_REGI
                            AND A.COD_ZONA = regRegistro.COD_ZONA
                            AND ROWNUM = 1;
                       EXCEPTION
                       WHEN no_data_found THEN
                            regRegistro.COD_LIDE_ZONA := NULL;
                            regRegistro.NOM_LIDE_ZONA := NULL;
                       END;

                 END;
                 /* Encontrando los Porcentajes de Retribucion*/
                 BEGIN
                   SELECT A.VAL_PORC_COMI,
                          A.VAL_PORC_COMI_TRA2,
                          A.NUM_DIAS_COMI,
                          A.NUM_DIAS_COMI_TRA2
                   INTO   regRegistro.VAL_PROC_TRA1,
                          regRegistro.VAL_PROC_TRA2,
                          regRegistro.NUM_DIAS_COMI,
                          regRegistro.NUM_DIAS_COMI_TRA2
                   FROM COM_REPOR_COMIS_RECUP A
                   WHERE A.COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND ROWNUM = 1;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                       regRegistro.VAL_PROC_TRA1 := 0;
                       regRegistro.VAL_PROC_TRA2:= 0;
                       regRegistro.NUM_DIAS_COMI:= 0;
                       regRegistro.NUM_DIAS_COMI_TRA2:= 0;
                 END ;

                 /* Encontrando importes para Comision de Recuperaci�n */
                 BEGIN
                 IF lnIndDctoImportePago = 1 THEN
                     SELECT
                         SUM(A.IMP_NETO_SIN_RECL),
                         SUM(A.IMP_NETO_SREC_CIMP),
                         SUM(A.IMP_PAGO_ANTE_LIMI),
                         SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
                         SUM(nvl(A.IMP_COMI_TRA1,0) + nvl(A.IMP_COMI_TRA2,0)),
                         SUM(A.IMP_COMI_TRA1),
                         SUM(A.IMP_COMI_TRA2)
                       INTO
                         regRegistro.IMP_NETO,
                         regRegistro.IMP_NETO_SREC_CIMP,
                         regRegistro.IMP_RECU,
                         regRegistro.IMP_RECU_TRA2,
                         regRegistro.IMP_COMI_RECU,
                         regRegistro.IMP_COMI_TRA1,
                         regRegistro.IMP_COMI_TRA2
                       FROM COM_COMIS_PERIO_CALCU A
                       WHERE A.PERI_COD_PERI = psCodPeriodo
                         AND A.COD_COMI = psCodComision
                         AND A.IND_COMI = '02'
                         AND A.COD_REGI = regRegistro.COD_REGI
                         AND A.COD_ZONA = regRegistro.COD_ZONA;
                 ELSE
                     SELECT
                         SUM(A.IMP_NETO_SIN_RECL),
                         SUM(A.IMP_NETO_SREC_CIMP),
                         SUM(A.IMP_PAGO_ANTE_LIMI_CIMP),
                         SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
                         SUM(nvl(A.IMP_COMI_TRA1,0) + nvl(A.IMP_COMI_TRA2,0)),
                         SUM(A.IMP_COMI_TRA1),
                         SUM(A.IMP_COMI_TRA2)
                       INTO
                         regRegistro.IMP_NETO,
                         regRegistro.IMP_NETO_SREC_CIMP,
                         regRegistro.IMP_RECU_CIMP,
                         regRegistro.IMP_RECU_TRA2_CIMP,
                         regRegistro.IMP_COMI_RECU,
                         regRegistro.IMP_COMI_TRA1,
                         regRegistro.IMP_COMI_TRA2
                       FROM COM_COMIS_PERIO_CALCU A
                       WHERE A.PERI_COD_PERI = psCodPeriodo
                         AND A.COD_COMI = psCodComision
                         AND A.IND_COMI = '02'
                         AND A.COD_REGI = regRegistro.COD_REGI
                         AND A.COD_ZONA = regRegistro.COD_ZONA;
                 END IF;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_NETO :=0;
                     regRegistro.IMP_NETO_SREC_CIMP :=0;
                     regRegistro.IMP_RECU :=0;
                     regRegistro.IMP_RECU_TRA2 :=0;
                     regRegistro.IMP_RECU_CIMP :=0;
                     regRegistro.IMP_RECU_TRA2_CIMP :=0;
                     regRegistro.IMP_COMI_RECU :=0;
                     regRegistro.IMP_COMI_TRA1 :=0;
                     regRegistro.IMP_COMI_TRA2 :=0;
                 END ;

                 /* Encontrando importes para Comision de Ingreso */
                 BEGIN
                   SELECT
                     SUM(A.IMP_NETO_SIN_RECL),
                     SUM(A.IMP_COMI),
                     SUM(A.IMP_COMI_DCTO)
                   INTO
                     regRegistro.IMP_VENT_INGR,
                     regRegistro.IMP_COMI_INGR,
                     regRegistro.IMP_COMI_INGR_ANTE
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComisionIngreso
                     AND A.IND_COMI = '01'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_VENT_INGR := 0;
                     regRegistro.IMP_COMI_INGR := 0;
                      regRegistro.IMP_COMI_INGR_ANTE :=0;
                 END ;

                 /* Encontrando # de Lideres */
                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     lnContadorIngreso
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComisionIngreso
                     AND A.IND_COMI = '01'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     lnContadorIngreso := 0;
                 END ;
                 regRegistro.NUM_LIDE := lnContadorIngreso;

                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     lnContadorRecupero
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     lnContadorRecupero := 0;
                 END ;
                 IF lnContadorRecupero > lnContadorIngreso THEN
                    regRegistro.NUM_LIDE := lnContadorRecupero;
                 END IF;

                 /* Encontrando Lideres Ganadoras Comision de Ingreso */
                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     regRegistro.NUM_LIDE_GANA_INGR
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComisionIngreso
                     AND A.IND_COMI = '01'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA
                     AND A.IMP_COMI > 0;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.NUM_LIDE_GANA_INGR := 0;
                 END ;

                 /* Encontrando Lideres Ganadoras Comision de Recupero */
                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     regRegistro.NUM_LIDE_GANA_RECU
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA
                      AND A.IMP_COMI_TRA1+ A.IMP_COMI_TRA2 > 0;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.NUM_LIDE_GANA_RECU := 0;
                 END ;

                 /* Obteniendo % ganadores */
                 IF regRegistro.NUM_LIDE > 0 THEN
                    regRegistro.POR_LIDE_GANA_RECU := (regRegistro.NUM_LIDE_GANA_RECU / regRegistro.NUM_LIDE) * 100;
                    regRegistro.POR_LIDE_GANA_INGR := (regRegistro.NUM_LIDE_GANA_INGR / regRegistro.NUM_LIDE) * 100;
                 END IF;

                 /* Sumando Totales de Comision */
                 regRegistro.TOT_COMI := regRegistro.IMP_COMI_INGR + regRegistro.IMP_COMI_RECU- regRegistro.IMP_COMI_INGR_ANTE;

                 /* Ganancia de Lideres */
                 IF regRegistro.NUM_LIDE_GANA_RECU > 0 THEN
                    regRegistro.IMP_GANA_LIDE := regRegistro.IMP_COMI_RECU / regRegistro.NUM_LIDE_GANA_RECU;
                 END IF;

                 /* Porcentaje de Recupero */
                 IF regRegistro.IMP_NETO > 0 THEN
                    regRegistro.POR_RECU := (regRegistro.IMP_RECU / regRegistro.IMP_NETO) * 100;
                    regRegistro.POR_RECU_TRA2 := ((regRegistro.IMP_RECU_TRA2 +regRegistro.IMP_RECU)/ regRegistro.IMP_NETO) * 100;
                 END IF;

                  INSERT INTO COM_TEMPO_COMIS_RECUP_ZONA(
                      COD_REGI,
                      COD_ZONA,
                      COD_LIDE_ZONA,
                      NOM_LIDE_ZONA,
                      NUM_LIDE,
                      NUM_LIDE_GANA_RECU,
                      NUM_LIDE_GANA_INGR,
                      POR_LIDE_GANA_RECU,
                      POR_LIDE_GANA_INGR,
                      IMP_NETO,
                      IMP_RECU,
                      IMP_RECU_TRA2,
                      POR_RECU,
                      POR_RECU_TRA2,
                      IMP_COMI_RECU,
                      IMP_VENT_INGR,
                      IMP_COMI_INGR,
                      IMP_COMI_INGR_ANTE,
                      VAL_PROC_TRA1,
                      VAL_PROC_TRA2,
                      TOT_COMI,
                      IMP_GANA_LIDE,
                      NUM_DIAS_COMI,
                      NUM_DIAS_COMI_TRA2,
                      IMP_COMI_TRA1,
                      IMP_COMI_TRA2,
                      IMP_RECU_CIMP,
                      IMP_RECU_TRA2_CIMP,
                      IMP_NETO_SREC_CIMP
                     )
                     VALUES (
                      regRegistro.COD_REGI,
                      regRegistro.COD_ZONA,
                      regRegistro.COD_LIDE_ZONA,
                      regRegistro.NOM_LIDE_ZONA,
                      regRegistro.NUM_LIDE,
                      regRegistro.NUM_LIDE_GANA_RECU,
                      regRegistro.NUM_LIDE_GANA_INGR,
                      regRegistro.POR_LIDE_GANA_RECU,
                      regRegistro.POR_LIDE_GANA_INGR,
                      regRegistro.IMP_NETO,
                      regRegistro.IMP_RECU,
                      regRegistro.IMP_RECU_TRA2,
                      regRegistro.POR_RECU,
                      regRegistro.POR_RECU_TRA2,
                      regRegistro.IMP_COMI_RECU,
                      regRegistro.IMP_VENT_INGR,
                      regRegistro.IMP_COMI_INGR,
                      regRegistro.IMP_COMI_INGR_ANTE,
                      regRegistro.VAL_PROC_TRA1,
                      regRegistro.VAL_PROC_TRA2,
                      regRegistro.TOT_COMI,
                      regRegistro.IMP_GANA_LIDE,
                      regRegistro.NUM_DIAS_COMI,
                      regRegistro.NUM_DIAS_COMI_TRA2,
                      regRegistro.IMP_COMI_TRA1,
                      regRegistro.IMP_COMI_TRA2,
                      regRegistro.IMP_RECU_CIMP,
                      regRegistro.IMP_RECU_TRA2_CIMP,
                      regRegistro.IMP_NETO_SREC_CIMP
                     );
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_OBTIE_COMIS_RECUP_ZONA: '||ls_sqlerrm);
END COM_PR_OBTIE_COMIS_RECUP_ZONA;


/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Comision de Recuperacion
                    (Resumen x Region)
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_COMIS_RECUP_REGIO(
    psCodPeriodo      VARCHAR2,
    psCodComision     VARCHAR2,
    psCodComisionIngreso VARCHAR2)
IS
 tablaRegistro      tTablaComisionRecuRegion;
 regRegistro        tRegComisionRecuRegion;
 lnIndDctoImportePago com_comis.ind_desc_impo_vent_pago%TYPE;
 lnIdComision       NUMBER;
 lnContadorIngreso  NUMBER;
 lnContadorRecupero NUMBER;

 CURSOR cursorRegistro IS
   SELECT DISTINCT
     A.COD_REGI,
     '' AS DES_REGI,
     0  AS NUM_LIDE,
     0  AS NUM_LIDE_GANA_RECU,
     0  AS NUM_LIDE_GANA_INGR,
     0  AS POR_LIDE_GANA_RECU,
     0  AS POR_LIDE_GANA_INGR,
     0  AS IMP_NETO,
     0  AS IMP_NETO_SREC_CIMP,
     0  AS IMP_RECU,
     0  AS IMP_RECU_TRA2,
     0  AS IMP_RECU_CIMP,
     0  AS IMP_RECU_TRA2_CIMP,
     0  AS POR_RECU  ,
     0  AS POR_RECU_TRA2,
     0  AS IMP_COMI_RECU,
     0  AS IMP_VENT_INGR,
     0  AS IMP_COMI_INGR,
     0  AS IMP_COMI_INGR_ANTE,
     0  AS VAL_PROC_TRA1,
     0  AS VAL_PROC_TRA2,
     0  AS TOT_COMI,
     0  AS NUM_DIAS_COMI,
     0  AS NUM_DIAS_COMI_TRA2,
     0  AS IMP_COMI_TRA1,
     0  AS IMP_COMI_TRA2
   FROM COM_COMIS_PERIO_CALCU A
   WHERE A.PERI_COD_PERI = psCodPeriodo
    AND ((A.COD_COMI = psCodComision AND A.IND_COMI = '02') OR
         (A.COD_COMI = psCodComisionIngreso AND A.IND_COMI = '01'))
   ORDER BY A.COD_REGI  ;
 BEGIN
   DELETE FROM COM_TEMPO_COMIS_RECUP_REGIO;
   lnIdComision   := COM_PKG_REPOR.COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

   /* Obtenemos los valores de los flags y el porcentaje de actividad */
   SELECT A.IND_DESC_IMPO_VENT_PAGO
   INTO lnIndDctoImportePago
   FROM COM_COMIS A
   WHERE A.OID_COMI = lnIdComision;

   OPEN cursorRegistro;
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 regRegistro := tablaRegistro(x);

                 -- Encontrando descripcion de Region
                 SELECT A.DES_REGI
                 INTO
                    regRegistro.DES_REGI
                 FROM ZON_REGIO A
                 WHERE
                     A.COD_REGI = regRegistro.COD_REGI;

                /* Encontrando los Porcentajes de Retribucion*/
                 BEGIN
                   SELECT A.VAL_PORC_COMI,
                          A.VAL_PORC_COMI_TRA2,
                          A.NUM_DIAS_COMI,
                          A.NUM_DIAS_COMI_TRA2
                   INTO   regRegistro.VAL_PROC_TRA1,
                          regRegistro.VAL_PROC_TRA2,
                          regRegistro.NUM_DIAS_COMI,
                          regRegistro.NUM_DIAS_COMI_TRA2
                   FROM COM_REPOR_COMIS_RECUP A
                   WHERE A.COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND ROWNUM = 1;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                       regRegistro.VAL_PROC_TRA1 := 0;
                       regRegistro.VAL_PROC_TRA2:= 0;
                       regRegistro.NUM_DIAS_COMI:= 0;
                       regRegistro.NUM_DIAS_COMI_TRA2:= 0;
                 END ;

                 -- Encontrando importes para Comision de Recuperaci�n
                 BEGIN
                 IF lnIndDctoImportePago = 1 THEN
                 SELECT
                     SUM(A.IMP_NETO_SIN_RECL),
                     SUM(A.IMP_NETO_SREC_CIMP),
                     SUM(A.IMP_PAGO_ANTE_LIMI),
                     SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
                     SUM(A.IMP_PAGO_ANTE_LIMI_CIMP),
                     SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
                     SUM(nvl(A.IMP_COMI_TRA1,0) + nvl(A.IMP_COMI_TRA2,0)),
                     SUM(A.IMP_COMI_TRA1),
                     SUM(A.IMP_COMI_TRA2)
                   INTO
                     regRegistro.IMP_NETO,
                     regRegistro.IMP_NETO_SREC_CIMP,
                     regRegistro.IMP_RECU,
                     regRegistro.IMP_RECU_TRA2,
                     regRegistro.IMP_RECU_CIMP,
                     regRegistro.IMP_RECU_TRA2_CIMP,
                     regRegistro.IMP_COMI_RECU,
                     regRegistro.IMP_COMI_TRA1,
                     regRegistro.IMP_COMI_TRA2
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI ;
                 ELSE
                 SELECT
                     SUM(A.IMP_NETO_SIN_RECL),
                     SUM(A.IMP_NETO_SREC_CIMP),
                     SUM(A.IMP_PAGO_ANTE_LIMI),
                     SUM(A.IMP_PAGO_ANTE_LIMI_TRA2),
                     SUM(A.IMP_PAGO_ANTE_LIMI_CIMP),
                     SUM(A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP),
                     SUM(nvl(A.IMP_COMI_TRA1,0) + nvl(A.IMP_COMI_TRA2,0)),
                     SUM(A.IMP_COMI_TRA1),
                     SUM(A.IMP_COMI_TRA2)
                   INTO
                     regRegistro.IMP_NETO,
                     regRegistro.IMP_NETO_SREC_CIMP,
                     regRegistro.IMP_RECU,
                     regRegistro.IMP_RECU_TRA2,
                     regRegistro.IMP_RECU_CIMP,
                     regRegistro.IMP_RECU_TRA2_CIMP,
                     regRegistro.IMP_COMI_RECU,
                     regRegistro.IMP_COMI_TRA1,
                     regRegistro.IMP_COMI_TRA2
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI ;
                 END IF;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_NETO :=0;
                     regRegistro.IMP_NETO_SREC_CIMP :=0;
                     regRegistro.IMP_RECU :=0;
                     regRegistro.IMP_RECU_TRA2 :=0;
                     regRegistro.IMP_RECU_CIMP :=0;
                     regRegistro.IMP_RECU_TRA2_CIMP :=0;
                     regRegistro.IMP_COMI_RECU :=0;
                     regRegistro.IMP_COMI_TRA1 :=0;
                     regRegistro.IMP_COMI_TRA2 :=0;
                 END ;

                 -- Encontrando importes para Comision de Ingreso
                 BEGIN
                  SELECT
                     SUM(A.IMP_NETO_SIN_RECL),
                     SUM(A.IMP_COMI),
                     SUM(A.IMP_COMI_DCTO)
                   INTO
                     regRegistro.IMP_VENT_INGR,
                     regRegistro.IMP_COMI_INGR,
                     regRegistro.IMP_COMI_INGR_ANTE
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComisionIngreso
                     AND A.IND_COMI = '01'
                     AND A.COD_REGI = regRegistro.COD_REGI;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_VENT_INGR := 0;
                     regRegistro.IMP_COMI_INGR := 0;
                     regRegistro.IMP_COMI_INGR_ANTE := 0;
                 END ;

                 -- Encontrando # de Lideres
                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     lnContadorIngreso
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComisionIngreso
                     AND A.IND_COMI = '01'
                     AND A.COD_REGI = regRegistro.COD_REGI;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     lnContadorIngreso := 0;
                 END ;
                 regRegistro.NUM_LIDE := lnContadorIngreso;

                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     lnContadorRecupero
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     lnContadorRecupero := 0;
                 END ;
                 IF lnContadorRecupero > lnContadorIngreso THEN
                    regRegistro.NUM_LIDE := lnContadorRecupero;
                 END IF;

                 -- Encontrando Lideres Ganadoras Comision de Ingreso
                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     regRegistro.NUM_LIDE_GANA_INGR
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComisionIngreso
                     AND A.IND_COMI = '01'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.IMP_COMI > 0;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.NUM_LIDE_GANA_INGR := 0;
                 END ;

                 -- Encontrando Lideres Ganadoras Comision de Recupero
                 BEGIN
                   SELECT COUNT(1)
                   INTO
                     regRegistro.NUM_LIDE_GANA_RECU
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psCodComision
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI
                      AND A.IMP_COMI_TRA1+ A.IMP_COMI_TRA2 > 0;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.NUM_LIDE_GANA_RECU := 0;
                 END ;

                 -- Obteniendo % ganadores
                 IF regRegistro.NUM_LIDE > 0 THEN
                    regRegistro.POR_LIDE_GANA_RECU := (regRegistro.NUM_LIDE_GANA_RECU / regRegistro.NUM_LIDE) * 100;
                    regRegistro.POR_LIDE_GANA_INGR := (regRegistro.NUM_LIDE_GANA_INGR / regRegistro.NUM_LIDE) * 100;
                 END IF;

                 -- Sumando Totales de Comision
                 regRegistro.TOT_COMI := regRegistro.IMP_COMI_INGR + regRegistro.IMP_COMI_RECU - regRegistro.IMP_COMI_INGR_ANTE;

                 /* Porcentaje de Recupero */
                 IF regRegistro.IMP_NETO > 0 THEN
                    regRegistro.POR_RECU := (regRegistro.IMP_RECU / regRegistro.IMP_NETO) * 100;
                    regRegistro.POR_RECU_TRA2 := ((regRegistro.IMP_RECU_TRA2 +regRegistro.IMP_RECU) / regRegistro.IMP_NETO) * 100;
                 END IF;

                  INSERT INTO COM_TEMPO_COMIS_RECUP_REGIO(
                    COD_REGI,
                    DES_REGI,
                    NUM_LIDE,
                    NUM_LIDE_GANA_RECU,
                    NUM_LIDE_GANA_INGR,
                    POR_LIDE_GANA_RECU,
                    POR_LIDE_GANA_INGR,
                    IMP_NETO,
                    IMP_RECU,
                    IMP_RECU_TRA2,
                    POR_RECU,
                    POR_RECU_TRA2,
                    IMP_COMI_RECU,
                    IMP_VENT_INGR,
                    IMP_COMI_INGR,
                    IMP_COMI_INGR_ANTE,
                    VAL_PROC_TRA1,
                    VAL_PROC_TRA2,
                    TOT_COMI,
                    NUM_DIAS_COMI,
                    NUM_DIAS_COMI_TRA2,
                    IMP_COMI_TRA1,
                    IMP_COMI_TRA2,
                    IMP_RECU_CIMP,
                    IMP_RECU_TRA2_CIMP,
                    IMP_NETO_SREC_CIMP
                  )
                     VALUES (
                    regRegistro.COD_REGI,
                    regRegistro.DES_REGI,
                    regRegistro.NUM_LIDE,
                    regRegistro.NUM_LIDE_GANA_RECU,
                    regRegistro.NUM_LIDE_GANA_INGR,
                    regRegistro.POR_LIDE_GANA_RECU,
                    regRegistro.POR_LIDE_GANA_INGR,
                    regRegistro.IMP_NETO,
                    regRegistro.IMP_RECU,
                    regRegistro.IMP_RECU_TRA2,
                    regRegistro.POR_RECU,
                    regRegistro.POR_RECU_TRA2,
                    regRegistro.IMP_COMI_RECU,
                    regRegistro.IMP_VENT_INGR,
                    regRegistro.IMP_COMI_INGR,
                    regRegistro.IMP_COMI_INGR_ANTE,
                    regRegistro.VAL_PROC_TRA1,
                    regRegistro.VAL_PROC_TRA2,
                    regRegistro.TOT_COMI,
                    regRegistro.NUM_DIAS_COMI,
                    regRegistro.NUM_DIAS_COMI_TRA2,
                    regRegistro.IMP_COMI_TRA1,
                    regRegistro.IMP_COMI_TRA2,
                    regRegistro.IMP_RECU_CIMP,
                    regRegistro.IMP_RECU_TRA2_CIMP,
                    regRegistro.IMP_NETO_SREC_CIMP
                  );
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_OBTIE_COMIS_RECUP_REGIO: '||ls_sqlerrm);
END COM_PR_OBTIE_COMIS_RECUP_REGIO;


/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Comision de Recuperacion
                    (Resumen x Zona)
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE COM_PR_OBTIE_COMIS_RECUP_GZONA(
    psCodPeriodo     VARCHAR2,
    psCodComision    VARCHAR2)
IS
 TYPE tTablaRegistro IS TABLE OF COM_TEMPO_COMIS_RECUP_GZONA%ROWTYPE;
 regRegistro        COM_TEMPO_COMIS_RECUP_GZONA%ROWTYPE;
 tablaRegistro      tTablaRegistro;
 lnIndDctoImportePago com_comis.ind_desc_impo_vent_pago%TYPE;
 lnIdComision       NUMBER;
 lnIdLideZona       NUMBER;
 lbInsertar         BOOLEAN;

 CURSOR cursorRegistro IS
   SELECT DISTINCT
     A.COD_REGI,
     A.COD_ZONA,
     A.COD_EMPL AS COD_PLAN,
     A.COD_LIDE_ZONA,
     A.NOM_LIDE_ZONA,
     0  AS IMP_NETO,
     0  AS IMP_NETO_CIMP,
     0  AS IMP_RECU,
     0  AS IMP_RECU_CIMP,
     0  AS IMP_COMI_RECU,
     0  AS IMP_RECU_TRA2,
     0  AS IMP_RECU_TRA2_CIMP,
     0  AS IMP_COMI_RECU_TRA2,
     0  AS POR_RECU,
     0  AS POR_RECU_TRA2
   FROM COM_COMIS_PERIO_CALCU_ZONA A
   WHERE A.PERI_COD_PERI = psCodPeriodo
     AND A.COD_COMI = psCodComision
   ORDER BY A.COD_REGI, A.COD_ZONA  ;

 BEGIN
   DELETE FROM com_tempo_comis_recup_gzona;
   lnIdComision   := COM_PKG_REPOR.COM_FN_DEVUE_ULTI_OID_COMI(psCodComision);

   /* Obtenemos los valores de los flags y el porcentaje de actividad */
   SELECT A.IND_DESC_IMPO_VENT_PAGO
   INTO lnIndDctoImportePago
   FROM COM_COMIS A
   WHERE A.OID_COMI = lnIdComision;

   OPEN cursorRegistro;
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 regRegistro := tablaRegistro(x);
                 lbInsertar := TRUE;

                 /* Encontrando importes para Comision de Recuperaci�n */
                 BEGIN
                   IF lnIndDctoImportePago = 1 THEN
                     SELECT
                       A.IMP_NETO_SIN_RECL,
                       A.IMP_NETO_SREC_CIMP,
                       A.IMP_PAGO_ANTE_LIMI,
                       A.IMP_PAGO_ANTE_LIMI_CIMP,
                       A.IMP_COMI_TRA1,
                       A.IMP_PAGO_ANTE_LIMI_TRA2,
                       A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
                       A.IMP_COMI_TRA2,
                       A.POR_RECU,
                       A.POR_RECU_TRA2
                     INTO
                       regRegistro.IMP_NETO,
                       regRegistro.IMP_NETO_CIMP,
                       regRegistro.IMP_RECU,
                       regRegistro.IMP_RECU_CIMP,
                       regRegistro.IMP_COMI_RECU,
                       regRegistro.IMP_RECU_TRA2,
                       regRegistro.IMP_RECU_TRA2_CIMP,
                       regRegistro.IMP_COMI_RECU_TRA2,
                       regRegistro.POR_RECU,
                       regRegistro.POR_RECU_TRA2

                     FROM COM_COMIS_PERIO_CALCU_ZONA A
                     WHERE A.PERI_COD_PERI = psCodPeriodo
                       AND A.COD_COMI = psCodComision
                       AND A.COD_REGI = regRegistro.COD_REGI
                       AND A.COD_ZONA = regRegistro.COD_ZONA;
                   ELSE
                     SELECT
                       A.IMP_NETO_SIN_RECL,
                       A.IMP_NETO_SREC_CIMP,
                       A.IMP_PAGO_ANTE_LIMI,
                       A.IMP_PAGO_ANTE_LIMI_CIMP,
                       A.IMP_COMI_TRA1,
                       A.IMP_PAGO_ANTE_LIMI_TRA2,
                       A.IMP_PAGO_ANTE_LIMI_TRA2_CIMP,
                       A.IMP_COMI_TRA2,
                       A.POR_RECU,
                       A.POR_RECU_TRA2
                     INTO
                       regRegistro.IMP_NETO,
                       regRegistro.IMP_NETO_CIMP,
                       regRegistro.IMP_RECU,
                       regRegistro.IMP_RECU_CIMP,
                       regRegistro.IMP_COMI_RECU,
                       regRegistro.IMP_RECU_TRA2,
                       regRegistro.IMP_RECU_TRA2_CIMP,
                       regRegistro.IMP_COMI_RECU_TRA2,
                       regRegistro.POR_RECU,
                       regRegistro.POR_RECU_TRA2

                     FROM COM_COMIS_PERIO_CALCU_ZONA A
                     WHERE A.PERI_COD_PERI = psCodPeriodo
                       AND A.COD_COMI = psCodComision
                       AND A.COD_REGI = regRegistro.COD_REGI
                       AND A.COD_ZONA = regRegistro.COD_ZONA;
                   END IF;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_NETO := 0;
                     regRegistro.IMP_NETO_CIMP := 0;
                     regRegistro.IMP_RECU := 0;
                     regRegistro.IMP_RECU_CIMP := 0;
                     regRegistro.IMP_COMI_RECU := 0;
                     regRegistro.IMP_RECU_TRA2 := 0;
                     regRegistro.IMP_RECU_TRA2_CIMP := 0;
                     regRegistro.IMP_COMI_RECU_TRA2 := 0;
                     regRegistro.POR_RECU := 0;
                     regRegistro.POR_RECU_TRA2 := 0;
                 END ;

                 INSERT INTO COM_TEMPO_COMIS_RECUP_GZONA
                 VALUES regRegistro;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_PR_OBTIE_COMIS_RECUP_GZONA: '||ls_sqlerrm);
END COM_PR_OBTIE_COMIS_RECUP_GZONA;

/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de Pago de Lideres Suspendidas
Fecha Creacion 	  : 03/04/2007
Autor             : Carlos Bazalar
***************************************************************************/
FUNCTION COM_FN_OBTIE_COMIS_LIDER_SUSPE(
    psCodPais          VARCHAR2,
    psCodPeriodo       VARCHAR2,
    psComisionIngreso  VARCHAR2,
    psComisionRecu     VARCHAR2)
RETURN  tTablaPagoLideresSuspe PIPELINED
IS
 tablaRegistro      tTablaPagoLideresSuspe;
 regRegistro        tRegPagoLideresSuspe;

  CURSOR cursorRegistro IS
   SELECT DISTINCT
     A.COD_REGI,
     A.COD_ZONA,
     A.COD_SECC,
     A.COD_LIDE_SECC,
     A.NOM_LIDE_SECC,
     0  AS IMP_COMI_RECU,
     0  AS IMP_COMI_INGR,
     0  AS TOT_COMI
   FROM COM_COMIS_PERIO_CALCU A,
        MAE_CLIEN_LIDER_BLOQU X
   WHERE A.PERI_COD_PERI = psCodPeriodo
     AND ((A.COD_COMI = psComisionIngreso AND A.IND_COMI = '01') OR
          (A.COD_COMI = psComisionRecu   AND A.IND_COMI = '02')  )
     AND X.PAIS_COD_PAIS = psCodPais
	   AND X.COD_CLIB = A.COD_LIDE_SECC
     AND X.EST_CLIB = '1'
	   AND (psCodPeriodo >= X.PER_INIC)
		 AND (psCodPeriodo <= X.PER_FINA OR X.PER_FINA IS NULL)
   ORDER BY A.COD_REGI, A.COD_ZONA, A.COD_SECC, A.COD_LIDE_SECC  ;

 BEGIN

      OPEN cursorRegistro;
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                 regRegistro := tablaRegistro(x);

                 /* Encontrando Comision de Ingreso */
                 BEGIN
                   SELECT nvl(A.IMP_COMI,0) - nvl(A.IMP_COMI_DCTO,0)
                   INTO
                     regRegistro.IMP_COMI_INGR
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psComisionIngreso
                     AND A.IND_COMI = '01'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA
                     AND A.COD_SECC = regRegistro.COD_SECC
                     AND A.COD_LIDE_SECC = regRegistro.COD_LIDE_SECC;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_COMI_INGR := 0;
                 END ;

                 /* Encontrando Comision de Recuperacion */
                 BEGIN
                   SELECT nvl(A.IMP_COMI_TRA1,0) + nvl(A.IMP_COMI_TRA2,0)
                   INTO
                     regRegistro.IMP_COMI_RECU
                   FROM COM_COMIS_PERIO_CALCU A
                   WHERE A.PERI_COD_PERI = psCodPeriodo
                     AND A.COD_COMI = psComisionRecu
                     AND A.IND_COMI = '02'
                     AND A.COD_REGI = regRegistro.COD_REGI
                     AND A.COD_ZONA = regRegistro.COD_ZONA
                     AND A.COD_SECC = regRegistro.COD_SECC
                     AND A.COD_LIDE_SECC = regRegistro.COD_LIDE_SECC;
                 EXCEPTION
                 WHEN NO_DATA_FOUND THEN
                     regRegistro.IMP_COMI_RECU := 0;
                 END ;
                 regRegistro.TOT_COMI := regRegistro.IMP_COMI_RECU + regRegistro.IMP_COMI_INGR;

                 PIPE ROW(regRegistro);
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_OBTIE_COMIS_LIDER_SUSPE : '||ls_sqlerrm);
END COM_FN_OBTIE_COMIS_LIDER_SUSPE;


/***************************************************************************
Descripcion    	  : Devuelve Listado para Reporte de comision de Comercializacion
Fecha Creacion 	  : 03/05/2007
Autor             : Carlos Bazalar
 psTipoComision   : G Gerente de Zona Antigua
                    P Promotora
***************************************************************************/
FUNCTION COM_FN_OBTIE_REPOR_COMER(
    psCodPais          VARCHAR2,
    psFechaIni         VARCHAR2,
    psFechaFin         VARCHAR2,
    psCodComision      VARCHAR2,
    psTipoComision     VARCHAR2)
RETURN  tTablaReporteComisionComer PIPELINED
IS
 tablaRegistro      tTablaReporteComisionComer;
 regRegistro        tRegReporteComisionComer;
 lnIdPais           NUMBER;
 lnNumPorc          NUMBER;
 lnImpMontoTope     NUMBER;
 lsCodEmpl          MAE_CLIEN_DATOS_ADICI.Cod_Empl%TYPE;

  CURSOR cursorRegistro IS
   SELECT
     A.FEC_INIC,
     A.FEC_FINA,
     A.COD_REGI,
     A.COD_ZONA,
     A.COD_EMPL AS COD_LIDE_ZONA,
     A.NOM_LIDE_ZONA,
     A.TIP_COMI,
     A.IMP_PAGO,
     A.IMP_COMI,
     0 AS IMP_CALC,
     0 AS IMP_RESU

   FROM COM_COMIS_PERIO_CALCU_COMER A
   WHERE A.FEC_INIC = TO_DATE(psFechaIni,'DD/MM/YYYY')
     AND A.FEC_FINA = TO_DATE(psFechaFin,'DD/MM/YYYY')
     AND A.COD_COMI = psCodComision
   ORDER BY A.COD_REGI, A.COD_ZONA, A.COD_LIDE_ZONA;

 BEGIN
      lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
      OPEN cursorRegistro;
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                  regRegistro := tablaRegistro(x);

                  /* Encontrando tope y Importe calculado */
                  IF psTipoComision = 'P' AND regRegistro.TIP_COMI = 'P' THEN
                     BEGIN
                        SELECT A.NUM_PORC, A.IMP_MONT_TOPE
                        INTO
                           lnNumPorc, lnImpMontoTope
                        FROM
                          COM_COMIS_PROMO A
                        WHERE A.COD_ZONA = regRegistro.COD_ZONA;
                        regRegistro.IMP_CALC := lnNumPorc * regRegistro.IMP_COMI;
                        IF regRegistro.IMP_CALC > lnImpMontoTope THEN
                           regRegistro.IMP_RESU := lnImpMontoTope;
                        ELSE
                           regRegistro.IMP_RESU := regRegistro.IMP_CALC;
                        END IF;
                     EXCEPTION
                     WHEN no_data_found THEN
                          regRegistro.IMP_CALC := 0;
                          regRegistro.IMP_RESU := 0;
                     END;
                  ELSE
                     regRegistro.IMP_RESU := regRegistro.IMP_COMI;
                  END IF;
                  IF psTipoComision = regRegistro.TIP_COMI THEN
                     PIPE ROW(regRegistro);
                  END IF;
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_OBTIE_REPOR_COMER : '||ls_sqlerrm);
END COM_FN_OBTIE_REPOR_COMER;


/***************************************************************************
Descripcion    	  : Devuelve Listado de Responsables UA de acuerdo al
                    Tipo de Unidad
Fecha Creacion 	  : 20/06/2007
Autor             : Carlos Bazalar
 psTipoUnidad     : 1 SubGerencia
                    2 Region
                    3 Zona
                    4 Secci�n
 psTipoConsulta   : T Todas
                    C Con Responsable
                    S Sin Responsable
***************************************************************************/
FUNCTION COM_FN_OBTIE_RESPO_UA(
    psCodPais          VARCHAR2,
    psCodCanal         VARCHAR2,
    psCodMarca         VARCHAR2,
    psTipoUnidad       VARCHAR2,
    psCodPeriodo       VARCHAR2,
    psCodZona          VARCHAR2,
    psTipoConsulta     VARCHAR2
)
RETURN  tTablaResponsablesUA PIPELINED
IS
 lnIdPais           NUMBER;

 lsTelefono         MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE;
 lnOidPeriodo       NUMBER;
 lnOidPeriodoAnt    NUMBER;
 lnNumActiInic      NUMBER;
 lnNumActiFina      NUMBER;
 lnNumTotal         NUMBER;
 lnOidPeriodoAnt2   NUMBER;

 tablaRegistro      tTablaResponsablesUA;
 regRegistro        tRegTablaResponsablesUA;
 /* INI JJ VEN-SiCC-2?012-0125 */

 CURSOR c1(vnIdPais NUMBER,vnOidPeriodo NUMBER) IS
  SELECT
        TABLA.*
    FROM (
       SELECT
         A.COD_SUBG_VENT,
         A.DES_SUBG_VENT,
         B.COD_REGI,
         B.COD_REGI || ' - ' || B.DES_REGI des_regi,
         NULL COD_ZONA,
         NULL DES_ZONA,
         NULL OID_SECC,
         NULL COD_SECC,
         NULL C01,
         NULL C02,
         NULL C03,
         NULL C04,
         NULL C05,
         NULL C06,
         NULL C07,
         NULL C08,
         NULL C09,
         NULL C10,
         NULL C11,
         NULL C12,
         NULL C13,
         NULL C14,
         NULL C15,
         NULL C16
       FROM
         ZON_SUB_GEREN_VENTA A,
         ZON_REGIO B
       WHERE A.PAIS_OID_PAIS = vnIdPais
         AND A.OID_SUBG_VENT = B.ZSGV_OID_SUBG_VENT
         AND (vnOidPeriodo >= B.PERD_OID_PERI_INIC)
         AND (vnOidPeriodo <= B.PERD_OID_PERI_FINA or B.PERD_OID_PERI_FINA IS NULL)
       UNION
       SELECT
         A.COD_SUBG_VENT,
         A.DES_SUBG_VENT,
         B.COD_REGI,
         B.COD_REGI || ' - ' || B.DES_REGI des_regi,
         C.COD_ZONA,
         C.DES_ZONA,
         NULL OID_SECC,
         NULL COD_SECC,
         NULL C01,
         NULL C02,
         NULL C03,
         NULL C04,
         NULL C05,
         NULL C06,
         NULL C07,
         NULL C08,
         NULL C09,
         NULL C10,
         NULL C11,
         NULL C12,
         NULL C13,
         NULL C14,
         NULL C15,
         NULL C16
       FROM
         ZON_SUB_GEREN_VENTA A,
         ZON_REGIO B,
         ZON_ZONA C
       WHERE A.PAIS_OID_PAIS = vnIdPais
         AND A.OID_SUBG_VENT = B.ZSGV_OID_SUBG_VENT
         AND B.OID_REGI = C.ZORG_OID_REGI
         AND (vnOidPeriodo >= C.PERD_OID_PERI_INIC)
         AND (vnOidPeriodo <= C.PERD_OID_PERI_FINA or C.PERD_OID_PERI_FINA IS NULL)
         AND C.COD_ZONA = DECODE(psCodZona, NULL, C.COD_ZONA, psCodZona)
     UNION
     SELECT
     A.COD_SUBG_VENT,
     A.DES_SUBG_VENT,
     B.COD_REGI,
         B.COD_REGI || ' - ' || B.DES_REGI des_regi,
     C.COD_ZONA,
     C.DES_ZONA,
     D.OID_SECC,
     D.COD_SECC,
         NULL C01,
         NULL C02,
         NULL C03,
         NULL C04,
         NULL C05,
         NULL C06,
         NULL C07,
         NULL C08,
         NULL C09,
         NULL C10,
         NULL C11,
         NULL C12,
         NULL C13,
         NULL C14,
         NULL C15,
         NULL C16
   FROM
     ZON_SUB_GEREN_VENTA A,
     ZON_REGIO B,
     ZON_ZONA  C,
     ZON_SECCI D
   WHERE A.PAIS_OID_PAIS = vnIdPais
     AND A.OID_SUBG_VENT = B.ZSGV_OID_SUBG_VENT
     AND B.OID_REGI = C.ZORG_OID_REGI
     AND C.OID_ZONA = D.ZZON_OID_ZONA
     AND (vnOidPeriodo >= D.PERD_OID_PERI_INIC)
     AND (vnOidPeriodo <= D.PERD_OID_PERI_FINA or D.PERD_OID_PERI_FINA IS NULL)
     AND C.COD_ZONA = DECODE(psCodZona, NULL, C.COD_ZONA, psCodZona)
         ) TABLA
    ORDER BY TABLA.COD_SUBG_VENT, TABLA.COD_REGI NULLS FIRST, TABLA.COD_ZONA NULLS FIRST, TABLA.COD_SECC NULLS FIRST;


  CURSOR c2(vnIdPais NUMBER,vnOidPeriodo NUMBER) IS
   SELECT
     A.COD_SUBG_VENT,
     A.DES_SUBG_VENT,
     B.COD_REGI,
     B.COD_REGI || ' - ' || B.DES_REGI,
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
     NULL
   FROM
     ZON_SUB_GEREN_VENTA A,
     ZON_REGIO B
   WHERE A.PAIS_OID_PAIS = vnIdPais
     AND A.OID_SUBG_VENT = B.ZSGV_OID_SUBG_VENT
     AND (vnOidPeriodo >= B.PERD_OID_PERI_INIC)
     AND (vnOidPeriodo <= B.PERD_OID_PERI_FINA or B.PERD_OID_PERI_FINA IS NULL)
   ORDER BY
       A.COD_SUBG_VENT, B.COD_REGI;

  CURSOR c3(vnIdPais NUMBER,vnOidPeriodo NUMBER) IS
   SELECT
     A.COD_SUBG_VENT,
     A.DES_SUBG_VENT,
     B.COD_REGI,
     B.COD_REGI || ' - ' || B.DES_REGI,
     C.COD_ZONA,
     C.DES_ZONA,
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
     NULL
   FROM
     ZON_SUB_GEREN_VENTA A,
     ZON_REGIO B,
     ZON_ZONA C
   WHERE A.PAIS_OID_PAIS = vnIdPais
     AND A.OID_SUBG_VENT = B.ZSGV_OID_SUBG_VENT
     AND B.OID_REGI = C.ZORG_OID_REGI
     AND (vnOidPeriodo >= C.PERD_OID_PERI_INIC)
     AND (vnOidPeriodo <= C.PERD_OID_PERI_FINA or C.PERD_OID_PERI_FINA IS NULL)
     AND C.COD_ZONA = DECODE(psCodZona, NULL, C.COD_ZONA, psCodZona)
   ORDER BY
       A.COD_SUBG_VENT, B.COD_REGI, C.COD_ZONA;

  CURSOR c4(vnIdPais NUMBER,vnOidPeriodo NUMBER) IS
   SELECT
     A.COD_SUBG_VENT,
     A.DES_SUBG_VENT,
     B.COD_REGI,
     B.COD_REGI || ' - ' || B.DES_REGI,
     C.COD_ZONA,
     C.DES_ZONA,
     D.OID_SECC,
     D.COD_SECC,
     D.COD_SECC,
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
     NULL
   FROM
     ZON_SUB_GEREN_VENTA A,
     ZON_REGIO B,
     ZON_ZONA  C,
     ZON_SECCI D
   WHERE A.PAIS_OID_PAIS = vnIdPais
     AND A.OID_SUBG_VENT = B.ZSGV_OID_SUBG_VENT
     AND B.OID_REGI = C.ZORG_OID_REGI
     AND C.OID_ZONA = D.ZZON_OID_ZONA
     AND (vnOidPeriodo >= D.PERD_OID_PERI_INIC)
     AND (vnOidPeriodo <= D.PERD_OID_PERI_FINA or D.PERD_OID_PERI_FINA IS NULL)
     AND C.COD_ZONA = DECODE(psCodZona, NULL, C.COD_ZONA, psCodZona)
   ORDER BY
       A.COD_SUBG_VENT, B.COD_REGI, C.COD_ZONA, D.COD_SECC;
   /* FIN JJ VEN-SiCC-2?012-0125 */
   lnOidCliente MAE_CLIEN.OID_CLIE%TYPE;

BEGIN
  lnIdPais   := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);

  --OBTENEMOS EL OID PERIODO PASADO COMO PARAMETRO y DEL PERIODO ANTERIOR
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodPeriodo);

  /*Obteniendo registros */
  IF psTipoUnidad = '1' THEN
    OPEN c1(lnIdPais,lnOidPeriodo);
    LOOP
         FETCH c1 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
         IF tablaRegistro.COUNT > 0 THEN
           FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                regRegistro := tablaRegistro(x);
                regRegistro.NOM_CLIE := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                        regRegistro.COD_CLIE,
                                        psCodPeriodo,
                                        lnIdPais,
                                        regRegistro.COD_SUBG_VENT,
                                        regRegistro.COD_REGI,
                                        regRegistro.COD_ZONA,
                                        regRegistro.COD_SECC);

                --OBTENEMOS EL TELEFONO DE LA LIDER
                BEGIN
                  lsTelefono := NULL;

                  SELECT COM.VAL_TEXT_COMU
                    INTO lsTelefono
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TF'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN OTHERS THEN NULL;
                END;
                regRegistro.TEL_CLIE := trim(lsTelefono);

                BEGIN
                  lnOidCliente := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CLIENTE(regRegistro.COD_CLIE);
                EXCEPTION
                  WHEN OTHERS THEN
                  lnOidCliente := NULL;
                END;

                BEGIN
                  SELECT TO_CHAR(FEC_NACI,'dd/MM/yyyy')
                  INTO regRegistro.FEC_NACI
                  FROM MAE_CLIEN_DATOS_ADICI
                  WHERE CLIE_OID_CLIE = lnOidCliente
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.FEC_NACI := NULL;
                END;

                BEGIN
                  SELECT NUM_DOCU_IDEN
                  INTO regRegistro.NUM_DOCU_IDEN
                  FROM MAE_CLIEN_IDENT
                  WHERE CLIE_OID_CLIE = lnOidCliente
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.NUM_DOCU_IDEN := NULL;
                END;

                BEGIN
                  SELECT (c.DES_ABRV_TIPO_VIA || ' ' || a.VAL_NOMB_VIA || ' ' || a.NUM_PPAL || ' ' || a.VAL_OBSE) AS DIRECCION
                  INTO regRegistro.DIERC
                  FROM MAE_CLIEN_DIREC a,
                       MAE_TIPO_DIREC b,
                       SEG_TIPO_VIA c,
                       MAE_CLIEN d,
                       ZON_TERRI t
                  WHERE d.OID_CLIE = lnOidCliente
                  AND   d.OID_CLIE = a.CLIE_OID_CLIE
                  AND   a.IND_ELIM = 0
                  AND   b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                  AND   c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                  AND   a.IND_DIRE_PPAL  = 1
                  AND   a.TERR_OID_TERR = t.OID_TERR (+)
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.DIERC := NULL;
                END;

                /* INI JJ VEN-SiCC-2?012-0125 */
                BEGIN
                 SELECT COM.VAL_TEXT_COMU
                    INTO regRegistro.TEL_MOVI
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TM'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.TEL_MOVI := NULL;
                END;

                BEGIN
                 SELECT COM.VAL_TEXT_COMU
                    INTO regRegistro.TEL_TRAB
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TT'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.TEL_TRAB := NULL;
                END;

                /* AGREGAR CAMPO CORREO ELECTRONICO */

                BEGIN
                     SELECT CC.VAL_TEXT_COMU
                    INTO regRegistro.VAL_EMAI
                        FROM MAE_CLIEN_COMUN CC
                        WHERE CC.CLIE_OID_CLIE = (SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE = regRegistro.COD_CLIE)
                        AND CC.TICM_OID_TIPO_COMU = 3 AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.VAL_EMAI := NULL;
                END;
                IF psTipoConsulta = 'T' THEN
                   PIPE ROW(regRegistro);
                ELSIF psTipoConsulta = 'C' THEN
                   IF regRegistro.NOM_CLIE IS NOT NULL THEN
                      PIPE ROW(regRegistro);
                   END IF;
                ELSIF psTipoConsulta = 'S' THEN
                   IF regRegistro.NOM_CLIE IS NULL THEN
                PIPE ROW(regRegistro);
                   END IF;
                END IF;
           END LOOP;
         END IF;
         EXIT WHEN c1%NOTFOUND ;
    END LOOP ;
    CLOSE c1;
    RETURN;
  ELSIF pstipoUnidad = '2' THEN
    OPEN c2(lnIdPais,lnOidPeriodo);
    LOOP
         FETCH c2 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
         IF tablaRegistro.COUNT > 0 THEN
           FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                regRegistro := tablaRegistro(x);

                regRegistro.NOM_CLIE := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                        regRegistro.COD_CLIE,
                                        psCodPeriodo,
                                        lnIdPais,
                                        regRegistro.COD_SUBG_VENT,
                                        regRegistro.COD_REGI,
                                        NULL,
                                        NULL);

                BEGIN
                  lnOidCliente := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CLIENTE(regRegistro.COD_CLIE);
                EXCEPTION
                  WHEN OTHERS THEN
                  lnOidCliente := NULL;
                END;

                BEGIN
                  SELECT TO_CHAR(FEC_NACI,'dd/MM/yyyy')
                  INTO regRegistro.FEC_NACI
                  FROM MAE_CLIEN_DATOS_ADICI
                  WHERE CLIE_OID_CLIE = lnOidCliente
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.FEC_NACI := NULL;
                END;

                BEGIN
                  SELECT NUM_DOCU_IDEN
                  INTO regRegistro.NUM_DOCU_IDEN
                  FROM MAE_CLIEN_IDENT
                  WHERE CLIE_OID_CLIE = lnOidCliente
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.NUM_DOCU_IDEN := NULL;
                END;

                BEGIN
                  SELECT (c.DES_ABRV_TIPO_VIA || ' ' || a.VAL_NOMB_VIA || ' ' || a.NUM_PPAL || ' ' || a.VAL_OBSE) AS DIRECCION
                  INTO regRegistro.DIERC
                  FROM MAE_CLIEN_DIREC a,
                       MAE_TIPO_DIREC b,
                       SEG_TIPO_VIA c,
                       MAE_CLIEN d,
                       ZON_TERRI t
                  WHERE d.OID_CLIE = lnOidCliente
                  AND   d.OID_CLIE = a.CLIE_OID_CLIE
                  AND   a.IND_ELIM = 0
                  AND   b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                  AND   c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                  AND   a.IND_DIRE_PPAL  = 1
                  AND   a.TERR_OID_TERR = t.OID_TERR (+)
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.DIERC := NULL;
                END;

                /* INI JJ VEN-SiCC-2?012-0125 */
                BEGIN
                  lsTelefono := NULL;

                  SELECT COM.VAL_TEXT_COMU
                    INTO lsTelefono
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TF'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN OTHERS THEN NULL;
                END;
                regRegistro.TEL_CLIE := trim(lsTelefono);

                BEGIN
                 SELECT COM.VAL_TEXT_COMU
                    INTO regRegistro.TEL_MOVI
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TM'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.TEL_MOVI := NULL;
                END;

                BEGIN
                 SELECT COM.VAL_TEXT_COMU
                    INTO regRegistro.TEL_TRAB
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TT'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.TEL_TRAB := NULL;
                END;

                /* FIN JJ VEN-SiCC-2?012-0125 */
                /* AGREGAR CAMPO CORREO ELECTRONICO */

                BEGIN
                SELECT CC.VAL_TEXT_COMU
                    INTO regRegistro.VAL_EMAI
                        FROM MAE_CLIEN_COMUN CC
                        WHERE CC.CLIE_OID_CLIE = (SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE =regRegistro.COD_CLIE)
                        AND CC.TICM_OID_TIPO_COMU=3 AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.VAL_EMAI := NULL;
                END;

                IF psTipoConsulta = 'T' THEN
                   PIPE ROW(regRegistro);
                ELSIF psTipoConsulta = 'C' THEN
                   IF regRegistro.NOM_CLIE IS NOT NULL THEN
                      PIPE ROW(regRegistro);
                   END IF;
                ELSIF psTipoConsulta = 'S' THEN
                   IF regRegistro.NOM_CLIE IS NULL THEN
                PIPE ROW(regRegistro);
                   END IF;
                END IF;
           END LOOP;
         END IF;
         EXIT WHEN c2%NOTFOUND ;
    END LOOP ;
    CLOSE c2;
    RETURN;
  ELSIF pstipoUnidad = '3' THEN
    OPEN c3(lnIdPais,lnOidPeriodo);
    LOOP
         FETCH c3 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
         IF tablaRegistro.COUNT > 0 THEN
           FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                regRegistro := tablaRegistro(x);

                regRegistro.NOM_CLIE := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                        regRegistro.COD_CLIE,
                                        psCodPeriodo,
                                        lnIdPais,
                                        regRegistro.COD_SUBG_VENT,
                                        regRegistro.COD_REGI,
                                        regRegistro.COD_ZONA,
                                        NULL);

                --OBTENEMOS EL TELEFONO DEL GERENTE DE ZONA
                BEGIN
                  lsTelefono := NULL;

                  SELECT COM.VAL_TEXT_COMU
                    INTO lsTelefono
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TF'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN OTHERS THEN NULL;
                END;
                regRegistro.TEL_CLIE := trim(lsTelefono);

                BEGIN
                  lnOidCliente := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CLIENTE(regRegistro.COD_CLIE);
                EXCEPTION
                  WHEN OTHERS THEN
                  lnOidCliente := NULL;
                END;

                BEGIN
                  SELECT TO_CHAR(FEC_NACI,'dd/MM/yyyy')
                  INTO regRegistro.FEC_NACI
                  FROM MAE_CLIEN_DATOS_ADICI
                  WHERE CLIE_OID_CLIE = lnOidCliente
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.FEC_NACI := NULL;
                END;

                BEGIN
                  SELECT NUM_DOCU_IDEN
                  INTO regRegistro.NUM_DOCU_IDEN
                  FROM MAE_CLIEN_IDENT
                  WHERE CLIE_OID_CLIE = lnOidCliente
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.NUM_DOCU_IDEN := NULL;
                END;

                BEGIN
                  SELECT (c.DES_ABRV_TIPO_VIA || ' ' || a.VAL_NOMB_VIA || ' ' || a.NUM_PPAL || ' ' || a.VAL_OBSE) AS DIRECCION
                  INTO regRegistro.DIERC
                  FROM MAE_CLIEN_DIREC a,
                       MAE_TIPO_DIREC b,
                       SEG_TIPO_VIA c,
                       MAE_CLIEN d,
                       ZON_TERRI t
                  WHERE d.OID_CLIE = lnOidCliente
                  AND   d.OID_CLIE = a.CLIE_OID_CLIE
                  AND   a.IND_ELIM = 0
                  AND   b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                  AND   c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                  AND   a.IND_DIRE_PPAL  = 1
                  AND   a.TERR_OID_TERR = t.OID_TERR (+)
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.DIERC := NULL;
                END;

                /* INI JJ VEN-SiCC-2?012-0125 */
                BEGIN
                 SELECT COM.VAL_TEXT_COMU
                    INTO regRegistro.TEL_MOVI
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TM'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.TEL_MOVI := NULL;
                END;

                BEGIN
                 SELECT COM.VAL_TEXT_COMU
                    INTO regRegistro.TEL_TRAB
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TT'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.TEL_TRAB := NULL;
                END;
                /* FIN JJ VEN-SiCC-2?012-0125 */
                /* AGREGAR CAMPO CORREO ELECTRONICO */

                BEGIN
                 SELECT CC.VAL_TEXT_COMU
                    INTO regRegistro.VAL_EMAI
                        FROM MAE_CLIEN_COMUN CC
                        WHERE CC.CLIE_OID_CLIE = (SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE =regRegistro.COD_CLIE)
                        AND CC.TICM_OID_TIPO_COMU=3 AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.VAL_EMAI := NULL;
                END;

                IF psTipoConsulta = 'T' THEN
                   PIPE ROW(regRegistro);
                ELSIF psTipoConsulta = 'C' THEN
                   IF regRegistro.NOM_CLIE IS NOT NULL THEN
                PIPE ROW(regRegistro);
                   END IF;
                ELSIF psTipoConsulta = 'S' THEN
                   IF regRegistro.NOM_CLIE IS NULL THEN
                      PIPE ROW(regRegistro);
                   END IF;
                END IF;
           END LOOP;
         END IF;
         EXIT WHEN c3%NOTFOUND ;
    END LOOP ;
    CLOSE c3;
    RETURN;
  ELSIF pstipoUnidad = '4' THEN
    OPEN c4(lnIdPais,lnOidPeriodo);
    LOOP
         FETCH c4 BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
         IF tablaRegistro.COUNT > 0 THEN
           FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                regRegistro := tablaRegistro(x);

                regRegistro.NOM_CLIE := COM_PKG_REPOR.COM_FN_DEVUE_RESPO_UNIAD_HIST2(
                                        regRegistro.COD_CLIE,
                                        psCodPeriodo,
                                        lnIdPais,
                                        regRegistro.COD_SUBG_VENT,
                                        regRegistro.COD_REGI,
                                        regRegistro.COD_ZONA,
                                        regRegistro.COD_SECC);

                --OBTENEMOS EL TELEFONO DE LA LIDER
                BEGIN
                  lsTelefono := NULL;

                  SELECT COM.VAL_TEXT_COMU
                    INTO lsTelefono
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TF'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN OTHERS THEN NULL;
                END;
                regRegistro.TEL_CLIE := trim(lsTelefono);
                
                --OBTENEMOS LOS NUMERO ACTIVAS INICIALES Y FINALES DE LA SECCION
                SELECT NVL(SUM(FUE.NUM_ACTI_INIC),0) ACTI_INIC,
                       NVL(SUM(FUE.NUM_ACTI_FINA),0) ACTI_FINA,
                       COUNT(1)
                INTO   lnNumActiInic,
                       lnNumActiFina,
                       lnNumTotal
                FROM   INT_FUENT_VENTAS_REAL FUE, ZON_TERRI TER, ZON_TERRI_ADMIN TAD, ZON_SECCI SEC, ZON_ZONA ZON
                WHERE  FUE.TERR_OID_TERR = TER.OID_TERR
                  AND  TAD.TERR_OID_TERR = TER.OID_TERR
                  AND  (lnOidPeriodo >= tad.PERD_OID_PERI_INIC or tad.PERD_OID_PERI_INIC is null)
                  AND  (lnOidPeriodo <= tad.PERD_OID_PERI_FINA or tad.PERD_OID_PERI_FINA is null)
                  AND  SEC.OID_SECC = TAD.ZSCC_OID_SECC
                  AND  SEC.OID_SECC = regRegistro.OID_SECC
                  AND  SEC.ZZON_OID_ZONA = ZON.OID_ZONA
                  AND  FUE.ZZON_OID_ZONA = ZON.OID_ZONA
                  AND  FUE.ZORG_OID_REGI = ZON.ZORG_OID_REGI
                  AND  FUE.PERD_OID_PERI = lnOidPeriodo
                  AND  FUE.PAIS_OID_PAIS = lnIdPais;

                --SI PARA EL PERIODO ACTUAL NO TRAE DATA, SE CALCULA PARA EL PERIODO ANTERIOR
                IF(lnNumTotal = 0) THEN
                  SELECT NVL(SUM(FUE.NUM_ACTI_FINA),0) ACTI_INIC,
                         0
                  INTO   lnNumActiInic,
                         lnNumActiFina
                  FROM   INT_FUENT_VENTAS_REAL FUE, ZON_TERRI TER, ZON_TERRI_ADMIN TAD, ZON_SECCI SEC, ZON_ZONA ZON
                  WHERE  FUE.TERR_OID_TERR = TER.OID_TERR
                    AND  TAD.TERR_OID_TERR = TER.OID_TERR
                    AND  (lnOidPeriodoAnt >= tad.PERD_OID_PERI_INIC or tad.PERD_OID_PERI_INIC is null)
                    AND  (lnOidPeriodoAnt <= tad.PERD_OID_PERI_FINA or tad.PERD_OID_PERI_FINA is null)
                    AND  SEC.OID_SECC = TAD.ZSCC_OID_SECC
                    AND  SEC.OID_SECC = regRegistro.OID_SECC
                    AND  SEC.ZZON_OID_ZONA = ZON.OID_ZONA
                    AND  FUE.ZZON_OID_ZONA = ZON.OID_ZONA
                    AND  FUE.ZORG_OID_REGI = ZON.ZORG_OID_REGI
                    AND  FUE.PERD_OID_PERI = lnOidPeriodoAnt
                    AND  FUE.PAIS_OID_PAIS = lnIdPais;
                END IF;

                regRegistro.NUM_ACTI_INIC := lnNumActiInic;
                regRegistro.NUM_ACTI_FINA := lnNumActiFina;
                regRegistro.PRO_ACTI_FINA := LID_PKG_PROCE_LIDER.LID_FN_OBTIE_PROME_SECCI(GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_ZONA(psCodPais,psCodMarca,psCodCanal,regRegistro.COD_REGI,regRegistro.COD_ZONA),regRegistro.OID_SECC,lnOidPeriodo,lnOidPeriodoAnt,lnOidPeriodoAnt2);

                BEGIN
                  lnOidCliente := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CLIENTE(regRegistro.COD_CLIE);
                EXCEPTION
                  WHEN OTHERS THEN
                  lnOidCliente := NULL;
                END;
                
                   --OBTENEMOS LA FECHA DE NACIMIENTO
                BEGIN
                  SELECT TO_CHAR(FEC_NACI,'dd/MM/yyyy')
                  INTO regRegistro.FEC_NACI
                  FROM MAE_CLIEN_DATOS_ADICI
                  WHERE CLIE_OID_CLIE = lnOidCliente
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.FEC_NACI := NULL;
                END;
                
                 --OBTENEMOS LA UNIDAD ADMINISTRATIVA DE zon_histo_geren
                 BEGIN
                  SELECT UA
                  INTO regRegistro.CAMPO_UA FROM zon_histo_geren hg,
                  cra_perio cp,
                  seg_perio_corpo sp,
                  mae_clien mc,
                  MAE_CLIEN_DATOS_ADICI ADI,     
                  MAE_CLIEN_IDENT MAECI
                  WHERE 
                  hg.GERE= regRegistro.COD_CLIE                  
                  and hg.PERD_OID_PERI_HAST is null
                  and hg.PERD_OID_PERI_DESD=cp.OID_PERI
                  and cp.PERI_OID_PERI=sp.OID_PERI 
                  and hg.gere=mc.cod_clie 
                  AND MC.OID_CLIE=ADI.CLIE_OID_CLIE
                 AND MC.OID_CLIE=MAECI.CLIE_OID_CLIE
                  and MAECI.VAL_IDEN_DOCU_PRIN = 1
                  and hg.cod_secc is not null                                      
                  --Sole SE           
                  and hg.cod_zona = psCodZona;                               
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN                     
                       regRegistro.CAMPO_UA := NULL;                   
                  END;
                
                --OBTENEMOS LA FECHA DESDE DE zon_histo_geren
                
                BEGIN
                  SELECT FEC_DESD
                  INTO regRegistro.FEC_DESD FROM zon_histo_geren hg,
                  cra_perio cp,
                  seg_perio_corpo sp,
                  mae_clien mc,
                  MAE_CLIEN_DATOS_ADICI ADI,     
                  MAE_CLIEN_IDENT MAECI
                  WHERE 
                  GERE= regRegistro.COD_CLIE
                  and hg.PERD_OID_PERI_HAST is null
                  and hg.PERD_OID_PERI_DESD=cp.OID_PERI
                  and cp.PERI_OID_PERI=sp.OID_PERI 
                  and hg.gere=mc.cod_clie 
                  AND MC.OID_CLIE=ADI.CLIE_OID_CLIE
                 AND MC.OID_CLIE=MAECI.CLIE_OID_CLIE
                  and MAECI.VAL_IDEN_DOCU_PRIN = 1
                  and hg.cod_secc is not null                                      
                  --Sole SE           
                  and hg.cod_zona = psCodZona;                 
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN 
                         regRegistro.FEC_DESD := NULL;
                  END;
                                
                 --OBTENEMOS LA CAMPAMA DESDE DE zon_histo_geren
                 --lbcodPeriodo := fin_pkg_gener.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodo);                
                 BEGIN
                   SELECT cp.VAL_NOMB_PERI 
                   INTO regRegistro.PERD_OID_PERI_DESD FROM zon_histo_geren hg,
                  cra_perio cp,
                  seg_perio_corpo sp,
                  mae_clien mc,
                  MAE_CLIEN_DATOS_ADICI ADI,     
                  MAE_CLIEN_IDENT MAECI 
                  WHERE 
                   GERE= regRegistro.COD_CLIE
                  AND hg.PERD_OID_PERI_HAST is null
                  AND hg.PERD_OID_PERI_DESD=cp.OID_PERI
                  AND cp.PERI_OID_PERI=sp.OID_PERI 
                  AND hg.gere=mc.cod_clie 
                  AND MC.OID_CLIE=ADI.CLIE_OID_CLIE
                  AND MC.OID_CLIE=MAECI.CLIE_OID_CLIE
                  AND MAECI.VAL_IDEN_DOCU_PRIN = 1
                  AND hg.cod_secc is not null                                      
                  --Sole SE           
                  and hg.cod_zona = psCodZona;             
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.PERD_OID_PERI_DESD := NULL;
                END;                
                
                BEGIN
                  SELECT NUM_DOCU_IDEN
                  INTO regRegistro.NUM_DOCU_IDEN
                  FROM MAE_CLIEN_IDENT
                  WHERE CLIE_OID_CLIE = lnOidCliente
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.NUM_DOCU_IDEN := NULL;
                END;

                BEGIN
                  SELECT (c.DES_ABRV_TIPO_VIA || ' ' || a.VAL_NOMB_VIA || ' ' || a.NUM_PPAL || ' ' || a.VAL_OBSE) AS DIRECCION
                  INTO regRegistro.DIERC
                  FROM MAE_CLIEN_DIREC a,
                       MAE_TIPO_DIREC b,
                       SEG_TIPO_VIA c,
                       MAE_CLIEN d,
                       ZON_TERRI t
                  WHERE d.OID_CLIE = lnOidCliente
                  AND   d.OID_CLIE = a.CLIE_OID_CLIE
                  AND   a.IND_ELIM = 0
                  AND   b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                  AND   c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                  AND   a.IND_DIRE_PPAL  = 1
                  AND   a.TERR_OID_TERR = t.OID_TERR (+)
                  AND   ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                   regRegistro.DIERC := NULL;
                END;

                /* INI JJ VEN-SiCC-2?012-0125 */
                BEGIN
                 SELECT COM.VAL_TEXT_COMU
                    INTO regRegistro.TEL_MOVI
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TM'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.TEL_MOVI := NULL;
                END;

                BEGIN
                 SELECT COM.VAL_TEXT_COMU
                    INTO regRegistro.TEL_TRAB
                    FROM MAE_CLIEN cli, MAE_CLIEN_COMUN COM, MAE_TIPO_COMUN TCC
                   WHERE CLI.COD_CLIE = regRegistro.COD_CLIE
                     AND CLI.PAIS_OID_PAIS = lnIdPais
                     AND COM.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND COM.TICM_OID_TIPO_COMU = TCC.OID_TIPO_COMU
                     AND TCC.COD_TIPO_COMU = 'TT'
                     AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.TEL_TRAB := NULL;
                END;
                /* FIN JJ VEN-SiCC-2?012-0125 */
                /* AGREGAR CAMPO CORREO ELECTRONICO */

                BEGIN
                SELECT CC.VAL_TEXT_COMU
                    INTO regRegistro.VAL_EMAI
                        FROM MAE_CLIEN_COMUN CC
                        WHERE CC.CLIE_OID_CLIE = (SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE =regRegistro.COD_CLIE)
                        AND CC.TICM_OID_TIPO_COMU=3 AND ROWNUM = 1;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                       regRegistro.VAL_EMAI := NULL;
                END;

                IF psTipoConsulta = 'T' THEN
                   PIPE ROW(regRegistro);
                ELSIF psTipoConsulta = 'C' THEN
                   IF regRegistro.NOM_CLIE IS NOT NULL THEN
                      PIPE ROW(regRegistro);
                   END IF;
                ELSIF psTipoConsulta = 'S' THEN
                   IF regRegistro.NOM_CLIE IS NULL THEN
                PIPE ROW(regRegistro);
                   END IF;
                END IF;
           END LOOP;
         END IF;
         EXIT WHEN c4%NOTFOUND ;
    END LOOP ;
    CLOSE c4;
    RETURN;
  END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_OBTIE_RESPO_UA : '||ls_sqlerrm);
END COM_FN_OBTIE_RESPO_UA;

FUNCTION COM_FN_OBTIE_REPOR_LEADERLIST(
    psCodPais          VARCHAR2,
    psCodPeriodo       VARCHAR2
)
RETURN  tTablaRegistradosLeaderList PIPELINED
IS
 tablaRegistro      tTablaRegistradosLeaderList;
 regRegistro        tRegTablaRegistradosLeaderList;
 lnIdPais           NUMBER;
 InIPeriodo         NUMBER;

  CURSOR cursorRegistro(lnIdPais NUMBER, InIPeriodo NUMBER)
   IS
select DISTINCT MPR.COD_SAP, MPR.CODI_ANTI, GPR.VAL_I18N AS DESCRIPCION, '01' AS Tipo
  from INC_CONCU_PARAM_GENER CPG,
       INC_VERSI_CONCU VCO,
       INC_PARAM_GENER_PREMI PGP,
       INC_PARAM_NIVEL_PREMI PNP,
       INC_PREMI_ARTIC PAR,
       INC_LOTE_PREMI_ARTIC LPA,
       INC_ARTIC_LOTE ALO,
       MAE_PRODU MPR,
       (SELECT *
          FROM GEN_I18N_SICC_PAIS
         WHERE ATTR_ENTI = 'MAE_PRODU') GPR,
       INC_REQUI_PREMI IRP
 where PGP.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
   and IRP.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
   AND VCO.VICO_OID_VIGE_CONC in (1, 6)
   AND VCO.COPA_OID_PARA_GRAL_ORIG = CPG.OID_PARA_GRAL
   AND PNP.PAGP_OID_PARA_GENE_PREM = PGP.OID_PARA_GENE_PREM
   AND PAR.PANP_OID_PARA_NIVE_PREM = PNP.OID_PARA_NIVE_PREM
   AND LPA.PRAR_OID_PREM_ARTI = PAR.OID_PREM_ARTI
   AND ALO.LOPA_OID_LOTE_PREM_ARTI = LPA.OID_LOTE_PREM_ARTI
   AND MPR.OID_PROD = ALO.PROD_OID_PROD
   AND PGP.PERD_OID_PERI = InIPeriodo
   AND GPR.VAL_OID(+) = ALO.PROD_OID_PROD
   AND NOT EXISTS ( SELECT 1
        FROM LAR_ESTIM_YOBEL LE
        WHERE LE.COD_PERI = psCodPeriodo
              AND LE.COD_SAP = MPR.COD_SAP )

UNION

select DISTINCT mp.COD_SAP, MP.CODI_ANTI, G.VAL_I18N, '02' AS TIPO
  from mav_detal_mav MDM,
       mae_produ MP,
       GEN_I18N_SICC_PAIS G,
       GEN_I18N_SICC_comun G1,
       (select *
          from GEN_I18N_SICC_comun
         where ATTR_ENTI = 'MAE_TIPO_CLASI_CLIEN') G2,
       (select *
          from GEN_I18N_SICC_comun
         where ATTR_ENTI = 'MAE_CLASI') G3,
       (select *
          from GEN_I18N_SICC_comun
         where ATTR_ENTI = 'PRE_TIPO_OFERT') G4,
       mav_subcr_asign MSA,
       (select * from GEN_I18N_SICC_pais where ATTR_ENTI = 'MAE_PRODU') G5,
       (select * from GEN_I18N_SICC_pais where ATTR_ENTI = 'MAV_ESTAD_MAV') G6,
       (select *
          from GEN_I18N_SICC_comun
         where ATTR_ENTI = 'MAE_SUBTI_CLIEN') G7,
       mav_activ_estad mae,
       mav_subti_clien_detal MSCD,
       pre_catal PCAT,
       cra_perio PCR,
       cra_perio PCR1,
       mav_tipo_estad_proce MTEP,
       msg_mensa MMSG
 where g.VAL_OID = mdm.ACTI_OID_ACTI
   and g.ATTR_ENTI = 'MAV_ACTIV'
   and g1.VAL_OID = mdm.TICL_OID_TIPO_CLIE
   and g1.ATTR_ENTI = 'MAE_TIPO_CLIEN'
   and g2.VAL_OID(+) = mdm.TCCL_OID_TIPO_CLAS
   and g3.VAL_OID(+) = mdm.CLAS_OID_CLAS
   and g4.VAL_OID(+) = MDM.TOFE_OID_TIPO_OFER
   and g5.VAL_OID(+) = mdm.PROD_OID_PROD
   and g7.VAL_OID(+) = MSCD.SBTI_OID_SUBT_CLIE
   and MSA.OID_SUBC(+) = MDM.SCAS_OID_SUBC
   and mp.OID_PROD = mdm.PROD_OID_PROD
   and mdm.AEST_OID_ESTA_ACTI = mae.OID_ESTA_ACTI(+)
   and mae.EMAV_OID_ESTA_MAV = G6.val_oid(+)
   and (mdm.PERD_OID_PERI = InIPeriodo
        )
   and mdm.TEPR_OID_TIPO_ESTA_proc in (1, 2, 3, 4)
   and mdm.TEPR_OID_TIPO_ESTA_proc = MTEP.OID_TIPO_ESTA_proc
   and mdm.oid_deta_mav = MSCD.denv_oid_deta_mav(+)
   and mdm.ocat_oid_cata = PCAT.oid_cata(+)
   and mdm.perd_oid_peri_inic_mont = PCR.oid_peri(+)
   and mdm.perd_oid_peri_fina_mont = PCR1.oid_peri(+)
   and mdm.mens_oid_mens = MMSG.oid_mens(+)

   AND NOT EXISTS ( SELECT 1
        FROM LAR_ESTIM_YOBEL LE
        WHERE LE.COD_PERI = psCodPeriodo
              AND LE.COD_SAP = mp.COD_SAP )
UNION
SELECT distinct prod.cod_sap, prod.CODI_ANTI, G.VAL_I18N DESCRIPCION, '03' AS TIPO
    FROM cra_perio peri,
         pre_ofert ofe,
         pre_ofert_detal ofedet,
         pre_matri_factu mf,
         pre_matri_factu_cabec mfc,
         mae_produ prod,
          bel_forma_pago fp,
          pre_estra es,
          pre_catal cat,
		  seg_marca marca,
          pre_ciclo_vida cvida,
          pre_tipo_ofert tofe,
          seg_acces acc,
          pre_venta_exclu vtaex,
         (SELECT v.val_oid, v.val_i18n
            FROM  v_gen_i18n_sicc v
           WHERE v.attr_enti = 'MAE_PRODU' AND v.idio_oid_idio = 1) i18prod,
         (SELECT v.val_oid, v.val_i18n
            FROM  v_gen_i18n_sicc v
           WHERE v.attr_enti = 'PRE_ESTRA' AND v.idio_oid_idio = 1) i18est,
         (SELECT v.val_oid, v.val_i18n
            FROM  v_gen_i18n_sicc v
           WHERE v.attr_enti = 'MAE_TIPO_CLIEN' AND v.idio_oid_idio = 1) i18tclien,
         (SELECT v.val_oid, v.val_i18n
            FROM  v_gen_i18n_sicc v
           WHERE v.attr_enti = 'MAE_SUBTI_CLIEN' AND v.idio_oid_idio = 1) i18stclien,
         (SELECT v.val_oid, v.val_i18n
            FROM  v_gen_i18n_sicc v
           WHERE v.attr_enti = 'MAE_TIPO_CLASI_CLIEN' AND v.idio_oid_idio = 1) i18tclasi,
         (SELECT v.val_oid, v.val_i18n
            FROM  v_gen_i18n_sicc v
           WHERE v.attr_enti = 'MAE_CLASI' AND v.idio_oid_idio = 1) i18clasi,
         (SELECT v.val_oid, v.val_i18n
            FROM  v_gen_i18n_sicc v
           WHERE v.attr_enti = 'SEG_PAIS' AND v.idio_oid_idio = 1) i18pais,
         (SELECT v.val_oid, v.val_i18n
            FROM  v_gen_i18n_sicc v
           WHERE v.attr_enti = 'SEG_CANAL' AND v.idio_oid_idio = 1) i18canal,
          ( SELECT * FROM GEN_I18N_SICC_PAIS WHERE attr_enti = 'MAE_PRODU') G
   WHERE  peri.oid_peri = mfc.perd_oid_peri
     AND ofe.mfca_oid_cabe = mfc.oid_cabe
     AND mf.mfca_oid_cabe = mfc.oid_cabe
     AND ofe.oid_ofer = ofedet.ofer_oid_ofer
     AND ofedet.oid_deta_ofer = mf.ofde_oid_deta_ofer
     AND ofedet.prod_oid_prod = prod.oid_prod
     AND ofe.fopa_oid_form_pago = fp.oid_form_pago(+)
     AND ofedet.ocat_oid_catal = cat.oid_cata(+)
     AND ofe.coes_oid_estr = es.oid_estr(+)
     AND ofedet.civi_oid_ciclo_vida = cvida.oid_cicl_vida(+)
     AND ofedet.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
     AND ofe.acce_oid_acce = acc.oid_acce(+)
     AND i18est.val_oid(+) = ofe.coes_oid_estr
     AND i18tclien.val_oid(+) = vtaex.ticl_oid_tipo_clie
     AND i18stclien.val_oid(+) = vtaex.sbti_oid_subt_clie
     AND i18tclasi.val_oid(+) = vtaex.tccl_oid_tipo_clas
     AND i18clasi.val_oid(+) = vtaex.clas_oid_clas
     AND i18pais.val_oid(+) = peri.pais_oid_pais
     AND i18canal.val_oid(+) = peri.cana_oid_cana
     AND i18prod.val_oid(+) = ofedet.prod_oid_prod
     AND ofe.oid_ofer = vtaex.ofer_oid_ofer(+)
     AND peri.marc_oid_marc = marca.oid_marc(+)
     AND G.VAL_OID = prod.oid_prod
     AND peri.pais_oid_pais = lnIdPais
     AND peri.oid_peri = InIPeriodo
        AND NOT EXISTS ( SELECT 1
        FROM LAR_ESTIM_YOBEL LE
        WHERE LE.COD_PERI = psCodPeriodo
              AND LE.COD_SAP = prod.cod_sap )

AND NOT EXISTS
(
SELECT 1
FROM (
select DISTINCT mp.COD_SAP, MP.CODI_ANTI, G.VAL_I18N, '02' AS TIPO
  from mav_detal_mav MDM,
       mae_produ MP,
       GEN_I18N_SICC_PAIS G,
       GEN_I18N_SICC_comun G1,
       (select *
          from GEN_I18N_SICC_comun
         where ATTR_ENTI = 'MAE_TIPO_CLASI_CLIEN') G2,
       (select *
          from GEN_I18N_SICC_comun
         where ATTR_ENTI = 'MAE_CLASI') G3,
       (select *
          from GEN_I18N_SICC_comun
         where ATTR_ENTI = 'PRE_TIPO_OFERT') G4,
       mav_subcr_asign MSA,
       (select * from GEN_I18N_SICC_pais where ATTR_ENTI = 'MAE_PRODU') G5,
       (select * from GEN_I18N_SICC_pais where ATTR_ENTI = 'MAV_ESTAD_MAV') G6,
       (select *
          from GEN_I18N_SICC_comun
         where ATTR_ENTI = 'MAE_SUBTI_CLIEN') G7,
       mav_activ_estad mae,
       mav_subti_clien_detal MSCD,
       pre_catal PCAT,
       cra_perio PCR,
       cra_perio PCR1,
       mav_tipo_estad_proce MTEP,
       msg_mensa MMSG
 where g.VAL_OID = mdm.ACTI_OID_ACTI
   and g.ATTR_ENTI = 'MAV_ACTIV'
   and g1.VAL_OID = mdm.TICL_OID_TIPO_CLIE
   and g1.ATTR_ENTI = 'MAE_TIPO_CLIEN'
   and g2.VAL_OID(+) = mdm.TCCL_OID_TIPO_CLAS
   and g3.VAL_OID(+) = mdm.CLAS_OID_CLAS
   and g4.VAL_OID(+) = MDM.TOFE_OID_TIPO_OFER
   and g5.VAL_OID(+) = mdm.PROD_OID_PROD
   and g7.VAL_OID(+) = MSCD.SBTI_OID_SUBT_CLIE
   and MSA.OID_SUBC(+) = MDM.SCAS_OID_SUBC
   and mp.OID_PROD = mdm.PROD_OID_PROD
   and mdm.AEST_OID_ESTA_ACTI = mae.OID_ESTA_ACTI(+)
   and mae.EMAV_OID_ESTA_MAV = G6.val_oid(+)
   and (mdm.PERD_OID_PERI = InIPeriodo
       )
   and mdm.TEPR_OID_TIPO_ESTA_proc in (1, 2, 3, 4)
   and mdm.TEPR_OID_TIPO_ESTA_proc = MTEP.OID_TIPO_ESTA_proc
   and mdm.oid_deta_mav = MSCD.denv_oid_deta_mav(+)
   and mdm.ocat_oid_cata = PCAT.oid_cata(+)
   and mdm.perd_oid_peri_inic_mont = PCR.oid_peri(+)
   and mdm.perd_oid_peri_fina_mont = PCR1.oid_peri(+)
   and mdm.mens_oid_mens = MMSG.oid_mens(+)
   AND NOT EXISTS ( SELECT 1
        FROM LAR_ESTIM_YOBEL LE
        WHERE LE.COD_PERI = psCodPeriodo
              AND LE.COD_SAP = mp.COD_SAP )
 ) TEMP
 WHERE TEMP.COD_SAP = prod.cod_sap
       AND TEMP.CODI_ANTI = prod.CODI_ANTI
)
;
 BEGIN
      lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
      InIPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodPeriodo,true);

      OPEN cursorRegistro(lnIdPais,InIPeriodo);
      LOOP
           FETCH cursorRegistro BULK COLLECT INTO tablaRegistro LIMIT W_FILAS;
           IF tablaRegistro.COUNT > 0 THEN
             FOR x IN tablaRegistro.FIRST .. tablaRegistro.LAST LOOP
                  regRegistro := tablaRegistro(x);
                     PIPE ROW(regRegistro);
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR COM_FN_OBTIE_REPOR_LEADERLIST : '||ls_sqlerrm);

END COM_FN_OBTIE_REPOR_LEADERLIST;

END COM_PKG_REPOR_RECUP;
/
