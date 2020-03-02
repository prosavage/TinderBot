import org.openqa.selenium.chrome.ChromeDriver

fun main(args: Array<String>) {
    Main().enable()
}



class Main() {

    fun enable() {
        val driver = ChromeDriver()
        driver.get("https://tinder.com")
    }



}