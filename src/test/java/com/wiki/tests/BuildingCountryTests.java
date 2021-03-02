package com.wiki.tests;

import com.wiki.TestBase;
import com.wiki.pages.TallestBuildingsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class BuildingCountryTests extends TestBase {

    @Test
    public void verifyThatTheCountryWithBiggestNumberOfTallBuildingsIsChina() {
        TallestBuildingsPage tallestBuildingsPage = new TallestBuildingsPage(getDriver());

        Map.Entry<String, Integer> country = tallestBuildingsPage.getTheMostFrequentCountry();

        Assert.assertEquals(country.getKey(), "China");
    }

}
