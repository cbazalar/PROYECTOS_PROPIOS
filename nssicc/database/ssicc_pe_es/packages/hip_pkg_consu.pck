CREATE OR REPLACE PACKAGE "HIP_PKG_CONSU" IS
  /* Declaracion de variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=3000;

  FUNCTION HIP_FN_DEVUE_SALDO_CAMPA(
    psCodigoPais           VARCHAR2,
    psCodigoMarca          VARCHAR2,
    psCodigoCanal          VARCHAR2,
    psCodigoCliente        VARCHAR2,
    psCodigoPeriodo        VARCHAR2,
    psCodigoZona           VARCHAR2)
  RETURN NUMBER;

  FUNCTION HIP_FN_DEVUE_SALDO_PAGAR(
    psCodigoPais           VARCHAR2,
    psCodigoMarca          VARCHAR2,
    psCodigoCanal          VARCHAR2,
    psCodigoRegion         VARCHAR2,
    psCodigoZona           VARCHAR2,
    psCodigoCliente        VARCHAR2)
  RETURN NUMBER;


  PROCEDURE HIP_PR_ACTIV(psPais  VARCHAR2,
                               psMarca VARCHAR2,
                               psCanal VARCHAR2,
                               psZona  VARCHAR2);

  PROCEDURE HIP_PR_PROGR(psCliente VARCHAR2, psNombre VARCHAR2);

  PROCEDURE HIP_PR_SUSCR( psCliente     VARCHAR2,
                          psNombre      VARCHAR2,
                          psCodPrograma VARCHAR2,
                          psOidClas     VARCHAR2,
                          psoidClieTipoSubT     VARCHAR2,
                          psoidTipoClas         VARCHAR2,
                          psCodPais          VARCHAR2,
                          psCodMarca        VARCHAR2,
                          psCodCanal         VARCHAR2
                          );

  PROCEDURE HIP_PR_FINSU(psCliente     VARCHAR2,
                                 psCodPrograma VARCHAR2,
                                 psOidClas     VARCHAR2,
                                 psoidClieTipoSubT     VARCHAR2,
                                 psoidTipoClas         VARCHAR2
                                 );

  FUNCTION HIP_FN_TERRI (psPais               VARCHAR2,
                         psZona               VARCHAR2,
                         psSecccion           VARCHAR2)
  RETURN VARCHAR2;

  FUNCTION HIP_FN_DEVUE_PROME_VENTA(
    psCodigoPais           VARCHAR2,
    psCodigoMarca          VARCHAR2,
    psCodigoCanal          VARCHAR2,
    psCodigoPeriodoInicio  VARCHAR2,
    psCodigoPeriodoFin     VARCHAR2,
    psCodigoCliente        VARCHAR2,
    pnNumeroCampanas       NUMBER)
  RETURN NUMBER;

  FUNCTION HIP_FN_DEVUELVE_PERIO_FECHA(
    psCodPais   VARCHAR2,
    psCodMarca  VARCHAR2,
    psCodCanal  VARCHAR2,
    pdFecha     DATE)
  RETURN VARCHAR2;

  /***********************************************************************************
  Descripcion        : Devuelve Promedio de Venta de las ultimas 5 campañas de la Consultora
  Fecha Creacion     : 30/01/2009
  Parametros Entrada :
             psCodigoPais : Codigo de Pais
             psCodigoMarca: Codigo de Marca
             psCodigoCanal: Codigo de Canal
             psCodigoRegion: Codigo de Region
             psCodigoCliente : Codigo de Cliente

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION HIP_FN_OBTIE_PROME_VENTA(
    psCodigoPais           VARCHAR2,
    psCodigoMarca          VARCHAR2,
    psCodigoCanal          VARCHAR2,
    psCodigoRegion         VARCHAR2,
    psCodigoCliente        VARCHAR2)
  RETURN NUMBER;

  /***********************************************************************************
  Descripcion        : Devuelve Mensaje de Origen del Pedido
  Fecha Creacion     : 12/08/2009
  Parametros Entrada :

             pnOidOrdenCompra: Oid Orden Compra
             psCodigoCliente:  Codigo Cliente

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION HIP_FN_OBTIE_ORIGE_PEDID_CONSO(
    pnOidOrdenCompra       NUMBER,
    psCodigoCliente        VARCHAR2)
  RETURN VARCHAR2;

  /***********************************************************************************
  Descripcion        : Devuelve Importe Real Atendido para Reclamos
  Fecha Creacion     : 23/11/2009
  Parametros Entrada :
             pnOidReclamo : Oid Reclamo

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION HIP_FN_DEVUE_IMPOR_ATEND(
    pnOidReclamo           NUMBER)
  RETURN NUMBER;

  /***********************************************************************************
  Descripcion        : Devuelve Unidades Real Atendido para Reclamos
  Fecha Creacion     : 23/11/2009
  Parametros Entrada :
             pnOidReclamo : Oid Reclamo

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION HIP_FN_DEVUE_UNIDA_ATEND(
    pnOidReclamo           NUMBER)
  RETURN NUMBER;

/***************************************************************************
Descripcion        : Devuelve Limite de credito en base al nivel de riego
Fecha Creacion     : 12/08/2010
Parametros Entrada : pnOidCliente
                     pnOidNivelRiesgo
Autor              : Dennys Oliva Iriarte
***************************************************************************/
FUNCTION HIP_FN_DEVUE_LIMIT_CREDI(pnOidCliente           NUMBER,
                                  pnOidNivelRiesgo       NUMBER) RETURN varchar2;

/***********************************************************************************
Descripcion        : Devuelve Estado de la Solicitud de Poliza
Fecha Creacion     : 25/05/2011
Parametros Entrada :
           psCodigoCliente : Codigo Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DEVUE_ESTAT_SOLIC_POLIZ(
  psCodigoCliente           VARCHAR2)
RETURN VARCHAR2;

/***********************************************************************************
Descripcion        : Devuelve Tipo de Consultora
Fecha Creacion     : 14/12/2012
Parametros Entrada :
           codigopais: Codigo de Pais 
           oidcliente: Oid de Cliente
Autor              : Aurelio Oviedo
***************************************************************************/
FUNCTION HIP_FN_DEVUELVE_TIPO_CONSUL(   
    codigopais VARCHAR2,
    oidcliente NUMBER
) RETURN VARCHAR2;

/***********************************************************************************
Descripcion        : Actualiza datos del Cliente
Fecha Creacion     : 05/08/2003
Parametros Entrada :
           pnOidCliente :     Oid Cliente
           psPrimerApellido:  Primer Apellido
           psSegundoApellido: Segundo Apellido
           psPrimerNombre:    Primer Nombre
           psSegundoNombre:   Segundo Nombre
           psTelefonoFijo:    Telefono Fijo
           psTelefonoCelular: Telefono Celular
           psEmail: Email
           psFechaNacimiento: Fecha de Nacimiento                      
           psCodigoUsuario:   Codigo de Usuario

Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE HIP_PR_ACTUA_DATOS_CLIEN(pnOidCliente              NUMBER,
                                   psPrimerApellido          VARCHAR2,
                                   psSegundoApellido         VARCHAR2,
                                   psPrimerNombre            VARCHAR2,
                                   psSegundoNombre           VARCHAR2,
                                   psTelefonoFijo            VARCHAR2,
                                   psTelefonoCelular         VARCHAR2,
                                   psEmail                   VARCHAR2,
                                   psFechaNacimiento         VARCHAR2,
                                   psCodigoUsuario           VARCHAR2);

/***********************************************************************************
  Descripcion        : Devuelve Promedio de Venta de las ultimas 5 campañas de la Consultora
  Fecha Creacion     : 29/08/2013
  Parametros Entrada :
             psCodigoPais : Codigo de Pais
             psCodigoMarca: Codigo de Marca
             psCodigoCanal: Codigo de Canal
             psCodigoRegion: Codigo de Region
             psCodigoCliente : Codigo de Cliente
             psNumeroPedido : Numero de Pedidos

  Autor              : Aurelio Oviedo
  ***************************************************************************/
  FUNCTION HIP_FN_OBTIE_PROME_VENTA_PEDID(
    psCodigoPais           VARCHAR2,
    psCodigoMarca          VARCHAR2,
    psCodigoCanal          VARCHAR2,
    psCodigoRegion         VARCHAR2,
    psCodigoCliente        VARCHAR2,
    psNumeroPedidos        NUMBER)
  RETURN NUMBER;

  /***********************************************************************************
Descripcion        : Actualiza datos del Cliente
Fecha Creacion     : 25/10/2013
Parametros Entrada :
           psCodigoCliente:   Codigo de consultora
           psCodigoPais: 		 Codigo pais
           psCodigoRegion:   Codigo region

Autor              : Sebastian Guerra
***************************************************************************/
PROCEDURE HIP_PR_CONSU_CTACT_CAMPA(psCodigoCliente      VARCHAR2,
                                                                           psCodigoPais           VARCHAR2,
                                                                           psCodigoRegion       VARCHAR2);
            
