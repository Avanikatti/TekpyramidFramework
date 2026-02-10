package com.comcust.crm.configannotationsutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementatioon implements ITestListener,ISuiteListener{

	public ExtentSparkReporter spark;
	public  ExtentReports report;
	public static ExtentTest test;
	@Override
	public void onStart(ISuite suite) {
		String date=new Date().toString().replace(" ", "_").replace(":", "_");
		spark=new ExtentSparkReporter("./AdvanceReport/report"+date+".html");
		spark.config().setDocumentTitle("VTiger Tset Suite Result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "window");
		report.setSystemInfo("browser", "chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		report.flush();
		Reporter.log("report backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		test=report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
	UtilityClassObject.getTest().log(Status.INFO, "===="+result.getMethod().getMethodName()+" execution started=========");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		UtilityClassObject.getTest().log(Status.PASS, "======"+result.getMethod().getMethodName()+" Success=======");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName=result.getMethod().getMethodName();
		Date date=new Date();
		String newDate=date.toString().replace(" ", "_").replace(";", "_");
		TakesScreenshot ts=(TakesScreenshot)UtilityClassObject.getDriver();
		String srcFilePath = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(srcFilePath, testName+date);
		UtilityClassObject.getTest().log(Status.FAIL, "====="+testName+" Failure========");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		UtilityClassObject.getTest().log(Status.SKIP, "========"+result.getMethod().getMethodName()+" execution skipped========");
	}
	

}
