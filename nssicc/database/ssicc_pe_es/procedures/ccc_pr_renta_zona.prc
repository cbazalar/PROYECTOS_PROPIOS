CREATE OR REPLACE PROCEDURE "CCC_PR_RENTA_ZONA" (p_codigoPais IN VARCHAR2,
                     p_codigoSociedad IN VARCHAR2,
              p_codigoMarca IN VARCHAR2,
              p_codigoCanal IN VARCHAR2,
              p_periodoCierreFinal IN VARCHAR2,
              p_numeroDiasIncobra IN NUMBER) IS

/**************************************************************************
Descripcion        : Calcula la Rentabilidad por Zona - Interfaces.
Fecha Creacion     : 28/02/2006
Fecha Modificacion : 28/02/2006
Parametros Entrada : p_codigoPais : Codigo de Pais
      p_codigoSociedad : Codigo de Sociedad
      p_codigoMarca : Codigo de Marca
      p_codigoCanal : Codigo de Canal
      p_periodoCierreFinal : Periodo Cierre Calculado
      p_numeroDiasIncobra : Numero de dias de Incobrabilidad
Autor              : Jose Martinez Vargas
Version      : Final
***************************************************************************/
l_oid            NUMBER;
l_codigoPais      VARCHAR2(3);
l_codigoSociedad   VARCHAR2(4);
l_codigoPeriodo    VARCHAR2(6);
l_codigoZona    VARCHAR2(4);
l_ultimoNumeroHisto   NUMBER(3);
l_fechaValor    DATE;
l_fechaDocumento   DATE;
l_importe     NUMBER(12,2);
l_importeCuenta    NUMBER(12,2);
l_codigoProceso    VARCHAR2(6);
l_codigoSubproceso   NUMBER(1);
l_importeHisto    NUMBER(12,2);

