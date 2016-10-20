CREATE OR REPLACE PACKAGE "COB_PKG_CLIEN_CASTI" AS

  PROCEDURE COB_PR_CARGA_CLIEN_CASTI(P_COD_PAIS IN VARCHAR2,
                                     P_COD_ENTI IN VARCHAR2,
                                     P_COD_PERI IN VARCHAR2,
                                     P_NUM_DIAS IN NUMBER,
                                     P_IMP_MINI IN NUMBER);

  PROCEDURE COB_PR_CARGA_INICI;

END COB_PKG_CLIEN_CASTI;
/

CREATE OR REPLACE PACKAGE BODY "COB_PKG_CLIEN_CASTI" AS

  PROCEDURE COB_PR_CARGA_CLIEN_CASTI(P_COD_PAIS IN VARCHAR2,
                                     P_COD_ENTI IN VARCHAR2,
                                     P_COD_PERI IN VARCHAR2,
                                     P_NUM_DIAS IN NUMBER,
                                     P_IMP_MINI IN NUMBER) IS
  BEGIN

   -- Eliminamos los registros que estan en la tabla con toda la informacion
   -- a enviar, cuyos OID se encuentren en la tabla que es actualizada por
   -- el trigger
   DELETE FROM INT_COB_CLIEN_CASTI
   WHERE COD_CLIE IN (SELECT MAE_CLIEN.COD_CLIE
                        FROM MAE_CLIEN, INT_COB_OID_CLIEN
        WHERE MAE_CLIEN.OID_CLIE = INT_COB_OID_CLIEN.OID_CLIE);

      -- Insertamos a los clientes que no estan en la tabla y que tienen deuda
      INSERT INTO INT_COB_CLIEN_CASTI (COD_CLIE,
                                       COD_ENTI,
                                       COD_TIPO_DOCU_CLIE,
                                       NUM_DOCU_IDEN,
                                       NOM_COMP_CLIE,
                                       DIR_COMP_CLIE,
                                       DES_GEO1,
                                       DES_GEO2,
                                       FEC_VENC,
                                       IMP_PEND)
      SELECT MAE_CLIEN.COD_CLIE,
             P_COD_ENTI COD_ENTI,
             CASE MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU
             WHEN 1 THEN '1'
             WHEN 2 THEN '2'
             ELSE '0'
             END COD_TIPO_DOCU,
             MAE_CLIEN_IDENT.NUM_DOCU_IDEN,
             TRIM(MAE_CLIEN.VAL_APE1) || ' ' || TRIM(MAE_CLIEN.VAL_APE2) || ' ' || TRIM(MAE_CLIEN.VAL_NOM1) || ' ' || TRIM(MAE_CLIEN.VAL_NOM2) AS NOM_COMP_CLIE,
             TRIM(DES_ABRV_TIPO_VIA) || ' ' || TRIM(VAL_NOMB_VIA) || ' ' || TRIM(NUM_PPAL) AS DIR_COMP_CLIE,
             GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 3) AS DES_GEO1,
             GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(MAE_CLIEN.PAIS_OID_PAIS, MAE_CLIEN.OID_CLIE, 1) AS DES_GEO2,
             MIN(FEC_VENC) AS FEC_VENC,
             SUM(IMP_PEND) AS IMP_PEND
        FROM MAE_CLIEN,
             MAE_CLIEN_DIREC,
             MAE_CLIEN_IDENT,
             CCC_MOVIM_CUENT_CORRI,
             CRA_PERIO,
             SEG_PERIO_CORPO,
             SEG_TIPO_VIA,
             SEG_PAIS,
             INT_COB_OID_CLIEN
      WHERE CCC_MOVIM_CUENT_CORRI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE
        AND MAE_CLIEN.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS
        AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_IDENT.CLIE_OID_CLIE
        AND MAE_CLIEN.OID_CLIE = MAE_CLIEN_DIREC.CLIE_OID_CLIE
        AND MAE_CLIEN_DIREC.TIVI_OID_TIPO_VIA = SEG_TIPO_VIA.OID_TIPO_VIA
        AND CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI = CRA_PERIO.OID_PERI
        AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
        AND MAE_CLIEN.OID_CLIE = INT_COB_OID_CLIEN.OID_CLIE
        AND MAE_CLIEN_IDENT.VAL_IDEN_DOCU_PRIN = 1
        AND MAE_CLIEN_DIREC.IND_DIRE_PPAL = 1
        AND SEG_PAIS.COD_PAIS = P_COD_PAIS
        AND SEG_PERIO_CORPO.COD_PERI >= P_COD_PERI
        AND SYSDATE - CCC_MOVIM_CUENT_CORRI.FEC_DOCU >= P_NUM_DIAS
        AND CCC_MOVIM_CUENT_CORRI.FEC_VENC < SYSDATE
        AND CCC_MOVIM_CUENT_CORRI.IMP_PEND > 0
      GROUP BY (MAE_CLIEN.PAIS_OID_PAIS,
                MAE_CLIEN.OID_CLIE,
                MAE_CLIEN.COD_CLIE,
                MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU,
                NUM_DOCU_IDEN,
                TRIM(MAE_CLIEN.VAL_APE1) || ' ' || TRIM(MAE_CLIEN.VAL_APE2) || ' ' || TRIM(MAE_CLIEN.VAL_NOM1) || ' ' || TRIM(MAE_CLIEN.VAL_NOM2),
                TRIM(DES_ABRV_TIPO_VIA) || ' ' || TRIM(VAL_NOMB_VIA) || ' ' || TRIM(NUM_PPAL))
         HAVING SUM(IMP_PEND) >= P_IMP_MINI;

   -- Eliminamos los oids cuya fecha de modificacion sean menor
   -- a la fecha actual menos la cantidad de dias de evalucacion
   DELETE FROM INT_COB_OID_CLIEN
   WHERE FEC_REGI <= SYSDATE - P_NUM_DIAS;

  END COB_PR_CARGA_CLIEN_CASTI;

  PROCEDURE COB_PR_CARGA_INICI IS
  BEGIN

      -- Eliminamos la informacion de la tabla en caso hayan registros
      DELETE FROM INT_COB_CLIEN_CASTI;

   -- Pasamos la informacion de la tabla temporal a la definitiva
   -- haciendo las transformaciones respectivas
   INSERT INTO INT_COB_CLIEN_CASTI (
       COD_CLIE,
    COD_TIPO_MOVI,
          COD_ENTI,
    COD_TIPO_DOCU_TRIB,
    COD_TIPO_DOCU_CLIE,
          NUM_DOCU_IDEN,
    COD_TIPO_PERS,
    COD_TIPO_DEUD,
          NOM_COMP_CLIE,
    DIR_COMP_CLIE,
    DES_GEO1,
          DES_GEO2,
    FEC_VENC,
    COD_TIPO_DOCU,
          COD_TIPO_MONE,
    IMP_PEND,
    COD_COND_DEUD)
   SELECT COD_CLIE,
          COD_TIPO_MOVI,
       COD_ENTI,
       COD_TIPO_DOCU_TRIB,
    COD_TIPO_DOCU_CLIE,
    NUM_DOCU_CLIE,
          COD_TIPO_PERS,
    COD_TIPO_DEUD,
    NOM_COMP_CLIE,
          DIR_COMP_CLIE,
    DES_GEO1,
    DES_GEO2,
          TO_DATE(FEC_VENC, 'YYYYMMDD'),
    COD_TIPO_DOCU,
    COD_TIPO_MONE,
          TO_NUMBER(SUBSTR(IMP_PEND, 1, 10) ||'.' || SUBSTR(IMP_PEND, 11, 2), '9999999999.99'),
    COD_COND_DEUD
     FROM INT_TMP_CLIEN_CASTI;


  END COB_PR_CARGA_INICI;

END;
/

