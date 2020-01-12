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
    biography nvarchar(2048) NULL,
    isInstructor boolean NOT NULL default false,
    CONSTRAINT PK_UserData PRIMARY KEY (id),
    CONSTRAINT FK_UserData_Users FOREIGN KEY(username) REFERENCES Users (username)
);

CREATE TABLE IF NOT EXISTS Course (
    id int identity NOT NULL,
    name nvarchar(256) NOT NULL,
    userDataId int NOT NULL,
    duration int NOT NULL,
    plane nvarchar(256) NOT NULL,
    CONSTRAINT PK_Courses PRIMARY KEY(id),
    CONSTRAINT FK_Courses_UserData FOREIGN KEY(userDataId)  REFERENCES UserData(id)
);

CREATE TABLE IF NOT EXISTS UserCourse (
    id int identity NOT NULL,
    courseId int NULL,
    userDataId int NULL,
    dateStarted date NULL,
    numberOfAttendances int NULL,
    CONSTRAINT PK_UserCourses PRIMARY KEY(id),
    CONSTRAINT FK_UserCourses_Courses FOREIGN KEY(courseId) REFERENCES Course (id),
    CONSTRAINT FK_UserCourses_UserData FOREIGN KEY(userDataId) REFERENCES UserData (id)
);

CREATE TABLE IF NOT EXISTS BLOG_POST (
      id int identity NOT NULL,
      title nvarchar(50) NOT NULL,
      post nvarchar(max) NOT NULL,
      lastEditedOn date NOT NULL,
      author nvarchar(256) NOT NULL,
      CONSTRAINT PK_BLOG_POSTS PRIMARY KEY(id),
      CONSTRAINT FK_BLOG_POSTS_Users FOREIGN KEY(author) REFERENCES Users (username)
);
