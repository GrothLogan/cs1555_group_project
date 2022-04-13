DROP TABLE IF EXISTS STATION CASCADE;
DROP TABLE IF EXISTS RAIL_LINES CASCADE;
DROP TABLE IF EXISTS ROUTE CASCADE;
DROP TABLE IF EXISTS TRAIN_SCHEDULE CASCADE;
DROP TABLE IF EXISTS TRAIN CASCADE;
DROP TABLE IF EXISTS PASSENGERS CASCADE;
DROP TABLE IF EXISTS CLOCK CASCADE;
DROP TABLE IF EXISTS STATION_RAIL_RELATION CASCADE;
DROP TABLE IF EXISTS STATION_ROUTE_RELATION CASCADE;
DROP TABLE IF EXISTS RESERVATION CASCADE;
DROP TABLE IF EXISTS LEGS CASCADE;
DROP TABLE IF EXISTS PATHY CASCADE;
DROP TABLE IF EXISTS TICKET CASCADE;
DROP user IF EXISTS username;
CREATE user username with password 'password';
ALTER USER username WITH SUPERUSER;
CREATE TABLE STATION(
    station_num     int NOT NULL PRIMARY KEY,
    name          char(40),
    opening_time    TIME not NULL,
    closing_time    TIME not NULL,
    stop_delay      int,
    street          char(40),
    town            char(40),
    postal_code     char(40)
);

CREATE TABLE RAIL_LINES(
    line_id     int,
    speed_limit int,
    station_num int,
    distance_prev    int,
    prev_station    int, /* should be null if first on line */
    distance_next    int,
    next_station    int,
    Constraint FK_RAIL_LINES1 FOREIGN KEY(station_num) REFERENCES STATION(station_num) ON DELETE CASCADE,
    Constraint FK_RAIL_LINES2 FOREIGN KEY(prev_station) REFERENCES STATION(station_num) ON DELETE CASCADE,
    Constraint FK_RAIL_LINES3 FOREIGN KEY(next_station) REFERENCES STATION(station_num) ON DELETE CASCADE,
    CONSTRAINT FK_RAIL_LINES4 PRIMARY KEY (line_id,station_num)
);
/*
CREATE TABLE STATION_RAIL_RELATION(
  line_id int,
  station_num int,
  Constraint FK_STATION_RAIL_RELATION1 FOREIGN KEY(station_num) REFERENCES STATION(station_num) ON DELETE CASCADE,
  Constraint FK_STATION_RAIL_RELATION2 FOREIGN KEY(line_id) REFERENCES RAIL_LINES(line_id) ON DELETE CASCADE
);
*/
CREATE TABLE ROUTE(
    route_id    int primary key,
    working boolean default True
);

CREATE TABLE STATION_ROUTE_RELATION(
  line_id int,
    station_route_id serial PRIMARY KEY,
    route_id int,
  station_num int,
  stop boolean,
  station_next int,
  distance int,
  speed_limit int,
  station_order int,
  Constraint FK_STATION_ROUTE_RELATION1 FOREIGN KEY(station_num) REFERENCES STATION(station_num) ON DELETE CASCADE,
  Constraint FK_STATION_ROUTE_RELATION2 FOREIGN KEY(route_id) REFERENCES ROUTE(route_id) ON DELETE CASCADE
);

CREATE TABLE TRAIN(
    train_id       int PRIMARY KEY,
    train_name     varchar(30),
    description    varchar(30),
    top_speed      int,
    seats          int,
    price_per_km   int
);

CREATE TABLE TRAIN_SCHEDULE(
    schedule_id serial primary key ,
    route_id    int,
    train_id    int,
    --line_id     int,
    day         char(20),
    time        time,
    CONSTRAINT FK_TRAIN_SCHEDULE1 FOREIGN KEY(route_id) REFERENCES ROUTE(route_id) ON DELETE CASCADE,
    CONSTRAINT FK_TRAIN_SCHEDULE2 FOREIGN KEY(train_id) REFERENCES TRAIN(train_id) ON DELETE CASCADE
    --CONSTRAINT PK_TRAIN_SCHEDULE3 PRIMARY KEY (route_id,train_id,day,time)
    --CONSTRAINT FK_TRAIN_SCHEDULE4 FOREIGN KEY(line_id)  REFERENCES RAIL_LINES(line_id) ON DELETE CASCADE
);




CREATE TABLE PASSENGERS
(
    customers_id int PRIMARY KEY,
    fname        char(20),
    lname        char(20),
    email        varchar(40),
    phone        char(20),
    street       char(40),
    town         char(40),
    postal_code   char(40)
);


CREATE TABLE CLOCK(
    p_date date,
    day     char(20), /* sample data is in this form */
    time    time
);

