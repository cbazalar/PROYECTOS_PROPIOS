CREATE OR REPLACE PROCEDURE "IMP_PR_GENER_GUIA_REMIS"
    (p_val_nume_soli IN NUMBER) AS

c_direccionPlanta1 VARCHAR2(100) := '  AV. SAN GENARO 150 URB MOLITALIA LOS OLI ';
c_direccionPlanta2 VARCHAR2(100) := '  VOS/LIMA/LIMA                            ';
c_detallesPorPagina NUMBER := 40;
-- Cursor de cabecera
CURSOR c_cabecera IS
SELECT DISTINCT
       D.COD_CLIE,
       NVL(B.VAL_SERI_DOCU_LEGA, ' ') VAL_SERI_DOCU_LEGA,
       B.VAL_APE1 || ' ' || B.VAL_APE2 || ', ' || B.VAL_NOM1 || ' ' || B.VAL_NOM2 NOM_CLIE,
       F.COD_PERI,
       TO_CHAR(B.FEC_EMIS, 'DD/MM/YYYY') FEC_EMIS,
       Z.COD_ZONA,
       B.VAL_DIRE_COMP,
       B.VAL_NUME_IDEN_FISC,
       A.VAL_NUME_SOLI VAL_NUME_SOLI,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(2001, D.OID_CLIE, 4) VAL_CALL,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(2001, D.OID_CLIE, 3) VAL_DIST,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(2001, D.OID_CLIE, 2) VAL_PROV,
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(2001, D.OID_CLIE, 1) VAL_DEPA,
       ABS(A.VAL_IMPO_FLET_TOTA_LOCA) VAL_IMPO_FLET_TOTA_LOCA,
    K.COD_TIPO_DOCU,
    L.VAL_TASA_IMPU
  FROM PED_SOLIC_CABEC A,
       FAC_DOCUM_CONTA_CABEC B,
       MAE_CLIEN D,
       CRA_PERIO E,
       SEG_PERIO_CORPO F,
       ZON_ZONA Z,
       PED_SOLIC_CABEC G,
       FAC_TIPO_DOCUM K,
       PED_TASA_IMPUE L
 WHERE A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
   AND D.OID_CLIE = A.CLIE_OID_CLIE
   AND B.PERD_OID_PERI = E.OID_PERI
   AND E.PERI_OID_PERI = F.OID_PERI
   AND A.CLIE_OID_CLIE = Z.CLIE_OID_CLIE
   AND A.OID_SOLI_CABE = G.SOCA_OID_SOLI_CABE
   AND B.TIDO_OID_TIPO_DOCU = K.OID_TIPO_DOCU
   AND A.PAIS_OID_PAIS = L.PAIS_OID_PAIS
   AND L.VAL_INDI_IMPU = 'IGV'
   AND A.VAL_NUME_SOLI = p_val_nume_soli;
r_cabecera c_cabecera%ROWTYPE;

-- Cursor Detalle
CURSOR c_detalle IS
SELECT '18' || S.VAL_CODI_VENT || '0' VAL_CODI_VENT,
       X.NUM_UNID_ATEN,
       P.VAL_I18N DES_PROD
FROM   GEN_I18N_SICC_PAIS P,
       CRA_PERIO Q,
       SEG_PERIO_CORPO R,
       PED_SOLIC_POSIC S,
(
SELECT C.NUM_UNID_ATEN,
       C.PROD_OID_PROD,
       A.PERD_OID_PERI,
       A.OID_SOLI_CABE,
       C.SOPO_OID_SOLI_POSI,
       C.NUM_LINEA
FROM  PED_SOLIC_CABEC A,
      FAC_DOCUM_CONTA_CABEC B,
      FAC_DOCUM_CONTA_LINEA C,
      FAC_TIPO_DOCUM D 
WHERE A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
   AND B.OID_CABE = C.DCCA_OID_CABE
   AND B.TIDO_OID_TIPO_DOCU = D.OID_TIPO_DOCU
   AND C.NUM_UNID_ATEN != 0
   AND A.VAL_NUME_SOLI = p_val_nume_soli
) X
WHERE  P.ATTR_ENTI = 'MAE_PRODU'
   AND P.IDIO_OID_IDIO = 1
   AND P.VAL_OID = X.PROD_OID_PROD
   AND X.SOPO_OID_SOLI_POSI = S.OID_SOLI_POSI
   AND X.PERD_OID_PERI = Q.OID_PERI
   AND Q.PERI_OID_PERI = R.OID_PERI
