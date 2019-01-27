package de.illilli.opendata.service.publicTransportElevator.converter;

import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.test.annotation.type.IntegrationTest;

import de.illilli.opendata.service.Converter;
import de.illilli.opendata.service.publicTransportElevator.model.Interruption;

@Category(IntegrationTest.class)
public class Url2InterruptionListTest {

	@Test
	public void testGetAsObject() {
		URL url = this.getClass().getClassLoader().getResource("fahrtreppen.stoerungen.20190122.json");
		Converter<List<Interruption>, URL> converter = new Url2InterruptionList();
		List<Interruption> interruptionList = converter.getAsObject(url);
		// System.out.println(interruptionList);
		Assert.assertTrue(interruptionList != null);
	}

}
