CREATE OR REPLACE PACKAGE MIC_PKG_PROCE_MICRO IS
/* Declaracion de variables */
ln_sqlcode NUMBER(10);
ls_sqlerrm VARCHAR2(1500);
W_FILAS NUMBER:=1000;

/***************************************************************************
Descripcion :
     Genra las aptas para microseguros
Fecha Creacion : 11/05/2010
Autor : Sergio Buchelli
Parametros:
 psCodigoPais :Codigo Pais
 psFechaProceso :Fecha Proceso
 psUsuario:Usuario

***************************************************************************/
PROCEDURE MIC_PR_GENER_APTAS_MICRO
 (psCodigoPais VARCHAR2,
  psFechaProceso VARCHAR2,
  psUsuario VARCHAR2);


/**************************************************************************
Descripcion        : Valida las clasificaciones del cliente en las clasificaciones del GRUPO DETALLE
Fecha Creacion     :  11/05/2010
Parametros Entrada :
            psNumDetalle  :numDetalle
           psOidCliente :oidCliente

Autor              : Sergio Buchelli
***************************************************************************/
FUNCTION MIC_FN_VALID_CLASI_GRUPO(psNumDetalle        NUMBER,
                                  pnOidCliente         NUMBER)
  RETURN VARCHAR2;

/**************************************************************************
Descripcion        : Valida si ya se debe enviar los pagos de microseguros
Fecha Creacion     :  13/05/2010
Parametros Entrada :
           psTipoOperacion : Tipo Operacion 01:IPM 02:ASEG
Autor              : Sergio Buchelli
***************************************************************************/
FUNCTION MIC_FN_VALID_ENVIO_MICRO(psTipoOperacion VARCHAR2)
  RETURN NUMBER;

/**************************************************************************
Descripcion        : Valida si ya existe una fecha de inico para el mes y anho ingresado
                    1: existe 0: no existe
Fecha Creacion     :  13/05/2010
Parametros Entrada :
           psCodigoPais :      Codigo Pais
           psCodigoCronograma : Codigo Cronograma
           psFechaInicio      :  Fecha inicio
           psTipoOperacion    :  Tipo Operacion
Autor              : Sergio Buchelli
***************************************************************************/
FUNCTION MIC_FN_VALID_FECHA_INICI_CRONO(psCodigoPais       VARCHAR2,
                                        psCodigoCronograma VARCHAR2,
                                        psFechaInicio VARCHAR2,
                                        psTipoOperacion VARCHAR2)
  RETURN NUMBER;

END MIC_PKG_PROCE_MICRO;
/