ORDER BY 1;
r_detalle c_detalle%ROWTYPE;

-- Variables para almacenar la direccion
l_direccionCompleta VARCHAR2(1000) := '';
l_direccionLinea1   VARCHAR2(1000) := ' ';
l_direccionLinea2   VARCHAR2(1000) := ' ';
l_direccionLinea3   VARCHAR2(1000) := ' ';

-- Variables para almacenar la Ubicacion Geografica
l_ubicacionGeografica VARCHAR2(1000) := ' ';
l_ubicacionLinea1     VARCHAR2(1000) := ' ';
l_ubicacionLinea2     VARCHAR2(1000) := ' ';

-- Variables para guardar los montos y contadores
l_numeroPagina        NUMBER := 1;
l_numeroDetalles      NUMBER := 0;
l_sumaUnidades        NUMBER := 0;
l_numeroCambiosLinea  NUMBER := 0;
l_totalPaginas        NUMBER := 0;

-- Variables usadas para la generacion del archivo
l_textoNumeroPagina VARCHAR2(100) := '                                                                   PAG.  1 DE   ' || l_numeroPagina;
l_cambioLineaEspacioBlanco VARCHAR2(2) := CHR(10) || CHR(32);
l_cambioLinea VARCHAR2(1) := CHR(10);
l_textoActual VARCHAR2(1000) := '';
l_totalDetalles NUMBER;

