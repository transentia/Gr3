package gr3

import org.springframework.beans.factory.InitializingBean

class ClientRegistryService implements InitializingBean {
  def authorisedIPs
  def clientRegistry

  boolean transactional = false

  def register = {ip ->
    clientRegistry[ip] = true
  }

  def registered = {ip ->
    clientRegistry[ip] == true
  }

  def unRegister = {ip ->
    clientRegistry[ip] = false
  }

  void afterPropertiesSet() {
    def authorisedMap = [:]
    for (ip in authorisedIPs)
      authorisedMap[ip] = false
    clientRegistry = authorisedMap.asSynchronized()
  }
}
