stackify-metrics-aspectj
========================

[![Build Status](https://travis-ci.org/stackify/stackify-metrics-aspectj.png)](https://travis-ci.org/stackify/stackify-metrics-aspectj)
[![Coverage Status](https://coveralls.io/repos/stackify/stackify-metrics-aspectj/badge.png?branch=master)](https://coveralls.io/r/stackify/stackify-metrics-aspectj?branch=master)

AspectJ AOP Extension for the Stackify Metrics API (https://github.com/stackify/stackify-metrics)

Custom Metrics Overview:

http://docs.stackify.com/m/7787/l/232533

Sign Up for a Trial:

http://www.stackify.com/sign-up/

## Usage

There are four different types of metrics: 
* Gauge: Keeps track of the last value that was set in the current minute
* Counter: Calculates the rate per minute
* Average: Calculates the average of all values in the current minute
* Timer: Calculates the average elapsed time for an operation in the current minute
* CounterAndTimer: Composite of the Counter and Timer metrics for convenience

All metrics are identified with a category and name. We will group metrics with the same category when they are displayed in Stackify. See below for more details on the operations available for each type of metric. 
```java
@Gauge(category = "MyCategory", name = "MyGauge")
...
```

Be sure to properly shutdown to flush any queued metrics and shutdown the background thread:
```java
MetricManager.shutdown();
```

#### Configuration

See https://github.com/stackify/stackify-metrics#configuration

#### Gauge Metric

```java
// Equivalent to
//     MetricFactory.getGauge("MyCategory", "MyGauge").set(v) 
// where v is the return value of the method annotated
@Gauge(category = "MyCategory", name = "MyGauge")
...
```

#### Counter Metric

```java
// Equivalent to
//     MetricFactory.getCounter("MyCategory", "MyCounter").increment()
@Counter(category = "MyCategory", name = "MyCounter")
...
```

#### Average Metric

```java
// Equivalent to
//     MetricFactory.getAverage("MyCategory", "MyAverage").addValue(v)
// where v is the return value of the method annotated
@Average(category = "MyCategory", name = "MyAverage")
...
```

#### Timer Metric

```java
// Equivalent to
//     MetricFactory.getTimer("MyCategory", "MyTimer").startMs(v)
// where v is the start time of the method annotated
@Timer(category = "MyCategory", name = "MyTimer")
...
```

#### CounterAndTimer Metric

```java
// Equivalent to
//     MetricFactory.getCounterAndTimer("MyCategory", "MyCounterAndTimer").startMs(v)
// where v is the start time of the method annotated
@CounterAndTimer(category = "MyCategory", name = "MyCounterAndTimer")
...
```

## Installation

Add it as a maven dependency:
```xml
<dependency>
    <groupId>com.stackify</groupId>
    <artifactId>stackify-metrics-aspectj</artifactId>
    <version>1.0.2</version>
</dependency>
```

Configure the aspectj-maven-plugin to compile-time weave the stackify-metrics-aspectj aspects into your project:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>aspectj-maven-plugin</artifactId>
            <configuration>
                <aspectLibraries>
                    <aspectLibrary>
                        <groupId>com.stackify</groupId>
                        <artifactId>stackify-metrics-aspectj</artifactId>
                    </aspectLibrary>
                </aspectLibraries>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>compile</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## License

Copyright 2014 Stackify, LLC.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
