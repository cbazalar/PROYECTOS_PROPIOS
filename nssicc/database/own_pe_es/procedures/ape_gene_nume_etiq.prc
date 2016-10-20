CREATE OR REPLACE PROCEDURE "APE_GENE_NUME_ETIQ" (
    OIDPAIS IN VARCHAR2
)
AS
   ult_num_etiq   NUMBER (12);

   CURSOR cur_etiq
   IS
      SELECT oid_etiq, ROW_NUMBER () OVER (ORDER BY oid_etiq) AS orden
        FROM ape_etiqu
       WHERE pais_oid_pais = TO_NUMBER(OIDPAIS) AND num_etiq IS NULL;
BEGIN
   SELECT NVL (MAX (num_etiq), 0)
     INTO ult_num_etiq
     FROM ape_etiqu
    WHERE pais_oid_pais = TO_NUMBER(OIDPAIS);

   FOR i IN cur_etiq
   LOOP
      BEGIN
         UPDATE ape_etiqu
            SET num_etiq = ult_num_etiq + i.orden
          WHERE oid_etiq = i.oid_etiq;
      END;
   END LOOP;
END;
/

