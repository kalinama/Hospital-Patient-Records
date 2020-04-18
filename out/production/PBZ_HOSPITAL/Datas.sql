CREATE TABLE teacher 
(
    ЛичныйНомер varchar(20) NOT NULL,
    Фамилия varchar(20) NOT NULL,
    Должность varchar(20) NOT NULL,
    Кафедра varchar(20) NOT NULL,
    ТелефонДомашний varchar(20) NOT NULL,
    PRIMARY KEY (ЛичныйНомер)
);

INSERT INTO teacher VALUES 
('221Л','Фролов','Доцент','ЭВМ','487'),
('222Л','Костин','Доцент','ЭВМ','543'),
('225Л','Бойко','Профессор','АСУ','112'),
('430Л','Глазов','Ассистент','ТФ','421'),
('110Л','Петров','Ассистент','Экономики','324');

CREATE DATABASE PBZ_Hospital;
USE PBZ_Hospital;

 drop table Patient_General_Inf, Hospital_Ward, Patient_Treatment_Inf, Hospital_Ward_History, Patient_Description;

CREATE TABLE Patient_General_Inf
(
    id_patient varchar(20) NOT NULL,
    surname varchar(20),
    name varchar(20),
    patronymic varchar(20),
    gender varchar(20) NOT NULL,
    date_of_birth DATE,
    PRIMARY KEY (id_patient)
);


CREATE TABLE Hospital_Ward
(
    id_ward varchar(20) NOT NULL,
    phone_number varchar(20),
    PRIMARY KEY (id_ward)
);

CREATE TABLE Patient_Treatment_Inf
(
    id_patient varchar(20) NOT NULL,
    diagnosis varchar(20) NOT NULL,
    way_of_coming varchar(30) NOT NULL,
    id_ward varchar(20) NOT NULL,
    date_of_coming DATE NOT NULL,
    discharge_date DATE,
    discharge_reason varchar(30),
    PRIMARY KEY (id_patient, date_of_coming),
    FOREIGN KEY (id_patient) REFERENCES Patient_General_Inf (id_patient) ON DELETE CASCADE,
    FOREIGN KEY (id_ward) REFERENCES Hospital_Ward (id_ward) ON UPDATE CASCADE
);

CREATE TABLE Hospital_Ward_History
(
    id_patient varchar(20) NOT NULL,
    id_ward varchar(20) NOT NULL,
    date_of_coming_ward DATE NOT NULL,

    PRIMARY KEY (id_patient, date_of_coming_ward),
    FOREIGN KEY (id_patient) REFERENCES Patient_General_Inf (id_patient) ON DELETE CASCADE,
    FOREIGN KEY (id_ward) REFERENCES Hospital_Ward (id_ward) ON UPDATE CASCADE
);


CREATE TABLE Patient_Description
(
    id_patient varchar(20) NOT NULL,
    height int,
    weight int,
    distinguishing_marks varchar(50),
    PRIMARY KEY (id_patient),
    FOREIGN KEY (id_patient) REFERENCES Patient_General_Inf (id_patient) ON DELETE CASCADE
);


INSERT INTO Patient_General_Inf VALUES 
('1','Фролов','Павел','Семенович','Мужской', '10.09.1987'),
('2','Степаненко','Павел','Валентинович','Мужской', '19.03.1949'),
('3','Иванова','Мария','Михайловна','Женский', '28.04.1997'),
('4','Кулик','Анна','Андреевна','Женский', '15.12.1973');




INSERT INTO Hospital_Ward VALUES 
('10','208-67-89'),
('11','208-56-87'),
('12','208-68-43');

INSERT INTO Patient_Treatment_Inf VALUES 
('1','Киста холедоха','Скорая помощь','11','11.09.2019', '13.10.2019', 'Выздоровление'),
('2','Водянка сердца','Скорая помощь','11','11.12.2019', NULL, NULL),
('3','Дискинезия пищевода','Поликлиника','12','23.01.2019', '01.02.2019', 'Выздоровление'),
('2','Водянка сердца','Поликлиника','10','23.10.2018', '01.12.2018', 'Выздоровление'),
('4','Дерматоз','Поликлиника','10','09.12.2019', NULL, NULL);

INSERT INTO Patient_Treatment_Inf VALUES 
('1','Киста холедоха','Скорая помощь','11','21.09.2018');

INSERT INTO Hospital_Ward_History VALUES 
('1','10','11.09.2019'),
('1','11','18.09.2019'),
('2','11','11.12.2019'),
('3','12','23.01.2019'),
('4','10','09.12.2019'),
('2','11','23.10.2018'),
('2','10','20.11.2018');

INSERT INTO Patient_Description VALUES 
('1','178','90','Большие уши'),
('2','165','60', NULL),
('3','189','76',NULL),
('4','168','49',NULL);


 drop table Patient_General_Inf, Hospital_Ward, Patient_Treatment_Inf, Hospital_Ward_History, Patient_Description;


