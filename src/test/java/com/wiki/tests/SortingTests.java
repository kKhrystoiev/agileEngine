package com.wiki.tests;

import com.wiki.TestBase;
import com.wiki.pages.TallestBuildingsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;

import static com.wiki.utils.Utils.sortByAscendingListWithStringsAndInt;

public class SortingTests extends TestBase {

    @Test
    public void verifyThatAscendingSortingByRankWorks() {
        TallestBuildingsPage tallestBuildingsPage = new TallestBuildingsPage(getDriver());

        ArrayList<String> expectedValues = tallestBuildingsPage.getValuesOfSortedColumnByIndex(1);
        tallestBuildingsPage.changeSortingOfColumnByHeaderText("Rank", "ascending");
        ArrayList<String> actualValues = tallestBuildingsPage.getValuesOfSortedColumnByIndex(1);

        Assert.assertEquals(actualValues,
            sortByAscendingListWithStringsAndInt(expectedValues)
        );
    }

    @Test
    public void verifyThatDescendingSortingByNameWorks() {
        TallestBuildingsPage tallestBuildingsPage = new TallestBuildingsPage(getDriver());

        ArrayList<String> expectedValues = tallestBuildingsPage.getValuesOfSortedColumnByIndex(2);
        tallestBuildingsPage.changeSortingOfColumnByHeaderText("Name", "descending");
        ArrayList<String> actualValues = tallestBuildingsPage.getValuesOfSortedColumnByIndex(2);

        Collections
            .reverse(
                sortByAscendingListWithStringsAndInt(expectedValues)
            );

        Assert.assertEquals(actualValues,
            expectedValues
        );
    }

}
