package org.key_project.sed.key.evaluation.model.definition;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.key_project.util.java.CollectionUtil;

public class SectionQuestion extends AbstractQuestion {
   private final List<AbstractQuestion> childQuestions;
   
   private final boolean grapVerticalSpace;

   public SectionQuestion(String name, String label, boolean grapVerticalSpace, AbstractQuestion... childQuestions) {
      this(name, label, grapVerticalSpace, CollectionUtil.toList(childQuestions));
   }

   public SectionQuestion(String name, String label, boolean grapVerticalSpace, List<AbstractQuestion> childQuestions) {
      super(name, label, null, null, false);
      this.childQuestions = childQuestions;
      this.grapVerticalSpace = grapVerticalSpace;
      validateChildren();
   }
   
   protected void validateChildren() {
      // Ensure that all children have different names
      Set<String> usedNames = new HashSet<String>();
      if (childQuestions != null) {
         for (AbstractQuestion childQuestion : childQuestions) {
            if (!usedNames.add(childQuestion.getName())) {
               throw new IllegalStateException("Chlild question name '" + childQuestion.getName() + "' used multiple times.");
            }
         }
      }
   }

   public boolean isGrapVerticalSpace() {
      return grapVerticalSpace;
   }

   public AbstractQuestion[] getChildQuestions() {
      return childQuestions.toArray(new AbstractQuestion[childQuestions.size()]);
   }
   
   public int countChildQuestions() {
      return childQuestions.size();
   }

   @Override
   public boolean isEditable() {
      return false;
   }
}