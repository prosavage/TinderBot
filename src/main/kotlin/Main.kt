import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

fun main(args: Array<String>) {
    Main().enable()
}



class Main() {

    fun enable() {
        val options = ChromeOptions()
        options.addArguments("user-data-dir=~/Documents/data")
        val driver = ChromeDriver(options)
        driver.get("https://tinder.com")
    }



}