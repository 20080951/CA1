package org.wit.albumlist.models

interface AlbumlistStore {
    fun findAll(): List<AlbumlistModel>
    fun findOne(id: Long): AlbumlistModel?
    fun create(placemark: AlbumlistModel)
    fun update(placemark: AlbumlistModel)
    fun delete(placemark: AlbumlistModel)
}