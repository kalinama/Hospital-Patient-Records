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


