import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CopyHadis2 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Chromedriver\\chromedriver.exe");
		collectNasae();
	}

	static void collectNasae() {
		WebDriver driver = new ChromeDriver();
		String index;
		//FFiles.changeData("F:\\Textsbooks\\nasaeerrorlog.txt", "");
		for(int i=2401; i<=5758; i++) {
			try {
				index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				driver.get("https://www.hadithbd.com/hadith/filter/?book=6&hadith="+i);
				WebElement t1;
				String res = "";
				t1 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span"));
				String text = t1.getText();
				if(text.contains(getSI(i)))
					res = text;

				if(res.length() < 2) {
					addErrorNasae(i, "No result found");
					continue;
				}
				else {
					FFiles.createFolder("F:\\Textsbooks\\Nasae\\"+index);
					FFiles.createAndWrite("F:\\Textsbooks\\Nasae\\"+index+"\\text.txt", res);
				}
				if(!res.startsWith(getSI(i))) addErrorNasae(i, "Has extra text");
			}
			catch (Exception e) {
				addErrorNasae(i, "Error occured!");
			}
		}
	}
	
	static void collectMuslim() {
		WebDriver driver = new ChromeDriver();
		String index;
		FFiles.changeData("F:\\Textsbooks\\muslimerrorlog.txt", "");
		for(int i=1; i<=7281; i++) {
			try {
				index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				driver.get("https://www.hadithbd.com/hadith/filter/?book=2&hadith="+i);
				WebElement t1;
				String res = "";
				t1 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span"));
				String text = t1.getText();
				if(text.contains(getSI(i)+"।"))
					res = text;

				if(res.length() < 2) addErrorMuslim(i, "No result found");
				else {
					FFiles.createFolder("F:\\Textsbooks\\Muslim\\"+index);
					FFiles.createAndWrite("F:\\Textsbooks\\Muslim\\"+index+"\\text.txt", res);
				}
				if(!res.startsWith(getSI(i)+"।")) addErrorMuslim(i, "Has extra text");
			}
			catch (Exception e) {
				addErrorMuslim(i, "Error occured!");
			}
		}
	}
	static void collectTirmiji() {
		WebDriver driver = new ChromeDriver();
		String index;
		FFiles.changeData("F:\\Textsbooks\\tirmijierrorlog.txt", "");
		for(int i=1; i<=3608; i++) {
			try {
				index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				driver.get("https://www.hadithbd.com/hadith/filter/?book=11&hadith="+i);
				WebElement t1;
				String res = "";
				t1 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span"));
				String text = t1.getText();
				if(text.contains(getSI(i)))
					res = text;

				if(res.length() < 2) addErrorTirmiji(i, "No result found");
				else {
					String text2 = "";
					for(int j=0; j<20; j++) {
						try {
							WebElement t2 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span/p["+j+"]/strong"));
							text2 = t2.getText();
							if(text2.length() > 2) break;
						}
						catch(Exception e) {
							
						}
					}
					if(text2.length()>2) {
						res = res.substring(0, text.indexOf(text2));
					}
					FFiles.createFolder("F:\\Textsbooks\\Tirmiji\\"+index);
					FFiles.createAndWrite("F:\\Textsbooks\\Tirmiji\\"+index+"\\text.txt", res);
				}
				if(!res.startsWith(getSI(i))) addErrorTirmiji(i, "Has extra text");
			}
			catch (Exception e) {
				addErrorTirmiji(i, "Error occured!");
			}
		}
	}
	
	static void addErrorMuslim(int i, String e) {
		FFiles.addTo("F:\\Textsbooks\\muslimerrorlog.txt", i+" ---> "+e+"\n");
	}
	static void addErrorTirmiji(int i, String e) {
		FFiles.addTo("F:\\Textsbooks\\tirmijierrorlog.txt", i+" ---> "+e+"\n");
	}
	static void addErrorNasae(int i, String e) {
		FFiles.addTo("F:\\Textsbooks\\nasaeerrorlog.txt", i+" ---> "+e+"\n");
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
