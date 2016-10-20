create or replace package MYE_PKG_REPOR is

    /* Declaracion de variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=3000;

  /***********************************************************************************
  Descripcion        : Proceso que se encarga de obtener las cxonsultoras para un period dado y que tengan
                       el numero de retencion igual al parametro ingresado
  Fecha Creacion     : 04/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psCodigoRegion     Codigo Region
    psCodigoZona       Codigo Zona
    psNumeroRetencion  Numero Retencion

  Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE MYE_PR_GENER_REPOR_RETEN_PEDID(
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psCodigoRegion     VARCHAR2,
    psCodigoZona       VARCHAR2,
    psNumeroRetencion  VARCHAR2 );




  /***********************************************************************************
  Descripcion        : Proceso que se encarga de obtener las cxonsultoras que hayan pasado pedido
  Fecha Creacion     : 04/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psCodigoRegion     Codigo Region
    psCodigoZona       Codigo Zona

  Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE MYE_PR_GENER_REPOR_PEDID_DIGIT(
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psCodigoRegion     VARCHAR2,
    psCodigoZona       VARCHAR2);


  /***********************************************************************************
  Descripcion        : Proceso que se encarga de obtener las cxonsultoras para un period dado y que tengan
                       el numero de retencion igual al parametro ingresado
  Fecha Creacion     : 04/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psCodigoRegion     Codigo Region
    psCodigoZona       Codigo Zona
    psNumeroRetencion  Numero Retencion

  Autor              : Sergio Buchelli
  ***************************************************************************/
  FUNCTION MYE_FN_DEVUE_RETEN_PEDID(
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psCodigoRegion     VARCHAR2,
    psCodigoZona       VARCHAR2,
    psNumeroRetencion  VARCHAR2,
    psOidCliente  NUMBER ) RETURN VARCHAR2;


/***********************************************************************************
  Descripcion        : Proceso que se encarga de obtener EL ESTATUS ANTERIOR DE AL CONSULTORA
  Fecha Creacion     : 08/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psOidCliente       Oid Cliente

  Autor              : Sergio Buchelli
  ***************************************************************************/
  FUNCTION MYE_FN_DEVUE_ESTAT_ANTER(
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psOidCliente  NUMBER ) RETURN NUMBER;


end MYE_PKG_REPOR;
/

