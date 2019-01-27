# publicTransportElevator

Diese Applikation bietet den Zugriff auf die Aufzüge und die Störungen der Aufzüge im Bereich der KVB. Die Daten basieren auf den Datensätzen [Fahrtreppen KVB Köln](https://offenedaten-koeln.de/dataset/fahrtreppen-kvb-koeln) der [Offenen Daten der Stadt Köln](https://www.offenedaten-koeln.de/). Die Daten werden als json-Daten verwendet und in einer H2Gis persistiert. Die Applikation bietet Schnittstellen zur Abfrage der Daten und eine einfache Weboberfläche zur Anzeige im räumlichen Kontext. Diese Applikation verarbeitet nicht die Daten der Haltestellen in Köln.

# Entwicklungsstand

Dieses Projekt befindet sich in der Entwicklung

# Technologien/ Bibliotheken

- Apache Commons
- Apache DBUtils
- Google Gson
- H2GIS
- Java
- Opendatalab GeoJson

# Fachliche Objekte

## Elevator

Das Objekt Elevator bildet einen Aufzug ab. Es enhält folgende Informationen:

- id: Eindeutige ID der Fahrtreppe, im Fahrtreppen-Datensatz die Kennung;
- bezeichnung: Beschreibung der Fahrtreppe;
- haltestellenbereich: Die id die zur Erkennung der Halterstelle verwendet werden kann;
- info: zusätzliche Informationen, zur Zeit nicht genutzt;
- coordinate: latitude, longitude

## Interruption

Das Objekt Interruption stellt die Unterbrechung einer Fahrtreppe dar. Es können je Fahrtreppe mehrere Einträge vorhanden sein.

- elevatorid: Die Elevator-ID; 
- start: Zeitstempel für das erste Feststellen der Unterbrechung
- stop: Zeitstempel für das letzte Feststellen der Unterbrechung

# Schnittstellen

Schnittstellen, die mit TODO gekennzeichnet sind sind nicht implementiert.

## /publicTransportElevator/elevators

Diese Schnittstelle gibt alle Haltestellen mit allen Informationen im JSON-Format zurück.

Beispiel [/publicTransportElevator/elevators](http://localhost:8080/publicTransportElevator/elevators)

format: json, geojson

Beispiel [/publicTransportElevator/elevators?format=geojson](http://localhost:8080/publicTransportElevator/elevators?format=geojson)

## /publicTransportElevator/elevators/{id}

Diese Schnittstelle gibt die Daten einer Fahrtreppen mit allen Informationen im JSON-Format zurück.

Beispiel [/publicTransportElevator/elevators/{id}](http://localhost:8080/publicTransportElevator/elevators/001-51)

format: json, geojson

Beispiel [/publicTransportElevator/elevators/{id}?format=geojson](http://localhost:8080/publicTransportElevator/elevators/001-51?format=geojson)

## /publicTransportElevator/stop/{id}

Diese Schnittstelle gibt die Daten aller Fahrtreppen einer Haltestelle mit allen Informationen im JSON-Format zurück. Die Haltestellen werden per 'id' identifiziert.

Beispiel [/publicTransportElevator/stop/{id}](http://localhost:8080/publicTransportElevator/stop/9)

format: json, geojson

Beispiel [/publicTransportElevator/stop/{id}?format=geojson](http://localhost:8080/publicTransportElevator/stop/9?format=geojson)

# Daten

Die Applikaton beruht auf den Daten der KVB. Die Daten sehen nach geojson aus. Die Geojson Parser werfen ohne Anpassungen einen Fehler. Darüber hinaus ist es möglich, dass Daten doppelt vorkommen. Diese müssen ggf. ausgefiltert werden.

- [Fahrtreppen KVB Koeln im json Format](https://online-service.kvb-koeln.de/geoserver/OPENDATA/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=ODENDATA%3Afahrtreppen&outputFormat=application/json)
- [Stoerungen Fahrtreppen KVB Koeln im json Format](https://online-service.kvb-koeln.de/geoserver/OPENDATA/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=ODENDATA%3Afahrtreppen_gestoert&outputFormat=application/json)

# Datenbank

## Tabellen anlegen

### elevator

	CREATE TABLE elevator (
	    id VARCHAR(10) NOT NULL,
	    bezeichnung VARCHAR(128),
	    haltestellenbereich INT,
	    info VARCHAR(256),
	    geom POINT
	);

### interruption

	CREATE TABLE interruption (
	    elevatorid VARCHAR(32),
	    start DATE,
	    stop DATE,
	    period INT
	);	

# Installation

Diese Applikation soll sowohl als Web-Applikation auf einem Tomcat installiert werden, als auch auf Heroku.

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.
