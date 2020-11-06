package org.wit.albumlist.views

import org.wit.albumlist.controllers.AlbumlistUIController
import org.wit.albumlist.models.AlbumlistModel
import tornadofx.*

class ListAlbumlistScreen : View("List Placemarks") {

    val albumlistUIController: AlbumlistUIController by inject()
    val tableContent = albumlistUIController.albumlists.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", AlbumlistModel::id)
            readonlyColumn("TITLE", AlbumlistModel::title)
            readonlyColumn("DESCRIPTION", AlbumlistModel::description)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    albumlistUIController.closeList()
                }
            }
        }
    }

}