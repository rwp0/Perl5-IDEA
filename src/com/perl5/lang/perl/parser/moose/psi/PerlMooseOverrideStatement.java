/*
 * Copyright 2015 Alexandr Evstigneev
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

package com.perl5.lang.perl.parser.moose.psi;

import com.perl5.lang.perl.extensions.parser.PerlReferencesProvider;
import com.perl5.lang.perl.psi.PerlSubDefinitionBase;
import com.perl5.lang.perl.psi.stubs.subsdefinitions.PerlSubDefinitionStub;

/**
 * Created by hurricup on 25.11.2015.
 */
public interface PerlMooseOverrideStatement extends PerlSubDefinitionBase<PerlSubDefinitionStub>, PerlReferencesProvider
{
}
