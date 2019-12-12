-- admin
insert into Users (username, password, enabled)
	values ('admin', '$2a$10$6giEUpsLMg135xTeu3GCD..OWgNaSjV1cJpWzn4x13rgLhdXY8ebC', 1);

insert into Authorities (username, authority)
	values ('admin', 'ROLE_ADMIN');

-- Instructors
insert into Users (username, password, enabled)
	values ('Maverick', '$2a$10$6giEUpsLMg135xTeu3GCD..OWgNaSjV1cJpWzn4x13rgLhdXY8ebC', 1);

insert into Users (username, password, enabled)
	values ('Goose', '$2a$10$6giEUpsLMg135xTeu3GCD..OWgNaSjV1cJpWzn4x13rgLhdXY8ebC', 1);

insert into Authorities (username, authority)
	values ('Maverick', 'ROLE_INSTRUCTOR');

insert into Authorities (username, authority)
	values ('Goose', 'ROLE_INSTRUCTOR');

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








