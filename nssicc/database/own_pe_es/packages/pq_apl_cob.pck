CREATE OR REPLACE PACKAGE "PQ_APL_COB"
AS
/******************************************************************************
   NAME:       PQ_APL_COB
   PURPOSE:    Funciones auxiliares de COB (Cobranzas)

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        07/05/2007  rromero          1. Creado con funciones:
                                                FUNCTION NUM_GESTIONES(OID_CLIE IN NUMBER, FEC_GEST IN DATE) RETURN NUMBER;
                                                FUNCTION ULT_GESTION(OID_CLIE IN NUMBER, FEC_GEST IN DATE) RETURN NUMBER;
******************************************************************************/
   FUNCTION num_gestiones (oid_clie IN NUMBER, fec_gest IN DATE)
      RETURN NUMBER;

   FUNCTION ult_gestion (oid_clie IN NUMBER, fec_gest IN DATE)
      RETURN cob_accio_cobra.val_desc%TYPE;
END pq_apl_cob;
/

CREATE OR REPLACE PACKAGE BODY "PQ_APL_COB"
AS
/******************************************************************************
   NAME:       PQ_APL_COB
   PURPOSE:    Funciones auxiliares de COB (Cobranzas)

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        07/05/2007  rromero          1. Created this package body.
                                                FUNCTION NUM_GESTIONES(OID_CLIE IN NUMBER, FEC_GEST IN DATE) RETURN NUMBER;
                                                FUNCTION ULT_GESTION(OID_CLIE IN NUMBER, FEC_GEST IN DATE) RETURN NUMBER;
******************************************************************************/
   FUNCTION num_gestiones (oid_clie IN NUMBER, fec_gest IN DATE)
      RETURN NUMBER
   IS
      retorno   NUMBER (12);
   BEGIN
      SELECT COUNT (*)
        INTO retorno
        FROM cob_gesti_cobra gcobra
       WHERE gcobra.clie_oid_clie = oid_clie AND gcobra.fec_gest >= fec_gest;

      RETURN retorno;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN 0;
   END;

   FUNCTION ult_gestion (oid_clie IN NUMBER, fec_gest IN DATE)
      RETURN cob_accio_cobra.val_desc%TYPE
   IS
      retorno   cob_accio_cobra.val_desc%TYPE;
   BEGIN
      SELECT acccob.val_desc
        INTO retorno
        FROM cob_accio_cobra acccob
       WHERE acccob.oid_acci_cobr =
                (SELECT acco_oid_acci_cobr
                   FROM (SELECT   gcobra.acco_oid_acci_cobr
                             FROM cob_gesti_cobra gcobra
                            WHERE gcobra.clie_oid_clie = oid_clie
                              AND gcobra.fec_gest >= fec_gest
                         ORDER BY gcobra.fec_gest DESC,
                                  gcobra.oid_gest_cobr DESC)
                  WHERE ROWNUM = 1);

      RETURN retorno;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN '';
   END;
END pq_apl_cob;
/

