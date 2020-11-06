package org.wit.albumlist.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class AlbumlistMemStore : AlbumlistStore {

    val albumlists = ArrayList<AlbumlistModel>()

    override fun findAll(): List<AlbumlistModel> {
        return albumlists
    }

    override fun findOne(id: Long) : AlbumlistModel? {
        var foundAlbumlist: AlbumlistModel? = albumlists.find { p -> p.id == id }
        return foundAlbumlist
    }

    override fun create(albumlist: AlbumlistModel) {
        albumlist.id = getId()
        albumlists.add(albumlist)
        logAll()
    }

    override fun update(albumlist: AlbumlistModel) {
        var foundAlbumlist = findOne(albumlist.id!!)
        if (foundAlbumlist != null) {
            foundAlbumlist.title = albumlist.title
            foundAlbumlist.description = albumlist.description
        }
    }

    override fun delete(albumlist: AlbumlistModel) {
        albumlists.remove(albumlist)
    }

    internal fun logAll() {
        albumlists.forEach { logger.info("${it}") }
    }
}