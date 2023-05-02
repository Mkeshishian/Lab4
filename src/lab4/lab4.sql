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
