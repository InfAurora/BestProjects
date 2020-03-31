Drop database if exists guessNumber;

Create database guessNumber;

use guessNumber;

create table Game(
gameId int primary key auto_increment,
answer char(4) not null,
`status` tinyInt not null
);

create table `Round` (
roundId int primary key auto_increment,
`time` timestamp not null,
roundNumber int not null,
guess char(4) not null,
`partial` int not null,
exact int not null,
gameId int not null,
foreign key(gameId) references Game(gameId)
);