application {
  title = 'Gr3Client'
  startupGroups = ['Gr3Client']

  // Should Griffon exit when no Griffon created frames are showing?
  //autoShutdown = true
  autoShutdown = false

  // If you want some non-standard application class, apply it here
  //frameClass = 'javax.swing.JFrame'
}

griffon.rest.injectInto = ["service"]

mvcGroups {
  'PrefsPanel' {
    model = 'gr3.PrefsPanelModel'
    controller = 'gr3.PrefsPanelController'
    actions = 'gr3.PrefsPanelActions'
    view = 'gr3.PrefsPanelView'
  }

  'Gr3Client' {
    model = 'gr3.Gr3ClientModel'
    controller = 'gr3.Gr3ClientController'
    actions = 'gr3.Gr3ClientActions'
    menus = 'gr3.Gr3ClientMenus'
    view = 'gr3.Gr3ClientView'
  }
}

