package gr3

import static java.awt.BorderLayout.*
import javax.swing.JFrame
import java.awt.Color
import org.jdesktop.swingx.painter.PinstripePainter

frame(id: 'preferencesFrame', title: 'Preferences', pack: true, locationByPlatform: true, show: true,
        defaultCloseOperation: JFrame.DISPOSE_ON_CLOSE) {
  jxheader(title: "Gr3 Preferences",
          description: 'Establish your preferences here.',
          icon: imageIcon('/griffon-icon-48x48.png'),  // TODO: change icon
          border: emptyBorder(4),
          constraints: NORTH,
          backgroundPainter: new PinstripePainter(Color.LIGHT_GRAY))
  panel(border: loweredBevelBorder(), constraints: CENTER) {
    tableLayout(cellpadding: 2) {
      tr {
        td { label("Server:") }
        td {
          textField(id: 'server', columns: 24, text: bind(source: model, 'server', mutual: true,
                  validator: { controller.isNonBlank(server) }))
        }
      }
      tr {
        td { label("Port:") }
        td {
          textField(id: 'port', columns: 5, text: bind(source: model, 'port', mutual: true,
                  validator: { controller.isInteger(port) }))
        }
      }
    }
  }

  hbox(constraints: SOUTH) {
    glue()
    button(closeAction)
    hstrut(8)
    button(id: 'saveButton', saveAction)
  }
}
