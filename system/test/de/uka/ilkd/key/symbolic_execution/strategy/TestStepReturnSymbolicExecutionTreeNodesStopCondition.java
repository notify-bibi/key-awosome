package de.uka.ilkd.key.symbolic_execution.strategy;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.uka.ilkd.key.proof.init.ProofInputException;
import de.uka.ilkd.key.symbolic_execution.AbstractSymbolicExecutionTestCase;

/**
 * Tests for {@link StepReturnSymbolicExecutionTreeNodesStopCondition} and
 * {@link SymbolicExecutionGoalChooser}. To do a step over the
 * {@link CompoundStopCondition} is also tested.
 * @author Martin Hentschel
 */
public class TestStepReturnSymbolicExecutionTreeNodesStopCondition extends AbstractSymbolicExecutionTestCase {
   /**
    * Does some step return tests on one branch.
    */
   public void testStepReturn() throws ProofInputException, IOException, ParserConfigurationException, SAXException {
      // Define test settings
      String javaPathInkeyRepDirectory = "examples/_testcase/set/stepReturnTest/test/StepReturnTest.java";
      String containerTypeName = "StepReturnTest";
      final String methodFullName = "main";
      String oraclePathInkeyRepDirectoryFile = "examples/_testcase/set/stepReturnTest/oracle/StepReturnTest";
      String oracleFileExtension = ".xml";
      // Create proof environment for symbolic execution
      SymbolicExecutionEnvironment env = createSymbolicExecutionEnvironment(keyRepDirectory, javaPathInkeyRepDirectory, containerTypeName, methodFullName);
      // Make sure that initial tree is valid
      int oracleIndex = 0;
      assertSetTreeAfterStep(env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory);
      // Do steps
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // call main
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // first level
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // call first level
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // second level
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // call second level
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // third level
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // call third level
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // fourth level
      stepInto(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // call fourth level
      stepReturn(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // a = a * 2
      stepReturn(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // second level
      stepReturn(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // first level
      stepReturn(env.getUi(), env.getBuilder(), oraclePathInkeyRepDirectoryFile, ++oracleIndex, oracleFileExtension, keyRepDirectory); // end
   }
}