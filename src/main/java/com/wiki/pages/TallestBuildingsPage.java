package com.wiki.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TallestBuildingsPage extends BasePage {

    String sortableColumnByIndexXpath = "//*[@id='Tallest_buildings_in_the_world']/../following-sibling::table[contains(@class, 'sortable')][1]//td[%d]";
    String headerByTextXpath = "//*[@id='Tallest_buildings_in_the_world']/../following-sibling::table[contains(@class, 'sortable')][1]//th[contains(text(),'%s')]";
    String cellXpath = "//*[@id='Tallest_buildings_in_the_world']/../following-sibling::table[contains(@class, 'sortable')][1]//tbody/tr[%d]/td[%d]";
    String headersXpath = "//*[@id='Tallest_buildings_in_the_world']/../following-sibling::table[contains(@class, 'sortable')][1]//th";
    String columnByIndexValuesXpath = "//*[@id='Tallest_buildings_in_the_world']/../following-sibling::table[contains(@class, 'sortable')][1]/tbody//td[%d]";

    public TallestBuildingsPage(WebDriver driver) {
        super(driver);
        changeSortingOfColumnByHeaderText("Rank", "ascending");
    }

    public ArrayList<String> getValuesOfSortedColumnByIndex(int index) {
        return driver.findElements(By.xpath(String.format(sortableColumnByIndexXpath, index)))
            .stream()
            .map(WebElement::getText)
            .map(String::trim)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public void changeSortingOfColumnByHeaderText(String headerText, String sort) {
        WebElement header = driver.findElement(By.xpath(String.format(headerByTextXpath, headerText)));
        String nextSort = header.getAttribute("title").toLowerCase();
        header.click();
        if (nextSort.endsWith(sort.toLowerCase())) {
            waitUntilElementAttributeContains(By.xpath(String.format(headerByTextXpath, headerText)),
                "title",
                sort
            );
        } else {
            waitUntilElementAttributeContains(By.xpath(String.format(headerByTextXpath, headerText)),
                "title",
                nextSort
            );
            changeSortingOfColumnByHeaderText(headerText, sort);
        }
    }

    public String getCellText(int row, int column) {
        return driver.findElement(By.xpath(String.format(cellXpath, row, column))).getText();
    }

    public Map.Entry<String, Integer> getTheMostFrequentCountry() {
        int index = getIndexOfColumnByHeaderText("Country");
        ArrayList<String> countriesList = driver.findElements(By.xpath(String.format(columnByIndexValuesXpath, index)))
            .stream()
            .map(WebElement::getText)
            .map(String::trim)
            .collect(Collectors.toCollection(ArrayList::new));

        Map<String, Integer> countriesMap = new LinkedHashMap<>();
        countriesList
            .forEach(a -> countriesMap.put(a, countriesMap.getOrDefault(a, 0) + 1));

        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : countriesMap.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        return maxEntry;
    }

    private int getIndexOfColumnByHeaderText(String headerText) {
        List<WebElement> list = driver.findElements(By.xpath(headersXpath));
        for (WebElement el : list) {
            if (el.getText().equals(headerText)) {
                return list.indexOf(el) + 1;
            }
        }
        return 0;
    }

}
