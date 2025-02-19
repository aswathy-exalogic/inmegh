package com.ski.logintest;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.ski.pageObjects.Home.homePage;
import com.ski.pageObjects.Login.loginPage;
import com.ski.testBase.DataSource;
import com.ski.testBase.TestBase;

	public class logintest extends TestBase{
			
			static loginPage login;
			homePage home;
			
			@DataProvider(name="testData")
			public Object[][] testData(){
				String[][] data = getExcelData("demo.xlsx", "login");
				return data;
			}
			
			@BeforeClass
			public void beforeClass(){
				getApplicationURL(DataSource.OR.getProperty("url"));
				login = new loginPage(driver);
			}
			
			@Test(dataProvider="testData")
			public void loginTest(String userName, String password, String runMode){
				if(runMode.equalsIgnoreCase("n")){
					throw new SkipException("Run mode for this set of data is marked N");
				}
				homePage homePage = login.loginToApplication(userName, password);
				homePage.clicklogout();
			}
	}