/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package harry.model.sut;

import java.util.concurrent.CompletableFuture;

import harry.operations.CompiledStatement;

public interface SystemUnderTest
{
    public interface SUTFactory
    {
        public SystemUnderTest make();
    }

    public boolean isShutdown();

    public void shutdown();

    default void schemaChange(String statement)
    {
        execute(statement, new Object[]{});
    }

    default Object[][] execute(CompiledStatement statement)
    {
        return execute(statement.cql(), statement.bindings());
    }

    Object[][] execute(String statement, Object... bindings);

    CompletableFuture<Object[][]> executeAsync(String statement, Object... bindings);

    interface SystemUnderTestFactory
    {
        SystemUnderTest create();
    }
}