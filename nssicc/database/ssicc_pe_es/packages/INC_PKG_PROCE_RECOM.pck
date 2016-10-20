create or replace package INC_PKG_PROCE_RECOM is

/* Declaracion de variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=3000;

  TIPO_FACTURACION CONSTANT VARCHAR2(1) := 'F'; 
  TIPO_CIERRE_ZONA CONSTANT VARCHAR2(1) := 'Z'; 
  TIPO_CIERRE_REGION CONSTANT VARCHAR2(1) := 'R';
  TIPO_CIERRE_PERIODO CONSTANT VARCHAR2(1) := 'P';
  
/**************************************************************************
Descripcion       : Este proceso replica al proceso P448 de GP4 de SICC, que
                    es el proceso Determinacion Ganadoras Consultoras de 
                    Recomendacion

Fecha Creacion    : 08/09/2015
Parametros Entrada:
  psCodigoPais     :  Codigo Pais
  pnOidCliente     :  Oid Cliente
  pnOidSolicitud     :  Oid Solicitud
  psTipoCierre       : Tipo Cierre : NULL->Facturacion Diaria, Z->Zona,  P->Periodo 
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_DETER_GANAD_RECOM(psCodigoPais               VARCHAR2,
                                   pnOidCliente               NUMBER,
                                   pnOidSolicitud             NUMBER,
                                   psTipoCierre               VARCHAR2,
                                   psCodigoPeriodo            VARCHAR2,
                                   psFechaFacturacion         VARCHAR2,
                                   psCodigoUsuario            VARCHAR2);
                                   
/**************************************************************************
Descripcion       : Realiza la determinacion de Ganadoras de Recomendacion
                   de las consultoras que han pasado pedido y se encuentran en gp4.
Fecha Creacion    : 05/10/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion : Fecha de Facturacion
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_DETER_GANAD_RECOM_FACTU(psCodigoPais               VARCHAR2,
                                         psCodigoMarca              VARCHAR2,
                                         psCodigoCanal              VARCHAR2,
                                         psCodigoPeriodo            VARCHAR2,
                                         psFechaFacturacion         VARCHAR2,
                                         psCodigoUsuario            VARCHAR2);
                                                                            
/**************************************************************************
Descripcion       : Realiza la determinacion de Ganadoras de Recomendacion
                   de las consultoras que que no han pasado pedido y al 
                   cierre de zona seran evaluadas si cumple las 
                    condiciones de recomendacion de los concursos
Fecha Creacion    : 05/10/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion : Fecha de Facturacion
  psCodigoZona     :  Codigo de zona
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_DETER_GANAD_RECOM_CZONA(psCodigoPais          VARCHAR2,
                                         psCodigoMarca         VARCHAR2,
                                         psCodigoCanal         VARCHAR2,
                                         psCodigoPeriodo       VARCHAR2,
                                         psFechaFacturacion    VARCHAR2,
                                         psCodigoZona          VARCHAR2,
                                         psCodigoUsuario       VARCHAR2);
                                         
/**************************************************************************
Descripcion       : Realiza la determinacion de Ganadoras de Recomendacion
                   de las consultoras que que no han pasado pedido y al 
                   cierre de periodo seran evaluadas si cumple las 
                    condiciones de recomendacion de los concursos
Fecha Creacion    : 05/10/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoZona     :  Codigo de zona
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_DETER_GANAD_RECOM_PERIO(psCodigoPais          VARCHAR2,
                                         psCodigoMarca         VARCHAR2,
                                         psCodigoCanal         VARCHAR2,
                                         psCodigoPeriodo       VARCHAR2,
                                         psCodigoUsuario       VARCHAR2);
                                                                                  
end INC_PKG_PROCE_RECOM;
/
create or replace package body INC_PKG_PROCE_RECOM is

/**************************************************************************
Descripcion       : Este proceso replica al proceso P448 de GP4 de SICC, que
                    es el proceso Determinacion Ganadoras Consultoras de 
                    Recomendacion

Fecha Creacion    : 08/09/2015
Parametros Entrada:
  psCodigoPais     :  Codigo Pais
  pnOidCliente     :  Oid Cliente
  pnOidSolicitud     :  Oid Solicitud
  psTipoCierre       : Tipo Cierre : F->Facturacion Diaria, Z->Zona,  P->Periodo 
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_DETER_GANAD_RECOM(psCodigoPais               VARCHAR2,
                                   pnOidCliente               NUMBER,
                                   pnOidSolicitud             NUMBER,
                                   psTipoCierre               VARCHAR2,
                                   psCodigoPeriodo            VARCHAR2,
                                   psFechaFacturacion         VARCHAR2,
                                   psCodigoUsuario            VARCHAR2)
IS
  lnOidPais             SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca            SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal            SEG_CANAL.OID_CANA%TYPE;

  lnOidPeriodo          PED_SOLIC_CABEC.PERD_OID_PERI%TYPE;
  lnOidConcurso         INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  lnOidBaseCalc         INC_CONCU_PARAM_GENER.BCAL_OID_BASE_CALC%TYPE;
  lsNombreConcurso      INC_CONCU_PARAM_GENER.VAL_NOMB%TYPE;
  lsNumeroConcurso      INC_CONCU_PARAM_GENER.NUM_CONC%TYPE;
  lnOidPeriodoIniEval   INC_CONCU_PARAM_GENER.PERD_OID_PERI_DESD%TYPE;
  lsCodPeriodoIniEval   SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoFinEval   SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoDesde     SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoHasta     SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnNumPeriEval         INC_CONCU_PARAM_CONSU.VAL_PERI_EVAL%TYPE;
  lnTipoEval            INC_CONCU_PARAM_CONSU.IND_TIPO_EVAL_MONT%TYPE;
  lnNumMiniPedi         INC_CONCU_PARAM_CONSU.NUM_MINI_PEDI%TYPE;
  lnNumMiniPediReco     INC_CONCU_PARAM_CONSU.NUM_MINI_PEDI_RECO%TYPE;
  lnIndRecoEfec         INC_CONCU_PARAM_CONSU.IND_RECO_EFEC%TYPE;
  lnImpMontMiniPedi     INC_CONCU_PARAM_CONSU.IMP_MONT_MINI_PEDI%TYPE;
  lnNumUnidMiniPedi     INC_CONCU_PARAM_CONSU.NUM_UNID_MINI_PEDI%TYPE;
  lnIndGenePuntReco     INC_CONCU_PARAM_CONSU.IND_GENE_PUNT_A_RECO%TYPE;
  lnOidConcursoReco     INC_CONCU_PARAM_CONSU.COPA_CONC_PUNT_RECO%TYPE;
  lnTipoPremiacion      INC_PARAM_GENER_PREMI.TPRM_OID_TIPO_PION%TYPE;
  lnIndObtenPuntos      INC_OBTEN_PUNTO.IND_COMU_OBTE%TYPE;
  lnOidMensaje          INC_OBTEN_PUNTO.MENS_OID_MENS%TYPE;
  lnIndPuntAcum         INC_OBTEN_PUNTO.VAL_PUNT_ACUM%TYPE;  
  lnFactorConversion    INC_OBTEN_PUNTO.VAL_FACT_CONV%TYPE;
  lnPuntosAsignar       INC_OBTEN_PUNTO.NUM_PUNT_ASIG%TYPE;
  lnIndDevolucion       INC_CONCU_PARAM_GENER.IND_DEVO%TYPE;
      
  lsCodPeriodoEval      SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoIniVinc   SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoFinVinc   SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnMontoTipo1          INC_MONTO_VENTA_RECOM.VAL_CANT_MONT_VENT%TYPE;
  lnMontoUnid2          INC_MONTO_VENTA_RECOM.VAL_CANT_MONT_VENT%TYPE;
  lnSumaTipo3           INC_MONTO_VENTA_RECOM.VAL_CANT_MONT_VENT%TYPE;
  lnPromTipo4           INC_MONTO_VENTA_RECOM.VAL_CANT_MONT_VENT%TYPE;
  lnMontoCampa          INC_CONCU_RECOM_CAMPA_MONTO.VAL_MONT%TYPE;

  lsCodigoCliente       MAE_CLIEN.COD_CLIE%TYPE;
  lnFactorConverReco    INC_OBTEN_PUNTO.VAL_FACT_CONV%TYPE;
  lnPuntosAsignarReco   INC_OBTEN_PUNTO.NUM_PUNT_ASIG%TYPE;
  lsCodMensaje          MSG_MENSA.COD_MENS%TYPE;
  lnIndNiveSele         INC_PARAM_NIVEL_PREMI.VAL_NIVE_SELE%TYPE;
  lnNumPuntProdExig		  INC_PARAM_NIVEL_PREMI.NUM_PUNT_PROD_EXIG%TYPE;
                
  lbMontoTipo1          BOOLEAN;
  lbMontoUnid2          BOOLEAN;
  lbSumaTipo3           BOOLEAN;
  lbPromTipo4           BOOLEAN;
  lnNumPeriVinc         NUMBER;
  lnSecuencia           NUMBER;
  lbPedidoValido        BOOLEAN;
  
  lnTotalPediSuma       NUMBER;
  lnTotalPediReco       NUMBER;
  lnSumaPediReco        NUMBER;
  lnSumaRetaReco        NUMBER;
  lnPromPediReco        NUMBER;
  
  lnTotalPediRete       NUMBER;
  lnSumaPediRete        NUMBER;
  lnSumaRetaRete        NUMBER;
  lnSumaUnidRete        NUMBER;
  lnRecomEfectivas      NUMBER;
  lnNumeroPuntosReco    NUMBER;
  lnNumeroPuntosRete    NUMBER;
  lnOidSecuencia        NUMBER;
  lnSaldoPuntosExig	  	NUMBER;
  lnTotalOcurrencias    NUMBER;
  lnBono                NUMBER;
  lsDescCuentCorri      VARCHAR2(200);

  CURSOR c_ConcursosOrdenCompra(oidCliente NUMBER, oidPeriodo NUMBER) IS                               
    SELECT OID_PARA_GRAL
      FROM inc_clien_recte       clie,
           MAE_CLIEN             mae_cli,
           inc_concu_param_gener concu,
           cra_perio             p1,
           cra_perio             desde,
           INC_CONCU_PARAM_CONSU CONSU
     WHERE clie.COPA_OID_PARA_GRAL = concu.OID_PARA_GRAL
       AND p1.OID_PERI = oidPeriodo
       AND concu.PERD_OID_PERI_DESD = desde.OID_PERI
       AND desde.FEC_INIC <= p1.FEC_INIC
       AND concu.IND_ACTI = 1
       AND clie.CLIE_OID_CLIE = mae_cli.OID_CLIE
       AND CONSU.COPA_OID_PARA_GRAL = CONCU.OID_PARA_GRAL
       AND ((psTipoCierre='P') OR (psTipoCierre!='P' AND CONSU.IND_PREM_CAMP_EFEC = 1))
       AND MAE_CLI.OID_CLIE = oidCliente;

  TYPE t_oidConcurso         IS TABLE OF INC_CONCU_PARAM_GENER.OID_PARA_GRAL%TYPE;
  v_oidConcurso              t_oidConcurso  := t_oidConcurso();
  
BEGIN

  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

  --Obtenemos el oid Periodo Proceso
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);


  --Recuperamos datos de la Consultora
  SELECT COD_CLIE
    INTO lsCodigoCliente
    FROM MAE_CLIEN
   WHERE OID_CLIE = pnOidCliente;

  --Recuperamos los Concursos de la Recomendante
  OPEN c_ConcursosOrdenCompra(pnOidCliente, lnOidPeriodo);
  LOOP
    FETCH c_ConcursosOrdenCompra INTO lnOidConcurso;
    EXIT WHEN c_ConcursosOrdenCompra%NOTFOUND;

    lbMontoTipo1 := FALSE;
    lbMontoUnid2 := FALSE;
    lbSumaTipo3 := FALSE;
    lbPromTipo4 := FALSE;
    lnRecomEfectivas := 0;
    
    --RECUPERAMOS DATOS DEL CONCURSO
    SELECT gen.VAL_NOMB,
           gen.NUM_CONC,
           con.PERD_OID_PERI_INIC_EVAL,
           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(con.PERD_OID_PERI_INIC_EVAL),
           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.PERD_OID_PERI_DESD),
           FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(gen.PERD_OID_PERI_HAST),
           con.VAL_PERI_EVAL,
           con.IND_TIPO_EVAL_MONT,
           NVL(con.NUM_MINI_PEDI,0),
           NVL(con.NUM_MINI_PEDI_RECO,0),
           NVL(con.IND_RECO_EFEC,0),
           NVL(con.IMP_MONT_MINI_PEDI,0),
           NVL(con.NUM_UNID_MINI_PEDI,0),
           NVL(con.IND_GENE_PUNT_A_RECO,0),
           con.COPA_CONC_PUNT_RECO,
           pre.TPRM_OID_TIPO_PION,
           NVL(obt.IND_COMU_OBTE, 0),
           obt.MENS_OID_MENS,
           NVL(obt.VAL_PUNT_ACUM, 0),
           NVL(VAL_FACT_CONV,1), 
           NVL(NUM_PUNT_ASIG,1),
           NVL(gen.IND_DEVO,0)
      INTO lsNombreConcurso,
           lsNumeroConcurso,
           lnOidPeriodoIniEval,
           lsCodPeriodoIniEval,
           lsCodPeriodoDesde,
           lsCodPeriodoHasta,
           lnNumPeriEval,
           lnTipoEval,
           lnNumMiniPedi,
           lnNumMiniPediReco,
           lnIndRecoEfec,
           lnImpMontMiniPedi,
           lnNumUnidMiniPedi,
           lnIndGenePuntReco,
           lnOidConcursoReco,
           lnTipoPremiacion,
           lnIndObtenPuntos,
           lnOidMensaje,
           lnIndPuntAcum,
           lnFactorConversion,
           lnPuntosAsignar,
           lnIndDevolucion
      FROM INC_CONCU_PARAM_GENER gen,
           INC_CONCU_PARAM_CONSU con,
           INC_PARAM_GENER_PREMI pre,
           INC_OBTEN_PUNTO obt
     WHERE gen.OID_PARA_GRAL = con.COPA_OID_PARA_GRAL
       AND gen.OID_PARA_GRAL = pre.COPA_OID_PARA_GRAL
       AND gen.OID_PARA_GRAL = obt.COPA_OID_PARA_GRAL
       AND gen.OID_PARA_GRAL = lnOidConcurso;

    --OBTENEMOS LA DESCRIPCION PARA EL INGRESO EN CUENTA CORRIENTE PUNTOS DE LA RECOMENDANTE  
    SELECT gen1.VAL_I18N || 'Puntaje del periodo' || mar.DES_MARC || ' ' ||
           gen2.VAL_I18N || ' ' || corp.COD_PERI
      INTO lsDescCuentCorri
      FROM V_GEN_I18N_SICC gen1,
           CRA_PERIO       cra,
           SEG_MARCA       mar,
           V_GEN_I18N_SICC gen2,
           SEG_PERIO_CORPO corp
     WHERE cra.OID_PERI = lnOidPeriodo
       AND cra.MARC_OID_MARC = mar.OID_MARC
       AND gen2.ATTR_NUM_ATRI = 1
       AND gen2.ATTR_ENTI = 'SEG_CANAL'
       AND gen2.VAL_OID = cra.CANA_OID_CANA
       AND gen2.IDIO_OID_IDIO = 1
       AND corp.OID_PERI = cra.PERI_OID_PERI
       AND gen1.ATTR_NUM_ATRI = 1
       AND gen1.ATTR_ENTI = 'INC_TIPO_MOVIM'
       AND gen1.VAL_OID = 1
       AND gen1.IDIO_OID_IDIO = 1;
    
    --CALCULAMOS PERIODO FIN EVALUACION
    lsCodPeriodoFinEval := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodPeriodoIniEval,
                                                                  lnOidPais,
                                                                  lnOidMarca,
                                                                  lnOidCanal,
                                                                  lnNumPeriEval);
                                                                  
    --OBTENEMOS EL PERIODO EVALUACION DE LA RECOMENDANTE                                                              
    lsCodPeriodoEval := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodo);                                                             
    
    --DBMS_OUTPUT.put_line('lsCodPeriodoIniEval : ' || lsCodPeriodoIniEval);
    --DBMS_OUTPUT.put_line('lsCodPeriodoFinEval : ' || lsCodPeriodoFinEval);
    --DBMS_OUTPUT.put_line('lnNumPeriEval : ' || lnNumPeriEval);
        
    --SI EL PERIODO DE LA RECOMENDATE ESTA EN EL RANGO DE EVALUACION DEL CONCURSO, SE PROCESA
    IF((lsCodPeriodoIniEval IS NULL) OR ((lsCodPeriodoEval >= lsCodPeriodoIniEval) AND
                            (lsCodPeriodoEval <= lsCodPeriodoFinEval)) ) THEN
    
      IF(lnIndRecoEfec = 1) THEN
      
        --CALCULAMOS EL NUMERO DE PERIODOS DE EVALUACION PARA LAS RECOMENDADAS
        lnNumPeriVinc := lnNumMiniPedi;
        IF(lnNumMiniPediReco > lnNumMiniPedi) THEN
          lnNumPeriVinc := lnNumMiniPediReco;
        END IF;
           
        --OBTENEMOS LOS TIPOS DE MONTO DE VENTA DE RECOMENDADAS
        FOR k IN (SELECT TMVR_OID_TIPO_MONT_VENT_RECO, 
                         VAL_CANT_MONT_VENT
                    FROM INC_MONTO_VENTA_RECOM
                   WHERE COPA_OID_PARA_GRAL = lnOidConcurso
                     AND VAL_CANT_MONT_VENT IS NOT NULL) LOOP
        
          IF(k.TMVR_OID_TIPO_MONT_VENT_RECO = 1) THEN
            lbMontoTipo1 := TRUE;
            lnMontoTipo1 := k.VAL_CANT_MONT_VENT;
          END IF;           

          IF(k.TMVR_OID_TIPO_MONT_VENT_RECO = 2) THEN
            lbMontoUnid2 := TRUE;                                
            lnMontoUnid2 := k.VAL_CANT_MONT_VENT;
          END IF;
          
          IF(k.TMVR_OID_TIPO_MONT_VENT_RECO = 3) THEN
            lbSumaTipo3 := TRUE; 
            lnSumaTipo3 := k.VAL_CANT_MONT_VENT;
          END IF;
          
          IF(k.TMVR_OID_TIPO_MONT_VENT_RECO = 4) THEN
            lbPromTipo4 := TRUE; 
            lnPromTipo4 := k.VAL_CANT_MONT_VENT;
          END IF;  
                           
        END LOOP;  
           
        --OBTENEMOS LA LISTA DE RECOMENDADOS      
        FOR x IN (SELECT A.OID_CLIE_REDO,
                         A.PANP_OID_PARA_NIVE_PREM,
                         A.NUM_PREM,
                         B.OID_CLIE_RETE,
                         A.CLIE_OID_CLIE,
                         FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(A.PERD_OID_PERI) COD_PERI
                    FROM INC_CLIEN_RECDO A,
                         INC_CLIEN_RECTE B
                   WHERE A.CLR3_OID_CLIE_RETE = B.OID_CLIE_RETE
                     AND B.COPA_OID_PARA_GRAL = lnOidConcurso
                     AND B.CLIE_OID_CLIE = pnOidCliente
                     AND (IND_EFEC <> 1 OR IND_EFEC IS NULL)
                     AND (A.IND_EVAL <> 1 OR A.IND_EVAL IS NULL)) LOOP
           
          --CALCULAMOS PERIODO INICIO Y FIN DE VINCULO DE LA RECOMENDADA
          lsCodPeriodoIniVinc := x.COD_PERI;
          lsCodPeriodoFinVinc := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(x.COD_PERI,
                                                                      lnOidPais,
                                                                      lnOidMarca,
                                                                      lnOidCanal,
                                                                      lnNumPeriVinc - 1);  
                                                                      
          --DBMS_OUTPUT.put_line('lsCodPeriodoIniVinc : ' || lsCodPeriodoIniVinc);
          --DBMS_OUTPUT.put_line('lsCodPeriodoFinVinc : ' || lsCodPeriodoFinVinc);                                                            
        
          lnSumaPediReco := 0;
          lnSumaRetaReco := 0;
          lnTotalPediReco := 0;
          lnTotalPediSuma := 0;
          
          --OBTENEMOS LA LISTA DE PEDIDOS x CADA RECOMENDADO
          FOR y IN (SELECT NVL(pcr.IMP_MONT_PEDI,0) IMP_MONT_PEDI,
                           NVL(pcr.NUM_UNID_PEDI,0) NUM_UNID_PEDI,
--                           NVL(pcr.IMP_MONT_RETL,0) - NVL(DECODE(lnIndDevolucion,1,pcr.IMP_MONT_DEVU,0),0) IMP_MONT_RETL,
                           NVL(pcr.IMP_MONT_RETL,0) IMP_MONT_RETL,
                           cor.COD_PERI
                      FROM INC_PEDID_CONCU_RECOM pcr,
                           INC_CLIEN_RECTE       recte,
                           inc_clien_recdo       recdo,
                           cra_perio             p1,
                           seg_perio_corpo       cor
                     WHERE pcr.COPA_OID_PARA_GRAL = lnOidConcurso
                       AND pcr.CLR3_OID_CLIE_RETE = recte.OID_CLIE_RETE
                       AND PCR.PERD_OID_PERI = P1.OID_PERI
                       AND P1.PERI_OID_PERI = cor.OID_PERI
                       AND recte.CLIE_OID_CLIE = pnOidCliente
                       AND pcr.CLRE_OID_CLIE_REDO = recdo.OID_CLIE_REDO
                       AND recdo.OID_CLIE_REDO = x.OID_CLIE_REDO) LOOP
             
               
            --VALIDAMOS QUE EL PEDIDO SE ENCUENTRE EN EL RANGO DE VINCULO DE LA RECOMENDADA           
            IF((y.COD_PERI >= lsCodPeriodoIniVinc) AND (y.COD_PERI <= lsCodPeriodoFinVinc)) THEN
              lbPedidoValido := TRUE;
                   
              IF((lnTipoEval IS NULL) OR (lnTipoEval = 1)) THEN
                IF(lbMontoTipo1) THEN
                  IF(NOT ((y.IMP_MONT_PEDI > 0) AND (y.IMP_MONT_PEDI + y.IMP_MONT_RETL >= lnMontoTipo1))) THEN
                    lbPedidoValido := FALSE;
                  END IF;
                END IF;
                
                IF(lbMontoUnid2) THEN
                  IF(NOT (y.NUM_UNID_PEDI >= lnMontoUnid2)) THEN
                    lbPedidoValido := FALSE;
                  END IF;
                END IF;  
                
                IF(lbPedidoValido) THEN
                  lnTotalPediReco := lnTotalPediReco + 1;
                END IF;
                
                lnSumaPediReco := lnSumaPediReco + y.IMP_MONT_PEDI;
                lnSumaRetaReco := lnSumaRetaReco + y.IMP_MONT_RETL;
                lnTotalPediSuma := lnTotalPediSuma + 1;
              END IF;
                
              --VALIDAMOS LOS PEDIDOS DE LAS RECOMENDADAS, CON EL MONTO DE LA TABLA INC_CONCU_RECOM_CAMPA_MONTO
              --EN BASE AL PERIODO QUE PASO PEDIDO
              IF(lnTipoEval = 2) THEN
                lnSecuencia := GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais, lsCodPeriodoIniVinc, y.COD_PERI)+1;
                    
                BEGIN
                  SELECT REC.VAL_MONT 
                    INTO lnMontoCampa
                    FROM INC_CONCU_RECOM_CAMPA_MONTO REC
                   WHERE REC.COD_PAIS = psCodigoPais
                     AND REC.NUM_CONC = lsNumeroConcurso
                     AND REC.IND_TIPO = '2'
                     AND REC.SEC_CAMP = lnSecuencia;  
                EXCEPTION
                  WHEN OTHERS THEN
                    lnMontoCampa := NULL;
                END;
                    
                IF(lnMontoCampa IS NOT NULL) THEN
                  IF((y.IMP_MONT_PEDI > 0) AND (y.IMP_MONT_PEDI + y.IMP_MONT_RETL >= lnMontoCampa)) THEN
                    lbPedidoValido := TRUE;
                  ELSE
                    lbPedidoValido := FALSE;
                  END IF;
                ELSE
                  lbPedidoValido := TRUE;  
                END IF;      
                
                IF(lbPedidoValido) THEN
                  lnTotalPediReco := lnTotalPediReco + 1;
                END IF;
                 
              END IF;   
                
            END IF;         
              
          END LOOP;
          
          IF((lnTipoEval IS NULL) OR (lnTipoEval = 1)) THEN
            IF(lbSumaTipo3) THEN
              IF(NOT ((lnSumaPediReco > 0) AND (lnSumaPediReco + lnSumaRetaReco >= lnSumaTipo3))) THEN
                lnTotalPediReco := 0;
              END IF;
            END IF;
                
            IF(lbPromTipo4) THEN
              IF(lnTotalPediSuma > 0) THEN
                lnPromPediReco :=  ROUND((lnSumaPediReco + lnSumaRetaReco) / lnTotalPediSuma, 2);
              ELSE
                lnPromPediReco := 0;
              END IF;
              
              IF(NOT (lnPromPediReco >= lnPromTipo4)) THEN
                lnTotalPediReco := 0;
              END IF;
            END IF;      
          END IF;
          
          lnTotalPediRete := 0;
          lnTotalPediSuma := 0;
          lnSumaPediRete := 0;
          lnSumaRetaRete := 0;
          lnSumaUnidRete := 0;
          
          --OBTENEMOS LA LISTA DE PEDIDOS DE LA RECOMENDANTE
          FOR y IN (SELECT NVL(IMP_MONT_PEDI,0) IMP_MONT_PEDI,
                           NVL(NUM_UNID_PEDI,0) NUM_UNID_PEDI,
--                           NVL(IMP_MONT_RETL,0) - NVL(DECODE(lnIndDevolucion,1,IMP_MONT_DEVU,0),0) IMP_MONT_RETL,
                           NVL(IMP_MONT_RETL,0) IMP_MONT_RETL,
                           cor.COD_PERI
                      FROM INC_PEDID_CONCU_RECOM pcr, 
                           INC_CLIEN_RECTE recte, 
                           cra_perio p1,
                           seg_perio_corpo cor
                     WHERE pcr.COPA_OID_PARA_GRAL = lnOidConcurso
                       AND pcr.CLR3_OID_CLIE_RETE = recte.OID_CLIE_RETE
                       AND recte.CLIE_OID_CLIE = pnOidCliente
                       AND pcr.CLRE_OID_CLIE_REDO IS NULL
                       AND PCR.PERD_OID_PERI = P1.OID_PERI
                       AND P1.PERI_OID_PERI = cor.OID_PERI) LOOP
             
               
            --VALIDAMOS QUE EL PEDIDO SE ENCUENTRE EN EL RANGO DE VINCULO DE LA RECOMENDADA           
            IF((y.COD_PERI >= lsCodPeriodoIniVinc) AND (y.COD_PERI <= lsCodPeriodoFinVinc)) THEN
              
              --PRIMER CASO: TIPO EVALUACION = NULL     
              IF((lnTipoEval IS NULL)) THEN
                IF(lbMontoTipo1) THEN
                  IF((y.IMP_MONT_PEDI > 0) AND (y.IMP_MONT_PEDI + y.IMP_MONT_RETL >= lnImpMontMiniPedi)
                                           AND (y.NUM_UNID_PEDI >= lnNumUnidMiniPedi)) THEN
                    lnTotalPediRete := lnTotalPediRete + 1;
                  END IF;
                END IF;
                
              END IF;
              
              --SEGUNDO CASO: TIPO EVALUACION = 1    
              IF(lnTipoEval = 1) THEN
                lnSumaPediRete := lnSumaPediRete + y.IMP_MONT_PEDI;
                lnSumaRetaRete := lnSumaRetaRete + y.IMP_MONT_RETL;
                lnSumaUnidRete := lnSumaUnidRete + y.NUM_UNID_PEDI;
                
                lnTotalPediSuma := lnTotalPediSuma + 1;
              END IF;
                
              --TERCER CASO: TIPO EVALUACION = 2  
              IF(lnTipoEval = 2) THEN
                lnSecuencia := GEN_PKG_GENER.GEN_FN_DEVUE_DIFER_PERIO_PAIS1(psCodigoPais, lsCodPeriodoIniVinc, y.COD_PERI)+1;
                    
                BEGIN
                  SELECT REC.VAL_MONT 
                    INTO lnMontoCampa
                    FROM INC_CONCU_RECOM_CAMPA_MONTO REC
                   WHERE REC.COD_PAIS = psCodigoPais
                     AND REC.NUM_CONC = lsNumeroConcurso
                     AND REC.IND_TIPO = '1'
                     AND REC.SEC_CAMP = lnSecuencia;  
                EXCEPTION
                  WHEN OTHERS THEN
                    lnMontoCampa := NULL;
                END;
                    
                IF(lnMontoCampa IS NOT NULL) THEN
                  IF((y.IMP_MONT_PEDI > 0) AND (y.IMP_MONT_PEDI + y.IMP_MONT_RETL >= lnMontoCampa)) THEN
                    lnTotalPediRete := lnTotalPediRete + 1;
                  END IF;
                ELSE
                  lnTotalPediRete := lnTotalPediRete + 1;
                END IF;      
                 
              END IF;   
                
            END IF;         
              
          END LOOP;
          
          --SI CASO: TIPO EVALUACION = 1, VALIDAMOS CON LA SUMA DE LOS MONTOS Y UNIDADES  
          IF(lnTipoEval = 1) THEN
            IF((lnSumaPediRete > 0) AND (lnSumaPediRete + lnSumaRetaRete >= lnImpMontMiniPedi)
                               AND (lnSumaUnidRete >= lnNumUnidMiniPedi)) THEN
              lnTotalPediRete := lnTotalPediSuma;
            END IF;                   
          END IF;
          
          
          --VALIDAMOS SI SE REALIZO LA RECOMENDACION EFECTIVA
          IF((lnTotalPediRete >= lnNumMiniPedi) AND (lnTotalPediReco >= lnNumMiniPediReco)) THEN
            lnRecomEfectivas := lnRecomEfectivas + 1;
            --DBMS_OUTPUT.put_line('RECOMENDACION EFECTIVA');
            
            --VALIDAMOS SI SE TIENE QUE PUNTUAR A LA RECOMENDADO
            IF(lnIndGenePuntReco = 1) THEN
              SELECT NVL(VAL_FACT_CONV,1), 
                     NVL(NUM_PUNT_ASIG,1)
                INTO lnFactorConverReco,
                     lnPuntosAsignarReco
                FROM INC_OBTEN_PUNTO 
               WHERE COPA_OID_PARA_GRAL = lnOidConcursoReco;
              
              lnNumeroPuntosReco := ROUND(lnPuntosAsignarReco / lnFactorConverReco);
              
              lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
              --Insertamos el puntaje en la Entidad Cuenta Corriente Puntos
              INSERT INTO INC_CUENT_CORRI_PUNTO
                (OID_CUEN_CORR_PUNT,
                 NUM_MOVI,
                 NUM_PUNT,
                 NUM_PUNT_EXIG,
                 FEC_MOVI,
                 COPA_OID_PARA_GRAL,
                 CLIE_OID_CLIE,
                 PERD_OID_PERI,
                 TMOV_OID_TIPO_MOVI,
                 FEC_ULTI_ACTU,
                 VAL_DESC,
                 DES_MOTI)
              VALUES
                (lnOidSecuencia,
                 lnOidSecuencia,
                 lnNumeroPuntosReco,
                 0,
                 TRUNC(SYSDATE),
                 lnOidConcursoReco,
                 pnOidCliente,
                 lnOidPeriodo,
                 1,  --ABONO
                 SYSDATE,
                 'Abono Cliente Recomendante  ' || lsCodigoCliente,
                 'Recomendaciones Efectivas');
            
              SELECT COUNT(1)
                INTO lnTotalOcurrencias
                FROM INC_CANDI_GANAD
               WHERE COPA_OID_PARA_GRAL = lnOidConcursoReco
                 AND CLIE_OID_CLIE = x.CLIE_OID_CLIE
                 AND PERD_OID_PERI = lnOidPeriodo;
      
              IF(lnTotalOcurrencias = 0) THEN
                INSERT INTO INC_CANDI_GANAD
                 (OID_CAND_GANA,
                  IND_META_SUPE,
                  VAL_REQU_PREM_SUPE,
                  PERD_OID_PERI,
                  COPA_OID_PARA_GRAL,
                  BINC_OID_BASE_INCU,
                  PERD_OID_PERI_EVAL,
                  CLIE_OID_CLIE,
                  FEC_ULTI_ACTU)
                VALUES
                  (INC_CAGA_SEQ.nextval,
                   0,
                   0,
                   lnOidPeriodo,
                   lnOidConcursoReco,
                   NULL,
                   NULL,
                   x.CLIE_OID_CLIE,
                   SYSDATE);
              ELSE
                UPDATE INC_CANDI_GANAD     
                   SET VAL_REQU_PREM_SUPE = 0
                 WHERE COPA_OID_PARA_GRAL = lnOidConcursoReco
                   AND CLIE_OID_CLIE = x.CLIE_OID_CLIE
                   AND PERD_OID_PERI = lnOidPeriodo;
              END IF;
                             
            END IF;
            
            IF(x.PANP_OID_PARA_NIVE_PREM IS NOT NULL) THEN
            
              --Verificamos si el Nivel es Selectivo
              SELECT NVL(VAL_NIVE_SELE, 0),
                     NUM_PUNT_PROD_EXIG 
                INTO lnIndNiveSele,
                     lnNumPuntProdExig
                FROM INC_PARAM_NIVEL_PREMI
               WHERE OID_PARA_NIVE_PREM = x.PANP_OID_PARA_NIVE_PREM;
               
              --RECUPERAMOS EL VALOR de Saldo Puntos y Saldo Puntos Exigidos
              IF(lnIndPuntAcum = 0) THEN
                SELECT SUM (NUM_PUNT_EXIG)
                  INTO lnSaldoPuntosExig
                  FROM INC_CUENT_CORRI_PUNTO 
                 WHERE CLIE_OID_CLIE = pnOidCliente
                   AND COPA_OID_PARA_GRAL = lnOidConcurso
                   AND PERD_OID_PERI = lnOidPeriodo;
              ELSE
                SELECT SUM (NUM_PUNT_EXIG)
                  INTO lnSaldoPuntosExig
                  FROM INC_CUENT_CORRI_PUNTO 
                 WHERE CLIE_OID_CLIE = pnOidCliente
                   AND COPA_OID_PARA_GRAL = lnOidConcurso;
              END IF; 

              --VALIDAMOS SI CUMPLE CON EL PUNTAJE DE PRODUCTOS EXIGIDOS DEL NIVEL
              IF((lnNumPuntProdExig IS NULL) OR ((lnNumPuntProdExig IS NOT NULL) AND
                   (lnSaldoPuntosExig >= lnNumPuntProdExig)) )  THEN

                --OBTENEMOS EL CODIGO DE MENSAJE A ENVIAR EN EL BUZON DE MENSAJES
                BEGIN
                  SELECT COD_MENS 
                    INTO lsCodMensaje
                    FROM INC_OBTEN_PUNTO, 
                         MSG_MENSA 
                   WHERE COPA_OID_PARA_GRAL = lnOidConcurso
                     AND MENS_OID_MENS = OID_MENS;
                EXCEPTION
                  WHEN OTHERS THEN
                    lsCodMensaje := NULL;     
                END; 
                
                IF((lnIndObtenPuntos = 1) AND (lsCodMensaje IS NOT NULL)) THEN
                  --INSERTAMOS REGISTRO EN BUZON DE MENSAJES
                  INSERT INTO MSG_BUZON_MENSA
                    (OID_BUZO_MENS,
                     NUM_SECU,
                     DATO_VARI_10,
                     DATO_VARI_11,
                     DATO_VARI_12,
                     DATO_VARI_13,
                     DATO_VARI_14,
                     DATO_VARI_15,
                     DATO_VARI_16,
                     DATO_VARI_17,
                     DATO_VARI_18,
                     DATO_VARI_19,
                     DATO_VARI_20,
                     IND_ESTA_MENS,
                     CLIE_OID_CLIE,
                     MENS_OID_MENS,
                     MODU_OID_MODU_ORIG,
                     VAL_NOM1_CLIE,
                     VAL_NOM2_CLIE,
                     VAL_APE1_CLIE,
                     VAL_APE2_CLIE,
                     VAL_APEL_CASA_CLIE,
                     DATO_VARI_01,
                     DATO_VARI_02,
                     DATO_VARI_03,
                     DATO_VARI_04,
                     DATO_VARI_05,
                     DATO_VARI_06,
                     DATO_VARI_07,
                     DATO_VARI_08,
                     DATO_VARI_09,
                     NUM_LOTE_IMPR,
                     FEC_GRAB,
                     FEC_IMPR,
                     IND_LIST_CONS,
                     PERI_OID_PERI,
                     IND_ACTI)
                    SELECT MSG_BUME_SEQ.NEXTVAL,
                           MSG_BUM2_SEQ.NEXTVAL,
                           lnRecomEfectivas,
                           lsNumeroConcurso, --datoVariable11
                           NULL, --datoVariable12
                           NULL, --datoVariable13
                           NULL, --datoVariable14
                           NULL, --datoVariable15
                           lsNombreConcurso, --datoVariable16
                           NULL, --datoVariable17,
                           NULL, --datoVariable18
                           NULL, --datoVariable19
                           NULL, --datoVariable20
                           NULL,
                           pnOidCliente,
                           lnOidMensaje,
                           13, --MODULO INCENTIVOS
                           NULL,
                           NULL,
                           NULL,
                           NULL,
                           NULL,
                           (SELECT VAL_NOM1 || ' ' || VAL_NOM2 || ' ' || VAL_APE1 || ' ' ||
                                   VAL_APE2
                              FROM MAE_CLIEN
                             WHERE OID_CLIE = pnOidCliente), --datoVariable01
                           lsCodPeriodoDesde, --datoVariable02
                           lsCodPeriodoHasta, --datoVariable03
                           (SELECT VAL_NOM1 || ' ' || VAL_NOM2 || ' ' || VAL_APE1 || ' ' ||
                                   VAL_APE2
                              FROM MAE_CLIEN
                             WHERE OID_CLIE = x.CLIE_OID_CLIE), --datoVariable04
                           NULL, --datoVariable05
                           NULL, --datoVariable06
                           x.COD_PERI, --datoVariable07
                           (SELECT SUM (IMP_MONT_PEDI) 
                              FROM INC_PEDID_CONCU_RECOM ped,  INC_CLIEN_RECTE cliR 
                             WHERE cliR.COPA_OID_PARA_GRAL = ped.COPA_OID_PARA_GRAL
                               AND cliR.CLIE_OID_CLIE =  pnOidCliente
                               AND ped.COPA_OID_PARA_GRAL = lnOidConcurso
                               AND ped.CLR3_OID_CLIE_RETE = cliR.OID_CLIE_RETE 
                               AND ped.CLRE_OID_CLIE_REDO = x.OID_CLIE_REDO), --datoVariable08
                           NULL, --datoVariable09
                           NULL,
                           SYSDATE,
                           NULL,
                           0,
                           NULL,
                           1
                      FROM DUAL;
                END IF;
                  
                --ACTUALIZAMOS AL RECOMENDADO COMO EFECTIVA  
                UPDATE INC_CLIEN_RECDO
                   SET IND_EFEC = 1,
                       FEC_EFEC = SYSDATE,
                       IND_EVAL = 1
                 WHERE OID_CLIE_REDO = x.OID_CLIE_REDO;
                 
              END IF;
              
              --Insertamos en Premios Elegidos, para el Tipo Premiacion = Por Niveles y que sea Selectivo
              IF((lnTipoPremiacion = 2) AND (lnIndNiveSele = 1)) THEN
                INSERT INTO INC_PREMI_ELEGI
                  (OID_PREM_ELEG,
                   NUM_PREM,
                   CLIE_OID_CLIE,
                   COPA_OID_PARA_GRAL,
                   PANP_OID_PARA_NIVE_PREM,
                   FEC_SIST)
                VALUES
                  (INC_PREL_SEQ.NEXTVAL,
                   x.NUM_PREM,
                   pnOidCliente,
                   lnOidConcurso,
                   x.PANP_OID_PARA_NIVE_PREM,
                   SYSDATE);
                  
              END IF;
            
            ELSE
 
              --OBTENEMOS EL CODIGO DE MENSAJE A ENVIAR EN EL BUZON DE MENSAJES
              BEGIN
                SELECT COD_MENS 
                  INTO lsCodMensaje
                  FROM INC_OBTEN_PUNTO, 
                       MSG_MENSA 
                 WHERE COPA_OID_PARA_GRAL = lnOidConcurso
                   AND MENS_OID_MENS = OID_MENS;
              EXCEPTION
                WHEN OTHERS THEN
                  lsCodMensaje := NULL;     
              END; 
                
              IF((lnIndObtenPuntos = 1) AND (lsCodMensaje IS NOT NULL)) THEN
                --INSERTAMOS REGISTRO EN BUZON DE MENSAJES
                INSERT INTO MSG_BUZON_MENSA
                  (OID_BUZO_MENS,
                   NUM_SECU,
                   DATO_VARI_10,
                   DATO_VARI_11,
                   DATO_VARI_12,
                   DATO_VARI_13,
                   DATO_VARI_14,
                   DATO_VARI_15,
                   DATO_VARI_16,
                   DATO_VARI_17,
                   DATO_VARI_18,
                   DATO_VARI_19,
                   DATO_VARI_20,
                   IND_ESTA_MENS,
                   CLIE_OID_CLIE,
                   MENS_OID_MENS,
                   MODU_OID_MODU_ORIG,
                   VAL_NOM1_CLIE,
                   VAL_NOM2_CLIE,
                   VAL_APE1_CLIE,
                   VAL_APE2_CLIE,
                   VAL_APEL_CASA_CLIE,
                   DATO_VARI_01,
                   DATO_VARI_02,
                   DATO_VARI_03,
                   DATO_VARI_04,
                   DATO_VARI_05,
                   DATO_VARI_06,
                   DATO_VARI_07,
                   DATO_VARI_08,
                   DATO_VARI_09,
                   NUM_LOTE_IMPR,
                   FEC_GRAB,
                   FEC_IMPR,
                   IND_LIST_CONS,
                   PERI_OID_PERI,
                   IND_ACTI)
                  SELECT MSG_BUME_SEQ.NEXTVAL,
                         MSG_BUM2_SEQ.NEXTVAL,
                         lnRecomEfectivas,
                         lsNumeroConcurso, --datoVariable11
                         NULL, --datoVariable12
                         NULL, --datoVariable13
                         NULL, --datoVariable14
                         NULL, --datoVariable15
                         lsNombreConcurso, --datoVariable16
                         NULL, --datoVariable17,
                         NULL, --datoVariable18
                         NULL, --datoVariable19
                         NULL, --datoVariable20
                         NULL,
                         pnOidCliente,
                         lnOidMensaje,
                         13, --MODULO INCENTIVOS
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         NULL,
                         (SELECT VAL_NOM1 || ' ' || VAL_NOM2 || ' ' || VAL_APE1 || ' ' ||
                                 VAL_APE2
                            FROM MAE_CLIEN
                           WHERE OID_CLIE = pnOidCliente), --datoVariable01
                         lsCodPeriodoDesde, --datoVariable02
                         lsCodPeriodoHasta, --datoVariable03
                         (SELECT VAL_NOM1 || ' ' || VAL_NOM2 || ' ' || VAL_APE1 || ' ' ||
                                 VAL_APE2
                            FROM MAE_CLIEN
                           WHERE OID_CLIE = x.CLIE_OID_CLIE), --datoVariable04
                         NULL, --datoVariable05
                         NULL, --datoVariable06
                         x.COD_PERI, --datoVariable07
                         (SELECT SUM (IMP_MONT_PEDI) 
                            FROM INC_PEDID_CONCU_RECOM ped,  INC_CLIEN_RECTE cliR 
                           WHERE cliR.COPA_OID_PARA_GRAL = ped.COPA_OID_PARA_GRAL
                             AND cliR.CLIE_OID_CLIE =  pnOidCliente
                             AND ped.COPA_OID_PARA_GRAL = lnOidConcurso
                             AND ped.CLR3_OID_CLIE_RETE = cliR.OID_CLIE_RETE 
                             AND ped.CLRE_OID_CLIE_REDO = x.OID_CLIE_REDO), --datoVariable08
                         NULL, --datoVariable09
                         NULL,
                         SYSDATE,
                         NULL,
                         0,
                         NULL,
                         1
                    FROM DUAL;
              END IF;
                  
              --ACTUALIZAMOS AL RECOMENDADO COMO EFECTIVA  
              UPDATE INC_CLIEN_RECDO
                 SET IND_EFEC = 1,
                     FEC_EFEC = SYSDATE,
                     IND_EVAL = 1
               WHERE OID_CLIE_REDO = x.OID_CLIE_REDO;
              
            END IF;
                         
          END IF;
                    
        END LOOP;
        
      ELSE
        SELECT COUNT(1)
          INTO lnRecomEfectivas
          FROM INC_CLIEN_RECDO A,
               INC_CLIEN_RECTE B
         WHERE A.CLR3_OID_CLIE_RETE = B.OID_CLIE_RETE
           AND B.COPA_OID_PARA_GRAL = lnOidConcurso
           AND B.CLIE_OID_CLIE = pnOidCliente
           AND (IND_EFEC <> 1 OR IND_EFEC IS NULL)
           AND (A.IND_EVAL <> 1 OR A.IND_EVAL IS NULL);  
      
      END IF;
      
      --VALIDAMOS LAS RECOMENDACIONES EFECTIVAS
      IF(lnRecomEfectivas > 0) THEN
        lnNumeroPuntosRete := ROUND((lnRecomEfectivas / lnFactorConversion) * lnPuntosAsignar);
            
        --Recuperamos monto de Bonificacion configurada para el Concurso
        BEGIN
          SELECT BON.VAL_BONR
            INTO lnBono
            FROM INC_BONIF_EFECT_CAMPA BON,
                 SEG_PAIS              PAI,
                 CRA_PERIO             CRA,
                 SEG_PERIO_CORPO       COR
           WHERE PAI.OID_PAIS = lnOidPais
             AND CRA.OID_PERI = lnOidPeriodo
             AND BON.COD_PAIS = PAI.COD_PAIS
             AND COR.OID_PERI = CRA.PERI_OID_PERI
             AND BON.COD_PERI = COR.COD_PERI
             AND BON.NUM_CONC = lsNumeroConcurso;
        EXCEPTION
          WHEN OTHERS THEN
            lnBono := 0;     
        END;
            
        IF(lnBono > 0) THEN
          lnNumeroPuntosRete := lnNumeroPuntosRete + ROUND(lnBono * lnNumeroPuntosRete);
        END IF;
            
        lnOidSecuencia := INC_CUCP_SEQ.NEXTVAL;
        --Insertamos el puntaje en la Entidad Cuenta Corriente Puntos
        INSERT INTO INC_CUENT_CORRI_PUNTO
          (OID_CUEN_CORR_PUNT,
           NUM_MOVI,
           NUM_PUNT,
           NUM_PUNT_EXIG,
           FEC_MOVI,
           COPA_OID_PARA_GRAL,
           CLIE_OID_CLIE,
           PERD_OID_PERI,
           TMOV_OID_TIPO_MOVI,
           FEC_ULTI_ACTU,
           VAL_DESC,
           DES_MOTI)
        VALUES
          (lnOidSecuencia,
           lnOidSecuencia,
           lnNumeroPuntosRete,
           0,
           TRUNC(SYSDATE),
           lnOidConcurso,
           pnOidCliente,
           lnOidPeriodo,
           1,  --ABONO
           SYSDATE,
           lsDescCuentCorri,
           'Recomendaciones Efectivas');
            
        SELECT COUNT(1)
          INTO lnTotalOcurrencias
          FROM INC_CANDI_GANAD
         WHERE COPA_OID_PARA_GRAL = lnOidConcurso
           AND CLIE_OID_CLIE = pnOidCliente
           AND PERD_OID_PERI = lnOidPeriodo;
      
        IF(lnTotalOcurrencias = 0) THEN
          INSERT INTO INC_CANDI_GANAD
           (OID_CAND_GANA,
            IND_META_SUPE,
            VAL_REQU_PREM_SUPE,
            PERD_OID_PERI,
            COPA_OID_PARA_GRAL,
            BINC_OID_BASE_INCU,
            PERD_OID_PERI_EVAL,
            CLIE_OID_CLIE,
            FEC_ULTI_ACTU)
          VALUES
            (INC_CAGA_SEQ.nextval,
             0,
             0,
             lnOidPeriodo,
             lnOidConcurso,
             NULL,
             NULL,
             pnOidCliente,
             SYSDATE);
        ELSE
          UPDATE INC_CANDI_GANAD     
             SET VAL_REQU_PREM_SUPE = 0
           WHERE COPA_OID_PARA_GRAL = lnOidConcurso
             AND CLIE_OID_CLIE = pnOidCliente
             AND PERD_OID_PERI = lnOidPeriodo;
        END IF;       
               
      END IF;
            
    END IF;    

  END LOOP;
  CLOSE c_ConcursosOrdenCompra;

/*EXCEPTION
  WHEN OTHERS THEN
    NULL;*/

