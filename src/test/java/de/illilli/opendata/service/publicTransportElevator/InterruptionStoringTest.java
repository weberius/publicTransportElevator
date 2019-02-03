package de.illilli.opendata.service.publicTransportElevator;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.illilli.jdbc.SelectListDao;
import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.converter.Url2InterruptionList;
import de.illilli.opendata.service.publicTransportElevator.jdbc.InterruptionDTO;
import de.illilli.opendata.service.publicTransportElevator.jdbc.SelectInterruption;
import de.illilli.opendata.service.publicTransportElevator.jdbc.StoringResult;
import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

public class InterruptionStoringTest extends Main {

	/**
	 * Dieser Test testet, ob Daten in die Datenbank geschrieben werden. Es sollen
	 * nur inserts durchgeführt werden.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testInsert() throws SQLException, IOException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.stoerungen.insert.json");
		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);

		InterruptionStoring storing = new InterruptionStoring(interruptionList, super.connection);
		storing.storeToDb();
		StoringResult result = storing.getResult();

		int expected = 4;
		int actual = result.getInserted();

		Assert.assertEquals(expected, actual);

	}

	/**
	 * Dieser Test prüft, ob doppelte Werte verworfen werden. Dabei werden die
	 * elevatorid und der Zeitstempel des Eintrags miteinander verglichen.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testSkip() throws SQLException, IOException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.stoerungen.skip.json");
		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);

		InterruptionStoring storing = new InterruptionStoring(interruptionList, super.connection);
		storing.storeToDb();
		StoringResult result = storing.getResult();

		int expected = 4;
		int actual = result.getSkipped();

		Assert.assertEquals(expected, actual);

	}

	/**
	 * Mit diesem Test werden zunächst vier Einträge in die Datenbank geschrieben.
	 * Danach werden vier weitere Einträge mit modifizierten Zeitstempeln versehen,
	 * sodass die Einträge in der DAtenbank aktualisiert werden.
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	@Test
	public void testUpdate() throws SQLException, IOException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.stoerungen.update.json");
		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);

		InterruptionStoring storing = new InterruptionStoring(interruptionList, super.connection);
		storing.storeToDb();
		StoringResult result = storing.getResult();

		// check updated
		int expected = 4;
		int actual = result.getUpdated();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testUpdatedValues() throws SQLException, IOException, ParseException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.stoerungen.update.json");
		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);

		InterruptionStoring storing = new InterruptionStoring(interruptionList, super.connection);
		storing.storeToDb();
		StoringResult result = storing.getResult();

		System.out.println(result.toString());

		// check updated
		// first read from database
		List<InterruptionDTO> dtoList = new SelectListDao<InterruptionDTO>(new SelectInterruption(), connection)
				.execute();

		for (InterruptionDTO dto : dtoList) {
			String format = "yyyy-MM-dd HH:mm:ss";
			String stop = "2019-01-22 23:39:09";
			Timestamp expectedStop = new Timestamp(new SimpleDateFormat(format).parse(stop).getTime());
			Timestamp actualStop = dto.getStop();
			Assert.assertEquals(expectedStop, actualStop);

			String start = "2019-01-22 21:39:09";
			Timestamp expectedStart = new Timestamp(new SimpleDateFormat(format).parse(start).getTime());
			Timestamp actualStart = dto.getStart();
			Assert.assertEquals(expectedStart, actualStart);
		}

	}

	/**
	 * <p>
	 * In diesem Test werden zunächst eine Störungen in die Datenbank geschrieben.
	 * Dann wird eine Störung des gleichen Aufzugs im Zeitrahmen der 'period'
	 * hinzugefügt. Der Zeitraum der Störung muss damit um den zweiten Eintrag
	 * erweitert werden. Danach wird wird eine weitere Störung hinzugefügt. Diese
	 * liegt ausserhalb der Period. Es muss daher eine weitere Störung des gleichen
	 * Aufzugs festgeschrieben werden. Diese zweite Störung wird dann um den
	 * Zeitraum der Period erweitert.
	 * </p>
	 * <p>
	 * Es muss somit zu zwei inserted und zwei updates kommen.
	 * </p>
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws ParseException
	 */
	@Test
	public void testUpdateMoreThanOne() throws SQLException, IOException, ParseException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.stoerungen.updateMoreThanOne.json");
		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);

		InterruptionStoring storing = new InterruptionStoring(interruptionList, super.connection);
		storing.storeToDb();
		StoringResult result = storing.getResult();

		System.out.println(result.toString());

		// check inserted
		int expected = 3;
		int actual = result.getUpdated();

		Assert.assertEquals(expected, actual);

		// check updated
		expected = 3;
		actual = result.getUpdated();

		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testUpdateFirstInterruption() throws SQLException, IOException, ParseException {

		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.stoerungen.updateMoreThanOne.json");
		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);

		// store values to database
		InterruptionStoring storing = new InterruptionStoring(interruptionList, super.connection);
		storing.storeToDb();

		// check updated
		// first read from database
		List<InterruptionDTO> dtoList = new SelectListDao<InterruptionDTO>(new SelectInterruption(), connection)
				.execute();

		InterruptionDTO dto = dtoList.get(0);

		String format = "yyyy-MM-dd HH:mm:ss";
		String stop = "2019-01-23 00:39:09";
		Timestamp expectedStop = new Timestamp(new SimpleDateFormat(format).parse(stop).getTime());
		Timestamp actualStop = dto.getStop();
		Assert.assertEquals(expectedStop, actualStop);

		String start = "2019-01-22 21:39:09";
		Timestamp expectedStart = new Timestamp(new SimpleDateFormat(format).parse(start).getTime());
		Timestamp actualStart = dto.getStart();
		Assert.assertEquals(expectedStart, actualStart);

	}

}
