/*
 * Copyright 2024 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yourorg;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.openrewrite.ExecutionContext;
import org.openrewrite.Preconditions;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaParser;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.MethodMatcher;
import org.openrewrite.java.search.UsesType;
import org.openrewrite.java.tree.Expression;
import org.openrewrite.java.tree.J;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = false)
public class ReplaceMethodRecipe extends Recipe {
    @Override
    public String getDisplayName() {
        // language=markdown
        return "Replace Method";
    }

    @Override
    public String getDescription() {
        return "Replace Method.";
    }

    private final JavaTemplate replaceTemplate = JavaTemplate.builder("Hi.instance().addNames(#{any(java.lang.String)}, #{any(java.lang.String)} , #{any(java.lang.String)} , #{any(java.lang.String)})").contextSensitive().build();

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {return new ReplaceMethodVisitor();}
        
                private class ReplaceMethodVisitor extends JavaIsoVisitor<ExecutionContext> {
                    @Override
                    public J.MethodInvocation visitMethodInvocation(J.MethodInvocation method, ExecutionContext ctx) {
                        J.MethodInvocation m = super.visitMethodInvocation(method, ctx);
                        if (method.getSimpleName().equals("printNames") {
                            maybeAddImport("com.youorg.Hi");
                            return replaceTemplate.apply(getCursor(),method.getCoordinates.replace(),method.getArguments(0),method.getArguments(0), method.getArguments(1),method.getArguments(2));
                            //first argumnet should always be null
                            //adding this code in line 59 with and without arguments give ArrayIndexOutOfBoundsException
                          
                        }
                        return m;
                    
                }
    }
}