create or replace package body MYE_PKG_REPOR is


  /***********************************************************************************
  Descripcion        : Proceso que se encarga de obtener las cxonsultoras para un period dado y que tengan
                       el numero de retencion igual al parametro ingresado
  Fecha Creacion     : 04/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psCodigoRegion     Codigo Region
    psCodigoZona       Codigo Zona
    psNumeroRetencion  Numero Retencion

  Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE MYE_PR_GENER_REPOR_RETEN_PEDID(
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psCodigoRegion     VARCHAR2,
    psCodigoZona       VARCHAR2,
    psNumeroRetencion  VARCHAR2 )
IS
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

  CURSOR cursorCliente IS
    SELECT DISTINCT A.OID_CLIE,
           A.COD_CLIE
         FROM MAE_CLIEN A,
         MAE_CLIEN_DATOS_ADICI B,
         MAE_CLIEN_UNIDA_ADMIN D,
         ZON_TERRI_ADMIN E,
         ZON_TERRI F,
         ZON_SECCI G,
         ZON_ZONA H,
         ZON_REGIO I
         WHERE A.OID_CLIE = B.CLIE_OID_CLIE
           AND B.IND_ACTI = '1'
           AND I.COD_REGI=psCodigoRegion
           AND H.COD_ZONA=psCodigoZona
           AND A.OID_CLIE = D.CLIE_OID_CLIE
           AND D.ZTAD_OID_TERR_ADMI = E.OID_TERR_ADMI
           AND E.TERR_OID_TERR = F.OID_TERR
           AND E.ZSCC_OID_SECC = G.OID_SECC
           AND G.ZZON_OID_ZONA = H.OID_ZONA
           AND H.ZORG_OID_REGI = I.OID_REGI;


  TYPE t_oid_clie IS TABLE OF MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE%TYPE ;
  TYPE t_cod_clie IS TABLE OF MAE_CLIEN.COD_CLIE%TYPE ;


 v_oid_clie t_oid_clie;
 v_cod_clie t_cod_clie;

  lsIsValid BOOLEAN:=FALSE;
  lsCodigoPeriodo VARCHAR2(6);
  lnCont NUMBER:=0;
  lsIndicadorRetencion VARCHAR2(1);
BEGIN
      EXECUTE IMMEDIATE 'TRUNCATE TABLE GTT_MYE_REPOR_PEDID';
     OPEN cursorCliente;
                 LOOP

                  FETCH cursorCliente BULK COLLECT INTO
                                            v_oid_clie,
                                            v_cod_clie LIMIT rows;

                    IF v_oid_clie.COUNT > 0 THEN


                        FOR x IN v_oid_clie.FIRST .. v_oid_clie.LAST LOOP

                            --se hace la validacion por cada cliente activa de la zona
                              lsIndicadorRetencion:= MYE_FN_DEVUE_RETEN_PEDID(psCodigoPais,
                                                                             psCodigoPeriodo,
                                                                             psCodigoRegion,
                                                                             psCodigoZona,
                                                                             psNumeroRetencion,
                                                                             v_oid_clie(x));--1:valido 0:no valido




                             IF(lsIndicadorRetencion='1') THEN

                              INSERT INTO GTT_MYE_REPOR_PEDID (
                                   COD_CLIE,
                                   COD_REGI,
                                   COD_ZONA,
                                   VAL_NUME_RETE,
                                   ULT_CAMP_FACT)
                                VALUES (v_cod_clie(x),
                                    psCodigoRegion,
                                    psCodigoZona,
                                    psNumeroRetencion,
                                    psCodigoPeriodo
                                );

                            END IF;

                        END LOOP;
                    END IF;

                 EXIT WHEN cursorCliente%NOTFOUND;
                 END LOOP;
       CLOSE cursorCliente;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MYE_PR_GENER_REPOR_RETEN_PEDID: ' || ls_sqlerrm);
END MYE_PR_GENER_REPOR_RETEN_PEDID;



  /***********************************************************************************
  Descripcion        : Proceso que se encarga de obtener las cxonsultoras que hayan pasado pedido
  Fecha Creacion     : 04/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psCodigoRegion     Codigo Region
    psCodigoZona       Codigo Zona

  Autor              : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE MYE_PR_GENER_REPOR_PEDID_DIGIT(
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psCodigoRegion     VARCHAR2,
    psCodigoZona       VARCHAR2)
IS
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

  CURSOR cursorCliente IS
    SELECT DISTINCT X.COD_CLIE,
           X.CLIE_OID_CLIE,
           CASE
                   WHEN X.IND_RECE_OCR='1'  THEN
                       '0CR'
                   WHEN X.IND_RECE_WEB='1'  THEN
                       'WEB'
                   WHEN X.IND_RECE_DD='1'   THEN
                        'DD'
                   WHEN X.IND_RECE_DIGI='1' THEN
                        'DIGI'
                   ELSE 'OTR'
           END MED_INGR,
           NVL(X.FEC_DIGI,X.FEC_MODI) FEC_DIGI,
           X.FEC_PROG_FACT FEC_PEDI
    FROM INT_SOLIC_CONSO_CABEC X
    WHERE  X.COD_PAIS = psCodigoPais
     AND X.COD_PERI = psCodigoPeriodo
     AND X.COD_REGI= psCodigoRegion
     AND X.COD_ZONA =psCodigoZona
     AND X.COD_CLIE IN(
        SELECT A.COD_CLIE
          FROM MAE_CLIEN A,
          MAE_CLIEN_DATOS_ADICI B,
          MAE_CLIEN_UNIDA_ADMIN D,
          ZON_TERRI_ADMIN E,
          ZON_TERRI F,
          ZON_SECCI G,
          ZON_ZONA H,
          ZON_REGIO I
         WHERE A.OID_CLIE = B.CLIE_OID_CLIE
           AND X.COD_CLIE= A.COD_CLIE
           AND B.IND_ACTI = '1'
           AND I.COD_REGI=psCodigoRegion
           AND H.COD_ZONA=psCodigoZona
           AND A.OID_CLIE = D.CLIE_OID_CLIE
           AND D.ZTAD_OID_TERR_ADMI = E.OID_TERR_ADMI
           AND E.TERR_OID_TERR = F.OID_TERR
           AND E.ZSCC_OID_SECC = G.OID_SECC
           AND G.ZZON_OID_ZONA = H.OID_ZONA
           AND H.ZORG_OID_REGI = I.OID_REGI);


  TYPE t_oid_clie  IS TABLE OF MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE%TYPE ;
  TYPE t_cod_clie  IS TABLE OF MAE_CLIEN.COD_CLIE%TYPE ;
  TYPE t_med_ingr  IS TABLE OF GTT_MYE_REPOR_PEDID.MED_INGR%TYPE;
  TYPE t_fec_digi  IS TABLE OF INT_SOLIC_CONSO_CABEC.FEC_DIGI%TYPE ;
  TYPE t_fec_fact  IS TABLE OF INT_SOLIC_CONSO_CABEC.FEC_PROG_FACT%TYPE ;


 v_oid_clie t_oid_clie;
 v_cod_clie t_cod_clie;
 v_med_ingr t_med_ingr;
 v_fec_digi t_fec_digi;
 v_fec_fact t_fec_fact;

  RegReporte GTT_MYE_REPOR_PEDID%ROWTYPE;
  lnOidPeriodo NUMBER(12);
  lsIndicadorRetencion varchar2(1);
  oidEstatus NUMBER(12);
BEGIN
      EXECUTE IMMEDIATE 'TRUNCATE TABLE GTT_MYE_REPOR_PEDID';

      lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
      OPEN cursorCliente;
                 LOOP

                  FETCH cursorCliente BULK COLLECT INTO
                                            v_cod_clie,
                                            v_oid_clie,
                                            v_med_ingr,
                                            v_fec_digi,
                                            v_fec_fact LIMIT rows;

                    IF v_oid_clie.COUNT > 0 THEN


                        FOR x IN v_cod_clie.FIRST .. v_cod_clie.LAST LOOP

                        -- SE OBTIENE LOS VALORES PARA LUEGO INSERTARLO EN EL TEMPORAL
                         RegReporte.COD_CLIE:= v_cod_clie(x);
                         RegReporte.COD_REGI:= psCodigoRegion;
                         RegReporte.COD_ZONA:= psCodigoZona;

                         --e. Monto ahorro esika
                         SELECT nvl(SUM (val_unid_dem * imp_prec_cata),0) into RegReporte.VAL_MONT_AHOR
                              FROM int_solic_conso_detal,
                                   cra_perio,
                                   pre_matri_factu_cabec,
                                   pre_ofert,
                                   pre_ofert_detal,
                                   pre_tipo_ofert
                             WHERE cod_pais=psCodigoPais
                               and cod_clie = v_cod_clie(x)
                               and cod_peri = psCodigoPeriodo
                               AND oid_peri = lnOidPeriodo
                               AND perd_oid_peri = oid_peri
                               AND mfca_oid_cabe = oid_cabe
                               AND ofer_oid_ofer = oid_ofer
                               AND tofe_oid_tipo_ofer = oid_tipo_ofer
                               AND cod_tipo_ofer = '21'
                               AND int_solic_conso_detal.cod_vent = pre_ofert_detal.val_codi_vent;

                          --F AGOTAR ESTOCK

                          SELECT NVL(SUM (val_unid_dem * imp_prec_cata),0)into RegReporte.VAL_MONT_AGOT
                          FROM int_solic_conso_detal,
                               cra_perio,
                               pre_matri_factu_cabec,
                               pre_ofert,
                               pre_ofert_detal,
                               pre_tipo_ofert
                         WHERE cod_pais=psCodigoPais
                           and cod_clie = v_cod_clie(x)
                           and cod_peri = psCodigoPeriodo
                           AND oid_peri = lnOidPeriodo
                           AND perd_oid_peri = oid_peri
                           AND mfca_oid_cabe = oid_cabe
                           AND ofer_oid_ofer = oid_ofer
                           AND tofe_oid_tipo_ofer = oid_tipo_ofer
                           AND cod_tipo_ofer = '16'
                           AND int_solic_conso_detal.cod_vent = pre_ofert_detal.val_codi_vent;

                           --G MONTO CATALOGO

                           SELECT NVL(SUM (val_unid_dem * imp_prec_cata),0) into RegReporte.VAL_MONT_CATA
                          FROM int_solic_conso_detal,
                               cra_perio,
                               pre_matri_factu_cabec,
                               pre_ofert,
                               pre_ofert_detal,
                               pre_tipo_ofert
                         WHERE cod_pais=psCodigoPais
                           and cod_peri = psCodigoPeriodo
                           and cod_clie = v_cod_clie(x)
                           AND oid_peri =lnOidPeriodo
                           AND perd_oid_peri = oid_peri
                           AND mfca_oid_cabe = oid_cabe
                           AND ofer_oid_ofer = oid_ofer
                           AND tofe_oid_tipo_ofer = oid_tipo_ofer
                           AND cod_tipo_ofer NOT IN ('16', '21')
                           AND int_solic_conso_detal.cod_vent = pre_ofert_detal.val_codi_vent;

                            --H MONTO TOTAL
                          RegReporte.VAL_MONT_TOTA:= RegReporte.VAL_MONT_AHOR + RegReporte.VAL_MONT_AGOT +  RegReporte.VAL_MONT_CATA;

                          --estatus anterior cod_esta
                          --falta

                          --J SEGUNA RETENCION
                                lsIndicadorRetencion:= MYE_FN_DEVUE_RETEN_PEDID(psCodigoPais,
                                                     psCodigoPeriodo,
                                                     psCodigoRegion,
                                                     psCodigoZona,
                                                     '2',
                                                     v_oid_clie(x));--1:valido 0:no valido

                                 if(lsIndicadorRetencion='1') then
                                    RegReporte.IND_SEGU_PEDI:='S';
                                 else
                                   RegReporte.IND_SEGU_PEDI:='N';
                                 end if;

                          --K TERCERA  RETENCION
                                lsIndicadorRetencion:= MYE_FN_DEVUE_RETEN_PEDID(psCodigoPais,
                                                     psCodigoPeriodo,
                                                     psCodigoRegion,
                                                     psCodigoZona,
                                                     '3',
                                                     v_oid_clie(x));--1:valido 0:no valido

                                 if(lsIndicadorRetencion='1') then
                                    RegReporte.IND_TERC_PEDI:='S';
                                 else
                                   RegReporte.IND_TERC_PEDI:='N';
                                 end if;

                          --K CUARTA  RETENCION
                                lsIndicadorRetencion:= MYE_FN_DEVUE_RETEN_PEDID(psCodigoPais,
                                                     psCodigoPeriodo,
                                                     psCodigoRegion,
                                                     psCodigoZona,
                                                     '4',
                                                     v_oid_clie(x));--1:valido 0:no valido

                                 if(lsIndicadorRetencion='1') then
                                    RegReporte.IND_CUAR_PEDI:='S';
                                 else
                                   RegReporte.IND_CUAR_PEDI:='N';
                                 end if;

                           -- L Posible egreso
                               BEGIN
                                  select ESTA_OID_ESTA_CLIE into oidEstatus
                                  from MAE_CLIEN_DATOS_ADICI a
                                  where a.clie_oid_clie= v_oid_clie(x);
                               EXCEPTION
                                 WHEN  OTHERS THEN
                                     oidEstatus:= null;
                               END;



                                if (oidEstatus= 4) then
                                    RegReporte.IND_POSI_EGRE:='1';
                                else
                                    RegReporte.IND_POSI_EGRE:='0';
                                end if;

                             --SALDO ACTUAL
                                SELECT NVL(sum(val_sald_deud),0) INTO RegReporte.VAL_IMPO_SALD_ACTU
                                FROM INT_SOLIC_CONSO_CABEC
                                WHERE COD_CLIE = v_cod_clie(x)
                                  AND COD_PERI= psCodigoPeriodo;


                                --Saldo coercial

                                SELECT NVL(SUM(IMP_PEND),0) INTO RegReporte.VAL_IMPO_SALD_COMI
                                FROM CCC_MOVIM_CUENT_CORRI mvcc
                                where CLIE_OID_CLIE = v_oid_clie(x);

                                -- 10. medio ingreso
                                RegReporte.MED_INGR:=v_med_ingr(x);
                                --11 fecha proceso
                                RegReporte.FEC_PROC := v_fec_digi(x);
                                -- 12 fecha facturacion
                               RegReporte.FEC_FACT := v_fec_fact(x);

                         BEGIN
                          INSERT INTO GTT_MYE_REPOR_PEDID VALUES RegReporte;
                         EXCEPTION
                          WHEN OTHERS THEN
                            --YA EXISTE REGISTRO
                            NULL;
                         END;


                        END LOOP;
                    END IF;

                 EXIT WHEN cursorCliente%NOTFOUND;
                 END LOOP;
       CLOSE cursorCliente;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MYE_PR_GENER_REPOR_PEDID_DIGIT: ' || ls_sqlerrm);
END MYE_PR_GENER_REPOR_PEDID_DIGIT;



  /***********************************************************************************
  Descripcion        : Proceso que se encarga de obtener las cxonsultoras para un period dado y que tengan
                       el numero de retencion igual al parametro ingresado DEVUELVE 1 si se encuntra en esa rtecion 0
                       si no es valido
  Fecha Creacion     : 04/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psCodigoRegion     Codigo Region
    psCodigoZona       Codigo Zona
    psNumeroRetencion  Numero Retencion

  Autor              : Sergio Buchelli
  ***************************************************************************/
  FUNCTION MYE_FN_DEVUE_RETEN_PEDID(
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psCodigoRegion     VARCHAR2,
    psCodigoZona       VARCHAR2,
    psNumeroRetencion  VARCHAR2,
    psOidCliente  NUMBER ) RETURN VARCHAR2 IS
 rows NATURAL := 1000; -- Number of rows to process at a time
 i BINARY_INTEGER := 0;
 v_row_count NUMBER := 0;

  TYPE t_cod_peri IS TABLE OF SEG_PERIO_CORPO.COD_PERI%TYPE;
  v_cod_peri t_cod_peri;

  CURSOR cursorRetencion IS
        SELECT (  SELECT A.COD_PERI
                  FROM SEG_PERIO_CORPO A, CRA_PERIO B, SEG_CANAL C, SEG_MARCA D
               WHERE A.OID_PERI = B.PERI_OID_PERI
                 AND B.OID_PERI = X.PERD_OID_PERI
                 AND B.CANA_OID_CANA = C.OID_CANA
                 AND B.MARC_OID_MARC = D.OID_MARC
                 AND C.COD_CANA = 'VD'
                 AND D.COD_MARC = 'T') periodo
        FROM PED_SOLIC_CABEC_ACUM2 X ,
             SEG_PERIO_CORPO A,
             CRA_PERIO B,
             SEG_CANAL C,
             SEG_MARCA D
       WHERE  A.OID_PERI = B.PERI_OID_PERI
               AND B.OID_PERI = X.PERD_OID_PERI
               AND B.CANA_OID_CANA = C.OID_CANA
               AND B.MARC_OID_MARC = D.OID_MARC
               AND C.COD_CANA = 'VD'
               AND D.COD_MARC = 'T'
               and x.clie_oid_clie = psOidCliente
               and A.COD_PERI <=psCodigoPeriodo
        order by 1 DESC;

  lsIsValid BOOLEAN:=FALSE;
  lsCodigoPeriodo VARCHAR2(6);
  lnCont NUMBER:=0;
  oidPais   seg_pais.OID_PAIS%TYPE;
