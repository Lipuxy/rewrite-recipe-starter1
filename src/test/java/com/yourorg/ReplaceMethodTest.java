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

import org.junit.jupiter.api.Test;
import org.openrewrite.DocumentExample;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

class AssertEqualsToAssertThatTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new ReplaceMethodRecipe())
          .parser(JavaParser.fromJavaVersion()
            .classpath(""));
    }

    @DocumentExample
    @Test
    void testMthodReplace() {
        rewriteRun(
          //language=java
          java(
            """
              import org.junit.jupiter.api.Assertions;
              
              class A {
                  void foo() {
                      String s1= "str1";
                      String s2= "str2";
                      String s3 = "str3";
              Hello hello = new Hello();
              String finalString = hello.printNames(s1 , s2 , s3);
                  }
              }
              """,
            """
              import org.assertj.core.api.Assertions;
              
              class A {
                  void foo() {
                       String s1= "str1";
                      String s2= "str2";
                      String s3 = "str3";
              Hello hello = new Hello();
              String finalString = Hi.instance().addNames(null, s1 , s2 , s3);
                  }
              }
              """
          )
        );
    }
}
