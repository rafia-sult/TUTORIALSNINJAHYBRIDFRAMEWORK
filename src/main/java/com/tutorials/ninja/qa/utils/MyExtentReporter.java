package com.tutorials.ninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyExtentReporter {

	public static ExtentReports generateExtentReport() throws Exception {
		ExtentReports extentReport = new ExtentReports();
		File extentReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");

		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TUTORIALS NINJA TEST RESULTS");
		sparkReporter.config().setDocumentTitle("Tutorials Ninja Automation Test");
		sparkReporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		Properties configProp = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorials\\ninja\\qa\\config\\config.properties");
		configProp.load(ip);

		extentReport.setSystemInfo("Application url", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Username", configProp.getProperty("validUserName"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.version"));
		extentReport.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

		return extentReport;

	}

}
