CREATE OR REPLACE PACKAGE "EVI_PKG_EJECU_VIRTU" IS

  /* Declaracion de Variables */
  LN_SQLCODE NUMBER(10);
  LS_SQLERRM VARCHAR2(150);
  W_FILAS    NUMBER := 5000 ;

  PROCEDURE EVI_PR_INSER_REGIS_ARCHI_MICA(PS_COD_PAIS  IN VARCHAR2,
                                          PS_COD_PERI  IN VARCHAR2,
                                          PS_COD_CLIE  IN VARCHAR2,
                                          PS_TIP_ORDE  IN VARCHAR2,
                                          PS_FEC_SOLIC IN DATE);

  PROCEDURE EVI_PR_CARGA_RESUM_PRE_FACTU;

  PROCEDURE EVI_PR_CARGA_PRIME_SOLIC_CABEC;

  PROCEDURE EVI_PR_CARGA_PEDID_ZONA_FECHA;

  PROCEDURE EVI_PR_CARGA_PEDID_ZONA_PERIO;

  PROCEDURE EVI_PR_CARGA_PEDID_REGIO_FECHA;

  PROCEDURE EVI_PR_CARGA_PEDID_REGIO_PERIO;

  PROCEDURE EVI_PR_CARGA_FACTU(PS_COD_PERI       IN VARCHAR2,
                               PS_COD_PERI_CRUCE IN VARCHAR2,
                               PS_COD_PAIS       IN VARCHAR2,
                               PS_COD_MARCA      IN VARCHAR2,
                               PS_COD_CANAL      IN VARCHAR2);

  PROCEDURE EVI_PR_CARGA_FACTU_CABEC(PS_COD_PERI       IN VARCHAR2,
                                     PS_COD_PERI_CRUCE IN VARCHAR2,
                                     PS_COD_PAIS       IN VARCHAR2,
                                     PS_COD_MARCA      IN VARCHAR2,
                                     PS_COD_CANAL      IN VARCHAR2);

  PROCEDURE EVI_PR_CARGA_FACTU_DETAL(PS_COD_PERI       IN VARCHAR2,
                                     PS_COD_PERI_CRUCE IN VARCHAR2,
                                     PS_COD_PAIS       IN VARCHAR2,
                                     PS_COD_MARCA      IN VARCHAR2,
                                     PS_COD_CANAL      IN VARCHAR2);

  FUNCTION EVI_FN_OBTEN_NUMER_PEDID_ESTIM(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2)
    RETURN NUMBER;

  FUNCTION EVI_FN_OBTEN_NUMER_PEDID_REAL(PS_COD_PERI IN VARCHAR2,
                                         PS_COD_REGI IN VARCHAR2,
                                         PS_COD_ZONA IN VARCHAR2,
                                         PS_FECHA    IN DATE,
                                         PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER;

  FUNCTION EVI_FN_OBTEN_NUMER_PEDID_REC(PS_COD_PERI IN VARCHAR2,
                                        PS_COD_REGI IN VARCHAR2,
                                        PS_COD_ZONA IN VARCHAR2,
                                        PS_FECHA    IN DATE,
                                        PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER;

  FUNCTION EVI_FN_OBTEN_NUMER_PEDID_RECHA(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2,
                                          PS_FECHA    IN DATE,
                                          PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER;

  FUNCTION EVI_FN_OBTEN_NUMER_PRIME_PEDID(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2,
                                          PS_FECHA    IN DATE,
                                          PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER;

  FUNCTION EVI_FN_OBTEN_TOTAL_FACTU_REGUL(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2,
                                          PS_FECHA    IN DATE,
                                          PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER;

  FUNCTION EVI_FN_OBTEN_TOTAL_FACTU_BOLSA(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2,
                                          PS_FECHA    IN DATE,
                                          PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER;

  FUNCTION EVI_FN_CALCU_VALOR_SALDO_DEUD2(PS_COD_CLIE IN VARCHAR2)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Anexo 3: Carga la tabla EVI_PR_CARGA_PEDID_ZONA_TERRI
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_PEDID_ZONA_TERRI;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Historico de Consultoras
  Fecha Creacion    : 14/01/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE EVI_PR_INT_HISTO_CONSU(PSCODIGOPAIS     VARCHAR2,
                                   PSCODIGOSISTEMA  VARCHAR2,
                                   PSCODIGOINTERFAZ VARCHAR2,
                                   PSNOMBREARCHIVO  VARCHAR2,
                                   PSCODIGOISO      VARCHAR2,
                                   PSCODIGOMARCA    VARCHAR2,
                                   PSCODIGOCANAL    VARCHAR2);

  /**************************************************************************
  Descripcion       : Devuelve el indicador de Dupla Cyzone
                      S -> Tiene Dupla
                      N -> No tiene Dubla
  Fecha Creacion    : 14/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_DUPLA(PNCODIGOCLIENTE IN NUMBER) RETURN VARCHAR;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Resultados de Cobranza
  Fecha Creacion    : 15/01/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE EVI_PR_INT_RESUL_COBRA(PSCODIGOPAIS     VARCHAR2,
                                   PSCODIGOSISTEMA  VARCHAR2,
                                   PSCODIGOINTERFAZ VARCHAR2,
                                   PSNOMBREARCHIVO  VARCHAR2,
                                   PSCODIGOMARCA    VARCHAR2,
                                   PSCODIGOCANAL    VARCHAR2);

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Resultados de Facturacion
  Fecha Creacion    : 15/01/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE EVI_PR_INT_RESUL_FACT(PSCODIGOPAIS     VARCHAR2,
                                  PSCODIGOSISTEMA  VARCHAR2,
                                  PSCODIGOINTERFAZ VARCHAR2,
                                  PSNOMBREARCHIVO  VARCHAR2,
                                  PSCODIGOMARCA    VARCHAR2,
                                  PSCODIGOCANAL    VARCHAR2);

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Saldos de Consultora
  Fecha Creacion    : 15/01/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE EVI_PR_INT_SALDO_CONSU(PSCODIGOPAIS     VARCHAR2,
                                   PSCODIGOSISTEMA  VARCHAR2,
                                   PSCODIGOINTERFAZ VARCHAR2,
                                   PSNOMBREARCHIVO  VARCHAR2,
                                   PSCODIGOMARCA    VARCHAR2,
                                   PSCODIGOCANAL    VARCHAR2);

  /**************************************************************************
  Descripcion       : Devuelve el monto Faltante para una zona y periodo
  Fecha Creacion    : 14/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_MONTO_FALTA_ZONA(LNOIDPAIS    IN NUMBER,
                                         LNOIDPERIODO IN NUMBER,
                                         LNOIDZONA    IN NUMBER)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Devuelve el periodo de facturacion en el caso de que
                      exista cruce en el periodo.
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_PERIO_FACTU(PSCODIGOPERIODO      IN VARCHAR2,
                                    PSCODIGOPERIODOCRUCE IN VARCHAR2,
                                    PSCODIGOZONA         IN VARCHAR2,
                                    PNOIDMARCA           IN NUMBER,
                                    PNOIDCANAL           IN NUMBER)
    RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve el periodo de cobranza en el caso de que
                      exista cruce en el periodo.
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_PERIO_COBRA(PSCODIGOPERIODO      IN VARCHAR2,
                                    PSCODIGOPERIODOCRUCE IN VARCHAR2,
                                    PSCODIGOZONA         IN VARCHAR2)
    RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Indica si la zona se encuentra en la tabla com_zona_deman_antic
                      'S' se enecuentra
                      'N' no se encuentra
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_EXIST_ZONA_DEMAN(PSCODIGOZONA IN VARCHAR2) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Indica si el registro de cuenta coorriente es valida
                      para el periodo
                      'S' es valida
                      'N' no es valida
  Fecha Creacion    : 17/01/2008

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_INDIC_CCVAL(PSFECHACRA IN DATE,
                                    PSFECHACC  IN DATE,
                                    PSTIPOVAL  IN VARCHAR2) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Obtienene el documento  de identidad del cliente
                      con indicador de documento principal=1
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_DOCUM_IDENT(PNIDCLIENTE NUMBER) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Obtiene el monto de cobranza a 31 dias
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_COBRA_31DIA(PNNUMEROHISTORICO    NUMBER,
                                    PNOIDULTIMOPROCESO   NUMBER,
                                    PDFECHAVENCIMIENTO   DATE,
                                    PDFECHADOCUMENTACION DATE,
                                    PSCODIGOZONA         VARCHAR2,
                                    PNIMPORTEPAGO        NUMBER,
                                    PNTASA               NUMBER,
                                    PNIDMOVIMIENTO       NUMBER)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Obtiene el id de subproceso de cuenta corriente
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_ID_SUBPR_CC(PSCODIGOPROCESO    VARCHAR2,
                                    PSCODIGOSUBPROCESO VARCHAR2)
    RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Obtiene el monto de reclamos para un registro de cuenta corriente
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_RECLA_CC(PNNUMEROHISTORICO   NUMBER,
                                 PNOIDULTIMOPROCESO  NUMBER,
                                 PNIMPORTEMOVIMIENTO NUMBER,
                                 PNTASA              NUMBER,
                                 PNIDMOVIMIENTO      NUMBER)

   RETURN NUMBER;

  /**************************************************************************
   Descripcion       : Debuel el Id de Periodo N siguiente
   Fecha Creacion    : 17/01/2008

   Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_ID_PERIO_NSGTE(PSCODIGOPERIODO VARCHAR2,
                                       PNIDPAIS        NUMBER,
                                       PNIDMARCA       NUMBER,
                                       PNIDCANAL       NUMBER,
                                       PNNSIGUIENTE    NUMBER) RETURN NUMBER;

  /**************************************************************************
   Descripcion       : Devuelve el Monto de Actividad
   Fecha Creacion    : 17/01/2008

   Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_ACTIV(PNNUMACTIVAS NUMBER, PNNUMPEDIDOS NUMBER)
    RETURN NUMBER;


/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Cabecereas de Pedidos Digitados
Fecha Creacion    : 03/12/2007
Autor             : José A. Cairampoma
***************************************************************************/
PROCEDURE INT_PR_EVI_ENVIA_PEDIG_CABEC(psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCodigoPeriodo VARCHAR2
);

END EVI_PKG_EJECU_VIRTU;
/

CREATE OR REPLACE PACKAGE BODY "EVI_PKG_EJECU_VIRTU" IS

  /**************************************************************************
  Descripcion       : Inserta un registro del archivo de MICA en la tabla INT_SOLIC_CONSO_CABEC
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE EVI_PR_INSER_REGIS_ARCHI_MICA(PS_COD_PAIS  IN VARCHAR2,
                                          PS_COD_PERI  IN VARCHAR2,
                                          PS_COD_CLIE  IN VARCHAR2,
                                          PS_TIP_ORDE  IN VARCHAR2,
                                          PS_FEC_SOLIC IN DATE) IS
    EXISTE      NUMBER;
    PROCESADA   VARCHAR(1);
    RECHAZADA   NUMBER := 0;
    COD_TERR    VARCHAR2(20);
    LS_COUNT    NUMBER;
    FECPROG     DATE;
    INDICA_PROC VARCHAR(1);

  BEGIN
    -- Valido del tipo de orden de compra
    IF (PS_TIP_ORDE <> 'N' AND PS_TIP_ORDE <> 'E' AND PS_TIP_ORDE <> 'P') THEN
      RETURN;
    END IF;

    -- Valido periodo activo
    IF (NOT GEN_PKG_GENER.GEN_FN_PERIO_VALID(PS_COD_PERI)) THEN
      RETURN;
    END IF;

    -- Valido que el cliente exista y sea consultora
    SELECT COUNT(1)
      INTO LS_COUNT
      FROM MAE_CLIEN, MAE_TIPO_CLIEN, MAE_CLIEN_TIPO_SUBTI
     WHERE MAE_CLIEN.OID_CLIE = MAE_CLIEN_TIPO_SUBTI.CLIE_OID_CLIE
       AND MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE =
           MAE_TIPO_CLIEN.OID_TIPO_CLIE
       AND MAE_TIPO_CLIEN.COD_TIPO_CLIE = '02'
       AND MAE_CLIEN.COD_CLIE = PS_COD_CLIE;

    IF LS_COUNT = 0 THEN
      RECHAZADA := 2;
    END IF;

    -- Validamos que tenga un codigo de region
    COD_TERR := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_TERR');
    IF COD_TERR IS NULL THEN
      RECHAZADA := 2;
    END IF;

    -- Valido si el pedido ya esta registrado para el pais, periodo, cliente y numero de lote
    SELECT COUNT(1)
      INTO EXISTE
      FROM INT_SOLIC_CONSO_CABEC I
     WHERE COD_PAIS = PS_COD_PAIS
       AND COD_PERI = PS_COD_PERI
       AND COD_CLIE = PS_COD_CLIE
       AND NUM_LOTE = (SELECT X.NUM_LOTE
                         FROM BAS_CTRL_FACT X
                        WHERE X.COD_PAIS = PS_COD_PAIS
                          AND X.COD_PERI = PS_COD_PERI);

    IF (EXISTE > 0) THEN
      RETURN;
    END IF;

    -- Busco el ultimo pedido para el pais, periodo y cliente
    BEGIN
      SELECT I.IND_PROC_GP2, I.FEC_PROG_FACT, I.IND_OCS_PROC
        INTO PROCESADA, FECPROG, INDICA_PROC
        FROM INT_SOLIC_CONSO_CABEC I
       WHERE I.COD_PAIS = PS_COD_PAIS
         AND I.COD_PERI = PS_COD_PERI
         AND I.COD_CLIE = PS_COD_CLIE
         AND I.NUM_LOTE = (SELECT MAX(C.NUM_LOTE)
                             FROM INT_SOLIC_CONSO_CABEC C
                            WHERE C.COD_PAIS = PS_COD_PAIS
                              AND C.COD_PERI = PS_COD_PERI
                              AND C.COD_CLIE = PS_COD_CLIE);

      IF (PROCESADA = 1) THEN
        -- El pedido ya fue procesado, inserto el segundo pedido
        INSERT INTO INT_SOLIC_CONSO_CABEC
          (COD_PAIS,
           COD_PERI,
           COD_CLIE,
           FEC_SOLI,
           NOM_CLIE,
           COD_REGI,
           DES_REGI,
           COD_ZONA,
           DES_ZONA,
           COD_TERR,
           TIP_ORDE,
           VAL_SALD_DEUD,
           IND_BLOQ_ADMI,
           IND_BLOQ_FINA,
           IND_ERROR_SGPE,
           IND_ERRO_RECH,
           STA_PROC,
           NUM_LOTE,
           IND_PROC_GP2,
           FEC_PROG_FACT)
        VALUES
          (PS_COD_PAIS,
           PS_COD_PERI,
           PS_COD_CLIE,
           PS_FEC_SOLIC,
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'NOM_CLIE'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_REGI'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'DES_REGI'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_ZONA'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'DES_ZONA'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_TERR'),
           PS_TIP_ORDE,
           0,
           GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU(PS_COD_CLIE, '01'),
           GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU(PS_COD_CLIE, '02'),
           1,
           RECHAZADA,
           DECODE(RECHAZADA, 0, 'A', 'R'),
           (SELECT X.NUM_LOTE
              FROM BAS_CTRL_FACT X
             WHERE X.COD_PAIS = PS_COD_PAIS
               AND X.COD_PERI = PS_COD_PERI),
           1, -- indicador GP2
           FECPROG -- PS_FEC_SOLIC -- Se inserta la fecha con la cual se facturo el pedido
           );
        RETURN;
      ELSIF (INDICA_PROC = 1) THEN
        -- El pedido ya fue procesado, inserto el segundo pedido
        INSERT INTO INT_SOLIC_CONSO_CABEC
          (COD_PAIS,
           COD_PERI,
           COD_CLIE,
           FEC_SOLI,
           NOM_CLIE,
           COD_REGI,
           DES_REGI,
           COD_ZONA,
           DES_ZONA,
           COD_TERR,
           TIP_ORDE,
           VAL_SALD_DEUD,
           IND_BLOQ_ADMI,
           IND_BLOQ_FINA,
           IND_ERROR_SGPE,
           IND_ERRO_RECH,
           STA_PROC,
           NUM_LOTE,
           IND_PROC_GP2,
           FEC_PROG_FACT)
        VALUES
          (PS_COD_PAIS,
           PS_COD_PERI,
           PS_COD_CLIE,
           PS_FEC_SOLIC,
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'NOM_CLIE'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_REGI'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'DES_REGI'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_ZONA'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'DES_ZONA'),
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_TERR'),
           PS_TIP_ORDE,
           0,
           GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU(PS_COD_CLIE, '01'),
           GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU(PS_COD_CLIE, '02'),
           1,
           RECHAZADA,
           DECODE(RECHAZADA, 0, 'A', 'R'),
           (SELECT X.NUM_LOTE
              FROM BAS_CTRL_FACT X
             WHERE X.COD_PAIS = PS_COD_PAIS
               AND X.COD_PERI = PS_COD_PERI),
           0, -- indicador GP2 del pedido anterior aun no se factura
           PS_FEC_SOLIC -- PS_FEC_SOLIC -- Se inserta la fecha de recepcion
           );
        RETURN;
      ELSIF (PROCESADA = 0) OR (INDICA_PROC = 0) THEN
        -- Los pedidos anteriores no han sido procesados, los marco como reemplazados
        UPDATE INT_SOLIC_CONSO_CABEC I
           SET I.IND_ERRO_REMP = 1
         WHERE I.COD_PAIS = PS_COD_PAIS
           AND I.COD_PERI = PS_COD_PERI
           AND I.COD_CLIE = PS_COD_CLIE;
      END IF;
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        -- No existe pedido para para el pais, periodo y cliente, no se hace nada
        NULL;
    END;

    -- Paso todas las validaciones, es primer pedido
    INSERT INTO INT_SOLIC_CONSO_CABEC
      (COD_PAIS,
       COD_PERI,
       COD_CLIE,
       FEC_SOLI,
       NOM_CLIE,
       COD_REGI,
       DES_REGI,
       COD_ZONA,
       DES_ZONA,
       COD_TERR,
       TIP_ORDE,
       VAL_SALD_DEUD,
       IND_BLOQ_ADMI,
       IND_BLOQ_FINA,
       IND_ERRO_RECH,
       STA_PROC,
       NUM_LOTE,
       IND_PROC_GP2,
       FEC_PROG_FACT)
    VALUES
      (PS_COD_PAIS,
       PS_COD_PERI,
       PS_COD_CLIE,
       PS_FEC_SOLIC,
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'NOM_CLIE'),
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_REGI'),
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'DES_REGI'),
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_ZONA'),
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'DES_ZONA'),
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(PS_COD_CLIE, 'COD_TERR'),
       PS_TIP_ORDE,
       0,
       GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU(PS_COD_CLIE, '01'),
       GEN_PKG_GENER.GEN_FN_CLIEN_BLOQU(PS_COD_CLIE, '02'),
       RECHAZADA,
       DECODE(RECHAZADA, 0, 'A', 'R'),
       (SELECT X.NUM_LOTE
          FROM BAS_CTRL_FACT X
         WHERE X.COD_PAIS = PS_COD_PAIS
           AND X.COD_PERI = PS_COD_PERI),
       0, -- indicador GP2
       PS_FEC_SOLIC);
  END EVI_PR_INSER_REGIS_ARCHI_MICA;

  /**************************************************************************
  Descripcion       : Carga de los resumenes de prefacturacion
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_RESUM_PRE_FACTU IS
  BEGIN
    EVI_PR_CARGA_PRIME_SOLIC_CABEC;
    EVI_PR_CARGA_PEDID_ZONA_FECHA;
    EVI_PR_CARGA_PEDID_ZONA_PERIO;
    EVI_PR_CARGA_PEDID_REGIO_FECHA;
    EVI_PR_CARGA_PEDID_REGIO_PERIO;
    EVI_PR_CARGA_PEDID_ZONA_TERRI;
  END EVI_PR_CARGA_RESUM_PRE_FACTU;

  /**************************************************************************
  Descripcion       : Carga en la tabla temporal las primeras solicitudes de
                      las consultoras del periodo activo.
  Fecha Creacion    : 23/03/2007
  Parametros Entrada:
  Autor             : Carlos Hurtado
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_PRIME_SOLIC_CABEC IS
    CURSOR PRIME_SOLIC_CABEC_CURSOR IS
      SELECT A.COD_PAIS,
             A.COD_PERI,
             A.COD_CLIE,
             NVL(A.COD_REGI, '0') COD_REGI,
             NVL(A.COD_ZONA, '0') COD_ZONA,
             NVL((SELECT DES_REGI FROM ZON_REGIO WHERE COD_REGI = A.COD_REGI), 'CODIGO REGION EN BLANCO') DES_REGI,
             NVL((SELECT DES_ZONA FROM ZON_ZONA  WHERE COD_ZONA = A.COD_ZONA), 'CODIGO ZONA EN BLANCO') DES_ZONA,
             A.TIP_ORDE,
             A.NUM_LOTE,
             A.FEC_SOLI,
             A.FEC_PROG_FACT,
             A.IND_PROC_GP2,
             CASE
               WHEN A.TIP_ORDE <> 'P' THEN
                1
               ELSE
                0
             END AS IND_RECE,
             CASE
               WHEN A.IND_PROC_GP2 = '1' AND A.FEC_SOLI = A.FEC_PROG_FACT THEN
                1
               ELSE
                0
             END AS IND_REGU,
             CASE
               WHEN A.IND_PROC_GP2 = '1' AND A.FEC_SOLI <> A.FEC_PROG_FACT THEN
                1
               ELSE
                0
             END AS IND_BOLS,
             0 AS IND_RECH
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS, COD_PERI, COD_CLIE, MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               WHERE COD_PERI IN
                     (SELECT COD_PERI FROM BAS_CTRL_FACT WHERE STA_CAMP = 0)
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE) X
       WHERE A.COD_PAIS = X.COD_PAIS
         AND A.COD_PERI = X.COD_PERI
         AND A.COD_CLIE = X.COD_CLIE
         AND A.NUM_LOTE = X.NUM_LOTE;

    TYPE PRIME_SOLIC_CABEC_TYPE IS TABLE OF INT_EVI_PRIME_SOLIC_CABEC%ROWTYPE;
    PRIME_SOLIC_CABEC PRIME_SOLIC_CABEC_TYPE;

    ROWLIMIT NUMBER := 1000;
    I        BINARY_INTEGER := 0;
  BEGIN
    delete INT_EVI_PRIME_SOLIC_CABEC;

    OPEN PRIME_SOLIC_CABEC_CURSOR;
    LOOP
      FETCH PRIME_SOLIC_CABEC_CURSOR BULK COLLECT
        INTO PRIME_SOLIC_CABEC LIMIT ROWLIMIT;
      EXIT WHEN PRIME_SOLIC_CABEC.COUNT = 0;
      FORALL I IN PRIME_SOLIC_CABEC.FIRST .. PRIME_SOLIC_CABEC.LAST
        INSERT INTO INT_EVI_PRIME_SOLIC_CABEC VALUES PRIME_SOLIC_CABEC (I);
    END LOOP;
    CLOSE PRIME_SOLIC_CABEC_CURSOR;

    -- Actualizamos los indicadores de rechazo por deuda
    UPDATE INT_EVI_PRIME_SOLIC_CABEC A
       SET A.IND_RECH = 1
     WHERE EXISTS (SELECT COD_CLIE
              FROM INT_SOLIC_CONSO_CABEC B
             WHERE B.IND_ERRO_DEUD = '2'
               AND B.IND_ERRO_REMP = '0'
               AND B.IND_ERROR_SGPE = '0'
               AND B.IND_ADMI_CART <> '1'
               AND B.COD_CLIE = A.COD_CLIE);


  END EVI_PR_CARGA_PRIME_SOLIC_CABEC;

  /**************************************************************************
  Descripcion       : Anexo 1: Carga la tabla INT_EVI_PERIO_REGIO_ZONA_FECHA
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_PEDID_ZONA_FECHA IS
    -- Zona Detalle
    CURSOR C_CARGA IS
      SELECT COD_PERI,
             COD_REGI,
             COD_ZONA,
             FEC_SOLI,
             NUM_LOTE,
             COUNT(*) NUM_REAL,
             SUM(IND_RECE) NUM_RECE,
             SUM(IND_REGU) NUM_REGU,
             SUM(IND_BOLS) NUM_BOLS,
             SUM(IND_RECH) NUM_RECH
        FROM INT_EVI_PRIME_SOLIC_CABEC
       GROUP BY COD_PERI, COD_REGI, COD_ZONA, FEC_SOLI, NUM_LOTE;

    TYPE CARGARECORDTYPE IS RECORD(
      CODIGOPERIODO           INT_EVI_PRIME_SOLIC_CABEC.COD_PERI%TYPE,
      CODIGOREGION            INT_EVI_PRIME_SOLIC_CABEC.COD_REGI%TYPE,
      CODIGOZONA              INT_EVI_PRIME_SOLIC_CABEC.COD_ZONA%TYPE,
      FECHASOLICITUD          INT_EVI_PRIME_SOLIC_CABEC.FEC_SOLI%TYPE,
      NUMEROLOTE              INT_EVI_PRIME_SOLIC_CABEC.NUM_LOTE%TYPE,
      NUMPEDIDOSREALES        NUMBER(5),
      NUMPEDIDOSRECEPCIONADOS NUMBER(5),
      TOTALFACTURADOREGULAR   NUMBER(5),
      TOTALFACTURADOBOLSA     NUMBER(5),
      NUMPEDIDOSRECHAZADOS    NUMBER(5));

    TYPE CARGARECORDTAB IS TABLE OF CARGARECORDTYPE;
    CARGARECORD CARGARECORDTAB;

    I    BINARY_INTEGER := 0;

    NUMPEDIDOSESTIMADOS NUMBER(13) := 0;
    NUMPRIMEROSPEDIDOS  NUMBER(13) := 0;
    PORCRECEPCION       NUMBER(5, 2) := 0;
    DIFREALESTIMADO     NUMBER(13) := 0;

  BEGIN
    -- Eliminamos la informaci¿n de la tabla
    DELETE FROM INT_EVI_PERIO_REGIO_ZONA_FECHA
     WHERE COD_PERI IN
           (SELECT COD_PERI FROM BAS_CTRL_FACT WHERE STA_CAMP = 0);

    -- Abrimos el cursor y realizamos los calculos correspondientes
    OPEN C_CARGA;
    LOOP
      -- Bulk Collect
      FETCH C_CARGA BULK COLLECT
        INTO CARGARECORD LIMIT W_FILAS;
      IF CARGARECORD.COUNT > 0 THEN
        FOR I IN CARGARECORD.FIRST .. CARGARECORD.LAST LOOP
          -- Obtenemos los valores de los estadisticos faltantes
          NUMPEDIDOSESTIMADOS := EVI_FN_OBTEN_NUMER_PEDID_ESTIM(CARGARECORD(I)
                                                                .CODIGOPERIODO,
                                                                CARGARECORD(I)
                                                                .CODIGOREGION,
                                                                CARGARECORD(I)
                                                                .CODIGOZONA);
          NUMPRIMEROSPEDIDOS  := EVI_FN_OBTEN_NUMER_PRIME_PEDID(CARGARECORD(I)
                                                                .CODIGOPERIODO,
                                                                NULL,
                                                                CARGARECORD(I)
                                                                .CODIGOZONA,
                                                                CARGARECORD(I)
                                                                .FECHASOLICITUD,
                                                                CARGARECORD(I)
                                                                .NUMEROLOTE);
          PORCRECEPCION       := 0;
          IF NUMPEDIDOSESTIMADOS != 0 THEN
            PORCRECEPCION := (CARGARECORD(I)
                             .NUMPEDIDOSREALES / NUMPEDIDOSESTIMADOS) * 100;
          END IF;
          DIFREALESTIMADO := CARGARECORD(I)
                            .NUMPEDIDOSREALES - NUMPEDIDOSESTIMADOS;

          INSERT INTO INT_EVI_PERIO_REGIO_ZONA_FECHA
            (COD_PERI,
             COD_REGI,
             COD_ZONA,
             FEC_RECE,
             NUM_LOTE,
             NUM_PEDI_REAL,
             VAL_PORC_RECE,
             NUM_PRIM_PEDI,
             NUM_PEDI_RECA,
             TOT_FACT_REGU,
             TOT_FACT_BOLS,
             NUM_PEDI_RECH)
          VALUES
            (CARGARECORD(I).CODIGOPERIODO,
             CARGARECORD(I).CODIGOREGION,
             CARGARECORD(I).CODIGOZONA,
             CARGARECORD(I).FECHASOLICITUD,
             CARGARECORD(I).NUMEROLOTE,
             CARGARECORD(I).NUMPEDIDOSREALES,
             PORCRECEPCION,
             NUMPRIMEROSPEDIDOS,
             CARGARECORD(I).NUMPEDIDOSRECEPCIONADOS + NUMPRIMEROSPEDIDOS,
             CARGARECORD(I).TOTALFACTURADOREGULAR,
             CARGARECORD(I).TOTALFACTURADOBOLSA,
             CARGARECORD(I).NUMPEDIDOSRECHAZADOS);
        END LOOP;
      END IF;
      EXIT WHEN C_CARGA%NOTFOUND;
    END LOOP;
    CLOSE C_CARGA;

  END EVI_PR_CARGA_PEDID_ZONA_FECHA;

  /**************************************************************************
  Descripcion       : Anexo 2: Carga la tabla INT_EVI_PERIO_REGIO_ZONA
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_PEDID_ZONA_PERIO IS
    -- Zona Cabecera
    CURSOR C_CARGA IS
      SELECT COD_PERI,
             COD_REGI,
             COD_ZONA,
             DES_REGI,
             DES_ZONA,
             COUNT(*) NUM_REAL,
             SUM(IND_RECE) NUM_RECE,
             SUM(IND_REGU) NUM_REGU,
             SUM(IND_BOLS) NUM_BOLS,
             SUM(IND_RECH) NUM_RECH
        FROM INT_EVI_PRIME_SOLIC_CABEC
       GROUP BY COD_PERI, COD_REGI, COD_ZONA, DES_REGI, DES_ZONA;

    TYPE CARGARECORDTYPE IS RECORD(
      CODIGOPERIODO           INT_EVI_PRIME_SOLIC_CABEC.COD_PERI%TYPE,
      CODIGOREGION            INT_EVI_PRIME_SOLIC_CABEC.COD_REGI%TYPE,
      CODIGOZONA              INT_EVI_PRIME_SOLIC_CABEC.COD_ZONA%TYPE,
      DESCRIPCIONREGION       INT_EVI_PRIME_SOLIC_CABEC.DES_REGI%TYPE,
      DESCRIPCIONZONA         INT_EVI_PRIME_SOLIC_CABEC.DES_ZONA%TYPE,
      NUMPEDIDOSREALES        NUMBER(5),
      NUMPEDIDOSRECEPCIONADOS NUMBER(5),
      TOTALFACTURADOREGULAR   NUMBER(5),
      TOTALFACTURADOBOLSA     NUMBER(5),
      NUMPEDIDOSRECHAZADOS    NUMBER(5));

    TYPE CARGARECORDTAB IS TABLE OF CARGARECORDTYPE;
    CARGARECORD CARGARECORDTAB;

    I    BINARY_INTEGER := 0;

    NUMPEDIDOSESTIMADOS  NUMBER(13) := 0;
    NUMPEDIDOSANTERIORES NUMBER(13) := 0;
    NUMPRIMEROSPEDIDOS   NUMBER(13) := 0;
    PORCRECEPCION        NUMBER(5, 2) := 0;
    DIFREALESTIMADO      NUMBER(13) := 0;

  BEGIN
    -- Eliminamos la informacion de la tabla
    DELETE FROM INT_EVI_PERIO_REGIO_ZONA
     WHERE COD_PERI IN
           (SELECT COD_PERI FROM BAS_CTRL_FACT WHERE STA_CAMP = 0);

    -- Abrimos el cursor y realizamos los calculos correspondientes
    OPEN C_CARGA;
    LOOP
      -- Bulk Collect
      FETCH C_CARGA BULK COLLECT
        INTO CARGARECORD LIMIT W_FILAS;
      IF CARGARECORD.COUNT > 0 THEN
        FOR I IN CARGARECORD.FIRST .. CARGARECORD.LAST LOOP
          -- Obtenemos los valores de los estadisticos faltantes
          NUMPEDIDOSESTIMADOS := EVI_FN_OBTEN_NUMER_PEDID_ESTIM(CARGARECORD(I)
                                                                .CODIGOPERIODO,
                                                                CARGARECORD(I)
                                                                .CODIGOREGION,
                                                                CARGARECORD(I)
                                                                .CODIGOZONA);
          NUMPRIMEROSPEDIDOS  := EVI_FN_OBTEN_NUMER_PRIME_PEDID(CARGARECORD(I)
                                                                .CODIGOPERIODO,
                                                                NULL,
                                                                CARGARECORD(I)
                                                                .CODIGOZONA,
                                                                NULL,
                                                                NULL);
          PORCRECEPCION       := 0;
          IF NUMPEDIDOSESTIMADOS != 0 THEN
            PORCRECEPCION := (CARGARECORD(I)
                             .NUMPEDIDOSREALES / NUMPEDIDOSESTIMADOS) * 100;
          END IF;
          DIFREALESTIMADO := CARGARECORD(I)
                            .NUMPEDIDOSREALES - NUMPEDIDOSESTIMADOS;

          BEGIN
            SELECT NVL(NUM_PEDI_REAL, 0)
              INTO NUMPEDIDOSANTERIORES
              FROM INT_EVI_PERIO_REGIO_ZONA
             WHERE COD_PERI =
                   GEN_FN_PERIO_ATRAS(CARGARECORD(I).CODIGOPERIODO, 1)
               AND COD_REGI = CARGARECORD(I)
            .CODIGOREGION
               AND COD_ZONA = CARGARECORD(I).CODIGOZONA;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NUMPEDIDOSANTERIORES := 0;
          END;

          INSERT INTO INT_EVI_PERIO_REGIO_ZONA
            (COD_PERI,
             COD_REGI,
             COD_ZONA,
             DES_REGI,
             DES_ZONA,
             NUM_PEDI_ESTI,
             NUM_PEDI_ANTE,
             NUM_PEDI_REAL,
             VAL_PORC_RECE,
             VAL_DIFE_REAL_ESTI,
             NUM_PRIM_PEDI,
             NUM_PEDI_RECA,
             TOT_FACT_REGU,
             TOT_FACT_BOLS,
             NUM_PEDI_RECH)
          VALUES
            (CARGARECORD(I).CODIGOPERIODO,
             CARGARECORD(I).CODIGOREGION,
             CARGARECORD(I).CODIGOZONA,
             CARGARECORD(I).DESCRIPCIONREGION,
             CARGARECORD(I).DESCRIPCIONZONA,
             NUMPEDIDOSESTIMADOS,
             NUMPEDIDOSANTERIORES,
             CARGARECORD(I).NUMPEDIDOSREALES,
             PORCRECEPCION,
             DIFREALESTIMADO,
             NUMPRIMEROSPEDIDOS,
             CARGARECORD(I).NUMPEDIDOSRECEPCIONADOS + NUMPRIMEROSPEDIDOS,
             CARGARECORD(I).TOTALFACTURADOREGULAR,
             CARGARECORD(I).TOTALFACTURADOBOLSA,
             CARGARECORD(I).NUMPEDIDOSRECHAZADOS);
        END LOOP;
      END IF;
      EXIT WHEN C_CARGA%NOTFOUND;
    END LOOP;
    CLOSE C_CARGA;


  END EVI_PR_CARGA_PEDID_ZONA_PERIO;

  /**************************************************************************
  Descripcion       : Anexo 3: Carga la tabla EVI_PR_CARGA_PEDID_ZONA_TERRI
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_PEDID_ZONA_TERRI IS
    -- Territorios
    CURSOR C_CARGA IS
      SELECT A.COD_PERI,
             A.COD_REGI,
             A.COD_ZONA,
             A.COD_TERR,
             R.DES_REGI,
             Z.DES_ZONA
        FROM INT_SOLIC_CONSO_CABEC A, ZON_REGIO R, ZON_ZONA Z
       WHERE A.COD_PERI IN
             (SELECT T.COD_PERI FROM BAS_CTRL_FACT T WHERE T.STA_CAMP = 0)
         AND A.COD_REGI IS NOT NULL
         AND A.COD_ZONA IS NOT NULL
         AND A.COD_TERR IS NOT NULL
         AND A.IND_ERRO_RECH = 0
		 AND A.COD_REGI = R.COD_REGI
		 AND A.COD_ZONA = Z.COD_ZONA
       GROUP BY A.COD_PERI,
                A.COD_REGI,
                R.DES_REGI,
                A.COD_ZONA,
                A.COD_TERR,
                Z.DES_ZONA;

    TYPE CARGARECORDTYPE IS RECORD(
      CODIGOPERIODO     INT_SOLIC_CONSO_CABEC.COD_PERI%TYPE,
      CODIGOREGION      INT_SOLIC_CONSO_CABEC.COD_REGI%TYPE,
      CODIGOZONA        INT_SOLIC_CONSO_CABEC.COD_ZONA%TYPE,
      CODIGOTERRITORIO  INT_SOLIC_CONSO_CABEC.COD_TERR%TYPE,
      DESCRIPCIONREGION INT_SOLIC_CONSO_CABEC.DES_REGI%TYPE,
      DESCRIPCIONZONA   INT_SOLIC_CONSO_CABEC.DES_ZONA%TYPE);

    TYPE CARGARECORDTAB IS TABLE OF CARGARECORDTYPE;
    CARGARECORD CARGARECORDTAB;

    I    BINARY_INTEGER := 0;

  BEGIN
    -- Truncamos la tabla de cabecera de region
   delete PED_PERIO_ZONA_TERRI;

    -- Abrimos el cursor y realizamos los calculos correspondientes
    OPEN C_CARGA;
    LOOP
      -- Bulk Collect
      FETCH C_CARGA BULK COLLECT
        INTO CARGARECORD LIMIT W_FILAS;
      IF CARGARECORD.COUNT > 0 THEN
        FOR I IN CARGARECORD.FIRST .. CARGARECORD.LAST LOOP
          INSERT INTO PED_PERIO_ZONA_TERRI
            (COD_PERI,
             COD_REGI,
             COD_ZONA,
             COD_TERR,
             DES_REGI,
             DES_ZONA,
             DES_TERR)
          VALUES
            (CARGARECORD(I).CODIGOPERIODO,
             CARGARECORD(I).CODIGOREGION,
             CARGARECORD(I).CODIGOZONA,
             CARGARECORD(I).CODIGOTERRITORIO,
             CARGARECORD(I).DESCRIPCIONREGION,
             CARGARECORD(I).DESCRIPCIONZONA,
             CARGARECORD(I).CODIGOTERRITORIO);
        END LOOP;
      END IF;
      EXIT WHEN C_CARGA%NOTFOUND;
    END LOOP;
    CLOSE C_CARGA;


  END EVI_PR_CARGA_PEDID_ZONA_TERRI;

  /**************************************************************************
  Descripcion       : Anexo 3: Carga la tabla INT_EVI_PERIO_REGIO_FECHA
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_PEDID_REGIO_FECHA IS
    -- Region Detalle
    CURSOR C_CARGA IS
      SELECT COD_PERI,
             COD_REGI,
             DES_REGI,
             FEC_SOLI,
             NUM_LOTE,
             COUNT(*) NUM_REAL,
             SUM(IND_RECE) NUM_RECE,
             SUM(IND_REGU) NUM_REGU,
             SUM(IND_BOLS) NUM_BOLS,
             SUM(IND_RECH) NUM_RECH
        FROM INT_EVI_PRIME_SOLIC_CABEC
       GROUP BY COD_PERI, COD_REGI, DES_REGI, FEC_SOLI, NUM_LOTE
       ORDER BY COD_REGI;

    TYPE CARGARECORDTYPE IS RECORD(
      CODIGOPERIODO           INT_EVI_PRIME_SOLIC_CABEC.COD_PERI%TYPE,
      CODIGOREGION            INT_EVI_PRIME_SOLIC_CABEC.COD_REGI%TYPE,
      DESCRIPCIONREGION       INT_EVI_PRIME_SOLIC_CABEC.DES_REGI%TYPE,
      FECHASOLICITUD          INT_EVI_PRIME_SOLIC_CABEC.FEC_SOLI%TYPE,
      NUMEROLOTE              INT_EVI_PRIME_SOLIC_CABEC.NUM_LOTE%TYPE,
      NUMPEDIDOSREALES        NUMBER(5),
      NUMPEDIDOSRECEPCIONADOS NUMBER(5),
      TOTALFACTURADOREGULAR   NUMBER(5),
      TOTALFACTURADOBOLSA     NUMBER(5),
      NUMPEDIDOSRECHAZADOS    NUMBER(5));

    TYPE CARGARECORDTAB IS TABLE OF CARGARECORDTYPE;
    CARGARECORD CARGARECORDTAB;

    I    BINARY_INTEGER := 0;

    NUMPEDIDOSESTIMADOS NUMBER(13) := 0;
    NUMPRIMEROSPEDIDOS  NUMBER(13) := 0;
    PORCRECEPCION       NUMBER(5, 2) := 0;
    DIFREALESTIMADO     NUMBER(13) := 0;

  BEGIN
    -- Eliminamos la informacion de la tabla
    DELETE FROM INT_EVI_PERIO_REGIO_FECHA
     WHERE COD_PERI IN
           (SELECT COD_PERI FROM BAS_CTRL_FACT WHERE STA_CAMP = 0);

    -- Abrimos el cursor y realizamos los calculos correspondientes
    OPEN C_CARGA;
    LOOP
      -- Bulk Collect
      FETCH C_CARGA BULK COLLECT
        INTO CARGARECORD LIMIT W_FILAS;
      IF CARGARECORD.COUNT > 0 THEN
        FOR I IN CARGARECORD.FIRST .. CARGARECORD.LAST LOOP
          -- Obtenemos los valores de los estadisticos faltantes
          NUMPEDIDOSESTIMADOS := EVI_FN_OBTEN_NUMER_PEDID_ESTIM(CARGARECORD(I)
                                                                .CODIGOPERIODO,
                                                                CARGARECORD(I)
                                                                .CODIGOREGION,
                                                                NULL);
          NUMPRIMEROSPEDIDOS  := EVI_FN_OBTEN_NUMER_PRIME_PEDID(CARGARECORD(I)
                                                                .CODIGOPERIODO,
                                                                CARGARECORD(I)
                                                                .CODIGOREGION,
                                                                NULL,
                                                                CARGARECORD(I)
                                                                .FECHASOLICITUD,
                                                                CARGARECORD(I)
                                                                .NUMEROLOTE);
          PORCRECEPCION       := 0;
          IF NUMPEDIDOSESTIMADOS != 0 THEN
            PORCRECEPCION := (CARGARECORD(I)
                             .NUMPEDIDOSREALES / NUMPEDIDOSESTIMADOS) * 100;
          END IF;
          DIFREALESTIMADO := CARGARECORD(I)
                            .NUMPEDIDOSREALES - NUMPEDIDOSESTIMADOS;

          INSERT INTO INT_EVI_PERIO_REGIO_FECHA
            (COD_PERI,
             COD_REGI,
             FEC_RECE,
             NUM_LOTE,
             NUM_PEDI_REAL,
             VAL_PORC_RECE,
             NUM_PRIM_PEDI,
             NUM_PEDI_RECA,
             TOT_FACT_REGU,
             TOT_FACT_BOLS,
             NUM_PEDI_RECH)
          VALUES
            (CARGARECORD(I).CODIGOPERIODO,
             CARGARECORD(I).CODIGOREGION,
             CARGARECORD(I).FECHASOLICITUD,
             CARGARECORD(I).NUMEROLOTE,
             CARGARECORD(I).NUMPEDIDOSREALES,
             PORCRECEPCION,
             NUMPRIMEROSPEDIDOS,
             CARGARECORD(I).NUMPEDIDOSRECEPCIONADOS + NUMPRIMEROSPEDIDOS,
             CARGARECORD(I).TOTALFACTURADOREGULAR,
             CARGARECORD(I).TOTALFACTURADOBOLSA,
             CARGARECORD(I).NUMPEDIDOSRECHAZADOS);
        END LOOP;
      END IF;
      EXIT WHEN C_CARGA%NOTFOUND;
    END LOOP;
    CLOSE C_CARGA;


  END EVI_PR_CARGA_PEDID_REGIO_FECHA;

  /**************************************************************************
  Descripcion       : Anexo 4: Carga la tabla INT_EVI_PERIO_REGIO
  Fecha Creacion    : 17/10/2006
  Parametros Entrada: Ninguno
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_PEDID_REGIO_PERIO IS
    -- Region Cabecera
    CURSOR C_CARGA IS
      SELECT COD_PERI,
             COD_REGI,
             DES_REGI,
             COUNT(*) NUM_REAL,
             SUM(IND_RECE) NUM_RECE,
             SUM(IND_REGU) NUM_REGU,
             SUM(IND_BOLS) NUM_BOLS,
             SUM(IND_RECH) NUM_RECH
        FROM INT_EVI_PRIME_SOLIC_CABEC
       GROUP BY COD_PERI, COD_REGI, DES_REGI
       ORDER BY COD_REGI;

    TYPE CARGARECORDTYPE IS RECORD(
      CODIGOPERIODO           INT_EVI_PRIME_SOLIC_CABEC.COD_PERI%TYPE,
      CODIGOREGION            INT_EVI_PRIME_SOLIC_CABEC.COD_REGI%TYPE,
      DESCRIPCIONREGION       INT_EVI_PRIME_SOLIC_CABEC.DES_REGI%TYPE,
      NUMPEDIDOSREALES        NUMBER(5),
      NUMPEDIDOSRECEPCIONADOS NUMBER(5),
      TOTALFACTURADOREGULAR   NUMBER(5),
      TOTALFACTURADOBOLSA     NUMBER(5),
      NUMPEDIDOSRECHAZADOS    NUMBER(5));

    TYPE CARGARECORDTAB IS TABLE OF CARGARECORDTYPE;
    CARGARECORD CARGARECORDTAB;

    I    BINARY_INTEGER := 0;

    NUMPEDIDOSESTIMADOS  NUMBER(13) := 0;
    NUMPEDIDOSANTERIORES NUMBER(13) := 0;
    NUMPRIMEROSPEDIDOS   NUMBER(13) := 0;
    PORCRECEPCION        NUMBER(5, 2) := 0;
    DIFREALESTIMADO      NUMBER(13) := 0;

  BEGIN
    -- Eliminamos la informaci¿n de la tabla
    DELETE FROM INT_EVI_PERIO_REGIO
     WHERE COD_PERI IN
           (SELECT COD_PERI FROM BAS_CTRL_FACT WHERE STA_CAMP = 0);

    -- Abrimos el cursor y realizamos los calculos correspondientes
    OPEN C_CARGA;
    LOOP
      -- Bulk Collect
      FETCH C_CARGA BULK COLLECT
        INTO CARGARECORD LIMIT W_FILAS;
      IF CARGARECORD.COUNT > 0 THEN
        FOR I IN CARGARECORD.FIRST .. CARGARECORD.LAST LOOP
          -- Obtenemos los valores de los estadisticos faltantes
          NUMPEDIDOSESTIMADOS := EVI_FN_OBTEN_NUMER_PEDID_ESTIM(CARGARECORD(I)
                                                                .CODIGOPERIODO,
                                                                CARGARECORD(I)
                                                                .CODIGOREGION,
                                                                NULL);
          NUMPRIMEROSPEDIDOS  := EVI_FN_OBTEN_NUMER_PRIME_PEDID(CARGARECORD(I)
                                                                .CODIGOPERIODO,
                                                                CARGARECORD(I)
                                                                .CODIGOREGION,
                                                                NULL,
                                                                NULL,
                                                                NULL);
          PORCRECEPCION       := 0;
          IF NUMPEDIDOSESTIMADOS != 0 THEN
            PORCRECEPCION := (CARGARECORD(I)
                             .NUMPEDIDOSREALES / NUMPEDIDOSESTIMADOS) * 100;
          END IF;
          DIFREALESTIMADO := CARGARECORD(I)
                            .NUMPEDIDOSREALES - NUMPEDIDOSESTIMADOS;

          BEGIN
            SELECT NVL(NUM_PEDI_REAL, 0)
              INTO NUMPEDIDOSANTERIORES
              FROM INT_EVI_PERIO_REGIO
             WHERE COD_PERI =
                   GEN_FN_PERIO_ATRAS(CARGARECORD(I).CODIGOPERIODO, 1)
               AND COD_REGI = CARGARECORD(I).CODIGOREGION;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              NUMPEDIDOSANTERIORES := 0;
          END;

          INSERT INTO INT_EVI_PERIO_REGIO
            (COD_PERI,
             COD_REGI,
             DES_REGI,
             NUM_PEDI_ESTI,
             NUM_PEDI_ANTE,
             NUM_PEDI_REAL,
             VAL_PORC_RECE,
             VAL_DIFE_REAL_ESTI,
             NUM_PRIM_PEDI,
             NUM_PEDI_RECA,
             TOT_FACT_REGU,
             TOT_FACT_BOLS,
             NUM_PEDI_RECH)
          VALUES
            (CARGARECORD(I).CODIGOPERIODO,
             CARGARECORD(I).CODIGOREGION,
             CARGARECORD(I).DESCRIPCIONREGION,
             NUMPEDIDOSESTIMADOS,
             NUMPEDIDOSANTERIORES,
             CARGARECORD(I).NUMPEDIDOSREALES,
             PORCRECEPCION,
             DIFREALESTIMADO,
             NUMPRIMEROSPEDIDOS,
             CARGARECORD(I).NUMPEDIDOSRECEPCIONADOS + NUMPRIMEROSPEDIDOS,
             CARGARECORD(I).TOTALFACTURADOREGULAR,
             CARGARECORD(I).TOTALFACTURADOBOLSA,
             CARGARECORD(I).NUMPEDIDOSRECHAZADOS);
        END LOOP;
      END IF;
      EXIT WHEN C_CARGA%NOTFOUND;
    END LOOP;
    CLOSE C_CARGA;


  END EVI_PR_CARGA_PEDID_REGIO_PERIO;

  PROCEDURE EVI_PR_CARGA_FACTU(PS_COD_PERI       IN VARCHAR2,
                               PS_COD_PERI_CRUCE IN VARCHAR2,
                               PS_COD_PAIS       IN VARCHAR2,
                               PS_COD_MARCA      IN VARCHAR2,
                               PS_COD_CANAL      IN VARCHAR2) IS
  BEGIN
    -- Ejecutamos el detalle ya que el numero de rechazados
    -- enviado por la cabecera lo ha de tomar de esta tabla
    EVI_PR_CARGA_FACTU_DETAL(PS_COD_PERI,
                             PS_COD_PERI_CRUCE,
                             PS_COD_PAIS,
                             PS_COD_MARCA,
                             PS_COD_CANAL);

    -- Ejecutamos el calculo de estadisticos de la cabecera
    EVI_PR_CARGA_FACTU_CABEC(PS_COD_PERI,
                             PS_COD_PERI_CRUCE,
                             PS_COD_PAIS,
                             PS_COD_MARCA,
                             PS_COD_CANAL);
  END EVI_PR_CARGA_FACTU;

  /**************************************************************************
  Descripcion       : Carga la informacion de perido seleccionado y perido de cruce en la tabla
                      temporal INT_EVI_FACTU_CABEC_TEMPO que servira para la obtencion de la informacion a enviar
                      de Facturacion - Cabecera para la interfaz.
  Fecha Creacion    : 25/10/2006
  Fecha Modificacion: 27/12/2006 (Carlos Hurtado Ramirez)
  Parametros Entrada:
    ps_cod_peri     :  Codigo de periodo
    ps_cod_peri_cruce   :  Codigo de periodo de cruce
    ps_cod_pais     :  Codigo de pais
    ps_cod_marca    :  Codigo de marca
    ps_cod_canal    :  Codigo de canal

  Autor             : Carla Marius
  Modificaciones    : En lugar de utilizar un solo cursor, se crearon 3 independientes
                      para una mayor claridad y un funcinamiento correcto del proceso.
       (Carlos Hurtado Ramirez)

  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_FACTU_CABEC(PS_COD_PERI       IN VARCHAR2,
                                     PS_COD_PERI_CRUCE IN VARCHAR2,
                                     PS_COD_PAIS       IN VARCHAR2,
                                     PS_COD_MARCA      IN VARCHAR2,
                                     PS_COD_CANAL      IN VARCHAR2) IS

    CURSOR C_PEDI_RECH IS
      SELECT INT_EVI_FACTU_DETAL_TEMPO.COD_PAIS,
             INT_EVI_FACTU_DETAL_TEMPO.COD_PERI,
             INT_EVI_FACTU_DETAL_TEMPO.COD_REGI,
             INT_EVI_FACTU_DETAL_TEMPO.COD_ZONA,
             ZON_REGIO.OID_REGI,
             ZON_ZONA.OID_ZONA,
             COUNT(*) NUM_PEDI_RECH
        FROM INT_EVI_FACTU_DETAL_TEMPO, ZON_REGIO, ZON_ZONA
       WHERE ((INT_EVI_FACTU_DETAL_TEMPO.COD_PAIS = PS_COD_PAIS) AND
             (INT_EVI_FACTU_DETAL_TEMPO.COD_REGI = ZON_REGIO.COD_REGI) AND
             (INT_EVI_FACTU_DETAL_TEMPO.COD_ZONA = ZON_ZONA.COD_ZONA) AND
             (INT_EVI_FACTU_DETAL_TEMPO.COD_PERI = PS_COD_PERI OR
             (PS_COD_PERI_CRUCE IS NOT NULL AND
             INT_EVI_FACTU_DETAL_TEMPO.COD_PERI = PS_COD_PERI_CRUCE)))
       GROUP BY INT_EVI_FACTU_DETAL_TEMPO.COD_PAIS,
                INT_EVI_FACTU_DETAL_TEMPO.COD_REGI,
                INT_EVI_FACTU_DETAL_TEMPO.COD_ZONA,
                INT_EVI_FACTU_DETAL_TEMPO.COD_PERI,
                ZON_REGIO.OID_REGI,
                ZON_ZONA.OID_ZONA;

    R_PEDI_RECH C_PEDI_RECH%ROWTYPE;

    CURSOR C_PEDI_ESTI IS
      SELECT SEG_PAIS.COD_PAIS,
             SEG_PERIO_CORPO.COD_PERI,
             ZON_REGIO.COD_REGI,
             ZON_ZONA.COD_ZONA,
             ZON_REGIO.OID_REGI,
             ZON_ZONA.OID_ZONA,
             INT_FUENT_VENTA_PREVI_SAP.NUM_PEDI AS NUM_PEDI_ESTI,
             INT_FUENT_VENTA_PREVI_SAP.VAL_VENT_NETA_ESTA AS VAL_MONT_ESTI_VENT
        FROM SEG_PAIS,
             SEG_PERIO_CORPO,
             SEG_MARCA,
             SEG_CANAL,
             ZON_ZONA,
             ZON_REGIO,
             CRA_PERIO,
             INT_FUENT_VENTA_PREVI_SAP
       WHERE ((SEG_PAIS.OID_PAIS = ZON_ZONA.PAIS_OID_PAIS) AND
             (ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI) AND
             (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS) AND
             (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
             (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
             (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
             (ZON_ZONA.OID_ZONA = INT_FUENT_VENTA_PREVI_SAP.ZZON_OID_ZONA) AND
             (ZON_REGIO.OID_REGI = INT_FUENT_VENTA_PREVI_SAP.ZORG_OID_REGI) AND
             (CRA_PERIO.OID_PERI = INT_FUENT_VENTA_PREVI_SAP.PERD_OID_PERI) AND
             (SEG_PAIS.COD_PAIS = PS_COD_PAIS) AND
             (SEG_MARCA.COD_MARC = PS_COD_MARCA) AND
             (SEG_CANAL.COD_CANA = PS_COD_CANAL) AND
             (SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI OR
             (PS_COD_PERI_CRUCE IS NOT NULL AND
             SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI_CRUCE)));

    R_PEDI_ESTI C_PEDI_ESTI%ROWTYPE;

    EXISTE NUMBER := 0;
    l_user  VARCHAR2(20);
  BEGIN
    l_user := USER;

    -- primero limpiar toda la tabla temporal
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_EVI_FACTU_CABEC_TEMPO';
    --DELETE FROM INT_EVI_FACTU_CABEC_TEMPO;

    -- insertamos las zonas con los valores de los pedidos facturados
    INSERT INTO INT_EVI_FACTU_CABEC_TEMPO
      (COD_PAIS,
       COD_PERI,
       COD_REGI,
       COD_ZONA,
       OID_REGI,
       OID_ZONA,
       NUM_PEDI_FACT,
       VAL_MONT_FACT)
      SELECT SEG_PAIS.COD_PAIS,
             SEG_PERIO_CORPO.COD_PERI,
             ZON_REGIO.COD_REGI,
             ZON_ZONA.COD_ZONA,
             ZON_REGIO.OID_REGI,
             ZON_ZONA.OID_ZONA,
             COUNT(*) AS NUM_PEDI_FACT,
             SUM(PED_SOLIC_CABEC.VAL_PREC_NETO_TOTA_LOCA) -
             SUM(PED_SOLIC_CABEC.VAL_IMPO_FLET_SIN_IMPU_TOTA) VAL_MONT_FACT
        FROM CRA_PERIO,
             PED_SOLIC_CABEC,
             PED_TIPO_SOLIC_PAIS,
             SEG_PERIO_CORPO,
             SEG_PAIS,
             SEG_MARCA,
             SEG_CANAL,
             PED_TIPO_SOLIC,
             ZON_ZONA,
             ZON_REGIO
       WHERE ((CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI) AND
             (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
             PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS) AND
             (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
             (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS) AND
             (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
             (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
             (PED_TIPO_SOLIC.OID_TIPO_SOLI =
             PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI) AND
             (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
             (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS)) AND
             ((PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 1) OR
             (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 5)) AND
             (PED_SOLIC_CABEC.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA) AND
             (ZON_ZONA.ZORG_OID_REGI = ZON_REGIO.OID_REGI) AND
             (PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC = 5) AND
             (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1) AND
             (PED_SOLIC_CABEC.IND_OC = 1) AND
             (PED_SOLIC_CABEC.FEC_FACT IS NOT NULL) AND
             (SEG_PAIS.COD_PAIS = PS_COD_PAIS) AND
             (SEG_MARCA.COD_MARC = PS_COD_MARCA) AND
             (SEG_CANAL.COD_CANA = PS_COD_CANAL) AND
             (SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI OR
             (PS_COD_PERI_CRUCE IS NOT NULL AND
             SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI_CRUCE)))
       GROUP BY SEG_PAIS.COD_PAIS,
                ZON_REGIO.COD_REGI,
                ZON_ZONA.COD_ZONA,
                SEG_PERIO_CORPO.COD_PERI,
                ZON_REGIO.OID_REGI,
                ZON_ZONA.OID_ZONA;

    DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'INT_EVI_FACTU_CABEC_TEMPO', CASCADE => TRUE );

    -- Actualizamos las cantidades de pedidos rechazados en caso sea necesario
    OPEN C_PEDI_RECH;
    LOOP
      FETCH C_PEDI_RECH
        INTO R_PEDI_RECH;
      EXIT WHEN C_PEDI_RECH%NOTFOUND;
      BEGIN
        SELECT COUNT(1)
          INTO EXISTE
          FROM INT_EVI_FACTU_CABEC_TEMPO
         WHERE COD_PAIS = R_PEDI_RECH.COD_PAIS
           AND COD_PERI = R_PEDI_RECH.COD_PERI
           AND COD_REGI = R_PEDI_RECH.COD_REGI
           AND COD_ZONA = R_PEDI_RECH.COD_ZONA;

        IF (EXISTE = 1) THEN
          UPDATE INT_EVI_FACTU_CABEC_TEMPO
             SET NUM_PEDI_RECH = R_PEDI_RECH.NUM_PEDI_RECH
           WHERE COD_PAIS = R_PEDI_RECH.COD_PAIS
             AND COD_PERI = R_PEDI_RECH.COD_PERI
             AND COD_REGI = R_PEDI_RECH.COD_REGI
             AND COD_ZONA = R_PEDI_RECH.COD_ZONA;
        ELSE
          INSERT INTO INT_EVI_FACTU_CABEC_TEMPO
            (COD_PAIS,
             COD_PERI,
             COD_REGI,
             COD_ZONA,
             OID_REGI,
             OID_ZONA,
             NUM_PEDI_RECH)
          VALUES
            (R_PEDI_RECH.COD_PAIS,
             R_PEDI_RECH.COD_PERI,
             R_PEDI_RECH.COD_REGI,
             R_PEDI_RECH.COD_ZONA,
             R_PEDI_RECH.OID_REGI,
             R_PEDI_RECH.OID_ZONA,
             R_PEDI_RECH.NUM_PEDI_RECH);
        END IF;
      END;
    END LOOP;
    CLOSE C_PEDI_RECH;

    -- Actualizamos las cantidades de pedidos estimados
    OPEN C_PEDI_ESTI;
    LOOP
      FETCH C_PEDI_ESTI
        INTO R_PEDI_ESTI;
      EXIT WHEN C_PEDI_ESTI%NOTFOUND;
      BEGIN
        SELECT COUNT(1)
          INTO EXISTE
          FROM INT_EVI_FACTU_CABEC_TEMPO
         WHERE COD_PAIS = R_PEDI_ESTI.COD_PAIS
           AND COD_PERI = R_PEDI_ESTI.COD_PERI
           AND COD_REGI = R_PEDI_ESTI.COD_REGI
           AND COD_ZONA = R_PEDI_ESTI.COD_ZONA;

        IF (EXISTE = 1) THEN
          UPDATE INT_EVI_FACTU_CABEC_TEMPO
             SET NUM_PEDI_ESTI      = R_PEDI_ESTI.NUM_PEDI_ESTI,
                 VAL_MONT_ESTI_VENT = R_PEDI_ESTI.VAL_MONT_ESTI_VENT
           WHERE COD_PAIS = R_PEDI_ESTI.COD_PAIS
             AND COD_PERI = R_PEDI_ESTI.COD_PERI
             AND COD_REGI = R_PEDI_ESTI.COD_REGI
             AND COD_ZONA = R_PEDI_ESTI.COD_ZONA;
        END IF;
      END;
    END LOOP;
    CLOSE C_PEDI_ESTI;

    -- Calculamos el porcentaje de pedidos facturados
    UPDATE INT_EVI_FACTU_CABEC_TEMPO
       SET VAL_PORC_PEDI_FACT = DECODE(NUM_PEDI_ESTI,
                                       0,
                                       0,
                                       ROUND(NUM_PEDI_FACT / NUM_PEDI_ESTI * 100));

  END EVI_PR_CARGA_FACTU_CABEC;

  /**************************************************************************
  Descripcion       : Carga la informacion de perido seleccionado y perido de cruce en la tabla
                      temporal INT_EVI_FACTU_DETAL_TEMPO que servira para la obtencion de la informacion a enviar
                      de Facturacion - Detalle para la interfaz.
  Fecha Creacion    : 26/10/2006
  Parametros Entrada:
    ps_cod_peri     :  Codigo de periodo
    ps_cod_peri_cruce   :  Codigo de periodo de cruce
    ps_cod_pais     :  Codigo de pais
    ps_cod_marca    :  Codigo de marca
    ps_cod_canal    :  Codigo de canal

  Autor             : Carla Marius
  ***************************************************************************/
  PROCEDURE EVI_PR_CARGA_FACTU_DETAL(PS_COD_PERI       IN VARCHAR2,
                                     PS_COD_PERI_CRUCE IN VARCHAR2,
                                     PS_COD_PAIS       IN VARCHAR2,
                                     PS_COD_MARCA      IN VARCHAR2,
                                     PS_COD_CANAL      IN VARCHAR2) IS

    CURSOR C_FACTURADOS IS
      SELECT INT_EVI_FACTU_DETAL_TEMPO.COD_PAIS,
             INT_EVI_FACTU_DETAL_TEMPO.COD_PERI,
             INT_EVI_FACTU_DETAL_TEMPO.COD_CLIE
        FROM CRA_PERIO,
             PED_SOLIC_CABEC,
             PED_TIPO_SOLIC_PAIS,
             SEG_PERIO_CORPO,
             SEG_PAIS,
             SEG_MARCA,
             SEG_CANAL,
             PED_TIPO_SOLIC,
             INT_EVI_FACTU_DETAL_TEMPO
       WHERE ((CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI) AND
             (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
             PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS) AND
             (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
             (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS) AND
             (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
             (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
             (PED_TIPO_SOLIC.OID_TIPO_SOLI =
             PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI) AND
             (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
             (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS)) AND
             ((PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 1) OR
             (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 5)) AND
             (PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC = 5) AND
             (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1) AND
             (PED_SOLIC_CABEC.IND_OC = 1) AND
             (PED_SOLIC_CABEC.FEC_FACT IS NOT NULL) AND
             (SEG_PAIS.COD_PAIS = PS_COD_PAIS) AND
             (SEG_MARCA.COD_MARC = PS_COD_MARCA) AND
             (SEG_CANAL.COD_CANA = PS_COD_CANAL) AND
             (SEG_PAIS.COD_PAIS = INT_EVI_FACTU_DETAL_TEMPO.COD_PAIS) AND
             (SEG_PERIO_CORPO.COD_PERI =
             INT_EVI_FACTU_DETAL_TEMPO.COD_PERI) AND
             (PED_SOLIC_CABEC.CLIE_OID_CLIE =
             INT_EVI_FACTU_DETAL_TEMPO.OID_CLIE));

    R_FACTURADOS C_FACTURADOS%ROWTYPE;

    FEC_MAX_FACT DATE;
    l_user  VARCHAR2(20);
  BEGIN
    l_user := USER;

    -- primero limpiar toda la tabla
    EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_EVI_FACTU_DETAL_TEMPO';

    -- Insertamos los detalles considerando tambien los activos
    -- para los casos en que un pedido fue inicialmente rechazado
    INSERT INTO INT_EVI_FACTU_DETAL_TEMPO
      (COD_PAIS,
       COD_PERI,
       OID_CLIE,
       COD_CLIE,
       COD_REGI,
       COD_ZONA,
       VAL_SALD,
       COD_MOTI_RECH,
       OID_ESTA_SOLI,
       FEC_PROG_FACT)
      SELECT SEG_PAIS.COD_PAIS,
             SEG_PERIO_CORPO.COD_PERI,
             MAE_CLIEN.OID_CLIE,
             MAE_CLIEN.COD_CLIE,
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MAE_CLIEN.COD_CLIE,
                                              'COD_REGI'),
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MAE_CLIEN.COD_CLIE,
                                              'COD_ZONA'),
             (EVI_PKG_EJECU_VIRTU.EVI_FN_CALCU_VALOR_SALDO_DEUD2(MAE_CLIEN.COD_CLIE)) AS SALDO_CONSULTORA,
             (CASE
               WHEN ((MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 1 OR
                    MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 2) AND
                    PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3) THEN
                ('+')
               ELSE
                CASE
               WHEN (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3) THEN
                ('D')
               ELSE
                ('-')
             END END),
             PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI,
             PED_SOLIC_CABEC.FEC_PROG_FACT
        FROM PED_SOLIC_CABEC,
             PED_TIPO_SOLIC_PAIS,
             MAE_CLIEN,
             MAE_CLIEN_DATOS_ADICI,
             PED_TIPO_SOLIC,
             SEG_CANAL,
             SEG_MARCA,
             SEG_PAIS,
             SEG_PERIO_CORPO,
             CRA_PERIO
       WHERE (PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
         AND (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
             PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS)
         AND (PED_TIPO_SOLIC.OID_TIPO_SOLI =
             PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI)
         AND (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
             (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS))
         AND (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 2 OR
             PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3)
         AND (MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
         AND (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
         AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
         AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
         AND (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS)
         AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
         AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
         AND (MAE_CLIEN_DATOS_ADICI.IND_ACTI = 1) -- Se envian solamente las consultoras activas
         AND (SEG_PAIS.COD_PAIS = PS_COD_PAIS)
         AND (SEG_MARCA.COD_MARC = PS_COD_MARCA)
         AND (SEG_CANAL.COD_CANA = PS_COD_CANAL)
         AND (SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI OR
             (PS_COD_PERI_CRUCE IS NOT NULL AND
             SEG_PERIO_CORPO.COD_PERI = PS_COD_PERI_CRUCE));

    -- Incluimos los registros rechazados por deuda de la tabla INT_SOLIC_CONSO_CABEC
    INSERT INTO INT_EVI_FACTU_DETAL_TEMPO
      (COD_PAIS,
       COD_PERI,
       COD_CLIE,
       COD_REGI,
       COD_ZONA,
       VAL_SALD,
       COD_MOTI_RECH,
       OID_ESTA_SOLI,
       FEC_PROG_FACT)
      SELECT A.COD_PAIS,
             A.COD_PERI,
             A.COD_CLIE,
             A.COD_REGI,
             A.COD_ZONA,
             A.VAL_SALD_DEUD,
             'D', -- Codigo de motivo de rechazo por deuda
             3, -- Usamos el OID de los rechazados por deuda
             A.FEC_SOLI
        FROM INT_SOLIC_CONSO_CABEC A
       WHERE (A.IND_ERRO_DEUD = '2')
         AND (A.IND_ADMI_CART = '0')
         AND (A.IND_OCS_PROC = '0')
         AND (A.IND_ERROR_SGPE = '0')
         AND (A.IND_ERRO_REMP = '0')
         AND (A.IND_CONT_ACT = '0') -- Se envian solo las activas
         AND NOT EXISTS
       (SELECT B.COD_PERI
                FROM INT_EVI_FACTU_DETAL_TEMPO B
               WHERE B.COD_PAIS = A.COD_PAIS
                 AND B.COD_CLIE = A.COD_CLIE
                 AND B.COD_PERI = A.COD_PERI)
         AND (A.COD_PERI = PS_COD_PERI OR (PS_COD_PERI_CRUCE IS NOT NULL AND
             A.COD_PERI = PS_COD_PERI_CRUCE));

    DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'INT_EVI_FACTU_DETAL_TEMPO', CASCADE => TRUE );

    -- Eliminamos a las consultoras que tiene una solicitud con status valido y que esten en
    -- el periodo correspondiente
    OPEN C_FACTURADOS;
    LOOP
      FETCH C_FACTURADOS
        INTO R_FACTURADOS;
      EXIT WHEN C_FACTURADOS%NOTFOUND;
      BEGIN
        DELETE FROM INT_EVI_FACTU_DETAL_TEMPO
         WHERE COD_PAIS = R_FACTURADOS.COD_PAIS
           AND COD_PERI = R_FACTURADOS.COD_PERI
           AND COD_CLIE = R_FACTURADOS.COD_CLIE;
      END;

    END LOOP;
    CLOSE C_FACTURADOS;

    -- Obtenemos la fecha maxima de facturacion
    SELECT MAX(FEC_PROG_FACT)
      INTO FEC_MAX_FACT
      FROM INT_EVI_FACTU_DETAL_TEMPO
     WHERE COD_PAIS = PS_COD_PAIS
       AND COD_PERI = PS_COD_PERI;

    -- Eliminamos aquellos detalles que no corresponden a la fecha
    -- maxima de facturacion programada
    IF FEC_MAX_FACT IS NOT NULL THEN
      DELETE FROM INT_EVI_FACTU_DETAL_TEMPO
       WHERE FEC_PROG_FACT <> FEC_MAX_FACT
         AND COD_PAIS = PS_COD_PAIS
         AND COD_PERI = PS_COD_PERI
         AND COD_MOTI_RECH <> 'D';
    END IF;

    -- Hacemos lo mismo en caso haya periodo de cruce
    IF PS_COD_PERI_CRUCE IS NOT NULL THEN
      FEC_MAX_FACT := NULL;
      -- Obtenemos la fecha maxima de facturacion
      SELECT MAX(FEC_PROG_FACT)
        INTO FEC_MAX_FACT
        FROM INT_EVI_FACTU_DETAL_TEMPO
       WHERE COD_PAIS = PS_COD_PAIS
         AND COD_PERI = PS_COD_PERI_CRUCE;

      -- Eliminamos aquellos detalles que no corresponden a la fecha
      -- maxima de facturacion programada
      IF FEC_MAX_FACT IS NOT NULL THEN
        DELETE FROM INT_EVI_FACTU_DETAL_TEMPO
         WHERE FEC_PROG_FACT <> FEC_MAX_FACT
           AND COD_PAIS = PS_COD_PAIS
           AND COD_PERI = PS_COD_PERI_CRUCE
           AND COD_MOTI_RECH <> 'D';
      END IF;

    END IF;

  END EVI_PR_CARGA_FACTU_DETAL;

  /**************************************************************************
  Descripcion       : Devuelve el numero de pedidos estimados de INT_FUENT_VENTA_PREVI_SAP
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
      ps_cod_regi   : Codigo de region
      ps_cod_zona   : Codigo de zona
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  FUNCTION EVI_FN_OBTEN_NUMER_PEDID_ESTIM(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2)
    RETURN NUMBER IS
    RES NUMBER := 0;
  BEGIN
    -- Anexo 1 y 2: Acceder con periodo, region y zona
    IF (PS_COD_PERI IS NOT NULL AND PS_COD_REGI IS NOT NULL AND
       PS_COD_ZONA IS NOT NULL) THEN
      SELECT SUM(NVL(I.NUM_PEDI, 0))
        INTO RES
        FROM INT_FUENT_VENTA_PREVI_SAP I,
             ZON_REGIO                 R,
             ZON_ZONA                  Z,
             CRA_PERIO                 C,
             SEG_PERIO_CORPO           P
       WHERE ((R.OID_REGI = I.ZORG_OID_REGI) AND
             (R.OID_REGI = Z.ZORG_OID_REGI) AND
             (Z.OID_ZONA = I.ZZON_OID_ZONA) AND
             (C.OID_PERI = I.PERD_OID_PERI) AND
             (P.OID_PERI = C.PERI_OID_PERI) AND (PS_COD_REGI = R.COD_REGI) AND
             (PS_COD_ZONA = Z.COD_ZONA) AND (PS_COD_PERI = P.COD_PERI));

      -- Anexo 3 y 4: Agrupar por periodo y region y sumar los pedidos
    ELSIF (PS_COD_PERI IS NOT NULL AND PS_COD_REGI IS NOT NULL) THEN
      SELECT SUM(NVL(I.NUM_PEDI, 0))
        INTO RES
        FROM INT_FUENT_VENTA_PREVI_SAP I,
             ZON_REGIO                 R,
             CRA_PERIO                 C,
             SEG_PERIO_CORPO           P
       WHERE ((R.OID_REGI = I.ZORG_OID_REGI) AND
             (C.OID_PERI = I.PERD_OID_PERI) AND
             (P.OID_PERI = C.PERI_OID_PERI) AND (PS_COD_PERI = P.COD_PERI) AND
             (PS_COD_REGI = R.COD_REGI));

    END IF;

    IF RES IS NULL THEN
      RES := 0;
    END IF;

    RETURN RES;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RES := 0;
    WHEN OTHERS THEN
      RES := 0;
      RETURN RES;
  END EVI_FN_OBTEN_NUMER_PEDID_ESTIM;

  /*
  */
  FUNCTION EVI_FN_OBTEN_NUMER_PEDID_REC(PS_COD_PERI IN VARCHAR2,
                                        PS_COD_REGI IN VARCHAR2,
                                        PS_COD_ZONA IN VARCHAR2,
                                        PS_FECHA    IN DATE,
                                        PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER IS
    NOCS NUMBER := 0;
    NPPE NUMBER := 0;
    RES  NUMBER := 0;
    FLAG NUMBER := 0;
  BEGIN

    -- Anexo 1: Filtrar por zona y fecha
    IF ((PS_COD_ZONA IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN

      -- Calculamos el numero de OCS
      SELECT COUNT(1)
        INTO NOCS
        FROM (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_ZONA,
                     TIP_ORDE,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_ZONA, TIP_ORDE) R
       WHERE R.COD_ZONA = PS_COD_ZONA
         AND R.FEC_SOLI = PS_FECHA
         AND R.NUM_LOTE = PS_NUM_LOTE
         AND R.TIP_ORDE <> 'P'
       GROUP BY R.COD_ZONA, R.COD_PERI, R.FEC_SOLI, R.NUM_LOTE;

      -- Calculamos el numero de primeros pedidos
      SELECT COUNT(1)
        INTO FLAG
        FROM INT_EVI_RECEP_PRIME_PEDID
       WHERE COD_ZONA = PS_COD_ZONA
         AND FEC_RECE = PS_FECHA
         AND NUM_LOTE = PS_NUM_LOTE;

      IF FLAG <> 0 THEN
        SELECT NVL(SUM(NUM_PRIM_PEDI), 0)
          INTO NPPE
          FROM INT_EVI_RECEP_PRIME_PEDID
         WHERE COD_ZONA = PS_COD_ZONA
           AND FEC_RECE = PS_FECHA
           AND NUM_LOTE = PS_NUM_LOTE;
      END IF;

      RES := NOCS + NPPE;

      -- Anexo 2: Filtrar por periodo y zona
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL)) THEN

      -- calculamos el numero de OCS
      SELECT COUNT(1)
        INTO NOCS
        FROM (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_ZONA,
                     TIP_ORDE,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_ZONA, TIP_ORDE) R
       WHERE R.COD_PERI = PS_COD_PERI
         AND R.COD_ZONA = PS_COD_ZONA
         AND R.TIP_ORDE <> 'P'
       GROUP BY R.COD_PERI, R.COD_ZONA;

      -- Calculamos el numero de primeros pedidos
      SELECT COUNT(1)
        INTO FLAG
        FROM INT_EVI_RECEP_PRIME_PEDID
       WHERE COD_ZONA = PS_COD_ZONA
         AND COD_PERI = PS_COD_PERI;

      IF FLAG <> 0 THEN
        SELECT NVL(SUM(NUM_PRIM_PEDI), 0)
          INTO NPPE
          FROM INT_EVI_RECEP_PRIME_PEDID
         WHERE COD_ZONA = PS_COD_ZONA
           AND COD_PERI = PS_COD_PERI;
      END IF;

      RES := NOCS + NPPE;

      -- Anexo 3: Filtrar por region y fecha
    ELSIF ((PS_COD_REGI IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO NOCS
        FROM (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_REGI,
                     TIP_ORDE,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_REGI, TIP_ORDE) R
       WHERE R.COD_REGI = PS_COD_REGI
         AND R.FEC_SOLI = PS_FECHA
         AND R.NUM_LOTE = PS_NUM_LOTE
         AND R.TIP_ORDE <> 'P'
       GROUP BY R.COD_REGI, R.COD_PERI, R.FEC_SOLI, R.NUM_LOTE;

      SELECT COUNT(1)
        INTO FLAG
        FROM INT_EVI_RECEP_PRIME_PEDID P, ZON_REGIO R, ZON_ZONA Z
       WHERE P.COD_ZONA = Z.COD_ZONA
         AND Z.ZORG_OID_REGI = R.OID_REGI
         AND R.COD_REGI = PS_COD_REGI
         AND P.FEC_RECE = PS_FECHA;

      IF FLAG <> 0 THEN
        SELECT NVL(SUM(NUM_PRIM_PEDI), 0)
          INTO NPPE
          FROM INT_EVI_RECEP_PRIME_PEDID P, ZON_REGIO R, ZON_ZONA Z
         WHERE P.COD_ZONA = Z.COD_ZONA
           AND Z.ZORG_OID_REGI = R.OID_REGI
           AND R.COD_REGI = PS_COD_REGI
           AND P.FEC_RECE = PS_FECHA
           AND P.NUM_LOTE = PS_NUM_LOTE;
      END IF;

      RES := NOCS + NPPE;

      -- Anexo 4: Filtrar por periodo y region
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_REGI IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO NOCS
        FROM (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_REGI,
                     TIP_ORDE,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_REGI, TIP_ORDE) R
       WHERE R.COD_PERI = PS_COD_PERI
         AND R.COD_REGI = PS_COD_REGI
         AND R.TIP_ORDE <> 'P'
       GROUP BY R.COD_PERI, R.COD_REGI;

      SELECT COUNT(1)
        INTO FLAG
        FROM INT_EVI_RECEP_PRIME_PEDID P, ZON_REGIO R, ZON_ZONA Z
       WHERE P.COD_ZONA = Z.COD_ZONA
         AND Z.ZORG_OID_REGI = R.OID_REGI
         AND R.COD_REGI = PS_COD_REGI
         AND P.COD_PERI = PS_COD_PERI;

      IF FLAG <> 0 THEN
        SELECT NVL(SUM(NUM_PRIM_PEDI), 0)
          INTO NPPE
          FROM INT_EVI_RECEP_PRIME_PEDID P, ZON_REGIO R, ZON_ZONA Z
         WHERE P.COD_ZONA = Z.COD_ZONA
           AND Z.ZORG_OID_REGI = R.OID_REGI
           AND R.COD_REGI = PS_COD_REGI
           AND P.COD_PERI = PS_COD_PERI;
      END IF;

      RES := NOCS + NPPE;
    END IF; -- End de los anexos

    IF RES IS NULL THEN
      RES := 0;
    END IF;

    RETURN RES;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RES := 0;
      RETURN RES;

  END EVI_FN_OBTEN_NUMER_PEDID_REC;

  /**************************************************************************
  Descripcion       : Devuelve el numero de pedidos reales obtenidos de INT_SOLIC_CONSO_CABEC
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
      ps_cod_regi   : Codigo de region
      ps_cod_zona   : Codigo de zona
      ps_fecha      : Fecha
  Autor             : Lennon Shimokawa
  Modificado        : Carlos Hurtado
  Cambios           : Se cambio el concepto de numero de pedidos reales de
                      acuerdo a documento enviado por Otto Paredes el 12/03/2007
       (13/03/2007)
  ***************************************************************************/
  FUNCTION EVI_FN_OBTEN_NUMER_PEDID_REAL(PS_COD_PERI IN VARCHAR2,
                                         PS_COD_REGI IN VARCHAR2,
                                         PS_COD_ZONA IN VARCHAR2,
                                         PS_FECHA    IN DATE,
                                         PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER IS
    RES NUMBER := 0;
  BEGIN

    -- Anexo 1: Filtrar por zona y fecha
    IF ((PS_COD_ZONA IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_ZONA,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_ZONA) R
       WHERE R.COD_ZONA = PS_COD_ZONA
         AND R.FEC_SOLI = PS_FECHA
         AND R.NUM_LOTE = PS_NUM_LOTE
       GROUP BY R.COD_ZONA, R.COD_PERI, R.FEC_SOLI, R.NUM_LOTE;
      -- Anexo 2: Filtrar por periodo y zona
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_ZONA,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_ZONA) R
       WHERE R.COD_PERI = PS_COD_PERI
         AND R.COD_ZONA = PS_COD_ZONA
       GROUP BY R.COD_PERI, R.COD_ZONA;
      -- Anexo 3: Filtrar por region y fecha
    ELSIF ((PS_COD_REGI IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_REGI,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_REGI) R
       WHERE R.COD_REGI = PS_COD_REGI
         AND R.FEC_SOLI = PS_FECHA
         AND R.NUM_LOTE = PS_NUM_LOTE
       GROUP BY R.COD_REGI, R.COD_PERI, R.FEC_SOLI, R.NUM_LOTE;
      -- Anexo 4: Filtrar por periodo y region
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_REGI IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_REGI,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_REGI) R
       WHERE R.COD_PERI = PS_COD_PERI
         AND R.COD_REGI = PS_COD_REGI
       GROUP BY R.COD_PERI, R.COD_REGI;
    END IF;

    IF RES IS NULL THEN
      RES := 0;
    END IF;

    RETURN RES;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RES := 0;
      RETURN RES;

  END EVI_FN_OBTEN_NUMER_PEDID_REAL;

  /**************************************************************************
  Descripcion       : Devuelve el numero de OCS 1 por consultora cuya fecha de
                      solicitud es igual a al fecha programada de facturacion.
  Fecha Creacion    : 13/03/2007
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
      ps_cod_regi   : Codigo de region
      ps_cod_zona   : Codigo de zona
      ps_fecha      : Fecha
  Autor             : Carlos Hurtado
  ***************************************************************************/
  FUNCTION EVI_FN_OBTEN_TOTAL_FACTU_REGUL(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2,
                                          PS_FECHA    IN DATE,
                                          PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER IS
    RES NUMBER := 0;
  BEGIN

    -- Anexo 1: Filtrar por zona y fecha
    IF ((PS_COD_ZONA IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_ZONA,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_ZONA) R
       WHERE A.COD_PAIS = R.COD_PAIS
         AND A.COD_PERI = R.COD_PERI
         AND A.COD_CLIE = R.COD_CLIE
         AND A.NUM_LOTE = R.NUM_LOTE
         AND A.FEC_SOLI = A.FEC_PROG_FACT
         AND A.IND_PROC_GP2 = '1'
         AND A.COD_ZONA = PS_COD_ZONA
         AND A.FEC_SOLI = PS_FECHA
         AND A.NUM_LOTE = PS_NUM_LOTE
       GROUP BY A.COD_ZONA, A.COD_PERI, A.FEC_SOLI, A.NUM_LOTE;
      -- Anexo 2: Filtrar por periodo y zona
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_ZONA,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_ZONA) R
       WHERE A.COD_PAIS = R.COD_PAIS
         AND A.COD_PERI = R.COD_PERI
         AND A.COD_CLIE = R.COD_CLIE
         AND A.NUM_LOTE = R.NUM_LOTE
         AND A.FEC_SOLI = A.FEC_PROG_FACT
         AND A.IND_PROC_GP2 = '1'
         AND A.COD_PERI = PS_COD_PERI
         AND A.COD_ZONA = PS_COD_ZONA
       GROUP BY A.COD_PERI, A.COD_ZONA;
      -- Anexo 3: Filtrar por region y fecha
    ELSIF ((PS_COD_REGI IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_REGI,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_REGI) R
       WHERE A.COD_PAIS = R.COD_PAIS
         AND A.COD_PERI = R.COD_PERI
         AND A.COD_CLIE = R.COD_CLIE
         AND A.NUM_LOTE = R.NUM_LOTE
         AND A.FEC_SOLI = A.FEC_PROG_FACT
         AND A.IND_PROC_GP2 = '1'
         AND A.COD_REGI = PS_COD_REGI
         AND A.FEC_SOLI = PS_FECHA
         AND A.NUM_LOTE = PS_NUM_LOTE
       GROUP BY A.COD_REGI, A.COD_PERI, A.FEC_SOLI, A.NUM_LOTE;
      -- Anexo 4: Filtrar por periodo y region
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_REGI IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_REGI,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_REGI) R
       WHERE A.COD_PAIS = R.COD_PAIS
         AND A.COD_PERI = R.COD_PERI
         AND A.COD_CLIE = R.COD_CLIE
         AND A.NUM_LOTE = R.NUM_LOTE
         AND A.FEC_SOLI = A.FEC_PROG_FACT
         AND A.IND_PROC_GP2 = '1'
         AND A.COD_PERI = PS_COD_PERI
         AND A.COD_REGI = PS_COD_REGI
       GROUP BY A.COD_PERI, A.COD_REGI;
    END IF;

    IF RES IS NULL THEN
      RES := 0;
    END IF;

    RETURN RES;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RES := 0;
      RETURN RES;

  END EVI_FN_OBTEN_TOTAL_FACTU_REGUL;

  /**************************************************************************
  Descripcion       : Devuelve el numero de OCS 1 por consultora cuya fecha de
                      solicitud es diferente a la fecha programada de facturacion.
  Fecha Creacion    : 13/03/2007
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
      ps_cod_regi   : Codigo de region
      ps_cod_zona   : Codigo de zona
      ps_fecha      : Fecha
  Autor             : Carlos Hurtado
  ***************************************************************************/
  FUNCTION EVI_FN_OBTEN_TOTAL_FACTU_BOLSA(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2,
                                          PS_FECHA    IN DATE,
                                          PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER IS
    RES NUMBER := 0;
  BEGIN

    -- Anexo 1: Filtrar por zona y fecha
    IF ((PS_COD_ZONA IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_ZONA,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_ZONA) R
       WHERE A.COD_PAIS = R.COD_PAIS
         AND A.COD_PERI = R.COD_PERI
         AND A.COD_CLIE = R.COD_CLIE
         AND A.NUM_LOTE = R.NUM_LOTE
         AND A.FEC_SOLI <> A.FEC_PROG_FACT
         AND A.IND_PROC_GP2 = '1'
         AND A.COD_ZONA = PS_COD_ZONA
         AND A.FEC_SOLI = PS_FECHA
         AND A.NUM_LOTE = PS_NUM_LOTE
       GROUP BY A.COD_ZONA, A.FEC_SOLI, A.NUM_LOTE;
      -- Anexo 2: Filtrar por periodo y zona
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_ZONA,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_ZONA) R
       WHERE A.COD_PAIS = R.COD_PAIS
         AND A.COD_PERI = R.COD_PERI
         AND A.COD_CLIE = R.COD_CLIE
         AND A.NUM_LOTE = R.NUM_LOTE
         AND A.FEC_SOLI <> A.FEC_PROG_FACT
         AND A.IND_PROC_GP2 = '1'
         AND A.COD_PERI = PS_COD_PERI
         AND A.COD_ZONA = PS_COD_ZONA
       GROUP BY A.COD_PERI, A.COD_ZONA;
      -- Anexo 3: Filtrar por region y fecha
    ELSIF ((PS_COD_REGI IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_REGI,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_REGI) R
       WHERE A.COD_PAIS = R.COD_PAIS
         AND A.COD_PERI = R.COD_PERI
         AND A.COD_CLIE = R.COD_CLIE
         AND A.NUM_LOTE = R.NUM_LOTE
         AND A.FEC_SOLI <> A.FEC_PROG_FACT
         AND A.IND_PROC_GP2 = '1'
         AND A.COD_REGI = PS_COD_REGI
         AND A.FEC_SOLI = PS_FECHA
         AND A.NUM_LOTE = PS_NUM_LOTE
       GROUP BY A.COD_REGI, A.COD_PERI, A.FEC_SOLI, A.NUM_LOTE;
      -- Anexo 4: Filtrar por periodo y region
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_REGI IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC A,
             (SELECT COD_PAIS,
                     COD_PERI,
                     COD_CLIE,
                     COD_REGI,
                     MIN(FEC_SOLI) FEC_SOLI,
                     MIN(NUM_LOTE) NUM_LOTE
                FROM INT_SOLIC_CONSO_CABEC
               GROUP BY COD_PAIS, COD_PERI, COD_CLIE, COD_REGI) R
       WHERE A.COD_PAIS = R.COD_PAIS
         AND A.COD_PERI = R.COD_PERI
         AND A.COD_CLIE = R.COD_CLIE
         AND A.NUM_LOTE = R.NUM_LOTE
         AND A.FEC_SOLI <> A.FEC_PROG_FACT
         AND A.IND_PROC_GP2 = '1'
         AND A.COD_PERI = PS_COD_PERI
         AND A.COD_REGI = PS_COD_REGI
       GROUP BY A.COD_PERI, A.COD_REGI;
    END IF;

    IF RES IS NULL THEN
      RES := 0;
    END IF;

    RETURN RES;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RES := 0;
      RETURN RES;

  END EVI_FN_OBTEN_TOTAL_FACTU_BOLSA;

  /**************************************************************************
  Descripcion       : Devuelve el numero de pedidos rechazados obtenidos de INT_SOLIC_CONSO_CABEC
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
      ps_cod_regi   : Codigo de region
      ps_cod_zona   : Codigo de zona
      ps_fecha      : Fecha
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  FUNCTION EVI_FN_OBTEN_NUMER_PEDID_RECHA(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2,
                                          PS_FECHA    IN DATE,
                                          PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER IS
    RES NUMBER := 0;
  BEGIN
    -- Anexo 1: Filtrar por zona y fecha
    IF ((PS_COD_ZONA IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC R
       WHERE R.COD_ZONA = PS_COD_ZONA
         AND R.FEC_SOLI = PS_FECHA
         AND R.NUM_LOTE = PS_NUM_LOTE
         AND R.IND_ERRO_DEUD = '2'
         AND R.IND_ERRO_REMP = 0
         AND R.IND_ERROR_SGPE = 0
         AND R.IND_ADMI_CART <> 1
       GROUP BY R.COD_ZONA, R.COD_PERI, R.FEC_SOLI, PS_NUM_LOTE;

      -- Anexo 2: Filtrar por periodo y zona
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC R
       WHERE R.COD_PERI = PS_COD_PERI
         AND R.COD_ZONA = PS_COD_ZONA
         AND R.IND_ERRO_DEUD = '2'
         AND R.IND_ERRO_REMP = 0
         AND R.IND_ERROR_SGPE = 0
         AND R.IND_ADMI_CART <> 1
       GROUP BY R.COD_PERI, R.COD_ZONA;

      -- Anexo 3: Filtrar por region y fecha
    ELSIF ((PS_COD_REGI IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC R
       WHERE R.COD_REGI = PS_COD_REGI
         AND R.FEC_SOLI = PS_FECHA
         AND R.NUM_LOTE = PS_NUM_LOTE
         AND R.IND_ERRO_DEUD = '2'
         AND R.IND_ERRO_REMP = 0
         AND R.IND_ERROR_SGPE = 0
         AND R.IND_ADMI_CART <> 1
       GROUP BY R.COD_REGI, R.COD_PERI, R.FEC_SOLI, R.NUM_LOTE;

      -- Anexo 4: Filtrar por periodo y region
    ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_REGI IS NOT NULL)) THEN
      SELECT COUNT(1)
        INTO RES
        FROM INT_SOLIC_CONSO_CABEC R
       WHERE R.COD_PERI = PS_COD_PERI
         AND R.COD_REGI = PS_COD_REGI
         AND R.IND_ERRO_DEUD = '2'
         AND R.IND_ERRO_REMP = 0
         AND R.IND_ERROR_SGPE = 0
         AND R.IND_ADMI_CART <> 1
       GROUP BY R.COD_PERI, R.COD_REGI;

    END IF;

    IF RES IS NULL THEN
      RES := 0;
    END IF;

    RETURN RES;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RES := 0;
      RETURN RES;
  END EVI_FN_OBTEN_NUMER_PEDID_RECHA;

  /**************************************************************************
  Descripcion       : Devuelve el numero de primeros pedidos obtenidos de INT_EVI_RECEP_PRIME_PEDID
  Fecha Creacion    : 17/10/2006
  Parametros Entrada:
      ps_cod_peri   : Codigo de periodo
      ps_cod_regi   : Codigo de region
      ps_cod_zona   : Codigo de zona
      ps_fecha      : Fecha
  Autor             : Lennon Shimokawa
  ***************************************************************************/
  FUNCTION EVI_FN_OBTEN_NUMER_PRIME_PEDID(PS_COD_PERI IN VARCHAR2,
                                          PS_COD_REGI IN VARCHAR2,
                                          PS_COD_ZONA IN VARCHAR2,
                                          PS_FECHA    IN DATE,
                                          PS_NUM_LOTE IN VARCHAR2)
    RETURN NUMBER IS
    RES NUMBER;
    v_ind_actua_prime_pedi  bas_pais.ind_actu_indi_prim_pedi%type;
  BEGIN
    --obtengo el indicador
    select p.ind_actu_indi_prim_pedi
      into v_ind_actua_prime_pedi
      from bas_pais p
     where p.cod_pais = (select distinct cod_pais from int_solic_conso_cabec);

    if v_ind_actua_prime_pedi = 'S' then
       -- Anexo 1: Filtrar por periodo, zona y fecha
        IF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL) AND
           (PS_FECHA IS NOT NULL)) THEN
          SELECT count(1)
            INTO RES
            FROM Int_Solic_Conso_Cabec r
           WHERE R.COD_PERI = PS_COD_PERI
             AND R.COD_ZONA = PS_COD_ZONA
             AND R.FEC_PROG_FACT = PS_FECHA
             AND R.NUM_LOTE = PS_NUM_LOTE
             and r.tip_orde = 'P';

          -- Anexos 2, 3, 4: Se agrupan los pedidos
        ELSE
          -- Anexo 2: Agrupar por periodo y zona
          IF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL)) THEN
            SELECT count(1)
              INTO RES
              FROM Int_Solic_Conso_Cabec P
             WHERE P.COD_PERI = PS_COD_PERI
               AND P.Cod_Zona = PS_COD_ZONA
               and P.tip_orde = 'P'
             GROUP BY P.COD_PERI, P.COD_ZONA ;

            -- Anexo 3: Agrupar por region y fecha
          ELSIF ((PS_COD_REGI IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
            SELECT count(1)
              INTO RES
              FROM INT_solic_conso_cabec P,
                   ZON_REGIO R,
                   ZON_ZONA Z
             WHERE P.COD_ZONA = Z.COD_ZONA
               AND Z.ZORG_OID_REGI = R.OID_REGI
               AND R.COD_REGI = PS_COD_REGI
               AND P.COD_PERI = PS_COD_PERI
               AND P.FEC_PROG_FACT = PS_FECHA
               AND P.NUM_LOTE = PS_NUM_LOTE
               and P.tip_orde = 'P'
             GROUP BY R.COD_REGI, P.COD_PERI, P.FEC_PROG_FACT, P.NUM_LOTE;

            -- Anexo 4: Agrupar por periodo y region
          ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_REGI IS NOT NULL)) THEN
            SELECT count(1)
              INTO RES
              FROM Int_Solic_Conso_Cabec P,
                   ZON_REGIO R,
                   ZON_ZONA Z
             WHERE P.COD_ZONA = Z.COD_ZONA
               AND Z.ZORG_OID_REGI = R.OID_REGI
               AND P.COD_PERI = PS_COD_PERI
               AND R.COD_REGI = PS_COD_REGI
               and P.tip_orde = 'P'
             GROUP BY P.COD_PERI, R.COD_REGI             ;
          END IF;
        END IF;
    else
    -- Anexo 1: Filtrar por periodo, zona y fecha
    IF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL) AND
       (PS_FECHA IS NOT NULL)) THEN
      SELECT SUM(R.NUM_PRIM_PEDI)
        INTO RES
        FROM INT_EVI_RECEP_PRIME_PEDID R
       WHERE R.COD_PERI = PS_COD_PERI
         AND R.COD_ZONA = PS_COD_ZONA
         AND R.FEC_RECE = PS_FECHA
         AND R.NUM_LOTE = PS_NUM_LOTE;

      -- Anexos 2, 3, 4: Se agrupan los pedidos
    ELSE
      -- Anexo 2: Agrupar por periodo y zona
      IF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_ZONA IS NOT NULL)) THEN
        SELECT SUM(P.NUM_PRIM_PEDI)
          INTO RES
          FROM INT_EVI_RECEP_PRIME_PEDID P
         WHERE P.COD_PERI = PS_COD_PERI
           AND P.COD_ZONA = PS_COD_ZONA
         GROUP BY P.COD_PERI, P.COD_ZONA;

        -- Anexo 3: Agrupar por region y fecha
      ELSIF ((PS_COD_REGI IS NOT NULL) AND (PS_FECHA IS NOT NULL)) THEN
        SELECT SUM(P.NUM_PRIM_PEDI)
          INTO RES
          FROM INT_EVI_RECEP_PRIME_PEDID P, ZON_REGIO R, ZON_ZONA Z
         WHERE P.COD_ZONA = Z.COD_ZONA
           AND Z.ZORG_OID_REGI = R.OID_REGI
           AND R.COD_REGI = PS_COD_REGI
           AND P.COD_PERI = PS_COD_PERI
           AND P.FEC_RECE = PS_FECHA
           AND P.NUM_LOTE = PS_NUM_LOTE
         GROUP BY R.COD_REGI, P.COD_PERI, P.FEC_RECE, P.NUM_LOTE;

        -- Anexo 4: Agrupar por periodo y region
      ELSIF ((PS_COD_PERI IS NOT NULL) AND (PS_COD_REGI IS NOT NULL)) THEN
        SELECT SUM(P.NUM_PRIM_PEDI)
          INTO RES
          FROM INT_EVI_RECEP_PRIME_PEDID P, ZON_REGIO R, ZON_ZONA Z
         WHERE P.COD_ZONA = Z.COD_ZONA
           AND Z.ZORG_OID_REGI = R.OID_REGI
           AND P.COD_PERI = PS_COD_PERI
           AND R.COD_REGI = PS_COD_REGI
         GROUP BY P.COD_PERI, R.COD_REGI;
      END IF;
    END IF;
    end if;

    IF RES IS NULL THEN
      RES := 0;
    END IF;

    RETURN RES;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RES := 0;
      RETURN RES;
  END EVI_FN_OBTEN_NUMER_PRIME_PEDID;

  /**************************************************************************
  Descripcion       : Calcula el saldo deudor del cliente sin considerar valores de cupon
  Fecha Creacion    : 23/10/2006
  Parametros Entrada:
      ps_cod_clie   : Codigo de cliente
  Autor             : Carla Marius
  ***************************************************************************/
  FUNCTION EVI_FN_CALCU_VALOR_SALDO_DEUD2(PS_COD_CLIE IN VARCHAR2)
    RETURN NUMBER IS
    SALDO NUMBER;
  BEGIN
    WITH TEMP_MARCA AS(
      SELECT CCC_MARCA_TIPO_ABONO.MASI_OID_MARC_SALI
        FROM CCC_PROCE,
             CCC_SUBPR,
             CCC_TIPO_ABONO_SUBPR,
             CCC_MARCA_TIPO_ABONO
       WHERE ((CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC) AND
             (CCC_SUBPR.OID_SUBP = CCC_TIPO_ABONO_SUBPR.SUBP_OID_SUBP) AND
             (CCC_TIPO_ABONO_SUBPR.OID_TIPO_ABON_SUBP =
             CCC_MARCA_TIPO_ABONO.TASP_OID_TIPO_ABON_SUBP) AND
             (CCC_PROCE.COD_PROC = 'CON001') AND
             (CCC_MARCA_TIPO_ABONO.IND_ENTR_SALI = 'E')))
        SELECT SUM(CCC_MOVIM_CUENT_CORRI.IMP_PEND)
          INTO SALDO
          FROM CCC_MOVIM_CUENT_CORRI, MAE_CLIEN, TEMP_MARCA
         WHERE CCC_MOVIM_CUENT_CORRI.IMP_PEND <> 0
           AND CCC_MOVIM_CUENT_CORRI.CLIE_OID_CLIE =
               MAE_CLIEN.OID_CLIE
           AND SYSDATE - CCC_MOVIM_CUENT_CORRI.FEC_VENC > 0
           AND CCC_MOVIM_CUENT_CORRI.MASI_OID_MARC_SITU =
               TEMP_MARCA.MASI_OID_MARC_SALI
           AND COD_CLIE = PS_COD_CLIE;


    IF SALDO IS NULL THEN
      SALDO := 0;
    END IF;

    RETURN SALDO;
  END EVI_FN_CALCU_VALOR_SALDO_DEUD2;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Historico de Consultoras
  Fecha Creacion    : 14/01/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE EVI_PR_INT_HISTO_CONSU(PSCODIGOPAIS     VARCHAR2,
                                   PSCODIGOSISTEMA  VARCHAR2,
                                   PSCODIGOINTERFAZ VARCHAR2,
                                   PSNOMBREARCHIVO  VARCHAR2,
                                   PSCODIGOISO      VARCHAR2,
                                   PSCODIGOMARCA    VARCHAR2,
                                   PSCODIGOCANAL    VARCHAR2) IS
    searchStr  VARCHAR2(100) := 'a"'',;|' || CHR(10) || CHR(13) || CHR(20);
    replaceStr VARCHAR2(100) := 'a        ';

    --Cursor para el caso de que no exista cruce
    CURSOR C_INTERFAZ(VSCODIGOPERIODOACTUAL VARCHAR2) IS

      SELECT GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE, 'COD_ZONA') CODIGOZONA,
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE, 'COD_TERR') CODIGOTERRITORIO,
             CLIE.COD_CLIE CODIGOCONSULTORA,
             TRANSLATE(CLIE.VAL_APE1 || ' ' || CLIE.VAL_APE2 || ' ' || CLIE.VAL_NOM1 || ' ' ||
             CLIE.VAL_NOM2, searchStr, replaceStr) NOMBRECLIENTE,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_DOCUM_IDENT(CLIE.OID_CLIE) DOCUMENTOIDENTIDAD,
             NULL TELEFONO,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(DAAD.ESTA_OID_ESTA_CLIE,
                                                       'MAE_ESTAT_CLIEN',
                                                       PSCODIGOISO) SITUACION,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_DUPLA(CLIE.OID_CLIE) DUPLA,
             '' CODIGOVERIFICACION,
             GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_VENCI(CLIE.OID_CLIE,
                                                          GEN_PKG_GENER.GEN_FN_OBTIE_FECHA_VENCI(PSCODIGOPAIS,
                                                                                                 PSCODIGOMARCA,
                                                                                                 PSCODIGOCANAL,
                                                                                                 VSCODIGOPERIODOACTUAL,
                                                                                                 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE,
                                                                                                                                      'COD_REGION'),
                                                                                                 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE,
                                                                                                                                      'COD_ZONA'),
                                                                                                 CLIE.OID_CLIE)) SALDOCONSULTORA,
             GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_ULTIM_PEDID(CLIE.OID_CLIE) ULTIMOPERIODO,
             CLIE.OID_CLIE IDCLIENTE

        FROM MAE_CLIEN CLIE, MAE_CLIEN_DATOS_ADICI DAAD

       WHERE EXISTS (SELECT 1
                FROM MAE_CLIEN_TIPO_SUBTI TCLI, MAE_TIPO_CLIEN TIPO
               WHERE TIPO.COD_TIPO_CLIE = '02'
                 AND TIPO.OID_TIPO_CLIE = TCLI.TICL_OID_TIPO_CLIE
                 AND CLIE.OID_CLIE = TCLI.CLIE_OID_CLIE)
         AND DAAD.CLIE_OID_CLIE = CLIE.OID_CLIE;

    CURSOR C_INTERFAZ2(VSCODIGOPERIODOACTUAL VARCHAR2, LSCODIGOPERIODOCRUCE VARCHAR2, VNIDMARCA NUMBER, VNIDCANAL NUMBER) IS

      SELECT GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE, 'COD_ZONA') CODIGOZONA,
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE, 'COD_TERR') CODIGOTERRITORIO,
             CLIE.COD_CLIE CODIGOCONSULTORA,
             TRANSLATE(TRIM(CLIE.VAL_APE1) || ' ' || TRIM(CLIE.VAL_APE2) || ' ' ||
             TRIM(CLIE.VAL_NOM1) || ' ' || TRIM(CLIE.VAL_NOM2), searchStr, replaceStr) NOMBRECLIENTE,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_DOCUM_IDENT(CLIE.OID_CLIE) DOCUMENTOIDENTIDAD,
             NULL TELEFONO,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(DAAD.ESTA_OID_ESTA_CLIE,
                                                       'MAE_ESTAT_CLIEN',
                                                       PSCODIGOISO) SITUACION,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_DUPLA(CLIE.OID_CLIE) DUPLA,
             '' CODIGOVERIFICACION,
             GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_VENCI(CLIE.OID_CLIE,
                                                          GEN_PKG_GENER.GEN_FN_OBTIE_FECHA_VENCI(PSCODIGOPAIS,
                                                                                                 PSCODIGOMARCA,
                                                                                                 PSCODIGOCANAL,
                                                                                                 EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_PERIO_FACTU(VSCODIGOPERIODOACTUAL,
                                                                                                                                              LSCODIGOPERIODOCRUCE,
                                                                                                                                              GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE,
                                                                                                                                                                                   'COD_ZONA'),
                                                                                                                                              VNIDMARCA,
                                                                                                                                              VNIDCANAL),
                                                                                                 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE,
                                                                                                                                      'COD_REGION'),
                                                                                                 GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CLIE.OID_CLIE,
                                                                                                                                      'COD_ZONA'),
                                                                                                 CLIE.OID_CLIE)) SALDOCONSULTORA,
             GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_ULTIM_PEDID(CLIE.OID_CLIE) ULTIMOPERIODO,
             CLIE.OID_CLIE IDCLIENTE

        FROM MAE_CLIEN CLIE, MAE_CLIEN_DATOS_ADICI DAAD

       WHERE EXISTS (SELECT 1
                FROM MAE_CLIEN_TIPO_SUBTI TCLI, MAE_TIPO_CLIEN TIPO
               WHERE TIPO.COD_TIPO_CLIE = '02'
                 AND TIPO.OID_TIPO_CLIE = TCLI.TICL_OID_TIPO_CLIE
                 AND CLIE.OID_CLIE = TCLI.CLIE_OID_CLIE)
         AND DAAD.CLIE_OID_CLIE = CLIE.OID_CLIE;

    TYPE INTERFAZREC IS RECORD(
      CODIGOZONA         VARCHAR2(4),
      CODIGOTERRITORIO   VARCHAR2(6),
      CODIGOCONSULTORA   VARCHAR2(20),
      NOMBRECLIENTE      VARCHAR2(103),
      DOCUMENTOIDENTIDAD VARCHAR2(40),
      TELEFONO           VARCHAR2(100),
      SITUACION          VARCHAR2(100),
      DUPLA              VARCHAR2(1),
      CODIGOVERIFICACION VARCHAR2(30),
      SALDOCONSULTORA    NUMBER,
      ULTIMOPERIODO      VARCHAR2(6),
      IDCLIENTE          MAE_CLIEN.OID_CLIE%TYPE);
    TYPE INTERFAZRECTAB IS TABLE OF INTERFAZREC;
    INTERFAZRECORD INTERFAZRECTAB;
    /* Variables usadas para la generacion del archivo de texto */
    LSDIRTEMPO            BAS_INTER.DIR_TEMP%TYPE;
    V_HANDLE              UTL_FILE.FILE_TYPE;

    LSLINEA               VARCHAR2(1000);

    LSNOMBREARCHIVO       VARCHAR2(50);
    LSCODIGOPERIODOACTUAL VARCHAR2(6);
    LSCODIGOPERIODOCRUCE  VARCHAR2(6);
    LSCODIGOREGION        VARCHAR2(2);

    LNIDPAIS  NUMBER;
    LNIDMARCA NUMBER;
    LNIDCANAL NUMBER;

    lbAbrirUtlFile  BOOLEAN;

  BEGIN

    /* Generando Archivo de Texto (Detalle) */

    LNIDPAIS  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(PSCODIGOPAIS);
    LNIDMARCA := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(PSCODIGOMARCA);
    LNIDCANAL := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(PSCODIGOCANAL);

    --DETERMINO SI HAY CRUCE
    SELECT DISTINCT FIRST_VALUE(B.COD_PERI) OVER(ORDER BY B.COD_PERI ASC ROWS UNBOUNDED PRECEDING),
                    FIRST_VALUE(B.COD_PERI) OVER(ORDER BY B.COD_PERI DESC ROWS UNBOUNDED PRECEDING)
      INTO LSCODIGOPERIODOACTUAL, LSCODIGOPERIODOCRUCE
      FROM CRA_PERIO A, SEG_PERIO_CORPO B
     WHERE A.PAIS_OID_PAIS = LNIDPAIS
       AND A.MARC_OID_MARC = LNIDMARCA
       AND A.CANA_OID_CANA = LNIDCANAL
       AND A.FEC_INIC <= TRUNC(SYSDATE)
       AND A.FEC_FINA >= TRUNC(SYSDATE)
       AND B.OID_PERI = A.PERI_OID_PERI;

         lbAbrirUtlFile := TRUE;

    IF LSCODIGOPERIODOACTUAL = LSCODIGOPERIODOCRUCE THEN

      OPEN C_INTERFAZ(LSCODIGOPERIODOACTUAL);
      LOOP
        FETCH C_INTERFAZ BULK COLLECT
          INTO INTERFAZRECORD LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                  psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
        IF INTERFAZRECORD.COUNT > 0 THEN
          FOR X IN INTERFAZRECORD.FIRST .. INTERFAZRECORD.LAST LOOP

            LSCODIGOREGION := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(INTERFAZRECORD(X)
                                                                   .IDCLIENTE,
                                                                   'COD_REGI');

            BEGIN
              INTERFAZRECORD(X).TELEFONO := GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(INTERFAZRECORD(X)
                                                                                   .IDCLIENTE,
                                                                                   'TF');

            EXCEPTION
              WHEN OTHERS THEN
                INTERFAZRECORD(X).TELEFONO := NULL;

            END;

            LSLINEA := INTERFAZRECORD(X)
                      .CODIGOZONA || ';' || INTERFAZRECORD(X)
                      .CODIGOTERRITORIO || ';' || INTERFAZRECORD(X)
                      .CODIGOCONSULTORA || ';' || INTERFAZRECORD(X)
                      .NOMBRECLIENTE || ';' || INTERFAZRECORD(X)
                      .DOCUMENTOIDENTIDAD || ';' || INTERFAZRECORD(X)
                      .TELEFONO || ';' || INTERFAZRECORD(X)
                      .SITUACION || ';' || INTERFAZRECORD(X)
                      .DUPLA || ';' || INTERFAZRECORD(X)
                      .CODIGOVERIFICACION || ';' || INTERFAZRECORD(X)
                      .SALDOCONSULTORA || ';' || INTERFAZRECORD(X)
                      .ULTIMOPERIODO;

            UTL_FILE.PUT_LINE(V_HANDLE, LSLINEA);

          END LOOP;
        END IF;
        EXIT WHEN C_INTERFAZ%NOTFOUND;
      END LOOP;
      CLOSE C_INTERFAZ;

    ELSE
      OPEN C_INTERFAZ2(LSCODIGOPERIODOACTUAL,
                       LSCODIGOPERIODOCRUCE,
                       LNIDMARCA,
                       LNIDCANAL);
      LOOP
        FETCH C_INTERFAZ2 BULK COLLECT
          INTO INTERFAZRECORD LIMIT W_FILAS;
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                  psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
        IF INTERFAZRECORD.COUNT > 0 THEN
          FOR X IN INTERFAZRECORD.FIRST .. INTERFAZRECORD.LAST LOOP

            LSCODIGOREGION := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(INTERFAZRECORD(X)
                                                                   .IDCLIENTE,
                                                                   'COD_REGI');

            BEGIN
              INTERFAZRECORD(X).TELEFONO := GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(INTERFAZRECORD(X)
                                                                                   .IDCLIENTE,
                                                                                   'TF');

            EXCEPTION
              WHEN OTHERS THEN
                INTERFAZRECORD(X).TELEFONO := NULL;

            END;

            LSLINEA := INTERFAZRECORD(X)
                      .CODIGOZONA || ';' || INTERFAZRECORD(X)
                      .CODIGOTERRITORIO || ';' || INTERFAZRECORD(X)
                      .CODIGOCONSULTORA || ';' || INTERFAZRECORD(X)
                      .NOMBRECLIENTE || ';' || INTERFAZRECORD(X)
                      .DOCUMENTOIDENTIDAD || ';' || INTERFAZRECORD(X)
                      .TELEFONO || ';' || INTERFAZRECORD(X)
                      .SITUACION || ';' || INTERFAZRECORD(X)
                      .DUPLA || ';' || INTERFAZRECORD(X)
                      .CODIGOVERIFICACION || ';' || INTERFAZRECORD(X)
                      .SALDOCONSULTORA || ';' || INTERFAZRECORD(X)
                      .ULTIMOPERIODO;

            UTL_FILE.PUT_LINE(V_HANDLE, LSLINEA);

          END LOOP;
        END IF;
        EXIT WHEN C_INTERFAZ2%NOTFOUND;
      END LOOP;
      CLOSE C_INTERFAZ2;

    END IF;

        IF NOT lbAbrirUtlFile THEN
           utl_file.fclose(V_HANDLE);

           /* Procedimiento final para generar interfaz */
          GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
       END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_PR_INT_HISTO_CONSU: ' ||
                              LS_SQLERRM);
  END EVI_PR_INT_HISTO_CONSU;

  /**************************************************************************
  Descripcion       : Devuelve el indicador de Dupla Cyzone
                      S -> Tiene Dupla
                      N -> No tiene Dubla
  Fecha Creacion    : 14/01/2008
  Parametros Entrada:
      pnCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_DUPLA(PNCODIGOCLIENTE IN NUMBER) RETURN VARCHAR IS
    LSDUPLA VARCHAR2(1) := 'N';
  BEGIN
    SELECT 'S'
      INTO LSDUPLA
      FROM MAE_CLIEN_VINCU VINC, MAE_TIPO_VINCU TIVI
     WHERE VINC.TIVC_OID_TIPO_VINC = TIVI.OID_TIPO_VINC
       AND VINC.CLIE_OID_CLIE_VNTE = PNCODIGOCLIENTE
       AND TIVI.COD_TIPO_VINC = '01';

    RETURN LSDUPLA;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN LSDUPLA;

    WHEN TOO_MANY_ROWS THEN
      RETURN 'S';

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_DUPLA: ' || LS_SQLERRM);

  END EVI_FN_DEVUE_DUPLA;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Resultados de Cobranza
  Fecha Creacion    : 15/01/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE EVI_PR_INT_RESUL_COBRA(PSCODIGOPAIS     VARCHAR2,
                                   PSCODIGOSISTEMA  VARCHAR2,
                                   PSCODIGOINTERFAZ VARCHAR2,
                                   PSNOMBREARCHIVO  VARCHAR2,
                                   PSCODIGOMARCA    VARCHAR2,
                                   PSCODIGOCANAL    VARCHAR2) IS
    CURSOR C_INTERFAZ(VSCODIGOPERIODOCOBRANZA VARCHAR2, VNFACTORESTIMADO NUMBER) IS

      SELECT VSCODIGOPERIODOCOBRANZA CODIGOPERIODO,
             A.COD_ZONA || '00' CODIGOZONA,
             TO_CHAR(SUM(A.VAL_IMPO_FACT), '99999999990.00') TOTALFACTURADO,
             TO_CHAR(SUM(A.VAL_IMPO_FACT - A.VAL_RECL), '99999999990.00') FACTURADONETO,
             TO_CHAR(SUM(NVL(A.VAL_COBR,0)), '99999999990.00') COBRANZA31DIAS,
             TO_CHAR(SUM(A.VAL_IMPO_FACT - A.VAL_RECL) * VNFACTORESTIMADO,
                     '99999999990.00') ESTIMADO,
             TO_CHAR(DECODE(SUM(A.VAL_IMPO_FACT - A.VAL_RECL),
                            NULL,
                            0,
                            0,
                            0,
                            SUM(NVL(A.VAL_COBR,0))*100 /
                            SUM(A.VAL_IMPO_FACT - A.VAL_RECL)),
                     '990.00') || '%' PORCENTAJERECUPERADO,
               TO_CHAR(DECODE(SIGN(SUM(A.VAL_IMPO_FACT - A.VAL_RECL) * VNFACTORESTIMADO - SUM(NVL(A.VAL_COBR,0))),
                            -1,
                            0,
                            SUM(A.VAL_IMPO_FACT - A.VAL_RECL) * VNFACTORESTIMADO - SUM(NVL(A.VAL_COBR,0))),
                     '9999999999000')  PORCENTAJERECUPERAR,
             A.FEC_CIER FECHACIERRE
        FROM INT_EVI_CLIEN_TEMPO_COBRA A
       GROUP BY A.COD_ZONA, A.FEC_CIER;

    TYPE INTERFAZREC IS RECORD(
      CODIGOPERIODO        VARCHAR2(6),
      CODIGOZONA           VARCHAR2(7),
      TOTALFACTURADO       VARCHAR2(15),
      FACTURADONETO        VARCHAR2(15),
      COBRANZA31DIAS       VARCHAR2(15),
      ESTIMADO             VARCHAR2(15),
      PORCENTAJERECUPERADO VARCHAR2(8),
      PORCENTAJERECUPERAR  VARCHAR2(15),
      FECHACIERRE          VARCHAR2(10));
    TYPE INTERFAZRECTAB IS TABLE OF INTERFAZREC;
    INTERFAZRECORD INTERFAZRECTAB;

    /* Variables usadas para la generacion del archivo de texto */
    LSDIRTEMPO      BAS_INTER.DIR_TEMP%TYPE;
    V_HANDLE        UTL_FILE.FILE_TYPE;

    LSLINEA         VARCHAR2(1000);

    LSNOMBREARCHIVO VARCHAR2(50);

    LSCODIGOPERIODOACTUAL   VARCHAR2(6);
    LSCODIGOPERIODOCRUCE    VARCHAR2(6);
    LSCODIGOPERIODOCOBRANZA VARCHAR2(6);

    LNIDMARCA          NUMBER;
    LNIDCANAL          NUMBER;
    LNIDPAIS           NUMBER;
    LNIDPERIODOPROCESO NUMBER;

    LNIDTIPOCONSULTORA NUMBER;
    LNFACTORESTIMADO   NUMBER;

    LNIDSUBPROCCARGOFACT NUMBER;

    lnOidActivFacturacion NUMBER;
    l_user  VARCHAR2(20);
    lbAbrirUtlFile  BOOLEAN;
  BEGIN
    l_user := USER;

     --Borrando la tabla
    EXECUTE IMMEDIATE 'TRUNCATE TABLE int_evi_cobra_zona_antic';

    --Borrando la tabla
    EXECUTE IMMEDIATE 'TRUNCATE TABLE int_evi_clien_tempo_cobra';

    --Determino los Ids de Pais, Marca y Canal
    LNIDPAIS  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(PSCODIGOPAIS);
    LNIDMARCA := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(PSCODIGOMARCA);
    LNIDCANAL := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(PSCODIGOCANAL);

    LNFACTORESTIMADO := 0.9;

    --Derermino el cruce de campañas
    SELECT DISTINCT FIRST_VALUE(B.COD_PERI) OVER(ORDER BY B.COD_PERI ASC ROWS UNBOUNDED PRECEDING),
                    FIRST_VALUE(B.COD_PERI) OVER(ORDER BY B.COD_PERI DESC ROWS UNBOUNDED PRECEDING)
      INTO LSCODIGOPERIODOACTUAL, LSCODIGOPERIODOCRUCE
      FROM CRA_PERIO A, SEG_PERIO_CORPO B
     WHERE A.PAIS_OID_PAIS = LNIDPAIS
       AND A.MARC_OID_MARC = LNIDMARCA
       AND A.CANA_OID_CANA = LNIDCANAL
       AND A.FEC_INIC <= TRUNC(SYSDATE)
       AND A.FEC_FINA >= TRUNC(SYSDATE)
       AND B.OID_PERI = A.PERI_OID_PERI;

    DBMS_OUTPUT.PUT_LINE('lsCodigoPeriodoActual: ' ||
                         LSCODIGOPERIODOACTUAL);
    --Determinando codigos de Periodo
    LSCODIGOPERIODOCOBRANZA :=  PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(LSCODIGOPERIODOACTUAL,
                                                                      LNIDPAIS,
                                                                      LNIDMARCA,
                                                                      LNIDCANAL,
                                                                     -2);


   --Periodo De proceso es el actual -2
    LSCODIGOPERIODOACTUAL :=   PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(LSCODIGOPERIODOACTUAL,
                                                                      LNIDPAIS,
                                                                      LNIDMARCA,
                                                                      LNIDCANAL,
                                                                      -2);
    --Periodo De cruce es el actual -2
   LSCODIGOPERIODOCRUCE  :=   PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(LSCODIGOPERIODOCRUCE,
                                                                      LNIDPAIS,
                                                                      LNIDMARCA,
                                                                      LNIDCANAL,
                                                                      -2);
    --IdPeriodo
    LNIDPERIODOPROCESO :=  LSCODIGOPERIODOCOBRANZA;/*GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(LSCODIGOPERIODOACTUAL,
                                                                     LNIDMARCA,
                                                                     LNIDCANAL);
    */

    --Obtengo el oid del tipo de consultora
    SELECT A.OID_TIPO_CLIE
      INTO LNIDTIPOCONSULTORA
      FROM MAE_TIPO_CLIEN A
     WHERE A.COD_TIPO_CLIE = '02';

    --lnIdSubProcCargoFact
    LNIDSUBPROCCARGOFACT := EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ID_SUBPR_CC('CCC001',
                                                                         '1');

    DBMS_OUTPUT.PUT_LINE('INICIO DE INSERT DE int_evi_cobra_zona_antic ' ||
                         SYSDATE);

    SELECT ACT.OID_ACTI
      INTO LNOIDACTIVFACTURACION
      FROM CRA_ACTIV ACT
     WHERE ACT.COD_ACTI = 'FA'
       AND ACT.PAIS_OID_PAIS = LNIDPAIS;

    --Insertando datos
    INSERT INTO INT_EVI_COBRA_ZONA_ANTIC
      (OID_ZONA,
       IND_ZONA_ANTIC,
       FEC_CIER,
       OID_PERI_ACTU,
       OID_PERI_ANTE,
       OID_PERI_SIG1,
       OID_PERI_SIG2)
      SELECT OIDZONA,
             INDZONA,
             (SELECT MAX(C.FEC_INIC) + 31
                FROM CRA_CRONO C
               WHERE C.PERD_OID_PERI =
                     GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(PERIODOACTUAL,
                                                                LNIDMARCA,
                                                                LNIDCANAL)
                 AND C.CACT_OID_ACTI = lnOidActivFacturacion
                 AND C.ZZON_OID_ZONA = ZON.OIDZONA) FECHAFACTURACION,
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(PERIODOACTUAL,
                                                        LNIDMARCA,
                                                        LNIDCANAL) OIDPERIODOACTUAL,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ID_PERIO_NSGTE(PERIODOACTUAL,
                                                             LNIDPAIS,
                                                             LNIDMARCA,
                                                             LNIDCANAL,
                                                             -1) OIDPERIODOANTERIOR,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ID_PERIO_NSGTE(PERIODOACTUAL,
                                                             LNIDPAIS,
                                                             LNIDMARCA,
                                                             LNIDCANAL,
                                                             1) OIDPERIODOSIGUIENTE1,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ID_PERIO_NSGTE(PERIODOACTUAL,
                                                             LNIDPAIS,
                                                             LNIDMARCA,
                                                             LNIDCANAL,
                                                             2) OIDPERIODOSIGUIENTE2
        FROM (SELECT Z.OID_ZONA OIDZONA,
                     EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_PERIO_FACTU(LSCODIGOPERIODOACTUAL,
                                                                  LSCODIGOPERIODOCRUCE,
                                                                  Z.COD_ZONA,
                                                                  LNIDMARCA,
                                                                  LNIDCANAL) PERIODOACTUAL,
                     EVI_PKG_EJECU_VIRTU.EVI_FN_EXIST_ZONA_DEMAN(Z.COD_ZONA) INDZONA
                FROM ZON_ZONA Z) ZON;

    DBMS_OUTPUT.PUT_LINE('FIN DE INSERT DE int_evi_cobra_zona_antic ' ||
                         SYSDATE);

    DBMS_OUTPUT.PUT_LINE('INICIO DE INSERT DE int_evi_clien_tempo_cobra ' ||
                         SYSDATE);
    --INSERTAMOS LOS VALORES CALCULADOS POR CLIENTE
    INSERT INTO INT_EVI_CLIEN_TEMPO_COBRA
      (OID_CLIEN,
       OID_ZONA,
       COD_ZONA,
       VAL_COBR,
       VAL_RECL,
       VAL_IMPO_FACT,
       FEC_CIER)
      SELECT TBCLIENTE.OIDCLIENTE,
             TBCLIENTE.OIDZONA,
             TBCLIENTE.CODZONA,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_COBRA_31DIA(CCC_MOVIM_CUENT_CORRI.VAL_ULTI_NUME_HIST,
                                                          CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_ULTI,
                                                          CCC_MOVIM_CUENT_CORRI.FEC_VENC,
                                                          DECODE(CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI,
                                                                 INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_ANTE,
                                                                 CRA_CRONO.FEC_INIC,
                                                                 CCC_MOVIM_CUENT_CORRI.FEC_DOCU),
                                                          TBCLIENTE.CODZONA,
                                                          CCC_MOVIM_CUENT_CORRI.IMP_PAGO,
                                                          PED_SOLIC_CABEC.VAL_TASA_IMPU,
                                                          CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC) COBRANZA31DIAS,
             EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_RECLA_CC(CCC_MOVIM_CUENT_CORRI.VAL_ULTI_NUME_HIST,
                                                       CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_ULTI,
                                                       CCC_MOVIM_CUENT_CORRI.IMP_MOVI,
                                                       PED_SOLIC_CABEC.VAL_TASA_IMPU,
                                                       CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC) RECLAMOS,
             DECODE(SIGN(CCC_MOVIM_CUENT_CORRI.IMP_MOVI),
                    -1,
                    0,
                    CCC_MOVIM_CUENT_CORRI.IMP_MOVI /
                    (1 + PED_SOLIC_CABEC.VAL_TASA_IMPU / 100)) TOTALFACTURADO,
             TO_CHAR(INT_EVI_COBRA_ZONA_ANTIC.FEC_CIER, 'DD/MM/YYYY') FECHACIERRE

        FROM (SELECT ZON_ZONA.OID_ZONA  OIDZONA,
                     ZON_ZONA.COD_ZONA  CODZONA,
                     MAE_CLIEN.OID_CLIE OIDCLIENTE
                FROM MAE_CLIEN,
                     MAE_CLIEN_TIPO_SUBTI,
                     MAE_CLIEN_UNIDA_ADMIN,
                     ZON_TERRI_ADMIN,
                     ZON_SECCI,
                     ZON_ZONA
               WHERE MAE_CLIEN.OID_CLIE = MAE_CLIEN_TIPO_SUBTI.CLIE_OID_CLIE
                 AND LNIDTIPOCONSULTORA =
                     MAE_CLIEN_TIPO_SUBTI.TICL_OID_TIPO_CLIE
                 AND MAE_CLIEN.OID_CLIE =
                     MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE
                 AND MAE_CLIEN_UNIDA_ADMIN.PERD_OID_PERI_INI <=
                     LNIDPERIODOPROCESO --PERIODO DE PROCESO
                 AND (MAE_CLIEN_UNIDA_ADMIN.PERD_OID_PERI_FIN >=
                     LNIDPERIODOPROCESO OR
                     MAE_CLIEN_UNIDA_ADMIN.PERD_OID_PERI_FIN IS NULL)
                 AND MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI =
                     ZON_TERRI_ADMIN.OID_TERR_ADMI
                 AND ZON_TERRI_ADMIN.ZSCC_OID_SECC = ZON_SECCI.OID_SECC
                 AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
              --AND mae_clien.oid_clie <1781
              ) TBCLIENTE,

             CCC_MOVIM_CUENT_CORRI,

             PED_SOLIC_CABEC,
             PED_ESTAD_SOLIC,
             CRA_CRONO,
             CRA_ACTIV,
             INT_EVI_COBRA_ZONA_ANTIC
       WHERE CCC_MOVIM_CUENT_CORRI.CLIE_OID_CLIE = TBCLIENTE.OIDCLIENTE --codigoCliente
         AND CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_CREA =
             LNIDSUBPROCCARGOFACT
         AND CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI IN
             (INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_ANTE, /*PERIODO ANTERIOR*/
              INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_ACTU) /*PERIODO ACTUAL*/
         AND PED_SOLIC_CABEC.OID_SOLI_CABE =
             CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE
         AND PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI =
             PED_ESTAD_SOLIC.OID_ESTA_SOLI
         AND PED_ESTAD_SOLIC.COD_ESTA_SOLI <> 'AN'
         AND CRA_CRONO.ZZON_OID_ZONA = TBCLIENTE.OIDZONA --ID_ZONA
         AND INT_EVI_COBRA_ZONA_ANTIC.OID_ZONA = CRA_CRONO.ZZON_OID_ZONA
         AND CRA_CRONO.PERD_OID_PERI =
             DECODE(INT_EVI_COBRA_ZONA_ANTIC.IND_ZONA_ANTIC,
                    'S',
                    DECODE(CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI,
                           INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_ANTE /*Periodo Anterior*/,
                           INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_SIG1 /*PeriodoProceso+1*/,
                           INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_SIG2 /*PeriodoProceso+2*/),
                    DECODE(CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI,
                           INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_ANTE /*Periodo Anterior*/,
                           INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_ACTU /*Periodo de Proceso*/,
                           INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_SIG1 /*Periodo de Proceso+1*/))
         AND CRA_CRONO.CACT_OID_ACTI = CRA_ACTIV.OID_ACTI
         AND CRA_ACTIV.COD_ACTI = 'FA'
         AND EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_INDIC_CCVAL(CRA_CRONO.FEC_INIC,
                                                          CCC_MOVIM_CUENT_CORRI.FEC_VENC,
                                                          DECODE(CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI,
                                                                 INT_EVI_COBRA_ZONA_ANTIC.OID_PERI_ANTE /*Periodo Anterior*/,
                                                                 'ANTERIOR',
                                                                 'ACTUAL')) = 'S';

    DBMS_OUTPUT.PUT_LINE('FIN DE INSERT DE int_evi_clien_tempo_cobra ' ||
                         SYSDATE);
    DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'int_evi_cobra_zona_antic', CASCADE => TRUE );
    DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'int_evi_clien_tempo_cobra', CASCADE => TRUE );

    lbAbrirUtlFile := TRUE;
    OPEN C_INTERFAZ(LSCODIGOPERIODOCOBRANZA, LNFACTORESTIMADO);
    LOOP
      FETCH C_INTERFAZ BULK COLLECT
        INTO INTERFAZRECORD LIMIT W_FILAS;
      /* Procedimiento inicial para generar interfaz */
      IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
      END IF;

      IF INTERFAZRECORD.COUNT > 0 THEN
        FOR X IN INTERFAZRECORD.FIRST .. INTERFAZRECORD.LAST LOOP
          LSLINEA := INTERFAZRECORD(X)
                    .CODIGOPERIODO || ';' || INTERFAZRECORD(X)
                    .CODIGOZONA || ';' || INTERFAZRECORD(X)
                    .TOTALFACTURADO || ';' || INTERFAZRECORD(X)
                    .FACTURADONETO || ';' || INTERFAZRECORD(X)
                    .COBRANZA31DIAS || ';' || INTERFAZRECORD(X)
                    .ESTIMADO || ';' || INTERFAZRECORD(X)
                    .PORCENTAJERECUPERADO || ';' || INTERFAZRECORD(X)
                    .PORCENTAJERECUPERAR || ';' || INTERFAZRECORD(X)
                    .FECHACIERRE;

          UTL_FILE.PUT_LINE(V_HANDLE, LSLINEA);
        END LOOP;
      END IF;
      EXIT WHEN C_INTERFAZ%NOTFOUND;
    END LOOP;
    CLOSE C_INTERFAZ;

    --Borramos los datos de la tabla temporal de clientes
    --DELETE int_evi_clien_tempo_cobra;

    DBMS_OUTPUT.PUT_LINE('FIN DE ESCRITURA ARCHIVO  ' || SYSDATE);
    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(v_handle);

        /* Procedimiento final para generar interfaz */
        GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_PR_INT_RESUL_COBRA: ' ||
                              LS_SQLERRM);
  END EVI_PR_INT_RESUL_COBRA;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Resultados de Facturacion
  Fecha Creacion    : 15/01/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE EVI_PR_INT_RESUL_FACT(PSCODIGOPAIS     VARCHAR2,
                                  PSCODIGOSISTEMA  VARCHAR2,
                                  PSCODIGOINTERFAZ VARCHAR2,
                                  PSNOMBREARCHIVO  VARCHAR2,
                                  PSCODIGOMARCA    VARCHAR2,
                                  PSCODIGOCANAL    VARCHAR2) IS
    CURSOR C_INTERFAZ(VNPERIODOACTUAL VARCHAR2) IS

      SELECT GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(VR.PERD_OID_PERI) PERIODOACTUAL,
             ZON.COD_ZONA CODIGOZONA,
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(ZON.CLIE_OID_CLIE,
                                                  'NOM_CLIE') GERENTEZONA, --GERENTE DE ZONA
             SUM(VR.NUM_INGR) INGRESOS,
             SUM(VR.NUM_REIN) REINGRESOS,
             SUM(VR.NUM_EGRE) EGRESOS,
             SUM(VR.NUM_ACTI_FINA) REGULARES,
             SUM(VR.NUM_ACTI_INIC) INICIALES,
             SUM(VR.NUM_ACTI_FINA - VR.NUM_ACTI_INIC - VR.NUM_REZO_RECI +
                 VR.NUM_REZO_ENTR) CAPITALIZACION, --[Act.Finales]-[Act.Iniciales]- recibidas+entrantes
             NULL ACTIVIDAD,
             NULL PEDIDOS,
             NULL UNIDADESVENTA,
             NULL CLIENTES,
             NULL MONTOVENTA,
             NULL PUP,
             NULL PYP,
             NULL PPU,
             NVL(TO_CHAR(EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_MONTO_FALTA_ZONA(VR.PAIS_OID_PAIS,
                                                                           VR.PERD_OID_PERI,
                                                                           VR.ZZON_OID_ZONA),
                         '9999999990.00'),
                 0) MONTOFALTANTE,
             NULL PORCEFALTANTE,
             SUM(VR.NUM_REZO_ENTR) ENTREGADAS,
             SUM(VR.NUM_REZO_RECI) RECIBIDAS,
             VR.ZZON_OID_ZONA IDZONA,
             VR.PERD_OID_PERI IDPERIODO
        FROM INT_FUENT_VENTAS_REAL VR, ZON_ZONA ZON
       WHERE VR.PERD_OID_PERI = VNPERIODOACTUAL
         AND VR.ZZON_OID_ZONA = ZON.OID_ZONA
       GROUP BY VR.PAIS_OID_PAIS,
                VR.PERD_OID_PERI,
                VR.ZZON_OID_ZONA,
                ZON.COD_ZONA,
                ZON.CLIE_OID_CLIE
       ORDER BY 1, 2;

    CURSOR C_INTERFAZ2(VNPERIODOACTUAL VARCHAR2, VNPERIODOCRUCE VARCHAR2) IS

      SELECT GEN_PKG_GENER.GEN_FN_DEVUELVE_DES_PERIO(VR.PERD_OID_PERI) PERIODOACTUAL,
             ZON.COD_ZONA CODIGOZONA,
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(ZON.CLIE_OID_CLIE,
                                                  'NOM_CLIE') GERENTEZONA, --GERENTE DE ZONA
             SUM(VR.NUM_INGR) INGRESOS,
             SUM(VR.NUM_REIN) REINGRESOS,
             SUM(VR.NUM_EGRE) EGRESOS,
             SUM(VR.NUM_ACTI_FINA) REGULARES,
             SUM(VR.NUM_ACTI_INIC) INICIALES,
             SUM(VR.NUM_ACTI_FINA - VR.NUM_ACTI_INIC - VR.NUM_REZO_RECI +
                 VR.NUM_REZO_ENTR) CAPITALIZACION, --[Act.Finales]-[Act.Iniciales]- recibidas+entrantes
             NULL ACTIVIDAD,
             NULL PEDIDOS,
             NULL UNIDADESVENTA,
             NULL CLIENTES,
             NULL MONTOVENTA,
             NULL PUP,
             NULL PYP,
             NULL PPU,
             NVL(TO_CHAR(EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_MONTO_FALTA_ZONA(VR.PAIS_OID_PAIS,
                                                                           VR.PERD_OID_PERI,
                                                                           VR.ZZON_OID_ZONA),
                         '9999999990.00'),
                 0) MONTOFALTANTE,
             NULL PORCEFALTANTE,
             SUM(VR.NUM_REZO_ENTR) ENTREGADAS,
             SUM(VR.NUM_REZO_RECI) RECIBIDAS,

             VR.ZZON_OID_ZONA IDZONA,
             VR.PERD_OID_PERI IDPERIODO
        FROM INT_FUENT_VENTAS_REAL VR, ZON_ZONA ZON
       WHERE VR.PERD_OID_PERI IN (VNPERIODOACTUAL, VNPERIODOCRUCE)
         AND VR.ZZON_OID_ZONA = ZON.OID_ZONA
       GROUP BY VR.PAIS_OID_PAIS,
                VR.PERD_OID_PERI,
                VR.ZZON_OID_ZONA,
                ZON.COD_ZONA,
                ZON.CLIE_OID_CLIE
       ORDER BY 1, 2;

    TYPE INTERFAZREC IS RECORD(
      PERIODOACTUAL  VARCHAR2(6),
      CODIGOZONA     ZON_ZONA.COD_ZONA%TYPE,
      GERENTEZONA    VARCHAR2(100),
      INGRESOS       NUMBER,
      REINGRESOS     NUMBER,
      EGRESOS        NUMBER,
      REGULARES      NUMBER,
      INICIALES      NUMBER,
      CAPITALIZACION NUMBER,
      ACTIVIDAD      VARCHAR2(14),
      PEDIDOS        NUMBER,
      UNIDADESVENTA  NUMBER,
      CLIENTES       NUMBER,
      MONTOVENTA     VARCHAR2(14),
      PUP            VARCHAR2(14),
      PYP            VARCHAR2(14),
      PPU            VARCHAR2(20),
      MONTOFALTANTE  VARCHAR2(14),
      PORCEFALTANTE  VARCHAR2(14),
      ENTREGADAS     NUMBER,
      RECIBIDAS      NUMBER,
      IDZONA         NUMBER,
      IDPERIODO      NUMBER

      );

    TYPE INTERFAZRECTAB IS TABLE OF INTERFAZREC;
    INTERFAZRECORD INTERFAZRECTAB;
    /* Variables usadas para la generacion del archivo de texto */
    LSDIRTEMPO      BAS_INTER.DIR_TEMP%TYPE;
    V_HANDLE        UTL_FILE.FILE_TYPE;

    LSLINEA         VARCHAR2(1000);

    LSNOMBREARCHIVO VARCHAR2(50);

    LSCODIGOPERIODOACTUAL VARCHAR2(6);
    LSCODIGOPERIODOCRUCE  VARCHAR2(6);

    LNCODIGOPERIODOACTUAL NUMBER;
    LNCODIGOPERIODOCRUCE  NUMBER;
    LNIDCANAL             NUMBER;
    LNIDMARCA             NUMBER;
    LNIDPAIS              NUMBER;
    lbAbrirUtlFile      BOOLEAN;
  BEGIN
    LNIDPAIS  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(PSCODIGOPAIS);
    LNIDMARCA := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(PSCODIGOMARCA);
    LNIDCANAL := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(PSCODIGOCANAL);
    lbAbrirUtlFile := TRUE;

    --DETERMINO SI HAY CRUCE
    SELECT DISTINCT FIRST_VALUE(B.COD_PERI) OVER(ORDER BY B.COD_PERI ASC ROWS UNBOUNDED PRECEDING),
                    FIRST_VALUE(B.COD_PERI) OVER(ORDER BY B.COD_PERI DESC ROWS UNBOUNDED PRECEDING)
      INTO LSCODIGOPERIODOACTUAL, LSCODIGOPERIODOCRUCE
      FROM CRA_PERIO A, SEG_PERIO_CORPO B
     WHERE A.PAIS_OID_PAIS = LNIDPAIS
       AND A.MARC_OID_MARC = LNIDMARCA
       AND A.CANA_OID_CANA = LNIDCANAL
       AND A.FEC_INIC <= TRUNC(SYSDATE)
       AND A.FEC_FINA >= TRUNC(SYSDATE)
       AND B.OID_PERI = A.PERI_OID_PERI;

    --Si no hay Cruce Calculamos los valores del periodo Actual
    IF LSCODIGOPERIODOACTUAL = LSCODIGOPERIODOCRUCE THEN

      LNCODIGOPERIODOACTUAL := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(LSCODIGOPERIODOACTUAL,
                                                                          LNIDMARCA,
                                                                          LNIDCANAL);

      OPEN C_INTERFAZ(LNCODIGOPERIODOACTUAL);
      LOOP
        FETCH C_INTERFAZ BULK COLLECT
          INTO INTERFAZRECORD LIMIT W_FILAS;

        /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                  psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
        IF INTERFAZRECORD.COUNT > 0 THEN
          FOR X IN INTERFAZRECORD.FIRST .. INTERFAZRECORD.LAST LOOP
            BEGIN
              SELECT SUM(VA.NUM_PEDI) PEDIDOS,
                     SUM(VA.NUM_UNID_VEND) UNIDADESVENDIDAS,
                     SUM(VA.NUM_CLIE) CLIENTES,
                     TO_CHAR(NVL(SUM(VA.IMP_VENT_NETA_ESTA), 0),
                             '9999999990.00') MONTOVENTA,
                     TO_CHAR(EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ACTIV(TRIM(INTERFAZRECORD(X)
                                                                         .REGULARES),
                                                                    SUM(VA.NUM_PEDI)),
                             '9999999990.00') ACTIVIDAD,
                     TO_CHAR(DECODE(SUM(VA.NUM_PEDI),
                                    0,
                                    0,
                                    SUM(VA.NUM_UNID_VEND) / SUM(VA.NUM_PEDI)),
                             '9999999990.00') PUP,
                     TO_CHAR(DECODE(SUM(VA.NUM_PEDI),
                                    0,
                                    0,
                                    SUM(VA.IMP_VENT_NETA_ESTA) /
                                    SUM(VA.NUM_PEDI)),
                             '999990.000000') PYP,
                     TO_CHAR(DECODE((SUM(VA.IMP_VENT_NETA_ESTA) +
                                    INTERFAZRECORD(X).MONTOFALTANTE),
                                    0,
                                    0,
                                    TO_NUMBER(TRIM(INTERFAZRECORD(X)
                                                   .MONTOFALTANTE)) * 100 /
                                    (SUM(VA.IMP_VENT_NETA_ESTA) +
                                     INTERFAZRECORD(X).MONTOFALTANTE)),
                             '990.00') PORCEFALTANTE,
                     TO_CHAR(DECODE(DECODE(SUM(VA.NUM_PEDI),
                                           0,
                                           0,
                                           (SUM(VA.NUM_UNID_VEND) /
                                           SUM(VA.NUM_PEDI))),
                                    0,
                                    0,
                                    (DECODE(SUM(VA.NUM_PEDI),
                                            0,
                                            0,
                                            SUM(VA.IMP_VENT_NETA_ESTA) /
                                            SUM(VA.NUM_PEDI))) /
                                    DECODE(SUM(VA.NUM_PEDI),
                                           0,
                                           0,
                                           (SUM(VA.NUM_UNID_VEND) /
                                           SUM(VA.NUM_PEDI)))),
                             '9999999990.00') PPU

                INTO INTERFAZRECORD(X) .PEDIDOS,
                     INTERFAZRECORD(X) .UNIDADESVENTA,
                     INTERFAZRECORD(X) .CLIENTES,
                     INTERFAZRECORD(X) .MONTOVENTA,
                     INTERFAZRECORD(X) .ACTIVIDAD,
                     INTERFAZRECORD(X) .PUP,
                     INTERFAZRECORD(X) .PYP,
                     INTERFAZRECORD(X) .PORCEFALTANTE,
                     INTERFAZRECORD(X) .PPU
                FROM INT_FUENT_VENTA_REAL_VACUM VA
               WHERE VA.PERD_OID_PERI = INTERFAZRECORD(X)
              .IDPERIODO
                 AND VA.ZZON_OID_ZONA = INTERFAZRECORD(X).IDZONA;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                INTERFAZRECORD(X).PEDIDOS := NULL;
                INTERFAZRECORD(X).UNIDADESVENTA := NULL;
                INTERFAZRECORD(X).CLIENTES := NULL;
                INTERFAZRECORD(X).MONTOVENTA := NULL;

            END;

            LSLINEA := INTERFAZRECORD(X)
                      .PERIODOACTUAL || ';' || INTERFAZRECORD(X)
                      .CODIGOZONA || ';' || INTERFAZRECORD(X)
                      .GERENTEZONA || ';' || INTERFAZRECORD(X)
                      .INGRESOS || ';' || INTERFAZRECORD(X)
                      .REINGRESOS || ';' || INTERFAZRECORD(X)
                      .EGRESOS || ';' || INTERFAZRECORD(X)
                      .REGULARES || ';' || INTERFAZRECORD(X)
                      .INICIALES || ';' || INTERFAZRECORD(X)
                      .CAPITALIZACION || ';' || INTERFAZRECORD(X)
                      .ACTIVIDAD || ';' || INTERFAZRECORD(X)
                      .PEDIDOS || ';' || INTERFAZRECORD(X)
                      .UNIDADESVENTA || ';' || INTERFAZRECORD(X)
                      .CLIENTES || ';' || INTERFAZRECORD(X)
                      .MONTOVENTA || ';' || INTERFAZRECORD(X)
                      .PUP || ';' || INTERFAZRECORD(X)
                      .PYP || ';' || INTERFAZRECORD(X)
                      .PPU || ';' || INTERFAZRECORD(X)
                      .MONTOFALTANTE || ';' || INTERFAZRECORD(X)
                      .PORCEFALTANTE || ';' || INTERFAZRECORD(X)
                      .ENTREGADAS || ';' || INTERFAZRECORD(X).RECIBIDAS;
            UTL_FILE.PUT_LINE(V_HANDLE, LSLINEA);
          END LOOP;
        END IF;
        EXIT WHEN C_INTERFAZ%NOTFOUND;
      END LOOP;
      CLOSE C_INTERFAZ;

    ELSE

      LNCODIGOPERIODOACTUAL := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(LSCODIGOPERIODOACTUAL,
                                                                          LNIDMARCA,
                                                                          LNIDCANAL);
      LNCODIGOPERIODOCRUCE  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(LSCODIGOPERIODOCRUCE,
                                                                          LNIDMARCA,
                                                                          LNIDCANAL);

      OPEN C_INTERFAZ2(LNCODIGOPERIODOACTUAL, LNCODIGOPERIODOCRUCE);
      LOOP
        FETCH C_INTERFAZ2 BULK COLLECT
          INTO INTERFAZRECORD LIMIT W_FILAS;
        /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                  psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;
        IF INTERFAZRECORD.COUNT > 0 THEN
          FOR X IN INTERFAZRECORD.FIRST .. INTERFAZRECORD.LAST LOOP
            BEGIN
              SELECT SUM(VA.NUM_PEDI) PEDIDOS,
                     SUM(VA.NUM_UNID_VEND) UNIDADESVENDIDAS,
                     SUM(VA.NUM_CLIE) CLIENTES,
                     TO_CHAR(NVL(SUM(VA.IMP_VENT_NETA_ESTA), 0),
                             '9999999990.00') MONTOVENTA,
                     TO_CHAR(EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ACTIV(TRIM(INTERFAZRECORD(X)
                                                                         .REGULARES),
                                                                    SUM(VA.NUM_PEDI)),
                             '9999999990.00') ACTIVIDAD,
                     TO_CHAR(DECODE(SUM(VA.NUM_PEDI),
                                    0,
                                    0,
                                    SUM(VA.NUM_UNID_VEND) / SUM(VA.NUM_PEDI)),
                             '9999999990.00') PUP,
                     TO_CHAR(DECODE(SUM(VA.NUM_PEDI),
                                    0,
                                    0,
                                    SUM(VA.IMP_VENT_NETA_ESTA) /
                                    SUM(VA.NUM_PEDI)),
                             '999990.000000') PYP,
                     TO_CHAR(DECODE((SUM(VA.IMP_VENT_NETA_ESTA) +
                                    INTERFAZRECORD(X).MONTOFALTANTE),
                                    0,
                                    0,
                                    TO_NUMBER(TRIM(INTERFAZRECORD(X)
                                                   .MONTOFALTANTE)) * 100 /
                                    (SUM(VA.IMP_VENT_NETA_ESTA) +
                                     INTERFAZRECORD(X).MONTOFALTANTE)),
                             '990.00') PORCEFALTANTE,
                     TO_CHAR(DECODE(DECODE(SUM(VA.NUM_PEDI),
                                           0,
                                           0,
                                           (SUM(VA.NUM_UNID_VEND) /
                                           SUM(VA.NUM_PEDI))),
                                    0,
                                    0,
                                    (DECODE(SUM(VA.NUM_PEDI),
                                            0,
                                            0,
                                            SUM(VA.IMP_VENT_NETA_ESTA) /
                                            SUM(VA.NUM_PEDI))) /
                                    DECODE(SUM(VA.NUM_PEDI),
                                           0,
                                           0,
                                           (SUM(VA.NUM_UNID_VEND) /
                                           SUM(VA.NUM_PEDI)))),
                             '9999999990.00') PPU

                INTO INTERFAZRECORD(X) .PEDIDOS,
                     INTERFAZRECORD(X) .UNIDADESVENTA,
                     INTERFAZRECORD(X) .CLIENTES,
                     INTERFAZRECORD(X) .MONTOVENTA,
                     INTERFAZRECORD(X) .ACTIVIDAD,
                     INTERFAZRECORD(X) .PUP,
                     INTERFAZRECORD(X) .PYP,
                     INTERFAZRECORD(X) .PORCEFALTANTE,
                     INTERFAZRECORD(X) .PPU

                FROM INT_FUENT_VENTA_REAL_VACUM VA
               WHERE VA.PERD_OID_PERI = INTERFAZRECORD(X)
              .IDPERIODO
                 AND VA.ZZON_OID_ZONA = INTERFAZRECORD(X).IDZONA;
            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                INTERFAZRECORD(X).PEDIDOS := NULL;
                INTERFAZRECORD(X).UNIDADESVENTA := NULL;
                INTERFAZRECORD(X).CLIENTES := NULL;
                INTERFAZRECORD(X).MONTOVENTA := NULL;

            END;

            LSLINEA := INTERFAZRECORD(X)
                      .PERIODOACTUAL || ';' || INTERFAZRECORD(X)
                      .CODIGOZONA || ';' || INTERFAZRECORD(X)
                      .GERENTEZONA || ';' || INTERFAZRECORD(X)
                      .INGRESOS || ';' || INTERFAZRECORD(X)
                      .REINGRESOS || ';' || INTERFAZRECORD(X)
                      .EGRESOS || ';' || INTERFAZRECORD(X)
                      .REGULARES || ';' || INTERFAZRECORD(X)
                      .INICIALES || ';' || INTERFAZRECORD(X)
                      .CAPITALIZACION || ';' || INTERFAZRECORD(X)
                      .ACTIVIDAD || ';' || INTERFAZRECORD(X)
                      .PEDIDOS || ';' || INTERFAZRECORD(X)
                      .UNIDADESVENTA || ';' || INTERFAZRECORD(X)
                      .CLIENTES || ';' || INTERFAZRECORD(X)
                      .MONTOVENTA || ';' || INTERFAZRECORD(X)
                      .PUP || ';' || INTERFAZRECORD(X)
                      .PYP || ';' || INTERFAZRECORD(X)
                      .PPU || ';' || INTERFAZRECORD(X)
                      .MONTOFALTANTE || ';' || INTERFAZRECORD(X)
                      .PORCEFALTANTE || ';' || INTERFAZRECORD(X)
                      .ENTREGADAS || ';' || INTERFAZRECORD(X).RECIBIDAS;
            UTL_FILE.PUT_LINE(V_HANDLE, LSLINEA);

          END LOOP;
        END IF;
        EXIT WHEN C_INTERFAZ2%NOTFOUND;
      END LOOP;
      CLOSE C_INTERFAZ2;
    END IF;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);

        /* Procedimiento final para generar interfaz */
        GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_PR_INT_RESUL_FACT: ' || LS_SQLERRM);
  END EVI_PR_INT_RESUL_FACT;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Saldos de Consultora
  Fecha Creacion    : 15/01/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE EVI_PR_INT_SALDO_CONSU(PSCODIGOPAIS     VARCHAR2,
                                   PSCODIGOSISTEMA  VARCHAR2,
                                   PSCODIGOINTERFAZ VARCHAR2,
                                   PSNOMBREARCHIVO  VARCHAR2,
                                   PSCODIGOMARCA    VARCHAR2,
                                   PSCODIGOCANAL    VARCHAR2) IS
    CURSOR C_INTERFAZ(VSCODIGOPERIODO VARCHAR2) IS

      SELECT CC.COD_ZONA CODIGOZONA,
             CC.COD_CLIE CODIGOCOLSULTORA,
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(CC.COD_CLIE, 'NOM_CLIE') NOMBRE,
             GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(CC.COD_CLIE),
                                                    'TF') TELEFONO,
             SUM(CC.VAL_SALD_DEUD) SALDOCONSULTORA,
             GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_ULTIM_PEDID(GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(CC.COD_CLIE)) ULTIMOPERIODO
        FROM INT_SOLIC_CONSO_CABEC CC
       WHERE CC.COD_PERI = VSCODIGOPERIODO
         AND CC.IND_ERRO_DEUD = '2'
         AND CC.IND_ERROR_SGPE = '0'
         AND CC.IND_ERRO_REMP = '0'
       GROUP BY CC.COD_ZONA, CC.COD_CLIE;

    CURSOR C_INTERFAZ2 IS

      SELECT CC.COD_ZONA CODIGOZONA,
             CC.COD_CLIE CODIGOCOLSULTORA,
             GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_CODIG(CC.COD_CLIE, 'NOM_CLIE') NOMBRE,
             GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(CC.COD_CLIE),
                                                    'TF') TELEFONO,
             SUM(CC.VAL_SALD_DEUD) SALDOCONSULTORA,
             GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_ULTIM_PEDID(GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(CC.COD_CLIE)) ULTIMOPERIODO
        FROM INT_SOLIC_CONSO_CABEC CC, INT_EVI_TEMPO_RESUL_COBRA_ZONA RZ

       WHERE CC.COD_PERI = RZ.COD_PERI
         AND RZ.COD_ZONA = CC.COD_ZONA
         AND CC.IND_ERRO_DEUD = '2'
         AND CC.IND_ERROR_SGPE = '0'
         AND CC.IND_ERRO_REMP = '0'
       GROUP BY CC.COD_ZONA, CC.COD_CLIE;

    TYPE INTERFAZREC IS RECORD(
      CODIGOZONA       VARCHAR2(4),
      CODIGOCOLSULTORA VARCHAR2(15),
      NOMBRECLIENTE    VARCHAR2(100),
      TELEFONO         VARCHAR2(100),
      SALDOCONSULTORA  VARCHAR2(14),
      ULTIMOPERIODO    VARCHAR2(6));
    TYPE INTERFAZRECTAB IS TABLE OF INTERFAZREC;
    INTERFAZRECORD INTERFAZRECTAB;
    /* Variables usadas para la generacion del archivo de texto */
    LSDIRTEMPO      BAS_INTER.DIR_TEMP%TYPE;
    V_HANDLE        UTL_FILE.FILE_TYPE;

    LSLINEA         VARCHAR2(1000);

    LSNOMBREARCHIVO VARCHAR2(50);

    LSCODIGOPERIODOACTUAL VARCHAR2(6);
    LSCODIGOPERIODOCRUCE  VARCHAR2(6);
    lbAbrirUtlFile  BOOLEAN;
  BEGIN
     BEGIN
       lbAbrirUtlFile := TRUE;
      --DETERMINO SI HAY CRUCE
      SELECT DISTINCT FIRST_VALUE(B.COD_PERI) OVER(ORDER BY B.COD_PERI ASC ROWS UNBOUNDED PRECEDING),
                      FIRST_VALUE(B.COD_PERI) OVER(ORDER BY B.COD_PERI DESC ROWS UNBOUNDED PRECEDING)
        INTO LSCODIGOPERIODOACTUAL, LSCODIGOPERIODOCRUCE
        FROM CRA_PERIO A, SEG_PERIO_CORPO B
       WHERE A.PAIS_OID_PAIS =
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(PSCODIGOPAIS)
         AND A.MARC_OID_MARC =
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(PSCODIGOMARCA)
         AND A.CANA_OID_CANA =
             GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(PSCODIGOCANAL)
         AND A.FEC_INIC <= TRUNC(SYSDATE)
         AND A.FEC_FINA >= TRUNC(SYSDATE)
         AND B.OID_PERI = A.PERI_OID_PERI;

      IF LSCODIGOPERIODOACTUAL = LSCODIGOPERIODOCRUCE THEN

        OPEN C_INTERFAZ(LSCODIGOPERIODOACTUAL);
        LOOP
          FETCH C_INTERFAZ BULK COLLECT
            INTO INTERFAZRECORD LIMIT W_FILAS;

          /* Procedimiento inicial para generar interfaz */
          IF lbAbrirUtlFile THEN
             GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                 psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
              lbAbrirUtlFile := FALSE;
          END IF;
          IF INTERFAZRECORD.COUNT > 0 THEN
            FOR X IN INTERFAZRECORD.FIRST .. INTERFAZRECORD.LAST LOOP
              LSLINEA := INTERFAZRECORD(X)
                        .CODIGOZONA || ';' || INTERFAZRECORD(X)
                        .CODIGOCOLSULTORA || ';' || INTERFAZRECORD(X)
                        .NOMBRECLIENTE || ';' || INTERFAZRECORD(X)
                        .TELEFONO || ';' || INTERFAZRECORD(X)
                        .SALDOCONSULTORA || ';' || INTERFAZRECORD(X)
                        .ULTIMOPERIODO;
              UTL_FILE.PUT_LINE(V_HANDLE, LSLINEA);
            END LOOP;
          END IF;
          EXIT WHEN C_INTERFAZ%NOTFOUND;
        END LOOP;
        CLOSE C_INTERFAZ;

      ELSE

        --Limpiamos la Tabla
        DELETE INT_EVI_TEMPO_RESUL_COBRA_ZONA;

        --Cargamos los periodos de facturacion por zona
        INSERT INTO INT_EVI_TEMPO_RESUL_COBRA_ZONA
          SELECT Z.OID_ZONA,
                 Z.COD_ZONA,
                 EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_PERIO_COBRA(LSCODIGOPERIODOACTUAL,
                                                              LSCODIGOPERIODOCRUCE,
                                                              Z.COD_ZONA)
            FROM ZON_ZONA Z;

        COMMIT;

        OPEN C_INTERFAZ2;
        LOOP
          FETCH C_INTERFAZ2 BULK COLLECT
            INTO INTERFAZRECORD LIMIT W_FILAS;

          /* Procedimiento inicial para generar interfaz */
          IF lbAbrirUtlFile THEN
             GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                 psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
              lbAbrirUtlFile := FALSE;
          END IF;

          IF INTERFAZRECORD.COUNT > 0 THEN
            FOR X IN INTERFAZRECORD.FIRST .. INTERFAZRECORD.LAST LOOP
              LSLINEA := INTERFAZRECORD(X)
                        .CODIGOZONA || ';' || INTERFAZRECORD(X)
                        .CODIGOCOLSULTORA || ';' || INTERFAZRECORD(X)
                        .NOMBRECLIENTE || ';' || INTERFAZRECORD(X)
                        .TELEFONO || ';' || INTERFAZRECORD(X)
                        .SALDOCONSULTORA || ';' || INTERFAZRECORD(X)
                        .ULTIMOPERIODO;
              UTL_FILE.PUT_LINE(V_HANDLE, LSLINEA);
            END LOOP;
          END IF;
          EXIT WHEN C_INTERFAZ2%NOTFOUND;
        END LOOP;
        CLOSE C_INTERFAZ2;

        --Limpiamos la Tabla
        DELETE INT_EVI_TEMPO_RESUL_COBRA_ZONA;

      END IF;

    END;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(V_HANDLE);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_PR_INT_SALDO_CONSU: ' ||
                              LS_SQLERRM);
  END EVI_PR_INT_SALDO_CONSU;

  /**************************************************************************
  Descripcion       : Devuelve el monto Faltante para una zona y periodo
  Fecha Creacion    : 14/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_MONTO_FALTA_ZONA(LNOIDPAIS    IN NUMBER,
                                         LNOIDPERIODO IN NUMBER,
                                         LNOIDZONA    IN NUMBER)
    RETURN NUMBER IS

    LNMONTOFALTANTE NUMBER;
  BEGIN

    SELECT SUM(CASE
                 WHEN SP.VAL_PREC_CATA_UNIT_LOCA <> 0 THEN
                  (SP.NUM_UNID_DEMA_REAL - SP.NUM_UNID_COMPR) *
                  SP.VAL_PREC_NETO_UNIT_LOCA
                 ELSE
                  0
               END)
      INTO LNMONTOFALTANTE
      FROM PED_SOLIC_CABEC     SC,
           PED_SOLIC_POSIC     SP,
           PED_TIPO_SOLIC_PAIS TS,
           PED_TIPO_SOLIC      TSOL
     WHERE SP.SOCA_OID_SOLI_CABE = SC.OID_SOLI_CABE
       AND SC.IND_OC = 1
       AND SC.ZZON_OID_ZONA = LNOIDZONA
       AND SC.IND_PEDI_PRUE = 0
       AND SC.PAIS_OID_PAIS = LNOIDPAIS
       AND SC.PERD_OID_PERI = LNOIDPERIODO
       AND SC.IND_TS_NO_CONSO = 1
       AND SC.TSPA_OID_TIPO_SOLI_PAIS = TS.OID_TIPO_SOLI_PAIS
       AND TS.TSOL_OID_TIPO_SOLI = TSOL.OID_TIPO_SOLI
       AND TSOL.IND_DEVO = 0
       AND TSOL.IND_ANUL = 0
       AND EXISTS (SELECT 1
              FROM PED_ESTAD_POSIC EP
             WHERE EP.COD_ESTA_POSI <> 'AN'
               AND EP.OID_ESTA_POSI = SP.ESPO_OID_ESTA_POSI)
       AND EXISTS
     (SELECT 1
              FROM INT_PARAM_TIPO_SOLIC PT
             WHERE PT.NUM_UNID_FALT = 1
               AND PT.TSPA_OID_TIPO_SOLI_PAIS = TS.OID_TIPO_SOLI_PAIS)
       AND EXISTS
     (SELECT 1
              FROM PRE_OFERT_DETAL OD, PRE_TIPO_OFERT TIPOOFERTA
             WHERE SP.VAL_CODI_VENT = OD.VAL_CODI_VENT
               AND OD.TOFE_OID_TIPO_OFER = TIPOOFERTA.OID_TIPO_OFER
               AND SP.OFDE_OID_DETA_OFER = OD.OID_DETA_OFER
               AND TIPOOFERTA.IND_ESTA = 1);
    RETURN LNMONTOFALTANTE;
  EXCEPTION

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_MONTO_FALTA_ZONA: ' ||
                              LS_SQLERRM);

  END EVI_FN_DEVUE_MONTO_FALTA_ZONA;

  /**************************************************************************
  Descripcion       : Devuelve el periodo de facturacion en el caso de que
                      exista cruce en el periodo.
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_PERIO_FACTU(PSCODIGOPERIODO      IN VARCHAR2,
                                    PSCODIGOPERIODOCRUCE IN VARCHAR2,
                                    PSCODIGOZONA         IN VARCHAR2,
                                    PNOIDMARCA           IN NUMBER,
                                    PNOIDCANAL           IN NUMBER)
    RETURN VARCHAR2 IS

    LSCODIGOPERIODO VARCHAR2(6);

  BEGIN

    SELECT MIN(PSCODIGOPERIODO)
      INTO LSCODIGOPERIODO
      FROM FAC_CONTR_CIERR C, ZON_ZONA Z, ZON_REGIO R, FAC_TIPOS_CIERR T
     WHERE C.PERD_OID_PERI =
           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(PSCODIGOPERIODO,
                                                      PNOIDMARCA,
                                                      PNOIDCANAL)
       AND Z.ZORG_OID_REGI = R.OID_REGI
       AND R.OID_REGI = C.ZORG_OID_REGI
       AND T.OID_TIPO_CIER = C.TCIE_OID_TIPO_CIER
       AND T.COD_TIPO_CIER = 'R'
       AND Z.COD_ZONA = PSCODIGOZONA;

    --SI existe Algun Pedido
    IF LSCODIGOPERIODO = PSCODIGOPERIODO THEN

      RETURN PSCODIGOPERIODOCRUCE;
    ELSE
      RETURN PSCODIGOPERIODO;
    END IF;

  EXCEPTION

    WHEN NO_DATA_FOUND THEN
      RETURN PSCODIGOPERIODO;

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_PERIO_FACTU: ' ||
                              LS_SQLERRM);

  END EVI_FN_DEVUE_PERIO_FACTU;

  /**************************************************************************
  Descripcion       : Devuelve el periodo de cobranza en el caso de que
                      exista cruce en el periodo.
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_PERIO_COBRA(PSCODIGOPERIODO      IN VARCHAR2,
                                    PSCODIGOPERIODOCRUCE IN VARCHAR2,
                                    PSCODIGOZONA         IN VARCHAR2)
    RETURN VARCHAR2 IS

    LNRETORNO NUMBER;

  BEGIN

    --Busco si hay pedios en la campaña de cruce
    SELECT MIN(1)
      INTO LNRETORNO
      FROM INT_SOLIC_CONSO_CABEC A
     WHERE A.COD_PERI = PSCODIGOPERIODOCRUCE
       AND A.COD_ZONA = PSCODIGOZONA;

    --SI existen Pedidos en la campaña
    IF LNRETORNO = 1 THEN
      RETURN PSCODIGOPERIODOCRUCE;
    ELSE
      RETURN PSCODIGOPERIODO;
    END IF;

  EXCEPTION

    WHEN NO_DATA_FOUND THEN
      RETURN PSCODIGOPERIODO;

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_PERIO_COBRA: ' ||
                              LS_SQLERRM);

  END EVI_FN_DEVUE_PERIO_COBRA;

  /**************************************************************************
  Descripcion       : Indica si la zona se encuentra en la tabla com_zona_deman_antic
                      'S' se enecuentra
                      'N' no se encuentra
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:
      psCodigoCliente   : Codigo de cliente
  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_EXIST_ZONA_DEMAN(PSCODIGOZONA IN VARCHAR2) RETURN VARCHAR2 IS

    LSINDICADOR VARCHAR2(1);

  BEGIN

    --Busco si hay pedios en la campaña de cruce
    SELECT MIN('1')
      INTO LSINDICADOR
      FROM COM_ZONA_DEMAN_ANTIC
     WHERE COD_ZONA = PSCODIGOZONA;

    IF LSINDICADOR = '1' THEN
      RETURN 'S';
    ELSE
      RETURN 'N';
    END IF;

  EXCEPTION

    WHEN NO_DATA_FOUND THEN
      RETURN 'N';

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_EXIST_ZONA_DEMAN: ' ||
                              LS_SQLERRM);

  END EVI_FN_EXIST_ZONA_DEMAN;

  /**************************************************************************
  Descripcion       : Indica si el registro de cuenta coorriente es valida
                      para el periodo
                      'S' es valida
                      'N' no es valida
  Fecha Creacion    : 17/01/2008

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_INDIC_CCVAL(PSFECHACRA IN DATE,
                                    PSFECHACC  IN DATE,
                                    PSTIPOVAL  IN VARCHAR2) RETURN VARCHAR2 IS

  BEGIN

    IF PSTIPOVAL = 'ANTERIOR' THEN

      IF PSFECHACC > PSFECHACRA THEN
        RETURN 'S';

      ELSE
        RETURN 'N';
      END IF;
    END IF;

    IF PSTIPOVAL = 'ACTUAL' THEN

      IF PSFECHACC <= PSFECHACRA THEN
        RETURN 'S';
      ELSE
        RETURN 'N';
      END IF;

    END IF;

  EXCEPTION

    WHEN NO_DATA_FOUND THEN
      RETURN 'N';

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_INDIC_CCVAL: ' ||
                              LS_SQLERRM);

  END EVI_FN_DEVUE_INDIC_CCVAL;

  /**************************************************************************
  Descripcion       : Obtienene el documento  de identidad del cliente
                      con indicador de documento principal=1
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_DOCUM_IDENT(PNIDCLIENTE NUMBER) RETURN VARCHAR2 IS

    LSDOCUMENTO VARCHAR2(30);

  BEGIN

    --Busco si hay pedios en la campaña de cruce
    SELECT IDEN.NUM_DOCU_IDEN
      INTO LSDOCUMENTO
      FROM MAE_CLIEN_IDENT IDEN

     WHERE IDEN.CLIE_OID_CLIE = PNIDCLIENTE
       AND IDEN.VAL_IDEN_DOCU_PRIN = 1;

    RETURN LSDOCUMENTO;

  EXCEPTION

    WHEN NO_DATA_FOUND THEN
      RETURN NULL;

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_DOCUM_IDENT: ' ||
                              LS_SQLERRM);

  END EVI_FN_DEVUE_DOCUM_IDENT;

  /**************************************************************************
  Descripcion       : Obtiene el monto de cobranza a 31 dias
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_COBRA_31DIA(PNNUMEROHISTORICO    NUMBER,
                                    PNOIDULTIMOPROCESO   NUMBER,
                                    PDFECHAVENCIMIENTO   DATE,
                                    PDFECHADOCUMENTACION DATE,
                                    PSCODIGOZONA         VARCHAR2,
                                    PNIMPORTEPAGO        NUMBER,
                                    PNTASA               NUMBER,
                                    PNIDMOVIMIENTO       NUMBER)

   RETURN NUMBER IS

    LNMONTO31DIAS       NUMBER(14, 2);
    LNMONTO31HISTO      NUMBER(14, 2);
    LNNUMERODIASHABILES NUMBER(3);
    LDFECHAMAXIMA       DATE;
    LNIDSUBPRBANCO      NUMBER;
    LNIDSUBPREXCESO     NUMBER;
    LNIDSUBPREXCESO2    NUMBER;
  BEGIN

    IF PNNUMEROHISTORICO > 0 THEN

      --PagoBanco
      LNIDSUBPRBANCO := EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ID_SUBPR_CC('CCC002',
                                                                     '1');

      LNIDSUBPREXCESO := EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ID_SUBPR_CC('CCCEV2',
                                                                      '2');

      LNIDSUBPREXCESO2 := EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ID_SUBPR_CC('CCCEV1',
                                                                       '2');

      --SI es un pago
      IF (PNOIDULTIMOPROCESO = LNIDSUBPRBANCO) OR
         (PNOIDULTIMOPROCESO = LNIDSUBPREXCESO) OR
         (PNOIDULTIMOPROCESO = LNIDSUBPREXCESO2) THEN
        --CALCULAMOS LA DIFERENCIA DE DIAS
        LNNUMERODIASHABILES := COM_PKG_REPOR.COM_FN_DEVUE_NUMER_DIAS(PDFECHADOCUMENTACION,
                                                                     31,
                                                                     PSCODIGOZONA);

        SELECT (PDFECHADOCUMENTACION + LNNUMERODIASHABILES)
          INTO LDFECHAMAXIMA
          FROM DUAL;

        IF LDFECHAMAXIMA > PDFECHAVENCIMIENTO THEN

          LNMONTO31DIAS := PNIMPORTEPAGO / (1 + PNTASA / 100);
          --Accedemos al historico
          IF PNNUMEROHISTORICO > 1 THEN

            SELECT NVL(SUM(A.IMP_PAGO / (1 + PNTASA / 100)), 0)
              INTO LNMONTO31HISTO
              FROM CCC_HISTO_MOVIM_CC A
             WHERE A.MVCC_OID_MOVI_CC = PNIDMOVIMIENTO
               AND A.SUBP_OID_SUBP IN
                   (LNIDSUBPRBANCO, LNIDSUBPREXCESO, LNIDSUBPREXCESO2);
            RETURN LNMONTO31DIAS + LNMONTO31HISTO;

          END IF;

          RETURN LNMONTO31DIAS;

        ELSE

          RETURN 0;

        END IF;

      ELSE
        RETURN 0;

      END IF;

    ELSE

      RETURN 0;

    END IF;

  EXCEPTION

    WHEN NO_DATA_FOUND THEN
      RETURN NULL;

    WHEN OTHERS THEN
      RETURN NULL;
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_COBRA_31DIA: ' ||
                              LS_SQLERRM);

  END EVI_FN_DEVUE_COBRA_31DIA;

  /**************************************************************************
  Descripcion       : Obtiene el id de subproceso de cuenta corriente
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_ID_SUBPR_CC(PSCODIGOPROCESO    VARCHAR2,
                                    PSCODIGOSUBPROCESO VARCHAR2)
    RETURN NUMBER IS

    LNIDSUBPROCESO NUMBER(12);

  BEGIN

    SELECT MIN(B.OID_SUBP)
      INTO LNIDSUBPROCESO
      FROM CCC_SUBPR B, CCC_PROCE C
     WHERE B.COD_SUBP = PSCODIGOSUBPROCESO
       AND C.COD_PROC = PSCODIGOPROCESO
       AND B.CCPR_OID_PROC = C.OID_PROC;

    RETURN LNIDSUBPROCESO;
  EXCEPTION

    WHEN NO_DATA_FOUND THEN
      RETURN NULL;

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_ID_SUBPR_CC: ' ||
                              LS_SQLERRM);

  END EVI_FN_DEVUE_ID_SUBPR_CC;

  /**************************************************************************
  Descripcion       : Obtiene el monto de reclamos para un registro de cuenta corriente
  Fecha Creacion    : 17/01/2008
  Parametros Entrada:

  Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_RECLA_CC(PNNUMEROHISTORICO   NUMBER,
                                 PNOIDULTIMOPROCESO  NUMBER,
                                 PNIMPORTEMOVIMIENTO NUMBER,
                                 PNTASA              NUMBER,
                                 PNIDMOVIMIENTO      NUMBER)

   RETURN NUMBER IS

    LNMONTORECLAMOS      NUMBER(14, 2);
    LNMONTORECLAMOSHISTO NUMBER(14, 2);

    LNIDSUBPRRECLAMO NUMBER;

  BEGIN

    IF PNNUMEROHISTORICO > 0 THEN

      --Reclamos
      LNIDSUBPRRECLAMO := EVI_PKG_EJECU_VIRTU.EVI_FN_DEVUE_ID_SUBPR_CC('CCC002',
                                                                       '2');

      --SI es un pago
      IF PNOIDULTIMOPROCESO = LNIDSUBPRRECLAMO THEN
        --CALCULAMOS LA DIFERENCIA DE DIAS

        LNMONTORECLAMOS := PNIMPORTEMOVIMIENTO / (1 + PNTASA / 100);
        --Accedemos al historico
        IF PNNUMEROHISTORICO > 1 THEN

          SELECT NVL(SUM(A.IMP_PAGO / (1 + 15 / 100)), 0)
            INTO LNMONTORECLAMOSHISTO
            FROM CCC_HISTO_MOVIM_CC A
           WHERE A.MVCC_OID_MOVI_CC = PNIDMOVIMIENTO
             AND A.SUBP_OID_SUBP = LNIDSUBPRRECLAMO;
          RETURN LNMONTORECLAMOS + LNMONTORECLAMOSHISTO;

        END IF;

        RETURN LNMONTORECLAMOS;

      ELSE
        RETURN 0;

      END IF;

    ELSE

      RETURN 0;

    END IF;

  EXCEPTION

    WHEN NO_DATA_FOUND THEN
      RETURN NULL;

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_RECLA_CC: ' || LS_SQLERRM);

  END EVI_FN_DEVUE_RECLA_CC;

  /**************************************************************************
   Descripcion       : Debuel el Id de Periodo N siguiente
   Fecha Creacion    : 17/01/2008

   Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_ID_PERIO_NSGTE(PSCODIGOPERIODO VARCHAR2,
                                       PNIDPAIS        NUMBER,
                                       PNIDMARCA       NUMBER,
                                       PNIDCANAL       NUMBER,
                                       PNNSIGUIENTE    NUMBER) RETURN NUMBER IS

    LSNCODIGOPERIODO VARCHAR2(6);

  BEGIN

    LSNCODIGOPERIODO := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(PSCODIGOPERIODO,
                                                               PNIDPAIS,
                                                               PNIDMARCA,
                                                               PNIDCANAL,
                                                               PNNSIGUIENTE);

    RETURN GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(LSNCODIGOPERIODO,
                                                      PNIDMARCA,
                                                      PNIDCANAL);

  EXCEPTION

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR EVI_FN_DEVUE_ID_PERIO_NSGTE: ' ||
                              LS_SQLERRM);

  END EVI_FN_DEVUE_ID_PERIO_NSGTE;

  /**************************************************************************
   Descripcion       : Devuelve el Monto de Actividad
   Fecha Creacion    : 17/01/2008

   Autor             : José A. Cairampoma
  ***************************************************************************/
  FUNCTION EVI_FN_DEVUE_ACTIV(PNNUMACTIVAS NUMBER, PNNUMPEDIDOS NUMBER)
    RETURN NUMBER IS

  BEGIN

    IF PNNUMACTIVAS IS NULL THEN
      RETURN NULL;
    END IF;

    IF PNNUMACTIVAS = 0 THEN
      RETURN NULL;
    END IF;

    RETURN PNNUMPEDIDOS * 100 / PNNUMACTIVAS;

  EXCEPTION

    WHEN OTHERS THEN
      LN_SQLCODE := SQLCODE;
      LS_SQLERRM := SUBSTR(SQLERRM, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR evi_fn_devue_activ: ' || LS_SQLERRM);

  END EVI_FN_DEVUE_ACTIV;



/***************************************************************************
Descripcion       : Genera Interfaz de Envio de Cabecereas de Pedidos Digitados
Fecha Creacion    : 03/12/2007
Autor             : José A. Cairampoma
***************************************************************************/
PROCEDURE INT_PR_EVI_ENVIA_PEDIG_CABEC(psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCodigoPeriodo VARCHAR2
)
IS
   CURSOR c_interfaz IS

   SELECT COD_PAIS codigoPais,
          COD_PERI codigoPeriodo,
          COD_CLIE cliente,
          NUM_LOTE numeroLote,
          DECODE(gen_pkg_gener.GEN_FN_CLIEN_DATOS_CODIG(COD_CLIE,'COD_ESTA_CLIE'),'01','P','07','P',NULL,NULL,'N') tipoOC,
          TO_CHAR(FEC_SOLI,'YYYYMMDD') fecha
     FROM PED_SOLIC_DIGIT_CABEC
    WHERE IND_OCS='0';

   TYPE interfazRec IS RECORD
   (
     codigoPais     seg_pais.cod_pais%TYPE,
     codigoPeriodo  seg_perio_corpo.cod_peri%TYPE,
     codigoCliente  mae_clien.cod_clie%TYPE,
     numeroLote     PED_SOLIC_DIGIT_CABEC.NUM_LOTE%TYPE,
     tipoOC         VARCHAR2(1),
     fecha          VARCHAR2(10)
  );
   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;
  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;

  lsLinea             VARCHAR2(1000);

  lsNombreArchivo     VARCHAR2(50);
BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */

        OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           IF interfazRecord.COUNT > 0 THEN

              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

                  lsLinea :=  interfazRecord(x).codigoPeriodo   ||';'||
                              interfazRecord(x).codigoCliente   ||';'||
                              interfazRecord(x).tipoOC          ||';'||
                              interfazRecord(x).fecha;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                   UPDATE PED_SOLIC_DIGIT_CABEC
                      SET FEC_MODI = SYSDATE, IND_OCS = '1'
                    WHERE COD_PAIS = interfazRecord(x).codigoPais
                      AND COD_PERI = interfazRecord(x).codigoPeriodo
                      AND COD_CLIE = interfazRecord(x).codigoCliente
                      AND NUM_LOTE = interfazRecord(x).numeroLote;

              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;

    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_DAT_CANAL_VENTA: '||ls_sqlerrm);
END INT_PR_EVI_ENVIA_PEDIG_CABEC;




END EVI_PKG_EJECU_VIRTU;
/

