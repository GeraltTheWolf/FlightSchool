CREATE TABLE IF NOT EXISTS Users (
    username nvarchar(256) NOT NULL,
    password nvarchar(256) NOT NULL,
    enabled boolean NOT NULL,
    CONSTRAINT PK_Users PRIMARY KEY(username)
);

CREATE TABLE IF NOT EXISTS Authorities (
    username nvarchar(256) NOT NULL,
    authority nvarchar(256) NOT NULL,
    CONSTRAINT FK_Authorities_Users FOREIGN KEY(username) REFERENCES Users(username)
);

CREATE TABLE IF NOT EXISTS UserData (
    id int identity NOT NULL,
    firstName nvarchar(256) NOT NULL,
    lastName nvarchar(256) NOT NULL,
    username nvarchar(256) NULL,
    CONSTRAINT PK_UserData PRIMARY KEY (id),
    CONSTRAINT FK_UserData_Users FOREIGN KEY(username) REFERENCES Users (username)
);

CREATE TABLE IF NOT EXISTS Courses (
    id int identity NOT NULL,
    name nvarchar(256) NOT NULL,
    userDataID int NOT NULL,
    duration int NOT NULL,
    plane nvarchar(256) NOT NULL,
    CONSTRAINT PK_Courses PRIMARY KEY(id),
    CONSTRAINT FK_Courses_UserData FOREIGN KEY(userDataID)  REFERENCES UserData(id)
);

CREATE TABLE IF NOT EXISTS UserCourses(
    courseID int NULL,
    userDataID int NULL,
    dateStarted date NULL,
    numberOfAttendances int NULL,
    CONSTRAINT FK_UserCourses_Courses FOREIGN KEY(courseID) REFERENCES Courses (id),
    CONSTRAINT FK_UserCourses_UserData FOREIGN KEY(userDataID) REFERENCES UserData (id)
);

