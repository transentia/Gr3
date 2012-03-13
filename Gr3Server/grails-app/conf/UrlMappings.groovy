class UrlMappings {
  static mappings = {
    "/totalizer/play"(controller:"totalizer", action: 'playAction', parseRequest:true)
    "/totalizer/win"(controller:"totalizer", action: 'winAction', parseRequest:true)
    "/totalizer/register"(controller:"totalizer", action: 'registerAction')
    "/totalizer/unRegister"(controller:"totalizer", action: 'unRegisterAction')
    "/totalizer/totalizer"(controller:"totalizer", action: 'totalizerAction')

    "/$controller/$action?/$id?" {
      constraints {
        // apply constraints here
      }
    }
    "/"(view: "/index")
    "500"(view: '/error')
  }
}