CREATE OR REPLACE PACKAGE BODY MIC_PKG_PROCE_MICRO IS

 /***************************************************************************
  Descripcion : Proceso que carga de generar las aptas de microseguros
  Fecha Creacion : 11/05/2010
  Autor : Sergio Buchelli
  Parametros:
   psCodigoPais : Codigo de Pais
   psFechaProceso : Fecha Proceso
   psUsuario : usuario

  ***************************************************************************/
  PROCEDURE MIC_PR_GENER_APTAS_MICRO(psCodigoPais   VARCHAR2,
                                     psFechaProceso VARCHAR2,
                                     psUsuario      VARCHAR2) IS
   lnExisteGrupo NUMBER;

    CURSOR c_Lideres IS
     SELECT sec.OID_SECC,
     (SELECT R.COD_CLIE FROM MAE_CLIEN R WHERE R.OID_CLIE =sec.CLIE_OID_CLIE) COD_CLIE,
     sec.CLIE_OID_CLIE,
     sub.COD_SUBG_VENT || reg.COD_REGI || zon.COD_ZONA || sec.COD_SECC
     FROM ZON_SECCI sec, ZON_ZONA zon, ZON_REGIO reg, ZON_SUB_GEREN_VENTA sub
     WHERE sec.ZZON_OID_ZONA = zon.OID_ZONA
     AND zon.ZORG_OID_REGI = reg.OID_REGI
     AND reg.ZSGV_OID_SUBG_VENT = sub.OID_SUBG_VENT
     AND sec.IND_ACTI = 1
     AND sec.IND_BORR = 0
     AND sec.CLIE_OID_CLIE IS NOT NULL;

     TYPE interfazLider IS RECORD
         (
         oidSeccion ZON_SECCI.OID_SECC%TYPE,
         codCliente MAE_CLIEN.COD_CLIE%TYPE,
         oidCliente MAE_CLIEN.OID_CLIE%TYPE,
         ua VARCHAR2(9)
         );

   CURSOR c_grupoDetal IS
     SELECT A.NUM_DETA, A.COD_NIVE,A.COD_GRUP
     FROM MIC_GRUPO_CLIEN_DETAL A
     WHERE A.COD_GRUP IN(
          SELECT  X.COD_GRUP
          FROM MIC_GRUPO_CLIEN_CABEC X
          WHERE X.COD_GRUP = A.COD_GRUP
          AND X.IND_ACTI ='1'  );


    CURSOR c_cobertura IS
        SELECT Y.COD_MICR,Y.COD_COBE
          FROM MIC_MICRO X, MIC_COBER Y
          WHERE X.IND_ACTI =1
           AND X.COD_MICR = Y.COD_MICR
           AND Y.IND_ACTI=1;


    TYPE recCobertura IS RECORD
         (
         codigoMicro     MIC_COBER.COD_MICR%TYPE,
         codigoCobertura MIC_COBER.COD_COBE%TYPE
         );

    TYPE recCoberturaTab IS TABLE OF recCobertura;
    coberturaRecord recCoberturaTab;

   regParam MIC_PARAM%ROWTYPE;

   TYPE interfazLiderTab IS TABLE OF interfazLider;
   interfazRecordN interfazLiderTab;

   lsCodigoConsultora MAE_CLIEN.COD_CLIE%TYPE;
   isValido BOOLEAN;
   isValidoPedido BOOLEAN;
   lsCodigoNivel COM_HISTO_VARIA_EJETR_CABEC.COD_NIVE%TYPE;
   lsCumpleTipologia VARCHAR2(1);

   lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
   lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
   lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
   lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;
   lsPeriodoActual             SEG_PERIO_CORPO.COD_PERI%TYPE;
   lsCodigoPeriodoIni          SEG_PERIO_CORPO.COD_PERI%TYPE;
   ldFecha DATE;
   lsFecha VARCHAR2(10);
   lnTotalPedidos NUMBER;
   lnContRecord   NUMBER;
   lsDocuIdent    VARCHAR2(30);
   lsCodigoGrupo  MIC_GRUPO_CLIEN_DETAL.COD_GRUP%TYPE;
  BEGIN

      lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
      lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
      lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

    /* Obteniendo periodos */
    ldFecha := SYSDATE;
    lsFecha := TO_CHAR(SYSDATE,'DD/MM/YYYY');
    ldFecha := TO_DATE(lsFecha,'DD/MM/YYYY');

    SELECT MAX(OID_PERI) INTO lnOidPeriodo
    FROM CRA_PERIO A
    WHERE ldFecha >=A.FEC_INIC
    AND ldFecha <= A.FEC_FINA;

    SELECT A.COD_PERI INTO lsPeriodoActual
     FROM SEG_PERIO_CORPO A,
     CRA_PERIO B,
     SEG_CANAL C,
     SEG_MARCA D
     WHERE A.OID_PERI = B.PERI_OID_PERI
     AND B.OID_PERI=lnOidPeriodo
     AND B.CANA_OID_CANA = C.OID_CANA
     AND B.MARC_OID_MARC = D.OID_MARC
     AND C.COD_CANA = 'VD'
     AND D.COD_MARC = 'T';


     SELECT X.*
     INTO regParam
     FROM MIC_PARAM X ;


     SELECT COUNT(1) into lnExisteGrupo
     FROM MIC_GRUPO_CLIEN_CABEC
     WHERE IND_ACTI='1';


     --actualiza a todos los clientes en microseguros como "No Aptos".
     UPDATE MIC_CLIEN_APTAS Z
     SET Z.IND_APTA ='0',
      Z.FEC_ULTI_ACTU =SYSDATE;


    IF(lnExisteGrupo > 0) THEN

     --Seguidamente para cada cliente recuperado se valida

       OPEN c_Lideres;
             LOOP
               FETCH c_Lideres BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
                     IF interfazRecordN.COUNT > 0 THEN

                         FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

                            --Si Pertenece a alguna clasificación detalle del Grupo de Clientes de Microseguros
                            lsCodigoConsultora := interfazRecordN(x).codCliente;
                            lsCodigoGrupo:= '';
                            isValido:=FALSE;
                            FOR cGrupoDetal IN c_grupoDetal LOOP

                                IF(cGrupoDetal.COD_NIVE IS NOT NULL AND
                                    length(cGrupoDetal.COD_NIVE) >0) THEN

                                    --se recupera de cada cliente su respectivo nivel
                                   BEGIN
                                    SELECT T.COD_NIVE INTO lsCodigoNivel
                                    FROM (SELECT Z.*
                                          FROM COM_HISTO_VARIA_EJETR_CABEC Z
                                          WHERE Z.COD_EJEC =lsCodigoConsultora
                                          ORDER BY NUM_ANIO_INIC DESC, COD_RANG DESC) T
                                    WHERE ROWNUM =1;
                                   EXCEPTION
                                    WHEN OTHERS THEN
                                      lsCodigoNivel:='';
                                   END;

                                    IF(cGrupoDetal.COD_NIVE = lsCodigoNivel )THEN
                                     isValido:=TRUE;
                                     lsCodigoGrupo:= cGrupoDetal.COD_GRUP;
                                     EXIT WHEN  isValido;
                                    END IF;


                                ELSE
                                   --SE VERIFICA el cliente cumple con la tipología del grupo detalle
                                    lsCumpleTipologia:=MIC_FN_VALID_CLASI_GRUPO(cGrupoDetal.NUM_DETA,interfazRecordN(x).oidCliente);
                                    IF(lsCumpleTipologia='1')THEN
                                     isValido:=TRUE;
                                     lsCodigoGrupo:= cGrupoDetal.COD_GRUP;
                                     EXIT WHEN  isValido;
                                    END IF;

                                 END IF;


                            END LOOP;
                            --
                            IF(isValido)THEN
                              --Para cada cliente marcado como válido,
                              -- se verifica si ha pasado por lo mensos un pedido en las traes ultimas campañas
                                isValidoPedido:=TRUE;
                                 IF( regParam.NUM_CAMP_PEDI IS NOT NULL AND regParam.NUM_CAMP_PEDI > 0) THEN

                                  IF(regParam.NUM_CAMP_PEDI = 1) THEN
                                      lsCodigoPeriodoIni := lsPeriodoActual;

                                    ELSE
                                      lsCodigoPeriodoIni := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsPeriodoActual,
                                                           lnOidPais, lnOidMarca, lnOidCanal, (-1)*(regParam.NUM_CAMP_PEDI-1));

                                  END IF;

                                    --si ha hecho peiddo
                                     SELECT COUNT(1)
                                      INTO lnTotalPedidos
                                      FROM PED_SOLIC_CABEC psc,
                                           PED_TIPO_SOLIC_PAIS tsp,
                                           (SELECT * FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC') ts,
                                           CRA_PERIO cra,
                                           SEG_PERIO_CORPO cor
                                     WHERE psc.PAIS_OID_PAIS = lnOidPais
                                       AND psc.PERD_OID_PERI = cra.oid_peri
                                       AND cra.peri_oid_peri = cor.oid_peri
                                       AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
                                       AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
                                       AND psc.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                                       AND psc.GRPR_OID_GRUP_PROC = 5
                                       AND psc.FEC_FACT IS NOT NULL
                                       AND cor.cod_peri >= lsCodigoPeriodoIni
                                       AND cor.cod_peri <= lsPeriodoActual;

                                    IF(lnTotalPedidos=0)THEN
                                      isValidoPedido:=FALSE;
                                    END IF;

                                END IF;--FIN SI HAY PARAMETRO NUM CAMPANHA

                                --si sigue siendo vaalido
                               IF( isValidoPedido)THEN
                                    --Se verifica si el cliente válido
                                    --existe en la tabla de aptas
                                    --por cada código de producto y cobertura recuperado
                                       OPEN c_cobertura;
                                             LOOP
                                               FETCH c_cobertura BULK COLLECT INTO coberturaRecord LIMIT W_FILAS;
                                                     IF coberturaRecord.COUNT > 0 THEN
                                                         FOR y IN coberturaRecord.FIRST .. coberturaRecord.LAST LOOP

                                                              SELECT COUNT(1) INTO lnContRecord
                                                              FROM MIC_CLIEN_APTAS X
                                                              WHERE  X.COD_CLIE = interfazRecordN(x).codCliente
                                                                AND X.COD_MICR = coberturaRecord(y).codigoMicro
                                                                AND X.COD_COBE = coberturaRecord(y).codigoCobertura
                                                                AND X.COD_GRUP = lsCodigoGrupo;

                                                                -- RECUPERAMOS DNI SI EXISTE SINO SE RECUPERA DOCUMENTO PRINCIPAL
                                                               BEGIN

                                                                 SELECT NUM_DOCU_IDEN INTO lsDocuIdent
                                                                 FROM MAE_CLIEN_IDENT ,MAE_TIPO_DOCUM
                                                                 WHERE CLIE_OID_CLIE=interfazRecordN(x).oidCliente
                                                                    AND COD_TIPO_DOCU ='01' --DNI
                                                                    AND TDOC_OID_TIPO_DOCU=OID_TIPO_DOCU;

                                                               EXCEPTION
                                                                WHEN OTHERS THEN
                                                                 SELECT NUM_DOCU_IDEN INTO lsDocuIdent
                                                                 FROM MAE_CLIEN_IDENT
                                                                 WHERE CLIE_OID_CLIE=interfazRecordN(x).oidCliente
                                                                    AND VAL_IDEN_DOCU_PRIN=1;
                                                               END;

                                                             IF(lnContRecord=0)THEN

                                                                --INSERTAMOS     NOM_CLIE
                                                                INSERT INTO MIC_CLIEN_APTAS (
                                                                   COD_CLIE,
                                                                   VAL_NOMB_COMP,
                                                                   COD_TIPO_DOCU,
                                                                   NUM_DOCU,
                                                                   IND_CARG_AUTO, IND_APTA,
                                                                   FEC_CREA,
                                                                   FEC_ULTI_ACTU,
                                                                   COD_MICR,
                                                                   COD_COBE,
                                                                   COD_GRUP,
                                                                   VAL_MONT,
                                                                   FEC_ENVI, FEC_VENC, NUM_POLI,
                                                                   IND_ERRO)
                                                                VALUES (interfazRecordN(x).codCliente,
                                                                   GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(interfazRecordN(x).codCliente,'NOM_CLIE'),
                                                                    (SELECT TD.COD_TIPO_DOCU
                                                                        FROM MAE_TIPO_DOCUM TD, MAE_CLIEN_IDENT IDT
                                                                        WHERE TD.OID_TIPO_DOCU =IDT.TDOC_OID_TIPO_DOCU
                                                                         AND IDT.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                                                                         AND IDT.NUM_DOCU_IDEN=lsDocuIdent),
                                                                    lsDocuIdent,-- (SELECT NUM_DOCU_IDEN FROM MAE_CLIEN_IDENT WHERE CLIE_OID_CLIE=interfazRecordN(x).oidCliente AND VAL_IDEN_DOCU_PRIN=1),
                                                                    0,
                                                                    1,
                                                                    SYSDATE,
                                                                    NULL,
                                                                    coberturaRecord(y).codigoMicro,
                                                                    coberturaRecord(y).codigoCobertura,
                                                                    lsCodigoGrupo,
                                                                    (SELECT B.VAL_PREC
                                                                     FROM MIC_COBER_GRUPO_CLIEN B
                                                                     WHERE  B.COD_MICR =  coberturaRecord(y).codigoMicro
                                                                       AND B.COD_COBE = coberturaRecord(y).codigoCobertura
                                                                       AND B.COD_GRUP =lsCodigoGrupo),
                                                                    NULL,
                                                                    (SELECT MAX(FEC_FINA)
                                                                      FROM MIC_CRONO
                                                                        WHERE
                                                                           COD_PAIS =psCodigoPais
                                                                           AND TO_CHAR(SYSDATE,'MM') = TO_CHAR(FEC_FINA,'MM')
                                                                           AND TIP_OPER='01'),
                                                                    '',0);

                                                              ELSE
                                                                --ACTUALIZAMOS
                                                                UPDATE MIC_CLIEN_APTAS
                                                                SET    IND_APTA      = '1',
                                                                       FEC_ULTI_ACTU = SYSDATE,
                                                                       VAL_MONT      = (SELECT A.VAL_PREC
                                                                                        FROM MIC_COBER_GRUPO_CLIEN A
                                                                                        WHERE  A.COD_MICR =  coberturaRecord(y).codigoMicro
                                                                                        AND A.COD_COBE = coberturaRecord(y).codigoCobertura
                                                                                        AND A.COD_GRUP =lsCodigoGrupo),
                                                                       FEC_VENC      = (SELECT MAX(FEC_FINA)
                                                                                        FROM MIC_CRONO
                                                                                        WHERE
                                                                                         COD_PAIS =psCodigoPais
                                                                                         AND TO_CHAR(SYSDATE,'MM') = TO_CHAR(FEC_FINA,'MM')
                                                                                         AND TIP_OPER='01'),
                                                                       NUM_POLI      = '',
                                                                       NUM_DOCU      = lsDocuIdent,
                                                                       COD_TIPO_DOCU = (SELECT TD.COD_TIPO_DOCU
                                                                                        FROM MAE_TIPO_DOCUM TD, MAE_CLIEN_IDENT IDT
                                                                                        WHERE TD.OID_TIPO_DOCU =IDT.TDOC_OID_TIPO_DOCU
                                                                                        AND IDT.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                                                                                        AND IDT.NUM_DOCU_IDEN=lsDocuIdent)
                                                                WHERE  COD_CLIE      =  interfazRecordN(x).codCliente
                                                                AND    COD_MICR      =  coberturaRecord(y).codigoMicro
                                                                AND    COD_COBE      =  coberturaRecord(y).codigoCobertura
                                                                AND    COD_GRUP      = lsCodigoGrupo;
                                                             END IF;

                                                         END LOOP;

                                                      END IF;
                                                EXIT WHEN c_cobertura%NOTFOUND;
                                             END LOOP;
                                       CLOSE c_cobertura;



                               END IF;

                            END IF;

                         END LOOP;

                     END IF;
                EXIT WHEN c_Lideres%NOTFOUND;
             END LOOP;
       CLOSE c_Lideres;



    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR MIC_PR_GENER_APTAS_MICRO: ' ||
                              ls_sqlerrm);
  END MIC_PR_GENER_APTAS_MICRO;