/***********************************************************************************
  Descripcion        : Valida si se Deshabilita Zona y Territorio para la pantalla de
                       Actualizar Datos de Direccion de HiperConsulta
  Fecha Creacion     : 12/03/2014
  Parametros Entrada :
             psCodigoPais : Codigo de Pais
             psCodigoMarca: Codigo de Marca
             psCodigoCanal: Codigo de Canal
             psCodigoCliente : Codigo de Cliente
             pnNumeroPeriodos : Numero de Campañas

  Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DESHA_ZONA_TERRI(
  psCodigoPais           VARCHAR2,
  psCodigoMarca          VARCHAR2,
  psCodigoCanal          VARCHAR2,
  psCodigoCliente        VARCHAR2,
  pnNumeroPeriodos       NUMBER)
RETURN VARCHAR2;
  
END HIP_PKG_CONSU;
/
CREATE OR REPLACE PACKAGE BODY "HIP_PKG_CONSU" IS

/***********************************************************************************
Descripcion        : Devuelve Saldo de la Campaña
Fecha Creacion     : 08/01/2009
Parametros Entrada :
           psCodigoPais : Codigo de Pais
           psCodigoMarca: Codigo de Marca
           psCodigoCanal: Codigo de Canal
           psCodigoCliente: Codigo de Cliente
           psCodigoPeriodo: Codigo de Periodo

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DEVUE_SALDO_CAMPA(
  psCodigoPais           VARCHAR2,
  psCodigoMarca          VARCHAR2,
  psCodigoCanal          VARCHAR2,
  psCodigoCliente        VARCHAR2,
  psCodigoPeriodo        VARCHAR2,
  psCodigoZona           VARCHAR2)
RETURN NUMBER
IS
  lsCodigoPeriodoDes   SEG_PERIO_CORPO.COD_PERI%TYPE;

  lnOidPais         SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca        SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal        SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo      CRA_PERIO.OID_PERI%TYPE;

  lnOidPeriodoDes   CRA_PERIO.OID_PERI%TYPE;
  ldFechaInicio     DATE;
  ldFechaFin        DATE;
  lnSaldo           NUMBER;
BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --Calculamos el periodo posterior
  lsCodigoPeriodoDes := GEN_FN_CALCU_PERIO(psCodigoPeriodo, 1);
  lnOidPeriodoDes := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoDes, lnOidMarca, lnOidCanal);

  --Recuperamos la fecha inicial de Facturacion de la Zona del Periodo Actual
  SELECT cro.FEC_INIC
  INTO   ldFechaInicio
  FROM   CRA_CRONO cro, ZON_ZONA zon, CRA_ACTIV act
  WHERE  zon.PAIS_OID_PAIS = lnOidPais
    AND  zon.MARC_OID_MARC = lnOidMarca
    AND  zon.CANA_OID_CANA = lnOidCanal
    AND  zon.COD_ZONA = psCodigoZona
    AND  cro.ZZON_OID_ZONA = zon.oid_zona
    AND  cro.PERD_OID_PERI = lnOidPeriodo
    AND  cro.CACT_OID_ACTI = act.oid_acti
    AND  act.COD_ACTI = 'FA';

  --Recuperamos la fecha inicial de Facturacion de la Zona del Periodo Actual
  SELECT cro.FEC_INIC
  INTO   ldFechaFin
  FROM   CRA_CRONO cro, ZON_ZONA zon, CRA_ACTIV act
  WHERE  zon.PAIS_OID_PAIS = lnOidPais
    AND  zon.MARC_OID_MARC = lnOidMarca
    AND  zon.CANA_OID_CANA = lnOidCanal
    AND  zon.COD_ZONA = psCodigoZona
    AND  cro.ZZON_OID_ZONA = zon.oid_zona
    AND  cro.PERD_OID_PERI = lnOidPeriodoDes
    AND  cro.CACT_OID_ACTI = act.oid_acti
    AND  act.COD_ACTI = 'FA';

  -- Recuperamos el Saldo del Cliente de acuerdo a Campañas del Cliente
  SELECT NVL(SUM(ccc.IMP_PEND), 0)
   INTO lnSaldo
   FROM CCC_MOVIM_CUENT_CORRI ccc, MAE_CLIEN cli
  WHERE cli.PAIS_OID_PAIS = lnOidPais
    AND cli.COD_CLIE = psCodigoCliente
    AND ccc.CLIE_OID_CLIE = cli.OID_CLIE
    AND ccc.FEC_VENC >= ldFechaInicio
    AND ccc.FEC_VENC < ldFechaFin + 1
    AND ccc.IMP_PEND > 0;

  RETURN lnSaldo;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DEVUE_SALDO_CAMPA: ' || ls_sqlerrm);
END HIP_FN_DEVUE_SALDO_CAMPA;

/***********************************************************************************
Descripcion        : Devuelve Saldo a Pagar para proximo Pedido
Fecha Creacion     : 27/01/2009
Parametros Entrada :
           psCodigoPais :   Codigo de Pais
           psCodigoMarca:   Codigo de Marca
           psCodigoCanal:   Codigo de Canal
           psCodigoRegion:  Codigo de Region
           psCodigoZona:    Codigo de Zona
           psCodigoCliente: Codigo de Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DEVUE_SALDO_PAGAR(
  psCodigoPais           VARCHAR2,
  psCodigoMarca          VARCHAR2,
  psCodigoCanal          VARCHAR2,
  psCodigoRegion         VARCHAR2,
  psCodigoZona           VARCHAR2,
  psCodigoCliente        VARCHAR2)
RETURN NUMBER
IS
  lnOidPais         SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca        SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal        SEG_CANAL.OID_CANA%TYPE;

  lnOidRegion       ZON_REGIO.OID_REGI%TYPE;
  lnOidZona         ZON_ZONA.OID_ZONA%TYPE;

  lnOidPeriodo      CRA_PERIO.OID_PERI%TYPE;
  lnOidPeriodoSig   CRA_PERIO.OID_PERI%TYPE;
  lsCodPeriodo      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoSig   SEG_PERIO_CORPO.COD_PERI%TYPE;

  ldFechaFin        DATE;
  lnSaldo           NUMBER;
  lnTotalPedidos    NUMBER;
BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

  --Recuperamos el Oid Region
  SELECT OID_REGI
  INTO  lnOidRegion
  FROM  ZON_REGIO
  WHERE PAIS_OID_PAIS = lnOidPais
    AND MARC_OID_MARC = lnOidMarca
    AND CANA_OID_CANA = lnOidCanal
    AND COD_REGI = psCodigoRegion;

  --Recuperamos el Oid zona
  SELECT OID_ZONA
  INTO  lnOidZona
  FROM  ZON_ZONA
  WHERE PAIS_OID_PAIS = lnOidPais
    AND MARC_OID_MARC = lnOidMarca
    AND CANA_OID_CANA = lnOidCanal
    AND COD_ZONA = psCodigoZona;

  --Recuperamos la ultima campaña cerrada de la región de la consultora
  BEGIN
    SELECT OID_PERI, COD_PERI
    INTO   lnOidPeriodo, lsCodPeriodo
    FROM   (
            SELECT cra.OID_PERI, per.COD_PERI
             FROM FAC_CONTR_CIERR con,
                  FAC_TIPOS_CIERR tip,
                  CRA_PERIO cra,
                 SEG_PERIO_CORPO per
            WHERE con.PAIS_OID_PAIS = lnOidPais
              AND con.ZORG_OID_REGI = lnOidRegion
              AND con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER
             AND tip.COD_TIPO_CIER = 'R'
              AND cra.OID_PERI = con.Perd_Oid_Peri
              AND per.OID_PERI = cra.PERI_OID_PERI
            ORDER BY cra.FEC_INIC DESC
            )
    WHERE ROWNUM = 1;
  EXCEPTION
    WHEN OTHERS THEN
       lnSaldo := 0;
       RETURN lnSaldo;
  END;
  --Calculamos el periodo Siguiente
  lsCodPeriodoSig := GEN_FN_CALCU_PERIO(lsCodPeriodo, 1);
  lnOidPeriodoSig := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSig, lnOidMarca, lnOidCanal);

  --Validamos si la consultora ha pasado pedido en la campaña Siguiente
  SELECT COUNT(*)
    INTO lnTotalPedidos
    FROM PED_SOLIC_CABEC psc,
         PED_TIPO_SOLIC_PAIS tsp,
         PED_TIPO_SOLIC ts,
         MAE_CLIEN cl
   WHERE psc.IND_OC = 1
     AND ((psc.IND_PEDI_PRUE is null) OR (psc.IND_PEDI_PRUE = 0))
     AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
     AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
     AND ts.IND_DEVO  = 0
     AND ts.IND_ANUL  = 0
     AND psc.IND_TS_NO_CONSO = 1
     AND psc.MODU_OID_MODU <> 15
     AND psc.CLIE_OID_CLIE = cl.Oid_Clie
     AND cl.COD_CLIE = psCodigoCliente
     AND cl.PAIS_OID_PAIS = lnOidPais
     AND psc.PERD_OID_PERI = lnOidPeriodoSig;

  --Si Paso Pedidos en la Campaña Siguiente, recuperamos la Campaña SubSiguiente
  IF(lnTotalPedidos > 0) THEN
    lsCodPeriodoSig := GEN_FN_CALCU_PERIO(lsCodPeriodoSig, 1);
    lnOidPeriodoSig := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoSig, lnOidMarca, lnOidCanal);
  END IF;

  --Recuperamos la fecha inicial de Facturacion de la Zona del Periodo Siguiente o SubSiguiente
  SELECT cro.FEC_INIC
  INTO   ldFechaFin
  FROM   CRA_CRONO cro, ZON_ZONA zon, CRA_ACTIV act
  WHERE  zon.OID_ZONA = lnOidZona
    AND  cro.ZZON_OID_ZONA = zon.OID_ZONA
    AND  cro.PERD_OID_PERI = lnOidPeriodoSig
    AND  cro.CACT_OID_ACTI = act.OID_ACTI
    AND  act.COD_ACTI = 'FA';

  -- Recuperamos el Saldo del Cliente de acuerdo si paso pedido en la campaña evaluada anteriormente
  SELECT NVL(SUM(ccc.IMP_PEND), 0)
   INTO lnSaldo
   FROM CCC_MOVIM_CUENT_CORRI ccc, MAE_CLIEN cli
  WHERE cli.PAIS_OID_PAIS = lnOidPais
    AND cli.COD_CLIE = psCodigoCliente
    AND ccc.CLIE_OID_CLIE = cli.OID_CLIE
    AND ccc.FEC_VENC < ldFechaFin + 1
    AND ccc.IMP_PEND > 0;

  RETURN lnSaldo;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DEVUE_SALDO_PAGAR: ' || ls_sqlerrm);
END HIP_FN_DEVUE_SALDO_PAGAR;


/***************************************************************************
Descripcion        : Consulta para el Cornograma de Acticvidades
Fecha Creacion     : 28/01/2009
Parametros Entrada :
           psPais : Codigo de Pais
           psMarca: Codigo de Marca
           psCanal: Codigo de Canal
           psZona: Codigo de Zona
Autor              : efernandezo
***************************************************************************/
  PROCEDURE HIP_PR_ACTIV(psPais  VARCHAR2,
                               psMarca VARCHAR2,
                               psCanal VARCHAR2,
                               psZona  VARCHAR2) IS
    /* Declaracion de Variables Entradas */
    varZonaOid NUMBER;

    /* Declaracion de Variables */
    ln_sqlcode NUMBER(10);
    ls_sqlerrm VARCHAR2(150);
    W_FILAS NUMBER := 10000;

    varPeriodoSiguiente NUMBER;
    varPeriodoAnterior  NUMBER;
    varPeriodoActual    NUMBER;
    varFechaPerAntInic  VARCHAR2(10);
    varFechaPerActInic  VARCHAR2(10);
    varFechaPerDesInic  VARCHAR2(10);
    varPeriodo          VARCHAR2(6);
    varDescripcion      VARCHAR2(100);
    varOidPais          NUMBER;
    varOidMarca         NUMBER;
    varOidCanal         NUMBER;

    CURSOR C_ACTIVIDADES is
      SELECT H.COD_ACTI FROM HIP_ACTIV_CRONO H WHERE H.COD_PAIS = psPais ORDER BY H.NUM_ORDE;
    TYPE RecActividades IS RECORD(
      COD_ACTI varchar2(4));

    TYPE recActividadesTab IS TABLE OF RecActividades;
    recActividadescord recActividadesTab;

  begin

    varOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psMarca); --'T'
    varOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCanal); --'VD'

    SELECT Z.OID_ZONA
      INTO varZonaOid
      FROM ZON_ZONA Z
     WHERE Z.COD_ZONA = psZona
       AND Z.CANA_OID_CANA = varOidCanal
       AND Z.MARC_OID_MARC = varOidMarca;

    varOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psPais);

    varPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(varOidPais,psMarca,varOidCanal);

