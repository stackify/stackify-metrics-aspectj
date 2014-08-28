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
 * AverageAspect
 * @author Eric Martin
 */
@Aspect
public class AverageAspect {
	
	/**
	 * After-Returning advice for a Average metric
	 * @param aa Average annotation
	 * @param result Byte result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Byte *(..)) && @annotation(aa)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Average aa, final Byte result) {
		MetricFactory.getAverage(aa.category(), aa.name()).addValue(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Average metric
	 * @param aa Average annotation
	 * @param result Short result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Short *(..)) && @annotation(aa)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Average aa, final Short result) {
		MetricFactory.getAverage(aa.category(), aa.name()).addValue(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Average metric
	 * @param aa Average annotation
	 * @param result Integer result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Integer *(..)) && @annotation(aa)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Average aa, final Integer result) {
		MetricFactory.getAverage(aa.category(), aa.name()).addValue(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Average metric
	 * @param aa Average annotation
	 * @param result Long result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Long *(..)) && @annotation(aa)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Average aa, final Long result) {
		MetricFactory.getAverage(aa.category(), aa.name()).addValue(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Average metric
	 * @param aa Average annotation
	 * @param result Float result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Float *(..)) && @annotation(aa)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Average aa, final Float result) {
		MetricFactory.getAverage(aa.category(), aa.name()).addValue(result.doubleValue());
	}
	
	/**
	 * After-Returning advice for a Average metric
	 * @param aa Average annotation
	 * @param result Double result
	 */
	@AfterReturning(pointcut = "execution(java.lang.Double *(..)) && @annotation(aa)", returning = "result")
	public void afterReturning(final com.stackify.metric.aop.Average aa, final Double result) {
		MetricFactory.getAverage(aa.category(), aa.name()).addValue(result.doubleValue());
	}
}
