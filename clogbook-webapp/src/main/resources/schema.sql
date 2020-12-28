CREATE TABLE IF NOT EXISTS TS_ROLE_ROL (
  rol_id SERIAL PRIMARY KEY,
  rol_name varchar
);

CREATE TABLE IF NOT EXISTS TS_PERMISSION_PRM (
  prm_id SERIAL PRIMARY KEY,
  prm_context varchar NOT NULL,
  prm_operation varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS TS_ROLE_RIGHT_URT (
  rrt_rol_id int,
  rrt_prm_id int,
  PRIMARY KEY (rrt_rol_id, rrt_prm_id),
  FOREIGN KEY (rrt_rol_id) REFERENCES TS_ROLE_ROL (rol_id),
  FOREIGN KEY (rrt_prm_id) REFERENCES TS_PERMISSION_PRM (prm_id)
);

CREATE TABLE IF NOT EXISTS T_PILOT_PLT (
  plt_id SERIAL PRIMARY KEY,
  plt_last_name varchar NOT NULL,
  plt_first_name varchar,
  plt_email varchar,
  plt_phone_number varchar(20),
  plt_owner int,
  FOREIGN KEY (plt_owner) REFERENCES T_PILOT_PLT (plt_id)
);

CREATE TABLE IF NOT EXISTS TS_USER_USR (
  usr_id int PRIMARY KEY,
  usr_rol_id int NOT NULL,
  usr_username varchar NOT NULL UNIQUE,
  usr_password varchar NOT NULL,
  usr_enabled boolean DEFAULT true,
  FOREIGN KEY (usr_rol_id) REFERENCES TS_ROLE_ROL (rol_id),
  FOREIGN KEY (usr_id) REFERENCES T_PILOT_PLT (plt_id)
);

/*SCRIPT POUR AJOUTER DES CLES ETRANGERES EN DEHORS DES TABLES
 * 
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'plt_fk_owner_usr') THEN
        ALTER TABLE T_PILOT_PLT
            ADD CONSTRAINT plt_fk_owner_usr
             FOREIGN KEY (plt_owner_usr) REFERENCES TS_USER_USR (usr_id);
    END IF;
END$$;
*/

CREATE TABLE IF NOT EXISTS T_AIRCRAFT_MODEL_AML (
  aml_id SERIAL PRIMARY KEY,
  aml_usr_id int,
  aml_brand varchar,
  aml_model varchar,
  aml_custom_name varchar,
  aml_multi_engine boolean default false,
  aml_multi_pilot boolean default false,
  FOREIGN KEY (aml_usr_id) REFERENCES TS_USER_USR (usr_id)
);

CREATE TABLE IF NOT EXISTS T_AIRCRAFT_ACT (
  act_id SERIAL PRIMARY KEY,
  act_aml_id int,
  act_registration varchar(10),
  act_usr_id int,
  FOREIGN KEY (act_usr_id) REFERENCES TS_USER_USR (usr_id),
  FOREIGN KEY (act_aml_id) REFERENCES T_AIRCRAFT_MODEL_AML (aml_id)
);

CREATE TABLE IF NOT EXISTS TR_AIRPORT_APT (
  apt_id SERIAL PRIMARY KEY,
  apt_name varchar,
  apt_icao_code varchar(10) NOT NULL,
  apt_iata_code varchar(3),
  apt_latitude double precision,
  apt_longitude double precision
);

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_indexes WHERE indexname = 'tr_airport_apt_apt_icao_code_idx') THEN
        CREATE UNIQUE INDEX tr_airport_apt_apt_icao_code_idx ON public.tr_airport_apt (apt_icao_code);
    END IF;
END$$;



