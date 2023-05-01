package trustedshopsPages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.google.common.base.CharMatcher;


public class TrustedShopsE2ETestPage {
	
		WebDriver driver;
		
		String classText = "";
			
		@FindBy(xpath="//title")
		WebElement reviewPageTitle;
			
		@FindBy(xpath="//div[@class='sc-62479231-12 ggAHVu']")
		WebElement getGrade;
		
		@FindBy(linkText="Wie berechnet sich die Note?")
		WebElement noteLink;
		
		@FindBy(xpath="//pre[text()='Notenberechnung auf Basis der Sternevergabe']")
		WebElement noteInfo;
		
		@FindBy(xpath="//span[@class='Iconstyles__Icon-sc-hltmf-0 gPhzdv Modalstyles__CloseIcon-sc-10yc67r-3 ijctcv hls hls-icon-action-dismiss']")
		WebElement closeButton;
		
		@FindBy(xpath="//div[@class='sc-61f2e426-7 dzyapM' and text()='2']")
		WebElement twoStarReviews;
		
		
		
		public TrustedShopsE2ETestPage(WebDriver driver) {
			this.driver = driver;
			
			//This initElements method will create all WebElements
			PageFactory.initElements(driver, this);
		}

		public String getReviewPageTitle() {
			return reviewPageTitle.getAttribute("innerText");
		}
		
		public void clickNote() {
			noteLink.click();
			//return reviewPageTitle.getAttribute("innerText");
		}
		
		public String getNotePageText() {
			return noteInfo.getAttribute("innerText");
		}		
		
		public String getGrade() {
			return getGrade.getAttribute("innerText");
		}
		
		public void closeNote() {
			closeButton.click();
		}
		
		public void clickOn2StarReviews() {
			twoStarReviews.click();
//			WebDriverWait wait = new WebDriverWait(driver, null);
//			List<WebElement> reviewsList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("//div[@class='sc-ccb4adb3-0 cInOEl']")));
		}

		public void verifyListOfAll2StarReviews(){
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				List<WebElement> reviewsList = driver.findElements(By.xpath("//div[@class='sc-ccb4adb3-0 cInOEl']/div[@class='sc-2e7612c5-0 sc-f836bc46-0 kyZgbN chcERM']"));
				System.out.println("Total 2 star reviews = " + reviewsList.size());
				Integer starCount=0;
				
				for(WebElement review : reviewsList) {
					System.out.println("Class = " + review.getAttribute("class"));
						List<WebElement> starList = review.findElements(By.xpath(("./div/div/div/span[@class='Iconstyles__Icon-sc-hltmf-0 goGliK  hls hls-star-filled']")));
						System.out.println("total stars in this review = " + starList.size());
						for(WebElement stars : starList) {
							String starStyle = stars.getAttribute("style");
							System.out.println("Style of stars = " + starStyle);
							if(starStyle.contains("rgb(255, 220, 15)")) {
								starCount++;
							}							
						}
						Assert.assertTrue(starCount<=2);
				}
		}
		
		
		
		public Integer getAllRatingPercentage() {
			List<WebElement> reviewsList = driver.findElements(By.xpath("//div[@class='sc-61f2e426-8 iMXstX']"));
			Integer percentValue = 0;
			Integer totalSumOfReviews = 0;
			for(WebElement review : reviewsList) {
				String percentReview = review.findElement(By.xpath("./span[1]")).getAttribute("innerText");
				String theNumbers = CharMatcher.inRange('0', '9').retainFrom(percentReview); 
				
				percentValue = Integer.parseInt(theNumbers);
				totalSumOfReviews = totalSumOfReviews+percentValue;
				System.out.println("Percentage of reviews = " + percentValue);
				
			}
			System.out.println("Total Percentage of reviews = " + totalSumOfReviews);
			return totalSumOfReviews;
		}
		
}
	
 
