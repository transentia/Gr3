package gr3

import java.beans.PropertyChangeListener
import java.awt.Color
import javax.swing.BorderFactory
import gr3.PrefsUtils

class PrefsPanelController {
  // these will be injected by Griffon
  def model
  def view

  final gb = BorderFactory.createLineBorder(Color.GRAY)
  final rb = BorderFactory.createLineBorder(Color.RED)

  private isNonBlank = {tf ->
//    model.serverTfValid =
        (tf.text ==~ /\S+/)
  }

  private isInteger = {tf ->
    try {
      def n = Integer.parseInt(tf.text)
      if ( ! (0..Short.MAX_VALUE - 1).contains(n))
        throw new IllegalArgumentException("Port out of range")
//      model.portTfValid = true
      true
    } catch (x) { /* NumberFormatException, IllegalArgumentException*/
//      model.portTfValid = false
    false
    }
  }

  private handleSaveButton = {->
      view.saveButton.enabled = isInteger(view.port) && isNonBlank(view.server)
  }

  void mvcGroupInit(Map args) {
    // this method is called after model and view are injected
    model.addPropertyChangeListener({evt ->
//      println "PORT/SERVER both: ${evt.propertyName}"
      handleSaveButton()
    } as PropertyChangeListener)
    model.addPropertyChangeListener("port", {evt ->
//      println "PORT"
      view.port.border = isInteger(view.port) ? gb : rb
    } as PropertyChangeListener)
    model.addPropertyChangeListener("server", {evt ->
//      println "SERVER"
      view.server.border = isNonBlank(view.server) ? gb : rb
    } as PropertyChangeListener)
  }

  void postMvcGroupInit(Map args = [:]) {
    view.server.text = model.server// = PrefsUtils.getServer()
    view.port.text = model.port// = PrefsUtils.getPort()
//    model.serverTfValid =  isNonBlank(view.server)
//    model.portTfValid = isInteger(view.port)
  }

  def closeWithoutSavingAction = {evt = null ->
    view.preferencesFrame.visible = false;
    destroyMVCGroup('PrefsPanel')
  }

  def savePreferencesAction = {evt = null ->
    PrefsUtils.setServer(model.server)
    PrefsUtils.setPort(model.port)
    closeWithoutSavingAction evt
  }
}
