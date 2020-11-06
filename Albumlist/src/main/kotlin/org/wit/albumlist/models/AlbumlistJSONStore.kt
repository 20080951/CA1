package org.wit.albumlist.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.albumlist.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "albumlists.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<AlbumlistModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class AlbumlistJSONStore : AlbumlistStore {

    var albumlists = mutableListOf<AlbumlistModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<AlbumlistModel> {
        return albumlists
    }

    override fun findOne(id: Long) : AlbumlistModel? {
        var foundAlbumlist: AlbumlistModel? = albumlists.find { p -> p.id == id }
        return foundAlbumlist
    }

    override fun create(albumlist: AlbumlistModel) {
        albumlist.id = generateRandomId()
        albumlists.add(albumlist)
        serialize()
    }

    override fun update(albumlist: AlbumlistModel) {
        var foundAlbumlist = findOne(albumlist.id!!)
        if (foundAlbumlist != null) {
            foundAlbumlist.title = albumlist.title
            foundAlbumlist.description = albumlist.description
        }
        serialize()
    }

    override fun delete(albumlist: AlbumlistModel) {
        albumlists.remove(albumlist)
        serialize()
    }

    internal fun logAll() {
        albumlists.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(albumlists, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        albumlists = Gson().fromJson(jsonString, listType)
    }
}
