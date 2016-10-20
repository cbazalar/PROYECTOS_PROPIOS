-- Create sequence 
drop sequence BAS_SEQ_REPO_HISTO_EJEC;
drop sequence BAS_SEQ_REPOR_HISTO_EJECU;
create sequence BAS_SEQ_REPOR_HISTO_EJECU
minvalue 1
maxvalue 99999999
start with 1000
increment by 1
cache 20;