package gr3

class TotalizerController {
  private static final JSON_CT = "application/json"
  private static final TEXT_CT = "text/plain"

  def totalizerService
  def clientRegistryService

  static allowedMethods = [startAction: 'POST', stopAction: 'DELETE', playAction: 'PUT', winAction: 'PUT', totalizerAction: 'GET']

  def beforeInterceptor = {
    def remIp = request.remoteAddr
    def registered = clientRegistryService.registered(remIp)
    if (registered && (actionName == 'registerAction')) {
      log.warn "Incoming register request from registered client: $remIp"
      return false
    }
    else if ((! registered) && (actionName == 'unRegisterAction')) {
      log.warn "Incoming unRegister request from unregistered client: $remIp"
      return false
    }
  }

  def registerAction = {
    def remIp = request.remoteAddr
    log.debug "Register: $remIp"

    clientRegistryService.register(remIp)

    render(text: "OK", contentType: TEXT_CT)
  }

  def unRegisterAction = {
    def remIp = request.remoteAddr
    log.debug "UnRegister: $remIp"

    clientRegistryService.unRegister(remIp)

    render(text: "OK", contentType: TEXT_CT)
  }

  def totalizerAction = {
    log.debug "totalizerAction request from $request.remoteAddr"
    render(contentType: JSON_CT) {
      [tote: totalizerService.getCurrentValue()]
    }
  }

  def playAction = {
    def amt = params.int('amount')
    def tote = totalizerService.accumulate(amt)
    log.debug "playAction request from $request.remoteAddr: ${amt}; tote now: ${tote}"
    render(contentType: JSON_CT) {
      [tote: tote]
    }
  }

  def winAction = {
    def playValue = params.int('playValue')
    def winnings = params.int('winnings')
    def tote = totalizerService.accumulate(-winnings)
    log.debug "winAction request from $request.remoteAddr: *** WIN *** played: ${playValue}, won: ${winnings}; tote now: ${tote}"
    render(contentType: JSON_CT) {
      [tote: tote]
    }
  }
}
