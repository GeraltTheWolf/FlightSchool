delete from BlogPost;
delete from UserCourse;
delete from Course;
delete from UserData;
delete from Authorities;
delete from Users;


-- admin
insert into Users (username, password, enabled)
	values ('admin', '$2a$10$6giEUpsLMg135xTeu3GCD..OWgNaSjV1cJpWzn4x13rgLhdXY8ebC', 1);

insert into Authorities (username, authority)
	values ('admin', 'ROLE_ADMIN');

insert into Authorities (username, authority)
	values ('admin', 'ROLE_USER');


-- Instructors
insert into Users (username, password, enabled)
	values ('Maverick', '$2a$10$6giEUpsLMg135xTeu3GCD..OWgNaSjV1cJpWzn4x13rgLhdXY8ebC', 1);

insert into Authorities (username, authority)
	values ('Maverick', 'ROLE_INSTRUCTOR');

insert into Authorities (username, authority)
	values ('Maverick', 'ROLE_USER');

insert into Users (username, password, enabled)
	values ('Goose', '$2a$10$6giEUpsLMg135xTeu3GCD..OWgNaSjV1cJpWzn4x13rgLhdXY8ebC', 1);

insert into Authorities (username, authority)
	values ('Goose', 'ROLE_INSTRUCTOR');

insert into Authorities (username, authority)
	values ('Goose', 'ROLE_USER');

-- Candidates
insert into Users (username, password, enabled)
	values ('Wolfman', '$2a$10$6giEUpsLMg135xTeu3GCD..OWgNaSjV1cJpWzn4x13rgLhdXY8ebC', 1);
insert into Users (username, password, enabled)
	values ('Merlin', '$2a$10$6giEUpsLMg135xTeu3GCD..OWgNaSjV1cJpWzn4x13rgLhdXY8ebC', 1);

insert into Authorities (username, authority)
	values ('Wolfman', 'ROLE_USER');
insert into Authorities (username, authority)
	values ('Merlin', 'ROLE_USER');

-- Fill User Data
insert into UserData ( firstName, lastName, username,isInstructor,biography)
    values('Pete','Mitchell','Maverick',1, 'Lieutenant Pete "Maverick" Mitchell is a US Naval Aviator and the protagonist of Top Gun. He is a troubled character and its hinted that the cause is due to his fathers death in the Vietnam War. We are led to believe that this incident shaped his life, flying style and led to his call sign.');
insert into UserData ( firstName, lastName, username,isInstructor,biography)
    values('Nick','Bradshaw','Goose',1,'Lieutenant (Junior Grade) Nick "Goose" Bradshaw was a Naval Flight Officer and Mavericks R.I.O., or Radar Intercept Officer. While clearly able to enjoy himself and have a good time, Goose was clearly more level headed than his partner Maverick, which is shown through his family and slightly more serious personality. He is a skilled R.I.O. and married to Carole, a fiery young woman with whom he has a son, Bradley.');
insert into UserData ( firstName, lastName, username,isInstructor)
    values('Henry','Ruth','Wolfman',0);
insert into UserData ( firstName, lastName, username,isInstructor)
    values('Sam','Wells','Merlin',0);

-- Fill Courses
insert into Course (name, userDataId, duration, plane)
    values ('Intro Course', 1, 2, 'F18');
insert into Course (name, userDataId, duration, plane)
    values ('Special Course', 2, 7, 'F16');
insert into Course (name, userDataId, duration, plane)
    values ('Basic Top Gun', 1, 3, 'F14');
insert into Course (name, userDataId, duration, plane)
    values ('Tactical Course', 1, 14, 'F14');

-- Fill User Courses
insert into UserCourse (courseId, userDataId, dateStarted, numberOfAttendances)
    values (1, 3, '2019-11-01',1);
insert into UserCourse (courseId, userDataId, dateStarted, numberOfAttendances)
    values (4, 3, '2019-12-15',3);
insert into UserCourse (courseId, userDataId, dateStarted, numberOfAttendances)
    values (2, 4, '2019-10-21',1);



insert into BlogPost (userId, title, content, lastEditedOn, author, enabled)
    values ('347be14c5-c7f3-417c-abce-526547d1d486','Sample blog post', 'This is sample blog post entered manually in database', '2019-11-01','admin',0);
insert into BlogPost (userId, title, content, lastEditedOn, author)
    values ('39f9141a-20ed-402b-8972-2d9a6fb46c6c','Happy New Year!', 'Some new stuff in new year', '2020-01-01','admin');
insert into BlogPost (userId, title, content, lastEditedOn, author)
    values ('017a0d44-1b0e-496b-84b5-de4ac3a645df','Maverick Sample blog post', 'Maverick was here...', '2019-12-01','Maverick');
insert into BlogPost (userId, title, content, lastEditedOn, author)
    values ('8b7d2a9d-953d-46ee-be09-c8eb12bbe60f','Goose Sample blog post', 'Goose was here...', '2019-10-01','Goose');
insert into BlogPost (userId, title, content, lastEditedOn, author)
    values ('1da792b5-9954-4f4d-bb1a-afd2ba049410','Goose changes partner!', 'Goose changed partner..', '2019-12-01','Goose');
insert into BlogPost (userId, title, content, lastEditedOn, author, enabled)
    values ('864d820bc-163e-42a6-9c0a-de38e44d95f8','Goose gets killed!', 'Goose is no more', '2019-12-02','Goose',0);







