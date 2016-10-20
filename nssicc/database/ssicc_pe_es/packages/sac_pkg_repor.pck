CREATE OR REPLACE PACKAGE SAC_PKG_REPOR is

  /* Declaracion de variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=3000;

  /* Indicadores */
  IND_COMPLETO CONSTANT VARCHAR2(2) := '01';
  IND_CONFORME CONSTANT VARCHAR2(2) := '02';
  IND_LINE_FILL_RATE CONSTANT VARCHAR2(2) := '03';
  IND_RECUPERACIONES CONSTANT VARCHAR2(2) := '04';
  IND_PEDIDOS_CON_SUSTITU CONSTANT VARCHAR2(2) := '05';
  IND_PEDIDOS_RECIBIDOS_OTROS_ME CONSTANT VARCHAR2(2) := '06';
  IND_PEDIDOS_RECHAZADOS_FINAL CONSTANT VARCHAR2(2) := '07';
  IND_CUMPLIMIENTO_CRONO_FACT CONSTANT VARCHAR2(2) := '08';
  IND_CAMBIOS_PEDIDOS CONSTANT VARCHAR2(2) := '09';
  IND_DEVOLUCIONES_PEDIDOS CONSTANT VARCHAR2(2) := '10';
  IND_FACTURADO_NO_RECIBIDO_PEDI CONSTANT VARCHAR2(2) := '11';
  IND_CAMBIOS_MONTO CONSTANT VARCHAR2(2) := '12';
  IND_DEVOLUCIONES_MONTO CONSTANT VARCHAR2(2) := '13';
  IND_FACTURADO_NO_RECIBI_MONTO CONSTANT VARCHAR2(2) := '14';

  /* Datos de Formulas */
  FOR_NUMERO_PEDIDOS_FNA CONSTANT VARCHAR2(2) := '01';
  FOR_TOTAL_PEDIDOS CONSTANT VARCHAR2(2) := '02';
  FOR_NUMERO_CONSU_PRESE_CAMBIOS CONSTANT VARCHAR2(2) := '03';
  FOR_NUMERO_CONSU_PRESE_FFNE CONSTANT VARCHAR2(2) := '04';
  FOR_LINE_FILL_RATE CONSTANT VARCHAR2(2) := '05';
  FOR_NUMERO_PEDIDOS_PROD_RECU CONSTANT VARCHAR2(2) := '06';
  FOR_PEDIDO_FALTANTE_CAMP_ANT CONSTANT VARCHAR2(2) := '07';
  FOR_PEDIDOS_CON_SUSTITUCIONES CONSTANT VARCHAR2(2) := '08';
  FOR_TOTAL_PEDIDOS_RECIBIDOS CONSTANT VARCHAR2(2) := '09';
  FOR_NUMERO_PEDIDOS_FACT_TIEMPO CONSTANT VARCHAR2(2) := '10';
  FOR_NUMERO_CONSU_PRESE_DEVO CONSTANT VARCHAR2(2) := '11';
  FOR_MONTO_VALOR_NETO_CAMB_RECI CONSTANT VARCHAR2(2) := '12';
  FOR_MONTO_VALOR_NETO_DEVO_RECI CONSTANT VARCHAR2(2) := '13';
  FOR_MONTO_VALOR_NETO_FFNE_RECL CONSTANT VARCHAR2(2) := '14';
  FOR_VENTA_NETA CONSTANT VARCHAR2(2) := '15';


PROCEDURE SAC_PR_CALCU_INDIC_PERIO(
  psCodigoPais           VARCHAR2,
  psCodigoPeriodo        VARCHAR2,
  psIndicadores          VARCHAR2,
  pnNumeroSecuencia      NUMBER);

FUNCTION SAC_FN_OBTIE_VALOR_DATOF(
  psCodigoPais            VARCHAR2,
  psCodigoPeriodo         VARCHAR2,
  psCodigoDatoFormula     VARCHAR2) RETURN NUMBER ;

FUNCTION SAC_FN_OBTIE_DESCR_DATOF(psCodigoDatoFormula     VARCHAR2) RETURN VARCHAR2;

