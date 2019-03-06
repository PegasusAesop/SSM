package com.pegasus.converter;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class String2LocalDateConverter implements Converter<String, LocalDate>{

	@Override
	public LocalDate convert(String source) {
		LocalDate localDate = LocalDate.parse(source);
		return localDate;
	}

}