--  varPeriodoSiguiente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PERIO(GEN_FN_CALCU_PERIO(varPeriodo,1));

    varPeriodoSiguiente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_FN_CALCU_PERIO(varPeriodo,1),varOidMarca,varOidCanal,TRUE);
    varPeriodoAnterior  := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_FN_CALCU_PERIO(varPeriodo,-1),varOidMarca,varOidCanal,TRUE);
    varPeriodoActual    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(varPeriodo,varOidMarca,varOidCanal,TRUE);


    DBMS_OUTPUT.PUT_LINE('periodos: ' || varPeriodoSiguiente || '-' ||
                         varPeriodoAnterior || '-' || varPeriodoActual);

    INSERT INTO GTT_HIPER_CONSU_ACTIV_PERIO
      (DES_ACTI, FEC_PERI_ANTE, FEC_PERI_ACTU, FEC_PERI_DESP)
      SELECT 'Descripcion Actividad',
             GEN_FN_CALCU_PERIO(varPeriodo, -1),
             varPeriodo,
             GEN_FN_CALCU_PERIO(varPeriodo, 1)
        FROM DUAL;

    OPEN C_ACTIVIDADES;
    LOOP
      FETCH C_ACTIVIDADES BULK COLLECT
        INTO recActividadescord LIMIT W_FILAS;
      IF recActividadescord.COUNT > 0 THEN
        FOR x IN recActividadescord.FIRST .. recActividadescord.LAST LOOP

          BEGIN
          SELECT FECHA
          INTO varFechaPerAntInic
          FROM (
                SELECT to_char(CR.FEC_INIC, 'dd/mm/yyyy') AS FECHA
                  FROM CRA_CRONO CR
                 WHERE CR.CACT_OID_ACTI =
                       (SELECT C.OID_ACTI
                          FROM CRA_ACTIV C
                         WHERE C.COD_ACTI = recActividadescord(x)
                        .COD_ACTI
                           AND C.PAIS_OID_PAIS = varOidPais)
                   AND CR.PERD_OID_PERI = varPeriodoAnterior
                   AND CR.ZZON_OID_ZONA = varZonaOid
                   ORDER BY CR.FEC_INIC DESC
                 ) TEMP
          WHERE ROWNUM = 1;
          EXCEPTION
            WHEN no_data_found THEN
              varFechaPerAntInic := '';
          END;

          BEGIN
          SELECT FECHA
          INTO varFechaPerActInic
          FROM (
              SELECT to_char(CR.FEC_INIC, 'dd/mm/yyyy') AS FECHA
                FROM CRA_CRONO CR
               WHERE CR.CACT_OID_ACTI =
                     (SELECT C.OID_ACTI
                        FROM CRA_ACTIV C
                       WHERE C.COD_ACTI = recActividadescord(x)
                      .COD_ACTI
                         AND C.PAIS_OID_PAIS = varOidPais)
                 AND CR.PERD_OID_PERI = varPeriodoActual
                 AND CR.ZZON_OID_ZONA = varZonaOid
                 ORDER BY CR.FEC_INIC DESC
                ) TEMP
             WHERE ROWNUM = 1;
          EXCEPTION
            WHEN no_data_found THEN
              varFechaPerActInic := '';
          END;

          BEGIN
            SELECT TEMP.FECHA
            INTO varFechaPerDesInic
            FROM(
                     SELECT to_char(CR.FEC_INIC, 'dd/mm/yyyy') AS FECHA
                          FROM CRA_CRONO CR
                         WHERE CR.CACT_OID_ACTI =
                               (SELECT C.OID_ACTI
                                  FROM CRA_ACTIV C
                                 WHERE C.COD_ACTI = recActividadescord(x).COD_ACTI
                                   AND C.PAIS_OID_PAIS = varOidPais)
                           AND CR.PERD_OID_PERI = varPeriodoSiguiente
                           AND CR.ZZON_OID_ZONA = varZonaOid
                     ORDER BY CR.FEC_INIC DESC
                  ) TEMP
                  WHERE ROWNUM = 1;

          EXCEPTION
            WHEN no_data_found THEN
              varFechaPerDesInic := '';
          END;

          BEGIN
            SELECT PQ_APL_AUX.Valor_Gen_I18n_Sicc(1,
                                                  C.OID_ACTI,
                                                  'CRA_ACTIV')
              INTO varDescripcion
              FROM CRA_ACTIV C
             WHERE C.COD_ACTI = recActividadescord(x)
            .COD_ACTI
               AND C.PAIS_OID_PAIS = varOidPais;
          END;

          INSERT INTO GTT_HIPER_CONSU_ACTIV_PERIO
            (COD_ACTI,
             DES_ACTI,
             FEC_PERI_ANTE,
             FEC_PERI_ACTU,
             FEC_PERI_DESP)
          VALUES
            (NVL(recActividadescord(x).COD_ACTI, '-'),
             varDescripcion,
             NVL(varFechaPerAntInic, '-'),
             NVL(varFechaPerActInic, '-'),
             NVL(varFechaPerDesInic, '-'));

        END LOOP;
      END IF;
      EXIT WHEN C_ACTIVIDADES%NOTFOUND;
    END LOOP;
    CLOSE C_ACTIVIDADES;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(ln_sqlcode || '-' || sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR HIP_PR_ACTIV: ' || ls_sqlerrm);
  end HIP_PR_ACTIV;

/**************************************************************************
Descripcion        : Consulta para los envios preferenciales
Fecha Creacion     : 28/01/2009
Parametros Entrada :
           psCliente : Codigo Cliente
           psNombre:  Nombre Cliente
Autor              : efernandezo
***************************************************************************/
  PROCEDURE HIP_PR_PROGR(psCliente VARCHAR2, psNombre VARCHAR2) IS

    /* Declaracion de Variables */
    ln_sqlcode NUMBER(10);
    ls_sqlerrm VARCHAR2(150);
    W_FILAS    NUMBER := 10000;

    /* Declaracion de Variables del Negocio */

    CURSOR C_HIST_PROGRAMA is
   SELECT TEMP.COD_PROG,
             TEMP.DES_PROG,
             HH.FEC_DESD,
             DECODE(HH.FEC_DESD, NULL, 'No participa', '') AS DES_OBSE,
             TEMP.COD_CLIE,
             DECODE(HH.FEC_DESD, NULL, '0', '1') AS FLAG_SUSC,
             TEMP.OID_CLAS,
             TEMP.OID_CLIE_TIPO_SUBT,
             TEMP.OID_TIPO_CLAS
        FROM ( SELECT F.COD_CLIE AS CLIENTE, F.COD_PROG, F.FEC_DESD
             FROM HIP_HISTO_SUSCR_CLIEN F
             WHERE F.FLG_ELIM IS NULL ) HH,
             (SELECT TP.COD_PROG, TP.DES_PROG, M.COD_CLIE, TC.OID_CLAS, MT.OID_CLIE_TIPO_SUBT, CLA.OID_TIPO_CLAS
                FROM MAE_CLIEN            M,
                     MAE_CLIEN_TIPO_SUBTI MT,
                     MAE_TIPO_CLIEN       MTC,
                     MAE_SUBTI_CLIEN      MSC,
                     HIP_TIPO_CLASI_PROGR TC,
                     HIP_TIPO_PROGR       TP,
                     MAE_TIPO_CLASI_CLIEN  CLA
               WHERE M.OID_CLIE = MT.CLIE_OID_CLIE
                 AND MTC.OID_TIPO_CLIE = MT.TICL_OID_TIPO_CLIE
                 AND MSC.OID_SUBT_CLIE = MT.SBTI_OID_SUBT_CLIE
                 AND CLA.SBTI_OID_SUBT_CLIE = MSC.OID_SUBT_CLIE
                 AND CLA.COD_TIPO_CLAS = TC.COD_TIPO_CLAS
                 AND MTC.COD_TIPO_CLIE = TC.COD_TIPO_CLIE
                 AND MSC.COD_SUBT_CLIE = TC.COD_SUBT_CLIE
                 AND TC.COD_PROG = TP.COD_PROG
                 AND M.COD_CLIE =  psCliente
                 ) TEMP
       WHERE HH.COD_PROG(+) = TEMP.COD_PROG
             AND HH.CLIENTE(+) = TEMP.COD_CLIE;

    TYPE RecHisPrograma IS RECORD(
      COD_PROG  VARCHAR2(2),
      DES_PROG  VARCHAR2(40),
      FEC_DESD  DATE,
      DES_OBSE  VARCHAR2(25),
      COD_CLIE  VARCHAR2(15),
      FLAG_SUSC VARCHAR2(1),
      OID_CLAS  NUMBER(12),
      OID_CLIE_TIPO_SUBT NUMBER(12),
      OID_TIPO_CLAS NUMBER(12)
      );

    TYPE recHisProgramaTab IS TABLE OF RecHisPrograma;
    recHisProgramacord recHisProgramaTab;

  begin

    OPEN C_HIST_PROGRAMA;
    LOOP
      FETCH C_HIST_PROGRAMA BULK COLLECT
        INTO recHisProgramacord LIMIT W_FILAS;
      IF recHisProgramacord.COUNT > 0 THEN
        FOR x IN recHisProgramacord.FIRST .. recHisProgramacord.LAST LOOP

          INSERT INTO GTT_HIPER_ENVIO_PREFE
            (COD_CLIE,
             NOM_CLIE,
             COD_PROG,
             DES_PROG,
             FEC_INSC,
             DES_OBSE,
             FLG_SUSC,
             OID_CLAS,
             OID_CLIE_TIPO_SUBT,
             OID_TIPO_CLAS
             )
          VALUES
            (psCliente,
             psNombre,
             recHisProgramacord(x).COD_PROG,
             recHisProgramacord(x).DES_PROG,
             recHisProgramacord(x).FEC_DESD,
             recHisProgramacord(x).DES_OBSE,
             recHisProgramacord(x).FLAG_SUSC,
             recHisProgramacord(x).OID_CLAS,
             recHisProgramacord(x).OID_CLIE_TIPO_SUBT,
             recHisProgramacord(x).OID_TIPO_CLAS
             );

        END LOOP;
      END IF;

      EXIT WHEN C_HIST_PROGRAMA%NOTFOUND;
    END LOOP;
    CLOSE C_HIST_PROGRAMA;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(ln_sqlcode || '-' || sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR HIP_PR_PROGRAMA: ' || ls_sqlerrm);

  end HIP_PR_PROGR;

/**************************************************************************
Descripcion        : Subcripcion del Cliente
Fecha Creacion     : 28/01/2009
Parametros Entrada :
           psCliente          :  Codigo Cliente
           psNombre           :  Nombre Cliente
           psCodPrograma      : Codigo Programa
           psOidClas          : Oid Clase
           psoidClieTipoSubT  : Oid Cliente Tipo Sub Tipo
           psoidTipoClas      : Oid TIpo Clase
           psCodPais          : Codigo Pais
           psCodMarca         : Codigo Marca
           psCodCanal         : Codigo Canal
Autor              : efernandezo
***************************************************************************/
  PROCEDURE HIP_PR_SUSCR( psCliente           VARCHAR2,
                          psNombre            VARCHAR2,
                          psCodPrograma       VARCHAR2,
                          psOidClas           VARCHAR2,
                          psoidClieTipoSubT   VARCHAR2,
                          psoidTipoClas       VARCHAR2,
                          psCodPais           VARCHAR2,
                          psCodMarca          VARCHAR2,
                          psCodCanal          VARCHAR2
                          ) IS

    /* Declaracion de Variables */
    ln_sqlcode NUMBER(10);
    ls_sqlerrm VARCHAR2(150);

    /* Declaracion de Variables del Negocio */
    varTotHist number;
    varTotClas number;
    ln_IdPais  number;
    ln_IdCanal number;
    ln_IdMarca number;
    ln_IdPeriActu number;

  begin

  ln_IdPais     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodPais);
  ln_IdCanal    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
  ln_IdMarca    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca); --'T'

