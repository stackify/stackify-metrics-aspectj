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
import com.stackify.metric.aop.Timer;

/**
 * TimerAspect JUnit Test
 * @author Eric Martin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TimerAspect.class, MetricFactory.class})
public class TimerAspectTest {

	/**
	 * testWithoutTimerAnnotation
	 */
	@Test
	public void testWithoutTimerAnnotation() {
		
		PowerMockito.mockStatic(MetricFactory.class);
		
		UUID uuid = TestClass.method();
		
		Assert.assertNotNull(uuid);
		
		PowerMockito.verifyStatic(Mockito.times(0));
		MetricFactory.getTimer(Mockito.anyString(), Mockito.anyString());
	}
	
	/**
	 * testWithTimerAnnotation
	 */
	@Test
	public void testWithTimerAnnotation() {
		
		com.stackify.metric.Timer timer = Mockito.mock(com.stackify.metric.Timer.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getTimer("TestClass", "timerMethod")).thenReturn(timer);
		
		UUID uuid = TestClass.timerMethod();
	
		Assert.assertNotNull(uuid);

		PowerMockito.verifyStatic();
		MetricFactory.getTimer("TestClass", "timerMethod");
		
		Mockito.verify(timer).startMs(Mockito.anyLong());
	}
	
	/**
	 * testWithTimerAnnotationAndException
	 */
	@Test
	public void testWithTimerAnnotationAndException() {
		
		com.stackify.metric.Timer timer = Mockito.mock(com.stackify.metric.Timer.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getTimer("TestClass", "timerMethodWithException")).thenReturn(timer);
		
		try {
			TestClass.timerMethodWithException();
		} catch (UnsupportedOperationException e) {
		}

		PowerMockito.verifyStatic();
		MetricFactory.getTimer("TestClass", "timerMethodWithException");
		
		Mockito.verify(timer).startMs(Mockito.anyLong());
	}
	
	/**
	 * TestClass
	 */
	private static class TestClass {

		/**
		 * timerMethod
		 */
		@Timer(category = "TestClass", name = "timerMethod")
		public static UUID timerMethod() {
			return UUID.randomUUID();
		}
		
		/**
		 * timerMethodWithException
		 */
		@Timer(category = "TestClass", name = "timerMethodWithException")
		public static UUID timerMethodWithException() {
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