BEGIN

        oidPais    := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        lsIsValid:=FALSE;
        OPEN cursorRetencion;
             LOOP

              FETCH cursorRetencion BULK COLLECT INTO
                                        v_cod_peri
                                         LIMIT rows;
                lnCont:=0;
                IF v_cod_peri.COUNT > 0 THEN

                    FOR y IN v_cod_peri.FIRST .. v_cod_peri.LAST LOOP
                      --obtenos el periodo a validar
                        if(lnCont=0) then
                         lsCodigoPeriodo := psCodigoPeriodo;
                        else
                          lsCodigoPeriodo := Per_Pkg_Repor_Perce.PER_FN_OBTIE_PERIO(lsCodigoPeriodo,
                                                                 oidPais,
                                                                 Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA('T'),
                                                                 Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL('VD'),
                                                                 -1);
                        end if;

                      if(lnCont <=TO_NUMBER(psNumeroRetencion)) then
                         --se valida el period que paso pedido con el obtenido anteriormente solo s eingresa si son distintos
                         -- asi mismo se ocntbiliza
                         -- el numero de periodo que paso pedido empezando de 0
                          if(v_cod_peri(y)<> lsCodigoPeriodo) then

                             if(lnCont=TO_NUMBER(psNumeroRetencion)) then
                               RETURN '1';
                             else

                               RETURN '0';
                            end if;

                          end if;
                      else
                               RETURN '0';
                      end if;
                      lnCont:=lnCont+1;

                    END LOOP;
                END IF;

             EXIT WHEN cursorRetencion%NOTFOUND;
             END LOOP;
          CLOSE cursorRetencion;


     RETURN '0';--NO ES VALIDO
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MYE_FN_DEVUE_RETEN_PEDID: ' || ls_sqlerrm);
END MYE_FN_DEVUE_RETEN_PEDID;