--  ln_IdPeriActu := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(ln_IdPais,psCodMarca,ln_IdCanal));
  ln_IdPeriActu := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(GEN_PKG_GENER.GEN_FN_DEVUELVE_PERIO_ACTU(ln_IdPais,psCodMarca,ln_IdCanal),ln_IdMarca,ln_IdCanal,TRUE);


    SELECT COUNT(1)
      INTO varTotHist
      FROM HIP_HISTO_SUSCR_CLIEN HH
     WHERE HH.COD_PROG = psCodPrograma
       AND HH.COD_CLIE = psCliente
       AND HH.FEC_HAST IS NULL;

    SELECT COUNT(1)
      INTO varTotClas
      FROM MAE_CLIEN_CLASI MC, MAE_CLIEN M, MAE_CLIEN_TIPO_SUBTI MT
     WHERE M.OID_CLIE = MT.CLIE_OID_CLIE
       AND MT.OID_CLIE_TIPO_SUBT = MC.CTSU_OID_CLIE_TIPO_SUBT
       AND M.COD_CLIE = psCliente
       AND MC.CLAS_OID_CLAS = psOidClas
       AND MC.CTSU_OID_CLIE_TIPO_SUBT = psoidClieTipoSubT
       AND MC.TCCL_OID_TIPO_CLASI = psoidTipoClas;

    IF (varTotHist + varTotClas) = 0 THEN
      IF varTotHist = 0 THEN

        INSERT INTO HIP_HISTO_SUSCR_CLIEN HH
          (HH.COD_PAIS, HH.COD_CLIE, HH.NOM_CLIE, HH.COD_PROG, HH.FEC_DESD)
        VALUES
          (psCodPais, psCliente, psNombre, psCodPrograma, SYSDATE);

        INSERT INTO MAE_CLIEN_CLASI MC
          (MC.OID_CLIE_CLAS,
           MC.CTSU_OID_CLIE_TIPO_SUBT,
           MC.CLAS_OID_CLAS,
           MC.TCCL_OID_TIPO_CLASI,
           MC.IND_PPAL,
           MC.PERD_OID_PERI
           )
           values
           ( MAE_CLCL_SEQ.NEXTVAL,
             psoidClieTipoSubT,
             psOidClas,
             psoidTipoClas,
             0,
             ln_IdPeriActu --PERIODO ACTUAL
           );

      END IF;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(ln_sqlcode || '-' || sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR HIP_PR_SUSCR: ' || ls_sqlerrm);

  end HIP_PR_SUSCR;

/**************************************************************************
Descripcion        : Fin Subcripcion del Cliente
Fecha Creacion     : 28/01/2009
Parametros Entrada :
           psNombre           :  Nombre Cliente
           psCodPrograma      : Codigo Programa
           psOidClas          : Oid Clase
           psoidClieTipoSubT  : Oid Cliente Tipo Sub Tipo
           psoidTipoClas      : Oid TIpo Clase
Autor              : efernandezo
***************************************************************************/
  PROCEDURE HIP_PR_FINSU(psCliente     VARCHAR2,
                                 psCodPrograma VARCHAR2,
                                 psOidClas     VARCHAR2,
                                 psoidClieTipoSubT     VARCHAR2,
                                 psoidTipoClas         VARCHAR2
                                 ) IS

    /* Declaracion de Variables */
    ln_sqlcode NUMBER(10);
    ls_sqlerrm VARCHAR2(150);

    /* Declaracion de Variables del Negocio */
    varTotHist number;
    varTotClas number;

    varOidClieClas      number;

  begin

    SELECT COUNT(1)
      INTO varTotHist
      FROM HIP_HISTO_SUSCR_CLIEN HH
     WHERE HH.COD_PROG = psCodPrograma
       AND HH.COD_CLIE = psCliente
       AND HH.FEC_HAST IS NULL;

     SELECT COUNT(1)
     INTO varTotClas
      FROM MAE_CLIEN_CLASI MC, MAE_CLIEN M, MAE_CLIEN_TIPO_SUBTI MT
     WHERE M.OID_CLIE = MT.CLIE_OID_CLIE
       AND MT.OID_CLIE_TIPO_SUBT = MC.CTSU_OID_CLIE_TIPO_SUBT
       AND M.COD_CLIE = psCliente
       AND MC.CLAS_OID_CLAS = psOidClas
       AND MC.CTSU_OID_CLIE_TIPO_SUBT = psoidClieTipoSubT
       AND MC.TCCL_OID_TIPO_CLASI = psoidTipoClas;

    IF (varTotHist > 0) THEN
      IF (varTotClas) > 0 THEN

        UPDATE HIP_HISTO_SUSCR_CLIEN HH
           SET HH.FEC_HAST = SYSDATE,
          --     HH.FEC_DESD = NULL,
               HH.FLG_ELIM = 'E'
        WHERE HH.COD_CLIE = psCliente
           and HH.COD_PROG = psCodPrograma
           AND HH.FLG_ELIM IS NULL;

        SELECT MC.OID_CLIE_CLAS
          INTO varOidClieClas
          FROM MAE_CLIEN_CLASI MC, MAE_CLIEN M, MAE_CLIEN_TIPO_SUBTI MT
         WHERE M.OID_CLIE = MT.CLIE_OID_CLIE
           AND MT.OID_CLIE_TIPO_SUBT = MC.CTSU_OID_CLIE_TIPO_SUBT
           AND M.COD_CLIE = psCliente
           AND MC.CLAS_OID_CLAS = psOidClas
           AND MC.CTSU_OID_CLIE_TIPO_SUBT = psoidClieTipoSubT
           AND MC.TCCL_OID_TIPO_CLASI = psoidTipoClas;

        DELETE FROM MAE_CLIEN_CLASI MCX
         WHERE MCX.OID_CLIE_CLAS = varOidClieClas;

      END IF;

    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(ln_sqlcode || '-' || sqlerrm, 1, 250);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR HIP_PR_FINSU: ' || ls_sqlerrm);

  end HIP_PR_FINSU;

/**************************************************************************
Descripcion        : Cadena de Territorios
Fecha Creacion     : 28/01/2009
Parametros Entrada :
           psPais          :  Codigo Pais
           psSecccion      : Codigo Seccion
Autor              : efernandezo
***************************************************************************/
   FUNCTION HIP_FN_TERRI (psPais               VARCHAR2,
                          psZona               VARCHAR2,
                          psSecccion           VARCHAR2)
   RETURN VARCHAR2
   IS

    /* Declaracion de Variables */
    W_FILAS    NUMBER := 10000;

    -- Local variables here
    i integer:=0;
    varCadena VARCHAR2(1000);

  CURSOR C_TERRITORIO is
      SELECT ZT.COD_TERR
      FROM ZON_TERRI_ADMIN ZTA, ZON_SECCI ZS,
           ZON_TERRI ZT, ZON_ZONA ZZ, SEG_PAIS SP
      WHERE ZTA.ZSCC_OID_SECC = ZS.OID_SECC
        AND ZZ.OID_ZONA = ZS.ZZON_OID_ZONA
        AND ZZ.PAIS_OID_PAIS = SP.OID_PAIS
        AND ZTA.TERR_OID_TERR = ZT.OID_TERR
        AND ZS.COD_SECC =  psSecccion
        AND ZZ.COD_ZONA = psZona
        AND SP.COD_PAIS = psPais
      ORDER BY 1 desc;

    TYPE RecTerritorio IS RECORD(
      COD_TERR  NUMBER(6)
      );

    TYPE recTerritorioTab IS TABLE OF RecTerritorio;
    recTerritorioRecord recTerritorioTab;

    BEGIN

     OPEN C_TERRITORIO;
    LOOP
      FETCH C_TERRITORIO BULK COLLECT
        INTO recTerritorioRecord LIMIT W_FILAS;
      IF recTerritorioRecord.COUNT > 0 THEN
        FOR x IN recTerritorioRecord.FIRST .. recTerritorioRecord.LAST LOOP

        IF i = 0 THEN
        varCadena := recTerritorioRecord(x).COD_TERR;
        ELSE
          IF(i=20 OR i=40 OR i=60 OR i=80 OR i=100) THEN
            varCadena := varCadena ||', '|| recTerritorioRecord(x).COD_TERR;
          ELSE
            varCadena := varCadena ||','|| recTerritorioRecord(x).COD_TERR;
          END IF;
        END IF;
         i:= i+1;

        END LOOP;
      END IF;

      EXIT WHEN C_TERRITORIO%NOTFOUND;
    END LOOP;
    CLOSE C_TERRITORIO;

    DBMS_OUTPUT.PUT_LINE('varCadena: ' || varCadena);

    RETURN varCadena;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN '';

   END HIP_FN_TERRI;

/***********************************************************************************
Descripcion        : Devuelve Promedio de Venta
Fecha Creacion     : 30/01/2009
Parametros Entrada :
           psCodigoPais : Codigo de Pais
           psCodigoMarca: Codigo de Marca
           psCodigoCanal: Codigo de Canal
           psCodigoPeriodoInicio: Codigo de Cliente
           psCodigoPeriodoFin: Codigo de Periodo
           psCodigoCliente : Codigo de Cliente
           pnNumeroCampanas: Numero de Campañas a Promediar
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DEVUE_PROME_VENTA(
  psCodigoPais           VARCHAR2,
  psCodigoMarca          VARCHAR2,
  psCodigoCanal          VARCHAR2,
  psCodigoPeriodoInicio  VARCHAR2,
  psCodigoPeriodoFin     VARCHAR2,
  psCodigoCliente        VARCHAR2,
  pnNumeroCampanas       NUMBER)
RETURN NUMBER
IS
  lnOidPais         SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca        SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal        SEG_CANAL.OID_CANA%TYPE;
  lnSaldo           NUMBER;
BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

  SELECT SUM(mcc.IMP_MOVI)
  INTO   lnSaldo
  FROM CCC_PROCE PRO,
       CCC_SUBPR SUB,
       CCC_MOVIM_CUENT_CORRI MCC,
       MAE_CLIEN MCL,
       CRA_PERIO CRA,
       SEG_PERIO_CORPO PER
 WHERE PRO.PAIS_OID_PAIS = lnOidPais
   AND PRO.OID_PROC = SUB.CCPR_OID_PROC
   AND MCL.OID_CLIE = MCC.CLIE_OID_CLIE
	 AND PRO.COD_PROC = 'CCC001'
	 AND SUB.COD_SUBP= '1'
   AND MCC.SUBP_OID_SUBP_CREA = SUB.OID_SUBP
   AND MCL.PAIS_OID_PAIS = lnOidPais
	 AND MCL.COD_CLIE = psCodigoCliente
   AND CRA.OID_PERI = MCC.PERD_OID_PERI
   AND CRA.PERI_OID_PERI = PER.OID_PERI
   AND CRA.MARC_OID_MARC = lnOidMarca
   AND CRA.CANA_OID_CANA = lnOidCanal
   AND PER.COD_PERI >= psCodigoPeriodoInicio
   AND PER.COD_PERI <= psCodigoPeriodoFin;

  RETURN ROUND(lnSaldo/pnNumeroCampanas,2);

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DEVUE_PROME_VENTA: ' || ls_sqlerrm);
END HIP_FN_DEVUE_PROME_VENTA;

/**************************************************************************
Descripcion        : Devuelve Periodo en base al Pais, Marca y Canal, fecha
                     Devuelve Periodo mayor si hay cruce de campaña
Fecha Creacion     : 13/03/2009
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DEVUELVE_PERIO_FECHA(
  psCodPais   VARCHAR2,
  psCodMarca  VARCHAR2,
  psCodCanal  VARCHAR2,
  pdFecha     DATE)
RETURN VARCHAR2
IS
  ld_fecha               DATE;
  ln_idpais              seg_pais.oid_pais%TYPE;
  ln_id_marca            seg_marca.oid_marc%TYPE;
  ln_id_canal            seg_canal.oid_cana%TYPE;
  ls_cod_peri            VARCHAR2(6);

  CURSOR cPeriodo IS
  SELECT
        b.cod_peri
    FROM CRA_PERIO A,
         SEG_PERIO_CORPO B
    WHERE
        A.PAIS_OID_PAIS = ln_idpais   AND
        A.MARC_OID_MARC = ln_id_marca AND
        A.CANA_OID_CANA = ln_id_canal AND
        A.FEC_INIC <= ld_fecha AND
        A.FEC_FINA >= ld_fecha AND
        B.OID_PERI = A.PERI_OID_PERI
     ORDER BY COD_PERI DESC;

BEGIN
  /* Obteniendo id */
  ln_idpais   := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodPais);
  ln_id_marca := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodMarca);
  ln_id_canal := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodCanal);
  ld_fecha    := gen_pkg_gener.GEN_FN_FECHA_SIN_HORA(pdFecha);

  /* encontrando periodo */
  BEGIN
    SELECT
        b.cod_peri
    INTO
        ls_cod_peri
    FROM CRA_PERIO A,
         SEG_PERIO_CORPO B
    WHERE
        A.PAIS_OID_PAIS = ln_idpais   AND
        A.MARC_OID_MARC = ln_id_marca AND
        A.CANA_OID_CANA = ln_id_canal AND
        A.FEC_INIC <= ld_fecha AND
        A.FEC_FINA >= ld_fecha AND
        B.OID_PERI = A.PERI_OID_PERI ;
    RETURN ls_cod_peri ;

  EXCEPTION
  WHEN TOO_MANY_ROWS THEN
     FOR c1 IN cPeriodo LOOP
         ls_cod_peri := c1.cod_peri;
         EXIT;
     END LOOP;
     RETURN ls_cod_peri;
  END ;

EXCEPTION
WHEN OTHERS THEN
   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DEVUELVE_PERIO_FECHA: '||ls_sqlerrm);
END HIP_FN_DEVUELVE_PERIO_FECHA;

