package selenium;

import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class main {
	public static WebDriver driver;
	public static String username = "kagan.akansel@gmail.com";
	public static String password = "Trendyol1";
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		//tarayıcı driver yolu eklenir
		Locale.setDefault(new Locale("en","EN"));
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//site açılır
		driver.get("https://www.trendyol.com");
		
		Elements element_page = new Elements(driver);
		//sitenin açıldığı kontrol edilir.
		if(driver.getCurrentUrl().equals("https://www.trendyol.com/")) {
			System.out.println("Siteye giris yapildi");
			//saniye cinsinden bekleme komutu
			Thread.sleep(3000);
			try {
				//cinsiyet seçimi popupı kapatma komutu.
				element_page.gender_popup().click();	
			}catch (Exception e) {
				
			}
			//giriş sayfasına yönelme
			element_page.btn_loginpage().click();
			//email girisi
			element_page.input_username().sendKeys(username);
			//password girisi
			element_page.input_password().sendKeys(password);
			//giris tusu
			element_page.btn_login().click();
			Thread.sleep(5000);
			if(driver.findElement(By.xpath("//*[@id=\"logged-in-container\"]/span")).getText().contains("Hesa")) {
				System.out.println("Hesaba giris yapildi");
				Thread.sleep(2000);
		
				try {
					//bildirim popupı kapatılır
					element_page.notification_popup().click();
				}catch (Exception e) {
					
				}
				//bilgisayar sözcüğü aratılır.
				element_page.input_search().sendKeys("bilgisayar",Keys.ENTER);
				//açılan sayfadan rastgele bir bilgisayar seçilir.
				element_page.select_random_computer().click();
				Thread.sleep(3000);
				//sepete eklenir.
				element_page.add_to_card().click();
				//sepete gidilir.
				element_page.btn_card().click();
				Thread.sleep(2000);
				//sepetteki ücreti kaydedilir.
				element_page.set_card_price();
				//sayfadaki ve sepetteki ücret kontrol edilir.
				element_page.check_price();
				try {
					//ürün sayısı 2ye çıkartılır kontrol edilmez çünkü her üründe ikiye çıkartma işlemi geçerli olmuyor.
					element_page.btn_plus().click();	
				}catch (Exception e) {
					System.out.println("Son bir adet ürün kalmis.");
				}
				
				Thread.sleep(1000);
				//Sepetten kaldırma işlemi
				element_page.btn_remove1().click();
				Thread.sleep(1500);
				element_page.btn_remove2().click();
				Thread.sleep(1000);
				//Sepet doluluk kontrolü
				if(element_page.check_card_status()) {
					System.out.println("Sepet bosaltildi");
				}
				Thread.sleep(2500);
				//Tarayıcı kapatılır.
				driver.close();
			}
		}
	}
}
