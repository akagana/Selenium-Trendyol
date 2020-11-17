package selenium;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Elements {
	public static WebDriver driver;
	public String page_price;
	public String card_price;
	public Elements(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement gender_popup() {
		return waitfor("/html/body/div[8]/div/div/a");	
	}
	
	public WebElement btn_loginpage() {
		return waitfor("//*[@id=\"accountBtn\"]");
	}
	
	public WebElement input_username() {
		return waitfor("//*[@id=\"login-email\"]");
	}
	
	public WebElement input_password () {
		return waitfor("//*[@id=\"login-password-input\"]");
	}
	
	public WebElement btn_login () {
		return waitfor("//*[@id=\"login-register\"]/div[3]/div[1]/form/button");
	}
	
	public WebElement notification_popup () {
		return driver.findElement(By.xpath("\"//*[@id=\\\"modal-root\\\"]/div\""));
	}
	
	public WebElement input_search() {
		return waitfor("//*[@id=\"auto-complete-app\"]/div/div/input");
	}
	
	public WebElement select_random_computer() throws InterruptedException {
		List<WebElement> forms = driver.findElements(By.className("p-card-chldrn-cntnr"));
		Random rand = new Random();
		int index = rand.nextInt(forms.size());
		page_price = forms.get(index).findElement(By.className("prc-box-sllng")).getText().trim();
		return forms.get(index);
		
	}
	
	public void set_card_price() {
		String[] prices = driver.findElement(By.className("pb-basket-item-price")).getText().split("TL");
		try {
			card_price = prices[1].trim();
			
		}catch (Exception e) {
			card_price = prices[0];

		}
		
	}
	public boolean check_price() {
		if(page_price.contains(card_price)) {
			System.out.println("Fiyatlar esit ürün sayfasindaki fiyat: "+page_price + " sepetteki fiyat: "+card_price + " TL");
			return true;
		}else {
			System.out.println("Fiyatlar esit degil");
			return false;
		}
	}
	public WebElement add_to_card() {
		
		try {
			return waitfor("//*[@id=\"product-detail-app\"]/div/div[2]/div[2]/div[2]/div[3]/button[1]");
		}catch (Exception e) {
			try {
				return waitfor("//*[@id=\"product-detail-app\"]/div/div[2]/div[2]/div[2]/div[1]/button[1]");
			}catch (Exception b) {
				try {
					return waitfor("//*[@id=\"product-detail-app\"]/div/div[2]/div[2]/div[2]/div[5]/button[1]");
				}catch (Exception c) {
					return null;
				}
			}
		}
		
		
	}
	
	public WebElement btn_card() {
		return waitfor("//*[@id=\"myBasketListItem\"]");
	}
	public WebElement btn_plus() {
		
		return waitfor("//*[@id=\"partial-basket\"]/div/div[2]/div[2]/div[3]/div[1]/div/button[2]");
	}
	public WebElement btn_remove1() {
		return waitfor("//*[@id=\"partial-basket\"]/div/div[2]/div[2]/div[3]/button");
	}
	public WebElement btn_remove2() {
		return waitfor("//*[@id=\"ngdialog1\"]/div[2]/form/div/div[2]/div/div[1]/button[2]");
	}
	
	public WebElement card_status() {
		return waitfor("//*[@id=\"basketNoProductPage\"]/div[2]/div/div[1]/p/span");
	}
	public boolean check_card_status() {
		if(card_status().getText().contains("bulunmamakta")) {
			return true;
		}else {
			return false;
		}
	}
	public static WebElement waitfor(String path) {
		WebDriverWait wait = new WebDriverWait((WebDriver) driver, 10);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
	}

	
}