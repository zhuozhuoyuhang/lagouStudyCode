/**
 *    Copyright 2009-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.logging.jdbc;

import org.apache.ibatis.logging.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Array;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BaseJdbcLoggerTest {

  @Mock
  Log log;
  @Mock
  Array array;
  BaseJdbcLogger logger;

  @Before
  public void setUp() throws Exception {
    logger = new BaseJdbcLogger(log, 1) {
    };
  }

  @Test
  public void shouldDescribePrimitiveArrayParameter() throws Exception {
    logger.setColumn("1", array);
    when(array.getArray()).thenReturn(new int[] { 1, 2, 3 });
    assertThat(logger.getParameterValueString()).startsWith("[1, 2, 3]");
  }

  @Test
  public void shouldDescribeObjectArrayParameter() throws Exception {
    logger.setColumn("1", array);
    when(array.getArray()).thenReturn(new String[] { "one", "two", "three" });
    assertThat(logger.getParameterValueString()).startsWith("[one, two, three]");
  }
}