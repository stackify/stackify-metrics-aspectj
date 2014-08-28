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

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import com.stackify.metric.MetricFactory;

/**
 * GaugeAspect
 * @author Eric Martin
 */
@Aspect
public class GaugeAspect {
	
	/**
	 * After-Returning advice for a Gauge metric
	 * @param ga Gauge annotation
	 * @param result Byte result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Byte *(..)) && @annotation(ga)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Gauge ga, final Byte result) {
		MetricFactory.getGauge(ga.category(), ga.name()).set(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Gauge metric
	 * @param ga Gauge annotation
	 * @param result Short result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Short *(..)) && @annotation(ga)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Gauge ga, final Short result) {
		MetricFactory.getGauge(ga.category(), ga.name()).set(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Gauge metric
	 * @param ga Gauge annotation
	 * @param result Integer result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Integer *(..)) && @annotation(ga)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Gauge ga, final Integer result) {
		MetricFactory.getGauge(ga.category(), ga.name()).set(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Gauge metric
	 * @param ga Gauge annotation
	 * @param result Long result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Long *(..)) && @annotation(ga)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Gauge ga, final Long result) {
		MetricFactory.getGauge(ga.category(), ga.name()).set(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Gauge metric
	 * @param ga Gauge annotation
	 * @param result Float result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Float *(..)) && @annotation(ga)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Gauge ga, final Float result) {
		MetricFactory.getGauge(ga.category(), ga.name()).set(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Gauge metric
	 * @param ga Gauge annotation
	 * @param result Double result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Double *(..)) && @annotation(ga)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Gauge ga, final Double result) {
		MetricFactory.getGauge(ga.category(), ga.name()).set(result.doubleValue());
	}
}