END INC_PR_DETER_GANAD_RECOM;

/**************************************************************************
Descripcion       : Realiza la determinacion de Ganadoras de Recomendacion
                   de las consultoras que han pasado pedido y se encuentran en gp4.
Fecha Creacion    : 05/10/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion : Fecha de Facturacion
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE INC_PR_DETER_GANAD_RECOM_FACTU(psCodigoPais               VARCHAR2,
                                         psCodigoMarca              VARCHAR2,
                                         psCodigoCanal              VARCHAR2,
                                         psCodigoPeriodo            VARCHAR2,
                                         psFechaFacturacion         VARCHAR2,
                                         psCodigoUsuario            VARCHAR2)
IS
  CURSOR c_pedidos(pnOidPeriodo cra_perio.oid_peri%TYPE) IS
    SELECT cab.CLIE_OID_CLIE,
           cab.OID_SOLI_CABE
      FROM PED_SOLIC_CABEC cab,
           PED_TIPO_SOLIC_PAIS tsp,
           PED_TIPO_SOLIC sol
     WHERE cab.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
       AND tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
       AND sol.COD_TIPO_SOLI = 'SOC'
       AND cab.GRPR_OID_GRUP_PROC = 4
       AND PERD_OID_PERI = pnOidPeriodo;

  --se define un tipo de dato tipo Tabla de Registros de los pedidos
  TYPE RegTab IS TABLE OF c_pedidos%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  pedidoReg RegTab;

  lnOidMarca     SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal     SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo   CRA_PERIO.OID_PERI%TYPE;
BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);
  OPEN c_pedidos(lnOidPeriodo);
      LOOP
      FETCH c_pedidos BULK COLLECT INTO pedidoReg LIMIT W_FILAS;

        IF pedidoReg.COUNT > 0 THEN
          FOR x IN pedidoReg.FIRST .. pedidoReg.LAST LOOP
              --Proceso de Premiacion de las Ordenes de Compra
						  INC_PR_DETER_GANAD_RECOM(psCodigoPais,
                                       pedidoReg(x).CLIE_OID_CLIE,
                                       pedidoReg(x).OID_SOLI_CABE,
                                       TIPO_FACTURACION,
                                       psCodigoPeriodo,
                                       psFechaFacturacion,
                                       psCodigoUsuario);
          END LOOP;
        END IF;

      EXIT WHEN c_pedidos%NOTFOUND;
      END LOOP;
    CLOSE c_pedidos;

/*EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
     RAISE_APPLICATION_ERROR(-20123,'ERROR INC_PR_DETER_GANAD_RECOM_FACTU: (' ||ln_sqlcode || ')' || ls_sqlerrm);
*/     
END INC_PR_DETER_GANAD_RECOM_FACTU;

