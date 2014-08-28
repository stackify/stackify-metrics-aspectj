/*
 * Copyright 2014 Stackify
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stackify.metric.aop.aspectj;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stackify.metric.MetricFactory;
import com.stackify.metric.aop.Counter;

/**
 * CounterAspect JUnit Test
 * @author Eric Martin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CounterAspect.class, MetricFactory.class})
public class CounterAspectTest {

	/**
	 * testWithoutCounterAnnotation
	 */
	@Test
	public void testWithoutCounterAnnotation() {
		
		PowerMockito.mockStatic(MetricFactory.class);
		
		UUID uuid = TestClass.method();
		
		Assert.assertNotNull(uuid);
		
		PowerMockito.verifyStatic(Mockito.times(0));
		MetricFactory.getCounter(Mockito.anyString(), Mockito.anyString());
	}
	
	/**
	 * testWithCounterAnnotation
	 */
	@Test
	public void testWithCounterAnnotation() {
		
		com.stackify.metric.Counter counter = Mockito.mock(com.stackify.metric.Counter.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getCounter("TestClass", "counterMethod")).thenReturn(counter);
		
		UUID uuid = TestClass.counterMethod();
	
		Assert.assertNotNull(uuid);
		
		PowerMockito.verifyStatic();
		MetricFactory.getCounter("TestClass", "counterMethod");
		
		Mockito.verify(counter).increment();
	}
	
	/**
	 * TestClass
	 */
	private static class TestClass {

		/**
		 * counterMethod
		 */
		@Counter(category = "TestClass", name = "counterMethod")
		public static UUID counterMethod() {
			return UUID.randomUUID();
		}
		
		/**
		 * method
		 */
		public static UUID method() {
			return UUID.randomUUID();
		}
	}
}
