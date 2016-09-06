CREATE TABLE VISITOR (
  VISITOR_ID INTEGER NOT NULL IDENTITY,
  FIRST_NAME VARCHAR(50) NOT NULL,
  LAST_NAME VARCHAR(50) NOT NULL,
  EMAIL_ADDRESS VARCHAR(200) NOT NULL
);

CREATE TABLE TRAINING_COURSE (
  TRAINING_COURSE_ID INTEGER NOT NULL IDENTITY,
  NAME VARCHAR(200) NOT NULL
);

CREATE TABLE TRAINING_COURSE_SECTION (
  TRAINING_COURSE_SECTION_ID INTEGER NOT NULL IDENTITY,
  TRAINING_COURSE_ID INTEGER NOT NULL,
  NAME VARCHAR(200) NOT NULL,
  FOREIGN KEY (TRAINING_COURSE_ID) REFERENCES TRAINING_COURSE (TRAINING_COURSE_ID)
);

CREATE TABLE TRAINING_COURSE_FEEDBACK (
  TRAINING_COURSE_FEEDBACK_ID INTEGER NOT NULL IDENTITY,
  VISITOR_ID INTEGER NOT NULL,
  TRAINING_COURSE_ID INTEGER NOT NULL,
  TRAINING_COURSE_DATE DATE NOT NULL, --represents year, month and day only
  FAVORITE_SECTION_ID INTEGER NOT NULL,
  RATING TINYINT NOT NULL,
  COMMENT CLOB,
  FOREIGN KEY (VISITOR_ID) REFERENCES VISITOR (VISITOR_ID),
  FOREIGN KEY (TRAINING_COURSE_ID) REFERENCES TRAINING_COURSE (TRAINING_COURSE_ID),
  FOREIGN KEY (FAVORITE_SECTION_ID) REFERENCES TRAINING_COURSE_SECTION (TRAINING_COURSE_SECTION_ID)
);