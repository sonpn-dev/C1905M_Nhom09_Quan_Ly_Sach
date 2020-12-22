create database qlsach
go
use qlsach
go



create table category
(
	id int primary key identity,
	name nvarchar(100) null
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
	quantity int null,
	FOREIGN KEY (author_id) REFERENCES authors(id),
	FOREIGN KEY (category_id) REFERENCES category(id),
	FOREIGN KEY (nxb_id) REFERENCES nxb(id),
)
go

create table users
(
	id int primary key identity,
	name nvarchar(100) null,
	address nvarchar(100) null,
	email nvarchar(100) null,
	password nvarchar(100) null,
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

CREATE PROCEDURE proc_book_list AS
BEGIN
    SELECT b.id, b.name, c.name, a.name,n.name,b.yearMaking  FROM books b 
    JOIN authors a ON a.id=b.author_id
    JOIN category c ON c.id = b.category_id
    JOIN nxb n ON n.id = b.nxb_id ;
END;
GO
CREATE PROCEDURE proc_deleteBook 
@id int
AS
BEGIN
    DELETE FROM books where id= @id
END;
GO

CREATE PROCEDURE proc_createBook 
@name nvarchar(50),
@author nvarchar(50),
@category nvarchar(50),
@nxb nvarchar(50),
@year int
AS
BEGIN
	declare @id_author int;
	declare @id_nxb int;
	declare @id_cat int;
    IF(EXISTS(SELECT name FROM authors WHERE name=@author))
    BEGIN
		set @id_author = (SELECT id FROM authors WHERE name=@author);
	END;
	ELSE
	BEGIN
		INSERT INTO authors(name) VALUES (@author)
		set @id_author = (SELECT id FROM authors WHERE name=@author);
	END;
	
	IF(EXISTS(SELECT name FROM nxb WHERE name=@nxb))
    BEGIN
		set @id_nxb = (SELECT id FROM nxb WHERE name=@nxb);
	END;
	ELSE
	BEGIN
		INSERT INTO nxb(name,address,email) VALUES (@nxb,'','')
		set @id_nxb = (SELECT id FROM nxb WHERE name=@nxb);
	END;
	set @id_cat = (SELECT id FROM category WHERE name=@category);
	Insert into books(name,category_id,author_id,nxb_id,yearMaking)
	VALUES (@name,@id_cat,@id_author,@id_nxb,@year)
END;
EXEC proc_createBook N'HaiDang',N'Kim Dong123',1,N'Kim Oanh112',1998
go

CREATE PROCEDURE proc_editBook 
@name nvarchar(50),
@author nvarchar(50),
@category nvarchar(50),
@nxb nvarchar(50),
@year int,
@id int
AS
BEGIN
	declare @id_author int;
	declare @id_nxb int;
	declare @id_cat int;
    IF(EXISTS(SELECT name FROM authors WHERE name=@author))
    BEGIN
		set @id_author = (SELECT id FROM authors WHERE name=@author);
	END;
	ELSE
	BEGIN
		INSERT INTO authors(name) VALUES (@author)
		set @id_author = (SELECT id FROM authors WHERE name=@author);
	END;
	
	IF(EXISTS(SELECT name FROM nxb WHERE name=@nxb))
    BEGIN
		set @id_nxb = (SELECT id FROM nxb WHERE name=@nxb);
	END;
	ELSE
	BEGIN
		INSERT INTO nxb(name,address,email) VALUES (@nxb,'','')
		set @id_nxb = (SELECT id FROM nxb WHERE name=@nxb);
	END;
	set @id_cat = (SELECT id FROM category WHERE name=@category);
	Update books 
	set name=@name,category_id=@id_cat,author_id=@id_author,nxb_id=@id_nxb,yearMaking=@year
	where id = @id
END;
GO


CREATE PROCEDURE proc_listStaff
AS
BEGIN
	select * from staffs
END;
go

CREATE PROCEDURE proc_createStaff
@name nvarchar(100),
@phone nvarchar(100)
AS
BEGIN
	Insert into staffs(name,phone)
	VALUES (@name,@phone)
END;
go

CREATE PROCEDURE proc_deleteStaff 
@id int
AS
BEGIN
    DELETE FROM staffs where id= @id
END;
GO

CREATE PROCEDURE proc_editStaff 
@id int,@name nvarchar(100),@phone nvarchar(100)
AS
BEGIN
    update staffs set name = @name, phone=@phone where id = @id 
END;
GO


CREATE PROCEDURE proc_listUser
AS
BEGIN
	select * from users
END;
go


CREATE PROCEDURE proc_createUser
@name nvarchar(100),
@address nvarchar(100),
@email nvarchar(100),
@password nvarchar(100)
AS
BEGIN
	Insert into users(name,address,email,password)
	VALUES (@name,@address,@email,@password)
END;
go

CREATE PROCEDURE proc_editUser
@id int,@name nvarchar(100),@address nvarchar(100),@email nvarchar(100)
AS
BEGIN
    update users set name = @name, address = @address, email = @email where id = @id 
END;
GO


insert into users(name,address,email,password) values 
('User1','address1','email1','1')
go