/**************************************************************************
Descripcion        : Valida las clasificaciones del cliente en las clasificaciones del GRUPO DETALLE
Fecha Creacion     :  11/05/2010
Parametros Entrada :
            psNumDetalle  :numDetalle
           psOidCliente :oidCliente

Autor              : Sergio Buchelli
***************************************************************************/
FUNCTION MIC_FN_VALID_CLASI_GRUPO(psNumDetalle         NUMBER,
                                  pnOidCliente         NUMBER)
  RETURN VARCHAR2 IS

   CURSOR cursorClasifCliente(lnOidClasificacion     NUMBER,
                              lnOidTipoClasificacion NUMBER,
                              lnOidTipoCliente       NUMBER,
                              lnOidSubTipoCliente    NUMBER)
   IS
     SELECT COUNT(1) AS lnCont
     FROM MAE_CLIEN_CLASI X,MAE_CLIEN_TIPO_SUBTI Y
      WHERE X.CTSU_OID_CLIE_TIPO_SUBT = Y.OID_CLIE_TIPO_SUBT
          AND Y.CLIE_OID_CLIE =pnOidCliente
          AND Y.IND_PPAL='1'
          AND (lnOidClasificacion IS NULL OR X.CLAS_OID_CLAS = lnOidClasificacion)
          AND (lnOidTipoClasificacion IS NULL OR X.TCCL_OID_TIPO_CLASI = lnOidTipoClasificacion)
          AND (lnOidTipoCliente IS NULL OR Y.TICL_OID_TIPO_CLIE = lnOidTipoCliente)
          AND (lnOidSubTipoCliente IS NULL OR Y.SBTI_OID_SUBT_CLIE = lnOidSubTipoCliente);

   CURSOR cursorGrupoDetalle
   IS
      SELECT C.*
      FROM MIC_GRUPO_CLIEN_DETAL C
      WHERE C.NUM_DETA=psNumDetalle;

  lnOidClasificacion       NUMBER;
  lsResult                 VARCHAR2(1);
