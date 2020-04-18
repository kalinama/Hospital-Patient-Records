
/*1. Запрос на получение общей информации о пациенте

SELECT DISTINCT Patient_General_Inf.id_patient, surname, name, patronymic, gender, date_of_birth, height, weight, distinguishing_marks FROM Patient_General_Inf
    INNER JOIN Patient_Description
    ON Patient_General_Inf.id_patient = Patient_Description.id_patient;

2. Запрос на получение информации о поступлениях пациента в больницу

    SELECT id_patient, diagnosis, way_of_coming, Patient_Treatment_Inf.id_ward,
    date_of_coming, discharge_date, discharge_reason, phone_number 
    FROM Patient_Treatment_Inf INNER JOIN Hospital_Ward
    ON Patient_Treatment_Inf.id_ward = Hospital_Ward.id_ward
    WHERE id_patient = '2' ORDER BY date_of_coming ASC;

3. Запрос на получение информации о переводах пациента в разные палаты

SELECT id_patient, Hospital_Ward.id_ward, phone_number, date_of_coming_ward
FROM Hospital_Ward_History INNER JOIN Hospital_Ward
ON Hospital_Ward_History.id_ward = Hospital_Ward.id_ward
WHERE id_patient = '1' ORDER BY date_of_coming_ward ASC;

4. Запрос на получение информации о палатах 

SELECT * FROM Hospital_Ward;
5. Выписка пациента
 UPDATE Patient_Treatment_Inf SET discharge_date =?, discharge_reason =? WHERE id_patient =? AND date_of_coming =?;
6. Редактирование общей информации о пациенте

UPDATE Patient_General_Inf SET name =?, surname =?, patronymic =?, date_of_birth=?, gender=? WHERE id_patient =?;

UPDATE Patient_Description SET weight =?, height =?, distinguishing_marks =? WHERE id_patient =?;

7. Редактирование информации о поступлении пациента

 UPDATE Patient_Treatment_Inf SET diagnosis =?, way_of_coming =? WHERE id_patient =? AND date_of_coming =?;

8. Перевести пациента в другую палату

INSERT INTO Hospital_Ward_History VALUES (?,?,?);

UPDATE Patient_Treatment_Inf SET id_ward =? WHERE id_patient =? AND date_of_coming =?;

9.Добавить новое поступление пациента

INSERT INTO Patient_Treatment_Inf VALUES (?,?,?,?,?,NULL,NULL);
INSERT INTO Hospital_Ward_History VALUES (?,?,?);

10. Зарегистрировать нового пациента

INSERT INTO Patient_General_Inf VALUES (?,?,?,?,?,?);

INSERT INTO Patient_Description VALUES (?,?,?,?);

INSERT INTO Patient_Treatment_Inf VALUES (?,?,?,?,?,NULL,NULL);

11.Удалить пациента

DELETE FROM Patient_general_inf WHERE id_patient=?;

12.Запрос на просмотр телефона и номера палаты заданного больного (больной м.б. определен по ФИО, если однофамильцев несколько, указать всех, при этом выводить возраст больного).

SELECT Patient_General_Inf.id_patient, name, surname, patronymic, date_of_birth, Patient_Treatment_Inf.id_ward, discharge_date, phone_number
FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf
ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient
INNER JOIN Hospital_Ward ON Patient_Treatment_Inf.id_ward = Hospital_Ward.id_ward
WHERE discharge_date IS NULL AND name =? AND surname =? AND patronymic = ?;

13. Запрос на просмотр списка всех больных на заданное число – дата, номер палаты, ФИО больного, возраст, дата поступления.

SELECT Patient_General_Inf.id_patient, name, surname, patronymic, date_of_birth, Patient_Treatment_Inf.id_ward, date_of_coming, discharge_date
FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf
ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient
WHERE (discharge_date>? AND date_of_coming<?) OR (discharge_date IS NULL AND date_of_coming<?);

14. Запрос на просмотр списка больных, достигших заданного возраста женского пола на текущую дату– дата, ФИО больного, возраст.

SELECT Patient_General_Inf.id_patient, name, surname, patronymic, gender, date_of_birth, discharge_date,
EXTRACT(YEAR FROM AGE(current_date, date_of_birth)) AS age
FROM Patient_General_Inf INNER JOIN Patient_Treatment_Inf
ON Patient_General_Inf.id_patient= Patient_Treatment_Inf.id_patient
WHERE discharge_date IS NULL AND gender='Женский' AND EXTRACT(YEAR FROM AGE(current_date, date_of_birth))>=?;

15. Редактирование информации о палате
UPDATE Hospital_Ward SET id_ward=?, phone_number=? WHERE id_ward=?;
16. Добавить новую палату
INSERT INTO Hospital_Ward VALUES (?,?);
*/
