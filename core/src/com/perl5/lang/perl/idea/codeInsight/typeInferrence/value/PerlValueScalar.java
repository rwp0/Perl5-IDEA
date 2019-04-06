/*
 * Copyright 2015-2018 Alexandr Evstigneev
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

package com.perl5.lang.perl.idea.codeInsight.typeInferrence.value;

import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Set;

/**
 * Represents a scalar value
 */
public final class PerlValueScalar extends PerlValue {
  @NotNull
  private final PerlValue myValue;

  public PerlValueScalar(@NotNull PerlValue value) {
    this(value, null);
  }

  public PerlValueScalar(@NotNull PerlValue value, @Nullable PerlValue bless) {
    super(bless);
    myValue = value;
  }

  public PerlValueScalar(@NotNull StubInputStream dataStream) throws IOException {
    super(dataStream);
    myValue = PerlValuesManager.deserialize(dataStream);
  }

  @Override
  protected void serializeData(@NotNull StubOutputStream dataStream) throws IOException {
    myValue.serialize(dataStream);
  }

  @Override
  protected int getSerializationId() {
    return PerlValuesManager.SCALAR_ID;
  }

  @NotNull
  @Override
  PerlValue createBlessedCopy(@NotNull PerlValue bless) {
    return new PerlValueScalar(this.myValue, bless);
  }

  @NotNull
  @Override
  protected Set<String> getNamespaceNames(@NotNull Project project,
                                          @NotNull GlobalSearchScope searchScope,
                                          @Nullable Set<PerlValue> recursion) {
    return myValue.getNamespaceNames(project, searchScope, recursion);
  }

  @NotNull
  @Override
  protected Set<String> getSubNames(@NotNull Project project,
                                    @NotNull GlobalSearchScope searchScope,
                                    @Nullable Set<PerlValue> recursion) {
    return myValue.getSubNames(project, searchScope, recursion);
  }

  @Override
  public boolean canRepresentNamespace(@Nullable String namespaceName) {
    return myValue.canRepresentNamespace(namespaceName);
  }

  @Override
  public boolean canRepresentSubName(@Nullable String subName) {
    return myValue.canRepresentSubName(subName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    PerlValueScalar scalar = (PerlValueScalar)o;

    return myValue.equals(scalar.myValue);
  }

  @Override
  protected int computeHashCode() {
    int result = super.computeHashCode();
    result = 31 * result + myValue.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Scalar: " + myValue;
  }

  @NotNull
  @Override
  public String getPresentableValueText() {
    return myValue.getPresentableText();
  }
}
