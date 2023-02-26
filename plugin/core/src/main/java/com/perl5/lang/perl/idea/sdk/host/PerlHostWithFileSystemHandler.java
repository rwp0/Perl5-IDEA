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

package com.perl5.lang.perl.idea.sdk.host;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.perl5.lang.perl.idea.sdk.PerlHandlerBean;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class PerlHostWithFileSystemHandler<Data extends PerlHostData<Data, Handler>, Handler extends PerlHostWithFileSystemHandler<Data, Handler>>
  extends PerlHostHandler<Data, Handler> {
  public PerlHostWithFileSystemHandler(@NotNull PerlHandlerBean bean) {
    super(bean);
  }

  @Override
  public final boolean isLocal() {
    return false;
  }

  @Override
  protected void customizeFileChooser(@NotNull FileChooserDescriptor descriptor, @NotNull VirtualFileSystem fileSystem) {
    descriptor.setForcedToUseIdeaFileChooser(true);
    descriptor.setShowFileSystemRoots(false);
    descriptor.setRoots(Objects.requireNonNull(fileSystem.findFileByPath("/")));
  }
}
