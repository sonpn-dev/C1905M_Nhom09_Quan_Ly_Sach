create database qlsach
go
use qlsach
go

create table category
(
	id int primary key identity,
)
go

create table authors
(
	id int primary key identity,
	name nvarchar(100) null,
	website nvarchar(100) null,
	note nvarchar(100) null,
)
go


create table nxb
(
	id int primary key identity,
	name nvarchar(100) null,
	address nvarchar(100) null,
	email nvarchar(100) null,
)
go


create table books
(
	id int primary key identity,
	name nvarchar(100) null,
	yearMaking int null,
	author_id int null,
	category_id int null,
	nxb_id int null,
	FOREIGN KEY (author_id) REFERENCES authors(id),
	FOREIGN KEY (category_id) REFERENCES category(id),
	FOREIGN KEY (nxb_id) REFERENCES nxb(id),
)
go

create table users
(
	id int primary key identity,
	name nvarchar(100) null,
	address nvarchar(100) null
)
go

create table cards
(
	id int primary key identity,
	start_date date null,
	finish_date date null,
	note nvarchar(100) null,
	user_id int null,
	FOREIGN KEY (user_id) REFERENCES users(id),
	status tinyint default(0)
)
go

create table staffs
(
	id int primary key identity,
	name nvarchar(100) null,
	phone nvarchar(100) null,
)
go

create table muontra
(
	id int primary key identity,
	card_id int null,
	FOREIGN KEY (card_id) REFERENCES cards(id),
	staff_id int null,
	FOREIGN KEY (staff_id) REFERENCES staffs(id),
	borrowed_date date null
)
go

create table ct_muontra
(
	muontra_id int null,
	FOREIGN KEY (muontra_id) REFERENCES muontra(id),
	book_id int null,
	FOREIGN KEY (book_id) REFERENCES books(id),
	note nvarchar(100) null,
	status bigint null,
	pay_date date null
)
go