PROCEDURE SAC_PR_CALCU_NUMER_PEDID_FNA(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_TOTAL_PEDID(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_CONSU_PRESE_CAMBI(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_CONSU_PRESE_FFNE(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_LINE_FILL_RATE(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_PEDID_PRODU_RECUP(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_PEDID_FALTA_CAMAN(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_PEDID_SUSTI(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_TOTAL_PEDID_RECIB(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_PEDID_FACTU_TIEMP(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_CONSU_PRESE_DEVOL(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_VNETO_CAMBI_RECIB(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_VNETO_DEVOL_RECIB(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_VNETO_FFNE_RECLA(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

PROCEDURE SAC_PR_CALCU_VENTA_NETA(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2);

/*************************************************************************
Descripcion : Genera la data para el Reporte de Fecha Real de Entrega
Fecha Creacion : 12/02/2013
Autor : Danny Amaro
 *************************************************************************/
PROCEDURE SAC_PR_GENER_REPOR_FECHA_ENTRE (
  psCodigoPais 	VARCHAR2,
  psCodigoPeriodo 	VARCHAR2,
  psFechaInicio 	VARCHAR2,
  psFechaFin		VARCHAR2);

/*************************************************************************
Descripcion : Retorna el tipo de origen.
Fecha Creacion : 03/08/2013
Autor : Yahir Rivas Luna
 *************************************************************************/
FUNCTION SAC_FN_DEVUE_TIPO_ORIGE(p_oid_soli_cabe PED_SOLIC_CABEC.Oid_Soli_Cabe%type) RETURN VARCHAR2;

/*************************************************************************
Descripcion : Retorna el tipo de origen en base al numero secuencial del documento.
Fecha Creacion : 17/09/2013
Autor : Sebastian Guerra
 *************************************************************************/
FUNCTION SAC_FN_DEVUE_TIPO_ORIGE_DOCUM(p_sec_nume_docu INT_SOLIC_CONSO_CABEC.sec_nume_docu%type) RETURN VARCHAR2;

/*************************************************************************
Descripcion : Genera la data para el Reporte de Facturacion Detalle SAC atraves
              de lo sleccionado(Algunas zonas y algunas regiones) y todos.
Fecha Creacion : 05/08/2013
Autor : Yahir Rivas Luna
 *************************************************************************/
PROCEDURE SAC_PR_GENER_REPOR_FACTU_DETA1(psPeriodoOid NUMBER,
                                          psFechaInicio   VARCHAR2,
                                          psFechaFin    VARCHAR2,
                                          psOrigen VARCHAR2);

/*************************************************************************
Descripcion : Genera la data para el Reporte de Facturacion Detalle SAC atraves
              de lo sleccionado(Solo Regiones).
Fecha Creacion : 05/08/2013
Autor : Yahir Rivas Luna
 *************************************************************************/
PROCEDURE SAC_PR_GENER_REPOR_FACTU_DETA2(psPeriodoOid NUMBER,
                                          psFechaInicio   VARCHAR2,
                                          psFechaFin    VARCHAR2,
                                          psOrigen VARCHAR2);

/***************************************************************************
Descripcion : Genera la data para el Reporte de Pedidos Recibidos por Origen.
Fecha Creacion : 05/08/2013
Autor : Gonzalo Javier Huertas Agurto
 ***************************************************************************/
PROCEDURE SAC_PR_REPOR_PEDID_REICB(psPeriodoOid VARCHAR2,
                                          psFechaInicio 	VARCHAR2,
                                          psFechaFin		VARCHAR2);

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAC Facturacion Detalle
                    (Algunas zonas y algunas regiones) y todos.
Fecha Creacion    : 16/01/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE SAC_PR_REPOR_FACTU_DETA(
    psCodigoPeriodo                     VARCHAR2,
    psAlmacen                           VARCHAR2,
    psOidPeriodo                        VARCHAR2,
    psFechaFacturacion                  VARCHAR2,
    psTipoPedido                        VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    ) ;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAC Recall Trazabilidad
Fecha Creacion    : 22/05/2014
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE SAC_PR_REPOR_RECAL_TRAZA(
    psCodigoPais                        VARCHAR2,
    psCodigoSAP                     VARCHAR2,
    psNumeroLote                     VARCHAR2,
    psFechaInicial                  VARCHAR2,
    psFechaFinal                  VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    ) ;

/***************************************************************************
Descripcion           : Genera archivo CSV correspondiente al Reporte SAC de Control Facturacion y Entrega de Pedido
Fecha Creacion    : 02/10/2014
Autor                   : Sebastian Guerra
***************************************************************************/
PROCEDURE SAC_PR_REPOR_CTRL_FACT_PEDI(
    psCodigoPais                     VARCHAR2,
    psCodigoPeriodoInicial      VARCHAR2,
    psCodigoPeriodoFinal        VARCHAR2,
    psCodigoSecuencia           VARCHAR2,
    psNombreArchivo              VARCHAR2,
    psTitulo                              VARCHAR2,
    psDirectorio                       OUT  VARCHAR2
    ) ;

/***************************************************************************
Descripcion       : Genera la data para el Reporte TIM (Bolivia)
Fecha Creacion    : 24/08/2015
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_REPOR_IMPOS_ADUAN(psCodigoPais 	  VARCHAR2,
                                   psCodigoUsuario  VARCHAR2,
												           psCodigoPeriodo 	VARCHAR2,
                                   psFechaInicio 	  VARCHAR2,
												           psFechaFin	    	VARCHAR2,
                                   psRegiones       VARCHAR2,
                                   psZonas          VARCHAR2);
                                   
/***************************************************************************
Descripcion       : Genera la data para el Reporte Consultoras Inexistentes
Fecha Creacion    : 28/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SAC_PR_REPOR_CODIG_INEXI(psCodigoPeriodoInicial 	VARCHAR2,
                                   psCodigoPeriodoFinal 	VARCHAR2,
                                   psFechaInicio 	  VARCHAR2,
												           psFechaFin	    	VARCHAR2,
                                   pscodigoRegion   VARCHAR2,
                                   pscodigoZona     VARCHAR2,
                                   pscodigoCliente  VARCHAR2,
                                   psEstado         VARCHAR2,
                                   psNroDocumento   VARCHAR2);                                 
                                   
END SAC_PKG_REPOR;
/
CREATE OR REPLACE PACKAGE BODY SAC_PKG_REPOR IS

/***************************************************************************
Descripcion       : Proceso que calcula los indicador seleccionados por el
                    usuario, de acuerdo a unas determinadas formulas, y son
                    calculadas para un determinado periodo

Fecha Creacion    : 24/02/2009
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_INDIC_PERIO
 (psCodigoPais           VARCHAR2,
  psCodigoPeriodo        VARCHAR2,
  psIndicadores          VARCHAR2,
  pnNumeroSecuencia      NUMBER)
IS
  lsCodigoIndicador      VARCHAR2(2);
  lsDescIndicador        SAC_INDIC.DES_INDI%TYPE;
  lsFormulaIndicador     SAC_INDIC.DES_FORM%TYPE;

  lnMontoAux             NUMBER(15,3);
  lnMontoAux2            NUMBER(15,3);
  lnMontoAux3            NUMBER(15,3);

  lnValorAux             NUMBER(15,3);
  lnInicio               NUMBER;
  lsIndicadores          VARCHAR2(200);

  lsDato1                SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
  lsDato2                SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
  lsDato3                SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
  lsDato4                SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
  lsDato5                SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;

  lsDatoAux1             SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
  lsDatoAux2             SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
  lsDatoAux3             SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
  lsDatoAux4             SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
  lsDatoAux5             SAC_TMP_INDIC_PERIO.DES_DAT1%TYPE;
BEGIN

  lnInicio := INSTR(psIndicadores, '(', 1, 1)+2;
  lsIndicadores := SUBSTR(psIndicadores, lnInicio);

  WHILE (LENGTH(lsIndicadores) > 0) LOOP
    lsCodigoIndicador := SUBSTR(lsIndicadores, 1, 2);

    --Seteamos Valores
    lsDato1 := '';
    lsDato2 := '';
    lsDato3 := '';
    lsDato4 := '';
    lsDato5 := '';
    lsDatoAux1 := '';
    lsDatoAux2 := '';
    lsDatoAux3 := '';
    lsDatoAux4 := '';
    lsDatoAux5 := '';

    --OBTENEMOS DATOS DEL INDICADOR
    SELECT DES_INDI, DES_FORM
    INTO   lsDescIndicador, lsFormulaIndicador
    FROM   SAC_INDIC
    WHERE  COD_INDI = lsCodigoIndicador;

    lsDescIndicador := lsDescIndicador || ' - C' || psCodigoPeriodo;

    --Calculamos el indicador: [%Completo] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_COMPLETO) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_NUMERO_PEDIDOS_FNA);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (1 - (lnMontoAux / lnMontoAux2));
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_NUMERO_PEDIDOS_FNA);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [%Conforme] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_CONFORME) THEN
      --lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_NUMERO_CONSU_PRESE_CAMBIOS);
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_PEDIDOS_CON_SUSTITUCIONES);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_NUMERO_CONSU_PRESE_FFNE);
      lnMontoAux3 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS);

      IF (lnMontoAux3 > 0 ) THEN
        lnValorAux := (1 - ((lnMontoAux + lnMontoAux2)/lnMontoAux3));
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_PEDIDOS_CON_SUSTITUCIONES);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_NUMERO_CONSU_PRESE_FFNE);
      lsDato3 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS);
      lsDato4 := 'Formula';
      lsDato5 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := TO_CHAR(lnMontoAux3);
      lsDatoAux4 := lsFormulaIndicador;
      lsDatoAux5 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Line Fill Rate (LFR)] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_LINE_FILL_RATE) THEN
      lnValorAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_LINE_FILL_RATE);

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_LINE_FILL_RATE);
      lsDato2 := 'Formula';
      lsDato3 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnValorAux, '0.000');
      lsDatoAux2 := lsFormulaIndicador;
      lsDatoAux3 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Recuperaciones] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_RECUPERACIONES) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_NUMERO_PEDIDOS_PROD_RECU);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_PEDIDO_FALTANTE_CAMP_ANT);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_NUMERO_PEDIDOS_PROD_RECU);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_PEDIDO_FALTANTE_CAMP_ANT);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Pedidos con Sustituciones] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_PEDIDOS_CON_SUSTITU) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_PEDIDOS_CON_SUSTITUCIONES);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_PEDIDOS_CON_SUSTITUCIONES);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Pedidos recibidos por otros medios] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_PEDIDOS_RECIBIDOS_OTROS_ME) THEN
      NULL;
    END IF;

    --Calculamos el indicador: [% Pedidos Rechazado Final] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_PEDIDOS_RECHAZADOS_FINAL) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS_RECIBIDOS);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (1 - (lnMontoAux / lnMontoAux2));
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS_RECIBIDOS);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Cumplimiento Cronog Fact] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_CUMPLIMIENTO_CRONO_FACT) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_NUMERO_PEDIDOS_FACT_TIEMPO);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_NUMERO_PEDIDOS_FACT_TIEMPO);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');

    END IF;

    --Calculamos el indicador: [% Cambios (Pedidos)] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_CAMBIOS_PEDIDOS) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_NUMERO_CONSU_PRESE_CAMBIOS);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_NUMERO_CONSU_PRESE_CAMBIOS);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Devoluciones (Pedidos)] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_DEVOLUCIONES_PEDIDOS) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_NUMERO_CONSU_PRESE_DEVO);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_NUMERO_CONSU_PRESE_DEVO);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Facturado no Recibido (Pedidos)] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_FACTURADO_NO_RECIBIDO_PEDI) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_NUMERO_CONSU_PRESE_FFNE);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_NUMERO_CONSU_PRESE_FFNE);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_TOTAL_PEDIDOS);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Cambios (Monto)] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_CAMBIOS_MONTO) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_MONTO_VALOR_NETO_CAMB_RECI);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_VENTA_NETA);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_MONTO_VALOR_NETO_CAMB_RECI);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_VENTA_NETA);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Devoluciones (Monto)] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_DEVOLUCIONES_MONTO) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_MONTO_VALOR_NETO_DEVO_RECI);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_VENTA_NETA);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_MONTO_VALOR_NETO_DEVO_RECI);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_VENTA_NETA);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Calculamos el indicador: [% Facturado no Recibido (Monto)] para el perido Seleccionado
    IF(lsCodigoIndicador = IND_FACTURADO_NO_RECIBI_MONTO) THEN
      lnMontoAux := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_MONTO_VALOR_NETO_FFNE_RECL);
      lnMontoAux2 := SAC_FN_OBTIE_VALOR_DATOF(psCodigoPais, psCodigoPeriodo, FOR_VENTA_NETA);

      IF (lnMontoAux2 > 0 ) THEN
        lnValorAux := (lnMontoAux / lnMontoAux2);
      ELSE
        lnValorAux := NULL;
      END IF;

      lsDato1 := SAC_FN_OBTIE_DESCR_DATOF(FOR_MONTO_VALOR_NETO_FFNE_RECL);
      lsDato2 := SAC_FN_OBTIE_DESCR_DATOF(FOR_VENTA_NETA);
      lsDato3 := 'Formula';
      lsDato4 := 'Resultado';

      lsDatoAux1 := TO_CHAR(lnMontoAux);
      lsDatoAux2 := TO_CHAR(lnMontoAux2);
      lsDatoAux3 := lsFormulaIndicador;
      lsDatoAux4 := TO_CHAR(lnValorAux, '0.000');
    END IF;

    --Insertamos los datos de la formula empleada
    INSERT INTO SAC_TMP_INDIC_PERIO
      (NUM_SECU, COD_PAIS, DES_INDI_PERI, DES_DAT1,
       DES_DAT2, DES_DAT3, DES_DAT4, DES_DAT5)
    VALUES
      (pnNumeroSecuencia, psCodigoPais, lsDescIndicador, lsDato1,
       lsDato2, lsDato3, lsDato4, lsDato5);

    --Insertamos los valores empleados en la formula
    INSERT INTO SAC_TMP_INDIC_PERIO
      (NUM_SECU, COD_PAIS, DES_INDI_PERI, DES_DAT1,
       DES_DAT2, DES_DAT3, DES_DAT4, DES_DAT5)
    VALUES
      (pnNumeroSecuencia, psCodigoPais, lsDescIndicador, lsDatoAux1,
       lsDatoAux2, lsDatoAux3, lsDatoAux4, lsDatoAux5);

    lnInicio := INSTR(lsIndicadores, ',', 1, 1);
    IF(lnInicio = 0) THEN
      lsIndicadores := '';
    ELSE
      lsIndicadores := SUBSTR(lsIndicadores, lnInicio + 2);
    END IF;
  END LOOP;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_CALCU_INDIC_PERIO: (' || ls_sqlerrm);
END SAC_PR_CALCU_INDIC_PERIO;

/***********************************************************************************
Descripcion        : Obtiene la descripcion del dato formula SAC

Fecha Creacion     : 25/02/2009
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION SAC_FN_OBTIE_DESCR_DATOF(psCodigoDatoFormula     VARCHAR2) RETURN VARCHAR2
IS
  lsDescripcion     SAC_DATOS_FORMU.DES_DATO_FORM%TYPE;
BEGIN
  SELECT DES_DATO_FORM
  INTO   lsDescripcion
  FROM   SAC_DATOS_FORMU
  WHERE  COD_DATO_FORM = psCodigoDatoFormula;

  RETURN lsDescripcion;
END SAC_FN_OBTIE_DESCR_DATOF;

/***********************************************************************************
Descripcion        : Valida si ya se ha calculado anteriormente
                     un dato formula SAC

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
FUNCTION SAC_FN_OBTIE_VALOR_DATOF(
  psCodigoPais            VARCHAR2,
  psCodigoPeriodo         VARCHAR2,
  psCodigoDatoFormula     VARCHAR2) RETURN NUMBER
IS
  lnValor             NUMBER;
  lnTotalOCurrencias  NUMBER;
BEGIN
  SELECT COUNT(*)
  INTO   lnTotalOCurrencias
  FROM   SAC_DATOS_FORMU_PERIO
  WHERE  COD_PAIS = psCodigoPais
    AND  COD_PERI = psCodigoPeriodo
    AND  COD_DATO_FORM = psCodigoDatoFormula;

  --Si no encontramos la formula para el periodo seleccionado, lo calculamos
  IF(lnTotalOCurrencias = 0) THEN
    IF(psCodigoDatoFormula = FOR_NUMERO_PEDIDOS_FNA) THEN
      SAC_PR_CALCU_NUMER_PEDID_FNA(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_TOTAL_PEDIDOS) THEN
      SAC_PR_CALCU_TOTAL_PEDID(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_NUMERO_CONSU_PRESE_CAMBIOS) THEN
      SAC_PR_CALCU_CONSU_PRESE_CAMBI(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_NUMERO_CONSU_PRESE_FFNE) THEN
      SAC_PR_CALCU_CONSU_PRESE_FFNE(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_LINE_FILL_RATE) THEN
      SAC_PR_CALCU_LINE_FILL_RATE(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_NUMERO_PEDIDOS_PROD_RECU) THEN
      SAC_PR_CALCU_PEDID_PRODU_RECUP(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_PEDIDO_FALTANTE_CAMP_ANT) THEN
      SAC_PR_CALCU_PEDID_FALTA_CAMAN(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_PEDIDOS_CON_SUSTITUCIONES) THEN
      SAC_PR_CALCU_PEDID_SUSTI(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_TOTAL_PEDIDOS_RECIBIDOS) THEN
      SAC_PR_CALCU_TOTAL_PEDID_RECIB(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_NUMERO_PEDIDOS_FACT_TIEMPO) THEN
      SAC_PR_CALCU_PEDID_FACTU_TIEMP(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_NUMERO_CONSU_PRESE_DEVO) THEN
      SAC_PR_CALCU_CONSU_PRESE_DEVOL(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_MONTO_VALOR_NETO_CAMB_RECI) THEN
      SAC_PR_CALCU_VNETO_CAMBI_RECIB(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_MONTO_VALOR_NETO_DEVO_RECI) THEN
      SAC_PR_CALCU_VNETO_DEVOL_RECIB(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_MONTO_VALOR_NETO_FFNE_RECL) THEN
      SAC_PR_CALCU_VNETO_FFNE_RECLA(psCodigoPais, psCodigoPeriodo);
    END IF;

    IF(psCodigoDatoFormula = FOR_VENTA_NETA) THEN
      SAC_PR_CALCU_VENTA_NETA(psCodigoPais, psCodigoPeriodo);
    END IF;

  END IF;

  --Recuperamos el valor de la formula
  SELECT VAL_MONT
  INTO   lnValor
  FROM   SAC_DATOS_FORMU_PERIO
  WHERE  COD_PAIS = psCodigoPais
    AND  COD_PERI = psCodigoPeriodo
    AND  COD_DATO_FORM = psCodigoDatoFormula;

  RETURN lnValor;
END SAC_FN_OBTIE_VALOR_DATOF;

/***********************************************************************************
Descripcion        : Calcula la formula del Numero de Pedidos con FNA

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_NUMER_PEDID_FNA(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT COUNT(DISTINCT a.oid_soli_cabe)
  INTO  lnTotal
  FROM PED_SOLIC_CABEC a,
       PED_SOLIC_POSIC b,
       CRA_PERIO c,
       SEG_PERIO_CORPO d,
       PRE_OFERT_DETAL f,
       PRE_TIPO_OFERT  g,
       MAE_PRODU       h,
       (SELECT OID_TIPO_SOLI_PAIS FROM PED_TIPO_SOLIC_PAIS
        WHERE TSOL_OID_TIPO_SOLI = (SELECT OID_TIPO_SOLI FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI='SOC')) g
  WHERE a.OID_SOLI_CABE=b.SOCA_OID_SOLI_CABE
    AND a.TSPA_OID_TIPO_SOLI_PAIS= g.oid_tipo_soli_pais
    AND d.COD_PERI = psCodigoPeriodo
    AND b.ESPO_OID_ESTA_POSI <> 2
    AND a.PERD_OID_PERI = c.OID_PERI
    AND c.PAIS_OID_PAIS = lnOidPais
    AND c.PERI_OID_PERI=d.OID_PERI AND b.NUM_UNID_DEMA_REAL-b.NUM_UNID_ATEN > 0
    AND b.OFDE_OID_DETA_OFER = f.OID_DETA_OFER
    and f.tofe_oid_tipo_ofer=g.oid_tipo_ofer
    and f.prod_oid_prod=h.oid_prod
    --and h.cod_sap not like '22%'
    --and h.cod_sap not like '40%'
    and g.cod_tipo_ofer not in ('21','23','16','77','57')
    --AND ((f.COD_ORIG =' MAV' AND f.IMP_PREC_CATA>0) OR f.COD_ORIG <> 'MAV')
    ;

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_NUMERO_PEDIDOS_FNA, lnTotal);

END SAC_PR_CALCU_NUMER_PEDID_FNA;

/***********************************************************************************
Descripcion        : Calcula la formula del Total de Pedidos

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_TOTAL_PEDID(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT COUNT(a.OID_SOLI_CABE)
  INTO  lnTotal
  FROM PED_SOLIC_CABEC a,
       CRA_PERIO c,
       SEG_PERIO_CORPO d,
       (SELECT OID_TIPO_SOLI_PAIS  FROM PED_TIPO_SOLIC_PAIS
         WHERE TSOL_OID_TIPO_SOLI=(SELECT OID_TIPO_SOLI FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI='SOC')) e,
         ped_solic_cabec f
  WHERE a.TSPA_OID_TIPO_SOLI_PAIS = e.oid_tipo_soli_pais
    AND d.COD_PERI = psCodigoPeriodo
    AND a.PERD_OID_PERI = c.OID_PERI
    AND c.PERI_OID_PERI = d.OID_PERI
    AND c.PAIS_OID_PAIS = lnOidPais
    and a.soca_oid_soli_cabe=f.oid_soli_cabe
    and f.num_unid_aten_tota>0
    ;

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS, lnTotal);

END SAC_PR_CALCU_TOTAL_PEDID;

/***********************************************************************************
Descripcion        : Calcula la formula de Numero consultoras que han presentado cambios

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_CONSU_PRESE_CAMBI(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
      SELECT COUNT (distinct d.clie_oid_clie)
   INTO  lnTotal
   FROM
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h
  WHERE c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND d.PERD_OID_PERI_RECL = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND f.COD_OPER IN ('CM', 'TM')
    and e.val_tipo_oper='01'
    and exists (
        select 1
        from rec_linea_opera_recla rlo, ped_solic_posic pos, ped_solic_Cabec psc
        where RLO.SOPO_OID_SOLI_POSI = POS.OID_SOLI_POSI
        and POS.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and  psc.modu_oid_modu <> 15
        and rlo.OPRE_OID_OPER_RECL = C.OID_OPER_RECL
    )
    ;


  /*SELECT COUNT (a.OID_SOLI_CABE)
   INTO  lnTotal
   FROM PED_SOLIC_CABEC a,
        REC_SOLIC_OPERA b,
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h
  WHERE a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
    AND b.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND d.PERD_OID_PERI_DOCU_REFE = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND g.PAIS_OID_PAIS = lnOidPais
    AND a.FEC_FACT IS NOT NULL
    AND f.COD_OPER IN ('CM', 'CP', 'TM', 'TP', 'MC', 'MT', 'PT', 'PC');
*/

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_NUMERO_CONSU_PRESE_CAMBIOS, lnTotal);

END SAC_PR_CALCU_CONSU_PRESE_CAMBI;

/***********************************************************************************
Descripcion        : Calcula la formula de Numero consultoras que han presentado FFNE

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_CONSU_PRESE_FFNE(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
      SELECT COUNT (distinct d.clie_oid_clie)
   INTO  lnTotal
   FROM
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h
  WHERE c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND d.PERD_OID_PERI_RECL = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND f.COD_OPER IN ('FM', 'FA')
    and e.val_tipo_oper='01'
    and exists (
        select 1
        from rec_linea_opera_recla rlo, ped_solic_posic pos, ped_solic_Cabec psc
        where RLO.SOPO_OID_SOLI_POSI = POS.OID_SOLI_POSI
        and POS.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and  psc.modu_oid_modu <> 15
        and rlo.OPRE_OID_OPER_RECL = C.OID_OPER_RECL
    )
    ;

/*
  SELECT COUNT (a.OID_SOLI_CABE)
   INTO  lnTotal
   FROM PED_SOLIC_CABEC a,
        REC_SOLIC_OPERA b,
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h
  WHERE a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
    AND b.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND d.PERD_OID_PERI_DOCU_REFE = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND g.PAIS_OID_PAIS = lnOidPais
    AND a.FEC_FACT IS NOT NULL
    AND f.COD_OPER IN ('FM', 'FA', 'MF');
*/

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_NUMERO_CONSU_PRESE_FFNE, lnTotal);

END SAC_PR_CALCU_CONSU_PRESE_FFNE;

/***********************************************************************************
Descripcion        : Calcula la formula de Line Fill Rate

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_LINE_FILL_RATE(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT (xx / yy)
   INTO lnTotal
  FROM (SELECT COUNT (b.oid_soli_posi) xx
          FROM ped_solic_cabec a,
               ped_solic_posic b,
               cra_perio c,
               seg_perio_corpo d,
               pre_ofert_detal f,
			   (SELECT oid_tipo_soli_pais FROM ped_tipo_solic_pais
                WHERE tsol_oid_tipo_soli = (SELECT oid_tipo_soli FROM ped_tipo_solic WHERE cod_tipo_soli = 'SOC')) g
         WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
           AND a.tspa_oid_tipo_soli_pais = g.oid_tipo_soli_pais
           AND d.cod_peri = psCodigoPeriodo
           AND a.perd_oid_peri = c.oid_peri
           AND c.peri_oid_peri = d.oid_peri
           AND c.PAIS_OID_PAIS = lnOidPais
           AND b.num_unid_dema_real - b.num_unid_aten > 0
           AND b.ofde_oid_deta_ofer = f.oid_deta_ofer
           AND ((f.cod_orig = 'MAV' AND f.imp_prec_cata > 0) OR f.cod_orig <> 'MAV') -- Usar solo si el campo Incluye MAV esta en 'NO'
       ) x,
       (SELECT COUNT (b.oid_soli_posi) yy
          FROM ped_solic_cabec a,
               ped_solic_posic b,
               cra_perio c,
               seg_perio_corpo d,
               pre_ofert_detal f,
               (SELECT oid_tipo_soli_pais FROM ped_tipo_solic_pais
                 WHERE tsol_oid_tipo_soli = (SELECT oid_tipo_soli FROM ped_tipo_solic WHERE cod_tipo_soli = 'SOC')) g
         WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
           AND a.tspa_oid_tipo_soli_pais = g.oid_tipo_soli_pais
           AND d.cod_peri = psCodigoPeriodo
           AND a.perd_oid_peri = c.oid_peri
           AND c.peri_oid_peri = d.oid_peri
           AND c.PAIS_OID_PAIS = lnOidPais
           AND b.ofde_oid_deta_ofer = f.oid_deta_ofer
           AND (   (f.cod_orig = 'MAV' AND f.imp_prec_cata > 0) OR f.cod_orig <> 'MAV') -- Usar solo si el campo Incluye MAV esta en 'NO'
       ) y;

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_LINE_FILL_RATE, lnTotal);

END SAC_PR_CALCU_LINE_FILL_RATE;

/***********************************************************************************
Descripcion        : Calcula el numero de Pedidos con productos recuperados

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_PEDID_PRODU_RECUP(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT COUNT (DISTINCT a.OID_SOLI_CABE)
   INTO lnTotal
   FROM PED_SOLIC_CABEC a,
        PED_SOLIC_POSIC b,
        CRA_PERIO c,
        SEG_PERIO_CORPO d,
        PRE_OFERT_DETAL f,
	     (SELECT OID_TIPO_SOLI_PAIS FROM PED_TIPO_SOLIC_PAIS
        WHERE TSOL_OID_TIPO_SOLI = (SELECT OID_TIPO_SOLI FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC')) g
  WHERE a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
    AND a.TSPA_OID_TIPO_SOLI_PAIS = g.OID_TIPO_SOLI_PAIS
    AND d.COD_PERI = psCodigoPeriodo
    AND b.ESPO_OID_ESTA_POSI <> 2
    AND a.PERD_OID_PERI = c.OID_PERI
    AND c.PERI_OID_PERI = d.OID_PERI
    AND c.PAIS_OID_PAIS = lnOidPais
    AND b.TPOS_OID_TIPO_POSI = 2
    AND b.OFDE_OID_DETA_OFER = f.OID_DETA_OFER
    AND ((f.COD_ORIG = 'MAV' AND f.IMP_PREC_CATA > 0) OR f.COD_ORIG <> 'MAV'); -- Usar solo si el campo Incluye MAV esta en 'NO'

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_NUMERO_PEDIDOS_PROD_RECU, lnTotal);

END SAC_PR_CALCU_PEDID_PRODU_RECUP;

/***********************************************************************************
Descripcion        : Calcula el numero de Pedidos con Faltante de Campa?a
                     Anterior

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_PEDID_FALTA_CAMAN(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lsCodigoPeriodoAnt  SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Obtenemos el codigo Periodo Anterior
  lsCodigoPeriodoAnt := GEN_FN_PERIO_ATRAS(psCodigoPeriodo, 1);

  --Calculamos la formula, que es la misma que Pedidos con FNA
  SELECT COUNT(DISTINCT a.OID_SOLI_CABE)
  INTO  lnTotal
  FROM PED_SOLIC_CABEC a,
       PED_SOLIC_POSIC b,
       CRA_PERIO c,
       SEG_PERIO_CORPO d,
       PRE_OFERT_DETAL f,
       (SELECT OID_TIPO_SOLI_PAIS FROM PED_TIPO_SOLIC_PAIS
        WHERE TSOL_OID_TIPO_SOLI = (SELECT OID_TIPO_SOLI FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI='SOC')) g
  WHERE a.OID_SOLI_CABE=b.SOCA_OID_SOLI_CABE
    AND a.TSPA_OID_TIPO_SOLI_PAIS= g.oid_tipo_soli_pais
    AND d.COD_PERI = lsCodigoPeriodoAnt
    AND b.ESPO_OID_ESTA_POSI <> 2
    AND a.PERD_OID_PERI = c.OID_PERI
    AND c.PAIS_OID_PAIS = lnOidPais
    AND c.PERI_OID_PERI=d.OID_PERI AND b.NUM_UNID_DEMA_REAL-b.NUM_UNID_ATEN > 0
    AND b.OFDE_OID_DETA_OFER = f.OID_DETA_OFER
    AND ((f.COD_ORIG =' MAV' AND f.IMP_PREC_CATA>0) OR f.COD_ORIG <> 'MAV');

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_PEDIDO_FALTANTE_CAMP_ANT, lnTotal);

END SAC_PR_CALCU_PEDID_FALTA_CAMAN;

/***********************************************************************************
Descripcion        : Calcula el numero de Pedidos con sustituciones

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_PEDID_SUSTI(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT COUNT (DISTINCT a.OID_SOLI_CABE)
  ---SELECT COUNT (DISTINCT a.clie_oid_clie )
   INTO lnTotal
   FROM PED_SOLIC_CABEC a,
        PED_SOLIC_POSIC b,
        CRA_PERIO c,
        SEG_PERIO_CORPO d,
        PRE_OFERT_DETAL f,
        (SELECT OID_TIPO_SOLI_PAIS FROM PED_TIPO_SOLIC_PAIS
          WHERE TSOL_OID_TIPO_SOLI = (SELECT OID_TIPO_SOLI FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC')) g,
        (SELECT OFDE_OID_DETA_OFER
           FROM PRE_MATRI_FACTU
          WHERE OID_MATR_FACT IN (SELECT MAFA_OID_COD_REEM FROM PRE_MATRI_REEMP)) mr,
        PRE_TIPO_OFERT h
  WHERE a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
    AND a.TSPA_OID_TIPO_SOLI_PAIS = g.OID_TIPO_SOLI_PAIS
    AND d.COD_PERI = psCodigoPeriodo
    AND a.PERD_OID_PERI = c.OID_PERI
    AND b.ESPO_OID_ESTA_POSI <> 2
    AND c.PERI_OID_PERI = d.OID_PERI
    AND c.PAIS_OID_PAIS = lnOidPais
    AND f.OID_DETA_OFER = mr.OFDE_OID_DETA_OFER
    AND b.OFDE_OID_DETA_OFER = f.OID_DETA_OFER
    and f.tofe_oid_tipo_ofer=h.oid_tipo_ofer
    and h.cod_tipo_ofer not in ('016','077','057')
    and H.COD_TIPO_OFER not in( select cod_tipo_ofer from pre_tipo_ofert where val_form_vent = 2);
    --AND ((f.COD_ORIG = 'MAV' AND f.IMP_PREC_CATA > 0) OR f.COD_ORIG <> 'MAV'); -- Usar solo si el campo Incluye MAV esta en 'NO'

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_PEDIDOS_CON_SUSTITUCIONES, lnTotal);

END SAC_PR_CALCU_PEDID_SUSTI;

/***********************************************************************************
Descripcion        : Calcula el total de Pedidos Recibidos

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_TOTAL_PEDID_RECIB(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnTotal             NUMBER(12,2);
BEGIN
  --Calculamos la formula
  SELECT COUNT (DISTINCT d.COD_CLIE)
    INTO lnTotal
    FROM PED_HISTO_SOLIC_CONSO_CABEC d -----, mae_clien b
   ----WHERE d.cod_clie = b.cod_clie
    WHERE d.COD_PAIS = psCodigoPais
     AND d.COD_PERI = psCodigoPeriodo;

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_TOTAL_PEDIDOS_RECIBIDOS, lnTotal);

END SAC_PR_CALCU_TOTAL_PEDID_RECIB;

/***********************************************************************************
Descripcion        : Calcula el numero de Pedidos facturados a Tiempo

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_PEDID_FACTU_TIEMP(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT COUNT (a.OID_SOLI_CABE)
    INTO lnTotal
   FROM PED_SOLIC_CABEC a,
        CRA_PERIO c,
        SEG_PERIO_CORPO d,
        CRA_CRONO e,
        CRA_ACTIV f,
        (SELECT OID_TIPO_SOLI_PAIS FROM PED_TIPO_SOLIC_PAIS
         WHERE TSOL_OID_TIPO_SOLI = (SELECT OID_TIPO_SOLI FROM PED_TIPO_SOLIC WHERE COD_TIPO_SOLI = 'SOC')) g
  WHERE a.TSPA_OID_TIPO_SOLI_PAIS = g.OID_TIPO_SOLI_PAIS
    AND d.COD_PERI = psCodigoPeriodo
    AND a.PERD_OID_PERI = c.OID_PERI
    AND e.ZZON_OID_ZONA = a.zzon_oid_zona
    AND e.perd_oid_peri = c.OID_PERI
    AND e.CACT_OID_ACTI = f.OID_ACTI
    AND f.PAIS_OID_PAIS = a.PAIS_OID_PAIS
    AND f.COD_ACTI = 'FA'
    AND c.PERI_OID_PERI = d.OID_PERI
    AND c.PAIS_OID_PAIS = lnOidPais
    AND a.FEC_FACT = e.FEC_INIC;

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_NUMERO_PEDIDOS_FACT_TIEMPO, lnTotal);

END SAC_PR_CALCU_PEDID_FACTU_TIEMP;

/***********************************************************************************
Descripcion        : Calcula el numero de Consultoras que han presentado Devoluciones

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_CONSU_PRESE_DEVOL(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
      SELECT COUNT (distinct d.clie_oid_clie)
   INTO  lnTotal
   FROM
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h
  WHERE c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND d.PERD_OID_PERI_RECL = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND f.COD_OPER IN ('DM', 'DN')
    and e.val_tipo_oper='01'
    and exists (
        select 1
        from rec_linea_opera_recla rlo, ped_solic_posic pos, ped_solic_Cabec psc
        where RLO.SOPO_OID_SOLI_POSI = POS.OID_SOLI_POSI
        and POS.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and  psc.modu_oid_modu <> 15
        and rlo.OPRE_OID_OPER_RECL = C.OID_OPER_RECL
    )
    ;

/*
  SELECT COUNT (a.OID_SOLI_CABE)
    INTO lnTotal
   FROM PED_SOLIC_CABEC a,
        REC_SOLIC_OPERA b,
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h
  WHERE a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
    AND b.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND d.PERD_OID_PERI_DOCU_REFE = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND g.PAIS_OID_PAIS = lnOidPais
    AND a.FEC_FACT IS NOT NULL
    AND f.COD_OPER IN ('DN', 'DE');
    */

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_NUMERO_CONSU_PRESE_DEVO, lnTotal);

END SAC_PR_CALCU_CONSU_PRESE_DEVOL;

/***********************************************************************************
Descripcion        : Calcula el monto en valor neto de Cambios Recibidos

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_VNETO_CAMBI_RECIB(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT NVL(SUM (i.IMP_ABON), 0)
    INTO lnTotal
   FROM PED_SOLIC_CABEC a,
        REC_SOLIC_OPERA b,
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h,
        REC_LINEA_OPERA_RECLA i
  WHERE a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
    AND b.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND i.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND d.PERD_OID_PERI_DOCU_REFE = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND g.PAIS_OID_PAIS = lnOidPais
    AND a.FEC_FACT IS NOT NULL
    AND f.COD_OPER IN ('CM', 'CP', 'TM', 'TP', 'MC', 'MT', 'PT', 'PC');

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_MONTO_VALOR_NETO_CAMB_RECI, lnTotal);

END SAC_PR_CALCU_VNETO_CAMBI_RECIB;

/***********************************************************************************
Descripcion        : Calcula el monto en valor neto de Devoluciones Recibidas

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_VNETO_DEVOL_RECIB(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT NVL(SUM (i.IMP_ABON), 0)
    INTO lnTotal
   FROM PED_SOLIC_CABEC a,
        REC_SOLIC_OPERA b,
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h,
        REC_LINEA_OPERA_RECLA i
  WHERE a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
    AND b.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND i.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND d.PERD_OID_PERI_DOCU_REFE = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND g.PAIS_OID_PAIS = lnOidPais
    AND a.FEC_FACT IS NOT NULL
    AND f.COD_OPER IN ('DN', 'DE');

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_MONTO_VALOR_NETO_DEVO_RECI, lnTotal);

END SAC_PR_CALCU_VNETO_DEVOL_RECIB;

/***********************************************************************************
Descripcion        : Calcula el monto en valor neto de FFNE Reclamadas

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_VNETO_FFNE_RECLA(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT NVL(SUM (i.IMP_ABON), 0)
    INTO lnTotal
   FROM PED_SOLIC_CABEC a,
        REC_SOLIC_OPERA b,
        REC_OPERA_RECLA c,
        REC_CABEC_RECLA d,
        REC_TIPOS_OPERA e,
        REC_OPERA f,
        CRA_PERIO g,
        SEG_PERIO_CORPO h,
        REC_LINEA_OPERA_RECLA i
  WHERE a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
    AND b.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND c.CARE_OID_CABE_RECL = d.OID_CABE_RECL
    AND c.TIOP_OID_TIPO_OPER = e.OID_TIPO_OPER
    AND e.ROPE_OID_OPER = f.OID_OPER
    AND i.OPRE_OID_OPER_RECL = c.OID_OPER_RECL
    AND d.PERD_OID_PERI_DOCU_REFE = g.OID_PERI
    AND g.PERI_OID_PERI = h.OID_PERI
    AND h.COD_PERI = psCodigoPeriodo
    AND g.PAIS_OID_PAIS = lnOidPais
    AND a.FEC_FACT IS NOT NULL
    AND f.COD_OPER IN ('FM', 'FA', 'MF');

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_MONTO_VALOR_NETO_FFNE_RECL, lnTotal);

END SAC_PR_CALCU_VNETO_FFNE_RECLA;

/***********************************************************************************
Descripcion        : Calcula el monto Valor Neta

Fecha Creacion     : 24/02/2009
Autor              : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_CALCU_VENTA_NETA(
  psCodigoPais        VARCHAR2,
  psCodigoPeriodo     VARCHAR2)
IS
  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnTotal             NUMBER(12,2);
BEGIN
  --Obtenemos el Oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  --Calculamos la formula
  SELECT NVL(SUM(VAL_PREC_NETO_UNIT_LOCA * DECODE (pto.IND_ESTA, 1, NUM_UNID_ATEN, 0)), 0)
    INTO lnTotal
    FROM PED_SOLIC_CABEC psc,
         PED_SOLIC_CABEC cons,
         PED_ESTAD_SOLIC pes,
         PED_TIPO_SOLIC_PAIS psp,
         PED_TIPO_SOLIC pts,
         PED_SOLIC_POSIC posic,
         PRE_OFERT_DETAL pod,
         PRE_TIPO_OFERT pto,
         CRA_PERIO cp,
         SEG_PERIO_CORPO spc
   WHERE psc.OID_SOLI_CABE = posic.SOCA_OID_SOLI_CABE
     AND psc.SOCA_OID_SOLI_CABE = cons.OID_SOLI_CABE
     AND posic.ESPO_OID_ESTA_POSI <> 2
     AND psc.IND_TS_NO_CONSO = 1
     AND psc.IND_PEDI_PRUE <> 1
     AND NVL (pes.COD_ESTA_SOLI, 1) <> 'AN'
     AND psc.IND_OC = 1
     AND pes.OID_ESTA_SOLI(+) = cons.ESSO_OID_ESTA_SOLI
     AND psc.TSPA_OID_TIPO_SOLI_PAIS = psp.OID_TIPO_SOLI_PAIS
     AND pts.OID_TIPO_SOLI = psp.TSOL_OID_TIPO_SOLI
     AND pts.IND_ANUL <> 1
     AND pts.IND_DEVO <> 1
     AND psc.FEC_FACT IS NOT NULL
     AND psc.PERD_OID_PERI = cp.OID_PERI
     AND cp.PERI_OID_PERI = spc.OID_PERI
     AND spc.COD_PERI = psCodigoPeriodo
     AND cp.PAIS_OID_PAIS = lnOidPais
     AND posic.OFDE_OID_DETA_OFER = pod.OID_DETA_OFER
     AND pod.TOFE_OID_TIPO_OFER = pto.OID_TIPO_OFER
     AND pts.COD_TIPO_SOLI = 'SOC';

  --Insertamos el monto calculado para el periodo seleccionado
  INSERT INTO SAC_DATOS_FORMU_PERIO
    VALUES (psCodigoPais, psCodigoPeriodo, FOR_VENTA_NETA, lnTotal);

END SAC_PR_CALCU_VENTA_NETA;

/*************************************************************************
Descripcion : Genera la data para el Reporte de Fecha Real de Entrega
Fecha Creacion : 12/02/2013
Autor : Danny Amaro
*************************************************************************/
PROCEDURE SAC_PR_GENER_REPOR_FECHA_ENTRE (psCodigoPais 	VARCHAR2,
												   psCodigoPeriodo 	VARCHAR2,
												   psFechaInicio 	VARCHAR2,
												   psFechaFin		VARCHAR2
												   )
	IS

		vnOidPais               SEG_PAIS.OID_PAIS%TYPE;
		vnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;

		lsCodigoIndActiv1      	VARCHAR2(2);
		lsCodigoIndActiv2      	VARCHAR2(2);
		lsCodigoIndActiv3      	VARCHAR2(2);

    BEGIN

        vnOidPais := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
       -- vnOidMarca := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
       -- vnOidCanal := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
        vnOidPeriodo := Gen_Pkg_Gener.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

		SELECT B.VAL_PARA
		INTO lsCodigoIndActiv1
		FROM BAS_PARAM_PAIS B
		WHERE B.COD_PAIS = psCodigoPais
          AND B.COD_SIST = 'SAC'
          AND B.COD_PARA = '001';

		SELECT B.VAL_PARA
		INTO lsCodigoIndActiv2
		FROM BAS_PARAM_PAIS B
		WHERE B.COD_PAIS = psCodigoPais
          AND B.COD_SIST = 'SAC'
          AND B.COD_PARA = '002';

		SELECT B.VAL_PARA
		INTO lsCodigoIndActiv3
		FROM BAS_PARAM_PAIS B
		WHERE B.COD_PAIS = psCodigoPais
          AND B.COD_SIST = 'SAC'
          AND B.COD_PARA = '003';

   EXECUTE IMMEDIATE 'TRUNCATE TABLE SAC_TEMPO_REPOR_FECHA_ENTRE';

    INSERT INTO SAC_TEMPO_REPOR_FECHA_ENTRE
			select f.cod_pais,
				   e.cod_peri,
				   c.cod_clie,
				   o.NUM_DOCU_IDEN,
				   c.val_nom1 || ' ' || c.val_nom2 || ' ' || c.val_ape1 || ' ' ||
				   c.val_ape2 NOMBRE,
				   h.cod_regi,
				   h.des_regi,
				   g.cod_zona,
				   k.fec_inic FEC_COMP,
				   p.fec_inic FEC_PROM,
				   i.fec_inic FEC_FACT_ZONA,
				   nvl((select fec_soli
						 from int_solic_conso_cabec
						where soca_oid_soli_cabe_refe = b.oid_soli_cabe),
					   (select fec_soli
						  from ped_histo_solic_conso_cabec
						 where soca_oid_soli_cabe_refe = b.oid_soli_cabe
						   and cod_clie = c.cod_clie
						   and cod_peri = psCodigoPeriodo)) FEC_RECE,
				   a.fec_fact FEC_FACT,
				   a.fec_fact FEC_ENTR
			  from ped_solic_cabec a,
				   ped_solic_cabec b,
				   mae_clien       c,
				   cra_perio       d,
				   seg_perio_corpo e,
				   seg_pais        f,
				   zon_zona        g,
				   zon_regio       h,
				   cra_crono       i,
				   cra_activ       j,
				   cra_crono       k,
				   cra_activ       l,
				   cra_crono       p,
				   cra_activ       q,
				   mae_clien_ident o
			 where a.oid_soli_cabe = b.soca_oid_soli_cabe
			   and b.clie_oid_clie = c.oid_clie
			   and b.ind_oc = 1
			   and a. perd_oid_peri = d.oid_peri
			   and c.oid_clie = o.clie_oid_clie
			   and o.VAL_IDEN_DOCU_PRIN = 1
			   and d.peri_oid_peri = e.oid_peri
			   and e.cod_peri = psCodigoPeriodo
			   and a.val_tota_paga_loca > 0
			   and a.pais_oid_pais = f.oid_pais
			   and a.zzon_oid_zona = g.oid_zona
			   and g.zorg_oid_regi = h.oid_regi
			   and g.oid_zona = i.zzon_oid_zona
			   and d.oid_peri = i.perd_oid_peri
			   and i.cact_oid_acti = j.oid_acti
			   and j.cod_acti = lsCodigoIndActiv1
			   and d.oid_peri = k.perd_oid_peri
			   and k.cact_oid_acti = l.oid_acti
			   and l.cod_acti = lsCodigoIndActiv2
			   and g.oid_zona = k.zzon_oid_zona
			   and d.oid_peri = p.perd_oid_peri
			   and p.cact_oid_acti = q.oid_acti
			   and q.cod_acti = lsCodigoIndActiv3
			   and g.oid_zona = p.zzon_oid_zona
			   and a.fec_fact >= TO_DATE(psFechaInicio,'dd/MM/yyyy')
			   and a.fec_fact <= TO_DATE(psFechaFin,'dd/MM/yyyy')
			 order by e.cod_peri, c.cod_clie;

    EXCEPTION
     WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_GENER_REPOR_FECHA_REAL_ENTRE: ' || ls_sqlerrm);

END SAC_PR_GENER_REPOR_FECHA_ENTRE;

/*************************************************************************
Descripcion : Retorna el tipo de origen.
Fecha Creacion : 03/08/2013
Autor : Yahir Rivas Luna
*************************************************************************/
FUNCTION SAC_FN_DEVUE_TIPO_ORIGE(p_oid_soli_cabe PED_SOLIC_CABEC.Oid_Soli_Cabe%type) RETURN VARCHAR2
IS
  v_oid_soli_cabe     VARCHAR2(20);
BEGIN
  select case
           when a2.ind_rece_ocr=1 and a2.ind_rece_web=0 and a2.ind_rece_digi=0 and a2.ind_rece_dd=0 then 'OCR'
           when a2.ind_rece_ocr=0 and a2.ind_rece_web=1 and a2.ind_rece_digi=0 and a2.ind_rece_dd=0 then 'WEB'
           when a2.ind_rece_ocr=0 and a2.ind_rece_web=0 and a2.ind_rece_digi=1 and a2.ind_rece_dd=0 then 'DIGITADO'
           when a2.ind_rece_ocr=0 and a2.ind_rece_web=0 and a2.ind_rece_digi=0 and a2.ind_rece_dd=1 then 'DD'
           else 'MIXTO'
           end
   INTO v_oid_soli_cabe
   from ped_solic_cabec a1, int_solic_conso_cabec a2
   where a1.oid_soli_cabe = p_oid_soli_cabe
       and a1.oid_soli_cabe=a2.soca_oid_soli_cabe_refe
       and ROWNUM=1;
  RETURN v_oid_soli_cabe;
END SAC_FN_DEVUE_TIPO_ORIGE;

/*************************************************************************
Descripcion : Retorna el tipo de origen en base al numero secuencial del documento.
Fecha Creacion : 17/09/2013
Autor : Sebastian Guerra
*************************************************************************/
FUNCTION SAC_FN_DEVUE_TIPO_ORIGE_DOCUM(p_sec_nume_docu INT_SOLIC_CONSO_CABEC.sec_nume_docu%type) RETURN VARCHAR2
IS
  v_sec_nume_docu     VARCHAR2(20);
BEGIN
  select case
           when a2.ind_rece_ocr=1 and a2.ind_rece_web=0 and a2.ind_rece_digi=0 and a2.ind_rece_dd=0 then 'OCR'
           when a2.ind_rece_ocr=0 and a2.ind_rece_web=1 and a2.ind_rece_digi=0 and a2.ind_rece_dd=0 then 'WEB'
           when a2.ind_rece_ocr=0 and a2.ind_rece_web=0 and a2.ind_rece_digi=1 and a2.ind_rece_dd=0 then 'DIGITADO'
           when a2.ind_rece_ocr=0 and a2.ind_rece_web=0 and a2.ind_rece_digi=0 and a2.ind_rece_dd=1 then 'DD'
           else 'MIXTO'
           end
   INTO v_sec_nume_docu
   from  int_solic_conso_cabec a2
   where a2.sec_nume_docu = p_sec_nume_docu;
  RETURN v_sec_nume_docu;
END SAC_FN_DEVUE_TIPO_ORIGE_DOCUM;

/*************************************************************************
Descripcion : Genera la data para el Reporte de Pedidos Recibidos por Origen.
Fecha Creacion : 05/08/2013
Autor : Gonzalo Javier Huertas Agurto
 *************************************************************************/
PROCEDURE SAC_PR_REPOR_PEDID_REICB(psPeriodoOid VARCHAR2,
                                          psFechaInicio 	VARCHAR2,
                                          psFechaFin		VARCHAR2)

IS

CURSOR c_report_pedid_recib is

select cod_pais,cod_peri, fec_soli, origen, count(1) as Ccount from
(
      select cod_pais,cod_peri, fec_soli
      , case
      when ind_rece_ocr=1 and ind_rece_dd=0 and ind_rece_mens=0 and ind_rece_web=0 and ind_rece_digi=0 then 'OCR'
      when ind_rece_ocr=0 and ind_rece_dd=1 and ind_rece_mens=0 and ind_rece_web=0 and ind_rece_digi=0 then 'DD'
      when ind_rece_ocr=0 and ind_rece_dd=0 and ind_rece_mens=1 and ind_rece_web=0 and ind_rece_digi=0 then 'IVR'
      when ind_rece_ocr=0 and ind_rece_dd=0 and ind_rece_mens=0 and ind_rece_web=1 and ind_rece_digi=0 then 'WEB'
      when ind_rece_ocr=0 and ind_rece_dd=0 and ind_rece_mens=0 and ind_rece_web=0 and ind_rece_digi=1 then 'DIGITADO'
      else 'MIXTO'
      end ORIGEN
      from ped_histo_solic_conso_cabec where cod_peri = psPeriodoOid
      and fec_soli>= to_date(psFechaInicio,'dd/mm/yyyy') and fec_soli<=to_date(psFechaFin,'dd/mm/yyyy')
      union all
      select cod_pais,cod_peri, fec_soli
      , case
      when ind_rece_ocr=1 and ind_rece_dd=0 and ind_rece_mens=0 and ind_rece_web=0 and ind_rece_digi=0 then 'OCR'
      when ind_rece_ocr=0 and ind_rece_dd=1 and ind_rece_mens=0 and ind_rece_web=0 and ind_rece_digi=0 then 'DD'
      when ind_rece_ocr=0 and ind_rece_dd=0 and ind_rece_mens=1 and ind_rece_web=0 and ind_rece_digi=0 then 'IVR'
      when ind_rece_ocr=0 and ind_rece_dd=0 and ind_rece_mens=0 and ind_rece_web=1 and ind_rece_digi=0 then 'WEB'
      when ind_rece_ocr=0 and ind_rece_dd=0 and ind_rece_mens=0 and ind_rece_web=0 and ind_rece_digi=1 then 'DIGITADO'
      else 'MIXTO'
      end ORIGEN
      from int_solic_conso_cabec where cod_peri=psPeriodoOid
      and fec_soli>=to_date(psFechaInicio,'dd/mm/yyyy') and fec_soli<=to_date(psFechaFin,'dd/mm/yyyy')
      )
group by cod_pais,cod_peri, fec_soli, origen;
--order by cod_pais,cod_peri, fec_soli, origenR

--FIN SELEC

  --declaracion de variables
  TYPE codPais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
  TYPE codPeri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
  TYPE fecSoli IS TABLE OF int_solic_conso_cabec. fec_soli%TYPE;
  TYPE origen_des IS TABLE OF SAC_REPOR_PEDID_RECIB.NOM_ORIG%TYPE;
  TYPE Ccount_des IS TABLE OF SAC_REPOR_PEDID_RECIB.NUM_CONT%TYPE;

  v_codPais codPais;
  v_codPeri codPeri;
  v_fecSoli fecSoli;
  v_origen_des origen_des;
  v_ccount_des Ccount_des;

  filas NATURAL := 1000;
 ----
BEGIN

  DELETE FROM SAC_REPOR_PEDID_RECIB; -- eliminamos la data de la tabla temporal

  OPEN c_report_pedid_recib();
     LOOP
       FETCH c_report_pedid_recib BULK COLLECT INTO v_codPais,
                                             v_codPeri,
                                             v_fecSoli,
                                             v_origen_des,
                                             v_ccount_des LIMIT filas;


       FORALL i IN 1..v_codPais.COUNT
       INSERT INTO SAC_REPOR_PEDID_RECIB(COD_PAIS,COD_PERI,FEC_SOLI,NOM_ORIG,
                   NUM_CONT)
                                      VALUES(v_codPais(i),
                                             v_codPeri(i),
                                             v_fecSoli(i),
                                             v_origen_des(i),
                                             v_ccount_des(i));

       EXIT WHEN c_report_pedid_recib%NOTFOUND;

       END LOOP;

   CLOSE c_report_pedid_recib;
   EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_REPOR_PEDID_REICB: '||ls_sqlerrm);


END SAC_PR_REPOR_PEDID_REICB;

/*************************************************************************
Descripcion : Genera la data para el Reporte de Facturacion Detalle SAC atraves
              de lo sleccionado(Algunas zonas y algunas regiones) y todos.
Fecha Creacion : 05/08/2013
Autor : Yahir Rivas Luna
 *************************************************************************/

PROCEDURE SAC_PR_GENER_REPOR_FACTU_DETA1(psPeriodoOid NUMBER,
                                          psFechaInicio   VARCHAR2,
                                          psFechaFin    VARCHAR2,
                                          psOrigen VARCHAR2)
IS
  CURSOR c_factu_detal1 IS
  SELECT J.VAL_NOMB_PERI CAMPANIA,
       nvl((select fec_soli from int_solic_conso_cabec where soca_oid_soli_cabe_refe=a.oid_soli_cabe),
       (select fec_soli from ped_histo_solic_conso_cabec where soca_oid_soli_cabe_refe=a.oid_soli_cabe and perd_oid_peri=a.perd_oid_peri and cod_clie=b.cod_clie))
        FECHA_CARGA
        ,a.fec_fact,
       G.DES_REGI REGION,
       F.DES_ZONA ZONA,
       nvl(H.DES_SECCI, h.cod_secc) des_secci,
       E.COD_TERR TERRITORIO,
       B.COD_CLIE CODIGO_CLIENTE,
      -- A.FEC_FACT,
       (select VAL_NUME_SOLI from ped_solic_cabec where oid_soli_cabe=a.soca_oid_soli_cabe) BOLETA_DESPACHO,
       IDE.NUM_DOCU_IDEN DOCUMENTO_IDENTIDAD,
       TRIM(B.VAL_APE1) || ' ' || TRIM(B.VAL_APE2) || ', ' || TRIM(B.VAL_NOM1) || ' ' ||
       TRIM(B.VAL_NOM2) NOMBRE,
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(B.OID_CLIE,'NUM_TELE') TELEFONO,
       GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_INGRE(B.OID_CLIE) CAMPANIA_INGRESO,
       A.VAL_PREC_CATA_TOTA_LOCA TOTAL_CATALOGO,
       A.VAL_PREC_NETO_TOTA_LOCA TOTAL_NETO,
       A.VAL_PREC_NETO_TOTA_LOCA - VAL_IMPO_FLET_SIN_IMPU_TOTA TOTAL_NETO_SIN_FLETE,
       ROUND((select (VAL_TOTA_PAGA_LOCA + (VAL_TOTA_PAGA_LOCA - VAL_IMPO_FLET_TOTA_LOCA) * GEN_PKG_GENER.GEN_FN_DEVUE_TASA_IMPUE_PAIS(B.PAIS_OID_PAIS) / 100) from ped_solic_cabec where oid_soli_cabe=a.soca_oid_soli_cabe),
             2) TOTAL_FACTURADO,
           b.sal_deud_ante SALDO,
       (SELECT DIREC.VAL_NOMB_VIA || ' ' || DIREC.NUM_PPAL || ' ' || DIREC.VAL_OBSE || ' ' ||
               (SELECT DES_GEOG
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = (SELECT ORDE_1
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_2 IS NULL
                   AND ORDE_3 IS NULL
                   AND ORDE_4 IS NULL) || ' ' ||
               (SELECT DES_GEOG
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = (SELECT ORDE_1
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_2 = (SELECT ORDE_2
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_3 IS NULL
                   AND ORDE_4 IS NULL) || ' ' ||
               (SELECT DES_GEOG
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = (SELECT ORDE_1
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_2 = (SELECT ORDE_2
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_3 = (SELECT ORDE_3
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_4 IS NULL) || ' ' ||
               (SELECT DES_GEOG
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = (SELECT ORDE_1
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_2 = (SELECT ORDE_2
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_3 = (SELECT ORDE_3
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_4 = (SELECT ORDE_4
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP))
          FROM MAE_CLIEN_DIREC DIREC,
               ZON_TERRI       D
         WHERE OID_CLIE_DIRE = A.CLDI_OID_CLIE_DIRE
           AND DIREC.TERR_OID_TERR = D.OID_TERR) DIRECCION,
           a.val_tota_gast_admi GASTOS_ADMINISTRATIVOS,
               spc.cod_peri PRIMER_PEDIDO,
          decode(a.inre_oid_indi_revi, 2, '1','') AS IND_CHEQ,
          (SELECT cod_resu_cheq
                FROM rec_resul_chequ rrc
                WHERE a.recq_oid_resu_cheq = rrc.oid_resu_cheq) EST_CHEQ,
           nvl(sac_pkg_repor.SAC_FN_DEVUE_TIPO_ORIGE(a.oid_soli_cabe),'MIXTO') ORIGEN,
(select x.ind_comp from ped_segui_pedid x, ped_solic_cabec y where x.soca_oid_soli_cabe=y.oid_soli_cabe
And y.oid_soli_cabe=a.soca_oid_soli_cabe
) REMESA,
(select num_unid_aten_tota from ped_solic_cabec where oid_soli_cabe=a.soca_oid_soli_cabe) UNIDADES_ATENDIDAS
  FROM PED_SOLIC_CABEC       A,
       MAE_CLIEN             B,
       MAE_CLIEN_IDENT       IDE,
       ZON_TERRI_ADMIN       D,
       ZON_TERRI             E,
       ZON_SECCI             H,
       ZON_ZONA              F,
       ZON_REGIO             G,
       PED_TIPO_SOLIC_PAIS   H,
       CRA_PERIO             J,
       ped_tipo_solic pts,
       mae_clien_prime_conta mcpc,
       cra_perio cp,
       seg_perio_corpo spc
WHERE A.CLIE_OID_CLIE = B.OID_CLIE
and a.clie_oid_clie=mcpc.clie_oid_clie
and mcpc.perd_oid_peri=cp.oid_peri
and cp.peri_oid_peri=spc.oid_peri
   AND A.ALMC_OID_ALMA = DECODE('','',A.ALMC_OID_ALMA, '')
   AND IDE.CLIE_OID_CLIE = a.CLIE_OID_CLIE
   AND IDE.VAL_IDEN_DOCU_PRIN = 1
   AND a.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
   AND D.TERR_OID_TERR = E.OID_TERR
   AND D.ZSCC_OID_SECC = H.OID_SECC
   AND H.ZZON_OID_ZONA = F.OID_ZONA
   AND F.ZORG_OID_REGI = G.OID_REGI
   AND A.TSPA_OID_TIPO_SOLI_PAIS = H.OID_TIPO_SOLI_PAIS
   and H.tsol_oid_tipo_soli=pts.oid_tipo_soli
   and pts.cod_tipo_soli='SOC'
   AND A.FEC_FACT >= TO_DATE(psFechaInicio,'dd/mm/yyyy') --fecha inicio
   AND A.FEC_FACT <= TO_DATE(psFechaFin,'dd/mm/yyyy')--fecha fin
   AND A.PERD_OID_PERI = J.OID_PERI
   AND A.PERD_OID_PERI = psPeriodoOid--CAMPA?A
   AND (psOrigen is null OR sac_pkg_repor.SAC_FN_DEVUE_TIPO_ORIGE(a.oid_soli_cabe) = psOrigen) --AGREGADO
   AND (((select case when count(cod_zona) = 0 then null
         else 1 end from SAC_GTT_REPOR_FACTU_DETAL where cod_zona is not null )
        is null or F.Cod_Zona in(select cod_zona from SAC_GTT_REPOR_FACTU_DETAL where cod_zona is not null))
   OR G.Cod_Regi in(select cod_regi from SAC_GTT_REPOR_FACTU_DETAL where cod_zona is null and val_rpat is null));

  -- Declarando variables

    TYPE campania IS TABLE OF SAC_REPOR_FACTU_DETAL.PER_CAMP%TYPE;
    TYPE fec_carg IS TABLE OF SAC_REPOR_FACTU_DETAL.FEC_CARG%TYPE;
    TYPE fec_fact IS TABLE OF SAC_REPOR_FACTU_DETAL.FEC_FACT%TYPE;
    TYPE region IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_REGI%TYPE;
    TYPE zona IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_ZONA%TYPE;
    TYPE seccion IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_SECC%TYPE;
    TYPE num_terr IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_TERR%TYPE;
    TYPE cod_cli IS TABLE OF SAC_REPOR_FACTU_DETAL.COD_CLIE%TYPE;
    TYPE num_bole IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_BOLE_DESP%TYPE;
    TYPE num_doc IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_DOCU_IDEN%TYPE;
    TYPE des_nom IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_NOMB%TYPE;
    TYPE des_tele IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_TELE%TYPE;
    TYPE camp_ingr IS TABLE OF SAC_REPOR_FACTU_DETAL.PER_CAMP_INGR%TYPE;
    TYPE val_tota_cata IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_TOTA_CATA%TYPE;
    TYPE val_tota_neto IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_TOTA_NETO%TYPE;
    TYPE val_neto_sin_flete IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_NETO_SINN_FLET%TYPE;
    TYPE val_tota_fact IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_TOTA_FACT%TYPE;
    TYPE val_sald IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_SALD%TYPE;
    TYPE des_dire IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_DIRE%TYPE;
    TYPE val_gast_admin IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_GAST_ADMI%TYPE;
    TYPE num_prim_pedi IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_PRIM_PEDI%TYPE;
    TYPE ind_cheq IS TABLE OF SAC_REPOR_FACTU_DETAL.IND_CHEQ%TYPE;
    TYPE est_cheq IS TABLE OF SAC_REPOR_FACTU_DETAL.EST_CHEQ%TYPE;
    TYPE des_orig IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_ORIG%TYPE;
    TYPE num_reme IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_REME%TYPE;
    TYPE uni_aten IS TABLE OF SAC_REPOR_FACTU_DETAL.UNI_ATEN%TYPE;

    v_campania campania;
    v_fec_carg fec_carg;
    v_fec_fact fec_fact;
    v_region region;
    v_zona zona;
    v_seccion seccion;
    v_num_terr num_terr;
    v_cod_cli cod_cli;
    v_num_bole num_bole;
    v_num_doc num_doc;
    v_des_nom des_nom;
    v_des_tele des_tele;
    v_camp_ingr camp_ingr;
    v_val_tota_cata val_tota_cata;
    v_val_tota_neto val_tota_neto;
    v_val_neto_sin_flete val_neto_sin_flete;
    v_val_tota_fact val_tota_fact;
    v_val_sald val_sald;
    v_des_dire des_dire;
    v_val_gast_admin val_gast_admin;
    v_num_prim_pedi num_prim_pedi;
    v_ind_cheq ind_cheq;
    v_est_cheq est_cheq;
    v_des_orig des_orig;
    v_num_reme num_reme;
    v_uni_aten uni_aten;


    filas NATURAL := 5000;

BEGIN

   delete from SAC_REPOR_FACTU_DETAL;-- eliminamos la data de la tabla temporal

   OPEN c_factu_detal1;
   LOOP
       FETCH c_factu_detal1 BULK COLLECT INTO v_campania,
                                              v_fec_carg,
                                              v_fec_fact,
                                              v_region,
                                              v_zona,
                                              v_seccion,
                                              v_num_terr,
                                              v_cod_cli,
                                              v_num_bole,
                                              v_num_doc,
                                              v_des_nom,
                                              v_des_tele,
                                              v_camp_ingr,
                                              v_val_tota_cata,
                                              v_val_tota_neto,
                                              v_val_neto_sin_flete,
                                              v_val_tota_fact,
                                              v_val_sald,
                                              v_des_dire,
                                              v_val_gast_admin,
                                              v_num_prim_pedi,
                                              v_ind_cheq,
                                              v_est_cheq,
                                              v_des_orig,
                                              v_num_reme,
                                              v_uni_aten LIMIT filas;

       FORALL i IN 1..v_campania.COUNT
       INSERT INTO SAC_REPOR_FACTU_DETAL(PER_CAMP,FEC_CARG,FEC_FACT,DES_REGI,DES_ZONA,
                                          DES_SECC,NUM_TERR,COD_CLIE,NUM_BOLE_DESP,NUM_DOCU_IDEN,DES_NOMB,
                                          DES_TELE,PER_CAMP_INGR,VAL_TOTA_CATA,VAL_TOTA_NETO,VAL_NETO_SINN_FLET,
                                          VAL_TOTA_FACT,VAL_SALD,DES_DIRE,VAL_GAST_ADMI,NUM_PRIM_PEDI,
                                          IND_CHEQ,EST_CHEQ,DES_ORIG,NUM_REME,UNI_ATEN)
                                      VALUES(v_campania(i),
                                              v_fec_carg(i),
                                              v_fec_fact(i),
                                              v_region(i),
                                              v_zona(i),
                                              v_seccion(i),
                                              v_num_terr(i),
                                              v_cod_cli(i),
                                              v_num_bole(i),
                                              v_num_doc(i),
                                              v_des_nom(i),
                                              v_des_tele(i),
                                              v_camp_ingr(i),
                                              v_val_tota_cata(i),
                                              v_val_tota_neto(i),
                                              v_val_neto_sin_flete(i),
                                              v_val_tota_fact(i),
                                              v_val_sald(i),
                                              v_des_dire(i),
                                              v_val_gast_admin(i),
                                              v_num_prim_pedi(i),
                                              v_ind_cheq(i),
                                              v_est_cheq(i),
                                              v_des_orig(i),
                                              v_num_reme(i),
                                              v_uni_aten(i));

       EXIT WHEN c_factu_detal1%NOTFOUND;

       END LOOP;

   CLOSE c_factu_detal1;
   COMMIT;
   EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_GENER_REPOR_FACTU_DETA1: '||ls_sqlerrm);
     ROLLBACK;

END SAC_PR_GENER_REPOR_FACTU_DETA1;


/*************************************************************************
Descripcion : Genera la data para el Reporte de Facturacion Detalle SAC atraves
              de lo sleccionado(Solo Regiones).
Fecha Creacion : 05/08/2013
Autor : Yahir Rivas Luna
 *************************************************************************/
PROCEDURE SAC_PR_GENER_REPOR_FACTU_DETA2(psPeriodoOid NUMBER,
                                          psFechaInicio   VARCHAR2,
                                          psFechaFin    VARCHAR2,
                                          psOrigen VARCHAR2)
IS
  CURSOR c_factu_detal2 IS
  SELECT J.VAL_NOMB_PERI CAMPANIA,
       nvl((select fec_soli from int_solic_conso_cabec where soca_oid_soli_cabe_refe=a.oid_soli_cabe),
       (select fec_soli from ped_histo_solic_conso_cabec where soca_oid_soli_cabe_refe=a.oid_soli_cabe and perd_oid_peri=a.perd_oid_peri and cod_clie=b.cod_clie))
        FECHA_CARGA
        ,a.fec_fact,
       G.DES_REGI REGION,
       F.DES_ZONA ZONA,
       nvl(H.DES_SECCI, h.cod_secc) des_secci,
       E.COD_TERR TERRITORIO,
       B.COD_CLIE CODIGO_CLIENTE,
      -- A.FEC_FACT,
       (select VAL_NUME_SOLI from ped_solic_cabec where oid_soli_cabe=a.soca_oid_soli_cabe) BOLETA_DESPACHO,
       IDE.NUM_DOCU_IDEN DOCUMENTO_IDENTIDAD,
       TRIM(B.VAL_APE1) || ' ' || TRIM(B.VAL_APE2) || ', ' || TRIM(B.VAL_NOM1) || ' ' ||
       TRIM(B.VAL_NOM2) NOMBRE,
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(B.OID_CLIE,'NUM_TELE') TELEFONO,
       GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_INGRE(B.OID_CLIE) CAMPANIA_INGRESO,
       A.VAL_PREC_CATA_TOTA_LOCA TOTAL_CATALOGO,
       A.VAL_PREC_NETO_TOTA_LOCA TOTAL_NETO,--15
       A.VAL_PREC_NETO_TOTA_LOCA - VAL_IMPO_FLET_SIN_IMPU_TOTA TOTAL_NETO_SIN_FLETE,
       ROUND((select (VAL_TOTA_PAGA_LOCA + (VAL_TOTA_PAGA_LOCA - VAL_IMPO_FLET_TOTA_LOCA) * GEN_PKG_GENER.GEN_FN_DEVUE_TASA_IMPUE_PAIS(B.PAIS_OID_PAIS) / 100) from ped_solic_cabec where oid_soli_cabe=a.soca_oid_soli_cabe),
             2) TOTAL_FACTURADO,
           b.sal_deud_ante SALDO,
       (SELECT DIREC.VAL_NOMB_VIA || ' ' || DIREC.NUM_PPAL || ' ' || DIREC.VAL_OBSE || ' ' ||
               (SELECT DES_GEOG
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = (SELECT ORDE_1
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_2 IS NULL
                   AND ORDE_3 IS NULL
                   AND ORDE_4 IS NULL) || ' ' ||
               (SELECT DES_GEOG
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = (SELECT ORDE_1
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_2 = (SELECT ORDE_2
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_3 IS NULL
                   AND ORDE_4 IS NULL) || ' ' ||
               (SELECT DES_GEOG
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = (SELECT ORDE_1
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_2 = (SELECT ORDE_2
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_3 = (SELECT ORDE_3
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_4 IS NULL) || ' ' ||
               (SELECT DES_GEOG
                  FROM ZON_VALOR_ESTRU_GEOPO
                 WHERE ORDE_1 = (SELECT ORDE_1
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_2 = (SELECT ORDE_2
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_3 = (SELECT ORDE_3
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                   AND ORDE_4 = (SELECT ORDE_4
                                   FROM ZON_VALOR_ESTRU_GEOPO
                                  WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP))
          FROM MAE_CLIEN_DIREC DIREC,
               ZON_TERRI       D
         WHERE OID_CLIE_DIRE = A.CLDI_OID_CLIE_DIRE
           AND DIREC.TERR_OID_TERR = D.OID_TERR) DIRECCION,
           a.val_tota_gast_admi GASTOS_ADMINISTRATIVOS,
               spc.cod_peri PRIMER_PEDIDO,
          decode(a.inre_oid_indi_revi, 2, '1','') AS IND_CHEQ,
          (SELECT cod_resu_cheq
                FROM rec_resul_chequ rrc
                WHERE a.recq_oid_resu_cheq = rrc.oid_resu_cheq) EST_CHEQ,
           nvl(sac_pkg_repor.SAC_FN_DEVUE_TIPO_ORIGE(a.oid_soli_cabe),'MIXTO') ORIGEN,
(select x.ind_comp from ped_segui_pedid x, ped_solic_cabec y where x.soca_oid_soli_cabe=y.oid_soli_cabe
And y.oid_soli_cabe=a.soca_oid_soli_cabe
) REMESA,
(select num_unid_aten_tota from ped_solic_cabec where oid_soli_cabe=a.soca_oid_soli_cabe) UNIDADES_ATENDIDAS
  FROM PED_SOLIC_CABEC       A,
       MAE_CLIEN             B,
       MAE_CLIEN_IDENT       IDE,
       ZON_TERRI_ADMIN       D,
       ZON_TERRI             E,
       ZON_SECCI             H,
       ZON_ZONA              F,
       ZON_REGIO             G,
       PED_TIPO_SOLIC_PAIS   H,
       CRA_PERIO             J,
       ped_tipo_solic pts,
       mae_clien_prime_conta mcpc,
       cra_perio cp,
       seg_perio_corpo spc
WHERE A.CLIE_OID_CLIE = B.OID_CLIE
and a.clie_oid_clie=mcpc.clie_oid_clie
and mcpc.perd_oid_peri=cp.oid_peri
and cp.peri_oid_peri=spc.oid_peri
   AND A.ALMC_OID_ALMA = DECODE('','',A.ALMC_OID_ALMA, '')
   AND IDE.CLIE_OID_CLIE = a.CLIE_OID_CLIE
   AND IDE.VAL_IDEN_DOCU_PRIN = 1
   AND a.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
   AND D.TERR_OID_TERR = E.OID_TERR
   AND D.ZSCC_OID_SECC = H.OID_SECC
   AND H.ZZON_OID_ZONA = F.OID_ZONA
   AND F.ZORG_OID_REGI = G.OID_REGI
   AND A.TSPA_OID_TIPO_SOLI_PAIS = H.OID_TIPO_SOLI_PAIS
   and H.tsol_oid_tipo_soli=pts.oid_tipo_soli
   and pts.cod_tipo_soli='SOC'
   AND A.FEC_FACT >= TO_DATE(psFechaInicio,'dd/mm/yyyy') --fecha inicio
   AND A.FEC_FACT <= TO_DATE(psFechaFin,'dd/mm/yyyy')--fecha fin
   AND A.PERD_OID_PERI = J.OID_PERI
   AND A.PERD_OID_PERI = psPeriodoOid--CAMPA?A
   AND (psOrigen is null OR sac_pkg_repor.SAC_FN_DEVUE_TIPO_ORIGE(a.oid_soli_cabe) = psOrigen) --AGREGADO
   AND ((select case when count(cod_regi) = 0 then null else 1 end from SAC_GTT_REPOR_FACTU_DETAL where cod_zona is null  and val_rpat is null) is null or
        G.Cod_Regi in(select cod_regi from SAC_GTT_REPOR_FACTU_DETAL where cod_zona is null and val_rpat is null));

  -- Declarando variables
   --25
    TYPE campania IS TABLE OF SAC_REPOR_FACTU_DETAL.PER_CAMP%TYPE;
    TYPE fec_carg IS TABLE OF SAC_REPOR_FACTU_DETAL.FEC_CARG%TYPE;
    TYPE fec_fact IS TABLE OF SAC_REPOR_FACTU_DETAL.FEC_FACT%TYPE;
    TYPE region IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_REGI%TYPE;
    TYPE zona IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_ZONA%TYPE;
    TYPE seccion IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_SECC%TYPE;
    TYPE num_terr IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_TERR%TYPE;
    TYPE cod_cli IS TABLE OF SAC_REPOR_FACTU_DETAL.COD_CLIE%TYPE;
    TYPE num_bole IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_BOLE_DESP%TYPE;
    TYPE num_doc IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_DOCU_IDEN%TYPE;
    TYPE des_nom IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_NOMB%TYPE;
    TYPE des_tele IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_TELE%TYPE;
    TYPE camp_ingr IS TABLE OF SAC_REPOR_FACTU_DETAL.PER_CAMP_INGR%TYPE;
    TYPE val_tota_cata IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_TOTA_CATA%TYPE;
    TYPE val_tota_neto IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_TOTA_NETO%TYPE;
    TYPE val_neto_sin_flete IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_NETO_SINN_FLET%TYPE;
    TYPE val_tota_fact IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_TOTA_FACT%TYPE;
    TYPE val_sald IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_SALD%TYPE;
    TYPE des_dire IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_DIRE%TYPE;
    TYPE val_gast_admin IS TABLE OF SAC_REPOR_FACTU_DETAL.VAL_GAST_ADMI%TYPE;
    TYPE num_prim_pedi IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_PRIM_PEDI%TYPE;
    TYPE ind_cheq IS TABLE OF SAC_REPOR_FACTU_DETAL.IND_CHEQ%TYPE;
    TYPE est_cheq IS TABLE OF SAC_REPOR_FACTU_DETAL.EST_CHEQ%TYPE;
    TYPE des_orig IS TABLE OF SAC_REPOR_FACTU_DETAL.DES_ORIG%TYPE;
    TYPE num_reme IS TABLE OF SAC_REPOR_FACTU_DETAL.NUM_REME%TYPE;
    TYPE uni_aten IS TABLE OF SAC_REPOR_FACTU_DETAL.UNI_ATEN%TYPE;

    v_campania campania;
    v_fec_carg fec_carg;
    v_fec_fact fec_fact;
    v_region region;
    v_zona zona;
    v_seccion seccion;
    v_num_terr num_terr;
    v_cod_cli cod_cli;
    v_num_bole num_bole;
    v_num_doc num_doc;
    v_des_nom des_nom;
    v_des_tele des_tele;
    v_camp_ingr camp_ingr;
    v_val_tota_cata val_tota_cata;
    v_val_tota_neto val_tota_neto;
    v_val_neto_sin_flete val_neto_sin_flete;
    v_val_tota_fact val_tota_fact;
    v_val_sald val_sald;
    v_des_dire des_dire;
    v_val_gast_admin val_gast_admin;
    v_num_prim_pedi num_prim_pedi;
    v_ind_cheq ind_cheq;
    v_est_cheq est_cheq;
    v_des_orig des_orig;
    v_num_reme num_reme;
    v_uni_aten uni_aten;

    filas NATURAL := 5000;

BEGIN

   delete from SAC_REPOR_FACTU_DETAL;-- eliminamos la data de la tabla temporal

   OPEN c_factu_detal2;

   LOOP
       FETCH c_factu_detal2 BULK COLLECT INTO v_campania,
                                              v_fec_carg,
                                              v_fec_fact,
                                              v_region,
                                              v_zona,
                                              v_seccion,
                                              v_num_terr,
                                              v_cod_cli,
                                              v_num_bole,
                                              v_num_doc,
                                              v_des_nom,
                                              v_des_tele,
                                              v_camp_ingr,
                                              v_val_tota_cata,
                                              v_val_tota_neto,
                                              v_val_neto_sin_flete,
                                              v_val_tota_fact,
                                              v_val_sald,
                                              v_des_dire,
                                              v_val_gast_admin,
                                              v_num_prim_pedi,
                                              v_ind_cheq,
                                              v_est_cheq,
                                              v_des_orig,
                                              v_num_reme,
                                              v_uni_aten LIMIT filas;

       FORALL i IN 1..v_campania.COUNT
       INSERT INTO SAC_REPOR_FACTU_DETAL(PER_CAMP,FEC_CARG,FEC_FACT,DES_REGI,DES_ZONA,
                                          DES_SECC,NUM_TERR,COD_CLIE,NUM_BOLE_DESP,NUM_DOCU_IDEN,DES_NOMB,
                                          DES_TELE,PER_CAMP_INGR,VAL_TOTA_CATA,VAL_TOTA_NETO,VAL_NETO_SINN_FLET,
                                          VAL_TOTA_FACT,VAL_SALD,DES_DIRE,VAL_GAST_ADMI,NUM_PRIM_PEDI,
                                          IND_CHEQ,EST_CHEQ,DES_ORIG,NUM_REME, UNI_ATEN)
                                      VALUES(v_campania(i),
                                              v_fec_carg(i),
                                              v_fec_fact(i),
                                              v_region(i),
                                              v_zona(i),
                                              v_seccion(i),
                                              v_num_terr(i),
                                              v_cod_cli(i),
                                              v_num_bole(i),
                                              v_num_doc(i),
                                              v_des_nom(i),
                                              v_des_tele(i),
                                              v_camp_ingr(i),
                                              v_val_tota_cata(i),
                                              v_val_tota_neto(i),
                                              v_val_neto_sin_flete(i),
                                              v_val_tota_fact(i),
                                              v_val_sald(i),
                                              v_des_dire(i),
                                              v_val_gast_admin(i),
                                              v_num_prim_pedi(i),
                                              v_ind_cheq(i),
                                              v_est_cheq(i),
                                              v_des_orig(i),
                                              v_num_reme(i),
                                              v_uni_aten(i));

       EXIT WHEN c_factu_detal2%NOTFOUND;

       END LOOP;

   CLOSE c_factu_detal2;
   COMMIT;
   EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_GENER_REPOR_FACTU_DETA2: '||ls_sqlerrm);
     ROLLBACK;

END SAC_PR_GENER_REPOR_FACTU_DETA2;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAC Facturacion Detalle
                    (Algunas zonas y algunas regiones) y todos.
Fecha Creacion    : 16/01/2014
Autor             : Gonzalo Javier Huertas Agurto
Parametros        :
            psCodigoPeriodo : Codigo Pais
            psNombreArchivo : Nombre del Archivo
            psDirectorio: Directorio en donde se encuentra el archivo
***************************************************************************/
PROCEDURE SAC_PR_REPOR_FACTU_DETA(
    psCodigoPeriodo                     VARCHAR2,
    psAlmacen                           VARCHAR2,
    psOidPeriodo                        VARCHAR2,
    psFechaFacturacion                  VARCHAR2,
    psTipoPedido                        VARCHAR2,
    psCodigoPais                        VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lsFlagRegionesZonas VARCHAR2(1);
  lnOidCodigoPeriodo NUMBER;
  lnTotalCatalogo varchar2(20);
  lnTotalFacturado varchar2(20);
  lnTotalNeto varchar2(20);
  lnTotalNetoSinFlete varchar2(20);

  CURSOR c_interfaz IS
        SELECT J.VAL_NOMB_PERI CAMPANIA,
           G.DES_REGI REGION,
           F.DES_ZONA ZONA,
           H.DES_SECCI,
           E.COD_TERR TERRITORIO,
           B.COD_CLIE CODIGO_CLIENTE,
           to_char(A.FEC_FACT,'dd/MM/yyyy') FEC_FACT,
           A.VAL_NUME_SOLI BOLETA_DESPACHO,
           IDE.NUM_DOCU_IDEN DOCUMENTO_IDENTIDAD,
           TRIM(B.VAL_APE1) || ' ' || TRIM(B.VAL_APE2) || ' - ' || TRIM(B.VAL_NOM1) || ' ' ||
           TRIM(B.VAL_NOM2) NOMBRE,
           GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(B.OID_CLIE,'NUM_TELE') TELEFONO,
           GEN_PKG_GENER.GEN_FN_CLIEN_PERIO_INGRE(B.OID_CLIE) CAMPANIA_INGRESO,
           A.VAL_PREC_CATA_TOTA_LOCA TOTAL_CATALOGO,
           A.VAL_PREC_NETO_TOTA_LOCA TOTAL_NETO,
           A.VAL_PREC_NETO_TOTA_LOCA - VAL_IMPO_FLET_SIN_IMPU_TOTA TOTAL_NETO_SIN_FLETE,
           ROUND((A.VAL_TOTA_PAGA_LOCA + (A.VAL_TOTA_PAGA_LOCA - A.VAL_IMPO_FLET_TOTA_LOCA) *
                 GEN_PKG_GENER.GEN_FN_DEVUE_TASA_IMPUE_PAIS(B.PAIS_OID_PAIS) / 100),
                 2) TOTAL_FACTURADO,
           (SELECT SUM(IMP_PEND)
              FROM CCC_MOVIM_CUENT_CORRI
             WHERE CLIE_OID_CLIE = B.OID_CLIE
               AND FEC_VENC <= (SELECT MAX(FEC_INIC)
                                  FROM CRA_CRONO
                                 WHERE CACT_OID_ACTI = (SELECT OID_ACTI
                                                          FROM CRA_ACTIV
                                                         WHERE PAIS_OID_PAIS = B.PAIS_OID_PAIS
                                                           AND COD_ACTI = 'V1')
                                   AND PERD_OID_PERI = J.OID_PERI)) SALDO,
           (SELECT DIREC.VAL_NOMB_VIA || ' ' || DIREC.NUM_PPAL || ' ' || DIREC.VAL_OBSE || ' ' ||
                   (SELECT DES_GEOG
                      FROM ZON_VALOR_ESTRU_GEOPO
                     WHERE ORDE_1 = (SELECT ORDE_1
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_2 IS NULL
                       AND ORDE_3 IS NULL
                       AND ORDE_4 IS NULL) || ' ' ||
                   (SELECT DES_GEOG
                      FROM ZON_VALOR_ESTRU_GEOPO
                     WHERE ORDE_1 = (SELECT ORDE_1
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_2 = (SELECT ORDE_2
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_3 IS NULL
                       AND ORDE_4 IS NULL) || ' ' ||
                   (SELECT DES_GEOG
                      FROM ZON_VALOR_ESTRU_GEOPO
                     WHERE ORDE_1 = (SELECT ORDE_1
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_2 = (SELECT ORDE_2
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_3 = (SELECT ORDE_3
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_4 IS NULL) || ' ' ||
                   (SELECT DES_GEOG
                      FROM ZON_VALOR_ESTRU_GEOPO
                     WHERE ORDE_1 = (SELECT ORDE_1
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_2 = (SELECT ORDE_2
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_3 = (SELECT ORDE_3
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP)
                       AND ORDE_4 = (SELECT ORDE_4
                                       FROM ZON_VALOR_ESTRU_GEOPO
                                      WHERE OID_VALO_ESTR_GEOP = D.VEPO_OID_VALO_ESTR_GEOP))
              FROM MAE_CLIEN_DIREC DIREC,
                   ZON_TERRI       D
             WHERE OID_CLIE_DIRE = A.CLDI_OID_CLIE_DIRE
               AND DIREC.TERR_OID_TERR = D.OID_TERR) DIRECCION,
         (SELECT DIREC.VAL_COD_POST
          FROM MAE_CLIEN_DIREC DIREC
          WHERE OID_CLIE_DIRE = A.CLDI_OID_CLIE_DIRE) CODIGOPOSTAL,
           a.val_tota_gast_admi GASTOS_ADMINISTRATIVOS,
           (SELECT M.VAL_I18N
              FROM GEN_I18N_SICC_PAIS M
             WHERE A.ALMC_OID_ALMA = M.VAL_OID
               AND M.ATTR_ENTI = 'BEL_ALMAC') ALMACEN,
           DECODE((SELECT SUM(IND_OC) FROM PED_SOLIC_CABEC WHERE SOCA_OID_SOLI_CABE = A.OID_SOLI_CABE),
                  0,
                  DECODE((SELECT COUNT(IND_OC)
                           FROM PED_SOLIC_CABEC
                          WHERE SOCA_OID_SOLI_CABE = A.OID_SOLI_CABE
                            AND MODU_OID_MODU = 13),
                         0,
                        DECODE((SELECT COUNT(IND_OC)
                           FROM PED_SOLIC_CABEC
                          WHERE SOCA_OID_SOLI_CABE = A.OID_SOLI_CABE
                            AND MODU_OID_MODU = 9),
                         0,
                         'RECLAMO',
             'MAV'),
                         'INCENTIVO'),
                  'NORMAL') PEDIDOS,
                   mce.CAMP_PRIM_PEDI PRIMER_PEDIDO,
                   mce.CAMP_ULTI_PEDI ULTIMO_PEDIDO,
              decode(a.inre_oid_indi_revi, 2, '1','') AS IND_CHEQ,
              (SELECT cod_resu_cheq
                    FROM rec_resul_chequ rrc
                    WHERE a.recq_oid_resu_cheq = rrc.oid_resu_cheq) EST_CHEQ,
               ped_pkg_cuadr_ofert.ped_fn_devue_orig_conso(a.oid_soli_cabe) ORIGEN,
               int_pkg_laris.LAR_FN_IND_SERV_RECL2(a.oid_soli_cabe, a.perd_oid_peri) IND_YOBEL,
                case when exists
                (SELECT 1
                  FROM gen_i18n_sicc_comun x,
                       ped_tipo_solic_pais y,
                       ped_tipo_solic z,
                       ped_solic_cabec zz
                 WHERE val_oid = z.oid_tipo_soli
                   AND attr_enti = 'PED_TIPO_SOLIC'
                   AND UPPER (val_i18n) LIKE '%XPRES%'
                   AND zz.tspa_oid_tipo_soli_pais = y.oid_tipo_soli_pais
                   AND y.tsol_oid_tipo_soli = z.oid_tipo_soli
                   AND zz.soca_oid_soli_cabe = a.oid_soli_cabe)
                then '1'
                else '0'
                end IND_EXPRESS,
                (select ind_comp from ped_segui_pedid where soca_oid_soli_cabe=a.oid_soli_cabe) REMESA,
          gen_pkg_gener.gen_fn_devuelve_descripcion( mcda.ESTA_OID_ESTA_CLIE ,'MAE_ESTAT_CLIEN' ,'es') ESTADO
          , (select y.docu_num_docu from ped_solic_cabec x, int_solic_conso_cabec y where x.soca_oid_soli_cabe=a.oid_soli_cabe and x.oid_soli_cabe=y.soca_oid_soli_cabe_refe and y.docu_num_docu is not null and rownum=1) PREIMPRESO,
          B.SAL_DEUD_ANTE SALDO,
          TO_CHAR((SELECT MAX(FEC_INIC)
              FROM CRA_CRONO
             WHERE CACT_OID_ACTI = (SELECT OID_ACTI
                                      FROM CRA_ACTIV
                                     WHERE PAIS_OID_PAIS = B.PAIS_OID_PAIS
                                       AND COD_ACTI = 'FA') AND PERD_OID_PERI = J.OID_PERI and zzon_oid_zona=a.zzon_oid_zona), 'DD/MM/YYYY') FECHA_INICIO_FACTURACION
      FROM PED_SOLIC_CABEC       A,
           MAE_CLIEN             B,
           MAE_CLIEN_IDENT       IDE,
           ZON_TERRI_ADMIN       D,
           ZON_TERRI             E,
           ZON_SECCI             H,
           ZON_ZONA              F,
           ZON_REGIO             G,
           PED_TIPO_SOLIC_PAIS   H,
           PED_TIPO_SOLIC        I,
           CRA_PERIO             J,
           int_lar_tipo_solici_pedido_dis dis,
         mae_clien_datos_adici mcda,
         mae_clien_estat mce
     WHERE A.CLIE_OID_CLIE = B.OID_CLIE
       and b.oid_clie=mce.oid_clie(+)
       AND MCDA.Clie_Oid_Clie = IDE.CLIE_OID_CLIE
       AND A.ALMC_OID_ALMA = DECODE(psAlmacen,'',A.ALMC_OID_ALMA, psAlmacen)
       AND IDE.CLIE_OID_CLIE = A.CLIE_OID_CLIE
       AND IDE.VAL_IDEN_DOCU_PRIN = 1
       AND A.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
       AND D.TERR_OID_TERR = E.OID_TERR
       AND D.ZSCC_OID_SECC = H.OID_SECC
       AND H.ZZON_OID_ZONA = F.OID_ZONA
       AND F.ZORG_OID_REGI = G.OID_REGI
       AND A.TSPA_OID_TIPO_SOLI_PAIS = H.OID_TIPO_SOLI_PAIS
       AND H.TSOL_OID_TIPO_SOLI = I.OID_TIPO_SOLI
       AND H.OID_TIPO_SOLI_PAIS = dis.tspa_oid_tipo_soli_pais
       AND A.NUM_UNID_ATEN_TOTA > 0
       AND A.FEC_FACT IS NOT NULL
       AND A.PERD_OID_PERI = J.OID_PERI
       AND A.PERD_OID_PERI = psOidPeriodo
       AND A.FEC_FACT = NVL(TO_DATE(psFechaFacturacion,'DD/MM/YYYY'),A.FEC_FACT)
       /*condicion region zona*/
       AND ((lsFlagRegionesZonas = '1' AND f.COD_ZONA IN(SELECT COD_ZONA FROM APE_REPOR_REGIO_ZONA)) OR lsFlagRegionesZonas='0')
       /*condicion tipo pedido*/
       AND (  CASE WHEN psTipoPedido = '1' THEN decode((select sum(ind_oc) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe),        0,decode(          (select count(ind_oc) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe and modu_oid_modu=15),              0,'RECLAMO',               'INCENTIVO'),           'NORMAL' ) ELSE ''  END = 'NORMAL'
        OR
        CASE WHEN psTipoPedido = '2' THEN decode((select sum(ind_oc) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe),        0,decode(          (select count(ind_oc) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe and modu_oid_modu=15),              0,'RECLAMO',               'INCENTIVO'),   'NORMAL' ) ELSE ''  END <> 'NORMAL'
        OR
        CASE WHEN DECODE(psTipoPedido,NULL,'3',psTipoPedido) = '3' THEN 'NORMAL' END = 'NORMAL'
        );

TYPE interfazTipo IS RECORD (
  v_CAMPANIA                varchar2(40),
  v_REGION                  varchar2(40),
	v_ZONA                    varchar2(40),
  v_DES_SECCI               varchar2(40),
  v_TERRITORIO              number(6),
	v_CODIGO_CLIENTE          varchar2(15),
  v_FEC_FACT                varchar2(10),
  v_BOLETA_DESPACHO         number(10),
  v_DOCUMENTO_IDENTIDAD     varchar2(30),
	v_NOMBRE                  varchar2(100),
  v_TELEFONO                varchar2(500),
  v_CAMPANIA_INGRESO        number(6),
  v_TOTAL_CATALOGO          number(12,2),
  v_TOTAL_NETO              number(12,2),
  v_TOTAL_NETO_SIN_FLETE    number(12,2),
  v_TOTAL_FACTURADO         number(12,2),
  v_SALDO                   number(12,2),
	v_DIRECCION               varchar2(900),
  v_CODIGOPOSTAL            varchar2(5),
  v_GASTOS_ADMINISTRATIVOS  number(12,2),
	v_ALMACEN                 varchar2(4000),
	v_PEDIDOS                 varchar2(20),
	v_PRIMER_PEDIDO           varchar2(6),
  v_ULTIMO_PEDIDO           varchar2(6),
  v_IND_CHEQ                varchar2(1),
  v_EST_CHEQ                varchar2(2),
  v_ORIGEN                  varchar2(100),
  v_IND_YOBEL               varchar2(1),
  v_IND_EXPRESS             varchar2(1),
  v_REMESA                  number(3),
  v_ESTADO                  varchar2(1000),
	v_PREIMPRESO              varchar2(10),
  v_SALDO_ACTUAL               number(12,2),
  v_FECHA_INICIO_FACTURACION   varchar2(10)
);

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;
   lbAbrirUtlFile  BOOLEAN;

BEGIN

  SELECT DECODE(COUNT(*), 0, '0', '1')
       INTO lsFlagRegionesZonas
     FROM APE_REPOR_REGIO_ZONA;

  lnOidCodigoPeriodo := GEN_PKG_GENER.gen_fn_devuelve_id_cra_perio2(psCodigoPeriodo);

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          select decode(ROUND(interfazRecord(x).v_TOTAL_CATALOGO, 0),0,to_char(interfazRecord(x).v_TOTAL_CATALOGO,'0.99'),TO_CHAR(interfazRecord(x).v_TOTAL_CATALOGO,'999999999.99'))
          into lnTotalCatalogo
          from dual;

          select decode(ROUND(interfazRecord(x).v_TOTAL_FACTURADO, 0),0,to_char(interfazRecord(x).v_TOTAL_FACTURADO,'0.99'),TO_CHAR(interfazRecord(x).v_TOTAL_FACTURADO,'999999999.99'))
          into lnTotalFacturado
          from dual;

          select decode(ROUND(interfazRecord(x).v_TOTAL_NETO, 0),0,to_char(interfazRecord(x).v_TOTAL_NETO,'0.99'),TO_CHAR(interfazRecord(x).v_TOTAL_NETO,'999999999.99'))
          into lnTotalNeto
          from dual;

          select decode(ROUND(interfazRecord(x).v_TOTAL_NETO_SIN_FLETE, 0),0,to_char(interfazRecord(x).v_TOTAL_NETO_SIN_FLETE,'0.99'),TO_CHAR(interfazRecord(x).v_TOTAL_NETO_SIN_FLETE,'999999999.99'))
          into lnTotalNetoSinFlete
          from dual;

          lsLinea := '=T("'||interfazRecord(x).v_CAMPANIA||'")' ||','||
                     '"'||interfazRecord(x).v_REGION||'"' ||','||
                     '"'||interfazRecord(x).v_ZONA||'"' ||','||
                     '"'||interfazRecord(x).v_DES_SECCI||'"' ||','||
                    '=T("'||interfazRecord(x).v_TERRITORIO||'")' ||','||
                    '=T("'||interfazRecord(x).v_CODIGO_CLIENTE||'")' ||','||
                    '=T("'||interfazRecord(x).v_FEC_FACT||'")' ||','||
                    '=T("'||interfazRecord(x).v_BOLETA_DESPACHO||'")' ||','||
                    '=T("'||interfazRecord(x).v_DOCUMENTO_IDENTIDAD||'")' ||','||
                    '"'||interfazRecord(x).v_NOMBRE||'"' ||','||
                    '=T("'||interfazRecord(x).v_TELEFONO||'")' ||','||
                    '=T("'||interfazRecord(x).v_CAMPANIA_INGRESO||'")' ||','||
--                    '=T("'||to_char(interfazRecord(x).v_TOTAL_CATALOGO,'999999999.99')||'")'||','||
                    '=T("'||lnTotalCatalogo||'")'||','||
                    '=T("'||lnTotalFacturado||'")' ||','||
                    '=T("'||lnTotalNeto||'")' ||','||
                    '=T("'||lnTotalNetoSinFlete||'")' ||','||
                    interfazRecord(x).v_PEDIDOS||','||
                    '"'||REPLACE(interfazRecord(x).v_DIRECCION,',')||'"' ||','||
                    '"'||interfazRecord(x).v_CODIGOPOSTAL||'"' ||','||
                    interfazRecord(x).v_ALMACEN||','||
                    '=T("'||interfazRecord(x).v_PRIMER_PEDIDO||'")' ||','||
                    '=T("'||interfazRecord(x).v_ULTIMO_PEDIDO||'")' ||','||
                    '=T("'||interfazRecord(x).v_IND_CHEQ||'")' ||','||
                    '=T("'||interfazRecord(x).v_EST_CHEQ||'")' ||','||
                    interfazRecord(x).v_ORIGEN ||','||
                    '=T("'||interfazRecord(x).v_GASTOS_ADMINISTRATIVOS||'")' ||','||
                    '=T("'||interfazRecord(x).v_IND_YOBEL||'")' ||','||
                    '=T("'||interfazRecord(x).v_IND_EXPRESS||'")' ||','||
                    '=T("'||interfazRecord(x).v_REMESA||'")' ||','||
                    '"'||interfazRecord(x).v_ESTADO||'"' ||','||
                    '"'||interfazRecord(x).v_PREIMPRESO||'"' ||','||
                    '"'||interfazRecord(x).v_SALDO_ACTUAL||'"' ||','||
                    '"'||interfazRecord(x).v_FECHA_INICIO_FACTURACION||'"';
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;
 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_REPOR_FACTU_DETA: '||ls_sqlerrm);
END SAC_PR_REPOR_FACTU_DETA;

/***************************************************************************
Descripcion       : Genera archivo CSV correspondiente al Reporte SAC Recall Trazabilidad
Fecha Creacion    : 22/05/2014
Autor             : Aurelio Oviedo
***************************************************************************/
PROCEDURE SAC_PR_REPOR_RECAL_TRAZA(
    psCodigoPais                        VARCHAR2,
    psCodigoSAP                     VARCHAR2,
    psNumeroLote                     VARCHAR2,
    psFechaInicial                  VARCHAR2,
    psFechaFinal                  VARCHAR2,
    psNombreArchivo                     VARCHAR2,
    psTitulo                            VARCHAR2,
    psDirectorio                   OUT  VARCHAR2
    )
IS

  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 5000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(4000);
  lsFlagRegionesZonas VARCHAR2(1);
  lnOidCodigoPeriodo NUMBER;
  lnTotalCatalogo varchar2(20);
  lnTotalFacturado varchar2(20);
  lnTotalNeto varchar2(20);
  lnTotalNetoSinFlete varchar2(20);

  CURSOR c_interfaz IS
        select
                a.cod_sap,a.num_lote,A.UNI_ATEN, d.cod_Clie,D.VAL_APE1 || ' ' || D.VAL_APE2 || ' ' || D.VAL_NOM1 || ' ' || D.VAL_NOM2 NOM_CLIE,
                a.num_pedi,b.fec_fact,dtel.telefono, dcel.celular,
                (SELECT NVL(STV.DES_ABRV_TIPO_VIA,'') FROM OWN_COMUN.SEG_TIPO_VIA STV WHERE STV.OID_TIPO_VIA =  mcd.TIVI_OID_TIPO_VIA) || ' ' || mcd.VAL_NOMB_VIA || ', ' || mcd.VAL_OBSE  AS DIRECCION,
                F.DES_REGI, E.DES_ZONA
                 from SSICC_PE_ES.YOB_CARGA_LOTES_TRAZA_HISTO a, ped_solic_cabec b, mae_clien d, zon_zona e, ZON_REGIO f ,MAE_CLIEN_DIREC mcd,
                 (
                        select
                            mcc.CLIE_OID_CLIE,
                            mcc.VAL_TEXT_COMU telefono
                        from
                            MAE_CLIEN_COMUN mcc,
                            MAE_TIPO_COMUN mtc
                        where
                            mcc.TICM_OID_TIPO_COMU = mtc.OID_TIPO_COMU
                            and mtc.COD_TIPO_COMU in ('TT')
                        order by 1
                    ) dtel,
                    (
                    select
                            mcc.CLIE_OID_CLIE,
                            mcc.VAL_TEXT_COMU celular
                        from
                            MAE_CLIEN_COMUN mcc,
                            MAE_TIPO_COMUN mtc
                        where
                            mcc.TICM_OID_TIPO_COMU = mtc.OID_TIPO_COMU
                            and mtc.COD_TIPO_COMU in ('TM')
                        order by 1
                    ) dcel
                where a.cod_sap = psCodigoSAP
                and a.num_lote = psNumeroLote
                and B.PERD_OID_PERI in (
                SELECT A.OID_PERI FROM CRA_PERIO A WHERE
                A.FEC_FINA >= TO_DATE(psFechaInicial, 'DD/MM/YYYY')
                AND A.FEC_INIC  <=  TO_DATE(psFechaFinal, 'DD/MM/YYYY')
                )
                and a.num_pedi = b.val_nume_soli
                and b.clie_oid_clie = d.oid_clie
                and B.ZZON_OID_ZONA = e.oid_zona
                and E.ZORG_OID_REGI = F.OID_REGI
                and d.OID_CLIE = dtel.CLIE_OID_CLIE(+)
                and d.OID_CLIE = dcel.CLIE_OID_CLIE(+)
                and d.oid_clie = mcd.clie_oid_clie
                and mcd.IND_DIRE_PPAL = 1
                and mcd.IND_ELIM = 0
                order by F.DES_REGI, E.DES_ZONA, b.fec_fact;

    TYPE interfazTipo IS RECORD (
        v_cod_sap                YOB_CARGA_LOTES_TRAZA_HISTO.COD_SAP%TYPE,
        v_num_lote               YOB_CARGA_LOTES_TRAZA_HISTO.NUM_LOTE%TYPE,
        v_uni_aten                YOB_CARGA_LOTES_TRAZA_HISTO.UNI_ATEN%TYPE,
        v_cod_clie                 MAE_CLIEN.COD_CLIE%TYPE,
        v_nom_clie                VARCHAR2(100),
        v_num_pedi               YOB_CARGA_LOTES_TRAZA_HISTO.NUM_PEDI%TYPE,
        v_fec_fact                  PED_SOLIC_CABEC.FEC_FACT%TYPE,
        v_val_tele                  MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        v_val_celu                  MAE_CLIEN_COMUN.VAL_TEXT_COMU%TYPE,
        v_nom_dire                VARCHAR2(500),
        v_des_regi                 ZON_REGIO.DES_REGI%TYPE,
        v_des_zona                ZON_ZONA.DES_ZONA%TYPE
    );

   TYPE interfazTab  IS TABLE OF interfazTipo ;
       interfazRecord interfazTab;

    lbAbrirUtlFile  BOOLEAN;

BEGIN

  lbAbrirUtlFile := TRUE;

  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, V_HANDLE);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;

   END IF ;
   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := '=T("'||interfazRecord(x).v_cod_sap||'")' ||','||
                             '"'||interfazRecord(x).v_num_lote||'"' ||','||
                             '"'||interfazRecord(x).v_uni_aten||'"' ||','||
                             '"'||interfazRecord(x).v_cod_clie||'"' ||','||
                            '"'||interfazRecord(x).v_nom_clie||'"' ||','||
                            '"'||interfazRecord(x).v_num_pedi||'"' ||','||
                            '"'||interfazRecord(x).v_fec_fact||'"' ||','||
                            '"'||interfazRecord(x).v_val_tele||'"' ||','||
                            '"'||interfazRecord(x).v_val_celu||'"' ||','||
                            '"'||interfazRecord(x).v_nom_dire||'"' ||','||
                            '"'||interfazRecord(x).v_des_regi||'"' ||','||
                            '"'||interfazRecord(x).v_des_zona||'"';

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_REPOR_RECAL_TRAZA: '||ls_sqlerrm);
END SAC_PR_REPOR_RECAL_TRAZA;

/*********************************************************************************************
Descripcion           : Genera archivo CSV correspondiente al Reporte SAC de Control Facturacion y Entrega de Pedido
Fecha Creacion    : 02/10/2014
Autor                   : Sebastian Guerra
*********************************************************************************************/
PROCEDURE SAC_PR_REPOR_CTRL_FACT_PEDI(
    psCodigoPais                     VARCHAR2,
    psCodigoPeriodoInicial      VARCHAR2,
    psCodigoPeriodoFinal        VARCHAR2,
    psCodigoSecuencia           VARCHAR2,
    psNombreArchivo              VARCHAR2,
    psTitulo                              VARCHAR2,
    psDirectorio                       OUT  VARCHAR2
    )
IS
  lsDirTempo                    BAS_INTER.DIR_TEMP%TYPE;
  w_filas                           NUMBER := 5000 ;
  v_handle                       UTL_FILE.FILE_TYPE;
  lsLinea                           VARCHAR2(4000);

  CURSOR c_interfaz IS
    SELECT 
      cod_pais, 
      cod_clie, 
      des_regi, 
      cod_zona, 
      cod_peri, 
      fec_comp, 
      fec_est_orig, 
      fec_prom, 
      val_turn, 
      fec_prim, 
      fec_rece, 
      fec_fact, 
      fec_real
    FROM SAC_GTT_REPOR_CTRL_FACT_PEDI
    ORDER BY
       fec_fact,
       des_regi,
       cod_zona;
    
    TYPE interfazTipo IS RECORD (
        v_cod_pais              VARCHAR2(3),
        v_cod_clie              VARCHAR2(15),
        v_des_regi              VARCHAR2(40),
        v_cod_zona              VARCHAR2(4),
        v_cod_peri              VARCHAR2(6),
        v_fec_comp              VARCHAR2(10),
        v_fec_est_orig          VARCHAR2(10),
        v_fec_prom              VARCHAR2(10),
        v_val_turn              VARCHAR2(10),
        v_fec_prim              VARCHAR2(10),
        v_fec_rece              VARCHAR2(10),
        v_fec_fact              VARCHAR2(10),
        v_fec_real              VARCHAR2(10)
    );

   TYPE interfazTab  IS TABLE OF interfazTipo ;
   interfazRecord interfazTab;

    lbAbrirUtlFile  BOOLEAN;
    ln_oid_tipo_soli_pais NUMBER;

BEGIN
  DELETE FROM SAC_GTT_REPOR_CTRL_FACT_PEDI;
  lbAbrirUtlFile := TRUE;
  ln_oid_tipo_soli_pais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC');
  
  INSERT INTO SAC_GTT_REPOR_CTRL_FACT_PEDI(
    cod_pais, 
    cod_clie, 
    des_regi, 
    cod_zona, 
    cod_peri, 
    fec_comp, 
    fec_est_orig, 
    fec_prom, 
    val_turn, 
    fec_prim, 
    fec_rece, 
    fec_fact, 
    fec_real
    )
    SELECT sp.cod_pais pais,
           mc.cod_clie consultora,
           zr.des_regi region,
           zz.cod_zona zona,
           spc.cod_peri ,
           (SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
              FROM cra_crono cc,
                   cra_activ ca
             WHERE cc.perd_oid_peri = cp.oid_peri
               AND cc.zzon_oid_zona = zz.oid_zona
               AND cc.cact_oid_acti = ca.oid_acti
               AND ca.cod_acti = (SELECT val_para
                                    FROM bas_param_pais
                                   WHERE cod_pais = psCodigoPais
                                     AND cod_sist = 'CRA'
                                     AND cod_para = '001')) conf_ventas_cra, --CV debe ser un parametro
           (SELECT  nvl(to_char(fec_orig, 'dd/mm/yyyy'),to_char(fec, 'dd/mm/yyyy'))
              FROM ped_segui_pedid
             WHERE soca_oid_soli_cabe = psc.soca_oid_soli_cabe) fec_est_orig,                                     
           (SELECT to_char(fec, 'dd/mm/yyyy')
              FROM ped_segui_pedid
             WHERE soca_oid_soli_cabe = psc.soca_oid_soli_cabe) reparto_cra, --RP debe ser un parametro
           (SELECT val_turn
              FROM ped_segui_pedid
             WHERE soca_oid_soli_cabe = psc.soca_oid_soli_cabe) turno,
           (SELECT to_char(cc.fec_inic, 'dd/mm/yyyy')
              FROM cra_crono cc,
                   cra_activ ca
             WHERE cc.perd_oid_peri = cp.oid_peri
               AND cc.zzon_oid_zona = zz.oid_zona
               AND cc.cact_oid_acti = ca.oid_acti
               AND ca.cod_acti = (SELECT val_para
                                    FROM bas_param_pais
                                   WHERE cod_pais = psCodigoPais
                                     AND cod_sist = 'CRA'
                                     AND cod_para = '003')) facturacion_cra, --FA debe ser un parametro
            nvl((SELECT to_char(fec_digi, 'dd/mm/yyyy')
                     FROM int_solic_conso_cabec
                    WHERE cod_clie = mc.cod_clie
                      AND cod_peri = spc.cod_peri
                      AND soca_oid_soli_cabe_refe = psc.oid_soli_cabe),
           (SELECT to_char(fec_digi, 'dd/mm/yyyy')
                     FROM ped_histo_solic_conso_cabec
                    WHERE cod_clie = mc.cod_clie
                      AND cod_peri = spc.cod_peri
                      AND soca_oid_soli_cabe_refe = psc.oid_soli_cabe)
                   ) recibida,
           to_char(psc.fec_fact, 'dd/mm/yyyy') facturada,
           (SELECT to_char(max(temp.fec_proc), 'dd/mm/yyyy')
              FROM REP_SAC_CFACT_EPEDI   temp
             WHERE temp.oid_soli_cabe = psc.soca_oid_soli_cabe
                   AND NUM_SECU = psCodigoSecuencia
               ) REAL
      FROM
           zon_regio             zr,
           zon_zona              zz,
           seg_pais              sp,
           cra_perio             cp,
           seg_perio_corpo       spc,
           ped_solic_cabec       psc,
           mae_clien             mc
     WHERE zz.zorg_oid_regi = zr.oid_regi
       AND psc.clie_oid_clie = mc.oid_clie
       AND zz.oid_zona = psc.zzon_oid_zona
       AND sp.oid_pais = psc.pais_oid_pais
       AND cp.pais_oid_pais = sp.oid_pais
       AND cp.peri_oid_peri = spc.oid_peri
       AND spc.cod_peri >= psCodigoPeriodoInicial
       AND spc.cod_peri <= psCodigoPeriodoFinal
       AND cp.oid_peri = psc.perd_oid_peri
       AND psc.tspa_oid_tipo_soli_pais = ln_oid_tipo_soli_pais
       AND psc.grpr_oid_grup_proc = 5; 
  
  COMMIT;
  
  OPEN c_interfaz;
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
   IF lbAbrirUtlFile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(psCodigoPais, psNombreArchivo, '.csv', psTitulo, lsDirTempo, v_handle);
      psdirectorio   := lsdirtempo;
      lbAbrirUtlFile := FALSE;
   END IF ;

   IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

          lsLinea := '=T("'||interfazRecord(x).v_cod_pais||'")' ||','||
                             '"'||interfazRecord(x).v_cod_clie||'"' ||','||
                             '"'||interfazRecord(x).v_des_regi||'"' ||','||
                             '"'||interfazRecord(x).v_cod_zona||'"' ||','||
                            '"'||interfazRecord(x).v_cod_peri||'"' ||','||
                            '"'||interfazRecord(x).v_fec_comp||'"' ||','||
                            '"'||interfazRecord(x).v_fec_est_orig||'"' ||','||
                            '"'||interfazRecord(x).v_fec_prom||'"' ||','||
                            '"'||interfazRecord(x).v_val_turn||'"' ||','||                            
                            '"'||interfazRecord(x).v_fec_prim||'"' ||','||
                            '"'||interfazRecord(x).v_fec_rece||'"' ||','||
                            '"'||interfazRecord(x).v_fec_fact||'"' ||','||
                            '"'||interfazRecord(x).v_fec_real||'"';

            UTL_FILE.PUT_LINE (v_handle, lslinea );
      END LOOP;
    END IF;
    EXIT WHEN c_interfaz%NOTFOUND;
 END LOOP;
 CLOSE c_interfaz;

 IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
 END IF;
 RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_REPOR_CTRL_FACT_PEDI: '||ls_sqlerrm);
END SAC_PR_REPOR_CTRL_FACT_PEDI;

/***************************************************************************
Descripcion       : Genera la data para el Reporte TIM (Bolivia)
Fecha Creacion    : 24/08/2015
Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE SAC_PR_REPOR_IMPOS_ADUAN(psCodigoPais 	  VARCHAR2,
                                   psCodigoUsuario  VARCHAR2,
												           psCodigoPeriodo 	VARCHAR2,
                                   psFechaInicio 	  VARCHAR2,
												           psFechaFin	    	VARCHAR2,
                                   psRegiones       VARCHAR2,
                                   psZonas          VARCHAR2)
IS
BEGIN

  EXECUTE IMMEDIATE 'TRUNCATE TABLE SAC_TEMPO_REPOR_IMPOS_ADUAN';

  INSERT INTO SAC_TEMPO_REPOR_IMPOS_ADUAN
  SELECT rank() over(order by NUMDOC, TIPO_DOCUMENTO, DESCRIPCION_COMERCIAL) N,
         DESCRIPCION_COMERCIAL,
         TIPO_MERCANCIA,
         TIPO_EMBALAJE,
         CANT,
         TIPO_DOCUMENTO,
         NUMDOC,
         OBSERVACIONES
   FROM (select imp_pkg_proce_laser.imp_fn_desc_produ(psCodigoPais,
                                                     b.prod_oid_prod) DESCRIPCION_COMERCIAL,
                'NACIONAL' TIPO_MERCANCIA,
                'CAJA DE CARTON' TIPO_EMBALAJE,
                b.num_unid_aten CANT,
                decode(a.tido_oid_tipo_docu,
                       1,
                       'FACTURA COMERCIAL',
                       'NOTA DE REMISION') TIPO_DOCUMENTO,
                decode(a.tido_oid_tipo_docu,
                       1,
                       a.num_docu_cont_inte,
                       (select min(num_docu_cont_inte)
                          from fac_docum_conta_cabec
                         where tido_oid_tipo_docu = 1
                           and soca_oid_soli_cabe = a.soca_oid_soli_cabe)) NUMDOC,
                e.cod_zona || ' - ' || f.val_nume_soli ||
                decode(a.tido_oid_tipo_docu, 30, ' PREMIO', '') OBSERVACIONES
           from fac_docum_conta_cabec a,
                fac_docum_conta_linea b,
                cra_perio             c,
                seg_perio_corpo       d,
                zon_zona              e,
                ped_solic_cabec       f,
                zon_regio             g
         where a.tido_oid_tipo_docu in (1, 30)
           and a.oid_cabe = b.dcca_oid_cabe
           and a.perd_oid_peri = c.oid_peri
           and c.peri_oid_peri = d.oid_peri
           and d.cod_peri = psCodigoPeriodo
           and a.soca_oid_soli_cabe = f.oid_soli_cabe
           and f.zzon_oid_zona = e.oid_zona
           and E.ZORG_OID_REGI = g.oid_regi
           and (psZonas IS NULL OR (INSTR(psZonas, '''' || e.cod_zona || '''') > 0) )
           and (psRegiones IS NULL OR (INSTR(psRegiones, '''' || g.cod_regi || '''') > 0) )
           and a.fec_fact >= TO_DATE(psFechaInicio,'dd/MM/yyyy')
           and a.fec_fact <= TO_DATE(psFechaFin,'dd/MM/yyyy'));

  EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_REPOR_IMPOS_ADUAN: ' || ls_sqlerrm);

END SAC_PR_REPOR_IMPOS_ADUAN;

/***************************************************************************
Descripcion       : Genera la data para el Reporte Consultoras Inexistentes
Fecha Creacion    : 28/09/2015
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE SAC_PR_REPOR_CODIG_INEXI(psCodigoPeriodoInicial 	VARCHAR2,
                                   psCodigoPeriodoFinal 	VARCHAR2,
                                   psFechaInicio 	  VARCHAR2,
												           psFechaFin	    	VARCHAR2,
                                   pscodigoRegion   VARCHAR2,
                                   pscodigoZona     VARCHAR2,
                                   pscodigoCliente  VARCHAR2,
                                   psEstado         VARCHAR2,
                                   psNroDocumento   VARCHAR2)
IS
BEGIN

  EXECUTE IMMEDIATE 'TRUNCATE TABLE SAC_REPOR_CODIG_INEXI';
  DELETE FROM SAC_GTT_REPOR_CODIG_INEXI;

   /* Insertando en tabla temporal del Reporte */
  INSERT INTO SAC_GTT_REPOR_CODIG_INEXI(
    cod_peri, 
    cod_regi, 
    cod_zona, 
    cod_secc, 
    docu_num_docu, 
    val_esta, 
    cod_clie_ante, 
    cod_clie_nuev, 
    nom_clie, 
    fec_carg, 
    fec_proc, 
    fec_fact, 
    val_tota_fact, 
    usu_modi, 
    val_sigl
  )
  SELECT   a.cod_peri,                                                                                                 
           b.cod_regi region,                                                                                              
           b.cod_zona zona,                                                                                                
           g.cod_secc seccion,                                                                                             
           b.docu_num_docu documento,                                                                                      
           decode(e.ind_rech,'1','RECHAZADO', 'SOLUCIONADO') estado,                                                                                           
           a.cod_clie_ante cuenta_errada,                                                                                  
           a.cod_clie_nuev cuenta_correcta,                                                                                
           trim(h.val_nom1)                                                                                                     
           || ' '                                                                                                          
           || trim(h.val_nom2)                                                                                                  
           || ' '                                                                                                          
           || trim(h.val_ape1)                                                                                                   
           || ' '                                                                                                          
           || trim(h.val_ape2) nombre,                                                                                           
           b.fec_digi fecha_carga,                                                                                         
           TRUNC (e.fec_modi) fecha_proceso,                                                                               
           d.fec_fact fecha_facturacion,                                                                                   
           d.val_tota_paga_loca total_facturado,                                                                           
           a.usu_modi   ,
           decode(b.IND_DOCU_IDEN,'1','SI','NO') val_sigl
        FROM ped_audit_conso_cabec a,                                                                                      
           ped_histo_solic_conso_cabec b,                                                                                  
           ped_solic_cabec c,                                                                                              
           ped_solic_cabec d,                                                                                              
           sto_histo_docum_digit e,                                                                                        
           zon_terri_admin f,                                                                                              
           zon_secci g,                                                                                                    
           mae_clien h,
           mae_tipo_docum td,
           mae_clien_ident ci                                                                                                      
         WHERE a.cod_peri >= psCodigoPeriodoInicial                                                                         
         AND a.cod_peri <= psCodigoPeriodoFinal                                                                           
         AND a.cod_clie_ante LIKE 'C%'   
         AND a.cod_clie_nuev = h.cod_clie          
         AND (pscodigoCliente IS NULL OR (pscodigoCliente IS NOT NULL AND a.cod_clie_nuev = pscodigoCliente) )                                                                          
         AND a.cod_peri = b.cod_peri                                                                                       
         AND a.cod_peri = e.cod_peri                                                                                       
         AND e.cod_tipo_docu = 'OCC'                                                                                       
         AND a.sec_nume_docu = b.sec_nume_docu                                                                             
         AND a.sec_nume_docu = e.sec_nume_docu                                                                             
         AND b.ztad_oid_terr_admi = f.oid_terr_admi                                                                        
         AND f.zscc_oid_secc = g.oid_secc                                                                                  
         AND b.soca_oid_soli_cabe_refe = c.oid_soli_cabe(+)                                                                
         AND c.soca_oid_soli_cabe = d.oid_soli_cabe(+)                                                                       
         AND (psFechaInicio IS NULL OR (psFechaInicio IS NOT NULL AND d.fec_fact >= TO_DATE(psFechaInicio,'DD/MM/YYYY HH24:MI')) )
         AND (psFechaFin IS NULL OR (psFechaFin IS NOT NULL AND d.fec_fact <= TO_DATE(psFechaFin,'DD/MM/YYYY HH24:MI')) )      
         AND (pscodigoRegion IS NULL OR (pscodigoRegion IS NOT NULL AND b.cod_regi = pscodigoRegion) )                                                 
         AND (pscodigoZona IS NULL OR (pscodigoZona IS NOT NULL AND b.cod_zona = pscodigoZona) )
         and ci.tdoc_oid_tipo_docu = td.oid_tipo_docu
         and ci.val_iden_docu_prin = '1'
         and ci.clie_oid_clie(+) = h.oid_clie
         AND (psEstado IS NULL OR (psEstado IS NOT NULL AND psEstado = decode(e.ind_rech,'1','RECHAZADO', 'SOLUCIONADO')) )
         AND (psNroDocumento IS NULL OR (psNroDocumento IS NOT NULL AND b.docu_num_docu = psNroDocumento) );
         
  COMMIT;
  
   
  INSERT INTO SAC_GTT_REPOR_CODIG_INEXI(
    cod_peri, 
    cod_regi, 
    cod_zona, 
    cod_secc, 
    docu_num_docu, 
    val_esta, 
    cod_clie_ante, 
    cod_clie_nuev, 
    nom_clie, 
    fec_carg, 
    fec_proc, 
    fec_fact, 
    val_tota_fact, 
    usu_modi, 
    val_sigl
  )
     SELECT   a.cod_peri,                                                                                                 
         b.cod_regi region,                                                                                              
         b.cod_zona zona,                                                                                                
         g.cod_secc seccion,                                                                                             
         b.docu_num_docu documento,                                                                                      
         decode(e.ind_rech,'1','RECHAZADO', 'SOLUCIONADO') estado,                                                                                           
         a.cod_clie_ante cuenta_errada,                                                                                  
         a.cod_clie_nuev cuenta_correcta,                                                                                
         trim(h.val_nom1)                                                                                                     
           || ' '                                                                                                          
           || trim(h.val_nom2)                                                                                                  
           || ' '                                                                                                          
           || trim(h.val_ape1)                                                                                                   
           || ' '                                                                                                          
           || trim(h.val_ape2) nombre,                                                                                             
         b.fec_digi fecha_carga,                                                                                         
         TRUNC (e.fec_modi) fecha_proceso,                                                                               
         d.fec_fact fecha_facturacion,                                                                                   
         d.val_tota_paga_loca total_facturado,                                                                           
         a.usu_modi    ,
         decode(b.IND_DOCU_IDEN,'1','SI','NO') val_sigl                                                                                                                 
      FROM ped_audit_conso_cabec a,                                                                                      
         int_solic_conso_cabec b,                                                                                        
         ped_solic_cabec c,                                                                                              
         ped_solic_cabec d,                                                                                              
         sto_docum_digit e,                                                                                              
         zon_terri_admin f,                                                                                              
         zon_secci g,                                                                                                    
         mae_clien h,
         mae_tipo_docum td,
         mae_clien_ident ci                                                                                                      
       WHERE a.cod_peri >= psCodigoPeriodoInicial                                                                              
       AND a.cod_peri <= psCodigoPeriodoFinal                                                                               
       AND a.cod_clie_ante LIKE 'C%'                                                                                     
       AND a.cod_clie_nuev = h.cod_clie   
       AND (pscodigoCliente IS NULL OR (pscodigoCliente IS NOT NULL AND a.cod_clie_nuev = pscodigoCliente) )                                                                                
       AND a.cod_peri = b.cod_peri                                                                                       
       AND a.cod_peri = e.cod_peri                                                                                       
       AND e.cod_tipo_docu = 'OCC'                                                                                       
       AND a.sec_nume_docu = b.sec_nume_docu                                                                             
       AND a.sec_nume_docu = e.sec_nume_docu                                                                             
       AND b.ztad_oid_terr_admi = f.oid_terr_admi                                                                        
       AND f.zscc_oid_secc = g.oid_secc                                                                                  
       AND b.soca_oid_soli_cabe_refe = c.oid_soli_cabe(+)                                                                
       AND c.soca_oid_soli_cabe = d.oid_soli_cabe(+)                                                                     
       AND (psFechaInicio IS NULL OR (psFechaInicio IS NOT NULL AND d.fec_fact >= TO_DATE(psFechaInicio,'DD/MM/YYYY HH24:MI')) )
       AND (psFechaFin IS NULL OR (psFechaFin IS NOT NULL AND d.fec_fact <= TO_DATE(psFechaFin,'DD/MM/YYYY HH24:MI')) )      
       AND (pscodigoRegion IS NULL OR (pscodigoRegion IS NOT NULL AND b.cod_regi = pscodigoRegion) )                                                 
       AND (pscodigoZona IS NULL OR (pscodigoZona IS NOT NULL AND b.cod_zona = pscodigoZona) )
       and ci.tdoc_oid_tipo_docu = td.oid_tipo_docu
       and ci.val_iden_docu_prin = '1'
       and ci.clie_oid_clie(+) = h.oid_clie 
       AND (psEstado IS NULL OR (psEstado IS NOT NULL AND psEstado = decode(e.ind_rech,'1','RECHAZADO', 'SOLUCIONADO')) )
       AND (psNroDocumento IS NULL OR (psNroDocumento IS NOT NULL AND b.docu_num_docu = psNroDocumento) ); 
  
  COMMIT;     
  
  IF (pscodigoCliente IS NULL) THEN 
        
      INSERT INTO SAC_GTT_REPOR_CODIG_INEXI(
        cod_peri, 
        cod_regi, 
        cod_zona, 
        cod_secc, 
        docu_num_docu, 
        val_esta, 
        cod_clie_ante, 
        cod_clie_nuev, 
        nom_clie, 
        fec_carg, 
        fec_proc, 
        fec_fact, 
        val_tota_fact, 
        usu_modi, 
        val_sigl
      )  
      SELECT   b.cod_peri,                                                                                                 
           '' region,                                                                                                      
           b.cod_zona_arri zona,                                                                                           
           '' seccion,                                                                                                     
           b.docu_num_docu documento,                                                                                      
           decode(e.ind_rech,'1','RECHAZADO', 'PENDIENTE') estado,                                                                                             
           b.cod_clie cuenta_errada,                                                                                       
           '' cuenta_correcta,                                                                                             
           '' nombre,                                                                                                      
           b.fec_digi fecha_carga,                                                                                         
           TRUNC (e.fec_modi) fecha_proceso,                                                                               
           NULL fecha_facturacion,                                                                                         
           NULL total_facturado,                                                                                           
           '' usu_modi  ,
           decode(b.IND_DOCU_IDEN,'1','SI','NO') val_sigl                                                                                                            
        FROM ped_histo_solic_conso_cabec b, sto_histo_docum_digit e                                                        
        WHERE b.cod_peri >= psCodigoPeriodoInicial                                                                              
          AND b.cod_peri <= psCodigoPeriodoFinal                                                                                
          AND b.cod_clie LIKE 'C%'                                                                                          
          AND b.cod_peri = e.cod_peri                                                                                       
          AND e.cod_tipo_docu = 'OCC'                                                                                       
          AND b.sec_nume_docu = e.sec_nume_docu          
          AND (pscodigoRegion IS NULL OR (pscodigoRegion IS NOT NULL AND b.cod_regi = pscodigoRegion) )                                                 
          AND (pscodigoZona IS NULL OR (pscodigoZona IS NOT NULL AND b.cod_zona = pscodigoZona) ) 
          AND (psEstado IS NULL OR (psEstado IS NOT NULL AND psEstado = decode(e.ind_rech,'1','RECHAZADO', 'SOLUCIONADO')) )
          AND (psNroDocumento IS NULL OR (psNroDocumento IS NOT NULL AND b.docu_num_docu = psNroDocumento) );
      
      COMMIT;
      
      INSERT INTO SAC_GTT_REPOR_CODIG_INEXI(
        cod_peri, 
        cod_regi, 
        cod_zona, 
        cod_secc, 
        docu_num_docu, 
        val_esta, 
        cod_clie_ante, 
        cod_clie_nuev, 
        nom_clie, 
        fec_carg, 
        fec_proc, 
        fec_fact, 
        val_tota_fact, 
        usu_modi, 
        val_sigl
      )  
      SELECT   b.cod_peri,                                                                                                 
               '' region,                                                                                                      
               b.cod_zona_arri zona,                                                                                           
               '' seccion,                                                                                                     
               b.docu_num_docu documento,                                                                                      
               decode(e.ind_rech,'1','RECHAZADO', 'PENDIENTE') estado,                                                                                             
               b.cod_clie cuenta_errada,                                                                                       
               '' cuenta_correcta,                                                                                             
               '' nombre,                                                                                                      
               b.fec_digi fecha_carga,                                                                                         
               TRUNC (e.fec_modi) fecha_proceso,                                                                               
               NULL fecha_facturacion,                                                                                         
               NULL total_facturado,                                                                                           
               '' usu_modi ,
               decode(b.IND_DOCU_IDEN,'1','SI','NO') val_sigl                                                                                                             
        FROM int_solic_conso_cabec b, sto_docum_digit e                                                                    
         WHERE b.cod_peri >= psCodigoPeriodoInicial                                                                              
         AND b.cod_peri <= psCodigoPeriodoFinal                                                                              
         AND b.cod_clie LIKE 'C%'                                                                                          
         AND b.cod_peri = e.cod_peri                                                                                       
         AND e.cod_tipo_docu = 'OCC'                                                                                       
         AND b.sec_nume_docu = e.sec_nume_docu                                                                             
         AND (pscodigoRegion IS NULL OR (pscodigoRegion IS NOT NULL AND b.cod_regi = pscodigoRegion) )                                                 
         AND (pscodigoZona IS NULL OR (pscodigoZona IS NOT NULL AND b.cod_zona = pscodigoZona) ) 
         AND (psEstado IS NULL OR (psEstado IS NOT NULL AND psEstado = decode(e.ind_rech,'1','RECHAZADO', 'SOLUCIONADO')) )    
         AND (psNroDocumento IS NULL OR (psNroDocumento IS NOT NULL AND b.docu_num_docu = psNroDocumento) );
    
     COMMIT;
 END IF;
 
 /* Insertando en tabla final del Reporte */
 INSERT INTO SAC_REPOR_CODIG_INEXI(
        cod_peri, 
        cod_regi, 
        cod_zona, 
        cod_secc, 
        docu_num_docu, 
        val_esta, 
        cod_clie_ante, 
        cod_clie_nuev, 
        nom_clie, 
        fec_carg, 
        fec_proc, 
        fec_fact, 
        val_tota_fact, 
        usu_modi, 
        val_sigl
      )  
   SELECT DISTINCT
        cod_peri, 
        cod_regi, 
        cod_zona, 
        cod_secc, 
        docu_num_docu, 
        val_esta, 
        cod_clie_ante, 
        cod_clie_nuev, 
        nom_clie, 
        fec_carg, 
        fec_proc, 
        fec_fact, 
        val_tota_fact, 
        usu_modi, 
        val_sigl
   FROM SAC_GTT_REPOR_CODIG_INEXI;
   COMMIT;
 
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := SUBSTR(SQLERRM, 1, 250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR SAC_PR_REPOR_CODIG_INEXI: ' || ls_sqlerrm);

END SAC_PR_REPOR_CODIG_INEXI;

END SAC_PKG_REPOR;
/
