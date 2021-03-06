/*
 ************************************************************************
 *******************  CANADIAN ASTRONOMY DATA CENTRE  *******************
 **************  CENTRE CANADIEN DE DONNÉES ASTRONOMIQUES  **************
 *
 *  (c) 2016.                            (c) 2016.
 *  Government of Canada                 Gouvernement du Canada
 *  National Research Council            Conseil national de recherches
 *  Ottawa, Canada, K1A 0R6              Ottawa, Canada, K1A 0R6
 *  All rights reserved                  Tous droits réservés
 *
 *  NRC disclaims any warranties,        Le CNRC dénie toute garantie
 *  expressed, implied, or               énoncée, implicite ou légale,
 *  statutory, of any kind with          de quelque nature que ce
 *  respect to the software,             soit, concernant le logiciel,
 *  including without limitation         y compris sans restriction
 *  any warranty of merchantability      toute garantie de valeur
 *  or fitness for a particular          marchande ou de pertinence
 *  purpose. NRC shall not be            pour un usage particulier.
 *  liable in any event for any          Le CNRC ne pourra en aucun cas
 *  damages, whether direct or           être tenu responsable de tout
 *  indirect, special or general,        dommage, direct ou indirect,
 *  consequential or incidental,         particulier ou général,
 *  arising from the use of the          accessoire ou fortuit, résultant
 *  software.  Neither the name          de l'utilisation du logiciel. Ni
 *  of the National Research             le nom du Conseil National de
 *  Council of Canada nor the            Recherches du Canada ni les noms
 *  names of its contributors may        de ses  participants ne peuvent
 *  be used to endorse or promote        être utilisés pour approuver ou
 *  products derived from this           promouvoir les produits dérivés
 *  software without specific prior      de ce logiciel sans autorisation
 *  written permission.                  préalable et particulière
 *                                       par écrit.
 *
 *  This file is part of the             Ce fichier fait partie du projet
 *  OpenCADC project.                    OpenCADC.
 *
 *  OpenCADC is free software:           OpenCADC est un logiciel libre ;
 *  you can redistribute it and/or       vous pouvez le redistribuer ou le
 *  modify it under the terms of         modifier suivant les termes de
 *  the GNU Affero General Public        la “GNU Affero General Public
 *  License as published by the          License” telle que publiée
 *  Free Software Foundation,            par la Free Software Foundation
 *  either version 3 of the              : soit la version 3 de cette
 *  License, or (at your option)         licence, soit (à votre gré)
 *  any later version.                   toute version ultérieure.
 *
 *  OpenCADC is distributed in the       OpenCADC est distribué
 *  hope that it will be useful,         dans l’espoir qu’il vous
 *  but WITHOUT ANY WARRANTY;            sera utile, mais SANS AUCUNE
 *  without even the implied             GARANTIE : sans même la garantie
 *  warranty of MERCHANTABILITY          implicite de COMMERCIALISABILITÉ
 *  or FITNESS FOR A PARTICULAR          ni d’ADÉQUATION À UN OBJECTIF
 *  PURPOSE.  See the GNU Affero         PARTICULIER. Consultez la Licence
 *  General Public License for           Générale Publique GNU Affero
 *  more details.                        pour plus de détails.
 *
 *  You should have received             Vous devriez avoir reçu une
 *  a copy of the GNU Affero             copie de la Licence Générale
 *  General Public License along         Publique GNU Affero avec
 *  with OpenCADC.  If not, see          OpenCADC ; si ce n’est
 *  <http://www.gnu.org/licenses/>.      pas le cas, consultez :
 *                                       <http://www.gnu.org/licenses/>.
 *
 *
 ************************************************************************
 */

package ca.nrc.cadc.search.integration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


public class CAOMSearchFormPage extends AbstractSearchFormPage
{
    private static final int DEFAULT_TIMEOUT_IN_SECONDS = 25;
    private static final By H1_HEADER = By.cssSelector("h1");

    static final By DATA_TRAIN_LOCATOR = By.id("caom2@Hierarchy");
    static final By DATA_TRAIN_COLLECTION_MENU = By.id("Observation.collection");
    static final By TARGET_INPUT = By.id("Plane.position.bounds");
    static final By TARGET_FORM_GROUP = By.id("Plane.position.bounds_details");
    static final By TARGET_RESOLUTION_STATUS_ICON_BY = By.className("target_name_resolution_status");
    static final By TARGET_RESOLUTION_STATUS_GOOD_ICON_BY = By.className("target_ok");
    static final By SSOIS_LINK_BY = By.id("ssois_link");
    static final String SPECTRAL_COVERAGE_INPUT_ID = "Plane.energy.bounds.samples";
    static final String OBSERVATION_DATE_INPUT_ID = "Plane.time.bounds.samples";
    static final String PIXEL_SCALE_INPUT_ID = "Plane.position.sampleSize";
    static final By RESET_BUTTON_SELECTOR = By.cssSelector("button[type=\"reset\"]");