BEGIN

  lsResult := '1';
  FOR cGrupoDetalle IN cursorGrupoDetalle LOOP
    lsResult := '0';
    --recorremos las clasificaciones del concurso para encontralas en las
    --clasificaciones del cliente la validacion puede ser viceversa
    FOR cClasifCliente IN cursorClasifCliente(cGrupoDetalle.CLAS_OID_CLAS,
                                              cGrupoDetalle.TCCL_OID_TIPO_CLAS,
                                              cGrupoDetalle.TICL_OID_TIPO_CLIE,
                                              cGrupoDetalle.SBTI_OID_SUBT_CLIE ) LOOP
      IF(cClasifCliente.lnCont > 0) THEN
        RETURN '1';
      END IF;
    END LOOP;

  END LOOP;

  RETURN lsResult;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MIC_FN_VALID_CLASI_GRUPO: ' || ls_sqlerrm);

END MIC_FN_VALID_CLASI_GRUPO;


/**************************************************************************
Descripcion        : Valida si ya se debe enviar los pagos de microseguros
Fecha Creacion     :  13/05/2010
Parametros Entrada :
           psTipoOperacion : Tipo Operacion 01:IPM 02:ASEG
Autor              : Sergio Buchelli
***************************************************************************/
FUNCTION MIC_FN_VALID_ENVIO_MICRO(psTipoOperacion VARCHAR2)
  RETURN NUMBER IS
  lnResult NUMBER:=0;
  regParam      MIC_PARAM%ROWTYPE;
  ldFechaInicio MIC_CRONO.FEC_INIC%TYPE;
  ldFecha       MIC_CRONO.FEC_INIC%TYPE;
  ldFechaFin    MIC_CRONO.FEC_FINA%TYPE;
  --lsHoraProgramada MIC_PARAM.HOR_ENVI%TYPE;
