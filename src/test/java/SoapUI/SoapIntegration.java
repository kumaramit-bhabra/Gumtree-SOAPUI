package SoapUI;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.support.SoapUIException;
import org.apache.xmlbeans.XmlException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SoapIntegration
{
    @Test
    public void soapTest() throws IOException, SoapUIException, XmlException {
        WsdlProject project = new WsdlProject("Gumtree-soapui-project.xml");
        WsdlTestSuite testSuite = project.getTestSuiteByName("GumTree E2E");
        System.out.println("Test Case Count is "+ testSuite.getTestCaseCount());

        for(int i = 0 ; i < testSuite.getTestCaseCount(); i++)
        {
            WsdlTestCase testCase = testSuite.getTestCaseAt(i);
            TestRunner runner = testCase.run(new PropertiesMap(),false);
            Assert.assertEquals(Status.FINISHED,runner.getStatus());
        }
    }

}
