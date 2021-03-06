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

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.stackify.metric.MetricFactory;

/**
 * TimerAspect
 * @author Eric Martin
 */
@Aspect
public class TimerAspect {
	
	/**
	 * Around advice for a Timer metric
	 * @param ta Timer annotation
	 * @return The underlying method's return
	 * @throws Throwable 
	 */
	@Around("execution(* *(..)) && @annotation(ta)")
	public Object around(final ProceedingJoinPoint joinPoint, final com.stackify.metric.aop.Timer ta) throws Throwable {
				
		long start = System.currentTimeMillis();
		
		try {
			return joinPoint.proceed();
		} finally {
			MetricFactory.getTimer(ta.category(), ta.name()).startMs(start);
		}
	}
}
