package org.wit.albumlist.controllers


import mu.KotlinLogging
import org.wit.albumlist.models.AlbumlistJSONStore
import org.wit.albumlist.models.AlbumlistModel
import org.wit.albumlist.views.*
import tornadofx.*

class AlbumlistUIController : Controller() {

    val albumlists = AlbumlistJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark TornadoFX UI App" }
    }
    fun add(_title : String, _description : String){


        var aAlbumlist = AlbumlistModel(title = _title, description = _description)
        albumlists.create(aAlbumlist)
        logger.info("Placemark Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListAlbumlistScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        albumlists.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddAlbumlistScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddAlbumlistScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListAlbumlistScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}