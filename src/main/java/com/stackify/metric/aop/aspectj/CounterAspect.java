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

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.stackify.metric.MetricFactory;

/**
 * CounterAspect
 * @author Eric Martin
 */
@Aspect
public class CounterAspect {
	
	/**
	 * Before advice for a Counter metric
	 * @param ca Counter annotation
	 */
	@Before("execution(* *(..)) && @annotation(ca)")
	public void before(final com.stackify.metric.aop.Counter ca) {
		MetricFactory.getCounter(ca.category(), ca.name()).increment();	
	}
}
