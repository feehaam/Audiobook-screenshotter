import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CopyHadis {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Chromedriver\\chromedriver.exe");
		collectBukhari();
	}
	
	static void collectBukhari() {
		WebDriver driver = new ChromeDriver();
		String index;
		FFiles.changeData("F:\\Textsbooks\\bukharierrorlog.txt", "");
		for(int i=1; i<=44; i++) {
			try {
				index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				driver.get("https://www.hadithbd.com/hadith/filter/?book=1&hadith="+i);
				WebElement t1, t2;
				String res = "";
				t1 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span"));
				String text = t1.getText();
				if(text.contains(getSI(i)+"।"))
					res = text;
				else {
					t2 = driver.findElement(By.xpath("/html/body/main/div[3]/div/div[2]/div[1]/div[1]/div/div[1]/span"));
					text = t2.getText();
					if(text.contains(getSI(i)+"।"))
						res = text;
				}
				if(res.length() < 2) addError(i, "No result found");
				else {
					FFiles.createFolder("F:\\Textsbooks\\Bukhari\\"+index);
					FFiles.createAndWrite("F:\\Textsbooks\\Bukhari\\"+index+"\\text.txt", res);
				}
				if(!res.startsWith(getSI(i)+"।")) addError(i, "Has extra text");
			}
			catch (Exception e) {
				addError(i, "Error occured!");
			}
		}
	}
	
	static void addError(int i, String e) {
		FFiles.addTo("F:\\Textsbooks\\bukharierrorlog.txt", i+" ---> "+e+"\n");
	}
	
	static void varify() {
		FFiles.changeData("D://Samples//errorlog.txt", "");
		FFiles.createAndWrite("D://Samples//varify.txt", "");
		WebDriver driver = new ChromeDriver();
		String index = "";
		for (int i = 1; i <= 7053; i++) {
			try {
				index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				driver.get("https://www.hadithbd.com/hadith/filter/?book=1&hadith="+i);
				String xpath = "/html";
				WebElement e = driver.findElement(By.xpath(xpath));
				String text = e.getText();
				int x = text.indexOf(getSI(i)+"।");
				String s1 = text.substring(x, x+10);
				String s2 = FFiles.read("D://Samples//"+index+"//text.txt").substring(0, 10);
				FFiles.addTo("D://Samples//varify.txt", i+" - "+s1+" vs "+s2+"\n");
				if(!s1.equals(s2)) {
					FFiles.addTo("D://Samples//varify.txt", "!!!!!!!!!!!\n");
					System.out.println(i+" does not match!!!");
					FFiles.addTo("F://errors.txt", index+" does not match!!!\n");
				}
			} 
			catch(Exception e) {
				System.out.println("Problem in "+i);
				FFiles.addTo("F://errors.txt", index+" may not exits\n");
			}
		}
	}

	static void checkDone() {
		FFiles.changeData("D://Samples//errorlog.txt", "");
		for (int i = 1; i <= 7053; i++) {
			boolean incomplete = true;
			String index = "error";
			try {
				String banglaNo = getSI(i);
				index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				String text = FFiles.read("D://Samples//" + index + "//text.txt");
				if (text.startsWith(banglaNo))
					incomplete = false;
				else
					FFiles.addTo("D://Samples//errorlog.txt", index + " \n");
			} catch (Exception e) {
				FFiles.addTo("D://Samples//errorlog.txt", index + " \n");
			}
		}
	}

	static void fixFails() {
		WebDriver driver = new ChromeDriver();
		String failed = FFiles.read("D://Samples//errorlog.txt");
		FFiles.changeData("D://Samples//errorlog.txt", "");
		for (int i = 1; i <= 7053; i++) {
			String index = String.valueOf(i);
			while (index.length() < 4)
				index = "0" + index;
			if (failed.contains(index)) {
				try {
					driver.get("https://www.hadithbd.com/hadith/filter/?book=1&hadith=" + i);
					String banglaNo = getSI(i);
					for (int j = 1; j < 100; j++) {
						String xpath = "/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span/p[" + j + "]";
						WebElement e = driver.findElement(By.xpath(xpath));
						String text = e.getText();
						if (text.startsWith(banglaNo)) {
							FFiles.createFolder("D://Samples//" + index);
							FFiles.createAndWrite("D://Samples//" + index + "//text.txt", text);
							System.out.println(index + " done.");
							break;
						}
					}
				} catch (Exception e) {
					FFiles.addTo("D://Samples//errorlog.txt", "Error collecting hadis #" + i + "\n");
					System.out.println("EEEEEEEEEEEEEE"+i);
				}
			}

		}
	}

	static void copyHadis() {
		WebDriver driver = new ChromeDriver();
		FFiles.changeData("D://Samples//errorlog.txt", "");
		for (int i = 1; i <= 7053; i++) {
			try {
				driver.get("https://www.hadithbd.com/hadith/filter/?book=1&hadith=" + i);
				String banglaNo = getSI(i);
				String index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				for (int j = 1; j < 100; j++) {
					String xpath = "/html/body/main/div[3]/div/div[2]/div[1]/div[1]/div/div[1]/span/p[" + j + "]";
					WebElement e = driver.findElement(By.xpath(xpath));
					String text = e.getText();
					if (text.startsWith(banglaNo)) {
						FFiles.createFolder("D://Samples//" + index);
						FFiles.createAndWrite("D://Samples//" + index + "//text.txt", text);
						System.out.println(index + " done.");
						break;
					}
				}
			} catch (Exception e) {
				FFiles.addTo("D://Samples//errorlog.txt", "Error collecting hadis #" + i + "\n");
			}

		}
		driver.close();
	}

	static String getSI(int N) {
		String no = String.valueOf(N);
		String banglaNumerics[] = { "০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯" };
		String banglaNo = "";
		for (int i = 0; i < no.length(); i++) {
			banglaNo += banglaNumerics[no.charAt(i) - '0'];
		}
		return banglaNo;
	}
}
