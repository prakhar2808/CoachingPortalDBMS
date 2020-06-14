create table Admin( AdminId int not null auto_increment, Password varchar(500) not null, primary key(AdminId));


create table course(CourseId varchar(7) not null, CourseName varchar(30) not null, FeesPerMonth int(11) not null, primary key(CourseId));

delimiter $

create trigger check_insert_feespermonth before insert on course for each row begin if new.FeesPerMonth<0 then signal sqlstate '12345' set message_text='Fees cannot be < 0'; end if; end$

delimiter ;

delimiter $

create trigger check_update_feespermonth before update on course for each row begin if new.FeesPerMonth<0 then signal sqlstate '12345' set message_text='Fees cannot be < 0'; end if; end$

delimiter ;

CREATE TABLE `guardian` (
  `GuardianId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Dob` date NOT NULL,
  `HouseNo` varchar(5) NOT NULL,
  `Street` varchar(20) NOT NULL,
  `City` varchar(20) NOT NULL,
  `Occupation` varchar(20) NOT NULL,
  `Password` varchar(500) NOT NULL,
  PRIMARY KEY (`GuardianId`)
);

CREATE TABLE `staff` (
  `StaffId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Dob` date NOT NULL,
  `HouseNo` varchar(5) NOT NULL,
  `Street` varchar(20) NOT NULL,
  `City` varchar(20) NOT NULL,
  `job` varchar(20) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `AccountNo` varchar(20) NOT NULL,
  `Salary` int(11) NOT NULL,
  PRIMARY KEY (`StaffId`)
);

delimiter $

create trigger check_insertsalarystaff before insert on staff for each row begin if new.Salary<0 then signal sqlstate '12345' set message_text='Salary must be >0';end if; end$

create trigger check_updatesalarystaff before update on staff for each row begin if new.Salary<0 then signal sqlstate '12345' set message_text='Salary must be >0';end if; end$

delimiter ;

