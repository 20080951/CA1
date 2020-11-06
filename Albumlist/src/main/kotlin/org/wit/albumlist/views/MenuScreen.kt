package org.wit.albumlist.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.albumlist.controllers.AlbumlistUIController
import tornadofx.*

class MenuScreen : View("Albumlist Main Menu") {

    val AlbumlistUIController: AlbumlistUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Albumlist") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        AlbumlistUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Albumlists") {

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