-- Variables usadas para la carga del archivo
l_CLOBDest              CLOB;
l_flagPaginaNueva       BOOLEAN := TRUE;
BEGIN
  -- Eliminamos la nota de credito en caso exista
  DELETE FROM IMP_TMP_NOTA_CREDI 
  WHERE VAL_NUME_SOLI = p_val_nume_soli;
  
 SELECT CEIL(COUNT(*) / c_detallesPorPagina)
 INTO  l_totalPaginas
 FROM  PED_SOLIC_CABEC A,
       FAC_DOCUM_CONTA_CABEC B,
       FAC_DOCUM_CONTA_LINEA C,
       FAC_TIPO_DOCUM D 
 WHERE A.OID_SOLI_CABE = B.SOCA_OID_SOLI_CABE
    AND B.OID_CABE = C.DCCA_OID_CABE
    AND B.TIDO_OID_TIPO_DOCU = D.OID_TIPO_DOCU
    AND C.NUM_UNID_ATEN != 0
    AND A.VAL_NUME_SOLI = p_val_nume_soli;
        
  -- Obtenemos la informacion de la cabecera
  OPEN c_cabecera;
  LOOP
  FETCH c_cabecera INTO r_cabecera;
  EXIT WHEN c_cabecera%NOTFOUND;
  BEGIN
       -- DETALLE
       OPEN c_detalle;
       LOOP
       FETCH c_detalle INTO r_detalle;
       EXIT WHEN c_detalle%NOTFOUND;
        BEGIN
  
      -- IMPRESION DE LA CABECERA DE LA GUIA
      IF l_flagPaginaNueva = TRUE THEN
         INSERT INTO IMP_TMP_GUIA_REMIS (
         COD_CLIE,
         VAL_SERI_DOCU_LEGA,
      NUM_PAGI,
         COD_PERI,
         FEC_EMIS,
         VAL_NUME_SOLI,
         TEX_GUIA_REMI)
         VALUES (
         r_cabecera.COD_CLIE,
         r_cabecera.VAL_SERI_DOCU_LEGA,
      l_numeroPagina,
         r_cabecera.COD_PERI,
         r_cabecera.FEC_EMIS,
         r_cabecera.VAL_NUME_SOLI,
         EMPTY_CLOB())
         RETURNING TEX_GUIA_REMI INTO l_CLOBDest;
         -- Creamos el contenido de la nota de credito
         -- CABECERA
               l_textoNumeroPagina := '                                                                   PAG.  ' || l_numeroPagina || ' DE   ' || l_totalPaginas;
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoNumeroPagina), l_textoNumeroPagina);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         -- Fecha de emision
         l_textoActual := '          ' || r_cabecera.FEC_EMIS;
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
      
      -- Descripcion de documento
      l_textoActual := LPAD('Cons.  MAV Gerente Gui', 49);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
      
      -- Codigo de zona y Descripcion de documento
      l_textoActual := LPAD(r_cabecera.COD_ZONA, 14) || '-000000      a Remision';
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
      
         -- Codigo de consultora
      l_textoActual := LPAD(r_cabecera.COD_CLIE, 19);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         
         -- Periodo y numero de solicitud
      l_textoActual := LPAD(r_cabecera.COD_PERI, 16) || LPAD(r_cabecera.VAL_NUME_SOLI, 20);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
  
         -- Direccion
         l_ubicacionGeografica := r_cabecera.VAL_DIST || '/' || r_cabecera.VAL_PROV || '/' || r_cabecera.VAL_DEPA;
         IF r_cabecera.VAL_CALL IS NOT NULL THEN
             l_ubicacionGeografica := r_cabecera.VAL_CALL || '/' || l_ubicacionGeografica;
         END IF;
      l_direccionCompleta := r_cabecera.VAL_DIRE_COMP || ' ' || l_ubicacionGeografica;
         l_direccionLinea1 := SUBSTR(l_direccionCompleta, 1, 38);
         IF LENGTH(l_direccionCompleta) > 38 THEN
             l_direccionLinea2 := SUBSTR(r_cabecera.VAL_DIRE_COMP, 39, 38);
         END IF;
         IF LENGTH(l_direccionCompleta) > 76 THEN
             l_direccionLinea3 := SUBSTR(r_cabecera.VAL_DIRE_COMP, 77, 38);
         END IF;
      
      l_direccionLinea1 := c_direccionPlanta1 || l_direccionLinea1;
      l_direccionLinea2 := c_direccionPlanta2 || l_direccionLinea2;
      l_direccionLinea3 := LPAD(' ', 43) || l_direccionLinea3;
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_direccionLinea1), l_direccionLinea1);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_direccionLinea2), l_direccionLinea2);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_direccionLinea3), l_direccionLinea3);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
  
         -- Nombre del Cliente           
      l_textoActual := LPAD(' ', 10) || r_cabecera.NOM_CLIE;
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
  
         -- Texto estatico
      l_textoActual := LPAD('4', 78);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);

      l_flagPaginaNueva := FALSE;
   END IF;
   
      -- IMPRESION DEL DETALLE EN SÍ
         l_textoActual := '  ' || RPAD(r_detalle.VAL_CODI_VENT, 14, ' ') || RPAD(r_detalle.DES_PROD, 36, ' ') || LPAD(r_detalle.NUM_UNID_ATEN, 9, ' ');
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
         DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
         l_numeroDetalles := l_numeroDetalles + 1;
   l_sumaUnidades := l_sumaUnidades + r_detalle.NUM_UNID_ATEN;

   IF l_numeroDetalles >= c_detallesPorPagina THEN
         -- IMPRESION DEL PIE DE LA GUIA
          -- Total de unidades
          l_textoActual := LPAD('---------', 61, ' ');
          DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
          DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
          l_textoActual := LPAD('TOTAL', 51, ' ') ||  LPAD(l_sumaUnidades, 10, ' ');
          DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
          DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
          DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
          -- Para el ultimo cambio de linea no consideramos el espacio en blanco
          DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLinea), l_cambioLinea);

       l_numeroPagina := l_numeroPagina + 1;
    l_sumaUnidades := 0;
    l_flagPaginaNueva := TRUE;
    l_numeroDetalles := 1;
   END IF;
       END;
       END LOOP;
       CLOSE c_detalle;

     -- IMPRESION DEL PIE DE LA GUIA DE LOS DETALLES RESTANTES
        -- Total de unidades
        l_textoActual := LPAD('---------', 61, ' ');
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
        l_textoActual := LPAD('TOTAL', 51, ' ') ||  LPAD(l_sumaUnidades, 10, ' ');
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_textoActual), l_textoActual);
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLineaEspacioBlanco), l_cambioLineaEspacioBlanco);
        -- Para el ultimo cambio de linea no consideramos el espacio en blanco
        DBMS_LOB.writeappend(l_CLOBDest, LENGTH(l_cambioLinea), l_cambioLinea);
  END;
  END LOOP;
  CLOSE c_cabecera;
  COMMIT;
END IMP_PR_GENER_GUIA_REMIS;
/