CREATE TABLE LEGS(
    -- plan query this table in a recursive function that returns a list
    route_id       int,
    legs_id        serial PRIMARY KEY ,
    train_id       int,
    station_start  int,
    station_end    int,
    start_day            char(20),
    end_day     char(20),
    distance       int,
    stations_passed int, -- counts stations passed it counts where it arrives
    cost int,
    departure_time  time,
    arrival_time    time,
    available_seats int,
    working BOOLEAN default true,
    CONSTRAINT FK_legs1 FOREIGN KEY (train_ID) REFERENCES TRAIN(train_id) ON DELETE CASCADE,
    CONSTRAINT FK_legs2 FOREIGN KEY (station_start) REFERENCES STATION(station_num) ON DELETE CASCADE,
    CONSTRAINT FK_legs3 FOREIGN KEY (station_end) REFERENCES STATION(station_num) ON DELETE CASCADE,
    CONSTRAINT FK_legs4 FOREIGN KEY (route_id) REFERENCES ROUTE(route_id) ON DELETE CASCADE
);
CREATE TABLE TICKET(
    ticket_id serial Primary KEY,
    station_start  int,
    station_end    int,
    payed boolean default false,
    customer_id int,
    adj boolean,

    CONSTRAINT FK_ticket3 FOREIGN KEY(customer_id) REFERENCES PASSENGERS(customers_id) ON DELETE CASCADE,
    CONSTRAINT FK_ticket1 FOREIGN KEY (station_start) REFERENCES STATION(station_num) ON DELETE CASCADE,
    CONSTRAINT FK_ticket2 FOREIGN KEY (station_end) REFERENCES STATION(station_num) ON DELETE CASCADE
)
CREATE TABLE RESERVATION(
    reservation_id  serial primary key,
    ticket_id       int,
    customer_id     int,
    day             char(20),
    time            time,
    ticketed        boolean,
    --schedule_id     int references TRAIN_SCHEDULE,
    cost            int,
    legs_id         int,
    --adjustable      boolean,
    CONSTRAINT FK_RESERVATION FOREIGN KEY(legs_id) REFERENCES LEGS(legs_id) ON DELETE CASCADE,
    CONSTRAINT FK_RESERVATION2 FOREIGN KEY(customer_id) REFERENCES PASSENGERS(customers_id) ON DELETE CASCADE,
    CONSTRAINT FK_RESERVATION3 FOREIGN KEY(ticket_id) REFERENCES TICKET(ticket_id) ON DELETE CASCADE
    --CONSTRAINT FK_RESERVATION2 FOREIGN KEY(day) REFERENCES TRAIN_SCHEDULE(day) ON DELETE CASCADE,
    --CONSTRAINT FK_RESERVATION3 FOREIGN KEY(time) REFERENCES TRAIN_SCHEDULE(time) ON DELETE CASCADE

);

CREATE TABLE PATHY(
    cost int,
    stops int,
    start_time time,
    arrival_time time,
    stations_passed int,
    legs char(500)

)

-- triggers 1
-- trigger 1 doesnt work
/*
DROP FUNCTION if exists line_disruption_function() CASCADE;
DROP TRIGGER IF EXISTS line_disruption on reservation;

CREATE or REPLACE function line_disruption_function()
returns trigger as $$
    begin
    UPDATE ROUTE set working = FALSE WHERE route_id in (SELECT route_id FROM STATION_ROUTE_RELATION WHERE line_id in (SELECT line_id from OLD));
    UPDATE LEGS set working = FALSE WHERE route_id in (SELECT route_id FROM route WHERE working = false);
    DECLARE S1 CURSOR FOR SELECT * FROM TICKET WHERE ticket_id in (SELECT ticket_id FROM RESERVATION WHERE legs_id in (SELECT legs_id FROM LEGS WHERE working = false));
    OPEN CURSOR S1;
    FOR * something in S1
    LOOP;
        if something.
    END LOOP;
    return;
        end;
    $$
    language plpgsql;

CREATE TRIGGER line_disruption
    BEFORE delete
    on rail_lines
    for each ROW
        WHEN (OLD.line_id != null )
    EXECUTE FUNCTION line_disruption_function();


*/
-- triggers 2
DROP FUNCTION if exists reservation_cancel_function() CASCADE;

DROP TRIGGER IF EXISTS reservation_cancel on reservation;

CREATE or REPLACE FUNCTION reservation_cancel_function()
    RETURNS trigger AS
    $$
    BEGIN
       -- update reservation
        DELETE FROM reservation
        where ticketed ='false' AND time + interval'2 hours' < NEW.time;
        return new;
    END;
    $$
    LANGUAGE plpgsql;
CREATE TRIGGER reservation_cancel
    after update of time
    on CLOCK
    for each ROW
        WHEN (
            NEW.time is not null
            )
    EXECUTE FUNCTION reservation_cancel_function();


