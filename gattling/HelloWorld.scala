package basic

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import com.excilys.ebi.gatling.jdbc.Predef._
import com.excilys.ebi.gatling.http.Headers.Names._
import akka.util.duration._
import bootstrap._

class HelloWorldSimulation extends Simulation {

  val httpConf = httpConfig
    .baseURL("http://localhost:8080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip,deflate,sdch")
    .acceptLanguageHeader("en-US,en;q=0.8,de-CH;q=0.6")
    .disableFollowRedirect

  val scn = scenario("Hello World")
    .exec(http("request_1").get("/").check(status.is(404))).pause(0 milliseconds, 100 milliseconds)
    .repeat(5) {
        exec(http("request_2").get("/api/hello/world").check(status.is(200))).pause(12, 13)
    }

  setUp(scn.users(10).ramp(10).protocolConfig(httpConf))
}
