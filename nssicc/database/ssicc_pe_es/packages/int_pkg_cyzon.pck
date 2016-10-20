CREATE OR REPLACE PACKAGE "INT_PKG_CYZON" AS

W_FILAS      NUMBER:=1000;

COD_CANA_DEFE VARCHAR2(100) := 'VD';
COD_MARC_DEFE VARCHAR2(100) := 'T';
COD_TIPO_SOLI_OC VARCHAR2(100) := 'SOC';

COD_PROG_CYSE VARCHAR2(100) := '001';
COD_PROG_PREM VARCHAR2(100) := '002';

/* Declaracion de procedures */

/***************************************************************************
Descripcion       : Procedimiento que realiza el proceso de Carga de los productos
                    despachados a una consultora, que pertenecen a un programa
                    de Cyzone especifico.
Fecha Creacion    : 02/01/2009
Autor             : Carlos Hurtado
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodigoProgramaPremio : Codigo de Programa
            psCodigoPeriodo   : Codigo de Periodo
            psFechaFacturacion : Fecha de Facturacion (DD/MM/YYYY)
            psCodigoUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE INT_PR_CYZ_CARGA_PRODU_DESPA(psCodigoPais       VARCHAR2,
                                       psCodigoPrograma   VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2,
                                       psCodigoUsuario    VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento que realiza el proceso de Carga de los premios
                    despachados a una consultora, que pertenecen a un programa
                    de Cyzone especifico, los cuales han sido solicitados desde
                    la web
Fecha Creacion    : 19/01/2009
Autor             : Carlos Hurtado
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodigoProgramaPremio : Codigo de Programa
            psCodigoPeriodo   : Codigo de Periodo
            psFechaFacturacion : Fecha de Facturacion (DD/MM/YYYY)
            psCodigoUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE INT_PR_CYZ_CARGA_PREMI_DESPA(psCodigoPais             VARCHAR2,
                                       psCodigoProgramaPremio   VARCHAR2,
                                       psCodigoPeriodo          VARCHAR2,
                                       psFechaFacturacion       VARCHAR2,
                                       psCodigoUsuario          VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento que genera el archivo de interfaz a enviar a
                    la web de Cyzone con la informacion de productos y premios
                    despachados.
Fecha Creacion    : 02/01/2009
Autor             : Carlos Hurtado
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodigoSistema : Codigo de Sistema
            psCodigoInterfaz   : Codigo de Interfaz
            psNombreArchivo : Nombre de Archivo de Interfaz
            psCodigoPrograma : Codigo de Programa
            psCodigoPeriodo   : Codigo de Periodo
            psFechaFacturacion : Fecha de Facturacion (DD/MM/YYYY)
            psCodigoProgramaPremio : Codigo de Programa de Premio
***************************************************************************/
PROCEDURE INT_PR_CYZ_ENVIA_PRODU_DESPA(psCodigoPais           VARCHAR2,
                                       psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCodigoPrograma       VARCHAR2,
                                       psCodigoPeriodo        VARCHAR2,
                                       psFechaFacturacion     VARCHAR2,
                                       psCodigoProgramaPremio VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento que realiza el proceso de Carga de los productos
                    despachados a una consultora, que pertenecen a alguno de los
                    programas del Welcome Pack, para lo cual hace uso de otro
                    procedimiento que hace la carga de un programa especifico.
Fecha Creacion    : 13/02/2009
Autor             : Carlos Hurtado
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodigoPeriodo   : Codigo de Periodo
            psFechaFacturacion : Fecha de Facturacion (DD/MM/YYYY)
            psCodigoUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE INT_PR_CYZ_CARGA_PRODU_BIENV(psCodigoPais       VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2,
                                       psCodigoUsuario    VARCHAR2);

END;
/

CREATE OR REPLACE PACKAGE BODY "INT_PKG_CYZON" AS

ln_sqlcode     NUMBER(10);
ls_sqlerrm     VARCHAR2(1000);

/***************************************************************************
Descripcion       : Procedimiento que realiza el proceso de Carga de los productos
                    despachados / solicitados a una consultora, que pertenecen a
                    un programa de Cyzone especifico.
Fecha Creacion    : 02/01/2009
Autor             : Carlos Hurtado
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodigoProgramaPremio : Codigo de Programa
            psCodigoPeriodo   : Codigo de Periodo
            psFechaFacturacion : Fecha de Facturacion (DD/MM/YYYY)
            psCodigoUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE INT_PR_CYZ_CARGA_PRODU_DESPA(psCodigoPais       VARCHAR2,
                                       psCodigoPrograma   VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2,
                                       psCodigoUsuario    VARCHAR2) IS

lnOidPais           NUMBER;
lnOidMarca          NUMBER;
lnOidCanal          NUMBER;
lnOidPeriodo        NUMBER;
lnOidTipoSoliPais   NUMBER;
lnOidDetaOfer       NUMBER;

CURSOR cProductos(oidPais NUMBER,
                  oidTipoSoliPais NUMBER,
                  oidPeriodo NUMBER) IS
SELECT PSC.OID_SOLI_CABE,
       PSC.CLIE_OID_CLIE,
       PSC.FEC_FACT,
       PSP.OID_SOLI_POSI,
       PSP.NUM_UNID_DEMA,
       PSP.NUM_UNID_ATEN,
       PSP.PROD_OID_PROD,
       PSP.VAL_CODI_VENT,
       PTO.COD_TIPO_OFER,
       MP.COD_SAP,
       NULL COD_CLIE,
       PSP.SOCA_OID_SOLI_CABE,
       NULL VAL_NUME_SOLI
FROM PED_SOLIC_CABEC PSC,
     PED_SOLIC_POSIC PSP,
     PRE_OFERT_DETAL POD,
     PRE_TIPO_OFERT PTO,
     MAE_PRODU MP
WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
  AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
  AND POD.TOFE_OID_TIPO_OFER = PTO.OID_TIPO_OFER
  AND PSP.PROD_OID_PROD = MP.OID_PROD
  AND PSC.PAIS_OID_PAIS = oidPais
  AND PSC.TSPA_OID_TIPO_SOLI_PAIS = oidTipoSoliPais
  AND PSC.PERD_OID_PERI = oidPeriodo
  AND PSC.FEC_FACT = TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')
  -- AND PSP.NUM_UNID_ATEN > 0 -- Se comenta para guardar registro de las solicitudes y no solo los despachos
  AND PSP.ESPO_OID_ESTA_POSI = 4 --  Estado Correcto
  AND EXISTS (
      SELECT NULL
      FROM CYZ_PRODU_PROGR_DUPLA PPD
      WHERE PPD.PAIS_COD_PAIS = psCodigoPais
        AND PPD.PRDU_COD_PROG = psCodigoPrograma
        AND PPD.COD_PERI = psCodigoPeriodo
        AND PPD.COD_PROD = MP.COD_SAP
        AND PPD.VAL_CODI_VENT = PSP.VAL_CODI_VENT
  )
  AND NOT EXISTS (
      SELECT NULL
      FROM CYZ_DESPA_PRODU X
      WHERE X.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        AND X.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
  );

CURSOR cProductosRecuperados(oidPais NUMBER,
                             oidTipoSoliPais NUMBER,
                             oidPeriodo NUMBER,
                             oidDetaOfer NUMBER) IS
SELECT PSC.OID_SOLI_CABE,
       PSC.CLIE_OID_CLIE,
       PSC.FEC_FACT,
       PSP.OID_SOLI_POSI,
       PSP.NUM_UNID_DEMA,
       PSP.NUM_UNID_ATEN,
       PSP.PROD_OID_PROD,
       PSP.VAL_CODI_VENT,
       PTO.COD_TIPO_OFER,
       MP.COD_SAP,
       NULL COD_CLIE,
       PSP.SOCA_OID_SOLI_CABE,
       NULL VAL_NUME_SOLI
FROM PED_SOLIC_CABEC PSC,
     PED_SOLIC_POSIC PSP,
     PRE_OFERT_DETAL POD,
     PRE_TIPO_OFERT PTO,
     MAE_PRODU MP
WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
  AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
  AND POD.TOFE_OID_TIPO_OFER = PTO.OID_TIPO_OFER
  AND PSP.PROD_OID_PROD = MP.OID_PROD
  AND PSC.PAIS_OID_PAIS = oidPais
  AND PSC.TSPA_OID_TIPO_SOLI_PAIS = oidTipoSoliPais
  AND PSC.PERD_OID_PERI = oidPeriodo
  AND PSC.FEC_FACT = TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')
  AND PSP.NUM_UNID_ATEN > 0
  AND PSP.OFDE_OID_DETA_OFER = oidDetaOfer
  AND NOT EXISTS (
      SELECT NULL
      FROM CYZ_DESPA_PRODU X
      WHERE X.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        AND X.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
  );

TYPE t_oidSoliCabe  IS TABLE OF PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
TYPE t_oidClie      IS TABLE OF PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE;
TYPE t_fecFact      IS TABLE OF PED_SOLIC_CABEC.FEC_FACT%TYPE;
TYPE t_oidSoliPosi  IS TABLE OF PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE;
TYPE t_numUnidDema  IS TABLE OF PED_SOLIC_POSIC.NUM_UNID_DEMA%TYPE;
TYPE t_numUnidAten  IS TABLE OF PED_SOLIC_POSIC.NUM_UNID_ATEN%TYPE;
TYPE t_oidProd      IS TABLE OF PED_SOLIC_POSIC.PROD_OID_PROD%TYPE;
TYPE t_valCodiVent  IS TABLE OF PED_SOLIC_POSIC.VAL_CODI_VENT%TYPE;
TYPE t_codTipoOfer  IS TABLE OF PRE_TIPO_OFERT.COD_TIPO_OFER%TYPE;
TYPE t_codProd      IS TABLE OF MAE_PRODU.COD_SAP%TYPE;
TYPE t_codClie      IS TABLE OF MAE_CLIEN.COD_CLIE%TYPE;
TYPE t_oidCons      IS TABLE OF PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
TYPE t_valNumeSoli  IS TABLE OF PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;

v_oidSoliCabe   t_oidSoliCabe;
v_oidClie       t_oidClie;
v_fecFact       t_fecFact;
v_oidSoliPosi   t_oidSoliPosi;
v_numUnidDema   t_numUnidDema;
v_numUnidAten   t_numUnidAten;
v_oidProd       t_oidProd;
v_valCodiVent   t_valCodiVent;
v_codTipoOfer   t_codTipoOfer;
v_codProd       t_codProd;
v_codClie       t_codClie;
v_oidCons       t_oidCons;
v_valNumeSoli   t_valNumeSoli;

CURSOR cOfertasRecuperadas(oidMarca NUMBER,
                           oidCanal NUMBER) IS
SELECT PMFA.OID_MATR_FACT,
       PMFA.OFDE_OID_DETA_OFER
FROM PRE_MATRI_FACTU PMF,
     PRE_OFERT_DETAL POD,
     CYZ_PRODU_PROGR_DUPLA PPD,
     PRE_MATRI_RECUP PMR,
     PRE_MATRI_FACTU PMFA
WHERE PMF.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
  AND PPD.VAL_CODI_VENT = POD.VAL_CODI_VENT
  AND PPD.PRDU_COD_PROG = psCodigoPrograma
  AND PMR.MAFA_OID_COD_RECU = PMF.OID_MATR_FACT
  AND PMR.MAFA_OID_COD_PPAL = PMFA.OID_MATR_FACT
  AND EXISTS (
      SELECT PMFC.OID_CABE
      FROM PRE_MATRI_FACTU_CABEC PMFC,
           CRA_PERIO CP,
           SEG_PERIO_CORPO SPC
      WHERE PMFC.PERD_OID_PERI = CP.OID_PERI
        AND CP.PERI_OID_PERI = SPC.OID_PERI
        AND CP.MARC_OID_MARC = oidMarca
        AND CP.CANA_OID_CANA = oidCanal
        AND PMF.MFCA_OID_CABE = PMFC.OID_CABE
        AND SPC.COD_PERI = PPD.COD_PERI
  );

TYPE t_oidMatrFact  IS TABLE OF PRE_MATRI_FACTU.OID_MATR_FACT%TYPE;
TYPE t_oidDetaOfer  IS TABLE OF PRE_OFERT_DETAL.OID_DETA_OFER%TYPE;

v_oidMatrFact   t_oidMatrFact;
v_oidDetaOfer   t_oidDetaOfer;

BEGIN

  -- Obtenemos los ID necesarios
  lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(COD_MARC_DEFE);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(COD_CANA_DEFE);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  SELECT TSP.OID_TIPO_SOLI_PAIS
  INTO lnOidTipoSoliPais
  FROM PED_TIPO_SOLIC_PAIS TSP,
       PED_TIPO_SOLIC TS
  WHERE TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
    AND TSP.PAIS_OID_PAIS = lnOidPais
    AND TS.COD_TIPO_SOLI = COD_TIPO_SOLI_OC;

  OPEN cProductos(lnOidPais, lnOidTipoSoliPais, lnOidPeriodo);
  LOOP
      FETCH cProductos BULK COLLECT INTO
            v_oidSoliCabe,
            v_oidClie,
            v_fecFact,
            v_oidSoliPosi,
            v_numUnidDema,
            v_numUnidAten,
            v_oidProd,
            v_valCodiVent,
            v_codTipoOfer,
            v_codProd,
            v_codClie,
            v_oidCons,
            v_valNumeSoli
            LIMIT W_FILAS;
      IF v_oidSoliCabe.COUNT > 0 THEN

         FOR i IN v_oidSoliCabe.FIRST..v_oidSoliCabe.LAST LOOP
           -- Manipulamos la informacion en memoria
           SELECT COD_CLIE
           INTO v_codClie(i)
           FROM MAE_CLIEN
           WHERE OID_CLIE = v_oidClie(i);

           SELECT VAL_NUME_SOLI
           INTO v_valNumeSoli(i)
           FROM PED_SOLIC_CABEC
           WHERE OID_SOLI_CABE = v_oidCons(i);

         END LOOP;

         FORALL i IN v_oidSoliCabe.FIRST..v_oidSoliCabe.LAST
            INSERT INTO CYZ_DESPA_PRODU(
            PAIS_COD_PAIS,
            PRDU_COD_PROG,
            COD_PERI,
            CLIE_OID_CLIE,
            COD_CLIE,
            SOCA_OID_SOLI_CABE,
            VAL_NUME_SOLI,
            FEC_FACT,
            COD_SAP,
            PROD_OID_PROD,
            DES_PROD,
            SOPO_OID_SOLI_POSI,
            NUM_UNID_DEMA,
            NUM_UNID_ATEN,
            VAL_CODI_VENT,
            COD_TIPO_OFER,
            USU_DIGI,
            FEC_DIGI
            )
            VALUES (
            psCodigoPais,
            psCodigoPrograma,
            psCodigoPeriodo,
            v_oidClie(i),
            v_codClie(i),
            v_oidSoliCabe(i),
            v_valNumeSoli(i),
            v_fecFact(i),
            v_codProd(i),
            v_oidProd(i),
            OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(v_oidProd(i)),
            v_oidSoliPosi(i),
            v_numUnidDema(i),
            v_numUnidAten(i),
            v_valCodiVent(i),
            v_codTipoOfer(i),
            psCodigoUsuario,
            SYSDATE
            );
      END IF;

      EXIT WHEN cProductos%NOTFOUND;
  END LOOP;
  CLOSE cProductos;

  -- Obtenemos los despachos de recuperaciones de campañas anteriores
  OPEN cOfertasRecuperadas(lnOidMarca, lnOidCanal);
  LOOP
      FETCH cOfertasRecuperadas BULK COLLECT INTO
            v_oidMatrFact,
            v_oidDetaOfer
            LIMIT W_FILAS;
      IF v_oidMatrFact.COUNT > 0 THEN

        FOR j IN v_oidMatrFact.FIRST..v_oidMatrFact.LAST LOOP
          lnOidDetaOfer := v_oidDetaOfer(j);

          OPEN cProductosRecuperados(lnOidPais, lnOidTipoSoliPais, lnOidPeriodo, lnOidDetaOfer);
          LOOP
              FETCH cProductosRecuperados BULK COLLECT INTO
                    v_oidSoliCabe,
                    v_oidClie,
                    v_fecFact,
                    v_oidSoliPosi,
                    v_numUnidDema,
                    v_numUnidAten,
                    v_oidProd,
                    v_valCodiVent,
                    v_codTipoOfer,
                    v_codProd,
                    v_codClie,
                    v_oidCons,
                    v_valNumeSoli
                    LIMIT W_FILAS;
              IF v_oidSoliCabe.COUNT > 0 THEN

                 FOR k IN v_oidSoliCabe.FIRST..v_oidSoliCabe.LAST LOOP
                   -- Manipulamos la informacion en memoria
                   SELECT COD_CLIE
                   INTO v_codClie(k)
                   FROM MAE_CLIEN
                   WHERE OID_CLIE = v_oidClie(k);

                   SELECT VAL_NUME_SOLI
                   INTO v_valNumeSoli(k)
                   FROM PED_SOLIC_CABEC
                   WHERE OID_SOLI_CABE = v_oidCons(k);

                 END LOOP;

                 FORALL k IN v_oidSoliCabe.FIRST..v_oidSoliCabe.LAST
                    INSERT INTO CYZ_DESPA_PRODU(
                    PAIS_COD_PAIS,
                    PRDU_COD_PROG,
                    COD_PERI,
                    CLIE_OID_CLIE,
                    COD_CLIE,
                    SOCA_OID_SOLI_CABE,
                    VAL_NUME_SOLI,
                    FEC_FACT,
                    COD_SAP,
                    PROD_OID_PROD,
                    DES_PROD,
                    SOPO_OID_SOLI_POSI,
                    NUM_UNID_DEMA,
                    NUM_UNID_ATEN,
                    VAL_CODI_VENT,
                    COD_TIPO_OFER,
                    USU_DIGI,
                    FEC_DIGI
                    )
                    VALUES (
                    psCodigoPais,
                    psCodigoPrograma,
                    psCodigoPeriodo,
                    v_oidClie(k),
                    v_codClie(k),
                    v_oidSoliCabe(k),
                    v_valNumeSoli(k),
                    v_fecFact(k),
                    v_codProd(k),
                    v_oidProd(k),
                    OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(v_oidProd(k)),
                    v_oidSoliPosi(k),
                    v_numUnidDema(k),
                    v_numUnidAten(k),
                    v_valCodiVent(k),
                    v_codTipoOfer(k),
                    psCodigoUsuario,
                    SYSDATE
                    );
              END IF;

              EXIT WHEN cProductosRecuperados%NOTFOUND;
          END LOOP;
          CLOSE cProductosRecuperados;

        END LOOP;

      END IF;
      EXIT WHEN cOfertasRecuperadas%NOTFOUND;
  END LOOP;
  CLOSE cOfertasRecuperadas;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_CYZ_CARGA_PRODU_DESPA: '||ls_sqlerrm);

END INT_PR_CYZ_CARGA_PRODU_DESPA;

/***************************************************************************
Descripcion       : Procedimiento que realiza el proceso de Carga de los premios
                    despachados a una consultora, que pertenecen a un programa
                    de Cyzone especifico, los cuales han sido solicitados desde
                    la web
Fecha Creacion    : 19/01/2009
Autor             : Carlos Hurtado
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodigoProgramaPremio : Codigo de Programa
            psCodigoPeriodo   : Codigo de Periodo
            psFechaFacturacion : Fecha de Facturacion (DD/MM/YYYY)
            psCodigoUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE INT_PR_CYZ_CARGA_PREMI_DESPA(psCodigoPais             VARCHAR2,
                                       psCodigoProgramaPremio   VARCHAR2,
                                       psCodigoPeriodo          VARCHAR2,
                                       psFechaFacturacion       VARCHAR2,
                                       psCodigoUsuario          VARCHAR2) IS

lnOidPais           NUMBER;
lnOidMarca          NUMBER;
lnOidCanal          NUMBER;
lnOidPeriodo        NUMBER;
lnOidTipoSoliPais   NUMBER;
lnOidDetaOfer       NUMBER;

CURSOR cPremios(oidPais NUMBER,
                oidTipoSoliPais NUMBER,
                oidPeriodo NUMBER) IS
SELECT PSC.OID_SOLI_CABE,
       PSC.CLIE_OID_CLIE,
       PSC.FEC_FACT,
       PSP.OID_SOLI_POSI,
       PSP.NUM_UNID_DEMA,
       PSP.NUM_UNID_ATEN,
       PSP.PROD_OID_PROD,
       PSP.VAL_CODI_VENT,
       PTO.COD_TIPO_OFER,
       MP.COD_SAP,
       NULL COD_CLIE,
       PSP.SOCA_OID_SOLI_CABE,
       NULL VAL_NUME_SOLI
FROM PED_SOLIC_CABEC PSC,
     PED_SOLIC_POSIC PSP,
     PRE_OFERT_DETAL POD,
     PRE_TIPO_OFERT PTO,
     MAE_PRODU MP
WHERE PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
  AND PSP.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER
  AND POD.TOFE_OID_TIPO_OFER = PTO.OID_TIPO_OFER
  AND PSP.PROD_OID_PROD = MP.OID_PROD
  AND PSC.PAIS_OID_PAIS = oidPais
  AND PSC.TSPA_OID_TIPO_SOLI_PAIS = oidTipoSoliPais
  AND PSC.PERD_OID_PERI = oidPeriodo
  AND PSC.FEC_FACT = TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')
  -- AND PSP.NUM_UNID_ATEN > 0
  AND EXISTS (
      SELECT NULL
      FROM CYZ_PRODU_PROGR_DUPLA PPD
      WHERE PPD.PAIS_COD_PAIS = psCodigoPais
        AND PPD.PRDU_COD_PROG = psCodigoProgramaPremio
        AND PPD.COD_PERI = psCodigoPeriodo
        AND PPD.COD_PROD = MP.COD_SAP
        AND PPD.VAL_CODI_VENT = PSP.VAL_CODI_VENT
  )
  AND NOT EXISTS (
      SELECT NULL
      FROM CYZ_DESPA_PRODU X
      WHERE X.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        AND X.SOPO_OID_SOLI_POSI = PSP.OID_SOLI_POSI
  );

TYPE t_oidSoliCabe  IS TABLE OF PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
TYPE t_oidClie      IS TABLE OF PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE;
TYPE t_fecFact      IS TABLE OF PED_SOLIC_CABEC.FEC_FACT%TYPE;
TYPE t_oidSoliPosi  IS TABLE OF PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE;
TYPE t_numUnidDema  IS TABLE OF PED_SOLIC_POSIC.NUM_UNID_DEMA%TYPE;
TYPE t_numUnidAten  IS TABLE OF PED_SOLIC_POSIC.NUM_UNID_ATEN%TYPE;
TYPE t_oidProd      IS TABLE OF PED_SOLIC_POSIC.PROD_OID_PROD%TYPE;
TYPE t_valCodiVent  IS TABLE OF PED_SOLIC_POSIC.VAL_CODI_VENT%TYPE;
TYPE t_codTipoOfer  IS TABLE OF PRE_TIPO_OFERT.COD_TIPO_OFER%TYPE;
TYPE t_codProd      IS TABLE OF MAE_PRODU.COD_SAP%TYPE;
TYPE t_codClie      IS TABLE OF MAE_CLIEN.COD_CLIE%TYPE;
TYPE t_oidCons      IS TABLE OF PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
TYPE t_valNumeSoli  IS TABLE OF PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;

v_oidSoliCabe   t_oidSoliCabe;
v_oidClie       t_oidClie;
v_fecFact       t_fecFact;
v_oidSoliPosi   t_oidSoliPosi;
v_numUnidDema   t_numUnidDema;
v_numUnidAten   t_numUnidAten;
v_oidProd       t_oidProd;
v_valCodiVent   t_valCodiVent;
v_codTipoOfer   t_codTipoOfer;
v_codProd       t_codProd;
v_codClie       t_codClie;
v_oidCons       t_oidCons;
v_valNumeSoli   t_valNumeSoli;

BEGIN

  -- Obtenemos los ID necesarios
  lnOidPais  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(COD_MARC_DEFE);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(COD_CANA_DEFE);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  SELECT TSP.OID_TIPO_SOLI_PAIS
  INTO lnOidTipoSoliPais
  FROM PED_TIPO_SOLIC_PAIS TSP,
       PED_TIPO_SOLIC TS
  WHERE TSP.TSOL_OID_TIPO_SOLI = TS.OID_TIPO_SOLI
    AND TSP.PAIS_OID_PAIS = lnOidPais
    AND TS.COD_TIPO_SOLI = COD_TIPO_SOLI_OC;

  OPEN cPremios(lnOidPais, lnOidTipoSoliPais, lnOidPeriodo);
  LOOP
      FETCH cPremios BULK COLLECT INTO
            v_oidSoliCabe,
            v_oidClie,
            v_fecFact,
            v_oidSoliPosi,
            v_numUnidDema,
            v_numUnidAten,
            v_oidProd,
            v_valCodiVent,
            v_codTipoOfer,
            v_codProd,
            v_codClie,
            v_oidCons,
            v_valNumeSoli
            LIMIT W_FILAS;
      IF v_oidSoliCabe.COUNT > 0 THEN

         FOR i IN v_oidSoliCabe.FIRST..v_oidSoliCabe.LAST LOOP
           -- Manipulamos la informacion en memoria
           SELECT COD_CLIE
           INTO v_codClie(i)
           FROM MAE_CLIEN
           WHERE OID_CLIE = v_oidClie(i);

           SELECT VAL_NUME_SOLI
           INTO v_valNumeSoli(i)
           FROM PED_SOLIC_CABEC
           WHERE OID_SOLI_CABE = v_oidCons(i);

         END LOOP;

         FORALL i IN v_oidSoliCabe.FIRST..v_oidSoliCabe.LAST
            INSERT INTO CYZ_DESPA_PRODU(
            PAIS_COD_PAIS,
            PRDU_COD_PROG,
            COD_PERI,
            CLIE_OID_CLIE,
            COD_CLIE,
            SOCA_OID_SOLI_CABE,
            VAL_NUME_SOLI,
            FEC_FACT,
            COD_SAP,
            PROD_OID_PROD,
            DES_PROD,
            SOPO_OID_SOLI_POSI,
            NUM_UNID_DEMA,
            NUM_UNID_ATEN,
            VAL_CODI_VENT,
            COD_TIPO_OFER,
            USU_DIGI,
            FEC_DIGI
            )
            VALUES (
            psCodigoPais,
            psCodigoProgramaPremio,
            psCodigoPeriodo,
            v_oidClie(i),
            v_codClie(i),
            v_oidSoliCabe(i),
            v_valNumeSoli(i),
            v_fecFact(i),
            v_codProd(i),
            v_oidProd(i),
            OCR_SOLIC_PEDIDOS.GEN_FN_DESC_PROD(v_oidProd(i)),
            v_oidSoliPosi(i),
            v_numUnidDema(i),
            v_numUnidAten(i),
            v_valCodiVent(i),
            v_codTipoOfer(i),
            psCodigoUsuario,
            SYSDATE
            );
      END IF;

      EXIT WHEN cPremios%NOTFOUND;
  END LOOP;
  CLOSE cPremios;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_CYZ_CARGA_PREMI_DESPA: '||ls_sqlerrm);

END INT_PR_CYZ_CARGA_PREMI_DESPA;


/***************************************************************************
Descripcion       : Procedimiento que genera el archivo de interfaz a enviar a
                    la web de Cyzone con la informacion de productos y premios
                    despachados.
Fecha Creacion    : 02/01/2009
Autor             : Carlos Hurtado
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodigoSistema : Codigo de Sistema
            psCodigoInterfaz   : Codigo de Interfaz
            psNombreArchivo : Nombre de Archivo de Interfaz
            psCodigoPrograma : Codigo de Programa
            psCodigoPeriodo   : Codigo de Periodo
            psFechaFacturacion : Fecha de Facturacion (DD/MM/YYYY)
            psCodigoProgramaPremio : Codigo de Programa de Premio
***************************************************************************/
PROCEDURE INT_PR_CYZ_ENVIA_PRODU_DESPA(psCodigoPais           VARCHAR2,
                                       psCodigoSistema        VARCHAR2,
                                       psCodigoInterfaz       VARCHAR2,
                                       psNombreArchivo        VARCHAR2,
                                       psCodigoPrograma       VARCHAR2,
                                       psCodigoPeriodo        VARCHAR2,
                                       psFechaFacturacion     VARCHAR2,
                                       psCodigoProgramaPremio VARCHAR2) IS

CURSOR c_interfaz IS
SELECT 1,
       PAIS_COD_PAIS,
       COD_CLIE,
       COD_SAP,
       VAL_CODI_VENT,
       COD_TIPO_OFER,
       COD_PERI,
       TO_CHAR(FEC_FACT, 'YYYYMMDD'),
       NUM_UNID_DEMA,
       1, -- Entregado
       0,
       NULL,
       NUM_UNID_ATEN
FROM CYZ_DESPA_PRODU
WHERE PAIS_COD_PAIS = psCodigoPais
  AND PRDU_COD_PROG = psCodigoPrograma
  AND COD_PERI = psCodigoPeriodo
  AND FEC_FACT = TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')
  AND NUM_UNID_ATEN > 0 -- para el caso del CySet se envian solo los despachados

UNION ALL

SELECT 2,
       PAIS_COD_PAIS,
       COD_CLIE,
       COD_SAP,
       VAL_CODI_VENT,
       COD_TIPO_OFER,
       COD_PERI,
       TO_CHAR(FEC_FACT, 'YYYYMMDD'),
       NUM_UNID_DEMA,
       CASE
           WHEN NUM_UNID_ATEN > 0 THEN 1 -- Entregado
           ELSE 0 -- No entregado
       END,
       0,
       NULL,
       NUM_UNID_ATEN
FROM CYZ_DESPA_PRODU
WHERE PAIS_COD_PAIS = psCodigoPais
  AND PRDU_COD_PROG = psCodigoProgramaPremio
  AND COD_PERI = psCodigoPeriodo
  AND FEC_FACT = TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')

UNION ALL

SELECT 2,
       A.PAIS_COD_PAIS,
       A.COD_CLIE,
       P.COD_PROD,
       A.VAL_CODI_VENT,
       P.COD_TIPO_OFER,
       A.COD_PERI,
       TO_CHAR(A.FEC_SOLI, 'YYYYMMDD'),
       A.NUM_UNID_SOLI,
       0, -- No entregado
       3, -- Otro, no paso pedido
       NULL,
       0 -- sin unidades atendidas
FROM CYZ_SOLIC_PRODU A,
     CYZ_PRODU_PROGR_DUPLA P
WHERE A.PAIS_COD_PAIS = P.PAIS_COD_PAIS
AND A.PRDU_COD_PROG = P.PRDU_COD_PROG
AND A.COD_PERI = P.COD_PERI
AND A.VAL_CODI_VENT = P.VAL_CODI_VENT
AND A.PAIS_COD_PAIS = psCodigoPais
AND A.PRDU_COD_PROG = psCodigoProgramaPremio
AND A.COD_PERI = psCodigoPeriodo
AND NOT EXISTS (
    SELECT NULL
    FROM CYZ_DESPA_PRODU B
    WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
    AND A.PRDU_COD_PROG = B.PRDU_COD_PROG
    AND A.COD_PERI = B.COD_PERI
    AND A.COD_CLIE = B.COD_CLIE
    AND B.FEC_FACT = TO_DATE(psFechaFacturacion, 'DD/MM/YYYY')
);

TYPE interfazRec IS RECORD
(
tipoDato                 NUMBER,
codigoPais               CYZ_DESPA_PRODU.PAIS_COD_PAIS%TYPE,
codigoCliente            CYZ_DESPA_PRODU.COD_CLIE%TYPE,
codigoProducto           CYZ_DESPA_PRODU.COD_SAP%TYPE,
codigoVenta              CYZ_DESPA_PRODU.VAL_CODI_VENT%TYPE,
codigoTipoOferta         CYZ_DESPA_PRODU.COD_TIPO_OFER%TYPE,
codigoPeriodo            CYZ_DESPA_PRODU.COD_PERI%TYPE,
fechaFacturacion         VARCHAR2(8),
numeroUnidadesDemandadas NUMBER,
estadoEntrega            NUMBER,
motivoNoEntrega          NUMBER,
codigoReemplazo          CYZ_DESPA_PRODU.COD_SAP%TYPE,
numeroUnidadesAtendidas  NUMBER
);

TYPE interfazRecTab  IS TABLE OF interfazRec ;
interfazRecord interfazRecTab;

/* Variables usadas para la generacion del archivo de texto */
lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
W_FILAS             NUMBER := 1000 ;
v_handle            UTL_FILE.FILE_TYPE;
lsLinea             VARCHAR2(1000);
lsNombreArchivo     VARCHAR2(50);
lnIdPais            NUMBER;
lnIdPeriodo         NUMBER;
lbAbrirUtlFile      BOOLEAN;

BEGIN

   /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       /* Procedimiento inicial para generar interfaz */
       IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
       END IF;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=  interfazRecord(x).tipoDato ||';'||
                          interfazRecord(x).codigoPais ||';'||
                          interfazRecord(x).codigoCliente ||';'||
                          interfazRecord(x).codigoProducto ||';'||
                          interfazRecord(x).codigoVenta ||';'||
                          interfazRecord(x).codigoTipoOferta ||';'||
                          interfazRecord(x).codigoPeriodo ||';'||
                          interfazRecord(x).fechaFacturacion ||';'||
                          interfazRecord(x).numeroUnidadesDemandadas ||';'||
                          interfazRecord(x).estadoEntrega ||';'||
                          interfazRecord(x).motivoNoEntrega ||';'||
                          interfazRecord(x).codigoReemplazo ||';'||
                          interfazRecord(x).numeroUnidadesAtendidas;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;
    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_CYZ_ENVIA_PRODU_DESPA: '||ls_sqlerrm);

END INT_PR_CYZ_ENVIA_PRODU_DESPA;

/***************************************************************************
Descripcion       : Procedimiento que realiza el proceso de Carga de los productos
                    despachados a una consultora, que pertenecen a alguno de los
                    programas del Welcome Pack, para lo cual hace uso de otro
                    procedimiento que hace la carga de un programa especifico.
Fecha Creacion    : 13/02/2009
Autor             : Carlos Hurtado
Parametros        :
            psCodigoPais : Codigo de Pais
            psCodigoPeriodo   : Codigo de Periodo
            psFechaFacturacion : Fecha de Facturacion (DD/MM/YYYY)
            psCodigoUsuario : Codigo de Usuario
***************************************************************************/
PROCEDURE INT_PR_CYZ_CARGA_PRODU_BIENV(psCodigoPais       VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2,
                                       psCodigoUsuario    VARCHAR2) IS

CURSOR cProgramaProducto IS
SELECT PD.COD_PAIS,
       PD.COD_PROG
FROM CYZ_PROGR_DUPLA PD
WHERE PD.COD_PERI_INIC <= psCodigoPeriodo
  AND PD.COD_PERI_FINA >= psCodigoPeriodo
  AND PD.EST_PROG = '1' -- Estado Activo
  AND PD.IND_PAQU_BIEN = 1 -- Welcome Pack
  AND EXISTS (
      SELECT NULL
      FROM CYZ_PRODU_PROGR_DUPLA PPD
      WHERE PD.COD_PAIS = PPD.PAIS_COD_PAIS
        AND PD.COD_PROG = PPD.PRDU_COD_PROG
        AND PPD.COD_PERI = psCodigoPeriodo
        AND PPD.EST_PROD_PRDU = '1' -- Estado Activo
  );

TYPE t_codPais      IS TABLE OF CYZ_PROGR_DUPLA.COD_PAIS%TYPE;
TYPE t_codProg      IS TABLE OF CYZ_PROGR_DUPLA.COD_PROG%TYPE;

v_codPais       t_codPais;
v_codProg       t_codProg;

BEGIN

    -- Hacemos la carga de los productos para los programas vigentes del welcome pack
    OPEN cProgramaProducto;
    LOOP
        FETCH cProgramaProducto BULK COLLECT INTO
              v_codPais,
              v_codProg
              LIMIT W_FILAS;
        IF v_codProg.COUNT > 0 THEN
            FOR k IN v_codProg.FIRST .. v_codProg.LAST LOOP

                INT_PR_CYZ_CARGA_PRODU_DESPA(v_codPais(k), v_codProg(k), psCodigoPeriodo, psFechaFacturacion, psCodigoUsuario);

            END LOOP;
        END IF;

        EXIT WHEN cProgramaProducto%NOTFOUND;
    END LOOP;
    CLOSE cProgramaProducto;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_CYZ_CARGA_PRODU_BIENV: '||ls_sqlerrm);

END INT_PR_CYZ_CARGA_PRODU_BIENV;

END;
/

