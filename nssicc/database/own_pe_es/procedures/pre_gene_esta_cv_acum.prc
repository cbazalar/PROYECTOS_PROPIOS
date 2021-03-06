CREATE OR REPLACE PROCEDURE "PRE_GENE_ESTA_CV_ACUM"
AS
CURSOR CUR_1 IS
 SELECT MEC.MAFA_OID_MATR_FACT,
     SUM(NVL(MEC.IMP_MONT_ANUL,0)) IMP_MONT_ANUL,
   SUM(NVL(MEC.IMP_MONT_DEVU,0)) IMP_MONT_DEVU,
   SUM(NVL(MEC.IMP_MONT_ESTI,0)) IMP_MONT_ESTI,
   SUM(NVL(MEC.IMP_MONT_FACT,0)) IMP_MONT_FACT,
   SUM(NVL(MEC.IMP_MONT_FALT,0)) IMP_MONT_FALT,
   SUM(NVL(MEC.NUM_UNID_ANUL,0)) NUM_UNID_ANUL,
   SUM(NVL(MEC.NUM_UNID_DEVU,0)) NUM_UNID_DEVU,
   SUM(NVL(MEC.NUM_UNID_ESTI,0)) NUM_UNID_ESTI,
   SUM(NVL(MEC.NUM_UNID_FACT,0)) NUM_UNID_FACT,
   SUM(NVL(MEC.NUM_UNID_FALTA,0)) NUM_UNID_FALTA
  FROM PRE_MATRI_ESTAD_CV_CLIEN MEC
  GROUP BY MEC.MAFA_OID_MATR_FACT;
OID_MATR_ESTA NUMBER;
BEGIN
  FOR I IN CUR_1 LOOP
   BEGIN
     SELECT OID_MATR_ESTA_CODI INTO OID_MATR_ESTA
  FROM PRE_MATRI_ESTAD_CODIG_VENTA
  WHERE MAFA_OID_MATR_FACT = I.MAFA_OID_MATR_FACT;

  UPDATE PRE_MATRI_ESTAD_CODIG_VENTA
  SET NUM_UNID_ESTI = NVL(NUM_UNID_ESTI,0) + I.NUM_UNID_ESTI,
     IMP_MONT_ESTI = NVL(IMP_MONT_ESTI,0) + I.IMP_MONT_ESTI,
   NUM_UNID_FACT = NVL(NUM_UNID_FACT,0) + I.NUM_UNID_FACT,
   IMP_MONT_FACT = NVL(IMP_MONT_FACT,0) + I.IMP_MONT_FACT,
   NUM_UNID_FALTA = NVL(NUM_UNID_FALTA,0) + I.NUM_UNID_FALTA,
   IMP_MONT_FALT = NVL(IMP_MONT_FALT,0) + I.IMP_MONT_FALT,
   NUM_UNID_DEVU = NVL(NUM_UNID_DEVU,0) + I.NUM_UNID_DEVU,
   IMP_MONT_DEVU = NVL(IMP_MONT_DEVU,0) + I.IMP_MONT_DEVU,
   NUM_UNID_ANUL = NVL(NUM_UNID_ANUL,0) + I.NUM_UNID_ANUL,
   IMP_MONT_ANUL = NVL(IMP_MONT_ANUL,0) + I.IMP_MONT_ANUL
     WHERE OID_MATR_ESTA_CODI = OID_MATR_ESTA;
 EXCEPTION
       WHEN NO_DATA_FOUND
         THEN
            INSERT INTO PRE_MATRI_ESTAD_CODIG_VENTA
                        (OID_MATR_ESTA_CODI,
       MAFA_OID_MATR_FACT,
                         NUM_UNID_ESTI,
                         IMP_MONT_ESTI,
                         NUM_UNID_FACT,
                         IMP_MONT_FACT,
                         NUM_UNID_FALTA,
                         IMP_MONT_FALT,
                         NUM_UNID_DEVU, IMP_MONT_DEVU,
                         NUM_UNID_ANUL, IMP_MONT_ANUL
                        )
                 VALUES (PRE_MECV_SEQ.NEXTVAL,
        I.MAFA_OID_MATR_FACT,
                         I.NUM_UNID_ESTI,
                         I.IMP_MONT_ESTI,
       I.NUM_UNID_FACT,
       I.IMP_MONT_FACT,
       I.NUM_UNID_FALTA,
       I.IMP_MONT_FALT,
       I.NUM_UNID_DEVU,
       I.IMP_MONT_DEVU,
       I.NUM_UNID_ANUL,
       I.IMP_MONT_ANUL
                        );
            END;
  END LOOP;
  DELETE PRE_MATRI_ESTAD_CV_CLIEN;
END;
/

