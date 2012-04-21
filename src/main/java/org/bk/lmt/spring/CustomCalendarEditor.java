package org.bk.lmt.spring;


import java.beans.PropertyEditorSupport;
import java.util.Calendar;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.util.StringUtils;

public class CustomCalendarEditor extends PropertyEditorSupport {
	private final DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTime();

	@Override
	public String getAsText() {
		Calendar value = (Calendar) getValue();
		DateTime dateTime = new DateTime(value);

		return ((value != null)?dateTime.toString(this.dateTimeFormatter):"");
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			DateTime dateTime = this.dateTimeFormatter.parseDateTime(text);
			setValue(dateTime.toGregorianCalendar());
		}
	}
}