/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.catalog

import org.burstsys.catalog.CatalogService.CatalogUnitTestServerConfig
import org.burstsys.vitals.logging.VitalsLog
import org.burstsys.vitals.logging.VitalsLogger
import org.burstsys.vitals.properties.VitalsPropertyRegistry
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.BeforeAndAfterAll
import org.scalatest.BeforeAndAfterEach

package object test extends VitalsLogger {

  abstract class BurstCatalogSqlSpecSupport extends AnyFlatSpec with Matchers with BeforeAndAfterAll with BeforeAndAfterEach {
    override def beforeAll(): Unit = {
      VitalsLog.configureLogging("catalog", consoleOnly = true)
    }
  }

  abstract class BurstCatalogSpecSupport extends AnyFlatSpec with Matchers with BeforeAndAfterAll {

//    lazy val catalogServer: CatalogService = CatalogService(CatalogUnitTestClientConfig)
    lazy val catalogServer: CatalogService = CatalogService(CatalogUnitTestServerConfig)

    override protected
    def beforeAll(): Unit = {
      VitalsLog.configureLogging("catalog", consoleOnly = true)
      log info s"------------STARTING------------"
      VitalsPropertyRegistry.logReport
      catalogServer.start
      log info s"------------STARTED------------\n"
    }

    override protected
    def afterAll(): Unit = {
      log info s"------------STOPPING-----------"
//      catalogServer.stop
      catalogServer.stop
      log info s"------------STOPPED------------\n"
    }


  }


}
