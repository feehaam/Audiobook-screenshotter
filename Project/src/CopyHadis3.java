import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CopyHadis3 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Chromedriver\\chromedriver.exe");
		collectMajah();
	}
	
	static void collectMajah() {
		WebDriver driver = new ChromeDriver();
		String index;
		FFiles.changeData("F:\\Textsbooks\\majaherrorlog.txt", "");
		for(int i=1; i<=4341; i++) {
			try {
				index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				driver.get("https://www.hadithbd.com/hadith/filter/?book=9&hadith="+i);
				WebElement t1;
				String res = "";
				t1 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span"));
				String text = t1.getText();
				if(text.contains(getSI(i)))
					res = text;

				if(res.length() < 2) {
					addErrorMajah(i, "No result found");
					continue;
				}
				else {
					FFiles.createFolder("F:\\Textsbooks\\Majah\\"+index);
					FFiles.createAndWrite("F:\\Textsbooks\\Majah\\"+index+"\\text.txt", res);
				}
				if(res.indexOf(getSI(i)) > 10) addErrorMajah(i, "Has extra text");
			}
			catch (Exception e) {
				addErrorMajah(i, "Error occured!");
			}
		}
	}
	
	static void collectDaud() {
		WebDriver driver = new ChromeDriver();
		String index;
		FFiles.changeData("F:\\Textsbooks\\dauderrorlog.txt", "");
		for(int i=1; i<=5184; i++) {
			try {
				index = String.valueOf(i);
				while (index.length() < 4)
					index = "0" + index;
				driver.get("https://www.hadithbd.com/hadith/filter/?book=4&hadith="+i);
				WebElement t1;
				String res = "";
				t1 = driver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/div[1]/div[1]/div/div[1]/span"));
				String text = t1.getText();
				if(text.contains(getSI(i)))
					res = text;

				if(res.length() < 2) addErrorDaud(i, "No result found");
				else {
					if(res.contains("(")  && res.contains(")")){
						int s = res.lastIndexOf("(");
						int e = res.lastIndexOf(")");
						String toCut = res.substring(s, e);
						if(toCut.length()>3) {
							res = res.substring(0,s) + res.substring(e+1, res.length());
						}
					}
					FFiles.createFolder("F:\\Textsbooks\\Daud\\"+index);
					FFiles.createAndWrite("F:\\Textsbooks\\Daud\\"+index+"\\text.txt", res);
				}
				if(!res.startsWith(getSI(i))) addErrorDaud(i, "Has extra text");
			}
			catch (Exception e) {
				addErrorDaud(i, "Error occured!");
			}
		}
	}

	static void addErrorDaud(int i, String e) {
		FFiles.addTo("F:\\Textsbooks\\dauderrorlog.txt", i+" ---> "+e+"\n");
	}
	static void addErrorMajah(int i, String e) {
		FFiles.addTo("F:\\Textsbooks\\majaherrorlog.txt", i+" ---> "+e+"\n");
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