/***********************************************************************************
  Descripcion        : Proceso que se encarga de obtener EL ESTATUS ANTERIOR DE AL CONSULTORA
  Fecha Creacion     : 08/11/2010
  Parametros Entrada :
    psCodigoPais       Codigo Pais
    psCodigoPeriodo    Codigo Periodo
    psOidCliente       Oid Cliente

  Autor              : Sergio Buchelli
  ***************************************************************************/
  FUNCTION MYE_FN_DEVUE_ESTAT_ANTER(
    psCodigoPais       VARCHAR2,
    psCodigoPeriodo    VARCHAR2,
    psOidCliente  NUMBER ) RETURN NUMBER
  IS
     lnOidPeriodo MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;
     lnOidPeriodoAnterior MAE_CLIEN_HISTO_ESTAT.PERD_OID_PERI%TYPE;
     lnCount      NUMBER(6);

     regEstatusHistorico    MAE_CLIEN_HISTO_ESTAT%ROWTYPE;

     CURSOR cursorEstatus IS
       select ESTA_OID_ESTA_CLIE
            from (SELECT x.*
                FROM MAE_CLIEN_HISTO_ESTAT x
                WHERE x.CLIE_OID_CLIE=psOidCliente
                order by x.perd_oid_peri desc) a
            where  a.CLIE_OID_CLIE=psOidCliente
               and rownum <=2;

      cont NUMBER(6):=0;

      oidPais SEG_PAIS.OID_PAIS%TYPE;
      lnEstatus NUMBER(6);

      CURSOR cursorEstatusDistintos IS
        select ESTA_OID_ESTA_CLIE
        from (SELECT rownum,x.*
            FROM MAE_CLIEN_HISTO_ESTAT x
            WHERE x.CLIE_OID_CLIE=psOidCliente
             AND x.perd_oid_peri<=lnOidPeriodo
            order by x.perd_oid_peri desc) a
        where  a.CLIE_OID_CLIE=psOidCliente
          AND  ROWNUM <= 2;


  BEGIN
        oidPais    := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
        lnOidPeriodo:=GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);


        SELECT count(1) into lnCount
        FROM MAE_CLIEN_HISTO_ESTAT
        WHERE CLIE_OID_CLIE=psOidCliente
          order by perd_oid_peri desc;

        --se devuelve el oid_estat_clien
        if(lnCount<=1)then
          return 1;
        else

            select * INTO regEstatusHistorico
            from (SELECT x.*
                FROM MAE_CLIEN_HISTO_ESTAT x
                WHERE x.CLIE_OID_CLIE=psOidCliente
                order by x.perd_oid_peri desc) a
            where  a.CLIE_OID_CLIE=psOidCliente
               and rownum=1;

            if(lnOidPeriodo = regEstatusHistorico.PERD_OID_PERI) then
              --se devulve el del periodo anterior
                cont:=1;
                  FOR cestatus IN cursorEstatus LOOP
                    if(cont=2) then
                         return cestatus.ESTA_OID_ESTA_CLIE;
                    end if;
                    cont:=cont+1;
                  END LOOP;
            else
                cont:=1;
                  FOR cestatusDistintos IN cursorEstatusDistintos LOOP
                    if(cont=2) then
                         return cestatusDistintos.ESTA_OID_ESTA_CLIE;
                    end if;
                    cont:=cont+1;
                  END LOOP;


            end if;


        end if;

        RETURN 1;
  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MYE_FN_DEVUE_ESTAT_ANTER: ' || ls_sqlerrm);
  END;

end MYE_PKG_REPOR;
/

