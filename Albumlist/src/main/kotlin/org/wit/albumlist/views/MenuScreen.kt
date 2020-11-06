package org.wit.albumlist.views

import com.github.mm.coloredconsole.ColoredConsole.Companion.PURPLE
import javafx.application.Platform
import javafx.geometry.Orientation
import javafx.scene.paint.Color.DARKGREEN
import javafx.scene.text.FontWeight
import org.wit.albumlist.controllers.AlbumlistUIController
import tornadofx.*
import java.awt.Color
import java.awt.Color.*

class MenuScreen : View("Album List Main Menu") {

    val AlbumlistUIController: AlbumlistUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)

        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add to Album list")  {
                style {
                    fontWeight = FontWeight.EXTRA_BOLD
                    }

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        AlbumlistUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Albums") {
                style {
                    fontWeight = FontWeight.BOLD
                }
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        AlbumlistUIController.loadListScreen()
                    }
                }
            }

            text("")
            button("Exit") {
                style {
                  //  textFill = Color.RED
                }
                isDefaultButton = true
                useMaxWidth = true

                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}