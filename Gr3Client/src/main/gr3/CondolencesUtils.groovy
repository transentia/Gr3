package gr3
class CondolencesUtils {
  private static final rand = new Random()
  private static final messages = ['Oh DEAR!',
          'The BIG WIN might be up next!',
          'Better luck next time!',
          'A fool and his/her money are easily separated...']

  static condolences = {
    messages[rand.nextInt(messages.size())]
  }
}
