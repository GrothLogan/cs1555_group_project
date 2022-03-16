DROP TABLE IF EXISTS STATION CASCADE;
DROP TABLE IF EXISTS RAIL_LINES CASCADE;
DROP TABLE IF EXISTS ROUTE CASCADE;
DROP TABLE IF EXISTS TRAIN_SCHEDULE CASCADE;
DROP TABLE IF EXISTS TRAIN CASCADE;
DROP TABLE IF EXISTS PASSENGERS CASCADE;
DROP TABLE IF EXISTS CLOCK CASCADE;
DROP TABLE IF EXISTS DIST CASCADE;
DROP TABLE IF EXISTS STATION_RAIL_RELATION CASCADE;
DROP TABLE IF EXISTS STATION_ROUTE_RELATION CASCADE;
DROP TYPE IF EXISTS address1 CASCADE;
DROP TYPE IF EXISTS address2 CASCADE;


CREATE TYPE address1 AS(
    street char(20),
    city char(20),
    zip char(10)
);

CREATE TYPE address2 AS(
    street char(20),
    city char(20),
    zip char(10)
);

CREATE TABLE STATION(
    station_num     int PRIMARY KEY,
    address         address1,
    hours_operation interval
);

CREATE TABLE RAIL_LINES(
    line_id     int PRIMARY KEY,
    speed_limit char(20)
);

CREATE TABLE STATION_RAIL_RELATION(
  line_id int,
  station_num int,
  Constraint FK_STATION_RAIL_RELATION1 FOREIGN KEY(station_num) REFERENCES STATION(station_num) ON DELETE CASCADE,
  Constraint FK_STATION_RAIL_RELATION2 FOREIGN KEY(line_id) REFERENCES RAIL_LINES(line_id) ON DELETE CASCADE
);

CREATE TABLE ROUTE(
    route_id    int PRIMARY KEY
);

CREATE TABLE STATION_ROUTE_RELATION(
  route_id int,
  station_num int,
  Constraint FK_STATION_ROUTE_RELATION1 FOREIGN KEY(station_num) REFERENCES STATION(station_num) ON DELETE CASCADE,
  Constraint FK_STATION_ROUTE_RELATION2 FOREIGN KEY(route_id) REFERENCES ROUTE(route_id) ON DELETE CASCADE
);

CREATE TABLE TRAIN(
    train_id       int PRIMARY KEY,
    top_speed      varchar(30),
    seats          int NOT NULL,
    price_per_mile dec(4,2)
);

CREATE TABLE TRAIN_SCHEDULE(
    route_id    int,
    train_id    int,
    line_id     int,
    day         char(20),
    time        time,
    CONSTRAINT PK_TRAIN_SCHEDULE1 PRIMARY KEY (route_id,train_id,line_id),
    CONSTRAINT FK_TRAIN_SCHEDULE2 FOREIGN KEY(route_id) REFERENCES ROUTE(route_id) ON DELETE CASCADE,
    CONSTRAINT FK_TRAIN_SCHEDULE3 FOREIGN KEY(train_id) REFERENCES TRAIN(train_id) ON DELETE CASCADE,
    CONSTRAINT FK_TRAIN_SCHEDULE4 FOREIGN KEY(line_id)  REFERENCES RAIL_LINES(line_id) ON DELETE CASCADE
);




CREATE TABLE PASSENGERS
(
    customers_id int,
    fname        char(20),
    lname        char(20),
    emali        varchar(40),
    phone        char(20),
    address      address2
);


CREATE TABLE CLOCK(
    p_date date
);

/*CREATE TABLE DIST(
station_num1 int,
    station_num2 int,
    distance int,
    CONSTRAINT PK_DIST PRIMARY KEY(station_num1,station_num2),
    CONSTRAINT FK_DIST1 FOREIGN KEY(station_num1) REFERENCES STATION(station_num),
    CONSTRAINT FK_DIST2 FOREIGN KEY(station_num2) REFERENCES STATION(station_num)

);

 */


