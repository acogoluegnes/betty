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
package com.zenika.betty.configuration;

import org.junit.Assert;
import org.junit.Test;

import com.zenika.betty.configuration.support.PropertyUtils;

/**
 * @author Arnaud Cogoluègnes
 *
 */
public class PropertyUtilsTest {

	@Test public void getPlaceholdersNoPlaceholder() {
		Assert.assertEquals(0,PropertyUtils.getPlaceholders("this is a test").length);
	}
	
	@Test public void getPlaceholdersOnePlaceholder() {
		Assert.assertArrayEquals(new String[]{"user"},PropertyUtils.getPlaceholders("${user}"));
	}
	
	@Test public void getPlaceholdersOnePlaceholderStart() {
		Assert.assertArrayEquals(new String[]{"PORT"},PropertyUtils.getPlaceholders("${PORT}/test"));
	}
	
	@Test public void getPlaceholdersOnePlaceholderMiddle() {
		Assert.assertArrayEquals(new String[]{"user"},PropertyUtils.getPlaceholders("/home/${user}/documents"));
	}
	
	@Test public void getPlaceholdersOnePlaceholderEnd() {
		Assert.assertArrayEquals(new String[]{"user"},PropertyUtils.getPlaceholders("/home/${user}"));
	}
	
	@Test public void getPlaceholdersOnePlaceholderNotFinished() {
		Assert.assertEquals(0,PropertyUtils.getPlaceholders("/home/${user/documents").length);
	}
	
	@Test public void getPlaceholdersPlaceholders() {
		Assert.assertArrayEquals(new String[]{"user","app"},PropertyUtils.getPlaceholders("/home/${user}/${app}/data"));
	}

	
}
