-- admin
insert into Users (username, password, enabled)
	values ('admin', '$2a$04$3UjPjJQ4/s4SqCuXrek4MekcYB9sdtIhOUnf8/nbWY0pr4TBOP.zW', 1);

insert into Authorities (username, authority)
	values ('admin', 'ROLE_ADMIN');

-- Instructors
insert into Users (username, password, enabled)
	values ('Maverick', '$2a$04$3UjPjJQ4/s4SqCuXrek4MekcYB9sdtIhOUnf8/nbWY0pr4TBOP.zW', 1);

insert into Users (username, password, enabled)
	values ('Goose', '$2a$04$3UjPjJQ4/s4SqCuXrek4MekcYB9sdtIhOUnf8/nbWY0pr4TBOP.zW', 1);

insert into Authorities (username, authority)
	values ('Maverick', 'ROLE_INSTRUCTOR');

insert into Authorities (username, authority)
	values ('Goose', 'ROLE_INSTRUCTOR');

-- Candidates
insert into Users (username, password, enabled)
	values ('Wolfman', '$2a$04$3UjPjJQ4/s4SqCuXrek4MekcYB9sdtIhOUnf8/nbWY0pr4TBOP.zW', 1);
insert into Users (username, password, enabled)
	values ('Merlin', '$2a$04$3UjPjJQ4/s4SqCuXrek4MekcYB9sdtIhOUnf8/nbWY0pr4TBOP.zW', 1);

insert into Authorities (username, authority)
	values ('Wolfman', 'ROLE_USER');
insert into Authorities (username, authority)
	values ('Merlin', 'ROLE_USER');

-- Fill User Data
insert into UserData ( firstName, lastName, username)
    values('Pete','Mitchell','Maverick');
insert into UserData ( firstName, lastName, username)
    values('Nick','Bradshaw','Goose');
insert into UserData ( firstName, lastName, username)
    values('Henry','Ruth','Wolfman');
insert into UserData ( firstName, lastName, username)
    values('Sam','Wells','Merlin');

-- Fill Courses
insert into Course (name, userDataId, duration, plane)
    values ('Intro Course', 1, 2, 'F/A-18 Super Hornet');
insert into Course (name, userDataId, duration, plane)
    values ('Special Top Gun Course', 2, 7, 'F-16');
insert into Course (name, userDataId, duration, plane)
    values ('Basic Top Gun', 1, 3, 'F-14 Tomcat');
insert into Course (name, userDataId, duration, plane)
    values ('Tactical Course', 1, 14, 'F-14 Tomcat');

-- Fill User Courses
insert into UserCourse (courseId, userDataId, dateStarted, numberOfAttendances)
    values (1, 3, '2019-11-01',1);
insert into UserCourse (courseId, userDataId, dateStarted, numberOfAttendances)
    values (4, 3, '2019-12-15',3);
insert into UserCourse (courseId, userDataId, dateStarted, numberOfAttendances)
    values (2, 4, '2019-10-21',1);







