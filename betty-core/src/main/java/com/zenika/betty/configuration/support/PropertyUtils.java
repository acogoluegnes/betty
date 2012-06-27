/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zenika.betty.configuration.support;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Arnaud Cogoluègnes
 *
 */
public class PropertyUtils {
	
	private static final String PLACEHOLDER_START = "${";
	private static final String PLACEHOLDER_END = "}";
	private static final char PLACEHOLDER_END_LAST_CHAR = PLACEHOLDER_END.charAt(PLACEHOLDER_END.length()-1);
	
	private static final String [] EMPTY_ARRAY = new String[]{};

	public static String [] getPlaceholders(String value) {
		int index = 0;
		List<String> placeholders = new ArrayList<String>();
		while(index < value.length() && index+PLACEHOLDER_START.length() < value.length()) {
			if(value.substring(index, index+PLACEHOLDER_START.length()).equals(PLACEHOLDER_START)) {
				int startOfPlaceholder = index;
				index += PLACEHOLDER_START.length();
				while(index < value.length() && value.charAt(index) != PLACEHOLDER_END_LAST_CHAR) {
					index++;
				}
				if(index < value.length() && value.charAt(index) == PLACEHOLDER_END_LAST_CHAR) {
					placeholders.add(value.substring(startOfPlaceholder+PLACEHOLDER_START.length(), index));
				}
			} else {
				index++;
			}
		}
		return placeholders.toArray(EMPTY_ARRAY);
	}
	
	private static String constructPlaceholder(String name) {
		return PLACEHOLDER_START+name+PLACEHOLDER_END;
	}
	
	public static String replace(String value,String placeholder,String placeholderValue) {
		return value.replace(constructPlaceholder(placeholder), placeholderValue);
	}
	
	public static String getPlaceholder(String value) {
		int start = value.indexOf(PLACEHOLDER_START);
		int index = -1;
		if(start == -1) {
			return null;
		} else {
			index = start;
			while(index < value.length() && value.charAt(index) !=  PLACEHOLDER_END_LAST_CHAR) {
				index++;
			}
			if(index < value.length() && value.charAt(index) == PLACEHOLDER_END_LAST_CHAR) {
				return value.substring(start+PLACEHOLDER_START.length(), index);
			} else {
				return null;
			}
		}
	}
	
	public static void replacePlaceholder(String placeholder,String value) {
		
	}
	
}