/***********************************************************************************
Descripcion        : Devuelve Promedio de Venta de las ultimas 5 campañas de la Consultora
Fecha Creacion     : 30/01/2009
Parametros Entrada :
           psCodigoPais : Codigo de Pais
           psCodigoMarca: Codigo de Marca
           psCodigoCanal: Codigo de Canal
           psCodigoRegion: Codigo de Region
           psCodigoCliente : Codigo de Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_OBTIE_PROME_VENTA(
  psCodigoPais           VARCHAR2,
  psCodigoMarca          VARCHAR2,
  psCodigoCanal          VARCHAR2,
  psCodigoRegion         VARCHAR2,
  psCodigoCliente        VARCHAR2)
RETURN NUMBER
IS
  lnOidPais         SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca        SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal        SEG_CANAL.OID_CANA%TYPE;
  lnSaldo           NUMBER;
  lnNumeroCampanas  NUMBER;

  lsCodigoPeriodoFin       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodigoPeriodoIni       SEG_PERIO_CORPO.COD_PERI%TYPE;
BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

  --Obtenemos el ultimo periodo de cierre de region
  SELECT NVL(MAX(cor.COD_PERI),'000000')
    INTO lsCodigoPeriodoFin
    FROM FAC_CONTR_CIERR con,
         FAC_TIPOS_CIERR tip,
         CRA_PERIO cra,
         SEG_PERIO_CORPO cor,
         SEG_PAIS pai,
         ZON_REGIO reg,
         SEG_MARCA mar,
         SEG_CANAL can
   WHERE con.TCIE_OID_TIPO_CIER = tip.OID_TIPO_CIER
     AND cra.OID_PERI = con.PERD_OID_PERI
     AND cor.OID_PERI = cra.PERI_OID_PERI
     AND pai.OID_PAIS = cra.PAIS_OID_PAIS
     AND reg.OID_REGI = con.ZORG_OID_REGI
     AND reg.COD_REGI = psCodigoRegion
     AND tip.COD_TIPO_CIER = 'R'
     AND pai.COD_PAIS = psCodigoPais
     AND mar.COD_MARC = psCodigoMarca
     AND can.COD_CANA = psCodigoCanal
     AND con.VAL_RESU_PROC = 'OK'
     AND reg.MARC_OID_MARC = mar.OID_MARC
     AND reg.CANA_OID_CANA = can.OID_CANA
     AND reg.PAIS_OID_PAIS = pai.OID_PAIS;

  --Recuperamos el periodo anterior a 5 campañas
  BEGIN
      SELECT a.periodo
      INTO lsCodigoPeriodoIni
      FROM (SELECT ROWNUM ID, periodo
            FROM (SELECT   cod_peri periodo
                      FROM SEG_PERIO_CORPO cor
                     WHERE cor.cod_peri <= lsCodigoPeriodoFin
                  ORDER BY cod_peri DESC)) a
      WHERE a.ID = 5;

  EXCEPTION
    WHEN OTHERS THEN
      lsCodigoPeriodoIni := '000000';
  END;

  IF(lsCodigoPeriodoIni='000000' OR lsCodigoPeriodoFin='000000') THEN
    RETURN NULL;
  END IF;

  --Obtenemos el saldo en el rango de periodos solicitado
  SELECT SUM(mcc.IMP_MOVI)
  INTO   lnSaldo
  FROM CCC_PROCE PRO,
       CCC_SUBPR SUB,
       CCC_MOVIM_CUENT_CORRI MCC,
       MAE_CLIEN MCL,
       CRA_PERIO CRA,
       SEG_PERIO_CORPO PER
 WHERE PRO.PAIS_OID_PAIS = lnOidPais
   AND PRO.OID_PROC = SUB.CCPR_OID_PROC
   AND MCL.OID_CLIE = MCC.CLIE_OID_CLIE
	 AND PRO.COD_PROC = 'CCC001'
	 AND SUB.COD_SUBP= '1'
   AND MCC.SUBP_OID_SUBP_CREA = SUB.OID_SUBP
   AND MCL.PAIS_OID_PAIS = lnOidPais
	 AND MCL.COD_CLIE = psCodigoCliente
   AND CRA.OID_PERI = MCC.PERD_OID_PERI
   AND CRA.PERI_OID_PERI = PER.OID_PERI
   AND CRA.MARC_OID_MARC = lnOidMarca
   AND CRA.CANA_OID_CANA = lnOidCanal
   AND PER.COD_PERI >= lsCodigoPeriodoIni
   AND PER.COD_PERI <= lsCodigoPeriodoFin;


  SELECT COUNT(DISTINCT PER.COD_PERI)
    INTO lnNumeroCampanas
    FROM CCC_PROCE PRO,
         CCC_SUBPR SUB,
         CCC_MOVIM_CUENT_CORRI MCC,
         MAE_CLIEN MCL,
         CRA_PERIO CRA,
         SEG_PERIO_CORPO PER
   WHERE PRO.PAIS_OID_PAIS = lnOidPais
     AND PRO.OID_PROC = SUB.CCPR_OID_PROC
     AND MCL.OID_CLIE = MCC.CLIE_OID_CLIE
  	 AND PRO.COD_PROC = 'CCC001'
  	 AND SUB.COD_SUBP= '1'
     AND MCC.SUBP_OID_SUBP_CREA = SUB.OID_SUBP
     AND MCL.PAIS_OID_PAIS = lnOidPais
  	 AND MCL.COD_CLIE = psCodigoCliente
     AND CRA.OID_PERI = MCC.PERD_OID_PERI
     AND CRA.PERI_OID_PERI = PER.OID_PERI
     AND CRA.MARC_OID_MARC = lnOidMarca
     AND CRA.CANA_OID_CANA = lnOidCanal
     AND PER.COD_PERI >= lsCodigoPeriodoIni
     AND PER.COD_PERI <= lsCodigoPeriodoFin;

  IF(lnSaldo = 0) THEN
    RETURN 0;
  ELSE
    RETURN ROUND(lnSaldo/lnNumeroCampanas,2);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_OBTIE_PROME_VENTA: ' || ls_sqlerrm);
END HIP_FN_OBTIE_PROME_VENTA;

/***********************************************************************************
Descripcion        : Devuelve Mensaje de Origen del Pedido
Fecha Creacion     : 12/08/2009
Parametros Entrada :

           pnOidOrdenCompra: Oid Orden Compra
           psCodigoCliente:  Codigo Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_OBTIE_ORIGE_PEDID_CONSO(
  pnOidOrdenCompra       NUMBER,
  psCodigoCliente        VARCHAR2)
RETURN VARCHAR2
IS
  lbEncontrado      BOOLEAN;
  lsMensaje         VARCHAR2(50);
BEGIN

  IF(pnOidOrdenCompra IS NULL) THEN
    RETURN ' ';
  END IF;

  --BUSCAMOS LA SOLICITUD POR REFERENCIA EN INT_SOLIC_CONSO_CABEC
  BEGIN
    SELECT (CASE WHEN ((NVL(con.IND_RECE_CC,'0') +
                      NVL(con.IND_RECE_DD,'0') + NVL(con.IND_RECE_DIGI,'0') +  NVL(con.IND_RECE_IVR,'0') +
                      NVL(con.IND_RECE_OCR,'0') + NVL(con.IND_RECE_WEB, '0')) > 1) THEN 'Origen Mixto'
                 WHEN (NVL(con.IND_RECE_CC,'0') = '1') THEN 'Call Center'
                 WHEN (NVL(con.IND_RECE_DD,'0') = '1') THEN 'Digitacion Distribuida'
                 WHEN (NVL(con.IND_RECE_DIGI,'0') = '1') THEN 'Digitacion'
                 WHEN (NVL(con.IND_RECE_OCR,'0') = '1') THEN 'OCR'
                 WHEN (NVL(con.IND_RECE_WEB,'0') = '1') THEN 'WEB'
                 WHEN (NVL(con.IND_RECE_IVR,'0') = '1') THEN 'IVR'
                 ELSE ' '
            END)
      INTO lsMensaje
      FROM INT_SOLIC_CONSO_CABEC con
     WHERE con.SOCA_OID_SOLI_CABE_REFE = pnOidOrdenCompra
       AND con.COD_CLIE = psCodigoCliente;

      lbEncontrado := TRUE;
  EXCEPTION
    WHEN OTHERS THEN
      lbEncontrado := FALSE;
      lsMensaje:= ' ';
  END;

  IF(NOT lbEncontrado) THEN
    --BUSCAMOS LA SOLICITUD POR REFERENCIA EN PED_HISTO_SOLIC_CONSO_CABEC
    BEGIN
      SELECT (CASE WHEN ((NVL(con.IND_RECE_CC,'0') +
                      NVL(con.IND_RECE_DD,'0') + NVL(con.IND_RECE_DIGI,'0') +  NVL(con.IND_RECE_IVR,'0') +
                        NVL(con.IND_RECE_OCR,'0') + NVL(con.IND_RECE_WEB, '0')) > 1) THEN 'Origen Mixto'
                   WHEN (NVL(con.IND_RECE_CC,'0') = '1') THEN 'Call Center'
                   WHEN (NVL(con.IND_RECE_DD,'0') = '1') THEN 'Digitacion Distribuida'
                   WHEN (NVL(con.IND_RECE_DIGI,'0') = '1') THEN 'Digitacion'
                   WHEN (NVL(con.IND_RECE_OCR,'0') = '1') THEN 'OCR'
                   WHEN (NVL(con.IND_RECE_WEB,'0') = '1') THEN 'WEB'
                 WHEN (NVL(con.IND_RECE_IVR,'0') = '1') THEN 'IVR'
                   ELSE ' '
              END)
        INTO lsMensaje
        FROM PED_HISTO_SOLIC_CONSO_CABEC con
       WHERE con.SOCA_OID_SOLI_CABE_REFE = pnOidOrdenCompra
         AND con.COD_CLIE = psCodigoCliente;

    EXCEPTION
      WHEN OTHERS THEN
        lsMensaje:= ' ';
    END;
  END IF;

  RETURN lsMensaje;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_OBTIE_ORIGE_PEDID_CONSO: ' || ls_sqlerrm);
END HIP_FN_OBTIE_ORIGE_PEDID_CONSO;

/***********************************************************************************
Descripcion        : Devuelve Importe Real Atendido para Reclamos
Fecha Creacion     : 23/11/2009
Parametros Entrada :
           pnOidReclamo : Oid Reclamo

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DEVUE_IMPOR_ATEND(
  pnOidReclamo           NUMBER)
RETURN NUMBER
IS
  lnImporte         NUMBER;
BEGIN

  SELECT NVL(SUM (VAL_PREC_TOTA_TOTA_LOCA),0)
    INTO lnImporte
         FROM (
         SELECT DISTINCT psp.oid_soli_posi,
                psp.VAL_PREC_TOTA_TOTA_LOCA
         FROM REC_SOLIC_OPERA rso,
              PED_SOLIC_POSIC psp,
              REC_OPERA_RECLA ror,
              REC_LINEA_OPERA_RECLA lor,
              PED_SOLIC_CABEC psc,
              PED_TIPO_SOLIC_PAIS ptsp,
              PED_TIPO_SOLIC pts
        WHERE rso.SOCA_OID_SOLI_CABE = psp.SOCA_OID_SOLI_CABE
          AND rso.OPRE_OID_OPER_RECL = ror.OID_OPER_RECL
          AND ror.CARE_OID_CABE_RECL = pnOidReclamo
          AND psc.OID_SOLI_CABE = psp.SOCA_OID_SOLI_CABE
          AND lor.PROD_OID_PROD = psp.PROD_OID_PROD
          AND psc.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
          AND ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
          AND lor.OPRE_OID_OPER_RECL = ror.OID_OPER_RECL
          AND lor.TIMO_OID_TIPO_MOVI = 1
          AND pts.IND_SOLI_NEGA = 0
          AND psc.FEC_FACT IS NOT NULL);

  RETURN lnImporte;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DEVUE_IMPOR_ATEND: ' || ls_sqlerrm);
END HIP_FN_DEVUE_IMPOR_ATEND;

/***********************************************************************************
Descripcion        : Devuelve Unidades Real Atendido para Reclamos
Fecha Creacion     : 23/11/2009
Parametros Entrada :
           pnOidReclamo : Oid Reclamo

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DEVUE_UNIDA_ATEND(
  pnOidReclamo           NUMBER)
RETURN NUMBER
IS
  lnTotal         NUMBER;
BEGIN

  SELECT SUM(NUM_UNID_ATEN)
    INTO lnTotal
          FROM(
              SELECT distinct psp.oid_soli_posi,
                     psp.NUM_UNID_ATEN
              FROM rec_solic_opera rso,
                   ped_solic_posic psp,
                   rec_opera_recla ror,
                   rec_linea_opera_recla lor,
                   ped_solic_cabec psc,
                   ped_tipo_solic_pais ptsp,
                   ped_tipo_solic pts
             WHERE rso.soca_oid_soli_cabe = psp.soca_oid_soli_cabe
               AND rso.opre_oid_oper_recl = ror.oid_oper_recl
               AND ror.care_oid_cabe_recl = pnOidReclamo
               AND psc.oid_soli_cabe = psp.soca_oid_soli_cabe
               AND lor.prod_oid_prod = psp.prod_oid_prod
               AND psc.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
               AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
               AND lor.opre_oid_oper_recl = ror.oid_oper_recl
               AND lor.timo_oid_tipo_movi = 1
               AND pts.ind_soli_nega = 0
               AND psc.fec_fact IS NOT NULL);

  RETURN lnTotal;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DEVUE_UNIDA_ATEND: ' || ls_sqlerrm);
END HIP_FN_DEVUE_UNIDA_ATEND;

/***************************************************************************
Descripcion        : Devuelve Limite de credito en base al nivel de riego
Fecha Creacion     : 12/08/2010
Parametros Entrada : pnOidCliente
                     pnOidNivelRiesgo
Autor              : Dennys Oliva Iriarte
***************************************************************************/
FUNCTION HIP_FN_DEVUE_LIMIT_CREDI(pnOidCliente           NUMBER,
                                  pnOidNivelRiesgo       NUMBER) RETURN varchar2
IS
  lnLimiteCredito      NUMBER;
  lnOidRegion          ZON_ZONA.oid_ZONA%type;
  lnOidZona            ZON_ZONA.ZORG_OID_REGI%type;

BEGIN

  SELECT NVL(ZON_ZONA.oid_ZONA, ''),
         NVL(ZON_ZONA.ZORG_OID_REGI , '')
    INTO lnOidZona, lnOidRegion
    FROM MAE_CLIEN_UNIDA_ADMIN,
         ZON_TERRI_ADMIN,
         ZON_SECCI,
         ZON_ZONA,
         ZON_REGIO
   WHERE ZON_TERRI_ADMIN.OID_TERR_ADMI = MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI
     AND ZON_SECCI.OID_SECC = ZON_TERRI_ADMIN.ZSCC_OID_SECC
     AND ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA
     AND ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI
     AND MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE = pnOidCliente
     AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = '1'
     AND ROWNUM = 1;

  begin
    SELECT b.val_mont_maxi_perm
      into lnLimiteCredito
      FROM car_asign_codig_confi a,
           car_param_carte b,
           (SELECT *
              FROM gen_i18n_sicc_comun
             WHERE attr_enti = 'CAR_NIVEL_RIESG') g1
     WHERE a.paca_oid_para_cart = b.oid_para_cart
       AND a.niri_oid_nive_ries = g1.val_oid
       and a.zorg_oid_regi = lnOidRegion
       and a.zzon_oid_zona = lnOidZona
       and b.niri_oid_nive_ries = pnOidNivelRiesgo;
  exception
    when no_data_found then
      SELECT b.val_mont_maxi_perm
        into lnLimiteCredito
        FROM car_asign_codig_confi a,
             car_param_carte b,
             (SELECT *
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'CAR_NIVEL_RIESG') g1
       WHERE a.paca_oid_para_cart = b.oid_para_cart
         AND a.niri_oid_nive_ries = g1.val_oid
         and a.zorg_oid_regi is null
         and a.zzon_oid_zona is null
         and b.niri_oid_nive_ries = pnOidNivelRiesgo;
  end;

  RETURN lnLimiteCredito;

EXCEPTION
  WHEN OTHERS THEN
    return '-';
    --ln_sqlcode := SQLCODE;
    --ls_sqlerrm := substr(sqlerrm,1,250);
    --RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DEVUE_LIMIT_CREDI: ' || ls_sqlerrm);
END HIP_FN_DEVUE_LIMIT_CREDI;

/***********************************************************************************
Descripcion        : Devuelve Estado de la Solicitud de Poliza
Fecha Creacion     : 25/05/2011
Parametros Entrada :
           psCodigoCliente : Codigo Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DEVUE_ESTAT_SOLIC_POLIZ(
  psCodigoCliente           VARCHAR2)
RETURN VARCHAR2
IS
  lnOcurrencias         NUMBER;
  lsEstado              VARCHAR2(50);
  ldFechaPoliza         DATE;
  ldFechaRechazada      DATE;
BEGIN

  --Verificamos si el cliente tiene poliza activa
  BEGIN
    select COUNT(1),
         CASE WHEN EST_POLI = '1' THEN 
            'solicitudPolizaDetal.estadoSolicitud.Registrada' 
         ELSE 
             'solicitudPolizaDetal.estadoSolicitud.Activa' 
         END EST_POLI
     INTO lnOcurrencias,lsEstado
     FROM SGR_FAMSE_POLIZ_REGIS
    WHERE COD_CLIE = psCodigoCliente
      AND EST_POLI IN ('1','3')
      AND EST_REGI = '1'
    GROUP BY EST_POLI;
  EXCEPTION
   WHEN NO_DATA_FOUND THEN
     lnOcurrencias := 0;
     lsEstado := NULL;
  END;
  
  IF(lnOcurrencias = 0) THEN
    
    BEGIN
      SELECT TRUNC(FEC_SOLI), DECODE(EST_POLI, '1', 'solicitudPolizaDetal.estadoSolicitud.Registrada',
                              'solicitudPolizaDetal.estadoSolicitud.Cancelada')
        INTO ldFechaPoliza, lsEstado
        FROM (SELECT FEC_SOLI,
                     EST_POLI
                FROM SGR_FAMSE_POLIZ_REGIS
               WHERE COD_CLIE = psCodigoCliente
                 AND EST_POLI = '4'
                 AND EST_REGI = '1'
               ORDER BY FEC_SOLI DESC)
       WHERE ROWNUM = 1;
    EXCEPTION
      WHEN OTHERS THEN
        lsEstado := '';
        ldFechaPoliza := NULL;
    END;

    BEGIN
      SELECT MAX(TRUNC(fs.FEC_PROC))
        INTO ldFechaRechazada
        FROM STO_DOCUM_DIGIT        dd,
             INT_SOLIC_CONSO_FAMIL_SEGUR fs
       WHERE dd.sec_nume_docu = fs.sec_nume_docu
         AND dd.cod_tipo_docu = 'FAS'
         AND dd.cod_clie = psCodigoCliente
         AND dd.Ind_Rech = '1';

      IF(ldFechaRechazada IS NOT NULL) THEN
        IF(ldFechaPoliza IS NULL) THEN
          lsEstado := 'solicitudPolizaDetal.estadoSolicitud.Rechazada'; --Rechazada
        END IF;

        IF((ldFechaPoliza IS NOT NULL) AND ldFechaRechazada > ldFechaPoliza) THEN
          lsEstado := 'solicitudPolizaDetal.estadoSolicitud.Rechazada'; --Rechazada
        END IF;
      END IF;

    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
  END IF;

  RETURN lsEstado;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DEVUE_ESTAT_SOLIC_POLIZ: ' || ls_sqlerrm);
END HIP_FN_DEVUE_ESTAT_SOLIC_POLIZ;

/***********************************************************************************
Descripcion        : Devuelve Tipo de Consultora
Fecha Creacion     : 14/12/2012
Parametros Entrada :
           codigopais: Codigo de Pais 
           oidcliente: Oid de Cliente
Autor              : Aurelio Oviedo
***************************************************************************/
FUNCTION HIP_FN_DEVUELVE_TIPO_CONSUL
(   
    codigopais VARCHAR2,
    oidcliente NUMBER
        
) RETURN VARCHAR2 IS
    lscodigoperiodo VARCHAR2(6);
    
    lscampana1  VARCHAR2(6);
    lscampana2  VARCHAR2(6);
    lscampana3  VARCHAR2(6);
    
    lsoidperiodo  VARCHAR2(4);
    
    lsrespuesta VARCHAR2(30):= '';
    
    lsindicadorbrillante      NUMBER(1);
    lsindicadorEjecutiva NUMBER(1);
    lsindicadorPrimerPedido NUMBER(1);
    lsindPedido               NUMBER(10);
    lsindicador123campania    NUMBER(10);
    lsquery                   VARCHAR2(4000);
    
    TYPE CUR_TYP  IS REF CURSOR;
    c_nuevaPedidos CUR_TYP;
    
