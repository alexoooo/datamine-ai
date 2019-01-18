package ao.ai.opt

import org.specs._

/**
 * User: AO
 * Date: 2/6/11
 * Time: 8:40 PM
 */

class HelloWorldSpec extends Specification {
  "'hello world' has 11 characters" in {
     "hello world".size must_== 11
  }
  "'hello world' matches 'h.* w.*'" in {
     "hello world" must be matching("h.* w.*")
  }
}