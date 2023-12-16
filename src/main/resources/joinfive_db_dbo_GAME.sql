create table GAME
(
    game_id        int identity
        constraint GAME_pk
            primary key nonclustered,
    player_id      int not null
        constraint PLAYER_GAME___fk
            references PLAYER,
    obtained_score int default 0
)
go

create unique index GAME_game_id_uindex
    on GAME (game_id)
go

SET IDENTITY_INSERT [joinfive-db].dbo.GAME ON;
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (3, 4, 0);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (4, 4, 0);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (5, 9, 1);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (7, 4, 0);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (8, 4, 0);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (9, 4, 0);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (11, 4, 0);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (12, 11, 0);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (13, 4, 0);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (6, 9, 22);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (14, 4, 2);
INSERT INTO [joinfive-db].dbo.GAME (game_id, player_id, obtained_score)
VALUES (10, 4, -18);
SET IDENTITY_INSERT [joinfive-db].dbo.GAME OFF;