CURSOR c_Renta IS
SELECT CCC_MOVIM_CUENT_CORRI.OID_MOVI_CC,
    SEG_PAIS.COD_PAIS,
    SEG_SOCIE.COD_SOCI,
    SEG_PERIO_CORPO.COD_PERI,
    ZON_ZONA.COD_ZONA,
    ZON_ZONA.DES_ZONA,
    SEG_MONED.COD_MONE,
    CCC_MOVIM_CUENT_CORRI.VAL_ULTI_NUME_HIST,
    CCC_MOVIM_CUENT_CORRI.FEC_VALO,
    CCC_MOVIM_CUENT_CORRI.FEC_DOCU,
    CCC_MOVIM_CUENT_CORRI.IMP_MOVI,
    CCC_MOVIM_CUENT_CORRI.IMP_MOVI_CUEN,
    CCC_PROCE.COD_PROC,
    CCC_SUBPR.COD_SUBP
  FROM SEG_SOCIE,
    CRA_PERIO,
    SEG_MONED,
       ZON_SECCI,
       CCC_SUBPR,
       CCC_MOVIM_CUENT_CORRI,
       SEG_PAIS,
       SEG_MARCA,
       SEG_CANAL,
       SEG_PERIO_CORPO,
       ZON_ZONA,
       CCC_PROCE
  WHERE (SEG_PAIS.COD_PAIS = p_codigoPais)
  AND (SEG_SOCIE.COD_SOCI = NVL(p_codigoSociedad, SEG_SOCIE.COD_SOCI))
  AND (SEG_MARCA.COD_MARC = NVL(p_codigoMarca, SEG_MARCA.COD_MARC))
  AND (SEG_CANAL.COD_CANA = p_codigoCanal)
  AND   (SEG_PERIO_CORPO.COD_PERI <= p_periodoCierreFinal)
  AND   ((CCC_PROCE.COD_PROC = 'CCC001' AND CCC_SUBPR.COD_SUBP = 1)
      OR (CCC_PROCE.COD_PROC = 'CCC003' AND CCC_SUBPR.COD_SUBP = 4)
      OR (CCC_PROCE.COD_PROC = 'CCC004' AND CCC_SUBPR.COD_SUBP = 4))
  AND   (CCC_MOVIM_CUENT_CORRI.IMP_MOVI > 0)
  AND   (SEG_PAIS.OID_PAIS = SEG_SOCIE.PAIS_OID_PAIS)
  AND  (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS)
  AND  (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
  AND  (SEG_MARCA.OID_MARC = CCC_MOVIM_CUENT_CORRI.MARC_OID_MARC)
  AND  (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
  AND  (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
  AND  (SEG_PAIS.OID_PAIS = ZON_ZONA.PAIS_OID_PAIS)
  AND  (SEG_MARCA.OID_MARC = ZON_ZONA.MARC_OID_MARC)
  AND  (SEG_CANAL.OID_CANA = ZON_ZONA.CANA_OID_CANA)
  AND  (ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA)
  AND  (SEG_PAIS.OID_PAIS = CCC_PROCE.PAIS_OID_PAIS)
  AND  (CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC)
  AND  (SEG_SOCIE.OID_SOCI(+) = CCC_MOVIM_CUENT_CORRI.SOCI_OID_SOCI)
  AND  (CRA_PERIO.OID_PERI(+) = CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI)
  AND  (SEG_MONED.OID_MONE(+) = CCC_MOVIM_CUENT_CORRI.MONE_OID_MONE)
  AND  (ZON_SECCI.OID_SECC(+) = CCC_MOVIM_CUENT_CORRI.ZSCC_OID_SECC)
  AND  (CCC_SUBPR.OID_SUBP(+) = CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_CREA)
  AND  (CCC_MOVIM_CUENT_CORRI.VAL_ULTI_NUME_HIST > 0 )
  ORDER BY SEG_PAIS.COD_PAIS, SEG_SOCIE.COD_SOCI,
       SEG_PERIO_CORPO.COD_PERI, ZON_ZONA.COD_ZONA;

r_Renta c_Renta%ROWTYPE;

BEGIN
   l_importe := 0;
   l_importeCuenta := 0;
   l_importeHisto := 0;

   /* Borrar tabla de totales por zona*/
   DELETE FROM CCC_RENTA_ZONA_TEMPO
   WHERE COD_PAIS = p_codigoPais;

   /* Insertar totales por zona*/
   INSERT INTO CCC_RENTA_ZONA_TEMPO (COD_PAIS, COD_SOCI, COD_PERI, COD_ZONA,
                                     DES_ZONA, COD_MONE, IMP_MOVI)
     SELECT SEG_PAIS.COD_PAIS,
   SEG_SOCIE.COD_SOCI,
   SEG_PERIO_CORPO.COD_PERI,
   ZON_ZONA.COD_ZONA,
   ZON_ZONA.DES_ZONA,
   SEG_MONED.COD_MONE,
   SUM (IMP_MOVI)
       FROM SEG_SOCIE,
      CRA_PERIO,
      SEG_MONED,
      ZON_SECCI,
      CCC_SUBPR,
      CCC_MOVIM_CUENT_CORRI,
      SEG_PAIS,
      SEG_MARCA,
      SEG_CANAL,
      SEG_PERIO_CORPO,
      ZON_ZONA,
      CCC_PROCE
    WHERE (SEG_PAIS.COD_PAIS = p_codigoPais)
    AND  (SEG_SOCIE.COD_SOCI = NVL(p_codigoSociedad, SEG_SOCIE.COD_SOCI))
    AND   (SEG_MARCA.COD_MARC = NVL(p_codigoMarca, SEG_MARCA.COD_MARC))
    AND  (SEG_CANAL.COD_CANA = p_codigoCanal)
       AND   (SEG_PERIO_CORPO.COD_PERI <= p_periodoCierreFinal)
       AND   ((CCC_PROCE.COD_PROC = 'CCC001' AND CCC_SUBPR.COD_SUBP = 1)
              OR (CCC_PROCE.COD_PROC = 'CCC003' AND CCC_SUBPR.COD_SUBP = 4)
              OR (CCC_PROCE.COD_PROC = 'CCC004' AND CCC_SUBPR.COD_SUBP = 4))
       AND   (CCC_MOVIM_CUENT_CORRI.IMP_MOVI > 0)
       AND   (SEG_PAIS.OID_PAIS = SEG_SOCIE.PAIS_OID_PAIS)
       AND   (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS)
       AND   (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
       AND   (SEG_MARCA.OID_MARC = CCC_MOVIM_CUENT_CORRI.MARC_OID_MARC)
       AND   (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
       AND   (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
       AND   (SEG_PAIS.OID_PAIS = ZON_ZONA.PAIS_OID_PAIS)
       AND   (SEG_MARCA.OID_MARC = ZON_ZONA.MARC_OID_MARC)
       AND   (SEG_CANAL.OID_CANA = ZON_ZONA.CANA_OID_CANA)
       AND   (ZON_ZONA.OID_ZONA = ZON_SECCI.ZZON_OID_ZONA)
       AND   (SEG_PAIS.OID_PAIS = CCC_PROCE.PAIS_OID_PAIS)
       AND   (CCC_PROCE.OID_PROC = CCC_SUBPR.CCPR_OID_PROC)
       AND   (SEG_SOCIE.OID_SOCI(+) = CCC_MOVIM_CUENT_CORRI.SOCI_OID_SOCI)
       AND   (CRA_PERIO.OID_PERI(+) = CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI)
       AND   (SEG_MONED.OID_MONE(+) = CCC_MOVIM_CUENT_CORRI.MONE_OID_MONE)
       AND   (ZON_SECCI.OID_SECC(+) = CCC_MOVIM_CUENT_CORRI.ZSCC_OID_SECC)
       AND   (CCC_SUBPR.OID_SUBP(+) = CCC_MOVIM_CUENT_CORRI.SUBP_OID_SUBP_CREA)
       GROUP BY SEG_PAIS.COD_PAIS, SEG_SOCIE.COD_SOCI,
              SEG_PERIO_CORPO.COD_PERI,ZON_ZONA.COD_ZONA,
          ZON_ZONA.DES_ZONA,SEG_MONED.COD_MONE
    ORDER BY SEG_PAIS.COD_PAIS, SEG_SOCIE.COD_SOCI,
        SEG_PERIO_CORPO.COD_PERI, ZON_ZONA.COD_ZONA;


   /* Abrimos cursor que contine informacion de Movimientos de Cuenta Corriente */
   DBMS_OUTPUT.PUT_LINE('INICIO');
   OPEN c_Renta;
   LOOP
      FETCH c_Renta INTO r_Renta;
      EXIT WHEN c_Renta%NOTFOUND;
   BEGIN
   l_codigoPais := r_Renta.cod_pais;
      l_codigoSociedad := r_Renta.cod_soci;
      l_codigoPeriodo := r_Renta.cod_peri;
      l_codigoZona := r_Renta.cod_zona;
      l_ultimoNumeroHisto := r_Renta.val_ulti_nume_hist;
      l_fechaValor := r_Renta.fec_valo;
      l_fechaDocumento := r_Renta.fec_docu;
      l_importe := r_Renta.imp_movi;
      l_importeCuenta := r_Renta.imp_movi_cuen;
      l_codigoProceso := r_Renta.cod_proc;
      l_codigoSubproceso := r_Renta.cod_subp;
   l_oid := r_Renta.oid_movi_cc;

   /* Restamos el importe */
   IF ((l_ultimoNumeroHisto >= 1) AND 
       (l_codigoProceso <> 'CCC006') AND
    (l_codigoProceso <> 'CCCA06') AND 
    (l_codigoProceso <> 'CCCEV6') AND
    (l_codigoProceso <> 'CCCN06') AND
    (l_codigoProceso <> 'CCCX06'))
            THEN IF (  (l_codigoProceso = 'CCC002' AND l_codigoSubproceso = 1)
           OR (l_codigoProceso = 'TES002' AND l_codigoSubproceso = 1)
     OR (l_codigoProceso = 'CCCEV2' AND l_codigoSubproceso = 2)
     OR (l_codigoProceso = 'CCCS03' AND l_codigoSubproceso = 1)
     OR (l_codigoProceso = 'CCCA02' AND l_codigoSubproceso = 2)
     OR (l_codigoProceso = 'CCCN02' AND l_codigoSubproceso = 2) )
            THEN IF ((l_fechaValor - l_fechaDocumento) <= p_numeroDiasIncobra)
               THEN UPDATE CCC_RENTA_ZONA_TEMPO
      SET CCC_RENTA_ZONA_TEMPO.IMP_MOVI = CCC_RENTA_ZONA_TEMPO.IMP_MOVI - l_importeCuenta
      WHERE CCC_RENTA_ZONA_TEMPO.COD_PAIS = l_codigoPais
       AND   CCC_RENTA_ZONA_TEMPO.COD_SOCI = l_codigoSociedad
      AND   CCC_RENTA_ZONA_TEMPO.COD_PERI = l_codigoPeriodo
       AND   CCC_RENTA_ZONA_TEMPO.COD_ZONA = l_codigoZona;
            END IF;
         END IF;
         ELSE UPDATE CCC_RENTA_ZONA_TEMPO
     SET CCC_RENTA_ZONA_TEMPO.IMP_MOVI = CCC_RENTA_ZONA_TEMPO.IMP_MOVI - l_importeCuenta
     WHERE CCC_RENTA_ZONA_TEMPO.COD_PAIS = l_codigoPais
      AND   CCC_RENTA_ZONA_TEMPO.COD_SOCI = l_codigoSociedad
     AND   CCC_RENTA_ZONA_TEMPO.COD_PERI = l_codigoPeriodo
      AND   CCC_RENTA_ZONA_TEMPO.COD_ZONA = l_codigoZona;
         END IF;

   /* Restamos importe de Historico */
   DBMS_OUTPUT.PUT_LINE('l_ultimoNumeroHisto: ' || to_char(l_ultimoNumeroHisto));
   IF (l_ultimoNumeroHisto > 1) THEN
      DBMS_OUTPUT.PUT_LINE('l_oid: ' || to_char(l_oid));
      SELECT SUM(IMP_MOVI) INTO l_importeHisto FROM CCC_HISTO_MOVIM_CC
     WHERE CCC_HISTO_MOVIM_CC.MVCC_OID_MOVI_CC = l_oid
     AND CCC_HISTO_MOVIM_CC.NUM_HIST = 0;
     DBMS_OUTPUT.PUT_LINE('l_importeHisto: ' || to_char(l_importeHisto));
   UPDATE CCC_RENTA_ZONA_TEMPO
     SET CCC_RENTA_ZONA_TEMPO.IMP_MOVI = CCC_RENTA_ZONA_TEMPO.IMP_MOVI - l_importeHisto
     WHERE CCC_RENTA_ZONA_TEMPO.COD_PAIS = l_codigoPais
      AND   CCC_RENTA_ZONA_TEMPO.COD_SOCI = l_codigoSociedad
     AND   CCC_RENTA_ZONA_TEMPO.COD_PERI = l_codigoPeriodo
      AND   CCC_RENTA_ZONA_TEMPO.COD_ZONA = l_codigoZona;
   END IF;
      END;
   END LOOP;
   CLOSE c_Renta;
COMMIT;
END CCC_PR_RENTA_ZONA;
/

