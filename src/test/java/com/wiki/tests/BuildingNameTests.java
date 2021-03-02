package com.wiki.tests;

import com.wiki.TestBase;
import com.wiki.pages.TallestBuildingsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BuildingNameTests extends TestBase {

    @Test
    public void verifyThatTheOldestBuildingIsEmpireStateBuilding() {
        TallestBuildingsPage tallestBuildingsPage = new TallestBuildingsPage(getDriver());

        tallestBuildingsPage.changeSortingOfColumnByHeaderText("Year", "ascending");
        String name = tallestBuildingsPage.getCellText(1, 2);

        Assert.assertEquals(name, "Empire State Building");
    }

}
