package org.key_project.key4eclipse.common.ui.setup;

import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.key_project.key4eclipse.common.ui.preference.page.TacletOptionsPreferencePage;
import org.key_project.util.eclipse.JobUtil;
import org.key_project.util.eclipse.setup.ISetupParticipant;

import de.uka.ilkd.key.gui.configuration.ChoiceSettings;
import de.uka.ilkd.key.gui.configuration.ProofSettings;
import de.uka.ilkd.key.symbolic_execution.util.SymbolicExecutionUtil;

/**
 * This {@link ISetupParticipant} sets the default Taclet Options
 * {@link TacletOptionsPreferencePage#getDefaultTacletOptions()}
 * on fresh workspaces.
 * @author Martin Hentschel
 */
public class TacletOptionsSetupParticipant implements ISetupParticipant {
   /**
    * {@inheritDoc}
    */
   @Override
   public void setupWorkspace() {
      // Make sure that settings are initialized
      if (!SymbolicExecutionUtil.isChoiceSettingInitialised()) {
         final String jobTitle = "Computing first time Taclet Options";
         Job job = new Job(jobTitle) {
            @Override
            protected IStatus run(IProgressMonitor monitor) {
               monitor.beginTask(jobTitle, IProgressMonitor.UNKNOWN);
               TacletOptionsPreferencePage.loadChoiceSettings();
               monitor.done();
               return Status.OK_STATUS;
            }
         };
         job.schedule();
         JobUtil.waitFor(job, 500);
      }
      // Set default choice settings
      ChoiceSettings settings = ProofSettings.DEFAULT_SETTINGS.getChoiceSettings();
      HashMap<String,String> defaults = settings.getDefaultChoices();
      defaults.putAll(TacletOptionsPreferencePage.getDefaultTacletOptions());
      settings.setDefaultChoices(defaults);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public void startup() {
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String getID() {
      return getClass().getName();
   }
}