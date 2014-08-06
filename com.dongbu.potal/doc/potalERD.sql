
/* Drop Tables */

DROP TABLE IF EXISTS a;
DROP TABLE IF EXISTS aaa;
DROP TABLE IF EXISTS aaa;
DROP TABLE IF EXISTS aaaa;
DROP TABLE IF EXISTS as;
DROP TABLE IF EXISTS cmtb_code;
DROP TABLE IF EXISTS cmtb_schedule;
DROP TABLE IF EXISTS cmtb_group_member;
DROP TABLE IF EXISTS cmtb_group;
DROP TABLE IF EXISTS cmtb_user;
DROP TABLE IF EXISTS new_table;
DROP TABLE IF EXISTS new_table;
DROP TABLE IF EXISTS new_table;
DROP TABLE IF EXISTS new_table;




/* Create Tables */

CREATE TABLE a
(
	a ,
	a ,
	a ,
	a ,
	entity_discern_no varchar(24) NOT NULL,
	PRIMARY KEY (entity_discern_no)
) WITHOUT OIDS;


CREATE TABLE aaa
(
	date timestamp,
	a ,
	a ,
	a ,
	aa ,
	-- 기본 'N'
	-- 
	-- N(없음)/Y(있음)
	a  DEFAULT 'N',
	entity_discern_no varchar(24) NOT NULL,
	PRIMARY KEY (entity_discern_no)
) WITHOUT OIDS;


CREATE TABLE aaa
(
	a ,
	a ,
	a ,
	a ,
	entity_discern_no varchar(24) NOT NULL,
	PRIMARY KEY (entity_discern_no)
) WITHOUT OIDS;


-- 송아지 기록시
-- 
-- 한우 개체관리 기록부에 먼저 등록된다.
-- 또한 이미 어미의 번호를 알고 있기때문에 (모) 번호와 함께 등록한다.
-- 
-- 부번호는 수정한 넘버
CREATE TABLE aaaa
(
	entity_discern_no varchar(24) NOT NULL,
	-- 1산..2산..3산..4산...5산...... MAX+1증가
	-- 
	th_no bigint NOT NULL,
	ss ,
	aa ,
	bord_day ,
	PRIMARY KEY (entity_discern_no, th_no)
) WITHOUT OIDS;


CREATE TABLE as
(
	entity_discern_no varchar(24) NOT NULL,
	-- MAX+1 증가
	th_no bigint NOT NULL,
	sperm_no ,
	sperm_fertilization_date timestamp,
	expected_date_confinement timestamp,
	aa ,
	PRIMARY KEY (entity_discern_no, th_no)
) WITHOUT OIDS;


CREATE TABLE cmtb_code
(
	code_mgt_no ,
	upper_code ,
	code_seq ,
	code ,
	code_name ,
	code_comment 
) WITHOUT OIDS;


CREATE TABLE cmtb_group
(
	group_no bigint NOT NULL,
	group_name varchar(128),
	group_desc text,
	group_crt_time timestamp,
	PRIMARY KEY (group_no)
) WITHOUT OIDS;


CREATE TABLE cmtb_group_member
(
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	-- 그룹번호
	group_no bigint NOT NULL,
	PRIMARY KEY (user_id, group_no)
) WITHOUT OIDS;


CREATE TABLE cmtb_schedule
(
	-- 일정관리번호
	sch_mgt_no bigint NOT NULL,
	-- 같은날의 순번
	sch_seq bigint NOT NULL,
	-- 제목
	sch_subject varchar(512) NOT NULL,
	sch_content text,
	-- 기준날짜
	-- 
	std_date varchar(10) NOT NULL,
	-- 오늘 날씨 ('맑음......')
	today_weather_code varchar(12),
	min_degreespoint_code varchar(1),
	max_degreespoint_code varchar(1),
	-- 최저 기온
	-- 
	min_temperature bigint,
	-- 최고온도
	max_temperature bigint,
	-- 생성시간
	create_time timestamp,
	-- 수정시간
	modify_time timestamp,
	-- 공개 여부 'Y' : 공개  'N' 비공개
	public_yn varchar(1) DEFAULT 'N',
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	-- 그룹번호
	group_no bigint NOT NULL,
	PRIMARY KEY (sch_mgt_no, sch_seq)
) WITHOUT OIDS;


