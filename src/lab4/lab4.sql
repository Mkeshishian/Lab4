DROP TABLE IF EXISTS ActualTripStopInfo;
DROP TABLE IF EXISTS TripStopInfo;
DROP TABLE IF EXISTS Stop;
DROP TABLE IF EXISTS TripOffering;
DROP TABLE IF EXISTS Driver;
DROP TABLE IF EXISTS Bus;
DROP TABLE IF EXISTS Trip;

CREATE TABLE Trip (
    TripNumber INT PRIMARY KEY,
    StartLocationName VARCHAR(255),
    DestinationName VARCHAR(255)
);

CREATE TABLE Bus (
    BusID INT PRIMARY KEY,
    Model VARCHAR(255),
    Year YEAR
);

CREATE TABLE Driver (
    DriverName VARCHAR(255) PRIMARY KEY,
    DriverTelephoneNumber VARCHAR(20)
);

CREATE TABLE TripOffering (
    TripNumber INT,
    Date DATE,
    ScheduledStartTime TIME,
    ScheduledArrivalTime TIME,
    DriverName VARCHAR(255),
    BusID INT,
    PRIMARY KEY (TripNumber, Date, ScheduledStartTime),
    FOREIGN KEY (TripNumber) REFERENCES Trip(TripNumber),
    FOREIGN KEY (DriverName) REFERENCES Driver(DriverName),
    FOREIGN KEY (BusID) REFERENCES Bus(BusID)
);

CREATE TABLE Stop (
    StopNumber INT PRIMARY KEY,
    StopAddress VARCHAR(255)
);

CREATE TABLE ActualTripStopInfo (
    TripNumber INT,
    Date DATE,
    ScheduledStartTime TIME,
    StopNumber INT,
    ScheduledArrivalTime TIME,
    ActualStartTime TIME,
    ActualArrivalTime TIME,
    NumberOfPassengerIn INT,
    NumberOfPassengerOut INT,
    PRIMARY KEY (TripNumber, Date, ScheduledStartTime, StopNumber),
    FOREIGN KEY (TripNumber, Date, ScheduledStartTime) REFERENCES TripOffering(TripNumber, Date, ScheduledStartTime),
    FOREIGN KEY (StopNumber) REFERENCES Stop(StopNumber)
);

CREATE TABLE TripStopInfo (
    TripNumber INT,
    StopNumber INT,
    SequenceNumber INT,
    DrivingTime TIME,
    PRIMARY KEY (TripNumber, StopNumber),
    FOREIGN KEY (TripNumber) REFERENCES Trip(TripNumber),
    FOREIGN KEY (StopNumber) REFERENCES Stop(StopNumber)
);

INSERT INTO Trip (TripNumber, StartLocationName, DestinationName) 
VALUES (1, 'Pomona', 'Los Angeles'), 
       (2, 'Pomona', 'San Francisco'), 
       (3, 'Los Angeles', 'Pomona'), 
       (4, 'San Francisco', 'Pomona');

INSERT INTO Bus (BusID, Model, Year)
VALUES (1, 'Bluebird', 2010),
       (2, 'Prevost', 2015),
       (3, 'MCI', 2020);

INSERT INTO Driver (DriverName, DriverTelephoneNumber)
VALUES ('John Smith', '555-1234'),
       ('Jane Doe', '555-5678'),
       ('Bob Johnson', '555-9012');

INSERT INTO TripOffering (TripNumber, Date, ScheduledStartTime, ScheduledArrivalTime, DriverName, BusID) 
VALUES 
(1, '2023-06-01', '07:00:00', '08:30:00', 'John Smith', 1),
(1, '2023-06-01', '08:00:00', '09:30:00', 'Jane Doe', 2),
(1, '2023-06-02', '07:00:00', '08:30:00', 'John Smith', 1),
(2, '2023-06-03', '09:00:00', '15:00:00', 'Bob Johnson', 3),
(2, '2023-06-04', '09:00:00', '15:00:00', 'Bob Johnson', 3),
(3, '2023-06-01', '07:00:00', '08:30:00', 'John Smith', 1),
(3, '2023-06-02', '07:00:00', '08:30:00', 'Jane Doe', 2),
(4, '2023-06-01', '07:00:00', '14:00:00', 'Bob Johnson', 3),
(4, '2023-06-02', '07:00:00', '14:00:00', 'Bob Johnson', 3);

INSERT INTO Stop (StopNumber, StopAddress)
VALUES (1, '123 Main St, Pomona'),
       (2, '456 Broadway, Los Angeles'),
       (3, '789 Market St, San Francisco'),
       (4, '111 Elm St, Riverside');

-- INSERT INTO Driver (DriverName, DriverTelephoneNumber)
-- VALUES ('John Smith', '555-1234'),
--        ('Jane Doe', '555-5678'),
--        ('Bob Johnson', '555-9012');

-- INSERT INTO Bus (BusID, Model, Year)
-- VALUES (1, 'Bluebird', 2010),
--        (2, 'Prevost', 2015),
--        (3, 'MCI', 2020);
INSERT INTO TripStopInfo (TripNumber, StopNumber, SequenceNumber, DrivingTime)
VALUES  
(1, 1, 1, '00:00:00'), 
(1, 2, 2, '00:30:00'), 
(1, 3, 3, '01:00:00'), 
(2, 1, 1, '00:00:00'), 
(2, 2, 2, '02:00:00'), 
(2, 3, 3, '05:00:00'), 
(3, 2, 1, '00:30:00'), 
(3, 1, 2, '01:00:00');

INSERT INTO ActualTripStopInfo (TripNumber, Date, ScheduledStartTime, StopNumber, ScheduledArrivalTime, ActualStartTime, ActualArrivalTime, NumberOfPassengerIn, NumberOfPassengerOut) 
VALUES  
(1, '2023-06-01', '07:00:00', 1, '07:50:00', '07:01:00', '08:31:00', 20, 5), 
(1, '2023-06-01', '07:00:00', 2, '07:30:00', '07:35:00', '08:50:00', 15, 10), 
(1, '2023-06-01', '07:00:00', 3, '08:00:00', '08:05:00', '09:10:00', 10, 20), 
(2, '2023-06-03', '09:00:00', 1, '09:00:00', '09:05:00', '10:30:00', 25, 5), 
(2, '2023-06-03', '09:00:00', 2, '11:00:00', '11:05:00', '12:30:00', 20, 10), 
(2, '2023-06-03', '09:00:00', 3, '14:00:00', '14:05:00', '15:30:00', 15, 15), 
(3, '2023-06-01', '07:00:00', 2, '07:30:00', '07:35:00', '08:50:00', 10, 0), 
(3, '2023-06-01', '07:00:00', 1, '07:00:00', '07:01:00', '08:31:00', 15, 5), 
(3, '2023-06-01', '07:00:00', 3, '08:00:00', '08:05:00', '09:10:00', 20, 20);



ALTER TABLE TripOffering ADD ActualStartTime TIME;
ALTER TABLE TripOffering ADD ActualArrivalTime TIME;
