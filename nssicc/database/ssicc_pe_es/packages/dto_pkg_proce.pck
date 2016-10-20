CREATE OR REPLACE PACKAGE DTO_PKG_PROCE IS

  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  W_FILAS NUMBER:=1000;

  /**************************************************************************
  Descripcion       : Evaluar todas las condiciones de la matriz de  aplicación
                      para en base a su parametrización asignar los descuentos
  Fecha Creacion    : 26/07/2012
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de marca
    psCodigoCanal    :  Codigo de canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE DTO_PR_PROCE_CALCU_DESCU
    (psCodigoPais               VARCHAR2,
     psCodigoMarca              VARCHAR2,
     psCodigoCanal              VARCHAR2,
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2);

  /**************************************************************************
  Descripcion       : Obtiene el grupo de Descuento, Rango de Descuento y
                      Porcentaje de acuerdo a los datos del cliente o de la
                      posicion de la solicitud
  Fecha Creacion    : 27/07/2012
  Parametros Entrada:
    pnCategoriaDescuento   :  Categoria de Descuento
    pnSubCategoria1        :  Sub Categoria1
    pnSubCategoria2        :  Sub Categoria 2
    pnMontoPedido          :  Monto de Pedido a Validar
    psCodigoPeriodo        :  Codigo de Periodo
    pnCodGrupoDescuento    :  Codigo de Grupo de Descuento
    pnCodRangoDescuento    :  Codigo de Rango de Descuento
    pnPorcDescuento        :  Porcentaje de Descuento

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE DTO_PR_OBTEN_PORCE_DESCU
    (pnCategoriaDescuento       NUMBER,
     pnSubCategoria1            NUMBER,
     pnSubCategoria2            NUMBER,
     pnMontoPedido              NUMBER,
     psCodigoPeriodo            VARCHAR2,
     pnCodGrupoDescuento        OUT NUMBER,
     pnCodRangoDescuento        OUT NUMBER,
     pnPorcDescuento            OUT NUMBER);

  /**************************************************************************
  Descripcion       : Obtiene el porcentaje Adicional, siempre y cuando haya
                      cumplido alguna validacion el cliente
  Fecha Creacion    : 29/11/2012
  Parametros Entrada:
    pnCodGrupoDescuento    :  Codigo de Grupo de Descuento
    pnOidCliente           :  Oid Cliente de Rango de Descuento
    pnMontoBaseCalculo     :  Monto Base Calculo Descuento
    psCodigoPeriodo        :  Codigo de Periodo

  Autor             : Sergio Apaza

  ***************************************************************************/
  FUNCTION DTO_FN_OBTIE_DSCTO_ADICI
    (pnCodGrupoDescuento    NUMBER,
     pnOidCliente           NUMBER,
     pnMontoBaseCalculo     NUMBER,
     psCodigoPeriodo        VARCHAR2) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Recupera el porcentaje Adicional, siempre y cuando haya
                      cumplido el cliente alguna validacion del descuento Adicional
  Fecha Creacion    : 29/11/2012
  Parametros Entrada:
    pnCodGrupoDescuento    :  Codigo de Grupo de Descuento
    pnOidCliente           :  Oid Cliente de Rango de Descuento
    pnOidSolicitud         : Oid Solicitud

  Autor             : Sergio Apaza

  ***************************************************************************/
  FUNCTION DTO_FN_RECUP_DSCTO_ADICI
    (pnCodGrupoDescuento    NUMBER,
     pnOidCliente           NUMBER,
     pnOidSolicitud         NUMBER,
     pnPorcentaje           NUMBER) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Obtiene el porcentaje Adicional, siempre y cuando haya
                      cumplido el cliente alguna validacion del descuento Adicional
  Fecha Creacion    : 26/11/2014
  Parametros Entrada:
    pnCodGrupoDescuento    :  Codigo de Grupo de Descuento
    pnOidCliente           :  Oid Cliente
    pnOidSolicitud           :  Oid Solicitud
    pnMontoBaseCalculo     :  Monto Base Calculo Descuento
    psCodigoPeriodo        :  Codigo de Periodo

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE DTO_PR_CALCU_DSCTO_ADICI
    (pnCodGrupoDescuento    NUMBER,
     pnOidCliente           NUMBER,
     pnOidSolicitud         NUMBER,
     pnMontoBaseCalculo     NUMBER,
     psCodigoPeriodo        VARCHAR2);


 /**************************************************************************
Descripcion       : devolverá el monto de la venta retail acumulada de la
                    campanha
Fecha Creacion    : 08/07/2015
Parametros Entrada:
  oidCliente     :  Oid Cliente
  oidPeriodo     :  Oid Periodo
  fechaFact      :  Fecha Facturacion
  oidZona        :  Oid Zona
  indProceso     :  Ind Proceso
  codPais        :  Codigo Pais

Autor            :

***************************************************************************/
  FUNCTION DTO_FN_DEVUE_MONTO_VENTA_RETAI
    (oidCliente        NUMBER,
     oidPeriodo        NUMBER,
     fechaFact         DATE,
     oidZona           NUMBER,
     indProceso        VARCHAR2,
     codPais           VARCHAR2) RETURN NUMBER;


/***************************************************************************/
  FUNCTION DTO_FN_DEVUE_REGIO_PILOT_RETAI
    (oidZona           NUMBER) RETURN NUMBER;

END DTO_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY "DTO_PKG_PROCE" IS