/**************************************************************************
Descripcion       : Realiza la determinacion de Ganadoras de Recomendacion
                   de las consultoras que que no han pasado pedido y al 
                   cierre de zona seran evaluadas si cumple las 
                    condiciones de recomendacion de los concursos
Fecha Creacion    : 05/10/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoPeriodo  :  Codigo de periodo
  psFechaFacturacion : Fecha de Facturacion
  psCodigoZona     :  Codigo de zona
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_DETER_GANAD_RECOM_CZONA(psCodigoPais          VARCHAR2,
                                         psCodigoMarca         VARCHAR2,
                                         psCodigoCanal         VARCHAR2,
                                         psCodigoPeriodo       VARCHAR2,
                                         psFechaFacturacion    VARCHAR2,
                                         psCodigoZona          VARCHAR2,
                                         psCodigoUsuario       VARCHAR2) IS
  lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;
  lnOidZona       ZON_ZONA.OID_ZONA%TYPE;
  lnOcurrencias   NUMBER;

  CURSOR c_Pedidos(oidZona NUMBER, oidPeriodo NUMBER) IS
    SELECT DISTINCT MAE_CLI.OID_CLIE
      FROM inc_clien_recte       clie,
           MAE_CLIEN             mae_cli,
           inc_concu_param_gener concu,
           cra_perio             p1,
           cra_perio             desde,
           INC_CONCU_PARAM_CONSU CONSU,
           MAE_CLIEN_UNIDA_ADMIN MCUA,
           ZON_TERRI_ADMIN       ZTA,
           ZON_SECCI             ZS
     WHERE clie.copa_oid_para_gral = concu.oid_para_gral
       AND p1.OID_PERI = oidPeriodo
       AND concu.perd_oid_peri_desd = desde.OID_PERI
       AND desde.FEC_INIC <= p1.FEC_INIC
       AND concu.ind_acti = 1
       AND clie.CLIE_OID_CLIE = mae_cli.OID_CLIE
       AND CONSU.COPA_OID_PARA_GRAL = CONCU.OID_PARA_GRAL
       AND CONSU.IND_PREM_CAMP_EFEC = 1
       AND MCUA.CLIE_OID_CLIE = MAE_CLI.OID_CLIE
       AND MCUA.IND_ACTI = 1
       AND MCUA.ZTAD_OID_TERR_ADMI = ZTA.OID_TERR_ADMI
       AND ZTA.ZSCC_OID_SECC = ZS.OID_SECC
       AND ZS.ZZON_OID_ZONA = oidZona;

  TYPE interfazPedidos IS RECORD(
    oidCliente         MAE_CLIEN.OID_CLIE%TYPE
  );

  TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
  interfazRecordN interfazPedidosTab;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);

  --Recorremos las zonas seleccionadas
  FOR y IN (SELECT OID_ZONA, COD_ZONA
              FROM ZON_ZONA
             WHERE INSTR(psCodigoZona, COD_ZONA) > 0
               AND IND_ACTI = 1
               AND IND_BORR = 0) LOOP

    lnOidZona := y.OID_ZONA;

    --PROCESAMOS LAS CONSULTORAS
    OPEN c_Pedidos(lnOidZona, lnOidPeriodo);
    LOOP
      FETCH c_Pedidos BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;
      IF interfazRecordN.COUNT > 0 THEN

        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

          --Proceso de Premiacion de las Ordenes de Compra
          INC_PR_DETER_GANAD_RECOM(psCodigoPais,
                                   interfazRecordN(x).oidCliente,
                                   NULL,
                                   TIPO_CIERRE_ZONA,
                                   psCodigoPeriodo,
                                   psFechaFacturacion,
                                   psCodigoUsuario);
              
        END LOOP;

      END IF;
      EXIT WHEN c_Pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_Pedidos;
    
    --INSERTAMOS EN TABLA FAC_CONTR_CIERR
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM FAC_CONTR_CIERR
     WHERE PERD_OID_PERI = lnOidPeriodo
       AND TCIE_OID_TIPO_CIER = 2
       AND ZZON_OID_ZONA = lnOidZona;
       
    IF(lnOcurrencias = 0) THEN         
      INSERT INTO FAC_CONTR_CIERR
        (oid_ctrl,
         pais_oid_pais,
         val_secu_cier,
         fec_cier,
         val_proc_ejec,
         val_resu_proc,
         perd_oid_peri,
         zzon_oid_zona,
         tcie_oid_tipo_cier,
         zorg_oid_regi,
         fec_ulti_actu
        )
      VALUES
        (FAC_COCI_SEQ.NEXTVAL,
         lnOidPais,
         FAC_COCI_SEQ.CURRVAL,
         TO_DATE(psFechaFacturacion, 'dd/MM/yyyy'),
         'DeterminarGanadRecomCosultoraCierreZona',
         'OK',
         lnOidPeriodo,
         lnOidZona,
         2, --2: Tipo Cierre Zona
         NULL,
         SYSDATE);
    END IF;
    
    --ACTUALIZAMOS EN TABLA FAC_PROGR_CIERR
    UPDATE FAC_PROGR_CIERR
       SET EST_CIER = 'P', 
           USU_MODI = psCodigoUsuario, 
           FEC_MODI = SYSDATE
     WHERE EST_CIER = 'A'
       AND COD_ZONA = y.COD_ZONA
       AND TIP_CIER = 'Z'
       AND CAM_PROC = psCodigoPeriodo;
    
  END LOOP;

/*EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_DETER_GANAD_RECOM_CZONA: (' || ln_sqlcode || ')' || ls_sqlerrm);
*/
END INC_PR_DETER_GANAD_RECOM_CZONA;