BEGIN
   lnResult:=0;
   SELECT X.*
   INTO regParam
   FROM MIC_PARAM X;

   IF(psTipoOperacion='01') THEN --ENVIO IPM
      BEGIN
       SELECT X.FEC_INIC into ldFechaInicio
       FROM MIC_CRONO X
       WHERE
         X.COD_PAIS = regParam.COD_PAIS
        AND TO_CHAR(X.FEC_INIC,'MM') = TO_CHAR(SYSDATE,'MM')
        AND TO_CHAR(X.FEC_INIC,'YYYY') = TO_CHAR(SYSDATE,'YYYY')
        AND X.TIP_OPER = psTipoOperacion
        AND X.IND_ACTI='1';
      EXCEPTION
       WHEN OTHERS THEN
           ldFechaInicio:=NULL;
      END;

       IF(ldFechaInicio IS NOT NULL) THEN

        ldFecha:= TRUNC(SYSDATE) - regParam.VAL_DIAS;--el val dias viene negativo
        IF(ldFechaInicio = ldFecha) THEN
          lnResult:=1;
        END IF;

      END IF;

   ELSE --ENVIO ASEG

         BEGIN
           SELECT X.FEC_INIC, X.FEC_FINA
           into ldFechaInicio,ldFechaFin
           FROM MIC_CRONO X
           WHERE
             X.COD_PAIS = regParam.COD_PAIS
            AND TO_CHAR(X.FEC_INIC,'MM') = TO_CHAR(SYSDATE,'MM')
            AND TO_CHAR(X.FEC_INIC,'YYYY') = TO_CHAR(SYSDATE,'YYYY')
            AND X.TIP_OPER = psTipoOperacion
            AND X.IND_ACTI='1';
          EXCEPTION
           WHEN OTHERS THEN
               ldFechaInicio:=NULL;
          END;

           IF(ldFechaInicio IS NOT NULL) THEN

            ldFecha:= TRUNC(SYSDATE) ;
            IF(ldFecha>=ldFechaInicio AND ldFecha<=ldFechaFin) THEN
              lnResult:=1;
            END IF;

          END IF;


   END IF;

 RETURN lnResult;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MIC_FN_VALID_ENVIO_MICRO: ' || ls_sqlerrm);
