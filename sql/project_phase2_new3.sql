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


DROP TABLE IF EXISTS LEGS CASCADE;
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
    day char(20),
    CONSTRAINT FK_ticket3 FOREIGN KEY(customer_id) REFERENCES PASSENGERS(customers_id) ON DELETE CASCADE,
    CONSTRAINT FK_ticket1 FOREIGN KEY (station_start) REFERENCES STATION(station_num) ON DELETE CASCADE,
    CONSTRAINT FK_ticket2 FOREIGN KEY (station_end) REFERENCES STATION(station_num) ON DELETE CASCADE
);
CREATE TABLE RESERVATION(
    reservation_id  serial primary key,
    ticket_id       int,
    customer_id     int,
    day             char(20),
    time            time,
    ticketed        boolean default false,
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

);
--- functions
DROP FUNCTION if exists int_equal(int, int) CASCADE;
CREATE or replace function int_equal (a int, b int)
    RETURNS BOOLEAN AS $$
    BEGIN
        IF a = b THEN
            return TRUE;
        ELSE
            return FALSE;
        end if;
    END
        $$language plpgsql;


DROP FUNCTION if exists greater_than (int, int) CASCADE;
CREATE or replace function greater_than (a int, b int)
    RETURNS BOOLEAN AS $$
    BEGIN
        IF a > b THEN
            return TRUE;
        ELSE
            return FALSE;
        end if;
    END
        $$language plpgsql;







-- triggers 1
DROP FUNCTION if exists line_disruption_function() CASCADE;
DROP TRIGGER IF EXISTS line_disruption on rail_lines;
DROP FUNCTION if exists recursion_start(integer,integer,character,integer,integer);
DROP FUNCTION if exists recursion(integer,integer,character,integer,integer,int, time without time zone);
Create or replace FUNCTION  recursion(rcustomer_id int,rticket_id int,rday char(20), curr int, stops int, dest int, rarrival_time Time)
returns Boolean as $$
    DECLARE
        w record;
         ---S3 CURSOR FOR SELECT * from LEGS WHERE curr = station_start and start_day = rday and end_day = rday and working = true and available_seats > 0 and departure_time > rarrival_time;
    Begin
        IF curr = dest
            then
            return true;
        end if;
        IF stops > 5 --- this is to limit the amount of stops
            then
            return false;
        end if;
        ---open S3;
        for w in SELECT * from LEGS WHERE curr = station_start and start_day = rday and end_day = rday and working = true and available_seats > 0 and departure_time >= rarrival_time
            loop
            if(recursion(rcustomer_id, rticket_id, rday,w.station_end, stops + 1, dest, w.arrival_time) )
                then
                insert into reservation(ticket_id, customer_id, day, time, ticketed, cost, legs_id) VALUES (rticket_id, rcustomer_id, rday, rarrival_time, false, w.cost, w.legs_id);
                update Legs set available_seats = available_seats - 1 where legs_id = w.legs_id;
                ---close s3;
                return true;
            end if;
            end loop;
    ---close s3;
    return false;
    end;
$$language plpgsql;


Create or replace FUNCTION  recursion_start(rcustomer_id int, rticket_id int, rday char(20), curr int, dest int)
returns Boolean as $$
    DECLARE
    C1 Record;
    ---S2 CURSOR FOR SELECT * from LEGS WHERE curr = station_start and start_day = rday and working = true and available_seats > 0;
    Begin
    ---open S2;
    for C1 in SELECT * from LEGS WHERE curr = station_start and start_day = rday and working = true and available_seats > 0 order by legs_id
        loop
            if recursion(rcustomer_id,rticket_id,rday,C1.station_end, 1, dest, C1.arrival_time )
                THEN
                insert into reservation(ticket_id, customer_id, day, time, ticketed, cost, legs_id) VALUES (rticket_id, rcustomer_id, rday, C1.departure_time, false, C1.cost, C1.legs_id);
                update Legs set available_seats = available_seats - 1 where legs_id = C1.legs_id;
                return true;
            end if;
        end loop;
    return false;
    end;
$$language plpgsql;