CREATE TABLE IF NOT EXISTS T_FLIGHT_FLT (
  flt_id SERIAL PRIMARY KEY,
  flt_usr_id int NOT NULL,
  flt_act_id int,
  flt_plt_id int,
  flt_departure_apt_id int,
  flt_arrival_apt_id int,
  flt_date date NOT NULL,
  flt_departure_time time,
  flt_arrival_time time,
  flt_day_to_nr smallint,
  flt_night_to_nr smallint,
  flt_day_ldg_nr smallint,
  flt_night_ldg_nr smallint,
  flt_multi_pilot boolean,
  flt_multi_engine boolean,
  flt_single_engine_time smallint,
  flt_multi_engine_time smallint,
  flt_night_time smallint,
  flt_instrument_time smallint,
  flt_total_time smallint,
  flt_pic_time smallint,
  flt_multi_pilot_time smallint,
  flt_copilot_time smallint,
  flt_instructor_time smallint,
  flt_dual_time smallint,
  flt_remarks varchar,
  flt_sim_type varchar(100),
  flt_sim_time smallint,
  FOREIGN KEY (flt_usr_id) REFERENCES TS_USER_USR (usr_id),
  FOREIGN KEY (flt_departure_apt_id) REFERENCES TR_AIRPORT_APT (apt_id),
  FOREIGN KEY (flt_arrival_apt_id) REFERENCES TR_AIRPORT_APT (apt_id),
  FOREIGN KEY (flt_act_id) REFERENCES T_AIRCRAFT_ACT (act_id),
  FOREIGN KEY (flt_plt_id) REFERENCES T_PILOT_PLT (plt_id)
);

--ALTER TABLE TS_ROLE_RIGHT_URT ADD FOREIGN KEY (rrt_rol_id) REFERENCES TS_ROLE_ROL (rol_id);
--ALTER TABLE TS_ROLE_RIGHT_URT ADD FOREIGN KEY (rrt_prm_id) REFERENCES TS_PERMISSION_PRM (prm_id);
--ALTER TABLE TS_USER_USR ADD FOREIGN KEY (usr_rol_id) REFERENCES TS_ROLE_ROL (rol_id);
--ALTER TABLE TS_USER_USR ADD FOREIGN KEY (usr_id) REFERENCES T_PILOT_PLT (plt_id);
--ALTER TABLE T_PILOT_PLT ADD FOREIGN KEY (plt_owner) REFERENCES TS_USER_USR (usr_id);
--ALTER TABLE T_AIRCRAFT_MODEL_AML ADD FOREIGN KEY (aml_usr_id) REFERENCES TS_USER_USR (usr_id);
--ALTER TABLE T_AIRCRAFT_ACT ADD FOREIGN KEY (act_usr_id) REFERENCES TS_USER_USR (usr_id);
--ALTER TABLE T_AIRCRAFT_ACT ADD FOREIGN KEY (act_aml_id) REFERENCES T_AIRCRAFT_MODEL_AML (aml_id);
--ALTER TABLE T_FLIGHT_FLT ADD FOREIGN KEY (flt_usr_id) REFERENCES TS_USER_USR (usr_id);
--ALTER TABLE T_FLIGHT_FLT ADD FOREIGN KEY (flt_departure_apt_id) REFERENCES TR_AIRPORT_APT (apt_id);
--ALTER TABLE T_FLIGHT_FLT ADD FOREIGN KEY (flt_arrival_apt_id) REFERENCES TR_AIRPORT_APT (apt_id);
--ALTER TABLE T_FLIGHT_FLT ADD FOREIGN KEY (flt_art_id) REFERENCES T_AIRCRAFT_ACT (act_id);
--ALTER TABLE T_FLIGHT_FLT ADD FOREIGN KEY (flt_plt_id) REFERENCES T_PILOT_PLT (plt_id);


COMMENT ON COLUMN T_FLIGHT_FLT.flt_single_engine_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_multi_engine_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_night_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_instrument_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_total_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_pic_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_multi_pilot_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_instructor_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_dual_time IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_remarks IS 'time in minutes';

COMMENT ON COLUMN T_FLIGHT_FLT.flt_sim_time IS 'time in minutes';
