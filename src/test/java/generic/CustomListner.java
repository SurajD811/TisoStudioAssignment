
package generic;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListner extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getMethod());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod());
        String methodName =result.getMethod().getMethodName();
        failed(methodName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
      String methodName =result.getMethod().getMethodName();
      failed(methodName);
    }

}
