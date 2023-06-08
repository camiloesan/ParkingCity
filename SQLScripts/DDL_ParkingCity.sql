DROP DATABASE IF EXISTS ParkingCity;

CREATE DATABASE ParkingCity DEFAULT CHARACTER SET utf8;
USE ParkingCity;

create table nivel0 (
    slot_id int not null primary key,
    available enum('yes', 'no') DEFAULT 'yes',
    UNIQUE (slot_id));
create table nivel1 (
    slot_id int not null primary key,
    available enum('yes', 'no') DEFAULT 'yes',
    UNIQUE (slot_id));
create table nivel2 (
    slot_id int not null primary key,
    available enum('yes', 'no') DEFAULT 'yes',
    UNIQUE (slot_id));
create table nivel3 (
    slot_id int not null primary key,
    available enum('yes', 'no') DEFAULT 'yes',
    UNIQUE (slot_id));

create table tickets (
    ticket_id int auto_increment,
    check_in  datetime not null,
    check_out datetime default null,
    slot_id int not null,
    constraint tickets_pk primary key (ticket_id)
);

CREATE TABLE pagos (
    pago_id int not null auto_increment,
    nivel int not null,
    slot int not null,
    PRIMARY KEY (pago_id)
);