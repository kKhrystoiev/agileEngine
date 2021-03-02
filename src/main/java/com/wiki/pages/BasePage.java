package com.wiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait driverWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, 5);
    }

    public void waitUntilElementAttributeContains(By by, String atr, String val) {
        driverWait.until(
            ExpectedConditions
                .not(ExpectedConditions
                    .attributeContains(by, atr, val)
                )
        );
    }

}