/**************************************************************************
Descripcion       : Evaluar todas las condiciones de la matriz de  aplicación
                    para en base a su parametrización asignar los descuentos
Fecha Creacion    : 26/07/2012
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de marca
  psCodigoCanal    :  Codigo de canal
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE DTO_PR_PROCE_CALCU_DESCU
  (psCodigoPais               VARCHAR2,
   psCodigoMarca              VARCHAR2,
   psCodigoCanal              VARCHAR2,
   psCodigoPeriodo            VARCHAR2,
   psCodigoUsuario            VARCHAR2)
IS
  lnOidPais                   SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca                  SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal                  SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo                CRA_PERIO.OID_PERI%TYPE;

  lnCodGrupoDescuento         DTO_DESCU_GRUPO.COD_GRUP_DESC%TYPE;
  lnCodRangoDescuento         DTO_DESCU_GRUPO_RANGO.COD_RANG_DESC%TYPE;
  lnPorcDescuento             DTO_DESCU_GRUPO_RANGO.POR_DESC%TYPE;
  lnPorcDescuentoAux          DTO_DESCU_GRUPO_RANGO.POR_DESC%TYPE;
  lnOidTipoCliente            MAE_TIPO_CLIEN.OID_TIPO_CLIE%TYPE;
  lnOidSubTipoCliente         MAE_SUBTI_CLIEN.OID_SUBT_CLIE%TYPE;
  lnMontoBaseDcto             PED_SOLIC_CABEC.VAL_MONT_BAPL_DCTO%TYPE;
  lnPorcDescuentoAdic         DTO_DESCU_ADICI_CABEC.POR_DESC_ADIC%TYPE;

  lnOidSoliCabecRef           PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
  lsCodigoTipoSolRef          PED_TIPO_SOLIC.COD_TIPO_SOLI%TYPE;
  lnMontoBaseDctoRef          PED_SOLIC_CABEC.VAL_MONT_BAPL_DCTO%TYPE;
  lsIndicadorComiRef          PED_TIPO_SOLIC_PAIS.IND_COMI%TYPE;
  ldFechaFactRef              PED_SOLIC_CABEC.FEC_FACT%TYPE;
  lnOidPeriodoRef             CRA_PERIO.OID_PERI%TYPE;
  ldfecproc                   DATE;

  lbAplicarDcto               BOOLEAN;
  lnOidZonaRef                PED_SOLIC_CABEC.ZZON_OID_ZONA%TYPE;
  lnMontoVentaRetail          ret_venta_cabec.val_mont_bapl_dcto%TYPE;
  lnsiccgp3                   NUMBER;

  CURSOR c_Consultoras(oidPeriodo NUMBER) IS
    SELECT PSC.OID_SOLI_CABE,
           PSC.CLIE_OID_CLIE,
           (SELECT SUM(A.VAL_MONT_BAPL_DCTO)
              FROM PED_SOLIC_CABEC A
             WHERE A.PERD_OID_PERI = lnOidPeriodo
               AND A.CLIE_OID_CLIE = PSC.CLIE_OID_CLIE
               AND A.GRPR_OID_GRUP_PROC IN (3,4)
               AND A.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS),
           TSP.IND_COMI,
           PSC.MODU_OID_MODU,
           TSP.COD_TIPO_SOLI,
           PSC.FEC_FACT, PSC.ZZON_OID_ZONA,
           PSC.GRPR_OID_GRUP_PROC
      FROM PED_SOLIC_CABEC PSC,
           (SELECT E.OID_TIPO_SOLI_PAIS, E.IND_COMI, F.COD_TIPO_SOLI
              FROM PED_TIPO_SOLIC_PAIS E,
                   PED_TIPO_SOLIC F
             WHERE E.IND_COMI IN (1,2,3,4)
               AND E.TSOL_OID_TIPO_SOLI = F.OID_TIPO_SOLI
               ) TSP
     WHERE PSC.PERD_OID_PERI = lnOidPeriodo
       AND PSC.GRPR_OID_GRUP_PROC IN (3,4)
       AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS;

 TYPE interfazConsultoras IS RECORD
  (
   oidSolicitud              PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE,
   oidCliente                MAE_CLIEN.OID_CLIE%TYPE,
   montoPedido               PED_SOLIC_CABEC.VAL_MONT_BAPL_DCTO%TYPE,
   indicadorComision         PED_TIPO_SOLIC_PAIS.IND_COMI%TYPE,
   oidModulo                 PED_SOLIC_CABEC.MODU_OID_MODU%TYPE,
   codigoTipoSolicitud       PED_TIPO_SOLIC.COD_TIPO_SOLI%TYPE,
   fechaFac                  PED_SOLIC_CABEC.FEC_FACT%TYPE,
   oidZona                   PED_SOLIC_CABEC.ZZON_OID_ZONA%TYPE,
   oidgrupproc               PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC%TYPE
  );


  TYPE interfazConsultorasTab  IS TABLE OF interfazConsultoras;
  interfazRecordN interfazConsultorasTab;

  TYPE t_oidPosicion          IS TABLE OF PED_SOLIC_POSIC.OID_SOLI_POSI%TYPE;
  TYPE t_porcDescuento        IS TABLE OF PED_SOLIC_POSIC.VAL_PORC_DESC%TYPE;
  TYPE t_importeDescuento1    IS TABLE OF PED_SOLIC_POSIC.VAL_IMPO_DESC_UNIT_LOCA%TYPE;
  TYPE t_importeDescuento2    IS TABLE OF PED_SOLIC_POSIC.VAL_IMPO_DESC_UNIT_DOCU%TYPE;
  TYPE t_grupoDescuento       IS TABLE OF PED_SOLIC_POSIC.BACA_OID_CABE%TYPE;
  TYPE t_rangoDescuento       IS TABLE OF PED_SOLIC_POSIC.ESLO_OID_ESLN%TYPE;

  v_oidPosicion               t_oidPosicion   := t_oidPosicion ();
  v_porcDescuento             t_porcDescuento  := t_porcDescuento();
  v_importeDescuento1         t_importeDescuento1  := t_importeDescuento1();
  v_importeDescuento2         t_importeDescuento2  := t_importeDescuento2();
  v_grupoDescuento            t_grupoDescuento  := t_grupoDescuento();
  v_rangoDescuento            t_rangoDescuento  := t_rangoDescuento();

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);
  
  BEGIN
    SELECT fec_proc
    INTO ldfecproc
    FROM bas_ctrl_fact bas
    WHERE cod_peri = psCodigoPeriodo;
  EXCEPTION
    WHEN OTHERS THEN
      ldfecproc := TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY');
  END;

  --(1)HACEMOS UNA PRIMERA BARRIDA PARA SUMAR EL MONTO REAL DEMANDADO REAL DE LAS
  --   POSICIONES DE LA SOLICITUD
  UPDATE PED_SOLIC_CABEC A
     SET A.VAL_MONT_BAPL_DCTO = (SELECT NVL(SUM(B.VAL_PREC_CATA_UNIT_LOCA * B.NUM_UNID_DEMA_REAL),0)
                                   FROM PED_SOLIC_POSIC B, PRE_OFERT_DETAL C, PRE_TIPO_OFERT D
                                  WHERE B.SOCA_OID_SOLI_CABE = A.OID_SOLI_CABE
                                    AND B.OFDE_OID_DETA_OFER = C.OID_DETA_OFER
                                    AND C.TOFE_OID_TIPO_OFER = D.OID_TIPO_OFER
                                    AND D.IND_APOR_MONT_ESCA = 1)
   WHERE A.PERD_OID_PERI = lnOidPeriodo
     AND A.GRPR_OID_GRUP_PROC IN (3,4)
     AND A.TSPA_OID_TIPO_SOLI_PAIS IN (SELECT E.OID_TIPO_SOLI_PAIS
                                         FROM PED_TIPO_SOLIC_PAIS E
                                        WHERE E.IND_COMI IN (1,2,3)
                                       );

  --Eliminamos la informacion de la tabla temporal
  EXECUTE IMMEDIATE 'TRUNCATE TABLE DTO_TMP_DESCU_ADICI';

  -- Verifica si hay procesos SICC activos en GP3:
  SELECT COUNT(0)
  INTO lnsiccgp3
  FROM 
    ped_secue_proce sp,
    ped_tipo_solic_pais tsp, ped_tipo_solic ts
    WHERE sp.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
          AND sp.grpr_oid_grup_proc = 4
          AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
          AND ts.cod_tipo_soli='SOC';

  --(2)BARREMOS TODAS LAS POSICIONES DE LAS SOLICITUDES ACTUALIZADOS EN EL PUNTO(1)
  --   CON INDICADOR DE OFERTA COMISIONABLE
  OPEN c_Consultoras(lnOidPeriodo);
  LOOP
    FETCH c_Consultoras BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lbAplicarDcto := TRUE;

        IF(interfazRecordN(x).indicadorComision = 2) THEN
          --Para Pedidos Especiales, ubicamos la primera orden de Compra 'SOC' que haya hecho la consultora
          --y con ese monto se realiza el proceso de descuento
          BEGIN
            SELECT VAL_MONT_BAPL_DCTO
              INTO lnMontoBaseDcto
              FROM (SELECT PSC.VAL_MONT_BAPL_DCTO
                      FROM PED_SOLIC_CABEC PSC,
                           PED_SOLIC_CABEC CAB,
                           PED_TIPO_SOLIC_PAIS TSP,
                           PED_TIPO_SOLIC TSO
                     WHERE PSC.Perd_Oid_Peri = lnOidPeriodo
                       AND PSC.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                       AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                       AND TSP.TSOL_OID_TIPO_SOLI = TSO.OID_TIPO_SOLI
                       AND PSC.SOCA_OID_SOLI_CABE = CAB.OID_SOLI_CABE(+)
                       AND NVL(CAB.ESSO_OID_ESTA_SOLI,0) <> 4
                       AND TSO.COD_TIPO_SOLI = 'SOC'
                     ORDER BY PSC.OID_SOLI_CABE)
             WHERE ROWNUM = 1;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnMontoBaseDcto := 0;
          END;
        ELSIF(interfazRecordN(x).indicadorComision = 3) THEN
          --Para Caso de acumulaci?n de descuentos
          BEGIN
            SELECT SUM(PSC.VAL_MONT_BAPL_DCTO)
              INTO lnMontoBaseDcto
              FROM PED_SOLIC_CABEC PSC,
                   PED_SOLIC_CABEC CAB,
                   PED_TIPO_SOLIC_PAIS TSP,
                   PED_TIPO_SOLIC TSO
             WHERE PSC.Perd_Oid_Peri = lnOidPeriodo
               AND PSC.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
               AND TSP.TSOL_OID_TIPO_SOLI = TSO.OID_TIPO_SOLI
               AND PSC.SOCA_OID_SOLI_CABE = CAB.OID_SOLI_CABE(+)
               AND NVL(CAB.ESSO_OID_ESTA_SOLI,0) <> 4
               AND TSO.COD_TIPO_SOLI = 'SOC';
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              lnMontoBaseDcto := 0;
          END;
          --
          /*  Procedimiento para agregar la venta Retail a la base para descuentos */
          --
          lnMontoVentaRetail := 0;

          IF DTO_FN_DEVUE_REGIO_PILOT_RETAI(interfazRecordN(x).oidZona) = 1 THEN
             BEGIN
                 SELECT SUM(nvl(rc.val_mont_bapl_dcto,0))
                 INTO lnMontoVentaRetail
                 FROM ret_venta_cabec rc
                 WHERE rc.val_cuen_consu = gen_pkg_gener.gen_fn_devuelve_cod_clie(interfazRecordN(x).oidCliente)
                       AND rc.cam_reta = gen_pkg_gener.gen_fn_devuelve_id_cra_perio3(lnOidPeriodo)
                       AND rc.cod_pais = psCodigoPais;
                 EXCEPTION
                   WHEN OTHERS THEN
                      lnMontoVentaRetail:=0;
             END;
             ---
             UPDATE ret_venta_cabec SET fec_fact=ldfecproc
             WHERE val_cuen_consu = gen_pkg_gener.gen_fn_devuelve_cod_clie(interfazRecordN(x).oidCliente)
                   AND cam_reta = gen_pkg_gener.gen_fn_devuelve_id_cra_perio3(lnOidPeriodo)
                   AND cod_pais = psCodigoPais
                   AND fec_fact is null;
          END IF;
          --
          /*  Fin de procedimiento para agregar la venta Retail a la base para descuentos */
          --

         lnMontoBaseDcto := nvl(lnMontoBaseDcto,0) + nvl(lnMontoVentaRetail,0);

        ELSIF(interfazRecordN(x).indicadorComision = 4) THEN
          --Solo debe aplicar para solicitudes que no son tipo SOC y Sean del Modulo de Reclamos (15)
          IF((interfazRecordN(x).codigoTipoSolicitud)  <> 'SOC' AND (interfazRecordN(x).oidModulo = 15)) THEN
            BEGIN
              SELECT PSP1.SOCA_OID_SOLI_CABE
                INTO lnOidSoliCabecRef
                FROM PED_SOLIC_POSIC PSP,
                     REC_LINEA_OPERA_RECLA RLO,
                     REC_LINEA_OPERA_RECLA RLO1,
                     PED_SOLIC_POSIC PSP1
               WHERE PSP.OID_LINE_OPER_RECL = RLO.OID_LINE_OPER_RECL
                 AND RLO.OPRE_OID_OPER_RECL = RLO1.OPRE_OID_OPER_RECL
                 AND RLO1.SOPO_OID_SOLI_POSI = PSP1.OID_SOLI_POSI
                 AND PSP.SOCA_OID_SOLI_CABE = interfazRecordN(x).oidSolicitud
                 AND ROWNUM = 1;

            EXCEPTION
              WHEN NO_DATA_FOUND THEN
                lnOidSoliCabecRef:= NULL;
            END;

            --De no hallar solicitud de referencia, proceder como IND_COMI=1
            IF(lnOidSoliCabecRef IS NULL) THEN

                --Actualizamos el monto Base de Descuento de Aplicacion para la solicitud
                SELECT NVL(SUM(B.VAL_PREC_CATA_UNIT_LOCA * B.NUM_UNID_DEMA_REAL),0)
                  INTO lnMontoBaseDcto
                  FROM PED_SOLIC_POSIC B, PRE_OFERT_DETAL C, PRE_TIPO_OFERT D
                 WHERE B.SOCA_OID_SOLI_CABE = interfazRecordN(x).oidSolicitud
                   AND B.OFDE_OID_DETA_OFER = C.OID_DETA_OFER
                   AND C.TOFE_OID_TIPO_OFER = D.OID_TIPO_OFER
                   AND D.IND_APOR_MONT_ESCA = 1;

                UPDATE PED_SOLIC_CABEC
                   SET VAL_MONT_BAPL_DCTO = lnMontoBaseDcto
                 WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;

            ELSE
              --OBTENEMOS DATOS DE LA SOLICITUD DE REFERENCIA
              SELECT TSO.COD_TIPO_SOLI,
                     NVL(PSC.VAL_MONT_BAPL_DCTO, 0),
                     PSC.PERD_OID_PERI,
                     PSC.FEC_FACT,
                     TSP.IND_COMI,
                     PSC.ZZON_OID_ZONA
                INTO lsCodigoTipoSolRef,
                     lnMontoBaseDctoRef,
                     lnOidPeriodoRef,
                     ldFechaFactRef,
                     lsIndicadorComiRef,
                     lnOidZonaRef
                FROM PED_SOLIC_CABEC PSC,
                     PED_TIPO_SOLIC_PAIS TSP,
                     PED_TIPO_SOLIC TSO
               WHERE PSC.OID_SOLI_CABE = lnOidSoliCabecRef
                 AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                 AND TSP.TSOL_OID_TIPO_SOLI = TSO.OID_TIPO_SOLI;

              --PARA EL CASO DE LAS SOLICITUDES SOC
              IF(lsCodigoTipoSolRef = 'SOC') THEN

                IF(lsIndicadorComiRef = 1) THEN
                  lnMontoBaseDcto := lnMontoBaseDctoRef;

                  UPDATE PED_SOLIC_CABEC
                     SET VAL_MONT_BAPL_DCTO = lnMontoBaseDcto
                   WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;

                ELSIF(lsIndicadorComiRef = 3) THEN

                  SELECT SUM(NVL(PSC.VAL_MONT_BAPL_DCTO,0))
                    INTO lnMontoBaseDcto
                    FROM PED_SOLIC_CABEC PSC,
                         PED_SOLIC_CABEC CAB,
                         PED_TIPO_SOLIC_PAIS TSP,
                         PED_TIPO_SOLIC TSO
                   WHERE PSC.PERD_OID_PERI = lnOidPeriodoRef
                     AND PSC.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                     AND PSC.FEC_FACT <= ldFechaFactRef
                     AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                     AND TSP.TSOL_OID_TIPO_SOLI = TSO.OID_TIPO_SOLI
                     AND PSC.SOCA_OID_SOLI_CABE = CAB.OID_SOLI_CABE(+)
                     AND NVL(CAB.ESSO_OID_ESTA_SOLI,0) <> 4
                     AND TSO.COD_TIPO_SOLI = 'SOC';

                  UPDATE PED_SOLIC_CABEC
                     SET VAL_MONT_BAPL_DCTO = lnMontoBaseDcto
                   WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;
                  --
                  /*  Procedimiento para agregar la venta Retail a la base para descuentos */
                  --
                  lnMontoVentaRetail := 0;

                  IF DTO_FN_DEVUE_REGIO_PILOT_RETAI(lnOidZonaRef) = 1 THEN
                     BEGIN
                         SELECT SUM(nvl(rc.val_mont_bapl_dcto,0))
                         INTO lnMontoVentaRetail
                         FROM ret_venta_cabec rc
                         WHERE rc.val_cuen_consu = gen_pkg_gener.gen_fn_devuelve_cod_clie(interfazRecordN(x).oidCliente)
                               AND rc.cam_reta = gen_pkg_gener.gen_fn_devuelve_id_cra_perio3(lnOidPeriodoRef)
                               AND rc.cod_pais = psCodigoPais
                               AND rc.fec_fact IS NOT NULL
                               AND rc.fec_fact <= ldFechaFactRef;

                     EXCEPTION
                     WHEN OTHERS THEN
                          lnMontoVentaRetail:=0;
                     END;
                  END IF;
          --
          /*  Fin de procedimiento para agregar la venta Retail a la base para descuentos */
          --

                   lnMontoBaseDcto := NVL(lnMontoBaseDcto,0) + nvl(lnMontoVentaRetail,0);

                ELSE
                  --Continuar con el siguiente elemento de la lista de Solicitudes
                  lbAplicarDcto := FALSE;
                END IF;

              ELSE
                --PARA EL CASO DE LAS SOLICITUDES DE ATENCION
                lnMontoBaseDcto := lnMontoBaseDctoRef;

                UPDATE PED_SOLIC_CABEC
                   SET VAL_MONT_BAPL_DCTO = lnMontoBaseDcto
                 WHERE OID_SOLI_CABE = interfazRecordN(x).oidSolicitud;

              END IF;

            END IF;

          ELSE
            --Continuar con el siguiente elemento de la lista de Solicitudes
            lbAplicarDcto := FALSE;
          END IF;

        ELSE
          lnMontoBaseDcto := interfazRecordN(x).montoPedido;
        END IF;

        IF(lbAplicarDcto) THEN
          --Calculamos el descuento adicional de los grupos activos para el cliente seleccionado
          FOR k IN (SELECT grp.COD_GRUP_DESC
            FROM DTO_DESCU_GRUPO grp,
                 (SELECT DISTINCT COD_GRUP_DESC FROM DTO_DESCU_ADICI_GRUPO WHERE EST_REGI = 1) adg
           WHERE grp.COD_GRUP_DESC = adg.COD_GRUP_DESC
                      AND grp.EST_REGI = 1) LOOP


             DTO_PR_CALCU_DSCTO_ADICI(k.COD_GRUP_DESC, interfazRecordN(x).OIDCLIENTE, interfazRecordN(x).oidSolicitud,
                                      lnMontoBaseDcto, psCodigoPeriodo);
          END LOOP;

           lnPorcDescuentoAdic := 0;

          --Obtenemos el Tipo/SubTipo del Cliente
           BEGIN
          SELECT TICL_OID_TIPO_CLIE, SBTI_OID_SUBT_CLIE
            INTO lnOidTipoCliente, lnOidSubTipoCliente
            FROM MAE_CLIEN_TIPO_SUBTI
           WHERE CLIE_OID_CLIE = interfazRecordN(x).oidCliente
