package org.wit.albumlist.controllers

import mu.KotlinLogging
import org.wit.albumlist.models.AlbumlistJSONStore
import org.wit.albumlist.models.AlbumlistMemStore
import org.wit.albumlist.models.AlbumlistModel
import org.wit.albumlist.views.AlbumlistView

class AlbumlistController {

    // val albumlists = AlbumlistMemStore()
    val albumlists = AlbumlistJSONStore()
    val albumlistView = AlbumlistView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Albumlist Console App" }
        println("Albumlist Kotlin App Version 4.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Albumlist Console App" }
    }

    fun menu() :Int { return albumlistView.menu() }

    fun add(){
        var aAlbumlist = AlbumlistModel()

        if (albumlistView.addAlbumlistData(aAlbumlist))
            albumlists.create(aAlbumlist)
        else
            logger.info("Albumlist Not Added")
    }

    fun list() {
        albumlistView.listAlbumlists(albumlists)
    }

    fun update() {

        albumlistView.listAlbumlists(albumlists)
        var searchId = albumlistView.getId()
        val aAlbumlist = search(searchId)

        if(aAlbumlist != null) {
            if(albumlistView.updateAlbumlistData(aAlbumlist)) {
                albumlists.update(aAlbumlist)
                albumlistView.showAlbumlist(aAlbumlist)
                logger.info("Albumlist Updated : [ $aAlbumlist ]")
            }
            else
                logger.info("Albumlist Not Updated")
        }
        else
            println("Albumlist Not Updated...")
    }

    fun delete() {
        albumlistView.listAlbumlists(albumlists)
        var searchId = albumlistView.getId()
        val aAlbumlist = search(searchId)

        if(aAlbumlist != null) {
            albumlists.delete(aAlbumlist)
            println("Albumlist Deleted...")
            albumlistView.listAlbumlists(albumlists)
        }
        else
            println("Albumlist Not Deleted...")
    }

    fun search() {
        val aAlbumlist = search(albumlistView.getId())!!
        albumlistView.showAlbumlist(aAlbumlist)
    }


    fun search(id: Long) : AlbumlistModel? {
        var foundAlbumlist = albumlists.findOne(id)
        return foundAlbumlist
    }

    fun dummyData() {
        albumlists.create(AlbumlistModel(title = "New York New York", description = "So Good They Named It Twice"))
        albumlists.create(AlbumlistModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        albumlists.create(AlbumlistModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
}