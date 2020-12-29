CREATE SCHEMA `match_reservation_system` ;

CREATE TABLE `match_reservation_system`.`team` (
team_name varchar(255) NOT NULL,
PRIMARY KEY(team_name)
);

CREATE TABLE `match_reservation_system`.`user` (
username varchar(255),
pw varchar(255) NOT NULL,
fname varchar(255) NOT NULL,
lname varchar(255) NOT NULL,
dob date NOT NULL,
gender varchar(255) NOT NULL,
city varchar(255) NOT NULL,
address varchar(255) DEFAULT NULL,
mail varchar(255) NOT NULL UNIQUE ,
user_type varchar(255) NOT NULL,
approved boolean NOT NULL DEFAULT 0 ,
PRIMARY KEY (username)
);

CREATE TABLE `match_reservation_system`.`stadium` (
stadium_id integer AUTO_INCREMENT,
city varchar(255) NOT NULL,
name varchar(255) NOT NULL,
number_of_rows integer NOT NULL,
seats_per_row integer NOT NULL,
PRIMARY KEY(stadium_id)
);

CREATE TABLE `match_reservation_system`.`match` (
match_id integer AUTO_INCREMENT,
home_team varchar(255) NOT NULL,
away_team varchar(255) NOT NULL,
match_date_time datetime NOT NULL,
main_refree varchar(255) NOT NULL,
linesman1 varchar(255) NOT NULL,
linesman2 varchar(255) NOT NULL,
stadium_id integer NOT NULL,
PRIMARY KEY(match_id),
FOREIGN KEY(stadium_id) REFERENCES stadium(stadium_id),
FOREIGN KEY(home_team) REFERENCES team(team_name),
FOREIGN KEY(away_team) REFERENCES match_reservation_system.team(team_name)
);


CREATE TABLE `match_reservation_system`.`ticket` (
ticket_id integer AUTO_INCREMENT NOT NULL UNIQUE,
username varchar(255) NOT NULL,
seat_number integer,
match_id integer,
PRIMARY KEY(match_id, seat_number),
FOREIGN KEY(username) REFERENCES user(username) ON DELETE CASCADE,
FOREIGN KEY(match_id) REFERENCES match_reservation_system.match(match_id)
);