create table kactivitytemplaite(
	acTempId int(11) auto_increment primary key,
	tempName varchar(50) UNIQUE,
	acRule varchar(200) default null,
	tempValid tinyint default 0,
	tempUsedTimes int(11) default 0,
	acPicAddress varchar(300) default null
);
////////////////////////////////////////////////////////////////////////

create table kactivityinfo(
	ktvId int(11),
	activityId int(11)  auto_increment,
	acName varchar(50),
	acRule varchar(200),
	acValid tinyint default 1,
	acStartDate varchar(30),
	acStartTime varchar(30),
	acEndTime varchar(30),
	acCloseTime varchar(30),
	week varchar(10),
	qrcodeImg varchar(250),
	acPicAddress varchar(300) default null,
	acCrrentPeople int(11),
	prize1 varchar(60),
	prize2 varchar(60),
	prize3 varchar(60),
	prize4 varchar(60),
	prize5 varchar(60),
	prize6 varchar(60),
	prize7 varchar(60),
	prize8 varchar(60),
	prize9 varchar(60),
	prize10 varchar(60),
	rightAnswer1 varchar(10) default null,
	rightAnswer2 varchar(10) default null,
	rightAnswer3 varchar(10) default null,
	rightAnswer4 varchar(10) default null,
	rightAnswer5 varchar(10) default null,
	rightAnswer6 varchar(10) default null,
	rightAnswer7 varchar(10) default null,
	rightAnswer8 varchar(10) default null,
	rightAnswer9 varchar(10) default null,
	rightAnswer10 varchar(10) default null,
	winNum tinyint default 0,
	primary key(activityId),
	foreign key(ktvId) references songhallinfo(Id) on update cascade
);
///////////////////////////////////////////////////////////////////////////////

create table kactivityUser(
	userId int(11) not null auto_increment,
	nickname varchar(40),
	sex tinyint(4),
	headpicpath varchar(100),
	ktvId int(11)  not null,
	activityId int(11) not null,
	joinTime varchar(20),
	yourAnswer varchar(200),
	isWinner char(1),
	th tinyint default 0,
	primary key(userId,ktvId,activityId),
	foreign key(userId) references customerinfo(id) on update cascade,
	foreign key(activityId) references kactivityinfo(activityId) on delete cascade on update cascade
);
======================

//常用操作：
alter table kactivityUser modify joinTime varchar(20);
alter table kactivityUser add sex tinyint;
//删除外键
alter table kactivityUser drop foreign key kactivityuser_ibfk_3;
//修改外键
alter table kactivityUser add foreign key(activityId) references kactivityinfo(activityId) on delete cascade on update cascade