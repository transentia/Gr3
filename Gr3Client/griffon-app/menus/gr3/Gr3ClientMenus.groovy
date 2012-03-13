package gr3

import static griffon.util.GriffonApplicationUtils.*

menuBar(id: 'mainMenuBar') {
  if (!isMacOSX) {
    menu(text: 'File', mnemonic: 'F') {
      menuItem(preferencesAction)
      separator()
      menuItem(exitAction)
    }
  }
  if (!isMacOSX) {
    glue()
    menu(text: 'Help', mnemonic: 'H') {
      menuItem(aboutAction)
    }
  }
}

