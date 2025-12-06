package ru.netology.testing.uiautomator

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertNull


const val SETTINGS_PACKAGE = "com.android.settings"
const val MODEL_PACKAGE = "ru.netology.testing.uiautomator"

const val TIMEOUT = 5000L

@RunWith(AndroidJUnit4::class)
class ChangeTextTest {

    private lateinit var device: UiDevice
    private val textToSet = "Netology"
    private var initailText = ""
    private val emptyText = ""
    private val spaceText = "   "

//    @Test
//    fun testInternetSettings() {
//        // Press home
//        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
//        device.pressHome()
//
//        // Wait for launcher
//        val launcherPackage = device.launcherPackageName
//        device.wait(Until.hasObject(By.pkg(launcherPackage)), TIMEOUT)
//        waitForPackage(SETTINGS_PACKAGE)
//
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val intent = context.packageManager.getLaunchIntentForPackage(SETTINGS_PACKAGE)
//        context.startActivity(intent)
//        device.wait(Until.hasObject(By.pkg(SETTINGS_PACKAGE)), TIMEOUT)
//
//        device.findObject(
//            UiSelector().resourceId("android:id/title").instance(0)
//        ).click()
//    }

//    @Test
//    fun testChangeText() {
//        // Press home
//        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
//        device.pressHome()
//
//        // Wait for launcher
//        val launcherPackage = device.launcherPackageName
//        device.wait(Until.hasObject(By.pkg(launcherPackage)), TIMEOUT)
//        waitForPackage(SETTINGS_PACKAGE)
//
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        val packageName = context.packageName
//        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
//        context.startActivity(intent)
//        device.wait(Until.hasObject(By.pkg(packageName)), TIMEOUT)
//
//
//        device.findObject(By.res(packageName, "userInput")).text = textToSet
//        device.findObject(By.res(packageName, "buttonChange")).click()
//
//        val result = device.findObject(By.res(packageName, "textToBeChanged")).text
//        assertEquals(result, textToSet)
//    }

    private fun waitForPackage(packageName: String) {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        context.startActivity(intent)
        device.wait(Until.hasObject(By.pkg(packageName)), TIMEOUT)
    }

    @Before
    fun beforeEachTest() {
        // Press home
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage)), TIMEOUT)
    }

//    @Test
//    fun testInternetSettings() {
//        waitForPackage(SETTINGS_PACKAGE)
//
//        device.findObject(
//            UiSelector().resourceId("android:id/title").instance(0)
//        ).click()
//    }

    @Test
    fun testChangeText() {
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)

        device.findObject(By.res(packageName, "userInput")).text = textToSet
        device.findObject(By.res(packageName, "buttonChange")).click()

        val result = device.findObject(By.res(packageName, "textToBeChanged")).text
        assertEquals(result, textToSet)
    }

    // 1.1 Проверка на установку пустой строки
    @Test
    fun testChangeEmptyText() {
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)

        initailText = device.findObject(By.res(packageName, "textToBeChanged")).text
        device.findObject(By.res(packageName, "userInput")).text = emptyText
        device.findObject(By.res(packageName, "buttonChange")).click()

        val result = device.findObject(By.res(packageName, "textToBeChanged")).text
        assertEquals(result, initailText)
    }

    // 1.2 Проверка на неустановление строки из пробелов
    @Test
    fun testChangeSpaceText() {
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)

        initailText = device.findObject(By.res(packageName, "textToBeChanged")).text
        device.findObject(By.res(packageName, "userInput")).text = spaceText
        device.findObject(By.res(packageName, "buttonChange")).click()

        val result = device.findObject(By.res(packageName, "textToBeChanged")).text
        assertEquals(result, initailText)
    }

    // 2.1 Открытие новой Activity с текстом
    @Test
    fun testOpenActivityText() {
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)

        initailText = device.findObject(By.res(packageName, "textToBeChanged")).text
        device.findObject(By.res(packageName, "userInput")).text = textToSet
        device.findObject(By.res(packageName, "buttonActivity")).click()

        device.wait(Until.hasObject(By.res(packageName, "text")), TIMEOUT)

        val result = device.findObject(By.res(packageName, "text")).text
        assertEquals(result, textToSet)
    }

    // 2.2 Открытие новой Activity с пустой строкой
    @Test
    fun testOpenActivityEmptyText() {
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)

        initailText = device.findObject(By.res(packageName, "textToBeChanged")).text
        device.findObject(By.res(packageName, "userInput")).text = emptyText
        device.findObject(By.res(packageName, "buttonActivity")).click()

        device.wait(Until.hasObject(By.res(packageName, "text")), TIMEOUT)

        assertNull(device.findObject(By.res(packageName, "text")))
    }

    // 2.3 Открытие новой Activity с пробелами
    @Test
    fun testOpenActivitySpaceText() {
        val packageName = MODEL_PACKAGE
        waitForPackage(packageName)

        initailText = device.findObject(By.res(packageName, "textToBeChanged")).text
        device.findObject(By.res(packageName, "userInput")).text = spaceText
        device.findObject(By.res(packageName, "buttonActivity")).click()

        device.wait(Until.hasObject(By.res(packageName, "text")), TIMEOUT)

        val result = device.findObject(By.res(packageName, "text")).text
        assertEquals(result, spaceText)
    }
}



