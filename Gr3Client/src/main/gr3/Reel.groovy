package gr3

import javax.swing.JList
import javax.swing.DefaultListModel

class Reel extends JList {

  final rand = new Random()
  final icons

  Reel(Map m) {
    icons = m.icons
    setModel(makeModel())
  }

  private makeModel() {
    def dm = new DefaultListModel()
    for (i in icons) {
      dm.addElement i
    }
    dm
  }

  def activate = {
    java.util.Collections.shuffle(icons, rand)

    setModel(makeModel())
  }
}
