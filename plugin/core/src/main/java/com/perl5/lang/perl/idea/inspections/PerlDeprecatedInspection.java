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

package com.perl5.lang.perl.idea.inspections;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.perl5.PerlBundle;
import com.perl5.lang.perl.psi.*;
import com.perl5.lang.perl.psi.utils.PerlResolveUtil;
import org.jetbrains.annotations.NotNull;

/**
 * fixme butify this
 */
public class PerlDeprecatedInspection extends PerlInspection {
  @Override
  public @NotNull PsiElementVisitor buildVisitor(final @NotNull ProblemsHolder holder, boolean isOnTheFly) {
    return new PerlVisitor() {
      @Override
      public void visitSubNameElement(@NotNull PerlSubNameElement o) {
        PsiElement container = o.getParent();

        if (!(container instanceof PerlSubElement)) {
          PerlResolveUtil.processResolveTargets((psiElement, reference) -> {
            if (psiElement instanceof PerlDeprecatable && ((PerlDeprecatable)psiElement).isDeprecated()) {
              markDeprecated(holder, o, PerlBundle.message("perl.deprecated.sub"));
              return false;
            }
            return true;
          }, o);
        }
      }

      @Override
      protected boolean shouldVisitLightElements() {
        return true;
      }

      @Override
      public void visitVariableNameElement(@NotNull PerlVariableNameElement o) {
        PsiElement parent = o.getParent();
        if (parent instanceof PerlVariable) {
          if (((PerlVariable)parent).isDeclaration()) {
            if (((PerlVariable)parent).isDeprecated()) {
              markDeprecated(holder, o, PerlBundle.message("perl.deprecated.variable"));
            }
          }

          PerlResolveUtil.processResolveTargets((psiElement, reference) -> {
            String message = null;
            if (psiElement instanceof PerlNamespaceDefinitionElement &&
                ((PerlNamespaceDefinitionElement)psiElement).isDeprecated()) {
              message = PerlBundle.message("perl.deprecated.namespace");
            }
            else if (psiElement instanceof PerlVariableDeclarationElement &&
                     ((PerlVariableDeclarationElement)psiElement)
                       .isDeprecated()) {
              message = PerlBundle.message("perl.deprecated.variable");
            }
            if (message != null) {
              holder.registerProblem(reference, message,
                                     ProblemHighlightType.LIKE_DEPRECATED);
            }
            return true;
          }, o);
        }
      }

      @Override
      public void visitNamespaceElement(@NotNull PerlNamespaceElement o) {
        if (!(o.getParent() instanceof PerlNamespaceDefinition) && o.isDeprecated()) {
          markDeprecated(holder, o, PerlBundle.message("perl.deprecated.namespace"));
        }
      }

      @Override
      public void visitPerlSubElement(@NotNull PerlSubElement o) {
        if (o.isDeprecated()) {
          PsiElement nameIdentifier = o.getNameIdentifier();
          if (nameIdentifier != null) {
            markDeprecated(holder, nameIdentifier, PerlBundle.message("perl.deprecated.sub"));
          }
        }
      }

      @Override
      public void visitPerlNamespaceDefinitionWithIdentifier(@NotNull PerlNamespaceDefinitionWithIdentifier o) {
        if (o.isDeprecated()) {
          PsiElement nameIdentifier = o.getNameIdentifier();
          if (nameIdentifier != null) {
            markDeprecated(holder, nameIdentifier, PerlBundle.message("perl.deprecated.namespace"));
          }
        }
      }
    };
  }
}