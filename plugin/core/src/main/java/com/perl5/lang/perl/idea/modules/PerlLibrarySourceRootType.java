/*
 * Copyright 2015-2020 Alexandr Evstigneev
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

package com.perl5.lang.perl.idea.modules;

import org.jetbrains.annotations.NotNull;


public class PerlLibrarySourceRootType extends PerlSourceRootType {
  public static final PerlLibrarySourceRootType INSTANCE = new PerlLibrarySourceRootType();

  @Override
  protected PerlSourceRootType getRootType() {
    return INSTANCE;
  }

  @Override
  public @NotNull String getSerializationKey() {
    return "perl-library";
  }
}
