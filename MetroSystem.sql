create database metro_system;
use metro_system;

create table metro_card(
metro_card_id int primary key auto_increment,
username varchar(30),
phone varchar(10),
balance double
);

ALTER TABLE metro_card AUTO_INCREMENT = 1001;

create table station(
station_id int primary key,
station_name varchar(30),
station_code varchar(10)
);

insert into station(station_id, station_name,station_code) values 
(1, "Yesvantpur", "YPR"),
(2, "Goraguntepalya", "GPA"),
(3, "Jalahalli", "JHI"),
(4, "Dasarahalli", "DHI"),
(5, "Nagasandra", "NGA");

create table journey(
journey_id int primary key auto_increment,
metro_card_id int not null,
source_station_id int not null,
source_time_stamp timestamp not null,
destination_station_id int,
destination_time_stamp timestamp,
total_fare double,
foreign key(metro_card_id) references metro_card(metro_card_id),
foreign key(source_station_id) references station(station_id),
foreign key(destination_station_id) references station(station_id)
);