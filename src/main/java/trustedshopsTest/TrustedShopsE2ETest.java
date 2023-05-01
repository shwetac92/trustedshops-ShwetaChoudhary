package trustedshopsTest;
import org.testng.annotations.Test;

import trustedshopsPages.TrustedShopsE2ETestPage;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;

public class TrustedShopsE2ETest {

	private WebDriver driver;
	TrustedShopsE2ETestPage newTrustedShopsPage;
	
	@BeforeTest
			public void setUp() {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				
				driver = new ChromeDriver(options);
				
				
				//Maximize current window
				driver.manage().window().maximize();
				//Navigate to TrustedShops review URL
				driver.get("https://www.trustedshops.de/bewertung/info_X77B11C1B8A5ABA16DDEC0C30E7996C21.html");
			}
				
			//TC01 : Check if page title exists
			//Owner : Shweta Choudhary
			//Date : 28-04-2023
			@Test (priority=1)
			public void pageTitleForJalousiescoutReviewPage () {
				newTrustedShopsPage = new TrustedShopsE2ETestPage(driver);
				
				//Load the Review Page Link and Assert the page title
				String registrationPageTitle = newTrustedShopsPage.getReviewPageTitle();		
				System.out.println("Page Title : " + registrationPageTitle);
				Assert.assertTrue(registrationPageTitle.contains("Bewertungen zu Jalousiescout.de"));
			} 
				
			//TC02 : check Grade >0 For Jalousiescout Review Page
			//Owner : Shweta Choudhary
			//Date : 28-04-2023
			@Test (priority=2)
			public void  checkGradeTest() {
				newTrustedShopsPage = new TrustedShopsE2ETestPage(driver);
				
				//Assert the Grade > 0 for Review Page of Jalousiescout.de
				String grade = newTrustedShopsPage.getGrade().replaceAll(",", ".");
				System.out.println("Grade : " + grade);
				double gradeNumber = Double.parseDouble(grade);
				
				System.out.println("Grade in Numbers : " + gradeNumber);
				
				//Assert successful registration message
				Assert.assertTrue(gradeNumber>0);
					
			}
			
			
			
			//TC03 : check “Wie berechnet sich die Note?” Page For Jalousiescout.de Review Page
			//Owner : Shweta Choudhary
			//Date : 28-04-2023
			@Test (priority=3)
			public void  checkNotePageTest() {
				newTrustedShopsPage = new TrustedShopsE2ETestPage(driver);
				//Click on “Wie berechnet sich die Note?” Link and get a portion of text to verify the relevant information
				newTrustedShopsPage.clickNote();
				String noteInfo = newTrustedShopsPage.getNotePageText();
				System.out.println("Note Data : " + noteInfo);
				
				Assert.assertSame("Notenberechnung auf Basis der Sternevergabe", noteInfo, "Information irrelevant");
				newTrustedShopsPage.closeNote();
			}
			
			//TC04 : check 2 star reviews For Jalousiescout.de Review Page
			//Owner : Shweta Choudhary
			//Date : 28-04-2023
			@Test (priority=5)
			public void  check2StarReviewsTest() throws InterruptedException {
				newTrustedShopsPage = new TrustedShopsE2ETestPage(driver);
				//Click on 2 star reviews link and verify all reviews have 2 star reviews
				newTrustedShopsPage.clickOn2StarReviews();
				newTrustedShopsPage.verifyListOfAll2StarReviews();
			}
			
	
			//TC05 : check Total reviews perecntage For Jalousiescout.de Review Page
			//Owner : Shweta Choudhary
			//Date : 28-04-2023
			@Test (priority=4)
			public void  checkTotalReviewsPercentageTest() throws InterruptedException {
				newTrustedShopsPage = new TrustedShopsE2ETestPage(driver);
				
				//Click on 2 star reviews link and verify all reviews have 2 star reviews
				Integer total = newTrustedShopsPage.getAllRatingPercentage();
				Assert.assertTrue(total<=100);
				//newTrustedShopsPage.verifyListOfAll2StarReviews();
			}

	@AfterClass
	public void closeTest() {
		driver.close();
	}
}