BEGIN 
    /* obtenemos codigo de periodo */
     SELECT COD_PERI INTO lscodigoperiodo FROM BAS_CTRL_FACT WHERE COD_PAIS = codigopais AND sta_camp = '0' AND ind_camp_act = '1';
    
    /* obtenemos codigo de periodo hace 1 periodo*/
    lscampana1 := gen_pkg_gener.gen_fn_perio_nsigu (codigopais, lscodigoperiodo, -1);
    
    /* obtenemos codigo de periodo hace 2 periodos*/
    lscampana2 := gen_pkg_gener.gen_fn_perio_nsigu (codigopais, lscodigoperiodo, -2);
    
    /* obtenemos codigo de periodo hace 3 periodos*/
    lscampana3 := gen_pkg_gener.gen_fn_perio_nsigu (codigopais, lscodigoperiodo, -3);
    
    
    BEGIN 
      SELECT COUNT(1) 
        INTO lsindPedido 
        FROM ped_solic_cabec sc,
             ped_tipo_solic ts,
             ped_tipo_solic_pais tsp
       WHERE sc.clie_oid_clie          = oidcliente AND
             sc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais AND
             tsp.tsol_oid_tipo_soli     = ts.oid_tipo_soli AND
             ts.cod_tipo_soli           = 'SOC' AND
             sc.grpr_oid_grup_proc      = 5 AND
             sc.fec_fact IS NOT NULL;
      EXCEPTION
            WHEN NO_DATA_FOUND THEN
                 lsindPedido:= 0;
     END;


    BEGIN
      SELECT COUNT(1) 
        INTO lsindicadorPrimerPedido
        FROM MAE_CLIEN_DATOS_ADICI adic,
             mae_clien_tipo_subti mcts
        WHERE adic.clie_oid_clie = mcts.clie_oid_clie
          AND mcts.ticl_oid_tipo_clie = 2
          AND mcts.sbti_oid_subt_clie = 1
          AND adic.CLIE_OID_CLIE = oidcliente
          AND adic.esta_oid_esta_clie = 1;

        IF lsindicadorPrimerPedido > 0 THEN 
          IF lsindPedido > 0 THEN
            lsrespuesta := 'Nueva 1er Pedido';
          ELSE 
            lsrespuesta := 'Registrada';
          END IF;
          
          RETURN lsrespuesta;
          
        END IF;

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        lsindicadorPrimerPedido:= 0;
    END;
        
    BEGIN
    
        SELECT COUNT(1) INTO lsindicadorPrimerPedido 
            FROM MAE_CLIEN_DATOS_ADICI adic,
                 MAE_ESTAT_CLIEN est,
                 mae_clien_tipo_subti mcts
            WHERE adic.clie_oid_clie = mcts.clie_oid_clie
              AND mcts.ticl_oid_tipo_clie = 2
              AND mcts.sbti_oid_subt_clie = 1
              AND adic.ESTA_OID_ESTA_CLIE = est.OID_ESTA_CLIE
            AND adic.CLIE_OID_CLIE = oidcliente
            AND (est.COD_ESTA_CLIE = '02' OR est.COD_ESTA_CLIE = '08');
            
        IF lsindicadorPrimerPedido > 0 THEN
            lsrespuesta := 'Nueva 1er Pedido';
            RETURN lsrespuesta;
        END IF;    
            
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        lsindicadorPrimerPedido:= 0;
    END;
    
    
    lsquery :='SELECT COUNT(1) 
                 FROM MAE_CLIEN_HISTO_ESTAT c, 
                      MAE_ESTAT_CLIEN est,
                      mae_clien_tipo_subti mcts
                WHERE c.CLIE_OID_CLIE = mcts.clie_oid_clie
                  AND mcts.ticl_oid_tipo_clie = 2
                  AND mcts.sbti_oid_subt_clie = 1
                  and c.CLIE_OID_CLIE = '||oidcliente||'
            AND c.ESTA_OID_ESTA_CLIE = est.OID_ESTA_CLIE
            AND (est.COD_ESTA_CLIE = ''02'' OR est.COD_ESTA_CLIE = ''08'')
            AND c.PERD_OID_PERI = gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(:lnperiodo)';
    BEGIN
                
        EXECUTE IMMEDIATE lsquery INTO  lsindicador123campania using lscampana1 ;
        
        IF lsindicador123campania > 0 THEN
         lsrespuesta := 'Nueva 2do Pedido';
         RETURN lsrespuesta;
        END IF;
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        lsindicador123campania := 0;
    END;
    
    BEGIN
        EXECUTE IMMEDIATE lsquery INTO lsindicador123campania USING lscampana2 ;
        
        IF lsindicador123campania > 0 THEN
        lsrespuesta := 'Nueva 3er Pedido';
         RETURN lsrespuesta;
        END IF;
        
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        lsindicador123campania := 0;
    END;
    
    BEGIN
    
       EXECUTE IMMEDIATE lsquery INTO lsindicador123campania USING lscampana3 ;
       
        IF lsindicador123campania > 0 THEN
         lsrespuesta := 'Nueva 4to Pedido';
         RETURN lsrespuesta; 
        END IF;
         
    EXCEPTION
    WHEN NO_DATA_FOUND THEN
        lsindicador123campania := 0;
    END;    

    IF lsrespuesta IS NULL THEN
    lsrespuesta := 'Normal';
    END IF;
    
    RETURN lsrespuesta;
    
 EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR HIP_FN_DEVUELVE_TIPO_CONSUL: ' || ls_sqlerrm);
END HIP_FN_DEVUELVE_TIPO_CONSUL;


/***********************************************************************************
Descripcion        : Actualiza datos del Cliente
Fecha Creacion     : 05/08/2003
Parametros Entrada :
           pnOidCliente :     Oid Cliente
           psPrimerApellido:  Primer Apellido
           psSegundoApellido: Segundo Apellido
           psPrimerNombre:    Primer Nombre
           psSegundoNombre:   Segundo Nombre
           psTelefonoFijo:    Telefono Fijo
           psTelefonoCelular: Telefono Celular
           psEmail: Email
           psFechaNacimiento: Fecha de Nacimiento                      
           psCodigoUsuario:   Codigo de Usuario

Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE HIP_PR_ACTUA_DATOS_CLIEN(pnOidCliente              NUMBER,
                                   psPrimerApellido          VARCHAR2,
                                   psSegundoApellido         VARCHAR2,
                                   psPrimerNombre            VARCHAR2,
                                   psSegundoNombre           VARCHAR2,
                                   psTelefonoFijo            VARCHAR2,
                                   psTelefonoCelular         VARCHAR2,
                                   psEmail                   VARCHAR2,
                                   psFechaNacimiento         VARCHAR2,
                                   psCodigoUsuario           VARCHAR2)
IS
  lnOidComunTF      NUMBER;
  lnOidComunTM      NUMBER;
  lnOidComunML      NUMBER;
  lnOidComunOtro    NUMBER;

  lnOidTipoComuTF   NUMBER;
  lnOidTipoComuTM   NUMBER;
  lnOidTipoComuML   NUMBER;
  lnIndPrincipal    NUMBER;  
BEGIN
  
  --ACTUALIZAMOS NOMBRES DEL CLIENTE
  UPDATE MAE_CLIEN 
     SET VAL_APE1 = psPrimerApellido,
         VAL_APE2 = psSegundoApellido,
         VAL_NOM1 = psPrimerNombre,
         VAL_NOM2 = psSegundoNombre,
         USU_MODI = psCodigoUsuario
   WHERE OID_CLIE = pnOidCliente;
  
  --ACTUALIZAMOS FECHAD DE NACIMIENTO DEL CLIENTE
  UPDATE MAE_CLIEN_DATOS_ADICI
     SET FEC_NACI = TO_DATE(psFechaNacimiento, 'dd/MM/yyyy'),
         USU_MODI = psCodigoUsuario
   WHERE CLIE_OID_CLIE = pnOidCliente;  
   
  --Obtenemos el Oid de Telefono Fijo
  SELECT OID_TIPO_COMU INTO lnOidTipoComuTF FROM MAE_TIPO_COMUN WHERE COD_TIPO_COMU = 'TF';

  --Obtenemos el Oid de Telefono Celular
  SELECT OID_TIPO_COMU INTO lnOidTipoComuTM FROM MAE_TIPO_COMUN WHERE COD_TIPO_COMU = 'TM';

  --Obtenemos el Oid de Mail
  SELECT OID_TIPO_COMU INTO lnOidTipoComuML FROM MAE_TIPO_COMUN WHERE COD_TIPO_COMU = 'ML';

  lnOidComunOtro := NULL;
  FOR x IN (SELECT OID_CLIE_COMU, TICM_OID_TIPO_COMU
              FROM MAE_CLIEN_COMUN
             WHERE CLIE_OID_CLIE = pnOidCliente) LOOP

    IF(x.TICM_OID_TIPO_COMU = lnOidTipoComuTF) THEN
      lnOidComunTF := x.OID_CLIE_COMU;
    ELSIF(x.TICM_OID_TIPO_COMU = lnOidTipoComuTM) THEN
      lnOidComunTM := x.OID_CLIE_COMU;
    ELSIF(x.TICM_OID_TIPO_COMU = lnOidTipoComuML) THEN
      lnOidComunML := x.OID_CLIE_COMU;
    ELSE
      lnOidComunOtro := x.OID_CLIE_COMU;
    END IF;

  END LOOP;

  lnIndPrincipal := 1;

  --ACTUALIZAMOS EL TELEFONO FIJO
  IF((psTelefonoFijo IS NOT NULL) OR (psTelefonoFijo <> '')) THEN
    IF(lnOidComunTF IS NULL) THEN
      INSERT INTO MAE_CLIEN_COMUN
        (OID_CLIE_COMU,
         CLIE_OID_CLIE,
         TICM_OID_TIPO_COMU,
         VAL_DIA_COMU,
         VAL_TEXT_COMU,
         FEC_HORA_DESD,
         FEC_HORA_HAST,
         VAL_INTE_COMU,
         IND_COMU_PPAL
        )
     	VALUES
   	    (MAE_CLCO_SEQ.NEXTVAL,
   	     pnOidCliente,
   	     lnOidTipoComuTF,
   	     NULL,
         psTelefonoFijo,
         NULL,
         NULL,
         NULL,
         lnIndPrincipal
        );
    ELSE
      UPDATE MAE_CLIEN_COMUN
         SET VAL_TEXT_COMU = psTelefonoFijo,
             IND_COMU_PPAL = lnIndPrincipal,
             USU_MODI = psCodigoUsuario
       WHERE OID_CLIE_COMU = lnOidComunTF;

    END IF;

    IF(lnIndPrincipal = 1) THEN
      lnIndPrincipal := 0;
    END IF;
  ELSE
    IF(lnOidComunTF IS NOT NULL) THEN
      DELETE FROM MAE_CLIEN_COMUN
       WHERE OID_CLIE_COMU = lnOidComunTF;
    END IF;
  END IF;

  --ACTUALIZAMOS EL TELEFONO MOVIL
  IF((psTelefonoCelular IS NOT NULL) OR (psTelefonoCelular <> '')) THEN
    IF(lnOidComunTM IS NULL) THEN
      INSERT INTO MAE_CLIEN_COMUN
        (OID_CLIE_COMU,
         CLIE_OID_CLIE,
         TICM_OID_TIPO_COMU,
         VAL_DIA_COMU,
         VAL_TEXT_COMU,
         FEC_HORA_DESD,
         FEC_HORA_HAST,
         VAL_INTE_COMU,
         IND_COMU_PPAL
        )
     	VALUES
   	    (MAE_CLCO_SEQ.NEXTVAL,
   	     pnOidCliente,
   	     lnOidTipoComuTM,
   	     NULL,
         psTelefonoCelular,
         NULL,
         NULL,
         NULL,
         lnIndPrincipal
        );
    ELSE
      UPDATE MAE_CLIEN_COMUN
         SET VAL_TEXT_COMU = psTelefonoCelular,
             IND_COMU_PPAL = lnIndPrincipal,
             USU_MODI = psCodigoUsuario
       WHERE OID_CLIE_COMU = lnOidComunTM;

    END IF;

    IF(lnIndPrincipal = 1) THEN
      lnIndPrincipal := 0;
    END IF;
  ELSE
    IF(lnOidComunTM IS NOT NULL) THEN
      DELETE FROM MAE_CLIEN_COMUN
       WHERE OID_CLIE_COMU = lnOidComunTM;
    END IF;
  END IF;

  --ACTUALIZAMOS EL MAIL
  IF((psEmail IS NOT NULL) OR (psEmail <> '')) THEN
    IF(lnOidComunML IS NULL) THEN
      INSERT INTO MAE_CLIEN_COMUN
        (OID_CLIE_COMU,
         CLIE_OID_CLIE,
         TICM_OID_TIPO_COMU,
         VAL_DIA_COMU,
         VAL_TEXT_COMU,
         FEC_HORA_DESD,
         FEC_HORA_HAST,
         VAL_INTE_COMU,
         IND_COMU_PPAL
        )
     	VALUES
   	    (MAE_CLCO_SEQ.NEXTVAL,
   	     pnOidCliente,
   	     lnOidTipoComuML,
   	     NULL,
         psEmail,
         NULL,
         NULL,
         NULL,
         lnIndPrincipal
        );
    ELSE
      UPDATE MAE_CLIEN_COMUN
         SET VAL_TEXT_COMU = psEmail,
             IND_COMU_PPAL = lnIndPrincipal,
             USU_MODI = psCodigoUsuario
       WHERE OID_CLIE_COMU = lnOidComunML;

    END IF;

  ELSE
    IF(lnOidComunML IS NOT NULL) THEN
      DELETE FROM MAE_CLIEN_COMUN
       WHERE OID_CLIE_COMU = lnOidComunML;
    END IF;
  END IF;

  --En caso se actualizo uno de los 3 datos y tenga otras comunicaciones, se le actualiza
  --su indicador principal = 0
  IF(lnOidComunOtro IS NOT NULL) THEN
    UPDATE MAE_CLIEN_COMUN
       SET IND_COMU_PPAL = 0,
           USU_MODI = psCodigoUsuario
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND TICM_OID_TIPO_COMU NOT IN (lnOidTipoComuTF, lnOidTipoComuTM, lnOidTipoComuML);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(ln_sqlcode || '-' || sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR HIP_PR_ACTUA_DATOS_CLIEN: ' || ls_sqlerrm);

END HIP_PR_ACTUA_DATOS_CLIEN;
  
/***********************************************************************************
  Descripcion        : Devuelve Promedio de Venta de las ultimas 5 campañas de la Consultora
  Fecha Creacion     : 29/08/2013
  Parametros Entrada :
             psCodigoPais : Codigo de Pais
             psCodigoMarca: Codigo de Marca
             psCodigoCanal: Codigo de Canal
             psCodigoRegion: Codigo de Region
             psCodigoCliente : Codigo de Cliente
             psNumeroPedido : Numero de Pedidos

  Autor              : Aurelio Oviedo
***************************************************************************/
FUNCTION HIP_FN_OBTIE_PROME_VENTA_PEDID(
  psCodigoPais           VARCHAR2,
  psCodigoMarca          VARCHAR2,
  psCodigoCanal          VARCHAR2,
  psCodigoRegion         VARCHAR2,
  psCodigoCliente        VARCHAR2,
  psNumeroPedidos        NUMBER)