CREATE TABLE cmtb_user
(
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	-- 패스워드
	password varchar(1024) NOT NULL,
	user_name  NOT NULL,
	email ,
	phone_number ,
	mobile_phone_number ,
	ssn_number ,
	gender ,
	-- 마지막 접속 시간
	last_login_time ,
	-- 마지막 실패 시간
	fail_login_time ,
	-- 실패카운트
	fail_login_count ,
	-- 잠금여부
	use_lock_state ,
	description ,
	PRIMARY KEY (user_id)
) WITHOUT OIDS;


CREATE TABLE new_table
(

) WITHOUT OIDS;


CREATE TABLE new_table
(

) WITHOUT OIDS;


CREATE TABLE new_table
(

) WITHOUT OIDS;


CREATE TABLE new_table
(
	entity_discern_no varchar(24) NOT NULL,
	parent_papa_no varchar(24),
	birth_day timestamp,
	parent_mom_no varchar(24),
	ear_tag_date timestamp,
	castration_date timestamp,
	-- Cow 암소  / Bull 수소
	gender varchar(4),
	create_time timestamp,
	modify_time timestamp,
	user_id varchar(24),
	PRIMARY KEY (entity_discern_no)
) WITHOUT OIDS;



/* Create Foreign Keys */

ALTER TABLE cmtb_group_member
	ADD FOREIGN KEY (group_no)
	REFERENCES cmtb_group (group_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE cmtb_schedule
	ADD FOREIGN KEY (user_id, group_no)
	REFERENCES cmtb_group_member (user_id, group_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE cmtb_group_member
	ADD FOREIGN KEY (user_id)
	REFERENCES cmtb_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE aaa
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES new_table (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE aaa
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES new_table (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE a
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES new_table (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE as
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES new_table (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE aaaa
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES new_table (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Comments */

COMMENT ON COLUMN aaa.a IS '기본 ''N''

N(없음)/Y(있음)';
COMMENT ON TABLE aaaa IS '송아지 기록시

한우 개체관리 기록부에 먼저 등록된다.
또한 이미 어미의 번호를 알고 있기때문에 (모) 번호와 함께 등록한다.

부번호는 수정한 넘버';
COMMENT ON COLUMN aaaa.th_no IS '1산..2산..3산..4산...5산...... MAX+1증가
';
COMMENT ON COLUMN as.th_no IS 'MAX+1 증가';
COMMENT ON COLUMN cmtb_group_member.user_id IS '사용자 아이디';
COMMENT ON COLUMN cmtb_group_member.group_no IS '그룹번호';
COMMENT ON COLUMN cmtb_schedule.sch_mgt_no IS '일정관리번호';
COMMENT ON COLUMN cmtb_schedule.sch_seq IS '같은날의 순번';
COMMENT ON COLUMN cmtb_schedule.sch_subject IS '제목';
COMMENT ON COLUMN cmtb_schedule.std_date IS '기준날짜
';
COMMENT ON COLUMN cmtb_schedule.today_weather_code IS '오늘 날씨 (''맑음......'')';
COMMENT ON COLUMN cmtb_schedule.min_temperature IS '최저 기온
';
COMMENT ON COLUMN cmtb_schedule.max_temperature IS '최고온도';
COMMENT ON COLUMN cmtb_schedule.create_time IS '생성시간';
COMMENT ON COLUMN cmtb_schedule.modify_time IS '수정시간';
COMMENT ON COLUMN cmtb_schedule.public_yn IS '공개 여부 ''Y'' : 공개  ''N'' 비공개';
COMMENT ON COLUMN cmtb_schedule.user_id IS '사용자 아이디';
COMMENT ON COLUMN cmtb_schedule.group_no IS '그룹번호';
COMMENT ON COLUMN cmtb_user.user_id IS '사용자 아이디';
COMMENT ON COLUMN cmtb_user.password IS '패스워드';
COMMENT ON COLUMN cmtb_user.last_login_time IS '마지막 접속 시간';
COMMENT ON COLUMN cmtb_user.fail_login_time IS '마지막 실패 시간';
COMMENT ON COLUMN cmtb_user.fail_login_count IS '실패카운트';
COMMENT ON COLUMN cmtb_user.use_lock_state IS '잠금여부';
COMMENT ON COLUMN new_table.gender IS 'Cow 암소  / Bull 수소';



