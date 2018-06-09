spool schema_objects.log

prompt
prompt Creating table ROUTE
prompt ====================
prompt
create table PIXEL.ROUTE
(
  ID       NUMBER(19,2) not null,
  IS_READY CHAR(1 CHAR) not null,
  TIME     NUMBER(19,2) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table PIXEL.ROUTE
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table POINT
prompt ====================
prompt
create table PIXEL.POINT
(
  ID             NUMBER(19,2) not null,
  ROUTE_POINT_ID NUMBER(19,2) not null,
  ROUTE_ID       NUMBER(19,2) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table PIXEL.POINT
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table PIXEL.POINT
  add constraint FKS1HE5WLRP4JNL0O85Q34AN54E foreign key (ROUTE_ID)
  references PIXEL.ROUTE (ID);

prompt
prompt Creating table POINT_GRAPH
prompt ==========================
prompt
create table PIXEL.POINT_GRAPH
(
  ID           NUMBER(19,2) not null,
  ID_POINT_ONE NUMBER(19,2) not null,
  ID_POINT_TWO NUMBER(19,2) not null,
  TIME_INFO    NUMBER(19,2) not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table PIXEL.POINT_GRAPH
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating sequence POINT_SEQ
prompt ===========================
prompt
create sequence PIXEL.POINT_SEQ
minvalue 1
maxvalue 9999999999999999
start with 49
increment by 1
cache 2;

prompt
prompt Creating sequence ROUTE_SEQ
prompt ===========================
prompt
create sequence PIXEL.ROUTE_SEQ
minvalue 1
maxvalue 9999999999999999
start with 17
increment by 1
cache 2;


spool off
