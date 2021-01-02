delete from T_FLIGHT_FLT;
delete from TR_AIRPORT_APT;
delete from T_AIRCRAFT_ACT;
delete from T_AIRCRAFT_MODEL_AML;
delete from TS_USER_USR;
delete from T_PILOT_PLT;
delete from TS_ROLE_RIGHT_URT;
delete from TS_ROLE_ROL;
delete from TS_PERMISSION_PRM;

INSERT INTO TS_PERMISSION_PRM(prm_context, prm_operation) VALUES
('role', 'create'),
('role', 'read-all'),
('role', 'read'),
('role', 'delete'),
('role', 'delete-all'),
('role', 'update'),
('role', 'update-all'),
('flight', 'create'),
('flight', 'read-all'),
('flight', 'read'),
('flight', 'delete'),
('flight', 'delete-all'),
('flight', 'update'),
('flight', 'update-all');

INSERT INTO TS_ROLE_ROL (rol_name) VALUES
('ADMIN'),
('USER');

INSERT INTO TS_ROLE_RIGHT_URT (rrt_rol_id, rrt_prm_id) VALUES 
((SELECT rol_id FROM TS_ROLE_ROL where rol_name='USER'), (SELECT prm_id FROM TS_PERMISSION_PRM WHERE prm_context='role' AND prm_operation = 'read')),
((SELECT rol_id FROM TS_ROLE_ROL where rol_name='USER'), (SELECT prm_id FROM TS_PERMISSION_PRM WHERE prm_context='flight' AND prm_operation = 'read')),
((SELECT rol_id FROM TS_ROLE_ROL where rol_name='USER'), (SELECT prm_id FROM TS_PERMISSION_PRM WHERE prm_context='flight' AND prm_operation = 'create')),
((SELECT rol_id FROM TS_ROLE_ROL where rol_name='USER'), (SELECT prm_id FROM TS_PERMISSION_PRM WHERE prm_context='flight' AND prm_operation = 'delete')),
((SELECT rol_id FROM TS_ROLE_ROL where rol_name='USER'), (SELECT prm_id FROM TS_PERMISSION_PRM WHERE prm_context='flight' AND prm_operation = 'update'));

INSERT INTO t_pilot_plt (plt_last_name, plt_first_name, plt_email, plt_phone_number) VALUES
('Lefebvre', 'Colin', 'colin.lefebvre1@gmail.com', '+33675605506');

UPDATE t_pilot_plt SET plt_owner=(select plt_id from t_pilot_plt WHERE plt_email='colin.lefebvre1@gmail.com' limit 1) WHERE plt_email='colin.lefebvre1@gmail.com';

INSERT INTO ts_user_usr (usr_id, usr_rol_id, usr_username, usr_password, usr_enabled) VALUES
((select plt_id from t_pilot_plt WHERE plt_email='colin.lefebvre1@gmail.com' limit 1), (SELECT rol_id FROM TS_ROLE_ROL where rol_name='ADMIN'), 'colin.lefebvre1@gmail.com', '$2a$10$g3R7wF.Jv4pSmNBtr.8zfONfoePKd1/5puQOCglM56lD6QtYLjEK.', true);

INSERT INTO t_aircraft_model_aml (aml_usr_id, aml_brand, aml_model, aml_custom_name) VALUES
((select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com'), 'Diamond', 'DA40', 'DA40-TDI'),
((select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com'), 'Robin', 'HR200', 'HR200');

INSERT INTO t_aircraft_act (act_aml_id, act_registration, act_usr_id) VALUES
((select aml_id from t_aircraft_model_aml where aml_model = 'DA40'  and aml_usr_id = (select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com') ), 'F-HZIC', (select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com')),
((select aml_id from t_aircraft_model_aml where aml_model = 'DA40'  and aml_usr_id = (select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com') ), 'F-HDJE', (select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com')),
((select aml_id from t_aircraft_model_aml where aml_model = 'DA40'  and aml_usr_id = (select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com') ), 'F-HDJG', (select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com')),
((select aml_id from t_aircraft_model_aml where aml_model = 'HR200' and aml_usr_id = (select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com') ), 'F-GTPO', (select usr_id from ts_user_usr WHERE usr_username='colin.lefebvre1@gmail.com'));


