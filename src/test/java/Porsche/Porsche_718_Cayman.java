package Porsche;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Porsche_718_Cayman {
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.porsche.com/usa/modelstart/");

		driver.findElement(By.className("b-teaser-preview-wrapper")).click();

		String priceModelRangePage = driver.findElement(By.className("m-14-model-price")).getText();
		System.out.println(priceModelRangePage);

		double priceDoubleRangePage = (Integer.parseInt(priceModelRangePage.replaceAll("\\D", ""))) / 100;// String
																											// regex
		System.out.println(priceDoubleRangePage);

		driver.findElement(By.className("m-14-quick-link")).click();

		// Switch the window
		String winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		// waits until page loaded
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// find the base price
		// String basePriceBuildPage =
		// driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[1]/div[2]")).getText();
		String basePriceBuildPage = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[1]/div[2]"))
				.getText();

		System.out.println(basePriceBuildPage);
		double basePriceToDoubleBuildPage = (Integer.parseInt(basePriceBuildPage.replaceAll("\\D", "")));// String regex
		System.out.println(basePriceToDoubleBuildPage);

		// compare if the price is the same
		if (priceDoubleRangePage == basePriceToDoubleBuildPage) {
			System.out.println("price is equal");
		} else {
			System.out.println("price is different");
		}

		// 7.Verify that the price for equipment is $0

		// 7.1 - get the Price for Equipment
		String priceEquipment = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[2]/div[2]")).getText();
		double priceToDoubleEquipment = (Integer.parseInt(priceEquipment.replaceAll("\\D", "")));// String regex?????
		System.out.println("Price for the Equipment: $" + priceToDoubleEquipment);

		// 7.2 - verify that the price for equipment is $0
		if (priceToDoubleEquipment == 0.0) {
			System.out.println("Equipment price $0");
		} else {
			System.out.println("Equipment price is not $0 it's :" + priceToDoubleEquipment);
		}

		// 8. Verify that total price is the sum of base price + Delivery, Processing
		// and Handling Fee

		// 8.1 - get the Delivery, Processing and Handling Fee
		String priceFees = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[3]/div[2]")).getText();
		double priceToDoubleFees = (Integer.parseInt(priceFees.replaceAll("\\D", "")));// String regex?????
		System.out.println("Delivery, Processing and Handling Fee : $" + priceToDoubleFees);

		// 8.2 - get the TotalPrice
		String priceTotalPrice = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[4]/div[2]"))
				.getText();
		double priceToDoubleTotalPrice = (Integer.parseInt(priceTotalPrice.replaceAll("\\D", "")));// String regex?????
		System.out.println("Total price is: $" + priceToDoubleTotalPrice);

		// 8.3 - verify that total price is the sum of base price + Delivery, Processing
		// and Handling Fee

		if ((basePriceToDoubleBuildPage + priceToDoubleEquipment + priceToDoubleFees) == priceToDoubleTotalPrice) {

			System.out.println("Total price is the sum ");

		} else {
			System.out.println("Total price is the NOT a sum ");
		}

		// 9. Select color “Miami Blue”

		// 9.1 - select color “Miami Blue”
		driver.findElement(By.id("s_exterieur_x_FJ5")).click();

		// 9.2 - get the price for color “Miami Blue”

		String priceMiamiBlueColor = driver.findElement(By.id("s_exterieur_x_FJ5")).getAttribute("data-price");
		double priceToDoubleMiamiBlueColor = (Integer.parseInt(priceMiamiBlueColor.replaceAll("\\D", "")));// String
																											// regex?????
		System.out.println("Miami Blue color price: $" + priceToDoubleMiamiBlueColor);

		// 10. Verify that Price for Equipment is Equal to Miami Blue price

		// 10.1 - get the Price for Equipment (update)
		priceEquipment = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[2]/div[2]")).getText();
		priceToDoubleEquipment = (Integer.parseInt(priceEquipment.replaceAll("\\D", "")));// String regex?????
		System.out.println("Price for the Equipment: $" + priceToDoubleEquipment);

		// 10.2 - verify that Price for Equipment is Equal to Miami Blue price

		if (priceToDoubleMiamiBlueColor == priceToDoubleEquipment) {
			System.out.println("Price for Equipment is equal to Miami Blue price");
		} else {
			System.out.println("Price for Equipment is NOT equal to Miami Blue price");

		}

		// 11. Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee

		// 11.1 - get the TotalPrice(updated)
		priceTotalPrice = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[4]/div[2]")).getText();
	
		priceToDoubleTotalPrice = (Integer.parseInt(priceTotalPrice.replaceAll("\\D", "")));// String regex?????
		System.out.println("Total price is: $" + priceToDoubleTotalPrice);

		// 11.2 - verify that total price is the sum of base price + Delivery,
		// Processing
		// and Handling Fee

		if ((basePriceToDoubleBuildPage + priceToDoubleEquipment + priceToDoubleFees) == priceToDoubleTotalPrice) {

			System.out.println("Total price is the sum ");

		} else {
			System.out.println("Total price is the NOT a sum ");
		}
		
		
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		//driver.findElement(By.xpath("//*[@id='scrollIndicatorWrapper']")).click();
		
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,500)");

		// 555. --- (extra step) this will scroll down the page so the item can be seen
		// and can be clicked

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		// 12. Select 20" Carrera Sport Wheels

		// 12.1 - select 20" Carrera Sport Wheels
		driver.findElement(By.id("s_exterieur_x_MXRD")).click();

		// 12.2 - get the price for 20" Carrera Sport Wheels

		String price20CarreraSportWheels = driver.findElement(By.id("s_exterieur_x_MXRD")).getAttribute("data-price");
		double priceToDouble20CarreraSportWheels = (Integer.parseInt(price20CarreraSportWheels.replaceAll("\\D", "")));// String
																														// regex?????
		System.out.println("20\" Carrera Sport Wheels price: $" + priceToDouble20CarreraSportWheels);

		// 13. Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels

		// 13.1 - get the Price for Equipment (update)
		priceEquipment = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[2]/div[2]")).getText();
		priceToDoubleEquipment = (Integer.parseInt(priceEquipment.replaceAll("\\D", "")));// String regex?????
		System.out.println("Price for the Equipment: $" + priceToDoubleEquipment);

		// 13.2 - verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels
		if (priceToDoubleEquipment == (priceToDouble20CarreraSportWheels + priceToDoubleMiamiBlueColor)) {
			System.out.println("Equipment is the sum of Miami Blue price + 20\" Carrera Sport Wheels");
		} else {
			System.out.println("Equipment is NOT the sum of Miami Blue price + 20\" Carrera Sport Wheels");
		}

		// 14. Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee

		// 14.1 - get the TotalPrice(updated)
		priceTotalPrice = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[4]/div[2]")).getText();
		priceToDoubleTotalPrice = (Integer.parseInt(priceTotalPrice.replaceAll("\\D", "")));// String regex?????
		System.out.println("Total price is: $" + priceToDoubleTotalPrice);

		// 14.2 - verify that total price is the sum of base price + Delivery,
		// Processing
		// and Handling Fee

		if ((basePriceToDoubleBuildPage + priceToDoubleEquipment + priceToDoubleFees) == priceToDoubleTotalPrice) {

			System.out.println("Total price is the sum ");

		} else {
			System.out.println("Total price is the NOT a sum ");
		}

		// 15. Select seats ‘Power Sport Seats (14-way) with Memory Package

		// 15.1 scroll down the options window to find the seats option
		js.executeScript("window.scrollBy(0,1000)");

		// 15.2 - select seats 'Power Sport Seats (14-way) with Memory Package'

		driver.findElement(By.id("s_interieur_x_73_x_PP06_x_shorttext")).click();

		// 15.3 - getthe price of the 'Power Sport Seats (14-way) with Memory Package'

		//String pricePowerSportSeats = driver.findElement(By.xpath("//*[@id='seats_73']/div[2]/div[1]/div[3]/div")).getText();
		String pricePowerSportSeats = driver.findElement(By.xpath("//*[@id='seats_73']//div[@class='pBox']/div")).getText();
		
		double priceToDoublePowerSportSeats = (Integer.parseInt(pricePowerSportSeats.replaceAll("\\D", "")));// String
																												// regex?????
		System.out.println("Power Sport Seats (14-way) with Memory Package price: $" + priceToDoublePowerSportSeats);

		// 16. Verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels +
		// Power Sport Seats (14-way) with Memory Package

		// 16.1 - get the Price for Equipment (update)
		priceEquipment = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[2]/div[2]")).getText();
		priceToDoubleEquipment = (Integer.parseInt(priceEquipment.replaceAll("\\D", "")));// String regex?????
		System.out.println("Price for the Equipment: $" + priceToDoubleEquipment);

		// 16.2 - verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package
		if (priceToDoubleEquipment == (priceToDouble20CarreraSportWheels + priceToDoubleMiamiBlueColor
				+ priceToDoublePowerSportSeats)) {
			System.out.println(
					"Equipment is the sum of Miami Blue price + 20\" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package");
		} else {
			System.out.println(
					priceToDouble20CarreraSportWheels + priceToDoubleMiamiBlueColor + priceToDoublePowerSportSeats);
			System.out.println(
					"Equipment is NOT the sum of Miami Blue price + 20\" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package");
		}

		// 17. Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee

		// 17.1 - get the TotalPrice(updated)
		priceTotalPrice = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[4]/div[2]")).getText();
		priceToDoubleTotalPrice = (Integer.parseInt(priceTotalPrice.replaceAll("\\D", "")));// String regex?????
		System.out.println("Total price is: $" + priceToDoubleTotalPrice);

		// 17.2 - verify that total price is the sum of base price + Delivery,
		// Processing
		// and Handling Fee

		if ((basePriceToDoubleBuildPage + priceToDoubleEquipment + priceToDoubleFees) == priceToDoubleTotalPrice) {

			System.out.println("Total price is the sum ");

		} else {
			System.out.println("Total price is the NOT a sum ");
		}

		// 18. Click on Interior Carbon Fiber

		// 18.1 - scroll down the options window to find the Interior Carbon Fiber
		js.executeScript("window.scrollBy(0,700)");

		// 18.2 - click on Interior Carbon Fiber
		driver.findElement(By.id("IIC_subHdl")).click();

		// 19. Select Interior Trim in Carbon Fiber i.c.w. Standard Interior

		// 19.1 - select Interior Trim in Carbon Fiber i.c.w. Standard Interior
		driver.findElement(By.id("vs_table_IIC_x_PEKH_x_c01_PEKH")).click();

		// 19.2 - get the price of the Interior Trim: Carbon Fiber i.c.w. Standard
		// Interior

		String priceCarbonFiberStandard = driver
				.findElement(By.xpath("//*[@id='vs_table_IIC_x_PEKH']/div[1]/div[2]/div")).getText();
		double priceToDoubleCarbonFiberStandard = (Integer.parseInt(priceCarbonFiberStandard.replaceAll("\\D", "")));// String
																														// regex?????
		System.out.println(
				"Interior Trim: Carbon Fiber i.c.w. Standard Interior price: $" + priceToDoubleCarbonFiberStandard);

		/*
		 * 20. Verify that Price for Equipment is the sum of Miami Blue price + 20"
		 * Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package +
		 * Interior Trim in Carbon Fiber i.c.w. Standard Interior
		 */

		// 20.1 - get the Price for Equipment (update)
		priceEquipment = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[2]/div[2]")).getText();
		priceToDoubleEquipment = (Integer.parseInt(priceEquipment.replaceAll("\\D", "")));// String regex?????
		System.out.println("Price for the Equipment: $" + priceToDoubleEquipment);

		// 20.2 - verify that Price for Equipment is the sum of Miami Blue price + 20"
		// Carrera Sport Wheels
		if (priceToDoubleEquipment == (priceToDouble20CarreraSportWheels + priceToDoubleMiamiBlueColor
				+ priceToDoublePowerSportSeats + priceToDoubleCarbonFiberStandard)) {
			System.out.println("Equipment is the sum of Miami Blue price + 20\" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package");
		} else {
			System.out.println("Equipment is NOT the sum of Miami Blue price + 20\" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package");
		}

		// 21. Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee

		// 21.1 - get the TotalPrice(updated)
		priceTotalPrice = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[4]/div[2]")).getText();
		priceToDoubleTotalPrice = (Integer.parseInt(priceTotalPrice.replaceAll("\\D", "")));// String regex?????
		System.out.println("Total price is: $" + priceToDoubleTotalPrice);

		// 21.2 - verify that total price is the sum of base price + Delivery,
		// Processing
		// and Handling Fee

		if ((basePriceToDoubleBuildPage + priceToDoubleEquipment + priceToDoubleFees) == priceToDoubleTotalPrice) {

			System.out.println("Total price is the sum ");

		} else {
			System.out.println("Total price is the NOT a sum ");
		}

		// 22. Click on Performance

		driver.findElement(By.id("IMG_wrapper")).click();

		// 23. Select 7-speed Porsche Doppelkupplung (PDK)

		// 23.1 - select 7-speed Porsche Doppelkupplung (PDK)
		driver.findElement(By.id("vs_table_IMG_x_M250_x_c11_M250")).click();

		// 23.2 - get price for 7-speed Porsche Doppelkupplung (PDK)

		String price7SpeedPDK = driver.findElement(By.xpath("//*[@id='vs_table_IMG_x_M250']/div[1]/div[2]/div"))
				.getText();
		double priceToDouble7SpeedPDK = (Integer.parseInt(price7SpeedPDK.replaceAll("\\D", "")));// String regex?????
		System.out.println("7-speed Porsche Doppelkupplung (PDK) price: $" + priceToDouble7SpeedPDK);

		// 24. Select Porsche Ceramic Composite Brakes (PCCB)

		// 24.1 - scroll down the options window to find the Porsche Ceramic Composite
		// Brakes (PCCB)
		js.executeScript("window.scrollBy(0,500)");

		// 24.1 - select Porsche Ceramic Composite Brakes (PCCB)
		driver.findElement(By.id("vs_table_IMG_x_M450_x_c91_M450")).click();

		// 24.2 - get price for Porsche Ceramic Composite Brakes (PCCB)

		String pricePCCB = driver.findElement(By.xpath("//*[@id='vs_table_IMG_x_M450']/div[1]/div[2]/div")).getText();
		double priceToDoublePCCB = (Integer.parseInt(pricePCCB.replaceAll("\\D", "")));// String regex?????
		System.out.println("Porsche Ceramic Composite Brakes (PCCB) price: $" + priceToDoublePCCB);

		/*
		 * 25. Verify that Price for Equipment is the sum of Miami Blue price + 20"
		 * Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package +
		 * Interior Trim in Carbon Fiber i.c.w. Standard Interior + 7-speed Porsche
		 * Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)
		 */

		// 25.1 - get the Price for Equipment (update)
		priceEquipment = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[2]/div[2]")).getText();
		priceToDoubleEquipment = (Integer.parseInt(priceEquipment.replaceAll("\\D", "")));// String regex?????
		System.out.println("Price for the Equipment: $" + priceToDoubleEquipment);

		// 25.2 - verify that Price for Equipment is the sum of ...25
		if (priceToDoubleEquipment == (priceToDouble20CarreraSportWheels + priceToDoubleMiamiBlueColor
				+ priceToDoublePowerSportSeats + priceToDoubleCarbonFiberStandard + priceToDouble7SpeedPDK +  priceToDoublePCCB )) {
			System.out.println("Equipment is the sum of Miami Blue price + 20\" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + 7-speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)");
		} else {
			System.out.println("Equipment is NOT the sum of Miami Blue price + 20\" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + 7-speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)");
		}

		// 26. Verify that total price is the sum of base price + Price for Equipment +
		// Delivery, Processing and Handling Fee

		// 26.1 - get the TotalPrice(updated)
		priceTotalPrice = driver.findElement(By.xpath("//section[@id='s_price']/div[1]/div[4]/div[2]")).getText();
		priceToDoubleTotalPrice = (Integer.parseInt(priceTotalPrice.replaceAll("\\D", "")));// String regex?????
		System.out.println("Total price is: $" + priceToDoubleTotalPrice);

		// 26.2 - verify that total price is the sum of base price + Delivery,
		// Processing
		// and Handling Fee

		if ((basePriceToDoubleBuildPage + priceToDoubleEquipment + priceToDoubleFees) == priceToDoubleTotalPrice) {

			System.out.println("Total price is the sum ");

		} else {
			System.out.println("Total price is the NOT a sum ");
		}

	}

}
