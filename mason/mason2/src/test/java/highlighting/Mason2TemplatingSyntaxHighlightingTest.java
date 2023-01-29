/*
 * Copyright 2015-2023 Alexandr Evstigneev
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

package highlighting;

import base.Mason2TopLevelComponentTestCase;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.perl5.lang.mason2.filetypes.MasonInternalComponentFileType.INTERNAL_COMPONENT_EXTENSION;
import static com.perl5.lang.mason2.filetypes.MasonTopLevelComponentFileType.TOP_LEVEL_COMPONENT_EXTENSION;

@RunWith(Parameterized.class)
public class Mason2TemplatingSyntaxHighlightingTest extends Mason2TopLevelComponentTestCase {
  private final @NotNull String myExtension;

  public Mason2TemplatingSyntaxHighlightingTest(@NotNull String extension) {
    myExtension = extension;
  }

  @Override
  public String getFileExtension() {
    return myExtension;
  }

  @Override
  protected String getBaseDataPath() {
    return "highlighting/syntax/templates";
  }

  @Test
  public void testHeredoc() {
    doTest();
  }

  @Test
  public void testTestComponent() {
    doTest();
  }

  @Test
  public void testLiveTemplates() {
    doTest();
  }

  @Test
  public void testIssue1077() {
    doTest();
  }

  protected void doTest() {
    doTest(true);
  }

  private void doTest(boolean checkErrors) {
    doTestHighlighter(checkErrors);
  }

  @Parameterized.Parameters(name = "{0}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
      {INTERNAL_COMPONENT_EXTENSION},
      {TOP_LEVEL_COMPONENT_EXTENSION},
    });
  }
}