RETURN NUMBER
IS
  lnOidPais         SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca        SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal        SEG_CANAL.OID_CANA%TYPE;
  lnSaldo           NUMBER;
  lnNumeroCampanas  NUMBER;

  lsCodigoPeriodoFin       SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodigoPeriodoIni       SEG_PERIO_CORPO.COD_PERI%TYPE;

  v_oidperi PED_SOLIC_CABEC.Perd_Oid_Peri%TYPE;
  v_monto   PED_SOLIC_CABEC.Val_Tota_Paga_Loca%TYPE;

  lnValorPromedio   NUMBER;

lnOidSoliPais NUMBER;
lnOidPeriodo NUMBER;
lnOidPeriodoAnt NUMBER;
lsPeriodo VARCHAR2(6);
  BEGIN

  BEGIN
    
  lnValorPromedio:=0;
    lsPeriodo:=FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA ;
    lnOidSoliPais :=FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS ('C1');
    lnOidPeriodo:= FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lsPeriodo);
    lnOidPeriodoAnt := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( GEN_FN_CALCU_PERIO( lsPeriodo, -1*psNumeroPedidos));

--hay periodos que hay doble pedidos con la funcion dense_rank no se 
--discriminan y se toman el promedio de venta de lass ultimos n campañas aun
--no sean consecutivas
 select ROUND( sum(VAL_TOTA_PAGA_LOCA)/ count(1), 0)
      Into lnValorPromedio
     from(
      select   A.perd_oid_peri,
               sum(A.VAL_TOTA_PAGA_LOCA) VAL_TOTA_PAGA_LOCA,
         dense_rank() over (order by A.PERD_OID_PERI DESC ) rid
         from ped_solic_cabec a
        where A.TSPA_OID_TIPO_SOLI_PAIS = lnOidSoliPais
        and A.GRPR_OID_GRUP_PROC = 5
        and A.CLIE_OID_CLIE =  ( SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE = psCodigoCliente)
        and exists (select 1 from ped_solic_cabec where ind_oc=1 and soca_oid_soli_cabe=a.oid_soli_cabe)
        and a.val_tota_paga_loca > 0
        and A.PERD_OID_PERI < lnOidPeriodo
        and A.Perd_Oid_Peri >= lnOidPeriodoAnt
        group by a.perd_oid_peri
        )
     where rid <= psNumeroPedidos;
    
   /* select ROUND( sum(VAL_TOTA_PAGA_LOCA)/ count(*), 0)
    Into lnValorPromedio
    from
    (
    select rownum id,PED.VAL_TOTA_PAGA_LOCA 
    from
    (
        select  rownum , A.VAL_TOTA_PAGA_LOCA
         from ped_solic_cabec a
        where A.TSPA_OID_TIPO_SOLI_PAIS = FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS ('C1')
        and A.GRPR_OID_GRUP_PROC = 5
        and A.CLIE_OID_CLIE = ( SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE = psCodigoCliente)
        and a.val_tota_paga_loca > 0
        and A.PERD_OID_PERI <> FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO_ACTUA )
        order by A.PERD_OID_PERI DESC 
    ) PED
    )
    where id <= psNumeroPedidos;*/
    
    /*
    select ROUND( sum(A.VAL_TOTA_PAGA_LOCA)/ count(*), 0)
     Into lnValorPromedio
     from ped_solic_cabec a
    where A.TSPA_OID_TIPO_SOLI_PAIS = FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS ('C1')
    and A.GRPR_OID_GRUP_PROC = 5
    and A.CLIE_OID_CLIE = ( SELECT OID_CLIE FROM MAE_CLIEN WHERE COD_CLIE = psCodigoCliente)
    and a.val_tota_paga_loca > 0
    and rownum <= psNumeroPedidos
    order by A.PERD_OID_PERI DESC;
    */
   EXCEPTION
      WHEN OTHERS THEN
        lnValorPromedio := 0;   
  END;   

  RETURN lnValorPromedio;

/*EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_OBTIE_PROME_VENTA_PEDID: ' || ls_sqlerrm);*/
END HIP_FN_OBTIE_PROME_VENTA_PEDID;  
  


/***********************************************************************************
Descripcion        : Actualiza datos del Cliente
Fecha Creacion     : 25/10/2013
Parametros Entrada :
           psCodigoCliente:   Codigo de consultora
           psCodigoPais: 		 Codigo pais
           psCodigoRegion:   Codigo region

Autor              : Sebastian Guerra
***************************************************************************/
PROCEDURE HIP_PR_CONSU_CTACT_CAMPA(psCodigoCliente      VARCHAR2,
                                                                           psCodigoPais           VARCHAR2,
                                                                           psCodigoRegion       VARCHAR2)
IS 

CURSOR c_ctactecpa IS
SELECT 
    num_regi, 
    fec_emis, 
    cam_refe, 
    des_movi, 
    imp_carg, 
    imp_abon, 
    cod_movi,
    cam_calc,
    (case when imp_abon <> 0 then 1 else 2 end) ind_caab  
  FROM gtt_consu_cuent_corri_campa;

TYPE ctactecpa IS RECORD(
    num_regi    gtt_consu_cuent_corri_campa.num_regi%type,
    fec_emis    gtt_consu_cuent_corri_campa.fec_emis%type,
    cam_refe    gtt_consu_cuent_corri_campa.cam_refe%type,
    des_movi    gtt_consu_cuent_corri_campa.des_movi%type,
    imp_carg    gtt_consu_cuent_corri_campa.imp_carg%type,
    imp_abon    gtt_consu_cuent_corri_campa.imp_abon%type,
    cod_movi    gtt_consu_cuent_corri_campa.cod_movi%type,
    cam_calc    gtt_consu_cuent_corri_campa.cam_calc%type,
    ind_caab    gtt_consu_cuent_corri_campa.ind_caab%type
  );

TYPE ctactecpatab IS TABLE OF ctactecpa;
ctactecparecord ctactecpatab;

