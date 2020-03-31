drop database if exists hoteldb;

create database hoteldb;

use hoteldb; 

create table Guest(
guestId int primary key auto_increment,
firstName varchar(50) not null,
lastName varchar(50) not null,
address varchar(100) not null,
city varchar(50) not null,
state varchar(2) not null,
zip char(5) not null,
phoneNumber char(10) not null
);

create table `Type`(
typeId int primary key auto_increment,
`name` varchar(10) not null,
standardOccupancy int not null,
maxOccupancy int not null,
basePrice decimal(5,2) not null,
extraPersonCost decimal(2,0) not null
); 

create table Amenity(
amenityId int primary key auto_increment,
name varchar(50) not null,
charge decimal(2,0) not null
);

create table Room(
roomId int primary key auto_increment,
roomNumber int,
typeId int not null,
foreign key(typeId) references `type`(typeId), -- foreign key
adaId tinyint not null
);

create table Reservation(
reservationId int primary key auto_increment,
startDate date not null,
endDate date not null,
adultCount int not null,
childrenCount int not null,
totalRoomCost decimal(7,2) not null,
guestId int not null, -- foreign key
foreign key(guestId) references guest(guestId)
);

create table roomReservation(
roomId int,
foreign key(roomId) references Room(roomId),
reservationId int,
foreign key(reservationId) references reservation(reservationId),
primary key(roomId, reservationId)
);

create table roomAmenity(
roomId int,
foreign key(roomId) references Room(roomId),
amenityId int,
foreign key(amenityId) references Amenity(amenityId),
primary key(roomId, amenityId)
);
