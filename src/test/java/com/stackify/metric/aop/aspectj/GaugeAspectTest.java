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
import com.stackify.metric.aop.Gauge;

/**
 * GaugeAspect JUnit Test
 * @author Eric Martin
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GaugeAspect.class, MetricFactory.class})
public class GaugeAspectTest {

	/**
	 * testWithoutGaugeAnnotation
	 */
	@Test
	public void testWithoutGaugeAnnotation() {
		
		PowerMockito.mockStatic(MetricFactory.class);
		
		UUID uuid = TestClass.method();
		
		Assert.assertNotNull(uuid);
		
		PowerMockito.verifyStatic(Mockito.times(0));
		MetricFactory.getGauge(Mockito.anyString(), Mockito.anyString());
	}
	
	/**
	 * testWithGaugeAnnotationByte
	 */
	@Test
	public void testWithGaugeAnnotationByte() {
		
		com.stackify.metric.Gauge gauge = Mockito.mock(com.stackify.metric.Gauge.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getGauge("TestClass", "gaugeByteMethod")).thenReturn(gauge);
		
		Byte b = TestClass.gaugeByteMethod();
	
		Assert.assertNotNull(b);
		
		PowerMockito.verifyStatic();
		MetricFactory.getGauge("TestClass", "gaugeByteMethod");
		
		Mockito.verify(gauge).set(b.doubleValue());
	}
	
	/**
	 * testWithGaugeAnnotationShort
	 */
	@Test
	public void testWithGaugeAnnotationShort() {
		
		com.stackify.metric.Gauge gauge = Mockito.mock(com.stackify.metric.Gauge.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getGauge("TestClass", "gaugeShortMethod")).thenReturn(gauge);
		
		Short s = TestClass.gaugeShortMethod();
	
		Assert.assertNotNull(s);
		
		PowerMockito.verifyStatic();
		MetricFactory.getGauge("TestClass", "gaugeShortMethod");
		
		Mockito.verify(gauge).set(s.doubleValue());
	}
	
	/**
	 * testWithGaugeAnnotationInteger
	 */
	@Test
	public void testWithGaugeAnnotationInteger() {
		
		com.stackify.metric.Gauge gauge = Mockito.mock(com.stackify.metric.Gauge.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getGauge("TestClass", "gaugeIntegerMethod")).thenReturn(gauge);
		
		Integer i = TestClass.gaugeIntegerMethod();
	
		Assert.assertNotNull(i);
		
		PowerMockito.verifyStatic();
		MetricFactory.getGauge("TestClass", "gaugeIntegerMethod");
		
		Mockito.verify(gauge).set(i.doubleValue());
	}
	
	/**
	 * testWithGaugeAnnotationLong
	 */
	@Test
	public void testWithGaugeAnnotationLong() {
		
		com.stackify.metric.Gauge gauge = Mockito.mock(com.stackify.metric.Gauge.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getGauge("TestClass", "gaugeLongMethod")).thenReturn(gauge);
		
		Long l = TestClass.gaugeLongMethod();
	
		Assert.assertNotNull(l);
		
		PowerMockito.verifyStatic();
		MetricFactory.getGauge("TestClass", "gaugeLongMethod");
		
		Mockito.verify(gauge).set(l.doubleValue());
	}
	
	/**
	 * testWithGaugeAnnotationFloat
	 */
	@Test
	public void testWithGaugeAnnotationFloat() {
		
		com.stackify.metric.Gauge gauge = Mockito.mock(com.stackify.metric.Gauge.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getGauge("TestClass", "gaugeFloatMethod")).thenReturn(gauge);
		
		Float f = TestClass.gaugeFloatMethod();
	
		Assert.assertNotNull(f);
		
		PowerMockito.verifyStatic();
		MetricFactory.getGauge("TestClass", "gaugeFloatMethod");
		
		Mockito.verify(gauge).set(f.doubleValue());
	}
	
	/**
	 * testWithGaugeAnnotationDouble
	 */
	@Test
	public void testWithGaugeAnnotationDouble() {
		
		com.stackify.metric.Gauge gauge = Mockito.mock(com.stackify.metric.Gauge.class);

		PowerMockito.mockStatic(MetricFactory.class);
		PowerMockito.when(MetricFactory.getGauge("TestClass", "gaugeDoubleMethod")).thenReturn(gauge);
		
		Double d = TestClass.gaugeDoubleMethod();
	
		Assert.assertNotNull(d);
		
		PowerMockito.verifyStatic();
		MetricFactory.getGauge("TestClass", "gaugeDoubleMethod");
		
		Mockito.verify(gauge).set(d.doubleValue());
	}
	
	/**
	 * TestClass
	 */
	private static class TestClass {

		/**
		 * gaugeByteMethod
		 */
		@Gauge(category = "TestClass", name = "gaugeByteMethod")
		public static Byte gaugeByteMethod() {
			return Byte.valueOf((byte) 14);
		}
		
		/**
		 * gaugeShortMethod
		 */
		@Gauge(category = "TestClass", name = "gaugeShortMethod")
		public static Short gaugeShortMethod() {
			return Short.valueOf((short) 142);
		}
		
		/**
		 * gaugeIntegerMethod
		 */
		@Gauge(category = "TestClass", name = "gaugeIntegerMethod")
		public static Integer gaugeIntegerMethod() {
			return Integer.valueOf(14232);
		}
		
		/**
		 * gaugeLongMethod
		 */
		@Gauge(category = "TestClass", name = "gaugeLongMethod")
		public static Long gaugeLongMethod() {
			return Long.valueOf(142267);
		}
		
		/**
		 * gaugeFloatMethod
		 */
		@Gauge(category = "TestClass", name = "gaugeFloatMethod")
		public static Float gaugeFloatMethod() {
			return Float.valueOf((float) 123.45);
		}
		
		/**
		 * gaugeDoubleMethod
		 */
		@Gauge(category = "TestClass", name = "gaugeDoubleMethod")
		public static Double gaugeDoubleMethod() {
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
