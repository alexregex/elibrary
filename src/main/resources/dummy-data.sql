-- -----------------------------------------------------
-- Data for table `elibrarydb`.`APP_USER`
-- -----------------------------------------------------
START TRANSACTION;
USE `elibrarydb`;
INSERT INTO `elibrarydb`.`APP_USER` (`id`, `first_name`, `last_name`, `login`, `password`, `email`) VALUES (1, 'Alexander', 'Voloshyn', 'admin', '$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'voloshyn.olexander@gmail.com');
INSERT INTO `elibrarydb`.`APP_USER` (`id`, `first_name`, `last_name`, `login`, `password`, `email`) VALUES (DEFAULT, 'Ivan', 'Zakulko', 'user', '$2a$10$i/L2t39z7zDxdMj5Rmf6lebe8usKFv5wSHVKoq26SAS2QN1Dn3Mvm', 'ivan@yahoo.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `elibrarydb`.`BOOK`
-- -----------------------------------------------------
START TRANSACTION;
USE `elibrarydb`;
INSERT INTO `elibrarydb`.`BOOK` (`id`, `title`, `author`, `publishing`, `cover`, `link`, `description`, `date_add`, `user_id`) VALUES (1, 'Spring in action. Fourth Edition', 'Craig Walls', 'Packt Publishing Ltd', 'spring_in_action.png', 'Spring_in_action.rtf', 'This book has a very clear aim to introduce you to the incredible simplicity and power of Spring MVC. The best way to test whether or not you really understand a concept is to try to teach it.', '2017-10-19', 1);
INSERT INTO `elibrarydb`.`BOOK` (`id`, `title`, `author`, `publishing`, `cover`, `link`, `description`, `date_add`, `user_id`) VALUES (2, 'Java 8.The Complete Reference,8th edition', 'Herbert Schildt', 'McGraw Hill Professional', 'java_the_complete_reference_8th_edition_herbert_schildt.jpg', 'Herbert_Schildt_Java_The_Complete_Reference_8th_Edition.pdf', 'The Definitive Java Programming Guide. In Java: The Complete Reference, Eighth Edition, bestselling programming author Herb Schildt shows you everything you need to develop, compile, debug, and run Java programs.', '2017-12-10', 1);
INSERT INTO `elibrarydb`.`BOOK` (`id`, `title`, `author`, `publishing`, `cover`, `link`, `description`, `date_add`, `user_id`) VALUES (3, 'Clean Code. 1st Edition', 'Robert C. Martin', 'Prentice Hall', 'martin_flower_cleancode.jpg', 'Clean_Code_A_Handbook_of_Agile_Software_Craftsmanship_1st_Edition.pdf', 'Even bad code can function. But if code isn t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code.', '2017-03-04', 1);
INSERT INTO `elibrarydb`.`BOOK` (`id`, `title`, `author`, `publishing`, `cover`, `link`, `description`, `date_add`, `user_id`) VALUES (4, 'Effective Java (3rd Edition)', 'Joshua Bloch', 'Amazon Inc', 'effective_java_3rd _edition.png', 'Effective_Java_3rd_edition.docx', 'Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 9. This Jolt award-winning classic has now been thoroughly updated to take full advantage.', '2018-01-14', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `elibrarydb`.`USER_PROFILE`
-- -----------------------------------------------------
START TRANSACTION;
USE `elibrarydb`;
INSERT INTO `elibrarydb`.`USER_PROFILE` (`id`, `type`) VALUES (1, 'USER');
INSERT INTO `elibrarydb`.`USER_PROFILE` (`id`, `type`) VALUES (2, 'ADMIN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `elibrarydb`.`APP_USER_USER_PROFILE`
-- -----------------------------------------------------
START TRANSACTION;
USE `elibrarydb`;
INSERT INTO `elibrarydb`.`APP_USER_USER_PROFILE` (`app_user_id`, `user_profile_id`) VALUES (1, 2);
INSERT INTO `elibrarydb`.`APP_USER_USER_PROFILE` (`app_user_id`, `user_profile_id`) VALUES (2, 1);

COMMIT;

