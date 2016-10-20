CREATE OR REPLACE FUNCTION gen_fn_perio_atras
(
  vchperiodo       IN VARCHAR2,
  numperiodosatras IN NUMBER
) RETURN VARCHAR2 IS
BEGIN

  RETURN gen_pkg_gener.gen_fn_perio_nsigu(NULL,
                                          vchperiodo,
                                          -numperiodosatras);

END gen_fn_perio_atras;
/
