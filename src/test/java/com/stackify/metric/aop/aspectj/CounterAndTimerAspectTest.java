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
import com.stackify.metric.aop.CounterAndTimer;

/**
 * CounterAndTimerAspect JUnit Test
 * @author Eric Martin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CounterAndTimerAspect.class, MetricFactory.class})
public class CounterAndTimerAspectTest {

	/**
	 * testWithoutCounterAndTimerAnnotation
	 */
	@Test
	public void testWithoutCounterAndTimerAnnotation() {
		
		PowerMockito.mockStatic(MetricFactory.class);
		
		UUID uuid = TestClass.method();
		
		Assert.assertNotNull(uuid);
		
		PowerMockito.verifyStatic(Mockito.times(0));
		MetricFactory.getTimer(Mockito.anyString(), Mockito.anyString());
	}
	
	/**
	 * testWithCounterAndTimerAnnotation
	 */
	@Test
	public void testWithTimerAnnotation() {
		
		com.stackify.metric.CounterAndTimer counterAndTimer = Mockito.mock(com.stackify.metric.CounterAndTimer.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getCounterAndTimer("TestClass", "counterAndTimerMethod")).thenReturn(counterAndTimer);
		
		UUID uuid = TestClass.counterAndTimerMethod();
	
		Assert.assertNotNull(uuid);
		
		PowerMockito.verifyStatic();
		MetricFactory.getCounterAndTimer("TestClass", "counterAndTimerMethod");
		
		Mockito.verify(counterAndTimer).startMs(Mockito.anyLong());
	}
	
	/**
	 * testWithCounterAndTimerAnnotationAndException
	 */
	@Test
	public void testWithCounterAndTimerAnnotationAndException() {
		
		com.stackify.metric.CounterAndTimer counterAndTimer = Mockito.mock(com.stackify.metric.CounterAndTimer.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getCounterAndTimer("TestClass", "counterAndTimerMethodWithException")).thenReturn(counterAndTimer);
		
		try {
			TestClass.counterAndTimerMethodWithException();
		} catch (UnsupportedOperationException e) {
		}
				
		PowerMockito.verifyStatic();
		MetricFactory.getCounterAndTimer("TestClass", "counterAndTimerMethodWithException");
		
		Mockito.verify(counterAndTimer).startMs(Mockito.anyLong());
	}
	
	/**
	 * TestClass
	 */
	private static class TestClass {

		/**
		 * counterAndTimerMethod
		 */
		@CounterAndTimer(category = "TestClass", name = "counterAndTimerMethod")
		public static UUID counterAndTimerMethod() {
			return UUID.randomUUID();
		}
		
		/**
		 * counterAndTimerMethodWithException
		 */
		@CounterAndTimer(category = "TestClass", name = "counterAndTimerMethodWithException")
		public static UUID counterAndTimerMethodWithException() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * method
		 */
		public static UUID method() {
			return UUID.randomUUID();
		}
	}
}