CREATE or REPLACE function line_disruption_function()
returns trigger as $$
    DECLARE
        w record;
        ---S1 CURSOR FOR SELECT * FROM TICKET WHERE adj = True and ticket_id in (SELECT ticket_id FROM RESERVATION WHERE legs_id in (SELECT legs_id FROM LEGS WHERE working = false));
    begin
    UPDATE ROUTE set working = FALSE WHERE route_id in (SELECT route_id FROM STATION_ROUTE_RELATION WHERE line_id = OLD.line_id);
    UPDATE LEGS set working = FALSE WHERE route_id in (SELECT route_id FROM route WHERE working = false);
    DELETE FROM TICKET WHERE adj = false and ticket_id in (SELECT ticket_id FROM RESERVATION WHERE legs_id in (SELECT legs_id FROM LEGS WHERE working = false));
    ---open S1;
    FOR w in SELECT * FROM TICKET WHERE adj = True and ticket_id in (SELECT ticket_id FROM RESERVATION WHERE legs_id in (SELECT legs_id FROM LEGS WHERE working = false))
    LOOP
        Delete from reservation where ticket_id = w.ticket_id;
        if not recursion_start( w.customer_id,w.ticket_id,w.day, w.station_start, w.station_end )
            then
            Delete from ticket where ticket_id = w.ticket_id;
        end if;
    ---DELETE FROM RESERVATION where ticket_id in (SELECT ticket_id FROM RESERVATION WHERE legs_id in (SELECT legs_id FROM LEGS WHERE working = false));
    END LOOP;
    ---close s1;
    Delete from route where working = false;
    delete from legs where working = false;

    return old;
        end;
    $$
    language plpgsql;

CREATE or REPLACE function increment_seats()
returns trigger as $$
    BEGIN
        UPDATE LEGS set available_seats = available_seats + 1 WHERE legs_id = old.legs_id;
        return old;
    end;$$ language plpgsql;
drop trigger if exists increment_seats on reservation;

CREATE TRIGGER increment_seats
    BEFORE delete
    on reservation
    for each ROW
        WHEN (OLD.legs_id is not null)
    EXECUTE FUNCTION increment_seats();



CREATE TRIGGER line_disruption
    BEFORE delete
    on rail_lines
    for each ROW
        WHEN (OLD.line_id is not null )
    EXECUTE FUNCTION line_disruption_function();


-- triggers 2
DROP FUNCTION if exists reservation_cancel_function() CASCADE;

DROP TRIGGER IF EXISTS reservation_cancel on reservation;

CREATE or REPLACE FUNCTION reservation_cancel_function()
    RETURNS trigger AS
    $$
    BEGIN
       -- update reservation
        DELETE FROM reservation
        where ticketed ='false' AND time + interval'2 hours' < NEW.time AND day = new.day;
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

--- function for single route search
DROP PROCEDURE if exists single_route(integer,character,integer,integer,integer,integer,time without time zone,integer,integer,character,time without time zone,integer);
Create or Replace PROCEDURE single_route( Rroute_id int, day char(20), curr int,Rtrain_id int, rstops int, Rcost int , Rarrival_time Time, passes int, dest int, legy text, rstart_time Time, depth int)
as
$$
    DECLARE
        C1 RECORD;
        next_legy text;
BEGIN
    if curr = dest then
        INSERT INTO PATHY(cost, stops, arrival_time, stations_passed, legs, start_time) VALUES(Rcost, rstops ,Rarrival_time, passes, legy, rstart_time);
        return;
    end if;
    if rstops > 20 then
        return;
    end if;
    ---open RC;
    for C1 IN SELECT * FROM LEGS WHERE station_start = curr AND start_day = day AND end_day = day  AND available_seats > 0 AND working = 'true' AND train_id = Rtrain_id  AND route_id = Rroute_id AND departure_time >= Rarrival_time  AND working = 'true' AND available_seats > 0 ORDER BY legs_id
    LOOP
        next_legy := TRIM(legy)|| ', '||C1.legs_id;

         CALL single_route(Rroute_id, day, C1.station_end,Rtrain_id,rstops + 1, (C1.cost+ Rcost), C1.arrival_time, (C1.stations_passed + passes), dest,next_legy, rstart_time, depth);
    end loop;

    ---close RC;
    return;
    end;$$LANGUAGE plpgsql;



DROP PROCEDURE if exists multi_route(character,integer,integer,integer,time without time zone,integer,integer,character,time without time zone,integer);
--- function for multiple routes
Create or Replace procedure multi_route(day char(20), curr int, rstops int, Rcost int , Rarrival_time Time, passes int, dest int, legy char(500), rstart_time Time, depth int)
as
$$
    DECLARE
        C1 record;
        next_legy text;
BEGIN
    if curr = dest then
        INSERT INTO PATHY(cost, stops, arrival_time, stations_passed, legs, start_time) VALUES(Rcost, rstops ,Rarrival_time, passes, legy, rstart_time);
        return;
    end if;
    if rstops > depth then
        return;
    end if;
    ---open RC;
    for C1 IN SELECT * FROM LEGS WHERE station_start = curr AND start_day = day AND end_day = day  AND available_seats > 0 AND working = 'true' AND departure_time >= Rarrival_time  AND working = 'true' AND available_seats > 0 ORDER BY legs_id
    LOOP
        next_legy := TRIM(legy)|| ', '||C1.legs_id;
        call multi_route(day, C1.station_end,rstops + 1, Rcost+C1.cost, C1.arrival_time, passes + C1.stations_passed, dest,(TRIM(legy)|| ', '||C1.legs_id), rstart_time, depth);
    end loop;
    ---close RC;
    return;
    end;$$LANGUAGE plpgsql;




