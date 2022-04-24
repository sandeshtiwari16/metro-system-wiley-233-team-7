create database metro_system;
use metro_system;

create table users (
user_id int primary key not null auto_increment, 
user_name varchar(30) not null unique,
phone_number varchar(10) not null
);

create table station(
station_id int primary key not null,
station_name varchar(30) not null
);

create table metro_card(
metro_card_id int not null auto_increment,
user_id int,
balance double not null,
primary key(metro_card_id),
foreign key (user_id) references users(user_id)
);

create table journey(
metro_card_id int,
source_station_id int,
source_time_stamp timestamp,
destination_station_id int,
destination_time_stamp timestamp,
total_fare double,
primary key(metro_card_id),
foreign key(metro_card_id) references metro_card(metro_card_id),
foreign key(source_station_id) references station(station_id),
foreign key(destination_station_id) references station(station_id)
);

create table journey_history(
metro_card_id int,
source_station_id int,
source_time_stamp timestamp,
destination_station_id int,
destination_time_stamp timestamp,
total_fare double,
primary key(metro_card_id),
foreign key(metro_card_id) references metro_card(metro_card_id),
foreign key(source_station_id) references station(station_id),
foreign key(destination_station_id) references station(station_id)
);