--             AND IND_PPAL = 1;
                     AND TICL_OID_TIPO_CLIE = 2
                     AND ROWNUM = 1;

           EXCEPTION
              WHEN NO_DATA_FOUND THEN
                lnOidTipoCliente := NULL;
                lnOidSubTipoCliente:= NULL;
            END;


            FOR z IN (SELECT B.OID_SOLI_POSI,
                             B.VAL_PREC_CATA_UNIT_LOCA,
                             D.OID_TIPO_OFER,
                             P.NEGO_OID_NEGO,
                             P.UNEG_OID_UNID_NEGO,
                             B.VAL_PORC_DESC,
                             B.BACA_OID_CABE,
                           B.ESLO_OID_ESLN,
                           P.OID_PROD
                        FROM PED_SOLIC_POSIC B, PRE_OFERT_DETAL C, PRE_TIPO_OFERT D, MAE_PRODU P
                       WHERE B.SOCA_OID_SOLI_CABE = interfazRecordN(x).oidSolicitud
                         AND B.OFDE_OID_DETA_OFER = C.OID_DETA_OFER
                         AND C.TOFE_OID_TIPO_OFER = D.OID_TIPO_OFER
                         AND D.IND_COMI = 1
                         AND B.PROD_OID_PROD = P.OID_PROD) LOOP

              lnPorcDescuento := NULL;

              IF((z.VAL_PORC_DESC IS NOT NULL) AND z.VAL_PORC_DESC > 0) THEN
                lnPorcDescuento := z.VAL_PORC_DESC;
                lnCodGrupoDescuento := z.BACA_OID_CABE;
                lnCodRangoDescuento := z.ESLO_OID_ESLN;

              ELSE
              --Buscamos si tiene definido por Producto para la Posicion
              DTO_PR_OBTEN_PORCE_DESCU(0, z.OID_PROD, NULL, lnMontoBaseDcto,
                     psCodigoPeriodo, lnCodGrupoDescuento, lnCodRangoDescuento, lnPorcDescuento);

              IF(lnCodGrupoDescuento IS NULL) THEN
                --Buscamos si tiene definido por Tipo/SubTipo para la Posicion
                DTO_PR_OBTEN_PORCE_DESCU(1, lnOidTipoCliente, lnOidSubTipoCliente, lnMontoBaseDcto,
                       psCodigoPeriodo, lnCodGrupoDescuento, lnCodRangoDescuento, lnPorcDescuento);
              END IF;

              IF(lnCodGrupoDescuento IS NULL) THEN
                --Buscamos si tiene definido por Tipo Oferta para la Posicion
                DTO_PR_OBTEN_PORCE_DESCU(2, z.OID_TIPO_OFER, NULL, lnMontoBaseDcto,
                         psCodigoPeriodo, lnCodGrupoDescuento, lnCodRangoDescuento, lnPorcDescuento);
              END IF;

                IF(lnCodGrupoDescuento IS NULL) THEN
                  --Buscamos si tiene definido por Negocio / Unidad Negocio
                  DTO_PR_OBTEN_PORCE_DESCU(3, z.NEGO_OID_NEGO, z.UNEG_OID_UNID_NEGO, lnMontoBaseDcto,
                           psCodigoPeriodo, lnCodGrupoDescuento, lnCodRangoDescuento, lnPorcDescuento);
                END IF;

                IF(lnCodGrupoDescuento IS NULL) THEN
                  --Buscamos si tiene definido por Por Defecto
                  DTO_PR_OBTEN_PORCE_DESCU(4, NULL, NULL, lnMontoBaseDcto,
                           psCodigoPeriodo, lnCodGrupoDescuento, lnCodRangoDescuento, lnPorcDescuento);
                END IF;
              END IF;

              IF(lnPorcDescuento IS NOT NULL) THEN
                v_oidPosicion.EXTEND(1);
                v_porcDescuento.EXTEND(1);
                v_importeDescuento1.EXTEND(1);
                v_importeDescuento2.EXTEND(1);
                v_grupoDescuento.EXTEND(1);
                v_rangoDescuento.EXTEND(1);

                IF((z.VAL_PORC_DESC IS NOT NULL) AND z.VAL_PORC_DESC > 0) THEN
                  lnPorcDescuentoAux := z.VAL_PORC_DESC;
                ELSE
                --Calculamos el Porcentaje Adicional para Categoria: 0, 1, 2, 3 o 4
                lnPorcDescuentoAux := DTO_FN_RECUP_DSCTO_ADICI(lnCodGrupoDescuento, interfazRecordN(x).oidCliente,
                                                                interfazRecordN(x).oidSolicitud, lnPorcDescuento);

                END IF;

                v_oidPosicion(v_oidPosicion.COUNT) := z.OID_SOLI_POSI;
                v_porcDescuento(v_porcDescuento.COUNT) := lnPorcDescuentoAux;
                v_importeDescuento1(v_importeDescuento1.COUNT) := ROUND(z.VAL_PREC_CATA_UNIT_LOCA * (lnPorcDescuentoAux/100),2);
                v_importeDescuento2(v_importeDescuento2.COUNT) := ROUND(z.VAL_PREC_CATA_UNIT_LOCA * (lnPorcDescuentoAux/100),2);
                v_grupoDescuento(v_grupoDescuento.COUNT) := lnCodGrupoDescuento;
                v_rangoDescuento(v_rangoDescuento.COUNT) := lnCodRangoDescuento;
              END IF;

            END LOOP;

          END IF;
          ----- Pasa a status 4 sólo si se ha desactivado el SICC:
          IF lnsiccgp3 = 0 AND interfazRecordN(x).CodigoTipoSolicitud ='SOC' AND interfazRecordN(x).oidgrupproc=3 THEN 
             UPDATE ped_solic_cabec SET grpr_oid_grup_proc=4 WHERE oid_soli_cabe=interfazRecordN(x).oidSolicitud;
          END IF;
                  
      END LOOP;
    END IF;

    DELETE FROM DTO_TMP_DESCU_ADICI;

    EXIT WHEN c_Consultoras%NOTFOUND;
  END LOOP;
  CLOSE c_Consultoras;

  --Actualizamos en la tabla PED_SOLIC_POSIC
  FORALL i IN 1..v_oidPosicion.COUNT
    UPDATE PED_SOLIC_POSIC
       SET VAL_PORC_DESC = v_porcDescuento(i),
           VAL_IMPO_DESC_UNIT_LOCA = v_importeDescuento1(i),
           VAL_IMPO_DESC_UNIT_DOCU = v_importeDescuento2(i),
           BACA_OID_CABE = v_grupoDescuento(i),
           ESLO_OID_ESLN = v_rangoDescuento(i)
     WHERE OID_SOLI_POSI = v_oidPosicion(i);


EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR DTO_PR_PROCE_CALCU_DESCU: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END DTO_PR_PROCE_CALCU_DESCU;


/**************************************************************************
Descripcion       : Obtiene el grupo de Descuento, Rango de Descuento y
                    Porcentaje de acuerdo a los datos del cliente o de la
                    posicion de la solicitud
Fecha Creacion    : 27/07/2012
Parametros Entrada:
  pnCategoriaDescuento   :  Categoria de Descuento
  pnSubCategoria1        :  Sub Categoria1
  pnSubCategoria2        :  Sub Categoria 2
  pnMontoPedido          :  Monto de Pedido a Validar
  psCodigoPeriodo        :  Codigo de Periodo
  pnCodGrupoDescuento    :  Codigo de Grupo de Descuento
  pnCodRangoDescuento    :  Codigo de Rango de Descuento
  pnPorcDescuento        :  Porcentaje de Descuento

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE DTO_PR_OBTEN_PORCE_DESCU
  (pnCategoriaDescuento       NUMBER,
   pnSubCategoria1            NUMBER,
   pnSubCategoria2            NUMBER,
   pnMontoPedido              NUMBER,
   psCodigoPeriodo            VARCHAR2,
   pnCodGrupoDescuento        OUT NUMBER,
   pnCodRangoDescuento        OUT NUMBER,
   pnPorcDescuento            OUT NUMBER)
IS
  lnCodGrupoDescuento         NUMBER;
  lnCodigoRangoDcto           DTO_DESCU_GRUPO_RANGO.COD_RANG_DESC%TYPE;
  lnPorcDescuento             DTO_DESCU_GRUPO_RANGO.POR_DESC%TYPE;

BEGIN

  lnCodGrupoDescuento := NULL;

  FOR y IN (SELECT DDM.SUB_CAT1,
                   DDM.SUB_CAT2,
                   DDG.COD_GRUP_DESC
              FROM DTO_DESCU_GRUPO DDG, DTO_DESCU_MATRI DDM
             WHERE DDG.EST_REGI = 1
               AND CAM_INIC <= psCodigoPeriodo
               AND (CAM_FINA >= psCodigoPeriodo OR CAM_FINA IS NULL)
               AND DDM.COD_GRUP_DESC = DDG.COD_GRUP_DESC
               AND DDM.EST_REGI = 1
               AND DDM.COD_CATE = pnCategoriaDescuento
             ORDER BY DDM.COD_CATE, DDM.SUB_CAT1, DDM.SUB_CAT2 DESC) LOOP

    --Por Producto
    IF(pnCategoriaDescuento = 0 AND (lnCodGrupoDescuento IS NULL)) THEN
      IF(pnSubCategoria1 = y.SUB_CAT1) THEN
        lnCodGrupoDescuento := y.COD_GRUP_DESC;
      END IF;
    END IF;

    --Por SubTipoCliente
    IF(pnCategoriaDescuento = 1 AND (lnCodGrupoDescuento IS NULL)) THEN
      IF(y.SUB_CAT2 > 0) THEN --Tiene SubTipoCliente definido
        IF(pnSubCategoria2 = y.SUB_CAT2) THEN
          lnCodGrupoDescuento := y.COD_GRUP_DESC;
        END IF;

      ELSE
        IF(pnSubCategoria1 = y.SUB_CAT1) THEN
          lnCodGrupoDescuento := y.COD_GRUP_DESC;
        END IF;
      END IF;
    END IF;

    --Por Tipo de Oferta
    IF(pnCategoriaDescuento = 2 AND (lnCodGrupoDescuento IS NULL)) THEN
      IF(pnSubCategoria1 = y.SUB_CAT1) THEN
        lnCodGrupoDescuento := y.COD_GRUP_DESC;
      END IF;
    END IF;

    --Por Negocio / Unidad de Negocio
    IF(pnCategoriaDescuento = 3 AND (lnCodGrupoDescuento IS NULL)) THEN
      IF(y.SUB_CAT1 > 0 AND y.SUB_CAT2 > 0) THEN --Tiene Ambos
        IF(pnSubCategoria1 = y.SUB_CAT1 AND pnSubCategoria2 = y.SUB_CAT2) THEN
          lnCodGrupoDescuento := y.COD_GRUP_DESC;
        END IF;

      ELSE
        IF(pnSubCategoria1 = y.SUB_CAT1) THEN
          lnCodGrupoDescuento := y.COD_GRUP_DESC;
        END IF;

        IF(pnSubCategoria2 = y.SUB_CAT2) THEN
          lnCodGrupoDescuento := y.COD_GRUP_DESC;
        END IF;

      END IF;
    END IF;

    --Por Negocio / Unidad de Negocio
    IF(pnCategoriaDescuento = 4 AND (lnCodGrupoDescuento IS NULL)) THEN
      lnCodGrupoDescuento := y.COD_GRUP_DESC;
    END IF;

    EXIT WHEN (lnCodGrupoDescuento IS NOT NULL);

  END LOOP;

  IF(lnCodGrupoDescuento IS NOT NULL) THEN
    IF(pnMontoPedido IS NOT NULL) THEN
      --Ubicamos el Rango que se aplica al monto Base de la Solicitud
      SELECT COD_RANG_DESC, POR_DESC
        INTO lnCodigoRangoDcto, lnPorcDescuento
        FROM (SELECT COD_RANG_DESC, POR_DESC
                FROM DTO_DESCU_GRUPO_RANGO
               WHERE COD_GRUP_DESC = lnCodGrupoDescuento
                 AND EST_REGI = 1
                 AND VAL_IMPO_HASTA >= pnMontoPedido
               ORDER BY VAL_IMPO_HASTA)
        WHERE ROWNUM = 1;
    ELSE
      --Ubicamos el Rango que se aplica al monto Base de la Solicitud
      SELECT COD_RANG_DESC, POR_DESC
        INTO lnCodigoRangoDcto, lnPorcDescuento
        FROM (SELECT COD_RANG_DESC, POR_DESC
                FROM DTO_DESCU_GRUPO_RANGO
               WHERE COD_GRUP_DESC = lnCodGrupoDescuento
                 AND EST_REGI = 1
               ORDER BY VAL_IMPO_HASTA)
        WHERE ROWNUM = 1;
    END IF;

    pnCodGrupoDescuento := lnCodGrupoDescuento;
    pnCodRangoDescuento :=  lnCodigoRangoDcto;
    pnPorcDescuento := lnPorcDescuento;
  ELSE
    pnCodGrupoDescuento := NULL;
    pnCodRangoDescuento := NULL;
    pnPorcDescuento := NULL;
  END IF;


EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR DTO_PR_OBTEN_PORCE_DESCU: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END DTO_PR_OBTEN_PORCE_DESCU;

/**************************************************************************
Descripcion       : Obtiene el porcentaje Adicional, siempre y cuando haya
                    cumplido el cliente alguna validacion del descuento Adicional
Fecha Creacion    : 29/11/2012
Parametros Entrada:
  pnCodGrupoDescuento    :  Codigo de Grupo de Descuento
  pnOidCliente           :  Oid Cliente de Rango de Descuento
  pnMontoBaseCalculo     :  Monto Base Calculo Descuento
  psCodigoPeriodo        :  Codigo de Periodo

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION DTO_FN_OBTIE_DSCTO_ADICI
  (pnCodGrupoDescuento    NUMBER,
   pnOidCliente           NUMBER,
   pnMontoBaseCalculo     NUMBER,
   psCodigoPeriodo        VARCHAR2) RETURN NUMBER
IS
  CURSOR cursorCabeceraAdicional IS
    SELECT CAB.COD_DESC_ADIC,
           CAB.POR_DESC_ADIC
      FROM DTO_DESCU_ADICI_GRUPO GRU,
           DTO_DESCU_ADICI_CABEC CAB
     WHERE GRU.COD_GRUP_DESC = pnCodGrupoDescuento
       AND GRU.COD_DESC_ADIC = CAB.COD_DESC_ADIC
       AND psCodigoPeriodo >= CAB.CAM_INIC_DESC
       AND (CAB.CAM_FINA_DESC IS NULL OR CAB.CAM_FINA_DESC >= psCodigoPeriodo)
       AND GRU.EST_REGI = 1
       AND CAB.EST_REGI = 1;

  CURSOR cursorDetalleAdicional(lnCodigoAdicional  NUMBER) IS
    SELECT D.*
      FROM DTO_DESCU_ADICI_DETAL D
     WHERE D.COD_DESC_ADIC = lnCodigoAdicional
       AND D.EST_REGI = 1;

  lbValidaDetalle    BOOLEAN;
  lnOcurrencias      NUMBER;
  lnPorcentaje       NUMBER;
BEGIN
  lnPorcentaje := 0;

  --Recorremos las cabeceras de Descuento Adicional
  FOR cCabeceraAdicional IN cursorCabeceraAdicional LOOP
    lbValidaDetalle := FALSE;

    --Recorremos los detalles de Descuento Adicional
    FOR cDetalleAdicional IN cursorDetalleAdicional(cCabeceraAdicional.COD_DESC_ADIC) LOOP

      --Recorremos las clasificaciones del descuento adicional
      IF(NOT lbValidaDetalle) THEN
        --Validamos Clasificaciones y Tipos del Cliente
        SELECT COUNT(1)
          INTO lnOcurrencias
          FROM MAE_CLIEN_CLASI X,
               MAE_CLIEN_TIPO_SUBTI Y,
               MAE_TIPO_CLIEN TCL,
               MAE_SUBTI_CLIEN SUB,
               MAE_TIPO_CLASI_CLIEN TCC,
               MAE_CLASI CLA
         WHERE X.CTSU_OID_CLIE_TIPO_SUBT = Y.OID_CLIE_TIPO_SUBT
           AND Y.CLIE_OID_CLIE = pnOidCliente
           AND Y.IND_PPAL = '1'
           AND Y.TICL_OID_TIPO_CLIE = TCL.OID_TIPO_CLIE
           AND Y.SBTI_OID_SUBT_CLIE = SUB.OID_SUBT_CLIE
           AND X.TCCL_OID_TIPO_CLASI = TCC.OID_TIPO_CLAS
           AND X.CLAS_OID_CLAS = CLA.OID_CLAS
           AND (cDetalleAdicional.COD_CLAS IS NULL OR CLA.COD_CLAS = cDetalleAdicional.COD_CLAS)
           AND (cDetalleAdicional.COD_TIPO_CLAS IS NULL OR TCC.COD_TIPO_CLAS = cDetalleAdicional.COD_TIPO_CLAS)
           AND TCL.COD_TIPO_CLIE = cDetalleAdicional.COD_TIPO_CLIE
           AND SUB.COD_SUBT_CLIE = cDetalleAdicional.COD_SUBT_CLIE;

        IF(lnOcurrencias > 0) THEN
          --Validamos Region/Zona
          IF((cDetalleAdicional.COD_REGI IS NOT NULL) OR (cDetalleAdicional.COD_ZONA IS NOT NULL)) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM MAE_CLIEN_UNIDA_ADMIN ua,
  	               ZON_TERRI_ADMIN zta,
              	 	 ZON_ZONA  zz,
                	 ZON_SECCI zs,
                   ZON_REGIO zr
             WHERE ua.CLIE_OID_CLIE = pnOidCliente
          	   AND ua.IND_ACTI = 1
          	   AND ua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
          	   AND zs.OID_SECC = zta.ZSCC_OID_SECC
          	   AND zz.OID_ZONA = zs.ZZON_OID_ZONA
               AND zr.OID_REGI = zz.ZORG_OID_REGI
               AND (cDetalleAdicional.COD_REGI IS NULL OR zr.COD_REGI = cDetalleAdicional.COD_REGI)
               AND (cDetalleAdicional.COD_ZONA IS NULL OR zz.COD_ZONA = cDetalleAdicional.COD_ZONA);
          END IF;

          IF(lnOcurrencias > 0) THEN
            --Validamos Monto Limite
            IF((cDetalleAdicional.VAL_MONT_LIMI IS NOT NULL) AND (cDetalleAdicional.VAL_MONT_LIMI>0)) THEN
              IF(pnMontoBaseCalculo >= cDetalleAdicional.VAL_MONT_LIMI) THEN
                lbValidaDetalle := TRUE;
              END IF;
            ELSE
              lbValidaDetalle := TRUE;
            END IF;
          END IF;

        END IF;
      END IF;

    END LOOP;

    IF(lbValidaDetalle) THEN
      IF(cCabeceraAdicional.POR_DESC_ADIC > lnPorcentaje) THEN
        lnPorcentaje := cCabeceraAdicional.POR_DESC_ADIC;
      END IF;
    END IF;

  END LOOP;

  RETURN lnPorcentaje;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR DTO_FN_OBTIE_DSCTO_ADICI: ' || ls_sqlerrm);
END DTO_FN_OBTIE_DSCTO_ADICI;


/**************************************************************************
Descripcion       : Recupera el porcentaje Adicional, siempre y cuando haya
                    cumplido el cliente alguna validacion del descuento Adicional
Fecha Creacion    : 29/11/2012
Parametros Entrada:
  pnCodGrupoDescuento    :  Codigo de Grupo de Descuento
  pnOidCliente           :  Oid Cliente de Rango de Descuento
  pnOidSolicitud         : Oid Solicitud

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION DTO_FN_RECUP_DSCTO_ADICI
  (pnCodGrupoDescuento    NUMBER,
   pnOidCliente           NUMBER,
   pnOidSolicitud         NUMBER,
   pnPorcentaje           NUMBER) RETURN NUMBER
IS

  lnPorcentaje       NUMBER;
BEGIN

  lnPorcentaje := pnPorcentaje;

  FOR x IN (SELECT POR_DESC_ADIC,
                   IND_TIPO_DESC_ADIC
    FROM DTO_TMP_DESCU_ADICI
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND SOCA_OID_SOLI_CABE = pnOidSolicitud
               AND COD_GRUP_DESC = pnCodGrupoDescuento
             ORDER BY COD_DESC_ADIC) LOOP

    IF(x.IND_TIPO_DESC_ADIC = 0) THEN
      lnPorcentaje := lnPorcentaje + x.POR_DESC_ADIC;
    ELSE
      lnPorcentaje := x.POR_DESC_ADIC;
    END IF;

    IF(lnPorcentaje < 0) THEN
      lnPorcentaje := 0;
    END IF;
  END LOOP;

  RETURN lnPorcentaje;

EXCEPTION
  WHEN OTHERS THEN
    RETURN 0;
END DTO_FN_RECUP_DSCTO_ADICI;

/**************************************************************************
Descripcion       : Obtiene el porcentaje Adicional, siempre y cuando haya
                    cumplido el cliente alguna validacion del descuento Adicional
Fecha Creacion    : 26/11/2014
Parametros Entrada:
  pnCodGrupoDescuento    :  Codigo de Grupo de Descuento
  pnOidCliente           :  Oid Cliente
  pnOidSolicitud           :  Oid Solicitud
  pnMontoBaseCalculo     :  Monto Base Calculo Descuento
  psCodigoPeriodo        :  Codigo de Periodo

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE DTO_PR_CALCU_DSCTO_ADICI
  (pnCodGrupoDescuento    NUMBER,
   pnOidCliente           NUMBER,
   pnOidSolicitud         NUMBER,
   pnMontoBaseCalculo     NUMBER,
   psCodigoPeriodo        VARCHAR2)
IS
  CURSOR cursorCabeceraAdicional IS
    SELECT CAB.COD_DESC_ADIC,
           CAB.POR_DESC_ADIC,
           NVL(CAB.IND_TIPO_DESC_ADIC,0) IND_TIPO_DESC_ADIC
      FROM DTO_DESCU_ADICI_GRUPO GRU,
           DTO_DESCU_ADICI_CABEC CAB
     WHERE GRU.COD_GRUP_DESC = pnCodGrupoDescuento
       AND GRU.COD_DESC_ADIC = CAB.COD_DESC_ADIC
       AND psCodigoPeriodo >= CAB.CAM_INIC_DESC
       AND (CAB.CAM_FINA_DESC IS NULL OR CAB.CAM_FINA_DESC >= psCodigoPeriodo)
       AND GRU.EST_REGI = 1
       AND CAB.EST_REGI = 1;

  CURSOR cursorDetalleAdicional(lnCodigoAdicional  NUMBER) IS
    SELECT D.*
      FROM DTO_DESCU_ADICI_DETAL D
     WHERE D.COD_DESC_ADIC = lnCodigoAdicional
       AND D.EST_REGI = 1;

  lbValidaDetalle    BOOLEAN;
  lnOcurrencias      NUMBER;
  lnPorcentaje       NUMBER;
BEGIN
  lnPorcentaje := 0;

  --Recorremos las cabeceras de Descuento Adicional
  FOR cCabeceraAdicional IN cursorCabeceraAdicional LOOP
    lbValidaDetalle := FALSE;

    --Recorremos los detalles de Descuento Adicional
    FOR cDetalleAdicional IN cursorDetalleAdicional(cCabeceraAdicional.COD_DESC_ADIC) LOOP

      --Recorremos las clasificaciones del descuento adicional
      IF(NOT lbValidaDetalle) THEN
        --Validamos Clasificaciones y Tipos del Cliente
        SELECT COUNT(1)
          INTO lnOcurrencias
          FROM MAE_CLIEN_CLASI X,
               MAE_CLIEN_TIPO_SUBTI Y,
               MAE_TIPO_CLIEN TCL,
               MAE_SUBTI_CLIEN SUB,
               MAE_TIPO_CLASI_CLIEN TCC,
               MAE_CLASI CLA
         WHERE X.CTSU_OID_CLIE_TIPO_SUBT = Y.OID_CLIE_TIPO_SUBT
           AND Y.CLIE_OID_CLIE = pnOidCliente
           AND Y.IND_PPAL = '1'
           AND Y.TICL_OID_TIPO_CLIE = TCL.OID_TIPO_CLIE
           AND Y.SBTI_OID_SUBT_CLIE = SUB.OID_SUBT_CLIE
           AND X.TCCL_OID_TIPO_CLASI = TCC.OID_TIPO_CLAS
           AND X.CLAS_OID_CLAS = CLA.OID_CLAS
           AND (cDetalleAdicional.COD_CLAS IS NULL OR CLA.COD_CLAS = cDetalleAdicional.COD_CLAS)
           AND (cDetalleAdicional.COD_TIPO_CLAS IS NULL OR TCC.COD_TIPO_CLAS = cDetalleAdicional.COD_TIPO_CLAS)
           AND TCL.COD_TIPO_CLIE = cDetalleAdicional.COD_TIPO_CLIE
           AND SUB.COD_SUBT_CLIE = cDetalleAdicional.COD_SUBT_CLIE;

        IF(lnOcurrencias > 0) THEN
          --Validamos Region/Zona
          IF((cDetalleAdicional.COD_REGI IS NOT NULL) OR (cDetalleAdicional.COD_ZONA IS NOT NULL)) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM MAE_CLIEN_UNIDA_ADMIN ua,
  	               ZON_TERRI_ADMIN zta,
              	 	 ZON_ZONA  zz,
                	 ZON_SECCI zs,
                   ZON_REGIO zr
             WHERE ua.CLIE_OID_CLIE = pnOidCliente
          	   AND ua.IND_ACTI = 1
          	   AND ua.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
          	   AND zs.OID_SECC = zta.ZSCC_OID_SECC
          	   AND zz.OID_ZONA = zs.ZZON_OID_ZONA
               AND zr.OID_REGI = zz.ZORG_OID_REGI
               AND (cDetalleAdicional.COD_REGI IS NULL OR zr.COD_REGI = cDetalleAdicional.COD_REGI)
               AND (cDetalleAdicional.COD_ZONA IS NULL OR zz.COD_ZONA = cDetalleAdicional.COD_ZONA);
          END IF;

          IF(lnOcurrencias > 0) THEN
            --Validamos Monto Limite
            IF((cDetalleAdicional.VAL_MONT_LIMI IS NOT NULL) AND (cDetalleAdicional.VAL_MONT_LIMI>0)) THEN
              IF(pnMontoBaseCalculo >= cDetalleAdicional.VAL_MONT_LIMI) THEN
                lbValidaDetalle := TRUE;
              END IF;
            ELSE
              lbValidaDetalle := TRUE;
            END IF;
          END IF;

        END IF;
      END IF;

    END LOOP;

    IF(lbValidaDetalle) THEN
      INSERT INTO DTO_TMP_DESCU_ADICI
        (CLIE_OID_CLIE,
          SOCA_OID_SOLI_CABE,
          COD_GRUP_DESC,
          POR_DESC_ADIC,
          COD_DESC_ADIC,
          IND_TIPO_DESC_ADIC)
       VALUES(pnOidCliente,
              pnOidSolicitud,
              pnCodGrupoDescuento,
              cCabeceraAdicional.POR_DESC_ADIC,
              cCabeceraAdicional.COD_DESC_ADIC,
              cCabeceraAdicional.IND_TIPO_DESC_ADIC);

    END IF;

  END LOOP;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR DTO_PR_CALCU_DSCTO_ADICI: ' || ls_sqlerrm);
END DTO_PR_CALCU_DSCTO_ADICI;

/**************************************************************************
Descripcion       : devolverá el monto de la venta retail acumulada de la
                    campanha
Fecha Creacion    : 08/07/2015
Parametros Entrada:
  oidCliente     :  Oid Cliente
  oidPeriodo     :  Oid Periodo
  fechaFact      :  Fecha Facturacion
  oidZona        :  Oid Zona
  indProceso     :  Ind Proceso
  codPais        :  Codigo Pais

Autor            :

/***************************************************************************/
FUNCTION DTO_FN_DEVUE_MONTO_VENTA_RETAI
  (
    oidCliente        NUMBER,
    oidPeriodo        NUMBER,
    fechaFact         DATE,
    oidZona           NUMBER,
    indProceso        VARCHAR2,
    codPais           VARCHAR2
  ) RETURN NUMBER IS
    ln_val_mont_bapl_dcto    ret_venta_cabec.val_mont_bapl_dcto%TYPE;
    ln_ind_pilo_reta      zon_regio.ind_pilo_reta%TYPE;
    ln_cod_clie           mae_clien.cod_clie%TYPE;
    ln_cod_periodo        VARCHAR2(6);
  BEGIN
    /* campo ind_pilo_reta = 1 */
       BEGIN
       select zr.ind_pilo_reta
       into ln_ind_pilo_reta
       from zon_regio zr, zon_zona zz
       where zr.oid_regi = zz.zorg_oid_regi
       and zz.oid_zona = oidZona;

        IF(ln_ind_pilo_reta IS NULL) THEN
               ln_ind_pilo_reta := '0';
        END IF;

        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            ln_val_mont_bapl_dcto := 0;
            RETURN ln_val_mont_bapl_dcto;
        END;

     IF(ln_ind_pilo_reta = '1') THEN
      ln_cod_clie := gen_pkg_gener.gen_fn_devuelve_cod_clie(oidCliente);
      ln_cod_periodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio3(oidPeriodo);

        IF(indProceso = '1') THEN
             select sum(val_mont_bapl_dcto)
             into ln_val_mont_bapl_dcto
             from ret_venta_cabec rc
             where rc.val_cuen_consu = ln_cod_clie
             and rc.cam_reta = ln_cod_periodo
             and rc.cod_pais = codPais;

             IF(ln_val_mont_bapl_dcto is null) THEN
               ln_val_mont_bapl_dcto := 0;
             END IF;
         END IF;

        IF(indProceso = '0') THEN
             select sum(VAL_MONT_BAPL_DCTO)
             into ln_val_mont_bapl_dcto
             from RET_VENTA_CABEC rc
             where rc.val_cuen_consu = ln_cod_clie
             and rc.cam_reta = ln_cod_periodo
             and rc.cod_pais = codPais
             and rc.fec_fact IS NOT NULL
             and rc.fec_fact <= TO_DATE(fechaFact,'DD/MM/YYYY');

             IF(ln_val_mont_bapl_dcto IS NULL) THEN
               ln_val_mont_bapl_dcto := 0;
             END IF;
        END IF;
     END IF;

     IF(ln_ind_pilo_reta <> '1')THEN
      ln_val_mont_bapl_dcto := 0;
     END IF;

     RETURN ln_val_mont_bapl_dcto;
    EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR DTO_FN_DEVUE_MONTO_VENTA_RETAI: '||ls_sqlerrm);
     RETURN 0;
  END DTO_FN_DEVUE_MONTO_VENTA_RETAI;

FUNCTION DTO_FN_DEVUE_REGIO_PILOT_RETAI
  (
    oidZona           NUMBER
  ) RETURN NUMBER IS
    ln_ind_pilo_reta      zon_regio.ind_pilo_reta%TYPE;
  BEGIN
       BEGIN
       select zr.ind_pilo_reta
       into ln_ind_pilo_reta
       from zon_regio zr, zon_zona zz
       where zr.oid_regi = zz.zorg_oid_regi
       and zz.oid_zona = oidZona;

       EXCEPTION
          WHEN NO_DATA_FOUND THEN
            RETURN 0;
       END;

     IF(ln_ind_pilo_reta = '1')THEN
        RETURN 1;
     ELSE
        RETURN 0;
     END IF;

    EXCEPTION
    WHEN OTHERS THEN
        RETURN 0;
  END DTO_FN_DEVUE_REGIO_PILOT_RETAI;


END DTO_PKG_PROCE;
/
