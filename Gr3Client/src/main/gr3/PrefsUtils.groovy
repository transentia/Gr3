package gr3

import java.util.prefs.Preferences

class PrefsUtils {
  private static final SERVER_KEY = 'server'
  private static final DEFAULT_SERVER = 'localhost'
  private static final PORT_KEY = 'port'
  private static final DEFAULT_PORT = '8080'
  private static final prefs = Preferences.userRoot().node('/Gr3')

  static setServer = {s ->
    prefs.put(SERVER_KEY, s)
    prefs.sync()
  }
  static getServer = {
    prefs.sync()
    prefs.get(SERVER_KEY, DEFAULT_SERVER)
  }
  static setPort = {p ->
    prefs.put(PORT_KEY, p)
    prefs.sync()
  }
  static getPort = {
    prefs.sync()
    prefs.get(PORT_KEY, DEFAULT_PORT)
  }
}
