package io.swagger.pet.Helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static long getRandomNumber() {
		// A single statement can also be used here--
		// LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyHHmmss"));
		SimpleDateFormat dateFormatter = new SimpleDateFormat("YYMMddhhmmssMs");
		return Long.parseUnsignedLong(dateFormatter.format(new Date()));
	}

	public static String getRandomString() {
		return getRandomString(-1);
	}

	public static String getRandomString(int len) {
		String random = "pet_" + getRandomNumber();
		if (len < random.length() && len > 0)
			random = random.substring(0, len);
		return random;
	}
}
