CREATE ALIAS IF NOT EXISTS H2GIS_SPATIAL FOR "org.h2gis.functions.factory.H2GISFunctions.load";
CALL H2GIS_SPATIAL();

CREATE TABLE elevator (
    id VARCHAR(10) NOT NULL,
    bezeichnung VARCHAR(128),
    haltestellenbereich INT,
    info VARCHAR(256),
    geom POINT
);
	
CREATE TABLE interruption (
    elevatorid VARCHAR(32),
    start DATE,
    stop DATE
);	
