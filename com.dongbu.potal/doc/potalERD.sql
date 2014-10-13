
/* Drop Tables */

DROP TABLE IF EXISTS cmtb_code;
DROP TABLE IF EXISTS cmtb_schedule;
DROP TABLE IF EXISTS cmtb_group_member;
DROP TABLE IF EXISTS cmtb_group;
DROP TABLE IF EXISTS hm_address_phone;
DROP TABLE IF EXISTS hm_email_info;
DROP TABLE IF EXISTS hm_anniversary_mng;
DROP TABLE IF EXISTS hm_address_info;
DROP TABLE IF EXISTS hm_mng_address;
DROP TABLE IF EXISTS hm_childbirth_recode;
DROP TABLE IF EXISTS hm_buy_info;
DROP TABLE IF EXISTS hm_child_recode;
DROP TABLE IF EXISTS hm_cure_info;
DROP TABLE IF EXISTS hm_sell_stroe_info;
DROP TABLE IF EXISTS hm_entity_register;
DROP TABLE IF EXISTS cmtb_user;




/* Create Tables */

CREATE TABLE cmtb_code
(
	code_mgt_no varchar(64),
	upper_code varchar(32),
	code_seq bigint,
	code varchar(16),
	code_name varchar(128),
	code_comment varchar(2048)
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
	user_name varchar(32) NOT NULL,
	email varchar(32),
	phone_number varchar(16),
	mobile_phone_number varchar(16),
	ssn_number varchar(12),
	-- M : 남성
	-- F : 여성
	gender varchar(1),
	-- 마지막 접속 시간
	last_login_time timestamp,
	-- 마지막 실패 시간
	fail_login_time timestamp,
	-- 실패카운트
	fail_login_count bigint,
	-- 잠금여부 (Y : 잠김)
	use_lock_state varchar(1) DEFAULT 'N',
	description text,
	PRIMARY KEY (user_id)
) WITHOUT OIDS;


CREATE TABLE hm_address_info
(
	hm_mg_num bigint NOT NULL,
	hm_ad_no bigint NOT NULL,
	hm_type varchar(12),
	hm_add_info varchar(128),
	PRIMARY KEY (hm_ad_no)
) WITHOUT OIDS;


CREATE TABLE hm_address_phone
(
	hm_mg_num bigint NOT NULL,
	hm_pho_no bigint NOT NULL,
	phone_number varchar(12),
	type varchar(12),
	PRIMARY KEY (hm_pho_no)
) WITHOUT OIDS;


CREATE TABLE hm_anniversary_mng
(
	hm_mg_no bigint NOT NULL,
	hm_am_no bigint NOT NULL,
	-- yyyyMMdd 형태
	hm_date timestamp,
	-- 생일/기념일/기타/직접 입력
	hm_type varchar(32),
	PRIMARY KEY (hm_am_no)
) WITHOUT OIDS;


CREATE TABLE hm_buy_info
(
	buy_store_name varchar(64),
	-- 
	-- 
	buy_date timestamp,
	buy_note varchar(512),
	buy_phone_number varchar(11),
	entity_discern_no varchar(24) NOT NULL,
	PRIMARY KEY (entity_discern_no)
) WITHOUT OIDS;


CREATE TABLE hm_childbirth_recode
(
	entity_discern_no varchar(24) NOT NULL,
	-- MAX+1 증가
	th_no bigint NOT NULL,
	sperm_no bigint,
	sperm_fertilization_date timestamp,
	expected_date_confinement timestamp,
	nothing_special varchar(24),
	PRIMARY KEY (entity_discern_no, th_no)
) WITHOUT OIDS;


-- 송아지 기록시
-- 
-- 한우 개체관리 기록부에 먼저 등록된다.
-- 또한 이미 어미의 번호를 알고 있기때문에 (모) 번호와 함께 등록한다.
-- 
-- 부번호는 수정한 넘버
CREATE TABLE hm_child_recode
(
	entity_discern_no varchar(24) NOT NULL,
	-- 1산..2산..3산..4산...5산...... MAX+1증가
	-- 
	th_no bigint NOT NULL,
	gender varchar(4),
	-- Y(판매됨) /N(미판매)
	sell_yn varchar(1),
	birthday timestamp,
	PRIMARY KEY (entity_discern_no, th_no)
) WITHOUT OIDS;


