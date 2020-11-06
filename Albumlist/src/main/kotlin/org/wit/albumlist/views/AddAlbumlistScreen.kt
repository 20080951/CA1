package org.wit.albumlist.views


import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.albumlist.controllers.AlbumlistUIController
import tornadofx.*
import kotlin.reflect.KClass

class AddAlbumlistScreen : View("Add Album ") {
    val model = ViewModel()
    val _title = model.bind { SimpleStringProperty() }
    val _description = model.bind { SimpleStringProperty() }
    val _duration = model.bind { SimpleStringProperty() }
    val albumlistUIController: AlbumlistUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Artist Name") {
                textfield(_title).required()
            }
            field("Album Description") {
                textarea(_description).required()
            }
            field("Duration") {
                textfield(_duration).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        albumlistUIController.add(_title.value,_description.value,_duration.value)

                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                       albumlistUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _title.value = ""
        _description.value = ""
        _duration.value = ""
        model.clearDecorators()
    }
}