ls_periodo NUMBER;
lnIdPais NUMBER;
vnOidTipoSoliPais PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS%TYPE;

  BEGIN 

    lnIdPais    := gen_pkg_gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
    
     SELECT OID_TIPO_SOLI_PAIS
     INTO vnOidTipoSoliPais
     FROM PED_TIPO_SOLIC_PAIS
     WHERE PAIS_OID_PAIS = lnIdPais
     AND TSOL_OID_TIPO_SOLI = (SELECT OID_TIPO_SOLI
                               FROM PED_TIPO_SOLIC
                               WHERE COD_TIPO_SOLI = 'SOC');

    INSERT INTO gtt_consu_cuent_corri_campa(
        num_regi,
        fec_emis,
        cam_refe, 
        des_movi, 
        imp_carg,
        imp_abon,
        cod_movi
    )
    SELECT ROWNUM, TRUNC(fec_emis) fec_emis, NVL (TO_CHAR (cam_refe), ' ') cam_refe, des_movi,
           imp_carg, imp_abon, cod_movi
      FROM (SELECT   fec_emis, cam_refe, tip_movi, cod_movi, des_movi, oid_movi,
                     soca_oid_soli_cabe, num_docu, fec_venc, fec_pago,
                     SUM (imp_carg) imp_carg, SUM (imp_abon) imp_abon
                FROM ((SELECT mcc.fec_docu fec_emis, spc.cod_peri cam_refe,
                              CASE
                                 WHEN mcc.tcab_oid_tcab_crea = 2001
                                 AND mcc.imp_movi > 0
                                    THEN 'P'
                                 WHEN mcc.tcab_oid_tcab_crea = 2001
                                 AND mcc.imp_movi < 0
                                    THEN 'R'
                                 ELSE 'D'
                              END tip_movi,
                              CASE
                                 WHEN mcc.tcab_oid_tcab_crea = 2001
                                    THEN CASE
                                           WHEN EXISTS (
                                                  SELECT oid_soli_cabe
                                                    FROM ped_solic_cabec p,
                                                         ped_tipo_solic_pais tp,
                                                         ped_tipo_solic t
                                                   WHERE p.soca_oid_soli_cabe = mcc.soca_oid_soli_cabe
                                                     AND p.tspa_oid_tipo_soli_pais = tp.oid_tipo_soli_pais
                                                     AND tp.tsol_oid_tipo_soli = t.oid_tipo_soli
                                                     AND p.ind_oc = 1
                                                     AND t.ind_soli_nega = 0)
                                              THEN 'Pedido'
                                           ELSE CASE
                                           WHEN mcc.imp_movi > 0
                                              THEN DECODE
                                                     (mcc.soca_oid_soli_cabe,
                                                      NULL, 'Atención de Servicio',
                                                      rec_pkg_proce.rec_fn_solic_pedid_hiper
                                                           (mcc.soca_oid_soli_cabe)
                                                     )
                                           ELSE NVL
                                                  ((SELECT    'C'
                                                           || periocorpo.cod_peri
                                                           || ' '
                                                           || opera.val_desc_larg
                                                           || ' NRO.'
                                                           || cabecrecla.num_recl
                                                                       AS descrip
                                                      FROM ped_solic_cabec cons,
                                                           ped_solic_cabec solicrecla,
                                                           ped_solic_cabec solicorigen,
                                                           rec_solic_opera solicopera,
                                                           rec_opera_recla operarecla,
                                                           rec_cabec_recla cabecrecla,
                                                           rec_tipos_opera tiposopera,
                                                           rec_opera opera,
                                                           cra_perio perio,
                                                           seg_perio_corpo periocorpo
                                                     WHERE cons.oid_soli_cabe = mcc.soca_oid_soli_cabe
                                                       AND cons.oid_soli_cabe = solicrecla.soca_oid_soli_cabe
                                                       AND solicrecla.soca_oid_docu_refe IS NOT NULL
                                                       AND solicrecla.oid_soli_cabe = solicopera.soca_oid_soli_cabe
                                                       AND solicopera.opre_oid_oper_recl = operarecla.oid_oper_recl
                                                       AND operarecla.care_oid_cabe_recl = cabecrecla.oid_cabe_recl
                                                       AND operarecla.tiop_oid_tipo_oper = tiposopera.oid_tipo_oper
                                                       AND tiposopera.rope_oid_oper = opera.oid_oper
                                                       AND solicrecla.soca_oid_docu_refe = solicorigen.oid_soli_cabe
                                                       AND solicorigen.perd_oid_peri = perio.oid_peri
                                                       AND perio.peri_oid_peri = periocorpo.oid_peri
                                                       AND ROWNUM = 1),
                                                   DECODE
                                                      (mcc.soca_oid_soli_cabe,
                                                       NULL, 'CDR',
                                                       rec_pkg_proce.rec_fn_solic_pedid_hiper
                                                           (mcc.soca_oid_soli_cabe)
                                                      )
                                                  )
                                        END
                                        END
                                 ELSE CASE
                                 WHEN mcc.subp_oid_subp_crea = 203
                                    THEN (SELECT    DECODE
                                                          (flx.val_nume_orde_cuot,
                                                           2, 'Primera ',
                                                           3, 'Segunda '
                                                          )
                                                 || gen.val_i18n
                                                 || ' de '
                                                 || flx.cod_peri
                                            FROM flx_cuota_flexi_factu_detal flx
                                           WHERE flx.oid_movi_carg_flex =
                                                                   mcc.oid_movi_cc
                                             AND flx.oid_peri_cuot_flex =
                                                                 mcc.perd_oid_peri
                                             AND flx.oid_clie = mcc.clie_oid_clie)
                                 ELSE gen.val_i18n
                              END
                              END des_movi,
                              mcc.oid_movi_cc oid_movi,
                              mcc.soca_oid_soli_cabe soca_oid_soli_cabe,
                              mcc.num_iden_cuot num_docu, mcc.fec_venc fec_venc,
                              NULL fec_pago,
                              CASE
                                 WHEN (mcc.imp_movi > 0)
                                    THEN mcc.imp_movi
                                 ELSE NULL
                              END imp_carg,
                              CASE
                                 WHEN (mcc.imp_movi > 0)
                                    THEN NULL
                                 ELSE ABS (mcc.imp_movi)
                              END imp_abon,
                              TO_CHAR (gen.val_oid) cod_movi
                         FROM ccc_movim_cuent_corri mcc,
                              ccc_subpr cs,
                              ccc_tipo_abono_subpr tas,
                              ccc_tipo_cargo_abono tca,
                              gen_i18n_sicc_pais gen,
                              cra_perio cp,
                              seg_perio_corpo spc
                        WHERE mcc.subp_oid_subp_crea = cs.oid_subp
                          AND cs.oid_subp = tas.subp_oid_subp
                          AND tas.tcab_oid_tcab = tca.oid_tipo_carg_abon
                          AND gen.attr_enti LIKE 'CCC_TIPO_CARGO_ABONO'
                          AND gen.val_oid = tca.oid_tipo_carg_abon
                          AND mcc.perd_oid_peri = cp.oid_peri
                          AND cp.peri_oid_peri = spc.oid_peri
                          AND mcc.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigocliente))
                      UNION ALL
                      (SELECT mb.fec_proc fec_emis, NULL oid_peri, 'B' tip_movi,
                              ccb.des_cc des_movi, mb.oid_movi_banc oid_movi,
                              mb.oid_movi_banc soca_oid_soli_cabe,
                              mb.num_lote num_docu, NULL fec_venc,
                              mb.fec_pago fec_pago, NULL imp_carg,
                              mb.imp_pago imp_abon, ccb.cod_cc cod_movi
                         FROM ccc_movim_banca mb, ccc_cuent_corri_banca ccb
                        WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
                          AND mb.cod_iden_proc = 'P'
                          AND ccb.ind_banc_cheq = 0
                          AND mb.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigocliente))
                      UNION ALL
                      (SELECT mb.fec_proc fec_emis, NULL oid_peri, 'CH' tip_movi,
                              ccb.des_cc || ' ' || bc.des_banc des_movi,
                              mb.oid_movi_banc oid_movi,
                              mb.oid_movi_banc soca_oid_soli_cabe,
                              mb.num_lote num_docu, mb.fec_cobr_cheq fec_venc,
                              mb.fec_pago fec_pago, NULL imp_carg,
                              mb.imp_pago imp_abon, ccb.cod_cc cod_movi
                         FROM ccc_movim_banca mb,
                              ccc_cuent_corri_banca ccb,
                              ccc_banco_chequ bc
                        WHERE mb.ccba_oid_cc_banc = ccb.oid_cuen_corr_banc
                          AND mb.cod_iden_proc = 'P'
                          AND ccb.ind_banc_cheq = 1
                          AND mb.cod_banc_cheq = bc.cod_banc
                          AND mb.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigocliente)))
            GROUP BY fec_emis,
                     cam_refe,
                     tip_movi,
                     des_movi,
                     cod_movi,
                     oid_movi,
                     soca_oid_soli_cabe,
                     num_docu,
                     fec_venc,
                     fec_pago);
    
    
    OPEN c_ctactecpa;
    LOOP
    FETCH c_ctactecpa BULK COLLECT
    INTO ctactecparecord LIMIT w_filas;
    
        IF ctactecparecord.count > 0 THEN
            FOR x IN ctactecparecord.first .. ctactecparecord.last
            LOOP
            
                IF ctactecparecord(x).imp_carg IS NOT NULL THEN
                    -- Se es cargo, asume la campaña de referencia
                    update gtt_consu_cuent_corri_campa 
                    set cam_calc = ctactecparecord(x).cam_refe, ind_caab = ctactecparecord(x).ind_caab  
                    where num_regi = ctactecparecord(x).num_regi;
                ELSE     
                    --Otros Abonos, distintos a Abono Flexipago (205), Percepcion Abono Flexipago (206)
                    ls_periodo := null;
                     
                    IF(ctactecparecord(x).cod_movi <> '205' AND ctactecparecord(x).cod_movi <> '206' ) THEN 
                          BEGIN
                              --Identificar el pedido más cercano según fecha de emisión (anterior al abono) y asumir que el abono es de esa campaña  
                              SELECT to_char(gen_pkg_gener.gen_fn_devuelve_id_cra_perio3 (p.perd_oid_peri)) cod_peri
                              INTO ls_periodo
                              FROM (SELECT   perd_oid_peri
                                          FROM ped_solic_cabec
                                         WHERE clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente (pscodigocliente)
                                         AND tspa_oid_tipo_soli_pais = vnOidTipoSoliPais
                                         AND TRUNC (fec_fact) < ctactecparecord (x).fec_emis
                                      ORDER BY fec_fact DESC) p
                               WHERE ROWNUM = 1;
                          EXCEPTION
                          WHEN NO_DATA_FOUND THEN
                               ls_periodo :=NULL;
                          END;
          
                        IF ls_periodo IS NULL THEN
                            BEGIN
                                -- debe identificar el cargo por "Interés Flexipago" más cercano (anterior al abono) y asumir que el abono es de la misma campaña
                                SELECT p.cam_refe
                                INTO ls_periodo
                                FROM (SELECT   cam_refe
                                                FROM gtt_consu_cuent_corri_campa
                                                WHERE cod_movi = '204'  --Interes Flexipago (204), 
                                                AND TRUNC (fec_emis) < ctactecparecord (x).fec_emis
                                                AND imp_abon IS NULL
                                                ORDER BY fec_emis DESC) p
                                WHERE ROWNUM = 1;
                            EXCEPTION
                            WHEN NO_DATA_FOUND THEN
                                ls_periodo :=NULL;
                            END;
                           
                            IF ls_periodo IS NULL THEN
                                BEGIN
                                    SELECT cod_peri
                                    INTO ls_periodo
                                    FROM (SELECT   con.fec_cier, cor.cod_peri
                                                FROM fac_contr_cierr con,
                                                     fac_tipos_cierr tip,
                                                     cra_perio cra,
                                                     seg_perio_corpo cor,
                                                     seg_pais pai,
                                                     zon_regio reg
                                               WHERE con.tcie_oid_tipo_cier = tip.oid_tipo_cier
                                                 AND cra.oid_peri = con.perd_oid_peri
                                                 AND cor.oid_peri = cra.peri_oid_peri
                                                 AND pai.oid_pais = cra.pais_oid_pais
                                                 AND reg.oid_regi = con.zorg_oid_regi
                                                 AND tip.cod_tipo_cier = 'R'
                                                 AND pai.cod_pais = psCodigoPais
                                                 AND reg.cod_regi = psCodigoRegion
                                                 AND TRUNC (fec_cier) <= ctactecparecord (x).fec_emis
                                            ORDER BY fec_cier DESC) t
                                    WHERE ROWNUM = 1;
                                EXCEPTION
                                WHEN NO_DATA_FOUND THEN
                                    ls_periodo :=NULL;
                                END;
                            END IF;
                        END IF; 
        
                        update gtt_consu_cuent_corri_campa 
                        set cam_calc = ls_periodo, ind_caab = ctactecparecord(x).ind_caab 
                        where num_regi = ctactecparecord(x).num_regi;
            
                    ELSE
                        -- Abono Flexipago (205), Percepcion Abono Flexipago (206)
                        update gtt_consu_cuent_corri_campa 
                        set cam_calc = ctactecparecord(x).cam_refe, ind_caab = ctactecparecord(x).ind_caab 
                        where num_regi = ctactecparecord(x).num_regi;            
                    END IF;
        
                END IF;
      
          END LOOP;
    END IF;
    
    EXIT WHEN c_ctactecpa%NOTFOUND;
    END LOOP;
    CLOSE c_ctactecpa;
    

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(ln_sqlcode || '-' || sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_PR_CONSU_CTACT_CAMPA: ' || ls_sqlerrm);

END HIP_PR_CONSU_CTACT_CAMPA;

/***********************************************************************************
  Descripcion        : Valida si se Deshabilita Zona y Territorio para la pantalla de
                       Actualizar Datos de Direccion de HiperConsulta
  Fecha Creacion     : 12/03/2014
  Parametros Entrada :
             psCodigoPais : Codigo de Pais
             psCodigoMarca: Codigo de Marca
             psCodigoCanal: Codigo de Canal
             psCodigoCliente : Codigo de Cliente
             pnNumeroPeriodos : Numero de Campañas

  Autor              : Sergio Apaza
***************************************************************************/
FUNCTION HIP_FN_DESHA_ZONA_TERRI(
  psCodigoPais           VARCHAR2,
  psCodigoMarca          VARCHAR2,
  psCodigoCanal          VARCHAR2,
  psCodigoCliente        VARCHAR2,
  pnNumeroPeriodos       NUMBER)
RETURN VARCHAR2
IS
  ldFechaRegistro     MAE_DATOS_CLIEN_HISTO.FEC_REGI%TYPE;

  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca          SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal          SEG_CANAL.OID_CANA%TYPE;

  lsCodigoPeriodo     SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsPeriodoProceso    SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsIndDeshabilitar   VARCHAR2(1):='0';
   
BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  
  SELECT MAX(FEC_REGI) 
    INTO ldFechaRegistro
    FROM MAE_DATOS_CLIEN_HISTO
   WHERE COD_CLIE = psCodigoCliente
     AND (IND_CAMB_ZONA = 'Z' or IND_CAMB_ZONA = 'T');
     
  IF(ldFechaRegistro IS NOT NULL) THEN
  
    --Obtenemos el Periodo de Proceso
    SELECT COD_PERI
      INTO lsPeriodoProceso
      FROM BAS_CTRL_FACT
     WHERE COD_PAIS = psCodigoPais
       AND STA_CAMP = '0'
       AND IND_CAMP_ACT = '1';
  
    SELECT MAX(PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(cor.COD_PERI,
                                                  lnOidPais,
                                                  lnOidMarca,
                                                  lnOidCanal,
                                                  pnNumeroPeriodos))
      INTO lsCodigoPeriodo                                                                 
      FROM CRA_PERIO cra, SEG_PERIO_CORPO cor
     WHERE cra.FEC_INIC <= ldFechaRegistro
       AND cra.FEC_FINA >= ldFechaRegistro
       AND cra.PERI_OID_PERI = cor.OID_PERI;
  
    IF(lsCodigoPeriodo > lsPeriodoProceso) THEN
      lsIndDeshabilitar := '1';
    END IF;
  END IF;   
  
  RETURN lsIndDeshabilitar;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR HIP_FN_DESHA_ZONA_TERRI: ' || ls_sqlerrm);

END HIP_FN_DESHA_ZONA_TERRI;

END HIP_PKG_CONSU;
/
