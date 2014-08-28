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
import com.stackify.metric.aop.Average;

/**
 * AverageAspect JUnit Test
 * @author Eric Martin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({AverageAspect.class, MetricFactory.class})
public class AverageAspectTest {

	/**
	 * testWithoutAverageAnnotation
	 */
	@Test
	public void testWithoutAverageAnnotation() {
		
		PowerMockito.mockStatic(MetricFactory.class);
		
		UUID uuid = TestClass.method();
		
		Assert.assertNotNull(uuid);
		
		PowerMockito.verifyStatic(Mockito.times(0));
		MetricFactory.getAverage(Mockito.anyString(), Mockito.anyString());
	}
	
	/**
	 * testWithAverageAnnotationByte
	 */
	@Test
	public void testWithAverageAnnotationByte() {
		
		com.stackify.metric.Average average = Mockito.mock(com.stackify.metric.Average.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getAverage("TestClass", "averageByteMethod")).thenReturn(average);
		
		Byte b = TestClass.averageByteMethod();
	
		Assert.assertNotNull(b);
		
		PowerMockito.verifyStatic();
		MetricFactory.getAverage("TestClass", "averageByteMethod");
		
		Mockito.verify(average).addValue(b.doubleValue());
	}
	
	/**
	 * testWithAverageAnnotationShort
	 */
	@Test
	public void testWithAverageAnnotationShort() {
		
		com.stackify.metric.Average average = Mockito.mock(com.stackify.metric.Average.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getAverage("TestClass", "averageShortMethod")).thenReturn(average);
		
		Short s = TestClass.averageShortMethod();
	
		Assert.assertNotNull(s);
		
		PowerMockito.verifyStatic();
		MetricFactory.getAverage("TestClass", "averageShortMethod");
		
		Mockito.verify(average).addValue(s.doubleValue());
	}
	
	/**
	 * testWithAverageAnnotationInteger
	 */
	@Test
	public void testWithAverageAnnotationInteger() {
		
		com.stackify.metric.Average average = Mockito.mock(com.stackify.metric.Average.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getAverage("TestClass", "averageIntegerMethod")).thenReturn(average);
		
		Integer i = TestClass.averageIntegerMethod();
	
		Assert.assertNotNull(i);
		
		PowerMockito.verifyStatic();
		MetricFactory.getAverage("TestClass", "averageIntegerMethod");
		
		Mockito.verify(average).addValue(i.doubleValue());
	}
	
	/**
	 * testWithAverageAnnotationLong
	 */
	@Test
	public void testWithAverageAnnotationLong() {
		
		com.stackify.metric.Average average = Mockito.mock(com.stackify.metric.Average.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getAverage("TestClass", "averageLongMethod")).thenReturn(average);
		
		Long l = TestClass.averageLongMethod();
	
		Assert.assertNotNull(l);
		
		PowerMockito.verifyStatic();
		MetricFactory.getAverage("TestClass", "averageLongMethod");
		
		Mockito.verify(average).addValue(l.doubleValue());
	}
	
	/**
	 * testWithAverageAnnotationFloat
	 */
	@Test
	public void testWithAverageAnnotationFloat() {
		
		com.stackify.metric.Average average = Mockito.mock(com.stackify.metric.Average.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getAverage("TestClass", "averageFloatMethod")).thenReturn(average);
		
		Float f = TestClass.averageFloatMethod();
	
		Assert.assertNotNull(f);
		
		PowerMockito.verifyStatic();
		MetricFactory.getAverage("TestClass", "averageFloatMethod");
		
		Mockito.verify(average).addValue(f.doubleValue());
	}
	
	/**
	 * testWithAverageAnnotationDouble
	 */
	@Test
	public void testWithAverageAnnotationDouble() {
		
		com.stackify.metric.Average average = Mockito.mock(com.stackify.metric.Average.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getAverage("TestClass", "averageDoubleMethod")).thenReturn(average);
		
		Double d = TestClass.averageDoubleMethod();
	
		Assert.assertNotNull(d);
		
		PowerMockito.verifyStatic();
		MetricFactory.getAverage("TestClass", "averageDoubleMethod");
		
		Mockito.verify(average).addValue(d.doubleValue());
	}
	
	/**
	 * TestClass
	 */
	private static class TestClass {

		/**
		 * averageByteMethod
		 */
		@Average(category = "TestClass", name = "averageByteMethod")
		public static Byte averageByteMethod() {
			return Byte.valueOf((byte) 14);
		}
		
		/**
		 * averageShortMethod
		 */
		@Average(category = "TestClass", name = "averageShortMethod")
		public static Short averageShortMethod() {
			return Short.valueOf((short) 142);
		}
		
		/**
		 * averageIntegerMethod
		 */
		@Average(category = "TestClass", name = "averageIntegerMethod")
		public static Integer averageIntegerMethod() {
			return Integer.valueOf(14232);
		}
		
		/**
		 * averageLongMethod
		 */
		@Average(category = "TestClass", name = "averageLongMethod")
		public static Long averageLongMethod() {
			return Long.valueOf(142267);
		}
		
		/**
		 * averageFloatMethod
		 */
		@Average(category = "TestClass", name = "averageFloatMethod")
		public static Float averageFloatMethod() {
			return Float.valueOf((float) 123.45);
		}
		
		/**
		 * averageDoubleMethod
		 */
		@Average(category = "TestClass", name = "averageDoubleMethod")
		public static Double averageDoubleMethod() {
			return Double.valueOf((float) 123453.45);
		}
		
		/**
		 * method
		 */
		public static UUID method() {
			return UUID.randomUUID();
		}
	}
}
