package org.key_project.sed.key.core.test.testcase.swtbot;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Test;
import org.key_project.sed.core.model.ISEDDebugTarget;
import org.key_project.sed.core.test.util.TestSedCoreUtil;
import org.key_project.sed.key.core.test.util.TestBreakpointsUtil;

public class SWTBotRemoveLineBreakpoint extends AbstractKeYDebugTargetTestCase {
   
   private static final String CALLER_PATH= ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toString() + "/SWTBotRemoveLineBreakpoint_test/src/BreakpointStopCallerAndLoop.java";
   
   @Test
   public void test() throws Exception{
      IKeYDebugTargetTestExecutor executor = new IKeYDebugTargetTestExecutor() {
         @Override
         public void test(SWTWorkbenchBot bot, IJavaProject project, IMethod method, String targetName, SWTBotView debugView, SWTBotTree debugTree, ISEDDebugTarget target, ILaunch launch) throws Exception {            
            // Get debug target TreeItem
            TestBreakpointsUtil.addSomeBreakpoints(CALLER_PATH, bot, 15, 14, 5, 16, "NullPointerException");
            SWTBotTreeItem item = TestSedCoreUtil.selectInDebugTree(debugTree, 0, 0, 0);
            resume(bot, item, target);
            //allBreakpoints there
            assertTrue(TestBreakpointsUtil.checkTargetContainsSomeBreakpoints(target, 2, 1, 1, 1));
            assertTrue(TestBreakpointsUtil.checkProofContainsSomeBreakpoints(target,  2, 1, 1, 1));
            assertTrue(TestBreakpointsUtil.removeBrakpoint(bot, "BreakpointStopCallerAndLoop [line: 16] - main(int)"));
            assertTrue(TestBreakpointsUtil.checkTargetContainsSomeBreakpoints(target, 1, 1, 1, 1));
            assertTrue(TestBreakpointsUtil.checkProofContainsSomeBreakpoints(target,  1, 1, 1, 1));
            TestBreakpointsUtil.removeAllBreakpoints();
               
         }
      };
      doKeYDebugTargetTest("SWTBotRemoveLineBreakpoint_test",
            "data/BreakpointTest/test",
            true,
            true,
            createMethodSelector("BreakpointStopCallerAndLoop", "main"),
            null,
            null,
            Boolean.FALSE,
            Boolean.FALSE,
            Boolean.FALSE,
            Boolean.FALSE,
            Boolean.FALSE,
            8,
            executor);   
   } 
}