    private static final By ACCESS_ACTIONS_DROPDOWN_BY = By.cssSelector("a.access-actions");
    private static final By LOGIN_DROPDOWN_BY = By.cssSelector("a.login-form");
    private static final By USERNAME_INPUT_BY = By.id("as-username");
    private static final By PASSWORD_INPUT_BY = By.id("as-password");
    private static final By LOGIN_SUBMIT_BUTTON_BY = By.id("submitLogin");
    private static final By LOGOUT_LINK_BY = By.id("as-logout");
    private static final By DISPLAY_USERNAME = By.className("auth-username");



    @FindBy(id = "caom2@Hierarchy")
    WebElement dataTrain;

    @FindBy(id = "Observation.observationID")
    WebElement observationIDInput;

    @FindBy(id = "Plane.position.bounds")
    WebElement targetInput;

    @FindBy(id = "Plane.energy.bounds.samples")
    WebElement spectralCoverageInput;

    @FindBy(id = "Plane.time.bounds.samples")
    WebElement observationDateInput;

    @FindBy(id = "ssois_link")
    WebElement ssoisLink;



    /**
     * Constructors need to be public for reflection to find them.
     *
     * @param driver        WebDriver instance.
     * @throws Exception        Any errors.
     */
    public CAOMSearchFormPage(final WebDriver driver) throws Exception
    {
        super(driver, DEFAULT_TIMEOUT_IN_SECONDS);

        waitForElementPresent(DATA_TRAIN_LOCATOR);
        waitForElementPresent(DATA_TRAIN_COLLECTION_MENU);
        waitForElementPresent(TARGET_INPUT);
        waitForElementPresent(By.id(SPECTRAL_COVERAGE_INPUT_ID));
        waitForElementPresent(By.id(OBSERVATION_DATE_INPUT_ID));
        waitForElementPresent(SSOIS_LINK_BY);
        waitForElementPresent(By.id("Observation.observationID"));

        PageFactory.initElements(driver, this);
    }

    void enterObservationID(final String observationIDValue) throws Exception
    {
        enterInputValue(observationIDInput, observationIDValue);
    }

    void enterTarget(final String targetValue) throws Exception
    {
        enterInputValue(targetInput, targetValue);
    }

    void enterValidTarget(final String targetValue) throws Exception
    {
        enterTarget(targetValue);
        confirmResolverOK();
    }

    void confirmResolverOK() throws Exception
    {
        waitForElementPresent(TARGET_RESOLUTION_STATUS_ICON_BY);
        waitForElementVisible(TARGET_RESOLUTION_STATUS_ICON_BY);
        waitForElementPresent(TARGET_RESOLUTION_STATUS_GOOD_ICON_BY);
        waitForElementVisible(TARGET_RESOLUTION_STATUS_GOOD_ICON_BY);
    }

    void enterCollection(final String collection) throws Exception
    {
        final Select collSelect = new Select(dataTrain.findElement(DATA_TRAIN_COLLECTION_MENU));

        // Unselect the 'All' option
        collSelect.deselectByIndex(0);
        collSelect.selectByValue(collection);
    }

    int findDataTrainValueIndex(final By menuLocator, final String value, final boolean ignoreCase)
            throws Exception
    {
        final Select selectMenu = new Select(dataTrain.findElement(menuLocator));
        final List<WebElement> options = selectMenu.getOptions();
        final int optionSize = options.size();

        for (int i = 0; i < optionSize; i++)
        {
            final WebElement option = options.get(i);
            final String optionValue = option.getAttribute("value");

            if ((ignoreCase && optionValue.equalsIgnoreCase(value)) || optionValue.equals(value))
            {
                return i;
            }
        }

        throw new IllegalStateException("No such element with value '" + value + "' (Case"
                                        + (ignoreCase ? " " : " not ") + "ignored)");
    }

    void ssoisLinkLoads() throws Exception
    {
        click(TARGET_FORM_GROUP);

        final String curWindowTitle = getCurrentWindowHandle();

        // click on ssois link
        click(ssoisLink);

        selectWindow("ssois_window");
        waitForTextPresent(H1_HEADER, "Solar System Object Image Search");

        // Nav back to form
        selectWindow(curWindowTitle);
    }
}
