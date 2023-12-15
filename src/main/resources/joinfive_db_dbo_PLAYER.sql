create table PLAYER
(
    id       int identity
        constraint PLAYER_pk
            primary key,
    username varchar(100) not null,
    password varchar(100)
)
go

create unique index PLAYER_name_uindex
    on PLAYER (username)
go

SET IDENTITY_INSERT [joinfive-db].dbo.PLAYER ON;
INSERT INTO [joinfive-db].dbo.PLAYER (id, username, password)
VALUES (2, N'i', N'i');
INSERT INTO [joinfive-db].dbo.PLAYER (id, username, password)
VALUES (3, N'Cisco', N'$2a$10$Swpe9yV4C2G9wP1SXEKUeO2goQve9JhHq1UUuxB/PItL8aQfhwZ/.');
INSERT INTO [joinfive-db].dbo.PLAYER (id, username, password)
VALUES (4, N'', N'$2a$10$GmER9iOlifm7TZkI0RQo8uCmr3J98RqfFHO1LJ0UzSNxlCsydVbeu');
INSERT INTO [joinfive-db].dbo.PLAYER (id, username, password)
VALUES (5, N'az', N'$2a$10$GJibeD.uVgi7rLiv6ROT0.krQTzl3Q96Gga.DVcNR6OnnbIWDoc.a');
INSERT INTO [joinfive-db].dbo.PLAYER (id, username, password)
VALUES (6, N'ilyess', N'$2a$10$hTcRlhQD3ctRs0R7ppsAB.XUM0/J5xOgXF8WACnzY5.9YQzg.x7kq');
INSERT INTO [joinfive-db].dbo.PLAYER (id, username, password)
VALUES (9, N'a', N'$2a$10$vwLAaYzKWMZujMl8sqe2I.KXMLOuD3Vl/NmAGGix9euu3cbmJ.c1q');
INSERT INTO [joinfive-db].dbo.PLAYER (id, username, password)
VALUES (11, N'djamsko', N'$2a$10$H02dpHiyv6FX27YBNSzcduZ0rM/bcA/Dg2VDfXlAZrBABWSZsh/8u');
SET IDENTITY_INSERT [joinfive-db].dbo.PLAYER OFF;
