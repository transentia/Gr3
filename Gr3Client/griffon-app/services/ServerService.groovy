import static groovyx.net.http.ContentType.*
import gr3.NetworkUtils

class ServerService {

  def registerAction = {->
    withRest(uri: NetworkUtils.URI) {
      def resp = post(path: 'register')
      assert resp.status == 200
      //assert resp.data?.text == "OK"
    }
  }

  def unRegisterAction = {->
    withRest(uri: NetworkUtils.URI) {
      def resp = delete(path: 'unRegister',
              contentType: TEXT)
      assert resp.status == 200
      assert resp.data?.text == "OK"
    }
  }

  def totalizerAction = {->
    withRest(id: 'totalizer', uri: NetworkUtils.URI) {
      def resp = get(path: 'totalizer',
              contentType: JSON)
      assert resp.status == 200
      resp.data?.tote
    }
  }

  def playAction = {value ->
    withRest(id: 'play', uri: NetworkUtils.URI) {
      def resp = put(path: 'play',
              body: [amount: value],
              contentType: JSON)
      assert resp.status == 200
      resp.data?.tote
    }
  }

  def winAction = {pv, w ->
    withRest(id: 'play', uri: NetworkUtils.URI) {
      def resp = put(path: 'win',
              body: [playValue: pv, winnings: w],
              contentType: JSON)
      assert resp.status == 200
      resp.data?.tote
    }
  }
}