//Запрос1 

SELECT DISTINCT Patient_General_Inf.id_patient, surname, name, patronymic, gender, date_of_birth, height, weight, distinguishing_marks FROM Patient_General_Inf
    INNER JOIN Patient_Description
    ON Patient_General_Inf.id_patient = Patient_Description.id_patient;
//Запрос2
    SELECT id_patient, diagnosis, way_of_coming, Patient_Treatment_Inf.id_ward,
    date_of_coming, discharge_date, discharge_reason, phone_number 
    FROM Patient_Treatment_Inf INNER JOIN Hospital_Ward
    ON Patient_Treatment_Inf.id_ward = Hospital_Ward.id_ward
    WHERE id_patient = '2' ORDER BY date_of_coming ASC;
//Запрос3
SELECT id_patient, Hospital_Ward.id_ward, phone_number, date_of_coming_ward
FROM Hospital_Ward_History INNER JOIN Hospital_Ward
ON Hospital_Ward_History.id_ward = Hospital_Ward.id_ward
WHERE id_patient = '1' ORDER BY date_of_coming_ward ASC;
//Запрос4
SELECT * FROM Hospital_Ward;
 //выписка
 UPDATE Patient_Treatment_Inf SET discharge_date =?, discharge_reason =? WHERE id_patient =? AND date_of_coming =?;
//редактир данных
UPDATE Patient_General_Inf SET name =?, surname =?, patronymic =?, date_of_birth=?, gender=? WHERE id_patient =?;

UPDATE Patient_Description SET weight =?, height =?, distinguishing_marks =? WHERE id_patient =?;

//редактир
 UPDATE Patient_Treatment_Inf SET diagnosis =?, way_of_coming =? WHERE id_patient =? AND date_of_coming =?;
//первод в др палату
INSERT INTO Hospital_Ward_History VALUES (?,?,?);
UPDATE Patient_Treatment_Inf SET id_ward =? WHERE id_patient =? AND date_of_coming =?;
//добавить новое поступление
INSERT INTO Patient_Treatment_Inf VALUES (?,?,?,?,?,NULL,NULL);
INSERT INTO Hospital_Ward_History VALUES (?,?,?);
//добавить нового пациента
INSERT INTO Patient_General_Inf VALUES (?,?,?,?,?,?);
INSERT INTO Patient_Description VALUES (?,?,?,?);
INSERT INTO Patient_Treatment_Inf VALUES (?,?,?,?,?,NULL,NULL);
//delete Patient
DELETE FROM Patient_general_inf WHERE id_patient=?;
//запрос-1
SELECT Patient_General_Inf.id_patient, name, surname, patronymic, date_of_birth, Patient_Treatment_Inf.id_ward, discharge_date, phone_number
FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf
ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient
INNER JOIN Hospital_Ward ON Patient_Treatment_Inf.id_ward = Hospital_Ward.id_ward
WHERE discharge_date IS NULL AND name =? AND surname =? AND patronymic = ?;
//запрос-2
SELECT Patient_General_Inf.id_patient, name, surname, patronymic, date_of_birth, Patient_Treatment_Inf.id_ward, date_of_coming, discharge_date
FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf
ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient
WHERE (discharge_date>? AND date_of_coming<?) OR (discharge_date IS NULL AND date_of_coming<?);
//запрос-3
SELECT Patient_General_Inf.id_patient, name, surname, patronymic, gender, date_of_birth, discharge_date,
EXTRACT(YEAR FROM AGE(current_date, date_of_birth)) AS age
FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf
ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient
WHERE discharge_date IS NULL AND gender='Женский' AND EXTRACT(YEAR FROM AGE(current_date, date_of_birth))>=?;
//изменить палату
UPDATE Hospital_Ward SET id_ward=?, phone_number=? WHERE id_ward=?;
//добавить палату

SELECT EXTRACT(YEAR FROM AGE(TIMESTAMP '20.04.2000'));
SELECT Patient_General_Inf.id_patient, name, surname, patronymic, date_of_birth, Patient_Treatment_Inf.id_ward, date_of_coming, discharge_date
FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf
ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient
WHERE discharge_date>'25.11.2019'  OR (discharge_date IS NULL AND date_of_coming<'25.11.2019');


SELECT * FROM Patient_Treatment_Inf WHERE id_patient = '1' AND date_of_coming = '11.09.2019';

 UPDATE Patient_Treatment_Inf SET discharge_date ='15.12.2019', discharge_reason ='Выздоровление' WHERE id_patient ='2' AND date_of_coming ='11.12.2019';

UPDATE Patient_Treatment_Inf SET discharge_date ='2019-12-15 +03', discharge_reason ='d' WHERE id_patient ='4' AND date_of_coming ='2019-12-09'