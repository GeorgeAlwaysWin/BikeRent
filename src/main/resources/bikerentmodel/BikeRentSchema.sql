#
# TABLE STRUCTURE FOR: Admin
#

DROP TABLE IF EXISTS `Admin`;

CREATE TABLE `Admin` (
  `user_id` int(9) unsigned NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  UNIQUE KEY `user_id_2` (`user_id`),
  UNIQUE KEY `login` (`login`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

#
# TABLE STRUCTURE FOR: Bike_models
#

DROP TABLE IF EXISTS `Bike_models`;

CREATE TABLE `Bike_models` (
  `bike_model` varchar(32) NOT NULL,
  `type` enum('city','country','mountain') NOT NULL,
  `gears` tinyint(3) unsigned NOT NULL,
  KEY `bike_model` (`bike_model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Rudolph', 'mountain', 2);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Henschel', 'country', 5);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Textor', 'country', 2);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Ruppersberger', 'mountain', 3);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Otto', 'mountain', 4);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Süßebier', 'mountain', 3);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Röhrdanz', 'mountain', 2);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Döhn', 'country', 5);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Weiß', 'mountain', 3);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Lehmann', 'country', 3);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Dussen van', 'mountain', 2);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Stumpf', 'city', 4);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Briemer', 'mountain', 4);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Rörricht', 'mountain', 5);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Ortmann', 'country', 5);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Trub', 'city', 5);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Benthin', 'mountain', 3);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Trapp', 'country', 5);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Kallert', 'mountain', 3);
INSERT INTO `Bike_models` (`bike_model`, `type`, `gears`) VALUES ('Bender', 'mountain', 5);


#
# TABLE STRUCTURE FOR: Bikes
#

DROP TABLE IF EXISTS `Bikes`;

CREATE TABLE `Bikes` (
  `bike_id` int(9) unsigned NOT NULL AUTO_INCREMENT,
  `bike_model` varchar(32) NOT NULL,
  `shop_name` varchar(32) NOT NULL,
  KEY `bike_id` (`bike_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (1, 'Kallert', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (2, 'Textor', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (3, 'Benthin', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (4, 'Kallert', 'Thistle');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (5, 'Ruppersberger', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (6, 'Briemer', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (7, 'Ruppersberger', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (8, 'Süßebier', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (9, 'Bender', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (10, 'Döhn', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (11, 'Ruppersberger', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (12, 'Ortmann', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (13, 'Trub', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (14, 'Weiß', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (15, 'Röhrdanz', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (16, 'Otto', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (17, 'Weiß', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (18, 'Röhrdanz', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (19, 'Ruppersberger', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (20, 'Rörricht', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (21, 'Dussen van', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (22, 'Ruppersberger', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (23, 'Kallert', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (24, 'Bender', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (25, 'Lehmann', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (26, 'Dussen van', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (27, 'Stumpf', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (28, 'Dussen van', 'Thistle');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (29, 'Textor', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (30, 'Textor', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (31, 'Stumpf', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (32, 'Stumpf', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (33, 'Otto', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (34, 'Textor', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (35, 'Henschel', 'Thistle');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (36, 'Ruppersberger', 'LightGreen');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (37, 'Döhn', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (38, 'Trub', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (39, 'Weiß', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (40, 'Rörricht', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (41, 'Briemer', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (42, 'Lehmann', 'Thistle');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (43, 'Trapp', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (44, 'Rörricht', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (45, 'Benthin', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (46, 'Weiß', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (47, 'Dussen van', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (48, 'Süßebier', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (49, 'Ruppersberger', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (50, 'Benthin', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (51, 'Weiß', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (52, 'Benthin', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (53, 'Stumpf', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (54, 'Benthin', 'Thistle');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (55, 'Kallert', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (56, 'Trapp', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (57, 'Otto', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (58, 'Stumpf', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (59, 'Trapp', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (60, 'Rudolph', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (61, 'Briemer', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (62, 'Bender', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (63, 'Weiß', 'Thistle');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (64, 'Röhrdanz', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (65, 'Textor', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (66, 'Lehmann', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (67, 'Süßebier', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (68, 'Döhn', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (69, 'Stumpf', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (70, 'Trapp', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (71, 'Lehmann', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (72, 'Röhrdanz', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (73, 'Rörricht', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (74, 'Döhn', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (75, 'Rudolph', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (76, 'Henschel', 'Thistle');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (77, 'Kallert', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (78, 'Otto', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (79, 'Dussen van', 'LightGreen');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (80, 'Henschel', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (81, 'Rudolph', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (82, 'Ortmann', 'LightGreen');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (83, 'Lehmann', 'Ivory');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (84, 'Rörricht', 'DarkMagenta');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (85, 'Trub', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (86, 'Röhrdanz', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (87, 'Döhn', 'LightGreen');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (88, 'Döhn', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (89, 'Ruppersberger', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (90, 'Rörricht', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (91, 'Trapp', 'LightCoral');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (92, 'Rudolph', 'DarkKhaki');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (93, 'Stumpf', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (94, 'Lehmann', 'NavajoWhite');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (95, 'Döhn', 'Aquamarine');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (96, 'Textor', 'Thistle');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (97, 'Kallert', 'MediumSlateBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (98, 'Benthin', 'RoyalBlue');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (99, 'Stumpf', 'LightGreen');
INSERT INTO `Bikes` (`bike_id`, `bike_model`, `shop_name`) VALUES (100, 'Rudolph', 'DarkMagenta');


#
# TABLE STRUCTURE FOR: Booking
#

DROP TABLE IF EXISTS `Booking`;

CREATE TABLE `Booking` (
  `user_id` int(9) unsigned NOT NULL,
  `bike_model` varchar(32) NOT NULL,
  `shop_name` varchar(32) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `book_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  UNIQUE KEY `book_id` (`book_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

#
# TABLE STRUCTURE FOR: Clients
#

DROP TABLE IF EXISTS `Clients`;

CREATE TABLE `Clients` (
  `user_id` int(9) unsigned NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `passport` char(10) NOT NULL,
  `address` varchar(255) NOT NULL,
  UNIQUE KEY `passport` (`passport`),
  KEY `user_id` (`user_id`),
  KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

#
# TABLE STRUCTURE FOR: Rent
#

DROP TABLE IF EXISTS `Rent`;

CREATE TABLE `Rent` (
  `book_id` int(9) unsigned NOT NULL,
  `bike_id` int(9) unsigned NOT NULL,
  KEY `book_id` (`book_id`),
  KEY `bike_id` (`bike_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

#
# TABLE STRUCTURE FOR: Roles
#

DROP TABLE IF EXISTS `Roles`;

CREATE TABLE `Roles` (
  `user_id` int(9) unsigned NOT NULL AUTO_INCREMENT,
  `role` enum('client','worker','admin') NOT NULL DEFAULT 'client',
  UNIQUE KEY `user_id_2` (`user_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

INSERT INTO `Roles` (`user_id`, `role`) VALUES (1, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (2, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (3, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (4, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (5, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (6, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (7, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (8, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (9, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (10, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (11, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (12, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (13, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (14, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (15, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (16, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (17, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (18, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (19, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (20, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (21, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (22, 'worker');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (23, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (24, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (25, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (26, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (27, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (28, 'client');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (29, 'admin');
INSERT INTO `Roles` (`user_id`, `role`) VALUES (30, 'client');


#
# TABLE STRUCTURE FOR: Shop_workers
#

DROP TABLE IF EXISTS `Shop_workers`;

CREATE TABLE `Shop_workers` (
  `user_id` int(9) unsigned NOT NULL,
  `shop_name` varchar(32) NOT NULL,
  UNIQUE KEY `user_id_2` (`user_id`),
  KEY `User_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

#
# TABLE STRUCTURE FOR: Shops
#

DROP TABLE IF EXISTS `Shops`;

CREATE TABLE `Shops` (
  `shop_name` varchar(32) NOT NULL,
  `Adress` varchar(255) NOT NULL,
  UNIQUE KEY `shop_name_2` (`shop_name`),
  KEY `Shop_name` (`shop_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('Aquamarine', '4833 Hermiston Lake');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('DarkKhaki', '782 Rhea Land Suite 216');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('DarkMagenta', '1532 Howell Station Suite 818');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('Ivory', '029 Heidenreich Bridge');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('LightCoral', '49574 Hickle Plaza Apt. 381');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('LightGreen', '4941 Gorczany Stravenue Suite 937');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('MediumSlateBlue', '4193 Annie Spurs');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('NavajoWhite', '249 Michelle Bypass Apt. 316');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('RoyalBlue', '647 Pasquale Center Suite 227');
INSERT INTO `Shops` (`shop_name`, `Adress`) VALUES ('Thistle', '050 Gaylord Ford Apt. 802');


#
# TABLE STRUCTURE FOR: Workers
#

DROP TABLE IF EXISTS `Workers`;

CREATE TABLE `Workers` (
  `user_id` int(9) unsigned NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (1, 'Игнатий', '5b4af884dcc7684a6baef7525185512e');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (2, 'Яков', '1d3b9bfb2eca4a5091bde32ddb4627fb');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (3, 'Клементина', 'f741499213072844fd7b49aed02dfd99');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (4, 'Аполлон', 'e9623cfcbb53f8fd756b16e06ffb997a');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (5, 'Регина', 'f5039880bc3c7480c000c7d3e408d106');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (6, 'Клавдия', '100b1a793ad7cc22d1aaaf33744ecfe1');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (7, 'Александра', 'd35f77b4b93e4e250de27b0b26f96e43');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (8, 'Роберт', 'dbd33425d6229325efcaaa9c1ed34d13');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (9, 'Константин', '8621366ba02aabc8265074e65e456841');
INSERT INTO `Workers` (`user_id`, `login`, `password`) VALUES (10, 'Юлиан', 'e2a3e37cec861435663197d55574f170');