CREATE TABLE hm_cure_info
(
	cure_date timestamp NOT NULL,
	--  ㅍ
	disease_name varchar(124) NOT NULL,
	cure_brakdown varchar(512) NOT NULL,
	injection_method varchar(256),
	withdrawal_period_expiration_date timestamp,
	-- 기본 'N'
	-- 
	-- N(없음)/Y(있음)
	needle_lose_yn varchar(1) DEFAULT '''N''',
	entity_discern_no varchar(24) NOT NULL,
	PRIMARY KEY (entity_discern_no)
) WITHOUT OIDS;


CREATE TABLE hm_email_info
(
	hm_mg_num bigint NOT NULL,
	hm_em_no bigint NOT NULL,
	hm_email varchar(32),
	PRIMARY KEY (hm_em_no)
) WITHOUT OIDS;


CREATE TABLE hm_entity_register
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
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	PRIMARY KEY (entity_discern_no)
) WITHOUT OIDS;


CREATE TABLE hm_mng_address
(
	hm_mg_num bigint NOT NULL,
	-- 사용자 아이디
	user_id varchar(16) NOT NULL,
	name varchar(32),
	homepage_url varchar(512),
	hm_memo text,
	PRIMARY KEY (hm_mg_num)
) WITHOUT OIDS;


CREATE TABLE hm_sell_stroe_info
(
	store_name varchar(256) NOT NULL,
	sell_date timestamp NOT NULL,
	sell_note varchar(512),
	sell_phone_number varchar(11),
	entity_discern_no varchar(24) NOT NULL,
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


ALTER TABLE hm_mng_address
	ADD FOREIGN KEY (user_id)
	REFERENCES cmtb_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE cmtb_group_member
	ADD FOREIGN KEY (user_id)
	REFERENCES cmtb_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_entity_register
	ADD FOREIGN KEY (user_id)
	REFERENCES cmtb_user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_childbirth_recode
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES hm_entity_register (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_buy_info
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES hm_entity_register (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_child_recode
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES hm_entity_register (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_cure_info
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES hm_entity_register (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_sell_stroe_info
	ADD FOREIGN KEY (entity_discern_no)
	REFERENCES hm_entity_register (entity_discern_no)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_address_phone
	ADD FOREIGN KEY (hm_mg_num)
	REFERENCES hm_mng_address (hm_mg_num)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_email_info
	ADD FOREIGN KEY (hm_mg_num)
	REFERENCES hm_mng_address (hm_mg_num)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_anniversary_mng
	ADD FOREIGN KEY (hm_mg_no)
	REFERENCES hm_mng_address (hm_mg_num)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE hm_address_info
	ADD FOREIGN KEY (hm_mg_num)
	REFERENCES hm_mng_address (hm_mg_num)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



/* Comments */

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
COMMENT ON COLUMN cmtb_user.gender IS 'M : 남성
F : 여성';
COMMENT ON COLUMN cmtb_user.last_login_time IS '마지막 접속 시간';
COMMENT ON COLUMN cmtb_user.fail_login_time IS '마지막 실패 시간';
COMMENT ON COLUMN cmtb_user.fail_login_count IS '실패카운트';
COMMENT ON COLUMN cmtb_user.use_lock_state IS '잠금여부 (Y : 잠김)';
COMMENT ON COLUMN hm_anniversary_mng.hm_date IS 'yyyyMMdd 형태';
COMMENT ON COLUMN hm_anniversary_mng.hm_type IS '생일/기념일/기타/직접 입력';
COMMENT ON COLUMN hm_buy_info.buy_date IS '
';
COMMENT ON COLUMN hm_childbirth_recode.th_no IS 'MAX+1 증가';
COMMENT ON TABLE hm_child_recode IS '송아지 기록시

한우 개체관리 기록부에 먼저 등록된다.
또한 이미 어미의 번호를 알고 있기때문에 (모) 번호와 함께 등록한다.

부번호는 수정한 넘버';
COMMENT ON COLUMN hm_child_recode.th_no IS '1산..2산..3산..4산...5산...... MAX+1증가
';
COMMENT ON COLUMN hm_child_recode.sell_yn IS 'Y(판매됨) /N(미판매)';
COMMENT ON COLUMN hm_cure_info.disease_name IS ' ㅍ';
COMMENT ON COLUMN hm_cure_info.needle_lose_yn IS '기본 ''N''

N(없음)/Y(있음)';
COMMENT ON COLUMN hm_entity_register.gender IS 'Cow 암소  / Bull 수소';
COMMENT ON COLUMN hm_entity_register.user_id IS '사용자 아이디';
COMMENT ON COLUMN hm_mng_address.user_id IS '사용자 아이디';