/**************************************************************************
Descripcion       : Realiza la determinacion de Ganadoras de Recomendacion
                   de las consultoras que que no han pasado pedido y al 
                   cierre de periodo seran evaluadas si cumple las 
                    condiciones de recomendacion de los concursos
Fecha Creacion    : 05/10/2015
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoMarca    :  Codigo de Marca
  psCodigoCanal    :  Codigo de Canal
  psCodigoZona     :  Codigo de zona
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE INC_PR_DETER_GANAD_RECOM_PERIO(psCodigoPais          VARCHAR2,
                                         psCodigoMarca         VARCHAR2,
                                         psCodigoCanal         VARCHAR2,
                                         psCodigoPeriodo       VARCHAR2,
                                         psCodigoUsuario       VARCHAR2) IS
  lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;
  lnOcurrencias   NUMBER;

  CURSOR c_Pedidos(oidPeriodo NUMBER) IS
    SELECT DISTINCT MAE_CLI.OID_CLIE
      FROM inc_clien_recte       clie,
           MAE_CLIEN             mae_cli,
           inc_concu_param_gener concu,
           cra_perio             p1,
           cra_perio             desde
     WHERE clie.copa_oid_para_gral = concu.oid_para_gral
       AND p1.OID_PERI = oidPeriodo
       AND concu.perd_oid_peri_desd = desde.OID_PERI
       AND desde.FEC_INIC <= p1.FEC_INIC
       AND concu.ind_acti = 1
       AND clie.CLIE_OID_CLIE = mae_cli.OID_CLIE;

  --se define un tipo de dato tipo Tabla de Registros de los pedidos
  TYPE RegTab IS TABLE OF c_pedidos%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  pedidoReg RegTab;

BEGIN

  --Recuperamos el oid Pais,Marca,Canal,Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                             lnOidMarca,
                                                             lnOidCanal);
  --Recorremos las zonas seleccionadas
  OPEN c_pedidos(lnOidPeriodo);
    LOOP
    FETCH c_pedidos BULK COLLECT INTO pedidoReg LIMIT W_FILAS;

      IF pedidoReg.COUNT > 0 THEN
        FOR x IN pedidoReg.FIRST .. pedidoReg.LAST LOOP
            --Proceso de Premiacion de las Ordenes de Compra
            INC_PR_DETER_GANAD_RECOM(psCodigoPais,
                                     pedidoReg(x).OID_CLIE,
                                     NULL,
                                     TIPO_CIERRE_PERIODO,
                                     psCodigoPeriodo,
                                     NULL,
                                     psCodigoUsuario);
        END LOOP;
      END IF;

    EXIT WHEN c_pedidos%NOTFOUND;
    END LOOP;
  CLOSE c_pedidos;
  
  --INSERTAMOS EN TABLA FAC_CONTR_CIERR
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM FAC_CONTR_CIERR
   WHERE PERD_OID_PERI = lnOidPeriodo
     AND TCIE_OID_TIPO_CIER = 3;
       
  IF(lnOcurrencias = 0) THEN         
    INSERT INTO FAC_CONTR_CIERR
      (oid_ctrl,
       pais_oid_pais,
       val_secu_cier,
       fec_cier,
       val_proc_ejec,
       val_resu_proc,
       perd_oid_peri,
       zzon_oid_zona,
       tcie_oid_tipo_cier,
       zorg_oid_regi,
       fec_ulti_actu
      )
    VALUES
      (FAC_COCI_SEQ.NEXTVAL,
       lnOidPais,
       FAC_COCI_SEQ.CURRVAL,
       TRUNC(SYSDATE),
       'Procesar_cierre_incentivos',
       'OK',
       lnOidPeriodo,
       NULL,
       3, --3: Tipo Cierre Periodo
       NULL,
       SYSDATE);
  END IF;
    
  --ACTUALIZAMOS EN TABLA FAC_PROGR_CIERR
  UPDATE FAC_PROGR_CIERR
     SET EST_CIER = 'P', 
         USU_MODI = psCodigoUsuario, 
         FEC_MODI = SYSDATE
   WHERE EST_CIER = 'A'
     AND TIP_CIER = 'C'
     AND CAM_PROC = psCodigoPeriodo;

/*EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR INC_PR_DETER_GANAD_RECOM_PERIO: (' || ln_sqlcode || ')' || ls_sqlerrm);
*/
END INC_PR_DETER_GANAD_RECOM_PERIO;

end INC_PKG_PROCE_RECOM;
/
