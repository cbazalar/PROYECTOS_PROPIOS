CREATE OR REPLACE FUNCTION cra_activ_refer_padre(poid_acti IN VARCHAR2, pcod_pais in varchar2) RETURN varchar2 IS

  cursor c_actis is
      select ca.oid_acti
      from cra_activ ca, seg_pais sp
     where sp.cod_pais = pcod_pais
       and ca.pais_oid_pais = sp.oid_pais
       and ca.cact_oid_acti = poid_acti;

  TYPE t_oid_acti IS TABLE OF cra_activ.oid_acti%TYPE;
  
  v_oid_acti   t_oid_acti;
  
  lv_actis varchar2(100);

BEGIN
    lv_actis := poid_acti;
    OPEN c_actis;
    LOOP
      FETCH c_actis BULK COLLECT
         INTO v_oid_acti 
              LIMIT 1000;

      IF v_oid_acti.count > 0 THEN
      begin
        FOR i IN v_oid_acti.first .. v_oid_acti.last loop
             lv_actis:=  lv_actis || '|' || cra_activ_refer_padre(v_oid_acti(i),pcod_pais);
       
        END LOOP;
        return lv_actis;
      end;
      ELSE
        return lv_actis;
      END IF;

      EXIT WHEN c_actis%NOTFOUND;
      END LOOP;
    CLOSE c_actis; 

END;
/