END MIC_FN_VALID_ENVIO_MICRO;


/**************************************************************************
Descripcion        : Valida si ya existe una fecha de inico para el mes y anho ingresado
                    1: existe 0: no existe
Fecha Creacion     :  13/05/2010
Parametros Entrada :
           psCodigoPais :      Codigo Pais
           psCodigoCronograma : Codigo Cronograma
           psFechaInicio      :  Fecha inicio
           psTipoOperacion    :  Tipo Operacion
Autor              : Sergio Buchelli
***************************************************************************/
FUNCTION MIC_FN_VALID_FECHA_INICI_CRONO(psCodigoPais       VARCHAR2,
                                        psCodigoCronograma VARCHAR2,
                                        psFechaInicio VARCHAR2,
                                        psTipoOperacion VARCHAR2)
RETURN NUMBER IS
  lnResult NUMBER:=0;
  lnCont NUMBER;
  ldFechaInicio DATE;
BEGIN
   lnResult:=0;
   ldFechaInicio:=TO_DATE(psFechaInicio,'dd/MM/yyyy');
   IF(psCodigoCronograma IS NULL OR LENGTH(psCodigoCronograma)=0) THEN
   --SE TRATA DE UN INSERT

        SELECT COUNT(1) INTO lnCont
        FROM MIC_CRONO X
        WHERE COD_PAIS= psCodigoPais
         AND TIP_OPER= psTipoOperacion
         AND TO_CHAR(FEC_INIC,'YYYY')= TO_CHAR(ldFechaInicio,'YYYY')
         AND TO_CHAR(FEC_INIC,'MM')= TO_CHAR(ldFechaInicio,'MM');

        IF(lnCont>0) THEN
          lnResult:= 1;
        ELSE
          lnResult:=0;
        END IF;

   ELSE
   --SE TRAT D EUN UPDATE
       SELECT COUNT(1) INTO lnCont
        FROM MIC_CRONO X
        WHERE COD_PAIS= psCodigoPais
        AND TIP_OPER= psTipoOperacion
         AND COD_CRON <> psCodigoCronograma
         AND TO_CHAR(FEC_INIC,'YYYY')= TO_CHAR(ldFechaInicio,'YYYY')
         AND TO_CHAR(FEC_INIC,'MM')= TO_CHAR(ldFechaInicio,'MM');

        IF(lnCont>0) THEN
          lnResult:= 1;
        ELSE
          lnResult:=0;
        END IF;


   END IF;

 RETURN lnResult;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MIC_FN_VALID_FECHA_INICI_CRONO: ' || ls_sqlerrm);
END MIC_FN_VALID_FECHA_INICI_CRONO;


END MIC_PKG_PROCE_MICRO;
/

