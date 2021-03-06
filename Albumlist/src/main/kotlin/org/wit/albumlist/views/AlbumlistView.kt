package org.wit.albumlist.views


import com.github.mm.coloredconsole.colored
import org.wit.albumlist.models.AlbumlistJSONStore
import org.wit.albumlist.models.AlbumlistModel

class AlbumlistView {

    fun menu() : Int {

        var option : Int
        var input: String?
colored {

    println("MAIN MENU".blue.bold.italic)
    println(" 1. Add Albumlist".green.bold)
    println(" 2. Update Albumlist".green.bold)
    println(" 3. List All Albumlists".green.bold)
    println(" 4. Search Albumlists".green.bold)
    println(" 5. Delete Albumlist".green.bold)
    println("-1. Exit".red.bold)
    println()
    print("Enter Option : ".yellow.bold.italic)
}
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listAlbumlists(albumlists : AlbumlistJSONStore) {
        println("List All Albumlists")
        println()
        albumlists.logAll()
        println()
    }

    fun showAlbumlist(albumlist : AlbumlistModel) {
        if(albumlist != null)
            println("Albumlist Details [ $albumlist ]")
        else
            println("Albumlist Not Found...")
    }

    fun addAlbumlistData(albumlist : AlbumlistModel) : Boolean {

        println()
        print("Enter a Title : ")
        albumlist.title = readLine()!!
        print("Enter a Description : ")
        albumlist.description = readLine()!!
        print("Enter a Duration : ")
        albumlist.duration = readLine()!!

        return albumlist.title.isNotEmpty() && albumlist.description.isNotEmpty() && albumlist.duration.isNotEmpty()
    }

    fun updateAlbumlistData(albumlist : AlbumlistModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?
        var tempDuration: String?

        if (albumlist != null) {
            print("Enter a new Title for [ " + albumlist.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + albumlist.description + " ] : ")
            tempDescription = readLine()!!
            print("Enter a new Description for [ " + albumlist.duration + " ] : ")
            tempDuration = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                albumlist.title = tempTitle
                albumlist.description = tempDescription
                albumlist.duration = tempDuration

                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}