CREATE TABLE `student` (
  `RollNo` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Dob` date NOT NULL,
  `HouseNo` varchar(5) NOT NULL,
  `Street` varchar(20) NOT NULL,
  `City` varchar(20) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `GuardianId` int(11) DEFAULT NULL,
  PRIMARY KEY (`RollNo`),
  KEY `GuardianId` (`GuardianId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`GuardianId`) REFERENCES `guardian` (`guardianid`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `teacher` (
  `TeacherId` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `Gender` char(1) NOT NULL,
  `Dob` date NOT NULL,
  `HouseNo` varchar(5) NOT NULL,
  `Street` varchar(20) NOT NULL,
  `City` varchar(20) NOT NULL,
  `Password` varchar(500) NOT NULL,
  `AccountNo` varchar(20) NOT NULL,
  `Salary` int(11) NOT NULL,
  PRIMARY KEY (`TeacherId`)
);

delimiter $

create trigger check_insertsalaryteacher before insert on teacher for each row begin if new.Salary<0 then signal sqlstate '12345' set message_text='Salary must be >0';end if; end$

create trigger check_updatesalaryteacher before update on teacher for each row begin if new.Salary<0 then signal sqlstate '12345' set message_text='Salary must be >0';end if; end$

delimiter ;

CREATE TABLE `batch` (
  `BatchId` int(11) NOT NULL,
  `CourseId` varchar(7) NOT NULL,
  `RoomNo` int(11) NOT NULL,
  `StartTiming` time NOT NULL,
  `EndTiming` time NOT NULL,
  PRIMARY KEY (`BatchId`,`CourseId`),
  KEY `fk7` (`CourseId`),
  CONSTRAINT `fk7` FOREIGN KEY (`CourseId`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE
);

delimiter $

create trigger checktime_insertbatch before insert on batch for each row begin if new.StartTiming>new.EndTiming then signal sqlstate '12345' set message_text='Invalid time'; end if; end$

create trigger checktime_updatebatch before update on batch for each row begin if new.StartTiming>new.EndTiming then signal sqlstate '12345' set message_text='Invalid time'; end if; end$

delimiter ;

CREATE TABLE `enrollment` (
  `TransacationNo` varchar(15) NOT NULL,
  `JoinDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `EnrolledBy` int(11) DEFAULT NULL,
  `CourseId` varchar(7) NOT NULL,
  `RollNo` int(11) NOT NULL,
  `BatchId` int(11) NOT NULL,
  PRIMARY KEY (`RollNo`,`CourseId`,`BatchId`),
  UNIQUE KEY `TransacationNo` (`TransacationNo`),
  KEY `fk1` (`EnrolledBy`),
  KEY `fk2` (`CourseId`),
  KEY `BatchId` (`BatchId`,`CourseId`),
  CONSTRAINT `enrollment_ibfk_1` FOREIGN KEY (`RollNo`) REFERENCES `student` (`rollno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `enrollment_ibfk_2` FOREIGN KEY (`BatchId`, `CourseId`) REFERENCES `batch` (`batchid`, `courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk1` FOREIGN KEY (`EnrolledBy`) REFERENCES `staff` (`staffid`) ON DELETE SET NULL ON UPDATE CASCADE
);

delimiter $

create trigger checkdate_insertenroll before insert on enrollment for each row begin if new.JoinDate>new.EndDate then signal sqlstate '12345' set message_text='Invalid dates'; end if; end$

create trigger checkdate_updateenroll before update on enrollment for each row begin if new.JoinDate>new.EndDate then signal sqlstate '12345' set message_text='Invalid dates'; end if; end$

delimiter ;

CREATE TABLE `exam` (
  `ExamId` varchar(10) NOT NULL,
  `CourseId` varchar(7) NOT NULL,
  `ExamName` varchar(15) NOT NULL,
  `ExamDate` date NOT NULL,
  `StartTiming` time NOT NULL,
  `EndTiming` time NOT NULL,
  PRIMARY KEY (`ExamId`,`CourseId`),
  KEY `fk8` (`CourseId`),
  CONSTRAINT `fk8` FOREIGN KEY (`CourseId`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE
);

delimiter $

create trigger checktime_insertexam before insert on exam for each row begin if new.StartTiming>new.EndTiming then signal sqlstate '12345' set message_text='Invalid time'; end if; end$

create trigger checktime_updateexam before update on exam for each row begin if new.StartTiming>new.EndTiming then signal sqlstate '12345' set message_text='Invalid time'; end if; end$

delimiter ;

CREATE TABLE `result` (
  `ExamId` varchar(10) NOT NULL,
  `CourseId` varchar(7) NOT NULL,
  `ResultDeclarationDate` date NOT NULL,
  `StudentRank` int(11) NOT NULL,
  `RollNo` int(11) NOT NULL,
  `marks` int(11) NOT NULL,
  PRIMARY KEY (`RollNo`,`ExamId`,`CourseId`),
  KEY `fk9` (`ExamId`,`CourseId`),
  KEY `fk10` (`RollNo`),
  CONSTRAINT `fk10` FOREIGN KEY (`RollNo`) REFERENCES `student` (`rollno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk9` FOREIGN KEY (`ExamId`, `CourseId`) REFERENCES `exam` (`examid`, `courseid`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `staffAttendance` (
  `StaffId` int(11) NOT NULL,
  `date` date NOT NULL,
  `isPresent` varchar(3) NOT NULL,
  PRIMARY KEY (`StaffId`,`date`),
  CONSTRAINT `fk6` FOREIGN KEY (`StaffId`) REFERENCES `staff` (`staffid`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `studentAttendance` (
  `RollNo` int(11) NOT NULL,
  `date` date NOT NULL,
  `isPresent` varchar(3) NOT NULL,
  `CourseId` varchar(7) DEFAULT NULL,
  `BatchId` int(11) DEFAULT NULL,
  PRIMARY KEY (`RollNo`,`date`),
  KEY `BatchId` (`BatchId`,`CourseId`),
  CONSTRAINT `fk4` FOREIGN KEY (`RollNo`) REFERENCES `student` (`rollno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentAttendance_ibfk_1` FOREIGN KEY (`BatchId`, `CourseId`) REFERENCES `batch` (`batchid`, `courseid`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `studyMaterial` (
  `MaterialCode` varchar(10) NOT NULL,
  `CourseId` varchar(7) NOT NULL,
  `TopicName` varchar(20) NOT NULL,
  `Level` int(11) NOT NULL,
  PRIMARY KEY (`MaterialCode`,`CourseId`),
  KEY `fk11` (`CourseId`),
  CONSTRAINT `fk11` FOREIGN KEY (`CourseId`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `subjects` (
  `CourseId` varchar(7) NOT NULL,
  `SubjectName` varchar(30) NOT NULL,
  PRIMARY KEY (`CourseId`,`SubjectName`),
  CONSTRAINT `fk` FOREIGN KEY (`CourseId`) REFERENCES `course` (`courseid`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `teacherAttendance` (
  `TeacherId` int(11) NOT NULL,
  `date` date NOT NULL,
  `isPresent` varchar(3) NOT NULL,
  PRIMARY KEY (`TeacherId`,`date`),
  CONSTRAINT `fk5` FOREIGN KEY (`TeacherId`) REFERENCES `teacher` (`teacherid`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `teaches` (
  `BatchId` int(11) NOT NULL,
  `CourseId` varchar(7) NOT NULL,
  `TeacherId` int(11) NOT NULL,
  PRIMARY KEY (`BatchId`,`CourseId`,`TeacherId`),
  KEY `fk31` (`TeacherId`),
  CONSTRAINT `fk30` FOREIGN KEY (`BatchId`, `CourseId`) REFERENCES `batch` (`batchid`, `courseid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk31` FOREIGN KEY (`TeacherId`) REFERENCES `teacher` (`teacherid`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO `Admin` VALUES (1,'$2a$10$vHCFNk2xrvwjvwp.uLrz7eHwbNVbQj9ZAzrAc.oug6vVETsrKdZMm');

INSERT INTO `course` VALUES ('ASPIRE','NEET 2 YEAR',3400),('SPARK','JEE 1 YEAR',8000),('SWIFT','JEE 2 YEAR',4000);

INSERT INTO `guardian` VALUES (1,'Arvind','Sinha','M','1963-01-19','7','NML Flats','Ghaziabad','Scientist','$2a$10$cGmtoQGw.1ICJHe6l8nTtuf9/kQMvw0U5VymlFnmEbg1p0fYdr1T2'),(2,'Manish','Saxena','M','1966-01-01','29','NML Flats','Ghaziabad','Professor','$2a$10$BefTY3iC7LICDTb/hYtz5.yh5YFfrPzGLfvxmOuSud/.bU6JVLRDm'),(5,'Raghuveer','Singh','M','1965-10-10','3','NML Flats','Ghaziabad','Teacher','$2a$10$BUCc5JPCLlSSy31M6AnW5O2Zjb4GFdqDpz8z.oWB/VuJw0TBebxbS'),(6,'Swapan','Das','M','1962-09-04','5','NML Flats','Ghaziabad','Scientist','$2a$10$Tpog2BwS/GJolzFC3SIAHeRFWpMXb1G/GaNwjW3TXAJ9.TkUjzaO2');

INSERT INTO `staff` VALUES (2,'Raman','Khan','M','1986-03-21','47','CVR','Ghaziabad','Receptionist','$2a$10$88U/3E28uScUeru/t8sqyOZlwcg1p1ELx7Jml0p6dyTlA2yoc4tS2','202254558957',40500),(3,'Arpita','Singh','F','1990-11-11','32','CVR','Ghaziabad','Receptionist','$2a$10$Tb7TYnI1HLO.wnVAkJ2R8OnDiPvyBZVS4ch3BiHkkYuEwmQ3uD6/C','8521478958',50000),(4,'Vikas','Sharma','M','1984-01-02','20','CVR','Ghaziabad','System Analyst','$2a$10$X94ev9Yrd5J3InxxzELVbuRGbBwCbrrbhqhpGDYR4186ua.FUvUeu','8958575125',60000);

INSERT INTO `student` VALUES (8,'Prakhar','Sinha','M','1997-08-28','7','NML Flats','Ghaziabad','$2a$10$dvbL3GPwezIgkhrMh3loXu.R/3OdSN1DG9aaFmhWTCDiLqhBFCS1q',1),(9,'Aayushi','Sinha','F','1997-08-23','7','NML Flats','Ghaziabad','$2a$10$P6X6gq69KVwldTy5wKWp3.N2J7gFUO7Ysr3V43FE4vJt5f0rF7Qwq',1),(10,'Aniruddha','Das','M','1997-05-06','5','NML Flats','Ghaziabad','$2a$10$bOPkDDlJXaEbAUdZq5rXjeZNCKPXzFZpkmL9pV7/lCPLBLjljKWrq',6),(11,'Malvika','Saxena','F','1998-08-21','29','NML Flats','Ghaziabad','$2a$10$pYIgGyKr4xH5GctsH74ZsOqAot1ToNNoqQ2iFCRF6JCRESuZRnCMS',2),(12,'Utkarsh','Singh','M','1999-06-12','3','NML Flats','Ghaziabad','$2a$10$GseKLceODsHB4E8AT5DNRupefKPNFtexf1A7OZtEo7M0GQymbDKbO',5),(13,'Vaishnavi','Singh','F','1998-01-28','3','NML Flats','Ghaziabad','$2a$10$HaSKkOEZsHx.yBnoZJhmlOPAmr4T84yklRrkXiDnnjgdp2L4AkZjO',5),(15,'Naveen','Punjabi','M','1998-08-31','40','NML Flats','Ghaziabad','$2a$10$AZTLn8qXJQkZU60KeIJgGeok4mgUjUilfhEtD7GwQ5XK6B63wPY12',2),(16,'Anurag','Tyagi','M','1998-02-11','21','NML Flats','Ghaziabad','$2a$10$J7CbGJoIDBWkgQzcSr.KtOkzqgIJ276UldNN4C9624Oy3Q5tL5/ue',2),(17,'Pallav','Singh','M','1997-10-02','191','NML Flats','Ghaziabad','$2a$10$hgQ.Q8sdwBNkwOk27xz6MuoCxj31AI4EoUb.Vo/s97cZsLOzMsI1i',6);

INSERT INTO `teacher` VALUES (2,'Shyam','Bhushan','M','1974-09-09','21','MOR','Ghaziabad','$2a$10$tvAWAnZKkr5qTAicvWOSUO.an80OCm3f7rThBpCgny0KsiK6/Cu0W','1258987458',100500),(3,'Sanjay','Ranjan','M','1975-01-01','98','MORV','Ghaziabad','$2a$10$YrWaWGqW3uJVYTWx6uYIXO9sjFpK0ahe5XQgRCXemd7ReBDh2Qc02','3875895648',95000),(4,'Ajay','Srivastava','M','1984-06-15','45','MORV','Ghaziabad','$2a$10$lYBG3dWJnbkWgpTeUKMJPuXIJyGOxTLLG0MIbvsANSdSq.BwbYj9C','2587413698',84000);

INSERT INTO `batch` VALUES (1,'ASPIRE',102,'09:00:00','13:30:00'),(1,'SPARK',101,'16:00:00','20:30:00'),(1,'SWIFT',301,'15:00:00','19:30:00'),(2,'SPARK',101,'09:00:00','13:30:00');

INSERT INTO `enrollment` VALUES ('1236547895','2018-08-01','2019-05-01',3,'SPARK',8,1),('5852369754','2018-08-01','2019-05-01',3,'SPARK',9,1),('7896541265','2018-08-01','2019-05-01',3,'SPARK',10,2),('12369858998','2018-08-01','2020-05-01',3,'ASPIRE',11,1),('8949849849','2018-08-01','2020-05-01',3,'SWIFT',12,1),('2654988915','2018-08-01','2020-08-01',3,'SWIFT',13,1),('3546726353','2018-08-01','2020-05-01',4,'ASPIRE',15,1),('3568547852','2018-08-01','2020-05-01',3,'ASPIRE',16,1),('1236548985','2018-08-01','2019-05-01',3,'SPARK',17,2);

INSERT INTO `exam` VALUES ('ASCPT1','ASPIRE','CPT-1','2018-09-01','09:00:00','12:00:00'),('ASCPT2','ASPIRE','CPT-2','2018-09-08','09:00:00','12:00:00'),('ASCPT3','ASPIRE','CPT-3','2018-09-15','09:00:00','12:00:00'),('ASCPT4','ASPIRE','CPT-4','2018-09-22','09:00:00','12:00:00'),('ASCPT5','ASPIRE','CPT-5','2018-09-29','09:00:00','12:00:00'),('SPAIT1','SPARK','AIT1','2018-09-01','16:00:00','19:00:00'),('SPAIT2','SPARK','AIT2','2018-09-02','09:00:00','12:00:00'),('SPAIT3','SPARK','AIT3','2018-09-08','16:00:00','19:00:00'),('SPAIT4','SPARK','AIT4','2018-09-09','09:00:00','12:00:00'),('SWEXM1','SWIFT','EXM-1','2018-09-01','12:30:00','15:30:00'),('SWEXM2','SWIFT','EXM-2','2018-09-08','12:30:00','15:30:00'),('SWEXM3','SWIFT','EXM-3','2018-09-15','12:30:00','15:30:00'),('SWEXM4','SWIFT','EXM-4','2018-09-22','12:30:00','15:30:00');

INSERT INTO `result` VALUES ('SPAIT1','SPARK','2018-09-02',1,8,250),('SPAIT2','SPARK','2018-09-03',3,8,216),('SPAIT3','SPARK','2018-09-09',2,8,187),('SPAIT4','SPARK','2018-09-10',3,8,218),('SPAIT1','SPARK','2018-09-02',3,9,214),('SPAIT2','SPARK','2018-09-03',1,9,275),('SPAIT3','SPARK','2018-09-09',1,9,190),('SPAIT4','SPARK','2018-09-10',1,9,230),('SPAIT1','SPARK','2018-09-02',4,10,185),('SPAIT2','SPARK','2018-09-03',2,10,217),('SPAIT3','SPARK','2018-09-09',4,10,143),('SPAIT4','SPARK','2018-09-10',4,10,190),('ASCPT1','ASPIRE','2018-09-02',2,11,210),('ASCPT2','ASPIRE','2018-09-09',3,11,185),('ASCPT3','ASPIRE','2018-09-16',1,11,241),('ASCPT4','ASPIRE','2018-09-23',1,11,199),('ASCPT5','ASPIRE','2018-09-30',3,11,210),('SWEXM1','SWIFT','2018-09-02',1,12,215),('SWEXM2','SWIFT','2018-09-09',2,12,225),('SWEXM3','SWIFT','2018-09-16',1,12,198),('SWEXM4','SWIFT','2018-09-23',1,12,250),('SWEXM1','SWIFT','2018-09-02',2,13,178),('SWEXM2','SWIFT','2018-09-09',1,13,245),('SWEXM3','SWIFT','2018-09-16',2,13,156),('SWEXM4','SWIFT','2018-09-23',2,13,219),('ASCPT1','ASPIRE','2018-09-02',1,15,253),('ASCPT2','ASPIRE','2018-09-09',1,15,215),('ASCPT3','ASPIRE','2018-09-16',2,15,225),('ASCPT4','ASPIRE','2018-09-23',3,15,175),('ASCPT5','ASPIRE','2018-09-30',1,15,254),('ASCPT1','ASPIRE','2018-09-02',3,16,187),('ASCPT2','ASPIRE','2018-09-09',2,16,201),('ASCPT3','ASPIRE','2018-09-16',3,16,198),('ASCPT4','ASPIRE','2018-09-23',2,16,189),('ASCPT5','ASPIRE','2018-09-30',2,16,225),('SPAIT1','SPARK','2018-09-02',2,17,245),('SPAIT2','SPARK','2018-09-03',4,17,174),('SPAIT3','SPARK','2018-09-09',3,17,161),('SPAIT4','SPARK','2018-09-10',2,17,220);

INSERT INTO `staffAttendance` VALUES (2,'2018-08-10','YES'),(2,'2018-08-11','YES'),(2,'2018-09-03','YES'),(2,'2018-09-04','YES'),(2,'2018-09-05','YES'),(2,'2018-09-06','YES'),(2,'2018-09-07','YES'),(3,'2018-08-10','YES'),(3,'2018-08-11','NO'),(3,'2018-09-03','YES'),(3,'2018-09-04','YES'),(3,'2018-09-05','YES'),(3,'2018-09-06','YES'),(3,'2018-09-07','YES'),(4,'2018-08-10','YES'),(4,'2018-08-11','YES'),(4,'2018-09-03','YES'),(4,'2018-09-04','YES'),(4,'2018-09-05','YES'),(4,'2018-09-06','YES'),(4,'2018-09-07','NO');

INSERT INTO `studentAttendance` VALUES (8,'2018-08-10','YES','SPARK',1),(8,'2018-08-11','NO','SPARK',1),(8,'2018-08-12','NO','SPARK',1),(8,'2018-09-03','YES','SPARK',1),(8,'2018-09-04','NO','SPARK',1),(8,'2018-09-05','YES','SPARK',1),(8,'2018-09-06','YES','SPARK',1),(8,'2018-09-07','YES','SPARK',1),(9,'2018-08-10','YES','SPARK',1),(9,'2018-08-11','YES','SPARK',1),(9,'2018-08-12','YES','SPARK',1),(9,'2018-09-03','YES','SPARK',1),(9,'2018-09-04','YES','SPARK',1),(9,'2018-09-05','YES','SPARK',1),(9,'2018-09-06','YES','SPARK',1),(9,'2018-09-07','YES','SPARK',1),(10,'2018-08-10','YES','SPARK',2),(10,'2018-09-03','YES','SPARK',2),(10,'2018-09-04','YES','SPARK',2),(10,'2018-09-05','NO','SPARK',2),(10,'2018-09-06','NO','SPARK',2),(10,'2018-09-07','YES','SPARK',2),(11,'2018-09-03','NO','ASPIRE',1),(11,'2018-09-04','YES','ASPIRE',1),(11,'2018-09-05','YES','ASPIRE',1),(11,'2018-09-06','YES','ASPIRE',1),(11,'2018-09-07','YES','ASPIRE',1),(11,'2018-10-20','YES','ASPIRE',1),(12,'2018-09-03','YES','SWIFT',1),(12,'2018-09-04','YES','SWIFT',1),(12,'2018-09-05','YES','SWIFT',1),(12,'2018-09-06','NO','SWIFT',1),(12,'2018-09-07','NO','SWIFT',1),(13,'2018-09-03','YES','SWIFT',1),(13,'2018-09-04','YES','SWIFT',1),(13,'2018-09-05','NO','SWIFT',1),(13,'2018-09-06','YES','SWIFT',1),(13,'2018-09-07','YES','SWIFT',1),(15,'2018-09-03','YES','ASPIRE',1),(15,'2018-09-04','YES','ASPIRE',1),(15,'2018-09-05','NO','ASPIRE',1),(15,'2018-09-06','NO','ASPIRE',1),(15,'2018-09-07','YES','ASPIRE',1),(16,'2018-09-03','YES','ASPIRE',1),(16,'2018-09-04','YES','ASPIRE',1),(16,'2018-09-05','YES','ASPIRE',1),(16,'2018-09-06','YES','ASPIRE',1),(16,'2018-09-07','NO','ASPIRE',1),(17,'2018-09-03','YES','SPARK',2),(17,'2018-09-04','NO','SPARK',2),(17,'2018-09-05','YES','SPARK',2),(17,'2018-09-06','YES','SPARK',2),(17,'2018-09-07','YES','SPARK',2);

INSERT INTO `studyMaterial` VALUES ('ASBIO11','ASPIRE','Cells & Tissues',1),('ASBIO12','ASPIRE','Cells & Tissues',2),('ASBIO21','ASPIRE','Kingdoms',1),('ASCHE11','ASPIRE','Kinetics',1),('ASCHE21','ASPIRE','Mole Concept',1),('ASPHY11','ASPIRE','Motion',1),('ASPHY12','ASPIRE','Motion',1),('SPCHE11','SPARK','Mole Concept',1),('SPCHE12','SPARK','Mole Concept',2),('SPCHE13','SPARK','Mole Concept',3),('SPCHE14','SPARK','Mole Concept',4),('SPMAT11','SPARK','Inequations, Logs',1),('SPMAT12','SPARK','Inequations, Logs',2),('SPMAT13','SPARK','Inequations, Logs',3),('SPMAT21','SPARK','Modulus',1),('SPMAT22','SPARK','Modulus',2),('SPPHY11','SPARK','Vectors',1),('SPPHY21','SPARK','Kinematics',1),('SPPHY22','SPARK','Kinematics',2),('SPPHY31','SPARK','2D Motion',1),('SPPHY32','SPARK','2D Motion',2),('SWCHE11','SWIFT','Moles and Kinetics',1),('SWCHE12','SWIFT','Moles and Kinetics',2),('SWCHE13','SWIFT','Moles and Kinetics',3),('SWMAT11','SWIFT','Algebra',1),('SWMAT12','SWIFT','Algebra',2),('SWMAT13','SWIFT','Algebra',3),('SWMAT14','SWIFT','Algebra',4),('SWPHY11','SWIFT','1D & 2D Motion',1),('SWPHY12','SWIFT','1D & 2D Motion',2),('SWPHY13','SWIFT','1D & 2D Motion',3);

INSERT INTO `subjects` VALUES ('ASPIRE','BIOLOGY'),('ASPIRE','CHEMISTRY-NEET'),('ASPIRE','PHYSICS - NEET'),('SPARK','CHEMISTRY'),('SPARK','MATHEMATICS'),('SPARK','PHYSICS'),('SWIFT','CHEMISTRY'),('SWIFT','MATHEMATICS'),('SWIFT','PHYSICS');

INSERT INTO `teacherAttendance` VALUES (2,'2018-08-10','YES'),(2,'2018-08-11','YES'),(2,'2018-09-03','YES'),(2,'2018-09-04','YES'),(2,'2018-09-05','YES'),(2,'2018-09-06','YES'),(2,'2018-09-07','YES'),(3,'2018-08-10','YES'),(3,'2018-08-11','NO'),(3,'2018-09-03','YES'),(3,'2018-09-04','YES'),(3,'2018-09-05','YES'),(3,'2018-09-06','NO'),(3,'2018-09-07','YES'),(4,'2018-08-10','YES'),(4,'2018-08-11','YES'),(4,'2018-09-03','YES'),(4,'2018-09-04','YES'),(4,'2018-09-05','YES'),(4,'2018-09-06','YES'),(4,'2018-09-07','YES');

INSERT INTO `teaches` VALUES (1,'ASPIRE',2),(1,'SPARK',2),(1,'SWIFT',2),(1,'ASPIRE',3),(1,'SPARK',3),(2,'SPARK',3),(1,'SPARK',4),(1,'SWIFT',4),(2,'SPARK',4);
